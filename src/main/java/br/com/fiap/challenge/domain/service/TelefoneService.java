package br.com.fiap.challenge.domain.service;

import br.com.fiap.challenge.Main;
import br.com.fiap.challenge.domain.entity.Telefone;
import br.com.fiap.challenge.domain.repository.TelefoneRepository;
import br.com.fiap.challenge.infra.database.EntityManagerFactoryProvider;
import br.com.fiap.challenge.infra.security.service.PessoaFisicaService;
import br.com.fiap.challenge.infra.security.service.PessoaJuridicaService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class TelefoneService implements Service<Telefone, Long> {

    private static final AtomicReference<TelefoneService> instance = new AtomicReference<>();

    private final TelefoneRepository repository;

    private TelefoneService(TelefoneRepository repository) {

        this.repository = repository;
    }

    public static TelefoneService build() {
        TelefoneService result = instance.get();
        if (Objects.isNull( result )) {
            EntityManagerFactory factory = EntityManagerFactoryProvider.of( Main.PERSISTENCE_UNIT ).provide();
            EntityManager manager = factory.createEntityManager();
            TelefoneRepository repository = TelefoneRepository.build( manager );
            TelefoneService service = new TelefoneService( repository );
            if (instance.compareAndSet( null, service )) {
                result = service;
            } else {
                result = instance.get();
            }
        }
        return result;
    }

    private PessoaFisicaService pfService = PessoaFisicaService.of( Main.PERSISTENCE_UNIT );
    private PessoaJuridicaService pjService = PessoaJuridicaService.of( Main.PERSISTENCE_UNIT );

    @Override
    public List<Telefone> findAll() {
        return repository.findAll();
    }

    @Override
    public Telefone findById(Long id) {
        return repository.findById( id );
    }


    @Override
    public Telefone persist(Telefone telefone) {
        return repository.persist( telefone );
    }

    @Override
    public Telefone update(Long id, Telefone telefone) {
        var entidade = repository.findById( id );
        if (Objects.isNull( entidade )) return null;
        telefone.setId( id );
        return repository.update(telefone);
    }

    @Override
    public boolean delete(Telefone telefone) {
        return repository.delete(telefone);
    }
}