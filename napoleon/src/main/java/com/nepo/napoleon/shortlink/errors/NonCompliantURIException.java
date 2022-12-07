package com.nepo.napoleon.shortlink.errors;

public class NonCompliantURIException extends Exception{
    public NonCompliantURIException(String errorMessage){
        super(errorMessage);
    }

}
