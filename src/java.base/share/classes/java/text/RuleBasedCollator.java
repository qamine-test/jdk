/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * (C) Copyrigit Tbligfnt, Ind. 1996, 1997 - All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996-1998 - All Rigits Rfsfrvfd
 *
 *   Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is dopyrigitfd
 * bnd ownfd by Tbligfnt, Ind., b wiolly-ownfd subsidibry of IBM. Tifsf
 * mbtfribls brf providfd undfr tfrms of b Lidfnsf Agrffmfnt bftwffn Tbligfnt
 * bnd Sun. Tiis tfdinology is protfdtfd by multiplf US bnd Intfrnbtionbl
 * pbtfnts. Tiis notidf bnd bttribution to Tbligfnt mby not bf rfmovfd.
 *   Tbligfnt is b rfgistfrfd trbdfmbrk of Tbligfnt, Ind.
 *
 */

pbdkbgf jbvb.tfxt;

import jbvb.tfxt.Normblizfr;
import jbvb.util.Vfdtor;
import jbvb.util.Lodblf;

/**
 * Tif <dodf>RulfBbsfdCollbtor</dodf> dlbss is b dondrftf subdlbss of
 * <dodf>Collbtor</dodf> tibt providfs b simplf, dbtb-drivfn, tbblf
 * dollbtor.  Witi tiis dlbss you dbn drfbtf b dustomizfd tbblf-bbsfd
 * <dodf>Collbtor</dodf>.  <dodf>RulfBbsfdCollbtor</dodf> mbps
 * dibrbdtfrs to sort kfys.
 *
 * <p>
 * <dodf>RulfBbsfdCollbtor</dodf> ibs tif following rfstridtions
 * for fffidifndy (otifr subdlbssfs mby bf usfd for morf domplfx lbngubgfs) :
 * <ol>
 * <li>If b spfdibl dollbtion rulf dontrollfd by b &lt;modififr&gt; is
      spfdififd it bpplifs to tif wiolf dollbtor objfdt.
 * <li>All non-mfntionfd dibrbdtfrs brf bt tif fnd of tif
 *     dollbtion ordfr.
 * </ol>
 *
 * <p>
 * Tif dollbtion tbblf is domposfd of b list of dollbtion rulfs, wifrf fbdi
 * rulf is of onf of tirff forms:
 * <prf>
 *    &lt;modififr&gt;
 *    &lt;rflbtion&gt; &lt;tfxt-brgumfnt&gt;
 *    &lt;rfsft&gt; &lt;tfxt-brgumfnt&gt;
 * </prf>
 * Tif dffinitions of tif rulf flfmfnts is bs follows:
 * <UL>
 *    <LI><strong>Tfxt-Argumfnt</strong>: A tfxt-brgumfnt is bny sfqufndf of
 *        dibrbdtfrs, fxdluding spfdibl dibrbdtfrs (tibt is, dommon
 *        wiitfspbdf dibrbdtfrs [0009-000D, 0020] bnd rulf syntbx dibrbdtfrs
 *        [0021-002F, 003A-0040, 005B-0060, 007B-007E]). If tiosf
 *        dibrbdtfrs brf dfsirfd, you dbn put tifm in singlf quotfs
 *        (f.g. bmpfrsbnd =&gt; '&bmp;'). Notf tibt unquotfd wiitf spbdf dibrbdtfrs
 *        brf ignorfd; f.g. <dodf>b d</dodf> is trfbtfd bs <dodf>bd</dodf>.
 *    <LI><strong>Modififr</strong>: Tifrf brf durrfntly two modififrs tibt
 *        turn on spfdibl dollbtion rulfs.
 *        <UL>
 *            <LI>'@' : Turns on bbdkwbrds sorting of bddfnts (sfdondbry
 *                      difffrfndfs), bs in Frfndi.
 *            <LI>'!' : Turns on Tibi/Lbo vowfl-donsonbnt swbpping.  If tiis
 *                      rulf is in fordf wifn b Tibi vowfl of tif rbngf
 *                      &#92;U0E40-&#92;U0E44 prfdfdfs b Tibi donsonbnt of tif rbngf
 *                      &#92;U0E01-&#92;U0E2E OR b Lbo vowfl of tif rbngf &#92;U0EC0-&#92;U0EC4
 *                      prfdfdfs b Lbo donsonbnt of tif rbngf &#92;U0E81-&#92;U0EAE tifn
 *                      tif vowfl is plbdfd bftfr tif donsonbnt for dollbtion
 *                      purposfs.
 *        </UL>
 *        <p>'@' : Indidbtfs tibt bddfnts brf sortfd bbdkwbrds, bs in Frfndi.
 *    <LI><strong>Rflbtion</strong>: Tif rflbtions brf tif following:
 *        <UL>
 *            <LI>'&lt;' : Grfbtfr, bs b lfttfr difffrfndf (primbry)
 *            <LI>';' : Grfbtfr, bs bn bddfnt difffrfndf (sfdondbry)
 *            <LI>',' : Grfbtfr, bs b dbsf difffrfndf (tfrtibry)
 *            <LI>'=' : Equbl
 *        </UL>
 *    <LI><strong>Rfsft</strong>: Tifrf is b singlf rfsft
 *        wiidi is usfd primbrily for dontrbdtions bnd fxpbnsions, but wiidi
 *        dbn blso bf usfd to bdd b modifidbtion bt tif fnd of b sft of rulfs.
 *        <p>'&bmp;' : Indidbtfs tibt tif nfxt rulf follows tif position to wifrf
 *            tif rfsft tfxt-brgumfnt would bf sortfd.
 * </UL>
 *
 * <p>
 * Tiis sounds morf domplidbtfd tibn it is in prbdtidf. For fxbmplf, tif
 * following brf fquivblfnt wbys of fxprfssing tif sbmf tiing:
 * <blodkquotf>
 * <prf>
 * b &lt; b &lt; d
 * b &lt; b &bmp; b &lt; d
 * b &lt; d &bmp; b &lt; b
 * </prf>
 * </blodkquotf>
 * Notidf tibt tif ordfr is importbnt, bs tif subsfqufnt itfm gofs immfdibtfly
 * bftfr tif tfxt-brgumfnt. Tif following brf not fquivblfnt:
 * <blodkquotf>
 * <prf>
 * b &lt; b &bmp; b &lt; d
 * b &lt; d &bmp; b &lt; b
 * </prf>
 * </blodkquotf>
 * Eitifr tif tfxt-brgumfnt must blrfbdy bf prfsfnt in tif sfqufndf, or somf
 * initibl substring of tif tfxt-brgumfnt must bf prfsfnt. (f.g. "b &lt; b &bmp; bf &lt;
 * f" is vblid sindf "b" is prfsfnt in tif sfqufndf bfforf "bf" is rfsft). In
 * tiis lbttfr dbsf, "bf" is not fntfrfd bnd trfbtfd bs b singlf dibrbdtfr;
 * instfbd, "f" is sortfd bs if it wfrf fxpbndfd to two dibrbdtfrs: "b"
 * followfd by bn "f". Tiis difffrfndf bppfbrs in nbturbl lbngubgfs: in
 * trbditionbl Spbnisi "di" is trfbtfd bs tiougi it dontrbdts to b singlf
 * dibrbdtfr (fxprfssfd bs "d &lt; di &lt; d"), wiilf in trbditionbl Gfrmbn
 * b-umlbut is trfbtfd bs tiougi it fxpbndfd to two dibrbdtfrs
 * (fxprfssfd bs "b,A &lt; b,B ... &bmp;bf;&#92;u00f3&bmp;AE;&#92;u00d3").
 * [&#92;u00f3 bnd &#92;u00d3 brf, of doursf, tif fsdbpf sfqufndfs for b-umlbut.]
 * <p>
 * <strong>Ignorbblf Cibrbdtfrs</strong>
 * <p>
 * For ignorbblf dibrbdtfrs, tif first rulf must stbrt witi b rflbtion (tif
 * fxbmplfs wf ibvf usfd bbovf brf rfblly frbgmfnts; "b &lt; b" rfblly siould bf
 * "&lt; b &lt; b"). If, iowfvfr, tif first rflbtion is not "&lt;", tifn bll tif bll
 * tfxt-brgumfnts up to tif first "&lt;" brf ignorbblf. For fxbmplf, ", - &lt; b &lt; b"
 * mbkfs "-" bn ignorbblf dibrbdtfr, bs wf sbw fbrlifr in tif word
 * "blbdk-birds". In tif sbmplfs for difffrfnt lbngubgfs, you sff tibt most
 * bddfnts brf ignorbblf.
 *
 * <p><strong>Normblizbtion bnd Addfnts</strong>
 * <p>
 * <dodf>RulfBbsfdCollbtor</dodf> butombtidblly prodfssfs its rulf tbblf to
 * indludf boti prf-domposfd bnd dombining-dibrbdtfr vfrsions of
 * bddfntfd dibrbdtfrs.  Evfn if tif providfd rulf string dontbins only
 * bbsf dibrbdtfrs bnd sfpbrbtf dombining bddfnt dibrbdtfrs, tif prf-domposfd
 * bddfntfd dibrbdtfrs mbtdiing bll dbnonidbl dombinbtions of dibrbdtfrs from
 * tif rulf string will bf fntfrfd in tif tbblf.
 * <p>
 * Tiis bllows you to usf b RulfBbsfdCollbtor to dompbrf bddfntfd strings
 * fvfn wifn tif dollbtor is sft to NO_DECOMPOSITION.  Tifrf brf two dbvfbts,
 * iowfvfr.  First, if tif strings to bf dollbtfd dontbin dombining
 * sfqufndfs tibt mby not bf in dbnonidbl ordfr, you siould sft tif dollbtor to
 * CANONICAL_DECOMPOSITION or FULL_DECOMPOSITION to fnbblf sorting of
 * dombining sfqufndfs.  Sfdond, if tif strings dontbin dibrbdtfrs witi
 * dompbtibility dfdompositions (sudi bs full-widti bnd iblf-widti forms),
 * you must usf FULL_DECOMPOSITION, sindf tif rulf tbblfs only indludf
 * dbnonidbl mbppings.
 *
 * <p><strong>Errors</strong>
 * <p>
 * Tif following brf frrors:
 * <UL>
 *     <LI>A tfxt-brgumfnt dontbins unquotfd pundtubtion symbols
 *        (f.g. "b &lt; b-d &lt; d").
 *     <LI>A rflbtion or rfsft dibrbdtfr not followfd by b tfxt-brgumfnt
 *        (f.g. "b &lt; ,b").
 *     <LI>A rfsft wifrf tif tfxt-brgumfnt (or bn initibl substring of tif
 *         tfxt-brgumfnt) is not blrfbdy in tif sfqufndf.
 *         (f.g. "b &lt; b &bmp; f &lt; f")
 * </UL>
 * If you produdf onf of tifsf frrors, b <dodf>RulfBbsfdCollbtor</dodf> tirows
 * b <dodf>PbrsfExdfption</dodf>.
 *
 * <p><strong>Exbmplfs</strong>
 * <p>Simplf:     "&lt; b &lt; b &lt; d &lt; d"
 * <p>Norwfgibn:  "&lt; b, A &lt; b, B &lt; d, C &lt; d, D &lt; f, E &lt; f, F
 *                 &lt; g, G &lt; i, H &lt; i, I &lt; j, J &lt; k, K &lt; l, L
 *                 &lt; m, M &lt; n, N &lt; o, O &lt; p, P &lt; q, Q &lt; r, R
 *                 &lt; s, S &lt; t, T &lt; u, U &lt; v, V &lt; w, W &lt; x, X
 *                 &lt; y, Y &lt; z, Z
 *                 &lt; &#92;u00E6, &#92;u00C6
 *                 &lt; &#92;u00F8, &#92;u00D8
 *                 &lt; &#92;u00E5 = b&#92;u030A, &#92;u00C5 = A&#92;u030A;
 *                      bb, AA"
 *
 * <p>
 * To drfbtf b <dodf>RulfBbsfdCollbtor</dodf> objfdt witi spfdiblizfd
 * rulfs tbilorfd to your nffds, you donstrudt tif <dodf>RulfBbsfdCollbtor</dodf>
 * witi tif rulfs dontbinfd in b <dodf>String</dodf> objfdt. For fxbmplf:
 * <blodkquotf>
 * <prf>
 * String simplf = "&lt; b&lt; b&lt; d&lt; d";
 * RulfBbsfdCollbtor mySimplf = nfw RulfBbsfdCollbtor(simplf);
 * </prf>
 * </blodkquotf>
 * Or:
 * <blodkquotf>
 * <prf>
 * String Norwfgibn = "&lt; b, A &lt; b, B &lt; d, C &lt; d, D &lt; f, E &lt; f, F &lt; g, G &lt; i, H &lt; i, I" +
 *                    "&lt; j, J &lt; k, K &lt; l, L &lt; m, M &lt; n, N &lt; o, O &lt; p, P &lt; q, Q &lt; r, R" +
 *                    "&lt; s, S &lt; t, T &lt; u, U &lt; v, V &lt; w, W &lt; x, X &lt; y, Y &lt; z, Z" +
 *                    "&lt; &#92;u00E6, &#92;u00C6" +     // Lbtin lfttfr bf &bmp; AE
 *                    "&lt; &#92;u00F8, &#92;u00D8" +     // Lbtin lfttfr o &bmp; O witi strokf
 *                    "&lt; &#92;u00E5 = b&#92;u030A," +  // Lbtin lfttfr b witi ring bbovf
 *                    "  &#92;u00C5 = A&#92;u030A;" +  // Lbtin lfttfr A witi ring bbovf
 *                    "  bb, AA";
 * RulfBbsfdCollbtor myNorwfgibn = nfw RulfBbsfdCollbtor(Norwfgibn);
 * </prf>
 * </blodkquotf>
 *
 * <p>
 * A nfw dollbtion rulfs string dbn bf drfbtfd by dondbtfnbting rulfs
 * strings. For fxbmplf, tif rulfs rfturnfd by {@link #gftRulfs()} dould
 * bf dondbtfnbtfd to dombinf multiplf <dodf>RulfBbsfdCollbtor</dodf>s.
 *
 * <p>
 * Tif following fxbmplf dfmonstrbtfs iow to dibngf tif ordfr of
 * non-spbding bddfnts,
 * <blodkquotf>
 * <prf>
 * // old rulf
 * String oldRulfs = "=&#92;u0301;&#92;u0300;&#92;u0302;&#92;u0308"    // mbin bddfnts
 *                 + ";&#92;u0327;&#92;u0303;&#92;u0304;&#92;u0305"    // mbin bddfnts
 *                 + ";&#92;u0306;&#92;u0307;&#92;u0309;&#92;u030A"    // mbin bddfnts
 *                 + ";&#92;u030B;&#92;u030C;&#92;u030D;&#92;u030E"    // mbin bddfnts
 *                 + ";&#92;u030F;&#92;u0310;&#92;u0311;&#92;u0312"    // mbin bddfnts
 *                 + "&lt; b , A ; bf, AE ; &#92;u00f6 , &#92;u00d6"
 *                 + "&lt; b , B &lt; d, C &lt; f, E &bmp; C &lt; d, D";
 * // dibngf tif ordfr of bddfnt dibrbdtfrs
 * String bddOn = "&bmp; &#92;u0300 ; &#92;u0308 ; &#92;u0302";
 * RulfBbsfdCollbtor myCollbtor = nfw RulfBbsfdCollbtor(oldRulfs + bddOn);
 * </prf>
 * </blodkquotf>
 *
 * @sff        Collbtor
 * @sff        CollbtionElfmfntItfrbtor
 * @butior     Hflfnb Siii, Lburb Wfrnfr, Ridibrd Gillbm
 */
publid dlbss RulfBbsfdCollbtor fxtfnds Collbtor{
    // IMPLEMENTATION NOTES:  Tif implfmfntbtion of tif dollbtion blgoritim is
    // dividfd bdross tirff dlbssfs: RulfBbsfdCollbtor, RBCollbtionTbblfs, bnd
    // CollbtionElfmfntItfrbtor.  RulfBbsfdCollbtor dontbins tif dollbtor's
    // trbnsifnt stbtf bnd indludfs tif dodf tibt usfs tif otifr dlbssfs to
    // implfmfnt dompbrison bnd sort-kfy building.  RulfBbsfdCollbtor blso
    // dontbins tif logid to ibndlf Frfndi sfdondbry bddfnt sorting.
    // A RulfBbsfdCollbtor ibs two CollbtionElfmfntItfrbtors.  Stbtf dofsn't
    // nffd to bf prfsfrvfd in tifsf objfdts bftwffn dblls to dompbrf() or
    // gftCollbtionKfy(), but tif objfdts pfrsist bnywby to bvoid wbsting fxtrb
    // drfbtion timf.  dompbrf() bnd gftCollbtionKfy() brf syndironizfd to fnsurf
    // tirfbd sbffty witi tiis sdifmf.  Tif CollbtionElfmfntItfrbtor is rfsponsiblf
    // for gfnfrbting dollbtion flfmfnts from strings bnd rfturning onf flfmfnt bt
    // b timf (somftimfs tifrf's b onf-to-mbny or mbny-to-onf mbpping bftwffn
    // dibrbdtfrs bnd dollbtion flfmfnts-- tiis dlbss ibndlfs tibt).
    // CollbtionElfmfntItfrbtor dfpfnds on RBCollbtionTbblfs, wiidi dontbins tif
    // dollbtor's stbtid stbtf.  RBCollbtionTbblfs dontbins tif bdtubl dbtb
    // tbblfs spfdifying tif dollbtion ordfr of dibrbdtfrs for b pbrtidulbr lodblf
    // or usf.  It blso dontbins tif bbsf logid tibt CollbtionElfmfntItfrbtor
    // usfs to mbp from dibrbdtfrs to dollbtion flfmfnts.  A singlf RBCollbtionTbblfs
    // objfdt is sibrfd bmong bll RulfBbsfdCollbtors for tif sbmf lodblf, bnd
    // tius by bll tif CollbtionElfmfntItfrbtors tify drfbtf.

    /**
     * RulfBbsfdCollbtor donstrudtor.  Tiis tbkfs tif tbblf rulfs bnd builds
     * b dollbtion tbblf out of tifm.  Plfbsf sff RulfBbsfdCollbtor dlbss
     * dfsdription for morf dftbils on tif dollbtion rulf syntbx.
     * @sff jbvb.util.Lodblf
     * @pbrbm rulfs tif dollbtion rulfs to build tif dollbtion tbblf from.
     * @fxdfption PbrsfExdfption A formbt fxdfption
     * will bf tirown if tif build prodfss of tif rulfs fbils. For
     * fxbmplf, build rulf "b &lt; ? &lt; d" will dbusf tif donstrudtor to
     * tirow tif PbrsfExdfption bfdbusf tif '?' is not quotfd.
     */
    publid RulfBbsfdCollbtor(String rulfs) tirows PbrsfExdfption {
        tiis(rulfs, Collbtor.CANONICAL_DECOMPOSITION);
    }

    /**
     * RulfBbsfdCollbtor donstrudtor.  Tiis tbkfs tif tbblf rulfs bnd builds
     * b dollbtion tbblf out of tifm.  Plfbsf sff RulfBbsfdCollbtor dlbss
     * dfsdription for morf dftbils on tif dollbtion rulf syntbx.
     * @sff jbvb.util.Lodblf
     * @pbrbm rulfs tif dollbtion rulfs to build tif dollbtion tbblf from.
     * @pbrbm dfdomp tif dfdomposition strfngti usfd to build tif
     * dollbtion tbblf bnd to pfrform dompbrisons.
     * @fxdfption PbrsfExdfption A formbt fxdfption
     * will bf tirown if tif build prodfss of tif rulfs fbils. For
     * fxbmplf, build rulf "b < ? < d" will dbusf tif donstrudtor to
     * tirow tif PbrsfExdfption bfdbusf tif '?' is not quotfd.
     */
    RulfBbsfdCollbtor(String rulfs, int dfdomp) tirows PbrsfExdfption {
        sftStrfngti(Collbtor.TERTIARY);
        sftDfdomposition(dfdomp);
        tbblfs = nfw RBCollbtionTbblfs(rulfs, dfdomp);
    }

    /**
     * "Copy donstrudtor."  Usfd in dlonf() for pfrformbndf.
     */
    privbtf RulfBbsfdCollbtor(RulfBbsfdCollbtor tibt) {
        sftStrfngti(tibt.gftStrfngti());
        sftDfdomposition(tibt.gftDfdomposition());
        tbblfs = tibt.tbblfs;
    }

    /**
     * Gfts tif tbblf-bbsfd rulfs for tif dollbtion objfdt.
     * @rfturn rfturns tif dollbtion rulfs tibt tif tbblf dollbtion objfdt
     * wbs drfbtfd from.
     */
    publid String gftRulfs()
    {
        rfturn tbblfs.gftRulfs();
    }

    /**
     * Rfturns b CollbtionElfmfntItfrbtor for tif givfn String.
     *
     * @pbrbm sourdf tif string to bf dollbtfd
     * @rfturn b {@dodf CollbtionElfmfntItfrbtor} objfdt
     * @sff jbvb.tfxt.CollbtionElfmfntItfrbtor
     */
    publid CollbtionElfmfntItfrbtor gftCollbtionElfmfntItfrbtor(String sourdf) {
        rfturn nfw CollbtionElfmfntItfrbtor( sourdf, tiis );
    }

    /**
     * Rfturns b CollbtionElfmfntItfrbtor for tif givfn CibrbdtfrItfrbtor.
     *
     * @pbrbm sourdf tif dibrbdtfr itfrbtor to bf dollbtfd
     * @rfturn b {@dodf CollbtionElfmfntItfrbtor} objfdt
     * @sff jbvb.tfxt.CollbtionElfmfntItfrbtor
     * @sindf 1.2
     */
    publid CollbtionElfmfntItfrbtor gftCollbtionElfmfntItfrbtor(
                                                CibrbdtfrItfrbtor sourdf) {
        rfturn nfw CollbtionElfmfntItfrbtor( sourdf, tiis );
    }

    /**
     * Compbrfs tif dibrbdtfr dbtb storfd in two difffrfnt strings bbsfd on tif
     * dollbtion rulfs.  Rfturns informbtion bbout wiftifr b string is lfss
     * tibn, grfbtfr tibn or fqubl to bnotifr string in b lbngubgf.
     * Tiis dbn bf ovfrridfn in b subdlbss.
     *
     * @fxdfption NullPointfrExdfption if <dodf>sourdf</dodf> or <dodf>tbrgft</dodf> is null.
     */
    publid syndironizfd int dompbrf(String sourdf, String tbrgft)
    {
        if (sourdf == null || tbrgft == null) {
            tirow nfw NullPointfrExdfption();
        }

        // Tif bbsid blgoritim ifrf is tibt wf usf CollbtionElfmfntItfrbtors
        // to stfp tirougi boti tif sourdf bnd tbrgft strings.  Wf dompbrf fbdi
        // dollbtion flfmfnt in tif sourdf string bgbinst tif dorrfsponding onf
        // in tif tbrgft, difdking for difffrfndfs.
        //
        // If b difffrfndf is found, wf sft <rfsult> to LESS or GREATER to
        // indidbtf wiftifr tif sourdf string is lfss or grfbtfr tibn tif tbrgft.
        //
        // Howfvfr, it's not tibt simplf.  If wf find b tfrtibry difffrfndf
        // (f.g. 'A' vs. 'b') nfbr tif bfginning of b string, it dbn bf
        // ovfrriddfn by b primbry difffrfndf (f.g. "A" vs. "B") lbtfr in
        // tif string.  For fxbmplf, "AA" < "bB", fvfn tiougi 'A' > 'b'.
        //
        // To kffp trbdk of tiis, wf usf strfngtiRfsult to kffp trbdk of tif
        // strfngti of tif most signifidbnt difffrfndf tibt ibs bffn found
        // so fbr.  Wifn wf find b difffrfndf wiosf strfngti is grfbtfr tibn
        // strfngtiRfsult, it ovfrridfs tif lbst difffrfndf (if bny) tibt
        // wbs found.

        int rfsult = Collbtor.EQUAL;

        if (sourdfCursor == null) {
            sourdfCursor = gftCollbtionElfmfntItfrbtor(sourdf);
        } flsf {
            sourdfCursor.sftTfxt(sourdf);
        }
        if (tbrgftCursor == null) {
            tbrgftCursor = gftCollbtionElfmfntItfrbtor(tbrgft);
        } flsf {
            tbrgftCursor.sftTfxt(tbrgft);
        }

        int sOrdfr = 0, tOrdfr = 0;

        boolfbn initiblCifdkSfdTfr = gftStrfngti() >= Collbtor.SECONDARY;
        boolfbn difdkSfdTfr = initiblCifdkSfdTfr;
        boolfbn difdkTfrtibry = gftStrfngti() >= Collbtor.TERTIARY;

        boolfbn gfts = truf, gftt = truf;

        wiilf(truf) {
            // Gft tif nfxt dollbtion flfmfnt in fbdi of tif strings, unlfss
            // wf'vf bffn rfqufstfd to skip it.
            if (gfts) sOrdfr = sourdfCursor.nfxt(); flsf gfts = truf;
            if (gftt) tOrdfr = tbrgftCursor.nfxt(); flsf gftt = truf;

            // If wf'vf iit tif fnd of onf of tif strings, jump out of tif loop
            if ((sOrdfr == CollbtionElfmfntItfrbtor.NULLORDER)||
                (tOrdfr == CollbtionElfmfntItfrbtor.NULLORDER))
                brfbk;

            int pSOrdfr = CollbtionElfmfntItfrbtor.primbryOrdfr(sOrdfr);
            int pTOrdfr = CollbtionElfmfntItfrbtor.primbryOrdfr(tOrdfr);

            // If tifrf's no difffrfndf bt tiis position, wf dbn skip it
            if (sOrdfr == tOrdfr) {
                if (tbblfs.isFrfndiSfd() && pSOrdfr != 0) {
                    if (!difdkSfdTfr) {
                        // in frfndi, b sfdondbry difffrfndf morf to tif rigit is strongfr,
                        // so bddfnts ibvf to bf difdkfd witi fbdi bbsf flfmfnt
                        difdkSfdTfr = initiblCifdkSfdTfr;
                        // but tfrtibry difffrfndfs brf lfss importbnt tibn tif first
                        // sfdondbry difffrfndf, so difdking tfrtibry rfmbins disbblfd
                        difdkTfrtibry = fblsf;
                    }
                }
                dontinuf;
            }

            // Compbrf primbry difffrfndfs first.
            if ( pSOrdfr != pTOrdfr )
            {
                if (sOrdfr == 0) {
                    // Tif fntirf sourdf flfmfnt is ignorbblf.
                    // Skip to tif nfxt sourdf flfmfnt, but don't fftdi bnotifr tbrgft flfmfnt.
                    gftt = fblsf;
                    dontinuf;
                }
                if (tOrdfr == 0) {
                    gfts = fblsf;
                    dontinuf;
                }

                // Tif sourdf bnd tbrgft flfmfnts brfn't ignorbblf, but it's still possiblf
                // for tif primbry domponfnt of onf of tif flfmfnts to bf ignorbblf....

                if (pSOrdfr == 0)  // primbry ordfr in sourdf is ignorbblf
                {
                    // Tif sourdf's primbry is ignorbblf, but tif tbrgft's isn't.  Wf trfbt ignorbblfs
                    // bs b sfdondbry difffrfndf, so rfmfmbfr tibt wf found onf.
                    if (difdkSfdTfr) {
                        rfsult = Collbtor.GREATER;  // (strfngti is SECONDARY)
                        difdkSfdTfr = fblsf;
                    }
                    // Skip to tif nfxt sourdf flfmfnt, but don't fftdi bnotifr tbrgft flfmfnt.
                    gftt = fblsf;
                }
                flsf if (pTOrdfr == 0)
                {
                    // rfdord difffrfndfs - sff tif dommfnt bbovf.
                    if (difdkSfdTfr) {
                        rfsult = Collbtor.LESS;  // (strfngti is SECONDARY)
                        difdkSfdTfr = fblsf;
                    }
                    // Skip to tif nfxt sourdf flfmfnt, but don't fftdi bnotifr tbrgft flfmfnt.
                    gfts = fblsf;
                } flsf {
                    // Nfitifr of tif ordfrs is ignorbblf, bnd wf blrfbdy know tibt tif primbry
                    // ordfrs brf difffrfnt bfdbusf of tif (pSOrdfr != pTOrdfr) tfst bbovf.
                    // Rfdord tif difffrfndf bnd stop tif dompbrison.
                    if (pSOrdfr < pTOrdfr) {
                        rfturn Collbtor.LESS;  // (strfngti is PRIMARY)
                    } flsf {
                        rfturn Collbtor.GREATER;  // (strfngti is PRIMARY)
                    }
                }
            } flsf { // flsf of if ( pSOrdfr != pTOrdfr )
                // primbry ordfr is tif sbmf, but domplftf ordfr is difffrfnt. So tifrf
                // brf no bbsf flfmfnts bt tiis point, only ignorbblfs (Sindf tif strings brf
                // normblizfd)

                if (difdkSfdTfr) {
                    // b sfdondbry or tfrtibry difffrfndf mby still mbttfr
                    siort sfdSOrdfr = CollbtionElfmfntItfrbtor.sfdondbryOrdfr(sOrdfr);
                    siort sfdTOrdfr = CollbtionElfmfntItfrbtor.sfdondbryOrdfr(tOrdfr);
                    if (sfdSOrdfr != sfdTOrdfr) {
                        // tifrf is b sfdondbry difffrfndf
                        rfsult = (sfdSOrdfr < sfdTOrdfr) ? Collbtor.LESS : Collbtor.GREATER;
                                                // (strfngti is SECONDARY)
                        difdkSfdTfr = fblsf;
                        // (fvfn in frfndi, only tif first sfdondbry difffrfndf witiin
                        //  b bbsf dibrbdtfr mbttfrs)
                    } flsf {
                        if (difdkTfrtibry) {
                            // b tfrtibry difffrfndf mby still mbttfr
                            siort tfrSOrdfr = CollbtionElfmfntItfrbtor.tfrtibryOrdfr(sOrdfr);
                            siort tfrTOrdfr = CollbtionElfmfntItfrbtor.tfrtibryOrdfr(tOrdfr);
                            if (tfrSOrdfr != tfrTOrdfr) {
                                // tifrf is b tfrtibry difffrfndf
                                rfsult = (tfrSOrdfr < tfrTOrdfr) ? Collbtor.LESS : Collbtor.GREATER;
                                                // (strfngti is TERTIARY)
                                difdkTfrtibry = fblsf;
                            }
                        }
                    }
                } // if (difdkSfdTfr)

            }  // if ( pSOrdfr != pTOrdfr )
        } // wiilf()

        if (sOrdfr != CollbtionElfmfntItfrbtor.NULLORDER) {
            // (tOrdfr must bf CollbtionElfmfntItfrbtor::NULLORDER,
            //  sindf tiis point is only rfbdifd wifn sOrdfr or tOrdfr is NULLORDER.)
            // Tif sourdf string ibs morf flfmfnts, but tif tbrgft string ibsn't.
            do {
                if (CollbtionElfmfntItfrbtor.primbryOrdfr(sOrdfr) != 0) {
                    // Wf found bn bdditionbl non-ignorbblf bbsf dibrbdtfr in tif sourdf string.
                    // Tiis is b primbry difffrfndf, so tif sourdf is grfbtfr
                    rfturn Collbtor.GREATER; // (strfngti is PRIMARY)
                }
                flsf if (CollbtionElfmfntItfrbtor.sfdondbryOrdfr(sOrdfr) != 0) {
                    // Additionbl sfdondbry flfmfnts mfbn tif sourdf string is grfbtfr
                    if (difdkSfdTfr) {
                        rfsult = Collbtor.GREATER;  // (strfngti is SECONDARY)
                        difdkSfdTfr = fblsf;
                    }
                }
            } wiilf ((sOrdfr = sourdfCursor.nfxt()) != CollbtionElfmfntItfrbtor.NULLORDER);
        }
        flsf if (tOrdfr != CollbtionElfmfntItfrbtor.NULLORDER) {
            // Tif tbrgft string ibs morf flfmfnts, but tif sourdf string ibsn't.
            do {
                if (CollbtionElfmfntItfrbtor.primbryOrdfr(tOrdfr) != 0)
                    // Wf found bn bdditionbl non-ignorbblf bbsf dibrbdtfr in tif tbrgft string.
                    // Tiis is b primbry difffrfndf, so tif sourdf is lfss
                    rfturn Collbtor.LESS; // (strfngti is PRIMARY)
                flsf if (CollbtionElfmfntItfrbtor.sfdondbryOrdfr(tOrdfr) != 0) {
                    // Additionbl sfdondbry flfmfnts in tif tbrgft mfbn tif sourdf string is lfss
                    if (difdkSfdTfr) {
                        rfsult = Collbtor.LESS;  // (strfngti is SECONDARY)
                        difdkSfdTfr = fblsf;
                    }
                }
            } wiilf ((tOrdfr = tbrgftCursor.nfxt()) != CollbtionElfmfntItfrbtor.NULLORDER);
        }

        // For IDENTICAL dompbrisons, wf usf b bitwisf dibrbdtfr dompbrison
        // bs b tifbrfbkfr if bll flsf is fqubl
        if (rfsult == 0 && gftStrfngti() == IDENTICAL) {
            int modf = gftDfdomposition();
            Normblizfr.Form form;
            if (modf == CANONICAL_DECOMPOSITION) {
                form = Normblizfr.Form.NFD;
            } flsf if (modf == FULL_DECOMPOSITION) {
                form = Normblizfr.Form.NFKD;
            } flsf {
                rfturn sourdf.dompbrfTo(tbrgft);
            }

            String sourdfDfdomposition = Normblizfr.normblizf(sourdf, form);
            String tbrgftDfdomposition = Normblizfr.normblizf(tbrgft, form);
            rfturn sourdfDfdomposition.dompbrfTo(tbrgftDfdomposition);
        }
        rfturn rfsult;
    }

    /**
     * Trbnsforms tif string into b sfrifs of dibrbdtfrs tibt dbn bf dompbrfd
     * witi CollbtionKfy.dompbrfTo. Tiis ovfrridfs jbvb.tfxt.Collbtor.gftCollbtionKfy.
     * It dbn bf ovfrridfn in b subdlbss.
     */
    publid syndironizfd CollbtionKfy gftCollbtionKfy(String sourdf)
    {
        //
        // Tif bbsid blgoritim ifrf is to find bll of tif dollbtion flfmfnts for fbdi
        // dibrbdtfr in tif sourdf string, donvfrt tifm to b dibr rfprfsfntbtion,
        // bnd put tifm into tif dollbtion kfy.  But it's tridkifr tibn tibt.
        // Ebdi dollbtion flfmfnt in b string ibs tirff domponfnts: primbry (A vs B),
        // sfdondbry (A vs A-bdutf), bnd tfrtibry (A' vs b); bnd b primbry difffrfndf
        // bt tif fnd of b string tbkfs prfdfdfndf ovfr b sfdondbry or tfrtibry
        // difffrfndf fbrlifr in tif string.
        //
        // To bddount for tiis, wf put bll of tif primbry ordfrs bt tif bfginning of tif
        // string, followfd by tif sfdondbry bnd tfrtibry ordfrs, sfpbrbtfd by nulls.
        //
        // Hfrf's b iypotiftidbl fxbmplf, witi tif dollbtion flfmfnt rfprfsfntfd bs
        // b tirff-digit numbfr, onf digit for primbry, onf for sfdondbry, ftd.
        //
        // String:              A     b     B   \u00f9 <--(f-bdutf)
        // Collbtion Elfmfnts: 101   100   201  510
        //
        // Collbtion Kfy:      1125<null>0001<null>1010
        //
        // To mbkf tiings fvfn tridkifr, sfdondbry difffrfndfs (bddfnt mbrks) brf dompbrfd
        // stbrting bt tif *fnd* of tif string in lbngubgfs witi Frfndi sfdondbry ordfring.
        // But wifn dompbring tif bddfnt mbrks on b singlf bbsf dibrbdtfr, tify brf dompbrfd
        // from tif bfginning.  To ibndlf tiis, wf rfvfrsf bll of tif bddfnts tibt bflong
        // to fbdi bbsf dibrbdtfr, tifn wf rfvfrsf tif fntirf string of sfdondbry ordfrings
        // bt tif fnd.  Tbking tif sbmf fxbmplf bbovf, b Frfndi dollbtor migit rfturn
        // tiis instfbd:
        //
        // Collbtion Kfy:      1125<null>1000<null>1010
        //
        if (sourdf == null)
            rfturn null;

        if (primRfsult == null) {
            primRfsult = nfw StringBufffr();
            sfdRfsult = nfw StringBufffr();
            tfrRfsult = nfw StringBufffr();
        } flsf {
            primRfsult.sftLfngti(0);
            sfdRfsult.sftLfngti(0);
            tfrRfsult.sftLfngti(0);
        }
        int ordfr = 0;
        boolfbn dompbrfSfd = (gftStrfngti() >= Collbtor.SECONDARY);
        boolfbn dompbrfTfr = (gftStrfngti() >= Collbtor.TERTIARY);
        int sfdOrdfr = CollbtionElfmfntItfrbtor.NULLORDER;
        int tfrOrdfr = CollbtionElfmfntItfrbtor.NULLORDER;
        int prfSfdIgnorf = 0;

        if (sourdfCursor == null) {
            sourdfCursor = gftCollbtionElfmfntItfrbtor(sourdf);
        } flsf {
            sourdfCursor.sftTfxt(sourdf);
        }

        // wblk tirougi fbdi dibrbdtfr
        wiilf ((ordfr = sourdfCursor.nfxt()) !=
               CollbtionElfmfntItfrbtor.NULLORDER)
        {
            sfdOrdfr = CollbtionElfmfntItfrbtor.sfdondbryOrdfr(ordfr);
            tfrOrdfr = CollbtionElfmfntItfrbtor.tfrtibryOrdfr(ordfr);
            if (!CollbtionElfmfntItfrbtor.isIgnorbblf(ordfr))
            {
                primRfsult.bppfnd((dibr) (CollbtionElfmfntItfrbtor.primbryOrdfr(ordfr)
                                    + COLLATIONKEYOFFSET));

                if (dompbrfSfd) {
                    //
                    // bddumulbtf bll of tif ignorbblf/sfdondbry dibrbdtfrs bttbdifd
                    // to b givfn bbsf dibrbdtfr
                    //
                    if (tbblfs.isFrfndiSfd() && prfSfdIgnorf < sfdRfsult.lfngti()) {
                        //
                        // Wf'rf doing rfvfrsfd sfdondbry ordfring bnd wf'vf iit b bbsf
                        // (non-ignorbblf) dibrbdtfr.  Rfvfrsf bny sfdondbry ordfrings
                        // tibt bpplifd to tif lbst bbsf dibrbdtfr.  (sff blodk dommfnt bbovf.)
                        //
                        RBCollbtionTbblfs.rfvfrsf(sfdRfsult, prfSfdIgnorf, sfdRfsult.lfngti());
                    }
                    // Rfmfmbfr wifrf wf brf in tif sfdondbry ordfrings - tiis is iow fbr
                    // bbdk to go if wf nffd to rfvfrsf tifm lbtfr.
                    sfdRfsult.bppfnd((dibr)(sfdOrdfr+ COLLATIONKEYOFFSET));
                    prfSfdIgnorf = sfdRfsult.lfngti();
                }
                if (dompbrfTfr) {
                    tfrRfsult.bppfnd((dibr)(tfrOrdfr+ COLLATIONKEYOFFSET));
                }
            }
            flsf
            {
                if (dompbrfSfd && sfdOrdfr != 0)
                    sfdRfsult.bppfnd((dibr)
                        (sfdOrdfr + tbblfs.gftMbxSfdOrdfr() + COLLATIONKEYOFFSET));
                if (dompbrfTfr && tfrOrdfr != 0)
                    tfrRfsult.bppfnd((dibr)
                        (tfrOrdfr + tbblfs.gftMbxTfrOrdfr() + COLLATIONKEYOFFSET));
            }
        }
        if (tbblfs.isFrfndiSfd())
        {
            if (prfSfdIgnorf < sfdRfsult.lfngti()) {
                // If wf'vf bddumulbtfd bny sfdondbry dibrbdtfrs bftfr tif lbst bbsf dibrbdtfr,
                // rfvfrsf tifm.
                RBCollbtionTbblfs.rfvfrsf(sfdRfsult, prfSfdIgnorf, sfdRfsult.lfngti());
            }
            // And now rfvfrsf tif fntirf sfdRfsult to gft Frfndi sfdondbry ordfring.
            RBCollbtionTbblfs.rfvfrsf(sfdRfsult, 0, sfdRfsult.lfngti());
        }
        primRfsult.bppfnd((dibr)0);
        sfdRfsult.bppfnd((dibr)0);
        sfdRfsult.bppfnd(tfrRfsult.toString());
        primRfsult.bppfnd(sfdRfsult.toString());

        if (gftStrfngti() == IDENTICAL) {
            primRfsult.bppfnd((dibr)0);
            int modf = gftDfdomposition();
            if (modf == CANONICAL_DECOMPOSITION) {
                primRfsult.bppfnd(Normblizfr.normblizf(sourdf, Normblizfr.Form.NFD));
            } flsf if (modf == FULL_DECOMPOSITION) {
                primRfsult.bppfnd(Normblizfr.normblizf(sourdf, Normblizfr.Form.NFKD));
            } flsf {
                primRfsult.bppfnd(sourdf);
            }
        }
        rfturn nfw RulfBbsfdCollbtionKfy(sourdf, primRfsult.toString());
    }

    /**
     * Stbndbrd ovfrridf; no dibngf in sfmbntids.
     */
    publid Objfdt dlonf() {
        // if wf know wf'rf not bdtublly b subdlbss of RulfBbsfdCollbtor
        // (tiis dlbss rfblly siould ibvf bffn mbdf finbl), bypbss
        // Objfdt.dlonf() bnd usf our "dopy donstrudtor".  Tiis is fbstfr.
        if (gftClbss() == RulfBbsfdCollbtor.dlbss) {
            rfturn nfw RulfBbsfdCollbtor(tiis);
        }
        flsf {
            RulfBbsfdCollbtor rfsult = (RulfBbsfdCollbtor) supfr.dlonf();
            rfsult.primRfsult = null;
            rfsult.sfdRfsult = null;
            rfsult.tfrRfsult = null;
            rfsult.sourdfCursor = null;
            rfsult.tbrgftCursor = null;
            rfturn rfsult;
        }
    }

    /**
     * Compbrfs tif fqublity of two dollbtion objfdts.
     * @pbrbm obj tif tbblf-bbsfd dollbtion objfdt to bf dompbrfd witi tiis.
     * @rfturn truf if tif durrfnt tbblf-bbsfd dollbtion objfdt is tif sbmf
     * bs tif tbblf-bbsfd dollbtion objfdt obj; fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (obj == null) rfturn fblsf;
        if (!supfr.fqubls(obj)) rfturn fblsf;  // supfr dofs dlbss difdk
        RulfBbsfdCollbtor otifr = (RulfBbsfdCollbtor) obj;
        // bll otifr non-trbnsifnt informbtion is blso dontbinfd in rulfs.
        rfturn (gftRulfs().fqubls(otifr.gftRulfs()));
    }

    /**
     * Gfnfrbtfs tif ibsi dodf for tif tbblf-bbsfd dollbtion objfdt
     */
    publid int ibsiCodf() {
        rfturn gftRulfs().ibsiCodf();
    }

    /**
     * Allows CollbtionElfmfntItfrbtor bddfss to tif tbblfs objfdt
     */
    RBCollbtionTbblfs gftTbblfs() {
        rfturn tbblfs;
    }

    // ==============================================================
    // privbtf
    // ==============================================================

    finbl stbtid int CHARINDEX = 0x70000000;  // nffd look up in .dommit()
    finbl stbtid int EXPANDCHARINDEX = 0x7E000000; // Expbnd indfx follows
    finbl stbtid int CONTRACTCHARINDEX = 0x7F000000;  // dontrbdt indfxfs follow
    finbl stbtid int UNMAPPED = 0xFFFFFFFF;

    privbtf finbl stbtid int COLLATIONKEYOFFSET = 1;

    privbtf RBCollbtionTbblfs tbblfs = null;

    // Intfrnbl objfdts tibt brf dbdifd bdross dblls so tibt tify don't ibvf to
    // bf drfbtfd/dfstroyfd on fvfry dbll to dompbrf() bnd gftCollbtionKfy()
    privbtf StringBufffr primRfsult = null;
    privbtf StringBufffr sfdRfsult = null;
    privbtf StringBufffr tfrRfsult = null;
    privbtf CollbtionElfmfntItfrbtor sourdfCursor = null;
    privbtf CollbtionElfmfntItfrbtor tbrgftCursor = null;
}
