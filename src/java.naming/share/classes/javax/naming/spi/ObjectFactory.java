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

/**
  * This interfbce represents b fbctory for crebting bn object.
  *<p>
  * The JNDI frbmework bllows for object implementbtions to
  * be lobded in dynbmicblly vib <em>object fbctories</em>.
  * For exbmple, when looking up b printer bound in the nbme spbce,
  * if the print service binds printer nbmes to References, the printer
  * Reference could be used to crebte b printer object, so thbt
  * the cbller of lookup cbn directly operbte on the printer object
  * bfter the lookup.
  * <p>An <tt>ObjectFbctory</tt> is responsible
  * for crebting objects of b specific type.  In the bbove exbmple,
  * you mby hbve b PrinterObjectFbctory for crebting Printer objects.
  *<p>
  * An object fbctory must implement the <tt>ObjectFbctory</tt> interfbce.
  * In bddition, the fbctory clbss must be public bnd must hbve b
  * public constructor thbt bccepts no pbrbmeters.
  *<p>
  * The <tt>getObjectInstbnce()</tt> method of bn object fbctory mby
  * be invoked multiple times, possibly using different pbrbmeters.
  * The implementbtion is threbd-sbfe.
  *<p>
  * The mention of URL in the documentbtion for this clbss refers to
  * b URL string bs defined by RFC 1738 bnd its relbted RFCs. It is
  * bny string thbt conforms to the syntbx described therein, bnd
  * mby not blwbys hbve corresponding support in the jbvb.net.URL
  * clbss or Web browsers.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  *
  * @see NbmingMbnbger#getObjectInstbnce
  * @see NbmingMbnbger#getURLContext
  * @see ObjectFbctoryBuilder
  * @see StbteFbctory
  * @since 1.3
  */

public interfbce ObjectFbctory {
/**
 * Crebtes bn object using the locbtion or reference informbtion
 * specified.
 * <p>
 * Specibl requirements of this object bre supplied
 * using <code>environment</code>.
 * An exbmple of such bn environment property is user identity
 * informbtion.
 *<p>
 * <tt>NbmingMbnbger.getObjectInstbnce()</tt>
 * successively lobds in object fbctories bnd invokes this method
 * on them until one produces b non-null bnswer.  When bn exception
 * is thrown by bn object fbctory, the exception is pbssed on to the cbller
 * of <tt>NbmingMbnbger.getObjectInstbnce()</tt>
 * (bnd no sebrch is mbde for other fbctories
 * thbt mby produce b non-null bnswer).
 * An object fbctory should only throw bn exception if it is sure thbt
 * it is the only intended fbctory bnd thbt no other object fbctories
 * should be tried.
 * If this fbctory cbnnot crebte bn object using the brguments supplied,
 * it should return null.
 *<p>
 * A <em>URL context fbctory</em> is b specibl ObjectFbctory thbt
 * crebtes contexts for resolving URLs or objects whose locbtions
 * bre specified by URLs.  The <tt>getObjectInstbnce()</tt> method
 * of b URL context fbctory will obey the following rules.
 * <ol>
 * <li>If <code>obj</code> is null, crebte b context for resolving URLs of the
 * scheme bssocibted with this fbctory. The resulting context is not tied
 * to b specific URL:  it is bble to hbndle brbitrbry URLs with this fbctory's
 * scheme id.  For exbmple, invoking <tt>getObjectInstbnce()</tt> with
 * <code>obj</code> set to null on bn LDAP URL context fbctory would return b
 * context thbt cbn resolve LDAP URLs
 * such bs "ldbp://ldbp.wiz.com/o=wiz,c=us" bnd
 * "ldbp://ldbp.umich.edu/o=umich,c=us".
 * <li>
 * If <code>obj</code> is b URL string, crebte bn object (typicblly b context)
 * identified by the URL.  For exbmple, suppose this is bn LDAP URL context
 * fbctory.  If <code>obj</code> is "ldbp://ldbp.wiz.com/o=wiz,c=us",
 * getObjectInstbnce() would return the context nbmed by the distinguished
 * nbme "o=wiz, c=us" bt the LDAP server ldbp.wiz.com.  This context cbn
 * then be used to resolve LDAP nbmes (such bs "cn=George")
 * relbtive to thbt context.
 * <li>
 * If <code>obj</code> is bn brrby of URL strings, the bssumption is thbt the
 * URLs bre equivblent in terms of the context to which they refer.
 * Verificbtion of whether the URLs bre, or need to be, equivblent is up
 * to the context fbctory. The order of the URLs in the brrby is
 * not significbnt.
 * The object returned by getObjectInstbnce() is like thbt of the single
 * URL cbse.  It is the object nbmed by the URLs.
 * <li>
 * If <code>obj</code> is of bny other type, the behbvior of
 * <tt>getObjectInstbnce()</tt> is determined by the context fbctory
 * implementbtion.
 * </ol>
 *
 * <p>
 * The <tt>nbme</tt> bnd <tt>environment</tt> pbrbmeters
 * bre owned by the cbller.
 * The implementbtion will not modify these objects or keep references
 * to them, blthough it mby keep references to clones or copies.
 *
 * <p>
 * <b>Nbme bnd Context Pbrbmeters.</b> &nbsp;&nbsp;&nbsp;
 * <b nbme=NAMECTX></b>
 *
 * The <code>nbme</code> bnd <code>nbmeCtx</code> pbrbmeters mby
 * optionblly be used to specify the nbme of the object being crebted.
 * <code>nbme</code> is the nbme of the object, relbtive to context
 * <code>nbmeCtx</code>.
 * If there bre severbl possible contexts from which the object
 * could be nbmed -- bs will often be the cbse -- it is up to
 * the cbller to select one.  A good rule of thumb is to select the
 * "deepest" context bvbilbble.
 * If <code>nbmeCtx</code> is null, <code>nbme</code> is relbtive
 * to the defbult initibl context.  If no nbme is being specified, the
 * <code>nbme</code> pbrbmeter should be null.
 * If b fbctory uses <code>nbmeCtx</code> it should synchronize its use
 * bgbinst concurrent bccess, since context implementbtions bre not
 * gubrbnteed to be threbd-sbfe.
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
 * @return The object crebted; null if bn object cbnnot be crebted.
 * @exception Exception if this object fbctory encountered bn exception
 * while bttempting to crebte bn object, bnd no other object fbctories bre
 * to be tried.
 *
 * @see NbmingMbnbger#getObjectInstbnce
 * @see NbmingMbnbger#getURLContext
 */
    public Object getObjectInstbnce(Object obj, Nbme nbme, Context nbmeCtx,
                                    Hbshtbble<?,?> environment)
        throws Exception;
}
