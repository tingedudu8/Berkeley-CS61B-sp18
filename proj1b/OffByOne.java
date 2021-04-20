public class OffByOne implements CharacterComparator {
    public boolean equalChars(char x, char y) {
        return ((x - y) == 1 || (x - y) == -1);
    //     int diff = x - y;
    //     if (Math.abs(diff) == 1) {
    //         return true;
    //     }
    //     return false;
    }
}



