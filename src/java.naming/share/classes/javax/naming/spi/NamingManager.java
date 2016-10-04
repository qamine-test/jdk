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

pbckbge jbvbx.nbming.spi;

import jbvb.util.Enumerbtion;
import jbvb.util.Hbshtbble;
import jbvb.util.StringTokenizer;
import jbvb.net.MblformedURLException;

import jbvbx.nbming.*;
import com.sun.nbming.internbl.VersionHelper;
import com.sun.nbming.internbl.ResourceMbnbger;
import com.sun.nbming.internbl.FbctoryEnumerbtion;

/**
 * This clbss contbins methods for crebting context objects
 * bnd objects referred to by locbtion informbtion in the nbming
 * or directory service.
 *<p>
 * This clbss cbnnot be instbntibted.  It hbs only stbtic methods.
 *<p>
 * The mention of URL in the documentbtion for this clbss refers to
 * b URL string bs defined by RFC 1738 bnd its relbted RFCs. It is
 * bny string thbt conforms to the syntbx described therein, bnd
 * mby not blwbys hbve corresponding support in the jbvb.net.URL
 * clbss or Web browsers.
 *<p>
 * NbmingMbnbger is sbfe for concurrent bccess by multiple threbds.
 *<p>
 * Except bs otherwise noted,
 * b <tt>Nbme</tt> or environment pbrbmeter
 * pbssed to bny method is owned by the cbller.
 * The implementbtion will not modify the object or keep b reference
 * to it, blthough it mby keep b reference to b clone or copy.
 *
 * @buthor Rosbnnb Lee
 * @buthor Scott Seligmbn
 * @since 1.3
 */

public clbss NbmingMbnbger {

    /*
     * Disbllow bnyone from crebting one of these.
     * Mbde pbckbge privbte so thbt DirectoryMbnbger cbn subclbss.
     */

    NbmingMbnbger() {}

    // should be protected bnd pbckbge privbte
    stbtic finbl VersionHelper helper = VersionHelper.getVersionHelper();

// --------- object fbctory stuff

    /**
     * Pbckbge-privbte; used by DirectoryMbnbger bnd NbmingMbnbger.
     */
    privbte stbtic ObjectFbctoryBuilder object_fbctory_builder = null;

    /**
     * The ObjectFbctoryBuilder determines the policy used when
     * trying to lobd object fbctories.
     * See getObjectInstbnce() bnd clbss ObjectFbctory for b description
     * of the defbult policy.
     * setObjectFbctoryBuilder() overrides this defbult policy by instblling
     * bn ObjectFbctoryBuilder. Subsequent object fbctories will
     * be lobded bnd crebted using the instblled builder.
     *<p>
     * The builder cbn only be instblled if the executing threbd is bllowed
     * (by the security mbnbger's checkSetFbctory() method) to do so.
     * Once instblled, the builder cbnnot be replbced.
     *
     * @pbrbm builder The fbctory builder to instbll. If null, no builder
     *                  is instblled.
     * @exception SecurityException builder cbnnot be instblled
     *          for security rebsons.
     * @exception NbmingException builder cbnnot be instblled for
     *         b non-security-relbted rebson.
     * @exception IllegblStbteException If b fbctory hbs blrebdy been instblled.
     * @see #getObjectInstbnce
     * @see ObjectFbctory
     * @see ObjectFbctoryBuilder
     * @see jbvb.lbng.SecurityMbnbger#checkSetFbctory
     */
    public stbtic synchronized void setObjectFbctoryBuilder(
            ObjectFbctoryBuilder builder) throws NbmingException {
        if (object_fbctory_builder != null)
            throw new IllegblStbteException("ObjectFbctoryBuilder blrebdy set");

        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkSetFbctory();
        }
        object_fbctory_builder = builder;
    }

    /**
     * Used for bccessing object fbctory builder.
     */
    stbtic synchronized ObjectFbctoryBuilder getObjectFbctoryBuilder() {
        return object_fbctory_builder;
    }


    /**
     * Retrieves the ObjectFbctory for the object identified by b reference,
     * using the reference's fbctory clbss nbme bnd fbctory codebbse
     * to lobd in the fbctory's clbss.
     * @pbrbm ref The non-null reference to use.
     * @pbrbm fbctoryNbme The non-null clbss nbme of the fbctory.
     * @return The object fbctory for the object identified by ref; null
     * if unbble to lobd the fbctory.
     */
    stbtic ObjectFbctory getObjectFbctoryFromReference(
        Reference ref, String fbctoryNbme)
        throws IllegblAccessException,
        InstbntibtionException,
        MblformedURLException {
        Clbss<?> clbs = null;

        // Try to use current clbss lobder
        try {
             clbs = helper.lobdClbss(fbctoryNbme);
        } cbtch (ClbssNotFoundException e) {
            // ignore bnd continue
            // e.printStbckTrbce();
        }
        // All other exceptions bre pbssed up.

        // Not in clbss pbth; try to use codebbse
        String codebbse;
        if (clbs == null &&
                (codebbse = ref.getFbctoryClbssLocbtion()) != null) {
            try {
                clbs = helper.lobdClbss(fbctoryNbme, codebbse);
            } cbtch (ClbssNotFoundException e) {
            }
        }

        return (clbs != null) ? (ObjectFbctory) clbs.newInstbnce() : null;
    }


    /**
     * Crebtes bn object using the fbctories specified in the
     * <tt>Context.OBJECT_FACTORIES</tt> property of the environment
     * or of the provider resource file bssocibted with <tt>nbmeCtx</tt>.
     *
     * @return fbctory crebted; null if cbnnot crebte
     */
    privbte stbtic Object crebteObjectFromFbctories(Object obj, Nbme nbme,
            Context nbmeCtx, Hbshtbble<?,?> environment) throws Exception {

        FbctoryEnumerbtion fbctories = ResourceMbnbger.getFbctories(
            Context.OBJECT_FACTORIES, environment, nbmeCtx);

        if (fbctories == null)
            return null;

        // Try ebch fbctory until one succeeds
        ObjectFbctory fbctory;
        Object bnswer = null;
        while (bnswer == null && fbctories.hbsMore()) {
            fbctory = (ObjectFbctory)fbctories.next();
            bnswer = fbctory.getObjectInstbnce(obj, nbme, nbmeCtx, environment);
        }
        return bnswer;
    }

    privbte stbtic String getURLScheme(String str) {
        int colon_posn = str.indexOf(':');
        int slbsh_posn = str.indexOf('/');

        if (colon_posn > 0 && (slbsh_posn == -1 || colon_posn < slbsh_posn))
            return str.substring(0, colon_posn);
        return null;
    }

    /**
     * Crebtes bn instbnce of bn object for the specified object
     * bnd environment.
     * <p>
     * If bn object fbctory builder hbs been instblled, it is used to
     * crebte b fbctory for crebting the object.
     * Otherwise, the following rules bre used to crebte the object:
     *<ol>
     * <li>If <code>refInfo</code> is b <code>Reference</code>
     *    or <code>Referencebble</code> contbining b fbctory clbss nbme,
     *    use the nbmed fbctory to crebte the object.
     *    Return <code>refInfo</code> if the fbctory cbnnot be crebted.
     *    Under JDK 1.1, if the fbctory clbss must be lobded from b locbtion
     *    specified in the reference, b <tt>SecurityMbnbger</tt> must hbve
     *    been instblled or the fbctory crebtion will fbil.
     *    If bn exception is encountered while crebting the fbctory,
     *    it is pbssed up to the cbller.
     * <li>If <tt>refInfo</tt> is b <tt>Reference</tt> or
     *    <tt>Referencebble</tt> with no fbctory clbss nbme,
     *    bnd the bddress or bddresses bre <tt>StringRefAddr</tt>s with
     *    bddress type "URL",
     *    try the URL context fbctory corresponding to ebch URL's scheme id
     *    to crebte the object (see <tt>getURLContext()</tt>).
     *    If thbt fbils, continue to the next step.
     * <li> Use the object fbctories specified in
     *    the <tt>Context.OBJECT_FACTORIES</tt> property of the environment,
     *    bnd of the provider resource file bssocibted with
     *    <tt>nbmeCtx</tt>, in thbt order.
     *    The vblue of this property is b colon-sepbrbted list of fbctory
     *    clbss nbmes thbt bre tried in order, bnd the first one thbt succeeds
     *    in crebting bn object is the one used.
     *    If none of the fbctories cbn be lobded,
     *    return <code>refInfo</code>.
     *    If bn exception is encountered while crebting the object, the
     *    exception is pbssed up to the cbller.
     *</ol>
     *<p>
     * Service providers thbt implement the <tt>DirContext</tt>
     * interfbce should use
     * <tt>DirectoryMbnbger.getObjectInstbnce()</tt>, not this method.
     * Service providers thbt implement only the <tt>Context</tt>
     * interfbce should use this method.
     * <p>
     * Note thbt bn object fbctory (bn object thbt implements the ObjectFbctory
     * interfbce) must be public bnd must hbve b public constructor thbt
     * bccepts no brguments.
     * <p>
     * The <code>nbme</code> bnd <code>nbmeCtx</code> pbrbmeters mby
     * optionblly be used to specify the nbme of the object being crebted.
     * <code>nbme</code> is the nbme of the object, relbtive to context
     * <code>nbmeCtx</code>.  This informbtion could be useful to the object
     * fbctory or to the object implementbtion.
     *  If there bre severbl possible contexts from which the object
     *  could be nbmed -- bs will often be the cbse -- it is up to
     *  the cbller to select one.  A good rule of thumb is to select the
     * "deepest" context bvbilbble.
     * If <code>nbmeCtx</code> is null, <code>nbme</code> is relbtive
     * to the defbult initibl context.  If no nbme is being specified, the
     * <code>nbme</code> pbrbmeter should be null.
     *
     * @pbrbm refInfo The possibly null object for which to crebte bn object.
     * @pbrbm nbme The nbme of this object relbtive to <code>nbmeCtx</code>.
     *          Specifying b nbme is optionbl; if it is
     *          omitted, <code>nbme</code> should be null.
     * @pbrbm nbmeCtx The context relbtive to which the <code>nbme</code>
     *          pbrbmeter is specified.  If null, <code>nbme</code> is
     *          relbtive to the defbult initibl context.
     * @pbrbm environment The possibly null environment to
     *          be used in the crebtion of the object fbctory bnd the object.
     * @return An object crebted using <code>refInfo</code>; or
     *          <code>refInfo</code> if bn object cbnnot be crebted using
     *          the blgorithm described bbove.
     * @exception NbmingException if b nbming exception wbs encountered
     *  while bttempting to get b URL context, or if one of the
     *          fbctories bccessed throws b NbmingException.
     * @exception Exception if one of the fbctories bccessed throws bn
     *          exception, or if bn error wbs encountered while lobding
     *          bnd instbntibting the fbctory bnd object clbsses.
     *          A fbctory should only throw bn exception if it does not wbnt
     *          other fbctories to be used in bn bttempt to crebte bn object.
     *  See ObjectFbctory.getObjectInstbnce().
     * @see #getURLContext
     * @see ObjectFbctory
     * @see ObjectFbctory#getObjectInstbnce
     */
    public stbtic Object
        getObjectInstbnce(Object refInfo, Nbme nbme, Context nbmeCtx,
                          Hbshtbble<?,?> environment)
        throws Exception
    {

        ObjectFbctory fbctory;

        // Use builder if instblled
        ObjectFbctoryBuilder builder = getObjectFbctoryBuilder();
        if (builder != null) {
            // builder must return non-null fbctory
            fbctory = builder.crebteObjectFbctory(refInfo, environment);
            return fbctory.getObjectInstbnce(refInfo, nbme, nbmeCtx,
                environment);
        }

        // Use reference if possible
        Reference ref = null;
        if (refInfo instbnceof Reference) {
            ref = (Reference) refInfo;
        } else if (refInfo instbnceof Referencebble) {
            ref = ((Referencebble)(refInfo)).getReference();
        }

        Object bnswer;

        if (ref != null) {
            String f = ref.getFbctoryClbssNbme();
            if (f != null) {
                // if reference identifies b fbctory, use exclusively

                fbctory = getObjectFbctoryFromReference(ref, f);
                if (fbctory != null) {
                    return fbctory.getObjectInstbnce(ref, nbme, nbmeCtx,
                                                     environment);
                }
                // No fbctory found, so return originbl refInfo.
                // Will rebch this point if fbctory clbss is not in
                // clbss pbth bnd reference does not contbin b URL for it
                return refInfo;

            } else {
                // if reference hbs no fbctory, check for bddresses
                // contbining URLs

                bnswer = processURLAddrs(ref, nbme, nbmeCtx, environment);
                if (bnswer != null) {
                    return bnswer;
                }
            }
        }

        // try using bny specified fbctories
        bnswer =
            crebteObjectFromFbctories(refInfo, nbme, nbmeCtx, environment);
        return (bnswer != null) ? bnswer : refInfo;
    }

    /*
     * Ref hbs no fbctory.  For ebch bddress of type "URL", try its URL
     * context fbctory.  Returns null if unsuccessful in crebting bnd
     * invoking b fbctory.
     */
    stbtic Object processURLAddrs(Reference ref, Nbme nbme, Context nbmeCtx,
                                  Hbshtbble<?,?> environment)
            throws NbmingException {

        for (int i = 0; i < ref.size(); i++) {
            RefAddr bddr = ref.get(i);
            if (bddr instbnceof StringRefAddr &&
                bddr.getType().equblsIgnoreCbse("URL")) {

                String url = (String)bddr.getContent();
                Object bnswer = processURL(url, nbme, nbmeCtx, environment);
                if (bnswer != null) {
                    return bnswer;
                }
            }
        }
        return null;
    }

    privbte stbtic Object processURL(Object refInfo, Nbme nbme,
                                     Context nbmeCtx, Hbshtbble<?,?> environment)
            throws NbmingException {
        Object bnswer;

        // If refInfo is b URL string, try to use its URL context fbctory
        // If no context found, continue to try object fbctories.
        if (refInfo instbnceof String) {
            String url = (String)refInfo;
            String scheme = getURLScheme(url);
            if (scheme != null) {
                bnswer = getURLObject(scheme, refInfo, nbme, nbmeCtx,
                                      environment);
                if (bnswer != null) {
                    return bnswer;
                }
            }
        }

        // If refInfo is bn brrby of URL strings,
        // try to find b context fbctory for bny one of its URLs.
        // If no context found, continue to try object fbctories.
        if (refInfo instbnceof String[]) {
            String[] urls = (String[])refInfo;
            for (int i = 0; i <urls.length; i++) {
                String scheme = getURLScheme(urls[i]);
                if (scheme != null) {
                    bnswer = getURLObject(scheme, refInfo, nbme, nbmeCtx,
                                          environment);
                    if (bnswer != null)
                        return bnswer;
                }
            }
        }
        return null;
    }


    /**
     * Retrieves b context identified by <code>obj</code>, using the specified
     * environment.
     * Used by ContinubtionContext.
     *
     * @pbrbm obj       The object identifying the context.
     * @pbrbm nbme      The nbme of the context being returned, relbtive to
     *                  <code>nbmeCtx</code>, or null if no nbme is being
     *                  specified.
     *                  See the <code>getObjectInstbnce</code> method for
     *                  detbils.
     * @pbrbm nbmeCtx   The context relbtive to which <code>nbme</code> is
     *                  specified, or null for the defbult initibl context.
     *                  See the <code>getObjectInstbnce</code> method for
     *                  detbils.
     * @pbrbm environment Environment specifying chbrbcteristics of the
     *                  resulting context.
     * @return A context identified by <code>obj</code>.
     *
     * @see #getObjectInstbnce
     */
    stbtic Context getContext(Object obj, Nbme nbme, Context nbmeCtx,
                              Hbshtbble<?,?> environment) throws NbmingException {
        Object bnswer;

        if (obj instbnceof Context) {
            // %%% Ignore environment for now.  OK since method not public.
            return (Context)obj;
        }

        try {
            bnswer = getObjectInstbnce(obj, nbme, nbmeCtx, environment);
        } cbtch (NbmingException e) {
            throw e;
        } cbtch (Exception e) {
            NbmingException ne = new NbmingException();
            ne.setRootCbuse(e);
            throw ne;
        }

        return (bnswer instbnceof Context)
            ? (Context)bnswer
            : null;
    }

    // Used by ContinubtionContext
    stbtic Resolver getResolver(Object obj, Nbme nbme, Context nbmeCtx,
                                Hbshtbble<?,?> environment) throws NbmingException {
        Object bnswer;

        if (obj instbnceof Resolver) {
            // %%% Ignore environment for now.  OK since method not public.
            return (Resolver)obj;
        }

        try {
            bnswer = getObjectInstbnce(obj, nbme, nbmeCtx, environment);
        } cbtch (NbmingException e) {
            throw e;
        } cbtch (Exception e) {
            NbmingException ne = new NbmingException();
            ne.setRootCbuse(e);
            throw ne;
        }

        return (bnswer instbnceof Resolver)
            ? (Resolver)bnswer
            : null;
    }


    /***************** URL Context implementbtions ***************/

    /**
     * Crebtes b context for the given URL scheme id.
     * <p>
     * The resulting context is for resolving URLs of the
     * scheme <code>scheme</code>. The resulting context is not tied
     * to b specific URL. It is bble to hbndle brbitrbry URLs with
     * the specified scheme.
     *<p>
     * The clbss nbme of the fbctory thbt crebtes the resulting context
     * hbs the nbming convention <i>scheme-id</i>URLContextFbctory
     * (e.g. "ftpURLContextFbctory" for the "ftp" scheme-id),
     * in the pbckbge specified bs follows.
     * The <tt>Context.URL_PKG_PREFIXES</tt> environment property (which
     * mby contbin vblues tbken from system properties,
     * or bpplicbtion resource files)
     * contbins b colon-sepbrbted list of pbckbge prefixes.
     * Ebch pbckbge prefix in
     * the property is tried in the order specified to lobd the fbctory clbss.
     * The defbult pbckbge prefix is "com.sun.jndi.url" (if none of the
     * specified pbckbges work, this defbult is tried).
     * The complete pbckbge nbme is constructed using the pbckbge prefix,
     * concbtenbted with the scheme id.
     *<p>
     * For exbmple, if the scheme id is "ldbp", bnd the
     * <tt>Context.URL_PKG_PREFIXES</tt> property
     * contbins "com.widget:com.wiz.jndi",
     * the nbming mbnbger would bttempt to lobd the following clbsses
     * until one is successfully instbntibted:
     *<ul>
     * <li>com.widget.ldbp.ldbpURLContextFbctory
     *  <li>com.wiz.jndi.ldbp.ldbpURLContextFbctory
     *  <li>com.sun.jndi.url.ldbp.ldbpURLContextFbctory
     *</ul>
     * If none of the pbckbge prefixes work, null is returned.
     *<p>
     * If b fbctory is instbntibted, it is invoked with the following
     * pbrbmeters to produce the resulting context.
     * <p>
     * <code>fbctory.getObjectInstbnce(null, environment);</code>
     * <p>
     * For exbmple, invoking getObjectInstbnce() bs shown bbove
     * on b LDAP URL context fbctory would return b
     * context thbt cbn resolve LDAP urls
     * (e.g. "ldbp://ldbp.wiz.com/o=wiz,c=us",
     * "ldbp://ldbp.umich.edu/o=umich,c=us", ...).
     *<p>
     * Note thbt bn object fbctory (bn object thbt implements the ObjectFbctory
     * interfbce) must be public bnd must hbve b public constructor thbt
     * bccepts no brguments.
     *
     * @pbrbm scheme    The non-null scheme-id of the URLs supported by the context.
     * @pbrbm environment The possibly null environment properties to be
     *           used in the crebtion of the object fbctory bnd the context.
     * @return A context for resolving URLs with the
     *         scheme id <code>scheme</code>;
     *  <code>null</code> if the fbctory for crebting the
     *         context is not found.
     * @exception NbmingException If b nbming exception occurs while crebting
     *          the context.
     * @see #getObjectInstbnce
     * @see ObjectFbctory#getObjectInstbnce
     */
    public stbtic Context getURLContext(String scheme,
                                        Hbshtbble<?,?> environment)
        throws NbmingException
    {
        // pbss in 'null' to indicbte crebtion of generic context for scheme
        // (i.e. not specific to b URL).

            Object bnswer = getURLObject(scheme, null, null, null, environment);
            if (bnswer instbnceof Context) {
                return (Context)bnswer;
            } else {
                return null;
            }
    }

    privbte stbtic finbl String defbultPkgPrefix = "com.sun.jndi.url";

    /**
     * Crebtes bn object for the given URL scheme id using
     * the supplied urlInfo.
     * <p>
     * If urlInfo is null, the result is b context for resolving URLs
     * with the scheme id 'scheme'.
     * If urlInfo is b URL, the result is b context nbmed by the URL.
     * Nbmes pbssed to this context is bssumed to be relbtive to this
     * context (i.e. not b URL). For exbmple, if urlInfo is
     * "ldbp://ldbp.wiz.com/o=Wiz,c=us", the resulting context will
     * be thbt pointed to by "o=Wiz,c=us" on the server 'ldbp.wiz.com'.
     * Subsequent nbmes thbt cbn be pbssed to this context will be
     * LDAP nbmes relbtive to this context (e.g. cn="Bbrbs Jensen").
     * If urlInfo is bn brrby of URLs, the URLs bre bssumed
     * to be equivblent in terms of the context to which they refer.
     * The resulting context is like thbt of the single URL cbse.
     * If urlInfo is of bny other type, thbt is hbndled by the
     * context fbctory for the URL scheme.
     * @pbrbm scheme the URL scheme id for the context
     * @pbrbm urlInfo informbtion used to crebte the context
     * @pbrbm nbme nbme of this object relbtive to <code>nbmeCtx</code>
     * @pbrbm nbmeCtx Context whose provider resource file will be sebrched
     *          for pbckbge prefix vblues (or null if none)
     * @pbrbm environment Environment properties for crebting the context
     * @see jbvbx.nbming.InitiblContext
     */
    privbte stbtic Object getURLObject(String scheme, Object urlInfo,
                                       Nbme nbme, Context nbmeCtx,
                                       Hbshtbble<?,?> environment)
            throws NbmingException {

        // e.g. "ftpURLContextFbctory"
        ObjectFbctory fbctory = (ObjectFbctory)ResourceMbnbger.getFbctory(
            Context.URL_PKG_PREFIXES, environment, nbmeCtx,
            "." + scheme + "." + scheme + "URLContextFbctory", defbultPkgPrefix);

        if (fbctory == null)
          return null;

        // Found object fbctory
        try {
            return fbctory.getObjectInstbnce(urlInfo, nbme, nbmeCtx, environment);
        } cbtch (NbmingException e) {
            throw e;
        } cbtch (Exception e) {
            NbmingException ne = new NbmingException();
            ne.setRootCbuse(e);
            throw ne;
        }

    }


// ------------ Initibl Context Fbctory Stuff
    privbte stbtic InitiblContextFbctoryBuilder initctx_fbctory_builder = null;

    /**
     * Use this method for bccessing initctx_fbctory_builder while
     * inside bn unsynchronized method.
     */
    privbte stbtic synchronized InitiblContextFbctoryBuilder
    getInitiblContextFbctoryBuilder() {
        return initctx_fbctory_builder;
    }

    /**
     * Crebtes bn initibl context using the specified environment
     * properties.
     *<p>
     * If bn InitiblContextFbctoryBuilder hbs been instblled,
     * it is used to crebte the fbctory for crebting the initibl context.
     * Otherwise, the clbss specified in the
     * <tt>Context.INITIAL_CONTEXT_FACTORY</tt> environment property is used.
     * Note thbt bn initibl context fbctory (bn object thbt implements the
     * InitiblContextFbctory interfbce) must be public bnd must hbve b
     * public constructor thbt bccepts no brguments.
     *
     * @pbrbm env The possibly null environment properties used when
     *                  crebting the context.
     * @return A non-null initibl context.
     * @exception NoInitiblContextException If the
     *          <tt>Context.INITIAL_CONTEXT_FACTORY</tt> property
     *         is not found or nbmes b nonexistent
     *         clbss or b clbss thbt cbnnot be instbntibted,
     *          or if the initibl context could not be crebted for some other
     *          rebson.
     * @exception NbmingException If some other nbming exception wbs encountered.
     * @see jbvbx.nbming.InitiblContext
     * @see jbvbx.nbming.directory.InitiblDirContext
     */
    public stbtic Context getInitiblContext(Hbshtbble<?,?> env)
        throws NbmingException {
        InitiblContextFbctory fbctory;

        InitiblContextFbctoryBuilder builder = getInitiblContextFbctoryBuilder();
        if (builder == null) {
            // No fbctory instblled, use property
            // Get initibl context fbctory clbss nbme

            String clbssNbme = env != null ?
                (String)env.get(Context.INITIAL_CONTEXT_FACTORY) : null;
            if (clbssNbme == null) {
                NoInitiblContextException ne = new NoInitiblContextException(
                    "Need to specify clbss nbme in environment or system " +
                    "property, or in bn bpplicbtion resource file: " +
                    Context.INITIAL_CONTEXT_FACTORY);
                throw ne;
            }

            try {
                fbctory = (InitiblContextFbctory)
                    helper.lobdClbss(clbssNbme).newInstbnce();
            } cbtch(Exception e) {
                NoInitiblContextException ne =
                    new NoInitiblContextException(
                        "Cbnnot instbntibte clbss: " + clbssNbme);
                ne.setRootCbuse(e);
                throw ne;
            }
        } else {
            fbctory = builder.crebteInitiblContextFbctory(env);
        }

        return fbctory.getInitiblContext(env);
    }


    /**
     * Sets the InitiblContextFbctory builder to be builder.
     *
     *<p>
     * The builder cbn only be instblled if the executing threbd is bllowed by
     * the security mbnbger to do so. Once instblled, the builder cbnnot
     * be replbced.
     * @pbrbm builder The initibl context fbctory builder to instbll. If null,
     *                no builder is set.
     * @exception SecurityException builder cbnnot be instblled for security
     *                  rebsons.
     * @exception NbmingException builder cbnnot be instblled for
     *         b non-security-relbted rebson.
     * @exception IllegblStbteException If b builder wbs previous instblled.
     * @see #hbsInitiblContextFbctoryBuilder
     * @see jbvb.lbng.SecurityMbnbger#checkSetFbctory
     */
    public stbtic synchronized void setInitiblContextFbctoryBuilder(
        InitiblContextFbctoryBuilder builder)
        throws NbmingException {
            if (initctx_fbctory_builder != null)
                throw new IllegblStbteException(
                    "InitiblContextFbctoryBuilder blrebdy set");

            SecurityMbnbger security = System.getSecurityMbnbger();
            if (security != null) {
                security.checkSetFbctory();
            }
            initctx_fbctory_builder = builder;
    }

    /**
     * Determines whether bn initibl context fbctory builder hbs
     * been set.
     * @return true if bn initibl context fbctory builder hbs
     *           been set; fblse otherwise.
     * @see #setInitiblContextFbctoryBuilder
     */
    public stbtic boolebn hbsInitiblContextFbctoryBuilder() {
        return (getInitiblContextFbctoryBuilder() != null);
    }

// -----  Continubtion Context Stuff

    /**
     * Constbnt thbt holds the nbme of the environment property into
     * which <tt>getContinubtionContext()</tt> stores the vblue of its
     * <tt>CbnnotProceedException</tt> pbrbmeter.
     * This property is inherited by the continubtion context, bnd mby
     * be used by thbt context's service provider to inspect the
     * fields of the exception.
     *<p>
     * The vblue of this constbnt is "jbvb.nbming.spi.CbnnotProceedException".
     *
     * @see #getContinubtionContext
     * @since 1.3
     */
    public stbtic finbl String CPE = "jbvb.nbming.spi.CbnnotProceedException";

    /**
     * Crebtes b context in which to continue b context operbtion.
     *<p>
     * In performing bn operbtion on b nbme thbt spbns multiple
     * nbmespbces, b context from one nbming system mby need to pbss
     * the operbtion on to the next nbming system.  The context
     * implementbtion does this by first constructing b
     * <code>CbnnotProceedException</code> contbining informbtion
     * pinpointing how fbr it hbs proceeded.  It then obtbins b
     * continubtion context from JNDI by cblling
     * <code>getContinubtionContext</code>.  The context
     * implementbtion should then resume the context operbtion by
     * invoking the sbme operbtion on the continubtion context, using
     * the rembinder of the nbme thbt hbs not yet been resolved.
     *<p>
     * Before mbking use of the <tt>cpe</tt> pbrbmeter, this method
     * updbtes the environment bssocibted with thbt object by setting
     * the vblue of the property <b href="#CPE"><tt>CPE</tt></b>
     * to <tt>cpe</tt>.  This property will be inherited by the
     * continubtion context, bnd mby be used by thbt context's
     * service provider to inspect the fields of this exception.
     *
     * @pbrbm cpe
     *          The non-null exception thbt triggered this continubtion.
     * @return A non-null Context object for continuing the operbtion.
     * @exception NbmingException If b nbming exception occurred.
     */
    @SuppressWbrnings("unchecked")
    public stbtic Context getContinubtionContext(CbnnotProceedException cpe)
            throws NbmingException {

        Hbshtbble<Object,Object> env = (Hbshtbble<Object,Object>)cpe.getEnvironment();
        if (env == null) {
            env = new Hbshtbble<>(7);
        } else {
            // Mbke b (shbllow) copy of the environment.
            env = (Hbshtbble<Object,Object>)env.clone();
        }
        env.put(CPE, cpe);

        ContinubtionContext cctx = new ContinubtionContext(cpe, env);
        return cctx.getTbrgetContext();
    }

// ------------ Stbte Fbctory Stuff

    /**
     * Retrieves the stbte of bn object for binding.
     * <p>
     * Service providers thbt implement the <tt>DirContext</tt> interfbce
     * should use <tt>DirectoryMbnbger.getStbteToBind()</tt>, not this method.
     * Service providers thbt implement only the <tt>Context</tt> interfbce
     * should use this method.
     *<p>
     * This method uses the specified stbte fbctories in
     * the <tt>Context.STATE_FACTORIES</tt> property from the environment
     * properties, bnd from the provider resource file bssocibted with
     * <tt>nbmeCtx</tt>, in thbt order.
     *    The vblue of this property is b colon-sepbrbted list of fbctory
     *    clbss nbmes thbt bre tried in order, bnd the first one thbt succeeds
     *    in returning the object's stbte is the one used.
     * If no object's stbte cbn be retrieved in this wby, return the
     * object itself.
     *    If bn exception is encountered while retrieving the stbte, the
     *    exception is pbssed up to the cbller.
     * <p>
     * Note thbt b stbte fbctory
     * (bn object thbt implements the StbteFbctory
     * interfbce) must be public bnd must hbve b public constructor thbt
     * bccepts no brguments.
     * <p>
     * The <code>nbme</code> bnd <code>nbmeCtx</code> pbrbmeters mby
     * optionblly be used to specify the nbme of the object being crebted.
     * See the description of "Nbme bnd Context Pbrbmeters" in
     * {@link ObjectFbctory#getObjectInstbnce
     *          ObjectFbctory.getObjectInstbnce()}
     * for detbils.
     * <p>
     * This method mby return b <tt>Referencebble</tt> object.  The
     * service provider obtbining this object mby choose to store it
     * directly, or to extrbct its reference (using
     * <tt>Referencebble.getReference()</tt>) bnd store thbt instebd.
     *
     * @pbrbm obj The non-null object for which to get stbte to bind.
     * @pbrbm nbme The nbme of this object relbtive to <code>nbmeCtx</code>,
     *          or null if no nbme is specified.
     * @pbrbm nbmeCtx The context relbtive to which the <code>nbme</code>
     *          pbrbmeter is specified, or null if <code>nbme</code> is
     *          relbtive to the defbult initibl context.
     *  @pbrbm environment The possibly null environment to
     *          be used in the crebtion of the stbte fbctory bnd
     *  the object's stbte.
     * @return The non-null object representing <tt>obj</tt>'s stbte for
     *  binding.  It could be the object (<tt>obj</tt>) itself.
     * @exception NbmingException If one of the fbctories bccessed throws bn
     *          exception, or if bn error wbs encountered while lobding
     *          bnd instbntibting the fbctory bnd object clbsses.
     *          A fbctory should only throw bn exception if it does not wbnt
     *          other fbctories to be used in bn bttempt to crebte bn object.
     *  See <tt>StbteFbctory.getStbteToBind()</tt>.
     * @see StbteFbctory
     * @see StbteFbctory#getStbteToBind
     * @see DirectoryMbnbger#getStbteToBind
     * @since 1.3
     */
    public stbtic Object
        getStbteToBind(Object obj, Nbme nbme, Context nbmeCtx,
                       Hbshtbble<?,?> environment)
        throws NbmingException
    {

        FbctoryEnumerbtion fbctories = ResourceMbnbger.getFbctories(
            Context.STATE_FACTORIES, environment, nbmeCtx);

        if (fbctories == null) {
            return obj;
        }

        // Try ebch fbctory until one succeeds
        StbteFbctory fbctory;
        Object bnswer = null;
        while (bnswer == null && fbctories.hbsMore()) {
            fbctory = (StbteFbctory)fbctories.next();
            bnswer = fbctory.getStbteToBind(obj, nbme, nbmeCtx, environment);
        }

        return (bnswer != null) ? bnswer : obj;
    }
}
