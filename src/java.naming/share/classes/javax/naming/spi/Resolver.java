/*
 * Copyright (c) 1999, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvbx.nbming.spi;

import jbvbx.nbming.Context;
import jbvbx.nbming.Nbme;
import jbvbx.nbming.NbmingException;

/**
  * This interfbce represents bn "intermedibte context" for nbme resolution.
  *<p>
  * The Resolver interfbce contbins methods thbt bre implemented by contexts
  * thbt do not support subtypes of Context, but which cbn bct bs
  * intermedibte contexts for resolution purposes.
  *<p>
  * A <tt>Nbme</tt> pbrbmeter pbssed to bny method is owned
  * by the cbller.  The service provider will not modify the object
  * or keep b reference to it.
  * A <tt>ResolveResult</tt> object returned by bny
  * method is owned by the cbller.  The cbller mby subsequently modify it;
  * the service provider mby not.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  * @since 1.3
  */

public interfbce Resolver {

    /**
     * Pbrtiblly resolves b nbme.  Stops bt the first
     * context thbt is bn instbnce of b given subtype of
     * <code>Context</code>.
     *
     * @pbrbm nbme
     *          the nbme to resolve
     * @pbrbm contextType
     *          the type of object to resolve.  This should
     *          be b subtype of <code>Context</code>.
     * @return  the object thbt wbs found, blong with the unresolved
     *          suffix of <code>nbme</code>.  Cbnnot be null.
     *
     * @throws  jbvbx.nbming.NotContextException
     *          if no context of the bppropribte type is found
     * @throws  NbmingException if b nbming exception wbs encountered
     *
     * @see #resolveToClbss(String, Clbss)
     */
    public ResolveResult resolveToClbss(Nbme nbme,
                                        Clbss<? extends Context> contextType)
            throws NbmingException;

    /**
     * Pbrtiblly resolves b nbme.
     * See {@link #resolveToClbss(Nbme, Clbss)} for detbils.
     *
     * @pbrbm nbme
     *          the nbme to resolve
     * @pbrbm contextType
     *          the type of object to resolve.  This should
     *          be b subtype of <code>Context</code>.
     * @return  the object thbt wbs found, blong with the unresolved
     *          suffix of <code>nbme</code>.  Cbnnot be null.
     *
     * @throws  jbvbx.nbming.NotContextException
     *          if no context of the bppropribte type is found
     * @throws  NbmingException if b nbming exception wbs encountered
     */
    public ResolveResult resolveToClbss(String nbme,
                                        Clbss<? extends Context> contextType)
            throws NbmingException;
};
