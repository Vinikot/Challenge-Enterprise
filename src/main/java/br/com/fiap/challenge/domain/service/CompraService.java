package br.com.fiap.challenge.domain.service;

import br.com.fiap.challenge.Main;
import br.com.fiap.challenge.domain.entity.Compra;
import br.com.fiap.challenge.domain.repository.CompraRepository;
import br.com.fiap.challenge.infra.database.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class CompraService implements Service<Compra, Long> {

    private static final AtomicReference<CompraService> instance = new AtomicReference<>();

    private final CompraRepository repository;

    private CompraService(CompraRepository repository) {

        this.repository = repository;
    }

    public static CompraService build() {
        CompraService result = instance.get();
        if (Objects.isNull( result )) {
            EntityManagerFactory factory = EntityManagerFactoryProvider.of( Main.PERSISTENCE_UNIT ).provide();
            EntityManager manager = factory.createEntityManager();
            CompraRepository repository = CompraRepository.build( manager );
            CompraService service = new CompraService( repository );
            if (instance.compareAndSet( null, service )) {
                result = service;
            } else {
                result = instance.get();
            }
        }
        return result;
    }

    @Override
    public List<Compra> findAll() {
        return repository.findAll();
    }

    @Override
    public Compra findById(Long id) {
        return repository.findById( id );
    }

    @Override
    public Compra persist(Compra compra) {
        return repository.persist( compra );
    }

    @Override
    public Compra update(Long id, Compra compra) {
        var entidade = repository.findById( id );
        if (Objects.isNull( entidade )) return null;
        compra.setId( id );
        return repository.update(compra);
    }

    @Override
    public boolean delete(Compra compra) {
        return repository.delete(compra);
    }
}
