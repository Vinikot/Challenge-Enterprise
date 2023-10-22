package br.com.fiap.challenge.domain.repository;

import br.com.fiap.challenge.domain.entity.TipoItem;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class TipoItemRepository implements Repository<TipoItem, Integer> {
    private static final AtomicReference<TipoItemRepository> instance = new AtomicReference<>();
    private final EntityManager manager;

    private TipoItemRepository(EntityManager manager) {
        this.manager = manager;
    }

    public static TipoItemRepository build(EntityManager manager) {
        TipoItemRepository result = instance.get();
        if (Objects.isNull(result)) {
            TipoItemRepository repo = new TipoItemRepository(manager);
            if (instance.compareAndSet(null, repo)) {
                result = repo;
            } else {
                result = instance.get();
            }
        }
        return result;
    }

    @Override
    public List<TipoItem> findAll() {
        return manager.createQuery("FROM TipoItem", TipoItem.class).getResultList();
    }

    @Override
    public TipoItem findById(Integer id) {
        return manager.find(TipoItem.class, id);
    }

    @Override
    public TipoItem persist(TipoItem tipoItem) {
        manager.getTransaction().begin();
        manager.persist(tipoItem);
        manager.getTransaction().commit();
        return tipoItem;
    }

    @Override
    public TipoItem update(TipoItem tipoItem) {
        manager.getTransaction().begin();
        tipoItem = manager.merge(tipoItem);
        manager.getTransaction().commit();
        return tipoItem;
    }

    @Override
    public boolean delete(TipoItem tipoItem) {
        manager.getTransaction().begin();
        manager.remove(tipoItem);
        manager.getTransaction().commit();
        return true;
    }
}
