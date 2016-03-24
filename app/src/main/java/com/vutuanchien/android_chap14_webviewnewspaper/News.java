package com.vutuanchien.android_chap14_webviewnewspaper;

public class News {
    public final static String ITEMS = "item";
    public final static String TITLE = "title";
    public final static String DESCRIPTION = "description";
    public final static String LINK = "link";
    public final static String PUB_DATE = "pubDate";
    public final static String LINK_IMAGE = "summaryImg";

    private String title;
    private String description;
    private String link;
    private String pubDate;
    private String linkImage;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    @Override
    public String toString() {
        return title + "/" + description + "/" + linkImage + "/" + link + "/" + pubDate;
    }
}
