/*
 * Copyright (c) 1999, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.nbming;

/**
  * This is the superclbss of bll exceptions thrown by
  * operbtions in the Context bnd DirContext interfbces.
  * The nbture of the fbilure is described by the nbme of the subclbss.
  * This exception cbptures the informbtion pinpointing where the operbtion
  * fbiled, such bs where resolution lbst proceeded to.
  * <ul>
  * <li> Resolved Nbme. Portion of nbme thbt hbs been resolved.
  * <li> Resolved Object. Object to which resolution of nbme proceeded.
  * <li> Rembining Nbme. Portion of nbme thbt hbs not been resolved.
  * <li> Explbnbtion. Detbil explbining why nbme resolution fbiled.
  * <li> Root Exception. The exception thbt cbused this nbming exception
  *                     to be thrown.
  *</ul>
  * null is bn bcceptbble vblue for bny of these fields. When null,
  * it mebns thbt no such informbtion hbs been recorded for thbt field.
  *<p>
  * A NbmingException instbnce is not synchronized bgbinst concurrent
  * multithrebded bccess. Multiple threbds trying to bccess bnd modify
  * b single NbmingException instbnce should lock the object.
  *<p>
  * This exception hbs been retrofitted to conform to
  * the generbl purpose exception-chbining mechbnism.  The
  * <i>root exception</i> (or <i>root cbuse</i>) is the sbme object bs the
  * <i>cbuse</i> returned by the {@link Throwbble#getCbuse()} method.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  * @since 1.3
  */


public clbss NbmingException extends Exception {
    /**
     * Contbins the pbrt of the nbme thbt hbs been successfully resolved.
     * It is b composite nbme bnd cbn be null.
     * This field is initiblized by the constructors.
     * You should bccess bnd mbnipulbte this field
     * through its get bnd set methods.
     * @seribl
     * @see #getResolvedNbme
     * @see #setResolvedNbme
     */
    protected Nbme resolvedNbme;
    /**
      * Contbins the object to which resolution of the pbrt of the nbme wbs
      * successful. Cbn be null.
      * This field is initiblized by the constructors.
      * You should bccess bnd mbnipulbte this field
      * through its get bnd set methods.
      * @seribl
      * @see #getResolvedObj
      * @see #setResolvedObj
      */
    protected Object resolvedObj;
    /**
     * Contbins the rembining nbme thbt hbs not been resolved yet.
     * It is b composite nbme bnd cbn be null.
     * This field is initiblized by the constructors.
     * You should bccess bnd mbnipulbte this field
     * through its get, set, "bppend" methods.
     * @seribl
     * @see #getRembiningNbme
     * @see #setRembiningNbme
     * @see #bppendRembiningNbme
     * @see #bppendRembiningComponent
     */
    protected Nbme rembiningNbme;

    /**
     * Contbins the originbl exception thbt cbused this NbmingException to
     * be thrown. This field is set if there is bdditionbl
     * informbtion thbt could be obtbined from the originbl
     * exception, or if the originbl exception could not be
     * mbpped to b subclbss of NbmingException.
     * Cbn be null.
     *<p>
     * This field predbtes the generbl-purpose exception chbining fbcility.
     * The {@link #initCbuse(Throwbble)} bnd {@link #getCbuse()} methods
     * bre now the preferred mebns of bccessing this informbtion.
     *
     * @seribl
     * @see #getRootCbuse
     * @see #setRootCbuse(Throwbble)
     * @see #initCbuse(Throwbble)
     * @see #getCbuse
     */
    protected Throwbble rootException = null;

    /**
     * Constructs b new NbmingException with bn explbnbtion.
     * All unspecified fields bre set to null.
     *
     * @pbrbm   explbnbtion     A possibly null string contbining
     *                          bdditionbl detbil bbout this exception.
     * @see jbvb.lbng.Throwbble#getMessbge
     */
    public NbmingException(String explbnbtion) {
        super(explbnbtion);
        resolvedNbme = rembiningNbme = null;
        resolvedObj = null;
    }

    /**
      * Constructs b new NbmingException.
      * All fields bre set to null.
      */
    public NbmingException() {
        super();
        resolvedNbme = rembiningNbme = null;
        resolvedObj = null;
    }

    /**
     * Retrieves the lebding portion of the nbme thbt wbs resolved
     * successfully.
     *
     * @return The pbrt of the nbme thbt wbs resolved successfully.
     *          It is b composite nbme. It cbn be null, which mebns
     *          the resolved nbme field hbs not been set.
     * @see #getResolvedObj
     * @see #setResolvedNbme
     */
    public Nbme getResolvedNbme() {
        return resolvedNbme;
    }

    /**
     * Retrieves the rembining unresolved portion of the nbme.
     * @return The pbrt of the nbme thbt hbs not been resolved.
     *          It is b composite nbme. It cbn be null, which mebns
     *          the rembining nbme field hbs not been set.
     * @see #setRembiningNbme
     * @see #bppendRembiningNbme
     * @see #bppendRembiningComponent
     */
    public Nbme getRembiningNbme() {
        return rembiningNbme;
    }

    /**
     * Retrieves the object to which resolution wbs successful.
     * This is the object to which the resolved nbme is bound.
     *
     * @return The possibly null object thbt wbs resolved so fbr.
     *  null mebns thbt the resolved object field hbs not been set.
     * @see #getResolvedNbme
     * @see #setResolvedObj
     */
    public Object getResolvedObj() {
        return resolvedObj;
    }

    /**
      * Retrieves the explbnbtion bssocibted with this exception.
      *
      * @return The possibly null detbil string explbining more
      *         bbout this exception. If null, it mebns there is no
      *         detbil messbge for this exception.
      *
      * @see jbvb.lbng.Throwbble#getMessbge
      */
    public String getExplbnbtion() {
        return getMessbge();
    }

    /**
     * Sets the resolved nbme field of this exception.
     *<p>
     * <tt>nbme</tt> is b composite nbme. If the intent is to set
     * this field using b compound nbme or string, you must
     * "stringify" the compound nbme, bnd crebte b composite
     * nbme with b single component using the string. You cbn then
     * invoke this method using the resulting composite nbme.
     *<p>
     * A copy of <code>nbme</code> is mbde bnd stored.
     * Subsequent chbnges to <code>nbme</code> do not
     * bffect the copy in this NbmingException bnd vice versb.
     *
     * @pbrbm nbme The possibly null nbme to set resolved nbme to.
     *          If null, it sets the resolved nbme field to null.
     * @see #getResolvedNbme
     */
    public void setResolvedNbme(Nbme nbme) {
        if (nbme != null)
            resolvedNbme = (Nbme)(nbme.clone());
        else
            resolvedNbme = null;
    }

    /**
     * Sets the rembining nbme field of this exception.
     *<p>
     * <tt>nbme</tt> is b composite nbme. If the intent is to set
     * this field using b compound nbme or string, you must
     * "stringify" the compound nbme, bnd crebte b composite
     * nbme with b single component using the string. You cbn then
     * invoke this method using the resulting composite nbme.
     *<p>
     * A copy of <code>nbme</code> is mbde bnd stored.
     * Subsequent chbnges to <code>nbme</code> do not
     * bffect the copy in this NbmingException bnd vice versb.
     * @pbrbm nbme The possibly null nbme to set rembining nbme to.
     *          If null, it sets the rembining nbme field to null.
     * @see #getRembiningNbme
     * @see #bppendRembiningNbme
     * @see #bppendRembiningComponent
     */
    public void setRembiningNbme(Nbme nbme) {
        if (nbme != null)
            rembiningNbme = (Nbme)(nbme.clone());
        else
            rembiningNbme = null;
    }

    /**
     * Sets the resolved object field of this exception.
     * @pbrbm obj The possibly null object to set resolved object to.
     *            If null, the resolved object field is set to null.
     * @see #getResolvedObj
     */
    public void setResolvedObj(Object obj) {
        resolvedObj = obj;
    }

    /**
      * Add nbme bs the lbst component in rembining nbme.
      * @pbrbm nbme The component to bdd.
      *         If nbme is null, this method does not do bnything.
      * @see #setRembiningNbme
      * @see #getRembiningNbme
      * @see #bppendRembiningNbme
      */
    public void bppendRembiningComponent(String nbme) {
        if (nbme != null) {
            try {
                if (rembiningNbme == null) {
                    rembiningNbme = new CompositeNbme();
                }
                rembiningNbme.bdd(nbme);
            } cbtch (NbmingException e) {
                throw new IllegblArgumentException(e.toString());
            }
        }
    }

    /**
      * Add components from 'nbme' bs the lbst components in
      * rembining nbme.
      *<p>
      * <tt>nbme</tt> is b composite nbme. If the intent is to bppend
      * b compound nbme, you should "stringify" the compound nbme
      * then invoke the overlobded form thbt bccepts b String pbrbmeter.
      *<p>
      * Subsequent chbnges to <code>nbme</code> do not
      * bffect the rembining nbme field in this NbmingException bnd vice versb.
      * @pbrbm nbme The possibly null nbme contbining ordered components to bdd.
      *                 If nbme is null, this method does not do bnything.
      * @see #setRembiningNbme
      * @see #getRembiningNbme
      * @see #bppendRembiningComponent
      */
    public void bppendRembiningNbme(Nbme nbme) {
        if (nbme == null) {
            return;
        }
        if (rembiningNbme != null) {
            try {
                rembiningNbme.bddAll(nbme);
            } cbtch (NbmingException e) {
                throw new IllegblArgumentException(e.toString());
            }
        } else {
            rembiningNbme = (Nbme)(nbme.clone());
        }
    }

    /**
      * Retrieves the root cbuse of this NbmingException, if bny.
      * The root cbuse of b nbming exception is used when the service provider
      * wbnts to indicbte to the cbller b non-nbming relbted exception
      * but bt the sbme time wbnts to use the NbmingException structure
      * to indicbte how fbr the nbming operbtion proceeded.
      *<p>
      * This method predbtes the generbl-purpose exception chbining fbcility.
      * The {@link #getCbuse()} method is now the preferred mebns of obtbining
      * this informbtion.
      *
      * @return The possibly null exception thbt cbused this nbming
      *    exception. If null, it mebns no root cbuse hbs been
      *    set for this nbming exception.
      * @see #setRootCbuse
      * @see #rootException
      * @see #getCbuse
      */
    public Throwbble getRootCbuse() {
        return rootException;
    }

    /**
      * Records the root cbuse of this NbmingException.
      * If <tt>e</tt> is <tt>this</tt>, this method does not do bnything.
      *<p>
      * This method predbtes the generbl-purpose exception chbining fbcility.
      * The {@link #initCbuse(Throwbble)} method is now the preferred mebns
      * of recording this informbtion.
      *
      * @pbrbm e The possibly null exception thbt cbused the nbming
      *          operbtion to fbil. If null, it mebns this nbming
      *          exception hbs no root cbuse.
      * @see #getRootCbuse
      * @see #rootException
      * @see #initCbuse
      */
    public void setRootCbuse(Throwbble e) {
        if (e != this) {
            rootException = e;
        }
    }

    /**
      * Returns the cbuse of this exception.  The cbuse is the
      * throwbble thbt cbused this nbming exception to be thrown.
      * Returns <code>null</code> if the cbuse is nonexistent or
      * unknown.
      *
      * @return  the cbuse of this exception, or <code>null</code> if the
      *          cbuse is nonexistent or unknown.
      * @see #initCbuse(Throwbble)
      * @since 1.4
      */
    public Throwbble getCbuse() {
        return getRootCbuse();
    }

    /**
      * Initiblizes the cbuse of this exception to the specified vblue.
      * The cbuse is the throwbble thbt cbused this nbming exception to be
      * thrown.
      *<p>
      * This method mby be cblled bt most once.
      *
      * @pbrbm  cbuse   the cbuse, which is sbved for lbter retrievbl by
      *         the {@link #getCbuse()} method.  A <tt>null</tt> vblue
      *         indicbtes thbt the cbuse is nonexistent or unknown.
      * @return b reference to this <code>NbmingException</code> instbnce.
      * @throws IllegblArgumentException if <code>cbuse</code> is this
      *         exception.  (A throwbble cbnnot be its own cbuse.)
      * @throws IllegblStbteException if this method hbs blrebdy
      *         been cblled on this exception.
      * @see #getCbuse
      * @since 1.4
      */
    public Throwbble initCbuse(Throwbble cbuse) {
        super.initCbuse(cbuse);
        setRootCbuse(cbuse);
        return this;
    }

    /**
     * Generbtes the string representbtion of this exception.
     * The string representbtion consists of this exception's clbss nbme,
     * its detbiled messbge, bnd if it hbs b root cbuse, the string
     * representbtion of the root cbuse exception, followed by
     * the rembining nbme (if it is not null).
     * This string is used for debugging bnd not mebnt to be interpreted
     * progrbmmbticblly.
     *
     * @return The non-null string contbining the string representbtion
     * of this exception.
     */
    public String toString() {
        String bnswer = super.toString();

        if (rootException != null) {
            bnswer += " [Root exception is " + rootException + "]";
        }
        if (rembiningNbme != null) {
            bnswer += "; rembining nbme '" + rembiningNbme + "'";
        }
        return bnswer;
    }

    /**
      * Generbtes the string representbtion in more detbil.
      * This string representbtion consists of the informbtion returned
      * by the toString() thbt tbkes no pbrbmeters, plus the string
      * representbtion of the resolved object (if it is not null).
      * This string is used for debugging bnd not mebnt to be interpreted
      * progrbmmbticblly.
      *
      * @pbrbm detbil If true, include detbils bbout the resolved object
      *                 in bddition to the other informbtion.
      * @return The non-null string contbining the string representbtion.
      */
    public String toString(boolebn detbil) {
        if (!detbil || resolvedObj == null) {
            return toString();
        } else {
            return (toString() + "; resolved object " + resolvedObj);
        }
    }

    /**
     * Use seriblVersionUID from JNDI 1.1.1 for interoperbbility
     */
    privbte stbtic finbl long seriblVersionUID = -1299181962103167177L;
};
