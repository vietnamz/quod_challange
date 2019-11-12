package downloader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class DownloaderTest {

    private String rootFolder = "src/test/resource/githubtest";

    private void deleteDirectoryRecursion(File file) throws IOException {
        if (file.isDirectory()) {
            File[] entries = file.listFiles();
            if (entries != null) {
                for (File entry : entries) {
                    deleteDirectoryRecursion(entry);
                }
            }
        }
        if (!file.delete()) {
            throw new IOException("Failed to delete " + file);
        }
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);


        // Make folder to save file
        File dir = new File(rootFolder);
        try {
            deleteDirectoryRecursion(dir);
        } catch (IOException ex) {
        }

        dir.mkdirs();
    }

    @Test()
    public void downloadResource_Success() {
        String url = "https://data.gharchive.org/2015-01-01-15.json.gz";
        Optional<String> outputFile = Downloader.downloadResource(url, rootFolder);

        Assert.assertEquals(outputFile.get(), "src/test/resource/githubtest/2015-01-01-15.json.gz");

    }

    @Test()
    public void downloadResource_Fail() {
        String url = "https://data.gharchive.org/2020-01-01-15.json.gz";
        Optional<String> outputFile = Downloader.downloadResource(url, rootFolder);
        if (!outputFile.isPresent()) {
            System.out.println("There is a problem to download file");
        }
    }
}
