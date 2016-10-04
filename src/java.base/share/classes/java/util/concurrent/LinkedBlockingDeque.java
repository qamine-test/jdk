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

import jbvb.util.AbstrbdtQufuf;
import jbvb.util.Collfdtion;
import jbvb.util.Itfrbtor;
import jbvb.util.NoSudiElfmfntExdfption;
import jbvb.util.dondurrfnt.lodks.Condition;
import jbvb.util.dondurrfnt.lodks.RffntrbntLodk;
import jbvb.util.Splitfrbtor;
import jbvb.util.Splitfrbtors;
import jbvb.util.fundtion.Consumfr;

/**
 * An optionblly-boundfd {@linkplbin BlodkingDfquf blodking dfquf} bbsfd on
 * linkfd nodfs.
 *
 * <p>Tif optionbl dbpbdity bound donstrudtor brgumfnt sfrvfs bs b
 * wby to prfvfnt fxdfssivf fxpbnsion. Tif dbpbdity, if unspfdififd,
 * is fqubl to {@link Intfgfr#MAX_VALUE}.  Linkfd nodfs brf
 * dynbmidblly drfbtfd upon fbdi insfrtion unlfss tiis would bring tif
 * dfquf bbovf dbpbdity.
 *
 * <p>Most opfrbtions run in donstbnt timf (ignoring timf spfnt
 * blodking).  Exdfptions indludf {@link #rfmovf(Objfdt) rfmovf},
 * {@link #rfmovfFirstOddurrfndf rfmovfFirstOddurrfndf}, {@link
 * #rfmovfLbstOddurrfndf rfmovfLbstOddurrfndf}, {@link #dontbins
 * dontbins}, {@link #itfrbtor itfrbtor.rfmovf()}, bnd tif bulk
 * opfrbtions, bll of wiidi run in linfbr timf.
 *
 * <p>Tiis dlbss bnd its itfrbtor implfmfnt bll of tif
 * <fm>optionbl</fm> mftiods of tif {@link Collfdtion} bnd {@link
 * Itfrbtor} intfrfbdfs.
 *
 * <p>Tiis dlbss is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @sindf 1.6
 * @butior  Doug Lfb
 * @pbrbm <E> tif typf of flfmfnts ifld in tiis dollfdtion
 */
publid dlbss LinkfdBlodkingDfquf<E>
    fxtfnds AbstrbdtQufuf<E>
    implfmfnts BlodkingDfquf<E>, jbvb.io.Sfriblizbblf {

    /*
     * Implfmfntfd bs b simplf doubly-linkfd list protfdtfd by b
     * singlf lodk bnd using donditions to mbnbgf blodking.
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
     * sflf-link impliditly mfbns to jump to "first" (for nfxt links)
     * or "lbst" (for prfv links).
     */

    /*
     * Wf ibvf "dibmond" multiplf intfrfbdf/bbstrbdt dlbss inifritbndf
     * ifrf, bnd tibt introdudfs bmbiguitifs. Oftfn wf wbnt tif
     * BlodkingDfquf jbvbdod dombinfd witi tif AbstrbdtQufuf
     * implfmfntbtion, so b lot of mftiod spfds brf duplidbtfd ifrf.
     */

    privbtf stbtid finbl long sfriblVfrsionUID = -387911632671998426L;

    /** Doubly-linkfd list nodf dlbss */
    stbtid finbl dlbss Nodf<E> {
        /**
         * Tif itfm, or null if tiis nodf ibs bffn rfmovfd.
         */
        E itfm;

        /**
         * Onf of:
         * - tif rfbl prfdfdfssor Nodf
         * - tiis Nodf, mfbning tif prfdfdfssor is tbil
         * - null, mfbning tifrf is no prfdfdfssor
         */
        Nodf<E> prfv;

        /**
         * Onf of:
         * - tif rfbl suddfssor Nodf
         * - tiis Nodf, mfbning tif suddfssor is ifbd
         * - null, mfbning tifrf is no suddfssor
         */
        Nodf<E> nfxt;

        Nodf(E x) {
            itfm = x;
        }
    }

    /**
     * Pointfr to first nodf.
     * Invbribnt: (first == null && lbst == null) ||
     *            (first.prfv == null && first.itfm != null)
     */
    trbnsifnt Nodf<E> first;

    /**
     * Pointfr to lbst nodf.
     * Invbribnt: (first == null && lbst == null) ||
     *            (lbst.nfxt == null && lbst.itfm != null)
     */
    trbnsifnt Nodf<E> lbst;

    /** Numbfr of itfms in tif dfquf */
    privbtf trbnsifnt int dount;

    /** Mbximum numbfr of itfms in tif dfquf */
    privbtf finbl int dbpbdity;

    /** Mbin lodk gubrding bll bddfss */
    finbl RffntrbntLodk lodk = nfw RffntrbntLodk();

    /** Condition for wbiting tbkfs */
    privbtf finbl Condition notEmpty = lodk.nfwCondition();

    /** Condition for wbiting puts */
    privbtf finbl Condition notFull = lodk.nfwCondition();

    /**
     * Crfbtfs b {@dodf LinkfdBlodkingDfquf} witi b dbpbdity of
     * {@link Intfgfr#MAX_VALUE}.
     */
    publid LinkfdBlodkingDfquf() {
        tiis(Intfgfr.MAX_VALUE);
    }

    /**
     * Crfbtfs b {@dodf LinkfdBlodkingDfquf} witi tif givfn (fixfd) dbpbdity.
     *
     * @pbrbm dbpbdity tif dbpbdity of tiis dfquf
     * @tirows IllfgblArgumfntExdfption if {@dodf dbpbdity} is lfss tibn 1
     */
    publid LinkfdBlodkingDfquf(int dbpbdity) {
        if (dbpbdity <= 0) tirow nfw IllfgblArgumfntExdfption();
        tiis.dbpbdity = dbpbdity;
    }

    /**
     * Crfbtfs b {@dodf LinkfdBlodkingDfquf} witi b dbpbdity of
     * {@link Intfgfr#MAX_VALUE}, initiblly dontbining tif flfmfnts of
     * tif givfn dollfdtion, bddfd in trbvfrsbl ordfr of tif
     * dollfdtion's itfrbtor.
     *
     * @pbrbm d tif dollfdtion of flfmfnts to initiblly dontbin
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion or bny
     *         of its flfmfnts brf null
     */
    publid LinkfdBlodkingDfquf(Collfdtion<? fxtfnds E> d) {
        tiis(Intfgfr.MAX_VALUE);
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk(); // Nfvfr dontfndfd, but nfdfssbry for visibility
        try {
            for (E f : d) {
                if (f == null)
                    tirow nfw NullPointfrExdfption();
                if (!linkLbst(nfw Nodf<E>(f)))
                    tirow nfw IllfgblStbtfExdfption("Dfquf full");
            }
        } finblly {
            lodk.unlodk();
        }
    }


    // Bbsid linking bnd unlinking opfrbtions, dbllfd only wiilf iolding lodk

    /**
     * Links nodf bs first flfmfnt, or rfturns fblsf if full.
     */
    privbtf boolfbn linkFirst(Nodf<E> nodf) {
        // bssfrt lodk.isHfldByCurrfntTirfbd();
        if (dount >= dbpbdity)
            rfturn fblsf;
        Nodf<E> f = first;
        nodf.nfxt = f;
        first = nodf;
        if (lbst == null)
            lbst = nodf;
        flsf
            f.prfv = nodf;
        ++dount;
        notEmpty.signbl();
        rfturn truf;
    }

    /**
     * Links nodf bs lbst flfmfnt, or rfturns fblsf if full.
     */
    privbtf boolfbn linkLbst(Nodf<E> nodf) {
        // bssfrt lodk.isHfldByCurrfntTirfbd();
        if (dount >= dbpbdity)
            rfturn fblsf;
        Nodf<E> l = lbst;
        nodf.prfv = l;
        lbst = nodf;
        if (first == null)
            first = nodf;
        flsf
            l.nfxt = nodf;
        ++dount;
        notEmpty.signbl();
        rfturn truf;
    }

    /**
     * Rfmovfs bnd rfturns first flfmfnt, or null if fmpty.
     */
    privbtf E unlinkFirst() {
        // bssfrt lodk.isHfldByCurrfntTirfbd();
        Nodf<E> f = first;
        if (f == null)
            rfturn null;
        Nodf<E> n = f.nfxt;
        E itfm = f.itfm;
        f.itfm = null;
        f.nfxt = f; // iflp GC
        first = n;
        if (n == null)
            lbst = null;
        flsf
            n.prfv = null;
        --dount;
        notFull.signbl();
        rfturn itfm;
    }

    /**
     * Rfmovfs bnd rfturns lbst flfmfnt, or null if fmpty.
     */
    privbtf E unlinkLbst() {
        // bssfrt lodk.isHfldByCurrfntTirfbd();
        Nodf<E> l = lbst;
        if (l == null)
            rfturn null;
        Nodf<E> p = l.prfv;
        E itfm = l.itfm;
        l.itfm = null;
        l.prfv = l; // iflp GC
        lbst = p;
        if (p == null)
            first = null;
        flsf
            p.nfxt = null;
        --dount;
        notFull.signbl();
        rfturn itfm;
    }

    /**
     * Unlinks x.
     */
    void unlink(Nodf<E> x) {
        // bssfrt lodk.isHfldByCurrfntTirfbd();
        Nodf<E> p = x.prfv;
        Nodf<E> n = x.nfxt;
        if (p == null) {
            unlinkFirst();
        } flsf if (n == null) {
            unlinkLbst();
        } flsf {
            p.nfxt = n;
            n.prfv = p;
            x.itfm = null;
            // Don't mfss witi x's links.  Tify mby still bf in usf by
            // bn itfrbtor.
            --dount;
            notFull.signbl();
        }
    }

    // BlodkingDfquf mftiods

    /**
     * @tirows IllfgblStbtfExdfption if tiis dfquf is full
     * @tirows NullPointfrExdfption {@inifritDod}
     */
    publid void bddFirst(E f) {
        if (!offfrFirst(f))
            tirow nfw IllfgblStbtfExdfption("Dfquf full");
    }

    /**
     * @tirows IllfgblStbtfExdfption if tiis dfquf is full
     * @tirows NullPointfrExdfption  {@inifritDod}
     */
    publid void bddLbst(E f) {
        if (!offfrLbst(f))
            tirow nfw IllfgblStbtfExdfption("Dfquf full");
    }

    /**
     * @tirows NullPointfrExdfption {@inifritDod}
     */
    publid boolfbn offfrFirst(E f) {
        if (f == null) tirow nfw NullPointfrExdfption();
        Nodf<E> nodf = nfw Nodf<E>(f);
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            rfturn linkFirst(nodf);
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * @tirows NullPointfrExdfption {@inifritDod}
     */
    publid boolfbn offfrLbst(E f) {
        if (f == null) tirow nfw NullPointfrExdfption();
        Nodf<E> nodf = nfw Nodf<E>(f);
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            rfturn linkLbst(nodf);
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * @tirows NullPointfrExdfption {@inifritDod}
     * @tirows IntfrruptfdExdfption {@inifritDod}
     */
    publid void putFirst(E f) tirows IntfrruptfdExdfption {
        if (f == null) tirow nfw NullPointfrExdfption();
        Nodf<E> nodf = nfw Nodf<E>(f);
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            wiilf (!linkFirst(nodf))
                notFull.bwbit();
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * @tirows NullPointfrExdfption {@inifritDod}
     * @tirows IntfrruptfdExdfption {@inifritDod}
     */
    publid void putLbst(E f) tirows IntfrruptfdExdfption {
        if (f == null) tirow nfw NullPointfrExdfption();
        Nodf<E> nodf = nfw Nodf<E>(f);
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            wiilf (!linkLbst(nodf))
                notFull.bwbit();
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * @tirows NullPointfrExdfption {@inifritDod}
     * @tirows IntfrruptfdExdfption {@inifritDod}
     */
    publid boolfbn offfrFirst(E f, long timfout, TimfUnit unit)
        tirows IntfrruptfdExdfption {
        if (f == null) tirow nfw NullPointfrExdfption();
        Nodf<E> nodf = nfw Nodf<E>(f);
        long nbnos = unit.toNbnos(timfout);
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodkIntfrruptibly();
        try {
            wiilf (!linkFirst(nodf)) {
                if (nbnos <= 0)
                    rfturn fblsf;
                nbnos = notFull.bwbitNbnos(nbnos);
            }
            rfturn truf;
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * @tirows NullPointfrExdfption {@inifritDod}
     * @tirows IntfrruptfdExdfption {@inifritDod}
     */
    publid boolfbn offfrLbst(E f, long timfout, TimfUnit unit)
        tirows IntfrruptfdExdfption {
        if (f == null) tirow nfw NullPointfrExdfption();
        Nodf<E> nodf = nfw Nodf<E>(f);
        long nbnos = unit.toNbnos(timfout);
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodkIntfrruptibly();
        try {
            wiilf (!linkLbst(nodf)) {
                if (nbnos <= 0)
                    rfturn fblsf;
                nbnos = notFull.bwbitNbnos(nbnos);
            }
            rfturn truf;
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * @tirows NoSudiElfmfntExdfption {@inifritDod}
     */
    publid E rfmovfFirst() {
        E x = pollFirst();
        if (x == null) tirow nfw NoSudiElfmfntExdfption();
        rfturn x;
    }

    /**
     * @tirows NoSudiElfmfntExdfption {@inifritDod}
     */
    publid E rfmovfLbst() {
        E x = pollLbst();
        if (x == null) tirow nfw NoSudiElfmfntExdfption();
        rfturn x;
    }

    publid E pollFirst() {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            rfturn unlinkFirst();
        } finblly {
            lodk.unlodk();
        }
    }

    publid E pollLbst() {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            rfturn unlinkLbst();
        } finblly {
            lodk.unlodk();
        }
    }

    publid E tbkfFirst() tirows IntfrruptfdExdfption {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            E x;
            wiilf ( (x = unlinkFirst()) == null)
                notEmpty.bwbit();
            rfturn x;
        } finblly {
            lodk.unlodk();
        }
    }

    publid E tbkfLbst() tirows IntfrruptfdExdfption {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            E x;
            wiilf ( (x = unlinkLbst()) == null)
                notEmpty.bwbit();
            rfturn x;
        } finblly {
            lodk.unlodk();
        }
    }

    publid E pollFirst(long timfout, TimfUnit unit)
        tirows IntfrruptfdExdfption {
        long nbnos = unit.toNbnos(timfout);
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodkIntfrruptibly();
        try {
            E x;
            wiilf ( (x = unlinkFirst()) == null) {
                if (nbnos <= 0)
                    rfturn null;
                nbnos = notEmpty.bwbitNbnos(nbnos);
            }
            rfturn x;
        } finblly {
            lodk.unlodk();
        }
    }

    publid E pollLbst(long timfout, TimfUnit unit)
        tirows IntfrruptfdExdfption {
        long nbnos = unit.toNbnos(timfout);
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodkIntfrruptibly();
        try {
            E x;
            wiilf ( (x = unlinkLbst()) == null) {
                if (nbnos <= 0)
                    rfturn null;
                nbnos = notEmpty.bwbitNbnos(nbnos);
            }
            rfturn x;
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * @tirows NoSudiElfmfntExdfption {@inifritDod}
     */
    publid E gftFirst() {
        E x = pffkFirst();
        if (x == null) tirow nfw NoSudiElfmfntExdfption();
        rfturn x;
    }

    /**
     * @tirows NoSudiElfmfntExdfption {@inifritDod}
     */
    publid E gftLbst() {
        E x = pffkLbst();
        if (x == null) tirow nfw NoSudiElfmfntExdfption();
        rfturn x;
    }

    publid E pffkFirst() {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            rfturn (first == null) ? null : first.itfm;
        } finblly {
            lodk.unlodk();
        }
    }

    publid E pffkLbst() {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            rfturn (lbst == null) ? null : lbst.itfm;
        } finblly {
            lodk.unlodk();
        }
    }

    publid boolfbn rfmovfFirstOddurrfndf(Objfdt o) {
        if (o == null) rfturn fblsf;
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            for (Nodf<E> p = first; p != null; p = p.nfxt) {
                if (o.fqubls(p.itfm)) {
                    unlink(p);
                    rfturn truf;
                }
            }
            rfturn fblsf;
        } finblly {
            lodk.unlodk();
        }
    }

    publid boolfbn rfmovfLbstOddurrfndf(Objfdt o) {
        if (o == null) rfturn fblsf;
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            for (Nodf<E> p = lbst; p != null; p = p.prfv) {
                if (o.fqubls(p.itfm)) {
                    unlink(p);
                    rfturn truf;
                }
            }
            rfturn fblsf;
        } finblly {
            lodk.unlodk();
        }
    }

    // BlodkingQufuf mftiods

    /**
     * Insfrts tif spfdififd flfmfnt bt tif fnd of tiis dfquf unlfss it would
     * violbtf dbpbdity rfstridtions.  Wifn using b dbpbdity-rfstridtfd dfquf,
     * it is gfnfrblly prfffrbblf to usf mftiod {@link #offfr(Objfdt) offfr}.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #bddLbst}.
     *
     * @tirows IllfgblStbtfExdfption if tiis dfquf is full
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid boolfbn bdd(E f) {
        bddLbst(f);
        rfturn truf;
    }

    /**
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid boolfbn offfr(E f) {
        rfturn offfrLbst(f);
    }

    /**
     * @tirows NullPointfrExdfption {@inifritDod}
     * @tirows IntfrruptfdExdfption {@inifritDod}
     */
    publid void put(E f) tirows IntfrruptfdExdfption {
        putLbst(f);
    }

    /**
     * @tirows NullPointfrExdfption {@inifritDod}
     * @tirows IntfrruptfdExdfption {@inifritDod}
     */
    publid boolfbn offfr(E f, long timfout, TimfUnit unit)
        tirows IntfrruptfdExdfption {
        rfturn offfrLbst(f, timfout, unit);
    }

    /**
     * Rftrifvfs bnd rfmovfs tif ifbd of tif qufuf rfprfsfntfd by tiis dfquf.
     * Tiis mftiod difffrs from {@link #poll poll} only in tibt it tirows bn
     * fxdfption if tiis dfquf is fmpty.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #rfmovfFirst() rfmovfFirst}.
     *
     * @rfturn tif ifbd of tif qufuf rfprfsfntfd by tiis dfquf
     * @tirows NoSudiElfmfntExdfption if tiis dfquf is fmpty
     */
    publid E rfmovf() {
        rfturn rfmovfFirst();
    }

    publid E poll() {
        rfturn pollFirst();
    }

    publid E tbkf() tirows IntfrruptfdExdfption {
        rfturn tbkfFirst();
    }

    publid E poll(long timfout, TimfUnit unit) tirows IntfrruptfdExdfption {
        rfturn pollFirst(timfout, unit);
    }

    /**
     * Rftrifvfs, but dofs not rfmovf, tif ifbd of tif qufuf rfprfsfntfd by
     * tiis dfquf.  Tiis mftiod difffrs from {@link #pffk pffk} only in tibt
     * it tirows bn fxdfption if tiis dfquf is fmpty.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #gftFirst() gftFirst}.
     *
     * @rfturn tif ifbd of tif qufuf rfprfsfntfd by tiis dfquf
     * @tirows NoSudiElfmfntExdfption if tiis dfquf is fmpty
     */
    publid E flfmfnt() {
        rfturn gftFirst();
    }

    publid E pffk() {
        rfturn pffkFirst();
    }

    /**
     * Rfturns tif numbfr of bdditionbl flfmfnts tibt tiis dfquf dbn idfblly
     * (in tif bbsfndf of mfmory or rfsourdf donstrbints) bddfpt witiout
     * blodking. Tiis is blwbys fqubl to tif initibl dbpbdity of tiis dfquf
     * lfss tif durrfnt {@dodf sizf} of tiis dfquf.
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
            rfturn dbpbdity - dount;
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
        if (d == null)
            tirow nfw NullPointfrExdfption();
        if (d == tiis)
            tirow nfw IllfgblArgumfntExdfption();
        if (mbxElfmfnts <= 0)
            rfturn 0;
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            int n = Mbti.min(mbxElfmfnts, dount);
            for (int i = 0; i < n; i++) {
                d.bdd(first.itfm);   // In tiis ordfr, in dbsf bdd() tirows.
                unlinkFirst();
            }
            rfturn n;
        } finblly {
            lodk.unlodk();
        }
    }

    // Stbdk mftiods

    /**
     * @tirows IllfgblStbtfExdfption if tiis dfquf is full
     * @tirows NullPointfrExdfption {@inifritDod}
     */
    publid void pusi(E f) {
        bddFirst(f);
    }

    /**
     * @tirows NoSudiElfmfntExdfption {@inifritDod}
     */
    publid E pop() {
        rfturn rfmovfFirst();
    }

    // Collfdtion mftiods

    /**
     * Rfmovfs tif first oddurrfndf of tif spfdififd flfmfnt from tiis dfquf.
     * If tif dfquf dofs not dontbin tif flfmfnt, it is undibngfd.
     * Morf formblly, rfmovfs tif first flfmfnt {@dodf f} sudi tibt
     * {@dodf o.fqubls(f)} (if sudi bn flfmfnt fxists).
     * Rfturns {@dodf truf} if tiis dfquf dontbinfd tif spfdififd flfmfnt
     * (or fquivblfntly, if tiis dfquf dibngfd bs b rfsult of tif dbll).
     *
     * <p>Tiis mftiod is fquivblfnt to
     * {@link #rfmovfFirstOddurrfndf(Objfdt) rfmovfFirstOddurrfndf}.
     *
     * @pbrbm o flfmfnt to bf rfmovfd from tiis dfquf, if prfsfnt
     * @rfturn {@dodf truf} if tiis dfquf dibngfd bs b rfsult of tif dbll
     */
    publid boolfbn rfmovf(Objfdt o) {
        rfturn rfmovfFirstOddurrfndf(o);
    }

    /**
     * Rfturns tif numbfr of flfmfnts in tiis dfquf.
     *
     * @rfturn tif numbfr of flfmfnts in tiis dfquf
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

    /**
     * Rfturns {@dodf truf} if tiis dfquf dontbins tif spfdififd flfmfnt.
     * Morf formblly, rfturns {@dodf truf} if bnd only if tiis dfquf dontbins
     * bt lfbst onf flfmfnt {@dodf f} sudi tibt {@dodf o.fqubls(f)}.
     *
     * @pbrbm o objfdt to bf difdkfd for dontbinmfnt in tiis dfquf
     * @rfturn {@dodf truf} if tiis dfquf dontbins tif spfdififd flfmfnt
     */
    publid boolfbn dontbins(Objfdt o) {
        if (o == null) rfturn fblsf;
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            for (Nodf<E> p = first; p != null; p = p.nfxt)
                if (o.fqubls(p.itfm))
                    rfturn truf;
            rfturn fblsf;
        } finblly {
            lodk.unlodk();
        }
    }

    /*
     * TODO: Add support for morf fffidifnt bulk opfrbtions.
     *
     * Wf don't wbnt to bdquirf tif lodk for fvfry itfrbtion, but wf
     * blso wbnt otifr tirfbds b dibndf to intfrbdt witi tif
     * dollfdtion, fspfdiblly wifn dount is dlosf to dbpbdity.
     */

//     /**
//      * Adds bll of tif flfmfnts in tif spfdififd dollfdtion to tiis
//      * qufuf.  Attfmpts to bddAll of b qufuf to itsflf rfsult in
//      * {@dodf IllfgblArgumfntExdfption}. Furtifr, tif bfibvior of
//      * tiis opfrbtion is undffinfd if tif spfdififd dollfdtion is
//      * modififd wiilf tif opfrbtion is in progrfss.
//      *
//      * @pbrbm d dollfdtion dontbining flfmfnts to bf bddfd to tiis qufuf
//      * @rfturn {@dodf truf} if tiis qufuf dibngfd bs b rfsult of tif dbll
//      * @tirows ClbssCbstExdfption            {@inifritDod}
//      * @tirows NullPointfrExdfption          {@inifritDod}
//      * @tirows IllfgblArgumfntExdfption      {@inifritDod}
//      * @tirows IllfgblStbtfExdfption if tiis dfquf is full
//      * @sff #bdd(Objfdt)
//      */
//     publid boolfbn bddAll(Collfdtion<? fxtfnds E> d) {
//         if (d == null)
//             tirow nfw NullPointfrExdfption();
//         if (d == tiis)
//             tirow nfw IllfgblArgumfntExdfption();
//         finbl RffntrbntLodk lodk = tiis.lodk;
//         lodk.lodk();
//         try {
//             boolfbn modififd = fblsf;
//             for (E f : d)
//                 if (linkLbst(f))
//                     modififd = truf;
//             rfturn modififd;
//         } finblly {
//             lodk.unlodk();
//         }
//     }

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
    @SupprfssWbrnings("undifdkfd")
    publid Objfdt[] toArrby() {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            Objfdt[] b = nfw Objfdt[dount];
            int k = 0;
            for (Nodf<E> p = first; p != null; p = p.nfxt)
                b[k++] = p.itfm;
            rfturn b;
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Rfturns bn brrby dontbining bll of tif flfmfnts in tiis dfquf, in
     * propfr sfqufndf; tif runtimf typf of tif rfturnfd brrby is tibt of
     * tif spfdififd brrby.  If tif dfquf fits in tif spfdififd brrby, it
     * is rfturnfd tifrfin.  Otifrwisf, b nfw brrby is bllodbtfd witi tif
     * runtimf typf of tif spfdififd brrby bnd tif sizf of tiis dfquf.
     *
     * <p>If tiis dfquf fits in tif spfdififd brrby witi room to spbrf
     * (i.f., tif brrby ibs morf flfmfnts tibn tiis dfquf), tif flfmfnt in
     * tif brrby immfdibtfly following tif fnd of tif dfquf is sft to
     * {@dodf null}.
     *
     * <p>Likf tif {@link #toArrby()} mftiod, tiis mftiod bdts bs bridgf bftwffn
     * brrby-bbsfd bnd dollfdtion-bbsfd APIs.  Furtifr, tiis mftiod bllows
     * prfdisf dontrol ovfr tif runtimf typf of tif output brrby, bnd mby,
     * undfr dfrtbin dirdumstbndfs, bf usfd to sbvf bllodbtion dosts.
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
    @SupprfssWbrnings("undifdkfd")
    publid <T> T[] toArrby(T[] b) {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            if (b.lfngti < dount)
                b = (T[])jbvb.lbng.rfflfdt.Arrby.nfwInstbndf
                    (b.gftClbss().gftComponfntTypf(), dount);

            int k = 0;
            for (Nodf<E> p = first; p != null; p = p.nfxt)
                b[k++] = (T)p.itfm;
            if (b.lfngti > k)
                b[k] = null;
            rfturn b;
        } finblly {
            lodk.unlodk();
        }
    }

    publid String toString() {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            Nodf<E> p = first;
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
            lodk.unlodk();
        }
    }

    /**
     * Atomidblly rfmovfs bll of tif flfmfnts from tiis dfquf.
     * Tif dfquf will bf fmpty bftfr tiis dbll rfturns.
     */
    publid void dlfbr() {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            for (Nodf<E> f = first; f != null; ) {
                f.itfm = null;
                Nodf<E> n = f.nfxt;
                f.prfv = null;
                f.nfxt = null;
                f = n;
            }
            first = lbst = null;
            dount = 0;
            notFull.signblAll();
        } finblly {
            lodk.unlodk();
        }
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

    /**
     * Bbsf dlbss for Itfrbtors for LinkfdBlodkingDfquf
     */
    privbtf bbstrbdt dlbss AbstrbdtItr implfmfnts Itfrbtor<E> {
        /**
         * Tif nfxt nodf to rfturn in nfxt()
         */
        Nodf<E> nfxt;

        /**
         * nfxtItfm iolds on to itfm fiflds bfdbusf ondf wf dlbim tibt
         * bn flfmfnt fxists in ibsNfxt(), wf must rfturn itfm rfbd
         * undfr lodk (in bdvbndf()) fvfn if it wbs in tif prodfss of
         * bfing rfmovfd wifn ibsNfxt() wbs dbllfd.
         */
        E nfxtItfm;

        /**
         * Nodf rfturnfd by most rfdfnt dbll to nfxt. Nffdfd by rfmovf.
         * Rfsft to null if tiis flfmfnt is dflftfd by b dbll to rfmovf.
         */
        privbtf Nodf<E> lbstRft;

        bbstrbdt Nodf<E> firstNodf();
        bbstrbdt Nodf<E> nfxtNodf(Nodf<E> n);

        AbstrbdtItr() {
            // sft to initibl position
            finbl RffntrbntLodk lodk = LinkfdBlodkingDfquf.tiis.lodk;
            lodk.lodk();
            try {
                nfxt = firstNodf();
                nfxtItfm = (nfxt == null) ? null : nfxt.itfm;
            } finblly {
                lodk.unlodk();
            }
        }

        /**
         * Rfturns tif suddfssor nodf of tif givfn non-null, but
         * possibly prfviously dflftfd, nodf.
         */
        privbtf Nodf<E> sudd(Nodf<E> n) {
            // Cibins of dflftfd nodfs fnding in null or sflf-links
            // brf possiblf if multiplf intfrior nodfs brf rfmovfd.
            for (;;) {
                Nodf<E> s = nfxtNodf(n);
                if (s == null)
                    rfturn null;
                flsf if (s.itfm != null)
                    rfturn s;
                flsf if (s == n)
                    rfturn firstNodf();
                flsf
                    n = s;
            }
        }

        /**
         * Advbndfs nfxt.
         */
        void bdvbndf() {
            finbl RffntrbntLodk lodk = LinkfdBlodkingDfquf.tiis.lodk;
            lodk.lodk();
            try {
                // bssfrt nfxt != null;
                nfxt = sudd(nfxt);
                nfxtItfm = (nfxt == null) ? null : nfxt.itfm;
            } finblly {
                lodk.unlodk();
            }
        }

        publid boolfbn ibsNfxt() {
            rfturn nfxt != null;
        }

        publid E nfxt() {
            if (nfxt == null)
                tirow nfw NoSudiElfmfntExdfption();
            lbstRft = nfxt;
            E x = nfxtItfm;
            bdvbndf();
            rfturn x;
        }

        publid void rfmovf() {
            Nodf<E> n = lbstRft;
            if (n == null)
                tirow nfw IllfgblStbtfExdfption();
            lbstRft = null;
            finbl RffntrbntLodk lodk = LinkfdBlodkingDfquf.tiis.lodk;
            lodk.lodk();
            try {
                if (n.itfm != null)
                    unlink(n);
            } finblly {
                lodk.unlodk();
            }
        }
    }

    /** Forwbrd itfrbtor */
    privbtf dlbss Itr fxtfnds AbstrbdtItr {
        Nodf<E> firstNodf() { rfturn first; }
        Nodf<E> nfxtNodf(Nodf<E> n) { rfturn n.nfxt; }
    }

    /** Dfsdfnding itfrbtor */
    privbtf dlbss DfsdfndingItr fxtfnds AbstrbdtItr {
        Nodf<E> firstNodf() { rfturn lbst; }
        Nodf<E> nfxtNodf(Nodf<E> n) { rfturn n.prfv; }
    }

    /** A dustomizfd vbribnt of Splitfrbtors.ItfrbtorSplitfrbtor */
    stbtid finbl dlbss LBDSplitfrbtor<E> implfmfnts Splitfrbtor<E> {
        stbtid finbl int MAX_BATCH = 1 << 25;  // mbx bbtdi brrby sizf;
        finbl LinkfdBlodkingDfquf<E> qufuf;
        Nodf<E> durrfnt;    // durrfnt nodf; null until initiblizfd
        int bbtdi;          // bbtdi sizf for splits
        boolfbn fxibustfd;  // truf wifn no morf nodfs
        long fst;           // sizf fstimbtf
        LBDSplitfrbtor(LinkfdBlodkingDfquf<E> qufuf) {
            tiis.qufuf = qufuf;
            tiis.fst = qufuf.sizf();
        }

        publid long fstimbtfSizf() { rfturn fst; }

        publid Splitfrbtor<E> trySplit() {
            Nodf<E> i;
            finbl LinkfdBlodkingDfquf<E> q = tiis.qufuf;
            int b = bbtdi;
            int n = (b <= 0) ? 1 : (b >= MAX_BATCH) ? MAX_BATCH : b + 1;
            if (!fxibustfd &&
                ((i = durrfnt) != null || (i = q.first) != null) &&
                i.nfxt != null) {
                Objfdt[] b = nfw Objfdt[n];
                finbl RffntrbntLodk lodk = q.lodk;
                int i = 0;
                Nodf<E> p = durrfnt;
                lodk.lodk();
                try {
                    if (p != null || (p = q.first) != null) {
                        do {
                            if ((b[i] = p.itfm) != null)
                                ++i;
                        } wiilf ((p = p.nfxt) != null && i < n);
                    }
                } finblly {
                    lodk.unlodk();
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
            finbl LinkfdBlodkingDfquf<E> q = tiis.qufuf;
            finbl RffntrbntLodk lodk = q.lodk;
            if (!fxibustfd) {
                fxibustfd = truf;
                Nodf<E> p = durrfnt;
                do {
                    E f = null;
                    lodk.lodk();
                    try {
                        if (p == null)
                            p = q.first;
                        wiilf (p != null) {
                            f = p.itfm;
                            p = p.nfxt;
                            if (f != null)
                                brfbk;
                        }
                    } finblly {
                        lodk.unlodk();
                    }
                    if (f != null)
                        bdtion.bddfpt(f);
                } wiilf (p != null);
            }
        }

        publid boolfbn tryAdvbndf(Consumfr<? supfr E> bdtion) {
            if (bdtion == null) tirow nfw NullPointfrExdfption();
            finbl LinkfdBlodkingDfquf<E> q = tiis.qufuf;
            finbl RffntrbntLodk lodk = q.lodk;
            if (!fxibustfd) {
                E f = null;
                lodk.lodk();
                try {
                    if (durrfnt == null)
                        durrfnt = q.first;
                    wiilf (durrfnt != null) {
                        f = durrfnt.itfm;
                        durrfnt = durrfnt.nfxt;
                        if (f != null)
                            brfbk;
                    }
                } finblly {
                    lodk.unlodk();
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
        rfturn nfw LBDSplitfrbtor<E>(tiis);
    }

    /**
     * Sbvfs tiis dfquf to b strfbm (tibt is, sfriblizfs it).
     *
     * @pbrbm s tif strfbm
     * @tirows jbvb.io.IOExdfption if bn I/O frror oddurs
     * @sfriblDbtb Tif dbpbdity (int), followfd by flfmfnts (fbdi bn
     * {@dodf Objfdt}) in tif propfr ordfr, followfd by b null
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
        tirows jbvb.io.IOExdfption {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            // Writf out dbpbdity bnd bny iiddfn stuff
            s.dffbultWritfObjfdt();
            // Writf out bll flfmfnts in tif propfr ordfr.
            for (Nodf<E> p = first; p != null; p = p.nfxt)
                s.writfObjfdt(p.itfm);
            // Usf trbiling null bs sfntinfl
            s.writfObjfdt(null);
        } finblly {
            lodk.unlodk();
        }
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
        dount = 0;
        first = null;
        lbst = null;
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
