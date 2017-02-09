package in.swapnilbhoite.projects.ilovezappos.models;

import java.util.HashMap;
import java.util.Map;

public class Product {

    private String brandName;
    private String thumbnailImageUrl;
    private String productId;
    private String originalPrice;
    private String styleId;
    private String colorId;
    private String price;
    private String percentOff;
    private String productUrl;
    private String productName;
    private Map<String, Object> additionalProperties = new HashMap<>();

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Product withBrandName(String brandName) {
        this.brandName = brandName;
        return this;
    }

    public String getThumbnailImageUrl() {
        return thumbnailImageUrl;
    }

    public void setThumbnailImageUrl(String thumbnailImageUrl) {
        this.thumbnailImageUrl = thumbnailImageUrl;
    }

    public Product withThumbnailImageUrl(String thumbnailImageUrl) {
        this.thumbnailImageUrl = thumbnailImageUrl;
        return this;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Product withProductId(String productId) {
        this.productId = productId;
        return this;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Product withOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
        return this;
    }

    public String getStyleId() {
        return styleId;
    }

    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }

    public Product withStyleId(String styleId) {
        this.styleId = styleId;
        return this;
    }

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public Product withColorId(String colorId) {
        this.colorId = colorId;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Product withPrice(String price) {
        this.price = price;
        return this;
    }

    public String getPercentOff() {
        return percentOff;
    }

    public void setPercentOff(String percentOff) {
        this.percentOff = percentOff;
    }

    public Product withPercentOff(String percentOff) {
        this.percentOff = percentOff;
        return this;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public Product withProductUrl(String productUrl) {
        this.productUrl = productUrl;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Product withProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Product withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }
}