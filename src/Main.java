import java.io.*;
import java.net.*;
import com.google.gson.*;

public class Main {
    private static final String API_KEY = "e42acf7b9412f989fd542644";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD";

    public static void main(String[] args) {
        try {
            while (true) {
                exibirMenu();
                int opcao = lerOpcao();
                if (opcao == 7) {
                    System.out.println("Obrigado por utilizar o Conversor de Moedas. Até mais!");
                    break;
                }
                converterMoeda(opcao);
            }
        } catch (IOException e) {
            System.out.println("Erro ao processar a solicitação: " + e.getMessage());
        }
    }

    private static void exibirMenu() {
        System.out.println("Seja bem-vindo/a ao Conversor de Moeda =]");
        System.out.println("1) Dólar =>> Peso argentino");
        System.out.println("2) Peso argentino =>> Dólar");
        System.out.println("3) Dólar =>> Real brasileiro");
        System.out.println("4) Real brasileiro =>> Dólar");
        System.out.println("5) Dólar =>> Peso colombiano");
        System.out.println("6) Peso colombiano =>> Dólar");
        System.out.println("7) Sair");
        System.out.print("Escolha uma opção válida: ");
    }

    private static int lerOpcao() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return Integer.parseInt(reader.readLine());
    }

    private static void converterMoeda(int opcao) throws IOException {
        String urlStr = API_URL;
        URL url = new URL(urlStr);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        // Convert to JSON
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();

        // Accessing object
        JsonObject rates = jsonobj.getAsJsonObject("conversion_rates");

        switch (opcao) {
            case 1:
                System.out.println("Digite o valor em dólar:");
                double valorDolar1 = lerValor();
                double valorConvertido1 = valorDolar1 * rates.get("ARS").getAsDouble();
                System.out.println("Valor em Peso argentino: " + valorConvertido1);
                break;
            case 2:
                System.out.println("Digite o valor em Peso argentino:");
                double valorPesoArgentino = lerValor();
                double valorConvertido2 = valorPesoArgentino / rates.get("ARS").getAsDouble();
                System.out.println("Valor em Dólar: " + valorConvertido2);
                break;
            case 3:
                System.out.println("Digite o valor em dólar:");
                double valorDolar3 = lerValor();
                double valorConvertido3 = valorDolar3 * rates.get("BRL").getAsDouble();
                System.out.println("Valor em Real brasileiro: " + valorConvertido3);
                break;
            case 4:
                System.out.println("Digite o valor em Real brasileiro:");
                double valorReal = lerValor();
                double valorConvertido4 = valorReal / rates.get("BRL").getAsDouble();
                System.out.println("Valor em Dólar: " + valorConvertido4);
                break;
            case 5:
                System.out.println("Digite o valor em dólar:");
                double valorDolar5 = lerValor();
                double valorConvertido5 = valorDolar5 * rates.get("COP").getAsDouble();
                System.out.println("Valor em Peso colombiano: " + valorConvertido5);
                break;
            case 6:
                System.out.println("Digite o valor em Peso colombiano:");
                double valorPesoColombiano = lerValor();
                double valorConvertido6 = valorPesoColombiano / rates.get("COP").getAsDouble();
                System.out.println("Valor em Dólar: " + valorConvertido6);
                break;
            default:
                System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
        }
    }

    private static double lerValor() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return Double.parseDouble(reader.readLine());
    }
}