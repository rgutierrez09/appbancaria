package com.finzapp.gestion_clientes.Entity;

import com.finzapp.gestion_clientes.Enum.TypeDocument;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Table(name = "cliente")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name ="Tipo_identificacion")
    private TypeDocument typeIdentification;

    @NotNull(message = "El numero de identificacion debe ser obligatorio")
    @Column(name ="numero de identificacion")
    private String numberIdentification;

    @NotNull(message = "El nombre es obligatorio")
    @Length(min =2, message = "El nombre no puede tener menos de 2 caracteres")
    @Column(name = "nombre_cliente")
    private String name;

    @NotNull(message = "El nombre es obligatorio")
    @Length(min =2, message = "El nombre no puede tener menos de 2 caracteres")
    @Column(name = "nombre_cliente")
    private String lastName;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,}$", message = "El formato no es valido")
    @Column(name ="email")
    private String email;

    @Column(name ="fecha_nacimiento")
    private String DateOfBirth;

    @OneToMany(mappedBy = "clienteEntinty",cascade = CascadeType.ALL)
    private List<ProductEntity> productEntity;

}
