package io.security.corespringsecurity.security.voter;

import io.security.corespringsecurity.service.SecurityResourceService;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.util.Collection;
import java.util.List;

public class IpAddressVoter implements AccessDecisionVoter<Object> {

    private SecurityResourceService securityResourceService;

    public IpAddressVoter(SecurityResourceService securityResourceService) {

        this.securityResourceService = securityResourceService;
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    /**
     * 사용자정보 인증정보, 요청정보, 권한정보를 받아 심의한다.
     * @param authentication
     * @param object
     * @param attributes
     * @return
     */
    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {

        // 사용자의 IP 값 추출, 세션ID도 가져올 수 있음.
        WebAuthenticationDetails details = (WebAuthenticationDetails)authentication.getDetails();
        String remoteAddress = details.getRemoteAddress();

        List<String> accessIpList = securityResourceService.getAccessIpList(); // IP 정보

        int result = ACCESS_DENIED;

        for(String ipAddress : accessIpList){
            if(remoteAddress.equals(ipAddress)){
                return ACCESS_ABSTAIN; // 다른 심의
            }
        }
        
        // IP가 허용이 안되면 바로 차단
        if(result == ACCESS_DENIED){
            throw new AccessDeniedException("Invalid IpAddress");
        }

        return result;
    }
}