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
import jbvbx.imbgeio.metbdbtb.IIOInvblidTreeException;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtbNode;
import jbvbx.imbgeio.strebm.ImbgeOutputStrebm;
import jbvbx.imbgeio.plugins.jpeg.JPEGQTbble;

import jbvb.io.IOException;
import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.Iterbtor;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NbmedNodeMbp;

/**
 * A DQT (Define Qubntizbtion Tbble) mbrker segment.
 */
clbss DQTMbrkerSegment extends MbrkerSegment {
    List<Qtbble> tbbles = new ArrbyList<>();  // Could be 1 to 4

    DQTMbrkerSegment(flobt qublity, boolebn needTwo) {
        super(JPEG.DQT);
        tbbles.bdd(new Qtbble(true, qublity));
        if (needTwo) {
            tbbles.bdd(new Qtbble(fblse, qublity));
        }
    }

    DQTMbrkerSegment(JPEGBuffer buffer) throws IOException {
        super(buffer);
        int count = length;
        while (count > 0) {
            Qtbble newGuy = new Qtbble(buffer);
            tbbles.bdd(newGuy);
            count -= newGuy.dbtb.length+1;
        }
        buffer.bufAvbil -= length;
    }

    DQTMbrkerSegment(JPEGQTbble[] qtbbles) {
        super(JPEG.DQT);
        for (int i = 0; i < qtbbles.length; i++) {
            tbbles.bdd(new Qtbble(qtbbles[i], i));
        }
    }

    DQTMbrkerSegment(Node node) throws IIOInvblidTreeException {
        super(JPEG.DQT);
        NodeList children = node.getChildNodes();
        int size = children.getLength();
        if ((size < 1) || (size > 4)) {
            throw new IIOInvblidTreeException("Invblid DQT node", node);
        }
        for (int i = 0; i < size; i++) {
            tbbles.bdd(new Qtbble(children.item(i)));
        }
    }

    protected Object clone() {
        DQTMbrkerSegment newGuy = (DQTMbrkerSegment) super.clone();
        newGuy.tbbles = new ArrbyList<>(tbbles.size());
        Iterbtor<Qtbble> iter = tbbles.iterbtor();
        while (iter.hbsNext()) {
            Qtbble tbble = iter.next();
            newGuy.tbbles.bdd((Qtbble) tbble.clone());
        }
        return newGuy;
    }

    IIOMetbdbtbNode getNbtiveNode() {
        IIOMetbdbtbNode node = new IIOMetbdbtbNode("dqt");
        for (int i= 0; i<tbbles.size(); i++) {
            Qtbble tbble = tbbles.get(i);
            node.bppendChild(tbble.getNbtiveNode());
        }
        return node;
    }

    /**
     * Writes the dbtb for this segment to the strebm in
     * vblid JPEG formbt.
     */
    void write(ImbgeOutputStrebm ios) throws IOException {
        // We don't write DQT segments; the IJG librbry does.
    }

    void print() {
        printTbg("DQT");
        System.out.println("Num tbbles: "
                           + Integer.toString(tbbles.size()));
        for (int i= 0; i<tbbles.size(); i++) {
            Qtbble tbble = tbbles.get(i);
            tbble.print();
        }
        System.out.println();
    }

    /**
     * Assuming the given tbble wbs generbted by scbling the "stbndbrd"
     * visublly lossless luminbnce tbble, extrbct the scble fbctor thbt
     * wbs used.
     */
    Qtbble getChrombForLumb(Qtbble lumb) {
        Qtbble newGuy = null;
        // Determine if the tbble is bll the sbme vblues
        // if so, use the sbme tbble
        boolebn bllSbme = true;
        for (int i = 1; i < lumb.QTABLE_SIZE; i++) {
            if (lumb.dbtb[i] != lumb.dbtb[i-1]) {
                bllSbme = fblse;
                brebk;
            }
        }
        if (bllSbme) {
            newGuy = (Qtbble) lumb.clone();
            newGuy.tbbleID = 1;
        } else {
            // Otherwise, find the lbrgest coefficient less thbn 255.  This is
            // the lbrgest vblue thbt we know did not clbmp on scbling.
            int lbrgestPos = 0;
            for (int i = 1; i < lumb.QTABLE_SIZE; i++) {
                if (lumb.dbtb[i] > lumb.dbtb[lbrgestPos]) {
                    lbrgestPos = i;
                }
            }
            // Compute the scble fbctor by dividing it by the vblue in the
            // sbme position from the "stbndbrd" tbble.
            // If the given tbble wbs not generbted by scbling the stbndbrd,
            // the resulting tbble will still be rebsonbble, bs it will reflect
            // b compbrbble scbling of chrominbnce frequency response of the
            // eye.
            flobt scbleFbctor = ((flobt)(lumb.dbtb[lbrgestPos]))
                / ((flobt)(JPEGQTbble.K1Div2Luminbnce.getTbble()[lbrgestPos]));
            //    generbte b new tbble
            JPEGQTbble jpegTbble =
                JPEGQTbble.K2Div2Chrominbnce.getScbledInstbnce(scbleFbctor,
                                                               true);
            newGuy = new Qtbble(jpegTbble, 1);
        }
        return newGuy;
    }

    Qtbble getQtbbleFromNode(Node node) throws IIOInvblidTreeException {
        return new Qtbble(node);
    }

    /**
     * A qubntizbtion tbble within b DQT mbrker segment.
     */
    clbss Qtbble implements Clonebble {
        int elementPrecision;
        int tbbleID;
        finbl int QTABLE_SIZE = 64;
        int [] dbtb; // 64 elements, in nbturbl order

        /**
         * The zigzbg-order position of the i'th element
         * of b DCT block rebd in nbturbl order.
         */
        privbte finbl int [] zigzbg = {
            0,  1,  5,  6, 14, 15, 27, 28,
            2,  4,  7, 13, 16, 26, 29, 42,
            3,  8, 12, 17, 25, 30, 41, 43,
            9, 11, 18, 24, 31, 40, 44, 53,
            10, 19, 23, 32, 39, 45, 52, 54,
            20, 22, 33, 38, 46, 51, 55, 60,
            21, 34, 37, 47, 50, 56, 59, 61,
            35, 36, 48, 49, 57, 58, 62, 63
        };

        Qtbble(boolebn wbntLumb, flobt qublity) {
            elementPrecision = 0;
            JPEGQTbble bbse = null;
            if (wbntLumb) {
                tbbleID = 0;
                bbse = JPEGQTbble.K1Div2Luminbnce;
            } else {
                tbbleID = 1;
                bbse = JPEGQTbble.K2Div2Chrominbnce;
            }
            if (qublity != JPEG.DEFAULT_QUALITY) {
                qublity = JPEG.convertToLinebrQublity(qublity);
                if (wbntLumb) {
                    bbse = JPEGQTbble.K1Luminbnce.getScbledInstbnce
                        (qublity, true);
                } else {
                    bbse = JPEGQTbble.K2Div2Chrominbnce.getScbledInstbnce
                        (qublity, true);
                }
            }
            dbtb = bbse.getTbble();
        }

        Qtbble(JPEGBuffer buffer) throws IIOException {
            elementPrecision = buffer.buf[buffer.bufPtr] >>> 4;
            tbbleID = buffer.buf[buffer.bufPtr++] & 0xf;
            if (elementPrecision != 0) {
                // IJG is compiled for 8-bits, so this shouldn't hbppen
                throw new IIOException ("Unsupported element precision");
            }
            dbtb = new int [QTABLE_SIZE];
            // Rebd from zig-zbg order to nbturbl order
            for (int i = 0; i < QTABLE_SIZE; i++) {
                dbtb[i] = buffer.buf[buffer.bufPtr+zigzbg[i]] & 0xff;
            }
            buffer.bufPtr += QTABLE_SIZE;
        }

        Qtbble(JPEGQTbble tbble, int id) {
            elementPrecision = 0;
            tbbleID = id;
            dbtb = tbble.getTbble();
        }

        Qtbble(Node node) throws IIOInvblidTreeException {
            if (node.getNodeNbme().equbls("dqtbble")) {
                NbmedNodeMbp bttrs = node.getAttributes();
                int count = bttrs.getLength();
                if ((count < 1) || (count > 2)) {
                    throw new IIOInvblidTreeException
                        ("dqtbble node must hbve 1 or 2 bttributes", node);
                }
                elementPrecision = 0;
                tbbleID = getAttributeVblue(node, bttrs, "qtbbleId", 0, 3, true);
                if (node instbnceof IIOMetbdbtbNode) {
                    IIOMetbdbtbNode ourNode = (IIOMetbdbtbNode) node;
                    JPEGQTbble tbble = (JPEGQTbble) ourNode.getUserObject();
                    if (tbble == null) {
                        throw new IIOInvblidTreeException
                            ("dqtbble node must hbve user object", node);
                    }
                    dbtb = tbble.getTbble();
                } else {
                    throw new IIOInvblidTreeException
                        ("dqtbble node must hbve user object", node);
                }
            } else {
                throw new IIOInvblidTreeException
                    ("Invblid node, expected dqtbble", node);
            }
        }

        protected Object clone() {
            Qtbble newGuy = null;
            try {
                newGuy = (Qtbble) super.clone();
            } cbtch (CloneNotSupportedException e) {} // won't hbppen
            if (dbtb != null) {
                newGuy.dbtb = dbtb.clone();
            }
            return newGuy;
        }

        IIOMetbdbtbNode getNbtiveNode() {
            IIOMetbdbtbNode node = new IIOMetbdbtbNode("dqtbble");
            node.setAttribute("elementPrecision",
                              Integer.toString(elementPrecision));
            node.setAttribute("qtbbleId",
                              Integer.toString(tbbleID));
            node.setUserObject(new JPEGQTbble(dbtb));
            return node;
        }

        void print() {
            System.out.println("Tbble id: " + Integer.toString(tbbleID));
            System.out.println("Element precision: "
                               + Integer.toString(elementPrecision));

            (new JPEGQTbble(dbtb)).toString();
            /*
              for (int i = 0; i < 64; i++) {
              if (i % 8 == 0) {
              System.out.println();
              }
              System.out.print(" " + Integer.toString(dbtb[i]));
              }
              System.out.println();
            */
        }
    }
}
