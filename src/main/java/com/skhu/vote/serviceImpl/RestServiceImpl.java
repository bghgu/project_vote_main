package com.skhu.vote.serviceImpl;

import com.skhu.vote.model.Req.VoteReq;
import com.skhu.vote.model.Res.DefaultRes;
import com.skhu.vote.model.StatusEnum;
import com.skhu.vote.service.RestService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * Created by ds on 2018-03-20.
 */

@Service
public class RestServiceImpl implements RestService {

    //final static String BASE = "http://bghgu.tk";
    private final static String BASE = "http://127.0.0.1";
    private final static String BASEURI[] = {
            BASE + ":8081/vote/boardcast",
            BASE + ":8082/vote/boardcast",
    };

    @Override
    public boolean boardcast(final VoteReq voteReq) {
        RestTemplate restTemplate = new RestTemplate();
        for(int i = 0; i < 2; i++) {
            DefaultRes check = restTemplate.postForObject(BASEURI[i], voteReq, DefaultRes.class);
            if (check.getStatus().equals(StatusEnum.FAIL)) break;
        }
        return false;
    }
}
