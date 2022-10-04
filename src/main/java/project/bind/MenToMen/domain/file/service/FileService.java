package project.bind.MenToMen.domain.file.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.bind.MenToMen.domain.file.dto.ImgUrlResponseDto;
import project.bind.MenToMen.global.error.CustomError;
import project.bind.MenToMen.global.error.ErrorCode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileService {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3Client s3Client;

    public List<ImgUrlResponseDto> upload(List<MultipartFile> files) throws IOException {

        List<ImgUrlResponseDto> imgUrlResponseDtoList = new ArrayList<>();

        for (MultipartFile file : files) {

            extensionCheck(file.getOriginalFilename());

            String ext = file.getOriginalFilename().split("\\.")[1];

            String s3FileName = UUID.randomUUID() + "." + ext;

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());

            s3Client.putObject(bucket, s3FileName, file.getInputStream(), metadata);

            imgUrlResponseDtoList.add(new ImgUrlResponseDto(s3Client.getUrl(bucket, s3FileName).toString()));
        }
        return imgUrlResponseDtoList;
    }

    private void extensionCheck(String imgUrl) {

        final String[] permitExtension = {".jpg", ".jpeg", ".png"};
        boolean check = false;

        for (String permit : permitExtension) {
            if(imgUrl.contains(permit)) {
                check = true;
                break;
            }
        }
        if (check == false) throw CustomError.of(ErrorCode.WRONG_FILE);
    }

    public void update(List<ImgUrlResponseDto> updateUrls, String originalUrls) {

        List<String> updateFileNameList = updateUrls.stream().map(imgUrlResponseDto -> imgUrlResponseDto.getImgUrl().split("m/")[1]).collect(Collectors.toList());
        List<String> originalFileNameList = List.of(originalUrls.split("///")).stream().map(url -> url.split("m/")[1]).collect(Collectors.toList());
//        List<String> originalUrlList = List.of(originalUrls.split("///"));
//        List<String> originalFileNameList = originalUrlList.stream().map(url -> url.split("m/")[1]).collect(Collectors.toList());

        for (String originalFileName : originalFileNameList) {
            boolean checkUrl = false;
            for (String updateFileName : updateFileNameList) {
                if(originalFileName.equals(updateFileName)) {
                    checkUrl = true;
                    break;
                }
            }
            if (checkUrl == false) s3Client.deleteObject(new DeleteObjectRequest(bucket, originalFileName));
        }
    }

    public void delete(String imgUrl) {
        String fileName = imgUrl.split("m/")[1];
        System.out.println(fileName);
        s3Client.deleteObject(new DeleteObjectRequest(bucket, fileName));
    }
}
