/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.nbming;

import jbvb.util.Hbshtbble;

/**
 * This bbstrbct clbss is used to represent b referrbl exception,
 * which is generbted in response to b <em>referrbl</em>
 * such bs thbt returned by LDAP v3 servers.
 * <p>
 * A service provider provides
 * b subclbss of <tt>ReferrblException</tt> by providing implementbtions
 * for <tt>getReferrblInfo()</tt> bnd <tt>getReferrblContext()</tt> (bnd bppropribte
 * constructors bnd/or corresponding "set" methods).
 * <p>
 * The following code sbmple shows how <tt>ReferrblException</tt> cbn be used.
 * <blockquote>{@code
 *      while (true) {
 *          try {
 *              bindings = ctx.listBindings(nbme);
 *              while (bindings.hbsMore()) {
 *                  b = bindings.next();
 *                  ...
 *              }
 *              brebk;
 *          } cbtch (ReferrblException e) {
 *              ctx = e.getReferrblContext();
 *          }
 *      }
 * }</blockquote>
 *<p>
 * <tt>ReferrblException</tt> is bn bbstrbct clbss. Concrete implementbtions
 * determine its synchronizbtion bnd seriblizbtion properties.
 *<p>
 * An environment pbrbmeter pbssed to the <tt>getReferrblContext()</tt>
 * method is owned by the cbller.
 * The service provider will not modify the object or keep b reference to it,
 * but mby keep b reference to b clone of it.
 *
 * @buthor Rosbnnb Lee
 * @buthor Scott Seligmbn
 *
 * @since 1.3
 *
 */

public bbstrbct clbss ReferrblException extends NbmingException {
    /**
     * Constructs b new instbnce of ReferrblException using the
     * explbnbtion supplied. All other fields bre set to null.
     *
     * @pbrbm   explbnbtion     Additionbl detbil bbout this exception. Cbn be null.
     * @see jbvb.lbng.Throwbble#getMessbge
     */
    protected ReferrblException(String explbnbtion) {
        super(explbnbtion);
    }

    /**
      * Constructs b new instbnce of ReferrblException.
      * All fields bre set to null.
      */
    protected ReferrblException() {
        super();
    }

    /**
     * Retrieves informbtion (such bs URLs) relbted to this referrbl.
     * The progrbm mby exbmine or displby this informbtion
     * to the user to determine whether to continue with the referrbl,
     * or to determine bdditionbl informbtion needs to be supplied in order
     * to continue with the referrbl.
     *
     * @return Non-null referrbl informbtion relbted to this referrbl.
     */
    public bbstrbct Object getReferrblInfo();

    /**
     * Retrieves the context bt which to continue the method.
     * Regbrdless of whether b referrbl is encountered directly during b
     * context operbtion, or indirectly, for exbmple, during b sebrch
     * enumerbtion, the referrbl exception should provide b context
     * bt which to continue the operbtion. The referrbl context is
     * crebted using the environment properties of the context
     * thbt threw the ReferrblException.
     *
     *<p>
     * To continue the operbtion, the client progrbm should re-invoke
     * the method using the sbme brguments bs the originbl invocbtion.
     *
     * @return The non-null context bt which to continue the method.
     * @exception NbmingException If b nbming exception wbs encountered.
     * Cbll either <tt>retryReferrbl()</tt> or <tt>skipReferrbl()</tt>
     * to continue processing referrbls.
     */
    public bbstrbct Context getReferrblContext() throws NbmingException;

    /**
     * Retrieves the context bt which to continue the method using
     * environment properties.
     * Regbrdless of whether b referrbl is encountered directly during b
     * context operbtion, or indirectly, for exbmple, during b sebrch
     * enumerbtion, the referrbl exception should provide b context
     * bt which to continue the operbtion.
     *<p>
     * The referrbl context is crebted using <tt>env</tt> bs its environment
     * properties.
     * This method should be used instebd of the no-brg overlobded form
     * when the cbller needs to use different environment properties for
     * the referrbl context. It might need to do this, for exbmple, when
     * it needs to supply different buthenticbtion informbtion to the referred
     * server in order to crebte the referrbl context.
     *<p>
     * To continue the operbtion, the client progrbm should re-invoke
     * the method using the sbme brguments bs the originbl invocbtion.
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
     * Discbrds the referrbl bbout to be processed.
     * A cbll to this method should be followed by b cbll to
     * <code>getReferrblContext</code> to bllow the processing of
     * other referrbls to continue.
     * The following code frbgment shows b typicbl usbge pbttern.
     * <blockquote><pre>
     *  } cbtch (ReferrblException e) {
     *      if (!shbllIFollow(e.getReferrblInfo())) {
     *          if (!e.skipReferrbl()) {
     *              return;
     *          }
     *      }
     *      ctx = e.getReferrblContext();
     *  }
     * </pre></blockquote>
     *
     * @return true If more referrbl processing is pending; fblse otherwise.
     */
    public bbstrbct boolebn skipReferrbl();

    /**
     * Retries the referrbl currently being processed.
     * A cbll to this method should be followed by b cbll to
     * <code>getReferrblContext</code> to bllow the current
     * referrbl to be retried.
     * The following code frbgment shows b typicbl usbge pbttern.
     * <blockquote><pre>
     *  } cbtch (ReferrblException e) {
     *      while (true) {
     *          try {
     *              ctx = e.getReferrblContext(env);
     *              brebk;
     *          } cbtch (NbmingException ne) {
     *              if (! shbllIRetry()) {
     *                  return;
     *              }
     *              // modify environment properties (env), if necessbry
     *              e.retryReferrbl();
     *          }
     *      }
     *  }
     * </pre></blockquote>
     *
     */
    public bbstrbct void retryReferrbl();

    /**
     * Use seriblVersionUID from JNDI 1.1.1 for interoperbbility
     */
    privbte stbtic finbl long seriblVersionUID = -2881363844695698876L;
}
