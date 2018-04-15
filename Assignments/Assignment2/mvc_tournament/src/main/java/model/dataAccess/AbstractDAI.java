package model.dataAccess;

public interface AbstractDAI<T> {
	
	public int insert(T g);
	
	public T findById(int id);
	
	public void update(int id, T g);
	
	public void delete(int id);

}
