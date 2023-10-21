package br.com.fiap.challenge.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_FORNECEDOR")
public class Fornecedor {

    @Id
    @Column(name = "CNPJ_FORNECEDOR", columnDefinition = "NUMBER(14)")
    private long cnpj;

    @Column(name = "NM_FORNECEDOR", columnDefinition = "VARCHAR(50)", nullable = false)
    private String nome;

    @Column(name = "TELEFONE_FORNECEDOR", columnDefinition = "NUMBER(11)", nullable = false)
    private long telefone;

    @Column(name = "REPUTACAO_FORNECEDOR", columnDefinition = "VARCHAR(250)", nullable = false)
    private String reputacao;

    public Fornecedor() {
    }

    public Fornecedor(long cnpj, String nome, long telefone, String reputacao) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.telefone = telefone;
        this.reputacao = reputacao;
    }

    public long getCnpj() {
        return cnpj;
    }

    public Fornecedor setCnpj(long cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Fornecedor setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public long getTelefone() {
        return telefone;
    }

    public Fornecedor setTelefone(long telefone) {
        this.telefone = telefone;
        return this;
    }

    public String getReputacao() {
        return reputacao;
    }

    public Fornecedor setReputacao(String reputacao) {
        this.reputacao = reputacao;
        return this;
    }

    @Override
    public String toString() {
        return "Fornecedor{" +
                "cnpj=" + cnpj +
                ", nome='" + nome + '\'' +
                ", telefone=" + telefone +
                ", reputacao='" + reputacao + '\'' +
                '}';
    }
}
