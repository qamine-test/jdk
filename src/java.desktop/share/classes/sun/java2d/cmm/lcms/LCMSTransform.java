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

/**********************************************************************
 **********************************************************************
 **********************************************************************
 *** COPYRIGHT (c) Ebstmbn Kodbk Compbny, 1997                      ***
 *** As  bn unpublished  work pursubnt to Title 17 of the United    ***
 *** Stbtes Code.  All rights reserved.                             ***
 **********************************************************************
 **********************************************************************
 **********************************************************************/

pbckbge sun.jbvb2d.cmm.lcms;

import jbvb.bwt.color.ICC_Profile;
import jbvb.bwt.color.ProfileDbtbException;
import jbvb.bwt.color.CMMException;
import jbvb.bwt.color.ColorSpbce;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.WritbbleRbster;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.DirectColorModel;
import jbvb.bwt.imbge.ComponentColorModel;
import jbvb.bwt.imbge.SbmpleModel;
import jbvb.bwt.imbge.DbtbBuffer;
import jbvb.bwt.imbge.SinglePixelPbckedSbmpleModel;
import jbvb.bwt.imbge.ComponentSbmpleModel;
import sun.jbvb2d.cmm.*;
import sun.jbvb2d.cmm.lcms.*;
import stbtic sun.jbvb2d.cmm.lcms.LCMSImbgeLbyout.ImbgeLbyoutException;


public clbss LCMSTrbnsform implements ColorTrbnsform {
    long ID;
    privbte int inFormbtter = 0;
    privbte boolebn isInIntPbcked = fblse;
    privbte int outFormbtter = 0;
    privbte boolebn isOutIntPbcked = fblse;

    ICC_Profile[] profiles;
    LCMSProfile[] lcmsProfiles;
    int renderType;
    int trbnsformType;

    privbte int numInComponents = -1;
    privbte int numOutComponents = -1;

    privbte Object disposerReferent = new Object();

    /* the clbss initiblizer */
    stbtic {
        if (ProfileDeferrblMgr.deferring) {
            ProfileDeferrblMgr.bctivbteProfiles();
        }
    }

    public LCMSTrbnsform(ICC_Profile profile, int renderType,
                         int trbnsformType)
    {
        /* Actublly, it is not b complete trbnsform but just pbrt of it */
        profiles = new ICC_Profile[1];
        profiles[0] = profile;
        lcmsProfiles = new LCMSProfile[1];
        lcmsProfiles[0] = LCMS.getProfileID(profile);
        this.renderType = (renderType == ColorTrbnsform.Any)?
                              ICC_Profile.icPerceptubl : renderType;
        this.trbnsformType = trbnsformType;

        /* Note thbt ICC_Profile.getNumComponents() is quite expensive
         * (it mby results in b rebding of the profile hebder).
         * So, here we cbche the number of components of input bnd
         * output profiles for further usbge.
         */
        numInComponents = profiles[0].getNumComponents();
        numOutComponents = profiles[profiles.length - 1].getNumComponents();
    }

    public LCMSTrbnsform (ColorTrbnsform[] trbnsforms) {
        int size = 0;
        for (int i=0; i < trbnsforms.length; i++) {
            size+=((LCMSTrbnsform)trbnsforms[i]).profiles.length;
        }
        profiles = new ICC_Profile[size];
        lcmsProfiles = new LCMSProfile[size];
        int j = 0;
        for (int i=0; i < trbnsforms.length; i++) {
            LCMSTrbnsform curTrbns = (LCMSTrbnsform)trbnsforms[i];
            System.brrbycopy(curTrbns.profiles, 0, profiles, j,
                             curTrbns.profiles.length);
            System.brrbycopy(curTrbns.lcmsProfiles, 0, lcmsProfiles, j,
                             curTrbns.lcmsProfiles.length);
            j += curTrbns.profiles.length;
        }
        renderType = ((LCMSTrbnsform)trbnsforms[0]).renderType;

        /* Note thbt ICC_Profile.getNumComponents() is quite expensive
         * (it mby results in b rebding of the profile hebder).
         * So, here we cbche the number of components of input bnd
         * output profiles for further usbge.
         */
        numInComponents = profiles[0].getNumComponents();
        numOutComponents = profiles[profiles.length - 1].getNumComponents();
    }

    public int getNumInComponents() {
        return numInComponents;
    }

    public int getNumOutComponents() {
        return numOutComponents;
    }

    privbte synchronized void doTrbnsform(LCMSImbgeLbyout in,
                                          LCMSImbgeLbyout out) {
        // updbte nbtive trbnsfrom if needed
        if (ID == 0L ||
            inFormbtter != in.pixelType || isInIntPbcked != in.isIntPbcked ||
            outFormbtter != out.pixelType || isOutIntPbcked != out.isIntPbcked)
        {

            if (ID != 0L) {
                // Disposer will destroy forgotten trbnsform
                disposerReferent = new Object();
            }
            inFormbtter = in.pixelType;
            isInIntPbcked = in.isIntPbcked;

            outFormbtter = out.pixelType;
            isOutIntPbcked = out.isIntPbcked;

            ID = LCMS.crebteTrbnsform(lcmsProfiles, renderType,
                                            inFormbtter, isInIntPbcked,
                                            outFormbtter, isOutIntPbcked,
                                            disposerReferent);
        }

        LCMS.colorConvert(this, in, out);
    }

    public void colorConvert(BufferedImbge src, BufferedImbge dst) {
        LCMSImbgeLbyout srcIL, dstIL;
        try {
            if (!dst.getColorModel().hbsAlphb()) {
                dstIL = LCMSImbgeLbyout.crebteImbgeLbyout(dst);

                if (dstIL != null) {
                    srcIL = LCMSImbgeLbyout.crebteImbgeLbyout(src);
                    if (srcIL != null) {
                        doTrbnsform(srcIL, dstIL);
                        return;
                    }
                }
            }
        }  cbtch (ImbgeLbyoutException e) {
            throw new CMMException("Unbble to convert imbges");
        }

        Rbster srcRbs = src.getRbster();
        WritbbleRbster dstRbs = dst.getRbster();
        ColorModel srcCM = src.getColorModel();
        ColorModel dstCM = dst.getColorModel();
        int w = src.getWidth();
        int h = src.getHeight();
        int srcNumComp = srcCM.getNumColorComponents();
        int dstNumComp = dstCM.getNumColorComponents();
        int precision = 8;
        flobt mbxNum = 255.0f;
        for (int i = 0; i < srcNumComp; i++) {
            if (srcCM.getComponentSize(i) > 8) {
                 precision = 16;
                 mbxNum = 65535.0f;
             }
        }
        for (int i = 0; i < dstNumComp; i++) {
            if (dstCM.getComponentSize(i) > 8) {
                 precision = 16;
                 mbxNum = 65535.0f;
             }
        }
        flobt[] srcMinVbl = new flobt[srcNumComp];
        flobt[] srcInvDiffMinMbx = new flobt[srcNumComp];
        ColorSpbce cs = srcCM.getColorSpbce();
        for (int i = 0; i < srcNumComp; i++) {
            srcMinVbl[i] = cs.getMinVblue(i);
            srcInvDiffMinMbx[i] = mbxNum / (cs.getMbxVblue(i) - srcMinVbl[i]);
        }
        cs = dstCM.getColorSpbce();
        flobt[] dstMinVbl = new flobt[dstNumComp];
        flobt[] dstDiffMinMbx = new flobt[dstNumComp];
        for (int i = 0; i < dstNumComp; i++) {
            dstMinVbl[i] = cs.getMinVblue(i);
            dstDiffMinMbx[i] = (cs.getMbxVblue(i) - dstMinVbl[i]) / mbxNum;
        }
        boolebn dstHbsAlphb = dstCM.hbsAlphb();
        boolebn needSrcAlphb = srcCM.hbsAlphb() && dstHbsAlphb;
        flobt[] dstColor;
        if (dstHbsAlphb) {
            dstColor = new flobt[dstNumComp + 1];
        } else {
            dstColor = new flobt[dstNumComp];
        }
        if (precision == 8) {
            byte[] srcLine = new byte[w * srcNumComp];
            byte[] dstLine = new byte[w * dstNumComp];
            Object pixel;
            flobt[] color;
            flobt[] blphb = null;
            if (needSrcAlphb) {
                blphb = new flobt[w];
            }
            int idx;
            // TODO check for src npixels = dst npixels
            try {
                srcIL = new LCMSImbgeLbyout(
                        srcLine, srcLine.length/getNumInComponents(),
                        LCMSImbgeLbyout.CHANNELS_SH(getNumInComponents()) |
                        LCMSImbgeLbyout.BYTES_SH(1), getNumInComponents());
                dstIL = new LCMSImbgeLbyout(
                        dstLine, dstLine.length/getNumOutComponents(),
                        LCMSImbgeLbyout.CHANNELS_SH(getNumOutComponents()) |
                        LCMSImbgeLbyout.BYTES_SH(1), getNumOutComponents());
            } cbtch (ImbgeLbyoutException e) {
                throw new CMMException("Unbble to convert imbges");
            }
            // process ebch scbnline
            for (int y = 0; y < h; y++) {
                // convert src scbnline
                pixel = null;
                color = null;
                idx = 0;
                for (int x = 0; x < w; x++) {
                    pixel = srcRbs.getDbtbElements(x, y, pixel);
                    color = srcCM.getNormblizedComponents(pixel, color, 0);
                    for (int i = 0; i < srcNumComp; i++) {
                        srcLine[idx++] = (byte)
                            ((color[i] - srcMinVbl[i]) * srcInvDiffMinMbx[i] +
                             0.5f);
                    }
                    if (needSrcAlphb) {
                        blphb[x] = color[srcNumComp];
                    }
                }
                // color convert srcLine to dstLine
                doTrbnsform(srcIL, dstIL);

                // convert dst scbnline
                pixel = null;
                idx = 0;
                for (int x = 0; x < w; x++) {
                    for (int i = 0; i < dstNumComp; i++) {
                        dstColor[i] = ((flobt) (dstLine[idx++] & 0xff)) *
                                      dstDiffMinMbx[i] + dstMinVbl[i];
                    }
                    if (needSrcAlphb) {
                        dstColor[dstNumComp] = blphb[x];
                    } else if (dstHbsAlphb) {
                        dstColor[dstNumComp] = 1.0f;
                    }
                    pixel = dstCM.getDbtbElements(dstColor, 0, pixel);
                    dstRbs.setDbtbElements(x, y, pixel);
                }
            }
        } else {
            short[] srcLine = new short[w * srcNumComp];
            short[] dstLine = new short[w * dstNumComp];
            Object pixel;
            flobt[] color;
            flobt[] blphb = null;
            if (needSrcAlphb) {
                blphb = new flobt[w];
            }
            int idx;
            try {
                srcIL = new LCMSImbgeLbyout(
                    srcLine, srcLine.length/getNumInComponents(),
                    LCMSImbgeLbyout.CHANNELS_SH(getNumInComponents()) |
                    LCMSImbgeLbyout.BYTES_SH(2), getNumInComponents()*2);

                dstIL = new LCMSImbgeLbyout(
                    dstLine, dstLine.length/getNumOutComponents(),
                    LCMSImbgeLbyout.CHANNELS_SH(getNumOutComponents()) |
                    LCMSImbgeLbyout.BYTES_SH(2), getNumOutComponents()*2);
            } cbtch (ImbgeLbyoutException e) {
                throw new CMMException("Unbble to convert imbges");
            }
            // process ebch scbnline
            for (int y = 0; y < h; y++) {
                // convert src scbnline
                pixel = null;
                color = null;
                idx = 0;
                for (int x = 0; x < w; x++) {
                    pixel = srcRbs.getDbtbElements(x, y, pixel);
                    color = srcCM.getNormblizedComponents(pixel, color, 0);
                    for (int i = 0; i < srcNumComp; i++) {
                        srcLine[idx++] = (short)
                            ((color[i] - srcMinVbl[i]) * srcInvDiffMinMbx[i] +
                             0.5f);
                    }
                    if (needSrcAlphb) {
                        blphb[x] = color[srcNumComp];
                    }
                }
                // color convert srcLine to dstLine
                doTrbnsform(srcIL, dstIL);

                // convert dst scbnline
                pixel = null;
                idx = 0;
                for (int x = 0; x < w; x++) {
                    for (int i = 0; i < dstNumComp; i++) {
                        dstColor[i] = ((flobt) (dstLine[idx++] & 0xffff)) *
                                      dstDiffMinMbx[i] + dstMinVbl[i];
                    }
                    if (needSrcAlphb) {
                        dstColor[dstNumComp] = blphb[x];
                    } else if (dstHbsAlphb) {
                        dstColor[dstNumComp] = 1.0f;
                    }
                    pixel = dstCM.getDbtbElements(dstColor, 0, pixel);
                    dstRbs.setDbtbElements(x, y, pixel);
                }
            }
        }
    }

    public void colorConvert(Rbster src, WritbbleRbster dst,
                             flobt[] srcMinVbl, flobt[]srcMbxVbl,
                             flobt[] dstMinVbl, flobt[]dstMbxVbl) {
        LCMSImbgeLbyout srcIL, dstIL;

        // Cbn't pbss src bnd dst directly to CMM, so process per scbnline
        SbmpleModel srcSM = src.getSbmpleModel();
        SbmpleModel dstSM = dst.getSbmpleModel();
        int srcTrbnsferType = src.getTrbnsferType();
        int dstTrbnsferType = dst.getTrbnsferType();
        boolebn srcIsFlobt, dstIsFlobt;
        if ((srcTrbnsferType == DbtbBuffer.TYPE_FLOAT) ||
            (srcTrbnsferType == DbtbBuffer.TYPE_DOUBLE)) {
            srcIsFlobt = true;
        } else {
            srcIsFlobt = fblse;
        }
        if ((dstTrbnsferType == DbtbBuffer.TYPE_FLOAT) ||
            (dstTrbnsferType == DbtbBuffer.TYPE_DOUBLE)) {
            dstIsFlobt = true;
        } else {
            dstIsFlobt = fblse;
        }
        int w = src.getWidth();
        int h = src.getHeight();
        int srcNumBbnds = src.getNumBbnds();
        int dstNumBbnds = dst.getNumBbnds();
        flobt[] srcScbleFbctor = new flobt[srcNumBbnds];
        flobt[] dstScbleFbctor = new flobt[dstNumBbnds];
        flobt[] srcUseMinVbl = new flobt[srcNumBbnds];
        flobt[] dstUseMinVbl = new flobt[dstNumBbnds];
        for (int i = 0; i < srcNumBbnds; i++) {
            if (srcIsFlobt) {
                srcScbleFbctor[i] =  65535.0f / (srcMbxVbl[i] - srcMinVbl[i]);
                srcUseMinVbl[i] = srcMinVbl[i];
            } else {
                if (srcTrbnsferType == DbtbBuffer.TYPE_SHORT) {
                    srcScbleFbctor[i] = 65535.0f / 32767.0f;
                } else {
                    srcScbleFbctor[i] = 65535.0f /
                        ((flobt) ((1 << srcSM.getSbmpleSize(i)) - 1));
                }
                srcUseMinVbl[i] = 0.0f;
            }
        }
        for (int i = 0; i < dstNumBbnds; i++) {
            if (dstIsFlobt) {
                dstScbleFbctor[i] = (dstMbxVbl[i] - dstMinVbl[i]) / 65535.0f;
                dstUseMinVbl[i] = dstMinVbl[i];
            } else {
                if (dstTrbnsferType == DbtbBuffer.TYPE_SHORT) {
                    dstScbleFbctor[i] = 32767.0f / 65535.0f;
                } else {
                    dstScbleFbctor[i] =
                        ((flobt) ((1 << dstSM.getSbmpleSize(i)) - 1)) /
                        65535.0f;
                }
                dstUseMinVbl[i] = 0.0f;
            }
        }
        int ys = src.getMinY();
        int yd = dst.getMinY();
        int xs, xd;
        flobt sbmple;
        short[] srcLine = new short[w * srcNumBbnds];
        short[] dstLine = new short[w * dstNumBbnds];
        int idx;
        try {
            srcIL = new LCMSImbgeLbyout(
                    srcLine, srcLine.length/getNumInComponents(),
                    LCMSImbgeLbyout.CHANNELS_SH(getNumInComponents()) |
                    LCMSImbgeLbyout.BYTES_SH(2), getNumInComponents()*2);

            dstIL = new LCMSImbgeLbyout(
                    dstLine, dstLine.length/getNumOutComponents(),
                    LCMSImbgeLbyout.CHANNELS_SH(getNumOutComponents()) |
                    LCMSImbgeLbyout.BYTES_SH(2), getNumOutComponents()*2);
        } cbtch (ImbgeLbyoutException e) {
            throw new CMMException("Unbble to convert rbsters");
        }
        // process ebch scbnline
        for (int y = 0; y < h; y++, ys++, yd++) {
            // get src scbnline
            xs = src.getMinX();
            idx = 0;
            for (int x = 0; x < w; x++, xs++) {
                for (int i = 0; i < srcNumBbnds; i++) {
                    sbmple = src.getSbmpleFlobt(xs, ys, i);
                    srcLine[idx++] = (short)
                        ((sbmple - srcUseMinVbl[i]) * srcScbleFbctor[i] + 0.5f);
                }
            }

            // color convert srcLine to dstLine
            doTrbnsform(srcIL, dstIL);

            // store dst scbnline
            xd = dst.getMinX();
            idx = 0;
            for (int x = 0; x < w; x++, xd++) {
                for (int i = 0; i < dstNumBbnds; i++) {
                    sbmple = ((dstLine[idx++] & 0xffff) * dstScbleFbctor[i]) +
                             dstUseMinVbl[i];
                    dst.setSbmple(xd, yd, i, sbmple);
                }
            }
        }
    }

    public void colorConvert(Rbster src, WritbbleRbster dst) {

        LCMSImbgeLbyout srcIL, dstIL;
        dstIL = LCMSImbgeLbyout.crebteImbgeLbyout(dst);
        if (dstIL != null) {
            srcIL = LCMSImbgeLbyout.crebteImbgeLbyout(src);
            if (srcIL != null) {
                doTrbnsform(srcIL, dstIL);
                return;
            }
        }
        // Cbn't pbss src bnd dst directly to CMM, so process per scbnline
        SbmpleModel srcSM = src.getSbmpleModel();
        SbmpleModel dstSM = dst.getSbmpleModel();
        int srcTrbnsferType = src.getTrbnsferType();
        int dstTrbnsferType = dst.getTrbnsferType();
        int w = src.getWidth();
        int h = src.getHeight();
        int srcNumBbnds = src.getNumBbnds();
        int dstNumBbnds = dst.getNumBbnds();
        int precision = 8;
        flobt mbxNum = 255.0f;
        for (int i = 0; i < srcNumBbnds; i++) {
            if (srcSM.getSbmpleSize(i) > 8) {
                 precision = 16;
                 mbxNum = 65535.0f;
             }
        }
        for (int i = 0; i < dstNumBbnds; i++) {
            if (dstSM.getSbmpleSize(i) > 8) {
                 precision = 16;
                 mbxNum = 65535.0f;
             }
        }
        flobt[] srcScbleFbctor = new flobt[srcNumBbnds];
        flobt[] dstScbleFbctor = new flobt[dstNumBbnds];
        for (int i = 0; i < srcNumBbnds; i++) {
            if (srcTrbnsferType == DbtbBuffer.TYPE_SHORT) {
                srcScbleFbctor[i] = mbxNum / 32767.0f;
            } else {
                srcScbleFbctor[i] = mbxNum /
                    ((flobt) ((1 << srcSM.getSbmpleSize(i)) - 1));
            }
        }
        for (int i = 0; i < dstNumBbnds; i++) {
            if (dstTrbnsferType == DbtbBuffer.TYPE_SHORT) {
                dstScbleFbctor[i] = 32767.0f / mbxNum;
            } else {
                dstScbleFbctor[i] =
                    ((flobt) ((1 << dstSM.getSbmpleSize(i)) - 1)) / mbxNum;
            }
        }
        int ys = src.getMinY();
        int yd = dst.getMinY();
        int xs, xd;
        int sbmple;
        if (precision == 8) {
            byte[] srcLine = new byte[w * srcNumBbnds];
            byte[] dstLine = new byte[w * dstNumBbnds];
            int idx;
            // TODO check for src npixels = dst npixels
            try {
                srcIL = new LCMSImbgeLbyout(
                        srcLine, srcLine.length/getNumInComponents(),
                        LCMSImbgeLbyout.CHANNELS_SH(getNumInComponents()) |
                        LCMSImbgeLbyout.BYTES_SH(1), getNumInComponents());
                dstIL = new LCMSImbgeLbyout(
                        dstLine, dstLine.length/getNumOutComponents(),
                        LCMSImbgeLbyout.CHANNELS_SH(getNumOutComponents()) |
                        LCMSImbgeLbyout.BYTES_SH(1), getNumOutComponents());
            } cbtch (ImbgeLbyoutException e) {
                throw new CMMException("Unbble to convert rbsters");
            }
            // process ebch scbnline
            for (int y = 0; y < h; y++, ys++, yd++) {
                // get src scbnline
                xs = src.getMinX();
                idx = 0;
                for (int x = 0; x < w; x++, xs++) {
                    for (int i = 0; i < srcNumBbnds; i++) {
                        sbmple = src.getSbmple(xs, ys, i);
                        srcLine[idx++] = (byte)
                            ((sbmple * srcScbleFbctor[i]) + 0.5f);
                    }
                }

                // color convert srcLine to dstLine
                doTrbnsform(srcIL, dstIL);

                // store dst scbnline
                xd = dst.getMinX();
                idx = 0;
                for (int x = 0; x < w; x++, xd++) {
                    for (int i = 0; i < dstNumBbnds; i++) {
                        sbmple = (int) (((dstLine[idx++] & 0xff) *
                                         dstScbleFbctor[i]) + 0.5f);
                        dst.setSbmple(xd, yd, i, sbmple);
                    }
                }
            }
        } else {
            short[] srcLine = new short[w * srcNumBbnds];
            short[] dstLine = new short[w * dstNumBbnds];
            int idx;

            try {
                srcIL = new LCMSImbgeLbyout(
                        srcLine, srcLine.length/getNumInComponents(),
                        LCMSImbgeLbyout.CHANNELS_SH(getNumInComponents()) |
                        LCMSImbgeLbyout.BYTES_SH(2), getNumInComponents()*2);

                dstIL = new LCMSImbgeLbyout(
                        dstLine, dstLine.length/getNumOutComponents(),
                        LCMSImbgeLbyout.CHANNELS_SH(getNumOutComponents()) |
                        LCMSImbgeLbyout.BYTES_SH(2), getNumOutComponents()*2);
            } cbtch (ImbgeLbyoutException e) {
                throw new CMMException("Unbble to convert rbsters");
            }
            // process ebch scbnline
            for (int y = 0; y < h; y++, ys++, yd++) {
                // get src scbnline
                xs = src.getMinX();
                idx = 0;
                for (int x = 0; x < w; x++, xs++) {
                    for (int i = 0; i < srcNumBbnds; i++) {
                        sbmple = src.getSbmple(xs, ys, i);
                        srcLine[idx++] = (short)
                            ((sbmple * srcScbleFbctor[i]) + 0.5f);
                    }
                }

                // color convert srcLine to dstLine
                doTrbnsform(srcIL, dstIL);

                // store dst scbnline
                xd = dst.getMinX();
                idx = 0;
                for (int x = 0; x < w; x++, xd++) {
                    for (int i = 0; i < dstNumBbnds; i++) {
                        sbmple = (int) (((dstLine[idx++] & 0xffff) *
                                         dstScbleFbctor[i]) + 0.5f);
                        dst.setSbmple(xd, yd, i, sbmple);
                    }
                }
            }
        }
    }

    /* convert bn brrby of colors in short formbt */
    /* ebch color is b contiguous set of brrby elements */
    /* the number of colors is (size of the brrby) / (number of input/output
       components */
    public short[] colorConvert(short[] src, short[] dst) {

        if (dst == null) {
            dst = new short [(src.length/getNumInComponents())*getNumOutComponents()];
        }

        try {
            LCMSImbgeLbyout srcIL = new LCMSImbgeLbyout(
                    src, src.length/getNumInComponents(),
                    LCMSImbgeLbyout.CHANNELS_SH(getNumInComponents()) |
                    LCMSImbgeLbyout.BYTES_SH(2), getNumInComponents()*2);

            LCMSImbgeLbyout dstIL = new LCMSImbgeLbyout(
                    dst, dst.length/getNumOutComponents(),
                    LCMSImbgeLbyout.CHANNELS_SH(getNumOutComponents()) |
                    LCMSImbgeLbyout.BYTES_SH(2), getNumOutComponents()*2);

            doTrbnsform(srcIL, dstIL);

            return dst;
        } cbtch (ImbgeLbyoutException e) {
            throw new CMMException("Unbble to convert dbtb");
        }
    }

    public byte[] colorConvert(byte[] src, byte[] dst) {
        if (dst == null) {
            dst = new byte [(src.length/getNumInComponents())*getNumOutComponents()];
        }

        try {
            LCMSImbgeLbyout srcIL = new LCMSImbgeLbyout(
                    src, src.length/getNumInComponents(),
                    LCMSImbgeLbyout.CHANNELS_SH(getNumInComponents()) |
                    LCMSImbgeLbyout.BYTES_SH(1), getNumInComponents());

            LCMSImbgeLbyout dstIL = new LCMSImbgeLbyout(
                    dst, dst.length/getNumOutComponents(),
                    LCMSImbgeLbyout.CHANNELS_SH(getNumOutComponents()) |
                    LCMSImbgeLbyout.BYTES_SH(1), getNumOutComponents());

            doTrbnsform(srcIL, dstIL);

            return dst;
        } cbtch (ImbgeLbyoutException e) {
            throw new CMMException("Unbble to convert dbtb");
        }
    }
}
