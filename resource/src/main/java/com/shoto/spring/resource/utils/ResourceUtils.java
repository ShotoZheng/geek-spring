package com.shoto.spring.resource.utils;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;

public class ResourceUtils {

    public static String getContent(Resource resource) {
        try {
            return getContent(resource, "UTF-8");
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    public static String getContent(Resource resource, String encoding) throws IOException {
        EncodedResource encodedResource = new EncodedResource(resource);
        try (Reader reader = encodedResource.getReader()) {
            return IOUtils.toString(reader);
        }
    }
}
