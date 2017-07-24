package com.tc.ta.core.fundbaseinfo.bo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.tc.ta.util.exception.ComRuntimeException;
import com.tc.ta.core.fundbaseinfo.bo.FundBaseInfoBo;
import com.tc.ta.core.fundbaseinfo.dao.FundBaseInfoDao;
import com.tc.ta.core.fundbaseinfo.dto.*;
import com.tc.ta.core.fundbaseinfo.pojo.FundBaseInfo;

@Service
public class FundBaseInfoBo {
    @Autowired
    private FundBaseInfoDao fundBaseInfoDao;

    public FundBaseInfo getById(Integer id) {
		return fundBaseInfoDao.getById(id);
    }

    public void add(FundBaseInfo fundBaseInfo) {
		validateAddUpdate(fundBaseInfo, true);
		fundBaseInfoDao.add(fundBaseInfo);
    }

    public void update(FundBaseInfo fundBaseInfo) {
		validateAddUpdate(fundBaseInfo, false);
		fundBaseInfoDao.update(fundBaseInfo);
    }

    private void validateAddUpdate(FundBaseInfo fundBaseInfo, boolean isAdd) {
		if (fundBaseInfo == null) {
		    throw new ComRuntimeException("对象不能为空");
        }
    }

    public void delete(Integer fundBaseInfoId) {
		fundBaseInfoDao.delete(fundBaseInfoId);
    }

    public List<FundBaseInfo> searchPojo(FundBaseInfoSearchCondition sc) {
		return fundBaseInfoDao.searchPojo(sc);
    }

    public List<FundBaseInfoDto> searchDto(FundBaseInfoSearchCondition sc) {
		return fundBaseInfoDao.searchDto(sc);
    }

    public Integer searchCnt(FundBaseInfoSearchCondition sc) {
		return fundBaseInfoDao.searchCnt(sc);
    }

		//======================================= new method create here ====================================/
}
