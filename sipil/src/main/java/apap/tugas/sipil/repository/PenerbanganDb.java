package apap.tugas.sipil.repository;

import apap.tugas.sipil.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface PenerbanganDb extends JpaRepository<PenerbanganModel, Long> {
    Optional<PenerbanganModel> findById(Long id);
    List<PenerbanganModel> findAll();
}