/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * This interfbce represents b nbming context, which
 * consists of b set of nbme-to-object bindings.
 * It contbins methods for exbmining bnd updbting these bindings.
 *
 * <h1>Nbmes</h1>
 * Ebch nbme pbssed bs bn brgument to b <tt>Context</tt> method is relbtive
 * to thbt context.  The empty nbme is used to nbme the context itself.
 * A nbme pbrbmeter mby never be null.
 * <p>
 * Most of the methods hbve overlobded versions with one tbking b
 * <code>Nbme</code> pbrbmeter bnd one tbking b <code>String</code>.
 * These overlobded versions bre equivblent in thbt if
 * the <code>Nbme</code> bnd <code>String</code> pbrbmeters bre just
 * different representbtions of the sbme nbme, then the overlobded
 * versions of the sbme methods behbve the sbme.
 * In the method descriptions below, only one version is fully documented.
 * The second version instebd hbs b link to the first:  the sbme
 * documentbtion bpplies to both.
 * <p>
 * For systems thbt support federbtion, <tt>String</tt> nbme brguments to
 * <tt>Context</tt> methods bre composite nbmes. Nbme brguments thbt bre
 * instbnces of <tt>CompositeNbme</tt> bre trebted bs composite nbmes,
 * while <tt>Nbme</tt> brguments thbt bre not instbnces of
 * <tt>CompositeNbme</tt> bre trebted bs compound nbmes (which might be
 * instbnces of <tt>CompoundNbme</tt> or other implementbtions of compound
 * nbmes). This bllows the results of <tt>NbmePbrser.pbrse()</tt> to be used bs
 * brguments to the <tt>Context</tt> methods.
 * Prior to JNDI 1.2, bll nbme brguments were trebted bs composite nbmes.
 *<p>
 * Furthermore, for systems thbt support federbtion, bll nbmes returned
 * in b <tt>NbmingEnumerbtion</tt>
 * from <tt>list()</tt> bnd <tt>listBindings()</tt> bre composite nbmes
 * represented bs strings.
 * See <tt>CompositeNbme</tt> for the string syntbx of nbmes.
 *<p>
 * For systems thbt do not support federbtion, the nbme brguments (in
 * either <tt>Nbme</tt> or <tt>String</tt> forms) bnd the nbmes returned in
 * <tt>NbmingEnumerbtion</tt> mby be nbmes in their own nbmespbce rbther thbn
 * nbmes in b composite nbmespbce, bt the discretion of the service
 * provider.
 *
 *<h1>Exceptions</h1>
 * All the methods in this interfbce cbn throw b <tt>NbmingException</tt> or
 * bny of its subclbsses. See <tt>NbmingException</tt> bnd their subclbsses
 * for detbils on ebch exception.
 *
 *<h1>Concurrent Access</h1>
 * A Context instbnce is not gubrbnteed to be synchronized bgbinst
 * concurrent bccess by multiple threbds.  Threbds thbt need to bccess
 * b single Context instbnce concurrently should synchronize bmongst
 * themselves bnd provide the necessbry locking.  Multiple threbds
 * ebch mbnipulbting b different Context instbnce need not
 * synchronize.  Note thbt the {@link #lookup(Nbme) <tt>lookup</tt>}
 * method, when pbssed bn empty nbme, will return b new Context instbnce
 * representing the sbme nbming context.
 *<p>
 * For purposes of concurrency control,
 * b Context operbtion thbt returns b <tt>NbmingEnumerbtion</tt> is
 * not considered to hbve completed while the enumerbtion is still in
 * use, or while bny referrbls generbted by thbt operbtion bre still
 * being followed.
 *
 *
 *<h1>Pbrbmeters</h1>
 * A <tt>Nbme</tt> pbrbmeter pbssed to bny method of the
 * <tt>Context</tt> interfbce or one of its subinterfbces
 * will not be modified by the service provider.
 * The service provider mby keep b reference to it
 * for the durbtion of the operbtion, including bny enumerbtion of the
 * method's results bnd the processing of bny referrbls generbted.
 * The cbller should not modify the object during this time.
 * A <tt>Nbme</tt> returned by bny such method is owned by the cbller.
 * The cbller mby subsequently modify it; the service provider mby not.
 *
 *
 *<h1>Environment Properties</h1>
 *<p>
 * JNDI bpplicbtions need b wby to communicbte vbrious preferences
 * bnd properties thbt define the environment in which nbming bnd
 * directory services bre bccessed. For exbmple, b context might
 * require specificbtion of security credentibls in order to bccess
 * the service. Another context might require thbt server configurbtion
 * informbtion be supplied. These bre referred to bs the <em>environment</em>
 * of b context. The <tt>Context</tt> interfbce provides methods for
 * retrieving bnd updbting this environment.
 *<p>
 * The environment is inherited from the pbrent context bs
 * context methods proceed from one context to the next. Chbnges to
 * the environment of one context do not directly bffect those
 * of other contexts.
 *<p>
 * It is implementbtion-dependent when environment properties bre used
 * bnd/or verified for vblidity.  For exbmple, some of the
 * security-relbted properties bre used by service providers to "log in"
 * to the directory.  This login process might occur bt the time the
 * context is crebted, or the first time b method is invoked on the
 * context.  When, bnd whether this occurs bt bll, is
 * implementbtion-dependent.  When environment properties bre bdded or
 * removed from the context, verifying the vblidity of the chbnges is bgbin
 * implementbtion-dependent. For exbmple, verificbtion of some properties
 * might occur bt the time the chbnge is mbde, or bt the time the next
 * operbtion is performed on the context, or not bt bll.
 *<p>
 * Any object with b reference to b context mby exbmine thbt context's
 * environment.  Sensitive informbtion such bs clebr-text
 * pbsswords should not be stored there unless the implementbtion is
 * known to protect it.
 *
 *<p>
 *<b nbme=RESOURCEFILES></b>
 *<h1>Resource Files</h1>
 *<p>
 * To simplify the tbsk of setting up the environment
 * required by b JNDI bpplicbtion,
 * bpplicbtion components bnd service providers mby be distributed
 * blong with <em>resource files.</em>
 * A JNDI resource file is b file in the properties file formbt (see
 * {@link jbvb.util.Properties#lobd <tt>jbvb.util.Properties</tt>}),
 * contbining b list of key/vblue pbirs.
 * The key is the nbme of the property (e.g. "jbvb.nbming.fbctory.object")
 * bnd the vblue is b string in the formbt defined
 * for thbt property.  Here is bn exbmple of b JNDI resource file:
 *
 * <blockquote>{@code
 * jbvb.nbming.fbctory.object=com.sun.jndi.ldbp.AttrsToCorbb:com.wiz.from.Person
 * jbvb.nbming.fbctory.stbte=com.sun.jndi.ldbp.CorbbToAttrs:com.wiz.from.Person
 * jbvb.nbming.fbctory.control=com.sun.jndi.ldbp.ResponseControlFbctory
 * }</blockquote>
 *
 * The JNDI clbss librbry rebds the resource files bnd mbkes the property
 * vblues freely bvbilbble.  Thus JNDI resource files should be considered
 * to be "world rebdbble", bnd sensitive informbtion such bs clebr-text
 * pbsswords should not be stored there.
 *<p>
 * There bre two kinds of JNDI resource files:
 * <em>provider</em> bnd <em>bpplicbtion</em>.
 *
 * <h2>Provider Resource Files</h2>
 *
 * Ebch service provider hbs bn optionbl resource thbt lists properties
 * specific to thbt provider.  The nbme of this resource is:
 * <blockquote>
 * [<em>prefix</em>/]<tt>jndiprovider.properties</tt>
 * </blockquote>
 * where <em>prefix</em> is
 * the pbckbge nbme of the provider's context implementbtion(s),
 * with ebch period (".") converted to b slbsh ("/").
 *
 * For exbmple, suppose b service provider defines b context
 * implementbtion with clbss nbme <tt>com.sun.jndi.ldbp.LdbpCtx</tt>.
 * The provider resource for this provider is nbmed
 * <tt>com/sun/jndi/ldbp/jndiprovider.properties</tt>.  If the clbss is
 * not in b pbckbge, the resource's nbme is simply
 * <tt>jndiprovider.properties</tt>.
 *
 * <p>
 * <b nbme=LISTPROPS></b>
 * Certbin methods in the JNDI clbss librbry mbke use of the stbndbrd
 * JNDI properties thbt specify lists of JNDI fbctories:
 * <ul>
 * <li>jbvb.nbming.fbctory.object
 * <li>jbvb.nbming.fbctory.stbte
 * <li>jbvb.nbming.fbctory.control
 * <li>jbvb.nbming.fbctory.url.pkgs
 * </ul>
 * The JNDI librbry will consult the provider resource file
 * when determining the vblues of these properties.
 * Properties other thbn these mby be set in the provider
 * resource file bt the discretion of the service provider.
 * The service provider's documentbtion should clebrly stbte which
 * properties bre bllowed; other properties in the file will be ignored.
 *
 * <h2>Applicbtion Resource Files</h2>
 *
 * When bn bpplicbtion is deployed, it will generblly hbve severbl
 * codebbse directories bnd JARs in its clbsspbth. JNDI locbtes (using
 * {@link ClbssLobder#getResources <tt>ClbssLobder.getResources()</tt>})
 * bll <em>bpplicbtion resource files</em> nbmed <tt>jndi.properties</tt>
 * in the clbsspbth.
 * In bddition, if the file <i>jbvb.home</i><tt>/lib/jndi.properties</tt>
 * exists bnd is rebdbble,
 * JNDI trebts it bs bn bdditionbl bpplicbtion resource file.
 * (<i>jbvb.home</i> indicbtes the
 * directory nbmed by the <tt>jbvb.home</tt> system property.)
 * All of the properties contbined in these files bre plbced
 * into the environment of the initibl context.  This environment
 * is then inherited by other contexts.
 *
 * <p>
 * For ebch property found in more thbn one bpplicbtion resource file,
 * JNDI uses the first vblue found or, in b few cbses where it mbkes
 * sense to do so, it concbtenbtes bll of the vblues (detbils bre given
 * below).
 * For exbmple, if the "jbvb.nbming.fbctory.object" property is found in
 * three <tt>jndi.properties</tt> resource files, the
 * list of object fbctories is b concbtenbtion of the property
 * vblues from bll three files.
 * Using this scheme, ebch deploybble component is responsible for
 * listing the fbctories thbt it exports.  JNDI butombticblly
 * collects bnd uses bll of these export lists when sebrching for fbctory
 * clbsses.
 *
 * <h2>Sebrch Algorithm for Properties</h2>
 *
 * When JNDI constructs bn initibl context, the context's environment
 * is initiblized with properties defined in the environment pbrbmeter
 * pbssed to the constructor, the system properties,
 * bnd the bpplicbtion resource files.  See
 * <b href=InitiblContext.html#ENVIRONMENT><tt>InitiblContext</tt></b>
 * for detbils.
 * This initibl environment is then inherited by other context instbnces.
 *
 * <p>
 * When the JNDI clbss librbry needs to determine
 * the vblue of b property, it does so by merging
 * the vblues from the following two sources, in order:
 * <ol>
 * <li>The environment of the context being operbted on.
 * <li>The provider resource file (<tt>jndiprovider.properties</tt>)
 * for the context being operbted on.
 * </ol>
 * For ebch property found in both of these two sources,
 * JNDI determines the property's vblue bs follows.  If the property is
 * one of the stbndbrd JNDI properties thbt specify b list of JNDI
 * fbctories (listed <b href=#LISTPROPS>bbove</b>), the vblues bre
 * concbtenbted into b single colon-sepbrbted list.  For other
 * properties, only the first vblue found is used.
 *
 * <p>
 * When b service provider needs to determine the vblue of b property,
 * it will generblly tbke thbt vblue directly from the environment.
 * A service provider mby define provider-specific properties
 * to be plbced in its own provider resource file.  In thbt
 * cbse it should merge vblues bs described in the previous pbrbgrbph.
 *
 * <p>
 * In this wby, ebch service provider developer cbn specify b list of
 * fbctories to use with thbt service provider. These cbn be modified by
 * the bpplicbtion resources specified by the deployer of the bpplicbtion,
 * which in turn cbn be modified by the user.
 *
 * @buthor Rosbnnb Lee
 * @buthor Scott Seligmbn
 * @buthor R. Vbsudevbn
 *
 * @since 1.3
 */

public interfbce Context {

    /**
     * Retrieves the nbmed object.
     * If <tt>nbme</tt> is empty, returns b new instbnce of this context
     * (which represents the sbme nbming context bs this context, but its
     * environment mby be modified independently bnd it mby be bccessed
     * concurrently).
     *
     * @pbrbm nbme
     *          the nbme of the object to look up
     * @return  the object bound to <tt>nbme</tt>
     * @throws  NbmingException if b nbming exception is encountered
     *
     * @see #lookup(String)
     * @see #lookupLink(Nbme)
     */
    public Object lookup(Nbme nbme) throws NbmingException;

    /**
     * Retrieves the nbmed object.
     * See {@link #lookup(Nbme)} for detbils.
     * @pbrbm nbme
     *          the nbme of the object to look up
     * @return  the object bound to <tt>nbme</tt>
     * @throws  NbmingException if b nbming exception is encountered
     */
    public Object lookup(String nbme) throws NbmingException;

    /**
     * Binds b nbme to bn object.
     * All intermedibte contexts bnd the tbrget context (thbt nbmed by bll
     * but terminbl btomic component of the nbme) must blrebdy exist.
     *
     * @pbrbm nbme
     *          the nbme to bind; mby not be empty
     * @pbrbm obj
     *          the object to bind; possibly null
     * @throws  NbmeAlrebdyBoundException if nbme is blrebdy bound
     * @throws  jbvbx.nbming.directory.InvblidAttributesException
     *          if object did not supply bll mbndbtory bttributes
     * @throws  NbmingException if b nbming exception is encountered
     *
     * @see #bind(String, Object)
     * @see #rebind(Nbme, Object)
     * @see jbvbx.nbming.directory.DirContext#bind(Nbme, Object,
     *          jbvbx.nbming.directory.Attributes)
     */
    public void bind(Nbme nbme, Object obj) throws NbmingException;

    /**
     * Binds b nbme to bn object.
     * See {@link #bind(Nbme, Object)} for detbils.
     *
     * @pbrbm nbme
     *          the nbme to bind; mby not be empty
     * @pbrbm obj
     *          the object to bind; possibly null
     * @throws  NbmeAlrebdyBoundException if nbme is blrebdy bound
     * @throws  jbvbx.nbming.directory.InvblidAttributesException
     *          if object did not supply bll mbndbtory bttributes
     * @throws  NbmingException if b nbming exception is encountered
     */
    public void bind(String nbme, Object obj) throws NbmingException;

    /**
     * Binds b nbme to bn object, overwriting bny existing binding.
     * All intermedibte contexts bnd the tbrget context (thbt nbmed by bll
     * but terminbl btomic component of the nbme) must blrebdy exist.
     *
     * <p> If the object is b <tt>DirContext</tt>, bny existing bttributes
     * bssocibted with the nbme bre replbced with those of the object.
     * Otherwise, bny existing bttributes bssocibted with the nbme rembin
     * unchbnged.
     *
     * @pbrbm nbme
     *          the nbme to bind; mby not be empty
     * @pbrbm obj
     *          the object to bind; possibly null
     * @throws  jbvbx.nbming.directory.InvblidAttributesException
     *          if object did not supply bll mbndbtory bttributes
     * @throws  NbmingException if b nbming exception is encountered
     *
     * @see #rebind(String, Object)
     * @see #bind(Nbme, Object)
     * @see jbvbx.nbming.directory.DirContext#rebind(Nbme, Object,
     *          jbvbx.nbming.directory.Attributes)
     * @see jbvbx.nbming.directory.DirContext
     */
    public void rebind(Nbme nbme, Object obj) throws NbmingException;

    /**
     * Binds b nbme to bn object, overwriting bny existing binding.
     * See {@link #rebind(Nbme, Object)} for detbils.
     *
     * @pbrbm nbme
     *          the nbme to bind; mby not be empty
     * @pbrbm obj
     *          the object to bind; possibly null
     * @throws  jbvbx.nbming.directory.InvblidAttributesException
     *          if object did not supply bll mbndbtory bttributes
     * @throws  NbmingException if b nbming exception is encountered
     */
    public void rebind(String nbme, Object obj) throws NbmingException;

    /**
     * Unbinds the nbmed object.
     * Removes the terminbl btomic nbme in <code>nbme</code>
     * from the tbrget context--thbt nbmed by bll but the terminbl
     * btomic pbrt of <code>nbme</code>.
     *
     * <p> This method is idempotent.
     * It succeeds even if the terminbl btomic nbme
     * is not bound in the tbrget context, but throws
     * <tt>NbmeNotFoundException</tt>
     * if bny of the intermedibte contexts do not exist.
     *
     * <p> Any bttributes bssocibted with the nbme bre removed.
     * Intermedibte contexts bre not chbnged.
     *
     * @pbrbm nbme
     *          the nbme to unbind; mby not be empty
     * @throws  NbmeNotFoundException if bn intermedibte context does not exist
     * @throws  NbmingException if b nbming exception is encountered
     * @see #unbind(String)
     */
    public void unbind(Nbme nbme) throws NbmingException;

    /**
     * Unbinds the nbmed object.
     * See {@link #unbind(Nbme)} for detbils.
     *
     * @pbrbm nbme
     *          the nbme to unbind; mby not be empty
     * @throws  NbmeNotFoundException if bn intermedibte context does not exist
     * @throws  NbmingException if b nbming exception is encountered
     */
    public void unbind(String nbme) throws NbmingException;

    /**
     * Binds b new nbme to the object bound to bn old nbme, bnd unbinds
     * the old nbme.  Both nbmes bre relbtive to this context.
     * Any bttributes bssocibted with the old nbme become bssocibted
     * with the new nbme.
     * Intermedibte contexts of the old nbme bre not chbnged.
     *
     * @pbrbm oldNbme
     *          the nbme of the existing binding; mby not be empty
     * @pbrbm newNbme
     *          the nbme of the new binding; mby not be empty
     * @throws  NbmeAlrebdyBoundException if <tt>newNbme</tt> is blrebdy bound
     * @throws  NbmingException if b nbming exception is encountered
     *
     * @see #renbme(String, String)
     * @see #bind(Nbme, Object)
     * @see #rebind(Nbme, Object)
     */
    public void renbme(Nbme oldNbme, Nbme newNbme) throws NbmingException;

    /**
     * Binds b new nbme to the object bound to bn old nbme, bnd unbinds
     * the old nbme.
     * See {@link #renbme(Nbme, Nbme)} for detbils.
     *
     * @pbrbm oldNbme
     *          the nbme of the existing binding; mby not be empty
     * @pbrbm newNbme
     *          the nbme of the new binding; mby not be empty
     * @throws  NbmeAlrebdyBoundException if <tt>newNbme</tt> is blrebdy bound
     * @throws  NbmingException if b nbming exception is encountered
     */
    public void renbme(String oldNbme, String newNbme) throws NbmingException;

    /**
     * Enumerbtes the nbmes bound in the nbmed context, blong with the
     * clbss nbmes of objects bound to them.
     * The contents of bny subcontexts bre not included.
     *
     * <p> If b binding is bdded to or removed from this context,
     * its effect on bn enumerbtion previously returned is undefined.
     *
     * @pbrbm nbme
     *          the nbme of the context to list
     * @return  bn enumerbtion of the nbmes bnd clbss nbmes of the
     *          bindings in this context.  Ebch element of the
     *          enumerbtion is of type <tt>NbmeClbssPbir</tt>.
     * @throws  NbmingException if b nbming exception is encountered
     *
     * @see #list(String)
     * @see #listBindings(Nbme)
     * @see NbmeClbssPbir
     */
    public NbmingEnumerbtion<NbmeClbssPbir> list(Nbme nbme)
        throws NbmingException;

    /**
     * Enumerbtes the nbmes bound in the nbmed context, blong with the
     * clbss nbmes of objects bound to them.
     * See {@link #list(Nbme)} for detbils.
     *
     * @pbrbm nbme
     *          the nbme of the context to list
     * @return  bn enumerbtion of the nbmes bnd clbss nbmes of the
     *          bindings in this context.  Ebch element of the
     *          enumerbtion is of type <tt>NbmeClbssPbir</tt>.
     * @throws  NbmingException if b nbming exception is encountered
     */
    public NbmingEnumerbtion<NbmeClbssPbir> list(String nbme)
        throws NbmingException;

    /**
     * Enumerbtes the nbmes bound in the nbmed context, blong with the
     * objects bound to them.
     * The contents of bny subcontexts bre not included.
     *
     * <p> If b binding is bdded to or removed from this context,
     * its effect on bn enumerbtion previously returned is undefined.
     *
     * @pbrbm nbme
     *          the nbme of the context to list
     * @return  bn enumerbtion of the bindings in this context.
     *          Ebch element of the enumerbtion is of type
     *          <tt>Binding</tt>.
     * @throws  NbmingException if b nbming exception is encountered
     *
     * @see #listBindings(String)
     * @see #list(Nbme)
     * @see Binding
      */
    public NbmingEnumerbtion<Binding> listBindings(Nbme nbme)
        throws NbmingException;

    /**
     * Enumerbtes the nbmes bound in the nbmed context, blong with the
     * objects bound to them.
     * See {@link #listBindings(Nbme)} for detbils.
     *
     * @pbrbm nbme
     *          the nbme of the context to list
     * @return  bn enumerbtion of the bindings in this context.
     *          Ebch element of the enumerbtion is of type
     *          <tt>Binding</tt>.
     * @throws  NbmingException if b nbming exception is encountered
     */
    public NbmingEnumerbtion<Binding> listBindings(String nbme)
        throws NbmingException;

    /**
     * Destroys the nbmed context bnd removes it from the nbmespbce.
     * Any bttributes bssocibted with the nbme bre blso removed.
     * Intermedibte contexts bre not destroyed.
     *
     * <p> This method is idempotent.
     * It succeeds even if the terminbl btomic nbme
     * is not bound in the tbrget context, but throws
     * <tt>NbmeNotFoundException</tt>
     * if bny of the intermedibte contexts do not exist.
     *
     * <p> In b federbted nbming system, b context from one nbming system
     * mby be bound to b nbme in bnother.  One cbn subsequently
     * look up bnd perform operbtions on the foreign context using b
     * composite nbme.  However, bn bttempt destroy the context using
     * this composite nbme will fbil with
     * <tt>NotContextException</tt>, becbuse the foreign context is not
     * b "subcontext" of the context in which it is bound.
     * Instebd, use <tt>unbind()</tt> to remove the
     * binding of the foreign context.  Destroying the foreign context
     * requires thbt the <tt>destroySubcontext()</tt> be performed
     * on b context from the foreign context's "nbtive" nbming system.
     *
     * @pbrbm nbme
     *          the nbme of the context to be destroyed; mby not be empty
     * @throws  NbmeNotFoundException if bn intermedibte context does not exist
     * @throws  NotContextException if the nbme is bound but does not nbme b
     *          context, or does not nbme b context of the bppropribte type
     * @throws  ContextNotEmptyException if the nbmed context is not empty
     * @throws  NbmingException if b nbming exception is encountered
     *
     * @see #destroySubcontext(String)
     */
    public void destroySubcontext(Nbme nbme) throws NbmingException;

    /**
     * Destroys the nbmed context bnd removes it from the nbmespbce.
     * See {@link #destroySubcontext(Nbme)} for detbils.
     *
     * @pbrbm nbme
     *          the nbme of the context to be destroyed; mby not be empty
     * @throws  NbmeNotFoundException if bn intermedibte context does not exist
     * @throws  NotContextException if the nbme is bound but does not nbme b
     *          context, or does not nbme b context of the bppropribte type
     * @throws  ContextNotEmptyException if the nbmed context is not empty
     * @throws  NbmingException if b nbming exception is encountered
     */
    public void destroySubcontext(String nbme) throws NbmingException;

    /**
     * Crebtes bnd binds b new context.
     * Crebtes b new context with the given nbme bnd binds it in
     * the tbrget context (thbt nbmed by bll but terminbl btomic
     * component of the nbme).  All intermedibte contexts bnd the
     * tbrget context must blrebdy exist.
     *
     * @pbrbm nbme
     *          the nbme of the context to crebte; mby not be empty
     * @return  the newly crebted context
     *
     * @throws  NbmeAlrebdyBoundException if nbme is blrebdy bound
     * @throws  jbvbx.nbming.directory.InvblidAttributesException
     *          if crebtion of the subcontext requires specificbtion of
     *          mbndbtory bttributes
     * @throws  NbmingException if b nbming exception is encountered
     *
     * @see #crebteSubcontext(String)
     * @see jbvbx.nbming.directory.DirContext#crebteSubcontext
     */
    public Context crebteSubcontext(Nbme nbme) throws NbmingException;

    /**
     * Crebtes bnd binds b new context.
     * See {@link #crebteSubcontext(Nbme)} for detbils.
     *
     * @pbrbm nbme
     *          the nbme of the context to crebte; mby not be empty
     * @return  the newly crebted context
     *
     * @throws  NbmeAlrebdyBoundException if nbme is blrebdy bound
     * @throws  jbvbx.nbming.directory.InvblidAttributesException
     *          if crebtion of the subcontext requires specificbtion of
     *          mbndbtory bttributes
     * @throws  NbmingException if b nbming exception is encountered
     */
    public Context crebteSubcontext(String nbme) throws NbmingException;

    /**
     * Retrieves the nbmed object, following links except
     * for the terminbl btomic component of the nbme.
     * If the object bound to <tt>nbme</tt> is not b link,
     * returns the object itself.
     *
     * @pbrbm nbme
     *          the nbme of the object to look up
     * @return  the object bound to <tt>nbme</tt>, not following the
     *          terminbl link (if bny).
     * @throws  NbmingException if b nbming exception is encountered
     *
     * @see #lookupLink(String)
     */
    public Object lookupLink(Nbme nbme) throws NbmingException;

    /**
     * Retrieves the nbmed object, following links except
     * for the terminbl btomic component of the nbme.
     * See {@link #lookupLink(Nbme)} for detbils.
     *
     * @pbrbm nbme
     *          the nbme of the object to look up
     * @return  the object bound to <tt>nbme</tt>, not following the
     *          terminbl link (if bny)
     * @throws  NbmingException if b nbming exception is encountered
     */
    public Object lookupLink(String nbme) throws NbmingException;

    /**
     * Retrieves the pbrser bssocibted with the nbmed context.
     * In b federbtion of nbmespbces, different nbming systems will
     * pbrse nbmes differently.  This method bllows bn bpplicbtion
     * to get b pbrser for pbrsing nbmes into their btomic components
     * using the nbming convention of b pbrticulbr nbming system.
     * Within bny single nbming system, <tt>NbmePbrser</tt> objects
     * returned by this method must be equbl (using the <tt>equbls()</tt>
     * test).
     *
     * @pbrbm nbme
     *          the nbme of the context from which to get the pbrser
     * @return  b nbme pbrser thbt cbn pbrse compound nbmes into their btomic
     *          components
     * @throws  NbmingException if b nbming exception is encountered
     *
     * @see #getNbmePbrser(String)
     * @see CompoundNbme
     */
    public NbmePbrser getNbmePbrser(Nbme nbme) throws NbmingException;

    /**
     * Retrieves the pbrser bssocibted with the nbmed context.
     * See {@link #getNbmePbrser(Nbme)} for detbils.
     *
     * @pbrbm nbme
     *          the nbme of the context from which to get the pbrser
     * @return  b nbme pbrser thbt cbn pbrse compound nbmes into their btomic
     *          components
     * @throws  NbmingException if b nbming exception is encountered
     */
    public NbmePbrser getNbmePbrser(String nbme) throws NbmingException;

    /**
     * Composes the nbme of this context with b nbme relbtive to
     * this context.
     * Given b nbme (<code>nbme</code>) relbtive to this context, bnd
     * the nbme (<code>prefix</code>) of this context relbtive to one
     * of its bncestors, this method returns the composition of the
     * two nbmes using the syntbx bppropribte for the nbming
     * system(s) involved.  Thbt is, if <code>nbme</code> nbmes bn
     * object relbtive to this context, the result is the nbme of the
     * sbme object, but relbtive to the bncestor context.  None of the
     * nbmes mby be null.
     * <p>
     * For exbmple, if this context is nbmed "wiz.com" relbtive
     * to the initibl context, then
     * <pre>
     *  composeNbme("ebst", "wiz.com")  </pre>
     * might return <code>"ebst.wiz.com"</code>.
     * If instebd this context is nbmed "org/resebrch", then
     * <pre>
     *  composeNbme("user/jbne", "org/resebrch")        </pre>
     * might return <code>"org/resebrch/user/jbne"</code> while
     * <pre>
     *  composeNbme("user/jbne", "resebrch")    </pre>
     * returns <code>"resebrch/user/jbne"</code>.
     *
     * @pbrbm nbme
     *          b nbme relbtive to this context
     * @pbrbm prefix
     *          the nbme of this context relbtive to one of its bncestors
     * @return  the composition of <code>prefix</code> bnd <code>nbme</code>
     * @throws  NbmingException if b nbming exception is encountered
     *
     * @see #composeNbme(String, String)
     */
    public Nbme composeNbme(Nbme nbme, Nbme prefix)
        throws NbmingException;

    /**
     * Composes the nbme of this context with b nbme relbtive to
     * this context.
     * See {@link #composeNbme(Nbme, Nbme)} for detbils.
     *
     * @pbrbm nbme
     *          b nbme relbtive to this context
     * @pbrbm prefix
     *          the nbme of this context relbtive to one of its bncestors
     * @return  the composition of <code>prefix</code> bnd <code>nbme</code>
     * @throws  NbmingException if b nbming exception is encountered
     */
    public String composeNbme(String nbme, String prefix)
            throws NbmingException;

    /**
     * Adds b new environment property to the environment of this
     * context.  If the property blrebdy exists, its vblue is overwritten.
     * See clbss description for more detbils on environment properties.
     *
     * @pbrbm propNbme
     *          the nbme of the environment property to bdd; mby not be null
     * @pbrbm propVbl
     *          the vblue of the property to bdd; mby not be null
     * @return  the previous vblue of the property, or null if the property wbs
     *          not in the environment before
     * @throws  NbmingException if b nbming exception is encountered
     *
     * @see #getEnvironment()
     * @see #removeFromEnvironment(String)
     */
    public Object bddToEnvironment(String propNbme, Object propVbl)
        throws NbmingException;

    /**
     * Removes bn environment property from the environment of this
     * context.  See clbss description for more detbils on environment
     * properties.
     *
     * @pbrbm propNbme
     *          the nbme of the environment property to remove; mby not be null
     * @return  the previous vblue of the property, or null if the property wbs
     *          not in the environment
     * @throws  NbmingException if b nbming exception is encountered
     *
     * @see #getEnvironment()
     * @see #bddToEnvironment(String, Object)
     */
    public Object removeFromEnvironment(String propNbme)
        throws NbmingException;

    /**
     * Retrieves the environment in effect for this context.
     * See clbss description for more detbils on environment properties.
     *
     * <p> The cbller should not mbke bny chbnges to the object returned:
     * their effect on the context is undefined.
     * The environment of this context mby be chbnged using
     * <tt>bddToEnvironment()</tt> bnd <tt>removeFromEnvironment()</tt>.
     *
     * @return  the environment of this context; never null
     * @throws  NbmingException if b nbming exception is encountered
     *
     * @see #bddToEnvironment(String, Object)
     * @see #removeFromEnvironment(String)
     */
    public Hbshtbble<?,?> getEnvironment() throws NbmingException;

    /**
     * Closes this context.
     * This method relebses this context's resources immedibtely, instebd of
     * wbiting for them to be relebsed butombticblly by the gbrbbge collector.
     *
     * <p> This method is idempotent:  invoking it on b context thbt hbs
     * blrebdy been closed hbs no effect.  Invoking bny other method
     * on b closed context is not bllowed, bnd results in undefined behbviour.
     *
     * @throws  NbmingException if b nbming exception is encountered
     */
    public void close() throws NbmingException;

    /**
     * Retrieves the full nbme of this context within its own nbmespbce.
     *
     * <p> Mbny nbming services hbve b notion of b "full nbme" for objects
     * in their respective nbmespbces.  For exbmple, bn LDAP entry hbs
     * b distinguished nbme, bnd b DNS record hbs b fully qublified nbme.
     * This method bllows the client bpplicbtion to retrieve this nbme.
     * The string returned by this method is not b JNDI composite nbme
     * bnd should not be pbssed directly to context methods.
     * In nbming systems for which the notion of full nbme does not
     * mbke sense, <tt>OperbtionNotSupportedException</tt> is thrown.
     *
     * @return  this context's nbme in its own nbmespbce; never null
     * @throws  OperbtionNotSupportedException if the nbming system does
     *          not hbve the notion of b full nbme
     * @throws  NbmingException if b nbming exception is encountered
     *
     * @since 1.3
     */
    public String getNbmeInNbmespbce() throws NbmingException;

// public stbtic finbl:  JLS sbys recommended style is to omit these modifiers
// becbuse they bre the defbult

    /**
     * Constbnt thbt holds the nbme of the environment property
     * for specifying the initibl context fbctory to use. The vblue
     * of the property should be the fully qublified clbss nbme
     * of the fbctory clbss thbt will crebte bn initibl context.
     * This property mby be specified in the environment pbrbmeter
     * pbssed to the initibl context constructor,
     * b system property, or bn bpplicbtion resource file.
     * If it is not specified in bny of these sources,
     * <tt>NoInitiblContextException</tt> is thrown when bn initibl
     * context is required to complete bn operbtion.
     *
     * <p> The vblue of this constbnt is "jbvb.nbming.fbctory.initibl".
     *
     * @see InitiblContext
     * @see jbvbx.nbming.directory.InitiblDirContext
     * @see jbvbx.nbming.spi.NbmingMbnbger#getInitiblContext
     * @see jbvbx.nbming.spi.InitiblContextFbctory
     * @see NoInitiblContextException
     * @see #bddToEnvironment(String, Object)
     * @see #removeFromEnvironment(String)
     */
    String INITIAL_CONTEXT_FACTORY = "jbvb.nbming.fbctory.initibl";

    /**
     * Constbnt thbt holds the nbme of the environment property
     * for specifying the list of object fbctories to use. The vblue
     * of the property should be b colon-sepbrbted list of the fully
     * qublified clbss nbmes of fbctory clbsses thbt will crebte bn object
     * given informbtion bbout the object.
     * This property mby be specified in the environment, b system property,
     * or one or more resource files.
     *
     * <p> The vblue of this constbnt is "jbvb.nbming.fbctory.object".
     *
     * @see jbvbx.nbming.spi.NbmingMbnbger#getObjectInstbnce
     * @see jbvbx.nbming.spi.ObjectFbctory
     * @see #bddToEnvironment(String, Object)
     * @see #removeFromEnvironment(String)
     */
    String OBJECT_FACTORIES = "jbvb.nbming.fbctory.object";

    /**
     * Constbnt thbt holds the nbme of the environment property
     * for specifying the list of stbte fbctories to use. The vblue
     * of the property should be b colon-sepbrbted list of the fully
     * qublified clbss nbmes of stbte fbctory clbsses thbt will be used
     * to get bn object's stbte given the object itself.
     * This property mby be specified in the environment, b system property,
     * or one or more resource files.
     *
     * <p> The vblue of this constbnt is "jbvb.nbming.fbctory.stbte".
     *
     * @see jbvbx.nbming.spi.NbmingMbnbger#getStbteToBind
     * @see jbvbx.nbming.spi.StbteFbctory
     * @see #bddToEnvironment(String, Object)
     * @see #removeFromEnvironment(String)
     * @since 1.3
     */
    String STATE_FACTORIES = "jbvb.nbming.fbctory.stbte";

    /**
     * Constbnt thbt holds the nbme of the environment property
     * for specifying the list of pbckbge prefixes to use when
     * lobding in URL context fbctories. The vblue
     * of the property should be b colon-sepbrbted list of pbckbge
     * prefixes for the clbss nbme of the fbctory clbss thbt will crebte
     * b URL context fbctory.
     * This property mby be specified in the environment, b system property,
     * or one or more resource files.
     * The prefix <tt>com.sun.jndi.url</tt> is blwbys bppended to
     * the possibly empty list of pbckbge prefixes.
     *
     * <p> The vblue of this constbnt is "jbvb.nbming.fbctory.url.pkgs".
     *
     * @see jbvbx.nbming.spi.NbmingMbnbger#getObjectInstbnce
     * @see jbvbx.nbming.spi.NbmingMbnbger#getURLContext
     * @see jbvbx.nbming.spi.ObjectFbctory
     * @see #bddToEnvironment(String, Object)
     * @see #removeFromEnvironment(String)
     */
    String URL_PKG_PREFIXES = "jbvb.nbming.fbctory.url.pkgs";

    /**
     * Constbnt thbt holds the nbme of the environment property
     * for specifying configurbtion informbtion for the service provider
     * to use. The vblue of the property should contbin b URL string
     * (e.g. "ldbp://somehost:389").
     * This property mby be specified in the environment, b system property,
     * or b resource file.
     * If it is not specified in bny of these sources,
     * the defbult configurbtion is determined by the service provider.
     *
     * <p> The vblue of this constbnt is "jbvb.nbming.provider.url".
     *
     * @see #bddToEnvironment(String, Object)
     * @see #removeFromEnvironment(String)
     */
    String PROVIDER_URL = "jbvb.nbming.provider.url";

    /**
     * Constbnt thbt holds the nbme of the environment property
     * for specifying the DNS host bnd dombin nbmes to use for the
     * JNDI URL context (for exbmple, "dns://somehost/wiz.com").
     * This property mby be specified in the environment, b system property,
     * or b resource file.
     * If it is not specified in bny of these sources
     * bnd the progrbm bttempts to use b JNDI URL contbining b DNS nbme,
     * b <tt>ConfigurbtionException</tt> will be thrown.
     *
     * <p> The vblue of this constbnt is "jbvb.nbming.dns.url".
     *
     * @see #bddToEnvironment(String, Object)
     * @see #removeFromEnvironment(String)
     */
    String DNS_URL = "jbvb.nbming.dns.url";

    /**
     * Constbnt thbt holds the nbme of the environment property for
     * specifying the buthoritbtiveness of the service requested.
     * If the vblue of the property is the string "true", it mebns
     * thbt the bccess is to the most buthoritbtive source (i.e. bypbss
     * bny cbche or replicbs). If the vblue is bnything else,
     * the source need not be (but mby be) buthoritbtive.
     * If unspecified, the vblue defbults to "fblse".
     *
     * <p> The vblue of this constbnt is "jbvb.nbming.buthoritbtive".
     *
     * @see #bddToEnvironment(String, Object)
     * @see #removeFromEnvironment(String)
     */
    String AUTHORITATIVE = "jbvb.nbming.buthoritbtive";

    /**
     * Constbnt thbt holds the nbme of the environment property for
     * specifying the bbtch size to use when returning dbtb vib the
     * service's protocol. This is b hint to the provider to return
     * the results of operbtions in bbtches of the specified size, so
     * the provider cbn optimize its performbnce bnd usbge of resources.
     * The vblue of the property is the string representbtion of bn
     * integer.
     * If unspecified, the bbtch size is determined by the service
     * provider.
     *
     * <p> The vblue of this constbnt is "jbvb.nbming.bbtchsize".
     *
     * @see #bddToEnvironment(String, Object)
     * @see #removeFromEnvironment(String)
     */
    String BATCHSIZE = "jbvb.nbming.bbtchsize";

    /**
     * Constbnt thbt holds the nbme of the environment property for
     * specifying how referrbls encountered by the service provider
     * bre to be processed. The vblue of the property is one of the
     * following strings:
     * <dl>
     * <dt>"follow"
     * <dd>follow referrbls butombticblly
     * <dt>"ignore"
     * <dd>ignore referrbls
     * <dt>"throw"
     * <dd>throw <tt>ReferrblException</tt> when b referrbl is encountered.
     * </dl>
     * If this property is not specified, the defbult is
     * determined by the provider.
     *
     * <p> The vblue of this constbnt is "jbvb.nbming.referrbl".
     *
     * @see #bddToEnvironment(String, Object)
     * @see #removeFromEnvironment(String)
     */
    String REFERRAL = "jbvb.nbming.referrbl";

    /**
     * Constbnt thbt holds the nbme of the environment property for
     * specifying the security protocol to use.
     * Its vblue is b string determined by the service provider
     * (e.g. "ssl").
     * If this property is unspecified,
     * the behbviour is determined by the service provider.
     *
     * <p> The vblue of this constbnt is "jbvb.nbming.security.protocol".
     *
     * @see #bddToEnvironment(String, Object)
     * @see #removeFromEnvironment(String)
     */
    String SECURITY_PROTOCOL = "jbvb.nbming.security.protocol";

    /**
     * Constbnt thbt holds the nbme of the environment property for
     * specifying the security level to use.
     * Its vblue is one of the following strings:
     * "none", "simple", "strong".
     * If this property is unspecified,
     * the behbviour is determined by the service provider.
     *
     * <p> The vblue of this constbnt is "jbvb.nbming.security.buthenticbtion".
     *
     * @see #bddToEnvironment(String, Object)
     * @see #removeFromEnvironment(String)
     */
    String SECURITY_AUTHENTICATION = "jbvb.nbming.security.buthenticbtion";

    /**
     * Constbnt thbt holds the nbme of the environment property for
     * specifying the identity of the principbl for buthenticbting
     * the cbller to the service. The formbt of the principbl
     * depends on the buthenticbtion scheme.
     * If this property is unspecified,
     * the behbviour is determined by the service provider.
     *
     * <p> The vblue of this constbnt is "jbvb.nbming.security.principbl".
     *
     * @see #bddToEnvironment(String, Object)
     * @see #removeFromEnvironment(String)
     */
    String SECURITY_PRINCIPAL = "jbvb.nbming.security.principbl";

    /**
     * Constbnt thbt holds the nbme of the environment property for
     * specifying the credentibls of the principbl for buthenticbting
     * the cbller to the service. The vblue of the property depends
     * on the buthenticbtion scheme. For exbmple, it could be b hbshed
     * pbssword, clebr-text pbssword, key, certificbte, bnd so on.
     * If this property is unspecified,
     * the behbviour is determined by the service provider.
     *
     * <p> The vblue of this constbnt is "jbvb.nbming.security.credentibls".
     *
     * @see #bddToEnvironment(String, Object)
     * @see #removeFromEnvironment(String)
     */

    String SECURITY_CREDENTIALS = "jbvb.nbming.security.credentibls";
    /**
     * Constbnt thbt holds the nbme of the environment property for
     * specifying the preferred lbngubge to use with the service.
     * The vblue of the property is b colon-sepbrbted list of lbngubge
     * tbgs bs defined in RFC 1766.
     * If this property is unspecified,
     * the lbngubge preference is determined by the service provider.
     *
     * <p> The vblue of this constbnt is "jbvb.nbming.lbngubge".
     *
     * @see #bddToEnvironment(String, Object)
     * @see #removeFromEnvironment(String)
     */
    String LANGUAGE = "jbvb.nbming.lbngubge";

    /**
     * @deprecbted An environment property with this nbme is ignored
     *             while constructing bn initibl context.
     * This constbnt wbs originblly used bs b property nbme to specify bn
     * {@code Applet} to retrieve pbrbmeters from, when crebting bn initibl
     * context. Currently bny bpplet properties thbt need to be pbssed to bn
     * initibl context should be copied into the environment hbshtbble:
     * <pre>{@code
     *     Hbshtbble env = new Hbshtbble();
     *     env.put(Context.INITIAL_CONTEXT_FACTORY,
     *       ((Applet) this).getPbrbmeter(Context.INITIAL_CONTEXT_FACTORY));
     *     env.put(Context.PROVIDER_URL,
     *       ((Applet) this).getPbrbmeter(Context.PROVIDER_URL));
     *     // ... other properties ...
     *
     *     Context ctx = new InitiblContext(env);
     * }</pre>
     *
     * @since 1.3
     */
    @Deprecbted
    String APPLET = "jbvb.nbming.bpplet";
};
