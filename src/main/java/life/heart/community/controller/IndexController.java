package life.heart.community.controller;

import life.heart.community.mapper.UserMapper;
import life.heart.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    /**
     * 当访问首页的时候，循环去看所有的cookie，找到名字是token的cookie
     * 然后去数据库中查是不是有cookie这条记录，如果有，就把user放大session
     * 里面。
     *
     * @param request
     * @return
     */
    @GetMapping("/")
    public String index(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.equals("token")) {
                    System.out.println("cookie is not null");
                    String token = cookie.getValue();
                    System.out.println("token = " + token);
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        System.out.println("user isnot null");
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        //model.addAttribute("name",name);
        return "index";
    }
}
