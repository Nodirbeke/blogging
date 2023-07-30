package nod.pro.blogging.service;

import nod.pro.blogging.criteria.BlogCriteria;
import nod.pro.blogging.entity.Blog;
import nod.pro.blogging.entity.Theme;
import nod.pro.blogging.mapper.BlogMapper;
import nod.pro.blogging.model.request.blog.BlogCreateDTO;
import nod.pro.blogging.model.request.blog.BlogUpdateDTO;
import nod.pro.blogging.model.response.BlogDTO;
import nod.pro.blogging.repository.BlogRepository;
import nod.pro.blogging.service.base.AbstractService;
import nod.pro.blogging.service.base.GenericCRUDService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService extends AbstractService<BlogRepository, BlogMapper>
        implements GenericCRUDService<BlogDTO, BlogCreateDTO, BlogUpdateDTO, BlogCriteria, Long> {

    private final ThemeService themeService;

    private final CommentService commentService;

    public BlogService(BlogRepository repository,
                       BlogMapper mapper,
                       ThemeService themeService,
                       CommentService commentService) {
        super(repository, mapper);
        this.themeService = themeService;
        this.commentService = commentService;
    }

    @Override
    public Long create(BlogCreateDTO createDto) {
        Blog blog = mapper.fromCreateDto(createDto);
        String themeName = createDto.getTheme();
        Theme theme = themeService.getByNameOrCreateNew(themeName);
        blog.setTheme(theme);
        Blog save = repository.save(blog);
        return save.getId();
    }

    @Override
    public Void delete(Long id) {
        Blog blo = getBlog(id);
        commentService.deleteByBlogId(id);
        repository.delete(blo);
        return null;
    }

    @Override
    public BlogDTO update(BlogUpdateDTO updateDto) {
        Blog targetBlog = getBlog(updateDto.getId());
        Blog blog = mapper.fromUpdateDto(updateDto, targetBlog);
        Blog updatedBlog = repository.save(blog);
        return mapper.toDto(updatedBlog);
    }

    @Override
    public List<BlogDTO> getAll() {
        List<Blog> all = repository.findAll();
        List<BlogDTO> blogDTOS = mapper.toDto(all);
        return blogDTOS;
    }

    @Override
    public List<BlogDTO> getAll(BlogCriteria criteria) {
        PageRequest request =
                PageRequest.of(criteria.getPage(), criteria.getSize(), Sort.by("createdAt").descending());
        List<Blog> blogs = repository.findAll(request).stream().toList();

        List<BlogDTO> blogDTOS = mapper.toDto(blogs);
        return blogDTOS;
    }

    @Override
    public BlogDTO get(Long id) {
        Blog blog = getBlog(id);
        repository.save(blog);
        BlogDTO blogDTO = mapper.toDto(blog);
        return blogDTO;
    }

    @Override
    public Long totalCount(BlogCriteria criteria) {
        return repository.getSize();
    }

    public void addViewCount(Long id) {
        Blog blog = getBlog(id);
        blog.setView(blog.getView() + 1);
        repository.save(blog);
    }

    public void markAsCheckedByModerator(Long id) {
        Blog blog = getBlog(id);
        blog.setCheckedByModerator(!blog.getCheckedByModerator());
        repository.save(blog);
    }

    private Blog getBlog(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Blog Not Found"));
    }


    public Long getTotalPages() {
        long count = repository.count();
        Long totalPages = count / 10;
        if (count % 10 > 0) totalPages++;
        return totalPages;
    }
}
