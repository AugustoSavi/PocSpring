package com.poc.academia.api.usuario.representations;

import com.poc.academia.api.usuario.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UsuarioDto {

    @Id
    private UUID id;
    @NotBlank(message = "user não pode ser vazio")
    @Length(min = 4, max = 100, message = "tamanho do user deve ser entre 4 e 100")
    private String username;

    @NotBlank(message = "password não pode ser vazio")
    @Length(min = 8, max = 100, message = "tamanho do password deve ser entre 4 e 100")
    private String password;

    @Email
    private String email;


    public UsuarioDto(Usuario usuario) {
        BeanUtils.copyProperties(usuario, this);
    }

}
