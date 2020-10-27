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

    PilotPenerbanganDb pilotPenerbanganDb;

    @Override
    public void addPilot(PilotModel pilot){
        pilotDb.save(pilot);
    }

    @Override
    public void generateNip(PilotModel pilot){
        String gender = pilot.getJenisKelamin().toString();
        String awalTempatLahir = pilot.getTempatLahir().substring(0,2);
        String akhirNama = String.valueOf(pilot.getNamaPilot().charAt(pilot.getNamaPilot().length()-1));
        System.out.println("akhirnama= " + akhirNama);
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
}
