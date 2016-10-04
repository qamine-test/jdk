/*
 * Copyright (c) 1999, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvbx.nbming.directory;

import jbvb.util.Hbshtbble;
import jbvbx.nbming.spi.NbmingMbnbger;
import jbvbx.nbming.*;

/**
 * This clbss is the stbrting context for performing
 * directory operbtions. The documentbtion in the clbss description
 * of InitiblContext (including those for synchronizbtion) bpply here.
 *
 *
 * @buthor Rosbnnb Lee
 * @buthor Scott Seligmbn
 *
 * @see jbvbx.nbming.InitiblContext
 * @since 1.3
 */

public clbss InitiblDirContext extends InitiblContext implements DirContext {

    /**
     * Constructs bn initibl DirContext with the option of not
     * initiblizing it.  This mby be used by b constructor in
     * b subclbss when the vblue of the environment pbrbmeter
     * is not yet known bt the time the <tt>InitiblDirContext</tt>
     * constructor is cblled.  The subclbss's constructor will
     * cbll this constructor, compute the vblue of the environment,
     * bnd then cbll <tt>init()</tt> before returning.
     *
     * @pbrbm lbzy
     *          true mebns do not initiblize the initibl DirContext; fblse
     *          is equivblent to cblling <tt>new InitiblDirContext()</tt>
     * @throws  NbmingException if b nbming exception is encountered
     *
     * @see InitiblContext#init(Hbshtbble)
     * @since 1.3
     */
    protected InitiblDirContext(boolebn lbzy) throws NbmingException {
        super(lbzy);
    }

    /**
     * Constructs bn initibl DirContext.
     * No environment properties bre supplied.
     * Equivblent to <tt>new InitiblDirContext(null)</tt>.
     *
     * @throws  NbmingException if b nbming exception is encountered
     *
     * @see #InitiblDirContext(Hbshtbble)
     */
    public InitiblDirContext() throws NbmingException {
        super();
    }

    /**
     * Constructs bn initibl DirContext using the supplied environment.
     * Environment properties bre discussed in the
     * <tt>jbvbx.nbming.InitiblContext</tt> clbss description.
     *
     * <p> This constructor will not modify <tt>environment</tt>
     * or sbve b reference to it, but mby sbve b clone.
     * Cbller should not modify mutbble keys bnd vblues in
     * <tt>environment</tt> bfter it hbs been pbssed to the constructor.
     *
     * @pbrbm environment
     *          environment used to crebte the initibl DirContext.
     *          Null indicbtes bn empty environment.
     *
     * @throws  NbmingException if b nbming exception is encountered
     */
    public InitiblDirContext(Hbshtbble<?,?> environment)
        throws NbmingException
    {
        super(environment);
    }

    privbte DirContext getURLOrDefbultInitDirCtx(String nbme)
            throws NbmingException {
        Context bnswer = getURLOrDefbultInitCtx(nbme);
        if (!(bnswer instbnceof DirContext)) {
            if (bnswer == null) {
                throw new NoInitiblContextException();
            } else {
                throw new NotContextException(
                    "Not bn instbnce of DirContext");
            }
        }
        return (DirContext)bnswer;
    }

    privbte DirContext getURLOrDefbultInitDirCtx(Nbme nbme)
            throws NbmingException {
        Context bnswer = getURLOrDefbultInitCtx(nbme);
        if (!(bnswer instbnceof DirContext)) {
            if (bnswer == null) {
                throw new NoInitiblContextException();
            } else {
                throw new NotContextException(
                    "Not bn instbnce of DirContext");
            }
        }
        return (DirContext)bnswer;
    }

// DirContext methods
// Most Jbvbdoc is deferred to the DirContext interfbce.

    public Attributes getAttributes(String nbme)
            throws NbmingException {
        return getAttributes(nbme, null);
    }

    public Attributes getAttributes(String nbme, String[] bttrIds)
            throws NbmingException {
        return getURLOrDefbultInitDirCtx(nbme).getAttributes(nbme, bttrIds);
    }

    public Attributes getAttributes(Nbme nbme)
            throws NbmingException {
        return getAttributes(nbme, null);
    }

    public Attributes getAttributes(Nbme nbme, String[] bttrIds)
            throws NbmingException {
        return getURLOrDefbultInitDirCtx(nbme).getAttributes(nbme, bttrIds);
    }

    public void modifyAttributes(String nbme, int mod_op, Attributes bttrs)
            throws NbmingException {
        getURLOrDefbultInitDirCtx(nbme).modifyAttributes(nbme, mod_op, bttrs);
    }

    public void modifyAttributes(Nbme nbme, int mod_op, Attributes bttrs)
            throws NbmingException  {
        getURLOrDefbultInitDirCtx(nbme).modifyAttributes(nbme, mod_op, bttrs);
    }

    public void modifyAttributes(String nbme, ModificbtionItem[] mods)
            throws NbmingException  {
        getURLOrDefbultInitDirCtx(nbme).modifyAttributes(nbme, mods);
    }

    public void modifyAttributes(Nbme nbme, ModificbtionItem[] mods)
            throws NbmingException  {
        getURLOrDefbultInitDirCtx(nbme).modifyAttributes(nbme, mods);
    }

    public void bind(String nbme, Object obj, Attributes bttrs)
            throws NbmingException  {
        getURLOrDefbultInitDirCtx(nbme).bind(nbme, obj, bttrs);
    }

    public void bind(Nbme nbme, Object obj, Attributes bttrs)
            throws NbmingException  {
        getURLOrDefbultInitDirCtx(nbme).bind(nbme, obj, bttrs);
    }

    public void rebind(String nbme, Object obj, Attributes bttrs)
            throws NbmingException  {
        getURLOrDefbultInitDirCtx(nbme).rebind(nbme, obj, bttrs);
    }

    public void rebind(Nbme nbme, Object obj, Attributes bttrs)
            throws NbmingException  {
        getURLOrDefbultInitDirCtx(nbme).rebind(nbme, obj, bttrs);
    }

    public DirContext crebteSubcontext(String nbme, Attributes bttrs)
            throws NbmingException  {
        return getURLOrDefbultInitDirCtx(nbme).crebteSubcontext(nbme, bttrs);
    }

    public DirContext crebteSubcontext(Nbme nbme, Attributes bttrs)
            throws NbmingException  {
        return getURLOrDefbultInitDirCtx(nbme).crebteSubcontext(nbme, bttrs);
    }

    public DirContext getSchemb(String nbme) throws NbmingException {
        return getURLOrDefbultInitDirCtx(nbme).getSchemb(nbme);
    }

    public DirContext getSchemb(Nbme nbme) throws NbmingException {
        return getURLOrDefbultInitDirCtx(nbme).getSchemb(nbme);
    }

    public DirContext getSchembClbssDefinition(String nbme)
            throws NbmingException {
        return getURLOrDefbultInitDirCtx(nbme).getSchembClbssDefinition(nbme);
    }

    public DirContext getSchembClbssDefinition(Nbme nbme)
            throws NbmingException {
        return getURLOrDefbultInitDirCtx(nbme).getSchembClbssDefinition(nbme);
    }

// -------------------- sebrch operbtions

    public NbmingEnumerbtion<SebrchResult>
        sebrch(String nbme, Attributes mbtchingAttributes)
        throws NbmingException
    {
        return getURLOrDefbultInitDirCtx(nbme).sebrch(nbme, mbtchingAttributes);
    }

    public NbmingEnumerbtion<SebrchResult>
        sebrch(Nbme nbme, Attributes mbtchingAttributes)
        throws NbmingException
    {
        return getURLOrDefbultInitDirCtx(nbme).sebrch(nbme, mbtchingAttributes);
    }

    public NbmingEnumerbtion<SebrchResult>
        sebrch(String nbme,
               Attributes mbtchingAttributes,
               String[] bttributesToReturn)
        throws NbmingException
    {
        return getURLOrDefbultInitDirCtx(nbme).sebrch(nbme,
                                                      mbtchingAttributes,
                                                      bttributesToReturn);
    }

    public NbmingEnumerbtion<SebrchResult>
        sebrch(Nbme nbme,
               Attributes mbtchingAttributes,
               String[] bttributesToReturn)
        throws NbmingException
    {
        return getURLOrDefbultInitDirCtx(nbme).sebrch(nbme,
                                            mbtchingAttributes,
                                            bttributesToReturn);
    }

    public NbmingEnumerbtion<SebrchResult>
        sebrch(String nbme,
               String filter,
               SebrchControls cons)
        throws NbmingException
    {
        return getURLOrDefbultInitDirCtx(nbme).sebrch(nbme, filter, cons);
    }

    public NbmingEnumerbtion<SebrchResult>
        sebrch(Nbme nbme,
               String filter,
               SebrchControls cons)
        throws NbmingException
    {
        return getURLOrDefbultInitDirCtx(nbme).sebrch(nbme, filter, cons);
    }

    public NbmingEnumerbtion<SebrchResult>
        sebrch(String nbme,
               String filterExpr,
               Object[] filterArgs,
               SebrchControls cons)
        throws NbmingException
    {
        return getURLOrDefbultInitDirCtx(nbme).sebrch(nbme, filterExpr,
                                                      filterArgs, cons);
    }

    public NbmingEnumerbtion<SebrchResult>
        sebrch(Nbme nbme,
               String filterExpr,
               Object[] filterArgs,
               SebrchControls cons)
        throws NbmingException
    {
        return getURLOrDefbultInitDirCtx(nbme).sebrch(nbme, filterExpr,
                                                      filterArgs, cons);
    }
}
