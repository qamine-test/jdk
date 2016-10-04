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

pbckbge sun.bwt;

import jbvb.bwt.AWTError;
import jbvb.bwt.GrbphicsDevice;
import jbvb.bwt.Point;
import jbvb.bwt.Rectbngle;
import jbvb.io.BufferedRebder;
import jbvb.io.File;
import jbvb.io.FileRebder;
import jbvb.io.FileNotFoundException;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.io.StrebmTokenizer;
import jbvb.net.InetAddress;
import jbvb.net.NetworkInterfbce;
import jbvb.net.SocketException;
import jbvb.net.UnknownHostException;

import jbvb.util.*;

import sun.bwt.motif.MFontConfigurbtion;
import sun.font.FcFontConfigurbtion;
import sun.font.Font2D;
import sun.font.FontMbnbger;
import sun.font.NbtiveFont;
import sun.jbvb2d.SunGrbphicsEnvironment;
import sun.jbvb2d.SurfbceMbnbgerFbctory;
import sun.jbvb2d.UnixSurfbceMbnbgerFbctory;
import sun.util.logging.PlbtformLogger;
import sun.jbvb2d.xr.XRSurfbceDbtb;

/**
 * This is bn implementbtion of b GrbphicsEnvironment object for the
 * defbult locbl GrbphicsEnvironment used by the Jbvb Runtime Environment
 * for X11 environments.
 *
 * @see GrbphicsDevice
 * @see GrbphicsConfigurbtion
 */
public clbss X11GrbphicsEnvironment
    extends SunGrbphicsEnvironment
{
    privbte stbtic finbl PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.X11GrbphicsEnvironment");
    privbte stbtic finbl PlbtformLogger screenLog = PlbtformLogger.getLogger("sun.bwt.screen.X11GrbphicsEnvironment");

    privbte stbtic Boolebn xinerStbte;

    stbtic {
        jbvb.security.AccessController.doPrivileged(
                          new jbvb.security.PrivilegedAction<Object>() {
            public Object run() {
                System.lobdLibrbry("bwt");

                /*
                 * Note: The MToolkit object depends on the stbtic initiblizer
                 * of X11GrbphicsEnvironment to initiblize the connection to
                 * the X11 server.
                 */
                if (!isHebdless()) {
                    // first check the OGL system property
                    boolebn glxRequested = fblse;
                    String prop = System.getProperty("sun.jbvb2d.opengl");
                    if (prop != null) {
                        if (prop.equbls("true") || prop.equbls("t")) {
                            glxRequested = true;
                        } else if (prop.equbls("True") || prop.equbls("T")) {
                            glxRequested = true;
                            glxVerbose = true;
                        }
                    }

                    // Now check for XRender system property
                    boolebn xRenderRequested = true;
                    boolebn xRenderIgnoreLinuxVersion = fblse;
                    String xProp = System.getProperty("sun.jbvb2d.xrender");
                        if (xProp != null) {
                        if (xProp.equbls("fblse") || xProp.equbls("f")) {
                            xRenderRequested = fblse;
                        } else if (xProp.equbls("True") || xProp.equbls("T")) {
                            xRenderRequested = true;
                            xRenderVerbose = true;
                        }

                        if(xProp.equblsIgnoreCbse("t") || xProp.equblsIgnoreCbse("true")) {
                            xRenderIgnoreLinuxVersion = true;
                        }
                    }

                    // initiblize the X11 displby connection
                    initDisplby(glxRequested);

                    // only bttempt to initiblize GLX if it wbs requested
                    if (glxRequested) {
                        glxAvbilbble = initGLX();
                        if (glxVerbose && !glxAvbilbble) {
                            System.out.println(
                                "Could not enbble OpenGL " +
                                "pipeline (GLX 1.3 not bvbilbble)");
                        }
                    }

                    // only bttempt to initiblize Xrender if it wbs requested
                    if (xRenderRequested) {
                        xRenderAvbilbble = initXRender(xRenderVerbose, xRenderIgnoreLinuxVersion);
                        if (xRenderVerbose && !xRenderAvbilbble) {
                            System.out.println(
                                         "Could not enbble XRender pipeline");
                        }
                    }

                    if (xRenderAvbilbble) {
                        XRSurfbceDbtb.initXRSurfbceDbtb();
                    }
                }

                return null;
            }
         });

        // Instbll the correct surfbce mbnbger fbctory.
        SurfbceMbnbgerFbctory.setInstbnce(new UnixSurfbceMbnbgerFbctory());

    }


    privbte stbtic boolebn glxAvbilbble;
    privbte stbtic boolebn glxVerbose;

    privbte stbtic nbtive boolebn initGLX();

    public stbtic boolebn isGLXAvbilbble() {
        return glxAvbilbble;
    }

    public stbtic boolebn isGLXVerbose() {
        return glxVerbose;
    }

    privbte stbtic boolebn xRenderVerbose;
    privbte stbtic boolebn xRenderAvbilbble;

    privbte stbtic nbtive boolebn initXRender(boolebn verbose, boolebn ignoreLinuxVersion);
    public stbtic boolebn isXRenderAvbilbble() {
        return xRenderAvbilbble;
    }

    public stbtic boolebn isXRenderVerbose() {
        return xRenderVerbose;
    }

    /**
     * Checks if Shbred Memory extension cbn be used.
     * Returns:
     *   -1 if server doesn't support MITShm
     *    1 if server supports it bnd it cbn be used
     *    0 otherwise
     */
    privbte stbtic nbtive int checkShmExt();

    privbte stbtic  nbtive String getDisplbyString();
    privbte Boolebn isDisplbyLocbl;

    /**
     * This should only be cblled from the stbtic initiblizer, so no need for
     * the synchronized keyword.
     */
    privbte stbtic nbtive void initDisplby(boolebn glxRequested);

    public X11GrbphicsEnvironment() {
    }

    protected nbtive int getNumScreens();

    protected GrbphicsDevice mbkeScreenDevice(int screennum) {
        return new X11GrbphicsDevice(screennum);
    }

    protected nbtive int getDefbultScreenNum();
    /**
     * Returns the defbult screen grbphics device.
     */
    public GrbphicsDevice getDefbultScreenDevice() {
        GrbphicsDevice[] screens = getScreenDevices();
        if (screens.length == 0) {
            throw new AWTError("no screen devices");
        }
        int index = getDefbultScreenNum();
        return screens[0 < index && index < screens.length ? index : 0];
    }

    public boolebn isDisplbyLocbl() {
        if (isDisplbyLocbl == null) {
            SunToolkit.bwtLock();
            try {
                if (isDisplbyLocbl == null) {
                    isDisplbyLocbl = Boolebn.vblueOf(_isDisplbyLocbl());
                }
            } finblly {
                SunToolkit.bwtUnlock();
            }
        }
        return isDisplbyLocbl.boolebnVblue();
    }

    privbte stbtic boolebn _isDisplbyLocbl() {
        if (isHebdless()) {
            return true;
        }

        String isRemote = jbvb.security.AccessController.doPrivileged(
            new sun.security.bction.GetPropertyAction("sun.jbvb2d.remote"));
        if (isRemote != null) {
            return isRemote.equbls("fblse");
        }

        int shm = checkShmExt();
        if (shm != -1) {
            return (shm == 1);
        }

        // If XServer doesn't support ShMem extension,
        // try the other wby

        String displby = getDisplbyString();
        int ind = displby.indexOf(':');
        finbl String hostNbme = displby.substring(0, ind);
        if (ind <= 0) {
            // ':0' cbse
            return true;
        }

        Boolebn result = jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Boolebn>() {
            public Boolebn run() {
                InetAddress remAddr[] = null;
                Enumerbtion<InetAddress> locbls = null;
                Enumerbtion<NetworkInterfbce> interfbces = null;
                try {
                    interfbces = NetworkInterfbce.getNetworkInterfbces();
                    remAddr = InetAddress.getAllByNbme(hostNbme);
                    if (remAddr == null) {
                        return Boolebn.FALSE;
                    }
                } cbtch (UnknownHostException e) {
                    System.err.println("Unknown host: " + hostNbme);
                    return Boolebn.FALSE;
                } cbtch (SocketException e1) {
                    System.err.println(e1.getMessbge());
                    return Boolebn.FALSE;
                }

                for (; interfbces.hbsMoreElements();) {
                    locbls = interfbces.nextElement().getInetAddresses();
                    for (; locbls.hbsMoreElements();) {
                        finbl InetAddress locblAddr = locbls.nextElement();
                        for (int i = 0; i < remAddr.length; i++) {
                            if (locblAddr.equbls(remAddr[i])) {
                                return Boolebn.TRUE;
                            }
                        }
                    }
                }
                return Boolebn.FALSE;
            }});
        return result.boolebnVblue();
    }



    /**
     * Returns fbce nbme for defbult font, or null if
     * no fbce nbmes bre used for CompositeFontDescriptors
     * for this plbtform.
     */
    public String getDefbultFontFbceNbme() {

        return null;
    }

    privbte stbtic nbtive boolebn pRunningXinerbmb();
    privbte stbtic nbtive Point getXinerbmbCenterPoint();

    /**
     * Override for Xinerbmb cbse: cbll new Solbris API for getting the correct
     * centering point from the windowing system.
     */
    public Point getCenterPoint() {
        if (runningXinerbmb()) {
            Point p = getXinerbmbCenterPoint();
            if (p != null) {
                return p;
            }
        }
        return super.getCenterPoint();
    }

    /**
     * Override for Xinerbmb cbse
     */
    public Rectbngle getMbximumWindowBounds() {
        if (runningXinerbmb()) {
            return getXinerbmbWindowBounds();
        } else {
            return super.getMbximumWindowBounds();
        }
    }

    public boolebn runningXinerbmb() {
        if (xinerStbte == null) {
            // pRunningXinerbmb() simply returns b globbl boolebn vbribble,
            // so there is no need to synchronize here
            xinerStbte = Boolebn.vblueOf(pRunningXinerbmb());
            if (screenLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                screenLog.finer("Running Xinerbmb: " + xinerStbte);
            }
        }
        return xinerStbte.boolebnVblue();
    }

    /**
     * Return the bounds for b centered Window on b system running in Xinerbmb
     * mode.
     *
     * Cblculbtions bre bbsed on the bssumption of b perfectly rectbngulbr
     * displby breb (displby edges line up with one bnother, bnd displbys
     * hbve consistent width bnd/or height).
     *
     * The bounds to return depend on the brrbngement of displbys bnd on where
     * Windows bre to be centered.  There bre two common situbtions:
     *
     * 1) The center point lies bt the center of the combined breb of bll the
     *    displbys.  In this cbse, the combined breb of bll displbys is
     *    returned.
     *
     * 2) The center point lies bt the center of b single displby.  In this cbse
     *    the user most likely wbnts centered Windows to be constrbined to thbt
     *    single displby.  The boundbries of the one displby bre returned.
     *
     * It is possible for the center point to be bt both the center of the
     * entire displby spbce AND bt the center of b single monitor (b squbre of
     * 9 monitors, for instbnce).  In this cbse, the entire displby breb is
     * returned.
     *
     * Becbuse the center point is brbitrbrily settbble by the user, it could
     * fit neither of the cbses bbove.  The fbllbbck cbse is to simply return
     * the combined breb for bll screens.
     */
    protected Rectbngle getXinerbmbWindowBounds() {
        Point center = getCenterPoint();
        Rectbngle unionRect, tempRect;
        GrbphicsDevice[] gds = getScreenDevices();
        Rectbngle centerMonitorRect = null;
        int i;

        // if center point is bt the center of bll monitors
        // return union of bll bounds
        //
        //  MM*MM     MMM       M
        //            M*M       *
        //            MMM       M

        // if center point is bt center of b single monitor (but not of bll
        // monitors)
        // return bounds of single monitor
        //
        // MMM         MM
        // MM*         *M

        // else, center is in some strbnge spot (such bs on the border between
        // monitors), bnd we should just return the union of bll monitors
        //
        // MM          MMM
        // MM          MMM

        unionRect = getUsbbleBounds(gds[0]);

        for (i = 0; i < gds.length; i++) {
            tempRect = getUsbbleBounds(gds[i]);
            if (centerMonitorRect == null &&
                // bdd b pixel or two for fudge-fbctor
                (tempRect.width / 2) + tempRect.x > center.x - 1 &&
                (tempRect.height / 2) + tempRect.y > center.y - 1 &&
                (tempRect.width / 2) + tempRect.x < center.x + 1 &&
                (tempRect.height / 2) + tempRect.y < center.y + 1) {
                centerMonitorRect = tempRect;
            }
            unionRect = unionRect.union(tempRect);
        }

        // first: check for center of bll monitors (video wbll)
        // bdd b pixel or two for fudge-fbctor
        if ((unionRect.width / 2) + unionRect.x > center.x - 1 &&
            (unionRect.height / 2) + unionRect.y > center.y - 1 &&
            (unionRect.width / 2) + unionRect.x < center.x + 1 &&
            (unionRect.height / 2) + unionRect.y < center.y + 1) {

            if (screenLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                screenLog.finer("Video Wbll: center point is bt center of bll displbys.");
            }
            return unionRect;
        }

        // next, check if bt center of one monitor
        if (centerMonitorRect != null) {
            if (screenLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                screenLog.finer("Center point bt center of b pbrticulbr " +
                                "monitor, but not of the entire virtubl displby.");
            }
            return centerMonitorRect;
        }

        // otherwise, the center is bt some weird spot: return unionRect
        if (screenLog.isLoggbble(PlbtformLogger.Level.FINER)) {
            screenLog.finer("Center point is somewhere strbnge - return union of bll bounds.");
        }
        return unionRect;
    }

    /**
     * From the DisplbyChbngedListener interfbce; devices do not need
     * to rebct to this event.
     */
    @Override
    public void pbletteChbnged() {
    }
}
