package com.exemplo.gerenciadorversoes.resource;

import com.exemplo.gerenciadorversoes.dto.FeatureDTO;
import com.exemplo.gerenciadorversoes.service.FeatureService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import java.util.List;

@Path("/api/features")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Features", description = "Operações para gerenciar features")
public class FeatureResource {

    @Inject
    FeatureService featureService;

    @POST
    @Operation(summary = "Criar uma nova feature")
    public Response create(@Valid FeatureDTO featureDTO) {
        FeatureDTO created = featureService.create(featureDTO);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    @Operation(summary = "Listar todas as features")
    public Response findAll(@QueryParam("releaseId") Long releaseId,
                           @QueryParam("title") String title) {
        List<FeatureDTO> features;
        if (releaseId != null) {
            features = featureService.findByRelease(releaseId);
        } else if (title != null && !title.trim().isEmpty()) {
            features = featureService.findByTitle(title);
        } else {
            features = featureService.findAll();
        }
        return Response.ok(features).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Buscar feature por ID")
    public Response findById(@PathParam("id") Long id) {
        FeatureDTO feature = featureService.findById(id);
        return Response.ok(feature).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Atualizar feature")
    public Response update(@PathParam("id") Long id, @Valid FeatureDTO featureDTO) {
        FeatureDTO updated = featureService.update(id, featureDTO);
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Excluir feature")
    public Response delete(@PathParam("id") Long id) {
        featureService.delete(id);
        return Response.noContent().build();
    }
}
