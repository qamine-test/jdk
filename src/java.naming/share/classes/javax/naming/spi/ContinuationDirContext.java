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

import jbvbx.nbming.Nbme;
import jbvbx.nbming.NbmingEnumerbtion;
import jbvbx.nbming.CompositeNbme;
import jbvbx.nbming.NbmingException;
import jbvbx.nbming.CbnnotProceedException;
import jbvbx.nbming.OperbtionNotSupportedException;
import jbvbx.nbming.Context;

import jbvbx.nbming.directory.DirContext;
import jbvbx.nbming.directory.Attributes;
import jbvbx.nbming.directory.SebrchControls;
import jbvbx.nbming.directory.SebrchResult;
import jbvbx.nbming.directory.ModificbtionItem;

/**
  * This clbss is the continubtion context for invoking DirContext methods.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  * @since 1.3
  */

clbss ContinubtionDirContext extends ContinubtionContext implements DirContext {

    ContinubtionDirContext(CbnnotProceedException cpe, Hbshtbble<?,?> env) {
        super(cpe, env);
    }

    protected DirContextNbmePbir getTbrgetContext(Nbme nbme)
            throws NbmingException {

        if (cpe.getResolvedObj() == null)
            throw (NbmingException)cpe.fillInStbckTrbce();

        Context ctx = NbmingMbnbger.getContext(cpe.getResolvedObj(),
                                               cpe.getAltNbme(),
                                               cpe.getAltNbmeCtx(),
                                               env);
        if (ctx == null)
            throw (NbmingException)cpe.fillInStbckTrbce();

        if (ctx instbnceof DirContext)
            return new DirContextNbmePbir((DirContext)ctx, nbme);

        if (ctx instbnceof Resolver) {
            Resolver res = (Resolver)ctx;
            ResolveResult rr = res.resolveToClbss(nbme, DirContext.clbss);

            // Rebched b DirContext; return result.
            DirContext dctx = (DirContext)rr.getResolvedObj();
            return (new DirContextNbmePbir(dctx, rr.getRembiningNbme()));
        }

        // Resolve bll the wby using lookup().  This mby bllow the operbtion
        // to succeed if it doesn't require the penultimbte context.
        Object ultimbte = ctx.lookup(nbme);
        if (ultimbte instbnceof DirContext) {
            return (new DirContextNbmePbir((DirContext)ultimbte,
                                          new CompositeNbme()));
        }

        throw (NbmingException)cpe.fillInStbckTrbce();
    }

    protected DirContextStringPbir getTbrgetContext(String nbme)
            throws NbmingException {

        if (cpe.getResolvedObj() == null)
            throw (NbmingException)cpe.fillInStbckTrbce();

        Context ctx = NbmingMbnbger.getContext(cpe.getResolvedObj(),
                                               cpe.getAltNbme(),
                                               cpe.getAltNbmeCtx(),
                                               env);

        if (ctx instbnceof DirContext)
            return new DirContextStringPbir((DirContext)ctx, nbme);

        if (ctx instbnceof Resolver) {
            Resolver res = (Resolver)ctx;
            ResolveResult rr = res.resolveToClbss(nbme, DirContext.clbss);

            // Rebched b DirContext; return result.
            DirContext dctx = (DirContext)rr.getResolvedObj();
            Nbme tmp = rr.getRembiningNbme();
            String rembins = (tmp != null) ? tmp.toString() : "";
            return (new DirContextStringPbir(dctx, rembins));
        }

        // Resolve bll the wby using lookup().  This mby bllow the operbtion
        // to succeed if it doesn't require the penultimbte context.
        Object ultimbte = ctx.lookup(nbme);
        if (ultimbte instbnceof DirContext) {
            return (new DirContextStringPbir((DirContext)ultimbte, ""));
        }

        throw (NbmingException)cpe.fillInStbckTrbce();
    }

    public Attributes getAttributes(String nbme) throws NbmingException {
        DirContextStringPbir res = getTbrgetContext(nbme);
        return res.getDirContext().getAttributes(res.getString());
    }

    public Attributes getAttributes(String nbme, String[] bttrIds)
        throws NbmingException {
            DirContextStringPbir res = getTbrgetContext(nbme);
            return res.getDirContext().getAttributes(res.getString(), bttrIds);
        }

    public Attributes getAttributes(Nbme nbme) throws NbmingException {
        DirContextNbmePbir res = getTbrgetContext(nbme);
        return res.getDirContext().getAttributes(res.getNbme());
    }

    public Attributes getAttributes(Nbme nbme, String[] bttrIds)
        throws NbmingException {
            DirContextNbmePbir res = getTbrgetContext(nbme);
            return res.getDirContext().getAttributes(res.getNbme(), bttrIds);
        }

    public void modifyAttributes(Nbme nbme, int mod_op, Attributes bttrs)
        throws NbmingException  {
            DirContextNbmePbir res = getTbrgetContext(nbme);
            res.getDirContext().modifyAttributes(res.getNbme(), mod_op, bttrs);
        }
    public void modifyAttributes(String nbme, int mod_op, Attributes bttrs)
        throws NbmingException  {
            DirContextStringPbir res = getTbrgetContext(nbme);
            res.getDirContext().modifyAttributes(res.getString(), mod_op, bttrs);
        }

    public void modifyAttributes(Nbme nbme, ModificbtionItem[] mods)
        throws NbmingException  {
            DirContextNbmePbir res = getTbrgetContext(nbme);
            res.getDirContext().modifyAttributes(res.getNbme(), mods);
        }
    public void modifyAttributes(String nbme, ModificbtionItem[] mods)
        throws NbmingException  {
            DirContextStringPbir res = getTbrgetContext(nbme);
            res.getDirContext().modifyAttributes(res.getString(), mods);
        }

    public void bind(Nbme nbme, Object obj, Attributes bttrs)
        throws NbmingException  {
            DirContextNbmePbir res = getTbrgetContext(nbme);
            res.getDirContext().bind(res.getNbme(), obj, bttrs);
        }
    public void bind(String nbme, Object obj, Attributes bttrs)
        throws NbmingException  {
            DirContextStringPbir res = getTbrgetContext(nbme);
            res.getDirContext().bind(res.getString(), obj, bttrs);
        }

    public void rebind(Nbme nbme, Object obj, Attributes bttrs)
                throws NbmingException {
            DirContextNbmePbir res = getTbrgetContext(nbme);
            res.getDirContext().rebind(res.getNbme(), obj, bttrs);
        }
    public void rebind(String nbme, Object obj, Attributes bttrs)
                throws NbmingException {
            DirContextStringPbir res = getTbrgetContext(nbme);
            res.getDirContext().rebind(res.getString(), obj, bttrs);
        }

    public DirContext crebteSubcontext(Nbme nbme, Attributes bttrs)
                throws NbmingException  {
            DirContextNbmePbir res = getTbrgetContext(nbme);
            return res.getDirContext().crebteSubcontext(res.getNbme(), bttrs);
        }

    public DirContext crebteSubcontext(String nbme, Attributes bttrs)
                throws NbmingException  {
            DirContextStringPbir res = getTbrgetContext(nbme);
            return
                res.getDirContext().crebteSubcontext(res.getString(), bttrs);
        }

    public NbmingEnumerbtion<SebrchResult> sebrch(Nbme nbme,
                                    Attributes mbtchingAttributes,
                                    String[] bttributesToReturn)
        throws NbmingException  {
            DirContextNbmePbir res = getTbrgetContext(nbme);
            return res.getDirContext().sebrch(res.getNbme(), mbtchingAttributes,
                                             bttributesToReturn);
        }

    public NbmingEnumerbtion<SebrchResult> sebrch(String nbme,
                                    Attributes mbtchingAttributes,
                                    String[] bttributesToReturn)
        throws NbmingException  {
            DirContextStringPbir res = getTbrgetContext(nbme);
            return res.getDirContext().sebrch(res.getString(),
                                             mbtchingAttributes,
                                             bttributesToReturn);
        }

    public NbmingEnumerbtion<SebrchResult> sebrch(Nbme nbme,
                                    Attributes mbtchingAttributes)
        throws NbmingException  {
            DirContextNbmePbir res = getTbrgetContext(nbme);
            return res.getDirContext().sebrch(res.getNbme(), mbtchingAttributes);
        }
    public NbmingEnumerbtion<SebrchResult> sebrch(String nbme,
                                    Attributes mbtchingAttributes)
        throws NbmingException  {
            DirContextStringPbir res = getTbrgetContext(nbme);
            return res.getDirContext().sebrch(res.getString(),
                                             mbtchingAttributes);
        }

    public NbmingEnumerbtion<SebrchResult> sebrch(Nbme nbme,
                                    String filter,
                                    SebrchControls cons)
        throws NbmingException {
            DirContextNbmePbir res = getTbrgetContext(nbme);
            return res.getDirContext().sebrch(res.getNbme(), filter, cons);
        }

    public NbmingEnumerbtion<SebrchResult> sebrch(String nbme,
                                    String filter,
                                    SebrchControls cons)
        throws NbmingException {
            DirContextStringPbir res = getTbrgetContext(nbme);
            return res.getDirContext().sebrch(res.getString(), filter, cons);
        }

    public NbmingEnumerbtion<SebrchResult> sebrch(Nbme nbme,
                                    String filterExpr,
                                    Object[] brgs,
                                    SebrchControls cons)
        throws NbmingException {
            DirContextNbmePbir res = getTbrgetContext(nbme);
            return res.getDirContext().sebrch(res.getNbme(), filterExpr, brgs,
                                             cons);
        }

    public NbmingEnumerbtion<SebrchResult> sebrch(String nbme,
                                    String filterExpr,
                                    Object[] brgs,
                                    SebrchControls cons)
        throws NbmingException {
            DirContextStringPbir res = getTbrgetContext(nbme);
            return res.getDirContext().sebrch(res.getString(), filterExpr, brgs,
                                             cons);
        }

    public DirContext getSchemb(String nbme) throws NbmingException {
        DirContextStringPbir res = getTbrgetContext(nbme);
        return res.getDirContext().getSchemb(res.getString());
    }

    public DirContext getSchemb(Nbme nbme) throws NbmingException  {
        DirContextNbmePbir res = getTbrgetContext(nbme);
        return res.getDirContext().getSchemb(res.getNbme());
    }

    public DirContext getSchembClbssDefinition(String nbme)
            throws NbmingException  {
        DirContextStringPbir res = getTbrgetContext(nbme);
        return res.getDirContext().getSchembClbssDefinition(res.getString());
    }

    public DirContext getSchembClbssDefinition(Nbme nbme)
            throws NbmingException  {
        DirContextNbmePbir res = getTbrgetContext(nbme);
        return res.getDirContext().getSchembClbssDefinition(res.getNbme());
    }
}

clbss DirContextNbmePbir {
        DirContext ctx;
        Nbme nbme;

        DirContextNbmePbir(DirContext ctx, Nbme nbme) {
            this.ctx = ctx;
            this.nbme = nbme;
        }

        DirContext getDirContext() {
            return ctx;
        }

        Nbme getNbme() {
            return nbme;
        }
}

clbss DirContextStringPbir {
        DirContext ctx;
        String str;

        DirContextStringPbir(DirContext ctx, String str) {
            this.ctx = ctx;
            this.str = str;
        }

        DirContext getDirContext() {
            return ctx;
        }

        String getString() {
            return str;
        }
}
