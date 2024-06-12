package api.todo_list_api.VO;

import java.io.Serializable;
import java.util.Date;

public class TokenVO implements Serializable {
    
    private String email; 
    private Boolean authenticated; 
    private Date created; 
    private Date expiration;    
    private String accessToken; 
    private String refreshToken;

    

    
    public TokenVO(String email, Boolean authenticated, Date created, Date expiration, String accessToken,
            String refreshToken) {
        this.email = email;
        this.authenticated = authenticated;
        this.created = created;
        this.expiration = expiration;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public Boolean getAuthenticated() {
        return authenticated;
    }


    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }


    public Date getCreated() {
        return created;
    }


    public void setCreated(Date created) {
        this.created = created;
    }


    public Date getExpiration() {
        return expiration;
    }


    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }


    public String getAccessToken() {
        return accessToken;
    }


    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }


    public String getRefreshToken() {
        return refreshToken;
    }


    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((authenticated == null) ? 0 : authenticated.hashCode());
        result = prime * result + ((created == null) ? 0 : created.hashCode());
        result = prime * result + ((expiration == null) ? 0 : expiration.hashCode());
        result = prime * result + ((accessToken == null) ? 0 : accessToken.hashCode());
        result = prime * result + ((refreshToken == null) ? 0 : refreshToken.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TokenVO other = (TokenVO) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (authenticated == null) {
            if (other.authenticated != null)
                return false;
        } else if (!authenticated.equals(other.authenticated))
            return false;
        if (created == null) {
            if (other.created != null)
                return false;
        } else if (!created.equals(other.created))
            return false;
        if (expiration == null) {
            if (other.expiration != null)
                return false;
        } else if (!expiration.equals(other.expiration))
            return false;
        if (accessToken == null) {
            if (other.accessToken != null)
                return false;
        } else if (!accessToken.equals(other.accessToken))
            return false;
        if (refreshToken == null) {
            if (other.refreshToken != null)
                return false;
        } else if (!refreshToken.equals(other.refreshToken))
            return false;
        return true;
    }

    

}
