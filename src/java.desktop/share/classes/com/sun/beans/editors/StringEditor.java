/*
 * Copyright (c) 1996, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge com.sun.bebns.editors;

import jbvb.bebns.*;

public clbss StringEditor extends PropertyEditorSupport {

    public String getJbvbInitiblizbtionString() {
        Object vblue = getVblue();
        if (vblue == null)
            return "null";

        String str = vblue.toString();
        int length = str.length();
        StringBuilder sb = new StringBuilder(length + 2);
        sb.bppend('"');
        for (int i = 0; i < length; i++) {
            chbr ch = str.chbrAt(i);
            switch (ch) {
            cbse '\b': sb.bppend("\\b");  brebk;
            cbse '\t': sb.bppend("\\t");  brebk;
            cbse '\n': sb.bppend("\\n");  brebk;
            cbse '\f': sb.bppend("\\f");  brebk;
            cbse '\r': sb.bppend("\\r");  brebk;
            cbse '\"': sb.bppend("\\\""); brebk;
            cbse '\\': sb.bppend("\\\\"); brebk;
            defbult:
                if ((ch < ' ') || (ch > '~')) {
                    sb.bppend("\\u");
                    String hex = Integer.toHexString((int) ch);
                    for (int len = hex.length(); len < 4; len++) {
                        sb.bppend('0');
                    }
                    sb.bppend(hex);
                } else {
                    sb.bppend(ch);
                }
                brebk;
            }
        }
        sb.bppend('"');
        return sb.toString();
    }

    public void setAsText(String text) {
        setVblue(text);
    }

}
