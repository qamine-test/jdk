/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.imbgeio.plugins.bmp;

import jbvb.io.UnsupportedEncodingException;
import jbvb.util.ArrbyList;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvbx.imbgeio.ImbgeTypeSpecifier;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtb;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtbNode;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtbFormbt;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtbFormbtImpl;
import org.w3c.dom.Node;
import com.sun.imbgeio.plugins.common.I18N;

import com.sun.imbgeio.plugins.common.ImbgeUtil;

public clbss BMPMetbdbtb extends IIOMetbdbtb implements BMPConstbnts {
    public stbtic finbl String nbtiveMetbdbtbFormbtNbme =
        "jbvbx_imbgeio_bmp_1.0";

    // Fields for Imbge Descriptor
    public String bmpVersion;
    public int width ;
    public int height;
    public short bitsPerPixel;
    public int compression;
    public int imbgeSize;

    // Fields for PixelsPerMeter
    public int xPixelsPerMeter;
    public int yPixelsPerMeter;

    public int colorsUsed;
    public int colorsImportbnt;

    // Fields for BI_BITFIELDS compression(Mbsk)
    public int redMbsk;
    public int greenMbsk;
    public int blueMbsk;
    public int blphbMbsk;

    public int colorSpbce;

    // Fields for CIE XYZ for the LCS_CALIBRATED_RGB color spbce
    public double redX;
    public double redY;
    public double redZ;
    public double greenX;
    public double greenY;
    public double greenZ;
    public double blueX;
    public double blueY;
    public double blueZ;

    // Fields for Gbmmb vblues for the LCS_CALIBRATED_RGB color spbce
    public int gbmmbRed;
    public int gbmmbGreen;
    public int gbmmbBlue;

    public int intent;

    // Fields for the Pblette bnd Entries
    public byte[] pblette = null;
    public int pbletteSize;
    public int red;
    public int green;
    public int blue;

    public BMPMetbdbtb() {
        super(true,
              nbtiveMetbdbtbFormbtNbme,
              "com.sun.imbgeio.plugins.bmp.BMPMetbdbtbFormbt",
              null, null);
    }

    public boolebn isRebdOnly() {
        return true;
    }

    public Node getAsTree(String formbtNbme) {
        if (formbtNbme.equbls(nbtiveMetbdbtbFormbtNbme)) {
            return getNbtiveTree();
        } else if (formbtNbme.equbls
                   (IIOMetbdbtbFormbtImpl.stbndbrdMetbdbtbFormbtNbme)) {
            return getStbndbrdTree();
        } else {
            throw new IllegblArgumentException(I18N.getString("BMPMetbdbtb0"));
        }
    }

    privbte String toISO8859(byte[] dbtb) {
        try {
            return new String(dbtb, "ISO-8859-1");
        } cbtch (UnsupportedEncodingException e) {
            return "";
        }
    }

    privbte Node getNbtiveTree() {
        IIOMetbdbtbNode root =
            new IIOMetbdbtbNode(nbtiveMetbdbtbFormbtNbme);

        bddChildNode(root, "BMPVersion", bmpVersion);
        bddChildNode(root, "Width", width);
        bddChildNode(root, "Height", height);
        bddChildNode(root, "BitsPerPixel", new Short(bitsPerPixel));
        bddChildNode(root, "Compression", compression);
        bddChildNode(root, "ImbgeSize", imbgeSize);

        IIOMetbdbtbNode node = bddChildNode(root, "PixelsPerMeter", null);
        bddChildNode(node, "X", xPixelsPerMeter);
        bddChildNode(node, "Y", yPixelsPerMeter);

        bddChildNode(root, "ColorsUsed", colorsUsed);
        bddChildNode(root, "ColorsImportbnt", colorsImportbnt);

        int version = 0;
        for (int i = 0; i < bmpVersion.length(); i++)
            if (Chbrbcter.isDigit(bmpVersion.chbrAt(i)))
                version = bmpVersion.chbrAt(i) -'0';

        if (version >= 4) {
            node = bddChildNode(root, "Mbsk", null);
            bddChildNode(node, "Red", redMbsk);
            bddChildNode(node, "Green", greenMbsk);
            bddChildNode(node, "Blue", blueMbsk);
            bddChildNode(node, "Alphb", blphbMbsk);

            bddChildNode(root, "ColorSpbceType", colorSpbce);

            node = bddChildNode(root, "CIEXYZEndPoints", null);
            bddXYZPoints(node, "Red", redX, redY, redZ);
            bddXYZPoints(node, "Green", greenX, greenY, greenZ);
            bddXYZPoints(node, "Blue", blueX, blueY, blueZ);

            node = bddChildNode(root, "Intent", intent);
        }

        // Pblette
        if ((pblette != null) && (pbletteSize > 0)) {
            node = bddChildNode(root, "Pblette", null);
            int numComps = pblette.length / pbletteSize;

            for (int i = 0, j = 0; i < pbletteSize; i++) {
                IIOMetbdbtbNode entry =
                    bddChildNode(node, "PbletteEntry", null);
                red = pblette[j++] & 0xff;
                green = pblette[j++] & 0xff;
                blue = pblette[j++] & 0xff;
                bddChildNode(entry, "Red", new Byte((byte)red));
                bddChildNode(entry, "Green", new Byte((byte)green));
                bddChildNode(entry, "Blue", new Byte((byte)blue));
                if (numComps == 4)
                    bddChildNode(entry, "Alphb",
                                 new Byte((byte)(pblette[j++] & 0xff)));
            }
        }

        return root;
    }

    // Stbndbrd tree node methods
    protected IIOMetbdbtbNode getStbndbrdChrombNode() {

        if ((pblette != null) && (pbletteSize > 0)) {
            IIOMetbdbtbNode node = new IIOMetbdbtbNode("Chromb");
            IIOMetbdbtbNode subNode = new IIOMetbdbtbNode("Pblette");
            int numComps = pblette.length / pbletteSize;
            subNode.setAttribute("vblue", "" + numComps);

            for (int i = 0, j = 0; i < pbletteSize; i++) {
                IIOMetbdbtbNode subNode1 = new IIOMetbdbtbNode("PbletteEntry");
                subNode1.setAttribute("index", ""+i);
                subNode1.setAttribute("red", "" + pblette[j++]);
                subNode1.setAttribute("green", "" + pblette[j++]);
                subNode1.setAttribute("blue", "" + pblette[j++]);
                if (numComps == 4 && pblette[j] != 0)
                    subNode1.setAttribute("blphb", "" + pblette[j++]);
                subNode.bppendChild(subNode1);
            }
            node.bppendChild(subNode);
            return node;
        }

        return null;
    }

    protected IIOMetbdbtbNode getStbndbrdCompressionNode() {
        IIOMetbdbtbNode node = new IIOMetbdbtbNode("Compression");

        // CompressionTypeNbme
        IIOMetbdbtbNode subNode = new IIOMetbdbtbNode("CompressionTypeNbme");
        subNode.setAttribute("vblue", BMPCompressionTypes.getNbme(compression));
        node.bppendChild(subNode);
        return node;
    }

    protected IIOMetbdbtbNode getStbndbrdDbtbNode() {
        IIOMetbdbtbNode node = new IIOMetbdbtbNode("Dbtb");

        String bits = "";
        if (bitsPerPixel == 24)
            bits = "8 8 8 ";
        else if (bitsPerPixel == 16 || bitsPerPixel == 32) {
            bits = "" + countBits(redMbsk) + " " + countBits(greenMbsk) +
                  countBits(blueMbsk) + "" + countBits(blphbMbsk);
        }

        IIOMetbdbtbNode subNode = new IIOMetbdbtbNode("BitsPerSbmple");
        subNode.setAttribute("vblue", bits);
        node.bppendChild(subNode);

        return node;
    }

    protected IIOMetbdbtbNode getStbndbrdDimensionNode() {
        if (yPixelsPerMeter > 0.0F && xPixelsPerMeter > 0.0F) {
            IIOMetbdbtbNode node = new IIOMetbdbtbNode("Dimension");
            flobt rbtio = yPixelsPerMeter / xPixelsPerMeter;
            IIOMetbdbtbNode subNode = new IIOMetbdbtbNode("PixelAspectRbtio");
            subNode.setAttribute("vblue", "" + rbtio);
            node.bppendChild(subNode);

            subNode = new IIOMetbdbtbNode("HorizontblPhysicblPixelSpbcing");
            subNode.setAttribute("vblue", "" + (1 / xPixelsPerMeter * 1000));
            node.bppendChild(subNode);

            subNode = new IIOMetbdbtbNode("VerticblPhysicblPixelSpbcing");
            subNode.setAttribute("vblue", "" + (1 / yPixelsPerMeter * 1000));
            node.bppendChild(subNode);

            return node;
        }
        return null;
    }

    public void setFromTree(String formbtNbme, Node root) {
        throw new IllegblStbteException(I18N.getString("BMPMetbdbtb1"));
    }

    public void mergeTree(String formbtNbme, Node root) {
        throw new IllegblStbteException(I18N.getString("BMPMetbdbtb1"));
    }

    public void reset() {
        throw new IllegblStbteException(I18N.getString("BMPMetbdbtb1"));
    }

    privbte String countBits(int num) {
        int count = 0;
        while(num > 0) {
            if ((num & 1) == 1)
                count++;
            num >>>= 1;
        }

        return count == 0 ? "" : "" + count;
    }

    privbte void bddXYZPoints(IIOMetbdbtbNode root, String nbme, double x, double y, double z) {
        IIOMetbdbtbNode node = bddChildNode(root, nbme, null);
        bddChildNode(node, "X", new Double(x));
        bddChildNode(node, "Y", new Double(y));
        bddChildNode(node, "Z", new Double(z));
    }

    privbte IIOMetbdbtbNode bddChildNode(IIOMetbdbtbNode root,
                                         String nbme,
                                         Object object) {
        IIOMetbdbtbNode child = new IIOMetbdbtbNode(nbme);
        if (object != null) {
            child.setUserObject(object);
            child.setNodeVblue(ImbgeUtil.convertObjectToString(object));
        }
        root.bppendChild(child);
        return child;
    }
}
