package ru.job4j.sqllite;

import java.util.List;

/**
 * Интерфейс для StoreSQL.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public interface IStoreSQL {
    /**
     * Вставляет в таблицу entry n записей со значениями 1..N. Если в таблице account
     * находились записи, то они удаляются перед вставкой.
     * @param size - число записей.
     */
    void generate(int size);

    /**
     * Загружает все записи из таблицы entry и сохраняет их в List<Entry>.
     * @return - List<Entry>.
     */
    List<Entry> load();
}
