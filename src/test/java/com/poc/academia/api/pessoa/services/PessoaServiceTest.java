package com.poc.academia.api.pessoa.services;

import com.poc.academia.api.pessoa.Pessoa;
import com.poc.academia.api.pessoa.repositories.PessoaRepository;
import com.poc.academia.api.pessoa.representations.PessoaDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class PessoaServiceTest {


    @Autowired
    private PessoaService pessoaService;

    @MockBean
    private PessoaRepository pessoaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve salvar pessoa com sucesso")
    void deveSalvarPessoaComSucesso() {
        // given
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setNome("João");

        Pessoa pessoaSalva = new Pessoa();
        pessoaSalva.setId(UUID.randomUUID());
        pessoaSalva.setNome(pessoaDto.getNome());
        pessoaSalva.setCpf(pessoaDto.getCpf());
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoaSalva);

        // when
        Pessoa pessoa = pessoaService.save(pessoaDto);

        // then
        Assertions.assertNotNull(pessoa.getId());
        Assertions.assertEquals(pessoaDto.getNome(), pessoa.getNome());
        Assertions.assertEquals(pessoaDto.getCpf(), pessoa.getCpf());
    }

    @Test
    @DisplayName("Deve atualizar pessoa com sucesso")
    void deveAtualizarPessoaComSucesso() {
        // given
        UUID pessoaId = UUID.randomUUID();
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setId(pessoaId);
        pessoaDto.setNome("João");
        pessoaDto.setCpf("70769399169");

        Pessoa pessoaExistente = new Pessoa();
        pessoaExistente.setId(pessoaId);
        pessoaExistente.setNome("Maria");
        pessoaExistente.setCpf("70769399170");

        Optional<Pessoa> optionalPessoa = Optional.of(pessoaExistente);
        when(pessoaRepository.findById(pessoaId)).thenReturn(optionalPessoa);
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoaExistente);

        // when
        Pessoa pessoaAtualizada = pessoaService.update(pessoaDto);

        // then
        Assertions.assertEquals(pessoaDto.getId(), pessoaAtualizada.getId());
        Assertions.assertEquals(pessoaDto.getNome(), pessoaAtualizada.getNome());
        Assertions.assertEquals(pessoaDto.getCpf(), pessoaAtualizada.getCpf());
//        Assertions.assertNotEquals(pessoaExistente.getNome(), pessoaAtualizada.getNome());

//        Assertions.assertNotEquals(pessoaExistente.getCpf(), pessoaAtualizada.getCpf());
//        Assertions.assertNotEquals(pessoaExistente.getVersion(), pessoaAtualizada.getVersion());
    }
}