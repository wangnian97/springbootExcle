package com.zslin.utils;

import com.alibaba.fastjson.JSON;

import com.zslin.model.HttpClientException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.HttpConnectionFactory;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultHttpResponseParserFactory;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.impl.io.DefaultHttpRequestWriterFactory;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author wangyanqing16
 */
public class HttpClientUtils {

    private static final Logger log = LoggerFactory.getLogger(HttpClientUtils.class);

    private static final int DEFAULT_HTTP_SOCKET_TIMEOUT = 3000;
    private static final int DEFAULT_HTTP_CONN_TIMEOUT = 5000;
    private static final int DEFAULT_HTTP_CONN_REQ_TIMEOUT = 2000;
    private static final int MAX_TOTAL = 300;
    private static final int DEFAULT_MAX_PER_ROUTE = 200;
    public static final String CHARSET = "UTF-8";
    public static final Charset DEFAULT_CHARSET = Charset.defaultCharset();
    private static final URLCodec URL_CODEC = new URLCodec(CHARSET);


    static PoolingHttpClientConnectionManager manager = null;

    static CloseableHttpClient httpClient = null;

    /**
     * 获取链接方法，关闭用 EntityUtils.consume(); EntityUtils.toString() 消费响应体
     * @return
     */
    public static synchronized CloseableHttpClient getHttpClient() {
        if (httpClient == null){
            //注册访问协议相关的socket工厂
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", new SSLConnectionSocketFactory(SSLContexts.createDefault(), NoopHostnameVerifier.INSTANCE))
                    .build();
            //HttpConnection 工厂， 配置写请求/解析响应处理器
            HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connectionFactory = new ManagedHttpClientConnectionFactory(
                    DefaultHttpRequestWriterFactory.INSTANCE,
                    DefaultHttpResponseParserFactory.INSTANCE
            );
            //DNS解析器
            DnsResolver dnsResolver = SystemDefaultDnsResolver.INSTANCE;
            //创建池化链接处理器
            manager = new PoolingHttpClientConnectionManager(socketFactoryRegistry, connectionFactory, dnsResolver);
            //默认为socket配置
            SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).build();
            manager.setDefaultSocketConfig(socketConfig);
            //整个连接池最大连接数
            manager.setMaxTotal(MAX_TOTAL);
            //每个路由最大连接数
            manager.setDefaultMaxPerRoute(DEFAULT_MAX_PER_ROUTE);
            //在从连接池获取链接时，链接不活跃多长时间需要进行一次验证
            manager.setValidateAfterInactivity(5 * 1000);

            RequestConfig defaultRequestConfig = RequestConfig.custom()
                    .setSocketTimeout(DEFAULT_HTTP_SOCKET_TIMEOUT)//设置链接超时时间 3s
                    .setConnectTimeout(DEFAULT_HTTP_CONN_TIMEOUT)//设置等待数据时间5s
                    .setConnectionRequestTimeout(DEFAULT_HTTP_CONN_REQ_TIMEOUT)//设置从链接池获取链接的等待超时时间
                    .setExpectContinueEnabled(false)
                    .build();

            httpClient = HttpClients.custom()
                    .setConnectionManager(manager)
                    .setConnectionManagerShared(false)
                    .evictIdleConnections(60, TimeUnit.SECONDS)
                    .evictExpiredConnections()
                    .setConnectionTimeToLive(60, TimeUnit.SECONDS)
                    .setDefaultRequestConfig(defaultRequestConfig)
                    .setConnectionReuseStrategy(DefaultConnectionReuseStrategy.INSTANCE)
                    .setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)
                    .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false))
                    .build();

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));
        }
        return httpClient;
    }

    private HttpClientUtils() {

    }

    /**
     * http get 方法调用
     * @param url 请求的url
     * @return string response
     */
    public static String httpGet(String url) throws IOException {
        return httpGet(url, null, 0, 0);
    }

    /**
     * http get 方法调用
     * @param url            请求的url
     * @param charset        编码
     * @param connectTimeout 连接超时
     * @param readTimeout    响应超时
     * @return string response
     */
    public static String httpGet(String url, String charset, int connectTimeout,
                                 int readTimeout) throws IOException {
        return httpGet(url, null, charset, connectTimeout, readTimeout);
    }

    /**
     * http get 方法调用
     *
     * @param url            请求的url
     * @param params         get参数
     * @param encode         编码
     * @param connectTimeout 连接超时
     * @param readTimeout    响应超时
     * @return string response
     */
    public static String httpGet(String url, Map<String, String> params, String encode, int connectTimeout,
                                 int readTimeout) throws IOException {
        HttpGet httpGet = new HttpGet();
        List<NameValuePair> formParams = setHttpParams(params);
        String param;
        if(StringUtils.isNotBlank(encode)&& CollectionUtils.isNotEmpty(formParams)){
            param = URLEncodedUtils.format(formParams, encode);
        }else{
            param = URLEncodedUtils.format(formParams, CHARSET);
        }
        httpGet.setURI(URI.create(url +  (StringUtils.isNotBlank(param)?("?" + param): "")));
        if (connectTimeout > 0 && readTimeout > 0) {
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(readTimeout)
                    .setConnectTimeout(connectTimeout)
                    .setConnectionRequestTimeout(connectTimeout)
                    .build();

            httpGet.setConfig(requestConfig);
        }

        if (log.isDebugEnabled()){
            log.debug("[HttpUtils Get] :", httpGet);
        }
        CloseableHttpResponse response = null;
        response = HttpClientUtils.getHttpClient().execute(httpGet);
        String resBody = "";
        if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
            EntityUtils.consume(response.getEntity());
        }else{
            resBody = EntityUtils.toString(response.getEntity());
        }
        return resBody;
    }

    /**
     * http get 方法调用
     *
     * @param url            请求的url
     * @param params         get参数
     * @return string response
     */
/*    public static String httpGet(String url, Map<String, String> params) throws IOException {
        return httpGet(url, params, CHARSET, 0, 0);
    }*/

    /**
     * 设置请求参数
     *
     * @param
     * @return
     */
    private static List<NameValuePair> setHttpParams(Map<String, String> paramMap) {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        if(paramMap != null){
            Set<Map.Entry<String, String>> set = paramMap.entrySet();
            for (Map.Entry<String, String> entry : set) {
                formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        return formparams;
    }

    /**
     * 请求webservice
     * @param url
     * @param xml
     * @return
     */
    public static String postWebServiceXml(String url, String xml) {
        CloseableHttpResponse response = null;
        try {
            HttpPost request = new HttpPost(url);
            if (!org.springframework.util.StringUtils.isEmpty(xml)) {
                request.setEntity(new StringEntity(xml));
            }
            request.addHeader("Content-Type", "application/soap+xml;charset=UTF-8");
            request.addHeader("Connection", "keep-alive");

            response = HttpClientUtils.getHttpClient().execute(request);
            int status = response.getStatusLine().getStatusCode();
            log.info("http postWebServiceXml url:" + url + ", params:" + xml + ", status:" + response.getStatusLine().getStatusCode());
            if (status != HttpStatus.SC_OK) {
                EntityUtils.consume(response.getEntity());
                throw new Exception("httpStatus code not ok");
            }else {
                HttpEntity resEntity = response.getEntity();
                return EntityUtils.toString(resEntity, CHARSET);
            }
        }catch (Exception e) {
            log.error("http postWebServiceXml url:" + url + ", params:" + xml, e);
            if (response != null && response.getEntity() != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            throw new HttpClientException(e);
        }
    }

    /**
     *
     * @param url
     * @param paramMap
     * @return
     * @throws IOException
     */
    public String postJson(String url, Map<String, String> paramMap) throws IOException {
        CloseableHttpResponse response = null;
        try {
            String reqBody = JSON.toJSONString(paramMap);
            StringEntity entity = new StringEntity(reqBody);
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            HttpPost post = new HttpPost(url);
            post.setEntity(entity);
            response = HttpClientUtils.getHttpClient().execute(post);
            if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
                EntityUtils.consume(response.getEntity());
                throw new HttpClientException("httpStatus code not ok");
            }else{
                String resBody = EntityUtils.toString(response.getEntity());
                return resBody;
            }
        } catch (IOException e) {
            e.printStackTrace();
            if(response != null && response.getEntity() != null){
                EntityUtils.consume(response.getEntity());
            }
            throw new HttpClientException(e);
        }
    }

    public static String httpGet(String url, Map<String, Object> paramMap) {
        return httpGet(url, paramMap, null);
    }

    /**
     * 处理httpGet  exception: HttpClientException
     * @param url
     * @param paramMap
     * @return
     */
    public static String httpGet(String url, Map<String, Object> paramMap, Map<String, String> headers) {
        CloseableHttpResponse response = null;
        try {
            List<NameValuePair> params = new ArrayList<>();
            for (Map.Entry postValue : paramMap.entrySet()) {
                params.add(new BasicNameValuePair(postValue.getKey().toString(), postValue.getValue().toString()));
            }
            String paramsStr = URLEncodedUtils.format(params, CHARSET);
            HttpGet httpGet = new HttpGet();
            if(headers != null){
                for (Map.Entry header : headers.entrySet()) {
                    httpGet.setHeader(header.getKey().toString(), header.getValue().toString());
                }
            }
            httpGet.setURI(URI.create(url + "?" + paramsStr));
            response = HttpClientUtils.getHttpClient().execute(httpGet);
            if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
                log.info("http httpGet url: {}, params: {}, status: {}", url, paramMap, response.getStatusLine().toString());
                EntityUtils.consume(response.getEntity());
                throw new HttpClientException("httpStatus code not ok");
            }else{
                String resBody = EntityUtils.toString(response.getEntity());
                log.info("http httpGet ok req {} res {}", paramMap, resBody);
                return resBody;
            }
        } catch (Exception e) {
            log.error("http httpGet error url: {}, params: {}", url, paramMap, e);
            if(response != null && response.getEntity() != null){
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            throw new HttpClientException(e);
        }
    }
}
