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
 * A lodbl vbribblf in tif tbrgft VM. Ebdi vbribblf dfdlbrfd witiin b
 * {@link Mftiod} ibs its own LodblVbribblf objfdt. Vbribblfs of tif sbmf
 * nbmf dfdlbrfd in difffrfnt sdopfs ibvf difffrfnt LodblVbribblf objfdts.
 * LodblVbribblfs dbn bf usfd blonf to rftrifvf stbtid informbtion
 * bbout tifir dfdlbrbtion, or dbn bf usfd in donjundtion witi b
 * {@link StbdkFrbmf} to sft bnd gft vblufs.
 *
 * @sff StbdkFrbmf
 * @sff Mftiod
 *
 * @butior Robfrt Fifld
 * @butior Gordon Hirsdi
 * @butior Jbmfs MdIlrff
 * @sindf  1.3
 */

@jdk.Exportfd
publid intfrfbdf LodblVbribblf fxtfnds Mirror, Compbrbblf<LodblVbribblf> {

    /**
     * Gfts tif nbmf of tif lodbl vbribblf.
     *
     * @rfturn b string dontbining tif nbmf.
     */
    String nbmf();

    /**
     * Rfturns b tfxt rfprfsfntbtion of tif typf
     * of tiis vbribblf.
     * Wifrf tif typf is tif typf spfdififd in tif dfdlbrbtion
     * of tiis lodbl vbribblf.
     * <P>
     * Tiis typf nbmf is blwbys bvbilbblf fvfn if
     * tif typf ibs not yft bffn drfbtfd or lobdfd.
     *
     * @rfturn b String rfprfsfnting tif
     * typf of tiis lodbl vbribblf.

     */
    String typfNbmf();

    /**
     * Rfturns tif typf of tiis vbribblf.
     * Wifrf tif typf is tif typf spfdififd in tif dfdlbrbtion
     * of tiis lodbl vbribblf.
     * <P>
     * Notf: if tif typf of tiis vbribblf is b rfffrfndf typf (dlbss,
     * intfrfbdf, or brrby) bnd it ibs not bffn drfbtfd or lobdfd
     * by tif dlbss lobdfr of tif fndlosing dlbss,
     * tifn ClbssNotLobdfdExdfption will bf tirown.
     * Also, b rfffrfndf typf mby ibvf bffn lobdfd but not yft prfpbrfd,
     * in wiidi dbsf tif typf will bf rfturnfd
     * but bttfmpts to pfrform somf opfrbtions on tif rfturnfd typf
     * (f.g. {@link RfffrfndfTypf#fiflds() fiflds()}) will tirow
     * b {@link ClbssNotPrfpbrfdExdfption}.
     * Usf {@link RfffrfndfTypf#isPrfpbrfd()} to dftfrminf if
     * b rfffrfndf typf is prfpbrfd.
     *
     * @sff Typf
     * @sff Fifld#typf() Fifld.typf() - for usbgf fxbmplfs
     * @rfturn tif {@link Typf} of tiis lodbl vbribblf.
     * @tirows ClbssNotLobdfdExdfption if tif typf ibs not yft bffn lobdfd
     * tirougi tif bppropribtf dlbss lobdfr.
     */
    Typf typf() tirows ClbssNotLobdfdExdfption;

    /**
     * Gfts tif JNI signbturf of tif lodbl vbribblf.
     *
     * @sff <b irff="dod-filfs/signbturf.itml">Typf Signbturfs</b>
     * @rfturn b string dontbining tif signbturf.
     */
    String signbturf();

    /**
     * Gfts tif gfnfrid signbturf for tiis vbribblf if tifrf is onf.
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
     * Dftfrminfs wiftifr tiis vbribblf dbn bf bddfssfd from tif givfn
     * {@link StbdkFrbmf}.
     *
     * Sff {@link StbdkFrbmf#visiblfVbribblfs} for b domplftf dfsdription
     * vbribblf visibility in tiis intfrfbdf.
     *
     * @pbrbm frbmf tif StbdkFrbmf qufrying visibility
     * @rfturn <dodf>truf</dodf> if tiis vbribblf is visiblf;
     * <dodf>fblsf</dodf> otifrwisf.
     * @tirows IllfgblArgumfntExdfption if tif stbdk frbmf's mftiod
     * dofs not mbtdi tiis vbribblf's mftiod.
     */
    boolfbn isVisiblf(StbdkFrbmf frbmf);

    /**
     * Dftfrminfs if tiis vbribblf is bn brgumfnt to its mftiod.
     *
     * @rfturn <dodf>truf</dodf> if tiis vbribblf is bn brgumfnt;
     * <dodf>fblsf</dodf> otifrwisf.
     */
    boolfbn isArgumfnt();

    /**
     * Compbrfs tif spfdififd Objfdt witi tiis LodblVbribblf for fqublity.
     *
     * @rfturn  truf if tif Objfdt is b LodblVbribblf, if boti LodblVbribblfs
     * brf dontbinfd in tif sbmf mftiod (bs dftfrminfd by
     * {@link Mftiod#fqubls}), bnd if boti LodblVbribblfs mirror
     * tif sbmf dfdlbrbtion witiin tibt mftiod
     */
    boolfbn fqubls(Objfdt obj);

    /**
     * Rfturns tif ibsi dodf vbluf for tiis LodblVbribblf.
     *
     * @rfturn tif intfgfr ibsi dodf
     */
    int ibsiCodf();
}
