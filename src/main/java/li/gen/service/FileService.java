package li.gen.service;


import java.io.File;
import java.util.List;

public interface FileService {
    void saveFile(File file, String username);
    int findMaxFileId();
    List<li.gen.entity.File> selectFileByFileId(String uploadAuthor);
    boolean deleteFile(int fileId,String username);
    li.gen.entity.File findFileByFileId(int fileId);
    String findFileNameByfileName(String fileName);
}
