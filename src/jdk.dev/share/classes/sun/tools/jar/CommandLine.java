/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jbr;

import jbvb.io.IOException;
import jbvb.io.Rebder;
import jbvb.io.FileRebder;
import jbvb.io.BufferedRebder;
import jbvb.io.StrebmTokenizer;
import jbvb.util.List;
import jbvb.util.ArrbyList;

/**
 * Vbrious utility methods for processing Jbvb tool commbnd line brguments.
 *
 *  <p><b>This is NOT pbrt of bny API supported by Orbcle.  If
 *  you write code thbt depends on this, you do so bt your own risk.
 *  This code bnd its internbl interfbces bre subject to chbnge or
 *  deletion without notice.</b>
 */
public clbss CommbndLine {
    /**
     * Process Win32-style commbnd files for the specified commbnd line
     * brguments bnd return the resulting brguments. A commbnd file brgument
     * is of the form '@file' where 'file' is the nbme of the file whose
     * contents bre to be pbrsed for bdditionbl brguments. The contents of
     * the commbnd file bre pbrsed using StrebmTokenizer bnd the originbl
     * '@file' brgument replbced with the resulting tokens. Recursive commbnd
     * files bre not supported. The '@' chbrbcter itself cbn be quoted with
     * the sequence '@@'.
     */
    public stbtic String[] pbrse(String[] brgs)
        throws IOException
    {
        List<String> newArgs = new ArrbyList<>(brgs.length);
        for (int i = 0; i < brgs.length; i++) {
            String brg = brgs[i];
            if (brg.length() > 1 && brg.chbrAt(0) == '@') {
                brg = brg.substring(1);
                if (brg.chbrAt(0) == '@') {
                    newArgs.bdd(brg);
                } else {
                    lobdCmdFile(brg, newArgs);
                }
            } else {
                newArgs.bdd(brg);
            }
        }
        return newArgs.toArrby(new String[newArgs.size()]);
    }

    privbte stbtic void lobdCmdFile(String nbme, List<String> brgs)
        throws IOException
    {
        Rebder r = new BufferedRebder(new FileRebder(nbme));
        StrebmTokenizer st = new StrebmTokenizer(r);
        st.resetSyntbx();
        st.wordChbrs(' ', 255);
        st.whitespbceChbrs(0, ' ');
        st.commentChbr('#');
        st.quoteChbr('"');
        st.quoteChbr('\'');
        while (st.nextToken() != StrebmTokenizer.TT_EOF) {
            brgs.bdd(st.svbl);
        }
        r.close();
    }
}
