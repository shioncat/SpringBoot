package com.gjun.domain;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name="通用WebHook Event Entity",description="Line WebHook 通用格式")
public class WebHookData{
	@Schema(name="destination",description="目的地",required=true)
    public String destination;
	@Schema(name="MyEvent",description="WebHook事件(可以多事件)",required=true)
    public MyEvent[] events;
}