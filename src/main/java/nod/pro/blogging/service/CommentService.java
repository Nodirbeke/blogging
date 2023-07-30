package nod.pro.blogging.service;

import nod.pro.blogging.criteria.CommentCriteria;
import nod.pro.blogging.entity.Blog;
import nod.pro.blogging.entity.Comment;
import nod.pro.blogging.mapper.CommentMapper;
import nod.pro.blogging.model.request.comment.CommentCreateDTO;
import nod.pro.blogging.model.request.comment.CommentUpdateDTO;
import nod.pro.blogging.model.response.CommentDTO;
import nod.pro.blogging.repository.BlogRepository;
import nod.pro.blogging.repository.CommentRepository;
import nod.pro.blogging.service.base.AbstractService;
import nod.pro.blogging.service.base.GenericCRUDService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService extends AbstractService<CommentRepository, CommentMapper>
        implements GenericCRUDService<CommentDTO, CommentCreateDTO, CommentUpdateDTO, CommentCriteria, Long> {

    private final BlogRepository blogRepository;

    protected CommentService(CommentRepository repository,
                             CommentMapper mapper,
                             BlogRepository blogRepository) {
        super(repository, mapper);
        this.blogRepository = blogRepository;
    }

    @Override
    public Long create(CommentCreateDTO createDto) {
        Comment comment = mapper.fromCreateDto(createDto);
        Optional<Blog> optionalBlog = blogRepository.findById(createDto.getBlogId());
        comment.setBlog(optionalBlog.orElseThrow(() -> new RuntimeException("Blog not found")));
        Comment save = repository.save(comment);
        return save.getId();
    }

    @Override
    public Void delete(Long id) {
        repository.deleteById(id);
        return null;
    }

    @Override
    public CommentDTO update(CommentUpdateDTO updateDto) {
        Optional<Comment> targetComment = repository.findById(updateDto.getId());
        Comment comment = mapper.fromUpdateDto(updateDto,
                targetComment.orElseThrow(() -> new RuntimeException("Comment not Found")));
        Comment updatedComment = repository.save(comment);
        return mapper.toDto(updatedComment);
    }

    @Override
    public List<CommentDTO> getAll() {
        List<Comment> all = repository.findAll();
        List<CommentDTO> commentDTOS = mapper.toDto(all);
        return commentDTOS;
    }

    @Override
    public List<CommentDTO> getAll(CommentCriteria criteria) {
        PageRequest request = PageRequest.of(criteria.getPage(), criteria.getSize());
        List<Comment> courses = repository.findAll(request).stream().toList();

        List<CommentDTO> courseGetDTOS = mapper.toDto(courses);

        return courseGetDTOS;
    }

    @Override
    public CommentDTO get(Long id) {
        Optional<Comment> byId = repository.findById(id);
        CommentDTO commentNotFound = mapper.toDto(byId.orElseThrow(() -> new RuntimeException("Comment not found")));
        return commentNotFound;
    }

    @Override
    public Long totalCount(CommentCriteria criteria) {
        Long size = repository.getSize();
        return size;
    }


    public List<CommentDTO> getByBlogId(Long id) {
        List<Comment> byBlogId = repository.findByBlogId(id);
        List<CommentDTO> commentDTOS = mapper.toDto(byBlogId);
        return commentDTOS;
    }

    public void unlike(Long id) {
        repository.unlike(id);
    }

    public void like(Long id) {
        repository.like(id);
    }

    public void deleteByBlogId(Long blogId) {
        repository.deleteByIdBlogId(blogId);
    }

    public Long getTotalPages() {
        long count = repository.count();
        Long totalPages = count / 5;
        if (count % 5 > 0) totalPages++;
        return totalPages;
    }

    public List<CommentDTO> getAllByBlogIdAndPagination(Long blogId, CommentCriteria commentCriteria) {
        PageRequest request = PageRequest.of(commentCriteria.getPage(), commentCriteria.getSize());
        List<Comment> comments = repository.findAllBlogId(blogId, request);

        List<CommentDTO> commentDTOS = mapper.toDto(comments);
        return commentDTOS;
    }

    public Long getPagesSizeByBlogId(Long id) {
        List<CommentDTO> byBlogId = getByBlogId(id);
        int size = byBlogId.size();
        Long totalPages = 0L;
        if (size > 0) {
            totalPages = (long) (size / 5);
            if (size % 5 > 0) totalPages++;
        }
        return totalPages;
    }
}
