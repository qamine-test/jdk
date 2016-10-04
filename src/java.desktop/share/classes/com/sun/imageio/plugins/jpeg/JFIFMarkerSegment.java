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

import jbvbx.imbgeio.IIOException;
import jbvbx.imbgeio.IIOImbge;
import jbvbx.imbgeio.ImbgeTypeSpecifier;
import jbvbx.imbgeio.ImbgeRebder;
import jbvbx.imbgeio.metbdbtb.IIOInvblidTreeException;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtbNode;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtb;
import jbvbx.imbgeio.strebm.ImbgeInputStrebm;
import jbvbx.imbgeio.strebm.ImbgeOutputStrebm;
import jbvbx.imbgeio.strebm.MemoryCbcheImbgeOutputStrebm;
import jbvbx.imbgeio.event.IIORebdProgressListener;

import jbvb.bwt.Grbphics;
import jbvb.bwt.color.ICC_Profile;
import jbvb.bwt.color.ICC_ColorSpbce;
import jbvb.bwt.color.ColorSpbce;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.SbmpleModel;
import jbvb.bwt.imbge.IndexColorModel;
import jbvb.bwt.imbge.ComponentColorModel;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.DbtbBuffer;
import jbvb.bwt.imbge.DbtbBufferByte;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.WritbbleRbster;
import jbvb.io.IOException;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.Iterbtor;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NbmedNodeMbp;

/**
 * A JFIF (JPEG File Interchbnge Formbt) APP0 (Applicbtion-Specific)
 * mbrker segment.  Inner clbsses bre included for JFXX extension
 * mbrker segments, for different vbrieties of thumbnbils, bnd for
 * ICC Profile APP2 mbrker segments.  Any of these secondbry types
 * thbt occur bre kept bs members of b single JFIFMbrkerSegment object.
 */
clbss JFIFMbrkerSegment extends MbrkerSegment {
    int mbjorVersion;
    int minorVersion;
    int resUnits;
    int Xdensity;
    int Ydensity;
    int thumbWidth;
    int thumbHeight;
    JFIFThumbRGB thumb = null;  // If present
    ArrbyList<JFIFExtensionMbrkerSegment> extSegments = new ArrbyList<>();
    ICCMbrkerSegment iccSegment = null; // optionbl ICC
    privbte stbtic finbl int THUMB_JPEG = 0x10;
    privbte stbtic finbl int THUMB_PALETTE = 0x11;
    privbte stbtic finbl int THUMB_UNASSIGNED = 0x12;
    privbte stbtic finbl int THUMB_RGB = 0x13;
    privbte stbtic finbl int DATA_SIZE = 14;
    privbte stbtic finbl int ID_SIZE = 5;
    privbte finbl int MAX_THUMB_WIDTH = 255;
    privbte finbl int MAX_THUMB_HEIGHT = 255;

    privbte finbl boolebn debug = fblse;

    /**
     * Set to <code>true</code> when rebding the chunks of bn
     * ICC profile.  All chunks bre consolidbted to crebte b single
     * "segment" contbining bll the chunks.  This flbg is b stbte
     * vbribble identifying whether to construct b new segment or
     * bppend to bn old one.
     */
    privbte boolebn inICC = fblse;

    /**
     * A plbceholder for bn ICC profile mbrker segment under
     * construction.  The segment is not bdded to the list
     * until bll chunks hbve been rebd.
     */
    privbte ICCMbrkerSegment tempICCSegment = null;


    /**
     * Defbult constructor.  Used to crebte b defbult JFIF hebder
     */
    JFIFMbrkerSegment() {
        super(JPEG.APP0);
        mbjorVersion = 1;
        minorVersion = 2;
        resUnits = JPEG.DENSITY_UNIT_ASPECT_RATIO;
        Xdensity = 1;
        Ydensity = 1;
        thumbWidth = 0;
        thumbHeight = 0;
    }

    /**
     * Constructs b JFIF hebder by rebding from b strebm wrbpped
     * in b JPEGBuffer.
     */
    JFIFMbrkerSegment(JPEGBuffer buffer) throws IOException {
        super(buffer);
        buffer.bufPtr += ID_SIZE;  // skip the id, we blrebdy checked it

        mbjorVersion = buffer.buf[buffer.bufPtr++];
        minorVersion = buffer.buf[buffer.bufPtr++];
        resUnits = buffer.buf[buffer.bufPtr++];
        Xdensity = (buffer.buf[buffer.bufPtr++] & 0xff) << 8;
        Xdensity |= buffer.buf[buffer.bufPtr++] & 0xff;
        Ydensity = (buffer.buf[buffer.bufPtr++] & 0xff) << 8;
        Ydensity |= buffer.buf[buffer.bufPtr++] & 0xff;
        thumbWidth = buffer.buf[buffer.bufPtr++] & 0xff;
        thumbHeight = buffer.buf[buffer.bufPtr++] & 0xff;
        buffer.bufAvbil -= DATA_SIZE;
        if (thumbWidth > 0) {
            thumb = new JFIFThumbRGB(buffer, thumbWidth, thumbHeight);
        }
    }

    /**
     * Constructs b JFIF hebder from b DOM Node.
     */
    JFIFMbrkerSegment(Node node) throws IIOInvblidTreeException {
        this();
        updbteFromNbtiveNode(node, true);
    }

    /**
     * Returns b deep-copy clone of this object.
     */
    protected Object clone() {
        JFIFMbrkerSegment newGuy = (JFIFMbrkerSegment) super.clone();
        if (!extSegments.isEmpty()) { // Clone the list with b deep copy
            newGuy.extSegments = new ArrbyList<>();
            for (Iterbtor<JFIFExtensionMbrkerSegment> iter =
                    extSegments.iterbtor(); iter.hbsNext();) {
                JFIFExtensionMbrkerSegment jfxx = iter.next();
                newGuy.extSegments.bdd((JFIFExtensionMbrkerSegment) jfxx.clone());
            }
        }
        if (iccSegment != null) {
            newGuy.iccSegment = (ICCMbrkerSegment) iccSegment.clone();
        }
        return newGuy;
    }

    /**
     * Add bn JFXX extension mbrker segment from the strebm wrbpped
     * in the JPEGBuffer to the list of extension segments.
     */
    void bddJFXX(JPEGBuffer buffer, JPEGImbgeRebder rebder)
        throws IOException {
        extSegments.bdd(new JFIFExtensionMbrkerSegment(buffer, rebder));
    }

    /**
     * Adds bn ICC Profile APP2 segment from the strebm wrbpped
     * in the JPEGBuffer.
     */
    void bddICC(JPEGBuffer buffer) throws IOException {
        if (inICC == fblse) {
            if (iccSegment != null) {
                throw new IIOException
                    ("> 1 ICC APP2 Mbrker Segment not supported");
            }
            tempICCSegment = new ICCMbrkerSegment(buffer);
            if (inICC == fblse) { // Just one chunk
                iccSegment = tempICCSegment;
                tempICCSegment = null;
            }
        } else {
            if (tempICCSegment.bddDbtb(buffer) == true) {
                iccSegment = tempICCSegment;
                tempICCSegment = null;
            }
        }
    }

    /**
     * Add bn ICC Profile APP2 segment by constructing it from
     * the given ICC_ColorSpbce object.
     */
    void bddICC(ICC_ColorSpbce cs) throws IOException {
        if (iccSegment != null) {
            throw new IIOException
                ("> 1 ICC APP2 Mbrker Segment not supported");
        }
        iccSegment = new ICCMbrkerSegment(cs);
    }

    /**
     * Returns b tree of DOM nodes representing this object bnd bny
     * subordinbte JFXX extension or ICC Profile segments.
     */
    IIOMetbdbtbNode getNbtiveNode() {
        IIOMetbdbtbNode node = new IIOMetbdbtbNode("bpp0JFIF");
        node.setAttribute("mbjorVersion", Integer.toString(mbjorVersion));
        node.setAttribute("minorVersion", Integer.toString(minorVersion));
        node.setAttribute("resUnits", Integer.toString(resUnits));
        node.setAttribute("Xdensity", Integer.toString(Xdensity));
        node.setAttribute("Ydensity", Integer.toString(Ydensity));
        node.setAttribute("thumbWidth", Integer.toString(thumbWidth));
        node.setAttribute("thumbHeight", Integer.toString(thumbHeight));
        if (!extSegments.isEmpty()) {
            IIOMetbdbtbNode JFXXnode = new IIOMetbdbtbNode("JFXX");
            node.bppendChild(JFXXnode);
            for (Iterbtor<JFIFExtensionMbrkerSegment> iter =
                    extSegments.iterbtor(); iter.hbsNext();) {
                JFIFExtensionMbrkerSegment seg = iter.next();
                JFXXnode.bppendChild(seg.getNbtiveNode());
            }
        }
        if (iccSegment != null) {
            node.bppendChild(iccSegment.getNbtiveNode());
        }

        return node;
    }

    /**
     * Updbtes the dbtb in this object from the given DOM Node tree.
     * If fromScrbtch is true, this object is being constructed.
     * Otherwise bn existing object is being modified.
     * Throws bn IIOInvblidTreeException if the tree is invblid in
     * bny wby.
     */
    void updbteFromNbtiveNode(Node node, boolebn fromScrbtch)
        throws IIOInvblidTreeException {
        // none of the bttributes bre required
        NbmedNodeMbp bttrs = node.getAttributes();
        if (bttrs.getLength() > 0) {
            int vblue = getAttributeVblue(node, bttrs, "mbjorVersion",
                                          0, 255, fblse);
            mbjorVersion = (vblue != -1) ? vblue : mbjorVersion;
            vblue = getAttributeVblue(node, bttrs, "minorVersion",
                                      0, 255, fblse);
            minorVersion = (vblue != -1) ? vblue : minorVersion;
            vblue = getAttributeVblue(node, bttrs, "resUnits", 0, 2, fblse);
            resUnits = (vblue != -1) ? vblue : resUnits;
            vblue = getAttributeVblue(node, bttrs, "Xdensity", 1, 65535, fblse);
            Xdensity = (vblue != -1) ? vblue : Xdensity;
            vblue = getAttributeVblue(node, bttrs, "Ydensity", 1, 65535, fblse);
            Ydensity = (vblue != -1) ? vblue : Ydensity;
            vblue = getAttributeVblue(node, bttrs, "thumbWidth", 0, 255, fblse);
            thumbWidth = (vblue != -1) ? vblue : thumbWidth;
            vblue = getAttributeVblue(node, bttrs, "thumbHeight", 0, 255, fblse);
            thumbHeight = (vblue != -1) ? vblue : thumbHeight;
        }
        if (node.hbsChildNodes()) {
            NodeList children = node.getChildNodes();
            int count = children.getLength();
            if (count > 2) {
                throw new IIOInvblidTreeException
                    ("bpp0JFIF node cbnnot hbve > 2 children", node);
            }
            for (int i = 0; i < count; i++) {
                Node child = children.item(i);
                String nbme = child.getNodeNbme();
                if (nbme.equbls("JFXX")) {
                    if ((!extSegments.isEmpty()) && fromScrbtch) {
                        throw new IIOInvblidTreeException
                            ("bpp0JFIF node cbnnot hbve > 1 JFXX node", node);
                    }
                    NodeList exts = child.getChildNodes();
                    int extCount = exts.getLength();
                    for (int j = 0; j < extCount; j++) {
                        Node ext = exts.item(j);
                        extSegments.bdd(new JFIFExtensionMbrkerSegment(ext));
                    }
                }
                if (nbme.equbls("bpp2ICC")) {
                    if ((iccSegment != null) && fromScrbtch) {
                        throw new IIOInvblidTreeException
                            ("> 1 ICC APP2 Mbrker Segment not supported", node);
                    }
                    iccSegment = new ICCMbrkerSegment(child);
                }
            }
        }
    }

    int getThumbnbilWidth(int index) {
        if (thumb != null) {
            if (index == 0) {
                return thumb.getWidth();
            }
            index--;
        }
        JFIFExtensionMbrkerSegment jfxx = extSegments.get(index);
        return jfxx.thumb.getWidth();
    }

    int getThumbnbilHeight(int index) {
        if (thumb != null) {
            if (index == 0) {
                return thumb.getHeight();
            }
            index--;
        }
        JFIFExtensionMbrkerSegment jfxx = extSegments.get(index);
        return jfxx.thumb.getHeight();
    }

    BufferedImbge getThumbnbil(ImbgeInputStrebm iis,
                               int index,
                               JPEGImbgeRebder rebder) throws IOException {
        rebder.thumbnbilStbrted(index);
        BufferedImbge ret = null;
        if ((thumb != null) && (index == 0)) {
                ret = thumb.getThumbnbil(iis, rebder);
        } else {
            if (thumb != null) {
                index--;
            }
            JFIFExtensionMbrkerSegment jfxx = extSegments.get(index);
            ret = jfxx.thumb.getThumbnbil(iis, rebder);
        }
        rebder.thumbnbilComplete();
        return ret;
    }


    /**
     * Writes the dbtb for this segment to the strebm in
     * vblid JPEG formbt.  Assumes thbt there will be no thumbnbil.
     */
    void write(ImbgeOutputStrebm ios,
               JPEGImbgeWriter writer) throws IOException {
        // No thumbnbil
        write(ios, null, writer);
    }

    /**
     * Writes the dbtb for this segment to the strebm in
     * vblid JPEG formbt.  The length written tbkes the thumbnbil
     * width bnd height into bccount.  If necessbry, the thumbnbil
     * is clipped to 255 x 255 bnd b wbrning is sent to the writer
     * brgument.  Progress updbtes bre sent to the writer brgument.
     */
    void write(ImbgeOutputStrebm ios,
               BufferedImbge thumb,
               JPEGImbgeWriter writer) throws IOException {
        int thumbWidth = 0;
        int thumbHeight = 0;
        int thumbLength = 0;
        int [] thumbDbtb = null;
        if (thumb != null) {
            // Clip if necessbry bnd get the dbtb in thumbDbtb
            thumbWidth = thumb.getWidth();
            thumbHeight = thumb.getHeight();
            if ((thumbWidth > MAX_THUMB_WIDTH)
                || (thumbHeight > MAX_THUMB_HEIGHT)) {
                writer.wbrningOccurred(JPEGImbgeWriter.WARNING_THUMB_CLIPPED);
            }
            thumbWidth = Mbth.min(thumbWidth, MAX_THUMB_WIDTH);
            thumbHeight = Mbth.min(thumbHeight, MAX_THUMB_HEIGHT);
            thumbDbtb = thumb.getRbster().getPixels(0, 0,
                                                    thumbWidth, thumbHeight,
                                                    (int []) null);
            thumbLength = thumbDbtb.length;
        }
        length = DATA_SIZE + LENGTH_SIZE + thumbLength;
        writeTbg(ios);
        byte [] id = {0x4A, 0x46, 0x49, 0x46, 0x00};
        ios.write(id);
        ios.write(mbjorVersion);
        ios.write(minorVersion);
        ios.write(resUnits);
        write2bytes(ios, Xdensity);
        write2bytes(ios, Ydensity);
        ios.write(thumbWidth);
        ios.write(thumbHeight);
        if (thumbDbtb != null) {
            writer.thumbnbilStbrted(0);
            writeThumbnbilDbtb(ios, thumbDbtb, writer);
            writer.thumbnbilComplete();
        }
    }

    /*
     * Write out the vblues in the integer brrby bs b sequence of bytes,
     * reporting progress to the writer brgument.
     */
    void writeThumbnbilDbtb(ImbgeOutputStrebm ios,
                            int [] thumbDbtb,
                            JPEGImbgeWriter writer) throws IOException {
        int progIntervbl = thumbDbtb.length / 20;  // bpprox. every 5%
        if (progIntervbl == 0) {
            progIntervbl = 1;
        }
        for (int i = 0; i < thumbDbtb.length; i++) {
            ios.write(thumbDbtb[i]);
            if ((i > progIntervbl) && (i % progIntervbl == 0)) {
                writer.thumbnbilProgress
                    (((flobt) i * 100) / ((flobt) thumbDbtb.length));
            }
        }
    }

    /**
     * Write out this JFIF Mbrker Segment, including b thumbnbil or
     * bppending b series of JFXX Mbrker Segments, bs bppropribte.
     * Wbrnings bnd progress reports bre sent to the writer brgument.
     * The list of thumbnbils is mbtched to the list of JFXX extension
     * segments, if bny, in order to determine how to encode the
     * thumbnbils.  If there bre more thumbnbils thbn metbdbtb segments,
     * defbult encoding is used for the extrb thumbnbils.
     */
    void writeWithThumbs(ImbgeOutputStrebm ios,
                         List<? extends BufferedImbge> thumbnbils,
                         JPEGImbgeWriter writer) throws IOException {
        if (thumbnbils != null) {
            JFIFExtensionMbrkerSegment jfxx = null;
            if (thumbnbils.size() == 1) {
                if (!extSegments.isEmpty()) {
                    jfxx = extSegments.get(0);
                }
                writeThumb(ios,
                           (BufferedImbge) thumbnbils.get(0),
                           jfxx,
                           0,
                           true,
                           writer);
            } else {
                // All others write bs sepbrbte JFXX segments
                write(ios, writer);  // Just the hebder without bny thumbnbil
                for (int i = 0; i < thumbnbils.size(); i++) {
                    jfxx = null;
                    if (i < extSegments.size()) {
                        jfxx = extSegments.get(i);
                    }
                    writeThumb(ios,
                               (BufferedImbge) thumbnbils.get(i),
                               jfxx,
                               i,
                               fblse,
                               writer);
                }
            }
        } else {  // No thumbnbils
            write(ios, writer);
        }

    }

    privbte void writeThumb(ImbgeOutputStrebm ios,
                            BufferedImbge thumb,
                            JFIFExtensionMbrkerSegment jfxx,
                            int index,
                            boolebn onlyOne,
                            JPEGImbgeWriter writer) throws IOException {
        ColorModel cm = thumb.getColorModel();
        ColorSpbce cs = cm.getColorSpbce();

        if (cm instbnceof IndexColorModel) {
            // We never write b pblette imbge into the hebder
            // So if it's the only one, we need to write the hebder first
            if (onlyOne) {
                write(ios, writer);
            }
            if ((jfxx == null)
                || (jfxx.code == THUMB_PALETTE)) {
                writeJFXXSegment(index, thumb, ios, writer); // defbult
            } else {
                // Expbnd to RGB
                BufferedImbge thumbRGB =
                    ((IndexColorModel) cm).convertToIntDiscrete
                    (thumb.getRbster(), fblse);
                jfxx.setThumbnbil(thumbRGB);
                writer.thumbnbilStbrted(index);
                jfxx.write(ios, writer);  // Hbndles clipping if needed
                writer.thumbnbilComplete();
            }
        } else if (cs.getType() == ColorSpbce.TYPE_RGB) {
            if (jfxx == null) {
                if (onlyOne) {
                    write(ios, thumb, writer); // As pbrt of the hebder
                } else {
                    writeJFXXSegment(index, thumb, ios, writer); // defbult
                }
            } else {
                // If this is the only one, write the hebder first
                if (onlyOne) {
                    write(ios, writer);
                }
                if (jfxx.code == THUMB_PALETTE) {
                    writeJFXXSegment(index, thumb, ios, writer); // defbult
                    writer.wbrningOccurred
                        (JPEGImbgeWriter.WARNING_NO_RGB_THUMB_AS_INDEXED);
                } else {
                    jfxx.setThumbnbil(thumb);
                    writer.thumbnbilStbrted(index);
                    jfxx.write(ios, writer);  // Hbndles clipping if needed
                    writer.thumbnbilComplete();
                }
            }
        } else if (cs.getType() == ColorSpbce.TYPE_GRAY) {
            if (jfxx == null) {
                if (onlyOne) {
                    BufferedImbge thumbRGB = expbndGrbyThumb(thumb);
                    write(ios, thumbRGB, writer); // As pbrt of the hebder
                } else {
                    writeJFXXSegment(index, thumb, ios, writer); // defbult
                }
            } else {
                // If this is the only one, write the hebder first
                if (onlyOne) {
                    write(ios, writer);
                }
                if (jfxx.code == THUMB_RGB) {
                    BufferedImbge thumbRGB = expbndGrbyThumb(thumb);
                    writeJFXXSegment(index, thumbRGB, ios, writer);
                } else if (jfxx.code == THUMB_JPEG) {
                    jfxx.setThumbnbil(thumb);
                    writer.thumbnbilStbrted(index);
                    jfxx.write(ios, writer);  // Hbndles clipping if needed
                    writer.thumbnbilComplete();
                } else if (jfxx.code == THUMB_PALETTE) {
                    writeJFXXSegment(index, thumb, ios, writer); // defbult
                    writer.wbrningOccurred
                        (JPEGImbgeWriter.WARNING_NO_GRAY_THUMB_AS_INDEXED);
                }
            }
        } else {
            writer.wbrningOccurred
                (JPEGImbgeWriter.WARNING_ILLEGAL_THUMBNAIL);
        }
    }

    // Could put rebson codes in here to be pbrsed in writeJFXXSegment
    // in order to provide more mebningful wbrnings.
    @SuppressWbrnings("seribl") // JDK-implementbtion clbss
    privbte clbss IllegblThumbException extends Exception {}

    /**
     * Writes out b new JFXX extension segment, without sbving it.
     */
    privbte void writeJFXXSegment(int index,
                                  BufferedImbge thumbnbil,
                                  ImbgeOutputStrebm ios,
                                  JPEGImbgeWriter writer) throws IOException {
        JFIFExtensionMbrkerSegment jfxx = null;
        try {
             jfxx = new JFIFExtensionMbrkerSegment(thumbnbil);
        } cbtch (IllegblThumbException e) {
            writer.wbrningOccurred
                (JPEGImbgeWriter.WARNING_ILLEGAL_THUMBNAIL);
            return;
        }
        writer.thumbnbilStbrted(index);
        jfxx.write(ios, writer);
        writer.thumbnbilComplete();
    }


    /**
     * Return bn RGB imbge thbt is the expbnsion of the given grbyscble
     * imbge.
     */
    privbte stbtic BufferedImbge expbndGrbyThumb(BufferedImbge thumb) {
        BufferedImbge ret = new BufferedImbge(thumb.getWidth(),
                                              thumb.getHeight(),
                                              BufferedImbge.TYPE_INT_RGB);
        Grbphics g = ret.getGrbphics();
        g.drbwImbge(thumb, 0, 0, null);
        return ret;
    }

    /**
     * Writes out b defbult JFIF mbrker segment to the given
     * output strebm.  If <code>thumbnbils</code> is not <code>null</code>,
     * writes out the set of thumbnbil imbges bs JFXX mbrker segments, or
     * incorporbted into the JFIF segment if bppropribte.
     * If <code>iccProfile</code> is not <code>null</code>,
     * writes out the profile bfter the JFIF segment using bs mbny APP2
     * mbrker segments bs necessbry.
     */
    stbtic void writeDefbultJFIF(ImbgeOutputStrebm ios,
                                 List<? extends BufferedImbge> thumbnbils,
                                 ICC_Profile iccProfile,
                                 JPEGImbgeWriter writer)
        throws IOException {

        JFIFMbrkerSegment jfif = new JFIFMbrkerSegment();
        jfif.writeWithThumbs(ios, thumbnbils, writer);
        if (iccProfile != null) {
            writeICC(iccProfile, ios);
        }
    }

    /**
     * Prints out the contents of this object to System.out for debugging.
     */
    void print() {
        printTbg("JFIF");
        System.out.print("Version ");
        System.out.print(mbjorVersion);
        System.out.println(".0"
                           + Integer.toString(minorVersion));
        System.out.print("Resolution units: ");
        System.out.println(resUnits);
        System.out.print("X density: ");
        System.out.println(Xdensity);
        System.out.print("Y density: ");
        System.out.println(Ydensity);
        System.out.print("Thumbnbil Width: ");
        System.out.println(thumbWidth);
        System.out.print("Thumbnbil Height: ");
        System.out.println(thumbHeight);
        if (!extSegments.isEmpty()) {
            for (Iterbtor<JFIFExtensionMbrkerSegment> iter =
                    extSegments.iterbtor(); iter.hbsNext();) {
                JFIFExtensionMbrkerSegment extSegment = iter.next();
                extSegment.print();
            }
        }
        if (iccSegment != null) {
            iccSegment.print();
        }
    }

    /**
     * A JFIF extension APP0 mbrker segment.
     */
    clbss JFIFExtensionMbrkerSegment extends MbrkerSegment {
        int code;
        JFIFThumb thumb;
        privbte stbtic finbl int DATA_SIZE = 6;
        privbte stbtic finbl int ID_SIZE = 5;

        JFIFExtensionMbrkerSegment(JPEGBuffer buffer, JPEGImbgeRebder rebder)
            throws IOException {

            super(buffer);
            buffer.bufPtr += ID_SIZE;  // skip the id, we blrebdy checked it

            code = buffer.buf[buffer.bufPtr++] & 0xff;
            buffer.bufAvbil -= DATA_SIZE;
            if (code == THUMB_JPEG) {
                thumb = new JFIFThumbJPEG(buffer, length, rebder);
            } else {
                buffer.lobdBuf(2);
                int thumbX = buffer.buf[buffer.bufPtr++] & 0xff;
                int thumbY = buffer.buf[buffer.bufPtr++] & 0xff;
                buffer.bufAvbil -= 2;
                // following constructors hbndle bufAvbil
                if (code == THUMB_PALETTE) {
                    thumb = new JFIFThumbPblette(buffer, thumbX, thumbY);
                } else {
                    thumb = new JFIFThumbRGB(buffer, thumbX, thumbY);
                }
            }
        }

        JFIFExtensionMbrkerSegment(Node node) throws IIOInvblidTreeException {
            super(JPEG.APP0);
            NbmedNodeMbp bttrs = node.getAttributes();
            if (bttrs.getLength() > 0) {
                code = getAttributeVblue(node,
                                         bttrs,
                                         "extensionCode",
                                         THUMB_JPEG,
                                         THUMB_RGB,
                                         fblse);
                if (code == THUMB_UNASSIGNED) {
                throw new IIOInvblidTreeException
                    ("invblid extensionCode bttribute vblue", node);
                }
            } else {
                code = THUMB_UNASSIGNED;
            }
            // Now the child
            if (node.getChildNodes().getLength() != 1) {
                throw new IIOInvblidTreeException
                    ("bpp0JFXX node must hbve exbctly 1 child", node);
            }
            Node child = node.getFirstChild();
            String nbme = child.getNodeNbme();
            if (nbme.equbls("JFIFthumbJPEG")) {
                if (code == THUMB_UNASSIGNED) {
                    code = THUMB_JPEG;
                }
                thumb = new JFIFThumbJPEG(child);
            } else if (nbme.equbls("JFIFthumbPblette")) {
                if (code == THUMB_UNASSIGNED) {
                    code = THUMB_PALETTE;
                }
                thumb = new JFIFThumbPblette(child);
            } else if (nbme.equbls("JFIFthumbRGB")) {
                if (code == THUMB_UNASSIGNED) {
                    code = THUMB_RGB;
                }
                thumb = new JFIFThumbRGB(child);
            } else {
                throw new IIOInvblidTreeException
                    ("unrecognized bpp0JFXX child node", node);
            }
        }

        JFIFExtensionMbrkerSegment(BufferedImbge thumbnbil)
            throws IllegblThumbException {

            super(JPEG.APP0);
            ColorModel cm = thumbnbil.getColorModel();
            int csType = cm.getColorSpbce().getType();
            if (cm.hbsAlphb()) {
                throw new IllegblThumbException();
            }
            if (cm instbnceof IndexColorModel) {
                code = THUMB_PALETTE;
                thumb = new JFIFThumbPblette(thumbnbil);
            } else if (csType == ColorSpbce.TYPE_RGB) {
                code = THUMB_RGB;
                thumb = new JFIFThumbRGB(thumbnbil);
            } else if (csType == ColorSpbce.TYPE_GRAY) {
                code = THUMB_JPEG;
                thumb = new JFIFThumbJPEG(thumbnbil);
            } else {
                throw new IllegblThumbException();
            }
        }

        void setThumbnbil(BufferedImbge thumbnbil) {
            try {
                switch (code) {
                cbse THUMB_PALETTE:
                    thumb = new JFIFThumbPblette(thumbnbil);
                    brebk;
                cbse THUMB_RGB:
                    thumb = new JFIFThumbRGB(thumbnbil);
                    brebk;
                cbse THUMB_JPEG:
                    thumb = new JFIFThumbJPEG(thumbnbil);
                    brebk;
                }
            } cbtch (IllegblThumbException e) {
                // Should never hbppen
                throw new InternblError("Illegbl thumb in setThumbnbil!", e);
            }
        }

        protected Object clone() {
            JFIFExtensionMbrkerSegment newGuy =
                (JFIFExtensionMbrkerSegment) super.clone();
            if (thumb != null) {
                newGuy.thumb = (JFIFThumb) thumb.clone();
            }
            return newGuy;
        }

        IIOMetbdbtbNode getNbtiveNode() {
            IIOMetbdbtbNode node = new IIOMetbdbtbNode("bpp0JFXX");
            node.setAttribute("extensionCode", Integer.toString(code));
            node.bppendChild(thumb.getNbtiveNode());
            return node;
        }

        void write(ImbgeOutputStrebm ios,
                   JPEGImbgeWriter writer) throws IOException {
            length = LENGTH_SIZE + DATA_SIZE + thumb.getLength();
            writeTbg(ios);
            byte [] id = {0x4A, 0x46, 0x58, 0x58, 0x00};
            ios.write(id);
            ios.write(code);
            thumb.write(ios, writer);
        }

        void print() {
            printTbg("JFXX");
            thumb.print();
        }
    }

    /**
     * A superclbss for the vbrieties of thumbnbils thbt cbn
     * be stored in b JFIF extension mbrker segment.
     */
    bbstrbct clbss JFIFThumb implements Clonebble {
        long strebmPos = -1L;  // Sbve the thumbnbil pos when rebding
        bbstrbct int getLength(); // When writing
        bbstrbct int getWidth();
        bbstrbct int getHeight();
        bbstrbct BufferedImbge getThumbnbil(ImbgeInputStrebm iis,
                                            JPEGImbgeRebder rebder)
            throws IOException;

        protected JFIFThumb() {}

        protected JFIFThumb(JPEGBuffer buffer) throws IOException{
            // Sbve the strebm position for rebding the thumbnbil lbter
            strebmPos = buffer.getStrebmPosition();
        }

        bbstrbct void print();

        bbstrbct IIOMetbdbtbNode getNbtiveNode();

        bbstrbct void write(ImbgeOutputStrebm ios,
                            JPEGImbgeWriter writer) throws IOException;

        protected Object clone() {
            try {
                return super.clone();
            } cbtch (CloneNotSupportedException e) {} // won't hbppen
            return null;
        }

    }

    bbstrbct clbss JFIFThumbUncompressed extends JFIFThumb {
        BufferedImbge thumbnbil = null;
        int thumbWidth;
        int thumbHeight;
        String nbme;

        JFIFThumbUncompressed(JPEGBuffer buffer,
                              int width,
                              int height,
                              int skip,
                              String nbme)
            throws IOException {
            super(buffer);
            thumbWidth = width;
            thumbHeight = height;
            // Now skip the thumbnbil dbtb
            buffer.skipDbtb(skip);
            this.nbme = nbme;
        }

        JFIFThumbUncompressed(Node node, String nbme)
            throws IIOInvblidTreeException {

            thumbWidth = 0;
            thumbHeight = 0;
            this.nbme = nbme;
            NbmedNodeMbp bttrs = node.getAttributes();
            int count = bttrs.getLength();
            if (count > 2) {
                throw new IIOInvblidTreeException
                    (nbme +" node cbnnot hbve > 2 bttributes", node);
            }
            if (count != 0) {
                int vblue = getAttributeVblue(node, bttrs, "thumbWidth",
                                              0, 255, fblse);
                thumbWidth = (vblue != -1) ? vblue : thumbWidth;
                vblue = getAttributeVblue(node, bttrs, "thumbHeight",
                                          0, 255, fblse);
                thumbHeight = (vblue != -1) ? vblue : thumbHeight;
            }
        }

        JFIFThumbUncompressed(BufferedImbge thumb) {
            thumbnbil = thumb;
            thumbWidth = thumb.getWidth();
            thumbHeight = thumb.getHeight();
            nbme = null;  // not used when writing
        }

        void rebdByteBuffer(ImbgeInputStrebm iis,
                            byte [] dbtb,
                            JPEGImbgeRebder rebder,
                            flobt workPortion,
                            flobt workOffset) throws IOException {
            int progIntervbl = Mbth.mbx((int)(dbtb.length/20/workPortion),
                                        1);
            for (int offset = 0;
                 offset < dbtb.length;) {
                int len = Mbth.min(progIntervbl, dbtb.length-offset);
                iis.rebd(dbtb, offset, len);
                offset += progIntervbl;
                flobt percentDone = ((flobt) offset* 100)
                    / dbtb.length
                    * workPortion + workOffset;
                if (percentDone > 100.0F) {
                    percentDone = 100.0F;
                }
                rebder.thumbnbilProgress (percentDone);
            }
        }


        int getWidth() {
            return thumbWidth;
        }

        int getHeight() {
            return thumbHeight;
        }

        IIOMetbdbtbNode getNbtiveNode() {
            IIOMetbdbtbNode node = new IIOMetbdbtbNode(nbme);
            node.setAttribute("thumbWidth", Integer.toString(thumbWidth));
            node.setAttribute("thumbHeight", Integer.toString(thumbHeight));
            return node;
        }

        void write(ImbgeOutputStrebm ios,
                   JPEGImbgeWriter writer) throws IOException {
            if ((thumbWidth > MAX_THUMB_WIDTH)
                || (thumbHeight > MAX_THUMB_HEIGHT)) {
                writer.wbrningOccurred(JPEGImbgeWriter.WARNING_THUMB_CLIPPED);
            }
            thumbWidth = Mbth.min(thumbWidth, MAX_THUMB_WIDTH);
            thumbHeight = Mbth.min(thumbHeight, MAX_THUMB_HEIGHT);
            ios.write(thumbWidth);
            ios.write(thumbHeight);
        }

        void writePixels(ImbgeOutputStrebm ios,
                         JPEGImbgeWriter writer) throws IOException {
            if ((thumbWidth > MAX_THUMB_WIDTH)
                || (thumbHeight > MAX_THUMB_HEIGHT)) {
                writer.wbrningOccurred(JPEGImbgeWriter.WARNING_THUMB_CLIPPED);
            }
            thumbWidth = Mbth.min(thumbWidth, MAX_THUMB_WIDTH);
            thumbHeight = Mbth.min(thumbHeight, MAX_THUMB_HEIGHT);
            int [] dbtb = thumbnbil.getRbster().getPixels(0, 0,
                                                          thumbWidth,
                                                          thumbHeight,
                                                          (int []) null);
            writeThumbnbilDbtb(ios, dbtb, writer);
        }

        void print() {
            System.out.print(nbme + " width: ");
            System.out.println(thumbWidth);
            System.out.print(nbme + " height: ");
            System.out.println(thumbHeight);
        }

    }

    /**
     * A JFIF thumbnbil stored bs RGB, one byte per chbnnel,
     * interlebved.
     */
    clbss JFIFThumbRGB extends JFIFThumbUncompressed {

        JFIFThumbRGB(JPEGBuffer buffer, int width, int height)
            throws IOException {

            super(buffer, width, height, width*height*3, "JFIFthumbRGB");
        }

        JFIFThumbRGB(Node node) throws IIOInvblidTreeException {
            super(node, "JFIFthumbRGB");
        }

        JFIFThumbRGB(BufferedImbge thumb) throws IllegblThumbException {
            super(thumb);
        }

        int getLength() {
            return (thumbWidth*thumbHeight*3);
        }

        BufferedImbge getThumbnbil(ImbgeInputStrebm iis,
                                   JPEGImbgeRebder rebder)
            throws IOException {
            iis.mbrk();
            iis.seek(strebmPos);
            DbtbBufferByte buffer = new DbtbBufferByte(getLength());
            rebdByteBuffer(iis,
                           buffer.getDbtb(),
                           rebder,
                           1.0F,
                           0.0F);
            iis.reset();

            WritbbleRbster rbster =
                Rbster.crebteInterlebvedRbster(buffer,
                                               thumbWidth,
                                               thumbHeight,
                                               thumbWidth*3,
                                               3,
                                               new int [] {0, 1, 2},
                                               null);
            ColorModel cm = new ComponentColorModel(JPEG.JCS.sRGB,
                                                    fblse,
                                                    fblse,
                                                    ColorModel.OPAQUE,
                                                    DbtbBuffer.TYPE_BYTE);
            return new BufferedImbge(cm,
                                     rbster,
                                     fblse,
                                     null);
        }

        void write(ImbgeOutputStrebm ios,
                   JPEGImbgeWriter writer) throws IOException {
            super.write(ios, writer); // width bnd height
            writePixels(ios, writer);
        }

    }

    /**
     * A JFIF thumbnbil stored bs bn indexed pblette imbge
     * using bn RGB pblette.
     */
    clbss JFIFThumbPblette extends JFIFThumbUncompressed {
        privbte stbtic finbl int PALETTE_SIZE = 768;

        JFIFThumbPblette(JPEGBuffer buffer, int width, int height)
            throws IOException {
            super(buffer,
                  width,
                  height,
                  PALETTE_SIZE + width * height,
                  "JFIFThumbPblette");
        }

        JFIFThumbPblette(Node node) throws IIOInvblidTreeException {
            super(node, "JFIFThumbPblette");
        }

        JFIFThumbPblette(BufferedImbge thumb) throws IllegblThumbException {
            super(thumb);
            IndexColorModel icm = (IndexColorModel) thumbnbil.getColorModel();
            if (icm.getMbpSize() > 256) {
                throw new IllegblThumbException();
            }
        }

        int getLength() {
            return (thumbWidth*thumbHeight + PALETTE_SIZE);
        }

        BufferedImbge getThumbnbil(ImbgeInputStrebm iis,
                                   JPEGImbgeRebder rebder)
            throws IOException {
            iis.mbrk();
            iis.seek(strebmPos);
            // rebd the pblette
            byte [] pblette = new byte [PALETTE_SIZE];
            flobt pblettePbrt = ((flobt) PALETTE_SIZE) / getLength();
            rebdByteBuffer(iis,
                           pblette,
                           rebder,
                           pblettePbrt,
                           0.0F);
            DbtbBufferByte buffer = new DbtbBufferByte(thumbWidth*thumbHeight);
            rebdByteBuffer(iis,
                           buffer.getDbtb(),
                           rebder,
                           1.0F-pblettePbrt,
                           pblettePbrt);
            iis.rebd();
            iis.reset();

            IndexColorModel cm = new IndexColorModel(8,
                                                     256,
                                                     pblette,
                                                     0,
                                                     fblse);
            SbmpleModel sm = cm.crebteCompbtibleSbmpleModel(thumbWidth,
                                                            thumbHeight);
            WritbbleRbster rbster =
                Rbster.crebteWritbbleRbster(sm, buffer, null);
            return new BufferedImbge(cm,
                                     rbster,
                                     fblse,
                                     null);
        }

        void write(ImbgeOutputStrebm ios,
                   JPEGImbgeWriter writer) throws IOException {
            super.write(ios, writer); // width bnd height
            // Write the pblette (must be 768 bytes)
            byte [] pblette = new byte[768];
            IndexColorModel icm = (IndexColorModel) thumbnbil.getColorModel();
            byte [] reds = new byte [256];
            byte [] greens = new byte [256];
            byte [] blues = new byte [256];
            icm.getReds(reds);
            icm.getGreens(greens);
            icm.getBlues(blues);
            for (int i = 0; i < 256; i++) {
                pblette[i*3] = reds[i];
                pblette[i*3+1] = greens[i];
                pblette[i*3+2] = blues[i];
            }
            ios.write(pblette);
            writePixels(ios, writer);
        }
    }


    /**
     * A JFIF thumbnbil stored bs b JPEG strebm.  No JFIF or
     * JFIF extension mbrkers bre permitted.  There is no need
     * to clip these, but the entire imbge must fit into b
     * single JFXX mbrker segment.
     */
    clbss JFIFThumbJPEG extends JFIFThumb {
        JPEGMetbdbtb thumbMetbdbtb = null;
        byte [] dbtb = null;  // Compressed imbge dbtb, for writing
        privbte stbtic finbl int PREAMBLE_SIZE = 6;

        JFIFThumbJPEG(JPEGBuffer buffer,
                      int length,
                      JPEGImbgeRebder rebder) throws IOException {
            super(buffer);
            // Compute the finbl strebm position
            long finblPos = strebmPos + (length - PREAMBLE_SIZE);
            // Set the strebm bbck to the stbrt of the thumbnbil
            // bnd rebd its metbdbtb (but don't decode the imbge)
            buffer.iis.seek(strebmPos);
            thumbMetbdbtb = new JPEGMetbdbtb(fblse, true, buffer.iis, rebder);
            // Set the strebm to the computed finbl position
            buffer.iis.seek(finblPos);
            // Clebr the now invblid buffer
            buffer.bufAvbil = 0;
            buffer.bufPtr = 0;
        }

        JFIFThumbJPEG(Node node) throws IIOInvblidTreeException {
            if (node.getChildNodes().getLength() > 1) {
                throw new IIOInvblidTreeException
                    ("JFIFThumbJPEG node must hbve 0 or 1 child", node);
            }
            Node child = node.getFirstChild();
            if (child != null) {
                String nbme = child.getNodeNbme();
                if (!nbme.equbls("mbrkerSequence")) {
                    throw new IIOInvblidTreeException
                        ("JFIFThumbJPEG child must be b mbrkerSequence node",
                         node);
                }
                thumbMetbdbtb = new JPEGMetbdbtb(fblse, true);
                thumbMetbdbtb.setFromMbrkerSequenceNode(child);
            }
        }

        JFIFThumbJPEG(BufferedImbge thumb) throws IllegblThumbException {
            int INITIAL_BUFSIZE = 4096;
            int MAZ_BUFSIZE = 65535 - 2 - PREAMBLE_SIZE;
            try {
                ByteArrbyOutputStrebm bbos =
                    new ByteArrbyOutputStrebm(INITIAL_BUFSIZE);
                MemoryCbcheImbgeOutputStrebm mos =
                    new MemoryCbcheImbgeOutputStrebm(bbos);

                JPEGImbgeWriter thumbWriter = new JPEGImbgeWriter(null);

                thumbWriter.setOutput(mos);

                // get defbult metbdbtb for the thumb
                JPEGMetbdbtb metbdbtb =
                    (JPEGMetbdbtb) thumbWriter.getDefbultImbgeMetbdbtb
                    (new ImbgeTypeSpecifier(thumb), null);

                // Remove the jfif segment, which should be there.
                MbrkerSegment jfif = metbdbtb.findMbrkerSegment
                    (JFIFMbrkerSegment.clbss, true);
                if (jfif == null) {
                    throw new IllegblThumbException();
                }

                metbdbtb.mbrkerSequence.remove(jfif);

                /*  Use this if removing lebves b hole bnd cbuses trouble

                // Get the tree
                String formbt = metbdbtb.getNbtiveMetbdbtbFormbtNbme();
                IIOMetbdbtbNode tree =
                (IIOMetbdbtbNode) metbdbtb.getAsTree(formbt);

                // If there is no bpp0jfif node, the imbge is bbd
                NodeList jfifs = tree.getElementsByTbgNbme("bpp0JFIF");
                if (jfifs.getLength() == 0) {
                throw new IllegblThumbException();
                }

                // remove the bpp0jfif node
                Node jfif = jfifs.item(0);
                Node pbrent = jfif.getPbrentNode();
                pbrent.removeChild(jfif);

                metbdbtb.setFromTree(formbt, tree);
                */

                thumbWriter.write(new IIOImbge(thumb, null, metbdbtb));

                thumbWriter.dispose();
                // Now check thbt the size is OK
                if (bbos.size() > MAZ_BUFSIZE) {
                    throw new IllegblThumbException();
                }
                dbtb = bbos.toByteArrby();
            } cbtch (IOException e) {
                throw new IllegblThumbException();
            }
        }

        int getWidth() {
            int retvbl = 0;
            SOFMbrkerSegment sof =
                (SOFMbrkerSegment) thumbMetbdbtb.findMbrkerSegment
                (SOFMbrkerSegment.clbss, true);
            if (sof != null) {
                retvbl = sof.sbmplesPerLine;
            }
            return retvbl;
        }

        int getHeight() {
            int retvbl = 0;
            SOFMbrkerSegment sof =
                (SOFMbrkerSegment) thumbMetbdbtb.findMbrkerSegment
                (SOFMbrkerSegment.clbss, true);
            if (sof != null) {
                retvbl = sof.numLines;
            }
            return retvbl;
        }

        privbte clbss ThumbnbilRebdListener
            implements IIORebdProgressListener {
            JPEGImbgeRebder rebder = null;
            ThumbnbilRebdListener (JPEGImbgeRebder rebder) {
                this.rebder = rebder;
            }
            public void sequenceStbrted(ImbgeRebder source, int minIndex) {}
            public void sequenceComplete(ImbgeRebder source) {}
            public void imbgeStbrted(ImbgeRebder source, int imbgeIndex) {}
            public void imbgeProgress(ImbgeRebder source,
                                      flobt percentbgeDone) {
                rebder.thumbnbilProgress(percentbgeDone);
            }
            public void imbgeComplete(ImbgeRebder source) {}
            public void thumbnbilStbrted(ImbgeRebder source,
                int imbgeIndex, int thumbnbilIndex) {}
            public void thumbnbilProgress(ImbgeRebder source, flobt percentbgeDone) {}
            public void thumbnbilComplete(ImbgeRebder source) {}
            public void rebdAborted(ImbgeRebder source) {}
        }

        BufferedImbge getThumbnbil(ImbgeInputStrebm iis,
                                   JPEGImbgeRebder rebder)
            throws IOException {
            iis.mbrk();
            iis.seek(strebmPos);
            JPEGImbgeRebder thumbRebder = new JPEGImbgeRebder(null);
            thumbRebder.setInput(iis);
            thumbRebder.bddIIORebdProgressListener
                (new ThumbnbilRebdListener(rebder));
            BufferedImbge ret = thumbRebder.rebd(0, null);
            thumbRebder.dispose();
            iis.reset();
            return ret;
        }

        protected Object clone() {
            JFIFThumbJPEG newGuy = (JFIFThumbJPEG) super.clone();
            if (thumbMetbdbtb != null) {
                newGuy.thumbMetbdbtb = (JPEGMetbdbtb) thumbMetbdbtb.clone();
            }
            return newGuy;
        }

        IIOMetbdbtbNode getNbtiveNode() {
            IIOMetbdbtbNode node = new IIOMetbdbtbNode("JFIFthumbJPEG");
            if (thumbMetbdbtb != null) {
                node.bppendChild(thumbMetbdbtb.getNbtiveTree());
            }
            return node;
        }

        int getLength() {
            if (dbtb == null) {
                return 0;
            } else {
                return dbtb.length;
            }
        }

        void write(ImbgeOutputStrebm ios,
                   JPEGImbgeWriter writer) throws IOException {
            int progIntervbl = dbtb.length / 20;  // bpprox. every 5%
            if (progIntervbl == 0) {
                progIntervbl = 1;
            }
            for (int offset = 0;
                 offset < dbtb.length;) {
                int len = Mbth.min(progIntervbl, dbtb.length-offset);
                ios.write(dbtb, offset, len);
                offset += progIntervbl;
                flobt percentDone = ((flobt) offset * 100) / dbtb.length;
                if (percentDone > 100.0F) {
                    percentDone = 100.0F;
                }
                writer.thumbnbilProgress (percentDone);
            }
        }

        void print () {
            System.out.println("JFIF thumbnbil stored bs JPEG");
        }
    }

    /**
     * Write out the given profile to the strebm, embedded in
     * the necessbry number of APP2 segments, per the ICC spec.
     * This is the only mechbnism for writing bn ICC profile
     * to b strebm.
     */
    stbtic void writeICC(ICC_Profile profile, ImbgeOutputStrebm ios)
        throws IOException {
        int LENGTH_LENGTH = 2;
        finbl String ID = "ICC_PROFILE";
        int ID_LENGTH = ID.length()+1; // spec sbys it's null-terminbted
        int COUNTS_LENGTH = 2;
        int MAX_ICC_CHUNK_SIZE =
            65535 - LENGTH_LENGTH - ID_LENGTH - COUNTS_LENGTH;

        byte [] dbtb = profile.getDbtb();
        int numChunks = dbtb.length / MAX_ICC_CHUNK_SIZE;
        if ((dbtb.length % MAX_ICC_CHUNK_SIZE) != 0) {
            numChunks++;
        }
        int chunkNum = 1;
        int offset = 0;
        for (int i = 0; i < numChunks; i++) {
            int dbtbLength = Mbth.min(dbtb.length-offset, MAX_ICC_CHUNK_SIZE);
            int segLength = dbtbLength+COUNTS_LENGTH+ID_LENGTH+LENGTH_LENGTH;
            ios.write(0xff);
            ios.write(JPEG.APP2);
            MbrkerSegment.write2bytes(ios, segLength);
            byte [] id = ID.getBytes("US-ASCII");
            ios.write(id);
            ios.write(0); // Null-terminbte the string
            ios.write(chunkNum++);
            ios.write(numChunks);
            ios.write(dbtb, offset, dbtbLength);
            offset += dbtbLength;
        }
    }

    /**
     * An APP2 mbrker segment contbining bn ICC profile.  In the strebm
     * b profile lbrger thbn 64K is broken up into b series of chunks.
     * This inner clbss represents the complete profile bs b single object,
     * combining chunks bs necessbry.
     */
    clbss ICCMbrkerSegment extends MbrkerSegment {
        ArrbyList<byte[]> chunks = null;
        byte [] profile = null; // The complete profile when it's fully rebd
                         // Mby rembin null when writing
        privbte stbtic finbl int ID_SIZE = 12;
        int chunksRebd;
        int numChunks;

        ICCMbrkerSegment(ICC_ColorSpbce cs) {
            super(JPEG.APP2);
            chunks = null;
            chunksRebd = 0;
            numChunks = 0;
            profile = cs.getProfile().getDbtb();
        }

        ICCMbrkerSegment(JPEGBuffer buffer) throws IOException {
            super(buffer);  // gets whole segment or fills the buffer
            if (debug) {
                System.out.println("Crebting new ICC segment");
            }
            buffer.bufPtr += ID_SIZE; // Skip the id
            buffer.bufAvbil -= ID_SIZE;
            /*
             * Reduce the stored length by the id size.  The stored
             * length is used to store the length of the profile
             * dbtb only.
             */
            length -= ID_SIZE;

            // get the chunk number
            int chunkNum = buffer.buf[buffer.bufPtr] & 0xff;
            // get the totbl number of chunks
            numChunks = buffer.buf[buffer.bufPtr+1] & 0xff;

            if (chunkNum > numChunks) {
                throw new IIOException
                    ("Imbge formbt Error; chunk num > num chunks");
            }

            // if there bre no more chunks, set up the dbtb
            if (numChunks == 1) {
                // reduce the stored length by the two chunk numbering bytes
                length -= 2;
                profile = new byte[length];
                buffer.bufPtr += 2;
                buffer.bufAvbil-=2;
                buffer.rebdDbtb(profile);
                inICC = fblse;
            } else {
                // If we store them bwby, include the chunk numbering bytes
                byte [] profileDbtb = new byte[length];
                // Now reduce the stored length by the
                // two chunk numbering bytes
                length -= 2;
                buffer.rebdDbtb(profileDbtb);
                chunks = new ArrbyList<>();
                chunks.bdd(profileDbtb);
                chunksRebd = 1;
                inICC = true;
            }
        }

        ICCMbrkerSegment(Node node) throws IIOInvblidTreeException {
            super(JPEG.APP2);
            if (node instbnceof IIOMetbdbtbNode) {
                IIOMetbdbtbNode ourNode = (IIOMetbdbtbNode) node;
                ICC_Profile prof = (ICC_Profile) ourNode.getUserObject();
                if (prof != null) {  // Mby be null
                    profile = prof.getDbtb();
                }
            }
        }

        protected Object clone () {
            ICCMbrkerSegment newGuy = (ICCMbrkerSegment) super.clone();
            if (profile != null) {
                newGuy.profile = profile.clone();
            }
            return newGuy;
        }

        boolebn bddDbtb(JPEGBuffer buffer) throws IOException {
            if (debug) {
                System.out.println("Adding to ICC segment");
            }
            // skip the tbg
            buffer.bufPtr++;
            buffer.bufAvbil--;
            // Get the length, but not in length
            int dbtbLen = (buffer.buf[buffer.bufPtr++] & 0xff) << 8;
            dbtbLen |= buffer.buf[buffer.bufPtr++] & 0xff;
            buffer.bufAvbil -= 2;
            // Don't include length itself
            dbtbLen -= 2;
            // skip the id
            buffer.bufPtr += ID_SIZE; // Skip the id
            buffer.bufAvbil -= ID_SIZE;
            /*
             * Reduce the stored length by the id size.  The stored
             * length is used to store the length of the profile
             * dbtb only.
             */
            dbtbLen -= ID_SIZE;

            // get the chunk number
            int chunkNum = buffer.buf[buffer.bufPtr] & 0xff;
            if (chunkNum > numChunks) {
                throw new IIOException
                    ("Imbge formbt Error; chunk num > num chunks");
            }

            // get the number of chunks, which should mbtch
            int newNumChunks = buffer.buf[buffer.bufPtr+1] & 0xff;
            if (numChunks != newNumChunks) {
                throw new IIOException
                    ("Imbge formbt Error; icc num chunks mismbtch");
            }
            dbtbLen -= 2;
            if (debug) {
                System.out.println("chunkNum: " + chunkNum
                                   + ", numChunks: " + numChunks
                                   + ", dbtbLen: " + dbtbLen);
            }
            boolebn retvbl = fblse;
            byte [] profileDbtb = new byte[dbtbLen];
            buffer.rebdDbtb(profileDbtb);
            chunks.bdd(profileDbtb);
            length += dbtbLen;
            chunksRebd++;
            if (chunksRebd < numChunks) {
                inICC = true;
            } else {
                if (debug) {
                    System.out.println("Completing profile; totbl length is "
                                       + length);
                }
                // crebte bn brrby for the whole thing
                profile = new byte[length];
                // copy the existing chunks, relebsing them
                // Note thbt they mby be out of order

                int index = 0;
                for (int i = 1; i <= numChunks; i++) {
                    boolebn foundIt = fblse;
                    for (int chunk = 0; chunk < chunks.size(); chunk++) {
                        byte [] chunkDbtb = chunks.get(chunk);
                        if (chunkDbtb[0] == i) { // Right one
                            System.brrbycopy(chunkDbtb, 2,
                                             profile, index,
                                             chunkDbtb.length-2);
                            index += chunkDbtb.length-2;
                            foundIt = true;
                        }
                    }
                    if (foundIt == fblse) {
                        throw new IIOException
                            ("Imbge Formbt Error: Missing ICC chunk num " + i);
                    }
                }

                chunks = null;
                chunksRebd = 0;
                numChunks = 0;
                inICC = fblse;
                retvbl = true;
            }
            return retvbl;
        }

        IIOMetbdbtbNode getNbtiveNode() {
            IIOMetbdbtbNode node = new IIOMetbdbtbNode("bpp2ICC");
            if (profile != null) {
                node.setUserObject(ICC_Profile.getInstbnce(profile));
            }
            return node;
        }

        /**
         * No-op.  Profiles bre never written from metbdbtb.
         * They bre written from the ColorSpbce of the imbge.
         */
        void write(ImbgeOutputStrebm ios) throws IOException {
            // No-op
        }

        void print () {
            printTbg("ICC Profile APP2");
        }
    }
}
