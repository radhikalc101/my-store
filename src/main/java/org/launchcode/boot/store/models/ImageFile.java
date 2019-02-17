package org.launchcode.boot.store.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "files")
@SequenceGenerator(name="imageSeq", initialValue=1, allocationSize=100)
public class ImageFile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imageSeq")
    private int id;

    private String fileName;

    private String fileType;

    private String userId;

    @Lob
    private byte[] data;

    public ImageFile() {

    }

    public ImageFile(String fileName, String fileType, String userId, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    // Getters and Setters (Omitted for brevity)
}