package cosimocrupi.L1.payloads;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorsListDTO(String mess, LocalDateTime timeStamp, List<String> errorList) {
}
