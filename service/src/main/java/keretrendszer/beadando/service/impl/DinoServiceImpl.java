package keretrendszer.beadando.service.impl;

import keretrendszer.beadando.dao.DinoDAO;
import keretrendszer.beadando.exceptions.DinoAlreadyInSystem;
import keretrendszer.beadando.exceptions.DinoNotFound;
import keretrendszer.beadando.model.Diet;
import keretrendszer.beadando.model.Dinosaur;
import keretrendszer.beadando.service.DinoService;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class DinoServiceImpl implements DinoService {
    private DinoDAO dinoDAO;

    public DinoServiceImpl(DinoDAO dinoDAO) {
        this.dinoDAO = dinoDAO;
    }

    public void addDino(Dinosaur dino) throws DinoAlreadyInSystem {
        dinoDAO.createDino(dino);
    }

    public Collection<Dinosaur> getAllDino() {
        return dinoDAO.readAllDino();
    }

    public Collection<Dinosaur> getAllDinoByDiet(Diet diet) {
        Collection<Dinosaur> dinosaurs = getAllDino();
        Collection<Dinosaur> result = dinosaurs.stream().filter(d -> d.getDiet() == diet).collect(Collectors.toList());
        return result;
    }

    public Dinosaur getDinoById(String id) throws DinoNotFound {
        return dinoDAO.readDino(id);
    }

    public void updateDino(Dinosaur dino) throws DinoNotFound {
        dinoDAO.updateDino(dino);
    }

    public void removeDino(Dinosaur dino) throws DinoNotFound {
        dinoDAO.deleteDino(dino);
    }

    public double avgWeight() {
        Collection<Dinosaur> dinosaurs = getAllDino();
        double sum = 0;
        for (Dinosaur d : dinosaurs){
            sum += d.getWeight();
        }
        return sum / dinosaurs.size();
    }

    public Map<Diet, Double> avgWeightByDiet(Diet diet) {
        return null;
    }

    public Collection<Dinosaur> getAllDinoMinWeight(int min) {
        return getAllDino().stream().filter(d -> d.getWeight() >= min).collect(Collectors.<Dinosaur>toList());
    }
}
