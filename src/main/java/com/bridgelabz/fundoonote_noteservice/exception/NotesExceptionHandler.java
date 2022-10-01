package com.bridgelabz.fundoonote_noteservice.exception;

import com.bridgelabz.fundoonote_noteservice.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class NotesExceptionHandler {

    @ExceptionHandler(NotesNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleId(NotesNotFoundException ab) {
        ResponseDTO response = new ResponseDTO();
        response.setStatus(400);
        response.setMessage(ab.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
