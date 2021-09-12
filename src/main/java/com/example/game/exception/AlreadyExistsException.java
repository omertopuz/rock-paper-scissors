package com.example.game.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Entity already exists, try another input.")
public class AlreadyExistsException extends Exception{

    public AlreadyExistsException(String message)
    {
        super(message);
    }

}
