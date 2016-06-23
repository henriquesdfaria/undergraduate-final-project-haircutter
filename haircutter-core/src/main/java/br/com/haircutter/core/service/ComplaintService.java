package br.com.haircutter.core.service;

import br.com.haircutter.core.model.Complaint;

import java.util.List;

/**
 * Created by hfaria on 6/23/16.
 */
public interface ComplaintService {

    List<Complaint> getAllByStatus(String status);

    Complaint create(Complaint complaint);

    void resolve(Long complaintId);

}
