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
public class PilotController {
    @Qualifier("pilotServiceImpl")
    @Autowired
    private PilotService pilotService;

    @Autowired
    private AkademiService akademiService;

    @Autowired
    private MaskapaiService maskapaiService;

    @Autowired
    private PilotPenerbanganService pilotPenerbanganService;

    @GetMapping("/")
    private String home(){
        return "home";
    }

    @GetMapping("/pilot")
    public String listPilot(Model model){
        List<PilotModel> listPilot = pilotService.getPilotList();
        model.addAttribute( "listPilot", listPilot);
        return "viewall-pilot";
    }

    @GetMapping("/pilot/tambah")
    public String addPilotFormPage(Model model){
        model.addAttribute("pilot", new PilotModel());
        model.addAttribute("akademi", akademiService.getListAkademi());
        model.addAttribute("maskapai", maskapaiService.getListMaskapai());
        return "form-add-pilot";
    }

    @PostMapping("/pilot/tambah")
    public String addPilotSubmit(
            @ModelAttribute PilotModel pilot,
            Model model){
        pilotService.addPilot(pilot);
        model.addAttribute("nip", pilot.getNip());
        return "add-pilot";
    }

    @GetMapping("/pilot/{nipPilot}")
    public String viewDetailPilot(
            @PathVariable String nipPilot,
            Model model
    ){
        PilotModel pilot = pilotService.getPilotByNipPilot(nipPilot);
        model.addAttribute("pilot", pilot);
        model.addAttribute("listPilPen", pilot.getListPilotPenerbangan());
        return "view-pilot";
    }

    @GetMapping("/pilot/ubah/{nipPilot}")
    public String changePilotFormPage(
            @PathVariable String nipPilot,
            Model model
    ){
        PilotModel pilot = pilotService.getPilotByNipPilot(nipPilot);
        pilotService.oldPilot(pilot);
        model.addAttribute("pilot", pilot);
        model.addAttribute("akademi", akademiService.getListAkademi());
        model.addAttribute("maskapai", maskapaiService.getListMaskapai());
        return "form-update-pilot";
    }

    @PostMapping("/pilot/ubah")
    public String changePilotFormSubmit(
            @ModelAttribute PilotModel pilot,
            Model model
    ){
        PilotModel pilotUpdated = pilotService.updatePilot(pilot);
        model.addAttribute("pilot", pilotUpdated);
        return "update-pilot";
    }

//    @RequestMapping("/pilot/hapus/{nipPilot}")
//    public String deletePilot(
//            @PathVariable String nipPilot,
//            Model model
//    ){
//        PilotModel pilot = pilotService.getPilotByNipPilot(nipPilot);
//        Set<PilotPenerbanganModel> listPilotPenerbangan = pilot.getListPilotPenerbangan();
//        model.addAttribute("pilot", pilot);
//        if(listPilotPenerbangan.size() == 0){
//            pilotService.deletePilot(pilot);
//            return "delete-pilot";
//        }else{
//            return "error-delete-pilot";
//        }
//    }

    @PostMapping("/pilot/hapus")
    public String deletePilot(
            @RequestParam(value="idPilot") Long idPilot,
            Model model
    ){
        PilotModel pilot = pilotService.getPilotByIdPilot(idPilot);
        pilotService.deletePilot(pilot);
        model.addAttribute("pilot", pilot);
        return "delete-pilot";
    }
}
