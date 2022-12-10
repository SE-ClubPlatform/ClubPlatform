package SW_Engineering.Group3.service;

import SW_Engineering.Group3.domain.ClubImgFile;
import SW_Engineering.Group3.domain.club.Club;
import SW_Engineering.Group3.repository.club.ClubImgFileRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FileService {

    private final ClubImgFileRepository clubImgFileRepository;

    /*
    @Transactional
    public void saveClubImage(Club club, MultipartFile image) throws IOException {

        //1. 이미지가 없으면 바로 반환
        if(image == null) {
            return;
        }

        //2. 이미지가 저장될 경로 설정
        String fileUrl = "C:\\Users\\김우진\\Desktop\\image\\";

        //3. 실제로 저장될 파일이름을 uuid로 설정하여 중복 방지
        String originalFileName = image.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() +
                originalFileName.substring(originalFileName.lastIndexOf("."));

        File destinationFile = new File(fileUrl + fileName);

        destinationFile.getParentFile().mkdirs();
        image.transferTo(destinationFile);

        //4. ClubImgFile 생성
        ClubImgFile clubImgFile = ClubImgFile.builder()
                .club(club)
                .originalFileName(originalFileName)
                .filename(fileName)
                .fileUrl(fileUrl).build();

        //5. club과 clubImgFile 테이블에 각각 저장
        club.setClubImgFile(clubImgFile);
        clubImgFileRepository.save(clubImgFile);
    }
    */

    public String getClubImage(Club club) throws IOException {
        ClubImgFile clubImgFile = clubImgFileRepository.findClubImgFileByClub(club);
        String base64Image = null;

        FileInputStream imageStream = new FileInputStream(clubImgFile.getFileUrl() + clubImgFile.getFilename());
        byte[] bytes = Base64.encodeBase64(imageStream.readAllBytes());
        base64Image = new String(bytes, "UTF-8");

        imageStream.close();

        return base64Image;
    }
}
