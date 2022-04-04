package io.security.corespringsecurity.aopSecurity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * 메소드 들어오기 위한 테스트
 */
@Service
public class AopMethodService {

    public void methodSecured() {

        System.out.println("methodSecured");
    }
}