package com.bridgelabz.fundoonote_noteservice.model;

import com.bridgelabz.fundoonote_noteservice.dto.NoteDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class NoteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long noteId;
    private String title;
    private String description;
    private long userId;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
    private boolean trash;
    private boolean isArchieve;
    private Long labelId;
    private String emailId;
    private String color;


    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "notes")
    private List<LabelModel> labellist;

    public NoteModel(NoteDTO noteDTO) {
        this.title = noteDTO.getTitle();
        this.description = noteDTO.getDescription();
        this.trash = noteDTO.isTrash();
        this.isArchieve = noteDTO.isArchieve();
        this.labelId = noteDTO.getLabelId();
        this.emailId = noteDTO.getEmailId();
        this.color = noteDTO.getColor();
    }
}
