package vazconnected.seletiva.dti;

import vazconnected.seletiva.dti.petShop.PetShop;
import vazconnected.seletiva.dti.petShop.PetShopPriceTable;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

public class Main {
    private static Set<PetShop> petShops = new HashSet<PetShop>();

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            Scanner scanner = new Scanner(System.in);
            args = scanner.nextLine().split(" ");
            scanner.close();
        } 
        
        if (args.length != 3) {
        	throw new IllegalArgumentException("É necessário informar data, numero de cachorros grandes e número de cachorros peguenos");
        }

        String[] dateFields = args[0].split("/");
        if (dateFields.length != 3) {
        	throw new IllegalArgumentException("Data inválida. Informe-a no formato dd/mm/yyyy");
        }
        LocalDate date = LocalDate.of(Integer.parseInt(dateFields[2]), Integer.parseInt(dateFields[1]),Integer.parseInt(dateFields[0]));

        int dayOfTheWeek = Main.convertDayOfTheWeekToInt(date.getDayOfWeek().toString());
        int numberOfSmallDogs = Integer.parseInt(args[1]);
        int numberOfBigDogs = Integer.parseInt(args[2]);
        
        if (numberOfSmallDogs < 0 || numberOfBigDogs < 0) {
        	throw new IllegalArgumentException("Quantidade de cachorros inválida!");
        }

        Main.loadAllPetShopsFromDirectory(new File("petShops"));
        Main.showBestOption(dayOfTheWeek, numberOfSmallDogs, numberOfBigDogs);
    }

    private static void loadAllPetShopsFromDirectory(File directory) {
        try {
            Stream<File> files = FileManager.getFileListFromDirectory(new File("petShops"));
            for (Object o : files.toArray()) {
                File f = (File) o;

                String name = FileManager.getPetShopName(f);
                double distance = FileManager.getPetShopDistance(f);
                PetShopPriceTable priceTable = FileManager.getPriceTable(f);

                Main.petShops.add(new PetShop(name, distance, priceTable));
            }
        } catch (IOException e) {
            System.err.println("Erro fatal. Não foi possível carregar os petShops por meio dos arquivos");
            System.exit(1);
        }
    }

    private static void showBestOption(int dayOfTheWeek, int numberOfSmallDogs, int numberOfBigDogs) {
        PetShop chosenPetShop = null;
        Double bestPrice = Double.MAX_VALUE;

        Iterator<PetShop> iterator = Main.petShops.iterator();
        while (iterator.hasNext()) {
            PetShop curretPetShop = iterator.next();

            try {
                double currentPrice = curretPetShop.getPriceOfServices(dayOfTheWeek, numberOfSmallDogs, numberOfBigDogs);
                if (currentPrice < bestPrice) {
                    chosenPetShop = curretPetShop;
                    bestPrice = currentPrice;
                } else if ( (currentPrice == bestPrice) && (curretPetShop.getDistance() < chosenPetShop.getDistance()) ) {
                    chosenPetShop = curretPetShop;
                    bestPrice = currentPrice;
                }
 
            } catch (Exception e) {
                System.err.println("O pet shop " + curretPetShop.getName() + " não atende os requisitos de serviço solicitados.\n" + e.getMessage());
            }
        }

        if (chosenPetShop == null) {
            System.out.println("null null");
        } else {
            System.out.println(chosenPetShop.getName() + " " + bestPrice.toString());
        }
    }

    private static int convertDayOfTheWeekToInt(String dayOfTheWeek) {
        switch(dayOfTheWeek) {
            case "SUNDAY": return 1;
            case "MONDAY": return 2;
            case "TUESDAY": return 3;
            case "WEDNESDAY": return 4;
            case "THURSDAY": return 5;
            case "FRIDAY": return 6;
            case "SATURDAY": return 7;
            default: throw new IllegalArgumentException("Dia da semana inválido: " + dayOfTheWeek);
        }
    }

}