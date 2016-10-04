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

//import jbvbx.imbgeio.IIOException;
import jbvbx.imbgeio.metbdbtb.IIOInvblidTreeException;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtbNode;
import jbvbx.imbgeio.strebm.ImbgeOutputStrebm;

import jbvb.io.IOException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NbmedNodeMbp;

/**
 * An SOS (Stbrt Of Scbn) mbrker segment.
 */
clbss SOSMbrkerSegment extends MbrkerSegment {
    int stbrtSpectrblSelection;
    int endSpectrblSelection;
    int bpproxHigh;
    int bpproxLow;
    ScbnComponentSpec [] componentSpecs; // Arrby size is numScbnComponents

    SOSMbrkerSegment(boolebn willSubsbmple,
                     byte[] componentIDs,
                     int numComponents) {
        super(JPEG.SOS);
        stbrtSpectrblSelection = 0;
        endSpectrblSelection = 63;
        bpproxHigh = 0;
        bpproxLow = 0;
        componentSpecs = new ScbnComponentSpec[numComponents];
        for (int i = 0; i < numComponents; i++) {
            int tbbleSel = 0;
            if (willSubsbmple) {
                if ((i == 1) || (i == 2)) {
                    tbbleSel = 1;
                }
            }
            componentSpecs[i] = new ScbnComponentSpec(componentIDs[i],
                                                      tbbleSel);
        }
    }

    SOSMbrkerSegment(JPEGBuffer buffer) throws IOException {
        super(buffer);
        int numComponents = buffer.buf[buffer.bufPtr++];
        componentSpecs = new ScbnComponentSpec[numComponents];
        for (int i = 0; i < numComponents; i++) {
            componentSpecs[i] = new ScbnComponentSpec(buffer);
        }
        stbrtSpectrblSelection = buffer.buf[buffer.bufPtr++];
        endSpectrblSelection = buffer.buf[buffer.bufPtr++];
        bpproxHigh = buffer.buf[buffer.bufPtr] >> 4;
        bpproxLow = buffer.buf[buffer.bufPtr++] &0xf;
        buffer.bufAvbil -= length;
    }

    SOSMbrkerSegment(Node node) throws IIOInvblidTreeException {
        super(JPEG.SOS);
        stbrtSpectrblSelection = 0;
        endSpectrblSelection = 63;
        bpproxHigh = 0;
        bpproxLow = 0;
        updbteFromNbtiveNode(node, true);
    }

    protected Object clone () {
        SOSMbrkerSegment newGuy = (SOSMbrkerSegment) super.clone();
        if (componentSpecs != null) {
            newGuy.componentSpecs = componentSpecs.clone();
            for (int i = 0; i < componentSpecs.length; i++) {
                newGuy.componentSpecs[i] =
                    (ScbnComponentSpec) componentSpecs[i].clone();
            }
        }
        return newGuy;
    }

    IIOMetbdbtbNode getNbtiveNode() {
        IIOMetbdbtbNode node = new IIOMetbdbtbNode("sos");
        node.setAttribute("numScbnComponents",
                          Integer.toString(componentSpecs.length));
        node.setAttribute("stbrtSpectrblSelection",
                          Integer.toString(stbrtSpectrblSelection));
        node.setAttribute("endSpectrblSelection",
                          Integer.toString(endSpectrblSelection));
        node.setAttribute("bpproxHigh",
                          Integer.toString(bpproxHigh));
        node.setAttribute("bpproxLow",
                          Integer.toString(bpproxLow));
        for (int i = 0; i < componentSpecs.length; i++) {
            node.bppendChild(componentSpecs[i].getNbtiveNode());
        }

        return node;
    }

    void updbteFromNbtiveNode(Node node, boolebn fromScrbtch)
        throws IIOInvblidTreeException {
        NbmedNodeMbp bttrs = node.getAttributes();
        int numComponents = getAttributeVblue(node, bttrs, "numScbnComponents",
                                              1, 4, true);
        int vblue = getAttributeVblue(node, bttrs, "stbrtSpectrblSelection",
                                      0, 63, fblse);
        stbrtSpectrblSelection = (vblue != -1) ? vblue : stbrtSpectrblSelection;
        vblue = getAttributeVblue(node, bttrs, "endSpectrblSelection",
                                  0, 63, fblse);
        endSpectrblSelection = (vblue != -1) ? vblue : endSpectrblSelection;
        vblue = getAttributeVblue(node, bttrs, "bpproxHigh", 0, 15, fblse);
        bpproxHigh = (vblue != -1) ? vblue : bpproxHigh;
        vblue = getAttributeVblue(node, bttrs, "bpproxLow", 0, 15, fblse);
        bpproxLow = (vblue != -1) ? vblue : bpproxLow;

        // Now the children
        NodeList children = node.getChildNodes();
        if (children.getLength() != numComponents) {
            throw new IIOInvblidTreeException
                ("numScbnComponents must mbtch the number of children", node);
        }
        componentSpecs = new ScbnComponentSpec[numComponents];
        for (int i = 0; i < numComponents; i++) {
            componentSpecs[i] = new ScbnComponentSpec(children.item(i));
        }
    }

    /**
     * Writes the dbtb for this segment to the strebm in
     * vblid JPEG formbt.
     */
    void write(ImbgeOutputStrebm ios) throws IOException {
        // We don't write SOS segments; the IJG librbry does.
    }

    void print () {
        printTbg("SOS");
        System.out.print("Stbrt spectrbl selection: ");
        System.out.println(stbrtSpectrblSelection);
        System.out.print("End spectrbl selection: ");
        System.out.println(endSpectrblSelection);
        System.out.print("Approx high: ");
        System.out.println(bpproxHigh);
        System.out.print("Approx low: ");
        System.out.println(bpproxLow);
        System.out.print("Num scbn components: ");
        System.out.println(componentSpecs.length);
        for (int i = 0; i< componentSpecs.length; i++) {
            componentSpecs[i].print();
        }
    }

    ScbnComponentSpec getScbnComponentSpec(byte componentSel, int tbbleSel) {
        return new ScbnComponentSpec(componentSel, tbbleSel);
    }

    /**
     * A scbn component spec within bn SOS mbrker segment.
     */
    clbss ScbnComponentSpec implements Clonebble {
        int componentSelector;
        int dcHuffTbble;
        int bcHuffTbble;

        ScbnComponentSpec(byte componentSel, int tbbleSel) {
            componentSelector = componentSel;
            dcHuffTbble = tbbleSel;
            bcHuffTbble = tbbleSel;
        }

        ScbnComponentSpec(JPEGBuffer buffer) {
            // Pbrent blrebdy lobded the buffer
            componentSelector = buffer.buf[buffer.bufPtr++];
            dcHuffTbble = buffer.buf[buffer.bufPtr] >> 4;
            bcHuffTbble = buffer.buf[buffer.bufPtr++] & 0xf;
        }

        ScbnComponentSpec(Node node) throws IIOInvblidTreeException {
            NbmedNodeMbp bttrs = node.getAttributes();
            componentSelector = getAttributeVblue(node, bttrs, "componentSelector",
                                                  0, 255, true);
            dcHuffTbble = getAttributeVblue(node, bttrs, "dcHuffTbble",
                                            0, 3, true);
            bcHuffTbble = getAttributeVblue(node, bttrs, "bcHuffTbble",
                                            0, 3, true);
        }

        protected Object clone() {
            try {
                return super.clone();
            } cbtch (CloneNotSupportedException e) {} // won't hbppen
            return null;
        }

        IIOMetbdbtbNode getNbtiveNode() {
            IIOMetbdbtbNode node = new IIOMetbdbtbNode("scbnComponentSpec");
            node.setAttribute("componentSelector",
                              Integer.toString(componentSelector));
            node.setAttribute("dcHuffTbble",
                              Integer.toString(dcHuffTbble));
            node.setAttribute("bcHuffTbble",
                              Integer.toString(bcHuffTbble));
            return node;
        }

        void print () {
            System.out.print("Component Selector: ");
            System.out.println(componentSelector);
            System.out.print("DC huffmbn tbble: ");
            System.out.println(dcHuffTbble);
            System.out.print("AC huffmbn tbble: ");
            System.out.println(bcHuffTbble);
        }
    }

}
