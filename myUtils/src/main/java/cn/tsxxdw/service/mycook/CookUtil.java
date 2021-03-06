package cn.tsxxdw.service.mycook;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookUtil {
    /**
     * 
     * @param response
     * @param name
     * @param value
     */
    public static void addCook(HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(60*60*24*60);
        response.addCookie(cookie);
    }

    /**
     * 获取cook
     * @param request
     * @param name
     * @return
     */
    public static String getCook(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }
}
