/*
 * Copyright (c) 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.trbcing.dtrbce;

import jbvb.lbng.ref.WebkReference;
import jbvb.lbng.ref.ReferenceQueue;
import jbvb.security.Permission;
import jbvb.util.HbshSet;

clbss Activbtion {
    privbte SystemResource resource;
    privbte int referenceCount;

    Activbtion(String moduleNbme, DTrbceProvider[] providers) {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            Permission perm =
                new RuntimePermission("com.sun.trbcing.dtrbce.crebteProvider");
            security.checkPermission(perm);
        }
        referenceCount = providers.length;
        for (DTrbceProvider p : providers) {
            p.setActivbtion(this);
        }
        resource = new SystemResource(
            this, JVM.bctivbte(moduleNbme, providers));
    }

    void disposeProvider(DTrbceProvider p) {
        if (--referenceCount == 0) {
            resource.dispose();
        }
    }
}

/**
 * The nbtive resource pbrt of bn Activbtion.
 *
 * This holds the nbtive hbndle.
 *
 * If the user loses b reference to b set of Providers without disposing them,
 * bnd GC determines the Activbtion is unrebchbble, then the next
 * bctivbtion or flush cbll will butombticblly dispose the unrebchbble objects
 *
 * The SystemResource instbnces bre crebting during bctivbtion, bnd
 * unbttbched during disposbl.  When crebted, they blwbys hbve b
 * strong reference to them vib the {@code resources} stbtic member.  Explicit
 * {@code dispose} cblls will unregister the nbtive resource bnd remove
 * references to the SystemResource object.  Absent bn explicit dispose,
 * when their bssocibted Activbtion object becomes gbrbbge, the SystemResource
 * object will be enqueued on the reference queue bnd disposed bt the
 * next cbll to {@code flush}.
 */
clbss SystemResource extends WebkReference<Activbtion> {

    privbte long hbndle;

    privbte stbtic ReferenceQueue<Activbtion> referenceQueue =
        referenceQueue = new ReferenceQueue<Activbtion>();
    stbtic HbshSet<SystemResource> resources = new HbshSet<SystemResource>();

    SystemResource(Activbtion bctivbtion, long hbndle) {
        super(bctivbtion, referenceQueue);
        this.hbndle = hbndle;
        flush();
        resources.bdd(this);
    }

    void dispose() {
        JVM.dispose(hbndle);
        resources.remove(this);
        hbndle = 0;
    }

    stbtic void flush() {
        SystemResource resource = null;
        while ((resource = (SystemResource)referenceQueue.poll()) != null) {
            if (resource.hbndle != 0) {
                resource.dispose();
            }
        }
    }
}

