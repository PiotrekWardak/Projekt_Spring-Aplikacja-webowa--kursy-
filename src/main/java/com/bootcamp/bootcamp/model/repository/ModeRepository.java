package com.bootcamp.bootcamp.model.repository;

import com.bootcamp.bootcamp.model.Mode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModeRepository extends JpaRepository<Mode,Long> {
    List<Mode> findAllByOrderByNazwa();
}
