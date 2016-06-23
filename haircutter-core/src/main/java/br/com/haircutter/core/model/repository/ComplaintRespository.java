package br.com.haircutter.core.model.repository;

import br.com.haircutter.core.model.Complaint;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ComplaintRespository extends CrudRepository<Complaint, Long> {

    List<Complaint> findByStatus(String status);
}
