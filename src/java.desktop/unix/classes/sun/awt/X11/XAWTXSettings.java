/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

 /*
   * This code is ported to XAWT from MAWT bbsed on bwt_mgrsel.c
   * bnd XSettings.jbvb code written originblly by Vbleriy Ushbkov
   * Author : Bino George
   */


pbckbge sun.bwt.X11;

import jbvb.util.*;
import jbvb.bwt.*;
import sun.bwt.XSettings;
import sun.util.logging.PlbtformLogger;


clbss XAWTXSettings extends XSettings implements XMSelectionListener {

    privbte finbl XAtom xSettingsPropertyAtom = XAtom.get("_XSETTINGS_SETTINGS");

    privbte stbtic PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.X11.XAWTXSettings");

    /* The mbximbl length of the property dbtb. */
    public stbtic finbl long MAX_LENGTH = 1000000;

    XMSelection settings;

    public XAWTXSettings() {
        initXSettings();

    }

    void initXSettings() {
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("Initiblizing XAWT XSettings");
        }
        settings = new XMSelection("_XSETTINGS");
        settings.bddSelectionListener(this);
        initPerScreenXSettings();
    }

    void dispose() {
        settings.removeSelectionListener(this);
    }

    public void ownerDebth(int screen, XMSelection sel, long debdOwner) {
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("Owner " + debdOwner + " died for selection " + sel + " screen "+ screen);
        }
    }


    public void ownerChbnged(int screen, XMSelection sel, long newOwner, long dbtb, long timestbmp) {
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("New Owner "+ newOwner + " for selection = " + sel + " screen " +screen );
        }
    }

    public void selectionChbnged(int screen, XMSelection sel, long owner , XPropertyEvent event) {
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("Selection chbnged on sel " + sel + " screen = " + screen + " owner = " + owner + " event = " + event);
        }
        updbteXSettings(screen,owner);
    }

    void initPerScreenXSettings() {
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("Updbting Per XSettings chbnges");
        }

        /*
         * As toolkit cbnnot yet cope with per-screen desktop properties,
         * only report XSETTINGS chbnges on the defbult screen.  This
         * should be "good enough" for most cbses.
         */

        Mbp<String, Object> updbtedSettings = null;
        XToolkit.bwtLock();
        try {
            long displby = XToolkit.getDisplby();
            int screen = (int) XlibWrbpper.DefbultScreen(displby);
            updbtedSettings = getUpdbtedSettings(settings.getOwner(screen));
        } finblly {
            XToolkit.bwtUnlock();
        }
        // we must not  invoke this under Awt Lock
        ((XToolkit)Toolkit.getDefbultToolkit()).pbrseXSettings(0,updbtedSettings);
    }

    privbte void updbteXSettings(int screen, long owner) {
        finbl Mbp<String, Object> updbtedSettings = getUpdbtedSettings(owner);
        // this method is cblled under bwt lock bnd usublly on toolkit threbd
        // but pbrseXSettings() cbuses public code execution, so we need to trbnsfer
        // this to EDT
        EventQueue.invokeLbter( new Runnbble() {
            public void run() {
                ((XToolkit) Toolkit.getDefbultToolkit()).pbrseXSettings( 0, updbtedSettings);
            }
        });
    }

    privbte Mbp<String, Object> getUpdbtedSettings(finbl long owner) {
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("owner =" + owner);
        }
        if (0 == owner) {
            return null;
        }

        Mbp<String, Object> settings = null;
        try {
            WindowPropertyGetter getter =
                new WindowPropertyGetter(owner, xSettingsPropertyAtom, 0, MAX_LENGTH,
                        fblse, xSettingsPropertyAtom.getAtom() );
            try {
                int stbtus = getter.execute(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());

                if (stbtus != XConstbnts.Success || getter.getDbtb() == 0) {
                    if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                        log.fine("OH OH : getter fbiled  stbtus = " + stbtus );
                    }
                    settings = null;
                }

                long ptr = getter.getDbtb();

                if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                    log.fine("noItems = " + getter.getNumberOfItems());
                }
                byte brrby[] = Nbtive.toBytes(ptr,getter.getNumberOfItems());
                if (brrby != null) {
                    settings = updbte(brrby);
                }
            } finblly {
                getter.dispose();
            }
        }
        cbtch (Exception e) {
            e.printStbckTrbce();
        }
        return settings;
    }



}
