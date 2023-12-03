package br.com.fiap.challenge.domain.entity;

import br.com.fiap.challenge.infra.security.entity.PessoaJuridica;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "TB_FORNECEDOR")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FORNECEDOR")
    @SequenceGenerator( name = "SQ_FORNECEDOR", sequenceName = "SQ_FORNECEDOR", allocationSize = 1, initialValue = 1)
    @Column(name = "ID_FORNECEDOR")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinColumn(
            name = "ID_PESSOA",
            referencedColumnName = "ID_PESSOA",
            foreignKey = @ForeignKey(
                    name = "FK_TB_PASSAGEIRO_PESSOA"
            )
    )
    private PessoaJuridica pessoa;

    @Column(name = "NM_FORNECEDOR", nullable = false)
    private String nome;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "TB_FORNECEDOR_ITEM",
            joinColumns = {
                    @JoinColumn(
                            name = "FORNECEDOR",
                            referencedColumnName = "ID_FORNECEDOR",
                            foreignKey = @ForeignKey(name = "TB_FORNECEDOR_ITEM_FK_FORNECEDOR")
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "ITEM",
                            referencedColumnName = "ID_ITEM",
                            foreignKey = @ForeignKey(name = "TB_FORNECEDOR_ITEM_FK_ITEM")
                    )
            }
    )
    private Set<Item> itemSet = new LinkedHashSet<>();

    @Column(name = "REPUTACAO_FORNECEDOR", nullable = false)
    private String reputacao;

    public Fornecedor() {
    }

    public Fornecedor(Long id, PessoaJuridica pessoa, String nome, Set<Item> itemSet, String reputacao) {
        this.id = id;
        this.pessoa = pessoa;
        this.nome = nome;
        this.itemSet = itemSet;
        this.reputacao = reputacao;
    }

    public Long getId() {
        return id;
    }

    public Fornecedor setId(Long id) {
        this.id = id;
        return this;
    }

    public PessoaJuridica getPessoa() {
        return pessoa;
    }

    public Fornecedor setPessoa(PessoaJuridica pessoa) {
        this.pessoa = pessoa;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Fornecedor setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Set<Item> getItemSet() {
        return itemSet;
    }

    public Fornecedor setItemSet(Set<Item> itemSet) {
        this.itemSet = itemSet;
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
                "id=" + id +
                ", pessoa=" + pessoa +
                ", nome='" + nome + '\'' +
                ", itemSet=" + itemSet +
                ", reputacao='" + reputacao + '\'' +
                '}';
    }
}
