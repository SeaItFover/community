package life.heart.community.controller;

import life.heart.community.dto.PaginationDTO;
import life.heart.community.dto.QuestionDTO;
import life.heart.community.mapper.QuestionMapper;
import life.heart.community.mapper.UserMapper;
import life.heart.community.model.Question;
import life.heart.community.model.User;
import life.heart.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    /**
     * 当访问首页的时候，循环去看所有的cookie，找到名字是token的cookie
     * 然后去数据库中查是不是有cookie这条记录，如果有，就把user放大session
     * 里面。
     *
     * @param request
     * @return
     */
    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        PaginationDTO pagination = questionService.list(page,size);
        model.addAttribute("pagination", pagination);
        return "index";
    }
}
