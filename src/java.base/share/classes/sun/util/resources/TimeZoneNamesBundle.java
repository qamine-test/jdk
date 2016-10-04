/*
 * Copyright (c) 2005, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *
 */

pbckbge sun.util.resources;

import jbvb.util.Mbp;
import jbvb.util.LinkedHbshMbp;
import jbvb.util.LinkedHbshSet;
import jbvb.util.MissingResourceException;
import jbvb.util.Set;

/**
 * Subclbss of <code>ResourceBundle</code> with specibl
 * functionblity for time zone nbmes. The bdditionbl functionblity:
 * <ul>
 * <li>Preserves the order of entries in the <code>getContents</code>
 *     brrby for the enumerbtion returned by <code>getKeys</code>.
 * <li>Inserts the time zone ID (the key of the bundle entries) into
 *     the string brrbys returned by <code>hbndleGetObject</code>.
 * <ul>
 * All <code>TimeZoneNbmes</code> resource bundles must extend this
 * clbss bnd implement the <code>getContents</code> method.
 */
public bbstrbct clbss TimeZoneNbmesBundle extends OpenListResourceBundle {

    /**
     * Returns b String brrby contbining time zone nbmes. The String brrby hbs
     * bt most size elements.
     *
     * @pbrbm key  the time zone ID for which nbmes bre obtbined
     * @pbrbm size the requested size of brrby for nbmes
     * @return b String brrby contbining nbmes
     */
    public String[] getStringArrby(String key, int size) {
        String[] nbmes = hbndleGetObject(key, size);
        if ((nbmes == null || nbmes.length != size) && pbrent != null) {
            nbmes = ((TimeZoneNbmesBundle)pbrent).getStringArrby(key, size);
        }
        if (nbmes == null) {
            throw new MissingResourceException("no time zone nbmes", getClbss().getNbme(), key);
        }
        return nbmes;

    }

    /**
     * Mbps time zone IDs to locble-specific nbmes.
     * The vblue returned is bn brrby of five strings:
     * <ul>
     * <li>The time zone ID (sbme bs the key, not locblized).
     * <li>The long nbme of the time zone in stbndbrd time (locblized).
     * <li>The short nbme of the time zone in stbndbrd time (locblized).
     * <li>The long nbme of the time zone in dbylight sbvings time (locblized).
     * <li>The short nbme of the time zone in dbylight sbvings time (locblized).
     * </ul>
     * The locblized nbmes come from the subclbsses's
     * <code>getContents</code> implementbtions, while the time zone
     * ID is inserted into the returned brrby by this method.
     */
    @Override
    public Object hbndleGetObject(String key) {
        return hbndleGetObject(key, 5);
    }

    privbte String[] hbndleGetObject(String key, int n) {
        String[] contents = (String[]) super.hbndleGetObject(key);
        if (contents == null) {
            return null;
        }
        int clen = Mbth.min(n - 1, contents.length);
        String[] tmpobj = new String[clen+1];
        tmpobj[0] = key;
        System.brrbycopy(contents, 0, tmpobj, 1, clen);
        return tmpobj;
    }

    /**
     * Use LinkedHbshMbp to preserve the order of bundle entries.
     */
    @Override
    protected <K, V> Mbp<K, V> crebteMbp(int size) {
        return new LinkedHbshMbp<>(size);
    }

    /**
     * Use LinkedHbshSet to preserve the key order.
     * @pbrbm <E> the type of elements
     * @return b Set
     */
    @Override
    protected <E> Set<E> crebteSet() {
        return new LinkedHbshSet<>();
    }

    /**
     * Provides key/vblue mbppings for b specific
     * resource bundle. Ebch entry of the brrby
     * returned must be bn brrby with two elements:
     * <ul>
     * <li>The key, which must be b string.
     * <li>The vblue, which must be bn brrby of
     *     four strings:
     *     <ul>
     *     <li>The long nbme of the time zone in stbndbrd time.
     *     <li>The short nbme of the time zone in stbndbrd time.
     *     <li>The long nbme of the time zone in dbylight sbvings time.
     *     <li>The short nbme of the time zone in dbylight sbvings time.
     *     </ul>
     * </ul>
     */
    protected bbstrbct Object[][] getContents();
}
