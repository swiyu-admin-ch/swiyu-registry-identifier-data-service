package ch.admin.bj.swiyu.registry.identifier.data.domain.datastore;

import static org.assertj.core.api.Assertions.*;

import ch.admin.bj.swiyu.registry.identifier.data.common.exception.ResourceNotReadyException;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.test.util.ReflectionTestUtils;

class DatastoreEntityDomainServiceTest {

    private static DatastoreEntity newEntity(DatastoreStatus status) {
        var datastoreEntity = new DatastoreEntity();
        ReflectionTestUtils.setField(datastoreEntity, "id", UUID.randomUUID());
        ReflectionTestUtils.setField(datastoreEntity, "status", status);
        return datastoreEntity;
    }

    /**
     * Ensures that an entry in state {@code ACTIVE} is accepted.
     */
    @Test
    void validateCanShow_allowsActiveEntry() {
        var entry = newEntity(DatastoreStatus.ACTIVE);
        assertThatCode(() -> DatastoreEntityDomainService.validateCanShow(entry)).doesNotThrowAnyException();
    }

    /**
     * Ensures that every non‑ACTIVE status is rejected with
     * {@link ResourceNotReadyException}.
     */
    @ParameterizedTest(name = "status={0} should raise ResourceNotReadyException")
    @EnumSource(value = DatastoreStatus.class, names = { "DISABLED", "SETUP", "DEACTIVATED" })
    void validateCanShow_rejectsNonActiveStatuses(DatastoreStatus status) {
        var entry = newEntity(status);

        assertThatThrownBy(() -> DatastoreEntityDomainService.validateCanShow(entry)).isInstanceOf(
            ResourceNotReadyException.class
        );
    }
}
