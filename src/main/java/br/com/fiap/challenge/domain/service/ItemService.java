package br.com.fiap.challenge.domain.service;

import br.com.fiap.challenge.Main;
import br.com.fiap.challenge.domain.entity.Item;
import br.com.fiap.challenge.domain.repository.ItemRepository;
import br.com.fiap.challenge.infra.database.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class ItemService implements Service<Item, Long> {

    private static final AtomicReference<ItemService> instance = new AtomicReference<>();

    private final ItemRepository repository;

    private ItemService(ItemRepository repository) {

        this.repository = repository;
    }

    public static ItemService build() {
        ItemService result = instance.get();
        if (Objects.isNull( result )) {
            EntityManagerFactory factory = EntityManagerFactoryProvider.of( Main.PERSISTENCE_UNIT ).provide();
            EntityManager manager = factory.createEntityManager();
            ItemRepository repository = ItemRepository.build( manager );
            ItemService service = new ItemService( repository );
            if (instance.compareAndSet( null, service )) {
                result = service;
            } else {
                result = instance.get();
            }
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        return repository.findAll();
    }

    @Override
    public Item findById(Long id) {
        return repository.findById( id );
    }

    @Override
    public Item persist(Item item) {
        return repository.persist( item );
    }

    @Override
    public Item update(Long id, Item item) {
        var entidade = repository.findById( id );
        if (Objects.isNull( entidade )) return null;
        item.setId( id );
        return repository.update(item);
    }

    @Override
    public boolean delete(Item item) {
        return repository.delete(item);
    }
}
