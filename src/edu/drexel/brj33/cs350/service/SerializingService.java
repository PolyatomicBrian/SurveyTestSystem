package edu.drexel.brj33.cs350.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializingService <T>{

    public SerializingService(){

    }

    public void serialize(String fileName, T object) throws IOException {
        FileOutputStream fo = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fo);
        objectOutputStream.writeObject(object);
    }

    public List<String> availableFiles(String fileExtension){
        List<String> listFiles = new ArrayList<>();
        File[] filesInPath = new File(".").listFiles();
        for (File f : filesInPath) {
            if (f.isFile() && f.getName().endsWith(fileExtension)) {
                listFiles.add(f.getName());
            }
        }
        return listFiles;
    }

    public T deserialize(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fi);
        return (T) objectInputStream.readObject();
    }
}
