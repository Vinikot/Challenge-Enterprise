package br.com.fiap.challenge.domain.entity;

import jakarta.persistence.*;

@Entity
@Table
public class Itens {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ITENS")
    @SequenceGenerator( name = "SQ_ITENS", sequenceName = "SQ_ITENS", allocationSize = 1, initialValue = 1)
    @Column(name = "ID_ITENS")
    private int id;

    @Column(name = "NM_ITENS", columnDefinition = "VARCHAR(50)", nullable = false)
    private String nome;

    @Column(name = "DESCRICAO_ITENS", columnDefinition = "VARCHAR(250)", nullable = false)
    private String descricao;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "TIPO_ITEM",
            referencedColumnName = "ID_TIPO_ITEM",
            foreignKey = @ForeignKey(name = "FK_ID_TIPO_ITEM")
    )
    private TipoItem tipoItem;

    public Itens() {
    }

    public Itens(int id, String nome, String descricao, TipoItem tipoItem) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.tipoItem = tipoItem;
    }

    public int getId() {
        return id;
    }

    public Itens setId(int id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Itens setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Itens setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public TipoItem getTipoItem() {
        return tipoItem;
    }

    public Itens setTipoItem(TipoItem tipoItem) {
        this.tipoItem = tipoItem;
        return this;
    }

    @Override
    public String toString() {
        return "Itens{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", tipoItem=" + tipoItem +
                '}';
    }
}
