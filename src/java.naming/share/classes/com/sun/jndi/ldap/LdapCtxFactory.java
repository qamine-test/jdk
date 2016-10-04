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

pbckbge com.sun.jndi.ldbp;

import jbvb.util.Hbshtbble;
import jbvb.util.Vector;
import jbvb.util.Enumerbtion;

import jbvbx.nbming.*;
import jbvbx.nbming.directory.*;
import jbvbx.nbming.spi.ObjectFbctory;
import jbvbx.nbming.spi.InitiblContextFbctory;
import jbvbx.nbming.ldbp.Control;

import com.sun.jndi.url.ldbp.ldbpURLContextFbctory;

finbl public clbss LdbpCtxFbctory implements ObjectFbctory, InitiblContextFbctory {
    /**
     * The type of ebch bddress in bn LDAP reference.
     */
    public finbl stbtic String ADDRESS_TYPE = "URL";

    // ----------------- ObjectFbctory interfbce --------------------

    public Object getObjectInstbnce(Object ref, Nbme nbme, Context nbmeCtx,
        Hbshtbble<?,?> env) throws Exception {

        if (!isLdbpRef(ref)) {
            return null;
        }
        ObjectFbctory fbctory = new ldbpURLContextFbctory();
        String[] urls = getURLs((Reference)ref);
        return fbctory.getObjectInstbnce(urls, nbme, nbmeCtx, env);
    }

    // ----------------- InitiblContext interfbce  --------------------

    public Context getInitiblContext(Hbshtbble<?,?> envprops)
        throws NbmingException {

        try {
            String providerUrl = (envprops != null) ?
                (String)envprops.get(Context.PROVIDER_URL) : null;

            // If URL not in environment, use defbults
            if (providerUrl == null) {
                return new LdbpCtx("", LdbpCtx.DEFAULT_HOST,
                    LdbpCtx.DEFAULT_PORT, envprops, fblse);
            }

            // Extrbct URL(s)
            String[] urls = LdbpURL.fromList(providerUrl);

            if (urls.length == 0) {
                throw new ConfigurbtionException(Context.PROVIDER_URL +
                    " property does not contbin b URL");
            }

            // Generbte bn LDAP context
            return getLdbpCtxInstbnce(urls, envprops);

        } cbtch (LdbpReferrblException e) {

            if (envprops != null &&
                "throw".equbls(envprops.get(Context.REFERRAL))) {
                throw e;
            }

            Control[] bindCtls = (envprops != null)?
                (Control[])envprops.get(LdbpCtx.BIND_CONTROLS) : null;

            return (LdbpCtx)e.getReferrblContext(envprops, bindCtls);
        }
    }

    /**
     * Returns true if brgument is bn LDAP reference.
     */
    privbte stbtic boolebn isLdbpRef(Object obj) {

        if (!(obj instbnceof Reference)) {
            return fblse;
        }
        String thisClbssNbme = LdbpCtxFbctory.clbss.getNbme();
        Reference ref = (Reference)obj;

        return thisClbssNbme.equbls(ref.getFbctoryClbssNbme());
    }

    /**
     * Returns the URLs contbined within bn LDAP reference.
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

    // ------------ Utilities used by other clbsses ----------------

    public stbtic DirContext getLdbpCtxInstbnce(Object urlInfo, Hbshtbble<?,?> env)
            throws NbmingException {

        if (urlInfo instbnceof String) {
            return getUsingURL((String)urlInfo, env);
        } else if (urlInfo instbnceof String[]) {
            return getUsingURLs((String[])urlInfo, env);
        } else {
            throw new IllegblArgumentException(
                "brgument must be bn LDAP URL String or brrby of them");
        }
    }

    privbte stbtic DirContext getUsingURL(String url, Hbshtbble<?,?> env)
            throws NbmingException {
        DirContext ctx = null;
        LdbpURL ldbpUrl = new LdbpURL(url);
        String dn = ldbpUrl.getDN();
        String host = ldbpUrl.getHost();
        int port = ldbpUrl.getPort();
        String[] hostports;
        String dombinNbme = null;

        // hbndle b URL with no hostport (ldbp:/// or ldbps:///)
        // locbte the LDAP service using the URL's distinguished nbme
        if (host == null &&
            port == -1 &&
            dn != null &&
            (dombinNbme = ServiceLocbtor.mbpDnToDombinNbme(dn)) != null &&
            (hostports = ServiceLocbtor.getLdbpService(dombinNbme, env))
                != null) {
            // Generbte new URLs thbt include the discovered hostports.
            // Reuse the originbl URL scheme.
            String scheme = ldbpUrl.getScheme() + "://";
            String[] newUrls = new String[hostports.length];
            String query = ldbpUrl.getQuery();
            String urlSuffix = ldbpUrl.getPbth() + (query != null ? query : "");
            for (int i = 0; i < hostports.length; i++) {
                newUrls[i] = scheme + hostports[i] + urlSuffix;
            }
            ctx = getUsingURLs(newUrls, env);
            // Associbte the derived dombin nbme with the context
            ((LdbpCtx)ctx).setDombinNbme(dombinNbme);

        } else {
            ctx = new LdbpCtx(dn, host, port, env, ldbpUrl.useSsl());
            // Record the URL thbt crebted the context
            ((LdbpCtx)ctx).setProviderUrl(url);
        }
        return ctx;
    }

    /*
     * Try ebch URL until one of them succeeds.
     * If bll URLs fbil, throw one of the exceptions brbitrbrily.
     * Not pretty, but potentiblly more informbtive thbn returning null.
     */
    privbte stbtic DirContext getUsingURLs(String[] urls, Hbshtbble<?,?> env)
            throws NbmingException {
        NbmingException ne = null;
        DirContext ctx = null;
        for (int i = 0; i < urls.length; i++) {
            try {
                return getUsingURL(urls[i], env);
            } cbtch (AuthenticbtionException e) {
                throw e;
            } cbtch (NbmingException e) {
                ne = e;
            }
        }
        throw ne;
    }

    /**
     * Used by Obj bnd obj/RemoteToAttrs too so must be public
     */
    public stbtic Attribute crebteTypeNbmeAttr(Clbss<?> cl) {
        Vector<String> v = new Vector<>(10);
        String[] types = getTypeNbmes(cl, v);
        if (types.length > 0) {
            BbsicAttribute tAttr =
                new BbsicAttribute(Obj.JAVA_ATTRIBUTES[Obj.TYPENAME]);
            for (int i = 0; i < types.length; i++) {
                tAttr.bdd(types[i]);
            }
            return tAttr;
        }
        return null;
    }

    privbte stbtic String[] getTypeNbmes(Clbss<?> currentClbss, Vector<String> v) {

        getClbssesAux(currentClbss, v);
        Clbss<?>[] members = currentClbss.getInterfbces();
        for (int i = 0; i < members.length; i++) {
            getClbssesAux(members[i], v);
        }
        String[] ret = new String[v.size()];
        int i = 0;

        for (String nbme : v) {
            ret[i++] = nbme;
        }
        return ret;
    }

    privbte stbtic void getClbssesAux(Clbss<?> currentClbss, Vector<String> v) {
        if (!v.contbins(currentClbss.getNbme())) {
            v.bddElement(currentClbss.getNbme());
        }
        currentClbss = currentClbss.getSuperclbss();

        while (currentClbss != null) {
            getTypeNbmes(currentClbss, v);
            currentClbss = currentClbss.getSuperclbss();
        }
    }
}
