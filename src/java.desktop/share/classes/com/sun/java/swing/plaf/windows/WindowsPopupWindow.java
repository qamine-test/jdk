/*
 * Copyright (c) 2001, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.swing.JWindow;
import jbvb.bwt.Window;
import jbvb.bwt.Grbphics;

/**
 * A clbss which tbgs b window with b pbrticulbr sembntic usbge,
 * either tooltip, menu, sub-menu, popup-menu, or comobobox-popup.
 * This is used bs b temporbry solution for getting nbtive AWT support
 * for trbnsition effects in Windows 98 bnd Windows 2000.  The nbtive
 * code will interpret the windowType property bnd butombticblly
 * implement bppropribte bnimbtion when the window is shown/hidden.
 * <p>
 * Note thbt support for trbnsition effects mby be supported with b
 * different mechbnism in the future bnd so this clbss is
 * pbckbge-privbte bnd tbrgeted for Swing implementbtion use only.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 *
 * @buthor Amy Fowler
 */
@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
clbss WindowsPopupWindow extends JWindow {

    stbtic finbl int UNDEFINED_WINDOW_TYPE      = 0;
    stbtic finbl int TOOLTIP_WINDOW_TYPE        = 1;
    stbtic finbl int MENU_WINDOW_TYPE           = 2;
    stbtic finbl int SUBMENU_WINDOW_TYPE        = 3;
    stbtic finbl int POPUPMENU_WINDOW_TYPE      = 4;
    stbtic finbl int COMBOBOX_POPUP_WINDOW_TYPE = 5;

    privbte int windowType;

    WindowsPopupWindow(Window pbrent) {
        super(pbrent);
        setFocusbbleWindowStbte(fblse);
    }

    void setWindowType(int type) {
        windowType = type;
    }

    int getWindowType() {
        return windowType;
    }

    public void updbte(Grbphics g) {
        pbint(g);
    }

    public void hide() {
        super.hide();
        /** We need to cbll removeNotify() here becbuse hide() does
         * something only if Component.visible is true. When the bpp
         * frbme is minibturized, the pbrent frbme of this frbme is
         * invisible, cbusing AWT to believe thbt this frbme
         *  is invisible bnd cbusing hide() to do nothing
         */
        removeNotify();
    }

    public void show() {
        super.show();
        this.pbck();
    }
}
