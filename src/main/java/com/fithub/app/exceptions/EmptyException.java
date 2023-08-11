package com.fithub.app.exceptions;

import java.io.Serial;

public class EmptyException extends Exception{

	public EmptyException(String message) {
        super(message);
    }
	
	@Serial
    private static final long serialVersionUID = 7697801918381719637L;

}
