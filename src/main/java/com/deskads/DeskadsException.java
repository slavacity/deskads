package com.deskads;

/**
 * Created by Administrator on 09.11.2017.
 */
public class DeskadsException extends RuntimeException {

    public DeskadsException(String message){
        super(message);
    }

    public DeskadsException(String message, Throwable cause){
        super(message, cause);
    }

    public DeskadsException(Throwable cause){
        super(cause);
    }
}
