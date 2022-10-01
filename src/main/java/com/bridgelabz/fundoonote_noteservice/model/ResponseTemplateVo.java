package com.bridgelabz.fundoonote_noteservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVo {
    private UserModel user;
    private  NoteModel note;
    private LabelModel label;
}
