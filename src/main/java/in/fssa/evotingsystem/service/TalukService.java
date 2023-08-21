package in.fssa.evotingsystem.service;

import java.util.List;

import in.fssa.evotingsystem.dao.TalukDAO;
import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.model.Taluk;
import in.fssa.evotingsystem.validator.TalukValidator;

/**
 * The TalukService class provides methods to interact with the database and perform operations related to Taluk entities.
 */
public class TalukService {
	
	TalukDAO talukdao = new TalukDAO();

	/**
     * Creates a new Taluk entity in the database.
     *
     * @param taluk The Taluk object to be created.
     * @return The created Taluk entity.
     * @throws Exception If there's an issue with the database operation or validation.
     */
	public Taluk create(Taluk taluk) throws Exception {

		TalukValidator.validate(taluk);

		talukdao.create(taluk);
		
		return taluk;

	}

	/**
     * Updates a Taluk entity's information in the database.
     *
     * @param newId The ID of the Taluk to be updated.
     * @param newTaluk The updated Taluk object.
     * @return The updated TalukDAO object.
     * @throws ValidationException If the Taluk object is invalid or the operation fails.
     */
	public TalukDAO update(int newId, Taluk newTaluk) throws ValidationException {

		TalukValidator.validate(newTaluk);

		talukdao.update(newId, newTaluk);
		
		return talukdao;

	}

	/**
     * Marks a Taluk entity as inactive in the database.
     *
     * @param id The ID of the Taluk to be marked as inactive.
     */
	public void delete(int Id) {

		talukdao.delete(Id);

	}


	/**
     * Retrieves a list of all active Taluk entities from the database.
     *
     * @return A list of Taluk entities.
     */
	public List<Taluk> getAll() {

		return talukdao.findAll();

	}

}
