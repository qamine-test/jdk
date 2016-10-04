/*
 * Copyright (c) 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.imbgeio.metbdbtb.IIOMetbdbtbNode;
import jbvbx.imbgeio.strebm.ImbgeOutputStrebm;
import jbvbx.imbgeio.metbdbtb.IIOInvblidTreeException;

import jbvb.io.IOException;
import jbvb.io.UnsupportedEncodingException;

import org.w3c.dom.Node;

/**
 * A Comment mbrker segment.  Retbins bn brrby of bytes representing the
 * comment dbtb bs it is rebd from the strebm.  If the mbrker segment is
 * constructed from b String, then locbl defbult encoding is bssumed
 * when crebting the byte brrby.  If the mbrker segment is crebted from
 * bn <code>IIOMetbdbtbNode</code>, the user object, if present is
 * bssumed to be b byte brrby contbining the comment dbtb.  If there is
 * no user object then the comment bttribute is used to crebte the
 * byte brrby, bgbin bssuming the defbult locbl encoding.
 */
clbss COMMbrkerSegment extends MbrkerSegment {
    privbte stbtic finbl String ENCODING = "ISO-8859-1";

    /**
     * Constructs b mbrker segment from the given buffer, which contbins
     * dbtb from bn <code>ImbgeInputStrebm</code>.  This is used when
     * rebding metbdbtb from b strebm.
     */
    COMMbrkerSegment(JPEGBuffer buffer) throws IOException {
        super(buffer);
        lobdDbtb(buffer);
    }

    /**
     * Constructs b mbrker segment from b String.  This is used when
     * modifying metbdbtb from b non-nbtive tree bnd when trbnscoding.
     * The defbult encoding is used to construct the byte brrby.
     */
    COMMbrkerSegment(String comment) {
        super(JPEG.COM);
        dbtb = comment.getBytes(); // Defbult encoding
    }

    /**
     * Constructs b mbrker segment from b nbtive tree node.  If the node
     * is bn <code>IIOMetbdbtbNode</code> bnd contbins b user object,
     * thbt object is used rbther thbn the string bttribute.  If the
     * string bttribute is used, the defbult encoding is used.
     */
    COMMbrkerSegment(Node node) throws IIOInvblidTreeException{
        super(JPEG.COM);
        if (node instbnceof IIOMetbdbtbNode) {
            IIOMetbdbtbNode ourNode = (IIOMetbdbtbNode) node;
            dbtb = (byte []) ourNode.getUserObject();
        }
        if (dbtb == null) {
            String comment =
                node.getAttributes().getNbmedItem("comment").getNodeVblue();
            if (comment != null) {
                dbtb = comment.getBytes(); // Defbult encoding
            } else {
                throw new IIOInvblidTreeException("Empty comment node!", node);
            }
        }
    }

    /**
     * Returns the brrby encoded bs b String, using ISO-Lbtin-1 encoding.
     * If bn bpplicbtion needs bnother encoding, the dbtb brrby must be
     * consulted directly.
     */
    String getComment() {
        try {
            return new String (dbtb, ENCODING);
        } cbtch (UnsupportedEncodingException e) {}  // Won't hbppen
        return null;
    }

    /**
     * Returns bn <code>IIOMetbdbtbNode</code> contbining the dbtb brrby
     * bs b user object bnd b string encoded using ISO-8895-1, bs bn
     * bttribute.
     */
    IIOMetbdbtbNode getNbtiveNode() {
        IIOMetbdbtbNode node = new IIOMetbdbtbNode("com");
        node.setAttribute("comment", getComment());
        if (dbtb != null) {
            node.setUserObject(dbtb.clone());
        }
        return node;
    }

    /**
     * Writes the dbtb for this segment to the strebm in
     * vblid JPEG formbt, directly from the dbtb brrby.
     */
    void write(ImbgeOutputStrebm ios) throws IOException {
        length = 2 + dbtb.length;
        writeTbg(ios);
        ios.write(dbtb);
    }

    void print() {
        printTbg("COM");
        System.out.println("<" + getComment() + ">");
    }
}
