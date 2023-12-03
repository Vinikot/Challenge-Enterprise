package br.com.fiap.challenge.domain.service;

import br.com.fiap.challenge.Main;
import br.com.fiap.challenge.domain.entity.TipoDeItem;
import br.com.fiap.challenge.domain.repository.TipoDeItemRepository;
import br.com.fiap.challenge.infra.database.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class TipoDeItemService implements Service<TipoDeItem, Long> {

    private static final AtomicReference<TipoDeItemService> instance = new AtomicReference<>();

    private final TipoDeItemRepository repository;

    private TipoDeItemService(TipoDeItemRepository repository) {

        this.repository = repository;
    }

    public static TipoDeItemService build() {
        TipoDeItemService result = instance.get();
        if (Objects.isNull( result )) {
            EntityManagerFactory factory = EntityManagerFactoryProvider.of( Main.PERSISTENCE_UNIT ).provide();
            EntityManager manager = factory.createEntityManager();
            TipoDeItemRepository repository = TipoDeItemRepository.build( manager );
            TipoDeItemService service = new TipoDeItemService( repository );
            if (instance.compareAndSet( null, service )) {
                result = service;
            } else {
                result = instance.get();
            }
        }
        return result;
    }

    @Override
    public List<TipoDeItem> findAll() {
        return repository.findAll();
    }

    @Override
    public TipoDeItem findById(Long id) {
        return repository.findById( id );
    }

    @Override
    public TipoDeItem persist(TipoDeItem tipoDeItem) {
        return repository.persist( tipoDeItem );
    }

    @Override
    public TipoDeItem update(Long id, TipoDeItem tipoDeItem) {
        var entidade = repository.findById( id );
        if (Objects.isNull( entidade )) return null;
        tipoDeItem.setId( id );
        return repository.update(tipoDeItem);
    }

    @Override
    public boolean delete(TipoDeItem tipoDeItem) {
        return repository.delete(tipoDeItem);
    }
}
