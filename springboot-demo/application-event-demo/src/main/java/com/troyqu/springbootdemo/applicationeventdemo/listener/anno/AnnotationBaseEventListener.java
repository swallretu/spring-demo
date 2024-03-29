package com.troyqu.springbootdemo.applicationeventdemo.listener.anno;

import com.alibaba.fastjson.JSON;
import com.troyqu.springbootdemo.applicationeventdemo.event.BaseEvent;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

/**
 * 基于注解的ApplicationListener处理, listener支持多线程操作，当有多个event时间被触发的时候，如果第一个事件没有完成，不会阻塞后面的事件处理
 */
@Slf4j
@Configuration
public class AnnotationBaseEventListener {

    /**
     * 配置Aysnc注解使用异步来处理事件，不添加async listener采用同步的方式来处理事件
     *
     * @param event
     * @throws InterruptedException
     */
    @Async("threadPoolTaskExecutor")
    @EventListener(classes = {BaseEvent.class})
    public void tmsApproveEventListener(BaseEvent event) throws InterruptedException {
        Thread.sleep(5 * 1000);
        hanlderEvent(event);
    }

    private void hanlderEvent(BaseEvent event) throws InterruptedException {
        val startTime = System.currentTimeMillis();
        log.info("AnnotationBaseEventListener started {}", startTime);
        switch (event.getType()) {
            case "troyqu":
                log.info("TroyquApplicationEvent={} onApplicationEvent happened with annotation: {}", event.getClass(), JSON.toJSONString(event, true));
                break;
            case "troy":
                log.info("TroyApplicationEvent={} onApplicationEvent happened with annotation: {}", event.getClass(), JSON.toJSONString(event, true));
                break;
            default:
                break;

        }
        Thread.sleep(3 * 1000); //mock business operation waiting
        log.info("AnnotationBaseEventListener finished {}", startTime);
    }
}
