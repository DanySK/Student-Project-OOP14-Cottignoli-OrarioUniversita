package model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DailyTimeImpl implements DailyTime {

	public static final int HOURS = 9;	//numero ore giornata scolastica
	public static final int FIRST_HOUR = 9;		//ora inizio lezioni
	
	private Lesson[] t = new Lesson[HOURS];
	
	public DailyTimeImpl(){}
	
	
	//aggiunge una materia, se parte o tutte le ore "selezionate" sono occupate tira un errore
	public void add(final Lesson less, final int hour, final int n){
		checkNull(less);
		checkHour(hour, n);
		if (less == null){
			throw new NullPointerException();
		}
		for (int i = 0; i < n; i++){
			if (getLesson(hour + i) != null){
				throw new IllegalArgumentException("this hours are already busy");
			}
		}
		
		for (int i = 0; i < n; i++){
			t[hour - FIRST_HOUR + i] = less;
		}
	}
	
	//rimuove qualsiasi materia nelle ore selezionate
	public void remove(final int hour, final int n){
		checkHour(hour, n);
		for (int i = 0; i < n; i++){
			t[hour - FIRST_HOUR + i] = null;
		}
	}
	
	//restituisce la materia ad una determinata ora
	public Lesson getLesson(final int hour){
		checkHour(hour);
		return t[hour - FIRST_HOUR];
	}
	
	//restituisce le materie che vi sono in un determinato arco di tempo, se è libero restituisce null
	public Set<Lesson> getlLessons(int hour, int n) {
		checkHour(hour, n);
		Set<Lesson> setLess = new HashSet<>();
		for(int i = 0; i < n; i++){
			if (getLesson(hour + i) != null){
				setLess.add(getLesson(hour + i));
			}
		}
		return setLess;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(t);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DailyTimeImpl other = (DailyTimeImpl) obj;
		if (!Arrays.equals(t, other.t))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "DailyTime [t=" + Arrays.toString(t) + "]";
	}
	
	//controlla che l'ora inserita sia compresa tra le 9 e le 17 altrimenti tira un errore
	private void checkHour(final int h){
		if (h < FIRST_HOUR || h >= (FIRST_HOUR + HOURS) ){
			throw new IllegalArgumentException("Wrong hour! Must be beetween 9 to 17");
		}
	}
	
	//controlla che l'ora + il numero di ore per cui va effettuata una determinata operazione non sia superiore a 17, altrimenti errore
	private void checkHour(final int h, final int n){
		checkHour(h);
		if (n < 1){
			throw new IllegalArgumentException("Wrong number of hour! Must be greater than 0");
		}
		if ((h + n) >= (FIRST_HOUR + HOURS)){
			throw new IllegalArgumentException("Wrong number of hour! Hour + Number must not be greater than 17");
		}
	}
	
	private void checkNull(final Object o){
		if (o == null){
			throw new NullPointerException();
		}
	}
}
