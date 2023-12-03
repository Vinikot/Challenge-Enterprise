package br.com.fiap.challenge.domain.resources;

import br.com.fiap.challenge.domain.entity.Item;
import br.com.fiap.challenge.domain.service.ItemService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/item")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemResource implements Resource<Item, Long> {

    @Context
    private UriInfo uriInfo;
    private ItemService service = ItemService.build();

    @GET
    @Override
    public Response findAll() {
        List<Item> all = service.findAll();
        return Response.ok( all ).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id") Long id) {
        var item = service.findById( id );
        if (Objects.isNull( item )) return Response.status( 404 ).build();
        return Response.ok( item ).build();

    }

    @POST
    @Override
    public Response persist(Item item) {
        var entity = service.persist( item );
        //Criando a URI da requisição
        UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        URI uri = ub.path( String.valueOf( entity.getId() ) ).build();
        return Response.created( uri ).entity( entity ).build();
    }

    @PUT
    @Path("/{id}")
    @Override
    public Response update(@PathParam("id") Long id, Item item) {
        Item updated = service.update( id, item );
        if (Objects.isNull( updated )) return Response.notModified().build();
        return Response.ok( updated ).build();
    }

    @DELETE
    @Override
    public Response delete(Item item) {
        var updated = service.delete( item );
        if (updated) return Response.notModified().build();
        return Response.ok( updated ).build();
    }
}
