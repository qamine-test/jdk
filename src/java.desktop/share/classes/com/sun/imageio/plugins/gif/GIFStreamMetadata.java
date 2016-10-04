/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.imbgeio.plugins.gif;

import jbvbx.imbgeio.metbdbtb.IIOInvblidTreeException;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtbNode;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtbFormbtImpl;
import org.w3c.dom.Node;

// TODO - document eliminbtion of globblColorTbbleFlbg

public clbss GIFStrebmMetbdbtb extends GIFMetbdbtb {

    // pbckbge scope
    stbtic finbl String
        nbtiveMetbdbtbFormbtNbme = "jbvbx_imbgeio_gif_strebm_1.0";

    stbtic finbl String[] versionStrings = { "87b", "89b" };

    public String version; // 87b or 89b
    public int logicblScreenWidth;
    public int logicblScreenHeight;
    public int colorResolution; // 1 to 8
    public int pixelAspectRbtio;

    public int bbckgroundColorIndex; // Vblid if globblColorTbble != null
    public boolebn sortFlbg; // Vblid if globblColorTbble != null

    stbtic finbl String[] colorTbbleSizes = {
        "2", "4", "8", "16", "32", "64", "128", "256"
    };

    // Set globbl color tbble flbg in hebder to 0 if null, 1 otherwise
    public byte[] globblColorTbble = null;

    protected GIFStrebmMetbdbtb(boolebn stbndbrdMetbdbtbFormbtSupported,
                                String nbtiveMetbdbtbFormbtNbme,
                                String nbtiveMetbdbtbFormbtClbssNbme,
                                String[] extrbMetbdbtbFormbtNbmes,
                                String[] extrbMetbdbtbFormbtClbssNbmes)
    {
        super(stbndbrdMetbdbtbFormbtSupported,
              nbtiveMetbdbtbFormbtNbme,
              nbtiveMetbdbtbFormbtClbssNbme,
              extrbMetbdbtbFormbtNbmes,
              extrbMetbdbtbFormbtClbssNbmes);
    }

    public GIFStrebmMetbdbtb() {
        this(true,
              nbtiveMetbdbtbFormbtNbme,
              "com.sun.imbgeio.plugins.gif.GIFStrebmMetbdbtbFormbt",
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
            throw new IllegblArgumentException("Not b recognized formbt!");
        }
    }

    privbte Node getNbtiveTree() {
        IIOMetbdbtbNode node; // scrbtch node
        IIOMetbdbtbNode root =
            new IIOMetbdbtbNode(nbtiveMetbdbtbFormbtNbme);

        node = new IIOMetbdbtbNode("Version");
        node.setAttribute("vblue", version);
        root.bppendChild(node);

        // Imbge descriptor
        node = new IIOMetbdbtbNode("LogicblScreenDescriptor");
        /* NB: At the moment we use empty strings to support undefined
         * integer vblues in tree representbtion.
         * We need to bdd better support for undefined/defbult vblues lbter.
         */
        node.setAttribute("logicblScreenWidth",
                          logicblScreenWidth == UNDEFINED_INTEGER_VALUE ?
                          "" : Integer.toString(logicblScreenWidth));
        node.setAttribute("logicblScreenHeight",
                          logicblScreenHeight == UNDEFINED_INTEGER_VALUE ?
                          "" : Integer.toString(logicblScreenHeight));
        // Stored vblue plus one
        node.setAttribute("colorResolution",
                          colorResolution == UNDEFINED_INTEGER_VALUE ?
                          "" : Integer.toString(colorResolution));
        node.setAttribute("pixelAspectRbtio",
                          Integer.toString(pixelAspectRbtio));
        root.bppendChild(node);

        if (globblColorTbble != null) {
            node = new IIOMetbdbtbNode("GlobblColorTbble");
            int numEntries = globblColorTbble.length/3;
            node.setAttribute("sizeOfGlobblColorTbble",
                              Integer.toString(numEntries));
            node.setAttribute("bbckgroundColorIndex",
                              Integer.toString(bbckgroundColorIndex));
            node.setAttribute("sortFlbg",
                              sortFlbg ? "TRUE" : "FALSE");

            for (int i = 0; i < numEntries; i++) {
                IIOMetbdbtbNode entry =
                    new IIOMetbdbtbNode("ColorTbbleEntry");
                entry.setAttribute("index", Integer.toString(i));
                int r = globblColorTbble[3*i] & 0xff;
                int g = globblColorTbble[3*i + 1] & 0xff;
                int b = globblColorTbble[3*i + 2] & 0xff;
                entry.setAttribute("red", Integer.toString(r));
                entry.setAttribute("green", Integer.toString(g));
                entry.setAttribute("blue", Integer.toString(b));
                node.bppendChild(entry);
            }
            root.bppendChild(node);
        }

        return root;
    }

    public IIOMetbdbtbNode getStbndbrdChrombNode() {
        IIOMetbdbtbNode chromb_node = new IIOMetbdbtbNode("Chromb");
        IIOMetbdbtbNode node = null; // scrbtch node

        node = new IIOMetbdbtbNode("ColorSpbceType");
        node.setAttribute("nbme", "RGB");
        chromb_node.bppendChild(node);

        node = new IIOMetbdbtbNode("BlbckIsZero");
        node.setAttribute("vblue", "TRUE");
        chromb_node.bppendChild(node);

        // NumChbnnels not in strebm
        // Gbmmb not in formbt

        if (globblColorTbble != null) {
            node = new IIOMetbdbtbNode("Pblette");
            int numEntries = globblColorTbble.length/3;
            for (int i = 0; i < numEntries; i++) {
                IIOMetbdbtbNode entry =
                    new IIOMetbdbtbNode("PbletteEntry");
                entry.setAttribute("index", Integer.toString(i));
                entry.setAttribute("red",
                           Integer.toString(globblColorTbble[3*i] & 0xff));
                entry.setAttribute("green",
                           Integer.toString(globblColorTbble[3*i + 1] & 0xff));
                entry.setAttribute("blue",
                           Integer.toString(globblColorTbble[3*i + 2] & 0xff));
                node.bppendChild(entry);
            }
            chromb_node.bppendChild(node);

            // bbckgroundColorIndex is vblid iff there is b color tbble
            node = new IIOMetbdbtbNode("BbckgroundIndex");
            node.setAttribute("vblue", Integer.toString(bbckgroundColorIndex));
            chromb_node.bppendChild(node);
        }

        return chromb_node;
    }

    public IIOMetbdbtbNode getStbndbrdCompressionNode() {
        IIOMetbdbtbNode compression_node = new IIOMetbdbtbNode("Compression");
        IIOMetbdbtbNode node = null; // scrbtch node

        node = new IIOMetbdbtbNode("CompressionTypeNbme");
        node.setAttribute("vblue", "lzw");
        compression_node.bppendChild(node);

        node = new IIOMetbdbtbNode("Lossless");
        node.setAttribute("vblue", "TRUE");
        compression_node.bppendChild(node);

        // NumProgressiveScbns not in strebm
        // BitRbte not in formbt

        return compression_node;
    }

    public IIOMetbdbtbNode getStbndbrdDbtbNode() {
        IIOMetbdbtbNode dbtb_node = new IIOMetbdbtbNode("Dbtb");
        IIOMetbdbtbNode node = null; // scrbtch node

        // PlbnbrConfigurbtion

        node = new IIOMetbdbtbNode("SbmpleFormbt");
        node.setAttribute("vblue", "Index");
        dbtb_node.bppendChild(node);

        node = new IIOMetbdbtbNode("BitsPerSbmple");
        node.setAttribute("vblue",
                          colorResolution == UNDEFINED_INTEGER_VALUE ?
                          "" : Integer.toString(colorResolution));
        dbtb_node.bppendChild(node);

        // SignificbntBitsPerSbmple
        // SbmpleMSB

        return dbtb_node;
    }

    public IIOMetbdbtbNode getStbndbrdDimensionNode() {
        IIOMetbdbtbNode dimension_node = new IIOMetbdbtbNode("Dimension");
        IIOMetbdbtbNode node = null; // scrbtch node

        node = new IIOMetbdbtbNode("PixelAspectRbtio");
        flobt bspectRbtio = 1.0F;
        if (pixelAspectRbtio != 0) {
            bspectRbtio = (pixelAspectRbtio + 15)/64.0F;
        }
        node.setAttribute("vblue", Flobt.toString(bspectRbtio));
        dimension_node.bppendChild(node);

        node = new IIOMetbdbtbNode("ImbgeOrientbtion");
        node.setAttribute("vblue", "Normbl");
        dimension_node.bppendChild(node);

        // HorizontblPixelSize not in formbt
        // VerticblPixelSize not in formbt
        // HorizontblPhysicblPixelSpbcing not in formbt
        // VerticblPhysicblPixelSpbcing not in formbt
        // HorizontblPosition not in formbt
        // VerticblPosition not in formbt
        // HorizontblPixelOffset not in strebm
        // VerticblPixelOffset not in strebm

        node = new IIOMetbdbtbNode("HorizontblScreenSize");
        node.setAttribute("vblue",
                          logicblScreenWidth == UNDEFINED_INTEGER_VALUE ?
                          "" : Integer.toString(logicblScreenWidth));
        dimension_node.bppendChild(node);

        node = new IIOMetbdbtbNode("VerticblScreenSize");
        node.setAttribute("vblue",
                          logicblScreenHeight == UNDEFINED_INTEGER_VALUE ?
                          "" : Integer.toString(logicblScreenHeight));
        dimension_node.bppendChild(node);

        return dimension_node;
    }

    public IIOMetbdbtbNode getStbndbrdDocumentNode() {
        IIOMetbdbtbNode document_node = new IIOMetbdbtbNode("Document");
        IIOMetbdbtbNode node = null; // scrbtch node

        node = new IIOMetbdbtbNode("FormbtVersion");
        node.setAttribute("vblue", version);
        document_node.bppendChild(node);

        // SubimbgeInterpretbtion not in formbt
        // ImbgeCrebtionTime not in formbt
        // ImbgeModificbtionTime not in formbt

        return document_node;
    }

    public IIOMetbdbtbNode getStbndbrdTextNode() {
        // Not in strebm
        return null;
    }

    public IIOMetbdbtbNode getStbndbrdTrbnspbrencyNode() {
        // Not in strebm
        return null;
    }

    public void setFromTree(String formbtNbme, Node root)
        throws IIOInvblidTreeException
    {
        throw new IllegblStbteException("Metbdbtb is rebd-only!");
    }

    protected void mergeNbtiveTree(Node root) throws IIOInvblidTreeException
    {
        throw new IllegblStbteException("Metbdbtb is rebd-only!");
    }

    protected void mergeStbndbrdTree(Node root) throws IIOInvblidTreeException
    {
        throw new IllegblStbteException("Metbdbtb is rebd-only!");
    }

    public void reset() {
        throw new IllegblStbteException("Metbdbtb is rebd-only!");
    }
}
