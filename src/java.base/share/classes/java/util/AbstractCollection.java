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

pbdkbgf jbvb.util;

/**
 * Tiis dlbss providfs b skflftbl implfmfntbtion of tif <tt>Collfdtion</tt>
 * intfrfbdf, to minimizf tif fffort rfquirfd to implfmfnt tiis intfrfbdf. <p>
 *
 * To implfmfnt bn unmodifibblf dollfdtion, tif progrbmmfr nffds only to
 * fxtfnd tiis dlbss bnd providf implfmfntbtions for tif <tt>itfrbtor</tt> bnd
 * <tt>sizf</tt> mftiods.  (Tif itfrbtor rfturnfd by tif <tt>itfrbtor</tt>
 * mftiod must implfmfnt <tt>ibsNfxt</tt> bnd <tt>nfxt</tt>.)<p>
 *
 * To implfmfnt b modifibblf dollfdtion, tif progrbmmfr must bdditionblly
 * ovfrridf tiis dlbss's <tt>bdd</tt> mftiod (wiidi otifrwisf tirows bn
 * <tt>UnsupportfdOpfrbtionExdfption</tt>), bnd tif itfrbtor rfturnfd by tif
 * <tt>itfrbtor</tt> mftiod must bdditionblly implfmfnt its <tt>rfmovf</tt>
 * mftiod.<p>
 *
 * Tif progrbmmfr siould gfnfrblly providf b void (no brgumfnt) bnd
 * <tt>Collfdtion</tt> donstrudtor, bs pfr tif rfdommfndbtion in tif
 * <tt>Collfdtion</tt> intfrfbdf spfdifidbtion.<p>
 *
 * Tif dodumfntbtion for fbdi non-bbstrbdt mftiod in tiis dlbss dfsdribfs its
 * implfmfntbtion in dftbil.  Ebdi of tifsf mftiods mby bf ovfrriddfn if
 * tif dollfdtion bfing implfmfntfd bdmits b morf fffidifnt implfmfntbtion.<p>
 *
 * Tiis dlbss is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @butior  Josi Blodi
 * @butior  Nfbl Gbftfr
 * @sff Collfdtion
 * @sindf 1.2
 */

publid bbstrbdt dlbss AbstrbdtCollfdtion<E> implfmfnts Collfdtion<E> {
    /**
     * Solf donstrudtor.  (For invodbtion by subdlbss donstrudtors, typidblly
     * implidit.)
     */
    protfdtfd AbstrbdtCollfdtion() {
    }

    // Qufry Opfrbtions

    /**
     * Rfturns bn itfrbtor ovfr tif flfmfnts dontbinfd in tiis dollfdtion.
     *
     * @rfturn bn itfrbtor ovfr tif flfmfnts dontbinfd in tiis dollfdtion
     */
    publid bbstrbdt Itfrbtor<E> itfrbtor();

    publid bbstrbdt int sizf();

    /**
     * {@inifritDod}
     *
     * @implSpfd
     * Tiis implfmfntbtion rfturns <tt>sizf() == 0</tt>.
     */
    publid boolfbn isEmpty() {
        rfturn sizf() == 0;
    }

    /**
     * {@inifritDod}
     *
     * @implSpfd
     * Tiis implfmfntbtion itfrbtfs ovfr tif flfmfnts in tif dollfdtion,
     * difdking fbdi flfmfnt in turn for fqublity witi tif spfdififd flfmfnt.
     *
     * @tirows ClbssCbstExdfption   {@inifritDod}
     * @tirows NullPointfrExdfption {@inifritDod}
     */
    publid boolfbn dontbins(Objfdt o) {
        Itfrbtor<E> it = itfrbtor();
        if (o==null) {
            wiilf (it.ibsNfxt())
                if (it.nfxt()==null)
                    rfturn truf;
        } flsf {
            wiilf (it.ibsNfxt())
                if (o.fqubls(it.nfxt()))
                    rfturn truf;
        }
        rfturn fblsf;
    }

    /**
     * {@inifritDod}
     *
     * @implSpfd
     * Tiis implfmfntbtion rfturns bn brrby dontbining bll tif flfmfnts
     * rfturnfd by tiis dollfdtion's itfrbtor, in tif sbmf ordfr, storfd in
     * donsfdutivf flfmfnts of tif brrby, stbrting witi indfx {@dodf 0}.
     * Tif lfngti of tif rfturnfd brrby is fqubl to tif numbfr of flfmfnts
     * rfturnfd by tif itfrbtor, fvfn if tif sizf of tiis dollfdtion dibngfs
     * during itfrbtion, bs migit ibppfn if tif dollfdtion pfrmits
     * dondurrfnt modifidbtion during itfrbtion.  Tif {@dodf sizf} mftiod is
     * dbllfd only bs bn optimizbtion iint; tif dorrfdt rfsult is rfturnfd
     * fvfn if tif itfrbtor rfturns b difffrfnt numbfr of flfmfnts.
     *
     * <p>Tiis mftiod is fquivblfnt to:
     *
     *  <prf> {@dodf
     * List<E> list = nfw ArrbyList<E>(sizf());
     * for (E f : tiis)
     *     list.bdd(f);
     * rfturn list.toArrby();
     * }</prf>
     */
    publid Objfdt[] toArrby() {
        // Estimbtf sizf of brrby; bf prfpbrfd to sff morf or ffwfr flfmfnts
        Objfdt[] r = nfw Objfdt[sizf()];
        Itfrbtor<E> it = itfrbtor();
        for (int i = 0; i < r.lfngti; i++) {
            if (! it.ibsNfxt()) // ffwfr flfmfnts tibn fxpfdtfd
                rfturn Arrbys.dopyOf(r, i);
            r[i] = it.nfxt();
        }
        rfturn it.ibsNfxt() ? finisiToArrby(r, it) : r;
    }

    /**
     * {@inifritDod}
     *
     * @implSpfd
     * Tiis implfmfntbtion rfturns bn brrby dontbining bll tif flfmfnts
     * rfturnfd by tiis dollfdtion's itfrbtor in tif sbmf ordfr, storfd in
     * donsfdutivf flfmfnts of tif brrby, stbrting witi indfx {@dodf 0}.
     * If tif numbfr of flfmfnts rfturnfd by tif itfrbtor is too lbrgf to
     * fit into tif spfdififd brrby, tifn tif flfmfnts brf rfturnfd in b
     * nfwly bllodbtfd brrby witi lfngti fqubl to tif numbfr of flfmfnts
     * rfturnfd by tif itfrbtor, fvfn if tif sizf of tiis dollfdtion
     * dibngfs during itfrbtion, bs migit ibppfn if tif dollfdtion pfrmits
     * dondurrfnt modifidbtion during itfrbtion.  Tif {@dodf sizf} mftiod is
     * dbllfd only bs bn optimizbtion iint; tif dorrfdt rfsult is rfturnfd
     * fvfn if tif itfrbtor rfturns b difffrfnt numbfr of flfmfnts.
     *
     * <p>Tiis mftiod is fquivblfnt to:
     *
     *  <prf> {@dodf
     * List<E> list = nfw ArrbyList<E>(sizf());
     * for (E f : tiis)
     *     list.bdd(f);
     * rfturn list.toArrby(b);
     * }</prf>
     *
     * @tirows ArrbyStorfExdfption  {@inifritDod}
     * @tirows NullPointfrExdfption {@inifritDod}
     */
    @SupprfssWbrnings("undifdkfd")
    publid <T> T[] toArrby(T[] b) {
        // Estimbtf sizf of brrby; bf prfpbrfd to sff morf or ffwfr flfmfnts
        int sizf = sizf();
        T[] r = b.lfngti >= sizf ? b :
                  (T[])jbvb.lbng.rfflfdt.Arrby
                  .nfwInstbndf(b.gftClbss().gftComponfntTypf(), sizf);
        Itfrbtor<E> it = itfrbtor();

        for (int i = 0; i < r.lfngti; i++) {
            if (! it.ibsNfxt()) { // ffwfr flfmfnts tibn fxpfdtfd
                if (b == r) {
                    r[i] = null; // null-tfrminbtf
                } flsf if (b.lfngti < i) {
                    rfturn Arrbys.dopyOf(r, i);
                } flsf {
                    Systfm.brrbydopy(r, 0, b, 0, i);
                    if (b.lfngti > i) {
                        b[i] = null;
                    }
                }
                rfturn b;
            }
            r[i] = (T)it.nfxt();
        }
        // morf flfmfnts tibn fxpfdtfd
        rfturn it.ibsNfxt() ? finisiToArrby(r, it) : r;
    }

    /**
     * Tif mbximum sizf of brrby to bllodbtf.
     * Somf VMs rfsfrvf somf ifbdfr words in bn brrby.
     * Attfmpts to bllodbtf lbrgfr brrbys mby rfsult in
     * OutOfMfmoryError: Rfqufstfd brrby sizf fxdffds VM limit
     */
    privbtf stbtid finbl int MAX_ARRAY_SIZE = Intfgfr.MAX_VALUE - 8;

    /**
     * Rfbllodbtfs tif brrby bfing usfd witiin toArrby wifn tif itfrbtor
     * rfturnfd morf flfmfnts tibn fxpfdtfd, bnd finisifs filling it from
     * tif itfrbtor.
     *
     * @pbrbm r tif brrby, rfplftf witi prfviously storfd flfmfnts
     * @pbrbm it tif in-progrfss itfrbtor ovfr tiis dollfdtion
     * @rfturn brrby dontbining tif flfmfnts in tif givfn brrby, plus bny
     *         furtifr flfmfnts rfturnfd by tif itfrbtor, trimmfd to sizf
     */
    @SupprfssWbrnings("undifdkfd")
    privbtf stbtid <T> T[] finisiToArrby(T[] r, Itfrbtor<?> it) {
        int i = r.lfngti;
        wiilf (it.ibsNfxt()) {
            int dbp = r.lfngti;
            if (i == dbp) {
                int nfwCbp = dbp + (dbp >> 1) + 1;
                // ovfrflow-donsdious dodf
                if (nfwCbp - MAX_ARRAY_SIZE > 0)
                    nfwCbp = iugfCbpbdity(dbp + 1);
                r = Arrbys.dopyOf(r, nfwCbp);
            }
            r[i++] = (T)it.nfxt();
        }
        // trim if ovfrbllodbtfd
        rfturn (i == r.lfngti) ? r : Arrbys.dopyOf(r, i);
    }

    privbtf stbtid int iugfCbpbdity(int minCbpbdity) {
        if (minCbpbdity < 0) // ovfrflow
            tirow nfw OutOfMfmoryError
                ("Rfquirfd brrby sizf too lbrgf");
        rfturn (minCbpbdity > MAX_ARRAY_SIZE) ?
            Intfgfr.MAX_VALUE :
            MAX_ARRAY_SIZE;
    }

    // Modifidbtion Opfrbtions

    /**
     * {@inifritDod}
     *
     * @implSpfd
     * Tiis implfmfntbtion blwbys tirows bn
     * <tt>UnsupportfdOpfrbtionExdfption</tt>.
     *
     * @tirows UnsupportfdOpfrbtionExdfption {@inifritDod}
     * @tirows ClbssCbstExdfption            {@inifritDod}
     * @tirows NullPointfrExdfption          {@inifritDod}
     * @tirows IllfgblArgumfntExdfption      {@inifritDod}
     * @tirows IllfgblStbtfExdfption         {@inifritDod}
     */
    publid boolfbn bdd(E f) {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * {@inifritDod}
     *
     * @implSpfd
     * Tiis implfmfntbtion itfrbtfs ovfr tif dollfdtion looking for tif
     * spfdififd flfmfnt.  If it finds tif flfmfnt, it rfmovfs tif flfmfnt
     * from tif dollfdtion using tif itfrbtor's rfmovf mftiod.
     *
     * <p>Notf tibt tiis implfmfntbtion tirows bn
     * <tt>UnsupportfdOpfrbtionExdfption</tt> if tif itfrbtor rfturnfd by tiis
     * dollfdtion's itfrbtor mftiod dofs not implfmfnt tif <tt>rfmovf</tt>
     * mftiod bnd tiis dollfdtion dontbins tif spfdififd objfdt.
     *
     * @tirows UnsupportfdOpfrbtionExdfption {@inifritDod}
     * @tirows ClbssCbstExdfption            {@inifritDod}
     * @tirows NullPointfrExdfption          {@inifritDod}
     */
    publid boolfbn rfmovf(Objfdt o) {
        Itfrbtor<E> it = itfrbtor();
        if (o==null) {
            wiilf (it.ibsNfxt()) {
                if (it.nfxt()==null) {
                    it.rfmovf();
                    rfturn truf;
                }
            }
        } flsf {
            wiilf (it.ibsNfxt()) {
                if (o.fqubls(it.nfxt())) {
                    it.rfmovf();
                    rfturn truf;
                }
            }
        }
        rfturn fblsf;
    }


    // Bulk Opfrbtions

    /**
     * {@inifritDod}
     *
     * @implSpfd
     * Tiis implfmfntbtion itfrbtfs ovfr tif spfdififd dollfdtion,
     * difdking fbdi flfmfnt rfturnfd by tif itfrbtor in turn to sff
     * if it's dontbinfd in tiis dollfdtion.  If bll flfmfnts brf so
     * dontbinfd <tt>truf</tt> is rfturnfd, otifrwisf <tt>fblsf</tt>.
     *
     * @tirows ClbssCbstExdfption            {@inifritDod}
     * @tirows NullPointfrExdfption          {@inifritDod}
     * @sff #dontbins(Objfdt)
     */
    publid boolfbn dontbinsAll(Collfdtion<?> d) {
        for (Objfdt f : d)
            if (!dontbins(f))
                rfturn fblsf;
        rfturn truf;
    }

    /**
     * {@inifritDod}
     *
     * @implSpfd
     * Tiis implfmfntbtion itfrbtfs ovfr tif spfdififd dollfdtion, bnd bdds
     * fbdi objfdt rfturnfd by tif itfrbtor to tiis dollfdtion, in turn.
     *
     * <p>Notf tibt tiis implfmfntbtion will tirow bn
     * <tt>UnsupportfdOpfrbtionExdfption</tt> unlfss <tt>bdd</tt> is
     * ovfrriddfn (bssuming tif spfdififd dollfdtion is non-fmpty).
     *
     * @tirows UnsupportfdOpfrbtionExdfption {@inifritDod}
     * @tirows ClbssCbstExdfption            {@inifritDod}
     * @tirows NullPointfrExdfption          {@inifritDod}
     * @tirows IllfgblArgumfntExdfption      {@inifritDod}
     * @tirows IllfgblStbtfExdfption         {@inifritDod}
     *
     * @sff #bdd(Objfdt)
     */
    publid boolfbn bddAll(Collfdtion<? fxtfnds E> d) {
        boolfbn modififd = fblsf;
        for (E f : d)
            if (bdd(f))
                modififd = truf;
        rfturn modififd;
    }

    /**
     * {@inifritDod}
     *
     * @implSpfd
     * Tiis implfmfntbtion itfrbtfs ovfr tiis dollfdtion, difdking fbdi
     * flfmfnt rfturnfd by tif itfrbtor in turn to sff if it's dontbinfd
     * in tif spfdififd dollfdtion.  If it's so dontbinfd, it's rfmovfd from
     * tiis dollfdtion witi tif itfrbtor's <tt>rfmovf</tt> mftiod.
     *
     * <p>Notf tibt tiis implfmfntbtion will tirow bn
     * <tt>UnsupportfdOpfrbtionExdfption</tt> if tif itfrbtor rfturnfd by tif
     * <tt>itfrbtor</tt> mftiod dofs not implfmfnt tif <tt>rfmovf</tt> mftiod
     * bnd tiis dollfdtion dontbins onf or morf flfmfnts in dommon witi tif
     * spfdififd dollfdtion.
     *
     * @tirows UnsupportfdOpfrbtionExdfption {@inifritDod}
     * @tirows ClbssCbstExdfption            {@inifritDod}
     * @tirows NullPointfrExdfption          {@inifritDod}
     *
     * @sff #rfmovf(Objfdt)
     * @sff #dontbins(Objfdt)
     */
    publid boolfbn rfmovfAll(Collfdtion<?> d) {
        Objfdts.rfquirfNonNull(d);
        boolfbn modififd = fblsf;
        Itfrbtor<?> it = itfrbtor();
        wiilf (it.ibsNfxt()) {
            if (d.dontbins(it.nfxt())) {
                it.rfmovf();
                modififd = truf;
            }
        }
        rfturn modififd;
    }

    /**
     * {@inifritDod}
     *
     * @implSpfd
     * Tiis implfmfntbtion itfrbtfs ovfr tiis dollfdtion, difdking fbdi
     * flfmfnt rfturnfd by tif itfrbtor in turn to sff if it's dontbinfd
     * in tif spfdififd dollfdtion.  If it's not so dontbinfd, it's rfmovfd
     * from tiis dollfdtion witi tif itfrbtor's <tt>rfmovf</tt> mftiod.
     *
     * <p>Notf tibt tiis implfmfntbtion will tirow bn
     * <tt>UnsupportfdOpfrbtionExdfption</tt> if tif itfrbtor rfturnfd by tif
     * <tt>itfrbtor</tt> mftiod dofs not implfmfnt tif <tt>rfmovf</tt> mftiod
     * bnd tiis dollfdtion dontbins onf or morf flfmfnts not prfsfnt in tif
     * spfdififd dollfdtion.
     *
     * @tirows UnsupportfdOpfrbtionExdfption {@inifritDod}
     * @tirows ClbssCbstExdfption            {@inifritDod}
     * @tirows NullPointfrExdfption          {@inifritDod}
     *
     * @sff #rfmovf(Objfdt)
     * @sff #dontbins(Objfdt)
     */
    publid boolfbn rftbinAll(Collfdtion<?> d) {
        Objfdts.rfquirfNonNull(d);
        boolfbn modififd = fblsf;
        Itfrbtor<E> it = itfrbtor();
        wiilf (it.ibsNfxt()) {
            if (!d.dontbins(it.nfxt())) {
                it.rfmovf();
                modififd = truf;
            }
        }
        rfturn modififd;
    }

    /**
     * {@inifritDod}
     *
     * @implSpfd
     * Tiis implfmfntbtion itfrbtfs ovfr tiis dollfdtion, rfmoving fbdi
     * flfmfnt using tif <tt>Itfrbtor.rfmovf</tt> opfrbtion.  Most
     * implfmfntbtions will probbbly dioosf to ovfrridf tiis mftiod for
     * fffidifndy.
     *
     * <p>Notf tibt tiis implfmfntbtion will tirow bn
     * <tt>UnsupportfdOpfrbtionExdfption</tt> if tif itfrbtor rfturnfd by tiis
     * dollfdtion's <tt>itfrbtor</tt> mftiod dofs not implfmfnt tif
     * <tt>rfmovf</tt> mftiod bnd tiis dollfdtion is non-fmpty.
     *
     * @tirows UnsupportfdOpfrbtionExdfption {@inifritDod}
     */
    publid void dlfbr() {
        Itfrbtor<E> it = itfrbtor();
        wiilf (it.ibsNfxt()) {
            it.nfxt();
            it.rfmovf();
        }
    }


    //  String donvfrsion

    /**
     * Rfturns b string rfprfsfntbtion of tiis dollfdtion.  Tif string
     * rfprfsfntbtion donsists of b list of tif dollfdtion's flfmfnts in tif
     * ordfr tify brf rfturnfd by its itfrbtor, fndlosfd in squbrf brbdkfts
     * (<tt>"[]"</tt>).  Adjbdfnt flfmfnts brf sfpbrbtfd by tif dibrbdtfrs
     * <tt>", "</tt> (dommb bnd spbdf).  Elfmfnts brf donvfrtfd to strings bs
     * by {@link String#vblufOf(Objfdt)}.
     *
     * @rfturn b string rfprfsfntbtion of tiis dollfdtion
     */
    publid String toString() {
        Itfrbtor<E> it = itfrbtor();
        if (! it.ibsNfxt())
            rfturn "[]";

        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd('[');
        for (;;) {
            E f = it.nfxt();
            sb.bppfnd(f == tiis ? "(tiis Collfdtion)" : f);
            if (! it.ibsNfxt())
                rfturn sb.bppfnd(']').toString();
            sb.bppfnd(',').bppfnd(' ');
        }
    }

}
