package com.poc.academia.api.pessoa.representations;

import com.poc.academia.api.pessoa.Pessoa;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Data
@AllArgsConstructor
public class PessoaDto {

    @Id
    private UUID id;
    @NotBlank
    private String nome;
    @NotBlank
    @Size(max = 11)
    private String cpf;

    public PessoaDto (Pessoa pessoa){
        BeanUtils.copyProperties(pessoa, this);
    }

}
