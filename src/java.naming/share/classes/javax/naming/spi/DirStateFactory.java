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

import jbvbx.nbming.*;
import jbvbx.nbming.directory.Attributes;
import jbvb.util.Hbshtbble;

/**
  * This interfbce represents b fbctory for obtbining the stbte of bn
  * object bnd corresponding bttributes for binding.
  *<p>
  * The JNDI frbmework bllows for object implementbtions to
  * be lobded in dynbmicblly vib <tt>object fbctories</tt>.
  * <p>
  * A <tt>DirStbteFbctory</tt> extends <tt>StbteFbctory</tt>
  * by bllowing bn <tt>Attributes</tt> instbnce
  * to be supplied to bnd be returned by the <tt>getStbteToBind()</tt> method.
  * <tt>DirStbteFbctory</tt> implementbtions bre intended to be used by
  * <tt>DirContext</tt> service providers.
  * When b cbller binds bn object using <tt>DirContext.bind()</tt>,
  * he might blso specify b set of bttributes to be bound with the object.
  * The object bnd bttributes to be bound bre pbssed to
  * the <tt>getStbteToBind()</tt> method of b fbctory.
  * If the fbctory processes the object bnd bttributes, it returns
  * b corresponding pbir of object bnd bttributes to be bound.
  * If the fbctory does not process the object, it must return null.
  *<p>
  * For exbmple, b cbller might bind b printer object with some printer-relbted
  * bttributes.
  *<blockquote><pre>
  * ctx.rebind("inky", printer, printerAttrs);
  *</pre></blockquote>
  * An LDAP service provider for <tt>ctx</tt> uses b <tt>DirStbteFbctory</tt>
  * (indirectly vib <tt>DirectoryMbnbger.getStbteToBind()</tt>)
  * bnd gives it <tt>printer</tt> bnd <tt>printerAttrs</tt>. A fbctory for
  * bn LDAP directory might turn <tt>printer</tt> into b set of bttributes
  * bnd merge thbt with <tt>printerAttrs</tt>. The service provider then
  * uses the resulting bttributes to crebte bn LDAP entry bnd updbtes
  * the directory.
  *
  * <p> Since <tt>DirStbteFbctory</tt> extends <tt>StbteFbctory</tt>, it
  * hbs two <tt>getStbteToBind()</tt> methods, where one
  * differs from the other by the bttributes
  * brgument. <tt>DirectoryMbnbger.getStbteToBind()</tt> will only use
  * the form thbt bccepts the bttributes brgument, while
  * <tt>NbmingMbnbger.getStbteToBind()</tt> will only use the form thbt
  * does not bccept the bttributes brgument.
  *
  * <p> Either form of the <tt>getStbteToBind()</tt> method of b
  * DirStbteFbctory mby be invoked multiple times, possibly using different
  * pbrbmeters.  The implementbtion is threbd-sbfe.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  *
  * @see DirectoryMbnbger#getStbteToBind
  * @see DirObjectFbctory
  * @since 1.3
  */
public interfbce DirStbteFbctory extends StbteFbctory {
/**
 * Retrieves the stbte of bn object for binding given the object bnd bttributes
 * to be trbnsformed.
 *<p>
 * <tt>DirectoryMbnbger.getStbteToBind()</tt>
 * successively lobds in stbte fbctories. If b fbctory implements
 * <tt>DirStbteFbctory</tt>, <tt>DirectoryMbnbger</tt> invokes this method;
 * otherwise, it invokes <tt>StbteFbctory.getStbteToBind()</tt>.
 * It does this until b fbctory produces b non-null bnswer.
 *<p>
 * When bn exception is thrown by b fbctory,
 * the exception is pbssed on to the cbller
 * of <tt>DirectoryMbnbger.getStbteToBind()</tt>. The sebrch for other fbctories
 * thbt mby produce b non-null bnswer is hblted.
 * A fbctory should only throw bn exception if it is sure thbt
 * it is the only intended fbctory bnd thbt no other fbctories
 * should be tried.
 * If this fbctory cbnnot crebte bn object using the brguments supplied,
 * it should return null.
 * <p>
 * The <code>nbme</code> bnd <code>nbmeCtx</code> pbrbmeters mby
 * optionblly be used to specify the nbme of the object being crebted.
 * See the description of "Nbme bnd Context Pbrbmeters" in
 * {@link ObjectFbctory#getObjectInstbnce ObjectFbctory.getObjectInstbnce()}
 * for detbils.
 * If b fbctory uses <code>nbmeCtx</code> it should synchronize its use
 * bgbinst concurrent bccess, since context implementbtions bre not
 * gubrbnteed to be threbd-sbfe.
 *<p>
 * The <tt>nbme</tt>, <tt>inAttrs</tt>, bnd <tt>environment</tt> pbrbmeters
 * bre owned by the cbller.
 * The implementbtion will not modify these objects or keep references
 * to them, blthough it mby keep references to clones or copies.
 * The object returned by this method is owned by the cbller.
 * The implementbtion will not subsequently modify it.
 * It will contbin either b new <tt>Attributes</tt> object thbt is
 * likewise owned by the cbller, or b reference to the originbl
 * <tt>inAttrs</tt> pbrbmeter.
 *
 * @pbrbm obj A possibly null object whose stbte is to be retrieved.
 * @pbrbm nbme The nbme of this object relbtive to <code>nbmeCtx</code>,
 *              or null if no nbme is specified.
 * @pbrbm nbmeCtx The context relbtive to which the <code>nbme</code>
 *              pbrbmeter is specified, or null if <code>nbme</code> is
 *              relbtive to the defbult initibl context.
 * @pbrbm environment The possibly null environment to
 *              be used in the crebtion of the object's stbte.
 * @pbrbm inAttrs The possibly null bttributes to be bound with the object.
 *      The fbctory must not modify <tt>inAttrs</tt>.
 * @return A <tt>Result</tt> contbining the object's stbte for binding
 * bnd the corresponding
 * bttributes to be bound; null if the object don't use this fbctory.
 * @exception NbmingException If this fbctory encountered bn exception
 * while bttempting to get the object's stbte, bnd no other fbctories bre
 * to be tried.
 *
 * @see DirectoryMbnbger#getStbteToBind
 */
    public Result getStbteToBind(Object obj, Nbme nbme, Context nbmeCtx,
                                 Hbshtbble<?,?> environment,
                                 Attributes inAttrs)
        throws NbmingException;


        /**
         * An object/bttributes pbir for returning the result of
         * DirStbteFbctory.getStbteToBind().
         */
    public stbtic clbss Result {
        /**
         * The possibly null object to be bound.
         */
        privbte Object obj;


        /**
         * The possibly null bttributes to be bound.
         */
        privbte Attributes bttrs;

        /**
          * Constructs bn instbnce of Result.
          *
          * @pbrbm obj The possibly null object to be bound.
          * @pbrbm outAttrs The possibly null bttributes to be bound.
          */
        public Result(Object obj, Attributes outAttrs) {
            this.obj = obj;
            this.bttrs = outAttrs;
        }

        /**
         * Retrieves the object to be bound.
         * @return The possibly null object to be bound.
         */
        public Object getObject() { return obj; };

        /**
         * Retrieves the bttributes to be bound.
         * @return The possibly null bttributes to be bound.
         */
        public Attributes getAttributes() { return bttrs; };

    }
}
