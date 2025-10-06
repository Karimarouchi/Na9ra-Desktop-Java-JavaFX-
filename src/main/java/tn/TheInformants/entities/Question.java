package tn.TheInformants.entities;

import java.util.Objects;

public class Question {
    int id_quest,quiz_id;
    String quest ,rep1,rep2,rep3,rep4,repc;

    public void setId_quest(int id_quest) {
        this.id_quest = id_quest;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public void setQuest(String quest) {
        this.quest = quest;
    }

    public void setRep1(String rep1) {
        this.rep1 = rep1;
    }

    public Question() {
    }

    public void setRep2(String rep2) {
        this.rep2 = rep2;
    }

    public void setRep3(String rep3) {
        this.rep3 = rep3;
    }

    public void setRep4(String rep4) {
        this.rep4 = rep4;
    }

    public void setRepc(String repc) {
        this.repc = repc;
    }

    public int getId_quest() {
        return id_quest;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public String getQuest() {
        return quest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question question)) return false;
        return quest.equals(question.quest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_quest, quiz_id, quest, rep1, rep2, rep3, rep4, repc);
    }

    public String getRep1() {
        return rep1;
    }

    public String getRep2() {
        return rep2;
    }

    public String getRep3() {
        return rep3;
    }

    public String getRep4() {
        return rep4;
    }

    public String getRepc() {
        return repc;
    }

    public Question(String quest, String rep1, String rep2, String rep3, String rep4, String repc) {


        this.quest = quest;
        this.rep1 = rep1;
        this.rep2 = rep2;
        this.rep3 = rep3;
        this.rep4 = rep4;
        this.repc = repc;
    }
}
/*
package tn.TheInformants.entities;

public class Question {
    int id_quest,quiz_id;
    String quest ,rep1,rep2,rep3,rep4,repc;

    public void setId_quest(int id_quest) {
        this.id_quest = id_quest;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public void setQuest(String quest) {
        this.quest = quest;
    }

    public void setRep1(String rep1) {
        this.rep1 = rep1;
    }

    public Question() {
    }

    public void setRep2(String rep2) {
        this.rep2 = rep2;
    }

    public void setRep3(String rep3) {
        this.rep3 = rep3;
    }

    public void setRep4(String rep4) {
        this.rep4 = rep4;
    }

    public void setRepc(String repc) {
        this.repc = repc;
    }

    public int getId_quest() {
        return id_quest;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public String getQuest() {
        return quest;
    }

    public String getRep1() {
        return rep1;
    }

    public String getRep2() {
        return rep2;
    }

    public String getRep3() {
        return rep3;
    }

    public String getRep4() {
        return rep4;
    }

    public String getRepc() {
        return repc;
    }

    public Question(int id_quest, int quiz_id, String quest, String rep1, String rep2, String rep3, String rep4, String repc) {
        this.id_quest = id_quest;
        this.quiz_id = quiz_id;
        this.quest = quest;
        this.rep1 = rep1;
        this.rep2 = rep2;
        this.rep3 = rep3;
        this.rep4 = rep4;
        this.repc = repc;
    }
}

 */