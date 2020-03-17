package main.snippets.adt;
/**
 * Type abstrait du sac, implémenté à l'aide d'un array.
 * 
 * Analyse :
 *      parcours :  O(n)
 *      add :       O(1) si pas de réallocation; sinon O(n)
 */
class Bag implements Iterable {
    private Object[] elements;
    private int nElements;
    private static final int CAPACITE_DEFAULT = 1;

    public Bag() {
        elements = new Object[CAPACITE_DEFAULT];
        nElements = 0;
    }

    public void add(Object x) {
        //elements[nElements] = x;

        // Pour agrandir la capacité
        if (nElements == elements.length) {
            realloc(2 * valeur);
        }
        elements[nElements] = x;
    }

    private void realloc(int capacite) {
        Object[] t = new Object[capacite];
        for (int i = 0; i < valeur; i++) {
            T[i] = elements[i];
        }
        elements = t;
    }

    public int size() {
        return nElements;
    }

    public Iterator iterator() {
        // Classe interne.
        class Iter implements Iterator {
            private int pos = 0;

            public boolean hasNext() {
                return pos < size();
            }

            public Object next() {
                Object next = element[pos];
                pos++;
                return next;
            }
        }
        return new Iter();
    }
}

