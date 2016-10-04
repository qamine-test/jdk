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

import jbvb.util.Hbshtbble;
import jbvbx.nbming.*;
import jbvbx.nbming.directory.Attributes;

/**
  * This interfbce represents b fbctory for crebting bn object given
  * bn object bnd bttributes bbout the object.
  *<p>
  * The JNDI frbmework bllows for object implementbtions to
  * be lobded in dynbmicblly vib <em>object fbctories</em>. See
  * <tt>ObjectFbctory</tt> for detbils.
  * <p>
  * A <tt>DirObjectFbctory</tt> extends <tt>ObjectFbctory</tt> by bllowing
  * bn <tt>Attributes</tt> instbnce
  * to be supplied to the <tt>getObjectInstbnce()</tt> method.
  * <tt>DirObjectFbctory</tt> implementbtions bre intended to be used by <tt>DirContext</tt>
  * service providers. The service provider, in bddition rebding bn
  * object from the directory, might blrebdy hbve bttributes thbt
  * bre useful for the object fbctory to check to see whether the
  * fbctory is supposed to process the object. For instbnce, bn LDAP-style
  * service provider might hbve rebd the "objectclbss" of the object.
  * A CORBA object fbctory might be interested only in LDAP entries
  * with "objectclbss=corbbObject". By using the bttributes supplied by
  * the LDAP service provider, the CORBA object fbctory cbn quickly
  * eliminbte objects thbt it need not worry bbout, bnd non-CORBA object
  * fbctories cbn quickly eliminbte CORBA-relbted LDAP entries.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  *
  * @see NbmingMbnbger#getObjectInstbnce
  * @see DirectoryMbnbger#getObjectInstbnce
  * @see ObjectFbctory
  * @since 1.3
  */

public interfbce DirObjectFbctory extends ObjectFbctory {
/**
 * Crebtes bn object using the locbtion or reference informbtion, bnd bttributes
 * specified.
 * <p>
 * Specibl requirements of this object bre supplied
 * using <code>environment</code>.
 * An exbmple of such bn environment property is user identity
 * informbtion.
 *<p>
 * <tt>DirectoryMbnbger.getObjectInstbnce()</tt>
 * successively lobds in object fbctories. If it encounters b <tt>DirObjectFbctory</tt>,
 * it will invoke <tt>DirObjectFbctory.getObjectInstbnce()</tt>;
 * otherwise, it invokes
 * <tt>ObjectFbctory.getObjectInstbnce()</tt>. It does this until b fbctory
 * produces b non-null bnswer.
 * <p> When bn exception
 * is thrown by bn object fbctory, the exception is pbssed on to the cbller
 * of <tt>DirectoryMbnbger.getObjectInstbnce()</tt>. The sebrch for other fbctories
 * thbt mby produce b non-null bnswer is hblted.
 * An object fbctory should only throw bn exception if it is sure thbt
 * it is the only intended fbctory bnd thbt no other object fbctories
 * should be tried.
 * If this fbctory cbnnot crebte bn object using the brguments supplied,
 * it should return null.
  *<p>Since <tt>DirObjectFbctory</tt> extends <tt>ObjectFbctory</tt>, it
  * effectively
  * hbs two <tt>getObjectInstbnce()</tt> methods, where one differs from the other by
  * the bttributes brgument. Given b fbctory thbt implements <tt>DirObjectFbctory</tt>,
  * <tt>DirectoryMbnbger.getObjectInstbnce()</tt> will only
  * use the method thbt bccepts the bttributes brgument, while
  * <tt>NbmingMbnbger.getObjectInstbnce()</tt> will only use the one thbt does not bccept
  * the bttributes brgument.
 *<p>
 * See <tt>ObjectFbctory</tt> for b description URL context fbctories bnd other
 * properties of object fbctories thbt bpply equblly to <tt>DirObjectFbctory</tt>.
 *<p>
 * The <tt>nbme</tt>, <tt>bttrs</tt>, bnd <tt>environment</tt> pbrbmeters
 * bre owned by the cbller.
 * The implementbtion will not modify these objects or keep references
 * to them, blthough it mby keep references to clones or copies.
 *
 * @pbrbm obj The possibly null object contbining locbtion or reference
 *              informbtion thbt cbn be used in crebting bn object.
 * @pbrbm nbme The nbme of this object relbtive to <code>nbmeCtx</code>,
 *              or null if no nbme is specified.
 * @pbrbm nbmeCtx The context relbtive to which the <code>nbme</code>
 *              pbrbmeter is specified, or null if <code>nbme</code> is
 *              relbtive to the defbult initibl context.
 * @pbrbm environment The possibly null environment thbt is used in
 *              crebting the object.
 * @pbrbm bttrs The possibly null bttributes contbining some of <tt>obj</tt>'s
 * bttributes. <tt>bttrs</tt> might not necessbrily hbve bll of <tt>obj</tt>'s
 * bttributes. If the object fbctory requires more bttributes, it needs
 * to get it, either using <tt>obj</tt>, or <tt>nbme</tt> bnd <tt>nbmeCtx</tt>.
 *      The fbctory must not modify bttrs.
 * @return The object crebted; null if bn object cbnnot be crebted.
 * @exception Exception If this object fbctory encountered bn exception
 * while bttempting to crebte bn object, bnd no other object fbctories bre
 * to be tried.
 *
 * @see DirectoryMbnbger#getObjectInstbnce
 * @see NbmingMbnbger#getURLContext
 */
    public Object getObjectInstbnce(Object obj, Nbme nbme, Context nbmeCtx,
                                    Hbshtbble<?,?> environment,
                                    Attributes bttrs)
        throws Exception;
}
