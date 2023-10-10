package com.techcourse.service;

import com.techcourse.domain.User;
import org.springframework.transaction.TransactionExecutor;

public class TxUserService implements UserService {

    private final AppUserService appUserService;
    private final TransactionExecutor transactionExecutor;

    public TxUserService(final AppUserService appUserService, final TransactionExecutor transactionExecutor) {
        this.appUserService = appUserService;
        this.transactionExecutor = transactionExecutor;
    }

    @Override
    public User findById(final long id) {
        return transactionExecutor.execute(() -> appUserService.findById(id));
    }

    @Override
    public void insert(final User user) {
        transactionExecutor.execute(() -> appUserService.insert(user));
    }

    @Override
    public void changePassword(final long id, final String newPassword, final String createBy) {
        transactionExecutor.execute(() -> appUserService.changePassword(id, newPassword, createBy));
    }
}
