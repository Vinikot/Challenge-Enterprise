package br.com.fiap.challenge.domain.resources;

import br.com.fiap.challenge.domain.entity.TipoDeItem;
import br.com.fiap.challenge.domain.service.TipoDeItemService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/tipo-de-item")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TipoDeItemResource implements Resource<TipoDeItem, Long> {

    @Context
    private UriInfo uriInfo;
    private TipoDeItemService service = TipoDeItemService.build();

    @GET
    @Override
    public Response findAll() {
        List<TipoDeItem> all = service.findAll();
        return Response.ok( all ).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id") Long id) {
        var tipoDeItem = service.findById( id );
        if (Objects.isNull( tipoDeItem )) return Response.status( 404 ).build();
        return Response.ok( tipoDeItem ).build();

    }

    @POST
    @Override
    public Response persist(TipoDeItem tipoDeItem) {
        var entity = service.persist( tipoDeItem );
        //Criando a URI da requisição
        UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        URI uri = ub.path( String.valueOf( entity.getId() ) ).build();
        return Response.created( uri ).entity( entity ).build();
    }

    @PUT
    @Path("/{id}")
    @Override
    public Response update(@PathParam("id") Long id, TipoDeItem tipoDeItem) {
        TipoDeItem updated = service.update( id, tipoDeItem );
        if (Objects.isNull( updated )) return Response.notModified().build();
        return Response.ok( updated ).build();
    }

    @DELETE
    @Override
    public Response delete(TipoDeItem tipoDeItem) {
        var updated = service.delete( tipoDeItem );
        if (updated) return Response.notModified().build();
        return Response.ok( updated ).build();
    }
}
