package cn.iocoder.yudao.module.trade.dal.mysql.brokerage.record;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.trade.controller.admin.brokerage.record.vo.TradeBrokerageRecordPageReqVO;
import cn.iocoder.yudao.module.trade.dal.dataobject.brokerage.record.TradeBrokerageRecordDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 佣金记录 Mapper
 *
 * @author owen
 */
@Mapper
public interface TradeBrokerageRecordMapper extends BaseMapperX<TradeBrokerageRecordDO> {

    default PageResult<TradeBrokerageRecordDO> selectPage(TradeBrokerageRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TradeBrokerageRecordDO>()
                .eqIfPresent(TradeBrokerageRecordDO::getUserId, reqVO.getUserId())
                .eqIfPresent(TradeBrokerageRecordDO::getBizType, reqVO.getBizType())
                .eqIfPresent(TradeBrokerageRecordDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(TradeBrokerageRecordDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TradeBrokerageRecordDO::getId));
    }

    default List<TradeBrokerageRecordDO> selectListByStatusAndUnfreezeTimeLt(Integer status, LocalDateTime unfreezeTime) {
        return selectList(new LambdaQueryWrapper<TradeBrokerageRecordDO>()
                .eq(TradeBrokerageRecordDO::getStatus, status)
                .lt(TradeBrokerageRecordDO::getUnfreezeTime, unfreezeTime));
    }

    default int updateByIdAndStatus(Integer id, Integer status, TradeBrokerageRecordDO updateObj) {
        return update(updateObj, new LambdaQueryWrapper<TradeBrokerageRecordDO>()
                .eq(TradeBrokerageRecordDO::getId, id)
                .eq(TradeBrokerageRecordDO::getStatus, status));
    }

    default TradeBrokerageRecordDO selectByUserIdAndBizTypeAndBizId(Integer bizType, String bizId) {
        return selectOne(TradeBrokerageRecordDO::getBizType, bizType,
                TradeBrokerageRecordDO::getBizId, bizId);
    }
}