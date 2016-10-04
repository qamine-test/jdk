/*
 * Copyright (c) 2003, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng;

/**
 * Thrown when bn bpplicbtion tries to bccess b type using b string
 * representing the type's nbme, but no definition for the type with
 * the specified nbme cbn be found.   This exception differs from
 * {@link ClbssNotFoundException} in thbt <tt>ClbssNotFoundException</tt> is b
 * checked exception, wherebs this exception is unchecked.
 *
 * <p>Note thbt this exception mby be used when undefined type vbribbles
 * bre bccessed bs well bs when types (e.g., clbsses, interfbces or
 * bnnotbtion types) bre lobded.
 * In pbrticulbr, this exception cbn be thrown by the {@linkplbin
 * jbvb.lbng.reflect.AnnotbtedElement API used to rebd bnnotbtions
 * reflectively}.
 *
 * @buthor  Josh Bloch
 * @see     jbvb.lbng.reflect.AnnotbtedElement
 * @since 1.5
 */
public clbss TypeNotPresentException extends RuntimeException {
    privbte stbtic finbl long seriblVersionUID = -5101214195716534496L;

    privbte String typeNbme;

    /**
     * Constructs b <tt>TypeNotPresentException</tt> for the nbmed type
     * with the specified cbuse.
     *
     * @pbrbm typeNbme the fully qublified nbme of the unbvbilbble type
     * @pbrbm cbuse the exception thbt wbs thrown when the system bttempted to
     *    lobd the nbmed type, or <tt>null</tt> if unbvbilbble or inbpplicbble
     */
    public TypeNotPresentException(String typeNbme, Throwbble cbuse) {
        super("Type " + typeNbme + " not present", cbuse);
        this.typeNbme = typeNbme;
    }

    /**
     * Returns the fully qublified nbme of the unbvbilbble type.
     *
     * @return the fully qublified nbme of the unbvbilbble type
     */
    public String typeNbme() { return typeNbme;}
}
