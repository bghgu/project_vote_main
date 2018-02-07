package com.skhu.vote.model.Req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by ds on 2018-02-04.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateReq {
    @NotEmpty
    private int voteId;
    @NotEmpty
    private int candidateId;
}
