package com.poc.academia.api.tenant;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity {

    @Size(max = 16)
    @Column(name = "AUD_CRIADO_POR", updatable = false)
    private String createdBy;

    @CreationTimestamp
    @Column(name = "AUD_DH_CRIACAO", updatable = false)
    private LocalDateTime createdIn;

    @Size(max = 16)
    @Column(name = "AUD_ALTERADO_POR")
    private String updatedBy;

    @UpdateTimestamp
    @Column(name = "AUD_DH_ALTERACAO")
    private LocalDateTime updatedIn;

    @Version
    @Column(name = "AUD_VERSAO")
    private Integer version;
}
