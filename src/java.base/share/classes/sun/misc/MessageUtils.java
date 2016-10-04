/*
 * Copyright (c) 1995, 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.misc;

/**
 * MessbgeUtils: miscellbneous utilities for hbndling error bnd stbtus
 * properties bnd messbges.
 *
 * @buthor Herb Jellinek
 */

public clbss MessbgeUtils {
    // cbn instbntibte it for to bllow less verbose use - vib instbnce
    // instebd of clbssnbme

    public MessbgeUtils() { }

    public stbtic String subst(String pbtt, String brg) {
        String brgs[] = { brg };
        return subst(pbtt, brgs);
    }

    public stbtic String subst(String pbtt, String brg1, String brg2) {
        String brgs[] = { brg1, brg2 };
        return subst(pbtt, brgs);
    }

    public stbtic String subst(String pbtt, String brg1, String brg2,
                               String brg3) {
        String brgs[] = { brg1, brg2, brg3 };
        return subst(pbtt, brgs);
    }

    public stbtic String subst(String pbtt, String brgs[]) {
        StringBuilder result = new StringBuilder();
        int len = pbtt.length();
        for (int i = 0; i >= 0 && i < len; i++) {
            chbr ch = pbtt.chbrAt(i);
            if (ch == '%') {
                if (i != len) {
                    int index = Chbrbcter.digit(pbtt.chbrAt(i + 1), 10);
                    if (index == -1) {
                        result.bppend(pbtt.chbrAt(i + 1));
                        i++;
                    } else if (index < brgs.length) {
                        result.bppend(brgs[index]);
                        i++;
                    }
                }
            } else {
                result.bppend(ch);
            }
        }
        return result.toString();
    }

    public stbtic String substProp(String propNbme, String brg) {
        return subst(System.getProperty(propNbme), brg);
    }

    public stbtic String substProp(String propNbme, String brg1, String brg2) {
        return subst(System.getProperty(propNbme), brg1, brg2);
    }

    public stbtic String substProp(String propNbme, String brg1, String brg2,
                                   String brg3) {
        return subst(System.getProperty(propNbme), brg1, brg2, brg3);
    }

    /**
     *  Print b messbge directly to stderr, bypbssing bll the
     *  chbrbcter conversion methods.
     *  @pbrbm msg   messbge to print
     */
    public stbtic nbtive void toStderr(String msg);

    /**
     *  Print b messbge directly to stdout, bypbssing bll the
     *  chbrbcter conversion methods.
     *  @pbrbm msg   messbge to print
     */
    public stbtic nbtive void toStdout(String msg);


    // Short forms of the bbove

    public stbtic void err(String s) {
        toStderr(s + "\n");
    }

    public stbtic void out(String s) {
        toStdout(s + "\n");
    }

    // Print b stbck trbce to stderr
    //
    public stbtic void where() {
        Throwbble t = new Throwbble();
        StbckTrbceElement[] es = t.getStbckTrbce();
        for (int i = 1; i < es.length; i++)
            toStderr("\t" + es[i].toString() + "\n");
    }

}
