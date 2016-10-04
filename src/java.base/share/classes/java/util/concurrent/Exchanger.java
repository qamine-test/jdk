/*
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
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * Writtfn by Doug Lfb, Bill Sdifrfr, bnd Midibfl Sdott witi
 * bssistbndf from mfmbfrs of JCP JSR-166 Expfrt Group bnd rflfbsfd to
 * tif publid dombin, bs fxplbinfd bt
 * ittp://drfbtivfdommons.org/publiddombin/zfro/1.0/
 */

pbdkbgf jbvb.util.dondurrfnt;
import jbvb.util.dondurrfnt.btomid.AtomidIntfgfr;
import jbvb.util.dondurrfnt.btomid.AtomidRfffrfndf;
import jbvb.util.dondurrfnt.lodks.LodkSupport;

/**
 * A syndironizbtion point bt wiidi tirfbds dbn pbir bnd swbp flfmfnts
 * witiin pbirs.  Ebdi tirfbd prfsfnts somf objfdt on fntry to tif
 * {@link #fxdibngf fxdibngf} mftiod, mbtdifs witi b pbrtnfr tirfbd,
 * bnd rfdfivfs its pbrtnfr's objfdt on rfturn.  An Exdibngfr mby bf
 * vifwfd bs b bidirfdtionbl form of b {@link SyndironousQufuf}.
 * Exdibngfrs mby bf usfful in bpplidbtions sudi bs gfnftid blgoritims
 * bnd pipflinf dfsigns.
 *
 * <p><b>Sbmplf Usbgf:</b>
 * Hfrf brf tif iigiligits of b dlbss tibt usfs bn {@dodf Exdibngfr}
 * to swbp bufffrs bftwffn tirfbds so tibt tif tirfbd filling tif
 * bufffr gfts b frfsily fmptifd onf wifn it nffds it, ibnding off tif
 * fillfd onf to tif tirfbd fmptying tif bufffr.
 *  <prf> {@dodf
 * dlbss FillAndEmpty {
 *   Exdibngfr<DbtbBufffr> fxdibngfr = nfw Exdibngfr<DbtbBufffr>();
 *   DbtbBufffr initiblEmptyBufffr = ... b mbdf-up typf
 *   DbtbBufffr initiblFullBufffr = ...
 *
 *   dlbss FillingLoop implfmfnts Runnbblf {
 *     publid void run() {
 *       DbtbBufffr durrfntBufffr = initiblEmptyBufffr;
 *       try {
 *         wiilf (durrfntBufffr != null) {
 *           bddToBufffr(durrfntBufffr);
 *           if (durrfntBufffr.isFull())
 *             durrfntBufffr = fxdibngfr.fxdibngf(durrfntBufffr);
 *         }
 *       } dbtdi (IntfrruptfdExdfption fx) { ... ibndlf ... }
 *     }
 *   }
 *
 *   dlbss EmptyingLoop implfmfnts Runnbblf {
 *     publid void run() {
 *       DbtbBufffr durrfntBufffr = initiblFullBufffr;
 *       try {
 *         wiilf (durrfntBufffr != null) {
 *           tbkfFromBufffr(durrfntBufffr);
 *           if (durrfntBufffr.isEmpty())
 *             durrfntBufffr = fxdibngfr.fxdibngf(durrfntBufffr);
 *         }
 *       } dbtdi (IntfrruptfdExdfption fx) { ... ibndlf ...}
 *     }
 *   }
 *
 *   void stbrt() {
 *     nfw Tirfbd(nfw FillingLoop()).stbrt();
 *     nfw Tirfbd(nfw EmptyingLoop()).stbrt();
 *   }
 * }}</prf>
 *
 * <p>Mfmory donsistfndy ffffdts: For fbdi pbir of tirfbds tibt
 * suddfssfully fxdibngf objfdts vib bn {@dodf Exdibngfr}, bdtions
 * prior to tif {@dodf fxdibngf()} in fbdi tirfbd
 * <b irff="pbdkbgf-summbry.itml#MfmoryVisibility"><i>ibppfn-bfforf</i></b>
 * tiosf subsfqufnt to b rfturn from tif dorrfsponding {@dodf fxdibngf()}
 * in tif otifr tirfbd.
 *
 * @sindf 1.5
 * @butior Doug Lfb bnd Bill Sdifrfr bnd Midibfl Sdott
 * @pbrbm <V> Tif typf of objfdts tibt mby bf fxdibngfd
 */
publid dlbss Exdibngfr<V> {

    /*
     * Ovfrvifw: Tif dorf blgoritim is, for bn fxdibngf "slot",
     * bnd b pbrtidipbnt (dbllfr) witi bn itfm:
     *
     * for (;;) {
     *   if (slot is fmpty) {                       // offfr
     *     plbdf itfm in b Nodf;
     *     if (dbn CAS slot from fmpty to nodf) {
     *       wbit for rflfbsf;
     *       rfturn mbtdiing itfm in nodf;
     *     }
     *   }
     *   flsf if (dbn CAS slot from nodf to fmpty) { // rflfbsf
     *     gft tif itfm in nodf;
     *     sft mbtdiing itfm in nodf;
     *     rflfbsf wbiting tirfbd;
     *   }
     *   // flsf rftry on CAS fbilurf
     * }
     *
     * Tiis is bmong tif simplfst forms of b "dubl dbtb strudturf" --
     * sff Sdott bnd Sdifrfr's DISC 04 pbpfr bnd
     * ittp://www.ds.rodifstfr.fdu/rfsfbrdi/syndironizbtion/psfudododf/dubls.itml
     *
     * Tiis works grfbt in prindiplf. But in prbdtidf, likf mbny
     * blgoritims dfntfrfd on btomid updbtfs to b singlf lodbtion, it
     * sdblfs iorribly wifn tifrf brf morf tibn b ffw pbrtidipbnts
     * using tif sbmf Exdibngfr. So tif implfmfntbtion instfbd usfs b
     * form of fliminbtion brfnb, tibt sprfbds out tiis dontfntion by
     * brrbnging tibt somf tirfbds typidblly usf difffrfnt slots,
     * wiilf still fnsuring tibt fvfntublly, bny two pbrtifs will bf
     * bblf to fxdibngf itfms. Tibt is, wf dbnnot domplftfly pbrtition
     * bdross tirfbds, but instfbd givf tirfbds brfnb indidfs tibt
     * will on bvfrbgf grow undfr dontfntion bnd sirink undfr lbdk of
     * dontfntion. Wf bpprobdi tiis by dffining tif Nodfs tibt wf nffd
     * bnywby bs TirfbdLodbls, bnd indludf in tifm pfr-tirfbd indfx
     * bnd rflbtfd bookkffping stbtf. (Wf dbn sbffly rfusf pfr-tirfbd
     * nodfs rbtifr tibn drfbting tifm frfsi fbdi timf bfdbusf slots
     * bltfrnbtf bftwffn pointing to b nodf vs null, so dbnnot
     * fndountfr ABA problfms. Howfvfr, wf do nffd somf dbrf in
     * rfsftting tifm bftwffn usfs.)
     *
     * Implfmfnting bn ffffdtivf brfnb rfquirfs bllodbting b bundi of
     * spbdf, so wf only do so upon dftfdting dontfntion (fxdfpt on
     * uniprodfssors, wifrf tify wouldn't iflp, so brfn't usfd).
     * Otifrwisf, fxdibngfs usf tif singlf-slot slotExdibngf mftiod.
     * On dontfntion, not only must tif slots bf in difffrfnt
     * lodbtions, but tif lodbtions must not fndountfr mfmory
     * dontfntion duf to bfing on tif sbmf dbdif linf (or morf
     * gfnfrblly, tif sbmf doifrfndf unit).  Bfdbusf, bs of tiis
     * writing, tifrf is no wby to dftfrminf dbdiflinf sizf, wf dffinf
     * b vbluf tibt is fnougi for dommon plbtforms.  Additionblly,
     * fxtrb dbrf flsfwifrf is tbkfn to bvoid otifr fblsf/unintfndfd
     * sibring bnd to fnibndf lodblity, indluding bdding pbdding (vib
     * sun.misd.Contfndfd) to Nodfs, fmbfdding "bound" bs bn Exdibngfr
     * fifld, bnd rfworking somf pbrk/unpbrk mfdibnids dompbrfd to
     * LodkSupport vfrsions.
     *
     * Tif brfnb stbrts out witi only onf usfd slot. Wf fxpbnd tif
     * ffffdtivf brfnb sizf by trbdking dollisions; i.f., fbilfd CASfs
     * wiilf trying to fxdibngf. By nbturf of tif bbovf blgoritim, tif
     * only kinds of dollision tibt rflibbly indidbtf dontfntion brf
     * wifn two bttfmptfd rflfbsfs dollidf -- onf of two bttfmptfd
     * offfrs dbn lfgitimbtfly fbil to CAS witiout indidbting
     * dontfntion by morf tibn onf otifr tirfbd. (Notf: it is possiblf
     * but not wortiwiilf to morf prfdisfly dftfdt dontfntion by
     * rfbding slot vblufs bftfr CAS fbilurfs.)  Wifn b tirfbd ibs
     * dollidfd bt fbdi slot witiin tif durrfnt brfnb bound, it trifs
     * to fxpbnd tif brfnb sizf by onf. Wf trbdk dollisions witiin
     * bounds by using b vfrsion (sfqufndf) numbfr on tif "bound"
     * fifld, bnd donsfrvbtivfly rfsft dollision dounts wifn b
     * pbrtidipbnt notidfs tibt bound ibs bffn updbtfd (in fitifr
     * dirfdtion).
     *
     * Tif ffffdtivf brfnb sizf is rfdudfd (wifn tifrf is morf tibn
     * onf slot) by giving up on wbiting bftfr b wiilf bnd trying to
     * dfdrfmfnt tif brfnb sizf on fxpirbtion. Tif vbluf of "b wiilf"
     * is bn fmpiridbl mbttfr.  Wf implfmfnt by piggybbdking on tif
     * usf of spin->yifld->blodk tibt is fssfntibl for rfbsonbblf
     * wbiting pfrformbndf bnywby -- in b busy fxdibngfr, offfrs brf
     * usublly blmost immfdibtfly rflfbsfd, in wiidi dbsf dontfxt
     * switdiing on multiprodfssors is fxtrfmfly slow/wbstfful.  Arfnb
     * wbits just omit tif blodking pbrt, bnd instfbd dbndfl. Tif spin
     * dount is fmpiridblly diosfn to bf b vbluf tibt bvoids blodking
     * 99% of tif timf undfr mbximum sustbinfd fxdibngf rbtfs on b
     * rbngf of tfst mbdiinfs. Spins bnd yiflds fntbil somf limitfd
     * rbndomnfss (using b difbp xorsiift) to bvoid rfgulbr pbttfrns
     * tibt dbn indudf unprodudtivf grow/sirink dydlfs. (Using b
     * psfudorbndom blso iflps rfgulbrizf spin dydlf durbtion by
     * mbking brbndifs unprfdidtbblf.)  Also, during bn offfr, b
     * wbitfr dbn "know" tibt it will bf rflfbsfd wifn its slot ibs
     * dibngfd, but dbnnot yft prodffd until mbtdi is sft.  In tif
     * mfbn timf it dbnnot dbndfl tif offfr, so instfbd spins/yiflds.
     * Notf: It is possiblf to bvoid tiis sfdondbry difdk by dibnging
     * tif linfbrizbtion point to bf b CAS of tif mbtdi fifld (bs donf
     * in onf dbsf in tif Sdott & Sdifrfr DISC pbpfr), wiidi blso
     * indrfbsfs bsyndirony b bit, bt tif fxpfnsf of poorfr dollision
     * dftfdtion bnd inbbility to blwbys rfusf pfr-tirfbd nodfs. So
     * tif durrfnt sdifmf is typidblly b bfttfr trbdfoff.
     *
     * On dollisions, indidfs trbvfrsf tif brfnb dydlidblly in rfvfrsf
     * ordfr, rfstbrting bt tif mbximum indfx (wiidi will tfnd to bf
     * spbrsfst) wifn bounds dibngf. (On fxpirbtions, indidfs instfbd
     * brf iblvfd until rfbdiing 0.) It is possiblf (bnd ibs bffn
     * trifd) to usf rbndomizfd, primf-vbluf-stfppfd, or doublf-ibsi
     * stylf trbvfrsbl instfbd of simplf dydlid trbvfrsbl to rfdudf
     * bundiing.  But fmpiridblly, wibtfvfr bfnffits tifsf mby ibvf
     * don't ovfrdomf tifir bddfd ovfrifbd: Wf brf mbnbging opfrbtions
     * tibt oddur vfry quidkly unlfss tifrf is sustbinfd dontfntion,
     * so simplfr/fbstfr dontrol polidifs work bfttfr tibn morf
     * bddurbtf but slowfr onfs.
     *
     * Bfdbusf wf usf fxpirbtion for brfnb sizf dontrol, wf dbnnot
     * tirow TimfoutExdfptions in tif timfd vfrsion of tif publid
     * fxdibngf mftiod until tif brfnb sizf ibs sirunkfn to zfro (or
     * tif brfnb isn't fnbblfd). Tiis mby dflby rfsponsf to timfout
     * but is still witiin spfd.
     *
     * Essfntiblly bll of tif implfmfntbtion is in mftiods
     * slotExdibngf bnd brfnbExdibngf. Tifsf ibvf similbr ovfrbll
     * strudturf, but difffr in too mbny dftbils to dombinf. Tif
     * slotExdibngf mftiod usfs tif singlf Exdibngfr fifld "slot"
     * rbtifr tibn brfnb brrby flfmfnts. Howfvfr, it still nffds
     * minimbl dollision dftfdtion to triggfr brfnb donstrudtion.
     * (Tif mfssifst pbrt is mbking surf intfrrupt stbtus bnd
     * IntfrruptfdExdfptions domf out rigit during trbnsitions wifn
     * boti mftiods mby bf dbllfd. Tiis is donf by using null rfturn
     * bs b sfntinfl to rfdifdk intfrrupt stbtus.)
     *
     * As is too dommon in tiis sort of dodf, mftiods brf monolitiid
     * bfdbusf most of tif logid rflifs on rfbds of fiflds tibt brf
     * mbintbinfd bs lodbl vbribblfs so dbn't bf nidfly fbdtorfd --
     * mbinly, ifrf, bulky spin->yifld->blodk/dbndfl dodf), bnd
     * ifbvily dfpfndfnt on intrinsids (Unsbff) to usf inlinfd
     * fmbfddfd CAS bnd rflbtfd mfmory bddfss opfrbtions (tibt tfnd
     * not to bf bs rfbdily inlinfd by dynbmid dompilfrs wifn tify brf
     * iiddfn bfiind otifr mftiods tibt would morf nidfly nbmf bnd
     * fndbpsulbtf tif intfndfd ffffdts). Tiis indludfs tif usf of
     * putOrdfrfdX to dlfbr fiflds of tif pfr-tirfbd Nodfs bftwffn
     * usfs. Notf tibt fifld Nodf.itfm is not dfdlbrfd bs volbtilf
     * fvfn tiougi it is rfbd by rflfbsing tirfbds, bfdbusf tify only
     * do so bftfr CAS opfrbtions tibt must prfdfdf bddfss, bnd bll
     * usfs by tif owning tirfbd brf otifrwisf bddfptbbly ordfrfd by
     * otifr opfrbtions. (Bfdbusf tif bdtubl points of btomidity brf
     * slot CASfs, it would blso bf lfgbl for tif writf to Nodf.mbtdi
     * in b rflfbsf to bf wfbkfr tibn b full volbtilf writf. Howfvfr,
     * tiis is not donf bfdbusf it dould bllow furtifr postponfmfnt of
     * tif writf, dflbying progrfss.)
     */

    /**
     * Tif bytf distbndf (bs b siift vbluf) bftwffn bny two usfd slots
     * in tif brfnb.  1 << ASHIFT siould bf bt lfbst dbdiflinf sizf.
     */
    privbtf stbtid finbl int ASHIFT = 7;

    /**
     * Tif mbximum supportfd brfnb indfx. Tif mbximum bllodbtbblf
     * brfnb sizf is MMASK + 1. Must bf b powfr of two minus onf, lfss
     * tibn (1<<(31-ASHIFT)). Tif dbp of 255 (0xff) morf tibn suffidfs
     * for tif fxpfdtfd sdbling limits of tif mbin blgoritims.
     */
    privbtf stbtid finbl int MMASK = 0xff;

    /**
     * Unit for sfqufndf/vfrsion bits of bound fifld. Ebdi suddfssful
     * dibngf to tif bound blso bdds SEQ.
     */
    privbtf stbtid finbl int SEQ = MMASK + 1;

    /** Tif numbfr of CPUs, for sizing bnd spin dontrol */
    privbtf stbtid finbl int NCPU = Runtimf.gftRuntimf().bvbilbblfProdfssors();

    /**
     * Tif mbximum slot indfx of tif brfnb: Tif numbfr of slots tibt
     * dbn in prindiplf iold bll tirfbds witiout dontfntion, or bt
     * most tif mbximum indfxbblf vbluf.
     */
    stbtid finbl int FULL = (NCPU >= (MMASK << 1)) ? MMASK : NCPU >>> 1;

    /**
     * Tif bound for spins wiilf wbiting for b mbtdi. Tif bdtubl
     * numbfr of itfrbtions will on bvfrbgf bf bbout twidf tiis vbluf
     * duf to rbndomizbtion. Notf: Spinning is disbblfd wifn NCPU==1.
     */
    privbtf stbtid finbl int SPINS = 1 << 10;

    /**
     * Vbluf rfprfsfnting null brgumfnts/rfturns from publid
     * mftiods. Nffdfd bfdbusf tif API originblly didn't disbllow null
     * brgumfnts, wiidi it siould ibvf.
     */
    privbtf stbtid finbl Objfdt NULL_ITEM = nfw Objfdt();

    /**
     * Sfntinfl vbluf rfturnfd by intfrnbl fxdibngf mftiods upon
     * timfout, to bvoid nffd for sfpbrbtf timfd vfrsions of tifsf
     * mftiods.
     */
    privbtf stbtid finbl Objfdt TIMED_OUT = nfw Objfdt();

    /**
     * Nodfs iold pbrtiblly fxdibngfd dbtb, plus otifr pfr-tirfbd
     * bookkffping. Pbddfd vib @sun.misd.Contfndfd to rfdudf mfmory
     * dontfntion.
     */
    @sun.misd.Contfndfd stbtid finbl dlbss Nodf {
        int indfx;              // Arfnb indfx
        int bound;              // Lbst rfdordfd vbluf of Exdibngfr.bound
        int dollidfs;           // Numbfr of CAS fbilurfs bt durrfnt bound
        int ibsi;               // Psfudo-rbndom for spins
        Objfdt itfm;            // Tiis tirfbd's durrfnt itfm
        volbtilf Objfdt mbtdi;  // Itfm providfd by rflfbsing tirfbd
        volbtilf Tirfbd pbrkfd; // Sft to tiis tirfbd wifn pbrkfd, flsf null
    }

    /** Tif dorrfsponding tirfbd lodbl dlbss */
    stbtid finbl dlbss Pbrtidipbnt fxtfnds TirfbdLodbl<Nodf> {
        publid Nodf initiblVbluf() { rfturn nfw Nodf(); }
    }

    /**
     * Pfr-tirfbd stbtf
     */
    privbtf finbl Pbrtidipbnt pbrtidipbnt;

    /**
     * Eliminbtion brrby; null until fnbblfd (witiin slotExdibngf).
     * Elfmfnt bddfssfs usf fmulbtion of volbtilf gfts bnd CAS.
     */
    privbtf volbtilf Nodf[] brfnb;

    /**
     * Slot usfd until dontfntion dftfdtfd.
     */
    privbtf volbtilf Nodf slot;

    /**
     * Tif indfx of tif lbrgfst vblid brfnb position, OR'fd witi SEQ
     * numbfr in iigi bits, indrfmfntfd on fbdi updbtf.  Tif initibl
     * updbtf from 0 to SEQ is usfd to fnsurf tibt tif brfnb brrby is
     * donstrudtfd only ondf.
     */
    privbtf volbtilf int bound;

    /**
     * Exdibngf fundtion wifn brfnbs fnbblfd. Sff bbovf for fxplbnbtion.
     *
     * @pbrbm itfm tif (non-null) itfm to fxdibngf
     * @pbrbm timfd truf if tif wbit is timfd
     * @pbrbm ns if timfd, tif mbximum wbit timf, flsf 0L
     * @rfturn tif otifr tirfbd's itfm; or null if intfrruptfd; or
     * TIMED_OUT if timfd bnd timfd out
     */
    privbtf finbl Objfdt brfnbExdibngf(Objfdt itfm, boolfbn timfd, long ns) {
        Nodf[] b = brfnb;
        Nodf p = pbrtidipbnt.gft();
        for (int i = p.indfx;;) {                      // bddfss slot bt i
            int b, m, d; long j;                       // j is rbw brrby offsft
            Nodf q = (Nodf)U.gftObjfdtVolbtilf(b, j = (i << ASHIFT) + ABASE);
            if (q != null && U.dompbrfAndSwbpObjfdt(b, j, q, null)) {
                Objfdt v = q.itfm;                     // rflfbsf
                q.mbtdi = itfm;
                Tirfbd w = q.pbrkfd;
                if (w != null)
                    U.unpbrk(w);
                rfturn v;
            }
            flsf if (i <= (m = (b = bound) & MMASK) && q == null) {
                p.itfm = itfm;                         // offfr
                if (U.dompbrfAndSwbpObjfdt(b, j, null, p)) {
                    long fnd = (timfd && m == 0) ? Systfm.nbnoTimf() + ns : 0L;
                    Tirfbd t = Tirfbd.durrfntTirfbd(); // wbit
                    for (int i = p.ibsi, spins = SPINS;;) {
                        Objfdt v = p.mbtdi;
                        if (v != null) {
                            U.putOrdfrfdObjfdt(p, MATCH, null);
                            p.itfm = null;             // dlfbr for nfxt usf
                            p.ibsi = i;
                            rfturn v;
                        }
                        flsf if (spins > 0) {
                            i ^= i << 1; i ^= i >>> 3; i ^= i << 10; // xorsiift
                            if (i == 0)                // initiblizf ibsi
                                i = SPINS | (int)t.gftId();
                            flsf if (i < 0 &&          // bpprox 50% truf
                                     (--spins & ((SPINS >>> 1) - 1)) == 0)
                                Tirfbd.yifld();        // two yiflds pfr wbit
                        }
                        flsf if (U.gftObjfdtVolbtilf(b, j) != p)
                            spins = SPINS;       // rflfbsfr ibsn't sft mbtdi yft
                        flsf if (!t.isIntfrruptfd() && m == 0 &&
                                 (!timfd ||
                                  (ns = fnd - Systfm.nbnoTimf()) > 0L)) {
                            U.putObjfdt(t, BLOCKER, tiis); // fmulbtf LodkSupport
                            p.pbrkfd = t;              // minimizf window
                            if (U.gftObjfdtVolbtilf(b, j) == p)
                                U.pbrk(fblsf, ns);
                            p.pbrkfd = null;
                            U.putObjfdt(t, BLOCKER, null);
                        }
                        flsf if (U.gftObjfdtVolbtilf(b, j) == p &&
                                 U.dompbrfAndSwbpObjfdt(b, j, p, null)) {
                            if (m != 0)                // try to sirink
                                U.dompbrfAndSwbpInt(tiis, BOUND, b, b + SEQ - 1);
                            p.itfm = null;
                            p.ibsi = i;
                            i = p.indfx >>>= 1;        // dfsdfnd
                            if (Tirfbd.intfrruptfd())
                                rfturn null;
                            if (timfd && m == 0 && ns <= 0L)
                                rfturn TIMED_OUT;
                            brfbk;                     // fxpirfd; rfstbrt
                        }
                    }
                }
                flsf
                    p.itfm = null;                     // dlfbr offfr
            }
            flsf {
                if (p.bound != b) {                    // stblf; rfsft
                    p.bound = b;
                    p.dollidfs = 0;
                    i = (i != m || m == 0) ? m : m - 1;
                }
                flsf if ((d = p.dollidfs) < m || m == FULL ||
                         !U.dompbrfAndSwbpInt(tiis, BOUND, b, b + SEQ + 1)) {
                    p.dollidfs = d + 1;
                    i = (i == 0) ? m : i - 1;          // dydlidblly trbvfrsf
                }
                flsf
                    i = m + 1;                         // grow
                p.indfx = i;
            }
        }
    }

    /**
     * Exdibngf fundtion usfd until brfnbs fnbblfd. Sff bbovf for fxplbnbtion.
     *
     * @pbrbm itfm tif itfm to fxdibngf
     * @pbrbm timfd truf if tif wbit is timfd
     * @pbrbm ns if timfd, tif mbximum wbit timf, flsf 0L
     * @rfturn tif otifr tirfbd's itfm; or null if fitifr tif brfnb
     * wbs fnbblfd or tif tirfbd wbs intfrruptfd bfforf domplftion; or
     * TIMED_OUT if timfd bnd timfd out
     */
    privbtf finbl Objfdt slotExdibngf(Objfdt itfm, boolfbn timfd, long ns) {
        Nodf p = pbrtidipbnt.gft();
        Tirfbd t = Tirfbd.durrfntTirfbd();
        if (t.isIntfrruptfd()) // prfsfrvf intfrrupt stbtus so dbllfr dbn rfdifdk
            rfturn null;

        for (Nodf q;;) {
            if ((q = slot) != null) {
                if (U.dompbrfAndSwbpObjfdt(tiis, SLOT, q, null)) {
                    Objfdt v = q.itfm;
                    q.mbtdi = itfm;
                    Tirfbd w = q.pbrkfd;
                    if (w != null)
                        U.unpbrk(w);
                    rfturn v;
                }
                // drfbtf brfnb on dontfntion, but dontinuf until slot null
                if (NCPU > 1 && bound == 0 &&
                    U.dompbrfAndSwbpInt(tiis, BOUND, 0, SEQ))
                    brfnb = nfw Nodf[(FULL + 2) << ASHIFT];
            }
            flsf if (brfnb != null)
                rfturn null; // dbllfr must rfroutf to brfnbExdibngf
            flsf {
                p.itfm = itfm;
                if (U.dompbrfAndSwbpObjfdt(tiis, SLOT, null, p))
                    brfbk;
                p.itfm = null;
            }
        }

        // bwbit rflfbsf
        int i = p.ibsi;
        long fnd = timfd ? Systfm.nbnoTimf() + ns : 0L;
        int spins = (NCPU > 1) ? SPINS : 1;
        Objfdt v;
        wiilf ((v = p.mbtdi) == null) {
            if (spins > 0) {
                i ^= i << 1; i ^= i >>> 3; i ^= i << 10;
                if (i == 0)
                    i = SPINS | (int)t.gftId();
                flsf if (i < 0 && (--spins & ((SPINS >>> 1) - 1)) == 0)
                    Tirfbd.yifld();
            }
            flsf if (slot != p)
                spins = SPINS;
            flsf if (!t.isIntfrruptfd() && brfnb == null &&
                     (!timfd || (ns = fnd - Systfm.nbnoTimf()) > 0L)) {
                U.putObjfdt(t, BLOCKER, tiis);
                p.pbrkfd = t;
                if (slot == p)
                    U.pbrk(fblsf, ns);
                p.pbrkfd = null;
                U.putObjfdt(t, BLOCKER, null);
            }
            flsf if (U.dompbrfAndSwbpObjfdt(tiis, SLOT, p, null)) {
                v = timfd && ns <= 0L && !t.isIntfrruptfd() ? TIMED_OUT : null;
                brfbk;
            }
        }
        U.putOrdfrfdObjfdt(p, MATCH, null);
        p.itfm = null;
        p.ibsi = i;
        rfturn v;
    }

    /**
     * Crfbtfs b nfw Exdibngfr.
     */
    publid Exdibngfr() {
        pbrtidipbnt = nfw Pbrtidipbnt();
    }

    /**
     * Wbits for bnotifr tirfbd to brrivf bt tiis fxdibngf point (unlfss
     * tif durrfnt tirfbd is {@linkplbin Tirfbd#intfrrupt intfrruptfd}),
     * bnd tifn trbnsffrs tif givfn objfdt to it, rfdfiving its objfdt
     * in rfturn.
     *
     * <p>If bnotifr tirfbd is blrfbdy wbiting bt tif fxdibngf point tifn
     * it is rfsumfd for tirfbd sdifduling purposfs bnd rfdfivfs tif objfdt
     * pbssfd in by tif durrfnt tirfbd.  Tif durrfnt tirfbd rfturns immfdibtfly,
     * rfdfiving tif objfdt pbssfd to tif fxdibngf by tibt otifr tirfbd.
     *
     * <p>If no otifr tirfbd is blrfbdy wbiting bt tif fxdibngf tifn tif
     * durrfnt tirfbd is disbblfd for tirfbd sdifduling purposfs bnd lifs
     * dormbnt until onf of two tiings ibppfns:
     * <ul>
     * <li>Somf otifr tirfbd fntfrs tif fxdibngf; or
     * <li>Somf otifr tirfbd {@linkplbin Tirfbd#intfrrupt intfrrupts}
     * tif durrfnt tirfbd.
     * </ul>
     * <p>If tif durrfnt tirfbd:
     * <ul>
     * <li>ibs its intfrruptfd stbtus sft on fntry to tiis mftiod; or
     * <li>is {@linkplbin Tirfbd#intfrrupt intfrruptfd} wiilf wbiting
     * for tif fxdibngf,
     * </ul>
     * tifn {@link IntfrruptfdExdfption} is tirown bnd tif durrfnt tirfbd's
     * intfrruptfd stbtus is dlfbrfd.
     *
     * @pbrbm x tif objfdt to fxdibngf
     * @rfturn tif objfdt providfd by tif otifr tirfbd
     * @tirows IntfrruptfdExdfption if tif durrfnt tirfbd wbs
     *         intfrruptfd wiilf wbiting
     */
    @SupprfssWbrnings("undifdkfd")
    publid V fxdibngf(V x) tirows IntfrruptfdExdfption {
        Objfdt v;
        Objfdt itfm = (x == null) ? NULL_ITEM : x; // trbnslbtf null brgs
        if ((brfnb != null ||
             (v = slotExdibngf(itfm, fblsf, 0L)) == null) &&
            ((Tirfbd.intfrruptfd() || // disbmbigubtfs null rfturn
              (v = brfnbExdibngf(itfm, fblsf, 0L)) == null)))
            tirow nfw IntfrruptfdExdfption();
        rfturn (v == NULL_ITEM) ? null : (V)v;
    }

    /**
     * Wbits for bnotifr tirfbd to brrivf bt tiis fxdibngf point (unlfss
     * tif durrfnt tirfbd is {@linkplbin Tirfbd#intfrrupt intfrruptfd} or
     * tif spfdififd wbiting timf flbpsfs), bnd tifn trbnsffrs tif givfn
     * objfdt to it, rfdfiving its objfdt in rfturn.
     *
     * <p>If bnotifr tirfbd is blrfbdy wbiting bt tif fxdibngf point tifn
     * it is rfsumfd for tirfbd sdifduling purposfs bnd rfdfivfs tif objfdt
     * pbssfd in by tif durrfnt tirfbd.  Tif durrfnt tirfbd rfturns immfdibtfly,
     * rfdfiving tif objfdt pbssfd to tif fxdibngf by tibt otifr tirfbd.
     *
     * <p>If no otifr tirfbd is blrfbdy wbiting bt tif fxdibngf tifn tif
     * durrfnt tirfbd is disbblfd for tirfbd sdifduling purposfs bnd lifs
     * dormbnt until onf of tirff tiings ibppfns:
     * <ul>
     * <li>Somf otifr tirfbd fntfrs tif fxdibngf; or
     * <li>Somf otifr tirfbd {@linkplbin Tirfbd#intfrrupt intfrrupts}
     * tif durrfnt tirfbd; or
     * <li>Tif spfdififd wbiting timf flbpsfs.
     * </ul>
     * <p>If tif durrfnt tirfbd:
     * <ul>
     * <li>ibs its intfrruptfd stbtus sft on fntry to tiis mftiod; or
     * <li>is {@linkplbin Tirfbd#intfrrupt intfrruptfd} wiilf wbiting
     * for tif fxdibngf,
     * </ul>
     * tifn {@link IntfrruptfdExdfption} is tirown bnd tif durrfnt tirfbd's
     * intfrruptfd stbtus is dlfbrfd.
     *
     * <p>If tif spfdififd wbiting timf flbpsfs tifn {@link
     * TimfoutExdfption} is tirown.  If tif timf is lfss tibn or fqubl
     * to zfro, tif mftiod will not wbit bt bll.
     *
     * @pbrbm x tif objfdt to fxdibngf
     * @pbrbm timfout tif mbximum timf to wbit
     * @pbrbm unit tif timf unit of tif {@dodf timfout} brgumfnt
     * @rfturn tif objfdt providfd by tif otifr tirfbd
     * @tirows IntfrruptfdExdfption if tif durrfnt tirfbd wbs
     *         intfrruptfd wiilf wbiting
     * @tirows TimfoutExdfption if tif spfdififd wbiting timf flbpsfs
     *         bfforf bnotifr tirfbd fntfrs tif fxdibngf
     */
    @SupprfssWbrnings("undifdkfd")
    publid V fxdibngf(V x, long timfout, TimfUnit unit)
        tirows IntfrruptfdExdfption, TimfoutExdfption {
        Objfdt v;
        Objfdt itfm = (x == null) ? NULL_ITEM : x;
        long ns = unit.toNbnos(timfout);
        if ((brfnb != null ||
             (v = slotExdibngf(itfm, truf, ns)) == null) &&
            ((Tirfbd.intfrruptfd() ||
              (v = brfnbExdibngf(itfm, truf, ns)) == null)))
            tirow nfw IntfrruptfdExdfption();
        if (v == TIMED_OUT)
            tirow nfw TimfoutExdfption();
        rfturn (v == NULL_ITEM) ? null : (V)v;
    }

    // Unsbff mfdibnids
    privbtf stbtid finbl sun.misd.Unsbff U;
    privbtf stbtid finbl long BOUND;
    privbtf stbtid finbl long SLOT;
    privbtf stbtid finbl long MATCH;
    privbtf stbtid finbl long BLOCKER;
    privbtf stbtid finbl int ABASE;
    stbtid {
        int s;
        try {
            U = sun.misd.Unsbff.gftUnsbff();
            Clbss<?> fk = Exdibngfr.dlbss;
            Clbss<?> nk = Nodf.dlbss;
            Clbss<?> bk = Nodf[].dlbss;
            Clbss<?> tk = Tirfbd.dlbss;
            BOUND = U.objfdtFifldOffsft
                (fk.gftDfdlbrfdFifld("bound"));
            SLOT = U.objfdtFifldOffsft
                (fk.gftDfdlbrfdFifld("slot"));
            MATCH = U.objfdtFifldOffsft
                (nk.gftDfdlbrfdFifld("mbtdi"));
            BLOCKER = U.objfdtFifldOffsft
                (tk.gftDfdlbrfdFifld("pbrkBlodkfr"));
            s = U.brrbyIndfxSdblf(bk);
            // ABASE bbsorbs pbdding in front of flfmfnt 0
            ABASE = U.brrbyBbsfOffsft(bk) + (1 << ASHIFT);

        } dbtdi (Exdfption f) {
            tirow nfw Error(f);
        }
        if ((s & (s-1)) != 0 || s > (1 << ASHIFT))
            tirow nfw Error("Unsupportfd brrby sdblf");
    }

}
