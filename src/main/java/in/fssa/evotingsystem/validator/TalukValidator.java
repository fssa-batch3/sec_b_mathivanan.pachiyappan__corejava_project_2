package in.fssa.evotingsystem.validator;

import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.model.Taluk;
import in.fssa.evotingsystem.util.StringUtil;

public class TalukValidator {
	
	public static void validate(Taluk newTaluk) throws ValidationException {

		if (newTaluk == null) {
			throw new ValidationException("Invalid Taluk Input");
		}
		
		if (newTaluk.getId() <= 600000001 && newTaluk.getId() >= 999999999) {

			throw new ValidationException("Invalid Taluk ID");
		}
		
		StringUtil.rejectIfInvalidString(newTaluk.getTalukName(), "Taluk Name");

	}
}
