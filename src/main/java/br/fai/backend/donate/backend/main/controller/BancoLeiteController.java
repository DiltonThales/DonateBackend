package br.fai.backend.donate.backend.main.controller;

import br.fai.backend.donate.backend.main.domain.BancoLeiteModel;
import br.fai.backend.donate.backend.main.port.service.bancoleite.BancoLeiteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/banco-leite")
public class BancoLeiteController {

    private final BancoLeiteService bancoLeiteService;

    public BancoLeiteController(BancoLeiteService bancoLeiteService) {
        this.bancoLeiteService = bancoLeiteService;
    }

    // Listar todos os bancos
    @GetMapping
    public ResponseEntity<List<BancoLeiteModel>> getEntities() {
        List<BancoLeiteModel> bancos = bancoLeiteService.findAll();
        return ResponseEntity.ok().body(bancos);
    }

    // Buscar banco por ID
    @GetMapping("/{id}")
    public ResponseEntity<BancoLeiteModel> getEntityById(@PathVariable final int id) {
        BancoLeiteModel banco = bancoLeiteService.findById(id);
        if (banco == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(banco);
    }

    // Buscar banco mais próximo pela latitude e longitude
    @GetMapping("/proximo")
    public ResponseEntity<Map<String, Object>> getBancoMaisProximo(
            @RequestParam final double latitude,
            @RequestParam final double longitude) {

        Map<String, Object> banco = bancoLeiteService.buscarBancoMaisProximo(latitude, longitude);
        if (banco.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(banco);
    }

    // Criar um novo banco
    @PostMapping
    public ResponseEntity<Void> createEntity(@RequestBody final BancoLeiteModel data) {
        int id = bancoLeiteService.create(data);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    // Atualizar um banco existente
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEntity(@PathVariable final int id, @RequestBody final BancoLeiteModel data) {
        bancoLeiteService.update(id, data);
        return ResponseEntity.ok().build();
    }

    // Deletar um banco
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntity(@PathVariable final int id) {
        bancoLeiteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
