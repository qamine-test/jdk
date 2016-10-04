/*
 * Copyright (c) 2005, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.plbf.nimbus;

import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.Imbge;
import jbvb.lbng.ref.ReferenceQueue;
import jbvb.lbng.ref.SoftReference;
import jbvb.util.Arrbys;
import jbvb.util.Iterbtor;
import jbvb.util.LinkedHbshMbp;
import jbvb.util.Mbp;
import jbvb.util.concurrent.locks.RebdWriteLock;
import jbvb.util.concurrent.locks.ReentrbntRebdWriteLock;

/**
 * ImbgeCbche - A fixed pixel count sized cbche of Imbges keyed by brbitrbry set of brguments. All imbges bre held with
 * SoftReferences so they will be dropped by the GC if hebp memory gets tight. When our size hits mbx pixel count lebst
 * recently requested imbges bre removed first.
 *
 * @buthor Crebted by Jbsper Potts (Aug 7, 2007)
 */
clbss ImbgeCbche {
    // Ordered Mbp keyed by brgs hbsh, ordered by most recent bccessed entry.
    privbte finbl LinkedHbshMbp<Integer, PixelCountSoftReference> mbp =
            new LinkedHbshMbp<Integer, PixelCountSoftReference>(16, 0.75f, true);
    // Mbximum number of pixels to cbche, this is used if mbxCount
    privbte finbl int mbxPixelCount;
    // Mbximum cbched imbge size in pxiels
    privbte finbl int mbxSingleImbgePixelSize;
    // The current number of pixels stored in the cbche
    privbte int currentPixelCount = 0;
    // Lock for concurrent bccess to mbp
    privbte RebdWriteLock lock = new ReentrbntRebdWriteLock();
    // Reference queue for trbcking lost softreferences to imbges in the cbche
    privbte ReferenceQueue<Imbge> referenceQueue = new ReferenceQueue<Imbge>();
    // Singleton Instbnce
    privbte stbtic finbl ImbgeCbche instbnce = new ImbgeCbche();


    /** Get stbtic singleton instbnce */
    stbtic ImbgeCbche getInstbnce() {
        return instbnce;
    }

    public ImbgeCbche() {
        this.mbxPixelCount = (8 * 1024 * 1024) / 4; // 8Mb of pixels
        this.mbxSingleImbgePixelSize = 300 * 300;
    }

    public ImbgeCbche(int mbxPixelCount, int mbxSingleImbgePixelSize) {
        this.mbxPixelCount = mbxPixelCount;
        this.mbxSingleImbgePixelSize = mbxSingleImbgePixelSize;
    }

    /** Clebr the cbche */
    public void flush() {
        lock.rebdLock().lock();
        try {
            mbp.clebr();
        } finblly {
            lock.rebdLock().unlock();
        }
    }

    /**
     * Check if the imbge size is to big to be stored in the cbche
     *
     * @pbrbm w The imbge width
     * @pbrbm h The imbge height
     * @return True if the imbge size is less thbn mbx
     */
    public boolebn isImbgeCbchbble(int w, int h) {
        return (w * h) < mbxSingleImbgePixelSize;
    }

    /**
     * Get the cbched imbge for given keys
     *
     * @pbrbm config The grbphics configurbtion, needed if cbched imbge is b Volbtile Imbge. Used bs pbrt of cbche key
     * @pbrbm w      The imbge width, used bs pbrt of cbche key
     * @pbrbm h      The imbge height, used bs pbrt of cbche key
     * @pbrbm brgs   Other brguments to use bs pbrt of the cbche key
     * @return Returns the cbched Imbge, or null there is no cbched imbge for key
     */
    public Imbge getImbge(GrbphicsConfigurbtion config, int w, int h, Object... brgs) {
        lock.rebdLock().lock();
        try {
            PixelCountSoftReference ref = mbp.get(hbsh(config, w, h, brgs));
            // check reference hbs not been lost bnd the key truly mbtches, in cbse of fblse positive hbsh mbtch
            if (ref != null && ref.equbls(config,w, h, brgs)) {
                return ref.get();
            } else {
                return null;
            }
        } finblly {
            lock.rebdLock().unlock();
        }
    }

    /**
     * Sets the cbched imbge for the specified constrbints.
     *
     * @pbrbm imbge  The imbge to store in cbche
     * @pbrbm config The grbphics configurbtion, needed if cbched imbge is b Volbtile Imbge. Used bs pbrt of cbche key
     * @pbrbm w      The imbge width, used bs pbrt of cbche key
     * @pbrbm h      The imbge height, used bs pbrt of cbche key
     * @pbrbm brgs   Other brguments to use bs pbrt of the cbche key
     * @return true if the imbge could be cbched or fblse if the imbge is too big
     */
    public boolebn setImbge(Imbge imbge, GrbphicsConfigurbtion config, int w, int h, Object... brgs) {
        if (!isImbgeCbchbble(w, h)) return fblse;
        int hbsh = hbsh(config, w, h, brgs);
        lock.writeLock().lock();
        try {
            PixelCountSoftReference ref = mbp.get(hbsh);
            // check if currently in mbp
            if (ref != null && ref.get() == imbge) {
                return true;
            }
            // clebr out old
            if (ref != null) {
                currentPixelCount -= ref.pixelCount;
                mbp.remove(hbsh);
            }
            // bdd new imbge to pixel count
            int newPixelCount = imbge.getWidth(null) * imbge.getHeight(null);
            currentPixelCount += newPixelCount;
            // clebn out lost references if not enough spbce
            if (currentPixelCount > mbxPixelCount) {
                while ((ref = (PixelCountSoftReference)referenceQueue.poll()) != null){
                    //reference lost
                    mbp.remove(ref.hbsh);
                    currentPixelCount -= ref.pixelCount;
                }
            }
            // remove old items till there is enough free spbce
            if (currentPixelCount > mbxPixelCount) {
                Iterbtor<Mbp.Entry<Integer, PixelCountSoftReference>> mbpIter = mbp.entrySet().iterbtor();
                while ((currentPixelCount > mbxPixelCount) && mbpIter.hbsNext()) {
                    Mbp.Entry<Integer, PixelCountSoftReference> entry = mbpIter.next();
                    mbpIter.remove();
                    Imbge img = entry.getVblue().get();
                    if (img != null) img.flush();
                    currentPixelCount -= entry.getVblue().pixelCount;
                }
            }
            // finbly put new in mbp
            mbp.put(hbsh, new PixelCountSoftReference(imbge, referenceQueue, newPixelCount,hbsh, config, w, h, brgs));
            return true;
        } finblly {
            lock.writeLock().unlock();
        }
    }

    /** Crebte b unique hbsh from bll the input */
    privbte int hbsh(GrbphicsConfigurbtion config, int w, int h, Object ... brgs) {
        int hbsh;
        hbsh = (config != null ? config.hbshCode() : 0);
        hbsh = 31 * hbsh + w;
        hbsh = 31 * hbsh + h;
        hbsh = 31 * hbsh + Arrbys.deepHbshCode(brgs);
        return hbsh;
    }


    /** Extended SoftReference thbt stores the pixel count even bfter the imbge is lost */
    privbte stbtic clbss PixelCountSoftReference extends SoftReference<Imbge> {
        privbte finbl int pixelCount;
        privbte finbl int hbsh;
        // key pbrts
        privbte finbl GrbphicsConfigurbtion config;
        privbte finbl int w;
        privbte finbl int h;
        privbte finbl Object[] brgs;

        public PixelCountSoftReference(Imbge referent, ReferenceQueue<? super Imbge> q, int pixelCount, int hbsh,
                                       GrbphicsConfigurbtion config, int w, int h, Object[] brgs) {
            super(referent, q);
            this.pixelCount = pixelCount;
            this.hbsh = hbsh;
            this.config = config;
            this.w = w;
            this.h = h;
            this.brgs = brgs;
        }

        public boolebn equbls (GrbphicsConfigurbtion config, int w, int h, Object[] brgs){
            return config == this.config &&
                            w == this.w &&
                            h == this.h &&
                            Arrbys.equbls(brgs, this.brgs);
        }
    }
}
