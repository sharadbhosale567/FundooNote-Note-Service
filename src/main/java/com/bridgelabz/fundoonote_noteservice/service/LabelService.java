package com.bridgelabz.fundoonote_noteservice.service;

import com.bridgelabz.fundoonote_noteservice.dto.LabelDTO;
import com.bridgelabz.fundoonote_noteservice.exception.NotesNotFoundException;
import com.bridgelabz.fundoonote_noteservice.model.LabelModel;
import com.bridgelabz.fundoonote_noteservice.model.UserModel;
import com.bridgelabz.fundoonote_noteservice.repository.LabelRepository;
import com.bridgelabz.fundoonote_noteservice.util.MailService;
import com.bridgelabz.fundoonote_noteservice.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LabelService implements ILabelService {

    @Autowired
    LabelRepository labelRepository;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    MailService mailService;

    @Autowired
    RestTemplate restTemplate;

    /**
     * Purpose:create label
     */

    @Override
    public LabelModel createLabel(LabelDTO labelDTO, String token) {

        UserModel isUserPresent = restTemplate.getForObject("http://http://USER-SERVICE/getUser/" + token, UserModel.class);
        if (isUserPresent != null ) {
            Long decode = tokenUtil.decodeToken(token);
            LabelModel model = new LabelModel(labelDTO);
            model.setUserId(decode);
           // model.setNoteId(model.getNoteId());
            model.setRegisterDate(LocalDateTime.now());
            labelRepository.save(model);
            return model;
        }
        throw new NotesNotFoundException(400, "Token Invalid");
    }

    /**
     * Purpose:update label
     */

    @Override
    public LabelModel updateLabel(LabelDTO labelDTO, Long labelId, String token) {
        UserModel isUserPresent = restTemplate.getForObject("http://http://USER-SERVICE/getUser/" + token, UserModel.class);
        if (isUserPresent != null ) {
            Optional<LabelModel> isLabelPresent = labelRepository.findById(labelId);
            if(isLabelPresent.isPresent()) {
                isLabelPresent.get().setLabelName(labelDTO.getLabelName());
                isLabelPresent.get().setUpdateDate(LocalDateTime.now());
                labelRepository.save(isLabelPresent.get());
                return isLabelPresent.get();
            }
            throw new NotesNotFoundException(400, "Label not present");
        }
        throw new NotesNotFoundException(400, "Token Invalid");
    }

    /**
     * Purpose:get all labels
     */

    @Override
    public List<LabelModel> getAllLabels(String token) {
        UserModel isUserPresent = restTemplate.getForObject("http://http://USER-SERVICE/getUser/" + token, UserModel.class);
        if (isUserPresent != null) {
            List<LabelModel> getAllLabels = labelRepository.findAll();
            if(getAllLabels.size()>0) {
                return getAllLabels;
            }
            throw new NotesNotFoundException(400, "Label not present");
        }
        throw new NotesNotFoundException(400, "Token Invalid");
    }

    /**
     * Purpose:delete label
     */

    @Override
    public LabelModel deleteLabel(Long labelId, String token) {
        UserModel isUserPresent = restTemplate.getForObject("http://http://USER-SERVICE/getUser/" + token, UserModel.class);
        if (isUserPresent != null ) {
            Optional<LabelModel> isLabelPresent = labelRepository.findById(labelId);
            if(isLabelPresent.isPresent()) {
                labelRepository.delete(isLabelPresent.get());
                return isLabelPresent.get();
            }
            throw new NotesNotFoundException(400, "Label not found");
        }
        throw new NotesNotFoundException(400, "Token not found");
    }
}
