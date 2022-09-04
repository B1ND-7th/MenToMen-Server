package project.bind.MenToMen.domain.file.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.bind.MenToMen.domain.file.dto.ImgUrlResponseDto;

import java.io.File;
import java.io.IOException;

@Service
@Slf4j
public class FileService {

    public ImgUrlResponseDto post(MultipartFile file) {

        if( !file.isEmpty() ) {
            log.info("file name = " + file.getOriginalFilename());
            try {
                file.transferTo(new File(file.getOriginalFilename()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ImgUrlResponseDto("/static/" + file.getOriginalFilename());
    }
}
