package com.my.blog.website.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.my.blog.website.constant.WebConst;
import com.my.blog.website.dao.CerticatteVoMapper;
import com.my.blog.website.dao.ContentVoMapper;
import com.my.blog.website.dao.MetaVoMapper;
import com.my.blog.website.dto.Types;
import com.my.blog.website.exception.TipException;
import com.my.blog.website.modal.Vo.CertificateVo;
import com.my.blog.website.modal.Vo.CertificateVoExample;
import com.my.blog.website.modal.Vo.ContentVo;
import com.my.blog.website.modal.Vo.ContentVoExample;
import com.my.blog.website.service.ICertificateService;
import com.my.blog.website.service.IContentService;
import com.my.blog.website.service.IMetaService;
import com.my.blog.website.service.IRelationshipService;
import com.my.blog.website.utils.DateKit;
import com.my.blog.website.utils.TaleUtils;
import com.my.blog.website.utils.Tools;
import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/3/13 013.
 */
@Service
public class CertificatteServiceImpl implements ICertificateService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CertificatteServiceImpl.class);

    @Resource
    private CerticatteVoMapper certicatteVoMapper;

    @Resource
    private MetaVoMapper metaDao;

    @Resource
    private IRelationshipService relationshipService;

    @Resource
    private IMetaService metasService;

    @Override
    public void publish(CertificateVo contents) {
        if (null == contents) {
            throw new TipException("文章对象为空");
        }
        if (StringUtils.isBlank(contents.getTitle())) {
            throw new TipException("文章标题不能为空");
        }

        int titleLength = contents.getTitle().length();
        if (titleLength > WebConst.MAX_TITLE_COUNT) {
            throw new TipException("文章标题过长");
        }
        if (null == contents.getAuthorId()) {
            throw new TipException("请登录后发布文章");
        }
        int time = DateKit.getCurrentUnixTime();
        contents.setCreated(time);
        contents.setModified(time);
        contents.setHits(0);

        String tags = contents.getTags();
        String categories = contents.getCategories();
        certicatteVoMapper.insert(contents);
        Integer cid = contents.getCid();

        metasService.saveMetas(cid, tags, Types.TAG.getType());
        metasService.saveMetas(cid, categories, Types.CATEGORY.getType());
    }

    @Override
    public PageInfo<CertificateVo> getContents(Integer p, Integer limit) {
        CertificateVoExample example = new CertificateVoExample();
        example.setOrderByClause("created desc");
        example.createCriteria().andTypeEqualTo(Types.ARTICLE.getType()).andStatusEqualTo(Types.PUBLISH.getType());
        PageHelper.startPage(p, limit);
        List<CertificateVo> data = certicatteVoMapper.selectByExample(example);
        PageInfo<CertificateVo> pageInfo = new PageInfo<>(data);
        return pageInfo;
    }

    @Override
    public CertificateVo getContents(String id) {
        if (StringUtils.isNotBlank(id)) {
            if (Tools.isNumber(id)) {
                CertificateVo contentVo = certicatteVoMapper.selectByPrimaryKey(Integer.valueOf(id));
                if (contentVo != null) {
                    contentVo.setHits(contentVo.getHits() + 1);
                    certicatteVoMapper.updateByPrimaryKey(contentVo);
                }
                return contentVo;
            } else {
                CertificateVoExample certificateVoExample = new CertificateVoExample();
                certificateVoExample.createCriteria().andSlugEqualTo(id);
                List<CertificateVo> contentVos = certicatteVoMapper.selectByExample(certificateVoExample);
                if (contentVos.size() != 1) {
                    throw new TipException("query content by id and return is not one");
                }
                return contentVos.get(0);
            }
        }
        return null;
    }

    @Override
    public void updateContentByCid(CertificateVo contentVo) {
        if (null != contentVo && null != contentVo.getCid()) {
            certicatteVoMapper.updateByPrimaryKeySelective(contentVo);
        }
    }

    @Override
    public PageInfo<CertificateVo> getArticles(Integer mid, int page, int limit) {
        int total = metaDao.countWithSql(mid);
        PageHelper.startPage(page, limit);
        List<CertificateVo> list = certicatteVoMapper.findByCatalog(mid);
        PageInfo<CertificateVo> paginator = new PageInfo<>(list);
        paginator.setTotal(total);
        return paginator;
    }

    @Override
    public PageInfo<CertificateVo> getArticles(String keyword, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        CertificateVoExample certificateVoExample = new CertificateVoExample();
        CertificateVoExample.Criteria criteria = certificateVoExample.createCriteria();
        criteria.andTypeEqualTo(Types.ARTICLE.getType());
        criteria.andStatusEqualTo(Types.PUBLISH.getType());
        criteria.andTitleLike("%" + keyword + "%");
        certificateVoExample.setOrderByClause("created desc");
        List<CertificateVo> contentVos = certicatteVoMapper.selectByExample(certificateVoExample);
        return new PageInfo<>(contentVos);
    }

    @Override
    public PageInfo<CertificateVo> getArticlesWithpage(CertificateVoExample certificateVoExample, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<CertificateVo> contentVos = certicatteVoMapper.selectByExample(certificateVoExample);
        return new PageInfo<>(contentVos);
    }

    @Override
    public void deleteByCid(Integer cid) {
        CertificateVo contents = this.getContents(cid + "");
        if (null != contents) {
            certicatteVoMapper.deleteByPrimaryKey(cid);
            relationshipService.deleteById(cid, null);
        }
    }

    @Override
    public void updateCategory(String ordinal, String newCatefory) {
        CertificateVo contentVo = new CertificateVo();
        contentVo.setCategories(newCatefory);
        CertificateVoExample example = new CertificateVoExample();
        example.createCriteria().andCategoriesEqualTo(ordinal);
        certicatteVoMapper.updateByExampleSelective(contentVo, example);
    }

    @Override
    public void updateArticle(CertificateVo contents) {
        if (null == contents || null == contents.getCid()) {
            throw new TipException("文章对象不能为空");
        }
        if (StringUtils.isBlank(contents.getTitle())) {
            throw new TipException("文章标题不能为空");
        }
       
        if (contents.getTitle().length() > 200) {
            throw new TipException("文章标题过长");
        }
        
        if (null == contents.getAuthorId()) {
            throw new TipException("请登录后发布文章");
        }
        if (StringUtils.isBlank(contents.getSlug())) {
            contents.setSlug(null);
        }
        int time = DateKit.getCurrentUnixTime();
        contents.setModified(time);
        Integer cid = contents.getCid();

        certicatteVoMapper.updateByPrimaryKeySelective(contents);
        relationshipService.deleteById(cid, null);
        metasService.saveMetas(cid, contents.getTags(), Types.TAG.getType());
        metasService.saveMetas(cid, contents.getCategories(), Types.CATEGORY.getType());
    }
}
