package api.todo_list_api.VO;

import java.io.Serializable;

public class RegisterUserVO implements Serializable {

    private String username;
    private String fullName;
    private String password;

    public RegisterUserVO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
