package vazconnected.seletiva.dti.petShop;


import vazconnected.seletiva.dti.petShop.exception.PetShopServiceNotAvailable;

import java.security.InvalidParameterException;

public class PetShop {
    private String name;
    private Double distance;
    private PetShopPriceTable priceTable;

    public PetShop(String name, Double distance) {
        if (name == null || distance == null) {
            throw new NullPointerException("É necessário informar nome e distância para registrar um PetShop.");
        }

        this.name = name;
        this.distance = distance;
        this.priceTable = new PetShopPriceTable();
    }

    public PetShop(String name, Double distance, PetShopPriceTable priceTable) {
        if (name == null || distance == null || priceTable == null) {
            throw new NullPointerException("É necessário informar nome, distância e tabela de preços para registrar um PetShop.");
        }

        this.name = name;
        this.distance = distance;
        this.priceTable = priceTable;
    }

    public String getName() {
        return this.name;
    }

    public Double getDistance() {
        return this.distance;
    }

    public double getPrice(int dayOfTheWeek, PetSize size) throws PetShopServiceNotAvailable {
        return priceTable.getPrice(dayOfTheWeek, size);
    }

    public void putInPriceTable(int dayOfTheWeek, PetSize size, double price) {
        this.priceTable.registerPrice(dayOfTheWeek, size, price);
    }

    public void removeFromPriceTable(int dayOfTheWeek) {
        this.priceTable.removePriceCoast(dayOfTheWeek);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Nome: " + this.name + "\n");
        stringBuilder.append("Distância: " + this.distance + "\n");
        stringBuilder.append(this.priceTable);

        return stringBuilder.toString();
    }

    public double getPriceOfServices(int dayOfTheWeek, int numberOfSmallDogs, int numberOfBigDogs) {
        if (dayOfTheWeek < 1 || dayOfTheWeek > 7) {
            throw new InvalidParameterException("Os dias da semana variam no intervalo [1, 7], em que o 1o dia é Domingo");
        } else if (numberOfSmallDogs < 0 || numberOfBigDogs < 0) {
            throw new InvalidParameterException("Não é possível informar uma quantidade negativa de cachorros");
        }

        double priceForSmallDogs = this.priceTable.getPrice(dayOfTheWeek, PetSize.SMALL) * numberOfSmallDogs;
        double priceForBigDogs = this.priceTable.getPrice(dayOfTheWeek, PetSize.BIG) * numberOfBigDogs;

        return priceForSmallDogs + priceForBigDogs;
    }

}
