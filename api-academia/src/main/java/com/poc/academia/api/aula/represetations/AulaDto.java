package com.poc.academia.api.aula.represetations;

import com.poc.academia.api.aula.Aula;
import com.poc.academia.api.aula.AulaAluno;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AulaDto {

    @Id
    private UUID id;

    @NotBlank
    private String descricao;

    private List<AulaAlunoDto> alunos;


    public AulaDto(Aula aula){
        BeanUtils.copyProperties(aula, this);
    }

    public Aula AulaDtoToAula (AulaDto aulaDto){
        Aula aula = new Aula();
        BeanUtils.copyProperties(aulaDto, aula);

        return aula;
    }

}
