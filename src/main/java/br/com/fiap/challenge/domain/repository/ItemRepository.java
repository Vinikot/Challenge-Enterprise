package br.com.fiap.challenge.domain.repository;

import br.com.fiap.challenge.domain.entity.Item;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class ItemRepository implements Repository<Item, Long> {
    private static final AtomicReference<ItemRepository> instance = new AtomicReference<>();
    private final EntityManager manager;

    private ItemRepository(EntityManager manager) {
        this.manager = manager;
    }

    public static ItemRepository build(EntityManager manager) {
        ItemRepository result = instance.get();
        if (Objects.isNull(result)) {
            ItemRepository repo = new ItemRepository(manager);
            if (instance.compareAndSet(null, repo)) {
                result = repo;
            } else {
                result = instance.get();
            }
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        return manager.createQuery("FROM Item", Item.class).getResultList();
    }

    @Override
    public Item findById(Long id) {
        return manager.find(Item.class, id);
    }

    @Override
    public Item persist(Item item) {
        manager.getTransaction().begin();
        manager.persist(item);
        manager.getTransaction().commit();
        return item;
    }

    @Override
    public Item update(Item item) {
        manager.getTransaction().begin();
        item = manager.merge(item);
        manager.getTransaction().commit();
        return item;
    }

    @Override
    public boolean delete(Item item) {
        manager.getTransaction().begin();
        manager.remove(item);
        manager.getTransaction().commit();
        return true;
    }
}
