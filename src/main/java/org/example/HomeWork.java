package org.example;

import java.util.ArrayList;
import java.util.List;


public class HomeWork extends BinaryIntSearchTree {

    /**
     * <h1>Задание 1.</h1>
     * Дан класс BinaryTree, который реализует бинарное дерево поиска.
     * Реализовать метод findMaxDigits, который возвращает массив
     * наибольших элементов в дереве, не превосходящих upperBound.
     * <br/>
     * Пример :
     * коллекция в дереве 1, 2, 3, 4, 5
     * count = 3, upperBound 4
     * ответ [4, 3, 2]
     *
     * @param count      максимальное количество элементов в ответе
     * @param upperBound верхняя граница для поиска элементов
     * @return массив найденных максимальных значений не более чем upperBound и длиной не более count, отсортировано от большего к меньшему
     * Сигнатуру метода не меняем
     */
    public List<Integer> findMaxDigits(int count, int upperBound) {
        if(count <= 0) {
            throw new IllegalArgumentException("сount должен быть больше 0");
        }
        if(upperBound <= 0) {
            throw new IllegalArgumentException("upperBound должен быть больше 0");
        }

        List<Integer> result = new ArrayList<>();
        Node currentNode = findMaxInBoundNode(upperBound);
        while (count > 0 && currentNode != null) {
            result.add(currentNode.value);
            count--;
            currentNode = getNextDescNode(currentNode);
        }
        return result;
    }

    private Node findMaxInBoundNode(int bound) {
        Node found = null;
        Node currentNode = root;
        while (currentNode != null) {
            found = currentNode;
            if (currentNode.value < bound) {
                currentNode = currentNode.right;
            } else if(currentNode.value == bound){
                break;
            } else {
                currentNode = currentNode.left;
            }
        }
        return found;
    }

    private Node getNextDescNode(Node node) {
        Node prevNode;
        if(node.left != null) {
            prevNode = node.left;
            while(prevNode.right != null) {
                prevNode = prevNode.right;
            }
        } else {
            prevNode = node;
            while(prevNode.parent != null && prevNode.parent.left == prevNode){
                prevNode = prevNode.parent;
            }
            prevNode = prevNode.parent;
        }
        return prevNode;
    }
}
