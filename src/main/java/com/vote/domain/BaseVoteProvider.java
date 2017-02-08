package com.vote.domain;

import com.vote.util.SqlProviderUtil;

/**
 * Created by cgw on 2017/2/7.
 */
public class BaseVoteProvider {

    public String insert(BaseVote basevote) {
        return SqlProviderUtil.provideInsertNotBlank(basevote, "BASE_VOTE");
    }

    public String update(BaseVote basevote) {
        StringBuffer sql = new StringBuffer("UPDATE BASE_VOTE ");
        sql.append(SqlProviderUtil.provideSetterNotBlank(basevote));
        sql.append(" WHERE ");
        sql.append("VOTE_ID=#{vote_id}");
        return sql.toString();
    }
}
