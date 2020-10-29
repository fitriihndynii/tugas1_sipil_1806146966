package apap.tugas.sipil.service;

import apap.tugas.sipil.model.*;
import java.util.List;

public interface PilotPenerbanganService {
    List<PilotPenerbanganModel> getListPilPenByPenerbangan(PenerbanganModel penerbangan);
}
