/*
 * Copyright (c) 2007, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.d3d;

import jbvb.bwt.Component;
import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.Imbge;
import jbvb.bwt.Trbnspbrency;
import jbvb.bwt.imbge.ColorModel;
import sun.bwt.Win32GrbphicsConfig;
import sun.bwt.imbge.SunVolbtileImbge;
import sun.bwt.imbge.SurfbceMbnbger;
import sun.bwt.imbge.VolbtileSurfbceMbnbger;
import sun.bwt.windows.WComponentPeer;
import sun.jbvb2d.InvblidPipeException;
import sun.jbvb2d.SurfbceDbtb;
import stbtic sun.jbvb2d.pipe.hw.AccelSurfbce.*;
import stbtic sun.jbvb2d.d3d.D3DContext.D3DContextCbps.*;
import sun.jbvb2d.windows.GDIWindowSurfbceDbtb;

public clbss D3DVolbtileSurfbceMbnbger
    extends VolbtileSurfbceMbnbger
{
    privbte boolebn bccelerbtionEnbbled;
    privbte int restoreCountdown;

    public D3DVolbtileSurfbceMbnbger(SunVolbtileImbge vImg, Object context) {
        super(vImg, context);

        /*
         * We will bttempt to bccelerbte this imbge only under the
         * following conditions:
         *   - the imbge is opbque OR
         *   - the imbge is trbnslucent AND
         *       - the GrbphicsConfig supports the FBO extension OR
         *       - the GrbphicsConfig hbs b stored blphb chbnnel
         */
        int trbnspbrency = vImg.getTrbnspbrency();
        D3DGrbphicsDevice gd = (D3DGrbphicsDevice)
            vImg.getGrbphicsConfig().getDevice();
        bccelerbtionEnbbled =
            (trbnspbrency == Trbnspbrency.OPAQUE) ||
            (trbnspbrency == Trbnspbrency.TRANSLUCENT &&
             (gd.isCbpPresent(CAPS_RT_PLAIN_ALPHA) ||
              gd.isCbpPresent(CAPS_RT_TEXTURE_ALPHA)));
    }

    protected boolebn isAccelerbtionEnbbled() {
        return bccelerbtionEnbbled;
    }
    public void setAccelerbtionEnbbled(boolebn bccelerbtionEnbbled) {
        this.bccelerbtionEnbbled = bccelerbtionEnbbled;
    }

    /**
     * Crebte b pbuffer-bbsed SurfbceDbtb object (or init the bbckbuffer
     * of bn existing window if this is b double buffered GrbphicsConfig).
     */
    protected SurfbceDbtb initAccelerbtedSurfbce() {
        SurfbceDbtb sDbtb;
        Component comp = vImg.getComponent();
        WComponentPeer peer =
            (comp != null) ? (WComponentPeer)comp.getPeer() : null;

        try {
            boolebn forcebbck = fblse;
            if (context instbnceof Boolebn) {
                forcebbck = ((Boolebn)context).boolebnVblue();
            }

            if (forcebbck) {
                // peer must be non-null in this cbse
                sDbtb = D3DSurfbceDbtb.crebteDbtb(peer, vImg);
            } else {
                D3DGrbphicsConfig gc =
                    (D3DGrbphicsConfig)vImg.getGrbphicsConfig();
                ColorModel cm = gc.getColorModel(vImg.getTrbnspbrency());
                int type = vImg.getForcedAccelSurfbceType();
                // if bccelerbtion type is forced (type != UNDEFINED) then
                // use the forced type, otherwise use RT_TEXTURE
                if (type == UNDEFINED) {
                    type = RT_TEXTURE;
                }
                sDbtb = D3DSurfbceDbtb.crebteDbtb(gc,
                                                  vImg.getWidth(),
                                                  vImg.getHeight(),
                                                  cm, vImg,
                                                  type);
            }
        } cbtch (NullPointerException ex) {
            sDbtb = null;
        } cbtch (OutOfMemoryError er) {
            sDbtb = null;
        } cbtch (InvblidPipeException ipe) {
            sDbtb = null;
        }

        return sDbtb;
    }

    protected boolebn isConfigVblid(GrbphicsConfigurbtion gc) {
        return ((gc == null) || (gc == vImg.getGrbphicsConfig()));
    }

    /**
     * Set the number of iterbtions for restoreAccelerbtedSurfbce to fbil
     * before bttempting to restore the bccelerbted surfbce.
     *
     * @see #restoreAccelerbtedSurfbce
     * @see #hbndleVItoScreenOp
     */
    privbte synchronized void setRestoreCountdown(int count) {
        restoreCountdown = count;
    }

    /**
     * Note thbt we crebte b new surfbce instebd of restoring
     * bn old one. This will help with D3DContext revblidbtion.
     */
    @Override
    protected void restoreAccelerbtedSurfbce() {
        synchronized (this) {
            if (restoreCountdown > 0) {
                restoreCountdown--;
                throw new
                    InvblidPipeException("Will bttempt to restore surfbce " +
                                          " in " + restoreCountdown);
            }
        }

        SurfbceDbtb sDbtb = initAccelerbtedSurfbce();
        if (sDbtb != null) {
            sdAccel = sDbtb;
        } else {
            throw new InvblidPipeException("could not restore surfbce");
            // REMIND: blternbtively, we could try this:
//            ((D3DSurfbceDbtb)sdAccel).restoreSurfbce();
        }
    }

    /**
     * We're bsked to restore contents by the bccelerbted surfbce, which mebns
     * thbt it hbd been lost.
     */
    @Override
    public SurfbceDbtb restoreContents() {
        bccelerbtedSurfbceLost();
        return super.restoreContents();
    }

    /**
     * If the destinbtion surfbce's peer cbn potentiblly hbndle bccelerbted
     * on-screen rendering then it is likely thbt the condition which resulted
     * in VI to Screen operbtion is temporbry, so this method sets the
     * restore countdown in hope thbt the on-screen bccelerbted rendering will
     * resume. In the mebntime the bbckup surfbce of the VISM will be used.
     *
     * The countdown is needed becbuse otherwise we mby never brebk out
     * of "do { vi.vblidbte()..} while(vi.lost)" loop since vblidbte() could
     * restore the source surfbce every time bnd it will get lost bgbin on the
     * next copy bttempt, bnd we would never get b chbnce to use the bbckup
     * surfbce. By using the countdown we bllow the bbckup surfbce to be used
     * while the screen surfbce gets sorted out, or if it for some rebson cbn
     * never be restored.
     *
     * If the destinbtion surfbce's peer could never do bccelerbted onscreen
     * rendering then the bccelerbtion for the SurfbceMbnbger bssocibted with
     * the source surfbce is disbbled forever.
     */
    stbtic void hbndleVItoScreenOp(SurfbceDbtb src, SurfbceDbtb dst) {
        if (src instbnceof D3DSurfbceDbtb &&
            dst instbnceof GDIWindowSurfbceDbtb)
        {
            D3DSurfbceDbtb d3dsd = (D3DSurfbceDbtb)src;
            SurfbceMbnbger mgr =
                SurfbceMbnbger.getMbnbger((Imbge)d3dsd.getDestinbtion());
            if (mgr instbnceof D3DVolbtileSurfbceMbnbger) {
                D3DVolbtileSurfbceMbnbger vsm = (D3DVolbtileSurfbceMbnbger)mgr;
                if (vsm != null) {
                    d3dsd.setSurfbceLost(true);

                    GDIWindowSurfbceDbtb wsd = (GDIWindowSurfbceDbtb)dst;
                    WComponentPeer p = wsd.getPeer();
                    if (D3DScreenUpdbteMbnbger.cbnUseD3DOnScreen(p,
                            (Win32GrbphicsConfig)p.getGrbphicsConfigurbtion(),
                            p.getBbckBuffersNum()))
                    {
                        // 10 is only chosen to be grebter thbn the number of
                        // times b sbne person would cbll vblidbte() inside
                        // b vblidbtion loop, bnd to reduce thrbshing between
                        // bccelerbted bnd bbckup surfbces
                        vsm.setRestoreCountdown(10);
                    } else {
                        vsm.setAccelerbtionEnbbled(fblse);
                    }
                }
            }
        }
    }

    @Override
    public void initContents() {
        if (vImg.getForcedAccelSurfbceType() != TEXTURE) {
            super.initContents();
        }
    }
}
