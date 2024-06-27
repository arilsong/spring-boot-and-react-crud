package com.projectcrud.fullStack_project.controller;

import com.projectcrud.fullStack_project.exception.UserNotFoundException;
import com.projectcrud.fullStack_project.model.User;
import com.projectcrud.fullStack_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")  // Permite requisições de uma origem específica (localhost:3000) para evitar problemas de CORS
public class UserController {

    @Autowired
    private UserRepository userRepository;  // Injeta a dependência do repositório de usuários

    @PostMapping("/user")  // Define a rota para criar um novo usuário
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }

    @GetMapping("/users")  // Define a rota para obter todos os usuários
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")  // Define a rota para obter um usuário por ID
    User getUserById(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")  // Define a rota para atualizar um usuário por ID
    User updateUser(@RequestBody User newUser, @PathVariable Long id){
        return userRepository.findById(id)
                .map(user -> {  // Atualiza os campos do usuário existente
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);  // Salva o usuário atualizado
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")  // Define a rota para deletar um usuário por ID
    String deleteUser(@PathVariable Long id){
        if (!userRepository.existsById(id)){  // Verifica se o usuário existe
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);  // Deleta o usuário do repositório
        return "User has been deleted";
    }
}
