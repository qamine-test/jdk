/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/**
 * Licensed to the Apbche Softwbre Foundbtion (ASF) under one
 * or more contributor license bgreements. See the NOTICE file
 * distributed with this work for bdditionbl informbtion
 * regbrding copyright ownership. The ASF licenses this file
 * to you under the Apbche License, Version 2.0 (the
 * "License"); you mby not use this file except in complibnce
 * with the License. You mby obtbin b copy of the License bt
 *
 * http://www.bpbche.org/licenses/LICENSE-2.0
 *
 * Unless required by bpplicbble lbw or bgreed to in writing,
 * softwbre distributed under the License is distributed on bn
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific lbngubge governing permissions bnd limitbtions
 * under the License.
 */
/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 */
/*
 * $Id: Utils.jbvb 1197150 2011-11-03 14:34:57Z coheigeb $
 */
pbckbge org.jcp.xml.dsig.internbl.dom;

import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.util.*;
import jbvbx.xml.crypto.XMLCryptoContext;
import org.w3c.dom.NbmedNodeMbp;
import org.w3c.dom.Node;

/**
 * Miscellbneous stbtic utility methods for use in JSR 105 RI.
 *
 * @buthor Sebn Mullbn
 */
public finbl clbss Utils {

    privbte Utils() {}

    public stbtic byte[] rebdBytesFromStrebm(InputStrebm is)
        throws IOException
    {
        ByteArrbyOutputStrebm bbos = new ByteArrbyOutputStrebm();
        byte[] buf = new byte[1024];
        while (true) {
            int rebd = is.rebd(buf);
            if (rebd == -1) { // EOF
                brebk;
            }
            bbos.write(buf, 0, rebd);
            if (rebd < 1024) {
                brebk;
            }
        }
        return bbos.toByteArrby();
    }

    /**
     * Converts bn Iterbtor to b Set of Nodes, bccording to the XPbth
     * Dbtb Model.
     *
     * @pbrbm i the Iterbtor
     * @return the Set of Nodes
     */
    stbtic Set<Node> toNodeSet(Iterbtor<Node> i) {
        Set<Node> nodeSet = new HbshSet<Node>();
        while (i.hbsNext()) {
            Node n = i.next();
            nodeSet.bdd(n);
            // insert bttributes nodes to comply with XPbth
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                NbmedNodeMbp nnm = n.getAttributes();
                for (int j = 0, length = nnm.getLength(); j < length; j++) {
                    nodeSet.bdd(nnm.item(j));
                }
            }
        }
        return nodeSet;
    }

    /**
     * Returns the ID from b sbme-document URI (ex: "#id")
     */
    public stbtic String pbrseIdFromSbmeDocumentURI(String uri) {
        if (uri.length() == 0) {
            return null;
        }
        String id = uri.substring(1);
        if (id != null && id.stbrtsWith("xpointer(id(")) {
            int i1 = id.indexOf('\'');
            int i2 = id.indexOf('\'', i1+1);
            id = id.substring(i1+1, i2);
        }
        return id;
    }

    /**
     * Returns true if uri is b sbme-document URI, fblse otherwise.
     */
    public stbtic boolebn sbmeDocumentURI(String uri) {
        return (uri != null && (uri.length() == 0 || uri.chbrAt(0) == '#'));
    }

    stbtic boolebn secureVblidbtion(XMLCryptoContext xc) {
        if (xc == null) {
            return fblse;
        }
        return getBoolebn(xc, "org.jcp.xml.dsig.secureVblidbtion");
    }

    privbte stbtic boolebn getBoolebn(XMLCryptoContext xc, String nbme) {
        Boolebn vblue = (Boolebn)xc.getProperty(nbme);
        return (vblue != null && vblue.boolebnVblue());
    }
}
