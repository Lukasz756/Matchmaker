package com.projectSpringJava.Matchmaker.service;

import com.projectSpringJava.Matchmaker.DTO.GameDTO;
import com.projectSpringJava.Matchmaker.DTO.UserDTO;
import com.projectSpringJava.Matchmaker.entity.Game;
import com.projectSpringJava.Matchmaker.entity.User;
import com.projectSpringJava.Matchmaker.repository.GameRepo;
import com.projectSpringJava.Matchmaker.repository.RoleRepo;
import com.projectSpringJava.Matchmaker.repository.UserRepo;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GameServiceImpl implements GameService{

    private GameRepo gameRepo;
    private UserRepo userRepo;

    public GameServiceImpl(UserRepo userRepo, GameRepo gameRepo){
        this.userRepo = userRepo;
        this.gameRepo = gameRepo;

    }

    @Override
    public void addUserToGame(Long id, String email) {

       Set<User> users = new HashSet<>();
       users.add(userRepo.findByEmail(email));
       gameRepo.findById(id).get().setUsers(users);

    }

    @Override
    public Game findByTitle(String title) {

        List<Game> games = new ArrayList<Game>();
        games=gameRepo.findAll().stream().filter(g -> g.getTitle().equals(title)).toList();
        if(games.isEmpty()){
            return null;
        }else{
            return games.get(0);
        }
    }

    @Override
    public void createGame(GameDTO gameDTO) {
        Game game = new Game();
        game.setTitle(gameDTO.getTitle());
        gameRepo.save(game);
    }

    @Override
    public List<GameDTO> findAllGames() {
        return gameRepo.findAll().stream().map((game) -> convertEntityToDto(game))
                .collect(Collectors.toList());
    }

    private GameDTO convertEntityToDto(Game game){
        GameDTO gameDTO = new GameDTO();
        gameDTO.setTitle(game.getTitle());
        gameDTO.setId(game.getId());
        return gameDTO;
    }


}
