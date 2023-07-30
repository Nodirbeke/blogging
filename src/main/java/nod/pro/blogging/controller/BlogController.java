package nod.pro.blogging.controller;

import nod.pro.blogging.controller.base.AbstractController;
import nod.pro.blogging.criteria.BlogCriteria;
import nod.pro.blogging.criteria.CommentCriteria;
import nod.pro.blogging.model.request.blog.BlogCreateDTO;
import nod.pro.blogging.model.request.blog.BlogUpdateDTO;
import nod.pro.blogging.service.BlogService;
import nod.pro.blogging.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/blog")
public class BlogController extends AbstractController<BlogService> {

    private final CommentService commentService;

    public BlogController(BlogService service, CommentService commentService) {
        super(service);
        this.commentService = commentService;
    }

    @GetMapping(value = "/create")
    public String createPage() {
        return "blog/create";
    }

    @PostMapping(value = "/create")
    public String create(Model model, @ModelAttribute BlogCreateDTO dto) {
        model.addAttribute("blogId", service.create(dto));
        return list(0, model);
    }

    @GetMapping(value = "/update/{id}")
    public String updateOrganization(Model model, @PathVariable Long id) {
        model.addAttribute("blog", service.get(id));
        return "blog/update";
    }
//
//    @GetMapping(value = "/detail/{id}")
//    public String blogDetail(Model model, @PathVariable Long id) {
//        model.addAttribute("blog", service.get(id));
//        model.addAttribute("comments", commentService.getByBlogId(id));
//        model.addAttribute("totalPagers", commentService.getByBlogId(id));
//        model.addAttribute("currentPage", commentService.getByBlogId(id));
//        return "blog/detail";
//    }

    @GetMapping(value = "/detail/{id}")
    public String blogDetail(Model model, @PathVariable Long id, @RequestParam(name = "page", defaultValue = "0") Integer page) {
        model.addAttribute("blog", service.get(id));
        model.addAttribute("comments",
                commentService.getAllByBlogIdAndPagination(id, new CommentCriteria(5, page)));
        model.addAttribute("totalPages", commentService.getPagesSizeByBlogId(id));
        model.addAttribute("page", page);
        return "blog/detail";
    }

//    @GetMapping("/blog/details/{id}")
//    public String showBlogDetail(@PathVariable Long id, @RequestParam(name = "page", defaultValue = "0") Integer page, Model model) {
//        // Your code to retrieve the blog details by its ID and comments for the given page
//        // For example:
//        Blog blog = blogService.getBlogById(id);
//        List<Comment> comments = commentService.getCommentsByBlogId(blog.getId(), page, PAGE_SIZE);
//
//        // Calculate the total number of pages based on the number of comments and the page size
//        int totalComments = commentService.getTotalCommentsByBlogId(blog.getId());
//        int totalPages = (int) Math.ceil((double) totalComments / PAGE_SIZE);
//
//        model.addAttribute("blog", blog);
//        model.addAttribute("comments", comments);
//        model.addAttribute("page", page);
//        model.addAttribute("totalPages", totalPages);
//
//        return "blog-detail";
//    }


    @GetMapping(value = "/details/{id}")
    public String blogDetails(Model model, @PathVariable Long id) {
        service.addViewCount(id);
        return blogDetail(model, id, 0);
    }

    @PostMapping(value = "/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute BlogUpdateDTO dto) {
        service.update(dto);
        return "redirect:/blog/list";
    }


    @Transactional
    @PostMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/blog/list";
    }

    @PostMapping(value = "/check/{id}")
    public String check(@PathVariable Long id) {
        service.markAsCheckedByModerator(id);
        return "redirect:/blog/list";
    }


    //    @GetMapping(value = "/list")
//    public String list(Model model) {
//        model.addAttribute("blogs", service.getAll());
//        return "blog/list";
//    }
//
    @GetMapping(value = "/list")
    public String list(@RequestParam(defaultValue = "0") int page, Model model) {
        model.addAttribute("blogs", service.getAll(new BlogCriteria(10, page)));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", service.getTotalPages());
        return "blog/list";
    }


}



