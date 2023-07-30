package nod.pro.blogging.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    private final BlogController blogController;

    public HomeController(BlogController blogController) {
        this.blogController = blogController;
    }

    @RequestMapping(value = {"", "/", "/home"}, method = RequestMethod.GET)
    public String list(Model model) {
        return blogController.list(0, model);
    }

}
