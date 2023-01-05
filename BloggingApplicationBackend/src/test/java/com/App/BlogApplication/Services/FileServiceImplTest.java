package com.App.BlogApplication.Services;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {FileServiceImpl.class})
@ExtendWith(SpringExtension.class)
class FileServiceImplTest {
    @Autowired
    private FileServiceImpl fileServiceImpl;

    /**
     * Method under test: {@link FileServiceImpl#uploadImage(String, MultipartFile)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUploadImage() throws IOException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 1
        //       at com.App.BlogApplication.Services.FileServiceImpl.uploadImage(FileServiceImpl.java:28)
        //   See https://diff.blue/R013 to resolve this issue.

        fileServiceImpl.uploadImage("Path",
                new MockMultipartFile("Name", new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8"))));
    }

    /**
     * Method under test: {@link FileServiceImpl#getResource(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetResource() throws FileNotFoundException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.io.FileNotFoundException: Path\foo.txt (The system cannot find the path specified)
        //       at java.io.FileInputStream.open0(Native Method)
        //       at java.io.FileInputStream.open(FileInputStream.java:216)
        //       at java.io.FileInputStream.<init>(FileInputStream.java:157)
        //       at java.io.FileInputStream.<init>(FileInputStream.java:111)
        //       at com.App.BlogApplication.Services.FileServiceImpl.getResource(FileServiceImpl.java:48)
        //   See https://diff.blue/R013 to resolve this issue.

        fileServiceImpl.getResource("Path", "foo.txt");
    }
}

