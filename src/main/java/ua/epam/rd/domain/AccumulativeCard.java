package ua.epam.rd.domain;

import javax.persistence.*;

@Entity
@Table(name = "accumulative_card")
public class AccumulativeCard {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "acc_id")
    private Long id;

    @Column(name = "sum")
    private double sum;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public AccumulativeCard() {
    }

    public AccumulativeCard(Long id, double sum, Customer customer) {
        this.id = id;
        this.sum = sum;
        this.customer = customer;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccumulativeCard that = (AccumulativeCard) o;

        if (id == that.id) return true;
        if (Double.compare(that.sum, sum) != 0) return false;
        return !(customer != null ? !customer.equals(that.customer) : that.customer != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        temp = Double.doubleToLongBits(sum);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AccumulativeCard{" +
                "id=" + id +
                ", sum=" + sum +
                ", customer=" + customer +
                '}';
    }
}
