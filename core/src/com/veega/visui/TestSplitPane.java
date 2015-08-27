/*
 * Copyright 2014-2015 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.veega.visui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisSplitPane;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisWindow;

public class TestSplitPane extends VisWindow {

	public TestSplitPane (boolean useVisWidgets) {
		super("splitpane");

		TableUtils.setSpacingDefaults(this);
		columnDefaults(0).left();

		if (useVisWidgets)
			addVisWidgets();
		else
			addNormalWidgets();

		setSize(300, 150);
		setPosition(958, 245);
	}

	private void addVisWidgets () {
		VisLabel label = new VisLabel("Lorem \nipsum \ndolor \nsit \namet");
		VisLabel label2 = new VisLabel("Consectetur \nadipiscing \nelit");
		VisTable table = new VisTable(true);
		VisTable table2 = new VisTable(true);

		table.add(label);
		table2.add(label2);

		VisSplitPane splitPane = new VisSplitPane(table, table2, false);
		add(splitPane).fill().expand();
	}

	private void addNormalWidgets () {
		Skin skin = VisUI.getSkin();
		Label label = new Label("Lorem \nipsum \ndolor \nsit \namet", skin);
		Label label2 = new Label("Consectetur \nadipiscing \nelit", skin);

		VisTable table = new VisTable(true);
		VisTable table2 = new VisTable(true);

		table.add(label);
		table2.add(label2);

		SplitPane splitPane = new SplitPane(table, table2, false, skin);
		add(splitPane).fill().expand();
	}

}
