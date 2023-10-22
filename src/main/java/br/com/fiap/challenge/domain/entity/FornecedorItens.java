package br.com.fiap.challenge.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "TB_ITENS_FORNECEDOR")
public class FornecedorItens {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ITENS_FORNECEDOR")
    @SequenceGenerator( name = "SQ_ITENS_FORNECEDOR", sequenceName = "SQ_ITENS_FORNECEDOR", allocationSize = 1, initialValue = 1)
    @Column(name = "ID_ITENS_FORNECEDOR")
    private int id;

    @Column(name = "QUANTIDADE_ITENS_FORNECEDOR", columnDefinition = "NUMBER(11,2)")
    private BigDecimal quantidade;

    @Column(name = "VALOR_ITENS_FORNECEDOR", columnDefinition = "NUMBER(11,2)")
    private BigDecimal valor;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "FORNECEDOR",
            referencedColumnName = "CNPJ_FORNECEDOR",
            foreignKey = @ForeignKey(name = "FK_CNPJ_FORNECEDOR")
    )
    private Fornecedor fornecedor;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ITENS",
            referencedColumnName = "ID_ITENS",
            foreignKey = @ForeignKey(name = "FK_ID_ITENS_F")
    )
    private Itens itens;

    public FornecedorItens() {
    }

    public FornecedorItens(BigDecimal quantidade, BigDecimal valor, Fornecedor fornecedor, Itens itens) {
        this.quantidade = quantidade;
        this.valor = valor;
        this.fornecedor = fornecedor;
        this.itens = itens;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public FornecedorItens setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public FornecedorItens setValor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public FornecedorItens setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
        return this;
    }

    public Itens getItens() {
        return itens;
    }

    public FornecedorItens setItens(Itens itens) {
        this.itens = itens;
        return this;
    }

    @Override
    public String toString() {
        return "FornecedorItens{" +
                "quantidade=" + quantidade +
                ", valor=" + valor +
                ", fornecedor=" + fornecedor +
                ", itens=" + itens +
                '}';
    }
}
