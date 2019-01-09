package ug.adamtrawinski.laptops.service;

import ug.adamtrawinski.laptops.domain.SerialCode;

import java.util.List;

public interface SerialCodeManager {
    void addSerialCode(SerialCode serialCode);
    SerialCode findSerialCodeById(long id);
    List<SerialCode> getAllSerialCodes();
    void deleteSerialCode(long id);
    void updateSerialCode(SerialCode serialCode);
    void clearTable();
}
