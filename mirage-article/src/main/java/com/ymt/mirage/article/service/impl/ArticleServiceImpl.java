/**
 * 
 */
package com.ymt.mirage.article.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymt.mirage.article.domain.Article;
import com.ymt.mirage.article.domain.ArticleTag;
import com.ymt.mirage.article.dto.ArticleInfo;
import com.ymt.mirage.article.repository.ArticleRepository;
import com.ymt.mirage.article.repository.ArticleTagRepository;
import com.ymt.mirage.article.repository.spec.ArticleSpec;
import com.ymt.mirage.article.service.ArticleService;
import com.ymt.mirage.social.service.CommentService;
import com.ymt.mirage.tag.service.TagService;
import com.ymt.pz365.data.jpa.support.AbstractDomain2InfoConverter;
import com.ymt.pz365.data.jpa.support.QueryResultConverter;

/**
 * @author zhailiang
 * @since 2016年6月4日
 */
@Service("articleService")
@Transactional
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private ArticleTagRepository articleTagRepository;
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	private CommentService commentService;
	
	@Override
	public Page<ArticleInfo> query(ArticleInfo articleInfo, Pageable pageable) {
		List<Order> orders = new ArrayList<Order>();
		if(articleInfo.getTagId() == null) {
			orders.add(new Order(Direction.DESC, "top"));
			orders.add(new Order(Direction.DESC, "createdTime"));
		}else{
			orders.add(new Order(Direction.DESC, "target.top"));
			orders.add(new Order(Direction.DESC, "target.createdTime"));
		}
		
		Sort sort = new Sort(orders);
		pageable = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), sort);
		if(articleInfo.getTagId() == null) {
			Page<Article> pageData = articleRepository.findAll(new ArticleSpec(articleInfo), pageable);
			return QueryResultConverter.convert(pageData, pageable, new AbstractDomain2InfoConverter<Article, ArticleInfo>() {
				@Override
				protected void doConvert(Article domain, ArticleInfo info) throws Exception {
					info.setTagInfos(tagService.getTags(domain));
					info.setContent("");
				}
			});
		}else{
			Page<ArticleTag> articles = articleTagRepository.findByTagIdWithTop(articleInfo.getTagId(), pageable);
			return QueryResultConverter.convert(articles, pageable, new AbstractDomain2InfoConverter<ArticleTag, ArticleInfo>() {
				@Override
				protected void doConvert(ArticleTag domain, ArticleInfo info) throws Exception {
					info.setTagInfos(tagService.getTags(domain.getTarget()));
					BeanUtils.copyProperties(domain.getTarget(), info);
					info.setContent("");
				}
			});
		}		
	}

	@Override
	public ArticleInfo create(ArticleInfo articleInfo) throws Exception {
		Article article = new Article();
		BeanUtils.copyProperties(articleInfo, article);
		if(articleInfo.getEnable()) {
		    article.setEnabled(true);
        }
		articleInfo.setId(articleRepository.save(article).getId());
		tagService.addTag(article, articleInfo.getTagInfos());
		return articleInfo;
	}

	@Override
	public ArticleInfo getInfo(Long id) throws Exception {
		return getInfo(id, null);
	}

	@Override
	public ArticleInfo update(ArticleInfo articleInfo) throws Exception {
		Article article = articleRepository.findOne(articleInfo.getId());
		BeanUtils.copyProperties(articleInfo, article);
		if(articleInfo.getEnable()) {
            article.setEnabled(true);
        }
		articleRepository.save(article);
		tagService.addTag(article, articleInfo.getTagInfos());
		return articleInfo;
	}

	@Override
	public void delete(Long id) {
		articleRepository.delete(id);		
	}

    @Override
    public ArticleInfo getInfo(Long id, Long currentUserId) throws Exception {
        Article article = articleRepository.findOne(id);
        ArticleInfo info = new ArticleInfo();
        article.setReadCount(article.getReadCount() + 1);
        BeanUtils.copyProperties(article, info);
        info.setTagInfos(tagService.getTags(article));
        info.setCommentCount(commentService.getComments(article.getId(), "article").length);
        return info;
    }

    @Override
    public void update(Long id, String content) {
        Article article = articleRepository.findOne(id);
        article.setContent(content);
    }
	
}
