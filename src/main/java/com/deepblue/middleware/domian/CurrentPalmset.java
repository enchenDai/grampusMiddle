package com.deepblue.middleware.domian;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by enchen on 10/12/17.
 */
@Entity
@Table(name = "current_palmset")
@Setter
@Getter
public class CurrentPalmset implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "palmset_name")
    private String palmsetName;

    @Column(name = "count")
    private Integer count;

    @Column(name = "enable")
    private Integer enable;

    @Column(name = "create_by")
    @JsonIgnore
    private DateTime createBy;

    @Column(name = "create_date")
    @JsonIgnore
    private Date createDate = new Date();

    @Column(name = "update_by")
    @JsonIgnore
    private DateTime updateBy;

    @Column(name = "update_date")
    @JsonIgnore
    private Date updateDate = new Date();
}
