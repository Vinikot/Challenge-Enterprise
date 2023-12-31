package br.com.fiap.challenge.domain.resources;

import br.com.fiap.challenge.domain.entity.Fornecedor;
import br.com.fiap.challenge.domain.service.FornecedorService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/fornecedor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FornecedorResource implements Resource<Fornecedor, Long> {

    @Context
    private UriInfo uriInfo;
    private FornecedorService service = FornecedorService.build();

    @GET
    @Override
    public Response findAll() {
        List<Fornecedor> all = service.findAll();
        return Response.ok( all ).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id") Long id) {
        var fornecedor = service.findById( id );
        if (Objects.isNull( fornecedor )) return Response.status( 404 ).build();
        return Response.ok( fornecedor ).build();

    }

    @POST
    @Override
    public Response persist(Fornecedor fornecedor) {
        var entity = service.persist( fornecedor );
        //Criando a URI da requisição
        UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        URI uri = ub.path( String.valueOf( entity.getId() ) ).build();
        return Response.created( uri ).entity( entity ).build();
    }

    @PUT
    @Path("/{id}")
    @Override
    public Response update(@PathParam("id") Long id, Fornecedor fornecedor) {
        Fornecedor updated = service.update( id, fornecedor );
        if (Objects.isNull( updated )) return Response.notModified().build();
        return Response.ok( updated ).build();
    }

    @DELETE
    @Override
    public Response delete(Fornecedor fornecedor) {
        var updated = service.delete( fornecedor );
        if (updated) return Response.notModified().build();
        return Response.ok( updated ).build();
    }
}
