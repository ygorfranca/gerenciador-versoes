package com.exemplo.gerenciadorversoes.resource;

import com.exemplo.gerenciadorversoes.dto.BugFixDTO;
import com.exemplo.gerenciadorversoes.service.BugFixService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import java.util.List;

@Path("/api/bugfixes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Bug Fixes", description = "Operações para gerenciar correções de bugs")
public class BugFixResource {

    @Inject
    BugFixService bugFixService;

    @POST
    @Operation(summary = "Criar uma nova correção de bug")
    public Response create(@Valid BugFixDTO bugFixDTO) {
        try {
            BugFixDTO created = bugFixService.create(bugFixDTO);
            return Response.status(Response.Status.CREATED).entity(created).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                .entity(new ProjectResource.ErrorResponse(e.getMessage())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ProjectResource.ErrorResponse("Erro interno do servidor")).build();
        }
    }

    @GET
    @Operation(summary = "Listar todas as correções de bugs")
    public Response findAll(@QueryParam("releaseId") Long releaseId,
                           @QueryParam("title") String title) {
        try {
            List<BugFixDTO> bugFixes;
            
            if (releaseId != null) {
                bugFixes = bugFixService.findByRelease(releaseId);
            } else if (title != null && !title.trim().isEmpty()) {
                bugFixes = bugFixService.findByTitle(title);
            } else {
                bugFixes = bugFixService.findAll();
            }
            
            return Response.ok(bugFixes).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                .entity(new ProjectResource.ErrorResponse(e.getMessage())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ProjectResource.ErrorResponse("Erro interno do servidor")).build();
        }
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Buscar correção de bug por ID")
    public Response findById(@PathParam("id") Long id) {
        try {
            BugFixDTO bugFix = bugFixService.findById(id);
            return Response.ok(bugFix).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                .entity(new ProjectResource.ErrorResponse(e.getMessage())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ProjectResource.ErrorResponse("Erro interno do servidor")).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Atualizar correção de bug")
    public Response update(@PathParam("id") Long id, @Valid BugFixDTO bugFixDTO) {
        try {
            BugFixDTO updated = bugFixService.update(id, bugFixDTO);
            return Response.ok(updated).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                .entity(new ProjectResource.ErrorResponse(e.getMessage())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ProjectResource.ErrorResponse("Erro interno do servidor")).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Excluir correção de bug")
    public Response delete(@PathParam("id") Long id) {
        try {
            bugFixService.delete(id);
            return Response.noContent().build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                .entity(new ProjectResource.ErrorResponse(e.getMessage())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ProjectResource.ErrorResponse("Erro interno do servidor")).build();
        }
    }
}
