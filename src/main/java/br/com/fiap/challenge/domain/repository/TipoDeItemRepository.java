package br.com.fiap.challenge.domain.repository;

import br.com.fiap.challenge.domain.entity.TipoDeItem;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class TipoDeItemRepository implements Repository<TipoDeItem, Long> {
    private static final AtomicReference<TipoDeItemRepository> instance = new AtomicReference<>();
    private final EntityManager manager;

    private TipoDeItemRepository(EntityManager manager) {
        this.manager = manager;
    }

    public static TipoDeItemRepository build(EntityManager manager) {
        TipoDeItemRepository result = instance.get();
        if (Objects.isNull(result)) {
            TipoDeItemRepository repo = new TipoDeItemRepository(manager);
            if (instance.compareAndSet(null, repo)) {
                result = repo;
            } else {
                result = instance.get();
            }
        }
        return result;
    }

    @Override
    public List<TipoDeItem> findAll() {
        return manager.createQuery("FROM TipoDeItem", TipoDeItem.class).getResultList();
    }

    @Override
    public TipoDeItem findById(Long id) {
        return manager.find(TipoDeItem.class, id);
    }

    @Override
    public TipoDeItem persist(TipoDeItem tipoDeItem) {
        manager.getTransaction().begin();
        manager.persist(tipoDeItem);
        manager.getTransaction().commit();
        return tipoDeItem;
    }

    @Override
    public TipoDeItem update(TipoDeItem tipoDeItem) {
        manager.getTransaction().begin();
        tipoDeItem = manager.merge(tipoDeItem);
        manager.getTransaction().commit();
        return tipoDeItem;
    }

    @Override
    public boolean delete(TipoDeItem tipoDeItem) {
        manager.getTransaction().begin();
        manager.remove(tipoDeItem);
        manager.getTransaction().commit();
        return true;
    }
}
