package com.backend;

public class Instruction {

    private String title;
    private String information;

    //Pointers to the previous and next node
    private Instruction previous = null;
    private Instruction next = null;

    /**
     * Creates a new Instruction when there is a title and information
     * @param t
     * @param i
     */
    public Instruction(String t, String i){
        title = t;
        information = i;
    }

    /**
     * Creates a new Instruction when there is only a title
     * @param t
     */
    public Instruction(String t){
        title = t;
        information = "";
    }

    public boolean hasPrevious(){
        return !(previous == null);
    }

    public boolean hasNext(){
        if(next == null)
            return false;
        return true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Instruction getPrevious() {
        return previous;
    }

    public void setPrevious(Instruction previous) {
        this.previous = previous;
    }

    public Instruction getNext() {
        return next;
    }

    public void setNext(Instruction next) {
        this.next = next;
    }

}
