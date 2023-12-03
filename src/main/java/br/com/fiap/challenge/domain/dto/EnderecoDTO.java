package br.com.fiap.challenge.domain.dto;

import br.com.fiap.challenge.Main;
import br.com.fiap.challenge.domain.entity.Endereco;
import br.com.fiap.challenge.domain.service.EnderecoService;
import br.com.fiap.challenge.infra.security.dto.PessoaDTO;

import java.util.Objects;

public record EnderecoDTO(
        PessoaDTO pessoa,
        String cep,
        String numero,
        String complemento,
        String logradouro
) {

    static EnderecoService service = EnderecoService.build( Main.PERSISTENCE_UNIT );

    public static Endereco of(EnderecoDTO e) {
        Endereco endereco = service.findByCEP( e.cep );
        endereco.setComplemento( e.complemento ).setNumero( e.numero );
        var p = PessoaDTO.of( e.pessoa );
        endereco.setPessoa( p );
        return endereco;
    }

    public static EnderecoDTO of(Endereco e) {

        if (Objects.isNull( e )) return null;
        PessoaDTO pessoa = null;
        if (Objects.nonNull( e.getPessoa() ) && Objects.nonNull( e.getPessoa().getId() )) {
            pessoa = PessoaDTO.of( e.getPessoa() );
        }

        return new EnderecoDTO( pessoa, e.getCep(), e.getNumero(), e.getComplemento(), e.getLogradouro() );
    }

}