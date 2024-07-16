package by.finby.assignmentTest.utill;

import by.finby.assignmentTest.exceptions.PhotoManagerException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class PhotoManager {

    private final String FILE_PATH =
            "assignmentTest\\src\\main\\resources\\static\\images\\source\\105994.jpg";
    private final String REQUEST_URL = "https://api.remove.bg/v1.0/removebg";

    private final String DEFAULT_TOKEN = "qw17YuVTxyFdaGVVthB4kFiW";
    private String TOKEN = "qw17YuVTxyFdaGVVthB4kFiW";

    public PhotoManager() {
    }

    public List<String> managePhoto(List<String> photoList) {
        List<String> urlManagedPhotoList = new ArrayList<>();
        photoList.forEach(photo ->
                urlManagedPhotoList.add(makeRequestAndGetPhotoFromResponse(photo)));
        return urlManagedPhotoList;
    }

    private String makeRequestAndGetPhotoFromResponse(String url) {
        Response response;
        String newFilePath = newFileNameGenerator();
        try {
            response = Request.Post(REQUEST_URL)
                    .addHeader("X-Api-Key", TOKEN)
                    .body(
                            MultipartEntityBuilder.create()
                                    .addBinaryBody("image_file", new File(url))
                                    .addTextBody("size", "auto")
                                    .build())
                    .execute();
        } catch (IOException e) {
            throw new PhotoManagerException("There was an exception during photo managing " + Arrays.toString(e.getStackTrace()));
        }
        try {
            response.saveContent(new File(newFilePath));
        } catch (IOException e) {
            throw new PhotoManagerException("There was an exception during managed photo saving " + Arrays.toString(e.getStackTrace()));

        }
        return newFilePath.substring(40);
    }

    private String newFileNameGenerator() {
        String prefix = "assignmentTest/src/main/resources/static/images/managed/";
        String postfix = ".jpg";
        Random random = new Random();
        String imageCode = String.valueOf(random.nextInt(Integer.MAX_VALUE));
        return prefix + imageCode + postfix;
    }

    public String getTOKEN() {
        return TOKEN;
    }

    public void setTOKEN(String TOKEN) {
        this.TOKEN = TOKEN;
    }

    public void setDEFAULT_TOKEN() {
        this.TOKEN = DEFAULT_TOKEN;
    }
}
