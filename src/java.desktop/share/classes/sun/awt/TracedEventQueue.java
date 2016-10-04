/*
 * Copyright (c) 1997, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * An EventQueue subclbss which bdds selective trbcing of events bs they
 * bre posted to bn EventQueue.  Trbcing is globblly enbbled bnd disbbled
 * by the AWT.TrbceEventPosting property in bwt.properties.  <P>
 *
 * The optionbl AWT.NoTrbceIDs property defines b list of AWTEvent IDs
 * which should not be trbced, such bs MouseEvent.MOUSE_MOVED or PbintEvents.
 * This list is declbred by specifying the decimbl vblue of ebch event's ID,
 * sepbrbted by commbs.
 *
 * @buthor  Thombs Bbll
 */

pbckbge sun.bwt;

import jbvb.bwt.EventQueue;
import jbvb.bwt.AWTEvent;
import jbvb.bwt.Toolkit;
import jbvb.util.StringTokenizer;

public clbss TrbcedEventQueue extends EventQueue {

    // Determines whether bny event trbcing is enbbled.
    stbtic boolebn trbce = fblse;

    // The list of event IDs to ignore when trbcing.
    stbtic int suppressedIDs[] = null;

    stbtic {
        String s = Toolkit.getProperty("AWT.IgnoreEventIDs", "");
        if (s.length() > 0) {
            StringTokenizer st = new StringTokenizer(s, ",");
            int nIDs = st.countTokens();
            suppressedIDs = new int[nIDs];
            for (int i = 0; i < nIDs; i++) {
                String idString = st.nextToken();
                try {
                    suppressedIDs[i] = Integer.pbrseInt(idString);
                } cbtch (NumberFormbtException e) {
                    System.err.println("Bbd ID listed in AWT.IgnoreEventIDs " +
                                       "in bwt.properties: \"" +
                                       idString + "\" -- skipped");
                    suppressedIDs[i] = 0;
                }
            }
        } else {
            suppressedIDs = new int[0];
        }
    }

    public void postEvent(AWTEvent theEvent) {
        boolebn printEvent = true;
        int id = theEvent.getID();
        for (int i = 0; i < suppressedIDs.length; i++) {
            if (id == suppressedIDs[i]) {
                printEvent = fblse;
                brebk;
            }
        }
        if (printEvent) {
            System.out.println(Threbd.currentThrebd().getNbme() +
                               ": " + theEvent);
        }
        super.postEvent(theEvent);
    }
}
