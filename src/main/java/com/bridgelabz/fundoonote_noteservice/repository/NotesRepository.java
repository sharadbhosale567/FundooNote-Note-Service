package com.bridgelabz.fundoonote_noteservice.repository;

import com.bridgelabz.fundoonote_noteservice.model.NoteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotesRepository extends JpaRepository<NoteModel,Long> {

    List<NoteModel> findByUserId(Long userId);

    Optional<NoteModel> findByUserIdAndNoteId(Long userId, Long noteId);
}
