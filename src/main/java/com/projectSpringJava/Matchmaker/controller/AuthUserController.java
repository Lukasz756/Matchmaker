package com.projectSpringJava.Matchmaker.controller;

import com.projectSpringJava.Matchmaker.DTO.GameDTO;
import com.projectSpringJava.Matchmaker.DTO.UserDTO;
import com.projectSpringJava.Matchmaker.configure.IAuthenticationFacade;
import com.projectSpringJava.Matchmaker.entity.Game;
import com.projectSpringJava.Matchmaker.entity.Role;
import com.projectSpringJava.Matchmaker.entity.User;
import com.projectSpringJava.Matchmaker.repository.UserRepo;
import com.projectSpringJava.Matchmaker.service.GameService;
import com.projectSpringJava.Matchmaker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
public class AuthUserController {
    private GameService gameService;
    private UserService userService;
    @Autowired
    private IAuthenticationFacade authenticationFacade;

    public AuthUserController(UserService userService, GameService gameService){
        this.userService = userService;
        this.gameService = gameService;
    }
    //home view
    @GetMapping("/index")
    public String home(){
        return "index";
    }

    //login form view
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }


    // handler method to handle user registration request
    @GetMapping("register")
    public String showRegistrationForm(Model model){
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle register user form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDTO user,
                               BindingResult result,
                               Model model){
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }


    //list of users view
    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        List<UserDTO> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    //delete user method
    @GetMapping("/users/delete")
    public String deleteUser(@RequestParam Long id){
        userService.deleteUser(id);
        return "redirect:/users";
    }

    //create game request form
    @GetMapping("/createGame")
    public String createGameForm(Model model) {

        GameDTO gameDTO = new GameDTO();
        model.addAttribute("game",gameDTO);
        return "createGame";
    }

    //create game request
    @PostMapping("/createGame")
    public String createGame(@Valid @ModelAttribute("game") GameDTO game,
                             BindingResult result,
                             Model model){
        Game existing = gameService.findByTitle(game.getTitle());
        if (existing != null) {
            result.rejectValue("title", null, "There is already a game created with that title");
        }
        if (result.hasErrors()) {
            model.addAttribute("game", game);
            return "createGame";
        }
        gameService.createGame(game);
        return "redirect:/users";
    }

    //list of possible games
    @GetMapping("/games")
    public String listPossibleGames(Model model){
        List<GameDTO> games = gameService.findAllGames();
        model.addAttribute("games", games);
        return "games";
    }

    // method that allows users/admins to join game. not implemented yet
    @GetMapping("/joinGame")
    public String joinGame(@RequestParam Long id){

        return "redirect:/index";
    }

}
