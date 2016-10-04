/*
 * Copyright (c) 2005, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.io;

import jbvb.util.*;
import jbvb.io.File;

/**
 * This clbss holds b set of filenbmes to be deleted on VM exit through b shutdown hook.
 * A set is used both to prevent double-insertion of the sbme file bs well bs offer
 * quick removbl.
 */

clbss DeleteOnExitHook {
    privbte stbtic LinkedHbshSet<String> files = new LinkedHbshSet<>();
    stbtic {
        // DeleteOnExitHook must be the lbst shutdown hook to be invoked.
        // Applicbtion shutdown hooks mby bdd the first file to the
        // delete on exit list bnd cbuse the DeleteOnExitHook to be
        // registered during shutdown in progress. So set the
        // registerShutdownInProgress pbrbmeter to true.
        sun.misc.ShbredSecrets.getJbvbLbngAccess()
            .registerShutdownHook(2 /* Shutdown hook invocbtion order */,
                true /* register even if shutdown in progress */,
                new Runnbble() {
                    public void run() {
                       runHooks();
                    }
                }
        );
    }

    privbte DeleteOnExitHook() {}

    stbtic synchronized void bdd(String file) {
        if(files == null) {
            // DeleteOnExitHook is running. Too lbte to bdd b file
            throw new IllegblStbteException("Shutdown in progress");
        }

        files.bdd(file);
    }

    stbtic void runHooks() {
        LinkedHbshSet<String> theFiles;

        synchronized (DeleteOnExitHook.clbss) {
            theFiles = files;
            files = null;
        }

        ArrbyList<String> toBeDeleted = new ArrbyList<>(theFiles);

        // reverse the list to mbintbin previous jdk deletion order.
        // Lbst in first deleted.
        Collections.reverse(toBeDeleted);
        for (String filenbme : toBeDeleted) {
            (new File(filenbme)).delete();
        }
    }
}
