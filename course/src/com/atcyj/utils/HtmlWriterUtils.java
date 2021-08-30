package com.atcyj.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class HtmlWriterUtils {

    private HtmlWriterUtils() {}

    public static void updateOneLine(HttpServletResponse response, String path, String oldLine, String newLine) throws IOException{
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8" ));
        String line;
        while((line= br.readLine())!=null){
            if(line.equalsIgnoreCase(oldLine) ){
                line = newLine;
            }
            writer.println(line);
            System.out.println(line);
        }
        writer.close();
        br.close();
    }
}
