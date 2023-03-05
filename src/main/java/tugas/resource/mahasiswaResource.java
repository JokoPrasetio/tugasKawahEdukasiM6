package tugas.resource;


import tugas.dto.mahasiswa;
import tugas.repository.mahasiswaRepository;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/mahasiswa")
public class mahasiswaResource {
    @Inject
    mahasiswaRepository mahasiswaRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMahasiswa(){
        return Response.ok(mahasiswaRepository.findAll()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMahasiswaById(@PathParam("id")Long id){
        mahasiswa mahasiswa = mahasiswaRepository.findById(id);
        if (mahasiswa == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(mahasiswa).build();
    }

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createMahasiswa(@Valid mahasiswa mahasiswa){
        mahasiswaRepository.persist(mahasiswa);
        return Response.ok(mahasiswa).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMahasiswa(@PathParam("id")Long id, @Valid mahasiswa mahasiswa ){
        tugas.dto.mahasiswa existingMahasiswa = mahasiswaRepository.findById(id);
        if(existingMahasiswa == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingMahasiswa.setNama(mahasiswa.getNama());
        existingMahasiswa.setNpm(mahasiswa.getNpm());
        existingMahasiswa.setJurusan(mahasiswa.getJurusan());
        existingMahasiswa.setAlamat(mahasiswa.getAlamat());
        existingMahasiswa.setEmail(mahasiswa.getEmail());
        mahasiswaRepository.persist(existingMahasiswa);
        return Response.ok(existingMahasiswa).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMahasiswa(@PathParam("id")Long id){
        mahasiswa mahasiswa = mahasiswaRepository.findById(id);
        if (mahasiswa == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        mahasiswaRepository.delete(mahasiswa);
        return Response.noContent().build();
    }


}
