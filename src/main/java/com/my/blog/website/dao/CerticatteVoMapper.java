package com.my.blog.website.dao;

import com.my.blog.website.modal.Bo.ArchiveBo;
import com.my.blog.website.modal.Vo.CertificateVo;
import com.my.blog.website.modal.Vo.CertificateVoExample;
import com.my.blog.website.modal.Vo.ContentVo;
import com.my.blog.website.modal.Vo.ContentVoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CerticatteVoMapper {
    long countByExample(CertificateVoExample example);

    int deleteByExample(CertificateVoExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(CertificateVo record);

    int insertSelective(CertificateVo record);


    List<CertificateVo> selectByExample(CertificateVoExample example);

    CertificateVo selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") CertificateVo record, @Param("example") CertificateVoExample example);

    int updateByExampleWithBLOBs(@Param("record") CertificateVo record, @Param("example") CertificateVoExample example);

    int updateByExample(@Param("record") ContentVo record, @Param("example") CertificateVoExample example);

    int updateByPrimaryKeySelective(CertificateVo record);

    int updateByPrimaryKeyWithBLOBs(CertificateVo record);

    int updateByPrimaryKey(CertificateVo record);

    List<ArchiveBo> findReturnArchiveBo();

    List<CertificateVo> findByCatalog(Integer mid);
}