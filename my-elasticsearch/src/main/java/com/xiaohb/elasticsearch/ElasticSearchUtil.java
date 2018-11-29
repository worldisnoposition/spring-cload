package com.xiaohb.elasticsearch;

import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ElasticSearchUtil {
    private static Logger LOGGER = Logger.getLogger("info");
    private static TransportClient client = null;

    public static void main(String[] args) {
        initClient();
        saveInfo();
        prepareSearch();
    }

    private static void saveInfo() {

        BulkRequestBuilder bulkRequest = client.prepareBulk();
//        bulkRequest.
        Map m = new HashMap();
        String jsonString = null;
        try {
            String path = "H:\\ssm-git-learn\\src\\main\\resource\\spider";        //要遍历的路径
            File file = new File(path);        //获取其file对象
            File[] fs = file.listFiles();    //遍历path下的文件和目录，放在File数组中
            for (File f : fs) {                    //遍历File[]数组
                if (!f.isDirectory()) {
                    byte[] strBuffer = null;
                    int flen = 0;
//                File xmlfile = new File("/data/local/getHomePage.xml");
                    try {
                        InputStream in = new FileInputStream(f);
                        flen = (int) f.length();
                        strBuffer = new byte[flen];
                        in.read(strBuffer, 0, flen);
                    } catch (FileNotFoundException e) {
// TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
// TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    jsonString = new String(strBuffer, "utf-8"); //构建String时，可用byte[]类型，

                    List<ZhiyePOJO> list = JSONObject.parseArray(jsonString, ZhiyePOJO.class);
//                    if (list != null && list.size() != 0) {
//                        create(jsonString);
//                    }
                    for(ZhiyePOJO z:list){
//                        System.out.println(z.getThirdNo());
//                        m.put(z.getThirdNo(),z);
//                        create(z);
                        bulkRequest.add(createBulk(z));
                    }

                }
            }
        } catch (Exception e) {
            System.out.println(jsonString);
            e.printStackTrace();
        }
        bulkRequest.get();
        System.out.println(m.size());
    }
    private static void bulkCreate(){

    }
    private static void prepareSearch() {
        SearchResponse response = client.prepareSearch().get();
        System.out.println(response);
    }

    private static IndexRequestBuilder createBulk(ZhiyePOJO zhiye) {
        return client.prepareIndex("work", "spider", zhiye.getThirdNo())
                .setSource(JSONObject.toJSONString(zhiye), XContentType.JSON);
    }

    private static void create(ZhiyePOJO zhiye) {
        client.prepareIndex("work", "spider", zhiye.getThirdNo())
                .setSource(JSONObject.toJSONString(zhiye), XContentType.JSON).get();
    }

    private static void initClient() {

        try {
            Settings settings = Settings.builder()
                    .put("client.transport.sniff", true)
                    .put("cluster.name", "elasticsearch").build();
            client = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
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
