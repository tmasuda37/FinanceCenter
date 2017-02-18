package com.tmasuda.fc.handler;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/status")
@Controller
public class ServerStatusHandler {

    private static final Logger LOGGER = Logger.getLogger(ServerStatusHandler.class);

    @RequestMapping(value = "/wake-up", method = RequestMethod.GET)
    @ResponseBody
    public void wakeUp() {
        LOGGER.info("Wake-up request has been received.");
    }

}
