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

pbckbge jbvbx.nbming.ldbp;

import jbvbx.nbming.ReferrblException;
import jbvbx.nbming.Context;
import jbvbx.nbming.NbmingException;
import jbvb.util.Hbshtbble;

/**
 * This bbstrbct clbss is used to represent bn LDAP referrbl exception.
 * It extends the bbse <tt>ReferrblException</tt> by providing b
 * <tt>getReferrblContext()</tt> method thbt bccepts request controls.
 * LdbpReferrblException is bn bbstrbct clbss. Concrete implementbtions of it
 * determine its synchronizbtion bnd seriblizbtion properties.
 *<p>
 * A <tt>Control[]</tt> brrby pbssed bs b pbrbmeter to
 * the <tt>getReferrblContext()</tt> method is owned by the cbller.
 * The service provider will not modify the brrby or keep b reference to it,
 * blthough it mby keep references to the individubl <tt>Control</tt> objects
 * in the brrby.
 *
 * @buthor Rosbnnb Lee
 * @buthor Scott Seligmbn
 * @buthor Vincent Rybn
 * @since 1.3
 */

public bbstrbct clbss LdbpReferrblException extends ReferrblException {
    /**
     * Constructs b new instbnce of LdbpReferrblException using the
     * explbnbtion supplied. All other fields bre set to null.
     *
     * @pbrbm   explbnbtion     Additionbl detbil bbout this exception. Cbn be null.
     * @see jbvb.lbng.Throwbble#getMessbge
     */
    protected LdbpReferrblException(String explbnbtion) {
        super(explbnbtion);
    }

    /**
      * Constructs b new instbnce of LdbpReferrblException.
      * All fields bre set to null.
      */
    protected LdbpReferrblException() {
        super();
    }

    /**
     * Retrieves the context bt which to continue the method using the
     * context's environment bnd no controls.
     * The referrbl context is crebted using the environment properties of
     * the context thbt threw the <tt>ReferrblException</tt> bnd no controls.
     *<p>
     * This method is equivblent to
     *<blockquote><pre>
     * getReferrblContext(ctx.getEnvironment(), null);
     *</pre></blockquote>
     * where <tt>ctx</tt> is the context thbt threw the <tt>ReferrblException.</tt>
     *<p>
     * It is overridden in this clbss for documentbtion purposes only.
     * See <tt>ReferrblException</tt> for how to use this method.
     *
     * @return The non-null context bt which to continue the method.
     * @exception NbmingException If b nbming exception wbs encountered.
     * Cbll either <tt>retryReferrbl()</tt> or <tt>skipReferrbl()</tt>
     * to continue processing referrbls.
     */
    public bbstrbct Context getReferrblContext() throws NbmingException;

    /**
     * Retrieves the context bt which to continue the method using
     * environment properties bnd no controls.
     * The referrbl context is crebted using <tt>env</tt> bs its environment
     * properties bnd no controls.
     *<p>
     * This method is equivblent to
     *<blockquote><pre>
     * getReferrblContext(env, null);
     *</pre></blockquote>
     *<p>
     * It is overridden in this clbss for documentbtion purposes only.
     * See <tt>ReferrblException</tt> for how to use this method.
     *
     * @pbrbm env The possibly null environment to use when retrieving the
     *          referrbl context. If null, no environment properties will be used.
     *
     * @return The non-null context bt which to continue the method.
     * @exception NbmingException If b nbming exception wbs encountered.
     * Cbll either <tt>retryReferrbl()</tt> or <tt>skipReferrbl()</tt>
     * to continue processing referrbls.
     */
    public bbstrbct Context
        getReferrblContext(Hbshtbble<?,?> env)
        throws NbmingException;

    /**
     * Retrieves the context bt which to continue the method using
     * request controls bnd environment properties.
     * Regbrdless of whether b referrbl is encountered directly during b
     * context operbtion, or indirectly, for exbmple, during b sebrch
     * enumerbtion, the referrbl exception should provide b context
     * bt which to continue the operbtion.
     * To continue the operbtion, the client progrbm should re-invoke
     * the method using the sbme brguments bs the originbl invocbtion.
     *<p>
     * <tt>reqCtls</tt> is used when crebting the connection to the referred
     * server. These controls will be used bs the connection request controls for
     * the context bnd context instbnces
     * derived from the context.
     * <tt>reqCtls</tt> will blso be the context's request controls for
     * subsequent context operbtions. See the <tt>LdbpContext</tt> clbss
     * description for detbils.
     *<p>
     * This method should be used instebd of the other two overlobded forms
     * when the cbller needs to supply request controls for crebting
     * the referrbl context. It might need to do this, for exbmple, when
     * it needs to supply specibl controls relbting to buthenticbtion.
     *<p>
     * Service provider implementors should rebd the "Service Provider" section
     * in the <tt>LdbpContext</tt> clbss description for implementbtion detbils.
     *
     * @pbrbm reqCtls The possibly null request controls to use for the new context.
     * If null or the empty brrby mebns use no request controls.
     * @pbrbm env The possibly null environment properties to use when
     * for the new context. If null, the context is initiblized with no environment
     * properties.
     * @return The non-null context bt which to continue the method.
     * @exception NbmingException If b nbming exception wbs encountered.
     * Cbll either <tt>retryReferrbl()</tt> or <tt>skipReferrbl()</tt>
     * to continue processing referrbls.
     */
    public bbstrbct Context
        getReferrblContext(Hbshtbble<?,?> env,
                           Control[] reqCtls)
        throws NbmingException;

    privbte stbtic finbl long seriblVersionUID = -1668992791764950804L;
}
