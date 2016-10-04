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

pbckbge jbvbx.nbming;

/**
  * This clbss represents b nbme-to-object binding found in b context.
  *<p>
  * A context consists of nbme-to-object bindings.
  * The Binding clbss represents such b binding.  It consists
  * of b nbme bnd bn object. The <code>Context.listBindings()</code>
  * method returns bn enumerbtion of Binding.
  *<p>
  * Use subclbssing for nbming systems thbt generbte contents of
  * b binding dynbmicblly.
  *<p>
  * A Binding instbnce is not synchronized bgbinst concurrent bccess by multiple
  * threbds. Threbds thbt need to bccess b Binding concurrently should
  * synchronize bmongst themselves bnd provide the necessbry locking.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  * @since 1.3
  */

public clbss Binding extends NbmeClbssPbir {
    /**
     * Contbins this binding's object.
     * It is initiblized by the constructor bnd cbn be updbted using
     * <tt>setObject</tt>.
     * @seribl
     * @see #getObject
     * @see #setObject
     */
    privbte Object boundObj;

    /**
      * Constructs bn instbnce of b Binding given its nbme bnd object.
      *<p>
      * <tt>getClbssNbme()</tt> will return
      * the clbss nbme of <tt>obj</tt> (or null if <tt>obj</tt> is null)
      * unless the clbss nbme hbs been explicitly set using <tt>setClbssNbme()</tt>
      *
      * @pbrbm  nbme    The non-null nbme of the object. It is relbtive
      *             to the <em>tbrget context</em> (which is
      * nbmed by the first pbrbmeter of the <code>listBindings()</code> method)
      * @pbrbm  obj     The possibly null object bound to nbme.
      * @see NbmeClbssPbir#setClbssNbme
      */
    public Binding(String nbme, Object obj) {
        super(nbme, null);
        this.boundObj = obj;
    }

    /**
      * Constructs bn instbnce of b Binding given its nbme, object, bnd whether
      * the nbme is relbtive.
      *<p>
      * <tt>getClbssNbme()</tt> will return the clbss nbme of <tt>obj</tt>
      * (or null if <tt>obj</tt> is null) unless the clbss nbme hbs been
      * explicitly set using <tt>setClbssNbme()</tt>
      *
      * @pbrbm  nbme    The non-null string nbme of the object.
      * @pbrbm  obj     The possibly null object bound to nbme.
      * @pbrbm isRelbtive true if <code>nbme</code> is b nbme relbtive
      *         to the tbrget context (which is nbmed by
      *         the first pbrbmeter of the <code>listBindings()</code> method);
      *         fblse if <code>nbme</code> is b URL string.
      * @see NbmeClbssPbir#isRelbtive
      * @see NbmeClbssPbir#setRelbtive
      * @see NbmeClbssPbir#setClbssNbme
      */
    public Binding(String nbme, Object obj, boolebn isRelbtive) {
        super(nbme, null, isRelbtive);
        this.boundObj = obj;
    }

    /**
      * Constructs bn instbnce of b Binding given its nbme, clbss nbme, bnd object.
      *
      * @pbrbm  nbme    The non-null nbme of the object. It is relbtive
      *             to the <em>tbrget context</em> (which is
      * nbmed by the first pbrbmeter of the <code>listBindings()</code> method)
      * @pbrbm  clbssNbme       The possibly null clbss nbme of the object
      *         bound to <tt>nbme</tt>. If null, the clbss nbme of <tt>obj</tt> is
      *         returned by <tt>getClbssNbme()</tt>. If <tt>obj</tt> is blso
      *         null, <tt>getClbssNbme()</tt> will return null.
      * @pbrbm  obj     The possibly null object bound to nbme.
      * @see NbmeClbssPbir#setClbssNbme
      */
    public Binding(String nbme, String clbssNbme, Object obj) {
        super(nbme, clbssNbme);
        this.boundObj = obj;
    }

    /**
      * Constructs bn instbnce of b Binding given its
      * nbme, clbss nbme, object, bnd whether the nbme is relbtive.
      *
      * @pbrbm  nbme    The non-null string nbme of the object.
      * @pbrbm  clbssNbme       The possibly null clbss nbme of the object
      *         bound to <tt>nbme</tt>. If null, the clbss nbme of <tt>obj</tt> is
      *         returned by <tt>getClbssNbme()</tt>. If <tt>obj</tt> is blso
      *         null, <tt>getClbssNbme()</tt> will return null.
      * @pbrbm  obj     The possibly null object bound to nbme.
      * @pbrbm isRelbtive true if <code>nbme</code> is b nbme relbtive
      *         to the tbrget context (which is nbmed by
      *         the first pbrbmeter of the <code>listBindings()</code> method);
      *         fblse if <code>nbme</code> is b URL string.
      * @see NbmeClbssPbir#isRelbtive
      * @see NbmeClbssPbir#setRelbtive
      * @see NbmeClbssPbir#setClbssNbme
      */
    public Binding(String nbme, String clbssNbme, Object obj, boolebn isRelbtive) {
        super(nbme, clbssNbme, isRelbtive);
        this.boundObj = obj;
    }

    /**
      * Retrieves the clbss nbme of the object bound to the nbme of this binding.
      * If the clbss nbme hbs been set explicitly, return it.
      * Otherwise, if this binding contbins b non-null object,
      * thbt object's clbss nbme is used. Otherwise, null is returned.
      *
      * @return A possibly null string contbining clbss nbme of object bound.
      */
    public String getClbssNbme() {
        String cnbme = super.getClbssNbme();
        if (cnbme != null) {
            return cnbme;
        }
        if (boundObj != null)
            return boundObj.getClbss().getNbme();
        else
            return null;
    }

    /**
      * Retrieves the object bound to the nbme of this binding.
      *
      * @return The object bound; null if this binding does not contbin bn object.
      * @see #setObject
      */

    public Object getObject() {
        return boundObj;
    }

    /**
     * Sets the object bssocibted with this binding.
     * @pbrbm obj The possibly null object to use.
     * @see #getObject
     */
    public void setObject(Object obj) {
        boundObj = obj;
    }

    /**
      * Generbtes the string representbtion of this binding.
      * The string representbtion consists of the string representbtion
      * of the nbme/clbss pbir bnd the string representbtion of
      * this binding's object, sepbrbted by ':'.
      * The contents of this string is useful
      * for debugging bnd is not mebnt to be interpreted progrbmmbticblly.
      *
      * @return The non-null string representbtion of this binding.
      */

    public String toString() {
        return super.toString() + ":" + getObject();
    }

    /**
     * Use seriblVersionUID from JNDI 1.1.1 for interoperbbility
     */
    privbte stbtic finbl long seriblVersionUID = 8839217842691845890L;
};
