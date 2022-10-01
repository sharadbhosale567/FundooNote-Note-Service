package com.bridgelabz.fundoonote_noteservice.service;

import com.bridgelabz.fundoonote_noteservice.dto.LabelDTO;
import com.bridgelabz.fundoonote_noteservice.model.LabelModel;

import java.util.List;

public interface ILabelService {

    LabelModel createLabel(LabelDTO labelDTO, String token);

    LabelModel updateLabel(LabelDTO labelDTO, Long labelId, String token);

    List<LabelModel> getAllLabels(String token);

    LabelModel deleteLabel(Long labelId, String token);
}
