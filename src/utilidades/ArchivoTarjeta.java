package utilidades;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import model.Edc;

public class ArchivoTarjeta implements Archivo {
	private Edc edc;
	private String msg="";
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	public ArchivoTarjeta(String corte,String mes, String anio){
		this.edc =  new Edc();
		this.edc.setCorte(corte);
		this.edc.setMes(mes);
		this.edc.setAnio(anio);
		this.edc.setModelo("T");
	}
	@Override
	public ArrayList<Edc> ExtraerInfo(File file) {
		ArrayList<Edc> datosEdc = new ArrayList<Edc>();
				int contLine = 1;
				BufferedReader br = null;
				try {
				    br = new BufferedReader(new FileReader(file.getAbsolutePath()));
				    String texto = br.readLine();
				    while(texto != null) {
				    	//captura cliente
				    	if(texto.substring(0, 4).contains("A;1")) {	
				    		edc.setCliente(texto.substring(4));				    				    		
				    	}
				    	
				    	//captura id
				    	if(texto.substring(0, 4).contains("A;5")) {	
				    		edc.setCliente(texto.substring(14,30));				    				    		
				    	}
				    	
				    	//captura correo
				    	if(texto.substring(0, 4).contains("A;87")) {	
				    		edc.setId(texto.substring(4));				    				    		
				    	}
				    	
				    	//captura etiqueta
				    	if(texto.substring(0, 4).contains("A;88")) {	
				    		edc.setId(texto.substring(5));	
				    		datosEdc.add(edc);
				    		edc.setCliente("");
				    		edc.setCorreo("");
				    		edc.setEtiqueta("");
				    	}
		        
				        texto = br.readLine();		        
				    }

				}
				catch (FileNotFoundException ex) {
				    System.out.println("Error: Fichero no encontrado");
				    ex.printStackTrace();
				}
				catch(Exception ex) {
				    System.out.println("Error de lectura del fichero");
				    ex.printStackTrace();
				}
				finally {
				    try {
				        if(br != null) {
				            br.close();
				        }
				    }
				    catch (Exception ex) {
				        System.out.println("Error al cerrar el fichero");
				        ex.printStackTrace();
				    }
				}
		return datosEdc;
	}
	@Override
	public void escribirCsv(ArrayList<Edc> datosEdc) {
		// TODO Auto-generated method stub
					try(FileWriter fw = new FileWriter(new File("").getAbsolutePath()+"\\"+"carga.cfa", true);
					    BufferedWriter bw = new BufferedWriter(fw);
					    PrintWriter out = new PrintWriter(bw))
					    {
					for(Edc edc:datosEdc) {
							out.println(edc.getId() +";"+edc.getCliente()+";"+edc.getCorreo()+";"+edc.getEtiqueta()+";"+edc.getCorte()+";"+edc.getMes()+";"+edc.getAnio()+";"+edc.getModelo());
					}    
					        
					} catch (IOException e) {
					    //exception handling left as an exercise for the reader
						e.printStackTrace();
					}
		
	}
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub
		propertyChangeSupport.addPropertyChangeListener(listener);
		
	}
	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub
		propertyChangeSupport.removePropertyChangeListener(listener);
		
	}

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		String oldValue = msg;
		propertyChangeSupport.firePropertyChange("myProperty", oldValue, msg);
		this.msg = msg;
	}


	

}
