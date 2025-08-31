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
        ProjectDTO created = projectService.create(projectDTO);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    @Operation(summary = "Listar todos os projetos")
    public Response findAll(@QueryParam("name") String name) {
        List<ProjectDTO> projects;
        if (name != null && !name.trim().isEmpty()) {
            projects = projectService.findByName(name);
        } else {
            projects = projectService.findAll();
        }
        return Response.ok(projects).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Buscar projeto por ID")
    public Response findById(@PathParam("id") Long id) {
        ProjectDTO project = projectService.findById(id);
        return Response.ok(project).build();
    }

    @GET
    @Path("/slug/{slug}")
    @Operation(summary = "Buscar projeto por slug")
    public Response findBySlug(@PathParam("slug") String slug) {
        ProjectDTO project = projectService.findBySlug(slug);
        return Response.ok(project).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Atualizar projeto")
    public Response update(@PathParam("id") Long id, @Valid ProjectDTO projectDTO) {
        ProjectDTO updated = projectService.update(id, projectDTO);
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Excluir projeto")
    public Response delete(@PathParam("id") Long id) {
        projectService.delete(id);
        return Response.noContent().build();
    }
}
