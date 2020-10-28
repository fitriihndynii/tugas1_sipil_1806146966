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
public class PenerbanganServiceImpl implements PenerbanganService{
    @Autowired
    PenerbanganDb penerbanganDb;

    @Override
    public void addPenerbangan(PenerbanganModel penerbangan){
        penerbanganDb.save(penerbangan);
    }

    @Override
    public List<PenerbanganModel> getPenerbanganList(){
        return penerbanganDb.findAll();
    }

    @Override
    public PenerbanganModel getPenerbanganById(Long id){
        return penerbanganDb.findById(id).get();
    }
}