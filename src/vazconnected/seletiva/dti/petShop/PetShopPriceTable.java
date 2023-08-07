package vazconnected.seletiva.dti.petShop;

import vazconnected.seletiva.dti.petShop.exception.PetShopServiceNotAvailable;

import java.security.InvalidParameterException;

public class PetShopPriceTable {
    private PetShopDayCost[] weeklyPrices;

    public PetShopPriceTable() {
        this.weeklyPrices = new PetShopDayCost[7];
    }

    public Double getPrice(int dayOfTheWeek, PetSize size) throws PetShopServiceNotAvailable{
        if (dayOfTheWeek < 1 || dayOfTheWeek > 7) {
            throw new InvalidParameterException("É necessário informar um dia da semana no intervalo [1, 7] para consultar o preço por tamanho.");
        } else if (this.weeklyPrices[dayOfTheWeek -1] == null) {
            throw new PetShopServiceNotAvailable("Nenhum serviço está disponível para o dia " + dayOfTheWeek + " da semana.");
        }

        return this.weeklyPrices[dayOfTheWeek - 1].getPrice(size);
    }

    public void registerPrice(int dayOfTheWeek, PetSize size, double price) {
        if (dayOfTheWeek < 1 || dayOfTheWeek > 7) {
            throw new InvalidParameterException("Os dias da semana variam no intervalo [1, 7], em que o 1o dia é Domingo");
        }

        PetShopDayCost petShopDayCost = this.weeklyPrices[dayOfTheWeek - 1];
        if (petShopDayCost == null || petShopDayCost.isEmpty()) {
            this.weeklyPrices[dayOfTheWeek - 1] = new PetShopDayCost();
        }

        this.weeklyPrices[dayOfTheWeek - 1].setPrice(size, price);
    }

    public void removePriceCoast(int dayOfTheWeek) {
        if (dayOfTheWeek < 1 || dayOfTheWeek > 7) {
            throw new InvalidParameterException(" Os dias da semana variam no intervalo [1, 7], em que o 1o dia é Domingo");
        }

        this.weeklyPrices[dayOfTheWeek - 1] = null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Tabela de Preços:\n");

        for (int i = 0; i < weeklyPrices.length; i++) {
            if (weeklyPrices[i] != null) {
                stringBuilder.append("\t" + convertNumberToDayOfTheWeek(i+1) + ": \n");
                stringBuilder.append("\t\t" + weeklyPrices[i].toString() + "\n");
            }
        }

        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    public static String convertNumberToDayOfTheWeek(int weekDay) {
        switch(weekDay) {
            case 1: return "DOMINGO";
            case 2: return "SEGUNDA-FEIRA";
            case 3: return "TERÇA-FEIRA";
            case 4: return "QUARTA-FEIRA";
            case 5: return "QUINTA-FEIRA";
            case 6: return "SEXTA-FEIRA";
            case 7: return "SÁBADO";
            default:
                throw new InvalidParameterException("É necessário informar um dia da semana no intervalo [1, 7].");
        }
    }


}
