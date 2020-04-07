package life.heart.community.controller;

import life.heart.community.dto.PaginationDTO;
import life.heart.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    /**
     * 当访问首页的时候，循环去看所有的cookie，找到名字是token的cookie
     * 然后去数据库中查是不是有cookie这条记录，如果有，就把user放大session
     * 里面。
     *
     * @param
     * @return
     */
    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size,
                        @RequestParam(name = "search", required = false) String search) {
        PaginationDTO pagination = questionService.list(search,page,size);
        model.addAttribute("pagination", pagination);
        return "index";
    }
}
