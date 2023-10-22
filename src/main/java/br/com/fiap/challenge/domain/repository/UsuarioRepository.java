package br.com.fiap.challenge.domain.repository;

import br.com.fiap.challenge.domain.entity.Usuario;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class UsuarioRepository implements Repository<Usuario, Long> {
    private static final AtomicReference<UsuarioRepository> instance = new AtomicReference<>();
    private final EntityManager manager;

    private UsuarioRepository(EntityManager manager) {
        this.manager = manager;
    }

    public static UsuarioRepository build(EntityManager manager) {
        UsuarioRepository result = instance.get();
        if (Objects.isNull(result)) {
            UsuarioRepository repo = new UsuarioRepository(manager);
            if (instance.compareAndSet(null, repo)) {
                result = repo;
            } else {
                result = instance.get();
            }
        }
        return result;
    }


    @Override
    public List<Usuario> findAll() {
        return manager.createQuery("From Usuario").getResultList();
    }

    @Override
    public Usuario findById(Long cpf) {
        return manager.find(Usuario.class, cpf);
    }


    @Override
    public Usuario persist(Usuario usuario) {
        manager.getTransaction().begin();
        manager.persist(usuario);
        manager.getTransaction().commit();
        return usuario;
    }

    @Override
    public Usuario update(Usuario usuario) {
        manager.getTransaction().begin();
        usuario = manager.merge(usuario);
        manager.getTransaction().commit();
        return usuario;
    }

    @Override
    public boolean delete(Usuario usuario) {
        manager.getTransaction().begin();
        manager.remove(usuario);
        manager.getTransaction().commit();
        return true;
    }
}
