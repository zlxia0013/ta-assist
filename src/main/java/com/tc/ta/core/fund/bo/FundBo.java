package com.tc.ta.core.fund.bo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.tc.ta.util.exception.ComRuntimeException;
import com.tc.ta.core.fund.bo.FundBo;
import com.tc.ta.core.fund.dao.FundDao;
import com.tc.ta.core.fund.dto.*;
import com.tc.ta.core.fund.pojo.Fund;

@Service
public class FundBo {
    @Autowired
    private FundDao fundDao;

    public Fund getById(Integer id) {
		return fundDao.getById(id);
    }

    public void add(Fund fund) {
		validateAddUpdate(fund, true);
		fundDao.add(fund);
    }

    public void update(Fund fund) {
		validateAddUpdate(fund, false);
		fundDao.update(fund);
    }

    private void validateAddUpdate(Fund fund, boolean isAdd) {
		if (fund == null) {
		    throw new ComRuntimeException("对象不能为空");
        }
    }

    public void delete(Integer fundId) {
		fundDao.delete(fundId);
    }

    public List<Fund> searchPojo(FundSearchCondition sc) {
		return fundDao.searchPojo(sc);
    }

    public List<FundDto> searchDto(FundSearchCondition sc) {
		return fundDao.searchDto(sc);
    }

    public Integer searchCnt(FundSearchCondition sc) {
		return fundDao.searchCnt(sc);
    }

		//======================================= new method create here ====================================/
}
