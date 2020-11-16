package com.leyou.upload.service.impl;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.leyou.upload.service.UploadService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadServiceImpl implements UploadService {

    private static final List<String> CONTENT_TYPES =
            Arrays.asList("image/peg", "image/jpeg", "image/gif", "application/x-jpg");
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadServiceImpl.class);

    @Autowired
    private FastFileStorageClient storageClient;

    @Override
    public String uploadImage(MultipartFile file) {

        //get original filename  the contentType
        String originalFilename = file.getOriginalFilename();
        //get file's contentType
        String contentType = file.getContentType();
        //verify file's cotentType
        if (!CONTENT_TYPES.contains(contentType)){
            LOGGER.info("El content-type del documento no es válido: {}", originalFilename);
            return null;
        }

        try {
            //verify file's content with utils ImageIO. In case that return value is null, then the file is not an image
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream()); //bufferedImage: binary image
            if (bufferedImage == null){
                LOGGER.info("El documento no es válido: {}", originalFilename);
                return null;
            }
            //get file's suffix and save the file in server
            String ext = StringUtils.substringAfterLast(originalFilename, ".");
            StorePath storePath = this.storageClient.uploadFile(file.getInputStream(), file.getSize(), ext, null);

            //get url
            return "http://image.leyou.com/" +storePath.getFullPath();
        } catch (IOException e) {
            LOGGER.info("Server internal error: "+ originalFilename);
            e.printStackTrace();
        }

        return null;
    }
}
