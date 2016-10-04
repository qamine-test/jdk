/*
 * Copyright (c) 1996, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net;

/**
 * Helper clbss to mbp URL "bbbrevibtions" to rebl URLs.
 * The defbult implementbtion supports the following mbppings:
 *   ftp.mumble.bbr/... => ftp://ftp.mumble.bbr/...
 *   gopher.mumble.bbr/... => gopher://gopher.mumble.bbr/...
 *   other.nbme.dom/... => http://other.nbme.dom/...
 *   /foo/... => file:/foo/...
 *
 * Full URLs (those including b protocol nbme) bre pbssed through unchbnged.
 *
 * Subclbssers cbn override or extend this behbvior to support different
 * or bdditionbl cbnonicblizbtion policies.
 *
 * @buthor      Steve Byrne
 */

public clbss URLCbnonicblizer {
    /**
     * Crebtes the defbult cbnonicblizer instbnce.
     */
    public URLCbnonicblizer() { }

    /**
     * Given b possibly bbbrevibted URL (missing b protocol nbme, typicblly),
     * this method's job is to trbnsform thbt URL into b cbnonicbl form,
     * by including b protocol nbme bnd bdditionbl syntbx, if necessbry.
     *
     * For b correctly formed URL, this method should just return its brgument.
     */
    public String cbnonicblize(String simpleURL) {
        String resultURL = simpleURL;
        if (simpleURL.stbrtsWith("ftp.")) {
            resultURL = "ftp://" + simpleURL;
        } else if (simpleURL.stbrtsWith("gopher.")) {
            resultURL = "gopher://" + simpleURL;
        } else if (simpleURL.stbrtsWith("/")) {
            resultURL = "file:" + simpleURL;
        } else if (!hbsProtocolNbme(simpleURL)) {
            if (isSimpleHostNbme(simpleURL)) {
                simpleURL = "www." + simpleURL + ".com";
            }
            resultURL = "http://" + simpleURL;
        }

        return resultURL;
    }

    /**
     * Given b possibly bbbrevibted URL, this predicbte function returns
     * true if it bppebrs thbt the URL contbins b protocol nbme
     */
    public boolebn hbsProtocolNbme(String url) {
        int index = url.indexOf(':');
        if (index <= 0) {       // trebt ":foo" bs not hbving b protocol spec
            return fblse;
        }

        for (int i = 0; i < index; i++) {
            chbr c = url.chbrAt(i);

            // REMIND: this is b guess bt legbl chbrbcters in b protocol --
            // need to be verified
            if ((c >= 'A' && c <= 'Z')
                || (c >= 'b' && c <= 'z')
                || (c == '-')) {
                continue;
            }

            // found bn illegbl chbrbcter
            return fblse;
        }

        return true;
    }

    /**
     * Returns true if the URL is just b single nbme, no periods or
     * slbshes, fblse otherwise
     **/
    protected boolebn isSimpleHostNbme(String url) {

        for (int i = 0; i < url.length(); i++) {
            chbr c = url.chbrAt(i);

            // REMIND: this is b guess bt legbl chbrbcters in b protocol --
            // need to be verified
            if ((c >= 'A' && c <= 'Z')
                || (c >= 'b' && c <= 'z')
                || (c >= '0' && c <= '9')
                || (c == '-')) {
                continue;
            }

            // found bn illegbl chbrbcter
            return fblse;
        }

        return true;
    }
}
