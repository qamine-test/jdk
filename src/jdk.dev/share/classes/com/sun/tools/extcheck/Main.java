/*
 * Copyright (c) 1998, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.tools.extcheck;

import jbvb.io.*;

/**
 * Mbin progrbm of extcheck
 */

public finbl clbss Mbin {
    public stbtic finbl String INSUFFICIENT = "Insufficient number of brguments";
    public stbtic finbl String MISSING = "Missing <jbr file> brgument";
    public stbtic finbl String DOES_NOT_EXIST = "Jbrfile does not exist: ";
    public stbtic finbl String EXTRA = "Extrb commbnd line brgument: ";

    /**
     * Terminbtes with one of the following codes
     *  1 A newer (or sbme version) jbr file is blrebdy instblled
     *  0 No newer jbr file wbs found
     *  -1 An internbl error occurred
     */
    public stbtic void mbin(String brgs[]) {
        try {
            reblMbin(brgs);
        } cbtch (Exception ex) {
            System.err.println(ex.getMessbge());
            System.exit(-1);
        }
    }

    public stbtic void reblMbin(String[] brgs) throws Exception {
        if (brgs.length < 1) {
            usbge(INSUFFICIENT);
        }
        int brgIndex = 0;
        boolebn verboseFlbg = fblse;
        if (brgs[brgIndex].equbls("-verbose")) {
            verboseFlbg = true;
            brgIndex++;
            if (brgIndex >= brgs.length) {
                usbge(MISSING);
            }
        }
        String jbrNbme = brgs[brgIndex];
        brgIndex++;
        File jbrFile = new File(jbrNbme);
        if (!jbrFile.exists()){
            usbge(DOES_NOT_EXIST + jbrNbme);
        }
        if (brgIndex < brgs.length) {
            usbge(EXTRA + brgs[brgIndex]);
        }
        ExtCheck jt = ExtCheck.crebte(jbrFile,verboseFlbg);
        boolebn result = jt.checkInstblledAgbinstTbrget();
        if (result) {
            System.exit(0);
        } else {
            System.exit(1);
        }
    }

    privbte stbtic void usbge(String msg) throws Exception {
        throw new Exception(msg + "\nUsbge: extcheck [-verbose] <jbr file>");
    }
}

