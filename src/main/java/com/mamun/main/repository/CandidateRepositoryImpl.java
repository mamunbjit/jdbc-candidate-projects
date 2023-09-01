package com.mamun.main.repository;


import com.mamun.main.model.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CandidateRepositoryImpl implements CandidateRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Optional<Candidate> getById(Integer id) {
        String query = "SELECT * FROM candidate WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, (resultSet, rowNum) ->
                Optional.of(new Candidate(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone")
                ))
        );
    }

    @Override
    public List<Candidate> getAll() {
        String query = "SELECT * FROM candidate";
        return jdbcTemplate.query(query, (resultSet, rowNum) ->
                new Candidate(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone")
                )
        );
    }

    @Override
    public void add(Candidate candidate) {
        String query = "insert into candidate (id, name, email, phone) values (?, ?, ?, ?)";
        jdbcTemplate.update(query, candidate.getId(), candidate.getName(), candidate.getEmail(), candidate.getPhone());
    }

    @Override
    public void updateById(Integer id, Candidate candidate) {
        String query = "update candidate set name = ?, email = ?, phone = ? where id = ?";
        jdbcTemplate.update(query, candidate.getName(), candidate.getEmail(), candidate.getPhone(), id);
    }


    @Override
    public void deleteById(Integer id) {
        String query = "delete from candidate where id = ?";
        jdbcTemplate.update(query, id);
    }

}
