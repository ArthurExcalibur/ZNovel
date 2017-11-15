package com.excalibur.znovel.util;


import com.excalibur.znovel.bean.ZhangJie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ZhangJieUtil {

    public static String getContentByUrl(String url){
        String content = null;
        try {
            Document document = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
//            Document doc = Jsoup.connect(url).get();
            if(null == document)return null;
            Element div = document.getElementById("content");
            if(null == div)return null;
            content = div.text();
            content.replaceAll("<br />","\n");
            content.replaceAll("<br/>","\n");
            content.replaceAll("&nbsp"," ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static List<ZhangJie> getContentByIndexUrl(String url){
        List<ZhangJie> list = new ArrayList<>();
        int index = 0;
        try {
            Document doc = Jsoup.connect(url).get();
            if(null == doc)return null;
            Element div = doc.getElementById("TabCss");
            if(null == div)return null;
            Document document = Jsoup.parse(div.toString());
            if(null == document)return null;
            Elements elements = document.getElementsByTag("dd");
            if(null == elements)return null;
            for (Element element : elements) {
                Document aas = Jsoup.parse(element.toString());
                if (null != aas) {
                    String text = aas.text();
                    if (null != text && !"".equals(text)) {
                        Elements href = aas.getElementsByTag("a");
                        if (null != href) {
                            index++;
                            ZhangJie zhangJie = new ZhangJie();
                            switch (index % 4) {
                                case 1:
                                    zhangJie.setIndex(index + 3);
                                    break;
                                case 2:
                                    zhangJie.setIndex(index - 1);
                                    break;
                                case 3:
                                    zhangJie.setIndex(index);
                                    break;
                                case 0:
                                    zhangJie.setIndex(index - 2);
                                    break;
                            }
                            zhangJie.setName(aas.text());
                            String u = href.attr("href");
                            if (null != u && !"".equals(u)) {
                                zhangJie.setUrl(u);
                                list.add(zhangJie);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        list.sort(Comparator.comparingInt(ZhangJie::getIndex));
        return list;
    }
}
