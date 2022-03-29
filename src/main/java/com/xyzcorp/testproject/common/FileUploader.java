package com.xyzcorp.testproject.common;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Implementation should implement for specific files.
 *
 * @param <T>
 */
public interface FileUploader<T> {

    public List<T> upload(MultipartFile file);
}
