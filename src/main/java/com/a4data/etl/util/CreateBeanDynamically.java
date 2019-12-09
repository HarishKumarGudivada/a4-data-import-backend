package com.a4data.etl.util;

import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;


@Component
public class CreateBeanDynamically {


/*	public void createBean(Map<String,String> data) {

		String path = "./src/main/java/com/a4data/etl/models/";
		FileWriter fileWritter = null;
		try {
			if(data!=null) {
				String className = data.get("classname");
				fileWritter = new FileWriter(path+className+".java");

				StringBuilder builder = new StringBuilder();
				builder.append("package com.a4data.etl.models;\n\n");
				builder.append("import javax.persistence.Entity;\r\n");
				builder.append("import javax.persistence.Id;\r\n");
				builder.append("import javax.persistence.Table;\r\n\n");
				builder.append("@Entity\n");
				builder.append("@Table(name=\"").append(data.get("tablename")).append("\")\n");
				builder.append("public class ").append(className).append("{\n\n");
				builder.append("@Id");
				builder.append("private int id;\n\n");
				builder.append("public int getId() {\n");
				builder.append("return id;\n");
				builder.append("}\n"); 
				builder.append("public void setId(int id) {\n"); 
				builder.append("this.id = id;\n");
				builder.append("}");
				for(Entry<String, String> entry : data.entrySet()) {
					builder.append("private ").append(entry.getValue()).append(" ").append(entry.getKey()).append(";\n\n");
					builder.append("public ").append(entry.getValue()).append(" ").append("get").append(entry.getKey().substring(0, 1).toUpperCase());
					builder.append(entry.getKey().substring(1)).append("()").append("{");
					builder.append("return this.").append(entry.getKey()).append(";\n");
					builder.append("}\n\n");

					builder.append("public ").append("void ").append("set").append(entry.getKey().substring(0,1).toUpperCase());
					builder.append(entry.getKey().substring(1)).append("(").append(entry.getValue()).append(" ").append(entry.getKey()).append(")").append("{\n");
					builder.append("this.").append(entry.getKey()).append("=").append(entry.getKey()).append(";\n");
					builder.append("}\n\n");

				}
				builder.append("}");

				fileWritter.write(builder.toString());
                
		//		createCrudRepository(className);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
*/	
	
/*	public void createCrudRepository(String className) {
		try {
			String path = "./src/main/java/com/a4data/etl/repository/";
			FileWriter fileWritter = new FileWriter(path+className+".java");
			StringBuilder builder = new StringBuilder();
			builder.append("package com.a4data.etl.repository;\n\n");
			builder.append("import org.springframework.data.repository.CrudRepository;\n\n"); 
			builder.append("import org.springframework.stereotype.Repository;\n");
			builder.append("import com.a4data.etl.models;\n\n");
			builder.append("@Repository\n");
			builder.append("public interface ").append(className).append("Repository ").append("extends CrudRepository<").append(className).append(", id>{\n\n");
			builder.append("}");
				
			fileWritter.write(builder.toString());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
*/

	@SuppressWarnings("rawtypes")
	public Object getInstance(String classBinNames) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Constructor constructor = null;
		try {
		// Create a new JavaClassLoader 
		ClassLoader classLoader = this.getClass().getClassLoader();

		// Load the target class using its binary name
		Class loadedMyClass = classLoader.loadClass(classBinNames);

		System.out.println("Loaded class name: " + loadedMyClass.getName());

		// Create a new instance from the loaded class
		constructor = loadedMyClass.getConstructor();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return constructor.newInstance();
	}



	
	public void createBean(Map<String,Object> data) {

		String path = "./src/main/java/com/a4data/etl/models/";
		FileWriter fileWritter = null;
		try {
			if(data!=null) {
				Map<String, String> tableData = (Map<String, String>) data.get("sorcedb");
				
				String className = (String) data.get("classname");
				fileWritter = new FileWriter(path+className+".java");

				StringBuilder builder = new StringBuilder();
				builder.append("package com.a4data.etl.models;\n\n");
				builder.append("public class ").append(className).append("{\n\n");
				builder.append("private int id;\n\n");
				builder.append("public int getId() {\n");
				builder.append("return id;\n");
				builder.append("}\n"); 
				builder.append("public void setId(int id) {\n"); 
				builder.append("this.id = id;\n");
				builder.append("}");
				for(Entry<String, String> entry : tableData.entrySet()) {
					builder.append("private ").append(entry.getValue()).append(" ").append(entry.getKey()).append(";\n\n");
					builder.append("public ").append(entry.getValue()).append(" ").append("get").append(entry.getKey().substring(0, 1).toUpperCase());
					builder.append(entry.getKey().substring(1)).append("()").append("{");
					builder.append("return this.").append(entry.getKey()).append(";\n");
					builder.append("}\n\n");

					builder.append("public ").append("void ").append("set").append(entry.getKey().substring(0,1).toUpperCase());
					builder.append(entry.getKey().substring(1)).append("(").append(entry.getValue()).append(" ").append(entry.getKey()).append(")").append("{\n");
					builder.append("this.").append(entry.getKey()).append("=").append(entry.getKey()).append(";\n");
					builder.append("}\n\n");

				}
				builder.append("}");

				fileWritter.write(builder.toString());
                
		//		createCrudRepository(className);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
