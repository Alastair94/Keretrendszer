package keretrendszer.beadando.dao;

import keretrendszer.beadando.exceptions.DinoAlreadyInSystem;
import keretrendszer.beadando.exceptions.DinoNotFound;
import keretrendszer.beadando.model.Diet;
import keretrendszer.beadando.model.Dinosaur;

import java.util.Collection;

public interface DinoDAO {
    void createDino(Dinosaur dino) throws DinoAlreadyInSystem;
    Collection<Dinosaur> readAllDino();
    Collection<Dinosaur> readAllDinoOfDiet(Diet diet);
    Dinosaur readDino(String id) throws DinoNotFound;
    void updateDino(Dinosaur dino) throws DinoNotFound;
    void deleteDino(Dinosaur dino) throws DinoNotFound;

}
