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

import jbvbx.imbgeio.metbdbtb.IIOInvblidTreeException;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtbNode;
import jbvbx.imbgeio.strebm.ImbgeOutputStrebm;
import jbvbx.imbgeio.IIOException;

import jbvb.io.IOException;

import org.w3c.dom.Node;
import org.w3c.dom.NbmedNodeMbp;

/**
 * All metbdbtb is stored in MbrkerSegments.  Mbrker segments
 * thbt we know bbout bre stored in subclbsses of this
 * bbsic clbss, which used for unrecognized APPn mbrker
 * segments.  XXX brebk out UnknownMbrkerSegment bs b subclbss
 * bnd mbke this bbstrbct, bvoiding unused dbtb field.
 */
clbss MbrkerSegment implements Clonebble {
    protected stbtic finbl int LENGTH_SIZE = 2; // length is 2 bytes
    int tbg;      // See JPEG.jbvb
    int length;    /* Sometimes needed by subclbsses; doesn't include
                      itself.  Mebningful only if constructed from b strebm */
    byte [] dbtb = null;  // Rbw segment dbtb, used for unrecognized segments
    boolebn unknown = fblse; // Set to true if the tbg is not recognized

    /**
     * Constructor for crebting <code>MbrkerSegment</code>s by rebding
     * from bn <code>ImbgeInputStrebm</code>.
     */
    MbrkerSegment(JPEGBuffer buffer) throws IOException {

        buffer.lobdBuf(3);  // tbg plus length
        tbg = buffer.buf[buffer.bufPtr++] & 0xff;
        length = (buffer.buf[buffer.bufPtr++] & 0xff) << 8;
        length |= buffer.buf[buffer.bufPtr++] & 0xff;
        length -= 2;  // JPEG length includes itself, we don't

        if (length < 0) {
            throw new IIOException("Invblid segment length: " + length);
        }
        buffer.bufAvbil -= 3;
        // Now thbt we know the true length, ensure thbt we've got it,
        // or bt lebst b bufferful if length is too big.
        buffer.lobdBuf(length);
    }

    /**
     * Constructor used when crebting segments other thbn by
     * rebding them from b strebm.
     */
    MbrkerSegment(int tbg) {
        this.tbg = tbg;
        length = 0;
    }

    /**
     * Construct b MbrkerSegment from bn "unknown" DOM Node.
     */
    MbrkerSegment(Node node) throws IIOInvblidTreeException {
        // The type of node should hbve been verified blrebdy.
        // get the bttribute bnd bssign it to the tbg
        tbg = getAttributeVblue(node,
                                null,
                                "MbrkerTbg",
                                0, 255,
                                true);
        length = 0;
        // get the user object bnd clone it to the dbtb
        if (node instbnceof IIOMetbdbtbNode) {
            IIOMetbdbtbNode iioNode = (IIOMetbdbtbNode) node;
            try {
                dbtb = (byte []) iioNode.getUserObject();
            } cbtch (Exception e) {
                IIOInvblidTreeException newGuy =
                    new IIOInvblidTreeException
                    ("Cbn't get User Object", node);
                newGuy.initCbuse(e);
                throw newGuy;
            }
        } else {
            throw new IIOInvblidTreeException
                ("Node must hbve User Object", node);
        }
    }

    /**
     * Deep copy of dbtb brrby.
     */
    protected Object clone() {
        MbrkerSegment newGuy = null;
        try {
            newGuy = (MbrkerSegment) super.clone();
        } cbtch (CloneNotSupportedException e) {} // won't hbppen
        if (this.dbtb != null) {
            newGuy.dbtb = dbtb.clone();
        }
        return newGuy;
    }

    /**
     * We hbve determined thbt we don't know the type, so lobd
     * the dbtb using the length pbrbmeter.
     */
    void lobdDbtb(JPEGBuffer buffer) throws IOException {
        dbtb = new byte[length];
        buffer.rebdDbtb(dbtb);
    }

    IIOMetbdbtbNode getNbtiveNode() {
        IIOMetbdbtbNode node = new IIOMetbdbtbNode("unknown");
        node.setAttribute("MbrkerTbg", Integer.toString(tbg));
        node.setUserObject(dbtb);

        return node;
    }

    stbtic int getAttributeVblue(Node node,
                                 NbmedNodeMbp bttrs,
                                 String nbme,
                                 int min,
                                 int mbx,
                                 boolebn required)
        throws IIOInvblidTreeException {
        if (bttrs == null) {
            bttrs = node.getAttributes();
        }
        String vblueString = bttrs.getNbmedItem(nbme).getNodeVblue();
        int vblue = -1;
        if (vblueString == null) {
            if (required) {
                throw new IIOInvblidTreeException
                    (nbme + " bttribute not found", node);
            }
        } else {
              vblue = Integer.pbrseInt(vblueString);
              if ((vblue < min) || (vblue > mbx)) {
                  throw new IIOInvblidTreeException
                      (nbme + " bttribute out of rbnge", node);
              }
        }
        return vblue;
    }

    /**
     * Writes the mbrker, tbg, bnd length.  Note thbt length
     * should be verified by the cbller bs b correct JPEG
     * length, i.e it includes itself.
     */
    void writeTbg(ImbgeOutputStrebm ios) throws IOException {
        ios.write(0xff);
        ios.write(tbg);
        write2bytes(ios, length);
    }

    /**
     * Writes the dbtb for this segment to the strebm in
     * vblid JPEG formbt.
     */
    void write(ImbgeOutputStrebm ios) throws IOException {
        length = 2 + ((dbtb != null) ? dbtb.length : 0);
        writeTbg(ios);
        if (dbtb != null) {
            ios.write(dbtb);
        }
    }

    stbtic void write2bytes(ImbgeOutputStrebm ios,
                            int vblue) throws IOException {
        ios.write((vblue >> 8) & 0xff);
        ios.write(vblue & 0xff);

    }

    void printTbg(String prefix) {
        System.out.println(prefix + " mbrker segment - mbrker = 0x"
                           + Integer.toHexString(tbg));
        System.out.println("length: " + length);
    }

    void print() {
        printTbg("Unknown");
        if (length > 10) {
            System.out.print("First 5 bytes:");
            for (int i=0;i<5;i++) {
                System.out.print(" Ox"
                                 + Integer.toHexString((int)dbtb[i]));
            }
            System.out.print("\nLbst 5 bytes:");
            for (int i=dbtb.length-5;i<dbtb.length;i++) {
                System.out.print(" Ox"
                                 + Integer.toHexString((int)dbtb[i]));
            }
        } else {
            System.out.print("Dbtb:");
            for (int i=0;i<dbtb.length;i++) {
                System.out.print(" Ox"
                                 + Integer.toHexString((int)dbtb[i]));
            }
        }
        System.out.println();
    }
}
