package com.cartisan.mall.file.util;

import com.cartisan.mall.file.FastDFSFile;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @author colin
 */
public class FastDFSClient {
    static {
        final String path = new ClassPathResource("fastdfs-client.conf").getPath();
        try {
            ClientGlobal.init(path);
        } catch (MyException | IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] upload(FastDFSFile file) {
        try {
            final TrackerClient trackerClient = new TrackerClient();
            final TrackerServer trackerServer = trackerClient.getConnection();
            final StorageClient storageClient = new StorageClient(trackerServer, null);

            final NameValuePair[] meta_list = {
                    new NameValuePair(file.getAuthor()),
                    new NameValuePair(file.getName())
            };

            final String[] strings = storageClient.upload_file(file.getContent(), file.getExt(), meta_list);
            return strings;
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getTrackerUrl() {
        try {
            final TrackerClient trackerClient = new TrackerClient();
            final TrackerServer trackerServer = trackerClient.getConnection();
            final String hostString = trackerServer.getInetSocketAddress().getHostString();

            final int g_tracker_http_port = ClientGlobal.getG_tracker_http_port();

            return "http://"+hostString+":"+g_tracker_http_port;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
