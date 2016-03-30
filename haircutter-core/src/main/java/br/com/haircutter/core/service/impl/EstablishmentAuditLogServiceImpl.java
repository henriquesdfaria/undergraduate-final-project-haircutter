package br.com.haircutter.core.service.impl;

import br.com.haircutter.core.model.EstablishmentAuditLog;
import br.com.haircutter.core.model.repository.EstablishmentAuditLogRespository;
import br.com.haircutter.core.service.EstablishmentAuditLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EstablishmentAuditLogServiceImpl implements EstablishmentAuditLogService {

    @Autowired
    private EstablishmentAuditLogRespository establishmentAuditLogRepository;


    @Override
    public List<EstablishmentAuditLog> getAuditLogsByCnpj(String cnpj) {

        return establishmentAuditLogRepository.findByEstablishmentCnpj(cnpj);
    }


}
