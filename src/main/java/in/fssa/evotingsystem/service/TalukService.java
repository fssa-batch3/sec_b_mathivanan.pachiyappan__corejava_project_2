package in.fssa.evotingsystem.service;

import java.util.List;

import in.fssa.evotingsystem.dao.TalukDAO;
import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.model.Taluk;
import in.fssa.evotingsystem.validator.TalukValidator;

public class TalukService {
	
	TalukDAO talukdao = new TalukDAO();

	public Taluk create(Taluk taluk) throws Exception {

		TalukValidator.validate(taluk);

		talukdao.create(taluk);
		
		return taluk;

	}


	public TalukDAO update(int newId, Taluk newTaluk) throws ValidationException {

		TalukValidator.validate(newTaluk);

		talukdao.update(newId, newTaluk);
		
		return talukdao;

	}

	public void delete(int Id) {

		talukdao.delete(Id);

	}


	public List<Taluk> getAll() {

		return talukdao.findAll();

	}

}
