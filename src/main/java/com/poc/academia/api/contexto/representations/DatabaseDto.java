package com.poc.academia.api.contexto.representations;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatabaseDto {

    @Id
    private Long id;

    @NotBlank
    private String descricao;
}
