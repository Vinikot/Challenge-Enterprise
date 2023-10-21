package br.com.fiap.challenge.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "TB_ITENS_COMPRA")
public class CompraItens {

    @Column(name = "QUANTIDADE_ITENS_COMPRA", columnDefinition = "NUMBER(11,2)")
    private BigDecimal quantidade;

    @Column(name = "VALOR_ITENS_COMPRA", columnDefinition = "NUMBER(11,2)")
    private BigDecimal valor;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "COMPRA",
            referencedColumnName = "ID_COMPRA",
            foreignKey = @ForeignKey(name = "FK_ID_COMPRA")
    )
    private Compra compra;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ITENS",
            referencedColumnName = "ID_ITENS",
            foreignKey = @ForeignKey(name = "FK_ID_ITENS")
    )
    private Itens itens;

    public CompraItens() {
    }

    public CompraItens(BigDecimal quantidade, BigDecimal valor, Compra compra, Itens itens) {
        this.quantidade = quantidade;
        this.valor = valor;
        this.compra = compra;
        this.itens = itens;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public CompraItens setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public CompraItens setValor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public Compra getCompra() {
        return compra;
    }

    public CompraItens setCompra(Compra compra) {
        this.compra = compra;
        return this;
    }

    public Itens getItens() {
        return itens;
    }

    public CompraItens setItens(Itens itens) {
        this.itens = itens;
        return this;
    }

    @Override
    public String toString() {
        return "CompraItens{" +
                "quantidade=" + quantidade +
                ", valor=" + valor +
                ", compra=" + compra +
                ", itens=" + itens +
                '}';
    }
}
