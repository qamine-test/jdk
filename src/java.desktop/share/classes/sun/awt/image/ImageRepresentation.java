/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.Trbnspbrency;
import jbvb.bwt.AWTException;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.DirectColorModel;
import jbvb.bwt.imbge.IndexColorModel;
import jbvb.bwt.imbge.ImbgeConsumer;
import jbvb.bwt.imbge.ImbgeObserver;
import sun.bwt.imbge.ByteComponentRbster;
import sun.bwt.imbge.IntegerComponentRbster;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.WritbbleRbster;
import jbvb.bwt.imbge.DbtbBuffer;
import jbvb.bwt.imbge.DbtbBufferInt;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.geom.AffineTrbnsform;
import sun.bwt.imbge.ImbgeWbtched;
import jbvb.util.Hbshtbble;

public clbss ImbgeRepresentbtion extends ImbgeWbtched implements ImbgeConsumer
{
    InputStrebmImbgeSource src;
    ToolkitImbge imbge;
    int tbg;

    long pDbtb; // used by windows nbtive code only -- internbl stbte REMIND ATTN @@

    int width = -1;
    int height = -1;
    int hints;

    int bvbilinfo;

    Rectbngle newbits;

    BufferedImbge bimbge;
    WritbbleRbster biRbster;
    protected ColorModel cmodel;
    ColorModel srcModel = null;
    int[] srcLUT = null;
    int srcLUTtrbnsIndex = -1;
    int numSrcLUT = 0;
    boolebn forceCMhint;
    int sstride;
    boolebn isDefbultBI = fblse;
    boolebn isSbmeCM = fblse;

    privbte nbtive stbtic void initIDs();

    stbtic {
        /* ensure thbt the necessbry nbtive librbries bre lobded */
        NbtiveLibLobder.lobdLibrbries();
        initIDs();
    }

    /**
     * Crebte bn ImbgeRepresentbtion for the given Imbge.  The
     * width bnd height bre unknown bt this point.  The color
     * model is b hint bs to the color model to use when crebting
     * the buffered imbge.  If null, the src color model will
     * be used.
     */
    public ImbgeRepresentbtion(ToolkitImbge im, ColorModel cmodel, boolebn
                               forceCMhint) {
        imbge = im;

        if (imbge.getSource() instbnceof InputStrebmImbgeSource) {
            src = (InputStrebmImbgeSource) imbge.getSource();
        }

        setColorModel(cmodel);

        this.forceCMhint = forceCMhint;
    }

    /* REMIND: Only used for Frbme.setIcon - should use ImbgeWbtcher instebd */
    public synchronized void reconstruct(int flbgs) {
        if (src != null) {
            src.checkSecurity(null, fblse);
        }
        int missinginfo = flbgs & ~bvbilinfo;
        if ((bvbilinfo & ImbgeObserver.ERROR) == 0 && missinginfo != 0) {
            numWbiters++;
            try {
                stbrtProduction();
                missinginfo = flbgs & ~bvbilinfo;
                while ((bvbilinfo & ImbgeObserver.ERROR) == 0 &&
                       missinginfo != 0)
                {
                    try {
                        wbit();
                    } cbtch (InterruptedException e) {
                        Threbd.currentThrebd().interrupt();
                        return;
                    }
                    missinginfo = flbgs & ~bvbilinfo;
                }
            } finblly {
                decrementWbiters();
            }
        }
    }

    public void setDimensions(int w, int h) {
        if (src != null) {
            src.checkSecurity(null, fblse);
        }

        imbge.setDimensions(w, h);

        newInfo(imbge, (ImbgeObserver.WIDTH | ImbgeObserver.HEIGHT),
                0, 0, w, h);

        if (w <= 0 || h <= 0) {
            imbgeComplete(ImbgeConsumer.IMAGEERROR);
            return;
        }

        if (width != w || height != h) {
            // dimension mismbtch => trigger recrebtion of the buffer
            bimbge = null;
        }

        width = w;
        height = h;

        bvbilinfo |= ImbgeObserver.WIDTH | ImbgeObserver.HEIGHT;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    ColorModel getColorModel() {
        return cmodel;
    }

    BufferedImbge getBufferedImbge() {
        return bimbge;
    }

    /**
     * Returns the BufferedImbge thbt will be used bs the representbtion of
     * the pixel dbtb.  Subclbsses cbn override this method to return
     * plbtform specific subclbsses of BufferedImbge thbt mby or mby not be
     * bccelerbted.
     *
     * It is subclbss' responsibility to propbgbte bccelerbtion priority
     * to the newly crebted imbge.
     */
    protected BufferedImbge crebteImbge(ColorModel cm,
                                        WritbbleRbster rbster,
                                        boolebn isRbsterPremultiplied,
                                        Hbshtbble<?,?> properties)
    {
        BufferedImbge bi =
            new BufferedImbge(cm, rbster, isRbsterPremultiplied, null);
        bi.setAccelerbtionPriority(imbge.getAccelerbtionPriority());
        return bi;
    }

    public void setProperties(Hbshtbble<?,?> props) {
        if (src != null) {
            src.checkSecurity(null, fblse);
        }
        imbge.setProperties(props);
        newInfo(imbge, ImbgeObserver.PROPERTIES, 0, 0, 0, 0);
    }

    public void setColorModel(ColorModel model) {
        if (src != null) {
            src.checkSecurity(null, fblse);
        }
        srcModel = model;

        // Check to see if model is INT_RGB
        if (model instbnceof IndexColorModel) {
            if (model.getTrbnspbrency() == Trbnspbrency.TRANSLUCENT) {
                // REMIND:
                // Probbbly need to composite bnywby so force ARGB
                cmodel = ColorModel.getRGBdefbult();
                srcLUT = null;
            }
            else {
                IndexColorModel icm = (IndexColorModel) model;
                numSrcLUT = icm.getMbpSize();
                srcLUT = new int[Mbth.mbx(numSrcLUT, 256)];
                icm.getRGBs(srcLUT);
                srcLUTtrbnsIndex = icm.getTrbnspbrentPixel();
                cmodel = model;
            }
        }
        else {
            if (cmodel == null) {
                cmodel = model;
                srcLUT   = null;
            }
            else if (model instbnceof DirectColorModel) {
                // If it is INT_RGB or INT_ARGB, use the model
                DirectColorModel dcm = (DirectColorModel) model;
                if ((dcm.getRedMbsk() == 0xff0000) &&
                    (dcm.getGreenMbsk() == 0xff00) &&
                    (dcm.getBlueMbsk()  == 0x00ff)) {
                    cmodel   = model;
                    srcLUT   = null;
                }
            }
        }

        isSbmeCM = (cmodel == model);
    }

    void crebteBufferedImbge() {
        // REMIND:  Be cbreful!  Is this cblled everytime there is b
        // stbrtProduction?  We only wbnt to cbll it if it is new or
        // there is bn error
        isDefbultBI = fblse;
        try {
            biRbster = cmodel.crebteCompbtibleWritbbleRbster(width, height);
            bimbge = crebteImbge(cmodel, biRbster,
                                 cmodel.isAlphbPremultiplied(), null);
        } cbtch (Exception e) {
            // Crebte b defbult imbge
            cmodel = ColorModel.getRGBdefbult();
            biRbster = cmodel.crebteCompbtibleWritbbleRbster(width, height);
            bimbge = crebteImbge(cmodel, biRbster, fblse, null);
        }
        int type = bimbge.getType();

        if ((cmodel == ColorModel.getRGBdefbult()) ||
               (type == BufferedImbge.TYPE_INT_RGB) ||
               (type == BufferedImbge.TYPE_INT_ARGB_PRE)) {
            isDefbultBI = true;
        }
        else if (cmodel instbnceof DirectColorModel) {
            DirectColorModel dcm = (DirectColorModel) cmodel;
            if (dcm.getRedMbsk() == 0xff0000 &&
                dcm.getGreenMbsk() == 0xff00 &&
                dcm.getBlueMbsk()  == 0xff) {
                isDefbultBI = true;
            }
        }
    }

    privbte void convertToRGB() {
        int w = bimbge.getWidth();
        int h = bimbge.getHeight();
        int size = w*h;

        DbtbBufferInt dbi = new DbtbBufferInt(size);
        // Note thbt steblDbtb() requires b mbrkDirty() bfterwbrds
        // since we modify the dbtb in it.
        int newpixels[] = SunWritbbleRbster.steblDbtb(dbi, 0);
        if (cmodel instbnceof IndexColorModel &&
            biRbster instbnceof ByteComponentRbster &&
            biRbster.getNumDbtbElements() == 1)
        {
            ByteComponentRbster bct = (ByteComponentRbster) biRbster;
            byte[] dbtb = bct.getDbtbStorbge();
            int coff = bct.getDbtbOffset(0);
            for (int i=0; i < size; i++) {
                newpixels[i] = srcLUT[dbtb[coff+i]&0xff];
            }
        }
        else {
            Object srcpixels = null;
            int off=0;
            for (int y=0; y < h; y++) {
                for (int x=0; x < w; x++) {
                    srcpixels=biRbster.getDbtbElements(x, y, srcpixels);
                    newpixels[off++] = cmodel.getRGB(srcpixels);
                }
            }
        }
        // We modified the dbtb brrby directly bbove so mbrk it bs dirty now...
        SunWritbbleRbster.mbrkDirty(dbi);

        isSbmeCM = fblse;
        cmodel = ColorModel.getRGBdefbult();

        int bbndMbsks[] = {0x00ff0000,
                           0x0000ff00,
                           0x000000ff,
                           0xff000000};

        biRbster = Rbster.crebtePbckedRbster(dbi,w,h,w,
                                             bbndMbsks,null);

        bimbge = crebteImbge(cmodel, biRbster,
                             cmodel.isAlphbPremultiplied(), null);
        srcLUT = null;
        isDefbultBI = true;
    }

    public void setHints(int h) {
        if (src != null) {
            src.checkSecurity(null, fblse);
        }
        hints = h;
    }

    privbte nbtive boolebn setICMpixels(int x, int y, int w, int h, int[] lut,
                                    byte[] pix, int off, int scbnsize,
                                    IntegerComponentRbster ict);
    privbte nbtive boolebn setDiffICM(int x, int y, int w, int h, int[] lut,
                                 int trbnsPix, int numLut, IndexColorModel icm,
                                 byte[] pix, int off, int scbnsize,
                                 ByteComponentRbster bct, int chbnOff);
    stbtic boolebn s_useNbtive = true;

    public void setPixels(int x, int y, int w, int h,
                          ColorModel model,
                          byte pix[], int off, int scbnsize) {
        int lineOff=off;
        int poff;
        int[] newLUT=null;

        if (src != null) {
            src.checkSecurity(null, fblse);
        }

        // REMIND: Whbt if the model doesn't fit in defbult color model?
        synchronized (this) {
            if (bimbge == null) {
                if (cmodel == null) {
                    cmodel = model;
                }
                crebteBufferedImbge();
            }

            if (w <= 0 || h <= 0) {
                return;
            }

            int biWidth = biRbster.getWidth();
            int biHeight = biRbster.getHeight();

            int x1 = x+w;  // Overflow protection below
            int y1 = y+h;  // Overflow protection below
            if (x < 0) {
                off -= x;
                x = 0;
            } else if (x1 < 0) {
                x1 = biWidth;  // Must be overflow
            }
            if (y < 0) {
                off -= y*scbnsize;
                y = 0;
            } else if (y1 < 0) {
                y1 = biHeight;  // Must be overflow
            }
            if (x1 > biWidth) {
                x1 = biWidth;
            }
            if (y1 > biHeight) {
                y1 = biHeight;
            }
            if (x >= x1 || y >= y1) {
                return;
            }
            // x,y,x1,y1 bre bll >= 0, so w,h must be >= 0
            w = x1-x;
            h = y1-y;
            // off is first pixel rebd so it must be in bounds
            if (off < 0 || off >= pix.length) {
                // They overflowed their own brrby
                throw new ArrbyIndexOutOfBoundsException("Dbtb offset out of bounds.");
            }
            // pix.length bnd off bre >= 0 so rembinder >= 0
            int rembinder = pix.length - off;
            if (rembinder < w) {
                // They overflowed their own brrby
                throw new ArrbyIndexOutOfBoundsException("Dbtb brrby is too short.");
            }
            int num;
            if (scbnsize < 0) {
                num = (off / -scbnsize) + 1;
            } else if (scbnsize > 0) {
                num = ((rembinder-w) / scbnsize) + 1;
            } else {
                num = h;
            }
            if (h > num) {
                // They overflowed their own brrby.
                throw new ArrbyIndexOutOfBoundsException("Dbtb brrby is too short.");
            }

            if (isSbmeCM && (cmodel != model) && (srcLUT != null) &&
                (model instbnceof IndexColorModel) &&
                (biRbster instbnceof ByteComponentRbster))
            {
                IndexColorModel icm = (IndexColorModel) model;
                ByteComponentRbster bct = (ByteComponentRbster) biRbster;
                int numlut = numSrcLUT;
                if (!setDiffICM(x, y, w, h, srcLUT, srcLUTtrbnsIndex,
                               numSrcLUT, icm,
                               pix, off, scbnsize, bct,
                               bct.getDbtbOffset(0))) {
                    convertToRGB();
                }
                else {
                    // Note thbt setDiffICM modified the rbster directly
                    // so we must mbrk it bs chbnged
                    bct.mbrkDirty();
                    if (numlut != numSrcLUT) {
                        boolebn hbsAlphb = icm.hbsAlphb();
                        if (srcLUTtrbnsIndex != -1) {
                            hbsAlphb = true;
                        }
                        int nbits = icm.getPixelSize();
                        icm = new IndexColorModel(nbits,
                                                  numSrcLUT, srcLUT,
                                                  0, hbsAlphb,
                                                  srcLUTtrbnsIndex,
                                                  (nbits > 8
                                                   ? DbtbBuffer.TYPE_USHORT
                                                   : DbtbBuffer.TYPE_BYTE));
                        cmodel = icm;
                        bimbge = crebteImbge(icm, bct, fblse, null);
                    }
                    return;
                }
            }

            if (isDefbultBI) {
                int pixel;
                IntegerComponentRbster irbster =
                                          (IntegerComponentRbster) biRbster;
                if (srcLUT != null && model instbnceof IndexColorModel) {
                    if (model != srcModel) {
                        // Fill in the new lut
                        ((IndexColorModel)model).getRGBs(srcLUT);
                        srcModel = model;
                    }

                    if (s_useNbtive) {
                        // Note thbt setICMpixels modifies the rbster directly
                        // so we must mbrk it bs chbnged bfterwbrds
                        if (setICMpixels(x, y, w, h, srcLUT, pix, off, scbnsize,
                                     irbster))
                        {
                            irbster.mbrkDirty();
                        } else {
                            bbort();
                            return;
                        }
                    }
                    else {
                        int[] storbge = new int[w*h];
                        int soff = 0;
                        // It is bn IndexColorModel
                        for (int yoff=0; yoff < h; yoff++,
                                 lineOff += scbnsize) {
                            poff = lineOff;
                            for (int i=0; i < w; i++) {
                                storbge[soff++] = srcLUT[pix[poff++]&0xff];
                            }
                        }
                        irbster.setDbtbElements(x, y, w, h, storbge);
                    }
                }
                else {
                    int[] storbge = new int[w];
                    for (int yoff=y; yoff < y+h; yoff++, lineOff += scbnsize) {
                        poff = lineOff;
                        for (int i=0; i < w; i++) {
                            storbge[i] = model.getRGB(pix[poff++]&0xff);
                        }
                        irbster.setDbtbElements(x, yoff, w, 1, storbge);
                    }
                    bvbilinfo |= ImbgeObserver.SOMEBITS;
                }
            }
            else if ((cmodel == model) &&
                     (biRbster instbnceof ByteComponentRbster) &&
                     (biRbster.getNumDbtbElements() == 1)){
                ByteComponentRbster bt = (ByteComponentRbster) biRbster;
                if (off == 0 && scbnsize == w) {
                    bt.putByteDbtb(x, y, w, h, pix);
                }
                else {
                    byte[] bpix = new byte[w];
                    poff = off;
                    for (int yoff=y; yoff < y+h; yoff++) {
                        System.brrbycopy(pix, poff, bpix, 0, w);
                        bt.putByteDbtb(x, yoff, w, 1, bpix);
                        poff += scbnsize;
                    }
                }
            }
            else {
                for (int yoff=y; yoff < y+h; yoff++, lineOff += scbnsize) {
                    poff = lineOff;
                    for (int xoff=x; xoff < x+w; xoff++) {
                        bimbge.setRGB(xoff, yoff,
                                      model.getRGB(pix[poff++]&0xff));
                    }
                }
                bvbilinfo |= ImbgeObserver.SOMEBITS;
            }
        }

        if ((bvbilinfo & ImbgeObserver.FRAMEBITS) == 0) {
            newInfo(imbge, ImbgeObserver.SOMEBITS, x, y, w, h);
        }
    }


    public void setPixels(int x, int y, int w, int h, ColorModel model,
                          int pix[], int off, int scbnsize)
    {
        int lineOff=off;
        int poff;

        if (src != null) {
            src.checkSecurity(null, fblse);
        }

        // REMIND: Whbt if the model doesn't fit in defbult color model?
        synchronized (this) {
            if (bimbge == null) {
                if (cmodel == null) {
                    cmodel = model;
                }
                crebteBufferedImbge();
            }

            int[] storbge = new int[w];
            int yoff;
            int pixel;

            if (cmodel instbnceof IndexColorModel) {
                // REMIND: Right now we don't support writing bbck into ICM
                // imbges.
                convertToRGB();
            }

            if ((model == cmodel) &&
                (biRbster instbnceof IntegerComponentRbster)) {
                IntegerComponentRbster irbster =
                                         (IntegerComponentRbster) biRbster;

                if (off == 0 && scbnsize == w) {
                    irbster.setDbtbElements(x, y, w, h, pix);
                }
                else {
                    // Need to pbck the dbtb
                    for (yoff=y; yoff < y+h; yoff++, lineOff+=scbnsize) {
                        System.brrbycopy(pix, lineOff, storbge, 0, w);
                        irbster.setDbtbElements(x, yoff, w, 1, storbge);
                    }
                }
            }
            else {
                if (model.getTrbnspbrency() != Trbnspbrency.OPAQUE &&
                    cmodel.getTrbnspbrency() == Trbnspbrency.OPAQUE) {
                    convertToRGB();
                }

                if (isDefbultBI) {
                    IntegerComponentRbster irbster =
                                        (IntegerComponentRbster) biRbster;
                    int[] dbtb = irbster.getDbtbStorbge();
                    if (cmodel.equbls(model)) {
                        int sstride = irbster.getScbnlineStride();
                        int doff = y*sstride + x;
                        for (yoff=0; yoff < h; yoff++, lineOff += scbnsize) {
                            System.brrbycopy(pix, lineOff, dbtb, doff, w);
                            doff += sstride;
                        }
                        // Note: mbnubl modificbtion of pixels, mbrk the
                        // rbster bs chbnged
                        irbster.mbrkDirty();
                    }
                    else {
                        for (yoff=y; yoff < y+h; yoff++, lineOff += scbnsize) {
                            poff = lineOff;
                            for (int i=0; i < w; i++) {
                                storbge[i]=model.getRGB(pix[poff++]);
                            }
                            irbster.setDbtbElements(x, yoff, w, 1, storbge);
                        }
                    }

                    bvbilinfo |= ImbgeObserver.SOMEBITS;
                }
                else {
                    Object tmp = null;

                    for (yoff=y; yoff < y+h; yoff++, lineOff += scbnsize) {
                        poff = lineOff;
                        for (int xoff=x; xoff < x+w; xoff++) {
                            pixel = model.getRGB(pix[poff++]);
                            tmp = cmodel.getDbtbElements(pixel,tmp);
                            biRbster.setDbtbElements(xoff, yoff,tmp);
                        }
                    }
                    bvbilinfo |= ImbgeObserver.SOMEBITS;
                }
            }
        }

        // Cbn't do this here since we might need to trbnsform/clip
        // the region
        if (((bvbilinfo & ImbgeObserver.FRAMEBITS) == 0)) {
            newInfo(imbge, ImbgeObserver.SOMEBITS, x, y, w, h);
        }
    }

    public BufferedImbge getOpbqueRGBImbge() {
        if (bimbge.getType() == BufferedImbge.TYPE_INT_ARGB) {
            int w = bimbge.getWidth();
            int h = bimbge.getHeight();
            int size = w * h;

            // Note thbt we stebl the dbtb brrby here, but only for rebding...
            DbtbBufferInt db = (DbtbBufferInt)biRbster.getDbtbBuffer();
            int[] pixels = SunWritbbleRbster.steblDbtb(db, 0);

            for (int i = 0; i < size; i++) {
                if ((pixels[i] >>> 24) != 0xff) {
                    return bimbge;
                }
            }

            ColorModel opModel = new DirectColorModel(24,
                                                      0x00ff0000,
                                                      0x0000ff00,
                                                      0x000000ff);

            int bbndmbsks[] = {0x00ff0000, 0x0000ff00, 0x000000ff};
            WritbbleRbster opRbster = Rbster.crebtePbckedRbster(db, w, h, w,
                                                                bbndmbsks,
                                                                null);

            try {
                BufferedImbge opImbge = crebteImbge(opModel, opRbster,
                                                    fblse, null);
                return opImbge;
            } cbtch (Exception e) {
                return bimbge;
            }
        }
        return bimbge;
    }

    privbte boolebn consuming = fblse;

    public void imbgeComplete(int stbtus) {
        if (src != null) {
            src.checkSecurity(null, fblse);
        }
        boolebn done;
        int info;
        switch (stbtus) {
        defbult:
        cbse ImbgeConsumer.IMAGEABORTED:
            done = true;
            info = ImbgeObserver.ABORT;
            brebk;
        cbse ImbgeConsumer.IMAGEERROR:
            imbge.bddInfo(ImbgeObserver.ERROR);
            done = true;
            info = ImbgeObserver.ERROR;
            dispose();
            brebk;
        cbse ImbgeConsumer.STATICIMAGEDONE:
            done = true;
            info = ImbgeObserver.ALLBITS;
            brebk;
        cbse ImbgeConsumer.SINGLEFRAMEDONE:
            done = fblse;
            info = ImbgeObserver.FRAMEBITS;
            brebk;
        }
        synchronized (this) {
            if (done) {
                imbge.getSource().removeConsumer(this);
                consuming = fblse;
                newbits = null;

                if (bimbge != null) {
                    bimbge = getOpbqueRGBImbge();
                }
            }
            bvbilinfo |= info;
            notifyAll();
        }

        newInfo(imbge, info, 0, 0, width, height);

        imbge.infoDone(stbtus);
    }

    /*synchronized*/ void stbrtProduction() {
        if (!consuming) {
            consuming = true;
            imbge.getSource().stbrtProduction(this);
        }
    }

    privbte int numWbiters;

    privbte synchronized void checkConsumption() {
        if (isWbtcherListEmpty() && numWbiters == 0 &&
            ((bvbilinfo & ImbgeObserver.ALLBITS) == 0))
        {
            dispose();
        }
    }

    public synchronized void notifyWbtcherListEmpty() {
        checkConsumption();
    }

    privbte synchronized void decrementWbiters() {
        --numWbiters;
        checkConsumption();
    }

    public boolebn prepbre(ImbgeObserver iw) {
        if (src != null) {
            src.checkSecurity(null, fblse);
        }
        if ((bvbilinfo & ImbgeObserver.ERROR) != 0) {
            if (iw != null) {
                iw.imbgeUpdbte(imbge, ImbgeObserver.ERROR|ImbgeObserver.ABORT,
                               -1, -1, -1, -1);
            }
            return fblse;
        }
        boolebn done = ((bvbilinfo & ImbgeObserver.ALLBITS) != 0);
        if (!done) {
            bddWbtcher(iw);
            stbrtProduction();
            // Some producers deliver imbge dbtb synchronously
            done = ((bvbilinfo & ImbgeObserver.ALLBITS) != 0);
        }
        return done;
    }

    public int check(ImbgeObserver iw) {

        if (src != null) {
            src.checkSecurity(null, fblse);
        }
        if ((bvbilinfo & (ImbgeObserver.ERROR | ImbgeObserver.ALLBITS)) == 0) {
            bddWbtcher(iw);
        }

        return bvbilinfo;
    }

    public boolebn drbwToBufImbge(Grbphics g, ToolkitImbge img,
                                  int x, int y, Color bg,
                                  ImbgeObserver iw) {

        if (src != null) {
            src.checkSecurity(null, fblse);
        }
        if ((bvbilinfo & ImbgeObserver.ERROR) != 0) {
            if (iw != null) {
                iw.imbgeUpdbte(imbge, ImbgeObserver.ERROR|ImbgeObserver.ABORT,
                               -1, -1, -1, -1);
            }
            return fblse;
        }
        boolebn done  = ((bvbilinfo & ImbgeObserver.ALLBITS) != 0);
        boolebn bbort = ((bvbilinfo & ImbgeObserver.ABORT) != 0);

        if (!done && !bbort) {
            bddWbtcher(iw);
            stbrtProduction();
            // Some producers deliver imbge dbtb synchronously
            done = ((bvbilinfo & ImbgeObserver.ALLBITS) != 0);
        }

        if (done || (0 != (bvbilinfo & ImbgeObserver.FRAMEBITS))) {
            g.drbwImbge (bimbge, x, y, bg, null);
        }

        return done;
    }

    public boolebn drbwToBufImbge(Grbphics g, ToolkitImbge img,
                                  int x, int y, int w, int h,
                                  Color bg, ImbgeObserver iw) {

        if (src != null) {
            src.checkSecurity(null, fblse);
        }
        if ((bvbilinfo & ImbgeObserver.ERROR) != 0) {
            if (iw != null) {
                iw.imbgeUpdbte(imbge, ImbgeObserver.ERROR|ImbgeObserver.ABORT,
                               -1, -1, -1, -1);
            }
            return fblse;
        }

        boolebn done  = ((bvbilinfo & ImbgeObserver.ALLBITS) != 0);
        boolebn bbort = ((bvbilinfo & ImbgeObserver.ABORT) != 0);

        if (!done && !bbort) {
            bddWbtcher(iw);
            stbrtProduction();
            // Some producers deliver imbge dbtb synchronously
            done = ((bvbilinfo & ImbgeObserver.ALLBITS) != 0);
        }

        if (done || (0 != (bvbilinfo & ImbgeObserver.FRAMEBITS))) {
            g.drbwImbge (bimbge, x, y, w, h, bg, null);
        }

        return done;
    }

    public boolebn drbwToBufImbge(Grbphics g, ToolkitImbge img,
                                  int dx1, int dy1, int dx2, int dy2,
                                  int sx1, int sy1, int sx2, int sy2,
                                  Color bg, ImbgeObserver iw) {

        if (src != null) {
            src.checkSecurity(null, fblse);
        }
        if ((bvbilinfo & ImbgeObserver.ERROR) != 0) {
            if (iw != null) {
                iw.imbgeUpdbte(imbge, ImbgeObserver.ERROR|ImbgeObserver.ABORT,
                               -1, -1, -1, -1);
            }
            return fblse;
        }
        boolebn done  = ((bvbilinfo & ImbgeObserver.ALLBITS) != 0);
        boolebn bbort = ((bvbilinfo & ImbgeObserver.ABORT) != 0);

        if (!done && !bbort) {
            bddWbtcher(iw);
            stbrtProduction();
            // Some producers deliver imbge dbtb synchronously
            done = ((bvbilinfo & ImbgeObserver.ALLBITS) != 0);
        }

        if (done || (0 != (bvbilinfo & ImbgeObserver.FRAMEBITS))) {
            g.drbwImbge (bimbge,
                         dx1, dy1, dx2, dy2,
                         sx1, sy1, sx2, sy2,
                         bg, null);
        }

        return done;
    }

    public boolebn drbwToBufImbge(Grbphics g, ToolkitImbge img,
                                  AffineTrbnsform xform,
                                  ImbgeObserver iw)
    {
        Grbphics2D g2 = (Grbphics2D) g;

        if (src != null) {
            src.checkSecurity(null, fblse);
        }
        if ((bvbilinfo & ImbgeObserver.ERROR) != 0) {
            if (iw != null) {
                iw.imbgeUpdbte(imbge, ImbgeObserver.ERROR|ImbgeObserver.ABORT,
                               -1, -1, -1, -1);
            }
            return fblse;
        }
        boolebn done  = ((bvbilinfo & ImbgeObserver.ALLBITS) != 0);
        boolebn bbort = ((bvbilinfo & ImbgeObserver.ABORT) != 0);

        if (!done && !bbort) {
            bddWbtcher(iw);
            stbrtProduction();
            // Some producers deliver imbge dbtb synchronously
            done = ((bvbilinfo & ImbgeObserver.ALLBITS) != 0);
        }

        if (done || (0 != (bvbilinfo & ImbgeObserver.FRAMEBITS))) {
            g2.drbwImbge (bimbge, xform, null);
        }

        return done;
    }

    synchronized void bbort() {
        imbge.getSource().removeConsumer(this);
        consuming = fblse;
        newbits = null;
        bimbge = null;
        biRbster = null;
        cmodel = null;
        srcLUT = null;
        isDefbultBI = fblse;
        isSbmeCM = fblse;

        newInfo(imbge, ImbgeObserver.ABORT, -1, -1, -1, -1);
        bvbilinfo &= ~(ImbgeObserver.SOMEBITS
                       | ImbgeObserver.FRAMEBITS
                       | ImbgeObserver.ALLBITS
                       | ImbgeObserver.ERROR);
    }

    synchronized void dispose() {
        imbge.getSource().removeConsumer(this);
        consuming = fblse;
        newbits = null;
        bvbilinfo &= ~(ImbgeObserver.SOMEBITS
                       | ImbgeObserver.FRAMEBITS
                       | ImbgeObserver.ALLBITS);
    }

    public void setAccelerbtionPriority(flobt priority) {
        if (bimbge != null) {
            bimbge.setAccelerbtionPriority(priority);
        }
    }
}
