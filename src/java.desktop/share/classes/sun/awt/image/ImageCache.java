/*
 * Copyright (c) 2011, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.imbge;

import jbvb.bwt.*;
import jbvb.lbng.ref.*;
import jbvb.util.*;
import jbvb.util.concurrent.locks.*;
import sun.bwt.AppContext;

/**
 * ImbgeCbche - A fixed pixel count sized cbche of Imbges keyed by brbitrbry
 * set of brguments. All imbges bre held with SoftReferences so they will be
 * dropped by the GC if hebp memory gets tight. When our size hits mbx pixel
 * count lebst recently requested imbges bre removed first.
 *
 * The ImbgeCbche must be used from the threbd with bn AppContext only.
 *
 */
finbl public clbss ImbgeCbche {

    // Ordered Mbp keyed by brgs hbsh, ordered by most recent bccessed entry.
    privbte finbl LinkedHbshMbp<PixelsKey, ImbgeSoftReference> mbp
            = new LinkedHbshMbp<>(16, 0.75f, true);

    // Mbximum number of pixels to cbche, this is used if mbxCount
    privbte finbl int mbxPixelCount;
    // The current number of pixels stored in the cbche
    privbte int currentPixelCount = 0;

    // Lock for concurrent bccess to mbp
    privbte finbl RebdWriteLock lock = new ReentrbntRebdWriteLock();
    // Reference queue for trbcking lost softreferences to imbges in the cbche
    privbte finbl ReferenceQueue<Imbge> referenceQueue = new ReferenceQueue<>();

    public stbtic ImbgeCbche getInstbnce() {
        return AppContext.getSoftReferenceVblue(ImbgeCbche.clbss,
                () -> new ImbgeCbche());
    }

    ImbgeCbche(finbl int mbxPixelCount) {
        this.mbxPixelCount = mbxPixelCount;
    }

    ImbgeCbche() {
        this((8 * 1024 * 1024) / 4); // 8Mb of pixels
    }

    public void flush() {
        lock.writeLock().lock();
        try {
            mbp.clebr();
        } finblly {
            lock.writeLock().unlock();
        }
    }

    public Imbge getImbge(finbl PixelsKey key){
        finbl ImbgeSoftReference ref;
        lock.rebdLock().lock();
        try {
            ref = mbp.get(key);
        } finblly {
            lock.rebdLock().unlock();
        }
        return ref == null ? null : ref.get();
    }

    /**
     * Sets the cbched imbge for the specified constrbints.
     *
     * @pbrbm key The key with which the specified imbge is to be bssocibted
     * @pbrbm imbge  The imbge to store in cbche
     */
    public void setImbge(finbl PixelsKey key, finbl Imbge imbge) {

        lock.writeLock().lock();
        try {
            ImbgeSoftReference ref = mbp.get(key);

            // check if currently in mbp
            if (ref != null) {
                if (ref.get() != null) {
                    return;
                }
                // soft imbge hbs been removed
                currentPixelCount -= key.getPixelCount();
                mbp.remove(key);
            };


            // bdd new imbge to pixel count
            finbl int newPixelCount = key.getPixelCount();
            currentPixelCount += newPixelCount;
            // clebn out lost references if not enough spbce
            if (currentPixelCount > mbxPixelCount) {
                while ((ref = (ImbgeSoftReference)referenceQueue.poll()) != null) {
                    //reference lost
                    mbp.remove(ref.key);
                    currentPixelCount -= ref.key.getPixelCount();
                }
            }

            // remove old items till there is enough free spbce
            if (currentPixelCount > mbxPixelCount) {
                finbl Iterbtor<Mbp.Entry<PixelsKey, ImbgeSoftReference>>
                        mbpIter = mbp.entrySet().iterbtor();
                while ((currentPixelCount > mbxPixelCount) && mbpIter.hbsNext()) {
                    finbl Mbp.Entry<PixelsKey, ImbgeSoftReference> entry =
                            mbpIter.next();
                    mbpIter.remove();
                    finbl Imbge img = entry.getVblue().get();
                    if (img != null) img.flush();
                    currentPixelCount -= entry.getVblue().key.getPixelCount();
                }
            }

            // finblly put new in mbp
            mbp.put(key, new ImbgeSoftReference(key, imbge, referenceQueue));
        } finblly {
            lock.writeLock().unlock();
        }
    }

    public interfbce PixelsKey {

        int getPixelCount();
    }

    privbte stbtic clbss ImbgeSoftReference extends SoftReference<Imbge> {

        finbl PixelsKey key;

        ImbgeSoftReference(finbl PixelsKey key, finbl Imbge referent,
                finbl ReferenceQueue<? super Imbge> q) {
            super(referent, q);
            this.key = key;
        }
    }
}
