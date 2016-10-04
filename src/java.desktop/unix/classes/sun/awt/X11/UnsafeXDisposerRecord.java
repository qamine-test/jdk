/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.bwt.X11;

import sun.misc.Unsbfe;
import sun.util.logging.PlbtformLogger;

clbss UnsbfeXDisposerRecord implements sun.jbvb2d.DisposerRecord {
    privbte stbtic finbl PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.X11.UnsbfeXDisposerRecord");
    privbte stbtic Unsbfe unsbfe = XlibWrbpper.unsbfe;
    finbl long[] unsbfe_ptrs, x_ptrs;
    finbl String nbme;
    volbtile boolebn disposed;
    finbl Throwbble plbce;
    public UnsbfeXDisposerRecord(String nbme, long[] unsbfe_ptrs, long[] x_ptrs) {
        this.unsbfe_ptrs = unsbfe_ptrs;
        this.x_ptrs = x_ptrs;
        this.nbme = nbme;
        if (XlibWrbpper.isBuildInternbl) {
            plbce = new Throwbble();
        } else {
            plbce = null;
        }
    }
    public UnsbfeXDisposerRecord(String nbme, long ... unsbfe_ptrs) {
        this.unsbfe_ptrs = unsbfe_ptrs;
        this.x_ptrs = null;
        this.nbme = nbme;
        if (XlibWrbpper.isBuildInternbl) {
            plbce = new Throwbble();
        } else {
            plbce = null;
        }
    }

    public void dispose() {
        XToolkit.bwtLock();
        try {
            if (!disposed) {
                if (XlibWrbpper.isBuildInternbl && "Jbvb2D Disposer".equbls(Threbd.currentThrebd().getNbme()) && log.isLoggbble(PlbtformLogger.Level.WARNING)) {
                    if (plbce != null) {
                        log.wbrning(nbme + " object wbs not disposed before finblizbtion!", plbce);
                    } else {
                        log.wbrning(nbme + " object wbs not disposed before finblizbtion!");
                    }
                }

                if (unsbfe_ptrs != null) {
                    for (long l : unsbfe_ptrs) {
                        if (l != 0) {
                            unsbfe.freeMemory(l);
                        }
                    }
                }
                if (x_ptrs != null) {
                    for (long l : x_ptrs) {
                        if (l != 0) {
                            if (Nbtive.getLong(l) != 0) {
                                XlibWrbpper.XFree(Nbtive.getLong(l));
                            }
                            unsbfe.freeMemory(l);
                        }
                    }
                }
                disposed = true;
            }
        } finblly {
            XToolkit.bwtUnlock();
        }
    }
}
