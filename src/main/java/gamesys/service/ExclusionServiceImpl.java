package gamesys.service;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import gamesys.exception.InvalidDOBException;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service("exclusionService")
public class ExclusionServiceImpl implements ExclusionService {

    private static Set<String> BLACKLISTED_USERS = new HashSet<>();
    static {
        BLACKLISTED_USERS.add("1985-01-01_999-11-1111");
        BLACKLISTED_USERS.add("1985-01-02_999-11-1112");
        BLACKLISTED_USERS.add("1985-01-03_999-11-1113");
        BLACKLISTED_USERS.add("1985-01-04_999-11-1114");
        BLACKLISTED_USERS.add("1985-01-05_999-11-1115");
    }

    /**
     * A fake implementation to the Interface.
     *
     */
    public boolean validate(String dob, String ssn) {
        try {
            ISO8601DateFormat df = new ISO8601DateFormat();
            FastDateFormat fastDateFormat = FastDateFormat.getInstance("yyyy-MM-dd");
            Date date = df.parse(dob);
            if (BLACKLISTED_USERS.contains(fastDateFormat.format(date) + "_" + ssn)) {
                return false;
            }
        } catch (ParseException e) {
            throw new InvalidDOBException();
        }
        return true;
    }
}
