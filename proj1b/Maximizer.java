public class Maximizer {
    // public static Object max(Object[] items) {
    //     int maxDex = 0;
    //     for (int i = 0; i < items.length; i += 1) {
    //         if (items[i] > items[maxDex]) {
    //             maxDex = i;
    //         }
    //     }
    //     return items[maxDex];
    // }
    
    public static Comparable max(Comparable[] items) {
        int maxDex = 0;
        for (int i = 0; i < items.length; i += 1) {
            int cmp = items[i].compareTo(items[maxDex]);
            if (cmp > 0) {
                maxDex = i;
            }
        }
        return items[maxDex];
    }

    public static void main(String[] args) {
        Dog[] dogs = {new Dog("Elyse", 3), new Dog("Sture", 9), new Dog("Benjamin", 15)};
        Dog maxDog = (Dog) max(dogs);
        maxDog.bark();
    }

    // public static Dog maxDog(Dog[] dogs) {
    //     if (dogs == null || dogs.length == 0) {
    //         return null;
    //     }
    //     Dog maxDog = dogs[0];
    //     for (Dog d : dogs) {
    //         if (d.size > maxDog.size) {
    //             maxDog = d;
    //         }
    //     }
    //     return maxDog;

    // }
    
}
