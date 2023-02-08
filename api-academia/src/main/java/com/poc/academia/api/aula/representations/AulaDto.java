package com.poc.academia.api.aula.representations;

import com.poc.academia.api.aula.Aula;
import com.poc.academia.api.aula.enums.TipoAula;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
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

    private LocalDateTime dataHoraInicio;

    private LocalDateTime dataHoraFinal;

    private TipoAula tipoAula;


    public AulaDto(Aula aula) {
        BeanUtils.copyProperties(aula, this);
    }

    public Aula AulaDtoToAula(AulaDto aulaDto) {
        Aula aula = new Aula();
        BeanUtils.copyProperties(aulaDto, aula);

        return aula;
    }

}
