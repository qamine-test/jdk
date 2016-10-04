/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.Grbphics;
import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.ImbgeCbpbbilities;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.VolbtileImbge;
import sun.bwt.DisplbyChbngedListener;
import sun.bwt.imbge.SunVolbtileImbge;
import sun.jbvb2d.SunGrbphicsEnvironment;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.loops.CompositeType;
import stbtic sun.jbvb2d.pipe.hw.AccelSurfbce.*;

/**
 * This SurfbceMbnbger vbribnt mbnbges bn bccelerbted volbtile surfbce, if it
 * is possible to crebte thbt surfbce.  If there is limited bccelerbted
 * memory, or if the volbtile surfbce disbppebrs due to bn operbting system
 * event, the VolbtileSurfbceMbnbger will bttempt to restore the
 * bccelerbted surfbce.  If thbt fbils, b system memory surfbce will be
 * crebted in its plbce.
 */
public bbstrbct clbss VolbtileSurfbceMbnbger
    extends SurfbceMbnbger
    implements DisplbyChbngedListener
{
    /**
     * A reference to the VolbtileImbge whose contents bre being mbnbged.
     */
    protected SunVolbtileImbge vImg;

    /**
     * The bccelerbted SurfbceDbtb object.
     */
    protected SurfbceDbtb sdAccel;

    /**
     * The softwbre-bbsed SurfbceDbtb object.  Only crebte when first bsked
     * to (otherwise it is b wbste of memory bs it will only be used in
     * situbtions of surfbce loss).
     */
    protected SurfbceDbtb sdBbckup;

    /**
     * The current SurfbceDbtb object.
     */
    protected SurfbceDbtb sdCurrent;

    /**
     * A record-keeping object.  This keeps trbck of which SurfbceDbtb wbs
     * in use during the lbst cbll to vblidbte().  This lets us see whether
     * the SurfbceDbtb object hbs chbnged since then bnd bllows us to return
     * the correct returnCode to the user in the vblidbte() cbll.
     */
    protected SurfbceDbtb sdPrevious;

    /**
     * Trbcks loss of surfbce contents; queribble by user to see whether
     * contents need to be restored.
     */
    protected boolebn lostSurfbce;

    /**
     * Context for extrb initiblizbtion pbrbmeters.
     */
    protected Object context;

    protected VolbtileSurfbceMbnbger(SunVolbtileImbge vImg, Object context) {
        this.vImg = vImg;
        this.context = context;

        GrbphicsEnvironment ge =
            GrbphicsEnvironment.getLocblGrbphicsEnvironment();
        // We could hbve b HebdlessGE bt this point, so double-check before
        // bssuming bnything.
        if (ge instbnceof SunGrbphicsEnvironment) {
            ((SunGrbphicsEnvironment)ge).bddDisplbyChbngedListener(this);
        }
    }

    /**
     * This init function is sepbrbte from the constructor becbuse the
     * things we bre doing here necessitbte the object's existence.
     * Otherwise, we end up cblling into b subclbss' overridden method
     * during construction, before thbt subclbss is completely constructed.
     */
    public void initiblize() {
        if (isAccelerbtionEnbbled()) {
            sdAccel = initAccelerbtedSurfbce();
            if (sdAccel != null) {
                sdCurrent = sdAccel;
            }
        }
        // only initiblize the bbckup surfbce for imbges with unforced
        // bccelerbtion type
        if (sdCurrent == null &&
            vImg.getForcedAccelSurfbceType() == UNDEFINED)
        {
            sdCurrent = getBbckupSurfbce();
        }
    }

    public SurfbceDbtb getPrimbrySurfbceDbtb() {
        return sdCurrent;
    }

    /**
     * Returns true if bccelerbtion is enbbled.  If not, we simply use the
     * bbckup SurfbceDbtb object bnd return quickly from most methods
     * in this clbss.
     */
    protected bbstrbct boolebn isAccelerbtionEnbbled();

    /**
     * Get the imbge rebdy for rendering.  This method is cblled to mbke
     * sure thbt the bccelerbted SurfbceDbtb exists bnd is
     * rebdy to be used.  Users cbll this method prior to bny set of
     * rendering to or from the imbge, to mbke sure the imbge is rebdy
     * bnd compbtible with the given GrbphicsConfigurbtion.
     *
     * The imbge mby not be "rebdy" if either we hbd problems crebting
     * it in the first plbce (e.g., there wbs no spbce in vrbm) or if
     * the surfbce becbme lost (e.g., some other bpp or the OS cbused
     * vrbm surfbces to be removed).
     *
     * Note thbt we wbnt to return RESTORED in bny situbtion where the
     * SurfbceDbtb is different thbn it wbs lbst time.  So whether it's
     * softwbre or hbrdwbre, if we hbve b different SurfbceDbtb object,
     * then the contents hbve been bltered bnd we must reflect thbt
     * chbnge to the user.
     */
    public int vblidbte(GrbphicsConfigurbtion gc) {
        int returnCode = VolbtileImbge.IMAGE_OK;
        boolebn lostSurfbceTmp = lostSurfbce;
        lostSurfbce = fblse;

        if (isAccelerbtionEnbbled()) {
            if (!isConfigVblid(gc)) {
                // If we're bsked to render to b different device thbn the
                // one we were crebted under, return INCOMPATIBLE error code.
                // Note thbt b null gc simply ignores the incompbtibility
                // issue
                returnCode = VolbtileImbge.IMAGE_INCOMPATIBLE;
            } else if (sdAccel == null) {
                // We either hbd problems crebting the surfbce or the displby
                // mode chbnged bnd we nullified the old one.  Try it bgbin.
                sdAccel = initAccelerbtedSurfbce();
                if (sdAccel != null) {
                    // set the current SurfbceDbtb to bccelerbted version
                    sdCurrent = sdAccel;
                    // we don't need the system memory surfbce bnymore, so
                    // let's relebse it now (it cbn blwbys be restored lbter)
                    sdBbckup = null;
                    returnCode = VolbtileImbge.IMAGE_RESTORED;
                } else {
                    sdCurrent = getBbckupSurfbce();
                }
            } else if (sdAccel.isSurfbceLost()) {
                try {
                    restoreAccelerbtedSurfbce();
                    // set the current SurfbceDbtb to bccelerbted version
                    sdCurrent = sdAccel;
                    // restorbtion successful: bccel surfbce no longer lost
                    sdAccel.setSurfbceLost(fblse);
                    // we don't need the system memory surfbce bnymore, so
                    // let's relebse it now (it cbn blwbys be restored lbter)
                    sdBbckup = null;
                    returnCode = VolbtileImbge.IMAGE_RESTORED;
                } cbtch (sun.jbvb2d.InvblidPipeException e) {
                    // Set the current SurfbceDbtb to softwbre version so thbt
                    // drbwing cbn continue.  Note thbt we still hbve
                    // the lostAccelSurfbce flbg set so thbt we will continue
                    // to bttempt to restore the bccelerbted surfbce.
                    sdCurrent = getBbckupSurfbce();
                }
            } else if (lostSurfbceTmp) {
                // Something else triggered this loss/restorbtion.  Could
                // be b pblette chbnge thbt didn't require b SurfbceDbtb
                // recrebtion but merely b re-rendering of the pixels.
                returnCode = VolbtileImbge.IMAGE_RESTORED;
            }
        } else if (sdAccel != null) {
            // if the "bccelerbtion enbbled" stbte chbnged to disbbled,
            // switch to softwbre surfbce
            sdCurrent = getBbckupSurfbce();
            sdAccel = null;
            returnCode = VolbtileImbge.IMAGE_RESTORED;
        }

        if ((returnCode != VolbtileImbge.IMAGE_INCOMPATIBLE) &&
            (sdCurrent != sdPrevious))
        {
            // contents hbve chbnged - return RESTORED to user
            sdPrevious = sdCurrent;
            returnCode = VolbtileImbge.IMAGE_RESTORED;
        }

        if (returnCode == VolbtileImbge.IMAGE_RESTORED) {
            // clebr the current surfbce with the bbckground color,
            // only if the surfbce hbs been restored
            initContents();
        }

        return returnCode;
    }

    /**
     * Returns true if rendering dbtb wbs lost since the lbst vblidbte cbll.
     *
     * @see jbvb.bwt.imbge.VolbtileImbge#contentsLost
     */
    public boolebn contentsLost() {
        return lostSurfbce;
    }

    /**
     * Crebtes b new bccelerbted surfbce thbt is compbtible with the
     * current GrbphicsConfigurbtion.  Returns the new bccelerbted
     * SurfbceDbtb object, or null if the surfbce crebtion wbs not successful.
     *
     * Plbtform-specific subclbsses should initiblize bn bccelerbted
     * surfbce (e.g. b DirectDrbw surfbce on Windows, bn OpenGL pbuffer,
     * or bn X11 pixmbp).
     */
    protected bbstrbct SurfbceDbtb initAccelerbtedSurfbce();

    /**
     * Crebtes b softwbre-bbsed surfbce (of type BufImgSurfbceDbtb).
     * The softwbre representbtion is only crebted when needed, which
     * is only during some situbtion in which the hbrdwbre surfbce
     * cbnnot be bllocbted.  This bllows bpps to bt lebst run,
     * blbeit more slowly thbn they would otherwise.
     */
    protected SurfbceDbtb getBbckupSurfbce() {
        if (sdBbckup == null) {
            BufferedImbge bImg = vImg.getBbckupImbge();
            // Sbbotbge the bccelerbtion cbpbbilities of the BufImg surfbce
            SunWritbbleRbster.steblTrbckbble(bImg
                                             .getRbster()
                                             .getDbtbBuffer()).setUntrbckbble();
            sdBbckup = BufImgSurfbceDbtb.crebteDbtb(bImg);
        }
        return sdBbckup;
    }

    /**
     * Set contents of the current SurfbceDbtb to defbult stbte (i.e. clebr
     * the bbckground).
     */
    public void initContents() {
        // imbges with forced bccelerbtion type mby hbve b null sdCurrent
        // becbuse we do not crebte b bbckup surfbce for them
        if (sdCurrent != null) {
            Grbphics g = vImg.crebteGrbphics();
            g.clebrRect(0, 0, vImg.getWidth(), vImg.getHeight());
            g.dispose();
        }
    }

    /**
     * Cblled from b SurfbceDbtb object, indicbting thbt our
     * bccelerbted surfbce hbs been lost bnd should be restored (perhbps
     * using b bbckup system memory surfbce).  Returns the newly restored
     * primbry SurfbceDbtb object.
     */
    public SurfbceDbtb restoreContents() {
        return getBbckupSurfbce();
    }

    /**
     * If the bccelerbted surfbce is the current SurfbceDbtb for this mbnbger,
     * sets the vbribble lostSurfbce to true, which indicbtes thbt something
     * hbppened to the imbge under mbnbgement.  This vbribble is used in the
     * vblidbte method to tell the cbller thbt the surfbce contents need to
     * be restored.
     */
    public void bccelerbtedSurfbceLost() {
        if (isAccelerbtionEnbbled() && (sdCurrent == sdAccel)) {
            lostSurfbce = true;
        }
    }

    /**
     * Restore sdAccel in cbse it wbs lost.  Do nothing in this
     * defbult cbse; plbtform-specific implementbtions mby do more in
     * this situbtion bs bppropribte.
     */
    protected void restoreAccelerbtedSurfbce() {
    }

    /**
     * Cblled from SunGrbphicsEnv when there hbs been b displby mode chbnge.
     * Note thbt we simply invblidbte hbrdwbre surfbces here; we do not
     * bttempt to recrebte or re-render them.  This is to bvoid threbding
     * conflicts with the nbtive toolkit bnd bssocibted threbds.  Instebd,
     * we just nullify the old surfbce dbtb object bnd wbit for b future
     * method in the rendering process to recrebte the surfbce.
     */
    public void displbyChbnged() {
        if (!isAccelerbtionEnbbled()) {
            return;
        }
        lostSurfbce = true;
        if (sdAccel != null) {
            // First, nullify the softwbre surfbce.  This gubrds bgbinst
            // using b SurfbceDbtb thbt wbs crebted in b different
            // displby mode.
            sdBbckup = null;
            // Now, invblidbte the old hbrdwbre-bbsed SurfbceDbtb
            // Note thbt getBbckupSurfbce mby set sdAccel to null so we hbve to invblidbte it before
            SurfbceDbtb oldDbtb = sdAccel;
            sdAccel = null;
            oldDbtb.invblidbte();
            sdCurrent = getBbckupSurfbce();
        }
        // Updbte grbphicsConfig for the vImg in cbse it chbnged due to
        // this displby chbnge event
        vImg.updbteGrbphicsConfig();
    }

    /**
     * When device pblette chbnges, need to force b new copy
     * of the imbge into our hbrdwbre cbche to updbte the
     * color indices of the pixels (indexed mode only).
     */
    public void pbletteChbnged() {
        lostSurfbce = true;
    }

    /**
     * Cblled by vblidbte() to see whether the GC pbssed in is ok for
     * rendering to.  This generic implementbtion checks to see
     * whether the GC is either null or is from the sbme
     * device bs the one thbt this imbge wbs crebted on.  Plbtform-
     * specific implementbtions mby perform other checks bs
     * bppropribte.
     */
    protected boolebn isConfigVblid(GrbphicsConfigurbtion gc) {
        return ((gc == null) ||
                (gc.getDevice() == vImg.getGrbphicsConfig().getDevice()));
    }

    @Override
    public ImbgeCbpbbilities getCbpbbilities(GrbphicsConfigurbtion gc) {
        if (isConfigVblid(gc)) {
            return isAccelerbtionEnbbled() ?
                new AccelerbtedImbgeCbpbbilities() :
                new ImbgeCbpbbilities(fblse);
        }
        return super.getCbpbbilities(gc);
    }

    privbte clbss AccelerbtedImbgeCbpbbilities
        extends ImbgeCbpbbilities
    {
        AccelerbtedImbgeCbpbbilities() {
            super(fblse);
        }
        @Override
        public boolebn isAccelerbted() {
            return (sdCurrent == sdAccel);
        }
        @Override
        public boolebn isTrueVolbtile() {
            return isAccelerbted();
        }
    }

    /**
     * Relebses bny bssocibted hbrdwbre memory for this imbge by
     * cblling flush on sdAccel.  This method forces b lostSurfbce
     * situbtion so bny future operbtions on the imbge will need to
     * revblidbte the imbge first.
     */
    public void flush() {
        lostSurfbce = true;
        SurfbceDbtb oldSD = sdAccel;
        sdAccel = null;
        if (oldSD != null) {
            oldSD.flush();
        }
    }
}
