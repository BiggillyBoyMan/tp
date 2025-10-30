package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.BizBook;
import seedu.address.model.ReadOnlyBizBook;

/**
 * Represents a storage for {@link BizBook}.
 */
public interface BizBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getBizBookFilePath();

    /**
     * Returns BizBook data as a {@link ReadOnlyBizBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyBizBook> readBizBook() throws DataLoadingException;

    /**
     * @see #getBizBookFilePath()
     */
    Optional<ReadOnlyBizBook> readBizBook(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyBizBook} to the storage.
     * @param BizBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveBizBook(ReadOnlyBizBook BizBook) throws IOException;

    /**
     * @see #saveBizBook(ReadOnlyBizBook)
     */
    void saveBizBook(ReadOnlyBizBook BizBook, Path filePath) throws IOException;

}
