package com.project.starwarsapi.domain.model;

import java.util.List;
import java.util.Objects;

public class PlanetPage {
    private Long count;
    private String next;
    private String previous;
    private List<PlanetDTO> results;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<PlanetDTO> getResults() {
        return results;
    }

    public void setResults(List<PlanetDTO> results) {
        this.results = results;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.count);
        hash = 53 * hash + Objects.hashCode(this.next);
        hash = 53 * hash + Objects.hashCode(this.previous);
        hash = 53 * hash + Objects.hashCode(this.results);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PlanetPage other = (PlanetPage) obj;
        if (!Objects.equals(this.next, other.next)) {
            return false;
        }
        if (!Objects.equals(this.previous, other.previous)) {
            return false;
        }
        if (!Objects.equals(this.count, other.count)) {
            return false;
        }
        if (!Objects.equals(this.results, other.results)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PlanetPage{" + "count=" + count + ", next=" + next + ", previous=" + previous + ", results=" + results + '}';
    }
}
