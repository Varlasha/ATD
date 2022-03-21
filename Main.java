/*ЗАДАНИЕ

1.	Необходимо реализовать обобщенный класс Список на массиве (необходимые открытые методы перечислены выше). 
2.	Реализовать класс Рациональных чисел (см. ниже).
3.	Реализовать класс с общим методом удаления дубликатов из списка (только для типов реализующий интерфейс Comparable(<T>)). В main() (в этом же классе) проверить метод удаления дубликатов для классов Рациональных чисел, String и Integer.

Класс Рациональных чисел (реализовывает интерфейс Comparable(<T>)). Для этого надо реализовать единственный метод: 
public int compareTo(T obj);
Использование: a.compareTo(b);

Возвращаемый результат: целое число <0 a<b, =0 a==b; >0 a>b.

Рациональное число – дробь, где a – числитель, q – знаменатель - a/q,
a – целое, q – натуральное.
0/1 - ноль для рациональных чисел.
Если знаменатель 0 (ноль) выбросить ArithmeticException.
Храниться дроби должны в несократимом виде, т. е. НОД(a, q) = 1.
toString() – переопределить (дробь при выводе на печать должна быть правильной a<q):
целая_часть числитель/знаменатель.

Общие требования:
1. Не использовать коллекции.
2. Не использовать доступ по умолчанию. Указывать доступ для классов, данных, методов.
3. Обязательно наличие в коде комментариев.
*/
package rational;

import java.util.Random;

public class Main {

public static void main(String[] args) {

final int size = 9;

ListImplement <String> pr1 = new ListImplement<String>(new String[10]);

ListImplement <Integer> pr2 = new ListImplement<Integer>(new Integer[10]);

ListImplement <Rational> pr3 = new ListImplement<Rational>(new Rational[10]);

Random random = new Random();

for (int i = 0; i < size; i++){

String pr = "";

pr += random.nextInt(100);

pr1.Insert(pr,pr1.End());

}

for (int i = 0; i < size; i++){

pr2.Insert(random.nextInt(100),pr2.End());

}

for (int i = 0; i < size; i++){

pr3.Insert(new Rational(random.nextInt(100), random.nextInt(100)), pr3.End());

}

pr1.Printlist();

pr2.Printlist();

pr3.Printlist();

deleteDub(pr1);

deleteDub(pr2);

deleteDub(pr3);

pr1.Printlist();

pr2.Printlist();

pr3.Printlist();

}

private static <T extends Comparable<T>> void deleteDub(ListImplement<T> el) {

for (int i = el.First(); i < el.End(); i=el.Next(i)) {

T danMom = el.Retrive(i); // элемент с которым сравниваю

T vrem = el.Retrive(el.Next(i)); //элемент который сравниваем

int j = el.Next(i);

while (j != el.End()) {

if (danMom.compareTo(vrem) == 0) {

el.Delete(j);

j=el.Previous(j);

}

vrem = el.Retrive(el.Next(j));

j=el.Next(j);

}

}

}

}

package rational;

public class Rational implements Comparable<Rational> {

private int numerator;

private int denominator;

public Rational(int num, int den) {

if (den == 0)

throw new ArithmeticException("На ноль делить нельзя");

numerator= num;

denominator= den;

int gcd=gcd(Math.abs(numerator),Math.abs(denominator));

numerator/=gcd;

denominator/=gcd;

if (denominator<0){

numerator *=-1;

denominator *=-1;

}

}

private int gcd(int a, int b){//наибольший общий делитель

return b == 0 ? a : gcd(b, a % b);

}

public String toString(){

String string = "";

if (Math.abs(numerator)<denominator)

string += numerator+"/"+denominator;

else{

string += (numerator/denominator);

if ((Math.abs(numerator)%denominator)!=0){

string += " "+(Math.abs(numerator)%denominator)+"/"+denominator;}

}

return string;

}

@Override

public int compareTo(Rational two) {

return (numerator*two.denominator)-(two.numerator*denominator);

}

}

package rational;

public class ListImplement <T extends Comparable <T>>{

private T [] ob;

private int size=-1; //отвечает на последний который что-то содержит

ListImplement (T [] o){

ob=o;

}

int End(){

return size +1;

}

//позиция после последнего

public void Insert(T obj, int pos){

for (int i=size +1; i>pos; i--){

ob[i]=ob[i-1];

}

ob[pos] = obj;

size++;

}

//вставляет в позицию

public int Locate (T obj){

int a = End();

for (int i = 0; i<=size; i++){

if (ob[i].compareTo(obj)==0){

a=i;

break;

}

}

return a;

}

//возвращает позицию элемета pos

public T Retrive (int pos){

if (pos < 0) {

throw new IndexOutOfBoundsException();

}

return ob[pos];

}

//элемент в позиции рos

void Delete (int pos){

if ( pos < 0 ) {

throw new IndexOutOfBoundsException();

}

for (int i = pos;i<size;i++){

ob[i]=ob[i+1];

}

size--;

} //удаление элемента в позиции рos

public int Next (int pos){

if ( pos < -1) {

throw new IndexOutOfBoundsException();

}

return pos+1;

}

int Previous (int pos){

if ( pos < 1) {

throw new IndexOutOfBoundsException();

}

return pos-1;

}//возвращают позицию предыдующую от рos возвращает элемeнт

void Mekenull (){

size = 0;

} //делает список пустым

int First (){

return 0;

}//возвращается 1 позиция в списке символ

void Printlist (){ //печать элементов в порядке их расположения

for (int i = 0; i<=size; i++){

System.out.print(ob[i]+", ");

}

System.out.println("");

}

}
