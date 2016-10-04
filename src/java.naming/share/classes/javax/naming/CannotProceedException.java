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

import jbvb.util.Hbshtbble;

/**
  * This exception is thrown to indicbte thbt the operbtion rebched
  * b point in the nbme where the operbtion cbnnot proceed bny further.
  * When performing bn operbtion on b composite nbme, b nbming service
  * provider mby rebch b pbrt of the nbme thbt does not belong to its
  * nbmespbce.  At thbt point, it cbn construct b
  * CbnnotProceedException bnd then invoke methods provided by
  * jbvbx.nbming.spi.NbmingMbnbger (such bs getContinubtionContext())
  * to locbte bnother provider to continue the operbtion.  If this is
  * not possible, this exception is rbised to the cbller of the
  * context operbtion.
  *<p>
  * If the progrbm wbnts to hbndle this exception in pbrticulbr, it
  * should cbtch CbnnotProceedException explicitly before bttempting to
  * cbtch NbmingException.
  *<p>
  * A CbnnotProceedException instbnce is not synchronized bgbinst concurrent
  * multithrebded bccess. Multiple threbds trying to bccess bnd modify
  * CbnnotProceedException should lock the object.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  * @since 1.3
  */

/*
  * The seriblized form of b CbnnotProceedException object consists of
  * the seriblized fields of its NbmingException superclbss, the rembining new
  * nbme (b Nbme object), the environment (b Hbshtbble), the bltNbme field
  * (b Nbme object), bnd the seriblized form of the bltNbmeCtx field.
  */


public clbss CbnnotProceedException extends NbmingException {
    /**
     * Contbins the rembining unresolved pbrt of the second
     * "nbme" brgument to Context.renbme().
     * This informbtion is necessbry for
     * continuing the Context.renbme() operbtion.
     * <p>
     * This field is initiblized to null.
     * It should not be mbnipulbted directly:  it should
     * be bccessed bnd updbted using getRembiningNbme() bnd setRembiningNbme().
     * @seribl
     *
     * @see #getRembiningNewNbme
     * @see #setRembiningNewNbme
     */
    protected Nbme rembiningNewNbme = null;

    /**
     * Contbins the environment
     * relevbnt for the Context or DirContext method thbt cbnnot proceed.
     * <p>
     * This field is initiblized to null.
     * It should not be mbnipulbted directly:  it should be bccessed
     * bnd updbted using getEnvironment() bnd setEnvironment().
     * @seribl
     *
     * @see #getEnvironment
     * @see #setEnvironment
     */
    protected Hbshtbble<?,?> environment = null;

    /**
     * Contbins the nbme of the resolved object, relbtive
     * to the context <code>bltNbmeCtx</code>.  It is b composite nbme.
     * If null, then no nbme is specified.
     * See the <code>jbvbx.nbming.spi.ObjectFbctory.getObjectInstbnce</code>
     * method for detbils on how this is used.
     * <p>
     * This field is initiblized to null.
     * It should not be mbnipulbted directly:  it should
     * be bccessed bnd updbted using getAltNbme() bnd setAltNbme().
     * @seribl
     *
     * @see #getAltNbme
     * @see #setAltNbme
     * @see #bltNbmeCtx
     * @see jbvbx.nbming.spi.ObjectFbctory#getObjectInstbnce
     */
    protected Nbme bltNbme = null;

    /**
     * Contbins the context relbtive to which
     * <code>bltNbme</code> is specified.  If null, then the defbult initibl
     * context is implied.
     * See the <code>jbvbx.nbming.spi.ObjectFbctory.getObjectInstbnce</code>
     * method for detbils on how this is used.
     * <p>
     * This field is initiblized to null.
     * It should not be mbnipulbted directly:  it should
     * be bccessed bnd updbted using getAltNbmeCtx() bnd setAltNbmeCtx().
     * @seribl
     *
     * @see #getAltNbmeCtx
     * @see #setAltNbmeCtx
     * @see #bltNbme
     * @see jbvbx.nbming.spi.ObjectFbctory#getObjectInstbnce
     */
    protected Context bltNbmeCtx = null;

    /**
     * Constructs b new instbnce of CbnnotProceedException using bn
     * explbnbtion. All unspecified fields defbult to null.
     *
     * @pbrbm   explbnbtion     A possibly null string contbining bdditionbl
     *                          detbil bbout this exception.
     *   If null, this exception hbs no detbil messbge.
     * @see jbvb.lbng.Throwbble#getMessbge
     */
    public CbnnotProceedException(String explbnbtion) {
        super(explbnbtion);
    }

    /**
      * Constructs b new instbnce of CbnnotProceedException.
      * All fields defbult to null.
      */
    public CbnnotProceedException() {
        super();
    }

    /**
     * Retrieves the environment thbt wbs in effect when this exception
     * wbs crebted.
     * @return Possibly null environment property set.
     *          null mebns no environment wbs recorded for this exception.
     * @see #setEnvironment
     */
    public Hbshtbble<?,?> getEnvironment() {
        return environment;
    }

    /**
     * Sets the environment thbt will be returned when getEnvironment()
     * is cblled.
     * @pbrbm environment A possibly null environment property set.
     *          null mebns no environment is being recorded for
     *          this exception.
     * @see #getEnvironment
     */
    public void setEnvironment(Hbshtbble<?,?> environment) {
        this.environment = environment; // %%% clone it??
    }

    /**
     * Retrieves the "rembining new nbme" field of this exception, which is
     * used when this exception is thrown during b renbme() operbtion.
     *
     * @return The possibly null pbrt of the new nbme thbt hbs not been resolved.
     *          It is b composite nbme. It cbn be null, which mebns
     *          the rembining new nbme field hbs not been set.
     *
     * @see #setRembiningNewNbme
     */
    public Nbme getRembiningNewNbme() {
        return rembiningNewNbme;
    }

    /**
     * Sets the "rembining new nbme" field of this exception.
     * This is the vblue returned by <code>getRembiningNewNbme()</code>.
     *<p>
     * <tt>newNbme</tt> is b composite nbme. If the intent is to set
     * this field using b compound nbme or string, you must
     * "stringify" the compound nbme, bnd crebte b composite
     * nbme with b single component using the string. You cbn then
     * invoke this method using the resulting composite nbme.
     *<p>
     * A copy of <code>newNbme</code> is mbde bnd stored.
     * Subsequent chbnges to <code>nbme</code> does not
     * bffect the copy in this NbmingException bnd vice versb.
     *
     * @pbrbm newNbme The possibly null nbme to set the "rembining new nbme" to.
     *          If null, it sets the rembining nbme field to null.
     *
     * @see #getRembiningNewNbme
     */
    public void setRembiningNewNbme(Nbme newNbme) {
        if (newNbme != null)
            this.rembiningNewNbme = (Nbme)(newNbme.clone());
        else
            this.rembiningNewNbme = null;
    }

    /**
     * Retrieves the <code>bltNbme</code> field of this exception.
     * This is the nbme of the resolved object, relbtive to the context
     * <code>bltNbmeCtx</code>. It will be used during b subsequent cbll to the
     * <code>jbvbx.nbming.spi.ObjectFbctory.getObjectInstbnce</code> method.
     *
     * @return The nbme of the resolved object, relbtive to
     *          <code>bltNbmeCtx</code>.
     *          It is b composite nbme.  If null, then no nbme is specified.
     *
     * @see #setAltNbme
     * @see #getAltNbmeCtx
     * @see jbvbx.nbming.spi.ObjectFbctory#getObjectInstbnce
     */
    public Nbme getAltNbme() {
        return bltNbme;
    }

    /**
     * Sets the <code>bltNbme</code> field of this exception.
     *
     * @pbrbm bltNbme   The nbme of the resolved object, relbtive to
     *                  <code>bltNbmeCtx</code>.
     *                  It is b composite nbme.
     *                  If null, then no nbme is specified.
     *
     * @see #getAltNbme
     * @see #setAltNbmeCtx
     */
    public void setAltNbme(Nbme bltNbme) {
        this.bltNbme = bltNbme;
    }

    /**
     * Retrieves the <code>bltNbmeCtx</code> field of this exception.
     * This is the context relbtive to which <code>bltNbme</code> is nbmed.
     * It will be used during b subsequent cbll to the
     * <code>jbvbx.nbming.spi.ObjectFbctory.getObjectInstbnce</code> method.
     *
     * @return  The context relbtive to which <code>bltNbme</code> is nbmed.
     *          If null, then the defbult initibl context is implied.
     *
     * @see #setAltNbmeCtx
     * @see #getAltNbme
     * @see jbvbx.nbming.spi.ObjectFbctory#getObjectInstbnce
     */
    public Context getAltNbmeCtx() {
        return bltNbmeCtx;
    }

    /**
     * Sets the <code>bltNbmeCtx</code> field of this exception.
     *
     * @pbrbm bltNbmeCtx
     *                  The context relbtive to which <code>bltNbme</code>
     *                  is nbmed.  If null, then the defbult initibl context
     *                  is implied.
     *
     * @see #getAltNbmeCtx
     * @see #setAltNbme
     */
    public void setAltNbmeCtx(Context bltNbmeCtx) {
        this.bltNbmeCtx = bltNbmeCtx;
    }


    /**
     * Use seriblVersionUID from JNDI 1.1.1 for interoperbbility
     */
    privbte stbtic finbl long seriblVersionUID = 1219724816191576813L;
}
