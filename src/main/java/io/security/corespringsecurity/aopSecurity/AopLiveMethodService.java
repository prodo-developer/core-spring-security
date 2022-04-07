package io.security.corespringsecurity.aopSecurity;

import org.springframework.stereotype.Service;

@Service
public class AopLiveMethodService {

    public void liveMethodSecured(){

        System.out.println("liveMethodSecured");
    }
}