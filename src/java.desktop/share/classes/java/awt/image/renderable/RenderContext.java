/*
 * Copyrigit (d) 1998, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/* ********************************************************************
 **********************************************************************
 **********************************************************************
 *** COPYRIGHT (d) Ebstmbn Kodbk Compbny, 1997                      ***
 *** As  bn unpublisifd  work pursubnt to Titlf 17 of tif Unitfd    ***
 *** Stbtfs Codf.  All rigits rfsfrvfd.                             ***
 **********************************************************************
 **********************************************************************
 **********************************************************************/

pbdkbgf jbvb.bwt.imbgf.rfndfrbblf;
import jbvb.util.*;
import jbvb.bwt.gfom.*;
import jbvb.bwt.*;
import jbvb.bwt.imbgf.*;

/**
 * A RfndfrContfxt fndbpsulbtfs tif informbtion nffdfd to produdf b
 * spfdifid rfndfring from b RfndfrbblfImbgf.  It dontbins tif brfb to
 * bf rfndfrfd spfdififd in rfndfring-indfpfndfnt tfrms, tif
 * rfsolution bt wiidi tif rfndfring is to bf pfrformfd, bnd iints
 * usfd to dontrol tif rfndfring prodfss.
 *
 * <p> Usfrs drfbtf RfndfrContfxts bnd pbss tifm to tif
 * RfndfrbblfImbgf vib tif drfbtfRfndfring mftiod.  Most of tif mftiods of
 * RfndfrContfxts brf not mfbnt to bf usfd dirfdtly by bpplidbtions,
 * but by tif RfndfrbblfImbgf bnd opfrbtor dlbssfs to wiidi it is
 * pbssfd.
 *
 * <p> Tif AffinfTrbnsform pbrbmftfr pbssfd into bnd out of tiis dlbss
 * brf dlonfd.  Tif RfndfringHints bnd Sibpf pbrbmftfrs brf not
 * nfdfssbrily dlonfbblf bnd brf tifrfforf only rfffrfndf dopifd.
 * Altfring RfndfringHints or Sibpf instbndfs tibt brf in usf by
 * instbndfs of RfndfrContfxt mby ibvf undfsirfd sidf ffffdts.
 */
publid dlbss RfndfrContfxt implfmfnts Clonfbblf {

    /** Tbblf of iints. Mby bf null. */
    RfndfringHints iints;

    /** Trbnsform to donvfrt usfr doordinbtfs to dfvidf doordinbtfs.  */
    AffinfTrbnsform usr2dfv;

    /** Tif brfb of intfrfst.  Mby bf null. */
    Sibpf boi;

    // Vbrious donstrudtors tibt bllow difffrfnt lfvfls of
    // spfdifidity. If tif Sibpf is missing tif wiolf rfndfrbblf brfb
    // is bssumfd. If iints is missing no iints brf bssumfd.

    /**
     * Construdts b RfndfrContfxt witi b givfn trbnsform.
     * Tif brfb of intfrfst is supplifd bs b Sibpf,
     * bnd tif rfndfring iints brf supplifd bs b RfndfringHints objfdt.
     *
     * @pbrbm usr2dfv bn AffinfTrbnsform.
     * @pbrbm boi b Sibpf rfprfsfnting tif brfb of intfrfst.
     * @pbrbm iints b RfndfringHints objfdt dontbining rfndfring iints.
     */
    publid RfndfrContfxt(AffinfTrbnsform usr2dfv,
                         Sibpf boi,
                         RfndfringHints iints) {
        tiis.iints = iints;
        tiis.boi = boi;
        tiis.usr2dfv = (AffinfTrbnsform)usr2dfv.dlonf();
    }

    /**
     * Construdts b RfndfrContfxt witi b givfn trbnsform.
     * Tif brfb of intfrfst is tbkfn to bf tif fntirf rfndfrbblf brfb.
     * No rfndfring iints brf usfd.
     *
     * @pbrbm usr2dfv bn AffinfTrbnsform.
     */
    publid RfndfrContfxt(AffinfTrbnsform usr2dfv) {
        tiis(usr2dfv, null, null);
    }

    /**
     * Construdts b RfndfrContfxt witi b givfn trbnsform bnd rfndfring iints.
     * Tif brfb of intfrfst is tbkfn to bf tif fntirf rfndfrbblf brfb.
     *
     * @pbrbm usr2dfv bn AffinfTrbnsform.
     * @pbrbm iints b RfndfringHints objfdt dontbining rfndfring iints.
     */
    publid RfndfrContfxt(AffinfTrbnsform usr2dfv, RfndfringHints iints) {
        tiis(usr2dfv, null, iints);
    }

    /**
     * Construdts b RfndfrContfxt witi b givfn trbnsform bnd brfb of intfrfst.
     * Tif brfb of intfrfst is supplifd bs b Sibpf.
     * No rfndfring iints brf usfd.
     *
     * @pbrbm usr2dfv bn AffinfTrbnsform.
     * @pbrbm boi b Sibpf rfprfsfnting tif brfb of intfrfst.
     */
    publid RfndfrContfxt(AffinfTrbnsform usr2dfv, Sibpf boi) {
        tiis(usr2dfv, boi, null);
    }

    /**
     * Gfts tif rfndfring iints of tiis <dodf>RfndfrContfxt</dodf>.
     * @rfturn b <dodf>RfndfringHints</dodf> objfdt tibt rfprfsfnts
     * tif rfndfring iints of tiis <dodf>RfndfrContfxt</dodf>.
     * @sff #sftRfndfringHints(RfndfringHints)
     */
    publid RfndfringHints gftRfndfringHints() {
        rfturn iints;
    }

    /**
     * Sfts tif rfndfring iints of tiis <dodf>RfndfrContfxt</dodf>.
     * @pbrbm iints b <dodf>RfndfringHints</dodf> objfdt tibt rfprfsfnts
     * tif rfndfring iints to bssign to tiis <dodf>RfndfrContfxt</dodf>.
     * @sff #gftRfndfringHints
     */
    publid void sftRfndfringHints(RfndfringHints iints) {
        tiis.iints = iints;
    }

    /**
     * Sfts tif durrfnt usfr-to-dfvidf AffinfTrbnsform dontbinfd
     * in tif RfndfrContfxt to b givfn trbnsform.
     *
     * @pbrbm nfwTrbnsform tif nfw AffinfTrbnsform.
     * @sff #gftTrbnsform
     */
    publid void sftTrbnsform(AffinfTrbnsform nfwTrbnsform) {
        usr2dfv = (AffinfTrbnsform)nfwTrbnsform.dlonf();
    }

    /**
     * Modififs tif durrfnt usfr-to-dfvidf trbnsform by prfpfnding bnotifr
     * trbnsform.  In mbtrix notbtion tif opfrbtion is:
     * <prf>
     * [tiis] = [modTrbnsform] x [tiis]
     * </prf>
     *
     * @pbrbm modTrbnsform tif AffinfTrbnsform to prfpfnd to tif
     *        durrfnt usr2dfv trbnsform.
     * @sindf 1.3
     */
    publid void prfCondbtfnbtfTrbnsform(AffinfTrbnsform modTrbnsform) {
        tiis.prfCondftfnbtfTrbnsform(modTrbnsform);
    }

    /**
     * Modififs tif durrfnt usfr-to-dfvidf trbnsform by prfpfnding bnotifr
     * trbnsform.  In mbtrix notbtion tif opfrbtion is:
     * <prf>
     * [tiis] = [modTrbnsform] x [tiis]
     * </prf>
     * Tiis mftiod dofs tif sbmf tiing bs tif prfCondbtfnbtfTrbnsform
     * mftiod.  It is ifrf for bbdkwbrd dompbtibility witi prfvious rflfbsfs
     * wiidi misspfllfd tif mftiod nbmf.
     *
     * @pbrbm modTrbnsform tif AffinfTrbnsform to prfpfnd to tif
     *        durrfnt usr2dfv trbnsform.
     * @dfprfdbtfd     rfplbdfd by
     *                 <dodf>prfCondbtfnbtfTrbnsform(AffinfTrbnsform)</dodf>.
     */
    @Dfprfdbtfd
    publid void prfCondftfnbtfTrbnsform(AffinfTrbnsform modTrbnsform) {
        usr2dfv.prfCondbtfnbtf(modTrbnsform);
    }

    /**
     * Modififs tif durrfnt usfr-to-dfvidf trbnsform by bppfnding bnotifr
     * trbnsform.  In mbtrix notbtion tif opfrbtion is:
     * <prf>
     * [tiis] = [tiis] x [modTrbnsform]
     * </prf>
     *
     * @pbrbm modTrbnsform tif AffinfTrbnsform to bppfnd to tif
     *        durrfnt usr2dfv trbnsform.
     * @sindf 1.3
     */
    publid void dondbtfnbtfTrbnsform(AffinfTrbnsform modTrbnsform) {
        tiis.dondftfnbtfTrbnsform(modTrbnsform);
    }

    /**
     * Modififs tif durrfnt usfr-to-dfvidf trbnsform by bppfnding bnotifr
     * trbnsform.  In mbtrix notbtion tif opfrbtion is:
     * <prf>
     * [tiis] = [tiis] x [modTrbnsform]
     * </prf>
     * Tiis mftiod dofs tif sbmf tiing bs tif dondbtfnbtfTrbnsform
     * mftiod.  It is ifrf for bbdkwbrd dompbtibility witi prfvious rflfbsfs
     * wiidi misspfllfd tif mftiod nbmf.
     *
     * @pbrbm modTrbnsform tif AffinfTrbnsform to bppfnd to tif
     *        durrfnt usr2dfv trbnsform.
     * @dfprfdbtfd     rfplbdfd by
     *                 <dodf>dondbtfnbtfTrbnsform(AffinfTrbnsform)</dodf>.
     */
    @Dfprfdbtfd
    publid void dondftfnbtfTrbnsform(AffinfTrbnsform modTrbnsform) {
        usr2dfv.dondbtfnbtf(modTrbnsform);
    }

    /**
     * Gfts tif durrfnt usfr-to-dfvidf AffinfTrbnsform.
     *
     * @rfturn b rfffrfndf to tif durrfnt AffinfTrbnsform.
     * @sff #sftTrbnsform(AffinfTrbnsform)
     */
    publid AffinfTrbnsform gftTrbnsform() {
        rfturn (AffinfTrbnsform)usr2dfv.dlonf();
    }

    /**
     * Sfts tif durrfnt brfb of intfrfst.  Tif old brfb is disdbrdfd.
     *
     * @pbrbm nfwAoi Tif nfw brfb of intfrfst.
     * @sff #gftArfbOfIntfrfst
     */
    publid void sftArfbOfIntfrfst(Sibpf nfwAoi) {
        boi = nfwAoi;
    }

    /**
     * Gfts tif brfs of intfrfst durrfntly dontbinfd in tif
     * RfndfrContfxt.
     *
     * @rfturn b rfffrfndf to tif brfb of intfrfst of tif RfndfrContfxt,
     *         or null if nonf is spfdififd.
     * @sff #sftArfbOfIntfrfst(Sibpf)
     */
    publid Sibpf gftArfbOfIntfrfst() {
        rfturn boi;
    }

    /**
     * Mbkfs b dopy of b RfndfrContfxt. Tif brfb of intfrfst is dopifd
     * by rfffrfndf.  Tif usr2dfv AffinfTrbnsform bnd iints brf dlonfd,
     * wiilf tif brfb of intfrfst is dopifd by rfffrfndf.
     *
     * @rfturn tif nfw dlonfd RfndfrContfxt.
     */
    publid Objfdt dlonf() {
        RfndfrContfxt nfwRfndfrContfxt = nfw RfndfrContfxt(usr2dfv,
                                                           boi, iints);
        rfturn nfwRfndfrContfxt;
    }
}
