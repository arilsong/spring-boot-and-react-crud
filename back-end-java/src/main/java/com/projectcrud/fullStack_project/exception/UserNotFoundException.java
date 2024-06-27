package com.projectcrud.fullStack_project.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id){
        super("Could not found thr user with id");
    }
}
