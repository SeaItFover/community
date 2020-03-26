package life.heart.community;

import life.heart.community.mapper.UserMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//(basePackages = "life.heart.community.mapper")  //去掉这个不报错空白页了
public class CommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunityApplication.class, args);
    }
    //private final UserMapper userMapper;

    /*public CommunityApplication(UserMapper userMapper) {
        this.userMapper = userMapper;
    }*/
}
