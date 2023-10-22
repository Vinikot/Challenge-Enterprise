package br.com.fiap.challenge.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "TB_COMPRA")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_COMPRA")
    @SequenceGenerator( name = "SQ_COMPRA", sequenceName = "SQ_COMPRA", allocationSize = 1, initialValue = 1)
    @Column(name = "ID_COMPRA")
    private Long id;

    @Column(name = "COD_CONTRATO_COMPRA", columnDefinition = "VARCHAR(50)")
    private String codContrato;

    @Column(name = "DATA_COMPRA", columnDefinition = "DATE")
    private LocalDate dataCompra;

    @Column(name = "VALOR_TOTAL_COMPRA", columnDefinition = "NUMBER(11,2)")
    private BigDecimal valorTotal;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "USUARIO",
            referencedColumnName = "CPF_USUARIO",
            foreignKey = @ForeignKey(name = "FK_CPF_USUARIO")
    )
    private Usuario usuario;

    public Compra() {
    }

    public Compra(Long id, String codContrato, LocalDate dataCompra, BigDecimal valorTotal, Usuario usuario) {
        this.id = id;
        this.codContrato = codContrato;
        this.dataCompra = dataCompra;
        this.valorTotal = valorTotal;
        this.usuario = usuario;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public Compra setUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", codContrato='" + codContrato + '\'' +
                ", dataCompra=" + dataCompra +
                ", valorTotal=" + valorTotal +
                ", usuario=" + usuario +
                '}';
    }
}
