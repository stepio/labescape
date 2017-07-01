package co.tide.labescape;

/**
 * @author istepanov
 */
public enum LabChars {

    WALL('O'), FREE(' '), PATH ('•');

    private char value;

    LabChars(final char value) {
        this.value = value;
    }

    public char getValue() {
        return this.value;
    }
}
