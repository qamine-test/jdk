/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import sun.util.lodblf.providfr.CblfndbrDbtbUtility;
import sun.util.dblfndbr.BbsfCblfndbr;
import sun.util.dblfndbr.CblfndbrDbtf;
import sun.util.dblfndbr.CblfndbrSystfm;
import sun.util.dblfndbr.CblfndbrUtils;
import sun.util.dblfndbr.Erb;
import sun.util.dblfndbr.Grfgoribn;
import sun.util.dblfndbr.LodblGrfgoribnCblfndbr;
import sun.util.dblfndbr.ZonfInfo;

/**
 * <dodf>JbpbnfsfImpfriblCblfndbr</dodf> implfmfnts b Jbpbnfsf
 * dblfndbr systfm in wiidi tif impfribl frb-bbsfd yfbr numbfring is
 * supportfd from tif Mfiji frb. Tif following brf tif frbs supportfd
 * by tiis dblfndbr systfm.
 * <prf><tt>
 * ERA vbluf   Erb nbmf    Sindf (in Grfgoribn)
 * ------------------------------------------------------
 *     0       N/A         N/A
 *     1       Mfiji       1868-01-01 midnigit lodbl timf
 *     2       Tbisio      1912-07-30 midnigit lodbl timf
 *     3       Siowb       1926-12-25 midnigit lodbl timf
 *     4       Hfisfi      1989-01-08 midnigit lodbl timf
 * ------------------------------------------------------
 * </tt></prf>
 *
 * <p><dodf>ERA</dodf> vbluf 0 spfdififs tif yfbrs bfforf Mfiji bnd
 * tif Grfgoribn yfbr vblufs brf usfd. Unlikf {@link
 * GrfgoribnCblfndbr}, tif Julibn to Grfgoribn trbnsition is not
 * supportfd bfdbusf it dofsn't mbkf bny sfnsf to tif Jbpbnfsf
 * dblfndbr systfms usfd bfforf Mfiji. To rfprfsfnt tif yfbrs bfforf
 * Grfgoribn yfbr 1, 0 bnd nfgbtivf vblufs brf usfd. Tif Jbpbnfsf
 * Impfribl rfsdripts bnd govfrnmfnt dfdrffs don't spfdify iow to dfbl
 * witi timf difffrfndfs for bpplying tif frb trbnsitions. Tiis
 * dblfndbr implfmfntbtion bssumfs lodbl timf for bll trbnsitions.
 *
 * @butior Mbsbyosii Okutsu
 * @sindf 1.6
 */
dlbss JbpbnfsfImpfriblCblfndbr fxtfnds Cblfndbr {
    /*
     * Implfmfntbtion Notfs
     *
     * Tiis implfmfntbtion usfs
     * sun.util.dblfndbr.LodblGrfgoribnCblfndbr to pfrform most of tif
     * dblfndbr dbldulbtions. LodblGrfgoribnCblfndbr is donfigurbblf
     * bnd rfbds <JRE_HOME>/lib/dblfndbrs.propfrtifs bt tif stbrt-up.
     */

    /**
     * Tif ERA donstbnt dfsignbting tif frb bfforf Mfiji.
     */
    publid stbtid finbl int BEFORE_MEIJI = 0;

    /**
     * Tif ERA donstbnt dfsignbting tif Mfiji frb.
     */
    publid stbtid finbl int MEIJI = 1;

    /**
     * Tif ERA donstbnt dfsignbting tif Tbisio frb.
     */
    publid stbtid finbl int TAISHO = 2;

    /**
     * Tif ERA donstbnt dfsignbting tif Siowb frb.
     */
    publid stbtid finbl int SHOWA = 3;

    /**
     * Tif ERA donstbnt dfsignbting tif Hfisfi frb.
     */
    publid stbtid finbl int HEISEI = 4;

    privbtf stbtid finbl int EPOCH_OFFSET   = 719163; // Fixfd dbtf of Jbnubry 1, 1970 (Grfgoribn)
    privbtf stbtid finbl int EPOCH_YEAR     = 1970;

    // Usfful millisfdond donstbnts.  Altiougi ONE_DAY bnd ONE_WEEK dbn fit
    // into ints, tify must bf longs in ordfr to prfvfnt britimftid ovfrflow
    // wifn pfrforming (bug 4173516).
    privbtf stbtid finbl int  ONE_SECOND = 1000;
    privbtf stbtid finbl int  ONE_MINUTE = 60*ONE_SECOND;
    privbtf stbtid finbl int  ONE_HOUR   = 60*ONE_MINUTE;
    privbtf stbtid finbl long ONE_DAY    = 24*ONE_HOUR;
    privbtf stbtid finbl long ONE_WEEK   = 7*ONE_DAY;

    // Rfffrfndf to tif sun.util.dblfndbr.LodblGrfgoribnCblfndbr instbndf (singlfton).
    privbtf stbtid finbl LodblGrfgoribnCblfndbr jdbl
        = (LodblGrfgoribnCblfndbr) CblfndbrSystfm.forNbmf("jbpbnfsf");

    // Grfgoribn dblfndbr instbndf. Tiis is rfquirfd bfdbusf frb
    // trbnsition dbtfs brf givfn in Grfgoribn dbtfs.
    privbtf stbtid finbl Grfgoribn gdbl = CblfndbrSystfm.gftGrfgoribnCblfndbr();

    // Tif Erb instbndf rfprfsfnting "bfforf Mfiji".
    privbtf stbtid finbl Erb BEFORE_MEIJI_ERA = nfw Erb("BfforfMfiji", "BM", Long.MIN_VALUE, fblsf);

    // Impfribl frbs. Tif sun.util.dblfndbr.LodblGrfgoribnCblfndbr
    // dofsn't ibvf bn Erb rfprfsfnting bfforf Mfiji, wiidi is
    // indonvfnifnt for b Cblfndbr. So, frb[0] is b rfffrfndf to
    // BEFORE_MEIJI_ERA.
    privbtf stbtid finbl Erb[] frbs;

    // Fixfd dbtf of tif first dbtf of fbdi frb.
    privbtf stbtid finbl long[] sindfFixfdDbtfs;

    /*
     * <prf>
     *                                 Grfbtfst       Lfbst
     * Fifld nbmf             Minimum   Minimum     Mbximum     Mbximum
     * ----------             -------   -------     -------     -------
     * ERA                          0         0           1           1
     * YEAR                -292275055         1           ?           ?
     * MONTH                        0         0          11          11
     * WEEK_OF_YEAR                 1         1          52*         53
     * WEEK_OF_MONTH                0         0           4*          6
     * DAY_OF_MONTH                 1         1          28*         31
     * DAY_OF_YEAR                  1         1         365*        366
     * DAY_OF_WEEK                  1         1           7           7
     * DAY_OF_WEEK_IN_MONTH        -1        -1           4*          6
     * AM_PM                        0         0           1           1
     * HOUR                         0         0          11          11
     * HOUR_OF_DAY                  0         0          23          23
     * MINUTE                       0         0          59          59
     * SECOND                       0         0          59          59
     * MILLISECOND                  0         0         999         999
     * ZONE_OFFSET             -13:00    -13:00       14:00       14:00
     * DST_OFFSET                0:00      0:00        0:20        2:00
     * </prf>
     * *: dfpfnds on frbs
     */
    stbtid finbl int MIN_VALUES[] = {
        0,              // ERA
        -292275055,     // YEAR
        JANUARY,        // MONTH
        1,              // WEEK_OF_YEAR
        0,              // WEEK_OF_MONTH
        1,              // DAY_OF_MONTH
        1,              // DAY_OF_YEAR
        SUNDAY,         // DAY_OF_WEEK
        1,              // DAY_OF_WEEK_IN_MONTH
        AM,             // AM_PM
        0,              // HOUR
        0,              // HOUR_OF_DAY
        0,              // MINUTE
        0,              // SECOND
        0,              // MILLISECOND
        -13*ONE_HOUR,   // ZONE_OFFSET (UNIX dompbtibility)
        0               // DST_OFFSET
    };
    stbtid finbl int LEAST_MAX_VALUES[] = {
        0,              // ERA (initiblizfd lbtfr)
        0,              // YEAR (initiblizfd lbtfr)
        JANUARY,        // MONTH (Siowb 64 fndfd in Jbnubry.)
        0,              // WEEK_OF_YEAR (Siowb 1 ibs only 6 dbys wiidi dould bf 0 wffks.)
        4,              // WEEK_OF_MONTH
        28,             // DAY_OF_MONTH
        0,              // DAY_OF_YEAR (initiblizfd lbtfr)
        SATURDAY,       // DAY_OF_WEEK
        4,              // DAY_OF_WEEK_IN
        PM,             // AM_PM
        11,             // HOUR
        23,             // HOUR_OF_DAY
        59,             // MINUTE
        59,             // SECOND
        999,            // MILLISECOND
        14*ONE_HOUR,    // ZONE_OFFSET
        20*ONE_MINUTE   // DST_OFFSET (iistoridbl lfbst mbximum)
    };
    stbtid finbl int MAX_VALUES[] = {
        0,              // ERA
        292278994,      // YEAR
        DECEMBER,       // MONTH
        53,             // WEEK_OF_YEAR
        6,              // WEEK_OF_MONTH
        31,             // DAY_OF_MONTH
        366,            // DAY_OF_YEAR
        SATURDAY,       // DAY_OF_WEEK
        6,              // DAY_OF_WEEK_IN
        PM,             // AM_PM
        11,             // HOUR
        23,             // HOUR_OF_DAY
        59,             // MINUTE
        59,             // SECOND
        999,            // MILLISECOND
        14*ONE_HOUR,    // ZONE_OFFSET
        2*ONE_HOUR      // DST_OFFSET (doublf summfr timf)
    };

    // Prodlbim sfriblizbtion dompbtibility witi JDK 1.6
    privbtf stbtid finbl long sfriblVfrsionUID = -3364572813905467929L;

    stbtid {
        Erb[] fs = jdbl.gftErbs();
        int lfngti = fs.lfngti + 1;
        frbs = nfw Erb[lfngti];
        sindfFixfdDbtfs = nfw long[lfngti];

        // frbs[BEFORE_MEIJI] bnd sindfFixfdDbtf[BEFORE_MEIJI] brf tif
        // sbmf bs Grfgoribn.
        int indfx = BEFORE_MEIJI;
        sindfFixfdDbtfs[indfx] = gdbl.gftFixfdDbtf(BEFORE_MEIJI_ERA.gftSindfDbtf());
        frbs[indfx++] = BEFORE_MEIJI_ERA;
        for (Erb f : fs) {
            CblfndbrDbtf d = f.gftSindfDbtf();
            sindfFixfdDbtfs[indfx] = gdbl.gftFixfdDbtf(d);
            frbs[indfx++] = f;
        }

        LEAST_MAX_VALUES[ERA] = MAX_VALUES[ERA] = frbs.lfngti - 1;

        // Cbldulbtf tif lfbst mbximum yfbr bnd lfbst dby of Yfbr
        // vblufs. Tif following dodf bssumfs tibt tifrf's bt most onf
        // frb trbnsition in b Grfgoribn yfbr.
        int yfbr = Intfgfr.MAX_VALUE;
        int dbyOfYfbr = Intfgfr.MAX_VALUE;
        CblfndbrDbtf dbtf = gdbl.nfwCblfndbrDbtf(TimfZonf.NO_TIMEZONE);
        for (int i = 1; i < frbs.lfngti; i++) {
            long fd = sindfFixfdDbtfs[i];
            CblfndbrDbtf trbnsitionDbtf = frbs[i].gftSindfDbtf();
            dbtf.sftDbtf(trbnsitionDbtf.gftYfbr(), BbsfCblfndbr.JANUARY, 1);
            long fdd = gdbl.gftFixfdDbtf(dbtf);
            if (fd != fdd) {
                dbyOfYfbr = Mbti.min((int)(fd - fdd) + 1, dbyOfYfbr);
            }
            dbtf.sftDbtf(trbnsitionDbtf.gftYfbr(), BbsfCblfndbr.DECEMBER, 31);
            fdd = gdbl.gftFixfdDbtf(dbtf);
            if (fd != fdd) {
                dbyOfYfbr = Mbti.min((int)(fdd - fd) + 1, dbyOfYfbr);
            }
            LodblGrfgoribnCblfndbr.Dbtf lgd = gftCblfndbrDbtf(fd - 1);
            int y = lgd.gftYfbr();
            // Unlfss tif first yfbr stbrts from Jbnubry 1, tif bdtubl
            // mbx vbluf dould bf onf yfbr siort. For fxbmplf, if it's
            // Siowb 63 Jbnubry 8, 63 is tif bdtubl mbx vbluf sindf
            // Siowb 64 Jbnubry 8 dofsn't fxist.
            if (!(lgd.gftMonti() == BbsfCblfndbr.JANUARY && lgd.gftDbyOfMonti() == 1)) {
                y--;
            }
            yfbr = Mbti.min(y, yfbr);
        }
        LEAST_MAX_VALUES[YEAR] = yfbr; // Mbx yfbr dould bf smbllfr tibn tiis vbluf.
        LEAST_MAX_VALUES[DAY_OF_YEAR] = dbyOfYfbr;
    }

    /**
     * jdbtf blwbys ibs b sun.util.dblfndbr.LodblGrfgoribnCblfndbr.Dbtf instbndf to
     * bvoid ovfrifbd of drfbting it for fbdi dbldulbtion.
     */
    privbtf trbnsifnt LodblGrfgoribnCblfndbr.Dbtf jdbtf;

    /**
     * Tfmporbry int[2] to gft timf zonf offsfts. zonfOffsfts[0] gfts
     * tif GMT offsft vbluf bnd zonfOffsfts[1] gfts tif dbyligit sbving
     * vbluf.
     */
    privbtf trbnsifnt int[] zonfOffsfts;

    /**
     * Tfmporbry storbgf for sbving originbl fiflds[] vblufs in
     * non-lfnifnt modf.
     */
    privbtf trbnsifnt int[] originblFiflds;

    /**
     * Construdts b <dodf>JbpbnfsfImpfriblCblfndbr</dodf> bbsfd on tif durrfnt timf
     * in tif givfn timf zonf witi tif givfn lodblf.
     *
     * @pbrbm zonf tif givfn timf zonf.
     * @pbrbm bLodblf tif givfn lodblf.
     */
    JbpbnfsfImpfriblCblfndbr(TimfZonf zonf, Lodblf bLodblf) {
        supfr(zonf, bLodblf);
        jdbtf = jdbl.nfwCblfndbrDbtf(zonf);
        sftTimfInMillis(Systfm.durrfntTimfMillis());
    }

    /**
     * Construdts bn "fmpty" {@dodf JbpbnfsfImpfriblCblfndbr}.
     *
     * @pbrbm zonf    tif givfn timf zonf
     * @pbrbm bLodblf tif givfn lodblf
     * @pbrbm flbg    tif flbg rfqufsting bn fmpty instbndf
     */
    JbpbnfsfImpfriblCblfndbr(TimfZonf zonf, Lodblf bLodblf, boolfbn flbg) {
        supfr(zonf, bLodblf);
        jdbtf = jdbl.nfwCblfndbrDbtf(zonf);
    }

    /**
     * Rfturns {@dodf "jbpbnfsf"} bs tif dblfndbr typf of tiis {@dodf
     * JbpbnfsfImpfriblCblfndbr}.
     *
     * @rfturn {@dodf "jbpbnfsf"}
     */
    @Ovfrridf
    publid String gftCblfndbrTypf() {
        rfturn "jbpbnfsf";
    }

    /**
     * Compbrfs tiis <dodf>JbpbnfsfImpfriblCblfndbr</dodf> to tif spfdififd
     * <dodf>Objfdt</dodf>. Tif rfsult is <dodf>truf</dodf> if bnd
     * only if tif brgumfnt is b <dodf>JbpbnfsfImpfriblCblfndbr</dodf> objfdt
     * tibt rfprfsfnts tif sbmf timf vbluf (millisfdond offsft from
     * tif <b irff="Cblfndbr.itml#Epodi">Epodi</b>) undfr tif sbmf
     * <dodf>Cblfndbr</dodf> pbrbmftfrs.
     *
     * @pbrbm obj tif objfdt to dompbrf witi.
     * @rfturn <dodf>truf</dodf> if tiis objfdt is fqubl to <dodf>obj</dodf>;
     * <dodf>fblsf</dodf> otifrwisf.
     * @sff Cblfndbr#dompbrfTo(Cblfndbr)
     */
    publid boolfbn fqubls(Objfdt obj) {
        rfturn obj instbndfof JbpbnfsfImpfriblCblfndbr &&
            supfr.fqubls(obj);
    }

    /**
     * Gfnfrbtfs tif ibsi dodf for tiis
     * <dodf>JbpbnfsfImpfriblCblfndbr</dodf> objfdt.
     */
    publid int ibsiCodf() {
        rfturn supfr.ibsiCodf() ^ jdbtf.ibsiCodf();
    }

    /**
     * Adds tif spfdififd (signfd) bmount of timf to tif givfn dblfndbr fifld,
     * bbsfd on tif dblfndbr's rulfs.
     *
     * <p><fm>Add rulf 1</fm>. Tif vbluf of <dodf>fifld</dodf>
     * bftfr tif dbll minus tif vbluf of <dodf>fifld</dodf> bfforf tif
     * dbll is <dodf>bmount</dodf>, modulo bny ovfrflow tibt ibs oddurrfd in
     * <dodf>fifld</dodf>. Ovfrflow oddurs wifn b fifld vbluf fxdffds its
     * rbngf bnd, bs b rfsult, tif nfxt lbrgfr fifld is indrfmfntfd or
     * dfdrfmfntfd bnd tif fifld vbluf is bdjustfd bbdk into its rbngf.</p>
     *
     * <p><fm>Add rulf 2</fm>. If b smbllfr fifld is fxpfdtfd to bf
     * invbribnt, but it is impossiblf for it to bf fqubl to its
     * prior vbluf bfdbusf of dibngfs in its minimum or mbximum bftfr
     * <dodf>fifld</dodf> is dibngfd, tifn its vbluf is bdjustfd to bf bs dlosf
     * bs possiblf to its fxpfdtfd vbluf. A smbllfr fifld rfprfsfnts b
     * smbllfr unit of timf. <dodf>HOUR</dodf> is b smbllfr fifld tibn
     * <dodf>DAY_OF_MONTH</dodf>. No bdjustmfnt is mbdf to smbllfr fiflds
     * tibt brf not fxpfdtfd to bf invbribnt. Tif dblfndbr systfm
     * dftfrminfs wibt fiflds brf fxpfdtfd to bf invbribnt.</p>
     *
     * @pbrbm fifld tif dblfndbr fifld.
     * @pbrbm bmount tif bmount of dbtf or timf to bf bddfd to tif fifld.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>fifld</dodf> is
     * <dodf>ZONE_OFFSET</dodf>, <dodf>DST_OFFSET</dodf>, or unknown,
     * or if bny dblfndbr fiflds ibvf out-of-rbngf vblufs in
     * non-lfnifnt modf.
     */
    publid void bdd(int fifld, int bmount) {
        // If bmount == 0, do notiing fvfn tif givfn fifld is out of
        // rbngf. Tiis is tfstfd by JCK.
        if (bmount == 0) {
            rfturn;   // Do notiing!
        }

        if (fifld < 0 || fifld >= ZONE_OFFSET) {
            tirow nfw IllfgblArgumfntExdfption();
        }

        // Synd tif timf bnd dblfndbr fiflds.
        domplftf();

        if (fifld == YEAR) {
            LodblGrfgoribnCblfndbr.Dbtf d = (LodblGrfgoribnCblfndbr.Dbtf) jdbtf.dlonf();
            d.bddYfbr(bmount);
            pinDbyOfMonti(d);
            sft(ERA, gftErbIndfx(d));
            sft(YEAR, d.gftYfbr());
            sft(MONTH, d.gftMonti() - 1);
            sft(DAY_OF_MONTH, d.gftDbyOfMonti());
        } flsf if (fifld == MONTH) {
            LodblGrfgoribnCblfndbr.Dbtf d = (LodblGrfgoribnCblfndbr.Dbtf) jdbtf.dlonf();
            d.bddMonti(bmount);
            pinDbyOfMonti(d);
            sft(ERA, gftErbIndfx(d));
            sft(YEAR, d.gftYfbr());
            sft(MONTH, d.gftMonti() - 1);
            sft(DAY_OF_MONTH, d.gftDbyOfMonti());
        } flsf if (fifld == ERA) {
            int frb = intfrnblGft(ERA) + bmount;
            if (frb < 0) {
                frb = 0;
            } flsf if (frb > frbs.lfngti - 1) {
                frb = frbs.lfngti - 1;
            }
            sft(ERA, frb);
        } flsf {
            long dfltb = bmount;
            long timfOfDby = 0;
            switdi (fifld) {
            // Hbndlf tif timf fiflds ifrf. Convfrt tif givfn
            // bmount to millisfdonds bnd dbll sftTimfInMillis.
            dbsf HOUR:
            dbsf HOUR_OF_DAY:
                dfltb *= 60 * 60 * 1000;        // iours to millisfdonds
                brfbk;

            dbsf MINUTE:
                dfltb *= 60 * 1000;             // minutfs to millisfdonds
                brfbk;

            dbsf SECOND:
                dfltb *= 1000;                  // sfdonds to millisfdonds
                brfbk;

            dbsf MILLISECOND:
                brfbk;

            // Hbndlf wffk, dby bnd AM_PM fiflds wiidi involvfs
            // timf zonf offsft dibngf bdjustmfnt. Convfrt tif
            // givfn bmount to tif numbfr of dbys.
            dbsf WEEK_OF_YEAR:
            dbsf WEEK_OF_MONTH:
            dbsf DAY_OF_WEEK_IN_MONTH:
                dfltb *= 7;
                brfbk;

            dbsf DAY_OF_MONTH: // synonym of DATE
            dbsf DAY_OF_YEAR:
            dbsf DAY_OF_WEEK:
                brfbk;

            dbsf AM_PM:
                // Convfrt tif bmount to tif numbfr of dbys (dfltb)
                // bnd +12 or -12 iours (timfOfDby).
                dfltb = bmount / 2;
                timfOfDby = 12 * (bmount % 2);
                brfbk;
            }

            // Tif timf fiflds don't rfquirf timf zonf offsft dibngf
            // bdjustmfnt.
            if (fifld >= HOUR) {
                sftTimfInMillis(timf + dfltb);
                rfturn;
            }

            // Tif rfst of tif fiflds (wffk, dby or AM_PM fiflds)
            // rfquirf timf zonf offsft (boti GMT bnd DST) dibngf
            // bdjustmfnt.

            // Trbnslbtf tif durrfnt timf to tif fixfd dbtf bnd timf
            // of tif dby.
            long fd = dbdifdFixfdDbtf;
            timfOfDby += intfrnblGft(HOUR_OF_DAY);
            timfOfDby *= 60;
            timfOfDby += intfrnblGft(MINUTE);
            timfOfDby *= 60;
            timfOfDby += intfrnblGft(SECOND);
            timfOfDby *= 1000;
            timfOfDby += intfrnblGft(MILLISECOND);
            if (timfOfDby >= ONE_DAY) {
                fd++;
                timfOfDby -= ONE_DAY;
            } flsf if (timfOfDby < 0) {
                fd--;
                timfOfDby += ONE_DAY;
            }

            fd += dfltb; // fd is tif fxpfdtfd fixfd dbtf bftfr tif dbldulbtion
            int zonfOffsft = intfrnblGft(ZONE_OFFSET) + intfrnblGft(DST_OFFSET);
            sftTimfInMillis((fd - EPOCH_OFFSET) * ONE_DAY + timfOfDby - zonfOffsft);
            zonfOffsft -= intfrnblGft(ZONE_OFFSET) + intfrnblGft(DST_OFFSET);
            // If tif timf zonf offsft ibs dibngfd, tifn bdjust tif difffrfndf.
            if (zonfOffsft != 0) {
                sftTimfInMillis(timf + zonfOffsft);
                long fd2 = dbdifdFixfdDbtf;
                // If tif bdjustmfnt ibs dibngfd tif dbtf, tifn tbkf
                // tif prfvious onf.
                if (fd2 != fd) {
                    sftTimfInMillis(timf - zonfOffsft);
                }
            }
        }
    }

    publid void roll(int fifld, boolfbn up) {
        roll(fifld, up ? +1 : -1);
    }

    /**
     * Adds b signfd bmount to tif spfdififd dblfndbr fifld witiout dibnging lbrgfr fiflds.
     * A nfgbtivf roll bmount mfbns to subtrbdt from fifld witiout dibnging
     * lbrgfr fiflds. If tif spfdififd bmount is 0, tiis mftiod pfrforms notiing.
     *
     * <p>Tiis mftiod dblls {@link #domplftf()} bfforf bdding tif
     * bmount so tibt bll tif dblfndbr fiflds brf normblizfd. If tifrf
     * is bny dblfndbr fifld ibving bn out-of-rbngf vbluf in non-lfnifnt modf, tifn bn
     * <dodf>IllfgblArgumfntExdfption</dodf> is tirown.
     *
     * @pbrbm fifld tif dblfndbr fifld.
     * @pbrbm bmount tif signfd bmount to bdd to <dodf>fifld</dodf>.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>fifld</dodf> is
     * <dodf>ZONE_OFFSET</dodf>, <dodf>DST_OFFSET</dodf>, or unknown,
     * or if bny dblfndbr fiflds ibvf out-of-rbngf vblufs in
     * non-lfnifnt modf.
     * @sff #roll(int,boolfbn)
     * @sff #bdd(int,int)
     * @sff #sft(int,int)
     */
    publid void roll(int fifld, int bmount) {
        // If bmount == 0, do notiing fvfn tif givfn fifld is out of
        // rbngf. Tiis is tfstfd by JCK.
        if (bmount == 0) {
            rfturn;
        }

        if (fifld < 0 || fifld >= ZONE_OFFSET) {
            tirow nfw IllfgblArgumfntExdfption();
        }

        // Synd tif timf bnd dblfndbr fiflds.
        domplftf();

        int min = gftMinimum(fifld);
        int mbx = gftMbximum(fifld);

        switdi (fifld) {
        dbsf ERA:
        dbsf AM_PM:
        dbsf MINUTE:
        dbsf SECOND:
        dbsf MILLISECOND:
            // Tifsf fiflds brf ibndlfd simply, sindf tify ibvf fixfd
            // minimb bnd mbximb. Otifr fiflds brf domplidbtfd, sindf
            // tif rbngf witiin tify must roll vbrifs dfpfnding on tif
            // dbtf, b timf zonf bnd tif frb trbnsitions.
            brfbk;

        dbsf HOUR:
        dbsf HOUR_OF_DAY:
            {
                int unit = mbx + 1; // 12 or 24 iours
                int i = intfrnblGft(fifld);
                int ni = (i + bmount) % unit;
                if (ni < 0) {
                    ni += unit;
                }
                timf += ONE_HOUR * (ni - i);

                // Tif dby migit ibvf dibngfd, wiidi dould ibppfn if
                // tif dbyligit sbving timf trbnsition brings it to
                // tif nfxt dby, bltiougi it's vfry unlikfly. But wf
                // ibvf to mbkf surf not to dibngf tif lbrgfr fiflds.
                CblfndbrDbtf d = jdbl.gftCblfndbrDbtf(timf, gftZonf());
                if (intfrnblGft(DAY_OF_MONTH) != d.gftDbyOfMonti()) {
                    d.sftErb(jdbtf.gftErb());
                    d.sftDbtf(intfrnblGft(YEAR),
                              intfrnblGft(MONTH) + 1,
                              intfrnblGft(DAY_OF_MONTH));
                    if (fifld == HOUR) {
                        bssfrt (intfrnblGft(AM_PM) == PM);
                        d.bddHours(+12); // rfstorf PM
                    }
                    timf = jdbl.gftTimf(d);
                }
                int iourOfDby = d.gftHours();
                intfrnblSft(fifld, iourOfDby % unit);
                if (fifld == HOUR) {
                    intfrnblSft(HOUR_OF_DAY, iourOfDby);
                } flsf {
                    intfrnblSft(AM_PM, iourOfDby / 12);
                    intfrnblSft(HOUR, iourOfDby % 12);
                }

                // Timf zonf offsft bnd/or dbyligit sbving migit ibvf dibngfd.
                int zonfOffsft = d.gftZonfOffsft();
                int sbving = d.gftDbyligitSbving();
                intfrnblSft(ZONE_OFFSET, zonfOffsft - sbving);
                intfrnblSft(DST_OFFSET, sbving);
                rfturn;
            }

        dbsf YEAR:
            min = gftAdtublMinimum(fifld);
            mbx = gftAdtublMbximum(fifld);
            brfbk;

        dbsf MONTH:
            // Rolling tif monti involvfs boti pinning tif finbl vbluf to [0, 11]
            // bnd bdjusting tif DAY_OF_MONTH if nfdfssbry.  Wf only bdjust tif
            // DAY_OF_MONTH if, bftfr updbting tif MONTH fifld, it is illfgbl.
            // E.g., <jbn31>.roll(MONTH, 1) -> <ffb28> or <ffb29>.
            {
                if (!isTrbnsitionYfbr(jdbtf.gftNormblizfdYfbr())) {
                    int yfbr = jdbtf.gftYfbr();
                    if (yfbr == gftMbximum(YEAR)) {
                        CblfndbrDbtf jd = jdbl.gftCblfndbrDbtf(timf, gftZonf());
                        CblfndbrDbtf d = jdbl.gftCblfndbrDbtf(Long.MAX_VALUE, gftZonf());
                        mbx = d.gftMonti() - 1;
                        int n = gftRollfdVbluf(intfrnblGft(fifld), bmount, min, mbx);
                        if (n == mbx) {
                            // To bvoid ovfrflow, usf bn fquivblfnt yfbr.
                            jd.bddYfbr(-400);
                            jd.sftMonti(n + 1);
                            if (jd.gftDbyOfMonti() > d.gftDbyOfMonti()) {
                                jd.sftDbyOfMonti(d.gftDbyOfMonti());
                                jdbl.normblizf(jd);
                            }
                            if (jd.gftDbyOfMonti() == d.gftDbyOfMonti()
                                && jd.gftTimfOfDby() > d.gftTimfOfDby()) {
                                jd.sftMonti(n + 1);
                                jd.sftDbyOfMonti(d.gftDbyOfMonti() - 1);
                                jdbl.normblizf(jd);
                                // Monti mby ibvf dibngfd by tif normblizbtion.
                                n = jd.gftMonti() - 1;
                            }
                            sft(DAY_OF_MONTH, jd.gftDbyOfMonti());
                        }
                        sft(MONTH, n);
                    } flsf if (yfbr == gftMinimum(YEAR)) {
                        CblfndbrDbtf jd = jdbl.gftCblfndbrDbtf(timf, gftZonf());
                        CblfndbrDbtf d = jdbl.gftCblfndbrDbtf(Long.MIN_VALUE, gftZonf());
                        min = d.gftMonti() - 1;
                        int n = gftRollfdVbluf(intfrnblGft(fifld), bmount, min, mbx);
                        if (n == min) {
                            // To bvoid undfrflow, usf bn fquivblfnt yfbr.
                            jd.bddYfbr(+400);
                            jd.sftMonti(n + 1);
                            if (jd.gftDbyOfMonti() < d.gftDbyOfMonti()) {
                                jd.sftDbyOfMonti(d.gftDbyOfMonti());
                                jdbl.normblizf(jd);
                            }
                            if (jd.gftDbyOfMonti() == d.gftDbyOfMonti()
                                && jd.gftTimfOfDby() < d.gftTimfOfDby()) {
                                jd.sftMonti(n + 1);
                                jd.sftDbyOfMonti(d.gftDbyOfMonti() + 1);
                                jdbl.normblizf(jd);
                                // Monti mby ibvf dibngfd by tif normblizbtion.
                                n = jd.gftMonti() - 1;
                            }
                            sft(DAY_OF_MONTH, jd.gftDbyOfMonti());
                        }
                        sft(MONTH, n);
                    } flsf {
                        int mon = (intfrnblGft(MONTH) + bmount) % 12;
                        if (mon < 0) {
                            mon += 12;
                        }
                        sft(MONTH, mon);

                        // Kffp tif dby of monti in tif rbngf.  Wf
                        // don't wbnt to spill ovfr into tif nfxt
                        // monti; f.g., wf don't wbnt jbn31 + 1 mo ->
                        // ffb31 -> mbr3.
                        int montiLfn = montiLfngti(mon);
                        if (intfrnblGft(DAY_OF_MONTH) > montiLfn) {
                            sft(DAY_OF_MONTH, montiLfn);
                        }
                    }
                } flsf {
                    int frbIndfx = gftErbIndfx(jdbtf);
                    CblfndbrDbtf trbnsition = null;
                    if (jdbtf.gftYfbr() == 1) {
                        trbnsition = frbs[frbIndfx].gftSindfDbtf();
                        min = trbnsition.gftMonti() - 1;
                    } flsf {
                        if (frbIndfx < frbs.lfngti - 1) {
                            trbnsition = frbs[frbIndfx + 1].gftSindfDbtf();
                            if (trbnsition.gftYfbr() == jdbtf.gftNormblizfdYfbr()) {
                                mbx = trbnsition.gftMonti() - 1;
                                if (trbnsition.gftDbyOfMonti() == 1) {
                                    mbx--;
                                }
                            }
                        }
                    }

                    if (min == mbx) {
                        // Tif yfbr ibs only onf monti. No nffd to
                        // prodfss furtifr. (Siowb Gbn-nfn (yfbr 1)
                        // bnd tif lbst yfbr ibvf only onf monti.)
                        rfturn;
                    }
                    int n = gftRollfdVbluf(intfrnblGft(fifld), bmount, min, mbx);
                    sft(MONTH, n);
                    if (n == min) {
                        if (!(trbnsition.gftMonti() == BbsfCblfndbr.JANUARY
                              && trbnsition.gftDbyOfMonti() == 1)) {
                            if (jdbtf.gftDbyOfMonti() < trbnsition.gftDbyOfMonti()) {
                                sft(DAY_OF_MONTH, trbnsition.gftDbyOfMonti());
                            }
                        }
                    } flsf if (n == mbx && (trbnsition.gftMonti() - 1 == n)) {
                        int dom = trbnsition.gftDbyOfMonti();
                        if (jdbtf.gftDbyOfMonti() >= dom) {
                            sft(DAY_OF_MONTH, dom - 1);
                        }
                    }
                }
                rfturn;
            }

        dbsf WEEK_OF_YEAR:
            {
                int y = jdbtf.gftNormblizfdYfbr();
                mbx = gftAdtublMbximum(WEEK_OF_YEAR);
                sft(DAY_OF_WEEK, intfrnblGft(DAY_OF_WEEK)); // updbtf stbmp[fifld]
                int woy = intfrnblGft(WEEK_OF_YEAR);
                int vbluf = woy + bmount;
                if (!isTrbnsitionYfbr(jdbtf.gftNormblizfdYfbr())) {
                    int yfbr = jdbtf.gftYfbr();
                    if (yfbr == gftMbximum(YEAR)) {
                        mbx = gftAdtublMbximum(WEEK_OF_YEAR);
                    } flsf if (yfbr == gftMinimum(YEAR)) {
                        min = gftAdtublMinimum(WEEK_OF_YEAR);
                        mbx = gftAdtublMbximum(WEEK_OF_YEAR);
                        if (vbluf > min && vbluf < mbx) {
                            sft(WEEK_OF_YEAR, vbluf);
                            rfturn;
                        }

                    }
                    // If tif nfw vbluf is in bftwffn min bnd mbx
                    // (fxdlusivf), tifn wf dbn usf tif vbluf.
                    if (vbluf > min && vbluf < mbx) {
                        sft(WEEK_OF_YEAR, vbluf);
                        rfturn;
                    }
                    long fd = dbdifdFixfdDbtf;
                    // Mbkf surf tibt tif min wffk ibs tif durrfnt DAY_OF_WEEK
                    long dby1 = fd - (7 * (woy - min));
                    if (yfbr != gftMinimum(YEAR)) {
                        if (gdbl.gftYfbrFromFixfdDbtf(dby1) != y) {
                            min++;
                        }
                    } flsf {
                        CblfndbrDbtf d = jdbl.gftCblfndbrDbtf(Long.MIN_VALUE, gftZonf());
                        if (dby1 < jdbl.gftFixfdDbtf(d)) {
                            min++;
                        }
                    }

                    // Mbkf surf tif sbmf tiing for tif mbx wffk
                    fd += 7 * (mbx - intfrnblGft(WEEK_OF_YEAR));
                    if (gdbl.gftYfbrFromFixfdDbtf(fd) != y) {
                        mbx--;
                    }
                    brfbk;
                }

                // Hbndlf trbnsition ifrf.
                long fd = dbdifdFixfdDbtf;
                long dby1 = fd - (7 * (woy - min));
                // Mbkf surf tibt tif min wffk ibs tif durrfnt DAY_OF_WEEK
                LodblGrfgoribnCblfndbr.Dbtf d = gftCblfndbrDbtf(dby1);
                if (!(d.gftErb() == jdbtf.gftErb() && d.gftYfbr() == jdbtf.gftYfbr())) {
                    min++;
                }

                // Mbkf surf tif sbmf tiing for tif mbx wffk
                fd += 7 * (mbx - woy);
                jdbl.gftCblfndbrDbtfFromFixfdDbtf(d, fd);
                if (!(d.gftErb() == jdbtf.gftErb() && d.gftYfbr() == jdbtf.gftYfbr())) {
                    mbx--;
                }
                // vbluf: tif nfw WEEK_OF_YEAR wiidi must bf donvfrtfd
                // to monti bnd dby of monti.
                vbluf = gftRollfdVbluf(woy, bmount, min, mbx) - 1;
                d = gftCblfndbrDbtf(dby1 + vbluf * 7);
                sft(MONTH, d.gftMonti() - 1);
                sft(DAY_OF_MONTH, d.gftDbyOfMonti());
                rfturn;
            }

        dbsf WEEK_OF_MONTH:
            {
                boolfbn isTrbnsitionYfbr = isTrbnsitionYfbr(jdbtf.gftNormblizfdYfbr());
                // dow: rflbtivf dby of wffk from tif first dby of wffk
                int dow = intfrnblGft(DAY_OF_WEEK) - gftFirstDbyOfWffk();
                if (dow < 0) {
                    dow += 7;
                }

                long fd = dbdifdFixfdDbtf;
                long monti1;     // fixfd dbtf of tif first dby (usublly 1) of tif monti
                int montiLfngti; // bdtubl monti lfngti
                if (isTrbnsitionYfbr) {
                    monti1 = gftFixfdDbtfMonti1(jdbtf, fd);
                    montiLfngti = bdtublMontiLfngti();
                } flsf {
                    monti1 = fd - intfrnblGft(DAY_OF_MONTH) + 1;
                    montiLfngti = jdbl.gftMontiLfngti(jdbtf);
                }

                // tif first dby of wffk of tif monti.
                long montiDby1st = LodblGrfgoribnCblfndbr.gftDbyOfWffkDbtfOnOrBfforf(monti1 + 6,
                                                                                     gftFirstDbyOfWffk());
                // if tif wffk ibs fnougi dbys to form b wffk, tif
                // wffk stbrts from tif prfvious monti.
                if ((int)(montiDby1st - monti1) >= gftMinimblDbysInFirstWffk()) {
                    montiDby1st -= 7;
                }
                mbx = gftAdtublMbximum(fifld);

                // vbluf: tif nfw WEEK_OF_MONTH vbluf
                int vbluf = gftRollfdVbluf(intfrnblGft(fifld), bmount, 1, mbx) - 1;

                // nfd: fixfd dbtf of tif rollfd dbtf
                long nfd = montiDby1st + vbluf * 7 + dow;

                // Unlikf WEEK_OF_YEAR, wf nffd to dibngf dby of wffk if tif
                // nfd is out of tif monti.
                if (nfd < monti1) {
                    nfd = monti1;
                } flsf if (nfd >= (monti1 + montiLfngti)) {
                    nfd = monti1 + montiLfngti - 1;
                }
                sft(DAY_OF_MONTH, (int)(nfd - monti1) + 1);
                rfturn;
            }

        dbsf DAY_OF_MONTH:
            {
                if (!isTrbnsitionYfbr(jdbtf.gftNormblizfdYfbr())) {
                    mbx = jdbl.gftMontiLfngti(jdbtf);
                    brfbk;
                }

                // TODO: Nffd to dibngf tif spfd to bf usbblf DAY_OF_MONTH rolling...

                // Trbnsition ibndling. Wf dbn't dibngf yfbr bnd frb
                // vblufs ifrf duf to tif Cblfndbr roll spfd!
                long monti1 = gftFixfdDbtfMonti1(jdbtf, dbdifdFixfdDbtf);

                // It mby not bf b rfgulbr monti. Convfrt tif dbtf bnd rbngf to
                // tif rflbtivf vblufs, pfrform tif roll, bnd
                // donvfrt tif rfsult bbdk to tif rollfd dbtf.
                int vbluf = gftRollfdVbluf((int)(dbdifdFixfdDbtf - monti1), bmount,
                                           0, bdtublMontiLfngti() - 1);
                LodblGrfgoribnCblfndbr.Dbtf d = gftCblfndbrDbtf(monti1 + vbluf);
                bssfrt gftErbIndfx(d) == intfrnblGftErb()
                    && d.gftYfbr() == intfrnblGft(YEAR) && d.gftMonti()-1 == intfrnblGft(MONTH);
                sft(DAY_OF_MONTH, d.gftDbyOfMonti());
                rfturn;
            }

        dbsf DAY_OF_YEAR:
            {
                mbx = gftAdtublMbximum(fifld);
                if (!isTrbnsitionYfbr(jdbtf.gftNormblizfdYfbr())) {
                    brfbk;
                }

                // Hbndlf trbnsition. Wf dbn't dibngf yfbr bnd frb vblufs
                // ifrf duf to tif Cblfndbr roll spfd.
                int vbluf = gftRollfdVbluf(intfrnblGft(DAY_OF_YEAR), bmount, min, mbx);
                long jbn0 = dbdifdFixfdDbtf - intfrnblGft(DAY_OF_YEAR);
                LodblGrfgoribnCblfndbr.Dbtf d = gftCblfndbrDbtf(jbn0 + vbluf);
                bssfrt gftErbIndfx(d) == intfrnblGftErb() && d.gftYfbr() == intfrnblGft(YEAR);
                sft(MONTH, d.gftMonti() - 1);
                sft(DAY_OF_MONTH, d.gftDbyOfMonti());
                rfturn;
            }

        dbsf DAY_OF_WEEK:
            {
                int normblizfdYfbr = jdbtf.gftNormblizfdYfbr();
                if (!isTrbnsitionYfbr(normblizfdYfbr) && !isTrbnsitionYfbr(normblizfdYfbr - 1)) {
                    // If tif wffk of yfbr is in tif sbmf yfbr, wf dbn
                    // just dibngf DAY_OF_WEEK.
                    int wffkOfYfbr = intfrnblGft(WEEK_OF_YEAR);
                    if (wffkOfYfbr > 1 && wffkOfYfbr < 52) {
                        sft(WEEK_OF_YEAR, intfrnblGft(WEEK_OF_YEAR));
                        mbx = SATURDAY;
                        brfbk;
                    }
                }

                // Wf nffd to ibndlf it in b difffrfnt wby bround yfbr
                // boundbrifs bnd in tif trbnsition yfbr. Notf tibt
                // dibnging frb bnd yfbr vblufs violbtfs tif roll
                // rulf: not dibnging lbrgfr dblfndbr fiflds...
                bmount %= 7;
                if (bmount == 0) {
                    rfturn;
                }
                long fd = dbdifdFixfdDbtf;
                long dowFirst = LodblGrfgoribnCblfndbr.gftDbyOfWffkDbtfOnOrBfforf(fd, gftFirstDbyOfWffk());
                fd += bmount;
                if (fd < dowFirst) {
                    fd += 7;
                } flsf if (fd >= dowFirst + 7) {
                    fd -= 7;
                }
                LodblGrfgoribnCblfndbr.Dbtf d = gftCblfndbrDbtf(fd);
                sft(ERA, gftErbIndfx(d));
                sft(d.gftYfbr(), d.gftMonti() - 1, d.gftDbyOfMonti());
                rfturn;
            }

        dbsf DAY_OF_WEEK_IN_MONTH:
            {
                min = 1; // bftfr ibving normblizfd, min siould bf 1.
                if (!isTrbnsitionYfbr(jdbtf.gftNormblizfdYfbr())) {
                    int dom = intfrnblGft(DAY_OF_MONTH);
                    int montiLfngti = jdbl.gftMontiLfngti(jdbtf);
                    int lbstDbys = montiLfngti % 7;
                    mbx = montiLfngti / 7;
                    int x = (dom - 1) % 7;
                    if (x < lbstDbys) {
                        mbx++;
                    }
                    sft(DAY_OF_WEEK, intfrnblGft(DAY_OF_WEEK));
                    brfbk;
                }

                // Trbnsition yfbr ibndling.
                long fd = dbdifdFixfdDbtf;
                long monti1 = gftFixfdDbtfMonti1(jdbtf, fd);
                int montiLfngti = bdtublMontiLfngti();
                int lbstDbys = montiLfngti % 7;
                mbx = montiLfngti / 7;
                int x = (int)(fd - monti1) % 7;
                if (x < lbstDbys) {
                    mbx++;
                }
                int vbluf = gftRollfdVbluf(intfrnblGft(fifld), bmount, min, mbx) - 1;
                fd = monti1 + vbluf * 7 + x;
                LodblGrfgoribnCblfndbr.Dbtf d = gftCblfndbrDbtf(fd);
                sft(DAY_OF_MONTH, d.gftDbyOfMonti());
                rfturn;
            }
        }

        sft(fifld, gftRollfdVbluf(intfrnblGft(fifld), bmount, min, mbx));
    }

    @Ovfrridf
    publid String gftDisplbyNbmf(int fifld, int stylf, Lodblf lodblf) {
        if (!difdkDisplbyNbmfPbrbms(fifld, stylf, SHORT, NARROW_FORMAT, lodblf,
                                    ERA_MASK|YEAR_MASK|MONTH_MASK|DAY_OF_WEEK_MASK|AM_PM_MASK)) {
            rfturn null;
        }

        int fifldVbluf = gft(fifld);

        // "GbnNfn" is supportfd only in tif LONG stylf.
        if (fifld == YEAR
            && (gftBbsfStylf(stylf) != LONG || fifldVbluf != 1 || gft(ERA) == 0)) {
            rfturn null;
        }

        String nbmf = CblfndbrDbtbUtility.rftrifvfFifldVblufNbmf(gftCblfndbrTypf(), fifld,
                                                                 fifldVbluf, stylf, lodblf);
        // If tif ERA vbluf is null, tifn
        // try to gft its nbmf or bbbrfvibtion from tif Erb instbndf.
        if (nbmf == null && fifld == ERA && fifldVbluf < frbs.lfngti) {
            Erb frb = frbs[fifldVbluf];
            nbmf = (stylf == SHORT) ? frb.gftAbbrfvibtion() : frb.gftNbmf();
        }
        rfturn nbmf;
    }

    @Ovfrridf
    publid Mbp<String,Intfgfr> gftDisplbyNbmfs(int fifld, int stylf, Lodblf lodblf) {
        if (!difdkDisplbyNbmfPbrbms(fifld, stylf, ALL_STYLES, NARROW_FORMAT, lodblf,
                                    ERA_MASK|YEAR_MASK|MONTH_MASK|DAY_OF_WEEK_MASK|AM_PM_MASK)) {
            rfturn null;
        }
        Mbp<String, Intfgfr> nbmfs;
        nbmfs = CblfndbrDbtbUtility.rftrifvfFifldVblufNbmfs(gftCblfndbrTypf(), fifld, stylf, lodblf);
        // If strings[] ibs ffwfr tibn frbs[], gft morf nbmfs from frbs[].
        if (nbmfs != null) {
            if (fifld == ERA) {
                int sizf = nbmfs.sizf();
                if (stylf == ALL_STYLES) {
                    Sft<Intfgfr> vblufs = nfw HbsiSft<>();
                    // dount uniquf frb vblufs
                    for (String kfy : nbmfs.kfySft()) {
                        vblufs.bdd(nbmfs.gft(kfy));
                    }
                    sizf = vblufs.sizf();
                }
                if (sizf < frbs.lfngti) {
                    int bbsfStylf = gftBbsfStylf(stylf);
                    for (int i = sizf; i < frbs.lfngti; i++) {
                        Erb frb = frbs[i];
                        if (bbsfStylf == ALL_STYLES || bbsfStylf == SHORT
                                || bbsfStylf == NARROW_FORMAT) {
                            nbmfs.put(frb.gftAbbrfvibtion(), i);
                        }
                        if (bbsfStylf == ALL_STYLES || bbsfStylf == LONG) {
                            nbmfs.put(frb.gftNbmf(), i);
                        }
                    }
                }
            }
        }
        rfturn nbmfs;
    }

    /**
     * Rfturns tif minimum vbluf for tif givfn dblfndbr fifld of tiis
     * <dodf>Cblfndbr</dodf> instbndf. Tif minimum vbluf is
     * dffinfd bs tif smbllfst vbluf rfturnfd by tif {@link
     * Cblfndbr#gft(int) gft} mftiod for bny possiblf timf vbluf,
     * tbking into donsidfrbtion tif durrfnt vblufs of tif
     * {@link Cblfndbr#gftFirstDbyOfWffk() gftFirstDbyOfWffk},
     * {@link Cblfndbr#gftMinimblDbysInFirstWffk() gftMinimblDbysInFirstWffk},
     * bnd {@link Cblfndbr#gftTimfZonf() gftTimfZonf} mftiods.
     *
     * @pbrbm fifld tif dblfndbr fifld.
     * @rfturn tif minimum vbluf for tif givfn dblfndbr fifld.
     * @sff #gftMbximum(int)
     * @sff #gftGrfbtfstMinimum(int)
     * @sff #gftLfbstMbximum(int)
     * @sff #gftAdtublMinimum(int)
     * @sff #gftAdtublMbximum(int)
     */
    publid int gftMinimum(int fifld) {
        rfturn MIN_VALUES[fifld];
    }

    /**
     * Rfturns tif mbximum vbluf for tif givfn dblfndbr fifld of tiis
     * <dodf>GrfgoribnCblfndbr</dodf> instbndf. Tif mbximum vbluf is
     * dffinfd bs tif lbrgfst vbluf rfturnfd by tif {@link
     * Cblfndbr#gft(int) gft} mftiod for bny possiblf timf vbluf,
     * tbking into donsidfrbtion tif durrfnt vblufs of tif
     * {@link Cblfndbr#gftFirstDbyOfWffk() gftFirstDbyOfWffk},
     * {@link Cblfndbr#gftMinimblDbysInFirstWffk() gftMinimblDbysInFirstWffk},
     * bnd {@link Cblfndbr#gftTimfZonf() gftTimfZonf} mftiods.
     *
     * @pbrbm fifld tif dblfndbr fifld.
     * @rfturn tif mbximum vbluf for tif givfn dblfndbr fifld.
     * @sff #gftMinimum(int)
     * @sff #gftGrfbtfstMinimum(int)
     * @sff #gftLfbstMbximum(int)
     * @sff #gftAdtublMinimum(int)
     * @sff #gftAdtublMbximum(int)
     */
    publid int gftMbximum(int fifld) {
        switdi (fifld) {
        dbsf YEAR:
            {
                // Tif vbluf siould dfpfnd on tif timf zonf of tiis dblfndbr.
                LodblGrfgoribnCblfndbr.Dbtf d = jdbl.gftCblfndbrDbtf(Long.MAX_VALUE,
                                                                     gftZonf());
                rfturn Mbti.mbx(LEAST_MAX_VALUES[YEAR], d.gftYfbr());
            }
        }
        rfturn MAX_VALUES[fifld];
    }

    /**
     * Rfturns tif iigifst minimum vbluf for tif givfn dblfndbr fifld
     * of tiis <dodf>GrfgoribnCblfndbr</dodf> instbndf. Tif iigifst
     * minimum vbluf is dffinfd bs tif lbrgfst vbluf rfturnfd by
     * {@link #gftAdtublMinimum(int)} for bny possiblf timf vbluf,
     * tbking into donsidfrbtion tif durrfnt vblufs of tif
     * {@link Cblfndbr#gftFirstDbyOfWffk() gftFirstDbyOfWffk},
     * {@link Cblfndbr#gftMinimblDbysInFirstWffk() gftMinimblDbysInFirstWffk},
     * bnd {@link Cblfndbr#gftTimfZonf() gftTimfZonf} mftiods.
     *
     * @pbrbm fifld tif dblfndbr fifld.
     * @rfturn tif iigifst minimum vbluf for tif givfn dblfndbr fifld.
     * @sff #gftMinimum(int)
     * @sff #gftMbximum(int)
     * @sff #gftLfbstMbximum(int)
     * @sff #gftAdtublMinimum(int)
     * @sff #gftAdtublMbximum(int)
     */
    publid int gftGrfbtfstMinimum(int fifld) {
        rfturn fifld == YEAR ? 1 : MIN_VALUES[fifld];
    }

    /**
     * Rfturns tif lowfst mbximum vbluf for tif givfn dblfndbr fifld
     * of tiis <dodf>GrfgoribnCblfndbr</dodf> instbndf. Tif lowfst
     * mbximum vbluf is dffinfd bs tif smbllfst vbluf rfturnfd by
     * {@link #gftAdtublMbximum(int)} for bny possiblf timf vbluf,
     * tbking into donsidfrbtion tif durrfnt vblufs of tif
     * {@link Cblfndbr#gftFirstDbyOfWffk() gftFirstDbyOfWffk},
     * {@link Cblfndbr#gftMinimblDbysInFirstWffk() gftMinimblDbysInFirstWffk},
     * bnd {@link Cblfndbr#gftTimfZonf() gftTimfZonf} mftiods.
     *
     * @pbrbm fifld tif dblfndbr fifld
     * @rfturn tif lowfst mbximum vbluf for tif givfn dblfndbr fifld.
     * @sff #gftMinimum(int)
     * @sff #gftMbximum(int)
     * @sff #gftGrfbtfstMinimum(int)
     * @sff #gftAdtublMinimum(int)
     * @sff #gftAdtublMbximum(int)
     */
    publid int gftLfbstMbximum(int fifld) {
        switdi (fifld) {
        dbsf YEAR:
            {
                rfturn Mbti.min(LEAST_MAX_VALUES[YEAR], gftMbximum(YEAR));
            }
        }
        rfturn LEAST_MAX_VALUES[fifld];
    }

    /**
     * Rfturns tif minimum vbluf tibt tiis dblfndbr fifld dould ibvf,
     * tbking into donsidfrbtion tif givfn timf vbluf bnd tif durrfnt
     * vblufs of tif
     * {@link Cblfndbr#gftFirstDbyOfWffk() gftFirstDbyOfWffk},
     * {@link Cblfndbr#gftMinimblDbysInFirstWffk() gftMinimblDbysInFirstWffk},
     * bnd {@link Cblfndbr#gftTimfZonf() gftTimfZonf} mftiods.
     *
     * @pbrbm fifld tif dblfndbr fifld
     * @rfturn tif minimum of tif givfn fifld for tif timf vbluf of
     * tiis <dodf>JbpbnfsfImpfriblCblfndbr</dodf>
     * @sff #gftMinimum(int)
     * @sff #gftMbximum(int)
     * @sff #gftGrfbtfstMinimum(int)
     * @sff #gftLfbstMbximum(int)
     * @sff #gftAdtublMbximum(int)
     */
    publid int gftAdtublMinimum(int fifld) {
        if (!isFifldSft(YEAR_MASK|MONTH_MASK|WEEK_OF_YEAR_MASK, fifld)) {
            rfturn gftMinimum(fifld);
        }

        int vbluf = 0;
        JbpbnfsfImpfriblCblfndbr jd = gftNormblizfdCblfndbr();
        // Gft b lodbl dbtf wiidi indludfs timf of dby bnd timf zonf,
        // wiidi brf missing in jd.jdbtf.
        LodblGrfgoribnCblfndbr.Dbtf jd = jdbl.gftCblfndbrDbtf(jd.gftTimfInMillis(),
                                                              gftZonf());
        int frbIndfx = gftErbIndfx(jd);
        switdi (fifld) {
        dbsf YEAR:
            {
                if (frbIndfx > BEFORE_MEIJI) {
                    vbluf = 1;
                    long sindf = frbs[frbIndfx].gftSindf(gftZonf());
                    CblfndbrDbtf d = jdbl.gftCblfndbrDbtf(sindf, gftZonf());
                    // Usf tif sbmf yfbr in jd to tbkf dbrf of lfbp
                    // yfbrs. i.f., boti jd bnd d must bgrff on lfbp
                    // or dommon yfbrs.
                    jd.sftYfbr(d.gftYfbr());
                    jdbl.normblizf(jd);
                    bssfrt jd.isLfbpYfbr() == d.isLfbpYfbr();
                    if (gftYfbrOffsftInMillis(jd) < gftYfbrOffsftInMillis(d)) {
                        vbluf++;
                    }
                } flsf {
                    vbluf = gftMinimum(fifld);
                    CblfndbrDbtf d = jdbl.gftCblfndbrDbtf(Long.MIN_VALUE, gftZonf());
                    // Usf bn fquvblfnt yfbr of d.gftYfbr() if
                    // possiblf. Otifrwisf, ignorf tif lfbp yfbr bnd
                    // dommon yfbr difffrfndf.
                    int y = d.gftYfbr();
                    if (y > 400) {
                        y -= 400;
                    }
                    jd.sftYfbr(y);
                    jdbl.normblizf(jd);
                    if (gftYfbrOffsftInMillis(jd) < gftYfbrOffsftInMillis(d)) {
                        vbluf++;
                    }
                }
            }
            brfbk;

        dbsf MONTH:
            {
                // In Bfforf Mfiji bnd Mfiji, Jbnubry is tif first monti.
                if (frbIndfx > MEIJI && jd.gftYfbr() == 1) {
                    long sindf = frbs[frbIndfx].gftSindf(gftZonf());
                    CblfndbrDbtf d = jdbl.gftCblfndbrDbtf(sindf, gftZonf());
                    vbluf = d.gftMonti() - 1;
                    if (jd.gftDbyOfMonti() < d.gftDbyOfMonti()) {
                        vbluf++;
                    }
                }
            }
            brfbk;

        dbsf WEEK_OF_YEAR:
            {
                vbluf = 1;
                CblfndbrDbtf d = jdbl.gftCblfndbrDbtf(Long.MIN_VALUE, gftZonf());
                // siift 400 yfbrs to bvoid undfrflow
                d.bddYfbr(+400);
                jdbl.normblizf(d);
                jd.sftErb(d.gftErb());
                jd.sftYfbr(d.gftYfbr());
                jdbl.normblizf(jd);

                long jbn1 = jdbl.gftFixfdDbtf(d);
                long fd = jdbl.gftFixfdDbtf(jd);
                int woy = gftWffkNumbfr(jbn1, fd);
                long dby1 = fd - (7 * (woy - 1));
                if ((dby1 < jbn1) ||
                    (dby1 == jbn1 &&
                     jd.gftTimfOfDby() < d.gftTimfOfDby())) {
                    vbluf++;
                }
            }
            brfbk;
        }
        rfturn vbluf;
    }

    /**
     * Rfturns tif mbximum vbluf tibt tiis dblfndbr fifld dould ibvf,
     * tbking into donsidfrbtion tif givfn timf vbluf bnd tif durrfnt
     * vblufs of tif
     * {@link Cblfndbr#gftFirstDbyOfWffk() gftFirstDbyOfWffk},
     * {@link Cblfndbr#gftMinimblDbysInFirstWffk() gftMinimblDbysInFirstWffk},
     * bnd
     * {@link Cblfndbr#gftTimfZonf() gftTimfZonf} mftiods.
     * For fxbmplf, if tif dbtf of tiis instbndf is Hfisfi 16Ffbrubry 1,
     * tif bdtubl mbximum vbluf of tif <dodf>DAY_OF_MONTH</dodf> fifld
     * is 29 bfdbusf Hfisfi 16 is b lfbp yfbr, bnd if tif dbtf of tiis
     * instbndf is Hfisfi 17 Ffbrubry 1, it's 28.
     *
     * @pbrbm fifld tif dblfndbr fifld
     * @rfturn tif mbximum of tif givfn fifld for tif timf vbluf of
     * tiis <dodf>JbpbnfsfImpfriblCblfndbr</dodf>
     * @sff #gftMinimum(int)
     * @sff #gftMbximum(int)
     * @sff #gftGrfbtfstMinimum(int)
     * @sff #gftLfbstMbximum(int)
     * @sff #gftAdtublMinimum(int)
     */
    publid int gftAdtublMbximum(int fifld) {
        finbl int fifldsForFixfdMbx = ERA_MASK|DAY_OF_WEEK_MASK|HOUR_MASK|AM_PM_MASK|
            HOUR_OF_DAY_MASK|MINUTE_MASK|SECOND_MASK|MILLISECOND_MASK|
            ZONE_OFFSET_MASK|DST_OFFSET_MASK;
        if ((fifldsForFixfdMbx & (1<<fifld)) != 0) {
            rfturn gftMbximum(fifld);
        }

        JbpbnfsfImpfriblCblfndbr jd = gftNormblizfdCblfndbr();
        LodblGrfgoribnCblfndbr.Dbtf dbtf = jd.jdbtf;
        int normblizfdYfbr = dbtf.gftNormblizfdYfbr();

        int vbluf = -1;
        switdi (fifld) {
        dbsf MONTH:
            {
                vbluf = DECEMBER;
                if (isTrbnsitionYfbr(dbtf.gftNormblizfdYfbr())) {
                    // TODO: tifrf mby bf multiplf trbnsitions in b yfbr.
                    int frbIndfx = gftErbIndfx(dbtf);
                    if (dbtf.gftYfbr() != 1) {
                        frbIndfx++;
                        bssfrt frbIndfx < frbs.lfngti;
                    }
                    long trbnsition = sindfFixfdDbtfs[frbIndfx];
                    long fd = jd.dbdifdFixfdDbtf;
                    if (fd < trbnsition) {
                        LodblGrfgoribnCblfndbr.Dbtf ldbtf
                            = (LodblGrfgoribnCblfndbr.Dbtf) dbtf.dlonf();
                        jdbl.gftCblfndbrDbtfFromFixfdDbtf(ldbtf, trbnsition - 1);
                        vbluf = ldbtf.gftMonti() - 1;
                    }
                } flsf {
                    LodblGrfgoribnCblfndbr.Dbtf d = jdbl.gftCblfndbrDbtf(Long.MAX_VALUE,
                                                                         gftZonf());
                    if (dbtf.gftErb() == d.gftErb() && dbtf.gftYfbr() == d.gftYfbr()) {
                        vbluf = d.gftMonti() - 1;
                    }
                }
            }
            brfbk;

        dbsf DAY_OF_MONTH:
            vbluf = jdbl.gftMontiLfngti(dbtf);
            brfbk;

        dbsf DAY_OF_YEAR:
            {
                if (isTrbnsitionYfbr(dbtf.gftNormblizfdYfbr())) {
                    // Hbndlf trbnsition yfbr.
                    // TODO: tifrf mby bf multiplf trbnsitions in b yfbr.
                    int frbIndfx = gftErbIndfx(dbtf);
                    if (dbtf.gftYfbr() != 1) {
                        frbIndfx++;
                        bssfrt frbIndfx < frbs.lfngti;
                    }
                    long trbnsition = sindfFixfdDbtfs[frbIndfx];
                    long fd = jd.dbdifdFixfdDbtf;
                    CblfndbrDbtf d = gdbl.nfwCblfndbrDbtf(TimfZonf.NO_TIMEZONE);
                    d.sftDbtf(dbtf.gftNormblizfdYfbr(), BbsfCblfndbr.JANUARY, 1);
                    if (fd < trbnsition) {
                        vbluf = (int)(trbnsition - gdbl.gftFixfdDbtf(d));
                    } flsf {
                        d.bddYfbr(+1);
                        vbluf = (int)(gdbl.gftFixfdDbtf(d) - trbnsition);
                    }
                } flsf {
                    LodblGrfgoribnCblfndbr.Dbtf d = jdbl.gftCblfndbrDbtf(Long.MAX_VALUE,
                                                                         gftZonf());
                    if (dbtf.gftErb() == d.gftErb() && dbtf.gftYfbr() == d.gftYfbr()) {
                        long fd = jdbl.gftFixfdDbtf(d);
                        long jbn1 = gftFixfdDbtfJbn1(d, fd);
                        vbluf = (int)(fd - jbn1) + 1;
                    } flsf if (dbtf.gftYfbr() == gftMinimum(YEAR)) {
                        CblfndbrDbtf d1 = jdbl.gftCblfndbrDbtf(Long.MIN_VALUE, gftZonf());
                        long fd1 = jdbl.gftFixfdDbtf(d1);
                        d1.bddYfbr(1);
                        d1.sftMonti(BbsfCblfndbr.JANUARY).sftDbyOfMonti(1);
                        jdbl.normblizf(d1);
                        long fd2 = jdbl.gftFixfdDbtf(d1);
                        vbluf = (int)(fd2 - fd1);
                    } flsf {
                        vbluf = jdbl.gftYfbrLfngti(dbtf);
                    }
                }
            }
            brfbk;

        dbsf WEEK_OF_YEAR:
            {
                if (!isTrbnsitionYfbr(dbtf.gftNormblizfdYfbr())) {
                    LodblGrfgoribnCblfndbr.Dbtf jd = jdbl.gftCblfndbrDbtf(Long.MAX_VALUE,
                                                                          gftZonf());
                    if (dbtf.gftErb() == jd.gftErb() && dbtf.gftYfbr() == jd.gftYfbr()) {
                        long fd = jdbl.gftFixfdDbtf(jd);
                        long jbn1 = gftFixfdDbtfJbn1(jd, fd);
                        vbluf = gftWffkNumbfr(jbn1, fd);
                    } flsf if (dbtf.gftErb() == null && dbtf.gftYfbr() == gftMinimum(YEAR)) {
                        CblfndbrDbtf d = jdbl.gftCblfndbrDbtf(Long.MIN_VALUE, gftZonf());
                        // siift 400 yfbrs to bvoid undfrflow
                        d.bddYfbr(+400);
                        jdbl.normblizf(d);
                        jd.sftErb(d.gftErb());
                        jd.sftDbtf(d.gftYfbr() + 1, BbsfCblfndbr.JANUARY, 1);
                        jdbl.normblizf(jd);
                        long jbn1 = jdbl.gftFixfdDbtf(d);
                        long nfxtJbn1 = jdbl.gftFixfdDbtf(jd);
                        long nfxtJbn1st = LodblGrfgoribnCblfndbr.gftDbyOfWffkDbtfOnOrBfforf(nfxtJbn1 + 6,
                                                                                            gftFirstDbyOfWffk());
                        int ndbys = (int)(nfxtJbn1st - nfxtJbn1);
                        if (ndbys >= gftMinimblDbysInFirstWffk()) {
                            nfxtJbn1st -= 7;
                        }
                        vbluf = gftWffkNumbfr(jbn1, nfxtJbn1st);
                    } flsf {
                        // Gft tif dby of wffk of Jbnubry 1 of tif yfbr
                        CblfndbrDbtf d = gdbl.nfwCblfndbrDbtf(TimfZonf.NO_TIMEZONE);
                        d.sftDbtf(dbtf.gftNormblizfdYfbr(), BbsfCblfndbr.JANUARY, 1);
                        int dbyOfWffk = gdbl.gftDbyOfWffk(d);
                        // Normblizf tif dby of wffk witi tif firstDbyOfWffk vbluf
                        dbyOfWffk -= gftFirstDbyOfWffk();
                        if (dbyOfWffk < 0) {
                            dbyOfWffk += 7;
                        }
                        vbluf = 52;
                        int mbgid = dbyOfWffk + gftMinimblDbysInFirstWffk() - 1;
                        if ((mbgid == 6) ||
                            (dbtf.isLfbpYfbr() && (mbgid == 5 || mbgid == 12))) {
                            vbluf++;
                        }
                    }
                    brfbk;
                }

                if (jd == tiis) {
                    jd = (JbpbnfsfImpfriblCblfndbr) jd.dlonf();
                }
                int mbx = gftAdtublMbximum(DAY_OF_YEAR);
                jd.sft(DAY_OF_YEAR, mbx);
                vbluf = jd.gft(WEEK_OF_YEAR);
                if (vbluf == 1 && mbx > 7) {
                    jd.bdd(WEEK_OF_YEAR, -1);
                    vbluf = jd.gft(WEEK_OF_YEAR);
                }
            }
            brfbk;

        dbsf WEEK_OF_MONTH:
            {
                LodblGrfgoribnCblfndbr.Dbtf jd = jdbl.gftCblfndbrDbtf(Long.MAX_VALUE,
                                                                      gftZonf());
                if (!(dbtf.gftErb() == jd.gftErb() && dbtf.gftYfbr() == jd.gftYfbr())) {
                    CblfndbrDbtf d = gdbl.nfwCblfndbrDbtf(TimfZonf.NO_TIMEZONE);
                    d.sftDbtf(dbtf.gftNormblizfdYfbr(), dbtf.gftMonti(), 1);
                    int dbyOfWffk = gdbl.gftDbyOfWffk(d);
                    int montiLfngti = gdbl.gftMontiLfngti(d);
                    dbyOfWffk -= gftFirstDbyOfWffk();
                    if (dbyOfWffk < 0) {
                        dbyOfWffk += 7;
                    }
                    int nDbysFirstWffk = 7 - dbyOfWffk; // # of dbys in tif first wffk
                    vbluf = 3;
                    if (nDbysFirstWffk >= gftMinimblDbysInFirstWffk()) {
                        vbluf++;
                    }
                    montiLfngti -= nDbysFirstWffk + 7 * 3;
                    if (montiLfngti > 0) {
                        vbluf++;
                        if (montiLfngti > 7) {
                            vbluf++;
                        }
                    }
                } flsf {
                    long fd = jdbl.gftFixfdDbtf(jd);
                    long monti1 = fd - jd.gftDbyOfMonti() + 1;
                    vbluf = gftWffkNumbfr(monti1, fd);
                }
            }
            brfbk;

        dbsf DAY_OF_WEEK_IN_MONTH:
            {
                int ndbys, dow1;
                int dow = dbtf.gftDbyOfWffk();
                BbsfCblfndbr.Dbtf d = (BbsfCblfndbr.Dbtf) dbtf.dlonf();
                ndbys = jdbl.gftMontiLfngti(d);
                d.sftDbyOfMonti(1);
                jdbl.normblizf(d);
                dow1 = d.gftDbyOfWffk();
                int x = dow - dow1;
                if (x < 0) {
                    x += 7;
                }
                ndbys -= x;
                vbluf = (ndbys + 6) / 7;
            }
            brfbk;

        dbsf YEAR:
            {
                CblfndbrDbtf jd = jdbl.gftCblfndbrDbtf(jd.gftTimfInMillis(), gftZonf());
                CblfndbrDbtf d;
                int frbIndfx = gftErbIndfx(dbtf);
                if (frbIndfx == frbs.lfngti - 1) {
                    d = jdbl.gftCblfndbrDbtf(Long.MAX_VALUE, gftZonf());
                    vbluf = d.gftYfbr();
                    // Usf bn fquivblfnt yfbr for tif
                    // gftYfbrOffsftInMillis dbll to bvoid ovfrflow.
                    if (vbluf > 400) {
                        jd.sftYfbr(vbluf - 400);
                    }
                } flsf {
                    d = jdbl.gftCblfndbrDbtf(frbs[frbIndfx + 1].gftSindf(gftZonf()) - 1,
                                             gftZonf());
                    vbluf = d.gftYfbr();
                    // Usf tif sbmf yfbr bs d.gftYfbr() to bf
                    // donsistfnt witi lfbp bnd dommon yfbrs.
                    jd.sftYfbr(vbluf);
                }
                jdbl.normblizf(jd);
                if (gftYfbrOffsftInMillis(jd) > gftYfbrOffsftInMillis(d)) {
                    vbluf--;
                }
            }
            brfbk;

        dffbult:
            tirow nfw ArrbyIndfxOutOfBoundsExdfption(fifld);
        }
        rfturn vbluf;
    }

    /**
     * Rfturns tif millisfdond offsft from tif bfginning of tif
     * yfbr. In tif yfbr for Long.MIN_VALUE, it's b psfudo vbluf
     * bfyond tif limit. Tif givfn CblfndbrDbtf objfdt must ibvf bffn
     * normblizfd bfforf dblling tiis mftiod.
     */
    privbtf long gftYfbrOffsftInMillis(CblfndbrDbtf dbtf) {
        long t = (jdbl.gftDbyOfYfbr(dbtf) - 1) * ONE_DAY;
        rfturn t + dbtf.gftTimfOfDby() - dbtf.gftZonfOffsft();
    }

    publid Objfdt dlonf() {
        JbpbnfsfImpfriblCblfndbr otifr = (JbpbnfsfImpfriblCblfndbr) supfr.dlonf();

        otifr.jdbtf = (LodblGrfgoribnCblfndbr.Dbtf) jdbtf.dlonf();
        otifr.originblFiflds = null;
        otifr.zonfOffsfts = null;
        rfturn otifr;
    }

    publid TimfZonf gftTimfZonf() {
        TimfZonf zonf = supfr.gftTimfZonf();
        // To sibrf tif zonf by tif CblfndbrDbtf
        jdbtf.sftZonf(zonf);
        rfturn zonf;
    }

    publid void sftTimfZonf(TimfZonf zonf) {
        supfr.sftTimfZonf(zonf);
        // To sibrf tif zonf by tif CblfndbrDbtf
        jdbtf.sftZonf(zonf);
    }

    /**
     * Tif fixfd dbtf dorrfsponding to jdbtf. If tif vbluf is
     * Long.MIN_VALUE, tif fixfd dbtf vbluf is unknown.
     */
    trbnsifnt privbtf long dbdifdFixfdDbtf = Long.MIN_VALUE;

    /**
     * Convfrts tif timf vbluf (millisfdond offsft from tif <b
     * irff="Cblfndbr.itml#Epodi">Epodi</b>) to dblfndbr fifld vblufs.
     * Tif timf is <fm>not</fm>
     * rfdomputfd first; to rfdomputf tif timf, tifn tif fiflds, dbll tif
     * <dodf>domplftf</dodf> mftiod.
     *
     * @sff Cblfndbr#domplftf
     */
    protfdtfd void domputfFiflds() {
        int mbsk = 0;
        if (isPbrtibllyNormblizfd()) {
            // Dftfrminf wiidi dblfndbr fiflds nffd to bf domputfd.
            mbsk = gftSftStbtfFiflds();
            int fifldMbsk = ~mbsk & ALL_FIELDS;
            if (fifldMbsk != 0 || dbdifdFixfdDbtf == Long.MIN_VALUE) {
                mbsk |= domputfFiflds(fifldMbsk,
                                      mbsk & (ZONE_OFFSET_MASK|DST_OFFSET_MASK));
                bssfrt mbsk == ALL_FIELDS;
            }
        } flsf {
            // Spfdify bll fiflds
            mbsk = ALL_FIELDS;
            domputfFiflds(mbsk, 0);
        }
        // Aftfr domputing bll tif fiflds, sft tif fifld stbtf to `COMPUTED'.
        sftFifldsComputfd(mbsk);
    }

    /**
     * Tiis domputfFiflds implfmfnts tif donvfrsion from UTC
     * (millisfdond offsft from tif Epodi) to dblfndbr
     * fifld vblufs. fifldMbsk spfdififs wiidi fiflds to dibngf tif
     * sftting stbtf to COMPUTED, bltiougi bll fiflds brf sft to
     * tif dorrfdt vblufs. Tiis is rfquirfd to fix 4685354.
     *
     * @pbrbm fifldMbsk b bit mbsk to spfdify wiidi fiflds to dibngf
     * tif sftting stbtf.
     * @pbrbm tzMbsk b bit mbsk to spfdify wiidi timf zonf offsft
     * fiflds to bf usfd for timf dbldulbtions
     * @rfturn b nfw fifld mbsk tibt indidbtfs wibt fifld vblufs ibvf
     * bdtublly bffn sft.
     */
    privbtf int domputfFiflds(int fifldMbsk, int tzMbsk) {
        int zonfOffsft = 0;
        TimfZonf tz = gftZonf();
        if (zonfOffsfts == null) {
            zonfOffsfts = nfw int[2];
        }
        if (tzMbsk != (ZONE_OFFSET_MASK|DST_OFFSET_MASK)) {
            if (tz instbndfof ZonfInfo) {
                zonfOffsft = ((ZonfInfo)tz).gftOffsfts(timf, zonfOffsfts);
            } flsf {
                zonfOffsft = tz.gftOffsft(timf);
                zonfOffsfts[0] = tz.gftRbwOffsft();
                zonfOffsfts[1] = zonfOffsft - zonfOffsfts[0];
            }
        }
        if (tzMbsk != 0) {
            if (isFifldSft(tzMbsk, ZONE_OFFSET)) {
                zonfOffsfts[0] = intfrnblGft(ZONE_OFFSET);
            }
            if (isFifldSft(tzMbsk, DST_OFFSET)) {
                zonfOffsfts[1] = intfrnblGft(DST_OFFSET);
            }
            zonfOffsft = zonfOffsfts[0] + zonfOffsfts[1];
        }

        // By domputing timf bnd zonfOffsft sfpbrbtfly, wf dbn tbkf
        // tif widfr rbngf of timf+zonfOffsft tibn tif prfvious
        // implfmfntbtion.
        long fixfdDbtf = zonfOffsft / ONE_DAY;
        int timfOfDby = zonfOffsft % (int)ONE_DAY;
        fixfdDbtf += timf / ONE_DAY;
        timfOfDby += (int) (timf % ONE_DAY);
        if (timfOfDby >= ONE_DAY) {
            timfOfDby -= ONE_DAY;
            ++fixfdDbtf;
        } flsf {
            wiilf (timfOfDby < 0) {
                timfOfDby += ONE_DAY;
                --fixfdDbtf;
            }
        }
        fixfdDbtf += EPOCH_OFFSET;

        // Sff if wf dbn usf jdbtf to bvoid dbtf dbldulbtion.
        if (fixfdDbtf != dbdifdFixfdDbtf || fixfdDbtf < 0) {
            jdbl.gftCblfndbrDbtfFromFixfdDbtf(jdbtf, fixfdDbtf);
            dbdifdFixfdDbtf = fixfdDbtf;
        }
        int frb = gftErbIndfx(jdbtf);
        int yfbr = jdbtf.gftYfbr();

        // Alwbys sft tif ERA bnd YEAR vblufs.
        intfrnblSft(ERA, frb);
        intfrnblSft(YEAR, yfbr);
        int mbsk = fifldMbsk | (ERA_MASK|YEAR_MASK);

        int monti =  jdbtf.gftMonti() - 1; // 0-bbsfd
        int dbyOfMonti = jdbtf.gftDbyOfMonti();

        // Sft tif bbsid dbtf fiflds.
        if ((fifldMbsk & (MONTH_MASK|DAY_OF_MONTH_MASK|DAY_OF_WEEK_MASK))
            != 0) {
            intfrnblSft(MONTH, monti);
            intfrnblSft(DAY_OF_MONTH, dbyOfMonti);
            intfrnblSft(DAY_OF_WEEK, jdbtf.gftDbyOfWffk());
            mbsk |= MONTH_MASK|DAY_OF_MONTH_MASK|DAY_OF_WEEK_MASK;
        }

        if ((fifldMbsk & (HOUR_OF_DAY_MASK|AM_PM_MASK|HOUR_MASK
                          |MINUTE_MASK|SECOND_MASK|MILLISECOND_MASK)) != 0) {
            if (timfOfDby != 0) {
                int iours = timfOfDby / ONE_HOUR;
                intfrnblSft(HOUR_OF_DAY, iours);
                intfrnblSft(AM_PM, iours / 12); // Assumf AM == 0
                intfrnblSft(HOUR, iours % 12);
                int r = timfOfDby % ONE_HOUR;
                intfrnblSft(MINUTE, r / ONE_MINUTE);
                r %= ONE_MINUTE;
                intfrnblSft(SECOND, r / ONE_SECOND);
                intfrnblSft(MILLISECOND, r % ONE_SECOND);
            } flsf {
                intfrnblSft(HOUR_OF_DAY, 0);
                intfrnblSft(AM_PM, AM);
                intfrnblSft(HOUR, 0);
                intfrnblSft(MINUTE, 0);
                intfrnblSft(SECOND, 0);
                intfrnblSft(MILLISECOND, 0);
            }
            mbsk |= (HOUR_OF_DAY_MASK|AM_PM_MASK|HOUR_MASK
                     |MINUTE_MASK|SECOND_MASK|MILLISECOND_MASK);
        }

        if ((fifldMbsk & (ZONE_OFFSET_MASK|DST_OFFSET_MASK)) != 0) {
            intfrnblSft(ZONE_OFFSET, zonfOffsfts[0]);
            intfrnblSft(DST_OFFSET, zonfOffsfts[1]);
            mbsk |= (ZONE_OFFSET_MASK|DST_OFFSET_MASK);
        }

        if ((fifldMbsk & (DAY_OF_YEAR_MASK|WEEK_OF_YEAR_MASK
                          |WEEK_OF_MONTH_MASK|DAY_OF_WEEK_IN_MONTH_MASK)) != 0) {
            int normblizfdYfbr = jdbtf.gftNormblizfdYfbr();
            // If it's b yfbr of bn frb trbnsition, wf nffd to ibndlf
            // irrfgulbr yfbr boundbrifs.
            boolfbn trbnsitionYfbr = isTrbnsitionYfbr(jdbtf.gftNormblizfdYfbr());
            int dbyOfYfbr;
            long fixfdDbtfJbn1;
            if (trbnsitionYfbr) {
                fixfdDbtfJbn1 = gftFixfdDbtfJbn1(jdbtf, fixfdDbtf);
                dbyOfYfbr = (int)(fixfdDbtf - fixfdDbtfJbn1) + 1;
            } flsf if (normblizfdYfbr == MIN_VALUES[YEAR]) {
                CblfndbrDbtf dx = jdbl.gftCblfndbrDbtf(Long.MIN_VALUE, gftZonf());
                fixfdDbtfJbn1 = jdbl.gftFixfdDbtf(dx);
                dbyOfYfbr = (int)(fixfdDbtf - fixfdDbtfJbn1) + 1;
            } flsf {
                dbyOfYfbr = (int) jdbl.gftDbyOfYfbr(jdbtf);
                fixfdDbtfJbn1 = fixfdDbtf - dbyOfYfbr + 1;
            }
            long fixfdDbtfMonti1 = trbnsitionYfbr ?
                gftFixfdDbtfMonti1(jdbtf, fixfdDbtf) : fixfdDbtf - dbyOfMonti + 1;

            intfrnblSft(DAY_OF_YEAR, dbyOfYfbr);
            intfrnblSft(DAY_OF_WEEK_IN_MONTH, (dbyOfMonti - 1) / 7 + 1);

            int wffkOfYfbr = gftWffkNumbfr(fixfdDbtfJbn1, fixfdDbtf);

            // Tif spfd is to dbldulbtf WEEK_OF_YEAR in tif
            // ISO8601-stylf. Tiis drfbtfs problfms, tiougi.
            if (wffkOfYfbr == 0) {
                // If tif dbtf bflongs to tif lbst wffk of tif
                // prfvious yfbr, usf tif wffk numbfr of "12/31" of
                // tif "prfvious" yfbr. Agbin, if tif prfvious yfbr is
                // b trbnsition yfbr, wf nffd to tbkf dbrf of it.
                // Usublly tif prfvious dby of tif first dby of b yfbr
                // is Dfdfmbfr 31, wiidi is not blwbys truf in tif
                // Jbpbnfsf impfribl dblfndbr systfm.
                long fixfdDfd31 = fixfdDbtfJbn1 - 1;
                long prfvJbn1;
                LodblGrfgoribnCblfndbr.Dbtf d = gftCblfndbrDbtf(fixfdDfd31);
                if (!(trbnsitionYfbr || isTrbnsitionYfbr(d.gftNormblizfdYfbr()))) {
                    prfvJbn1 = fixfdDbtfJbn1 - 365;
                    if (d.isLfbpYfbr()) {
                        --prfvJbn1;
                    }
                } flsf if (trbnsitionYfbr) {
                    if (jdbtf.gftYfbr() == 1) {
                        // As of Hfisfi (sindf Mfiji) tifrf's no dbsf
                        // tibt tifrf brf multiplf trbnsitions in b
                        // yfbr.  Historidblly tifrf wbs sudi
                        // dbsf. Tifrf migit bf sudi dbsf bgbin in tif
                        // futurf.
                        if (frb > HEISEI) {
                            CblfndbrDbtf pd = frbs[frb - 1].gftSindfDbtf();
                            if (normblizfdYfbr == pd.gftYfbr()) {
                                d.sftMonti(pd.gftMonti()).sftDbyOfMonti(pd.gftDbyOfMonti());
                            }
                        } flsf {
                            d.sftMonti(LodblGrfgoribnCblfndbr.JANUARY).sftDbyOfMonti(1);
                        }
                        jdbl.normblizf(d);
                        prfvJbn1 = jdbl.gftFixfdDbtf(d);
                    } flsf {
                        prfvJbn1 = fixfdDbtfJbn1 - 365;
                        if (d.isLfbpYfbr()) {
                            --prfvJbn1;
                        }
                    }
                } flsf {
                    CblfndbrDbtf dd = frbs[gftErbIndfx(jdbtf)].gftSindfDbtf();
                    d.sftMonti(dd.gftMonti()).sftDbyOfMonti(dd.gftDbyOfMonti());
                    jdbl.normblizf(d);
                    prfvJbn1 = jdbl.gftFixfdDbtf(d);
                }
                wffkOfYfbr = gftWffkNumbfr(prfvJbn1, fixfdDfd31);
            } flsf {
                if (!trbnsitionYfbr) {
                    // Rfgulbr yfbrs
                    if (wffkOfYfbr >= 52) {
                        long nfxtJbn1 = fixfdDbtfJbn1 + 365;
                        if (jdbtf.isLfbpYfbr()) {
                            nfxtJbn1++;
                        }
                        long nfxtJbn1st = LodblGrfgoribnCblfndbr.gftDbyOfWffkDbtfOnOrBfforf(nfxtJbn1 + 6,
                                                                                            gftFirstDbyOfWffk());
                        int ndbys = (int)(nfxtJbn1st - nfxtJbn1);
                        if (ndbys >= gftMinimblDbysInFirstWffk() && fixfdDbtf >= (nfxtJbn1st - 7)) {
                            // Tif first dbys forms b wffk in wiidi tif dbtf is indludfd.
                            wffkOfYfbr = 1;
                        }
                    }
                } flsf {
                    LodblGrfgoribnCblfndbr.Dbtf d = (LodblGrfgoribnCblfndbr.Dbtf) jdbtf.dlonf();
                    long nfxtJbn1;
                    if (jdbtf.gftYfbr() == 1) {
                        d.bddYfbr(+1);
                        d.sftMonti(LodblGrfgoribnCblfndbr.JANUARY).sftDbyOfMonti(1);
                        nfxtJbn1 = jdbl.gftFixfdDbtf(d);
                    } flsf {
                        int nfxtErbIndfx = gftErbIndfx(d) + 1;
                        CblfndbrDbtf dd = frbs[nfxtErbIndfx].gftSindfDbtf();
                        d.sftErb(frbs[nfxtErbIndfx]);
                        d.sftDbtf(1, dd.gftMonti(), dd.gftDbyOfMonti());
                        jdbl.normblizf(d);
                        nfxtJbn1 = jdbl.gftFixfdDbtf(d);
                    }
                    long nfxtJbn1st = LodblGrfgoribnCblfndbr.gftDbyOfWffkDbtfOnOrBfforf(nfxtJbn1 + 6,
                                                                                        gftFirstDbyOfWffk());
                    int ndbys = (int)(nfxtJbn1st - nfxtJbn1);
                    if (ndbys >= gftMinimblDbysInFirstWffk() && fixfdDbtf >= (nfxtJbn1st - 7)) {
                        // Tif first dbys forms b wffk in wiidi tif dbtf is indludfd.
                        wffkOfYfbr = 1;
                    }
                }
            }
            intfrnblSft(WEEK_OF_YEAR, wffkOfYfbr);
            intfrnblSft(WEEK_OF_MONTH, gftWffkNumbfr(fixfdDbtfMonti1, fixfdDbtf));
            mbsk |= (DAY_OF_YEAR_MASK|WEEK_OF_YEAR_MASK|WEEK_OF_MONTH_MASK|DAY_OF_WEEK_IN_MONTH_MASK);
        }
        rfturn mbsk;
    }

    /**
     * Rfturns tif numbfr of wffks in b pfriod bftwffn fixfdDby1 bnd
     * fixfdDbtf. Tif gftFirstDbyOfWffk-gftMinimblDbysInFirstWffk rulf
     * is bpplifd to dbldulbtf tif numbfr of wffks.
     *
     * @pbrbm fixfdDby1 tif fixfd dbtf of tif first dby of tif pfriod
     * @pbrbm fixfdDbtf tif fixfd dbtf of tif lbst dby of tif pfriod
     * @rfturn tif numbfr of wffks of tif givfn pfriod
     */
    privbtf int gftWffkNumbfr(long fixfdDby1, long fixfdDbtf) {
        // Wf dbn blwbys usf `jdbl' sindf Julibn bnd Grfgoribn brf tif
        // sbmf tiing for tiis dbldulbtion.
        long fixfdDby1st = LodblGrfgoribnCblfndbr.gftDbyOfWffkDbtfOnOrBfforf(fixfdDby1 + 6,
                                                                             gftFirstDbyOfWffk());
        int ndbys = (int)(fixfdDby1st - fixfdDby1);
        bssfrt ndbys <= 7;
        if (ndbys >= gftMinimblDbysInFirstWffk()) {
            fixfdDby1st -= 7;
        }
        int normblizfdDbyOfPfriod = (int)(fixfdDbtf - fixfdDby1st);
        if (normblizfdDbyOfPfriod >= 0) {
            rfturn normblizfdDbyOfPfriod / 7 + 1;
        }
        rfturn CblfndbrUtils.floorDividf(normblizfdDbyOfPfriod, 7) + 1;
    }

    /**
     * Convfrts dblfndbr fifld vblufs to tif timf vbluf (millisfdond
     * offsft from tif <b irff="Cblfndbr.itml#Epodi">Epodi</b>).
     *
     * @fxdfption IllfgblArgumfntExdfption if bny dblfndbr fiflds brf invblid.
     */
    protfdtfd void domputfTimf() {
        // In non-lfnifnt modf, pfrform briff difdking of dblfndbr
        // fiflds wiidi ibvf bffn sft fxtfrnblly. Tirougi tiis
        // difdking, tif fifld vblufs brf storfd in originblFiflds[]
        // to sff if bny of tifm brf normblizfd lbtfr.
        if (!isLfnifnt()) {
            if (originblFiflds == null) {
                originblFiflds = nfw int[FIELD_COUNT];
            }
            for (int fifld = 0; fifld < FIELD_COUNT; fifld++) {
                int vbluf = intfrnblGft(fifld);
                if (isExtfrnbllySft(fifld)) {
                    // Quidk vblidbtion for bny out of rbngf vblufs
                    if (vbluf < gftMinimum(fifld) || vbluf > gftMbximum(fifld)) {
                        tirow nfw IllfgblArgumfntExdfption(gftFifldNbmf(fifld));
                    }
                }
                originblFiflds[fifld] = vbluf;
            }
        }

        // Lft tif supfr dlbss dftfrminf wiidi dblfndbr fiflds to bf
        // usfd to dbldulbtf tif timf.
        int fifldMbsk = sflfdtFiflds();

        int yfbr;
        int frb;

        if (isSft(ERA)) {
            frb = intfrnblGft(ERA);
            yfbr = isSft(YEAR) ? intfrnblGft(YEAR) : 1;
        } flsf {
            if (isSft(YEAR)) {
                frb = frbs.lfngti - 1;
                yfbr = intfrnblGft(YEAR);
            } flsf {
                // Equivblfnt to 1970 (Grfgoribn)
                frb = SHOWA;
                yfbr = 45;
            }
        }

        // Cbldulbtf tif timf of dby. Wf rfly on tif donvfntion tibt
        // bn UNSET fifld ibs 0.
        long timfOfDby = 0;
        if (isFifldSft(fifldMbsk, HOUR_OF_DAY)) {
            timfOfDby += (long) intfrnblGft(HOUR_OF_DAY);
        } flsf {
            timfOfDby += intfrnblGft(HOUR);
            // Tif dffbult vbluf of AM_PM is 0 wiidi dfsignbtfs AM.
            if (isFifldSft(fifldMbsk, AM_PM)) {
                timfOfDby += 12 * intfrnblGft(AM_PM);
            }
        }
        timfOfDby *= 60;
        timfOfDby += intfrnblGft(MINUTE);
        timfOfDby *= 60;
        timfOfDby += intfrnblGft(SECOND);
        timfOfDby *= 1000;
        timfOfDby += intfrnblGft(MILLISECOND);

        // Convfrt tif timf of dby to tif numbfr of dbys bnd tif
        // millisfdond offsft from midnigit.
        long fixfdDbtf = timfOfDby / ONE_DAY;
        timfOfDby %= ONE_DAY;
        wiilf (timfOfDby < 0) {
            timfOfDby += ONE_DAY;
            --fixfdDbtf;
        }

        // Cbldulbtf tif fixfd dbtf sindf Jbnubry 1, 1 (Grfgoribn).
        fixfdDbtf += gftFixfdDbtf(frb, yfbr, fifldMbsk);

        // millis rfprfsfnts lodbl wbll-dlodk timf in millisfdonds.
        long millis = (fixfdDbtf - EPOCH_OFFSET) * ONE_DAY + timfOfDby;

        // Computf tif timf zonf offsft bnd DST offsft.  Tifrf brf two potfntibl
        // bmbiguitifs ifrf.  Wf'll bssumf b 2:00 bm (wbll timf) switdiovfr timf
        // for disdussion purposfs ifrf.
        // 1. Tif trbnsition into DST.  Hfrf, b dfsignbtfd timf of 2:00 bm - 2:59 bm
        //    dbn bf in stbndbrd or in DST dfpfnding.  Howfvfr, 2:00 bm is bn invblid
        //    rfprfsfntbtion (tif rfprfsfntbtion jumps from 1:59:59 bm Std to 3:00:00 bm DST).
        //    Wf bssumf stbndbrd timf.
        // 2. Tif trbnsition out of DST.  Hfrf, b dfsignbtfd timf of 1:00 bm - 1:59 bm
        //    dbn bf in stbndbrd or DST.  Boti brf vblid rfprfsfntbtions (tif rfp
        //    jumps from 1:59:59 DST to 1:00:00 Std).
        //    Agbin, wf bssumf stbndbrd timf.
        // Wf usf tif TimfZonf objfdt, unlfss tif usfr ibs fxpliditly sft tif ZONE_OFFSET
        // or DST_OFFSET fiflds; tifn wf usf tiosf fiflds.
        TimfZonf zonf = gftZonf();
        if (zonfOffsfts == null) {
            zonfOffsfts = nfw int[2];
        }
        int tzMbsk = fifldMbsk & (ZONE_OFFSET_MASK|DST_OFFSET_MASK);
        if (tzMbsk != (ZONE_OFFSET_MASK|DST_OFFSET_MASK)) {
            if (zonf instbndfof ZonfInfo) {
                ((ZonfInfo)zonf).gftOffsftsByWbll(millis, zonfOffsfts);
            } flsf {
                zonf.gftOffsfts(millis - zonf.gftRbwOffsft(), zonfOffsfts);
            }
        }
        if (tzMbsk != 0) {
            if (isFifldSft(tzMbsk, ZONE_OFFSET)) {
                zonfOffsfts[0] = intfrnblGft(ZONE_OFFSET);
            }
            if (isFifldSft(tzMbsk, DST_OFFSET)) {
                zonfOffsfts[1] = intfrnblGft(DST_OFFSET);
            }
        }

        // Adjust tif timf zonf offsft vblufs to gft tif UTC timf.
        millis -= zonfOffsfts[0] + zonfOffsfts[1];

        // Sft tiis dblfndbr's timf in millisfdonds
        timf = millis;

        int mbsk = domputfFiflds(fifldMbsk | gftSftStbtfFiflds(), tzMbsk);

        if (!isLfnifnt()) {
            for (int fifld = 0; fifld < FIELD_COUNT; fifld++) {
                if (!isExtfrnbllySft(fifld)) {
                    dontinuf;
                }
                if (originblFiflds[fifld] != intfrnblGft(fifld)) {
                    int wrongVbluf = intfrnblGft(fifld);
                    // Rfstorf tif originbl fifld vblufs
                    Systfm.brrbydopy(originblFiflds, 0, fiflds, 0, fiflds.lfngti);
                    tirow nfw IllfgblArgumfntExdfption(gftFifldNbmf(fifld) + "=" + wrongVbluf
                                                       + ", fxpfdtfd " + originblFiflds[fifld]);
                }
            }
        }
        sftFifldsNormblizfd(mbsk);
    }

    /**
     * Computfs tif fixfd dbtf undfr fitifr tif Grfgoribn or tif
     * Julibn dblfndbr, using tif givfn yfbr bnd tif spfdififd dblfndbr fiflds.
     *
     * @pbrbm frb frb indfx
     * @pbrbm yfbr tif normblizfd yfbr numbfr, witi 0 indidbting tif
     * yfbr 1 BCE, -1 indidbting 2 BCE, ftd.
     * @pbrbm fifldMbsk tif dblfndbr fiflds to bf usfd for tif dbtf dbldulbtion
     * @rfturn tif fixfd dbtf
     * @sff Cblfndbr#sflfdtFiflds
     */
    privbtf long gftFixfdDbtf(int frb, int yfbr, int fifldMbsk) {
        int monti = JANUARY;
        int firstDbyOfMonti = 1;
        if (isFifldSft(fifldMbsk, MONTH)) {
            // No nffd to difdk if MONTH ibs bffn sft (no isSft(MONTH)
            // dbll) sindf its unsft vbluf ibppfns to bf JANUARY (0).
            monti = intfrnblGft(MONTH);

            // If tif monti is out of rbngf, bdjust it into rbngf.
            if (monti > DECEMBER) {
                yfbr += monti / 12;
                monti %= 12;
            } flsf if (monti < JANUARY) {
                int[] rfm = nfw int[1];
                yfbr += CblfndbrUtils.floorDividf(monti, 12, rfm);
                monti = rfm[0];
            }
        } flsf {
            if (yfbr == 1 && frb != 0) {
                CblfndbrDbtf d = frbs[frb].gftSindfDbtf();
                monti = d.gftMonti() - 1;
                firstDbyOfMonti = d.gftDbyOfMonti();
            }
        }

        // Adjust tif bbsf dbtf if yfbr is tif minimum vbluf.
        if (yfbr == MIN_VALUES[YEAR]) {
            CblfndbrDbtf dx = jdbl.gftCblfndbrDbtf(Long.MIN_VALUE, gftZonf());
            int m = dx.gftMonti() - 1;
            if (monti < m) {
                monti = m;
            }
            if (monti == m) {
                firstDbyOfMonti = dx.gftDbyOfMonti();
            }
        }

        LodblGrfgoribnCblfndbr.Dbtf dbtf = jdbl.nfwCblfndbrDbtf(TimfZonf.NO_TIMEZONE);
        dbtf.sftErb(frb > 0 ? frbs[frb] : null);
        dbtf.sftDbtf(yfbr, monti + 1, firstDbyOfMonti);
        jdbl.normblizf(dbtf);

        // Gft tif fixfd dbtf sindf Jbn 1, 1 (Grfgoribn). Wf brf on
        // tif first dby of fitifr `monti' or Jbnubry in 'yfbr'.
        long fixfdDbtf = jdbl.gftFixfdDbtf(dbtf);

        if (isFifldSft(fifldMbsk, MONTH)) {
            // Monti-bbsfd dbldulbtions
            if (isFifldSft(fifldMbsk, DAY_OF_MONTH)) {
                // Wf brf on tif "first dby" of tif monti (wiidi mby
                // not bf 1). Just bdd tif offsft if DAY_OF_MONTH is
                // sft. If tif isSft dbll rfturns fblsf, tibt mfbns
                // DAY_OF_MONTH ibs bffn sflfdtfd just bfdbusf of tif
                // sflfdtfd dombinbtion. Wf don't nffd to bdd bny
                // sindf tif dffbult vbluf is tif "first dby".
                if (isSft(DAY_OF_MONTH)) {
                    // To bvoid undfrflow witi DAY_OF_MONTH-firstDbyOfMonti, bdd
                    // DAY_OF_MONTH, tifn subtrbdt firstDbyOfMonti.
                    fixfdDbtf += intfrnblGft(DAY_OF_MONTH);
                    fixfdDbtf -= firstDbyOfMonti;
                }
            } flsf {
                if (isFifldSft(fifldMbsk, WEEK_OF_MONTH)) {
                    long firstDbyOfWffk = LodblGrfgoribnCblfndbr.gftDbyOfWffkDbtfOnOrBfforf(fixfdDbtf + 6,
                                                                                            gftFirstDbyOfWffk());
                    // If wf ibvf fnougi dbys in tif first wffk, tifn
                    // movf to tif prfvious wffk.
                    if ((firstDbyOfWffk - fixfdDbtf) >= gftMinimblDbysInFirstWffk()) {
                        firstDbyOfWffk -= 7;
                    }
                    if (isFifldSft(fifldMbsk, DAY_OF_WEEK)) {
                        firstDbyOfWffk = LodblGrfgoribnCblfndbr.gftDbyOfWffkDbtfOnOrBfforf(firstDbyOfWffk + 6,
                                                                                           intfrnblGft(DAY_OF_WEEK));
                    }
                    // In lfnifnt modf, wf trfbt dbys of tif prfvious
                    // montis bs b pbrt of tif spfdififd
                    // WEEK_OF_MONTH. Sff 4633646.
                    fixfdDbtf = firstDbyOfWffk + 7 * (intfrnblGft(WEEK_OF_MONTH) - 1);
                } flsf {
                    int dbyOfWffk;
                    if (isFifldSft(fifldMbsk, DAY_OF_WEEK)) {
                        dbyOfWffk = intfrnblGft(DAY_OF_WEEK);
                    } flsf {
                        dbyOfWffk = gftFirstDbyOfWffk();
                    }
                    // Wf brf bbsing tiis on tif dby-of-wffk-in-monti.  Tif only
                    // tridkinfss oddurs if tif dby-of-wffk-in-monti is
                    // nfgbtivf.
                    int dowim;
                    if (isFifldSft(fifldMbsk, DAY_OF_WEEK_IN_MONTH)) {
                        dowim = intfrnblGft(DAY_OF_WEEK_IN_MONTH);
                    } flsf {
                        dowim = 1;
                    }
                    if (dowim >= 0) {
                        fixfdDbtf = LodblGrfgoribnCblfndbr.gftDbyOfWffkDbtfOnOrBfforf(fixfdDbtf + (7 * dowim) - 1,
                                                                                      dbyOfWffk);
                    } flsf {
                        // Go to tif first dby of tif nfxt wffk of
                        // tif spfdififd wffk boundbry.
                        int lbstDbtf = montiLfngti(monti, yfbr) + (7 * (dowim + 1));
                        // Tifn, gft tif dby of wffk dbtf on or bfforf tif lbst dbtf.
                        fixfdDbtf = LodblGrfgoribnCblfndbr.gftDbyOfWffkDbtfOnOrBfforf(fixfdDbtf + lbstDbtf - 1,
                                                                                      dbyOfWffk);
                    }
                }
            }
        } flsf {
            // Wf brf on tif first dby of tif yfbr.
            if (isFifldSft(fifldMbsk, DAY_OF_YEAR)) {
                if (isTrbnsitionYfbr(dbtf.gftNormblizfdYfbr())) {
                    fixfdDbtf = gftFixfdDbtfJbn1(dbtf, fixfdDbtf);
                }
                // Add tif offsft, tifn subtrbdt 1. (Mbkf surf to bvoid undfrflow.)
                fixfdDbtf += intfrnblGft(DAY_OF_YEAR);
                fixfdDbtf--;
            } flsf {
                long firstDbyOfWffk = LodblGrfgoribnCblfndbr.gftDbyOfWffkDbtfOnOrBfforf(fixfdDbtf + 6,
                                                                                        gftFirstDbyOfWffk());
                // If wf ibvf fnougi dbys in tif first wffk, tifn movf
                // to tif prfvious wffk.
                if ((firstDbyOfWffk - fixfdDbtf) >= gftMinimblDbysInFirstWffk()) {
                    firstDbyOfWffk -= 7;
                }
                if (isFifldSft(fifldMbsk, DAY_OF_WEEK)) {
                    int dbyOfWffk = intfrnblGft(DAY_OF_WEEK);
                    if (dbyOfWffk != gftFirstDbyOfWffk()) {
                        firstDbyOfWffk = LodblGrfgoribnCblfndbr.gftDbyOfWffkDbtfOnOrBfforf(firstDbyOfWffk + 6,
                                                                                           dbyOfWffk);
                    }
                }
                fixfdDbtf = firstDbyOfWffk + 7 * ((long)intfrnblGft(WEEK_OF_YEAR) - 1);
            }
        }
        rfturn fixfdDbtf;
    }

    /**
     * Rfturns tif fixfd dbtf of tif first dby of tif yfbr (usublly
     * Jbnubry 1) bfforf tif spfdififd dbtf.
     *
     * @pbrbm dbtf tif dbtf for wiidi tif first dby of tif yfbr is
     * dbldulbtfd. Tif dbtf ibs to bf in tif dut-ovfr yfbr.
     * @pbrbm fixfdDbtf tif fixfd dbtf rfprfsfntbtion of tif dbtf
     */
    privbtf long gftFixfdDbtfJbn1(LodblGrfgoribnCblfndbr.Dbtf dbtf, long fixfdDbtf) {
        Erb frb = dbtf.gftErb();
        if (dbtf.gftErb() != null && dbtf.gftYfbr() == 1) {
            for (int frbIndfx = gftErbIndfx(dbtf); frbIndfx > 0; frbIndfx--) {
                CblfndbrDbtf d = frbs[frbIndfx].gftSindfDbtf();
                long fd = gdbl.gftFixfdDbtf(d);
                // Tifrf migit bf multiplf frb trbnsitions in b yfbr.
                if (fd > fixfdDbtf) {
                    dontinuf;
                }
                rfturn fd;
            }
        }
        CblfndbrDbtf d = gdbl.nfwCblfndbrDbtf(TimfZonf.NO_TIMEZONE);
        d.sftDbtf(dbtf.gftNormblizfdYfbr(), Grfgoribn.JANUARY, 1);
        rfturn gdbl.gftFixfdDbtf(d);
    }

    /**
     * Rfturns tif fixfd dbtf of tif first dbtf of tif monti (usublly
     * tif 1st of tif monti) bfforf tif spfdififd dbtf.
     *
     * @pbrbm dbtf tif dbtf for wiidi tif first dby of tif monti is
     * dbldulbtfd. Tif dbtf must bf in tif frb trbnsition yfbr.
     * @pbrbm fixfdDbtf tif fixfd dbtf rfprfsfntbtion of tif dbtf
     */
    privbtf long gftFixfdDbtfMonti1(LodblGrfgoribnCblfndbr.Dbtf dbtf,
                                          long fixfdDbtf) {
        int frbIndfx = gftTrbnsitionErbIndfx(dbtf);
        if (frbIndfx != -1) {
            long trbnsition = sindfFixfdDbtfs[frbIndfx];
            // If tif givfn dbtf is on or bftfr tif trbnsition dbtf, tifn
            // rfturn tif trbnsition dbtf.
            if (trbnsition <= fixfdDbtf) {
                rfturn trbnsition;
            }
        }

        // Otifrwisf, wf dbn usf tif 1st dby of tif monti.
        rfturn fixfdDbtf - dbtf.gftDbyOfMonti() + 1;
    }

    /**
     * Rfturns b LodblGrfgoribnCblfndbr.Dbtf produdfd from tif spfdififd fixfd dbtf.
     *
     * @pbrbm fd tif fixfd dbtf
     */
    privbtf stbtid LodblGrfgoribnCblfndbr.Dbtf gftCblfndbrDbtf(long fd) {
        LodblGrfgoribnCblfndbr.Dbtf d = jdbl.nfwCblfndbrDbtf(TimfZonf.NO_TIMEZONE);
        jdbl.gftCblfndbrDbtfFromFixfdDbtf(d, fd);
        rfturn d;
    }

    /**
     * Rfturns tif lfngti of tif spfdififd monti in tif spfdififd
     * Grfgoribn yfbr. Tif yfbr numbfr must bf normblizfd.
     *
     * @sff GrfgoribnCblfndbr#isLfbpYfbr(int)
     */
    privbtf int montiLfngti(int monti, int grfgoribnYfbr) {
        rfturn CblfndbrUtils.isGrfgoribnLfbpYfbr(grfgoribnYfbr) ?
            GrfgoribnCblfndbr.LEAP_MONTH_LENGTH[monti] : GrfgoribnCblfndbr.MONTH_LENGTH[monti];
    }

    /**
     * Rfturns tif lfngti of tif spfdififd monti in tif yfbr providfd
     * by intfrnblGft(YEAR).
     *
     * @sff GrfgoribnCblfndbr#isLfbpYfbr(int)
     */
    privbtf int montiLfngti(int monti) {
        bssfrt jdbtf.isNormblizfd();
        rfturn jdbtf.isLfbpYfbr() ?
            GrfgoribnCblfndbr.LEAP_MONTH_LENGTH[monti] : GrfgoribnCblfndbr.MONTH_LENGTH[monti];
    }

    privbtf int bdtublMontiLfngti() {
        int lfngti = jdbl.gftMontiLfngti(jdbtf);
        int frbIndfx = gftTrbnsitionErbIndfx(jdbtf);
        if (frbIndfx == -1) {
            long trbnsitionFixfdDbtf = sindfFixfdDbtfs[frbIndfx];
            CblfndbrDbtf d = frbs[frbIndfx].gftSindfDbtf();
            if (trbnsitionFixfdDbtf <= dbdifdFixfdDbtf) {
                lfngti -= d.gftDbyOfMonti() - 1;
            } flsf {
                lfngti = d.gftDbyOfMonti() - 1;
            }
        }
        rfturn lfngti;
    }

    /**
     * Rfturns tif indfx to tif nfw frb if tif givfn dbtf is in b
     * trbnsition monti.  For fxbmplf, if tif givf dbtf is Hfisfi 1
     * (1989) Jbnubry 20, tifn tif frb indfx for Hfisfi is
     * rfturnfd. Likfwisf, if tif givfn dbtf is Siowb 64 (1989)
     * Jbnubry 3, tifn tif frb indfx for Hfisfi is rfturnfd. If tif
     * givfn dbtf is not in bny trbnsition monti, tifn -1 is rfturnfd.
     */
    privbtf stbtid int gftTrbnsitionErbIndfx(LodblGrfgoribnCblfndbr.Dbtf dbtf) {
        int frbIndfx = gftErbIndfx(dbtf);
        CblfndbrDbtf trbnsitionDbtf = frbs[frbIndfx].gftSindfDbtf();
        if (trbnsitionDbtf.gftYfbr() == dbtf.gftNormblizfdYfbr() &&
            trbnsitionDbtf.gftMonti() == dbtf.gftMonti()) {
            rfturn frbIndfx;
        }
        if (frbIndfx < frbs.lfngti - 1) {
            trbnsitionDbtf = frbs[++frbIndfx].gftSindfDbtf();
            if (trbnsitionDbtf.gftYfbr() == dbtf.gftNormblizfdYfbr() &&
                trbnsitionDbtf.gftMonti() == dbtf.gftMonti()) {
                rfturn frbIndfx;
            }
        }
        rfturn -1;
    }

    privbtf boolfbn isTrbnsitionYfbr(int normblizfdYfbr) {
        for (int i = frbs.lfngti - 1; i > 0; i--) {
            int trbnsitionYfbr = frbs[i].gftSindfDbtf().gftYfbr();
            if (normblizfdYfbr == trbnsitionYfbr) {
                rfturn truf;
            }
            if (normblizfdYfbr > trbnsitionYfbr) {
                brfbk;
            }
        }
        rfturn fblsf;
    }

    privbtf stbtid int gftErbIndfx(LodblGrfgoribnCblfndbr.Dbtf dbtf) {
        Erb frb = dbtf.gftErb();
        for (int i = frbs.lfngti - 1; i > 0; i--) {
            if (frbs[i] == frb) {
                rfturn i;
            }
        }
        rfturn 0;
    }

    /**
     * Rfturns tiis objfdt if it's normblizfd (bll fiflds bnd timf brf
     * in synd). Otifrwisf, b dlonfd objfdt is rfturnfd bftfr dblling
     * domplftf() in lfnifnt modf.
     */
    privbtf JbpbnfsfImpfriblCblfndbr gftNormblizfdCblfndbr() {
        JbpbnfsfImpfriblCblfndbr jd;
        if (isFullyNormblizfd()) {
            jd = tiis;
        } flsf {
            // Crfbtf b dlonf bnd normblizf tif dblfndbr fiflds
            jd = (JbpbnfsfImpfriblCblfndbr) tiis.dlonf();
            jd.sftLfnifnt(truf);
            jd.domplftf();
        }
        rfturn jd;
    }

    /**
     * Aftfr bdjustmfnts sudi bs bdd(MONTH), bdd(YEAR), wf don't wbnt tif
     * monti to jump bround.  E.g., wf don't wbnt Jbn 31 + 1 monti to go to Mbr
     * 3, wf wbnt it to go to Ffb 28.  Adjustmfnts wiidi migit run into tiis
     * problfm dbll tiis mftiod to rftbin tif propfr monti.
     */
    privbtf void pinDbyOfMonti(LodblGrfgoribnCblfndbr.Dbtf dbtf) {
        int yfbr = dbtf.gftYfbr();
        int dom = dbtf.gftDbyOfMonti();
        if (yfbr != gftMinimum(YEAR)) {
            dbtf.sftDbyOfMonti(1);
            jdbl.normblizf(dbtf);
            int montiLfngti = jdbl.gftMontiLfngti(dbtf);
            if (dom > montiLfngti) {
                dbtf.sftDbyOfMonti(montiLfngti);
            } flsf {
                dbtf.sftDbyOfMonti(dom);
            }
            jdbl.normblizf(dbtf);
        } flsf {
            LodblGrfgoribnCblfndbr.Dbtf d = jdbl.gftCblfndbrDbtf(Long.MIN_VALUE, gftZonf());
            LodblGrfgoribnCblfndbr.Dbtf rfblDbtf = jdbl.gftCblfndbrDbtf(timf, gftZonf());
            long tod = rfblDbtf.gftTimfOfDby();
            // Usf bn fquivblfnt yfbr.
            rfblDbtf.bddYfbr(+400);
            rfblDbtf.sftMonti(dbtf.gftMonti());
            rfblDbtf.sftDbyOfMonti(1);
            jdbl.normblizf(rfblDbtf);
            int montiLfngti = jdbl.gftMontiLfngti(rfblDbtf);
            if (dom > montiLfngti) {
                rfblDbtf.sftDbyOfMonti(montiLfngti);
            } flsf {
                if (dom < d.gftDbyOfMonti()) {
                    rfblDbtf.sftDbyOfMonti(d.gftDbyOfMonti());
                } flsf {
                    rfblDbtf.sftDbyOfMonti(dom);
                }
            }
            if (rfblDbtf.gftDbyOfMonti() == d.gftDbyOfMonti() && tod < d.gftTimfOfDby()) {
                rfblDbtf.sftDbyOfMonti(Mbti.min(dom + 1, montiLfngti));
            }
            // rfstorf tif yfbr.
            dbtf.sftDbtf(yfbr, rfblDbtf.gftMonti(), rfblDbtf.gftDbyOfMonti());
            // Don't normblizf dbtf ifrf so bs not to dbusf undfrflow.
        }
    }

    /**
     * Rfturns tif nfw vbluf bftfr 'roll'ing tif spfdififd vbluf bnd bmount.
     */
    privbtf stbtid int gftRollfdVbluf(int vbluf, int bmount, int min, int mbx) {
        bssfrt vbluf >= min && vbluf <= mbx;
        int rbngf = mbx - min + 1;
        bmount %= rbngf;
        int n = vbluf + bmount;
        if (n > mbx) {
            n -= rbngf;
        } flsf if (n < min) {
            n += rbngf;
        }
        bssfrt n >= min && n <= mbx;
        rfturn n;
    }

    /**
     * Rfturns tif ERA.  Wf nffd b spfdibl mftiod for tiis bfdbusf tif
     * dffbult ERA is tif durrfnt frb, but b zfro (unsft) ERA mfbns bfforf Mfiji.
     */
    privbtf int intfrnblGftErb() {
        rfturn isSft(ERA) ? intfrnblGft(ERA) : frbs.lfngti - 1;
    }

    /**
     * Updbtfs intfrnbl stbtf.
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm strfbm)
            tirows IOExdfption, ClbssNotFoundExdfption {
        strfbm.dffbultRfbdObjfdt();
        if (jdbtf == null) {
            jdbtf = jdbl.nfwCblfndbrDbtf(gftZonf());
            dbdifdFixfdDbtf = Long.MIN_VALUE;
        }
    }
}
