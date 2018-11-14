package edu.drexel.brj33.cs350.response;

import java.io.Serializable;
import java.util.Objects;

public class Response implements Serializable {

    private String resp;

    public Response(String resp){
        this.resp = resp;
    }

    public String getResponse(){
        return resp;
    }

    /**
     * Used to compare a Response to another Response.
     * Specifically, will be used to compare a correct answer to a user's response.
     * This needed to be overridden because getNode() (which is used by containsKey, which
     * is used by contains, which is used by containsAll) uses equals(). We need this
     * equals() to work with our Responses.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Response) {
            Response respObj = (Response) obj;
            return this.resp.equalsIgnoreCase(respObj.resp);
        }else{
            return false;
        }
    }

    /**
     * Overridden so every Response object is different based on their string.
     * This way, we can put different Responses into our set (since sets do not
     * allow for duplicates). Without this method, only one Response-typed object
     * could be stored in the set, and everything else would be considered a duplicate.
     * HashSets check the hashcode of each object to determine what is unique.
     *
     * @return a unique ID pertaining to the specific Response object, generated
     *         based on their response string.
     */
    @Override
    public int hashCode(){
        return Objects.hash(this.toString().toLowerCase());
    }

    public String toString(){
        return this.resp;
    }
}
