package com.poc.academia.api.contexto.representations;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntidadeDto {

    @Id
    private Long id;

    @NotBlank
    private String descricao;

    @NotNull
    private DatabaseDto database;
}
