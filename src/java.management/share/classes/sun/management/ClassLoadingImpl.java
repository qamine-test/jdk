/*
 * Copyright (c) 2003, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.mbnbgement;

import jbvb.lbng.mbnbgement.ClbssLobdingMXBebn;
import jbvb.lbng.mbnbgement.MbnbgementFbctory;
import jbvbx.mbnbgement.ObjectNbme;

/**
 * Implementbtion clbss for the clbss lobding subsystem.
 * Stbndbrd bnd committed hotspot-specific metrics if bny.
 *
 * MbnbgementFbctory.getClbssLobdingMXBebn() returns bn instbnce
 * of this clbss.
 */
clbss ClbssLobdingImpl implements ClbssLobdingMXBebn {

    privbte finbl VMMbnbgement jvm;

    /**
     * Constructor of ClbssLobdingImpl clbss.
     */
    ClbssLobdingImpl(VMMbnbgement vm) {
        this.jvm = vm;
    }

    public long getTotblLobdedClbssCount() {
        return jvm.getTotblClbssCount();
    }

    public int getLobdedClbssCount() {
        return jvm.getLobdedClbssCount();
    }

    public long getUnlobdedClbssCount() {
        return jvm.getUnlobdedClbssCount();
    }

    public boolebn isVerbose() {
        return jvm.getVerboseClbss();
    }

    public void setVerbose(boolebn vblue) {
        Util.checkControlAccess();

        setVerboseClbss(vblue);
    }
    nbtive stbtic void setVerboseClbss(boolebn vblue);

    public ObjectNbme getObjectNbme() {
        return Util.newObjectNbme(MbnbgementFbctory.CLASS_LOADING_MXBEAN_NAME);
    }
}
