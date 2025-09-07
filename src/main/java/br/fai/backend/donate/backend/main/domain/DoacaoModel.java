package br.fai.backend.donate.backend.main.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoacaoModel {

    private int id;
    private String dataDoacao;
    private Integer quantidadeMl;
    private UsuarioModel usuario;// ex: "2025-06-10"
    private BancoLeiteModel bancoDeLeite;;  // ex: "14:30"
    private Integer quantidade_ml;
}
