package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyBizBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of BizBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private BizBookStorage bizBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code BizBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(BizBookStorage bizBookStorage, UserPrefsStorage userPrefsStorage) {
        this.bizBookStorage = bizBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ BizBook methods ==============================

    @Override
    public Path getBizBookFilePath() {
        return bizBookStorage.getBizBookFilePath();
    }

    @Override
    public Optional<ReadOnlyBizBook> readBizBook() throws DataLoadingException {
        return readBizBook(bizBookStorage.getBizBookFilePath());
    }

    @Override
    public Optional<ReadOnlyBizBook> readBizBook(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return bizBookStorage.readBizBook(filePath);
    }

    @Override
    public void saveBizBook(ReadOnlyBizBook BizBook) throws IOException {
        saveBizBook(BizBook, bizBookStorage.getBizBookFilePath());
    }

    @Override
    public void saveBizBook(ReadOnlyBizBook BizBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        bizBookStorage.saveBizBook(BizBook, filePath);
    }

}
