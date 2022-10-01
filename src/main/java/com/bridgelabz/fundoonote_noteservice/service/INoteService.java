package com.bridgelabz.fundoonote_noteservice.service;

import com.bridgelabz.fundoonote_noteservice.dto.NoteDTO;
import com.bridgelabz.fundoonote_noteservice.dto.ResponseDTO;
import com.bridgelabz.fundoonote_noteservice.model.NoteModel;
import com.bridgelabz.fundoonote_noteservice.model.ResponseTemplateVo;

import java.util.List;
import java.util.Optional;

public interface INoteService {


    NoteModel createNotes(NoteDTO noteDTO, String token);

    NoteModel updateNotes(Long noteId, NoteDTO noteDTO, String token);

    List<NoteModel> readAllNotes(String token);

    Optional<NoteModel> readNotesById(Long noteId, String token);

    NoteModel archeiveNote(Long noteId, String token);

    NoteModel unArcheiveNote(Long noteId, String token);

    NoteModel trashNote(Long noteId, String token);

    NoteModel restoreNote(Long noteId, String token);

    ResponseDTO deleteNote(Long noteId, String token);

    NoteModel changeNoteColour(Long noteId, String colour, String token);

}
