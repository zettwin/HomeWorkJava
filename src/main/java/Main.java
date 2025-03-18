
public class Main {
    public static void main(String[] args) {
        MyLinkedList<String> lList = new MyLinkedList<>();
        lList.add("Work it");
        lList.add("Make it");
        lList.add("Do it");
        lList.add("Makes us");
        lList.add("Harder");
        lList.add("Better");
        lList.add("Faster");
        lList.add("Stronger");

        System.out.println(lList); // [Work it, Make it, Do it, Makes us, Harder, Better, Faster, Stronger]
        lList.sort(new MyComparator());
        System.out.println(lList); // [Better, Do it, Faster, Harder, Make it, Makes us, Stronger, Work it]

        MyArrayList<String> aList = new MyArrayList<>();
        aList.add("Work it");
        aList.add("Make it");
        aList.add("Do it");
        aList.add("Makes us");
        aList.add("Harder");
        aList.add("Better");
        aList.add("Faster");
        aList.add("Stronger");

        System.out.println(aList); // [Work it, Make it, Do it, Makes us, Harder, Better, Faster, Stronger]
        aList.sort(new MyComparator());
        System.out.println(aList); // [Better, Do it, Faster, Harder, Make it, Makes us, Stronger, Work it]
    }
}