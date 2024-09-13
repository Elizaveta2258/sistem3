import java.util.*;

 class DanceMarathonVoting {

    // Константы
    private static final int max = 16;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Считываем количество SMS-сообщений
        System.out.print("Введите количество SMS-сообщений: ");
        int N = scanner.nextInt();

        // Проверка на допустимое количество голосов
        if (N <= 0) {
            System.out.println("Количество голосов должно быть больше 0.");
            return;
        }

        // Массив для хранения голосов за пары
        int[] votes = new int[max + 1]; // Индексы пар от 1 до 16, 0 не используется

        // Считываем номера пар и подсчитываем голоса
        System.out.println("Введите номера пар (от 1 до 16): ");
        for (int i = 0; i < N; i++) {
            int pairNumber = scanner.nextInt();
            if (pairNumber < 1 || pairNumber > max) {
                System.out.println("Номер пары должен быть в диапазоне от 1 до 16.");
                i--; // Уменьшаем счетчик, чтобы повторно считать этот голос
                continue;
            }
            votes[pairNumber]++;
        }

        // Создаём список для хранения пар и их голосов
        List<PairVotes> pairVotesList = new ArrayList<>();
        for (int i = 1; i <= max; i++) {
            pairVotesList.add(new PairVotes(i, votes[i]));
        }

        // Сортируем список по количеству голосов (по убыванию)
        Collections.sort(pairVotesList, (a, b) -> b.votes - a.votes);

        // Вывод результатов
        System.out.println("Результаты голосования:");
        for (PairVotes pairVotes : pairVotesList) {
            // Выводим только пар с голосами больше 0
            if (pairVotes.votes > 0) {
                System.out.println(pairVotes.pairNumber + " " + pairVotes.votes);
            }
        }

        // Замыкаем сканер
        scanner.close();
    }

    // Вспомогательный класс для хранения номера пары и количества голосов
    static class PairVotes {
        int pairNumber;
        int votes;

        PairVotes(int pairNumber, int votes) {
            this.pairNumber = pairNumber;
            this.votes = votes;
        }
    }
}
