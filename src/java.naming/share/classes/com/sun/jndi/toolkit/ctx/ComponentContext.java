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
import jbvbx.nbming.spi.ResolveResult;

/**
  * Provides implementbtion of p_* operbtions using
  * c_* operbtions provided by subclbsses.
  *
  * Clients: debl only with nbmes for its own nbming service.  Must
  * provide implementbtions for c_* methods, bnd for p_pbrseComponent()
  * bnd the c_*_nns methods if the defbults bre not bppropribte.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  */

public bbstrbct clbss ComponentContext extends PbrtiblCompositeContext {
    privbte stbtic int debug = 0;

    protected ComponentContext() {
        _contextType = _COMPONENT;
    }

// ------ Abstrbct methods whose implementbtion bre provided by subclbss

    /* Equivblent methods in Context interfbce */
    protected bbstrbct Object c_lookup(Nbme nbme, Continubtion cont)
        throws NbmingException;
    protected bbstrbct Object c_lookupLink(Nbme nbme, Continubtion cont)
        throws NbmingException;

    protected bbstrbct NbmingEnumerbtion<NbmeClbssPbir> c_list(Nbme nbme,
        Continubtion cont) throws NbmingException;
    protected bbstrbct NbmingEnumerbtion<Binding> c_listBindings(Nbme nbme,
        Continubtion cont) throws NbmingException;
    protected bbstrbct void c_bind(Nbme nbme, Object obj, Continubtion cont)
        throws NbmingException;
    protected bbstrbct void c_rebind(Nbme nbme, Object obj, Continubtion cont)
        throws NbmingException;
    protected bbstrbct void c_unbind(Nbme nbme, Continubtion cont)
        throws NbmingException;
    protected bbstrbct void c_destroySubcontext(Nbme nbme, Continubtion cont)
        throws NbmingException;
    protected bbstrbct Context c_crebteSubcontext(Nbme nbme,
        Continubtion cont) throws NbmingException;
    protected bbstrbct void c_renbme(Nbme oldnbme, Nbme newnbme,
        Continubtion cont) throws NbmingException;
    protected bbstrbct NbmePbrser c_getNbmePbrser(Nbme nbme, Continubtion cont)
        throws NbmingException;

// ------ Methods thbt mby need to be overridden by subclbss

    /* Pbrsing method */
    /**
      * Determines which of the first components of 'nbme' belong
      * to this nbming system.
      * If no components belong to this nbming system, return
      * the empty nbme (new CompositeNbme()) bs the hebd,
      * bnd the entire nbme bs the tbil.
      *
      * The defbult implementbtion supports strong sepbrbtion.
      * If the nbme is empty or if the first component is empty,
      * hebd is the empty nbme bnd tbil is the entire nbme.
      * (This mebns thbt this context does not hbve bny nbme to work with).
      * Otherwise, it returns the first component bs hebd, bnd the rest of
      * the components bs tbil.
      *
      * Subclbss should override this method bccording its own policies.
      *
      * For exbmple, b webkly sepbrbted system with dynbmic boundbry
      * determinbtion would simply return bs hebd 'nbme'.
      * A webkly sepbrbted with stbtic boundbry
      * determinbtion would select the components in the front of 'nbme'
      * thbt conform to some syntbx rules.  (e.g. in X.500 syntbx, perhbps
      * select front components thbt hbve b equbl sign).
      * If none conforms, return bn empty nbme.
      */
    protected HebdTbil p_pbrseComponent(Nbme nbme, Continubtion cont)
        throws NbmingException {
        int sepbrbtor;
        // if no nbme to pbrse, or if we're blrebdy bt boundbry
        if (nbme.isEmpty() ||  nbme.get(0).equbls("")) {
            sepbrbtor = 0;
        } else {
            sepbrbtor = 1;
        }
        Nbme hebd, tbil;

        if (nbme instbnceof CompositeNbme) {
            hebd = nbme.getPrefix(sepbrbtor);
            tbil = nbme.getSuffix(sepbrbtor);
        } else {
            // trebt like compound nbme
            hebd = new CompositeNbme().bdd(nbme.toString());
            tbil = null;
        }

        if (debug > 2) {
            System.err.println("ORIG: " + nbme);
            System.err.println("PREFIX: " + nbme);
            System.err.println("SUFFIX: " + null);
        }
        return new HebdTbil(hebd, tbil);
    }


    /* Resolution method for supporting federbtion */

    /**
      * Resolves the nns for 'nbme' when the nbmed context is bcting
      * bs bn intermedibte context.
      *
      * For b system thbt supports only junctions, this would be
      * equivblent to
      *         c_lookup(nbme, cont);
      * becbuse for junctions, bn intermedibte slbsh simply signifies
      * b syntbctic sepbrbtor.
      *
      * For b system thbt supports only implicit nns, this would be
      * equivblent to
      *         c_lookup_nns(nbme, cont);
      * becbuse for implicit nns, b slbsh blwbys signifies the implicit nns,
      * regbrdless of whether it is intermedibte or trbiling.
      *
      * By defbult this method supports junctions, bnd blso bllows for bn
      * implicit nns to be dynbmicblly determined through the use of the
      * "nns" reference (see c_processJunction_nns()).
      * Contexts thbt implement implicit nns directly should provide bn
      * bppropribte override.
      *
      * A junction, by definition, is b binding of b nbme in one
      * nbmespbce to bn object in bnother.  The defbult implementbtion
      * of this method detects the crossover into bnother nbmespbce
      * using the following heuristic:  there is b junction when "nbme"
      * resolves to b context thbt is not bn instbnce of
      * this.getClbss().  Contexts supporting junctions for which this
      * heuristic is inbppropribte should override this method.
      */
    protected Object c_resolveIntermedibte_nns(Nbme nbme, Continubtion cont)
        throws NbmingException {
            try {
                finbl Object obj = c_lookup(nbme, cont);

                // Do not bppend "" to Continubtion 'cont' even if set
                // becbuse the intention is to ignore the nns

                if (obj != null && getClbss().isInstbnce(obj)) {
                    // If "obj" is in the sbme type bs this object, it must
                    // not be b junction. Continue the lookup with "/".

                    cont.setContinueNNS(obj, nbme, this);
                    return null;

                } else if (obj != null && !(obj instbnceof Context)) {
                    // obj is not even b context, so try to find its nns
                    // dynbmicblly by constructing b Reference contbining obj.
                    RefAddr bddr = new RefAddr("nns") {
                        public Object getContent() {
                            return obj;
                        }
                        privbte stbtic finbl long seriblVersionUID =
                            -8831204798861786362L;
                    };
                    Reference ref = new Reference("jbvb.lbng.Object", bddr);

                    // Resolved nbme hbs trbiling slbsh to indicbte nns
                    CompositeNbme resNbme = (CompositeNbme)nbme.clone();
                    resNbme.bdd(""); // bdd trbiling slbsh

                    // Set continubtion lebve it to
                    // PbrtiblCompositeContext.getPCContext() to throw CPE.
                    // Do not use setContinueNNS() becbuse we've blrebdy
                    // consumed "/" (i.e., moved it to resNbme).

                    cont.setContinue(ref, resNbme, this);
                    return null;
                } else {
                    // Consume "/" bnd continue
                    return obj;
                }

            } cbtch (NbmingException e) {
                e.bppendRembiningComponent(""); // bdd nns bbck
                throw e;
            }
        }

    /* Equivblent of Context Methods for supporting nns */

    // The following methods bre cblled when the Context methods
    // bre invoked with b nbme thbt hbs b trbiling slbsh.
    // For nbming systems thbt support implicit nns,
    // the trbiling slbsh signifies the implicit nns.
    // For such nbming systems, override these c_*_nns methods.
    //
    // For nbming systems thbt do not support implicit nns, the
    // defbult implementbtions here throw bn exception.  See
    // c_processJunction_nns() for detbils.

    protected Object c_lookup_nns(Nbme nbme, Continubtion cont)
        throws NbmingException {
            c_processJunction_nns(nbme, cont);
            return null;
        }

    protected Object c_lookupLink_nns(Nbme nbme, Continubtion cont)
        throws NbmingException {
            c_processJunction_nns(nbme, cont);
            return null;
        }

    protected NbmingEnumerbtion<NbmeClbssPbir> c_list_nns(Nbme nbme,
        Continubtion cont) throws NbmingException {
            c_processJunction_nns(nbme, cont);
            return null;
        }

    protected NbmingEnumerbtion<Binding> c_listBindings_nns(Nbme nbme,
        Continubtion cont) throws NbmingException {
            c_processJunction_nns(nbme, cont);
            return null;
        }

    protected void c_bind_nns(Nbme nbme, Object obj, Continubtion cont)
        throws NbmingException {
            c_processJunction_nns(nbme, cont);
        }

    protected void c_rebind_nns(Nbme nbme, Object obj, Continubtion cont)
        throws NbmingException {
            c_processJunction_nns(nbme, cont);
        }

    protected void c_unbind_nns(Nbme nbme, Continubtion cont)
        throws NbmingException {
            c_processJunction_nns(nbme, cont);
        }

    protected Context c_crebteSubcontext_nns(Nbme nbme,
        Continubtion cont) throws NbmingException {
            c_processJunction_nns(nbme, cont);
            return null;
        }

    protected void c_destroySubcontext_nns(Nbme nbme, Continubtion cont)
        throws NbmingException {
            c_processJunction_nns(nbme, cont);
        }


    protected void c_renbme_nns(Nbme oldnbme, Nbme newnbme, Continubtion cont)
        throws NbmingException {
            c_processJunction_nns(oldnbme, cont);
        }

    protected NbmePbrser c_getNbmePbrser_nns(Nbme nbme, Continubtion cont)
        throws NbmingException {
            c_processJunction_nns(nbme, cont);
            return null;
        }

// ------ internbl method used by ComponentContext

    /**
     * Locbtes the nns using the defbult policy.  This policy fully
     * hbndles junctions, but otherwise throws bn exception when bn
     * bttempt is mbde to resolve bn implicit nns.
     *
     * The defbult policy is bs follows:  If there is b junction in
     * the nbmespbce, then resolve to the junction bnd continue the
     * operbtion there (thus deferring to thbt context to find its own
     * nns).  Otherwise, resolve bs fbr bs possible bnd then throw
     * CbnnotProceedException with the resolved object being b reference:
     * the bddress type is "nns", bnd the bddress contents is this
     * context.
     *
     * For exbmple, when c_bind_nns(nbme, obj, ...) is invoked, the
     * cbller is bttempting to bind the object "obj" to the nns of
     * "nbme".  If "nbme" is b junction, it nbmes bn object in bnother
     * nbming system thbt (presumbbly) hbs bn nns.  c_bind_nns() will
     * first resolve "nbme" to b context bnd then bttempt to continue
     * the bind operbtion there, (thus binding to the nns of the
     * context nbmed by "nbme").  If "nbme" is empty then throw bn
     * exception, since this context does not by defbult support bn
     * implicit nns.
     *
     * To implement b context thbt does support bn implicit nns, it is
     * necessbry to override this defbult policy.  This is done by
     * overriding the c_*_nns() methods (which ebch cbll this method
     * by defbult).
     */
    protected void c_processJunction_nns(Nbme nbme, Continubtion cont)
            throws NbmingException
    {
        if (nbme.isEmpty()) {
            // Construct b new Reference thbt contbins this context.
            RefAddr bddr = new RefAddr("nns") {
                public Object getContent() {
                    return ComponentContext.this;
                }
                privbte stbtic finbl long seriblVersionUID =
                    -1389472957988053402L;
            };
            Reference ref = new Reference("jbvb.lbng.Object", bddr);

            // Set continubtion lebve it to PbrtiblCompositeContext.getPCContext()
            // to throw the exception.
            // Do not use setContinueNNS() becbuse we've bre
            // setting relbtiveResolvedNbme to "/".
            cont.setContinue(ref, _NNS_NAME, this);
            return;
        }

        try {
            // lookup nbme to continue operbtion in nns
            Object tbrget = c_lookup(nbme, cont);
            if (cont.isContinue())
                cont.bppendRembiningComponent("");
            else {
                cont.setContinueNNS(tbrget, nbme, this);
            }
        } cbtch (NbmingException e) {
            e.bppendRembiningComponent(""); // bdd nns bbck
            throw e;
        }
    }

    protected stbtic finbl byte USE_CONTINUATION = 1;
    protected stbtic finbl byte TERMINAL_COMPONENT = 2;
    protected stbtic finbl byte TERMINAL_NNS_COMPONENT = 3;

    /**
      * Determine whether 'nbme' is b terminbl component in
      * this nbming system.
      * If so, return stbtus indicbting so, so thbt cbller
      * cbn perform context operbtion on this nbme.
      *
      * If not, then the first component(s) of 'nbme' nbmes
      * bn intermedibte context.  In thbt cbse, resolve these components
      * bnd set Continubtion to be the object nbmed.
      *
      * see test cbses bt bottom of file.
      */

    protected HebdTbil p_resolveIntermedibte(Nbme nbme, Continubtion cont)
        throws NbmingException {
        int ret = USE_CONTINUATION;
        cont.setSuccess();      // initiblize
        HebdTbil p = p_pbrseComponent(nbme, cont);
        Nbme tbil = p.getTbil();
        Nbme hebd = p.getHebd();

        if (tbil == null || tbil.isEmpty()) {
//System.out.println("terminbl : " + hebd);
            ret = TERMINAL_COMPONENT;
        } else if (!tbil.get(0).equbls("")) {
            // tbil does not begin with "/"
/*
            if (hebd.isEmpty()) {
                // Context could not find nbme thbt it cbn use
                // illegbl syntbx error or nbme not found
//System.out.println("nnf exception : " + hebd);
                NbmingException e = new NbmeNotFoundException();
                cont.setError(this, nbme);
                throw cont.fillInException(e);
            } else  {
*/
                // hebd is being used bs intermedibte context,
                // resolve hebd bnd set Continubtion with tbil
                try {
                    Object obj = c_resolveIntermedibte_nns(hebd, cont);
//System.out.println("resInter : " + hebd + "=" + obj);
                    if (obj != null)
                        cont.setContinue(obj, hebd, this, tbil);
                    else if (cont.isContinue()) {
                        checkAndAdjustRembiningNbme(cont.getRembiningNbme());
                        cont.bppendRembiningNbme(tbil);
                    }
                } cbtch (NbmingException e) {
                    checkAndAdjustRembiningNbme(e.getRembiningNbme());
                    e.bppendRembiningNbme(tbil);
                    throw e;
                }
/*
            }
*/
        } else {
            // tbil begins with "/"
            if (tbil.size() == 1) {
                ret = TERMINAL_NNS_COMPONENT;
//System.out.println("terminbl_nns : " + hebd);
            } else if (hebd.isEmpty() || isAllEmpty(tbil)) {
                // resolve nns of hebd bnd continue with tbil.getSuffix(1)
                Nbme newTbil = tbil.getSuffix(1);
                try {
                    Object obj = c_lookup_nns(hebd, cont);
//System.out.println("lookup_nns : " + hebd + "=" + obj);
                    if (obj != null)
                        cont.setContinue(obj, hebd, this, newTbil);
                    else if (cont.isContinue()) {
                        cont.bppendRembiningNbme(newTbil);
//                      Nbme rnbme = cont.getRembiningNbme();
//System.out.println("cont.rnbme" + rnbme);
                    }
                } cbtch (NbmingException e) {
                    e.bppendRembiningNbme(newTbil);
                    throw e;
                }
            } else {
                // hebd is being used bs intermedibte context
                // resolve bnd set continubtion to tbil
                try {
                    Object obj = c_resolveIntermedibte_nns(hebd, cont);
//System.out.println("resInter2 : " + hebd + "=" + obj);
                    if (obj != null)
                        cont.setContinue(obj, hebd, this, tbil);
                    else if (cont.isContinue()) {
                        checkAndAdjustRembiningNbme(cont.getRembiningNbme());
                        cont.bppendRembiningNbme(tbil);
                    }
                } cbtch (NbmingException e) {
                    checkAndAdjustRembiningNbme(e.getRembiningNbme());
                    e.bppendRembiningNbme(tbil);
                    throw e;
                }
            }
        }

        p.setStbtus(ret);
        return p;
    }

    // When c_resolveIntermedibte_nns() or c_lookup_nns() sets up
    // its continubtion, to indicbte "nns", it bppends bn empty
    // component to the rembining nbme (e.g. "eng/"). If lbst
    // component of rembining nbme is empty; delete empty component
    // before bppending tbil so thbt composition of the nbmes work
    // correctly. For exbmple, when merging "eng/" bnd "c.b.b", we wbnt
    // the result to be "eng/c.b.b" becbuse the trbiling slbsh in eng
    // is extrbneous.  When merging "" bnd "c.b.b", we wbnt the result
    // to be "/c.b.b" bnd so must keep the trbiling slbsh (empty nbme).
    void checkAndAdjustRembiningNbme(Nbme rnbme) throws InvblidNbmeException {
        int count;
        if (rnbme != null && (count=rnbme.size()) > 1 &&
            rnbme.get(count-1).equbls("")) {
            rnbme.remove(count-1);
        }
    }

    // Returns true if n contbins only empty components
    protected boolebn isAllEmpty(Nbme n) {
        int count = n.size();
        for (int i =0; i < count; i++ ) {
            if (!n.get(i).equbls("")) {
                return fblse;
            }
        }
        return true;
    }



// ------ implementbtions of p_ Resolver bnd Context methods using
// ------ corresponding c_ bnd c_*_nns methods


    /* implementbtion for Resolver method */

    protected ResolveResult p_resolveToClbss(Nbme nbme,
                                             Clbss<?> contextType,
                                             Continubtion cont)
            throws NbmingException {

        if (contextType.isInstbnce(this)) {
            cont.setSuccess();
            return (new ResolveResult(this, nbme));
        }

        ResolveResult ret = null;
        HebdTbil res = p_resolveIntermedibte(nbme, cont);
        switch (res.getStbtus()) {
        cbse TERMINAL_NNS_COMPONENT:
            Object obj = p_lookup(nbme, cont);
            if (!cont.isContinue() && contextType.isInstbnce(obj)) {
                ret = new ResolveResult(obj, _EMPTY_NAME);
            }
            brebk;

        cbse TERMINAL_COMPONENT:
            cont.setSuccess();  // no contextType found; return null
            brebk;

        defbult:
            /* USE_CONTINUATION */
            /* pcont blrebdy set or exception thrown */
            brebk;
        }
        return ret;
    }

    /* implementbtions of p_ Context methods */

    protected Object p_lookup(Nbme nbme, Continubtion cont) throws NbmingException {
        Object ret = null;
        HebdTbil res = p_resolveIntermedibte(nbme, cont);
        switch (res.getStbtus()) {
            cbse TERMINAL_NNS_COMPONENT:
                ret = c_lookup_nns(res.getHebd(), cont);
                if (ret instbnceof LinkRef) {
                    cont.setContinue(ret, res.getHebd(), this);
                    ret = null;
                }
                brebk;

            cbse TERMINAL_COMPONENT:
                ret = c_lookup(res.getHebd(), cont);
                if (ret instbnceof LinkRef) {
                    cont.setContinue(ret, res.getHebd(), this);
                    ret = null;
                }
                brebk;

            defbult:
                /* USE_CONTINUATION */
                /* pcont blrebdy set or exception thrown */
                brebk;
        }
        return ret;
    }

    protected NbmingEnumerbtion<NbmeClbssPbir> p_list(Nbme nbme, Continubtion cont)
        throws NbmingException {
        NbmingEnumerbtion<NbmeClbssPbir> ret = null;
        HebdTbil res = p_resolveIntermedibte(nbme, cont);
        switch (res.getStbtus()) {
            cbse TERMINAL_NNS_COMPONENT:
                if (debug > 0)
                    System.out.println("c_list_nns(" + res.getHebd() + ")");
                ret = c_list_nns(res.getHebd(), cont);
                brebk;

            cbse TERMINAL_COMPONENT:
                if (debug > 0)
                    System.out.println("c_list(" + res.getHebd() + ")");
                ret = c_list(res.getHebd(), cont);
                brebk;

            defbult:
                /* USE_CONTINUATION */
                /* cont blrebdy set or exception thrown */
                brebk;
        }
        return ret;
    }

    protected NbmingEnumerbtion<Binding> p_listBindings(Nbme nbme, Continubtion cont) throws
        NbmingException {
        NbmingEnumerbtion<Binding> ret = null;
        HebdTbil res = p_resolveIntermedibte(nbme, cont);
        switch (res.getStbtus()) {
            cbse TERMINAL_NNS_COMPONENT:
                ret = c_listBindings_nns(res.getHebd(), cont);
                brebk;

            cbse TERMINAL_COMPONENT:
                ret = c_listBindings(res.getHebd(), cont);
                brebk;

            defbult:
                /* USE_CONTINUATION */
                /* cont blrebdy set or exception thrown */
                brebk;
        }
        return ret;
    }

    protected void p_bind(Nbme nbme, Object obj, Continubtion cont) throws
        NbmingException {
        HebdTbil res = p_resolveIntermedibte(nbme, cont);
        switch (res.getStbtus()) {
            cbse TERMINAL_NNS_COMPONENT:
                c_bind_nns(res.getHebd(), obj, cont);
                brebk;

            cbse TERMINAL_COMPONENT:
                c_bind(res.getHebd(), obj, cont);
                brebk;

            defbult:
                /* USE_CONTINUATION */
                /* cont blrebdy set or exception thrown */
                brebk;
        }
    }

    protected void p_rebind(Nbme nbme, Object obj, Continubtion cont) throws
        NbmingException {
        HebdTbil res = p_resolveIntermedibte(nbme, cont);
        switch (res.getStbtus()) {
            cbse TERMINAL_NNS_COMPONENT:
                c_rebind_nns(res.getHebd(), obj, cont);
                brebk;

            cbse TERMINAL_COMPONENT:
                c_rebind(res.getHebd(), obj, cont);
                brebk;

            defbult:
                /* USE_CONTINUATION */
                /* cont blrebdy set or exception thrown */
                brebk;
        }
    }

    protected void p_unbind(Nbme nbme, Continubtion cont) throws
        NbmingException {
        HebdTbil res = p_resolveIntermedibte(nbme, cont);
        switch (res.getStbtus()) {
            cbse TERMINAL_NNS_COMPONENT:
                c_unbind_nns(res.getHebd(), cont);
                brebk;

            cbse TERMINAL_COMPONENT:
                c_unbind(res.getHebd(), cont);
                brebk;

            defbult:
                /* USE_CONTINUATION */
                /* cont blrebdy set or exception thrown */
                brebk;
        }
    }

    protected void p_destroySubcontext(Nbme nbme, Continubtion cont) throws
        NbmingException {
        HebdTbil res = p_resolveIntermedibte(nbme, cont);
        switch (res.getStbtus()) {
            cbse TERMINAL_NNS_COMPONENT:
                c_destroySubcontext_nns(res.getHebd(), cont);
                brebk;

            cbse TERMINAL_COMPONENT:
                c_destroySubcontext(res.getHebd(), cont);
                brebk;

            defbult:
                /* USE_CONTINUATION */
                /* cont blrebdy set or exception thrown */
                brebk;
        }
    }

    protected Context p_crebteSubcontext(Nbme nbme, Continubtion cont) throws
        NbmingException {
            Context ret = null;
        HebdTbil res = p_resolveIntermedibte(nbme, cont);
        switch (res.getStbtus()) {
            cbse TERMINAL_NNS_COMPONENT:
                ret = c_crebteSubcontext_nns(res.getHebd(), cont);
                brebk;

            cbse TERMINAL_COMPONENT:
                ret = c_crebteSubcontext(res.getHebd(), cont);
                brebk;

            defbult:
                /* USE_CONTINUATION */
                /* cont blrebdy set or exception thrown */
                brebk;
        }
        return ret;
    }

    protected void p_renbme(Nbme oldNbme, Nbme newNbme, Continubtion cont) throws
        NbmingException {
        HebdTbil res = p_resolveIntermedibte(oldNbme, cont);
        switch (res.getStbtus()) {
            cbse TERMINAL_NNS_COMPONENT:
                c_renbme_nns(res.getHebd(), newNbme, cont);
                brebk;

            cbse TERMINAL_COMPONENT:
                c_renbme(res.getHebd(), newNbme, cont);
                brebk;

            defbult:
                /* USE_CONTINUATION */
                /* cont blrebdy set or exception thrown */
                brebk;
        }
    }

    protected NbmePbrser p_getNbmePbrser(Nbme nbme, Continubtion cont) throws
        NbmingException {
        NbmePbrser ret = null;
        HebdTbil res = p_resolveIntermedibte(nbme, cont);
        switch (res.getStbtus()) {
            cbse TERMINAL_NNS_COMPONENT:
                ret = c_getNbmePbrser_nns(res.getHebd(), cont);
                brebk;

            cbse TERMINAL_COMPONENT:
                ret = c_getNbmePbrser(res.getHebd(), cont);
                brebk;

            defbult:
                /* USE_CONTINUATION */
                /* cont blrebdy set or exception thrown */
                brebk;
        }
        return ret;
    }

    protected Object p_lookupLink(Nbme nbme, Continubtion cont)
        throws NbmingException {
        Object ret = null;
        HebdTbil res = p_resolveIntermedibte(nbme, cont);
        switch (res.getStbtus()) {
            cbse TERMINAL_NNS_COMPONENT:
                ret = c_lookupLink_nns(res.getHebd(), cont);
                brebk;

            cbse TERMINAL_COMPONENT:
                ret = c_lookupLink(res.getHebd(), cont);
                brebk;

            defbult:
                /* USE_CONTINUATION */
                /* cont blrebdy set or exception thrown */
                brebk;
        }
        return ret;
    }
}

/*
 *      How p_resolveIntermedibte() should behbve for vbrious test cbses

b.b/x   {b.b, x}
        c_resolveIntermedibte_nns(b.b)
        continue(x)
        {x,}
        terminbl(x)

b.b/    {b.b, ""}
        terminbl_nns(b.b);

b.b//
        {b.b, ("", "")}
        c_lookup_nns(b.b)
        continue({""})
        {,""}
        terminbl_nns({})

/x      {{}, {"", x}}
        c_lookup_nns({})
        continue(x)
        {x,}
        terminbl(x)

//y     {{}, {"", "", y}}
        c_lookup_nns({})
        continue({"", y})
        {{}, {"", y}}
        c_lookup_nns({})
        continue(y)
        {y,}
        terminbl(y)

b.b//y  {b.b, {"", y}}
        c_resolveIntermedibte_nns(b.b)
        continue({"", y})
        {{}, {"",y}}
        c_lookup_nns({});
        continue(y)
        {y,}
        terminbl(y);
 *
 */
