import java.util.*;

public class JogoDigitacao {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== CONFIGURAÇÃO DO TESTE ===");
        System.out.println("Cole aqui o texto que servirá de referência e aperte ENTER:");
        String referencia = scanner.nextLine();

        if (referencia.trim().isEmpty()) {
            System.out.println("O texto de referência não pode estar vazio. Reinicie o programa.");
            return;
        }

        System.out.println("\n-------------------------------------------");
        System.out.println("Tudo pronto! Quando você apertar ENTER, o cronômetro começa.");
        System.out.println("Tente digitar exatamente igual ao texto acima.");
        System.out.println("-------------------------------------------");
        scanner.nextLine();

        System.out.println("VALENDO!!!\n");
        System.out.println("REFERÊNCIA: " + referencia);
        System.out.print("DIGITE AQUI: ");

        long inicio = System.currentTimeMillis();
        String digitado = scanner.nextLine();
        long fim = System.currentTimeMillis();

        double tempoSegundos = (fim - inicio) / 1000.0;
        if (tempoSegundos > 300) tempoSegundos = 300; // Limite de 5 min

        processarResultados(referencia, digitado, tempoSegundos);
        scanner.close();
    }

    private static void processarResultados(String ref, String dig, double tempo) {
        int erros = 0;
        int acertos = 0;
        int totalRef = ref.length();
        int totalDig = dig.length();

        // Compara até o limite do menor texto para evitar erro de índice
        int limite = Math.min(totalRef, totalDig);

        for (int i = 0; i < limite; i++) {
            if (ref.charAt(i) == dig.charAt(i)) {
                acertos++;
            } else {
                erros++;
            }
        }

        // Se o usuário digitou menos ou mais caracteres, contamos como erro
        erros += Math.abs(totalRef - totalDig);

        // Cálculos
        double precisao = ((double) acertos / Math.max(totalRef, totalDig)) * 100;
        double kpm = (totalDig / tempo) * 60;
        double wpm = kpm / 5;

        System.out.println("\n===========================================");
        System.out.println("             RELATÓRIO DE DESEMPENHO       ");
        System.out.println("===========================================");
        System.out.printf("Tempo:         %.2f segundos\n", tempo);
        System.out.printf("Sua Precisão:  %.2f%%\n", precisao);
        System.out.printf("Erros Totais:  %d\n", erros);
        System.out.printf("Velocidade:    %.2f WPM (Palavras/min)\n", wpm);
        System.out.println("-------------------------------------------");

        exibirRank(wpm, precisao);
    }

    private static void exibirRank(double wpm, double precisao) {
        System.out.print("STATUS: ");
        if (precisao < 70) {
            System.out.println("🙈 Digitação às cegas (muitos erros!)");
        } else if (wpm < 30) {
            System.out.println("🐢 Tartaruga Pensativa");
        } else if (wpm < 60) {
            System.out.println("⚡ Digitação Sólida");
        } else {
            System.out.println("🔥 Fogo no Teclado!");
        }
        System.out.println("===========================================");
    }
}