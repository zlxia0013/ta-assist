package com.tc.ta.core.fundcodechange.bo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.tc.ta.util.exception.ComRuntimeException;
import com.tc.ta.core.fundcodechange.bo.FundCodeChangeBo;
import com.tc.ta.core.fundcodechange.dao.FundCodeChangeDao;
import com.tc.ta.core.fundcodechange.dto.*;
import com.tc.ta.core.fundcodechange.pojo.FundCodeChange;

@Service
public class FundCodeChangeBo {
    @Autowired
    private FundCodeChangeDao fundCodeChangeDao;

    public FundCodeChange getById(Integer id) {
		return fundCodeChangeDao.getById(id);
    }

    public void add(FundCodeChange fundCodeChange) {
		validateAddUpdate(fundCodeChange, true);
		fundCodeChangeDao.add(fundCodeChange);
    }

    public void update(FundCodeChange fundCodeChange) {
		validateAddUpdate(fundCodeChange, false);
		fundCodeChangeDao.update(fundCodeChange);
    }

    private void validateAddUpdate(FundCodeChange fundCodeChange, boolean isAdd) {
		if (fundCodeChange == null) {
		    throw new ComRuntimeException("对象不能为空");
        }
    }

    public void delete(Integer fundCodeChangeId) {
		fundCodeChangeDao.delete(fundCodeChangeId);
    }

    public List<FundCodeChange> searchPojo(FundCodeChangeSearchCondition sc) {
		return fundCodeChangeDao.searchPojo(sc);
    }

    public List<FundCodeChangeDto> searchDto(FundCodeChangeSearchCondition sc) {
		return fundCodeChangeDao.searchDto(sc);
    }

    public Integer searchCnt(FundCodeChangeSearchCondition sc) {
		return fundCodeChangeDao.searchCnt(sc);
    }

		//======================================= new method create here ====================================/
}
