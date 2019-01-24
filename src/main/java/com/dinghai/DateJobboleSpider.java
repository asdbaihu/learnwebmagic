package com.dinghai;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;


public class DateJobboleSpider implements PageProcessor {
    //    http://date.jobbole.com/
    private Site site = Site.me().setRetrySleepTime(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        Selectable selectable = html.css(".media-body .p-tit-single","text");
        String title = selectable.toString();
        if (null != title && !(title.contains("【") && title.contains("】"))) {
            page.putField("title",title );
            String desc = html.css(".p-entry p", "text").get();
            page.putField("desc", desc);
            List<String> imgList = html.css(".p-entry p img", "src").all();
            page.putField("imgUrls", imgList);

        }else{
            page.setSkip(true);
        }

//        page.addTargetRequests(page.getHtml().css("#pagination-next-page").links().regex("http://date.jobbole.com/.*").all());
        page.addTargetRequests(page.getHtml().css(".media .p-tit").links().regex("http://date.jobbole.com/.*").all());
        page.addTargetRequests(page.getHtml().css(".media-body .topic-info").links().regex("http://date.jobbole.com/.*").all());
    }

    @Override
    public Site getSite() {
        return site;
    }
}
