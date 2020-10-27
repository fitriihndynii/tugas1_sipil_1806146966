package apap.tugas.sipil.service;
import apap.tugas.sipil.model.*;
import apap.tugas.sipil.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PilotPenerbanganServiceImpl implements PilotPenerbanganService{
    @Autowired
    PilotPenerbanganDb pilotPenerbanganDb;

    @Override
    public List<PilotModel> getListPilot(Long penerbanganId){
        return pilotPenerbanganDb.findAllByPenerbangan(penerbanganId);
    }

    @Override
    public List<PenerbanganModel> getListPenerbangan(Long pilotId){
        return pilotPenerbanganDb.findAllByPilot(pilotId);
    }
}
