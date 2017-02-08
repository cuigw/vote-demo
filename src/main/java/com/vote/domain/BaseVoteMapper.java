package com.vote.domain;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 功能说明: BaseVote 投票基本信息 操作接口<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author cgw<br>
 * 开发时间: 2017年02月07日<br>
 */
@Mapper
public interface BaseVoteMapper {

    /**
     * 根据商户号查询下属投票对象
     * @param business_id 商户号
     * @return
     */
    @Select("SELECT * FROM BASE_VOTE WHERE BUSINESS_ID = #{business_id}")
    List<BaseVote> findByBusinessId(@Param("business_id") String business_id);

    /**
     * 根据主键查询BaseVote对象
     */
    @Select("SELECT *  FROM BASE_VOTE WHERE VOTE_ID = #{vote_id}")
    public BaseVote query(@Param("vote_id") Long vote_id);

    /**
     * 新增BaseVote对象
     */
    @InsertProvider(method = "insert", type = BaseVoteProvider.class)
    public int insert(BaseVote basevote);

    /**
     * 修改BaseVote对象
     */
    @UpdateProvider(method = "update", type = BaseVoteProvider.class)
    public int update(BaseVote basevote);

    /**
     * 删除BaseVote对象
     */
    @Delete("delete FROM BASE_VOTE WHERE VOTE_ID = #{vote_id}")
    public int delete(@Param("vote_id") Long vote_id);

    /**
     * 投票数量+1
     */
    @Update("update BASE_VOTE set VOTE_SUM = VOTE_SUM + 1 where VOTE_ID = #{vote_id}")
    public int voteIncrease(@Param("vote_id") Long vote_id);
}
