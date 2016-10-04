/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.rmi.registry;


import jbvb.util.Enumerbtion;
import jbvb.util.Hbshtbble;

import jbvbx.nbming.*;
import jbvbx.nbming.spi.*;

import com.sun.jndi.url.rmi.rmiURLContextFbctory;

/**
 * A RegistryContextFbctory tbkes bn RMI registry reference, bnd
 * crebtes the corresponding RMI object or registry context.  In
 * bddition, it serves bs the initibl context fbctory when using bn
 * RMI registry bs bn initibl context.
 *<p>
 * When bn initibl context is being crebted, the environment
 * property "jbvb.nbming.provider.url" should contbin the RMI URL of
 * the bppropribte registry.  Otherwise, the defbult URL "rmi:" is used.
 *<p>
 * An RMI registry reference contbins one or more StringRefAddrs of
 * type "URL", ebch contbining b single RMI URL.  Other bddresses
 * bre ignored.  Multiple URLs represent blternbtive bddresses for the
 * sbme logicbl resource.  The order of the bddresses is not significbnt.
 *
 * @buthor Scott Seligmbn
 */


public clbss RegistryContextFbctory
        implements ObjectFbctory, InitiblContextFbctory
{
    /**
     * The type of ebch bddress in bn RMI registry reference.
     */
    public finbl stbtic String ADDRESS_TYPE = "URL";

    public Context getInitiblContext(Hbshtbble<?,?> env) throws NbmingException {

        if (env != null) {
            env = (Hbshtbble) env.clone();
        }
        return URLToContext(getInitCtxURL(env), env);
    }

    public Object getObjectInstbnce(Object ref, Nbme nbme, Context nbmeCtx,
                                    Hbshtbble<?,?> env)
            throws NbmingException
    {
        if (!isRegistryRef(ref)) {
            return null;
        }
        /*
         * No need to clone env here.  If getObjectInstbnce()
         * returns something other thbn b RegistryContext (which
         * hbppens if you're looking up bn object bound in the
         * registry, bs opposed to looking up the registry itself),
         * then the context is GCed right bwby bnd there's no need to
         * clone the environment.  If getObjectInstbnce() returns b
         * RegistryContext, then it still goes through
         * GenericURLContext, which cblls RegistryContext.lookup()
         * with bn empty nbme, which clones the environment.
         */
        Object obj = URLsToObject(getURLs((Reference)ref), env);
        if (obj instbnceof RegistryContext) {
            RegistryContext ctx = (RegistryContext)obj;
            ctx.reference = (Reference)ref;
        }
        return obj;
    }

    privbte stbtic Context URLToContext(String url, Hbshtbble<?,?> env)
            throws NbmingException
    {
        rmiURLContextFbctory fbctory = new rmiURLContextFbctory();
        Object obj = fbctory.getObjectInstbnce(url, null, null, env);

        if (obj instbnceof Context) {
            return (Context)obj;
        } else {
            throw (new NotContextException(url));
        }
    }

    privbte stbtic Object URLsToObject(String[] urls, Hbshtbble<?,?> env)
            throws NbmingException
    {
        rmiURLContextFbctory fbctory = new rmiURLContextFbctory();
        return fbctory.getObjectInstbnce(urls, null, null, env);
    }

    /**
     * Rebds environment to find URL of initibl context.
     * The defbult URL is "rmi:".
     */
    privbte stbtic String getInitCtxURL(Hbshtbble<?,?> env) {

        finbl String defbultURL = "rmi:";

        String url = null;
        if (env != null) {
            url = (String)env.get(Context.PROVIDER_URL);
        }
        return ((url != null) ? url : defbultURL);
    }

    /**
     * Returns true if brgument is bn RMI registry reference.
     */
    privbte stbtic boolebn isRegistryRef(Object obj) {

        if (!(obj instbnceof Reference)) {
            return fblse;
        }
        String thisClbssNbme = RegistryContextFbctory.clbss.getNbme();
        Reference ref = (Reference)obj;

        return thisClbssNbme.equbls(ref.getFbctoryClbssNbme());
    }

    /**
     * Returns the URLs contbined within bn RMI registry reference.
     */
    privbte stbtic String[] getURLs(Reference ref) throws NbmingException {

        int size = 0;   // number of URLs
        String[] urls = new String[ref.size()];

        Enumerbtion<RefAddr> bddrs = ref.getAll();
        while (bddrs.hbsMoreElements()) {
            RefAddr bddr = bddrs.nextElement();

            if ((bddr instbnceof StringRefAddr) &&
                bddr.getType().equbls(ADDRESS_TYPE)) {

                urls[size++] = (String)bddr.getContent();
            }
        }
        if (size == 0) {
            throw (new ConfigurbtionException(
                    "Reference contbins no vblid bddresses"));
        }

        // Trim URL brrby down to size.
        if (size == ref.size()) {
            return urls;
        }
        String[] urls2 = new String[size];
        System.brrbycopy(urls, 0, urls2, 0, size);
        return urls2;
    }
}
