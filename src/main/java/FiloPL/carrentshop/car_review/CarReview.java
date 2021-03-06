package FiloPL.carrentshop.car_review;

import FiloPL.carrentshop.car.Car;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class CarReview {
    @Id
    @GeneratedValue(generator = "carReviewSeq")
    @SequenceGenerator(name = "carReviewSeq", sequenceName = "car_review_seq", allocationSize = 1)
    private int id;

    @ManyToOne(targetEntity = Car.class)
    private Car car;

    private int counterState;
    private LocalDate reviewDate;
    private String notes;

    public CarReview() {
    }

    public CarReview(Car car, int counterState, LocalDate reviewDate, String notes) {
        this.car = car;
        this.counterState = counterState;
        this.reviewDate = reviewDate;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getCounterState() {
        return counterState;
    }

    public void setCounterState(int counterState) {
        this.counterState = counterState;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarReview carReview = (CarReview) o;
        return id == carReview.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
