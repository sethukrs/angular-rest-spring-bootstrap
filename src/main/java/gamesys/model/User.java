package gamesys.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class User {

	private int id;
	private String username;
	private String password;
	private String dob;
	private String ssn;
	
	public User(){
		id=0;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.getSsn().hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (ssn == null) {
			if (other.ssn != null)
				return false;
		} else if(!this.getSsn().equals(other.getSsn()))
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", dob=" + dob
				+ ", ssn=" + ssn + "]";
	}
}
