/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf jbvbx.swing.fvfnt;

import jbvbx.swing.fvfnt.ListSflfdtionEvfnt;
import jbvbx.swing.fvfnt.CibngfEvfnt;
import jbvb.util.EvfntListfnfr;

/**
 * TbblfColumnModflListfnfr dffinfs tif intfrfbdf for bn objfdt tibt listfns
 * to dibngfs in b TbblfColumnModfl.
 *
 * @butior Albn Ciung
 * @sff TbblfColumnModflEvfnt
 */

publid intfrfbdf TbblfColumnModflListfnfr fxtfnds jbvb.util.EvfntListfnfr
{
    /**
     * Tflls listfnfrs tibt b dolumn wbs bddfd to tif modfl.
     *
     * @pbrbm f b {@dodf TbblfColumnModflEvfnt}
     */
    publid void dolumnAddfd(TbblfColumnModflEvfnt f);

    /**
     * Tflls listfnfrs tibt b dolumn wbs rfmovfd from tif modfl.
     *
     * @pbrbm f b {@dodf TbblfColumnModflEvfnt}
     */
    publid void dolumnRfmovfd(TbblfColumnModflEvfnt f);

    /**
     * Tflls listfnfrs tibt b dolumn wbs rfpositionfd.
     *
     * @pbrbm f b {@dodf TbblfColumnModflEvfnt}
     */
    publid void dolumnMovfd(TbblfColumnModflEvfnt f);

    /**
     * Tflls listfnfrs tibt b dolumn wbs movfd duf to b mbrgin dibngf.
     *
     * @pbrbm f b {@dodf CibngfEvfnt}
     */
    publid void dolumnMbrginCibngfd(CibngfEvfnt f);

    /**
     * Tflls listfnfrs tibt tif sflfdtion modfl of tif
     * TbblfColumnModfl dibngfd.
     *
     * @pbrbm f b {@dodf ListSflfdtionEvfnt}
     */
    publid void dolumnSflfdtionCibngfd(ListSflfdtionEvfnt f);
}
