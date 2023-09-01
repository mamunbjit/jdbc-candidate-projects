package com.mamun.main.repository;


import com.mamun.main.model.Candidate;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository {

    Optional<Candidate> getById(Integer id);

    List<Candidate> getAll();

    void add(Candidate candidate);
    void updateById(Integer id, Candidate candidate);

    void deleteById(Integer id);

}
