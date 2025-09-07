package br.fai.backend.donate.backend.main.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioModel {

    private int id;
    private String nome;
    private String telefone;
    private String senha;
    private String email;
    private String cpf;

    private Boolean doadora;
    private Boolean receptora;
    private Boolean profissional;
    private Boolean admin;

    private Double latitude;
    private Double longitude;

    private UserRole userRole;

    public enum UserRole {
        DOADOR,
        RECEPTOR,
        PROFISSIONAL,
        ADMIN
    }

    // Define userRole automaticamente com base nos booleanos
    public void definirUserRole() {
        if (Boolean.TRUE.equals(admin)) this.userRole = UserRole.ADMIN;
        else if (Boolean.TRUE.equals(profissional)) this.userRole = UserRole.PROFISSIONAL;
        else if (Boolean.TRUE.equals(receptora)) this.userRole = UserRole.RECEPTOR;
        else if (Boolean.TRUE.equals(doadora)) this.userRole = UserRole.DOADOR;
        else this.userRole = null;
    }
}
