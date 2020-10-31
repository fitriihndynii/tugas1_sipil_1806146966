package apap.tugas.sipil.repository;

import apap.tugas.sipil.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface PilotDb extends JpaRepository<PilotModel, String> {
    List<PilotModel> findAll();
    Optional<PilotModel> findByNip(String nip);
    Optional<PilotModel> findById(Long id);
    List<PilotModel> findAllByMaskapai_KodeAndAkademi_Id(String Kode, Long id);
    List<PilotModel> findAllByMaskapai_Kode(String Kode);
    List<PilotModel> findAllByAkademi_Id(Long id);
    List<PilotModel> findDistinctByMaskapai_KodeOrderByListPilotPenerbanganDesc(String kode);
}