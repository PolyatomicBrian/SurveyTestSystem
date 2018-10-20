package edu.drexel.brj33.cs350.service;

import java.io.Serializable;
import java.util.List;

public class SerializingService <T extends SerializingInterface> {

    public SerializingService(){

    }

    public void serialize(String fileName, T object){

    }

    public List<String> availableFiles(){
        System.out.println(T.fileExtension);
        return null;
    }

    public T deserialize(String fileName){
        return null;
    }
}
