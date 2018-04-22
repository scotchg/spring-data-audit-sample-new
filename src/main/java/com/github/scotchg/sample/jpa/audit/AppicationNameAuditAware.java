package com.github.scotchg.sample.jpa.audit;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * アプリケーション名を監査実行者として登録するクラス.
 */
@RequiredArgsConstructor
public class AppicationNameAuditAware implements AuditorAware{
    private final String applicationName;

    /**
     * アプリケーション名をSpring dataの監査の実行者として登録する.
     * @return 未存在の場合はアプリケーション名を設定.
     */
    @Override
    public Optional getCurrentAuditor() {
        return Optional.of(applicationName);
    }
}
