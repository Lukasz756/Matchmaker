package com.projectSpringJava.Matchmaker.service;

import com.projectSpringJava.Matchmaker.DTO.GameDTO;
import com.projectSpringJava.Matchmaker.entity.Game;
import com.projectSpringJava.Matchmaker.entity.User;

import java.util.List;

public interface GameService {
    void addUserToGame(Long id, String email);
    Game findByTitle(String title);
    void createGame(GameDTO gameDTO);
    List<GameDTO> findAllGames();
}
