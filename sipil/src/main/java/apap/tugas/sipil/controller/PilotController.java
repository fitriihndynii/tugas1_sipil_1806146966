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
        model.addAttribute( "listHotel", listPilot);
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
        System.out.println("nama " + pilot.getNamaPilot());
        System.out.println("nik " + pilot.getNik());
        System.out.println("nip " + pilot.getNip());
        System.out.println("gender " + pilot.getJenisKelamin());
        System.out.println("tanggalLahir " + pilot.getTanggalLahir());
        System.out.println("tempat " + pilot.getTempatLahir());
        System.out.println("sekolah " + pilot.getAkademi());
        System.out.println("maskapai " + pilot.getMaskapai());
        pilotService.generateNip(pilot);
        pilotService.addPilot(pilot);
        model.addAttribute("nip", pilot.getNip());
        return "add-pilot";
    }

    @GetMapping("/pilot/{nipPilot}")
    public String viewDetailPilot(
            @RequestParam(value = "nip") String nip,
            Model model
    ){
        PilotModel pilot = pilotService.getPilotByNipPilot(nip);
        List<PenerbanganModel> listPenerbangan = pilotPenerbanganService.getListPenerbangan(pilot.getId());
        model.addAttribute("pilot", pilot);
        model.addAttribute("listPenerbangan", listPenerbangan);
        return "view-pilot";
    }

//    @RequestMapping("/pilot/{nipPilot}")
//    public String viewDetailPilotWithPath(
//            @PathVariable String nip,
//            Model model
//    ){
//        PilotModel pilot = pilotService.getPilotByNipPilot(nip);
//        List<PenerbanganModel> listPenerbangan = pilotPenerbanganService.getListPenerbangan(pilot.getId());
//        model.addAttribute("pilot", pilot);
//        model.addAttribute("listPenerbangan", listPenerbangan);
//        return "view-pilot";
//    }
}
