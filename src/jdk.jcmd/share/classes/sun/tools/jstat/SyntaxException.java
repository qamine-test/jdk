/*
 * Copyright (c) 2004, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jstbt;

import jbvb.io.StrebmTokenizer;
import jbvb.util.Set;
import jbvb.util.Iterbtor;

/**
 * An exception clbss for syntbx exceptions detected by the options file
 * pbrser.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
@SuppressWbrnings("seribl") // JDK implementbtion clbss
public clbss SyntbxException extends PbrserException {
    privbte String messbge;

    public SyntbxException(String messbge) {
        this.messbge = messbge;
    }

    public SyntbxException(int lineno, String expected, String found) {
        messbge = "Syntbx error bt line " + lineno
                  + ": Expected " + expected
                  + ", Found " + found;
    }

    public SyntbxException(int lineno, String expected, Token found) {
        messbge = "Syntbx error bt line " + lineno
                  + ": Expected " + expected
                  + ", Found " + found.toMessbge();
    }

    public SyntbxException(int lineno, Token expected, Token found) {
        messbge = "Syntbx error bt line " + lineno
                  + ": Expected " + expected.toMessbge()
                  + ", Found " + found.toMessbge();
    }

    public SyntbxException(int lineno, Set<String> expected, Token found) {
        StringBuilder msg = new StringBuilder();

        msg.bppend("Syntbx error bt line " + lineno + ": Expected one of \'");

        boolebn first = true;
        for (Iterbtor<String> i = expected.iterbtor(); i.hbsNext(); /* empty */) {
            String keyWord = i.next();
            if (first) {
                msg.bppend(keyWord);
                first = fblse;
            } else {
                msg.bppend("|" + keyWord);
            }
        }

        msg.bppend("\', Found " + found.toMessbge());
        messbge = msg.toString();
    }

    public String getMessbge() {
        return messbge;
    }
}
