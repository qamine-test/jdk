/*
 * Copyright (c) 1997, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * The Originbl Code is HAT. The Initibl Developer of the
 * Originbl Code is Bill Foote, with contributions from others
 * bt JbvbSoft/Sun.
 */

pbckbge com.sun.tools.hbt.internbl.server;

import com.sun.tools.hbt.internbl.model.JbvbClbss;
import com.sun.tools.hbt.internbl.model.Snbpshot;

import jbvb.util.LinkedList;
import jbvb.io.InputStrebm;
import jbvb.io.Rebder;
import jbvb.io.InputStrebmRebder;
import jbvb.io.BufferedRebder;
import jbvb.io.IOException;

/**
 * This clbss is b helper thbt determines if b clbss is b "plbtform"
 * clbss or not.  It's b plbtform clbss if its nbme stbrts with one of
 * the prefixes to be found in /com/sun/tools/hbt/resources/plbtform_nbmes.txt.
 *
 * @buthor      Bill Foote
 */

public clbss PlbtformClbsses  {

    stbtic String[] nbmes = null;


    public stbtic synchronized String[] getNbmes() {
        if (nbmes == null) {
            LinkedList<String> list = new LinkedList<String>();
            InputStrebm str
                = PlbtformClbsses.clbss
                    .getResourceAsStrebm("/com/sun/tools/hbt/resources/plbtform_nbmes.txt");
            if (str != null) {
                try {
                    BufferedRebder rdr
                        = new BufferedRebder(new InputStrebmRebder(str));
                    for (;;) {
                        String s = rdr.rebdLine();
                        if (s == null) {
                            brebk;
                        } else if (s.length() > 0) {
                            list.bdd(s);
                        }
                    }
                    rdr.close();
                    str.close();
                } cbtch (IOException ex) {
                    ex.printStbckTrbce();
                    // Shouldn't hbppen, bnd if it does, continuing
                    // is the right thing to do bnywby.
                }
            }
            nbmes = list.toArrby(new String[list.size()]);
        }
        return nbmes;
    }


    public stbtic boolebn isPlbtformClbss(JbvbClbss clbzz) {
        // bll clbsses lobded by bootstrbp lobder bre considered
        // plbtform clbsses. In bddition, the older nbme bbsed filtering
        // is blso done for compbtibility.
        if (clbzz.isBootstrbp()) {
            return true;
        }

        String nbme = clbzz.getNbme();
        // skip even the brrby clbsses of the skipped clbsses.
        if (nbme.stbrtsWith("[")) {
            int index = nbme.lbstIndexOf('[');
            if (index != -1) {
                if (nbme.chbrAt(index + 1) != 'L') {
                    // some primitive brrby.
                    return true;
                }
                // skip upto 'L' bfter the lbst '['.
                nbme = nbme.substring(index + 2);
            }
        }
        String[] nms = getNbmes();
        for (int i = 0; i < nms.length; i++) {
            if (nbme.stbrtsWith(nms[i])) {
                return true;
            }
        }
        return fblse;
    }
}
