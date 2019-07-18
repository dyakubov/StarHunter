package ru.geekbrains;

import com.badlogic.gdx.Game;
import ru.geekbrains.screen.MenuScreen;

public class StarHunter extends Game {


	@Override
	public void create() {
		setScreen(new MenuScreen());
	}
}
