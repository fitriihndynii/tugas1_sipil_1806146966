package apap.tugas.sipil.service;

import apap.tugas.sipil.model.*;

import java.util.LinkedHashMap;
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
    List<PilotModel> pilotByMaskapaiAndAkademi(String kode, Long id);
    List<PilotModel> pilotByMaskapai(String kode);
    List<PilotModel> pilotByAkademi(Long id);
    LinkedHashMap<PilotModel, Integer> pilotPenerbanganTerbanyak(String kode);
}
