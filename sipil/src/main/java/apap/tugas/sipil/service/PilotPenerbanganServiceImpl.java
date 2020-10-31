package apap.tugas.sipil.service;
import apap.tugas.sipil.model.*;
import apap.tugas.sipil.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class PilotPenerbanganServiceImpl implements PilotPenerbanganService{
    @Autowired
    PilotPenerbanganDb pilotPenerbanganDb;

    @Override
    public void addPilotPenerbangan(PilotPenerbanganModel pilotPenerbangan){
        pilotPenerbanganDb.save(pilotPenerbangan);
    }

    @Override
    public PilotPenerbanganModel createPilPen(PilotModel pilot, PenerbanganModel penerbangan){
        PilotPenerbanganModel pilpen = new PilotPenerbanganModel();
        pilpen.setPilot(pilot);
        pilpen.setPenerbangan(penerbangan);
        pilpen.setTanggalPenugasan(LocalDate.now());
        addPilotPenerbangan(pilpen);
        return pilpen;
    }

    @Override
    public List<PilotPenerbanganModel> getListPilPenByPenerbangan(PenerbanganModel penerbangan){
        return pilotPenerbanganDb.findAllByPenerbangan(penerbangan);
    }
}
