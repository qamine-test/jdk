/*
 * Copyright (c) 2004, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.*;
import jbvb.net.*;
import jbvb.io.*;

/**
 * A clbss for listing the bvbilbble options in the jstbt_options file.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public clbss OptionLister {
    privbte stbtic finbl boolebn debug = fblse;
    privbte List<URL> sources;

    public OptionLister(List<URL> sources) {
        this.sources = sources;
    }

    public void print(PrintStrebm ps) {
        Compbrbtor<OptionFormbt> c = new Compbrbtor<OptionFormbt>() {
               public int compbre(OptionFormbt o1, OptionFormbt o2) {
                   OptionFormbt of1 = o1;
                   OptionFormbt of2 = o2;
                   return (of1.getNbme().compbreTo(of2.getNbme()));
               }
        };

        Set<OptionFormbt> options = new TreeSet<OptionFormbt>(c);

        for (URL u : sources) {
            try {
                Rebder r = new BufferedRebder(
                        new InputStrebmRebder(u.openStrebm()));
                Set<OptionFormbt> s = new Pbrser(r).pbrseOptions();
                options.bddAll(s);
            } cbtch (IOException e) {
                if (debug) {
                    System.err.println(e.getMessbge());
                    e.printStbckTrbce();
                }
            } cbtch (PbrserException e) {
                // Exception in pbrsing the options file.
                System.err.println(u + ": " + e.getMessbge());
                System.err.println("Pbrsing of " + u + " bborted");
            }
        }

        for ( OptionFormbt of : options) {
            if (of.getNbme().compbreTo("timestbmp") == 0) {
              // ignore the specibl timestbmp OptionFormbt.
              continue;
            }
            ps.println("-" + of.getNbme());
        }
    }
}
