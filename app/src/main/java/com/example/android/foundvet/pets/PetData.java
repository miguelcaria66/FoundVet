package com.example.android.foundvet.pets;

import java.net.URL;
import java.security.Timestamp;

public class PetData {

    String petName;
    String petSpecies;
    /*Para efeitos de teste fica este atributo*/
    String petAge;
    String petBreed;
    String petGender;
    Boolean petSterilized;
    String petWeight;
    Timestamp petBirthday;
    /*PIIIIICCCCCC
    Url petPhoto;*/
    String ownerID;


    /*Para efeitos de teste fica este construtor*/
    public PetData(String name, String species, String age) {
        this.petName=name;
        this.petSpecies=species;
        this.petAge=age;
    }


    public String getPetName() {
        return petName;
    }

    public String getPetSpecies() {
        return petSpecies;
    }

    public String getPetAge() {
        return petAge;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public String getPetGender() {
        return petGender;
    }

    public Boolean getPetSterilized() {
        return petSterilized;
    }

    public String getPetWeight() {
        return petWeight;
    }

    public Timestamp getPetBirthday() {
        return petBirthday;
    }

    public String getOwnerID() {
        return ownerID;
    }

}