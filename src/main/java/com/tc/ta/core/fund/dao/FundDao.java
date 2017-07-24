package com.tc.ta.core.fund.dao;

import java.util.*;
import org.apache.ibatis.annotations.Param;
import com.tc.ta.core.fund.dto.FundDto;
import com.tc.ta.core.fund.dto.FundSearchCondition;
import com.tc.ta.core.fund.pojo.Fund;

public interface FundDao {
		Fund getById(@Param("id") int id);

		void add(Fund fund);

		void update(Fund fund);

		void delete(Integer fundId);

		List<Fund> searchPojo(FundSearchCondition sc);

		List<FundDto> searchDto(FundSearchCondition sc);

		Integer searchCnt(FundSearchCondition sc);

//======================================= new method create here ====================================/

}
