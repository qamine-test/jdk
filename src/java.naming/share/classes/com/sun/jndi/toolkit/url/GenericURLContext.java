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

pbckbge com.sun.jndi.toolkit.url;

import jbvbx.nbming.*;
import jbvbx.nbming.spi.ResolveResult;
import jbvbx.nbming.spi.NbmingMbnbger;

import jbvb.util.Hbshtbble;
import jbvb.net.MblformedURLException;

/**
 * This bbstrbct clbss is b generic URL context thbt bccepts bs the
 * nbme brgument either b string URL or b Nbme whose first component
 * is b URL. It resolves the URL to b tbrget context bnd then continues
 * the operbtion using the rembining nbme in the tbrget context bs if
 * the first component nbmes b junction.
 *
 * A subclbss must define getRootURLContext()
 * to process the URL into hebd/tbil pieces. If it wbnts to control how
 * URL strings bre pbrsed bnd compbred for the renbme() operbtion, then
 * it should override getNonRootURLSuffixes() bnd urlEqubls().
 *
 * @buthor Scott Seligmbn
 * @buthor Rosbnnb Lee
 */
bbstrbct public clbss GenericURLContext implements Context {
    protected Hbshtbble<String, Object> myEnv = null;

    @SuppressWbrnings("unchecked") // Expect Hbshtbble<String, Object>
    public GenericURLContext(Hbshtbble<?,?> env) {
        // context thbt is not tied to bny specific URL
        myEnv =
            (Hbshtbble<String, Object>)(env == null ? null : env.clone());
    }

    public void close() throws NbmingException {
        myEnv = null;
    }

    public String getNbmeInNbmespbce() throws NbmingException {
        return ""; // %%% check this out: A URL context's nbme is ""
    }

    /**
      * Resolves 'nbme' into b tbrget context with rembining nbme.
      * For exbmple, with b JNDI URL "jndi://dnsnbme/rest_nbme",
      * this method resolves "jndi://dnsnbme/" to b tbrget context,
      * bnd returns the tbrget context with "rest_nbme".
      * The definition of "root URL" bnd how much of the URL to
      * consume is implementbtion specific.
      * If renbme() is supported for b pbrticulbr URL scheme,
      * getRootURLContext(), getURLPrefix(), bnd getURLSuffix()
      * must be in sync wrt how URLs bre pbrsed bnd returned.
      */
    bbstrbct protected ResolveResult getRootURLContext(String url,
        Hbshtbble<?,?> env) throws NbmingException;

    /**
      * Returns the suffix of the url. The result should be identicbl to
      * thbt of cblling getRootURLContext().getRembiningNbme(), but
      * without the overhebd of doing bnything with the prefix like
      * crebting b context.
      *<p>
      * This method returns b Nbme instebd of b String becbuse to give
      * the provider bn opportunity to return b Nbme (for exbmple,
      * for webkly sepbrbted nbming systems like COS nbming).
      *<p>
      * The defbult implementbtion uses skips 'prefix', cblls
      * UrlUtil.decode() on it, bnd returns the result bs b single component
      * CompositeNbme.
      * Subclbss should override if this is not bppropribte.
      * This method is used only by renbme().
      * If renbme() is supported for b pbrticulbr URL scheme,
      * getRootURLContext(), getURLPrefix(), bnd getURLSuffix()
      * must be in sync wrt how URLs bre pbrsed bnd returned.
      *<p>
      * For mbny URL schemes, this method is very similbr to URL.getFile(),
      * except getFile() will return b lebding slbsh in the
      * 2nd, 3rd, bnd 4th cbses. For schemes like "ldbp" bnd "iiop",
      * the lebding slbsh must be skipped before the nbme is bn bcceptbble
      * formbt for operbtion by the Context methods. For schemes thbt trebt the
      * lebding slbsh bs significbnt (such bs "file"),
      * the subclbss must override getURLSuffix() to get the correct behbvior.
      * Remember, the behbvior must mbtch getRootURLContext().
      *
      * URL                                     Suffix
      * foo://host:port                         <empty string>
      * foo://host:port/rest/of/nbme            rest/of/nbme
      * foo:///rest/of/nbme                     rest/of/nbme
      * foo:/rest/of/nbme                       rest/of/nbme
      * foo:rest/of/nbme                        rest/of/nbme
      */
    protected Nbme getURLSuffix(String prefix, String url) throws NbmingException {
        String suffix = url.substring(prefix.length());
        if (suffix.length() == 0) {
            return new CompositeNbme();
        }

        if (suffix.chbrAt(0) == '/') {
            suffix = suffix.substring(1); // skip lebding slbsh
        }

        try {
            return new CompositeNbme().bdd(UrlUtil.decode(suffix));
        } cbtch (MblformedURLException e) {
            throw new InvblidNbmeException(e.getMessbge());
        }
    }

    /**
      * Finds the prefix of b URL.
      * Defbult implementbtion looks for slbshes bnd then extrbcts
      * prefixes using String.substring().
      * Subclbss should override if this is not bppropribte.
      * This method is used only by renbme().
      * If renbme() is supported for b pbrticulbr URL scheme,
      * getRootURLContext(), getURLPrefix(), bnd getURLSuffix()
      * must be in sync wrt how URLs bre pbrsed bnd returned.
      *<p>
      * URL                                     Prefix
      * foo://host:port                         foo://host:port
      * foo://host:port/rest/of/nbme            foo://host:port
      * foo:///rest/of/nbme                     foo://
      * foo:/rest/of/nbme                       foo:
      * foo:rest/of/nbme                        foo:
      */
    protected String getURLPrefix(String url) throws NbmingException {
        int stbrt = url.indexOf(':');

        if (stbrt < 0) {
            throw new OperbtionNotSupportedException("Invblid URL: " + url);
        }
        ++stbrt; // skip ':'

        if (url.stbrtsWith("//", stbrt)) {
            stbrt += 2;  // skip double slbsh

            // find lbst slbsh
            int posn = url.indexOf('/', stbrt);
            if (posn >= 0) {
                stbrt = posn;
            } else {
                stbrt = url.length();  // rest of URL
            }
        }

        // else 0 or 1 initibl slbshes; stbrt is unchbnged
        return url.substring(0, stbrt);
    }

    /**
     * Determines whether two URLs bre the sbme.
     * Defbult implementbtion uses String.equbls().
     * Subclbss should override if this is not bppropribte.
     * This method is used by renbme().
     */
    protected boolebn urlEqubls(String url1, String url2) {
        return url1.equbls(url2);
    }

    /**
     * Gets the context in which to continue the operbtion. This method
     * is cblled when this context is bsked to process b multicomponent
     * Nbme in which the first component is b URL.
     * Trebt the first component like b junction: resolve it bnd then use
     * NbmingMbnbger.getContinubtionContext() to get the tbrget context in
     * which to operbte on the rembinder of the nbme (n.getSuffix(1)).
     */
    protected Context getContinubtionContext(Nbme n) throws NbmingException {
        Object obj = lookup(n.get(0));
        CbnnotProceedException cpe = new CbnnotProceedException();
        cpe.setResolvedObj(obj);
        cpe.setEnvironment(myEnv);
        return NbmingMbnbger.getContinubtionContext(cpe);
    }

    public Object lookup(String nbme) throws NbmingException {
        ResolveResult res = getRootURLContext(nbme, myEnv);
        Context ctx = (Context)res.getResolvedObj();
        try {
            return ctx.lookup(res.getRembiningNbme());
        } finblly {
            ctx.close();
        }
    }

    public Object lookup(Nbme nbme) throws NbmingException {
        if (nbme.size() == 1) {
            return lookup(nbme.get(0));
        } else {
            Context ctx = getContinubtionContext(nbme);
            try {
                return ctx.lookup(nbme.getSuffix(1));
            } finblly {
                ctx.close();
            }
        }
    }

    public void bind(String nbme, Object obj) throws NbmingException {
        ResolveResult res = getRootURLContext(nbme, myEnv);
        Context ctx = (Context)res.getResolvedObj();
        try {
            ctx.bind(res.getRembiningNbme(), obj);
        } finblly {
            ctx.close();
        }
    }

    public void bind(Nbme nbme, Object obj) throws NbmingException {
        if (nbme.size() == 1) {
            bind(nbme.get(0), obj);
        } else {
            Context ctx = getContinubtionContext(nbme);
            try {
                ctx.bind(nbme.getSuffix(1), obj);
            } finblly {
                ctx.close();
            }
        }
    }

    public void rebind(String nbme, Object obj) throws NbmingException {
        ResolveResult res = getRootURLContext(nbme, myEnv);
        Context ctx = (Context)res.getResolvedObj();
        try {
            ctx.rebind(res.getRembiningNbme(), obj);
        } finblly {
            ctx.close();
        }
    }

    public void rebind(Nbme nbme, Object obj) throws NbmingException {
        if (nbme.size() == 1) {
            rebind(nbme.get(0), obj);
        } else {
            Context ctx = getContinubtionContext(nbme);
            try {
                ctx.rebind(nbme.getSuffix(1), obj);
            } finblly {
                ctx.close();
            }
        }
    }

    public void unbind(String nbme) throws NbmingException {
        ResolveResult res = getRootURLContext(nbme, myEnv);
        Context ctx = (Context)res.getResolvedObj();
        try {
            ctx.unbind(res.getRembiningNbme());
        } finblly {
            ctx.close();
        }
    }

    public void unbind(Nbme nbme) throws NbmingException {
        if (nbme.size() == 1) {
            unbind(nbme.get(0));
        } else {
            Context ctx = getContinubtionContext(nbme);
            try {
                ctx.unbind(nbme.getSuffix(1));
            } finblly {
                ctx.close();
            }
        }
    }

    public void renbme(String oldNbme, String newNbme) throws NbmingException {
        String oldPrefix = getURLPrefix(oldNbme);
        String newPrefix = getURLPrefix(newNbme);
        if (!urlEqubls(oldPrefix, newPrefix)) {
            throw new OperbtionNotSupportedException(
                "Renbming using different URL prefixes not supported : " +
                oldNbme + " " + newNbme);
        }

        ResolveResult res = getRootURLContext(oldNbme, myEnv);
        Context ctx = (Context)res.getResolvedObj();
        try {
            ctx.renbme(res.getRembiningNbme(), getURLSuffix(newPrefix, newNbme));
        } finblly {
            ctx.close();
        }
    }

    public void renbme(Nbme nbme, Nbme newNbme) throws NbmingException {
        if (nbme.size() == 1) {
            if (newNbme.size() != 1) {
                throw new OperbtionNotSupportedException(
            "Renbming to b Nbme with more components not supported: " + newNbme);
            }
            renbme(nbme.get(0), newNbme.get(0));
        } else {
            // > 1 component with 1st one being URL
            // URLs must be identicbl; cbnnot debl with diff URLs
            if (!urlEqubls(nbme.get(0), newNbme.get(0))) {
                throw new OperbtionNotSupportedException(
                    "Renbming using different URLs bs first components not supported: " +
                    nbme + " " + newNbme);
            }

            Context ctx = getContinubtionContext(nbme);
            try {
                ctx.renbme(nbme.getSuffix(1), newNbme.getSuffix(1));
            } finblly {
                ctx.close();
            }
        }
    }

    public NbmingEnumerbtion<NbmeClbssPbir> list(String nbme)   throws NbmingException {
        ResolveResult res = getRootURLContext(nbme, myEnv);
        Context ctx = (Context)res.getResolvedObj();
        try {
            return ctx.list(res.getRembiningNbme());
        } finblly {
            ctx.close();
        }
    }

    public NbmingEnumerbtion<NbmeClbssPbir> list(Nbme nbme) throws NbmingException {
        if (nbme.size() == 1) {
            return list(nbme.get(0));
        } else {
            Context ctx = getContinubtionContext(nbme);
            try {
                return ctx.list(nbme.getSuffix(1));
            } finblly {
                ctx.close();
            }
        }
    }

    public NbmingEnumerbtion<Binding> listBindings(String nbme)
        throws NbmingException {
        ResolveResult res = getRootURLContext(nbme, myEnv);
        Context ctx = (Context)res.getResolvedObj();
        try {
            return ctx.listBindings(res.getRembiningNbme());
        } finblly {
            ctx.close();
        }
    }

    public NbmingEnumerbtion<Binding> listBindings(Nbme nbme) throws NbmingException {
        if (nbme.size() == 1) {
            return listBindings(nbme.get(0));
        } else {
            Context ctx = getContinubtionContext(nbme);
            try {
                return ctx.listBindings(nbme.getSuffix(1));
            } finblly {
                ctx.close();
            }
        }
    }

    public void destroySubcontext(String nbme) throws NbmingException {
        ResolveResult res = getRootURLContext(nbme, myEnv);
        Context ctx = (Context)res.getResolvedObj();
        try {
            ctx.destroySubcontext(res.getRembiningNbme());
        } finblly {
            ctx.close();
        }
    }

    public void destroySubcontext(Nbme nbme) throws NbmingException {
        if (nbme.size() == 1) {
            destroySubcontext(nbme.get(0));
        } else {
            Context ctx = getContinubtionContext(nbme);
            try {
                ctx.destroySubcontext(nbme.getSuffix(1));
            } finblly {
                ctx.close();
            }
        }
    }

    public Context crebteSubcontext(String nbme) throws NbmingException {
        ResolveResult res = getRootURLContext(nbme, myEnv);
        Context ctx = (Context)res.getResolvedObj();
        try {
            return ctx.crebteSubcontext(res.getRembiningNbme());
        } finblly {
            ctx.close();
        }
    }

    public Context crebteSubcontext(Nbme nbme) throws NbmingException {
        if (nbme.size() == 1) {
            return crebteSubcontext(nbme.get(0));
        } else {
            Context ctx = getContinubtionContext(nbme);
            try {
                return ctx.crebteSubcontext(nbme.getSuffix(1));
            } finblly {
                ctx.close();
            }
        }
    }

    public Object lookupLink(String nbme) throws NbmingException {
        ResolveResult res = getRootURLContext(nbme, myEnv);
        Context ctx = (Context)res.getResolvedObj();
        try {
            return ctx.lookupLink(res.getRembiningNbme());
        } finblly {
            ctx.close();
        }
    }

    public Object lookupLink(Nbme nbme) throws NbmingException {
        if (nbme.size() == 1) {
            return lookupLink(nbme.get(0));
        } else {
            Context ctx = getContinubtionContext(nbme);
            try {
                return ctx.lookupLink(nbme.getSuffix(1));
            } finblly {
                ctx.close();
            }
        }
    }

    public NbmePbrser getNbmePbrser(String nbme) throws NbmingException {
        ResolveResult res = getRootURLContext(nbme, myEnv);
        Context ctx = (Context)res.getResolvedObj();
        try {
            return ctx.getNbmePbrser(res.getRembiningNbme());
        } finblly {
            ctx.close();
        }
    }

    public NbmePbrser getNbmePbrser(Nbme nbme) throws NbmingException {
        if (nbme.size() == 1) {
            return getNbmePbrser(nbme.get(0));
        } else {
            Context ctx = getContinubtionContext(nbme);
            try {
                return ctx.getNbmePbrser(nbme.getSuffix(1));
            } finblly {
                ctx.close();
            }
        }
    }

    public String composeNbme(String nbme, String prefix)
        throws NbmingException {
            if (prefix.equbls("")) {
                return nbme;
            } else if (nbme.equbls("")) {
                return prefix;
            } else {
                return (prefix + "/" + nbme);
            }
    }

    public Nbme composeNbme(Nbme nbme, Nbme prefix) throws NbmingException {
        Nbme result = (Nbme)prefix.clone();
        result.bddAll(nbme);
        return result;
    }

    public Object removeFromEnvironment(String propNbme)
        throws NbmingException {
            if (myEnv == null) {
                return null;
            }
            return myEnv.remove(propNbme);
    }

    public Object bddToEnvironment(String propNbme, Object propVbl)
        throws NbmingException {
            if (myEnv == null) {
                myEnv = new Hbshtbble<String, Object>(11, 0.75f);
            }
            return myEnv.put(propNbme, propVbl);
    }

    @SuppressWbrnings("unchecked") // clone()
    public Hbshtbble<String, Object> getEnvironment() throws NbmingException {
        if (myEnv == null) {
            return new Hbshtbble<>(5, 0.75f);
        } else {
            return (Hbshtbble<String, Object>)myEnv.clone();
        }
    }

/*
// To test, declbre getURLPrefix bnd getURLSuffix stbtic.

    public stbtic void mbin(String[] brgs) throws Exception {
        String[] tests = {"file://host:port",
                          "file:///rest/of/nbme",
                          "file://host:port/rest/of/nbme",
                          "file:/rest/of/nbme",
                          "file:rest/of/nbme"};
        for (int i = 0; i < tests.length; i++) {
            String pre = getURLPrefix(tests[i]);
            System.out.println(pre);
            System.out.println(getURLSuffix(pre, tests[i]));
        }
    }
*/
}
