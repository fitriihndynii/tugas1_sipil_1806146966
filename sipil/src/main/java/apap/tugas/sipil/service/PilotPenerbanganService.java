package apap.tugas.sipil.service;

import apap.tugas.sipil.model.*;
import java.util.List;
import java.util.Set;

public interface PilotPenerbanganService {
    PilotPenerbanganModel createPilPen(PilotModel pilot, PenerbanganModel penerbangan);
    void addPilotPenerbangan(PilotPenerbanganModel pilotPenerbangan);
    void deletePilotPenerbangan(PilotModel pilot);
    List<PilotPenerbanganModel> getListPilPenByPenerbangan(PenerbanganModel penerbangan);
    Set<PilotModel> pilotBulanIni();
}
