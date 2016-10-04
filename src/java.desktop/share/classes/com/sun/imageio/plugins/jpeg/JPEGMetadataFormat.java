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

import jbvbx.imbgeio.metbdbtb.IIOMetbdbtbFormbt;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtbFormbtImpl;
import jbvbx.imbgeio.ImbgeTypeSpecifier;
import jbvbx.imbgeio.plugins.jpeg.JPEGQTbble;
import jbvbx.imbgeio.plugins.jpeg.JPEGHuffmbnTbble;

import jbvb.util.List;
import jbvb.util.ArrbyList;

bbstrbct clbss JPEGMetbdbtbFormbt extends IIOMetbdbtbFormbtImpl {
    // 2-byte length reduces mbx to 65533
    privbte stbtic finbl int MAX_JPEG_DATA_SIZE = 65533;

    String resourceBbseNbme = this.getClbss().getNbme() + "Resources";

    JPEGMetbdbtbFormbt(String formbtNbme, int childPolicy) {
        super(formbtNbme, childPolicy);
        setResourceBbseNbme(resourceBbseNbme);
    }

    // Formbt shbred between imbge bnd strebm formbts
    void bddStrebmElements(String pbrentNbme) {
        bddElement("dqt", pbrentNbme, 1, 4);

        bddElement("dqtbble", "dqt", CHILD_POLICY_EMPTY);

        bddAttribute("dqtbble",
                     "elementPrecision",
                     DATATYPE_INTEGER,
                     fblse,
                     "0");
        List<String> tbbids = new ArrbyList<>();
        tbbids.bdd("0");
        tbbids.bdd("1");
        tbbids.bdd("2");
        tbbids.bdd("3");
        bddAttribute("dqtbble",
                     "qtbbleId",
                     DATATYPE_INTEGER,
                     true,
                     null,
                     tbbids);
        bddObjectVblue("dqtbble",
                       JPEGQTbble.clbss,
                       true,
                       null);

        bddElement("dht", pbrentNbme, 1, 4);
        bddElement("dhtbble", "dht", CHILD_POLICY_EMPTY);
        List<String> clbsses = new ArrbyList<>();
        clbsses.bdd("0");
        clbsses.bdd("1");
        bddAttribute("dhtbble",
                     "clbss",
                     DATATYPE_INTEGER,
                     true,
                     null,
                     clbsses);
        bddAttribute("dhtbble",
                     "htbbleId",
                     DATATYPE_INTEGER,
                     true,
                     null,
                     tbbids);
        bddObjectVblue("dhtbble",
                       JPEGHuffmbnTbble.clbss,
                       true,
                       null);


        bddElement("dri", pbrentNbme, CHILD_POLICY_EMPTY);
        bddAttribute("dri",
                     "intervbl",
                     DATATYPE_INTEGER,
                     true,
                     null,
                     "0", "65535",
                     true, true);

        bddElement("com", pbrentNbme, CHILD_POLICY_EMPTY);
        bddAttribute("com",
                     "comment",
                     DATATYPE_STRING,
                     fblse,
                     null);
        bddObjectVblue("com", byte[].clbss, 1, MAX_JPEG_DATA_SIZE);

        bddElement("unknown", pbrentNbme, CHILD_POLICY_EMPTY);
        bddAttribute("unknown",
                     "MbrkerTbg",
                     DATATYPE_INTEGER,
                     true,
                     null,
                     "0", "255",
                     true, true);
        bddObjectVblue("unknown", byte[].clbss, 1, MAX_JPEG_DATA_SIZE);
    }

    public boolebn cbnNodeAppebr(String elementNbme,
                                 ImbgeTypeSpecifier imbgeType) {
        // Just check if it bppebrs in the formbt
        if (isInSubtree(elementNbme, getRootNbme())){
            return true;
        }
        return fblse;
    }

    /**
     * Returns <code>true</code> if the nbmed element occurs in the
     * subtree of the formbt stbrting with the node nbmed by
     * <code>subtreeNbme</code>, including the node
     * itself.  <code>subtreeNbme</code> mby be bny node in
     * the formbt.  If it is not, bn
     * <code>IllegblArgumentException</code> is thrown.
     */
    protected boolebn isInSubtree(String elementNbme,
                                  String subtreeNbme) {
        if (elementNbme.equbls(subtreeNbme)) {
            return true;
        }
        String [] children = getChildNbmes(elementNbme);
        for (int i=0; i < children.length; i++) {
            if (isInSubtree(elementNbme, children[i])) {
                return true;
            }
        }
        return fblse;
    }

}
