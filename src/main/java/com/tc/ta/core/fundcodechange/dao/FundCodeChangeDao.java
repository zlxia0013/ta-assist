package com.tc.ta.core.fundcodechange.dao;

import java.util.*;
import org.apache.ibatis.annotations.Param;
import com.tc.ta.core.fundcodechange.dto.FundCodeChangeDto;
import com.tc.ta.core.fundcodechange.dto.FundCodeChangeSearchCondition;
import com.tc.ta.core.fundcodechange.pojo.FundCodeChange;

public interface FundCodeChangeDao {
		FundCodeChange getById(@Param("id") int id);

		void add(FundCodeChange fundCodeChange);

		void update(FundCodeChange fundCodeChange);

		void delete(Integer fundCodeChangeId);

		List<FundCodeChange> searchPojo(FundCodeChangeSearchCondition sc);

		List<FundCodeChangeDto> searchDto(FundCodeChangeSearchCondition sc);

		Integer searchCnt(FundCodeChangeSearchCondition sc);

//======================================= new method create here ====================================/

}
