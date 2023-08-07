package vazconnected.seletiva.dti.petShop;

public enum PetSize {
    SMALL,
    BIG;

    @Override
    public String toString() {
        switch (this.ordinal()) {
            case 0: return "PEQUENO";
            case 1: return "GRANDE";
            default: return null;
        }
    }

}
