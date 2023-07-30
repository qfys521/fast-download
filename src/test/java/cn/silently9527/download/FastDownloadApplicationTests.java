package cn.silently9527.download;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLDecoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.UUID;

//@SpringBootTest
class FastDownloadApplicationTests {

//    private static final String fileURL = "https://qdct02.baidupcs.com/file/c5cd1e2dfdc7c4a1c014df7a350e5073?bkt=en-864c1d195a8f2f4189c3aef7b41c513e86a41ad2dcf94a970f37cd64b3447c2b39f303d4fb3b8273aaa39c31abdeac3a479d5003cea5f9f7169e291d9b7cc026&fid=1125129068-250528-963583740988337&time=1608347201&sign=FDTAXUGERQlBHSKfWqi-DCb740ccc5511e5e8fedcff06b081203-ix+6wMPjXCWMN8wDai/s7ohQJV8=&to=91&size=26074876&sta_dx=26074876&sta_cs=23532&sta_ft=pdf&sta_ct=7&sta_mt=7&fm2=MH,Qingdao,Anywhere,,sichuan,ct&ctime=1528100807&mtime=1528100807&resv0=0&resv1=0&resv2=rlim&resv3=5&resv4=26074876&vuk=1125129068&iv=0&htype=&randtype=&newver=1&newfm=1&secfm=1&flow_ver=3&pkey=en-fc3f9fc6f525bec2af4170653795fb5fe74b859bafdfb61ae1303c9c2b5a15564b22ece4e6a5d3c27dbcd47e5a2bced1b30570a821424a4a305a5e1275657320&expires=8h&rt=sh&r=706156877&vbdid=2772727088&fin=敏捷软件开发：原则、模式与实践%40www.java1234.com.pdf&fn=敏捷软件开发：原则、模式与实践%40www.java1234.com.pdf&rtype=1&dp-logid=8197804408107661170&dp-callid=0.1&hps=1&tsl=0&csl=0&fsl=0&csign=jV2uzzBE+xzEVao5X2v2k/k4Y3c=&so=0&ut=6&uter=4&serv=0&uc=3280231489&ti=51d76b5cdb7a87d26156643294208c80dbf04abab43fd24e80d4af97bfb69cf0&hflag=30&adg=c_26fc4f6159ca9bace5d32fb47d377ef9&reqlabel=250528_f_0185396431d133377ba2dd75c27ff1df_-1_2914b46ae17d3fc5480bfadc8dd896ac&by=themis";

    private RestTemplate restTemplate = buildRestTemplate(false);


    public static void main(String[] args) throws UnsupportedEncodingException {
        RestTemplate restTemplate = buildRestTemplate(false);

        HttpHeaders requestHeaders = new HttpHeaders();
//        requestHeaders.add("Accept","*/*");
//        requestHeaders.add("Host","qdct02.baidupcs.com");
//        requestHeaders.add("Cache-Control","no-cache");
//        requestHeaders.add("Pragma","no-cache");
//        requestHeaders.add("Connection","keep-alive");
//        requestHeaders.add("Sec-Fetch-Site","cross-site");
//        requestHeaders.add("Sec-Fetch-Mode","navigate");
//        requestHeaders.add("Sec-Fetch-Dest","empty");
//        requestHeaders.add("Origin","https://pan.baidu.com");
//        requestHeaders.add("Referer","https://pan.baidu.com");
//        requestHeaders.add("Referrer Policy","strict-origin-when-cross-origin");
//        requestHeaders.add("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");
//        requestHeaders.add("Accept-Encoding","gzip, deflate, br");
//        requestHeaders.add("Accept-Language","zh-CN,zh;q=0.9,en;q=0.8,zh-TW;q=0.7");

        String fileURL = "http://img.doutula.com/production/uploads/image/2017/10/19/20171019627498_uQtkcl.jpg";

//        HttpEntity<byte[]> requestEntity = new HttpEntity<>(null, requestHeaders);
//        ResponseEntity<byte[]> entity = restTemplate.exchange(fileURL, HttpMethod.GET, requestEntity, byte[].class);
        String fileName = getFileName("https://download.jetbrains.8686c.com/idea/ideaIU-2020.3.dmg");
        System.out.println(fileName);
    }

    private static String getFileName(String url) throws UnsupportedEncodingException {
        HttpEntity<String> requestEntity = new HttpEntity<>(null, new HttpHeaders());
        ResponseEntity<String> entity = buildRestTemplate(false).exchange(url, HttpMethod.HEAD, requestEntity, String.class);

        HttpHeaders headers = entity.getHeaders();
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        if (fileName.contains(".")) {
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            if (suffix.length() > 4 || suffix.contains("?")) {
                fileName = headers.getContentDisposition().getFilename();
                if (fileName == null || !fileName.contains("filename")) {
                    fileName = UUID.randomUUID().toString();
                } else {
                    fileName = fileName.substring(fileName.lastIndexOf("filename") + 9);
                }
            }
        } else {
            fileName = headers.getContentDisposition().getFilename();
            if (fileName == null || !fileName.contains("filename")) {
                fileName = UUID.randomUUID().toString();
            } else {
                fileName = fileName.substring(fileName.lastIndexOf("filename") + 9);
            }
        }
        fileName = URLDecoder.decode(fileName, "UTF-8");
        return fileName;
    }

    public void executeDownload(long byteStart, long byteEnd) {

//        restTemplate.execute(
//                fileURL,
//                HttpMethod.GET,
//                clientHttpRequest -> clientHttpRequest.getHeaders().set(
//                        "Range",
//                        String.format("bytes=%d-%d", byteStart, byteEnd)),
//                clientHttpResponse -> {
//                    StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(file, true));
//                    return file;
//                });

    }


    public static RestTemplate buildRestTemplate(boolean enableSslCheck) {
        final RestTemplate restTemplate = new RestTemplate();

        // sslIgnore
        SimpleClientHttpRequestFactory requestFactory;
        if (!enableSslCheck) {
            requestFactory = getUnsafeClientHttpRequestFactory();
        } else {
            requestFactory = new SimpleClientHttpRequestFactory();
        }

        // timeout
        requestFactory.setConnectTimeout(10000);
        requestFactory.setReadTimeout(10000);

        restTemplate.setRequestFactory(requestFactory);
        return restTemplate;
    }

    private static SimpleClientHttpRequestFactory getUnsafeClientHttpRequestFactory() {
        TrustManager[] byPassTrustManagers = new TrustManager[]{new X509TrustManager() {

            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }
        }};
        final SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, byPassTrustManagers, new SecureRandom());
            sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException(e);
        }

        return new SimpleClientHttpRequestFactory() {
            @Override
            protected void prepareConnection(HttpURLConnection connection,
                                             String httpMethod) throws IOException {
                super.prepareConnection(connection, httpMethod);
                if (connection instanceof HttpsURLConnection) {
                    ((HttpsURLConnection) connection).setSSLSocketFactory(
                            sslContext.getSocketFactory());
                }
            }
        };
    }

}
