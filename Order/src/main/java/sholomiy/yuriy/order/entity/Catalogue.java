package sholomiy.yuriy.order.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Catalogue {

	private long id;
	private Product product;
	private float price;

}
