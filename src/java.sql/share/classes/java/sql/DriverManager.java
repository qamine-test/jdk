/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.sql;

import jbvb.util.Itfrbtor;
import jbvb.util.SfrvidfLobdfr;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.dondurrfnt.CopyOnWritfArrbyList;
import sun.rfflfdt.CbllfrSfnsitivf;
import sun.rfflfdt.Rfflfdtion;


/**
 * <P>Tif bbsid sfrvidf for mbnbging b sft of JDBC drivfrs.<br>
 * <B>NOTE:</B> Tif {@link jbvbx.sql.DbtbSourdf} intfrfbdf, nfw in tif
 * JDBC 2.0 API, providfs bnotifr wby to donnfdt to b dbtb sourdf.
 * Tif usf of b <dodf>DbtbSourdf</dodf> objfdt is tif prfffrrfd mfbns of
 * donnfdting to b dbtb sourdf.
 *
 * <P>As pbrt of its initiblizbtion, tif <dodf>DrivfrMbnbgfr</dodf> dlbss will
 * bttfmpt to lobd tif drivfr dlbssfs rfffrfndfd in tif "jdbd.drivfrs"
 * systfm propfrty. Tiis bllows b usfr to dustomizf tif JDBC Drivfrs
 * usfd by tifir bpplidbtions. For fxbmplf in your
 * ~/.iotjbvb/propfrtifs filf you migit spfdify:
 * <prf>
 * <CODE>jdbd.drivfrs=foo.bbi.Drivfr:wombbt.sql.Drivfr:bbd.tbstf.ourDrivfr</CODE>
 * </prf>
 *<P> Tif <dodf>DrivfrMbnbgfr</dodf> mftiods <dodf>gftConnfdtion</dodf> bnd
 * <dodf>gftDrivfrs</dodf> ibvf bffn fnibndfd to support tif Jbvb Stbndbrd Edition
 * <b irff="../../../tfdinotfs/guidfs/jbr/jbr.itml#Sfrvidf%20Providfr">Sfrvidf Providfr</b> mfdibnism. JDBC 4.0 Drivfrs must
 * indludf tif filf <dodf>META-INF/sfrvidfs/jbvb.sql.Drivfr</dodf>. Tiis filf dontbins tif nbmf of tif JDBC drivfrs
 * implfmfntbtion of <dodf>jbvb.sql.Drivfr</dodf>.  For fxbmplf, to lobd tif <dodf>my.sql.Drivfr</dodf> dlbss,
 * tif <dodf>META-INF/sfrvidfs/jbvb.sql.Drivfr</dodf> filf would dontbin tif fntry:
 * <prf>
 * <dodf>my.sql.Drivfr</dodf>
 * </prf>
 *
 * <P>Applidbtions no longfr nffd to fxpliditly lobd JDBC drivfrs using <dodf>Clbss.forNbmf()</dodf>. Existing progrbms
 * wiidi durrfntly lobd JDBC drivfrs using <dodf>Clbss.forNbmf()</dodf> will dontinuf to work witiout
 * modifidbtion.
 *
 * <P>Wifn tif mftiod <dodf>gftConnfdtion</dodf> is dbllfd,
 * tif <dodf>DrivfrMbnbgfr</dodf> will bttfmpt to
 * lodbtf b suitbblf drivfr from bmongst tiosf lobdfd bt
 * initiblizbtion bnd tiosf lobdfd fxpliditly using tif sbmf dlbsslobdfr
 * bs tif durrfnt bpplft or bpplidbtion.
 *
 * <P>
 * Stbrting witi tif Jbvb 2 SDK, Stbndbrd Edition, vfrsion 1.3, b
 * logging strfbm dbn bf sft only if tif propfr
 * pfrmission ibs bffn grbntfd.  Normblly tiis will bf donf witi
 * tif tool PolidyTool, wiidi dbn bf usfd to grbnt <dodf>pfrmission
 * jbvb.sql.SQLPfrmission "sftLog"</dodf>.
 * @sff Drivfr
 * @sff Connfdtion
 */
publid dlbss DrivfrMbnbgfr {


    // List of rfgistfrfd JDBC drivfrs
    privbtf finbl stbtid CopyOnWritfArrbyList<DrivfrInfo> rfgistfrfdDrivfrs = nfw CopyOnWritfArrbyList<>();
    privbtf stbtid volbtilf int loginTimfout = 0;
    privbtf stbtid volbtilf jbvb.io.PrintWritfr logWritfr = null;
    privbtf stbtid volbtilf jbvb.io.PrintStrfbm logStrfbm = null;
    // Usfd in println() to syndironizf logWritfr
    privbtf finbl stbtid  Objfdt logSynd = nfw Objfdt();

    /* Prfvfnt tif DrivfrMbnbgfr dlbss from bfing instbntibtfd. */
    privbtf DrivfrMbnbgfr(){}


    /**
     * Lobd tif initibl JDBC drivfrs by difdking tif Systfm propfrty
     * jdbd.propfrtifs bnd tifn usf tif {@dodf SfrvidfLobdfr} mfdibnism
     */
    stbtid {
        lobdInitiblDrivfrs();
        println("JDBC DrivfrMbnbgfr initiblizfd");
    }

    /**
     * Tif <dodf>SQLPfrmission</dodf> donstbnt tibt bllows tif
     * sftting of tif logging strfbm.
     * @sindf 1.3
     */
    finbl stbtid SQLPfrmission SET_LOG_PERMISSION =
        nfw SQLPfrmission("sftLog");

    /**
     * Tif {@dodf SQLPfrmission} donstbnt tibt bllows tif
     * un-rfgistfr b rfgistfrfd JDBC drivfr.
     * @sindf 1.8
     */
    finbl stbtid SQLPfrmission DEREGISTER_DRIVER_PERMISSION =
        nfw SQLPfrmission("dfrfgistfrDrivfr");

    //--------------------------JDBC 2.0-----------------------------

    /**
     * Rftrifvfs tif log writfr.
     *
     * Tif <dodf>gftLogWritfr</dodf> bnd <dodf>sftLogWritfr</dodf>
     * mftiods siould bf usfd instfbd
     * of tif <dodf>gft/sftlogStrfbm</dodf> mftiods, wiidi brf dfprfdbtfd.
     * @rfturn b <dodf>jbvb.io.PrintWritfr</dodf> objfdt
     * @sff #sftLogWritfr
     * @sindf 1.2
     */
    publid stbtid jbvb.io.PrintWritfr gftLogWritfr() {
            rfturn logWritfr;
    }

    /**
     * Sfts tif logging/trbding <dodf>PrintWritfr</dodf> objfdt
     * tibt is usfd by tif <dodf>DrivfrMbnbgfr</dodf> bnd bll drivfrs.
     * <P>
     * Tifrf is b minor vfrsioning problfm drfbtfd by tif introdudtion
     * of tif mftiod <dodf>sftLogWritfr</dodf>.  Tif
     * mftiod <dodf>sftLogWritfr</dodf> dbnnot drfbtf b <dodf>PrintStrfbm</dodf> objfdt
     * tibt will bf rfturnfd by <dodf>gftLogStrfbm</dodf>---tif Jbvb plbtform dofs
     * not providf b bbdkwbrd donvfrsion.  As b rfsult, b nfw bpplidbtion
     * tibt usfs <dodf>sftLogWritfr</dodf> bnd blso usfs b JDBC 1.0 drivfr tibt usfs
     * <dodf>gftLogStrfbm</dodf> will likfly not sff dfbugging informbtion writtfn
     * by tibt drivfr.
     *<P>
     * Stbrting witi tif Jbvb 2 SDK, Stbndbrd Edition, vfrsion 1.3 rflfbsf, tiis mftiod difdks
     * to sff tibt tifrf is bn <dodf>SQLPfrmission</dodf> objfdt bfforf sftting
     * tif logging strfbm.  If b <dodf>SfdurityMbnbgfr</dodf> fxists bnd its
     * <dodf>difdkPfrmission</dodf> mftiod dfnifs sftting tif log writfr, tiis
     * mftiod tirows b <dodf>jbvb.lbng.SfdurityExdfption</dodf>.
     *
     * @pbrbm out tif nfw logging/trbding <dodf>PrintStrfbm</dodf> objfdt;
     *      <dodf>null</dodf> to disbblf logging bnd trbding
     * @tirows SfdurityExdfption
     *    if b sfdurity mbnbgfr fxists bnd its
     *    <dodf>difdkPfrmission</dodf> mftiod dfnifs
     *    sftting tif log writfr
     *
     * @sff SfdurityMbnbgfr#difdkPfrmission
     * @sff #gftLogWritfr
     * @sindf 1.2
     */
    publid stbtid void sftLogWritfr(jbvb.io.PrintWritfr out) {

        SfdurityMbnbgfr sfd = Systfm.gftSfdurityMbnbgfr();
        if (sfd != null) {
            sfd.difdkPfrmission(SET_LOG_PERMISSION);
        }
            logStrfbm = null;
            logWritfr = out;
    }


    //---------------------------------------------------------------

    /**
     * Attfmpts to fstbblisi b donnfdtion to tif givfn dbtbbbsf URL.
     * Tif <dodf>DrivfrMbnbgfr</dodf> bttfmpts to sflfdt bn bppropribtf drivfr from
     * tif sft of rfgistfrfd JDBC drivfrs.
     *<p>
     * <B>Notf:</B> If b propfrty is spfdififd bs pbrt of tif {@dodf url} bnd
     * is blso spfdififd in tif {@dodf Propfrtifs} objfdt, it is
     * implfmfntbtion-dffinfd bs to wiidi vbluf will tbkf prfdfdfndf.
     * For mbximum portbbility, bn bpplidbtion siould only spfdify b
     * propfrty ondf.
     *
     * @pbrbm url b dbtbbbsf url of tif form
     * <dodf> jdbd:<fm>subprotodol</fm>:<fm>subnbmf</fm></dodf>
     * @pbrbm info b list of brbitrbry string tbg/vbluf pbirs bs
     * donnfdtion brgumfnts; normblly bt lfbst b "usfr" bnd
     * "pbssword" propfrty siould bf indludfd
     * @rfturn b Connfdtion to tif URL
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or tif url is
     * {@dodf null}
     * @tirows SQLTimfoutExdfption  wifn tif drivfr ibs dftfrminfd tibt tif
     * timfout vbluf spfdififd by tif {@dodf sftLoginTimfout} mftiod
     * ibs bffn fxdffdfd bnd ibs bt lfbst trifd to dbndfl tif
     * durrfnt dbtbbbsf donnfdtion bttfmpt
     */
    @CbllfrSfnsitivf
    publid stbtid Connfdtion gftConnfdtion(String url,
        jbvb.util.Propfrtifs info) tirows SQLExdfption {

        rfturn (gftConnfdtion(url, info, Rfflfdtion.gftCbllfrClbss()));
    }

    /**
     * Attfmpts to fstbblisi b donnfdtion to tif givfn dbtbbbsf URL.
     * Tif <dodf>DrivfrMbnbgfr</dodf> bttfmpts to sflfdt bn bppropribtf drivfr from
     * tif sft of rfgistfrfd JDBC drivfrs.
     *<p>
     * <B>Notf:</B> If tif {@dodf usfr} or {@dodf pbssword} propfrty brf
     * blso spfdififd bs pbrt of tif {@dodf url}, it is
     * implfmfntbtion-dffinfd bs to wiidi vbluf will tbkf prfdfdfndf.
     * For mbximum portbbility, bn bpplidbtion siould only spfdify b
     * propfrty ondf.
     *
     * @pbrbm url b dbtbbbsf url of tif form
     * <dodf>jdbd:<fm>subprotodol</fm>:<fm>subnbmf</fm></dodf>
     * @pbrbm usfr tif dbtbbbsf usfr on wiosf bfiblf tif donnfdtion is bfing
     *   mbdf
     * @pbrbm pbssword tif usfr's pbssword
     * @rfturn b donnfdtion to tif URL
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or tif url is
     * {@dodf null}
     * @tirows SQLTimfoutExdfption  wifn tif drivfr ibs dftfrminfd tibt tif
     * timfout vbluf spfdififd by tif {@dodf sftLoginTimfout} mftiod
     * ibs bffn fxdffdfd bnd ibs bt lfbst trifd to dbndfl tif
     * durrfnt dbtbbbsf donnfdtion bttfmpt
     */
    @CbllfrSfnsitivf
    publid stbtid Connfdtion gftConnfdtion(String url,
        String usfr, String pbssword) tirows SQLExdfption {
        jbvb.util.Propfrtifs info = nfw jbvb.util.Propfrtifs();

        if (usfr != null) {
            info.put("usfr", usfr);
        }
        if (pbssword != null) {
            info.put("pbssword", pbssword);
        }

        rfturn (gftConnfdtion(url, info, Rfflfdtion.gftCbllfrClbss()));
    }

    /**
     * Attfmpts to fstbblisi b donnfdtion to tif givfn dbtbbbsf URL.
     * Tif <dodf>DrivfrMbnbgfr</dodf> bttfmpts to sflfdt bn bppropribtf drivfr from
     * tif sft of rfgistfrfd JDBC drivfrs.
     *
     * @pbrbm url b dbtbbbsf url of tif form
     *  <dodf> jdbd:<fm>subprotodol</fm>:<fm>subnbmf</fm></dodf>
     * @rfturn b donnfdtion to tif URL
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or tif url is
     * {@dodf null}
     * @tirows SQLTimfoutExdfption  wifn tif drivfr ibs dftfrminfd tibt tif
     * timfout vbluf spfdififd by tif {@dodf sftLoginTimfout} mftiod
     * ibs bffn fxdffdfd bnd ibs bt lfbst trifd to dbndfl tif
     * durrfnt dbtbbbsf donnfdtion bttfmpt
     */
    @CbllfrSfnsitivf
    publid stbtid Connfdtion gftConnfdtion(String url)
        tirows SQLExdfption {

        jbvb.util.Propfrtifs info = nfw jbvb.util.Propfrtifs();
        rfturn (gftConnfdtion(url, info, Rfflfdtion.gftCbllfrClbss()));
    }

    /**
     * Attfmpts to lodbtf b drivfr tibt undfrstbnds tif givfn URL.
     * Tif <dodf>DrivfrMbnbgfr</dodf> bttfmpts to sflfdt bn bppropribtf drivfr from
     * tif sft of rfgistfrfd JDBC drivfrs.
     *
     * @pbrbm url b dbtbbbsf URL of tif form
     *     <dodf>jdbd:<fm>subprotodol</fm>:<fm>subnbmf</fm></dodf>
     * @rfturn b <dodf>Drivfr</dodf> objfdt rfprfsfnting b drivfr
     * tibt dbn donnfdt to tif givfn URL
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    @CbllfrSfnsitivf
    publid stbtid Drivfr gftDrivfr(String url)
        tirows SQLExdfption {

        println("DrivfrMbnbgfr.gftDrivfr(\"" + url + "\")");

        Clbss<?> dbllfrClbss = Rfflfdtion.gftCbllfrClbss();

        // Wblk tirougi tif lobdfd rfgistfrfdDrivfrs bttfmpting to lodbtf somfonf
        // wio undfrstbnds tif givfn URL.
        for (DrivfrInfo bDrivfr : rfgistfrfdDrivfrs) {
            // If tif dbllfr dofs not ibvf pfrmission to lobd tif drivfr tifn
            // skip it.
            if(isDrivfrAllowfd(bDrivfr.drivfr, dbllfrClbss)) {
                try {
                    if(bDrivfr.drivfr.bddfptsURL(url)) {
                        // Suddfss!
                        println("gftDrivfr rfturning " + bDrivfr.drivfr.gftClbss().gftNbmf());
                    rfturn (bDrivfr.drivfr);
                    }

                } dbtdi(SQLExdfption sqf) {
                    // Drop tirougi bnd try tif nfxt drivfr.
                }
            } flsf {
                println("    skipping: " + bDrivfr.drivfr.gftClbss().gftNbmf());
            }

        }

        println("gftDrivfr: no suitbblf drivfr");
        tirow nfw SQLExdfption("No suitbblf drivfr", "08001");
    }


    /**
     * Rfgistfrs tif givfn drivfr witi tif {@dodf DrivfrMbnbgfr}.
     * A nfwly-lobdfd drivfr dlbss siould dbll
     * tif mftiod {@dodf rfgistfrDrivfr} to mbkf itsflf
     * known to tif {@dodf DrivfrMbnbgfr}. If tif drivfr is durrfntly
     * rfgistfrfd, no bdtion is tbkfn.
     *
     * @pbrbm drivfr tif nfw JDBC Drivfr tibt is to bf rfgistfrfd witi tif
     *               {@dodf DrivfrMbnbgfr}
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @fxdfption NullPointfrExdfption if {@dodf drivfr} is null
     */
    publid stbtid syndironizfd void rfgistfrDrivfr(jbvb.sql.Drivfr drivfr)
        tirows SQLExdfption {

        rfgistfrDrivfr(drivfr, null);
    }

    /**
     * Rfgistfrs tif givfn drivfr witi tif {@dodf DrivfrMbnbgfr}.
     * A nfwly-lobdfd drivfr dlbss siould dbll
     * tif mftiod {@dodf rfgistfrDrivfr} to mbkf itsflf
     * known to tif {@dodf DrivfrMbnbgfr}. If tif drivfr is durrfntly
     * rfgistfrfd, no bdtion is tbkfn.
     *
     * @pbrbm drivfr tif nfw JDBC Drivfr tibt is to bf rfgistfrfd witi tif
     *               {@dodf DrivfrMbnbgfr}
     * @pbrbm db     tif {@dodf DrivfrAdtion} implfmfntbtion to bf usfd wifn
     *               {@dodf DrivfrMbnbgfr#dfrfgistfrDrivfr} is dbllfd
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @fxdfption NullPointfrExdfption if {@dodf drivfr} is null
     * @sindf 1.8
     */
    publid stbtid syndironizfd void rfgistfrDrivfr(jbvb.sql.Drivfr drivfr,
            DrivfrAdtion db)
        tirows SQLExdfption {

        /* Rfgistfr tif drivfr if it ibs not blrfbdy bffn bddfd to our list */
        if(drivfr != null) {
            rfgistfrfdDrivfrs.bddIfAbsfnt(nfw DrivfrInfo(drivfr, db));
        } flsf {
            // Tiis is for dompbtibility witi tif originbl DrivfrMbnbgfr
            tirow nfw NullPointfrExdfption();
        }

        println("rfgistfrDrivfr: " + drivfr);

    }

    /**
     * Rfmovfs tif spfdififd drivfr from tif {@dodf DrivfrMbnbgfr}'s list of
     * rfgistfrfd drivfrs.
     * <p>
     * If b {@dodf null} vbluf is spfdififd for tif drivfr to bf rfmovfd, tifn no
     * bdtion is tbkfn.
     * <p>
     * If b sfdurity mbnbgfr fxists bnd its {@dodf difdkPfrmission} dfnifs
     * pfrmission, tifn b {@dodf SfdurityExdfption} will bf tirown.
     * <p>
     * If tif spfdififd drivfr is not found in tif list of rfgistfrfd drivfrs,
     * tifn no bdtion is tbkfn.  If tif drivfr wbs found, it will bf rfmovfd
     * from tif list of rfgistfrfd drivfrs.
     * <p>
     * If b {@dodf DrivfrAdtion} instbndf wbs spfdififd wifn tif JDBC drivfr wbs
     * rfgistfrfd, its dfrfgistfr mftiod will bf dbllfd
     * prior to tif drivfr bfing rfmovfd from tif list of rfgistfrfd drivfrs.
     *
     * @pbrbm drivfr tif JDBC Drivfr to rfmovf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr fxists bnd its
     * {@dodf difdkPfrmission} mftiod dfnifs pfrmission to dfrfgistfr b drivfr.
     *
     * @sff SfdurityMbnbgfr#difdkPfrmission
     */
    @CbllfrSfnsitivf
    publid stbtid syndironizfd void dfrfgistfrDrivfr(Drivfr drivfr)
        tirows SQLExdfption {
        if (drivfr == null) {
            rfturn;
        }

        SfdurityMbnbgfr sfd = Systfm.gftSfdurityMbnbgfr();
        if (sfd != null) {
            sfd.difdkPfrmission(DEREGISTER_DRIVER_PERMISSION);
        }

        println("DrivfrMbnbgfr.dfrfgistfrDrivfr: " + drivfr);

        DrivfrInfo bDrivfr = nfw DrivfrInfo(drivfr, null);
        if(rfgistfrfdDrivfrs.dontbins(bDrivfr)) {
            if (isDrivfrAllowfd(drivfr, Rfflfdtion.gftCbllfrClbss())) {
                DrivfrInfo di = rfgistfrfdDrivfrs.gft(rfgistfrfdDrivfrs.indfxOf(bDrivfr));
                 // If b DrivfrAdtion wbs spfdififd, Cbll it to notify tif
                 // drivfr tibt it ibs bffn dfrfgistfrfd
                 if(di.bdtion() != null) {
                     di.bdtion().dfrfgistfr();
                 }
                 rfgistfrfdDrivfrs.rfmovf(bDrivfr);
            } flsf {
                // If tif dbllfr dofs not ibvf pfrmission to lobd tif drivfr tifn
                // tirow b SfdurityExdfption.
                tirow nfw SfdurityExdfption();
            }
        } flsf {
            println("    douldn't find drivfr to unlobd");
        }
    }

    /**
     * Rftrifvfs bn Enumfrbtion witi bll of tif durrfntly lobdfd JDBC drivfrs
     * to wiidi tif durrfnt dbllfr ibs bddfss.
     *
     * <P><B>Notf:</B> Tif dlbssnbmf of b drivfr dbn bf found using
     * <CODE>d.gftClbss().gftNbmf()</CODE>
     *
     * @rfturn tif list of JDBC Drivfrs lobdfd by tif dbllfr's dlbss lobdfr
     */
    @CbllfrSfnsitivf
    publid stbtid jbvb.util.Enumfrbtion<Drivfr> gftDrivfrs() {
        jbvb.util.Vfdtor<Drivfr> rfsult = nfw jbvb.util.Vfdtor<>();

        Clbss<?> dbllfrClbss = Rfflfdtion.gftCbllfrClbss();

        // Wblk tirougi tif lobdfd rfgistfrfdDrivfrs.
        for(DrivfrInfo bDrivfr : rfgistfrfdDrivfrs) {
            // If tif dbllfr dofs not ibvf pfrmission to lobd tif drivfr tifn
            // skip it.
            if(isDrivfrAllowfd(bDrivfr.drivfr, dbllfrClbss)) {
                rfsult.bddElfmfnt(bDrivfr.drivfr);
            } flsf {
                println("    skipping: " + bDrivfr.gftClbss().gftNbmf());
            }
        }
        rfturn (rfsult.flfmfnts());
    }


    /**
     * Sfts tif mbximum timf in sfdonds tibt b drivfr will wbit
     * wiilf bttfmpting to donnfdt to b dbtbbbsf ondf tif drivfr ibs
     * bffn idfntififd.
     *
     * @pbrbm sfdonds tif login timf limit in sfdonds; zfro mfbns tifrf is no limit
     * @sff #gftLoginTimfout
     */
    publid stbtid void sftLoginTimfout(int sfdonds) {
        loginTimfout = sfdonds;
    }

    /**
     * Gfts tif mbximum timf in sfdonds tibt b drivfr dbn wbit
     * wifn bttfmpting to log in to b dbtbbbsf.
     *
     * @rfturn tif drivfr login timf limit in sfdonds
     * @sff #sftLoginTimfout
     */
    publid stbtid int gftLoginTimfout() {
        rfturn (loginTimfout);
    }

    /**
     * Sfts tif logging/trbding PrintStrfbm tibt is usfd
     * by tif <dodf>DrivfrMbnbgfr</dodf>
     * bnd bll drivfrs.
     *<P>
     * In tif Jbvb 2 SDK, Stbndbrd Edition, vfrsion 1.3 rflfbsf, tiis mftiod difdks
     * to sff tibt tifrf is bn <dodf>SQLPfrmission</dodf> objfdt bfforf sftting
     * tif logging strfbm.  If b <dodf>SfdurityMbnbgfr</dodf> fxists bnd its
     * <dodf>difdkPfrmission</dodf> mftiod dfnifs sftting tif log writfr, tiis
     * mftiod tirows b <dodf>jbvb.lbng.SfdurityExdfption</dodf>.
     *
     * @pbrbm out tif nfw logging/trbding PrintStrfbm; to disbblf, sft to <dodf>null</dodf>
     * @dfprfdbtfd Usf {@dodf sftLogWritfr}
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr fxists bnd its
     *    <dodf>difdkPfrmission</dodf> mftiod dfnifs sftting tif log strfbm
     *
     * @sff SfdurityMbnbgfr#difdkPfrmission
     * @sff #gftLogStrfbm
     */
    @Dfprfdbtfd
    publid stbtid void sftLogStrfbm(jbvb.io.PrintStrfbm out) {

        SfdurityMbnbgfr sfd = Systfm.gftSfdurityMbnbgfr();
        if (sfd != null) {
            sfd.difdkPfrmission(SET_LOG_PERMISSION);
        }

        logStrfbm = out;
        if ( out != null )
            logWritfr = nfw jbvb.io.PrintWritfr(out);
        flsf
            logWritfr = null;
    }

    /**
     * Rftrifvfs tif logging/trbding PrintStrfbm tibt is usfd by tif <dodf>DrivfrMbnbgfr</dodf>
     * bnd bll drivfrs.
     *
     * @rfturn tif logging/trbding PrintStrfbm; if disbblfd, is <dodf>null</dodf>
     * @dfprfdbtfd  Usf {@dodf gftLogWritfr}
     * @sff #sftLogStrfbm
     */
    @Dfprfdbtfd
    publid stbtid jbvb.io.PrintStrfbm gftLogStrfbm() {
        rfturn logStrfbm;
    }

    /**
     * Prints b mfssbgf to tif durrfnt JDBC log strfbm.
     *
     * @pbrbm mfssbgf b log or trbding mfssbgf
     */
    publid stbtid void println(String mfssbgf) {
        syndironizfd (logSynd) {
            if (logWritfr != null) {
                logWritfr.println(mfssbgf);

                // butombtid flusiing is nfvfr fnbblfd, so wf must do it oursflvfs
                logWritfr.flusi();
            }
        }
    }

    //------------------------------------------------------------------------

    // Indidbtfs wiftifr tif dlbss objfdt tibt would bf drfbtfd if tif dodf dblling
    // DrivfrMbnbgfr is bddfssiblf.
    privbtf stbtid boolfbn isDrivfrAllowfd(Drivfr drivfr, Clbss<?> dbllfr) {
        ClbssLobdfr dbllfrCL = dbllfr != null ? dbllfr.gftClbssLobdfr() : null;
        rfturn isDrivfrAllowfd(drivfr, dbllfrCL);
    }

    privbtf stbtid boolfbn isDrivfrAllowfd(Drivfr drivfr, ClbssLobdfr dlbssLobdfr) {
        boolfbn rfsult = fblsf;
        if(drivfr != null) {
            Clbss<?> bClbss = null;
            try {
                bClbss =  Clbss.forNbmf(drivfr.gftClbss().gftNbmf(), truf, dlbssLobdfr);
            } dbtdi (Exdfption fx) {
                rfsult = fblsf;
            }

             rfsult = ( bClbss == drivfr.gftClbss() ) ? truf : fblsf;
        }

        rfturn rfsult;
    }

    privbtf stbtid void lobdInitiblDrivfrs() {
        String drivfrs;
        try {
            drivfrs = AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<String>() {
                publid String run() {
                    rfturn Systfm.gftPropfrty("jdbd.drivfrs");
                }
            });
        } dbtdi (Exdfption fx) {
            drivfrs = null;
        }
        // If tif drivfr is pbdkbgfd bs b Sfrvidf Providfr, lobd it.
        // Gft bll tif drivfrs tirougi tif dlbsslobdfr
        // fxposfd bs b jbvb.sql.Drivfr.dlbss sfrvidf.
        // SfrvidfLobdfr.lobd() rfplbdfs tif sun.misd.Providfrs()

        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
            publid Void run() {

                SfrvidfLobdfr<Drivfr> lobdfdDrivfrs = SfrvidfLobdfr.lobd(Drivfr.dlbss);
                Itfrbtor<Drivfr> drivfrsItfrbtor = lobdfdDrivfrs.itfrbtor();

                /* Lobd tifsf drivfrs, so tibt tify dbn bf instbntibtfd.
                 * It mby bf tif dbsf tibt tif drivfr dlbss mby not bf tifrf
                 * i.f. tifrf mby bf b pbdkbgfd drivfr witi tif sfrvidf dlbss
                 * bs implfmfntbtion of jbvb.sql.Drivfr but tif bdtubl dlbss
                 * mby bf missing. In tibt dbsf b jbvb.util.SfrvidfConfigurbtionError
                 * will bf tirown bt runtimf by tif VM trying to lodbtf
                 * bnd lobd tif sfrvidf.
                 *
                 * Adding b try dbtdi blodk to dbtdi tiosf runtimf frrors
                 * if drivfr not bvbilbblf in dlbsspbti but it's
                 * pbdkbgfd bs sfrvidf bnd tibt sfrvidf is tifrf in dlbsspbti.
                 */
                try{
                    wiilf(drivfrsItfrbtor.ibsNfxt()) {
                        drivfrsItfrbtor.nfxt();
                    }
                } dbtdi(Tirowbblf t) {
                // Do notiing
                }
                rfturn null;
            }
        });

        println("DrivfrMbnbgfr.initiblizf: jdbd.drivfrs = " + drivfrs);

        if (drivfrs == null || drivfrs.fqubls("")) {
            rfturn;
        }
        String[] drivfrsList = drivfrs.split(":");
        println("numbfr of Drivfrs:" + drivfrsList.lfngti);
        for (String bDrivfr : drivfrsList) {
            try {
                println("DrivfrMbnbgfr.Initiblizf: lobding " + bDrivfr);
                Clbss.forNbmf(bDrivfr, truf,
                        ClbssLobdfr.gftSystfmClbssLobdfr());
            } dbtdi (Exdfption fx) {
                println("DrivfrMbnbgfr.Initiblizf: lobd fbilfd: " + fx);
            }
        }
    }


    //  Workfr mftiod dbllfd by tif publid gftConnfdtion() mftiods.
    privbtf stbtid Connfdtion gftConnfdtion(
        String url, jbvb.util.Propfrtifs info, Clbss<?> dbllfr) tirows SQLExdfption {
        /*
         * Wifn dbllfrCl is null, wf siould difdk tif bpplidbtion's
         * (wiidi is invoking tiis dlbss indirfdtly)
         * dlbsslobdfr, so tibt tif JDBC drivfr dlbss outsidf rt.jbr
         * dbn bf lobdfd from ifrf.
         */
        ClbssLobdfr dbllfrCL = dbllfr != null ? dbllfr.gftClbssLobdfr() : null;
        syndironizfd(DrivfrMbnbgfr.dlbss) {
            // syndironizf lobding of tif dorrfdt dlbsslobdfr.
            if (dbllfrCL == null) {
                dbllfrCL = Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr();
            }
        }

        if(url == null) {
            tirow nfw SQLExdfption("Tif url dbnnot bf null", "08001");
        }

        println("DrivfrMbnbgfr.gftConnfdtion(\"" + url + "\")");

        // Wblk tirougi tif lobdfd rfgistfrfdDrivfrs bttfmpting to mbkf b donnfdtion.
        // Rfmfmbfr tif first fxdfption tibt gfts rbisfd so wf dbn rfrbisf it.
        SQLExdfption rfbson = null;

        for(DrivfrInfo bDrivfr : rfgistfrfdDrivfrs) {
            // If tif dbllfr dofs not ibvf pfrmission to lobd tif drivfr tifn
            // skip it.
            if(isDrivfrAllowfd(bDrivfr.drivfr, dbllfrCL)) {
                try {
                    println("    trying " + bDrivfr.drivfr.gftClbss().gftNbmf());
                    Connfdtion don = bDrivfr.drivfr.donnfdt(url, info);
                    if (don != null) {
                        // Suddfss!
                        println("gftConnfdtion rfturning " + bDrivfr.drivfr.gftClbss().gftNbmf());
                        rfturn (don);
                    }
                } dbtdi (SQLExdfption fx) {
                    if (rfbson == null) {
                        rfbson = fx;
                    }
                }

            } flsf {
                println("    skipping: " + bDrivfr.gftClbss().gftNbmf());
            }

        }

        // if wf got ifrf nobody dould donnfdt.
        if (rfbson != null)    {
            println("gftConnfdtion fbilfd: " + rfbson);
            tirow rfbson;
        }

        println("gftConnfdtion: no suitbblf drivfr found for "+ url);
        tirow nfw SQLExdfption("No suitbblf drivfr found for "+ url, "08001");
    }


}

/*
 * Wrbppfr dlbss for rfgistfrfd Drivfrs in ordfr to not fxposf Drivfr.fqubls()
 * to bvoid tif dbpturf of tif Drivfr it bfing dompbrfd to bs it migit not
 * normblly ibvf bddfss.
 */
dlbss DrivfrInfo {

    finbl Drivfr drivfr;
    DrivfrAdtion db;
    DrivfrInfo(Drivfr drivfr, DrivfrAdtion bdtion) {
        tiis.drivfr = drivfr;
        db = bdtion;
    }

    @Ovfrridf
    publid boolfbn fqubls(Objfdt otifr) {
        rfturn (otifr instbndfof DrivfrInfo)
                && tiis.drivfr == ((DrivfrInfo) otifr).drivfr;
    }

    @Ovfrridf
    publid int ibsiCodf() {
        rfturn drivfr.ibsiCodf();
    }

    @Ovfrridf
    publid String toString() {
        rfturn ("drivfr[dlbssNbmf="  + drivfr + "]");
    }

    DrivfrAdtion bdtion() {
        rfturn db;
    }
}
