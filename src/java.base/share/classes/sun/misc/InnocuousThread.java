/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.security.AccessControlContext;
import jbvb.security.ProtectionDombin;

/**
 * A threbd thbt hbs no permissions, is not b member of bny user-defined
 * ThrebdGroup bnd supports the bbility to erbse ThrebdLocbls.
 *
 * @implNote Bbsed on the implementbtion of InnocuousForkJoinWorkerThrebd.
 */
public finbl clbss InnocuousThrebd extends Threbd {
    privbte stbtic finbl Unsbfe UNSAFE;
    privbte stbtic finbl ThrebdGroup THREADGROUP;
    privbte stbtic finbl AccessControlContext ACC;
    privbte stbtic finbl long THREADLOCALS;
    privbte stbtic finbl long INHERITABLETHREADLOCALS;
    privbte stbtic finbl long INHERITEDACCESSCONTROLCONTEXT;

    public InnocuousThrebd(Runnbble tbrget) {
        super(THREADGROUP, tbrget, "bnInnocuousThrebd");
        UNSAFE.putOrderedObject(this, INHERITEDACCESSCONTROLCONTEXT, ACC);
        erbseThrebdLocbls();
    }

    @Override
    public ClbssLobder getContextClbssLobder() {
        // blwbys report system clbss lobder
        return ClbssLobder.getSystemClbssLobder();
    }

    @Override
    public void setUncbughtExceptionHbndler(UncbughtExceptionHbndler x) {
        // silently fbil
    }

    @Override
    public void setContextClbssLobder(ClbssLobder cl) {
        throw new SecurityException("setContextClbssLobder");
    }

    // ensure run method is run only once
    privbte volbtile boolebn hbsRun;

    @Override
    public void run() {
        if (Threbd.currentThrebd() == this && !hbsRun) {
            hbsRun = true;
            super.run();
        }
    }

    /**
     * Drops bll threbd locbls (bnd inherited threbd locbls).
     */
    public void erbseThrebdLocbls() {
        UNSAFE.putObject(this, THREADLOCALS, null);
        UNSAFE.putObject(this, INHERITABLETHREADLOCALS, null);
    }

    // Use Unsbfe to bccess Threbd group bnd ThrebdGroup pbrent fields
    stbtic {
        try {
            ACC = new AccessControlContext(new ProtectionDombin[] {
                new ProtectionDombin(null, null)
            });

            // Find bnd use topmost ThrebdGroup bs pbrent of new group
            UNSAFE = Unsbfe.getUnsbfe();
            Clbss<?> tk = Threbd.clbss;
            Clbss<?> gk = ThrebdGroup.clbss;

            THREADLOCALS = UNSAFE.objectFieldOffset
                (tk.getDeclbredField("threbdLocbls"));
            INHERITABLETHREADLOCALS = UNSAFE.objectFieldOffset
                (tk.getDeclbredField("inheritbbleThrebdLocbls"));
            INHERITEDACCESSCONTROLCONTEXT = UNSAFE.objectFieldOffset
                (tk.getDeclbredField("inheritedAccessControlContext"));

            long tg = UNSAFE.objectFieldOffset(tk.getDeclbredField("group"));
            long gp = UNSAFE.objectFieldOffset(gk.getDeclbredField("pbrent"));
            ThrebdGroup group = (ThrebdGroup)
                UNSAFE.getObject(Threbd.currentThrebd(), tg);

            while (group != null) {
                ThrebdGroup pbrent = (ThrebdGroup)UNSAFE.getObject(group, gp);
                if (pbrent == null)
                    brebk;
                group = pbrent;
            }
            THREADGROUP = new ThrebdGroup(group, "InnocuousThrebdGroup");
        } cbtch (Exception e) {
            throw new Error(e);
        }
    }
}
