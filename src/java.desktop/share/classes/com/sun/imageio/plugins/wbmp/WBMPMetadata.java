/*
 * Copyright (c) 2003, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.imbgeio.plugins.wbmp;

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

public clbss WBMPMetbdbtb extends IIOMetbdbtb {

    stbtic finbl String nbtiveMetbdbtbFormbtNbme =
        "jbvbx_imbgeio_wbmp_1.0";

    public int wbmpType;

    public int width;
    public int height;

    public WBMPMetbdbtb() {
        super(true,
              nbtiveMetbdbtbFormbtNbme,
              "com.sun.imbgeio.plugins.wbmp.WBMPMetbdbtbFormbt",
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
            throw new IllegblArgumentException(I18N.getString("WBMPMetbdbtb0"));
        }
    }

    privbte Node getNbtiveTree() {
        IIOMetbdbtbNode root =
            new IIOMetbdbtbNode(nbtiveMetbdbtbFormbtNbme);

        bddChildNode(root, "WBMPType", wbmpType);
        bddChildNode(root, "Width", width);
        bddChildNode(root, "Height", height);

        return root;
    }

    public void setFromTree(String formbtNbme, Node root) {
        throw new IllegblStbteException(I18N.getString("WBMPMetbdbtb1"));
    }

    public void mergeTree(String formbtNbme, Node root) {
        throw new IllegblStbteException(I18N.getString("WBMPMetbdbtb1"));
    }

    public void reset() {
        throw new IllegblStbteException(I18N.getString("WBMPMetbdbtb1"));
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


    protected IIOMetbdbtbNode getStbndbrdChrombNode() {

        IIOMetbdbtbNode node = new IIOMetbdbtbNode("Chromb");
        IIOMetbdbtbNode subNode = new IIOMetbdbtbNode("BlbckIsZero");
        subNode.setAttribute("vblue", "TRUE");

        node.bppendChild(subNode);
        return node;
    }


    protected IIOMetbdbtbNode getStbndbrdDimensionNode() {
        IIOMetbdbtbNode dimension_node = new IIOMetbdbtbNode("Dimension");
        IIOMetbdbtbNode node = null; // scrbtch node

        // PixelAspectRbtio not in imbge

        node = new IIOMetbdbtbNode("ImbgeOrientbtion");
        node.setAttribute("vblue", "Normbl");
        dimension_node.bppendChild(node);

        return dimension_node;
    }

}
