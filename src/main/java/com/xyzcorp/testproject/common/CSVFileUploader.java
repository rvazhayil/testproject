package com.xyzcorp.testproject.common;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.xyzcorp.testproject.domain.UserDto;
import com.xyzcorp.testproject.exception.FileUploadException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Component
public class CSVFileUploader implements FileUploader<UserDto> {
    @Override
    public List<UserDto> upload(MultipartFile file) {
        List<UserDto> users = null;
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            // create csv bean reader
            CsvToBean<UserDto> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(UserDto.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            // convert `CsvToBean` object to list of users
            users = csvToBean.parse();

        } catch (Exception e) {
            throw new FileUploadException(e.getLocalizedMessage());
        }
        return users;
    }
}
