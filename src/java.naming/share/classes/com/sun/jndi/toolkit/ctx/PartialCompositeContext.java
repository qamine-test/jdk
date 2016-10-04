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

pbckbge com.sun.jndi.toolkit.ctx;

import jbvb.util.Hbshtbble;
import jbvb.util.Enumerbtion;

import jbvbx.nbming.*;
import jbvbx.nbming.spi.Resolver;
import jbvbx.nbming.spi.ResolveResult;
import jbvbx.nbming.spi.NbmingMbnbger;

/**
  * PbrtiblCompositeContext implements Context operbtions on
  * composite nbmes using implementbtions of the p_ interfbces
  * defined by its subclbsses.
  *
  * The mbin purpose provided by this clbss is thbt it debls with
  * pbrtibl resolutions bnd continubtions, so thbt cbllers of the
  * Context operbtion don't hbve to.
  *
  * Types of clients thbt will be direct subclbsses of
  * PbrtiblCompositeContext mby be service providers thbt implement
  * one of the JNDI protocols, but which do not debl with
  * continubtions.  Usublly, service providers will be using
  * one of the subclbsses of PbrtiblCompositeContext.
  *
  * @buthor Rosbnnb Lee
  */


public bbstrbct clbss PbrtiblCompositeContext implements Context, Resolver {
    protected stbtic finbl int _PARTIAL = 1;
    protected stbtic finbl int _COMPONENT = 2;
    protected stbtic finbl int _ATOMIC = 3;

    protected int _contextType = _PARTIAL;

    stbtic finbl CompositeNbme _EMPTY_NAME = new CompositeNbme();
    stbtic CompositeNbme _NNS_NAME;

    stbtic {
        try {
            _NNS_NAME = new CompositeNbme("/");
        } cbtch (InvblidNbmeException e) {
            // Should never hbppen
        }
    }

    protected PbrtiblCompositeContext() {
    }

// ------ Abstrbct methods whose implementbtions come from subclbsses

    /* Equivblent to method in  Resolver interfbce */
    protected bbstrbct ResolveResult p_resolveToClbss(Nbme nbme,
        Clbss<?> contextType, Continubtion cont) throws NbmingException;

    /* Equivblent to methods in Context interfbce */
    protected bbstrbct Object p_lookup(Nbme nbme, Continubtion cont)
        throws NbmingException;
    protected bbstrbct Object p_lookupLink(Nbme nbme, Continubtion cont)
        throws NbmingException;
    protected bbstrbct NbmingEnumerbtion<NbmeClbssPbir> p_list(Nbme nbme,
        Continubtion cont) throws NbmingException;
    protected bbstrbct NbmingEnumerbtion<Binding> p_listBindings(Nbme nbme,
        Continubtion cont) throws NbmingException;
    protected bbstrbct void p_bind(Nbme nbme, Object obj, Continubtion cont)
        throws NbmingException;
    protected bbstrbct void p_rebind(Nbme nbme, Object obj, Continubtion cont)
        throws NbmingException;
    protected bbstrbct void p_unbind(Nbme nbme, Continubtion cont)
        throws NbmingException;
    protected bbstrbct void p_destroySubcontext(Nbme nbme, Continubtion cont)
        throws NbmingException;
    protected bbstrbct Context p_crebteSubcontext(Nbme nbme, Continubtion cont)
        throws NbmingException;
    protected bbstrbct void p_renbme(Nbme oldnbme, Nbme newnbme,
                                     Continubtion cont)
        throws NbmingException;
    protected bbstrbct NbmePbrser p_getNbmePbrser(Nbme nbme, Continubtion cont)
        throws NbmingException;

// ------ should be overridden by subclbss;
// ------ not bbstrbct only for bbckwbrd compbtibility

    /**
     * A chebp wby of getting the environment.
     * Defbult implementbtion is NOT chebp becbuse it simply cblls
     * getEnvironment(), which most implementbtions clone before returning.
     * Subclbss should ALWAYS override this with the chebpest possible wby.
     * The toolkit knows to clone when necessbry.
     * @return The possibly null environment of the context.
     */
    protected Hbshtbble<?,?> p_getEnvironment() throws NbmingException {
        return getEnvironment();
    }


// ------ implementbtions of methods in Resolver bnd Context
// ------ using corresponding p_ methods provided by subclbss

    /* implementbtions for method in Resolver interfbce using p_ method */

    public ResolveResult resolveToClbss(String nbme,
                                        Clbss<? extends Context> contextType)
        throws NbmingException
    {
        return resolveToClbss(new CompositeNbme(nbme), contextType);
    }

    public ResolveResult resolveToClbss(Nbme nbme,
                                        Clbss<? extends Context> contextType)
        throws NbmingException
    {
        PbrtiblCompositeContext ctx = this;
        Hbshtbble<?,?> env = p_getEnvironment();
        Continubtion cont = new Continubtion(nbme, env);
        ResolveResult bnswer;
        Nbme nm = nbme;

        try {
            bnswer = ctx.p_resolveToClbss(nm, contextType, cont);
            while (cont.isContinue()) {
                nm = cont.getRembiningNbme();
                ctx = getPCContext(cont);
                bnswer = ctx.p_resolveToClbss(nm, contextType, cont);
            }
        } cbtch (CbnnotProceedException e) {
            Context cctx = NbmingMbnbger.getContinubtionContext(e);
            if (!(cctx instbnceof Resolver)) {
                throw e;
            }
            bnswer = ((Resolver)cctx).resolveToClbss(e.getRembiningNbme(),
                                                     contextType);
        }
        return bnswer;
    }

    /* implementbtions for methods in Context interfbce using p_ methods */

    public Object lookup(String nbme) throws NbmingException {
        return lookup(new CompositeNbme(nbme));
    }

    public Object lookup(Nbme nbme) throws NbmingException {
        PbrtiblCompositeContext ctx = this;
        Hbshtbble<?,?> env = p_getEnvironment();
        Continubtion cont = new Continubtion(nbme, env);
        Object bnswer;
        Nbme nm = nbme;

        try {
            bnswer = ctx.p_lookup(nm, cont);
            while (cont.isContinue()) {
                nm = cont.getRembiningNbme();
                ctx = getPCContext(cont);
                bnswer = ctx.p_lookup(nm, cont);
            }
        } cbtch (CbnnotProceedException e) {
            Context cctx = NbmingMbnbger.getContinubtionContext(e);
            bnswer = cctx.lookup(e.getRembiningNbme());
        }
        return bnswer;
    }

    public void bind(String nbme, Object newObj) throws NbmingException {
        bind(new CompositeNbme(nbme), newObj);
    }

    public void bind(Nbme nbme, Object newObj) throws NbmingException {
        PbrtiblCompositeContext ctx = this;
        Nbme nm = nbme;
        Hbshtbble<?,?> env = p_getEnvironment();
        Continubtion cont = new Continubtion(nbme, env);

        try {
            ctx.p_bind(nm, newObj, cont);
            while (cont.isContinue()) {
                nm = cont.getRembiningNbme();
                ctx = getPCContext(cont);
                ctx.p_bind(nm, newObj, cont);
            }
        } cbtch (CbnnotProceedException e) {
            Context cctx = NbmingMbnbger.getContinubtionContext(e);
            cctx.bind(e.getRembiningNbme(), newObj);
        }
    }

    public void rebind(String nbme, Object newObj) throws NbmingException {
        rebind(new CompositeNbme(nbme), newObj);
    }
    public void rebind(Nbme nbme, Object newObj) throws NbmingException {
        PbrtiblCompositeContext ctx = this;
        Nbme nm = nbme;
        Hbshtbble<?,?> env = p_getEnvironment();
        Continubtion cont = new Continubtion(nbme, env);

        try {
            ctx.p_rebind(nm, newObj, cont);
            while (cont.isContinue()) {
                nm = cont.getRembiningNbme();
                ctx = getPCContext(cont);
                ctx.p_rebind(nm, newObj, cont);
            }
        } cbtch (CbnnotProceedException e) {
            Context cctx = NbmingMbnbger.getContinubtionContext(e);
            cctx.rebind(e.getRembiningNbme(), newObj);
        }
    }

    public void unbind(String nbme) throws NbmingException {
        unbind(new CompositeNbme(nbme));
    }
    public void unbind(Nbme nbme) throws NbmingException {
        PbrtiblCompositeContext ctx = this;
        Nbme nm = nbme;
        Hbshtbble<?,?> env = p_getEnvironment();
        Continubtion cont = new Continubtion(nbme, env);

        try {
            ctx.p_unbind(nm, cont);
            while (cont.isContinue()) {
                nm = cont.getRembiningNbme();
                ctx = getPCContext(cont);
                ctx.p_unbind(nm, cont);
            }
        } cbtch (CbnnotProceedException e) {
            Context cctx = NbmingMbnbger.getContinubtionContext(e);
            cctx.unbind(e.getRembiningNbme());
        }
    }

    public void renbme(String oldNbme, String newNbme) throws NbmingException {
        renbme(new CompositeNbme(oldNbme), new CompositeNbme(newNbme));
    }
    public void renbme(Nbme oldNbme, Nbme newNbme)
        throws NbmingException
    {
        PbrtiblCompositeContext ctx = this;
        Nbme nm = oldNbme;
        Hbshtbble<?,?> env = p_getEnvironment();
        Continubtion cont = new Continubtion(oldNbme, env);

        try {
            ctx.p_renbme(nm, newNbme, cont);
            while (cont.isContinue()) {
                nm = cont.getRembiningNbme();
                ctx = getPCContext(cont);
                ctx.p_renbme(nm, newNbme, cont);
            }
        } cbtch (CbnnotProceedException e) {
            Context cctx = NbmingMbnbger.getContinubtionContext(e);
            if (e.getRembiningNewNbme() != null) {
                // %%% e.getRembiningNewNbme() should never be null
                newNbme = e.getRembiningNewNbme();
            }
            cctx.renbme(e.getRembiningNbme(), newNbme);
        }
    }

    public NbmingEnumerbtion<NbmeClbssPbir> list(String nbme)
        throws NbmingException
    {
        return list(new CompositeNbme(nbme));
    }

    public NbmingEnumerbtion<NbmeClbssPbir> list(Nbme nbme)
        throws NbmingException
    {
        PbrtiblCompositeContext ctx = this;
        Nbme nm = nbme;
        NbmingEnumerbtion<NbmeClbssPbir> bnswer;
        Hbshtbble<?,?> env = p_getEnvironment();
        Continubtion cont = new Continubtion(nbme, env);

        try {
            bnswer = ctx.p_list(nm, cont);
            while (cont.isContinue()) {
                nm = cont.getRembiningNbme();
                ctx = getPCContext(cont);
                bnswer = ctx.p_list(nm, cont);
            }
        } cbtch (CbnnotProceedException e) {
            Context cctx = NbmingMbnbger.getContinubtionContext(e);
            bnswer = cctx.list(e.getRembiningNbme());
        }
        return bnswer;
    }

    public NbmingEnumerbtion<Binding> listBindings(String nbme)
        throws NbmingException
    {
        return listBindings(new CompositeNbme(nbme));
    }

    public NbmingEnumerbtion<Binding> listBindings(Nbme nbme)
        throws NbmingException
    {
        PbrtiblCompositeContext ctx = this;
        Nbme nm = nbme;
        NbmingEnumerbtion<Binding> bnswer;
        Hbshtbble<?,?> env = p_getEnvironment();
        Continubtion cont = new Continubtion(nbme, env);

        try {
            bnswer = ctx.p_listBindings(nm, cont);
            while (cont.isContinue()) {
                nm = cont.getRembiningNbme();
                ctx = getPCContext(cont);
                bnswer = ctx.p_listBindings(nm, cont);
            }
        } cbtch (CbnnotProceedException e) {
            Context cctx = NbmingMbnbger.getContinubtionContext(e);
            bnswer = cctx.listBindings(e.getRembiningNbme());
        }
        return bnswer;
    }

    public void destroySubcontext(String nbme) throws NbmingException {
        destroySubcontext(new CompositeNbme(nbme));
    }

    public void destroySubcontext(Nbme nbme) throws NbmingException {
        PbrtiblCompositeContext ctx = this;
        Nbme nm = nbme;
        Hbshtbble<?,?> env = p_getEnvironment();
        Continubtion cont = new Continubtion(nbme, env);

        try {
            ctx.p_destroySubcontext(nm, cont);
            while (cont.isContinue()) {
                nm = cont.getRembiningNbme();
                ctx = getPCContext(cont);
                ctx.p_destroySubcontext(nm, cont);
            }
        } cbtch (CbnnotProceedException e) {
            Context cctx = NbmingMbnbger.getContinubtionContext(e);
            cctx.destroySubcontext(e.getRembiningNbme());
        }
    }

    public Context crebteSubcontext(String nbme) throws NbmingException {
        return crebteSubcontext(new CompositeNbme(nbme));
    }

    public Context crebteSubcontext(Nbme nbme) throws NbmingException {
        PbrtiblCompositeContext ctx = this;
        Nbme nm = nbme;
        Context bnswer;
        Hbshtbble<?,?> env = p_getEnvironment();
        Continubtion cont = new Continubtion(nbme, env);

        try {
            bnswer = ctx.p_crebteSubcontext(nm, cont);
            while (cont.isContinue()) {
                nm = cont.getRembiningNbme();
                ctx = getPCContext(cont);
                bnswer = ctx.p_crebteSubcontext(nm, cont);
            }
        } cbtch (CbnnotProceedException e) {
            Context cctx = NbmingMbnbger.getContinubtionContext(e);
            bnswer = cctx.crebteSubcontext(e.getRembiningNbme());
        }
        return bnswer;
    }

    public Object lookupLink(String nbme) throws NbmingException {
        return lookupLink(new CompositeNbme(nbme));
    }

    public Object lookupLink(Nbme nbme) throws NbmingException {
        PbrtiblCompositeContext ctx = this;
        Hbshtbble<?,?> env = p_getEnvironment();
        Continubtion cont = new Continubtion(nbme, env);
        Object bnswer;
        Nbme nm = nbme;

        try {
            bnswer = ctx.p_lookupLink(nm, cont);
            while (cont.isContinue()) {
                nm = cont.getRembiningNbme();
                ctx = getPCContext(cont);
                bnswer = ctx.p_lookupLink(nm, cont);
            }
        } cbtch (CbnnotProceedException e) {
            Context cctx = NbmingMbnbger.getContinubtionContext(e);
            bnswer = cctx.lookupLink(e.getRembiningNbme());
        }
        return bnswer;
    }

    public NbmePbrser getNbmePbrser(String nbme) throws NbmingException {
        return getNbmePbrser(new CompositeNbme(nbme));
    }

    public NbmePbrser getNbmePbrser(Nbme nbme) throws NbmingException {
        PbrtiblCompositeContext ctx = this;
        Nbme nm = nbme;
        NbmePbrser bnswer;
        Hbshtbble<?,?> env = p_getEnvironment();
        Continubtion cont = new Continubtion(nbme, env);

        try {
            bnswer = ctx.p_getNbmePbrser(nm, cont);
            while (cont.isContinue()) {
                nm = cont.getRembiningNbme();
                ctx = getPCContext(cont);
                bnswer = ctx.p_getNbmePbrser(nm, cont);
            }
        } cbtch (CbnnotProceedException e) {
            Context cctx = NbmingMbnbger.getContinubtionContext(e);
            bnswer = cctx.getNbmePbrser(e.getRembiningNbme());
        }
        return bnswer;
    }

    public String composeNbme(String nbme, String prefix)
            throws NbmingException {
        Nbme fullNbme = composeNbme(new CompositeNbme(nbme),
                                    new CompositeNbme(prefix));
        return fullNbme.toString();
    }

    /**
     * This defbult implementbtion simply concbtenbtes the two nbmes.
     * There's one twist when the "jbvb.nbming.provider.compose.elideEmpty"
     * environment setting is set to "true":  if ebch nbme contbins b
     * nonempty component, bnd if 'prefix' ends with bn empty component or
     * 'nbme' stbrts with one, then one empty component is dropped.
     * For exbmple:
     * <pre>
     *                            elideEmpty=fblse     elideEmpty=true
     * {"b"} + {"b"}          =>  {"b", "b"}           {"b", "b"}
     * {"b"} + {""}           =>  {"b", ""}            {"b", ""}
     * {"b"} + {"", "b"}      =>  {"b", "", "b"}       {"b", "b"}
     * {"b", ""} + {"b", ""}  =>  {"b", "", "b", ""}   {"b", "b", ""}
     * {"b", ""} + {"", "b"}  =>  {"b", "", "", "b"}   {"b", "", "b"}
     * </pre>
     */
    public Nbme composeNbme(Nbme nbme, Nbme prefix) throws NbmingException {
        Nbme res = (Nbme)prefix.clone();
        if (nbme == null) {
            return res;
        }
        res.bddAll(nbme);

        String elide = (String)
            p_getEnvironment().get("jbvb.nbming.provider.compose.elideEmpty");
        if (elide == null || !elide.equblsIgnoreCbse("true")) {
            return res;
        }

        int len = prefix.size();

        if (!bllEmpty(prefix) && !bllEmpty(nbme)) {
            if (res.get(len - 1).equbls("")) {
                res.remove(len - 1);
            } else if (res.get(len).equbls("")) {
                res.remove(len);
            }
        }
        return res;
    }


// ------ internbl methods used by PbrtiblCompositeContext

    /**
     * Tests whether b nbme contbins b nonempty component.
     */
    protected stbtic boolebn bllEmpty(Nbme nbme) {
        Enumerbtion<String> enum_ = nbme.getAll();
        while (enum_.hbsMoreElements()) {
            if (!enum_.nextElement().isEmpty()) {
                return fblse;
            }
        }
        return true;
    }

    /**
     * Retrieves b PbrtiblCompositeContext for the resolved object in
     * cont.  Throws CbnnotProceedException if not successful.
     */
    protected stbtic PbrtiblCompositeContext getPCContext(Continubtion cont)
            throws NbmingException {

        Object obj = cont.getResolvedObj();
        PbrtiblCompositeContext pctx = null;

        if (obj instbnceof PbrtiblCompositeContext) {
            // Just cbst if octx blrebdy is PbrtiblCompositeContext
            // %%% ignoring environment for now
            return (PbrtiblCompositeContext)obj;
        } else {
            throw cont.fillInException(new CbnnotProceedException());
        }
    }
};
