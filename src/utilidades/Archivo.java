package utilidades;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;

import model.Edc;

public interface Archivo {
	public ArrayList<Edc>ExtraerInfo(File file);
	void escribirCsv(ArrayList<Edc> datosEdc);
	void addPropertyChangeListener(PropertyChangeListener listener);
    void removePropertyChangeListener(PropertyChangeListener listener);
}
