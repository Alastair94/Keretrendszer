package keretrendszer.beadando.model;

import keretrendszer.beadando.exceptions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Dinosaur {
    private String name;
    private String id;
    private LocalDate registered;
    private Diet diet;
    private double length;
    private double height;
    private int weight;

    public Dinosaur() {
        this.id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws MustHaveName {
        if(name.trim().length() == 0)
            throw new MustHaveName("It must have a name!");
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDate registered) throws WrongRegisterDate {
        if(registered.isAfter(LocalDate.now()))
            throw new WrongRegisterDate("Could not have registered in future!");
        this.registered = LocalDate.now();
    }

    public Diet getDiet() {
        return diet;
    }

    public void setDiet(Diet diet) {
        this.diet = diet;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) throws NotZeroLength {
        if(length < 0)
            throw new NotZeroLength("Length must be bigger than 0!" );
        this.length = length;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) throws NotZeroHeight {
        if(height < 0)
            throw new NotZeroHeight("Height must be bigger than 0!" );
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) throws NotZeroWeight {
        if(weight < 0)
            throw new NotZeroWeight("Weight must be bigger than 0!" );
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Dinosaur{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", registered=" + registered +
                ", diet=" + diet +
                ", length=" + length + "m" +
                ", height=" + height + "m" +
                ", weight=" + weight + "kg" +
                '}';
    }
}
