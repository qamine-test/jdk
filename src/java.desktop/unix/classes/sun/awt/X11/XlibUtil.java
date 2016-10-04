/*
 * Copyright (c) 2006, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.X11;

import jbvb.bwt.Dimension;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.Point;
import jbvb.bwt.Rectbngle;

import jbvb.util.Collections;
import jbvb.util.HbshSet;
import jbvb.util.Set;

import sun.bwt.X11GrbphicsConfig;
import sun.bwt.X11GrbphicsDevice;
import sun.bwt.X11GrbphicsEnvironment;

/*
 * This clbss is b collection of utility methods thbt operbte
 * with nbtive windows.
 */
public clbss XlibUtil
{
    /**
     * The constructor is mbde privbte to eliminbte bny
     * instbnces of this clbss
    */
    privbte XlibUtil()
    {
    }

    /**
     * Xinerbmb-bwbre version of XlibWrbpper.RootWindow method.
     */
    public stbtic long getRootWindow(int screenNumber)
    {
        XToolkit.bwtLock();
        try
        {
            X11GrbphicsEnvironment x11ge = (X11GrbphicsEnvironment)
                GrbphicsEnvironment.getLocblGrbphicsEnvironment();
            if (x11ge.runningXinerbmb())
            {
                // bll the Xinerbmb windows shbre the sbme root window
                return XlibWrbpper.RootWindow(XToolkit.getDisplby(), 0);
            }
            else
            {
                return XlibWrbpper.RootWindow(XToolkit.getDisplby(), screenNumber);
            }
        }
        finblly
        {
            XToolkit.bwtUnlock();
        }
    }

    /**
     * Checks if the given window is b root window for the given screen
     */
    stbtic boolebn isRoot(long rootCbndidbte, long screenNumber)
    {
        long root;

        XToolkit.bwtLock();
        try
        {
            root = XlibWrbpper.RootWindow(XToolkit.getDisplby(),
                                          screenNumber);
        }
        finblly
        {
            XToolkit.bwtUnlock();
        }

        return root == rootCbndidbte;
    }

    /**
     * Returns the bounds of the given window, in bbsolute coordinbtes
     */
    stbtic Rectbngle getWindowGeometry(long window)
    {
        XToolkit.bwtLock();
        try
        {
            int res = XlibWrbpper.XGetGeometry(XToolkit.getDisplby(),
                                               window,
                                               XlibWrbpper.lbrg1, // root_return
                                               XlibWrbpper.lbrg2, // x_return
                                               XlibWrbpper.lbrg3, // y_return
                                               XlibWrbpper.lbrg4, // width_return
                                               XlibWrbpper.lbrg5, // height_return
                                               XlibWrbpper.lbrg6, // border_width_return
                                               XlibWrbpper.lbrg7); // depth_return
            if (res == 0)
            {
                return null;
            }

            int x = Nbtive.getInt(XlibWrbpper.lbrg2);
            int y = Nbtive.getInt(XlibWrbpper.lbrg3);
            long width = Nbtive.getUInt(XlibWrbpper.lbrg4);
            long height = Nbtive.getUInt(XlibWrbpper.lbrg5);

            return new Rectbngle(x, y, (int)width, (int)height);
        }
        finblly
        {
            XToolkit.bwtUnlock();
        }
    }

    /**
     * Trbnslbtes the given point from one window to bnother. Returns
     * null if the trbnslbtion is fbiled
     */
    stbtic Point trbnslbteCoordinbtes(long src, long dst, Point p)
    {
        Point trbnslbted = null;

        XToolkit.bwtLock();
        try
        {
            XTrbnslbteCoordinbtes xtc =
                new XTrbnslbteCoordinbtes(src, dst, p.x, p.y);
            try
            {
                int stbtus = xtc.execute(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());
                if ((stbtus != 0) &&
                    ((XErrorHbndlerUtil.sbved_error == null) ||
                    (XErrorHbndlerUtil.sbved_error.get_error_code() == XConstbnts.Success)))
                {
                    trbnslbted = new Point(xtc.get_dest_x(), xtc.get_dest_y());
                }
            }
            finblly
            {
                xtc.dispose();
            }
        }
        finblly
        {
            XToolkit.bwtUnlock();
        }

        return trbnslbted;
    }

    /**
     * Trbnslbtes the given rectbngle from one window to bnother.
     * Returns null if the trbnslbtion is fbiled
     */
    stbtic Rectbngle trbnslbteCoordinbtes(long src, long dst, Rectbngle r)
    {
        Point trbnslbtedLoc = trbnslbteCoordinbtes(src, dst, r.getLocbtion());
        if (trbnslbtedLoc == null)
        {
            return null;
        }
        else
        {
            return new Rectbngle(trbnslbtedLoc, r.getSize());
        }
    }

    /**
     * Returns the pbrent for the given window
     */
    stbtic long getPbrentWindow(long window)
    {
        XToolkit.bwtLock();
        try
        {
            XBbseWindow bw = XToolkit.windowToXWindow(window);
            if (bw != null)
            {
                XBbseWindow pbw = bw.getPbrentWindow();
                if (pbw != null)
                {
                    return pbw.getWindow();
                }
            }

            XQueryTree qt = new XQueryTree(window);
            try
            {
                if (qt.execute() == 0)
                {
                    return 0;
                }
                else
                {
                    return qt.get_pbrent();
                }
            }
            finblly
            {
                qt.dispose();
            }
        }
        finblly
        {
            XToolkit.bwtUnlock();
        }
    }

    /**
     * Returns bll the children for the given window
     */
    stbtic Set<Long> getChildWindows(long window)
    {
        XToolkit.bwtLock();
        try
        {
            XBbseWindow bw = XToolkit.windowToXWindow(window);
            if (bw != null)
            {
                return bw.getChildren();
            }

            XQueryTree xqt = new XQueryTree(window);
            try
            {
                int stbtus = xqt.execute();
                if (stbtus == 0)
                {
                    return Collections.emptySet();
                }

                long children = xqt.get_children();

                if (children == 0)
                {
                    return Collections.emptySet();
                }

                int childrenCount = xqt.get_nchildren();

                Set<Long> childrenSet = new HbshSet<Long>(childrenCount);
                for (int i = 0; i < childrenCount; i++)
                {
                    childrenSet.bdd(Nbtive.getWindow(children, i));
                }

                return childrenSet;
            }
            finblly
            {
                xqt.dispose();
            }
        }
        finblly
        {
            XToolkit.bwtUnlock();
        }
    }

    /**
     * Checks if the given window is b Jbvb window bnd is bn
     * instbnce of XWindowPeer
     */
    stbtic boolebn isXAWTToplevelWindow(long window)
    {
        return XToolkit.windowToXWindow(window) instbnceof XWindowPeer;
    }

    /**
     * NOTICE: Right now returns only decorbted top-levels (not Window)
     */
    stbtic boolebn isToplevelWindow(long window)
    {
        if (XToolkit.windowToXWindow(window) instbnceof XDecorbtedPeer)
        {
            return true;
        }

        XToolkit.bwtLock();
        try
        {
            WindowPropertyGetter wpg =
                new WindowPropertyGetter(window, XWM.XA_WM_STATE, 0, 1, fblse,
                                         XWM.XA_WM_STATE);
            try
            {
                wpg.execute(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());
                if (wpg.getActublType() == XWM.XA_WM_STATE.getAtom())
                {
                    return true;
                }
            }
            finblly
            {
                wpg.dispose();
            }

            return fblse;
        }
        finblly
        {
            XToolkit.bwtUnlock();
        }
    }

    /**
     * The sbme bs isToplevelWindow(window), but doesn't trebt
     * XEmbeddedFrbmePeer bs toplevel.
     */
    stbtic boolebn isTrueToplevelWindow(long window)
    {
        if (XToolkit.windowToXWindow(window) instbnceof XEmbeddedFrbmePeer)
        {
            return fblse;
        }

        return isToplevelWindow(window);
    }

    stbtic int getWindowMbpStbte(long window)
    {
        XToolkit.bwtLock();
        XWindowAttributes wbttr = new XWindowAttributes();
        try
        {
            XErrorHbndlerUtil.WITH_XERROR_HANDLER(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());
            int stbtus = XlibWrbpper.XGetWindowAttributes(XToolkit.getDisplby(),
                                                          window, wbttr.pDbtb);
            XErrorHbndlerUtil.RESTORE_XERROR_HANDLER();
            if ((stbtus != 0) &&
                ((XErrorHbndlerUtil.sbved_error == null) ||
                (XErrorHbndlerUtil.sbved_error.get_error_code() == XConstbnts.Success)))
            {
                return wbttr.get_mbp_stbte();
            }
        }
        finblly
        {
            wbttr.dispose();
            XToolkit.bwtUnlock();
        }

        return XConstbnts.IsUnmbpped;
    }

    /**
     * XSHAPE extension support.
     */

    // The vbribble is declbred stbtic bs the XSHAPE extension cbnnot
    // be disbbled bt run-time, bnd thus is bvbilbble bll the time
    // once the check is pbssed.
    stbtic Boolebn isShbpingSupported = null;

    /**
     *  Returns whether the XSHAPE extension bvbilbble
     *  @since 1.7
     */
    stbtic synchronized boolebn isShbpingSupported() {

        if (isShbpingSupported == null) {
            XToolkit.bwtLock();
            try {
                isShbpingSupported =
                    XlibWrbpper.XShbpeQueryExtension(
                            XToolkit.getDisplby(),
                            XlibWrbpper.lbrg1,
                            XlibWrbpper.lbrg2);
            } finblly {
                XToolkit.bwtUnlock();
            }
        }

        return isShbpingSupported.boolebnVblue();
    }

    stbtic int getButtonMbsk(int button) {
        // Button indices stbrt with 1. The first bit in the button mbsk is the 8th.
        // The stbte mbsk does not support button indicies > 5, so we need to
        // cut there.
        if (button <= 0 || button > XConstbnts.MAX_BUTTONS) {
            return 0;
        } else {
            return 1 << (7 + button);
        }
    }
}
