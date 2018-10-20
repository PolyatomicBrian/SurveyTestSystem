package edu.drexel.brj33.cs350.service;

import java.io.Serializable;

public interface SerializingInterface extends Serializable {

    public final String fileExtension;

    public String getFileName();

}
