package br.com.rdfmapping.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FilesModeling {
	private static File file = new File("/home/felipe/Documentos/java-workspace/rdfmapping/src/main/webapp/resource/rdf/");
		
	public static ArrayList<String> getFiles(final String extension){
		
		ArrayList<String> fileNames = new ArrayList<>();

		File afile[] = file.listFiles(
				new FileFilter(){
					public boolean accept(File b){
						return b.getName().endsWith("."+extension);
					}
				});

		if (afile!=null){
			for (int i=0; i<afile.length;i++){
				fileNames.add(afile[i].getName());				
			}		 
		}
		
		return fileNames;
	}
	
	public static void newFile(String name, String extension, String content){

		// Create new file
		File newFile = new File(file.getAbsolutePath()+"/"+name+"."+extension);
		try {
			// If file doesn't exists, then create it
            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            FileWriter fw = new FileWriter(newFile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            // Write in file
            bw.write(content);
            // Close connection
            bw.close();
        } catch (IOException e) {
            System.err.println("Problem writing to the file");
        }
		
	}

}
