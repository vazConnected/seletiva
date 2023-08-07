package vazconnected.seletiva.dti;

import vazconnected.seletiva.dti.petShop.PetShopPriceTable;
import vazconnected.seletiva.dti.petShop.PetSize;

import java.io.*;
import java.nio.file.NotDirectoryException;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.stream.Stream;

public class FileManager {
    private FileManager() {}

    public static String getPetShopName(File file) throws IOException {
        if (!(file.exists() && file.isFile())) {
            throw new FileNotFoundException("É necessário informar um arquivo válido para obter o nome do Pet Shop");
        }

        BufferedReader bufferedReader = new BufferedReader( new FileReader(file));

        String petShopName  = bufferedReader.readLine().trim();
        bufferedReader.close();

        return petShopName;
    }

    public static Double getPetShopDistance(File file) throws IOException {
        if (!(file.exists() && file.isFile())) {
            throw new FileNotFoundException("É necessário informar um arquivo válido para obter o nome do Pet Shop");
        }

        BufferedReader bufferedReader = new BufferedReader( new FileReader(file));

        bufferedReader.readLine(); // Ignorar primeira linha
        Double distance  = Double.parseDouble(bufferedReader.readLine().trim());
        bufferedReader.close();

        return distance;
    }

    public static PetShopPriceTable getPriceTable(File file) throws IOException {
        if (!(file.exists() && file.isFile())) {
            throw new FileNotFoundException("É necessário informar um arquivo válido para obter a tabela de preços para um Pet Shop");
        }

        BufferedReader bufferedReader = new BufferedReader( new FileReader(file));
        String[] allFileLines = bufferedReader.lines().toArray(String[]::new);
        bufferedReader.close();

        PetShopPriceTable priceTable = new PetShopPriceTable();
        int lineInWhichThePriceTableStarts = 3;
        for (int i = lineInWhichThePriceTableStarts; i < allFileLines.length; i++) {
            String[] fields = allFileLines[i].split(",");
            int dayOfTheWeek = Integer.parseInt(fields[0]); // Dia da semana
            PetSize size = PetSize.valueOf(fields[1]); // Tamanho
            double price = Double.parseDouble(fields[2]); // Preço

            if (dayOfTheWeek < 1 || dayOfTheWeek > 7) {
                throw new InvalidParameterException("Dia da semana inválido no arquivo " + file.getPath());
            } else if (price < 0.0) {
                throw new InvalidParameterException("Preço inválido no arquivo " + file.getPath());
            }

            priceTable.registerPrice(dayOfTheWeek, size, price);
        }

        return priceTable;
    }

    public static Stream<File> getFileListFromDirectory(File directory) throws NotDirectoryException {
        if ( !(directory.exists() && directory.isDirectory()) ) {
            throw new NotDirectoryException("É necessário informar um diretório para obter sua lista de arquivos");
        }

        Stream<File> files = Arrays.stream(directory.listFiles()).filter((file -> {
            return file.isFile();
        }));

        return files;
    }
}
