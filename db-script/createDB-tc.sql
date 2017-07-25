/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.5.54 : Database - ta-assist
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ta-assist` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ta-assist`;

/*Table structure for table `allow_fund_change` */

DROP TABLE IF EXISTS `allow_fund_change`;

CREATE TABLE `allow_fund_change` (
  `fund_code` varchar(6) DEFAULT NULL COMMENT '基金代码',
  `share_class` char(1) DEFAULT NULL COMMENT '份额类别',
  `target_fund_code` varchar(6) DEFAULT NULL COMMENT '转换时目标代码',
  `target_share_type` char(1) DEFAULT NULL COMMENT '转换时目标份额类别',
  `distributor_code` varchar(9) DEFAULT NULL COMMENT '销售商代码(费率设置时不显示，默认***)',
  UNIQUE KEY `i_allow_fund_change` (`fund_code`,`share_class`,`target_fund_code`,`target_share_type`,`distributor_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基金转换关系';

/*Data for the table `allow_fund_change` */

/*Table structure for table `bonus_schema` */

DROP TABLE IF EXISTS `bonus_schema`;

CREATE TABLE `bonus_schema` (
  `fund_code` varchar(6) DEFAULT NULL COMMENT '基金代码',
  `share_class` char(1) DEFAULT NULL COMMENT '份额类别',
  `registration_date` varchar(8) DEFAULT NULL COMMENT '权益登记日期',
  `divident_date` varchar(8) DEFAULT NULL COMMENT '分红日/发放日',
  `xr_date` varchar(8) DEFAULT NULL COMMENT '除权日',
  `dividend_per_unit` decimal(16,2) DEFAULT NULL COMMENT '单位基金分红金额（含税）',
  `draw_bonus_unit` decimal(10,0) DEFAULT NULL COMMENT '分红单位',
  UNIQUE KEY `i_bonus_schema` (`fund_code`,`share_class`,`registration_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分红方案';

/*Data for the table `bonus_schema` */

/*Table structure for table `custodian_bank` */

DROP TABLE IF EXISTS `custodian_bank`;

CREATE TABLE `custodian_bank` (
  `code` varchar(9) DEFAULT NULL COMMENT '托管行代码',
  `name` varchar(100) DEFAULT NULL COMMENT '托管行名称',
  `interface_ver` varchar(10) DEFAULT NULL COMMENT '会计接口版本',
  `file_name_format` char(1) DEFAULT NULL COMMENT '会计接口文件样式',
  `fax` varchar(36) DEFAULT NULL COMMENT '传真号码',
  UNIQUE KEY `i_custodian_bank` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='托管行信息';

/*Data for the table `custodian_bank` */

/*Table structure for table `distributor_info` */

DROP TABLE IF EXISTS `distributor_info`;

CREATE TABLE `distributor_info` (
  `distributor_code` varchar(9) DEFAULT NULL COMMENT '销售人代码',
  `distributor_name` varchar(40) DEFAULT NULL COMMENT '销售人名称',
  `distributor_type` char(1) DEFAULT NULL COMMENT '销售人类型',
  `address` varchar(128) DEFAULT NULL COMMENT '总部地址',
  `zip_code` varchar(6) DEFAULT NULL COMMENT '邮政编码',
  `contract` varchar(64) DEFAULT NULL COMMENT '销售商联系人',
  `phone` varchar(64) DEFAULT NULL COMMENT '销售商联系电话',
  `fax` varchar(36) DEFAULT NULL COMMENT '销售商传真号码',
  `email` varchar(36) DEFAULT NULL COMMENT '销售商电子邮件',
  `distributor_status` char(1) DEFAULT NULL COMMENT '销售人状态',
  `is_sync` varchar(1) DEFAULT NULL COMMENT '是否已同步到TA',
  `ref_code` varchar(9) DEFAULT NULL COMMENT '参考销售人代码',
  UNIQUE KEY `i_distributor_code` (`distributor_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='销售人信息';

/*Data for the table `distributor_info` */

/*Table structure for table `distributor_trade_info` */

DROP TABLE IF EXISTS `distributor_trade_info`;

CREATE TABLE `distributor_trade_info` (
  `distributor_code` varchar(9) DEFAULT NULL COMMENT '销售商代码',
  `concentrate_reg` char(1) DEFAULT NULL COMMENT '是否大集中',
  `over_one_transaction_acco` char(1) DEFAULT NULL COMMENT '是否支持多交易账户',
  `one_step_transfer` char(1) DEFAULT NULL COMMENT '是否支持一步转托管',
  `fund_code_change_mode` char(1) DEFAULT NULL COMMENT '基金转换确认方式',
  `sub_error_send_type` char(1) DEFAULT NULL COMMENT '认购失败数据下发方式',
  `need_param_file` char(1) DEFAULT NULL COMMENT '是否需要参数文件',
  UNIQUE KEY `i_distributor_trade_info` (`distributor_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='销售商交易规则';

/*Data for the table `distributor_trade_info` */

/*Table structure for table `fa_info` */

DROP TABLE IF EXISTS `fa_info`;

CREATE TABLE `fa_info` (
  `fa_code` varchar(9) DEFAULT NULL COMMENT '估值机构代码',
  `name` varchar(100) DEFAULT NULL COMMENT '估值机构名称',
  `interface_ver` varchar(10) DEFAULT NULL COMMENT '估值接口版本',
  `file_name_format` char(1) DEFAULT NULL COMMENT '估值接口文件样式',
  `fax` varchar(36) DEFAULT NULL COMMENT '传真号码',
  UNIQUE KEY `i_fa_info` (`fa_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='估值机构信息';

/*Data for the table `fa_info` */

/*Table structure for table `fare_belong` */

DROP TABLE IF EXISTS `fare_belong`;

CREATE TABLE `fare_belong` (
  `fund_code` varchar(6) DEFAULT NULL COMMENT '基金代码',
  `business_code` varchar(3) DEFAULT NULL COMMENT '业务代码',
  `distributor_code` varchar(9) DEFAULT NULL COMMENT '销售人代码',
  `fare_type` char(20) DEFAULT NULL COMMENT '费用类型',
  `other_fund_code` varchar(6) DEFAULT NULL COMMENT '对方基金代码',
  `belong_type` char(1) DEFAULT NULL COMMENT '费用分成方式',
  `distributor_ratio` decimal(9,8) DEFAULT NULL COMMENT '归销售商比例(%)',
  `regist_ratio` decimal(9,8) DEFAULT NULL COMMENT '归管理人比例(%)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='费用分成设置';

/*Data for the table `fare_belong` */

/*Table structure for table `fare_rate` */

DROP TABLE IF EXISTS `fare_rate`;

CREATE TABLE `fare_rate` (
  `fund_code` varchar(6) DEFAULT NULL COMMENT '基金代码',
  `share_class` char(1) DEFAULT NULL COMMENT '份额类别',
  `target_fund_code` varchar(6) DEFAULT NULL COMMENT '转换时目标代码',
  `target_share_type` char(1) DEFAULT NULL COMMENT '转换时目标份额类别',
  `business_code` varchar(3) DEFAULT NULL COMMENT '业务类型,参考数据字典2003',
  `fare_type` varchar(3) DEFAULT NULL COMMENT '费用类型,参考数据字典2004',
  `distributor_code` varchar(9) DEFAULT NULL COMMENT '销售商代码(费率设置时不显示，默认***)',
  `capital_type` varchar(3) DEFAULT NULL COMMENT '资金渠道(费率设置时不显示，默认***)',
  `individual_or_institution` char(1) DEFAULT NULL COMMENT '客户类型(费率设置时不显示，默认*)',
  `vol_lower_limit` decimal(16,2) DEFAULT NULL COMMENT '份额下限',
  `vol_upper_limit` decimal(16,2) DEFAULT NULL COMMENT '份额上限',
  `amount_lower_limit` decimal(16,2) DEFAULT NULL COMMENT '金额下限',
  `amount_upper_limit` decimal(16,2) DEFAULT NULL COMMENT '金额上限',
  `days_lower_limit` decimal(16,2) DEFAULT NULL COMMENT '天数下限',
  `days_upper_limit` decimal(16,2) DEFAULT NULL COMMENT '天数上限',
  `fee_rate_flag` char(1) DEFAULT NULL COMMENT '费率类型,0-绝对费率,1-相对费率(分成比例)(优惠设置时不现实，默认0)',
  `rate_fee` decimal(9,8) DEFAULT NULL COMMENT '费率,费率类型为0时表示实际计算费率如0.015,为1时表示分成比例如0.4,只支持扣除归基金资产后的分成比例(优惠设置时不现实，默认0)',
  `constant_fee` decimal(16,2) DEFAULT NULL COMMENT '固定费用(优惠设置时不现实，默认0)',
  `least_benefit_agio` decimal(9,8) DEFAULT NULL COMMENT '至少优惠折扣,即费率上至少会乘以此费率(费率设置时不显示，默认1)',
  `lowest_benefit_agio` decimal(9,8) DEFAULT NULL COMMENT '最优惠折扣(费率设置时不显示，默认1)',
  `lowest_fare_rate` decimal(9,8) DEFAULT NULL COMMENT '乘以折扣之后，不能低于此费率(费率设置时不显示，默认0,不限制)',
  UNIQUE KEY `i_fare_rate` (`fund_code`,`share_class`,`target_fund_code`,`target_share_type`,`business_code`,`fare_type`,`distributor_code`,`capital_type`,`individual_or_institution`,`vol_lower_limit`,`vol_upper_limit`,`amount_lower_limit`,`amount_upper_limit`,`days_lower_limit`,`days_upper_limit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基金费率表(其中销售商代码为***,表示基础费率,销售商代码有值，表示费用优惠';

/*Data for the table `fare_rate` */

/*Table structure for table `fund` */

DROP TABLE IF EXISTS `fund`;

CREATE TABLE `fund` (
  `fund_code` varchar(6) DEFAULT NULL COMMENT '基金代码',
  `fund_name` varchar(40) DEFAULT NULL COMMENT '基金名称',
  `fund_english_name` varchar(40) DEFAULT NULL COMMENT '基金英文名',
  `fund_class` char(1) DEFAULT NULL COMMENT '基金性质',
  `fund_type` char(1) DEFAULT NULL COMMENT '基金类型',
  `share_class` char(1) DEFAULT NULL COMMENT '份额分类',
  `currency_type` char(3) DEFAULT NULL COMMENT '结算币种',
  `fund_manager_code` varchar(6) DEFAULT NULL COMMENT '管理人代码',
  `custodian_code` varchar(9) DEFAULT NULL COMMENT '托管人代码',
  `fa_code` char(9) DEFAULT NULL COMMENT '估值核算机构代码',
  `manager_fee_rate` decimal(5,4) DEFAULT NULL COMMENT '管理费率',
  `specification` varchar(100) DEFAULT NULL COMMENT '基金描述',
  `fund_status` char(1) DEFAULT NULL COMMENT '基金状态',
  `is_guaranteed_fund` char(1) DEFAULT NULL COMMENT '是否保本基金',
  `is_structured_fund` char(1) DEFAULT NULL COMMENT '是否结构化分级基金',
  `is_multi_fund` char(1) DEFAULT NULL COMMENT '是否多基金分级基金',
  `is_qdii_fund` char(1) DEFAULT NULL COMMENT '是否QDII基金',
  `is_etf_fund` char(1) DEFAULT NULL COMMENT '是否ETF指数基金',
  `is_lof` char(1) DEFAULT NULL COMMENT '是否LOF基金',
  `ta_code` varchar(6) DEFAULT NULL COMMENT 'TA代码',
  `is_sync` varchar(1) DEFAULT NULL COMMENT '是否已同步到TA',
  `ref_fund` varchar(6) DEFAULT NULL COMMENT '同步参考基金代码',
  `add_user_id` int(11) DEFAULT NULL COMMENT '添加人',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基金基本信息';

/*Data for the table `fund` */

/*Table structure for table `fund_code_change` */

DROP TABLE IF EXISTS `fund_code_change`;

CREATE TABLE `fund_code_change` (
  `fund_code` varchar(6) DEFAULT NULL COMMENT '基金代码',
  `share_class` varchar(1) DEFAULT NULL COMMENT '份额类别',
  `outer_fund_code` varchar(6) DEFAULT NULL COMMENT '外部基金代码',
  `outer_fund_name` varchar(100) DEFAULT NULL COMMENT '外部基金名称',
  UNIQUE KEY `i_fund_code_change` (`fund_code`,`share_class`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基金内外部代码对照表';

/*Data for the table `fund_code_change` */

/*Table structure for table `fund_distributor_relation` */

DROP TABLE IF EXISTS `fund_distributor_relation`;

CREATE TABLE `fund_distributor_relation` (
  `fund_code` varchar(6) DEFAULT NULL COMMENT '基金代码',
  `distributor_code` varchar(9) DEFAULT NULL COMMENT '销售商代码',
  `signup_date` varchar(10) DEFAULT NULL COMMENT '签约起始日期',
  `sub_pay_type` char(1) DEFAULT NULL COMMENT '认购款到账方式',
  `pur_settle_type` char(1) DEFAULT NULL COMMENT '申购资金交收方式',
  `redeem_settle_type` char(1) DEFAULT NULL COMMENT '赎回资金交收方式',
  `need_nav_file` char(1) DEFAULT NULL COMMENT '是否需要行情文件',
  `need_param_file` char(1) DEFAULT NULL COMMENT '是否需要参数文件',
  `template_file` varchar(500) DEFAULT NULL COMMENT '模板文件',
  UNIQUE KEY `i_fund_distributor_relation` (`fund_code`,`distributor_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基金销售商关系';

/*Data for the table `fund_distributor_relation` */

/*Table structure for table `fund_fare_for_asset` */

DROP TABLE IF EXISTS `fund_fare_for_asset`;

CREATE TABLE `fund_fare_for_asset` (
  `fund_code` varchar(6) DEFAULT NULL COMMENT '基金代码',
  `distributor_code` varchar(9) DEFAULT NULL COMMENT '销售机构代码',
  `business_code` varchar(3) DEFAULT NULL COMMENT '业务代码',
  `target_fund_code` varchar(6) DEFAULT NULL COMMENT '对方基金代码',
  `share_class` char(1) DEFAULT NULL COMMENT '收费类别',
  `target_share_class` char(1) DEFAULT NULL COMMENT '对方基金收费类别',
  `min_days` decimal(4,0) DEFAULT NULL COMMENT '持有天数下限',
  `max_days` decimal(4,0) DEFAULT NULL COMMENT '持有天数上限',
  `asset_rate` decimal(5,4) DEFAULT NULL COMMENT '归资产比例',
  UNIQUE KEY `i_fund_fare_for_asset` (`fund_code`,`distributor_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='归基金资产比例';

/*Data for the table `fund_fare_for_asset` */

/*Table structure for table `fund_issue_info` */

DROP TABLE IF EXISTS `fund_issue_info`;

CREATE TABLE `fund_issue_info` (
  `fund_code` varchar(6) DEFAULT NULL COMMENT '基金代码',
  `ipo_price` decimal(11,8) DEFAULT NULL COMMENT '基金发行价格',
  `issue_type` char(1) DEFAULT NULL COMMENT '基金发行方式',
  `ipo_start_date` varchar(8) DEFAULT NULL COMMENT '基金募集起始日期',
  `ipo_end_date` varchar(8) DEFAULT NULL COMMENT '基金募集结束日期',
  `fund_establish_date` varchar(8) DEFAULT NULL COMMENT '基金成立日期',
  `fund_minsize` decimal(16,2) DEFAULT NULL COMMENT '基金最低募集金额',
  `fund_maxsize` decimal(16,2) DEFAULT NULL COMMENT '基金最高募集金额',
  `customer_min_amount` int(11) DEFAULT NULL COMMENT '最少开户数量',
  `real_subs_amount` decimal(16,2) DEFAULT NULL COMMENT '实际募集金额',
  `placement_mode` char(1) DEFAULT NULL COMMENT '认购配售方式',
  `interest_mode` char(1) DEFAULT NULL COMMENT '认购利息方式',
  UNIQUE KEY `i_fund_issue_info` (`fund_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基金发行信息';

/*Data for the table `fund_issue_info` */

/*Table structure for table `fund_liquidate` */

DROP TABLE IF EXISTS `fund_liquidate`;

CREATE TABLE `fund_liquidate` (
  `fund_code` varchar(6) DEFAULT NULL COMMENT '基金代码',
  `distributor_code` varchar(9) DEFAULT NULL COMMENT '销售人代码',
  `other_fund_code` varchar(6) DEFAULT NULL COMMENT '转入基金代码',
  `liquidate_sub` decimal(10,0) DEFAULT NULL COMMENT '认购清算天数',
  `liquidate_allot` decimal(10,0) DEFAULT NULL COMMENT '申购清算天数',
  `liquidate_redeem` decimal(10,0) DEFAULT NULL COMMENT '赎回清算天数',
  `liquidate_bonus` decimal(10,0) DEFAULT NULL COMMENT '分红清算天数',
  `liquidate_change` decimal(10,0) DEFAULT NULL COMMENT '转换出清算天数',
  `liquidate_change_in` decimal(10,0) DEFAULT NULL COMMENT '转换入清算天数',
  `liquidate_fail` decimal(10,0) DEFAULT NULL COMMENT '发行失败清算天数',
  `liquidate_end` decimal(10,0) DEFAULT NULL COMMENT '清盘清算天数',
  `liquidate_fare` decimal(10,0) DEFAULT NULL COMMENT '费用结算天数',
  `need_flag` char(1) DEFAULT NULL COMMENT '清算天数标志',
  UNIQUE KEY `i_fund_liquidate` (`fund_code`,`distributor_code`,`other_fund_code`,`need_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='清算天数信息';

/*Data for the table `fund_liquidate` */

/*Table structure for table `fund_precision` */

DROP TABLE IF EXISTS `fund_precision`;

CREATE TABLE `fund_precision` (
  `fund_code` varchar(6) DEFAULT NULL COMMENT '基金代码',
  `fare` char(1) DEFAULT NULL COMMENT '费用精度处理方式,0-四舍五入 1-截位 2-进位',
  `share` char(1) DEFAULT NULL COMMENT '份额精度处理方式,0-四舍五入 1-截位 2-进位',
  `money` char(1) DEFAULT NULL COMMENT '金额精度处理方式,0-四舍五入 1-截位 2-进位',
  `interest` char(1) DEFAULT NULL COMMENT '利息精度处理方式,0-四舍五入 1-截位 2-进位',
  `bonus` char(1) DEFAULT NULL COMMENT '分红精度处理方式,0-四舍五入 1-截位 2-进位',
  `fare_for_asset` char(1) DEFAULT NULL COMMENT '归资产精度处理方式,0-四舍五入 1-截位 2-进位',
  `income_rate` char(1) DEFAULT NULL COMMENT '收益率精度处理方式,0-四舍五入 1-截位 2-进位',
  UNIQUE KEY `i_fund_precision` (`fund_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='精度处理方式';

/*Data for the table `fund_precision` */

/*Table structure for table `fund_trade_info` */

DROP TABLE IF EXISTS `fund_trade_info`;

CREATE TABLE `fund_trade_info` (
  `fund_code` varchar(6) DEFAULT NULL COMMENT '基金代码',
  `confirm_days` decimal(2,0) DEFAULT NULL COMMENT 'T+N确认天数',
  `vast_redeem_ratio` decimal(9,8) DEFAULT NULL COMMENT '巨额赎回比例',
  `vast_redeem_delay` char(1) DEFAULT NULL COMMENT '巨额赎回顺延方式',
  `vast_redeem_mode` char(1) DEFAULT NULL COMMENT '巨额赎回处理方式',
  `exceed_buy_mode` char(1) DEFAULT NULL COMMENT '超额认申购处理方式',
  `def_dividend_method` char(1) DEFAULT NULL COMMENT '基金默认分红方式',
  `reinvest_date` decimal(10,0) DEFAULT NULL COMMENT '货币基金收益兑付日期',
  `buy_fee_calc_mode` char(1) DEFAULT NULL COMMENT '认申购费用计算方式',
  `redemption_sequence` char(1) DEFAULT NULL COMMENT '赎回明细处理方式',
  `unit_buy_mode` char(1) DEFAULT NULL COMMENT '级差的处理方式',
  `force_redemption_type` char(1) DEFAULT NULL COMMENT '强制赎回方式',
  `redeem_feeback_mode` char(1) DEFAULT NULL COMMENT '赎回费归基金资产方式',
  `closed_end_date` varchar(8) DEFAULT NULL COMMENT '封闭结束日期',
  `fund_end_date` varchar(8) DEFAULT NULL COMMENT '基金到期日期',
  `max_account` decimal(4,0) DEFAULT NULL COMMENT '最大账户数,0表示不限制',
  `nav_precision` decimal(2,0) DEFAULT NULL COMMENT '净值精度',
  `nav_notice_freq` char(1) DEFAULT NULL COMMENT '净值公布频率',
  `nav_notice_mode` char(1) DEFAULT NULL COMMENT '净值公布频率按月计算方式',
  `total_nav_from_file` char(1) DEFAULT NULL COMMENT '累计净值从文件读取',
  `remain_asset_deal` char(1) DEFAULT NULL COMMENT '赎回后资产低于最低保有金额',
  `min_asset_deal` char(1) DEFAULT NULL COMMENT '最低账面资产处理方式',
  UNIQUE KEY `i_fundt_rade_info` (`fund_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基金交易规则';

/*Data for the table `fund_trade_info` */

/*Table structure for table `fund_trade_limit` */

DROP TABLE IF EXISTS `fund_trade_limit`;

CREATE TABLE `fund_trade_limit` (
  `fund_code` varchar(6) DEFAULT NULL COMMENT '基金代码',
  `individual_or_institution` char(1) DEFAULT NULL COMMENT '个人/机构标志',
  `distributor_code` varchar(9) DEFAULT NULL COMMENT '销售人代码',
  `min_first_subs_amount` decimal(16,2) DEFAULT NULL COMMENT '首次认购最低金额',
  `min_app_subs_amount` decimal(16,2) DEFAULT NULL COMMENT '追加认购最低金额',
  `max_subs_amount` decimal(16,2) DEFAULT NULL COMMENT '最高认购金额',
  `unit_subs_amount` decimal(16,2) DEFAULT NULL COMMENT '认购级差金额',
  `min_first_bids_amount` decimal(16,2) DEFAULT NULL COMMENT '首次申购最低金额',
  `min_app_bids_amount` decimal(16,2) DEFAULT NULL COMMENT '追加申购最低金额',
  `max_purchase` decimal(16,2) DEFAULT NULL COMMENT '最大申购金额',
  `day_max_sum_buy` decimal(16,2) DEFAULT NULL COMMENT '当日最大购买金额',
  `unit_bids_amount` decimal(16,2) DEFAULT NULL COMMENT '申购级差金额',
  `min_redemption_vol` decimal(16,2) DEFAULT NULL COMMENT '最低赎回份额',
  `max_redeem` decimal(16,2) DEFAULT NULL COMMENT '最大赎回份额',
  `day_max_sum_redeem` decimal(16,2) DEFAULT NULL COMMENT '当日累计赎回最大份额',
  `min_inter_convert_vol` decimal(16,2) DEFAULT NULL COMMENT '最低基金转换份数',
  `ration_purchase_min_amount` decimal(16,2) DEFAULT NULL COMMENT '定期定额最低金额',
  `ration_purchase_max_amount` decimal(16,2) DEFAULT NULL COMMENT '定期定额最高金额',
  `ration_redemption_min_vol` decimal(16,2) DEFAULT NULL COMMENT '定期定额赎回最低份额',
  `min_account_balance` decimal(16,2) DEFAULT NULL COMMENT '基金最低持有份额',
  UNIQUE KEY `i_fund_trade_limit` (`fund_code`,`distributor_code`,`individual_or_institution`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基金交易限制信息';

/*Data for the table `fund_trade_limit` */

/*Table structure for table `interest_cal_rule` */

DROP TABLE IF EXISTS `interest_cal_rule`;

CREATE TABLE `interest_cal_rule` (
  `fund_code` varchar(6) DEFAULT NULL COMMENT '基金代码',
  `distributor_Code` varchar(9) DEFAULT NULL COMMENT '销售商代码,默认***',
  `interest_type` char(1) DEFAULT NULL COMMENT '利率类型,参考数据字典2005',
  `start_day` decimal(2,0) DEFAULT NULL COMMENT '计息起始天数',
  `end_date` varchar(8) DEFAULT NULL COMMENT '计息截止日期',
  UNIQUE KEY `i_interest_cal_rule` (`fund_code`,`distributor_Code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='利息计算规则';

/*Data for the table `interest_cal_rule` */

/*Table structure for table `interest_rate` */

DROP TABLE IF EXISTS `interest_rate`;

CREATE TABLE `interest_rate` (
  `currency_type` varchar(3) DEFAULT NULL COMMENT '币种',
  `interest_type` char(1) DEFAULT NULL COMMENT '利率类型,参考数据字典2005',
  `rate` decimal(9,8) DEFAULT NULL COMMENT '年利率',
  `adjust_date` varchar(8) DEFAULT NULL COMMENT '调整日期',
  UNIQUE KEY `i_interest_rate` (`currency_type`,`adjust_date`,`interest_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='利率';

/*Data for the table `interest_rate` */

/*Table structure for table `manager_info` */

DROP TABLE IF EXISTS `manager_info`;

CREATE TABLE `manager_info` (
  `manager_code` varchar(6) DEFAULT NULL COMMENT '管理人代码',
  `manager_name` varchar(64) DEFAULT NULL COMMENT '管理人名称',
  `manager_type` varchar(1) DEFAULT NULL COMMENT '管理人类型',
  `full_name` varchar(128) DEFAULT NULL COMMENT '管理人全称',
  `address` varchar(128) DEFAULT NULL COMMENT '通讯地址',
  `contract` varchar(64) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(64) DEFAULT NULL COMMENT '联系电话',
  `fax` varchar(36) DEFAULT NULL COMMENT '传真号码',
  `ta_code` varchar(6) DEFAULT NULL COMMENT 'TA代码',
  UNIQUE KEY `i_manager_info` (`manager_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理人信息';

/*Data for the table `manager_info` */

/*Table structure for table `promotional_activity` */

DROP TABLE IF EXISTS `promotional_activity`;

CREATE TABLE `promotional_activity` (
  `activity_name` varchar(100) DEFAULT NULL COMMENT '活动名称',
  `fund_code` varchar(6) DEFAULT NULL COMMENT '基金代码',
  `share_class` char(1) DEFAULT NULL COMMENT '份额类别',
  `distributor_Code` varchar(9) DEFAULT NULL COMMENT '销售商代码',
  `start_date` varchar(8) DEFAULT NULL COMMENT '活动开始日期',
  `end_date` varchar(8) DEFAULT NULL COMMENT '活动结束日期',
  `amount_lower_limit` decimal(16,2) DEFAULT NULL COMMENT '金额下限',
  `amount_upper_limit` decimal(16,2) DEFAULT NULL COMMENT '金额上限',
  `discounts` decimal(9,8) DEFAULT NULL COMMENT '促销优惠折扣',
  UNIQUE KEY `i_promotional_activities` (`fund_code`,`share_class`,`distributor_Code`,`start_date`,`end_date`,`amount_lower_limit`,`amount_upper_limit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='促销优惠';

/*Data for the table `promotional_activity` */

/*Table structure for table `sys_dict` */

DROP TABLE IF EXISTS `sys_dict`;

CREATE TABLE `sys_dict` (
  `key_no` varchar(10) DEFAULT NULL COMMENT '字典词条',
  `key_value` varchar(200) DEFAULT NULL COMMENT '词条键值',
  `key_caption` varchar(200) DEFAULT NULL COMMENT '词条解释',
  `key_type` char(1) DEFAULT NULL COMMENT '词条类型,0固定值 1动态SQL,定义成(tablename,condition,keycolumn,valuecolumn)',
  UNIQUE KEY `i_dictionary` (`key_no`,`key_value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典';

/*Data for the table `sys_dict` */

/*Table structure for table `sys_param` */

DROP TABLE IF EXISTS `sys_param`;

CREATE TABLE `sys_param` (
  `param_code` varchar(40) DEFAULT NULL COMMENT '参数名',
  `param_name` varchar(60) DEFAULT NULL COMMENT '参数说明',
  `param_value` varchar(100) DEFAULT NULL COMMENT '参数值',
  `param_range` varchar(200) DEFAULT NULL COMMENT '参数值域',
  `param_type` char(1) DEFAULT NULL COMMENT '参数显示类型',
  `param_modify` char(1) DEFAULT NULL COMMENT '参数允许修改(0不可见;1可见可修改;2可见不可改)',
  `param_class` varchar(40) DEFAULT NULL COMMENT '参数大类',
  `param_subclass` varchar(40) DEFAULT NULL COMMENT '参数子类',
  `crypt_flag` char(1) DEFAULT NULL COMMENT '参数值加密',
  `serial_no` varchar(4) DEFAULT NULL COMMENT '参数序号',
  UNIQUE KEY `i_sys_param` (`param_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统参数表';

/*Data for the table `sys_param` */

/*Table structure for table `sys_privilege` */

DROP TABLE IF EXISTS `sys_privilege`;

CREATE TABLE `sys_privilege` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(50) NOT NULL COMMENT '权限代码',
  `value` varchar(100) DEFAULT NULL COMMENT '权限值，比如url',
  `no_limit_access` int(11) DEFAULT '0' COMMENT '所有用户包括未登录用户是否可访问',
  `need_login` int(11) DEFAULT '0' COMMENT 'no_limit_access为1时, 是否需要登录',
  `comments` varchar(200) DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_sys_privilege_code` (`code`),
  UNIQUE KEY `i_sys_privilege_value` (`value`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `sys_privilege` */

insert  into `sys_privilege`(`id`,`code`,`value`,`no_limit_access`,`need_login`,`comments`) values (1,'goto_index_page','/goto_index_page',1,1,'打开主页'),(2,'goto_login_page','/goto_login_page',1,0,'打开登陆页面'),(3,'login','/login',1,0,'用户登陆'),(4,'logout','/logout',1,0,'用户注销'),(5,'user_goto_main_page','/user/goto_main_page',0,0,'打开用户管理页面'),(6,'user_goto_add_page','/user/goto_add_page',0,0,'打开新增用户页面'),(7,'user_add','/user/add',0,0,'新增用户'),(8,'user_goto_update_page','/user/goto_update_page',0,0,'打开修改用户页面'),(9,'user_update','/user/update',0,0,'修改用户信息'),(10,'user_delete','/user/delete',0,0,'删除用户'),(11,'user_goto_update_pwd_page','/user/goto_update_pwd_page',1,1,'打开修改密码页面'),(12,'user_update_pwd','/user/update_pwd',1,1,'修改密码'),(13,'fund_goto_main_page','/fund/goto_main_page',0,0,'打开基金管理页面'),(14,'fund_goto_add_page','/fund/goto_add_page',0,0,'打开新增基金页面'),(15,'fund_add','/fund/add',0,0,'新增基金'),(16,'fund_goto_update_page','/fund/goto_update_page',0,0,'打开修改基金页面'),(17,'fund_update','/fund/update',0,0,'修改基金信息'),(18,'fund_delete','/fund/delete',0,0,'删除基金');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) DEFAULT NULL,
  `label` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`code`,`label`) values (1,'ADMIN','管理员'),(2,'OPERATOR','操作员');

/*Table structure for table `sys_role_privilege` */

DROP TABLE IF EXISTS `sys_role_privilege`;

CREATE TABLE `sys_role_privilege` (
  `role_code` varchar(20) NOT NULL,
  `privilege_code` varchar(20) NOT NULL,
  PRIMARY KEY (`role_code`,`privilege_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_privilege` */

insert  into `sys_role_privilege`(`role_code`,`privilege_code`) values ('ADMIN','login');

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `ver_nbr` int(11) NOT NULL DEFAULT '1',
  `name` varchar(50) NOT NULL,
  `pwd` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `full_name` varchar(50) DEFAULT NULL,
  `state_id` int(11) NOT NULL,
  `add_user_id` int(11) NOT NULL,
  `add_time` datetime NOT NULL,
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_user_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`ver_nbr`,`name`,`pwd`,`phone`,`full_name`,`state_id`,`add_user_id`,`add_time`,`remark`) values (1,1,'admin','aa',NULL,'admin',1,0,'2017-07-19 18:07:26',NULL);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` varchar(20) NOT NULL,
  `role_id` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`user_id`,`role_id`) values ('1','1');

/*Table structure for table `tail_ratio` */

DROP TABLE IF EXISTS `tail_ratio`;

CREATE TABLE `tail_ratio` (
  `fund_code` varchar(6) DEFAULT NULL COMMENT '基金代码',
  `distributor_code` varchar(9) DEFAULT NULL COMMENT '销售人代码',
  `flag` char(1) DEFAULT NULL COMMENT '销售人代码',
  `begin_date` varchar(10) DEFAULT NULL COMMENT '启用日期',
  `hold_min` decimal(20,6) DEFAULT NULL COMMENT '区间最小',
  `hold_max` decimal(20,6) DEFAULT NULL COMMENT '区间最大',
  `ratio` decimal(8,7) DEFAULT NULL COMMENT '尾随佣金比例(%)',
  `calculate_type` char(1) DEFAULT NULL COMMENT '尾随佣金计算方式',
  `min_tail_fare` decimal(16,2) DEFAULT NULL COMMENT '尾随佣金起付金额',
  `year_days` decimal(4,0) DEFAULT NULL COMMENT '尾随佣金年天数',
  `tail_segment` char(1) DEFAULT NULL COMMENT '是否累进计算',
  `update_flag` char(1) DEFAULT NULL COMMENT '是否更新标志',
  `agency_fee_ratio` decimal(8,7) DEFAULT NULL,
  UNIQUE KEY `i_tail_ratio` (`fund_code`,`distributor_code`,`flag`,`begin_date`,`hold_min`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='尾随佣金设置';

/*Data for the table `tail_ratio` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
