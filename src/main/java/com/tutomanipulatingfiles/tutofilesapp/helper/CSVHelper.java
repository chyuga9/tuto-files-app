package com.tutomanipulatingfiles.tutofilesapp.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.web.multipart.MultipartFile;

import com.tutomanipulatingfiles.tutofilesapp.model.Tutorial;

public class CSVHelper {
  public static String TYPE = "text/csv";
  static String[] HEADERs = { "Id", "Title", "Description", "Published" };
  
  public static boolean hasCSVFormat(MultipartFile file) {
    if (!TYPE.equals(file.getContentType())) {
      return false;
    }
    return true;
  }
  

  // https://commons.apache.org/proper/commons-csv/user-guide.html
  // only use csv file saved as csv(delimited by semicolon) with "," as separator
  // doesn't work with csv file saved as csv(delimited by semicolon)
  public static List<Tutorial> csvToTutorials(InputStream is) {
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
    	
      List<Tutorial> tutorials = new ArrayList<Tutorial>();
      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
      for (CSVRecord csvRecord : csvRecords) {
        Tutorial tutorial = new Tutorial(
              Long.parseLong(csvRecord.get("Id")),
              csvRecord.get("Title"),
              csvRecord.get("Description"),
              Boolean.parseBoolean(csvRecord.get("Published"))
            );
        tutorials.add(tutorial);
      }
      return tutorials;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }
   
  // https://commons.apache.org/proper/commons-csv/user-guide.html
  // Handling Byte Order Marks (BOM) for csv (delimitedwith  comma OR semicolon) using coma but not semi-colon
  // Doesn't even work with the methods withRecordSeparator() or withDelimiter()
  /*
  public static List<Tutorial> csvToTutorials(InputStream is) {
	try (Reader fileReader = new InputStreamReader(new BOMInputStream(is), "UTF-8");
        CSVParser csvParser = new CSVParser(fileReader,
            CSVFormat.DEFAULT.withRecordSeparator(";").withDeli.withHeader());) {
    	
      List<Tutorial> tutorials = new ArrayList<Tutorial>();
      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
      for (CSVRecord csvRecord : csvRecords) {
        Tutorial tutorial = new Tutorial(
              Long.parseLong(csvRecord.get("Id")),
              csvRecord.get("Title"),
              csvRecord.get("Description"),
              Boolean.parseBoolean(csvRecord.get("Published"))
            );
        tutorials.add(tutorial);
      }
      return tutorials;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }
  */
}
