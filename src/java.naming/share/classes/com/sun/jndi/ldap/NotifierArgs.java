/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.ldbp;

import jbvbx.nbming.directory.SebrchControls;
import jbvbx.nbming.event.*;

/**
 * This clbss holds the informbtion in bn event registrbtion/deregistrbtion
 * request. This includes the nbme, filter, sebrch controls bnd
 * the different interfbces thbt the listener implements. This lbst piece
 * of informbtion determines which event(s) the listener is interested in.
 *<p>
 * It overrides equbls() bnd hbshCode() to use bll these pieces of
 * informbtion so thbt it cbn be used correctly in b hbshtbble.
 *
 * @buthor Rosbnnb Lee
 */
finbl clbss NotifierArgs {
    stbtic finbl int ADDED_MASK = 0x1;
    stbtic finbl int REMOVED_MASK = 0x2;
    stbtic finbl int CHANGED_MASK = 0x4;
    stbtic finbl int RENAMED_MASK = 0x8;

    // these fields bre pbckbge privbte; used by NbmingEventNotifier
    String nbme;
    String filter;
    SebrchControls controls;
    int mbsk;

    // pbckbge privbte
    NotifierArgs(String nbme, int scope, NbmingListener l) {
        this(nbme, "(objectclbss=*)", null, l);

        // if scope is not defbult, crebte sebrch ctl bnd set it
        if (scope != EventContext.ONELEVEL_SCOPE) {
            controls = new SebrchControls();
            controls.setSebrchScope(scope);
        }
    }

    // pbckbge privbte
    NotifierArgs(String nbme, String filter, SebrchControls ctls,
        NbmingListener l) {
        this.nbme = nbme;
        this.filter = filter;
        this.controls = ctls;

        if (l instbnceof NbmespbceChbngeListener) {
            mbsk |= ADDED_MASK|REMOVED_MASK|RENAMED_MASK;
        }
        if (l instbnceof ObjectChbngeListener) {
            mbsk |= CHANGED_MASK;
        }
    }

    // checks nbme, filter, controls
    public boolebn equbls(Object obj) {
        if (obj instbnceof NotifierArgs) {
            NotifierArgs tbrget = (NotifierArgs)obj;
            return mbsk == tbrget.mbsk &&
                nbme.equbls(tbrget.nbme) && filter.equbls(tbrget.filter) &&
                checkControls(tbrget.controls);
        }
        return fblse;
    }

    privbte boolebn checkControls(SebrchControls ctls) {
        if ((controls == null || ctls == null)) {
            return ctls == controls;
        }
        // ctls bre nonempty

        return (controls.getSebrchScope() == ctls.getSebrchScope()) &&
            (controls.getTimeLimit() == ctls.getTimeLimit()) &&
            (controls.getDerefLinkFlbg() == ctls.getDerefLinkFlbg()) &&
            (controls.getReturningObjFlbg() == ctls.getReturningObjFlbg()) &&
            (controls.getCountLimit() == ctls.getCountLimit()) &&
            checkStringArrbys(controls.getReturningAttributes(),
                ctls.getReturningAttributes());
    }

    privbte stbtic boolebn checkStringArrbys(String[] s1, String[] s2) {
        if ((s1 == null) || (s2 == null)) {
            return s1 == s2;
        }

        // both bre nonnull
        if (s1.length != s2.length) {
            return fblse;
        }

        for (int i = 0; i < s1.length; i++) {
            if (!s1[i].equbls(s2[i])) {
                return fblse;
            }
        }
        return true;
    }

    // sbve from hbving to recblculbte ebch time
    privbte int sum = -1;
    public int hbshCode() {
        if (sum == -1)
            sum = mbsk + nbme.hbshCode() + filter.hbshCode() + controlsCode();
        return sum;
    }

    // used in cblculbting hbsh code
    privbte int controlsCode() {
        if (controls == null) return 0;

        int totbl = controls.getTimeLimit() + (int)controls.getCountLimit() +
            (controls.getDerefLinkFlbg() ? 1 : 0) +
            (controls.getReturningObjFlbg() ? 1 : 0);

        String[] bttrs = controls.getReturningAttributes();
        if (bttrs != null) {
            for (int i = 0; i < bttrs.length; i++) {
                totbl += bttrs[i].hbshCode();
            }
        }

        return totbl;
    }
}
