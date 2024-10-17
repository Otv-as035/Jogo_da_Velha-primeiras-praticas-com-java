import java.util.Scanner;

public class Jogo_da_velha {
    // Dados privados: matriz 3x3 para a grade do jogo
    private char[][] grade = new char[3][3];
    
    // Construtor: inicializa a grade vazia
    public Jogo_da_velha() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grade[i][j] = ' '; // Inicializa todas as casas como vazias
            }
        }
    }
    
    // Método para exibir a grade
    public void exibirGrade() {
        System.out.println("  0 1 2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(grade[i][j]);
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("  -----");
        }
    }

    // Método para verificar se o movimento é válido (se a casa está vazia)
    public boolean movimentoValido(int linha, int coluna) {
        return linha >= 0 && linha < 3 && coluna >= 0 && coluna < 3 && grade[linha][coluna] == ' ';
    }

    // Método para jogar o jogo
    public void jogar() {
        Scanner scanner = new Scanner(System.in);
        char jogadorAtual = 'X';
        boolean jogoAtivo = true;
        int turnos = 0;

        while (jogoAtivo) {
            exibirGrade();
            System.out.println("Jogador " + jogadorAtual + ", faça sua jogada (linha e coluna): ");
            
            int linha = scanner.nextInt();
            int coluna = scanner.nextInt();

            if (movimentoValido(linha, coluna)) {
                grade[linha][coluna] = jogadorAtual;
                turnos++;

                // Verifica se houve vitória ou empate
                if (verificarVitoria(jogadorAtual)) {
                    exibirGrade();
                    System.out.println("Jogador " + jogadorAtual + " venceu!");
                    jogoAtivo = false;
                } else if (turnos == 9) {
                    exibirGrade();
                    System.out.println("Empate!");
                    jogoAtivo = false;
                } else {
                    // Alterna o jogador
                    jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
                }
            } else {
                System.out.println("Movimento inválido! Tente novamente.");
            }
        }
        scanner.close();
    }

    // Método para verificar vitória
    public boolean verificarVitoria(char jogador) {
        // Verifica linhas, colunas e diagonais
        for (int i = 0; i < 3; i++) {
            if (grade[i][0] == jogador && grade[i][1] == jogador && grade[i][2] == jogador) {
                return true;
            }
            if (grade[0][i] == jogador && grade[1][i] == jogador && grade[2][i] == jogador) {
                return true;
            }
        }
        // Verifica as diagonais
        if (grade[0][0] == jogador && grade[1][1] == jogador && grade[2][2] == jogador) {
            return true;
        }
        if (grade[0][2] == jogador && grade[1][1] == jogador && grade[2][0] == jogador) {
            return true;
        }
        return false;
    }

    // Método principal para iniciar o jogo
    public static void main(String[] args) {
        Jogo_da_velha jogo = new Jogo_da_velha();
        jogo.jogar();
    }
}
