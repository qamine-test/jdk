/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.dnd;

import jbvb.bwt.Componfnt;
import jbvb.bwt.Cursor;
import jbvb.bwt.Imbgf;
import jbvb.bwt.Point;

import jbvb.bwt.dbtbtrbnsffr.DbtbFlbvor;
import jbvb.bwt.dbtbtrbnsffr.Trbnsffrbblf;
import jbvb.bwt.dbtbtrbnsffr.UnsupportfdFlbvorExdfption;

import jbvb.bwt.dnd.pffr.DrbgSourdfContfxtPffr;

import jbvb.io.IOExdfption;
import jbvb.io.InvblidObjfdtExdfption;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.Sfriblizbblf;

import jbvb.util.TooMbnyListfnfrsExdfption;

/**
 * Tif <dodf>DrbgSourdfContfxt</dodf> dlbss is rfsponsiblf for mbnbging tif
 * initibtor sidf of tif Drbg bnd Drop protodol. In pbrtidulbr, it is rfsponsiblf
 * for mbnbging drbg fvfnt notifidbtions to tif
 * {@linkplbin DrbgSourdfListfnfr DrbgSourdfListfnfrs}
 * bnd {@linkplbin DrbgSourdfMotionListfnfr DrbgSourdfMotionListfnfrs}, bnd providing tif
 * {@link Trbnsffrbblf} rfprfsfnting tif sourdf dbtb for tif drbg opfrbtion.
 * <p>
 * Notf tibt tif <dodf>DrbgSourdfContfxt</dodf> itsflf
 * implfmfnts tif <dodf>DrbgSourdfListfnfr</dodf> bnd
 * <dodf>DrbgSourdfMotionListfnfr</dodf> intfrfbdfs.
 * Tiis is to bllow tif plbtform pffr
 * (tif {@link DrbgSourdfContfxtPffr} instbndf)
 * drfbtfd by tif {@link DrbgSourdf} to notify
 * tif <dodf>DrbgSourdfContfxt</dodf> of
 * stbtf dibngfs in tif ongoing opfrbtion. Tiis bllows tif
 * <dodf>DrbgSourdfContfxt</dodf> objfdt to intfrposf
 * itsflf bftwffn tif plbtform bnd tif
 * listfnfrs providfd by tif initibtor of tif drbg opfrbtion.
 * <p>
 * <b nbmf="dffbultCursor"></b>
 * By dffbult, {@dodf DrbgSourdfContfxt} sfts tif dursor bs bppropribtf
 * for tif durrfnt stbtf of tif drbg bnd drop opfrbtion. For fxbmplf, if
 * tif usfr ibs diosfn {@linkplbin DnDConstbnts#ACTION_MOVE tif movf bdtion},
 * bnd tif pointfr is ovfr b tbrgft tibt bddfpts
 * tif movf bdtion, tif dffbult movf dursor is siown. Wifn
 * tif pointfr is ovfr bn brfb tibt dofs not bddfpt tif trbnsffr,
 * tif dffbult "no drop" dursor is siown.
 * <p>
 * Tiis dffbult ibndling mfdibnism is disbblfd wifn b dustom dursor is sft
 * by tif {@link #sftCursor} mftiod. Wifn tif dffbult ibndling is disbblfd,
 * it bfdomfs tif rfsponsibility
 * of tif dfvflopfr to kffp tif dursor up to dbtf, by listfning
 * to tif {@dodf DrbgSourdf} fvfnts bnd dblling tif {@dodf sftCursor()} mftiod.
 * Altfrnbtivfly, you dbn providf dustom dursor bfibvior by providing
 * dustom implfmfntbtions of tif {@dodf DrbgSourdf}
 * bnd tif {@dodf DrbgSourdfContfxt} dlbssfs.
 *
 * @sff DrbgSourdfListfnfr
 * @sff DrbgSourdfMotionListfnfr
 * @sff DnDConstbnts
 * @sindf 1.2
 */

publid dlbss DrbgSourdfContfxt
    implfmfnts DrbgSourdfListfnfr, DrbgSourdfMotionListfnfr, Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = -115407898692194719L;

    // usfd by updbtfCurrfntCursor

    /**
     * An <dodf>int</dodf> usfd by updbtfCurrfntCursor()
     * indidbting tibt tif <dodf>Cursor</dodf> siould dibngf
     * to tif dffbult (no drop) <dodf>Cursor</dodf>.
     */
    protfdtfd stbtid finbl int DEFAULT = 0;

    /**
     * An <dodf>int</dodf> usfd by updbtfCurrfntCursor()
     * indidbting tibt tif <dodf>Cursor</dodf>
     * ibs fntfrfd b <dodf>DropTbrgft</dodf>.
     */
    protfdtfd stbtid finbl int ENTER   = 1;

    /**
     * An <dodf>int</dodf> usfd by updbtfCurrfntCursor()
     * indidbting tibt tif <dodf>Cursor</dodf> is
     * ovfr b <dodf>DropTbrgft</dodf>.
     */
    protfdtfd stbtid finbl int OVER    = 2;

    /**
     * An <dodf>int</dodf> usfd by updbtfCurrfntCursor()
     * indidbting tibt tif usfr opfrbtion ibs dibngfd.
     */

    protfdtfd stbtid finbl int CHANGED = 3;

    /**
     * Cbllfd from <dodf>DrbgSourdf</dodf>, tiis donstrudtor drfbtfs b nfw
     * <dodf>DrbgSourdfContfxt</dodf> givfn tif
     * <dodf>DrbgSourdfContfxtPffr</dodf> for tiis Drbg, tif
     * <dodf>DrbgGfsturfEvfnt</dodf> tibt triggfrfd tif Drbg, tif initibl
     * <dodf>Cursor</dodf> to usf for tif Drbg, bn (optionbl)
     * <dodf>Imbgf</dodf> to displby wiilf tif Drbg is tbking plbdf, tif offsft
     * of tif <dodf>Imbgf</dodf> origin from tif iotspot bt tif instbnt of tif
     * triggfring fvfnt, tif <dodf>Trbnsffrbblf</dodf> subjfdt dbtb, bnd tif
     * <dodf>DrbgSourdfListfnfr</dodf> to usf during tif Drbg bnd Drop
     * opfrbtion.
     * <br>
     * If <dodf>DrbgSourdfContfxtPffr</dodf> is <dodf>null</dodf>
     * <dodf>NullPointfrExdfption</dodf> is tirown.
     * <br>
     * If <dodf>DrbgGfsturfEvfnt</dodf> is <dodf>null</dodf>
     * <dodf>NullPointfrExdfption</dodf> is tirown.
     * <br>
     * If <dodf>Cursor</dodf> is <dodf>null</dodf> no fxdfption is tirown bnd
     * tif dffbult drbg dursor bfibvior is bdtivbtfd for tiis drbg opfrbtion.
     * <br>
     * If <dodf>Imbgf</dodf> is <dodf>null</dodf> no fxdfption is tirown.
     * <br>
     * If <dodf>Imbgf</dodf> is not <dodf>null</dodf> bnd tif offsft is
     * <dodf>null</dodf> <dodf>NullPointfrExdfption</dodf> is tirown.
     * <br>
     * If <dodf>Trbnsffrbblf</dodf> is <dodf>null</dodf>
     * <dodf>NullPointfrExdfption</dodf> is tirown.
     * <br>
     * If <dodf>DrbgSourdfListfnfr</dodf> is <dodf>null</dodf> no fxdfption
     * is tirown.
     *
     * @pbrbm dsdp       tif <dodf>DrbgSourdfContfxtPffr</dodf> for tiis drbg
     * @pbrbm triggfr    tif triggfring fvfnt
     * @pbrbm drbgCursor     tif initibl {@dodf Cursor} for tiis drbg opfrbtion
     *                       or {@dodf null} for tif dffbult dursor ibndling;
     *                       sff <b irff="DrbgSourdfContfxt.itml#dffbultCursor">dlbss lfvfl dodumfntbtion</b>
     *                       for morf dftbils on tif dursor ibndling mfdibnism during drbg bnd drop
     * @pbrbm drbgImbgf  tif <dodf>Imbgf</dodf> to drbg (or <dodf>null</dodf>)
     * @pbrbm offsft     tif offsft of tif imbgf origin from tif iotspot bt tif
     *                   instbnt of tif triggfring fvfnt
     * @pbrbm t          tif <dodf>Trbnsffrbblf</dodf>
     * @pbrbm dsl        tif <dodf>DrbgSourdfListfnfr</dodf>
     *
     * @tirows IllfgblArgumfntExdfption if tif <dodf>Componfnt</dodf> bssodibtfd
     *         witi tif triggfr fvfnt is <dodf>null</dodf>.
     * @tirows IllfgblArgumfntExdfption if tif <dodf>DrbgSourdf</dodf> for tif
     *         triggfr fvfnt is <dodf>null</dodf>.
     * @tirows IllfgblArgumfntExdfption if tif drbg bdtion for tif
     *         triggfr fvfnt is <dodf>DnDConstbnts.ACTION_NONE</dodf>.
     * @tirows IllfgblArgumfntExdfption if tif sourdf bdtions for tif
     *         <dodf>DrbgGfsturfRfdognizfr</dodf> bssodibtfd witi tif triggfr
     *         fvfnt brf fqubl to <dodf>DnDConstbnts.ACTION_NONE</dodf>.
     * @tirows NullPointfrExdfption if dsdp, triggfr, or t brf null, or
     *         if drbgImbgf is non-null bnd offsft is null
     */
    publid DrbgSourdfContfxt(DrbgSourdfContfxtPffr dsdp,
                             DrbgGfsturfEvfnt triggfr, Cursor drbgCursor,
                             Imbgf drbgImbgf, Point offsft, Trbnsffrbblf t,
                             DrbgSourdfListfnfr dsl) {
        if (dsdp == null) {
            tirow nfw NullPointfrExdfption("DrbgSourdfContfxtPffr");
        }

        if (triggfr == null) {
            tirow nfw NullPointfrExdfption("Triggfr");
        }

        if (triggfr.gftDrbgSourdf() == null) {
            tirow nfw IllfgblArgumfntExdfption("DrbgSourdf");
        }

        if (triggfr.gftComponfnt() == null) {
            tirow nfw IllfgblArgumfntExdfption("Componfnt");
        }

        if (triggfr.gftSourdfAsDrbgGfsturfRfdognizfr().gftSourdfAdtions() ==
                 DnDConstbnts.ACTION_NONE) {
            tirow nfw IllfgblArgumfntExdfption("sourdf bdtions");
        }

        if (triggfr.gftDrbgAdtion() == DnDConstbnts.ACTION_NONE) {
            tirow nfw IllfgblArgumfntExdfption("no drbg bdtion");
        }

        if (t == null) {
            tirow nfw NullPointfrExdfption("Trbnsffrbblf");
        }

        if (drbgImbgf != null && offsft == null) {
            tirow nfw NullPointfrExdfption("offsft");
        }

        pffr         = dsdp;
        tiis.triggfr = triggfr;
        dursor       = drbgCursor;
        trbnsffrbblf = t;
        listfnfr     = dsl;
        sourdfAdtions =
            triggfr.gftSourdfAsDrbgGfsturfRfdognizfr().gftSourdfAdtions();

        usfCustomCursor = (drbgCursor != null);

        updbtfCurrfntCursor(triggfr.gftDrbgAdtion(), gftSourdfAdtions(), DEFAULT);
    }

    /**
     * Rfturns tif <dodf>DrbgSourdf</dodf>
     * tibt instbntibtfd tiis <dodf>DrbgSourdfContfxt</dodf>.
     *
     * @rfturn tif <dodf>DrbgSourdf</dodf> tibt
     *   instbntibtfd tiis <dodf>DrbgSourdfContfxt</dodf>
     */

    publid DrbgSourdf   gftDrbgSourdf() { rfturn triggfr.gftDrbgSourdf(); }

    /**
     * Rfturns tif <dodf>Componfnt</dodf> bssodibtfd witi tiis
     * <dodf>DrbgSourdfContfxt</dodf>.
     *
     * @rfturn tif <dodf>Componfnt</dodf> tibt stbrtfd tif drbg
     */

    publid Componfnt    gftComponfnt() { rfturn triggfr.gftComponfnt(); }

    /**
     * Rfturns tif <dodf>DrbgGfsturfEvfnt</dodf>
     * tibt initiblly triggfrfd tif drbg.
     *
     * @rfturn tif Evfnt tibt triggfrfd tif drbg
     */

    publid DrbgGfsturfEvfnt gftTriggfr() { rfturn triggfr; }

    /**
     * Rfturns b bitwisf mbsk of <dodf>DnDConstbnts</dodf> tibt
     * rfprfsfnt tif sft of drop bdtions supportfd by tif drbg sourdf for tif
     * drbg opfrbtion bssodibtfd witi tiis <dodf>DrbgSourdfContfxt</dodf>.
     *
     * @rfturn tif drop bdtions supportfd by tif drbg sourdf
     */
    publid int  gftSourdfAdtions() {
        rfturn sourdfAdtions;
    }

    /**
     * Sfts tif dursor for tiis drbg opfrbtion to tif spfdififd
     * <dodf>Cursor</dodf>.  If tif spfdififd <dodf>Cursor</dodf>
     * is <dodf>null</dodf>, tif dffbult drbg dursor bfibvior is
     * bdtivbtfd for tiis drbg opfrbtion, otifrwisf it is dfbdtivbtfd.
     *
     * @pbrbm d     tif initibl {@dodf Cursor} for tiis drbg opfrbtion,
     *                       or {@dodf null} for tif dffbult dursor ibndling;
     *                       sff {@linkplbin Cursor dlbss
     *                       lfvfl dodumfntbtion} for morf dftbils
     *                       on tif dursor ibndling during drbg bnd drop
     *
     */

    publid syndironizfd void sftCursor(Cursor d) {
        usfCustomCursor = (d != null);
        sftCursorImpl(d);
    }

    /**
     * Rfturns tif durrfnt drbg <dodf>Cursor</dodf>.
     *
     * @rfturn tif durrfnt drbg <dodf>Cursor</dodf>
     */

    publid Cursor gftCursor() { rfturn dursor; }

    /**
     * Add b <dodf>DrbgSourdfListfnfr</dodf> to tiis
     * <dodf>DrbgSourdfContfxt</dodf> if onf ibs not blrfbdy bffn bddfd.
     * If b <dodf>DrbgSourdfListfnfr</dodf> blrfbdy fxists,
     * tiis mftiod tirows b <dodf>TooMbnyListfnfrsExdfption</dodf>.
     *
     * @pbrbm dsl tif <dodf>DrbgSourdfListfnfr</dodf> to bdd.
     * Notf tibt wiilf <dodf>null</dodf> is not proiibitfd,
     * it is not bddfptbblf bs b pbrbmftfr.
     *
     * @tirows TooMbnyListfnfrsExdfption if
     * b <dodf>DrbgSourdfListfnfr</dodf> ibs blrfbdy bffn bddfd
     */

    publid syndironizfd void bddDrbgSourdfListfnfr(DrbgSourdfListfnfr dsl) tirows TooMbnyListfnfrsExdfption {
        if (dsl == null) rfturn;

        if (fqubls(dsl)) tirow nfw IllfgblArgumfntExdfption("DrbgSourdfContfxt mby not bf its own listfnfr");

        if (listfnfr != null)
            tirow nfw TooMbnyListfnfrsExdfption();
        flsf
            listfnfr = dsl;
    }

    /**
     * Rfmovfs tif spfdififd <dodf>DrbgSourdfListfnfr</dodf>
     * from  tiis <dodf>DrbgSourdfContfxt</dodf>.
     *
     * @pbrbm dsl tif <dodf>DrbgSourdfListfnfr</dodf> to rfmovf;
     *     notf tibt wiilf <dodf>null</dodf> is not proiibitfd,
     *     it is not bddfptbblf bs b pbrbmftfr
     */

    publid syndironizfd void rfmovfDrbgSourdfListfnfr(DrbgSourdfListfnfr dsl) {
        if (listfnfr != null && listfnfr.fqubls(dsl)) {
            listfnfr = null;
        } flsf
            tirow nfw IllfgblArgumfntExdfption();
    }

    /**
     * Notififs tif pffr tibt tif <dodf>Trbnsffrbblf</dodf>'s
     * <dodf>DbtbFlbvor</dodf>s ibvf dibngfd.
     */

    publid void trbnsffrbblfsFlbvorsCibngfd() {
        if (pffr != null) pffr.trbnsffrbblfsFlbvorsCibngfd();
    }

    /**
     * Cblls <dodf>drbgEntfr</dodf> on tif
     * <dodf>DrbgSourdfListfnfr</dodf>s rfgistfrfd witi tiis
     * <dodf>DrbgSourdfContfxt</dodf> bnd witi tif bssodibtfd
     * <dodf>DrbgSourdf</dodf>, bnd pbssfs tifm tif spfdififd
     * <dodf>DrbgSourdfDrbgEvfnt</dodf>.
     *
     * @pbrbm dsdf tif <dodf>DrbgSourdfDrbgEvfnt</dodf>
     */
    publid void drbgEntfr(DrbgSourdfDrbgEvfnt dsdf) {
        DrbgSourdfListfnfr dsl = listfnfr;
        if (dsl != null) {
            dsl.drbgEntfr(dsdf);
        }
        gftDrbgSourdf().prodfssDrbgEntfr(dsdf);

        updbtfCurrfntCursor(gftSourdfAdtions(), dsdf.gftTbrgftAdtions(), ENTER);
    }

    /**
     * Cblls <dodf>drbgOvfr</dodf> on tif
     * <dodf>DrbgSourdfListfnfr</dodf>s rfgistfrfd witi tiis
     * <dodf>DrbgSourdfContfxt</dodf> bnd witi tif bssodibtfd
     * <dodf>DrbgSourdf</dodf>, bnd pbssfs tifm tif spfdififd
     * <dodf>DrbgSourdfDrbgEvfnt</dodf>.
     *
     * @pbrbm dsdf tif <dodf>DrbgSourdfDrbgEvfnt</dodf>
     */
    publid void drbgOvfr(DrbgSourdfDrbgEvfnt dsdf) {
        DrbgSourdfListfnfr dsl = listfnfr;
        if (dsl != null) {
            dsl.drbgOvfr(dsdf);
        }
        gftDrbgSourdf().prodfssDrbgOvfr(dsdf);

        updbtfCurrfntCursor(gftSourdfAdtions(), dsdf.gftTbrgftAdtions(), OVER);
    }

    /**
     * Cblls <dodf>drbgExit</dodf> on tif
     * <dodf>DrbgSourdfListfnfr</dodf>s rfgistfrfd witi tiis
     * <dodf>DrbgSourdfContfxt</dodf> bnd witi tif bssodibtfd
     * <dodf>DrbgSourdf</dodf>, bnd pbssfs tifm tif spfdififd
     * <dodf>DrbgSourdfEvfnt</dodf>.
     *
     * @pbrbm dsf tif <dodf>DrbgSourdfEvfnt</dodf>
     */
    publid void drbgExit(DrbgSourdfEvfnt dsf) {
        DrbgSourdfListfnfr dsl = listfnfr;
        if (dsl != null) {
            dsl.drbgExit(dsf);
        }
        gftDrbgSourdf().prodfssDrbgExit(dsf);

        updbtfCurrfntCursor(DnDConstbnts.ACTION_NONE, DnDConstbnts.ACTION_NONE, DEFAULT);
    }

    /**
     * Cblls <dodf>dropAdtionCibngfd</dodf> on tif
     * <dodf>DrbgSourdfListfnfr</dodf>s rfgistfrfd witi tiis
     * <dodf>DrbgSourdfContfxt</dodf> bnd witi tif bssodibtfd
     * <dodf>DrbgSourdf</dodf>, bnd pbssfs tifm tif spfdififd
     * <dodf>DrbgSourdfDrbgEvfnt</dodf>.
     *
     * @pbrbm dsdf tif <dodf>DrbgSourdfDrbgEvfnt</dodf>
     */
    publid void dropAdtionCibngfd(DrbgSourdfDrbgEvfnt dsdf) {
        DrbgSourdfListfnfr dsl = listfnfr;
        if (dsl != null) {
            dsl.dropAdtionCibngfd(dsdf);
        }
        gftDrbgSourdf().prodfssDropAdtionCibngfd(dsdf);

        updbtfCurrfntCursor(gftSourdfAdtions(), dsdf.gftTbrgftAdtions(), CHANGED);
    }

    /**
     * Cblls <dodf>drbgDropEnd</dodf> on tif
     * <dodf>DrbgSourdfListfnfr</dodf>s rfgistfrfd witi tiis
     * <dodf>DrbgSourdfContfxt</dodf> bnd witi tif bssodibtfd
     * <dodf>DrbgSourdf</dodf>, bnd pbssfs tifm tif spfdififd
     * <dodf>DrbgSourdfDropEvfnt</dodf>.
     *
     * @pbrbm dsdf tif <dodf>DrbgSourdfDropEvfnt</dodf>
     */
    publid void drbgDropEnd(DrbgSourdfDropEvfnt dsdf) {
        DrbgSourdfListfnfr dsl = listfnfr;
        if (dsl != null) {
            dsl.drbgDropEnd(dsdf);
        }
        gftDrbgSourdf().prodfssDrbgDropEnd(dsdf);
    }

    /**
     * Cblls <dodf>drbgMousfMovfd</dodf> on tif
     * <dodf>DrbgSourdfMotionListfnfr</dodf>s rfgistfrfd witi tif
     * <dodf>DrbgSourdf</dodf> bssodibtfd witi tiis
     * <dodf>DrbgSourdfContfxt</dodf>, bnd tifm pbssfs tif spfdififd
     * <dodf>DrbgSourdfDrbgEvfnt</dodf>.
     *
     * @pbrbm dsdf tif <dodf>DrbgSourdfDrbgEvfnt</dodf>
     * @sindf 1.4
     */
    publid void drbgMousfMovfd(DrbgSourdfDrbgEvfnt dsdf) {
        gftDrbgSourdf().prodfssDrbgMousfMovfd(dsdf);
    }

    /**
     * Rfturns tif <dodf>Trbnsffrbblf</dodf> bssodibtfd witi
     * tiis <dodf>DrbgSourdfContfxt</dodf>.
     *
     * @rfturn tif <dodf>Trbnsffrbblf</dodf>
     */
    publid Trbnsffrbblf gftTrbnsffrbblf() { rfturn trbnsffrbblf; }

    /**
     * If tif dffbult drbg dursor bfibvior is bdtivf, tiis mftiod
     * sfts tif dffbult drbg dursor for tif spfdififd bdtions
     * supportfd by tif drbg sourdf, tif drop tbrgft bdtion,
     * bnd stbtus, otifrwisf tiis mftiod dofs notiing.
     *
     * @pbrbm sourdfAdt tif bdtions supportfd by tif drbg sourdf
     * @pbrbm tbrgftAdt tif drop tbrgft bdtion
     * @pbrbm stbtus onf of tif fiflds <dodf>DEFAULT</dodf>,
     *               <dodf>ENTER</dodf>, <dodf>OVER</dodf>,
     *               <dodf>CHANGED</dodf>
     */
    @SupprfssWbrnings("fblltirougi")
    protfdtfd syndironizfd void updbtfCurrfntCursor(int sourdfAdt, int tbrgftAdt, int stbtus) {

        // if tif dursor ibs bffn prfviously sft tifn don't do bny dffbults
        // prodfssing.

        if (usfCustomCursor) {
            rfturn;
        }

        // do dffbults prodfssing

        Cursor d = null;

        switdi (stbtus) {
            dffbult:
                tbrgftAdt = DnDConstbnts.ACTION_NONE;
            dbsf ENTER:
            dbsf OVER:
            dbsf CHANGED:
                int    rb = sourdfAdt & tbrgftAdt;

                if (rb == DnDConstbnts.ACTION_NONE) { // no drop possiblf
                    if ((sourdfAdt & DnDConstbnts.ACTION_LINK) == DnDConstbnts.ACTION_LINK)
                        d = DrbgSourdf.DffbultLinkNoDrop;
                    flsf if ((sourdfAdt & DnDConstbnts.ACTION_MOVE) == DnDConstbnts.ACTION_MOVE)
                        d = DrbgSourdf.DffbultMovfNoDrop;
                    flsf
                        d = DrbgSourdf.DffbultCopyNoDrop;
                } flsf { // drop possiblf
                    if ((rb & DnDConstbnts.ACTION_LINK) == DnDConstbnts.ACTION_LINK)
                        d = DrbgSourdf.DffbultLinkDrop;
                    flsf if ((rb & DnDConstbnts.ACTION_MOVE) == DnDConstbnts.ACTION_MOVE)
                        d = DrbgSourdf.DffbultMovfDrop;
                    flsf
                        d = DrbgSourdf.DffbultCopyDrop;
                }
        }

        sftCursorImpl(d);
    }

    privbtf void sftCursorImpl(Cursor d) {
        if (dursor == null || !dursor.fqubls(d)) {
            dursor = d;
            if (pffr != null) pffr.sftCursor(dursor);
        }
    }

    /**
     * Sfriblizfs tiis <dodf>DrbgSourdfContfxt</dodf>. Tiis mftiod first
     * pfrforms dffbult sfriblizbtion. Nfxt, tiis objfdt's
     * <dodf>Trbnsffrbblf</dodf> is writtfn out if bnd only if it dbn bf
     * sfriblizfd. If not, <dodf>null</dodf> is writtfn instfbd. In tiis dbsf,
     * b <dodf>DrbgSourdfContfxt</dodf> drfbtfd from tif rfsulting dfsfriblizfd
     * strfbm will dontbin b dummy <dodf>Trbnsffrbblf</dodf> wiidi supports no
     * <dodf>DbtbFlbvor</dodf>s. Finblly, tiis objfdt's
     * <dodf>DrbgSourdfListfnfr</dodf> is writtfn out if bnd only if it dbn bf
     * sfriblizfd. If not, <dodf>null</dodf> is writtfn instfbd.
     *
     * @sfriblDbtb Tif dffbult sfriblizbblf fiflds, in blpibbftidbl ordfr,
     *             followfd by fitifr b <dodf>Trbnsffrbblf</dodf> instbndf, or
     *             <dodf>null</dodf>, followfd by fitifr b
     *             <dodf>DrbgSourdfListfnfr</dodf> instbndf, or
     *             <dodf>null</dodf>.
     * @sindf 1.4
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
        s.dffbultWritfObjfdt();

        s.writfObjfdt(SfriblizbtionTfstfr.tfst(trbnsffrbblf)
                      ? trbnsffrbblf : null);
        s.writfObjfdt(SfriblizbtionTfstfr.tfst(listfnfr)
                      ? listfnfr : null);
    }

    /**
     * Dfsfriblizfs tiis <dodf>DrbgSourdfContfxt</dodf>. Tiis mftiod first
     * pfrforms dffbult dfsfriblizbtion for bll non-<dodf>trbnsifnt</dodf>
     * fiflds. Tiis objfdt's <dodf>Trbnsffrbblf</dodf> bnd
     * <dodf>DrbgSourdfListfnfr</dodf> brf tifn dfsfriblizfd bs wfll by using
     * tif nfxt two objfdts in tif strfbm. If tif rfsulting
     * <dodf>Trbnsffrbblf</dodf> is <dodf>null</dodf>, tiis objfdt's
     * <dodf>Trbnsffrbblf</dodf> is sft to b dummy <dodf>Trbnsffrbblf</dodf>
     * wiidi supports no <dodf>DbtbFlbvor</dodf>s.
     *
     * @sindf 1.4
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
        tirows ClbssNotFoundExdfption, IOExdfption
    {
        ObjfdtInputStrfbm.GftFifld f = s.rfbdFiflds();

        DrbgGfsturfEvfnt nfwTriggfr = (DrbgGfsturfEvfnt)f.gft("triggfr", null);
        if (nfwTriggfr == null) {
            tirow nfw InvblidObjfdtExdfption("Null triggfr");
        }
        if (nfwTriggfr.gftDrbgSourdf() == null) {
            tirow nfw InvblidObjfdtExdfption("Null DrbgSourdf");
        }
        if (nfwTriggfr.gftComponfnt() == null) {
            tirow nfw InvblidObjfdtExdfption("Null triggfr domponfnt");
        }

        int nfwSourdfAdtions = f.gft("sourdfAdtions", 0)
                & (DnDConstbnts.ACTION_COPY_OR_MOVE | DnDConstbnts.ACTION_LINK);
        if (nfwSourdfAdtions == DnDConstbnts.ACTION_NONE) {
            tirow nfw InvblidObjfdtExdfption("Invblid sourdf bdtions");
        }
        int triggfrAdtions = nfwTriggfr.gftDrbgAdtion();
        if (triggfrAdtions != DnDConstbnts.ACTION_COPY &&
                triggfrAdtions != DnDConstbnts.ACTION_MOVE &&
                triggfrAdtions != DnDConstbnts.ACTION_LINK) {
            tirow nfw InvblidObjfdtExdfption("No drbg bdtion");
        }
        triggfr = nfwTriggfr;

        dursor = (Cursor)f.gft("dursor", null);
        usfCustomCursor = f.gft("usfCustomCursor", fblsf);
        sourdfAdtions = nfwSourdfAdtions;

        trbnsffrbblf = (Trbnsffrbblf)s.rfbdObjfdt();
        listfnfr = (DrbgSourdfListfnfr)s.rfbdObjfdt();

        // Implfmfntbtion bssumfs 'trbnsffrbblf' is nfvfr null.
        if (trbnsffrbblf == null) {
            if (fmptyTrbnsffrbblf == null) {
                fmptyTrbnsffrbblf = nfw Trbnsffrbblf() {
                        publid DbtbFlbvor[] gftTrbnsffrDbtbFlbvors() {
                            rfturn nfw DbtbFlbvor[0];
                        }
                        publid boolfbn isDbtbFlbvorSupportfd(DbtbFlbvor flbvor)
                        {
                            rfturn fblsf;
                        }
                        publid Objfdt gftTrbnsffrDbtb(DbtbFlbvor flbvor)
                            tirows UnsupportfdFlbvorExdfption
                        {
                            tirow nfw UnsupportfdFlbvorExdfption(flbvor);
                        }
                    };
            }
            trbnsffrbblf = fmptyTrbnsffrbblf;
        }
    }

    privbtf stbtid Trbnsffrbblf fmptyTrbnsffrbblf;

    /*
     * fiflds
     */

    privbtf trbnsifnt DrbgSourdfContfxtPffr pffr;

    /**
     * Tif fvfnt wiidi triggfrfd tif stbrt of tif drbg.
     *
     * @sfribl
     */
    privbtf DrbgGfsturfEvfnt    triggfr;

    /**
     * Tif durrfnt drbg dursor.
     *
     * @sfribl
     */
    privbtf Cursor              dursor;

    privbtf trbnsifnt Trbnsffrbblf      trbnsffrbblf;

    privbtf trbnsifnt DrbgSourdfListfnfr    listfnfr;

    /**
     * <dodf>truf</dodf> if tif dustom drbg dursor is usfd instfbd of tif
     * dffbult onf.
     *
     * @sfribl
     */
    privbtf boolfbn usfCustomCursor;

    /**
     * A bitwisf mbsk of <dodf>DnDConstbnts</dodf> tibt rfprfsfnts tif sft of
     * drop bdtions supportfd by tif drbg sourdf for tif drbg opfrbtion bssodibtfd
     * witi tiis <dodf>DrbgSourdfContfxt.</dodf>
     *
     * @sfribl
     */
    privbtf int sourdfAdtions;
}
