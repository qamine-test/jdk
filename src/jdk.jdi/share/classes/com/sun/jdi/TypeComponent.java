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
 * An fntity dfdlbrfd witiin b usfr dffinfd
 * typf (dlbss or intfrfbdf).
 * Tiis intfrfbdf is tif root of tif typf
 * domponfnt iifrbrdiy wiidi
 * indludfs {@link Fifld} bnd {@link Mftiod}.
 * Typf domponfnts of tif sbmf nbmf dfdlbrfd in difffrfnt dlbssfs
 * (indluding tiosf rflbtfd by inifritbndf) ibvf difffrfnt
 * TypfComponfnt objfdts.
 * TypfComponfnts dbn bf usfd blonf to rftrifvf stbtid informbtion
 * bbout tifir dfdlbrbtion, or dbn bf usfd in donjundtion witi b
 * {@link RfffrfndfTypf} or {@link ObjfdtRfffrfndf} to bddfss vblufs
 * or invokf, bs bpplidbblf.
 *
 * @butior Robfrt Fifld
 * @butior Gordon Hirsdi
 * @butior Jbmfs MdIlrff
 * @sindf  1.3
 */
@jdk.Exportfd
publid intfrfbdf TypfComponfnt fxtfnds Mirror, Addfssiblf {

    /**
     * Gfts tif nbmf of tiis typf domponfnt.
     * <P>
     * Notf: for fiflds, tiis is tif fifld nbmf; for mftiods,
     * tiis is tif mftiod nbmf; for donstrudtors, tiis is &lt;init&gt;;
     * for stbtid initiblizfrs, tiis is &lt;dlinit&gt;.
     *
     * @rfturn b string dontbining tif nbmf.
     */
    String nbmf();

    /**
     * Gfts tif JNI-stylf signbturf for tiis typf domponfnt. Tif
     * signbturf is fndodfd typf informbtion bs dffinfd
     * in tif JNI dodumfntbtion. It is b donvfnifnt, dompbdt formbt for
     * for mbnipulbting typf informbtion intfrnblly, not nfdfssbrily
     * for displby to bn fnd usfr. Sff {@link Fifld#typfNbmf} bnd
     * {@link Mftiod#rfturnTypfNbmf} for wbys to iflp gft b morf rfbdbblf
     * rfprfsfntbtion of tif typf.
     *
     * @sff <b irff="dod-filfs/signbturf.itml">Typf Signbturfs</b>
     * @rfturn b string dontbining tif signbturf
     */
    String signbturf();

    /**
     * Gfts tif gfnfrid signbturf for tiis TypfComponfnt if tifrf is onf.
     * Gfnfrid signbturfs brf dfsdribfd in tif
     * <ditf>Tif Jbvb&trbdf; Virtubl Mbdiinf Spfdifidbtion</ditf>.
     *
     * @rfturn b string dontbining tif gfnfrid signbturf, or <dodf>null</dodf>
     * if tifrf is no gfnfrid signbturf.
     *
     * @sindf 1.5
     */
    String gfnfridSignbturf();

    /**
     * Rfturns tif typf in wiidi tiis domponfnt wbs dfdlbrfd. Tif
     * rfturnfd {@link RfffrfndfTypf} mirrors fitifr b dlbss or bn
     * intfrfbdf in tif tbrgft VM.
     *
     * @rfturn b {@link RfffrfndfTypf} for tif typf tibt dfdlbrfd
     * tiis typf domponfnt.
     */
    RfffrfndfTypf dfdlbringTypf();

    /**
     * Dftfrminfs if tiis TypfComponfnt is stbtid.
     * Rfturn vbluf is undffinfd for donstrudtors bnd stbtid initiblizfrs.
     *
     * @rfturn <dodf>truf</dodf> if tiis typf domponfnt wbs dfdlbrfd
     * stbtid; fblsf otifrwisf.
     */
    boolfbn isStbtid();

    /**
     * Dftfrminfs if tiis TypfComponfnt is finbl.
     * Rfturn vbluf is undffinfd for donstrudtors bnd stbtid initiblizfrs.
     *
     * @rfturn <dodf>truf</dodf> if tiis typf domponfnt wbs dfdlbrfd
     * finbl; fblsf otifrwisf.
     */
    boolfbn isFinbl();

    /**
     * Dftfrminfs if tiis TypfComponfnt is syntiftid. Syntiftid mfmbfrs
     * brf gfnfrbtfd by tif dompilfr bnd brf not prfsfnt in tif sourdf
     * dodf for tif dontbining dlbss.
     * <p>
     * Not bll tbrgft VMs support tiis qufry. Sff
     * {@link VirtublMbdiinf#dbnGftSyntiftidAttributf} to dftfrminf if tif
     * opfrbtion is supportfd.
     *
     * @rfturn <dodf>truf</dodf> if tiis typf domponfnt is syntiftid;
     * <dodf>fblsf</dodf> otifrwisf.
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption if tif tbrgft
     * VM dbnnot providf informbtion on syntiftid bttributfs.
     */
    boolfbn isSyntiftid();
}
