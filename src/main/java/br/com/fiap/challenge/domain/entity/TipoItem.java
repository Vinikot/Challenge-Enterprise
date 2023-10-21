package br.com.fiap.challenge.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_TIPO_ITEM")
public class TipoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TIPO_ITEM")
    @SequenceGenerator( name = "SQ_TIPO_ITEM", sequenceName = "SQ_TIPO_ITEM", allocationSize = 1, initialValue = 1)
    @Column(name = "ID_TIPO_ITEM")
    private int id;

    @Column(name = "NM_TIPO_ITEM", columnDefinition = "VARCHAR(50)", nullable = false)
    private String nome;

    @Column(name = "DESCRICAO_TIPO_ITEM", columnDefinition = "VARCHAR(250)", nullable = false)
    private String descricao;

    public TipoItem() {
    }

    public TipoItem(int id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public TipoItem setId(int id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public TipoItem setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public TipoItem setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    @Override
    public String toString() {
        return "TipoItem{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
