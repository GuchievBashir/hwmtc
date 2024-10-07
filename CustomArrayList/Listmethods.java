package org.example.CustomArrayList;

/**
 * @param <E> тип элементов в создаваемом списке
 */
public class Listmethods<E> {
  /**
   * Добавляет элемент в конец списка
   * @param value добавляемый элемент
   */
  void add(E value);
  /**
   * Удаляет элемент списка по выбранному индексу
   * @param index индекс удаляемого элемента
   * @return элемент удаляемый из списка
   */
  E remove(int index);
  /**
   * Возвращает элемент по указанному индексу
   * @param index индекс возвращаемого элемента
   * @return элемент стоящий по указанному индексу
   */
  E get(int index);
}