package com.poc.academia.api.aula.represetations;

import com.poc.academia.api.aula.Aula;
import com.poc.academia.api.aula.AulaAluno;
import com.poc.academia.api.pessoa.Pessoa;
import com.poc.academia.api.pessoa.representations.PessoaDto;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AulaAlunoDto {
    @Id
    private UUID id;

    private Aula aula;

    private Pessoa pessoa;

    public AulaAluno AulaAlunoDtoToAulaAluno(Aula aula, Pessoa pessoa){
        return new AulaAluno(aula,pessoa);
    }
}
