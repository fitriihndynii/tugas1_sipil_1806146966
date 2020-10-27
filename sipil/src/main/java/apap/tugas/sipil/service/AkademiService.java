package apap.tugas.sipil.service;

import apap.tugas.sipil.model.*;

import java.util.List;

public interface AkademiService {
    List<AkademiModel> getListAkademi();
    AkademiModel getAkademiByNama(String nama);
}
