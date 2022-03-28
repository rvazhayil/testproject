package com.xyzcorp.testproject.common;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploader<T> {

    public List<T> upload(MultipartFile file);
}
