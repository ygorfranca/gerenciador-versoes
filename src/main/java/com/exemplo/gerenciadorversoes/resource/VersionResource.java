package com.exemplo.gerenciadorversoes.resource;

import com.exemplo.gerenciadorversoes.dto.VersionDTO;
import com.exemplo.gerenciadorversoes.service.VersionService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import java.util.List;

@Path("/api/versions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Versions", description = "Operações para gerenciar versões")
public class VersionResource {

    @Inject
    VersionService versionService;

    @POST
    @Operation(summary = "Criar uma nova versão")
    public Response create(@Valid VersionDTO versionDTO) {
        VersionDTO created = versionService.create(versionDTO);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    @Operation(summary = "Listar todas as versões")
    public Response findAll(@QueryParam("projectId") Long projectId, 
                           @QueryParam("status") String status) {
        List<VersionDTO> versions;
        if (projectId != null) {
            versions = versionService.findByProject(projectId);
        } else if (status != null && !status.trim().isEmpty()) {
            versions = versionService.findByStatus(status);
        } else {
            versions = versionService.findAll();
        }
        return Response.ok(versions).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Buscar versão por ID")
    public Response findById(@PathParam("id") Long id) {
        VersionDTO version = versionService.findById(id);
        return Response.ok(version).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Atualizar versão")
    public Response update(@PathParam("id") Long id, @Valid VersionDTO versionDTO) {
        VersionDTO updated = versionService.update(id, versionDTO);
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Excluir versão")
    public Response delete(@PathParam("id") Long id) {
        versionService.delete(id);
        return Response.noContent().build();
    }
}
