package apap.tugas.sipil.service;
import apap.tugas.sipil.model.*;
import apap.tugas.sipil.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Override
    public void deletePilotPenerbangan(PilotModel pilot){
        List<PilotPenerbanganModel> pilpen = pilotPenerbanganDb.findAllByPilot(pilot);
        System.out.println("pilpen " + pilpen==null);
        for(PilotPenerbanganModel pilots :pilpen){
            pilotPenerbanganDb.delete(pilots);
        }
    }

    @Override
    public Set<PilotModel> pilotBulanIni(){
        LocalDate start = LocalDate.ofEpochDay(System.currentTimeMillis() / (24 * 60 * 60 * 1000) ).withDayOfMonth(1);
        LocalDate end = LocalDate.ofEpochDay(System.currentTimeMillis() / (24 * 60 * 60 * 1000) ).plusMonths(1).withDayOfMonth(1).minusDays(1);
        List<PilotPenerbanganModel> pilpenBulanIni = pilotPenerbanganDb.findByTanggalPenugasanGreaterThanAndTanggalPenugasanLessThan(start, end);
        System.out.println("pilpen " + pilpenBulanIni);
        Set<PilotModel> listPilotBulanIni = new HashSet<PilotModel>();
        for (PilotPenerbanganModel pilpen: pilpenBulanIni){
            listPilotBulanIni.add(pilpen.getPilot());
        }
        return listPilotBulanIni;
    }
}
