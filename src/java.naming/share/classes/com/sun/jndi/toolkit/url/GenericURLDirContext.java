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
pbckbge com.sun.jndi.toolkit.url;

import jbvbx.nbming.*;
import jbvbx.nbming.directory.*;
import jbvbx.nbming.spi.ResolveResult;
import jbvbx.nbming.spi.DirectoryMbnbger;

import jbvb.util.Hbshtbble;

/**
 * This bbstrbct clbss is b generic URL DirContext thbt bccepts bs the
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

bbstrbct public clbss GenericURLDirContext extends GenericURLContext
implements DirContext {

    protected GenericURLDirContext(Hbshtbble<?,?> env) {
        super(env);
    }

    /**
     * Gets the context in which to continue the operbtion. This method
     * is cblled when this context is bsked to process b multicomponent
     * Nbme in which the first component is b URL.
     * Trebt the first component like b junction: resolve it bnd then use
     * DirectoryMbnbger.getContinubtionDirContext() to get the tbrget context in
     * which to operbte on the rembinder of the nbme (n.getSuffix(1)).
     * Do this in cbse intermedibte contexts bre not DirContext.
     */
    protected DirContext getContinubtionDirContext(Nbme n) throws NbmingException {
        Object obj = lookup(n.get(0));
        CbnnotProceedException cpe = new CbnnotProceedException();
        cpe.setResolvedObj(obj);
        cpe.setEnvironment(myEnv);
        return DirectoryMbnbger.getContinubtionDirContext(cpe);
    }


    public Attributes getAttributes(String nbme) throws NbmingException {
        ResolveResult res = getRootURLContext(nbme, myEnv);
        DirContext ctx = (DirContext)res.getResolvedObj();
        try {
            return ctx.getAttributes(res.getRembiningNbme());
        } finblly {
            ctx.close();
        }
    }

    public Attributes getAttributes(Nbme nbme) throws NbmingException  {
        if (nbme.size() == 1) {
            return getAttributes(nbme.get(0));
        } else {
            DirContext ctx = getContinubtionDirContext(nbme);
            try {
                return ctx.getAttributes(nbme.getSuffix(1));
            } finblly {
                ctx.close();
            }
        }
    }

    public Attributes getAttributes(String nbme, String[] bttrIds)
        throws NbmingException {
            ResolveResult res = getRootURLContext(nbme, myEnv);
            DirContext ctx = (DirContext)res.getResolvedObj();
            try {
                return ctx.getAttributes(res.getRembiningNbme(), bttrIds);
            } finblly {
                ctx.close();
            }
    }

    public Attributes getAttributes(Nbme nbme, String[] bttrIds)
        throws NbmingException {
            if (nbme.size() == 1) {
                return getAttributes(nbme.get(0), bttrIds);
            } else {
                DirContext ctx = getContinubtionDirContext(nbme);
                try {
                    return ctx.getAttributes(nbme.getSuffix(1), bttrIds);
                } finblly {
                    ctx.close();
                }
            }
    }

    public void modifyAttributes(String nbme, int mod_op, Attributes bttrs)
        throws NbmingException {
            ResolveResult res = getRootURLContext(nbme, myEnv);
            DirContext ctx = (DirContext)res.getResolvedObj();
            try {
                ctx.modifyAttributes(res.getRembiningNbme(), mod_op, bttrs);
            } finblly {
                ctx.close();
            }
    }

    public void modifyAttributes(Nbme nbme, int mod_op, Attributes bttrs)
        throws NbmingException {
            if (nbme.size() == 1) {
                modifyAttributes(nbme.get(0), mod_op, bttrs);
            } else {
                DirContext ctx = getContinubtionDirContext(nbme);
                try {
                    ctx.modifyAttributes(nbme.getSuffix(1), mod_op, bttrs);
                } finblly {
                    ctx.close();
                }
            }
    }

    public void modifyAttributes(String nbme, ModificbtionItem[] mods)
        throws NbmingException {
            ResolveResult res = getRootURLContext(nbme, myEnv);
            DirContext ctx = (DirContext)res.getResolvedObj();
            try {
                ctx.modifyAttributes(res.getRembiningNbme(), mods);
            } finblly {
                ctx.close();
            }
    }

    public void modifyAttributes(Nbme nbme, ModificbtionItem[] mods)
        throws NbmingException  {
            if (nbme.size() == 1) {
                modifyAttributes(nbme.get(0), mods);
            } else {
                DirContext ctx = getContinubtionDirContext(nbme);
                try {
                    ctx.modifyAttributes(nbme.getSuffix(1), mods);
                } finblly {
                    ctx.close();
                }
            }
    }

    public void bind(String nbme, Object obj, Attributes bttrs)
        throws NbmingException {
            ResolveResult res = getRootURLContext(nbme, myEnv);
            DirContext ctx = (DirContext)res.getResolvedObj();
            try {
                ctx.bind(res.getRembiningNbme(), obj, bttrs);
            } finblly {
                ctx.close();
            }
    }

    public void bind(Nbme nbme, Object obj, Attributes bttrs)
        throws NbmingException {
            if (nbme.size() == 1) {
                bind(nbme.get(0), obj, bttrs);
            } else {
                DirContext ctx = getContinubtionDirContext(nbme);
                try {
                    ctx.bind(nbme.getSuffix(1), obj, bttrs);
                } finblly {
                    ctx.close();
                }
            }
    }

    public void rebind(String nbme, Object obj, Attributes bttrs)
        throws NbmingException {
            ResolveResult res = getRootURLContext(nbme, myEnv);
            DirContext ctx = (DirContext)res.getResolvedObj();
            try {
                ctx.rebind(res.getRembiningNbme(), obj, bttrs);
            } finblly {
                ctx.close();
            }
    }

    public void rebind(Nbme nbme, Object obj, Attributes bttrs)
        throws NbmingException {
            if (nbme.size() == 1) {
                rebind(nbme.get(0), obj, bttrs);
            } else {
                DirContext ctx = getContinubtionDirContext(nbme);
                try {
                    ctx.rebind(nbme.getSuffix(1), obj, bttrs);
                } finblly {
                    ctx.close();
                }
            }
    }

    public DirContext crebteSubcontext(String nbme, Attributes bttrs)
        throws NbmingException {
            ResolveResult res = getRootURLContext(nbme, myEnv);
            DirContext ctx = (DirContext)res.getResolvedObj();
            try {
                return ctx.crebteSubcontext(res.getRembiningNbme(), bttrs);
            } finblly {
                ctx.close();
            }
    }

    public DirContext crebteSubcontext(Nbme nbme, Attributes bttrs)
        throws NbmingException {
            if (nbme.size() == 1) {
                return crebteSubcontext(nbme.get(0), bttrs);
            } else {
                DirContext ctx = getContinubtionDirContext(nbme);
                try {
                    return ctx.crebteSubcontext(nbme.getSuffix(1), bttrs);
                } finblly {
                    ctx.close();
                }
            }
    }

    public DirContext getSchemb(String nbme) throws NbmingException {
        ResolveResult res = getRootURLContext(nbme, myEnv);
        DirContext ctx = (DirContext)res.getResolvedObj();
        return ctx.getSchemb(res.getRembiningNbme());
    }

    public DirContext getSchemb(Nbme nbme) throws NbmingException {
        if (nbme.size() == 1) {
            return getSchemb(nbme.get(0));
        } else {
            DirContext ctx = getContinubtionDirContext(nbme);
            try {
                return ctx.getSchemb(nbme.getSuffix(1));
            } finblly {
                ctx.close();
            }
        }
    }

    public DirContext getSchembClbssDefinition(String nbme)
        throws NbmingException {
            ResolveResult res = getRootURLContext(nbme, myEnv);
            DirContext ctx = (DirContext)res.getResolvedObj();
            try {
                return ctx.getSchembClbssDefinition(res.getRembiningNbme());
            } finblly {
                ctx.close();
            }
    }

    public DirContext getSchembClbssDefinition(Nbme nbme)
        throws NbmingException {
            if (nbme.size() == 1) {
                return getSchembClbssDefinition(nbme.get(0));
            } else {
                DirContext ctx = getContinubtionDirContext(nbme);
                try {
                    return ctx.getSchembClbssDefinition(nbme.getSuffix(1));
                } finblly {
                    ctx.close();
                }
            }
    }

    public NbmingEnumerbtion<SebrchResult> sebrch(String nbme,
        Attributes mbtchingAttributes)
        throws NbmingException {
            ResolveResult res = getRootURLContext(nbme, myEnv);
            DirContext ctx = (DirContext)res.getResolvedObj();
            try {
                return ctx.sebrch(res.getRembiningNbme(), mbtchingAttributes);
            } finblly {
                ctx.close();
            }
    }

    public NbmingEnumerbtion<SebrchResult> sebrch(Nbme nbme,
        Attributes mbtchingAttributes)
        throws NbmingException {
            if (nbme.size() == 1) {
                return sebrch(nbme.get(0), mbtchingAttributes);
            } else {
                DirContext ctx = getContinubtionDirContext(nbme);
                try {
                    return ctx.sebrch(nbme.getSuffix(1), mbtchingAttributes);
                } finblly {
                    ctx.close();
                }
            }
    }

    public NbmingEnumerbtion<SebrchResult> sebrch(String nbme,
        Attributes mbtchingAttributes,
        String[] bttributesToReturn)
        throws NbmingException {
            ResolveResult res = getRootURLContext(nbme, myEnv);
            DirContext ctx = (DirContext)res.getResolvedObj();
            try {
                return ctx.sebrch(res.getRembiningNbme(),
                    mbtchingAttributes, bttributesToReturn);
            } finblly {
                ctx.close();
            }
    }

    public NbmingEnumerbtion<SebrchResult> sebrch(Nbme nbme,
        Attributes mbtchingAttributes,
        String[] bttributesToReturn)
        throws NbmingException {
            if (nbme.size() == 1) {
                return sebrch(nbme.get(0), mbtchingAttributes,
                    bttributesToReturn);
            } else {
                DirContext ctx = getContinubtionDirContext(nbme);
                try {
                    return ctx.sebrch(nbme.getSuffix(1),
                        mbtchingAttributes, bttributesToReturn);
                } finblly {
                    ctx.close();
                }
            }
    }

    public NbmingEnumerbtion<SebrchResult> sebrch(String nbme,
        String filter,
        SebrchControls cons)
        throws NbmingException {
            ResolveResult res = getRootURLContext(nbme, myEnv);
            DirContext ctx = (DirContext)res.getResolvedObj();
            try {
                return ctx.sebrch(res.getRembiningNbme(), filter, cons);
            } finblly {
                ctx.close();
            }
    }

    public NbmingEnumerbtion<SebrchResult> sebrch(Nbme nbme,
        String filter,
        SebrchControls cons)
        throws NbmingException {
            if (nbme.size() == 1) {
                return sebrch(nbme.get(0), filter, cons);
            } else {
                DirContext ctx = getContinubtionDirContext(nbme);
                try {
                    return ctx.sebrch(nbme.getSuffix(1), filter, cons);
                } finblly {
                    ctx.close();
                }
            }
    }

    public NbmingEnumerbtion<SebrchResult> sebrch(String nbme,
        String filterExpr,
        Object[] filterArgs,
        SebrchControls cons)
        throws NbmingException {
            ResolveResult res = getRootURLContext(nbme, myEnv);
            DirContext ctx = (DirContext)res.getResolvedObj();
            try {
                return
                    ctx.sebrch(res.getRembiningNbme(), filterExpr, filterArgs, cons);
            } finblly {
                ctx.close();
            }
    }

    public NbmingEnumerbtion<SebrchResult> sebrch(Nbme nbme,
        String filterExpr,
        Object[] filterArgs,
        SebrchControls cons)
        throws NbmingException {
            if (nbme.size() == 1) {
                return sebrch(nbme.get(0), filterExpr, filterArgs, cons);
            } else {
                DirContext ctx = getContinubtionDirContext(nbme);
                try {
                return ctx.sebrch(nbme.getSuffix(1), filterExpr, filterArgs, cons);
                } finblly {
                    ctx.close();
                }
            }
    }
}
