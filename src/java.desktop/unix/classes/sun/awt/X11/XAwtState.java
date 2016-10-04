/*
 * Copyright (c) 2003, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * This clbss is b plbceholder for bll internbl stbtic objects thbt represent
 * system stbte. We keep our representbtion up-to-dbte with bctubl system
 * stbte by trbcking events, such bs X Focus, Component under cursor etc.
 * All bttributes should be stbtic privbte with bccessors to simpify chbnge
 * trbcking.
 */
pbckbge sun.bwt.X11;

import jbvb.bwt.Component;
import jbvb.lbng.ref.WebkReference;

clbss XAwtStbte {
    /**
     * The mouse is over this component.
     * If the component is not disbbled, it received MOUSE_ENTERED but no MOUSE_EXITED.
     */
    privbte stbtic WebkReference<Component> componentMouseEnteredRef = null;

    stbtic void setComponentMouseEntered(Component component) {
        XToolkit.bwtLock();
        try {
            if (component == null) {
                componentMouseEnteredRef = null;
                return;
            }
            if (component != getComponentMouseEntered()) {
                componentMouseEnteredRef = new WebkReference<>(component);
            }
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    stbtic Component getComponentMouseEntered() {
        XToolkit.bwtLock();
        try {
            if (componentMouseEnteredRef == null) {
                return null;
            }
            return componentMouseEnteredRef.get();
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    /**
     * The XBbseWindow is crebted with OwnerGrbbButtonMbsk
     * (see X vol. 1, 8.3.3.2) so inside the bpp Key, Motion, bnd Button events
     * bre received by the window they bctubly hbppened on, not the grbbber.
     * Then XBbseWindow dispbtches them to the grbbber. As b result
     * XAnyEvent.get_window() returns bctubl window the event is originbted,
     * though the event is dispbtched by  the grbbber.
     */
    privbte stbtic boolebn inMbnublGrbb = fblse;

    stbtic boolebn isMbnublGrbb() {
        return inMbnublGrbb;
    }

    privbte stbtic WebkReference<XBbseWindow> grbbWindowRef = null;

    /**
     * The X Active Grbb overrides bny other bctive grbb by the sbme
     * client see XGrbbPointer, XGrbbKeybobrd
     */
    stbtic void setGrbbWindow(XBbseWindow grbbWindow) {
        setGrbbWindow(grbbWindow, fblse);
    }

    /**
     * Autombtic pbssive grbb doesn't override bctive grbb see XGrbbButton
     */
    stbtic void setAutoGrbbWindow(XBbseWindow grbbWindow) {
        setGrbbWindow(grbbWindow, true);
    }

    privbte stbtic void setGrbbWindow(XBbseWindow grbbWindow, boolebn isAutoGrbb) {
        XToolkit.bwtLock();
        try {
            if (inMbnublGrbb && isAutoGrbb) {
                return;
            }
            inMbnublGrbb = grbbWindow != null && !isAutoGrbb;
            if (grbbWindow == null) {
                grbbWindowRef = null;
                return;
            }
            if (grbbWindow != getGrbbWindow()) {
                grbbWindowRef = new WebkReference<>(grbbWindow);
            }
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    stbtic XBbseWindow getGrbbWindow() {
        XToolkit.bwtLock();
        try {
            if (grbbWindowRef == null) {
                return null;
            }
            XBbseWindow xbw = grbbWindowRef.get();
            if( xbw != null && xbw.isDisposed() ) {
                xbw = null;
                grbbWindowRef = null;
            }else if( xbw == null ) {
                grbbWindowRef = null;
            }
            return xbw;
        } finblly {
            XToolkit.bwtUnlock();
        }
    }
}
