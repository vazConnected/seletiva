package vazconnected.seletiva.dti.petShop;

import vazconnected.seletiva.dti.petShop.exception.PetShopServiceNotAvailable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class PetShopDayCost {
    Map<PetSize, Double> prices;

    public PetShopDayCost() {
        this.prices = new HashMap<PetSize, Double>();
    }

    public PetShopDayCost(Map<PetSize, Double> prices) {
        if (prices == null || prices.isEmpty())
            throw new IllegalArgumentException("A tabla de preços para o dia é inválida");
        this.prices = prices;
    }

    public double getPrice(PetSize size) {
        if (!this.prices.containsKey(size)) {
            throw new PetShopServiceNotAvailable("O Petshop não atende pets " + size.toString() + " no dia selecionado");
        }

        return this.prices.get(size);
    }

    public boolean isEmpty() {
        return this.prices.isEmpty();
    }

    public void setPrice(PetSize size, Double value) {
        this.prices.put(size, value);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        Set<PetSize> keys = this.prices.keySet();
        Iterator<PetSize> iterator = keys.iterator();
        while (iterator.hasNext()) {
            PetSize size = iterator.next();
            Double price = this.prices.get(size);

            stringBuilder.append(size.toString() + ": ");
            stringBuilder.append(price);
            if (iterator.hasNext()) stringBuilder.append(" |\t");
        }

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PetShopDayCost) {
            PetShopDayCost target = (PetShopDayCost) obj;
            return target.prices.equals(this.prices);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return this.prices.hashCode();
    }
}
