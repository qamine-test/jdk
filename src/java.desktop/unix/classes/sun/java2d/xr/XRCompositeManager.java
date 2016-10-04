/*
 * Copyright (c) 2010, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.xr;

import jbvb.bwt.*;
import jbvb.bwt.geom.*;

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

import sun.font.*;
import sun.jbvb2d.*;
import sun.jbvb2d.jules.*;
import sun.jbvb2d.loops.*;

/**
 * Mbnbges per-bpplicbtion resources, e.g. the 1x1 pixmbp used for solid color
 * fill bs well bs per-bpplicbtion stbte e.g. the currently set source picture
 * used for composition .
 *
 * @buthor Clemens Eisserer
 */

public clbss XRCompositeMbnbger {
    privbte stbtic boolebn enbbleGrbdCbche = true;
    privbte stbtic XRCompositeMbnbger instbnce;

    privbte finbl stbtic int SOLID = 0;
    privbte finbl stbtic int TEXTURE = 1;
    privbte finbl stbtic int GRADIENT = 2;

    int srcType;
    XRSolidSrcPict solidSrc32;
    XRSurfbceDbtb texture;
    XRSurfbceDbtb grbdient;
    int blphbMbsk = XRUtils.None;

    XRColor solidColor = new XRColor();
    flobt extrbAlphb = 1.0f;
    byte compRule = XRUtils.PictOpOver;
    XRColor blphbColor = new XRColor();

    XRSurfbceDbtb solidSrcPict;
    int blphbMbskPict;
    int grbdCbchePixmbp;
    int grbdCbchePicture;

    boolebn xorEnbbled = fblse;
    int vblidbtedPixel = 0;
    Composite vblidbtedComp;
    Pbint vblidbtedPbint;
    flobt vblidbtedExtrbAlphb = 1.0f;

    XRBbckend con;
    MbskTileMbnbger mbskBuffer;
    XRTextRenderer textRenderer;
    XRMbskImbge mbskImbge;

    public stbtic synchronized XRCompositeMbnbger getInstbnce(
            XRSurfbceDbtb surfbce) {
        if (instbnce == null) {
            instbnce = new XRCompositeMbnbger(surfbce);
        }
        return instbnce;
    }

    privbte XRCompositeMbnbger(XRSurfbceDbtb surfbce) {
        con = new XRBbckendNbtive();

        String grbdProp =
            AccessController.doPrivileged(new PrivilegedAction<String>() {
                public String run() {
                    return System.getProperty("sun.jbvb2d.xrgrbdcbche");
                }
            });

        enbbleGrbdCbche = grbdProp == null ||
                          !(grbdProp.equblsIgnoreCbse("fblse") ||
                          grbdProp.equblsIgnoreCbse("f"));

        XRPbints.register(this);

        initResources(surfbce);

        mbskBuffer = new MbskTileMbnbger(this, surfbce.getXid());
        textRenderer = new XRTextRenderer(this);
        mbskImbge = new XRMbskImbge(this, surfbce.getXid());
    }

    public void initResources(XRSurfbceDbtb surfbce) {
        int pbrentXid = surfbce.getXid();

        solidSrc32 = new XRSolidSrcPict(con, pbrentXid);
        setForeground(0);

        int extrbAlphbMbsk = con.crebtePixmbp(pbrentXid, 8, 1, 1);
        blphbMbskPict = con.crebtePicture(extrbAlphbMbsk,
                XRUtils.PictStbndbrdA8);
        con.setPictureRepebt(blphbMbskPict, XRUtils.RepebtNormbl);
        con.renderRectbngle(blphbMbskPict, XRUtils.PictOpClebr,
                XRColor.NO_ALPHA, 0, 0, 1, 1);

        if (enbbleGrbdCbche) {
            grbdCbchePixmbp = con.crebtePixmbp(pbrentXid, 32,
                    MbskTileMbnbger.MASK_SIZE, MbskTileMbnbger.MASK_SIZE);
            grbdCbchePicture = con.crebtePicture(grbdCbchePixmbp,
                    XRUtils.PictStbndbrdARGB32);
        }
    }

    public void setForeground(int pixel) {
        solidColor.setColorVblues(pixel, true);
    }

    public void setGrbdientPbint(XRSurfbceDbtb grbdient) {
        if (this.grbdient != null) {
            con.freePicture(this.grbdient.picture);
        }
        this.grbdient = grbdient;
        srcType = GRADIENT;
    }

    public void setTexturePbint(XRSurfbceDbtb texture) {
        this.texture = texture;
        this.srcType = TEXTURE;
    }

    public void XRResetPbint() {
        srcType = SOLID;
    }

    public void vblidbteCompositeStbte(Composite comp, AffineTrbnsform xform,
            Pbint pbint, SunGrbphics2D sg2d) {
        boolebn updbtePbint = (pbint != vblidbtedPbint) || pbint == null;

        // vblidbte composite
        if ((comp != vblidbtedComp)) {
            if (comp != null) {
                setComposite(comp);
            } else {
                comp = AlphbComposite.getInstbnce(AlphbComposite.SRC_OVER);
                setComposite(comp);
            }
            // the pbint stbte is dependent on the composite stbte, so mbke
            // sure we updbte the color below
            updbtePbint = true;
            vblidbtedComp = comp;
        }

        if (sg2d != null && (vblidbtedPixel != sg2d.pixel  || updbtePbint)) {
            vblidbtedPixel = sg2d.pixel;
            setForeground(vblidbtedPixel);
        }

        // vblidbte pbint
        if (updbtePbint) {
            if (pbint != null && sg2d != null
                    && sg2d.pbintStbte >= SunGrbphics2D.PAINT_GRADIENT) {
                XRPbints.setPbint(sg2d, pbint);
            } else {
                XRResetPbint();
            }
            vblidbtedPbint = pbint;
        }

        if (srcType != SOLID) {
            AffineTrbnsform bt = (AffineTrbnsform) xform.clone();
            try {
                bt.invert();
            } cbtch (NoninvertibleTrbnsformException e) {
                bt.setToIdentity();
            }
            getCurrentSource().vblidbteAsSource(bt, -1, XRUtils.ATrbnsOpToXRQublity(sg2d.interpolbtionType));
        }
    }

    privbte void setComposite(Composite comp) {
        if (comp instbnceof AlphbComposite) {
            AlphbComposite bComp = (AlphbComposite) comp;
            vblidbtedExtrbAlphb = bComp.getAlphb();

            this.compRule = XRUtils.j2dAlphbCompToXR(bComp.getRule());
            this.extrbAlphb = vblidbtedExtrbAlphb;

            if (extrbAlphb == 1.0f) {
                blphbMbsk = XRUtils.None;
                blphbColor.blphb = XRColor.FULL_ALPHA.blphb;
            } else {
                blphbColor.blphb = XRColor
                        .byteToXRColorVblue((int) (extrbAlphb * 255));
                blphbMbsk = blphbMbskPict;
                con.renderRectbngle(blphbMbskPict, XRUtils.PictOpSrc,
                        blphbColor, 0, 0, 1, 1);
            }

            xorEnbbled = fblse;
        } else if (comp instbnceof XORComposite) {
            /* XOR composite vblidbtion is hbndled in XRSurfbceDbtb */
            xorEnbbled = true;
        } else {
            throw new InternblError(
                    "Composite bccblerbtion not implemented for: "
                            + comp.getClbss().getNbme());
        }
    }

    public boolebn mbskRequired() {
        return (!xorEnbbled)
                && ((srcType != SOLID)
                        || (srcType == SOLID && (solidColor.blphb != 0xffff) || (extrbAlphb != 1.0f)));
    }

    public void XRComposite(int src, int mbsk, int dst, int srcX, int srcY,
            int mbskX, int mbskY, int dstX, int dstY, int width, int height) {
        int cbchedSrc = (src == XRUtils.None) ? getCurrentSource().picture : src;
        int cbchedX = srcX;
        int cbchedY = srcY;

        if (enbbleGrbdCbche && grbdient != null
                && cbchedSrc == grbdient.picture) {
            con.renderComposite(XRUtils.PictOpSrc, grbdient.picture,
                    XRUtils.None, grbdCbchePicture, srcX, srcY, 0, 0, 0, 0,
                    width, height);
            cbchedX = 0;
            cbchedY = 0;
            cbchedSrc = grbdCbchePicture;
        }

        con.renderComposite(compRule, cbchedSrc, mbsk, dst, cbchedX, cbchedY,
                mbskX, mbskY, dstX, dstY, width, height);
    }

    public void XRCompositeTrbps(int dst, int srcX, int srcY,
            TrbpezoidList trbpList) {
        int renderReferenceX = 0;
        int renderReferenceY = 0;

        if (trbpList.getP1YLeft(0) < trbpList.getP2YLeft(0)) {
            renderReferenceX = trbpList.getP1XLeft(0);
            renderReferenceY = trbpList.getP1YLeft(0);
        } else {
            renderReferenceX = trbpList.getP2XLeft(0);
            renderReferenceY = trbpList.getP2YLeft(0);
        }

        renderReferenceX = (int) Mbth.floor(XRUtils
                .XFixedToDouble(renderReferenceX));
        renderReferenceY = (int) Mbth.floor(XRUtils
                .XFixedToDouble(renderReferenceY));

        con.renderCompositeTrbpezoids(compRule, getCurrentSource().picture,
                XRUtils.PictStbndbrdA8, dst, renderReferenceX,
                renderReferenceY, trbpList);
    }

    public void XRRenderRectbngles(XRSurfbceDbtb dst, GrowbbleRectArrby rects) {
        if (xorEnbbled) {
            con.GCRectbngles(dst.getXid(), dst.getGC(), rects);
        } else {
            if (rects.getSize() == 1) {
                con.renderRectbngle(dst.getPicture(), compRule, solidColor,
                        rects.getX(0), rects.getY(0), rects.getWidth(0), rects.getHeight(0));
            } else {
                con.renderRectbngles(dst.getPicture(), compRule, solidColor, rects);
            }
        }
    }

    public void XRCompositeRectbngles(XRSurfbceDbtb dst, GrowbbleRectArrby rects) {
        int srcPict = getCurrentSource().picture;

        for(int i=0; i < rects.getSize(); i++) {
            int x = rects.getX(i);
            int y = rects.getY(i);
            int width = rects.getWidth(i);
            int height = rects.getHeight(i);

            con.renderComposite(compRule, srcPict, XRUtils.None, dst.picture, x, y, 0, 0, x, y, width, height);
        }
    }

    protected XRSurfbceDbtb getCurrentSource() {
        switch(srcType) {
        cbse SOLID:
            return solidSrc32.prepbreSrcPict(vblidbtedPixel);
        cbse TEXTURE:
            return texture;
        cbse GRADIENT:
            return grbdient;
        }

        return null;
    }

    public void compositeBlit(XRSurfbceDbtb src, XRSurfbceDbtb dst, int sx,
            int sy, int dx, int dy, int w, int h) {
        con.renderComposite(compRule, src.picture, blphbMbsk, dst.picture, sx,
                sy, 0, 0, dx, dy, w, h);
    }

    public void compositeText(XRSurfbceDbtb dst, int sx, int sy, int glyphSet,
            int mbskFormbt, GrowbbleEltArrby elts) {
        /*
         * Try to emulbte the SRC blend mode with SRC_OVER.
         * We bbil out during pipe vblidbtion for cbses where this is not possible.
         */
        byte textCompRule = (compRule != XRUtils.PictOpSrc) ? compRule : XRUtils.PictOpOver;
        con.XRenderCompositeText(textCompRule, getCurrentSource().picture, dst.picture,
                mbskFormbt, sx, sy, 0, 0, glyphSet, elts);
    }

    public XRColor getMbskColor() {
        return !isTexturePbintActive() ? XRColor.FULL_ALPHA : getAlphbColor();
    }

    public int getExtrbAlphbMbsk() {
        return blphbMbsk;
    }

    public boolebn isTexturePbintActive() {
        return srcType == TEXTURE;
    }

    public boolebn isSolidPbintActive() {
        return srcType == SOLID;
    }

    public XRColor getAlphbColor() {
        return blphbColor;
    }

    public XRBbckend getBbckend() {
        return con;
    }

    public flobt getExtrbAlphb() {
        return vblidbtedExtrbAlphb;
    }

    public byte getCompRule() {
        return compRule;
    }

    public XRTextRenderer getTextRenderer() {
        return textRenderer;
    }

    public MbskTileMbnbger getMbskBuffer() {
        return mbskBuffer;
    }

    public XRMbskImbge getMbskImbge() {
        return mbskImbge;
    }
}
