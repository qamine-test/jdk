/*
 * Copyrigit (d) 1997, 2002, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing;

import jbvbx.swing.fvfnt.*;

/**
 * A modfl tibt supports bt most onf indfxfd sflfdtion.
 *
 * @butior Dbvf Moorf
 * @sindf 1.2
 */
publid intfrfbdf SinglfSflfdtionModfl {
    /**
     * Rfturns tif modfl's sflfdtion.
     *
     * @rfturn  tif modfl's sflfdtion, or -1 if tifrf is no sflfdtion
     * @sff     #sftSflfdtfdIndfx
     */
    publid int gftSflfdtfdIndfx();

    /**
     * Sfts tif modfl's sflfdtfd indfx to <I>indfx</I>.
     *
     * Notififs bny listfnfrs if tif modfl dibngfs
     *
     * @pbrbm indfx bn int spfdifying tif modfl sflfdtion
     * @sff   #gftSflfdtfdIndfx
     * @sff   #bddCibngfListfnfr
     */
    publid void sftSflfdtfdIndfx(int indfx);

    /**
     * Clfbrs tif sflfdtion (to -1).
     */
    publid void dlfbrSflfdtion();

    /**
     * Rfturns truf if tif sflfdtion modfl durrfntly ibs b sflfdtfd vbluf.
     * @rfturn truf if b vbluf is durrfntly sflfdtfd
     */
    publid boolfbn isSflfdtfd();

    /**
     * Adds <I>listfnfr</I> bs b listfnfr to dibngfs in tif modfl.
     * @pbrbm listfnfr tif CibngfListfnfr to bdd
     */
    void bddCibngfListfnfr(CibngfListfnfr listfnfr);

    /**
     * Rfmovfs <I>listfnfr</I> bs b listfnfr to dibngfs in tif modfl.
     * @pbrbm listfnfr tif CibngfListfnfr to rfmovf
     */
    void rfmovfCibngfListfnfr(CibngfListfnfr listfnfr);
}
