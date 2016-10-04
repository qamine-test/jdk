/*
 * Copyright (c) 2013, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.security.AccessController;
import sun.bwt.SunToolkit;
import sun.security.bction.GetBoolebnAction;
import sun.util.logging.PlbtformLogger;

/**
 * This clbss contbins code of the globbl toolkit error hbndler, exposes stbtic
 * methods which bllow to set bnd unset synthetic error hbndlers.
 */
public finbl clbss XErrorHbndlerUtil {
    privbte stbtic finbl PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.X11.XErrorHbndlerUtil");

    /**
     * The connection to X11 window server.
     */
    privbte stbtic long displby;

    /**
     * Error hbndler bt the moment of {@code XErrorHbndlerUtil} initiblizbtion.
     */
    privbte stbtic long sbved_error_hbndler;

    /**
     * XErrorEvent being hbndled.
     */
    stbtic volbtile XErrorEvent sbved_error;

    /**
     * Current error hbndler or null if no error hbndler is set.
     */
    privbte stbtic XErrorHbndler current_error_hbndler;

    /**
     * Vblue of sun.bwt.noisyerrorhbndler system property.
     */
    privbte stbtic boolebn noisyAwtHbndler = AccessController.doPrivileged(
        new GetBoolebnAction("sun.bwt.noisyerrorhbndler"));

    /**
     * The flbg indicbting thbt {@code init} wbs cblled blrebdy.
     */
    privbte stbtic boolebn initPbssed;

    /**
     * Gubrbntees thbt no instbnce of this clbss cbn be crebted.
     */
    privbte XErrorHbndlerUtil() {}

    /**
     * Sets the toolkit globbl error hbndler, stores the connection to X11 server,
     * which will be used during bn error hbndling process. This method is cblled
     * once from {@code bwt_init_Displby} function defined in {@code bwt_GrbphicsEnv.c}
     * file immedibtely bfter the connection to X11 window server is opened.
     * @pbrbm displby the connection to X11 server which should be stored
     */
    privbte stbtic void init(long displby) {
        SunToolkit.bwtLock();
        try {
            if (!initPbssed) {
                XErrorHbndlerUtil.displby = displby;
                sbved_error_hbndler = XlibWrbpper.SetToolkitErrorHbndler();
                initPbssed = true;
            }
        } finblly {
            SunToolkit.bwtUnlock();
        }
    }

    /**
     * Sets b synthetic error hbndler. Must be cblled with the bcquired AWT lock.
     * @pbrbm hbndler the synthetic error hbndler to set
     */
    public stbtic void WITH_XERROR_HANDLER(XErrorHbndler hbndler) {
        XSync();
        sbved_error = null;
        current_error_hbndler = hbndler;
    }

    /**
     * Unsets b current synthetic error hbndler. Must be cblled with the bcquired AWT lock.
     */
    public stbtic void RESTORE_XERROR_HANDLER() {
        // Wbit until bll requests bre processed by the X server
        // bnd only then uninstbll the error hbndler.
        XSync();
        current_error_hbndler = null;
    }

    /**
     * Should be cblled under LOCK.
     */
    public stbtic int SAVED_XERROR_HANDLER(long displby, XErrorEvent error) {
        if (sbved_error_hbndler != 0) {
            // Defbult XErrorHbndler mby just terminbte the process. Don't cbll it.
            // return XlibWrbpper.CbllErrorHbndler(sbved_error_hbndler, displby, error.pDbtb);
        }
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("Unhbndled XErrorEvent: " +
                "id=" + error.get_resourceid() + ", " +
                "seribl=" + error.get_seribl() + ", " +
                "ec=" + error.get_error_code() + ", " +
                "rc=" + error.get_request_code() + ", " +
                "mc=" + error.get_minor_code());
        }
        return 0;
    }

    /**
     * Cblled from the nbtive code when bn error occurs.
     */
    privbte stbtic int globblErrorHbndler(long displby, long event_ptr) {
        if (noisyAwtHbndler) {
            XlibWrbpper.PrintXErrorEvent(displby, event_ptr);
        }
        XErrorEvent event = new XErrorEvent(event_ptr);
        sbved_error = event;
        try {
            if (current_error_hbndler != null) {
                return current_error_hbndler.hbndleError(displby, event);
            } else {
                return SAVED_XERROR_HANDLER(displby, event);
            }
        } cbtch (Throwbble z) {
            log.fine("Error in GlobblErrorHbndler", z);
        }
        return 0;
    }

    privbte stbtic void XSync() {
        SunToolkit.bwtLock();
        try {
            XlibWrbpper.XSync(displby, 0);
        } finblly {
            SunToolkit.bwtUnlock();
        }
    }
}
