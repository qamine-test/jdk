/*
 * Copyright (c) 1999, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * This exception is used to describe problems encountered while resolving links.
 * Additionbl informbtion is bdded to the bbse NbmingException for pinpointing
 * the problem with the link.
 *<p>
 * Anblogously to how NbmingException cbptures nbme resolution informbtion,
 * LinkException cbptures "link"-nbme resolution informbtion pinpointing
 * the problem encountered while resolving b link. All these fields mby
 * be null.
 * <ul>
 * <li> Link Resolved Nbme. Portion of link nbme thbt hbs been resolved.
 * <li> Link Resolved Object. Object to which resolution of link nbme proceeded.
 * <li> Link Rembining Nbme. Portion of link nbme thbt hbs not been resolved.
 * <li> Link Explbnbtion. Detbil explbining why link resolution fbiled.
 *</ul>
 *
  *<p>
  * A LinkException instbnce is not synchronized bgbinst concurrent
  * multithrebded bccess. Multiple threbds trying to bccess bnd modify
  * b single LinkException instbnce should lock the object.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  *
  * @see Context#lookupLink
  * @see LinkRef
  * @since 1.3
  */


  /*<p>
  * The seriblized form of b LinkException object consists of the
  * seriblized fields of its NbmingException superclbss, the link resolved
  * nbme (b Nbme object), the link resolved object, link rembining nbme
  * (b Nbme object), bnd the link explbnbtion String.
*/


public clbss LinkException extends NbmingException {
    /**
     * Contbins the pbrt of the link thbt hbs been successfully resolved.
     * It is b composite nbme bnd cbn be null.
     * This field is initiblized by the constructors.
     * You should bccess bnd mbnipulbte this field
     * through its get bnd set methods.
     * @seribl
     * @see #getLinkResolvedNbme
     * @see #setLinkResolvedNbme
     */
    protected Nbme linkResolvedNbme;

    /**
      * Contbins the object to which resolution of the pbrt of the link wbs successful.
      * Cbn be null. This field is initiblized by the constructors.
      * You should bccess bnd mbnipulbte this field
      * through its get bnd set methods.
      * @seribl
      * @see #getLinkResolvedObj
      * @see #setLinkResolvedObj
      */
    protected Object linkResolvedObj;

    /**
     * Contbins the rembining link nbme thbt hbs not been resolved yet.
     * It is b composite nbme bnd cbn be null.
     * This field is initiblized by the constructors.
     * You should bccess bnd mbnipulbte this field
     * through its get bnd set methods.
     * @seribl
     * @see #getLinkRembiningNbme
     * @see #setLinkRembiningNbme
     */
    protected Nbme linkRembiningNbme;

    /**
     * Contbins the exception of why resolution of the link fbiled.
     * Cbn be null. This field is initiblized by the constructors.
     * You should bccess bnd mbnipulbte this field
     * through its get bnd set methods.
     * @seribl
     * @see #getLinkExplbnbtion
     * @see #setLinkExplbnbtion
     */
    protected String linkExplbnbtion;

    /**
      * Constructs b new instbnce of LinkException with bn explbnbtion.
      * All the other fields bre initiblized to null.
      * @pbrbm  explbnbtion     A possibly null string contbining bdditionbl
      *                         detbil bbout this exception.
      * @see jbvb.lbng.Throwbble#getMessbge
      */
    public LinkException(String explbnbtion) {
        super(explbnbtion);
        linkResolvedNbme = null;
        linkResolvedObj = null;
        linkRembiningNbme = null;
        linkExplbnbtion = null;
    }

    /**
      * Constructs b new instbnce of LinkException.
      * All the non-link-relbted bnd link-relbted fields bre initiblized to null.
      */
    public LinkException() {
        super();
        linkResolvedNbme = null;
        linkResolvedObj = null;
        linkRembiningNbme = null;
        linkExplbnbtion = null;
    }

    /**
     * Retrieves the lebding portion of the link nbme thbt wbs resolved
     * successfully.
     *
     * @return The pbrt of the link nbme thbt wbs resolved successfully.
     *          It is b composite nbme. It cbn be null, which mebns
     *          the link resolved nbme field hbs not been set.
     * @see #getLinkResolvedObj
     * @see #setLinkResolvedNbme
     */
    public Nbme getLinkResolvedNbme() {
        return this.linkResolvedNbme;
    }

    /**
     * Retrieves the rembining unresolved portion of the link nbme.
     * @return The pbrt of the link nbme thbt hbs not been resolved.
     *          It is b composite nbme. It cbn be null, which mebns
     *          the link rembining nbme field hbs not been set.
     * @see #setLinkRembiningNbme
     */
    public Nbme getLinkRembiningNbme() {
        return this.linkRembiningNbme;
    }

    /**
     * Retrieves the object to which resolution wbs successful.
     * This is the object to which the resolved link nbme is bound.
     *
     * @return The possibly null object thbt wbs resolved so fbr.
     * If null, it mebns the link resolved object field hbs not been set.
     * @see #getLinkResolvedNbme
     * @see #setLinkResolvedObj
     */
    public Object getLinkResolvedObj() {
        return this.linkResolvedObj;
    }

    /**
      * Retrieves the explbnbtion bssocibted with the problem encountered
      * when resolving b link.
      *
      * @return The possibly null detbil string explbining more bbout the problem
      * with resolving b link.
      *         If null, it mebns there is no
      *         link detbil messbge for this exception.
      * @see #setLinkExplbnbtion
      */
    public String getLinkExplbnbtion() {
        return this.linkExplbnbtion;
    }

    /**
      * Sets the explbnbtion bssocibted with the problem encountered
      * when resolving b link.
      *
      * @pbrbm msg The possibly null detbil string explbining more bbout the problem
      * with resolving b link. If null, it mebns no detbil will be recorded.
      * @see #getLinkExplbnbtion
      */
    public void setLinkExplbnbtion(String msg) {
        this.linkExplbnbtion = msg;
    }

    /**
     * Sets the resolved link nbme field of this exception.
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
     *
     * @pbrbm nbme The nbme to set resolved link nbme to. This cbn be null.
     *          If null, it sets the link resolved nbme field to null.
     * @see #getLinkResolvedNbme
     */
    public void setLinkResolvedNbme(Nbme nbme) {
        if (nbme != null) {
            this.linkResolvedNbme = (Nbme)(nbme.clone());
        } else {
            this.linkResolvedNbme = null;
        }
    }

    /**
     * Sets the rembining link nbme field of this exception.
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
     * @pbrbm nbme The nbme to set rembining link nbme to. This cbn be null.
     *  If null, it sets the rembining nbme field to null.
     * @see #getLinkRembiningNbme
     */
    public void setLinkRembiningNbme(Nbme nbme) {
        if (nbme != null)
            this.linkRembiningNbme = (Nbme)(nbme.clone());
        else
            this.linkRembiningNbme = null;
    }

    /**
     * Sets the link resolved object field of this exception.
     * This indicbtes the lbst successfully resolved object of link nbme.
     * @pbrbm obj The object to set link resolved object to. This cbn be null.
     *            If null, the link resolved object field is set to null.
     * @see #getLinkResolvedObj
     */
    public void setLinkResolvedObj(Object obj) {
        this.linkResolvedObj = obj;
    }

    /**
     * Generbtes the string representbtion of this exception.
     * This string consists of the NbmingException informbtion plus
     * the link's rembining nbme.
     * This string is used for debugging bnd not mebnt to be interpreted
     * progrbmmbticblly.
     * @return The non-null string representbtion of this link exception.
     */
    public String toString() {
        return super.toString() + "; Link Rembining Nbme: '" +
            this.linkRembiningNbme + "'";
    }

    /**
     * Generbtes the string representbtion of this exception.
     * This string consists of the NbmingException informbtion plus
     * the bdditionbl informbtion of resolving the link.
     * If 'detbil' is true, the string blso contbins informbtion on
     * the link resolved object. If fblse, this method is the sbme
     * bs the form of toString() thbt bccepts no pbrbmeters.
     * This string is used for debugging bnd not mebnt to be interpreted
     * progrbmmbticblly.
     *
     * @pbrbm   detbil  If true, bdd informbtion bbout the link resolved
     *                  object.
     * @return The non-null string representbtion of this link exception.
     */
    public String toString(boolebn detbil) {
        if (!detbil || this.linkResolvedObj == null)
            return this.toString();

        return this.toString() + "; Link Resolved Object: " +
            this.linkResolvedObj;
    }

    /**
     * Use seriblVersionUID from JNDI 1.1.1 for interoperbbility
     */
    privbte stbtic finbl long seriblVersionUID = -7967662604076777712L;
};
