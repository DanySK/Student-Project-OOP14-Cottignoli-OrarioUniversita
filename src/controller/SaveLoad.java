package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Utility class che serve per salvare e caricare su file qualsiasi oggetto che implementi l'interfaccia {@link java.io.Serializable}.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public final class SaveLoad {
	
	private SaveLoad() {
		super();
	}
	
	/**
	 * Metodo per salvare un oggetto su file.
	 * 
	 * @param fileName The system-dependent filename
	 * @param obj Oggetto da salvare su file.
	 * @throws IOException {@link ObjectOutputStream#writeObject(Object)}.
	 */
	public static void commandSave(final String fileName, final Object obj) throws IOException {
		final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
		oos.writeObject(obj);
		oos.close();
	}
	
	/**
	 * Metodo per caricare un oggetto da file.
	 * 
	 * @param fileName The system-dependent filename
	 * @return Oggetto caricato da file.
	 * @throws IOException construct method of {@link ObjectIntputStream}.
	 * @throws ClassNotFoundException {@link ObjectInputStream#readObject()}
	 */
	public static Object commandLoad(final String fileName) throws IOException, ClassNotFoundException {
		final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
		final Object o = ois.readObject();
		ois.close();
		return o;
	}
}
