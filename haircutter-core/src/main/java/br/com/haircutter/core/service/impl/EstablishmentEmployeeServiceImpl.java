package br.com.haircutter.core.service.impl;


import br.com.haircutter.core.model.EstablishmentEmployee;
import br.com.haircutter.core.model.User;
import br.com.haircutter.core.model.repository.EstablishmentEmployeeRespository;
import br.com.haircutter.core.model.repository.UserRespository;
import br.com.haircutter.core.service.EstablishmentAuditLogService;
import br.com.haircutter.core.service.EstablishmentEmployeeService;
import br.com.haircutter.core.validator.EstablishmentEmployeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Service
public class EstablishmentEmployeeServiceImpl implements EstablishmentEmployeeService {

    @Autowired
    EstablishmentEmployeeRespository repository;

    @Autowired
    EstablishmentEmployeeValidator validator;

    @Autowired
    EstablishmentAuditLogService auditLogService;

    @Autowired
    UserRespository userRespository;


    @Override
    public EstablishmentEmployee get(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<EstablishmentEmployee> getAllByEstablishment(String cnpj) {
        return repository.findAllByEstablishmentCnpjAndDeleted(cnpj, false);
    }

    @Override
    public EstablishmentEmployee create(EstablishmentEmployee establishmentEmployee, String username) {

        validator.validateCreate(establishmentEmployee);

        Date now = new Date(ZonedDateTime.now().toInstant().toEpochMilli());

        establishmentEmployee.setDeleted(false);
        establishmentEmployee.setCreationTime(now);
        establishmentEmployee.setLastModifiedDate(now);

        establishmentEmployee.getUser().setPassword(BCrypt.hashpw("haircutter", BCrypt.gensalt(10)));
        establishmentEmployee.getUser().setLastModifiedDate(now);
        establishmentEmployee.getUser().setCreationTime(now);
        establishmentEmployee.getUser().setEnabled(true);

        EstablishmentEmployee createdEstablishmentEmployee = repository.save(establishmentEmployee);

        auditLogService.registerLog(establishmentEmployee.getEstablishmentCnpj(), username, "Criou usuário " + establishmentEmployee.getUser().getUsername());

        return createdEstablishmentEmployee;
    }

    @Override
    public void edit(EstablishmentEmployee establishmentEmployee, String username) {

        EstablishmentEmployee foundEmployee = repository.findOne(establishmentEmployee.getId());

        validator.validateEdit(establishmentEmployee, foundEmployee.getUser().getUsername());

        establishmentEmployee.setLastModifiedDate(new Date(ZonedDateTime.now().toInstant().toEpochMilli()));

        repository.save(establishmentEmployee);

        auditLogService.registerLog(establishmentEmployee.getEstablishmentCnpj(), username, "Editou usuário " + establishmentEmployee.getUser().getUsername());
    }

    @Override
    public void delete(Long id, String username) {

        EstablishmentEmployee establishmentEmployee = repository.findOne(id);

        establishmentEmployee.setDeleted(true);
        establishmentEmployee.setLastModifiedDate(new Date(ZonedDateTime.now().toInstant().toEpochMilli()));

        repository.save(establishmentEmployee);

        User user = userRespository.findOneByUsername(establishmentEmployee.getUser().getUsername());
        user.setEnabled(false);

        userRespository.save(user);

        auditLogService.registerLog(establishmentEmployee.getEstablishmentCnpj(), username, "Removeu usuário " + establishmentEmployee.getUser().getUsername());
    }

}
