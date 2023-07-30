package nod.pro.blogging.repository;

import nod.pro.blogging.entity.Comment;
import nod.pro.blogging.entity.Theme;
import nod.pro.blogging.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ThemeRepository extends JpaRepository<Theme, Long>, BaseRepository {
    Optional<Theme> findByName(String theme);
}
