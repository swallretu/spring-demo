package com.troyqu.springbootdemo.applicationeventdemo.event;

import com.troyqu.springbootdemo.applicationeventdemo.dto.Message;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TroyquApplicationEvent extends BaseEvent {

    Message message;

    public TroyquApplicationEvent(Object source, String type, Message message) {
        super(source, type);
        this.message = message;
    }
}
