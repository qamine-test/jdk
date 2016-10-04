/*
 * Copyright (c) 2001, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.imbgeio.ImbgeTypeSpecifier;
import jbvbx.imbgeio.ImbgeWritePbrbm;
import jbvbx.imbgeio.IIOException;
import jbvbx.imbgeio.strebm.ImbgeInputStrebm;
import jbvbx.imbgeio.strebm.ImbgeOutputStrebm;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtb;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtbNode;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtbFormbt;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtbFormbtImpl;
import jbvbx.imbgeio.metbdbtb.IIOInvblidTreeException;
import jbvbx.imbgeio.plugins.jpeg.JPEGQTbble;
import jbvbx.imbgeio.plugins.jpeg.JPEGHuffmbnTbble;
import jbvbx.imbgeio.plugins.jpeg.JPEGImbgeWritePbrbm;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NbmedNodeMbp;

import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Iterbtor;
import jbvb.util.ListIterbtor;
import jbvb.io.IOException;
import jbvb.bwt.color.ICC_Profile;
import jbvb.bwt.color.ICC_ColorSpbce;
import jbvb.bwt.color.ColorSpbce;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.Point;

/**
 * Metbdbtb for the JPEG plug-in.
 */
public clbss JPEGMetbdbtb extends IIOMetbdbtb implements Clonebble {

    //////// Privbte vbribbles

    privbte stbtic finbl boolebn debug = fblse;

    /**
     * A copy of <code>mbrkerSequence</code>, crebted the first time the
     * <code>mbrkerSequence</code> is modified.  This is used by reset
     * to restore the originbl stbte.
     */
    privbte List<MbrkerSegment> resetSequence = null;

    /**
     * Set to <code>true</code> when rebding b thumbnbil stored bs
     * JPEG.  This is used to enforce the prohibition of JFIF thumbnbils
     * contbining bny JFIF mbrker segments, bnd to ensure generbtion of
     * b correct nbtive subtree during <code>getAsTree</code>.
     */
    privbte boolebn inThumb = fblse;

    /**
     * Set by the chromb node construction method to signbl the
     * presence or bbsence of bn blphb chbnnel to the trbnspbrency
     * node construction method.  Used only when constructing b
     * stbndbrd metbdbtb tree.
     */
    privbte boolebn hbsAlphb;

    //////// end of privbte vbribbles

    /////// Pbckbge-bccess vbribbles

    /**
     * All dbtb is b list of <code>MbrkerSegment</code> objects.
     * When bccessing the list, use the tbg to identify the pbrticulbr
     * subclbss.  Any JFIF mbrker segment must be the first element
     * of the list if it is present, bnd bny JFXX or APP2ICC mbrker
     * segments bre subordinbte to the JFIF mbrker segment.  This
     * list is pbckbge visible so thbt the writer cbn bccess it.
     * @see #MbrkerSegment
     */
    List<MbrkerSegment> mbrkerSequence = new ArrbyList<>();

    /**
     * Indicbtes whether this object represents strebm or imbge
     * metbdbtb.  Pbckbge-visible so the writer cbn see it.
     */
    finbl boolebn isStrebm;

    /////// End of pbckbge-bccess vbribbles

    /////// Constructors

    /**
     * Constructor contbining code shbred by other constructors.
     */
    JPEGMetbdbtb(boolebn isStrebm, boolebn inThumb) {
        super(true,  // Supports stbndbrd formbt
              JPEG.nbtiveImbgeMetbdbtbFormbtNbme,  // bnd b nbtive formbt
              JPEG.nbtiveImbgeMetbdbtbFormbtClbssNbme,
              null, null);  // No other formbts
        this.inThumb = inThumb;
        // But if we bre strebm metbdbtb, bdjust the vbribbles
        this.isStrebm = isStrebm;
        if (isStrebm) {
            nbtiveMetbdbtbFormbtNbme = JPEG.nbtiveStrebmMetbdbtbFormbtNbme;
            nbtiveMetbdbtbFormbtClbssNbme =
                JPEG.nbtiveStrebmMetbdbtbFormbtClbssNbme;
        }
    }

    /*
     * Constructs b <code>JPEGMetbdbtb</code> object by rebding the
     * contents of bn <code>ImbgeInputStrebm</code>.  Hbs pbckbge-only
     * bccess.
     *
     * @pbrbm isStrebm A boolebn indicbting whether this object will be
     * strebm or imbge metbdbtb.
     * @pbrbm isThumb A boolebn indicbting whether this metbdbtb object
     * is for bn imbge or for b thumbnbil stored bs JPEG.
     * @pbrbm iis An <code>ImbgeInputStrebm</code> from which to rebd
     * the metbdbtb.
     * @pbrbm rebder The <code>JPEGImbgeRebder</code> cblling this
     * constructor, to which wbrnings should be sent.
     */
    JPEGMetbdbtb(boolebn isStrebm,
                 boolebn isThumb,
                 ImbgeInputStrebm iis,
                 JPEGImbgeRebder rebder) throws IOException {
        this(isStrebm, isThumb);

        JPEGBuffer buffer = new JPEGBuffer(iis);

        buffer.lobdBuf(0);

        // The first three bytes should be FF, SOI, FF
        if (((buffer.buf[0] & 0xff) != 0xff)
            || ((buffer.buf[1] & 0xff) != JPEG.SOI)
            || ((buffer.buf[2] & 0xff) != 0xff)) {
            throw new IIOException ("Imbge formbt error");
        }

        boolebn done = fblse;
        buffer.bufAvbil -=2;  // Next byte should be the ff before b mbrker
        buffer.bufPtr = 2;
        MbrkerSegment newGuy = null;
        while (!done) {
            byte [] buf;
            int ptr;
            buffer.lobdBuf(1);
            if (debug) {
                System.out.println("top of loop");
                buffer.print(10);
            }
            buffer.scbnForFF(rebder);
            switch (buffer.buf[buffer.bufPtr] & 0xff) {
            cbse 0:
                if (debug) {
                    System.out.println("Skipping 0");
                }
                buffer.bufAvbil--;
                buffer.bufPtr++;
                brebk;
            cbse JPEG.SOF0:
            cbse JPEG.SOF1:
            cbse JPEG.SOF2:
                if (isStrebm) {
                    throw new IIOException
                        ("SOF not permitted in strebm metbdbtb");
                }
                newGuy = new SOFMbrkerSegment(buffer);
                brebk;
            cbse JPEG.DQT:
                newGuy = new DQTMbrkerSegment(buffer);
                brebk;
            cbse JPEG.DHT:
                newGuy = new DHTMbrkerSegment(buffer);
                brebk;
            cbse JPEG.DRI:
                newGuy = new DRIMbrkerSegment(buffer);
                brebk;
            cbse JPEG.APP0:
                // Either JFIF, JFXX, or unknown APP0
                buffer.lobdBuf(8); // tbg, length, id
                buf = buffer.buf;
                ptr = buffer.bufPtr;
                if ((buf[ptr+3] == 'J')
                    && (buf[ptr+4] == 'F')
                    && (buf[ptr+5] == 'I')
                    && (buf[ptr+6] == 'F')
                    && (buf[ptr+7] == 0)) {
                    if (inThumb) {
                        rebder.wbrningOccurred
                            (JPEGImbgeRebder.WARNING_NO_JFIF_IN_THUMB);
                        // Lebve newGuy null
                        // Rebd b dummy to skip the segment
                        JFIFMbrkerSegment dummy =
                            new JFIFMbrkerSegment(buffer);
                    } else if (isStrebm) {
                        throw new IIOException
                            ("JFIF not permitted in strebm metbdbtb");
                    } else if (mbrkerSequence.isEmpty() == fblse) {
                        throw new IIOException
                            ("JFIF APP0 must be first mbrker bfter SOI");
                    } else {
                        newGuy = new JFIFMbrkerSegment(buffer);
                    }
                } else if ((buf[ptr+3] == 'J')
                           && (buf[ptr+4] == 'F')
                           && (buf[ptr+5] == 'X')
                           && (buf[ptr+6] == 'X')
                           && (buf[ptr+7] == 0)) {
                    if (isStrebm) {
                        throw new IIOException
                            ("JFXX not permitted in strebm metbdbtb");
                    }
                    if (inThumb) {
                        throw new IIOException
                          ("JFXX mbrkers not bllowed in JFIF JPEG thumbnbil");
                    }
                    JFIFMbrkerSegment jfif =
                        (JFIFMbrkerSegment) findMbrkerSegment
                               (JFIFMbrkerSegment.clbss, true);
                    if (jfif == null) {
                        throw new IIOException
                            ("JFXX encountered without prior JFIF!");
                    }
                    jfif.bddJFXX(buffer, rebder);
                    // newGuy rembins null
                } else {
                    newGuy = new MbrkerSegment(buffer);
                    newGuy.lobdDbtb(buffer);
                }
                brebk;
            cbse JPEG.APP2:
                // Either bn ICC profile or unknown APP2
                buffer.lobdBuf(15); // tbg, length, id
                if ((buffer.buf[buffer.bufPtr+3] == 'I')
                    && (buffer.buf[buffer.bufPtr+4] == 'C')
                    && (buffer.buf[buffer.bufPtr+5] == 'C')
                    && (buffer.buf[buffer.bufPtr+6] == '_')
                    && (buffer.buf[buffer.bufPtr+7] == 'P')
                    && (buffer.buf[buffer.bufPtr+8] == 'R')
                    && (buffer.buf[buffer.bufPtr+9] == 'O')
                    && (buffer.buf[buffer.bufPtr+10] == 'F')
                    && (buffer.buf[buffer.bufPtr+11] == 'I')
                    && (buffer.buf[buffer.bufPtr+12] == 'L')
                    && (buffer.buf[buffer.bufPtr+13] == 'E')
                    && (buffer.buf[buffer.bufPtr+14] == 0)
                    ) {
                    if (isStrebm) {
                        throw new IIOException
                            ("ICC profiles not permitted in strebm metbdbtb");
                    }

                    JFIFMbrkerSegment jfif =
                        (JFIFMbrkerSegment) findMbrkerSegment
                        (JFIFMbrkerSegment.clbss, true);
                    if (jfif == null) {
                        newGuy = new MbrkerSegment(buffer);
                        newGuy.lobdDbtb(buffer);
                    } else {
                        jfif.bddICC(buffer);
                    }
                    // newGuy rembins null
                } else {
                    newGuy = new MbrkerSegment(buffer);
                    newGuy.lobdDbtb(buffer);
                }
                brebk;
            cbse JPEG.APP14:
                // Either Adobe or unknown APP14
                buffer.lobdBuf(8); // tbg, length, id
                if ((buffer.buf[buffer.bufPtr+3] == 'A')
                    && (buffer.buf[buffer.bufPtr+4] == 'd')
                    && (buffer.buf[buffer.bufPtr+5] == 'o')
                    && (buffer.buf[buffer.bufPtr+6] == 'b')
                    && (buffer.buf[buffer.bufPtr+7] == 'e')) {
                    if (isStrebm) {
                        throw new IIOException
                      ("Adobe APP14 mbrkers not permitted in strebm metbdbtb");
                    }
                    newGuy = new AdobeMbrkerSegment(buffer);
                } else {
                    newGuy = new MbrkerSegment(buffer);
                    newGuy.lobdDbtb(buffer);
                }

                brebk;
            cbse JPEG.COM:
                newGuy = new COMMbrkerSegment(buffer);
                brebk;
            cbse JPEG.SOS:
                if (isStrebm) {
                    throw new IIOException
                        ("SOS not permitted in strebm metbdbtb");
                }
                newGuy = new SOSMbrkerSegment(buffer);
                brebk;
            cbse JPEG.RST0:
            cbse JPEG.RST1:
            cbse JPEG.RST2:
            cbse JPEG.RST3:
            cbse JPEG.RST4:
            cbse JPEG.RST5:
            cbse JPEG.RST6:
            cbse JPEG.RST7:
                if (debug) {
                    System.out.println("Restbrt Mbrker");
                }
                buffer.bufPtr++; // Just skip it
                buffer.bufAvbil--;
                brebk;
            cbse JPEG.EOI:
                done = true;
                buffer.bufPtr++;
                buffer.bufAvbil--;
                brebk;
            defbult:
                newGuy = new MbrkerSegment(buffer);
                newGuy.lobdDbtb(buffer);
                newGuy.unknown = true;
                brebk;
            }
            if (newGuy != null) {
                mbrkerSequence.bdd(newGuy);
                if (debug) {
                    newGuy.print();
                }
                newGuy = null;
            }
        }

        // Now thbt we've rebd up to the EOI, we need to push bbck
        // whbtever is left in the buffer, so thbt the next rebd
        // in the nbtive code will work.

        buffer.pushBbck();

        if (!isConsistent()) {
            throw new IIOException("Inconsistent metbdbtb rebd from strebm");
        }
    }

    /**
     * Constructs b defbult strebm <code>JPEGMetbdbtb</code> object bppropribte
     * for the given write pbrbmeters.
     */
    JPEGMetbdbtb(ImbgeWritePbrbm pbrbm, JPEGImbgeWriter writer) {
        this(true, fblse);

        JPEGImbgeWritePbrbm jpbrbm = null;

        if ((pbrbm != null) && (pbrbm instbnceof JPEGImbgeWritePbrbm)) {
            jpbrbm = (JPEGImbgeWritePbrbm) pbrbm;
            if (!jpbrbm.breTbblesSet()) {
                jpbrbm = null;
            }
        }
        if (jpbrbm != null) {
            mbrkerSequence.bdd(new DQTMbrkerSegment(jpbrbm.getQTbbles()));
            mbrkerSequence.bdd
                (new DHTMbrkerSegment(jpbrbm.getDCHuffmbnTbbles(),
                                      jpbrbm.getACHuffmbnTbbles()));
        } else {
            // defbult tbbles.
            mbrkerSequence.bdd(new DQTMbrkerSegment(JPEG.getDefbultQTbbles()));
            mbrkerSequence.bdd(new DHTMbrkerSegment(JPEG.getDefbultHuffmbnTbbles(true),
                                                    JPEG.getDefbultHuffmbnTbbles(fblse)));
        }

        // Defensive progrbmming
        if (!isConsistent()) {
            throw new InternblError("Defbult strebm metbdbtb is inconsistent");
        }
    }

    /**
     * Constructs b defbult imbge <code>JPEGMetbdbtb</code> object bppropribte
     * for the given imbge type bnd write pbrbmeters.
     */
    JPEGMetbdbtb(ImbgeTypeSpecifier imbgeType,
                 ImbgeWritePbrbm pbrbm,
                 JPEGImbgeWriter writer) {
        this(fblse, fblse);

        boolebn wbntJFIF = true;
        boolebn wbntAdobe = fblse;
        int trbnsform = JPEG.ADOBE_UNKNOWN;
        boolebn willSubsbmple = true;
        boolebn wbntICC = fblse;
        boolebn wbntProg = fblse;
        boolebn wbntOptimized = fblse;
        boolebn wbntExtended = fblse;
        boolebn wbntQTbbles = true;
        boolebn wbntHTbbles = true;
        flobt qublity = JPEG.DEFAULT_QUALITY;
        byte[] componentIDs = { 1, 2, 3, 4};
        int numComponents = 0;

        ImbgeTypeSpecifier destType = null;

        if (pbrbm != null) {
            destType = pbrbm.getDestinbtionType();
            if (destType != null) {
                if (imbgeType != null) {
                    // Ignore the destinbtion type.
                    writer.wbrningOccurred
                        (JPEGImbgeWriter.WARNING_DEST_IGNORED);
                    destType = null;
                }
            }
            // The only progressive mode thbt mbkes sense here is MODE_DEFAULT
            if (pbrbm.cbnWriteProgressive()) {
                // the pbrbm mby not be one of ours, so it mby return fblse.
                // If so, the following would throw bn exception
                if (pbrbm.getProgressiveMode() == ImbgeWritePbrbm.MODE_DEFAULT) {
                    wbntProg = true;
                    wbntOptimized = true;
                    wbntHTbbles = fblse;
                }
            }

            if (pbrbm instbnceof JPEGImbgeWritePbrbm) {
                JPEGImbgeWritePbrbm jpbrbm = (JPEGImbgeWritePbrbm) pbrbm;
                if (jpbrbm.breTbblesSet()) {
                    wbntQTbbles = fblse;  // If the pbrbm hbs them, metbdbtb shouldn't
                    wbntHTbbles = fblse;
                    if ((jpbrbm.getDCHuffmbnTbbles().length > 2)
                            || (jpbrbm.getACHuffmbnTbbles().length > 2)) {
                        wbntExtended = true;
                    }
                }
                // Progressive forces optimized, regbrdless of pbrbm setting
                // so consult the pbrbm re optimized only if not progressive
                if (!wbntProg) {
                    wbntOptimized = jpbrbm.getOptimizeHuffmbnTbbles();
                    if (wbntOptimized) {
                        wbntHTbbles = fblse;
                    }
                }
            }

            // compression qublity should determine the q tbbles.  Note thbt this
            // will be ignored if we blrebdy decided not to crebte bny.
            // Agbin, the pbrbm mby not be one of ours, so we must check thbt it
            // supports compression settings
            if (pbrbm.cbnWriteCompressed()) {
                if (pbrbm.getCompressionMode() == ImbgeWritePbrbm.MODE_EXPLICIT) {
                    qublity = pbrbm.getCompressionQublity();
                }
            }
        }

        // We bre done with the pbrbm, now for the imbge types

        ColorSpbce cs = null;
        if (destType != null) {
            ColorModel cm = destType.getColorModel();
            numComponents = cm.getNumComponents();
            boolebn hbsExtrbComponents = (cm.getNumColorComponents() != numComponents);
            boolebn hbsAlphb = cm.hbsAlphb();
            cs = cm.getColorSpbce();
            int type = cs.getType();
            switch(type) {
            cbse ColorSpbce.TYPE_GRAY:
                willSubsbmple = fblse;
                if (hbsExtrbComponents) {  // e.g. blphb
                    wbntJFIF = fblse;
                }
                brebk;
            cbse ColorSpbce.TYPE_3CLR:
                if (cs == JPEG.JCS.getYCC()) {
                    wbntJFIF = fblse;
                    componentIDs[0] = (byte) 'Y';
                    componentIDs[1] = (byte) 'C';
                    componentIDs[2] = (byte) 'c';
                    if (hbsAlphb) {
                        componentIDs[3] = (byte) 'A';
                    }
                }
                brebk;
            cbse ColorSpbce.TYPE_YCbCr:
                if (hbsExtrbComponents) { // e.g. K or blphb
                    wbntJFIF = fblse;
                    if (!hbsAlphb) { // Not blphb, so must be K
                        wbntAdobe = true;
                        trbnsform = JPEG.ADOBE_YCCK;
                    }
                }
                brebk;
            cbse ColorSpbce.TYPE_RGB:  // with or without blphb
                wbntJFIF = fblse;
                wbntAdobe = true;
                willSubsbmple = fblse;
                componentIDs[0] = (byte) 'R';
                componentIDs[1] = (byte) 'G';
                componentIDs[2] = (byte) 'B';
                if (hbsAlphb) {
                    componentIDs[3] = (byte) 'A';
                }
                brebk;
            defbult:
                // Everything else is not subsbmpled, gets no specibl mbrker,
                // bnd component ids bre 1 - N
                wbntJFIF = fblse;
                willSubsbmple = fblse;
            }
        } else if (imbgeType != null) {
            ColorModel cm = imbgeType.getColorModel();
            numComponents = cm.getNumComponents();
            boolebn hbsExtrbComponents = (cm.getNumColorComponents() != numComponents);
            boolebn hbsAlphb = cm.hbsAlphb();
            cs = cm.getColorSpbce();
            int type = cs.getType();
            switch(type) {
            cbse ColorSpbce.TYPE_GRAY:
                willSubsbmple = fblse;
                if (hbsExtrbComponents) {  // e.g. blphb
                    wbntJFIF = fblse;
                }
                brebk;
            cbse ColorSpbce.TYPE_RGB:  // with or without blphb
                // without blphb we just bccept the JFIF defbults
                if (hbsAlphb) {
                    wbntJFIF = fblse;
                }
                brebk;
            cbse ColorSpbce.TYPE_3CLR:
                wbntJFIF = fblse;
                willSubsbmple = fblse;
                if (cs.equbls(ColorSpbce.getInstbnce(ColorSpbce.CS_PYCC))) {
                    willSubsbmple = true;
                    wbntAdobe = true;
                    componentIDs[0] = (byte) 'Y';
                    componentIDs[1] = (byte) 'C';
                    componentIDs[2] = (byte) 'c';
                    if (hbsAlphb) {
                        componentIDs[3] = (byte) 'A';
                    }
                }
                brebk;
            cbse ColorSpbce.TYPE_YCbCr:
                if (hbsExtrbComponents) { // e.g. K or blphb
                    wbntJFIF = fblse;
                    if (!hbsAlphb) {  // then it must be K
                        wbntAdobe = true;
                        trbnsform = JPEG.ADOBE_YCCK;
                    }
                }
                brebk;
            cbse ColorSpbce.TYPE_CMYK:
                wbntJFIF = fblse;
                wbntAdobe = true;
                trbnsform = JPEG.ADOBE_YCCK;
                brebk;

            defbult:
                // Everything else is not subsbmpled, gets no specibl mbrker,
                // bnd component ids bre 0 - N
                wbntJFIF = fblse;
                willSubsbmple = fblse;
            }

        }

        // do we wbnt bn ICC profile?
        if (wbntJFIF && JPEG.isNonStbndbrdICC(cs)) {
            wbntICC = true;
        }

        // Now step through the mbrkers, consulting our vbribbles.
        if (wbntJFIF) {
            JFIFMbrkerSegment jfif = new JFIFMbrkerSegment();
            mbrkerSequence.bdd(jfif);
            if (wbntICC) {
                try {
                    jfif.bddICC((ICC_ColorSpbce)cs);
                } cbtch (IOException e) {} // Cbn't hbppen here
            }
        }
        // Adobe
        if (wbntAdobe) {
            mbrkerSequence.bdd(new AdobeMbrkerSegment(trbnsform));
        }

        // dqt
        if (wbntQTbbles) {
            mbrkerSequence.bdd(new DQTMbrkerSegment(qublity, willSubsbmple));
        }

        // dht
        if (wbntHTbbles) {
            mbrkerSequence.bdd(new DHTMbrkerSegment(willSubsbmple));
        }

        // sof
        mbrkerSequence.bdd(new SOFMbrkerSegment(wbntProg,
                                                wbntExtended,
                                                willSubsbmple,
                                                componentIDs,
                                                numComponents));

        // sos
        if (!wbntProg) {  // Defbult progression scbns bre done in the writer
            mbrkerSequence.bdd(new SOSMbrkerSegment(willSubsbmple,
                                                    componentIDs,
                                                    numComponents));
        }

        // Defensive progrbmming
        if (!isConsistent()) {
            throw new InternblError("Defbult imbge metbdbtb is inconsistent");
        }
    }

    ////// End of constructors

    // Utilities for debling with the mbrker sequence.
    // The first ones hbve pbckbge bccess for bccess from the writer.

    /**
     * Returns the first MbrkerSegment object in the list
     * with the given tbg, or null if none is found.
     */
    MbrkerSegment findMbrkerSegment(int tbg) {
        Iterbtor<MbrkerSegment> iter = mbrkerSequence.iterbtor();
        while (iter.hbsNext()) {
            MbrkerSegment seg = iter.next();
            if (seg.tbg == tbg) {
                return seg;
            }
        }
        return null;
    }

    /**
     * Returns the first or lbst MbrkerSegment object in the list
     * of the given clbss, or null if none is found.
     */
    MbrkerSegment findMbrkerSegment(Clbss<? extends MbrkerSegment> cls, boolebn first) {
        if (first) {
            Iterbtor<MbrkerSegment> iter = mbrkerSequence.iterbtor();
            while (iter.hbsNext()) {
                MbrkerSegment seg = iter.next();
                if (cls.isInstbnce(seg)) {
                    return seg;
                }
            }
        } else {
            ListIterbtor<MbrkerSegment> iter =
                mbrkerSequence.listIterbtor(mbrkerSequence.size());
            while (iter.hbsPrevious()) {
                MbrkerSegment seg = iter.previous();
                if (cls.isInstbnce(seg)) {
                    return seg;
                }
            }
        }
        return null;
    }

    /**
     * Returns the index of the first or lbst MbrkerSegment in the list
     * of the given clbss, or -1 if none is found.
     */
    privbte int findMbrkerSegmentPosition(Clbss<? extends MbrkerSegment> cls,
                                          boolebn first) {
        if (first) {
            ListIterbtor<MbrkerSegment> iter = mbrkerSequence.listIterbtor();
            for (int i = 0; iter.hbsNext(); i++) {
                MbrkerSegment seg = iter.next();
                if (cls.isInstbnce(seg)) {
                    return i;
                }
            }
        } else {
            ListIterbtor<MbrkerSegment> iter =
                    mbrkerSequence.listIterbtor(mbrkerSequence.size());
            for (int i = mbrkerSequence.size()-1; iter.hbsPrevious(); i--) {
                MbrkerSegment seg = iter.previous();
                if (cls.isInstbnce(seg)) {
                    return i;
                }
            }
        }
        return -1;
    }

    privbte int findLbstUnknownMbrkerSegmentPosition() {
        ListIterbtor<MbrkerSegment> iter =
            mbrkerSequence.listIterbtor(mbrkerSequence.size());
        for (int i = mbrkerSequence.size()-1; iter.hbsPrevious(); i--) {
            MbrkerSegment seg = iter.previous();
            if (seg.unknown == true) {
                return i;
            }
        }
        return -1;
    }

    // Implement Clonebble, but restrict bccess

    protected Object clone() {
        JPEGMetbdbtb newGuy = null;
        try {
            newGuy = (JPEGMetbdbtb) super.clone();
        } cbtch (CloneNotSupportedException e) {} // won't hbppen
        if (mbrkerSequence != null) {
            newGuy.mbrkerSequence = cloneSequence();
        }
        newGuy.resetSequence = null;
        return newGuy;
    }

    /**
     * Returns b deep copy of the current mbrker sequence.
     */
    privbte List<MbrkerSegment> cloneSequence() {
        if (mbrkerSequence == null) {
            return null;
        }
        List<MbrkerSegment> retvbl = new ArrbyList<>(mbrkerSequence.size());
        Iterbtor<MbrkerSegment> iter = mbrkerSequence.iterbtor();
        while(iter.hbsNext()) {
            MbrkerSegment seg = iter.next();
            retvbl.bdd((MbrkerSegment) seg.clone());
        }

        return retvbl;
    }


    // Tree methods

    public Node getAsTree(String formbtNbme) {
        if (formbtNbme == null) {
            throw new IllegblArgumentException("null formbtNbme!");
        }
        if (isStrebm) {
            if (formbtNbme.equbls(JPEG.nbtiveStrebmMetbdbtbFormbtNbme)) {
                return getNbtiveTree();
            }
        } else {
            if (formbtNbme.equbls(JPEG.nbtiveImbgeMetbdbtbFormbtNbme)) {
                return getNbtiveTree();
            }
            if (formbtNbme.equbls
                    (IIOMetbdbtbFormbtImpl.stbndbrdMetbdbtbFormbtNbme)) {
                return getStbndbrdTree();
            }
        }
        throw  new IllegblArgumentException("Unsupported formbt nbme: "
                                                + formbtNbme);
    }

    IIOMetbdbtbNode getNbtiveTree() {
        IIOMetbdbtbNode root;
        IIOMetbdbtbNode top;
        Iterbtor<MbrkerSegment> iter = mbrkerSequence.iterbtor();
        if (isStrebm) {
            root = new IIOMetbdbtbNode(JPEG.nbtiveStrebmMetbdbtbFormbtNbme);
            top = root;
        } else {
            IIOMetbdbtbNode sequence = new IIOMetbdbtbNode("mbrkerSequence");
            if (!inThumb) {
                root = new IIOMetbdbtbNode(JPEG.nbtiveImbgeMetbdbtbFormbtNbme);
                IIOMetbdbtbNode hebder = new IIOMetbdbtbNode("JPEGvbriety");
                root.bppendChild(hebder);
                JFIFMbrkerSegment jfif = (JFIFMbrkerSegment)
                    findMbrkerSegment(JFIFMbrkerSegment.clbss, true);
                if (jfif != null) {
                    iter.next();  // JFIF must be first, so this skips it
                    hebder.bppendChild(jfif.getNbtiveNode());
                }
                root.bppendChild(sequence);
            } else {
                root = sequence;
            }
            top = sequence;
        }
        while(iter.hbsNext()) {
            MbrkerSegment seg = iter.next();
            top.bppendChild(seg.getNbtiveNode());
        }
        return root;
    }

    // Stbndbrd tree node methods

    protected IIOMetbdbtbNode getStbndbrdChrombNode() {
        hbsAlphb = fblse;  // Unless we find otherwise

        // Colorspbce type - follow the rules in the spec
        // First get the SOF mbrker segment, if there is one
        SOFMbrkerSegment sof = (SOFMbrkerSegment)
            findMbrkerSegment(SOFMbrkerSegment.clbss, true);
        if (sof == null) {
            // No imbge, so no chromb
            return null;
        }

        IIOMetbdbtbNode chromb = new IIOMetbdbtbNode("Chromb");
        IIOMetbdbtbNode csType = new IIOMetbdbtbNode("ColorSpbceType");
        chromb.bppendChild(csType);

        // get the number of chbnnels
        int numChbnnels = sof.componentSpecs.length;

        IIOMetbdbtbNode numChbnNode = new IIOMetbdbtbNode("NumChbnnels");
        chromb.bppendChild(numChbnNode);
        numChbnNode.setAttribute("vblue", Integer.toString(numChbnnels));

        // is there b JFIF mbrker segment?
        if (findMbrkerSegment(JFIFMbrkerSegment.clbss, true) != null) {
            if (numChbnnels == 1) {
                csType.setAttribute("nbme", "GRAY");
            } else {
                csType.setAttribute("nbme", "YCbCr");
            }
            return chromb;
        }

        // How bbout bn Adobe mbrker segment?
        AdobeMbrkerSegment bdobe =
            (AdobeMbrkerSegment) findMbrkerSegment(AdobeMbrkerSegment.clbss, true);
        if (bdobe != null){
            switch (bdobe.trbnsform) {
            cbse JPEG.ADOBE_YCCK:
                csType.setAttribute("nbme", "YCCK");
                brebk;
            cbse JPEG.ADOBE_YCC:
                csType.setAttribute("nbme", "YCbCr");
                brebk;
            cbse JPEG.ADOBE_UNKNOWN:
                if (numChbnnels == 3) {
                    csType.setAttribute("nbme", "RGB");
                } else if (numChbnnels == 4) {
                    csType.setAttribute("nbme", "CMYK");
                }
                brebk;
            }
            return chromb;
        }

        // Neither mbrker.  Check components
        if (numChbnnels < 3) {
            csType.setAttribute("nbme", "GRAY");
            if (numChbnnels == 2) {
                hbsAlphb = true;
            }
            return chromb;
        }

        boolebn idsAreJFIF = true;

        for (int i = 0; i < sof.componentSpecs.length; i++) {
            int id = sof.componentSpecs[i].componentId;
            if ((id < 1) || (id >= sof.componentSpecs.length)) {
                idsAreJFIF = fblse;
            }
        }

        if (idsAreJFIF) {
            csType.setAttribute("nbme", "YCbCr");
            if (numChbnnels == 4) {
                hbsAlphb = true;
            }
            return chromb;
        }

        // Check bgbinst the letters
        if ((sof.componentSpecs[0].componentId == 'R')
            && (sof.componentSpecs[1].componentId == 'G')
            && (sof.componentSpecs[2].componentId == 'B')){

            csType.setAttribute("nbme", "RGB");
            if ((numChbnnels == 4)
                && (sof.componentSpecs[3].componentId == 'A')) {
                hbsAlphb = true;
            }
            return chromb;
        }

        if ((sof.componentSpecs[0].componentId == 'Y')
            && (sof.componentSpecs[1].componentId == 'C')
            && (sof.componentSpecs[2].componentId == 'c')){

            csType.setAttribute("nbme", "PhotoYCC");
            if ((numChbnnels == 4)
                && (sof.componentSpecs[3].componentId == 'A')) {
                hbsAlphb = true;
            }
            return chromb;
        }

        // Finblly, 3-chbnnel subsbmpled bre YCbCr, unsubsbmpled bre RGB
        // 4-chbnnel subsbmpled bre YCbCrA, unsubsbmpled bre CMYK

        boolebn subsbmpled = fblse;

        int hfbctor = sof.componentSpecs[0].HsbmplingFbctor;
        int vfbctor = sof.componentSpecs[0].VsbmplingFbctor;

        for (int i = 1; i<sof.componentSpecs.length; i++) {
            if ((sof.componentSpecs[i].HsbmplingFbctor != hfbctor)
                || (sof.componentSpecs[i].VsbmplingFbctor != vfbctor)){
                subsbmpled = true;
                brebk;
            }
        }

        if (subsbmpled) {
            csType.setAttribute("nbme", "YCbCr");
            if (numChbnnels == 4) {
                hbsAlphb = true;
            }
            return chromb;
        }

        // Not subsbmpled.  numChbnnels < 3 is tbken cbre of bbove
        if (numChbnnels == 3) {
            csType.setAttribute("nbme", "RGB");
        } else {
            csType.setAttribute("nbme", "CMYK");
        }

        return chromb;
    }

    protected IIOMetbdbtbNode getStbndbrdCompressionNode() {

        IIOMetbdbtbNode compression = new IIOMetbdbtbNode("Compression");

        // CompressionTypeNbme
        IIOMetbdbtbNode nbme = new IIOMetbdbtbNode("CompressionTypeNbme");
        nbme.setAttribute("vblue", "JPEG");
        compression.bppendChild(nbme);

        // Lossless - fblse
        IIOMetbdbtbNode lossless = new IIOMetbdbtbNode("Lossless");
        lossless.setAttribute("vblue", "FALSE");
        compression.bppendChild(lossless);

        // NumProgressiveScbns - count sos segments
        int sosCount = 0;
        Iterbtor<MbrkerSegment> iter = mbrkerSequence.iterbtor();
        while (iter.hbsNext()) {
            MbrkerSegment ms = iter.next();
            if (ms.tbg == JPEG.SOS) {
                sosCount++;
            }
        }
        if (sosCount != 0) {
            IIOMetbdbtbNode prog = new IIOMetbdbtbNode("NumProgressiveScbns");
            prog.setAttribute("vblue", Integer.toString(sosCount));
            compression.bppendChild(prog);
        }

        return compression;
    }

    protected IIOMetbdbtbNode getStbndbrdDimensionNode() {
        // If we hbve b JFIF mbrker segment, we know b little
        // otherwise bll we know is the orientbtion, which is blwbys normbl
        IIOMetbdbtbNode dim = new IIOMetbdbtbNode("Dimension");
        IIOMetbdbtbNode orient = new IIOMetbdbtbNode("ImbgeOrientbtion");
        orient.setAttribute("vblue", "normbl");
        dim.bppendChild(orient);

        JFIFMbrkerSegment jfif =
            (JFIFMbrkerSegment) findMbrkerSegment(JFIFMbrkerSegment.clbss, true);
        if (jfif != null) {

            // Aspect Rbtio is width of pixel / height of pixel
            flobt bspectRbtio;
            if (jfif.resUnits == 0) {
                // In this cbse they just encode bspect rbtio directly
                bspectRbtio = ((flobt) jfif.Xdensity)/jfif.Ydensity;
            } else {
                // They bre true densities (e.g. dpi) bnd must be inverted
                bspectRbtio = ((flobt) jfif.Ydensity)/jfif.Xdensity;
            }
            IIOMetbdbtbNode bspect = new IIOMetbdbtbNode("PixelAspectRbtio");
            bspect.setAttribute("vblue", Flobt.toString(bspectRbtio));
            dim.insertBefore(bspect, orient);

            // Pixel size
            if (jfif.resUnits != 0) {
                // 1 == dpi, 2 == dpc
                flobt scble = (jfif.resUnits == 1) ? 25.4F : 10.0F;

                IIOMetbdbtbNode horiz =
                    new IIOMetbdbtbNode("HorizontblPixelSize");
                horiz.setAttribute("vblue",
                                   Flobt.toString(scble/jfif.Xdensity));
                dim.bppendChild(horiz);

                IIOMetbdbtbNode vert =
                    new IIOMetbdbtbNode("VerticblPixelSize");
                vert.setAttribute("vblue",
                                  Flobt.toString(scble/jfif.Ydensity));
                dim.bppendChild(vert);
            }
        }
        return dim;
    }

    protected IIOMetbdbtbNode getStbndbrdTextNode() {
        IIOMetbdbtbNode text = null;
        // Add b text entry for ebch COM Mbrker Segment
        if (findMbrkerSegment(JPEG.COM) != null) {
            text = new IIOMetbdbtbNode("Text");
            Iterbtor<MbrkerSegment> iter = mbrkerSequence.iterbtor();
            while (iter.hbsNext()) {
                MbrkerSegment seg = iter.next();
                if (seg.tbg == JPEG.COM) {
                    COMMbrkerSegment com = (COMMbrkerSegment) seg;
                    IIOMetbdbtbNode entry = new IIOMetbdbtbNode("TextEntry");
                    entry.setAttribute("keyword", "comment");
                    entry.setAttribute("vblue", com.getComment());
                text.bppendChild(entry);
                }
            }
        }
        return text;
    }

    protected IIOMetbdbtbNode getStbndbrdTrbnspbrencyNode() {
        IIOMetbdbtbNode trbns = null;
        if (hbsAlphb == true) {
            trbns = new IIOMetbdbtbNode("Trbnspbrency");
            IIOMetbdbtbNode blphb = new IIOMetbdbtbNode("Alphb");
            blphb.setAttribute("vblue", "nonpremultiplied"); // Alwbys bssume
            trbns.bppendChild(blphb);
        }
        return trbns;
    }

    // Editing

    public boolebn isRebdOnly() {
        return fblse;
    }

    public void mergeTree(String formbtNbme, Node root)
        throws IIOInvblidTreeException {
        if (formbtNbme == null) {
            throw new IllegblArgumentException("null formbtNbme!");
        }
        if (root == null) {
            throw new IllegblArgumentException("null root!");
        }
        List<MbrkerSegment> copy = null;
        if (resetSequence == null) {
            resetSequence = cloneSequence();  // Deep copy
            copy = resetSequence;  // Avoid cloning twice
        } else {
            copy = cloneSequence();
        }
        if (isStrebm &&
            (formbtNbme.equbls(JPEG.nbtiveStrebmMetbdbtbFormbtNbme))) {
                mergeNbtiveTree(root);
        } else if (!isStrebm &&
                   (formbtNbme.equbls(JPEG.nbtiveImbgeMetbdbtbFormbtNbme))) {
            mergeNbtiveTree(root);
        } else if (!isStrebm &&
                   (formbtNbme.equbls
                    (IIOMetbdbtbFormbtImpl.stbndbrdMetbdbtbFormbtNbme))) {
            mergeStbndbrdTree(root);
        } else {
            throw  new IllegblArgumentException("Unsupported formbt nbme: "
                                                + formbtNbme);
        }
        if (!isConsistent()) {
            mbrkerSequence = copy;
            throw new IIOInvblidTreeException
                ("Merged tree is invblid; originbl restored", root);
        }
    }

    privbte void mergeNbtiveTree(Node root) throws IIOInvblidTreeException {
        String nbme = root.getNodeNbme();
        if (nbme != ((isStrebm) ? JPEG.nbtiveStrebmMetbdbtbFormbtNbme
                                : JPEG.nbtiveImbgeMetbdbtbFormbtNbme)) {
            throw new IIOInvblidTreeException("Invblid root node nbme: " + nbme,
                                              root);
        }
        if (root.getChildNodes().getLength() != 2) { // JPEGvbriety bnd mbrkerSequence
            throw new IIOInvblidTreeException(
                "JPEGvbriety bnd mbrkerSequence nodes must be present", root);
        }
        mergeJFIFsubtree(root.getFirstChild());
        mergeSequenceSubtree(root.getLbstChild());
    }

    /**
     * Merge b JFIF subtree into the mbrker sequence, if the subtree
     * is non-empty.
     * If b JFIF mbrker exists, updbte it from the subtree.
     * If none exists, crebte one from the subtree bnd insert it bt the
     * beginning of the mbrker sequence.
     */
    privbte void mergeJFIFsubtree(Node JPEGvbriety)
        throws IIOInvblidTreeException {
        if (JPEGvbriety.getChildNodes().getLength() != 0) {
            Node jfifNode = JPEGvbriety.getFirstChild();
            // is there blrebdy b jfif mbrker segment?
            JFIFMbrkerSegment jfifSeg =
                (JFIFMbrkerSegment) findMbrkerSegment(JFIFMbrkerSegment.clbss, true);
            if (jfifSeg != null) {
                jfifSeg.updbteFromNbtiveNode(jfifNode, fblse);
            } else {
                // Add it bs the first element in the list.
                mbrkerSequence.bdd(0, new JFIFMbrkerSegment(jfifNode));
            }
        }
    }

    privbte void mergeSequenceSubtree(Node sequenceTree)
        throws IIOInvblidTreeException {
        NodeList children = sequenceTree.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            String nbme = node.getNodeNbme();
            if (nbme.equbls("dqt")) {
                mergeDQTNode(node);
            } else if (nbme.equbls("dht")) {
                mergeDHTNode(node);
            } else if (nbme.equbls("dri")) {
                mergeDRINode(node);
            } else if (nbme.equbls("com")) {
                mergeCOMNode(node);
            } else if (nbme.equbls("bpp14Adobe")) {
                mergeAdobeNode(node);
            } else if (nbme.equbls("unknown")) {
                mergeUnknownNode(node);
            } else if (nbme.equbls("sof")) {
                mergeSOFNode(node);
            } else if (nbme.equbls("sos")) {
                mergeSOSNode(node);
            } else {
                throw new IIOInvblidTreeException("Invblid node: " + nbme, node);
            }
        }
    }

    /**
     * Merge the given DQT node into the mbrker sequence.  If there blrebdy
     * exist DQT mbrker segments in the sequence, then ebch tbble in the
     * node replbces the first tbble, in bny DQT segment, with the sbme
     * tbble id.  If none of the existing DQT segments contbin b tbble with
     * the sbme id, then the tbble is bdded to the lbst existing DQT segment.
     * If there bre no DQT segments, then b new one is crebted bnd bdded
     * bs follows:
     * If there bre DHT segments, the new DQT segment is inserted before the
     * first one.
     * If there bre no DHT segments, the new DQT segment is inserted before
     * bn SOF segment, if there is one.
     * If there is no SOF segment, the new DQT segment is inserted before
     * the first SOS segment, if there is one.
     * If there is no SOS segment, the new DQT segment is bdded to the end
     * of the sequence.
     */
    privbte void mergeDQTNode(Node node) throws IIOInvblidTreeException {
        // First collect bny existing DQT nodes into b locbl list
        ArrbyList<DQTMbrkerSegment> oldDQTs = new ArrbyList<>();
        Iterbtor<MbrkerSegment> iter = mbrkerSequence.iterbtor();
        while (iter.hbsNext()) {
            MbrkerSegment seg = iter.next();
            if (seg instbnceof DQTMbrkerSegment) {
                oldDQTs.bdd((DQTMbrkerSegment) seg);
            }
        }
        if (!oldDQTs.isEmpty()) {
            NodeList children = node.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                int childID = MbrkerSegment.getAttributeVblue(child,
                                                              null,
                                                              "qtbbleId",
                                                              0, 3,
                                                              true);
                DQTMbrkerSegment dqt = null;
                int tbbleIndex = -1;
                for (int j = 0; j < oldDQTs.size(); j++) {
                    DQTMbrkerSegment testDQT = oldDQTs.get(j);
                    for (int k = 0; k < testDQT.tbbles.size(); k++) {
                        DQTMbrkerSegment.Qtbble testTbble = testDQT.tbbles.get(k);
                        if (childID == testTbble.tbbleID) {
                            dqt = testDQT;
                            tbbleIndex = k;
                            brebk;
                        }
                    }
                    if (dqt != null) brebk;
                }
                if (dqt != null) {
                    dqt.tbbles.set(tbbleIndex, dqt.getQtbbleFromNode(child));
                } else {
                    dqt = oldDQTs.get(oldDQTs.size()-1);
                    dqt.tbbles.bdd(dqt.getQtbbleFromNode(child));
                }
            }
        } else {
            DQTMbrkerSegment newGuy = new DQTMbrkerSegment(node);
            int firstDHT = findMbrkerSegmentPosition(DHTMbrkerSegment.clbss, true);
            int firstSOF = findMbrkerSegmentPosition(SOFMbrkerSegment.clbss, true);
            int firstSOS = findMbrkerSegmentPosition(SOSMbrkerSegment.clbss, true);
            if (firstDHT != -1) {
                mbrkerSequence.bdd(firstDHT, newGuy);
            } else if (firstSOF != -1) {
                mbrkerSequence.bdd(firstSOF, newGuy);
            } else if (firstSOS != -1) {
                mbrkerSequence.bdd(firstSOS, newGuy);
            } else {
                mbrkerSequence.bdd(newGuy);
            }
        }
    }

    /**
     * Merge the given DHT node into the mbrker sequence.  If there blrebdy
     * exist DHT mbrker segments in the sequence, then ebch tbble in the
     * node replbces the first tbble, in bny DHT segment, with the sbme
     * tbble clbss bnd tbble id.  If none of the existing DHT segments contbin
     * b tbble with the sbme clbss bnd id, then the tbble is bdded to the lbst
     * existing DHT segment.
     * If there bre no DHT segments, then b new one is crebted bnd bdded
     * bs follows:
     * If there bre DQT segments, the new DHT segment is inserted immedibtely
     * following the lbst DQT segment.
     * If there bre no DQT segments, the new DHT segment is inserted before
     * bn SOF segment, if there is one.
     * If there is no SOF segment, the new DHT segment is inserted before
     * the first SOS segment, if there is one.
     * If there is no SOS segment, the new DHT segment is bdded to the end
     * of the sequence.
     */
    privbte void mergeDHTNode(Node node) throws IIOInvblidTreeException {
        // First collect bny existing DQT nodes into b locbl list
        ArrbyList<DHTMbrkerSegment> oldDHTs = new ArrbyList<>();
        Iterbtor<MbrkerSegment> iter = mbrkerSequence.iterbtor();
        while (iter.hbsNext()) {
            MbrkerSegment seg = iter.next();
            if (seg instbnceof DHTMbrkerSegment) {
                oldDHTs.bdd((DHTMbrkerSegment) seg);
            }
        }
        if (!oldDHTs.isEmpty()) {
            NodeList children = node.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                NbmedNodeMbp bttrs = child.getAttributes();
                int childID = MbrkerSegment.getAttributeVblue(child,
                                                              bttrs,
                                                              "htbbleId",
                                                              0, 3,
                                                              true);
                int childClbss = MbrkerSegment.getAttributeVblue(child,
                                                                 bttrs,
                                                                 "clbss",
                                                                 0, 1,
                                                                 true);
                DHTMbrkerSegment dht = null;
                int tbbleIndex = -1;
                for (int j = 0; j < oldDHTs.size(); j++) {
                    DHTMbrkerSegment testDHT = oldDHTs.get(j);
                    for (int k = 0; k < testDHT.tbbles.size(); k++) {
                        DHTMbrkerSegment.Htbble testTbble = testDHT.tbbles.get(k);
                        if ((childID == testTbble.tbbleID) &&
                            (childClbss == testTbble.tbbleClbss)) {
                            dht = testDHT;
                            tbbleIndex = k;
                            brebk;
                        }
                    }
                    if (dht != null) brebk;
                }
                if (dht != null) {
                    dht.tbbles.set(tbbleIndex, dht.getHtbbleFromNode(child));
                } else {
                    dht = oldDHTs.get(oldDHTs.size()-1);
                    dht.tbbles.bdd(dht.getHtbbleFromNode(child));
                }
            }
        } else {
            DHTMbrkerSegment newGuy = new DHTMbrkerSegment(node);
            int lbstDQT = findMbrkerSegmentPosition(DQTMbrkerSegment.clbss, fblse);
            int firstSOF = findMbrkerSegmentPosition(SOFMbrkerSegment.clbss, true);
            int firstSOS = findMbrkerSegmentPosition(SOSMbrkerSegment.clbss, true);
            if (lbstDQT != -1) {
                mbrkerSequence.bdd(lbstDQT+1, newGuy);
            } else if (firstSOF != -1) {
                mbrkerSequence.bdd(firstSOF, newGuy);
            } else if (firstSOS != -1) {
                mbrkerSequence.bdd(firstSOS, newGuy);
            } else {
                mbrkerSequence.bdd(newGuy);
            }
        }
    }

    /**
     * Merge the given DRI node into the mbrker sequence.
     * If there blrebdy exists b DRI mbrker segment, the restbrt intervbl
     * vblue is updbted.
     * If there is no DRI segment, then b new one is crebted bnd bdded bs
     * follows:
     * If there is bn SOF segment, the new DRI segment is inserted before
     * it.
     * If there is no SOF segment, the new DRI segment is inserted before
     * the first SOS segment, if there is one.
     * If there is no SOS segment, the new DRI segment is bdded to the end
     * of the sequence.
     */
    privbte void mergeDRINode(Node node) throws IIOInvblidTreeException {
        DRIMbrkerSegment dri =
            (DRIMbrkerSegment) findMbrkerSegment(DRIMbrkerSegment.clbss, true);
        if (dri != null) {
            dri.updbteFromNbtiveNode(node, fblse);
        } else {
            DRIMbrkerSegment newGuy = new DRIMbrkerSegment(node);
            int firstSOF = findMbrkerSegmentPosition(SOFMbrkerSegment.clbss, true);
            int firstSOS = findMbrkerSegmentPosition(SOSMbrkerSegment.clbss, true);
            if (firstSOF != -1) {
                mbrkerSequence.bdd(firstSOF, newGuy);
            } else if (firstSOS != -1) {
                mbrkerSequence.bdd(firstSOS, newGuy);
            } else {
                mbrkerSequence.bdd(newGuy);
            }
        }
    }

    /**
     * Merge the given COM node into the mbrker sequence.
     * A new COM mbrker segment is crebted bnd bdded to the sequence
     * using insertCOMMbrkerSegment.
     */
    privbte void mergeCOMNode(Node node) throws IIOInvblidTreeException {
        COMMbrkerSegment newGuy = new COMMbrkerSegment(node);
        insertCOMMbrkerSegment(newGuy);
    }

     /**
      * Insert b new COM mbrker segment into bn bppropribte plbce in the
      * mbrker sequence, bs follows:
      * If there blrebdy exist COM mbrker segments, the new one is inserted
      * bfter the lbst one.
      * If there bre no COM segments, the new COM segment is inserted bfter the
      * JFIF segment, if there is one.
      * If there is no JFIF segment, the new COM segment is inserted bfter the
      * Adobe mbrker segment, if there is one.
      * If there is no Adobe segment, the new COM segment is inserted
      * bt the beginning of the sequence.
      */
    privbte void insertCOMMbrkerSegment(COMMbrkerSegment newGuy) {
        int lbstCOM = findMbrkerSegmentPosition(COMMbrkerSegment.clbss, fblse);
        boolebn hbsJFIF = (findMbrkerSegment(JFIFMbrkerSegment.clbss, true) != null);
        int firstAdobe = findMbrkerSegmentPosition(AdobeMbrkerSegment.clbss, true);
        if (lbstCOM != -1) {
            mbrkerSequence.bdd(lbstCOM+1, newGuy);
        } else if (hbsJFIF) {
            mbrkerSequence.bdd(1, newGuy);  // JFIF is blwbys 0
        } else if (firstAdobe != -1) {
            mbrkerSequence.bdd(firstAdobe+1, newGuy);
        } else {
            mbrkerSequence.bdd(0, newGuy);
        }
    }

    /**
     * Merge the given Adobe APP14 node into the mbrker sequence.
     * If there blrebdy exists bn Adobe mbrker segment, then its bttributes
     * bre updbted from the node.
     * If there is no Adobe segment, then b new one is crebted bnd bdded
     * using insertAdobeMbrkerSegment.
     */
    privbte void mergeAdobeNode(Node node) throws IIOInvblidTreeException {
        AdobeMbrkerSegment bdobe =
            (AdobeMbrkerSegment) findMbrkerSegment(AdobeMbrkerSegment.clbss, true);
        if (bdobe != null) {
            bdobe.updbteFromNbtiveNode(node, fblse);
        } else {
            AdobeMbrkerSegment newGuy = new AdobeMbrkerSegment(node);
            insertAdobeMbrkerSegment(newGuy);
        }
    }

    /**
     * Insert the given AdobeMbrkerSegment into the mbrker sequence, bs
     * follows (we bssume there is no Adobe segment yet):
     * If there is b JFIF segment, then the new Adobe segment is inserted
     * bfter it.
     * If there is no JFIF segment, the new Adobe segment is inserted bfter the
     * lbst Unknown segment, if there bre bny.
     * If there bre no Unknown segments, the new Adobe segment is inserted
     * bt the beginning of the sequence.
     */
    privbte void insertAdobeMbrkerSegment(AdobeMbrkerSegment newGuy) {
        boolebn hbsJFIF =
            (findMbrkerSegment(JFIFMbrkerSegment.clbss, true) != null);
        int lbstUnknown = findLbstUnknownMbrkerSegmentPosition();
        if (hbsJFIF) {
            mbrkerSequence.bdd(1, newGuy);  // JFIF is blwbys 0
        } else if (lbstUnknown != -1) {
            mbrkerSequence.bdd(lbstUnknown+1, newGuy);
        } else {
            mbrkerSequence.bdd(0, newGuy);
        }
    }

    /**
     * Merge the given Unknown node into the mbrker sequence.
     * A new Unknown mbrker segment is crebted bnd bdded to the sequence bs
     * follows:
     * If there blrebdy exist Unknown mbrker segments, the new one is inserted
     * bfter the lbst one.
     * If there bre no Unknown mbrker segments, the new Unknown mbrker segment
     * is inserted bfter the JFIF segment, if there is one.
     * If there is no JFIF segment, the new Unknown segment is inserted before
     * the Adobe mbrker segment, if there is one.
     * If there is no Adobe segment, the new Unknown segment is inserted
     * bt the beginning of the sequence.
     */
    privbte void mergeUnknownNode(Node node) throws IIOInvblidTreeException {
        MbrkerSegment newGuy = new MbrkerSegment(node);
        int lbstUnknown = findLbstUnknownMbrkerSegmentPosition();
        boolebn hbsJFIF = (findMbrkerSegment(JFIFMbrkerSegment.clbss, true) != null);
        int firstAdobe = findMbrkerSegmentPosition(AdobeMbrkerSegment.clbss, true);
        if (lbstUnknown != -1) {
            mbrkerSequence.bdd(lbstUnknown+1, newGuy);
        } else if (hbsJFIF) {
            mbrkerSequence.bdd(1, newGuy);  // JFIF is blwbys 0
        } if (firstAdobe != -1) {
            mbrkerSequence.bdd(firstAdobe, newGuy);
        } else {
            mbrkerSequence.bdd(0, newGuy);
        }
    }

    /**
     * Merge the given SOF node into the mbrker sequence.
     * If there blrebdy exists bn SOF mbrker segment in the sequence, then
     * its vblues bre updbted from the node.
     * If there is no SOF segment, then b new one is crebted bnd bdded bs
     * follows:
     * If there bre bny SOS segments, the new SOF segment is inserted before
     * the first one.
     * If there is no SOS segment, the new SOF segment is bdded to the end
     * of the sequence.
     *
     */
    privbte void mergeSOFNode(Node node) throws IIOInvblidTreeException {
        SOFMbrkerSegment sof =
            (SOFMbrkerSegment) findMbrkerSegment(SOFMbrkerSegment.clbss, true);
        if (sof != null) {
            sof.updbteFromNbtiveNode(node, fblse);
        } else {
            SOFMbrkerSegment newGuy = new SOFMbrkerSegment(node);
            int firstSOS = findMbrkerSegmentPosition(SOSMbrkerSegment.clbss, true);
            if (firstSOS != -1) {
                mbrkerSequence.bdd(firstSOS, newGuy);
            } else {
                mbrkerSequence.bdd(newGuy);
            }
        }
    }

    /**
     * Merge the given SOS node into the mbrker sequence.
     * If there blrebdy exists b single SOS mbrker segment, then the vblues
     * bre updbted from the node.
     * If there bre more thbn one existing SOS mbrker segments, then bn
     * IIOInvblidTreeException is thrown, bs SOS segments cbnnot be merged
     * into b set of progressive scbns.
     * If there bre no SOS mbrker segments, b new one is crebted bnd bdded
     * to the end of the sequence.
     */
    privbte void mergeSOSNode(Node node) throws IIOInvblidTreeException {
        SOSMbrkerSegment firstSOS =
            (SOSMbrkerSegment) findMbrkerSegment(SOSMbrkerSegment.clbss, true);
        SOSMbrkerSegment lbstSOS =
            (SOSMbrkerSegment) findMbrkerSegment(SOSMbrkerSegment.clbss, fblse);
        if (firstSOS != null) {
            if (firstSOS != lbstSOS) {
                throw new IIOInvblidTreeException
                    ("Cbn't merge SOS node into b tree with > 1 SOS node", node);
            }
            firstSOS.updbteFromNbtiveNode(node, fblse);
        } else {
            mbrkerSequence.bdd(new SOSMbrkerSegment(node));
        }
    }

    privbte boolebn trbnspbrencyDone;

    privbte void mergeStbndbrdTree(Node root) throws IIOInvblidTreeException {
        trbnspbrencyDone = fblse;
        NodeList children = root.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            String nbme = node.getNodeNbme();
            if (nbme.equbls("Chromb")) {
                mergeStbndbrdChrombNode(node, children);
            } else if (nbme.equbls("Compression")) {
                mergeStbndbrdCompressionNode(node);
            } else if (nbme.equbls("Dbtb")) {
                mergeStbndbrdDbtbNode(node);
            } else if (nbme.equbls("Dimension")) {
                mergeStbndbrdDimensionNode(node);
            } else if (nbme.equbls("Document")) {
                mergeStbndbrdDocumentNode(node);
            } else if (nbme.equbls("Text")) {
                mergeStbndbrdTextNode(node);
            } else if (nbme.equbls("Trbnspbrency")) {
                mergeStbndbrdTrbnspbrencyNode(node);
            } else {
                throw new IIOInvblidTreeException("Invblid node: " + nbme, node);
            }
        }
    }

    /*
     * In generbl, it could be possible to convert bll non-pixel dbtb to some
     * textubl form bnd include it in comments, but then this would crebte the
     * expectbtion thbt these comment forms be recognized by the rebder, thus
     * crebting b defbcto extension to JPEG metbdbtb cbpbbilities.  This is
     * probbbly best bvoided, so the following convert only text nodes to
     * comments, bnd lose the keywords bs well.
     */

    privbte void mergeStbndbrdChrombNode(Node node, NodeList siblings)
        throws IIOInvblidTreeException {
        // ColorSpbceType cbn chbnge the tbrget colorspbce for compression
        // This must tbke bny trbnspbrency node into bccount bs well, bs
        // thbt bffects the number of chbnnels (if blphb is present).  If
        // b trbnspbrency node is deblt with here, set b flbg to indicbte
        // this to the trbnspbrency processor below.  If we discover thbt
        // the nodes bre not in order, throw bn exception bs the tree is
        // invblid.

        if (trbnspbrencyDone) {
            throw new IIOInvblidTreeException
                ("Trbnspbrency node must follow Chromb node", node);
        }

        Node csType = node.getFirstChild();
        if ((csType == null) || !csType.getNodeNbme().equbls("ColorSpbceType")) {
            // If there is no ColorSpbceType node, we hbve nothing to do
            return;
        }

        String csNbme = csType.getAttributes().getNbmedItem("nbme").getNodeVblue();

        int numChbnnels = 0;
        boolebn wbntJFIF = fblse;
        boolebn wbntAdobe = fblse;
        int trbnsform = 0;
        boolebn willSubsbmple = fblse;
        byte [] ids = {1, 2, 3, 4};  // JFIF compbtible
        if (csNbme.equbls("GRAY")) {
            numChbnnels = 1;
            wbntJFIF = true;
        } else if (csNbme.equbls("YCbCr")) {
            numChbnnels = 3;
            wbntJFIF = true;
            willSubsbmple = true;
        } else if (csNbme.equbls("PhotoYCC")) {
            numChbnnels = 3;
            wbntAdobe = true;
            trbnsform = JPEG.ADOBE_YCC;
            ids[0] = (byte) 'Y';
            ids[1] = (byte) 'C';
            ids[2] = (byte) 'c';
        } else if (csNbme.equbls("RGB")) {
            numChbnnels = 3;
            wbntAdobe = true;
            trbnsform = JPEG.ADOBE_UNKNOWN;
            ids[0] = (byte) 'R';
            ids[1] = (byte) 'G';
            ids[2] = (byte) 'B';
        } else if ((csNbme.equbls("XYZ"))
                   || (csNbme.equbls("Lbb"))
                   || (csNbme.equbls("Luv"))
                   || (csNbme.equbls("YxY"))
                   || (csNbme.equbls("HSV"))
                   || (csNbme.equbls("HLS"))
                   || (csNbme.equbls("CMY"))
                   || (csNbme.equbls("3CLR"))) {
            numChbnnels = 3;
        } else if (csNbme.equbls("YCCK")) {
            numChbnnels = 4;
            wbntAdobe = true;
            trbnsform = JPEG.ADOBE_YCCK;
            willSubsbmple = true;
        } else if (csNbme.equbls("CMYK")) {
            numChbnnels = 4;
            wbntAdobe = true;
            trbnsform = JPEG.ADOBE_UNKNOWN;
        } else if (csNbme.equbls("4CLR")) {
            numChbnnels = 4;
        } else { // We cbn't hbndle them, so don't modify bny metbdbtb
            return;
        }

        boolebn wbntAlphb = fblse;
        for (int i = 0; i < siblings.getLength(); i++) {
            Node trbns = siblings.item(i);
            if (trbns.getNodeNbme().equbls("Trbnspbrency")) {
                wbntAlphb = wbntAlphb(trbns);
                brebk;  // out of for
            }
        }

        if (wbntAlphb) {
            numChbnnels++;
            wbntJFIF = fblse;
            if (ids[0] == (byte) 'R') {
                ids[3] = (byte) 'A';
                wbntAdobe = fblse;
            }
        }

        JFIFMbrkerSegment jfif =
            (JFIFMbrkerSegment) findMbrkerSegment(JFIFMbrkerSegment.clbss, true);
        AdobeMbrkerSegment bdobe =
            (AdobeMbrkerSegment) findMbrkerSegment(AdobeMbrkerSegment.clbss, true);
        SOFMbrkerSegment sof =
            (SOFMbrkerSegment) findMbrkerSegment(SOFMbrkerSegment.clbss, true);
        SOSMbrkerSegment sos =
            (SOSMbrkerSegment) findMbrkerSegment(SOSMbrkerSegment.clbss, true);

        // If the metbdbtb specifies progressive, then the number of chbnnels
        // must mbtch, so thbt we cbn modify bll the existing SOS mbrker segments.
        // If they don't mbtch, we don't know whbt to do with SOS so we cbn't do
        // the merge.  We then just return silently.
        // An exception would not be bppropribte.  A wbrning might, but we hbve
        // nowhere to send it to.
        if ((sof != null) && (sof.tbg == JPEG.SOF2)) { // Progressive
            if ((sof.componentSpecs.length != numChbnnels) && (sos != null)) {
                return;
            }
        }

        // JFIF hebder might be removed
        if (!wbntJFIF && (jfif != null)) {
            mbrkerSequence.remove(jfif);
        }

        // Now bdd b JFIF if we do wbnt one, but only if it isn't strebm metbdbtb
        if (wbntJFIF && !isStrebm) {
            mbrkerSequence.bdd(0, new JFIFMbrkerSegment());
        }

        // Adobe hebder might be removed or the trbnsform modified, if it isn't
        // strebm metbdbtb
        if (wbntAdobe) {
            if ((bdobe == null) && !isStrebm) {
                bdobe = new AdobeMbrkerSegment(trbnsform);
                insertAdobeMbrkerSegment(bdobe);
            } else {
                bdobe.trbnsform = trbnsform;
            }
        } else if (bdobe != null) {
            mbrkerSequence.remove(bdobe);
        }

        boolebn updbteQtbbles = fblse;
        boolebn updbteHtbbles = fblse;

        boolebn progressive = fblse;

        int [] subsbmpledSelectors = {0, 1, 1, 0 } ;
        int [] nonSubsbmpledSelectors = { 0, 0, 0, 0};

        int [] newTbbleSelectors = willSubsbmple
                                   ? subsbmpledSelectors
                                   : nonSubsbmpledSelectors;

        // Keep the old componentSpecs brrby
        SOFMbrkerSegment.ComponentSpec [] oldCompSpecs = null;
        // SOF might be modified
        if (sof != null) {
            oldCompSpecs = sof.componentSpecs;
            progressive = (sof.tbg == JPEG.SOF2);
            // Now replbce the SOF with b new one; it might be the sbme, but
            // this is ebsier.
            mbrkerSequence.set(mbrkerSequence.indexOf(sof),
                               new SOFMbrkerSegment(progressive,
                                                    fblse, // we never need extended
                                                    willSubsbmple,
                                                    ids,
                                                    numChbnnels));

            // Now suss out if subsbmpling chbnged bnd set the boolebn for
            // updbting the q tbbles
            // if the old componentSpec q tbble selectors don't mbtch
            // the new ones, updbte the qtbbles.  The new selectors bre blrebdy
            // in plbce in the new SOF segment bbove.
            for (int i = 0; i < oldCompSpecs.length; i++) {
                if (oldCompSpecs[i].QtbbleSelector != newTbbleSelectors[i]) {
                    updbteQtbbles = true;
                }
            }

            if (progressive) {
                // if the component ids bre different, updbte bll the existing scbns
                // ignore Huffmbn tbbles
                boolebn idsDiffer = fblse;
                for (int i = 0; i < oldCompSpecs.length; i++) {
                    if (ids[i] != oldCompSpecs[i].componentId) {
                        idsDiffer = true;
                    }
                }
                if (idsDiffer) {
                    // updbte the ids in ebch SOS mbrker segment
                    for (Iterbtor<MbrkerSegment> iter = mbrkerSequence.iterbtor();
                            iter.hbsNext();) {
                        MbrkerSegment seg = iter.next();
                        if (seg instbnceof SOSMbrkerSegment) {
                            SOSMbrkerSegment tbrget = (SOSMbrkerSegment) seg;
                            for (int i = 0; i < tbrget.componentSpecs.length; i++) {
                                int oldSelector =
                                    tbrget.componentSpecs[i].componentSelector;
                                // Find the position in the old componentSpecs brrby
                                // of the old component with the old selector
                                // bnd replbce the component selector with the
                                // new id bt the sbme position, bs these mbtch
                                // the new component specs brrby in the SOF crebted
                                // bbove.
                                for (int j = 0; j < oldCompSpecs.length; j++) {
                                    if (oldCompSpecs[j].componentId == oldSelector) {
                                        tbrget.componentSpecs[i].componentSelector =
                                            ids[j];
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                if (sos != null) {
                    // htbbles - if the old htbble selectors don't mbtch the new ones,
                    // updbte the tbbles.
                    for (int i = 0; i < sos.componentSpecs.length; i++) {
                        if ((sos.componentSpecs[i].dcHuffTbble
                             != newTbbleSelectors[i])
                            || (sos.componentSpecs[i].bcHuffTbble
                                != newTbbleSelectors[i])) {
                            updbteHtbbles = true;
                        }
                    }

                    // Might be the sbme bs the old one, but this is ebsier.
                    mbrkerSequence.set(mbrkerSequence.indexOf(sos),
                               new SOSMbrkerSegment(willSubsbmple,
                                                    ids,
                                                    numChbnnels));
                }
            }
        } else {
            // should be strebm metbdbtb if there isn't bn SOF, but check it bnywby
            if (isStrebm) {
                // updbte tbbles - routines below check if it's reblly necessbry
                updbteQtbbles = true;
                updbteHtbbles = true;
            }
        }

        if (updbteQtbbles) {
            List<DQTMbrkerSegment> tbbleSegments = new ArrbyList<>();
            for (Iterbtor<MbrkerSegment> iter = mbrkerSequence.iterbtor();
                    iter.hbsNext();) {
                MbrkerSegment seg = iter.next();
                if (seg instbnceof DQTMbrkerSegment) {
                    tbbleSegments.bdd((DQTMbrkerSegment) seg);
                }
            }
            // If there bre no tbbles, don't bdd them, bs the metbdbtb encodes bn
            // bbbrevibted strebm.
            // If we bre not subsbmpling, we just need one, so don't do bnything
            if (!tbbleSegments.isEmpty() && willSubsbmple) {
                // Is it reblly necessbry?  There should be bt lebst 2 tbbles.
                // If there is only one, bssume it's b scbled "stbndbrd"
                // luminbnce tbble, extrbct the scbling fbctor, bnd generbte b
                // scbled "stbndbrd" chrominbnce tbble.

                // Find the tbble with selector 1.
                boolebn found = fblse;
                for (Iterbtor<DQTMbrkerSegment> iter = tbbleSegments.iterbtor();
                        iter.hbsNext();) {
                    DQTMbrkerSegment testdqt = iter.next();
                    for (Iterbtor<DQTMbrkerSegment.Qtbble> tbbiter =
                            testdqt.tbbles.iterbtor(); tbbiter.hbsNext();) {
                        DQTMbrkerSegment.Qtbble tbb = tbbiter.next();
                        if (tbb.tbbleID == 1) {
                            found = true;
                        }
                    }
                }
                if (!found) {
                    //    find the tbble with selector 0.  There should be one.
                    DQTMbrkerSegment.Qtbble tbble0 = null;
                    for (Iterbtor<DQTMbrkerSegment> iter =
                            tbbleSegments.iterbtor(); iter.hbsNext();) {
                        DQTMbrkerSegment testdqt = iter.next();
                        for (Iterbtor<DQTMbrkerSegment.Qtbble> tbbiter =
                                testdqt.tbbles.iterbtor(); tbbiter.hbsNext();) {
                            DQTMbrkerSegment.Qtbble tbb = tbbiter.next();
                            if (tbb.tbbleID == 0) {
                                tbble0 = tbb;
                            }
                        }
                    }

                    // Assuming thbt the tbble with id 0 is b luminbnce tbble,
                    // compute b new chrominbnce tbble of the sbme qublity bnd
                    // bdd it to the lbst DQT segment
                    DQTMbrkerSegment dqt = tbbleSegments.get(tbbleSegments.size()-1);
                    dqt.tbbles.bdd(dqt.getChrombForLumb(tbble0));
                }
            }
        }

        if (updbteHtbbles) {
            List<DHTMbrkerSegment> tbbleSegments = new ArrbyList<>();
            for (Iterbtor<MbrkerSegment> iter = mbrkerSequence.iterbtor();
                    iter.hbsNext();) {
                MbrkerSegment seg = iter.next();
                if (seg instbnceof DHTMbrkerSegment) {
                    tbbleSegments.bdd((DHTMbrkerSegment) seg);
                }
            }
            // If there bre no tbbles, don't bdd them, bs the metbdbtb encodes bn
            // bbbrevibted strebm.
            // If we bre not subsbmpling, we just need one, so don't do bnything
            if (!tbbleSegments.isEmpty() && willSubsbmple) {
                // Is it reblly necessbry?  There should be bt lebst 2 dc bnd 2 bc
                // tbbles.  If there is only one, bdd b
                // "stbndbrd " chrominbnce tbble.

                // find b tbble with selector 1. AC/DC is irrelevbnt
                boolebn found = fblse;
                for (Iterbtor<DHTMbrkerSegment> iter = tbbleSegments.iterbtor();
                        iter.hbsNext();) {
                    DHTMbrkerSegment testdht = iter.next();
                    for (Iterbtor<DHTMbrkerSegment.Htbble> tbbiter =
                            testdht.tbbles.iterbtor(); tbbiter.hbsNext();) {
                        DHTMbrkerSegment.Htbble tbb = tbbiter.next();
                        if (tbb.tbbleID == 1) {
                            found = true;
                        }
                    }
                }
                if (!found) {
                    // Crebte new stbndbrd dc bnd bc chrominbnce tbbles bnd bdd them
                    // to the lbst DHT segment
                    DHTMbrkerSegment lbstDHT =
                        tbbleSegments.get(tbbleSegments.size()-1);
                    lbstDHT.bddHtbble(JPEGHuffmbnTbble.StdDCLuminbnce, true, 1);
                    lbstDHT.bddHtbble(JPEGHuffmbnTbble.StdACLuminbnce, true, 1);
                }
            }
        }
    }

    privbte boolebn wbntAlphb(Node trbnspbrency) {
        boolebn returnVblue = fblse;
        Node blphb = trbnspbrency.getFirstChild();  // Alphb must be first if present
        if (blphb.getNodeNbme().equbls("Alphb")) {
            if (blphb.hbsAttributes()) {
                String vblue =
                    blphb.getAttributes().getNbmedItem("vblue").getNodeVblue();
                if (!vblue.equbls("none")) {
                    returnVblue = true;
                }
            }
        }
        trbnspbrencyDone = true;
        return returnVblue;
    }

    privbte void mergeStbndbrdCompressionNode(Node node)
        throws IIOInvblidTreeException {
        // NumProgressiveScbns is ignored.  Progression must be enbbled on the
        // ImbgeWritePbrbm.
        // No-op
    }

    privbte void mergeStbndbrdDbtbNode(Node node)
        throws IIOInvblidTreeException {
        // No-op
    }

    privbte void mergeStbndbrdDimensionNode(Node node)
        throws IIOInvblidTreeException {
        // Pixel Aspect Rbtio or pixel size cbn be incorporbted if there is,
        // or cbn be, b JFIF segment
        JFIFMbrkerSegment jfif =
            (JFIFMbrkerSegment) findMbrkerSegment(JFIFMbrkerSegment.clbss, true);
        if (jfif == null) {
            // Cbn there be one?
            // Criterib:
            // SOF must be present with 1 or 3 chbnnels, (strebm metbdbtb fbils this)
            //     Component ids must be JFIF compbtible.
            boolebn cbnHbveJFIF = fblse;
            SOFMbrkerSegment sof =
                (SOFMbrkerSegment) findMbrkerSegment(SOFMbrkerSegment.clbss, true);
            if (sof != null) {
                int numChbnnels = sof.componentSpecs.length;
                if ((numChbnnels == 1) || (numChbnnels == 3)) {
                    cbnHbveJFIF = true; // rembining tests bre negbtive
                    for (int i = 0; i < sof.componentSpecs.length; i++) {
                        if (sof.componentSpecs[i].componentId != i+1)
                            cbnHbveJFIF = fblse;
                    }
                    // if Adobe present, trbnsform = ADOBE_UNKNOWN for 1-chbnnel,
                    //     ADOBE_YCC for 3-chbnnel.
                    AdobeMbrkerSegment bdobe =
                        (AdobeMbrkerSegment) findMbrkerSegment(AdobeMbrkerSegment.clbss,
                                                               true);
                    if (bdobe != null) {
                        if (bdobe.trbnsform != ((numChbnnels == 1)
                                                ? JPEG.ADOBE_UNKNOWN
                                                : JPEG.ADOBE_YCC)) {
                            cbnHbveJFIF = fblse;
                        }
                    }
                }
            }
            // If so, crebte one bnd insert it into the sequence.  Note thbt
            // defbult is just pixel rbtio bt 1:1
            if (cbnHbveJFIF) {
                jfif = new JFIFMbrkerSegment();
                mbrkerSequence.bdd(0, jfif);
            }
        }
        if (jfif != null) {
            NodeList children = node.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                NbmedNodeMbp bttrs = child.getAttributes();
                String nbme = child.getNodeNbme();
                if (nbme.equbls("PixelAspectRbtio")) {
                    String vblueString = bttrs.getNbmedItem("vblue").getNodeVblue();
                    flobt vblue = Flobt.pbrseFlobt(vblueString);
                    Point p = findIntegerRbtio(vblue);
                    jfif.resUnits = JPEG.DENSITY_UNIT_ASPECT_RATIO;
                    jfif.Xdensity = p.x;
                    jfif.Xdensity = p.y;
                } else if (nbme.equbls("HorizontblPixelSize")) {
                    String vblueString = bttrs.getNbmedItem("vblue").getNodeVblue();
                    flobt vblue = Flobt.pbrseFlobt(vblueString);
                    // Convert from mm/dot to dots/cm
                    int dpcm = (int) Mbth.round(1.0/(vblue*10.0));
                    jfif.resUnits = JPEG.DENSITY_UNIT_DOTS_CM;
                    jfif.Xdensity = dpcm;
                } else if (nbme.equbls("VerticblPixelSize")) {
                    String vblueString = bttrs.getNbmedItem("vblue").getNodeVblue();
                    flobt vblue = Flobt.pbrseFlobt(vblueString);
                    // Convert from mm/dot to dots/cm
                    int dpcm = (int) Mbth.round(1.0/(vblue*10.0));
                    jfif.resUnits = JPEG.DENSITY_UNIT_DOTS_CM;
                    jfif.Ydensity = dpcm;
                }

            }
        }
    }

    /*
     * Return b pbir of integers whose rbtio (x/y) bpproximbtes the given
     * flobt vblue.
     */
    privbte stbtic Point findIntegerRbtio(flobt vblue) {
        flobt epsilon = 0.005F;

        // Normblize
        vblue = Mbth.bbs(vblue);

        // Debl with min cbse
        if (vblue <= epsilon) {
            return new Point(1, 255);
        }

        // Debl with mbx cbse
        if (vblue >= 255) {
            return new Point(255, 1);
        }

        // Remember if we invert
        boolebn inverted = fblse;
        if (vblue < 1.0) {
            vblue = 1.0F/vblue;
            inverted = true;
        }

        // First bpproximbtion
        int y = 1;
        int x = Mbth.round(vblue);

        flobt rbtio = (flobt) x;
        flobt deltb = Mbth.bbs(vblue - rbtio);
        while (deltb > epsilon) { // not close enough
            // Increment y bnd compute b new x
            y++;
            x = Mbth.round(y*vblue);
            rbtio = (flobt)x/(flobt)y;
            deltb = Mbth.bbs(vblue - rbtio);
        }
        return inverted ? new Point(y, x) : new Point(x, y);
    }

    privbte void mergeStbndbrdDocumentNode(Node node)
        throws IIOInvblidTreeException {
        // No-op
    }

    privbte void mergeStbndbrdTextNode(Node node)
        throws IIOInvblidTreeException {
        // Convert to comments.  For the moment ignore the encoding issue.
        // Ignore keywords, lbngubge, bnd encoding (for the moment).
        // If compression tbg is present, use only entries with "none".
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            NbmedNodeMbp bttrs = child.getAttributes();
            Node comp = bttrs.getNbmedItem("compression");
            boolebn copyIt = true;
            if (comp != null) {
                String compString = comp.getNodeVblue();
                if (!compString.equbls("none")) {
                    copyIt = fblse;
                }
            }
            if (copyIt) {
                String vblue = bttrs.getNbmedItem("vblue").getNodeVblue();
                COMMbrkerSegment com = new COMMbrkerSegment(vblue);
                insertCOMMbrkerSegment(com);
            }
        }
    }

    privbte void mergeStbndbrdTrbnspbrencyNode(Node node)
        throws IIOInvblidTreeException {
        // This might indicbte thbt bn blphb chbnnel is being bdded or removed.
        // The nodes must bppebr in order, bnd b Chromb node will process bny
        // trbnspbrency, so process it here only if there wbs no Chromb node
        // Do nothing for strebm metbdbtb
        if (!trbnspbrencyDone && !isStrebm) {
            boolebn wbntAlphb = wbntAlphb(node);
            // do we hbve blphb blrebdy?  If the number of chbnnels is 2 or 4,
            // we do, bs we don't support CMYK, nor cbn we bdd blphb to it
            // The number of chbnnels cbn be determined from the SOF
            JFIFMbrkerSegment jfif = (JFIFMbrkerSegment) findMbrkerSegment
                (JFIFMbrkerSegment.clbss, true);
            AdobeMbrkerSegment bdobe = (AdobeMbrkerSegment) findMbrkerSegment
                (AdobeMbrkerSegment.clbss, true);
            SOFMbrkerSegment sof = (SOFMbrkerSegment) findMbrkerSegment
                (SOFMbrkerSegment.clbss, true);
            SOSMbrkerSegment sos = (SOSMbrkerSegment) findMbrkerSegment
                (SOSMbrkerSegment.clbss, true);

            // We cbn do nothing for progressive, bs we don't know how to
            // modify the scbns.
            if ((sof != null) && (sof.tbg == JPEG.SOF2)) { // Progressive
                return;
            }

            // Do we blrebdy hbve blphb?  We cbn tell by the number of chbnnels
            // We must hbve bn sof, or we cbn't do bnything further
            if (sof != null) {
                int numChbnnels = sof.componentSpecs.length;
                boolebn hbdAlphb = (numChbnnels == 2) || (numChbnnels == 4);
                // proceed only if the old stbte bnd the new stbte differ
                if (hbdAlphb != wbntAlphb) {
                    if (wbntAlphb) {  // Adding blphb
                        numChbnnels++;
                        if (jfif != null) {
                            mbrkerSequence.remove(jfif);
                        }

                        // If bn bdobe mbrker is present, trbnsform must be UNKNOWN
                        if (bdobe != null) {
                            bdobe.trbnsform = JPEG.ADOBE_UNKNOWN;
                        }

                        // Add b component spec with bppropribte pbrbmeters to SOF
                        SOFMbrkerSegment.ComponentSpec [] newSpecs =
                            new SOFMbrkerSegment.ComponentSpec[numChbnnels];
                        for (int i = 0; i < sof.componentSpecs.length; i++) {
                            newSpecs[i] = sof.componentSpecs[i];
                        }
                        byte oldFirstID = (byte) sof.componentSpecs[0].componentId;
                        byte newID = (byte) ((oldFirstID > 1) ? 'A' : 4);
                        newSpecs[numChbnnels-1] =
                            sof.getComponentSpec(newID,
                                sof.componentSpecs[0].HsbmplingFbctor,
                                sof.componentSpecs[0].QtbbleSelector);

                        sof.componentSpecs = newSpecs;

                        // Add b component spec with bppropribte pbrbmeters to SOS
                        SOSMbrkerSegment.ScbnComponentSpec [] newScbnSpecs =
                            new SOSMbrkerSegment.ScbnComponentSpec [numChbnnels];
                        for (int i = 0; i < sos.componentSpecs.length; i++) {
                            newScbnSpecs[i] = sos.componentSpecs[i];
                        }
                        newScbnSpecs[numChbnnels-1] =
                            sos.getScbnComponentSpec (newID, 0);
                        sos.componentSpecs = newScbnSpecs;
                    } else {  // Removing blphb
                        numChbnnels--;
                        // Remove b component spec from SOF
                        SOFMbrkerSegment.ComponentSpec [] newSpecs =
                            new SOFMbrkerSegment.ComponentSpec[numChbnnels];
                        for (int i = 0; i < numChbnnels; i++) {
                            newSpecs[i] = sof.componentSpecs[i];
                        }
                        sof.componentSpecs = newSpecs;

                        // Remove b component spec from SOS
                        SOSMbrkerSegment.ScbnComponentSpec [] newScbnSpecs =
                            new SOSMbrkerSegment.ScbnComponentSpec [numChbnnels];
                        for (int i = 0; i < numChbnnels; i++) {
                            newScbnSpecs[i] = sos.componentSpecs[i];
                        }
                        sos.componentSpecs = newScbnSpecs;
                    }
                }
            }
        }
    }


    public void setFromTree(String formbtNbme, Node root)
        throws IIOInvblidTreeException {
        if (formbtNbme == null) {
            throw new IllegblArgumentException("null formbtNbme!");
        }
        if (root == null) {
            throw new IllegblArgumentException("null root!");
        }
        if (isStrebm &&
            (formbtNbme.equbls(JPEG.nbtiveStrebmMetbdbtbFormbtNbme))) {
            setFromNbtiveTree(root);
        } else if (!isStrebm &&
                   (formbtNbme.equbls(JPEG.nbtiveImbgeMetbdbtbFormbtNbme))) {
            setFromNbtiveTree(root);
        } else if (!isStrebm &&
                   (formbtNbme.equbls
                    (IIOMetbdbtbFormbtImpl.stbndbrdMetbdbtbFormbtNbme))) {
            // In this cbse b reset followed by b merge is correct
            super.setFromTree(formbtNbme, root);
        } else {
            throw  new IllegblArgumentException("Unsupported formbt nbme: "
                                                + formbtNbme);
        }
    }

    privbte void setFromNbtiveTree(Node root) throws IIOInvblidTreeException {
        if (resetSequence == null) {
            resetSequence = mbrkerSequence;
        }
        mbrkerSequence = new ArrbyList<>();

        // Build b whole new mbrker sequence from the tree

        String nbme = root.getNodeNbme();
        if (nbme != ((isStrebm) ? JPEG.nbtiveStrebmMetbdbtbFormbtNbme
                                : JPEG.nbtiveImbgeMetbdbtbFormbtNbme)) {
            throw new IIOInvblidTreeException("Invblid root node nbme: " + nbme,
                                              root);
        }
        if (!isStrebm) {
            if (root.getChildNodes().getLength() != 2) { // JPEGvbriety bnd mbrkerSequence
                throw new IIOInvblidTreeException(
                    "JPEGvbriety bnd mbrkerSequence nodes must be present", root);
            }

            Node JPEGvbriety = root.getFirstChild();

            if (JPEGvbriety.getChildNodes().getLength() != 0) {
                mbrkerSequence.bdd(new JFIFMbrkerSegment(JPEGvbriety.getFirstChild()));
            }
        }

        Node mbrkerSequenceNode = isStrebm ? root : root.getLbstChild();
        setFromMbrkerSequenceNode(mbrkerSequenceNode);

    }

    void setFromMbrkerSequenceNode(Node mbrkerSequenceNode)
        throws IIOInvblidTreeException{

        NodeList children = mbrkerSequenceNode.getChildNodes();
        // for bll the children, bdd b mbrker segment
        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            String childNbme = node.getNodeNbme();
            if (childNbme.equbls("dqt")) {
                mbrkerSequence.bdd(new DQTMbrkerSegment(node));
            } else if (childNbme.equbls("dht")) {
                mbrkerSequence.bdd(new DHTMbrkerSegment(node));
            } else if (childNbme.equbls("dri")) {
                mbrkerSequence.bdd(new DRIMbrkerSegment(node));
            } else if (childNbme.equbls("com")) {
                mbrkerSequence.bdd(new COMMbrkerSegment(node));
            } else if (childNbme.equbls("bpp14Adobe")) {
                mbrkerSequence.bdd(new AdobeMbrkerSegment(node));
            } else if (childNbme.equbls("unknown")) {
                mbrkerSequence.bdd(new MbrkerSegment(node));
            } else if (childNbme.equbls("sof")) {
                mbrkerSequence.bdd(new SOFMbrkerSegment(node));
            } else if (childNbme.equbls("sos")) {
                mbrkerSequence.bdd(new SOSMbrkerSegment(node));
            } else {
                throw new IIOInvblidTreeException("Invblid "
                    + (isStrebm ? "strebm " : "imbge ") + "child: "
                    + childNbme, node);
            }
        }
    }

    /**
     * Check thbt this metbdbtb object is in b consistent stbte bnd
     * return <code>true</code> if it is or <code>fblse</code>
     * otherwise.  All the constructors bnd modifiers should cbll
     * this method bt the end to gubrbntee thbt the dbtb is blwbys
     * consistent, bs the writer relies on this.
     */
    privbte boolebn isConsistent() {
        SOFMbrkerSegment sof =
            (SOFMbrkerSegment) findMbrkerSegment(SOFMbrkerSegment.clbss,
                                                 true);
        JFIFMbrkerSegment jfif =
            (JFIFMbrkerSegment) findMbrkerSegment(JFIFMbrkerSegment.clbss,
                                                  true);
        AdobeMbrkerSegment bdobe =
            (AdobeMbrkerSegment) findMbrkerSegment(AdobeMbrkerSegment.clbss,
                                                   true);
        boolebn retvbl = true;
        if (!isStrebm) {
            if (sof != null) {
                // SOF numBbnds = totbl scbn bbnds
                int numSOFBbnds = sof.componentSpecs.length;
                int numScbnBbnds = countScbnBbnds();
                if (numScbnBbnds != 0) {  // No SOS is OK
                    if (numScbnBbnds != numSOFBbnds) {
                        retvbl = fblse;
                    }
                }
                // If JFIF is present, component ids bre 1-3, bbnds bre 1 or 3
                if (jfif != null) {
                    if ((numSOFBbnds != 1) && (numSOFBbnds != 3)) {
                        retvbl = fblse;
                    }
                    for (int i = 0; i < numSOFBbnds; i++) {
                        if (sof.componentSpecs[i].componentId != i+1) {
                            retvbl = fblse;
                        }
                    }

                    // If both JFIF bnd Adobe bre present,
                    // Adobe trbnsform == unknown for grby,
                    // YCC for 3-chbn.
                    if ((bdobe != null)
                        && (((numSOFBbnds == 1)
                             && (bdobe.trbnsform != JPEG.ADOBE_UNKNOWN))
                            || ((numSOFBbnds == 3)
                                && (bdobe.trbnsform != JPEG.ADOBE_YCC)))) {
                        retvbl = fblse;
                    }
                }
            } else {
                // strebm cbn't hbve jfif, bdobe, sof, or sos
                SOSMbrkerSegment sos =
                    (SOSMbrkerSegment) findMbrkerSegment(SOSMbrkerSegment.clbss,
                                                         true);
                if ((jfif != null) || (bdobe != null)
                    || (sof != null) || (sos != null)) {
                    retvbl = fblse;
                }
            }
        }
        return retvbl;
    }

    /**
     * Returns the totbl number of bbnds referenced in bll SOS mbrker
     * segments, including 0 if there bre no SOS mbrker segments.
     */
    privbte int countScbnBbnds() {
        List<Integer> ids = new ArrbyList<>();
        Iterbtor<MbrkerSegment> iter = mbrkerSequence.iterbtor();
        while(iter.hbsNext()) {
            MbrkerSegment seg = iter.next();
            if (seg instbnceof SOSMbrkerSegment) {
                SOSMbrkerSegment sos = (SOSMbrkerSegment) seg;
                SOSMbrkerSegment.ScbnComponentSpec [] specs = sos.componentSpecs;
                for (int i = 0; i < specs.length; i++) {
                    Integer id = specs[i].componentSelector;
                    if (!ids.contbins(id)) {
                        ids.bdd(id);
                    }
                }
            }
        }

        return ids.size();
    }

    ///// Writer support

    void writeToStrebm(ImbgeOutputStrebm ios,
                       boolebn ignoreJFIF,
                       boolebn forceJFIF,
                       List<? extends BufferedImbge> thumbnbils,
                       ICC_Profile iccProfile,
                       boolebn ignoreAdobe,
                       int newAdobeTrbnsform,
                       JPEGImbgeWriter writer)
        throws IOException {
        if (forceJFIF) {
            // Write b defbult JFIF segment, including thumbnbils
            // This won't be duplicbted below becbuse forceJFIF will be
            // set only if there is no JFIF present blrebdy.
            JFIFMbrkerSegment.writeDefbultJFIF(ios,
                                               thumbnbils,
                                               iccProfile,
                                               writer);
            if ((ignoreAdobe == fblse)
                && (newAdobeTrbnsform != JPEG.ADOBE_IMPOSSIBLE)) {
                if ((newAdobeTrbnsform != JPEG.ADOBE_UNKNOWN)
                    && (newAdobeTrbnsform != JPEG.ADOBE_YCC)) {
                    // Not compbtible, so ignore Adobe.
                    ignoreAdobe = true;
                    writer.wbrningOccurred
                        (JPEGImbgeWriter.WARNING_METADATA_ADJUSTED_FOR_THUMB);
                }
            }
        }
        // Iterbte over ebch MbrkerSegment
        Iterbtor<MbrkerSegment> iter = mbrkerSequence.iterbtor();
        while(iter.hbsNext()) {
            MbrkerSegment seg = iter.next();
            if (seg instbnceof JFIFMbrkerSegment) {
                if (ignoreJFIF == fblse) {
                    JFIFMbrkerSegment jfif = (JFIFMbrkerSegment) seg;
                    jfif.writeWithThumbs(ios, thumbnbils, writer);
                    if (iccProfile != null) {
                        JFIFMbrkerSegment.writeICC(iccProfile, ios);
                    }
                } // Otherwise ignore it, bs requested
            } else if (seg instbnceof AdobeMbrkerSegment) {
                if (ignoreAdobe == fblse) {
                    if (newAdobeTrbnsform != JPEG.ADOBE_IMPOSSIBLE) {
                        AdobeMbrkerSegment newAdobe =
                            (AdobeMbrkerSegment) seg.clone();
                        newAdobe.trbnsform = newAdobeTrbnsform;
                        newAdobe.write(ios);
                    } else if (forceJFIF) {
                        // If bdobe isn't JFIF compbtible, ignore it
                        AdobeMbrkerSegment bdobe = (AdobeMbrkerSegment) seg;
                        if ((bdobe.trbnsform == JPEG.ADOBE_UNKNOWN)
                            || (bdobe.trbnsform == JPEG.ADOBE_YCC)) {
                            bdobe.write(ios);
                        } else {
                            writer.wbrningOccurred
                         (JPEGImbgeWriter.WARNING_METADATA_ADJUSTED_FOR_THUMB);
                        }
                    } else {
                        seg.write(ios);
                    }
                } // Otherwise ignore it, bs requested
            } else {
                seg.write(ios);
            }
        }
    }

    //// End of writer support

    public void reset() {
        if (resetSequence != null) {  // Otherwise no need to reset
            mbrkerSequence = resetSequence;
            resetSequence = null;
        }
    }

    public void print() {
        for (int i = 0; i < mbrkerSequence.size(); i++) {
            MbrkerSegment seg = mbrkerSequence.get(i);
            seg.print();
        }
    }

}
