package cn.silently9527.download.download;

import cn.silently9527.download.ext.ByteArrayResponseExtractor;
import cn.silently9527.download.support.DownloadProgressPrinter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class SimpleDownloader extends AbstractDownloader {

    public SimpleDownloader(DownloadProgressPrinter downloadProgressPrinter) {
        super(downloadProgressPrinter);
    }

    public SimpleDownloader() {
        super(DownloadProgressPrinter.defaultDownloadProgressPrinter());
    }

    @Override
    protected void doDownload(String fileURL, String dir, String fileName, HttpHeaders headers) throws IOException {
        String filePath = dir + File.separator + fileName;
        byte[] body = restTemplate.execute(fileURL, HttpMethod.GET, null,
                new ByteArrayResponseExtractor(downloadProgressPrinter));
        Files.write(Paths.get(filePath), Objects.requireNonNull(body));
    }

}
