package com.dinghai;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import javax.management.JMException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws JMException {
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("192.168.199.102", 1080)));
        Spider spider = Spider.create(new DateJobboleSpider())
                .addUrl("http://date.jobbole.com/")
//                .addPipeline(new JsonFilePipeline("/home/dh/下载/date"))
                .addPipeline(new DateJobbolePipeline("/home/dh/下载/date"))
                .thread(5);
//        spider.setDownloader(httpClientDownloader);

        SpiderMonitor.instance().register(spider);

        spider.start();
    }
}
