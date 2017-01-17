package com.sydney.dream;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.*;
import java.util.List;

/**
 * Created by lWX355499 on 2017/1/16.
 */
public class FindSuite {

    public static void main(String[] args) throws IOException, JDOMException {
        System.out.println(System.getProperty("user.dir"));
        read("D:\\junitResult.xml");
    }

    public static void read(String path) throws IOException, JDOMException {
        SAXBuilder saxBuilder = new SAXBuilder();
        InputStream  inputStream = new FileInputStream(new File(path));
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        Document document = saxBuilder.build(inputStreamReader);
        Element rootElement = document.getRootElement();
        System.out.println(rootElement);
        List<Element> result = rootElement.getChildren("suites");
        Element result_tag = result.get(0);
        System.out.println(result_tag);
        List<Element> suite_case=result_tag.getChildren("suite");
        System.out.println("totalSuite is :" + suite_case.size());
        System.out.print(suite_case);
        System.out.println();
        for (int i =0; i < suite_case.size(); i++){
            Element element = suite_case.get(i);
            System.out.println(element.getChild("name").getText());
        }
    }
}

