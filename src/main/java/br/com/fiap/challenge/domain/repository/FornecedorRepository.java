package br.com.fiap.challenge.domain.repository;

import br.com.fiap.challenge.domain.entity.Fornecedor;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class FornecedorRepository implements Repository<Fornecedor, Long> {
    private static final AtomicReference<FornecedorRepository> instance = new AtomicReference<>();
    private final EntityManager manager;

    private FornecedorRepository(EntityManager manager) {
        this.manager = manager;
    }

    public static FornecedorRepository build(EntityManager manager) {
        FornecedorRepository result = instance.get();
        if (Objects.isNull(result)) {
            FornecedorRepository repo = new FornecedorRepository(manager);
            if (instance.compareAndSet(null, repo)) {
                result = repo;
            } else {
                result = instance.get();
            }
        }
        return result;
    }

    @Override
    public List<Fornecedor> findAll() {
        return manager.createQuery("FROM Fornecedor", Fornecedor.class).getResultList();
    }

    @Override
    public Fornecedor findById(Long cnpj) {
        return manager.find(Fornecedor.class, cnpj);
    }

    @Override
    public Fornecedor persist(Fornecedor fornecedor) {
        manager.getTransaction().begin();
        manager.persist(fornecedor);
        manager.getTransaction().commit();
        return fornecedor;
    }

    @Override
    public Fornecedor update(Fornecedor fornecedor) {
        manager.getTransaction().begin();
        fornecedor = manager.merge(fornecedor);
        manager.getTransaction().commit();
        return fornecedor;
    }

    @Override
    public boolean delete(Fornecedor fornecedor) {
        manager.getTransaction().begin();
        manager.remove(fornecedor);
        manager.getTransaction().commit();
        return true;
    }
}
