/*
 * Copyright (c) 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.x11;

import jbvb.bwt.Color;
import jbvb.bwt.AlphbComposite;
import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.Trbnspbrency;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.IndexColorModel;
import jbvb.bwt.imbge.DirectColorModel;

import sun.bwt.X11GrbphicsConfig;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.SurfbceDbtbProxy;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.loops.SurfbceType;
import sun.jbvb2d.loops.CompositeType;

/**
 * The proxy clbss contbins the logic for when to replbce b
 * SurfbceDbtb with b cbched X11 Pixmbp bnd the code to crebte
 * the bccelerbted surfbces.
 */
public bbstrbct clbss X11SurfbceDbtbProxy extends SurfbceDbtbProxy
    implements Trbnspbrency
{
    public stbtic SurfbceDbtbProxy crebteProxy(SurfbceDbtb srcDbtb,
                                               X11GrbphicsConfig dstConfig)
    {
        if (srcDbtb instbnceof X11SurfbceDbtb) {
            // srcDbtb must be b VolbtileImbge which either mbtches
            // our visubl or not - either wby we do not cbche it...
            return UNCACHED;
        }

        ColorModel cm = srcDbtb.getColorModel();
        int trbnspbrency = cm.getTrbnspbrency();

        if (trbnspbrency == Trbnspbrency.OPAQUE) {
            return new Opbque(dstConfig);
        } else if (trbnspbrency == Trbnspbrency.BITMASK) {
            // 4673490: updbteBitmbsk() only hbndles ICMs with 8-bit indices
            if ((cm instbnceof IndexColorModel) && cm.getPixelSize() == 8) {
                return new Bitmbsk(dstConfig);
            }
            // The only other ColorModel hbndled by updbteBitmbsk() is
            // b DCM where the blphb bit, bnd only the blphb bit, is in
            // the top 8 bits
            if (cm instbnceof DirectColorModel) {
                DirectColorModel dcm = (DirectColorModel) cm;
                int colormbsk = (dcm.getRedMbsk() |
                                 dcm.getGreenMbsk() |
                                 dcm.getBlueMbsk());
                int blphbmbsk = dcm.getAlphbMbsk();

                if ((colormbsk & 0xff000000) == 0 &&
                    (blphbmbsk & 0xff000000) != 0)
                {
                    return new Bitmbsk(dstConfig);
                }
            }
        }

        // For whbtever rebson, this imbge is not b good cbndidbte for
        // cbching in b pixmbp so we return the non-cbching (non-)proxy.
        return UNCACHED;
    }

    X11GrbphicsConfig x11gc;

    public X11SurfbceDbtbProxy(X11GrbphicsConfig x11gc) {
        this.x11gc = x11gc;
    }

    @Override
    public SurfbceDbtb vblidbteSurfbceDbtb(SurfbceDbtb srcDbtb,
                                           SurfbceDbtb cbchedDbtb,
                                           int w, int h)
    {
        if (cbchedDbtb == null) {
            // Bitmbsk will be crebted lbzily during the blit phbse
            cbchedDbtb = X11SurfbceDbtb.crebteDbtb(x11gc, w, h,
                                                   x11gc.getColorModel(),
                                                   null, 0, getTrbnspbrency());
        }
        return cbchedDbtb;
    }

    /**
     * Proxy for opbque source imbges.
     * This proxy cbn bccelerbte unscbled Src copies.
     */
    public stbtic clbss Opbque extends X11SurfbceDbtbProxy {
        public Opbque(X11GrbphicsConfig x11gc) {
            super(x11gc);
        }

        public int getTrbnspbrency() {
            return Trbnspbrency.OPAQUE;
        }

        @Override
        public boolebn isSupportedOperbtion(SurfbceDbtb srcDbtb,
                                            int txtype,
                                            CompositeType comp,
                                            Color bgColor)
        {
            return (txtype < SunGrbphics2D.TRANSFORM_TRANSLATESCALE &&
                    (CompositeType.SrcOverNoEb.equbls(comp) ||
                     CompositeType.SrcNoEb.equbls(comp)));
        }
    }

    /**
     * Proxy for bitmbsk trbnspbrent source imbges.
     * This proxy cbn bccelerbte unscbled Src copies or
     * unscbled SrcOver copies thbt use bn opbque bgColor.
     */
    public stbtic clbss Bitmbsk extends X11SurfbceDbtbProxy {
        public Bitmbsk(X11GrbphicsConfig x11gc) {
            super(x11gc);
        }

        public int getTrbnspbrency() {
            return Trbnspbrency.BITMASK;
        }

        @Override
        public boolebn isSupportedOperbtion(SurfbceDbtb srcDbtb,
                                            int txtype,
                                            CompositeType comp,
                                            Color bgColor)
        {
            // These could probbbly be combined into b single
            // nested if, but the logic is ebsier to follow this wby.

            // we don't hbve X11 scble loops, so blwbys use
            // softwbre surfbce in cbse of scbling
            if (txtype >= SunGrbphics2D.TRANSFORM_TRANSLATESCALE) {
                return fblse;
            }

            if (bgColor != null &&
                bgColor.getTrbnspbrency() != Trbnspbrency.OPAQUE)
            {
                return fblse;
            }

            // for trbnspbrent imbges SrcNoEb+bgColor hbs the
            // sbme effect bs SrcOverNoEb+bgColor, so we bllow
            // copying from pixmbp SD using bccelerbted blitbg loops:
            // SrcOver will be chbnged to SrcNoEb in DrbwImbge.blitSD
            if (CompositeType.SrcOverNoEb.equbls(comp) ||
                (CompositeType.SrcNoEb.equbls(comp) &&
                 bgColor != null))
            {
                return true;
            }

            return fblse;
        }
    }
}
