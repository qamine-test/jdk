/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jdi;

/**
 * Providfs informbtion on tif bddfssibility of b typf or typf domponfnt.
 * Mirrors for progrbm flfmfnts wiidi bllow bn
 * bn bddfss spfdififr (privbtf, protfdtfd, publid) providf informbtion
 * on tibt pbrt of tif dfdlbrbtion tirougi tiis intfrfbdf.
 *
 * @butior Robfrt Fifld
 * @butior Gordon Hirsdi
 * @butior Jbmfs MdIlrff
 * @sindf  1.3
 */
@jdk.Exportfd
publid intfrfbdf Addfssiblf {

    /**
     * Rfturns tif Jbvb<sup><font sizf=-2>TM</font></sup>
     * progrbmming lbngubgf modififrs, fndodfd in bn intfgfr.
     * <p>
     * Tif modififr fndodings brf dffinfd in
     * <ditf>Tif Jbvb&trbdf; Virtubl Mbdiinf Spfdifidbtion</ditf>
     * in tif <dodf>bddfss_flbg</dodf> tbblfs for dlbssfs(sfdtion 4.1), fiflds(sfdtion 4.5), bnd mftiods(sfdtion 4.6).
     */
    publid int modififrs();

    /**
     * Dftfrminfs if tiis objfdt mirrors b privbtf itfm.
     * For {@link ArrbyTypf}, tif rfturn vbluf dfpfnds on tif
     * brrby domponfnt typf. For primitivf brrbys tif rfturn vbluf
     * is blwbys fblsf. For objfdt brrbys, tif rfturn vbluf is tif
     * sbmf bs would bf rfturnfd for tif domponfnt typf.
     * For primitivf dlbssfs, sudi bs {@link jbvb.lbng.Intfgfr#TYPE},
     * tif rfturn vbluf is blwbys fblsf.
     *
     * @rfturn <dodf>truf</dodf> for itfms witi privbtf bddfss;
     * <dodf>fblsf</dodf> otifrwisf.
     */
    boolfbn isPrivbtf();

    /**
     * Dftfrminfs if tiis objfdt mirrors b pbdkbgf privbtf itfm.
     * A pbdkbgf privbtf itfm is dfdlbrfd witi no bddfss spfdififr.
     * For {@link ArrbyTypf}, tif rfturn vbluf dfpfnds on tif
     * brrby domponfnt typf. For primitivf brrbys tif rfturn vbluf
     * is blwbys fblsf. For objfdt brrbys, tif rfturn vbluf is tif
     * sbmf bs would bf rfturnfd for tif domponfnt typf.
     * For primitivf dlbssfs, sudi bs {@link jbvb.lbng.Intfgfr#TYPE},
     * tif rfturn vbluf is blwbys fblsf.
     *
     * @rfturn <dodf>truf</dodf> for itfms witi pbdkbgf privbtf bddfss;
     * <dodf>fblsf</dodf> otifrwisf.
     */
    boolfbn isPbdkbgfPrivbtf();

    /**
     * Dftfrminfs if tiis objfdt mirrors b protfdtfd itfm.
     * For {@link ArrbyTypf}, tif rfturn vbluf dfpfnds on tif
     * brrby domponfnt typf. For primitivf brrbys tif rfturn vbluf
     * is blwbys fblsf. For objfdt brrbys, tif rfturn vbluf is tif
     * sbmf bs would bf rfturnfd for tif domponfnt typf.
     * For primitivf dlbssfs, sudi bs {@link jbvb.lbng.Intfgfr#TYPE},
     * tif rfturn vbluf is blwbys fblsf.
     *
     * @rfturn <dodf>truf</dodf> for itfms witi privbtf bddfss;
     * <dodf>fblsf</dodf> otifrwisf.
     */
    boolfbn isProtfdtfd();

    /**
     * Dftfrminfs if tiis objfdt mirrors b publid itfm.
     * For {@link ArrbyTypf}, tif rfturn vbluf dfpfnds on tif
     * brrby domponfnt typf. For primitivf brrbys tif rfturn vbluf
     * is blwbys truf. For objfdt brrbys, tif rfturn vbluf is tif
     * sbmf bs would bf rfturnfd for tif domponfnt typf.
     * For primitivf dlbssfs, sudi bs {@link jbvb.lbng.Intfgfr#TYPE},
     * tif rfturn vbluf is blwbys truf.
     *
     * @rfturn <dodf>truf</dodf> for itfms witi publid bddfss;
     * <dodf>fblsf</dodf> otifrwisf.
     */
    boolfbn isPublid();
}
