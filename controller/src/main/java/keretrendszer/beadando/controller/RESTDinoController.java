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
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/rest/")
public class RESTDinoController {

    @Autowired
    DinoService dinoService;

    @GetMapping(value = "dino/{id:[A-Za-z0-9]{8}-[A-Za-z0-9]{4}-[A-Za-z0-9]{4}-[A-Za-z0-9]{4}-[A-Za-z0-9]{12}}")
    public Dinosaur getDino(@PathVariable(name = "id") String id) throws DinoNotFound {
        return dinoService.getDinoById(id);
    }

    @ExceptionHandler(DinoNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String unknownDinoID(DinoNotFound e){
        return "There is no dinosaur in the database with the following ID: " + e.getMessage();
    }

    @GetMapping(value = "dinos")
    public Collection<Dinosaur> getAllDinoOfDiet(@RequestParam(value = "diet", required = false) Diet diet) {
        //Collection<Dinosaur> byWeight = weight != null ? dinoService.getAllDinoMinWeight(weight.intValue()) : dinoService.getAllDino();
        Collection<Dinosaur> byDiet = diet != null ? dinoService.getAllDinoByDiet(diet) : dinoService.getAllDino();
        //return byWeight.stream().filter(d -> byDiet.contains(d)).collect(Collectors.toList());
        return byDiet;
    }

    @PostMapping(value = "dinos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json;charset=utf-8")
    public String addDino(@RequestBody Dinosaur dino) throws DinoAlreadyInSystem, WrongRegisterDate {
        dino.setRegistered(LocalDate.now());
        dinoService.addDino(dino);
        return "New dinosaur has been added with the following ID: " + dino.getId();
    }

    @ExceptionHandler(DinoAlreadyInSystem.class)
    @ResponseStatus(HttpStatus.IM_USED)
    public String usedDinoID(DinoAlreadyInSystem e){
        return "There is already a dinosaur in the database with the following ID: " + e.getMessage();
    }

    @DeleteMapping(value = "/delete/{id}")
    public String removeFilm(@PathVariable String id) throws DinoNotFound {
        Dinosaur dino = dinoService.getDinoById(id);
        dinoService.removeDino(dinoService.getDinoById(id));
        return "Dinosaur succesfully removed! (" + dino.getName() + ")";
    }
}
