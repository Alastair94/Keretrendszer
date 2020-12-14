package keretrendszer.beadando.controller;

import keretrendszer.beadando.exceptions.DinoAlreadyInSystem;
import keretrendszer.beadando.exceptions.DinoNotFound;
import keretrendszer.beadando.exceptions.WrongRegisterDate;
import keretrendszer.beadando.model.Diet;
import keretrendszer.beadando.model.Dinosaur;
import keretrendszer.beadando.service.DinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Collection;

@RestController
public class RESTDinoController {

    @Autowired
    DinoService dinoService;

    @GetMapping(value = "dino/{id:[A-Za-z0-9]{8}-[A-Za-z0-9]{4}-[A-Za-z0-9]{4}-[A-Za-z0-9]{4}-[A-Za-z0-9]{12}}")
    public ModelAndView getDino(@PathVariable(name = "id") String id) throws DinoNotFound {
        ModelAndView mav = new ModelAndView("dinodetails.jsp");
        mav.addObject("dino", dinoService.getDinoById(id));
        return mav;
    }
    @ExceptionHandler(DinoNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String unknownDinoID(DinoNotFound e){
        return "There is no dinosaur in the database with the following ID: " + e.getMessage();
    }


    /*@GetMapping(value = "dinos")
    public Collection<Dinosaur> getAllDinoOfDiet(@RequestParam(value = "diet", required = false) Diet diet, @RequestParam(value = "minWeight", required = false) Integer weight) {
        //Collection<Dinosaur> byWeight = weight != null ? dinoService.getAllDinoMinWeight(weight.intValue()) : dinoService.getAllDino();
        Collection<Dinosaur> byDiet = diet != null ? dinoService.getAllDinoByDiet(diet) : dinoService.getAllDino();
        //return byWeight.stream().filter(d -> byDiet.contains(d)).collect(Collectors.toList());
        return byDiet;
    }*/

    @ModelAttribute(value = "dino")
    public Dinosaur create(){
        return new Dinosaur();
    }

    @GetMapping(value = "dinos")
    public ModelAndView getAllDino(Model model, @RequestParam(value = "diet", required = false) Diet diet){
        ModelAndView mav = new ModelAndView("dinolist.jsp");
        Collection<Dinosaur> byDiet = diet != null ? dinoService.getAllDinoByDiet(diet) : dinoService.getAllDino();
        mav.addObject("dinos", byDiet);
        return mav;
    }

    @GetMapping(value ="addDino")
    public ModelAndView addDinoForm(){
        ModelAndView mav = new ModelAndView("dinoForm.jsp");
        mav.addObject("diets", Diet.values());
        return mav;
    }

    @PostMapping(value = "addDino")
    public ModelAndView addDino(@ModelAttribute("dino") Dinosaur dino) throws DinoAlreadyInSystem, WrongRegisterDate {
        ModelAndView mav = new ModelAndView("dinodetails.jsp");
        dino.setRegistered(LocalDate.now());
        dinoService.addDino(dino);
        return mav;
    }

    @PostMapping(value = "addDinoJSON", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json;charset=utf-8")
    public String addDinoJSON(@RequestBody Dinosaur dino) throws DinoAlreadyInSystem, WrongRegisterDate {
        dino.setRegistered(LocalDate.now());
        dinoService.addDino(dino);
        return "New dino added to database: " + dino.getId();
    }

    @ExceptionHandler(DinoAlreadyInSystem.class)
    @ResponseStatus(HttpStatus.IM_USED)
    public String usedDinoID(DinoAlreadyInSystem e){
        return "There is already a dinosaur in the database with the following ID: " + e.getMessage();
    }
}
