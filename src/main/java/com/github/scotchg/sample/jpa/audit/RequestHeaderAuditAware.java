package com.github.scotchg.sample.jpa.audit;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

@RequiredArgsConstructor
public class RequestHeaderAuditAware implements AuditorAware<String> {
    private final String auditorHeaderKey;
    private final String applicationName;

    /**
     * リクエストヘッダから取得した実行者を取得し、Spring dataの監査の実行者として登録する.
     * @return リクエストヘッダから取得した実行者. 未存在の場合はアプリケーション名を設定.
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(
                Optional.ofNullable(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()))
                        .map(ServletRequestAttributes::getRequest)
                        .map(s -> s.getHeader(auditorHeaderKey))
                        .orElse(applicationName));
    }
}
