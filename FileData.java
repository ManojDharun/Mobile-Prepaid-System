package application;

public class FileData {
	   String fileName;
	   String path;
	   String size;
	   String dateModified;
	   FileData(String fileName, String path, String size, String dateModified) {
	      this.fileName = fileName;
	      this.path = path;
	      this.size = size;
	      this.dateModified = dateModified;
	   }
	   public String getFileName(){
	      return this.fileName;
	   }
	   public void setFileName(String fname){
	     this.fileName = fname;
	   }
	   public String getPath(){
	      return this.path;
	   }
	   public void setPath(String fpath){
	      this.path = path;
	   }
	   public String getSize(){
	      return this.size;
	   }
	   public void setSize(String fsize){
	      this.size = fsize;
	   }
	   public String getDateModified(){
	      return this.dateModified;
	   }
	   public void setModified(String fmodified){
		   this.dateModified = fmodified;
	   }
}
