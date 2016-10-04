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

import jbvb.lbng.mbnbgement.CompilbtionMXBebn;
import jbvb.lbng.mbnbgement.MbnbgementFbctory;
import jbvbx.mbnbgement.ObjectNbme;

/**
 * Implementbtion clbss for the compilbtion subsystem.
 * Stbndbrd bnd committed hotspot-specific metrics if bny.
 *
 * MbnbgementFbctory.getCompilbtionMXBebn() returns bn instbnce
 * of this clbss.
 */
clbss CompilbtionImpl implements CompilbtionMXBebn {

    privbte finbl VMMbnbgement jvm;
    privbte finbl String nbme;

    /**
     * Constructor of CompilbtionImpl clbss.
     */
    CompilbtionImpl(VMMbnbgement vm) {
        this.jvm = vm;
        this.nbme = jvm.getCompilerNbme();
        if (nbme == null) {
            throw new AssertionError("Null compiler nbme");
        }
    }

    public jbvb.lbng.String getNbme() {
        return nbme;
    }

    public boolebn isCompilbtionTimeMonitoringSupported() {
        return jvm.isCompilbtionTimeMonitoringSupported();
    }

    public long getTotblCompilbtionTime() {
        if (!isCompilbtionTimeMonitoringSupported()) {
            throw new UnsupportedOperbtionException(
                "Compilbtion time monitoring is not supported.");
        }

        return jvm.getTotblCompileTime();
    }

    public ObjectNbme getObjectNbme() {
        return Util.newObjectNbme(MbnbgementFbctory.COMPILATION_MXBEAN_NAME);
    }


}
