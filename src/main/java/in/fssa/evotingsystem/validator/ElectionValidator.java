package in.fssa.evotingsystem.validator;

import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.model.Election;
import in.fssa.evotingsystem.util.StringUtil;

public class ElectionValidator {

	public static void validate(Election newElection) throws ValidationException {

		if (newElection == null) {
			throw new ValidationException("Invalid Election Input");
		}

		StringUtil.rejectIfInvalidString(newElection.getElectionName(), "Election Name");
		StringUtil.rejectIfInvalidString(newElection.getBoothAddress(), "Booth Address");

	}
}
