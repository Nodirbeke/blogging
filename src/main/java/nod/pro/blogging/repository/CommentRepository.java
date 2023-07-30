package nod.pro.blogging.repository;

import nod.pro.blogging.entity.Comment;
import nod.pro.blogging.repository.base.BaseRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long>, BaseRepository {

    @Query(nativeQuery = true, value = "select count(*) from commnet")
    Long getSize();

//    Long getSize();

    @Query("select c from Comment c where c.blog.id = :id order by c.createdAt desc")
    List<Comment> findByBlogId(@Param("id") Long id);

    @Modifying
    @Query(value = "update Comment c set c.unusefulCount = (c.unusefulCount + 1) where c.id = :id")
    void unlike(@Param("id") Long id);

    @Modifying
    @Query(value = "update Comment c set c.usefullyCount = (c.usefullyCount + 1) where c.id = :id")
    void like(@Param("id") Long id);

    @Modifying
    @Query(value = "delete Comment c  where c.blog.id = :blogId")
    void deleteByIdBlogId(@Param("blogId") Long blogId);

    @Query("select c from Comment c where c.blog.id = :id order by c.createdAt desc")
    List<Comment> findAllBlogId(@Param("id") Long id, PageRequest request1);
}
