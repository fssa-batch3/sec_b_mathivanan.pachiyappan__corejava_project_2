package in.fssa.evotingsystem.Interface;

import java.util.List;

import in.fssa.evotingsystem.exception.PersistanceException;

public interface Base<T> {

	public abstract List<T> findAll() throws PersistanceException;

	public abstract void create(T t) throws PersistanceException;

	public abstract void update(int id, T t) throws PersistanceException;

	public abstract void delete(int id) throws PersistanceException;

	public abstract T findById(int id) throws PersistanceException;

}