package keretrendszer.beadando.controller;

import keretrendszer.beadando.exceptions.DinoAlreadyInSystem;
import keretrendszer.beadando.exceptions.DinoNotFound;
import keretrendszer.beadando.exceptions.WrongRegisterDate;
import keretrendszer.beadando.model.Diet;
import keretrendszer.beadando.model.Dinosaur;
import keretrendszer.beadando.service.DinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Collection;

@Controller("/ctr")
public class DinoController {

    @Autowired
    @Qualifier("dinoService")
    DinoService service;

    @ModelAttribute(value = "dino")
    public Dinosaur create(){
        return new Dinosaur();
    }

    @GetMapping(value = "/dinos")
    public String getAllDino(Model model){
        model.addAttribute("dinos", service.getAllDino());
        return "dinolist.jsp";
    }

    @GetMapping(value = "/dino/{id}")
    public String getDinoById(@PathVariable String id, Model model) throws DinoNotFound {
        model.addAttribute("dino", service.getDinoById(id));
        return "dinodetails.jsp";
    }

    @GetMapping(value = "/addDino")
    public String addDinoForm(Model model){
        model.addAttribute("diets", Diet.values());
        return "dinoForm.jsp";
    }

    @PostMapping(value = "/addDino")
    public String addDino(@ModelAttribute("dino") Dinosaur dino, Model model) throws DinoAlreadyInSystem, WrongRegisterDate {
        dino.setRegistered(LocalDate.now());
        service.addDino(dino);
        return "redirect:dino/"+dino.getId();
    }

    @GetMapping(value = "/update/{id}")
    public ModelAndView updateDino(@ModelAttribute("dino") Dinosaur dino) throws DinoNotFound {
            service.updateDino(dino);


        ModelAndView mav = new ModelAndView("dinolist.jsp");
        System.out.println(service.getAllDino());
        mav.addObject("dinos", service.getAllDino());
        return mav;
    }

    @GetMapping(value = "/edit/{id}")
    public String updateDinoForm(@PathVariable String id, Model model) throws DinoNotFound {
        model.addAttribute("dino", service.getDinoById(id));
        return "dinoupdate.jsp";
    }

    @DeleteMapping(value = "delete/{id}")
    public ModelAndView deleteDino(@ModelAttribute("dino") Dinosaur dino) throws DinoNotFound {
        service.removeDino(dino);
        ModelAndView mav = new ModelAndView("dinolist.jsp");
        System.out.println(service.getAllDino());
        mav.addObject("dinos", service.getAllDino());
        return mav;
    }
}
