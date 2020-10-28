package apap.tugas.sipil.controller;
import apap.tugas.sipil.model.*;
import apap.tugas.sipil.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PenerbanganController {
    @Qualifier("penerbanganServiceImpl")
    @Autowired
    PenerbanganService penerbanganService;

    @GetMapping("/penerbangan")
    public String listPenerbangan(Model model){
        List<PenerbanganModel> listPenerbangan = penerbanganService.getPenerbanganList();
        model.addAttribute( "listPenerbangan", listPenerbangan);
        return "viewall-penerbangan";
    }

    @GetMapping("/penerbangan/tambah")
    public String addPenerbanganFormPage(Model model){
        model.addAttribute("penerbangan", new PilotModel());
        return "form-add-penerbangan";
    }

    @PostMapping("/penerbangan/tambah")
    public String addPilotSubmit(
            @ModelAttribute PenerbanganModel penerbangan,
            Model model){
        penerbanganService.addPenerbangan(penerbangan);
        model.addAttribute("kode", penerbangan.getKode());
        return "add-penerbangan";
    }
}
