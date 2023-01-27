package mx.com.tutum.shared.domain;

import org.apache.commons.lang3.StringUtils;

public class AuthException extends RuntimeException {

	private static final long serialVersionUID = -1601660638177962471L;

	private static final String UNKNOWN_PERMISSION = "?";
	
	private String permission = UNKNOWN_PERMISSION;

	public AuthException(String message) {
		this(message, null, null);
	}
	
	public AuthException(String message, String permission) {
		this(message, permission, null);
	}
	
	public AuthException(String message, String permission, String alternativePermission) {
		super(message);
		this.setPermission(permission, alternativePermission);
	}

	private void setPermission(String permission, String alternativePermission) {
		if (StringUtils.isNoneBlank(permission, alternativePermission)) {
			if (StringUtils.equals(permission, alternativePermission)) {
				this.permission = permission;
			} else {
				this.permission = String.join(",", permission, alternativePermission);
			}
		} else if (StringUtils.isNotBlank(permission)) {
			this.permission = permission;
		} else if (StringUtils.isNotBlank(alternativePermission)) {
			this.permission = alternativePermission;
		}
	}
	
	public String getPermission() {
		return this.permission;
	}
	
	@Override
	public String getMessage() {
		if (StringUtils.isBlank(getPermission()) || UNKNOWN_PERMISSION.equals(getPermission())) {
			return super.getMessage();
		}
		
		return super.getMessage().replaceFirst("\\W+$", ": ").concat(getPermission());
	}
	
}

