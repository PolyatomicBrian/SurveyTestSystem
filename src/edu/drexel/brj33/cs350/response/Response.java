package edu.drexel.brj33.cs350.response;

import java.io.Serializable;

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
     * Specifically, used to compare a correct answer to a user's response.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Response) {
            Response respObj = (Response) obj;
            return this.resp.equals(respObj.resp);
        }else{
            return false;
        }
    }

    public String toString(){
        return this.resp;
    }
}
