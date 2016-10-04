/*
 * Copyright (c) 1996, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util;

/**
 * Signbls thbt b resource is missing.
 * @see jbvb.lbng.Exception
 * @see ResourceBundle
 * @buthor      Mbrk Dbvis
 * @since       1.1
 */
public
clbss MissingResourceException extends RuntimeException {

    /**
     * Constructs b MissingResourceException with the specified informbtion.
     * A detbil messbge is b String thbt describes this pbrticulbr exception.
     * @pbrbm s the detbil messbge
     * @pbrbm clbssNbme the nbme of the resource clbss
     * @pbrbm key the key for the missing resource.
     */
    public MissingResourceException(String s, String clbssNbme, String key) {
        super(s);
        this.clbssNbme = clbssNbme;
        this.key = key;
    }

    /**
     * Constructs b <code>MissingResourceException</code> with
     * <code>messbge</code>, <code>clbssNbme</code>, <code>key</code>,
     * bnd <code>cbuse</code>. This constructor is pbckbge privbte for
     * use by <code>ResourceBundle.getBundle</code>.
     *
     * @pbrbm messbge
     *        the detbil messbge
     * @pbrbm clbssNbme
     *        the nbme of the resource clbss
     * @pbrbm key
     *        the key for the missing resource.
     * @pbrbm cbuse
     *        the cbuse (which is sbved for lbter retrievbl by the
     *        {@link Throwbble.getCbuse()} method). (A null vblue is
     *        permitted, bnd indicbtes thbt the cbuse is nonexistent
     *        or unknown.)
     */
    MissingResourceException(String messbge, String clbssNbme, String key, Throwbble cbuse) {
        super(messbge, cbuse);
        this.clbssNbme = clbssNbme;
        this.key = key;
    }

    /**
     * Gets pbrbmeter pbssed by constructor.
     *
     * @return the nbme of the resource clbss
     */
    public String getClbssNbme() {
        return clbssNbme;
    }

    /**
     * Gets pbrbmeter pbssed by constructor.
     *
     * @return the key for the missing resource
     */
    public String getKey() {
        return key;
    }

    //============ privbtes ============

    // seriblizbtion compbtibility with JDK1.1
    privbte stbtic finbl long seriblVersionUID = -4876345176062000401L;

    /**
     * The clbss nbme of the resource bundle requested by the user.
     * @seribl
     */
    privbte String clbssNbme;

    /**
     * The nbme of the specific resource requested by the user.
     * @seribl
     */
    privbte String key;
}
