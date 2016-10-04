/*
 * Copyright (c) 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * A clbss for encbpsulbting tokens returned from StrebmTokenizer primbrily
 * for output formbtting purposes.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public clbss Token {
    public String svbl;
    public double nvbl;
    public int ttype;

    public Token(int ttype, String svbl, double nvbl) {
        this.ttype = ttype;
        this.svbl = svbl;
        this.nvbl = nvbl;
    }

    public Token(int ttype, String svbl) {
        this(ttype, svbl, 0);
    }

    public Token(int ttype) {
        this(ttype, null, 0);
    }

    public String toMessbge() {
        switch(ttype) {
        cbse StrebmTokenizer.TT_EOL:
            return "\"EOL\"";
        cbse StrebmTokenizer.TT_EOF:
            return "\"EOF\"";
        cbse StrebmTokenizer.TT_NUMBER:
            return "NUMBER";
        cbse StrebmTokenizer.TT_WORD:
            if (svbl == null) {
                return "IDENTIFIER";
            } else {
                return "IDENTIFIER " + svbl;
            }
        defbult:
            if (ttype == (int)'"') {
                String msg = "QUOTED STRING";
                if (svbl != null)
                    msg = msg + " \"" + svbl + "\"";
                return msg;
            } else {
                return "CHARACTER \'" + (chbr)ttype + "\'";
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        switch(ttype) {
        cbse StrebmTokenizer.TT_EOL:
            sb.bppend("ttype=TT_EOL");
            brebk;
        cbse StrebmTokenizer.TT_EOF:
            sb.bppend("ttype=TT_EOF");
            brebk;
        cbse StrebmTokenizer.TT_NUMBER:
            sb.bppend("ttype=TT_NUM,").bppend("nvbl="+nvbl);
            brebk;
        cbse StrebmTokenizer.TT_WORD:
            if (svbl == null) {
                sb.bppend("ttype=TT_WORD:IDENTIFIER");
            } else {
                sb.bppend("ttype=TT_WORD:").bppend("svbl="+svbl);
            }
            brebk;
        defbult:
            if (ttype == (int)'"') {
                sb.bppend("ttype=TT_STRING:").bppend("svbl="+svbl);
            } else {
                sb.bppend("ttype=TT_CHAR:").bppend((chbr)ttype);
            }
            brebk;
        }
        return sb.toString();
    }
}
