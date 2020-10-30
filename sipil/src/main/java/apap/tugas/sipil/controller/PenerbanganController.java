package apap.tugas.sipil.controller;
import apap.tugas.sipil.model.*;
import apap.tugas.sipil.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
public class PenerbanganController {
    @Qualifier("penerbanganServiceImpl")
    @Autowired
    PenerbanganService penerbanganService;

    @Autowired
    PilotPenerbanganService pilotPenerbanganService;

    @Autowired
    PilotService pilotService;

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

    @GetMapping("/penerbangan/ubah/{idPenerbangan}")
    public String changePenerbanganFormPage(
            @PathVariable Long idPenerbangan,
            Model model
    ){
        PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(idPenerbangan);
        model.addAttribute("penerbangan", penerbangan);
        return "form-update-penerbangan";
    }

    @PostMapping("/penerbangan/ubah")
    public String changePenerbanganFormSubmit(
            @ModelAttribute PenerbanganModel penerbangan,
            Model model
    ){
        PenerbanganModel penerbanganUpdated = penerbanganService.updatePenerbangan(penerbangan);
        model.addAttribute("penerbangan", penerbanganUpdated);
        return "update-penerbangan";
    }

    @GetMapping("/penerbangan/detail/{idPenerbangan}")
    public String viewDetailPenerbangan(
            @PathVariable Long idPenerbangan,
            Model model
    ){
        PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(idPenerbangan);
//        List<PilotPenerbanganModel> listPilotPenerbangan = pilotPenerbanganService.getListPilPenByPenerbangan(penerbangan);
        List<PilotModel> listPilot = pilotService.getPilotList();
        model.addAttribute("penerbangan", penerbangan);
//        model.addAttribute("pilot", penerbangan.getListPilotPenerbangan());
//        model.addAttribute("listPilpen", listPilotPenerbangan);
        model.addAttribute("listPilot", listPilot);
        return "view-penerbangan";
    }

    @PostMapping("/penerbangan/hapus")
    public String deletePenerbangan(
            @RequestParam(value="idPenerbangan") Long idPenerbangan,
            Model model
    ){
        PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(idPenerbangan);
        model.addAttribute("penerbangan", penerbangan);
//        System.out.println("kode " + penerbangan.getKode());
//        System.out.println("id " + idPenerbangan);
        if(penerbangan.getListPilotPenerbangan() == null || penerbangan.getListPilotPenerbangan().isEmpty()){
            penerbanganService.deletePenerbangan(penerbangan);
            return "delete-penerbangan";
        }else{
            return "error-delete-penerbangan";
        }
    }

    @PostMapping("/penerbangan/{idPenerbangan}/pilot/tambah")
    public String tambahPilot(
            @ModelAttribute Long idPilot, Long idPenerbangan,
            Model model
    ){
//        PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(idPenerbangan);
//        penerbangan.getListPilotPenerbangan().
        System.out.println("pilot " + idPilot);
        System.out.println("penerbangan " + idPenerbangan);
        return "tambah-pilot";
    }

}
