package br.com.fiap.challenge.domain.repository;

import br.com.fiap.challenge.domain.entity.CompraItens;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class CompraItensRepository implements Repository<CompraItens, Integer> {
    private static final AtomicReference<CompraItensRepository> instance = new AtomicReference<>();
    private final EntityManager manager;

    private CompraItensRepository(EntityManager manager) {
        this.manager = manager;
    }

    public static CompraItensRepository build(EntityManager manager) {
        CompraItensRepository result = instance.get();
        if (Objects.isNull(result)) {
            CompraItensRepository repo = new CompraItensRepository(manager);
            if (instance.compareAndSet(null, repo)) {
                result = repo;
            } else {
                result = instance.get();
            }
        }
        return result;
    }

    @Override
    public List<CompraItens> findAll() {
        return manager.createQuery("FROM CompraItens", CompraItens.class).getResultList();
    }

    @Override
    public CompraItens findById(Integer id) {
        return null;
    }

    @Override
    public CompraItens persist(CompraItens compraItens) {
        manager.getTransaction().begin();
        manager.persist(compraItens);
        manager.getTransaction().commit();
        return compraItens;
    }

    @Override
    public CompraItens update(CompraItens compraItens) {
//        manager.getTransaction().begin();
//        compraItens = manager.merge(compraItens);
//        manager.getTransaction().commit();
        return null;
    }

    @Override
    public boolean delete(CompraItens compraItens) {
        manager.getTransaction().begin();
        manager.remove(compraItens);
        manager.getTransaction().commit();
        return true;
    }
}
