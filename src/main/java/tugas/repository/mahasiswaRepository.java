package tugas.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;
import tugas.dto.mahasiswa;

@ApplicationScoped
public class mahasiswaRepository implements PanacheRepository<mahasiswa> {


}
