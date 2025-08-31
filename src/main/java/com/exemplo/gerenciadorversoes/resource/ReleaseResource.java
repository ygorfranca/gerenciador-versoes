package com.exemplo.gerenciadorversoes.resource;

import com.exemplo.gerenciadorversoes.dto.ReleaseDTO;
import com.exemplo.gerenciadorversoes.service.ReleaseService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import java.util.List;

@Path("/api/releases")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Releases", description = "Operações para gerenciar releases")
public class ReleaseResource {

    @Inject
    ReleaseService releaseService;

    @POST
    @Operation(summary = "Criar um novo release")
    public Response create(@Valid ReleaseDTO releaseDTO) {
        ReleaseDTO created = releaseService.create(releaseDTO);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    @Operation(summary = "Listar todos os releases")
    public Response findAll(@QueryParam("recent") Integer recent) {
        List<ReleaseDTO> releases;
        if (recent != null && recent > 0) {
            releases = releaseService.findRecent(recent);
        } else {
            releases = releaseService.findAll();
        }
        return Response.ok(releases).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Buscar release por ID")
    public Response findById(@PathParam("id") Long id) {
        ReleaseDTO release = releaseService.findById(id);
        return Response.ok(release).build();
    }

    @GET
    @Path("/version/{versionId}")
    @Operation(summary = "Buscar release por versão")
    public Response findByVersion(@PathParam("versionId") Long versionId) {
        ReleaseDTO release = releaseService.findByVersion(versionId);
        return Response.ok(release).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Atualizar release")
    public Response update(@PathParam("id") Long id, @Valid ReleaseDTO releaseDTO) {
        ReleaseDTO updated = releaseService.update(id, releaseDTO);
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Excluir release")
    public Response delete(@PathParam("id") Long id) {
        releaseService.delete(id);
        return Response.noContent().build();
    }
}
