package com.example.deploy.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@Table(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "tags")
    private String tags;

    @Column(name = "theme")
    private String theme;

    @Column(name = "body")
    private String body;

    @Column(name = "posted", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date posted;

    @OneToMany
    @JoinColumn(name = "post_id")
    private Collection<Comment> comments;

    @OneToMany
    @JoinColumn(name = "post_id")
    private Collection<Image> images;

    @OneToMany
    @JoinColumn(name = "post_id")
    private Collection<Rate> rateCollection;

    @Column(name = "rate")
    private Double rate;

    public Double getRate() {
        Double rate = 0D;
        if (rateCollection.isEmpty()){
            return 0.0;
        }
        for (Rate rateFromCollection: rateCollection) {
            rate += rateFromCollection.getRate();
        }
        rate /= rateCollection.size();
        return roundAvoid(rate,1);
    }

    private static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }
}
