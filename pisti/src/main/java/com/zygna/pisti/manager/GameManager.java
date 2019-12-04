package com.zygna.pisti.manager;

import com.zygna.pisti.pojo.Bot;

public interface GameManager {
	
	public void initGames(Integer playersNo,Integer gamesNo,Bot smartBot,Bot dummyBot);
	
}
