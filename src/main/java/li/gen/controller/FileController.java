package li.gen.controller;


import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import li.gen.service.FileService;
import li.gen.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.security.Principal;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/file")
public class FileController {
    @Autowired
    FileService fileService;

    @RequestMapping(value = "/fileUpload" , consumes = "multipart/form-data")
    public String fileUpload(@RequestParam("imageFile") MultipartFile imageFile, HttpServletRequest request){
//        获得存在springsecurity的用户名
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
                .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        String   rePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
        String   realPath = request.getSession().getServletContext().getRealPath("/");
        String filename = imageFile.getOriginalFilename();
        String filename1= filename.substring(0,filename.lastIndexOf("."));
        System.out.println(filename1);
        String fileType=filename.substring(filename.lastIndexOf("."));
        System.out.println(fileType);
        int  id=fileService.findMaxFileId();
        if (fileService.findFileNameByfileName(filename1)!=null){
               filename=filename1+id+fileType;
        }
        System.out.println(filename);
        File dir = new File(realPath+"upload/");//1.新建一个文件夹对象
        if(!dir.exists()){              //2.检查路径下upload文件夹是否存在
            dir.mkdirs();
        }

        System.out.println("文件上传到:"+realPath+"upload/"+ filename);

        File targetFile = new File(realPath+"upload/"+ filename);//3.在文件夹下新建一个filename文件的文件对象,此处新建文件应该新建在确切的物理路径下

        if(!targetFile.exists()){//4.判断真实路径下是否存在filename文件
            try {
                targetFile.createNewFile();//5.在真实路径下创建filename空文件
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            imageFile.transferTo(targetFile);//6.复制文件到真实路径下

        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        fileService.saveFile(targetFile,securityContextImpl.getAuthentication().getName());
        System.out.println("文件名"+securityContextImpl.getAuthentication().getName());
        System.out.println("真实路径:"+realPath+"upload/");
        System.out.println("相对路径:"+rePath+"upload/");

        return "redirect:/user/login";            //非安全目录下使用(可用)
        //return "redirect:"+RealPath()+"upload/"+filename;                 //重定向到真实地址(不可用)
        //return "redirect:http://localhost:8080/SpringMvcTest/upload/"+filename;
    }

    @RequestMapping("/fileDownload")
    public String  fileDownload(String fileName , HttpServletRequest request , HttpServletResponse response) throws UnsupportedEncodingException {
        //String filetype = fileType.trim();
        System.out.println(fileName);
//        String filename=new String(fileName.getBytes("ISO-8859-1"),"UTF-8");
//        System.out.println(new String(fileName.getBytes("ISO-8859-1"),"UTF-8"));
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type","text/html;charset=UTF-8");
        try {
            request.setCharacterEncoding("UTF-8");//2.确保请求的编码类型为UTF-8,不然文件下载后有可能因为类型不一致出现乱码
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        String ctxPath = request.getSession().getServletContext().getRealPath("/") + "upload/";
        String downLoadPath = ctxPath + fileName;
        System.out.println(downLoadPath);

        try {
            long fileLength = new File(downLoadPath).length();

            //3.设置响应头文件内容,文件类型、弹出下载对话框、文件大小
           // response.setContentType("application/x-msdownload");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(fileLength));

            bis = new BufferedInputStream(new FileInputStream(downLoadPath));//4.新建一个输入流缓存对象,并将文件输入流对象传递进去,将文件路径传递进文件输入流对象中，这是一个逐步处理的过程
            bos = new BufferedOutputStream(response.getOutputStream());//5.新建一个输出流缓存对象,将服务器响应输出流对象至于其中
            byte[] buff = new byte[2048];//6.新建一个缓存
            int bytesRead;              //7.内容字节总数
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                //8.输入到到buff缓存中,当文件为空是read()会return -1,否则返回读取的字节总数
                bos.write(buff, 0, bytesRead);//9.将buff的字节写到响应体的输出流中，输出流持续输出到客户端
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null)
                try {
                    bis.close();//10.关闭缓存输入流对象
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            if (bos != null)
                try {
                    bos.close();//10.关闭缓存输出流对象
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return null;

    }

       @RequestMapping("fileList")
       @ResponseBody
       public List<li.gen.entity.File> fileList(@RequestParam String uploadAuthor,HttpServletRequest request, HttpServletResponse response){
           System.out.println("查找的人"+uploadAuthor);
        List<li.gen.entity.File> list = fileService.selectFileByFileId(uploadAuthor);



        return list;
       }

       @RequestMapping("deleteFile")
       public ModelAndView deleteFile(int fileId, String username, HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
           String result="";
           String ctxPath = request.getSession().getServletContext().getRealPath("/") + "upload/";
           System.out.println(ctxPath);
           li.gen.entity.File file1 = fileService.findFileByFileId(fileId);

           if (file1==null){
              result="error1";
           }else {
               String downLoadPath = ctxPath + file1.getFileName()+"."+file1.getFileType();
               System.out.println(downLoadPath);
               System.out.println(fileId + username);
               File file = new File(downLoadPath);
               System.out.println(file);

               if (fileService.deleteFile(fileId, username)) {
                   if (FileUtil.deleteFile(file)) {
                       result = "";
                   } else {
                       result = "error";
                   }

               } else {
                   result = "error";
               }
           }
         mv.addObject("result",result);
         mv.setViewName("index");
         return mv;
       }

      @RequestMapping("share")
    public ModelAndView share(@RequestParam int fileId){
        ModelAndView mv = new ModelAndView();
        mv.addObject("fileId",fileId);
        mv.setViewName("share");
        return mv;
      }
      @RequestMapping(value="createUrl")
      @ResponseBody
      public Map<String,String> createUrl(int fileId, HttpServletRequest request){
        li.gen.entity.File file=fileService.findFileByFileId(fileId);
        Map<String,String> map=new HashMap<String,String>();

        String name = file.getFileName()+"."+file.getFileType();
        String  url="http://localhost:8080/file/fileDownload?fileName="+name;
        map.put("url",url);
          System.out.println(url);
        return map ;
      }
}
