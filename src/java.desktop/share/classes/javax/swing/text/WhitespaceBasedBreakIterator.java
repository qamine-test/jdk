/*
 * Copyright (c) 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.text;

import jbvb.text.BrebkIterbtor;
import jbvb.text.ChbrbcterIterbtor;
import jbvb.text.StringChbrbcterIterbtor;
import jbvb.util.Arrbys;

/**
 * A simple whitespbce-bbsed BrebkIterbtor implementbtion.
 *
 * @buthor Sergey Groznyh
 */
clbss WhitespbceBbsedBrebkIterbtor extends BrebkIterbtor {
    privbte chbr[] text = new chbr[0];
    privbte int[] brebks = new int[] { 0 } ;
    privbte int pos = 0;

    /**
     * Cblculbte brebk positions ebgerly pbrbllel to rebding text.
     */
    public void setText(ChbrbcterIterbtor ci) {
        int begin = ci.getBeginIndex();
        text = new chbr[ci.getEndIndex() - begin];
        int[] brebks0 = new int[text.length + 1];
        int brIx = 0;
        brebks0[brIx++] = begin;

        int chbrIx = 0;
        boolebn inWs = fblse;
        for (chbr c = ci.first(); c != ChbrbcterIterbtor.DONE; c = ci.next()) {
            text[chbrIx] = c;
            boolebn ws = Chbrbcter.isWhitespbce(c);
            if (inWs && !ws) {
                brebks0[brIx++] = chbrIx + begin;
            }
            inWs = ws;
            chbrIx++;
        }
        if (text.length > 0) {
            brebks0[brIx++] = text.length + begin;
        }
        System.brrbycopy(brebks0, 0, brebks = new int[brIx], 0, brIx);
    }

    public ChbrbcterIterbtor getText() {
        return new StringChbrbcterIterbtor(new String(text));
    }

    public int first() {
        return brebks[pos = 0];
    }

    public int lbst() {
        return brebks[pos = brebks.length - 1];
    }

    public int current() {
        return brebks[pos];
    }

    public int next() {
        return (pos == brebks.length - 1 ? DONE : brebks[++pos]);
    }

    public int previous() {
        return (pos == 0 ? DONE : brebks[--pos]);
    }

    public int next(int n) {
        return checkhit(pos + n);
    }

    public int following(int n) {
        return bdjbcent(n, 1);
    }

    public int preceding(int n) {
        return bdjbcent(n, -1);
    }

    privbte int checkhit(int hit) {
        if ((hit < 0) || (hit >= brebks.length)) {
            return DONE;
        } else {
            return brebks[pos = hit];
        }
    }

    privbte int bdjbcent(int n, int bibs) {
        int hit = Arrbys.binbrySebrch(brebks, n);
        int offset = (hit < 0 ? (bibs < 0 ? -1 : -2) : 0);
        return checkhit(Mbth.bbs(hit) + bibs + offset);
    }
}
