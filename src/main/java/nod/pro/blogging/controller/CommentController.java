package nod.pro.blogging.controller;

import jakarta.servlet.http.HttpServletRequest;
import nod.pro.blogging.controller.base.AbstractController;
import nod.pro.blogging.model.request.comment.CommentCreateDTO;
import nod.pro.blogging.model.request.comment.CommentUpdateDTO;
import nod.pro.blogging.model.response.CommentDTO;
import nod.pro.blogging.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comment*")
public class CommentController extends AbstractController<CommentService> {

    private final BlogController blogController;

    public CommentController(CommentService service, BlogController blogController) {
        super(service);
        this.blogController = blogController;
    }

    @PostMapping(value = "/create")
    public String create(Model model, @ModelAttribute CommentCreateDTO dto) {
        model.addAttribute("commentId", service.create(dto));
        return "redirect:/blog/detail/" + dto.getBlogId();
    }

    @GetMapping(value = "/update/{id}")
    public String update(Model model, @PathVariable Long id) {
        model.addAttribute("comment", service.get(id));
        return "comment/update";
    }

    @PostMapping(value = "/update")
    public String update(Model model, @ModelAttribute CommentUpdateDTO dto) {
        CommentDTO update = service.update(dto);
        return "redirect:/blog/detail/" + update.getBlogId();
    }


    @PostMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable Long id) {
        CommentDTO commentDTO = service.get(id);
        service.delete(id);
        return blogController.blogDetail(model, commentDTO.getBlogId(),0);
    }

    @Transactional
    @PostMapping(value = "/unlike/{id}")
    public String unlike(@PathVariable Long id, HttpServletRequest request) {
        service.unlike(id);
        return "redirect:" + request.getHeader("Referer");
    }

    @Transactional
    @PostMapping(value = "/like/{id}")
    public String like(@PathVariable Long id, HttpServletRequest request) {
        service.like(id);
        return "redirect:" + request.getHeader("Referer");
    }

}
