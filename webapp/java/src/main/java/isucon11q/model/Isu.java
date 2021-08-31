package isucon11q.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Isu implements Serializable {
    private Integer id;
    private String jiaIsuUUID;
    private String name;
    private byte[] image;
    private String character;
    private String jiaUserID;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
