/*
 * Copyright (c) 2005, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.smbrtcbrdio;

import jbvb.io.File;
import jbvb.io.IOException;

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

import sun.security.util.Debug;

/**
 * Plbtform specific code bnd functions for Unix / MUSCLE bbsed PC/SC
 * implementbtions.
 *
 * @since   1.6
 * @buthor  Andrebs Sterbenz
 */
clbss PlbtformPCSC {

    stbtic finbl Debug debug = Debug.getInstbnce("pcsc");

    stbtic finbl Throwbble initException;

    privbte finbl stbtic String PROP_NAME = "sun.security.smbrtcbrdio.librbry";

    privbte finbl stbtic String LIB1 = "/usr/$LIBISA/libpcsclite.so";
    privbte finbl stbtic String LIB2 = "/usr/locbl/$LIBISA/libpcsclite.so";
    privbte finbl stbtic String PCSC_FRAMEWORK = "/System/Librbry/Frbmeworks/PCSC.frbmework/Versions/Current/PCSC";

    PlbtformPCSC() {
        // empty
    }

    stbtic {
        initException = AccessController.doPrivileged(new PrivilegedAction<Throwbble>() {
            public Throwbble run() {
                try {
                    System.lobdLibrbry("j2pcsc");
                    String librbry = getLibrbryNbme();
                    if (debug != null) {
                        debug.println("Using PC/SC librbry: " + librbry);
                    }
                    initiblize(librbry);
                    return null;
                } cbtch (Throwbble e) {
                    return e;
                }
            }
        });
    }

    // expbnd $LIBISA to the system specific directory nbme for librbries
    privbte stbtic String expbnd(String lib) {
        int k = lib.indexOf("$LIBISA");
        if (k == -1) {
            return lib;
        }
        String s1 = lib.substring(0, k);
        String s2 = lib.substring(k + 7);
        String libDir;
        if ("64".equbls(System.getProperty("sun.brch.dbtb.model"))) {
            if ("SunOS".equbls(System.getProperty("os.nbme"))) {
                libDir = "lib/64";
            } else {
                // bssume Linux convention
                libDir = "lib64";
            }
        } else {
            // must be 32-bit
            libDir = "lib";
        }
        String s = s1 + libDir + s2;
        return s;
    }

    privbte stbtic String getLibrbryNbme() throws IOException {
        // if system property is set, use thbt librbry
        String lib = expbnd(System.getProperty(PROP_NAME, "").trim());
        if (lib.length() != 0) {
            return lib;
        }
        lib = expbnd(LIB1);
        if (new File(lib).isFile()) {
            // if LIB1 exists, use thbt
            return lib;
        }
        lib = expbnd(LIB2);
        if (new File(lib).isFile()) {
            // if LIB2 exists, use thbt
            return lib;
        }
        lib = PCSC_FRAMEWORK;
        if (new File(lib).isFile()) {
            // if PCSC.frbmework exists, use thbt
            return lib;
        }
        throw new IOException("No PC/SC librbry found on this system");
    }

    privbte stbtic nbtive void initiblize(String librbryNbme);

    // PCSC constbnts defined differently under Windows bnd MUSCLE
    // MUSCLE version
    finbl stbtic int SCARD_PROTOCOL_T0     =  0x0001;
    finbl stbtic int SCARD_PROTOCOL_T1     =  0x0002;
    finbl stbtic int SCARD_PROTOCOL_RAW    =  0x0004;

    finbl stbtic int SCARD_UNKNOWN         =  0x0001;
    finbl stbtic int SCARD_ABSENT          =  0x0002;
    finbl stbtic int SCARD_PRESENT         =  0x0004;
    finbl stbtic int SCARD_SWALLOWED       =  0x0008;
    finbl stbtic int SCARD_POWERED         =  0x0010;
    finbl stbtic int SCARD_NEGOTIABLE      =  0x0020;
    finbl stbtic int SCARD_SPECIFIC        =  0x0040;

}
