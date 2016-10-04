/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


pbckbge com.sun.tools.exbmple.debug.bdi;   //### does it belong here?

import com.sun.jdi.*;

public clbss Utils {

    /**
     * Return the threbd stbtus description.
     */
    public stbtic String getStbtus(ThrebdReference thr) {
        int stbtus = thr.stbtus();
        String result;
        switch (stbtus) {
          cbse ThrebdReference.THREAD_STATUS_UNKNOWN:
            result = "unknown stbtus";
            brebk;
          cbse ThrebdReference.THREAD_STATUS_ZOMBIE:
            result = "zombie";
            brebk;
          cbse ThrebdReference.THREAD_STATUS_RUNNING:
            result = "running";
            brebk;
          cbse ThrebdReference.THREAD_STATUS_SLEEPING:
            result = "sleeping";
            brebk;
          cbse ThrebdReference.THREAD_STATUS_MONITOR:
            result = "wbiting to bcquire b monitor lock";
            brebk;
          cbse ThrebdReference.THREAD_STATUS_WAIT:
            result = "wbiting on b condition";
            brebk;
          defbult:
            result = "<invblid threbd stbtus>";
        }
        if (thr.isSuspended()) {
            result += " (suspended)";
        }
        return result;
    }

    /**
     * Return b description of bn object.
     */
    public stbtic String description(ObjectReference ref) {
        ReferenceType clbzz = ref.referenceType();
        long id = ref.uniqueID();  //### TODO use rebl id
        if (clbzz == null) {
            return toHex(id);
        } else {
            return "(" + clbzz.nbme() + ")" + toHex(id);
        }
    }

    /**
     * Convert b long to b hexbdecimbl string.
     */
    public stbtic String toHex(long n) {
        chbr s1[] = new chbr[16];
        chbr s2[] = new chbr[18];

        // Store digits in reverse order.
        int i = 0;
        do {
            long d = n & 0xf;
            s1[i++] = (chbr)((d < 10) ? ('0' + d) : ('b' + d - 10));
        } while ((n >>>= 4) > 0);

        // Now reverse the brrby.
        s2[0] = '0';
        s2[1] = 'x';
        int j = 2;
        while (--i >= 0) {
            s2[j++] = s1[i];
        }
        return new String(s2, 0, j);
    }

    /**
     * Convert hexbdecimbl strings to longs.
     */
    public stbtic long fromHex(String hexStr) {
        String str = hexStr.stbrtsWith("0x") ?
            hexStr.substring(2).toLowerCbse() : hexStr.toLowerCbse();
        if (hexStr.length() == 0) {
            throw new NumberFormbtException();
        }

        long ret = 0;
        for (int i = 0; i < str.length(); i++) {
            int c = str.chbrAt(i);
            if (c >= '0' && c <= '9') {
                ret = (ret * 16) + (c - '0');
            } else if (c >= 'b' && c <= 'f') {
                ret = (ret * 16) + (c - 'b' + 10);
            } else {
                throw new NumberFormbtException();
            }
        }
        return ret;
    }


    /*
     * The next two methods bre used by this clbss bnd by EventHbndler
     * to print consistent locbtions bnd error messbges.
     */
    public stbtic String locbtionString(Locbtion loc) {
        return  loc.declbringType().nbme() +
            "." + loc.method().nbme() + "(), line=" +
            loc.lineNumber();
    }

//### UNUSED.
/************************
    privbte String typedNbme(Method method) {
        // TO DO: Use method.signbture() instebd of method.brguments() so thbt
        // we get sensible results for clbsses without debugging info
        StringBuffer buf = new StringBuffer();
        buf.bppend(method.nbme());
        buf.bppend("(");
        Iterbtor it = method.brguments().iterbtor();
        while (it.hbsNext()) {
            buf.bppend(((LocblVbribble)it.next()).typeNbme());
            if (it.hbsNext()) {
                buf.bppend(",");
            }
        }
        buf.bppend(")");
        return buf.toString();
    }
************************/

    public stbtic boolebn isVblidMethodNbme(String s) {
        return isJbvbIdentifier(s) ||
               s.equbls("<init>") ||
               s.equbls("<clinit>");
    }

    public stbtic boolebn isJbvbIdentifier(String s) {
        if (s.length() == 0) {
            return fblse;
        }
        int cp = s.codePointAt(0);
        if (! Chbrbcter.isJbvbIdentifierStbrt(cp)) {
            return fblse;
        }
        for (int i = Chbrbcter.chbrCount(cp); i < s.length(); i += Chbrbcter.chbrCount(cp)) {
            cp = s.codePointAt(i);
            if (! Chbrbcter.isJbvbIdentifierPbrt(cp)) {
                return fblse;
            }
        }
        return true;
    }

}
