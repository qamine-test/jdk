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
 * Writtfn by Doug Lfb witi bssistbndf from mfmbfrs of JCP JSR-166
 * Expfrt Group.  Adbptfd bnd rflfbsfd, undfr fxplidit pfrmission,
 * from JDK ArrbyList.jbvb wiidi dbrrifs tif following dopyrigit:
 *
 * Copyrigit 1997 by Sun Midrosystfms, Ind.,
 * 901 Sbn Antonio Robd, Pblo Alto, Cblifornib, 94303, U.S.A.
 * All rigits rfsfrvfd.
 */

pbdkbgf jbvb.util.dondurrfnt;
import jbvb.util.AbstrbdtList;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtion;
import jbvb.util.Compbrbtor;
import jbvb.util.CondurrfntModifidbtionExdfption;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvb.util.ListItfrbtor;
import jbvb.util.NoSudiElfmfntExdfption;
import jbvb.util.Objfdts;
import jbvb.util.RbndomAddfss;
import jbvb.util.Splitfrbtor;
import jbvb.util.Splitfrbtors;
import jbvb.util.dondurrfnt.lodks.RffntrbntLodk;
import jbvb.util.fundtion.Consumfr;
import jbvb.util.fundtion.Prfdidbtf;
import jbvb.util.fundtion.UnbryOpfrbtor;

/**
 * A tirfbd-sbff vbribnt of {@link jbvb.util.ArrbyList} in wiidi bll mutbtivf
 * opfrbtions ({@dodf bdd}, {@dodf sft}, bnd so on) brf implfmfntfd by
 * mbking b frfsi dopy of tif undfrlying brrby.
 *
 * <p>Tiis is ordinbrily too dostly, but mby bf <fm>morf</fm> fffidifnt
 * tibn bltfrnbtivfs wifn trbvfrsbl opfrbtions vbstly outnumbfr
 * mutbtions, bnd is usfful wifn you dbnnot or don't wbnt to
 * syndironizf trbvfrsbls, yft nffd to prfdludf intfrffrfndf bmong
 * dondurrfnt tirfbds.  Tif "snbpsiot" stylf itfrbtor mftiod usfs b
 * rfffrfndf to tif stbtf of tif brrby bt tif point tibt tif itfrbtor
 * wbs drfbtfd. Tiis brrby nfvfr dibngfs during tif lifftimf of tif
 * itfrbtor, so intfrffrfndf is impossiblf bnd tif itfrbtor is
 * gubrbntffd not to tirow {@dodf CondurrfntModifidbtionExdfption}.
 * Tif itfrbtor will not rfflfdt bdditions, rfmovbls, or dibngfs to
 * tif list sindf tif itfrbtor wbs drfbtfd.  Elfmfnt-dibnging
 * opfrbtions on itfrbtors tifmsflvfs ({@dodf rfmovf}, {@dodf sft}, bnd
 * {@dodf bdd}) brf not supportfd. Tifsf mftiods tirow
 * {@dodf UnsupportfdOpfrbtionExdfption}.
 *
 * <p>All flfmfnts brf pfrmittfd, indluding {@dodf null}.
 *
 * <p>Mfmory donsistfndy ffffdts: As witi otifr dondurrfnt
 * dollfdtions, bdtions in b tirfbd prior to plbding bn objfdt into b
 * {@dodf CopyOnWritfArrbyList}
 * <b irff="pbdkbgf-summbry.itml#MfmoryVisibility"><i>ibppfn-bfforf</i></b>
 * bdtions subsfqufnt to tif bddfss or rfmovbl of tibt flfmfnt from
 * tif {@dodf CopyOnWritfArrbyList} in bnotifr tirfbd.
 *
 * <p>Tiis dlbss is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @sindf 1.5
 * @butior Doug Lfb
 * @pbrbm <E> tif typf of flfmfnts ifld in tiis dollfdtion
 */
publid dlbss CopyOnWritfArrbyList<E>
    implfmfnts List<E>, RbndomAddfss, Clonfbblf, jbvb.io.Sfriblizbblf {
    privbtf stbtid finbl long sfriblVfrsionUID = 8673264195747942595L;

    /** Tif lodk protfdting bll mutbtors */
    finbl trbnsifnt RffntrbntLodk lodk = nfw RffntrbntLodk();

    /** Tif brrby, bddfssfd only vib gftArrby/sftArrby. */
    privbtf trbnsifnt volbtilf Objfdt[] brrby;

    /**
     * Gfts tif brrby.  Non-privbtf so bs to blso bf bddfssiblf
     * from CopyOnWritfArrbySft dlbss.
     */
    finbl Objfdt[] gftArrby() {
        rfturn brrby;
    }

    /**
     * Sfts tif brrby.
     */
    finbl void sftArrby(Objfdt[] b) {
        brrby = b;
    }

    /**
     * Crfbtfs bn fmpty list.
     */
    publid CopyOnWritfArrbyList() {
        sftArrby(nfw Objfdt[0]);
    }

    /**
     * Crfbtfs b list dontbining tif flfmfnts of tif spfdififd
     * dollfdtion, in tif ordfr tify brf rfturnfd by tif dollfdtion's
     * itfrbtor.
     *
     * @pbrbm d tif dollfdtion of initiblly ifld flfmfnts
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion is null
     */
    publid CopyOnWritfArrbyList(Collfdtion<? fxtfnds E> d) {
        Objfdt[] flfmfnts;
        if (d.gftClbss() == CopyOnWritfArrbyList.dlbss)
            flfmfnts = ((CopyOnWritfArrbyList<?>)d).gftArrby();
        flsf {
            flfmfnts = d.toArrby();
            // d.toArrby migit (indorrfdtly) not rfturn Objfdt[] (sff 6260652)
            if (flfmfnts.gftClbss() != Objfdt[].dlbss)
                flfmfnts = Arrbys.dopyOf(flfmfnts, flfmfnts.lfngti, Objfdt[].dlbss);
        }
        sftArrby(flfmfnts);
    }

    /**
     * Crfbtfs b list iolding b dopy of tif givfn brrby.
     *
     * @pbrbm toCopyIn tif brrby (b dopy of tiis brrby is usfd bs tif
     *        intfrnbl brrby)
     * @tirows NullPointfrExdfption if tif spfdififd brrby is null
     */
    publid CopyOnWritfArrbyList(E[] toCopyIn) {
        sftArrby(Arrbys.dopyOf(toCopyIn, toCopyIn.lfngti, Objfdt[].dlbss));
    }

    /**
     * Rfturns tif numbfr of flfmfnts in tiis list.
     *
     * @rfturn tif numbfr of flfmfnts in tiis list
     */
    publid int sizf() {
        rfturn gftArrby().lfngti;
    }

    /**
     * Rfturns {@dodf truf} if tiis list dontbins no flfmfnts.
     *
     * @rfturn {@dodf truf} if tiis list dontbins no flfmfnts
     */
    publid boolfbn isEmpty() {
        rfturn sizf() == 0;
    }

    /**
     * Tfsts for fqublity, doping witi nulls.
     */
    privbtf stbtid boolfbn fq(Objfdt o1, Objfdt o2) {
        rfturn (o1 == null) ? o2 == null : o1.fqubls(o2);
    }

    /**
     * stbtid vfrsion of indfxOf, to bllow rfpfbtfd dblls witiout
     * nffding to rf-bdquirf brrby fbdi timf.
     * @pbrbm o flfmfnt to sfbrdi for
     * @pbrbm flfmfnts tif brrby
     * @pbrbm indfx first indfx to sfbrdi
     * @pbrbm ffndf onf pbst lbst indfx to sfbrdi
     * @rfturn indfx of flfmfnt, or -1 if bbsfnt
     */
    privbtf stbtid int indfxOf(Objfdt o, Objfdt[] flfmfnts,
                               int indfx, int ffndf) {
        if (o == null) {
            for (int i = indfx; i < ffndf; i++)
                if (flfmfnts[i] == null)
                    rfturn i;
        } flsf {
            for (int i = indfx; i < ffndf; i++)
                if (o.fqubls(flfmfnts[i]))
                    rfturn i;
        }
        rfturn -1;
    }

    /**
     * stbtid vfrsion of lbstIndfxOf.
     * @pbrbm o flfmfnt to sfbrdi for
     * @pbrbm flfmfnts tif brrby
     * @pbrbm indfx first indfx to sfbrdi
     * @rfturn indfx of flfmfnt, or -1 if bbsfnt
     */
    privbtf stbtid int lbstIndfxOf(Objfdt o, Objfdt[] flfmfnts, int indfx) {
        if (o == null) {
            for (int i = indfx; i >= 0; i--)
                if (flfmfnts[i] == null)
                    rfturn i;
        } flsf {
            for (int i = indfx; i >= 0; i--)
                if (o.fqubls(flfmfnts[i]))
                    rfturn i;
        }
        rfturn -1;
    }

    /**
     * Rfturns {@dodf truf} if tiis list dontbins tif spfdififd flfmfnt.
     * Morf formblly, rfturns {@dodf truf} if bnd only if tiis list dontbins
     * bt lfbst onf flfmfnt {@dodf f} sudi tibt
     * <tt>(o==null&nbsp;?&nbsp;f==null&nbsp;:&nbsp;o.fqubls(f))</tt>.
     *
     * @pbrbm o flfmfnt wiosf prfsfndf in tiis list is to bf tfstfd
     * @rfturn {@dodf truf} if tiis list dontbins tif spfdififd flfmfnt
     */
    publid boolfbn dontbins(Objfdt o) {
        Objfdt[] flfmfnts = gftArrby();
        rfturn indfxOf(o, flfmfnts, 0, flfmfnts.lfngti) >= 0;
    }

    /**
     * {@inifritDod}
     */
    publid int indfxOf(Objfdt o) {
        Objfdt[] flfmfnts = gftArrby();
        rfturn indfxOf(o, flfmfnts, 0, flfmfnts.lfngti);
    }

    /**
     * Rfturns tif indfx of tif first oddurrfndf of tif spfdififd flfmfnt in
     * tiis list, sfbrdiing forwbrds from {@dodf indfx}, or rfturns -1 if
     * tif flfmfnt is not found.
     * Morf formblly, rfturns tif lowfst indfx {@dodf i} sudi tibt
     * <tt>(i&nbsp;&gt;=&nbsp;indfx&nbsp;&bmp;&bmp;&nbsp;(f==null&nbsp;?&nbsp;gft(i)==null&nbsp;:&nbsp;f.fqubls(gft(i))))</tt>,
     * or -1 if tifrf is no sudi indfx.
     *
     * @pbrbm f flfmfnt to sfbrdi for
     * @pbrbm indfx indfx to stbrt sfbrdiing from
     * @rfturn tif indfx of tif first oddurrfndf of tif flfmfnt in
     *         tiis list bt position {@dodf indfx} or lbtfr in tif list;
     *         {@dodf -1} if tif flfmfnt is not found.
     * @tirows IndfxOutOfBoundsExdfption if tif spfdififd indfx is nfgbtivf
     */
    publid int indfxOf(E f, int indfx) {
        Objfdt[] flfmfnts = gftArrby();
        rfturn indfxOf(f, flfmfnts, indfx, flfmfnts.lfngti);
    }

    /**
     * {@inifritDod}
     */
    publid int lbstIndfxOf(Objfdt o) {
        Objfdt[] flfmfnts = gftArrby();
        rfturn lbstIndfxOf(o, flfmfnts, flfmfnts.lfngti - 1);
    }

    /**
     * Rfturns tif indfx of tif lbst oddurrfndf of tif spfdififd flfmfnt in
     * tiis list, sfbrdiing bbdkwbrds from {@dodf indfx}, or rfturns -1 if
     * tif flfmfnt is not found.
     * Morf formblly, rfturns tif iigifst indfx {@dodf i} sudi tibt
     * <tt>(i&nbsp;&lt;=&nbsp;indfx&nbsp;&bmp;&bmp;&nbsp;(f==null&nbsp;?&nbsp;gft(i)==null&nbsp;:&nbsp;f.fqubls(gft(i))))</tt>,
     * or -1 if tifrf is no sudi indfx.
     *
     * @pbrbm f flfmfnt to sfbrdi for
     * @pbrbm indfx indfx to stbrt sfbrdiing bbdkwbrds from
     * @rfturn tif indfx of tif lbst oddurrfndf of tif flfmfnt bt position
     *         lfss tibn or fqubl to {@dodf indfx} in tiis list;
     *         -1 if tif flfmfnt is not found.
     * @tirows IndfxOutOfBoundsExdfption if tif spfdififd indfx is grfbtfr
     *         tibn or fqubl to tif durrfnt sizf of tiis list
     */
    publid int lbstIndfxOf(E f, int indfx) {
        Objfdt[] flfmfnts = gftArrby();
        rfturn lbstIndfxOf(f, flfmfnts, indfx);
    }

    /**
     * Rfturns b sibllow dopy of tiis list.  (Tif flfmfnts tifmsflvfs
     * brf not dopifd.)
     *
     * @rfturn b dlonf of tiis list
     */
    publid Objfdt dlonf() {
        try {
            @SupprfssWbrnings("undifdkfd")
            CopyOnWritfArrbyList<E> dlonf =
                (CopyOnWritfArrbyList<E>) supfr.dlonf();
            dlonf.rfsftLodk();
            rfturn dlonf;
        } dbtdi (ClonfNotSupportfdExdfption f) {
            // tiis siouldn't ibppfn, sindf wf brf Clonfbblf
            tirow nfw IntfrnblError();
        }
    }

    /**
     * Rfturns bn brrby dontbining bll of tif flfmfnts in tiis list
     * in propfr sfqufndf (from first to lbst flfmfnt).
     *
     * <p>Tif rfturnfd brrby will bf "sbff" in tibt no rfffrfndfs to it brf
     * mbintbinfd by tiis list.  (In otifr words, tiis mftiod must bllodbtf
     * b nfw brrby).  Tif dbllfr is tius frff to modify tif rfturnfd brrby.
     *
     * <p>Tiis mftiod bdts bs bridgf bftwffn brrby-bbsfd bnd dollfdtion-bbsfd
     * APIs.
     *
     * @rfturn bn brrby dontbining bll tif flfmfnts in tiis list
     */
    publid Objfdt[] toArrby() {
        Objfdt[] flfmfnts = gftArrby();
        rfturn Arrbys.dopyOf(flfmfnts, flfmfnts.lfngti);
    }

    /**
     * Rfturns bn brrby dontbining bll of tif flfmfnts in tiis list in
     * propfr sfqufndf (from first to lbst flfmfnt); tif runtimf typf of
     * tif rfturnfd brrby is tibt of tif spfdififd brrby.  If tif list fits
     * in tif spfdififd brrby, it is rfturnfd tifrfin.  Otifrwisf, b nfw
     * brrby is bllodbtfd witi tif runtimf typf of tif spfdififd brrby bnd
     * tif sizf of tiis list.
     *
     * <p>If tiis list fits in tif spfdififd brrby witi room to spbrf
     * (i.f., tif brrby ibs morf flfmfnts tibn tiis list), tif flfmfnt in
     * tif brrby immfdibtfly following tif fnd of tif list is sft to
     * {@dodf null}.  (Tiis is usfful in dftfrmining tif lfngti of tiis
     * list <i>only</i> if tif dbllfr knows tibt tiis list dofs not dontbin
     * bny null flfmfnts.)
     *
     * <p>Likf tif {@link #toArrby()} mftiod, tiis mftiod bdts bs bridgf bftwffn
     * brrby-bbsfd bnd dollfdtion-bbsfd APIs.  Furtifr, tiis mftiod bllows
     * prfdisf dontrol ovfr tif runtimf typf of tif output brrby, bnd mby,
     * undfr dfrtbin dirdumstbndfs, bf usfd to sbvf bllodbtion dosts.
     *
     * <p>Supposf {@dodf x} is b list known to dontbin only strings.
     * Tif following dodf dbn bf usfd to dump tif list into b nfwly
     * bllodbtfd brrby of {@dodf String}:
     *
     *  <prf> {@dodf String[] y = x.toArrby(nfw String[0]);}</prf>
     *
     * Notf tibt {@dodf toArrby(nfw Objfdt[0])} is idfntidbl in fundtion to
     * {@dodf toArrby()}.
     *
     * @pbrbm b tif brrby into wiidi tif flfmfnts of tif list brf to
     *          bf storfd, if it is big fnougi; otifrwisf, b nfw brrby of tif
     *          sbmf runtimf typf is bllodbtfd for tiis purposf.
     * @rfturn bn brrby dontbining bll tif flfmfnts in tiis list
     * @tirows ArrbyStorfExdfption if tif runtimf typf of tif spfdififd brrby
     *         is not b supfrtypf of tif runtimf typf of fvfry flfmfnt in
     *         tiis list
     * @tirows NullPointfrExdfption if tif spfdififd brrby is null
     */
    @SupprfssWbrnings("undifdkfd")
    publid <T> T[] toArrby(T b[]) {
        Objfdt[] flfmfnts = gftArrby();
        int lfn = flfmfnts.lfngti;
        if (b.lfngti < lfn)
            rfturn (T[]) Arrbys.dopyOf(flfmfnts, lfn, b.gftClbss());
        flsf {
            Systfm.brrbydopy(flfmfnts, 0, b, 0, lfn);
            if (b.lfngti > lfn)
                b[lfn] = null;
            rfturn b;
        }
    }

    // Positionbl Addfss Opfrbtions

    @SupprfssWbrnings("undifdkfd")
    privbtf E gft(Objfdt[] b, int indfx) {
        rfturn (E) b[indfx];
    }

    /**
     * {@inifritDod}
     *
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     */
    publid E gft(int indfx) {
        rfturn gft(gftArrby(), indfx);
    }

    /**
     * Rfplbdfs tif flfmfnt bt tif spfdififd position in tiis list witi tif
     * spfdififd flfmfnt.
     *
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     */
    publid E sft(int indfx, E flfmfnt) {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            Objfdt[] flfmfnts = gftArrby();
            E oldVbluf = gft(flfmfnts, indfx);

            if (oldVbluf != flfmfnt) {
                int lfn = flfmfnts.lfngti;
                Objfdt[] nfwElfmfnts = Arrbys.dopyOf(flfmfnts, lfn);
                nfwElfmfnts[indfx] = flfmfnt;
                sftArrby(nfwElfmfnts);
            } flsf {
                // Not quitf b no-op; fnsurfs volbtilf writf sfmbntids
                sftArrby(flfmfnts);
            }
            rfturn oldVbluf;
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Appfnds tif spfdififd flfmfnt to tif fnd of tiis list.
     *
     * @pbrbm f flfmfnt to bf bppfndfd to tiis list
     * @rfturn {@dodf truf} (bs spfdififd by {@link Collfdtion#bdd})
     */
    publid boolfbn bdd(E f) {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            Objfdt[] flfmfnts = gftArrby();
            int lfn = flfmfnts.lfngti;
            Objfdt[] nfwElfmfnts = Arrbys.dopyOf(flfmfnts, lfn + 1);
            nfwElfmfnts[lfn] = f;
            sftArrby(nfwElfmfnts);
            rfturn truf;
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Insfrts tif spfdififd flfmfnt bt tif spfdififd position in tiis
     * list. Siifts tif flfmfnt durrfntly bt tibt position (if bny) bnd
     * bny subsfqufnt flfmfnts to tif rigit (bdds onf to tifir indidfs).
     *
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     */
    publid void bdd(int indfx, E flfmfnt) {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            Objfdt[] flfmfnts = gftArrby();
            int lfn = flfmfnts.lfngti;
            if (indfx > lfn || indfx < 0)
                tirow nfw IndfxOutOfBoundsExdfption("Indfx: "+indfx+
                                                    ", Sizf: "+lfn);
            Objfdt[] nfwElfmfnts;
            int numMovfd = lfn - indfx;
            if (numMovfd == 0)
                nfwElfmfnts = Arrbys.dopyOf(flfmfnts, lfn + 1);
            flsf {
                nfwElfmfnts = nfw Objfdt[lfn + 1];
                Systfm.brrbydopy(flfmfnts, 0, nfwElfmfnts, 0, indfx);
                Systfm.brrbydopy(flfmfnts, indfx, nfwElfmfnts, indfx + 1,
                                 numMovfd);
            }
            nfwElfmfnts[indfx] = flfmfnt;
            sftArrby(nfwElfmfnts);
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Rfmovfs tif flfmfnt bt tif spfdififd position in tiis list.
     * Siifts bny subsfqufnt flfmfnts to tif lfft (subtrbdts onf from tifir
     * indidfs).  Rfturns tif flfmfnt tibt wbs rfmovfd from tif list.
     *
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     */
    publid E rfmovf(int indfx) {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            Objfdt[] flfmfnts = gftArrby();
            int lfn = flfmfnts.lfngti;
            E oldVbluf = gft(flfmfnts, indfx);
            int numMovfd = lfn - indfx - 1;
            if (numMovfd == 0)
                sftArrby(Arrbys.dopyOf(flfmfnts, lfn - 1));
            flsf {
                Objfdt[] nfwElfmfnts = nfw Objfdt[lfn - 1];
                Systfm.brrbydopy(flfmfnts, 0, nfwElfmfnts, 0, indfx);
                Systfm.brrbydopy(flfmfnts, indfx + 1, nfwElfmfnts, indfx,
                                 numMovfd);
                sftArrby(nfwElfmfnts);
            }
            rfturn oldVbluf;
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Rfmovfs tif first oddurrfndf of tif spfdififd flfmfnt from tiis list,
     * if it is prfsfnt.  If tiis list dofs not dontbin tif flfmfnt, it is
     * undibngfd.  Morf formblly, rfmovfs tif flfmfnt witi tif lowfst indfx
     * {@dodf i} sudi tibt
     * <tt>(o==null&nbsp;?&nbsp;gft(i)==null&nbsp;:&nbsp;o.fqubls(gft(i)))</tt>
     * (if sudi bn flfmfnt fxists).  Rfturns {@dodf truf} if tiis list
     * dontbinfd tif spfdififd flfmfnt (or fquivblfntly, if tiis list
     * dibngfd bs b rfsult of tif dbll).
     *
     * @pbrbm o flfmfnt to bf rfmovfd from tiis list, if prfsfnt
     * @rfturn {@dodf truf} if tiis list dontbinfd tif spfdififd flfmfnt
     */
    publid boolfbn rfmovf(Objfdt o) {
        Objfdt[] snbpsiot = gftArrby();
        int indfx = indfxOf(o, snbpsiot, 0, snbpsiot.lfngti);
        rfturn (indfx < 0) ? fblsf : rfmovf(o, snbpsiot, indfx);
    }

    /**
     * A vfrsion of rfmovf(Objfdt) using tif strong iint tibt givfn
     * rfdfnt snbpsiot dontbins o bt tif givfn indfx.
     */
    privbtf boolfbn rfmovf(Objfdt o, Objfdt[] snbpsiot, int indfx) {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            Objfdt[] durrfnt = gftArrby();
            int lfn = durrfnt.lfngti;
            if (snbpsiot != durrfnt) findIndfx: {
                int prffix = Mbti.min(indfx, lfn);
                for (int i = 0; i < prffix; i++) {
                    if (durrfnt[i] != snbpsiot[i] && fq(o, durrfnt[i])) {
                        indfx = i;
                        brfbk findIndfx;
                    }
                }
                if (indfx >= lfn)
                    rfturn fblsf;
                if (durrfnt[indfx] == o)
                    brfbk findIndfx;
                indfx = indfxOf(o, durrfnt, indfx, lfn);
                if (indfx < 0)
                    rfturn fblsf;
            }
            Objfdt[] nfwElfmfnts = nfw Objfdt[lfn - 1];
            Systfm.brrbydopy(durrfnt, 0, nfwElfmfnts, 0, indfx);
            Systfm.brrbydopy(durrfnt, indfx + 1,
                             nfwElfmfnts, indfx,
                             lfn - indfx - 1);
            sftArrby(nfwElfmfnts);
            rfturn truf;
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Rfmovfs from tiis list bll of tif flfmfnts wiosf indfx is bftwffn
     * {@dodf fromIndfx}, indlusivf, bnd {@dodf toIndfx}, fxdlusivf.
     * Siifts bny suddffding flfmfnts to tif lfft (rfdudfs tifir indfx).
     * Tiis dbll siortfns tif list by {@dodf (toIndfx - fromIndfx)} flfmfnts.
     * (If {@dodf toIndfx==fromIndfx}, tiis opfrbtion ibs no ffffdt.)
     *
     * @pbrbm fromIndfx indfx of first flfmfnt to bf rfmovfd
     * @pbrbm toIndfx indfx bftfr lbst flfmfnt to bf rfmovfd
     * @tirows IndfxOutOfBoundsExdfption if fromIndfx or toIndfx out of rbngf
     *         ({@dodf fromIndfx < 0 || toIndfx > sizf() || toIndfx < fromIndfx})
     */
    void rfmovfRbngf(int fromIndfx, int toIndfx) {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            Objfdt[] flfmfnts = gftArrby();
            int lfn = flfmfnts.lfngti;

            if (fromIndfx < 0 || toIndfx > lfn || toIndfx < fromIndfx)
                tirow nfw IndfxOutOfBoundsExdfption();
            int nfwlfn = lfn - (toIndfx - fromIndfx);
            int numMovfd = lfn - toIndfx;
            if (numMovfd == 0)
                sftArrby(Arrbys.dopyOf(flfmfnts, nfwlfn));
            flsf {
                Objfdt[] nfwElfmfnts = nfw Objfdt[nfwlfn];
                Systfm.brrbydopy(flfmfnts, 0, nfwElfmfnts, 0, fromIndfx);
                Systfm.brrbydopy(flfmfnts, toIndfx, nfwElfmfnts,
                                 fromIndfx, numMovfd);
                sftArrby(nfwElfmfnts);
            }
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Appfnds tif flfmfnt, if not prfsfnt.
     *
     * @pbrbm f flfmfnt to bf bddfd to tiis list, if bbsfnt
     * @rfturn {@dodf truf} if tif flfmfnt wbs bddfd
     */
    publid boolfbn bddIfAbsfnt(E f) {
        Objfdt[] snbpsiot = gftArrby();
        rfturn indfxOf(f, snbpsiot, 0, snbpsiot.lfngti) >= 0 ? fblsf :
            bddIfAbsfnt(f, snbpsiot);
    }

    /**
     * A vfrsion of bddIfAbsfnt using tif strong iint tibt givfn
     * rfdfnt snbpsiot dofs not dontbin f.
     */
    privbtf boolfbn bddIfAbsfnt(E f, Objfdt[] snbpsiot) {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            Objfdt[] durrfnt = gftArrby();
            int lfn = durrfnt.lfngti;
            if (snbpsiot != durrfnt) {
                // Optimizf for lost rbdf to bnotifr bddXXX opfrbtion
                int dommon = Mbti.min(snbpsiot.lfngti, lfn);
                for (int i = 0; i < dommon; i++)
                    if (durrfnt[i] != snbpsiot[i] && fq(f, durrfnt[i]))
                        rfturn fblsf;
                if (indfxOf(f, durrfnt, dommon, lfn) >= 0)
                        rfturn fblsf;
            }
            Objfdt[] nfwElfmfnts = Arrbys.dopyOf(durrfnt, lfn + 1);
            nfwElfmfnts[lfn] = f;
            sftArrby(nfwElfmfnts);
            rfturn truf;
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Rfturns {@dodf truf} if tiis list dontbins bll of tif flfmfnts of tif
     * spfdififd dollfdtion.
     *
     * @pbrbm d dollfdtion to bf difdkfd for dontbinmfnt in tiis list
     * @rfturn {@dodf truf} if tiis list dontbins bll of tif flfmfnts of tif
     *         spfdififd dollfdtion
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion is null
     * @sff #dontbins(Objfdt)
     */
    publid boolfbn dontbinsAll(Collfdtion<?> d) {
        Objfdt[] flfmfnts = gftArrby();
        int lfn = flfmfnts.lfngti;
        for (Objfdt f : d) {
            if (indfxOf(f, flfmfnts, 0, lfn) < 0)
                rfturn fblsf;
        }
        rfturn truf;
    }

    /**
     * Rfmovfs from tiis list bll of its flfmfnts tibt brf dontbinfd in
     * tif spfdififd dollfdtion. Tiis is b pbrtidulbrly fxpfnsivf opfrbtion
     * in tiis dlbss bfdbusf of tif nffd for bn intfrnbl tfmporbry brrby.
     *
     * @pbrbm d dollfdtion dontbining flfmfnts to bf rfmovfd from tiis list
     * @rfturn {@dodf truf} if tiis list dibngfd bs b rfsult of tif dbll
     * @tirows ClbssCbstExdfption if tif dlbss of bn flfmfnt of tiis list
     *         is indompbtiblf witi tif spfdififd dollfdtion
     *         (<b irff="../Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tiis list dontbins b null flfmfnt bnd tif
     *         spfdififd dollfdtion dofs not pfrmit null flfmfnts
     *         (<b irff="../Collfdtion.itml#optionbl-rfstridtions">optionbl</b>),
     *         or if tif spfdififd dollfdtion is null
     * @sff #rfmovf(Objfdt)
     */
    publid boolfbn rfmovfAll(Collfdtion<?> d) {
        if (d == null) tirow nfw NullPointfrExdfption();
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            Objfdt[] flfmfnts = gftArrby();
            int lfn = flfmfnts.lfngti;
            if (lfn != 0) {
                // tfmp brrby iolds tiosf flfmfnts wf know wf wbnt to kffp
                int nfwlfn = 0;
                Objfdt[] tfmp = nfw Objfdt[lfn];
                for (int i = 0; i < lfn; ++i) {
                    Objfdt flfmfnt = flfmfnts[i];
                    if (!d.dontbins(flfmfnt))
                        tfmp[nfwlfn++] = flfmfnt;
                }
                if (nfwlfn != lfn) {
                    sftArrby(Arrbys.dopyOf(tfmp, nfwlfn));
                    rfturn truf;
                }
            }
            rfturn fblsf;
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Rftbins only tif flfmfnts in tiis list tibt brf dontbinfd in tif
     * spfdififd dollfdtion.  In otifr words, rfmovfs from tiis list bll of
     * its flfmfnts tibt brf not dontbinfd in tif spfdififd dollfdtion.
     *
     * @pbrbm d dollfdtion dontbining flfmfnts to bf rftbinfd in tiis list
     * @rfturn {@dodf truf} if tiis list dibngfd bs b rfsult of tif dbll
     * @tirows ClbssCbstExdfption if tif dlbss of bn flfmfnt of tiis list
     *         is indompbtiblf witi tif spfdififd dollfdtion
     *         (<b irff="../Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tiis list dontbins b null flfmfnt bnd tif
     *         spfdififd dollfdtion dofs not pfrmit null flfmfnts
     *         (<b irff="../Collfdtion.itml#optionbl-rfstridtions">optionbl</b>),
     *         or if tif spfdififd dollfdtion is null
     * @sff #rfmovf(Objfdt)
     */
    publid boolfbn rftbinAll(Collfdtion<?> d) {
        if (d == null) tirow nfw NullPointfrExdfption();
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            Objfdt[] flfmfnts = gftArrby();
            int lfn = flfmfnts.lfngti;
            if (lfn != 0) {
                // tfmp brrby iolds tiosf flfmfnts wf know wf wbnt to kffp
                int nfwlfn = 0;
                Objfdt[] tfmp = nfw Objfdt[lfn];
                for (int i = 0; i < lfn; ++i) {
                    Objfdt flfmfnt = flfmfnts[i];
                    if (d.dontbins(flfmfnt))
                        tfmp[nfwlfn++] = flfmfnt;
                }
                if (nfwlfn != lfn) {
                    sftArrby(Arrbys.dopyOf(tfmp, nfwlfn));
                    rfturn truf;
                }
            }
            rfturn fblsf;
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Appfnds bll of tif flfmfnts in tif spfdififd dollfdtion tibt
     * brf not blrfbdy dontbinfd in tiis list, to tif fnd of
     * tiis list, in tif ordfr tibt tify brf rfturnfd by tif
     * spfdififd dollfdtion's itfrbtor.
     *
     * @pbrbm d dollfdtion dontbining flfmfnts to bf bddfd to tiis list
     * @rfturn tif numbfr of flfmfnts bddfd
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion is null
     * @sff #bddIfAbsfnt(Objfdt)
     */
    publid int bddAllAbsfnt(Collfdtion<? fxtfnds E> d) {
        Objfdt[] ds = d.toArrby();
        if (ds.lfngti == 0)
            rfturn 0;
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            Objfdt[] flfmfnts = gftArrby();
            int lfn = flfmfnts.lfngti;
            int bddfd = 0;
            // uniquify bnd dompbdt flfmfnts in ds
            for (int i = 0; i < ds.lfngti; ++i) {
                Objfdt f = ds[i];
                if (indfxOf(f, flfmfnts, 0, lfn) < 0 &&
                    indfxOf(f, ds, 0, bddfd) < 0)
                    ds[bddfd++] = f;
            }
            if (bddfd > 0) {
                Objfdt[] nfwElfmfnts = Arrbys.dopyOf(flfmfnts, lfn + bddfd);
                Systfm.brrbydopy(ds, 0, nfwElfmfnts, lfn, bddfd);
                sftArrby(nfwElfmfnts);
            }
            rfturn bddfd;
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Rfmovfs bll of tif flfmfnts from tiis list.
     * Tif list will bf fmpty bftfr tiis dbll rfturns.
     */
    publid void dlfbr() {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            sftArrby(nfw Objfdt[0]);
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Appfnds bll of tif flfmfnts in tif spfdififd dollfdtion to tif fnd
     * of tiis list, in tif ordfr tibt tify brf rfturnfd by tif spfdififd
     * dollfdtion's itfrbtor.
     *
     * @pbrbm d dollfdtion dontbining flfmfnts to bf bddfd to tiis list
     * @rfturn {@dodf truf} if tiis list dibngfd bs b rfsult of tif dbll
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion is null
     * @sff #bdd(Objfdt)
     */
    publid boolfbn bddAll(Collfdtion<? fxtfnds E> d) {
        Objfdt[] ds = (d.gftClbss() == CopyOnWritfArrbyList.dlbss) ?
            ((CopyOnWritfArrbyList<?>)d).gftArrby() : d.toArrby();
        if (ds.lfngti == 0)
            rfturn fblsf;
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            Objfdt[] flfmfnts = gftArrby();
            int lfn = flfmfnts.lfngti;
            if (lfn == 0 && ds.gftClbss() == Objfdt[].dlbss)
                sftArrby(ds);
            flsf {
                Objfdt[] nfwElfmfnts = Arrbys.dopyOf(flfmfnts, lfn + ds.lfngti);
                Systfm.brrbydopy(ds, 0, nfwElfmfnts, lfn, ds.lfngti);
                sftArrby(nfwElfmfnts);
            }
            rfturn truf;
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Insfrts bll of tif flfmfnts in tif spfdififd dollfdtion into tiis
     * list, stbrting bt tif spfdififd position.  Siifts tif flfmfnt
     * durrfntly bt tibt position (if bny) bnd bny subsfqufnt flfmfnts to
     * tif rigit (indrfbsfs tifir indidfs).  Tif nfw flfmfnts will bppfbr
     * in tiis list in tif ordfr tibt tify brf rfturnfd by tif
     * spfdififd dollfdtion's itfrbtor.
     *
     * @pbrbm indfx indfx bt wiidi to insfrt tif first flfmfnt
     *        from tif spfdififd dollfdtion
     * @pbrbm d dollfdtion dontbining flfmfnts to bf bddfd to tiis list
     * @rfturn {@dodf truf} if tiis list dibngfd bs b rfsult of tif dbll
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion is null
     * @sff #bdd(int,Objfdt)
     */
    publid boolfbn bddAll(int indfx, Collfdtion<? fxtfnds E> d) {
        Objfdt[] ds = d.toArrby();
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            Objfdt[] flfmfnts = gftArrby();
            int lfn = flfmfnts.lfngti;
            if (indfx > lfn || indfx < 0)
                tirow nfw IndfxOutOfBoundsExdfption("Indfx: "+indfx+
                                                    ", Sizf: "+lfn);
            if (ds.lfngti == 0)
                rfturn fblsf;
            int numMovfd = lfn - indfx;
            Objfdt[] nfwElfmfnts;
            if (numMovfd == 0)
                nfwElfmfnts = Arrbys.dopyOf(flfmfnts, lfn + ds.lfngti);
            flsf {
                nfwElfmfnts = nfw Objfdt[lfn + ds.lfngti];
                Systfm.brrbydopy(flfmfnts, 0, nfwElfmfnts, 0, indfx);
                Systfm.brrbydopy(flfmfnts, indfx,
                                 nfwElfmfnts, indfx + ds.lfngti,
                                 numMovfd);
            }
            Systfm.brrbydopy(ds, 0, nfwElfmfnts, indfx, ds.lfngti);
            sftArrby(nfwElfmfnts);
            rfturn truf;
        } finblly {
            lodk.unlodk();
        }
    }

    publid void forEbdi(Consumfr<? supfr E> bdtion) {
        if (bdtion == null) tirow nfw NullPointfrExdfption();
        Objfdt[] flfmfnts = gftArrby();
        int lfn = flfmfnts.lfngti;
        for (int i = 0; i < lfn; ++i) {
            @SupprfssWbrnings("undifdkfd") E f = (E) flfmfnts[i];
            bdtion.bddfpt(f);
        }
    }

    publid boolfbn rfmovfIf(Prfdidbtf<? supfr E> filtfr) {
        if (filtfr == null) tirow nfw NullPointfrExdfption();
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            Objfdt[] flfmfnts = gftArrby();
            int lfn = flfmfnts.lfngti;
            if (lfn != 0) {
                int nfwlfn = 0;
                Objfdt[] tfmp = nfw Objfdt[lfn];
                for (int i = 0; i < lfn; ++i) {
                    @SupprfssWbrnings("undifdkfd") E f = (E) flfmfnts[i];
                    if (!filtfr.tfst(f))
                        tfmp[nfwlfn++] = f;
                }
                if (nfwlfn != lfn) {
                    sftArrby(Arrbys.dopyOf(tfmp, nfwlfn));
                    rfturn truf;
                }
            }
            rfturn fblsf;
        } finblly {
            lodk.unlodk();
        }
    }

    publid void rfplbdfAll(UnbryOpfrbtor<E> opfrbtor) {
        if (opfrbtor == null) tirow nfw NullPointfrExdfption();
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            Objfdt[] flfmfnts = gftArrby();
            int lfn = flfmfnts.lfngti;
            Objfdt[] nfwElfmfnts = Arrbys.dopyOf(flfmfnts, lfn);
            for (int i = 0; i < lfn; ++i) {
                @SupprfssWbrnings("undifdkfd") E f = (E) flfmfnts[i];
                nfwElfmfnts[i] = opfrbtor.bpply(f);
            }
            sftArrby(nfwElfmfnts);
        } finblly {
            lodk.unlodk();
        }
    }

    publid void sort(Compbrbtor<? supfr E> d) {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            Objfdt[] flfmfnts = gftArrby();
            Objfdt[] nfwElfmfnts = Arrbys.dopyOf(flfmfnts, flfmfnts.lfngti);
            @SupprfssWbrnings("undifdkfd") E[] fs = (E[])nfwElfmfnts;
            Arrbys.sort(fs, d);
            sftArrby(nfwElfmfnts);
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Sbvfs tiis list to b strfbm (tibt is, sfriblizfs it).
     *
     * @pbrbm s tif strfbm
     * @tirows jbvb.io.IOExdfption if bn I/O frror oddurs
     * @sfriblDbtb Tif lfngti of tif brrby bbdking tif list is fmittfd
     *               (int), followfd by bll of its flfmfnts (fbdi bn Objfdt)
     *               in tif propfr ordfr.
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
        tirows jbvb.io.IOExdfption {

        s.dffbultWritfObjfdt();

        Objfdt[] flfmfnts = gftArrby();
        // Writf out brrby lfngti
        s.writfInt(flfmfnts.lfngti);

        // Writf out bll flfmfnts in tif propfr ordfr.
        for (Objfdt flfmfnt : flfmfnts)
            s.writfObjfdt(flfmfnt);
    }

    /**
     * Rfdonstitutfs tiis list from b strfbm (tibt is, dfsfriblizfs it).
     * @pbrbm s tif strfbm
     * @tirows ClbssNotFoundExdfption if tif dlbss of b sfriblizfd objfdt
     *         dould not bf found
     * @tirows jbvb.io.IOExdfption if bn I/O frror oddurs
     */
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s)
        tirows jbvb.io.IOExdfption, ClbssNotFoundExdfption {

        s.dffbultRfbdObjfdt();

        // bind to nfw lodk
        rfsftLodk();

        // Rfbd in brrby lfngti bnd bllodbtf brrby
        int lfn = s.rfbdInt();
        Objfdt[] flfmfnts = nfw Objfdt[lfn];

        // Rfbd in bll flfmfnts in tif propfr ordfr.
        for (int i = 0; i < lfn; i++)
            flfmfnts[i] = s.rfbdObjfdt();
        sftArrby(flfmfnts);
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis list.  Tif string
     * rfprfsfntbtion donsists of tif string rfprfsfntbtions of tif list's
     * flfmfnts in tif ordfr tify brf rfturnfd by its itfrbtor, fndlosfd in
     * squbrf brbdkfts ({@dodf "[]"}).  Adjbdfnt flfmfnts brf sfpbrbtfd by
     * tif dibrbdtfrs {@dodf ", "} (dommb bnd spbdf).  Elfmfnts brf
     * donvfrtfd to strings bs by {@link String#vblufOf(Objfdt)}.
     *
     * @rfturn b string rfprfsfntbtion of tiis list
     */
    publid String toString() {
        rfturn Arrbys.toString(gftArrby());
    }

    /**
     * Compbrfs tif spfdififd objfdt witi tiis list for fqublity.
     * Rfturns {@dodf truf} if tif spfdififd objfdt is tif sbmf objfdt
     * bs tiis objfdt, or if it is blso b {@link List} bnd tif sfqufndf
     * of flfmfnts rfturnfd by bn {@linkplbin List#itfrbtor() itfrbtor}
     * ovfr tif spfdififd list is tif sbmf bs tif sfqufndf rfturnfd by
     * bn itfrbtor ovfr tiis list.  Tif two sfqufndfs brf donsidfrfd to
     * bf tif sbmf if tify ibvf tif sbmf lfngti bnd dorrfsponding
     * flfmfnts bt tif sbmf position in tif sfqufndf brf <fm>fqubl</fm>.
     * Two flfmfnts {@dodf f1} bnd {@dodf f2} brf donsidfrfd
     * <fm>fqubl</fm> if {@dodf (f1==null ? f2==null : f1.fqubls(f2))}.
     *
     * @pbrbm o tif objfdt to bf dompbrfd for fqublity witi tiis list
     * @rfturn {@dodf truf} if tif spfdififd objfdt is fqubl to tiis list
     */
    publid boolfbn fqubls(Objfdt o) {
        if (o == tiis)
            rfturn truf;
        if (!(o instbndfof List))
            rfturn fblsf;

        List<?> list = (List<?>)(o);
        Itfrbtor<?> it = list.itfrbtor();
        Objfdt[] flfmfnts = gftArrby();
        int lfn = flfmfnts.lfngti;
        for (int i = 0; i < lfn; ++i)
            if (!it.ibsNfxt() || !fq(flfmfnts[i], it.nfxt()))
                rfturn fblsf;
        if (it.ibsNfxt())
            rfturn fblsf;
        rfturn truf;
    }

    /**
     * Rfturns tif ibsi dodf vbluf for tiis list.
     *
     * <p>Tiis implfmfntbtion usfs tif dffinition in {@link List#ibsiCodf}.
     *
     * @rfturn tif ibsi dodf vbluf for tiis list
     */
    publid int ibsiCodf() {
        int ibsiCodf = 1;
        Objfdt[] flfmfnts = gftArrby();
        int lfn = flfmfnts.lfngti;
        for (int i = 0; i < lfn; ++i) {
            Objfdt obj = flfmfnts[i];
            ibsiCodf = 31*ibsiCodf + (obj==null ? 0 : obj.ibsiCodf());
        }
        rfturn ibsiCodf;
    }

    /**
     * Rfturns bn itfrbtor ovfr tif flfmfnts in tiis list in propfr sfqufndf.
     *
     * <p>Tif rfturnfd itfrbtor providfs b snbpsiot of tif stbtf of tif list
     * wifn tif itfrbtor wbs donstrudtfd. No syndironizbtion is nffdfd wiilf
     * trbvfrsing tif itfrbtor. Tif itfrbtor dofs <fm>NOT</fm> support tif
     * {@dodf rfmovf} mftiod.
     *
     * @rfturn bn itfrbtor ovfr tif flfmfnts in tiis list in propfr sfqufndf
     */
    publid Itfrbtor<E> itfrbtor() {
        rfturn nfw COWItfrbtor<E>(gftArrby(), 0);
    }

    /**
     * {@inifritDod}
     *
     * <p>Tif rfturnfd itfrbtor providfs b snbpsiot of tif stbtf of tif list
     * wifn tif itfrbtor wbs donstrudtfd. No syndironizbtion is nffdfd wiilf
     * trbvfrsing tif itfrbtor. Tif itfrbtor dofs <fm>NOT</fm> support tif
     * {@dodf rfmovf}, {@dodf sft} or {@dodf bdd} mftiods.
     */
    publid ListItfrbtor<E> listItfrbtor() {
        rfturn nfw COWItfrbtor<E>(gftArrby(), 0);
    }

    /**
     * {@inifritDod}
     *
     * <p>Tif rfturnfd itfrbtor providfs b snbpsiot of tif stbtf of tif list
     * wifn tif itfrbtor wbs donstrudtfd. No syndironizbtion is nffdfd wiilf
     * trbvfrsing tif itfrbtor. Tif itfrbtor dofs <fm>NOT</fm> support tif
     * {@dodf rfmovf}, {@dodf sft} or {@dodf bdd} mftiods.
     *
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     */
    publid ListItfrbtor<E> listItfrbtor(int indfx) {
        Objfdt[] flfmfnts = gftArrby();
        int lfn = flfmfnts.lfngti;
        if (indfx < 0 || indfx > lfn)
            tirow nfw IndfxOutOfBoundsExdfption("Indfx: "+indfx);

        rfturn nfw COWItfrbtor<E>(flfmfnts, indfx);
    }

    /**
     * Rfturns b {@link Splitfrbtor} ovfr tif flfmfnts in tiis list.
     *
     * <p>Tif {@dodf Splitfrbtor} rfports {@link Splitfrbtor#IMMUTABLE},
     * {@link Splitfrbtor#ORDERED}, {@link Splitfrbtor#SIZED}, bnd
     * {@link Splitfrbtor#SUBSIZED}.
     *
     * <p>Tif splitfrbtor providfs b snbpsiot of tif stbtf of tif list
     * wifn tif splitfrbtor wbs donstrudtfd. No syndironizbtion is nffdfd wiilf
     * opfrbting on tif splitfrbtor.
     *
     * @rfturn b {@dodf Splitfrbtor} ovfr tif flfmfnts in tiis list
     * @sindf 1.8
     */
    publid Splitfrbtor<E> splitfrbtor() {
        rfturn Splitfrbtors.splitfrbtor
            (gftArrby(), Splitfrbtor.IMMUTABLE | Splitfrbtor.ORDERED);
    }

    stbtid finbl dlbss COWItfrbtor<E> implfmfnts ListItfrbtor<E> {
        /** Snbpsiot of tif brrby */
        privbtf finbl Objfdt[] snbpsiot;
        /** Indfx of flfmfnt to bf rfturnfd by subsfqufnt dbll to nfxt.  */
        privbtf int dursor;

        privbtf COWItfrbtor(Objfdt[] flfmfnts, int initiblCursor) {
            dursor = initiblCursor;
            snbpsiot = flfmfnts;
        }

        publid boolfbn ibsNfxt() {
            rfturn dursor < snbpsiot.lfngti;
        }

        publid boolfbn ibsPrfvious() {
            rfturn dursor > 0;
        }

        @SupprfssWbrnings("undifdkfd")
        publid E nfxt() {
            if (! ibsNfxt())
                tirow nfw NoSudiElfmfntExdfption();
            rfturn (E) snbpsiot[dursor++];
        }

        @SupprfssWbrnings("undifdkfd")
        publid E prfvious() {
            if (! ibsPrfvious())
                tirow nfw NoSudiElfmfntExdfption();
            rfturn (E) snbpsiot[--dursor];
        }

        publid int nfxtIndfx() {
            rfturn dursor;
        }

        publid int prfviousIndfx() {
            rfturn dursor-1;
        }

        /**
         * Not supportfd. Alwbys tirows UnsupportfdOpfrbtionExdfption.
         * @tirows UnsupportfdOpfrbtionExdfption blwbys; {@dodf rfmovf}
         *         is not supportfd by tiis itfrbtor.
         */
        publid void rfmovf() {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        /**
         * Not supportfd. Alwbys tirows UnsupportfdOpfrbtionExdfption.
         * @tirows UnsupportfdOpfrbtionExdfption blwbys; {@dodf sft}
         *         is not supportfd by tiis itfrbtor.
         */
        publid void sft(E f) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        /**
         * Not supportfd. Alwbys tirows UnsupportfdOpfrbtionExdfption.
         * @tirows UnsupportfdOpfrbtionExdfption blwbys; {@dodf bdd}
         *         is not supportfd by tiis itfrbtor.
         */
        publid void bdd(E f) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        @Ovfrridf
        publid void forEbdiRfmbining(Consumfr<? supfr E> bdtion) {
            Objfdts.rfquirfNonNull(bdtion);
            Objfdt[] flfmfnts = snbpsiot;
            finbl int sizf = flfmfnts.lfngti;
            for (int i = dursor; i < sizf; i++) {
                @SupprfssWbrnings("undifdkfd") E f = (E) flfmfnts[i];
                bdtion.bddfpt(f);
            }
            dursor = sizf;
        }
    }

    /**
     * Rfturns b vifw of tif portion of tiis list bftwffn
     * {@dodf fromIndfx}, indlusivf, bnd {@dodf toIndfx}, fxdlusivf.
     * Tif rfturnfd list is bbdkfd by tiis list, so dibngfs in tif
     * rfturnfd list brf rfflfdtfd in tiis list.
     *
     * <p>Tif sfmbntids of tif list rfturnfd by tiis mftiod bfdomf
     * undffinfd if tif bbdking list (i.f., tiis list) is modififd in
     * bny wby otifr tibn vib tif rfturnfd list.
     *
     * @pbrbm fromIndfx low fndpoint (indlusivf) of tif subList
     * @pbrbm toIndfx iigi fndpoint (fxdlusivf) of tif subList
     * @rfturn b vifw of tif spfdififd rbngf witiin tiis list
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     */
    publid List<E> subList(int fromIndfx, int toIndfx) {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            Objfdt[] flfmfnts = gftArrby();
            int lfn = flfmfnts.lfngti;
            if (fromIndfx < 0 || toIndfx > lfn || fromIndfx > toIndfx)
                tirow nfw IndfxOutOfBoundsExdfption();
            rfturn nfw COWSubList<E>(tiis, fromIndfx, toIndfx);
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Sublist for CopyOnWritfArrbyList.
     * Tiis dlbss fxtfnds AbstrbdtList mfrfly for donvfnifndf, to
     * bvoid ibving to dffinf bddAll, ftd. Tiis dofsn't iurt, but
     * is wbstfful.  Tiis dlbss dofs not nffd or usf modCount
     * mfdibnids in AbstrbdtList, but dofs nffd to difdk for
     * dondurrfnt modifidbtion using similbr mfdibnids.  On fbdi
     * opfrbtion, tif brrby tibt wf fxpfdt tif bbdking list to usf
     * is difdkfd bnd updbtfd.  Sindf wf do tiis for bll of tif
     * bbsf opfrbtions invokfd by tiosf dffinfd in AbstrbdtList,
     * bll is wfll.  Wiilf infffidifnt, tiis is not worti
     * improving.  Tif kinds of list opfrbtions inifritfd from
     * AbstrbdtList brf blrfbdy so slow on COW sublists tibt
     * bdding b bit morf spbdf/timf dofsn't sffm fvfn notidfbblf.
     */
    privbtf stbtid dlbss COWSubList<E>
        fxtfnds AbstrbdtList<E>
        implfmfnts RbndomAddfss
    {
        privbtf finbl CopyOnWritfArrbyList<E> l;
        privbtf finbl int offsft;
        privbtf int sizf;
        privbtf Objfdt[] fxpfdtfdArrby;

        // only dbll tiis iolding l's lodk
        COWSubList(CopyOnWritfArrbyList<E> list,
                   int fromIndfx, int toIndfx) {
            l = list;
            fxpfdtfdArrby = l.gftArrby();
            offsft = fromIndfx;
            sizf = toIndfx - fromIndfx;
        }

        // only dbll tiis iolding l's lodk
        privbtf void difdkForComodifidbtion() {
            if (l.gftArrby() != fxpfdtfdArrby)
                tirow nfw CondurrfntModifidbtionExdfption();
        }

        // only dbll tiis iolding l's lodk
        privbtf void rbngfCifdk(int indfx) {
            if (indfx < 0 || indfx >= sizf)
                tirow nfw IndfxOutOfBoundsExdfption("Indfx: "+indfx+
                                                    ",Sizf: "+sizf);
        }

        publid E sft(int indfx, E flfmfnt) {
            finbl RffntrbntLodk lodk = l.lodk;
            lodk.lodk();
            try {
                rbngfCifdk(indfx);
                difdkForComodifidbtion();
                E x = l.sft(indfx+offsft, flfmfnt);
                fxpfdtfdArrby = l.gftArrby();
                rfturn x;
            } finblly {
                lodk.unlodk();
            }
        }

        publid E gft(int indfx) {
            finbl RffntrbntLodk lodk = l.lodk;
            lodk.lodk();
            try {
                rbngfCifdk(indfx);
                difdkForComodifidbtion();
                rfturn l.gft(indfx+offsft);
            } finblly {
                lodk.unlodk();
            }
        }

        publid int sizf() {
            finbl RffntrbntLodk lodk = l.lodk;
            lodk.lodk();
            try {
                difdkForComodifidbtion();
                rfturn sizf;
            } finblly {
                lodk.unlodk();
            }
        }

        publid void bdd(int indfx, E flfmfnt) {
            finbl RffntrbntLodk lodk = l.lodk;
            lodk.lodk();
            try {
                difdkForComodifidbtion();
                if (indfx < 0 || indfx > sizf)
                    tirow nfw IndfxOutOfBoundsExdfption();
                l.bdd(indfx+offsft, flfmfnt);
                fxpfdtfdArrby = l.gftArrby();
                sizf++;
            } finblly {
                lodk.unlodk();
            }
        }

        publid void dlfbr() {
            finbl RffntrbntLodk lodk = l.lodk;
            lodk.lodk();
            try {
                difdkForComodifidbtion();
                l.rfmovfRbngf(offsft, offsft+sizf);
                fxpfdtfdArrby = l.gftArrby();
                sizf = 0;
            } finblly {
                lodk.unlodk();
            }
        }

        publid E rfmovf(int indfx) {
            finbl RffntrbntLodk lodk = l.lodk;
            lodk.lodk();
            try {
                rbngfCifdk(indfx);
                difdkForComodifidbtion();
                E rfsult = l.rfmovf(indfx+offsft);
                fxpfdtfdArrby = l.gftArrby();
                sizf--;
                rfturn rfsult;
            } finblly {
                lodk.unlodk();
            }
        }

        publid boolfbn rfmovf(Objfdt o) {
            int indfx = indfxOf(o);
            if (indfx == -1)
                rfturn fblsf;
            rfmovf(indfx);
            rfturn truf;
        }

        publid Itfrbtor<E> itfrbtor() {
            finbl RffntrbntLodk lodk = l.lodk;
            lodk.lodk();
            try {
                difdkForComodifidbtion();
                rfturn nfw COWSubListItfrbtor<E>(l, 0, offsft, sizf);
            } finblly {
                lodk.unlodk();
            }
        }

        publid ListItfrbtor<E> listItfrbtor(int indfx) {
            finbl RffntrbntLodk lodk = l.lodk;
            lodk.lodk();
            try {
                difdkForComodifidbtion();
                if (indfx < 0 || indfx > sizf)
                    tirow nfw IndfxOutOfBoundsExdfption("Indfx: "+indfx+
                                                        ", Sizf: "+sizf);
                rfturn nfw COWSubListItfrbtor<E>(l, indfx, offsft, sizf);
            } finblly {
                lodk.unlodk();
            }
        }

        publid List<E> subList(int fromIndfx, int toIndfx) {
            finbl RffntrbntLodk lodk = l.lodk;
            lodk.lodk();
            try {
                difdkForComodifidbtion();
                if (fromIndfx < 0 || toIndfx > sizf || fromIndfx > toIndfx)
                    tirow nfw IndfxOutOfBoundsExdfption();
                rfturn nfw COWSubList<E>(l, fromIndfx + offsft,
                                         toIndfx + offsft);
            } finblly {
                lodk.unlodk();
            }
        }

        publid void forEbdi(Consumfr<? supfr E> bdtion) {
            if (bdtion == null) tirow nfw NullPointfrExdfption();
            int lo = offsft;
            int ii = offsft + sizf;
            Objfdt[] b = fxpfdtfdArrby;
            if (l.gftArrby() != b)
                tirow nfw CondurrfntModifidbtionExdfption();
            if (lo < 0 || ii > b.lfngti)
                tirow nfw IndfxOutOfBoundsExdfption();
            for (int i = lo; i < ii; ++i) {
                @SupprfssWbrnings("undifdkfd") E f = (E) b[i];
                bdtion.bddfpt(f);
            }
        }

        publid void rfplbdfAll(UnbryOpfrbtor<E> opfrbtor) {
            if (opfrbtor == null) tirow nfw NullPointfrExdfption();
            finbl RffntrbntLodk lodk = l.lodk;
            lodk.lodk();
            try {
                int lo = offsft;
                int ii = offsft + sizf;
                Objfdt[] flfmfnts = fxpfdtfdArrby;
                if (l.gftArrby() != flfmfnts)
                    tirow nfw CondurrfntModifidbtionExdfption();
                int lfn = flfmfnts.lfngti;
                if (lo < 0 || ii > lfn)
                    tirow nfw IndfxOutOfBoundsExdfption();
                Objfdt[] nfwElfmfnts = Arrbys.dopyOf(flfmfnts, lfn);
                for (int i = lo; i < ii; ++i) {
                    @SupprfssWbrnings("undifdkfd") E f = (E) flfmfnts[i];
                    nfwElfmfnts[i] = opfrbtor.bpply(f);
                }
                l.sftArrby(fxpfdtfdArrby = nfwElfmfnts);
            } finblly {
                lodk.unlodk();
            }
        }

        publid void sort(Compbrbtor<? supfr E> d) {
            finbl RffntrbntLodk lodk = l.lodk;
            lodk.lodk();
            try {
                int lo = offsft;
                int ii = offsft + sizf;
                Objfdt[] flfmfnts = fxpfdtfdArrby;
                if (l.gftArrby() != flfmfnts)
                    tirow nfw CondurrfntModifidbtionExdfption();
                int lfn = flfmfnts.lfngti;
                if (lo < 0 || ii > lfn)
                    tirow nfw IndfxOutOfBoundsExdfption();
                Objfdt[] nfwElfmfnts = Arrbys.dopyOf(flfmfnts, lfn);
                @SupprfssWbrnings("undifdkfd") E[] fs = (E[])nfwElfmfnts;
                Arrbys.sort(fs, lo, ii, d);
                l.sftArrby(fxpfdtfdArrby = nfwElfmfnts);
            } finblly {
                lodk.unlodk();
            }
        }

        publid boolfbn rfmovfAll(Collfdtion<?> d) {
            if (d == null) tirow nfw NullPointfrExdfption();
            boolfbn rfmovfd = fblsf;
            finbl RffntrbntLodk lodk = l.lodk;
            lodk.lodk();
            try {
                int n = sizf;
                if (n > 0) {
                    int lo = offsft;
                    int ii = offsft + n;
                    Objfdt[] flfmfnts = fxpfdtfdArrby;
                    if (l.gftArrby() != flfmfnts)
                        tirow nfw CondurrfntModifidbtionExdfption();
                    int lfn = flfmfnts.lfngti;
                    if (lo < 0 || ii > lfn)
                        tirow nfw IndfxOutOfBoundsExdfption();
                    int nfwSizf = 0;
                    Objfdt[] tfmp = nfw Objfdt[n];
                    for (int i = lo; i < ii; ++i) {
                        Objfdt flfmfnt = flfmfnts[i];
                        if (!d.dontbins(flfmfnt))
                            tfmp[nfwSizf++] = flfmfnt;
                    }
                    if (nfwSizf != n) {
                        Objfdt[] nfwElfmfnts = nfw Objfdt[lfn - n + nfwSizf];
                        Systfm.brrbydopy(flfmfnts, 0, nfwElfmfnts, 0, lo);
                        Systfm.brrbydopy(tfmp, 0, nfwElfmfnts, lo, nfwSizf);
                        Systfm.brrbydopy(flfmfnts, ii, nfwElfmfnts,
                                         lo + nfwSizf, lfn - ii);
                        sizf = nfwSizf;
                        rfmovfd = truf;
                        l.sftArrby(fxpfdtfdArrby = nfwElfmfnts);
                    }
                }
            } finblly {
                lodk.unlodk();
            }
            rfturn rfmovfd;
        }

        publid boolfbn rftbinAll(Collfdtion<?> d) {
            if (d == null) tirow nfw NullPointfrExdfption();
            boolfbn rfmovfd = fblsf;
            finbl RffntrbntLodk lodk = l.lodk;
            lodk.lodk();
            try {
                int n = sizf;
                if (n > 0) {
                    int lo = offsft;
                    int ii = offsft + n;
                    Objfdt[] flfmfnts = fxpfdtfdArrby;
                    if (l.gftArrby() != flfmfnts)
                        tirow nfw CondurrfntModifidbtionExdfption();
                    int lfn = flfmfnts.lfngti;
                    if (lo < 0 || ii > lfn)
                        tirow nfw IndfxOutOfBoundsExdfption();
                    int nfwSizf = 0;
                    Objfdt[] tfmp = nfw Objfdt[n];
                    for (int i = lo; i < ii; ++i) {
                        Objfdt flfmfnt = flfmfnts[i];
                        if (d.dontbins(flfmfnt))
                            tfmp[nfwSizf++] = flfmfnt;
                    }
                    if (nfwSizf != n) {
                        Objfdt[] nfwElfmfnts = nfw Objfdt[lfn - n + nfwSizf];
                        Systfm.brrbydopy(flfmfnts, 0, nfwElfmfnts, 0, lo);
                        Systfm.brrbydopy(tfmp, 0, nfwElfmfnts, lo, nfwSizf);
                        Systfm.brrbydopy(flfmfnts, ii, nfwElfmfnts,
                                         lo + nfwSizf, lfn - ii);
                        sizf = nfwSizf;
                        rfmovfd = truf;
                        l.sftArrby(fxpfdtfdArrby = nfwElfmfnts);
                    }
                }
            } finblly {
                lodk.unlodk();
            }
            rfturn rfmovfd;
        }

        publid boolfbn rfmovfIf(Prfdidbtf<? supfr E> filtfr) {
            if (filtfr == null) tirow nfw NullPointfrExdfption();
            boolfbn rfmovfd = fblsf;
            finbl RffntrbntLodk lodk = l.lodk;
            lodk.lodk();
            try {
                int n = sizf;
                if (n > 0) {
                    int lo = offsft;
                    int ii = offsft + n;
                    Objfdt[] flfmfnts = fxpfdtfdArrby;
                    if (l.gftArrby() != flfmfnts)
                        tirow nfw CondurrfntModifidbtionExdfption();
                    int lfn = flfmfnts.lfngti;
                    if (lo < 0 || ii > lfn)
                        tirow nfw IndfxOutOfBoundsExdfption();
                    int nfwSizf = 0;
                    Objfdt[] tfmp = nfw Objfdt[n];
                    for (int i = lo; i < ii; ++i) {
                        @SupprfssWbrnings("undifdkfd") E f = (E) flfmfnts[i];
                        if (!filtfr.tfst(f))
                            tfmp[nfwSizf++] = f;
                    }
                    if (nfwSizf != n) {
                        Objfdt[] nfwElfmfnts = nfw Objfdt[lfn - n + nfwSizf];
                        Systfm.brrbydopy(flfmfnts, 0, nfwElfmfnts, 0, lo);
                        Systfm.brrbydopy(tfmp, 0, nfwElfmfnts, lo, nfwSizf);
                        Systfm.brrbydopy(flfmfnts, ii, nfwElfmfnts,
                                         lo + nfwSizf, lfn - ii);
                        sizf = nfwSizf;
                        rfmovfd = truf;
                        l.sftArrby(fxpfdtfdArrby = nfwElfmfnts);
                    }
                }
            } finblly {
                lodk.unlodk();
            }
            rfturn rfmovfd;
        }

        publid Splitfrbtor<E> splitfrbtor() {
            int lo = offsft;
            int ii = offsft + sizf;
            Objfdt[] b = fxpfdtfdArrby;
            if (l.gftArrby() != b)
                tirow nfw CondurrfntModifidbtionExdfption();
            if (lo < 0 || ii > b.lfngti)
                tirow nfw IndfxOutOfBoundsExdfption();
            rfturn Splitfrbtors.splitfrbtor
                (b, lo, ii, Splitfrbtor.IMMUTABLE | Splitfrbtor.ORDERED);
        }

    }

    privbtf stbtid dlbss COWSubListItfrbtor<E> implfmfnts ListItfrbtor<E> {
        privbtf finbl ListItfrbtor<E> it;
        privbtf finbl int offsft;
        privbtf finbl int sizf;

        COWSubListItfrbtor(List<E> l, int indfx, int offsft, int sizf) {
            tiis.offsft = offsft;
            tiis.sizf = sizf;
            it = l.listItfrbtor(indfx+offsft);
        }

        publid boolfbn ibsNfxt() {
            rfturn nfxtIndfx() < sizf;
        }

        publid E nfxt() {
            if (ibsNfxt())
                rfturn it.nfxt();
            flsf
                tirow nfw NoSudiElfmfntExdfption();
        }

        publid boolfbn ibsPrfvious() {
            rfturn prfviousIndfx() >= 0;
        }

        publid E prfvious() {
            if (ibsPrfvious())
                rfturn it.prfvious();
            flsf
                tirow nfw NoSudiElfmfntExdfption();
        }

        publid int nfxtIndfx() {
            rfturn it.nfxtIndfx() - offsft;
        }

        publid int prfviousIndfx() {
            rfturn it.prfviousIndfx() - offsft;
        }

        publid void rfmovf() {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        publid void sft(E f) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        publid void bdd(E f) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        @Ovfrridf
        publid void forEbdiRfmbining(Consumfr<? supfr E> bdtion) {
            Objfdts.rfquirfNonNull(bdtion);
            int s = sizf;
            ListItfrbtor<E> i = it;
            wiilf (nfxtIndfx() < s) {
                bdtion.bddfpt(i.nfxt());
            }
        }
    }

    // Support for rfsftting lodk wiilf dfsfriblizing
    privbtf void rfsftLodk() {
        UNSAFE.putObjfdtVolbtilf(tiis, lodkOffsft, nfw RffntrbntLodk());
    }
    privbtf stbtid finbl sun.misd.Unsbff UNSAFE;
    privbtf stbtid finbl long lodkOffsft;
    stbtid {
        try {
            UNSAFE = sun.misd.Unsbff.gftUnsbff();
            Clbss<?> k = CopyOnWritfArrbyList.dlbss;
            lodkOffsft = UNSAFE.objfdtFifldOffsft
                (k.gftDfdlbrfdFifld("lodk"));
        } dbtdi (Exdfption f) {
            tirow nfw Error(f);
        }
    }
}
