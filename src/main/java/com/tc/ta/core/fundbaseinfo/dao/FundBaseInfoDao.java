package com.tc.ta.core.fundbaseinfo.dao;

import java.util.*;
import org.apache.ibatis.annotations.Param;
import com.tc.ta.core.fundbaseinfo.dto.FundBaseInfoDto;
import com.tc.ta.core.fundbaseinfo.dto.FundBaseInfoSearchCondition;
import com.tc.ta.core.fundbaseinfo.pojo.FundBaseInfo;

public interface FundBaseInfoDao {
		FundBaseInfo getById(@Param("id") int id);

		void add(FundBaseInfo fundBaseInfo);

		void update(FundBaseInfo fundBaseInfo);

		void delete(Integer fundBaseInfoId);

		List<FundBaseInfo> searchPojo(FundBaseInfoSearchCondition sc);

		List<FundBaseInfoDto> searchDto(FundBaseInfoSearchCondition sc);

		Integer searchCnt(FundBaseInfoSearchCondition sc);

//======================================= new method create here ====================================/

}
