package sof03.lfg.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Meeting {

    // attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long meetingId;
    private String title;
    private String location;
    private Date date;
    private String content;

    @ManyToOne
    @JsonIgnoreProperties("meetings")
    @JoinColumn(name = "catId")
    private Category category;

    // constructors
    public Meeting() {
    }

    public Meeting(String title, String location, Date date, String content, Category category) {
        super();
        this.title = title;
        this.location = location;
        this.date = date;
        this.content = content;
        this.category = category;
    }

    // getters and setters
    public Long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
        ;
    }

    public Date getDate() {
        return date;
    }

    public String getDateString() {
        Locale fi = new Locale("fi", "FI");
        return new SimpleDateFormat("dd.MM.yyyy", fi).format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    // toString
    @Override
    public String toString() {
        return "Meeting [meetingId=" + meetingId + ", title=" + title + ", location=" + location + ", date=" + date
                + ", content=" + content + ", category=" + category + "]";
    }

}
