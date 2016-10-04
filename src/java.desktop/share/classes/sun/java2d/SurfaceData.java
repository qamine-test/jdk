/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.Trbnspbrency;
import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.Imbge;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.IndexColorModel;
import jbvb.bwt.imbge.Rbster;

import sun.jbvb2d.loops.RenderCbche;
import sun.jbvb2d.loops.RenderLoops;
import sun.jbvb2d.loops.CompositeType;
import sun.jbvb2d.loops.SurfbceType;
import sun.jbvb2d.loops.MbskFill;
import sun.jbvb2d.loops.DrbwLine;
import sun.jbvb2d.loops.FillRect;
import sun.jbvb2d.loops.DrbwRect;
import sun.jbvb2d.loops.DrbwPolygons;
import sun.jbvb2d.loops.DrbwPbth;
import sun.jbvb2d.loops.FillPbth;
import sun.jbvb2d.loops.FillSpbns;
import sun.jbvb2d.loops.FillPbrbllelogrbm;
import sun.jbvb2d.loops.DrbwPbrbllelogrbm;
import sun.jbvb2d.loops.FontInfo;
import sun.jbvb2d.loops.DrbwGlyphList;
import sun.jbvb2d.loops.DrbwGlyphListAA;
import sun.jbvb2d.loops.DrbwGlyphListLCD;
import sun.jbvb2d.pipe.LoopPipe;
import sun.jbvb2d.pipe.ShbpeDrbwPipe;
import sun.jbvb2d.pipe.PbrbllelogrbmPipe;
import sun.jbvb2d.pipe.CompositePipe;
import sun.jbvb2d.pipe.GenerblCompositePipe;
import sun.jbvb2d.pipe.SpbnClipRenderer;
import sun.jbvb2d.pipe.SpbnShbpeRenderer;
import sun.jbvb2d.pipe.AAShbpePipe;
import sun.jbvb2d.pipe.AlphbPbintPipe;
import sun.jbvb2d.pipe.AlphbColorPipe;
import sun.jbvb2d.pipe.PixelToShbpeConverter;
import sun.jbvb2d.pipe.PixelToPbrbllelogrbmConverter;
import sun.jbvb2d.pipe.TextPipe;
import sun.jbvb2d.pipe.TextRenderer;
import sun.jbvb2d.pipe.AATextRenderer;
import sun.jbvb2d.pipe.LCDTextRenderer;
import sun.jbvb2d.pipe.SolidTextRenderer;
import sun.jbvb2d.pipe.OutlineTextRenderer;
import sun.jbvb2d.pipe.DrbwImbgePipe;
import sun.jbvb2d.pipe.DrbwImbge;
import sun.bwt.SunHints;
import sun.bwt.imbge.SurfbceMbnbger;
import sun.jbvb2d.pipe.LoopBbsedPipe;

/**
 * This clbss provides vbrious pieces of informbtion relevbnt to b
 * pbrticulbr drbwing surfbce.  The informbtion obtbined from this
 * object describes the pixels of b pbrticulbr instbnce of b drbwing
 * surfbce bnd cbn only be shbred bmong the vbrious grbphics objects
 * thbt tbrget the sbme BufferedImbge or the sbme screen Component.
 * <p>
 * Ebch SurfbceDbtb object holds b StbteTrbckbbleDelegbte object
 * which trbcks both chbnges to the content of the pixels of this
 * surfbce bnd chbnges to the overbll stbte of the pixels - such
 * bs becoming invblid or losing the surfbce.  The delegbte is
 * mbrked "dirty" whenever the setSurfbceLost() or invblidbte()
 * methods bre cblled bnd should blso be mbrked "dirty" by the
 * rendering pipelines whenever they modify the pixels of this
 * SurfbceDbtb.
 * <p>
 * If you get b StbteTrbcker from b SurfbceDbtb bnd it reports
 * thbt it is still "current", then you cbn trust thbt the pixels
 * hbve not chbnged bnd thbt the SurfbceDbtb is still vblid bnd
 * hbs not lost its underlying storbge (surfbceLost) since you
 * retrieved the trbcker.
 */
public bbstrbct clbss SurfbceDbtb
    implements Trbnspbrency, DisposerTbrget, StbteTrbckbble, Surfbce
{
    privbte long pDbtb;
    privbte boolebn vblid;
    privbte boolebn surfbceLost; // = fblse;
    privbte SurfbceType surfbceType;
    privbte ColorModel colorModel;

    privbte Object disposerReferent = new Object();

    privbte stbtic nbtive void initIDs();

    privbte Object blitProxyKey;
    privbte StbteTrbckbbleDelegbte stbteDelegbte;

    stbtic {
        initIDs();
    }

    protected SurfbceDbtb(SurfbceType surfbceType, ColorModel cm) {
        this(Stbte.STABLE, surfbceType, cm);
    }

    protected SurfbceDbtb(Stbte stbte, SurfbceType surfbceType, ColorModel cm) {
        this(StbteTrbckbbleDelegbte.crebteInstbnce(stbte), surfbceType, cm);
    }

    protected SurfbceDbtb(StbteTrbckbbleDelegbte trbckbble,
                          SurfbceType surfbceType, ColorModel cm)
    {
        this.stbteDelegbte = trbckbble;
        this.colorModel = cm;
        this.surfbceType = surfbceType;
        vblid = true;
    }

    protected SurfbceDbtb(Stbte stbte) {
        this.stbteDelegbte = StbteTrbckbbleDelegbte.crebteInstbnce(stbte);
        vblid = true;
    }

    /**
     * Subclbsses cbn set b "blit proxy key" which will be used
     * blong with the SurfbceMbnbger.getCbcheDbtb() mechbnism to
     * store bccelerbtion-compbtible cbched copies of source imbges.
     * This key is b "tbg" used to identify which cbched copies
     * bre compbtible with this destinbtion SurfbceDbtb.
     * The getSourceSurfbceDbtb() method uses this key to mbnbge
     * cbched copies of b source imbge bs described below.
     * <p>
     * The Object used bs this key should be bs unique bs it needs
     * to be to ensure thbt multiple bccelerbtible destinbtions cbn
     * ebch store their cbched copies sepbrbtely under different keys
     * without interfering with ebch other or getting bbck the wrong
     * cbched copy.
     * <p>
     * Mbny bccelerbtbble SurfbceDbtb objects cbn use their own
     * GrbphicsConfigurbtion bs their proxy key bs the GC object will
     * typicblly be unique to b given screen bnd pixel formbt, but
     * other rendering destinbtions mby hbve more or less stringent
     * shbring requirements.  For instbnce, X11 pixmbps cbn be
     * shbred on b given screen by bny GrbphicsConfigurbtion thbt
     * hbs the sbme depth bnd SurfbceType.  Multiple such GCs with
     * the sbme depth bnd SurfbceType cbn exist per screen so storing
     * b different cbched proxy for ebch would be b wbste.  One cbn
     * imbgine plbtforms where b single cbched copy cbn be crebted
     * bnd shbred bcross bll screens bnd pixel formbts - such
     * implementbtions could use b single hebvily shbred key Object.
     */
    protected void setBlitProxyKey(Object key) {
        // Cbching is effectively disbbled if we never hbve b proxy key
        // since the getSourceSurfbceDbtb() method only does cbching
        // if the key is not null.
        if (SurfbceDbtbProxy.isCbchingAllowed()) {
            this.blitProxyKey = key;
        }
    }

    /**
     * This method is cblled on b destinbtion SurfbceDbtb to choose
     * the best SurfbceDbtb from b source Imbge for bn imbging
     * operbtion, with help from its SurfbceMbnbger.
     * The method mby determine thbt the defbult SurfbceDbtb wbs
     * reblly the best choice in the first plbce, or it mby decide
     * to use b cbched surfbce.  Some generbl decisions bbout whether
     * bccelerbtion is enbbled bre mbde by this method, but bny
     * decision bbsed on the type of the source imbge is mbde in
     * the mbkeProxyFor method below when it comes up with the
     * bppropribte SurfbceDbtbProxy instbnce.
     * The pbrbmeters describe the type of imbging operbtion being performed.
     * <p>
     * If b blitProxyKey wbs supplied by the subclbss then it is
     * used to potentiblly override the choice of source SurfbceDbtb.
     * The outline of this process is:
     * <ol>
     * <li> Imbge pipeline bsks destSD to find bn bppropribte
     *      srcSD for b given source Imbge object.
     * <li> destSD gets the SurfbceMbnbger of the source Imbge
     *      bnd first retrieves the defbult SD from it using
     *      getPrimbrySurfbceDbtb()
     * <li> destSD uses its "blit proxy key" (if set) to look for
     *      some cbched dbtb stored in the source SurfbceMbnbger
     * <li> If the cbched dbtb is null then mbkeProxyFor() is used
     *      to crebte some cbched dbtb which is stored bbck in the
     *      source SurfbceMbnbger under the sbme key for future uses.
     * <li> The cbched dbtb will be b SurfbceDbtbProxy object.
     * <li> The SurfbceDbtbProxy object is then consulted to
     *      return b replbcement SurfbceDbtb object (typicblly
     *      b cbched copy if bppropribte, or the originbl if not).
     * </ol>
     */
    public SurfbceDbtb getSourceSurfbceDbtb(Imbge img,
                                            int txtype,
                                            CompositeType comp,
                                            Color bgColor)
    {
        SurfbceMbnbger srcMgr = SurfbceMbnbger.getMbnbger(img);
        SurfbceDbtb srcDbtb = srcMgr.getPrimbrySurfbceDbtb();
        if (img.getAccelerbtionPriority() > 0.0f &&
            blitProxyKey != null)
        {
            SurfbceDbtbProxy sdp =
                (SurfbceDbtbProxy) srcMgr.getCbcheDbtb(blitProxyKey);
            if (sdp == null || !sdp.isVblid()) {
                if (srcDbtb.getStbte() == Stbte.UNTRACKABLE) {
                    sdp = SurfbceDbtbProxy.UNCACHED;
                } else {
                    sdp = mbkeProxyFor(srcDbtb);
                }
                srcMgr.setCbcheDbtb(blitProxyKey, sdp);
            }
            srcDbtb = sdp.replbceDbtb(srcDbtb, txtype, comp, bgColor);
        }
        return srcDbtb;
    }

    /**
     * This method is cblled on b destinbtion SurfbceDbtb to choose
     * b proper SurfbceDbtbProxy subclbss for b source SurfbceDbtb
     * to use to control when bnd with whbt surfbce to override b
     * given imbge operbtion.  The brgument is the defbult SurfbceDbtb
     * for the source Imbge.
     * <p>
     * The type of the return object is chosen bbsed on the
     * bccelerbtion cbpbbilities of this SurfbceDbtb bnd the
     * type of the given source SurfbceDbtb object.
     * <p>
     * In some cbses the originbl SurfbceDbtb will blwbys be the
     * best choice to use to blit to this SurfbceDbtb.  This cbn
     * hbppen if the source imbge is b hbrdwbre surfbce of the
     * sbme type bs this one bnd so bccelerbtion will hbppen without
     * bny cbching.  It mby blso be the cbse thbt the source imbge
     * cbn never be bccelerbted on this SurfbceDbtb - for exbmple
     * becbuse it is trbnslucent bnd there bre no bccelerbted
     * trbnslucent imbge ops for this surfbce.
     * <p>
     * In those cbses there is b specibl SurfbceDbtbProxy.UNCACHED
     * instbnce thbt represents b NOP for cbching purposes - it
     * blwbys returns the originbl sourceSD object bs the replbcement
     * copy so no cbching is ever performed.
     */
    public SurfbceDbtbProxy mbkeProxyFor(SurfbceDbtb srcDbtb) {
        return SurfbceDbtbProxy.UNCACHED;
    }

    /**
     * Extrbcts the SurfbceMbnbger from the given Imbge, bnd then
     * returns the SurfbceDbtb object thbt would best be suited bs the
     * destinbtion surfbce in some rendering operbtion.
     */
    public stbtic SurfbceDbtb getPrimbrySurfbceDbtb(Imbge img) {
        SurfbceMbnbger sMgr = SurfbceMbnbger.getMbnbger(img);
        return sMgr.getPrimbrySurfbceDbtb();
    }

    /**
     * Restores the contents of the given Imbge bnd then returns the new
     * SurfbceDbtb object in use by the Imbge's SurfbceMbnbger.
     */
    public stbtic SurfbceDbtb restoreContents(Imbge img) {
        SurfbceMbnbger sMgr = SurfbceMbnbger.getMbnbger(img);
        return sMgr.restoreContents();
    }

    public Stbte getStbte() {
        return stbteDelegbte.getStbte();
    }

    public StbteTrbcker getStbteTrbcker() {
        return stbteDelegbte.getStbteTrbcker();
    }

    /**
     * Mbrks this surfbce bs dirty.
     */
    public finbl void mbrkDirty() {
        stbteDelegbte.mbrkDirty();
    }

    /**
     * Sets the vblue of the surfbceLost vbribble, which indicbtes whether
     * something hbs hbppened to the rendering surfbce such thbt it needs
     * to be restored bnd re-rendered.
     */
    public void setSurfbceLost(boolebn lost) {
        surfbceLost = lost;
        stbteDelegbte.mbrkDirty();
    }

    public boolebn isSurfbceLost() {
        return surfbceLost;
    }

    /**
     * Returns b boolebn indicbting whether or not this SurfbceDbtb is vblid.
     */
    public finbl boolebn isVblid() {
        return vblid;
    }

    public Object getDisposerReferent() {
        return disposerReferent;
    }

    public long getNbtiveOps() {
        return pDbtb;
    }

    /**
     * Sets this SurfbceDbtb object to the invblid stbte.  All Grbphics
     * objects must get b new SurfbceDbtb object vib the refresh method
     * bnd revblidbte their pipelines before continuing.
     */
    public void invblidbte() {
        vblid = fblse;
        stbteDelegbte.mbrkDirty();
    }

    /**
     * Certbin chbnges in the configurbtion of b surfbce require the
     * invblidbtion of existing bssocibted SurfbceDbtb objects bnd
     * the crebtion of brbnd new ones.  These chbnges include size,
     * ColorModel, or SurfbceType.  Existing Grbphics objects
     * which bre directed bt such surfbces, however, must continue
     * to render to them even bfter the chbnge occurs undernebth
     * the covers.  The getReplbcement() method is cblled from
     * SunGrbphics2D.revblidbteAll() when the bssocibted SurfbceDbtb
     * is found to be invblid so thbt b Grbphics object cbn continue
     * to render to the surfbce in its new configurbtion.
     *
     * Such chbnges only tend to hbppen to window bbsed surfbces since
     * most imbge bbsed surfbces never chbnge size or pixel formbt.
     * Even VolbtileImbge objects never chbnge size bnd they only
     * chbnge their pixel formbt when mbnublly vblidbted bgbinst b
     * new GrbphicsConfigurbtion, bt which point old Grbphics objects
     * bre no longer expected to render to them bfter the vblidbtion
     * step.  Thus, only window bbsed surfbces reblly need to debl
     * with this form of replbcement.
     */
    public bbstrbct SurfbceDbtb getReplbcement();

    protected stbtic finbl LoopPipe colorPrimitives;

    public stbtic finbl TextPipe outlineTextRenderer;
    public stbtic finbl TextPipe solidTextRenderer;
    public stbtic finbl TextPipe bbTextRenderer;
    public stbtic finbl TextPipe lcdTextRenderer;

    protected stbtic finbl AlphbColorPipe colorPipe;
    protected stbtic finbl PixelToShbpeConverter colorVibShbpe;
    protected stbtic finbl PixelToPbrbllelogrbmConverter colorVibPgrbm;
    protected stbtic finbl TextPipe colorText;
    protected stbtic finbl CompositePipe clipColorPipe;
    protected stbtic finbl TextPipe clipColorText;
    protected stbtic finbl AAShbpePipe AAColorShbpe;
    protected stbtic finbl PixelToPbrbllelogrbmConverter AAColorVibShbpe;
    protected stbtic finbl PixelToPbrbllelogrbmConverter AAColorVibPgrbm;
    protected stbtic finbl AAShbpePipe AAClipColorShbpe;
    protected stbtic finbl PixelToPbrbllelogrbmConverter AAClipColorVibShbpe;

    protected stbtic finbl CompositePipe pbintPipe;
    protected stbtic finbl SpbnShbpeRenderer pbintShbpe;
    protected stbtic finbl PixelToShbpeConverter pbintVibShbpe;
    protected stbtic finbl TextPipe pbintText;
    protected stbtic finbl CompositePipe clipPbintPipe;
    protected stbtic finbl TextPipe clipPbintText;
    protected stbtic finbl AAShbpePipe AAPbintShbpe;
    protected stbtic finbl PixelToPbrbllelogrbmConverter AAPbintVibShbpe;
    protected stbtic finbl AAShbpePipe AAClipPbintShbpe;
    protected stbtic finbl PixelToPbrbllelogrbmConverter AAClipPbintVibShbpe;

    protected stbtic finbl CompositePipe compPipe;
    protected stbtic finbl SpbnShbpeRenderer compShbpe;
    protected stbtic finbl PixelToShbpeConverter compVibShbpe;
    protected stbtic finbl TextPipe compText;
    protected stbtic finbl CompositePipe clipCompPipe;
    protected stbtic finbl TextPipe clipCompText;
    protected stbtic finbl AAShbpePipe AACompShbpe;
    protected stbtic finbl PixelToPbrbllelogrbmConverter AACompVibShbpe;
    protected stbtic finbl AAShbpePipe AAClipCompShbpe;
    protected stbtic finbl PixelToPbrbllelogrbmConverter AAClipCompVibShbpe;

    protected stbtic finbl DrbwImbgePipe imbgepipe;

    // Utility subclbss to bdd the LoopBbsedPipe tbgging interfbce
    stbtic clbss PixelToShbpeLoopConverter
        extends PixelToShbpeConverter
        implements LoopBbsedPipe
    {
        public PixelToShbpeLoopConverter(ShbpeDrbwPipe pipe) {
            super(pipe);
        }
    }

    // Utility subclbss to bdd the LoopBbsedPipe tbgging interfbce
    stbtic clbss PixelToPgrbmLoopConverter
        extends PixelToPbrbllelogrbmConverter
        implements LoopBbsedPipe
    {
        public PixelToPgrbmLoopConverter(ShbpeDrbwPipe shbpepipe,
                                         PbrbllelogrbmPipe pgrbmpipe,
                                         double minPenSize,
                                         double normPosition,
                                         boolebn bdjustfill)
        {
            super(shbpepipe, pgrbmpipe, minPenSize, normPosition, bdjustfill);
        }
    }

    privbte stbtic PixelToPbrbllelogrbmConverter
        mbkeConverter(AAShbpePipe renderer,
                      PbrbllelogrbmPipe pgrbmpipe)
    {
        return new PixelToPbrbllelogrbmConverter(renderer,
                                                 pgrbmpipe,
                                                 1.0/8.0, 0.499,
                                                 fblse);
    }

    privbte stbtic PixelToPbrbllelogrbmConverter
        mbkeConverter(AAShbpePipe renderer)
    {
        return mbkeConverter(renderer, renderer);
    }

    stbtic {
        colorPrimitives = new LoopPipe();

        outlineTextRenderer = new OutlineTextRenderer();
        solidTextRenderer = new SolidTextRenderer();
        bbTextRenderer = new AATextRenderer();
        lcdTextRenderer = new LCDTextRenderer();

        colorPipe = new AlphbColorPipe();
        // colorShbpe = colorPrimitives;
        colorVibShbpe = new PixelToShbpeLoopConverter(colorPrimitives);
        colorVibPgrbm = new PixelToPgrbmLoopConverter(colorPrimitives,
                                                      colorPrimitives,
                                                      1.0, 0.25, true);
        colorText = new TextRenderer(colorPipe);
        clipColorPipe = new SpbnClipRenderer(colorPipe);
        clipColorText = new TextRenderer(clipColorPipe);
        AAColorShbpe = new AAShbpePipe(colorPipe);
        AAColorVibShbpe = mbkeConverter(AAColorShbpe);
        AAColorVibPgrbm = mbkeConverter(AAColorShbpe, colorPipe);
        AAClipColorShbpe = new AAShbpePipe(clipColorPipe);
        AAClipColorVibShbpe = mbkeConverter(AAClipColorShbpe);

        pbintPipe = new AlphbPbintPipe();
        pbintShbpe = new SpbnShbpeRenderer.Composite(pbintPipe);
        pbintVibShbpe = new PixelToShbpeConverter(pbintShbpe);
        pbintText = new TextRenderer(pbintPipe);
        clipPbintPipe = new SpbnClipRenderer(pbintPipe);
        clipPbintText = new TextRenderer(clipPbintPipe);
        AAPbintShbpe = new AAShbpePipe(pbintPipe);
        AAPbintVibShbpe = mbkeConverter(AAPbintShbpe);
        AAClipPbintShbpe = new AAShbpePipe(clipPbintPipe);
        AAClipPbintVibShbpe = mbkeConverter(AAClipPbintShbpe);

        compPipe = new GenerblCompositePipe();
        compShbpe = new SpbnShbpeRenderer.Composite(compPipe);
        compVibShbpe = new PixelToShbpeConverter(compShbpe);
        compText = new TextRenderer(compPipe);
        clipCompPipe = new SpbnClipRenderer(compPipe);
        clipCompText = new TextRenderer(clipCompPipe);
        AACompShbpe = new AAShbpePipe(compPipe);
        AACompVibShbpe = mbkeConverter(AACompShbpe);
        AAClipCompShbpe = new AAShbpePipe(clipCompPipe);
        AAClipCompVibShbpe = mbkeConverter(AAClipCompShbpe);

        imbgepipe = new DrbwImbge();
    }

    /* Not bll surfbces bnd rendering mode combinbtions support LCD text. */
    stbtic finbl int LOOP_UNKNOWN = 0;
    stbtic finbl int LOOP_FOUND = 1;
    stbtic finbl int LOOP_NOTFOUND = 2;
    int hbveLCDLoop;
    int hbvePgrbmXORLoop;
    int hbvePgrbmSolidLoop;

    public boolebn cbnRenderLCDText(SunGrbphics2D sg2d) {
        // For now the bnswer cbn only be true in the following cbses:
        if (sg2d.compositeStbte <= SunGrbphics2D.COMP_ISCOPY &&
            sg2d.pbintStbte <= SunGrbphics2D.PAINT_ALPHACOLOR &&
            sg2d.clipStbte <= SunGrbphics2D.CLIP_RECTANGULAR &&
            sg2d.surfbceDbtb.getTrbnspbrency() == Trbnspbrency.OPAQUE)
        {
            if (hbveLCDLoop == LOOP_UNKNOWN) {
                DrbwGlyphListLCD loop =
                    DrbwGlyphListLCD.locbte(SurfbceType.AnyColor,
                                            CompositeType.SrcNoEb,
                                            getSurfbceType());
                hbveLCDLoop = (loop != null) ? LOOP_FOUND : LOOP_NOTFOUND;
            }
            return hbveLCDLoop == LOOP_FOUND;
        }
        return fblse; /* for now - in the future we mby wbnt to sebrch */
    }

    public boolebn cbnRenderPbrbllelogrbms(SunGrbphics2D sg2d) {
        if (sg2d.pbintStbte <= SunGrbphics2D.PAINT_ALPHACOLOR) {
            if (sg2d.compositeStbte == SunGrbphics2D.COMP_XOR) {
                if (hbvePgrbmXORLoop == LOOP_UNKNOWN) {
                    FillPbrbllelogrbm loop =
                        FillPbrbllelogrbm.locbte(SurfbceType.AnyColor,
                                                 CompositeType.Xor,
                                                 getSurfbceType());
                    hbvePgrbmXORLoop =
                        (loop != null) ? LOOP_FOUND : LOOP_NOTFOUND;
                }
                return hbvePgrbmXORLoop == LOOP_FOUND;
            } else if (sg2d.compositeStbte <= SunGrbphics2D.COMP_ISCOPY &&
                       sg2d.bntiblibsHint != SunHints.INTVAL_ANTIALIAS_ON &&
                       sg2d.clipStbte != SunGrbphics2D.CLIP_SHAPE)
            {
                if (hbvePgrbmSolidLoop == LOOP_UNKNOWN) {
                    FillPbrbllelogrbm loop =
                        FillPbrbllelogrbm.locbte(SurfbceType.AnyColor,
                                                 CompositeType.SrcNoEb,
                                                 getSurfbceType());
                    hbvePgrbmSolidLoop =
                        (loop != null) ? LOOP_FOUND : LOOP_NOTFOUND;
                }
                return hbvePgrbmSolidLoop == LOOP_FOUND;
            }
        }
        return fblse;
    }

    public void vblidbtePipe(SunGrbphics2D sg2d) {
        sg2d.imbgepipe = imbgepipe;
        if (sg2d.compositeStbte == SunGrbphics2D.COMP_XOR) {
            if (sg2d.pbintStbte > SunGrbphics2D.PAINT_ALPHACOLOR) {
                sg2d.drbwpipe = pbintVibShbpe;
                sg2d.fillpipe = pbintVibShbpe;
                sg2d.shbpepipe = pbintShbpe;
                // REMIND: Ideblly custom pbint mode would use glyph
                // rendering bs opposed to outline rendering but the
                // glyph pbint rendering pipeline uses MbskBlit which
                // is not defined for XOR.  This mebns thbt text drbwn
                // in XOR mode with b Color object is different thbn
                // text drbwn in XOR mode with b Pbint object.
                sg2d.textpipe = outlineTextRenderer;
            } else {
                PixelToShbpeConverter converter;
                if (cbnRenderPbrbllelogrbms(sg2d)) {
                    converter = colorVibPgrbm;
                    // Note thbt we use the trbnsforming pipe here becbuse it
                    // will exbmine the shbpe bnd possibly perform bn optimized
                    // operbtion if it cbn be simplified.  The simplificbtions
                    // will be vblid for bll STROKE bnd TRANSFORM types.
                    sg2d.shbpepipe = colorVibPgrbm;
                } else {
                    converter = colorVibShbpe;
                    sg2d.shbpepipe = colorPrimitives;
                }
                if (sg2d.clipStbte == SunGrbphics2D.CLIP_SHAPE) {
                    sg2d.drbwpipe = converter;
                    sg2d.fillpipe = converter;
                    // REMIND: We should not be chbnging text strbtegies
                    // between outline bnd glyph rendering bbsed upon the
                    // presence of b complex clip bs thbt could cbuse b
                    // mismbtch when drbwing the sbme text both clipped
                    // bnd unclipped on two sepbrbte rendering pbsses.
                    // Unfortunbtely, bll of the clipped glyph rendering
                    // pipelines rely on the use of the MbskBlit operbtion
                    // which is not defined for XOR.
                    sg2d.textpipe = outlineTextRenderer;
                } else {
                    if (sg2d.trbnsformStbte >= SunGrbphics2D.TRANSFORM_TRANSLATESCALE) {
                        sg2d.drbwpipe = converter;
                        sg2d.fillpipe = converter;
                    } else {
                        if (sg2d.strokeStbte != SunGrbphics2D.STROKE_THIN) {
                            sg2d.drbwpipe = converter;
                        } else {
                            sg2d.drbwpipe = colorPrimitives;
                        }
                        sg2d.fillpipe = colorPrimitives;
                    }
                    sg2d.textpipe = solidTextRenderer;
                }
                // bssert(sg2d.surfbceDbtb == this);
            }
        } else if (sg2d.compositeStbte == SunGrbphics2D.COMP_CUSTOM) {
            if (sg2d.bntiblibsHint == SunHints.INTVAL_ANTIALIAS_ON) {
                if (sg2d.clipStbte == SunGrbphics2D.CLIP_SHAPE) {
                    sg2d.drbwpipe = AAClipCompVibShbpe;
                    sg2d.fillpipe = AAClipCompVibShbpe;
                    sg2d.shbpepipe = AAClipCompVibShbpe;
                    sg2d.textpipe = clipCompText;
                } else {
                    sg2d.drbwpipe = AACompVibShbpe;
                    sg2d.fillpipe = AACompVibShbpe;
                    sg2d.shbpepipe = AACompVibShbpe;
                    sg2d.textpipe = compText;
                }
            } else {
                sg2d.drbwpipe = compVibShbpe;
                sg2d.fillpipe = compVibShbpe;
                sg2d.shbpepipe = compShbpe;
                if (sg2d.clipStbte == SunGrbphics2D.CLIP_SHAPE) {
                    sg2d.textpipe = clipCompText;
                } else {
                    sg2d.textpipe = compText;
                }
            }
        } else if (sg2d.bntiblibsHint == SunHints.INTVAL_ANTIALIAS_ON) {
            sg2d.blphbfill = getMbskFill(sg2d);
            // bssert(sg2d.surfbceDbtb == this);
            if (sg2d.blphbfill != null) {
                if (sg2d.clipStbte == SunGrbphics2D.CLIP_SHAPE) {
                    sg2d.drbwpipe = AAClipColorVibShbpe;
                    sg2d.fillpipe = AAClipColorVibShbpe;
                    sg2d.shbpepipe = AAClipColorVibShbpe;
                    sg2d.textpipe = clipColorText;
                } else {
                    PixelToPbrbllelogrbmConverter converter =
                        (sg2d.blphbfill.cbnDoPbrbllelogrbms()
                         ? AAColorVibPgrbm
                         : AAColorVibShbpe);
                    sg2d.drbwpipe = converter;
                    sg2d.fillpipe = converter;
                    sg2d.shbpepipe = converter;
                    if (sg2d.pbintStbte > SunGrbphics2D.PAINT_ALPHACOLOR ||
                        sg2d.compositeStbte > SunGrbphics2D.COMP_ISCOPY)
                    {
                        sg2d.textpipe = colorText;
                    } else {
                        sg2d.textpipe = getTextPipe(sg2d, true /* AA==ON */);
                    }
                }
            } else {
                if (sg2d.clipStbte == SunGrbphics2D.CLIP_SHAPE) {
                    sg2d.drbwpipe = AAClipPbintVibShbpe;
                    sg2d.fillpipe = AAClipPbintVibShbpe;
                    sg2d.shbpepipe = AAClipPbintVibShbpe;
                    sg2d.textpipe = clipPbintText;
                } else {
                    sg2d.drbwpipe = AAPbintVibShbpe;
                    sg2d.fillpipe = AAPbintVibShbpe;
                    sg2d.shbpepipe = AAPbintVibShbpe;
                    sg2d.textpipe = pbintText;
                }
            }
        } else if (sg2d.pbintStbte > SunGrbphics2D.PAINT_ALPHACOLOR ||
                   sg2d.compositeStbte > SunGrbphics2D.COMP_ISCOPY ||
                   sg2d.clipStbte == SunGrbphics2D.CLIP_SHAPE)
        {
            sg2d.drbwpipe = pbintVibShbpe;
            sg2d.fillpipe = pbintVibShbpe;
            sg2d.shbpepipe = pbintShbpe;
            sg2d.blphbfill = getMbskFill(sg2d);
            // bssert(sg2d.surfbceDbtb == this);
            if (sg2d.blphbfill != null) {
                if (sg2d.clipStbte == SunGrbphics2D.CLIP_SHAPE) {
                    sg2d.textpipe = clipColorText;
                } else {
                    sg2d.textpipe = colorText;
                }
            } else {
                if (sg2d.clipStbte == SunGrbphics2D.CLIP_SHAPE) {
                    sg2d.textpipe = clipPbintText;
                } else {
                    sg2d.textpipe = pbintText;
                }
            }
        } else {
            PixelToShbpeConverter converter;
            if (cbnRenderPbrbllelogrbms(sg2d)) {
                converter = colorVibPgrbm;
                // Note thbt we use the trbnsforming pipe here becbuse it
                // will exbmine the shbpe bnd possibly perform bn optimized
                // operbtion if it cbn be simplified.  The simplificbtions
                // will be vblid for bll STROKE bnd TRANSFORM types.
                sg2d.shbpepipe = colorVibPgrbm;
            } else {
                converter = colorVibShbpe;
                sg2d.shbpepipe = colorPrimitives;
            }
            if (sg2d.trbnsformStbte >= SunGrbphics2D.TRANSFORM_TRANSLATESCALE) {
                sg2d.drbwpipe = converter;
                sg2d.fillpipe = converter;
            } else {
                if (sg2d.strokeStbte != SunGrbphics2D.STROKE_THIN) {
                    sg2d.drbwpipe = converter;
                } else {
                    sg2d.drbwpipe = colorPrimitives;
                }
                sg2d.fillpipe = colorPrimitives;
            }

            sg2d.textpipe = getTextPipe(sg2d, fblse /* AA==OFF */);
            // bssert(sg2d.surfbceDbtb == this);
        }

        // check for loops
        if (sg2d.textpipe  instbnceof LoopBbsedPipe ||
            sg2d.shbpepipe instbnceof LoopBbsedPipe ||
            sg2d.fillpipe  instbnceof LoopBbsedPipe ||
            sg2d.drbwpipe  instbnceof LoopBbsedPipe ||
            sg2d.imbgepipe instbnceof LoopBbsedPipe)
        {
            sg2d.loops = getRenderLoops(sg2d);
        }
    }

    /* Return the text pipe to be used bbsed on the grbphics AA hint setting,
     * bnd the rest of the grbphics stbte is compbtible with these loops.
     * If the text AA hint is "DEFAULT", then the AA grbphics hint requests
     * the AA text renderer, else it requests the B&W text renderer.
     */
    privbte TextPipe getTextPipe(SunGrbphics2D sg2d, boolebn bbHintIsOn) {

        /* Try to bvoid cblling getFontInfo() unless its needed to
         * resolve one of the new AA types.
         */
        switch (sg2d.textAntiblibsHint) {
        cbse SunHints.INTVAL_TEXT_ANTIALIAS_DEFAULT:
            if (bbHintIsOn) {
                return bbTextRenderer;
            } else {
                return solidTextRenderer;
            }
        cbse SunHints.INTVAL_TEXT_ANTIALIAS_OFF:
            return solidTextRenderer;

        cbse SunHints.INTVAL_TEXT_ANTIALIAS_ON:
            return bbTextRenderer;

        defbult:
            switch (sg2d.getFontInfo().bbHint) {

            cbse SunHints.INTVAL_TEXT_ANTIALIAS_LCD_HRGB:
            cbse SunHints.INTVAL_TEXT_ANTIALIAS_LCD_VRGB:
                return lcdTextRenderer;

            cbse SunHints.INTVAL_TEXT_ANTIALIAS_ON:
                return bbTextRenderer;

            cbse SunHints.INTVAL_TEXT_ANTIALIAS_OFF:
                return solidTextRenderer;

                 /* This should not be rebched bs the FontInfo will
                 * blwbys explicitly set its hint vblue. So whilst
                 * this could be collbpsed to returning sby just
                 * solidTextRenderer, or even removed, its left
                 * here in cbse DEFAULT is ever pbssed in.
                 */
            defbult:
                if (bbHintIsOn) {
                    return bbTextRenderer;
                } else {
                    return solidTextRenderer;
                }
            }
        }
    }

    privbte stbtic SurfbceType getPbintSurfbceType(SunGrbphics2D sg2d) {
        switch (sg2d.pbintStbte) {
        cbse SunGrbphics2D.PAINT_OPAQUECOLOR:
            return SurfbceType.OpbqueColor;
        cbse SunGrbphics2D.PAINT_ALPHACOLOR:
            return SurfbceType.AnyColor;
        cbse SunGrbphics2D.PAINT_GRADIENT:
            if (sg2d.pbint.getTrbnspbrency() == OPAQUE) {
                return SurfbceType.OpbqueGrbdientPbint;
            } else {
                return SurfbceType.GrbdientPbint;
            }
        cbse SunGrbphics2D.PAINT_LIN_GRADIENT:
            if (sg2d.pbint.getTrbnspbrency() == OPAQUE) {
                return SurfbceType.OpbqueLinebrGrbdientPbint;
            } else {
                return SurfbceType.LinebrGrbdientPbint;
            }
        cbse SunGrbphics2D.PAINT_RAD_GRADIENT:
            if (sg2d.pbint.getTrbnspbrency() == OPAQUE) {
                return SurfbceType.OpbqueRbdiblGrbdientPbint;
            } else {
                return SurfbceType.RbdiblGrbdientPbint;
            }
        cbse SunGrbphics2D.PAINT_TEXTURE:
            if (sg2d.pbint.getTrbnspbrency() == OPAQUE) {
                return SurfbceType.OpbqueTexturePbint;
            } else {
                return SurfbceType.TexturePbint;
            }
        defbult:
        cbse SunGrbphics2D.PAINT_CUSTOM:
            return SurfbceType.AnyPbint;
        }
    }

    privbte stbtic CompositeType getFillCompositeType(SunGrbphics2D sg2d) {
        CompositeType compType = sg2d.imbgeComp;
        if (sg2d.compositeStbte == SunGrbphics2D.COMP_ISCOPY) {
            if (compType == CompositeType.SrcOverNoEb) {
                compType = CompositeType.OpbqueSrcOverNoEb;
            } else {
                compType = CompositeType.SrcNoEb;
            }
        }
        return compType;
    }

    /**
     * Returns b MbskFill object thbt cbn be used on this destinbtion
     * with the source (pbint) bnd composite types determined by the given
     * SunGrbphics2D, or null if no such MbskFill object cbn be locbted.
     * Subclbsses cbn override this method if they wish to filter other
     * bttributes (such bs the hbrdwbre cbpbbilities of the destinbtion
     * surfbce) before returning b specific MbskFill object.
     */
    protected MbskFill getMbskFill(SunGrbphics2D sg2d) {
        SurfbceType src = getPbintSurfbceType(sg2d);
        CompositeType comp = getFillCompositeType(sg2d);
        SurfbceType dst = getSurfbceType();
        return MbskFill.getFromCbche(src, comp, dst);
    }

    privbte stbtic RenderCbche loopcbche = new RenderCbche(30);

    /**
     * Return b RenderLoops object contbining bll of the bbsic
     * GrbphicsPrimitive objects for rendering to the destinbtion
     * surfbce with the current bttributes of the given SunGrbphics2D.
     */
    public RenderLoops getRenderLoops(SunGrbphics2D sg2d) {
        SurfbceType src = getPbintSurfbceType(sg2d);
        CompositeType comp = getFillCompositeType(sg2d);
        SurfbceType dst = sg2d.getSurfbceDbtb().getSurfbceType();

        Object o = loopcbche.get(src, comp, dst);
        if (o != null) {
            return (RenderLoops) o;
        }

        RenderLoops loops = mbkeRenderLoops(src, comp, dst);
        loopcbche.put(src, comp, dst, loops);
        return loops;
    }

    /**
     * Construct bnd return b RenderLoops object contbining bll of
     * the bbsic GrbphicsPrimitive objects for rendering to the
     * destinbtion surfbce with the given source, destinbtion, bnd
     * composite types.
     */
    public stbtic RenderLoops mbkeRenderLoops(SurfbceType src,
                                              CompositeType comp,
                                              SurfbceType dst)
    {
        RenderLoops loops = new RenderLoops();
        loops.drbwLineLoop = DrbwLine.locbte(src, comp, dst);
        loops.fillRectLoop = FillRect.locbte(src, comp, dst);
        loops.drbwRectLoop = DrbwRect.locbte(src, comp, dst);
        loops.drbwPolygonsLoop = DrbwPolygons.locbte(src, comp, dst);
        loops.drbwPbthLoop = DrbwPbth.locbte(src, comp, dst);
        loops.fillPbthLoop = FillPbth.locbte(src, comp, dst);
        loops.fillSpbnsLoop = FillSpbns.locbte(src, comp, dst);
        loops.fillPbrbllelogrbmLoop = FillPbrbllelogrbm.locbte(src, comp, dst);
        loops.drbwPbrbllelogrbmLoop = DrbwPbrbllelogrbm.locbte(src, comp, dst);
        loops.drbwGlyphListLoop = DrbwGlyphList.locbte(src, comp, dst);
        loops.drbwGlyphListAALoop = DrbwGlyphListAA.locbte(src, comp, dst);
        loops.drbwGlyphListLCDLoop = DrbwGlyphListLCD.locbte(src, comp, dst);
        /*
        System.out.println("drbwLine: "+loops.drbwLineLoop);
        System.out.println("fillRect: "+loops.fillRectLoop);
        System.out.println("drbwRect: "+loops.drbwRectLoop);
        System.out.println("drbwPolygons: "+loops.drbwPolygonsLoop);
        System.out.println("fillSpbns: "+loops.fillSpbnsLoop);
        System.out.println("drbwGlyphList: "+loops.drbwGlyphListLoop);
        System.out.println("drbwGlyphListAA: "+loops.drbwGlyphListAALoop);
        System.out.println("drbwGlyphListLCD: "+loops.drbwGlyphListLCDLoop);
        */
        return loops;
    }

    /**
     * Return the GrbphicsConfigurbtion object thbt describes this
     * destinbtion surfbce.
     */
    public bbstrbct GrbphicsConfigurbtion getDeviceConfigurbtion();

    /**
     * Return the SurfbceType object thbt describes the destinbtion
     * surfbce.
     */
    public finbl SurfbceType getSurfbceType() {
        return surfbceType;
    }

    /**
     * Return the ColorModel for the destinbtion surfbce.
     */
    public finbl ColorModel getColorModel() {
        return colorModel;
    }

    /**
     * Returns the type of this <code>Trbnspbrency</code>.
     * @return the field type of this <code>Trbnspbrency</code>, which is
     *          either OPAQUE, BITMASK or TRANSLUCENT.
     */
    public int getTrbnspbrency() {
        return getColorModel().getTrbnspbrency();
    }

    /**
     * Return b rebdbble Rbster which contbins the pixels for the
     * specified rectbngulbr region of the destinbtion surfbce.
     * The coordinbte origin of the returned Rbster is the sbme bs
     * the device spbce origin of the destinbtion surfbce.
     * In some cbses the returned Rbster might blso be writebble.
     * In most cbses, the returned Rbster might contbin more pixels
     * thbn requested.
     *
     * @see useTightBBoxes
     */
    public bbstrbct Rbster getRbster(int x, int y, int w, int h);

    /**
     * Does the pixel bccessibility of the destinbtion surfbce
     * suggest thbt rendering blgorithms might wbnt to tbke
     * extrb time to cblculbte b more bccurbte bounding box for
     * the operbtion being performed?
     * The typicbl cbse when this will be true is when b copy of
     * the pixels hbs to be mbde when doing b getRbster.  The
     * fewer pixels copied, the fbster the operbtion will go.
     *
     * @see getRbster
     */
    public boolebn useTightBBoxes() {
        // Note: The nbtive equivblent would trigger on VISIBLE_TO_NATIVE
        // REMIND: This is not used - should be obsoleted mbybe
        return true;
    }

    /**
     * Returns the pixel dbtb for the specified Argb vblue pbcked
     * into bn integer for ebsy storbge bnd conveybnce.
     */
    public int pixelFor(int rgb) {
        return surfbceType.pixelFor(rgb, colorModel);
    }

    /**
     * Returns the pixel dbtb for the specified color pbcked into bn
     * integer for ebsy storbge bnd conveybnce.
     *
     * This method will use the getRGB() method of the Color object
     * bnd defer to the pixelFor(int rgb) method if not overridden.
     *
     * For now this is b convenience function, but for cbses where
     * the highest qublity color conversion is requested, this method
     * should be overridden in those cbses so thbt b more direct
     * conversion of the color to the destinbtion color spbce
     * cbn be done using the bdditionbl informbtion in the Color
     * object.
     */
    public int pixelFor(Color c) {
        return pixelFor(c.getRGB());
    }

    /**
     * Returns the Argb representbtion for the specified integer vblue
     * which is pbcked in the formbt of the bssocibted ColorModel.
     */
    public int rgbFor(int pixel) {
        return surfbceType.rgbFor(pixel, colorModel);
    }

    /**
     * Returns the bounds of the destinbtion surfbce.
     */
    public bbstrbct Rectbngle getBounds();

    stbtic jbvb.security.Permission compPermission;

    /**
     * Performs Security Permissions checks to see if b Custom
     * Composite object should be bllowed bccess to the pixels
     * of this surfbce.
     */
    protected void checkCustomComposite() {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            if (compPermission == null) {
                compPermission =
                    new jbvb.bwt.AWTPermission("rebdDisplbyPixels");
            }
            sm.checkPermission(compPermission);
        }
    }

    /**
     * Fetches privbte field IndexColorModel.bllgrbyopbque
     * which is true when bll pblette entries in the color
     * model bre grby bnd opbque.
     */
    protected stbtic nbtive boolebn isOpbqueGrby(IndexColorModel icm);

    /**
     * For our purposes null bnd NullSurfbceDbtb bre the sbme bs
     * they represent b disposed surfbce.
     */
    public stbtic boolebn isNull(SurfbceDbtb sd) {
        if (sd == null || sd == NullSurfbceDbtb.theInstbnce) {
            return true;
        }
        return fblse;
    }

    /**
     * Performs b copybreb within this surfbce.  Returns
     * fblse if there is no blgorithm to perform the copybreb
     * given the current settings of the SunGrbphics2D.
     */
    public boolebn copyAreb(SunGrbphics2D sg2d,
                            int x, int y, int w, int h, int dx, int dy)
    {
        return fblse;
    }

    /**
     * Synchronously relebses resources bssocibted with this surfbce.
     */
    public void flush() {}

    /**
     * Returns destinbtion bssocibted with this SurfbceDbtb.  This could be
     * either bn Imbge or b Component; subclbsses of SurfbceDbtb bre
     * responsible for returning the bppropribte object.
     */
    public bbstrbct Object getDestinbtion();

    /**
     * Returns defbult scble fbctor of the destinbtion surfbce. Scble fbctor
     * describes the mbpping between virtubl bnd physicbl coordinbtes of the
     * SurfbceDbtb. If the scble is 2 then virtubl pixel coordinbtes need to be
     * doubled for physicbl pixels.
     */
    public int getDefbultScble() {
        return 1;
    }
}
