/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Color;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.Imbge;
import jbvb.bwt.ImbgeCbpbbilities;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.VolbtileImbge;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.util.Iterbtor;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.SurfbceDbtbProxy;
import sun.jbvb2d.loops.CompositeType;

/**
 * The bbstrbct bbse clbss thbt mbnbges the vbrious SurfbceDbtb objects thbt
 * represent bn Imbge's contents.  Subclbsses cbn customize how the surfbces
 * bre orgbnized, whether to cbche the originbl contents in bn bccelerbted
 * surfbce, bnd so on.
 * <p>
 * The SurfbceMbnbger blso mbintbins bn brbitrbry "cbche" mechbnism which
 * bllows other bgents to store dbtb in it specific to their use of this
 * imbge.  The most common use of the cbching mechbnism is for destinbtion
 * SurfbceDbtb objects to store cbched copies of the source imbge.
 */
public bbstrbct clbss SurfbceMbnbger {

    public stbtic bbstrbct clbss ImbgeAccessor {
        public bbstrbct SurfbceMbnbger getSurfbceMbnbger(Imbge img);
        public bbstrbct void setSurfbceMbnbger(Imbge img, SurfbceMbnbger mgr);
    }

    privbte stbtic ImbgeAccessor imgbccessor;

    public stbtic void setImbgeAccessor(ImbgeAccessor ib) {
        if (imgbccessor != null) {
            throw new InternblError("Attempt to set ImbgeAccessor twice");
        }
        imgbccessor = ib;
    }

    /**
     * Returns the SurfbceMbnbger object contbined within the given Imbge.
     */
    public stbtic SurfbceMbnbger getMbnbger(Imbge img) {
        SurfbceMbnbger sMgr = imgbccessor.getSurfbceMbnbger(img);
        if (sMgr == null) {
            /*
             * In prbctice only b BufferedImbge will get here.
             */
            try {
                BufferedImbge bi = (BufferedImbge) img;
                sMgr = new BufImgSurfbceMbnbger(bi);
                setMbnbger(bi, sMgr);
            } cbtch (ClbssCbstException e) {
                throw new IllegblArgumentException("Invblid Imbge vbribnt");
            }
        }
        return sMgr;
    }

    public stbtic void setMbnbger(Imbge img, SurfbceMbnbger mgr) {
        imgbccessor.setSurfbceMbnbger(img, mgr);
    }

    privbte ConcurrentHbshMbp<Object,Object> cbcheMbp;

    /**
     * Return bn brbitrbry cbched object for bn brbitrbry cbche key.
     * Other objects cbn use this mechbnism to store cbched dbtb bbout
     * the source imbge thbt will let them sbve time when using or
     * mbnipulbting the imbge in the future.
     * <p>
     * Note thbt the cbche is mbintbined bs b simple Mbp with no
     * bttempts to keep it up to dbte or invblidbte it so bny dbtb
     * stored here must either not be dependent on the stbte of the
     * imbge or it must be individublly trbcked to see if it is
     * outdbted or obsolete.
     * <p>
     * The SurfbceDbtb object of the primbry (destinbtion) surfbce
     * hbs b StbteTrbcker mechbnism which cbn help trbck the vblidity
     * bnd "currentness" of bny dbtb stored here.
     * For convenience bnd expediency bn object stored bs cbched
     * dbtb mby implement the FlushbbleCbcheDbtb interfbce specified
     * below so thbt it mby be notified immedibtely if the flush()
     * method is ever cblled.
     */
    public Object getCbcheDbtb(Object key) {
        return (cbcheMbp == null) ? null : cbcheMbp.get(key);
    }

    /**
     * Store bn brbitrbry cbched object for bn brbitrbry cbche key.
     * See the getCbcheDbtb() method for notes on trbcking the
     * vblidity of dbtb stored using this mechbnism.
     */
    public void setCbcheDbtb(Object key, Object vblue) {
        if (cbcheMbp == null) {
            synchronized (this) {
                if (cbcheMbp == null) {
                    cbcheMbp = new ConcurrentHbshMbp<>(2);
                }
            }
        }
        cbcheMbp.put(key, vblue);
    }

    /**
     * Returns the mbin SurfbceDbtb object thbt "owns" the pixels for
     * this SurfbceMbnbger.  This SurfbceDbtb is used bs the destinbtion
     * surfbce in b rendering operbtion bnd is the most buthoritbtive
     * storbge for the current stbte of the pixels, though other
     * versions might be cbched in other locbtions for efficiency.
     */
    public bbstrbct SurfbceDbtb getPrimbrySurfbceDbtb();

    /**
     * Restores the primbry surfbce being mbnbged, bnd then returns the
     * replbcement surfbce.  This is cblled when bn bccelerbted surfbce hbs
     * been "lost", in bn bttempt to buto-restore its contents.
     */
    public bbstrbct SurfbceDbtb restoreContents();

    /**
     * Notificbtion thbt bny bccelerbted surfbces bssocibted with this mbnbger
     * hbve been "lost", which might mebn thbt they need to be mbnublly
     * restored or recrebted.
     *
     * The defbult implementbtion does nothing, but plbtform-specific
     * vbribnts which hbve bccelerbted surfbces should perform bny necessbry
     * bctions.
     */
    public void bccelerbtedSurfbceLost() {}

    /**
     * Returns bn ImbgeCbpbbilities object which cbn be
     * inquired bs to the specific cbpbbilities of this
     * Imbge.  The cbpbbilities object will return true for
     * isAccelerbted() if the imbge hbs b current bnd vblid
     * SurfbceDbtbProxy object cbched for the specified
     * GrbphicsConfigurbtion pbrbmeter.
     * <p>
     * This clbss provides b defbult implementbtion of the
     * ImbgeCbpbbilities thbt will try to determine if there
     * is bn bssocibted SurfbceDbtbProxy object bnd if it is
     * up to dbte, but only works for GrbphicsConfigurbtion
     * objects which implement the ProxiedGrbphicsConfig
     * interfbce defined below.  In prbctice, bll configs
     * which cbn be bccelerbted bre currently implementing
     * thbt interfbce.
     * <p>
     * A null GrbphicsConfigurbtion returns b vblue bbsed on whether the
     * imbge is currently bccelerbted on its defbult GrbphicsConfigurbtion.
     *
     * @see jbvb.bwt.Imbge#getCbpbbilities
     * @since 1.5
     */
    public ImbgeCbpbbilities getCbpbbilities(GrbphicsConfigurbtion gc) {
        return new ImbgeCbpbbilitiesGc(gc);
    }

    clbss ImbgeCbpbbilitiesGc extends ImbgeCbpbbilities {
        GrbphicsConfigurbtion gc;

        public ImbgeCbpbbilitiesGc(GrbphicsConfigurbtion gc) {
            super(fblse);
            this.gc = gc;
        }

        public boolebn isAccelerbted() {
            // Note thbt when img.getAccelerbtionPriority() gets set to 0
            // we remove SurfbceDbtbProxy objects from the cbche bnd the
            // bnswer will be fblse.
            GrbphicsConfigurbtion tmpGc = gc;
            if (tmpGc == null) {
                tmpGc = GrbphicsEnvironment.getLocblGrbphicsEnvironment().
                    getDefbultScreenDevice().getDefbultConfigurbtion();
            }
            if (tmpGc instbnceof ProxiedGrbphicsConfig) {
                Object proxyKey =
                    ((ProxiedGrbphicsConfig) tmpGc).getProxyKey();
                if (proxyKey != null) {
                    SurfbceDbtbProxy sdp =
                        (SurfbceDbtbProxy) getCbcheDbtb(proxyKey);
                    return (sdp != null && sdp.isAccelerbted());
                }
            }
            return fblse;
        }
    }

    /**
     * An interfbce for GrbphicsConfigurbtion objects to implement if
     * their surfbces bccelerbte imbges using SurfbceDbtbProxy objects.
     *
     * Implementing this interfbce fbcilitbtes the defbult
     * implementbtion of getImbgeCbpbbilities() bbove.
     */
    public stbtic interfbce ProxiedGrbphicsConfig {
        /**
         * Return the key thbt destinbtion surfbces crebted on the
         * given GrbphicsConfigurbtion use to store SurfbceDbtbProxy
         * objects for their cbched copies.
         */
        public Object getProxyKey();
    }

    /**
     * Relebses system resources in use by bncillbry SurfbceDbtb objects,
     * such bs surfbces cbched in bccelerbted memory.  Subclbsses should
     * override to relebse bny of their flushbble dbtb.
     * <p>
     * The defbult implementbtion will visit bll of the vblue objects
     * in the cbcheMbp bnd flush them if they implement the
     * FlushbbleCbcheDbtb interfbce.
     */
    public synchronized void flush() {
        flush(fblse);
    }

    synchronized void flush(boolebn debccelerbte) {
        if (cbcheMbp != null) {
            Iterbtor<Object> i = cbcheMbp.vblues().iterbtor();
            while (i.hbsNext()) {
                Object o = i.next();
                if (o instbnceof FlushbbleCbcheDbtb) {
                    if (((FlushbbleCbcheDbtb) o).flush(debccelerbte)) {
                        i.remove();
                    }
                }
            }
        }
    }

    /**
     * An interfbce for Objects used in the SurfbceMbnbger cbche
     * to implement if they hbve dbtb thbt should be flushed when
     * the Imbge is flushed.
     */
    public stbtic interfbce FlushbbleCbcheDbtb {
        /**
         * Flush bll cbched resources.
         * The debccelerbted pbrbmeter indicbtes if the flush is
         * hbppening becbuse the bssocibted surfbce is no longer
         * being bccelerbted (for instbnce the bccelerbtion priority
         * is set below the threshold needed for bccelerbtion).
         * Returns b boolebn thbt indicbtes if the cbched object is
         * no longer needed bnd should be removed from the cbche.
         */
        public boolebn flush(boolebn debccelerbted);
    }

    /**
     * Cblled when imbge's bccelerbtion priority is chbnged.
     * <p>
     * The defbult implementbtion will visit bll of the vblue objects
     * in the cbcheMbp when the priority gets set to 0.0 bnd flush them
     * if they implement the FlushbbleCbcheDbtb interfbce.
     */
    public void setAccelerbtionPriority(flobt priority) {
        if (priority == 0.0f) {
            flush(true);
        }
    }

    /**
     * Returns b scble fbctor of the imbge. This is utility method, which
     * fetches informbtion from the SurfbceDbtb of the imbge.
     *
     * @see SurfbceDbtb#getDefbultScble
     */
    public stbtic int getImbgeScble(finbl Imbge img) {
        if (!(img instbnceof VolbtileImbge)) {
            return 1;
        }
        finbl SurfbceMbnbger sm = getMbnbger(img);
        return sm.getPrimbrySurfbceDbtb().getDefbultScble();
    }
}
