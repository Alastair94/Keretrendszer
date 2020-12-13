import keretrendszer.beadando.dao.DinoDAO;
import keretrendszer.beadando.dao.mongo.DinoDAOMongo;
import keretrendszer.beadando.exceptions.*;
import keretrendszer.beadando.model.Diet;
import keretrendszer.beadando.model.Dinosaur;
import keretrendszer.beadando.service.DinoService;
import keretrendszer.beadando.service.impl.DinoServiceImpl;

import java.net.UnknownHostException;
import java.sql.SQLOutput;
import java.time.LocalDateTime;

public class Test {
    public static void main(String[] args) throws UnknownHostException, MustHaveName, NotZeroHeight, NotZeroLength, WrongRegisterDate, NotZeroWeight, DinoNotFound {
        DinoDAO dao = new DinoDAOMongo("mongodb://localhost:27017", "dinosaurs", "dinosaur");
        DinoService service = new DinoServiceImpl(dao);

        Dinosaur dino = new Dinosaur();
        /*dino.setName("Gallimimus");
        dino.setDiet(Diet.HERBIVORE);
        dino.setHeight(2);
        dino.setLength(6);
        dino.setRegistered(LocalDateTime.now());
        dino.setWeight(200);*/

        dino.setName("Velociraptor");
        dino.setDiet(Diet.CARNIVORE);
        dino.setHeight(0.5);
        dino.setLength(2.07);
        dino.setWeight(17);
        dino.setRegistered(LocalDateTime.now());

        try {
            service.addDino(dino);
        } catch (DinoAlreadyInSystem dinoAlreadyInSystem) {
            dinoAlreadyInSystem.printStackTrace();
        }
        System.out.println("Recently added dinosaur: " + service.getDinoById(dino.getId()));
        System.out.println("All dinosaur in system: " + service.getAllDino());
        System.out.println(service.avgWeight());


    }
}
