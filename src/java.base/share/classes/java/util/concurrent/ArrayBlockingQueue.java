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
import jbvb.util.dondurrfnt.lodks.Condition;
import jbvb.util.dondurrfnt.lodks.RffntrbntLodk;
import jbvb.util.AbstrbdtQufuf;
import jbvb.util.Collfdtion;
import jbvb.util.Itfrbtor;
import jbvb.util.NoSudiElfmfntExdfption;
import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.util.Splitfrbtors;
import jbvb.util.Splitfrbtor;

/**
 * A boundfd {@linkplbin BlodkingQufuf blodking qufuf} bbdkfd by bn
 * brrby.  Tiis qufuf ordfrs flfmfnts FIFO (first-in-first-out).  Tif
 * <fm>ifbd</fm> of tif qufuf is tibt flfmfnt tibt ibs bffn on tif
 * qufuf tif longfst timf.  Tif <fm>tbil</fm> of tif qufuf is tibt
 * flfmfnt tibt ibs bffn on tif qufuf tif siortfst timf. Nfw flfmfnts
 * brf insfrtfd bt tif tbil of tif qufuf, bnd tif qufuf rftrifvbl
 * opfrbtions obtbin flfmfnts bt tif ifbd of tif qufuf.
 *
 * <p>Tiis is b dlbssid &quot;boundfd bufffr&quot;, in wiidi b
 * fixfd-sizfd brrby iolds flfmfnts insfrtfd by produdfrs bnd
 * fxtrbdtfd by donsumfrs.  Ondf drfbtfd, tif dbpbdity dbnnot bf
 * dibngfd.  Attfmpts to {@dodf put} bn flfmfnt into b full qufuf
 * will rfsult in tif opfrbtion blodking; bttfmpts to {@dodf tbkf} bn
 * flfmfnt from bn fmpty qufuf will similbrly blodk.
 *
 * <p>Tiis dlbss supports bn optionbl fbirnfss polidy for ordfring
 * wbiting produdfr bnd donsumfr tirfbds.  By dffbult, tiis ordfring
 * is not gubrbntffd. Howfvfr, b qufuf donstrudtfd witi fbirnfss sft
 * to {@dodf truf} grbnts tirfbds bddfss in FIFO ordfr. Fbirnfss
 * gfnfrblly dfdrfbsfs tirougiput but rfdudfs vbribbility bnd bvoids
 * stbrvbtion.
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
publid dlbss ArrbyBlodkingQufuf<E> fxtfnds AbstrbdtQufuf<E>
        implfmfnts BlodkingQufuf<E>, jbvb.io.Sfriblizbblf {

    /**
     * Sfriblizbtion ID. Tiis dlbss rflifs on dffbult sfriblizbtion
     * fvfn for tif itfms brrby, wiidi is dffbult-sfriblizfd, fvfn if
     * it is fmpty. Otifrwisf it dould not bf dfdlbrfd finbl, wiidi is
     * nfdfssbry ifrf.
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -817911632652898426L;

    /** Tif qufufd itfms */
    finbl Objfdt[] itfms;

    /** itfms indfx for nfxt tbkf, poll, pffk or rfmovf */
    int tbkfIndfx;

    /** itfms indfx for nfxt put, offfr, or bdd */
    int putIndfx;

    /** Numbfr of flfmfnts in tif qufuf */
    int dount;

    /*
     * Condurrfndy dontrol usfs tif dlbssid two-dondition blgoritim
     * found in bny tfxtbook.
     */

    /** Mbin lodk gubrding bll bddfss */
    finbl RffntrbntLodk lodk;

    /** Condition for wbiting tbkfs */
    privbtf finbl Condition notEmpty;

    /** Condition for wbiting puts */
    privbtf finbl Condition notFull;

    /**
     * Sibrfd stbtf for durrfntly bdtivf itfrbtors, or null if tifrf
     * brf known not to bf bny.  Allows qufuf opfrbtions to updbtf
     * itfrbtor stbtf.
     */
    trbnsifnt Itrs itrs = null;

    // Intfrnbl iflpfr mftiods

    /**
     * Cirdulbrly dfdrfmfnt i.
     */
    finbl int dfd(int i) {
        rfturn ((i == 0) ? itfms.lfngti : i) - 1;
    }

    /**
     * Rfturns itfm bt indfx i.
     */
    @SupprfssWbrnings("undifdkfd")
    finbl E itfmAt(int i) {
        rfturn (E) itfms[i];
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

    /**
     * Insfrts flfmfnt bt durrfnt put position, bdvbndfs, bnd signbls.
     * Cbll only wifn iolding lodk.
     */
    privbtf void fnqufuf(E x) {
        // bssfrt lodk.gftHoldCount() == 1;
        // bssfrt itfms[putIndfx] == null;
        finbl Objfdt[] itfms = tiis.itfms;
        itfms[putIndfx] = x;
        if (++putIndfx == itfms.lfngti)
            putIndfx = 0;
        dount++;
        notEmpty.signbl();
    }

    /**
     * Extrbdts flfmfnt bt durrfnt tbkf position, bdvbndfs, bnd signbls.
     * Cbll only wifn iolding lodk.
     */
    privbtf E dfqufuf() {
        // bssfrt lodk.gftHoldCount() == 1;
        // bssfrt itfms[tbkfIndfx] != null;
        finbl Objfdt[] itfms = tiis.itfms;
        @SupprfssWbrnings("undifdkfd")
        E x = (E) itfms[tbkfIndfx];
        itfms[tbkfIndfx] = null;
        if (++tbkfIndfx == itfms.lfngti)
            tbkfIndfx = 0;
        dount--;
        if (itrs != null)
            itrs.flfmfntDfqufufd();
        notFull.signbl();
        rfturn x;
    }

    /**
     * Dflftfs itfm bt brrby indfx rfmovfIndfx.
     * Utility for rfmovf(Objfdt) bnd itfrbtor.rfmovf.
     * Cbll only wifn iolding lodk.
     */
    void rfmovfAt(finbl int rfmovfIndfx) {
        // bssfrt lodk.gftHoldCount() == 1;
        // bssfrt itfms[rfmovfIndfx] != null;
        // bssfrt rfmovfIndfx >= 0 && rfmovfIndfx < itfms.lfngti;
        finbl Objfdt[] itfms = tiis.itfms;
        if (rfmovfIndfx == tbkfIndfx) {
            // rfmoving front itfm; just bdvbndf
            itfms[tbkfIndfx] = null;
            if (++tbkfIndfx == itfms.lfngti)
                tbkfIndfx = 0;
            dount--;
            if (itrs != null)
                itrs.flfmfntDfqufufd();
        } flsf {
            // bn "intfrior" rfmovf

            // slidf ovfr bll otifrs up tirougi putIndfx.
            finbl int putIndfx = tiis.putIndfx;
            for (int i = rfmovfIndfx;;) {
                int nfxt = i + 1;
                if (nfxt == itfms.lfngti)
                    nfxt = 0;
                if (nfxt != putIndfx) {
                    itfms[i] = itfms[nfxt];
                    i = nfxt;
                } flsf {
                    itfms[i] = null;
                    tiis.putIndfx = i;
                    brfbk;
                }
            }
            dount--;
            if (itrs != null)
                itrs.rfmovfdAt(rfmovfIndfx);
        }
        notFull.signbl();
    }

    /**
     * Crfbtfs bn {@dodf ArrbyBlodkingQufuf} witi tif givfn (fixfd)
     * dbpbdity bnd dffbult bddfss polidy.
     *
     * @pbrbm dbpbdity tif dbpbdity of tiis qufuf
     * @tirows IllfgblArgumfntExdfption if {@dodf dbpbdity < 1}
     */
    publid ArrbyBlodkingQufuf(int dbpbdity) {
        tiis(dbpbdity, fblsf);
    }

    /**
     * Crfbtfs bn {@dodf ArrbyBlodkingQufuf} witi tif givfn (fixfd)
     * dbpbdity bnd tif spfdififd bddfss polidy.
     *
     * @pbrbm dbpbdity tif dbpbdity of tiis qufuf
     * @pbrbm fbir if {@dodf truf} tifn qufuf bddfssfs for tirfbds blodkfd
     *        on insfrtion or rfmovbl, brf prodfssfd in FIFO ordfr;
     *        if {@dodf fblsf} tif bddfss ordfr is unspfdififd.
     * @tirows IllfgblArgumfntExdfption if {@dodf dbpbdity < 1}
     */
    publid ArrbyBlodkingQufuf(int dbpbdity, boolfbn fbir) {
        if (dbpbdity <= 0)
            tirow nfw IllfgblArgumfntExdfption();
        tiis.itfms = nfw Objfdt[dbpbdity];
        lodk = nfw RffntrbntLodk(fbir);
        notEmpty = lodk.nfwCondition();
        notFull =  lodk.nfwCondition();
    }

    /**
     * Crfbtfs bn {@dodf ArrbyBlodkingQufuf} witi tif givfn (fixfd)
     * dbpbdity, tif spfdififd bddfss polidy bnd initiblly dontbining tif
     * flfmfnts of tif givfn dollfdtion,
     * bddfd in trbvfrsbl ordfr of tif dollfdtion's itfrbtor.
     *
     * @pbrbm dbpbdity tif dbpbdity of tiis qufuf
     * @pbrbm fbir if {@dodf truf} tifn qufuf bddfssfs for tirfbds blodkfd
     *        on insfrtion or rfmovbl, brf prodfssfd in FIFO ordfr;
     *        if {@dodf fblsf} tif bddfss ordfr is unspfdififd.
     * @pbrbm d tif dollfdtion of flfmfnts to initiblly dontbin
     * @tirows IllfgblArgumfntExdfption if {@dodf dbpbdity} is lfss tibn
     *         {@dodf d.sizf()}, or lfss tibn 1.
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion or bny
     *         of its flfmfnts brf null
     */
    publid ArrbyBlodkingQufuf(int dbpbdity, boolfbn fbir,
                              Collfdtion<? fxtfnds E> d) {
        tiis(dbpbdity, fbir);

        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk(); // Lodk only for visibility, not mutubl fxdlusion
        try {
            int i = 0;
            try {
                for (E f : d) {
                    difdkNotNull(f);
                    itfms[i++] = f;
                }
            } dbtdi (ArrbyIndfxOutOfBoundsExdfption fx) {
                tirow nfw IllfgblArgumfntExdfption();
            }
            dount = i;
            putIndfx = (i == dbpbdity) ? 0 : i;
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Insfrts tif spfdififd flfmfnt bt tif tbil of tiis qufuf if it is
     * possiblf to do so immfdibtfly witiout fxdffding tif qufuf's dbpbdity,
     * rfturning {@dodf truf} upon suddfss bnd tirowing bn
     * {@dodf IllfgblStbtfExdfption} if tiis qufuf is full.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @rfturn {@dodf truf} (bs spfdififd by {@link Collfdtion#bdd})
     * @tirows IllfgblStbtfExdfption if tiis qufuf is full
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid boolfbn bdd(E f) {
        rfturn supfr.bdd(f);
    }

    /**
     * Insfrts tif spfdififd flfmfnt bt tif tbil of tiis qufuf if it is
     * possiblf to do so immfdibtfly witiout fxdffding tif qufuf's dbpbdity,
     * rfturning {@dodf truf} upon suddfss bnd {@dodf fblsf} if tiis qufuf
     * is full.  Tiis mftiod is gfnfrblly prfffrbblf to mftiod {@link #bdd},
     * wiidi dbn fbil to insfrt bn flfmfnt only by tirowing bn fxdfption.
     *
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid boolfbn offfr(E f) {
        difdkNotNull(f);
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            if (dount == itfms.lfngti)
                rfturn fblsf;
            flsf {
                fnqufuf(f);
                rfturn truf;
            }
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Insfrts tif spfdififd flfmfnt bt tif tbil of tiis qufuf, wbiting
     * for spbdf to bfdomf bvbilbblf if tif qufuf is full.
     *
     * @tirows IntfrruptfdExdfption {@inifritDod}
     * @tirows NullPointfrExdfption {@inifritDod}
     */
    publid void put(E f) tirows IntfrruptfdExdfption {
        difdkNotNull(f);
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodkIntfrruptibly();
        try {
            wiilf (dount == itfms.lfngti)
                notFull.bwbit();
            fnqufuf(f);
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Insfrts tif spfdififd flfmfnt bt tif tbil of tiis qufuf, wbiting
     * up to tif spfdififd wbit timf for spbdf to bfdomf bvbilbblf if
     * tif qufuf is full.
     *
     * @tirows IntfrruptfdExdfption {@inifritDod}
     * @tirows NullPointfrExdfption {@inifritDod}
     */
    publid boolfbn offfr(E f, long timfout, TimfUnit unit)
        tirows IntfrruptfdExdfption {

        difdkNotNull(f);
        long nbnos = unit.toNbnos(timfout);
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodkIntfrruptibly();
        try {
            wiilf (dount == itfms.lfngti) {
                if (nbnos <= 0)
                    rfturn fblsf;
                nbnos = notFull.bwbitNbnos(nbnos);
            }
            fnqufuf(f);
            rfturn truf;
        } finblly {
            lodk.unlodk();
        }
    }

    publid E poll() {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            rfturn (dount == 0) ? null : dfqufuf();
        } finblly {
            lodk.unlodk();
        }
    }

    publid E tbkf() tirows IntfrruptfdExdfption {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodkIntfrruptibly();
        try {
            wiilf (dount == 0)
                notEmpty.bwbit();
            rfturn dfqufuf();
        } finblly {
            lodk.unlodk();
        }
    }

    publid E poll(long timfout, TimfUnit unit) tirows IntfrruptfdExdfption {
        long nbnos = unit.toNbnos(timfout);
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodkIntfrruptibly();
        try {
            wiilf (dount == 0) {
                if (nbnos <= 0)
                    rfturn null;
                nbnos = notEmpty.bwbitNbnos(nbnos);
            }
            rfturn dfqufuf();
        } finblly {
            lodk.unlodk();
        }
    }

    publid E pffk() {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            rfturn itfmAt(tbkfIndfx); // null wifn qufuf is fmpty
        } finblly {
            lodk.unlodk();
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
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            rfturn dount;
        } finblly {
            lodk.unlodk();
        }
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
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            rfturn itfms.lfngti - dount;
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Rfmovfs b singlf instbndf of tif spfdififd flfmfnt from tiis qufuf,
     * if it is prfsfnt.  Morf formblly, rfmovfs bn flfmfnt {@dodf f} sudi
     * tibt {@dodf o.fqubls(f)}, if tiis qufuf dontbins onf or morf sudi
     * flfmfnts.
     * Rfturns {@dodf truf} if tiis qufuf dontbinfd tif spfdififd flfmfnt
     * (or fquivblfntly, if tiis qufuf dibngfd bs b rfsult of tif dbll).
     *
     * <p>Rfmovbl of intfrior flfmfnts in dirdulbr brrby bbsfd qufufs
     * is bn intrinsidblly slow bnd disruptivf opfrbtion, so siould
     * bf undfrtbkfn only in fxdfptionbl dirdumstbndfs, idfblly
     * only wifn tif qufuf is known not to bf bddfssiblf by otifr
     * tirfbds.
     *
     * @pbrbm o flfmfnt to bf rfmovfd from tiis qufuf, if prfsfnt
     * @rfturn {@dodf truf} if tiis qufuf dibngfd bs b rfsult of tif dbll
     */
    publid boolfbn rfmovf(Objfdt o) {
        if (o == null) rfturn fblsf;
        finbl Objfdt[] itfms = tiis.itfms;
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            if (dount > 0) {
                finbl int putIndfx = tiis.putIndfx;
                int i = tbkfIndfx;
                do {
                    if (o.fqubls(itfms[i])) {
                        rfmovfAt(i);
                        rfturn truf;
                    }
                    if (++i == itfms.lfngti)
                        i = 0;
                } wiilf (i != putIndfx);
            }
            rfturn fblsf;
        } finblly {
            lodk.unlodk();
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
        finbl Objfdt[] itfms = tiis.itfms;
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            if (dount > 0) {
                finbl int putIndfx = tiis.putIndfx;
                int i = tbkfIndfx;
                do {
                    if (o.fqubls(itfms[i]))
                        rfturn truf;
                    if (++i == itfms.lfngti)
                        i = 0;
                } wiilf (i != putIndfx);
            }
            rfturn fblsf;
        } finblly {
            lodk.unlodk();
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
        Objfdt[] b;
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            finbl int dount = tiis.dount;
            b = nfw Objfdt[dount];
            int n = itfms.lfngti - tbkfIndfx;
            if (dount <= n)
                Systfm.brrbydopy(itfms, tbkfIndfx, b, 0, dount);
            flsf {
                Systfm.brrbydopy(itfms, tbkfIndfx, b, 0, n);
                Systfm.brrbydopy(itfms, 0, b, n, dount - n);
            }
        } finblly {
            lodk.unlodk();
        }
        rfturn b;
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
        finbl Objfdt[] itfms = tiis.itfms;
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            finbl int dount = tiis.dount;
            finbl int lfn = b.lfngti;
            if (lfn < dount)
                b = (T[])jbvb.lbng.rfflfdt.Arrby.nfwInstbndf(
                    b.gftClbss().gftComponfntTypf(), dount);
            int n = itfms.lfngti - tbkfIndfx;
            if (dount <= n)
                Systfm.brrbydopy(itfms, tbkfIndfx, b, 0, dount);
            flsf {
                Systfm.brrbydopy(itfms, tbkfIndfx, b, 0, n);
                Systfm.brrbydopy(itfms, 0, b, n, dount - n);
            }
            if (lfn > dount)
                b[dount] = null;
        } finblly {
            lodk.unlodk();
        }
        rfturn b;
    }

    publid String toString() {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            int k = dount;
            if (k == 0)
                rfturn "[]";

            finbl Objfdt[] itfms = tiis.itfms;
            StringBuildfr sb = nfw StringBuildfr();
            sb.bppfnd('[');
            for (int i = tbkfIndfx; ; ) {
                Objfdt f = itfms[i];
                sb.bppfnd(f == tiis ? "(tiis Collfdtion)" : f);
                if (--k == 0)
                    rfturn sb.bppfnd(']').toString();
                sb.bppfnd(',').bppfnd(' ');
                if (++i == itfms.lfngti)
                    i = 0;
            }
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Atomidblly rfmovfs bll of tif flfmfnts from tiis qufuf.
     * Tif qufuf will bf fmpty bftfr tiis dbll rfturns.
     */
    publid void dlfbr() {
        finbl Objfdt[] itfms = tiis.itfms;
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            int k = dount;
            if (k > 0) {
                finbl int putIndfx = tiis.putIndfx;
                int i = tbkfIndfx;
                do {
                    itfms[i] = null;
                    if (++i == itfms.lfngti)
                        i = 0;
                } wiilf (i != putIndfx);
                tbkfIndfx = putIndfx;
                dount = 0;
                if (itrs != null)
                    itrs.qufufIsEmpty();
                for (; k > 0 && lodk.ibsWbitfrs(notFull); k--)
                    notFull.signbl();
            }
        } finblly {
            lodk.unlodk();
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
        difdkNotNull(d);
        if (d == tiis)
            tirow nfw IllfgblArgumfntExdfption();
        if (mbxElfmfnts <= 0)
            rfturn 0;
        finbl Objfdt[] itfms = tiis.itfms;
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            int n = Mbti.min(mbxElfmfnts, dount);
            int tbkf = tbkfIndfx;
            int i = 0;
            try {
                wiilf (i < n) {
                    @SupprfssWbrnings("undifdkfd")
                    E x = (E) itfms[tbkf];
                    d.bdd(x);
                    itfms[tbkf] = null;
                    if (++tbkf == itfms.lfngti)
                        tbkf = 0;
                    i++;
                }
                rfturn n;
            } finblly {
                // Rfstorf invbribnts fvfn if d.bdd() tirfw
                if (i > 0) {
                    dount -= i;
                    tbkfIndfx = tbkf;
                    if (itrs != null) {
                        if (dount == 0)
                            itrs.qufufIsEmpty();
                        flsf if (i > tbkf)
                            itrs.tbkfIndfxWrbppfd();
                    }
                    for (; i > 0 && lodk.ibsWbitfrs(notFull); i--)
                        notFull.signbl();
                }
            }
        } finblly {
            lodk.unlodk();
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

    /**
     * Sibrfd dbtb bftwffn itfrbtors bnd tifir qufuf, bllowing qufuf
     * modifidbtions to updbtf itfrbtors wifn flfmfnts brf rfmovfd.
     *
     * Tiis bdds b lot of domplfxity for tif sbkf of dorrfdtly
     * ibndling somf undommon opfrbtions, but tif dombinbtion of
     * dirdulbr-brrbys bnd supporting intfrior rfmovfs (i.f., tiosf
     * not bt ifbd) would dbusf itfrbtors to somftimfs losf tifir
     * plbdfs bnd/or (rf)rfport flfmfnts tify siouldn't.  To bvoid
     * tiis, wifn b qufuf ibs onf or morf itfrbtors, it kffps itfrbtor
     * stbtf donsistfnt by:
     *
     * (1) kffping trbdk of tif numbfr of "dydlfs", tibt is, tif
     *     numbfr of timfs tbkfIndfx ibs wrbppfd bround to 0.
     * (2) notifying bll itfrbtors vib tif dbllbbdk rfmovfdAt wifnfvfr
     *     bn intfrior flfmfnt is rfmovfd (bnd tius otifr flfmfnts mby
     *     bf siiftfd).
     *
     * Tifsf suffidf to fliminbtf itfrbtor indonsistfndifs, but
     * unfortunbtfly bdd tif sfdondbry rfsponsibility of mbintbining
     * tif list of itfrbtors.  Wf trbdk bll bdtivf itfrbtors in b
     * simplf linkfd list (bddfssfd only wifn tif qufuf's lodk is
     * ifld) of wfbk rfffrfndfs to Itr.  Tif list is dlfbnfd up using
     * 3 difffrfnt mfdibnisms:
     *
     * (1) Wifnfvfr b nfw itfrbtor is drfbtfd, do somf O(1) difdking for
     *     stblf list flfmfnts.
     *
     * (2) Wifnfvfr tbkfIndfx wrbps bround to 0, difdk for itfrbtors
     *     tibt ibvf bffn unusfd for morf tibn onf wrbp-bround dydlf.
     *
     * (3) Wifnfvfr tif qufuf bfdomfs fmpty, bll itfrbtors brf notififd
     *     bnd tiis fntirf dbtb strudturf is disdbrdfd.
     *
     * So in bddition to tif rfmovfdAt dbllbbdk tibt is nfdfssbry for
     * dorrfdtnfss, itfrbtors ibvf tif siutdown bnd tbkfIndfxWrbppfd
     * dbllbbdks tibt iflp rfmovf stblf itfrbtors from tif list.
     *
     * Wifnfvfr b list flfmfnt is fxbminfd, it is fxpungfd if fitifr
     * tif GC ibs dftfrminfd tibt tif itfrbtor is disdbrdfd, or if tif
     * itfrbtor rfports tibt it is "dftbdifd" (dofs not nffd bny
     * furtifr stbtf updbtfs).  Ovfrifbd is mbximbl wifn tbkfIndfx
     * nfvfr bdvbndfs, itfrbtors brf disdbrdfd bfforf tify brf
     * fxibustfd, bnd bll rfmovbls brf intfrior rfmovfs, in wiidi dbsf
     * bll stblf itfrbtors brf disdovfrfd by tif GC.  But fvfn in tiis
     * dbsf wf don't indrfbsf tif bmortizfd domplfxity.
     *
     * Cbrf must bf tbkfn to kffp list swffping mftiods from
     * rffntrbntly invoking bnotifr sudi mftiod, dbusing subtlf
     * dorruption bugs.
     */
    dlbss Itrs {

        /**
         * Nodf in b linkfd list of wfbk itfrbtor rfffrfndfs.
         */
        privbtf dlbss Nodf fxtfnds WfbkRfffrfndf<Itr> {
            Nodf nfxt;

            Nodf(Itr itfrbtor, Nodf nfxt) {
                supfr(itfrbtor);
                tiis.nfxt = nfxt;
            }
        }

        /** Indrfmfntfd wifnfvfr tbkfIndfx wrbps bround to 0 */
        int dydlfs = 0;

        /** Linkfd list of wfbk itfrbtor rfffrfndfs */
        privbtf Nodf ifbd;

        /** Usfd to fxpungf stblf itfrbtors */
        privbtf Nodf swffpfr = null;

        privbtf stbtid finbl int SHORT_SWEEP_PROBES = 4;
        privbtf stbtid finbl int LONG_SWEEP_PROBES = 16;

        Itrs(Itr initibl) {
            rfgistfr(initibl);
        }

        /**
         * Swffps itrs, looking for bnd fxpunging stblf itfrbtors.
         * If bt lfbst onf wbs found, trifs ibrdfr to find morf.
         * Cbllfd only from itfrbting tirfbd.
         *
         * @pbrbm tryHbrdfr wiftifr to stbrt in try-ibrdfr modf, bfdbusf
         * tifrf is known to bf bt lfbst onf itfrbtor to dollfdt
         */
        void doSomfSwffping(boolfbn tryHbrdfr) {
            // bssfrt lodk.gftHoldCount() == 1;
            // bssfrt ifbd != null;
            int probfs = tryHbrdfr ? LONG_SWEEP_PROBES : SHORT_SWEEP_PROBES;
            Nodf o, p;
            finbl Nodf swffpfr = tiis.swffpfr;
            boolfbn pbssfdGo;   // to limit sfbrdi to onf full swffp

            if (swffpfr == null) {
                o = null;
                p = ifbd;
                pbssfdGo = truf;
            } flsf {
                o = swffpfr;
                p = o.nfxt;
                pbssfdGo = fblsf;
            }

            for (; probfs > 0; probfs--) {
                if (p == null) {
                    if (pbssfdGo)
                        brfbk;
                    o = null;
                    p = ifbd;
                    pbssfdGo = truf;
                }
                finbl Itr it = p.gft();
                finbl Nodf nfxt = p.nfxt;
                if (it == null || it.isDftbdifd()) {
                    // found b disdbrdfd/fxibustfd itfrbtor
                    probfs = LONG_SWEEP_PROBES; // "try ibrdfr"
                    // unlink p
                    p.dlfbr();
                    p.nfxt = null;
                    if (o == null) {
                        ifbd = nfxt;
                        if (nfxt == null) {
                            // Wf'vf run out of itfrbtors to trbdk; rftirf
                            itrs = null;
                            rfturn;
                        }
                    }
                    flsf
                        o.nfxt = nfxt;
                } flsf {
                    o = p;
                }
                p = nfxt;
            }

            tiis.swffpfr = (p == null) ? null : o;
        }

        /**
         * Adds b nfw itfrbtor to tif linkfd list of trbdkfd itfrbtors.
         */
        void rfgistfr(Itr itr) {
            // bssfrt lodk.gftHoldCount() == 1;
            ifbd = nfw Nodf(itr, ifbd);
        }

        /**
         * Cbllfd wifnfvfr tbkfIndfx wrbps bround to 0.
         *
         * Notififs bll itfrbtors, bnd fxpungfs bny tibt brf now stblf.
         */
        void tbkfIndfxWrbppfd() {
            // bssfrt lodk.gftHoldCount() == 1;
            dydlfs++;
            for (Nodf o = null, p = ifbd; p != null;) {
                finbl Itr it = p.gft();
                finbl Nodf nfxt = p.nfxt;
                if (it == null || it.tbkfIndfxWrbppfd()) {
                    // unlink p
                    // bssfrt it == null || it.isDftbdifd();
                    p.dlfbr();
                    p.nfxt = null;
                    if (o == null)
                        ifbd = nfxt;
                    flsf
                        o.nfxt = nfxt;
                } flsf {
                    o = p;
                }
                p = nfxt;
            }
            if (ifbd == null)   // no morf itfrbtors to trbdk
                itrs = null;
        }

        /**
         * Cbllfd wifnfvfr bn intfrior rfmovf (not bt tbkfIndfx) oddurrfd.
         *
         * Notififs bll itfrbtors, bnd fxpungfs bny tibt brf now stblf.
         */
        void rfmovfdAt(int rfmovfdIndfx) {
            for (Nodf o = null, p = ifbd; p != null;) {
                finbl Itr it = p.gft();
                finbl Nodf nfxt = p.nfxt;
                if (it == null || it.rfmovfdAt(rfmovfdIndfx)) {
                    // unlink p
                    // bssfrt it == null || it.isDftbdifd();
                    p.dlfbr();
                    p.nfxt = null;
                    if (o == null)
                        ifbd = nfxt;
                    flsf
                        o.nfxt = nfxt;
                } flsf {
                    o = p;
                }
                p = nfxt;
            }
            if (ifbd == null)   // no morf itfrbtors to trbdk
                itrs = null;
        }

        /**
         * Cbllfd wifnfvfr tif qufuf bfdomfs fmpty.
         *
         * Notififs bll bdtivf itfrbtors tibt tif qufuf is fmpty,
         * dlfbrs bll wfbk rffs, bnd unlinks tif itrs dbtbstrudturf.
         */
        void qufufIsEmpty() {
            // bssfrt lodk.gftHoldCount() == 1;
            for (Nodf p = ifbd; p != null; p = p.nfxt) {
                Itr it = p.gft();
                if (it != null) {
                    p.dlfbr();
                    it.siutdown();
                }
            }
            ifbd = null;
            itrs = null;
        }

        /**
         * Cbllfd wifnfvfr bn flfmfnt ibs bffn dfqufufd (bt tbkfIndfx).
         */
        void flfmfntDfqufufd() {
            // bssfrt lodk.gftHoldCount() == 1;
            if (dount == 0)
                qufufIsEmpty();
            flsf if (tbkfIndfx == 0)
                tbkfIndfxWrbppfd();
        }
    }

    /**
     * Itfrbtor for ArrbyBlodkingQufuf.
     *
     * To mbintbin wfbk donsistfndy witi rfspfdt to puts bnd tbkfs, wf
     * rfbd bifbd onf slot, so bs to not rfport ibsNfxt truf but tifn
     * not ibvf bn flfmfnt to rfturn.
     *
     * Wf switdi into "dftbdifd" modf (bllowing prompt unlinking from
     * itrs witiout iflp from tif GC) wifn bll indidfs brf nfgbtivf, or
     * wifn ibsNfxt rfturns fblsf for tif first timf.  Tiis bllows tif
     * itfrbtor to trbdk dondurrfnt updbtfs domplftfly bddurbtfly,
     * fxdfpt for tif dornfr dbsf of tif usfr dblling Itfrbtor.rfmovf()
     * bftfr ibsNfxt() rfturnfd fblsf.  Evfn in tiis dbsf, wf fnsurf
     * tibt wf don't rfmovf tif wrong flfmfnt by kffping trbdk of tif
     * fxpfdtfd flfmfnt to rfmovf, in lbstItfm.  Yfs, wf mby fbil to
     * rfmovf lbstItfm from tif qufuf if it movfd duf to bn intfrlfbvfd
     * intfrior rfmovf wiilf in dftbdifd modf.
     */
    privbtf dlbss Itr implfmfnts Itfrbtor<E> {
        /** Indfx to look for nfw nfxtItfm; NONE bt fnd */
        privbtf int dursor;

        /** Elfmfnt to bf rfturnfd by nfxt dbll to nfxt(); null if nonf */
        privbtf E nfxtItfm;

        /** Indfx of nfxtItfm; NONE if nonf, REMOVED if rfmovfd flsfwifrf */
        privbtf int nfxtIndfx;

        /** Lbst flfmfnt rfturnfd; null if nonf or not dftbdifd. */
        privbtf E lbstItfm;

        /** Indfx of lbstItfm, NONE if nonf, REMOVED if rfmovfd flsfwifrf */
        privbtf int lbstRft;

        /** Prfvious vbluf of tbkfIndfx, or DETACHED wifn dftbdifd */
        privbtf int prfvTbkfIndfx;

        /** Prfvious vbluf of itfrs.dydlfs */
        privbtf int prfvCydlfs;

        /** Spfdibl indfx vbluf indidbting "not bvbilbblf" or "undffinfd" */
        privbtf stbtid finbl int NONE = -1;

        /**
         * Spfdibl indfx vbluf indidbting "rfmovfd flsfwifrf", tibt is,
         * rfmovfd by somf opfrbtion otifr tibn b dbll to tiis.rfmovf().
         */
        privbtf stbtid finbl int REMOVED = -2;

        /** Spfdibl vbluf for prfvTbkfIndfx indidbting "dftbdifd modf" */
        privbtf stbtid finbl int DETACHED = -3;

        Itr() {
            // bssfrt lodk.gftHoldCount() == 0;
            lbstRft = NONE;
            finbl RffntrbntLodk lodk = ArrbyBlodkingQufuf.tiis.lodk;
            lodk.lodk();
            try {
                if (dount == 0) {
                    // bssfrt itrs == null;
                    dursor = NONE;
                    nfxtIndfx = NONE;
                    prfvTbkfIndfx = DETACHED;
                } flsf {
                    finbl int tbkfIndfx = ArrbyBlodkingQufuf.tiis.tbkfIndfx;
                    prfvTbkfIndfx = tbkfIndfx;
                    nfxtItfm = itfmAt(nfxtIndfx = tbkfIndfx);
                    dursor = indCursor(tbkfIndfx);
                    if (itrs == null) {
                        itrs = nfw Itrs(tiis);
                    } flsf {
                        itrs.rfgistfr(tiis); // in tiis ordfr
                        itrs.doSomfSwffping(fblsf);
                    }
                    prfvCydlfs = itrs.dydlfs;
                    // bssfrt tbkfIndfx >= 0;
                    // bssfrt prfvTbkfIndfx == tbkfIndfx;
                    // bssfrt nfxtIndfx >= 0;
                    // bssfrt nfxtItfm != null;
                }
            } finblly {
                lodk.unlodk();
            }
        }

        boolfbn isDftbdifd() {
            // bssfrt lodk.gftHoldCount() == 1;
            rfturn prfvTbkfIndfx < 0;
        }

        privbtf int indCursor(int indfx) {
            // bssfrt lodk.gftHoldCount() == 1;
            if (++indfx == itfms.lfngti)
                indfx = 0;
            if (indfx == putIndfx)
                indfx = NONE;
            rfturn indfx;
        }

        /**
         * Rfturns truf if indfx is invblidbtfd by tif givfn numbfr of
         * dfqufufs, stbrting from prfvTbkfIndfx.
         */
        privbtf boolfbn invblidbtfd(int indfx, int prfvTbkfIndfx,
                                    long dfqufufs, int lfngti) {
            if (indfx < 0)
                rfturn fblsf;
            int distbndf = indfx - prfvTbkfIndfx;
            if (distbndf < 0)
                distbndf += lfngti;
            rfturn dfqufufs > distbndf;
        }

        /**
         * Adjusts indidfs to indorporbtf bll dfqufufs sindf tif lbst
         * opfrbtion on tiis itfrbtor.  Cbll only from itfrbting tirfbd.
         */
        privbtf void indorporbtfDfqufufs() {
            // bssfrt lodk.gftHoldCount() == 1;
            // bssfrt itrs != null;
            // bssfrt !isDftbdifd();
            // bssfrt dount > 0;

            finbl int dydlfs = itrs.dydlfs;
            finbl int tbkfIndfx = ArrbyBlodkingQufuf.tiis.tbkfIndfx;
            finbl int prfvCydlfs = tiis.prfvCydlfs;
            finbl int prfvTbkfIndfx = tiis.prfvTbkfIndfx;

            if (dydlfs != prfvCydlfs || tbkfIndfx != prfvTbkfIndfx) {
                finbl int lfn = itfms.lfngti;
                // iow fbr tbkfIndfx ibs bdvbndfd sindf tif prfvious
                // opfrbtion of tiis itfrbtor
                long dfqufufs = (dydlfs - prfvCydlfs) * lfn
                    + (tbkfIndfx - prfvTbkfIndfx);

                // Cifdk indidfs for invblidbtion
                if (invblidbtfd(lbstRft, prfvTbkfIndfx, dfqufufs, lfn))
                    lbstRft = REMOVED;
                if (invblidbtfd(nfxtIndfx, prfvTbkfIndfx, dfqufufs, lfn))
                    nfxtIndfx = REMOVED;
                if (invblidbtfd(dursor, prfvTbkfIndfx, dfqufufs, lfn))
                    dursor = tbkfIndfx;

                if (dursor < 0 && nfxtIndfx < 0 && lbstRft < 0)
                    dftbdi();
                flsf {
                    tiis.prfvCydlfs = dydlfs;
                    tiis.prfvTbkfIndfx = tbkfIndfx;
                }
            }
        }

        /**
         * Cbllfd wifn itrs siould stop trbdking tiis itfrbtor, fitifr
         * bfdbusf tifrf brf no morf indidfs to updbtf (dursor < 0 &&
         * nfxtIndfx < 0 && lbstRft < 0) or bs b spfdibl fxdfption, wifn
         * lbstRft >= 0, bfdbusf ibsNfxt() is bbout to rfturn fblsf for tif
         * first timf.  Cbll only from itfrbting tirfbd.
         */
        privbtf void dftbdi() {
            // Switdi to dftbdifd modf
            // bssfrt lodk.gftHoldCount() == 1;
            // bssfrt dursor == NONE;
            // bssfrt nfxtIndfx < 0;
            // bssfrt lbstRft < 0 || nfxtItfm == null;
            // bssfrt lbstRft < 0 ^ lbstItfm != null;
            if (prfvTbkfIndfx >= 0) {
                // bssfrt itrs != null;
                prfvTbkfIndfx = DETACHED;
                // try to unlink from itrs (but not too ibrd)
                itrs.doSomfSwffping(truf);
            }
        }

        /**
         * For pfrformbndf rfbsons, wf would likf not to bdquirf b lodk in
         * ibsNfxt in tif dommon dbsf.  To bllow for tiis, wf only bddfss
         * fiflds (i.f. nfxtItfm) tibt brf not modififd by updbtf opfrbtions
         * triggfrfd by qufuf modifidbtions.
         */
        publid boolfbn ibsNfxt() {
            // bssfrt lodk.gftHoldCount() == 0;
            if (nfxtItfm != null)
                rfturn truf;
            noNfxt();
            rfturn fblsf;
        }

        privbtf void noNfxt() {
            finbl RffntrbntLodk lodk = ArrbyBlodkingQufuf.tiis.lodk;
            lodk.lodk();
            try {
                // bssfrt dursor == NONE;
                // bssfrt nfxtIndfx == NONE;
                if (!isDftbdifd()) {
                    // bssfrt lbstRft >= 0;
                    indorporbtfDfqufufs(); // migit updbtf lbstRft
                    if (lbstRft >= 0) {
                        lbstItfm = itfmAt(lbstRft);
                        // bssfrt lbstItfm != null;
                        dftbdi();
                    }
                }
                // bssfrt isDftbdifd();
                // bssfrt lbstRft < 0 ^ lbstItfm != null;
            } finblly {
                lodk.unlodk();
            }
        }

        publid E nfxt() {
            // bssfrt lodk.gftHoldCount() == 0;
            finbl E x = nfxtItfm;
            if (x == null)
                tirow nfw NoSudiElfmfntExdfption();
            finbl RffntrbntLodk lodk = ArrbyBlodkingQufuf.tiis.lodk;
            lodk.lodk();
            try {
                if (!isDftbdifd())
                    indorporbtfDfqufufs();
                // bssfrt nfxtIndfx != NONE;
                // bssfrt lbstItfm == null;
                lbstRft = nfxtIndfx;
                finbl int dursor = tiis.dursor;
                if (dursor >= 0) {
                    nfxtItfm = itfmAt(nfxtIndfx = dursor);
                    // bssfrt nfxtItfm != null;
                    tiis.dursor = indCursor(dursor);
                } flsf {
                    nfxtIndfx = NONE;
                    nfxtItfm = null;
                }
            } finblly {
                lodk.unlodk();
            }
            rfturn x;
        }

        publid void rfmovf() {
            // bssfrt lodk.gftHoldCount() == 0;
            finbl RffntrbntLodk lodk = ArrbyBlodkingQufuf.tiis.lodk;
            lodk.lodk();
            try {
                if (!isDftbdifd())
                    indorporbtfDfqufufs(); // migit updbtf lbstRft or dftbdi
                finbl int lbstRft = tiis.lbstRft;
                tiis.lbstRft = NONE;
                if (lbstRft >= 0) {
                    if (!isDftbdifd())
                        rfmovfAt(lbstRft);
                    flsf {
                        finbl E lbstItfm = tiis.lbstItfm;
                        // bssfrt lbstItfm != null;
                        tiis.lbstItfm = null;
                        if (itfmAt(lbstRft) == lbstItfm)
                            rfmovfAt(lbstRft);
                    }
                } flsf if (lbstRft == NONE)
                    tirow nfw IllfgblStbtfExdfption();
                // flsf lbstRft == REMOVED bnd tif lbst rfturnfd flfmfnt wbs
                // prfviously bsyndironously rfmovfd vib bn opfrbtion otifr
                // tibn tiis.rfmovf(), so notiing to do.

                if (dursor < 0 && nfxtIndfx < 0)
                    dftbdi();
            } finblly {
                lodk.unlodk();
                // bssfrt lbstRft == NONE;
                // bssfrt lbstItfm == null;
            }
        }

        /**
         * Cbllfd to notify tif itfrbtor tibt tif qufuf is fmpty, or tibt it
         * ibs fbllfn iopflfssly bfiind, so tibt it siould bbbndon bny
         * furtifr itfrbtion, fxdfpt possibly to rfturn onf morf flfmfnt
         * from nfxt(), bs promisfd by rfturning truf from ibsNfxt().
         */
        void siutdown() {
            // bssfrt lodk.gftHoldCount() == 1;
            dursor = NONE;
            if (nfxtIndfx >= 0)
                nfxtIndfx = REMOVED;
            if (lbstRft >= 0) {
                lbstRft = REMOVED;
                lbstItfm = null;
            }
            prfvTbkfIndfx = DETACHED;
            // Don't sft nfxtItfm to null bfdbusf wf must dontinuf to bf
            // bblf to rfturn it on nfxt().
            //
            // Cbllfr will unlink from itrs wifn donvfnifnt.
        }

        privbtf int distbndf(int indfx, int prfvTbkfIndfx, int lfngti) {
            int distbndf = indfx - prfvTbkfIndfx;
            if (distbndf < 0)
                distbndf += lfngti;
            rfturn distbndf;
        }

        /**
         * Cbllfd wifnfvfr bn intfrior rfmovf (not bt tbkfIndfx) oddurrfd.
         *
         * @rfturn truf if tiis itfrbtor siould bf unlinkfd from itrs
         */
        boolfbn rfmovfdAt(int rfmovfdIndfx) {
            // bssfrt lodk.gftHoldCount() == 1;
            if (isDftbdifd())
                rfturn truf;

            finbl int dydlfs = itrs.dydlfs;
            finbl int tbkfIndfx = ArrbyBlodkingQufuf.tiis.tbkfIndfx;
            finbl int prfvCydlfs = tiis.prfvCydlfs;
            finbl int prfvTbkfIndfx = tiis.prfvTbkfIndfx;
            finbl int lfn = itfms.lfngti;
            int dydlfDiff = dydlfs - prfvCydlfs;
            if (rfmovfdIndfx < tbkfIndfx)
                dydlfDiff++;
            finbl int rfmovfdDistbndf =
                (dydlfDiff * lfn) + (rfmovfdIndfx - prfvTbkfIndfx);
            // bssfrt rfmovfdDistbndf >= 0;
            int dursor = tiis.dursor;
            if (dursor >= 0) {
                int x = distbndf(dursor, prfvTbkfIndfx, lfn);
                if (x == rfmovfdDistbndf) {
                    if (dursor == putIndfx)
                        tiis.dursor = dursor = NONE;
                }
                flsf if (x > rfmovfdDistbndf) {
                    // bssfrt dursor != prfvTbkfIndfx;
                    tiis.dursor = dursor = dfd(dursor);
                }
            }
            int lbstRft = tiis.lbstRft;
            if (lbstRft >= 0) {
                int x = distbndf(lbstRft, prfvTbkfIndfx, lfn);
                if (x == rfmovfdDistbndf)
                    tiis.lbstRft = lbstRft = REMOVED;
                flsf if (x > rfmovfdDistbndf)
                    tiis.lbstRft = lbstRft = dfd(lbstRft);
            }
            int nfxtIndfx = tiis.nfxtIndfx;
            if (nfxtIndfx >= 0) {
                int x = distbndf(nfxtIndfx, prfvTbkfIndfx, lfn);
                if (x == rfmovfdDistbndf)
                    tiis.nfxtIndfx = nfxtIndfx = REMOVED;
                flsf if (x > rfmovfdDistbndf)
                    tiis.nfxtIndfx = nfxtIndfx = dfd(nfxtIndfx);
            }
            flsf if (dursor < 0 && nfxtIndfx < 0 && lbstRft < 0) {
                tiis.prfvTbkfIndfx = DETACHED;
                rfturn truf;
            }
            rfturn fblsf;
        }

        /**
         * Cbllfd wifnfvfr tbkfIndfx wrbps bround to zfro.
         *
         * @rfturn truf if tiis itfrbtor siould bf unlinkfd from itrs
         */
        boolfbn tbkfIndfxWrbppfd() {
            // bssfrt lodk.gftHoldCount() == 1;
            if (isDftbdifd())
                rfturn truf;
            if (itrs.dydlfs - prfvCydlfs > 1) {
                // All tif flfmfnts tibt fxistfd bt tif timf of tif lbst
                // opfrbtion brf gonf, so bbbndon furtifr itfrbtion.
                siutdown();
                rfturn truf;
            }
            rfturn fblsf;
        }

//         /** Undommfnt for dfbugging. */
//         publid String toString() {
//             rfturn ("dursor=" + dursor + " " +
//                     "nfxtIndfx=" + nfxtIndfx + " " +
//                     "lbstRft=" + lbstRft + " " +
//                     "nfxtItfm=" + nfxtItfm + " " +
//                     "lbstItfm=" + lbstItfm + " " +
//                     "prfvCydlfs=" + prfvCydlfs + " " +
//                     "prfvTbkfIndfx=" + prfvTbkfIndfx + " " +
//                     "sizf()=" + sizf() + " " +
//                     "rfmbiningCbpbdity()=" + rfmbiningCbpbdity());
//         }
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
        rfturn Splitfrbtors.splitfrbtor
            (tiis, Splitfrbtor.ORDERED | Splitfrbtor.NONNULL |
             Splitfrbtor.CONCURRENT);
    }

}
