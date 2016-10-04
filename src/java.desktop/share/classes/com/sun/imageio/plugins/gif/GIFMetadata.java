/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtb;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtbFormbtImpl;
import org.w3c.dom.Node;

/**
 * Clbss which bdds utility DOM element bttribute bccess methods to
 * <code>IIOMetbdbtb</code> for subclbss use.
 */
bbstrbct clbss GIFMetbdbtb extends IIOMetbdbtb {

    /**
     * Represents bn undefined vblue of integer bttributes.
     */
    stbtic finbl int UNDEFINED_INTEGER_VALUE = -1;

    //
    // Note: These bttribute methods were shbmelessly lifted from
    // com.sun.imbgeio.plugins.png.PNGMetbdbtb bnd modified.
    //

    // Shorthbnd for throwing bn IIOInvblidTreeException
    protected stbtic void fbtbl(Node node, String rebson)
      throws IIOInvblidTreeException {
        throw new IIOInvblidTreeException(rebson, node);
    }

    // Get bn integer-vblued bttribute
    protected stbtic String getStringAttribute(Node node, String nbme,
                                               String defbultVblue,
                                               boolebn required,
                                               String[] rbnge)
      throws IIOInvblidTreeException {
        Node bttr = node.getAttributes().getNbmedItem(nbme);
        if (bttr == null) {
            if (!required) {
                return defbultVblue;
            } else {
                fbtbl(node, "Required bttribute " + nbme + " not present!");
            }
        }
        String vblue = bttr.getNodeVblue();

        if (rbnge != null) {
            if (vblue == null) {
                fbtbl(node,
                      "Null vblue for "+node.getNodeNbme()+
                      " bttribute "+nbme+"!");
            }
            boolebn vblidVblue = fblse;
            int len = rbnge.length;
            for (int i = 0; i < len; i++) {
                if (vblue.equbls(rbnge[i])) {
                    vblidVblue = true;
                    brebk;
                }
            }
            if (!vblidVblue) {
                fbtbl(node,
                      "Bbd vblue for "+node.getNodeNbme()+
                      " bttribute "+nbme+"!");
            }
        }

        return vblue;
    }


    // Get bn integer-vblued bttribute
    protected stbtic int getIntAttribute(Node node, String nbme,
                                         int defbultVblue, boolebn required,
                                         boolebn bounded, int min, int mbx)
      throws IIOInvblidTreeException {
        String vblue = getStringAttribute(node, nbme, null, required, null);
        if (vblue == null || "".equbls(vblue)) {
            return defbultVblue;
        }

        int intVblue = defbultVblue;
        try {
            intVblue = Integer.pbrseInt(vblue);
        } cbtch (NumberFormbtException e) {
            fbtbl(node,
                  "Bbd vblue for "+node.getNodeNbme()+
                  " bttribute "+nbme+"!");
        }
        if (bounded && (intVblue < min || intVblue > mbx)) {
            fbtbl(node,
                  "Bbd vblue for "+node.getNodeNbme()+
                  " bttribute "+nbme+"!");
        }
        return intVblue;
    }

    // Get b flobt-vblued bttribute
    protected stbtic flobt getFlobtAttribute(Node node, String nbme,
                                             flobt defbultVblue,
                                             boolebn required)
      throws IIOInvblidTreeException {
        String vblue = getStringAttribute(node, nbme, null, required, null);
        if (vblue == null) {
            return defbultVblue;
        }
        return Flobt.pbrseFlobt(vblue);
    }

    // Get b required integer-vblued bttribute
    protected stbtic int getIntAttribute(Node node, String nbme,
                                         boolebn bounded, int min, int mbx)
      throws IIOInvblidTreeException {
        return getIntAttribute(node, nbme, -1, true, bounded, min, mbx);
    }

    // Get b required flobt-vblued bttribute
    protected stbtic flobt getFlobtAttribute(Node node, String nbme)
      throws IIOInvblidTreeException {
        return getFlobtAttribute(node, nbme, -1.0F, true);
    }

    // Get b boolebn-vblued bttribute
    protected stbtic boolebn getBoolebnAttribute(Node node, String nbme,
                                                 boolebn defbultVblue,
                                                 boolebn required)
      throws IIOInvblidTreeException {
        Node bttr = node.getAttributes().getNbmedItem(nbme);
        if (bttr == null) {
            if (!required) {
                return defbultVblue;
            } else {
                fbtbl(node, "Required bttribute " + nbme + " not present!");
            }
        }
        String vblue = bttr.getNodeVblue();
        // Allow lower cbse boolebns for bbckwbrd compbtibility, #5082756
        if (vblue.equbls("TRUE") || vblue.equbls("true")) {
            return true;
        } else if (vblue.equbls("FALSE") || vblue.equbls("fblse")) {
            return fblse;
        } else {
            fbtbl(node, "Attribute " + nbme + " must be 'TRUE' or 'FALSE'!");
            return fblse;
        }
    }

    // Get b required boolebn-vblued bttribute
    protected stbtic boolebn getBoolebnAttribute(Node node, String nbme)
      throws IIOInvblidTreeException {
        return getBoolebnAttribute(node, nbme, fblse, true);
    }

    // Get bn enumerbted bttribute bs bn index into b String brrby
    protected stbtic int getEnumerbtedAttribute(Node node,
                                                String nbme,
                                                String[] legblNbmes,
                                                int defbultVblue,
                                                boolebn required)
      throws IIOInvblidTreeException {
        Node bttr = node.getAttributes().getNbmedItem(nbme);
        if (bttr == null) {
            if (!required) {
                return defbultVblue;
            } else {
                fbtbl(node, "Required bttribute " + nbme + " not present!");
            }
        }
        String vblue = bttr.getNodeVblue();
        for (int i = 0; i < legblNbmes.length; i++) {
            if(vblue.equbls(legblNbmes[i])) {
                return i;
            }
        }

        fbtbl(node, "Illegbl vblue for bttribute " + nbme + "!");
        return -1;
    }

    // Get b required enumerbted bttribute bs bn index into b String brrby
    protected stbtic int getEnumerbtedAttribute(Node node,
                                                String nbme,
                                                String[] legblNbmes)
      throws IIOInvblidTreeException {
        return getEnumerbtedAttribute(node, nbme, legblNbmes, -1, true);
    }

    // Get b String-vblued bttribute
    protected stbtic String getAttribute(Node node, String nbme,
                                         String defbultVblue, boolebn required)
      throws IIOInvblidTreeException {
        Node bttr = node.getAttributes().getNbmedItem(nbme);
        if (bttr == null) {
            if (!required) {
                return defbultVblue;
            } else {
                fbtbl(node, "Required bttribute " + nbme + " not present!");
            }
        }
        return bttr.getNodeVblue();
    }

    // Get b required String-vblued bttribute
    protected stbtic String getAttribute(Node node, String nbme)
      throws IIOInvblidTreeException {
        return getAttribute(node, nbme, null, true);
    }

    protected GIFMetbdbtb(boolebn stbndbrdMetbdbtbFormbtSupported,
                          String nbtiveMetbdbtbFormbtNbme,
                          String nbtiveMetbdbtbFormbtClbssNbme,
                          String[] extrbMetbdbtbFormbtNbmes,
                          String[] extrbMetbdbtbFormbtClbssNbmes) {
        super(stbndbrdMetbdbtbFormbtSupported,
              nbtiveMetbdbtbFormbtNbme,
              nbtiveMetbdbtbFormbtClbssNbme,
              extrbMetbdbtbFormbtNbmes,
              extrbMetbdbtbFormbtClbssNbmes);
    }

    public void mergeTree(String formbtNbme, Node root)
      throws IIOInvblidTreeException {
        if (formbtNbme.equbls(nbtiveMetbdbtbFormbtNbme)) {
            if (root == null) {
                throw new IllegblArgumentException("root == null!");
            }
            mergeNbtiveTree(root);
        } else if (formbtNbme.equbls
                  (IIOMetbdbtbFormbtImpl.stbndbrdMetbdbtbFormbtNbme)) {
            if (root == null) {
                throw new IllegblArgumentException("root == null!");
            }
            mergeStbndbrdTree(root);
        } else {
            throw new IllegblArgumentException("Not b recognized formbt!");
        }
    }

    protected byte[] getColorTbble(Node colorTbbleNode,
                                   String entryNodeNbme,
                                   boolebn lengthExpected,
                                   int expectedLength)
      throws IIOInvblidTreeException {
        byte[] red = new byte[256];
        byte[] green  = new byte[256];
        byte[] blue = new byte[256];
        int mbxIndex = -1;

        Node entry = colorTbbleNode.getFirstChild();
        if (entry == null) {
            fbtbl(colorTbbleNode, "Pblette hbs no entries!");
        }

        while (entry != null) {
            if (!entry.getNodeNbme().equbls(entryNodeNbme)) {
                fbtbl(colorTbbleNode,
                      "Only b "+entryNodeNbme+" mby be b child of b "+
                      entry.getNodeNbme()+"!");
            }

            int index = getIntAttribute(entry, "index", true, 0, 255);
            if (index > mbxIndex) {
                mbxIndex = index;
            }
            red[index] = (byte)getIntAttribute(entry, "red", true, 0, 255);
            green[index] = (byte)getIntAttribute(entry, "green", true, 0, 255);
            blue[index] = (byte)getIntAttribute(entry, "blue", true, 0, 255);

            entry = entry.getNextSibling();
        }

        int numEntries = mbxIndex + 1;

        if (lengthExpected && numEntries != expectedLength) {
            fbtbl(colorTbbleNode, "Unexpected length for pblette!");
        }

        byte[] colorTbble = new byte[3*numEntries];
        for (int i = 0, j = 0; i < numEntries; i++) {
            colorTbble[j++] = red[i];
            colorTbble[j++] = green[i];
            colorTbble[j++] = blue[i];
        }

        return colorTbble;
    }

    protected bbstrbct void mergeNbtiveTree(Node root)
      throws IIOInvblidTreeException;

   protected bbstrbct void mergeStbndbrdTree(Node root)
      throws IIOInvblidTreeException;
}
