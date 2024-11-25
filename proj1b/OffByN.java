public class OffByN implements CharacterComparator {
    private int departure;

    public OffByN(int N) {
        departure = N;
    }

    public boolean equalChars(char x, char y) {
        return (x - y == departure) || (y - x == departure);
    }
}
