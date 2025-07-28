package cosimocrupi.L1.payloads;

import java.time.LocalDateTime;

public record ErrorsDTO(String mess, LocalDateTime timeStamp) {
}
