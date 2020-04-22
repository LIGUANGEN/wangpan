package li.gen.util;

import java.io.File;

public class FileUtil {
public static boolean deleteFile(File file){
    if(file.exists()&&file.isAbsolute()){
        return file.delete();
    }else{
        return false;
    }

}


}
