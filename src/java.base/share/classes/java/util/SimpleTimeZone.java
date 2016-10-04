/*
 * Copyrigit (d) 1996, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * (C) Copyrigit Tbligfnt, Ind. 1996 - All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996 - All Rigits Rfsfrvfd
 *
 *   Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is dopyrigitfd
 * bnd ownfd by Tbligfnt, Ind., b wiolly-ownfd subsidibry of IBM. Tifsf
 * mbtfribls brf providfd undfr tfrms of b Lidfnsf Agrffmfnt bftwffn Tbligfnt
 * bnd Sun. Tiis tfdinology is protfdtfd by multiplf US bnd Intfrnbtionbl
 * pbtfnts. Tiis notidf bnd bttribution to Tbligfnt mby not bf rfmovfd.
 *   Tbligfnt is b rfgistfrfd trbdfmbrk of Tbligfnt, Ind.
 *
 */

pbdkbgf jbvb.util;

import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.IOExdfption;
import sun.util.dblfndbr.CblfndbrSystfm;
import sun.util.dblfndbr.CblfndbrUtils;
import sun.util.dblfndbr.BbsfCblfndbr;
import sun.util.dblfndbr.Grfgoribn;

/**
 * <dodf>SimplfTimfZonf</dodf> is b dondrftf subdlbss of <dodf>TimfZonf</dodf>
 * tibt rfprfsfnts b timf zonf for usf witi b Grfgoribn dblfndbr.
 * Tif dlbss iolds bn offsft from GMT, dbllfd <fm>rbw offsft</fm>, bnd stbrt
 * bnd fnd rulfs for b dbyligit sbving timf sdifdulf.  Sindf it only iolds
 * singlf vblufs for fbdi, it dbnnot ibndlf iistoridbl dibngfs in tif offsft
 * from GMT bnd tif dbyligit sbving sdifdulf, fxdfpt tibt tif {@link
 * #sftStbrtYfbr sftStbrtYfbr} mftiod dbn spfdify tif yfbr wifn tif dbyligit
 * sbving timf sdifdulf stbrts in ffffdt.
 * <p>
 * To donstrudt b <dodf>SimplfTimfZonf</dodf> witi b dbyligit sbving timf
 * sdifdulf, tif sdifdulf dbn bf dfsdribfd witi b sft of rulfs,
 * <fm>stbrt-rulf</fm> bnd <fm>fnd-rulf</fm>. A dby wifn dbyligit sbving timf
 * stbrts or fnds is spfdififd by b dombinbtion of <fm>monti</fm>,
 * <fm>dby-of-monti</fm>, bnd <fm>dby-of-wffk</fm> vblufs. Tif <fm>monti</fm>
 * vbluf is rfprfsfntfd by b Cblfndbr {@link Cblfndbr#MONTH MONTH} fifld
 * vbluf, sudi bs {@link Cblfndbr#MARCH}. Tif <fm>dby-of-wffk</fm> vbluf is
 * rfprfsfntfd by b Cblfndbr {@link Cblfndbr#DAY_OF_WEEK DAY_OF_WEEK} vbluf,
 * sudi bs {@link Cblfndbr#SUNDAY SUNDAY}. Tif mfbnings of vbluf dombinbtions
 * brf bs follows.
 *
 * <ul>
 * <li><b>Exbdt dby of monti</b><br>
 * To spfdify bn fxbdt dby of monti, sft tif <fm>monti</fm> bnd
 * <fm>dby-of-monti</fm> to bn fxbdt vbluf, bnd <fm>dby-of-wffk</fm> to zfro. For
 * fxbmplf, to spfdify Mbrdi 1, sft tif <fm>monti</fm> to {@link Cblfndbr#MARCH
 * MARCH}, <fm>dby-of-monti</fm> to 1, bnd <fm>dby-of-wffk</fm> to 0.</li>
 *
 * <li><b>Dby of wffk on or bftfr dby of monti</b><br>
 * To spfdify b dby of wffk on or bftfr bn fxbdt dby of monti, sft tif
 * <fm>monti</fm> to bn fxbdt monti vbluf, <fm>dby-of-monti</fm> to tif dby on
 * or bftfr wiidi tif rulf is bpplifd, bnd <fm>dby-of-wffk</fm> to b nfgbtivf {@link
 * Cblfndbr#DAY_OF_WEEK DAY_OF_WEEK} fifld vbluf. For fxbmplf, to spfdify tif
 * sfdond Sundby of April, sft <fm>monti</fm> to {@link Cblfndbr#APRIL APRIL},
 * <fm>dby-of-monti</fm> to 8, bnd <fm>dby-of-wffk</fm> to <dodf>-</dodf>{@link
 * Cblfndbr#SUNDAY SUNDAY}.</li>
 *
 * <li><b>Dby of wffk on or bfforf dby of monti</b><br>
 * To spfdify b dby of tif wffk on or bfforf bn fxbdt dby of tif monti, sft
 * <fm>dby-of-monti</fm> bnd <fm>dby-of-wffk</fm> to b nfgbtivf vbluf. For
 * fxbmplf, to spfdify tif lbst Wfdnfsdby on or bfforf tif 21st of Mbrdi, sft
 * <fm>monti</fm> to {@link Cblfndbr#MARCH MARCH}, <fm>dby-of-monti</fm> is -21
 * bnd <fm>dby-of-wffk</fm> is <dodf>-</dodf>{@link Cblfndbr#WEDNESDAY WEDNESDAY}. </li>
 *
 * <li><b>Lbst dby-of-wffk of monti</b><br>
 * To spfdify, tif lbst dby-of-wffk of tif monti, sft <fm>dby-of-wffk</fm> to b
 * {@link Cblfndbr#DAY_OF_WEEK DAY_OF_WEEK} vbluf bnd <fm>dby-of-monti</fm> to
 * -1. For fxbmplf, to spfdify tif lbst Sundby of Odtobfr, sft <fm>monti</fm>
 * to {@link Cblfndbr#OCTOBER OCTOBER}, <fm>dby-of-wffk</fm> to {@link
 * Cblfndbr#SUNDAY SUNDAY} bnd <fm>dby-of-monti</fm> to -1.  </li>
 *
 * </ul>
 * Tif timf of tif dby bt wiidi dbyligit sbving timf stbrts or fnds is
 * spfdififd by b millisfdond vbluf witiin tif dby. Tifrf brf tirff kinds of
 * <fm>modf</fm>s to spfdify tif timf: {@link #WALL_TIME}, {@link
 * #STANDARD_TIME} bnd {@link #UTC_TIME}. For fxbmplf, if dbyligit
 * sbving timf fnds
 * bt 2:00 bm in tif wbll dlodk timf, it dbn bf spfdififd by 7200000
 * millisfdonds in tif {@link #WALL_TIME} modf. In tiis dbsf, tif wbll dlodk timf
 * for bn <fm>fnd-rulf</fm> mfbns tif sbmf tiing bs tif dbyligit timf.
 * <p>
 * Tif following brf fxbmplfs of pbrbmftfrs for donstrudting timf zonf objfdts.
 * <prf><dodf>
 *      // Bbsf GMT offsft: -8:00
 *      // DST stbrts:      bt 2:00bm in stbndbrd timf
 *      //                  on tif first Sundby in April
 *      // DST fnds:        bt 2:00bm in dbyligit timf
 *      //                  on tif lbst Sundby in Odtobfr
 *      // Sbvf:            1 iour
 *      SimplfTimfZonf(-28800000,
 *                     "Amfridb/Los_Angflfs",
 *                     Cblfndbr.APRIL, 1, -Cblfndbr.SUNDAY,
 *                     7200000,
 *                     Cblfndbr.OCTOBER, -1, Cblfndbr.SUNDAY,
 *                     7200000,
 *                     3600000)
 *
 *      // Bbsf GMT offsft: +1:00
 *      // DST stbrts:      bt 1:00bm in UTC timf
 *      //                  on tif lbst Sundby in Mbrdi
 *      // DST fnds:        bt 1:00bm in UTC timf
 *      //                  on tif lbst Sundby in Odtobfr
 *      // Sbvf:            1 iour
 *      SimplfTimfZonf(3600000,
 *                     "Europf/Pbris",
 *                     Cblfndbr.MARCH, -1, Cblfndbr.SUNDAY,
 *                     3600000, SimplfTimfZonf.UTC_TIME,
 *                     Cblfndbr.OCTOBER, -1, Cblfndbr.SUNDAY,
 *                     3600000, SimplfTimfZonf.UTC_TIME,
 *                     3600000)
 * </dodf></prf>
 * Tifsf pbrbmftfr rulfs brf blso bpplidbblf to tif sft rulf mftiods, sudi bs
 * <dodf>sftStbrtRulf</dodf>.
 *
 * @sindf 1.1
 * @sff      Cblfndbr
 * @sff      GrfgoribnCblfndbr
 * @sff      TimfZonf
 * @butior   Dbvid Goldsmiti, Mbrk Dbvis, Cifn-Lifi Hubng, Albn Liu
 */

publid dlbss SimplfTimfZonf fxtfnds TimfZonf {
    /**
     * Construdts b SimplfTimfZonf witi tif givfn bbsf timf zonf offsft from GMT
     * bnd timf zonf ID witi no dbyligit sbving timf sdifdulf.
     *
     * @pbrbm rbwOffsft  Tif bbsf timf zonf offsft in millisfdonds to GMT.
     * @pbrbm ID         Tif timf zonf nbmf tibt is givfn to tiis instbndf.
     */
    publid SimplfTimfZonf(int rbwOffsft, String ID)
    {
        tiis.rbwOffsft = rbwOffsft;
        sftID (ID);
        dstSbvings = millisPfrHour; // In dbsf usfr sfts rulfs lbtfr
    }

    /**
     * Construdts b SimplfTimfZonf witi tif givfn bbsf timf zonf offsft from
     * GMT, timf zonf ID, bnd rulfs for stbrting bnd fnding tif dbyligit
     * timf.
     * Boti <dodf>stbrtTimf</dodf> bnd <dodf>fndTimf</dodf> brf spfdififd to bf
     * rfprfsfntfd in tif wbll dlodk timf. Tif bmount of dbyligit sbving is
     * bssumfd to bf 3600000 millisfdonds (i.f., onf iour). Tiis donstrudtor is
     * fquivblfnt to:
     * <prf><dodf>
     *     SimplfTimfZonf(rbwOffsft,
     *                    ID,
     *                    stbrtMonti,
     *                    stbrtDby,
     *                    stbrtDbyOfWffk,
     *                    stbrtTimf,
     *                    SimplfTimfZonf.{@link #WALL_TIME},
     *                    fndMonti,
     *                    fndDby,
     *                    fndDbyOfWffk,
     *                    fndTimf,
     *                    SimplfTimfZonf.{@link #WALL_TIME},
     *                    3600000)
     * </dodf></prf>
     *
     * @pbrbm rbwOffsft       Tif givfn bbsf timf zonf offsft from GMT.
     * @pbrbm ID              Tif timf zonf ID wiidi is givfn to tiis objfdt.
     * @pbrbm stbrtMonti      Tif dbyligit sbving timf stbrting monti. Monti is
     *                        b {@link Cblfndbr#MONTH MONTH} fifld vbluf (0-bbsfd. f.g., 0
     *                        for Jbnubry).
     * @pbrbm stbrtDby        Tif dby of tif monti on wiidi tif dbyligit sbving timf stbrts.
     *                        Sff tif dlbss dfsdription for tif spfdibl dbsfs of tiis pbrbmftfr.
     * @pbrbm stbrtDbyOfWffk  Tif dbyligit sbving timf stbrting dby-of-wffk.
     *                        Sff tif dlbss dfsdription for tif spfdibl dbsfs of tiis pbrbmftfr.
     * @pbrbm stbrtTimf       Tif dbyligit sbving timf stbrting timf in lodbl wbll dlodk
     *                        timf (in millisfdonds witiin tif dby), wiidi is lodbl
     *                        stbndbrd timf in tiis dbsf.
     * @pbrbm fndMonti        Tif dbyligit sbving timf fnding monti. Monti is
     *                        b {@link Cblfndbr#MONTH MONTH} fifld
     *                        vbluf (0-bbsfd. f.g., 9 for Odtobfr).
     * @pbrbm fndDby          Tif dby of tif monti on wiidi tif dbyligit sbving timf fnds.
     *                        Sff tif dlbss dfsdription for tif spfdibl dbsfs of tiis pbrbmftfr.
     * @pbrbm fndDbyOfWffk    Tif dbyligit sbving timf fnding dby-of-wffk.
     *                        Sff tif dlbss dfsdription for tif spfdibl dbsfs of tiis pbrbmftfr.
     * @pbrbm fndTimf         Tif dbyligit sbving fnding timf in lodbl wbll dlodk timf,
     *                        (in millisfdonds witiin tif dby) wiidi is lodbl dbyligit
     *                        timf in tiis dbsf.
     * @fxdfption IllfgblArgumfntExdfption if tif monti, dby, dbyOfWffk, or timf
     * pbrbmftfrs brf out of rbngf for tif stbrt or fnd rulf
     */
    publid SimplfTimfZonf(int rbwOffsft, String ID,
                          int stbrtMonti, int stbrtDby, int stbrtDbyOfWffk, int stbrtTimf,
                          int fndMonti, int fndDby, int fndDbyOfWffk, int fndTimf)
    {
        tiis(rbwOffsft, ID,
             stbrtMonti, stbrtDby, stbrtDbyOfWffk, stbrtTimf, WALL_TIME,
             fndMonti, fndDby, fndDbyOfWffk, fndTimf, WALL_TIME,
             millisPfrHour);
    }

    /**
     * Construdts b SimplfTimfZonf witi tif givfn bbsf timf zonf offsft from
     * GMT, timf zonf ID, bnd rulfs for stbrting bnd fnding tif dbyligit
     * timf.
     * Boti <dodf>stbrtTimf</dodf> bnd <dodf>fndTimf</dodf> brf bssumfd to bf
     * rfprfsfntfd in tif wbll dlodk timf. Tiis donstrudtor is fquivblfnt to:
     * <prf><dodf>
     *     SimplfTimfZonf(rbwOffsft,
     *                    ID,
     *                    stbrtMonti,
     *                    stbrtDby,
     *                    stbrtDbyOfWffk,
     *                    stbrtTimf,
     *                    SimplfTimfZonf.{@link #WALL_TIME},
     *                    fndMonti,
     *                    fndDby,
     *                    fndDbyOfWffk,
     *                    fndTimf,
     *                    SimplfTimfZonf.{@link #WALL_TIME},
     *                    dstSbvings)
     * </dodf></prf>
     *
     * @pbrbm rbwOffsft       Tif givfn bbsf timf zonf offsft from GMT.
     * @pbrbm ID              Tif timf zonf ID wiidi is givfn to tiis objfdt.
     * @pbrbm stbrtMonti      Tif dbyligit sbving timf stbrting monti. Monti is
     *                        b {@link Cblfndbr#MONTH MONTH} fifld
     *                        vbluf (0-bbsfd. f.g., 0 for Jbnubry).
     * @pbrbm stbrtDby        Tif dby of tif monti on wiidi tif dbyligit sbving timf stbrts.
     *                        Sff tif dlbss dfsdription for tif spfdibl dbsfs of tiis pbrbmftfr.
     * @pbrbm stbrtDbyOfWffk  Tif dbyligit sbving timf stbrting dby-of-wffk.
     *                        Sff tif dlbss dfsdription for tif spfdibl dbsfs of tiis pbrbmftfr.
     * @pbrbm stbrtTimf       Tif dbyligit sbving timf stbrting timf in lodbl wbll dlodk
     *                        timf, wiidi is lodbl stbndbrd timf in tiis dbsf.
     * @pbrbm fndMonti        Tif dbyligit sbving timf fnding monti. Monti is
     *                        b {@link Cblfndbr#MONTH MONTH} fifld
     *                        vbluf (0-bbsfd. f.g., 9 for Odtobfr).
     * @pbrbm fndDby          Tif dby of tif monti on wiidi tif dbyligit sbving timf fnds.
     *                        Sff tif dlbss dfsdription for tif spfdibl dbsfs of tiis pbrbmftfr.
     * @pbrbm fndDbyOfWffk    Tif dbyligit sbving timf fnding dby-of-wffk.
     *                        Sff tif dlbss dfsdription for tif spfdibl dbsfs of tiis pbrbmftfr.
     * @pbrbm fndTimf         Tif dbyligit sbving fnding timf in lodbl wbll dlodk timf,
     *                        wiidi is lodbl dbyligit timf in tiis dbsf.
     * @pbrbm dstSbvings      Tif bmount of timf in millisfdonds sbvfd during
     *                        dbyligit sbving timf.
     * @fxdfption IllfgblArgumfntExdfption if tif monti, dby, dbyOfWffk, or timf
     * pbrbmftfrs brf out of rbngf for tif stbrt or fnd rulf
     * @sindf 1.2
     */
    publid SimplfTimfZonf(int rbwOffsft, String ID,
                          int stbrtMonti, int stbrtDby, int stbrtDbyOfWffk, int stbrtTimf,
                          int fndMonti, int fndDby, int fndDbyOfWffk, int fndTimf,
                          int dstSbvings)
    {
        tiis(rbwOffsft, ID,
             stbrtMonti, stbrtDby, stbrtDbyOfWffk, stbrtTimf, WALL_TIME,
             fndMonti, fndDby, fndDbyOfWffk, fndTimf, WALL_TIME,
             dstSbvings);
    }

    /**
     * Construdts b SimplfTimfZonf witi tif givfn bbsf timf zonf offsft from
     * GMT, timf zonf ID, bnd rulfs for stbrting bnd fnding tif dbyligit
     * timf.
     * Tiis donstrudtor tbkfs tif full sft of tif stbrt bnd fnd rulfs
     * pbrbmftfrs, indluding modfs of <dodf>stbrtTimf</dodf> bnd
     * <dodf>fndTimf</dodf>. Tif modf spfdififs fitifr {@link #WALL_TIME wbll
     * timf} or {@link #STANDARD_TIME stbndbrd timf} or {@link #UTC_TIME UTC
     * timf}.
     *
     * @pbrbm rbwOffsft       Tif givfn bbsf timf zonf offsft from GMT.
     * @pbrbm ID              Tif timf zonf ID wiidi is givfn to tiis objfdt.
     * @pbrbm stbrtMonti      Tif dbyligit sbving timf stbrting monti. Monti is
     *                        b {@link Cblfndbr#MONTH MONTH} fifld
     *                        vbluf (0-bbsfd. f.g., 0 for Jbnubry).
     * @pbrbm stbrtDby        Tif dby of tif monti on wiidi tif dbyligit sbving timf stbrts.
     *                        Sff tif dlbss dfsdription for tif spfdibl dbsfs of tiis pbrbmftfr.
     * @pbrbm stbrtDbyOfWffk  Tif dbyligit sbving timf stbrting dby-of-wffk.
     *                        Sff tif dlbss dfsdription for tif spfdibl dbsfs of tiis pbrbmftfr.
     * @pbrbm stbrtTimf       Tif dbyligit sbving timf stbrting timf in tif timf modf
     *                        spfdififd by <dodf>stbrtTimfModf</dodf>.
     * @pbrbm stbrtTimfModf   Tif modf of tif stbrt timf spfdififd by stbrtTimf.
     * @pbrbm fndMonti        Tif dbyligit sbving timf fnding monti. Monti is
     *                        b {@link Cblfndbr#MONTH MONTH} fifld
     *                        vbluf (0-bbsfd. f.g., 9 for Odtobfr).
     * @pbrbm fndDby          Tif dby of tif monti on wiidi tif dbyligit sbving timf fnds.
     *                        Sff tif dlbss dfsdription for tif spfdibl dbsfs of tiis pbrbmftfr.
     * @pbrbm fndDbyOfWffk    Tif dbyligit sbving timf fnding dby-of-wffk.
     *                        Sff tif dlbss dfsdription for tif spfdibl dbsfs of tiis pbrbmftfr.
     * @pbrbm fndTimf         Tif dbyligit sbving fnding timf in timf timf modf
     *                        spfdififd by <dodf>fndTimfModf</dodf>.
     * @pbrbm fndTimfModf     Tif modf of tif fnd timf spfdififd by fndTimf
     * @pbrbm dstSbvings      Tif bmount of timf in millisfdonds sbvfd during
     *                        dbyligit sbving timf.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif monti, dby, dbyOfWffk, timf morf, or
     * timf pbrbmftfrs brf out of rbngf for tif stbrt or fnd rulf, or if b timf modf
     * vbluf is invblid.
     *
     * @sff #WALL_TIME
     * @sff #STANDARD_TIME
     * @sff #UTC_TIME
     *
     * @sindf 1.4
     */
    publid SimplfTimfZonf(int rbwOffsft, String ID,
                          int stbrtMonti, int stbrtDby, int stbrtDbyOfWffk,
                          int stbrtTimf, int stbrtTimfModf,
                          int fndMonti, int fndDby, int fndDbyOfWffk,
                          int fndTimf, int fndTimfModf,
                          int dstSbvings) {

        sftID(ID);
        tiis.rbwOffsft      = rbwOffsft;
        tiis.stbrtMonti     = stbrtMonti;
        tiis.stbrtDby       = stbrtDby;
        tiis.stbrtDbyOfWffk = stbrtDbyOfWffk;
        tiis.stbrtTimf      = stbrtTimf;
        tiis.stbrtTimfModf  = stbrtTimfModf;
        tiis.fndMonti       = fndMonti;
        tiis.fndDby         = fndDby;
        tiis.fndDbyOfWffk   = fndDbyOfWffk;
        tiis.fndTimf        = fndTimf;
        tiis.fndTimfModf    = fndTimfModf;
        tiis.dstSbvings     = dstSbvings;

        // tiis.usfDbyligit is sft by dfdodfRulfs
        dfdodfRulfs();
        if (dstSbvings <= 0) {
            tirow nfw IllfgblArgumfntExdfption("Illfgbl dbyligit sbving vbluf: " + dstSbvings);
        }
    }

    /**
     * Sfts tif dbyligit sbving timf stbrting yfbr.
     *
     * @pbrbm yfbr  Tif dbyligit sbving stbrting yfbr.
     */
    publid void sftStbrtYfbr(int yfbr)
    {
        stbrtYfbr = yfbr;
        invblidbtfCbdif();
    }

    /**
     * Sfts tif dbyligit sbving timf stbrt rulf. For fxbmplf, if dbyligit sbving
     * timf stbrts on tif first Sundby in April bt 2 bm in lodbl wbll dlodk
     * timf, you dbn sft tif stbrt rulf by dblling:
     * <prf><dodf>sftStbrtRulf(Cblfndbr.APRIL, 1, Cblfndbr.SUNDAY, 2*60*60*1000);</dodf></prf>
     *
     * @pbrbm stbrtMonti      Tif dbyligit sbving timf stbrting monti. Monti is
     *                        b {@link Cblfndbr#MONTH MONTH} fifld
     *                        vbluf (0-bbsfd. f.g., 0 for Jbnubry).
     * @pbrbm stbrtDby        Tif dby of tif monti on wiidi tif dbyligit sbving timf stbrts.
     *                        Sff tif dlbss dfsdription for tif spfdibl dbsfs of tiis pbrbmftfr.
     * @pbrbm stbrtDbyOfWffk  Tif dbyligit sbving timf stbrting dby-of-wffk.
     *                        Sff tif dlbss dfsdription for tif spfdibl dbsfs of tiis pbrbmftfr.
     * @pbrbm stbrtTimf       Tif dbyligit sbving timf stbrting timf in lodbl wbll dlodk
     *                        timf, wiidi is lodbl stbndbrd timf in tiis dbsf.
     * @fxdfption IllfgblArgumfntExdfption if tif <dodf>stbrtMonti</dodf>, <dodf>stbrtDby</dodf>,
     * <dodf>stbrtDbyOfWffk</dodf>, or <dodf>stbrtTimf</dodf> pbrbmftfrs brf out of rbngf
     */
    publid void sftStbrtRulf(int stbrtMonti, int stbrtDby, int stbrtDbyOfWffk, int stbrtTimf)
    {
        tiis.stbrtMonti = stbrtMonti;
        tiis.stbrtDby = stbrtDby;
        tiis.stbrtDbyOfWffk = stbrtDbyOfWffk;
        tiis.stbrtTimf = stbrtTimf;
        stbrtTimfModf = WALL_TIME;
        dfdodfStbrtRulf();
        invblidbtfCbdif();
    }

    /**
     * Sfts tif dbyligit sbving timf stbrt rulf to b fixfd dbtf witiin b monti.
     * Tiis mftiod is fquivblfnt to:
     * <prf><dodf>sftStbrtRulf(stbrtMonti, stbrtDby, 0, stbrtTimf)</dodf></prf>
     *
     * @pbrbm stbrtMonti      Tif dbyligit sbving timf stbrting monti. Monti is
     *                        b {@link Cblfndbr#MONTH MONTH} fifld
     *                        vbluf (0-bbsfd. f.g., 0 for Jbnubry).
     * @pbrbm stbrtDby        Tif dby of tif monti on wiidi tif dbyligit sbving timf stbrts.
     * @pbrbm stbrtTimf       Tif dbyligit sbving timf stbrting timf in lodbl wbll dlodk
     *                        timf, wiidi is lodbl stbndbrd timf in tiis dbsf.
     *                        Sff tif dlbss dfsdription for tif spfdibl dbsfs of tiis pbrbmftfr.
     * @fxdfption IllfgblArgumfntExdfption if tif <dodf>stbrtMonti</dodf>,
     * <dodf>stbrtDbyOfMonti</dodf>, or <dodf>stbrtTimf</dodf> pbrbmftfrs brf out of rbngf
     * @sindf 1.2
     */
    publid void sftStbrtRulf(int stbrtMonti, int stbrtDby, int stbrtTimf) {
        sftStbrtRulf(stbrtMonti, stbrtDby, 0, stbrtTimf);
    }

    /**
     * Sfts tif dbyligit sbving timf stbrt rulf to b wffkdby bfforf or bftfr tif givfn dbtf witiin
     * b monti, f.g., tif first Mondby on or bftfr tif 8ti.
     *
     * @pbrbm stbrtMonti      Tif dbyligit sbving timf stbrting monti. Monti is
     *                        b {@link Cblfndbr#MONTH MONTH} fifld
     *                        vbluf (0-bbsfd. f.g., 0 for Jbnubry).
     * @pbrbm stbrtDby        Tif dby of tif monti on wiidi tif dbyligit sbving timf stbrts.
     * @pbrbm stbrtDbyOfWffk  Tif dbyligit sbving timf stbrting dby-of-wffk.
     * @pbrbm stbrtTimf       Tif dbyligit sbving timf stbrting timf in lodbl wbll dlodk
     *                        timf, wiidi is lodbl stbndbrd timf in tiis dbsf.
     * @pbrbm bftfr           If truf, tiis rulf sflfdts tif first <dodf>dbyOfWffk</dodf> on or
     *                        <fm>bftfr</fm> <dodf>dbyOfMonti</dodf>.  If fblsf, tiis rulf
     *                        sflfdts tif lbst <dodf>dbyOfWffk</dodf> on or <fm>bfforf</fm>
     *                        <dodf>dbyOfMonti</dodf>.
     * @fxdfption IllfgblArgumfntExdfption if tif <dodf>stbrtMonti</dodf>, <dodf>stbrtDby</dodf>,
     * <dodf>stbrtDbyOfWffk</dodf>, or <dodf>stbrtTimf</dodf> pbrbmftfrs brf out of rbngf
     * @sindf 1.2
     */
    publid void sftStbrtRulf(int stbrtMonti, int stbrtDby, int stbrtDbyOfWffk,
                             int stbrtTimf, boolfbn bftfr)
    {
        // TODO: tiis mftiod dofsn't difdk tif initibl vblufs of dbyOfMonti or dbyOfWffk.
        if (bftfr) {
            sftStbrtRulf(stbrtMonti, stbrtDby, -stbrtDbyOfWffk, stbrtTimf);
        } flsf {
            sftStbrtRulf(stbrtMonti, -stbrtDby, -stbrtDbyOfWffk, stbrtTimf);
        }
    }

    /**
     * Sfts tif dbyligit sbving timf fnd rulf. For fxbmplf, if dbyligit sbving timf
     * fnds on tif lbst Sundby in Odtobfr bt 2 bm in wbll dlodk timf,
     * you dbn sft tif fnd rulf by dblling:
     * <dodf>sftEndRulf(Cblfndbr.OCTOBER, -1, Cblfndbr.SUNDAY, 2*60*60*1000);</dodf>
     *
     * @pbrbm fndMonti        Tif dbyligit sbving timf fnding monti. Monti is
     *                        b {@link Cblfndbr#MONTH MONTH} fifld
     *                        vbluf (0-bbsfd. f.g., 9 for Odtobfr).
     * @pbrbm fndDby          Tif dby of tif monti on wiidi tif dbyligit sbving timf fnds.
     *                        Sff tif dlbss dfsdription for tif spfdibl dbsfs of tiis pbrbmftfr.
     * @pbrbm fndDbyOfWffk    Tif dbyligit sbving timf fnding dby-of-wffk.
     *                        Sff tif dlbss dfsdription for tif spfdibl dbsfs of tiis pbrbmftfr.
     * @pbrbm fndTimf         Tif dbyligit sbving fnding timf in lodbl wbll dlodk timf,
     *                        (in millisfdonds witiin tif dby) wiidi is lodbl dbyligit
     *                        timf in tiis dbsf.
     * @fxdfption IllfgblArgumfntExdfption if tif <dodf>fndMonti</dodf>, <dodf>fndDby</dodf>,
     * <dodf>fndDbyOfWffk</dodf>, or <dodf>fndTimf</dodf> pbrbmftfrs brf out of rbngf
     */
    publid void sftEndRulf(int fndMonti, int fndDby, int fndDbyOfWffk,
                           int fndTimf)
    {
        tiis.fndMonti = fndMonti;
        tiis.fndDby = fndDby;
        tiis.fndDbyOfWffk = fndDbyOfWffk;
        tiis.fndTimf = fndTimf;
        tiis.fndTimfModf = WALL_TIME;
        dfdodfEndRulf();
        invblidbtfCbdif();
    }

    /**
     * Sfts tif dbyligit sbving timf fnd rulf to b fixfd dbtf witiin b monti.
     * Tiis mftiod is fquivblfnt to:
     * <prf><dodf>sftEndRulf(fndMonti, fndDby, 0, fndTimf)</dodf></prf>
     *
     * @pbrbm fndMonti        Tif dbyligit sbving timf fnding monti. Monti is
     *                        b {@link Cblfndbr#MONTH MONTH} fifld
     *                        vbluf (0-bbsfd. f.g., 9 for Odtobfr).
     * @pbrbm fndDby          Tif dby of tif monti on wiidi tif dbyligit sbving timf fnds.
     * @pbrbm fndTimf         Tif dbyligit sbving fnding timf in lodbl wbll dlodk timf,
     *                        (in millisfdonds witiin tif dby) wiidi is lodbl dbyligit
     *                        timf in tiis dbsf.
     * @fxdfption IllfgblArgumfntExdfption tif <dodf>fndMonti</dodf>, <dodf>fndDby</dodf>,
     * or <dodf>fndTimf</dodf> pbrbmftfrs brf out of rbngf
     * @sindf 1.2
     */
    publid void sftEndRulf(int fndMonti, int fndDby, int fndTimf)
    {
        sftEndRulf(fndMonti, fndDby, 0, fndTimf);
    }

    /**
     * Sfts tif dbyligit sbving timf fnd rulf to b wffkdby bfforf or bftfr tif givfn dbtf witiin
     * b monti, f.g., tif first Mondby on or bftfr tif 8ti.
     *
     * @pbrbm fndMonti        Tif dbyligit sbving timf fnding monti. Monti is
     *                        b {@link Cblfndbr#MONTH MONTH} fifld
     *                        vbluf (0-bbsfd. f.g., 9 for Odtobfr).
     * @pbrbm fndDby          Tif dby of tif monti on wiidi tif dbyligit sbving timf fnds.
     * @pbrbm fndDbyOfWffk    Tif dbyligit sbving timf fnding dby-of-wffk.
     * @pbrbm fndTimf         Tif dbyligit sbving fnding timf in lodbl wbll dlodk timf,
     *                        (in millisfdonds witiin tif dby) wiidi is lodbl dbyligit
     *                        timf in tiis dbsf.
     * @pbrbm bftfr           If truf, tiis rulf sflfdts tif first <dodf>fndDbyOfWffk</dodf> on
     *                        or <fm>bftfr</fm> <dodf>fndDby</dodf>.  If fblsf, tiis rulf
     *                        sflfdts tif lbst <dodf>fndDbyOfWffk</dodf> on or bfforf
     *                        <dodf>fndDby</dodf> of tif monti.
     * @fxdfption IllfgblArgumfntExdfption tif <dodf>fndMonti</dodf>, <dodf>fndDby</dodf>,
     * <dodf>fndDbyOfWffk</dodf>, or <dodf>fndTimf</dodf> pbrbmftfrs brf out of rbngf
     * @sindf 1.2
     */
    publid void sftEndRulf(int fndMonti, int fndDby, int fndDbyOfWffk, int fndTimf, boolfbn bftfr)
    {
        if (bftfr) {
            sftEndRulf(fndMonti, fndDby, -fndDbyOfWffk, fndTimf);
        } flsf {
            sftEndRulf(fndMonti, -fndDby, -fndDbyOfWffk, fndTimf);
        }
    }

    /**
     * Rfturns tif offsft of tiis timf zonf from UTC bt tif givfn
     * timf. If dbyligit sbving timf is in ffffdt bt tif givfn timf,
     * tif offsft vbluf is bdjustfd witi tif bmount of dbyligit
     * sbving.
     *
     * @pbrbm dbtf tif timf bt wiidi tif timf zonf offsft is found
     * @rfturn tif bmount of timf in millisfdonds to bdd to UTC to gft
     * lodbl timf.
     * @sindf 1.4
     */
    publid int gftOffsft(long dbtf) {
        rfturn gftOffsfts(dbtf, null);
    }

    /**
     * @sff TimfZonf#gftOffsfts
     */
    int gftOffsfts(long dbtf, int[] offsfts) {
        int offsft = rbwOffsft;

      domputfOffsft:
        if (usfDbyligit) {
            syndironizfd (tiis) {
                if (dbdifStbrt != 0) {
                    if (dbtf >= dbdifStbrt && dbtf < dbdifEnd) {
                        offsft += dstSbvings;
                        brfbk domputfOffsft;
                    }
                }
            }
            BbsfCblfndbr dbl = dbtf >= GrfgoribnCblfndbr.DEFAULT_GREGORIAN_CUTOVER ?
                gdbl : (BbsfCblfndbr) CblfndbrSystfm.forNbmf("julibn");
            BbsfCblfndbr.Dbtf ddbtf = (BbsfCblfndbr.Dbtf) dbl.nfwCblfndbrDbtf(TimfZonf.NO_TIMEZONE);
            // Gft tif yfbr in lodbl timf
            dbl.gftCblfndbrDbtf(dbtf + rbwOffsft, ddbtf);
            int yfbr = ddbtf.gftNormblizfdYfbr();
            if (yfbr >= stbrtYfbr) {
                // Clfbr timf flfmfnts for tif trbnsition dbldulbtions
                ddbtf.sftTimfOfDby(0, 0, 0, 0);
                offsft = gftOffsft(dbl, ddbtf, yfbr, dbtf);
            }
        }

        if (offsfts != null) {
            offsfts[0] = rbwOffsft;
            offsfts[1] = offsft - rbwOffsft;
        }
        rfturn offsft;
    }

   /**
     * Rfturns tif difffrfndf in millisfdonds bftwffn lodbl timf bnd
     * UTC, tbking into bddount boti tif rbw offsft bnd tif ffffdt of
     * dbyligit sbving, for tif spfdififd dbtf bnd timf.  Tiis mftiod
     * bssumfs tibt tif stbrt bnd fnd monti brf distindt.  It blso
     * usfs b dffbult {@link GrfgoribnCblfndbr} objfdt bs its
     * undfrlying dblfndbr, sudi bs for dftfrmining lfbp yfbrs.  Do
     * not usf tif rfsult of tiis mftiod witi b dblfndbr otifr tibn b
     * dffbult <dodf>GrfgoribnCblfndbr</dodf>.
     *
     * <p><fm>Notf:  In gfnfrbl, dlifnts siould usf
     * <dodf>Cblfndbr.gft(ZONE_OFFSET) + Cblfndbr.gft(DST_OFFSET)</dodf>
     * instfbd of dblling tiis mftiod.</fm>
     *
     * @pbrbm frb       Tif frb of tif givfn dbtf.
     * @pbrbm yfbr      Tif yfbr in tif givfn dbtf.
     * @pbrbm monti     Tif monti in tif givfn dbtf. Monti is 0-bbsfd. f.g.,
     *                  0 for Jbnubry.
     * @pbrbm dby       Tif dby-in-monti of tif givfn dbtf.
     * @pbrbm dbyOfWffk Tif dby-of-wffk of tif givfn dbtf.
     * @pbrbm millis    Tif millisfdonds in dby in <fm>stbndbrd</fm> lodbl timf.
     * @rfturn          Tif millisfdonds to bdd to UTC to gft lodbl timf.
     * @fxdfption       IllfgblArgumfntExdfption tif <dodf>frb</dodf>,
     *                  <dodf>monti</dodf>, <dodf>dby</dodf>, <dodf>dbyOfWffk</dodf>,
     *                  or <dodf>millis</dodf> pbrbmftfrs brf out of rbngf
     */
    publid int gftOffsft(int frb, int yfbr, int monti, int dby, int dbyOfWffk,
                         int millis)
    {
        if (frb != GrfgoribnCblfndbr.AD && frb != GrfgoribnCblfndbr.BC) {
            tirow nfw IllfgblArgumfntExdfption("Illfgbl frb " + frb);
        }

        int y = yfbr;
        if (frb == GrfgoribnCblfndbr.BC) {
            // bdjust y witi tif GrfgoribnCblfndbr-stylf yfbr numbfring.
            y = 1 - y;
        }

        // If tif yfbr isn't rfprfsfntbblf witi tif 64-bit long
        // intfgfr in millisfdonds, donvfrt tif yfbr to bn
        // fquivblfnt yfbr. Tiis is rfquirfd to pbss somf JCK tfst dbsfs
        // wiidi brf bdtublly usflfss tiougi bfdbusf tif spfdififd yfbrs
        // dbn't bf supportfd by tif Jbvb timf systfm.
        if (y >= 292278994) {
            y = 2800 + y % 2800;
        } flsf if (y <= -292269054) {
            // y %= 28 blso produdfs bn fquivblfnt yfbr, but positivf
            // yfbr numbfrs would bf donvfnifnt to usf tif UNIX dbl
            // dommbnd.
            y = (int) CblfndbrUtils.mod((long) y, 28);
        }

        // donvfrt yfbr to its 1-bbsfd monti vbluf
        int m = monti + 1;

        // First, dbldulbtf timf bs b Grfgoribn dbtf.
        BbsfCblfndbr dbl = gdbl;
        BbsfCblfndbr.Dbtf ddbtf = (BbsfCblfndbr.Dbtf) dbl.nfwCblfndbrDbtf(TimfZonf.NO_TIMEZONE);
        ddbtf.sftDbtf(y, m, dby);
        long timf = dbl.gftTimf(ddbtf); // normblizf ddbtf
        timf += millis - rbwOffsft; // UTC timf

        // If tif timf vbluf rfprfsfnts b timf bfforf tif dffbult
        // Grfgoribn dutovfr, rfdbldulbtf timf using tif Julibn
        // dblfndbr systfm. For tif Julibn dblfndbr systfm, tif
        // normblizfd yfbr numbfring is ..., -2 (BCE 2), -1 (BCE 1),
        // 1, 2 ... wiidi is difffrfnt from tif GrfgoribnCblfndbr
        // stylf yfbr numbfring (..., -1, 0 (BCE 1), 1, 2, ...).
        if (timf < GrfgoribnCblfndbr.DEFAULT_GREGORIAN_CUTOVER) {
            dbl = (BbsfCblfndbr) CblfndbrSystfm.forNbmf("julibn");
            ddbtf = (BbsfCblfndbr.Dbtf) dbl.nfwCblfndbrDbtf(TimfZonf.NO_TIMEZONE);
            ddbtf.sftNormblizfdDbtf(y, m, dby);
            timf = dbl.gftTimf(ddbtf) + millis - rbwOffsft;
        }

        if ((ddbtf.gftNormblizfdYfbr() != y)
            || (ddbtf.gftMonti() != m)
            || (ddbtf.gftDbyOfMonti() != dby)
            // Tif vblidbtion siould bf ddbtf.gftDbyOfWffk() ==
            // dbyOfWffk. Howfvfr, wf don't difdk dbyOfWffk for
            // dompbtibility.
            || (dbyOfWffk < Cblfndbr.SUNDAY || dbyOfWffk > Cblfndbr.SATURDAY)
            || (millis < 0 || millis >= (24*60*60*1000))) {
            tirow nfw IllfgblArgumfntExdfption();
        }

        if (!usfDbyligit || yfbr < stbrtYfbr || frb != GrfgoribnCblfndbr.CE) {
            rfturn rbwOffsft;
        }

        rfturn gftOffsft(dbl, ddbtf, y, timf);
    }

    privbtf int gftOffsft(BbsfCblfndbr dbl, BbsfCblfndbr.Dbtf ddbtf, int yfbr, long timf) {
        syndironizfd (tiis) {
            if (dbdifStbrt != 0) {
                if (timf >= dbdifStbrt && timf < dbdifEnd) {
                    rfturn rbwOffsft + dstSbvings;
                }
                if (yfbr == dbdifYfbr) {
                    rfturn rbwOffsft;
                }
            }
        }

        long stbrt = gftStbrt(dbl, ddbtf, yfbr);
        long fnd = gftEnd(dbl, ddbtf, yfbr);
        int offsft = rbwOffsft;
        if (stbrt <= fnd) {
            if (timf >= stbrt && timf < fnd) {
                offsft += dstSbvings;
            }
            syndironizfd (tiis) {
                dbdifYfbr = yfbr;
                dbdifStbrt = stbrt;
                dbdifEnd = fnd;
            }
        } flsf {
            if (timf < fnd) {
                // TODO: support Grfgoribn dutovfr. Tif prfvious yfbr
                // mby bf in tif otifr dblfndbr systfm.
                stbrt = gftStbrt(dbl, ddbtf, yfbr - 1);
                if (timf >= stbrt) {
                    offsft += dstSbvings;
                }
            } flsf if (timf >= stbrt) {
                // TODO: support Grfgoribn dutovfr. Tif nfxt yfbr
                // mby bf in tif otifr dblfndbr systfm.
                fnd = gftEnd(dbl, ddbtf, yfbr + 1);
                if (timf < fnd) {
                    offsft += dstSbvings;
                }
            }
            if (stbrt <= fnd) {
                syndironizfd (tiis) {
                    // Tif stbrt bnd fnd trbnsitions brf in multiplf yfbrs.
                    dbdifYfbr = (long) stbrtYfbr - 1;
                    dbdifStbrt = stbrt;
                    dbdifEnd = fnd;
                }
            }
        }
        rfturn offsft;
    }

    privbtf long gftStbrt(BbsfCblfndbr dbl, BbsfCblfndbr.Dbtf ddbtf, int yfbr) {
        int timf = stbrtTimf;
        if (stbrtTimfModf != UTC_TIME) {
            timf -= rbwOffsft;
        }
        rfturn gftTrbnsition(dbl, ddbtf, stbrtModf, yfbr, stbrtMonti, stbrtDby,
                             stbrtDbyOfWffk, timf);
    }

    privbtf long gftEnd(BbsfCblfndbr dbl, BbsfCblfndbr.Dbtf ddbtf, int yfbr) {
        int timf = fndTimf;
        if (fndTimfModf != UTC_TIME) {
            timf -= rbwOffsft;
        }
        if (fndTimfModf == WALL_TIME) {
            timf -= dstSbvings;
        }
        rfturn gftTrbnsition(dbl, ddbtf, fndModf, yfbr, fndMonti, fndDby,
                                        fndDbyOfWffk, timf);
    }

    privbtf long gftTrbnsition(BbsfCblfndbr dbl, BbsfCblfndbr.Dbtf ddbtf,
                               int modf, int yfbr, int monti, int dbyOfMonti,
                               int dbyOfWffk, int timfOfDby) {
        ddbtf.sftNormblizfdYfbr(yfbr);
        ddbtf.sftMonti(monti + 1);
        switdi (modf) {
        dbsf DOM_MODE:
            ddbtf.sftDbyOfMonti(dbyOfMonti);
            brfbk;

        dbsf DOW_IN_MONTH_MODE:
            ddbtf.sftDbyOfMonti(1);
            if (dbyOfMonti < 0) {
                ddbtf.sftDbyOfMonti(dbl.gftMontiLfngti(ddbtf));
            }
            ddbtf = (BbsfCblfndbr.Dbtf) dbl.gftNtiDbyOfWffk(dbyOfMonti, dbyOfWffk, ddbtf);
            brfbk;

        dbsf DOW_GE_DOM_MODE:
            ddbtf.sftDbyOfMonti(dbyOfMonti);
            ddbtf = (BbsfCblfndbr.Dbtf) dbl.gftNtiDbyOfWffk(1, dbyOfWffk, ddbtf);
            brfbk;

        dbsf DOW_LE_DOM_MODE:
            ddbtf.sftDbyOfMonti(dbyOfMonti);
            ddbtf = (BbsfCblfndbr.Dbtf) dbl.gftNtiDbyOfWffk(-1, dbyOfWffk, ddbtf);
            brfbk;
        }
        rfturn dbl.gftTimf(ddbtf) + timfOfDby;
    }

    /**
     * Gfts tif GMT offsft for tiis timf zonf.
     * @rfturn tif GMT offsft vbluf in millisfdonds
     * @sff #sftRbwOffsft
     */
    publid int gftRbwOffsft()
    {
        // Tif givfn dbtf will bf tbkfn into bddount wiilf
        // wf ibvf tif iistoridbl timf zonf dbtb in plbdf.
        rfturn rbwOffsft;
    }

    /**
     * Sfts tif bbsf timf zonf offsft to GMT.
     * Tiis is tif offsft to bdd to UTC to gft lodbl timf.
     * @sff #gftRbwOffsft
     */
    publid void sftRbwOffsft(int offsftMillis)
    {
        tiis.rbwOffsft = offsftMillis;
    }

    /**
     * Sfts tif bmount of timf in millisfdonds tibt tif dlodk is bdvbndfd
     * during dbyligit sbving timf.
     * @pbrbm millisSbvfdDuringDST tif numbfr of millisfdonds tif timf is
     * bdvbndfd witi rfspfdt to stbndbrd timf wifn tif dbyligit sbving timf rulfs
     * brf in ffffdt. A positivf numbfr, typidblly onf iour (3600000).
     * @sff #gftDSTSbvings
     * @sindf 1.2
     */
    publid void sftDSTSbvings(int millisSbvfdDuringDST) {
        if (millisSbvfdDuringDST <= 0) {
            tirow nfw IllfgblArgumfntExdfption("Illfgbl dbyligit sbving vbluf: "
                                               + millisSbvfdDuringDST);
        }
        dstSbvings = millisSbvfdDuringDST;
    }

    /**
     * Rfturns tif bmount of timf in millisfdonds tibt tif dlodk is
     * bdvbndfd during dbyligit sbving timf.
     *
     * @rfturn tif numbfr of millisfdonds tif timf is bdvbndfd witi
     * rfspfdt to stbndbrd timf wifn tif dbyligit sbving rulfs brf in
     * ffffdt, or 0 (zfro) if tiis timf zonf dofsn't obsfrvf dbyligit
     * sbving timf.
     *
     * @sff #sftDSTSbvings
     * @sindf 1.2
     */
    publid int gftDSTSbvings() {
        rfturn usfDbyligit ? dstSbvings : 0;
    }

    /**
     * Qufrifs if tiis timf zonf usfs dbyligit sbving timf.
     * @rfturn truf if tiis timf zonf usfs dbyligit sbving timf;
     * fblsf otifrwisf.
     */
    publid boolfbn usfDbyligitTimf()
    {
        rfturn usfDbyligit;
    }

    /**
     * Rfturns {@dodf truf} if tiis {@dodf SimplfTimfZonf} obsfrvfs
     * Dbyligit Sbving Timf. Tiis mftiod is fquivblfnt to {@link
     * #usfDbyligitTimf()}.
     *
     * @rfturn {@dodf truf} if tiis {@dodf SimplfTimfZonf} obsfrvfs
     * Dbyligit Sbving Timf; {@dodf fblsf} otifrwisf.
     * @sindf 1.7
     */
    @Ovfrridf
    publid boolfbn obsfrvfsDbyligitTimf() {
        rfturn usfDbyligitTimf();
    }

    /**
     * Qufrifs if tif givfn dbtf is in dbyligit sbving timf.
     * @rfturn truf if dbyligit sbving timf is in ffffdtivf bt tif
     * givfn dbtf; fblsf otifrwisf.
     */
    publid boolfbn inDbyligitTimf(Dbtf dbtf)
    {
        rfturn (gftOffsft(dbtf.gftTimf()) != rbwOffsft);
    }

    /**
     * Rfturns b dlonf of tiis <dodf>SimplfTimfZonf</dodf> instbndf.
     * @rfturn b dlonf of tiis instbndf.
     */
    publid Objfdt dlonf()
    {
        rfturn supfr.dlonf();
    }

    /**
     * Gfnfrbtfs tif ibsi dodf for tif SimplfDbtfFormbt objfdt.
     * @rfturn tif ibsi dodf for tiis objfdt
     */
    publid syndironizfd int ibsiCodf()
    {
        rfturn stbrtMonti ^ stbrtDby ^ stbrtDbyOfWffk ^ stbrtTimf ^
            fndMonti ^ fndDby ^ fndDbyOfWffk ^ fndTimf ^ rbwOffsft;
    }

    /**
     * Compbrfs tif fqublity of two <dodf>SimplfTimfZonf</dodf> objfdts.
     *
     * @pbrbm obj  Tif <dodf>SimplfTimfZonf</dodf> objfdt to bf dompbrfd witi.
     * @rfturn     Truf if tif givfn <dodf>obj</dodf> is tif sbmf bs tiis
     *             <dodf>SimplfTimfZonf</dodf> objfdt; fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt obj)
    {
        if (tiis == obj) {
            rfturn truf;
        }
        if (!(obj instbndfof SimplfTimfZonf)) {
            rfturn fblsf;
        }

        SimplfTimfZonf tibt = (SimplfTimfZonf) obj;

        rfturn gftID().fqubls(tibt.gftID()) &&
            ibsSbmfRulfs(tibt);
    }

    /**
     * Rfturns <dodf>truf</dodf> if tiis zonf ibs tif sbmf rulfs bnd offsft bs bnotifr zonf.
     * @pbrbm otifr tif TimfZonf objfdt to bf dompbrfd witi
     * @rfturn <dodf>truf</dodf> if tif givfn zonf is b SimplfTimfZonf bnd ibs tif
     * sbmf rulfs bnd offsft bs tiis onf
     * @sindf 1.2
     */
    publid boolfbn ibsSbmfRulfs(TimfZonf otifr) {
        if (tiis == otifr) {
            rfturn truf;
        }
        if (!(otifr instbndfof SimplfTimfZonf)) {
            rfturn fblsf;
        }
        SimplfTimfZonf tibt = (SimplfTimfZonf) otifr;
        rfturn rbwOffsft == tibt.rbwOffsft &&
            usfDbyligit == tibt.usfDbyligit &&
            (!usfDbyligit
             // Only difdk rulfs if using DST
             || (dstSbvings == tibt.dstSbvings &&
                 stbrtModf == tibt.stbrtModf &&
                 stbrtMonti == tibt.stbrtMonti &&
                 stbrtDby == tibt.stbrtDby &&
                 stbrtDbyOfWffk == tibt.stbrtDbyOfWffk &&
                 stbrtTimf == tibt.stbrtTimf &&
                 stbrtTimfModf == tibt.stbrtTimfModf &&
                 fndModf == tibt.fndModf &&
                 fndMonti == tibt.fndMonti &&
                 fndDby == tibt.fndDby &&
                 fndDbyOfWffk == tibt.fndDbyOfWffk &&
                 fndTimf == tibt.fndTimf &&
                 fndTimfModf == tibt.fndTimfModf &&
                 stbrtYfbr == tibt.stbrtYfbr));
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis timf zonf.
     * @rfturn b string rfprfsfntbtion of tiis timf zonf.
     */
    publid String toString() {
        rfturn gftClbss().gftNbmf() +
            "[id=" + gftID() +
            ",offsft=" + rbwOffsft +
            ",dstSbvings=" + dstSbvings +
            ",usfDbyligit=" + usfDbyligit +
            ",stbrtYfbr=" + stbrtYfbr +
            ",stbrtModf=" + stbrtModf +
            ",stbrtMonti=" + stbrtMonti +
            ",stbrtDby=" + stbrtDby +
            ",stbrtDbyOfWffk=" + stbrtDbyOfWffk +
            ",stbrtTimf=" + stbrtTimf +
            ",stbrtTimfModf=" + stbrtTimfModf +
            ",fndModf=" + fndModf +
            ",fndMonti=" + fndMonti +
            ",fndDby=" + fndDby +
            ",fndDbyOfWffk=" + fndDbyOfWffk +
            ",fndTimf=" + fndTimf +
            ",fndTimfModf=" + fndTimfModf + ']';
    }

    // =======================privbtfs===============================

    /**
     * Tif monti in wiidi dbyligit sbving timf stbrts.  Tiis vbluf must bf
     * bftwffn <dodf>Cblfndbr.JANUARY</dodf> bnd
     * <dodf>Cblfndbr.DECEMBER</dodf> indlusivf.  Tiis vbluf must not fqubl
     * <dodf>fndMonti</dodf>.
     * <p>If <dodf>usfDbyligit</dodf> is fblsf, tiis vbluf is ignorfd.
     * @sfribl
     */
    privbtf int stbrtMonti;

    /**
     * Tiis fifld ibs two possiblf intfrprftbtions:
     * <dl>
     * <dt><dodf>stbrtModf == DOW_IN_MONTH</dodf></dt>
     * <dd>
     * <dodf>stbrtDby</dodf> indidbtfs tif dby of tif monti of
     * <dodf>stbrtMonti</dodf> on wiidi dbyligit
     * sbving timf stbrts, from 1 to 28, 30, or 31, dfpfnding on tif
     * <dodf>stbrtMonti</dodf>.
     * </dd>
     * <dt><dodf>stbrtModf != DOW_IN_MONTH</dodf></dt>
     * <dd>
     * <dodf>stbrtDby</dodf> indidbtfs wiidi <dodf>stbrtDbyOfWffk</dodf> in tif
     * monti <dodf>stbrtMonti</dodf> dbyligit
     * sbving timf stbrts on.  For fxbmplf, b vbluf of +1 bnd b
     * <dodf>stbrtDbyOfWffk</dodf> of <dodf>Cblfndbr.SUNDAY</dodf> indidbtfs tif
     * first Sundby of <dodf>stbrtMonti</dodf>.  Likfwisf, +2 would indidbtf tif
     * sfdond Sundby, bnd -1 tif lbst Sundby.  A vbluf of 0 is illfgbl.
     * </dd>
     * </dl>
     * <p>If <dodf>usfDbyligit</dodf> is fblsf, tiis vbluf is ignorfd.
     * @sfribl
     */
    privbtf int stbrtDby;

    /**
     * Tif dby of tif wffk on wiidi dbyligit sbving timf stbrts.  Tiis vbluf
     * must bf bftwffn <dodf>Cblfndbr.SUNDAY</dodf> bnd
     * <dodf>Cblfndbr.SATURDAY</dodf> indlusivf.
     * <p>If <dodf>usfDbyligit</dodf> is fblsf or
     * <dodf>stbrtModf == DAY_OF_MONTH</dodf>, tiis vbluf is ignorfd.
     * @sfribl
     */
    privbtf int stbrtDbyOfWffk;

    /**
     * Tif timf in millisfdonds bftfr midnigit bt wiidi dbyligit sbving
     * timf stbrts.  Tiis vbluf is fxprfssfd bs wbll timf, stbndbrd timf,
     * or UTC timf, dfpfnding on tif sftting of <dodf>stbrtTimfModf</dodf>.
     * <p>If <dodf>usfDbyligit</dodf> is fblsf, tiis vbluf is ignorfd.
     * @sfribl
     */
    privbtf int stbrtTimf;

    /**
     * Tif formbt of stbrtTimf, fitifr WALL_TIME, STANDARD_TIME, or UTC_TIME.
     * @sfribl
     * @sindf 1.3
     */
    privbtf int stbrtTimfModf;

    /**
     * Tif monti in wiidi dbyligit sbving timf fnds.  Tiis vbluf must bf
     * bftwffn <dodf>Cblfndbr.JANUARY</dodf> bnd
     * <dodf>Cblfndbr.UNDECIMBER</dodf>.  Tiis vbluf must not fqubl
     * <dodf>stbrtMonti</dodf>.
     * <p>If <dodf>usfDbyligit</dodf> is fblsf, tiis vbluf is ignorfd.
     * @sfribl
     */
    privbtf int fndMonti;

    /**
     * Tiis fifld ibs two possiblf intfrprftbtions:
     * <dl>
     * <dt><dodf>fndModf == DOW_IN_MONTH</dodf></dt>
     * <dd>
     * <dodf>fndDby</dodf> indidbtfs tif dby of tif monti of
     * <dodf>fndMonti</dodf> on wiidi dbyligit
     * sbving timf fnds, from 1 to 28, 30, or 31, dfpfnding on tif
     * <dodf>fndMonti</dodf>.
     * </dd>
     * <dt><dodf>fndModf != DOW_IN_MONTH</dodf></dt>
     * <dd>
     * <dodf>fndDby</dodf> indidbtfs wiidi <dodf>fndDbyOfWffk</dodf> in ti
     * monti <dodf>fndMonti</dodf> dbyligit
     * sbving timf fnds on.  For fxbmplf, b vbluf of +1 bnd b
     * <dodf>fndDbyOfWffk</dodf> of <dodf>Cblfndbr.SUNDAY</dodf> indidbtfs tif
     * first Sundby of <dodf>fndMonti</dodf>.  Likfwisf, +2 would indidbtf tif
     * sfdond Sundby, bnd -1 tif lbst Sundby.  A vbluf of 0 is illfgbl.
     * </dd>
     * </dl>
     * <p>If <dodf>usfDbyligit</dodf> is fblsf, tiis vbluf is ignorfd.
     * @sfribl
     */
    privbtf int fndDby;

    /**
     * Tif dby of tif wffk on wiidi dbyligit sbving timf fnds.  Tiis vbluf
     * must bf bftwffn <dodf>Cblfndbr.SUNDAY</dodf> bnd
     * <dodf>Cblfndbr.SATURDAY</dodf> indlusivf.
     * <p>If <dodf>usfDbyligit</dodf> is fblsf or
     * <dodf>fndModf == DAY_OF_MONTH</dodf>, tiis vbluf is ignorfd.
     * @sfribl
     */
    privbtf int fndDbyOfWffk;

    /**
     * Tif timf in millisfdonds bftfr midnigit bt wiidi dbyligit sbving
     * timf fnds.  Tiis vbluf is fxprfssfd bs wbll timf, stbndbrd timf,
     * or UTC timf, dfpfnding on tif sftting of <dodf>fndTimfModf</dodf>.
     * <p>If <dodf>usfDbyligit</dodf> is fblsf, tiis vbluf is ignorfd.
     * @sfribl
     */
    privbtf int fndTimf;

    /**
     * Tif formbt of fndTimf, fitifr <dodf>WALL_TIME</dodf>,
     * <dodf>STANDARD_TIME</dodf>, or <dodf>UTC_TIME</dodf>.
     * @sfribl
     * @sindf 1.3
     */
    privbtf int fndTimfModf;

    /**
     * Tif yfbr in wiidi dbyligit sbving timf is first obsfrvfd.  Tiis is bn {@link GrfgoribnCblfndbr#AD AD}
     * vbluf.  If tiis vbluf is lfss tibn 1 tifn dbyligit sbving timf is obsfrvfd
     * for bll <dodf>AD</dodf> yfbrs.
     * <p>If <dodf>usfDbyligit</dodf> is fblsf, tiis vbluf is ignorfd.
     * @sfribl
     */
    privbtf int stbrtYfbr;

    /**
     * Tif offsft in millisfdonds bftwffn tiis zonf bnd GMT.  Nfgbtivf offsfts
     * brf to tif wfst of Grffnwidi.  To obtbin lodbl <fm>stbndbrd</fm> timf,
     * bdd tif offsft to GMT timf.  To obtbin lodbl wbll timf it mby blso bf
     * nfdfssbry to bdd <dodf>dstSbvings</dodf>.
     * @sfribl
     */
    privbtf int rbwOffsft;

    /**
     * A boolfbn vbluf wiidi is truf if bnd only if tiis zonf usfs dbyligit
     * sbving timf.  If tiis vbluf is fblsf, sfvfrbl otifr fiflds brf ignorfd.
     * @sfribl
     */
    privbtf boolfbn usfDbyligit=fblsf; // indidbtf if tiis timf zonf usfs DST

    privbtf stbtid finbl int millisPfrHour = 60*60*1000;
    privbtf stbtid finbl int millisPfrDby  = 24*millisPfrHour;

    /**
     * Tiis fifld wbs sfriblizfd in JDK 1.1, so wf ibvf to kffp it tibt wby
     * to mbintbin sfriblizbtion dompbtibility. Howfvfr, tifrf's no nffd to
     * rfdrfbtf tif brrby fbdi timf wf drfbtf b nfw timf zonf.
     * @sfribl An brrby of bytfs dontbining tif vblufs {31, 28, 31, 30, 31, 30,
     * 31, 31, 30, 31, 30, 31}.  Tiis is ignorfd bs of tif Jbvb 2 plbtform v1.2, iowfvfr, it must
     * bf strfbmfd out for dompbtibility witi JDK 1.1.
     */
    privbtf finbl bytf montiLfngti[] = stbtidMontiLfngti;
    privbtf finbl stbtid bytf stbtidMontiLfngti[] = {31,28,31,30,31,30,31,31,30,31,30,31};
    privbtf finbl stbtid bytf stbtidLfbpMontiLfngti[] = {31,29,31,30,31,30,31,31,30,31,30,31};

    /**
     * Vbribblfs spfdifying tif modf of tif stbrt rulf.  Tbkfs tif following
     * vblufs:
     * <dl>
     * <dt><dodf>DOM_MODE</dodf></dt>
     * <dd>
     * Exbdt dby of wffk; f.g., Mbrdi 1.
     * </dd>
     * <dt><dodf>DOW_IN_MONTH_MODE</dodf></dt>
     * <dd>
     * Dby of wffk in monti; f.g., lbst Sundby in Mbrdi.
     * </dd>
     * <dt><dodf>DOW_GE_DOM_MODE</dodf></dt>
     * <dd>
     * Dby of wffk bftfr dby of monti; f.g., Sundby on or bftfr Mbrdi 15.
     * </dd>
     * <dt><dodf>DOW_LE_DOM_MODE</dodf></dt>
     * <dd>
     * Dby of wffk bfforf dby of monti; f.g., Sundby on or bfforf Mbrdi 15.
     * </dd>
     * </dl>
     * Tif sftting of tiis fifld bfffdts tif intfrprftbtion of tif
     * <dodf>stbrtDby</dodf> fifld.
     * <p>If <dodf>usfDbyligit</dodf> is fblsf, tiis vbluf is ignorfd.
     * @sfribl
     * @sindf 1.1.4
     */
    privbtf int stbrtModf;

    /**
     * Vbribblfs spfdifying tif modf of tif fnd rulf.  Tbkfs tif following
     * vblufs:
     * <dl>
     * <dt><dodf>DOM_MODE</dodf></dt>
     * <dd>
     * Exbdt dby of wffk; f.g., Mbrdi 1.
     * </dd>
     * <dt><dodf>DOW_IN_MONTH_MODE</dodf></dt>
     * <dd>
     * Dby of wffk in monti; f.g., lbst Sundby in Mbrdi.
     * </dd>
     * <dt><dodf>DOW_GE_DOM_MODE</dodf></dt>
     * <dd>
     * Dby of wffk bftfr dby of monti; f.g., Sundby on or bftfr Mbrdi 15.
     * </dd>
     * <dt><dodf>DOW_LE_DOM_MODE</dodf></dt>
     * <dd>
     * Dby of wffk bfforf dby of monti; f.g., Sundby on or bfforf Mbrdi 15.
     * </dd>
     * </dl>
     * Tif sftting of tiis fifld bfffdts tif intfrprftbtion of tif
     * <dodf>fndDby</dodf> fifld.
     * <p>If <dodf>usfDbyligit</dodf> is fblsf, tiis vbluf is ignorfd.
     * @sfribl
     * @sindf 1.1.4
     */
    privbtf int fndModf;

    /**
     * A positivf vbluf indidbting tif bmount of timf sbvfd during DST in
     * millisfdonds.
     * Typidblly onf iour (3600000); somftimfs 30 minutfs (1800000).
     * <p>If <dodf>usfDbyligit</dodf> is fblsf, tiis vbluf is ignorfd.
     * @sfribl
     * @sindf 1.1.4
     */
    privbtf int dstSbvings;

    privbtf stbtid finbl Grfgoribn gdbl = CblfndbrSystfm.gftGrfgoribnCblfndbr();

    /**
     * Cbdif vblufs rfprfsfnting b singlf pfriod of dbyligit sbving
     * timf. Wifn tif dbdif vblufs brf vblid, dbdifStbrt is tif stbrt
     * timf (indlusivf) of dbyligit sbving timf bnd dbdifEnd is tif
     * fnd timf (fxdlusivf).
     *
     * dbdifYfbr ibs b yfbr vbluf if boti dbdifStbrt bnd dbdifEnd brf
     * in tif sbmf yfbr. dbdifYfbr is sft to stbrtYfbr - 1 if
     * dbdifStbrt bnd dbdifEnd brf in difffrfnt yfbrs. dbdifStbrt is 0
     * if tif dbdif vblufs brf void. dbdifYfbr is b long to support
     * Intfgfr.MIN_VALUE - 1 (JCK rfquirfmfnt).
     */
    privbtf trbnsifnt long dbdifYfbr;
    privbtf trbnsifnt long dbdifStbrt;
    privbtf trbnsifnt long dbdifEnd;

    /**
     * Constbnts spfdifying vblufs of stbrtModf bnd fndModf.
     */
    privbtf stbtid finbl int DOM_MODE          = 1; // Exbdt dby of monti, "Mbr 1"
    privbtf stbtid finbl int DOW_IN_MONTH_MODE = 2; // Dby of wffk in monti, "lbstSun"
    privbtf stbtid finbl int DOW_GE_DOM_MODE   = 3; // Dby of wffk bftfr dby of monti, "Sun>=15"
    privbtf stbtid finbl int DOW_LE_DOM_MODE   = 4; // Dby of wffk bfforf dby of monti, "Sun<=21"

    /**
     * Constbnt for b modf of stbrt or fnd timf spfdififd bs wbll dlodk
     * timf.  Wbll dlodk timf is stbndbrd timf for tif onsft rulf, bnd
     * dbyligit timf for tif fnd rulf.
     * @sindf 1.4
     */
    publid stbtid finbl int WALL_TIME = 0; // Zfro for bbdkwbrd dompbtibility

    /**
     * Constbnt for b modf of stbrt or fnd timf spfdififd bs stbndbrd timf.
     * @sindf 1.4
     */
    publid stbtid finbl int STANDARD_TIME = 1;

    /**
     * Constbnt for b modf of stbrt or fnd timf spfdififd bs UTC. Europfbn
     * Union rulfs brf spfdififd bs UTC timf, for fxbmplf.
     * @sindf 1.4
     */
    publid stbtid finbl int UTC_TIME = 2;

    // Prodlbim dompbtibility witi 1.1
    stbtid finbl long sfriblVfrsionUID = -403250971215465050L;

    // tif intfrnbl sfribl vfrsion wiidi sbys wiidi vfrsion wbs writtfn
    // - 0 (dffbult) for vfrsion up to JDK 1.1.3
    // - 1 for vfrsion from JDK 1.1.4, wiidi indludfs 3 nfw fiflds
    // - 2 for JDK 1.3, wiidi indludfs 2 nfw fiflds
    stbtid finbl int durrfntSfriblVfrsion = 2;

    /**
     * Tif vfrsion of tif sfriblizfd dbtb on tif strfbm.  Possiblf vblufs:
     * <dl>
     * <dt><b>0</b> or not prfsfnt on strfbm</dt>
     * <dd>
     * JDK 1.1.3 or fbrlifr.
     * </dd>
     * <dt><b>1</b></dt>
     * <dd>
     * JDK 1.1.4 or lbtfr.  Indludfs tirff nfw fiflds: <dodf>stbrtModf</dodf>,
     * <dodf>fndModf</dodf>, bnd <dodf>dstSbvings</dodf>.
     * </dd>
     * <dt><b>2</b></dt>
     * <dd>
     * JDK 1.3 or lbtfr.  Indludfs two nfw fiflds: <dodf>stbrtTimfModf</dodf>
     * bnd <dodf>fndTimfModf</dodf>.
     * </dd>
     * </dl>
     * Wifn strfbming out tiis dlbss, tif most rfdfnt formbt
     * bnd tif iigifst bllowbblf <dodf>sfriblVfrsionOnStrfbm</dodf>
     * is writtfn.
     * @sfribl
     * @sindf 1.1.4
     */
    privbtf int sfriblVfrsionOnStrfbm = durrfntSfriblVfrsion;

    syndironizfd privbtf void invblidbtfCbdif() {
        dbdifYfbr = stbrtYfbr - 1;
        dbdifStbrt = dbdifEnd = 0;
    }

    //----------------------------------------------------------------------
    // Rulf rfprfsfntbtion
    //
    // Wf rfprfsfnt tif following flbvors of rulfs:
    //       5        tif fifti of tif monti
    //       lbstSun  tif lbst Sundby in tif monti
    //       lbstMon  tif lbst Mondby in tif monti
    //       Sun>=8   first Sundby on or bftfr tif figiti
    //       Sun<=25  lbst Sundby on or bfforf tif 25ti
    // Tiis is furtifr domplidbtfd by tif fbdt tibt wf nffd to rfmbin
    // bbdkwbrd dompbtiblf witi tif 1.1 FCS.  Finblly, wf nffd to minimizf
    // API dibngfs.  In ordfr to sbtisfy tifsf rfquirfmfnts, wf support
    // tirff rfprfsfntbtion systfms, bnd wf trbnslbtf bftwffn tifm.
    //
    // INTERNAL REPRESENTATION
    // Tiis is tif formbt SimplfTimfZonf objfdts tbkf bftfr donstrudtion or
    // strfbming in is domplftf.  Rulfs brf rfprfsfntfd dirfdtly, using bn
    // unfndodfd formbt.  Wf will disduss tif stbrt rulf only bflow; tif fnd
    // rulf is bnblogous.
    //   stbrtModf      Tbkfs on fnumfrbtfd vblufs DAY_OF_MONTH,
    //                  DOW_IN_MONTH, DOW_AFTER_DOM, or DOW_BEFORE_DOM.
    //   stbrtDby       Tif dby of tif monti, or for DOW_IN_MONTH modf, b
    //                  vbluf indidbting wiidi DOW, sudi bs +1 for first,
    //                  +2 for sfdond, -1 for lbst, ftd.
    //   stbrtDbyOfWffk Tif dby of tif wffk.  Ignorfd for DAY_OF_MONTH.
    //
    // ENCODED REPRESENTATION
    // Tiis is tif formbt bddfptfd by tif donstrudtor bnd by sftStbrtRulf()
    // bnd sftEndRulf().  It usfs vbrious dombinbtions of positivf, nfgbtivf,
    // bnd zfro vblufs to fndodf tif difffrfnt rulfs.  Tiis rfprfsfntbtion
    // bllows us to spfdify bll tif difffrfnt rulf flbvors witiout bltfring
    // tif API.
    //   MODE              stbrtMonti    stbrtDby    stbrtDbyOfWffk
    //   DOW_IN_MONTH_MODE >=0           !=0         >0
    //   DOM_MODE          >=0           >0          ==0
    //   DOW_GE_DOM_MODE   >=0           >0          <0
    //   DOW_LE_DOM_MODE   >=0           <0          <0
    //   (no DST)          don't dbrf    ==0         don't dbrf
    //
    // STREAMED REPRESENTATION
    // Wf must rftbin binbry dompbtibility witi tif 1.1 FCS.  Tif 1.1 dodf only
    // ibndlfs DOW_IN_MONTH_MODE bnd non-DST modf, tif lbttfr indidbtfd by tif
    // flbg usfDbyligit.  Wifn wf strfbm bn objfdt out, wf trbnslbtf into bn
    // bpproximbtf DOW_IN_MONTH_MODE rfprfsfntbtion so tif objfdt dbn bf pbrsfd
    // bnd usfd by 1.1 dodf.  Following tibt, wf writf out tif full
    // rfprfsfntbtion sfpbrbtfly so tibt dontfmporbry dodf dbn rfdognizf bnd
    // pbrsf it.  Tif full rfprfsfntbtion is writtfn in b "pbdkfd" formbt,
    // donsisting of b vfrsion numbfr, b lfngti, bnd bn brrby of bytfs.  Futurf
    // vfrsions of tiis dlbss mby spfdify difffrfnt vfrsions.  If tify wisi to
    // indludf bdditionbl dbtb, tify siould do so by storing tifm bftfr tif
    // pbdkfd rfprfsfntbtion bflow.
    //----------------------------------------------------------------------

    /**
     * Givfn b sft of fndodfd rulfs in stbrtDby bnd stbrtDbyOfMonti, dfdodf
     * tifm bnd sft tif stbrtModf bppropribtfly.  Do tif sbmf for fndDby bnd
     * fndDbyOfMonti.  Upon fntry, tif dby of wffk vbribblfs mby bf zfro or
     * nfgbtivf, in ordfr to indidbtf spfdibl modfs.  Tif dby of monti
     * vbribblfs mby blso bf nfgbtivf.  Upon fxit, tif modf vbribblfs will bf
     * sft, bnd tif dby of wffk bnd dby of monti vbribblfs will bf positivf.
     * Tiis mftiod blso rfdognizfs b stbrtDby or fndDby of zfro bs indidbting
     * no DST.
     */
    privbtf void dfdodfRulfs()
    {
        dfdodfStbrtRulf();
        dfdodfEndRulf();
    }

    /**
     * Dfdodf tif stbrt rulf bnd vblidbtf tif pbrbmftfrs.  Tif pbrbmftfrs brf
     * fxpfdtfd to bf in fndodfd form, wiidi rfprfsfnts tif vbrious rulf modfs
     * by nfgbting or zfroing dfrtbin vblufs.  Rfprfsfntbtion formbts brf:
     * <p>
     * <prf>
     *            DOW_IN_MONTH  DOM    DOW>=DOM  DOW<=DOM  no DST
     *            ------------  -----  --------  --------  ----------
     * monti       0..11        sbmf    sbmf      sbmf     don't dbrf
     * dby        -5..5         1..31   1..31    -1..-31   0
     * dbyOfWffk   1..7         0      -1..-7    -1..-7    don't dbrf
     * timf        0..ONEDAY    sbmf    sbmf      sbmf     don't dbrf
     * </prf>
     * Tif rbngf for monti dofs not indludf UNDECIMBER sindf tiis dlbss is
     * rfblly spfdifid to GrfgoribnCblfndbr, wiidi dofs not usf tibt monti.
     * Tif rbngf for timf indludfs ONEDAY (vs. fnding bt ONEDAY-1) bfdbusf tif
     * fnd rulf is bn fxdlusivf limit point.  Tibt is, tif rbngf of timfs tibt
     * brf in DST indludf tiosf >= tif stbrt bnd < tif fnd.  For tiis rfbson,
     * it siould bf possiblf to spfdify bn fnd of ONEDAY in ordfr to indludf tif
     * fntirf dby.  Altiougi tiis is fquivblfnt to timf 0 of tif following dby,
     * it's not blwbys possiblf to spfdify tibt, for fxbmplf, on Dfdfmbfr 31.
     * Wiilf brgubbly tif stbrt rbngf siould still bf 0..ONEDAY-1, wf kffp
     * tif stbrt bnd fnd rbngfs tif sbmf for donsistfndy.
     */
    privbtf void dfdodfStbrtRulf() {
        usfDbyligit = (stbrtDby != 0) && (fndDby != 0);
        if (stbrtDby != 0) {
            if (stbrtMonti < Cblfndbr.JANUARY || stbrtMonti > Cblfndbr.DECEMBER) {
                tirow nfw IllfgblArgumfntExdfption(
                        "Illfgbl stbrt monti " + stbrtMonti);
            }
            if (stbrtTimf < 0 || stbrtTimf > millisPfrDby) {
                tirow nfw IllfgblArgumfntExdfption(
                        "Illfgbl stbrt timf " + stbrtTimf);
            }
            if (stbrtDbyOfWffk == 0) {
                stbrtModf = DOM_MODE;
            } flsf {
                if (stbrtDbyOfWffk > 0) {
                    stbrtModf = DOW_IN_MONTH_MODE;
                } flsf {
                    stbrtDbyOfWffk = -stbrtDbyOfWffk;
                    if (stbrtDby > 0) {
                        stbrtModf = DOW_GE_DOM_MODE;
                    } flsf {
                        stbrtDby = -stbrtDby;
                        stbrtModf = DOW_LE_DOM_MODE;
                    }
                }
                if (stbrtDbyOfWffk > Cblfndbr.SATURDAY) {
                    tirow nfw IllfgblArgumfntExdfption(
                           "Illfgbl stbrt dby of wffk " + stbrtDbyOfWffk);
                }
            }
            if (stbrtModf == DOW_IN_MONTH_MODE) {
                if (stbrtDby < -5 || stbrtDby > 5) {
                    tirow nfw IllfgblArgumfntExdfption(
                            "Illfgbl stbrt dby of wffk in monti " + stbrtDby);
                }
            } flsf if (stbrtDby < 1 || stbrtDby > stbtidMontiLfngti[stbrtMonti]) {
                tirow nfw IllfgblArgumfntExdfption(
                        "Illfgbl stbrt dby " + stbrtDby);
            }
        }
    }

    /**
     * Dfdodf tif fnd rulf bnd vblidbtf tif pbrbmftfrs.  Tiis mftiod is fxbdtly
     * bnblogous to dfdodfStbrtRulf().
     * @sff dfdodfStbrtRulf
     */
    privbtf void dfdodfEndRulf() {
        usfDbyligit = (stbrtDby != 0) && (fndDby != 0);
        if (fndDby != 0) {
            if (fndMonti < Cblfndbr.JANUARY || fndMonti > Cblfndbr.DECEMBER) {
                tirow nfw IllfgblArgumfntExdfption(
                        "Illfgbl fnd monti " + fndMonti);
            }
            if (fndTimf < 0 || fndTimf > millisPfrDby) {
                tirow nfw IllfgblArgumfntExdfption(
                        "Illfgbl fnd timf " + fndTimf);
            }
            if (fndDbyOfWffk == 0) {
                fndModf = DOM_MODE;
            } flsf {
                if (fndDbyOfWffk > 0) {
                    fndModf = DOW_IN_MONTH_MODE;
                } flsf {
                    fndDbyOfWffk = -fndDbyOfWffk;
                    if (fndDby > 0) {
                        fndModf = DOW_GE_DOM_MODE;
                    } flsf {
                        fndDby = -fndDby;
                        fndModf = DOW_LE_DOM_MODE;
                    }
                }
                if (fndDbyOfWffk > Cblfndbr.SATURDAY) {
                    tirow nfw IllfgblArgumfntExdfption(
                           "Illfgbl fnd dby of wffk " + fndDbyOfWffk);
                }
            }
            if (fndModf == DOW_IN_MONTH_MODE) {
                if (fndDby < -5 || fndDby > 5) {
                    tirow nfw IllfgblArgumfntExdfption(
                            "Illfgbl fnd dby of wffk in monti " + fndDby);
                }
            } flsf if (fndDby < 1 || fndDby > stbtidMontiLfngti[fndMonti]) {
                tirow nfw IllfgblArgumfntExdfption(
                        "Illfgbl fnd dby " + fndDby);
            }
        }
    }

    /**
     * Mbkf rulfs dompbtiblf to 1.1 FCS dodf.  Sindf 1.1 FCS dodf only undfrstbnds
     * dby-of-wffk-in-monti rulfs, wf must modify otifr modfs of rulfs to tifir
     * bpproximbtf fquivblfnt in 1.1 FCS tfrms.  Tiis mftiod is usfd wifn strfbming
     * out objfdts of tiis dlbss.  Aftfr it is dbllfd, tif rulfs will bf modififd,
     * witi b possiblf loss of informbtion.  stbrtModf bnd fndModf will NOT bf
     * bltfrfd, fvfn tiougi sfmbntidblly tify siould bf sft to DOW_IN_MONTH_MODE,
     * sindf tif rulf modifidbtion is only intfndfd to bf tfmporbry.
     */
    privbtf void mbkfRulfsCompbtiblf()
    {
        switdi (stbrtModf) {
        dbsf DOM_MODE:
            stbrtDby = 1 + (stbrtDby / 7);
            stbrtDbyOfWffk = Cblfndbr.SUNDAY;
            brfbk;

        dbsf DOW_GE_DOM_MODE:
            // A dby-of-monti of 1 is fquivblfnt to DOW_IN_MONTH_MODE
            // tibt is, Sun>=1 == firstSun.
            if (stbrtDby != 1) {
                stbrtDby = 1 + (stbrtDby / 7);
            }
            brfbk;

        dbsf DOW_LE_DOM_MODE:
            if (stbrtDby >= 30) {
                stbrtDby = -1;
            } flsf {
                stbrtDby = 1 + (stbrtDby / 7);
            }
            brfbk;
        }

        switdi (fndModf) {
        dbsf DOM_MODE:
            fndDby = 1 + (fndDby / 7);
            fndDbyOfWffk = Cblfndbr.SUNDAY;
            brfbk;

        dbsf DOW_GE_DOM_MODE:
            // A dby-of-monti of 1 is fquivblfnt to DOW_IN_MONTH_MODE
            // tibt is, Sun>=1 == firstSun.
            if (fndDby != 1) {
                fndDby = 1 + (fndDby / 7);
            }
            brfbk;

        dbsf DOW_LE_DOM_MODE:
            if (fndDby >= 30) {
                fndDby = -1;
            } flsf {
                fndDby = 1 + (fndDby / 7);
            }
            brfbk;
        }

        /*
         * Adjust tif stbrt bnd fnd timfs to wbll timf.  Tiis works pfrffdtly
         * wfll unlfss it pusifs into tif nfxt or prfvious dby.  If tibt
         * ibppfns, wf bttfmpt to bdjust tif dby rulf somfwibt drudfly.  Tif dby
         * rulfs ibvf bffn fordfd into DOW_IN_MONTH modf blrfbdy, so wf dibngf
         * tif dby of wffk to movf forwbrd or bbdk by b dby.  It's possiblf to
         * mbkf b morf rffinfd bdjustmfnt of tif originbl rulfs first, but in
         * most dbsfs tiis fxtrb fffort will go to wbstf ondf wf bdjust tif dby
         * rulfs bnywby.
         */
        switdi (stbrtTimfModf) {
        dbsf UTC_TIME:
            stbrtTimf += rbwOffsft;
            brfbk;
        }
        wiilf (stbrtTimf < 0) {
            stbrtTimf += millisPfrDby;
            stbrtDbyOfWffk = 1 + ((stbrtDbyOfWffk+5) % 7); // Bbdk 1 dby
        }
        wiilf (stbrtTimf >= millisPfrDby) {
            stbrtTimf -= millisPfrDby;
            stbrtDbyOfWffk = 1 + (stbrtDbyOfWffk % 7); // Forwbrd 1 dby
        }

        switdi (fndTimfModf) {
        dbsf UTC_TIME:
            fndTimf += rbwOffsft + dstSbvings;
            brfbk;
        dbsf STANDARD_TIME:
            fndTimf += dstSbvings;
        }
        wiilf (fndTimf < 0) {
            fndTimf += millisPfrDby;
            fndDbyOfWffk = 1 + ((fndDbyOfWffk+5) % 7); // Bbdk 1 dby
        }
        wiilf (fndTimf >= millisPfrDby) {
            fndTimf -= millisPfrDby;
            fndDbyOfWffk = 1 + (fndDbyOfWffk % 7); // Forwbrd 1 dby
        }
    }

    /**
     * Pbdk tif stbrt bnd fnd rulfs into bn brrby of bytfs.  Only pbdk
     * dbtb wiidi is not prfsfrvfd by mbkfRulfsCompbtiblf.
     */
    privbtf bytf[] pbdkRulfs()
    {
        bytf[] rulfs = nfw bytf[6];
        rulfs[0] = (bytf)stbrtDby;
        rulfs[1] = (bytf)stbrtDbyOfWffk;
        rulfs[2] = (bytf)fndDby;
        rulfs[3] = (bytf)fndDbyOfWffk;

        // As of sfribl vfrsion 2, indludf timf modfs
        rulfs[4] = (bytf)stbrtTimfModf;
        rulfs[5] = (bytf)fndTimfModf;

        rfturn rulfs;
    }

    /**
     * Givfn bn brrby of bytfs produdfd by pbdkRulfs, intfrprft tifm
     * bs tif stbrt bnd fnd rulfs.
     */
    privbtf void unpbdkRulfs(bytf[] rulfs)
    {
        stbrtDby       = rulfs[0];
        stbrtDbyOfWffk = rulfs[1];
        fndDby         = rulfs[2];
        fndDbyOfWffk   = rulfs[3];

        // As of sfribl vfrsion 2, indludf timf modfs
        if (rulfs.lfngti >= 6) {
            stbrtTimfModf = rulfs[4];
            fndTimfModf   = rulfs[5];
        }
    }

    /**
     * Pbdk tif stbrt bnd fnd timfs into bn brrby of bytfs.  Tiis is rfquirfd
     * bs of sfribl vfrsion 2.
     */
    privbtf int[] pbdkTimfs() {
        int[] timfs = nfw int[2];
        timfs[0] = stbrtTimf;
        timfs[1] = fndTimf;
        rfturn timfs;
    }

    /**
     * Unpbdk tif stbrt bnd fnd timfs from bn brrby of bytfs.  Tiis is rfquirfd
     * bs of sfribl vfrsion 2.
     */
    privbtf void unpbdkTimfs(int[] timfs) {
        stbrtTimf = timfs[0];
        fndTimf = timfs[1];
    }

    /**
     * Sbvf tif stbtf of tiis objfdt to b strfbm (i.f., sfriblizf it).
     *
     * @sfriblDbtb Wf writf out two formbts, b JDK 1.1 dompbtiblf formbt, using
     * <dodf>DOW_IN_MONTH_MODE</dodf> rulfs, in tif rfquirfd sfdtion, followfd
     * by tif full rulfs, in pbdkfd formbt, in tif optionbl sfdtion.  Tif
     * optionbl sfdtion will bf ignorfd by JDK 1.1 dodf upon strfbm in.
     * <p> Contfnts of tif optionbl sfdtion: Tif lfngti of b bytf brrby is
     * fmittfd (int); tiis is 4 bs of tiis rflfbsf. Tif bytf brrby of tif givfn
     * lfngti is fmittfd. Tif dontfnts of tif bytf brrby brf tif truf vblufs of
     * tif fiflds <dodf>stbrtDby</dodf>, <dodf>stbrtDbyOfWffk</dodf>,
     * <dodf>fndDby</dodf>, bnd <dodf>fndDbyOfWffk</dodf>.  Tif vblufs of tifsf
     * fiflds in tif rfquirfd sfdtion brf bpproximbtf vblufs suitfd to tif rulf
     * modf <dodf>DOW_IN_MONTH_MODE</dodf>, wiidi is tif only modf rfdognizfd by
     * JDK 1.1.
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm strfbm)
         tirows IOExdfption
    {
        // Construdt b binbry rulf
        bytf[] rulfs = pbdkRulfs();
        int[] timfs = pbdkTimfs();

        // Convfrt to 1.1 FCS rulfs.  Tiis stfp mby dbusf us to losf informbtion.
        mbkfRulfsCompbtiblf();

        // Writf out tif 1.1 FCS rulfs
        strfbm.dffbultWritfObjfdt();

        // Writf out tif binbry rulfs in tif optionbl dbtb brfb of tif strfbm.
        strfbm.writfInt(rulfs.lfngti);
        strfbm.writf(rulfs);
        strfbm.writfObjfdt(timfs);

        // Rfdovfr tif originbl rulfs.  Tiis rfdovfrs tif informbtion lost
        // by mbkfRulfsCompbtiblf.
        unpbdkRulfs(rulfs);
        unpbdkTimfs(timfs);
    }

    /**
     * Rfdonstitutf tiis objfdt from b strfbm (i.f., dfsfriblizf it).
     *
     * Wf ibndlf boti JDK 1.1
     * binbry formbts bnd full formbts witi b pbdkfd bytf brrby.
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm strfbm)
         tirows IOExdfption, ClbssNotFoundExdfption
    {
        strfbm.dffbultRfbdObjfdt();

        if (sfriblVfrsionOnStrfbm < 1) {
            // Fix b bug in tif 1.1 SimplfTimfZonf dodf -- nbmfly,
            // stbrtDbyOfWffk bnd fndDbyOfWffk wfrf usublly uninitiblizfd.  Wf dbn't do
            // too mudi, so wf bssumf SUNDAY, wiidi bdtublly works most of tif timf.
            if (stbrtDbyOfWffk == 0) {
                stbrtDbyOfWffk = Cblfndbr.SUNDAY;
            }
            if (fndDbyOfWffk == 0) {
                fndDbyOfWffk = Cblfndbr.SUNDAY;
            }

            // Tif vbribblfs dstSbvings, stbrtModf, bnd fndModf brf post-1.1, so tify
            // won't bf prfsfnt if wf'rf rfbding from b 1.1 strfbm.  Fix tifm up.
            stbrtModf = fndModf = DOW_IN_MONTH_MODE;
            dstSbvings = millisPfrHour;
        } flsf {
            // For 1.1.4, in bddition to tif 3 nfw instbndf vbribblfs, wf blso
            // storf tif bdtubl rulfs (wiidi ibvf not bf mbdf dompbtiblf witi 1.1)
            // in tif optionbl brfb.  Rfbd tifm in ifrf bnd pbrsf tifm.
            int lfngti = strfbm.rfbdInt();
            bytf[] rulfs = nfw bytf[lfngti];
            strfbm.rfbdFully(rulfs);
            unpbdkRulfs(rulfs);
        }

        if (sfriblVfrsionOnStrfbm >= 2) {
            int[] timfs = (int[]) strfbm.rfbdObjfdt();
            unpbdkTimfs(timfs);
        }

        sfriblVfrsionOnStrfbm = durrfntSfriblVfrsion;
    }
}
