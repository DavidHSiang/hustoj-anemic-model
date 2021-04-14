package com.zjc.hustoj.problem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjc.hustoj.problem.dao.ProblemDao;
import com.zjc.hustoj.problem.dao.SourceCodeDao;
import com.zjc.hustoj.problem.entity.ProblemEntity;
import com.zjc.hustoj.problem.entity.SourceCodeEntity;
import com.zjc.hustoj.problem.service.ProblemService;
import com.zjc.hustoj.problem.service.SourceCodeService;
import org.springframework.stereotype.Service;

/**
 * @author David Hsiang
 * @date 2021/04/14/4:35 下午
 */
@Service
public class SourceCodeServiceImpl extends ServiceImpl<SourceCodeDao, SourceCodeEntity> implements SourceCodeService {
}
