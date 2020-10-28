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

    @GetMapping("/pilot/view")
    public String viewDetailPilot(
            @RequestParam(value = "nipPilot") String nipPilot,
            Model model
    ){
        PilotModel pilot = pilotService.getPilotByNipPilot(nipPilot);
//        List<PenerbanganModel> listPenerbangan = pilotPenerbanganService.getListPenerbangan(pilot.getId());
//        System.out.println("List Penerbangan " + listPenerbangan);
        model.addAttribute("pilot", pilot);
//        model.addAttribute("listPenerbangan", listPenerbangan);
        return "view-pilot";
    }

    @RequestMapping("/pilot/view/{nipPilot}")
    public String viewDetailPilotWithPath(
            @PathVariable String nipPilot,
            Model model
    ){
        PilotModel pilot = pilotService.getPilotByNipPilot(nipPilot);
//        List<PenerbanganModel> listPenerbangan = pilotPenerbanganService.getListPenerbangan(pilot.getId());
        model.addAttribute("pilot", pilot);
//        model.addAttribute("listPenerbangan", listPenerbangan);
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
        model.addAttribute("akademiPilot");
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
}
