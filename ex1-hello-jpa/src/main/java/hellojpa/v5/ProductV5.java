package hellojpa.v5;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ProductV5 {
    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "product")
    private List<MemberV5> members = new ArrayList<>();

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
}
