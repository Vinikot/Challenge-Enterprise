package br.com.fiap.challenge.domain.service;

import br.com.fiap.challenge.Main;
import br.com.fiap.challenge.domain.entity.Fornecedor;
import br.com.fiap.challenge.domain.repository.FornecedorRepository;
import br.com.fiap.challenge.infra.database.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class FornecedorService implements Service<Fornecedor, Long> {

    private static final AtomicReference<FornecedorService> instance = new AtomicReference<>();

    private final FornecedorRepository repository;

    private FornecedorService(FornecedorRepository repository) {

        this.repository = repository;
    }

    public static FornecedorService build() {
        FornecedorService result = instance.get();
        if (Objects.isNull( result )) {
            EntityManagerFactory factory = EntityManagerFactoryProvider.of( Main.PERSISTENCE_UNIT ).provide();
            EntityManager manager = factory.createEntityManager();
            FornecedorRepository repository = FornecedorRepository.build( manager );
            FornecedorService service = new FornecedorService( repository );
            if (instance.compareAndSet( null, service )) {
                result = service;
            } else {
                result = instance.get();
            }
        }
        return result;
    }

    @Override
    public List<Fornecedor> findAll() {
        return repository.findAll();
    }

    @Override
    public Fornecedor findById(Long id) {
        return repository.findById( id );
    }

    @Override
    public Fornecedor persist(Fornecedor fornecedor) {
        return repository.persist( fornecedor );
    }

    @Override
    public Fornecedor update(Long id, Fornecedor fornecedor) {
        var entidade = repository.findById( id );
        if (Objects.isNull( entidade )) return null;
        fornecedor.setId( id );
        return repository.update(fornecedor);
    }

    @Override
    public boolean delete(Fornecedor fornecedor) {
        return repository.delete(fornecedor);
    }
}
