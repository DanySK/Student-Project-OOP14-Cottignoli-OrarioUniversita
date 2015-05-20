package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Classrooms;
import model.DailyTime;
import model.Days;
import model.IModel;
import model.ISubject;
import model.SubjectType;
import view.IView;

/**
 * Implementazione dell'interfaccia {@link IViewController} utilizzando come struttura per salvare gli stati del modello un'implementazione
 * dell'interfaccia {@link ICaretaker}.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public class ViewController implements IViewController {
	private static final List<Object> INTEST = Arrays.asList(new Object[]{"", "9-10", "10-11", "11-12", "12-13", "13-14", "14-15", 
			"15-16", "16-17", "17-18"});
	
	private IModel model;
	private ICaretaker<IModel.IMemento> care = new Caretaker<>();
	
	private Object obj;
	
	/**
	 * Costruttore che setta il modello su cui operare.
	 * 
	 * @param m Modello su cui operare.
	 */
	public ViewController(final IModel m) {
		model = m;
		care.add(model.createMemento());
	}
	
	@Override
	public void setViewType(final IView v, final Object ob) {
		obj = ob;
		updateViews(v);
	}
	
	@Override
	public void setModel(final IModel m) {
		model = m;
	}
	
	@Override
	public IModel.IMemento getPrevMemento() {
		return care.getPrev();
	}
	
	@Override
	public IModel.IMemento getSuccMemento() {
		return care.getSucc();
	}
	
	@Override
	public void createMemento() {
		care.add(model.createMemento());
	}
	
	@Override
	public void resetCaretaker() {
		care = new Caretaker<>();
		createMemento();
	}
	
	@Override
	public void updateViews(final IView v) {
		v.clearData();
		if (obj == null) {
			v.addData(viewTot(v.getSelectedSem()));
		} else {
			if (obj instanceof String) {
				v.addData(viewFunct(v.getSelectedSem(), (sem, d, h) -> {
					if (model.whereTeaching((String) obj, sem, d, h).isEmpty()) {
						return "";
					}
					final StringBuilder s = new StringBuilder();
					model.whereTeaching((String) obj, sem, d, h).forEach(x -> s.append(model.getSubject(sem, d, x, h).get().getSubName() 
									+ "-" + x.getName() + "\n"));
					return s;
				}));
			}

			if (obj instanceof Days) {
				v.addData(viewDay(v.getSelectedSem(), (Days) obj));
			}
		
			if (obj instanceof Classrooms) {
				v.addData(viewFunct(v.getSelectedSem(), (sem, d, h) -> {
					if (model.getSubject(sem, d, (Classrooms) obj, h).isPresent()) {
						return model.getSubject(sem, d, (Classrooms) obj, h).get();
					}
					return "";
				}));
			}
			if (obj instanceof ISubject) {
				v.addData(viewFunct(v.getSelectedSem(), (sem, d, h) -> {
					if (model.wherePerforming((ISubject) obj, sem, d, h).isEmpty()) {
						return "";
					}
					final StringBuilder s = new StringBuilder();
					model.wherePerforming((ISubject) obj, sem, d, h).forEach(x -> s.append(x.getName() + "\n"));
					return s;
				}));
			}
			if (obj instanceof SubjectType) {
				final List<Object> list = viewTot(v.getSelectedSem());
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i) instanceof ISubject && !list.get(i).toString().contains(obj.toString())) {
						list.set(i, "");
					}
				}
				v.addData(list);
			}
		}
	}
	
	@Override
	public void undoRedo(final IView v) {
		if (care.succExist()) {
			v.setEnabledCommandRedo(true);
		} else {
			v.setEnabledCommandRedo(false);
		}
		if (care.prevExist()) {
			v.setEnabledCommandUndo(true);
		} else {
			v.setEnabledCommandUndo(false);
		}
	}
	
	/**
	 * Metodo per creare la vista completa per un giorno di un semestre.
	 * 
	 * @param sem Semestre del quale si vuole avere l'orario completo di un determinato giorno.
	 * @param d Giorno di cui si vuole avere l'orario completo.
	 * @return Orario completo di un giorno sottoforma di List.
	 */
	private List<Object> viewDay(final int sem, final Days d) {
		INTEST.set(0, d.getName());
		final List<Object> list = new ArrayList<>(INTEST);
		for (final Classrooms cls : Classrooms.values()) {
			list.add(cls.getName());
			for (int i = DailyTime.FIRST_HOUR; i < (DailyTime.FIRST_HOUR + DailyTime.HOURS); i++) {
				if (model.getSubject(sem, d, cls, i).isPresent()) {
					list.add(model.getSubject(sem, d, cls, i).get());
				} else {
					list.add("");
				}
			}
		}
		return list;
	}
	
	/**
	 * Metodo per creare la vista completa dell'orario di un semestre.
	 * 
	 * @param sem Semestre del quale si vuole avere l'orario completo.
	 * @return Orario completo sottoforma di List.
	 */
	private List<Object> viewTot(final int sem) {
		final List<Object> list = new ArrayList<>();
		for (final Days d : Days.values()) {
			list.addAll(viewDay(sem, d));
		}
		return list;
	}
	
	/**
	 * Metodo per creare viste simili che differiscono solo per la scelta del for interno. Questa
	 * scelta è stata affidata ad una interfaccia funzionale per evitare duplicazione di codice.
	 * 
	 * @param sem Semestre del quale si vuole avere la vista.
	 * @param fun Funzione che definisce la scelta interna.
	 * @return Vista definita grazie a fun sottoforma di List.
	 */
	private List<Object> viewFunct(final int sem, final MyFunction<?> fun) {
		INTEST.set(0, "Days");
		final List<Object> list = new ArrayList<>(INTEST);
		for (final Days d : Days.values()) {
			list.add(d.getName());
			for (int i = DailyTime.FIRST_HOUR; i < (DailyTime.FIRST_HOUR + DailyTime.HOURS); i++) {
				list.add(fun.apply(sem, d, i));
			}
		}
		return list;
	}
	
}
