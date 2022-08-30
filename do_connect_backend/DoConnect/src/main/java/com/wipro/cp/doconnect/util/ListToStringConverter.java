/*
 * @Author: Devashish Ashok Pathrabe
 * Modified Date: 25-08-2022
 * Description: List to String Converter
 */

package com.wipro.cp.doconnect.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.AttributeConverter;

public class ListToStringConverter implements AttributeConverter<List<String>, String> {
    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        return (attribute == null || attribute.size() == 0) ? null : String.join(",", attribute);
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        return dbData == null ? Collections.emptyList() : Arrays.asList(dbData.split(","));
    }
}
