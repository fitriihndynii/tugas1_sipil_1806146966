package apap.tugas.sipil.repository;

import apap.tugas.sipil.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface AkademiDb extends JpaRepository<AkademiModel, Long> {
    Optional<AkademiModel> findById(Long id);
    List<AkademiModel> findAll();
    AkademiModel findByNama(String nama);
}