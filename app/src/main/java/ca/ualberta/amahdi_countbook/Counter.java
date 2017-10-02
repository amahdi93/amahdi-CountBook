package ca.ualberta.amahdi_countbook;

import java.util.Date;


public class Counter{
    private String counterName;
    private Date date;
    private String currentCounter;
    private String initialCounter;
    private String counterComment;

    /**
     * Constructs a Counter object
     * @param counterName Counter name
     */
    public Counter(String counterName, String initialCounter, String comment) {
        date = new Date();
        this.counterName = counterName;
        this.currentCounter = initialCounter;
        this.initialCounter = initialCounter;
        if(comment.matches("")){this.counterComment = null;}
        else{ this.counterComment = comment;}

    }


    /**
     * Returns the Counter Name.
     * @return
     */
    public String getCounterName(){
        return counterName;
    }

    /**
     * Sets the Counter Name.
     *
     */
    public void setCounterName(String counterName){
            this.counterName = counterName;
    }
    /**
     * Returns string representation of Counter
     * @return String with Counter Name, initial value, current value, date, and comment
     */
    public String toString(){
        return "Counter name: " + counterName + "\nInitial Value: " + initialCounter
                + "\nCurrent Value: " + currentCounter +
                '\n' + "Last edited: " +date.toString()+
                '\n' + "Comments: " +counterComment;
    }
    /**
     * Returns date of Counter
     * @return Counter date
     */
    public Date getDate() {
        return null;
    }
    /**
     * Returns the Counter current value.
     * @return
     */
    public String getCurrentCounter(){return currentCounter;}

    /**
     * Returns the Counter initial value.
     * @return
     */
    public String getInitialCounter(){return initialCounter;}

    /**
     * Returns the Counter Comment.
     * @return
     */
    public String getCounterComment(){
        return counterComment;
    }

    /**
     * Sets the Counter Comment.
     *
     */
    public void setCounterComment(String counterComment){
        this.counterComment= counterComment;
    }

}
