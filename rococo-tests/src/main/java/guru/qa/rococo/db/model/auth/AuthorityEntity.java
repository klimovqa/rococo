package guru.qa.rococo.db.model.auth;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString(exclude = "user")
@EqualsAndHashCode
@Entity
@Table(name = "authorities")
public class AuthorityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    private UUID id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AuthUserEntity user;
}
