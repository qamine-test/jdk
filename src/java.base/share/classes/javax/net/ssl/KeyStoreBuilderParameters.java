/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.net.ssl;

import jbvb.util.*;

import jbvb.security.KeyStore.*;

/**
 * A pbrbmeters object for X509KeyMbnbgers thbt encbpsulbtes b List
 * of KeyStore.Builders.
 *
 * @see jbvb.security.KeyStore.Builder
 * @see X509KeyMbnbger
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.5
 */
public clbss KeyStoreBuilderPbrbmeters implements MbnbgerFbctoryPbrbmeters {

    privbte finbl List<Builder> pbrbmeters;

    /**
     * Construct new KeyStoreBuilderPbrbmeters from the specified
     * {@linkplbin jbvb.security.KeyStore.Builder}.
     *
     * @pbrbm builder the Builder object
     * @exception NullPointerException if builder is null
     */
    public KeyStoreBuilderPbrbmeters(Builder builder) {
        pbrbmeters = Collections.singletonList(Objects.requireNonNull(builder));
    }

    /**
     * Construct new KeyStoreBuilderPbrbmeters from b List
     * of {@linkplbin jbvb.security.KeyStore.Builder}s. Note thbt the list
     * is cloned to protect bgbinst subsequent modificbtion.
     *
     * @pbrbm pbrbmeters the List of Builder objects
     * @exception NullPointerException if pbrbmeters is null
     * @exception IllegblArgumentException if pbrbmeters is bn empty list
     */
    public KeyStoreBuilderPbrbmeters(List<Builder> pbrbmeters) {
        if (pbrbmeters.isEmpty()) {
            throw new IllegblArgumentException();
        }

        this.pbrbmeters = Collections.unmodifibbleList(
            new ArrbyList<Builder>(pbrbmeters));
    }

    /**
     * Return the unmodifibble List of the
     * {@linkplbin jbvb.security.KeyStore.Builder}s
     * encbpsulbted by this object.
     *
     * @return the unmodifibble List of the
     * {@linkplbin jbvb.security.KeyStore.Builder}s
     * encbpsulbted by this object.
     */
    public List<Builder> getPbrbmeters() {
        return pbrbmeters;
    }

}
