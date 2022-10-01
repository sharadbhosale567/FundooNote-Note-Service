package com.bridgelabz.fundoonote_noteservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDTO {
//    @Pattern(regexp = "^[a-zA-Z\\s]{2,}$", message = "Title name is Invalid")
    private String title;
//    @Pattern(regexp = "^[a-zA-Z\\s]{2,}$", message = "Description is Invalid")
    private String description;
    private boolean trash;
    private boolean isArchieve;
    private boolean pin;
    private Long labelId;
//    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "email is invalid")
    private String emailId;
//    @Pattern(regexp = "^[a-zA-Z\\s]{2,}$", message = "Colour is Invalid")
    private String color;
}
