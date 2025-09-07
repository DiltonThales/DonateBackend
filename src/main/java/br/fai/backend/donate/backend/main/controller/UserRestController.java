package br.fai.backend.donate.backend.main.controller;

import br.fai.backend.donate.backend.main.domain.UsuarioModel;
import br.fai.backend.donate.backend.main.dto.RecoveryPasswordDto;
import br.fai.backend.donate.backend.main.port.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/user")

public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<UsuarioModel>> getEntities(){
        List<UsuarioModel> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioModel> getEntityById(@PathVariable final int id){
        UsuarioModel user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioModel> getEntityByEmail(@PathVariable final String email){
        UsuarioModel user = userService.findByEmail(email);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping()
    public ResponseEntity<UsuarioModel> createEntity(@RequestBody final UsuarioModel data){
        int id = userService.create(data);
        final URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(("/{id}"))
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(uri).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEntity(@PathVariable final int id, @RequestBody final UsuarioModel data){
        userService.update(id, data);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioModel> deleteEntity(@PathVariable final int id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/recovery-password")
    public ResponseEntity<Void> recoveryPassword(@RequestBody final RecoveryPasswordDto data) {
        final boolean response = userService.recoveryPassword(data.getId(), data.getNewPassword());
        return response ? ResponseEntity.ok().build() :
                ResponseEntity.badRequest().build();
    }


}
