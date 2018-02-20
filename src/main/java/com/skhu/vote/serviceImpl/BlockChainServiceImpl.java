package com.skhu.vote.serviceImpl;

/**
 * Created by ds on 2018-02-04.
 */

/**
 * 블록 체인
 * 1. 블록 체크
 * 2. 블록 생성
 * 3. 블록 삽입
 * 4. 브로드 캐스트
 * 5. 블록 체크
 */

/*
@Service
public class BlockChainServiceImpl implements BlockChainService {

    @Autowired
    SessionService sessionService;

    //블록 생성
    private BlockBody createBlockBody(final CandidateReq candidateReq, final String code) {
        return new BlockBody(candidateReq, code);
    }

    //블록 삽입
    @Override
    public boolean insertBlock(final VoteReq voteReq) {
        //블록체인 검사
        if(checkBlockChain()) {
            //마지막 블록 해쉬값 리턴
            String tempHashCode = getPreBlockHashCode();
            //새로 삽입할 블록 리스트 생성
            for(CandidateReq candidateReq : voteReq.getVoteList()) {
                //블록 생성
                BlockHeader block = new BlockHeader(createBlockBody(candidateReq, voteReq.getCode()), tempHashCode);
                //레디스 블록 삽입

                //마지막 블록 해쉬값 갱신
                tempHashCode = block.getMerkleHash();
            }
            if(checkBlockChain()) {
                //commit;
                return true;
            }else {
                //rollback;
                return false;
            }
        }else {
            return false;
        }
    }

    //블록 체인 검사
    public boolean checkBlockChain() {
        return false;
    }

    //마지막 블록 해쉬값 리턴
    public String getPreBlockHashCode() {
        return "1";
    }
}
*/
