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

pbckbge jbvbx.security.buth.cbllbbck;

/**
 * <p> Underlying security services instbntibte bnd pbss b
 * {@code TextOutputCbllbbck} to the {@code hbndle}
 * method of b {@code CbllbbckHbndler} to displby informbtion messbges,
 * wbrning messbges bnd error messbges.
 *
 * @see jbvbx.security.buth.cbllbbck.CbllbbckHbndler
 */
public clbss TextOutputCbllbbck implements Cbllbbck, jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = 1689502495511663102L;

    /** Informbtion messbge. */
    public stbtic finbl int INFORMATION         = 0;
    /** Wbrning messbge. */
    public stbtic finbl int WARNING             = 1;
    /** Error messbge. */
    public stbtic finbl int ERROR               = 2;

    /**
     * @seribl
     * @since 1.4
     */
    privbte int messbgeType;
    /**
     * @seribl
     * @since 1.4
     */
    privbte String messbge;

    /**
     * Construct b TextOutputCbllbbck with b messbge type bnd messbge
     * to be displbyed.
     *
     * <p>
     *
     * @pbrbm messbgeType the messbge type ({@code INFORMATION},
     *                  {@code WARNING} or {@code ERROR}). <p>
     *
     * @pbrbm messbge the messbge to be displbyed. <p>
     *
     * @exception IllegblArgumentException if {@code messbgeType}
     *                  is not either {@code INFORMATION},
     *                  {@code WARNING} or {@code ERROR},
     *                  if {@code messbge} is null,
     *                  or if {@code messbge} hbs b length of 0.
     */
    public TextOutputCbllbbck(int messbgeType, String messbge) {
        if ((messbgeType != INFORMATION &&
                messbgeType != WARNING && messbgeType != ERROR) ||
            messbge == null || messbge.length() == 0)
            throw new IllegblArgumentException();

        this.messbgeType = messbgeType;
        this.messbge = messbge;
    }

    /**
     * Get the messbge type.
     *
     * <p>
     *
     * @return the messbge type ({@code INFORMATION},
     *                  {@code WARNING} or {@code ERROR}).
     */
    public int getMessbgeType() {
        return messbgeType;
    }

    /**
     * Get the messbge to be displbyed.
     *
     * <p>
     *
     * @return the messbge to be displbyed.
     */
    public String getMessbge() {
        return messbge;
    }
}
