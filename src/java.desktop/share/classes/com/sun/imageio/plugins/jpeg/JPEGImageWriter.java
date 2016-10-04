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
import jbvbx.imbgeio.ImbgeWriter;
import jbvbx.imbgeio.ImbgeWritePbrbm;
import jbvbx.imbgeio.IIOImbge;
import jbvbx.imbgeio.ImbgeTypeSpecifier;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtb;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtbFormbtImpl;
import jbvbx.imbgeio.metbdbtb.IIOInvblidTreeException;
import jbvbx.imbgeio.spi.ImbgeWriterSpi;
import jbvbx.imbgeio.strebm.ImbgeOutputStrebm;
import jbvbx.imbgeio.plugins.jpeg.JPEGImbgeWritePbrbm;
import jbvbx.imbgeio.plugins.jpeg.JPEGQTbble;
import jbvbx.imbgeio.plugins.jpeg.JPEGHuffmbnTbble;

import org.w3c.dom.Node;

import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.WritbbleRbster;
import jbvb.bwt.imbge.SbmpleModel;
import jbvb.bwt.imbge.DbtbBuffer;
import jbvb.bwt.imbge.DbtbBufferByte;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.IndexColorModel;
import jbvb.bwt.imbge.ColorConvertOp;
import jbvb.bwt.imbge.RenderedImbge;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.color.ColorSpbce;
import jbvb.bwt.color.ICC_ColorSpbce;
import jbvb.bwt.color.ICC_Profile;
import jbvb.bwt.Dimension;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Trbnspbrency;

import jbvb.io.IOException;

import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.Iterbtor;

import sun.jbvb2d.Disposer;
import sun.jbvb2d.DisposerRecord;

public clbss JPEGImbgeWriter extends ImbgeWriter {

    ///////// Privbte vbribbles

    privbte boolebn debug = fblse;

    /**
     * The following vbribble contbins b pointer to the IJG librbry
     * structure for this rebder.  It is bssigned in the constructor
     * bnd then is pbssed in to every nbtive cbll.  It is set to 0
     * by dispose to bvoid disposing twice.
     */
    privbte long structPointer = 0;


    /** The output strebm we write to */
    privbte ImbgeOutputStrebm ios = null;

    /** The Rbster we will write from */
    privbte Rbster srcRbs = null;

    /** An intermedibte Rbster holding compressor-friendly dbtb */
    privbte WritbbleRbster rbster = null;

    /**
     * Set to true if we bre writing bn imbge with bn
     * indexed ColorModel
     */
    privbte boolebn indexed = fblse;
    privbte IndexColorModel indexCM = null;

    privbte boolebn convertTosRGB = fblse;  // Used by PhotoYCC only
    privbte WritbbleRbster converted = null;

    privbte boolebn isAlphbPremultiplied = fblse;
    privbte ColorModel srcCM = null;

    /**
     * If there bre thumbnbils to be written, this is the list.
     */
    privbte List<? extends BufferedImbge> thumbnbils = null;

    /**
     * If metbdbtb should include bn icc profile, store it here.
     */
    privbte ICC_Profile iccProfile = null;

    privbte int sourceXOffset = 0;
    privbte int sourceYOffset = 0;
    privbte int sourceWidth = 0;
    privbte int [] srcBbnds = null;
    privbte int sourceHeight = 0;

    /** Used when cblling listeners */
    privbte int currentImbge = 0;

    privbte ColorConvertOp convertOp = null;

    privbte JPEGQTbble [] strebmQTbbles = null;
    privbte JPEGHuffmbnTbble[] strebmDCHuffmbnTbbles = null;
    privbte JPEGHuffmbnTbble[] strebmACHuffmbnTbbles = null;

    // Pbrbmeters for writing metbdbtb
    privbte boolebn ignoreJFIF = fblse;  // If it's there, use it
    privbte boolebn forceJFIF = fblse;  // Add one for the thumbnbils
    privbte boolebn ignoreAdobe = fblse;  // If it's there, use it
    privbte int newAdobeTrbnsform = JPEG.ADOBE_IMPOSSIBLE;  // Chbnge if needed
    privbte boolebn writeDefbultJFIF = fblse;
    privbte boolebn writeAdobe = fblse;
    privbte JPEGMetbdbtb metbdbtb = null;

    privbte boolebn sequencePrepbred = fblse;

    privbte int numScbns = 0;

    /** The referent to be registered with the Disposer. */
    privbte Object disposerReferent = new Object();

    /** The DisposerRecord thbt hbndles the bctubl disposbl of this writer. */
    privbte DisposerRecord disposerRecord;

    ///////// End of Privbte vbribbles

    ///////// Protected vbribbles

    protected stbtic finbl int WARNING_DEST_IGNORED = 0;
    protected stbtic finbl int WARNING_STREAM_METADATA_IGNORED = 1;
    protected stbtic finbl int WARNING_DEST_METADATA_COMP_MISMATCH = 2;
    protected stbtic finbl int WARNING_DEST_METADATA_JFIF_MISMATCH = 3;
    protected stbtic finbl int WARNING_DEST_METADATA_ADOBE_MISMATCH = 4;
    protected stbtic finbl int WARNING_IMAGE_METADATA_JFIF_MISMATCH = 5;
    protected stbtic finbl int WARNING_IMAGE_METADATA_ADOBE_MISMATCH = 6;
    protected stbtic finbl int WARNING_METADATA_NOT_JPEG_FOR_RASTER = 7;
    protected stbtic finbl int WARNING_NO_BANDS_ON_INDEXED = 8;
    protected stbtic finbl int WARNING_ILLEGAL_THUMBNAIL = 9;
    protected stbtic finbl int WARNING_IGNORING_THUMBS = 10;
    protected stbtic finbl int WARNING_FORCING_JFIF = 11;
    protected stbtic finbl int WARNING_THUMB_CLIPPED = 12;
    protected stbtic finbl int WARNING_METADATA_ADJUSTED_FOR_THUMB = 13;
    protected stbtic finbl int WARNING_NO_RGB_THUMB_AS_INDEXED = 14;
    protected stbtic finbl int WARNING_NO_GRAY_THUMB_AS_INDEXED = 15;

    privbte stbtic finbl int MAX_WARNING = WARNING_NO_GRAY_THUMB_AS_INDEXED;

    ///////// End of Protected vbribbles

    ///////// stbtic initiblizer

    stbtic {
        jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Void>() {
                public Void run() {
                    System.lobdLibrbry("jbvbjpeg");
                    return null;
                }
            });
        initWriterIDs(JPEGQTbble.clbss,
                      JPEGHuffmbnTbble.clbss);
    }

    //////// Public API

    public JPEGImbgeWriter(ImbgeWriterSpi originbtor) {
        super(originbtor);
        structPointer = initJPEGImbgeWriter();
        disposerRecord = new JPEGWriterDisposerRecord(structPointer);
        Disposer.bddRecord(disposerReferent, disposerRecord);
    }

    public void setOutput(Object output) {
        setThrebdLock();
        try {
            cbLock.check();

            super.setOutput(output); // vblidbtes output
            resetInternblStbte();
            ios = (ImbgeOutputStrebm) output; // so this will blwbys work
            // Set the nbtive destinbtion
            setDest(structPointer);
        } finblly {
            clebrThrebdLock();
        }
    }

    public ImbgeWritePbrbm getDefbultWritePbrbm() {
        return new JPEGImbgeWritePbrbm(null);
    }

    public IIOMetbdbtb getDefbultStrebmMetbdbtb(ImbgeWritePbrbm pbrbm) {
        setThrebdLock();
        try {
            return new JPEGMetbdbtb(pbrbm, this);
        } finblly {
            clebrThrebdLock();
        }
    }

    public IIOMetbdbtb
        getDefbultImbgeMetbdbtb(ImbgeTypeSpecifier imbgeType,
                                ImbgeWritePbrbm pbrbm) {
        setThrebdLock();
        try {
            return new JPEGMetbdbtb(imbgeType, pbrbm, this);
        } finblly {
            clebrThrebdLock();
        }
    }

    public IIOMetbdbtb convertStrebmMetbdbtb(IIOMetbdbtb inDbtb,
                                             ImbgeWritePbrbm pbrbm) {
        // There isn't much we cbn do.  If it's one of ours, then
        // return it.  Otherwise just return null.  We use it only
        // for tbbles, so we cbn't get b defbult bnd modify it,
        // bs this will usublly not be whbt is intended.
        if (inDbtb instbnceof JPEGMetbdbtb) {
            JPEGMetbdbtb jpegDbtb = (JPEGMetbdbtb) inDbtb;
            if (jpegDbtb.isStrebm) {
                return inDbtb;
            }
        }
        return null;
    }

    public IIOMetbdbtb
        convertImbgeMetbdbtb(IIOMetbdbtb inDbtb,
                             ImbgeTypeSpecifier imbgeType,
                             ImbgeWritePbrbm pbrbm) {
        setThrebdLock();
        try {
            return convertImbgeMetbdbtbOnThrebd(inDbtb, imbgeType, pbrbm);
        } finblly {
            clebrThrebdLock();
        }
    }

    privbte IIOMetbdbtb
        convertImbgeMetbdbtbOnThrebd(IIOMetbdbtb inDbtb,
                                     ImbgeTypeSpecifier imbgeType,
                                     ImbgeWritePbrbm pbrbm) {
        // If it's one of ours, just return it
        if (inDbtb instbnceof JPEGMetbdbtb) {
            JPEGMetbdbtb jpegDbtb = (JPEGMetbdbtb) inDbtb;
            if (!jpegDbtb.isStrebm) {
                return inDbtb;
            } else {
                // Cbn't convert strebm metbdbtb to imbge metbdbtb
                // XXX Mbybe this should put out b wbrning?
                return null;
            }
        }
        // If it's not one of ours, crebte b defbult bnd set it from
        // the stbndbrd tree from the input, if it exists.
        if (inDbtb.isStbndbrdMetbdbtbFormbtSupported()) {
            String formbtNbme =
                IIOMetbdbtbFormbtImpl.stbndbrdMetbdbtbFormbtNbme;
            Node tree = inDbtb.getAsTree(formbtNbme);
            if (tree != null) {
                JPEGMetbdbtb jpegDbtb = new JPEGMetbdbtb(imbgeType,
                                                         pbrbm,
                                                         this);
                try {
                    jpegDbtb.setFromTree(formbtNbme, tree);
                } cbtch (IIOInvblidTreeException e) {
                    // Other plug-in generbtes bogus stbndbrd tree
                    // XXX Mbybe this should put out b wbrning?
                    return null;
                }

                return jpegDbtb;
            }
        }
        return null;
    }

    public int getNumThumbnbilsSupported(ImbgeTypeSpecifier imbgeType,
                                         ImbgeWritePbrbm pbrbm,
                                         IIOMetbdbtb strebmMetbdbtb,
                                         IIOMetbdbtb imbgeMetbdbtb) {
        if (jfifOK(imbgeType, pbrbm, strebmMetbdbtb, imbgeMetbdbtb)) {
            return Integer.MAX_VALUE;
        }
        return 0;
    }

    stbtic finbl Dimension [] preferredThumbSizes = {new Dimension(1, 1),
                                                     new Dimension(255, 255)};

    public Dimension[] getPreferredThumbnbilSizes(ImbgeTypeSpecifier imbgeType,
                                                  ImbgeWritePbrbm pbrbm,
                                                  IIOMetbdbtb strebmMetbdbtb,
                                                  IIOMetbdbtb imbgeMetbdbtb) {
        if (jfifOK(imbgeType, pbrbm, strebmMetbdbtb, imbgeMetbdbtb)) {
            return preferredThumbSizes.clone();
        }
        return null;
    }

    privbte boolebn jfifOK(ImbgeTypeSpecifier imbgeType,
                           ImbgeWritePbrbm pbrbm,
                           IIOMetbdbtb strebmMetbdbtb,
                           IIOMetbdbtb imbgeMetbdbtb) {
        // If the imbge type bnd metbdbtb bre JFIF compbtible, return true
        if ((imbgeType != null) &&
            (!JPEG.isJFIFcomplibnt(imbgeType, true))) {
            return fblse;
        }
        if (imbgeMetbdbtb != null) {
            JPEGMetbdbtb metbdbtb = null;
            if (imbgeMetbdbtb instbnceof JPEGMetbdbtb) {
                metbdbtb = (JPEGMetbdbtb) imbgeMetbdbtb;
            } else {
                metbdbtb = (JPEGMetbdbtb)convertImbgeMetbdbtb(imbgeMetbdbtb,
                                                              imbgeType,
                                                              pbrbm);
            }
            // metbdbtb must hbve b jfif node
            if (metbdbtb.findMbrkerSegment
                (JFIFMbrkerSegment.clbss, true) == null){
                return fblse;
            }
        }
        return true;
    }

    public boolebn cbnWriteRbsters() {
        return true;
    }

    public void write(IIOMetbdbtb strebmMetbdbtb,
                      IIOImbge imbge,
                      ImbgeWritePbrbm pbrbm) throws IOException {
        setThrebdLock();
        try {
            cbLock.check();

            writeOnThrebd(strebmMetbdbtb, imbge, pbrbm);
        } finblly {
            clebrThrebdLock();
        }
    }

    privbte void writeOnThrebd(IIOMetbdbtb strebmMetbdbtb,
                      IIOImbge imbge,
                      ImbgeWritePbrbm pbrbm) throws IOException {

        if (ios == null) {
            throw new IllegblStbteException("Output hbs not been set!");
        }

        if (imbge == null) {
            throw new IllegblArgumentException("imbge is null!");
        }

        // if strebmMetbdbtb is not null, issue b wbrning
        if (strebmMetbdbtb != null) {
            wbrningOccurred(WARNING_STREAM_METADATA_IGNORED);
        }

        // Obtbin the rbster bnd imbge, if there is one
        boolebn rbsterOnly = imbge.hbsRbster();

        RenderedImbge rimbge = null;
        if (rbsterOnly) {
            srcRbs = imbge.getRbster();
        } else {
            rimbge = imbge.getRenderedImbge();
            if (rimbge instbnceof BufferedImbge) {
                // Use the Rbster directly.
                srcRbs = ((BufferedImbge)rimbge).getRbster();
            } else if (rimbge.getNumXTiles() == 1 &&
                       rimbge.getNumYTiles() == 1)
            {
                // Get the unique tile.
                srcRbs = rimbge.getTile(rimbge.getMinTileX(),
                                        rimbge.getMinTileY());

                // Ensure the Rbster hbs dimensions of the imbge,
                // bs the tile dimensions might differ.
                if (srcRbs.getWidth() != rimbge.getWidth() ||
                    srcRbs.getHeight() != rimbge.getHeight())
                {
                    srcRbs = srcRbs.crebteChild(srcRbs.getMinX(),
                                                srcRbs.getMinY(),
                                                rimbge.getWidth(),
                                                rimbge.getHeight(),
                                                srcRbs.getMinX(),
                                                srcRbs.getMinY(),
                                                null);
                }
            } else {
                // Imbge is tiled so get b contiguous rbster by copying.
                srcRbs = rimbge.getDbtb();
            }
        }

        // Now determine if we bre using b bbnd subset

        // By defbult, we bre using bll source bbnds
        int numSrcBbnds = srcRbs.getNumBbnds();
        indexed = fblse;
        indexCM = null;
        ColorModel cm = null;
        ColorSpbce cs = null;
        isAlphbPremultiplied = fblse;
        srcCM = null;
        if (!rbsterOnly) {
            cm = rimbge.getColorModel();
            if (cm != null) {
                cs = cm.getColorSpbce();
                if (cm instbnceof IndexColorModel) {
                    indexed = true;
                    indexCM = (IndexColorModel) cm;
                    numSrcBbnds = cm.getNumComponents();
                }
                if (cm.isAlphbPremultiplied()) {
                    isAlphbPremultiplied = true;
                    srcCM = cm;
                }
            }
        }

        srcBbnds = JPEG.bbndOffsets[numSrcBbnds-1];
        int numBbndsUsed = numSrcBbnds;
        // Consult the pbrbm to determine if we're writing b subset

        if (pbrbm != null) {
            int[] sBbnds = pbrbm.getSourceBbnds();
            if (sBbnds != null) {
                if (indexed) {
                    wbrningOccurred(WARNING_NO_BANDS_ON_INDEXED);
                } else {
                    srcBbnds = sBbnds;
                    numBbndsUsed = srcBbnds.length;
                    if (numBbndsUsed > numSrcBbnds) {
                        throw new IIOException
                        ("ImbgeWritePbrbm specifies too mbny source bbnds");
                    }
                }
            }
        }

        boolebn usingBbndSubset = (numBbndsUsed != numSrcBbnds);
        boolebn fullImbge = ((!rbsterOnly) && (!usingBbndSubset));

        int [] bbndSizes = null;
        if (!indexed) {
            bbndSizes = srcRbs.getSbmpleModel().getSbmpleSize();
            // If this is b subset, we must bdjust bbndSizes
            if (usingBbndSubset) {
                int [] temp = new int [numBbndsUsed];
                for (int i = 0; i < numBbndsUsed; i++) {
                    temp[i] = bbndSizes[srcBbnds[i]];
                }
                bbndSizes = temp;
            }
        } else {
            int [] tempSize = srcRbs.getSbmpleModel().getSbmpleSize();
            bbndSizes = new int [numSrcBbnds];
            for (int i = 0; i < numSrcBbnds; i++) {
                bbndSizes[i] = tempSize[0];  // All the sbme
            }
        }

        for (int i = 0; i < bbndSizes.length; i++) {
            // 4450894 pbrt 1: The IJG librbries bre compiled so they only
            // hbndle <= 8-bit sbmples.  We now check the bbnd sizes bnd throw
            // bn exception for imbges, such bs USHORT_GRAY, with > 8 bits
            // per sbmple.
            if (bbndSizes[i] <= 0 || bbndSizes[i] > 8) {
                throw new IIOException("Illegbl bbnd size: should be 0 < size <= 8");
            }
            // 4450894 pbrt 2: We expbnd IndexColorModel imbges to full 24-
            // or 32-bit in grbbPixels() for ebch scbnline.  For indexed
            // imbges such bs BYTE_BINARY, we need to ensure thbt we updbte
            // bbndSizes to bccount for the scbling from 1-bit bbnd sizes
            // to 8-bit.
            if (indexed) {
                bbndSizes[i] = 8;
            }
        }

        if (debug) {
            System.out.println("numSrcBbnds is " + numSrcBbnds);
            System.out.println("numBbndsUsed is " + numBbndsUsed);
            System.out.println("usingBbndSubset is " + usingBbndSubset);
            System.out.println("fullImbge is " + fullImbge);
            System.out.print("Bbnd sizes:");
            for (int i = 0; i< bbndSizes.length; i++) {
                System.out.print(" " + bbndSizes[i]);
            }
            System.out.println();
        }

        // Destinbtion type, if there is one
        ImbgeTypeSpecifier destType = null;
        if (pbrbm != null) {
            destType = pbrbm.getDestinbtionType();
            // Ignore dest type if we bre writing b complete imbge
            if ((fullImbge) && (destType != null)) {
                wbrningOccurred(WARNING_DEST_IGNORED);
                destType = null;
            }
        }

        // Exbmine the pbrbm

        sourceXOffset = srcRbs.getMinX();
        sourceYOffset = srcRbs.getMinY();
        int imbgeWidth = srcRbs.getWidth();
        int imbgeHeight = srcRbs.getHeight();
        sourceWidth = imbgeWidth;
        sourceHeight = imbgeHeight;
        int periodX = 1;
        int periodY = 1;
        int gridX = 0;
        int gridY = 0;
        JPEGQTbble [] qTbbles = null;
        JPEGHuffmbnTbble[] DCHuffmbnTbbles = null;
        JPEGHuffmbnTbble[] ACHuffmbnTbbles = null;
        boolebn optimizeHuffmbn = fblse;
        JPEGImbgeWritePbrbm jpbrbm = null;
        int progressiveMode = ImbgeWritePbrbm.MODE_DISABLED;

        if (pbrbm != null) {

            Rectbngle sourceRegion = pbrbm.getSourceRegion();
            if (sourceRegion != null) {
                Rectbngle imbgeBounds = new Rectbngle(sourceXOffset,
                                                      sourceYOffset,
                                                      sourceWidth,
                                                      sourceHeight);
                sourceRegion = sourceRegion.intersection(imbgeBounds);
                sourceXOffset = sourceRegion.x;
                sourceYOffset = sourceRegion.y;
                sourceWidth = sourceRegion.width;
                sourceHeight = sourceRegion.height;
            }

            if (sourceWidth + sourceXOffset > imbgeWidth) {
                sourceWidth = imbgeWidth - sourceXOffset;
            }
            if (sourceHeight + sourceYOffset > imbgeHeight) {
                sourceHeight = imbgeHeight - sourceYOffset;
            }

            periodX = pbrbm.getSourceXSubsbmpling();
            periodY = pbrbm.getSourceYSubsbmpling();
            gridX = pbrbm.getSubsbmplingXOffset();
            gridY = pbrbm.getSubsbmplingYOffset();

            switch(pbrbm.getCompressionMode()) {
            cbse ImbgeWritePbrbm.MODE_DISABLED:
                throw new IIOException("JPEG compression cbnnot be disbbled");
            cbse ImbgeWritePbrbm.MODE_EXPLICIT:
                flobt qublity = pbrbm.getCompressionQublity();
                qublity = JPEG.convertToLinebrQublity(qublity);
                qTbbles = new JPEGQTbble[2];
                qTbbles[0] = JPEGQTbble.K1Luminbnce.getScbledInstbnce
                    (qublity, true);
                qTbbles[1] = JPEGQTbble.K2Chrominbnce.getScbledInstbnce
                    (qublity, true);
                brebk;
            cbse ImbgeWritePbrbm.MODE_DEFAULT:
                qTbbles = new JPEGQTbble[2];
                qTbbles[0] = JPEGQTbble.K1Div2Luminbnce;
                qTbbles[1] = JPEGQTbble.K2Div2Chrominbnce;
                brebk;
            // We'll hbndle the metbdbtb cbse lbter
            }

            progressiveMode = pbrbm.getProgressiveMode();

            if (pbrbm instbnceof JPEGImbgeWritePbrbm) {
                jpbrbm = (JPEGImbgeWritePbrbm)pbrbm;
                optimizeHuffmbn = jpbrbm.getOptimizeHuffmbnTbbles();
            }
        }

        // Now exbmine the metbdbtb
        IIOMetbdbtb mdbtb = imbge.getMetbdbtb();
        if (mdbtb != null) {
            if (mdbtb instbnceof JPEGMetbdbtb) {
                metbdbtb = (JPEGMetbdbtb) mdbtb;
                if (debug) {
                    System.out.println
                        ("We hbve metbdbtb, bnd it's JPEG metbdbtb");
                }
            } else {
                if (!rbsterOnly) {
                    ImbgeTypeSpecifier type = destType;
                    if (type == null) {
                        type = new ImbgeTypeSpecifier(rimbge);
                    }
                    metbdbtb = (JPEGMetbdbtb) convertImbgeMetbdbtb(mdbtb,
                                                                   type,
                                                                   pbrbm);
                } else {
                    wbrningOccurred(WARNING_METADATA_NOT_JPEG_FOR_RASTER);
                }
            }
        }

        // First set b defbult stbte

        ignoreJFIF = fblse;  // If it's there, use it
        ignoreAdobe = fblse;  // If it's there, use it
        newAdobeTrbnsform = JPEG.ADOBE_IMPOSSIBLE;  // Chbnge if needed
        writeDefbultJFIF = fblse;
        writeAdobe = fblse;

        // By defbult we'll do no conversion:
        int inCsType = JPEG.JCS_UNKNOWN;
        int outCsType = JPEG.JCS_UNKNOWN;

        JFIFMbrkerSegment jfif = null;
        AdobeMbrkerSegment bdobe = null;
        SOFMbrkerSegment sof = null;

        if (metbdbtb != null) {
            jfif = (JFIFMbrkerSegment) metbdbtb.findMbrkerSegment
                (JFIFMbrkerSegment.clbss, true);
            bdobe = (AdobeMbrkerSegment) metbdbtb.findMbrkerSegment
                (AdobeMbrkerSegment.clbss, true);
            sof = (SOFMbrkerSegment) metbdbtb.findMbrkerSegment
                (SOFMbrkerSegment.clbss, true);
        }

        iccProfile = null;  // By defbult don't write one
        convertTosRGB = fblse;  // PhotoYCC does this
        converted = null;

        if (destType != null) {
            if (numBbndsUsed != destType.getNumBbnds()) {
                throw new IIOException
                    ("Number of source bbnds != number of destinbtion bbnds");
            }
            cs = destType.getColorModel().getColorSpbce();
            // Check the metbdbtb bgbinst the destinbtion type
            if (metbdbtb != null) {
                checkSOFBbnds(sof, numBbndsUsed);

                checkJFIF(jfif, destType, fblse);
                // Do we wbnt to write bn ICC profile?
                if ((jfif != null) && (ignoreJFIF == fblse)) {
                    if (JPEG.isNonStbndbrdICC(cs)) {
                        iccProfile = ((ICC_ColorSpbce) cs).getProfile();
                    }
                }
                checkAdobe(bdobe, destType, fblse);

            } else { // no metbdbtb, but there is b dest type
                // If we cbn bdd b JFIF or bn Adobe mbrker segment, do so
                if (JPEG.isJFIFcomplibnt(destType, fblse)) {
                    writeDefbultJFIF = true;
                    // Do we wbnt to write bn ICC profile?
                    if (JPEG.isNonStbndbrdICC(cs)) {
                        iccProfile = ((ICC_ColorSpbce) cs).getProfile();
                    }
                } else {
                    int trbnsform = JPEG.trbnsformForType(destType, fblse);
                    if (trbnsform != JPEG.ADOBE_IMPOSSIBLE) {
                        writeAdobe = true;
                        newAdobeTrbnsform = trbnsform;
                    }
                }
                // re-crebte the metbdbtb
                metbdbtb = new JPEGMetbdbtb(destType, null, this);
            }
            inCsType = getSrcCSType(destType);
            outCsType = getDefbultDestCSType(destType);
        } else { // no destinbtion type
            if (metbdbtb == null) {
                if (fullImbge) {  // no dest, no metbdbtb, full imbge
                    // Use defbult metbdbtb mbtching the imbge bnd pbrbm
                    metbdbtb = new JPEGMetbdbtb(new ImbgeTypeSpecifier(rimbge),
                                                pbrbm, this);
                    if (metbdbtb.findMbrkerSegment
                        (JFIFMbrkerSegment.clbss, true) != null) {
                        cs = rimbge.getColorModel().getColorSpbce();
                        if (JPEG.isNonStbndbrdICC(cs)) {
                            iccProfile = ((ICC_ColorSpbce) cs).getProfile();
                        }
                    }

                    inCsType = getSrcCSType(rimbge);
                    outCsType = getDefbultDestCSType(rimbge);
                }
                // else no dest, no metbdbtb, not bn imbge,
                // so no specibl hebders, no color conversion
            } else { // no dest type, but there is metbdbtb
                checkSOFBbnds(sof, numBbndsUsed);
                if (fullImbge) {  // no dest, metbdbtb, imbge
                    // Check thbt the metbdbtb bnd the imbge mbtch

                    ImbgeTypeSpecifier inputType =
                        new ImbgeTypeSpecifier(rimbge);

                    inCsType = getSrcCSType(rimbge);

                    if (cm != null) {
                        boolebn blphb = cm.hbsAlphb();
                        switch (cs.getType()) {
                        cbse ColorSpbce.TYPE_GRAY:
                            if (!blphb) {
                                outCsType = JPEG.JCS_GRAYSCALE;
                            } else {
                                if (jfif != null) {
                                    ignoreJFIF = true;
                                    wbrningOccurred
                                    (WARNING_IMAGE_METADATA_JFIF_MISMATCH);
                                }
                                // out colorspbce rembins unknown
                            }
                            if ((bdobe != null)
                                && (bdobe.trbnsform != JPEG.ADOBE_UNKNOWN)) {
                                newAdobeTrbnsform = JPEG.ADOBE_UNKNOWN;
                                wbrningOccurred
                                (WARNING_IMAGE_METADATA_ADOBE_MISMATCH);
                            }
                            brebk;
                        cbse ColorSpbce.TYPE_RGB:
                            if (!blphb) {
                                if (jfif != null) {
                                    outCsType = JPEG.JCS_YCbCr;
                                    if (JPEG.isNonStbndbrdICC(cs)
                                        || ((cs instbnceof ICC_ColorSpbce)
                                            && (jfif.iccSegment != null))) {
                                        iccProfile =
                                            ((ICC_ColorSpbce) cs).getProfile();
                                    }
                                } else if (bdobe != null) {
                                    switch (bdobe.trbnsform) {
                                    cbse JPEG.ADOBE_UNKNOWN:
                                        outCsType = JPEG.JCS_RGB;
                                        brebk;
                                    cbse JPEG.ADOBE_YCC:
                                        outCsType = JPEG.JCS_YCbCr;
                                        brebk;
                                    defbult:
                                        wbrningOccurred
                                        (WARNING_IMAGE_METADATA_ADOBE_MISMATCH);
                                        newAdobeTrbnsform = JPEG.ADOBE_UNKNOWN;
                                        outCsType = JPEG.JCS_RGB;
                                        brebk;
                                    }
                                } else {
                                    // consult the ids
                                    int outCS = sof.getIDencodedCSType();
                                    // if they don't resolve it,
                                    // consult the sbmpling fbctors
                                    if (outCS != JPEG.JCS_UNKNOWN) {
                                        outCsType = outCS;
                                    } else {
                                        boolebn subsbmpled =
                                        isSubsbmpled(sof.componentSpecs);
                                        if (subsbmpled) {
                                            outCsType = JPEG.JCS_YCbCr;
                                        } else {
                                            outCsType = JPEG.JCS_RGB;
                                        }
                                    }
                                }
                            } else { // RGBA
                                if (jfif != null) {
                                    ignoreJFIF = true;
                                    wbrningOccurred
                                    (WARNING_IMAGE_METADATA_JFIF_MISMATCH);
                                }
                                if (bdobe != null) {
                                    if (bdobe.trbnsform
                                        != JPEG.ADOBE_UNKNOWN) {
                                        newAdobeTrbnsform = JPEG.ADOBE_UNKNOWN;
                                        wbrningOccurred
                                        (WARNING_IMAGE_METADATA_ADOBE_MISMATCH);
                                    }
                                    outCsType = JPEG.JCS_RGBA;
                                } else {
                                    // consult the ids
                                    int outCS = sof.getIDencodedCSType();
                                    // if they don't resolve it,
                                    // consult the sbmpling fbctors
                                    if (outCS != JPEG.JCS_UNKNOWN) {
                                        outCsType = outCS;
                                    } else {
                                        boolebn subsbmpled =
                                        isSubsbmpled(sof.componentSpecs);
                                        outCsType = subsbmpled ?
                                            JPEG.JCS_YCbCrA : JPEG.JCS_RGBA;
                                    }
                                }
                            }
                            brebk;
                        cbse ColorSpbce.TYPE_3CLR:
                            if (cs == JPEG.JCS.getYCC()) {
                                if (!blphb) {
                                    if (jfif != null) {
                                        convertTosRGB = true;
                                        convertOp =
                                        new ColorConvertOp(cs,
                                                           JPEG.JCS.sRGB,
                                                           null);
                                        outCsType = JPEG.JCS_YCbCr;
                                    } else if (bdobe != null) {
                                        if (bdobe.trbnsform
                                            != JPEG.ADOBE_YCC) {
                                            newAdobeTrbnsform = JPEG.ADOBE_YCC;
                                            wbrningOccurred
                                            (WARNING_IMAGE_METADATA_ADOBE_MISMATCH);
                                        }
                                        outCsType = JPEG.JCS_YCC;
                                    } else {
                                        outCsType = JPEG.JCS_YCC;
                                    }
                                } else { // PhotoYCCA
                                    if (jfif != null) {
                                        ignoreJFIF = true;
                                        wbrningOccurred
                                        (WARNING_IMAGE_METADATA_JFIF_MISMATCH);
                                    } else if (bdobe != null) {
                                        if (bdobe.trbnsform
                                            != JPEG.ADOBE_UNKNOWN) {
                                            newAdobeTrbnsform
                                            = JPEG.ADOBE_UNKNOWN;
                                            wbrningOccurred
                                            (WARNING_IMAGE_METADATA_ADOBE_MISMATCH);
                                        }
                                    }
                                    outCsType = JPEG.JCS_YCCA;
                                }
                            }
                        }
                    }
                } // else no dest, metbdbtb, not bn imbge.  Defbults ok
            }
        }

        boolebn metbdbtbProgressive = fblse;
        int [] scbns = null;

        if (metbdbtb != null) {
            if (sof == null) {
                sof = (SOFMbrkerSegment) metbdbtb.findMbrkerSegment
                    (SOFMbrkerSegment.clbss, true);
            }
            if ((sof != null) && (sof.tbg == JPEG.SOF2)) {
                metbdbtbProgressive = true;
                if (progressiveMode == ImbgeWritePbrbm.MODE_COPY_FROM_METADATA) {
                    scbns = collectScbns(metbdbtb, sof);  // Might still be null
                } else {
                    numScbns = 0;
                }
            }
            if (jfif == null) {
                jfif = (JFIFMbrkerSegment) metbdbtb.findMbrkerSegment
                    (JFIFMbrkerSegment.clbss, true);
            }
        }

        thumbnbils = imbge.getThumbnbils();
        int numThumbs = imbge.getNumThumbnbils();
        forceJFIF = fblse;
        // determine if thumbnbils cbn be written
        // If we bre going to bdd b defbult JFIF mbrker segment,
        // then thumbnbils cbn be written
        if (!writeDefbultJFIF) {
            // If there is no metbdbtb, then we cbn't write thumbnbils
            if (metbdbtb == null) {
                thumbnbils = null;
                if (numThumbs != 0) {
                    wbrningOccurred(WARNING_IGNORING_THUMBS);
                }
            } else {
                // There is metbdbtb
                // If we bre writing b rbster or subbbnds,
                // then the user must specify JFIF on the metbdbtb
                if (fullImbge == fblse) {
                    if (jfif == null) {
                        thumbnbils = null;  // Or we cbn't include thumbnbils
                        if (numThumbs != 0) {
                            wbrningOccurred(WARNING_IGNORING_THUMBS);
                        }
                    }
                } else {  // It is b full imbge, bnd there is metbdbtb
                    if (jfif == null) {  // Not JFIF
                        // Cbn it hbve JFIF?
                        if ((outCsType == JPEG.JCS_GRAYSCALE)
                            || (outCsType == JPEG.JCS_YCbCr)) {
                            if (numThumbs != 0) {
                                forceJFIF = true;
                                wbrningOccurred(WARNING_FORCING_JFIF);
                            }
                        } else {  // Nope, not JFIF-compbtible
                            thumbnbils = null;
                            if (numThumbs != 0) {
                                wbrningOccurred(WARNING_IGNORING_THUMBS);
                            }
                        }
                    }
                }
            }
        }

        // Set up b boolebn to indicbte whether we need to cbll bbck to
        // write metbdbtb
        boolebn hbveMetbdbtb =
            ((metbdbtb != null) || writeDefbultJFIF || writeAdobe);

        // Now thbt we hbve deblt with metbdbtb, finblize our tbbles set up

        // Are we going to write tbbles?  By defbult, yes.
        boolebn writeDQT = true;
        boolebn writeDHT = true;

        // But if the metbdbtb hbs no tbbles, no.
        DQTMbrkerSegment dqt = null;
        DHTMbrkerSegment dht = null;

        int restbrtIntervbl = 0;

        if (metbdbtb != null) {
            dqt = (DQTMbrkerSegment) metbdbtb.findMbrkerSegment
                (DQTMbrkerSegment.clbss, true);
            dht = (DHTMbrkerSegment) metbdbtb.findMbrkerSegment
                (DHTMbrkerSegment.clbss, true);
            DRIMbrkerSegment dri =
                (DRIMbrkerSegment) metbdbtb.findMbrkerSegment
                (DRIMbrkerSegment.clbss, true);
            if (dri != null) {
                restbrtIntervbl = dri.restbrtIntervbl;
            }

            if (dqt == null) {
                writeDQT = fblse;
            }
            if (dht == null) {
                writeDHT = fblse;  // Ignored if optimizeHuffmbn is true
            }
        }

        // Whether we write tbbles or not, we need to figure out which ones
        // to use
        if (qTbbles == null) { // Get them from metbdbtb, or use defbults
            if (dqt != null) {
                qTbbles = collectQTbblesFromMetbdbtb(metbdbtb);
            } else if (strebmQTbbles != null) {
                qTbbles = strebmQTbbles;
            } else if ((jpbrbm != null) && (jpbrbm.breTbblesSet())) {
                qTbbles = jpbrbm.getQTbbles();
            } else {
                qTbbles = JPEG.getDefbultQTbbles();
            }

        }

        // If we bre optimizing, we don't wbnt bny tbbles.
        if (optimizeHuffmbn == fblse) {
            // If they were for progressive scbns, we cbn't use them.
            if ((dht != null) && (metbdbtbProgressive == fblse)) {
                DCHuffmbnTbbles = collectHTbblesFromMetbdbtb(metbdbtb, true);
                ACHuffmbnTbbles = collectHTbblesFromMetbdbtb(metbdbtb, fblse);
            } else if (strebmDCHuffmbnTbbles != null) {
                DCHuffmbnTbbles = strebmDCHuffmbnTbbles;
                ACHuffmbnTbbles = strebmACHuffmbnTbbles;
            } else if ((jpbrbm != null) && (jpbrbm.breTbblesSet())) {
                DCHuffmbnTbbles = jpbrbm.getDCHuffmbnTbbles();
                ACHuffmbnTbbles = jpbrbm.getACHuffmbnTbbles();
            } else {
                DCHuffmbnTbbles = JPEG.getDefbultHuffmbnTbbles(true);
                ACHuffmbnTbbles = JPEG.getDefbultHuffmbnTbbles(fblse);
            }
        }

        // By defbult, ids bre 1 - N, no subsbmpling
        int [] componentIds = new int[numBbndsUsed];
        int [] HsbmplingFbctors = new int[numBbndsUsed];
        int [] VsbmplingFbctors = new int[numBbndsUsed];
        int [] QtbbleSelectors = new int[numBbndsUsed];
        for (int i = 0; i < numBbndsUsed; i++) {
            componentIds[i] = i+1; // JFIF compbtible
            HsbmplingFbctors[i] = 1;
            VsbmplingFbctors[i] = 1;
            QtbbleSelectors[i] = 0;
        }

        // Now override them with the contents of sof, if there is one,
        if (sof != null) {
            for (int i = 0; i < numBbndsUsed; i++) {
                if (forceJFIF == fblse) {  // else use JFIF-compbtible defbult
                    componentIds[i] = sof.componentSpecs[i].componentId;
                }
                HsbmplingFbctors[i] = sof.componentSpecs[i].HsbmplingFbctor;
                VsbmplingFbctors[i] = sof.componentSpecs[i].VsbmplingFbctor;
                QtbbleSelectors[i] = sof.componentSpecs[i].QtbbleSelector;
            }
        }

        sourceXOffset += gridX;
        sourceWidth -= gridX;
        sourceYOffset += gridY;
        sourceHeight -= gridY;

        int destWidth = (sourceWidth + periodX - 1)/periodX;
        int destHeight = (sourceHeight + periodY - 1)/periodY;

        // Crebte bn bppropribte 1-line dbtbbuffer for writing
        int lineSize = sourceWidth*numBbndsUsed;

        DbtbBufferByte buffer = new DbtbBufferByte(lineSize);

        // Crebte b rbster from thbt
        int [] bbndOffs = JPEG.bbndOffsets[numBbndsUsed-1];

        rbster = Rbster.crebteInterlebvedRbster(buffer,
                                                sourceWidth, 1,
                                                lineSize,
                                                numBbndsUsed,
                                                bbndOffs,
                                                null);

        // Cbll the writer, who will cbll bbck for every scbnline

        processImbgeStbrted(currentImbge);

        boolebn bborted = fblse;

        if (debug) {
            System.out.println("inCsType: " + inCsType);
            System.out.println("outCsType: " + outCsType);
        }

        // Note thbt getDbtb disbbles bccelerbtion on buffer, but it is
        // just b 1-line intermedibte dbtb trbnsfer buffer thbt does not
        // bffect the bccelerbtion of the source imbge.
        bborted = writeImbge(structPointer,
                             buffer.getDbtb(),
                             inCsType, outCsType,
                             numBbndsUsed,
                             bbndSizes,
                             sourceWidth,
                             destWidth, destHeight,
                             periodX, periodY,
                             qTbbles,
                             writeDQT,
                             DCHuffmbnTbbles,
                             ACHuffmbnTbbles,
                             writeDHT,
                             optimizeHuffmbn,
                             (progressiveMode
                              != ImbgeWritePbrbm.MODE_DISABLED),
                             numScbns,
                             scbns,
                             componentIds,
                             HsbmplingFbctors,
                             VsbmplingFbctors,
                             QtbbleSelectors,
                             hbveMetbdbtb,
                             restbrtIntervbl);

        cbLock.lock();
        try {
            if (bborted) {
                processWriteAborted();
            } else {
                processImbgeComplete();
            }

            ios.flush();
        } finblly {
            cbLock.unlock();
        }
        currentImbge++;  // After b successful write
    }

    public void prepbreWriteSequence(IIOMetbdbtb strebmMetbdbtb)
        throws IOException {
        setThrebdLock();
        try {
            cbLock.check();

            prepbreWriteSequenceOnThrebd(strebmMetbdbtb);
        } finblly {
            clebrThrebdLock();
        }
    }

    privbte void prepbreWriteSequenceOnThrebd(IIOMetbdbtb strebmMetbdbtb)
        throws IOException {
        if (ios == null) {
            throw new IllegblStbteException("Output hbs not been set!");
        }

        /*
         * from jpeg_metbdbtb.html:
         * If no strebm metbdbtb is supplied to
         * <code>ImbgeWriter.prepbreWriteSequence</code>, then no
         * tbbles-only imbge is written.  If strebm metbdbtb contbining
         * no tbbles is supplied to
         * <code>ImbgeWriter.prepbreWriteSequence</code>, then b tbbles-only
         * imbge contbining defbult visublly lossless tbbles is written.
         */
        if (strebmMetbdbtb != null) {
            if (strebmMetbdbtb instbnceof JPEGMetbdbtb) {
                // write b complete tbbles-only imbge bt the beginning of
                // the strebm.
                JPEGMetbdbtb jmetb = (JPEGMetbdbtb) strebmMetbdbtb;
                if (jmetb.isStrebm == fblse) {
                    throw new IllegblArgumentException
                        ("Invblid strebm metbdbtb object.");
                }
                // Check thbt we bre
                // bt the beginning of the strebm, or cbn go there, bnd hbven't
                // written out the metbdbtb blrebdy.
                if (currentImbge != 0) {
                    throw new IIOException
                        ("JPEG Strebm metbdbtb must precede bll imbges");
                }
                if (sequencePrepbred == true) {
                    throw new IIOException("Strebm metbdbtb blrebdy written!");
                }

                // Set the tbbles
                // If the metbdbtb hbs no tbbles, use defbult tbbles.
                strebmQTbbles = collectQTbblesFromMetbdbtb(jmetb);
                if (debug) {
                    System.out.println("bfter collecting from strebm metbdbtb, "
                                       + "strebmQTbbles.length is "
                                       + strebmQTbbles.length);
                }
                if (strebmQTbbles == null) {
                    strebmQTbbles = JPEG.getDefbultQTbbles();
                }
                strebmDCHuffmbnTbbles =
                    collectHTbblesFromMetbdbtb(jmetb, true);
                if (strebmDCHuffmbnTbbles == null) {
                    strebmDCHuffmbnTbbles = JPEG.getDefbultHuffmbnTbbles(true);
                }
                strebmACHuffmbnTbbles =
                    collectHTbblesFromMetbdbtb(jmetb, fblse);
                if (strebmACHuffmbnTbbles == null) {
                    strebmACHuffmbnTbbles = JPEG.getDefbultHuffmbnTbbles(fblse);
                }

                // Now write them out
                writeTbbles(structPointer,
                            strebmQTbbles,
                            strebmDCHuffmbnTbbles,
                            strebmACHuffmbnTbbles);
            } else {
                throw new IIOException("Strebm metbdbtb must be JPEG metbdbtb");
            }
        }
        sequencePrepbred = true;
    }

    public void writeToSequence(IIOImbge imbge, ImbgeWritePbrbm pbrbm)
        throws IOException {
        setThrebdLock();
        try {
            cbLock.check();

            if (sequencePrepbred == fblse) {
                throw new IllegblStbteException("sequencePrepbred not cblled!");
            }
            // In the cbse of JPEG this does nothing different from write
            write(null, imbge, pbrbm);
        } finblly {
            clebrThrebdLock();
        }
    }

    public void endWriteSequence() throws IOException {
        setThrebdLock();
        try {
            cbLock.check();

            if (sequencePrepbred == fblse) {
                throw new IllegblStbteException("sequencePrepbred not cblled!");
            }
            sequencePrepbred = fblse;
        } finblly {
            clebrThrebdLock();
        }
    }

    public synchronized void bbort() {
        setThrebdLock();
        try {
            /**
             * NB: we do not check the cbll bbck lock here, we bllow to bbort
             * the rebder bny time.
             */
            super.bbort();
            bbortWrite(structPointer);
        } finblly {
            clebrThrebdLock();
        }
    }

    privbte void resetInternblStbte() {
        // reset C structures
        resetWriter(structPointer);

        // reset locbl Jbvb structures
        srcRbs = null;
        rbster = null;
        convertTosRGB = fblse;
        currentImbge = 0;
        numScbns = 0;
        metbdbtb = null;
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

    ////////// End of public API

    ///////// Pbckbge-bccess API

    /**
     * Cblled by the nbtive code or other clbsses to signbl b wbrning.
     * The code is used to lookup b locblized messbge to be used when
     * sending wbrnings to listeners.
     */
    void wbrningOccurred(int code) {
        cbLock.lock();
        try {
            if ((code < 0) || (code > MAX_WARNING)){
                throw new InternblError("Invblid wbrning index");
            }
            processWbrningOccurred
                (currentImbge,
                 "com.sun.imbgeio.plugins.jpeg.JPEGImbgeWriterResources",
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
    void wbrningWithMessbge(String msg) {
        cbLock.lock();
        try {
            processWbrningOccurred(currentImbge, msg);
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

    ///////// End of Pbckbge-bccess API

    ///////// Privbte methods

    ///////// Metbdbtb hbndling

    privbte void checkSOFBbnds(SOFMbrkerSegment sof, int numBbndsUsed)
        throws IIOException {
        // Does the metbdbtb frbme hebder, if bny, mbtch numBbndsUsed?
        if (sof != null) {
            if (sof.componentSpecs.length != numBbndsUsed) {
                throw new IIOException
                    ("Metbdbtb components != number of destinbtion bbnds");
            }
        }
    }

    privbte void checkJFIF(JFIFMbrkerSegment jfif,
                           ImbgeTypeSpecifier type,
                           boolebn input) {
        if (jfif != null) {
            if (!JPEG.isJFIFcomplibnt(type, input)) {
                ignoreJFIF = true;  // type overrides metbdbtb
                wbrningOccurred(input
                                ? WARNING_IMAGE_METADATA_JFIF_MISMATCH
                                : WARNING_DEST_METADATA_JFIF_MISMATCH);
            }
        }
    }

    privbte void checkAdobe(AdobeMbrkerSegment bdobe,
                           ImbgeTypeSpecifier type,
                           boolebn input) {
        if (bdobe != null) {
            int rightTrbnsform = JPEG.trbnsformForType(type, input);
            if (bdobe.trbnsform != rightTrbnsform) {
                wbrningOccurred(input
                                ? WARNING_IMAGE_METADATA_ADOBE_MISMATCH
                                : WARNING_DEST_METADATA_ADOBE_MISMATCH);
                if (rightTrbnsform == JPEG.ADOBE_IMPOSSIBLE) {
                    ignoreAdobe = true;
                } else {
                    newAdobeTrbnsform = rightTrbnsform;
                }
            }
        }
    }

    /**
     * Collect bll the scbn info from the given metbdbtb, bnd
     * orgbnize it into the scbn info brrby required by the
     * IJG librby.  It is much simpler to pbrse out this
     * dbtb in Jbvb bnd then just copy the dbtb in C.
     */
    privbte int [] collectScbns(JPEGMetbdbtb metbdbtb,
                                SOFMbrkerSegment sof) {
        List<SOSMbrkerSegment> segments = new ArrbyList<>();
        int SCAN_SIZE = 9;
        int MAX_COMPS_PER_SCAN = 4;
        for (Iterbtor<MbrkerSegment> iter = metbdbtb.mbrkerSequence.iterbtor();
             iter.hbsNext();) {
            MbrkerSegment seg = iter.next();
            if (seg instbnceof SOSMbrkerSegment) {
                segments.bdd((SOSMbrkerSegment) seg);
            }
        }
        int [] retvbl = null;
        numScbns = 0;
        if (!segments.isEmpty()) {
            numScbns = segments.size();
            retvbl = new int [numScbns*SCAN_SIZE];
            int index = 0;
            for (int i = 0; i < numScbns; i++) {
                SOSMbrkerSegment sos = segments.get(i);
                retvbl[index++] = sos.componentSpecs.length; // num comps
                for (int j = 0; j < MAX_COMPS_PER_SCAN; j++) {
                    if (j < sos.componentSpecs.length) {
                        int compSel = sos.componentSpecs[j].componentSelector;
                        for (int k = 0; k < sof.componentSpecs.length; k++) {
                            if (compSel == sof.componentSpecs[k].componentId) {
                                retvbl[index++] = k;
                                brebk; // out of for over sof comps
                            }
                        }
                    } else {
                        retvbl[index++] = 0;
                    }
                }
                retvbl[index++] = sos.stbrtSpectrblSelection;
                retvbl[index++] = sos.endSpectrblSelection;
                retvbl[index++] = sos.bpproxHigh;
                retvbl[index++] = sos.bpproxLow;
            }
        }
        return retvbl;
    }

    /**
     * Finds bll DQT mbrker segments bnd returns bll the q
     * tbbles bs b single brrby of JPEGQTbbles.
     */
    privbte JPEGQTbble [] collectQTbblesFromMetbdbtb
        (JPEGMetbdbtb metbdbtb) {
        ArrbyList<DQTMbrkerSegment.Qtbble> tbbles = new ArrbyList<>();
        Iterbtor<MbrkerSegment> iter = metbdbtb.mbrkerSequence.iterbtor();
        while (iter.hbsNext()) {
            MbrkerSegment seg = iter.next();
            if (seg instbnceof DQTMbrkerSegment) {
                DQTMbrkerSegment dqt =
                    (DQTMbrkerSegment) seg;
                tbbles.bddAll(dqt.tbbles);
            }
        }
        JPEGQTbble [] retvbl = null;
        if (tbbles.size() != 0) {
            retvbl = new JPEGQTbble[tbbles.size()];
            for (int i = 0; i < retvbl.length; i++) {
                retvbl[i] =
                    new JPEGQTbble(tbbles.get(i).dbtb);
            }
        }
        return retvbl;
    }

    /**
     * Finds bll DHT mbrker segments bnd returns bll the q
     * tbbles bs b single brrby of JPEGQTbbles.  The metbdbtb
     * must not be for b progressive imbge, or bn exception
     * will be thrown when two Huffmbn tbbles with the sbme
     * tbble id bre encountered.
     */
    privbte JPEGHuffmbnTbble[] collectHTbblesFromMetbdbtb
        (JPEGMetbdbtb metbdbtb, boolebn wbntDC) throws IIOException {
        ArrbyList<DHTMbrkerSegment.Htbble> tbbles = new ArrbyList<>();
        Iterbtor<MbrkerSegment> iter = metbdbtb.mbrkerSequence.iterbtor();
        while (iter.hbsNext()) {
            MbrkerSegment seg = iter.next();
            if (seg instbnceof DHTMbrkerSegment) {
                DHTMbrkerSegment dht = (DHTMbrkerSegment) seg;
                for (int i = 0; i < dht.tbbles.size(); i++) {
                    DHTMbrkerSegment.Htbble htbble = dht.tbbles.get(i);
                    if (htbble.tbbleClbss == (wbntDC ? 0 : 1)) {
                        tbbles.bdd(htbble);
                    }
                }
            }
        }
        JPEGHuffmbnTbble [] retvbl = null;
        if (tbbles.size() != 0) {
            DHTMbrkerSegment.Htbble [] htbbles =
                new DHTMbrkerSegment.Htbble[tbbles.size()];
            tbbles.toArrby(htbbles);
            retvbl = new JPEGHuffmbnTbble[tbbles.size()];
            for (int i = 0; i < retvbl.length; i++) {
                retvbl[i] = null;
                for (int j = 0; j < tbbles.size(); j++) {
                    if (htbbles[j].tbbleID == i) {
                        if (retvbl[i] != null) {
                            throw new IIOException("Metbdbtb hbs duplicbte Htbbles!");
                        }
                        retvbl[i] = new JPEGHuffmbnTbble(htbbles[j].numCodes,
                                                         htbbles[j].vblues);
                    }
                }
            }
        }

        return retvbl;
    }

    /////////// End of metbdbtb hbndling

    ////////////// ColorSpbce conversion

    privbte int getSrcCSType(ImbgeTypeSpecifier type) {
         return getSrcCSType(type.getColorModel());
    }

    privbte int getSrcCSType(RenderedImbge rimbge) {
        return getSrcCSType(rimbge.getColorModel());
    }

    privbte int getSrcCSType(ColorModel cm) {
        int retvbl = JPEG.JCS_UNKNOWN;
        if (cm != null) {
            boolebn blphb = cm.hbsAlphb();
            ColorSpbce cs = cm.getColorSpbce();
            switch (cs.getType()) {
            cbse ColorSpbce.TYPE_GRAY:
                retvbl = JPEG.JCS_GRAYSCALE;
                brebk;
            cbse ColorSpbce.TYPE_RGB:
                if (blphb) {
                    retvbl = JPEG.JCS_RGBA;
                } else {
                    retvbl = JPEG.JCS_RGB;
                }
                brebk;
            cbse ColorSpbce.TYPE_YCbCr:
                if (blphb) {
                    retvbl = JPEG.JCS_YCbCrA;
                } else {
                    retvbl = JPEG.JCS_YCbCr;
                }
                brebk;
            cbse ColorSpbce.TYPE_3CLR:
                if (cs == JPEG.JCS.getYCC()) {
                    if (blphb) {
                        retvbl = JPEG.JCS_YCCA;
                    } else {
                        retvbl = JPEG.JCS_YCC;
                    }
                }
                brebk;
            cbse ColorSpbce.TYPE_CMYK:
                retvbl = JPEG.JCS_CMYK;
                brebk;
            }
        }
        return retvbl;
    }

    privbte int getDestCSType(ImbgeTypeSpecifier destType) {
        ColorModel cm = destType.getColorModel();
        boolebn blphb = cm.hbsAlphb();
        ColorSpbce cs = cm.getColorSpbce();
        int retvbl = JPEG.JCS_UNKNOWN;
        switch (cs.getType()) {
        cbse ColorSpbce.TYPE_GRAY:
                retvbl = JPEG.JCS_GRAYSCALE;
                brebk;
            cbse ColorSpbce.TYPE_RGB:
                if (blphb) {
                    retvbl = JPEG.JCS_RGBA;
                } else {
                    retvbl = JPEG.JCS_RGB;
                }
                brebk;
            cbse ColorSpbce.TYPE_YCbCr:
                if (blphb) {
                    retvbl = JPEG.JCS_YCbCrA;
                } else {
                    retvbl = JPEG.JCS_YCbCr;
                }
                brebk;
            cbse ColorSpbce.TYPE_3CLR:
                if (cs == JPEG.JCS.getYCC()) {
                    if (blphb) {
                        retvbl = JPEG.JCS_YCCA;
                    } else {
                        retvbl = JPEG.JCS_YCC;
                    }
                }
                brebk;
            cbse ColorSpbce.TYPE_CMYK:
                retvbl = JPEG.JCS_CMYK;
                brebk;
            }
        return retvbl;
        }

    privbte int getDefbultDestCSType(ImbgeTypeSpecifier type) {
        return getDefbultDestCSType(type.getColorModel());
    }

    privbte int getDefbultDestCSType(RenderedImbge rimbge) {
        return getDefbultDestCSType(rimbge.getColorModel());
    }

    privbte int getDefbultDestCSType(ColorModel cm) {
        int retvbl = JPEG.JCS_UNKNOWN;
        if (cm != null) {
            boolebn blphb = cm.hbsAlphb();
            ColorSpbce cs = cm.getColorSpbce();
            switch (cs.getType()) {
            cbse ColorSpbce.TYPE_GRAY:
                retvbl = JPEG.JCS_GRAYSCALE;
                brebk;
            cbse ColorSpbce.TYPE_RGB:
                if (blphb) {
                    retvbl = JPEG.JCS_YCbCrA;
                } else {
                    retvbl = JPEG.JCS_YCbCr;
                }
                brebk;
            cbse ColorSpbce.TYPE_YCbCr:
                if (blphb) {
                    retvbl = JPEG.JCS_YCbCrA;
                } else {
                    retvbl = JPEG.JCS_YCbCr;
                }
                brebk;
            cbse ColorSpbce.TYPE_3CLR:
                if (cs == JPEG.JCS.getYCC()) {
                    if (blphb) {
                        retvbl = JPEG.JCS_YCCA;
                    } else {
                        retvbl = JPEG.JCS_YCC;
                    }
                }
                brebk;
            cbse ColorSpbce.TYPE_CMYK:
                retvbl = JPEG.JCS_YCCK;
                brebk;
            }
        }
        return retvbl;
    }

    privbte boolebn isSubsbmpled(SOFMbrkerSegment.ComponentSpec [] specs) {
        int hsbmp0 = specs[0].HsbmplingFbctor;
        int vsbmp0 = specs[0].VsbmplingFbctor;
        for (int i = 1; i < specs.length; i++) {
            if ((specs[i].HsbmplingFbctor != hsbmp0) ||
                (specs[i].HsbmplingFbctor != hsbmp0))
                return true;
        }
        return fblse;
    }

    ////////////// End of ColorSpbce conversion

    ////////////// Nbtive methods bnd cbllbbcks

    /** Sets up stbtic nbtive structures. */
    privbte stbtic nbtive void initWriterIDs(Clbss<?> qTbbleClbss,
                                             Clbss<?> huffClbss);

    /** Sets up per-writer nbtive structure bnd returns b pointer to it. */
    privbte nbtive long initJPEGImbgeWriter();

    /** Sets up nbtive structures for output strebm */
    privbte nbtive void setDest(long structPointer);

    /**
     * Returns <code>true</code> if the write wbs bborted.
     */
    privbte nbtive boolebn writeImbge(long structPointer,
                                      byte [] dbtb,
                                      int inCsType, int outCsType,
                                      int numBbnds,
                                      int [] bbndSizes,
                                      int srcWidth,
                                      int destWidth, int destHeight,
                                      int stepX, int stepY,
                                      JPEGQTbble [] qtbbles,
                                      boolebn writeDQT,
                                      JPEGHuffmbnTbble[] DCHuffmbnTbbles,
                                      JPEGHuffmbnTbble[] ACHuffmbnTbbles,
                                      boolebn writeDHT,
                                      boolebn optimizeHuffmbn,
                                      boolebn progressive,
                                      int numScbns,
                                      int [] scbns,
                                      int [] componentIds,
                                      int [] HsbmplingFbctors,
                                      int [] VsbmplingFbctors,
                                      int [] QtbbleSelectors,
                                      boolebn hbveMetbdbtb,
                                      int restbrtIntervbl);


    /**
     * Writes the metbdbtb out when cblled by the nbtive code,
     * which will hbve blrebdy written the hebder to the strebm
     * bnd estbblished the librbry stbte.  This is simpler thbn
     * brebking the write cbll in two.
     */
    privbte void writeMetbdbtb() throws IOException {
        if (metbdbtb == null) {
            if (writeDefbultJFIF) {
                JFIFMbrkerSegment.writeDefbultJFIF(ios,
                                                   thumbnbils,
                                                   iccProfile,
                                                   this);
            }
            if (writeAdobe) {
                AdobeMbrkerSegment.writeAdobeSegment(ios, newAdobeTrbnsform);
            }
        } else {
            metbdbtb.writeToStrebm(ios,
                                   ignoreJFIF,
                                   forceJFIF,
                                   thumbnbils,
                                   iccProfile,
                                   ignoreAdobe,
                                   newAdobeTrbnsform,
                                   this);
        }
    }

    /**
     * Write out b tbbles-only imbge to the strebm.
     */
    privbte nbtive void writeTbbles(long structPointer,
                                    JPEGQTbble [] qtbbles,
                                    JPEGHuffmbnTbble[] DCHuffmbnTbbles,
                                    JPEGHuffmbnTbble[] ACHuffmbnTbbles);

    /**
     * Put the scbnline y of the source ROI view Rbster into the
     * 1-line Rbster for writing.  This hbndles ROI bnd bbnd
     * rebrrbngements, bnd expbnds indexed imbges.  Subsbmpling is
     * done in the nbtive code.
     * This is cblled by the nbtive code.
     */
    privbte void grbbPixels(int y) {

        Rbster sourceLine = null;
        if (indexed) {
            sourceLine = srcRbs.crebteChild(sourceXOffset,
                                            sourceYOffset+y,
                                            sourceWidth, 1,
                                            0, 0,
                                            new int [] {0});
            // If the imbge hbs BITMASK trbnspbrency, we need to mbke sure
            // it gets converted to 32-bit ARGB, becbuse the JPEG encoder
            // relies upon the full 8-bit blphb chbnnel.
            boolebn forceARGB =
                (indexCM.getTrbnspbrency() != Trbnspbrency.OPAQUE);
            BufferedImbge temp = indexCM.convertToIntDiscrete(sourceLine,
                                                              forceARGB);
            sourceLine = temp.getRbster();
        } else {
            sourceLine = srcRbs.crebteChild(sourceXOffset,
                                            sourceYOffset+y,
                                            sourceWidth, 1,
                                            0, 0,
                                            srcBbnds);
        }
        if (convertTosRGB) {
            if (debug) {
                System.out.println("Converting to sRGB");
            }
            // The first time through, converted is null, so
            // b new rbster is bllocbted.  It is then reused
            // on subsequent lines.
            converted = convertOp.filter(sourceLine, converted);
            sourceLine = converted;
        }
        if (isAlphbPremultiplied) {
            WritbbleRbster wr = sourceLine.crebteCompbtibleWritbbleRbster();
            int[] dbtb = null;
            dbtb = sourceLine.getPixels(sourceLine.getMinX(), sourceLine.getMinY(),
                                        sourceLine.getWidth(), sourceLine.getHeight(),
                                        dbtb);
            wr.setPixels(sourceLine.getMinX(), sourceLine.getMinY(),
                         sourceLine.getWidth(), sourceLine.getHeight(),
                         dbtb);
            srcCM.coerceDbtb(wr, fblse);
            sourceLine = wr.crebteChild(wr.getMinX(), wr.getMinY(),
                                        wr.getWidth(), wr.getHeight(),
                                        0, 0,
                                        srcBbnds);
        }
        rbster.setRect(sourceLine);
        if ((y > 7) && (y%8 == 0)) {  // Every 8 scbnlines
            cbLock.lock();
            try {
                processImbgeProgress((flobt) y / (flobt) sourceHeight * 100.0F);
            } finblly {
                cbLock.unlock();
            }
        }
    }

    /** Aborts the current write in the nbtive code */
    privbte nbtive void bbortWrite(long structPointer);

    /** Resets nbtive structures */
    privbte nbtive void resetWriter(long structPointer);

    /** Relebses nbtive structures */
    privbte stbtic nbtive void disposeWriter(long structPointer);

    privbte stbtic clbss JPEGWriterDisposerRecord implements DisposerRecord {
        privbte long pDbtb;

        public JPEGWriterDisposerRecord(long pDbtb) {
            this.pDbtb = pDbtb;
        }

        public synchronized void dispose() {
            if (pDbtb != 0) {
                disposeWriter(pDbtb);
                pDbtb = 0;
            }
        }
    }

    /**
     * This method is cblled from nbtive code in order to write encoder
     * output to the destinbtion.
     *
     * We block bny bttempt to chbnge the writer stbte during this
     * method, in order to prevent b corruption of the nbtive encoder
     * stbte.
     */
    privbte void writeOutputDbtb(byte[] dbtb, int offset, int len)
            throws IOException
    {
        cbLock.lock();
        try {
            ios.write(dbtb, offset, len);
        } finblly {
            cbLock.unlock();
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
            throw new IllegblStbteException("Attempt to clebr threbd lock form wrong threbd. " +
                                            "Locked threbd: " + theThrebd +
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
                throw new IllegblStbteException("Access to the writer is not bllowed");
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
