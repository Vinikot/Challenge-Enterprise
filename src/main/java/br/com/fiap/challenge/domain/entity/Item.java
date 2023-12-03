package br.com.fiap.challenge.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_ITEM")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ITEM")
    @SequenceGenerator( name = "SQ_ITEM", sequenceName = "SQ_ITEM", allocationSize = 1, initialValue = 1)
    @Column(name = "ID_ITEM")
    private Long id;

    @Column(name = "NM_ITEM", nullable = false)
    private String nome;

    @Column(name = "DESCRICAO_ITEM",  nullable = false)
    private String descricao;

    @Column(name = "QUANTIDADE_ITEM")
    private Integer quantidade;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "TIPO_ITEM",
            referencedColumnName = "ID_TIPO_ITEM",
            foreignKey = @ForeignKey(name = "TB_ITEM_FK_TIPO_ITEM")
    )
    private TipoDeItem tipoDeItem;

    public Item() {
    }

    public Item(Long id, String nome, String descricao, Integer quantidade, TipoDeItem tipoDeItem) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.tipoDeItem = tipoDeItem;
    }

    public Long getId() {
        return id;
    }

    public Item setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Item setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Item setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Item setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public TipoDeItem getTipoDeItem() {
        return tipoDeItem;
    }

    public Item setTipoDeItem(TipoDeItem tipoDeItem) {
        this.tipoDeItem = tipoDeItem;
        return this;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", quantidade=" + quantidade +
                ", tipoDeItem=" + tipoDeItem +
                '}';
    }
}
