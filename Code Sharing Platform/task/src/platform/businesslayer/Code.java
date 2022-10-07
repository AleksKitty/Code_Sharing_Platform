package platform.businesslayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Table(name = "code")
public class Code {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    @Id
    String id;
    @Column(name = "code")
    String code;
    @Column(name = "date")
    LocalDateTime date;

    @Column(name = "time")
    Long time;

    @Column(name = "views")
    Long views;

    @Column(name = "timeRestrictions")
    boolean timeRestrictions;

    @Column(name = "viewsRestrictions")
    boolean viewsRestrictions;

    public Code() {
    }

    public Code(String id, String code, LocalDateTime date, Long time, Long views) {
        this.id = id;
        this.code = code;
        this.date = date;
        this.time = time;
        this.views = views;
    }

    public Code(String id, String code, LocalDateTime date, Long views) {
        this.id = id;
        this.code = code;
        this.date = date;
        this.views = views;
    }

    @JsonIgnore()
    public String getId() {
        return id;
    }

    @JsonProperty
    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date.format(FORMATTER);
    }

    @JsonIgnore
    public LocalDateTime getDateAsLocalDateTime() {
        return date;
    }

    @JsonProperty
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    @JsonIgnore
    public boolean isTimeRestrictions() {
        return timeRestrictions;
    }

    @JsonProperty
    public void setTimeRestrictions(boolean timeRestrictions) {
        this.timeRestrictions = timeRestrictions;
    }

    @JsonIgnore
    public boolean isViewsRestrictions() {
        return viewsRestrictions;
    }

    @JsonProperty
    public void setViewsRestrictions(boolean viewsRestrictions) {
        this.viewsRestrictions = viewsRestrictions;
    }
}
