package preprocessing;

import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Preprocessor {
    public static void main(String[] args) {

    	String inputFile = "/Users/megkobashi/Documents/Codestellation/recording.csv";
        String outputFile = "/Users/megkobashi/Documents/Codestellation/output.csv";

        String line = "";
        String cvsSplitBy = ",";
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
        	reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(outputFile));
            String output = "";
            List<String> raw_data;
            while ((line = reader.readLine()) != null) {
                // use comma as separator
                raw_data = Arrays.asList(line.split(cvsSplitBy));
                if (raw_data.get(1).contains("/muse/elements/raw_fft")) {
                	output = output + " " + String.join(" ", raw_data.subList(2, raw_data.size())) + " ";
                	if (raw_data.get(1).contains("/muse/elements/raw_fft3")) {
                		output = output + "\n";
                		writer.write(output);
                		output = "";
                	}
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	if (reader!= null && writer != null) {
        		try {
        			reader.close();
        			writer.flush();
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
        	}
        }
    }
}
