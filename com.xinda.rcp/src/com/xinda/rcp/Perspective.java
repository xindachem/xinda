package com.xinda.rcp;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.xinda.rcp.view.BookView;
import com.xinda.rcp.view.FolderView;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);
		layout.addView(FolderView.ID, IPageLayout.LEFT, 0.15f,
				layout.getEditorArea());
		layout.addView(BookView.ID, IPageLayout.RIGHT, IPageLayout.RATIO_MAX,
				layout.getEditorArea());
		layout.setFixed(true);
	}
}
