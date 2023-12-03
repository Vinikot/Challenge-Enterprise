package br.com.fiap.challenge.domain.entity;

import br.com.fiap.challenge.infra.security.entity.Usuario;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "TB_COMPRA")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_COMPRA")
    @SequenceGenerator(name = "SQ_COMPRA", sequenceName = "SQ_COMPRA", allocationSize = 1, initialValue = 1)
    @Column(name = "ID_COMPRA")
    private Long id;

    @Column(name = "COD_CONTRATO_COMPRA")
    private String codContrato;

    @Column(name = "DATA_COMPRA")
    private LocalDate dataCompra;

    @Column(name = "VALOR_TOTAL_COMPRA")
    private BigDecimal valorTotal;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinColumn(
            name = "PESSOA",
            referencedColumnName = "ID_PESSOA",
            foreignKey = @ForeignKey(
                    name = "TB_COMPRA_FK_TB_FORNECEDOR_PESSOA"
            )
    )
    private Fornecedor fornecedor;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "USUARIO",
            referencedColumnName = "ID_USUARIO",
            foreignKey = @ForeignKey(name = "TB_COMPRA_FK_USUARIO")
    )
    private Usuario usuario;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "TB_COMPRA_ITEM",
            joinColumns = {
                    @JoinColumn(
                            name = "COMPRA",
                            referencedColumnName = "ID_COMPRA",
                            foreignKey = @ForeignKey(name = "TB_COMPRA_ITEM_FK_COMPRA")
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "ITEM",
                            referencedColumnName = "ID_ITEM",
                            foreignKey = @ForeignKey(name = "TB_COMPRA_ITEM_FK_ITEM")
                    )
            }
    )
    private Set<Item> itemSet = new LinkedHashSet<>();

    public Compra() {
    }

    public Compra(Long id, String codContrato, LocalDate dataCompra, BigDecimal valorTotal, Fornecedor fornecedor, Usuario usuario, Set<Item> itemSet) {
        this.id = id;
        this.codContrato = codContrato;
        this.dataCompra = dataCompra;
        this.valorTotal = valorTotal;
        this.fornecedor = fornecedor;
        this.usuario = usuario;
        this.itemSet = itemSet;
    }

    public Long getId() {
        return id;
    }

    public Compra setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCodContrato() {
        return codContrato;
    }

    public Compra setCodContrato(String codContrato) {
        this.codContrato = codContrato;
        return this;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public Compra setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
        return this;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public Compra setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
        return this;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public Compra setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
        return this;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Compra setUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public Set<Item> getItemSet() {
        return itemSet;
    }

    public Compra setItemSet(Set<Item> itemSet) {
        this.itemSet = itemSet;
        return this;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", codContrato='" + codContrato + '\'' +
                ", dataCompra=" + dataCompra +
                ", valorTotal=" + valorTotal +
                ", fornecedor=" + fornecedor +
                ", usuario=" + usuario +
                ", itemSet=" + itemSet +
                '}';
    }
}
