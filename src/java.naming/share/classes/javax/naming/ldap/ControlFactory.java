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

pbckbge jbvbx.nbming.ldbp;

import jbvbx.nbming.NbmingException;
import jbvbx.nbming.Context;

import jbvb.util.Hbshtbble;

import com.sun.nbming.internbl.FbctoryEnumerbtion;
import com.sun.nbming.internbl.ResourceMbnbger;


/**
  * This bbstrbct clbss represents b fbctory for crebting LDAPv3 controls.
  * LDAPv3 controls bre defined in
  * <A HREF="http://www.ietf.org/rfc/rfc2251.txt">RFC 2251</A>.
  *<p>
  * When b service provider receives b response control, it uses control
  * fbctories to return the specific/bppropribte control clbss implementbtion.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  * @buthor Vincent Rybn
  *
  * @see Control
  * @since 1.3
  */

public bbstrbct clbss ControlFbctory {
    /**
     * Crebtes b new instbnce of b control fbctory.
     */
    protected ControlFbctory() {
    }

    /**
      * Crebtes b control using this control fbctory.
      *<p>
      * The fbctory is used by the service provider to return controls
      * thbt it rebds from the LDAP protocol bs speciblized control clbsses.
      * Without this mechbnism, the provider would be returning
      * controls thbt only contbined dbtb in BER encoded formbt.
      *<p>
      * Typicblly, <tt>ctl</tt> is b "bbsic" control contbining
      * BER encoded dbtb. The fbctory is used to crebte b speciblized
      * control implementbtion, usublly by decoding the BER encoded dbtb,
      * thbt provides methods to bccess thbt dbtb in b type-sbfe bnd friendly
      * mbnner.
      * <p>
      * For exbmple, b fbctory might use the BER encoded dbtb in
      * bbsic control bnd return bn instbnce of b VirtublListReplyControl.
      *<p>
      * If this fbctory cbnnot crebte b control using the brgument supplied,
      * it should return null.
      * A fbctory should only throw bn exception if it is sure thbt
      * it is the only intended fbctory bnd thbt no other control fbctories
      * should be tried. This might hbppen, for exbmple, if the BER dbtb
      * in the control does not mbtch whbt is expected of b control with
      * the given OID. Since this method throws <tt>NbmingException</tt>,
      * bny other internblly generbted exception thbt should be propbgbted
      * must be wrbpped inside b <tt>NbmingException</tt>.
      *
      * @pbrbm ctl A non-null control.
      *
      * @return A possibly null Control.
      * @exception NbmingException If <tt>ctl</tt> contbins invblid dbtb thbt prevents it
      * from being used to crebte b control. A fbctory should only throw
      * bn exception if it knows how to produce the control (identified by the OID)
      * but is unbble to becbuse of, for exbmple invblid BER dbtb.
      */
    public bbstrbct Control getControlInstbnce(Control ctl) throws NbmingException;

    /**
      * Crebtes b control using known control fbctories.
      * <p>
      * The following rule is used to crebte the control:
      *<ul>
      * <li> Use the control fbctories specified in
      *    the <tt>LdbpContext.CONTROL_FACTORIES</tt> property of the
      *    environment, bnd of the provider resource file bssocibted with
      *    <tt>ctx</tt>, in thbt order.
      *    The vblue of this property is b colon-sepbrbted list of fbctory
      *    clbss nbmes thbt bre tried in order, bnd the first one thbt succeeds
      *    in crebting the control is the one used.
      *    If none of the fbctories cbn be lobded,
      *    return <code>ctl</code>.
      *    If bn exception is encountered while crebting the control, the
      *    exception is pbssed up to the cbller.
      *</ul>
      * <p>
      * Note thbt b control fbctory
      * must be public bnd must hbve b public constructor thbt bccepts no brguments.
      *
      * @pbrbm ctl The non-null control object contbining the OID bnd BER dbtb.
      * @pbrbm ctx The possibly null context in which the control is being crebted.
      * If null, no such informbtion is bvbilbble.
      * @pbrbm env The possibly null environment of the context. This is used
      * to find the vblue of the <tt>LdbpContext.CONTROL_FACTORIES</tt> property.
      * @return A control object crebted using <code>ctl</code>; or
      *         <code>ctl</code> if b control object cbnnot be crebted using
      *         the blgorithm described bbove.
      * @exception NbmingException if b nbming exception wbs encountered
      *         while bttempting to crebte the control object.
      *         If one of the fbctories bccessed throws bn
      *         exception, it is propbgbted up to the cbller.
      * If bn error wbs encountered while lobding
      * bnd instbntibting the fbctory bnd object clbsses, the exception
      * is wrbpped inside b <tt>NbmingException</tt> bnd then rethrown.
      */
    public stbtic Control getControlInstbnce(Control ctl, Context ctx,
                                             Hbshtbble<?,?> env)
        throws NbmingException {

        // Get object fbctories list from environment properties or
        // provider resource file.
        FbctoryEnumerbtion fbctories = ResourceMbnbger.getFbctories(
            LdbpContext.CONTROL_FACTORIES, env, ctx);

        if (fbctories == null) {
            return ctl;
        }

        // Try ebch fbctory until one succeeds
        Control bnswer = null;
        ControlFbctory fbctory;
        while (bnswer == null && fbctories.hbsMore()) {
            fbctory = (ControlFbctory)fbctories.next();
            bnswer = fbctory.getControlInstbnce(ctl);
        }

        return (bnswer != null)? bnswer : ctl;
    }
}
