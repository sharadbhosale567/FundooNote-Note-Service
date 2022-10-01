package com.bridgelabz.fundoonote_noteservice.service;

import com.bridgelabz.fundoonote_noteservice.dto.NoteDTO;
import com.bridgelabz.fundoonote_noteservice.dto.ResponseDTO;
import com.bridgelabz.fundoonote_noteservice.exception.NotesNotFoundException;
import com.bridgelabz.fundoonote_noteservice.model.NoteModel;
import com.bridgelabz.fundoonote_noteservice.model.UserModel;
import com.bridgelabz.fundoonote_noteservice.repository.LabelRepository;
import com.bridgelabz.fundoonote_noteservice.repository.NotesRepository;
import com.bridgelabz.fundoonote_noteservice.util.MailService;
import com.bridgelabz.fundoonote_noteservice.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService implements INoteService {
    @Autowired
    NotesRepository notesRepository;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    MailService mailService;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LabelRepository labelRepository;

    /**
     * Purpose:To create notes
     */

    @Override
    public NoteModel createNotes(NoteDTO noteDTO, String token) {

        UserModel isUserPresent = restTemplate.getForObject("http://USER-SERVICE/getUser/" + token, UserModel.class);
        if (isUserPresent != null ) {
            Long decode = tokenUtil.decodeToken(token);
            NoteModel model = new NoteModel(noteDTO);
            model.setUserId(decode);
            model.setRegisterDate(LocalDateTime.now());
            notesRepository.save(model);
            return model;
        }
        throw new NotesNotFoundException(400, "Token Invalid");
    }

    /**
     * Purpose:update notes
     */

    @Override
    public NoteModel updateNotes(Long noteId, NoteDTO noteDTO, String token) {
        UserModel isUserPresent = restTemplate.getForObject("http://http://USER-SERVICE/getUser/" + token, UserModel.class);
        if (isUserPresent != null ) {
            Long userId = tokenUtil.decodeToken(token);
            Optional<NoteModel> isUserAndNoteIdPresent = notesRepository.findByUserIdAndNoteId(userId, noteId);
            if(isUserAndNoteIdPresent.isPresent()) {
                isUserAndNoteIdPresent.get().setTitle(noteDTO.getTitle());
                isUserAndNoteIdPresent.get().setDescription(noteDTO.getDescription());
                isUserAndNoteIdPresent.get().setUserId(userId);
                isUserAndNoteIdPresent.get().setLabelId(noteDTO.getLabelId());
                isUserAndNoteIdPresent.get().setEmailId(noteDTO.getEmailId());
                isUserAndNoteIdPresent.get().setColor(noteDTO.getColor());
                isUserAndNoteIdPresent.get().setUpdateDate(LocalDateTime.now());
                notesRepository.save(isUserAndNoteIdPresent.get());
                return isUserAndNoteIdPresent.get();
            }
            throw new NotesNotFoundException(400, "Note Not Found");
        }
        throw new NotesNotFoundException(400, "Token Id Not Found");
    }

    /**
     * Purpose:fetch all notes
     */

    @Override
    public List<NoteModel> readAllNotes(String token) {
        UserModel isUserPresent = restTemplate.getForObject("http://http://USER-SERVICE/getUser/" + token, UserModel.class);
        if (isUserPresent != null ) {
            Long userId = tokenUtil.decodeToken(token);
            List<NoteModel> readAllNotes = notesRepository.findByUserId(userId);
            if(readAllNotes.size()>0) {
                return readAllNotes;
            }
        }
        throw new NotesNotFoundException(400, "Notes not present");
    }

    /**
     * Purpose:fetch notes by id
     */

    @Override
    public Optional<NoteModel> readNotesById(Long noteId, String token) {
        UserModel isUserPresent = restTemplate.getForObject("http://http://USER-SERVICE/getUser/" + token, UserModel.class);
        if (isUserPresent != null ) {
            Long userId = tokenUtil.decodeToken(token);
            Optional<NoteModel> isUserAndNoteIdPresent = notesRepository.findByUserIdAndNoteId(userId, noteId);
            if(isUserAndNoteIdPresent.isPresent()) {
                return isUserAndNoteIdPresent;
            }
            throw new NotesNotFoundException(400, "Notes not present");
        }
        throw new NotesNotFoundException(400, "Token not found");
    }

    /**
     * Purpose:archeive notes
     */

    @Override
    public NoteModel archeiveNote(Long noteId, String token) {
        UserModel isUserPresent = restTemplate.getForObject("http://http://USER-SERVICE/getUser/" + token, UserModel.class);
        if (isUserPresent != null ) {
            Long userId = tokenUtil.decodeToken(token);
            Optional<NoteModel> isUserAndNoteIdPresent = notesRepository.findByUserIdAndNoteId(userId, noteId);
            if(isUserAndNoteIdPresent.isPresent()) {
                isUserAndNoteIdPresent.get().setArchieve(true);
                notesRepository.save(isUserAndNoteIdPresent.get());
                return isUserAndNoteIdPresent.get();
            }
            throw new NotesNotFoundException(400, "Notes not present");
        }
        throw new NotesNotFoundException(400, "Token not found");
    }

    /**
     * Purpose:unarcheive notes
     */

    @Override
    public NoteModel unArcheiveNote(Long noteId, String token) {
        UserModel isUserPresent = restTemplate.getForObject("http://http://USER-SERVICE/getUser/" + token, UserModel.class);
        if (isUserPresent != null ) {
            Long userId = tokenUtil.decodeToken(token);
            Optional<NoteModel> isUserAndNoteIdPresent = notesRepository.findByUserIdAndNoteId(userId, noteId);
            if(isUserAndNoteIdPresent.isPresent()) {
                isUserAndNoteIdPresent.get().setArchieve(false);
                notesRepository.save(isUserAndNoteIdPresent.get());
                return isUserAndNoteIdPresent.get();
            }
            throw new NotesNotFoundException(400, "Notes not present");
        }
        throw new NotesNotFoundException(400, "Token not found");
    }

    /**
     * Purpose:send notes to trash
     */

    @Override
    public NoteModel trashNote(Long noteId, String token) {
        UserModel isUserPresent = restTemplate.getForObject("http://http://USER-SERVICE/getUser/" + token, UserModel.class);
        if (isUserPresent != null ) {
            Long userId = tokenUtil.decodeToken(token);
            Optional<NoteModel> isUserAndNoteIdPresent = notesRepository.findByUserIdAndNoteId(userId, noteId);
            if (isUserAndNoteIdPresent.isPresent()) {
                isUserAndNoteIdPresent.get().setTrash(true);
                notesRepository.save(isUserAndNoteIdPresent.get());
                return isUserAndNoteIdPresent.get();
            }
            throw new NotesNotFoundException(400, "Notes not present");
        }
        throw new NotesNotFoundException(400, "Token not found");
    }

    /**
     * Purpose:restore notes from trash
     */

    @Override
    public NoteModel restoreNote(Long noteId, String token) {
        UserModel isUserPresent = restTemplate.getForObject("http://http://USER-SERVICE/getUser/" + token, UserModel.class);
        if (isUserPresent != null ) {
            Long userId = tokenUtil.decodeToken(token);
            Optional<NoteModel> isUserAndNoteIdPresent = notesRepository.findByUserIdAndNoteId(userId, noteId);
            if (isUserAndNoteIdPresent.isPresent()) {
                isUserAndNoteIdPresent.get().setTrash(false);
                notesRepository.save(isUserAndNoteIdPresent.get());
                return isUserAndNoteIdPresent.get();
            }
            throw new NotesNotFoundException(400, "Notes not present");
        }
        throw new NotesNotFoundException(400, "Token not found");
    }

    /**
     * Purpose:delete notes
     */

    @Override
    public ResponseDTO deleteNote(Long noteId, String token) {
        Long userId = tokenUtil.decodeToken(token);
        Optional<NoteModel> isUserAndNoteIdPresent = notesRepository.findByUserIdAndNoteId(userId, noteId);
        if (isUserAndNoteIdPresent.isPresent()) {
            notesRepository.delete(isUserAndNoteIdPresent.get());
            return new ResponseDTO(200, "Success", isUserAndNoteIdPresent.get());
        }
        throw new NotesNotFoundException(400, "Notes not found");
    }

    /**
     * Purpose:change note color
     */

    @Override
    public NoteModel changeNoteColour(Long noteId, String colour, String token) {
        UserModel isUserPresent = restTemplate.getForObject("http://http://USER-SERVICE/getUser/" + token, UserModel.class);
        if (isUserPresent != null ) {
            Long userId = tokenUtil.decodeToken(token);
            Optional<NoteModel> isUserAndNoteIdPresent = notesRepository.findByUserIdAndNoteId(userId, noteId);
            if (isUserAndNoteIdPresent.isPresent()) {
                isUserAndNoteIdPresent.get().setColor(colour);
                notesRepository.save(isUserAndNoteIdPresent.get());
                return isUserAndNoteIdPresent.get();
            }
            throw new NotesNotFoundException(400, "Notes not present");
        }
        throw new NotesNotFoundException(400, "Token not found");
    }
}
