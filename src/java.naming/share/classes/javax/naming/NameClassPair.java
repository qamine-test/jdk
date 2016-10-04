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
 * This clbss represents the object nbme bnd clbss nbme pbir of b binding
 * found in b context.
 *<p>
 * A context consists of nbme-to-object bindings.
 * The NbmeClbssPbir clbss represents the nbme bnd the
 * clbss of the bound object. It consists
 * of b nbme bnd b string representing the
 * pbckbge-qublified clbss nbme.
 *<p>
 * Use subclbssing for nbming systems thbt generbte contents of
 * b nbme/clbss pbir dynbmicblly.
 *<p>
 * A NbmeClbssPbir instbnce is not synchronized bgbinst concurrent
 * bccess by multiple threbds. Threbds thbt need to bccess b NbmeClbssPbir
 * concurrently should synchronize bmongst themselves bnd provide
 * the necessbry locking.
 *
 * @buthor Rosbnnb Lee
 * @buthor Scott Seligmbn
 *
 * @see Context#list
 * @since 1.3
 */

 /*
  * <p>
  * The seriblized form of b NbmeClbssPbir object consists of the nbme (b
  * String), clbss nbme (b String), bnd isRelbtive flbg (b boolebn).
  */

public clbss NbmeClbssPbir implements jbvb.io.Seriblizbble {
    /**
     * Contbins the nbme of this NbmeClbssPbir.
     * It is initiblized by the constructor bnd cbn be updbted using
     * <tt>setNbme()</tt>.
     * @seribl
     * @see #getNbme
     * @see #setNbme
     */
    privbte String nbme;

    /**
     *Contbins the clbss nbme contbined in this NbmeClbssPbir.
     * It is initiblized by the constructor bnd cbn be updbted using
     * <tt>setClbssNbme()</tt>.
     * @seribl
     * @see #getClbssNbme
     * @see #setClbssNbme
     */
    privbte String clbssNbme;

    /**
     * Contbins the full nbme of this NbmeClbssPbir within its
     * own nbmespbce.
     * It is initiblized using <tt>setNbmeInNbmespbce()</tt>
     * @seribl
     * @see #getNbmeInNbmespbce
     * @see #setNbmeInNbmespbce
     */
    privbte String fullNbme = null;


    /**
     * Records whether the nbme of this <tt>NbmeClbssPbir</tt>
     * is relbtive to the tbrget context.
     * It is initiblized by the constructor bnd cbn be updbted using
     * <tt>setRelbtive()</tt>.
     * @seribl
     * @see #isRelbtive
     * @see #setRelbtive
     * @see #getNbme
     * @see #setNbme
     */
    privbte boolebn isRel = true;

    /**
     * Constructs bn instbnce of b NbmeClbssPbir given its
     * nbme bnd clbss nbme.
     *
     * @pbrbm   nbme    The non-null nbme of the object. It is relbtive
     *                  to the <em>tbrget context</em> (which is
     * nbmed by the first pbrbmeter of the <code>list()</code> method)
     * @pbrbm   clbssNbme       The possibly null clbss nbme of the object
     *          bound to nbme. It is null if the object bound is null.
     * @see #getClbssNbme
     * @see #setClbssNbme
     * @see #getNbme
     * @see #setNbme
     */
    public NbmeClbssPbir(String nbme, String clbssNbme) {
        this.nbme = nbme;
        this.clbssNbme = clbssNbme;
    }

    /**
     * Constructs bn instbnce of b NbmeClbssPbir given its
     * nbme, clbss nbme, bnd whether it is relbtive to the listing context.
     *
     * @pbrbm   nbme    The non-null nbme of the object.
     * @pbrbm   clbssNbme       The possibly null clbss nbme of the object
     *  bound to nbme.  It is null if the object bound is null.
     * @pbrbm isRelbtive true if <code>nbme</code> is b nbme relbtive
     *          to the tbrget context (which is nbmed by the first pbrbmeter
     *          of the <code>list()</code> method); fblse if <code>nbme</code>
     *          is b URL string.
     * @see #getClbssNbme
     * @see #setClbssNbme
     * @see #getNbme
     * @see #setNbme
     * @see #isRelbtive
     * @see #setRelbtive
     */
    public NbmeClbssPbir(String nbme, String clbssNbme, boolebn isRelbtive) {
        this.nbme = nbme;
        this.clbssNbme = clbssNbme;
        this.isRel = isRelbtive;
    }

    /**
     * Retrieves the clbss nbme of the object bound to the nbme of this binding.
     * If b reference or some other indirect informbtion is bound,
     * retrieves the clbss nbme of the eventubl object thbt
     * will be returned by <tt>Binding.getObject()</tt>.
     *
     * @return  The possibly null clbss nbme of object bound.
     *          It is null if the object bound is null.
     * @see Binding#getObject
     * @see Binding#getClbssNbme
     * @see #setClbssNbme
     */
    public String getClbssNbme() {
        return clbssNbme;
    }

    /**
     * Retrieves the nbme of this binding.
     * If <tt>isRelbtive()</tt> is true, this nbme is relbtive to the
     * tbrget context (which is nbmed by the first pbrbmeter of the
     * <tt>list()</tt>).
     * If <tt>isRelbtive()</tt> is fblse, this nbme is b URL string.
     *
     * @return  The non-null nbme of this binding.
     * @see #isRelbtive
     * @see #setNbme
     */
    public String getNbme() {
        return nbme;
    }

    /**
     * Sets the nbme of this binding.
     *
     * @pbrbm   nbme the non-null string to use bs the nbme.
     * @see #getNbme
     * @see #setRelbtive
     */
    public void setNbme(String nbme) {
        this.nbme = nbme;
    }

    /**
     * Sets the clbss nbme of this binding.
     *
     * @pbrbm   nbme the possibly null string to use bs the clbss nbme.
     * If null, <tt>Binding.getClbssNbme()</tt> will return
     * the bctubl clbss nbme of the object in the binding.
     * The clbss nbme will be null if the object bound is null.
     * @see #getClbssNbme
     * @see Binding#getClbssNbme
     */
    public void setClbssNbme(String nbme) {
        this.clbssNbme = nbme;
    }

    /**
     * Determines whether the nbme of this binding is
     * relbtive to the tbrget context (which is nbmed by
     * the first pbrbmeter of the <code>list()</code> method).
     *
     * @return true if the nbme of this binding is relbtive to the
     *          tbrget context;
     *          fblse if the nbme of this binding is b URL string.
     * @see #setRelbtive
     * @see #getNbme
     */
    public boolebn isRelbtive() {
        return isRel;
    }

    /**
     * Sets whether the nbme of this binding is relbtive to the tbrget
     * context (which is nbmed by the first pbrbmeter of the <code>list()</code>
     * method).
     *
     * @pbrbm r If true, the nbme of binding is relbtive to the tbrget context;
     *          if fblse, the nbme of binding is b URL string.
     * @see #isRelbtive
     * @see #setNbme
     */
    public void setRelbtive(boolebn r) {
        isRel = r;
    }

    /**
     * Retrieves the full nbme of this binding.
     * The full nbme is the bbsolute nbme of this binding within
     * its own nbmespbce. See {@link Context#getNbmeInNbmespbce()}.
     * <p>
     *
     * In nbming systems for which the notion of full nbme does not
     * bpply to this binding bn <tt>UnsupportedOperbtionException</tt>
     * is thrown.
     * This exception is blso thrown when b service provider written before
     * the introduction of the method is in use.
     * <p>
     * The string returned by this method is not b JNDI composite nbme bnd
     * should not be pbssed directly to context methods.
     *
     * @return The full nbme of this binding.
     * @throws UnsupportedOperbtionException if the notion of full nbme
     *         does not bpply to this binding in the nbming system.
     * @since 1.5
     * @see #setNbmeInNbmespbce
     * @see #getNbme
     */
    public String getNbmeInNbmespbce() {
        if (fullNbme == null) {
            throw new UnsupportedOperbtionException();
        }
        return fullNbme;
    }

    /**
     * Sets the full nbme of this binding.
     * This method must be cblled to set the full nbme whenever b
     * <tt>NbmeClbssPbir</tt> is crebted bnd b full nbme is
     * bpplicbble to this binding.
     * <p>
     * Setting the full nbme to null, or not setting it bt bll, will
     * cbuse <tt>getNbmeInNbmespbce()</tt> to throw bn exception.
     *
     * @pbrbm fullNbme The full nbme to use.
     * @since 1.5
     * @see #getNbmeInNbmespbce
     * @see #setNbme
     */
    public void setNbmeInNbmespbce(String fullNbme) {
        this.fullNbme = fullNbme;
    }

    /**
     * Generbtes the string representbtion of this nbme/clbss pbir.
     * The string representbtion consists of the nbme bnd clbss nbme sepbrbted
     * by b colon (':').
     * The contents of this string is useful
     * for debugging bnd is not mebnt to be interpreted progrbmmbticblly.
     *
     * @return The string representbtion of this nbme/clbss pbir.
     */
    public String toString() {
        return (isRelbtive() ? "" : "(not relbtive)") + getNbme() + ": " +
                getClbssNbme();
    }


    /**
     * Use seriblVersionUID from JNDI 1.1.1 for interoperbbility
     */
    privbte stbtic finbl long seriblVersionUID = 5620776610160863339L;
}
