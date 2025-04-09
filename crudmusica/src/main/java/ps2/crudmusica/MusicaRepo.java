package ps2.crudmusica;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicaRepo extends JpaRepository<Musica, Long> {
}