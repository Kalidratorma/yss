package com.kalidratorma.yss.utils;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

public class CustomFilter {
    private String filterName;
    private SimpleBeanPropertyFilter propertyFilter;

    public CustomFilter(String filterName, SimpleBeanPropertyFilter filter) {
        this.filterName = filterName;
        this.propertyFilter = filter;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public SimpleBeanPropertyFilter getPropertyFilter() {
        return propertyFilter;
    }

    public void setPropertyFilter(SimpleBeanPropertyFilter propertyFilter) {
        this.propertyFilter = propertyFilter;
    }
}
