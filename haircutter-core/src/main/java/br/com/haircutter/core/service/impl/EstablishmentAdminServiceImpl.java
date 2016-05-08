package br.com.haircutter.core.service.impl;


import br.com.haircutter.core.enums.UserRoleEnum;
import br.com.haircutter.core.model.Establishment;
import br.com.haircutter.core.model.EstablishmentAdmin;
import br.com.haircutter.core.model.User;
import br.com.haircutter.core.model.repository.EstablishmentAdminRespository;
import br.com.haircutter.core.service.EstablishmentAdminService;
import br.com.haircutter.core.utils.HaircutterMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Service
public class EstablishmentAdminServiceImpl implements EstablishmentAdminService {

    @Autowired
    private EstablishmentAdminRespository establishmentAdminRespository;

    @Autowired
    private HaircutterMailSender haircutterMailSender;

    public void createEstablishmentAdmin(Establishment establishment) {


        User user = new User(establishment.getCnpj(), establishment.getName(),
                BCrypt.hashpw("haircutter", BCrypt.gensalt(10)), UserRoleEnum.ROLE_ESTABLISHMENT_ADMIN, true, null,
                null, null, new Date(ZonedDateTime.now().toInstant().toEpochMilli()),
                new Date(ZonedDateTime.now().toInstant().toEpochMilli()));

        EstablishmentAdmin establishmentAdmin = establishmentAdminRespository.save(
                new EstablishmentAdmin(establishment.getCnpj(), user,
                        new Date(ZonedDateTime.now().toInstant().toEpochMilli()),
                        new Date(ZonedDateTime.now().toInstant().toEpochMilli())));

        sendCreationEmail(establishment.getOwnerName(), establishment.getOwnerEmail(), establishmentAdmin.getUser());
    }

    @Override
    public void disableEstablishmentAdmins(String cnpj) {
        List<EstablishmentAdmin> establishmentAdmins = establishmentAdminRespository.findByEstablishmentCnpj(cnpj);
        Date now = new Date(ZonedDateTime.now().toInstant().toEpochMilli());

        establishmentAdmins.stream().forEach(establishmentAdmin -> {
            establishmentAdmin.getUser().setEnabled(false);
            establishmentAdmin.getUser().setLastModifiedDate(now);
            establishmentAdmin.setLastModifiedDate(now);
            establishmentAdminRespository.save(establishmentAdmin);
        });
    }

    private void sendCreationEmail(String ownerName, String ownerEmail, User user) {

        String subject = "Aprovação de Estabelecimento";

        String text = "Olá " + ownerName + ",\n\n"
                + "Seu estabelecimento foi aprovado!\n\n" + "Usuário: " + user.getUsername() + "\n\n"
                + "Senha: haircutter\n\n\n\nEquipe Haircutter";

        haircutterMailSender.sendEmail(ownerEmail, subject, text);
    }

}
