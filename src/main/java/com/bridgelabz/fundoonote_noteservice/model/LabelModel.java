package com.bridgelabz.fundoonote_noteservice.model;

import com.bridgelabz.fundoonote_noteservice.dto.LabelDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class LabelModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long labelId;
    @Column(name="labelName")
    private String labelName;
    @Column(name="userId")
    private Long userId;
    @Column(name="noteId")
    private Long noteId;
    @Column(name = "registeredDate")
    private LocalDateTime registerDate;
    @Column(name = "UpdatedDate")
    private LocalDateTime updateDate;

    @JsonIgnore
    @ManyToMany
    private List<NoteModel> notes;

    public LabelModel(LabelDTO labelDTO) {
        this.labelName = labelDTO.getLabelName();
    }
}
