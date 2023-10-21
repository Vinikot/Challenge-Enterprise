package br.com.fiap.challenge.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_USUARIO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_EMAIL_USUARIO", columnNames = "EMAIL_USUARIO")
})
public class Usuario {

    @Id
    @Column(name = "CPF_USUARIO", columnDefinition = "NUMBER(11)")
    private long cpf;

    @Column(name = "NM_USUARIO", columnDefinition = "VARCHAR(50)", nullable = false)
    private String nome;

    @Column(name = "EMAIL_USUARIO", columnDefinition = "VARCHAR(50)", nullable = false, unique = true)
    private String email;

    @Column(name = "SENHA_USUARIO", columnDefinition = "VARCHAR(50)", nullable = false)
    private String senha;

    @Column(name = "CEP_ENTREGA_USUARIO", columnDefinition = "NUMBER(8)", nullable = false)
    private long cepEntrega;

    @Column(name = "LOGRADOURO_USUARIO", columnDefinition = "VARCHAR(50)", nullable = false)
    private String logradouro;

    @Column(name = "NUM_LOGRADOURO_USUARIO", columnDefinition = "NUMBER(5)", nullable = false)
    private int numLogradouro;

    @Column(name = "COMPLEMENTO_USUARIO", columnDefinition = "VARCHAR(50)")
    private String complemento;

    @Column(name = "TELEFONE_USUARIO", columnDefinition = "NUMBER(11)", nullable = false)
    private long telefone;

    public Usuario() {
    }

    public Usuario(long cpf, String nome, String email, String senha, long cepEntrega, String logradouro, int numLogradouro, String complemento, long telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cepEntrega = cepEntrega;
        this.logradouro = logradouro;
        this.numLogradouro = numLogradouro;
        this.complemento = complemento;
        this.telefone = telefone;
    }

    public long getCpf() {
        return cpf;
    }

    public Usuario setCpf(long cpf) {
        this.cpf = cpf;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Usuario setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Usuario setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario setSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public long getCepEntrega() {
        return cepEntrega;
    }

    public Usuario setCepEntrega(long cepEntrega) {
        this.cepEntrega = cepEntrega;
        return this;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public Usuario setLogradouro(String logradouro) {
        this.logradouro = logradouro;
        return this;
    }

    public int getNumLogradouro() {
        return numLogradouro;
    }

    public Usuario setNumLogradouro(int numLogradouro) {
        this.numLogradouro = numLogradouro;
        return this;
    }

    public String getComplemento() {
        return complemento;
    }

    public Usuario setComplemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public long getTelefone() {
        return telefone;
    }

    public Usuario setTelefone(long telefone) {
        this.telefone = telefone;
        return this;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "cpf=" + cpf +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", cepEntrega=" + cepEntrega +
                ", logradouro='" + logradouro + '\'' +
                ", numLogradouro=" + numLogradouro +
                ", complemento='" + complemento + '\'' +
                ", telefone=" + telefone +
                '}';
    }
}
