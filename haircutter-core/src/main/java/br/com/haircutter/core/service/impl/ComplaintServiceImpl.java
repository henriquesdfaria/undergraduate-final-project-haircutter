package br.com.haircutter.core.service.impl;


import br.com.haircutter.core.model.Complaint;
import br.com.haircutter.core.model.repository.ComplaintRespository;
import br.com.haircutter.core.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    @Autowired
    ComplaintRespository complaintRespository;

    @Override
    public List<Complaint> getAllByStatus(String status) {
        return complaintRespository.findByStatus(status);
    }

    @Override
    public Complaint create(Complaint complaint) {
        complaint.setLastModifiedDate(new Date(ZonedDateTime.now().toInstant().toEpochMilli()));
        complaint.setCreationTime(new Date(ZonedDateTime.now().toInstant().toEpochMilli()));
        complaint.setStatus("PENDING");

        return complaintRespository.save(complaint);
    }

    @Override
    public void resolve(Long complaintId) {
        Complaint complaint = complaintRespository.findOne(complaintId);
        complaint.setStatus("VERIFIED");

        complaintRespository.save(complaint);
    }
}
