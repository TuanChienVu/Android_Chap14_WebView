package com.vutuanchien.android_chap14_webviewnewspaper;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class RSSUtils {

    public static List<News> read24hcom(InputStream inputStream) {
        List<News> newsList = new ArrayList<>();
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;

        try {
            builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(inputStream);
            if (document != null) {
                NodeList nodeList = document.getElementsByTagName(News.ITEMS);
                if (nodeList != null) {
                    News news = null;
                    for (int i = 0; i < nodeList.getLength(); i++) {
                        news = new News();
                        Node item = nodeList.item(i);
                        NodeList nodeListChildren = item.getChildNodes();
                        if (nodeListChildren != null) {
                            for (int j = 0; j < nodeListChildren.getLength(); j++) {
                                Node child = nodeListChildren.item(j);
                                String noidung = child.getTextContent();
                                switch (child.getNodeName()) {
                                    case News.TITLE:
                                        news.setTitle(noidung);
                                        break;
                                    case News.DESCRIPTION:
                                        news.setDescription(noidung);
                                        break;
                                    case News.LINK:
                                        news.setLink(noidung);
                                        break;
                                    case News.LINK_IMAGE:
                                        news.setLinkImage(noidung);
                                        break;
                                    case News.PUB_DATE:
                                        news.setPubDate(noidung);
                                        break;
                                }
                            }
                        }
                        newsList.add(news);
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newsList;
    }

}
