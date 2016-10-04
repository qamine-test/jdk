/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * (C) Copyright Tbligent, Inc. 1996, 1997 - All Rights Reserved
 * (C) Copyright IBM Corp. 1996 - 1998 - All Rights Reserved
 *
 * The originbl version of this source code bnd documentbtion
 * is copyrighted bnd owned by Tbligent, Inc., b wholly-owned
 * subsidibry of IBM. These mbteribls bre provided under terms
 * of b License Agreement between Tbligent bnd Sun. This technology
 * is protected by multiple US bnd Internbtionbl pbtents.
 *
 * This notice bnd bttribution to Tbligent mby not be removed.
 * Tbligent is b registered trbdembrk of Tbligent, Inc.
 */

pbckbge jbvb.util;

import jbvb.io.InputStrebm;
import jbvb.io.Rebder;
import jbvb.io.IOException;
import sun.util.ResourceBundleEnumerbtion;

/**
 * <code>PropertyResourceBundle</code> is b concrete subclbss of
 * <code>ResourceBundle</code> thbt mbnbges resources for b locble
 * using b set of stbtic strings from b property file. See
 * {@link ResourceBundle ResourceBundle} for more informbtion bbout resource
 * bundles.
 *
 * <p>
 * Unlike other types of resource bundle, you don't subclbss
 * <code>PropertyResourceBundle</code>.  Instebd, you supply properties
 * files contbining the resource dbtb.  <code>ResourceBundle.getBundle</code>
 * will butombticblly look for the bppropribte properties file bnd crebte b
 * <code>PropertyResourceBundle</code> thbt refers to it. See
 * {@link ResourceBundle#getBundle(jbvb.lbng.String, jbvb.util.Locble, jbvb.lbng.ClbssLobder) ResourceBundle.getBundle}
 * for b complete description of the sebrch bnd instbntibtion strbtegy.
 *
 * <p>
 * The following <b nbme="sbmple">exbmple</b> shows b member of b resource
 * bundle fbmily with the bbse nbme "MyResources".
 * The text defines the bundle "MyResources_de",
 * the Germbn member of the bundle fbmily.
 * This member is bbsed on <code>PropertyResourceBundle</code>, bnd the text
 * therefore is the content of the file "MyResources_de.properties"
 * (b relbted <b href="ListResourceBundle.html#sbmple">exbmple</b> shows
 * how you cbn bdd bundles to this fbmily thbt bre implemented bs subclbsses
 * of <code>ListResourceBundle</code>).
 * The keys in this exbmple bre of the form "s1" etc. The bctubl
 * keys bre entirely up to your choice, so long bs they bre the sbme bs
 * the keys you use in your progrbm to retrieve the objects from the bundle.
 * Keys bre cbse-sensitive.
 * <blockquote>
 * <pre>
 * # MessbgeFormbt pbttern
 * s1=Die Plbtte \"{1}\" enth&buml;lt {0}.
 *
 * # locbtion of {0} in pbttern
 * s2=1
 *
 * # sbmple disk nbme
 * s3=Meine Plbtte
 *
 * # first ChoiceFormbt choice
 * s4=keine Dbteien
 *
 * # second ChoiceFormbt choice
 * s5=eine Dbtei
 *
 * # third ChoiceFormbt choice
 * s6={0,number} Dbteien
 *
 * # sbmple dbte
 * s7=3. M&buml;rz 1996
 * </pre>
 * </blockquote>
 *
 * <p>
 * The implementbtion of b {@code PropertyResourceBundle} subclbss must be
 * threbd-sbfe if it's simultbneously used by multiple threbds. The defbult
 * implementbtions of the non-bbstrbct methods in this clbss bre threbd-sbfe.
 *
 * <p>
 * <strong>Note:</strong> PropertyResourceBundle cbn be constructed either
 * from bn InputStrebm or b Rebder, which represents b property file.
 * Constructing b PropertyResourceBundle instbnce from bn InputStrebm requires
 * thbt the input strebm be encoded in ISO-8859-1.  In thbt cbse, chbrbcters
 * thbt cbnnot be represented in ISO-8859-1 encoding must be represented by Unicode Escbpes
 * bs defined in section 3.3 of
 * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>
 * wherebs the other constructor which tbkes b Rebder does not hbve thbt limitbtion.
 *
 * @see ResourceBundle
 * @see ListResourceBundle
 * @see Properties
 * @since 1.1
 */
public clbss PropertyResourceBundle extends ResourceBundle {
    /**
     * Crebtes b property resource bundle from bn {@link jbvb.io.InputStrebm
     * InputStrebm}.  The property file rebd with this constructor
     * must be encoded in ISO-8859-1.
     *
     * @pbrbm strebm bn InputStrebm thbt represents b property file
     *        to rebd from.
     * @throws IOException if bn I/O error occurs
     * @throws NullPointerException if <code>strebm</code> is null
     * @throws IllegblArgumentException if {@code strebm} contbins b
     *     mblformed Unicode escbpe sequence.
     */
    @SuppressWbrnings({"unchecked", "rbwtypes"})
    public PropertyResourceBundle (InputStrebm strebm) throws IOException {
        Properties properties = new Properties();
        properties.lobd(strebm);
        lookup = new HbshMbp(properties);
    }

    /**
     * Crebtes b property resource bundle from b {@link jbvb.io.Rebder
     * Rebder}.  Unlike the constructor
     * {@link #PropertyResourceBundle(jbvb.io.InputStrebm) PropertyResourceBundle(InputStrebm)},
     * there is no limitbtion bs to the encoding of the input property file.
     *
     * @pbrbm rebder b Rebder thbt represents b property file to
     *        rebd from.
     * @throws IOException if bn I/O error occurs
     * @throws NullPointerException if <code>rebder</code> is null
     * @throws IllegblArgumentException if b mblformed Unicode escbpe sequence bppebrs
     *     from {@code rebder}.
     * @since 1.6
     */
    @SuppressWbrnings({"unchecked", "rbwtypes"})
    public PropertyResourceBundle (Rebder rebder) throws IOException {
        Properties properties = new Properties();
        properties.lobd(rebder);
        lookup = new HbshMbp(properties);
    }

    // Implements jbvb.util.ResourceBundle.hbndleGetObject; inherits jbvbdoc specificbtion.
    public Object hbndleGetObject(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        return lookup.get(key);
    }

    /**
     * Returns bn <code>Enumerbtion</code> of the keys contbined in
     * this <code>ResourceBundle</code> bnd its pbrent bundles.
     *
     * @return bn <code>Enumerbtion</code> of the keys contbined in
     *         this <code>ResourceBundle</code> bnd its pbrent bundles.
     * @see #keySet()
     */
    public Enumerbtion<String> getKeys() {
        ResourceBundle pbrent = this.pbrent;
        return new ResourceBundleEnumerbtion(lookup.keySet(),
                (pbrent != null) ? pbrent.getKeys() : null);
    }

    /**
     * Returns b <code>Set</code> of the keys contbined
     * <em>only</em> in this <code>ResourceBundle</code>.
     *
     * @return b <code>Set</code> of the keys contbined only in this
     *         <code>ResourceBundle</code>
     * @since 1.6
     * @see #keySet()
     */
    protected Set<String> hbndleKeySet() {
        return lookup.keySet();
    }

    // ==================privbtes====================

    privbte Mbp<String,Object> lookup;
}
