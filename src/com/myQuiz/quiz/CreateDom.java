package com.myQuiz.quiz;

import java.io.*;
import java.net.*;
import javax.xml.parsers.*;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class CreateDom {
	public static Document getDOM(String test)
			throws SAXException, ParserConfigurationException, IOException, URISyntaxException {
		Document dom = null;
		File quizFile = null;

		quizFile = new File("C:/Users/ASHISH KUMAR/workspace/OnlineQuizTest/WebContent/resource/Quizzes/" + test + "-quiz-1.xml");
		System.out.println("Quiz File Absolute Path " + quizFile.getAbsolutePath());

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		try {
			dom = db.parse(quizFile);
		} catch (FileNotFoundException fileNotFound) {
			System.out.println("Error : Quiz File Not Found " + fileNotFound);
		}
		dom.getDocumentElement().normalize();
		return dom;
	}
}
