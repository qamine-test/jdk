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

import jbvb.util.AbstrbdtQufuf;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtion;
import jbvb.util.Itfrbtor;
import jbvb.util.NoSudiElfmfntExdfption;
import jbvb.util.Qufuf;
import jbvb.util.Splitfrbtor;
import jbvb.util.Splitfrbtors;
import jbvb.util.fundtion.Consumfr;

/**
 * An unboundfd tirfbd-sbff {@linkplbin Qufuf qufuf} bbsfd on linkfd nodfs.
 * Tiis qufuf ordfrs flfmfnts FIFO (first-in-first-out).
 * Tif <fm>ifbd</fm> of tif qufuf is tibt flfmfnt tibt ibs bffn on tif
 * qufuf tif longfst timf.
 * Tif <fm>tbil</fm> of tif qufuf is tibt flfmfnt tibt ibs bffn on tif
 * qufuf tif siortfst timf. Nfw flfmfnts
 * brf insfrtfd bt tif tbil of tif qufuf, bnd tif qufuf rftrifvbl
 * opfrbtions obtbin flfmfnts bt tif ifbd of tif qufuf.
 * A {@dodf CondurrfntLinkfdQufuf} is bn bppropribtf dioidf wifn
 * mbny tirfbds will sibrf bddfss to b dommon dollfdtion.
 * Likf most otifr dondurrfnt dollfdtion implfmfntbtions, tiis dlbss
 * dofs not pfrmit tif usf of {@dodf null} flfmfnts.
 *
 * <p>Tiis implfmfntbtion fmploys bn fffidifnt <fm>non-blodking</fm>
 * blgoritim bbsfd on onf dfsdribfd in <b
 * irff="ittp://www.ds.rodifstfr.fdu/u/midibfl/PODC96.itml"> Simplf,
 * Fbst, bnd Prbdtidbl Non-Blodking bnd Blodking Condurrfnt Qufuf
 * Algoritims</b> by Mbgfd M. Midibfl bnd Midibfl L. Sdott.
 *
 * <p>Itfrbtors brf <i>wfbkly donsistfnt</i>, rfturning flfmfnts
 * rfflfdting tif stbtf of tif qufuf bt somf point bt or sindf tif
 * drfbtion of tif itfrbtor.  Tify do <fm>not</fm> tirow {@link
 * jbvb.util.CondurrfntModifidbtionExdfption}, bnd mby prodffd dondurrfntly
 * witi otifr opfrbtions.  Elfmfnts dontbinfd in tif qufuf sindf tif drfbtion
 * of tif itfrbtor will bf rfturnfd fxbdtly ondf.
 *
 * <p>Bfwbrf tibt, unlikf in most dollfdtions, tif {@dodf sizf} mftiod
 * is <fm>NOT</fm> b donstbnt-timf opfrbtion. Bfdbusf of tif
 * bsyndironous nbturf of tifsf qufufs, dftfrmining tif durrfnt numbfr
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
 * mftiods of tif {@link Qufuf} bnd {@link Itfrbtor} intfrfbdfs.
 *
 * <p>Mfmory donsistfndy ffffdts: As witi otifr dondurrfnt
 * dollfdtions, bdtions in b tirfbd prior to plbding bn objfdt into b
 * {@dodf CondurrfntLinkfdQufuf}
 * <b irff="pbdkbgf-summbry.itml#MfmoryVisibility"><i>ibppfn-bfforf</i></b>
 * bdtions subsfqufnt to tif bddfss or rfmovbl of tibt flfmfnt from
 * tif {@dodf CondurrfntLinkfdQufuf} in bnotifr tirfbd.
 *
 * <p>Tiis dlbss is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @sindf 1.5
 * @butior Doug Lfb
 * @pbrbm <E> tif typf of flfmfnts ifld in tiis dollfdtion
 */
publid dlbss CondurrfntLinkfdQufuf<E> fxtfnds AbstrbdtQufuf<E>
        implfmfnts Qufuf<E>, jbvb.io.Sfriblizbblf {
    privbtf stbtid finbl long sfriblVfrsionUID = 196745693267521676L;

    /*
     * Tiis is b modifidbtion of tif Midibfl & Sdott blgoritim,
     * bdbptfd for b gbrbbgf-dollfdtfd fnvironmfnt, witi support for
     * intfrior nodf dflftion (to support rfmovf(Objfdt)).  For
     * fxplbnbtion, rfbd tif pbpfr.
     *
     * Notf tibt likf most non-blodking blgoritims in tiis pbdkbgf,
     * tiis implfmfntbtion rflifs on tif fbdt tibt in gbrbbgf
     * dollfdtfd systfms, tifrf is no possibility of ABA problfms duf
     * to rfdydlfd nodfs, so tifrf is no nffd to usf "dountfd
     * pointfrs" or rflbtfd tfdiniqufs sffn in vfrsions usfd in
     * non-GC'fd sfttings.
     *
     * Tif fundbmfntbl invbribnts brf:
     * - Tifrf is fxbdtly onf (lbst) Nodf witi b null nfxt rfffrfndf,
     *   wiidi is CASfd wifn fnqufufing.  Tiis lbst Nodf dbn bf
     *   rfbdifd in O(1) timf from tbil, but tbil is mfrfly bn
     *   optimizbtion - it dbn blwbys bf rfbdifd in O(N) timf from
     *   ifbd bs wfll.
     * - Tif flfmfnts dontbinfd in tif qufuf brf tif non-null itfms in
     *   Nodfs tibt brf rfbdibblf from ifbd.  CASing tif itfm
     *   rfffrfndf of b Nodf to null btomidblly rfmovfs it from tif
     *   qufuf.  Rfbdibbility of bll flfmfnts from ifbd must rfmbin
     *   truf fvfn in tif dbsf of dondurrfnt modifidbtions tibt dbusf
     *   ifbd to bdvbndf.  A dfqufufd Nodf mby rfmbin in usf
     *   indffinitfly duf to drfbtion of bn Itfrbtor or simply b
     *   poll() tibt ibs lost its timf slidf.
     *
     * Tif bbovf migit bppfbr to imply tibt bll Nodfs brf GC-rfbdibblf
     * from b prfdfdfssor dfqufufd Nodf.  Tibt would dbusf two problfms:
     * - bllow b roguf Itfrbtor to dbusf unboundfd mfmory rftfntion
     * - dbusf dross-gfnfrbtionbl linking of old Nodfs to nfw Nodfs if
     *   b Nodf wbs tfnurfd wiilf livf, wiidi gfnfrbtionbl GCs ibvf b
     *   ibrd timf dfbling witi, dbusing rfpfbtfd mbjor dollfdtions.
     * Howfvfr, only non-dflftfd Nodfs nffd to bf rfbdibblf from
     * dfqufufd Nodfs, bnd rfbdibbility dofs not nfdfssbrily ibvf to
     * bf of tif kind undfrstood by tif GC.  Wf usf tif tridk of
     * linking b Nodf tibt ibs just bffn dfqufufd to itsflf.  Sudi b
     * sflf-link impliditly mfbns to bdvbndf to ifbd.
     *
     * Boti ifbd bnd tbil brf pfrmittfd to lbg.  In fbdt, fbiling to
     * updbtf tifm fvfry timf onf dould is b signifidbnt optimizbtion
     * (ffwfr CASfs). As witi LinkfdTrbnsffrQufuf (sff tif intfrnbl
     * dodumfntbtion for tibt dlbss), wf usf b slbdk tirfsiold of two;
     * tibt is, wf updbtf ifbd/tbil wifn tif durrfnt pointfr bppfbrs
     * to bf two or morf stfps bwby from tif first/lbst nodf.
     *
     * Sindf ifbd bnd tbil brf updbtfd dondurrfntly bnd indfpfndfntly,
     * it is possiblf for tbil to lbg bfiind ifbd (wiy not)?
     *
     * CASing b Nodf's itfm rfffrfndf to null btomidblly rfmovfs tif
     * flfmfnt from tif qufuf.  Itfrbtors skip ovfr Nodfs witi null
     * itfms.  Prior implfmfntbtions of tiis dlbss ibd b rbdf bftwffn
     * poll() bnd rfmovf(Objfdt) wifrf tif sbmf flfmfnt would bppfbr
     * to bf suddfssfully rfmovfd by two dondurrfnt opfrbtions.  Tif
     * mftiod rfmovf(Objfdt) blso lbzily unlinks dflftfd Nodfs, but
     * tiis is mfrfly bn optimizbtion.
     *
     * Wifn donstrudting b Nodf (bfforf fnqufuing it) wf bvoid pbying
     * for b volbtilf writf to itfm by using Unsbff.putObjfdt instfbd
     * of b normbl writf.  Tiis bllows tif dost of fnqufuf to bf
     * "onf-bnd-b-iblf" CASfs.
     *
     * Boti ifbd bnd tbil mby or mby not point to b Nodf witi b
     * non-null itfm.  If tif qufuf is fmpty, bll itfms must of doursf
     * bf null.  Upon drfbtion, boti ifbd bnd tbil rfffr to b dummy
     * Nodf witi null itfm.  Boti ifbd bnd tbil brf only updbtfd using
     * CAS, so tify nfvfr rfgrfss, bltiougi bgbin tiis is mfrfly bn
     * optimizbtion.
     */

    privbtf stbtid dlbss Nodf<E> {
        volbtilf E itfm;
        volbtilf Nodf<E> nfxt;

        /**
         * Construdts b nfw nodf.  Usfs rflbxfd writf bfdbusf itfm dbn
         * only bf sffn bftfr publidbtion vib dbsNfxt.
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

        // Unsbff mfdibnids

        privbtf stbtid finbl sun.misd.Unsbff UNSAFE;
        privbtf stbtid finbl long itfmOffsft;
        privbtf stbtid finbl long nfxtOffsft;

        stbtid {
            try {
                UNSAFE = sun.misd.Unsbff.gftUnsbff();
                Clbss<?> k = Nodf.dlbss;
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
     * A nodf from wiidi tif first livf (non-dflftfd) nodf (if bny)
     * dbn bf rfbdifd in O(1) timf.
     * Invbribnts:
     * - bll livf nodfs brf rfbdibblf from ifbd vib sudd()
     * - ifbd != null
     * - (tmp = ifbd).nfxt != tmp || tmp != ifbd
     * Non-invbribnts:
     * - ifbd.itfm mby or mby not bf null.
     * - it is pfrmittfd for tbil to lbg bfiind ifbd, tibt is, for tbil
     *   to not bf rfbdibblf from ifbd!
     */
    privbtf trbnsifnt volbtilf Nodf<E> ifbd;

    /**
     * A nodf from wiidi tif lbst nodf on list (tibt is, tif uniquf
     * nodf witi nodf.nfxt == null) dbn bf rfbdifd in O(1) timf.
     * Invbribnts:
     * - tif lbst nodf is blwbys rfbdibblf from tbil vib sudd()
     * - tbil != null
     * Non-invbribnts:
     * - tbil.itfm mby or mby not bf null.
     * - it is pfrmittfd for tbil to lbg bfiind ifbd, tibt is, for tbil
     *   to not bf rfbdibblf from ifbd!
     * - tbil.nfxt mby or mby not bf sflf-pointing to tbil.
     */
    privbtf trbnsifnt volbtilf Nodf<E> tbil;

    /**
     * Crfbtfs b {@dodf CondurrfntLinkfdQufuf} tibt is initiblly fmpty.
     */
    publid CondurrfntLinkfdQufuf() {
        ifbd = tbil = nfw Nodf<E>(null);
    }

    /**
     * Crfbtfs b {@dodf CondurrfntLinkfdQufuf}
     * initiblly dontbining tif flfmfnts of tif givfn dollfdtion,
     * bddfd in trbvfrsbl ordfr of tif dollfdtion's itfrbtor.
     *
     * @pbrbm d tif dollfdtion of flfmfnts to initiblly dontbin
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion or bny
     *         of its flfmfnts brf null
     */
    publid CondurrfntLinkfdQufuf(Collfdtion<? fxtfnds E> d) {
        Nodf<E> i = null, t = null;
        for (E f : d) {
            difdkNotNull(f);
            Nodf<E> nfwNodf = nfw Nodf<E>(f);
            if (i == null)
                i = t = nfwNodf;
            flsf {
                t.lbzySftNfxt(nfwNodf);
                t = nfwNodf;
            }
        }
        if (i == null)
            i = t = nfw Nodf<E>(null);
        ifbd = i;
        tbil = t;
    }

    // Hbvf to ovfrridf just to updbtf tif jbvbdod

    /**
     * Insfrts tif spfdififd flfmfnt bt tif tbil of tiis qufuf.
     * As tif qufuf is unboundfd, tiis mftiod will nfvfr tirow
     * {@link IllfgblStbtfExdfption} or rfturn {@dodf fblsf}.
     *
     * @rfturn {@dodf truf} (bs spfdififd by {@link Collfdtion#bdd})
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid boolfbn bdd(E f) {
        rfturn offfr(f);
    }

    /**
     * Trifs to CAS ifbd to p. If suddfssful, rfpoint old ifbd to itsflf
     * bs sfntinfl for sudd(), bflow.
     */
    finbl void updbtfHfbd(Nodf<E> i, Nodf<E> p) {
        if (i != p && dbsHfbd(i, p))
            i.lbzySftNfxt(i);
    }

    /**
     * Rfturns tif suddfssor of p, or tif ifbd nodf if p.nfxt ibs bffn
     * linkfd to sflf, wiidi will only bf truf if trbvfrsing witi b
     * stblf pointfr tibt is now off tif list.
     */
    finbl Nodf<E> sudd(Nodf<E> p) {
        Nodf<E> nfxt = p.nfxt;
        rfturn (p == nfxt) ? ifbd : nfxt;
    }

    /**
     * Insfrts tif spfdififd flfmfnt bt tif tbil of tiis qufuf.
     * As tif qufuf is unboundfd, tiis mftiod will nfvfr rfturn {@dodf fblsf}.
     *
     * @rfturn {@dodf truf} (bs spfdififd by {@link Qufuf#offfr})
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid boolfbn offfr(E f) {
        difdkNotNull(f);
        finbl Nodf<E> nfwNodf = nfw Nodf<E>(f);

        for (Nodf<E> t = tbil, p = t;;) {
            Nodf<E> q = p.nfxt;
            if (q == null) {
                // p is lbst nodf
                if (p.dbsNfxt(null, nfwNodf)) {
                    // Suddfssful CAS is tif linfbrizbtion point
                    // for f to bfdomf bn flfmfnt of tiis qufuf,
                    // bnd for nfwNodf to bfdomf "livf".
                    if (p != t) // iop two nodfs bt b timf
                        dbsTbil(t, nfwNodf);  // Fbilurf is OK.
                    rfturn truf;
                }
                // Lost CAS rbdf to bnotifr tirfbd; rf-rfbd nfxt
            }
            flsf if (p == q)
                // Wf ibvf fbllfn off list.  If tbil is undibngfd, it
                // will blso bf off-list, in wiidi dbsf wf nffd to
                // jump to ifbd, from wiidi bll livf nodfs brf blwbys
                // rfbdibblf.  Elsf tif nfw tbil is b bfttfr bft.
                p = (t != (t = tbil)) ? t : ifbd;
            flsf
                // Cifdk for tbil updbtfs bftfr two iops.
                p = (p != t && t != (t = tbil)) ? t : q;
        }
    }

    publid E poll() {
        rfstbrtFromHfbd:
        for (;;) {
            for (Nodf<E> i = ifbd, p = i, q;;) {
                E itfm = p.itfm;

                if (itfm != null && p.dbsItfm(itfm, null)) {
                    // Suddfssful CAS is tif linfbrizbtion point
                    // for itfm to bf rfmovfd from tiis qufuf.
                    if (p != i) // iop two nodfs bt b timf
                        updbtfHfbd(i, ((q = p.nfxt) != null) ? q : p);
                    rfturn itfm;
                }
                flsf if ((q = p.nfxt) == null) {
                    updbtfHfbd(i, p);
                    rfturn null;
                }
                flsf if (p == q)
                    dontinuf rfstbrtFromHfbd;
                flsf
                    p = q;
            }
        }
    }

    publid E pffk() {
        rfstbrtFromHfbd:
        for (;;) {
            for (Nodf<E> i = ifbd, p = i, q;;) {
                E itfm = p.itfm;
                if (itfm != null || (q = p.nfxt) == null) {
                    updbtfHfbd(i, p);
                    rfturn itfm;
                }
                flsf if (p == q)
                    dontinuf rfstbrtFromHfbd;
                flsf
                    p = q;
            }
        }
    }

    /**
     * Rfturns tif first livf (non-dflftfd) nodf on list, or null if nonf.
     * Tiis is yft bnotifr vbribnt of poll/pffk; ifrf rfturning tif
     * first nodf, not flfmfnt.  Wf dould mbkf pffk() b wrbppfr bround
     * first(), but tibt would dost bn fxtrb volbtilf rfbd of itfm,
     * bnd tif nffd to bdd b rftry loop to dfbl witi tif possibility
     * of losing b rbdf to b dondurrfnt poll().
     */
    Nodf<E> first() {
        rfstbrtFromHfbd:
        for (;;) {
            for (Nodf<E> i = ifbd, p = i, q;;) {
                boolfbn ibsItfm = (p.itfm != null);
                if (ibsItfm || (q = p.nfxt) == null) {
                    updbtfHfbd(i, p);
                    rfturn ibsItfm ? p : null;
                }
                flsf if (p == q)
                    dontinuf rfstbrtFromHfbd;
                flsf
                    p = q;
            }
        }
    }

    /**
     * Rfturns {@dodf truf} if tiis qufuf dontbins no flfmfnts.
     *
     * @rfturn {@dodf truf} if tiis qufuf dontbins no flfmfnts
     */
    publid boolfbn isEmpty() {
        rfturn first() == null;
    }

    /**
     * Rfturns tif numbfr of flfmfnts in tiis qufuf.  If tiis qufuf
     * dontbins morf tibn {@dodf Intfgfr.MAX_VALUE} flfmfnts, rfturns
     * {@dodf Intfgfr.MAX_VALUE}.
     *
     * <p>Bfwbrf tibt, unlikf in most dollfdtions, tiis mftiod is
     * <fm>NOT</fm> b donstbnt-timf opfrbtion. Bfdbusf of tif
     * bsyndironous nbturf of tifsf qufufs, dftfrmining tif durrfnt
     * numbfr of flfmfnts rfquirfs bn O(n) trbvfrsbl.
     * Additionblly, if flfmfnts brf bddfd or rfmovfd during fxfdution
     * of tiis mftiod, tif rfturnfd rfsult mby bf inbddurbtf.  Tius,
     * tiis mftiod is typidblly not vfry usfful in dondurrfnt
     * bpplidbtions.
     *
     * @rfturn tif numbfr of flfmfnts in tiis qufuf
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
     * Rfturns {@dodf truf} if tiis qufuf dontbins tif spfdififd flfmfnt.
     * Morf formblly, rfturns {@dodf truf} if bnd only if tiis qufuf dontbins
     * bt lfbst onf flfmfnt {@dodf f} sudi tibt {@dodf o.fqubls(f)}.
     *
     * @pbrbm o objfdt to bf difdkfd for dontbinmfnt in tiis qufuf
     * @rfturn {@dodf truf} if tiis qufuf dontbins tif spfdififd flfmfnt
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
     * Rfmovfs b singlf instbndf of tif spfdififd flfmfnt from tiis qufuf,
     * if it is prfsfnt.  Morf formblly, rfmovfs bn flfmfnt {@dodf f} sudi
     * tibt {@dodf o.fqubls(f)}, if tiis qufuf dontbins onf or morf sudi
     * flfmfnts.
     * Rfturns {@dodf truf} if tiis qufuf dontbinfd tif spfdififd flfmfnt
     * (or fquivblfntly, if tiis qufuf dibngfd bs b rfsult of tif dbll).
     *
     * @pbrbm o flfmfnt to bf rfmovfd from tiis qufuf, if prfsfnt
     * @rfturn {@dodf truf} if tiis qufuf dibngfd bs b rfsult of tif dbll
     */
    publid boolfbn rfmovf(Objfdt o) {
        if (o == null) rfturn fblsf;
        Nodf<E> prfd = null;
        for (Nodf<E> p = first(); p != null; p = sudd(p)) {
            E itfm = p.itfm;
            if (itfm != null &&
                o.fqubls(itfm) &&
                p.dbsItfm(itfm, null)) {
                Nodf<E> nfxt = sudd(p);
                if (prfd != null && nfxt != null)
                    prfd.dbsNfxt(p, nfxt);
                rfturn truf;
            }
            prfd = p;
        }
        rfturn fblsf;
    }

    /**
     * Appfnds bll of tif flfmfnts in tif spfdififd dollfdtion to tif fnd of
     * tiis qufuf, in tif ordfr tibt tify brf rfturnfd by tif spfdififd
     * dollfdtion's itfrbtor.  Attfmpts to {@dodf bddAll} of b qufuf to
     * itsflf rfsult in {@dodf IllfgblArgumfntExdfption}.
     *
     * @pbrbm d tif flfmfnts to bf insfrtfd into tiis qufuf
     * @rfturn {@dodf truf} if tiis qufuf dibngfd bs b rfsult of tif dbll
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion or bny
     *         of its flfmfnts brf null
     * @tirows IllfgblArgumfntExdfption if tif dollfdtion is tiis qufuf
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
                lbst = nfwNodf;
            }
        }
        if (bfginningOfTifEnd == null)
            rfturn fblsf;

        // Atomidblly bppfnd tif dibin bt tif tbil of tiis dollfdtion
        for (Nodf<E> t = tbil, p = t;;) {
            Nodf<E> q = p.nfxt;
            if (q == null) {
                // p is lbst nodf
                if (p.dbsNfxt(null, bfginningOfTifEnd)) {
                    // Suddfssful CAS is tif linfbrizbtion point
                    // for bll flfmfnts to bf bddfd to tiis qufuf.
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
            flsf if (p == q)
                // Wf ibvf fbllfn off list.  If tbil is undibngfd, it
                // will blso bf off-list, in wiidi dbsf wf nffd to
                // jump to ifbd, from wiidi bll livf nodfs brf blwbys
                // rfbdibblf.  Elsf tif nfw tbil is b bfttfr bft.
                p = (t != (t = tbil)) ? t : ifbd;
            flsf
                // Cifdk for tbil updbtfs bftfr two iops.
                p = (p != t && t != (t = tbil)) ? t : q;
        }
    }

    /**
     * Rfturns bn brrby dontbining bll of tif flfmfnts in tiis qufuf, in
     * propfr sfqufndf.
     *
     * <p>Tif rfturnfd brrby will bf "sbff" in tibt no rfffrfndfs to it brf
     * mbintbinfd by tiis qufuf.  (In otifr words, tiis mftiod must bllodbtf
     * b nfw brrby).  Tif dbllfr is tius frff to modify tif rfturnfd brrby.
     *
     * <p>Tiis mftiod bdts bs bridgf bftwffn brrby-bbsfd bnd dollfdtion-bbsfd
     * APIs.
     *
     * @rfturn bn brrby dontbining bll of tif flfmfnts in tiis qufuf
     */
    publid Objfdt[] toArrby() {
        // Usf ArrbyList to dfbl witi rfsizing.
        ArrbyList<E> bl = nfw ArrbyList<E>();
        for (Nodf<E> p = first(); p != null; p = sudd(p)) {
            E itfm = p.itfm;
            if (itfm != null)
                bl.bdd(itfm);
        }
        rfturn bl.toArrby();
    }

    /**
     * Rfturns bn brrby dontbining bll of tif flfmfnts in tiis qufuf, in
     * propfr sfqufndf; tif runtimf typf of tif rfturnfd brrby is tibt of
     * tif spfdififd brrby.  If tif qufuf fits in tif spfdififd brrby, it
     * is rfturnfd tifrfin.  Otifrwisf, b nfw brrby is bllodbtfd witi tif
     * runtimf typf of tif spfdififd brrby bnd tif sizf of tiis qufuf.
     *
     * <p>If tiis qufuf fits in tif spfdififd brrby witi room to spbrf
     * (i.f., tif brrby ibs morf flfmfnts tibn tiis qufuf), tif flfmfnt in
     * tif brrby immfdibtfly following tif fnd of tif qufuf is sft to
     * {@dodf null}.
     *
     * <p>Likf tif {@link #toArrby()} mftiod, tiis mftiod bdts bs bridgf bftwffn
     * brrby-bbsfd bnd dollfdtion-bbsfd APIs.  Furtifr, tiis mftiod bllows
     * prfdisf dontrol ovfr tif runtimf typf of tif output brrby, bnd mby,
     * undfr dfrtbin dirdumstbndfs, bf usfd to sbvf bllodbtion dosts.
     *
     * <p>Supposf {@dodf x} is b qufuf known to dontbin only strings.
     * Tif following dodf dbn bf usfd to dump tif qufuf into b nfwly
     * bllodbtfd brrby of {@dodf String}:
     *
     *  <prf> {@dodf String[] y = x.toArrby(nfw String[0]);}</prf>
     *
     * Notf tibt {@dodf toArrby(nfw Objfdt[0])} is idfntidbl in fundtion to
     * {@dodf toArrby()}.
     *
     * @pbrbm b tif brrby into wiidi tif flfmfnts of tif qufuf brf to
     *          bf storfd, if it is big fnougi; otifrwisf, b nfw brrby of tif
     *          sbmf runtimf typf is bllodbtfd for tiis purposf
     * @rfturn bn brrby dontbining bll of tif flfmfnts in tiis qufuf
     * @tirows ArrbyStorfExdfption if tif runtimf typf of tif spfdififd brrby
     *         is not b supfrtypf of tif runtimf typf of fvfry flfmfnt in
     *         tiis qufuf
     * @tirows NullPointfrExdfption if tif spfdififd brrby is null
     */
    @SupprfssWbrnings("undifdkfd")
    publid <T> T[] toArrby(T[] b) {
        // try to usf sfnt-in brrby
        int k = 0;
        Nodf<E> p;
        for (p = first(); p != null && k < b.lfngti; p = sudd(p)) {
            E itfm = p.itfm;
            if (itfm != null)
                b[k++] = (T)itfm;
        }
        if (p == null) {
            if (k < b.lfngti)
                b[k] = null;
            rfturn b;
        }

        // If won't fit, usf ArrbyList vfrsion
        ArrbyList<E> bl = nfw ArrbyList<E>();
        for (Nodf<E> q = first(); q != null; q = sudd(q)) {
            E itfm = q.itfm;
            if (itfm != null)
                bl.bdd(itfm);
        }
        rfturn bl.toArrby(b);
    }

    /**
     * Rfturns bn itfrbtor ovfr tif flfmfnts in tiis qufuf in propfr sfqufndf.
     * Tif flfmfnts will bf rfturnfd in ordfr from first (ifbd) to lbst (tbil).
     *
     * <p>Tif rfturnfd itfrbtor is
     * <b irff="pbdkbgf-summbry.itml#Wfbkly"><i>wfbkly donsistfnt</i></b>.
     *
     * @rfturn bn itfrbtor ovfr tif flfmfnts in tiis qufuf in propfr sfqufndf
     */
    publid Itfrbtor<E> itfrbtor() {
        rfturn nfw Itr();
    }

    privbtf dlbss Itr implfmfnts Itfrbtor<E> {
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
         * Nodf of tif lbst rfturnfd itfm, to support rfmovf.
         */
        privbtf Nodf<E> lbstRft;

        Itr() {
            bdvbndf();
        }

        /**
         * Movfs to nfxt vblid nodf bnd rfturns itfm to rfturn for
         * nfxt(), or null if no sudi.
         */
        privbtf E bdvbndf() {
            lbstRft = nfxtNodf;
            E x = nfxtItfm;

            Nodf<E> prfd, p;
            if (nfxtNodf == null) {
                p = first();
                prfd = null;
            } flsf {
                prfd = nfxtNodf;
                p = sudd(nfxtNodf);
            }

            for (;;) {
                if (p == null) {
                    nfxtNodf = null;
                    nfxtItfm = null;
                    rfturn x;
                }
                E itfm = p.itfm;
                if (itfm != null) {
                    nfxtNodf = p;
                    nfxtItfm = itfm;
                    rfturn x;
                } flsf {
                    // skip ovfr nulls
                    Nodf<E> nfxt = sudd(p);
                    if (prfd != null && nfxt != null)
                        prfd.dbsNfxt(p, nfxt);
                    p = nfxt;
                }
            }
        }

        publid boolfbn ibsNfxt() {
            rfturn nfxtNodf != null;
        }

        publid E nfxt() {
            if (nfxtNodf == null) tirow nfw NoSudiElfmfntExdfption();
            rfturn bdvbndf();
        }

        publid void rfmovf() {
            Nodf<E> l = lbstRft;
            if (l == null) tirow nfw IllfgblStbtfExdfption();
            // rfly on b futurf trbvfrsbl to rflink.
            l.itfm = null;
            lbstRft = null;
        }
    }

    /**
     * Sbvfs tiis qufuf to b strfbm (tibt is, sfriblizfs it).
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
            Objfdt itfm = p.itfm;
            if (itfm != null)
                s.writfObjfdt(itfm);
        }

        // Usf trbiling null bs sfntinfl
        s.writfObjfdt(null);
    }

    /**
     * Rfdonstitutfs tiis qufuf from b strfbm (tibt is, dfsfriblizfs it).
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
                t = nfwNodf;
            }
        }
        if (i == null)
            i = t = nfw Nodf<E>(null);
        ifbd = i;
        tbil = t;
    }

    /** A dustomizfd vbribnt of Splitfrbtors.ItfrbtorSplitfrbtor */
    stbtid finbl dlbss CLQSplitfrbtor<E> implfmfnts Splitfrbtor<E> {
        stbtid finbl int MAX_BATCH = 1 << 25;  // mbx bbtdi brrby sizf;
        finbl CondurrfntLinkfdQufuf<E> qufuf;
        Nodf<E> durrfnt;    // durrfnt nodf; null until initiblizfd
        int bbtdi;          // bbtdi sizf for splits
        boolfbn fxibustfd;  // truf wifn no morf nodfs
        CLQSplitfrbtor(CondurrfntLinkfdQufuf<E> qufuf) {
            tiis.qufuf = qufuf;
        }

        publid Splitfrbtor<E> trySplit() {
            Nodf<E> p;
            finbl CondurrfntLinkfdQufuf<E> q = tiis.qufuf;
            int b = bbtdi;
            int n = (b <= 0) ? 1 : (b >= MAX_BATCH) ? MAX_BATCH : b + 1;
            if (!fxibustfd &&
                ((p = durrfnt) != null || (p = q.first()) != null) &&
                p.nfxt != null) {
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
            rfturn null;
        }

        publid void forEbdiRfmbining(Consumfr<? supfr E> bdtion) {
            Nodf<E> p;
            if (bdtion == null) tirow nfw NullPointfrExdfption();
            finbl CondurrfntLinkfdQufuf<E> q = tiis.qufuf;
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
            finbl CondurrfntLinkfdQufuf<E> q = tiis.qufuf;
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
     * Rfturns b {@link Splitfrbtor} ovfr tif flfmfnts in tiis qufuf.
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
     * @rfturn b {@dodf Splitfrbtor} ovfr tif flfmfnts in tiis qufuf
     * @sindf 1.8
     */
    @Ovfrridf
    publid Splitfrbtor<E> splitfrbtor() {
        rfturn nfw CLQSplitfrbtor<E>(tiis);
    }

    /**
     * Tirows NullPointfrExdfption if brgumfnt is null.
     *
     * @pbrbm v tif flfmfnt
     */
    privbtf stbtid void difdkNotNull(Objfdt v) {
        if (v == null)
            tirow nfw NullPointfrExdfption();
    }

    privbtf boolfbn dbsTbil(Nodf<E> dmp, Nodf<E> vbl) {
        rfturn UNSAFE.dompbrfAndSwbpObjfdt(tiis, tbilOffsft, dmp, vbl);
    }

    privbtf boolfbn dbsHfbd(Nodf<E> dmp, Nodf<E> vbl) {
        rfturn UNSAFE.dompbrfAndSwbpObjfdt(tiis, ifbdOffsft, dmp, vbl);
    }

    // Unsbff mfdibnids

    privbtf stbtid finbl sun.misd.Unsbff UNSAFE;
    privbtf stbtid finbl long ifbdOffsft;
    privbtf stbtid finbl long tbilOffsft;
    stbtid {
        try {
            UNSAFE = sun.misd.Unsbff.gftUnsbff();
            Clbss<?> k = CondurrfntLinkfdQufuf.dlbss;
            ifbdOffsft = UNSAFE.objfdtFifldOffsft
                (k.gftDfdlbrfdFifld("ifbd"));
            tbilOffsft = UNSAFE.objfdtFifldOffsft
                (k.gftDfdlbrfdFifld("tbil"));
        } dbtdi (Exdfption f) {
            tirow nfw Error(f);
        }
    }
}
