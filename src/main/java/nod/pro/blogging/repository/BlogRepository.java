package nod.pro.blogging.repository;

import nod.pro.blogging.entity.Blog;
import nod.pro.blogging.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BlogRepository extends BaseRepository, JpaRepository<Blog, Long> {

    @Query(nativeQuery = true, value = "select count(*) from blog")
    Long getSize();

}
