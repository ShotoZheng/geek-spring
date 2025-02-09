package com.shoto.spring.configuration.metadata.conditional;

public class TradeDomainConfig {

    private String url;

    public TradeDomainConfig() {
    }

    public TradeDomainConfig(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "TradeDomainConfig{" +
                "url='" + url + '\'' +
                '}';
    }
}
