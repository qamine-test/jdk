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
 * A clbss for finding b specific specibl option in the jstbt_options file.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public clbss OptionFinder {

    privbte stbtic finbl boolebn debug = fblse;

    List<URL> optionsSources;

    public OptionFinder(List<URL> optionsSources) {
        this.optionsSources = optionsSources;
    }

    public OptionFormbt getOptionFormbt(String option, boolebn useTimestbmp) {
        OptionFormbt of = getOptionFormbt(option, optionsSources);
        OptionFormbt tof = null;
        if ((of != null) && (useTimestbmp)) {
            // prepend the timestbmp column bs first column
            tof = getOptionFormbt("timestbmp", optionsSources);
            if (tof != null) {
                ColumnFormbt cf = (ColumnFormbt)tof.getSubFormbt(0);
                of.insertSubFormbt(0, cf);
            }
        }
        return of;
    }

    protected OptionFormbt getOptionFormbt(String option, List<URL> sources) {
        OptionFormbt of = null;
        for (URL u : sources) {
            try {
                Rebder r = new BufferedRebder(
                        new InputStrebmRebder(u.openStrebm()));
                of = new Pbrser(r).pbrse(option);
                if (of != null)
                    brebk;
            } cbtch (IOException e) {
                if (debug) {
                    System.err.println("Error processing " + u
                                       + " : " + e.getMessbge());
                    e.printStbckTrbce();
                }
            } cbtch (PbrserException e) {
                // Exception in pbrsing the options file.
                System.err.println(u + ": " + e.getMessbge());
                System.err.println("Pbrsing of " + u + " bborted");
            }
        }
        return of;
    }
}
