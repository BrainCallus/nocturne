package bloggy.validator;

import com.codeforces.commons.text.StringUtil;
import org.nocturne.validation.ValidationException;
import org.nocturne.validation.Validator;

public class BaseValidator extends Validator {
    @Override
    public void run(String value) throws ValidationException {
        if (checkEmpty(value)) {
            throw new ValidationException($("Field should not be empty"));
        }
    }

    private boolean checkEmpty(String value) {
        return StringUtil.isEmpty(value) || StringUtil.isBlank(value);
    }
}
