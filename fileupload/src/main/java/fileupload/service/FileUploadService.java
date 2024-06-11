package fileupload.service;

import java.io.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
  private static String SAVE_PATH = "/fileupload-files"; // 배포되는 리눅스에 맞게 경로 설정 필요

  public String restore(MultipartFile file) {
    String url = null;
    // 1. 저장할 directory 생성
    File uploadDirectory = new File(SAVE_PATH);
    if (!uploadDirectory.exists()) {
      uploadDirectory.mkdirs(); // mkdirs(), 하나는 mkdir()
    }

    if (file.isEmpty()) {
      return url; //
    }

    String originFilename = file.getOriginalFilename();
    Long fileSize = file.getSize();
    System.out.println("########" + originFilename);
    System.out.println("########" + fileSize);
    return url;
  }

}
