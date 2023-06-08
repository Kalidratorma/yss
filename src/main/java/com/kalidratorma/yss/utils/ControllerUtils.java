package com.kalidratorma.yss.utils;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;

import java.util.List;

public class ControllerUtils {
    public static MappingJacksonValue getFilteredMapper(Object object, CustomFilter... filterList) {
        MappingJacksonValue mapper = new MappingJacksonValue(object);
        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        for(CustomFilter cf : filterList) {
            filterProvider.addFilter(cf.getFilterName(), cf.getPropertyFilter());
        }
        mapper.setFilters(filterProvider);
        return mapper;
    }
}
