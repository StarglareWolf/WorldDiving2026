package com.example.taskdive;
import java.net.URI;
import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

public class DataUtil {
    public static Map<String, String> readFiles(String folderPath){
        Map<String, String> fileMap = new HashMap<>();
        try{
            URL url = DataUtil.class.getClassLoader().getResource(folderPath);
            if (url == null){
                return fileMap;
            }

            URI uri = url.toURI();
            Path path;
            FileSystem fileSystem = null;
            if (uri.getScheme().equals("jar")){
                fileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
                path = fileSystem.getPath("/" + folderPath);
            }
            else{
                path = Paths.get(uri);
            }

            try (Stream<Path> walk = Files.walk(path)){
                walk.filter(Files::isRegularFile).forEach(file -> {
                    try{
                        String content = new String(Files.readAllBytes(file));
                        fileMap.put(file.getFileName().toString(), content);
                    }
                    catch(IOException ex){
                        ex.printStackTrace();
                    }
                });
            }
            if (uri.getScheme().equals("jar")){
                if (fileSystem != null) {
                    fileSystem.close();
                }
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return fileMap;
    }
}
