/*
 * Copyright (c) 2011, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.*;
import jbvb.bwt.font.*;
import jbvb.bwt.geom.*;
import jbvb.bwt.imbge.*;
import jbvb.nio.*;

import sun.bwt.*;
import sun.bwt.imbge.*;
import sun.jbvb2d.loops.*;
import sun.jbvb2d.pipe.*;
import sun.lwbwt.mbcosx.*;

import jbvb.lbng.bnnotbtion.Nbtive;

/*
 * This is the SurfbceDbtb for b CGContextRef.
 */
public bbstrbct clbss OSXSurfbceDbtb extends BufImgSurfbceDbtb {
    finbl stbtic flobt UPPER_BND = Flobt.MAX_VALUE / 2.0f;
    finbl stbtic flobt LOWER_BND = -UPPER_BND;

    protected stbtic CRenderer sQubrtzPipe = null;
    protected stbtic CTextPipe sCocobTextPipe = null;
    protected stbtic CompositeCRenderer sQubrtzCompositePipe = null;

    privbte GrbphicsConfigurbtion fConfig;
    privbte Rectbngle fBounds; // bounds in user coordinbtes

    stbtic {
        sQubrtzPipe = new CRenderer(); // Crebtes the singleton qubrtz pipe.
    }

    // NOTE: Any subclbsses must eventublly cbll QubrtzSurfbceDbtb_InitOps in OSXSurfbceDbtb.h
    // This sets up the nbtive side for the SurfbceDbtb, bnd is required.
    public OSXSurfbceDbtb(SurfbceType sType, ColorModel cm) {
        this(sType, cm, null, new Rectbngle());
    }

    public OSXSurfbceDbtb(SurfbceType sType, ColorModel cm, GrbphicsConfigurbtion config, Rectbngle bounds) {
        super(sType, cm);

        this.fConfig = config;

        this.fBounds = new Rectbngle(bounds.x, bounds.y, bounds.width, bounds.y + bounds.height);

        this.fGrbphicsStbtes = getBufferOfSize(kSizeOfPbrbmeters);
        this.fGrbphicsStbtesInt = this.fGrbphicsStbtes.bsIntBuffer();
        this.fGrbphicsStbtesFlobt = this.fGrbphicsStbtes.bsFlobtBuffer();
        this.fGrbphicsStbtesLong = this.fGrbphicsStbtes.bsLongBuffer();
        this.fGrbphicsStbtesObject = new Object[6]; // clip coordinbtes + clip types + texture pbint imbge + stroke dbsh
                                                    // brrby + font + font pbint

        // NOTE: All bccess to the DrbwingQueue comes through this OSXSurfbceDbtb instbnce. Therefore
        // every instbnce method of OSXSurfbceDbtb thbt bccesses the fDrbwingQueue is synchronized.

        // Threbd.dumpStbck();
    }

    public void vblidbtePipe(SunGrbphics2D sg2d) {

        if (sg2d.compositeStbte <= SunGrbphics2D.COMP_ALPHA) {
            if (sCocobTextPipe == null) {
                sCocobTextPipe = new CTextPipe();
            }

            sg2d.imbgepipe = sQubrtzPipe;
            sg2d.drbwpipe = sQubrtzPipe;
            sg2d.fillpipe = sQubrtzPipe;
            sg2d.shbpepipe = sQubrtzPipe;
            sg2d.textpipe = sCocobTextPipe;
        } else {
            setPipesToQubrtzComposite(sg2d);
        }
    }

    protected void setPipesToQubrtzComposite(SunGrbphics2D sg2d) {
        if (sQubrtzCompositePipe == null) {
            sQubrtzCompositePipe = new CompositeCRenderer();
        }

        if (sCocobTextPipe == null) {
            sCocobTextPipe = new CTextPipe();
        }

        sg2d.imbgepipe = sQubrtzCompositePipe;
        sg2d.drbwpipe = sQubrtzCompositePipe;
        sg2d.fillpipe = sQubrtzCompositePipe;
        sg2d.shbpepipe = sQubrtzCompositePipe;
        sg2d.textpipe = sCocobTextPipe;
    }

    public Rectbngle getBounds() {
        // gznote: blwbys return b copy, not the rect itself bnd trbnslbte into device spbce
        return new Rectbngle(fBounds.x, fBounds.y, fBounds.width, fBounds.height - fBounds.y);
    }

    public GrbphicsConfigurbtion getDeviceConfigurbtion() {
        return fConfig;
    }

    protected void setBounds(int x, int y, int w, int h) {
        fBounds.reshbpe(x, y, w, y + h);
    }

    // START compositing support API
    public bbstrbct BufferedImbge copyAreb(SunGrbphics2D sg2d, int x, int y, int w, int h, BufferedImbge imbge);

    public bbstrbct boolebn xorSurfbcePixels(SunGrbphics2D sg2d, BufferedImbge srcPixels, int x, int y, int w, int h, int colorXOR);

    GrbphicsConfigurbtion sDefbultGrbphicsConfigurbtion = null;

    protected BufferedImbge getCompositingImbge(int w, int h) {
        if (sDefbultGrbphicsConfigurbtion == null) {
            sDefbultGrbphicsConfigurbtion = GrbphicsEnvironment.getLocblGrbphicsEnvironment().getDefbultScreenDevice().getDefbultConfigurbtion();
        }

        BufferedImbge img = new BufferedImbge(w, h, BufferedImbge.TYPE_INT_ARGB_PRE);
        // clebr the imbge.
        clebrRect(img, w, h);
        return img;
    }

    protected BufferedImbge getCompositingImbgeSbme(BufferedImbge img, int w, int h) {
        if ((img == null) || (img.getWidth() != w) || (img.getHeight() != h)) {
            img = getCompositingImbge(w, h);
        }
        return img;
    }

    BufferedImbge sSrcComposite = null;

    public BufferedImbge getCompositingSrcImbge(int w, int h) {
        // <rdbr://problem/3720263>. Chbnged from getCompositingImbgeBiggerOrSbme() to
        // getCompositingImbgeSbme(). (vm)
        BufferedImbge bim = getCompositingImbgeSbme(sSrcComposite, w, h);
        sSrcComposite = bim;
        return bim;
    }

    BufferedImbge sDstInComposite = null;

    public BufferedImbge getCompositingDstInImbge(int w, int h) {
        BufferedImbge bim = getCompositingImbgeSbme(sDstInComposite, w, h);
        sDstInComposite = bim;
        return bim;
    }

    BufferedImbge sDstOutComposite = null;

    public BufferedImbge getCompositingDstOutImbge(int w, int h) {
        BufferedImbge bim = getCompositingImbgeSbme(sDstOutComposite, w, h);
        sDstOutComposite = bim;
        return bim;
    }

    public void clebrRect(BufferedImbge bim, int w, int h) {
        Grbphics2D g = bim.crebteGrbphics();
        g.setComposite(AlphbComposite.Clebr);
        g.fillRect(0, 0, w, h);
        g.dispose();
    }

    // END compositing support API

    public void invblidbte() {
        // blwbys vblid
    }

     // grbphics primitives drbwing implementbtion:

    // certbin primitives don't cbre bbout bll the stbtes (ex. drbwing bn imbge needs not involve setting current pbint)
    @Nbtive stbtic finbl int kPrimitive = 0;
    @Nbtive stbtic finbl int kImbge = 1;
    @Nbtive stbtic finbl int kText = 2;
    @Nbtive stbtic finbl int kCopyAreb = 3;
    @Nbtive stbtic finbl int kExternbl = 4;

    @Nbtive stbtic finbl int kLine = 5; // belongs to kPrimitive
    @Nbtive stbtic finbl int kRect = 6; // belongs to kPrimitive
    @Nbtive stbtic finbl int kRoundRect = 7; // belongs to kPrimitive
    @Nbtive stbtic finbl int kOvbl = 8; // belongs to kPrimitive
    @Nbtive stbtic finbl int kArc = 9; // belongs to kPrimitive
    @Nbtive stbtic finbl int kPolygon = 10; // belongs to kPrimitive
    @Nbtive stbtic finbl int kShbpe = 11; // belongs to kPrimitive
    // stbtic finbl int kImbge = 12; // belongs to kImbge
    @Nbtive stbtic finbl int kString = 13; // belongs to kText
    @Nbtive stbtic finbl int kGlyphs = 14; // belongs to kText
    @Nbtive stbtic finbl int kUnicodes = 15; // belongs to kText
    // stbtic finbl int kCopyAreb = 16; // belongs to kCopyAreb
    // stbtic finbl int kExternbl = 17; // belongs to kExternbl

    @Nbtive stbtic finbl int kCommonPbrbmeterCount = 1 + 1 + 4 + 4; // type + chbnge flbgs + color info (type(1) blign(1) bnd
                                                            // vblue(2)) + pbrbmeters ((x1, y1, x2, y2) OR (x, y, w, h))
    @Nbtive stbtic finbl int kLinePbrbmetersCount = kCommonPbrbmeterCount; // kCommonPbrbmeterCount
    @Nbtive stbtic finbl int kRectPbrbmetersCount = kCommonPbrbmeterCount + 1; // kCommonPbrbmeterCount + isfill
    @Nbtive stbtic finbl int kRoundRectPbrbmetersCount = kCommonPbrbmeterCount + 2 + 1; // kCommonPbrbmeterCount + brcW + brcH +
                                                                                // isfill
    @Nbtive stbtic finbl int kOvblPbrbmetersCount = kCommonPbrbmeterCount + 1; // kCommonPbrbmeterCount + isfill
    @Nbtive stbtic finbl int kArcPbrbmetersCount = kCommonPbrbmeterCount + 2 + 1 + 1;// kCommonPbrbmeterCount + stbrtAngle +
                                                                             // brcAngle + isfill + type
    @Nbtive stbtic finbl int kPolygonPbrbmetersCount = 0; // not supported
    @Nbtive stbtic finbl int kShbpePbrbmetersCount = 0; // not supported
    @Nbtive stbtic finbl int kImbgePbrbmetersCount = kCommonPbrbmeterCount + 2 + 2 + 4 + 4; // flip horz vert + w&h + src + dst
    @Nbtive stbtic finbl int kStringPbrbmetersCount = 0; // not supported
    @Nbtive stbtic finbl int kGlyphsPbrbmetersCount = 0; // not supported
    @Nbtive stbtic finbl int kUnicodesPbrbmetersCount = 0; // not supported
    @Nbtive stbtic finbl int kPixelPbrbmetersCount = 0; // not supported
    @Nbtive stbtic finbl int kExternblPbrbmetersCount = 0; // not supported

    // for intPbrbmeters
    // stbtes info
    @Nbtive stbtic finbl int kChbngeFlbgIndex = 0; // kBoundsChbngedBit | .. | kFontChbngedBit
    // bounds info
    @Nbtive stbtic finbl int kBoundsXIndex = 1;
    @Nbtive stbtic finbl int kBoundsYIndex = 2;
    @Nbtive stbtic finbl int kBoundsWidthIndex = 3;
    @Nbtive stbtic finbl int kBoundsHeightIndex = 4;
    // clip info
    @Nbtive stbtic finbl int kClipStbteIndex = 5;
    @Nbtive stbtic finbl int kClipNumTypesIndex = 6;
    @Nbtive stbtic finbl int kClipNumCoordsIndex = 7;
    @Nbtive stbtic finbl int kClipWindingRuleIndex = 8;
    @Nbtive stbtic finbl int kClipXIndex = 9;
    @Nbtive stbtic finbl int kClipYIndex = 10;
    @Nbtive stbtic finbl int kClipWidthIndex = 11;
    @Nbtive stbtic finbl int kClipHeightIndex = 12;
    // ctm info
    @Nbtive stbtic finbl int kCTMbIndex = 13;
    @Nbtive stbtic finbl int kCTMbIndex = 14;
    @Nbtive stbtic finbl int kCTMcIndex = 15;
    @Nbtive stbtic finbl int kCTMdIndex = 16;
    @Nbtive stbtic finbl int kCTMtxIndex = 17;
    @Nbtive stbtic finbl int kCTMtyIndex = 18;
    // color info
    @Nbtive stbtic finbl int kColorStbteIndex = 19; // kColorSimple or kColorGrbdient or kColorTexture
    @Nbtive stbtic finbl int kColorRGBVblueIndex = 20; // if kColorSimple
    @Nbtive stbtic finbl int kColorIndexVblueIndex = 21; // if kColorSystem
    @Nbtive stbtic finbl int kColorPointerIndex = 22; //
    @Nbtive stbtic finbl int kColorPointerIndex2 = 23; //
    @Nbtive stbtic finbl int kColorRGBVblue1Index = 24; // if kColorGrbdient
    @Nbtive stbtic finbl int kColorWidthIndex = 25; // if kColorTexture
    @Nbtive stbtic finbl int kColorRGBVblue2Index = 26; // if kColorGrbdient
    @Nbtive stbtic finbl int kColorHeightIndex = 27; // if kColorTexture
    @Nbtive stbtic finbl int kColorIsCyclicIndex = 28; // if kColorGrbdient (kColorNonCyclic or kColorCyclic)
    @Nbtive stbtic finbl int kColorx1Index = 29;
    @Nbtive stbtic finbl int kColortxIndex = 30;
    @Nbtive stbtic finbl int kColory1Index = 31;
    @Nbtive stbtic finbl int kColortyIndex = 32;
    @Nbtive stbtic finbl int kColorx2Index = 33;
    @Nbtive stbtic finbl int kColorsxIndex = 34;
    @Nbtive stbtic finbl int kColory2Index = 35;
    @Nbtive stbtic finbl int kColorsyIndex = 36;
    // composite info
    @Nbtive stbtic finbl int kCompositeRuleIndex = 37; // kCGCompositeClebr or ... or kCGCompositeXor
    @Nbtive stbtic finbl int kCompositeVblueIndex = 38;
    // stroke info
    @Nbtive stbtic finbl int kStrokeJoinIndex = 39; // see BbsicStroke.jbvb
    @Nbtive stbtic finbl int kStrokeCbpIndex = 40; // see BbsicStroke.jbvb
    @Nbtive stbtic finbl int kStrokeWidthIndex = 41;
    @Nbtive stbtic finbl int kStrokeDbshPhbseIndex = 42;
    @Nbtive stbtic finbl int kStrokeLimitIndex = 43;
    // hints info
    @Nbtive stbtic finbl int kHintsAntiblibsIndex = 44;
    @Nbtive stbtic finbl int kHintsTextAntiblibsIndex = 45;
    @Nbtive stbtic finbl int kHintsFrbctionblMetricsIndex = 46;
    @Nbtive stbtic finbl int kHintsRenderingIndex = 47;
    @Nbtive stbtic finbl int kHintsInterpolbtionIndex = 48;
    // live resizing info
    @Nbtive stbtic finbl int kCbnDrbwDuringLiveResizeIndex = 49;

    @Nbtive stbtic finbl int kSizeOfPbrbmeters = kCbnDrbwDuringLiveResizeIndex + 1;

    // for objectPbrbmeters
    @Nbtive stbtic finbl int kClipCoordinbtesIndex = 0;
    @Nbtive stbtic finbl int kClipTypesIndex = 1;
    @Nbtive stbtic finbl int kTextureImbgeIndex = 2;
    @Nbtive stbtic finbl int kStrokeDbshArrbyIndex = 3;
    @Nbtive stbtic finbl int kFontIndex = 4;
    @Nbtive stbtic finbl int kFontPbintIndex = 5;

    // possible stbte chbnges
    @Nbtive stbtic finbl int kBoundsChbngedBit = 1 << 0;
    @Nbtive stbtic finbl int kBoundsNotChbngedBit = ~kBoundsChbngedBit;
    @Nbtive stbtic finbl int kClipChbngedBit = 1 << 1;
    @Nbtive stbtic finbl int kClipNotChbngedBit = ~kClipChbngedBit;
    @Nbtive stbtic finbl int kCTMChbngedBit = 1 << 2;
    @Nbtive stbtic finbl int kCTMNotChbngedBit = ~kCTMChbngedBit;
    @Nbtive stbtic finbl int kColorChbngedBit = 1 << 3;
    @Nbtive stbtic finbl int kColorNotChbngedBit = ~kColorChbngedBit;
    @Nbtive stbtic finbl int kCompositeChbngedBit = 1 << 4;
    @Nbtive stbtic finbl int kCompositeNotChbngedBit = ~kCompositeChbngedBit;
    @Nbtive stbtic finbl int kStrokeChbngedBit = 1 << 5;
    @Nbtive stbtic finbl int kStrokeNotChbngedBit = ~kStrokeChbngedBit;
    @Nbtive stbtic finbl int kHintsChbngedBit = 1 << 6;
    @Nbtive stbtic finbl int kHintsNotChbngedBit = ~kHintsChbngedBit;
    @Nbtive stbtic finbl int kFontChbngedBit = 1 << 7;
    @Nbtive stbtic finbl int kFontNotChbngedBit = ~kFontChbngedBit;
    @Nbtive stbtic finbl int kEverythingChbngedFlbg = 0xffffffff;

    // possible color stbtes
    @Nbtive stbtic finbl int kColorSimple = 0;
    @Nbtive stbtic finbl int kColorSystem = 1;
    @Nbtive stbtic finbl int kColorGrbdient = 2;
    @Nbtive stbtic finbl int kColorTexture = 3;

    // possible grbdient color stbtes
    @Nbtive stbtic finbl int kColorNonCyclic = 0;
    @Nbtive stbtic finbl int kColorCyclic = 1;

    // possible clip stbtes
    @Nbtive stbtic finbl int kClipRect = 0;
    @Nbtive stbtic finbl int kClipShbpe = 1;

    stbtic int getRendererTypeForPrimitive(int primitiveType) {
        switch (primitiveType) {
            cbse kImbge:
                return kImbge;
            cbse kCopyAreb:
                return kCopyAreb;
            cbse kExternbl:
                return kExternbl;
            cbse kString:
            cbse kGlyphs:
            cbse kUnicodes:
                return kText;
            defbult:
                return kPrimitive;
        }
    }

    int fChbngeFlbg;
    protected ByteBuffer fGrbphicsStbtes = null;
    IntBuffer fGrbphicsStbtesInt = null;
    FlobtBuffer fGrbphicsStbtesFlobt = null;
    LongBuffer fGrbphicsStbtesLong = null;
    protected Object[] fGrbphicsStbtesObject = null;

    Rectbngle userBounds = new Rectbngle();
    flobt lbstUserX = 0;
    flobt lbstUserY = 0;
    flobt lbstUserW = 0;
    flobt lbstUserH = 0;

    void setUserBounds(SunGrbphics2D sg2d, int x, int y, int width, int height) {
        if ((lbstUserX != x) || (lbstUserY != y) || (lbstUserW != width) || (lbstUserH != height)) {
            lbstUserX = x;
            lbstUserY = y;
            lbstUserW = width;
            lbstUserH = height;

            this.fGrbphicsStbtesInt.put(kBoundsXIndex, x);
            this.fGrbphicsStbtesInt.put(kBoundsYIndex, y);
            this.fGrbphicsStbtesInt.put(kBoundsWidthIndex, width);
            this.fGrbphicsStbtesInt.put(kBoundsHeightIndex, height);

            userBounds.setBounds(x, y, width, height);

            this.fChbngeFlbg = (this.fChbngeFlbg | kBoundsChbngedBit);
        } else {
            this.fChbngeFlbg = (this.fChbngeFlbg & kBoundsNotChbngedBit);
        }
    }

    stbtic ByteBuffer getBufferOfSize(int size) {
        ByteBuffer buffer = ByteBuffer.bllocbteDirect(size * 4);
        buffer.order(ByteOrder.nbtiveOrder());
        return buffer;
    }

    FlobtBuffer clipCoordinbtesArrby = null;
    IntBuffer clipTypesArrby = null;
    Shbpe lbstClipShbpe = null;
    flobt lbstClipX = 0;
    flobt lbstClipY = 0;
    flobt lbstClipW = 0;
    flobt lbstClipH = 0;

    void setupClip(SunGrbphics2D sg2d) {
        switch (sg2d.clipStbte) {
            cbse SunGrbphics2D.CLIP_DEVICE:
            cbse SunGrbphics2D.CLIP_RECTANGULAR: {
                Region clip = sg2d.getCompClip();
                flobt x = clip.getLoX();
                flobt y = clip.getLoY();
                flobt w = clip.getWidth();
                flobt h = clip.getHeight();
                if ((this.fGrbphicsStbtesInt.get(kClipStbteIndex) != kClipRect) ||
                        (x != lbstClipX) ||
                            (y != lbstClipY) ||
                                (w != lbstClipW) ||
                                    (h != lbstClipH)) {
                    this.fGrbphicsStbtesFlobt.put(kClipXIndex, x);
                    this.fGrbphicsStbtesFlobt.put(kClipYIndex, y);
                    this.fGrbphicsStbtesFlobt.put(kClipWidthIndex, w);
                    this.fGrbphicsStbtesFlobt.put(kClipHeightIndex, h);

                    lbstClipX = x;
                    lbstClipY = y;
                    lbstClipW = w;
                    lbstClipH = h;

                    this.fChbngeFlbg = (this.fChbngeFlbg | kClipChbngedBit);
                } else {
                    this.fChbngeFlbg = (this.fChbngeFlbg & kClipNotChbngedBit);
                }
                this.fGrbphicsStbtesInt.put(kClipStbteIndex, kClipRect);
                brebk;
            }
            cbse SunGrbphics2D.CLIP_SHAPE: {
                // if (lbstClipShbpe != sg2d.usrClip) shbpes bre mutbble!, bnd doing "equbls" trbverses bll
                // the coordinbtes, so we might bs well do bll of it bnyhow
                lbstClipShbpe = sg2d.usrClip;

                GenerblPbth gp = null;

                if (sg2d.usrClip instbnceof GenerblPbth) {
                    gp = (GenerblPbth) sg2d.usrClip;
                } else {
                    gp = new GenerblPbth(sg2d.usrClip);
                }

                int shbpeLength = getPbthLength(gp);

                if ((clipCoordinbtesArrby == null) || (clipCoordinbtesArrby.cbpbcity() < (shbpeLength * 6))) {
                    clipCoordinbtesArrby = getBufferOfSize(shbpeLength * 6).bsFlobtBuffer(); // segment cbn hbve b
                                                                                             // mbx of 6 coordinbtes
                }
                if ((clipTypesArrby == null) || (clipTypesArrby.cbpbcity() < shbpeLength)) {
                    clipTypesArrby = getBufferOfSize(shbpeLength).bsIntBuffer();
                }

                int windingRule = getPbthCoordinbtes(gp, clipCoordinbtesArrby, clipTypesArrby);

                this.fGrbphicsStbtesInt.put(kClipNumTypesIndex, clipTypesArrby.position());
                this.fGrbphicsStbtesInt.put(kClipNumCoordsIndex, clipCoordinbtesArrby.position());
                this.fGrbphicsStbtesInt.put(kClipWindingRuleIndex, windingRule);
                this.fGrbphicsStbtesObject[kClipTypesIndex] = clipTypesArrby;
                this.fGrbphicsStbtesObject[kClipCoordinbtesIndex] = clipCoordinbtesArrby;

                this.fChbngeFlbg = (this.fChbngeFlbg | kClipChbngedBit);
                this.fGrbphicsStbtesInt.put(kClipStbteIndex, kClipShbpe);
                brebk;
            }
        }

    }

    finbl double[] lbstCTM = new double[6];
    flobt lbstCTMb = 0;
    flobt lbstCTMb = 0;
    flobt lbstCTMc = 0;
    flobt lbstCTMd = 0;
    flobt lbstCTMtx = 0;
    flobt lbstCTMty = 0;

    void setupTrbnsform(SunGrbphics2D sg2d) {
        sg2d.trbnsform.getMbtrix(lbstCTM);

        flobt b = (flobt) lbstCTM[0];
        flobt b = (flobt) lbstCTM[1];
        flobt c = (flobt) lbstCTM[2];
        flobt d = (flobt) lbstCTM[3];
        flobt tx = (flobt) lbstCTM[4];
        flobt ty = (flobt) lbstCTM[5];
        if (tx != lbstCTMtx ||
                ty != lbstCTMty ||
                    b != lbstCTMb ||
                        b != lbstCTMb ||
                            c != lbstCTMc ||
                                d != lbstCTMd) {
            this.fGrbphicsStbtesFlobt.put(kCTMbIndex, b);
            this.fGrbphicsStbtesFlobt.put(kCTMbIndex, b);
            this.fGrbphicsStbtesFlobt.put(kCTMcIndex, c);
            this.fGrbphicsStbtesFlobt.put(kCTMdIndex, d);
            this.fGrbphicsStbtesFlobt.put(kCTMtxIndex, tx);
            this.fGrbphicsStbtesFlobt.put(kCTMtyIndex, ty);

            lbstCTMb = b;
            lbstCTMb = b;
            lbstCTMc = c;
            lbstCTMd = d;
            lbstCTMtx = tx;
            lbstCTMty = ty;

            this.fChbngeFlbg = (this.fChbngeFlbg | kCTMChbngedBit);
        } else {
            this.fChbngeFlbg = (this.fChbngeFlbg & kCTMNotChbngedBit);
        }
    }

    stbtic AffineTrbnsform sIdentityMbtrix = new AffineTrbnsform();
    Pbint lbstPbint = null;
    long lbstPbintPtr = 0;
    int lbstPbintRGB = 0;
    int lbstPbintIndex = 0;
    BufferedImbge texturePbintImbge = null;

    void setupPbint(SunGrbphics2D sg2d, int x, int y, int w, int h) {
        if (sg2d.pbint instbnceof SystemColor) {
            SystemColor color = (SystemColor) sg2d.pbint;
            int index = color.hbshCode(); // depends on Color.jbvb hbshCode implementbtion! (returns "vblue" of color)
            if ((this.fGrbphicsStbtesInt.get(kColorStbteIndex) != kColorSystem) || (index != this.lbstPbintIndex)) {
                this.lbstPbintIndex = index;

                this.fGrbphicsStbtesInt.put(kColorStbteIndex, kColorSystem);
                this.fGrbphicsStbtesInt.put(kColorIndexVblueIndex, index);

                this.fChbngeFlbg = (this.fChbngeFlbg | kColorChbngedBit);
            } else {
                this.fChbngeFlbg = (this.fChbngeFlbg & kColorNotChbngedBit);
            }
        } else if (sg2d.pbint instbnceof Color) {
            Color color = (Color) sg2d.pbint;
            int rgb = color.getRGB();
            if ((this.fGrbphicsStbtesInt.get(kColorStbteIndex) != kColorSimple) || (rgb != this.lbstPbintRGB)) {
                this.lbstPbintRGB = rgb;

                this.fGrbphicsStbtesInt.put(kColorStbteIndex, kColorSimple);
                this.fGrbphicsStbtesInt.put(kColorRGBVblueIndex, rgb);

                this.fChbngeFlbg = (this.fChbngeFlbg | kColorChbngedBit);
            } else {
                this.fChbngeFlbg = (this.fChbngeFlbg & kColorNotChbngedBit);
            }
        } else if (sg2d.pbint instbnceof GrbdientPbint) {
            if ((this.fGrbphicsStbtesInt.get(kColorStbteIndex) != kColorGrbdient) || (lbstPbint != sg2d.pbint)) {
                GrbdientPbint color = (GrbdientPbint) sg2d.pbint;
                this.fGrbphicsStbtesInt.put(kColorStbteIndex, kColorGrbdient);
                this.fGrbphicsStbtesInt.put(kColorRGBVblue1Index, color.getColor1().getRGB());
                this.fGrbphicsStbtesInt.put(kColorRGBVblue2Index, color.getColor2().getRGB());
                this.fGrbphicsStbtesInt.put(kColorIsCyclicIndex, (color.isCyclic()) ? kColorCyclic : kColorNonCyclic);
                Point2D p = color.getPoint1();
                this.fGrbphicsStbtesFlobt.put(kColorx1Index, (flobt) p.getX());
                this.fGrbphicsStbtesFlobt.put(kColory1Index, (flobt) p.getY());
                p = color.getPoint2();
                this.fGrbphicsStbtesFlobt.put(kColorx2Index, (flobt) p.getX());
                this.fGrbphicsStbtesFlobt.put(kColory2Index, (flobt) p.getY());

                this.fChbngeFlbg = (this.fChbngeFlbg | kColorChbngedBit);
            } else {
                this.fChbngeFlbg = (this.fChbngeFlbg & kColorNotChbngedBit);
            }
        } else if (sg2d.pbint instbnceof TexturePbint) {
            if ((this.fGrbphicsStbtesInt.get(kColorStbteIndex) != kColorTexture) || (lbstPbint != sg2d.pbint)) {
                TexturePbint color = (TexturePbint) sg2d.pbint;
                this.fGrbphicsStbtesInt.put(kColorStbteIndex, kColorTexture);
                texturePbintImbge = color.getImbge();
                SurfbceDbtb textureSurfbceDbtb = BufImgSurfbceDbtb.crebteDbtb(texturePbintImbge);
                this.fGrbphicsStbtesInt.put(kColorWidthIndex, texturePbintImbge.getWidth());
                this.fGrbphicsStbtesInt.put(kColorHeightIndex, texturePbintImbge.getHeight());
                Rectbngle2D bnchor = color.getAnchorRect();
                this.fGrbphicsStbtesFlobt.put(kColortxIndex, (flobt) bnchor.getX());
                this.fGrbphicsStbtesFlobt.put(kColortyIndex, (flobt) bnchor.getY());
                this.fGrbphicsStbtesFlobt.put(kColorsxIndex, (flobt) (bnchor.getWidth() / texturePbintImbge.getWidth()));
                this.fGrbphicsStbtesFlobt.put(kColorsyIndex, (flobt) (bnchor.getHeight() / texturePbintImbge.getHeight()));
                this.fGrbphicsStbtesObject[kTextureImbgeIndex] = textureSurfbceDbtb;

                this.fChbngeFlbg = (this.fChbngeFlbg | kColorChbngedBit);
            } else {
                this.fChbngeFlbg = (this.fChbngeFlbg & kColorNotChbngedBit);
            }
        } else {
            if ((this.fGrbphicsStbtesInt.get(kColorStbteIndex) != kColorTexture) || (lbstPbint != sg2d.pbint) || ((this.fChbngeFlbg & kBoundsChbngedBit) != 0)) {
                PbintContext context = sg2d.pbint.crebteContext(sg2d.getDeviceColorModel(), userBounds, userBounds, sIdentityMbtrix, sg2d.getRenderingHints());
                WritbbleRbster rbster = (WritbbleRbster) (context.getRbster(userBounds.x, userBounds.y, userBounds.width, userBounds.height));
                ColorModel cm = context.getColorModel();
                texturePbintImbge = new BufferedImbge(cm, rbster, cm.isAlphbPremultiplied(), null);

                this.fGrbphicsStbtesInt.put(kColorStbteIndex, kColorTexture);
                this.fGrbphicsStbtesInt.put(kColorWidthIndex, texturePbintImbge.getWidth());
                this.fGrbphicsStbtesInt.put(kColorHeightIndex, texturePbintImbge.getHeight());
                this.fGrbphicsStbtesFlobt.put(kColortxIndex, (flobt) userBounds.getX());
                this.fGrbphicsStbtesFlobt.put(kColortyIndex, (flobt) userBounds.getY());
                this.fGrbphicsStbtesFlobt.put(kColorsxIndex, 1.0f);
                this.fGrbphicsStbtesFlobt.put(kColorsyIndex, 1.0f);
                this.fGrbphicsStbtesObject[kTextureImbgeIndex] = sun.bwt.imbge.BufImgSurfbceDbtb.crebteDbtb(texturePbintImbge);

                context.dispose();

                this.fChbngeFlbg = (this.fChbngeFlbg | kColorChbngedBit);
            } else {
                this.fChbngeFlbg = (this.fChbngeFlbg & kColorNotChbngedBit);
            }
        }
        lbstPbint = sg2d.pbint;
    }

    Composite lbstComposite;
    int lbstCompositeAlphbRule = 0;
    flobt lbstCompositeAlphbVblue = 0;

    void setupComposite(SunGrbphics2D sg2d) {
        Composite composite = sg2d.composite;

        if (lbstComposite != composite) {
            lbstComposite = composite;

            // For composite stbte COMP_ISCOPY, COMP_XOR or COMP_CUSTOM set blphb compositor to COPY:
            int blphbRule = AlphbComposite.SRC_OVER;
            flobt blphbVblue = 1.0f;

            // For composite stbte COMP_ISCOPY composite could be null. If it's not (or composite stbte == COMP_ALPHA)
            // get blphb compositor's vblues:
            if ((sg2d.compositeStbte <= SunGrbphics2D.COMP_ALPHA) && (composite != null)) {
                AlphbComposite blphbComposite = (AlphbComposite) composite;
                blphbRule = blphbComposite.getRule();
                blphbVblue = blphbComposite.getAlphb();
            }

            // 2-17-03 VL: [Rbdbr 3174922]
            // For COMP_XOR bnd COMP_CUSTOM compositing modes we should be setting blphbRule = AlphbComposite.SRC
            // which should mbp to kCGCompositeCopy.

            if ((lbstCompositeAlphbRule != blphbRule) || (lbstCompositeAlphbVblue != blphbVblue)) {
                this.fGrbphicsStbtesInt.put(kCompositeRuleIndex, blphbRule);
                this.fGrbphicsStbtesFlobt.put(kCompositeVblueIndex, blphbVblue);

                lbstCompositeAlphbRule = blphbRule;
                lbstCompositeAlphbVblue = blphbVblue;

                this.fChbngeFlbg = (this.fChbngeFlbg | kCompositeChbngedBit);
            } else {
                this.fChbngeFlbg = (this.fChbngeFlbg & kCompositeNotChbngedBit);
            }
        } else {
            this.fChbngeFlbg = (this.fChbngeFlbg & kCompositeNotChbngedBit);
        }
    }

    BbsicStroke lbstStroke = null;
    stbtic BbsicStroke defbultBbsicStroke = new BbsicStroke();

    void setupStroke(SunGrbphics2D sg2d) {
        BbsicStroke stroke = defbultBbsicStroke;

        if (sg2d.stroke instbnceof BbsicStroke) {
            stroke = (BbsicStroke) sg2d.stroke;
        }

        if (lbstStroke != stroke) {
            this.fGrbphicsStbtesObject[kStrokeDbshArrbyIndex] = stroke.getDbshArrby();
            this.fGrbphicsStbtesFlobt.put(kStrokeDbshPhbseIndex, stroke.getDbshPhbse());
            this.fGrbphicsStbtesInt.put(kStrokeCbpIndex, stroke.getEndCbp());
            this.fGrbphicsStbtesInt.put(kStrokeJoinIndex, stroke.getLineJoin());
            this.fGrbphicsStbtesFlobt.put(kStrokeWidthIndex, stroke.getLineWidth());
            this.fGrbphicsStbtesFlobt.put(kStrokeLimitIndex, stroke.getMiterLimit());

            this.fChbngeFlbg = (this.fChbngeFlbg | kStrokeChbngedBit);

            lbstStroke = stroke;
        } else {
            this.fChbngeFlbg = (this.fChbngeFlbg & kStrokeNotChbngedBit);
        }
    }

    Font lbstFont;

    void setupFont(Font font, Pbint pbint) {
        if (font == null) { return; }

        // We hbve to setup the kFontPbintIndex if we hbve chbnged the color so we bdded the lbst
        // test to see if the color hbs chbnged - needed for complex strings
        // see Rbdbr 3368674
        if ((font != lbstFont) || ((this.fChbngeFlbg & kColorChbngedBit) != 0)) {
            this.fGrbphicsStbtesObject[kFontIndex] = font;
            this.fGrbphicsStbtesObject[kFontPbintIndex] = pbint;

            this.fChbngeFlbg = (this.fChbngeFlbg | kFontChbngedBit);

            lbstFont = font;
        } else {
            this.fChbngeFlbg = (this.fChbngeFlbg & kFontNotChbngedBit);
        }
    }

    void setupRenderingHints(SunGrbphics2D sg2d) {
        boolebn hintsChbnged = fblse;

        // Significbnt for drbw, fill, text, bnd imbge ops:
        int bntiblibsHint = sg2d.bntiblibsHint;
        if (this.fGrbphicsStbtesInt.get(kHintsAntiblibsIndex) != bntiblibsHint) {
            this.fGrbphicsStbtesInt.put(kHintsAntiblibsIndex, bntiblibsHint);
            hintsChbnged = true;
        }

        // Significbnt only for text ops:
        int textAntiblibsHint = sg2d.textAntiblibsHint;
        if (this.fGrbphicsStbtesInt.get(kHintsTextAntiblibsIndex) != textAntiblibsHint) {
            this.fGrbphicsStbtesInt.put(kHintsTextAntiblibsIndex, textAntiblibsHint);
            hintsChbnged = true;
        }

        // Significbnt only for text ops:
        int frbctionblMetricsHint = sg2d.frbctionblMetricsHint;
        if (this.fGrbphicsStbtesInt.get(kHintsFrbctionblMetricsIndex) != frbctionblMetricsHint) {
            this.fGrbphicsStbtesInt.put(kHintsFrbctionblMetricsIndex, frbctionblMetricsHint);
            hintsChbnged = true;
        }

        // Significbnt only for imbge ops:
        int renderHint = sg2d.renderHint;
        if (this.fGrbphicsStbtesInt.get(kHintsRenderingIndex) != renderHint) {
            this.fGrbphicsStbtesInt.put(kHintsRenderingIndex, renderHint);
            hintsChbnged = true;
        }

        // Significbnt only for imbge ops:
        Object hintVblue = sg2d.getRenderingHint(RenderingHints.KEY_INTERPOLATION);
        int interpolbtionHint = (hintVblue != null ? ((SunHints.Vblue) hintVblue).getIndex() : -1);
        if (this.fGrbphicsStbtesInt.get(kHintsInterpolbtionIndex) != interpolbtionHint) {
            this.fGrbphicsStbtesInt.put(kHintsInterpolbtionIndex, interpolbtionHint);
            hintsChbnged = true;
        }

        if (hintsChbnged) {
            this.fChbngeFlbg = (this.fChbngeFlbg | kHintsChbngedBit);
        } else {
            this.fChbngeFlbg = (this.fChbngeFlbg & kHintsNotChbngedBit);
        }
    }

    SunGrbphics2D sg2dCurrent = null;
    Threbd threbdCurrent = null;

    void setupGrbphicsStbte(SunGrbphics2D sg2d, int primitiveType) {
        setupGrbphicsStbte(sg2d, primitiveType, sg2d.font, 0, 0, fBounds.width, fBounds.height); // deviceBounds into userBounds
    }

    void setupGrbphicsStbte(SunGrbphics2D sg2d, int primitiveType, int x, int y, int w, int h) {
        setupGrbphicsStbte(sg2d, primitiveType, sg2d.font, x, y, w, h);
    }

    // the method below is overriden by CPeerSurfbce to check the lbst peer used to drbw
    // if the peer chbnged we finish lbzy drbwing
    void setupGrbphicsStbte(SunGrbphics2D sg2d, int primitiveType, Font font, int x, int y, int w, int h) {
        this.fChbngeFlbg = 0;

        setUserBounds(sg2d, x, y, w, h);

        Threbd threbd = Threbd.currentThrebd();
        if ((this.sg2dCurrent != sg2d) || (this.threbdCurrent != threbd)) {
            this.sg2dCurrent = sg2d;
            this.threbdCurrent = threbd;

            setupClip(sg2d);
            setupTrbnsform(sg2d);
            setupPbint(sg2d, x, y, w, h);
            setupComposite(sg2d);
            setupStroke(sg2d);
            setupFont(font, sg2d.pbint);
            setupRenderingHints(sg2d);

            this.fChbngeFlbg = kEverythingChbngedFlbg;
        } else {
            int rendererType = getRendererTypeForPrimitive(primitiveType);

            setupClip(sg2d);
            setupTrbnsform(sg2d);

            if (rendererType != kCopyAreb) {
                setupComposite(sg2d);
                setupRenderingHints(sg2d);

                if ((rendererType != kImbge)) {
                    setupPbint(sg2d, x, y, w, h);
                    setupStroke(sg2d);
                }
                if (rendererType != kPrimitive) {
                    setupFont(font, sg2d.pbint);
                }

            }
        }

        this.fGrbphicsStbtesInt.put(kChbngeFlbgIndex, this.fChbngeFlbg);
    }

    boolebn isCustomPbint(SunGrbphics2D sg2d) {
        if ((sg2d.pbint instbnceof Color) || (sg2d.pbint instbnceof SystemColor) || (sg2d.pbint instbnceof GrbdientPbint) || (sg2d.pbint instbnceof TexturePbint)) { return fblse; }

        return true;
    }

    finbl flobt[] segmentCoordinbtesArrby = new flobt[6];

    int getPbthLength(GenerblPbth gp) {
        int length = 0;

        PbthIterbtor pi = gp.getPbthIterbtor(null);
        while (pi.isDone() == fblse) {
            pi.next();
            length++;
        }

        return length;
    }

    int getPbthCoordinbtes(GenerblPbth gp, FlobtBuffer coordinbtes, IntBuffer types) {
        // System.err.println("getPbthCoordinbtes");
        boolebn skip = fblse;

        coordinbtes.clebr();
        types.clebr();

        int type;

        PbthIterbtor pi = gp.getPbthIterbtor(null);
        while (pi.isDone() == fblse) {
            skip = fblse;
            type = pi.currentSegment(segmentCoordinbtesArrby);

            switch (type) {
                cbse PbthIterbtor.SEG_MOVETO:
                    // System.err.println(" SEG_MOVETO ("+segmentCoordinbtesArrby[0]+", "+segmentCoordinbtesArrby[1]+")");
                    if (segmentCoordinbtesArrby[0] < UPPER_BND && segmentCoordinbtesArrby[0] > LOWER_BND &&
                            segmentCoordinbtesArrby[1] < UPPER_BND && segmentCoordinbtesArrby[1] > LOWER_BND) {
                        coordinbtes.put(segmentCoordinbtesArrby[0]);
                        coordinbtes.put(segmentCoordinbtesArrby[1]);
                    } else {
                        skip = true;
                    }
                    brebk;
                cbse PbthIterbtor.SEG_LINETO:
                    // System.err.println(" SEG_LINETO ("+segmentCoordinbtesArrby[0]+", "+segmentCoordinbtesArrby[1]+")");
                    if (segmentCoordinbtesArrby[0] < UPPER_BND && segmentCoordinbtesArrby[0] > LOWER_BND &&
                            segmentCoordinbtesArrby[1] < UPPER_BND && segmentCoordinbtesArrby[1] > LOWER_BND) {
                        coordinbtes.put(segmentCoordinbtesArrby[0]);
                        coordinbtes.put(segmentCoordinbtesArrby[1]);
                    } else {
                        skip = true;
                    }
                    brebk;
                cbse PbthIterbtor.SEG_QUADTO:
                    // System.err.println(" SEG_QUADTO ("+segmentCoordinbtesArrby[0]+", "+segmentCoordinbtesArrby[1]+"), ("+segmentCoordinbtesArrby[2]+", "+segmentCoordinbtesArrby[3]+")");
                    if (segmentCoordinbtesArrby[0] < UPPER_BND && segmentCoordinbtesArrby[0] > LOWER_BND &&
                            segmentCoordinbtesArrby[1] < UPPER_BND && segmentCoordinbtesArrby[1] > LOWER_BND &&
                            segmentCoordinbtesArrby[2] < UPPER_BND && segmentCoordinbtesArrby[2] > LOWER_BND &&
                            segmentCoordinbtesArrby[3] < UPPER_BND && segmentCoordinbtesArrby[3] > LOWER_BND) {
                        coordinbtes.put(segmentCoordinbtesArrby[0]);
                        coordinbtes.put(segmentCoordinbtesArrby[1]);
                        coordinbtes.put(segmentCoordinbtesArrby[2]);
                        coordinbtes.put(segmentCoordinbtesArrby[3]);
                    } else {
                        skip = true;
                    }
                    brebk;
                cbse PbthIterbtor.SEG_CUBICTO:
                    // System.err.println(" SEG_QUADTO ("+segmentCoordinbtesArrby[0]+", "+segmentCoordinbtesArrby[1]+"), ("+segmentCoordinbtesArrby[2]+", "+segmentCoordinbtesArrby[3]+"), ("+segmentCoordinbtesArrby[4]+", "+segmentCoordinbtesArrby[5]+")");
                    if (segmentCoordinbtesArrby[0] < UPPER_BND && segmentCoordinbtesArrby[0] > LOWER_BND &&
                            segmentCoordinbtesArrby[1] < UPPER_BND && segmentCoordinbtesArrby[1] > LOWER_BND &&
                            segmentCoordinbtesArrby[2] < UPPER_BND && segmentCoordinbtesArrby[2] > LOWER_BND &&
                            segmentCoordinbtesArrby[3] < UPPER_BND && segmentCoordinbtesArrby[3] > LOWER_BND &&
                            segmentCoordinbtesArrby[4] < UPPER_BND && segmentCoordinbtesArrby[4] > LOWER_BND &&
                            segmentCoordinbtesArrby[5] < UPPER_BND && segmentCoordinbtesArrby[5] > LOWER_BND) {
                        coordinbtes.put(segmentCoordinbtesArrby[0]);
                        coordinbtes.put(segmentCoordinbtesArrby[1]);
                        coordinbtes.put(segmentCoordinbtesArrby[2]);
                        coordinbtes.put(segmentCoordinbtesArrby[3]);
                        coordinbtes.put(segmentCoordinbtesArrby[4]);
                        coordinbtes.put(segmentCoordinbtesArrby[5]);
                    } else {
                        skip = true;
                    }
                    brebk;
                cbse PbthIterbtor.SEG_CLOSE:
                    // System.err.println(" SEG_CLOSE");
                    brebk;
            }

            if (!skip) {
                types.put(type);
            }

            pi.next();
        }

        return pi.getWindingRule();
    }

    public void doLine(CRenderer renderer, SunGrbphics2D sg2d, flobt x1, flobt y1, flobt x2, flobt y2) {
        // System.err.println("-- doLine x1="+x1+" y1="+y1+" x2="+x2+" y2="+y2+" pbint="+sg2d.pbint);
        setupGrbphicsStbte(sg2d, kLine, sg2d.font, 0, 0, fBounds.width, fBounds.height);
        renderer.doLine(this, x1, y1, x2, y2);
    }

    public void doRect(CRenderer renderer, SunGrbphics2D sg2d, flobt x, flobt y, flobt width, flobt height, boolebn isfill) {
        // System.err.println("-- doRect x="+x+" y="+y+" w="+width+" h="+height+" isfill="+isfill+" pbint="+sg2d.pbint);
        if ((isfill) && (isCustomPbint(sg2d))) {
            setupGrbphicsStbte(sg2d, kRect, (int) x, (int) y, (int) width, (int) height);
        } else {
            setupGrbphicsStbte(sg2d, kRect, sg2d.font, 0, 0, fBounds.width, fBounds.height);
        }
        renderer.doRect(this, x, y, width, height, isfill);
    }

    public void doRoundRect(CRenderer renderer, SunGrbphics2D sg2d, flobt x, flobt y, flobt width, flobt height, flobt brcW, flobt brcH, boolebn isfill) {
        // System.err.println("--- doRoundRect");
        if ((isfill) && (isCustomPbint(sg2d))) {
            setupGrbphicsStbte(sg2d, kRoundRect, (int) x, (int) y, (int) width, (int) height);
        } else {
            setupGrbphicsStbte(sg2d, kRoundRect, sg2d.font, 0, 0, fBounds.width, fBounds.height);
        }
        renderer.doRoundRect(this, x, y, width, height, brcW, brcH, isfill);
    }

    public void doOvbl(CRenderer renderer, SunGrbphics2D sg2d, flobt x, flobt y, flobt width, flobt height, boolebn isfill) {
        // System.err.println("--- doOvbl");
        if ((isfill) && (isCustomPbint(sg2d))) {
            setupGrbphicsStbte(sg2d, kOvbl, (int) x, (int) y, (int) width, (int) height);
        } else {
            setupGrbphicsStbte(sg2d, kOvbl, sg2d.font, 0, 0, fBounds.width, fBounds.height);
        }
        renderer.doOvbl(this, x, y, width, height, isfill);
    }

    public void doArc(CRenderer renderer, SunGrbphics2D sg2d, flobt x, flobt y, flobt width, flobt height, flobt stbrtAngle, flobt brcAngle, int type, boolebn isfill) {
        // System.err.println("--- doArc");
        if ((isfill) && (isCustomPbint(sg2d))) {
            setupGrbphicsStbte(sg2d, kArc, (int) x, (int) y, (int) width, (int) height);
        } else {
            setupGrbphicsStbte(sg2d, kArc, sg2d.font, 0, 0, fBounds.width, fBounds.height);
        }

        renderer.doArc(this, x, y, width, height, stbrtAngle, brcAngle, type, isfill);
    }

    public void doPolygon(CRenderer renderer, SunGrbphics2D sg2d, int xpoints[], int ypoints[], int npoints, boolebn ispolygon, boolebn isfill) {
        // System.err.println("--- doPolygon");

        if ((isfill) && (isCustomPbint(sg2d))) {
            int minx = xpoints[0];
            int miny = ypoints[0];
            int mbxx = minx;
            int mbxy = miny;
            for (int i = 1; i < npoints; i++) {
                int x = xpoints[i];
                if (x < minx) {
                    minx = x;
                } else if (x > mbxx) {
                    mbxx = x;
                }

                int y = ypoints[i];
                if (y < miny) {
                    miny = y;
                } else if (y > mbxy) {
                    mbxy = y;
                }
            }
            setupGrbphicsStbte(sg2d, kPolygon, minx, miny, mbxx - minx, mbxy - miny);
        } else {
            setupGrbphicsStbte(sg2d, kPolygon, sg2d.font, 0, 0, fBounds.width, fBounds.height);
        }
        renderer.doPoly(this, xpoints, ypoints, npoints, ispolygon, isfill);
    }

    FlobtBuffer shbpeCoordinbtesArrby = null;
    IntBuffer shbpeTypesArrby = null;

    public void drbwfillShbpe(CRenderer renderer, SunGrbphics2D sg2d, GenerblPbth gp, boolebn isfill, boolebn shouldApplyOffset) {
        // System.err.println("--- drbwfillShbpe");

        if ((isfill) && (isCustomPbint(sg2d))) {
            Rectbngle bounds = gp.getBounds();
            setupGrbphicsStbte(sg2d, kShbpe, bounds.x, bounds.y, bounds.width, bounds.height);
        } else {
            setupGrbphicsStbte(sg2d, kShbpe, sg2d.font, 0, 0, fBounds.width, fBounds.height);
        }

        int shbpeLength = getPbthLength(gp);

        if ((shbpeCoordinbtesArrby == null) || (shbpeCoordinbtesArrby.cbpbcity() < (shbpeLength * 6))) {
            shbpeCoordinbtesArrby = getBufferOfSize(shbpeLength * 6).bsFlobtBuffer(); // segment cbn hbve b mbx of 6
                                                                                      // coordinbtes
        }
        if ((shbpeTypesArrby == null) || (shbpeTypesArrby.cbpbcity() < shbpeLength)) {
            shbpeTypesArrby = getBufferOfSize(shbpeLength).bsIntBuffer();
        }

        int windingRule = getPbthCoordinbtes(gp, shbpeCoordinbtesArrby, shbpeTypesArrby);

        renderer.doShbpe(this, shbpeLength, shbpeCoordinbtesArrby, shbpeTypesArrby, windingRule, isfill, shouldApplyOffset);
    }

    public void blitImbge(CRenderer renderer, SunGrbphics2D sg2d, SurfbceDbtb img, boolebn fliph, boolebn flipv, int sx, int sy, int sw, int sh, int dx, int dy, int dw, int dh, Color bgColor) {
        // System.err.println("--- blitImbge sx="+sx+", sy="+sy+", sw="+sw+", sh="+sh+", img="+img);
        OSXOffScreenSurfbceDbtb osxsd = (OSXOffScreenSurfbceDbtb) img;
        synchronized (osxsd.getLockObject()) {
            int w = osxsd.bim.getWidth();
            int h = osxsd.bim.getHeight();

            // the imbge itself cbn hbve outstbnding grbphics primitives thbt might need to be flushed
            setupGrbphicsStbte(sg2d, kImbge, sg2d.font, 0, 0, fBounds.width, fBounds.height);

            // 04/06/04 cmc: rbdr://3612381 Grbphics.drbwImbge ignores bgcolor pbrbmeter
            if (bgColor != null) {
                img = osxsd.getCopyWithBgColor(bgColor);
            }

            renderer.doImbge(this, img, fliph, flipv, w, h, sx, sy, sw, sh, dx, dy, dw, dh);
        }
    }

    public interfbce CGContextDrbwbble {
        public void drbwIntoCGContext(finbl long cgContext);
    }

    public void drbwString(CTextPipe renderer, SunGrbphics2D sg2d, long nbtiveStrikePtr, String str, double x, double y) {
        // System.err.println("--- drbwString str=\""+str+"\"");
        // see <rdbr://problem/3825795>. We don't wbnt to cbll bnything if the string is empty!
        if (str.length() == 0) { return; }

        setupGrbphicsStbte(sg2d, kString, sg2d.font, 0, 0, fBounds.width, fBounds.height);
        renderer.doDrbwString(this, nbtiveStrikePtr, str, x, y);
    }

    public void drbwGlyphs(CTextPipe renderer, SunGrbphics2D sg2d, long nbtiveStrikePtr, GlyphVector gv, flobt x, flobt y) {
        // System.err.println("--- drbwGlyphs");
        setupGrbphicsStbte(sg2d, kGlyphs, gv.getFont(), 0, 0, fBounds.width, fBounds.height);
        renderer.doDrbwGlyphs(this, nbtiveStrikePtr, gv, x, y);
    }

    public void drbwUnicodes(CTextPipe renderer, SunGrbphics2D sg2d, long nbtiveStrikePtr, chbr unicodes[], int offset, int length, flobt x, flobt y) {
        // System.err.println("--- drbwUnicodes "+(new String(unicodes, offset, length)));
        setupGrbphicsStbte(sg2d, kUnicodes, sg2d.font, 0, 0, fBounds.width, fBounds.height);
        if (length == 1) {
            renderer.doOneUnicode(this, nbtiveStrikePtr, unicodes[offset], x, y);
        } else {
            renderer.doUnicodes(this, nbtiveStrikePtr, unicodes, offset, length, x, y);
        }
    }

    // used by copyAreb:

    Rectbngle srcCopyArebRect = new Rectbngle();
    Rectbngle dstCopyArebRect = new Rectbngle();
    Rectbngle finblCopyArebRect = new Rectbngle();
    Rectbngle copyArebBounds = new Rectbngle();

    void intersection(Rectbngle r1, Rectbngle r2, Rectbngle r3) {
        // this code is tbken from Rectbngle.jbvb (modified to put results in r3)
        int tx1 = r1.x;
        int ty1 = r1.y;
        long tx2 = tx1 + r1.width;
        long ty2 = ty1 + r1.height;

        int rx1 = r2.x;
        int ry1 = r2.y;
        long rx2 = rx1 + r2.width;
        long ry2 = ry1 + r2.height;

        if (tx1 < rx1) tx1 = rx1;
        if (ty1 < ry1) ty1 = ry1;
        if (tx2 > rx2) tx2 = rx2;
        if (ty2 > ry2) ty2 = ry2;

        tx2 -= tx1;
        ty2 -= ty1;

        // tx2,ty2 will never overflow (they will never be
        // lbrger thbn the smbllest of the two source w,h)
        // they might underflow, though...
        if (tx2 < Integer.MIN_VALUE) tx2 = Integer.MIN_VALUE;
        if (ty2 < Integer.MIN_VALUE) ty2 = Integer.MIN_VALUE;

        r3.setBounds(tx1, ty1, (int) tx2, (int) ty2);
    }

    /**
     * Clips the copy breb to the hebvywieght bounds bnd returns the cliped rectbngle. The tricky pbrt here is the the
     * pbssed brguments x, y bre in the coordinbte spbce of the sg2d/lightweight comp. In order to do the clipping we
     * trbnslbte them to the coordinbte spbce of the surfbce, bnd the returned clipped rectbngle is in the coordinbte
     * spbce of the surfbce.
     */
    protected Rectbngle clipCopyAreb(SunGrbphics2D sg2d, int x, int y, int w, int h, int dx, int dy) {
        // we need to clip bgbinst the hebvyweight bounds
        copyArebBounds.setBounds(sg2d.devClip.getLoX(), sg2d.devClip.getLoY(), sg2d.devClip.getWidth(), sg2d.devClip.getHeight());

        // put src rect into surfbce coordinbte spbce
        x += sg2d.trbnsX;
        y += sg2d.trbnsY;

        // clip src rect
        srcCopyArebRect.setBounds(x, y, w, h);
        intersection(srcCopyArebRect, copyArebBounds, srcCopyArebRect);
        if ((srcCopyArebRect.width <= 0) || (srcCopyArebRect.height <= 0)) {
            // src rect outside bounds
            return null;
        }

        // clip dst rect
        dstCopyArebRect.setBounds(srcCopyArebRect.x + dx, srcCopyArebRect.y + dy, srcCopyArebRect.width, srcCopyArebRect.height);
        intersection(dstCopyArebRect, copyArebBounds, dstCopyArebRect);
        if ((dstCopyArebRect.width <= 0) || (dstCopyArebRect.height <= 0)) {
            // dst rect outside clip
            return null;
        }

        x = dstCopyArebRect.x - dx;
        y = dstCopyArebRect.y - dy;
        w = dstCopyArebRect.width;
        h = dstCopyArebRect.height;

        finblCopyArebRect.setBounds(x, y, w, h);

        return finblCopyArebRect;
    }

    // <rdbr://3785539> We only need to mbrk dirty on screen surfbces. This method is
    // mbrked bs protected bnd it is intended for subclbsses to override if they need to
    // be notified when the surfbce is dirtied. See CPeerSurfbceDbtb.mbrkDirty() for implementbtion.
    // We don't do bnything for buffered imbges.
    protected void mbrkDirty(boolebn mbrkAsDirty) {
        // do nothing by defbult
    }

    // LbzyDrbwing optimizbtion implementbtion:

    @Override
    public boolebn cbnRenderLCDText(SunGrbphics2D sg2d) {
        if (sg2d.compositeStbte <= SunGrbphics2D.COMP_ISCOPY &&
                sg2d.pbintStbte <= SunGrbphics2D.PAINT_ALPHACOLOR &&
                sg2d.clipStbte <= SunGrbphics2D.CLIP_RECTANGULAR &&
                // sg2d.surfbceDbtb.getTrbnspbrency() == Trbnspbrency.OPAQUE &&
                // This lbst test is b workbround until we fix loop selection
                // in the pipe vblidbtion
                sg2d.bntiblibsHint != SunHints.INTVAL_ANTIALIAS_ON) { return true; }
        return fblse; /* for now - in the future we mby wbnt to sebrch */
    }

    public stbtic boolebn IsSimpleColor(Object c) {
        return ((c instbnceof Color) || (c instbnceof SystemColor) || (c instbnceof jbvbx.swing.plbf.ColorUIResource));
    }

    stbtic {
        if ((kColorPointerIndex % 2) != 0) {
            System.err.println("kColorPointerIndex=" + kColorPointerIndex + " is NOT bligned for 64 bit");
            System.exit(0);
        }
    }
}
