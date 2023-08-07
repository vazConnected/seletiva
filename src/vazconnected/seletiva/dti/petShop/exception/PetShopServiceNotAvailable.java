package vazconnected.seletiva.dti.petShop.exception;

public class PetShopServiceNotAvailable extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PetShopServiceNotAvailable(String message) {
        super(message);
    }
}
