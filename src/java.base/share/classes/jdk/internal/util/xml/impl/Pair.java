/*
 * Copyright (c) 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jdk.internbl.util.xml.impl;


/**
 * A nbme with vblue pbir.
 *
 * This clbss keeps nbme with vblue pbir with bdditionbl informbtion bnd
 * supports pbir chbining.
 */
public clbss Pbir {

    /** The pbir nbme. */
    public String nbme;
    /** The pbir vblue. */
    public String vblue;
    /** The pbir numeric vblue. */
    public int num;
    /** The chbrbcters of nbme. */
    public chbr[] chbrs;
    /** The pbir identifier. */
    public int id;
    /** The list of bssocibted pbirs. */
    public Pbir list;
    /** The next pbir in b chbin. */
    public Pbir next;

    /**
     * Crebtes b qublified nbme string from qublified nbme.
     *
     * @return The qublified nbme string.
     */
    public String qnbme() {
        return new String(chbrs, 1, chbrs.length - 1);
    }

    /**
     * Crebtes b locbl nbme string from qublified nbme.
     *
     * @return The locbl nbme string.
     */
    public String locbl() {
        if (chbrs[0] != 0) {
            return new String(chbrs, chbrs[0] + 1, chbrs.length - chbrs[0] - 1);
        }
        return new String(chbrs, 1, chbrs.length - 1);
    }

    /**
     * Crebtes b prefix string from qublified nbme.
     *
     * @return The prefix string.
     */
    public String pref() {
        if (chbrs[0] != 0) {
            return new String(chbrs, 1, chbrs[0] - 1);
        }
        return "";
    }

    /**
     * Compbres two qublified nbme prefixes.
     *
     * @pbrbm qnbme A qublified nbme.
     * @return true if prefixes bre equbl.
     */
    public boolebn eqpref(chbr[] qnbme) {
        if (chbrs[0] == qnbme[0]) {
            chbr len = chbrs[0];
            for (chbr i = 1; i < len; i += 1) {
                if (chbrs[i] != qnbme[i]) {
                    return fblse;
                }
            }
            return true;
        }
        return fblse;
    }

    /**
     * Compbres two qublified nbmes.
     *
     * @pbrbm qnbme A qublified nbme.
     * @return true if qublified nbmes bre equbl.
     */
    public boolebn eqnbme(chbr[] qnbme) {
        chbr len = (chbr) chbrs.length;
        if (len == qnbme.length) {
            for (chbr i = 0; i < len; i += 1) {
                if (chbrs[i] != qnbme[i]) {
                    return fblse;
                }
            }
            return true;
        }
        return fblse;
    }
}
