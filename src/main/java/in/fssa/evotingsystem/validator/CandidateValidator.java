package in.fssa.evotingsystem.validator;

import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.model.Candidate;
import in.fssa.evotingsystem.util.StringUtil;

public class CandidateValidator {
	
	public static void validate(Candidate newCandidate) throws ValidationException {

		if (newCandidate == null) {
			throw new ValidationException("Invalid Candidate Input");
		}

		StringUtil.rejectIfInvalidString(newCandidate.getCandidateName(), "Name");

	}

}
