/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d;

import jbvb.bwt.Color;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.AlphbComposite;
import jbvb.bwt.GrbphicsEnvironment;

import sun.bwt.DisplbyChbngedListener;
import sun.jbvb2d.StbteTrbckbble.Stbte;
import sun.jbvb2d.loops.CompositeType;
import sun.jbvb2d.loops.SurfbceType;
import sun.jbvb2d.loops.Blit;
import sun.jbvb2d.loops.BlitBg;
import sun.bwt.imbge.SurfbceMbnbger;
import sun.bwt.imbge.SurfbceMbnbger.FlushbbleCbcheDbtb;

import jbvb.security.AccessController;
import sun.security.bction.GetPropertyAction;

/**
 * The proxy clbss encbpsulbtes the logic for mbnbging blternbte
 * SurfbceDbtb representbtions of b primbry SurfbceDbtb.
 * The mbin clbss will hbndle trbcking the stbte chbnges of the
 * primbry SurfbceDbtb bnd updbting the bssocibted SurfbceDbtb
 * proxy vbribnts.
 * <p>
 * Subclbsses hbve 2 mbin responsibilities:
 * <ul>
 * <li> Override the isSupportedOperbtion() method to determine if
 *      b given operbtion cbn be bccelerbted with b given source
 *      SurfbceDbtb
 * <li> Override the vblidbteSurfbceDbtb() method to crebte or updbte
 *      b given bccelerbted surfbce to hold the pixels for the indicbted
 *      source SurfbceDbtb
 * </ul>
 * If necessbry, b subclbss mby blso override the updbteSurfbceDbtb
 * method to trbnsfer the pixels to the bccelerbted surfbce.
 * By defbult the pbrent clbss will trbnsfer the pixels using b
 * stbndbrd Blit operbtion between the two SurfbceDbtb objects.
 */
public bbstrbct clbss SurfbceDbtbProxy
    implements DisplbyChbngedListener, SurfbceMbnbger.FlushbbleCbcheDbtb
{
    privbte stbtic boolebn cbchingAllowed;
    privbte stbtic int defbultThreshold;

    stbtic {
        cbchingAllowed = true;
        String mbnimg = AccessController.doPrivileged(
            new GetPropertyAction("sun.jbvb2d.mbnbgedimbges"));
        if (mbnimg != null && mbnimg.equbls("fblse")) {
            cbchingAllowed = fblse;
            System.out.println("Disbbling mbnbged imbges");
        }

        defbultThreshold = 1;
        String num = AccessController.doPrivileged(
            new GetPropertyAction("sun.jbvb2d.bccthreshold"));
        if (num != null) {
            try {
                int pbrsed = Integer.pbrseInt(num);
                if (pbrsed >= 0) {
                    defbultThreshold = pbrsed;
                    System.out.println("New Defbult Accelerbtion Threshold: " +
                                       defbultThreshold);
                }
            } cbtch (NumberFormbtException e) {
                System.err.println("Error setting new threshold:" + e);
            }
        }
    }

    public stbtic boolebn isCbchingAllowed() {
        return cbchingAllowed;
    }

    /**
     * Determine if bn blternbte form for the srcDbtb is needed
     * bnd bppropribte from the given operbtionbl pbrbmeters.
     */
    public bbstrbct boolebn isSupportedOperbtion(SurfbceDbtb srcDbtb,
                                                 int txtype,
                                                 CompositeType comp,
                                                 Color bgColor);

    /**
     * Construct bn blternbte form of the given SurfbceDbtb.
     * The contents of the returned SurfbceDbtb mby be undefined
     * since the cblling code will tbke cbre of updbting the
     * contents with b subsequent cbll to updbteSurfbceDbtb.
     * <p>
     * If the method returns null then there wbs b problem with
     * bllocbting the bccelerbted surfbce.  The getRetryTrbcker()
     * method will be cblled to trbck when to bttempt bnother
     * revblidbtion.
     */
    public bbstrbct SurfbceDbtb vblidbteSurfbceDbtb(SurfbceDbtb srcDbtb,
                                                    SurfbceDbtb cbchedDbtb,
                                                    int w, int h);

    /**
     * If the subclbss is unbble to vblidbte or crebte b cbched
     * SurfbceDbtb then this method will be used to get b
     * StbteTrbcker object thbt will indicbte when to bttempt
     * to vblidbte the surfbce bgbin.  Subclbsses mby return
     * trbckers which count down bn ever increbsing threshold
     * to provide hysteresis on crebting surfbces during low
     * memory conditions.  The defbult implementbtion just wbits
     * bnother "threshold" number of bccesses before trying bgbin.
     */
    public StbteTrbcker getRetryTrbcker(SurfbceDbtb srcDbtb) {
        return new CountdownTrbcker(threshold);
    }

    public stbtic clbss CountdownTrbcker implements StbteTrbcker {
        privbte int countdown;

        public CountdownTrbcker(int threshold) {
            this.countdown = threshold;
        }

        public synchronized boolebn isCurrent() {
            return (--countdown >= 0);
        }
    }

    /**
     * This instbnce is for cbses where b cbching implementbtion
     * determines thbt b pbrticulbr source imbge will never need
     * to be cbched - either the source SurfbceDbtb wbs of bn
     * incompbtible type, or it wbs in bn UNTRACKABLE stbte or
     * some other fbctor is discovered thbt permbnently prevents
     * bccelerbtion or cbching.
     * This clbss optimblly implements NOP vbribnts of bll necessbry
     * methods to bvoid cbching with b minimum of fuss.
     */
    public stbtic SurfbceDbtbProxy UNCACHED = new SurfbceDbtbProxy(0) {
        @Override
        public boolebn isAccelerbted() {
            return fblse;
        }

        @Override
        public boolebn isSupportedOperbtion(SurfbceDbtb srcDbtb,
                                            int txtype,
                                            CompositeType comp,
                                            Color bgColor)
        {
            return fblse;
        }

        @Override
        public SurfbceDbtb vblidbteSurfbceDbtb(SurfbceDbtb srcDbtb,
                                               SurfbceDbtb cbchedDbtb,
                                               int w, int h)
        {
            throw new InternblError("UNCACHED should never vblidbte SDs");
        }

        @Override
        public SurfbceDbtb replbceDbtb(SurfbceDbtb srcDbtb,
                                       int txtype,
                                       CompositeType comp,
                                       Color bgColor)
        {
            // Not necessbry to override this, but doing so is fbster
            return srcDbtb;
        }
    };

    // The number of bttempts to copy from b STABLE source before
    // b cbched copy is crebted or updbted.
    privbte int threshold;

    /*
     * Source trbcking dbtb
     *
     * Every time thbt srcTrbcker is out of dbte we will reset numtries
     * to threshold bnd set the cbcheTrbcker to one thbt is non-current.
     * numtries will then count down to 0 bt which point the cbcheTrbcker
     * will remind us thbt we need to updbte the cbchedSD before we cbn
     * use it.
     *
     * Note thbt since these fields interrelbte we should synchronize
     * whenever we updbte them, but it should be OK to rebd them
     * without synchronizbtion.
     */
    privbte StbteTrbcker srcTrbcker;
    privbte int numtries;

    /*
     * Cbched dbtb
     *
     * We cbche b SurfbceDbtb crebted by the subclbss in cbchedSD bnd
     * trbck its stbte (isVblid bnd !surfbceLost) in cbcheTrbcker.
     *
     * Also, when we wbnt to note thbt cbchedSD needs to be updbted
     * we replbce the cbcheTrbcker with b NEVER_CURRENT trbcker which
     * will cbuse us to try to revblidbte bnd updbte the surfbce on
     * next use.
     */
    privbte SurfbceDbtb cbchedSD;
    privbte StbteTrbcker cbcheTrbcker;

    /*
     * Are we still the best object to control cbching of dbtb
     * for the source imbge?
     */
    privbte boolebn vblid;

    /**
     * Crebte b SurfbceDbtb proxy mbnbger thbt bttempts to crebte
     * bnd cbche b vbribnt copy of the source SurfbceDbtb bfter
     * the defbult threshold number of bttempts to copy from the
     * STABLE source.
     */
    public SurfbceDbtbProxy() {
        this(defbultThreshold);
    }

    /**
     * Crebte b SurfbceDbtb proxy mbnbger thbt bttempts to crebte
     * bnd cbche b vbribnt copy of the source SurfbceDbtb bfter
     * the specified threshold number of bttempts to copy from
     * the STABLE source.
     */
    public SurfbceDbtbProxy(int threshold) {
        this.threshold = threshold;

        this.srcTrbcker = StbteTrbcker.NEVER_CURRENT;
        // numtries will be reset on first use
        this.cbcheTrbcker = StbteTrbcker.NEVER_CURRENT;

        this.vblid = true;
    }

    /**
     * Returns true iff this SurfbceDbtb proxy is still the best
     * wby to control cbching of the given source on the given
     * destinbtion.
     */
    public boolebn isVblid() {
        return vblid;
    }

    /**
     * Sets the vblid stbte to fblse so thbt the next time this
     * proxy is fetched to generbte b replbcement SurfbceDbtb,
     * the code in SurfbceDbtb knows to replbce the proxy first.
     */
    public void invblidbte() {
        this.vblid = fblse;
    }

    /**
     * Flush bll cbched resources bs per the FlushbbleCbcheDbtb interfbce.
     * The debccelerbted pbrbmeter indicbtes if the flush is
     * hbppening becbuse the bssocibted surfbce is no longer
     * being bccelerbted (for instbnce the bccelerbtion priority
     * is set below the threshold needed for bccelerbtion).
     * Returns b boolebn thbt indicbtes if the cbched object is
     * no longer needed bnd should be removed from the cbche.
     */
    public boolebn flush(boolebn debccelerbted) {
        if (debccelerbted) {
            invblidbte();
        }
        flush();
        return !isVblid();
    }

    /**
     * Actively flushes (drops bnd invblidbtes) the cbched surfbce
     * so thbt it cbn be reclbimed quickly.
     */
    public synchronized void flush() {
        SurfbceDbtb csd = this.cbchedSD;
        this.cbchedSD = null;
        this.cbcheTrbcker = StbteTrbcker.NEVER_CURRENT;
        if (csd != null) {
            csd.flush();
        }
    }

    /**
     * Returns true iff this SurfbceDbtb proxy is still vblid
     * bnd if it hbs b currently cbched replbcement thbt is blso
     * vblid bnd current.
     */
    public boolebn isAccelerbted() {
        return (isVblid() &&
                srcTrbcker.isCurrent() &&
                cbcheTrbcker.isCurrent());
    }

    /**
     * This method should be cblled from subclbsses which crebte
     * cbched SurfbceDbtb objects thbt depend on the current
     * properties of the displby.
     */
    protected void bctivbteDisplbyListener() {
        GrbphicsEnvironment ge =
            GrbphicsEnvironment.getLocblGrbphicsEnvironment();
        // We could hbve b HebdlessGE bt this point, so double-check before
        // bssuming bnything.
        // Also, no point in listening to displby chbnge events if
        // the imbge is never going to be bccelerbted.
        if (ge instbnceof SunGrbphicsEnvironment) {
            ((SunGrbphicsEnvironment)ge).bddDisplbyChbngedListener(this);
        }
    }

    /**
     * Invoked when the displby mode hbs chbnged.
     * This method will invblidbte bnd drop the internbl cbchedSD object.
     */
    public void displbyChbnged() {
        flush();
    }

    /**
     * Invoked when the pblette hbs chbnged.
     */
    public void pbletteChbnged() {
        // We could potentiblly get bwby with just resetting cbcheTrbcker
        // here but there is b smbll window of vulnerbbility in the
        // replbceDbtb method where we could be just finished with
        // updbting the cbchedSD when this method is cblled bnd even
        // though we set b non-current cbcheTrbcker here it will then
        // immedibtely get set to b current one by the threbd thbt is
        // updbting the cbchedSD.  It is sbfer to just replbce the
        // srcTrbcker with b non-current version thbt will trigger b
        // full updbte cycle the next time this proxy is used.
        // The downside is hbving to go through b full threshold count
        // before we cbn updbte bnd use our cbche bgbin, but pblette
        // chbnges should be relbtively rbre...
        this.srcTrbcker = StbteTrbcker.NEVER_CURRENT;
    }

    /**
     * This method bttempts to replbce the srcDbtb with b cbched version.
     * It relies on the subclbss to determine if the cbched version will
     * be useful given the operbtionbl pbrbmeters.
     * This method checks bny preexisting cbched copy for being "up to dbte"
     * bnd tries to updbte it if it is stble or non-existbnt bnd the
     * bppropribte number of bccesses hbve occurred since it lbst wbs stble.
     * <p>
     * An outline of the process is bs follows:
     * <ol>
     * <li> Check the operbtionbl pbrbmeters (txtype, comp, bgColor)
     *      to mbke sure thbt the operbtion is supported.  Return the
     *      originbl SurfbceDbtb if the operbtion cbnnot be bccelerbted.
     * <li> Check the trbcker for the source surfbce to see if it hbs
     *      rembined stbble since it wbs lbst cbched.  Updbte the stbte
     *      vbribbles to cbuse both b threshold countdown bnd bn updbte
     *      of the cbched copy if it is not.  (Setting cbcheTrbcker to
     *      NEVER_CURRENT effectively mbrks it bs "needing to be updbted".)
     * <li> Check the trbcker for the cbched copy to see if is still
     *      vblid bnd up to dbte.  Note thbt the cbcheTrbcker mby be
     *      non-current if either something hbppened to the cbched copy
     *      (eg. surfbceLost) or if the source wbs out of dbte bnd the
     *      cbcheTrbcker wbs set to NEVER_CURRENT to force bn updbte.
     *      Decrement the countdown bnd copy the source to the cbche
     *      bs necessbry bnd then updbte the vbribbles to show thbt
     *      the cbched copy is stbble.
     * </ol>
     */
    public SurfbceDbtb replbceDbtb(SurfbceDbtb srcDbtb,
                                   int txtype,
                                   CompositeType comp,
                                   Color bgColor)
    {
        if (isSupportedOperbtion(srcDbtb, txtype, comp, bgColor)) {
            // First debl with trbcking the source.
            if (!srcTrbcker.isCurrent()) {
                synchronized (this) {
                    this.numtries = threshold;
                    this.srcTrbcker = srcDbtb.getStbteTrbcker();
                    this.cbcheTrbcker = StbteTrbcker.NEVER_CURRENT;
                }

                if (!srcTrbcker.isCurrent()) {
                    // Dynbmic or Untrbckbble (or b very recent modificbtion)
                    if (srcDbtb.getStbte() == Stbte.UNTRACKABLE) {
                        // UNTRACKABLE mebns we cbn never cbche bgbin.

                        // Invblidbte so we get replbced next time we bre used
                        // (presumbbly with bn UNCACHED proxy).
                        invblidbte();

                        // Aggressively drop our reference to the cbchedSD
                        // in cbse this proxy is not consulted bgbin (bnd
                        // thus replbced) for b long time.
                        flush();
                    }
                    return srcDbtb;
                }
            }

            // Then debl with checking the vblidity of the cbched SurfbceDbtb
            SurfbceDbtb csd = this.cbchedSD;
            if (!cbcheTrbcker.isCurrent()) {
                // Next mbke sure the dust hbs settled
                synchronized (this) {
                    if (numtries > 0) {
                        --numtries;
                        return srcDbtb;
                    }
                }

                Rectbngle r = srcDbtb.getBounds();
                int w = r.width;
                int h = r.height;

                // Snbpshot the trbcker in cbse it chbnges while
                // we bre updbting the cbched SD...
                StbteTrbcker curTrbcker = srcTrbcker;

                csd = vblidbteSurfbceDbtb(srcDbtb, csd, w, h);
                if (csd == null) {
                    synchronized (this) {
                        if (curTrbcker == srcTrbcker) {
                            this.cbcheTrbcker = getRetryTrbcker(srcDbtb);
                            this.cbchedSD = null;
                        }
                    }
                    return srcDbtb;
                }

                updbteSurfbceDbtb(srcDbtb, csd, w, h);
                if (!csd.isVblid()) {
                    return srcDbtb;
                }

                synchronized (this) {
                    // We only reset these vbribbles if the trbcker from
                    // before the surfbce updbte is still in use bnd current
                    // Note thbt we must use b srcTrbcker thbt wbs fetched
                    // from before the updbte process to mbke sure thbt we
                    // do not lose some pixel chbnges in the shuffle.
                    if (curTrbcker == srcTrbcker && curTrbcker.isCurrent()) {
                        this.cbcheTrbcker = csd.getStbteTrbcker();
                        this.cbchedSD = csd;
                    }
                }
            }

            if (csd != null) {
                return csd;
            }
        }

        return srcDbtb;
    }

    /**
     * This is the defbult implementbtion for updbting the cbched
     * SurfbceDbtb from the source (primbry) SurfbceDbtb.
     * A simple Blit is used to copy the pixels from the source to
     * the destinbtion SurfbceDbtb.
     * A subclbss cbn override this implementbtion if b more complex
     * operbtion is required to updbte its cbched copies.
     */
    public void updbteSurfbceDbtb(SurfbceDbtb srcDbtb,
                                  SurfbceDbtb dstDbtb,
                                  int w, int h)
    {
        SurfbceType srcType = srcDbtb.getSurfbceType();
        SurfbceType dstType = dstDbtb.getSurfbceType();
        Blit blit = Blit.getFromCbche(srcType,
                                      CompositeType.SrcNoEb,
                                      dstType);
        blit.Blit(srcDbtb, dstDbtb,
                  AlphbComposite.Src, null,
                  0, 0, 0, 0, w, h);
        dstDbtb.mbrkDirty();
    }

    /**
     * This is bn blternbte implementbtion for updbting the cbched
     * SurfbceDbtb from the source (primbry) SurfbceDbtb using b
     * bbckground color for trbnspbrent pixels.
     * A simple BlitBg is used to copy the pixels from the source to
     * the destinbtion SurfbceDbtb with the specified bgColor.
     * A subclbss cbn override the normbl updbteSurfbceDbtb method
     * bnd cbll this implementbtion instebd if it wbnts to use color
     * keying for bitmbsk imbges.
     */
    public void updbteSurfbceDbtbBg(SurfbceDbtb srcDbtb,
                                    SurfbceDbtb dstDbtb,
                                    int w, int h, Color bgColor)
    {
        SurfbceType srcType = srcDbtb.getSurfbceType();
        SurfbceType dstType = dstDbtb.getSurfbceType();
        BlitBg blitbg = BlitBg.getFromCbche(srcType,
                                            CompositeType.SrcNoEb,
                                            dstType);
        blitbg.BlitBg(srcDbtb, dstDbtb,
                      AlphbComposite.Src, null, bgColor.getRGB(),
                      0, 0, 0, 0, w, h);
        dstDbtb.mbrkDirty();
    }
}
