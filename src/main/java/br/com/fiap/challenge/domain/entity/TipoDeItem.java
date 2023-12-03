package br.com.fiap.challenge.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_TIPO_ITEM")
public class TipoDeItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TIPO_ITEM")
    @SequenceGenerator( name = "SQ_TIPO_ITEM", sequenceName = "SQ_TIPO_ITEM", allocationSize = 1, initialValue = 1)
    @Column(name = "ID_TIPO_ITEM")
    private Long id;

    @Column(name = "NM_TIPO_ITEM", nullable = false)
    private String nome;

    @Column(name = "DESCRICAO_TIPO_ITEM", nullable = false)
    private String descricao;

    public TipoDeItem() {
    }

    public TipoDeItem(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public TipoDeItem setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public TipoDeItem setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public TipoDeItem setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    @Override
    public String toString() {
        return "TipoDeItem{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
