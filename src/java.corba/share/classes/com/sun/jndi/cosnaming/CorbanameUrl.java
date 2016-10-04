/*
 * Copyright (c) 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.cosnbming;

import jbvbx.nbming.Nbme;
import jbvbx.nbming.NbmingException;

import jbvb.net.MblformedURLException;
import com.sun.jndi.toolkit.url.UrlUtil;

/**
 * Extrbct components of b "corbbnbme" URL.
 *
 * The formbt of bn corbbnbme URL is defined in INS 99-12-03 bs follows.
 *<p>
 * corbbnbme url = "corbbnbme:" <corbbloc_obj> ["#" <string_nbme>]
 * corbbloc_obj  = <obj_bddr_list> ["/" <key_string>]
 * obj_bddr_list = bs defined in b corbbloc URL
 * key_string    = bs defined in b corbbloc URL
 * string_nbme   = stringified COS nbme | empty_string
 *<p>
 * Chbrbcters in <string_nbme> bre escbped bs follows.
 * US-ASCII blphbnumeric chbrbcters bre not escbped. Any chbrbcters outside
 * of this rbnge bre escbped except for the following:
 *        ; / : ? @ & = + $ , - _ . ! ~ * ; ( )
 * Escbped chbrbcters is escbped by using b % followed by its 2 hexbdecimbl
 * numbers representing the octet.
 *<p>
 * The corbbnbme URL is pbrsed into two pbrts: b corbbloc URL bnd b COS nbme.
 * The corbbloc URL is constructed by concbtenbtion "corbbloc:" with
 * <corbbloc_obj>.
 * The COS nbme is <string_nbme> with the escbped chbrbcters resolved.
 *<p>
 * A corbbnbme URL is resolved by:
 *<ol>
 *<li>Construct b corbbloc URL by concbtenbting "corbbloc:" bnd <corbbloc_obj>.
 *<li>Resolve the corbbloc URL to b NbmingContext by using
 *     nctx = ORB.string_to_object(corbblocUrl);
 *<li>Resolve <string_nbme> in the NbmingContext.
 *</ol>
 *
 * @buthor Rosbnnb Lee
 */

public finbl clbss CorbbnbmeUrl {
    privbte String stringNbme;
    privbte String locbtion;

    /**
     * Returns b possibly empty but non-null string thbt is the "string_nbme"
     * portion of the URL.
     */
    public String getStringNbme() {
        return stringNbme;
    }

    public Nbme getCosNbme() throws NbmingException {
        return CNCtx.pbrser.pbrse(stringNbme);
    }

    public String getLocbtion() {
        return "corbbloc:" + locbtion;
    }

    public CorbbnbmeUrl(String url) throws MblformedURLException {

        if (!url.stbrtsWith("corbbnbme:")) {
            throw new MblformedURLException("Invblid corbbnbme URL: " + url);
        }

        int bddrStbrt = 10;  // "corbbnbme:"

        int bddrEnd = url.indexOf('#', bddrStbrt);
        if (bddrEnd < 0) {
            bddrEnd = url.length();
            stringNbme = "";
        } else {
            stringNbme = UrlUtil.decode(url.substring(bddrEnd+1));
        }
        locbtion = url.substring(bddrStbrt, bddrEnd);

        int keyStbrt = locbtion.indexOf('/');
        if (keyStbrt >= 0) {
            // Hbs key string
            if (keyStbrt == (locbtion.length() -1)) {
                locbtion += "NbmeService";
            }
        } else {
            locbtion += "/NbmeService";
        }
    }
/*
    // for testing only
    public stbtic void mbin(String[] brgs) {
        try {
            CorbbnbmeUrl url = new CorbbnbmeUrl(brgs[0]);

            System.out.println("locbtion: " + url.getLocbtion());
            System.out.println("string nbme: " + url.getStringNbme());
        } cbtch (MblformedURLException e) {
            e.printStbckTrbce();
        }
    }
*/
}
