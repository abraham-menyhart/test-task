package kata.time;

record Component(long qty, String label) {
    @Override public String toString() { return qty + " " + label; }
}
