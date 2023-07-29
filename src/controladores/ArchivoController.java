package controladores;

import java.io.File;
import java.util.ArrayList;

import utilidades.Archivo;

public class ArchivoController {
	private Archivo archivo;
	private String rutaArchivos;
	private ArrayList<File> files;
	public ArchivoController(Archivo _archivo,String _carpetaArchivos){
		archivo =_archivo;
		rutaArchivos =_carpetaArchivos;
		setFiles(new ArrayList<>());
	}
	
	public void subirArchivo() {
		for(File file:files) {
			archivo.ExtraerInfo( file);
			
		}
	}
	private void getFiles(String filePath){
		File directorios =new File(filePath);
		File[] ficheros = directorios.listFiles();               
        if(ficheros!=null){
            for (File fichero : ficheros) {
                if (fichero.isDirectory()) {
                	getFiles(fichero.getAbsolutePath());
                    
                }else{
                    getFiles().add(fichero);
                    
                }
            }
        }

	}

	public ArrayList<File> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<File> files) {
		this.files = files;
	}

}
