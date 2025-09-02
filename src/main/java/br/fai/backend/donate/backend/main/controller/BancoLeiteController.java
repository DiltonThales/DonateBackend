/*
package com.donate.backend.main.main.controller;

import com.donate.backend.main.main.domain.BancoLeiteModel;
import com.donate.backend.main.main.port.service.bancoleite.BancoLeiteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/banco-leite")
public class BancoLeiteController {

    private final BancoLeiteService bancoLeiteService;

    public BancoLeiteController(BancoLeiteService bancoLeiteService) {
        this.bancoLeiteService = bancoLeiteService;
    }

    @GetMapping()
    public ResponseEntity<List<BancoLeiteModel>> getEntities(){
        List<BancoLeiteModel> bancos = bancoLeiteService.findAll();
        return ResponseEntity.ok().body(bancos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BancoLeiteModel> getEntityById(@PathVariable final int id){
        BancoLeiteModel banco = bancoLeiteService.findById(id);
        return ResponseEntity.ok().body(banco);
    }

    @GetMapping("/proximo")
    public ResponseEntity<Map<String, Object>> getBancoMaisProximo(
            @RequestParam final double latitude,
            @RequestParam final double longitude) {

        Map<String, Object> banco = bancoLeiteService.buscarBancoMaisProximo(latitude, longitude);
        return ResponseEntity.ok().body(banco);
    }

    @PostMapping()
    public ResponseEntity<BancoLeiteModel> createEntity(@RequestBody final BancoLeiteModel data){
        int id = bancoLeiteService.create(data);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEntity(@PathVariable final int id, @RequestBody final BancoLeiteModel data) {
        bancoLeiteService.update(id, data);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BancoLeiteModel> deleteEntity(@PathVariable final int id){
        bancoLeiteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
**/
