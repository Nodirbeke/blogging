package nod.pro.blogging.service;

import nod.pro.blogging.entity.Blog;
import nod.pro.blogging.entity.Comment;
import nod.pro.blogging.mapper.CommentMapper;
import nod.pro.blogging.model.request.comment.CommentCreateDTO;
import nod.pro.blogging.repository.BlogRepository;
import nod.pro.blogging.repository.CommentRepository;
import nod.pro.blogging.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommentServiceTest {

    @InjectMocks
    private CommentService commentService;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private CommentMapper commentMapper;

    @Mock
    private BlogRepository blogRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test for the create method
    @Test
    void testCreate() {
        CommentCreateDTO createDto = new CommentCreateDTO();
        // Set up your createDto properties here

        Comment comment = new Comment();
        // Set up your Comment entity here based on the createDto

        when(commentMapper.fromCreateDto(createDto)).thenReturn(comment);

        Blog blog = new Blog();
        // Set up your Blog entity here

        when(blogRepository.findById(createDto.getBlogId())).thenReturn(Optional.of(blog));

        when(commentRepository.save(comment)).thenReturn(comment);

        Long commentId = commentService.create(createDto);

        assertEquals(comment.getId(), commentId);
        verify(commentMapper, times(1)).fromCreateDto(createDto);
        verify(blogRepository, times(1)).findById(createDto.getBlogId());
        verify(commentRepository, times(1)).save(comment);
    }

    // Test for other methods can be similarly written.
    // Make sure to mock necessary repository methods and set up appropriate return values.

    // ...
}
