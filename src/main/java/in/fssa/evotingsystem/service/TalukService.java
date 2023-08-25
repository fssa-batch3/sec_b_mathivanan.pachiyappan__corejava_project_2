package in.fssa.evotingsystem.service;

import java.util.List;

import com.google.protobuf.ServiceException;

import in.fssa.evotingsystem.dao.TalukDAO;
import in.fssa.evotingsystem.exception.PersistanceException;
import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.model.Taluk;
import in.fssa.evotingsystem.validator.TalukValidator;

/**
 * This class provides services related to managing taluks.
 */
public class TalukService {

    private TalukDAO talukDAO = new TalukDAO();

    /**
     * Creates a new taluk.
     *
     * @param taluk The taluk to be created.
     * @throws ServiceException If there was an issue creating the taluk.
     * @throws ValidationException If the provided taluk data is not valid.
     */
    public void createTaluk(Taluk taluk) throws ServiceException, ValidationException {
        try {
            TalukValidator.validate(taluk);
            talukDAO.create(taluk);
        } catch (PersistanceException e) {
            throw new ServiceException("Failed to Create Taluk");
        }
    }

    /**
     * Updates an existing taluk's information.
     *
     * @param newId The ID of the taluk to be updated.
     * @param newTaluk The updated taluk information.
     * @throws ServiceException If there was an issue updating the taluk.
     * @throws ValidationException If the provided taluk data is not valid.
     */
    public void updateTaluk(int newId, Taluk newTaluk) throws ServiceException, ValidationException {
        try {
            TalukValidator.validate(newTaluk);
            TalukValidator.isIdExists(newId);
            talukDAO.update(newId, newTaluk);
        } catch (PersistanceException e) {
            throw new ServiceException("Failed to Update Taluk");
        }
    }

    /**
     * Finds a taluk by its ID.
     *
     * @param id The ID of the taluk to be found.
     * @return The found taluk, or null if not found.
     * @throws ServiceException If there was an issue finding the taluk.
     * @throws ValidationException If the provided taluk ID is not valid.
     */
    public Taluk findByTalukId(int id) throws ServiceException, ValidationException {
        try {
            TalukValidator.validateId(id);
            return talukDAO.findById(id);
        } catch (PersistanceException e) {
            throw new ServiceException("Failed to Find Taluk");
        }
    }

    /**
     * Deletes a taluk by ID.
     *
     * @param id The ID of the taluk to be deleted.
     * @throws ServiceException If there was an issue deleting the taluk.
     * @throws ValidationException If the provided taluk ID is not valid.
     */
    public void deleteTaluk(int id) throws ServiceException, ValidationException {
        try {
            TalukValidator.validateId(id);
            TalukValidator.isIdExists(id);
            talukDAO.delete(id);
        } catch (PersistanceException e) {
            throw new ServiceException("Failed to Delete Taluk");
        }
    }

    /**
     * Retrieves a list of all taluks.
     *
     * @return The list of all taluks.
     * @throws ServiceException If there was an issue retrieving the taluks.
     */
    public List<Taluk> getAllTaluk() throws ServiceException {
        try {
            return talukDAO.findAll();
        } catch (PersistanceException e) {
            throw new ServiceException("Failed to List All Taluks");
        }
    }
}
