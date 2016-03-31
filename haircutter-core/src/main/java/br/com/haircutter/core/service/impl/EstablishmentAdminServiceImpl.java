package br.com.haircutter.core.service.impl;


import br.com.haircutter.core.enums.UserRoleEnum;
import br.com.haircutter.core.model.Establishment;
import br.com.haircutter.core.model.EstablishmentAdmin;
import br.com.haircutter.core.model.User;
import br.com.haircutter.core.model.UserRole;
import br.com.haircutter.core.model.repository.EstablishmentAdminRespository;
import br.com.haircutter.core.service.EstablishmentAdminService;
import br.com.haircutter.core.utils.HaircutterMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EstablishmentAdminServiceImpl implements EstablishmentAdminService {

    @Autowired
    private EstablishmentAdminRespository establishmentAdminRespository;

    @Autowired
    private HaircutterMailSender haircutterMailSender;

    public void createEstablishmentAdmin(Establishment establishment) {

        List<UserRole> userRoles = new ArrayList<>();
        userRoles.add(new UserRole(establishment.getCnpj(), UserRoleEnum.ROLE_ESTABLISHMENT_ADMIN));

        User user = new User(establishment.getCnpj(), establishment.getName(), "haircutter", userRoles, true,
                new Date(ZonedDateTime.now().toInstant().toEpochMilli()),
                new Date(ZonedDateTime.now().toInstant().toEpochMilli()));

        EstablishmentAdmin establishmentAdmin = establishmentAdminRespository.save(
                new EstablishmentAdmin(establishment.getCnpj(), user,
                        new Date(ZonedDateTime.now().toInstant().toEpochMilli()),
                        new Date(ZonedDateTime.now().toInstant().toEpochMilli()),
                        false));

        sendCreationEmail(establishment.getOwnerEmail(), establishmentAdmin.getUser());
    }

    private void sendCreationEmail(String ownerEmail, User user) {

        String subject = "Aprovação de Estabelecimento";

        String text = "Olá " + user.getName() + ",\n\n"
                + "Seu estabelecimento foi aprovado!\n\n" + "Usuário: " + user.getUsername() + "\n\n"
                + "Senha: " + user.getPassword() + "\n\n\n\nEquipe Haircutter";

        haircutterMailSender.sendEmail(ownerEmail, subject, text);
    }

}
