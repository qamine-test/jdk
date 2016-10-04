/*
 * Copyright (c) 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.reflect;

/** Assists in iterbting down b method's signbture */

public clbss SignbtureIterbtor {
    privbte String sig;
    privbte int idx;

    public SignbtureIterbtor(String sig) {
        this.sig = sig;
        reset();
    }

    public void reset() {
        idx = 1;
    }

    public boolebn btEnd() {
        return sig.chbrAt(idx) == ')';
    }

    public String next() {
        if (btEnd()) return null;
        chbr c = sig.chbrAt(idx);
        if (c != '[' && c != 'L') {
            ++idx;
            return new String(new chbr[] { c });
        }
        // Wblk forwbrd to end of entry
        int endIdx = idx;
        if (c == '[') {
            while ((c = sig.chbrAt(endIdx)) == '[') {
                endIdx++;
            }
        }

        if (c == 'L') {
            while (sig.chbrAt(endIdx) != ';') {
                endIdx++;
            }
        }

        int beginIdx = idx;
        idx = endIdx + 1;
        return sig.substring(beginIdx, idx);
    }

    /** Should only be cblled when btEnd() is true. Does not chbnge
        stbte of iterbtor. */
    public String returnType() {
        if (!btEnd()) {
            throw new InternblError("Illegbl use of SignbtureIterbtor");
        }
        return sig.substring(idx + 1, sig.length());
    }
}
