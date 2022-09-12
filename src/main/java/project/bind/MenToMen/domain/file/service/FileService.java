package project.bind.MenToMen.domain.file.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.bind.MenToMen.domain.file.dto.ImgUrlResponseDto;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3Client s3Client;

    public ImgUrlResponseDto upload(MultipartFile file) throws IOException {

        String s3FileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        s3Client.putObject(bucket, s3FileName, file.getInputStream(), metadata);

        return new ImgUrlResponseDto(s3Client.getUrl(bucket, s3FileName).toString());
    }
}
