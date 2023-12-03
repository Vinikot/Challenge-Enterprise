package br.com.fiap.challenge.domain.repository;

import br.com.fiap.challenge.domain.entity.Telefone;
import br.com.fiap.challenge.infra.security.entity.Pessoa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class TelefoneRepository implements Repository<Telefone, Long> {

    private static final AtomicReference<TelefoneRepository> instance = new AtomicReference<>();
    private final EntityManager manager;

    private TelefoneRepository(EntityManager manager) {
        this.manager = manager;
    }

    public static TelefoneRepository build(EntityManager manager) {
        TelefoneRepository result = instance.get();
        if (Objects.isNull( result )) {
            TelefoneRepository repo = new TelefoneRepository( manager );
            if (instance.compareAndSet( null, repo )) {
                result = repo;
            } else {
                result = instance.get();
            }
        }
        return result;
    }


    @Override
    public List<Telefone> findAll() {
        return null;
    }

    @Override
    public Telefone findById(Long id) {
        return null;
    }


    @Override
    public Telefone persist(Telefone telefone) {
        return null;
    }

    @Override
    public Telefone update(Telefone telefone) {

        //Será que existe documento com o número informado?
        Telefone tel = manager.find( Telefone.class, telefone.getId() );
        if (Objects.isNull( tel )) return null;

        //Não posso confiar no usuário preciso pegar os dados do Dono:
        manager.getTransaction().begin();

        if (Objects.nonNull( telefone.getPessoa() )) {
            Query query = manager.createQuery( "From Pessoa p where p.id =:id" );
            query.setParameter( "id", telefone.getPessoa().getId() );
            List<Pessoa> list = query.getResultList();
            list.forEach( tel::setPessoa );

            if (Objects.nonNull( telefone.getNumero() ) && !telefone.getNumero().equals( "" )) {
                tel.setNumero( telefone.getNumero() );
            }

            if ( telefone.getDdd()>0) {
                tel.setDdd( telefone.getDdd() );
            }

        }

        telefone = manager.merge( tel );
        manager.getTransaction().commit();
        return telefone;
    }

    @Override
    public boolean delete(Telefone telefone) {
        manager.remove( telefone );
        return true;
    }
}