/**
 * A set that stores words consisting of English lowercase and capital letters.
 * Words are stored in sorted form, each element is unique in the set.
 */
public class WordSet {

    /**
     * WordSet properties.
     */
    protected Node head;

    /**
     * Node of the WordSet.
     */
    private class Node {

        private String data;
        private Node next;

        public Node(){
            this.data = null;
            this.next = null;
        }

        public Node(String data, Node next){
            this.data = data;
            this.next = next;
        }

        public Node(String data){
            this.data = data;
            this.next = null;
        }

        public String getData(){
            return data;
        }

        public Node getNext(){
            return next;
        }

        public void setNext(Node next){
            this.next = next;
        }

        public void setData(String data){
            this.data = data;
        }
    }

    /**
     * Void constructor.
     * T = O(1).
     * M = O(1).
     */
    public WordSet(){
        this.head = null;
    }

    /**
     * A constructor that creates an instance of the WordSet class based on an array of words.
     * @param arr - The array from which to create WordSet.
     * T = O(n^m) (n - the amount of the words in the WordSet,m - the amount of the words in the input array).
     * M = O(n) (n - the amount of the words in the WordSet).
     */
    public WordSet(String[] arr) {
        this.head = null;
        for (int i = 0; i < arr.length; i++) {
            this.insert(arr[i]);
        }
    }

    /**
     * constructor #2 which merges two WordSets.
     * @param w1 - One of two combined sets
     * @param w2 - One of two combined sets.
     * T = O(n+m) (n and m - number of elements in the combined sets).
     * M = O(n + m) (n and m - number of elements in the combined sets).
     */
    public WordSet(WordSet w1,WordSet w2){
        Node e1 = w1.head;
        Node e2 = w2.head;
        Node e3;
        if ((e1==null)&&(e2==null)){
            this.head=null;
            return;
        } else if (e1==null){
            this.head = new Node(e2.getData());
            e2 = e2.getNext();
        } else if (e2==null){
            this.head = new Node (e1.getData());
            e1 = e1.getNext();
        } else if (e1.getData().compareTo(e2.getData())<0){
            this.head = new Node (e1.getData());
            e1 = e1.getNext();
        } else {
            this.head = new Node(e2.getData());
            e2 = e2.getNext();
        }
        e3 = this.head;
        Node e4;
        while ((e1!=null)||(e2!=null)){
            if ((e1!=null)&&(e2!=null)&&(e1.getData().compareTo(e2.getData())<0)){
                e4 = new Node(e1.getData());
                e3.setNext(e4);;
                e3 = e4;
                e1 = e1.getNext();
            } else if ((e1!=null)&&(e2!=null)){
                e4 = new Node(e2.getData());
                e3.setNext(e4);;
                e3 = e4;
                e2 = e2.getNext();
            } else if (e1!=null){
                e4 = new Node(e1.getData());
                e3.setNext(e4);;
                e3 = e4;
                e1 = e1.getNext();
            } else {
                e4 = new Node(e2.getData());
                e3.setNext(e4);;
                e3 = e4;
                e2 = e2.getNext();
            }
        }
    }

    /**
     * WordSet string representation
     * @return wordSet string representation
     * T = O(n) (n - the amount of the words in the WordSet).
     * M = O(n*k + t) (n - the amount of the words in the WordSet, k - average number of characters in a word
     * of WordSet, t - the amount of memory reserved by StringBuilder).
     */
    public String toString(){
        StringBuilder str = new StringBuilder();
        if (this.head == null) {
            return str.toString();
        }
        Node item = this.head;
        str.append(item.getData());
        while (item.getNext()!=null){
            str.append("->");
            item = item.getNext();
            str.append(item.getData());
        }
        return str.toString();
    }

    /**
     * deletes word from the WordSet.
     * @param word - The word to be removed from the WordSet.
     * T = O(n) (n - the amount of the words in the WordSet).
     * M = O(1)
     */
    public void delete(String word){
        if (word.equals(this.head.getData())){
            head = this.head.getNext();
            return;
        }
        Node del = this.head.getNext();
        Node l = this.head;
        while (del.getNext()!=null){
            if (del.getData().equals(word)){
                break;
            }
            l = del;
            del = del.getNext();
        }
        if (del.getData().equals(word)){
            l.setNext(del.getNext());
        }
    }


    /**
     * Deletes all palindromes from the WordSet.
     * T = O(n*t) (n - the amount of the words in the WordSet, t - average words length).
     * M = O(1).
     */
    public void removePalindrom(){
        while ((this.head!=null)&&(this.checkPolindrom(this.head.getData()))){
            this.head = head.getNext();
        }
        Node del= null;
        Node l = null;
        if (this.head!=null){
            del = this.head.getNext();
            l = this.head;
        }
        while ((del!=null)&&(del.getNext()!=null)){
            if (this.checkPolindrom(del.getData())){
                l.setNext(del.getNext());
                del = del.getNext();
            } else {
                l = del;
                del = del.getNext();
            }
        }
        if ((del!=null)&&(this.checkPolindrom(del.getData()))) {
            l.setNext(null);
        }
    }

    /**
     * Check string to the palindrome.
     * @param str - check string.
     * @return result of checking.
     * T = O(l) (l - string length).
     * M = O(1).
     */
    private boolean checkPolindrom(String str){
        for (int i = 0;i<(int) str.length()/2;i++){
            if (str.charAt(i)!=str.charAt(str.length()-1-i)){
                return false;
            }
        }
        return true;
    }

    /**
     * Inserts new word in the WordSet.
     * @param word - word to add.
     * T = O(n) (n - the amount of the words in the WordSet).
     * M = O(1).
     */
    public void insert(String word) {
        if (!contains(word)) {
            if (this.head == null) {
                this.head = new Node(word, null);
            } else {
                if (this.head.data.compareTo(word) > 0) { // if (compare(this.head.data, word) > 0)
                    Node buffer = this.head;
                    this.head = new Node(word, buffer);
                    return;
                } else {
                    Node nextNode = this.head;
                    while (nextNode.next != null) {
                        if (word.compareTo(nextNode.next.data) < 0) { // if (compare(word, nextNode.next.data))
                            Node newNode = new Node(word, nextNode.next);
                            nextNode.next = newNode;
                            return;
                        } else nextNode = nextNode.next;
                    }
                    Node newNode = new Node(word, null);
                    nextNode.next = newNode;
                }
            }
        }
    }

    /**
     * Checks if the word is in the currentю
     * @param word - the word to search in WordSet.
     * @return - result of checking.
     * T = O(n) (n - the amount of the words in the WordSet).
     * M = O(1).
     */
    public boolean contains(String word) {
        if (this.head == null) {
            return false;
        } else {
            if (word.equals(this.head.data)) {
                return true;
            } else {
                Node nextNode = this.head;
                while (nextNode != null) {
                    if (word.equals(nextNode.getData())) {
                        return true;
                    } else {
                        nextNode = nextNode.getNext();
                    }
                }
                return false;
            }
        }
    }

    /**
     * Returns new WordSet with words with length - len.
     * @param len - word length in new WordSet.
     * @return - new WordSet.
     * T = O(n) (n - the amount of the words in the WordSet).
     * M = O(n) (n - the amount of the words in the WordSet).
     */
    public WordSet newWordSetByWordLength(int len) {
        WordSet result = new WordSet();
        if (this.head == null) {
            // try to fix NullPointerExsception
            return null;
        } else {
            Node nextNode = this.head;
            while (nextNode != null) {
                if (nextNode.data.length() == len) {
                    result.insert(nextNode.data);
                }
                nextNode = nextNode.next;
            }
            return result;
        }
    }

    /**
     * sort words to by first char to consonants or vowels
     * @return - 2 sorted arrays.
     * T = O(n) (n - the amount of the words in the WordSet).
     * M = O(n) (n - the amount of the words in the WordSet).
     */
    public WordSet [] vowelDivide() {
        WordSet[] result = new WordSet[2];
        result[0] = new WordSet();
        result[1] = new WordSet();
        if (this.head == null) {
            // try to fix NullPointerExsception
            return null;
        } else {
            Character[] vowels = new Character[] {'A', 'E', 'I', 'O', 'U', 'Y', 'a', 'e', 'i', 'o', 'u', 'y'};
            Node nextNode = this.head;
            boolean flag;
            while (nextNode != null) {
                flag = true;
                for (int i = 0; i < vowels.length; i++) {
                    if (nextNode.data.charAt(0) == vowels[i]) {
                        // add to vowels-words
                        result[0].insert(nextNode.data);
                        flag = false;
                    } else continue;
                }
                // add to consonants-words
                if (flag) result[1].insert(nextNode.data);
                nextNode = nextNode.next;
            }
            return result;
        }
    }
}
