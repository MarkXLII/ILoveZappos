package in.swapnilbhoite.projects.ilovezappos.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class SearchResultPage {

    private String originalTerm;
    private String currentResultCount;
    private String totalResultCount;
    private String term;
    private List<Product> results = null;
    private String statusCode;
    private Map<String, Object> additionalProperties = new HashMap<>();

    public String getOriginalTerm() {
        return originalTerm;
    }

    public void setOriginalTerm(String originalTerm) {
        this.originalTerm = originalTerm;
    }

    public SearchResultPage withOriginalTerm(String originalTerm) {
        this.originalTerm = originalTerm;
        return this;
    }

    public String getCurrentResultCount() {
        return currentResultCount;
    }

    public void setCurrentResultCount(String currentResultCount) {
        this.currentResultCount = currentResultCount;
    }

    public SearchResultPage withCurrentResultCount(String currentResultCount) {
        this.currentResultCount = currentResultCount;
        return this;
    }

    public String getTotalResultCount() {
        return totalResultCount;
    }

    public void setTotalResultCount(String totalResultCount) {
        this.totalResultCount = totalResultCount;
    }

    public SearchResultPage withTotalResultCount(String totalResultCount) {
        this.totalResultCount = totalResultCount;
        return this;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public SearchResultPage withTerm(String term) {
        this.term = term;
        return this;
    }

    public List<Product> getResults() {
        return results;
    }

    public void setResults(List<Product> results) {
        this.results = results;
    }

    public SearchResultPage withResults(List<Product> results) {
        this.results = results;
        return this;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public SearchResultPage withStatusCode(String statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public SearchResultPage withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return "SearchResultPage{" +
                "originalTerm='" + originalTerm + '\'' +
                ", currentResultCount='" + currentResultCount + '\'' +
                ", totalResultCount='" + totalResultCount + '\'' +
                ", term='" + term + '\'' +
                ", results=" + results +
                ", statusCode='" + statusCode + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
