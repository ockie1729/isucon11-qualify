package isucon11_qualify.isucondition.model;

import java.time.LocalDateTime;

public class User {
    private String jiaUserID;
    private LocalDateTime createdAt;

    public String getJiaUserID() {
        return jiaUserID;
    }

    public void setJiaUserID(String jiaUserID) {
        this.jiaUserID = jiaUserID;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
