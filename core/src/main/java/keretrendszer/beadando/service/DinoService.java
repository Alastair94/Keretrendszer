package keretrendszer.beadando.service;

import keretrendszer.beadando.model.Diet;
import keretrendszer.beadando.model.Dinosaur;

import java.util.Collection;
import java.util.Map;

public interface DinoService {
    void addDino(Dinosaur dino);
    Collection<Dinosaur> getAllDino();
    Collection<Dinosaur> getAllDinoByDiet(Diet diet);
    Dinosaur getDinoById(String id);
    void updateDino(Dinosaur dino);
    void removeDino(Dinosaur dino);
    double avgWeight();
    Map<Diet, Double> avgWeightByDiet(Diet diet);

}
