package edu.drexel.brj33.cs350.service;

import java.io.*;
import java.util.List;

public class SerializingService <T extends SerializingInterface> {

    public SerializingService(){

    }

    public void serialize(String fileName, T object) throws IOException {
        FileOutputStream fo = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fo);
        objectOutputStream.writeObject(object);
    }

    public List<String> availableFiles(){
        return null;
    }

    public T deserialize(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fi);
        return (T) objectInputStream.readObject();
    }
}
