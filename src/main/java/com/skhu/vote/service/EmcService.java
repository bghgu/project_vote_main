package com.skhu.vote.service;

import com.skhu.vote.model.DefaultResponse;

/**
 * Created by ds on 2018-01-30.
 */

public interface EmcService {
    DefaultResponse checkId(final String id);
    DefaultResponse voterConfirmation(final String id);
}
