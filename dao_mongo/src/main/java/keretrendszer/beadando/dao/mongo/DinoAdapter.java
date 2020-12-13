package keretrendszer.beadando.dao.mongo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import keretrendszer.beadando.model.Dinosaur;

import java.io.IOException;

public class DinoAdapter {
    private static ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public static Dinosaur dbObjectToDino(DBObject dino){
        try {
            Dinosaur obj = mapper.readValue(dino.toString(), Dinosaur.class);
            return obj;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DBObject dinoToDBObject(Dinosaur dino){
        String dinoString = "";
        try {
            dinoString = mapper.writeValueAsString(dino);
            BasicDBObject obj = mapper.readValue(dinoString, BasicDBObject.class);
            return obj;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new BasicDBObject();
    }
}
