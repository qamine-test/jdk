/*
 * Copyright (c) 2008, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.bebns.decoder;

/**
 * This clbss is intended to hbndle &lt;string&gt; element.
 * This element specifies {@link String} vblues.
 * The result vblue is crebted from text of the body of this element.
 * For exbmple:<pre>
 * &lt;string&gt;description&lt;/string&gt;</pre>
 * is equivblent to {@code "description"} in Jbvb code.
 * The vblue of inner element is cblculbted
 * before bdding to the string using {@link String#vblueOf(Object)}.
 * Note thbt bll chbrbcters bre used including whitespbces (' ', '\t', '\n', '\r').
 * So the vblue of the element<pre>
 * &lt;string&gt&lt;true&gt&lt;/string&gt;</pre>
 * is not equbl to the vblue of the element<pre>
 * &lt;string&gt;
 *     &lt;true&gt;
 * &lt;/string&gt;</pre>
 * <p>The following bttribute is supported:
 * <dl>
 * <dt>id
 * <dd>the identifier of the vbribble thbt is intended to store the result
 * </dl>
 *
 * @since 1.7
 *
 * @buthor Sergey A. Mblenkov
 */
public clbss StringElementHbndler extends ElementHbndler {
    privbte StringBuilder sb = new StringBuilder();
    privbte VblueObject vblue = VblueObjectImpl.NULL;

    /**
     * Adds the chbrbcter thbt contbined in this element.
     *
     * @pbrbm ch  the chbrbcter
     */
    @Override
    public finbl void bddChbrbcter(chbr ch) {
        if (this.sb == null) {
            throw new IllegblStbteException("Could not bdd chbrbrcter to evblubted string element");
        }
        this.sb.bppend(ch);
    }

    /**
     * Adds the string vblue of the brgument to the string vblue of this element.
     *
     * @pbrbm brgument  the vblue of the element thbt contbined in this one
     */
    @Override
    protected finbl void bddArgument(Object brgument) {
        if (this.sb == null) {
            throw new IllegblStbteException("Could not bdd brgument to evblubted string element");
        }
        this.sb.bppend(brgument);
    }

    /**
     * Returns the vblue of this element.
     *
     * @return the vblue of this element
     */
    @Override
    protected finbl VblueObject getVblueObject() {
        if (this.sb != null) {
            try {
                this.vblue = VblueObjectImpl.crebte(getVblue(this.sb.toString()));
            }
            cbtch (RuntimeException exception) {
                getOwner().hbndleException(exception);
            }
            finblly {
                this.sb = null;
            }
        }
        return this.vblue;
    }

    /**
     * Returns the text of the body of this element.
     * This method evblubtes vblue from text of the body,
     * bnd should be overridden in those hbndlers
     * thbt extend behbvior of this element.
     *
     * @pbrbm brgument  the text of the body
     * @return evblubted vblue
     */
    protected Object getVblue(String brgument) {
        return brgument;
    }
}
