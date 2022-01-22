/**
* 	@author RITIKA M
*/

package com.example.demo.model;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties
@Document(collection = "files")
public class File {
	    
	    @NotNull(message = "Defect ID is mandatory") 
		private String defect_id;
		private List<FileDocument> filedocument;

		
		

		public String getDefect_id() {
			return defect_id;
		}

		public void setDefect_id(String defect_id) {
			this.defect_id = defect_id;
		}

		public List<FileDocument> getFiledocument() {
			return filedocument;
		}

		public void setFiledocument(List<FileDocument> filedocument) {
			this.filedocument = filedocument;
		}

		public File(String defect_id, List<FileDocument> filesubdocument) {
//			super();
			this.defect_id = defect_id;
			this.filedocument = filedocument;
		}

		public File() {
			super();
		}

		

		
	
}
