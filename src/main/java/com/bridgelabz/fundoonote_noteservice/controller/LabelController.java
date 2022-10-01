package com.bridgelabz.fundoonote_noteservice.controller;

import com.bridgelabz.fundoonote_noteservice.dto.LabelDTO;
import com.bridgelabz.fundoonote_noteservice.dto.ResponseDTO;
import com.bridgelabz.fundoonote_noteservice.model.LabelModel;
import com.bridgelabz.fundoonote_noteservice.service.ILabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LabelController {

    @Autowired
    ILabelService labelService;

    /**
     * Purpose:create label
     */

    @PostMapping("/createLabel/{token}")
    public ResponseEntity<ResponseDTO> createLabel(@RequestBody LabelDTO labelDTO, @PathVariable String token) {
        LabelModel labelModel = labelService.createLabel(labelDTO, token);
        ResponseDTO responseDTO = new ResponseDTO(200, "Label created successfully", labelModel);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Purpose:update label
     */

    @PutMapping("/updateLabel/{id}/{token}")
    public ResponseEntity<ResponseDTO> updateLabel(@RequestBody LabelDTO labelDTO, @PathVariable Long labelId, @PathVariable String token) {
        LabelModel labelModel = labelService.updateLabel(labelDTO, labelId, token);
        ResponseDTO responseDTO = new ResponseDTO(200, "Label updated successfully", labelModel);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Purpose:get all label
     */

    @GetMapping("/getAllLabels/{token}")
    public ResponseEntity<ResponseDTO> getAllLabels(@PathVariable String token) {
        List<LabelModel> labelModel = labelService.getAllLabels(token);
        ResponseDTO responseDTO = new ResponseDTO(200, "Fetching all labels successfully", labelModel);
        return new ResponseEntity<>(responseDTO , HttpStatus.OK);
    }

    /**
     * Purpose:delete label
     */

    @DeleteMapping("/deleteLabels/{token}")
    public ResponseEntity<ResponseDTO> deleteLabel(@PathVariable Long labelId, @PathVariable String token) {
        LabelModel labelModel = labelService.deleteLabel(labelId, token);
        ResponseDTO responseDTO = new ResponseDTO(200, "label deleted successfully", labelModel);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
