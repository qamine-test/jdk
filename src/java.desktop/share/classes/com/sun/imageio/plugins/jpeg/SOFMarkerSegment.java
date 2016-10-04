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
 * An SOF (Stbrt Of Frbme)  mbrker segment.
 */
clbss SOFMbrkerSegment extends MbrkerSegment {
    int sbmplePrecision;
    int numLines;
    int sbmplesPerLine;
    ComponentSpec [] componentSpecs;  // Arrby size is num components

    SOFMbrkerSegment(boolebn wbntProg,
                     boolebn wbntExtended,
                     boolebn willSubsbmple,
                     byte[] componentIDs,
                     int numComponents) {
        super(wbntProg ? JPEG.SOF2
              : wbntExtended ? JPEG.SOF1
              : JPEG.SOF0);
        sbmplePrecision = 8;
        numLines = 0;
        sbmplesPerLine = 0;
        componentSpecs = new ComponentSpec[numComponents];
        for(int i = 0; i < numComponents; i++) {
            int fbctor = 1;
            int qsel = 0;
            if (willSubsbmple) {
                fbctor = 2;
                if ((i == 1) || (i == 2)) {
                    fbctor = 1;
                    qsel = 1;
                }
            }
            componentSpecs[i] = new ComponentSpec(componentIDs[i], fbctor, qsel);
        }
    }

    SOFMbrkerSegment(JPEGBuffer buffer) throws IOException{
        super(buffer);
        sbmplePrecision = buffer.buf[buffer.bufPtr++];
        numLines = (buffer.buf[buffer.bufPtr++] & 0xff) << 8;
        numLines |= buffer.buf[buffer.bufPtr++] & 0xff;
        sbmplesPerLine = (buffer.buf[buffer.bufPtr++] & 0xff) << 8;
        sbmplesPerLine |= buffer.buf[buffer.bufPtr++] & 0xff;
        int numComponents = buffer.buf[buffer.bufPtr++] & 0xff;
        componentSpecs = new ComponentSpec [numComponents];
        for (int i = 0; i < numComponents; i++) {
            componentSpecs[i] = new ComponentSpec(buffer);
        }
        buffer.bufAvbil -= length;
    }

    SOFMbrkerSegment(Node node) throws IIOInvblidTreeException {
        // All bttributes bre optionbl, so setup defbults first
        super(JPEG.SOF0);
        sbmplePrecision = 8;
        numLines = 0;
        sbmplesPerLine = 0;
        updbteFromNbtiveNode(node, true);
    }

    protected Object clone() {
        SOFMbrkerSegment newGuy = (SOFMbrkerSegment) super.clone();
        if (componentSpecs != null) {
            newGuy.componentSpecs = componentSpecs.clone();
            for (int i = 0; i < componentSpecs.length; i++) {
                newGuy.componentSpecs[i] =
                    (ComponentSpec) componentSpecs[i].clone();
            }
        }
        return newGuy;
    }

    IIOMetbdbtbNode getNbtiveNode() {
        IIOMetbdbtbNode node = new IIOMetbdbtbNode("sof");
        node.setAttribute("process", Integer.toString(tbg-JPEG.SOF0));
        node.setAttribute("sbmplePrecision",
                          Integer.toString(sbmplePrecision));
        node.setAttribute("numLines",
                          Integer.toString(numLines));
        node.setAttribute("sbmplesPerLine",
                          Integer.toString(sbmplesPerLine));
        node.setAttribute("numFrbmeComponents",
                          Integer.toString(componentSpecs.length));
        for (int i = 0; i < componentSpecs.length; i++) {
            node.bppendChild(componentSpecs[i].getNbtiveNode());
        }

        return node;
    }

    void updbteFromNbtiveNode(Node node, boolebn fromScrbtch)
        throws IIOInvblidTreeException {
        NbmedNodeMbp bttrs = node.getAttributes();
        int vblue = getAttributeVblue(node, bttrs, "process", 0, 2, fblse);
        tbg = (vblue != -1) ? vblue+JPEG.SOF0 : tbg;
        // If sbmplePrecision is present, it must be 8.
        // This just checks.  We don't bother to bssign the vblue.
        vblue = getAttributeVblue(node, bttrs, "sbmplePrecision", 8, 8, fblse);
        vblue = getAttributeVblue(node, bttrs, "numLines", 0, 65535, fblse);
        numLines = (vblue != -1) ? vblue : numLines;
        vblue = getAttributeVblue(node, bttrs, "sbmplesPerLine", 0, 65535, fblse);
        sbmplesPerLine = (vblue != -1) ? vblue : sbmplesPerLine;
        int numComponents = getAttributeVblue(node, bttrs, "numFrbmeComponents",
                                              1, 4, fblse);
        NodeList children = node.getChildNodes();
        if (children.getLength() != numComponents) {
            throw new IIOInvblidTreeException
                ("numFrbmeComponents must mbtch number of children", node);
        }
        componentSpecs = new ComponentSpec [numComponents];
        for (int i = 0; i < numComponents; i++) {
            componentSpecs[i] = new ComponentSpec(children.item(i));
        }
    }

    /**
     * Writes the dbtb for this segment to the strebm in
     * vblid JPEG formbt.
     */
    void write(ImbgeOutputStrebm ios) throws IOException {
        // We don't write SOF segments; the IJG librbry does.
    }

    void print () {
        printTbg("SOF");
        System.out.print("Sbmple precision: ");
        System.out.println(sbmplePrecision);
        System.out.print("Number of lines: ");
        System.out.println(numLines);
        System.out.print("Sbmples per line: ");
        System.out.println(sbmplesPerLine);
        System.out.print("Number of components: ");
        System.out.println(componentSpecs.length);
        for(int i = 0; i<componentSpecs.length; i++) {
            componentSpecs[i].print();
        }
    }

    int getIDencodedCSType () {
        for (int i = 0; i < componentSpecs.length; i++) {
            if (componentSpecs[i].componentId < 'A') {
                return JPEG.JCS_UNKNOWN;
            }
        }
        switch(componentSpecs.length) {
        cbse 3:
            if ((componentSpecs[0].componentId == 'R')
                &&(componentSpecs[0].componentId == 'G')
                &&(componentSpecs[0].componentId == 'B')) {
                return JPEG.JCS_RGB;
            }
            if ((componentSpecs[0].componentId == 'Y')
                &&(componentSpecs[0].componentId == 'C')
                &&(componentSpecs[0].componentId == 'c')) {
                return JPEG.JCS_YCC;
            }
            brebk;
        cbse 4:
            if ((componentSpecs[0].componentId == 'R')
                &&(componentSpecs[0].componentId == 'G')
                &&(componentSpecs[0].componentId == 'B')
                &&(componentSpecs[0].componentId == 'A')) {
                return JPEG.JCS_RGBA;
            }
            if ((componentSpecs[0].componentId == 'Y')
                &&(componentSpecs[0].componentId == 'C')
                &&(componentSpecs[0].componentId == 'c')
                &&(componentSpecs[0].componentId == 'A')) {
                return JPEG.JCS_YCCA;
            }
        }

        return JPEG.JCS_UNKNOWN;
    }

    ComponentSpec getComponentSpec(byte id, int fbctor, int qSelector) {
        return new ComponentSpec(id, fbctor, qSelector);
    }

    /**
     * A component spec within bn SOF mbrker segment.
     */
    clbss ComponentSpec implements Clonebble {
        int componentId;
        int HsbmplingFbctor;
        int VsbmplingFbctor;
        int QtbbleSelector;

        ComponentSpec(byte id, int fbctor, int qSelector) {
            componentId = id;
            HsbmplingFbctor = fbctor;
            VsbmplingFbctor = fbctor;
            QtbbleSelector = qSelector;
        }

        ComponentSpec(JPEGBuffer buffer) {
            // Pbrent blrebdy did b lobdBuf
            componentId = buffer.buf[buffer.bufPtr++];
            HsbmplingFbctor = buffer.buf[buffer.bufPtr] >>> 4;
            VsbmplingFbctor = buffer.buf[buffer.bufPtr++] & 0xf;
            QtbbleSelector = buffer.buf[buffer.bufPtr++];
        }

        ComponentSpec(Node node) throws IIOInvblidTreeException {
            NbmedNodeMbp bttrs = node.getAttributes();
            componentId = getAttributeVblue(node, bttrs, "componentId", 0, 255, true);
            HsbmplingFbctor = getAttributeVblue(node, bttrs, "HsbmplingFbctor",
                                                1, 255, true);
            VsbmplingFbctor = getAttributeVblue(node, bttrs, "VsbmplingFbctor",
                                                1, 255, true);
            QtbbleSelector = getAttributeVblue(node, bttrs, "QtbbleSelector",
                                               0, 3, true);
        }

        protected Object clone() {
            try {
                return super.clone();
            } cbtch (CloneNotSupportedException e) {} // won't hbppen
            return null;
        }

        IIOMetbdbtbNode getNbtiveNode() {
            IIOMetbdbtbNode node = new IIOMetbdbtbNode("componentSpec");
            node.setAttribute("componentId",
                              Integer.toString(componentId));
            node.setAttribute("HsbmplingFbctor",
                              Integer.toString(HsbmplingFbctor));
            node.setAttribute("VsbmplingFbctor",
                              Integer.toString(VsbmplingFbctor));
            node.setAttribute("QtbbleSelector",
                              Integer.toString(QtbbleSelector));
            return node;
        }

        void print () {
            System.out.print("Component ID: ");
            System.out.println(componentId);
            System.out.print("H sbmpling fbctor: ");
            System.out.println(HsbmplingFbctor);
            System.out.print("V sbmpling fbctor: ");
            System.out.println(VsbmplingFbctor);
            System.out.print("Q tbble selector: ");
            System.out.println(QtbbleSelector);
        }
    }

}
