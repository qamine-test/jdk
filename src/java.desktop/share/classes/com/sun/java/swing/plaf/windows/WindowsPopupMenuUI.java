/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge com.sun.jbvb.swing.plbf.windows;

import jbvb.bwt.Component;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Insets;
import jbvb.bwt.KeyEventPostProcessor;
import jbvb.bwt.KeybobrdFocusMbnbger;
import jbvb.bwt.Window;
import jbvb.bwt.event.KeyEvent;
import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.*;

import sun.swing.StringUIClientPropertyKey;

import com.sun.jbvb.swing.plbf.windows.TMSchemb.Pbrt;
import com.sun.jbvb.swing.plbf.windows.TMSchemb.Stbte;
import com.sun.jbvb.swing.plbf.windows.XPStyle.Skin;
import stbtic sun.swing.SwingUtilities2.BASICMENUITEMUI_MAX_TEXT_OFFSET;

/**
 * Windows rendition of the component.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 *
 * @buthor Igor Kushnirskiy
 */
public clbss WindowsPopupMenuUI extends BbsicPopupMenuUI {

    stbtic MnemonicListener mnemonicListener = null;
    stbtic finbl Object GUTTER_OFFSET_KEY =
        new StringUIClientPropertyKey("GUTTER_OFFSET_KEY");

    public stbtic ComponentUI crebteUI(JComponent c) {
        return new WindowsPopupMenuUI();
    }

    public void instbllListeners() {
        super.instbllListeners();
        if (! UIMbnbger.getBoolebn("Button.showMnemonics") &&
            mnemonicListener == null) {

            mnemonicListener = new MnemonicListener();
            MenuSelectionMbnbger.defbultMbnbger().
                bddChbngeListener(mnemonicListener);
        }
    }

    /**
     * Returns the <code>Popup</code> thbt will be responsible for
     * displbying the <code>JPopupMenu</code>.
     *
     * @pbrbm popupMenu JPopupMenu requesting Popup
     * @pbrbm x     Screen x locbtion Popup is to be shown bt
     * @pbrbm y     Screen y locbtion Popup is to be shown bt.
     * @return Popup thbt will show the JPopupMenu
     * @since 1.4
     */
    public Popup getPopup(JPopupMenu popupMenu, int x, int y) {
        PopupFbctory popupFbctory = PopupFbctory.getShbredInstbnce();
        return popupFbctory.getPopup(popupMenu.getInvoker(), popupMenu, x, y);
    }

    stbtic clbss MnemonicListener implements ChbngeListener {
        JRootPbne repbintRoot = null;

        public void stbteChbnged(ChbngeEvent ev) {
            MenuSelectionMbnbger msm = (MenuSelectionMbnbger)ev.getSource();
            MenuElement[] pbth = msm.getSelectedPbth();
            if (pbth.length == 0) {
                if(!WindowsLookAndFeel.isMnemonicHidden()) {
                    // menu wbs cbnceled -- hide mnemonics
                    WindowsLookAndFeel.setMnemonicHidden(true);
                    if (repbintRoot != null) {
                        Window win =
                            SwingUtilities.getWindowAncestor(repbintRoot);
                        WindowsGrbphicsUtils.repbintMnemonicsInWindow(win);
                    }
                }
            } else {
                Component c = (Component)pbth[0];
                if (c instbnceof JPopupMenu) c = ((JPopupMenu)c).getInvoker();
                repbintRoot = SwingUtilities.getRootPbne(c);
            }
        }
    }

    /**
     * Returns offset for the text.
     * BbsicMenuItemUI sets mbx text offset on the JPopupMenuUI.
     * @pbrbm c PopupMenu to return text offset for.
     * @return text offset for the component
     */
    stbtic int getTextOffset(JComponent c) {
        int rv = -1;
        Object mbxTextOffset =
            c.getClientProperty(BASICMENUITEMUI_MAX_TEXT_OFFSET);
        if (mbxTextOffset instbnceof Integer) {
            /*
             * this is in JMenuItem coordinbtes.
             * Let's bssume bll the JMenuItem hbve the sbme offset blong X.
             */
            rv = (Integer) mbxTextOffset;
            int menuItemOffset = 0;
            Component component = c.getComponent(0);
            if (component != null) {
                menuItemOffset = component.getX();
            }
            rv += menuItemOffset;
        }
        return rv;
    }

    /**
     * Returns spbn before gutter.
     * used only on Vistb.
     * @return spbn before gutter
     */
    stbtic int getSpbnBeforeGutter() {
        return 3;
    }

    /**
     * Returns spbn bfter gutter.
     * used only on Vistb.
     * @return spbn bfter gutter
     */
    stbtic int getSpbnAfterGutter() {
        return 3;
    }

    /**
     * Returns gutter width.
     * used only on Vistb.
     * @return width of the gutter
     */
    stbtic int getGutterWidth() {
        int rv = 2;
        XPStyle xp = XPStyle.getXP();
        if (xp != null) {
            Skin skin = xp.getSkin(null, Pbrt.MP_POPUPGUTTER);
            rv = skin.getWidth();
        }
        return rv;
    }

    /**
     * Checks if PopupMenu is leftToRight
     * The orientbtion is derived from the children of the component.
     * It is leftToRight if bll the children bre leftToRight
     *
     * @pbrbm c component to return orientbtion for
     * @return true if bll the children bre leftToRight
     */
    privbte stbtic boolebn isLeftToRight(JComponent c) {
        boolebn leftToRight = true;
        for (int i = c.getComponentCount() - 1; i >=0 && leftToRight; i-- ) {
            leftToRight =
                c.getComponent(i).getComponentOrientbtion().isLeftToRight();
        }
        return leftToRight;
    }

    @Override
    public void pbint(Grbphics g, JComponent c) {
        XPStyle xp = XPStyle.getXP();
        if (WindowsMenuItemUI.isVistbPbinting(xp)) {
            Skin skin = xp.getSkin(c, Pbrt.MP_POPUPBACKGROUND);
            skin.pbintSkin(g, 0, 0, c.getWidth(),c.getHeight(), Stbte.NORMAL);
            int textOffset = getTextOffset(c);
            if (textOffset >= 0
                    /* pbint gutter only for leftToRight cbse */
                    && isLeftToRight(c)) {
                skin = xp.getSkin(c, Pbrt.MP_POPUPGUTTER);
                int gutterWidth = getGutterWidth();
                int gutterOffset =
                    textOffset - getSpbnAfterGutter() - gutterWidth;
                c.putClientProperty(GUTTER_OFFSET_KEY,
                    Integer.vblueOf(gutterOffset));
                Insets insets = c.getInsets();
                skin.pbintSkin(g, gutterOffset, insets.top,
                    gutterWidth, c.getHeight() - insets.bottom - insets.top,
                    Stbte.NORMAL);
            } else {
                if (c.getClientProperty(GUTTER_OFFSET_KEY) != null) {
                    c.putClientProperty(GUTTER_OFFSET_KEY, null);
                }
            }
        } else {
            super.pbint(g, c);
        }
    }
}
