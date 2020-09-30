package wob.city.database.dto;

import java.util.ArrayList;
import java.util.List;

public class QueryDto {
    private String query;
    private List<Object> params;

    public QueryDto() {
        this.params = new ArrayList<>();
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<Object> getParams() {
        return params;
    }

    public void addParam(Object param) {
        params.add(param);
    }
}
