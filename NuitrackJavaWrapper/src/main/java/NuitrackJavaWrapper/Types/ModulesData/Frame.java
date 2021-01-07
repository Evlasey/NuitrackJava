package NuitrackJavaWrapper.Types.ModulesData;

public abstract class Frame <DataType> extends ObjectData {

    /**
     * @brief Returns the number of rows in the RGB frame.
     */
    public abstract long getRows();

    /**
     * @brief Returns the number of columns in the frame.
     */
    public abstract long getCols();

    /**
     * @brief Returns the frame ID.
     */
    public abstract long getID();

    /**
     * @brief Returns the frame data.
     */
    public abstract DataType[] getData();
}
