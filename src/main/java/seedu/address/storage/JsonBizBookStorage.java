package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyBizBook;

/**
 * A class to access BizBook data stored as a json file on the hard disk.
 */
public class JsonBizBookStorage implements BizBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonBizBookStorage.class);

    private Path filePath;

    public JsonBizBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getBizBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyBizBook> readBizBook() throws DataLoadingException {
        return readBizBook(filePath);
    }

    /**
     * Similar to {@link #readBizBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyBizBook> readBizBook(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableBizBook> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableBizBook.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveBizBook(ReadOnlyBizBook BizBook) throws IOException {
        saveBizBook(BizBook, filePath);
    }

    /**
     * Similar to {@link #saveBizBook(ReadOnlyBizBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveBizBook(ReadOnlyBizBook BizBook, Path filePath) throws IOException {
        requireNonNull(BizBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableBizBook(BizBook), filePath);
    }

}
