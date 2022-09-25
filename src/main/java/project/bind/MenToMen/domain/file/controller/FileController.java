package project.bind.MenToMen.domain.file.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.bind.MenToMen.domain.file.dto.ImgUrlResponseDto;
import project.bind.MenToMen.domain.file.service.FileService;
import project.bind.MenToMen.global.response.DataResponse;

import java.io.IOException;

@Api(tags = "File-Controller")
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @ApiOperation(value = "이미지 업로드 후 Url 받기", notes = "Form-data 형식 MultipartFile 업로드")
    @PostMapping("/upload")
    public ResponseEntity<DataResponse<ImgUrlResponseDto>> upload(@RequestPart MultipartFile file) throws IOException {
        return DataResponse.ok("파일 업로드 성공", fileService.upload(file));
    }
}
