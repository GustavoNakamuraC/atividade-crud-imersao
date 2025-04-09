package com.atividade.CrudRpg.service.exception;

public class PersonagemInvalidoException extends RuntimeException{
    public PersonagemInvalidoException(String message){
        super(message);
    }
}
