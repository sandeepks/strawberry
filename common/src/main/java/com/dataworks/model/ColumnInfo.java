package com.dataworks.model;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by Sandeep on 4/19/17.
 */
public class ColumnInfo {
    private String columnName;
    private String sqlDataType;
    private int decimalDigits;
    private int ordinalIdx;
    private boolean isPrimary;
    private String isNull;
    private int columnSize;
    private int characterOctetLength;

    public ColumnInfo(String columnName, String sqlDataType, int decimalDigits, int ordinalIdx, boolean isPrimary, String isNull, int columnSize, int characterOctetLength) {
        this.columnName = columnName;
        this.sqlDataType = sqlDataType;
        this.decimalDigits = decimalDigits;
        this.ordinalIdx = ordinalIdx;
        this.isPrimary = isPrimary;
        this.isNull = isNull;
        this.columnSize = columnSize;
        this.characterOctetLength = characterOctetLength;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getSqlDataType() {
        return sqlDataType;
    }

    //the number of fractional digits
    public int getDecimalDigits() {
        return decimalDigits;
    }

    public int getOrdinalIdx() {
        return ordinalIdx;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public String isNull() {
        return isNull;
    }

    //For char or date types this is the maximum number of characters, for numeric or decimal types this is precision.
    public int getColumnSize() {
        return columnSize;
    }

    //for char types the maximum number of bytes in the column
    public int getCharacterOctetLength() {
        return characterOctetLength;
    }

    public List<String> marshall() {
        List<String> returnValue = Lists.<String>newLinkedList();

        returnValue.add(getColumnName());
        returnValue.add(getSqlDataType());
        returnValue.add(toStringInt(getColumnSize()));
        returnValue.add(toStringInt(getDecimalDigits()));
        returnValue.add(toStringInt(getCharacterOctetLength()));
        returnValue.add(String.valueOf(getOrdinalIdx()));
        returnValue.add(toCharPK());
        returnValue.add(toCharNull());

        return returnValue;
    }

    private String toStringInt(int intValue) {
        return intValue > 0 ? String.valueOf(intValue) : null;
    }

    private String toCharNull() {
        String returnValue = null;
        if (isNull().equalsIgnoreCase("YES")) {
            returnValue = BOOL_CHAR.YES.val();
        } else if (isNull().equalsIgnoreCase("NO")) {
            returnValue = BOOL_CHAR.NO.val();
        } else {
            returnValue = BOOL_CHAR.NO.val();
        }
        return returnValue;
    }

    private String toCharPK() {
        return isPrimary() ? BOOL_CHAR.YES.val() : BOOL_CHAR.NO.val();
    }

    private enum BOOL_CHAR {
        YES("Y"),
        NO("N");

        private String val;

        private BOOL_CHAR(String val) {
            this.val = val;
        }

        public String val() {
            return val;
        }
    }

    public static class ColumnInfoBuilder {
        private String columnName;
        private String sqlDataType;
        private int decimalDigits;
        private int ordinalIdx;
        private boolean isPrimary;
        private String isNull;
        private int columnSize;
        private int characterOctetLength;

        public ColumnInfoBuilder columnName(String columnName) {
            this.columnName = columnName;
            return this;
        }

        public ColumnInfoBuilder sqlDataType(String sqlDataType) {
            this.sqlDataType = sqlDataType;
            return this;
        }

        public ColumnInfoBuilder decimalDigits(int decimalDigits) {
            this.decimalDigits = decimalDigits;
            return this;
        }

        public ColumnInfoBuilder ordinalIdx(int ordinalIdx) {
            this.ordinalIdx = ordinalIdx;
            return this;
        }

        public ColumnInfoBuilder isPrimary(boolean isPrimary) {
            this.isPrimary = isPrimary;
            return this;
        }

        public ColumnInfoBuilder isNull(String isNull) {
            this.isNull = isNull;
            return this;
        }

        public ColumnInfoBuilder columnSize(int columnSize) {
            this.columnSize = columnSize;
            return this;
        }

        public ColumnInfoBuilder characterOctetLength(int characterOctetLength) {
            this.characterOctetLength = characterOctetLength;
            return this;
        }

        public ColumnInfo build() {
            return new ColumnInfo(columnName, sqlDataType, decimalDigits, ordinalIdx, isPrimary, isNull, columnSize, characterOctetLength);
        }
    }

}
