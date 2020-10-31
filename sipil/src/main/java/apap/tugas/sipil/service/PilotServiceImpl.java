package apap.tugas.sipil.service;

import apap.tugas.sipil.model.*;
import apap.tugas.sipil.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class PilotServiceImpl implements PilotService{
    @Autowired
    PilotDb pilotDb;

    PilotPenerbanganService pilotPenerbanganService;

    private PilotModel oldPilot;

    @Override
    public void addPilot(PilotModel pilot){
        generateNip(pilot);
        pilotDb.save(pilot);
    }

    @Override
    public void generateNip(PilotModel pilot){
        String gender = pilot.getJenisKelamin().toString();
        String awalTempatLahir = pilot.getTempatLahir().substring(0,2);
        String akhirNama = String.valueOf(pilot.getNama().charAt(pilot.getNama().length()-1));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMM");
        String ddmm = pilot.getTanggalLahir().format(formatter);
        String tahun = Integer.toString((int)(Math.floor(pilot.getTanggalLahir().getYear()/10)));
        Random randomizer = new Random();
        int ascii1 = 65 + randomizer.nextInt(26);
        int ascii2 = 65 + randomizer.nextInt(26);
        String nip = (gender + awalTempatLahir + akhirNama + ddmm + tahun + (char)ascii1 + (char)ascii2).toUpperCase();
        pilot.setNip(nip);
    }

    @Override
    public List<PilotModel> getPilotList(){
        return pilotDb.findAll();
    }

    @Override
    public PilotModel getPilotByNipPilot(String nip){
        return pilotDb.findByNip(nip).get();
    }

    @Override
    public PilotModel getPilotByIdPilot(Long id){
        return pilotDb.findById(id).get();
    }

    @Override
    public PilotModel updatePilot(PilotModel pilot){
        PilotModel targetPilot = pilotDb.findByNip(pilot.getNip()).get();

        try{
            targetPilot.setNama(pilot.getNama());
            targetPilot.setNik(pilot.getNik());
            targetPilot.setTempatLahir(pilot.getTempatLahir());
            targetPilot.setTanggalLahir(pilot.getTanggalLahir());
            targetPilot.setJenisKelamin(pilot.getJenisKelamin());
            targetPilot.setAkademi(pilot.getAkademi());
            targetPilot.setMaskapai(pilot.getMaskapai());
            if(targetPilot.getNama() == oldPilot.getNama() || targetPilot.getTempatLahir() == oldPilot.getTempatLahir() || targetPilot.getTanggalLahir() == oldPilot.getTanggalLahir() || targetPilot.getJenisKelamin() == oldPilot.getJenisKelamin()){
                pilotDb.save(targetPilot);
            }else{
                generateNip(targetPilot);
                pilotDb.save(targetPilot);
            }
            return targetPilot;
        } catch (NullPointerException nullException){
            return null;
        }
    }

    @Override
    public void oldPilot(PilotModel pilot){
        this.oldPilot = pilot;
    }

    @Override
    public void deletePilot(PilotModel pilot){
        System.out.println("pilottt " + pilot);
        pilotPenerbanganService.deletePilotPenerbangan(pilot);
        pilotDb.delete(pilot);
    }

    @Override
    public List<PilotModel> pilotByMaskapaiAndAkademi(String kode, Long id){
        return pilotDb.findAllByMaskapai_KodeAndAkademi_Id(kode, id);
    }

    @Override
    public List<PilotModel> pilotByMaskapai(String kode){
        return pilotDb.findAllByMaskapai_Kode(kode);
    }

    @Override
    public List<PilotModel> pilotByAkademi(Long id){
        return pilotDb.findAllByAkademi_Id(id);
    }
}
