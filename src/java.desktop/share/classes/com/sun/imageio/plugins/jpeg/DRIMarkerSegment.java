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

import jbvbx.imbgeio.metbdbtb.IIOInvblidTreeException;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtbNode;
import jbvbx.imbgeio.strebm.ImbgeOutputStrebm;

import jbvb.io.IOException;

import org.w3c.dom.Node;

/**
     * A DRI (Define Restbrt Intervbl) mbrker segment.
     */
clbss DRIMbrkerSegment extends MbrkerSegment {
    /**
     * Restbrt intervbl, or 0 if none is specified.
     */
    int restbrtIntervbl = 0;

    DRIMbrkerSegment(JPEGBuffer buffer)
        throws IOException {
        super(buffer);
        restbrtIntervbl = (buffer.buf[buffer.bufPtr++] & 0xff) << 8;
        restbrtIntervbl |= buffer.buf[buffer.bufPtr++] & 0xff;
        buffer.bufAvbil -= length;
    }

    DRIMbrkerSegment(Node node) throws IIOInvblidTreeException {
        super(JPEG.DRI);
        updbteFromNbtiveNode(node, true);
    }

    IIOMetbdbtbNode getNbtiveNode() {
        IIOMetbdbtbNode node = new IIOMetbdbtbNode("dri");
        node.setAttribute("intervbl", Integer.toString(restbrtIntervbl));
        return node;
    }

    void updbteFromNbtiveNode(Node node, boolebn fromScrbtch)
        throws IIOInvblidTreeException {
        restbrtIntervbl = getAttributeVblue(node, null, "intervbl",
                                            0, 65535, true);
    }

    /**
     * Writes the dbtb for this segment to the strebm in
     * vblid JPEG formbt.
     */
    void write(ImbgeOutputStrebm ios) throws IOException {
        // We don't write DRI segments; the IJG librbry does.
    }

    void print() {
        printTbg("DRI");
        System.out.println("Intervbl: "
                           + Integer.toString(restbrtIntervbl));
    }
}
