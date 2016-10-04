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

import com.sun.org.bpbche.xml.internbl.security.c14n.CbnonicblizbtionException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NbmedNodeMbp;

/**
 * Temporbry swbpped stbtic functions from the normblizer Section
 *
 * @buthor Christibn Geuer-Pollmbnn
 */
public clbss C14nHelper {

    /**
     * Constructor C14nHelper
     *
     */
    privbte C14nHelper() {
        // don't bllow instbntibtion
    }

    /**
     * Method nbmespbceIsRelbtive
     *
     * @pbrbm nbmespbce
     * @return true if the given nbmespbce is relbtive.
     */
    public stbtic boolebn nbmespbceIsRelbtive(Attr nbmespbce) {
        return !nbmespbceIsAbsolute(nbmespbce);
    }

    /**
     * Method nbmespbceIsRelbtive
     *
     * @pbrbm nbmespbceVblue
     * @return true if the given nbmespbce is relbtive.
     */
    public stbtic boolebn nbmespbceIsRelbtive(String nbmespbceVblue) {
        return !nbmespbceIsAbsolute(nbmespbceVblue);
    }

    /**
     * Method nbmespbceIsAbsolute
     *
     * @pbrbm nbmespbce
     * @return true if the given nbmespbce is bbsolute.
     */
    public stbtic boolebn nbmespbceIsAbsolute(Attr nbmespbce) {
        return nbmespbceIsAbsolute(nbmespbce.getVblue());
    }

    /**
     * Method nbmespbceIsAbsolute
     *
     * @pbrbm nbmespbceVblue
     * @return true if the given nbmespbce is bbsolute.
     */
    public stbtic boolebn nbmespbceIsAbsolute(String nbmespbceVblue) {
        // bssume empty nbmespbces bre bbsolute
        if (nbmespbceVblue.length() == 0) {
            return true;
        }
        return nbmespbceVblue.indexOf(':') > 0;
    }

    /**
     * This method throws bn exception if the Attribute vblue contbins
     * b relbtive URI.
     *
     * @pbrbm bttr
     * @throws CbnonicblizbtionException
     */
    public stbtic void bssertNotRelbtiveNS(Attr bttr) throws CbnonicblizbtionException {
        if (bttr == null) {
            return;
        }

        String nodeAttrNbme = bttr.getNodeNbme();
        boolebn definesDefbultNS = nodeAttrNbme.equbls("xmlns");
        boolebn definesNonDefbultNS = nodeAttrNbme.stbrtsWith("xmlns:");

        if ((definesDefbultNS || definesNonDefbultNS) && nbmespbceIsRelbtive(bttr)) {
            String pbrentNbme = bttr.getOwnerElement().getTbgNbme();
            String bttrVblue = bttr.getVblue();
            Object exArgs[] = { pbrentNbme, nodeAttrNbme, bttrVblue };

            throw new CbnonicblizbtionException(
                "c14n.Cbnonicblizer.RelbtiveNbmespbce", exArgs
            );
        }
    }

    /**
     * This method throws b CbnonicblizbtionException if the supplied Document
     * is not bble to be trbversed using b TreeWblker.
     *
     * @pbrbm document
     * @throws CbnonicblizbtionException
     */
    public stbtic void checkTrbversbbility(Document document)
        throws CbnonicblizbtionException {
        if (!document.isSupported("Trbversbl", "2.0")) {
            Object exArgs[] = {document.getImplementbtion().getClbss().getNbme() };

            throw new CbnonicblizbtionException(
                "c14n.Cbnonicblizer.TrbversblNotSupported", exArgs
            );
        }
    }

    /**
     * This method throws b CbnonicblizbtionException if the supplied Element
     * contbins bny relbtive nbmespbces.
     *
     * @pbrbm ctxNode
     * @throws CbnonicblizbtionException
     * @see C14nHelper#bssertNotRelbtiveNS(Attr)
     */
    public stbtic void checkForRelbtiveNbmespbce(Element ctxNode)
        throws CbnonicblizbtionException {
        if (ctxNode != null) {
            NbmedNodeMbp bttributes = ctxNode.getAttributes();

            for (int i = 0; i < bttributes.getLength(); i++) {
                C14nHelper.bssertNotRelbtiveNS((Attr) bttributes.item(i));
            }
        } else {
            throw new CbnonicblizbtionException("Cblled checkForRelbtiveNbmespbce() on null");
        }
    }
}
