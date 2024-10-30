package org.application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.entities.Conversao;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitor = new Scanner(System.in);
        int opcao = 0;
        String moedaAtual = "";
        String moedaConvertida = "";
        List<String> list = new ArrayList<>();
        Gson gson = new GsonBuilder().create();
        while (opcao != 7){
            System.out.println("******************************************");
            System.out.println("Seja bem-vindo ao Conversor de Moeda =]");
            System.out.println();
            System.out.println("1) Dólar >> Peso argentino");
            System.out.println("2) Peso argentino >> Dólar");
            System.out.println("3) Dólar >> Real brasileiro");
            System.out.println("4) Real brasileiro >> Dólar");
            System.out.println("5) Dólar >> Peso colombiano");
            System.out.println("6) Peso colombiano >> Dólar");
            System.out.println("7) Sair");
            System.out.println("Escolha uma opção válida: ");
            System.out.println("********************************");
            opcao = leitor.nextInt();

            switch (opcao){
                case 1:
                    moedaAtual = "USD/";
                    moedaConvertida = "ARS/";
                    break;
                case 2:
                    moedaAtual = "ARS/";
                    moedaConvertida = "USD/";
                    break;
                case 3:
                    moedaAtual = "USD/";
                    moedaConvertida = "BRL/";
                    break;
                case 4:
                    moedaAtual = "BRL/";
                    moedaConvertida = "USD/";
                    break;
                case 5:
                    moedaAtual = "USD/";
                    moedaConvertida = "COP/";
                    break;
                case 6:
                    moedaAtual = "COP/";
                    moedaConvertida = "USD/";
                    break;
                case 7:
                    System.out.println("Finalizando conversor");
                    break;
                default:
                    System.out.println("Opção inválida! Por favor escolha uma opção válida.");
                    break;

            }
            if (opcao == 7){
                break;
            }
            //passo 2
            System.out.print("Valor a ser convertido: ");
            double valor = leitor.nextDouble();
            String endereco = "https://v6.exchangerate-api.com/v6/208779a3892b64f829308c75/pair/" + moedaAtual + moedaConvertida + valor;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endereco))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            Conversao conversao = gson.fromJson(json, Conversao.class);
            System.out.println(conversao);

        }
    }
}