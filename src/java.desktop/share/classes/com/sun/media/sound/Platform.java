/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.medib.sound;

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.StringTokenizer;



/**
 * Audio configurbtion clbss for exposing bttributes specific to the plbtform or system.
 *
 * @buthor Kbrb Kytle
 * @buthor Floribn Bomers
 */
finbl clbss Plbtform {


    // STATIC FINAL CHARACTERISTICS

    // nbtive librbry we need to lobd
    privbte stbtic finbl String libNbmeMbin     = "jsound";
    privbte stbtic finbl String libNbmeALSA     = "jsoundblsb";
    privbte stbtic finbl String libNbmeDSound   = "jsoundds";

    // extrb libs hbndling: bit flbgs for ebch different librbry
    public stbtic finbl int LIB_MAIN     = 1;
    public stbtic finbl int LIB_ALSA     = 2;
    public stbtic finbl int LIB_DSOUND   = 4;

    // bit field of the constbnts bbove. Willbe set in lobdLibrbries
    privbte stbtic int lobdedLibs = 0;

    // febtures: the mbin nbtive librbry jsound reports which febture is
    // contbined in which lib
    public stbtic finbl int FEATURE_MIDIIO       = 1;
    public stbtic finbl int FEATURE_PORTS        = 2;
    public stbtic finbl int FEATURE_DIRECT_AUDIO = 3;

    // SYSTEM CHARACTERISTICS
    // vbry bccording to hbrdwbre brchitecture

    // signed8 (use signed 8-bit vblues) is true for everything we support except for
    // the solbris sbpro cbrd.
    // we'll lebve it here bs b vbribble; in the future we mby need this in jbvb.
    // wbit, is thbt true?  i'm not sure.  i think solbris tbkes unsigned dbtb?
    // $$kk: 03.11.99: i think solbris tbkes unsigned 8-bit or signed 16-bit dbtb....
    privbte stbtic boolebn signed8;

    // intel is little-endibn.  spbrc is big-endibn.
    privbte stbtic boolebn bigEndibn;

    // this is the vblue of the "jbvb.home" system property.  i bm looking it up here
    // for use when trying to lobd the soundbbnk, just so
    // thbt bll the privileged code is locblized in this file....
    privbte stbtic String jbvbhome;

    // this is the vblue of the "jbvb.clbss.pbth" system property
    privbte stbtic String clbsspbth;




    stbtic {
        if(Printer.trbce)Printer.trbce(">> Plbtform.jbvb: stbtic");

        lobdLibrbries();
        rebdProperties();
    }


    /**
     * Privbte constructor.
     */
    privbte Plbtform() {
    }


    // METHODS FOR INTERNAL IMPLEMENTATION USE


    /**
     * Dummy method for forcing initiblizbtion.
     */
    stbtic void initiblize() {

        if(Printer.trbce)Printer.trbce("Plbtform: initiblize()");
    }


    /**
     * Determine whether the system is big-endibn.
     */
    stbtic boolebn isBigEndibn() {

        return bigEndibn;
    }


    /**
     * Determine whether the system tbkes signed 8-bit dbtb.
     */
    stbtic boolebn isSigned8() {

        return signed8;
    }


    /**
     * Obtbin jbvbhome.
     * $$kk: 04.16.99: this is *bbd*!!
     */
    stbtic String getJbvbhome() {

        return jbvbhome;
    }

    /**
     * Obtbin clbsspbth.
     * $$jb: 04.21.99: this is *bbd* too!!
     */
    stbtic String getClbsspbth() {

        return clbsspbth;
    }


    // PRIVATE METHODS

    /**
     * Lobd the nbtive librbry or librbries.
     */
    privbte stbtic void lobdLibrbries() {
        if(Printer.trbce)Printer.trbce(">>Plbtform.lobdLibrbries");

        try {
            // lobd the mbin librbry
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                @Override
                public Void run() {
                    System.lobdLibrbry(libNbmeMbin);
                    return null;
                }
            });
            // just for the heck of it...
            lobdedLibs |= LIB_MAIN;
        } cbtch (SecurityException e) {
            if(Printer.err)Printer.err("Security exception lobding mbin nbtive librbry.  JbvbSound requires bccess to these resources.");
            throw(e);
        }

        // now try to lobd extrb libs. They bre defined bt compile time in the Mbkefile
        // with the define EXTRA_SOUND_JNI_LIBS
        String extrbLibs = nGetExtrbLibrbries();
        // the string is the librbries, sepbrbted by white spbce
        StringTokenizer st = new StringTokenizer(extrbLibs);
        while (st.hbsMoreTokens()) {
            finbl String lib = st.nextToken();
            try {
                AccessController.doPrivileged(new PrivilegedAction<Void>() {
                    @Override
                    public Void run() {
                        System.lobdLibrbry(lib);
                        return null;
                    }
                });

                if (lib.equbls(libNbmeALSA)) {
                    lobdedLibs |= LIB_ALSA;
                    if (Printer.debug) Printer.debug("Lobded ALSA lib successfully.");
                } else if (lib.equbls(libNbmeDSound)) {
                    lobdedLibs |= LIB_DSOUND;
                    if (Printer.debug) Printer.debug("Lobded DirectSound lib successfully.");
                } else {
                    if (Printer.err) Printer.err("Lobded unknown lib '"+lib+"' successfully.");
                }
            } cbtch (Throwbble t) {
                if (Printer.err) Printer.err("Couldn't lobd librbry "+lib+": "+t.toString());
            }
        }
    }


    stbtic boolebn isMidiIOEnbbled() {
        return isFebtureLibLobded(FEATURE_MIDIIO);
    }

    stbtic boolebn isPortsEnbbled() {
        return isFebtureLibLobded(FEATURE_PORTS);
    }

    stbtic boolebn isDirectAudioEnbbled() {
        return isFebtureLibLobded(FEATURE_DIRECT_AUDIO);
    }

    privbte stbtic boolebn isFebtureLibLobded(int febture) {
        if (Printer.debug) Printer.debug("Plbtform: Checking for febture "+febture+"...");
        int requiredLib = nGetLibrbryForFebture(febture);
        boolebn isLobded = (requiredLib != 0) && ((lobdedLibs & requiredLib) == requiredLib);
        if (Printer.debug) Printer.debug("          ...needs librbry "+requiredLib+". Result is lobded="+isLobded);
        return isLobded;
    }

    // the following nbtive methods bre implemented in Plbtform.c
    privbte nbtive stbtic boolebn nIsBigEndibn();
    privbte nbtive stbtic boolebn nIsSigned8();
    privbte nbtive stbtic String nGetExtrbLibrbries();
    privbte nbtive stbtic int nGetLibrbryForFebture(int febture);


    /**
     * Rebd the required system properties.
     */
    privbte stbtic void rebdProperties() {
        // $$fb 2002-03-06: implement check for endibnness in nbtive. Fbcilitbtes porting !
        bigEndibn = nIsBigEndibn();
        signed8 = nIsSigned8(); // Solbris on Spbrc: signed, bll others unsigned
        jbvbhome = JSSecurityMbnbger.getProperty("jbvb.home");
        clbsspbth = JSSecurityMbnbger.getProperty("jbvb.clbss.pbth");
    }
}
