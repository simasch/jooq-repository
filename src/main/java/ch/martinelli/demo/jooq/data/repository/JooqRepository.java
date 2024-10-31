package ch.martinelli.demo.jooq.data.repository;

import org.jooq.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public abstract class JooqRepository<T extends Table<R>, R extends UpdatableRecord<R>, ID> {

    protected final DSLContext dslContext;
    protected final T table;

    public JooqRepository(DSLContext dslContext, T table) {
        this.dslContext = dslContext;
        this.table = table;
    }

    public Optional<R> findById(ID id) {
        TableField<R, ID> primaryKeyField = getPrimaryKeyField();
        return dslContext
                .selectFrom(table)
                .where(primaryKeyField.eq(id))
                .fetchOptional();
    }

    public Result<R> findAll(int offset, int limit, List<OrderField<?>> orderBy) {
        return dslContext
                .selectFrom(table)
                .orderBy(orderBy)
                .offset(offset)
                .limit(limit)
                .fetch();
    }

    public int count() {
        return dslContext.fetchCount(table);
    }

    @Transactional
    public int save(R record) {
        dslContext.attach(record);
        return record.store();
    }

    @Transactional
    public int merge(R record) {
        dslContext.attach(record);
        return record.merge();
    }

    @Transactional
    public int delete(R record) {
        dslContext.attach(record);
        return record.delete();
    }

    @Transactional
    public int deleteById(ID id) {
        TableField<R, ID> primaryKeyField = getPrimaryKeyField();
        return dslContext.deleteFrom(table).where(primaryKeyField.eq(id)).execute();
    }

    private TableField<R, ID> getPrimaryKeyField() {
        if (table.getPrimaryKey() == null) {
            throw new IllegalArgumentException(
                    "Cannot be used because the table %s has no primary key"
                            .formatted(table.getName()));
        }
        List<TableField<R, ?>> fields = table.getPrimaryKey().getFields();
        if (fields.size() != 1) {
            throw new UnsupportedOperationException(
                    "Can currently only be used with exactly one primary key");
        }

        TableField<R, ID> primaryKeyField = (TableField<R, ID>) table.getPrimaryKey().getFields().getFirst();
        return primaryKeyField;
    }

}
