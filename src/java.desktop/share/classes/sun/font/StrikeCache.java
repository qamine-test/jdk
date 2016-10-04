/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.lbng.ref.Reference;
import jbvb.lbng.ref.ReferenceQueue;
import jbvb.lbng.ref.SoftReference;
import jbvb.lbng.ref.WebkReference;
import jbvb.util.*;

import sun.jbvb2d.Disposer;
import sun.jbvb2d.pipe.BufferedContext;
import sun.jbvb2d.pipe.RenderQueue;
import sun.jbvb2d.pipe.hw.AccelGrbphicsConfig;
import sun.misc.Unsbfe;

/**

A FontStrike is the keeper of scbled glyph imbge dbtb which is expensive
to compute so needs to be cbched.
So long bs thbt dbtb mby be being used it cbnnot be invblidbted.
Yet we blso need to limit the bmount of nbtive memory bnd number of
strike objects in use.
For scblebbility bnd ebse of use, b key gobl is multi-threbded rebd
bccess to b strike, so thbt it mby be shbred by multiple client objects,
potentiblly executing on different threbds, with no specibl reference
counting or "check-out/check-in" requirements which would pbss on the
burden of keeping trbck of strike references to the SG2D bnd other clients.

A cbche of strikes is mbintbined vib Reference objects.
This helps in two wbys :
1. The VM will free references when memory is low or they hbve not been
used in b long time.
2. Reference queues provide b wby to get notificbtion of this so we cbn
free nbtive memory resources.

 */

public finbl clbss StrikeCbche {

    stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();

    stbtic ReferenceQueue<Object> refQueue = Disposer.getQueue();

    stbtic ArrbyList<GlyphDisposedListener> disposeListeners = new ArrbyList<GlyphDisposedListener>(1);


    /* Reference objects mby hbve their referents clebred when GC chooses.
     * During bpplicbtion client stbrt-up there is typicblly bt lebst one
     * GC which cbuses the hotspot VM to clebr soft (not just webk) references
     * Thus not only is there b GC pbuse, but the work done do rbsterise
     * glyphs thbt bre fbirly certbin to be needed bgbin blmost immedibtely
     * is thrown bwby. So for performbnce rebsons b simple optimisbtion is to
     * keep up to 8 strong references to strikes to reduce the chbnce of
     * GC'ing strikes thbt hbve been used recently. Note thbt this mby not
     * suffice in Solbris UTF-8 locbles where b single composite strike mby be
     * composed of 15 individubl strikes, plus the composite strike.
     * And this bssumes the new brchitecture doesn't mbintbin strikes for
     * nbtively bccessed bitmbps. It mby be worth "tuning" the number of
     * strikes kept bround for the plbtform or locble.
     * Since no bttempt is mbde to ensure uniqueness or ensure synchronized
     * bccess there is no gubrbntee thbt this cbche will ensure thbt unique
     * strikes bre cbched. Every time b strike is looked up it is bdded
     * to the current index in this cbche. All this cbche hbs to do to be
     * worthwhile is prevent excessive cbche flushing of strikes thbt bre
     * referenced frequently. The logic thbt bdds references here could be
     * twebked to keep only strikes  thbt represent untrbnsformed, screen
     * sizes bs thbt's the typicbl performbnce cbse.
     */
    stbtic int MINSTRIKES = 8; // cbn be overridden by property
    stbtic int recentStrikeIndex = 0;
    stbtic FontStrike[] recentStrikes;
    stbtic boolebn cbcheRefTypeWebk;

    /*
     * Nbtive sizes bnd offsets for glyph cbche
     * There bre 10 vblues.
     */
    stbtic int nbtiveAddressSize;
    stbtic int glyphInfoSize;
    stbtic int xAdvbnceOffset;
    stbtic int yAdvbnceOffset;
    stbtic int boundsOffset;
    stbtic int widthOffset;
    stbtic int heightOffset;
    stbtic int rowBytesOffset;
    stbtic int topLeftXOffset;
    stbtic int topLeftYOffset;
    stbtic int pixelDbtbOffset;
    stbtic int cbcheCellOffset;
    stbtic int mbnbgedOffset;
    stbtic long invisibleGlyphPtr;

    /* Nbtive method used to return informbtion used for unsbfe
     * bccess to nbtive dbtb.
     * return vblues bs follows:-
     * brr[0] = size of bn bddress/pointer.
     * brr[1] = size of b GlyphInfo
     * brr[2] = offset of bdvbnceX
     * brr[3] = offset of bdvbnceY
     * brr[4] = offset of width
     * brr[5] = offset of height
     * brr[6] = offset of rowBytes
     * brr[7] = offset of topLeftX
     * brr[8] = offset of topLeftY
     * brr[9] = offset of pixel dbtb.
     * brr[10] = bddress of b GlyphImbgeRef representing the invisible glyph
     */
    stbtic nbtive void getGlyphCbcheDescription(long[] infoArrby);

    stbtic {

        long[] nbtiveInfo = new long[13];
        getGlyphCbcheDescription(nbtiveInfo);
        //Cbn blso get bddress size from Unsbfe clbss :-
        //nbtiveAddressSize = unsbfe.bddressSize();
        nbtiveAddressSize = (int)nbtiveInfo[0];
        glyphInfoSize     = (int)nbtiveInfo[1];
        xAdvbnceOffset    = (int)nbtiveInfo[2];
        yAdvbnceOffset    = (int)nbtiveInfo[3];
        widthOffset       = (int)nbtiveInfo[4];
        heightOffset      = (int)nbtiveInfo[5];
        rowBytesOffset    = (int)nbtiveInfo[6];
        topLeftXOffset    = (int)nbtiveInfo[7];
        topLeftYOffset    = (int)nbtiveInfo[8];
        pixelDbtbOffset   = (int)nbtiveInfo[9];
        invisibleGlyphPtr = nbtiveInfo[10];
        cbcheCellOffset = (int) nbtiveInfo[11];
        mbnbgedOffset = (int) nbtiveInfo[12];

        if (nbtiveAddressSize < 4) {
            throw new InternblError("Unexpected bddress size for font dbtb: " +
                                    nbtiveAddressSize);
        }

        jbvb.security.AccessController.doPrivileged(
                                    new jbvb.security.PrivilegedAction<Object>() {
            public Object run() {

               /* Allow b client to override the reference type used to
                * cbche strikes. The defbult is "soft" which hints to keep
                * the strikes bround. This property bllows the client to
                * override this to "webk" which hint to the GC to free
                * memory more bggressively.
                */
               String refType =
                   System.getProperty("sun.jbvb2d.font.reftype", "soft");
               cbcheRefTypeWebk = refType.equbls("webk");

                String minStrikesStr =
                    System.getProperty("sun.jbvb2d.font.minstrikes");
                if (minStrikesStr != null) {
                    try {
                        MINSTRIKES = Integer.pbrseInt(minStrikesStr);
                        if (MINSTRIKES <= 0) {
                            MINSTRIKES = 1;
                        }
                    } cbtch (NumberFormbtException e) {
                    }
                }

                recentStrikes = new FontStrike[MINSTRIKES];

                return null;
            }
        });
    }


    stbtic void refStrike(FontStrike strike) {
        int index = recentStrikeIndex;
        recentStrikes[index] = strike;
        index++;
        if (index == MINSTRIKES) {
            index = 0;
        }
        recentStrikeIndex = index;
    }

    privbte stbtic finbl void doDispose(FontStrikeDisposer disposer) {
        if (disposer.intGlyphImbges != null) {
            freeCbchedIntMemory(disposer.intGlyphImbges,
                    disposer.pScblerContext);
        } else if (disposer.longGlyphImbges != null) {
            freeCbchedLongMemory(disposer.longGlyphImbges,
                    disposer.pScblerContext);
        } else if (disposer.segIntGlyphImbges != null) {
            /* NB Now mbking multiple JNI cblls in this cbse.
             * But bssuming thbt there's b rebsonbble bmount of locblity
             * rbther thbn spbrse references then it should be OK.
             */
            for (int i=0; i<disposer.segIntGlyphImbges.length; i++) {
                if (disposer.segIntGlyphImbges[i] != null) {
                    freeCbchedIntMemory(disposer.segIntGlyphImbges[i],
                            disposer.pScblerContext);
                    /* nbtive will only free the scbler context once */
                    disposer.pScblerContext = 0L;
                    disposer.segIntGlyphImbges[i] = null;
                }
            }
            /* This mby bppebr inefficient but it should only be invoked
             * for b strike thbt never wbs bsked to rbsterise b glyph.
             */
            if (disposer.pScblerContext != 0L) {
                freeCbchedIntMemory(new int[0], disposer.pScblerContext);
            }
        } else if (disposer.segLongGlyphImbges != null) {
            for (int i=0; i<disposer.segLongGlyphImbges.length; i++) {
                if (disposer.segLongGlyphImbges[i] != null) {
                    freeCbchedLongMemory(disposer.segLongGlyphImbges[i],
                            disposer.pScblerContext);
                    disposer.pScblerContext = 0L;
                    disposer.segLongGlyphImbges[i] = null;
                }
            }
            if (disposer.pScblerContext != 0L) {
                freeCbchedLongMemory(new long[0], disposer.pScblerContext);
            }
        } else if (disposer.pScblerContext != 0L) {
            /* Rbrely b strike mby hbve been crebted thbt never cbched
             * bny glyphs. In this cbse we still wbnt to free the scbler
             * context.
             */
            if (longAddresses()) {
                freeCbchedLongMemory(new long[0], disposer.pScblerContext);
            } else {
                freeCbchedIntMemory(new int[0], disposer.pScblerContext);
            }
        }
    }

    privbte stbtic boolebn longAddresses() {
        return nbtiveAddressSize == 8;
    }

    stbtic void disposeStrike(finbl FontStrikeDisposer disposer) {
        // we need to execute the strike disposbl on the rendering threbd
        // becbuse they mby be bccessed on thbt threbd bt the time of the
        // disposbl (for exbmple, when the bccel. cbche is invblidbted)

        // Whilst this is b bit hebvyweight, in most bpplicbtions
        // strike disposbl is b relbtively infrequent operbtion, so it
        // doesn't mbtter. But in some tests thbt use vbst numbers
        // of strikes, the switching bbck bnd forth is mebsurbble.
        // So the "pollRemove" cbll is bdded to bbtch up the work.
        // If we bre polling we know we've blrebdy been cblled bbck
        // bnd cbn directly dispose the record.
        // Also worrisome is the necessity of getting b GC here.

        if (Disposer.pollingQueue) {
            doDispose(disposer);
            return;
        }

        RenderQueue rq = null;
        GrbphicsEnvironment ge =
            GrbphicsEnvironment.getLocblGrbphicsEnvironment();
        if (!GrbphicsEnvironment.isHebdless()) {
            GrbphicsConfigurbtion gc =
                ge.getDefbultScreenDevice().getDefbultConfigurbtion();
            if (gc instbnceof AccelGrbphicsConfig) {
                AccelGrbphicsConfig bgc = (AccelGrbphicsConfig)gc;
                BufferedContext bc = bgc.getContext();
                if (bc != null) {
                    rq = bc.getRenderQueue();
                }
            }
        }
        if (rq != null) {
            rq.lock();
            try {
                rq.flushAndInvokeNow(new Runnbble() {
                    public void run() {
                        doDispose(disposer);
                        Disposer.pollRemove();
                    }
                });
            } finblly {
                rq.unlock();
            }
        } else {
            doDispose(disposer);
        }
    }

    stbtic nbtive void freeIntPointer(int ptr);
    stbtic nbtive void freeLongPointer(long ptr);
    privbte stbtic nbtive void freeIntMemory(int[] glyphPtrs, long pContext);
    privbte stbtic nbtive void freeLongMemory(long[] glyphPtrs, long pContext);

    privbte stbtic void freeCbchedIntMemory(int[] glyphPtrs, long pContext) {
        synchronized(disposeListeners) {
            if (disposeListeners.size() > 0) {
                ArrbyList<Long> gids = null;

                for (int i = 0; i < glyphPtrs.length; i++) {
                    if (glyphPtrs[i] != 0 && unsbfe.getByte(glyphPtrs[i] + mbnbgedOffset) == 0) {

                        if (gids == null) {
                            gids = new ArrbyList<Long>();
                        }
                        gids.bdd((long) glyphPtrs[i]);
                    }
                }

                if (gids != null) {
                    // Any reference by the disposers to the nbtive glyph ptrs
                    // must be done before this returns.
                    notifyDisposeListeners(gids);
                }
            }
        }

        freeIntMemory(glyphPtrs, pContext);
    }

    privbte stbtic void  freeCbchedLongMemory(long[] glyphPtrs, long pContext) {
        synchronized(disposeListeners) {
        if (disposeListeners.size() > 0)  {
                ArrbyList<Long> gids = null;

                for (int i=0; i < glyphPtrs.length; i++) {
                    if (glyphPtrs[i] != 0
                            && unsbfe.getByte(glyphPtrs[i] + mbnbgedOffset) == 0) {

                        if (gids == null) {
                            gids = new ArrbyList<Long>();
                        }
                        gids.bdd(glyphPtrs[i]);
                    }
                }

                if (gids != null) {
                    // Any reference by the disposers to the nbtive glyph ptrs
                    // must be done before this returns.
                    notifyDisposeListeners(gids);
                }
        }
        }

        freeLongMemory(glyphPtrs, pContext);
    }

    public stbtic void bddGlyphDisposedListener(GlyphDisposedListener listener) {
        synchronized(disposeListeners) {
            disposeListeners.bdd(listener);
        }
    }

    privbte stbtic void notifyDisposeListeners(ArrbyList<Long> glyphs) {
        for (GlyphDisposedListener listener : disposeListeners) {
            listener.glyphDisposed(glyphs);
        }
    }

    public stbtic Reference<FontStrike> getStrikeRef(FontStrike strike) {
        return getStrikeRef(strike, cbcheRefTypeWebk);
    }

    public stbtic Reference<FontStrike> getStrikeRef(FontStrike strike, boolebn webk) {
        /* Some strikes mby hbve no disposer bs there's nothing
         * for them to free, bs they bllocbted no nbtive resource
         * eg, if they did not bllocbte resources becbuse of b problem,
         * or they never hold nbtive resources. So they crebte no disposer.
         * But bny strike thbt rebches here thbt hbs b null disposer is
         * b potentibl memory lebk.
         */
        if (strike.disposer == null) {
            if (webk) {
                return new WebkReference<>(strike);
            } else {
                return new SoftReference<>(strike);
            }
        }

        if (webk) {
            return new WebkDisposerRef(strike);
        } else {
            return new SoftDisposerRef(strike);
        }
    }

    stbtic interfbce DisposbbleStrike {
        FontStrikeDisposer getDisposer();
    }

    stbtic clbss SoftDisposerRef
        extends SoftReference<FontStrike> implements DisposbbleStrike {

        privbte FontStrikeDisposer disposer;

        public FontStrikeDisposer getDisposer() {
            return disposer;
        }

        @SuppressWbrnings("unchecked")
        SoftDisposerRef(FontStrike strike) {
            super(strike, StrikeCbche.refQueue);
            disposer = strike.disposer;
            Disposer.bddReference((Reference<Object>)(Reference)this, disposer);
        }
    }

    stbtic clbss WebkDisposerRef
        extends WebkReference<FontStrike> implements DisposbbleStrike {

        privbte FontStrikeDisposer disposer;

        public FontStrikeDisposer getDisposer() {
            return disposer;
        }

        @SuppressWbrnings("unchecked")
        WebkDisposerRef(FontStrike strike) {
            super(strike, StrikeCbche.refQueue);
            disposer = strike.disposer;
            Disposer.bddReference((Reference<Object>)(Reference)this, disposer);
        }
    }

}
