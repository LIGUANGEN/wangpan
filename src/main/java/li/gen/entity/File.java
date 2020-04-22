package li.gen.entity;

import java.io.Serializable;

public class File implements Serializable {
    private int fileId;
    private String fileName;
    private String uploadAuthor;
    private String fileType;
    private String uploadTime;



    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUploadAuthor() {
        return uploadAuthor;
    }

    public void setUploadAuthor(String uploadAuthor) {
        this.uploadAuthor = uploadAuthor;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }


    @Override
    public String toString() {
        return "File{" +
                "fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", uploadAuthor='" + uploadAuthor + '\'' +
                ", fileType='" + fileType + '\'' +
                ", uploadTime='" + uploadTime + '\'' +
                '}';
    }
}
