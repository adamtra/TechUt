package ug.adamtrawinski.laptops.service;

import ug.adamtrawinski.laptops.domain.Processor;

import java.util.List;

public interface ProcessorManager {
    void addProcessor(Processor processor);
    Processor findProcessorById(long id);
    List<Processor> getAllProcessors();
    void deleteProcessor(long id);
    void updateProcessor(Processor processor);
    void clearTable();
}
