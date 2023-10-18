package bean;
import javax.servlet.ServletContext;
import javax.servlet.http.*;
public class User implements HttpSessionBindingListener{
    private String uid;
    private String pwd;

    public void valueBound(HttpSessionBindingEvent event){
        //用户登录
        //用户向session中绑定数据时，在这里向应用域绑定数字
        ServletContext application = event.getSession().getServletContext();
        Object onlineUsers = application.getAttribute("onlineUsers");
        if(onlineUsers == null){
            application.setAttribute("onlineUsers",1);
        }else{
            int count = (Integer) onlineUsers;
            count++;
            application.setAttribute("onlineUsers",count);
        }

    }
    public void valueUnbound(HttpSessionBindingEvent event){
        //用户退出
        //取出数据，减1，再设置
        ServletContext application = event.getSession().getServletContext();
        int onlineUsers = (Integer) application.getAttribute("onlineUsers");
        onlineUsers--;
        application.setAttribute("onlineUsers",onlineUsers);
    }

    public User() {
    }

    public User(String uid, String pwd) {
        this.uid = uid;
        this.pwd = pwd;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
