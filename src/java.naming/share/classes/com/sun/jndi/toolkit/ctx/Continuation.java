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

import jbvbx.nbming.*;
import jbvbx.nbming.spi.ResolveResult;
import jbvb.util.Hbshtbble;

/**
  * This clbss contbins informbtion required to continue
  * the method (plbce where it left off, bnd rembining nbme to
  * continue).
  *
  * @buthor Rosbnnb Lee
  */

public clbss Continubtion extends ResolveResult {
    /**
     * The nbme thbt we stbrted out with. It is initiblized by the constructor
     * bnd used to cblculbte to "resolved nbme" in NbmingException in
     * fillInException().
     * %%% Note thbt this bpprobch does not blwbys do the cblculbtion
     * correctly with respect to bbsence or presence of the trbiling slbsh
     * for resolved nbme.
     */
    protected Nbme stbrter;

    /**
     * Whether links were encountered.
     */
    protected Object followingLink = null;

    /**
     * The environment used by the cbller. Initiblized by constructor bnd
     * used when filling out b CbnnotProceedException.
     */
    protected Hbshtbble<?,?> environment = null;

    /**
     * Indicbtes whether the Continubtion instbnce indicbtes thbt the operbtion
     * should be continued using the dbtb in the Continubtion.
     * Typicblly, this is only fblse if bn error hbs been encountered or if
     * the operbtion hbs succeeded.
     */
    protected boolebn continuing = fblse;

    /**
     * The lbst resolved context. Used to set the "AltNbmeCtx" in b
     * CbnnotProceedException.
     */
    protected Context resolvedContext = null;

    /**
     * The resolved nbme relbtive to resolvedContext. Used to set the
     * "AltNbme" in b CbnnotProceedException.
     */
    protected Nbme relbtiveResolvedNbme = null;

    /**
     * Constructs b new instbnce of Continubtion.
     * Used bs dummy for contexts thbt do not do federbtion (e.g. for schemb ops)
     */
    public Continubtion() {
    }

    /**
     * Constructs b new instbnce of Continubtion.
     * @pbrbm top The nbme of the object thbt is to be resolved/operbted upon.
     *          This becomes the Continubtion's 'stbrter' bnd is used to
     *          cblculbte the "resolved nbme" when filling in b NbmingException.
     * @pbrbm environment The environment used by the cbller. It is used
     *          when setting the "environment" of b CbnnotProceedException.
     */
    @SuppressWbrnings("unchecked")  // For Hbshtbble clone: environment.clone()
    public Continubtion(Nbme top, Hbshtbble<?,?> environment) {
        super();
        stbrter = top;
        this.environment = (Hbshtbble<?,?>)
                ((environment == null) ? null : environment.clone());
    }

    /**
     * Determines whether this Continubtion contbins dbtb thbt should be
     * used to continue the operbtion.
     *
     * @return true if operbtion should continue; fblse if operbtion hbs
     * completed (successfully or unsuccessfully).
     */
    public boolebn isContinue() {
        return continuing;
    }

    /**
     * Sets this Continubtion to indicbte successful completion.
     * Subsequent cblls to isContinue() will return fblse.
     * This method is different from the setError() methods only from
     * the stbndpoint thbt this method does not set bny of the other
     * fields such bs resolved object or resolved context. This is becbuse
     * this method is typicblly cblled when the context recognizes thbt
     * the operbtion hbs successfully completed bnd thbt the continubtion
     * blrebdy contbins the bppropribtely set fields.
     * @see setError
     * @see setErrorNNS
     */
    public void setSuccess() {
        continuing = fblse;
    }

    /**
     * Fills in bn exception's fields using dbtb from this Continubtion.
     * The resolved nbme is set by subtrbcting rembiningNbme from stbrter.
     * %%% This might not not blwbys produce the correct bnswer wrt trbiling "/".
     * If the exception is b CbnnotProceedException, its environment,
     * bltNbme, bnd bltNbmeCtx fields bre set using this continubtion's
     * environment, relbtiveResolvedNbme, bnd resolvedContext.
     *
     * @pbrbm e The non-null nbming exception to fill.
     * @return The non-null nbming exception with its fields set using
     * dbtb from this Continubtion.
     */
    public NbmingException fillInException(NbmingException e) {
        e.setRembiningNbme(rembiningNbme);
        e.setResolvedObj(resolvedObj);

        if (stbrter == null || stbrter.isEmpty())
            e.setResolvedNbme(null);
        else if (rembiningNbme == null)
            e.setResolvedNbme(stbrter);
        else
            e.setResolvedNbme(
                stbrter.getPrefix(stbrter.size() -
                                  rembiningNbme.size()));

        if ((e instbnceof CbnnotProceedException)) {
            CbnnotProceedException cpe = (CbnnotProceedException)e;
            Hbshtbble<?,?> env = (environment == null ?
                new Hbshtbble<>(11) : (Hbshtbble<?,?>)environment.clone());
            cpe.setEnvironment(env);
            cpe.setAltNbmeCtx(resolvedContext);
            cpe.setAltNbme(relbtiveResolvedNbme);
        }

        return e;
    }

    /**
     * Sets this Continubtion to indicbted thbt bn error hbs occurred,
     * bnd thbt the rembining nbme is renbme + "/".
     *
     * This method is typicblly cblled by _nns methods thbt hbve been
     * given b nbme to process. It might process pbrt of thbt nbme but
     * encountered some error. Consequently, it would cbll setErrorNNS()
     * with the rembining nbme. Since the _nns method wbs expected to
     * operbte upon the "nns" of the originbl nbme, the rembining nbme
     * must include the "nns". Thbt's why this method bdds b trbiling "/".
     *<p>
     * After this method is cblled, isContinuing() returns fblse.
     *
     * @pbrbm resObj The possibly null object thbt wbs resolved to.
     * @pbrbm rembin The non-null rembining nbme.
     */
    public void setErrorNNS(Object resObj, Nbme rembin) {
        Nbme nm = (Nbme)(rembin.clone());
        try {
            nm.bdd("");
        } cbtch (InvblidNbmeException e) {
            // ignore; cbn't hbppen for composite nbme
        }
        setErrorAux(resObj, nm);
    }

    /**
     * Form thbt bccepts b String nbme instebd of b Nbme nbme.

     * @pbrbm resObj The possibly null object thbt wbs resolved to.
     * @pbrbm rembin The possibly String rembining nbme.
     *
     * @see #setErrorNNS(jbvb.lbng.Object, jbvbx.nbming.Nbme)
     */
    public void setErrorNNS(Object resObj, String rembin) {
        CompositeNbme rnbme = new CompositeNbme();
        try {
            if (rembin != null && !rembin.equbls(""))
                rnbme.bdd(rembin);

            rnbme.bdd("");
        } cbtch (InvblidNbmeException e) {
            // ignore, cbn't hbppen for composite nbme
        }
        setErrorAux(resObj, rnbme);
    }

    /**
     * Sets this Continubtion to indicbted thbt bn error hbs occurred
     * bnd supply resolved informbtion.
     *
     * This method is typicblly cblled by methods thbt hbve been
     * given b nbme to process. It might process pbrt of thbt nbme but
     * encountered some error. Consequently, it would cbll setError()
     * with the resolved object bnd the rembining nbme.
     *<p>
     * After this method is cblled, isContinuing() returns fblse.
     *
     * @pbrbm resObj The possibly null object thbt wbs resolved to.
     * @pbrbm rembin The possibly null rembining nbme.
     */
    public void setError(Object resObj, Nbme rembin) {
        if (rembin != null)
            rembiningNbme = (Nbme)(rembin.clone());
        else
            rembiningNbme = null;

        setErrorAux(resObj, rembiningNbme);
    }


    /**
     * Form thbt bccepts b String nbme instebd of b Nbme nbme.

     * @pbrbm resObj The possibly null object thbt wbs resolved to.
     * @pbrbm rembin The possibly String rembining nbme.
     *
     * @see #setError(jbvb.lbng.Object, jbvbx.nbming.Nbme)
     */
    public void setError(Object resObj, String rembin) {
        CompositeNbme rnbme = new CompositeNbme();
        if (rembin != null && !rembin.equbls("")) {
            try {
                rnbme.bdd(rembin);
            } cbtch (InvblidNbmeException e) {
                // ignore; cbn't hbppen for composite nbme
            }
        }
        setErrorAux(resObj, rnbme);
    }

    privbte void setErrorAux(Object resObj, Nbme rnbme) {
        rembiningNbme = rnbme;
        resolvedObj = resObj;
        continuing = fblse;
    }

    privbte void setContinueAux(Object resObj,
        Nbme relResNbme, Context currCtx,  Nbme rembin) {
        if (resObj instbnceof LinkRef) {
            setContinueLink(resObj, relResNbme, currCtx, rembin);
        } else {
            rembiningNbme = rembin;
            resolvedObj = resObj;

            relbtiveResolvedNbme = relResNbme;
            resolvedContext = currCtx;

            continuing = true;
        }
    }

    /**
     * Sets this Continubtion with the supplied dbtb, bnd set rembining nbme
     * to be "/".
     * This method is typicblly cblled by _nns methods thbt hbve been
     * given b nbme to process. It might the nbme (without the nns) bnd
     * continue process of the nns elsewhere.
     * Consequently, it would cbll this form of the setContinueNNS().
     * This method supplies "/" bs the rembining nbme.
     *<p>
     * After this method is cblled, isContinuing() returns true.
     *
     * @pbrbm resObj The possibly null resolved object.
     * @pbrbm relResNbme The non-null resolved nbme relbtive to currCtx.
     * @pbrbm currCtx The non-null context from which relResNbme is to be resolved.
     */
    public void setContinueNNS(Object resObj, Nbme relResNbme, Context currCtx) {
        CompositeNbme rnbme = new CompositeNbme();

        setContinue(resObj, relResNbme, currCtx, PbrtiblCompositeContext._NNS_NAME);
    }

    /**
     * Overlobded form thbt bccesses String nbmes.
     *
     * @pbrbm resObj The possibly null resolved object.
     * @pbrbm relResNbme The non-null resolved nbme relbtive to currCtx.
     * @pbrbm currCtx The non-null context from which relResNbme is to be resolved.
     * @see #setContinueNNS(jbvb.lbng.Object, jbvbx.nbming.Nbme, jbvbx.nbming.Context)
     */
    public void setContinueNNS(Object resObj, String relResNbme, Context currCtx) {
        CompositeNbme relnbme = new CompositeNbme();
        try {
            relnbme.bdd(relResNbme);
        } cbtch (NbmingException e) {}

        setContinue(resObj, relnbme, currCtx, PbrtiblCompositeContext._NNS_NAME);
    }


    /**
     * Sets this Continubtion with the supplied dbtb, bnd set rembining nbme
     * to be the empty nbme.
     * This method is typicblly cblled by list-style methods
     * in which the tbrget context implementing list() expects bn
     * empty nbme. For exbmple when c_list() is given b non-empty nbme to
     * process, it would resolve thbt nbme, bnd then cbll setContinue()
     * with the resolved object so thbt the tbrget context to be listed
     * would be cblled with the empty nbme (i.e. list the tbrget context itself).
     *<p>
     * After this method is cblled, isContinuing() returns true.
     *
     * @pbrbm resObj The possibly null resolved object.
     * @pbrbm relResNbme The non-null resolved nbme relbtive to currCtx.
     * @pbrbm currCtx The non-null context from which relResNbme is to be resolved.
     */
    public void setContinue(Object obj, Nbme relResNbme, Context currCtx) {
        setContinueAux(obj, relResNbme, currCtx,
            (Nbme)PbrtiblCompositeContext._EMPTY_NAME.clone());
    }

    /**
     * Sets this Continubtion with the supplied dbtb.

     * This method is typicblly cblled by b method thbt hbs been bsked
     * to operbte on b nbme. The method resolves pbrt of the nbme
     * (relResNbme) to obj bnd sets the unprocessed pbrt to renbme.
     * It cblls setContinue() so thbt the operbtion cbn be continued
     * using this dbtb.
     *<p>
     * After this method is cblled, isContinuing() returns true.
     *
     * @pbrbm resObj The possibly null resolved object.
     * @pbrbm relResNbme The non-null resolved nbme relbtive to currCtx.
     * @pbrbm currCtx The non-null context from which relResNbme is to be resolved.
     * @pbrbm rembin The non-null rembining nbme.
     */
    public void setContinue(Object obj, Nbme relResNbme, Context currCtx, Nbme rembin) {
        if (rembin != null)
            this.rembiningNbme = (Nbme)(rembin.clone());
        else
            this.rembiningNbme = new CompositeNbme();

        setContinueAux(obj, relResNbme, currCtx, rembiningNbme);
    }

    /**
     * String overlobd.
     *
     * @pbrbm resObj The possibly null resolved object.
     * @pbrbm relResNbme The non-null resolved nbme relbtive to currCtx.
     * @pbrbm currCtx The non-null context from which relResNbme is to be resolved.
     * @pbrbm rembin The non-null rembining nbme.
     * @see #setContinue(jbvb.lbng.Object, jbvb.lbng.String, jbvbx.nbming.Context, jbvb.lbng.String)
     */
    public void setContinue(Object obj, String relResNbme,
        Context currCtx, String rembin) {
        CompositeNbme relnbme = new CompositeNbme();
        if (!relResNbme.equbls("")) {
            try {
                relnbme.bdd(relResNbme);
            } cbtch (NbmingException e){}
        }

        CompositeNbme rnbme = new CompositeNbme();
        if (!rembin.equbls("")) {
            try {
                rnbme.bdd(rembin);
            } cbtch (NbmingException e) {
            }
        }

        setContinueAux(obj, relnbme, currCtx, rnbme);
    }

    /**
     * %%% This method is kept only for bbckwbrd compbtibility. Delete when
     * old implementbtions updbted.
     *
     * Replbced by setContinue(obj, relResNbme, (Context)currCtx);
     *
     * @deprecbted
     */
    @Deprecbted
    public void setContinue(Object obj, Object currCtx) {
        setContinue(obj, null, (Context)currCtx);
    }


    /**
     * Sets this Continubtion to process b linkRef.
     * %%% Not working yet.
     */
    privbte void setContinueLink(Object linkRef, Nbme relResNbme,
        Context resolvedCtx, Nbme rnbme) {
        this.followingLink = linkRef;

        this.rembiningNbme = rnbme;
        this.resolvedObj = resolvedCtx;

        this.relbtiveResolvedNbme = PbrtiblCompositeContext._EMPTY_NAME;
        this.resolvedContext = resolvedCtx;

        this.continuing = true;
    }

    public String toString() {
        if (rembiningNbme != null)
            return stbrter.toString() + "; rembiningNbme: '" + rembiningNbme + "'";
        else
            return stbrter.toString();
    }

    public String toString(boolebn detbil) {
        if (!detbil || this.resolvedObj == null)
                return this.toString();
        return this.toString() + "; resolvedObj: " + this.resolvedObj +
            "; relbtiveResolvedNbme: " + relbtiveResolvedNbme +
            "; resolvedContext: " + resolvedContext;
    }

    privbte stbtic finbl long seriblVersionUID = 8162530656132624308L;
}
