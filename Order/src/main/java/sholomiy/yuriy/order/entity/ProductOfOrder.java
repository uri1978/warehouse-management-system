package sholomiy.yuriy.order.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProductOfOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne(cascade = CascadeType.ALL)
	private Product product;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Order order;

	private int quantity;
	private float price;
	private float sum;
	
	public ProductOfOrder(Order order, Product product, int quantity, float price) {

		this.product = product;
		this.order = order;
		this.quantity = quantity;
		this.price = price;
		this.sum = price * quantity;

	}

}
