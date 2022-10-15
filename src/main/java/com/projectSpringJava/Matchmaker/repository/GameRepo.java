package com.projectSpringJava.Matchmaker.repository;

import com.projectSpringJava.Matchmaker.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GameRepo extends JpaRepository<Game,Long> {

}
