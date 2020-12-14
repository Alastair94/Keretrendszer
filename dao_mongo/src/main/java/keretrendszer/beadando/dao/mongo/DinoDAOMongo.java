package keretrendszer.beadando.dao.mongo;

import com.mongodb.*;
import keretrendszer.beadando.dao.DinoDAO;
import keretrendszer.beadando.exceptions.DinoAlreadyInSystem;
import keretrendszer.beadando.exceptions.DinoNotFound;
import keretrendszer.beadando.model.Diet;
import keretrendszer.beadando.model.Dinosaur;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class DinoDAOMongo implements DinoDAO {
    private MongoClient client;
    private DB db;
    private DBCollection collection;

    public DinoDAOMongo(String uri, String database, String collection) throws UnknownHostException {
        this.client = new MongoClient(new MongoClientURI(uri));
        this.db = client.getDB(database);
        this.collection = db.getCollection(collection);
    }

    @Override
    public void createDino(Dinosaur dino) throws DinoAlreadyInSystem {
        try {
            readDino(dino.getId());
        } catch (DinoNotFound dinoNotFound) {
            collection.insert(DinoAdapter.dinoToDBObject(dino));
            return;
        }
        throw new DinoAlreadyInSystem(dino.getId());

    }

    @Override
    public Collection<Dinosaur> readAllDino() {
        DBCursor cursor = collection.find(new BasicDBObject(), new BasicDBObject().append("_id", 0));
        Collection<Dinosaur> result = new ArrayList<>();
        cursor.forEach(dino -> {
            result.add(DinoAdapter.dbObjectToDino(dino));
        });
        return result;
    }

    @Override
    public Collection<Dinosaur> readAllDinoOfDiet(Diet diet) {
        Collection<Dinosaur> dinosaurs = readAllDino();
        Collection<Dinosaur> result = dinosaurs.stream().filter(d -> d.getDiet() == diet).collect(Collectors.toList());
        return result;
    }

    @Override
    public Dinosaur readDino(String id) throws DinoNotFound {
        DBCursor cursor = collection.find(new BasicDBObject().append("id", id), new BasicDBObject().append("_id", 0));
        if(cursor.length() == 0)
            throw new DinoNotFound(id);
        return DinoAdapter.dbObjectToDino(cursor.one());
    }

    @Override
    public void updateDino(Dinosaur dino) throws DinoNotFound {
        Dinosaur tmp = readDino(dino.getId());
        collection.update(DinoAdapter.dinoToDBObject(tmp), DinoAdapter.dinoToDBObject(dino));
    }

    @Override
    public void deleteDino(Dinosaur dino) throws DinoNotFound {
        //readDino(dino.getId());
        collection.remove(DinoAdapter.dinoToDBObject(dino));
    }
}
