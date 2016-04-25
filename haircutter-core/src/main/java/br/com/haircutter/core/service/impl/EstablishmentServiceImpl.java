package br.com.haircutter.core.service.impl;

import br.com.haircutter.core.enums.EstablishmentStatusEnum;
import br.com.haircutter.core.model.Establishment;
import br.com.haircutter.core.model.repository.EstablishmentRespository;
import br.com.haircutter.core.service.EstablishmentAdminService;
import br.com.haircutter.core.service.EstablishmentAuditLogService;
import br.com.haircutter.core.service.EstablishmentService;
import br.com.haircutter.core.utils.HaircutterMailSender;
import br.com.haircutter.core.validator.EstablishmentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Created by hfaria on 31/03/16.
 */
@Service
public class EstablishmentServiceImpl implements EstablishmentService {

    @Autowired
    EstablishmentRespository establishmentRespository;

    @Autowired
    EstablishmentValidator validator;

    @Autowired
    EstablishmentAuditLogService auditLogService;

    @Autowired
    EstablishmentAdminService establishmentAdminService;

    @Autowired
    private HaircutterMailSender mailSender;

    @Override
    public Establishment get(String cnpj) {

        return establishmentRespository.findOneByCnpj(cnpj);
    }

    @Override
    public void edit(Establishment establishment, String username) {

        validator.validateEdit(establishment);

        establishment.setLastModifiedDate(new Date(ZonedDateTime.now().toInstant().toEpochMilli()));

        Establishment editedEstablishment = establishmentRespository.save(establishment);

        auditLogService.registerLog(editedEstablishment.getCnpj(), username, "Editou perfil do estabelecimento");
    }

    @Override
    public void deactivate(String cnpj, String username) {
        Establishment foundEstablishement = establishmentRespository.findOneByCnpj(cnpj);

        foundEstablishement.setLastModifiedDate(new Date(ZonedDateTime.now().toInstant().toEpochMilli()));
        foundEstablishement.setStatus(EstablishmentStatusEnum.INACTIVE);

        Establishment inactivatedEstablishment = establishmentRespository.save(foundEstablishement);

        auditLogService.registerLog(inactivatedEstablishment.getCnpj(), username, "Editou perfil do estabelecimento");

        establishmentAdminService.disableEstablishmentAdmins(inactivatedEstablishment.getCnpj());

        sendEstablishmentInactivatedEmail(inactivatedEstablishment);
    }

    private void sendEstablishmentInactivatedEmail(final Establishment establishment) {

        String subject = "Estabelecimento desativado";

        String text = "Olá " + establishment.getOwnerName() + ",\n\n"
                + "Você desativou o estabelecimento " + establishment.getName() + ".\n\n"
                + "Caso deseje reativá-lo entre em contato conosco.\n\n"
                + "\n\nEquipe Haircutter";

        mailSender.sendEmail(establishment.getOwnerEmail(), subject, text);
    }
}
