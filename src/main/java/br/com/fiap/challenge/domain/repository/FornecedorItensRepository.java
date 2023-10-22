package br.com.fiap.challenge.domain.repository;

import br.com.fiap.challenge.domain.entity.FornecedorItens;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class FornecedorItensRepository implements Repository<FornecedorItens, Integer> {
    private static final AtomicReference<FornecedorItensRepository> instance = new AtomicReference<>();
    private final EntityManager manager;

    private FornecedorItensRepository(EntityManager manager) {
        this.manager = manager;
    }

    public static FornecedorItensRepository build(EntityManager manager) {
        FornecedorItensRepository result = instance.get();
        if (Objects.isNull(result)) {
            FornecedorItensRepository repo = new FornecedorItensRepository(manager);
            if (instance.compareAndSet(null, repo)) {
                result = repo;
            } else {
                result = instance.get();
            }
        }
        return result;
    }

    @Override
    public List<FornecedorItens> findAll() {
        return manager.createQuery("FROM FornecedorItens", FornecedorItens.class).getResultList();
    }

    @Override
    public FornecedorItens findById(Integer id) {
        return null;
    }

    @Override
    public FornecedorItens persist(FornecedorItens fornecedorItens) {
        manager.getTransaction().begin();
        manager.persist(fornecedorItens);
        manager.getTransaction().commit();
        return fornecedorItens;
    }

    @Override
    public FornecedorItens update(FornecedorItens fornecedorItens) {
//        manager.getTransaction().begin();
//        fornecedorItens = manager.merge(fornecedorItens);
//        manager.getTransaction().commit();
        return null;
    }

    @Override
    public boolean delete(FornecedorItens fornecedorItens) {
        manager.getTransaction().begin();
        manager.remove(fornecedorItens);
        manager.getTransaction().commit();
        return true;
    }
}
