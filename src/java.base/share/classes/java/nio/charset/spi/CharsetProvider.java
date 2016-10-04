/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.nio.chbrset.spi;

import jbvb.nio.chbrset.Chbrset;
import jbvb.util.Iterbtor;


/**
 * Chbrset service-provider clbss.
 *
 * <p> A chbrset provider is b concrete subclbss of this clbss thbt hbs b
 * zero-brgument constructor bnd some number of bssocibted chbrset
 * implementbtion clbsses.  Chbrset providers mby be instblled in bn instbnce
 * of the Jbvb plbtform bs extensions, thbt is, jbr files plbced into bny of
 * the usubl extension directories.  Providers mby blso be mbde bvbilbble by
 * bdding them to the bpplet or bpplicbtion clbss pbth or by some other
 * plbtform-specific mebns.  Chbrset providers bre looked up vib the current
 * threbd's {@link jbvb.lbng.Threbd#getContextClbssLobder() context clbss
 * lobder}.
 *
 * <p> A chbrset provider identifies itself with b provider-configurbtion file
 * nbmed <tt>jbvb.nio.chbrset.spi.ChbrsetProvider</tt> in the resource
 * directory <tt>META-INF/services</tt>.  The file should contbin b list of
 * fully-qublified concrete chbrset-provider clbss nbmes, one per line.  A line
 * is terminbted by bny one of b line feed (<tt>'\n'</tt>), b cbrribge return
 * (<tt>'\r'</tt>), or b cbrribge return followed immedibtely by b line feed.
 * Spbce bnd tbb chbrbcters surrounding ebch nbme, bs well bs blbnk lines, bre
 * ignored.  The comment chbrbcter is <tt>'#'</tt> (<tt>'&#92;u0023'</tt>); on
 * ebch line bll chbrbcters following the first comment chbrbcter bre ignored.
 * The file must be encoded in UTF-8.
 *
 * <p> If b pbrticulbr concrete chbrset provider clbss is nbmed in more thbn
 * one configurbtion file, or is nbmed in the sbme configurbtion file more thbn
 * once, then the duplicbtes will be ignored.  The configurbtion file nbming b
 * pbrticulbr provider need not be in the sbme jbr file or other distribution
 * unit bs the provider itself.  The provider must be bccessible from the sbme
 * clbss lobder thbt wbs initiblly queried to locbte the configurbtion file;
 * this is not necessbrily the clbss lobder thbt lobded the file. </p>
 *
 *
 * @buthor Mbrk Reinhold
 * @buthor JSR-51 Expert Group
 * @since 1.4
 *
 * @see jbvb.nio.chbrset.Chbrset
 */

public bbstrbct clbss ChbrsetProvider {

    privbte stbtic Void checkPermission() {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null)
            sm.checkPermission(new RuntimePermission("chbrsetProvider"));
        return null;
    }
    privbte ChbrsetProvider(Void ignore) { }

    /**
     * Initiblizes b new chbrset provider.
     *
     * @throws  SecurityException
     *          If b security mbnbger hbs been instblled bnd it denies
     *          {@link RuntimePermission}<tt>("chbrsetProvider")</tt>
     */
    protected ChbrsetProvider() {
        this(checkPermission());
    }

    /**
     * Crebtes bn iterbtor thbt iterbtes over the chbrsets supported by this
     * provider.  This method is used in the implementbtion of the {@link
     * jbvb.nio.chbrset.Chbrset#bvbilbbleChbrsets Chbrset.bvbilbbleChbrsets}
     * method.
     *
     * @return  The new iterbtor
     */
    public bbstrbct Iterbtor<Chbrset> chbrsets();

    /**
     * Retrieves b chbrset for the given chbrset nbme.
     *
     * @pbrbm  chbrsetNbme
     *         The nbme of the requested chbrset; mby be either
     *         b cbnonicbl nbme or bn blibs
     *
     * @return  A chbrset object for the nbmed chbrset,
     *          or <tt>null</tt> if the nbmed chbrset
     *          is not supported by this provider
     */
    public bbstrbct Chbrset chbrsetForNbme(String chbrsetNbme);

}
