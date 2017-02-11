package in.swapnilbhoite.projects.ilovezappos.models;

import android.annotation.TargetApi;
import android.os.Build;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.swapnilbhoite.projects.ilovezappos.utils.TextUtil;

@SuppressWarnings("unused")
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
    private String brandId;
    private String defaultImageUrl;
    private String defaultProductUrl;
    private List<Style> styles = null;
    private Map<String, Object> additionalProperties = new HashMap<>();

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public String getBrandName() {
        return TextUtil.decodeUnicodes(brandName);
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
        return TextUtil.decodeUnicodes(productName);
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Product withProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public Product withBrandId(String brandId) {
        this.brandId = brandId;
        return this;
    }

    public String getDefaultImageUrl() {
        return defaultImageUrl;
    }

    public void setDefaultImageUrl(String defaultImageUrl) {
        this.defaultImageUrl = defaultImageUrl;
    }

    public Product withDefaultImageUrl(String defaultImageUrl) {
        this.defaultImageUrl = defaultImageUrl;
        return this;
    }

    public String getDefaultProductUrl() {
        return defaultProductUrl;
    }

    public void setDefaultProductUrl(String defaultProductUrl) {
        this.defaultProductUrl = defaultProductUrl;
    }

    public Product withDefaultProductUrl(String defaultImageUrl) {
        this.defaultProductUrl = productUrl;
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

    public List<Style> getStyles() {
        return styles;
    }

    public void setStyles(List<Style> styles) {
        this.styles = styles;
    }

    public Product withStyles(List<Style> styles) {
        this.styles = styles;
        return this;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!productId.equals(product.productId)) return false;
        return styleId.equals(product.styleId);
    }

    @Override
    public int hashCode() {
        int result = productId.hashCode();
        result = 31 * result + styleId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "brandName='" + brandName + '\'' +
                ", thumbnailImageUrl='" + thumbnailImageUrl + '\'' +
                ", productId='" + productId + '\'' +
                ", originalPrice='" + originalPrice + '\'' +
                ", styleId='" + styleId + '\'' +
                ", colorId='" + colorId + '\'' +
                ", price='" + price + '\'' +
                ", percentOff='" + percentOff + '\'' +
                ", productUrl='" + productUrl + '\'' +
                ", productName='" + productName + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}