package persistence;

import domain.Sequence;

public interface SequenceDAO {
    Sequence getSequence(Sequence sequence);
    boolean updateSequence(Sequence sequence);
}
