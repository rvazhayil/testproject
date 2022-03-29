package com.xyzcorp.testproject.common;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.xyzcorp.testproject.domain.UserDto;
import com.xyzcorp.testproject.exception.FileUploadException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Component
@Slf4j
public class CSVFileUploader implements FileUploader<UserDto> {
    @Override
    public List<UserDto> upload(MultipartFile file) {
        List<UserDto> users = null;
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            CsvToBean<UserDto> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(UserDto.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            users = csvToBean.parse();

        } catch (Exception e) {
            log.error("Error Parsing File ", e);
            throw new FileUploadException(e.getLocalizedMessage());
        }
        return users;
    }
}
