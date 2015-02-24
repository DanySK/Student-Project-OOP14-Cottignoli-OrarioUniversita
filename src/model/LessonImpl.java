package model;

public class LessonImpl implements Lesson {

	private final String subName;
	private final String teachName;
	private final int semester;
	
	public LessonImpl(final String sub, final String teach, final int sem){
		if (sub == null || teach == null){
			throw new NullPointerException();
		}
		if (sem != FIRST_SEM && sem != SEC_SEM){
			throw new IllegalArgumentException();
		}
		subName = sub;
		teachName = teach;
		semester = sem;
	}
	
	public String getSubName() {
		return subName;
	}

	public String getTeachName() {
		return teachName;
	}
	
	public int getSemester(){
		return semester;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + semester;
		result = prime * result + ((subName == null) ? 0 : subName.hashCode());
		result = prime * result
				+ ((teachName == null) ? 0 : teachName.hashCode());
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
		LessonImpl other = (LessonImpl) obj;
		if (semester != other.semester)
			return false;
		if (subName == null) {
			if (other.subName != null)
				return false;
		} else if (!subName.equals(other.subName))
			return false;
		if (teachName == null) {
			if (other.teachName != null)
				return false;
		} else if (!teachName.equals(other.teachName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LessonImpl [subName=" + subName + ", teachName=" + teachName
				+ ", semester=" + semester + "]";
	}

	
}
