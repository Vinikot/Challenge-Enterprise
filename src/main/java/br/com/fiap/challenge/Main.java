package br.com.fiap.challenge;

import br.com.fiap.challenge.domain.entity.*;
import br.com.fiap.challenge.domain.repository.*;
import br.com.fiap.challenge.infra.configuration.cors.CORSFilter;
import br.com.fiap.challenge.infra.configuration.jwt.JsTokenFilterNeeded;
import br.com.fiap.challenge.infra.database.EntityManagerFactoryProvider;
import br.com.fiap.challenge.infra.database.EntityManagerProvider;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;

//@ApplicationPath("/challengeAPI")
public class Main {
//    public static final String BASE_URI = "http://localhost/api/";
//
//    public static final String PERSISTENCE_UNIT = "oracle-home";
//
//    @PersistenceContext
//    static EntityManager manager;
//
//    public static HttpServer startServer() {
//
//
//        final ResourceConfig rc = new ResourceConfig()
//                // Configure container response filters (CORSFilter)
//                .register(CORSFilter.class)
//                // .register( UpdatableBCrypt.build(10))
//                // Configure container request filters (JsTokenFilterNeeded)
//                .register(JsTokenFilterNeeded.class)
//                .register( EntityManagerFactoryProvider.class )
//                .register( EntityManagerProvider.class )
//                .register(
//                        new AbstractBinder() {
//                            @Override
//                            protected void configure() {
//                                bindFactory(EntityManagerFactoryProvider.class)
//                                        .to(EntityManagerFactory.class)
//                                        .in(Singleton.class);
//                                bindFactory(EntityManagerProvider.class)
//                                        .to(EntityManager.class)
//                                        .in(RequestScoped.class);
//                            }
//                        }
//                ).register(EntityManagerFactoryProvider.of(PERSISTENCE_UNIT).provide())
//
//
//                .packages("br.com.fiap.petshop.domain.resources", "br.com.fiap.challenge.infra.security.resources");
//        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
//    }

    public static void main(String[] args) {
//        final HttpServer server = startServer();
//        System.out.printf( "iTechFutureAPI app started with endpoints available as %s%nHit Ctrl-C to stop it....%n", BASE_URI);
//        try {
//            System.in.read();
//            server.stop();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("oracle-fiap");
        EntityManager entityManager = factory.createEntityManager();
        EntityManager manager = entityManager;

        persistirDados(manager);

    }

    public static void persistirDados(EntityManager manager) {
        Usuario usuario = new Usuario();
        usuario.setCpf(98765432109L);
        usuario.setNome("Novo Nome do Usuário");
        usuario.setEmail("novo_usuario@example.com");
        usuario.setSenha("novaSenha123");
        usuario.setCepEntrega(54321678L);
        usuario.setLogradouro("Novo Endereço do Usuário");
        usuario.setNumLogradouro(54321);
        usuario.setComplemento("Novo Complemento do Endereço");
        usuario.setTelefone(1234567890L);

        UsuarioRepository usuarioRepository = UsuarioRepository.build(manager);
        usuarioRepository.persist(usuario);

        Compra compra = new Compra();
        compra.setCodContrato("Novo Código do Contrato");
        compra.setDataCompra(LocalDate.now());
        compra.setValorTotal(BigDecimal.valueOf(7500.75));
        compra.setUsuario(usuario);

        CompraRepository compraRepository = CompraRepository.build(manager);
        compraRepository.persist(compra);

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setCnpj(98765432101234L);
        fornecedor.setNome("Novo Nome do Fornecedor");
        fornecedor.setTelefone(123456789L);
        fornecedor.setReputacao("Ótima reputação");

        FornecedorRepository fornecedorRepository = FornecedorRepository.build(manager);
        fornecedorRepository.persist(fornecedor);

        TipoItem tipoItem = new TipoItem();
        tipoItem.setNome("Novo Nome do Tipo de Item");
        tipoItem.setDescricao("Nova Descrição do Tipo de Item");

        TipoItemRepository tipoItemRepository = TipoItemRepository.build(manager);
        tipoItemRepository.persist(tipoItem);

        Itens item = new Itens();
        item.setNome("Novo Nome do Item");
        item.setDescricao("Nova Descrição do Item");
        item.setTipoItem(tipoItem);

        ItensRepository itensRepository = ItensRepository.build(manager);
        itensRepository.persist(item);

        FornecedorItens fornecedorItens = new FornecedorItens();
        fornecedorItens.setQuantidade(BigDecimal.valueOf(15.5));
        fornecedorItens.setValor(BigDecimal.valueOf(150.0));
        fornecedorItens.setFornecedor(fornecedor);
        fornecedorItens.setItens(item);

        FornecedorItensRepository fornecedorItensRepository = FornecedorItensRepository.build(manager);
        fornecedorItensRepository.persist(fornecedorItens);

        CompraItens compraItens = new CompraItens();
        compraItens.setQuantidade(BigDecimal.valueOf(7.0));
        compraItens.setValor(BigDecimal.valueOf(70.0));
        compraItens.setCompra(compra);
        compraItens.setItens(item);

        CompraItensRepository compraItensRepository = CompraItensRepository.build(manager);
        compraItensRepository.persist(compraItens);
    }

}