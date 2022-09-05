package project.bind.MenToMen.domain.file.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.bind.MenToMen.domain.file.dto.ImgUrlResponseDto;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FileService {

    public List<ImgUrlResponseDto> transferFile(List<MultipartFile> fileList) {

        return fileList.stream().map( file -> post(file))
                .collect(Collectors.toList());
    }

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
