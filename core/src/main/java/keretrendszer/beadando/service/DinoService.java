package keretrendszer.beadando.service;

import keretrendszer.beadando.exceptions.DinoAlreadyInSystem;
import keretrendszer.beadando.exceptions.DinoNotFound;
import keretrendszer.beadando.model.Diet;
import keretrendszer.beadando.model.Dinosaur;

import java.util.Collection;
import java.util.Map;

public interface DinoService {
    void addDino(Dinosaur dino) throws DinoAlreadyInSystem;
    Collection<Dinosaur> getAllDino();
    Collection<Dinosaur> getAllDinoByDiet(Diet diet);
    Dinosaur getDinoById(String id) throws DinoNotFound;
    void updateDino(Dinosaur dino) throws DinoNotFound;
    void removeDino(Dinosaur dino) throws DinoNotFound;
    double avgWeight();
    Map<Diet, Double> avgWeightByDiet(Diet diet);

}
