public class Main {

    public static void main(String[] args) {
        System.out.println(" Lab 3");
	// write your code here
        // 1
        // Варінат 10, 8, 8
        System.out.println("=== Task 1 ===");
        Document[] documents = new Document[5];

        documents[0] = new Receipt("Receipt #1", "2025-10-29", 1500.00);
        documents[1] = new Invoice("Invoice #2", "2025-10-28", "LLC 'Supplier'");
        documents[2] = new Account("Account #3", "2025-10-27", true);
        documents[3] = new Receipt("Receipt #4", "2025-10-26", 850.50);
        documents[4] = new Document("Receipt #4", "2025-10-26");

        for (Document doc : documents) {
            doc.Show();
        }

        // 2
        System.out.println("===Task 2 ===");
        Pair[] pairs = new Pair[4];
        pairs[0] = new Money(10, 75);         // 10 грн 75 коп
        pairs[1] = new Money(5, 50);          // 5 грн 50 коп
        pairs[2] = new Fraction(3, 4);        // 3/4
        pairs[3] = new Fraction(1, 2);        // 1/2

        System.out.println("Addition:");
        System.out.println(pairs[0].add(pairs[1])); // Money
        System.out.println(pairs[2].add(pairs[3])); // Fraction

        System.out.println("\nSubtraction:");
        System.out.println(pairs[0].subtract(pairs[1]));
        System.out.println(pairs[2].subtract(pairs[3]));

        System.out.println("\nMultiplication by 2:");
        System.out.println(pairs[0].multiply(2));
        System.out.println(pairs[2].multiply(2));

        System.out.println("\nDivision by 2:");
        System.out.println(pairs[0].divide(2));
        System.out.println(pairs[2].divide(2));

        // 3
        System.out.println("=== Task 3 ===");
        Pair[] pairs2 = new Pair[4];
        pairs2[0] = new Money(10, 75);
        pairs2[1] = new Money(5, 50);
        pairs2[2] = new Fraction(3, 4);
        pairs2[3] = new Fraction(1, 2);

        System.out.println("Addition:");
        System.out.println(pairs2[0].add(pairs[1])); // Money
        System.out.println(pairs2[2].add(pairs[3])); // Fraction

        System.out.println("\nSubtraction:");
        System.out.println(pairs2[0].subtract(pairs[1]));
        System.out.println(pairs2[2].subtract(pairs[3]));

        System.out.println("\nMultiplication by 2:");
        System.out.println(pairs2[0].multiply(2));
        System.out.println(pairs2[2].multiply(2));

        System.out.println("\nDivision by 2:");
        System.out.println(pairs2[0].divide(2));
        System.out.println(pairs2[2].divide(2));
    }
}

// 1 ===========================================================================================================================================
class Document {
    protected String title;
    protected String date;

    public Document(String title, String date) {
        this.title = title;
        this.date = date;
    }

    public void Show() {
        System.out.println("Document: " + title + ", Date: " + date);
    }
}

class Receipt extends Document {
    final private double amount;

    public Receipt(String title, String date, double amount) {
        super(title, date);
        this.amount = amount;
    }

    @Override
    public void Show() {
        System.out.println("Receipt: " + title + ", Date: " + date + ", Amount: " + amount + " UAH");
    }
}

class Invoice extends Document {
    final private String supplier;

    public Invoice(String title, String date, String supplier) {
        super(title, date);
        this.supplier = supplier;
    }

    @Override
    public void Show() {
        System.out.println("Invoice: " + title + ", Date: " + date + ", Supplier: " + supplier);
    }
}

class Account extends Document {
    final private boolean paid;

    public Account(String title, String date, boolean paid) {
        super(title, date);
        this.paid = paid;
    }

    @Override
    public void Show() {
        System.out.println("Account: " + title + ", Date: " + date + ", Paid: " + (paid ? "Yes" : "No"));
    }
}

// 2 ============================================================================================================================================

abstract class Pair {
    protected int a;
    protected int b;

    public Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public abstract Pair add(Pair other);
    public abstract Pair subtract(Pair other);
    public abstract Pair multiply(int factor);
    public abstract Pair divide(int divisor);

    @Override
    public abstract String toString();
}

class Money extends Pair {

    public Money(int hryvnias, int kopecks) {
        super(hryvnias, kopecks);
        normalize();
    }

    private void normalize() {
        a += b / 100;
        b = b % 100;
    }

    @Override
    public Pair add(Pair other) {
        return new Money(this.a + other.a, this.b + other.b);
    }

    @Override
    public Pair subtract(Pair other) {
        int total1 = this.a * 100 + this.b;
        int total2 = other.a * 100 + other.b;
        int result = total1 - total2;
        return new Money(result / 100, result % 100);
    }

    @Override
    public Pair multiply(int factor) {
        int total = (a * 100 + b) * factor;
        return new Money(total / 100, total % 100);
    }

    @Override
    public Pair divide(int divisor) {
        int total = a * 100 + b;
        int result = total / divisor;
        return new Money(result / 100, result % 100);
    }

    @Override
    public String toString() {
        return a + " UAH " + b + " kopecks";
    }
}

class Fraction extends Pair {

    public Fraction(int numerator, int denominator) {
        super(numerator, denominator);
    }

    @Override
    public Pair add(Pair other) {
        int num = this.a * other.b + other.a * this.b;
        int den = this.b * other.b;
        return new Fraction(num, den);
    }

    @Override
    public Pair subtract(Pair other) {
        int num = this.a * other.b - other.a * this.b;
        int den = this.b * other.b;
        return new Fraction(num, den);
    }

    @Override
    public Pair multiply(int factor) {
        return new Fraction(this.a * factor, this.b);
    }

    @Override
    public Pair divide(int divisor) {
        return new Fraction(this.a, this.b * divisor);
    }

    @Override
    public String toString() {
        return a + "/" + b;
    }
}

// 3 =============================================================================================================================================

interface Pairr {
    Pairr add(Pairr other);
    Pairr subtract(Pairr other);
    Pairr multiply(int factor);
    Pairr divide(int divisor);
    String toStringg();
}

class Moneyy implements Pairr {
    private int hryvnias;
    private int kopecks;

    public Moneyy(int hryvnias, int kopecks) {
        this.hryvnias = hryvnias;
        this.kopecks = kopecks;
        normalize();
    }

    private void normalize() {
        hryvnias += kopecks / 100;
        kopecks = kopecks % 100;
    }

    @Override
    public Pairr add(Pairr other) {
        if (other instanceof Moneyy m) {
            return new Moneyy(this.hryvnias + m.hryvnias, this.kopecks + m.kopecks);
        }
        throw new IllegalArgumentException("Cannot add different types");
    }

    @Override
    public Pairr subtract(Pairr other) {
        if (other instanceof Moneyy m) {
            int total1 = this.hryvnias * 100 + this.kopecks;
            int total2 = m.hryvnias * 100 + m.kopecks;
            int result = total1 - total2;
            return new Moneyy(result / 100, result % 100);
        }
        throw new IllegalArgumentException("Cannot subtract different types");
    }

    @Override
    public Pairr multiply(int factor) {
        int total = (hryvnias * 100 + kopecks) * factor;
        return new Moneyy(total / 100, total % 100);
    }

    @Override
    public Pairr divide(int divisor) {
        int total = hryvnias * 100 + kopecks;
        int result = total / divisor;
        return new Moneyy(result / 100, result % 100);
    }

    @Override
    public String toStringg() {
        return hryvnias + " UAH " + kopecks + " kopecks";
    }
}

class Fractionn implements Pairr {
    final private int numerator;
    final private int denominator;

    public Fractionn(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public Pairr add(Pairr other) {
        if (other instanceof Fractionn f) {
            int num = this.numerator * f.denominator + f.numerator * this.denominator;
            int den = this.denominator * f.denominator;
            return new Fractionn(num, den);
        }
        throw new IllegalArgumentException("Cannot add different types");
    }

    @Override
    public Pairr subtract(Pairr other) {
        if (other instanceof Fractionn f) {
            int num = this.numerator * f.denominator - f.numerator * this.denominator;
            int den = this.denominator * f.denominator;
            return new Fractionn(num, den);
        }
        throw new IllegalArgumentException("Cannot subtract different types");
    }

    @Override
    public Pairr multiply(int factor) {
        return new Fractionn(this.numerator * factor, this.denominator);
    }

    @Override
    public Pairr divide(int divisor) {
        return new Fractionn(this.numerator, this.denominator * divisor);
    }

    @Override
    public String toStringg() {
        return numerator + "/" + denominator;
    }
}
