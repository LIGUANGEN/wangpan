package li.gen.dao;

import li.gen.entity.File;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileDao {
    void  saveFile(@Param("File") File file);
    File findFileByFileId(int fileId);
    List<File> selectFileByFileId(String uploadAuthor);
    int findMaxFileId();
    int deleteFile(@Param("fileId")int fileId,@Param("uploadAuthor")String uploadAuthor);
    String findFileNameByfileName(String fileName);
}
