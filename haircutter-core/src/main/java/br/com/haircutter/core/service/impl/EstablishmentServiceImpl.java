package br.com.haircutter.core.service.impl;

import br.com.haircutter.core.enums.EstablishmentStatusEnum;
import br.com.haircutter.core.enums.UserRoleEnum;
import br.com.haircutter.core.exception.CustomInvalidException;
import br.com.haircutter.core.model.Establishment;
import br.com.haircutter.core.model.EstablishmentAdmin;
import br.com.haircutter.core.model.User;
import br.com.haircutter.core.model.UserRole;
import br.com.haircutter.core.model.repository.EstablishmentAdminRespository;
import br.com.haircutter.core.model.repository.EstablishmentRespository;
import br.com.haircutter.core.model.repository.UserRespository;
import br.com.haircutter.core.service.EstablishmentService;
import br.com.haircutter.core.validator.EstablishmentServiceValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class EstablishmentServiceImpl implements EstablishmentService {

    Logger LOGGER = LoggerFactory.getLogger(EstablishmentServiceImpl.class);

    private static final String HAIRCUTTER_EMAIL = "haircutterteam@gmail.com";

    @Autowired
    private EstablishmentRespository establishmentRepository;

    @Autowired
    private EstablishmentAdminRespository establishmentAdminRespository;

    @Autowired
    private UserRespository userRepository;

    @Autowired
    private EstablishmentServiceValidator validator;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public Establishment createNewRequest(final Establishment establishment) {

        validator.validateNewCreationRequest(establishment);

        establishment.setStatus(EstablishmentStatusEnum.WAITING);
        establishment.setCreationTime(new Date(ZonedDateTime.now().toInstant().toEpochMilli()));
        establishment.setLastModifiedDate(new Date(ZonedDateTime.now().toInstant().toEpochMilli()));

        Establishment createdRequest = establishmentRepository.save(establishment);

        createInactiveEstablishmentAdminUser(createdRequest);

        sendCreationRequestEmail(createdRequest);

        return createdRequest;

    }

    @Override
    public List<Establishment> getCreationRequests() {

        establishmentRepository.findByStatus(EstablishmentStatusEnum.WAITING);

        return establishmentRepository.findByStatus(EstablishmentStatusEnum.WAITING);
    }

    @Override
    public Establishment getCreationRequestByCnpj(final String cnpj) {

        Establishment creationRequest = null;

        try {
            creationRequest = establishmentRepository.findOneByCnpj(cnpj);
        } catch (Exception e) {
            throw new CustomInvalidException(e.getMessage(), "id");
        }

        return creationRequest;
    }

    @Override
    public Establishment approveCreationRequest(final String cnpj) {

        final Establishment creationRequest = establishmentRepository.findOneByCnpj(cnpj);

        validator.validateApproveOrDenyCreationRequest(creationRequest);

        creationRequest.setStatus(EstablishmentStatusEnum.ACTIVE);

        Establishment approvedEstablishment = establishmentRepository.save(creationRequest);

        User user = activeEstablishmentAdminUser(approvedEstablishment);

        sendEstablishmentApprovedEmail(approvedEstablishment, user);

        return approvedEstablishment;

    }

    @Override
    public Establishment denyCreationRequest(final String cnpj) {

        Establishment creationRequest = establishmentRepository.findOneByCnpj(cnpj);

        validator.validateApproveOrDenyCreationRequest(creationRequest);

        creationRequest.setStatus(EstablishmentStatusEnum.DENIED);

        Establishment deniedEstablishment = establishmentRepository.save(creationRequest);

        User user = activeEstablishmentAdminUser(deniedEstablishment);

        sendEstablishmentDeniedEmail(deniedEstablishment, user);

        return deniedEstablishment;

    }

    private void sendCreationRequestEmail(Establishment createdRequest) {
        MimeMessagePreparator mmp = new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setRecipient(RecipientType.TO, new InternetAddress(createdRequest.getOwnerEmail()));
                mimeMessage.setFrom(HAIRCUTTER_EMAIL);
                mimeMessage.setSubject("Solicitação de Criação de Estabelecimento");
                mimeMessage.setText("Olá " + createdRequest.getOwnerName() + ",\n\n"
                        + "Sua solicitação foi realizada com sucesso!\n\n"
                        + "Aguarde a sua aprovação.\n\nEquipe Haircutter");

            }
        };

        sendEmail(mmp);
    }

    private void sendEstablishmentApprovedEmail(final Establishment approvedEstablishment, User user) {
        MimeMessagePreparator mmp = new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setRecipient(RecipientType.TO, new InternetAddress(approvedEstablishment.getOwnerEmail()));
                mimeMessage.setFrom(HAIRCUTTER_EMAIL);
                mimeMessage.setSubject("Aprovação de Estabelecimento");
                mimeMessage.setText("Olá " + user.getName() + ",\n\n"
                        + "Finalmente seu estabelecimento foi aprovado!\n\n" + "Usuário: " + user.getUsername() + "\n\n"
                        + "Senha: " + user.getPassword() + "\n\n" + "Aguarde a sua aprovação.\n\nEquipe Haircutter");

            }
        };

        sendEmail(mmp);
    }

    private void sendEstablishmentDeniedEmail(final Establishment deniedEstablishment, User user) {
        MimeMessagePreparator mmp = new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setRecipient(RecipientType.TO, new InternetAddress(deniedEstablishment.getOwnerEmail()));
                mimeMessage.setFrom(HAIRCUTTER_EMAIL);
                mimeMessage.setSubject("Estabelecimento não aprovado");
                mimeMessage.setText("Olá " + user.getName() + ",\n\n"
                        + "Infelizmente sua solicitação não foi aprovada!\n\n" + "\n\nEquipe Haircutter");

            }
        };

        sendEmail(mmp);
    }

    private void sendEmail(MimeMessagePreparator mmp) {
        try {
            javaMailSender.send(mmp);
            LOGGER.info("E-mail has been sent");
        } catch (MailException me) {
            LOGGER.info("Error sending E-mail");
        }
    }

    private void createInactiveEstablishmentAdminUser(Establishment createdRequest) {
        EstablishmentAdmin establishmentAdmin = new EstablishmentAdmin(null, createdRequest.getCnpj(),
                createdRequest.getCnpj());

        List<UserRole> userRoles = new ArrayList<>();
        userRoles.add(new UserRole(createdRequest.getCnpj(), UserRoleEnum.ROLE_ESTABLISHMENT_ADMIN));

        User user = new User(createdRequest.getCnpj(), createdRequest.getName(), "haircutter", userRoles, false,
                new Date(ZonedDateTime.now().toInstant().toEpochMilli()),
                new Date(ZonedDateTime.now().toInstant().toEpochMilli()));

        userRepository.save(user);

        establishmentAdminRespository.save(establishmentAdmin);
    }

    private User activeEstablishmentAdminUser(Establishment approvedEstablishment) {
        User user = userRepository.findOneByUsername(approvedEstablishment.getCnpj());
        user.setEnabled(true);

        userRepository.save(user);
        return user;
    }
}
