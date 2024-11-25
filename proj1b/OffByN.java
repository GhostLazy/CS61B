public class OffByN implements CharacterComparator {
    public int departure;

    public OffByN(int N) {
        departure = N;
    }

    public boolean equalChars(char x, char y) {
        return (x - y == departure) || (y - x == departure);
    }
}
