package com.poc.api.pessoa.representations;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PessoaDto {

    @NotBlank
    private String nome;
    @NotBlank
    @Size(max = 11)
    private String cpf;
}
