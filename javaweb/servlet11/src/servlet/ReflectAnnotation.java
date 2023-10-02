package servlet;
import javax.servlet.annotation.*;
public class ReflectAnnotation {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> welcomeClass = Class.forName("servlet.WelcomeServlet");

        if(welcomeClass.isAnnotationPresent(WebServlet.class)){
            //
            WebServlet webServletAnnotation = welcomeClass.getAnnotation(WebServlet.class);
            //
            String[] values = webServletAnnotation.value();
            for(int i=0;i<values.length;i++){
                System.out.println(values[i]);
            }
        }

    }
}
