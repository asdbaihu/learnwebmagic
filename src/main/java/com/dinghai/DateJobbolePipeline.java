package com.dinghai;

import org.apache.commons.codec.digest.DigestUtils;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.File;
import java.util.List;


public class DateJobbolePipeline extends JsonFilePipeline {


    public DateJobbolePipeline(String path) {
        super(path);
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        super.process(resultItems, task);
        String imgPath = this.path + File.separator + task.getUUID() + File.separator + DigestUtils.md5Hex(resultItems.getRequest().getUrl()) + File.separator;
        List<String> imgUrls = resultItems.get("imgUrls");
        for (String imgUrl : imgUrls) {
            try {
                System.out.println(imgUrl);
                String imgName = imgUrl.substring(imgUrl.lastIndexOf("/"), imgUrl.length());
                File file = this.getFile(imgPath + File.separator + imgName);
                System.out.println(file.getAbsolutePath());
                HttpFileDownload.download(file, imgUrl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
