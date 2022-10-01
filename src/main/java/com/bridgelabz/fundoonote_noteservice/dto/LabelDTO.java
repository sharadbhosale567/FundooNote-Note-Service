package com.bridgelabz.fundoonote_noteservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabelDTO {
//    @Pattern(regexp = "^[a-zA-Z\\s]{2,}$", message = "Label name is Invalid")
    private String labelName;
    private Long noteId;
}
