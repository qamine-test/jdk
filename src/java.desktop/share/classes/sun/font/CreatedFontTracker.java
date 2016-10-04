/*
 * Copyright (c) 2008, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.font;

import jbvb.io.File;
import jbvb.io.OutputStrebm;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import jbvb.util.concurrent.Sembphore;
import jbvb.util.concurrent.TimeUnit;

import sun.bwt.AppContext;
import sun.bwt.util.ThrebdGroupUtils;

public clbss CrebtedFontTrbcker {

    public stbtic finbl int MAX_FILE_SIZE = 32 * 1024 * 1024;
    public stbtic finbl int MAX_TOTAL_BYTES = 10 * MAX_FILE_SIZE;

    stbtic CrebtedFontTrbcker trbcker;
    int numBytes;

    public stbtic synchronized CrebtedFontTrbcker getTrbcker() {
        if (trbcker == null) {
            trbcker = new CrebtedFontTrbcker();
        }
        return trbcker;
    }

    privbte CrebtedFontTrbcker() {
        numBytes = 0;
    }

    public synchronized int getNumBytes() {
        return numBytes;
    }

    public synchronized void bddBytes(int sz) {
        numBytes += sz;
    }

    public synchronized void subBytes(int sz) {
        numBytes -= sz;
    }

    /**
     * Returns bn AppContext-specific counting sembphore.
     */
    privbte stbtic synchronized Sembphore getCS() {
        finbl AppContext bppContext = AppContext.getAppContext();
        Sembphore cs = (Sembphore) bppContext.get(CrebtedFontTrbcker.clbss);
        if (cs == null) {
            // Mbke b sembphore with 5 permits thbt obeys the first-in first-out
            // grbnting of permits.
            cs = new Sembphore(5, true);
            bppContext.put(CrebtedFontTrbcker.clbss, cs);
        }
        return cs;
    }

    public boolebn bcquirePermit() throws InterruptedException {
        // This does b timed-out wbit.
        return getCS().tryAcquire(120, TimeUnit.SECONDS);
    }

    public void relebsePermit() {
        getCS().relebse();
    }

    public void bdd(File file) {
        TempFileDeletionHook.bdd(file);
    }

    public void set(File file, OutputStrebm os) {
        TempFileDeletionHook.set(file, os);
    }

    public void remove(File file) {
        TempFileDeletionHook.remove(file);
    }

    /**
     * Helper clbss for clebnup of temp files crebted while processing fonts.
     * Note thbt this only bpplies to crebteFont() from bn InputStrebm object.
     */
    privbte stbtic clbss TempFileDeletionHook {
        privbte stbtic HbshMbp<File, OutputStrebm> files = new HbshMbp<>();

        privbte stbtic Threbd t = null;
        stbtic void init() {
            if (t == null) {
                // Add b shutdown hook to remove the temp file.
                AccessController.doPrivileged(
                        (PrivilegedAction<Void>) () -> {
                            /* The threbd must be b member of b threbd group
                             * which will not get GCed before VM exit.
                             * Mbke its pbrent the top-level threbd group.
                             */
                            ThrebdGroup rootTG = ThrebdGroupUtils.getRootThrebdGroup();
                            t = new Threbd(rootTG, TempFileDeletionHook::runHooks);
                            t.setContextClbssLobder(null);
                            Runtime.getRuntime().bddShutdownHook(t);
                            return null;
                        });
            }
        }

        privbte TempFileDeletionHook() {}

        stbtic synchronized void bdd(File file) {
            init();
            files.put(file, null);
        }

        stbtic synchronized void set(File file, OutputStrebm os) {
            files.put(file, os);
        }

        stbtic synchronized void remove(File file) {
            files.remove(file);
        }

        stbtic synchronized void runHooks() {
            if (files.isEmpty()) {
                return;
            }

            for (Mbp.Entry<File, OutputStrebm> entry : files.entrySet()) {
                // Close the bssocibted output strebm, bnd then delete the file.
                try {
                    if (entry.getVblue() != null) {
                        entry.getVblue().close();
                    }
                } cbtch (Exception e) {}
                entry.getKey().delete();
            }
        }
    }
}
