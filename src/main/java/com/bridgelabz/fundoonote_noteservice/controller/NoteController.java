package com.bridgelabz.fundoonote_noteservice.controller;

import com.bridgelabz.fundoonote_noteservice.dto.NoteDTO;
import com.bridgelabz.fundoonote_noteservice.dto.ResponseDTO;
import com.bridgelabz.fundoonote_noteservice.model.NoteModel;
import com.bridgelabz.fundoonote_noteservice.service.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class NoteController {
    @Autowired
    INoteService notesService;


    /**
     * Purpose:create notes
     */

    @PostMapping("/createNotes/{token}")
    public ResponseEntity<ResponseDTO> createNotes(@RequestBody NoteDTO noteDTO, @PathVariable String token) {
            NoteModel noteModel = notesService.createNotes(noteDTO, token);
            ResponseDTO responseDTO = new ResponseDTO(200, "Notes created successfully", noteModel);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Purpose:update notes
     */

    @PutMapping("/updateNotes/{noteId}/{token}")
    public ResponseEntity<ResponseDTO> updateNotes(@PathVariable Long noteId, @RequestBody NoteDTO noteDTO, @PathVariable String token) {
        NoteModel noteModel = notesService.updateNotes(noteId, noteDTO, token);
        ResponseDTO responseDTO = new ResponseDTO(200, "Notes updated successfully", noteModel);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Purpose:read all notes
     */

    @GetMapping("/readAllNotes/{token}")
    public ResponseEntity<ResponseDTO> readAllNotes(@PathVariable String token) {
        List<NoteModel> noteModel = notesService.readAllNotes(token);
        ResponseDTO responseDTO = new ResponseDTO(200, "Fetching all notes successfully", noteModel);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Purpose:read notes by id
     */

    @GetMapping("/readNotesById/{noteId}/{token}")
    public ResponseEntity<ResponseDTO> readNotesById(@PathVariable Long noteId, @PathVariable String token){
        Optional<NoteModel> notesModel = notesService.readNotesById(noteId, token);
        ResponseDTO responseDTO = new ResponseDTO(200, "Fetching notes by id successfully", notesModel);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Purpose:archeive note by id
     */

    @PutMapping("/archeiveNote/{noteId}/{token}")
    public ResponseEntity<ResponseDTO> archeiveNote(@PathVariable Long noteId, @PathVariable String token) {
        NoteModel noteModel = notesService.archeiveNote(noteId, token);
        ResponseDTO responseDTO = new ResponseDTO(200, "Note archeived successfully", noteModel);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Purpose:unarcheive note by id
     */

    @PutMapping("/unArcheiveNote/{noteId}/{token}")
    public ResponseEntity<ResponseDTO> unArcheiveNote(@PathVariable Long noteId, @PathVariable String token) {
        NoteModel noteModel = notesService.unArcheiveNote(noteId, token);
        ResponseDTO responseDTO = new ResponseDTO(200, "Note unarcheived successfully", noteModel);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Purpose:trash note by id
     */

    @PutMapping("/trashNote/{noteId}/{token}")
    public ResponseEntity<ResponseDTO> trashNote(@PathVariable Long noteId, @PathVariable String token) {
        NoteModel noteModel = notesService.trashNote(noteId, token);
        ResponseDTO responseDTO = new ResponseDTO(200, "Note trashed successfully", noteModel);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Purpose:restore note by id
     */

    @PutMapping("/restoreNote/{noteId}/{token}")
    public ResponseEntity<ResponseDTO> restoreNote(@PathVariable Long noteId, @PathVariable String token) {
        NoteModel noteModel = notesService.restoreNote(noteId, token);
        ResponseDTO responseDTO = new ResponseDTO(200, "Note restored successfully", noteModel);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Purpose:delete note by id
     */

    @DeleteMapping("/deleteNote/{noteId}/{token}")
    public ResponseEntity<ResponseDTO> deleteNote(@PathVariable Long noteId, @PathVariable String token) {
        ResponseDTO notesModel = notesService.deleteNote(noteId, token);
        ResponseDTO responseDTO = new ResponseDTO(200, "Note deleted successfully", notesModel);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Purpose:change note colour
     */

    @PutMapping("/changeNoteColour/{noteId}/{token}")
    public ResponseEntity<ResponseDTO> changeNoteColour(@PathVariable Long noteId, @RequestParam String colour, @PathVariable String token) {
        NoteModel noteModel = notesService.changeNoteColour(noteId, colour, token);
        ResponseDTO responseDTO = new ResponseDTO(200, "Note colour changed successfully", noteModel);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
