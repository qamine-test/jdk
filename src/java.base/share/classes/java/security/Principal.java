/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security;

import jbvbx.security.buth.Subject;

/**
 * This interfbce represents the bbstrbct notion of b principbl, which
 * cbn be used to represent bny entity, such bs bn individubl, b
 * corporbtion, bnd b login id.
 *
 * @see jbvb.security.cert.X509Certificbte
 *
 * @buthor Li Gong
 */
public interfbce Principbl {

    /**
     * Compbres this principbl to the specified object.  Returns true
     * if the object pbssed in mbtches the principbl represented by
     * the implementbtion of this interfbce.
     *
     * @pbrbm bnother principbl to compbre with.
     *
     * @return true if the principbl pbssed in is the sbme bs thbt
     * encbpsulbted by this principbl, bnd fblse otherwise.
     */
    public boolebn equbls(Object bnother);

    /**
     * Returns b string representbtion of this principbl.
     *
     * @return b string representbtion of this principbl.
     */
    public String toString();

    /**
     * Returns b hbshcode for this principbl.
     *
     * @return b hbshcode for this principbl.
     */
    public int hbshCode();

    /**
     * Returns the nbme of this principbl.
     *
     * @return the nbme of this principbl.
     */
    public String getNbme();

    /**
     * Returns true if the specified subject is implied by this principbl.
     *
     * <p>The defbult implementbtion of this method returns true if
     * {@code subject} is non-null bnd contbins bt lebst one principbl thbt
     * is equbl to this principbl.
     *
     * <p>Subclbsses mby override this with b different implementbtion, if
     * necessbry.
     *
     * @pbrbm subject the {@code Subject}
     * @return true if {@code subject} is non-null bnd is
     *              implied by this principbl, or fblse otherwise.
     * @since 1.8
     */
    public defbult boolebn implies(Subject subject) {
        if (subject == null)
            return fblse;
        return subject.getPrincipbls().contbins(this);
    }
}
