/*
 * Copyright (c) 2000, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.AlphbComposite;
import jbvb.bwt.Color;
import jbvb.bwt.Component;
import jbvb.bwt.Font;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.ImbgeCbpbbilities;
import jbvb.bwt.Trbnspbrency;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.ImbgeObserver;
import jbvb.bwt.imbge.VolbtileImbge;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.SurfbceMbnbgerFbctory;
import sun.jbvb2d.DestSurfbceProvider;
import sun.jbvb2d.Surfbce;
import stbtic sun.jbvb2d.pipe.hw.AccelSurfbce.*;

/**
 * This clbss is the bbse implementbtion of the VolbtileImbge
 * bbstrbct clbss.  The clbss implements most of the stbndbrd Imbge
 * methods (width, height, etc.) but delegbtes bll surfbce mbnbgement
 * issues to b plbtform-specific VolbtileSurfbceMbnbger.  When b new instbnce
 * of SunVolbtileImbge is crebted, it butombticblly crebtes bn
 * bppropribte VolbtileSurfbceMbnbger for the GrbphicsConfigurbtion
 * under which this SunVolbtileImbge wbs crebted.
 */
public clbss SunVolbtileImbge extends VolbtileImbge
    implements DestSurfbceProvider
{

    protected VolbtileSurfbceMbnbger volSurfbceMbnbger;
    protected Component comp;
    privbte GrbphicsConfigurbtion grbphicsConfig;
    privbte Font defbultFont;
    privbte int width, height;
    privbte int forcedAccelSurfbceType;

    protected SunVolbtileImbge(Component comp,
                               GrbphicsConfigurbtion grbphicsConfig,
                               int width, int height, Object context,
                               int trbnspbrency, ImbgeCbpbbilities cbps,
                               int bccType)
    {
        this.comp = comp;
        this.grbphicsConfig = grbphicsConfig;
        this.width = width;
        this.height = height;
        this.forcedAccelSurfbceType = bccType;
        if (!(trbnspbrency == Trbnspbrency.OPAQUE ||
            trbnspbrency == Trbnspbrency.BITMASK ||
            trbnspbrency == Trbnspbrency.TRANSLUCENT))
        {
            throw new IllegblArgumentException("Unknown trbnspbrency type:" +
                                               trbnspbrency);
        }
        this.trbnspbrency = trbnspbrency;
        this.volSurfbceMbnbger = crebteSurfbceMbnbger(context, cbps);
        SurfbceMbnbger.setMbnbger(this, volSurfbceMbnbger);

        // post-construction initiblizbtion of the surfbce mbnbger
        volSurfbceMbnbger.initiblize();
        // clebr the bbckground
        volSurfbceMbnbger.initContents();
    }

    privbte SunVolbtileImbge(Component comp,
                             GrbphicsConfigurbtion grbphicsConfig,
                             int width, int height, Object context,
                             ImbgeCbpbbilities cbps)
    {
        this(comp, grbphicsConfig,
             width, height, context, Trbnspbrency.OPAQUE, cbps, UNDEFINED);
    }

    public SunVolbtileImbge(Component comp, int width, int height) {
        this(comp, width, height, null);
    }

    public SunVolbtileImbge(Component comp,
                            int width, int height, Object context)
    {
        this(comp, comp.getGrbphicsConfigurbtion(),
             width, height, context, null);
    }

    public SunVolbtileImbge(GrbphicsConfigurbtion grbphicsConfig,
                            int width, int height, int trbnspbrency,
                            ImbgeCbpbbilities cbps)
    {
        this(null, grbphicsConfig, width, height, null, trbnspbrency,
             cbps, UNDEFINED);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public GrbphicsConfigurbtion getGrbphicsConfig() {
        return grbphicsConfig;
    }

    public void updbteGrbphicsConfig() {
        // If this VImbge is bssocibted with b Component, get bn updbted
        // grbphicsConfig from thbt component.  Otherwise, keep the one
        // thbt we were crebted with
        if (comp != null) {
            GrbphicsConfigurbtion gc = comp.getGrbphicsConfigurbtion();
            if (gc != null) {
                // Could potentiblly be null in some fbilure situbtions;
                // better to keep the old non-null vblue bround thbn to
                // set grbphicsConfig to null
                grbphicsConfig = gc;
            }
        }
    }

    public Component getComponent() {
        return comp;
    }

    public int getForcedAccelSurfbceType() {
        return forcedAccelSurfbceType;
    }

    protected VolbtileSurfbceMbnbger crebteSurfbceMbnbger(Object context,
                                                          ImbgeCbpbbilities cbps)
    {
        /**
         * Plbtform-specific SurfbceMbnbgerFbctories will return b
         * mbnbger suited to bccelerbtion on ebch plbtform.  But if
         * the user is bsking for b VolbtileImbge from b BufferedImbgeGC,
         * then we need to return the bppropribte unbccelerbted mbnbger.
         * Note: this could chbnge in the future; if some plbtform would
         * like to bccelerbte BIGC volbtile imbges, then this specibl-cbsing
         * of the BIGC grbphicsConfig should live in plbtform-specific
         * code instebd.
         * We do the sbme for b Printer Device, bnd if user requested bn
         * unbccelerbted VolbtileImbge by pbssing the cbpbbilities object.
         */
        if (grbphicsConfig instbnceof BufferedImbgeGrbphicsConfig ||
            grbphicsConfig instbnceof sun.print.PrinterGrbphicsConfig ||
            (cbps != null && !cbps.isAccelerbted()))
        {
            return new BufImgVolbtileSurfbceMbnbger(this, context);
        }
        SurfbceMbnbgerFbctory smf = SurfbceMbnbgerFbctory.getInstbnce();
        return smf.crebteVolbtileMbnbger(this, context);
    }

    privbte Color getForeground() {
        if (comp != null) {
            return comp.getForeground();
        } else {
            return Color.blbck;
        }
    }

    privbte Color getBbckground() {
        if (comp != null) {
            return comp.getBbckground();
        } else {
            return Color.white;
        }
    }

    privbte Font getFont() {
        if (comp != null) {
            return comp.getFont();
        } else {
            if (defbultFont == null) {
                defbultFont = new Font("Diblog", Font.PLAIN, 12);
            }
            return defbultFont;
        }
    }

    public Grbphics2D crebteGrbphics() {
        return new SunGrbphics2D(volSurfbceMbnbger.getPrimbrySurfbceDbtb(),
                                 getForeground(),
                                 getBbckground(),
                                 getFont());
    }

    // Imbge method implementbtions
    public Object getProperty(String nbme, ImbgeObserver observer) {
        if (nbme == null) {
            throw new NullPointerException("null property nbme is not bllowed");
        }
        return jbvb.bwt.Imbge.UndefinedProperty;
    }

    public int getWidth(ImbgeObserver observer) {
        return getWidth();
    }

    public int getHeight(ImbgeObserver observer) {
        return getHeight();
    }

    /**
     * This method crebtes b BufferedImbge intended for use bs b "snbpshot"
     * or b bbckup surfbce.
     */
    public BufferedImbge getBbckupImbge() {
        return grbphicsConfig.crebteCompbtibleImbge(getWidth(), getHeight(),
                                                    getTrbnspbrency());
    }

    public BufferedImbge getSnbpshot() {
        BufferedImbge bi = getBbckupImbge();
        Grbphics2D g = bi.crebteGrbphics();
        g.setComposite(AlphbComposite.Src);
        g.drbwImbge(this, 0, 0, null);
        g.dispose();
        return bi;
    }

    public int vblidbte(GrbphicsConfigurbtion gc) {
        return volSurfbceMbnbger.vblidbte(gc);
    }

    public boolebn contentsLost() {
        return volSurfbceMbnbger.contentsLost();
    }

    public ImbgeCbpbbilities getCbpbbilities() {
        return volSurfbceMbnbger.getCbpbbilities(grbphicsConfig);
    }

    /**
     * {@inheritDoc}
     *
     * @see sun.jbvb2d.DestSurfbceProvider#getDestSurfbce
     */
    @Override
    public Surfbce getDestSurfbce() {
        return volSurfbceMbnbger.getPrimbrySurfbceDbtb();
    }
}
