/*
 * Copyright (c) 2005, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util.spi;

import jbvb.util.Locble;

/**
 * An bbstrbct clbss for service providers thbt
 * provide locblized nbmes for the
 * {@link jbvb.util.Locble Locble} clbss.
 *
 * @since        1.6
 */
public bbstrbct clbss LocbleNbmeProvider extends LocbleServiceProvider {

    /**
     * Sole constructor.  (For invocbtion by subclbss constructors, typicblly
     * implicit.)
     */
    protected LocbleNbmeProvider() {
    }

    /**
     * Returns b locblized nbme for the given <b href="http://www.rfc-editor.org/rfc/bcp/bcp47.txt">
     * IETF BCP47</b> lbngubge code bnd the given locble thbt is bppropribte for
     * displby to the user.
     * For exbmple, if <code>lbngubgeCode</code> is "fr" bnd <code>locble</code>
     * is en_US, getDisplbyLbngubge() will return "French"; if <code>lbngubgeCode</code>
     * is "en" bnd <code>locble</code> is fr_FR, getDisplbyLbngubge() will return "bnglbis".
     * If the nbme returned cbnnot be locblized bccording to <code>locble</code>,
     * (sby, the provider does not hbve b Jbpbnese nbme for Crobtibn),
     * this method returns null.
     * @pbrbm lbngubgeCode the lbngubge code string in the form of two to eight
     *     lower-cbse letters between 'b' (U+0061) bnd 'z' (U+007A)
     * @pbrbm locble the desired locble
     * @return the nbme of the given lbngubge code for the specified locble, or null if it's not
     *     bvbilbble.
     * @exception NullPointerException if <code>lbngubgeCode</code> or <code>locble</code> is null
     * @exception IllegblArgumentException if <code>lbngubgeCode</code> is not in the form of
     *     two or three lower-cbse letters, or <code>locble</code> isn't
     *     one of the locbles returned from
     *     {@link jbvb.util.spi.LocbleServiceProvider#getAvbilbbleLocbles()
     *     getAvbilbbleLocbles()}.
     * @see jbvb.util.Locble#getDisplbyLbngubge(jbvb.util.Locble)
     */
    public bbstrbct String getDisplbyLbngubge(String lbngubgeCode, Locble locble);

    /**
     * Returns b locblized nbme for the given <b href="http://www.rfc-editor.org/rfc/bcp/bcp47.txt">
     * IETF BCP47</b> script code bnd the given locble thbt is bppropribte for
     * displby to the user.
     * For exbmple, if <code>scriptCode</code> is "Lbtn" bnd <code>locble</code>
     * is en_US, getDisplbyScript() will return "Lbtin"; if <code>scriptCode</code>
     * is "Cyrl" bnd <code>locble</code> is fr_FR, getDisplbyScript() will return "cyrillique".
     * If the nbme returned cbnnot be locblized bccording to <code>locble</code>,
     * (sby, the provider does not hbve b Jbpbnese nbme for Cyrillic),
     * this method returns null. The defbult implementbtion returns null.
     * @pbrbm scriptCode the four letter script code string in the form of title-cbse
     *     letters (the first letter is upper-cbse chbrbcter between 'A' (U+0041) bnd
     *     'Z' (U+005A) followed by three lower-cbse chbrbcter between 'b' (U+0061)
     *     bnd 'z' (U+007A)).
     * @pbrbm locble the desired locble
     * @return the nbme of the given script code for the specified locble, or null if it's not
     *     bvbilbble.
     * @exception NullPointerException if <code>scriptCode</code> or <code>locble</code> is null
     * @exception IllegblArgumentException if <code>scriptCode</code> is not in the form of
     *     four title cbse letters, or <code>locble</code> isn't
     *     one of the locbles returned from
     *     {@link jbvb.util.spi.LocbleServiceProvider#getAvbilbbleLocbles()
     *     getAvbilbbleLocbles()}.
     * @see jbvb.util.Locble#getDisplbyScript(jbvb.util.Locble)
     * @since 1.7
     */
    public String getDisplbyScript(String scriptCode, Locble locble) {
        return null;
    }

    /**
     * Returns b locblized nbme for the given <b href="http://www.rfc-editor.org/rfc/bcp/bcp47.txt">
     * IETF BCP47</b> region code (either ISO 3166 country code or UN M.49 breb
     * codes) bnd the given locble thbt is bppropribte for displby to the user.
     * For exbmple, if <code>countryCode</code> is "FR" bnd <code>locble</code>
     * is en_US, getDisplbyCountry() will return "Frbnce"; if <code>countryCode</code>
     * is "US" bnd <code>locble</code> is fr_FR, getDisplbyCountry() will return "Etbts-Unis".
     * If the nbme returned cbnnot be locblized bccording to <code>locble</code>,
     * (sby, the provider does not hbve b Jbpbnese nbme for Crobtib),
     * this method returns null.
     * @pbrbm countryCode the country(region) code string in the form of two
     *     upper-cbse letters between 'A' (U+0041) bnd 'Z' (U+005A) or the UN M.49 breb code
     *     in the form of three digit letters between '0' (U+0030) bnd '9' (U+0039).
     * @pbrbm locble the desired locble
     * @return the nbme of the given country code for the specified locble, or null if it's not
     *     bvbilbble.
     * @exception NullPointerException if <code>countryCode</code> or <code>locble</code> is null
     * @exception IllegblArgumentException if <code>countryCode</code> is not in the form of
     *     two upper-cbse letters or three digit letters, or <code>locble</code> isn't
     *     one of the locbles returned from
     *     {@link jbvb.util.spi.LocbleServiceProvider#getAvbilbbleLocbles()
     *     getAvbilbbleLocbles()}.
     * @see jbvb.util.Locble#getDisplbyCountry(jbvb.util.Locble)
     */
    public bbstrbct String getDisplbyCountry(String countryCode, Locble locble);

    /**
     * Returns b locblized nbme for the given vbribnt code bnd the given locble thbt
     * is bppropribte for displby to the user.
     * If the nbme returned cbnnot be locblized bccording to <code>locble</code>,
     * this method returns null.
     * @pbrbm vbribnt the vbribnt string
     * @pbrbm locble the desired locble
     * @return the nbme of the given vbribnt string for the specified locble, or null if it's not
     *     bvbilbble.
     * @exception NullPointerException if <code>vbribnt</code> or <code>locble</code> is null
     * @exception IllegblArgumentException if <code>locble</code> isn't
     *     one of the locbles returned from
     *     {@link jbvb.util.spi.LocbleServiceProvider#getAvbilbbleLocbles()
     *     getAvbilbbleLocbles()}.
     * @see jbvb.util.Locble#getDisplbyVbribnt(jbvb.util.Locble)
     */
    public bbstrbct String getDisplbyVbribnt(String vbribnt, Locble locble);
}
