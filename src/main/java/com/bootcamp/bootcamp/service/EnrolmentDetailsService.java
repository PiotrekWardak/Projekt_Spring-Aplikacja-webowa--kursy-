package com.bootcamp.bootcamp.service;

import com.bootcamp.bootcamp.model.EnrolmentDetails;
import com.bootcamp.bootcamp.model.repository.EnrolmentDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrolmentDetailsService {
    @Autowired
    private EnrolmentDetailsRepository enrolmentDetailsRepository;


    public List<EnrolmentDetails> getAllStudentEnrolments(long id) {

        return enrolmentDetailsRepository.getStudentEnrolments(id);
    }


    public void addToDB(EnrolmentDetails enrolmentDetails) {

        enrolmentDetailsRepository.save(enrolmentDetails);
    }

    public void updateDB(EnrolmentDetails enrolmentDetails) {

        enrolmentDetailsRepository.save(enrolmentDetails);
    }
    public long checkIfEnroled(long editionId, long userId)
    {
        return enrolmentDetailsRepository.checkEdUs(userId,editionId);
    }
}
