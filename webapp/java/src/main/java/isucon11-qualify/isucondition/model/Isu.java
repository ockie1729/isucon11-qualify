package isucondition.model;

import java.time.LocalDateTime;

public class Isu {
    private Integer id;
    private String jiaIsuUUID;
    private String name;
    //private Blob image;
    private String character;
    private String jiaUserID;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public String getJiaIsuUUID() {
        return jiaIsuUUID;
    }

    public void setJiaIsuUUID(String jiaIsuUUID) {
        this.jiaIsuUUID = jiaIsuUUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Blob getImage() {
//        return image;
//    }
//
//    public void setImage(Blob image) {
//        this.image = image;
//    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
