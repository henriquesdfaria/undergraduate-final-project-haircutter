package br.com.haircutter.core.model.repository;

import br.com.haircutter.core.model.EstablishmentEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface EstablishmentEmployeeRespository extends JpaRepository<EstablishmentEmployee, Long> {

    List<EstablishmentEmployee> findAllByDeleted(Boolean deleted);

}
