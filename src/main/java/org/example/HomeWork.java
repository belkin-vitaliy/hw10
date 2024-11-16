package org.example;


import lombok.SneakyThrows;

import java.io.*;
import java.util.List;

public class HomeWork {

    /**
     * <h1>Задание 1.</h1>
     * Решить задачу https://codeforces.com/contest/356/problem/A
     */
    @SneakyThrows
    public void championship(InputStream in, OutputStream out) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        PrintWriter writer = new PrintWriter(out);

        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        // Создаем красно-черное дерево для отслеживания активных рыцарей
        RedBlackTree<Integer> activeKnights = new RedBlackTree<>();
        for (int i = 1; i <= n; i++) {
            activeKnights.add(i);
        }

        int[] defeatedBy = new int[n + 1]; // Массив, который хранит информацию о том, кем был побежден каждый рыцарь

        for (int i = 0; i < m; i++) {
            String[] fightData = br.readLine().split(" ");
            int l = Integer.parseInt(fightData[0]);
            int r = Integer.parseInt(fightData[1]);
            int winner = Integer.parseInt(fightData[2]);

            // Получаем всех рыцарей в диапазоне [l, r]
            List<Integer> toRemove = activeKnights.getRange(l, r);

            // Обрабатываем каждого рыцаря в диапазоне
            for (int knight : toRemove) {
                if (knight != winner) {
                    defeatedBy[knight] = winner;
                }
                activeKnights.remove(knight); // Удаляем из дерева всех рыцарей в диапазоне
            }

            // Победитель снова добавляется в дерево (если был удален)
            activeKnights.add(winner);
        }

        // Формируем выходной результат
        for (int i = 1; i <= n; i++) {
            writer.print(defeatedBy[i] + " ");
        }
        writer.flush();

    }



}
