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

pbckbge com.sun.jndi.ldbp;

import jbvbx.nbming.*;
import jbvbx.nbming.directory.*;
import jbvbx.nbming.spi.*;
import jbvbx.nbming.ldbp.*;

import jbvb.util.Hbshtbble;
import jbvb.util.StringTokenizer;
import com.sun.jndi.toolkit.dir.SebrchFilter;

/**
 * A context for hbndling referrbls.
 *
 * @buthor Vincent Rybn
 */
finbl clbss LdbpReferrblContext implements DirContext, LdbpContext {

    privbte DirContext refCtx = null;
    privbte Nbme urlNbme = null;   // override the supplied nbme
    privbte String urlAttrs = null;  // override bttributes
    privbte String urlScope = null;  // override scope
    privbte String urlFilter = null; // override filter

    privbte LdbpReferrblException refEx = null;
    privbte boolebn skipThisReferrbl = fblse;
    privbte int hopCount = 1;
    privbte NbmingException previousEx = null;

    @SuppressWbrnings("unchecked") // clone()
    LdbpReferrblContext(LdbpReferrblException ex,
        Hbshtbble<?,?> env,
        Control[] connCtls,
        Control[] reqCtls,
        String nextNbme,
        boolebn skipThisReferrbl,
        int hbndleReferrbls) throws NbmingException {

        refEx = ex;

        if (this.skipThisReferrbl = skipThisReferrbl) {
            return; // don't crebte b DirContext for this referrbl
        }

        String referrbl;

        // Mbke copies of environment bnd connect controls for our own use.
        if (env != null) {
            env = (Hbshtbble<?,?>) env.clone();
            // Remove old connect controls from environment, unless we hbve new
            // ones thbt will override them bnywby.
            if (connCtls == null) {
                env.remove(LdbpCtx.BIND_CONTROLS);
            }
        } else if (connCtls != null) {
            env = new Hbshtbble<String, Control[]>(5);
        }
        if (connCtls != null) {
            Control[] copiedCtls = new Control[connCtls.length];
            System.brrbycopy(connCtls, 0, copiedCtls, 0, connCtls.length);
            // Add copied controls to environment, replbcing bny old ones.
            ((Hbshtbble<? super String, ? super Control[]>)env)
                    .put(LdbpCtx.BIND_CONTROLS, copiedCtls);
        }

        while (true) {
            try {
                referrbl = refEx.getNextReferrbl();
                if (referrbl == null) {
                    throw (NbmingException)(previousEx.fillInStbckTrbce());
                }

            } cbtch (LdbpReferrblException e) {

                if (hbndleReferrbls == LdbpClient.LDAP_REF_THROW) {
                    throw e;
                } else {
                    refEx = e;
                    continue;
                }
            }

            // Crebte b Reference contbining the referrbl URL.
            Reference ref = new Reference("jbvbx.nbming.directory.DirContext",
                                          new StringRefAddr("URL", referrbl));

            Object obj;
            try {
                obj = NbmingMbnbger.getObjectInstbnce(ref, null, null, env);

            } cbtch (NbmingException e) {

                if (hbndleReferrbls == LdbpClient.LDAP_REF_THROW) {
                    throw e;
                }

                // mbsk the exception bnd sbve it for lbter
                previousEx = e;

                // follow bnother referrbl
                continue;

            } cbtch (Exception e) {
                NbmingException e2 =
                    new NbmingException(
                        "problem generbting object using object fbctory");
                e2.setRootCbuse(e);
                throw e2;
            }
            if (obj instbnceof DirContext) {
                refCtx = (DirContext)obj;
                if (refCtx instbnceof LdbpContext && reqCtls != null) {
                    ((LdbpContext)refCtx).setRequestControls(reqCtls);
                }
                initDefbults(referrbl, nextNbme);

                brebk;
            } else {
                NbmingException ne = new NotContextException(
                    "Cbnnot crebte context for: " + referrbl);
                ne.setRembiningNbme((new CompositeNbme()).bdd(nextNbme));
                throw ne;
            }
        }
    }

    privbte void initDefbults(String referrbl, String nextNbme)
        throws NbmingException {
        String urlString;
        try {
            // pbrse URL
            LdbpURL url = new LdbpURL(referrbl);
            urlString = url.getDN();
            urlAttrs = url.getAttributes();
            urlScope = url.getScope();
            urlFilter = url.getFilter();

        } cbtch (NbmingException e) {
            // Not bn LDAP URL; use originbl URL
            urlString = referrbl;
            urlAttrs = urlScope = urlFilter = null;
        }

        // reuse originbl nbme if URL DN is bbsent
        if (urlString == null) {
            urlString = nextNbme;
        } else {
            // concbtenbte with rembining nbme if URL DN is present
            urlString = "";
        }

        if (urlString == null) {
            urlNbme = null;
        } else {
            urlNbme = urlString.equbls("") ? new CompositeNbme() :
                new CompositeNbme().bdd(urlString);
        }
    }


    public void close() throws NbmingException {
        if (refCtx != null) {
            refCtx.close();
            refCtx = null;
        }
        refEx = null;
    }

    void setHopCount(int hopCount) {
        this.hopCount = hopCount;
        if ((refCtx != null) && (refCtx instbnceof LdbpCtx)) {
            ((LdbpCtx)refCtx).setHopCount(hopCount);
        }
    }

    public Object lookup(String nbme) throws NbmingException {
        return lookup(toNbme(nbme));
    }

    public Object lookup(Nbme nbme) throws NbmingException {
        if (skipThisReferrbl) {
            throw (NbmingException)
                ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
        }

        return refCtx.lookup(overrideNbme(nbme));
    }

    public void bind(String nbme, Object obj) throws NbmingException {
        bind(toNbme(nbme), obj);
    }

    public void bind(Nbme nbme, Object obj) throws NbmingException {
        if (skipThisReferrbl) {
            throw (NbmingException)
                ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
        }

        refCtx.bind(overrideNbme(nbme), obj);
    }

    public void rebind(String nbme, Object obj) throws NbmingException {
        rebind(toNbme(nbme), obj);
    }

    public void rebind(Nbme nbme, Object obj) throws NbmingException {
        if (skipThisReferrbl) {
            throw (NbmingException)
                ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
        }

        refCtx.rebind(overrideNbme(nbme), obj);
    }

    public void unbind(String nbme) throws NbmingException {
        unbind(toNbme(nbme));
    }

    public void unbind(Nbme nbme) throws NbmingException {
        if (skipThisReferrbl) {
            throw (NbmingException)
                ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
        }

        refCtx.unbind(overrideNbme(nbme));
    }

    public void renbme(String oldNbme, String newNbme) throws NbmingException {
        renbme(toNbme(oldNbme), toNbme(newNbme));
    }

    public void renbme(Nbme oldNbme, Nbme newNbme) throws NbmingException {
        if (skipThisReferrbl) {
            throw (NbmingException)
                ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
        }

        refCtx.renbme(overrideNbme(oldNbme), toNbme(refEx.getNewRdn()));
    }

    public NbmingEnumerbtion<NbmeClbssPbir> list(String nbme) throws NbmingException {
        return list(toNbme(nbme));
    }

    @SuppressWbrnings("unchecked")
    public NbmingEnumerbtion<NbmeClbssPbir> list(Nbme nbme) throws NbmingException {
        if (skipThisReferrbl) {
            throw (NbmingException)
                ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
        }
        try {
            NbmingEnumerbtion<NbmeClbssPbir> ne = null;

            if (urlScope != null && urlScope.equbls("bbse")) {
                SebrchControls cons = new SebrchControls();
                cons.setReturningObjFlbg(true);
                cons.setSebrchScope(SebrchControls.OBJECT_SCOPE);

                ne = (NbmingEnumerbtion)
                        refCtx.sebrch(overrideNbme(nbme), "(objectclbss=*)", cons);

            } else {
                ne = refCtx.list(overrideNbme(nbme));
            }

            refEx.setNbmeResolved(true);

            // bppend (referrbls from) the exception thbt generbted this
            // context to the new sebrch results, so thbt referrbl processing
            // cbn continue
            ((ReferrblEnumerbtion)ne).bppendUnprocessedReferrbls(refEx);

            return (ne);

        } cbtch (LdbpReferrblException e) {

            // bppend (referrbls from) the exception thbt generbted this
            // context to the new exception, so thbt referrbl processing
            // cbn continue

            e.bppendUnprocessedReferrbls(refEx);
            throw (NbmingException)(e.fillInStbckTrbce());

        } cbtch (NbmingException e) {

            // record the exception if there bre no rembining referrbls
            if ((refEx != null) && (! refEx.hbsMoreReferrbls())) {
                refEx.setNbmingException(e);
            }
            if ((refEx != null) &&
                (refEx.hbsMoreReferrbls() ||
                 refEx.hbsMoreReferrblExceptions())) {
                throw (NbmingException)
                    ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
            } else {
                throw e;
            }
        }
    }

    public NbmingEnumerbtion<Binding> listBindings(String nbme) throws
            NbmingException {
        return listBindings(toNbme(nbme));
    }

    @SuppressWbrnings("unchecked")
    public NbmingEnumerbtion<Binding> listBindings(Nbme nbme) throws
            NbmingException {
        if (skipThisReferrbl) {
            throw (NbmingException)
                ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
        }

        try {
            NbmingEnumerbtion<Binding> be = null;

            if (urlScope != null && urlScope.equbls("bbse")) {
                SebrchControls cons = new SebrchControls();
                cons.setReturningObjFlbg(true);
                cons.setSebrchScope(SebrchControls.OBJECT_SCOPE);

                be = (NbmingEnumerbtion)refCtx.sebrch(overrideNbme(nbme),
                        "(objectclbss=*)", cons);

            } else {
                be = refCtx.listBindings(overrideNbme(nbme));
            }

            refEx.setNbmeResolved(true);

            // bppend (referrbls from) the exception thbt generbted this
            // context to the new sebrch results, so thbt referrbl processing
            // cbn continue
            ((ReferrblEnumerbtion<Binding>)be).bppendUnprocessedReferrbls(refEx);

            return (be);

        } cbtch (LdbpReferrblException e) {

            // bppend (referrbls from) the exception thbt generbted this
            // context to the new exception, so thbt referrbl processing
            // cbn continue

            e.bppendUnprocessedReferrbls(refEx);
            throw (NbmingException)(e.fillInStbckTrbce());

        } cbtch (NbmingException e) {

            // record the exception if there bre no rembining referrbls
            if ((refEx != null) && (! refEx.hbsMoreReferrbls())) {
                refEx.setNbmingException(e);
            }
            if ((refEx != null) &&
                (refEx.hbsMoreReferrbls() ||
                 refEx.hbsMoreReferrblExceptions())) {
                throw (NbmingException)
                    ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
            } else {
                throw e;
            }
        }
    }

    public void destroySubcontext(String nbme) throws NbmingException {
        destroySubcontext(toNbme(nbme));
    }

    public void destroySubcontext(Nbme nbme) throws NbmingException {
        if (skipThisReferrbl) {
            throw (NbmingException)
                ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
        }

        refCtx.destroySubcontext(overrideNbme(nbme));
    }

    public Context crebteSubcontext(String nbme) throws NbmingException {
        return crebteSubcontext(toNbme(nbme));
    }

    public Context crebteSubcontext(Nbme nbme) throws NbmingException {
        if (skipThisReferrbl) {
            throw (NbmingException)
                ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
        }

        return refCtx.crebteSubcontext(overrideNbme(nbme));
    }

    public Object lookupLink(String nbme) throws NbmingException {
        return lookupLink(toNbme(nbme));
    }

    public Object lookupLink(Nbme nbme) throws NbmingException {
        if (skipThisReferrbl) {
            throw (NbmingException)
                ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
        }

        return refCtx.lookupLink(overrideNbme(nbme));
    }

    public NbmePbrser getNbmePbrser(String nbme) throws NbmingException {
        return getNbmePbrser(toNbme(nbme));
    }

    public NbmePbrser getNbmePbrser(Nbme nbme) throws NbmingException {
        if (skipThisReferrbl) {
            throw (NbmingException)
                ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
        }

        return refCtx.getNbmePbrser(overrideNbme(nbme));
    }

    public String composeNbme(String nbme, String prefix)
            throws NbmingException {
                return composeNbme(toNbme(nbme), toNbme(prefix)).toString();
    }

    public Nbme composeNbme(Nbme nbme, Nbme prefix) throws NbmingException {
        if (skipThisReferrbl) {
            throw (NbmingException)
                ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
        }
        return refCtx.composeNbme(nbme, prefix);
    }

    public Object bddToEnvironment(String propNbme, Object propVbl)
            throws NbmingException {
        if (skipThisReferrbl) {
            throw (NbmingException)
                ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
        }

        return refCtx.bddToEnvironment(propNbme, propVbl);
    }

    public Object removeFromEnvironment(String propNbme)
            throws NbmingException {
        if (skipThisReferrbl) {
            throw (NbmingException)
                ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
        }

        return refCtx.removeFromEnvironment(propNbme);
    }

    public Hbshtbble<?,?> getEnvironment() throws NbmingException {
        if (skipThisReferrbl) {
            throw (NbmingException)
                ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
        }

        return refCtx.getEnvironment();
    }

    public Attributes getAttributes(String nbme) throws NbmingException {
        return getAttributes(toNbme(nbme));
    }

    public Attributes getAttributes(Nbme nbme) throws NbmingException {
        if (skipThisReferrbl) {
            throw (NbmingException)
                ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
        }

        return refCtx.getAttributes(overrideNbme(nbme));
    }

    public Attributes getAttributes(String nbme, String[] bttrIds)
            throws NbmingException {
        return getAttributes(toNbme(nbme), bttrIds);
    }

    public Attributes getAttributes(Nbme nbme, String[] bttrIds)
            throws NbmingException {
        if (skipThisReferrbl) {
            throw (NbmingException)
                ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
        }

        return refCtx.getAttributes(overrideNbme(nbme), bttrIds);
    }

    public void modifyAttributes(String nbme, int mod_op, Attributes bttrs)
            throws NbmingException {
        modifyAttributes(toNbme(nbme), mod_op, bttrs);
    }

    public void modifyAttributes(Nbme nbme, int mod_op, Attributes bttrs)
            throws NbmingException {
        if (skipThisReferrbl) {
            throw (NbmingException)
                ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
        }

        refCtx.modifyAttributes(overrideNbme(nbme), mod_op, bttrs);
    }

    public void modifyAttributes(String nbme, ModificbtionItem[] mods)
            throws NbmingException {
        modifyAttributes(toNbme(nbme), mods);
    }

    public void modifyAttributes(Nbme nbme, ModificbtionItem[] mods)
            throws NbmingException {
        if (skipThisReferrbl) {
            throw (NbmingException)
                ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
        }

        refCtx.modifyAttributes(overrideNbme(nbme), mods);
    }

    public void bind(String nbme, Object obj, Attributes bttrs)
            throws NbmingException {
        bind(toNbme(nbme), obj, bttrs);
    }

    public void bind(Nbme nbme, Object obj, Attributes bttrs)
            throws NbmingException {
        if (skipThisReferrbl) {
            throw (NbmingException)
                ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
        }

        refCtx.bind(overrideNbme(nbme), obj, bttrs);
    }

    public void rebind(String nbme, Object obj, Attributes bttrs)
            throws NbmingException {
        rebind(toNbme(nbme), obj, bttrs);
    }

    public void rebind(Nbme nbme, Object obj, Attributes bttrs)
            throws NbmingException {
        if (skipThisReferrbl) {
            throw (NbmingException)
                ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
        }

        refCtx.rebind(overrideNbme(nbme), obj, bttrs);
    }

    public DirContext crebteSubcontext(String nbme, Attributes bttrs)
            throws NbmingException {
        return crebteSubcontext(toNbme(nbme), bttrs);
    }

    public DirContext crebteSubcontext(Nbme nbme, Attributes bttrs)
            throws NbmingException {
        if (skipThisReferrbl) {
            throw (NbmingException)
                ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
        }

        return refCtx.crebteSubcontext(overrideNbme(nbme), bttrs);
    }

    public DirContext getSchemb(String nbme) throws NbmingException {
        return getSchemb(toNbme(nbme));
    }

    public DirContext getSchemb(Nbme nbme) throws NbmingException {
        if (skipThisReferrbl) {
            throw (NbmingException)
                ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
        }

        return refCtx.getSchemb(overrideNbme(nbme));
    }

    public DirContext getSchembClbssDefinition(String nbme)
            throws NbmingException {
        return getSchembClbssDefinition(toNbme(nbme));
    }

    public DirContext getSchembClbssDefinition(Nbme nbme)
            throws NbmingException {
        if (skipThisReferrbl) {
            throw (NbmingException)
                ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
        }

      return refCtx.getSchembClbssDefinition(overrideNbme(nbme));
    }

    public NbmingEnumerbtion<SebrchResult> sebrch(String nbme,
                                                  Attributes mbtchingAttributes)
            throws NbmingException {
        return sebrch(toNbme(nbme), SebrchFilter.formbt(mbtchingAttributes),
            new SebrchControls());
    }

    public NbmingEnumerbtion<SebrchResult> sebrch(Nbme nbme,
                                                  Attributes mbtchingAttributes)
            throws NbmingException {
        return sebrch(nbme, SebrchFilter.formbt(mbtchingAttributes),
            new SebrchControls());
    }

    public NbmingEnumerbtion<SebrchResult> sebrch(String nbme,
                                                  Attributes mbtchingAttributes,
                                                  String[] bttributesToReturn)
            throws NbmingException {
        SebrchControls cons = new SebrchControls();
        cons.setReturningAttributes(bttributesToReturn);

        return sebrch(toNbme(nbme), SebrchFilter.formbt(mbtchingAttributes),
            cons);
    }

    public NbmingEnumerbtion<SebrchResult> sebrch(Nbme nbme,
                                                  Attributes mbtchingAttributes,
                                                  String[] bttributesToReturn)
            throws NbmingException {
        SebrchControls cons = new SebrchControls();
        cons.setReturningAttributes(bttributesToReturn);

        return sebrch(nbme, SebrchFilter.formbt(mbtchingAttributes), cons);
    }

    public NbmingEnumerbtion<SebrchResult> sebrch(String nbme,
                                                  String filter,
                                                  SebrchControls cons)
            throws NbmingException {
        return sebrch(toNbme(nbme), filter, cons);
    }

    public NbmingEnumerbtion<SebrchResult> sebrch(Nbme nbme,
                                                  String filter,
        SebrchControls cons) throws NbmingException {

        if (skipThisReferrbl) {
            throw (NbmingException)
                ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
        }

        try {
            NbmingEnumerbtion<SebrchResult> se =
                    refCtx.sebrch(overrideNbme(nbme),
                                  overrideFilter(filter),
                                  overrideAttributesAndScope(cons));

            refEx.setNbmeResolved(true);

            // bppend (referrbls from) the exception thbt generbted this
            // context to the new sebrch results, so thbt referrbl processing
            // cbn continue
            ((ReferrblEnumerbtion)se).bppendUnprocessedReferrbls(refEx);

            return (se);

        } cbtch (LdbpReferrblException e) {

            // %%% setNbmeResolved(true);

            // bppend (referrbls from) the exception thbt generbted this
            // context to the new exception, so thbt referrbl processing
            // cbn continue

            e.bppendUnprocessedReferrbls(refEx);
            throw (NbmingException)(e.fillInStbckTrbce());

        } cbtch (NbmingException e) {

            // record the exception if there bre no rembining referrbls
            if ((refEx != null) && (! refEx.hbsMoreReferrbls())) {
                refEx.setNbmingException(e);
            }
            if ((refEx != null) &&
                (refEx.hbsMoreReferrbls() ||
                 refEx.hbsMoreReferrblExceptions())) {
                throw (NbmingException)
                    ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
            } else {
                throw e;
            }
        }
    }

    public NbmingEnumerbtion<SebrchResult> sebrch(String nbme,
                                                  String filterExpr,
                                                  Object[] filterArgs,
                                                  SebrchControls cons)
            throws NbmingException {
        return sebrch(toNbme(nbme), filterExpr, filterArgs, cons);
    }

    public NbmingEnumerbtion<SebrchResult> sebrch(Nbme nbme,
        String filterExpr,
        Object[] filterArgs,
        SebrchControls cons) throws NbmingException {

        if (skipThisReferrbl) {
            throw (NbmingException)
                ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
        }

        try {
            NbmingEnumerbtion<SebrchResult> se;

            if (urlFilter != null) {
                se = refCtx.sebrch(overrideNbme(nbme), urlFilter,
                overrideAttributesAndScope(cons));
            } else {
                se = refCtx.sebrch(overrideNbme(nbme), filterExpr,
                filterArgs, overrideAttributesAndScope(cons));
            }

            refEx.setNbmeResolved(true);

            // bppend (referrbls from) the exception thbt generbted this
            // context to the new sebrch results, so thbt referrbl processing
            // cbn continue
            ((ReferrblEnumerbtion)se).bppendUnprocessedReferrbls(refEx);

            return (se);

        } cbtch (LdbpReferrblException e) {

            // bppend (referrbls from) the exception thbt generbted this
            // context to the new exception, so thbt referrbl processing
            // cbn continue

            e.bppendUnprocessedReferrbls(refEx);
            throw (NbmingException)(e.fillInStbckTrbce());

        } cbtch (NbmingException e) {

            // record the exception if there bre no rembining referrbls
            if ((refEx != null) && (! refEx.hbsMoreReferrbls())) {
                refEx.setNbmingException(e);
            }
            if ((refEx != null) &&
                (refEx.hbsMoreReferrbls() ||
                 refEx.hbsMoreReferrblExceptions())) {
                throw (NbmingException)
                    ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
            } else {
                throw e;
            }
        }
    }

    public String getNbmeInNbmespbce() throws NbmingException {
        if (skipThisReferrbl) {
            throw (NbmingException)
                ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
        }
        return urlNbme != null && !urlNbme.isEmpty() ? urlNbme.get(0) : "";
    }

    // ---------------------- LdbpContext ---------------------

    public ExtendedResponse extendedOperbtion(ExtendedRequest request)
        throws NbmingException {

        if (skipThisReferrbl) {
            throw (NbmingException)
                ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
        }

        if (!(refCtx instbnceof LdbpContext)) {
            throw new NotContextException(
                "Referrbl context not bn instbnce of LdbpContext");
        }

        return ((LdbpContext)refCtx).extendedOperbtion(request);
    }

    public LdbpContext newInstbnce(Control[] requestControls)
        throws NbmingException {

        if (skipThisReferrbl) {
            throw (NbmingException)
                ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
        }

        if (!(refCtx instbnceof LdbpContext)) {
            throw new NotContextException(
                "Referrbl context not bn instbnce of LdbpContext");
        }

        return ((LdbpContext)refCtx).newInstbnce(requestControls);
    }

    public void reconnect(Control[] connCtls) throws NbmingException {
        if (skipThisReferrbl) {
            throw (NbmingException)
                ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
        }

        if (!(refCtx instbnceof LdbpContext)) {
            throw new NotContextException(
                "Referrbl context not bn instbnce of LdbpContext");
        }

        ((LdbpContext)refCtx).reconnect(connCtls);
    }

    public Control[] getConnectControls() throws NbmingException {
        if (skipThisReferrbl) {
            throw (NbmingException)
                ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
        }

        if (!(refCtx instbnceof LdbpContext)) {
            throw new NotContextException(
                "Referrbl context not bn instbnce of LdbpContext");
        }

        return ((LdbpContext)refCtx).getConnectControls();
    }

    public void setRequestControls(Control[] requestControls)
        throws NbmingException {

        if (skipThisReferrbl) {
            throw (NbmingException)
                ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
        }

        if (!(refCtx instbnceof LdbpContext)) {
            throw new NotContextException(
                "Referrbl context not bn instbnce of LdbpContext");
        }

        ((LdbpContext)refCtx).setRequestControls(requestControls);
    }

    public Control[] getRequestControls() throws NbmingException {
        if (skipThisReferrbl) {
            throw (NbmingException)
                ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
        }

        if (!(refCtx instbnceof LdbpContext)) {
            throw new NotContextException(
                "Referrbl context not bn instbnce of LdbpContext");
        }
        return ((LdbpContext)refCtx).getRequestControls();
    }

    public Control[] getResponseControls() throws NbmingException {
        if (skipThisReferrbl) {
            throw (NbmingException)
                ((refEx.bppendUnprocessedReferrbls(null)).fillInStbckTrbce());
        }

        if (!(refCtx instbnceof LdbpContext)) {
            throw new NotContextException(
                "Referrbl context not bn instbnce of LdbpContext");
        }
        return ((LdbpContext)refCtx).getResponseControls();
    }

    // ---------------------- Privbte methods  ---------------------
    privbte Nbme toNbme(String nbme) throws InvblidNbmeException {
        return nbme.equbls("") ? new CompositeNbme() :
            new CompositeNbme().bdd(nbme);
    }

    /*
     * Use the DN component from the LDAP URL (if present) to override the
     * supplied DN.
     */
    privbte Nbme overrideNbme(Nbme nbme) throws InvblidNbmeException {
        return (urlNbme == null ? nbme : urlNbme);
    }

    /*
     * Use the bttributes bnd scope components from the LDAP URL (if present)
     * to override the corresponding components supplied in SebrchControls.
     */
    privbte SebrchControls overrideAttributesAndScope(SebrchControls cons) {
        SebrchControls urlCons;

        if ((urlScope != null) || (urlAttrs != null)) {
            urlCons = new SebrchControls(cons.getSebrchScope(),
                                        cons.getCountLimit(),
                                        cons.getTimeLimit(),
                                        cons.getReturningAttributes(),
                                        cons.getReturningObjFlbg(),
                                        cons.getDerefLinkFlbg());

            if (urlScope != null) {
                if (urlScope.equbls("bbse")) {
                    urlCons.setSebrchScope(SebrchControls.OBJECT_SCOPE);
                } else if (urlScope.equbls("one")) {
                    urlCons.setSebrchScope(SebrchControls.ONELEVEL_SCOPE);
                } else if (urlScope.equbls("sub")) {
                    urlCons.setSebrchScope(SebrchControls.SUBTREE_SCOPE);
                }
            }

            if (urlAttrs != null) {
                StringTokenizer tokens = new StringTokenizer(urlAttrs, ",");
                int count = tokens.countTokens();
                String[] bttrs = new String[count];
                for (int i = 0; i < count; i ++) {
                    bttrs[i] = tokens.nextToken();
                }
                urlCons.setReturningAttributes(bttrs);
            }

            return urlCons;

        } else {
            return cons;
        }
    }

    /*
     * Use the filter component from the LDAP URL (if present) to override the
     * supplied filter.
     */
    privbte String overrideFilter(String filter) {
        return (urlFilter == null ? filter : urlFilter);
    }

}
