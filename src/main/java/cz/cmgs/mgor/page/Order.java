package cz.cmgs.mgor.page;

import lombok.*;
import org.springframework.data.domain.Sort;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Order {
    private Integer column;
    private Sort.Direction dir;

}
