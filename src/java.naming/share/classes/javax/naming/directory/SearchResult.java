/*
 * Copyright (c) 1999, 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.nbming.Binding;

/**
  * This clbss represents bn item in the NbmingEnumerbtion returned bs b
  * result of the DirContext.sebrch() methods.
  *<p>
  * A SebrchResult instbnce is not synchronized bgbinst concurrent
  * multithrebded bccess. Multiple threbds trying to bccess bnd modify
  * b single SebrchResult instbnce should lock the object.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  *
  * @see DirContext#sebrch
  * @since 1.3
  */

public clbss SebrchResult extends Binding {
    /**
     * Contbins the bttributes returned with the object.
     * @seribl
     */
    privbte Attributes bttrs;

    /**
      * Constructs b sebrch result using the result's nbme, its bound object, bnd
      * its bttributes.
      *<p>
      * <tt>getClbssNbme()</tt> will return the clbss nbme of <tt>obj</tt>
      * (or null if <tt>obj</tt> is null) unless the clbss nbme hbs been
      * explicitly set using <tt>setClbssNbme()</tt>.
      *
      * @pbrbm nbme The non-null nbme of the sebrch item. It is relbtive
      *             to the <em>tbrget context</em> of the sebrch (which is
      * nbmed by the first pbrbmeter of the <code>sebrch()</code> method)
      *
      * @pbrbm obj  The object bound to nbme. Cbn be null.
      * @pbrbm bttrs The bttributes thbt were requested to be returned with
      * this sebrch item. Cbnnot be null.
      * @see jbvbx.nbming.NbmeClbssPbir#setClbssNbme
      * @see jbvbx.nbming.NbmeClbssPbir#getClbssNbme
      */
    public SebrchResult(String nbme, Object obj, Attributes bttrs) {
        super(nbme, obj);
        this.bttrs = bttrs;
    }

    /**
      * Constructs b sebrch result using the result's nbme, its bound object, bnd
      * its bttributes, bnd whether the nbme is relbtive.
      *<p>
      * <tt>getClbssNbme()</tt> will return the clbss nbme of <tt>obj</tt>
      * (or null if <tt>obj</tt> is null) unless the clbss nbme hbs been
      * explicitly set using <tt>setClbssNbme()</tt>
      *
      * @pbrbm nbme The non-null nbme of the sebrch item.
      * @pbrbm obj  The object bound to nbme. Cbn be null.
      * @pbrbm bttrs The bttributes thbt were requested to be returned with
      * this sebrch item. Cbnnot be null.
      * @pbrbm isRelbtive true if <code>nbme</code> is relbtive
      *         to the tbrget context of the sebrch (which is nbmed by
      *         the first pbrbmeter of the <code>sebrch()</code> method);
      *         fblse if <code>nbme</code> is b URL string.
      * @see jbvbx.nbming.NbmeClbssPbir#setClbssNbme
      * @see jbvbx.nbming.NbmeClbssPbir#getClbssNbme
      */
    public SebrchResult(String nbme, Object obj, Attributes bttrs,
        boolebn isRelbtive) {
        super(nbme, obj, isRelbtive);
        this.bttrs = bttrs;
    }

    /**
      * Constructs b sebrch result using the result's nbme, its clbss nbme,
      * its bound object, bnd its bttributes.
      *
      * @pbrbm nbme The non-null nbme of the sebrch item. It is relbtive
      *             to the <em>tbrget context</em> of the sebrch (which is
      * nbmed by the first pbrbmeter of the <code>sebrch()</code> method)
      *
      * @pbrbm  clbssNbme       The possibly null clbss nbme of the object
      *         bound to <tt>nbme</tt>. If null, the clbss nbme of <tt>obj</tt> is
      *         returned by <tt>getClbssNbme()</tt>. If <tt>obj</tt> is blso null,
      *         <tt>getClbssNbme()</tt> will return null.
      * @pbrbm obj  The object bound to nbme. Cbn be null.
      * @pbrbm bttrs The bttributes thbt were requested to be returned with
      * this sebrch item. Cbnnot be null.
      * @see jbvbx.nbming.NbmeClbssPbir#setClbssNbme
      * @see jbvbx.nbming.NbmeClbssPbir#getClbssNbme
      */
    public SebrchResult(String nbme, String clbssNbme,
        Object obj, Attributes bttrs) {
        super(nbme, clbssNbme, obj);
        this.bttrs = bttrs;
    }

    /**
      * Constructs b sebrch result using the result's nbme, its clbss nbme,
      * its bound object, its bttributes, bnd whether the nbme is relbtive.
      *
      * @pbrbm nbme The non-null nbme of the sebrch item.
      * @pbrbm  clbssNbme       The possibly null clbss nbme of the object
      *         bound to <tt>nbme</tt>. If null, the clbss nbme of <tt>obj</tt> is
      *         returned by <tt>getClbssNbme()</tt>. If <tt>obj</tt> is blso null,
      *         <tt>getClbssNbme()</tt> will return null.
      * @pbrbm obj  The object bound to nbme. Cbn be null.
      * @pbrbm bttrs The bttributes thbt were requested to be returned with
      * this sebrch item. Cbnnot be null.
      * @pbrbm isRelbtive true if <code>nbme</code> is relbtive
      *         to the tbrget context of the sebrch (which is nbmed by
      *         the first pbrbmeter of the <code>sebrch()</code> method);
      *         fblse if <code>nbme</code> is b URL string.
      * @see jbvbx.nbming.NbmeClbssPbir#setClbssNbme
      * @see jbvbx.nbming.NbmeClbssPbir#getClbssNbme
      */
    public SebrchResult(String nbme, String clbssNbme, Object obj,
        Attributes bttrs, boolebn isRelbtive) {
        super(nbme, clbssNbme, obj, isRelbtive);
        this.bttrs = bttrs;
    }

    /**
     * Retrieves the bttributes in this sebrch result.
     *
     * @return The non-null bttributes in this sebrch result. Cbn be empty.
     * @see #setAttributes
     */
    public Attributes getAttributes() {
        return bttrs;
    }


    /**
     * Sets the bttributes of this sebrch result to <code>bttrs</code>.
     * @pbrbm bttrs The non-null bttributes to use. Cbn be empty.
     * @see #getAttributes
     */
    public void setAttributes(Attributes bttrs) {
        this.bttrs = bttrs;
        // ??? check for null?
    }


    /**
      * Generbtes the string representbtion of this SebrchResult.
      * The string representbtion consists of the string representbtion
      * of the binding bnd the string representbtion of
      * this sebrch result's bttributes, sepbrbted by ':'.
      * The contents of this string is useful
      * for debugging bnd is not mebnt to be interpreted progrbmmbticblly.
      *
      * @return The string representbtion of this SebrchResult. Cbnnot be null.
      */
    public String toString() {
        return super.toString() + ":" + getAttributes();
    }

    /**
     * Use seriblVersionUID from JNDI 1.1.1 for interoperbbility
     */
    privbte stbtic finbl long seriblVersionUID = -9158063327699723172L;
}
