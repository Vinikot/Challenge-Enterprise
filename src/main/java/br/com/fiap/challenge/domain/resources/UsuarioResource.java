package br.com.fiap.challenge.domain.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;


@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource implements Resource<UsuarioResource, Long>{
    @Override
    public Response findAll() {
        return null;
    }

    @Override
    public Response findById(Long id) {
        return null;
    }

    @Override
    public Response persist(UsuarioResource usuarioResource) {
        return null;
    }

    @Override
    public Response update(Long id, UsuarioResource usuarioResource) {
        return null;
    }

    @Override
    public Response delete(UsuarioResource usuarioResource) {
        return null;
    }
}
