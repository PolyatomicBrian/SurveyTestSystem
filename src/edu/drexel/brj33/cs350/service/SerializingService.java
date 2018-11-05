package edu.drexel.brj33.cs350.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializingService <T>{

    public SerializingService(){

    }

    public void serialize(String fileName, T object) throws IOException {
        // Write object to file output stream.
        // "T" type is used to write a generic type, such as Survey or Test.
        FileOutputStream fo = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fo);
        objectOutputStream.writeObject(object);
    }

    public List<String> availableFiles(String fileExtension){
        // List all files in current directory ending with specified file extension.
        List<String> listFiles = new ArrayList<>();
        File[] filesInPath = new File(".").listFiles();
        // Iterate through all files in path
        for (File f : filesInPath) {
            // Only list those with specified extension.
            if (f.isFile() && f.getName().endsWith(fileExtension)) {
                listFiles.add(f.getName());
            }
        }
        return listFiles;
    }

    public T deserialize(String fileName) throws IOException, ClassNotFoundException {
        // Read object from file input stream.
        // "T" type is used to read generic object, such as Test or Survey.
        FileInputStream fi = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fi);
        return (T) objectInputStream.readObject();
    }
}
