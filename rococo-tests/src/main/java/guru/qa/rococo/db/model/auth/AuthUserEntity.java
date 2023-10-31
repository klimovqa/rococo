package guru.qa.rococo.db.model.auth;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.FetchType.EAGER;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class AuthUserEntity implements Cloneable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean enabled;

    @Column(name = "account_non_expired", nullable = false)
    private Boolean accountNonExpired;

    @Column(name = "account_non_locked", nullable = false)
    private Boolean accountNonLocked;

    @Column(name = "credentials_non_expired", nullable = false)
    private Boolean credentialsNonExpired;

    @OneToMany(fetch = EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    private List<AuthorityEntity> authorities = new ArrayList<>();
}
