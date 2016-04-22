package br.com.haircutter.core.model.repository;

import br.com.haircutter.core.model.EstablishmentAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface EstablishmentAdminRespository extends JpaRepository<EstablishmentAdmin, Long> {

    List<EstablishmentAdmin> findByEstablishmentCnpj(String cnpj);

}
