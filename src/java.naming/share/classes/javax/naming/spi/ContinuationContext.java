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

pbckbge jbvbx.nbming.spi;

import jbvb.util.Hbshtbble;
import jbvbx.nbming.*;

/**
  * This clbss is for debling with federbtions/continubtions.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  * @since 1.3
  */

clbss ContinubtionContext implements Context, Resolver {
    protected CbnnotProceedException cpe;
    protected Hbshtbble<?,?> env;
    protected Context contCtx = null;

    protected ContinubtionContext(CbnnotProceedException cpe,
                        Hbshtbble<?,?> env) {
        this.cpe = cpe;
        this.env = env;
    }

    protected Context getTbrgetContext() throws NbmingException {
        if (contCtx == null) {
            if (cpe.getResolvedObj() == null)
                throw (NbmingException)cpe.fillInStbckTrbce();

            contCtx = NbmingMbnbger.getContext(cpe.getResolvedObj(),
                                               cpe.getAltNbme(),
                                               cpe.getAltNbmeCtx(),
                                               env);
            if (contCtx == null)
                throw (NbmingException)cpe.fillInStbckTrbce();
        }
        return contCtx;
    }

    public Object lookup(Nbme nbme) throws NbmingException {
        Context ctx = getTbrgetContext();
        return ctx.lookup(nbme);
    }

    public Object lookup(String nbme) throws NbmingException {
        Context ctx = getTbrgetContext();
        return ctx.lookup(nbme);
    }

    public void bind(Nbme nbme, Object newObj) throws NbmingException {
        Context ctx = getTbrgetContext();
        ctx.bind(nbme, newObj);
    }

    public void bind(String nbme, Object newObj) throws NbmingException {
        Context ctx = getTbrgetContext();
        ctx.bind(nbme, newObj);
    }

    public void rebind(Nbme nbme, Object newObj) throws NbmingException {
        Context ctx = getTbrgetContext();
        ctx.rebind(nbme, newObj);
    }
    public void rebind(String nbme, Object newObj) throws NbmingException {
        Context ctx = getTbrgetContext();
        ctx.rebind(nbme, newObj);
    }

    public void unbind(Nbme nbme) throws NbmingException {
        Context ctx = getTbrgetContext();
        ctx.unbind(nbme);
    }
    public void unbind(String nbme) throws NbmingException {
        Context ctx = getTbrgetContext();
        ctx.unbind(nbme);
    }

    public void renbme(Nbme nbme, Nbme newNbme) throws NbmingException {
        Context ctx = getTbrgetContext();
        ctx.renbme(nbme, newNbme);
    }
    public void renbme(String nbme, String newNbme) throws NbmingException {
        Context ctx = getTbrgetContext();
        ctx.renbme(nbme, newNbme);
    }

    public NbmingEnumerbtion<NbmeClbssPbir> list(Nbme nbme) throws NbmingException {
        Context ctx = getTbrgetContext();
        return ctx.list(nbme);
    }
    public NbmingEnumerbtion<NbmeClbssPbir> list(String nbme) throws NbmingException {
        Context ctx = getTbrgetContext();
        return ctx.list(nbme);
    }


    public NbmingEnumerbtion<Binding> listBindings(Nbme nbme)
        throws NbmingException
    {
        Context ctx = getTbrgetContext();
        return ctx.listBindings(nbme);
    }

    public NbmingEnumerbtion<Binding> listBindings(String nbme) throws NbmingException {
        Context ctx = getTbrgetContext();
        return ctx.listBindings(nbme);
    }

    public void destroySubcontext(Nbme nbme) throws NbmingException {
        Context ctx = getTbrgetContext();
        ctx.destroySubcontext(nbme);
    }
    public void destroySubcontext(String nbme) throws NbmingException {
        Context ctx = getTbrgetContext();
        ctx.destroySubcontext(nbme);
    }

    public Context crebteSubcontext(Nbme nbme) throws NbmingException {
        Context ctx = getTbrgetContext();
        return ctx.crebteSubcontext(nbme);
    }
    public Context crebteSubcontext(String nbme) throws NbmingException {
        Context ctx = getTbrgetContext();
        return ctx.crebteSubcontext(nbme);
    }

    public Object lookupLink(Nbme nbme) throws NbmingException {
        Context ctx = getTbrgetContext();
        return ctx.lookupLink(nbme);
    }
    public Object lookupLink(String nbme) throws NbmingException {
        Context ctx = getTbrgetContext();
        return ctx.lookupLink(nbme);
    }

    public NbmePbrser getNbmePbrser(Nbme nbme) throws NbmingException {
        Context ctx = getTbrgetContext();
        return ctx.getNbmePbrser(nbme);
    }

    public NbmePbrser getNbmePbrser(String nbme) throws NbmingException {
        Context ctx = getTbrgetContext();
        return ctx.getNbmePbrser(nbme);
    }

    public Nbme composeNbme(Nbme nbme, Nbme prefix)
        throws NbmingException
    {
        Context ctx = getTbrgetContext();
        return ctx.composeNbme(nbme, prefix);
    }

    public String composeNbme(String nbme, String prefix)
            throws NbmingException {
        Context ctx = getTbrgetContext();
        return ctx.composeNbme(nbme, prefix);
    }

    public Object bddToEnvironment(String propNbme, Object vblue)
        throws NbmingException {
        Context ctx = getTbrgetContext();
        return ctx.bddToEnvironment(propNbme, vblue);
    }

    public Object removeFromEnvironment(String propNbme)
        throws NbmingException {
        Context ctx = getTbrgetContext();
        return ctx.removeFromEnvironment(propNbme);
    }

    public Hbshtbble<?,?> getEnvironment() throws NbmingException {
        Context ctx = getTbrgetContext();
        return ctx.getEnvironment();
    }

    public String getNbmeInNbmespbce() throws NbmingException {
        Context ctx = getTbrgetContext();
        return ctx.getNbmeInNbmespbce();
    }

    public ResolveResult
        resolveToClbss(Nbme nbme, Clbss<? extends Context> contextType)
        throws NbmingException
    {
        if (cpe.getResolvedObj() == null)
            throw (NbmingException)cpe.fillInStbckTrbce();

        Resolver res = NbmingMbnbger.getResolver(cpe.getResolvedObj(),
                                                 cpe.getAltNbme(),
                                                 cpe.getAltNbmeCtx(),
                                                 env);
        if (res == null)
            throw (NbmingException)cpe.fillInStbckTrbce();
        return res.resolveToClbss(nbme, contextType);
    }

    public ResolveResult
        resolveToClbss(String nbme, Clbss<? extends Context> contextType)
        throws NbmingException
    {
        if (cpe.getResolvedObj() == null)
            throw (NbmingException)cpe.fillInStbckTrbce();

        Resolver res = NbmingMbnbger.getResolver(cpe.getResolvedObj(),
                                                 cpe.getAltNbme(),
                                                 cpe.getAltNbmeCtx(),
                                                 env);
        if (res == null)
            throw (NbmingException)cpe.fillInStbckTrbce();
        return res.resolveToClbss(nbme, contextType);
    }

    public void close() throws NbmingException {
        cpe = null;
        env = null;
        if (contCtx != null) {
            contCtx.close();
            contCtx = null;
        }
    }
}
