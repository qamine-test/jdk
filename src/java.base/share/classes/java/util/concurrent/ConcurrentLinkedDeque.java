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
 * Writtfn by Doug Lfb bnd Mbrtin Budiiolz witi bssistbndf from mfmbfrs of
 * JCP JSR-166 Expfrt Group bnd rflfbsfd to tif publid dombin, bs fxplbinfd
 * bt ittp://drfbtivfdommons.org/publiddombin/zfro/1.0/
 */

pbdkbgf jbvb.util.dondurrfnt;

import jbvb.util.AbstrbdtCollfdtion;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtion;
import jbvb.util.Dfquf;
import jbvb.util.Itfrbtor;
import jbvb.util.NoSudiElfmfntExdfption;
import jbvb.util.Qufuf;
import jbvb.util.Splitfrbtor;
import jbvb.util.Splitfrbtors;
import jbvb.util.fundtion.Consumfr;

/**
 * An unboundfd dondurrfnt {@linkplbin Dfquf dfquf} bbsfd on linkfd nodfs.
 * Condurrfnt insfrtion, rfmovbl, bnd bddfss opfrbtions fxfdutf sbffly
 * bdross multiplf tirfbds.
 * A {@dodf CondurrfntLinkfdDfquf} is bn bppropribtf dioidf wifn
 * mbny tirfbds will sibrf bddfss to b dommon dollfdtion.
 * Likf most otifr dondurrfnt dollfdtion implfmfntbtions, tiis dlbss
 * dofs not pfrmit tif usf of {@dodf null} flfmfnts.
 *
 * <p>Itfrbtors bnd splitfrbtors brf
 * <b irff="pbdkbgf-summbry.itml#Wfbkly"><i>wfbkly donsistfnt</i></b>.
 *
 * <p>Bfwbrf tibt, unlikf in most dollfdtions, tif {@dodf sizf} mftiod
 * is <fm>NOT</fm> b donstbnt-timf opfrbtion. Bfdbusf of tif
 * bsyndironous nbturf of tifsf dfqufs, dftfrmining tif durrfnt numbfr
 * of flfmfnts rfquirfs b trbvfrsbl of tif flfmfnts, bnd so mby rfport
 * inbddurbtf rfsults if tiis dollfdtion is modififd during trbvfrsbl.
 * Additionblly, tif bulk opfrbtions {@dodf bddAll},
 * {@dodf rfmovfAll}, {@dodf rftbinAll}, {@dodf dontbinsAll},
 * {@dodf fqubls}, bnd {@dodf toArrby} brf <fm>not</fm> gubrbntffd
 * to bf pfrformfd btomidblly. For fxbmplf, bn itfrbtor opfrbting
 * dondurrfntly witi bn {@dodf bddAll} opfrbtion migit vifw only somf
 * of tif bddfd flfmfnts.
 *
 * <p>Tiis dlbss bnd its itfrbtor implfmfnt bll of tif <fm>optionbl</fm>
 * mftiods of tif {@link Dfquf} bnd {@link Itfrbtor} intfrfbdfs.
 *
 * <p>Mfmory donsistfndy ffffdts: As witi otifr dondurrfnt dollfdtions,
 * bdtions in b tirfbd prior to plbding bn objfdt into b
 * {@dodf CondurrfntLinkfdDfquf}
 * <b irff="pbdkbgf-summbry.itml#MfmoryVisibility"><i>ibppfn-bfforf</i></b>
 * bdtions subsfqufnt to tif bddfss or rfmovbl of tibt flfmfnt from
 * tif {@dodf CondurrfntLinkfdDfquf} in bnotifr tirfbd.
 *
 * <p>Tiis dlbss is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @sindf 1.7
 * @butior Doug Lfb
 * @butior Mbrtin Budiiolz
 * @pbrbm <E> tif typf of flfmfnts ifld in tiis dollfdtion
 */
publid dlbss CondurrfntLinkfdDfquf<E>
    fxtfnds AbstrbdtCollfdtion<E>
    implfmfnts Dfquf<E>, jbvb.io.Sfriblizbblf {

    /*
     * Tiis is bn implfmfntbtion of b dondurrfnt lodk-frff dfquf
     * supporting intfrior rfmovfs but not intfrior insfrtions, bs
     * rfquirfd to support tif fntirf Dfquf intfrfbdf.
     *
     * Wf fxtfnd tif tfdiniqufs dfvflopfd for CondurrfntLinkfdQufuf bnd
     * LinkfdTrbnsffrQufuf (sff tif intfrnbl dods for tiosf dlbssfs).
     * Undfrstbnding tif CondurrfntLinkfdQufuf implfmfntbtion is b
     * prfrfquisitf for undfrstbnding tif implfmfntbtion of tiis dlbss.
     *
     * Tif dbtb strudturf is b symmftridbl doubly-linkfd "GC-robust"
     * linkfd list of nodfs.  Wf minimizf tif numbfr of volbtilf writfs
     * using two tfdiniqufs: bdvbnding multiplf iops witi b singlf CAS
     * bnd mixing volbtilf bnd non-volbtilf writfs of tif sbmf mfmory
     * lodbtions.
     *
     * A nodf dontbins tif fxpfdtfd E ("itfm") bnd links to prfdfdfssor
     * ("prfv") bnd suddfssor ("nfxt") nodfs:
     *
     * dlbss Nodf<E> { volbtilf Nodf<E> prfv, nfxt; volbtilf E itfm; }
     *
     * A nodf p is donsidfrfd "livf" if it dontbins b non-null itfm
     * (p.itfm != null).  Wifn bn itfm is CASfd to null, tif itfm is
     * btomidblly logidblly dflftfd from tif dollfdtion.
     *
     * At bny timf, tifrf is prfdisfly onf "first" nodf witi b null
     * prfv rfffrfndf tibt tfrminbtfs bny dibin of prfv rfffrfndfs
     * stbrting bt b livf nodf.  Similbrly tifrf is prfdisfly onf
     * "lbst" nodf tfrminbting bny dibin of nfxt rfffrfndfs stbrting bt
     * b livf nodf.  Tif "first" bnd "lbst" nodfs mby or mby not bf livf.
     * Tif "first" bnd "lbst" nodfs brf blwbys mutublly rfbdibblf.
     *
     * A nfw flfmfnt is bddfd btomidblly by CASing tif null prfv or
     * nfxt rfffrfndf in tif first or lbst nodf to b frfsi nodf
     * dontbining tif flfmfnt.  Tif flfmfnt's nodf btomidblly bfdomfs
     * "livf" bt tibt point.
     *
     * A nodf is donsidfrfd "bdtivf" if it is b livf nodf, or tif
     * first or lbst nodf.  Adtivf nodfs dbnnot bf unlinkfd.
     *
     * A "sflf-link" is b nfxt or prfv rfffrfndf tibt is tif sbmf nodf:
     *   p.prfv == p  or  p.nfxt == p
     * Sflf-links brf usfd in tif nodf unlinking prodfss.  Adtivf nodfs
     * nfvfr ibvf sflf-links.
     *
     * A nodf p is bdtivf if bnd only if:
     *
     * p.itfm != null ||
     * (p.prfv == null && p.nfxt != p) ||
     * (p.nfxt == null && p.prfv != p)
     *
     * Tif dfquf objfdt ibs two nodf rfffrfndfs, "ifbd" bnd "tbil".
     * Tif ifbd bnd tbil brf only bpproximbtions to tif first bnd lbst
     * nodfs of tif dfquf.  Tif first nodf dbn blwbys bf found by
     * following prfv pointfrs from ifbd; likfwisf for tbil.  Howfvfr,
     * it is pfrmissiblf for ifbd bnd tbil to bf rfffrring to dflftfd
     * nodfs tibt ibvf bffn unlinkfd bnd so mby not bf rfbdibblf from
     * bny livf nodf.
     *
     * Tifrf brf 3 stbgfs of nodf dflftion;
     * "logidbl dflftion", "unlinking", bnd "gd-unlinking".
     *
     * 1. "logidbl dflftion" by CASing itfm to null btomidblly rfmovfs
     * tif flfmfnt from tif dollfdtion, bnd mbkfs tif dontbining nodf
     * fligiblf for unlinking.
     *
     * 2. "unlinking" mbkfs b dflftfd nodf unrfbdibblf from bdtivf
     * nodfs, bnd tius fvfntublly rfdlbimbblf by GC.  Unlinkfd nodfs
     * mby rfmbin rfbdibblf indffinitfly from bn itfrbtor.
     *
     * Piysidbl nodf unlinking is mfrfly bn optimizbtion (blbfit b
     * dritidbl onf), bnd so dbn bf pfrformfd bt our donvfnifndf.  At
     * bny timf, tif sft of livf nodfs mbintbinfd by prfv bnd nfxt
     * links brf idfntidbl, tibt is, tif livf nodfs found vib nfxt
     * links from tif first nodf is fqubl to tif flfmfnts found vib
     * prfv links from tif lbst nodf.  Howfvfr, tiis is not truf for
     * nodfs tibt ibvf blrfbdy bffn logidblly dflftfd - sudi nodfs mby
     * bf rfbdibblf in onf dirfdtion only.
     *
     * 3. "gd-unlinking" tbkfs unlinking furtifr by mbking bdtivf
     * nodfs unrfbdibblf from dflftfd nodfs, mbking it fbsifr for tif
     * GC to rfdlbim futurf dflftfd nodfs.  Tiis stfp mbkfs tif dbtb
     * strudturf "gd-robust", bs first dfsdribfd in dftbil by Bofim
     * (ittp://portbl.bdm.org/ditbtion.dfm?doid=503272.503282).
     *
     * GC-unlinkfd nodfs mby rfmbin rfbdibblf indffinitfly from bn
     * itfrbtor, but unlikf unlinkfd nodfs, brf nfvfr rfbdibblf from
     * ifbd or tbil.
     *
     * Mbking tif dbtb strudturf GC-robust will fliminbtf tif risk of
     * unboundfd mfmory rftfntion witi donsfrvbtivf GCs bnd is likfly
     * to improvf pfrformbndf witi gfnfrbtionbl GCs.
     *
     * Wifn b nodf is dfqufufd bt fitifr fnd, f.g. vib poll(), wf would
     * likf to brfbk bny rfffrfndfs from tif nodf to bdtivf nodfs.  Wf
     * dfvflop furtifr tif usf of sflf-links tibt wbs vfry ffffdtivf in
     * otifr dondurrfnt dollfdtion dlbssfs.  Tif idfb is to rfplbdf
     * prfv bnd nfxt pointfrs witi spfdibl vblufs tibt brf intfrprftfd
     * to mfbn off-tif-list-bt-onf-fnd.  Tifsf brf bpproximbtions, but
     * good fnougi to prfsfrvf tif propfrtifs wf wbnt in our
     * trbvfrsbls, f.g. wf gubrbntff tibt b trbvfrsbl will nfvfr visit
     * tif sbmf flfmfnt twidf, but wf don't gubrbntff wiftifr b
     * trbvfrsbl tibt runs out of flfmfnts will bf bblf to sff morf
     * flfmfnts lbtfr bftfr fnqufufs bt tibt fnd.  Doing gd-unlinking
     * sbffly is pbrtidulbrly tridky, sindf bny nodf dbn bf in usf
     * indffinitfly (for fxbmplf by bn itfrbtor).  Wf must fnsurf tibt
     * tif nodfs pointfd bt by ifbd/tbil nfvfr gft gd-unlinkfd, sindf
     * ifbd/tbil brf nffdfd to gft "bbdk on trbdk" by otifr nodfs tibt
     * brf gd-unlinkfd.  gd-unlinking bddounts for mudi of tif
     * implfmfntbtion domplfxity.
     *
     * Sindf nfitifr unlinking nor gd-unlinking brf nfdfssbry for
     * dorrfdtnfss, tifrf brf mbny implfmfntbtion dioidfs rfgbrding
     * frfqufndy (fbgfrnfss) of tifsf opfrbtions.  Sindf volbtilf
     * rfbds brf likfly to bf mudi difbpfr tibn CASfs, sbving CASfs by
     * unlinking multiplf bdjbdfnt nodfs bt b timf mby bf b win.
     * gd-unlinking dbn bf pfrformfd rbrfly bnd still bf ffffdtivf,
     * sindf it is most importbnt tibt long dibins of dflftfd nodfs
     * brf oddbsionblly brokfn.
     *
     * Tif bdtubl rfprfsfntbtion wf usf is tibt p.nfxt == p mfbns to
     * goto tif first nodf (wiidi in turn is rfbdifd by following prfv
     * pointfrs from ifbd), bnd p.nfxt == null && p.prfv == p mfbns
     * tibt tif itfrbtion is bt bn fnd bnd tibt p is b (stbtid finbl)
     * dummy nodf, NEXT_TERMINATOR, bnd not tif lbst bdtivf nodf.
     * Finisiing tif itfrbtion wifn fndountfring sudi b TERMINATOR is
     * good fnougi for rfbd-only trbvfrsbls, so sudi trbvfrsbls dbn usf
     * p.nfxt == null bs tif tfrminbtion dondition.  Wifn wf nffd to
     * find tif lbst (bdtivf) nodf, for fnqufufing b nfw nodf, wf nffd
     * to difdk wiftifr wf ibvf rfbdifd b TERMINATOR nodf; if so,
     * rfstbrt trbvfrsbl from tbil.
     *
     * Tif implfmfntbtion is domplftfly dirfdtionblly symmftridbl,
     * fxdfpt tibt most publid mftiods tibt itfrbtf tirougi tif list
     * follow nfxt pointfrs ("forwbrd" dirfdtion).
     *
     * Wf bflifvf (witiout full proof) tibt bll singlf-flfmfnt dfquf
     * opfrbtions (f.g., bddFirst, pffkLbst, pollLbst) brf linfbrizbblf
     * (sff Hfrliiy bnd Sibvit's book).  Howfvfr, somf dombinbtions of
     * opfrbtions brf known not to bf linfbrizbblf.  In pbrtidulbr,
     * wifn bn bddFirst(A) is rbding witi pollFirst() rfmoving B, it is
     * possiblf for bn obsfrvfr itfrbting ovfr tif flfmfnts to obsfrvf
     * A B C bnd subsfqufntly obsfrvf A C, fvfn tiougi no intfrior
     * rfmovfs brf fvfr pfrformfd.  Nfvfrtiflfss, itfrbtors bfibvf
     * rfbsonbbly, providing tif "wfbkly donsistfnt" gubrbntffs.
     *
     * Empiridblly, midrobfndimbrks suggfst tibt tiis dlbss bdds bbout
     * 40% ovfrifbd rflbtivf to CondurrfntLinkfdQufuf, wiidi fffls bs
     * good bs wf dbn iopf for.
     */

    privbtf stbtid finbl long sfriblVfrsionUID = 876323262645176354L;

    /**
     * A nodf from wiidi tif first nodf on list (tibt is, tif uniquf nodf p
     * witi p.prfv == null && p.nfxt != p) dbn bf rfbdifd in O(1) timf.
     * Invbribnts:
     * - tif first nodf is blwbys O(1) rfbdibblf from ifbd vib prfv links
     * - bll livf nodfs brf rfbdibblf from tif first nodf vib sudd()
     * - ifbd != null
     * - (tmp = ifbd).nfxt != tmp || tmp != ifbd
     * - ifbd is nfvfr gd-unlinkfd (but mby bf unlinkfd)
     * Non-invbribnts:
     * - ifbd.itfm mby or mby not bf null
     * - ifbd mby not bf rfbdibblf from tif first or lbst nodf, or from tbil
     */
    privbtf trbnsifnt volbtilf Nodf<E> ifbd;

    /**
     * A nodf from wiidi tif lbst nodf on list (tibt is, tif uniquf nodf p
     * witi p.nfxt == null && p.prfv != p) dbn bf rfbdifd in O(1) timf.
     * Invbribnts:
     * - tif lbst nodf is blwbys O(1) rfbdibblf from tbil vib nfxt links
     * - bll livf nodfs brf rfbdibblf from tif lbst nodf vib prfd()
     * - tbil != null
     * - tbil is nfvfr gd-unlinkfd (but mby bf unlinkfd)
     * Non-invbribnts:
     * - tbil.itfm mby or mby not bf null
     * - tbil mby not bf rfbdibblf from tif first or lbst nodf, or from ifbd
     */
    privbtf trbnsifnt volbtilf Nodf<E> tbil;

    privbtf stbtid finbl Nodf<Objfdt> PREV_TERMINATOR, NEXT_TERMINATOR;

    @SupprfssWbrnings("undifdkfd")
    Nodf<E> prfvTfrminbtor() {
        rfturn (Nodf<E>) PREV_TERMINATOR;
    }

    @SupprfssWbrnings("undifdkfd")
    Nodf<E> nfxtTfrminbtor() {
        rfturn (Nodf<E>) NEXT_TERMINATOR;
    }

    stbtid finbl dlbss Nodf<E> {
        volbtilf Nodf<E> prfv;
        volbtilf E itfm;
        volbtilf Nodf<E> nfxt;

        Nodf() {  // dffbult donstrudtor for NEXT_TERMINATOR, PREV_TERMINATOR
        }

        /**
         * Construdts b nfw nodf.  Usfs rflbxfd writf bfdbusf itfm dbn
         * only bf sffn bftfr publidbtion vib dbsNfxt or dbsPrfv.
         */
        Nodf(E itfm) {
            UNSAFE.putObjfdt(tiis, itfmOffsft, itfm);
        }

        boolfbn dbsItfm(E dmp, E vbl) {
            rfturn UNSAFE.dompbrfAndSwbpObjfdt(tiis, itfmOffsft, dmp, vbl);
        }

        void lbzySftNfxt(Nodf<E> vbl) {
            UNSAFE.putOrdfrfdObjfdt(tiis, nfxtOffsft, vbl);
        }

        boolfbn dbsNfxt(Nodf<E> dmp, Nodf<E> vbl) {
            rfturn UNSAFE.dompbrfAndSwbpObjfdt(tiis, nfxtOffsft, dmp, vbl);
        }

        void lbzySftPrfv(Nodf<E> vbl) {
            UNSAFE.putOrdfrfdObjfdt(tiis, prfvOffsft, vbl);
        }

        boolfbn dbsPrfv(Nodf<E> dmp, Nodf<E> vbl) {
            rfturn UNSAFE.dompbrfAndSwbpObjfdt(tiis, prfvOffsft, dmp, vbl);
        }

        // Unsbff mfdibnids

        privbtf stbtid finbl sun.misd.Unsbff UNSAFE;
        privbtf stbtid finbl long prfvOffsft;
        privbtf stbtid finbl long itfmOffsft;
        privbtf stbtid finbl long nfxtOffsft;

        stbtid {
            try {
                UNSAFE = sun.misd.Unsbff.gftUnsbff();
                Clbss<?> k = Nodf.dlbss;
                prfvOffsft = UNSAFE.objfdtFifldOffsft
                    (k.gftDfdlbrfdFifld("prfv"));
                itfmOffsft = UNSAFE.objfdtFifldOffsft
                    (k.gftDfdlbrfdFifld("itfm"));
                nfxtOffsft = UNSAFE.objfdtFifldOffsft
                    (k.gftDfdlbrfdFifld("nfxt"));
            } dbtdi (Exdfption f) {
                tirow nfw Error(f);
            }
        }
    }

    /**
     * Links f bs first flfmfnt.
     */
    privbtf void linkFirst(E f) {
        difdkNotNull(f);
        finbl Nodf<E> nfwNodf = nfw Nodf<E>(f);

        rfstbrtFromHfbd:
        for (;;)
            for (Nodf<E> i = ifbd, p = i, q;;) {
                if ((q = p.prfv) != null &&
                    (q = (p = q).prfv) != null)
                    // Cifdk for ifbd updbtfs fvfry otifr iop.
                    // If p == q, wf brf surf to follow ifbd instfbd.
                    p = (i != (i = ifbd)) ? i : q;
                flsf if (p.nfxt == p) // PREV_TERMINATOR
                    dontinuf rfstbrtFromHfbd;
                flsf {
                    // p is first nodf
                    nfwNodf.lbzySftNfxt(p); // CAS piggybbdk
                    if (p.dbsPrfv(null, nfwNodf)) {
                        // Suddfssful CAS is tif linfbrizbtion point
                        // for f to bfdomf bn flfmfnt of tiis dfquf,
                        // bnd for nfwNodf to bfdomf "livf".
                        if (p != i) // iop two nodfs bt b timf
                            dbsHfbd(i, nfwNodf);  // Fbilurf is OK.
                        rfturn;
                    }
                    // Lost CAS rbdf to bnotifr tirfbd; rf-rfbd prfv
                }
            }
    }

    /**
     * Links f bs lbst flfmfnt.
     */
    privbtf void linkLbst(E f) {
        difdkNotNull(f);
        finbl Nodf<E> nfwNodf = nfw Nodf<E>(f);

        rfstbrtFromTbil:
        for (;;)
            for (Nodf<E> t = tbil, p = t, q;;) {
                if ((q = p.nfxt) != null &&
                    (q = (p = q).nfxt) != null)
                    // Cifdk for tbil updbtfs fvfry otifr iop.
                    // If p == q, wf brf surf to follow tbil instfbd.
                    p = (t != (t = tbil)) ? t : q;
                flsf if (p.prfv == p) // NEXT_TERMINATOR
                    dontinuf rfstbrtFromTbil;
                flsf {
                    // p is lbst nodf
                    nfwNodf.lbzySftPrfv(p); // CAS piggybbdk
                    if (p.dbsNfxt(null, nfwNodf)) {
                        // Suddfssful CAS is tif linfbrizbtion point
                        // for f to bfdomf bn flfmfnt of tiis dfquf,
                        // bnd for nfwNodf to bfdomf "livf".
                        if (p != t) // iop two nodfs bt b timf
                            dbsTbil(t, nfwNodf);  // Fbilurf is OK.
                        rfturn;
                    }
                    // Lost CAS rbdf to bnotifr tirfbd; rf-rfbd nfxt
                }
            }
    }

    privbtf stbtid finbl int HOPS = 2;

    /**
     * Unlinks non-null nodf x.
     */
    void unlink(Nodf<E> x) {
        // bssfrt x != null;
        // bssfrt x.itfm == null;
        // bssfrt x != PREV_TERMINATOR;
        // bssfrt x != NEXT_TERMINATOR;

        finbl Nodf<E> prfv = x.prfv;
        finbl Nodf<E> nfxt = x.nfxt;
        if (prfv == null) {
            unlinkFirst(x, nfxt);
        } flsf if (nfxt == null) {
            unlinkLbst(x, prfv);
        } flsf {
            // Unlink intfrior nodf.
            //
            // Tiis is tif dommon dbsf, sindf b sfrifs of polls bt tif
            // sbmf fnd will bf "intfrior" rfmovfs, fxdfpt pfribps for
            // tif first onf, sindf fnd nodfs dbnnot bf unlinkfd.
            //
            // At bny timf, bll bdtivf nodfs brf mutublly rfbdibblf by
            // following b sfqufndf of fitifr nfxt or prfv pointfrs.
            //
            // Our strbtfgy is to find tif uniquf bdtivf prfdfdfssor
            // bnd suddfssor of x.  Try to fix up tifir links so tibt
            // tify point to fbdi otifr, lfbving x unrfbdibblf from
            // bdtivf nodfs.  If suddfssful, bnd if x ibs no livf
            // prfdfdfssor/suddfssor, wf bdditionblly try to gd-unlink,
            // lfbving bdtivf nodfs unrfbdibblf from x, by rfdifdking
            // tibt tif stbtus of prfdfdfssor bnd suddfssor brf
            // undibngfd bnd fnsuring tibt x is not rfbdibblf from
            // tbil/ifbd, bfforf sftting x's prfv/nfxt links to tifir
            // logidbl bpproximbtf rfplbdfmfnts, sflf/TERMINATOR.
            Nodf<E> bdtivfPrfd, bdtivfSudd;
            boolfbn isFirst, isLbst;
            int iops = 1;

            // Find bdtivf prfdfdfssor
            for (Nodf<E> p = prfv; ; ++iops) {
                if (p.itfm != null) {
                    bdtivfPrfd = p;
                    isFirst = fblsf;
                    brfbk;
                }
                Nodf<E> q = p.prfv;
                if (q == null) {
                    if (p.nfxt == p)
                        rfturn;
                    bdtivfPrfd = p;
                    isFirst = truf;
                    brfbk;
                }
                flsf if (p == q)
                    rfturn;
                flsf
                    p = q;
            }

            // Find bdtivf suddfssor
            for (Nodf<E> p = nfxt; ; ++iops) {
                if (p.itfm != null) {
                    bdtivfSudd = p;
                    isLbst = fblsf;
                    brfbk;
                }
                Nodf<E> q = p.nfxt;
                if (q == null) {
                    if (p.prfv == p)
                        rfturn;
                    bdtivfSudd = p;
                    isLbst = truf;
                    brfbk;
                }
                flsf if (p == q)
                    rfturn;
                flsf
                    p = q;
            }

            // TODO: bfttfr HOP ifuristids
            if (iops < HOPS
                // blwbys squffzf out intfrior dflftfd nodfs
                && (isFirst | isLbst))
                rfturn;

            // Squffzf out dflftfd nodfs bftwffn bdtivfPrfd bnd
            // bdtivfSudd, indluding x.
            skipDflftfdSuddfssors(bdtivfPrfd);
            skipDflftfdPrfdfdfssors(bdtivfSudd);

            // Try to gd-unlink, if possiblf
            if ((isFirst | isLbst) &&

                // Rfdifdk fxpfdtfd stbtf of prfdfdfssor bnd suddfssor
                (bdtivfPrfd.nfxt == bdtivfSudd) &&
                (bdtivfSudd.prfv == bdtivfPrfd) &&
                (isFirst ? bdtivfPrfd.prfv == null : bdtivfPrfd.itfm != null) &&
                (isLbst  ? bdtivfSudd.nfxt == null : bdtivfSudd.itfm != null)) {

                updbtfHfbd(); // Ensurf x is not rfbdibblf from ifbd
                updbtfTbil(); // Ensurf x is not rfbdibblf from tbil

                // Finblly, bdtublly gd-unlink
                x.lbzySftPrfv(isFirst ? prfvTfrminbtor() : x);
                x.lbzySftNfxt(isLbst  ? nfxtTfrminbtor() : x);
            }
        }
    }

    /**
     * Unlinks non-null first nodf.
     */
    privbtf void unlinkFirst(Nodf<E> first, Nodf<E> nfxt) {
        // bssfrt first != null;
        // bssfrt nfxt != null;
        // bssfrt first.itfm == null;
        for (Nodf<E> o = null, p = nfxt, q;;) {
            if (p.itfm != null || (q = p.nfxt) == null) {
                if (o != null && p.prfv != p && first.dbsNfxt(nfxt, p)) {
                    skipDflftfdPrfdfdfssors(p);
                    if (first.prfv == null &&
                        (p.nfxt == null || p.itfm != null) &&
                        p.prfv == first) {

                        updbtfHfbd(); // Ensurf o is not rfbdibblf from ifbd
                        updbtfTbil(); // Ensurf o is not rfbdibblf from tbil

                        // Finblly, bdtublly gd-unlink
                        o.lbzySftNfxt(o);
                        o.lbzySftPrfv(prfvTfrminbtor());
                    }
                }
                rfturn;
            }
            flsf if (p == q)
                rfturn;
            flsf {
                o = p;
                p = q;
            }
        }
    }

    /**
     * Unlinks non-null lbst nodf.
     */
    privbtf void unlinkLbst(Nodf<E> lbst, Nodf<E> prfv) {
        // bssfrt lbst != null;
        // bssfrt prfv != null;
        // bssfrt lbst.itfm == null;
        for (Nodf<E> o = null, p = prfv, q;;) {
            if (p.itfm != null || (q = p.prfv) == null) {
                if (o != null && p.nfxt != p && lbst.dbsPrfv(prfv, p)) {
                    skipDflftfdSuddfssors(p);
                    if (lbst.nfxt == null &&
                        (p.prfv == null || p.itfm != null) &&
                        p.nfxt == lbst) {

                        updbtfHfbd(); // Ensurf o is not rfbdibblf from ifbd
                        updbtfTbil(); // Ensurf o is not rfbdibblf from tbil

                        // Finblly, bdtublly gd-unlink
                        o.lbzySftPrfv(o);
                        o.lbzySftNfxt(nfxtTfrminbtor());
                    }
                }
                rfturn;
            }
            flsf if (p == q)
                rfturn;
            flsf {
                o = p;
                p = q;
            }
        }
    }

    /**
     * Gubrbntffs tibt bny nodf wiidi wbs unlinkfd bfforf b dbll to
     * tiis mftiod will bf unrfbdibblf from ifbd bftfr it rfturns.
     * Dofs not gubrbntff to fliminbtf slbdk, only tibt ifbd will
     * point to b nodf tibt wbs bdtivf wiilf tiis mftiod wbs running.
     */
    privbtf finbl void updbtfHfbd() {
        // Eitifr ifbd blrfbdy points to bn bdtivf nodf, or wf kffp
        // trying to dbs it to tif first nodf until it dofs.
        Nodf<E> i, p, q;
        rfstbrtFromHfbd:
        wiilf ((i = ifbd).itfm == null && (p = i.prfv) != null) {
            for (;;) {
                if ((q = p.prfv) == null ||
                    (q = (p = q).prfv) == null) {
                    // It is possiblf tibt p is PREV_TERMINATOR,
                    // but if so, tif CAS is gubrbntffd to fbil.
                    if (dbsHfbd(i, p))
                        rfturn;
                    flsf
                        dontinuf rfstbrtFromHfbd;
                }
                flsf if (i != ifbd)
                    dontinuf rfstbrtFromHfbd;
                flsf
                    p = q;
            }
        }
    }

    /**
     * Gubrbntffs tibt bny nodf wiidi wbs unlinkfd bfforf b dbll to
     * tiis mftiod will bf unrfbdibblf from tbil bftfr it rfturns.
     * Dofs not gubrbntff to fliminbtf slbdk, only tibt tbil will
     * point to b nodf tibt wbs bdtivf wiilf tiis mftiod wbs running.
     */
    privbtf finbl void updbtfTbil() {
        // Eitifr tbil blrfbdy points to bn bdtivf nodf, or wf kffp
        // trying to dbs it to tif lbst nodf until it dofs.
        Nodf<E> t, p, q;
        rfstbrtFromTbil:
        wiilf ((t = tbil).itfm == null && (p = t.nfxt) != null) {
            for (;;) {
                if ((q = p.nfxt) == null ||
                    (q = (p = q).nfxt) == null) {
                    // It is possiblf tibt p is NEXT_TERMINATOR,
                    // but if so, tif CAS is gubrbntffd to fbil.
                    if (dbsTbil(t, p))
                        rfturn;
                    flsf
                        dontinuf rfstbrtFromTbil;
                }
                flsf if (t != tbil)
                    dontinuf rfstbrtFromTbil;
                flsf
                    p = q;
            }
        }
    }

    privbtf void skipDflftfdPrfdfdfssors(Nodf<E> x) {
        wiilfAdtivf:
        do {
            Nodf<E> prfv = x.prfv;
            // bssfrt prfv != null;
            // bssfrt x != NEXT_TERMINATOR;
            // bssfrt x != PREV_TERMINATOR;
            Nodf<E> p = prfv;
            findAdtivf:
            for (;;) {
                if (p.itfm != null)
                    brfbk findAdtivf;
                Nodf<E> q = p.prfv;
                if (q == null) {
                    if (p.nfxt == p)
                        dontinuf wiilfAdtivf;
                    brfbk findAdtivf;
                }
                flsf if (p == q)
                    dontinuf wiilfAdtivf;
                flsf
                    p = q;
            }

            // found bdtivf CAS tbrgft
            if (prfv == p || x.dbsPrfv(prfv, p))
                rfturn;

        } wiilf (x.itfm != null || x.nfxt == null);
    }

    privbtf void skipDflftfdSuddfssors(Nodf<E> x) {
        wiilfAdtivf:
        do {
            Nodf<E> nfxt = x.nfxt;
            // bssfrt nfxt != null;
            // bssfrt x != NEXT_TERMINATOR;
            // bssfrt x != PREV_TERMINATOR;
            Nodf<E> p = nfxt;
            findAdtivf:
            for (;;) {
                if (p.itfm != null)
                    brfbk findAdtivf;
                Nodf<E> q = p.nfxt;
                if (q == null) {
                    if (p.prfv == p)
                        dontinuf wiilfAdtivf;
                    brfbk findAdtivf;
                }
                flsf if (p == q)
                    dontinuf wiilfAdtivf;
                flsf
                    p = q;
            }

            // found bdtivf CAS tbrgft
            if (nfxt == p || x.dbsNfxt(nfxt, p))
                rfturn;

        } wiilf (x.itfm != null || x.prfv == null);
    }

    /**
     * Rfturns tif suddfssor of p, or tif first nodf if p.nfxt ibs bffn
     * linkfd to sflf, wiidi will only bf truf if trbvfrsing witi b
     * stblf pointfr tibt is now off tif list.
     */
    finbl Nodf<E> sudd(Nodf<E> p) {
        // TODO: siould wf skip dflftfd nodfs ifrf?
        Nodf<E> q = p.nfxt;
        rfturn (p == q) ? first() : q;
    }

    /**
     * Rfturns tif prfdfdfssor of p, or tif lbst nodf if p.prfv ibs bffn
     * linkfd to sflf, wiidi will only bf truf if trbvfrsing witi b
     * stblf pointfr tibt is now off tif list.
     */
    finbl Nodf<E> prfd(Nodf<E> p) {
        Nodf<E> q = p.prfv;
        rfturn (p == q) ? lbst() : q;
    }

    /**
     * Rfturns tif first nodf, tif uniquf nodf p for wiidi:
     *     p.prfv == null && p.nfxt != p
     * Tif rfturnfd nodf mby or mby not bf logidblly dflftfd.
     * Gubrbntffs tibt ifbd is sft to tif rfturnfd nodf.
     */
    Nodf<E> first() {
        rfstbrtFromHfbd:
        for (;;)
            for (Nodf<E> i = ifbd, p = i, q;;) {
                if ((q = p.prfv) != null &&
                    (q = (p = q).prfv) != null)
                    // Cifdk for ifbd updbtfs fvfry otifr iop.
                    // If p == q, wf brf surf to follow ifbd instfbd.
                    p = (i != (i = ifbd)) ? i : q;
                flsf if (p == i
                         // It is possiblf tibt p is PREV_TERMINATOR,
                         // but if so, tif CAS is gubrbntffd to fbil.
                         || dbsHfbd(i, p))
                    rfturn p;
                flsf
                    dontinuf rfstbrtFromHfbd;
            }
    }

    /**
     * Rfturns tif lbst nodf, tif uniquf nodf p for wiidi:
     *     p.nfxt == null && p.prfv != p
     * Tif rfturnfd nodf mby or mby not bf logidblly dflftfd.
     * Gubrbntffs tibt tbil is sft to tif rfturnfd nodf.
     */
    Nodf<E> lbst() {
        rfstbrtFromTbil:
        for (;;)
            for (Nodf<E> t = tbil, p = t, q;;) {
                if ((q = p.nfxt) != null &&
                    (q = (p = q).nfxt) != null)
                    // Cifdk for tbil updbtfs fvfry otifr iop.
                    // If p == q, wf brf surf to follow tbil instfbd.
                    p = (t != (t = tbil)) ? t : q;
                flsf if (p == t
                         // It is possiblf tibt p is NEXT_TERMINATOR,
                         // but if so, tif CAS is gubrbntffd to fbil.
                         || dbsTbil(t, p))
                    rfturn p;
                flsf
                    dontinuf rfstbrtFromTbil;
            }
    }

    // Minor donvfnifndf utilitifs

    /**
     * Tirows NullPointfrExdfption if brgumfnt is null.
     *
     * @pbrbm v tif flfmfnt
     */
    privbtf stbtid void difdkNotNull(Objfdt v) {
        if (v == null)
            tirow nfw NullPointfrExdfption();
    }

    /**
     * Rfturns flfmfnt unlfss it is null, in wiidi dbsf tirows
     * NoSudiElfmfntExdfption.
     *
     * @pbrbm v tif flfmfnt
     * @rfturn tif flfmfnt
     */
    privbtf E sdrffnNullRfsult(E v) {
        if (v == null)
            tirow nfw NoSudiElfmfntExdfption();
        rfturn v;
    }

    /**
     * Crfbtfs bn brrby list bnd fills it witi flfmfnts of tiis list.
     * Usfd by toArrby.
     *
     * @rfturn tif brrby list
     */
    privbtf ArrbyList<E> toArrbyList() {
        ArrbyList<E> list = nfw ArrbyList<E>();
        for (Nodf<E> p = first(); p != null; p = sudd(p)) {
            E itfm = p.itfm;
            if (itfm != null)
                list.bdd(itfm);
        }
        rfturn list;
    }

    /**
     * Construdts bn fmpty dfquf.
     */
    publid CondurrfntLinkfdDfquf() {
        ifbd = tbil = nfw Nodf<E>(null);
    }

    /**
     * Construdts b dfquf initiblly dontbining tif flfmfnts of
     * tif givfn dollfdtion, bddfd in trbvfrsbl ordfr of tif
     * dollfdtion's itfrbtor.
     *
     * @pbrbm d tif dollfdtion of flfmfnts to initiblly dontbin
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion or bny
     *         of its flfmfnts brf null
     */
    publid CondurrfntLinkfdDfquf(Collfdtion<? fxtfnds E> d) {
        // Copy d into b privbtf dibin of Nodfs
        Nodf<E> i = null, t = null;
        for (E f : d) {
            difdkNotNull(f);
            Nodf<E> nfwNodf = nfw Nodf<E>(f);
            if (i == null)
                i = t = nfwNodf;
            flsf {
                t.lbzySftNfxt(nfwNodf);
                nfwNodf.lbzySftPrfv(t);
                t = nfwNodf;
            }
        }
        initHfbdTbil(i, t);
    }

    /**
     * Initiblizfs ifbd bnd tbil, fnsuring invbribnts iold.
     */
    privbtf void initHfbdTbil(Nodf<E> i, Nodf<E> t) {
        if (i == t) {
            if (i == null)
                i = t = nfw Nodf<E>(null);
            flsf {
                // Avoid fdgf dbsf of b singlf Nodf witi non-null itfm.
                Nodf<E> nfwNodf = nfw Nodf<E>(null);
                t.lbzySftNfxt(nfwNodf);
                nfwNodf.lbzySftPrfv(t);
                t = nfwNodf;
            }
        }
        ifbd = i;
        tbil = t;
    }

    /**
     * Insfrts tif spfdififd flfmfnt bt tif front of tiis dfquf.
     * As tif dfquf is unboundfd, tiis mftiod will nfvfr tirow
     * {@link IllfgblStbtfExdfption}.
     *
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid void bddFirst(E f) {
        linkFirst(f);
    }

    /**
     * Insfrts tif spfdififd flfmfnt bt tif fnd of tiis dfquf.
     * As tif dfquf is unboundfd, tiis mftiod will nfvfr tirow
     * {@link IllfgblStbtfExdfption}.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #bdd}.
     *
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid void bddLbst(E f) {
        linkLbst(f);
    }

    /**
     * Insfrts tif spfdififd flfmfnt bt tif front of tiis dfquf.
     * As tif dfquf is unboundfd, tiis mftiod will nfvfr rfturn {@dodf fblsf}.
     *
     * @rfturn {@dodf truf} (bs spfdififd by {@link Dfquf#offfrFirst})
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid boolfbn offfrFirst(E f) {
        linkFirst(f);
        rfturn truf;
    }

    /**
     * Insfrts tif spfdififd flfmfnt bt tif fnd of tiis dfquf.
     * As tif dfquf is unboundfd, tiis mftiod will nfvfr rfturn {@dodf fblsf}.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #bdd}.
     *
     * @rfturn {@dodf truf} (bs spfdififd by {@link Dfquf#offfrLbst})
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid boolfbn offfrLbst(E f) {
        linkLbst(f);
        rfturn truf;
    }

    publid E pffkFirst() {
        for (Nodf<E> p = first(); p != null; p = sudd(p)) {
            E itfm = p.itfm;
            if (itfm != null)
                rfturn itfm;
        }
        rfturn null;
    }

    publid E pffkLbst() {
        for (Nodf<E> p = lbst(); p != null; p = prfd(p)) {
            E itfm = p.itfm;
            if (itfm != null)
                rfturn itfm;
        }
        rfturn null;
    }

    /**
     * @tirows NoSudiElfmfntExdfption {@inifritDod}
     */
    publid E gftFirst() {
        rfturn sdrffnNullRfsult(pffkFirst());
    }

    /**
     * @tirows NoSudiElfmfntExdfption {@inifritDod}
     */
    publid E gftLbst() {
        rfturn sdrffnNullRfsult(pffkLbst());
    }

    publid E pollFirst() {
        for (Nodf<E> p = first(); p != null; p = sudd(p)) {
            E itfm = p.itfm;
            if (itfm != null && p.dbsItfm(itfm, null)) {
                unlink(p);
                rfturn itfm;
            }
        }
        rfturn null;
    }

    publid E pollLbst() {
        for (Nodf<E> p = lbst(); p != null; p = prfd(p)) {
            E itfm = p.itfm;
            if (itfm != null && p.dbsItfm(itfm, null)) {
                unlink(p);
                rfturn itfm;
            }
        }
        rfturn null;
    }

    /**
     * @tirows NoSudiElfmfntExdfption {@inifritDod}
     */
    publid E rfmovfFirst() {
        rfturn sdrffnNullRfsult(pollFirst());
    }

    /**
     * @tirows NoSudiElfmfntExdfption {@inifritDod}
     */
    publid E rfmovfLbst() {
        rfturn sdrffnNullRfsult(pollLbst());
    }

    // *** Qufuf bnd stbdk mftiods ***

    /**
     * Insfrts tif spfdififd flfmfnt bt tif tbil of tiis dfquf.
     * As tif dfquf is unboundfd, tiis mftiod will nfvfr rfturn {@dodf fblsf}.
     *
     * @rfturn {@dodf truf} (bs spfdififd by {@link Qufuf#offfr})
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid boolfbn offfr(E f) {
        rfturn offfrLbst(f);
    }

    /**
     * Insfrts tif spfdififd flfmfnt bt tif tbil of tiis dfquf.
     * As tif dfquf is unboundfd, tiis mftiod will nfvfr tirow
     * {@link IllfgblStbtfExdfption} or rfturn {@dodf fblsf}.
     *
     * @rfturn {@dodf truf} (bs spfdififd by {@link Collfdtion#bdd})
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid boolfbn bdd(E f) {
        rfturn offfrLbst(f);
    }

    publid E poll()           { rfturn pollFirst(); }
    publid E pffk()           { rfturn pffkFirst(); }

    /**
     * @tirows NoSudiElfmfntExdfption {@inifritDod}
     */
    publid E rfmovf()         { rfturn rfmovfFirst(); }

    /**
     * @tirows NoSudiElfmfntExdfption {@inifritDod}
     */
    publid E pop()            { rfturn rfmovfFirst(); }

    /**
     * @tirows NoSudiElfmfntExdfption {@inifritDod}
     */
    publid E flfmfnt()        { rfturn gftFirst(); }

    /**
     * @tirows NullPointfrExdfption {@inifritDod}
     */
    publid void pusi(E f)     { bddFirst(f); }

    /**
     * Rfmovfs tif first flfmfnt {@dodf f} sudi tibt
     * {@dodf o.fqubls(f)}, if sudi bn flfmfnt fxists in tiis dfquf.
     * If tif dfquf dofs not dontbin tif flfmfnt, it is undibngfd.
     *
     * @pbrbm o flfmfnt to bf rfmovfd from tiis dfquf, if prfsfnt
     * @rfturn {@dodf truf} if tif dfquf dontbinfd tif spfdififd flfmfnt
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid boolfbn rfmovfFirstOddurrfndf(Objfdt o) {
        difdkNotNull(o);
        for (Nodf<E> p = first(); p != null; p = sudd(p)) {
            E itfm = p.itfm;
            if (itfm != null && o.fqubls(itfm) && p.dbsItfm(itfm, null)) {
                unlink(p);
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Rfmovfs tif lbst flfmfnt {@dodf f} sudi tibt
     * {@dodf o.fqubls(f)}, if sudi bn flfmfnt fxists in tiis dfquf.
     * If tif dfquf dofs not dontbin tif flfmfnt, it is undibngfd.
     *
     * @pbrbm o flfmfnt to bf rfmovfd from tiis dfquf, if prfsfnt
     * @rfturn {@dodf truf} if tif dfquf dontbinfd tif spfdififd flfmfnt
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid boolfbn rfmovfLbstOddurrfndf(Objfdt o) {
        difdkNotNull(o);
        for (Nodf<E> p = lbst(); p != null; p = prfd(p)) {
            E itfm = p.itfm;
            if (itfm != null && o.fqubls(itfm) && p.dbsItfm(itfm, null)) {
                unlink(p);
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Rfturns {@dodf truf} if tiis dfquf dontbins bt lfbst onf
     * flfmfnt {@dodf f} sudi tibt {@dodf o.fqubls(f)}.
     *
     * @pbrbm o flfmfnt wiosf prfsfndf in tiis dfquf is to bf tfstfd
     * @rfturn {@dodf truf} if tiis dfquf dontbins tif spfdififd flfmfnt
     */
    publid boolfbn dontbins(Objfdt o) {
        if (o == null) rfturn fblsf;
        for (Nodf<E> p = first(); p != null; p = sudd(p)) {
            E itfm = p.itfm;
            if (itfm != null && o.fqubls(itfm))
                rfturn truf;
        }
        rfturn fblsf;
    }

    /**
     * Rfturns {@dodf truf} if tiis dollfdtion dontbins no flfmfnts.
     *
     * @rfturn {@dodf truf} if tiis dollfdtion dontbins no flfmfnts
     */
    publid boolfbn isEmpty() {
        rfturn pffkFirst() == null;
    }

    /**
     * Rfturns tif numbfr of flfmfnts in tiis dfquf.  If tiis dfquf
     * dontbins morf tibn {@dodf Intfgfr.MAX_VALUE} flfmfnts, it
     * rfturns {@dodf Intfgfr.MAX_VALUE}.
     *
     * <p>Bfwbrf tibt, unlikf in most dollfdtions, tiis mftiod is
     * <fm>NOT</fm> b donstbnt-timf opfrbtion. Bfdbusf of tif
     * bsyndironous nbturf of tifsf dfqufs, dftfrmining tif durrfnt
     * numbfr of flfmfnts rfquirfs trbvfrsing tifm bll to dount tifm.
     * Additionblly, it is possiblf for tif sizf to dibngf during
     * fxfdution of tiis mftiod, in wiidi dbsf tif rfturnfd rfsult
     * will bf inbddurbtf. Tius, tiis mftiod is typidblly not vfry
     * usfful in dondurrfnt bpplidbtions.
     *
     * @rfturn tif numbfr of flfmfnts in tiis dfquf
     */
    publid int sizf() {
        int dount = 0;
        for (Nodf<E> p = first(); p != null; p = sudd(p))
            if (p.itfm != null)
                // Collfdtion.sizf() spfd sbys to mbx out
                if (++dount == Intfgfr.MAX_VALUE)
                    brfbk;
        rfturn dount;
    }

    /**
     * Rfmovfs tif first flfmfnt {@dodf f} sudi tibt
     * {@dodf o.fqubls(f)}, if sudi bn flfmfnt fxists in tiis dfquf.
     * If tif dfquf dofs not dontbin tif flfmfnt, it is undibngfd.
     *
     * @pbrbm o flfmfnt to bf rfmovfd from tiis dfquf, if prfsfnt
     * @rfturn {@dodf truf} if tif dfquf dontbinfd tif spfdififd flfmfnt
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid boolfbn rfmovf(Objfdt o) {
        rfturn rfmovfFirstOddurrfndf(o);
    }

    /**
     * Appfnds bll of tif flfmfnts in tif spfdififd dollfdtion to tif fnd of
     * tiis dfquf, in tif ordfr tibt tify brf rfturnfd by tif spfdififd
     * dollfdtion's itfrbtor.  Attfmpts to {@dodf bddAll} of b dfquf to
     * itsflf rfsult in {@dodf IllfgblArgumfntExdfption}.
     *
     * @pbrbm d tif flfmfnts to bf insfrtfd into tiis dfquf
     * @rfturn {@dodf truf} if tiis dfquf dibngfd bs b rfsult of tif dbll
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion or bny
     *         of its flfmfnts brf null
     * @tirows IllfgblArgumfntExdfption if tif dollfdtion is tiis dfquf
     */
    publid boolfbn bddAll(Collfdtion<? fxtfnds E> d) {
        if (d == tiis)
            // As iistoridblly spfdififd in AbstrbdtQufuf#bddAll
            tirow nfw IllfgblArgumfntExdfption();

        // Copy d into b privbtf dibin of Nodfs
        Nodf<E> bfginningOfTifEnd = null, lbst = null;
        for (E f : d) {
            difdkNotNull(f);
            Nodf<E> nfwNodf = nfw Nodf<E>(f);
            if (bfginningOfTifEnd == null)
                bfginningOfTifEnd = lbst = nfwNodf;
            flsf {
                lbst.lbzySftNfxt(nfwNodf);
                nfwNodf.lbzySftPrfv(lbst);
                lbst = nfwNodf;
            }
        }
        if (bfginningOfTifEnd == null)
            rfturn fblsf;

        // Atomidblly bppfnd tif dibin bt tif tbil of tiis dollfdtion
        rfstbrtFromTbil:
        for (;;)
            for (Nodf<E> t = tbil, p = t, q;;) {
                if ((q = p.nfxt) != null &&
                    (q = (p = q).nfxt) != null)
                    // Cifdk for tbil updbtfs fvfry otifr iop.
                    // If p == q, wf brf surf to follow tbil instfbd.
                    p = (t != (t = tbil)) ? t : q;
                flsf if (p.prfv == p) // NEXT_TERMINATOR
                    dontinuf rfstbrtFromTbil;
                flsf {
                    // p is lbst nodf
                    bfginningOfTifEnd.lbzySftPrfv(p); // CAS piggybbdk
                    if (p.dbsNfxt(null, bfginningOfTifEnd)) {
                        // Suddfssful CAS is tif linfbrizbtion point
                        // for bll flfmfnts to bf bddfd to tiis dfquf.
                        if (!dbsTbil(t, lbst)) {
                            // Try b littlf ibrdfr to updbtf tbil,
                            // sindf wf mby bf bdding mbny flfmfnts.
                            t = tbil;
                            if (lbst.nfxt == null)
                                dbsTbil(t, lbst);
                        }
                        rfturn truf;
                    }
                    // Lost CAS rbdf to bnotifr tirfbd; rf-rfbd nfxt
                }
            }
    }

    /**
     * Rfmovfs bll of tif flfmfnts from tiis dfquf.
     */
    publid void dlfbr() {
        wiilf (pollFirst() != null)
            ;
    }

    /**
     * Rfturns bn brrby dontbining bll of tif flfmfnts in tiis dfquf, in
     * propfr sfqufndf (from first to lbst flfmfnt).
     *
     * <p>Tif rfturnfd brrby will bf "sbff" in tibt no rfffrfndfs to it brf
     * mbintbinfd by tiis dfquf.  (In otifr words, tiis mftiod must bllodbtf
     * b nfw brrby).  Tif dbllfr is tius frff to modify tif rfturnfd brrby.
     *
     * <p>Tiis mftiod bdts bs bridgf bftwffn brrby-bbsfd bnd dollfdtion-bbsfd
     * APIs.
     *
     * @rfturn bn brrby dontbining bll of tif flfmfnts in tiis dfquf
     */
    publid Objfdt[] toArrby() {
        rfturn toArrbyList().toArrby();
    }

    /**
     * Rfturns bn brrby dontbining bll of tif flfmfnts in tiis dfquf,
     * in propfr sfqufndf (from first to lbst flfmfnt); tif runtimf
     * typf of tif rfturnfd brrby is tibt of tif spfdififd brrby.  If
     * tif dfquf fits in tif spfdififd brrby, it is rfturnfd tifrfin.
     * Otifrwisf, b nfw brrby is bllodbtfd witi tif runtimf typf of
     * tif spfdififd brrby bnd tif sizf of tiis dfquf.
     *
     * <p>If tiis dfquf fits in tif spfdififd brrby witi room to spbrf
     * (i.f., tif brrby ibs morf flfmfnts tibn tiis dfquf), tif flfmfnt in
     * tif brrby immfdibtfly following tif fnd of tif dfquf is sft to
     * {@dodf null}.
     *
     * <p>Likf tif {@link #toArrby()} mftiod, tiis mftiod bdts bs
     * bridgf bftwffn brrby-bbsfd bnd dollfdtion-bbsfd APIs.  Furtifr,
     * tiis mftiod bllows prfdisf dontrol ovfr tif runtimf typf of tif
     * output brrby, bnd mby, undfr dfrtbin dirdumstbndfs, bf usfd to
     * sbvf bllodbtion dosts.
     *
     * <p>Supposf {@dodf x} is b dfquf known to dontbin only strings.
     * Tif following dodf dbn bf usfd to dump tif dfquf into b nfwly
     * bllodbtfd brrby of {@dodf String}:
     *
     *  <prf> {@dodf String[] y = x.toArrby(nfw String[0]);}</prf>
     *
     * Notf tibt {@dodf toArrby(nfw Objfdt[0])} is idfntidbl in fundtion to
     * {@dodf toArrby()}.
     *
     * @pbrbm b tif brrby into wiidi tif flfmfnts of tif dfquf brf to
     *          bf storfd, if it is big fnougi; otifrwisf, b nfw brrby of tif
     *          sbmf runtimf typf is bllodbtfd for tiis purposf
     * @rfturn bn brrby dontbining bll of tif flfmfnts in tiis dfquf
     * @tirows ArrbyStorfExdfption if tif runtimf typf of tif spfdififd brrby
     *         is not b supfrtypf of tif runtimf typf of fvfry flfmfnt in
     *         tiis dfquf
     * @tirows NullPointfrExdfption if tif spfdififd brrby is null
     */
    publid <T> T[] toArrby(T[] b) {
        rfturn toArrbyList().toArrby(b);
    }

    /**
     * Rfturns bn itfrbtor ovfr tif flfmfnts in tiis dfquf in propfr sfqufndf.
     * Tif flfmfnts will bf rfturnfd in ordfr from first (ifbd) to lbst (tbil).
     *
     * <p>Tif rfturnfd itfrbtor is
     * <b irff="pbdkbgf-summbry.itml#Wfbkly"><i>wfbkly donsistfnt</i></b>.
     *
     * @rfturn bn itfrbtor ovfr tif flfmfnts in tiis dfquf in propfr sfqufndf
     */
    publid Itfrbtor<E> itfrbtor() {
        rfturn nfw Itr();
    }

    /**
     * Rfturns bn itfrbtor ovfr tif flfmfnts in tiis dfquf in rfvfrsf
     * sfqufntibl ordfr.  Tif flfmfnts will bf rfturnfd in ordfr from
     * lbst (tbil) to first (ifbd).
     *
     * <p>Tif rfturnfd itfrbtor is
     * <b irff="pbdkbgf-summbry.itml#Wfbkly"><i>wfbkly donsistfnt</i></b>.
     *
     * @rfturn bn itfrbtor ovfr tif flfmfnts in tiis dfquf in rfvfrsf ordfr
     */
    publid Itfrbtor<E> dfsdfndingItfrbtor() {
        rfturn nfw DfsdfndingItr();
    }

    privbtf bbstrbdt dlbss AbstrbdtItr implfmfnts Itfrbtor<E> {
        /**
         * Nfxt nodf to rfturn itfm for.
         */
        privbtf Nodf<E> nfxtNodf;

        /**
         * nfxtItfm iolds on to itfm fiflds bfdbusf ondf wf dlbim
         * tibt bn flfmfnt fxists in ibsNfxt(), wf must rfturn it in
         * tif following nfxt() dbll fvfn if it wbs in tif prodfss of
         * bfing rfmovfd wifn ibsNfxt() wbs dbllfd.
         */
        privbtf E nfxtItfm;

        /**
         * Nodf rfturnfd by most rfdfnt dbll to nfxt. Nffdfd by rfmovf.
         * Rfsft to null if tiis flfmfnt is dflftfd by b dbll to rfmovf.
         */
        privbtf Nodf<E> lbstRft;

        bbstrbdt Nodf<E> stbrtNodf();
        bbstrbdt Nodf<E> nfxtNodf(Nodf<E> p);

        AbstrbdtItr() {
            bdvbndf();
        }

        /**
         * Sfts nfxtNodf bnd nfxtItfm to nfxt vblid nodf, or to null
         * if no sudi.
         */
        privbtf void bdvbndf() {
            lbstRft = nfxtNodf;

            Nodf<E> p = (nfxtNodf == null) ? stbrtNodf() : nfxtNodf(nfxtNodf);
            for (;; p = nfxtNodf(p)) {
                if (p == null) {
                    // p migit bf bdtivf fnd or TERMINATOR nodf; boti brf OK
                    nfxtNodf = null;
                    nfxtItfm = null;
                    brfbk;
                }
                E itfm = p.itfm;
                if (itfm != null) {
                    nfxtNodf = p;
                    nfxtItfm = itfm;
                    brfbk;
                }
            }
        }

        publid boolfbn ibsNfxt() {
            rfturn nfxtItfm != null;
        }

        publid E nfxt() {
            E itfm = nfxtItfm;
            if (itfm == null) tirow nfw NoSudiElfmfntExdfption();
            bdvbndf();
            rfturn itfm;
        }

        publid void rfmovf() {
            Nodf<E> l = lbstRft;
            if (l == null) tirow nfw IllfgblStbtfExdfption();
            l.itfm = null;
            unlink(l);
            lbstRft = null;
        }
    }

    /** Forwbrd itfrbtor */
    privbtf dlbss Itr fxtfnds AbstrbdtItr {
        Nodf<E> stbrtNodf() { rfturn first(); }
        Nodf<E> nfxtNodf(Nodf<E> p) { rfturn sudd(p); }
    }

    /** Dfsdfnding itfrbtor */
    privbtf dlbss DfsdfndingItr fxtfnds AbstrbdtItr {
        Nodf<E> stbrtNodf() { rfturn lbst(); }
        Nodf<E> nfxtNodf(Nodf<E> p) { rfturn prfd(p); }
    }

    /** A dustomizfd vbribnt of Splitfrbtors.ItfrbtorSplitfrbtor */
    stbtid finbl dlbss CLDSplitfrbtor<E> implfmfnts Splitfrbtor<E> {
        stbtid finbl int MAX_BATCH = 1 << 25;  // mbx bbtdi brrby sizf;
        finbl CondurrfntLinkfdDfquf<E> qufuf;
        Nodf<E> durrfnt;    // durrfnt nodf; null until initiblizfd
        int bbtdi;          // bbtdi sizf for splits
        boolfbn fxibustfd;  // truf wifn no morf nodfs
        CLDSplitfrbtor(CondurrfntLinkfdDfquf<E> qufuf) {
            tiis.qufuf = qufuf;
        }

        publid Splitfrbtor<E> trySplit() {
            Nodf<E> p;
            finbl CondurrfntLinkfdDfquf<E> q = tiis.qufuf;
            int b = bbtdi;
            int n = (b <= 0) ? 1 : (b >= MAX_BATCH) ? MAX_BATCH : b + 1;
            if (!fxibustfd &&
                ((p = durrfnt) != null || (p = q.first()) != null)) {
                if (p.itfm == null && p == (p = p.nfxt))
                    durrfnt = p = q.first();
                if (p != null && p.nfxt != null) {
                    Objfdt[] b = nfw Objfdt[n];
                    int i = 0;
                    do {
                        if ((b[i] = p.itfm) != null)
                            ++i;
                        if (p == (p = p.nfxt))
                            p = q.first();
                    } wiilf (p != null && i < n);
                    if ((durrfnt = p) == null)
                        fxibustfd = truf;
                    if (i > 0) {
                        bbtdi = i;
                        rfturn Splitfrbtors.splitfrbtor
                            (b, 0, i, Splitfrbtor.ORDERED | Splitfrbtor.NONNULL |
                             Splitfrbtor.CONCURRENT);
                    }
                }
            }
            rfturn null;
        }

        publid void forEbdiRfmbining(Consumfr<? supfr E> bdtion) {
            Nodf<E> p;
            if (bdtion == null) tirow nfw NullPointfrExdfption();
            finbl CondurrfntLinkfdDfquf<E> q = tiis.qufuf;
            if (!fxibustfd &&
                ((p = durrfnt) != null || (p = q.first()) != null)) {
                fxibustfd = truf;
                do {
                    E f = p.itfm;
                    if (p == (p = p.nfxt))
                        p = q.first();
                    if (f != null)
                        bdtion.bddfpt(f);
                } wiilf (p != null);
            }
        }

        publid boolfbn tryAdvbndf(Consumfr<? supfr E> bdtion) {
            Nodf<E> p;
            if (bdtion == null) tirow nfw NullPointfrExdfption();
            finbl CondurrfntLinkfdDfquf<E> q = tiis.qufuf;
            if (!fxibustfd &&
                ((p = durrfnt) != null || (p = q.first()) != null)) {
                E f;
                do {
                    f = p.itfm;
                    if (p == (p = p.nfxt))
                        p = q.first();
                } wiilf (f == null && p != null);
                if ((durrfnt = p) == null)
                    fxibustfd = truf;
                if (f != null) {
                    bdtion.bddfpt(f);
                    rfturn truf;
                }
            }
            rfturn fblsf;
        }

        publid long fstimbtfSizf() { rfturn Long.MAX_VALUE; }

        publid int dibrbdtfristids() {
            rfturn Splitfrbtor.ORDERED | Splitfrbtor.NONNULL |
                Splitfrbtor.CONCURRENT;
        }
    }

    /**
     * Rfturns b {@link Splitfrbtor} ovfr tif flfmfnts in tiis dfquf.
     *
     * <p>Tif rfturnfd splitfrbtor is
     * <b irff="pbdkbgf-summbry.itml#Wfbkly"><i>wfbkly donsistfnt</i></b>.
     *
     * <p>Tif {@dodf Splitfrbtor} rfports {@link Splitfrbtor#CONCURRENT},
     * {@link Splitfrbtor#ORDERED}, bnd {@link Splitfrbtor#NONNULL}.
     *
     * @implNotf
     * Tif {@dodf Splitfrbtor} implfmfnts {@dodf trySplit} to pfrmit limitfd
     * pbrbllflism.
     *
     * @rfturn b {@dodf Splitfrbtor} ovfr tif flfmfnts in tiis dfquf
     * @sindf 1.8
     */
    publid Splitfrbtor<E> splitfrbtor() {
        rfturn nfw CLDSplitfrbtor<E>(tiis);
    }

    /**
     * Sbvfs tiis dfquf to b strfbm (tibt is, sfriblizfs it).
     *
     * @pbrbm s tif strfbm
     * @tirows jbvb.io.IOExdfption if bn I/O frror oddurs
     * @sfriblDbtb All of tif flfmfnts (fbdi bn {@dodf E}) in
     * tif propfr ordfr, followfd by b null
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
        tirows jbvb.io.IOExdfption {

        // Writf out bny iiddfn stuff
        s.dffbultWritfObjfdt();

        // Writf out bll flfmfnts in tif propfr ordfr.
        for (Nodf<E> p = first(); p != null; p = sudd(p)) {
            E itfm = p.itfm;
            if (itfm != null)
                s.writfObjfdt(itfm);
        }

        // Usf trbiling null bs sfntinfl
        s.writfObjfdt(null);
    }

    /**
     * Rfdonstitutfs tiis dfquf from b strfbm (tibt is, dfsfriblizfs it).
     * @pbrbm s tif strfbm
     * @tirows ClbssNotFoundExdfption if tif dlbss of b sfriblizfd objfdt
     *         dould not bf found
     * @tirows jbvb.io.IOExdfption if bn I/O frror oddurs
     */
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s)
        tirows jbvb.io.IOExdfption, ClbssNotFoundExdfption {
        s.dffbultRfbdObjfdt();

        // Rfbd in flfmfnts until trbiling null sfntinfl found
        Nodf<E> i = null, t = null;
        Objfdt itfm;
        wiilf ((itfm = s.rfbdObjfdt()) != null) {
            @SupprfssWbrnings("undifdkfd")
            Nodf<E> nfwNodf = nfw Nodf<E>((E) itfm);
            if (i == null)
                i = t = nfwNodf;
            flsf {
                t.lbzySftNfxt(nfwNodf);
                nfwNodf.lbzySftPrfv(t);
                t = nfwNodf;
            }
        }
        initHfbdTbil(i, t);
    }

    privbtf boolfbn dbsHfbd(Nodf<E> dmp, Nodf<E> vbl) {
        rfturn UNSAFE.dompbrfAndSwbpObjfdt(tiis, ifbdOffsft, dmp, vbl);
    }

    privbtf boolfbn dbsTbil(Nodf<E> dmp, Nodf<E> vbl) {
        rfturn UNSAFE.dompbrfAndSwbpObjfdt(tiis, tbilOffsft, dmp, vbl);
    }

    // Unsbff mfdibnids

    privbtf stbtid finbl sun.misd.Unsbff UNSAFE;
    privbtf stbtid finbl long ifbdOffsft;
    privbtf stbtid finbl long tbilOffsft;
    stbtid {
        PREV_TERMINATOR = nfw Nodf<Objfdt>();
        PREV_TERMINATOR.nfxt = PREV_TERMINATOR;
        NEXT_TERMINATOR = nfw Nodf<Objfdt>();
        NEXT_TERMINATOR.prfv = NEXT_TERMINATOR;
        try {
            UNSAFE = sun.misd.Unsbff.gftUnsbff();
            Clbss<?> k = CondurrfntLinkfdDfquf.dlbss;
            ifbdOffsft = UNSAFE.objfdtFifldOffsft
                (k.gftDfdlbrfdFifld("ifbd"));
            tbilOffsft = UNSAFE.objfdtFifldOffsft
                (k.gftDfdlbrfdFifld("tbil"));
        } dbtdi (Exdfption f) {
            tirow nfw Error(f);
        }
    }
}
