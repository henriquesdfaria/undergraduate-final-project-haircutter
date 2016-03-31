package br.com.haircutter.core.service.impl;

import br.com.haircutter.core.model.EstablishmentAuditLog;
import br.com.haircutter.core.model.repository.EstablishmentAuditLogRespository;
import br.com.haircutter.core.service.EstablishmentAuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Service
public class EstablishmentAuditLogServiceImpl implements EstablishmentAuditLogService {

    @Autowired
    private EstablishmentAuditLogRespository establishmentAuditLogRepository;

    @Override
    public List<EstablishmentAuditLog> getAuditLogsByCnpj(String cnpj) {

        return establishmentAuditLogRepository.findByEstablishmentCnpj(cnpj);
    }

    @Override
    public void registerLog(String cnpj, String author, String action) {

        EstablishmentAuditLog establishmentAuditLog = new EstablishmentAuditLog(null, cnpj, author, action,
                new Date(ZonedDateTime.now().toInstant().toEpochMilli()));

        establishmentAuditLogRepository.save(establishmentAuditLog);
    }

}
