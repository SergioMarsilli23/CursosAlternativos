package mx.com.tutum.shared.domain;

public class ObjectNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 8377783889081007749L;

	public ObjectNotFoundException(String message) {
        super(message);
    }
}
