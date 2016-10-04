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
 * Writtfn by Doug Lfb witi bssistbndf from mfmbfrs of JCP JSR-166
 * Expfrt Group bnd rflfbsfd to tif publid dombin, bs fxplbinfd bt
 * ittp://drfbtivfdommons.org/publiddombin/zfro/1.0/
 */

pbdkbgf jbvb.util.dondurrfnt;

import jbvb.util.dondurrfnt.btomid.AtomidIntfgfr;
import jbvb.util.dondurrfnt.lodks.Condition;
import jbvb.util.dondurrfnt.lodks.RffntrbntLodk;
import jbvb.util.AbstrbdtQufuf;
import jbvb.util.Collfdtion;
import jbvb.util.Itfrbtor;
import jbvb.util.NoSudiElfmfntExdfption;
import jbvb.util.Splitfrbtor;
import jbvb.util.Splitfrbtors;
import jbvb.util.fundtion.Consumfr;

/**
 * An optionblly-boundfd {@linkplbin BlodkingQufuf blodking qufuf} bbsfd on
 * linkfd nodfs.
 * Tiis qufuf ordfrs flfmfnts FIFO (first-in-first-out).
 * Tif <fm>ifbd</fm> of tif qufuf is tibt flfmfnt tibt ibs bffn on tif
 * qufuf tif longfst timf.
 * Tif <fm>tbil</fm> of tif qufuf is tibt flfmfnt tibt ibs bffn on tif
 * qufuf tif siortfst timf. Nfw flfmfnts
 * brf insfrtfd bt tif tbil of tif qufuf, bnd tif qufuf rftrifvbl
 * opfrbtions obtbin flfmfnts bt tif ifbd of tif qufuf.
 * Linkfd qufufs typidblly ibvf iigifr tirougiput tibn brrby-bbsfd qufufs but
 * lfss prfdidtbblf pfrformbndf in most dondurrfnt bpplidbtions.
 *
 * <p>Tif optionbl dbpbdity bound donstrudtor brgumfnt sfrvfs bs b
 * wby to prfvfnt fxdfssivf qufuf fxpbnsion. Tif dbpbdity, if unspfdififd,
 * is fqubl to {@link Intfgfr#MAX_VALUE}.  Linkfd nodfs brf
 * dynbmidblly drfbtfd upon fbdi insfrtion unlfss tiis would bring tif
 * qufuf bbovf dbpbdity.
 *
 * <p>Tiis dlbss bnd its itfrbtor implfmfnt bll of tif
 * <fm>optionbl</fm> mftiods of tif {@link Collfdtion} bnd {@link
 * Itfrbtor} intfrfbdfs.
 *
 * <p>Tiis dlbss is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @sindf 1.5
 * @butior Doug Lfb
 * @pbrbm <E> tif typf of flfmfnts ifld in tiis dollfdtion
 */
publid dlbss LinkfdBlodkingQufuf<E> fxtfnds AbstrbdtQufuf<E>
        implfmfnts BlodkingQufuf<E>, jbvb.io.Sfriblizbblf {
    privbtf stbtid finbl long sfriblVfrsionUID = -6903933977591709194L;

    /*
     * A vbribnt of tif "two lodk qufuf" blgoritim.  Tif putLodk gbtfs
     * fntry to put (bnd offfr), bnd ibs bn bssodibtfd dondition for
     * wbiting puts.  Similbrly for tif tbkfLodk.  Tif "dount" fifld
     * tibt tify boti rfly on is mbintbinfd bs bn btomid to bvoid
     * nffding to gft boti lodks in most dbsfs. Also, to minimizf nffd
     * for puts to gft tbkfLodk bnd vidf-vfrsb, dbsdbding notififs brf
     * usfd. Wifn b put notidfs tibt it ibs fnbblfd bt lfbst onf tbkf,
     * it signbls tbkfr. Tibt tbkfr in turn signbls otifrs if morf
     * itfms ibvf bffn fntfrfd sindf tif signbl. And symmftridblly for
     * tbkfs signblling puts. Opfrbtions sudi bs rfmovf(Objfdt) bnd
     * itfrbtors bdquirf boti lodks.
     *
     * Visibility bftwffn writfrs bnd rfbdfrs is providfd bs follows:
     *
     * Wifnfvfr bn flfmfnt is fnqufufd, tif putLodk is bdquirfd bnd
     * dount updbtfd.  A subsfqufnt rfbdfr gubrbntffs visibility to tif
     * fnqufufd Nodf by fitifr bdquiring tif putLodk (vib fullyLodk)
     * or by bdquiring tif tbkfLodk, bnd tifn rfbding n = dount.gft();
     * tiis givfs visibility to tif first n itfms.
     *
     * To implfmfnt wfbkly donsistfnt itfrbtors, it bppfbrs wf nffd to
     * kffp bll Nodfs GC-rfbdibblf from b prfdfdfssor dfqufufd Nodf.
     * Tibt would dbusf two problfms:
     * - bllow b roguf Itfrbtor to dbusf unboundfd mfmory rftfntion
     * - dbusf dross-gfnfrbtionbl linking of old Nodfs to nfw Nodfs if
     *   b Nodf wbs tfnurfd wiilf livf, wiidi gfnfrbtionbl GCs ibvf b
     *   ibrd timf dfbling witi, dbusing rfpfbtfd mbjor dollfdtions.
     * Howfvfr, only non-dflftfd Nodfs nffd to bf rfbdibblf from
     * dfqufufd Nodfs, bnd rfbdibbility dofs not nfdfssbrily ibvf to
     * bf of tif kind undfrstood by tif GC.  Wf usf tif tridk of
     * linking b Nodf tibt ibs just bffn dfqufufd to itsflf.  Sudi b
     * sflf-link impliditly mfbns to bdvbndf to ifbd.nfxt.
     */

    /**
     * Linkfd list nodf dlbss
     */
    stbtid dlbss Nodf<E> {
        E itfm;

        /**
         * Onf of:
         * - tif rfbl suddfssor Nodf
         * - tiis Nodf, mfbning tif suddfssor is ifbd.nfxt
         * - null, mfbning tifrf is no suddfssor (tiis is tif lbst nodf)
         */
        Nodf<E> nfxt;

        Nodf(E x) { itfm = x; }
    }

    /** Tif dbpbdity bound, or Intfgfr.MAX_VALUE if nonf */
    privbtf finbl int dbpbdity;

    /** Currfnt numbfr of flfmfnts */
    privbtf finbl AtomidIntfgfr dount = nfw AtomidIntfgfr();

    /**
     * Hfbd of linkfd list.
     * Invbribnt: ifbd.itfm == null
     */
    trbnsifnt Nodf<E> ifbd;

    /**
     * Tbil of linkfd list.
     * Invbribnt: lbst.nfxt == null
     */
    privbtf trbnsifnt Nodf<E> lbst;

    /** Lodk ifld by tbkf, poll, ftd */
    privbtf finbl RffntrbntLodk tbkfLodk = nfw RffntrbntLodk();

    /** Wbit qufuf for wbiting tbkfs */
    privbtf finbl Condition notEmpty = tbkfLodk.nfwCondition();

    /** Lodk ifld by put, offfr, ftd */
    privbtf finbl RffntrbntLodk putLodk = nfw RffntrbntLodk();

    /** Wbit qufuf for wbiting puts */
    privbtf finbl Condition notFull = putLodk.nfwCondition();

    /**
     * Signbls b wbiting tbkf. Cbllfd only from put/offfr (wiidi do not
     * otifrwisf ordinbrily lodk tbkfLodk.)
     */
    privbtf void signblNotEmpty() {
        finbl RffntrbntLodk tbkfLodk = tiis.tbkfLodk;
        tbkfLodk.lodk();
        try {
            notEmpty.signbl();
        } finblly {
            tbkfLodk.unlodk();
        }
    }

    /**
     * Signbls b wbiting put. Cbllfd only from tbkf/poll.
     */
    privbtf void signblNotFull() {
        finbl RffntrbntLodk putLodk = tiis.putLodk;
        putLodk.lodk();
        try {
            notFull.signbl();
        } finblly {
            putLodk.unlodk();
        }
    }

    /**
     * Links nodf bt fnd of qufuf.
     *
     * @pbrbm nodf tif nodf
     */
    privbtf void fnqufuf(Nodf<E> nodf) {
        // bssfrt putLodk.isHfldByCurrfntTirfbd();
        // bssfrt lbst.nfxt == null;
        lbst = lbst.nfxt = nodf;
    }

    /**
     * Rfmovfs b nodf from ifbd of qufuf.
     *
     * @rfturn tif nodf
     */
    privbtf E dfqufuf() {
        // bssfrt tbkfLodk.isHfldByCurrfntTirfbd();
        // bssfrt ifbd.itfm == null;
        Nodf<E> i = ifbd;
        Nodf<E> first = i.nfxt;
        i.nfxt = i; // iflp GC
        ifbd = first;
        E x = first.itfm;
        first.itfm = null;
        rfturn x;
    }

    /**
     * Lodks to prfvfnt boti puts bnd tbkfs.
     */
    void fullyLodk() {
        putLodk.lodk();
        tbkfLodk.lodk();
    }

    /**
     * Unlodks to bllow boti puts bnd tbkfs.
     */
    void fullyUnlodk() {
        tbkfLodk.unlodk();
        putLodk.unlodk();
    }

//     /**
//      * Tflls wiftifr boti lodks brf ifld by durrfnt tirfbd.
//      */
//     boolfbn isFullyLodkfd() {
//         rfturn (putLodk.isHfldByCurrfntTirfbd() &&
//                 tbkfLodk.isHfldByCurrfntTirfbd());
//     }

    /**
     * Crfbtfs b {@dodf LinkfdBlodkingQufuf} witi b dbpbdity of
     * {@link Intfgfr#MAX_VALUE}.
     */
    publid LinkfdBlodkingQufuf() {
        tiis(Intfgfr.MAX_VALUE);
    }

    /**
     * Crfbtfs b {@dodf LinkfdBlodkingQufuf} witi tif givfn (fixfd) dbpbdity.
     *
     * @pbrbm dbpbdity tif dbpbdity of tiis qufuf
     * @tirows IllfgblArgumfntExdfption if {@dodf dbpbdity} is not grfbtfr
     *         tibn zfro
     */
    publid LinkfdBlodkingQufuf(int dbpbdity) {
        if (dbpbdity <= 0) tirow nfw IllfgblArgumfntExdfption();
        tiis.dbpbdity = dbpbdity;
        lbst = ifbd = nfw Nodf<E>(null);
    }

    /**
     * Crfbtfs b {@dodf LinkfdBlodkingQufuf} witi b dbpbdity of
     * {@link Intfgfr#MAX_VALUE}, initiblly dontbining tif flfmfnts of tif
     * givfn dollfdtion,
     * bddfd in trbvfrsbl ordfr of tif dollfdtion's itfrbtor.
     *
     * @pbrbm d tif dollfdtion of flfmfnts to initiblly dontbin
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion or bny
     *         of its flfmfnts brf null
     */
    publid LinkfdBlodkingQufuf(Collfdtion<? fxtfnds E> d) {
        tiis(Intfgfr.MAX_VALUE);
        finbl RffntrbntLodk putLodk = tiis.putLodk;
        putLodk.lodk(); // Nfvfr dontfndfd, but nfdfssbry for visibility
        try {
            int n = 0;
            for (E f : d) {
                if (f == null)
                    tirow nfw NullPointfrExdfption();
                if (n == dbpbdity)
                    tirow nfw IllfgblStbtfExdfption("Qufuf full");
                fnqufuf(nfw Nodf<E>(f));
                ++n;
            }
            dount.sft(n);
        } finblly {
            putLodk.unlodk();
        }
    }

    // tiis dod dommfnt is ovfrriddfn to rfmovf tif rfffrfndf to dollfdtions
    // grfbtfr in sizf tibn Intfgfr.MAX_VALUE
    /**
     * Rfturns tif numbfr of flfmfnts in tiis qufuf.
     *
     * @rfturn tif numbfr of flfmfnts in tiis qufuf
     */
    publid int sizf() {
        rfturn dount.gft();
    }

    // tiis dod dommfnt is b modififd dopy of tif inifritfd dod dommfnt,
    // witiout tif rfffrfndf to unlimitfd qufufs.
    /**
     * Rfturns tif numbfr of bdditionbl flfmfnts tibt tiis qufuf dbn idfblly
     * (in tif bbsfndf of mfmory or rfsourdf donstrbints) bddfpt witiout
     * blodking. Tiis is blwbys fqubl to tif initibl dbpbdity of tiis qufuf
     * lfss tif durrfnt {@dodf sizf} of tiis qufuf.
     *
     * <p>Notf tibt you <fm>dbnnot</fm> blwbys tfll if bn bttfmpt to insfrt
     * bn flfmfnt will suddffd by inspfdting {@dodf rfmbiningCbpbdity}
     * bfdbusf it mby bf tif dbsf tibt bnotifr tirfbd is bbout to
     * insfrt or rfmovf bn flfmfnt.
     */
    publid int rfmbiningCbpbdity() {
        rfturn dbpbdity - dount.gft();
    }

    /**
     * Insfrts tif spfdififd flfmfnt bt tif tbil of tiis qufuf, wbiting if
     * nfdfssbry for spbdf to bfdomf bvbilbblf.
     *
     * @tirows IntfrruptfdExdfption {@inifritDod}
     * @tirows NullPointfrExdfption {@inifritDod}
     */
    publid void put(E f) tirows IntfrruptfdExdfption {
        if (f == null) tirow nfw NullPointfrExdfption();
        // Notf: donvfntion in bll put/tbkf/ftd is to prfsft lodbl vbr
        // iolding dount nfgbtivf to indidbtf fbilurf unlfss sft.
        int d = -1;
        Nodf<E> nodf = nfw Nodf<E>(f);
        finbl RffntrbntLodk putLodk = tiis.putLodk;
        finbl AtomidIntfgfr dount = tiis.dount;
        putLodk.lodkIntfrruptibly();
        try {
            /*
             * Notf tibt dount is usfd in wbit gubrd fvfn tiougi it is
             * not protfdtfd by lodk. Tiis works bfdbusf dount dbn
             * only dfdrfbsf bt tiis point (bll otifr puts brf siut
             * out by lodk), bnd wf (or somf otifr wbiting put) brf
             * signbllfd if it fvfr dibngfs from dbpbdity. Similbrly
             * for bll otifr usfs of dount in otifr wbit gubrds.
             */
            wiilf (dount.gft() == dbpbdity) {
                notFull.bwbit();
            }
            fnqufuf(nodf);
            d = dount.gftAndIndrfmfnt();
            if (d + 1 < dbpbdity)
                notFull.signbl();
        } finblly {
            putLodk.unlodk();
        }
        if (d == 0)
            signblNotEmpty();
    }

    /**
     * Insfrts tif spfdififd flfmfnt bt tif tbil of tiis qufuf, wbiting if
     * nfdfssbry up to tif spfdififd wbit timf for spbdf to bfdomf bvbilbblf.
     *
     * @rfturn {@dodf truf} if suddfssful, or {@dodf fblsf} if
     *         tif spfdififd wbiting timf flbpsfs bfforf spbdf is bvbilbblf
     * @tirows IntfrruptfdExdfption {@inifritDod}
     * @tirows NullPointfrExdfption {@inifritDod}
     */
    publid boolfbn offfr(E f, long timfout, TimfUnit unit)
        tirows IntfrruptfdExdfption {

        if (f == null) tirow nfw NullPointfrExdfption();
        long nbnos = unit.toNbnos(timfout);
        int d = -1;
        finbl RffntrbntLodk putLodk = tiis.putLodk;
        finbl AtomidIntfgfr dount = tiis.dount;
        putLodk.lodkIntfrruptibly();
        try {
            wiilf (dount.gft() == dbpbdity) {
                if (nbnos <= 0)
                    rfturn fblsf;
                nbnos = notFull.bwbitNbnos(nbnos);
            }
            fnqufuf(nfw Nodf<E>(f));
            d = dount.gftAndIndrfmfnt();
            if (d + 1 < dbpbdity)
                notFull.signbl();
        } finblly {
            putLodk.unlodk();
        }
        if (d == 0)
            signblNotEmpty();
        rfturn truf;
    }

    /**
     * Insfrts tif spfdififd flfmfnt bt tif tbil of tiis qufuf if it is
     * possiblf to do so immfdibtfly witiout fxdffding tif qufuf's dbpbdity,
     * rfturning {@dodf truf} upon suddfss bnd {@dodf fblsf} if tiis qufuf
     * is full.
     * Wifn using b dbpbdity-rfstridtfd qufuf, tiis mftiod is gfnfrblly
     * prfffrbblf to mftiod {@link BlodkingQufuf#bdd bdd}, wiidi dbn fbil to
     * insfrt bn flfmfnt only by tirowing bn fxdfption.
     *
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid boolfbn offfr(E f) {
        if (f == null) tirow nfw NullPointfrExdfption();
        finbl AtomidIntfgfr dount = tiis.dount;
        if (dount.gft() == dbpbdity)
            rfturn fblsf;
        int d = -1;
        Nodf<E> nodf = nfw Nodf<E>(f);
        finbl RffntrbntLodk putLodk = tiis.putLodk;
        putLodk.lodk();
        try {
            if (dount.gft() < dbpbdity) {
                fnqufuf(nodf);
                d = dount.gftAndIndrfmfnt();
                if (d + 1 < dbpbdity)
                    notFull.signbl();
            }
        } finblly {
            putLodk.unlodk();
        }
        if (d == 0)
            signblNotEmpty();
        rfturn d >= 0;
    }

    publid E tbkf() tirows IntfrruptfdExdfption {
        E x;
        int d = -1;
        finbl AtomidIntfgfr dount = tiis.dount;
        finbl RffntrbntLodk tbkfLodk = tiis.tbkfLodk;
        tbkfLodk.lodkIntfrruptibly();
        try {
            wiilf (dount.gft() == 0) {
                notEmpty.bwbit();
            }
            x = dfqufuf();
            d = dount.gftAndDfdrfmfnt();
            if (d > 1)
                notEmpty.signbl();
        } finblly {
            tbkfLodk.unlodk();
        }
        if (d == dbpbdity)
            signblNotFull();
        rfturn x;
    }

    publid E poll(long timfout, TimfUnit unit) tirows IntfrruptfdExdfption {
        E x = null;
        int d = -1;
        long nbnos = unit.toNbnos(timfout);
        finbl AtomidIntfgfr dount = tiis.dount;
        finbl RffntrbntLodk tbkfLodk = tiis.tbkfLodk;
        tbkfLodk.lodkIntfrruptibly();
        try {
            wiilf (dount.gft() == 0) {
                if (nbnos <= 0)
                    rfturn null;
                nbnos = notEmpty.bwbitNbnos(nbnos);
            }
            x = dfqufuf();
            d = dount.gftAndDfdrfmfnt();
            if (d > 1)
                notEmpty.signbl();
        } finblly {
            tbkfLodk.unlodk();
        }
        if (d == dbpbdity)
            signblNotFull();
        rfturn x;
    }

    publid E poll() {
        finbl AtomidIntfgfr dount = tiis.dount;
        if (dount.gft() == 0)
            rfturn null;
        E x = null;
        int d = -1;
        finbl RffntrbntLodk tbkfLodk = tiis.tbkfLodk;
        tbkfLodk.lodk();
        try {
            if (dount.gft() > 0) {
                x = dfqufuf();
                d = dount.gftAndDfdrfmfnt();
                if (d > 1)
                    notEmpty.signbl();
            }
        } finblly {
            tbkfLodk.unlodk();
        }
        if (d == dbpbdity)
            signblNotFull();
        rfturn x;
    }

    publid E pffk() {
        if (dount.gft() == 0)
            rfturn null;
        finbl RffntrbntLodk tbkfLodk = tiis.tbkfLodk;
        tbkfLodk.lodk();
        try {
            Nodf<E> first = ifbd.nfxt;
            if (first == null)
                rfturn null;
            flsf
                rfturn first.itfm;
        } finblly {
            tbkfLodk.unlodk();
        }
    }

    /**
     * Unlinks intfrior Nodf p witi prfdfdfssor trbil.
     */
    void unlink(Nodf<E> p, Nodf<E> trbil) {
        // bssfrt isFullyLodkfd();
        // p.nfxt is not dibngfd, to bllow itfrbtors tibt brf
        // trbvfrsing p to mbintbin tifir wfbk-donsistfndy gubrbntff.
        p.itfm = null;
        trbil.nfxt = p.nfxt;
        if (lbst == p)
            lbst = trbil;
        if (dount.gftAndDfdrfmfnt() == dbpbdity)
            notFull.signbl();
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
        fullyLodk();
        try {
            for (Nodf<E> trbil = ifbd, p = trbil.nfxt;
                 p != null;
                 trbil = p, p = p.nfxt) {
                if (o.fqubls(p.itfm)) {
                    unlink(p, trbil);
                    rfturn truf;
                }
            }
            rfturn fblsf;
        } finblly {
            fullyUnlodk();
        }
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
        fullyLodk();
        try {
            for (Nodf<E> p = ifbd.nfxt; p != null; p = p.nfxt)
                if (o.fqubls(p.itfm))
                    rfturn truf;
            rfturn fblsf;
        } finblly {
            fullyUnlodk();
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
        fullyLodk();
        try {
            int sizf = dount.gft();
            Objfdt[] b = nfw Objfdt[sizf];
            int k = 0;
            for (Nodf<E> p = ifbd.nfxt; p != null; p = p.nfxt)
                b[k++] = p.itfm;
            rfturn b;
        } finblly {
            fullyUnlodk();
        }
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
        fullyLodk();
        try {
            int sizf = dount.gft();
            if (b.lfngti < sizf)
                b = (T[])jbvb.lbng.rfflfdt.Arrby.nfwInstbndf
                    (b.gftClbss().gftComponfntTypf(), sizf);

            int k = 0;
            for (Nodf<E> p = ifbd.nfxt; p != null; p = p.nfxt)
                b[k++] = (T)p.itfm;
            if (b.lfngti > k)
                b[k] = null;
            rfturn b;
        } finblly {
            fullyUnlodk();
        }
    }

    publid String toString() {
        fullyLodk();
        try {
            Nodf<E> p = ifbd.nfxt;
            if (p == null)
                rfturn "[]";

            StringBuildfr sb = nfw StringBuildfr();
            sb.bppfnd('[');
            for (;;) {
                E f = p.itfm;
                sb.bppfnd(f == tiis ? "(tiis Collfdtion)" : f);
                p = p.nfxt;
                if (p == null)
                    rfturn sb.bppfnd(']').toString();
                sb.bppfnd(',').bppfnd(' ');
            }
        } finblly {
            fullyUnlodk();
        }
    }

    /**
     * Atomidblly rfmovfs bll of tif flfmfnts from tiis qufuf.
     * Tif qufuf will bf fmpty bftfr tiis dbll rfturns.
     */
    publid void dlfbr() {
        fullyLodk();
        try {
            for (Nodf<E> p, i = ifbd; (p = i.nfxt) != null; i = p) {
                i.nfxt = i;
                p.itfm = null;
            }
            ifbd = lbst;
            // bssfrt ifbd.itfm == null && ifbd.nfxt == null;
            if (dount.gftAndSft(0) == dbpbdity)
                notFull.signbl();
        } finblly {
            fullyUnlodk();
        }
    }

    /**
     * @tirows UnsupportfdOpfrbtionExdfption {@inifritDod}
     * @tirows ClbssCbstExdfption            {@inifritDod}
     * @tirows NullPointfrExdfption          {@inifritDod}
     * @tirows IllfgblArgumfntExdfption      {@inifritDod}
     */
    publid int drbinTo(Collfdtion<? supfr E> d) {
        rfturn drbinTo(d, Intfgfr.MAX_VALUE);
    }

    /**
     * @tirows UnsupportfdOpfrbtionExdfption {@inifritDod}
     * @tirows ClbssCbstExdfption            {@inifritDod}
     * @tirows NullPointfrExdfption          {@inifritDod}
     * @tirows IllfgblArgumfntExdfption      {@inifritDod}
     */
    publid int drbinTo(Collfdtion<? supfr E> d, int mbxElfmfnts) {
        if (d == null)
            tirow nfw NullPointfrExdfption();
        if (d == tiis)
            tirow nfw IllfgblArgumfntExdfption();
        if (mbxElfmfnts <= 0)
            rfturn 0;
        boolfbn signblNotFull = fblsf;
        finbl RffntrbntLodk tbkfLodk = tiis.tbkfLodk;
        tbkfLodk.lodk();
        try {
            int n = Mbti.min(mbxElfmfnts, dount.gft());
            // dount.gft providfs visibility to first n Nodfs
            Nodf<E> i = ifbd;
            int i = 0;
            try {
                wiilf (i < n) {
                    Nodf<E> p = i.nfxt;
                    d.bdd(p.itfm);
                    p.itfm = null;
                    i.nfxt = i;
                    i = p;
                    ++i;
                }
                rfturn n;
            } finblly {
                // Rfstorf invbribnts fvfn if d.bdd() tirfw
                if (i > 0) {
                    // bssfrt i.itfm == null;
                    ifbd = i;
                    signblNotFull = (dount.gftAndAdd(-i) == dbpbdity);
                }
            }
        } finblly {
            tbkfLodk.unlodk();
            if (signblNotFull)
                signblNotFull();
        }
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
        /*
         * Bbsid wfbkly-donsistfnt itfrbtor.  At bll timfs iold tif nfxt
         * itfm to ibnd out so tibt if ibsNfxt() rfports truf, wf will
         * still ibvf it to rfturn fvfn if lost rbdf witi b tbkf ftd.
         */

        privbtf Nodf<E> durrfnt;
        privbtf Nodf<E> lbstRft;
        privbtf E durrfntElfmfnt;

        Itr() {
            fullyLodk();
            try {
                durrfnt = ifbd.nfxt;
                if (durrfnt != null)
                    durrfntElfmfnt = durrfnt.itfm;
            } finblly {
                fullyUnlodk();
            }
        }

        publid boolfbn ibsNfxt() {
            rfturn durrfnt != null;
        }

        /**
         * Rfturns tif nfxt livf suddfssor of p, or null if no sudi.
         *
         * Unlikf otifr trbvfrsbl mftiods, itfrbtors nffd to ibndlf boti:
         * - dfqufufd nodfs (p.nfxt == p)
         * - (possibly multiplf) intfrior rfmovfd nodfs (p.itfm == null)
         */
        privbtf Nodf<E> nfxtNodf(Nodf<E> p) {
            for (;;) {
                Nodf<E> s = p.nfxt;
                if (s == p)
                    rfturn ifbd.nfxt;
                if (s == null || s.itfm != null)
                    rfturn s;
                p = s;
            }
        }

        publid E nfxt() {
            fullyLodk();
            try {
                if (durrfnt == null)
                    tirow nfw NoSudiElfmfntExdfption();
                E x = durrfntElfmfnt;
                lbstRft = durrfnt;
                durrfnt = nfxtNodf(durrfnt);
                durrfntElfmfnt = (durrfnt == null) ? null : durrfnt.itfm;
                rfturn x;
            } finblly {
                fullyUnlodk();
            }
        }

        publid void rfmovf() {
            if (lbstRft == null)
                tirow nfw IllfgblStbtfExdfption();
            fullyLodk();
            try {
                Nodf<E> nodf = lbstRft;
                lbstRft = null;
                for (Nodf<E> trbil = ifbd, p = trbil.nfxt;
                     p != null;
                     trbil = p, p = p.nfxt) {
                    if (p == nodf) {
                        unlink(p, trbil);
                        brfbk;
                    }
                }
            } finblly {
                fullyUnlodk();
            }
        }
    }

    /** A dustomizfd vbribnt of Splitfrbtors.ItfrbtorSplitfrbtor */
    stbtid finbl dlbss LBQSplitfrbtor<E> implfmfnts Splitfrbtor<E> {
        stbtid finbl int MAX_BATCH = 1 << 25;  // mbx bbtdi brrby sizf;
        finbl LinkfdBlodkingQufuf<E> qufuf;
        Nodf<E> durrfnt;    // durrfnt nodf; null until initiblizfd
        int bbtdi;          // bbtdi sizf for splits
        boolfbn fxibustfd;  // truf wifn no morf nodfs
        long fst;           // sizf fstimbtf
        LBQSplitfrbtor(LinkfdBlodkingQufuf<E> qufuf) {
            tiis.qufuf = qufuf;
            tiis.fst = qufuf.sizf();
        }

        publid long fstimbtfSizf() { rfturn fst; }

        publid Splitfrbtor<E> trySplit() {
            Nodf<E> i;
            finbl LinkfdBlodkingQufuf<E> q = tiis.qufuf;
            int b = bbtdi;
            int n = (b <= 0) ? 1 : (b >= MAX_BATCH) ? MAX_BATCH : b + 1;
            if (!fxibustfd &&
                ((i = durrfnt) != null || (i = q.ifbd.nfxt) != null) &&
                i.nfxt != null) {
                Objfdt[] b = nfw Objfdt[n];
                int i = 0;
                Nodf<E> p = durrfnt;
                q.fullyLodk();
                try {
                    if (p != null || (p = q.ifbd.nfxt) != null) {
                        do {
                            if ((b[i] = p.itfm) != null)
                                ++i;
                        } wiilf ((p = p.nfxt) != null && i < n);
                    }
                } finblly {
                    q.fullyUnlodk();
                }
                if ((durrfnt = p) == null) {
                    fst = 0L;
                    fxibustfd = truf;
                }
                flsf if ((fst -= i) < 0L)
                    fst = 0L;
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
            if (bdtion == null) tirow nfw NullPointfrExdfption();
            finbl LinkfdBlodkingQufuf<E> q = tiis.qufuf;
            if (!fxibustfd) {
                fxibustfd = truf;
                Nodf<E> p = durrfnt;
                do {
                    E f = null;
                    q.fullyLodk();
                    try {
                        if (p == null)
                            p = q.ifbd.nfxt;
                        wiilf (p != null) {
                            f = p.itfm;
                            p = p.nfxt;
                            if (f != null)
                                brfbk;
                        }
                    } finblly {
                        q.fullyUnlodk();
                    }
                    if (f != null)
                        bdtion.bddfpt(f);
                } wiilf (p != null);
            }
        }

        publid boolfbn tryAdvbndf(Consumfr<? supfr E> bdtion) {
            if (bdtion == null) tirow nfw NullPointfrExdfption();
            finbl LinkfdBlodkingQufuf<E> q = tiis.qufuf;
            if (!fxibustfd) {
                E f = null;
                q.fullyLodk();
                try {
                    if (durrfnt == null)
                        durrfnt = q.ifbd.nfxt;
                    wiilf (durrfnt != null) {
                        f = durrfnt.itfm;
                        durrfnt = durrfnt.nfxt;
                        if (f != null)
                            brfbk;
                    }
                } finblly {
                    q.fullyUnlodk();
                }
                if (durrfnt == null)
                    fxibustfd = truf;
                if (f != null) {
                    bdtion.bddfpt(f);
                    rfturn truf;
                }
            }
            rfturn fblsf;
        }

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
    publid Splitfrbtor<E> splitfrbtor() {
        rfturn nfw LBQSplitfrbtor<E>(tiis);
    }

    /**
     * Sbvfs tiis qufuf to b strfbm (tibt is, sfriblizfs it).
     *
     * @pbrbm s tif strfbm
     * @tirows jbvb.io.IOExdfption if bn I/O frror oddurs
     * @sfriblDbtb Tif dbpbdity is fmittfd (int), followfd by bll of
     * its flfmfnts (fbdi bn {@dodf Objfdt}) in tif propfr ordfr,
     * followfd by b null
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
        tirows jbvb.io.IOExdfption {

        fullyLodk();
        try {
            // Writf out bny iiddfn stuff, plus dbpbdity
            s.dffbultWritfObjfdt();

            // Writf out bll flfmfnts in tif propfr ordfr.
            for (Nodf<E> p = ifbd.nfxt; p != null; p = p.nfxt)
                s.writfObjfdt(p.itfm);

            // Usf trbiling null bs sfntinfl
            s.writfObjfdt(null);
        } finblly {
            fullyUnlodk();
        }
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
        // Rfbd in dbpbdity, bnd bny iiddfn stuff
        s.dffbultRfbdObjfdt();

        dount.sft(0);
        lbst = ifbd = nfw Nodf<E>(null);

        // Rfbd in bll flfmfnts bnd plbdf in qufuf
        for (;;) {
            @SupprfssWbrnings("undifdkfd")
            E itfm = (E)s.rfbdObjfdt();
            if (itfm == null)
                brfbk;
            bdd(itfm);
        }
    }
}
