/**
 * 
 */
package com.ymt.mirage.meilang.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymt.mirage.meilang.domain.Videos;
import com.ymt.mirage.meilang.dto.VideosInfo;
import com.ymt.mirage.meilang.repository.VideosRepository;
import com.ymt.mirage.meilang.service.VideosService;

/**
 * @author zhailiang
 * @since 2016年5月15日
 */
@Service("videosService")
@Transactional
public class VideosServiceImpl implements VideosService {
	
	@Autowired
	private VideosRepository videosRepository;

	/* (non-Javadoc)
	 * @see com.ymt.mirage.meilang.service.VideosService#query(com.ymt.mirage.meilang.dto.VideosInfo)
	 */
	@Override
	public List<VideosInfo> query(VideosInfo videosInfo) {
		List<Videos> videoses = videosRepository.findBySetId(videosInfo.getSetId(), new Sort(Direction.ASC, "index"));
		List<VideosInfo> result = new ArrayList<VideosInfo>();
		for (Videos videos : videoses) {
			VideosInfo info = new VideosInfo();
			BeanUtils.copyProperties(videos, info);
			info.setName(videos.getVideo().getName());
			info.setSelection(videos.getVideo().isSelection());
			info.setPrice(videos.getVideo().getPrice());
			info.setDuration(videos.getVideo().getDuration());
			result.add(info);
		}
		return result;
	}

	@Override
	public void delete(Long id) {
		videosRepository.delete(id);
	}

	@Override
	public void update(List<VideosInfo> infos) {
		for (int i = 0; i < infos.size(); i++) {
			Videos videos = videosRepository.findOne(infos.get(i).getId());
			videos.setIndex(i);
			videosRepository.save(videos);
		}
		
	}

}
