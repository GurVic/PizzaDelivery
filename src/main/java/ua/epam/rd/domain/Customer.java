package ua.epam.rd.domain;

import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="customer")
@NamedQuery(name="findCustomerByName", query="SELECT c FROM Customer c WHERE c.name = :name")
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="customer_id")
	private Long id;

	@Column(name="name")
	private String name;

	@Column(name="password")
	private String password;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
	private AccumulativeCard accumulativeCard;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;

	public Customer(){}

    public Customer(String name, AccumulativeCard accumulativeCard, String password) {
        this.name = name;
        this.accumulativeCard = accumulativeCard;
        this.password = password;
    }



    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AccumulativeCard getAccumulativeCard() {
		return accumulativeCard;
	}

	public void setAccumulativeCard(AccumulativeCard accumulativeCard) {
		this.accumulativeCard = accumulativeCard;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (id != customer.id) return false;
        if (name != null ? !name.equals(customer.name) : customer.name != null) return false;
        if (password != null ? !password.equals(customer.password) : customer.password != null) return false;
        return !(accumulativeCard != null ? !accumulativeCard.equals(customer.accumulativeCard) : customer.accumulativeCard != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (accumulativeCard != null ? accumulativeCard.hashCode() : 0);
        return result;
    }

    @Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", accumulativeCard=" + accumulativeCard + "]";
	}
}
