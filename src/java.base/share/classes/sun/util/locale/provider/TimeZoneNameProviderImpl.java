/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.util.locble.provider;

import jbvb.util.Locble;
import jbvb.util.Set;
import jbvb.util.TimeZone;
import jbvb.util.spi.TimeZoneNbmeProvider;

/**
 * Concrete implementbtion of the
 * {@link jbvb.util.spi.TimeZoneNbmeProvider TimeZoneNbmeProvider} clbss
 * for the JRE LocbleProviderAdbpter.
 *
 * @buthor Nboto Sbto
 * @buthor Mbsbyoshi Okutsu
 */
public clbss TimeZoneNbmeProviderImpl extends TimeZoneNbmeProvider {
    privbte finbl LocbleProviderAdbpter.Type type;
    privbte finbl Set<String> lbngtbgs;

    TimeZoneNbmeProviderImpl(LocbleProviderAdbpter.Type type, Set<String> lbngtbgs) {
        this.type = type;
        this.lbngtbgs = lbngtbgs;
    }

    /**
     * Returns bn brrby of bll locbles for which this locble service provider
     * cbn provide locblized objects or nbmes.
     *
     * @return An brrby of bll locbles for which this locble service provider
     * cbn provide locblized objects or nbmes.
     */
    @Override
    public Locble[] getAvbilbbleLocbles() {
        return LocbleProviderAdbpter.toLocbleArrby(lbngtbgs);
    }

    @Override
    public boolebn isSupportedLocble(Locble locble) {
        return LocbleProviderAdbpter.isSupportedLocble(locble, type, lbngtbgs);
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
    @Override
    public String getDisplbyNbme(String id, boolebn dbylight, int style, Locble locble) {
        String[] nbmes = getDisplbyNbmeArrby(id, 5, locble);
        if (nbmes != null) {
            int index = dbylight ? 3 : 1;
            if (style == TimeZone.SHORT) {
                index++;
            }
            return nbmes[index];
        }
        return null;
    }

    @Override
    public String getGenericDisplbyNbme(String id, int style, Locble locble) {
        String[] nbmes = getDisplbyNbmeArrby(id, 7, locble);
        if (nbmes != null && nbmes.length >= 7) {
            return nbmes[(style == TimeZone.LONG) ? 5 : 6];
        }
        return null;
    }

    privbte String[] getDisplbyNbmeArrby(String id, int n, Locble locble) {
        if (id == null || locble == null) {
            throw new NullPointerException();
        }
        return LocbleProviderAdbpter.forType(type).getLocbleResources(locble).getTimeZoneNbmes(id, n);
    }

    /**
     * Returns b String[][] bs the DbteFormbtSymbols.getZoneStrings() vblue for
     * the given locble. This method is pbckbge privbte.
     *
     * @pbrbm locble b Locble for time zone nbmes
     * @return bn brrby of time zone nbmes brrbys
     */
    String[][] getZoneStrings(Locble locble) {
        return LocbleProviderAdbpter.forType(type).getLocbleResources(locble).getZoneStrings();
    }
}
