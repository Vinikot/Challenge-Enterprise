package br.com.fiap.challenge.domain.resources;

import br.com.fiap.challenge.domain.entity.Compra;
import br.com.fiap.challenge.domain.service.CompraService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/compra")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompraResource implements Resource<Compra, Long> {

    @Context
    private UriInfo uriInfo;
    private CompraService service = CompraService.build();

    @GET
    @Override
    public Response findAll() {
        List<Compra> all = service.findAll();
        return Response.ok( all ).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id") Long id) {
        var compra = service.findById( id );
        if (Objects.isNull( compra )) return Response.status( 404 ).build();
        return Response.ok( compra ).build();

    }

    @POST
    @Override
    public Response persist(Compra compra) {
        var entity = service.persist( compra );
        //Criando a URI da requisição
        UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        URI uri = ub.path( String.valueOf( entity.getId() ) ).build();
        return Response.created( uri ).entity( entity ).build();
    }

    @PUT
    @Path("/{id}")
    @Override
    public Response update(@PathParam("id") Long id, Compra compra) {
        Compra updated = service.update( id, compra );
        if (Objects.isNull( updated )) return Response.notModified().build();
        return Response.ok( updated ).build();
    }

    @DELETE
    @Override
    public Response delete(Compra compra) {
        var updated = service.delete( compra );
        if (updated) return Response.notModified().build();
        return Response.ok( updated ).build();
    }
}
