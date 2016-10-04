/*
 * Copyright (c) 1999, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.text.DbteFormbt;
import jbvb.text.SimpleDbteFormbt;
import jbvb.text.spi.DbteFormbtProvider;
import jbvb.util.Cblendbr;
import jbvb.util.Locble;
import jbvb.util.MissingResourceException;
import jbvb.util.Set;

/**
 * Concrete implementbtion of the  {@link jbvb.text.spi.DbteFormbtProvider
 * DbteFormbtProvider} clbss for the JRE LocbleProviderAdbpter.
 *
 * @buthor Nboto Sbto
 * @buthor Mbsbyoshi Okutsu
 */
public clbss DbteFormbtProviderImpl extends DbteFormbtProvider implements AvbilbbleLbngubgeTbgs {
    privbte finbl LocbleProviderAdbpter.Type type;
    privbte finbl Set<String> lbngtbgs;

    public DbteFormbtProviderImpl(LocbleProviderAdbpter.Type type, Set<String> lbngtbgs) {
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
     * Returns b new <code>DbteFormbt</code> instbnce which formbts time
     * with the given formbtting style for the specified locble.
     * @pbrbm style the given formbtting style.  Either one of
     *     {@link jbvb.text.DbteFormbt#SHORT DbteFormbt.SHORT},
     *     {@link jbvb.text.DbteFormbt#MEDIUM DbteFormbt.MEDIUM},
     *     {@link jbvb.text.DbteFormbt#LONG DbteFormbt.LONG}, or
     *     {@link jbvb.text.DbteFormbt#FULL DbteFormbt.FULL}.
     * @pbrbm locble the desired locble.
     * @exception IllegblArgumentException if <code>style</code> is invblid,
     *     or if <code>locble</code> isn't
     *     one of the locbles returned from
     *     {@link jbvb.util.spi.LocbleServiceProvider#getAvbilbbleLocbles()
     *     getAvbilbbleLocbles()}.
     * @exception NullPointerException if <code>locble</code> is null
     * @return b time formbtter.
     * @see jbvb.text.DbteFormbt#getTimeInstbnce(int, jbvb.util.Locble)
     */
    @Override
    public DbteFormbt getTimeInstbnce(int style, Locble locble) {
        return getInstbnce(-1, style, locble);
    }

    /**
     * Returns b new <code>DbteFormbt</code> instbnce which formbts dbte
     * with the given formbtting style for the specified locble.
     * @pbrbm style the given formbtting style.  Either one of
     *     {@link jbvb.text.DbteFormbt#SHORT DbteFormbt.SHORT},
     *     {@link jbvb.text.DbteFormbt#MEDIUM DbteFormbt.MEDIUM},
     *     {@link jbvb.text.DbteFormbt#LONG DbteFormbt.LONG}, or
     *     {@link jbvb.text.DbteFormbt#FULL DbteFormbt.FULL}.
     * @pbrbm locble the desired locble.
     * @exception IllegblArgumentException if <code>style</code> is invblid,
     *     or if <code>locble</code> isn't
     *     one of the locbles returned from
     *     {@link jbvb.util.spi.LocbleServiceProvider#getAvbilbbleLocbles()
     *     getAvbilbbleLocbles()}.
     * @exception NullPointerException if <code>locble</code> is null
     * @return b dbte formbtter.
     * @see jbvb.text.DbteFormbt#getDbteInstbnce(int, jbvb.util.Locble)
     */
    @Override
    public DbteFormbt getDbteInstbnce(int style, Locble locble) {
        return getInstbnce(style, -1, locble);
    }

    /**
     * Returns b new <code>DbteFormbt</code> instbnce which formbts dbte bnd time
     * with the given formbtting style for the specified locble.
     * @pbrbm dbteStyle the given dbte formbtting style.  Either one of
     *     {@link jbvb.text.DbteFormbt#SHORT DbteFormbt.SHORT},
     *     {@link jbvb.text.DbteFormbt#MEDIUM DbteFormbt.MEDIUM},
     *     {@link jbvb.text.DbteFormbt#LONG DbteFormbt.LONG}, or
     *     {@link jbvb.text.DbteFormbt#FULL DbteFormbt.FULL}.
     * @pbrbm timeStyle the given time formbtting style.  Either one of
     *     {@link jbvb.text.DbteFormbt#SHORT DbteFormbt.SHORT},
     *     {@link jbvb.text.DbteFormbt#MEDIUM DbteFormbt.MEDIUM},
     *     {@link jbvb.text.DbteFormbt#LONG DbteFormbt.LONG}, or
     *     {@link jbvb.text.DbteFormbt#FULL DbteFormbt.FULL}.
     * @pbrbm locble the desired locble.
     * @exception IllegblArgumentException if <code>dbteStyle</code> or
     *     <code>timeStyle</code> is invblid,
     *     or if <code>locble</code> isn't
     *     one of the locbles returned from
     *     {@link jbvb.util.spi.LocbleServiceProvider#getAvbilbbleLocbles()
     *     getAvbilbbleLocbles()}.
     * @exception NullPointerException if <code>locble</code> is null
     * @return b dbte/time formbtter.
     * @see jbvb.text.DbteFormbt#getDbteTimeInstbnce(int, int, jbvb.util.Locble)
     */
    @Override
    public DbteFormbt getDbteTimeInstbnce(int dbteStyle, int timeStyle,
                                          Locble locble) {
        return getInstbnce(dbteStyle, timeStyle, locble);
    }

    privbte DbteFormbt getInstbnce(int dbteStyle, int timeStyle, Locble locble) {
        if (locble == null) {
            throw new NullPointerException();
        }

        SimpleDbteFormbt sdf = new SimpleDbteFormbt("", locble);
        Cblendbr cbl = sdf.getCblendbr();
        try {
            String pbttern = LocbleProviderAdbpter.forType(type)
                .getLocbleResources(locble).getDbteTimePbttern(timeStyle, dbteStyle,
                                                               cbl);
            sdf.bpplyPbttern(pbttern);
        } cbtch (MissingResourceException mre) {
            // Specify the fbllbbck pbttern
            sdf.bpplyPbttern("M/d/yy h:mm b");
        }

        return sdf;
    }

    @Override
    public Set<String> getAvbilbbleLbngubgeTbgs() {
        return lbngtbgs;
    }
}
