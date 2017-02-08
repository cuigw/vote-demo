package com.vote.web;

import com.vote.domain.BaseVote;
import com.vote.domain.BaseVoteMapper;
import com.vote.result.SisapResult;
import com.vote.util.MapUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能说明: 投票控制器<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author cgw<br>
 * 开发时间: 2017年02月07日<br>
 */
@RestController
@RequestMapping(value = "/votes")
public class VoteController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    BaseVoteMapper baseVoteMapper;

    @ApiOperation(value="根据商户号获取所有投票对象", notes="根据商户号获取所有投票对象")
    @RequestMapping(value="/business/{business_id}", method= RequestMethod.POST)
    public SisapResult getVoteByBusinessId(@PathVariable String business_id) {
        SisapResult sisapResult = new SisapResult("0", "查询成功!");
        try {
            List<BaseVote> baseVotes = baseVoteMapper.findByBusinessId(business_id);
            if (null == baseVotes || baseVotes.size() <= 0 ) {
                sisapResult.setError_no("1");
                sisapResult.setError_info("未找到商户号为" + business_id + "的投票数据！");
            } else {
                List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
                for (BaseVote baseVote : baseVotes) {
                    Map<String, String> map = new HashMap<String, String>();
                    map = MapUtil.toMapString(baseVote);
                    resultList.add(map);
                }
                sisapResult.setResultList(resultList);
            }
        } catch (Exception e) {
            logger.error("（VoteController.getVoteByBusinessId）根据商户号获取所有投票对象异常", e );
            sisapResult.setError_no("-1");
            sisapResult.setError_info("根据商户号获取所有投票对象异常,请稍后重试！");
        }
        return sisapResult;
    }

    @ApiOperation(value="创建投票对象", notes="根据BaseVote对象创建")
    @RequestMapping(value="/voteAdd/", method=RequestMethod.POST)
    public SisapResult postVote(@ModelAttribute BaseVote baseVote) {
        SisapResult sisapResult = new SisapResult("0", "添加成功!");
        try {
            int i = baseVoteMapper.insert(baseVote);
            if (i <= 0) {
                sisapResult.setError_no("1");
                sisapResult.setError_info("添加投票对象失败,请稍后重试！");
            }
        } catch (Exception e) {
            logger.error("（VoteController.postVote）添加投票对象异常", e );
            sisapResult.setError_no("-1");
            sisapResult.setError_info("添加投票对象异常,请稍后重试！");
        }
        return sisapResult;
    }

    @ApiOperation(value="更新投票对象详细信息", notes="根据url的vote_id来指定更新对象，并根据传过来的vote信息来更新投票对象详细信息")
    @RequestMapping(value="/voteUpdate/{vote_id}", method=RequestMethod.POST)
    public SisapResult putVote(@PathVariable Long vote_id, @ModelAttribute BaseVote baseVote) {
        SisapResult sisapResult = new SisapResult("0", "更新成功!");
        try {
            BaseVote baseVoteQuery = baseVoteMapper.query(vote_id);
            if (null == baseVoteQuery) {
                sisapResult.setError_no("2");
                sisapResult.setError_info("投票对象不存在！");
            }
            baseVoteMapper.update(baseVote);
        } catch (Exception e) {
            logger.error("（VoteController.putVote）更新投票对象异常", e );
            sisapResult.setError_no("-1");
            sisapResult.setError_info("更新投票对象异常,请稍后重试！");
        }
        return sisapResult;
    }

    @ApiOperation(value="获取投票对象详细信息", notes="根据url的vote_id来获取投票对象的详细信息")
    @RequestMapping(value="/voteGet/{vote_id}", method=RequestMethod.POST)
    public SisapResult getVote(@PathVariable Long vote_id) {
        SisapResult sisapResult = new SisapResult("0", "查询成功!");
        try {
            BaseVote baseVoteQuery = baseVoteMapper.query(vote_id);
            if (null == baseVoteQuery) {
                sisapResult.setError_no("2");
                sisapResult.setError_info("投票对象不存在！");
            } else {
                sisapResult.setResultMap(MapUtil.toMapString(baseVoteQuery));
            }
        } catch (Exception e) {
            logger.error("（VoteController.getVote）查询投票对象异常", e );
            sisapResult.setError_no("-1");
            sisapResult.setError_info("查询投票对象异常,请稍后重试！");
        }
        return sisapResult;
    }

    @ApiOperation(value="删除投票对象", notes="根据url的vote_id来指定删除投票对象")
    @RequestMapping(value="/voteDelete/{vote_id}", method=RequestMethod.POST)
    public SisapResult deleteVote(@PathVariable Long vote_id) {
        SisapResult sisapResult = new SisapResult("0", "删除成功!");
        try {
            BaseVote baseVoteQuery = baseVoteMapper.query(vote_id);
            if (null == baseVoteQuery) {
                sisapResult.setError_no("2");
                sisapResult.setError_info("投票对象不存在！");
            } else {
                baseVoteMapper.delete(vote_id);
            }
        } catch (Exception e) {
            logger.error("（VoteController.deleteVote）删除投票对象异常", e );
            sisapResult.setError_no("-1");
            sisapResult.setError_info("删除投票对象异常,请稍后重试！");
        }
        return sisapResult;
    }

    @ApiOperation(value="增加票数（投票）", notes="根据url的vote_id来指定更新票数")
    @RequestMapping(value="/voteIncrease/{vote_id}", method=RequestMethod.POST)
    public SisapResult voteIncrease(@PathVariable Long vote_id) {
        SisapResult sisapResult = new SisapResult("0", "投票成功!");
        try {
            BaseVote baseVoteQuery = baseVoteMapper.query(vote_id);
            if (null == baseVoteQuery) {
                sisapResult.setError_no("2");
                sisapResult.setError_info("投票选项不存在！");
            }
            baseVoteMapper.voteIncrease(vote_id);
        } catch (Exception e) {
            logger.error("（VoteController.voteIncrease）投票异常", e );
            sisapResult.setError_no("-1");
            sisapResult.setError_info("投票异常,请稍后重试！");
        }
        return sisapResult;
    }
}
