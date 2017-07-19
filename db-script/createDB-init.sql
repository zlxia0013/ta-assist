/* 建库时，指定COLLATE为utf8_bin，主要为了表里存储的内容区分大小写 */

drop database if exists ta ;

create database if not exists ta 
default charset utf8 collate utf8_bin;

use ta;

 

drop table if exists  dictionary;
create table dictionary(
  key_no varchar(10) comment '字典词条',
  key_value varchar(200) comment '词条键值',
  key_caption varchar(200) comment '词条解释',
  key_type char(1) comment '词条类型,0固定值 1动态SQL,定义成(tablename,condition,keycolumn,valuecolumn)'
) engine=InnoDB comment '数据字典';
create unique index i_dictionary on dictionary(key_no,key_value);

drop table if exists  fund_base_info;
create table fund_base_info(
  fund_code varchar(6) comment '基金代码',
  fund_name varchar(40) comment '基金名称',
  fund_english_name varchar(40) comment '基金英文名',
  fund_class char(1) comment '基金性质',
  fund_type char(1) comment '基金类型',
  share_class char(1) comment '份额分类',
  currency_type char(3) comment '结算币种',
  fund_manager_code varchar(6) comment '管理人代码',
  custodian_code varchar(9) comment '托管人代码',
  fa_code char(9) comment '估值核算机构代码',
  manager_fee_rate numeric(5,4) comment '管理费率',
  specification varchar(100) comment '基金描述',
  fund_status char(1) comment '基金状态',
  is_guaranteed_fund char(1) comment '是否保本基金',
  is_structured_fund char(1) comment '是否结构化分级基金',
  is_multi_fund char(1) comment '是否多基金分级基金',
  is_qdii_fund char(1) comment '是否QDII基金',
  is_etf_fund char(1) comment '是否ETF指数基金',
  is_lof char(1) comment '是否LOF基金',
  ta_code varchar(6) comment 'TA代码',
  is_sync varchar(1) comment '是否已同步到TA',
  ref_fund varchar(6) comment '同步参考基金代码'
)engine=InnoDB comment '基金基本信息';
create unique index ifundbaseinfo  on fundbaseinfo (fundcode);

drop table if exists  distributor_info;
create table distributor_info(
  distributor_code      varchar(9)  comment '销售人代码',
  distributor_name      varchar(40) comment '销售人名称',
  distributor_type      char(1)     comment '销售人类型',
  address              varchar(128) comment '总部地址',
  zip_code              varchar(6)  comment '邮政编码',
  contract             varchar(64) comment '销售商联系人',
  phone                varchar(64) comment '销售商联系电话',
  fax                  varchar(36) comment '销售商传真号码',
  email                varchar(36) comment '销售商电子邮件',
  distributor_status    char(1)     comment '销售人状态',
  is_sync               varchar(1)  comment '是否已同步到TA',
  ref_code              varchar(9)  comment '参考销售人代码'
) engine=InnoDB comment '销售人信息';
create unique index i_distributor_code on distributor_info (distributor_code);

drop table if exists distributortradeinfo;
create table distributortradeinfo
(
    distributorcode varchar(9) comment '销售商代码',
    concentratereg char(1) comment '是否大集中',
    overonetransactionacco char(1) comment '是否支持多交易账户',
    onesteptransfer char(1) comment '是否支持一步转托管',
    fundcodechangemode char(1) comment '基金转换确认方式',
    suberrorsendtype char(1) comment '认购失败数据下发方式',
    needparamfile char(1) comment '是否需要参数文件'
)engine=InnoDB comment '销售商交易规则';
create unique index idistributortradeinfo on distributortradeinfo(distributorcode);

drop table if exists custodianbank;
create table custodianbank
(
    code varchar(9) comment '托管行代码',
    name varchar(100) comment '托管行名称',
    interfacever varchar(10) comment '会计接口版本',
    filenameformat char(1) comment '会计接口文件样式',
    fax varchar(36) comment '传真号码'
)engine=InnoDB comment '托管行信息';
create unique index icustodianbank on custodianbank(code);

drop table if exists fainfo;
create table fainfo
(
    facode varchar(9) comment '估值机构代码',
    name varchar(100) comment '估值机构名称',
    interfacever varchar(10) comment '估值接口版本',
    filenameformat char(1) comment '估值接口文件样式',
    fax varchar(36) comment '传真号码'
) engine=InnoDB comment '估值机构信息';
create unique index ifainfo on fainfo(facode);

drop table if exists funddistributorrelation;
create table funddistributorrelation(
    fundcode varchar(6) comment '基金代码',
    distributorcode varchar(9) comment '销售商代码',
    signupdate varchar(10) comment '签约起始日期',
    subpaytype char(1) comment '认购款到账方式',
    pursettletype char(1) comment '申购资金交收方式',
    redeemsettletype char(1) comment '赎回资金交收方式',
    neednavfile char(1) comment '是否需要行情文件',
    needparamfile char(1) comment '是否需要参数文件',
    templatefile varchar(500) comment '模板文件'
) engine=InnoDB comment '基金销售商关系';
create unique index ifunddistributorrelation on funddistributorrelation(fundcode,distributorcode);

drop table if exists  fundcodechange;
create table fundcodechange(
    fundcode varchar(6) comment '基金代码',
    shareclass varchar(1) comment '份额类别',
    outerfundcode varchar(6) comment '外部基金代码',
    outerfundname varchar(100) comment '外部基金名称'
)engine=InnoDB comment '基金内外部代码对照表';
create unique index ifundcodechange on fundcodechange(fundcode,shareclass);

drop table if exists  fundissueinfo;
create table fundissueinfo(
  fundcode varchar(6) comment '基金代码',
  ipoprice numeric(11,8) comment '基金发行价格',
  issuetype char(1) comment '基金发行方式',
  ipostartdate varchar(8) comment '基金募集起始日期',
  ipoenddate varchar(8) comment '基金募集结束日期',
  fundestablishdate varchar(8) comment '基金成立日期',
  fundminsize numeric(16,2) comment '基金最低募集金额',
  fundmaxsize numeric(16,2) comment '基金最高募集金额',
  customerminamount integer comment '最少开户数量',
  realsubsamount numeric(16,2) comment '实际募集金额',
  placementmode char(1) comment '认购配售方式',
  interestmode char(1) comment '认购利息方式'
) engine=InnoDB comment '基金发行信息';
create unique index ifundissueinfo on fundissueinfo (fundcode);

drop table if exists  fundprecision;
create table fundprecision(
  fundcode varchar(6) comment '基金代码',
  fare char(1) comment '费用精度处理方式,0-四舍五入 1-截位 2-进位',
  share char(1) comment '份额精度处理方式,0-四舍五入 1-截位 2-进位',
  money char(1) comment '金额精度处理方式,0-四舍五入 1-截位 2-进位',
  interest char(1) comment '利息精度处理方式,0-四舍五入 1-截位 2-进位',
  bonus char(1) comment '分红精度处理方式,0-四舍五入 1-截位 2-进位',
  fareforasset char(1) comment '归资产精度处理方式,0-四舍五入 1-截位 2-进位',
  incomerate char(1) comment '收益率精度处理方式,0-四舍五入 1-截位 2-进位'
)engine=InnoDB comment '精度处理方式';
create unique index ifundprecision on fundprecision(fundcode);

drop table if exists  fundfareforasset;
create table fundfareforasset(
  fundcode varchar(6) comment '基金代码',
  distributorcode varchar(9) comment '销售机构代码',
  businesscode varchar(3) comment '业务代码',
  targetfundcode varchar(6) comment '对方基金代码',
  shareclass char(1) comment '收费类别',
  targetshareclass char(1) comment '对方基金收费类别',
  mindays numeric(4) comment '持有天数下限',
  maxdays numeric(4) comment '持有天数上限',
  assetrate numeric(5,4) comment '归资产比例'
)engine=InnoDB comment '归基金资产比例';
create unique index ifundfareforasset on fundfareforasset(fundcode,distributorcode);

drop table if exists  fundtradeinfo;
create table fundtradeinfo(
  fundcode varchar(6) comment '基金代码',
  confirmdays numeric(2) comment 'T+N确认天数',
  vastredeemratio numeric(9,8) comment '巨额赎回比例',
  vastredeemdelay char(1) comment '巨额赎回顺延方式',
  vastredeemmode char(1) comment '巨额赎回处理方式',
  exceedbuymode char(1) comment '超额认申购处理方式',
  defdividendmethod char(1) comment '基金默认分红方式',
  reinvestdate numeric(10) comment '货币基金收益兑付日期',
  buyfeecalcmode char(1) comment '认申购费用计算方式',
  redemptionsequence char(1) comment '赎回明细处理方式',
  unitbuymode char(1) comment '级差的处理方式',
  forceredemptiontype chaR(1) comment '强制赎回方式',
  redeemfeebackmode     cHAR(1)      comment '赎回费归基金资产方式',
  closedenddate varchar(8) comment '封闭结束日期',
  fundenddate varchar(8) comment '基金到期日期',
  maxaccount numeric(4) comment '最大账户数,0表示不限制',
  navprecision numeric(2) comment '净值精度',
  navnoticefreq char(1) comment '净值公布频率',
  navnoticemode char(1) comment '净值公布频率按月计算方式',
  totalnavfromfile char(1) comment '累计净值从文件读取',
  remainassetdeal char(1) comment '赎回后资产低于最低保有金额',
  minassetdeal char(1) comment '最低账面资产处理方式'
) engine=InnoDB comment '基金交易规则';
create unique index ifundtradeinfo on fundtradeinfo(fundcode);

drop table if exists  fundtradelimit;
create table fundtradelimit(
  fundcode varchar(6) comment '基金代码',
  individualorinstitution cHAR(1) comment '个人/机构标志',
  distributorcode varchar(9) comment '销售人代码',
  minfirstsubsamount numeric(16,2) comment '首次认购最低金额',
  minappsubsamount numeric(16,2) comment '追加认购最低金额',
  maxsubsamount numeric(16,2) comment '最高认购金额',
  unitsubsamount numeric(16,2) comment '认购级差金额',
  minfirstbidsamount numeric(16,2) comment '首次申购最低金额',
  minappbidsamount numeric(16,2) comment '追加申购最低金额',
  maxpurchase numeric(16,2) comment '最大申购金额',
  daymaxsumbuy numeric(16,2) comment '当日最大购买金额',
  unitbidsamount numeric(16,2) comment '申购级差金额',
  minredemptionvol numeric(16,2) comment '最低赎回份额',
  maxredeem numeric(16,2) comment '最大赎回份额',
  daymaxsumredeem numeric(16,2) comment '当日累计赎回最大份额',
  mininterconvertvol numeric(16,2) comment '最低基金转换份数',
  rationpurchaseminamount numeric(16,2) comment '定期定额最低金额',
  rationpurchasemaxamount numeric(16,2) comment '定期定额最高金额',
  rationredemptionminvol numeric(16,2) comment '定期定额赎回最低份额',
  minaccountbalance numeric(16,2) comment '基金最低持有份额'
)engine=InnoDB comment '基金交易限制信息';
create unique index ifundtradelimit on fundtradelimit(fundcode, distributorcode, individualorinstitution);

drop table if exists farerate;
create table farerate(
  fundcode varchar(6) comment '基金代码',
  shareclass char(1) comment '份额类别',
  codeoftargetfund varchar(6) comment '转换时目标代码',
  targetsharetype char(1) comment '转换时目标份额类别',
  businesscode varchar(3) comment '业务类型,参考数据字典2003',
  faretype varchar(3) comment '费用类型,参考数据字典2004',
  distributorcode varchar(9) comment '销售商代码(费率设置时不显示，默认***)',
  capitaltype varchar(3) comment '资金渠道(费率设置时不显示，默认***)',
  individualorinstitution char(1) comment '客户类型(费率设置时不显示，默认*)',
  vollowerlimit numeric(16,2) comment '份额下限',
  volupperlimit numeric(16,2) comment '份额上限',
  amountlowerlimit numeric(16,2) comment '金额下限',
  amountupperlimit numeric(16,2) comment '金额上限',
  dayslowerlimit numeric(16,2) comment '天数下限',
  daysupperlimit numeric(16,2) comment '天数上限',
  feerateflag char(1) comment '费率类型,0-绝对费率,1-相对费率(分成比例)(优惠设置时不现实，默认0)',
  ratefee numeric(9,8) comment '费率,费率类型为0时表示实际计算费率如0.015,为1时表示分成比例如0.4,只支持扣除归基金资产后的分成比例(优惠设置时不现实，默认0)',
  constantfee numeric(16,2) comment '固定费用(优惠设置时不现实，默认0)',
  leastbenefitagio numeric(9,8) comment '至少优惠折扣,即费率上至少会乘以此费率(费率设置时不显示，默认1)',
  lowestbenefitagio numeric(9,8) comment '最优惠折扣(费率设置时不显示，默认1)',
  lowestfarerate numeric(9,8) comment '乘以折扣之后，不能低于此费率(费率设置时不显示，默认0,不限制)'
 )engine=InnoDB comment '基金费率表(其中销售商代码为***,表示基础费率,销售商代码有值，表示费用优惠';
create unique index ifarerate on farerate(fundcode,shareclass,codeoftargetfund,targetsharetype,businesscode,faretype,distributorcode,capitaltype,individualorinstitution,vollowerlimit,volupperlimit,amountlowerlimit,amountupperlimit,dayslowerlimit,daysupperlimit);

drop table if exists promotionalactivities;
create table promotionalactivities(
  activitiesName varchar(100) comment '活动名称',
  fundcode varchar(6) comment '基金代码',
  shareclass char(1) comment '份额类别',
  distributorCode varchar(9) comment '销售商代码',
  startdate varchar(8) comment '活动开始日期',
  enddate varchar(8) comment '活动结束日期',
  amountlowerlimit numeric(16,2) comment '金额下限',
  amountupperlimit numeric(16,2) comment '金额上限',
  discounts numeric(9,8) comment '促销优惠折扣'  
)engine=InnoDB comment '促销优惠';
create unique index ipromotionalactivities on promotionalactivities(fundcode,shareclass,distributorcode,startdate,enddate,amountlowerlimit,amountupperlimit);

drop table if exists managerinfo;
create table managerinfo
(
  managercode varchar(6)  comment '管理人代码',
  managername varchar(64) comment '管理人名称',
  managertype varchar(1)  comment '管理人类型',
  fullname    varchar(128) comment '管理人全称',
  address     varchar(128) comment '通讯地址',
  contract    varchar(64)  comment '联系人',
  phone       varchar(64)  comment '联系电话',
  fax         varchar(36)  comment '传真号码',
  tacode      varchar(6)   comment 'TA代码'
)engine=InnoDB comment '管理人信息';
create unique index imanagerinfo on managerinfo (managercode);

drop table if exists interestrate;
create table interestrate(
  currencytype varchar(3) comment '币种',
  interesttype char(1) comment '利率类型,参考数据字典2005',
  rate numeric(9,8) comment '年利率',
  adjustdate varchar(8) comment '调整日期'
) engine=InnoDB comment '利率';
create unique index iinterestrate on interestrate(currencytype,adjustdate,interesttype);

drop table if exists interestcalrule;
create table interestcalrule(
  fundcode varchar(6) comment '基金代码',
  DistributorCode varchar(9) comment '销售商代码,默认***',
  interesttype char(1) comment '利率类型,参考数据字典2005',
  startday numeric(2) comment '计息起始天数',
  enddate varchar(8) comment '计息截止日期'
) engine=InnoDB comment '利息计算规则';
create unique index iinterestcalrule on interestcalrule(fundcode,distributorcode);

drop table if exists allowfundchange;
create table allowfundchange(
  fundcode varchar(6) comment '基金代码',
  shareclass char(1) comment '份额类别',
  codeoftargetfund varchar(6) comment '转换时目标代码',
  targetsharetype char(1) comment '转换时目标份额类别',
  distributorcode varchar(9) comment '销售商代码(费率设置时不显示，默认***)'
)engine=InnoDB comment '基金转换关系';
create unique index iallowfundchange on allowfundchange(fundcode,shareclass,codeoftargetfund,targetsharetype,distributorcode);

drop table if exists bonusschema;
create table bonus_schema(
  fund_code varchar(6) comment '基金代码',
  share_class char(1) comment '份额类别',
  registration_date varchar(8) comment '权益登记日期',
  divident_date varchar(8) comment '分红日/发放日',
  xr_date varchar(8) comment '除权日',
  dividend_per_unit numeric(16,2) comment '单位基金分红金额（含税）',
  draw_bonus_unit numeric(10,0) comment '分红单位'
) engine=InnoDB comment '分红方案';
create unique index i_bonus_schema on bonus_schema(fund_code,share_class,registration_date);

drop table if exists fundliquidate;
create table fundliquidate
(
  fundcode            varchar(6)  comment '基金代码',
  distributorcode     varchar(9)  comment '销售人代码',
  otherfundcode       varchar(6)  comment '转入基金代码',
  liquidatesub        numeric(10, 0) comment '认购清算天数',
  liquidateallot      numeric(10, 0) comment '申购清算天数',
  liquidateredeem     numeric(10, 0) comment '赎回清算天数',
  liquidatebonus      numeric(10, 0) comment '分红清算天数',
  liquidatechange     numeric(10, 0) comment '转换出清算天数',
  liquidatechangein   numeric(10, 0) comment '转换入清算天数',
  liquidatefail       numeric(10, 0) comment '发行失败清算天数',
  liquidateend        numeric(10, 0) comment '清盘清算天数',
  liquidatefare       numeric(10, 0) comment '费用结算天数',
  needflag            char(1)        comment '清算天数标志' /*0-托管行清算天数 1-销售商清算天数 2-转换清算天数 */
) engine=InnoDB  comment '清算天数信息';
create unique index ifundliquidate on fundliquidate (fundcode,distributorcode,otherfundcode, needflag);

drop table if exists farebelong;
create table farebelong
(
  fundcode            varchar(6)  comment '基金代码',
  businesscode        varchar(3)  comment '业务代码',
  distributorcode     varchar(9)  comment '销售人代码',
  /* 0-交易费 3-后收手续费 5-违约金 6-业绩报酬 */
  faretype            char(20)     comment '费用类型',
  otherfundcode       varchar(6)  comment '对方基金代码',
  /* 1-总费用比例 2-扣除归基金资产后 */
  belongtype          char(1)     comment '费用分成方式',
  distributorratio    numeric(9, 8)  comment '归销售商比例(%)',
  registratio         numeric(9, 8)  comment '归管理人比例(%)'
) engine=InnoDB  comment '费用分成设置';
create unique index ifarebelong on farebelong (fundcode, businesscode, distributorcode, faretype, otherfundcode);

drop table if exists tailratio;
create table tailratio
(
  fundcode            varchar(6)    comment '基金代码',
  distributorcode     varchar(9)    comment '销售人代码',
  flag                char(1)       comment '销售人代码',
  begindate           varchar(10)    comment '启用日期',
  holdmin             numeric(20,6) comment '区间最小',
  holdmax             numeric(20,6) comment '区间最大',
  ratio               numeric(8,7)  comment '尾随佣金比例(%)',
  calculatetype       char(1)       comment '尾随佣金计算方式',
  mintailfare         numeric(16,2) comment '尾随佣金起付金额',
  yeardays            numeric(4)    comment '尾随佣金年天数',
  tailsegment         char(1)       comment '是否累进计算',
  updateflag          char(1)       comment '是否更新标志',
  agencyfeeratio      numeric(8,7)
) engine=InnoDB  comment '尾随佣金设置';
create unique index itailratio on tailratio(fundcode, distributorcode, flag, begindate, holdmin);
