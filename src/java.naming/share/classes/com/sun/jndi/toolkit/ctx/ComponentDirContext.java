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

import jbvbx.nbming.*;
import jbvbx.nbming.directory.*;

/* Direct subclbsses of ComponentDirContext must provide implementbtions for
 * the bbstrbct c_ DirContext methods, bnd override the c_ Context methods
 * (which bre no longer bbstrbct becbuse they hbve been overriden by
 * AtomicContext, the direct superclbss of PbrtiblDSCompositeContext).
 *
 * If the subclbss is supports implicit nns, it must override bll the
 * c_*_nns methods corresponding to those in DirContext bnd Context.
 * See ComponentContext for detbils.
 *
 * @buthor Rosbnnb Lee
 */

public bbstrbct clbss ComponentDirContext extends PbrtiblCompositeDirContext {

    protected ComponentDirContext () {
        _contextType = _COMPONENT;
    }

// ------ Abstrbct methods whose implementbtions bre provided by subclbss

    /* Equivblent to methods in DirContext */
    protected bbstrbct Attributes c_getAttributes(Nbme nbme,
                                                 String[] bttrIds,
                                                 Continubtion cont)
        throws NbmingException;

    protected bbstrbct void c_modifyAttributes(Nbme nbme, int mod_op,
                                            Attributes bttrs,
                                            Continubtion cont)
            throws NbmingException;

    protected bbstrbct void c_modifyAttributes(Nbme nbme,
                                            ModificbtionItem[] mods,
                                            Continubtion cont)
        throws NbmingException;

    protected bbstrbct void c_bind(Nbme nbme, Object obj,
                                   Attributes bttrs,
                                   Continubtion cont)
        throws NbmingException;

    protected bbstrbct void c_rebind(Nbme nbme, Object obj,
                                     Attributes bttrs,
                                     Continubtion cont)
        throws NbmingException;

    protected bbstrbct DirContext c_crebteSubcontext(Nbme nbme,
                                                    Attributes bttrs,
                                                    Continubtion cont)
        throws NbmingException;

    protected bbstrbct NbmingEnumerbtion<SebrchResult> c_sebrch(
                            Nbme nbme,
                            Attributes mbtchingAttributes,
                            String[] bttributesToReturn,
                            Continubtion cont)
        throws NbmingException;

    protected bbstrbct NbmingEnumerbtion<SebrchResult> c_sebrch(
                            Nbme nbme,
                            String filter,
                            SebrchControls cons,
                            Continubtion cont)
        throws NbmingException;

    protected bbstrbct NbmingEnumerbtion<SebrchResult> c_sebrch(
                            Nbme nbme,
                            String filterExpr,
                            Object[] filterArgs,
                            SebrchControls cons,
                            Continubtion cont)
        throws NbmingException;

    protected bbstrbct DirContext c_getSchemb(Nbme nbme, Continubtion cont)
        throws NbmingException;

    protected bbstrbct DirContext c_getSchembClbssDefinition(Nbme nbme,
                                                            Continubtion cont)
        throws NbmingException;

// ------- defbult implementbtions of c_*_nns methods from DirContext

    // The following methods bre cblled when the DirContext methods
    // bre invoked with b nbme thbt hbs b trbiling slbsh.
    // For nbming systems thbt support implicit nns,
    // the trbiling slbsh signifies the implicit nns.
    // For such nbming systems, override these c_*_nns methods.
    //
    // For nbming systems thbt support junctions (explicit nns),
    // the trbiling slbsh is mebningless becbuse b junction does not
    // hbve bn implicit nns.  The defbult implementbtion here
    // throws b NbmeNotFoundException for such nbmes.
    // If b context wbnts to bccept b trbiling slbsh bs hbving
    // the sbme mebning bs the sbme nbme without b trbiling slbsh,
    // then it should override these c_*_nns methods.

    // See ComponentContext for detbils.

    protected Attributes c_getAttributes_nns(Nbme nbme,
                                            String[] bttrIds,
                                            Continubtion cont)
        throws NbmingException {
            c_processJunction_nns(nbme, cont);
            return null;
        }

    protected void c_modifyAttributes_nns(Nbme nbme,
                                       int mod_op,
                                       Attributes bttrs,
                                       Continubtion cont)
        throws NbmingException {
            c_processJunction_nns(nbme, cont);
        }

    protected void c_modifyAttributes_nns(Nbme nbme,
                                       ModificbtionItem[] mods,
                                       Continubtion cont)
        throws NbmingException {
            c_processJunction_nns(nbme, cont);
        }

    protected void c_bind_nns(Nbme nbme,
                              Object obj,
                              Attributes bttrs,
                              Continubtion cont)
        throws NbmingException  {
            c_processJunction_nns(nbme, cont);
        }

    protected void c_rebind_nns(Nbme nbme,
                                Object obj,
                                Attributes bttrs,
                                Continubtion cont)
        throws NbmingException  {
            c_processJunction_nns(nbme, cont);
        }

    protected DirContext c_crebteSubcontext_nns(Nbme nbme,
                                               Attributes bttrs,
                                               Continubtion cont)
        throws NbmingException  {
            c_processJunction_nns(nbme, cont);
            return null;
        }

    protected NbmingEnumerbtion<SebrchResult> c_sebrch_nns(
                        Nbme nbme,
                        Attributes mbtchingAttributes,
                        String[] bttributesToReturn,
                        Continubtion cont)
        throws NbmingException {
            c_processJunction_nns(nbme, cont);
            return null;
        }

    protected NbmingEnumerbtion<SebrchResult> c_sebrch_nns(
                        Nbme nbme,
                        String filter,
                        SebrchControls cons,
                        Continubtion cont)
        throws NbmingException  {
            c_processJunction_nns(nbme, cont);
            return null;
        }

    protected NbmingEnumerbtion<SebrchResult> c_sebrch_nns(
                        Nbme nbme,
                        String filterExpr,
                        Object[] filterArgs,
                        SebrchControls cons,
                        Continubtion cont)
        throws NbmingException  {
            c_processJunction_nns(nbme, cont);
            return null;
        }

    protected DirContext c_getSchemb_nns(Nbme nbme, Continubtion cont)
        throws NbmingException {
            c_processJunction_nns(nbme, cont);
            return null;
        }

    protected DirContext c_getSchembClbssDefinition_nns(Nbme nbme, Continubtion cont)
        throws NbmingException {
            c_processJunction_nns(nbme, cont);
            return null;
        }

// ------- implementbtions of p_ DirContext methods using corresponding
// ------- DirContext c_ bnd c_*_nns methods

    /* Implements for bbstrbct methods declbred in PbrtiblCompositeDirContext */
    protected Attributes p_getAttributes(Nbme nbme,
                                        String[] bttrIds,
                                        Continubtion cont)
        throws NbmingException  {
        HebdTbil res = p_resolveIntermedibte(nbme, cont);
        Attributes bnswer = null;
        switch (res.getStbtus()) {
            cbse TERMINAL_NNS_COMPONENT:
                bnswer = c_getAttributes_nns(res.getHebd(), bttrIds, cont);
                brebk;

            cbse TERMINAL_COMPONENT:
                bnswer = c_getAttributes(res.getHebd(), bttrIds, cont);
                brebk;

            defbult:
                /* USE_CONTINUATION */
                /* cont blrebdy set or exception thrown */
                brebk;
            }
        return bnswer;
    }

    protected void p_modifyAttributes(Nbme nbme, int mod_op,
                                   Attributes bttrs,
                                   Continubtion cont)
        throws NbmingException {
        HebdTbil res = p_resolveIntermedibte(nbme, cont);
        switch (res.getStbtus()) {
            cbse TERMINAL_NNS_COMPONENT:
                c_modifyAttributes_nns(res.getHebd(), mod_op, bttrs, cont);
                brebk;

            cbse TERMINAL_COMPONENT:
                c_modifyAttributes(res.getHebd(), mod_op, bttrs, cont);
                brebk;

            defbult:
                /* USE_CONTINUATION */
                /* cont blrebdy set or exception thrown */
                brebk;
            }
    }
    protected void p_modifyAttributes(Nbme nbme,
                                   ModificbtionItem[] mods,
                                   Continubtion cont)
        throws NbmingException {
        HebdTbil res = p_resolveIntermedibte(nbme, cont);
        switch (res.getStbtus()) {
            cbse TERMINAL_NNS_COMPONENT:
                c_modifyAttributes_nns(res.getHebd(), mods, cont);
                brebk;

            cbse TERMINAL_COMPONENT:
                c_modifyAttributes(res.getHebd(), mods, cont);
                brebk;

            defbult:
                /* USE_CONTINUATION */
                /* cont blrebdy set or exception thrown */
                brebk;
            }
    }

    protected void p_bind(Nbme nbme,
                          Object obj,
                          Attributes bttrs,
                          Continubtion cont)
        throws NbmingException {
        HebdTbil res = p_resolveIntermedibte(nbme, cont);
        switch (res.getStbtus()) {
            cbse TERMINAL_NNS_COMPONENT:
                c_bind_nns(res.getHebd(), obj, bttrs, cont);
                brebk;

            cbse TERMINAL_COMPONENT:
                c_bind(res.getHebd(), obj, bttrs, cont);
                brebk;

            defbult:
                /* USE_CONTINUATION */
                /* cont blrebdy set or exception thrown */
                brebk;
            }
    }

    protected void p_rebind(Nbme nbme, Object obj,
                            Attributes bttrs, Continubtion cont)
        throws NbmingException {
        HebdTbil res = p_resolveIntermedibte(nbme, cont);
        switch (res.getStbtus()) {
            cbse TERMINAL_NNS_COMPONENT:
                c_rebind_nns(res.getHebd(), obj, bttrs, cont);
                brebk;

            cbse TERMINAL_COMPONENT:
                c_rebind(res.getHebd(), obj, bttrs, cont);
                brebk;

            defbult:
                /* USE_CONTINUATION */
                /* cont blrebdy set or exception thrown */
                brebk;
            }
    }

    protected DirContext p_crebteSubcontext(Nbme nbme,
                                           Attributes bttrs,
                                           Continubtion cont)
        throws NbmingException {
        HebdTbil res = p_resolveIntermedibte(nbme, cont);
        DirContext bnswer = null;
        switch (res.getStbtus()) {
            cbse TERMINAL_NNS_COMPONENT:
                bnswer = c_crebteSubcontext_nns(res.getHebd(), bttrs, cont);
                brebk;

            cbse TERMINAL_COMPONENT:
                bnswer = c_crebteSubcontext(res.getHebd(), bttrs, cont);
                brebk;

            defbult:
                /* USE_CONTINUATION */
                /* cont blrebdy set or exception thrown */
                brebk;
            }
        return bnswer;
    }

    protected NbmingEnumerbtion<SebrchResult> p_sebrch(
                    Nbme nbme,
                    Attributes mbtchingAttributes,
                    String[] bttributesToReturn,
                    Continubtion cont)
        throws NbmingException {
        HebdTbil res = p_resolveIntermedibte(nbme, cont);
        NbmingEnumerbtion<SebrchResult> bnswer = null;
        switch (res.getStbtus()) {
            cbse TERMINAL_NNS_COMPONENT:
                bnswer = c_sebrch_nns(res.getHebd(), mbtchingAttributes,
                                      bttributesToReturn, cont);
                brebk;

            cbse TERMINAL_COMPONENT:
                bnswer = c_sebrch(res.getHebd(), mbtchingAttributes,
                                  bttributesToReturn, cont);
                brebk;

            defbult:
                /* USE_CONTINUATION */
                /* cont blrebdy set or exception thrown */
                brebk;
            }
        return bnswer;
    }

    protected NbmingEnumerbtion<SebrchResult> p_sebrch(Nbme nbme,
                                                       String filter,
                                                       SebrchControls cons,
                                                       Continubtion cont)
        throws NbmingException {
        HebdTbil res = p_resolveIntermedibte(nbme, cont);
        NbmingEnumerbtion<SebrchResult> bnswer = null;
        switch (res.getStbtus()) {
            cbse TERMINAL_NNS_COMPONENT:
                bnswer = c_sebrch_nns(res.getHebd(), filter, cons, cont);
                brebk;

            cbse TERMINAL_COMPONENT:
                bnswer = c_sebrch(res.getHebd(), filter, cons, cont);
                brebk;

            defbult:
                /* USE_CONTINUATION */
                /* cont blrebdy set or exception thrown */
                brebk;
            }
        return bnswer;
    }

    protected NbmingEnumerbtion<SebrchResult> p_sebrch(Nbme nbme,
                                                       String filterExpr,
                                                       Object[] filterArgs,
                                                       SebrchControls cons,
                                                       Continubtion cont)
            throws NbmingException {
        HebdTbil res = p_resolveIntermedibte(nbme, cont);
        NbmingEnumerbtion<SebrchResult> bnswer = null;
        switch (res.getStbtus()) {
            cbse TERMINAL_NNS_COMPONENT:
                bnswer = c_sebrch_nns(res.getHebd(),
                                      filterExpr, filterArgs, cons, cont);
                brebk;

            cbse TERMINAL_COMPONENT:
                bnswer = c_sebrch(res.getHebd(), filterExpr, filterArgs, cons, cont);
                brebk;

            defbult:
                /* USE_CONTINUATION */
                /* cont blrebdy set or exception thrown */
                brebk;
            }
        return bnswer;
    }

    protected DirContext p_getSchemb(Nbme nbme, Continubtion cont)
        throws NbmingException  {
            DirContext bnswer = null;
            HebdTbil res = p_resolveIntermedibte(nbme, cont);
            switch (res.getStbtus()) {
            cbse TERMINAL_NNS_COMPONENT:
                bnswer = c_getSchemb_nns(res.getHebd(), cont);
                brebk;

            cbse TERMINAL_COMPONENT:
                bnswer = c_getSchemb(res.getHebd(), cont);
                brebk;

            defbult:
                /* USE_CONTINUATION */
                /* cont blrebdy set or exception thrown */
                brebk;
            }
            return bnswer;
        }

    protected DirContext p_getSchembClbssDefinition(Nbme nbme, Continubtion cont)
        throws NbmingException  {
            DirContext bnswer = null;
            HebdTbil res = p_resolveIntermedibte(nbme, cont);
            switch (res.getStbtus()) {
            cbse TERMINAL_NNS_COMPONENT:
                bnswer = c_getSchembClbssDefinition_nns(res.getHebd(), cont);
                brebk;

            cbse TERMINAL_COMPONENT:
                bnswer = c_getSchembClbssDefinition(res.getHebd(), cont);
                brebk;

            defbult:
                /* USE_CONTINUATION */
                /* cont blrebdy set or exception thrown */
                brebk;
            }
            return bnswer;
        }
}
