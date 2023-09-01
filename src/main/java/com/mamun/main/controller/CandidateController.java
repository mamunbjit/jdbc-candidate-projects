package com.mamun.main.controller;

import java.util.Optional;
import com.mamun.main.model.Candidate;
import com.mamun.main.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CandidateController {

    @Autowired
    private CandidateRepository candidateRepository;

    @GetMapping("/candidate/all")
    public ResponseEntity<List<Candidate>> getAllCandidate() {
        List<Candidate> candidateList = candidateRepository.getAll();
        return new ResponseEntity<>(candidateList, HttpStatus.OK);
    }

    @GetMapping("/candidate/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable Integer id) {

        Candidate candidate = candidateRepository.getById(id).get();
        return new ResponseEntity<>(candidate, HttpStatus.OK);
    }

//    @GetMapping("/candidates/{id}")
//    public ResponseEntity<Object> getCandidateById(@PathVariable Integer id) {
//        Optional<Candidate> candidate = candidateRepository.getById(id);
//
//        if (candidate.isPresent()) {
//            return new ResponseEntity<>(candidate.get(), HttpStatus.OK);
//        } else {
//            String message = "Candidate with ID " + id + " not found.";
//            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
//        }
//    }

    @PostMapping("/candidate")
    public ResponseEntity<Void> addCandidate(@RequestBody Candidate candidate) {
        candidateRepository.add(candidate);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PutMapping("/candidate/{id}")
    public ResponseEntity<Void> updateCandidateById(@PathVariable Integer id, @RequestBody Candidate candidate) {
        candidateRepository.updateById(id, candidate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/candidate/{id}")
    public ResponseEntity<Void> deleteCandidateById(@PathVariable Integer id) {
        candidateRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

