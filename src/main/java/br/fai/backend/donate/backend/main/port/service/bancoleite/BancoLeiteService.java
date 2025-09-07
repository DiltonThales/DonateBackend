package br.fai.backend.donate.backend.main.port.service.bancoleite;

import br.fai.backend.donate.backend.main.domain.BancoLeiteModel;

import java.util.List;
import java.util.Map;

public interface BancoLeiteService {

    List<BancoLeiteModel> findAll();

    BancoLeiteModel findById(int id);

    Map<String, Object> buscarBancoMaisProximo(double latitude, double longitude);

    int create(BancoLeiteModel banco);

    void update(int id, BancoLeiteModel banco);

    void delete(int id);
}
