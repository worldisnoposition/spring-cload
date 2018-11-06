package com.xiaohb.elasticsearch;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Logger;

public class ElasticSearchUtil {
    private static Logger LOGGER = Logger.getLogger("info");

    public static void main(String[] args) throws UnknownHostException {
        TransportClient client = null;
        try {
            System.out.println(checkHostAvailability("127.0.0.1", 9300));
            Settings settings = Settings.builder()
                    .put("client.transport.sniff", true)
                    .put("cluster.name", "elasticsearch").build();
            client = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
            SearchResponse response = client.prepareSearch().get();
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
            if (client != null) {
                client.close();
            }
        }
    }


    private static boolean checkHostAvailability(String host, int port) {
        boolean success = true;
        try {
            (new Socket(host, port)).close();
        } catch (UnknownHostException e) {
            success = false;
//            Logger.get.error("Unknown host {}: {}", host, e.getMessage());
        } catch (IOException e) {
            success = false;
//            logger.error("Service not running host {}: {}, {}", host, port, e.getMessage());
        }
        return success;
    }
}
