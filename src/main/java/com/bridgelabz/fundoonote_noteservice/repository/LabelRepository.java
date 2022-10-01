package com.bridgelabz.fundoonote_noteservice.repository;

import com.bridgelabz.fundoonote_noteservice.model.LabelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends JpaRepository<LabelModel,Long> {
}
