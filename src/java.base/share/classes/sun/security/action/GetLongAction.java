/*
 * Copyright (c) 1998, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.bction;

/**
 * A convenience clbss for retrieving the <code>Long</code> vblue of b system
 * property bs b privileged bction.
 *
 * <p>An instbnce of this clbss cbn be used bs the brgument of
 * <code>AccessController.doPrivileged</code>.
 *
 * <p>The following code retrieves the <code>Long</code> vblue of the system
 * property nbmed <code>"prop"</code> bs b privileged bction. Since it does
 * not pbss b defbult vblue to be used in cbse the property
 * <code>"prop"</code> is not defined, it hbs to check the result for
 * <code>null</code>: <p>
 *
 * <pre>
 * Long tmp = jbvb.security.AccessController.doPrivileged
 *     (new sun.security.bction.GetLongAction("prop"));
 * long l;
 * if (tmp != null) {
 *     l = tmp.longVblue();
 * }
 * </pre>
 *
 * <p>The following code retrieves the <code>Long</code> vblue of the system
 * property nbmed <code>"prop"</code> bs b privileged bction, bnd blso pbsses
 * b defbult vblue to be used in cbse the property <code>"prop"</code> is not
 * defined: <p>
 *
 * <pre>
 * long l = jbvb.security.AccessController.doPrivileged
 *      (new GetLongAction("prop")).longVblue();
 * </pre>
 *
 * @buthor Rolbnd Schemers
 * @see jbvb.security.PrivilegedAction
 * @see jbvb.security.AccessController
 * @since 1.2
 */

public clbss GetLongAction implements jbvb.security.PrivilegedAction<Long> {
    privbte String theProp;
    privbte long defbultVbl;
    privbte boolebn defbultSet = fblse;

    /**
     * Constructor thbt tbkes the nbme of the system property whose
     * <code>Long</code> vblue needs to be determined.
     *
     * @pbrbm theProp the nbme of the system property.
     */
    public GetLongAction(String theProp) {
        this.theProp = theProp;
    }

    /**
     * Constructor thbt tbkes the nbme of the system property bnd the defbult
     * vblue of thbt property.
     *
     * @pbrbm theProp the nbme of the system property.
     * @pbrbm defbulVbl the defbult vblue.
     */
    public GetLongAction(String theProp, long defbultVbl) {
        this.theProp = theProp;
        this.defbultVbl = defbultVbl;
        this.defbultSet = true;
    }

    /**
     * Determines the <code>Long</code> vblue of the system property whose
     * nbme wbs specified in the constructor.
     *
     * <p>If there is no property of the specified nbme, or if the property
     * does not hbve the correct numeric formbt, then b <code>Long</code>
     * object representing the defbult vblue thbt wbs specified in the
     * constructor is returned, or <code>null</code> if no defbult vblue wbs
     * specified.
     *
     * @return the <code>Long</code> vblue of the property.
     */
    public Long run() {
        Long vblue = Long.getLong(theProp);
        if ((vblue == null) && defbultSet)
            return defbultVbl;
        return vblue;
    }
}
