package in.fssa.evotingsystem.Interface;

import java.util.List;

public interface Base<T> {

	public abstract List<T> findAll();

	public abstract void create(T t);

	public abstract void update(int id, T t);

	public abstract void delete(int id);

	public abstract T findById(int id);

}