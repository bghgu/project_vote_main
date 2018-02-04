package com.skhu.vote.model.Req;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by ds on 2018-02-04.
 */
@Data
public class CandidateReq {
    @NotEmpty
    private int voteId;
    @NotEmpty
    private int candidateId;
}
