package com.my.blog.website.service;

import com.github.pagehelper.PageInfo;
import com.my.blog.website.modal.Vo.CertificateVo;
import com.my.blog.website.modal.Vo.CertificateVoExample;
import com.my.blog.website.modal.Vo.ContentVo;
import com.my.blog.website.modal.Vo.ContentVoExample;
/**
 * Created by Administrator on 2017/3/13 013.
 */
public interface ICertificateService {


    /**
     * 发布文章
     * @param contents
     */
    void publish(CertificateVo contents);

    /**
     *查询文章返回多条数据
     * @param p 当前页
     * @param limit 每页条数
     * @return ContentVo
     */
    PageInfo<CertificateVo> getContents(Integer p, Integer limit);


    /**
     * 根据id或slug获取文章
     *
     * @param id id
     * @return ContentVo
     */
    CertificateVo getContents(String id);

    /**
     * 根据主键更新
     * @param contentVo contentVo
     */
    void updateContentByCid(CertificateVo contentVo);


    /**
     * 查询分类/标签下的文章归档
     * @param mid mid
     * @param page page
     * @param limit limit
     * @return ContentVo
     */
    PageInfo<CertificateVo> getArticles(Integer mid, int page, int limit);

    /**
     * 搜索、分页
     * @param keyword keyword
     * @param page page
     * @param limit limit
     * @return ContentVo
     */
    PageInfo<CertificateVo> getArticles(String keyword, Integer page, Integer limit);


    /**
     * @param certificateVoExample
     * @param page
     * @param limit
     * @return
     */
    PageInfo<CertificateVo> getArticlesWithpage(CertificateVoExample certificateVoExample, Integer page, Integer limit);
    /**
     * 根据文章id删除
     * @param cid
     */
    void deleteByCid(Integer cid);

    /**
     * 编辑文章
     * @param contents
     */
    void updateArticle(CertificateVo contents);


    /**
     * 更新原有文章的category
     * @param ordinal
     * @param newCatefory
     */
    void updateCategory(String ordinal, String newCatefory);
}
