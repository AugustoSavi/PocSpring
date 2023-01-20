package com.poc.pessoa.representations;

import com.poc.pessoa.Pessoa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class PessoaDto {

    @NotBlank
    private String nome;
    @NotBlank
    @Size(max = 11)
    private String cpf;

    public PessoaDto toDto(Pessoa pessoa){
        PessoaDto pessoaDto = new PessoaDto();
        BeanUtils.copyProperties(pessoa, pessoaDto);
        return pessoaDto;
    }
}
