import com.makersy.equipment.pojo.User;
import com.makersy.equipment.util.JsonUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by makersy on 2019
 */

public class Test {

    @org.junit.Test
    public void test(HttpServletRequest request, HttpServletResponse response) {
        User user = new User(1, "maker", "111", 1, 2);
        User user1 = new User(1, "maker", "111", 1, 2);
        User user2 = new User(1, "maker", "111", 1, 2);

        List<User> userList = Arrays.asList(user, user1, user2);
        String json = JsonUtil.list2string(userList);
        try {
            response.getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
