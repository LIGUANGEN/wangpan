package li.gen.serviceimpl;

import li.gen.dao.FileDao;
import li.gen.entity.File;
import li.gen.service.FileService;
import li.gen.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class FileServiceImpl implements FileService {
    @Autowired
    FileDao fileDao;
    private String fileName;
    @Override
    public void saveFile(java.io.File iofile, String username) {
        fileName = iofile.getName();
        File file = new File();
        file.setFileId(findMaxFileId()+1);
        file.setFileName(fileName.substring(0, fileName.lastIndexOf(".")));
        file.setFileType(fileName.substring(fileName.lastIndexOf(".") + 1));
        file.setUploadTime( TimeUtil.getTime());
        file.setUploadAuthor(username);
        fileDao.saveFile(file);
    }

    @Override
    public int findMaxFileId() {
        return fileDao.findMaxFileId();
    }

    @Override
    public List<File> selectFileByFileId(String uploadAuthor) {

        return fileDao.selectFileByFileId(uploadAuthor);
    }

    @Override
    public boolean deleteFile(int fileId, String username) {
        if(fileDao.deleteFile(fileId,username)==1){
         return  true;
        }
        return false;
    }

    @Override
    public File findFileByFileId(int fileId) {
        return fileDao.findFileByFileId(fileId);
    }

    @Override
    public String findFileNameByfileName(String fileName) {
        return fileDao.findFileNameByfileName(fileName);
    }
}
