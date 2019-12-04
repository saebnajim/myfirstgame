package com.zygna.pisti.pojo;

import com.zygna.pisti.bot.BotService;

public class Bot {

	private String botName;
	private BotService botService;
	
	public Bot(String botName,BotService botService){
		this.botName=botName;
		this.botService=botService;
	}

	public String getBotName() {
		return botName;
	}

	public void setBotName(String botName) {
		this.botName = botName;
	}

	public BotService getBotService() {
		return botService;
	}

	public void setBotService(BotService botService) {
		this.botService = botService;
	}

}
