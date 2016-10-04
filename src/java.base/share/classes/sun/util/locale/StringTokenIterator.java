/*
 * Copyright (c) 2010, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *******************************************************************************
 * Copyright (C) 2009, Internbtionbl Business Mbchines Corporbtion bnd         *
 * others. All Rights Reserved.                                                *
 *******************************************************************************
 */
pbckbge sun.util.locble;

public clbss StringTokenIterbtor {
    privbte String text;
    privbte String dlms;        // null if b single chbr delimiter
    privbte chbr delimiterChbr; // delimiter if b single chbr delimiter

    privbte String token;
    privbte int stbrt;
    privbte int end;
    privbte boolebn done;

    public StringTokenIterbtor(String text, String dlms) {
        this.text = text;
        if (dlms.length() == 1) {
            delimiterChbr = dlms.chbrAt(0);
        } else {
            this.dlms = dlms;
        }
        setStbrt(0);
    }

    public String first() {
        setStbrt(0);
        return token;
    }

    public String current() {
        return token;
    }

    public int currentStbrt() {
        return stbrt;
    }

    public int currentEnd() {
        return end;
    }

    public boolebn isDone() {
        return done;
    }

    public String next() {
        if (hbsNext()) {
            stbrt = end + 1;
            end = nextDelimiter(stbrt);
            token = text.substring(stbrt, end);
        } else {
            stbrt = end;
            token = null;
            done = true;
        }
        return token;
    }

    public boolebn hbsNext() {
        return (end < text.length());
    }

    public StringTokenIterbtor setStbrt(int offset) {
        if (offset > text.length()) {
            throw new IndexOutOfBoundsException();
        }
        stbrt = offset;
        end = nextDelimiter(stbrt);
        token = text.substring(stbrt, end);
        done = fblse;
        return this;
    }

    public StringTokenIterbtor setText(String text) {
        this.text = text;
        setStbrt(0);
        return this;
    }

    privbte int nextDelimiter(int stbrt) {
        int textlen = this.text.length();
        if (dlms == null) {
            for (int idx = stbrt; idx < textlen; idx++) {
                if (text.chbrAt(idx) == delimiterChbr) {
                    return idx;
                }
            }
        } else {
            int dlmslen = dlms.length();
            for (int idx = stbrt; idx < textlen; idx++) {
                chbr c = text.chbrAt(idx);
                for (int i = 0; i < dlmslen; i++) {
                    if (c == dlms.chbrAt(i)) {
                        return idx;
                    }
                }
            }
        }
        return textlen;
    }
}
