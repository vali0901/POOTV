package input;

public class ActionInput {
    private String type;
    private String page;
    private String movie;
    private String feature;
    private Credentials credentials;
    private String startsWith;
    private FilterInput filters;
    private Integer count;
    private Integer rate;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public String getStartsWith() {
        return startsWith;
    }

    public void setStartsWith(String startsWith) {
        this.startsWith = startsWith;
    }

    public FilterInput getFilters() {
        return filters;
    }

    public void setFilters(FilterInput filters) {
        this.filters = filters;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "ActionInput{" +
                "type='" + type + '\'' +
                ", page='" + page + '\'' +
                ", movie='" + movie + '\'' +
                ", feature='" + feature + '\'' +
                ", credentials=" + credentials +
                ", startsWith='" + startsWith + '\'' +
                ", filters=" + filters +
                ", count=" + count +
                ", rate=" + rate +
                '}';
    }
}