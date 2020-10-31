package apap.tugas.sipil.repository;

import apap.tugas.sipil.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

public interface PilotPenerbanganDb extends JpaRepository<PilotPenerbanganModel, Long> {
    List<PilotPenerbanganModel> findAllByPenerbangan(PenerbanganModel penerbanganModel);
    List<PilotPenerbanganModel> findAllByPilot(PilotModel pilot);
    List<PilotPenerbanganModel> findAll();
//    List<PilotPenerbanganModel> findDistinctByTanggalPenugasanContaining(Month month);
}