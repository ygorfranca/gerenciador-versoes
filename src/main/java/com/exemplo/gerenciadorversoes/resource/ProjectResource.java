package com.exemplo.gerenciadorversoes.resource;

import com.exemplo.gerenciadorversoes.dto.ProjectDTO;
import com.exemplo.gerenciadorversoes.service.ProjectService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import java.util.List;

@Path("/api/projects")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Projects", description = "Operações para gerenciar projetos")
public class ProjectResource {

    @Inject
    ProjectService projectService;

    @POST
    @Operation(summary = "Criar um novo projeto")
    public Response create(@Valid ProjectDTO projectDTO) {
        try {
            ProjectDTO created = projectService.create(projectDTO);
            return Response.status(Response.Status.CREATED).entity(created).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.CONFLICT)
                .entity(new ErrorResponse(e.getMessage())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorResponse("Erro interno do servidor")).build();
        }
    }

    @GET
    @Operation(summary = "Listar todos os projetos")
    public Response findAll(@QueryParam("name") String name) {
        try {
            List<ProjectDTO> projects;
            if (name != null && !name.trim().isEmpty()) {
                projects = projectService.findByName(name);
            } else {
                projects = projectService.findAll();
            }
            return Response.ok(projects).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorResponse("Erro interno do servidor")).build();
        }
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Buscar projeto por ID")
    public Response findById(@PathParam("id") Long id) {
        try {
            ProjectDTO project = projectService.findById(id);
            return Response.ok(project).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorResponse(e.getMessage())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorResponse("Erro interno do servidor")).build();
        }
    }

    @GET
    @Path("/slug/{slug}")
    @Operation(summary = "Buscar projeto por slug")
    public Response findBySlug(@PathParam("slug") String slug) {
        try {
            ProjectDTO project = projectService.findBySlug(slug);
            return Response.ok(project).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorResponse(e.getMessage())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorResponse("Erro interno do servidor")).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Atualizar projeto")
    public Response update(@PathParam("id") Long id, @Valid ProjectDTO projectDTO) {
        try {
            ProjectDTO updated = projectService.update(id, projectDTO);
            return Response.ok(updated).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorResponse(e.getMessage())).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.CONFLICT)
                .entity(new ErrorResponse(e.getMessage())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorResponse("Erro interno do servidor")).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Excluir projeto")
    public Response delete(@PathParam("id") Long id) {
        try {
            projectService.delete(id);
            return Response.noContent().build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorResponse(e.getMessage())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorResponse("Erro interno do servidor")).build();
        }
    }

    public static class ErrorResponse {
        public String message;

        public ErrorResponse(String message) {
            this.message = message;
        }
    }
}
