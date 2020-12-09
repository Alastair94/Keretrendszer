package keretrendszer.beadando.dao;

import keretrendszer.beadando.model.Diet;
import keretrendszer.beadando.model.Dinosaur;

import java.util.Collection;

public interface DinoDAO {
    void createDino(Dinosaur dino);
    Collection<Dinosaur> readAllDino();
    Collection<Dinosaur> readAllDinoOfDiet(Diet diet);
    Dinosaur readDino(String id);
    void updateDino(Dinosaur dino);
    void deleteDino(Dinosaur dino);

}
