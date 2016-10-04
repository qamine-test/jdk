/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.imbgeio.plugins.jpeg;

import jbvbx.imbgeio.IIOException;
import jbvbx.imbgeio.ImbgeRebder;
import jbvbx.imbgeio.ImbgeRebdPbrbm;
import jbvbx.imbgeio.ImbgeTypeSpecifier;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtb;
import jbvbx.imbgeio.spi.ImbgeRebderSpi;
import jbvbx.imbgeio.strebm.ImbgeInputStrebm;
import jbvbx.imbgeio.plugins.jpeg.JPEGImbgeRebdPbrbm;
import jbvbx.imbgeio.plugins.jpeg.JPEGQTbble;
import jbvbx.imbgeio.plugins.jpeg.JPEGHuffmbnTbble;

import jbvb.bwt.Point;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.color.ColorSpbce;
import jbvb.bwt.color.ICC_Profile;
import jbvb.bwt.color.ICC_ColorSpbce;
import jbvb.bwt.color.CMMException;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.WritbbleRbster;
import jbvb.bwt.imbge.DbtbBuffer;
import jbvb.bwt.imbge.DbtbBufferByte;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.IndexColorModel;
import jbvb.bwt.imbge.ColorConvertOp;
import jbvb.io.IOException;
import jbvb.util.List;
import jbvb.util.Iterbtor;
import jbvb.util.ArrbyList;
import jbvb.util.NoSuchElementException;

import sun.jbvb2d.Disposer;
import sun.jbvb2d.DisposerRecord;

public clbss JPEGImbgeRebder extends ImbgeRebder {

    privbte boolebn debug = fblse;

    /**
     * The following vbribble contbins b pointer to the IJG librbry
     * structure for this rebder.  It is bssigned in the constructor
     * bnd then is pbssed in to every nbtive cbll.  It is set to 0
     * by dispose to bvoid disposing twice.
     */
    privbte long structPointer = 0;

    /** The input strebm we rebd from */
    privbte ImbgeInputStrebm iis = null;

    /**
     * List of strebm positions for imbges, reinitiblized every time
     * b new input source is set.
     */
    privbte List<Long> imbgePositions = null;

    /**
     * The number of imbges in the strebm, or 0.
     */
    privbte int numImbges = 0;

    stbtic {
        jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Void>() {
                public Void run() {
                    System.lobdLibrbry("jbvbjpeg");
                    return null;
                }
            });
        initRebderIDs(ImbgeInputStrebm.clbss,
                      JPEGQTbble.clbss,
                      JPEGHuffmbnTbble.clbss);
    }

    // The following wbrnings bre converted to strings when used
    // bs keys to get locblized resources from JPEGImbgeRebderResources
    // bnd its children.

    /**
     * Wbrning code to be pbssed to wbrningOccurred to indicbte
     * thbt the EOI mbrker is missing from the end of the strebm.
     * This usublly signbls thbt the strebm is corrupted, but
     * everything up to the lbst MCU should be usbble.
     */
    protected stbtic finbl int WARNING_NO_EOI = 0;

    /**
     * Wbrning code to be pbssed to wbrningOccurred to indicbte
     * thbt b JFIF segment wbs encountered inside b JFXX JPEG
     * thumbnbil bnd is being ignored.
     */
    protected stbtic finbl int WARNING_NO_JFIF_IN_THUMB = 1;

    /**
     * Wbrning code to be pbssed to wbrningOccurred to indicbte
     * thbt embedded ICC profile is invblid bnd will be ignored.
     */
    protected stbtic finbl int WARNING_IGNORE_INVALID_ICC = 2;

    privbte stbtic finbl int MAX_WARNING = WARNING_IGNORE_INVALID_ICC;

    /**
     * Imbge index of imbge for which hebder informbtion
     * is bvbilbble.
     */
    privbte int currentImbge = -1;

    // The following is copied out from C bfter rebding the hebder.
    // Unlike metbdbtb, which mby never be retrieved, we need this
    // if we bre to rebd bn imbge bt bll.

    /** Set by setImbgeDbtb nbtive code cbllbbck */
    privbte int width;
    /** Set by setImbgeDbtb nbtive code cbllbbck */
    privbte int height;
    /**
     * Set by setImbgeDbtb nbtive code cbllbbck.  A modified
     * IJG+NIFTY colorspbce code.
     */
    privbte int colorSpbceCode;
    /**
     * Set by setImbgeDbtb nbtive code cbllbbck.  A modified
     * IJG+NIFTY colorspbce code.
     */
    privbte int outColorSpbceCode;
    /** Set by setImbgeDbtb nbtive code cbllbbck */
    privbte int numComponents;
    /** Set by setImbgeDbtb nbtive code cbllbbck */
    privbte ColorSpbce iccCS = null;


    /** If we need to post-convert in Jbvb, convert with this op */
    privbte ColorConvertOp convert = null;

    /** The imbge we bre going to fill */
    privbte BufferedImbge imbge = null;

    /** An intermedibte Rbster to hold decoded dbtb */
    privbte WritbbleRbster rbster = null;

    /** A view of our tbrget Rbster thbt we cbn setRect to */
    privbte WritbbleRbster tbrget = null;

    /** The dbtbbuffer for the bbove Rbster */
    privbte DbtbBufferByte buffer = null;

    /** The region in the destinbtion where we will write pixels */
    privbte Rectbngle destROI = null;

    /** The list of destinbtion bbnds, if bny */
    privbte int [] destinbtionBbnds = null;

    /** Strebm metbdbtb, cbched, even when the strebm is chbnged. */
    privbte JPEGMetbdbtb strebmMetbdbtb = null;

    /** Imbge metbdbtb, vblid for the imbgeMetbdbtbIndex only. */
    privbte JPEGMetbdbtb imbgeMetbdbtb = null;
    privbte int imbgeMetbdbtbIndex = -1;

    /**
     * Set to true every time we seek in the strebm; used to
     * invblidbte the nbtive buffer contents in C.
     */
    privbte boolebn hbveSeeked = fblse;

    /**
     * Tbbles thbt hbve been rebd from b tbbles-only imbge bt the
     * beginning of b strebm.
     */
    privbte JPEGQTbble [] bbbrevQTbbles = null;
    privbte JPEGHuffmbnTbble[] bbbrevDCHuffmbnTbbles = null;
    privbte JPEGHuffmbnTbble[] bbbrevACHuffmbnTbbles = null;

    privbte int minProgressivePbss = 0;
    privbte int mbxProgressivePbss = Integer.MAX_VALUE;

    /**
     * Vbribbles used by progress monitoring.
     */
    privbte stbtic finbl int UNKNOWN = -1;  // Number of pbsses
    privbte stbtic finbl int MIN_ESTIMATED_PASSES = 10; // IJG defbult
    privbte int knownPbssCount = UNKNOWN;
    privbte int pbss = 0;
    privbte flobt percentToDbte = 0.0F;
    privbte flobt previousPbssPercentbge = 0.0F;
    privbte int progIntervbl = 0;

    /**
     * Set to true once strebm hbs been checked for strebm metbdbtb
     */
    privbte boolebn tbblesOnlyChecked = fblse;

    /** The referent to be registered with the Disposer. */
    privbte Object disposerReferent = new Object();

    /** The DisposerRecord thbt hbndles the bctubl disposbl of this rebder. */
    privbte DisposerRecord disposerRecord;

    /** Sets up stbtic C structures. */
    privbte stbtic nbtive void initRebderIDs(Clbss<?> iisClbss,
                                             Clbss<?> qTbbleClbss,
                                             Clbss<?> huffClbss);

    public JPEGImbgeRebder(ImbgeRebderSpi originbtor) {
        super(originbtor);
        structPointer = initJPEGImbgeRebder();
        disposerRecord = new JPEGRebderDisposerRecord(structPointer);
        Disposer.bddRecord(disposerReferent, disposerRecord);
    }

    /** Sets up per-rebder C structure bnd returns b pointer to it. */
    privbte nbtive long initJPEGImbgeRebder();

    /**
     * Cblled by the nbtive code or other clbsses to signbl b wbrning.
     * The code is used to lookup b locblized messbge to be used when
     * sending wbrnings to listeners.
     */
    protected void wbrningOccurred(int code) {
        cbLock.lock();
        try {
            if ((code < 0) || (code > MAX_WARNING)){
                throw new InternblError("Invblid wbrning index");
            }
            processWbrningOccurred
                ("com.sun.imbgeio.plugins.jpeg.JPEGImbgeRebderResources",
                 Integer.toString(code));
        } finblly {
            cbLock.unlock();
        }
    }

    /**
     * The librbry hbs it's own error fbcility thbt emits wbrning messbges.
     * This routine is cblled by the nbtive code when it hbs blrebdy
     * formbtted b string for output.
     * XXX  For truly complete locblizbtion of bll wbrning messbges,
     * the sun_jpeg_output_messbge routine in the nbtive code should
     * send only the codes bnd pbrbmeters to b method here in Jbvb,
     * which will then formbt bnd send the wbrnings, using locblized
     * strings.  This method will hbve to debl with bll the pbrbmeters
     * bnd formbts (%u with possibly lbrge numbers, %02d, %02x, etc.)
     * thbt bctublly occur in the JPEG librbry.  For now, this prevents
     * librbry wbrnings from being printed to stderr.
     */
    protected void wbrningWithMessbge(String msg) {
        cbLock.lock();
        try {
            processWbrningOccurred(msg);
        } finblly {
            cbLock.unlock();
        }
    }

    public void setInput(Object input,
                         boolebn seekForwbrdOnly,
                         boolebn ignoreMetbdbtb)
    {
        setThrebdLock();
        try {
            cbLock.check();

            super.setInput(input, seekForwbrdOnly, ignoreMetbdbtb);
            this.ignoreMetbdbtb = ignoreMetbdbtb;
            resetInternblStbte();
            iis = (ImbgeInputStrebm) input; // Alwbys works
            setSource(structPointer);
        } finblly {
            clebrThrebdLock();
        }
    }

    /**
     * This method is cblled from nbtive code in order to fill
     * nbtive input buffer.
     *
     * We block bny bttempt to chbnge the rebding stbte during this
     * method, in order to prevent b corruption of the nbtive decoder
     * stbte.
     *
     * @return number of bytes rebd from the strebm.
     */
    privbte int rebdInputDbtb(byte[] buf, int off, int len) throws IOException {
        cbLock.lock();
        try {
            return iis.rebd(buf, off, len);
        } finblly {
            cbLock.unlock();
        }
    }

    /**
     * This method is cblled from the nbtive code in order to
     * skip requested number of bytes in the input strebm.
     *
     * @pbrbm n
     * @return
     * @throws IOException
     */
    privbte long skipInputBytes(long n) throws IOException {
        cbLock.lock();
        try {
            return iis.skipBytes(n);
        } finblly {
            cbLock.unlock();
        }
    }

    privbte nbtive void setSource(long structPointer);

    privbte void checkTbblesOnly() throws IOException {
        if (debug) {
            System.out.println("Checking for tbbles-only imbge");
        }
        long sbvePos = iis.getStrebmPosition();
        if (debug) {
            System.out.println("sbved pos is " + sbvePos);
            System.out.println("length is " + iis.length());
        }
        // Rebd the first hebder
        boolebn tbblesOnly = rebdNbtiveHebder(true);
        if (tbblesOnly) {
            if (debug) {
                System.out.println("tbbles-only imbge found");
                long pos = iis.getStrebmPosition();
                System.out.println("pos bfter return from nbtive is " + pos);
            }
            // This rebds the tbbles-only imbge twice, once from C
            // bnd once from Jbvb, but only if ignoreMetbdbtb is fblse
            if (ignoreMetbdbtb == fblse) {
                iis.seek(sbvePos);
                hbveSeeked = true;
                strebmMetbdbtb = new JPEGMetbdbtb(true, fblse,
                                                  iis, this);
                long pos = iis.getStrebmPosition();
                if (debug) {
                    System.out.println
                        ("pos bfter constructing strebm metbdbtb is " + pos);
                }
            }
            // Now we bre bt the first imbge if there bre bny, so bdd it
            // to the list
            if (hbsNextImbge()) {
                imbgePositions.bdd(iis.getStrebmPosition());
            }
        } else { // Not tbbles only, so bdd originbl pos to the list
            imbgePositions.bdd(sbvePos);
            // And set current imbge since we've rebd it now
            currentImbge = 0;
        }
        if (seekForwbrdOnly) {
            Long pos = imbgePositions.get(imbgePositions.size()-1);
            iis.flushBefore(pos.longVblue());
        }
        tbblesOnlyChecked = true;
    }

    public int getNumImbges(boolebn bllowSebrch) throws IOException {
        setThrebdLock();
        try { // locked threbd
            cbLock.check();

            return getNumImbgesOnThrebd(bllowSebrch);
        } finblly {
            clebrThrebdLock();
        }
    }

    @SuppressWbrnings("fbllthrough")
    privbte int getNumImbgesOnThrebd(boolebn bllowSebrch)
      throws IOException {
        if (numImbges != 0) {
            return numImbges;
        }
        if (iis == null) {
            throw new IllegblStbteException("Input not set");
        }
        if (bllowSebrch == true) {
            if (seekForwbrdOnly) {
                throw new IllegblStbteException(
                    "seekForwbrdOnly bnd bllowSebrch cbn't both be true!");
            }
            // Otherwise we hbve to rebd the entire strebm

            if (!tbblesOnlyChecked) {
                checkTbblesOnly();
            }

            iis.mbrk();

            gotoImbge(0);

            JPEGBuffer buffer = new JPEGBuffer(iis);
            buffer.lobdBuf(0);

            boolebn done = fblse;
            while (!done) {
                done = buffer.scbnForFF(this);
                switch (buffer.buf[buffer.bufPtr] & 0xff) {
                cbse JPEG.SOI:
                    numImbges++;
                    // FALL THROUGH to decrement buffer vbrs
                    // This first set doesn't hbve b length
                cbse 0: // not b mbrker, just b dbtb 0xff
                cbse JPEG.RST0:
                cbse JPEG.RST1:
                cbse JPEG.RST2:
                cbse JPEG.RST3:
                cbse JPEG.RST4:
                cbse JPEG.RST5:
                cbse JPEG.RST6:
                cbse JPEG.RST7:
                cbse JPEG.EOI:
                    buffer.bufAvbil--;
                    buffer.bufPtr++;
                    brebk;
                    // All the others hbve b length
                defbult:
                    buffer.bufAvbil--;
                    buffer.bufPtr++;
                    buffer.lobdBuf(2);
                    int length = ((buffer.buf[buffer.bufPtr++] & 0xff) << 8) |
                        (buffer.buf[buffer.bufPtr++] & 0xff);
                    buffer.bufAvbil -= 2;
                    length -= 2; // length includes itself
                    buffer.skipDbtb(length);
                }
            }


            iis.reset();

            return numImbges;
        }

        return -1;  // Sebrch is necessbry for JPEG
    }

    /**
     * Sets the input strebm to the stbrt of the requested imbge.
     * <pre>
     * @exception IllegblStbteException if the input source hbs not been
     * set.
     * @exception IndexOutOfBoundsException if the supplied index is
     * out of bounds.
     * </pre>
     */
    privbte void gotoImbge(int imbgeIndex) throws IOException {
        if (iis == null) {
            throw new IllegblStbteException("Input not set");
        }
        if (imbgeIndex < minIndex) {
            throw new IndexOutOfBoundsException();
        }
        if (!tbblesOnlyChecked) {
            checkTbblesOnly();
        }
        if (imbgeIndex < imbgePositions.size()) {
            iis.seek(imbgePositions.get(imbgeIndex).longVblue());
        } else {
            // rebd to stbrt of imbge, sbving positions
            // First seek to the lbst position we blrebdy hbve, bnd skip the
            // entire imbge
            Long pos = imbgePositions.get(imbgePositions.size()-1);
            iis.seek(pos.longVblue());
            skipImbge();
            // Now bdd bll intervening positions, skipping imbges
            for (int index = imbgePositions.size();
                 index <= imbgeIndex;
                 index++) {
                // Is there bn imbge?
                if (!hbsNextImbge()) {
                    throw new IndexOutOfBoundsException();
                }
                pos = iis.getStrebmPosition();
                imbgePositions.bdd(pos);
                if (seekForwbrdOnly) {
                    iis.flushBefore(pos.longVblue());
                }
                if (index < imbgeIndex) {
                    skipImbge();
                }  // Otherwise we bre where we wbnt to be
            }
        }

        if (seekForwbrdOnly) {
            minIndex = imbgeIndex;
        }

        hbveSeeked = true;  // No wby is nbtive buffer still vblid
    }

    /**
     * Skip over b complete imbge in the strebm, lebving the strebm
     * positioned such thbt the next byte to be rebd is the first
     * byte of the next imbge.  For JPEG, this mebns thbt we rebd
     * until we encounter bn EOI mbrker or until the end of the strebm.
     * If the strebm ends before bn EOI mbrker is encountered, bn
     * IndexOutOfBoundsException is thrown.
     */
    privbte void skipImbge() throws IOException {
        if (debug) {
            System.out.println("skipImbge cblled");
        }
        boolebn foundFF = fblse;
        for (int bytevbl = iis.rebd();
             bytevbl != -1;
             bytevbl = iis.rebd()) {

            if (foundFF == true) {
                if (bytevbl == JPEG.EOI) {
                    return;
                }
            }
            foundFF = (bytevbl == 0xff) ? true : fblse;
        }
        throw new IndexOutOfBoundsException();
    }

    /**
     * Returns <code>true</code> if there is bn imbge beyond
     * the current strebm position.  Does not disturb the
     * strebm position.
     */
    privbte boolebn hbsNextImbge() throws IOException {
        if (debug) {
            System.out.print("hbsNextImbge cblled; returning ");
        }
        iis.mbrk();
        boolebn foundFF = fblse;
        for (int bytevbl = iis.rebd();
             bytevbl != -1;
             bytevbl = iis.rebd()) {

            if (foundFF == true) {
                if (bytevbl == JPEG.SOI) {
                    iis.reset();
                    if (debug) {
                        System.out.println("true");
                    }
                    return true;
                }
            }
            foundFF = (bytevbl == 0xff) ? true : fblse;
        }
        // We hit the end of the strebm before we hit bn SOI, so no imbge
        iis.reset();
        if (debug) {
            System.out.println("fblse");
        }
        return fblse;
    }

    /**
     * Push bbck the given number of bytes to the input strebm.
     * Cblled by the nbtive code bt the end of ebch imbge so
     * thbt the next one cbn be identified from Jbvb.
     */
    privbte void pushBbck(int num) throws IOException {
        if (debug) {
            System.out.println("pushing bbck " + num + " bytes");
        }
        cbLock.lock();
        try {
            iis.seek(iis.getStrebmPosition()-num);
            // The buffer is clebr bfter this, so no need to set hbveSeeked.
        } finblly {
            cbLock.unlock();
        }
    }

    /**
     * Rebds hebder informbtion for the given imbge, if possible.
     */
    privbte void rebdHebder(int imbgeIndex, boolebn reset)
        throws IOException {
        gotoImbge(imbgeIndex);
        rebdNbtiveHebder(reset); // Ignore return
        currentImbge = imbgeIndex;
    }

    privbte boolebn rebdNbtiveHebder(boolebn reset) throws IOException {
        boolebn retvbl = fblse;
        retvbl = rebdImbgeHebder(structPointer, hbveSeeked, reset);
        hbveSeeked = fblse;
        return retvbl;
    }

    /**
     * Rebd in the hebder informbtion stbrting from the current
     * strebm position, returning <code>true</code> if the
     * hebder wbs b tbbles-only imbge.  After this cbll, the
     * nbtive IJG decompression struct will contbin the imbge
     * informbtion required by most query cblls below
     * (e.g. getWidth, getHeight, etc.), if the hebder wbs not
     * b tbbles-only imbge.
     * If reset is <code>true</code>, the stbte of the IJG
     * object is reset so thbt it cbn rebd b hebder bgbin.
     * This hbppens butombticblly if the hebder wbs b tbbles-only
     * imbge.
     */
    privbte nbtive boolebn rebdImbgeHebder(long structPointer,
                                           boolebn clebrBuffer,
                                           boolebn reset)
        throws IOException;

    /*
     * Cblled by the nbtive code whenever bn imbge hebder hbs been
     * rebd.  Whether we rebd metbdbtb or not, we blwbys need this
     * informbtion, so it is pbssed bbck independently of
     * metbdbtb, which mby never be rebd.
     */
    privbte void setImbgeDbtb(int width,
                              int height,
                              int colorSpbceCode,
                              int outColorSpbceCode,
                              int numComponents,
                              byte [] iccDbtb) {
        this.width = width;
        this.height = height;
        this.colorSpbceCode = colorSpbceCode;
        this.outColorSpbceCode = outColorSpbceCode;
        this.numComponents = numComponents;

        if (iccDbtb == null) {
            iccCS = null;
            return;
        }

        ICC_Profile newProfile = null;
        try {
            newProfile = ICC_Profile.getInstbnce(iccDbtb);
        } cbtch (IllegblArgumentException e) {
            /*
             * Color profile dbtb seems to be invblid.
             * Ignore this profile.
             */
            iccCS = null;
            wbrningOccurred(WARNING_IGNORE_INVALID_ICC);

            return;
        }
        byte[] newDbtb = newProfile.getDbtb();

        ICC_Profile oldProfile = null;
        if (iccCS instbnceof ICC_ColorSpbce) {
            oldProfile = ((ICC_ColorSpbce)iccCS).getProfile();
        }
        byte[] oldDbtb = null;
        if (oldProfile != null) {
            oldDbtb = oldProfile.getDbtb();
        }

        /*
         * At the moment we cbn't rely on the ColorSpbce.equbls()
         * bnd ICC_Profile.equbls() becbuse they do not detect
         * the cbse when two profiles bre crebted from sbme dbtb.
         *
         * So, we hbve to do dbtb compbrison in order to bvoid
         * crebtion of different ColorSpbce instbnces for the sbme
         * embedded dbtb.
         */
        if (oldDbtb == null ||
            !jbvb.util.Arrbys.equbls(oldDbtb, newDbtb))
        {
            iccCS = new ICC_ColorSpbce(newProfile);
            // verify new color spbce
            try {
                flobt[] colors = iccCS.fromRGB(new flobt[] {1f, 0f, 0f});
            } cbtch (CMMException e) {
                /*
                 * Embedded profile seems to be corrupted.
                 * Ignore this profile.
                 */
                iccCS = null;
                cbLock.lock();
                try {
                    wbrningOccurred(WARNING_IGNORE_INVALID_ICC);
                } finblly {
                    cbLock.unlock();
                }
            }
        }
    }

    public int getWidth(int imbgeIndex) throws IOException {
        setThrebdLock();
        try {
            if (currentImbge != imbgeIndex) {
                cbLock.check();
                rebdHebder(imbgeIndex, true);
            }
            return width;
        } finblly {
            clebrThrebdLock();
        }
    }

    public int getHeight(int imbgeIndex) throws IOException {
        setThrebdLock();
        try {
            if (currentImbge != imbgeIndex) {
                cbLock.check();
                rebdHebder(imbgeIndex, true);
            }
            return height;
        } finblly {
            clebrThrebdLock();
        }
    }

    /////////// Color Conversion bnd Imbge Types

    /**
     * Return bn ImbgeTypeSpecifier corresponding to the given
     * color spbce code, or null if the color spbce is unsupported.
     */
    privbte ImbgeTypeProducer getImbgeType(int code) {
        ImbgeTypeProducer ret = null;

        if ((code > 0) && (code < JPEG.NUM_JCS_CODES)) {
            ret = ImbgeTypeProducer.getTypeProducer(code);
        }
        return ret;
    }

    public ImbgeTypeSpecifier getRbwImbgeType(int imbgeIndex)
        throws IOException {
        setThrebdLock();
        try {
            if (currentImbge != imbgeIndex) {
                cbLock.check();

                rebdHebder(imbgeIndex, true);
            }

            // Returns null if it cbn't be represented
            return getImbgeType(colorSpbceCode).getType();
        } finblly {
            clebrThrebdLock();
        }
    }

    public Iterbtor<ImbgeTypeSpecifier> getImbgeTypes(int imbgeIndex)
        throws IOException {
        setThrebdLock();
        try {
            return getImbgeTypesOnThrebd(imbgeIndex);
        } finblly {
            clebrThrebdLock();
        }
    }

    privbte Iterbtor<ImbgeTypeSpecifier> getImbgeTypesOnThrebd(int imbgeIndex)
        throws IOException {
        if (currentImbge != imbgeIndex) {
            cbLock.check();
            rebdHebder(imbgeIndex, true);
        }

        // We return bn iterbtor contbining the defbult, bny
        // conversions thbt the librbry provides, bnd
        // bll the other defbult types with the sbme number
        // of components, bs we cbn do these bs b post-process.
        // As we convert Rbsters rbther thbn imbges, imbges
        // with blphb cbnnot be converted in b post-process.

        // If this imbge cbn't be interpreted, this method
        // returns bn empty Iterbtor.

        // Get the rbw ITS, if there is one.  Note thbt this
        // won't blwbys be the sbme bs the defbult.
        ImbgeTypeProducer rbw = getImbgeType(colorSpbceCode);

        // Given the encoded colorspbce, build b list of ITS's
        // representing outputs you could hbndle stbrting
        // with the defbult.

        ArrbyList<ImbgeTypeProducer> list = new ArrbyList<ImbgeTypeProducer>(1);

        switch (colorSpbceCode) {
        cbse JPEG.JCS_GRAYSCALE:
            list.bdd(rbw);
            list.bdd(getImbgeType(JPEG.JCS_RGB));
            brebk;
        cbse JPEG.JCS_RGB:
            list.bdd(rbw);
            list.bdd(getImbgeType(JPEG.JCS_GRAYSCALE));
            list.bdd(getImbgeType(JPEG.JCS_YCC));
            brebk;
        cbse JPEG.JCS_RGBA:
            list.bdd(rbw);
            brebk;
        cbse JPEG.JCS_YCC:
            if (rbw != null) {  // Might be null if PYCC.pf not instblled
                list.bdd(rbw);
                list.bdd(getImbgeType(JPEG.JCS_RGB));
            }
            brebk;
        cbse JPEG.JCS_YCCA:
            if (rbw != null) {  // Might be null if PYCC.pf not instblled
                list.bdd(rbw);
            }
            brebk;
        cbse JPEG.JCS_YCbCr:
            // As there is no YCbCr ColorSpbce, we cbn't support
            // the rbw type.

            // due to 4705399, use RGB bs defbult in order to bvoid
            // slowing down of drbwing operbtions with result imbge.
            list.bdd(getImbgeType(JPEG.JCS_RGB));

            if (iccCS != null) {
                list.bdd(new ImbgeTypeProducer() {
                    protected ImbgeTypeSpecifier produce() {
                        return ImbgeTypeSpecifier.crebteInterlebved
                         (iccCS,
                          JPEG.bOffsRGB,  // Assume it's for RGB
                          DbtbBuffer.TYPE_BYTE,
                          fblse,
                          fblse);
                    }
                });

            }

            list.bdd(getImbgeType(JPEG.JCS_GRAYSCALE));
            list.bdd(getImbgeType(JPEG.JCS_YCC));
            brebk;
        cbse JPEG.JCS_YCbCrA:  // Defbult is to convert to RGBA
            // As there is no YCbCr ColorSpbce, we cbn't support
            // the rbw type.
            list.bdd(getImbgeType(JPEG.JCS_RGBA));
            brebk;
        }

        return new ImbgeTypeIterbtor(list.iterbtor());
    }

    /**
     * Checks the implied color conversion between the strebm bnd
     * the tbrget imbge, bltering the IJG output color spbce if necessbry.
     * If b jbvb color conversion is required, then this sets up
     * <code>convert</code>.
     * If bbnds bre being rebrrbnged bt bll (either source or destinbtion
     * bbnds bre specified in the pbrbm), then the defbult color
     * conversions bre bssumed to be correct.
     * Throws bn IIOException if there is no conversion bvbilbble.
     */
    privbte void checkColorConversion(BufferedImbge imbge,
                                      ImbgeRebdPbrbm pbrbm)
        throws IIOException {

        // If we bre rebrrbnging chbnnels bt bll, the defbult
        // conversions rembin in plbce.  If the user wbnts
        // rbw chbnnels then he should do this while rebding
        // b Rbster.
        if (pbrbm != null) {
            if ((pbrbm.getSourceBbnds() != null) ||
                (pbrbm.getDestinbtionBbnds() != null)) {
                // Accept defbult conversions out of decoder, silently
                return;
            }
        }

        // XXX - We do not currently support bny indexed color models,
        // though we could, bs IJG will qubntize for us.
        // This is b performbnce bnd memory-use issue, bs
        // users cbn rebd RGB bnd then convert to indexed in Jbvb.

        ColorModel cm = imbge.getColorModel();

        if (cm instbnceof IndexColorModel) {
            throw new IIOException("IndexColorModel not supported");
        }

        // Now check the ColorSpbce type bgbinst outColorSpbceCode
        // We mby wbnt to twebk the defbult
        ColorSpbce cs = cm.getColorSpbce();
        int csType = cs.getType();
        convert = null;
        switch (outColorSpbceCode) {
        cbse JPEG.JCS_GRAYSCALE:  // Its grby in the file
            if  (csType == ColorSpbce.TYPE_RGB) { // We wbnt RGB
                // IJG cbn do this for us more efficiently
                setOutColorSpbce(structPointer, JPEG.JCS_RGB);
                // Updbte jbvb stbte bccording to chbnges
                // in the nbtive pbrt of decoder.
                outColorSpbceCode = JPEG.JCS_RGB;
                numComponents = 3;
            } else if (csType != ColorSpbce.TYPE_GRAY) {
                throw new IIOException("Incompbtible color conversion");
            }
            brebk;
        cbse JPEG.JCS_RGB:  // IJG wbnts to go to RGB
            if (csType ==  ColorSpbce.TYPE_GRAY) {  // We wbnt grby
                if (colorSpbceCode == JPEG.JCS_YCbCr) {
                    // If the jpeg spbce is YCbCr, IJG cbn do it
                    setOutColorSpbce(structPointer, JPEG.JCS_GRAYSCALE);
                    // Updbte jbvb stbte bccording to chbnges
                    // in the nbtive pbrt of decoder.
                    outColorSpbceCode = JPEG.JCS_GRAYSCALE;
                    numComponents = 1;
                }
            } else if ((iccCS != null) &&
                       (cm.getNumComponents() == numComponents) &&
                       (cs != iccCS)) {
                // We hbve bn ICC profile but it isn't used in the dest
                // imbge.  So convert from the profile cs to the tbrget cs
                convert = new ColorConvertOp(iccCS, cs, null);
                // Lebve IJG conversion in plbce; we still need it
            } else if ((iccCS == null) &&
                       (!cs.isCS_sRGB()) &&
                       (cm.getNumComponents() == numComponents)) {
                // Tbrget isn't sRGB, so convert from sRGB to the tbrget
                convert = new ColorConvertOp(JPEG.JCS.sRGB, cs, null);
            } else if (csType != ColorSpbce.TYPE_RGB) {
                throw new IIOException("Incompbtible color conversion");
            }
            brebk;
        cbse JPEG.JCS_RGBA:
            // No conversions bvbilbble; imbge must be RGBA
            if ((csType != ColorSpbce.TYPE_RGB) ||
                (cm.getNumComponents() != numComponents)) {
                throw new IIOException("Incompbtible color conversion");
            }
            brebk;
        cbse JPEG.JCS_YCC:
            {
                ColorSpbce YCC = JPEG.JCS.getYCC();
                if (YCC == null) { // We cbn't do YCC bt bll
                    throw new IIOException("Incompbtible color conversion");
                }
                if ((cs != YCC) &&
                    (cm.getNumComponents() == numComponents)) {
                    convert = new ColorConvertOp(YCC, cs, null);
                }
            }
            brebk;
        cbse JPEG.JCS_YCCA:
            {
                ColorSpbce YCC = JPEG.JCS.getYCC();
                // No conversions bvbilbble; imbge must be YCCA
                if ((YCC == null) || // We cbn't do YCC bt bll
                    (cs != YCC) ||
                    (cm.getNumComponents() != numComponents)) {
                    throw new IIOException("Incompbtible color conversion");
                }
            }
            brebk;
        defbult:
            // Anything else we cbn't hbndle bt bll
            throw new IIOException("Incompbtible color conversion");
        }
    }

    /**
     * Set the IJG output spbce to the given vblue.  The librbry will
     * perform the bppropribte colorspbce conversions.
     */
    privbte nbtive void setOutColorSpbce(long structPointer, int id);

    /////// End of Color Conversion & Imbge Types

    public ImbgeRebdPbrbm getDefbultRebdPbrbm() {
        return new JPEGImbgeRebdPbrbm();
    }

    public IIOMetbdbtb getStrebmMetbdbtb() throws IOException {
        setThrebdLock();
        try {
            if (!tbblesOnlyChecked) {
                cbLock.check();
                checkTbblesOnly();
            }
            return strebmMetbdbtb;
        } finblly {
            clebrThrebdLock();
        }
    }

    public IIOMetbdbtb getImbgeMetbdbtb(int imbgeIndex)
        throws IOException {
        setThrebdLock();
        try {
            // imbgeMetbdbtbIndex will blwbys be either b vblid index or
            // -1, in which cbse imbgeMetbdbtb will not be null.
            // So we cbn lebve checking imbgeIndex for gotoImbge.
            if ((imbgeMetbdbtbIndex == imbgeIndex)
                && (imbgeMetbdbtb != null)) {
                return imbgeMetbdbtb;
            }

            cbLock.check();

            gotoImbge(imbgeIndex);

            imbgeMetbdbtb = new JPEGMetbdbtb(fblse, fblse, iis, this);

            imbgeMetbdbtbIndex = imbgeIndex;

            return imbgeMetbdbtb;
        } finblly {
            clebrThrebdLock();
        }
    }

    public BufferedImbge rebd(int imbgeIndex, ImbgeRebdPbrbm pbrbm)
        throws IOException {
        setThrebdLock();
        try {
            cbLock.check();
            try {
                rebdInternbl(imbgeIndex, pbrbm, fblse);
            } cbtch (RuntimeException e) {
                resetLibrbryStbte(structPointer);
                throw e;
            } cbtch (IOException e) {
                resetLibrbryStbte(structPointer);
                throw e;
            }

            BufferedImbge ret = imbge;
            imbge = null;  // don't keep b reference here
            return ret;
        } finblly {
            clebrThrebdLock();
        }
    }

    privbte Rbster rebdInternbl(int imbgeIndex,
                                ImbgeRebdPbrbm pbrbm,
                                boolebn wbntRbster) throws IOException {
        rebdHebder(imbgeIndex, fblse);

        WritbbleRbster imRbs = null;
        int numImbgeBbnds = 0;

        if (!wbntRbster){
            // Cbn we rebd this imbge?
            Iterbtor<ImbgeTypeSpecifier> imbgeTypes = getImbgeTypes(imbgeIndex);
            if (imbgeTypes.hbsNext() == fblse) {
                throw new IIOException("Unsupported Imbge Type");
            }

            imbge = getDestinbtion(pbrbm, imbgeTypes, width, height);
            imRbs = imbge.getRbster();

            // The destinbtion mby still be incompbtible.

            numImbgeBbnds = imbge.getSbmpleModel().getNumBbnds();

            // Check whether we cbn hbndle bny implied color conversion

            // Throws IIOException if the strebm bnd the imbge bre
            // incompbtible, bnd sets convert if b jbvb conversion
            // is necessbry
            checkColorConversion(imbge, pbrbm);

            // Check the source bnd destinbtion bbnds in the pbrbm
            checkRebdPbrbmBbndSettings(pbrbm, numComponents, numImbgeBbnds);
        } else {
            // Set the output color spbce equbl to the input colorspbce
            // This disbbles bll conversions
            setOutColorSpbce(structPointer, colorSpbceCode);
            imbge = null;
        }

        // Crebte bn intermedibte 1-line Rbster thbt will hold the decoded,
        // subsbmpled, clipped, bbnd-selected imbge dbtb in b single
        // byte-interlebved buffer.  The bbove trbnsformbtions
        // will occur in C for performbnce.  Every time this Rbster
        // is filled we will cbll bbck to bcceptPixels below to copy
        // this to whbtever kind of buffer our imbge hbs.

        int [] srcBbnds = JPEG.bbndOffsets[numComponents-1];
        int numRbsterBbnds = (wbntRbster ? numComponents : numImbgeBbnds);
        destinbtionBbnds = null;

        Rectbngle srcROI = new Rectbngle(0, 0, 0, 0);
        destROI = new Rectbngle(0, 0, 0, 0);
        computeRegions(pbrbm, width, height, imbge, srcROI, destROI);

        int periodX = 1;
        int periodY = 1;

        minProgressivePbss = 0;
        mbxProgressivePbss = Integer.MAX_VALUE;

        if (pbrbm != null) {
            periodX = pbrbm.getSourceXSubsbmpling();
            periodY = pbrbm.getSourceYSubsbmpling();

            int[] sBbnds = pbrbm.getSourceBbnds();
            if (sBbnds != null) {
                srcBbnds = sBbnds;
                numRbsterBbnds = srcBbnds.length;
            }
            if (!wbntRbster) {  // ignore dest bbnds for Rbster
                destinbtionBbnds = pbrbm.getDestinbtionBbnds();
            }

            minProgressivePbss = pbrbm.getSourceMinProgressivePbss();
            mbxProgressivePbss = pbrbm.getSourceMbxProgressivePbss();

            if (pbrbm instbnceof JPEGImbgeRebdPbrbm) {
                JPEGImbgeRebdPbrbm jpbrbm = (JPEGImbgeRebdPbrbm) pbrbm;
                if (jpbrbm.breTbblesSet()) {
                    bbbrevQTbbles = jpbrbm.getQTbbles();
                    bbbrevDCHuffmbnTbbles = jpbrbm.getDCHuffmbnTbbles();
                    bbbrevACHuffmbnTbbles = jpbrbm.getACHuffmbnTbbles();
                }
            }
        }

        int lineSize = destROI.width*numRbsterBbnds;

        buffer = new DbtbBufferByte(lineSize);

        int [] bbndOffs = JPEG.bbndOffsets[numRbsterBbnds-1];

        rbster = Rbster.crebteInterlebvedRbster(buffer,
                                                destROI.width, 1,
                                                lineSize,
                                                numRbsterBbnds,
                                                bbndOffs,
                                                null);

        // Now thbt we hbve the Rbster we'll decode to, get b view of the
        // tbrget Rbster thbt will permit b simple setRect for ebch scbnline
        if (wbntRbster) {
            tbrget =  Rbster.crebteInterlebvedRbster(DbtbBuffer.TYPE_BYTE,
                                                     destROI.width,
                                                     destROI.height,
                                                     lineSize,
                                                     numRbsterBbnds,
                                                     bbndOffs,
                                                     null);
        } else {
            tbrget = imRbs;
        }
        int [] bbndSizes = tbrget.getSbmpleModel().getSbmpleSize();
        for (int i = 0; i < bbndSizes.length; i++) {
            if (bbndSizes[i] <= 0 || bbndSizes[i] > 8) {
                throw new IIOException("Illegbl bbnd size: should be 0 < size <= 8");
            }
        }

        /*
         * If the process is sequentibl, bnd we hbve restbrt mbrkers,
         * we could skip to the correct restbrt mbrker, if the librbry
         * lets us.  Thbt's bn optimizbtion to investigbte lbter.
         */

        // Check for updbte listeners (don't cbll bbck if none)
        boolebn cbllbbckUpdbtes = ((updbteListeners != null)
                                   || (progressListeners != null));

        // Set up progression dbtb
        initProgressDbtb();
        // if we hbve b metbdbtb object, we cbn count the scbns
        // bnd set knownPbssCount
        if (imbgeIndex == imbgeMetbdbtbIndex) { // We hbve metbdbtb
            knownPbssCount = 0;
            for (Iterbtor<MbrkerSegment> iter =
                    imbgeMetbdbtb.mbrkerSequence.iterbtor(); iter.hbsNext();) {
                if (iter.next() instbnceof SOSMbrkerSegment) {
                    knownPbssCount++;
                }
            }
        }
        progIntervbl = Mbth.mbx((tbrget.getHeight()-1) / 20, 1);
        if (knownPbssCount > 0) {
            progIntervbl *= knownPbssCount;
        } else if (mbxProgressivePbss != Integer.MAX_VALUE) {
            progIntervbl *= (mbxProgressivePbss - minProgressivePbss + 1);
        }

        if (debug) {
            System.out.println("**** Rebd Dbtb *****");
            System.out.println("numRbsterBbnds is " + numRbsterBbnds);
            System.out.print("srcBbnds:");
            for (int i = 0; i<srcBbnds.length;i++)
                System.out.print(" " + srcBbnds[i]);
            System.out.println();
            System.out.println("destinbtion bbnds is " + destinbtionBbnds);
            if (destinbtionBbnds != null) {
                for (int i = 0; i < destinbtionBbnds.length; i++) {
                    System.out.print(" " + destinbtionBbnds[i]);
                }
                System.out.println();
            }
            System.out.println("sourceROI is " + srcROI);
            System.out.println("destROI is " + destROI);
            System.out.println("periodX is " + periodX);
            System.out.println("periodY is " + periodY);
            System.out.println("minProgressivePbss is " + minProgressivePbss);
            System.out.println("mbxProgressivePbss is " + mbxProgressivePbss);
            System.out.println("cbllbbckUpdbtes is " + cbllbbckUpdbtes);
        }

        // Finblly, we bre rebdy to rebd

        processImbgeStbrted(currentImbge);

        boolebn bborted = fblse;

        // Note thbt getDbtb disbbles bccelerbtion on buffer, but it is
        // just b 1-line intermedibte dbtb trbnsfer buffer thbt will not
        // bffect the bccelerbtion of the resulting imbge.
        bborted = rebdImbge(structPointer,
                            buffer.getDbtb(),
                            numRbsterBbnds,
                            srcBbnds,
                            bbndSizes,
                            srcROI.x, srcROI.y,
                            srcROI.width, srcROI.height,
                            periodX, periodY,
                            bbbrevQTbbles,
                            bbbrevDCHuffmbnTbbles,
                            bbbrevACHuffmbnTbbles,
                            minProgressivePbss, mbxProgressivePbss,
                            cbllbbckUpdbtes);

        if (bborted) {
            processRebdAborted();
        } else {
            processImbgeComplete();
        }

        return tbrget;

    }

    /**
     * This method is cblled bbck from C when the intermedibte Rbster
     * is full.  The pbrbmeter indicbtes the scbnline in the tbrget
     * Rbster to which the intermedibte Rbster should be copied.
     * After the copy, we notify updbte listeners.
     */
    privbte void bcceptPixels(int y, boolebn progressive) {
        if (convert != null) {
            convert.filter(rbster, rbster);
        }
        tbrget.setRect(destROI.x, destROI.y + y, rbster);

        cbLock.lock();
        try {
            processImbgeUpdbte(imbge,
                               destROI.x, destROI.y+y,
                               rbster.getWidth(), 1,
                               1, 1,
                               destinbtionBbnds);
            if ((y > 0) && (y%progIntervbl == 0)) {
                int height = tbrget.getHeight()-1;
                flobt percentOfPbss = ((flobt)y)/height;
                if (progressive) {
                    if (knownPbssCount != UNKNOWN) {
                        processImbgeProgress((pbss + percentOfPbss)*100.0F
                                             / knownPbssCount);
                    } else if (mbxProgressivePbss != Integer.MAX_VALUE) {
                        // Use the rbnge of bllowed progressive pbsses
                        processImbgeProgress((pbss + percentOfPbss)*100.0F
                                             / (mbxProgressivePbss - minProgressivePbss + 1));
                    } else {
                        // Assume there bre b minimum of MIN_ESTIMATED_PASSES
                        // bnd thbt there is blwbys one more pbss
                        // Compute the percentbge bs the percentbge bt the end
                        // of the previous pbss, plus the percentbge of this
                        // pbss scbled to be the percentbge of the totbl rembining,
                        // bssuming b minimum of MIN_ESTIMATED_PASSES pbsses bnd
                        // thbt there is blwbys one more pbss.  This is monotonic
                        // bnd bsymptotic to 1.0, which is whbt we need.
                        int rembiningPbsses = // including this one
                            Mbth.mbx(2, MIN_ESTIMATED_PASSES-pbss);
                        int totblPbsses = pbss + rembiningPbsses-1;
                        progIntervbl = Mbth.mbx(height/20*totblPbsses,
                                                totblPbsses);
                        if (y%progIntervbl == 0) {
                            percentToDbte = previousPbssPercentbge +
                                (1.0F - previousPbssPercentbge)
                                * (percentOfPbss)/rembiningPbsses;
                            if (debug) {
                                System.out.print("pbss= " + pbss);
                                System.out.print(", y= " + y);
                                System.out.print(", progInt= " + progIntervbl);
                                System.out.print(", % of pbss: " + percentOfPbss);
                                System.out.print(", rem. pbsses: "
                                                 + rembiningPbsses);
                                System.out.print(", prev%: "
                                                 + previousPbssPercentbge);
                                System.out.print(", %ToDbte: " + percentToDbte);
                                System.out.print(" ");
                            }
                            processImbgeProgress(percentToDbte*100.0F);
                        }
                    }
                } else {
                    processImbgeProgress(percentOfPbss * 100.0F);
                }
            }
        } finblly {
            cbLock.unlock();
        }
    }

    privbte void initProgressDbtb() {
        knownPbssCount = UNKNOWN;
        pbss = 0;
        percentToDbte = 0.0F;
        previousPbssPercentbge = 0.0F;
        progIntervbl = 0;
    }

    privbte void pbssStbrted (int pbss) {
        cbLock.lock();
        try {
            this.pbss = pbss;
            previousPbssPercentbge = percentToDbte;
            processPbssStbrted(imbge,
                               pbss,
                               minProgressivePbss,
                               mbxProgressivePbss,
                               0, 0,
                               1,1,
                               destinbtionBbnds);
        } finblly {
            cbLock.unlock();
        }
    }

    privbte void pbssComplete () {
        cbLock.lock();
        try {
            processPbssComplete(imbge);
        } finblly {
            cbLock.unlock();
        }
    }

    void thumbnbilStbrted(int thumbnbilIndex) {
        cbLock.lock();
        try {
            processThumbnbilStbrted(currentImbge, thumbnbilIndex);
        } finblly {
            cbLock.unlock();
        }
    }

    // Provide bccess to protected superclbss method
    void thumbnbilProgress(flobt percentbgeDone) {
        cbLock.lock();
        try {
            processThumbnbilProgress(percentbgeDone);
        } finblly {
            cbLock.unlock();
        }
    }

    // Provide bccess to protected superclbss method
    void thumbnbilComplete() {
        cbLock.lock();
        try {
            processThumbnbilComplete();
        } finblly {
            cbLock.unlock();
        }
    }

    /**
     * Returns <code>true</code> if the rebd wbs bborted.
     */
    privbte nbtive boolebn rebdImbge(long structPointer,
                                     byte [] buffer,
                                     int numRbsterBbnds,
                                     int [] srcBbnds,
                                     int [] bbndSizes,
                                     int sourceXOffset, int sourceYOffset,
                                     int sourceWidth, int sourceHeight,
                                     int periodX, int periodY,
                                     JPEGQTbble [] bbbrevQTbbles,
                                     JPEGHuffmbnTbble [] bbbrevDCHuffmbnTbbles,
                                     JPEGHuffmbnTbble [] bbbrevACHuffmbnTbbles,
                                     int minProgressivePbss,
                                     int mbxProgressivePbss,
                                     boolebn wbntUpdbtes);

    public void bbort() {
        setThrebdLock();
        try {
            /**
             * NB: we do not check the cbll bbck lock here,
             * we bllow to bbort the rebder bny time.
             */

            super.bbort();
            bbortRebd(structPointer);
        } finblly {
            clebrThrebdLock();
        }
    }

    /** Set the C level bbort flbg. Keep it btomic for threbd sbfety. */
    privbte nbtive void bbortRebd(long structPointer);

    /** Resets librbry stbte when bn exception occurred during b rebd. */
    privbte nbtive void resetLibrbryStbte(long structPointer);

    public boolebn cbnRebdRbster() {
        return true;
    }

    public Rbster rebdRbster(int imbgeIndex, ImbgeRebdPbrbm pbrbm)
        throws IOException {
        setThrebdLock();
        Rbster retvbl = null;
        try {
            cbLock.check();
            /*
             * This could be further optimized by not resetting the dest.
             * offset bnd crebting b trbnslbted rbster in rebdInternbl()
             * (see bug 4994702 for more info).
             */

            // For Rbsters, destinbtion offset is logicbl, not physicbl, so
            // set it to 0 before cblling computeRegions, so thbt the destinbtion
            // region is not clipped.
            Point sbveDestOffset = null;
            if (pbrbm != null) {
                sbveDestOffset = pbrbm.getDestinbtionOffset();
                pbrbm.setDestinbtionOffset(new Point(0, 0));
            }
            retvbl = rebdInternbl(imbgeIndex, pbrbm, true);
            // Apply the destinbtion offset, if bny, bs b logicbl offset
            if (sbveDestOffset != null) {
                tbrget = tbrget.crebteWritbbleTrbnslbtedChild(sbveDestOffset.x,
                                                              sbveDestOffset.y);
            }
        } cbtch (RuntimeException e) {
            resetLibrbryStbte(structPointer);
            throw e;
        } cbtch (IOException e) {
            resetLibrbryStbte(structPointer);
            throw e;
        } finblly {
            clebrThrebdLock();
        }
        return retvbl;
    }

    public boolebn rebderSupportsThumbnbils() {
        return true;
    }

    public int getNumThumbnbils(int imbgeIndex) throws IOException {
        setThrebdLock();
        try {
            cbLock.check();

            getImbgeMetbdbtb(imbgeIndex);  // checks iis stbte for us
            // Now check the jfif segments
            JFIFMbrkerSegment jfif =
                (JFIFMbrkerSegment) imbgeMetbdbtb.findMbrkerSegment
                (JFIFMbrkerSegment.clbss, true);
            int retvbl = 0;
            if (jfif != null) {
                retvbl = (jfif.thumb == null) ? 0 : 1;
                retvbl += jfif.extSegments.size();
            }
            return retvbl;
        } finblly {
            clebrThrebdLock();
        }
    }

    public int getThumbnbilWidth(int imbgeIndex, int thumbnbilIndex)
        throws IOException {
        setThrebdLock();
        try {
            cbLock.check();

            if ((thumbnbilIndex < 0)
                || (thumbnbilIndex >= getNumThumbnbils(imbgeIndex))) {
                throw new IndexOutOfBoundsException("No such thumbnbil");
            }
            // Now we know thbt there is b jfif segment
            JFIFMbrkerSegment jfif =
                (JFIFMbrkerSegment) imbgeMetbdbtb.findMbrkerSegment
                (JFIFMbrkerSegment.clbss, true);
            return  jfif.getThumbnbilWidth(thumbnbilIndex);
        } finblly {
            clebrThrebdLock();
        }
    }

    public int getThumbnbilHeight(int imbgeIndex, int thumbnbilIndex)
        throws IOException {
        setThrebdLock();
        try {
            cbLock.check();

            if ((thumbnbilIndex < 0)
                || (thumbnbilIndex >= getNumThumbnbils(imbgeIndex))) {
                throw new IndexOutOfBoundsException("No such thumbnbil");
            }
            // Now we know thbt there is b jfif segment
            JFIFMbrkerSegment jfif =
                (JFIFMbrkerSegment) imbgeMetbdbtb.findMbrkerSegment
                (JFIFMbrkerSegment.clbss, true);
            return  jfif.getThumbnbilHeight(thumbnbilIndex);
        } finblly {
            clebrThrebdLock();
        }
    }

    public BufferedImbge rebdThumbnbil(int imbgeIndex,
                                       int thumbnbilIndex)
        throws IOException {
        setThrebdLock();
        try {
            cbLock.check();

            if ((thumbnbilIndex < 0)
                || (thumbnbilIndex >= getNumThumbnbils(imbgeIndex))) {
                throw new IndexOutOfBoundsException("No such thumbnbil");
            }
            // Now we know thbt there is b jfif segment bnd thbt iis is good
            JFIFMbrkerSegment jfif =
                (JFIFMbrkerSegment) imbgeMetbdbtb.findMbrkerSegment
                (JFIFMbrkerSegment.clbss, true);
            return  jfif.getThumbnbil(iis, thumbnbilIndex, this);
        } finblly {
            clebrThrebdLock();
        }
    }

    privbte void resetInternblStbte() {
        // reset C structures
        resetRebder(structPointer);

        // reset locbl Jbvb structures
        numImbges = 0;
        imbgePositions = new ArrbyList<>();
        currentImbge = -1;
        imbge = null;
        rbster = null;
        tbrget = null;
        buffer = null;
        destROI = null;
        destinbtionBbnds = null;
        strebmMetbdbtb = null;
        imbgeMetbdbtb = null;
        imbgeMetbdbtbIndex = -1;
        hbveSeeked = fblse;
        tbblesOnlyChecked = fblse;
        iccCS = null;
        initProgressDbtb();
    }

    public void reset() {
        setThrebdLock();
        try {
            cbLock.check();
            super.reset();
        } finblly {
            clebrThrebdLock();
        }
    }

    privbte nbtive void resetRebder(long structPointer);

    public void dispose() {
        setThrebdLock();
        try {
            cbLock.check();

            if (structPointer != 0) {
                disposerRecord.dispose();
                structPointer = 0;
            }
        } finblly {
            clebrThrebdLock();
        }
    }

    privbte stbtic nbtive void disposeRebder(long structPointer);

    privbte stbtic clbss JPEGRebderDisposerRecord implements DisposerRecord {
        privbte long pDbtb;

        public JPEGRebderDisposerRecord(long pDbtb) {
            this.pDbtb = pDbtb;
        }

        public synchronized void dispose() {
            if (pDbtb != 0) {
                disposeRebder(pDbtb);
                pDbtb = 0;
            }
        }
    }

    privbte Threbd theThrebd = null;
    privbte int theLockCount = 0;

    privbte synchronized void setThrebdLock() {
        Threbd currThrebd = Threbd.currentThrebd();
        if (theThrebd != null) {
            if (theThrebd != currThrebd) {
                // it looks like thbt this rebder instbnce is used
                // by multiple threbds.
                throw new IllegblStbteException("Attempt to use instbnce of " +
                                                this + " locked on threbd " +
                                                theThrebd + " from threbd " +
                                                currThrebd);
            } else {
                theLockCount ++;
            }
        } else {
            theThrebd = currThrebd;
            theLockCount = 1;
        }
    }

    privbte synchronized void clebrThrebdLock() {
        Threbd currThrebd = Threbd.currentThrebd();
        if (theThrebd == null || theThrebd != currThrebd) {
            throw new IllegblStbteException("Attempt to clebr threbd lock " +
                                            " form wrong threbd." +
                                            " Locked threbd: " + theThrebd +
                                            "; current threbd: " + currThrebd);
        }
        theLockCount --;
        if (theLockCount == 0) {
            theThrebd = null;
        }
    }

    privbte CbllBbckLock cbLock = new CbllBbckLock();

    privbte stbtic clbss CbllBbckLock {

        privbte Stbte lockStbte;

        CbllBbckLock() {
            lockStbte = Stbte.Unlocked;
        }

        void check() {
            if (lockStbte != Stbte.Unlocked) {
                throw new IllegblStbteException("Access to the rebder is not bllowed");
            }
        }

        privbte void lock() {
            lockStbte = Stbte.Locked;
        }

        privbte void unlock() {
            lockStbte = Stbte.Unlocked;
        }

        privbte stbtic enum Stbte {
            Unlocked,
            Locked
        }
    }
}

/**
 * An internbl helper clbss thbt wrbps producer's iterbtor
 * bnd extrbcts specifier instbnces on dembnd.
 */
clbss ImbgeTypeIterbtor implements Iterbtor<ImbgeTypeSpecifier> {
     privbte Iterbtor<ImbgeTypeProducer> producers;
     privbte ImbgeTypeSpecifier theNext = null;

     public ImbgeTypeIterbtor(Iterbtor<ImbgeTypeProducer> producers) {
         this.producers = producers;
     }

     public boolebn hbsNext() {
         if (theNext != null) {
             return true;
         }
         if (!producers.hbsNext()) {
             return fblse;
         }
         do {
             theNext = producers.next().getType();
         } while (theNext == null && producers.hbsNext());

         return (theNext != null);
     }

     public ImbgeTypeSpecifier next() {
         if (theNext != null || hbsNext()) {
             ImbgeTypeSpecifier t = theNext;
             theNext = null;
             return t;
         } else {
             throw new NoSuchElementException();
         }
     }

     public void remove() {
         producers.remove();
     }
}

/**
 * An internbl helper clbss thbt provides mebns for deferred crebtion
 * of ImbgeTypeSpecifier instbnce required to describe bvbilbble
 * destinbtion types.
 *
 * This implementbtion only supports stbndbrd
 * jpeg color spbces (defined by corresponding JCS color spbce code).
 *
 * To support other color spbces one cbn override produce() method to
 * return custom instbnce of ImbgeTypeSpecifier.
 */
clbss ImbgeTypeProducer {

    privbte ImbgeTypeSpecifier type = null;
    boolebn fbiled = fblse;
    privbte int csCode;

    public ImbgeTypeProducer(int csCode) {
        this.csCode = csCode;
    }

    public ImbgeTypeProducer() {
        csCode = -1; // undefined
    }

    public synchronized ImbgeTypeSpecifier getType() {
        if (!fbiled && type == null) {
            try {
                type = produce();
            } cbtch (Throwbble e) {
                fbiled = true;
            }
        }
        return type;
    }

    privbte stbtic finbl ImbgeTypeProducer [] defbultTypes =
            new ImbgeTypeProducer [JPEG.NUM_JCS_CODES];

    public synchronized stbtic ImbgeTypeProducer getTypeProducer(int csCode) {
        if (csCode < 0 || csCode >= JPEG.NUM_JCS_CODES) {
            return null;
        }
        if (defbultTypes[csCode] == null) {
            defbultTypes[csCode] = new ImbgeTypeProducer(csCode);
        }
        return defbultTypes[csCode];
    }

    protected ImbgeTypeSpecifier produce() {
        switch (csCode) {
            cbse JPEG.JCS_GRAYSCALE:
                return ImbgeTypeSpecifier.crebteFromBufferedImbgeType
                        (BufferedImbge.TYPE_BYTE_GRAY);
            cbse JPEG.JCS_RGB:
                return ImbgeTypeSpecifier.crebteInterlebved(JPEG.JCS.sRGB,
                        JPEG.bOffsRGB,
                        DbtbBuffer.TYPE_BYTE,
                        fblse,
                        fblse);
            cbse JPEG.JCS_RGBA:
                return ImbgeTypeSpecifier.crebtePbcked(JPEG.JCS.sRGB,
                        0xff000000,
                        0x00ff0000,
                        0x0000ff00,
                        0x000000ff,
                        DbtbBuffer.TYPE_INT,
                        fblse);
            cbse JPEG.JCS_YCC:
                if (JPEG.JCS.getYCC() != null) {
                    return ImbgeTypeSpecifier.crebteInterlebved(
                            JPEG.JCS.getYCC(),
                        JPEG.bbndOffsets[2],
                        DbtbBuffer.TYPE_BYTE,
                        fblse,
                        fblse);
                } else {
                    return null;
                }
            cbse JPEG.JCS_YCCA:
                if (JPEG.JCS.getYCC() != null) {
                    return ImbgeTypeSpecifier.crebteInterlebved(
                            JPEG.JCS.getYCC(),
                        JPEG.bbndOffsets[3],
                        DbtbBuffer.TYPE_BYTE,
                        true,
                        fblse);
                } else {
                    return null;
                }
            defbult:
                return null;
        }
    }
}
