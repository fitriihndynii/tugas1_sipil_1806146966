package apap.tugas.sipil.service;

import apap.tugas.sipil.model.*;

import java.util.List;

public interface PilotService {
    void addPilot(PilotModel pilot);
    void generateNip(PilotModel pilot);
    List<PilotModel> getPilotList();
    PilotModel getPilotByNipPilot(String nip);
    PilotModel getPilotByIdPilot(Long id);
    PilotModel updatePilot(PilotModel pilot);
    void oldPilot(PilotModel pilot);
    void deletePilot(PilotModel pilot);
}
