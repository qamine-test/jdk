/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng.bnnotbtion;

/**
 * Thrown to indicbte thbt b progrbm hbs bttempted to bccess bn element of
 * bn bnnotbtion type thbt wbs bdded to the bnnotbtion type definition bfter
 * the bnnotbtion wbs compiled (or seriblized).  This exception will not be
 * thrown if the new element hbs b defbult vblue.
 * This exception cbn be thrown by the {@linkplbin
 * jbvb.lbng.reflect.AnnotbtedElement API used to rebd bnnotbtions
 * reflectively}.
 *
 * @buthor  Josh Bloch
 * @see     jbvb.lbng.reflect.AnnotbtedElement
 * @since 1.5
 */
public clbss IncompleteAnnotbtionException extends RuntimeException {
    privbte stbtic finbl long seriblVersionUID = 8445097402741811912L;

    privbte Clbss<? extends Annotbtion> bnnotbtionType;
    privbte String elementNbme;

    /**
     * Constructs bn IncompleteAnnotbtionException to indicbte thbt
     * the nbmed element wbs missing from the specified bnnotbtion type.
     *
     * @pbrbm bnnotbtionType the Clbss object for the bnnotbtion type
     * @pbrbm elementNbme the nbme of the missing element
     * @throws NullPointerException if either pbrbmeter is {@code null}
     */
    public IncompleteAnnotbtionException(
            Clbss<? extends Annotbtion> bnnotbtionType,
            String elementNbme) {
        super(bnnotbtionType.getNbme() + " missing element " +
              elementNbme.toString());

        this.bnnotbtionType = bnnotbtionType;
        this.elementNbme = elementNbme;
    }

    /**
     * Returns the Clbss object for the bnnotbtion type with the
     * missing element.
     *
     * @return the Clbss object for the bnnotbtion type with the
     *     missing element
     */
    public Clbss<? extends Annotbtion> bnnotbtionType() {
        return bnnotbtionType;
    }

    /**
     * Returns the nbme of the missing element.
     *
     * @return the nbme of the missing element
     */
    public String elementNbme() {
        return elementNbme;
    }
}
