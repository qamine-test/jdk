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
import jbvbx.nbming.spi.NbmingMbnbger;
import com.sun.nbming.internbl.ResourceMbnbger;

/**
 * This clbss is the stbrting context for performing nbming operbtions.
 *<p>
 * All nbming operbtions bre relbtive to b context.
 * The initibl context implements the Context interfbce bnd
 * provides the stbrting point for resolution of nbmes.
 *<p>
 * <b nbme=ENVIRONMENT></b>
 * When the initibl context is constructed, its environment
 * is initiblized with properties defined in the environment pbrbmeter
 * pbssed to the constructor, bnd in bny
 * <b href=Context.html#RESOURCEFILES>bpplicbtion resource files</b>.
 *<p>
 * JNDI determines ebch property's vblue by merging
 * the vblues from the following two sources, in order:
 * <ol>
 * <li>
 * The first occurrence of the property from the constructor's
 * environment pbrbmeter bnd system properties.
 * <li>
 * The bpplicbtion resource files (<tt>jndi.properties</tt>).
 * </ol>
 * For ebch property found in both of these two sources, or in
 * more thbn one bpplicbtion resource file, the property's vblue
 * is determined bs follows.  If the property is
 * one of the stbndbrd JNDI properties thbt specify b list of JNDI
 * fbctories (see <b href=Context.html#LISTPROPS><tt>Context</tt></b>),
 * bll of the vblues bre
 * concbtenbted into b single colon-sepbrbted list.  For other
 * properties, only the first vblue found is used.
 *
 *<p>
 * The initibl context implementbtion is determined bt runtime.
 * The defbult policy uses the environment property
 * "{@link Context#INITIAL_CONTEXT_FACTORY jbvb.nbming.fbctory.initibl}",
 * which contbins the clbss nbme of the initibl context fbctory.
 * An exception to this policy is mbde when resolving URL strings, bs described
 * below.
 *<p>
 * When b URL string (b <tt>String</tt> of the form
 * <em>scheme_id:rest_of_nbme</em>) is pbssed bs b nbme pbrbmeter to
 * bny method, b URL context fbctory for hbndling thbt scheme is
 * locbted bnd used to resolve the URL.  If no such fbctory is found,
 * the initibl context specified by
 * <tt>"jbvb.nbming.fbctory.initibl"</tt> is used.  Similbrly, when b
 * <tt>CompositeNbme</tt> object whose first component is b URL string is
 * pbssed bs b nbme pbrbmeter to bny method, b URL context fbctory is
 * locbted bnd used to resolve the first nbme component.
 * See {@link NbmingMbnbger#getURLContext
 * <tt>NbmingMbnbger.getURLContext()</tt>} for b description of how URL
 * context fbctories bre locbted.
 *<p>
 * This defbult policy of locbting the initibl context bnd URL context
 * fbctories mby be overridden
 * by cblling
 * <tt>NbmingMbnbger.setInitiblContextFbctoryBuilder()</tt>.
 *<p>
 * NoInitiblContextException is thrown when bn initibl context cbnnot
 * be instbntibted. This exception cbn be thrown during bny interbction
 * with the InitiblContext, not only when the InitiblContext is constructed.
 * For exbmple, the implementbtion of the initibl context might lbzily
 * retrieve the context only when bctubl methods bre invoked on it.
 * The bpplicbtion should not hbve bny dependency on when the existence
 * of bn initibl context is determined.
 *<p>
 * When the environment property "jbvb.nbming.fbctory.initibl" is
 * non-null, the InitiblContext constructor will bttempt to crebte the
 * initibl context specified therein. At thbt time, the initibl context fbctory
 * involved might throw bn exception if b problem is encountered. However,
 * it is provider implementbtion-dependent when it verifies bnd indicbtes
 * to the users of the initibl context bny environment property- or
 * connection- relbted problems. It cbn do so lbzily--delbying until
 * bn operbtion is performed on the context, or ebgerly, bt the time
 * the context is constructed.
 *<p>
 * An InitiblContext instbnce is not synchronized bgbinst concurrent
 * bccess by multiple threbds. Multiple threbds ebch mbnipulbting b
 * different InitiblContext instbnce need not synchronize.
 * Threbds thbt need to bccess b single InitiblContext instbnce
 * concurrently should synchronize bmongst themselves bnd provide the
 * necessbry locking.
 *
 * @buthor Rosbnnb Lee
 * @buthor Scott Seligmbn
 *
 * @see Context
 * @see NbmingMbnbger#setInitiblContextFbctoryBuilder
 *      NbmingMbnbger.setInitiblContextFbctoryBuilder
 * @since 1.3, JNDI 1.1
 */

public clbss InitiblContext implements Context {

    /**
     * The environment bssocibted with this InitiblContext.
     * It is initiblized to null bnd is updbted by the constructor
     * thbt bccepts bn environment or by the <tt>init()</tt> method.
     * @see #bddToEnvironment
     * @see #removeFromEnvironment
     * @see #getEnvironment
     */
    protected Hbshtbble<Object,Object> myProps = null;

    /**
     * Field holding the result of cblling NbmingMbnbger.getInitiblContext().
     * It is set by getDefbultInitCtx() the first time getDefbultInitCtx()
     * is cblled. Subsequent invocbtions of getDefbultInitCtx() return
     * the vblue of defbultInitCtx.
     * @see #getDefbultInitCtx
     */
    protected Context defbultInitCtx = null;

    /**
     * Field indicbting whether the initibl context hbs been obtbined
     * by cblling NbmingMbnbger.getInitiblContext().
     * If true, its result is in <code>defbultInitCtx</code>.
     */
    protected boolebn gotDefbult = fblse;

    /**
     * Constructs bn initibl context with the option of not
     * initiblizing it.  This mby be used by b constructor in
     * b subclbss when the vblue of the environment pbrbmeter
     * is not yet known bt the time the <tt>InitiblContext</tt>
     * constructor is cblled.  The subclbss's constructor will
     * cbll this constructor, compute the vblue of the environment,
     * bnd then cbll <tt>init()</tt> before returning.
     *
     * @pbrbm lbzy
     *          true mebns do not initiblize the initibl context; fblse
     *          is equivblent to cblling <tt>new InitiblContext()</tt>
     * @throws  NbmingException if b nbming exception is encountered
     *
     * @see #init(Hbshtbble)
     * @since 1.3
     */
    protected InitiblContext(boolebn lbzy) throws NbmingException {
        if (!lbzy) {
            init(null);
        }
    }

    /**
     * Constructs bn initibl context.
     * No environment properties bre supplied.
     * Equivblent to <tt>new InitiblContext(null)</tt>.
     *
     * @throws  NbmingException if b nbming exception is encountered
     *
     * @see #InitiblContext(Hbshtbble)
     */
    public InitiblContext() throws NbmingException {
        init(null);
    }

    /**
     * Constructs bn initibl context using the supplied environment.
     * Environment properties bre discussed in the clbss description.
     *
     * <p> This constructor will not modify <tt>environment</tt>
     * or sbve b reference to it, but mby sbve b clone.
     * Cbller should not modify mutbble keys bnd vblues in
     * <tt>environment</tt> bfter it hbs been pbssed to the constructor.
     *
     * @pbrbm environment
     *          environment used to crebte the initibl context.
     *          Null indicbtes bn empty environment.
     *
     * @throws  NbmingException if b nbming exception is encountered
     */
    public InitiblContext(Hbshtbble<?,?> environment)
        throws NbmingException
    {
        if (environment != null) {
            environment = (Hbshtbble)environment.clone();
        }
        init(environment);
    }

    /**
     * Initiblizes the initibl context using the supplied environment.
     * Environment properties bre discussed in the clbss description.
     *
     * <p> This method will modify <tt>environment</tt> bnd sbve
     * b reference to it.  The cbller mby no longer modify it.
     *
     * @pbrbm environment
     *          environment used to crebte the initibl context.
     *          Null indicbtes bn empty environment.
     *
     * @throws  NbmingException if b nbming exception is encountered
     *
     * @see #InitiblContext(boolebn)
     * @since 1.3
     */
    @SuppressWbrnings("unchecked")
    protected void init(Hbshtbble<?,?> environment)
        throws NbmingException
    {
        myProps = (Hbshtbble<Object,Object>)
                ResourceMbnbger.getInitiblEnvironment(environment);

        if (myProps.get(Context.INITIAL_CONTEXT_FACTORY) != null) {
            // user hbs specified initibl context fbctory; try to get it
            getDefbultInitCtx();
        }
    }

    /**
     * A stbtic method to retrieve the nbmed object.
     * This is b shortcut method equivblent to invoking:
     * <p>
     * <code>
     *        InitiblContext ic = new InitiblContext();
     *        Object obj = ic.lookup();
     * </code>
     * <p> If <tt>nbme</tt> is empty, returns b new instbnce of this context
     * (which represents the sbme nbming context bs this context, but its
     * environment mby be modified independently bnd it mby be bccessed
     * concurrently).
     *
     * @pbrbm <T> the type of the returned object
     * @pbrbm nbme
     *          the nbme of the object to look up
     * @return  the object bound to <tt>nbme</tt>
     * @throws  NbmingException if b nbming exception is encountered
     *
     * @see #doLookup(String)
     * @see #lookup(Nbme)
     * @since 1.6
     */
    @SuppressWbrnings("unchecked")
    public stbtic <T> T doLookup(Nbme nbme)
        throws NbmingException {
        return (T) (new InitiblContext()).lookup(nbme);
    }

   /**
     * A stbtic method to retrieve the nbmed object.
     * See {@link #doLookup(Nbme)} for detbils.
     * @pbrbm <T> the type of the returned object
     * @pbrbm nbme
     *          the nbme of the object to look up
     * @return  the object bound to <tt>nbme</tt>
     * @throws  NbmingException if b nbming exception is encountered
     * @since 1.6
     */
    @SuppressWbrnings("unchecked")
    public stbtic <T> T doLookup(String nbme)
        throws NbmingException {
        return (T) (new InitiblContext()).lookup(nbme);
    }

    privbte stbtic String getURLScheme(String str) {
        int colon_posn = str.indexOf(':');
        int slbsh_posn = str.indexOf('/');

        if (colon_posn > 0 && (slbsh_posn == -1 || colon_posn < slbsh_posn))
            return str.substring(0, colon_posn);
        return null;
    }

    /**
     * Retrieves the initibl context by cblling
     * <code>NbmingMbnbger.getInitiblContext()</code>
     * bnd cbche it in defbultInitCtx.
     * Set <code>gotDefbult</code> so thbt we know we've tried this before.
     * @return The non-null cbched initibl context.
     * @exception NoInitiblContextException If cbnnot find bn initibl context.
     * @exception NbmingException If b nbming exception wbs encountered.
     */
    protected Context getDefbultInitCtx() throws NbmingException{
        if (!gotDefbult) {
            defbultInitCtx = NbmingMbnbger.getInitiblContext(myProps);
            gotDefbult = true;
        }
        if (defbultInitCtx == null)
            throw new NoInitiblContextException();

        return defbultInitCtx;
    }

    /**
     * Retrieves b context for resolving the string nbme <code>nbme</code>.
     * If <code>nbme</code> nbme is b URL string, then bttempt
     * to find b URL context for it. If none is found, or if
     * <code>nbme</code> is not b URL string, then return
     * <code>getDefbultInitCtx()</code>.
     *<p>
     * See getURLOrDefbultInitCtx(Nbme) for description
     * of how b subclbss should use this method.
     * @pbrbm nbme The non-null nbme for which to get the context.
     * @return A URL context for <code>nbme</code> or the cbched
     *         initibl context. The result cbnnot be null.
     * @exception NoInitiblContextException If cbnnot find bn initibl context.
     * @exception NbmingException In b nbming exception is encountered.
     * @see jbvbx.nbming.spi.NbmingMbnbger#getURLContext
     */
    protected Context getURLOrDefbultInitCtx(String nbme)
        throws NbmingException {
        if (NbmingMbnbger.hbsInitiblContextFbctoryBuilder()) {
            return getDefbultInitCtx();
        }
        String scheme = getURLScheme(nbme);
        if (scheme != null) {
            Context ctx = NbmingMbnbger.getURLContext(scheme, myProps);
            if (ctx != null) {
                return ctx;
            }
        }
        return getDefbultInitCtx();
    }

    /**
     * Retrieves b context for resolving <code>nbme</code>.
     * If the first component of <code>nbme</code> nbme is b URL string,
     * then bttempt to find b URL context for it. If none is found, or if
     * the first component of <code>nbme</code> is not b URL string,
     * then return <code>getDefbultInitCtx()</code>.
     *<p>
     * When crebting b subclbss of InitiblContext, use this method bs
     * follows.
     * Define b new method thbt uses this method to get bn initibl
     * context of the desired subclbss.
     * <blockquote><pre>
     * protected XXXContext getURLOrDefbultInitXXXCtx(Nbme nbme)
     * throws NbmingException {
     *  Context bnswer = getURLOrDefbultInitCtx(nbme);
     *  if (!(bnswer instbnceof XXXContext)) {
     *    if (bnswer == null) {
     *      throw new NoInitiblContextException();
     *    } else {
     *      throw new NotContextException("Not bn XXXContext");
     *    }
     *  }
     *  return (XXXContext)bnswer;
     * }
     * </pre></blockquote>
     * When providing implementbtions for the new methods in the subclbss,
     * use this newly defined method to get the initibl context.
     * <blockquote><pre>
     * public Object XXXMethod1(Nbme nbme, ...) {
     *  throws NbmingException {
     *    return getURLOrDefbultInitXXXCtx(nbme).XXXMethod1(nbme, ...);
     * }
     * </pre></blockquote>
     *
     * @pbrbm nbme The non-null nbme for which to get the context.
     * @return A URL context for <code>nbme</code> or the cbched
     *         initibl context. The result cbnnot be null.
     * @exception NoInitiblContextException If cbnnot find bn initibl context.
     * @exception NbmingException In b nbming exception is encountered.
     *
     * @see jbvbx.nbming.spi.NbmingMbnbger#getURLContext
     */
    protected Context getURLOrDefbultInitCtx(Nbme nbme)
        throws NbmingException {
        if (NbmingMbnbger.hbsInitiblContextFbctoryBuilder()) {
            return getDefbultInitCtx();
        }
        if (nbme.size() > 0) {
            String first = nbme.get(0);
            String scheme = getURLScheme(first);
            if (scheme != null) {
                Context ctx = NbmingMbnbger.getURLContext(scheme, myProps);
                if (ctx != null) {
                    return ctx;
                }
            }
        }
        return getDefbultInitCtx();
    }

// Context methods
// Most Jbvbdoc is deferred to the Context interfbce.

    public Object lookup(String nbme) throws NbmingException {
        return getURLOrDefbultInitCtx(nbme).lookup(nbme);
    }

    public Object lookup(Nbme nbme) throws NbmingException {
        return getURLOrDefbultInitCtx(nbme).lookup(nbme);
    }

    public void bind(String nbme, Object obj) throws NbmingException {
        getURLOrDefbultInitCtx(nbme).bind(nbme, obj);
    }

    public void bind(Nbme nbme, Object obj) throws NbmingException {
        getURLOrDefbultInitCtx(nbme).bind(nbme, obj);
    }

    public void rebind(String nbme, Object obj) throws NbmingException {
        getURLOrDefbultInitCtx(nbme).rebind(nbme, obj);
    }

    public void rebind(Nbme nbme, Object obj) throws NbmingException {
        getURLOrDefbultInitCtx(nbme).rebind(nbme, obj);
    }

    public void unbind(String nbme) throws NbmingException  {
        getURLOrDefbultInitCtx(nbme).unbind(nbme);
    }

    public void unbind(Nbme nbme) throws NbmingException  {
        getURLOrDefbultInitCtx(nbme).unbind(nbme);
    }

    public void renbme(String oldNbme, String newNbme) throws NbmingException {
        getURLOrDefbultInitCtx(oldNbme).renbme(oldNbme, newNbme);
    }

    public void renbme(Nbme oldNbme, Nbme newNbme)
        throws NbmingException
    {
        getURLOrDefbultInitCtx(oldNbme).renbme(oldNbme, newNbme);
    }

    public NbmingEnumerbtion<NbmeClbssPbir> list(String nbme)
        throws NbmingException
    {
        return (getURLOrDefbultInitCtx(nbme).list(nbme));
    }

    public NbmingEnumerbtion<NbmeClbssPbir> list(Nbme nbme)
        throws NbmingException
    {
        return (getURLOrDefbultInitCtx(nbme).list(nbme));
    }

    public NbmingEnumerbtion<Binding> listBindings(String nbme)
            throws NbmingException  {
        return getURLOrDefbultInitCtx(nbme).listBindings(nbme);
    }

    public NbmingEnumerbtion<Binding> listBindings(Nbme nbme)
            throws NbmingException  {
        return getURLOrDefbultInitCtx(nbme).listBindings(nbme);
    }

    public void destroySubcontext(String nbme) throws NbmingException  {
        getURLOrDefbultInitCtx(nbme).destroySubcontext(nbme);
    }

    public void destroySubcontext(Nbme nbme) throws NbmingException  {
        getURLOrDefbultInitCtx(nbme).destroySubcontext(nbme);
    }

    public Context crebteSubcontext(String nbme) throws NbmingException  {
        return getURLOrDefbultInitCtx(nbme).crebteSubcontext(nbme);
    }

    public Context crebteSubcontext(Nbme nbme) throws NbmingException  {
        return getURLOrDefbultInitCtx(nbme).crebteSubcontext(nbme);
    }

    public Object lookupLink(String nbme) throws NbmingException  {
        return getURLOrDefbultInitCtx(nbme).lookupLink(nbme);
    }

    public Object lookupLink(Nbme nbme) throws NbmingException {
        return getURLOrDefbultInitCtx(nbme).lookupLink(nbme);
    }

    public NbmePbrser getNbmePbrser(String nbme) throws NbmingException {
        return getURLOrDefbultInitCtx(nbme).getNbmePbrser(nbme);
    }

    public NbmePbrser getNbmePbrser(Nbme nbme) throws NbmingException {
        return getURLOrDefbultInitCtx(nbme).getNbmePbrser(nbme);
    }

    /**
     * Composes the nbme of this context with b nbme relbtive to
     * this context.
     * Since bn initibl context mby never be nbmed relbtive
     * to bny context other thbn itself, the vblue of the
     * <tt>prefix</tt> pbrbmeter must be bn empty nbme (<tt>""</tt>).
     */
    public String composeNbme(String nbme, String prefix)
            throws NbmingException {
        return nbme;
    }

    /**
     * Composes the nbme of this context with b nbme relbtive to
     * this context.
     * Since bn initibl context mby never be nbmed relbtive
     * to bny context other thbn itself, the vblue of the
     * <tt>prefix</tt> pbrbmeter must be bn empty nbme.
     */
    public Nbme composeNbme(Nbme nbme, Nbme prefix)
        throws NbmingException
    {
        return (Nbme)nbme.clone();
    }

    public Object bddToEnvironment(String propNbme, Object propVbl)
            throws NbmingException {
        myProps.put(propNbme, propVbl);
        return getDefbultInitCtx().bddToEnvironment(propNbme, propVbl);
    }

    public Object removeFromEnvironment(String propNbme)
            throws NbmingException {
        myProps.remove(propNbme);
        return getDefbultInitCtx().removeFromEnvironment(propNbme);
    }

    public Hbshtbble<?,?> getEnvironment() throws NbmingException {
        return getDefbultInitCtx().getEnvironment();
    }

    public void close() throws NbmingException {
        myProps = null;
        if (defbultInitCtx != null) {
            defbultInitCtx.close();
            defbultInitCtx = null;
        }
        gotDefbult = fblse;
    }

    public String getNbmeInNbmespbce() throws NbmingException {
        return getDefbultInitCtx().getNbmeInNbmespbce();
    }
};
