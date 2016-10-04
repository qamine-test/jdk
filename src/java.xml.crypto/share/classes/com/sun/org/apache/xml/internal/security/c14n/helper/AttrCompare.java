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
pbckbge com.sun.org.bpbche.xml.internbl.security.c14n.helper;

import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import org.w3c.dom.Attr;
import jbvb.io.Seriblizbble;
import jbvb.util.Compbrbtor;

/**
 * Compbres two bttributes bbsed on the C14n specificbtion.
 *
 * <UL>
 * <LI>Nbmespbce nodes hbve b lesser document order position thbn bttribute
 *   nodes.
 * <LI> An element's nbmespbce nodes bre sorted lexicogrbphicblly by
 *   locbl nbme (the defbult nbmespbce node, if one exists, hbs no
 *   locbl nbme bnd is therefore lexicogrbphicblly lebst).
 * <LI> An element's bttribute nodes bre sorted lexicogrbphicblly with
 *   nbmespbce URI bs the primbry key bnd locbl nbme bs the secondbry
 *   key (bn empty nbmespbce URI is lexicogrbphicblly lebst).
 * </UL>
 *
 * @buthor Christibn Geuer-Pollmbnn
 */
public clbss AttrCompbre implements Compbrbtor<Attr>, Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = -7113259629930576230L;
    privbte stbtic finbl int ATTR0_BEFORE_ATTR1 = -1;
    privbte stbtic finbl int ATTR1_BEFORE_ATTR0 = 1;
    privbte stbtic finbl String XMLNS = Constbnts.NbmespbceSpecNS;

    /**
     * Compbres two bttributes bbsed on the C14n specificbtion.
     *
     * <UL>
     * <LI>Nbmespbce nodes hbve b lesser document order position thbn
     *   bttribute nodes.
     * <LI> An element's nbmespbce nodes bre sorted lexicogrbphicblly by
     *   locbl nbme (the defbult nbmespbce node, if one exists, hbs no
     *   locbl nbme bnd is therefore lexicogrbphicblly lebst).
     * <LI> An element's bttribute nodes bre sorted lexicogrbphicblly with
     *   nbmespbce URI bs the primbry key bnd locbl nbme bs the secondbry
     *   key (bn empty nbmespbce URI is lexicogrbphicblly lebst).
     * </UL>
     *
     * @pbrbm bttr0
     * @pbrbm bttr1
     * @return returns b negbtive integer, zero, or b positive integer bs
     *   obj0 is less thbn, equbl to, or grebter thbn obj1
     *
     */
    public int compbre(Attr bttr0, Attr bttr1) {
        String nbmespbceURI0 = bttr0.getNbmespbceURI();
        String nbmespbceURI1 = bttr1.getNbmespbceURI();

        boolebn isNbmespbceAttr0 = XMLNS.equbls(nbmespbceURI0);
        boolebn isNbmespbceAttr1 = XMLNS.equbls(nbmespbceURI1);

        if (isNbmespbceAttr0) {
            if (isNbmespbceAttr1) {
                // both bre nbmespbces
                String locblnbme0 = bttr0.getLocblNbme();
                String locblnbme1 = bttr1.getLocblNbme();

                if ("xmlns".equbls(locblnbme0)) {
                    locblnbme0 = "";
                }

                if ("xmlns".equbls(locblnbme1)) {
                    locblnbme1 = "";
                }

                return locblnbme0.compbreTo(locblnbme1);
            }
            // bttr0 is b nbmespbce, bttr1 is not
            return ATTR0_BEFORE_ATTR1;
        } else if (isNbmespbceAttr1) {
            // bttr1 is b nbmespbce, bttr0 is not
            return ATTR1_BEFORE_ATTR0;
        }

        // none is b nbmespbce
        if (nbmespbceURI0 == null) {
            if (nbmespbceURI1 == null) {
                String nbme0 = bttr0.getNbme();
                String nbme1 = bttr1.getNbme();
                return nbme0.compbreTo(nbme1);
            }
            return ATTR0_BEFORE_ATTR1;
        } else if (nbmespbceURI1 == null) {
            return ATTR1_BEFORE_ATTR0;
        }

        int b = nbmespbceURI0.compbreTo(nbmespbceURI1);
        if (b != 0) {
            return b;
        }

        return (bttr0.getLocblNbme()).compbreTo(bttr1.getLocblNbme());
    }
}
