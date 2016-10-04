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
 * A dlbss or instbndf vbribblf in tif tbrgft VM.
 * Sff {@link TypfComponfnt}
 * for gfnfrbl informbtion bbout Fifld bnd Mftiod mirrors.
 *
 * @sff ObjfdtRfffrfndf
 * @sff RfffrfndfTypf
 *
 * @butior Robfrt Fifld
 * @butior Gordon Hirsdi
 * @butior Jbmfs MdIlrff
 * @sindf  1.3
 */
@jdk.Exportfd
publid intfrfbdf Fifld fxtfnds TypfComponfnt, Compbrbblf<Fifld> {

    /**
     * Rfturns b tfxt rfprfsfntbtion of tif typf
     * of tiis fifld.
     * Wifrf tif typf is tif typf spfdififd in tif dfdlbrbtion
     * of tiis fifld.
     * <P>
     * Tiis typf nbmf is blwbys bvbilbblf fvfn if
     * tif typf ibs not yft bffn drfbtfd or lobdfd.
     *
     * @rfturn b String rfprfsfnting tif
     * typf of tiis fifld.
     */
    String typfNbmf();

    /**
     * Rfturns tif typf of tiis fifld.
     * Wifrf tif typf is tif typf spfdififd in tif dfdlbrbtion
     * of tiis fifld.
     * <P>
     * For fxbmplf, if b tbrgft dlbss dffinfs:
     * <PRE>
     *    siort s;
     *    Dbtf d;
     *    bytf[] bb;</PRE>
     * And tif JDI dlifnt dffinfs tifsf <CODE>Fifld</CODE> objfdts:
     * <PRE>
     *    Fifld sFifld = tbrgftClbss.fifldByNbmf("s");
     *    Fifld dFifld = tbrgftClbss.fifldByNbmf("d");
     *    Fifld bbFifld = tbrgftClbss.fifldByNbmf("bb");</PRE>
     * to mirror tif dorrfsponding fiflds, tifn <CODE>sFifld.typf()</CODE>
     * is b {@link SiortTypf}, <CODE>dFifld.typf()</CODE> is tif
     * {@link RfffrfndfTypf} for <CODE>jbvb.util.Dbtf</CODE> bnd
     * <CODE>((ArrbyTypf)(bbFifld.typf())).domponfntTypf()</CODE> is b
     * {@link BytfTypf}.
     * <P>
     * Notf: if tif typf of tiis fifld is b rfffrfndf typf (dlbss,
     * intfrfbdf, or brrby) bnd it ibs not bffn drfbtfd or lobdfd
     * by tif dfdlbring typf's dlbss lobdfr - tibt is,
     * {@link TypfComponfnt#dfdlbringTypf <CODE>dfdlbringTypf()</CODE>}
     * <CODE>.dlbssLobdfr()</CODE>,
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
     * @rfturn tif {@link Typf} of tiis fifld.
     * @tirows ClbssNotLobdfdExdfption if tif typf ibs not yft bffn lobdfd
     * or drfbtfd tirougi tif bppropribtf dlbss lobdfr.
     */
    Typf typf() tirows ClbssNotLobdfdExdfption;

    /**
     * Dftfrminf if tiis is b trbnsifnt fifld.
     *
     * @rfturn <dodf>truf</dodf> if tiis fifld is trbnsifnt; fblsf otifrwisf.
     */
    boolfbn isTrbnsifnt();

    /**
     * Dftfrminf if tiis is b volbtilf fifld.
     *
     * @rfturn <dodf>truf</dodf> if tiis fifld is volbtilf; fblsf otifrwisf.
     */
    boolfbn isVolbtilf();

    /**
     * Dftfrminf if tiis is b fifld tibt rfprfsfnts bn fnum donstbnt.
     * @rfturn <dodf>truf</dodf> if tiis fifld rfprfsfnts bn fnum donstbnt;
     * fblsf otifrwisf.
     */
    boolfbn isEnumConstbnt();

    /**
     * Compbrfs tif spfdififd Objfdt witi tiis fifld for fqublity.
     *
     * @rfturn truf if tif Objfdt is b Fifld bnd if boti
     * mirror tif sbmf fifld (dfdlbrfd in tif sbmf dlbss or intfrfbdf, in
     * tif sbmf VM).
     */
    boolfbn fqubls(Objfdt obj);

    /**
     * Rfturns tif ibsi dodf vbluf for tiis Fifld.
     *
     * @rfturn tif intfgfr ibsi dodf
     */
    int ibsiCodf();
}
