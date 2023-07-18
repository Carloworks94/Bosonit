package com.bosonit.block7crudvalidation.utils;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@UtilityClass
public class FileHelper {

    public String getContentFromFile (String requestFileName) throws IOException {
        Path filePath = Paths.get("src","test","resources",requestFileName);
        return Files.readString(filePath);
    }
}
