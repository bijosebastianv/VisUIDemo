package com.veega.visui;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.util.dialog.DialogUtils;
import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuBar;
import com.kotcrab.vis.ui.widget.MenuItem;
import com.kotcrab.vis.ui.widget.PopupMenu;

public class VisUIDemo extends ApplicationAdapter {


	private static final int TESTS_VERSION = 3;
	private static final boolean USE_VIS_WIDGETS = true;

	private Stage stage;
	private MenuBar menuBar;

	@Override
	public void create () {
		VisUI.load(VisUI.SkinScale.X1);

		stage = new Stage(new ScreenViewport());
		Table root = new Table();
		root.setFillParent(true);
		stage.addActor(root);

		Gdx.input.setInputProcessor(stage);

		menuBar = new MenuBar();
		root.add(menuBar.getTable()).expandX().fillX().row();
		root.add().expand().fill();

		createMenus();

		stage.addActor(new TestCollapsible());
		if (Gdx.app.getType() != Application.ApplicationType.WebGL) stage.addActor(new TestColorPicker());
		if (Gdx.app.getType() == Application.ApplicationType.Desktop) stage.addActor(new TestFileChooser());
		stage.addActor(new TestWindow(USE_VIS_WIDGETS));
		stage.addActor(new TestSplitPane(USE_VIS_WIDGETS));
		stage.addActor(new TestTextAreaAndScroll(USE_VIS_WIDGETS));
		stage.addActor(new TestTree(USE_VIS_WIDGETS));
		stage.addActor(new TestVertical(USE_VIS_WIDGETS));
		stage.addActor(new TestFormValidator());
		stage.addActor(new TestDialogUtils());
		stage.addActor(new TestValidator());
		stage.addActor(new TestBuilders());
	}

	private void createMenus () {
		Menu fileMenu = new Menu("File");
		Menu editMenu = new Menu("Edit");
		Menu windowMenu = new Menu("Window");
		Menu helpMenu = new Menu("Help");

		fileMenu.addItem(new MenuItem("menuitem #1"));
		fileMenu.addItem(new MenuItem("menuitem #2").setShortcut("f1"));
		fileMenu.addItem(new MenuItem("menuitem #3").setShortcut("f2"));
		fileMenu.addItem(new MenuItem("menuitem #4").setShortcut("alt + f4"));

		MenuItem subMenuItem = new MenuItem("subnenu #1");
		subMenuItem.setShortcut("alt + insert");
		subMenuItem.setSubMenu(createSubMenu());
		fileMenu.addItem(subMenuItem);

		MenuItem subMenuItem2 = new MenuItem("subnenu #2");
		subMenuItem2.setSubMenu(createSubMenu());
		fileMenu.addItem(subMenuItem2);

		MenuItem subMenuItem3 = new MenuItem("submenu disabled");
		subMenuItem3.setDisabled(true);
		subMenuItem3.setSubMenu(createSubMenu());
		fileMenu.addItem(subMenuItem3);

		// ---

		editMenu.addItem(new MenuItem("menuitem #5"));
		editMenu.addItem(new MenuItem("menuitem #6"));
		editMenu.addSeparator();
		editMenu.addItem(new MenuItem("menuitem #7"));
		editMenu.addItem(new MenuItem("menuitem #8"));

		MenuItem disabledItem = new MenuItem("disabled menuitem");
		disabledItem.setDisabled(true);
		MenuItem disabledItem2 = new MenuItem("disabled menuitem shortcut").setShortcut("alt + f4");
		disabledItem2.setDisabled(true);

		editMenu.addItem(disabledItem);
		editMenu.addItem(disabledItem2);

		windowMenu.addItem(new MenuItem("menuitem #9"));
		windowMenu.addItem(new MenuItem("menuitem #10"));
		windowMenu.addItem(new MenuItem("menuitem #11"));
		windowMenu.addSeparator();
		windowMenu.addItem(new MenuItem("menuitem #12"));

		helpMenu.addItem(new MenuItem("menuitem #13"));
		helpMenu.addItem(new MenuItem("menuitem #14"));

		helpMenu.addItem(new MenuItem("about", new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor actor) {
				DialogUtils.showOKDialog(stage, "about", "tests version: " + TESTS_VERSION + " \nvisui version: " + VisUI.VERSION);
			}

		}));

		menuBar.addMenu(fileMenu);
		menuBar.addMenu(editMenu);
		menuBar.addMenu(windowMenu);
		menuBar.addMenu(helpMenu);
	}

	private PopupMenu createSubMenu () {
		PopupMenu menu = new PopupMenu();
		menu.addItem(new MenuItem("Submenuitem #1"));
		menu.addItem(new MenuItem("Submenuitem #2"));
		menu.addSeparator();
		menu.addItem(new MenuItem("Submenuitem #3"));
		menu.addItem(new MenuItem("Submenuitem #4"));
		return menu;
	}

	@Override
	public void resize (int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void dispose () {
		VisUI.dispose();
		stage.dispose();
	}

}
