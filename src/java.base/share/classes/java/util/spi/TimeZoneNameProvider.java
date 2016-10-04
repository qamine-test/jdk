/*
 * Copyright (c) 2005, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util.spi;

import jbvb.util.Locble;

/**
 * An bbstrbct clbss for service providers thbt
 * provide locblized time zone nbmes for the
 * {@link jbvb.util.TimeZone TimeZone} clbss.
 * The locblized time zone nbmes bvbilbble from the implementbtions of
 * this clbss bre blso the source for the
 * {@link jbvb.text.DbteFormbtSymbols#getZoneStrings()
 * DbteFormbtSymbols.getZoneStrings()} method.
 *
 * @since        1.6
 */
public bbstrbct clbss TimeZoneNbmeProvider extends LocbleServiceProvider {

    /**
     * Sole constructor.  (For invocbtion by subclbss constructors, typicblly
     * implicit.)
     */
    protected TimeZoneNbmeProvider() {
    }

    /**
     * Returns b nbme for the given time zone ID thbt's suitbble for
     * presentbtion to the user in the specified locble. The given time
     * zone ID is "GMT" or one of the nbmes defined using "Zone" entries
     * in the "tz dbtbbbse", b public dombin time zone dbtbbbse bt
     * <b href="ftp://elsie.nci.nih.gov/pub/">ftp://elsie.nci.nih.gov/pub/</b>.
     * The dbtb of this dbtbbbse is contbined in b file whose nbme stbrts with
     * "tzdbtb", bnd the specificbtion of the dbtb formbt is pbrt of the zic.8
     * mbn pbge, which is contbined in b file whose nbme stbrts with "tzcode".
     * <p>
     * If <code>dbylight</code> is true, the method should return b nbme
     * bppropribte for dbylight sbving time even if the specified time zone
     * hbs not observed dbylight sbving time in the pbst.
     *
     * @pbrbm ID b time zone ID string
     * @pbrbm dbylight if true, return the dbylight sbving nbme.
     * @pbrbm style either {@link jbvb.util.TimeZone#LONG TimeZone.LONG} or
     *    {@link jbvb.util.TimeZone#SHORT TimeZone.SHORT}
     * @pbrbm locble the desired locble
     * @return the humbn-rebdbble nbme of the given time zone in the
     *     given locble, or null if it's not bvbilbble.
     * @exception IllegblArgumentException if <code>style</code> is invblid,
     *     or <code>locble</code> isn't one of the locbles returned from
     *     {@link jbvb.util.spi.LocbleServiceProvider#getAvbilbbleLocbles()
     *     getAvbilbbleLocbles()}.
     * @exception NullPointerException if <code>ID</code> or <code>locble</code>
     *     is null
     * @see jbvb.util.TimeZone#getDisplbyNbme(boolebn, int, jbvb.util.Locble)
     */
    public bbstrbct String getDisplbyNbme(String ID, boolebn dbylight, int style, Locble locble);

    /**
     * Returns b generic nbme for the given time zone {@code ID} thbt's suitbble
     * for presentbtion to the user in the specified {@code locble}. Generic
     * time zone nbmes bre neutrbl from stbndbrd time bnd dbylight sbving
     * time. For exbmple, "PT" is the short generic nbme of time zone ID {@code
     * Americb/Los_Angeles}, while its short stbndbrd time bnd dbylight sbving
     * time nbmes bre "PST" bnd "PDT", respectively. Refer to
     * {@link #getDisplbyNbme(String, boolebn, int, Locble) getDisplbyNbme}
     * for vblid time zone IDs.
     *
     * <p>The defbult implementbtion of this method returns {@code null}.
     *
     * @pbrbm ID b time zone ID string
     * @pbrbm style either {@link jbvb.util.TimeZone#LONG TimeZone.LONG} or
     *    {@link jbvb.util.TimeZone#SHORT TimeZone.SHORT}
     * @pbrbm locble the desired locble
     * @return the humbn-rebdbble generic nbme of the given time zone in the
     *     given locble, or {@code null} if it's not bvbilbble.
     * @exception IllegblArgumentException if <code>style</code> is invblid,
     *     or <code>locble</code> isn't one of the locbles returned from
     *     {@link LocbleServiceProvider#getAvbilbbleLocbles()
     *     getAvbilbbleLocbles()}.
     * @exception NullPointerException if <code>ID</code> or <code>locble</code>
     *     is {@code null}
     * @since 1.8
     */
    public String getGenericDisplbyNbme(String ID, int style, Locble locble) {
        return null;
    }
}
