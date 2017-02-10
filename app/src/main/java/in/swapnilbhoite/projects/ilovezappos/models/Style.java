package in.swapnilbhoite.projects.ilovezappos.models;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class Style {

    private String price;
    private String originalPrice;
    private String percentOff;
    private String productUrl;
    private String imageUrl;
    private String styleId;
    private String color;
    private Map<String, Object> additionalProperties = new HashMap<>();

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Style withPrice(String price) {
        this.price = price;
        return this;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Style withOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
        return this;
    }

    public String getPercentOff() {
        return percentOff;
    }

    public void setPercentOff(String percentOff) {
        this.percentOff = percentOff;
    }

    public Style withPercentOff(String percentOff) {
        this.percentOff = percentOff;
        return this;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public Style withProductUrl(String productUrl) {
        this.productUrl = productUrl;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Style withImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getStyleId() {
        return styleId;
    }

    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }

    public Style withStyleId(String styleId) {
        this.styleId = styleId;
        return this;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Style withColor(String color) {
        this.color = color;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Style withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}