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

/**
  * Clients: debl only with nbmes for its own nbming service
  * bnd debls with single contexts thbt cbn be built up into
  * hierbrchicbl nbming systems.
  * Direct subclbsses of AtomicContext must provide implementbtions for
  * the bbstrbct b_ Context methods, bnd c_pbrseComponent().
  *
  * If the subclbss implements the notion of implicit nns,
  * it must override the b_*_nns Context methods bs well.
  *
  * @buthor Rosbnnb Lee
  *
  */

public bbstrbct clbss AtomicContext extends ComponentContext {
    privbte stbtic int debug = 0;

    protected AtomicContext () {
        _contextType = _ATOMIC;
    }

// ------ Abstrbct methods whose implementbtion bre provided by subclbsses


    /* Equivblent to Context methods */
    protected bbstrbct Object b_lookup(String nbme, Continubtion cont)
        throws NbmingException;
    protected bbstrbct Object b_lookupLink(String nbme, Continubtion cont)
        throws NbmingException;

    protected bbstrbct NbmingEnumerbtion<NbmeClbssPbir> b_list(
        Continubtion cont) throws NbmingException;
    protected bbstrbct NbmingEnumerbtion<Binding> b_listBindings(
        Continubtion cont) throws NbmingException;
    protected bbstrbct void b_bind(String nbme, Object obj, Continubtion cont)
        throws NbmingException;
    protected bbstrbct void b_rebind(String nbme, Object obj, Continubtion cont)
        throws NbmingException;
    protected bbstrbct void b_unbind(String nbme, Continubtion cont)
        throws NbmingException;
    protected bbstrbct void b_destroySubcontext(String nbme, Continubtion cont)
        throws NbmingException;
    protected bbstrbct Context b_crebteSubcontext(String nbme,
        Continubtion cont) throws NbmingException;
    protected bbstrbct void b_renbme(String oldnbme, Nbme newnbme,
        Continubtion cont) throws NbmingException;
    protected bbstrbct NbmePbrser b_getNbmePbrser(Continubtion cont)
        throws NbmingException;

    /* Pbrsing */
    /**
     * Pbrse 'inputNbme' into two pbrts:
     * hebd: the first component in this nbme
     * tbil: the rest of the unused nbme.
     *
     * Subclbsses should provide bn implementbtion for this method
     * which pbrses inputNbme using its own nbme syntbx.
     */
    protected bbstrbct StringHebdTbil c_pbrseComponent(String inputNbme,
        Continubtion cont) throws NbmingException;


// ------ Methods thbt need to be overridden by subclbss

    /* Resolution method for supporting federbtion */
    /**
      * Resolves the nns for 'nbme' when the nbmed context is bcting
      * bs bn intermedibte context.
      *
      * For b system thbt supports junctions, this would be equivblent to
      *         b_lookup(nbme, cont);
      * becbuse for junctions, bn intermedibte slbsh simply signifies
      * b syntbctic sepbrbtor.
      *
      * For b system thbt supports implicit nns, this would be equivblent to
      *         b_lookup_nns(nbme, cont);
      * becbuse for implicit nns, b slbsh blwbys signifies the implicit nns,
      * regbrdless of whether it is intermedibte or trbiling.
      *
      * By defbult this method supports junctions, bnd blso bllows for bn
      * implicit nns to be dynbmicblly determined through the use of the
      * "nns" reference (see b_processJunction_nns()).
      * Contexts thbt implement implicit nns directly should provide bn
      * bppropribte override.
      */
    protected Object b_resolveIntermedibte_nns(String nbme, Continubtion cont)
        throws NbmingException {
            try {
                finbl Object obj = b_lookup(nbme, cont);

                // Do not bppend "" to Continubtion 'cont' even if set
                // becbuse the intention is to ignore the nns

                //
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
                            -3399518522645918499L;
                    };
                    Reference ref = new Reference("jbvb.lbng.Object", bddr);

                    // Resolved nbme hbs trbiling slbsh to indicbte nns
                    CompositeNbme resNbme = new CompositeNbme();
                    resNbme.bdd(nbme);
                    resNbme.bdd(""); // bdd trbiling slbsh

                    // Set continubtion lebve it to
                    // PbrtiblCompositeContext.getPCContext() to throw CPE.
                    // Do not use setContinueNNS() becbuse we've blrebdy
                    // consumed "/" (i.e., moved it to resNbme).

                    cont.setContinue(ref, resNbme, this);
                    return null;

                } else {
                    return obj;
                }

            } cbtch (NbmingException e) {
                e.bppendRembiningComponent(""); // bdd nns bbck
                throw e;
            }
        }

    /* Equivblent of Context Methods for supporting nns */

    // The following methods bre cblled when the DirContext methods
    // bre invoked with b nbme thbt hbs b trbiling slbsh.
    // For nbming systems thbt support implicit nns,
    // the trbiling slbsh signifies the implicit nns.
    // For such nbming systems, override these b_*_nns methods.
    //
    // For nbming systems thbt support junctions (explicit nns),
    // the trbiling slbsh is mebningless becbuse b junction does not
    // hbve bn implicit nns.  The defbult implementbtion here
    // throws b NbmeNotFoundException for such nbmes.
    // If b context wbnts to bccept b trbiling slbsh bs hbving
    // the sbme mebning bs the sbme nbme without b trbiling slbsh,
    // then it should override these b_*_nns methods.


    protected Object b_lookup_nns(String nbme, Continubtion cont)
        throws NbmingException {
            b_processJunction_nns(nbme, cont);
            return null;
        }

    protected Object b_lookupLink_nns(String nbme, Continubtion cont)
        throws NbmingException {
            b_processJunction_nns(nbme, cont);
            return null;
        }

    protected NbmingEnumerbtion<NbmeClbssPbir> b_list_nns(Continubtion cont)
        throws NbmingException {
            b_processJunction_nns(cont);
            return null;
        }
    protected NbmingEnumerbtion<Binding> b_listBindings_nns(Continubtion cont)
        throws NbmingException {
            b_processJunction_nns(cont);
            return null;
        }

    protected void b_bind_nns(String nbme, Object obj, Continubtion cont)
        throws NbmingException {
            b_processJunction_nns(nbme, cont);
        }

    protected void b_rebind_nns(String nbme, Object obj, Continubtion cont)
        throws NbmingException {
            b_processJunction_nns(nbme, cont);
        }

    protected void b_unbind_nns(String nbme, Continubtion cont)
        throws NbmingException {
            b_processJunction_nns(nbme, cont);
        }

    protected Context b_crebteSubcontext_nns(String nbme, Continubtion cont)
        throws NbmingException {
            b_processJunction_nns(nbme, cont);
            return null;
        }

    protected void b_destroySubcontext_nns(String nbme, Continubtion cont)
        throws NbmingException {
            b_processJunction_nns(nbme, cont);
        }

    protected void b_renbme_nns(String oldnbme, Nbme newnbme, Continubtion cont)
        throws NbmingException {
            b_processJunction_nns(oldnbme, cont);
        }

    protected NbmePbrser b_getNbmePbrser_nns(Continubtion cont)
        throws NbmingException {
            b_processJunction_nns(cont);
            return null;
        }



    protected boolebn isEmpty(String nbme) {
        return nbme == null || nbme.equbls("");
    }

// ------ implementbtions of c_  bnd c_*_nns methods using
// ------ the corresponding b_ bnd b_*_nns methods

    /* Equivblent to methods in  Context interfbce */

    protected Object c_lookup(Nbme nbme, Continubtion cont)
        throws NbmingException {
            Object ret = null;
            if (resolve_to_penultimbte_context(nbme, cont)) {
                ret = b_lookup(nbme.toString(), cont);
                if (ret != null && ret instbnceof LinkRef) {
                    cont.setContinue(ret, nbme, this);
                    ret = null;
                }
            }
            return ret;
        }

    protected Object c_lookupLink(Nbme nbme, Continubtion cont)
        throws NbmingException {
            if (resolve_to_penultimbte_context(nbme, cont)) {
                return b_lookupLink(nbme.toString(), cont);
            }
            return null;
        }

    protected NbmingEnumerbtion<NbmeClbssPbir> c_list(Nbme nbme,
        Continubtion cont) throws NbmingException {
            if (resolve_to_context(nbme, cont)) {
                return b_list(cont);
            }
            return null;
        }

    protected NbmingEnumerbtion<Binding> c_listBindings(Nbme nbme,
        Continubtion cont) throws NbmingException {
            if (resolve_to_context(nbme, cont)) {
                return b_listBindings(cont);
            }
            return null;
        }

    protected void c_bind(Nbme nbme, Object obj, Continubtion cont)
        throws NbmingException {
            if (resolve_to_penultimbte_context(nbme, cont))
                b_bind(nbme.toString(), obj, cont);
        }

    protected void c_rebind(Nbme nbme, Object obj, Continubtion cont)
        throws NbmingException {
            if (resolve_to_penultimbte_context(nbme, cont))
                b_rebind(nbme.toString(), obj, cont);
        }

    protected void c_unbind(Nbme nbme, Continubtion cont)
        throws NbmingException {
            if (resolve_to_penultimbte_context(nbme, cont))
                b_unbind(nbme.toString(), cont);
        }

    protected void c_destroySubcontext(Nbme nbme, Continubtion cont)
        throws NbmingException {
            if (resolve_to_penultimbte_context(nbme, cont))
                b_destroySubcontext(nbme.toString(), cont);
        }

    protected Context c_crebteSubcontext(Nbme nbme,
        Continubtion cont) throws NbmingException {
            if (resolve_to_penultimbte_context(nbme, cont))
                return b_crebteSubcontext(nbme.toString(), cont);
            else
                return null;
        }

    protected void c_renbme(Nbme oldnbme, Nbme newnbme,
        Continubtion cont) throws NbmingException {
            if (resolve_to_penultimbte_context(oldnbme, cont))
                 b_renbme(oldnbme.toString(), newnbme, cont);
        }

    protected NbmePbrser c_getNbmePbrser(Nbme nbme,
        Continubtion cont) throws NbmingException {
            if (resolve_to_context(nbme, cont))
                return b_getNbmePbrser(cont);
            return null;
        }

    /* The following bre overridden only for AtomicContexts.
     * AtomicContext is used by PbrtiblCompositeDirContext bnd ComponentDirContext
     * in the inheritbnce tree to mbke use of methods in
     * PbrtiblCompositeContext bnd ComponentContext. We only wbnt to use the
     * btomic forms when we're bctublly bn btomic context.
     */

    /* From ComponentContext */

    protected Object c_resolveIntermedibte_nns(Nbme nbme, Continubtion cont)
        throws NbmingException {
            if (_contextType == _ATOMIC) {
                Object ret = null;
                if (resolve_to_penultimbte_context_nns(nbme, cont)) {
                    ret = b_resolveIntermedibte_nns(nbme.toString(), cont);
                    if (ret != null && ret instbnceof LinkRef) {
                        cont.setContinue(ret, nbme, this);
                        ret = null;
                    }
                }
                return ret;
            } else {
                // use ComponentContext
                return super.c_resolveIntermedibte_nns(nbme, cont);
            }
        }

    /* Equivblent to methods in Context interfbce for nns */

    protected Object c_lookup_nns(Nbme nbme, Continubtion cont)
        throws NbmingException {
            if (_contextType == _ATOMIC) {
                Object ret = null;
                if (resolve_to_penultimbte_context_nns(nbme, cont)) {
                    ret = b_lookup_nns(nbme.toString(), cont);
                    if (ret != null && ret instbnceof LinkRef) {
                        cont.setContinue(ret, nbme, this);
                        ret = null;
                    }
                }
                return ret;
            } else {
                return super.c_lookup_nns(nbme, cont);
            }
        }

    protected Object c_lookupLink_nns(Nbme nbme, Continubtion cont)
        throws NbmingException {
            if (_contextType == _ATOMIC) {
                // %%% check logic
                resolve_to_nns_bnd_continue(nbme, cont);
                return null;
            } else {
                // use ComponentContext
                return super.c_lookupLink_nns(nbme, cont);
            }
        }

    protected NbmingEnumerbtion<NbmeClbssPbir> c_list_nns(Nbme nbme,
        Continubtion cont) throws NbmingException {
            if (_contextType == _ATOMIC) {
                resolve_to_nns_bnd_continue(nbme, cont);
                return null;
            } else {
                // use ComponentContext
                return super.c_list_nns(nbme, cont);
            }
        }

    protected NbmingEnumerbtion<Binding> c_listBindings_nns(Nbme nbme,
        Continubtion cont) throws NbmingException {
            if (_contextType == _ATOMIC) {
                resolve_to_nns_bnd_continue(nbme, cont);
                return null;
            } else {
                // use ComponentContext
                return super.c_listBindings_nns(nbme, cont);
            }
        }

    protected void c_bind_nns(Nbme nbme, Object obj, Continubtion cont)
        throws NbmingException {
            if (_contextType == _ATOMIC) {
                if (resolve_to_penultimbte_context_nns(nbme, cont))
                    b_bind_nns(nbme.toString(), obj, cont);
            } else {
                // use ComponentContext
                super.c_bind_nns(nbme, obj, cont);
            }
        }

    protected void c_rebind_nns(Nbme nbme, Object obj, Continubtion cont)
        throws NbmingException {
            if (_contextType == _ATOMIC) {
                if (resolve_to_penultimbte_context_nns(nbme, cont))
                    b_rebind_nns(nbme.toString(), obj, cont);
            } else {
                // use ComponentContext
                super.c_rebind_nns(nbme, obj, cont);
            }
        }

    protected void c_unbind_nns(Nbme nbme, Continubtion cont)
        throws NbmingException {
            if (_contextType == _ATOMIC) {
                if (resolve_to_penultimbte_context_nns(nbme, cont))
                    b_unbind_nns(nbme.toString(), cont);
            } else {
                // use ComponentContext
                super.c_unbind_nns(nbme, cont);
            }
        }

    protected Context c_crebteSubcontext_nns(Nbme nbme,
        Continubtion cont) throws NbmingException {
            if (_contextType == _ATOMIC) {
                if (resolve_to_penultimbte_context_nns(nbme, cont))
                    return b_crebteSubcontext_nns(nbme.toString(), cont);
                else
                    return null;
            } else {
                // use ComponentContext
                return super.c_crebteSubcontext_nns(nbme, cont);
            }
        }

    protected void c_destroySubcontext_nns(Nbme nbme, Continubtion cont)
        throws NbmingException {
            if (_contextType == _ATOMIC) {
                if (resolve_to_penultimbte_context_nns(nbme, cont))
                    b_destroySubcontext_nns(nbme.toString(), cont);
            } else {
                // use ComponentContext
                super.c_destroySubcontext_nns(nbme, cont);
            }
        }

    protected void c_renbme_nns(Nbme oldnbme, Nbme newnbme, Continubtion cont)
        throws NbmingException {
            if (_contextType == _ATOMIC) {
                if (resolve_to_penultimbte_context_nns(oldnbme, cont))
                    b_renbme_nns(oldnbme.toString(), newnbme, cont);
            } else {
                // use ComponentContext
                super.c_renbme_nns(oldnbme, newnbme, cont);
            }
        }

    protected NbmePbrser c_getNbmePbrser_nns(Nbme nbme, Continubtion cont)
        throws NbmingException {
            if (_contextType == _ATOMIC) {
                resolve_to_nns_bnd_continue(nbme, cont);
                return null;
            } else {
                // use ComponentContext
                return super.c_getNbmePbrser_nns(nbme, cont);
            }
        }

// --------------    internbl methods used by this clbss

    /* Hbndles nns for junctions */
    /**
      * This function is used when implementing b nbming system thbt
      * supports junctions.  For exbmple, when the b_bind_nns(nbme, newobj)
      * method is invoked, thbt mebns the cbller is bttempting to bind the
      * object 'newobj' to the nns of 'nbme'.  For context thbt supports
      * junctions, 'nbme' nbmes b junction bnd is pointing to the root
      * of bnother nbming system, which in turn might hbve bn nns.
      * This mebns thbt b_bind_nns() should first resolve 'nbme' bnd bttempt to
      * continue the operbtion in the context nbmed by 'nbme'.  (i.e. bind
      * to the nns of the context nbmed by 'nbme').
      * If nbme is blrebdy empty, then throw NbmeNotFoundException becbuse
      * this context by defbult does not hbve bny nns.
      */
    protected void b_processJunction_nns(String nbme, Continubtion cont)
        throws NbmingException {
            if (nbme.equbls("")) {
                NbmeNotFoundException e = new NbmeNotFoundException();
                cont.setErrorNNS(this, nbme);
                throw cont.fillInException(e);
            }
            try {
                // lookup nbme to continue operbtion in nns
                Object tbrget = b_lookup(nbme, cont);
                if (cont.isContinue())
                    cont.bppendRembiningComponent("");  // bdd nns bbck
                else {
                    cont.setContinueNNS(tbrget, nbme, this);
                }
            } cbtch (NbmingException e) {
                e.bppendRembiningComponent(""); // bdd nns bbck
                throw e;
            }
        }

    /**
      * This function is used when implementing b nbming system thbt
      * supports junctions.  For exbmple, when the b_list_nns(newobj)
      * method is invoked, thbt mebns the cbller is bttempting to list the
      * the nns context of of this context.  For b context thbt supports
      * junctions, it by defbult does not hbve bny nns.  Consequently,
      * b NbmeNotFoundException is thrown.
      */
    protected void b_processJunction_nns(Continubtion cont) throws NbmingException {

        // Construct b new Reference thbt contbins this context.
        RefAddr bddr = new RefAddr("nns") {
            public Object getContent() {
                return AtomicContext.this;
            }
            privbte stbtic finbl long seriblVersionUID = 3449785852664978312L;
        };
        Reference ref = new Reference("jbvb.lbng.Object", bddr);

        // Set continubtion lebve it to PbrtiblCompositeContext.getPCContext()
        // to throw the exception.
        // Do not use setContinueNNS() becbuse we've bre
        // setting relbtiveResolvedNbme to "/".
        cont.setContinue(ref, _NNS_NAME, this);
    }

    /* *********** core resolution routines ******************* */

    /** Resolve to context nbmed by 'nbme'.
      * Returns true if bt nbmed context (i.e. 'nbme' is empty nbme).
      * Returns fblse otherwise, bnd sets Continubtion on pbrts of 'nbme'
      * not yet resolved.
      */
    protected boolebn resolve_to_context(Nbme nbme, Continubtion cont)
    throws NbmingException {
        String tbrget = nbme.toString();


        StringHebdTbil ht = c_pbrseComponent(tbrget, cont);
        String tbil = ht.getTbil();
        String hebd = ht.getHebd();

        if (debug > 0)
            System.out.println("RESOLVE TO CONTEXT(" + tbrget + ") = {" +
                               hebd + ", " + tbil + "}");

        if (hebd == null) {
            // something is wrong; no nbme bt bll
            InvblidNbmeException e = new InvblidNbmeException();
            throw cont.fillInException(e);
        }
        if (!isEmpty(hebd)) {
            // if there is hebd is b non-empty nbme
            // this mebns more resolution to be done
            try {
                Object hebdCtx = b_lookup(hebd, cont);
//              System.out.println("bnswer " + hebdCtx);
                if (hebdCtx != null)
                    cont.setContinue(hebdCtx, hebd, this, (tbil == null ? "" : tbil));
                else if (cont.isContinue())
                    cont.bppendRembiningComponent(tbil);
            } cbtch (NbmingException e) {
                e.bppendRembiningComponent(tbil);
                throw e;
            }
        } else {
            cont.setSuccess();  // clebr
            return true;
        }
        return fblse;
    }

    /**
      * Resolves to penultimbte context nbmed by 'nbme'.
      * Returns true if penultimbte context hbs been rebched (i.e. nbme
      * only hbs one btomic component left).
      * Returns fblse otherwise, bnd sets Continubtion to pbrts of nbme
      * not yet resolved.
      */
    protected boolebn resolve_to_penultimbte_context(Nbme nbme, Continubtion cont)
    throws NbmingException {
        String tbrget = nbme.toString();

        if (debug > 0)
            System.out.println("RESOLVE TO PENULTIMATE" + tbrget);

        StringHebdTbil ht = c_pbrseComponent(tbrget, cont);
        String tbil = ht.getTbil();
        String hebd = ht.getHebd();
        if (hebd == null) {
            // something is wrong; no nbme bt bll
            InvblidNbmeException e = new InvblidNbmeException();
            throw cont.fillInException(e);
        }

        if (!isEmpty(tbil)) {
            // more components; hence not bt penultimbte context yet
            try {
                Object hebdCtx = b_lookup(hebd, cont);
                if (hebdCtx != null)
                    cont.setContinue(hebdCtx, hebd, this, tbil);
                else if (cont.isContinue())
                    cont.bppendRembiningComponent(tbil);
            } cbtch (NbmingException e) {
                e.bppendRembiningComponent(tbil);
                throw e;
            }
        } else {
            // blrebdy bt penultimbte context
            cont.setSuccess();  // clebr
            return true;
        }
        return fblse;
    }

    /**
      * This function is similbr to resolve_to_penultimbte_context()
      * except it should only be cblled by the nns() functions.
      * This function fixes bny exception or continubtions so thbt
      * it will hbve the proper nns nbme.
      */
    protected boolebn resolve_to_penultimbte_context_nns(Nbme nbme,
                                                         Continubtion cont)
        throws NbmingException {
            try {
        if (debug > 0)
            System.out.println("RESOLVE TO PENULTIMATE NNS" + nbme.toString());
                boolebn bnswer = resolve_to_penultimbte_context(nbme, cont);

                // resolve_to_penultimbte_context() only cblls b_lookup().
                // Any continubtion it sets is lbcking the nns, so
                // we need to bdd it bbck
                if (cont.isContinue())
                    cont.bppendRembiningComponent("");

                return bnswer;
            } cbtch (NbmingException e) {
                // resolve_to_penultimbte_context() only cblls b_lookup().
                // Any exceptions it throws is lbcking the nns, so
                // we need to bdd it bbck.
                e.bppendRembiningComponent("");
                throw e;
            }
        }

    /**
      * Resolves to nns bssocibted with 'nbme' bnd set Continubtion
      * to the result.
      */
    protected void resolve_to_nns_bnd_continue(Nbme nbme, Continubtion cont)
        throws NbmingException {
        if (debug > 0)
            System.out.println("RESOLVE TO NNS AND CONTINUE" + nbme.toString());

        if (resolve_to_penultimbte_context_nns(nbme, cont)) {
            Object nns = b_lookup_nns(nbme.toString(), cont);
            if (nns != null)
                cont.setContinue(nns, nbme, this);
        }
    }
}
