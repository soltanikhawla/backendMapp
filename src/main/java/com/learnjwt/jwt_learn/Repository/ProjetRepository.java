package com.learnjwt.jwt_learn.Repository;

import com.learnjwt.jwt_learn.Entity.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetRepository extends JpaRepository<Projet,Long> {
}
