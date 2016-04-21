package br.com.haircutter.core.service.impl;


import br.com.haircutter.core.enums.EstablishmentStatusEnum;
import br.com.haircutter.core.model.Establishment;
import br.com.haircutter.core.model.repository.EstablishmentRespository;
import br.com.haircutter.core.service.EstablishmentAdminService;
import br.com.haircutter.core.service.EstablishmentAuditLogService;
import br.com.haircutter.core.service.EstablishmentCreationRequestService;
import br.com.haircutter.core.utils.HaircutterMailSender;
import br.com.haircutter.core.validator.EstablishmentCreationRequestServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Service
public class EstablishmentCreationRequestServiceImpl implements EstablishmentCreationRequestService {

    @Autowired
    private EstablishmentRespository establishmentRepository;

    @Autowired
    private EstablishmentCreationRequestServiceValidator validator;

    @Autowired
    private HaircutterMailSender mailSender;

    @Autowired
    private EstablishmentAdminService establishmentAdminService;

    @Autowired
    private EstablishmentAuditLogService establishmentAuditLogService;

    @Override
    public Establishment create(final Establishment establishment) {

        validator.validateCreate(establishment);

        establishment.setStatus(EstablishmentStatusEnum.WAITING);

        Date now = new Date(ZonedDateTime.now().toInstant().toEpochMilli());

        establishment.setCreationTime(now);
        establishment.setLastModifiedDate(now);

        Establishment createdRequest = establishmentRepository.save(establishment);

        sendCreationRequestEmail(createdRequest);

        establishmentAuditLogService.registerLog(createdRequest.getCnpj(), createdRequest.getOwnerName(),
                "Solicitou criação do estabelecimento");

        return createdRequest;

    }

    @Override
    public List<Establishment> getAll() {

        establishmentRepository.findByStatus(EstablishmentStatusEnum.WAITING);

        return establishmentRepository.findByStatus(EstablishmentStatusEnum.WAITING);
    }

    @Override
    public void approve(final String cnpj) {

        final Establishment creationRequest = establishmentRepository.findOneByCnpj(cnpj);

        validator.validateApproveOrDeny(creationRequest);

        creationRequest.setStatus(EstablishmentStatusEnum.ACTIVE);
        creationRequest.setLastModifiedDate(new Date(ZonedDateTime.now().toInstant().toEpochMilli()));

        Establishment establishment = establishmentRepository.save(creationRequest);

        establishmentAuditLogService.registerLog(establishment.getCnpj(), "moderador",
                "Aprovou a criação do estabelecimento");

        establishmentAdminService.createEstablishmentAdmin(establishment);

        establishmentAuditLogService.registerLog(establishment.getCnpj(), "moderador",
                "Criou o usuário de administrador do estabelecimento");
    }

    @Override
    public void deny(final Establishment establishment) {

        Establishment creationRequest = establishmentRepository.findOneByCnpj(establishment.getCnpj());

        validator.validateApproveOrDeny(creationRequest);

        creationRequest.setDenyCause(establishment.getDenyCause());
        creationRequest.setStatus(EstablishmentStatusEnum.DENIED);
        creationRequest.setLastModifiedDate(new Date(ZonedDateTime.now().toInstant().toEpochMilli()));

        Establishment deniedEstablishment = establishmentRepository.save(creationRequest);


        sendEstablishmentDeniedEmail(deniedEstablishment);
    }

    private void sendCreationRequestEmail(Establishment createdRequest) {

        String subject = "Solicitação de Criação de Estabelecimento";

        String text = "Olá " + createdRequest.getOwnerName() + ",\n\n"
                + "Sua solicitação foi realizada com sucesso!\n\n"
                + "Aguarde a sua aprovação.\n\nEquipe Haircutter";


        mailSender.sendEmail(createdRequest.getOwnerEmail(), subject, text);
    }


    private void sendEstablishmentDeniedEmail(final Establishment establishment) {

        String subject = "Estabelecimento não aprovado";

        String text = "Olá " + establishment.getOwnerName() + ",\n\n"
                + "Infelizmente sua solicitação não foi aprovada!\n\n"
                + (establishment.getDenyCause() != null ? establishment.getDenyCause() : "")
                + "\n\nEquipe Haircutter";

        mailSender.sendEmail(establishment.getOwnerEmail(), subject, text);
    }

}
