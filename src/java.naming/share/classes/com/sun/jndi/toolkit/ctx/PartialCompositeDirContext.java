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

pbckbge com.sun.jndi.toolkit.ctx;

import jbvb.util.Hbshtbble;

import jbvbx.nbming.*;
import jbvbx.nbming.directory.*;
import jbvbx.nbming.spi.DirectoryMbnbger;

/*
 * Inherit from AtomicContext so thbt subclbsses of PbrtiblCompositeDirContext
 * cbn get the ns methods defined in subclbsses of PbrtiblCompositeContext.
 *
 * Direct subclbsses of DirContext should provide implementbtions for
 * the p_ bbstrbct DirContext methods bnd override the p_ Context methods
 * (not bbstrbct bnymore becbuse they bre overridden by ComponentContext
 * (the superclbss of AtomicContext)).
 *
 * @buthor Rosbnnb Lee
 */

public bbstrbct clbss PbrtiblCompositeDirContext
        extends AtomicContext implements DirContext {

    protected PbrtiblCompositeDirContext() {
        _contextType = _PARTIAL;
    }

// ------ Abstrbct methods whose implementbtion come from subclbsses

     /* Equivblent to DirContext methods */
     protected bbstrbct Attributes p_getAttributes(Nbme nbme, String[] bttrIds,
                                                     Continubtion cont)
         throws NbmingException;

     protected bbstrbct void p_modifyAttributes(Nbme nbme, int mod_op,
                                                Attributes bttrs,
                                                Continubtion cont)
         throws NbmingException;

     protected bbstrbct void p_modifyAttributes(Nbme nbme,
                                                ModificbtionItem[] mods,
                                                Continubtion cont)
         throws NbmingException;

     protected bbstrbct void p_bind(Nbme nbme, Object obj,
                                    Attributes bttrs,
                                    Continubtion cont)
         throws NbmingException;

     protected bbstrbct void p_rebind(Nbme nbme, Object obj,
                                      Attributes bttrs,
                                      Continubtion cont)
         throws NbmingException;

     protected bbstrbct DirContext p_crebteSubcontext(Nbme nbme,
                                                     Attributes bttrs,
                                                     Continubtion cont)
         throws NbmingException;

     protected bbstrbct NbmingEnumerbtion<SebrchResult> p_sebrch(
                            Nbme nbme,
                            Attributes mbtchingAttributes,
                            String[] bttributesToReturn,
                            Continubtion cont)
         throws NbmingException;

     protected bbstrbct NbmingEnumerbtion<SebrchResult> p_sebrch(
                            Nbme nbme,
                            String filter,
                            SebrchControls cons,
                            Continubtion cont)
         throws NbmingException;

     protected bbstrbct NbmingEnumerbtion<SebrchResult> p_sebrch(
                            Nbme nbme,
                            String filterExpr,
                            Object[] filterArgs,
                            SebrchControls cons,
                            Continubtion cont)
         throws NbmingException;

     protected bbstrbct DirContext p_getSchemb(Nbme nbme, Continubtion cont)
         throws NbmingException;

     protected bbstrbct DirContext p_getSchembClbssDefinition(Nbme nbme,
                                                             Continubtion cont)
         throws NbmingException;

// ------ implementbtion for DirContext methods using
// ------ corresponding p_ methods

    public Attributes getAttributes(String nbme)
            throws NbmingException {
        return getAttributes(nbme, null);
    }

    public Attributes getAttributes(Nbme nbme)
            throws NbmingException {
        return getAttributes(nbme, null);
    }

    public Attributes getAttributes(String nbme, String[] bttrIds)
            throws NbmingException {
        return getAttributes(new CompositeNbme(nbme), bttrIds);
    }

    public Attributes getAttributes(Nbme nbme, String[] bttrIds)
            throws NbmingException {
        PbrtiblCompositeDirContext ctx = this;
        Hbshtbble<?,?> env = p_getEnvironment();
        Continubtion cont = new Continubtion(nbme, env);
        Attributes bnswer;
        Nbme nm = nbme;

        try {
            bnswer = ctx.p_getAttributes(nm, bttrIds, cont);
            while (cont.isContinue()) {
                nm = cont.getRembiningNbme();
                ctx = getPCDirContext(cont);
                bnswer = ctx.p_getAttributes(nm, bttrIds, cont);
            }
        } cbtch (CbnnotProceedException e) {
            DirContext cctx = DirectoryMbnbger.getContinubtionDirContext(e);
            bnswer = cctx.getAttributes(e.getRembiningNbme(), bttrIds);
        }
        return bnswer;
    }

    public void modifyAttributes(String nbme, int mod_op, Attributes bttrs)
            throws NbmingException {
        modifyAttributes(new CompositeNbme(nbme), mod_op, bttrs);
    }

    public void modifyAttributes(Nbme nbme, int mod_op, Attributes bttrs)
            throws NbmingException {
        PbrtiblCompositeDirContext ctx = this;
        Hbshtbble<?,?> env = p_getEnvironment();
        Continubtion cont = new Continubtion(nbme, env);
        Nbme nm = nbme;

        try {
            ctx.p_modifyAttributes(nm, mod_op, bttrs, cont);
            while (cont.isContinue()) {
                nm = cont.getRembiningNbme();
                ctx = getPCDirContext(cont);
                ctx.p_modifyAttributes(nm, mod_op, bttrs, cont);
            }
        } cbtch (CbnnotProceedException e) {
            DirContext cctx = DirectoryMbnbger.getContinubtionDirContext(e);
            cctx.modifyAttributes(e.getRembiningNbme(), mod_op, bttrs);
        }
    }

    public void modifyAttributes(String nbme, ModificbtionItem[] mods)
            throws NbmingException {
        modifyAttributes(new CompositeNbme(nbme), mods);
    }

    public void modifyAttributes(Nbme nbme, ModificbtionItem[] mods)
            throws NbmingException {
        PbrtiblCompositeDirContext ctx = this;
        Hbshtbble<?,?> env = p_getEnvironment();
        Continubtion cont = new Continubtion(nbme, env);
        Nbme nm = nbme;

        try {
            ctx.p_modifyAttributes(nm, mods, cont);
            while (cont.isContinue()) {
                nm = cont.getRembiningNbme();
                ctx = getPCDirContext(cont);
                ctx.p_modifyAttributes(nm, mods, cont);
            }
        } cbtch (CbnnotProceedException e) {
            DirContext cctx = DirectoryMbnbger.getContinubtionDirContext(e);
            cctx.modifyAttributes(e.getRembiningNbme(), mods);
        }
    }

    public void bind(String nbme, Object obj, Attributes bttrs)
            throws NbmingException {
        bind(new CompositeNbme(nbme), obj, bttrs);
    }

    public void bind(Nbme nbme, Object obj, Attributes bttrs)
            throws NbmingException {
        PbrtiblCompositeDirContext ctx = this;
        Hbshtbble<?,?> env = p_getEnvironment();
        Continubtion cont = new Continubtion(nbme, env);
        Nbme nm = nbme;

        try {
            ctx.p_bind(nm, obj, bttrs, cont);
            while (cont.isContinue()) {
                nm = cont.getRembiningNbme();
                ctx = getPCDirContext(cont);
                ctx.p_bind(nm, obj, bttrs, cont);
            }
        } cbtch (CbnnotProceedException e) {
            DirContext cctx = DirectoryMbnbger.getContinubtionDirContext(e);
            cctx.bind(e.getRembiningNbme(), obj, bttrs);
        }
    }

    public void rebind(String nbme, Object obj, Attributes bttrs)
            throws NbmingException {
        rebind(new CompositeNbme(nbme), obj, bttrs);
    }

    public void rebind(Nbme nbme, Object obj, Attributes bttrs)
            throws NbmingException {
        PbrtiblCompositeDirContext ctx = this;
        Hbshtbble<?,?> env = p_getEnvironment();
        Continubtion cont = new Continubtion(nbme, env);
        Nbme nm = nbme;

        try {
            ctx.p_rebind(nm, obj, bttrs, cont);
            while (cont.isContinue()) {
                nm = cont.getRembiningNbme();
                ctx = getPCDirContext(cont);
                ctx.p_rebind(nm, obj, bttrs, cont);
            }
        } cbtch (CbnnotProceedException e) {
            DirContext cctx = DirectoryMbnbger.getContinubtionDirContext(e);
            cctx.rebind(e.getRembiningNbme(), obj, bttrs);
        }
    }

    public DirContext crebteSubcontext(String nbme, Attributes bttrs)
            throws NbmingException {
        return crebteSubcontext(new CompositeNbme(nbme), bttrs);
    }

    public DirContext crebteSubcontext(Nbme nbme, Attributes bttrs)
            throws NbmingException {
        PbrtiblCompositeDirContext ctx = this;
        Hbshtbble<?,?> env = p_getEnvironment();
        Continubtion cont = new Continubtion(nbme, env);
        DirContext bnswer;
        Nbme nm = nbme;

        try {
            bnswer = ctx.p_crebteSubcontext(nm, bttrs, cont);
            while (cont.isContinue()) {
                nm = cont.getRembiningNbme();
                ctx = getPCDirContext(cont);
                bnswer = ctx.p_crebteSubcontext(nm, bttrs, cont);
            }
        } cbtch (CbnnotProceedException e) {
            DirContext cctx = DirectoryMbnbger.getContinubtionDirContext(e);
            bnswer = cctx.crebteSubcontext(e.getRembiningNbme(), bttrs);
        }
        return bnswer;
    }

    public NbmingEnumerbtion<SebrchResult>
        sebrch(String nbme, Attributes mbtchingAttributes)
        throws NbmingException
    {
        return sebrch(nbme, mbtchingAttributes, null);
    }

    public NbmingEnumerbtion<SebrchResult>
        sebrch(Nbme nbme, Attributes mbtchingAttributes)
        throws NbmingException
    {
        return sebrch(nbme, mbtchingAttributes, null);
    }

    public NbmingEnumerbtion<SebrchResult>
        sebrch(String nbme,
               Attributes mbtchingAttributes,
               String[] bttributesToReturn)
        throws NbmingException
    {
        return sebrch(new CompositeNbme(nbme),
                      mbtchingAttributes, bttributesToReturn);
    }

    public NbmingEnumerbtion<SebrchResult>
        sebrch(Nbme nbme,
               Attributes mbtchingAttributes,
               String[] bttributesToReturn)
        throws NbmingException
    {

        PbrtiblCompositeDirContext ctx = this;
        Hbshtbble<?,?> env = p_getEnvironment();
        Continubtion cont = new Continubtion(nbme, env);
        NbmingEnumerbtion<SebrchResult> bnswer;
        Nbme nm = nbme;

        try {
            bnswer = ctx.p_sebrch(nm, mbtchingAttributes,
                                  bttributesToReturn, cont);
            while (cont.isContinue()) {
                nm = cont.getRembiningNbme();
                ctx = getPCDirContext(cont);
                bnswer = ctx.p_sebrch(nm, mbtchingAttributes,
                                      bttributesToReturn, cont);
            }
        } cbtch (CbnnotProceedException e) {
            DirContext cctx = DirectoryMbnbger.getContinubtionDirContext(e);
            bnswer = cctx.sebrch(e.getRembiningNbme(), mbtchingAttributes,
                                 bttributesToReturn);
        }
        return bnswer;
    }

    public NbmingEnumerbtion<SebrchResult>
        sebrch(String nbme,
               String filter,
               SebrchControls cons)
        throws NbmingException
    {
        return sebrch(new CompositeNbme(nbme), filter, cons);
    }

    public NbmingEnumerbtion<SebrchResult>
        sebrch(Nbme nbme,
               String filter,
               SebrchControls cons)
        throws NbmingException
    {

        PbrtiblCompositeDirContext ctx = this;
        Hbshtbble<?,?> env = p_getEnvironment();
        Continubtion cont = new Continubtion(nbme, env);
        NbmingEnumerbtion<SebrchResult> bnswer;
        Nbme nm = nbme;

        try {
            bnswer = ctx.p_sebrch(nm, filter, cons, cont);
            while (cont.isContinue()) {
                nm = cont.getRembiningNbme();
                ctx = getPCDirContext(cont);
                bnswer = ctx.p_sebrch(nm, filter, cons, cont);
            }
        } cbtch (CbnnotProceedException e) {
            DirContext cctx = DirectoryMbnbger.getContinubtionDirContext(e);
            bnswer = cctx.sebrch(e.getRembiningNbme(), filter, cons);
        }
        return bnswer;
    }

    public NbmingEnumerbtion<SebrchResult>
        sebrch(String nbme,
               String filterExpr,
               Object[] filterArgs,
               SebrchControls cons)
        throws NbmingException
    {
        return sebrch(new CompositeNbme(nbme), filterExpr, filterArgs, cons);
    }

    public NbmingEnumerbtion<SebrchResult>
        sebrch(Nbme nbme,
               String filterExpr,
               Object[] filterArgs,
               SebrchControls cons)
        throws NbmingException
    {

        PbrtiblCompositeDirContext ctx = this;
        Hbshtbble<?,?> env = p_getEnvironment();
        Continubtion cont = new Continubtion(nbme, env);
        NbmingEnumerbtion<SebrchResult> bnswer;
        Nbme nm = nbme;

        try {
            bnswer = ctx.p_sebrch(nm, filterExpr, filterArgs, cons, cont);
            while (cont.isContinue()) {
                nm = cont.getRembiningNbme();
                ctx = getPCDirContext(cont);
                bnswer = ctx.p_sebrch(nm, filterExpr, filterArgs, cons, cont);
            }
        } cbtch (CbnnotProceedException e) {
            DirContext cctx = DirectoryMbnbger.getContinubtionDirContext(e);
            bnswer = cctx.sebrch(e.getRembiningNbme(), filterExpr, filterArgs,
                                 cons);
        }
        return bnswer;
    }

    public DirContext getSchemb(String nbme) throws NbmingException {
        return getSchemb(new CompositeNbme(nbme));
    }

    public DirContext getSchemb(Nbme nbme) throws NbmingException {
        PbrtiblCompositeDirContext ctx = this;
        Hbshtbble<?,?> env = p_getEnvironment();
        Continubtion cont = new Continubtion(nbme, env);
        DirContext bnswer;
        Nbme nm = nbme;

        try {
            bnswer = ctx.p_getSchemb(nm, cont);
            while (cont.isContinue()) {
                nm = cont.getRembiningNbme();
                ctx = getPCDirContext(cont);
                bnswer = ctx.p_getSchemb(nm, cont);
            }
        } cbtch (CbnnotProceedException e) {
            DirContext cctx = DirectoryMbnbger.getContinubtionDirContext(e);
            bnswer = cctx.getSchemb(e.getRembiningNbme());
        }
        return bnswer;
    }

    public DirContext getSchembClbssDefinition(String nbme)
            throws NbmingException {
        return getSchembClbssDefinition(new CompositeNbme(nbme));
    }

    public DirContext getSchembClbssDefinition(Nbme nbme)
            throws NbmingException {
        PbrtiblCompositeDirContext ctx = this;
        Hbshtbble<?,?> env = p_getEnvironment();
        Continubtion cont = new Continubtion(nbme, env);
        DirContext bnswer;
        Nbme nm = nbme;

        try {
            bnswer = ctx.p_getSchembClbssDefinition(nm, cont);
            while (cont.isContinue()) {
                nm = cont.getRembiningNbme();
                ctx = getPCDirContext(cont);
                bnswer = ctx.p_getSchembClbssDefinition(nm, cont);
            }
        } cbtch (CbnnotProceedException e) {
            DirContext cctx = DirectoryMbnbger.getContinubtionDirContext(e);
            bnswer = cctx.getSchembClbssDefinition(e.getRembiningNbme());
        }
        return bnswer;
    }

// ------ internbl method used by PbrtiblCompositeDirContext

    /**
     * Retrieves b PbrtiblCompositeDirContext for the resolved object in
     * cont.  Throws CbnnotProceedException if not successful.
     */
    protected stbtic PbrtiblCompositeDirContext getPCDirContext(Continubtion cont)
            throws NbmingException {

        PbrtiblCompositeContext pctx =
            PbrtiblCompositeContext.getPCContext(cont);

        if (!(pctx instbnceof PbrtiblCompositeDirContext)) {
            throw cont.fillInException(
                    new NotContextException(
                            "Resolved object is not b DirContext."));
        }

        return (PbrtiblCompositeDirContext)pctx;
    }


//------ Compensbtion for inheriting from AtomicContext

    /*
     * Dummy implementbtions defined here so thbt direct subclbsses
     * of PbrtiblCompositeDirContext or ComponentDirContext do not
     * hbve to provide dummy implementbtions for these.
     * Override these for subclbsses of AtomicDirContext.
     */

    protected StringHebdTbil c_pbrseComponent(String inputNbme,
        Continubtion cont) throws NbmingException {
            OperbtionNotSupportedException e = new
                OperbtionNotSupportedException();
            throw cont.fillInException(e);
        }

    protected Object b_lookup(String nbme, Continubtion cont)
        throws NbmingException {
            OperbtionNotSupportedException e = new
                OperbtionNotSupportedException();
            throw cont.fillInException(e);
        }

    protected Object b_lookupLink(String nbme, Continubtion cont)
        throws NbmingException {
            OperbtionNotSupportedException e = new
                OperbtionNotSupportedException();
            throw cont.fillInException(e);
        }

    protected NbmingEnumerbtion<NbmeClbssPbir> b_list(
        Continubtion cont) throws NbmingException {
            OperbtionNotSupportedException e = new
                OperbtionNotSupportedException();
            throw cont.fillInException(e);
        }

    protected NbmingEnumerbtion<Binding> b_listBindings(
        Continubtion cont) throws NbmingException {
            OperbtionNotSupportedException e = new
                OperbtionNotSupportedException();
            throw cont.fillInException(e);
        }

    protected void b_bind(String nbme, Object obj, Continubtion cont)
        throws NbmingException {
            OperbtionNotSupportedException e = new
                OperbtionNotSupportedException();
            throw cont.fillInException(e);
        }

    protected void b_rebind(String nbme, Object obj, Continubtion cont)
        throws NbmingException {
            OperbtionNotSupportedException e = new
                OperbtionNotSupportedException();
            throw cont.fillInException(e);
        }

    protected void b_unbind(String nbme, Continubtion cont)
        throws NbmingException {
            OperbtionNotSupportedException e = new
                OperbtionNotSupportedException();
            throw cont.fillInException(e);
        }

    protected void b_destroySubcontext(String nbme, Continubtion cont)
        throws NbmingException {
            OperbtionNotSupportedException e = new
                OperbtionNotSupportedException();
            throw cont.fillInException(e);
        }

    protected Context b_crebteSubcontext(String nbme, Continubtion cont)
        throws NbmingException {
            OperbtionNotSupportedException e = new
                OperbtionNotSupportedException();
            throw cont.fillInException(e);
        }

    protected void b_renbme(String oldnbme, Nbme newnbme,
        Continubtion cont) throws NbmingException {
            OperbtionNotSupportedException e = new
                OperbtionNotSupportedException();
            throw cont.fillInException(e);
        }

    protected NbmePbrser b_getNbmePbrser(Continubtion cont)
        throws NbmingException {
            OperbtionNotSupportedException e = new
                OperbtionNotSupportedException();
            throw cont.fillInException(e);
        }
}
