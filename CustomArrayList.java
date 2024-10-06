//package org.example.CustomArrayList;

//import org.example.CustomArrayList.Listmethods;

/**
 * @param <E> тип элементов в создаваемом списке
 */
public class CustomArrayList<E> implements Listmethods<E> {
  private int size;
  private int capacity;
  private Object[] elements;

  /**
   * Конструктор, который создает изначальный список размером 10
   */
  public CustomArrayList() {
    this.size = 0;
    this.capacity = 10;
    this.elements = new Object[this.capacity];
  }

  /**
   * Возвращает длину списка
   */
   public int length() {
    return this.size;
  }

  /**
   * @return True если список пустой
   * @return False если непустой
   */
  public boolean isEmpty() {
     return this.size == 0;
  }

  /**
   * Пересоздает список в памяти, когда нашей изначальной длины недостаточно
   */
  private void Realloc() {
    this.capacity *= 2;
    Object[] newElements = new Object[this.capacity];
    System.arraycopy(this.elements, 0, newElements, 0, this.size);
    this.elements = newElements;
  }

  /**
   * Добавляет элемент в конец списка
   * @param value добавляемый элемент
   */
  @Override
  public void add(E value) {
    if (this.size == this.capacity) {
       this.Realloc();
    }
    this.elements[this.size] = value;
    this.size++;
  }

  /**
   * Удаляет элемент списка по выбранному индексу
   * @param index индекс удаляемого элемента
   * @return элемент удаляемый из списка
   */
  @Override
  public E remove(int index) {
    if (index < 0 || index >= this.size) {
      throw new IndexOutOfBoundsException("Index: " + index + "out of bounds for length" + this.size);
    }
    E removed = this.get(index);
    if (index == this.size - 1) {
      this.size--;
    } else {
      for (int i = this.size - 1; i > index; i--) {
        this.elements[i - 1] = this.elements[i];
      }
      this.size--;
    }
  }

  /**
   * Возвращает элемент по указанному индексу
   * @param index индекс возвращаемого элемента
   * @return элемент стоящий по указанному индексу
   */
  @Override
  public E get(int index) {
    if (index < 0 || index >= this.size) {
      throw new IndexOutOfBoundsException("Index: " + index + "out of bounds for length" + this.size);
    }
    return (E) this.elements[index];
  }
}