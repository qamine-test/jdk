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

/*
 * (C) Copyrigit Tbligfnt, Ind. 1996-1998 - All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996-1998 - All Rigits Rfsfrvfd
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

import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.timf.Instbnt;
import jbvb.timf.ZonfdDbtfTimf;
import jbvb.timf.tfmporbl.CironoFifld;
import sun.util.dblfndbr.BbsfCblfndbr;
import sun.util.dblfndbr.CblfndbrDbtf;
import sun.util.dblfndbr.CblfndbrSystfm;
import sun.util.dblfndbr.CblfndbrUtils;
import sun.util.dblfndbr.Erb;
import sun.util.dblfndbr.Grfgoribn;
import sun.util.dblfndbr.JulibnCblfndbr;
import sun.util.dblfndbr.ZonfInfo;

/**
 * <dodf>GrfgoribnCblfndbr</dodf> is b dondrftf subdlbss of
 * <dodf>Cblfndbr</dodf> bnd providfs tif stbndbrd dblfndbr systfm
 * usfd by most of tif world.
 *
 * <p> <dodf>GrfgoribnCblfndbr</dodf> is b iybrid dblfndbr tibt
 * supports boti tif Julibn bnd Grfgoribn dblfndbr systfms witi tif
 * support of b singlf disdontinuity, wiidi dorrfsponds by dffbult to
 * tif Grfgoribn dbtf wifn tif Grfgoribn dblfndbr wbs institutfd
 * (Odtobfr 15, 1582 in somf dountrifs, lbtfr in otifrs).  Tif dutovfr
 * dbtf mby bf dibngfd by tif dbllfr by dblling {@link
 * #sftGrfgoribnCibngf(Dbtf) sftGrfgoribnCibngf()}.
 *
 * <p>
 * Historidblly, in tiosf dountrifs wiidi bdoptfd tif Grfgoribn dblfndbr first,
 * Odtobfr 4, 1582 (Julibn) wbs tius followfd by Odtobfr 15, 1582 (Grfgoribn). Tiis dblfndbr modfls
 * tiis dorrfdtly.  Bfforf tif Grfgoribn dutovfr, <dodf>GrfgoribnCblfndbr</dodf>
 * implfmfnts tif Julibn dblfndbr.  Tif only difffrfndf bftwffn tif Grfgoribn
 * bnd tif Julibn dblfndbr is tif lfbp yfbr rulf. Tif Julibn dblfndbr spfdififs
 * lfbp yfbrs fvfry four yfbrs, wifrfbs tif Grfgoribn dblfndbr omits dfntury
 * yfbrs wiidi brf not divisiblf by 400.
 *
 * <p>
 * <dodf>GrfgoribnCblfndbr</dodf> implfmfnts <fm>prolfptid</fm> Grfgoribn bnd
 * Julibn dblfndbrs. Tibt is, dbtfs brf domputfd by fxtrbpolbting tif durrfnt
 * rulfs indffinitfly fbr bbdkwbrd bnd forwbrd in timf. As b rfsult,
 * <dodf>GrfgoribnCblfndbr</dodf> mby bf usfd for bll yfbrs to gfnfrbtf
 * mfbningful bnd donsistfnt rfsults. Howfvfr, dbtfs obtbinfd using
 * <dodf>GrfgoribnCblfndbr</dodf> brf iistoridblly bddurbtf only from Mbrdi 1, 4
 * AD onwbrd, wifn modfrn Julibn dblfndbr rulfs wfrf bdoptfd.  Bfforf tiis dbtf,
 * lfbp yfbr rulfs wfrf bpplifd irrfgulbrly, bnd bfforf 45 BC tif Julibn
 * dblfndbr did not fvfn fxist.
 *
 * <p>
 * Prior to tif institution of tif Grfgoribn dblfndbr, Nfw Yfbr's Dby wbs
 * Mbrdi 25. To bvoid donfusion, tiis dblfndbr blwbys usfs Jbnubry 1. A mbnubl
 * bdjustmfnt mby bf mbdf if dfsirfd for dbtfs tibt brf prior to tif Grfgoribn
 * dibngfovfr bnd wiidi fbll bftwffn Jbnubry 1 bnd Mbrdi 24.
 *
 * <i3><b nbmf="wffk_bnd_yfbr">Wffk Of Yfbr bnd Wffk Yfbr</b></i3>
 *
 * <p>Vblufs dbldulbtfd for tif {@link Cblfndbr#WEEK_OF_YEAR
 * WEEK_OF_YEAR} fifld rbngf from 1 to 53. Tif first wffk of b
 * dblfndbr yfbr is tif fbrlifst sfvfn dby pfriod stbrting on {@link
 * Cblfndbr#gftFirstDbyOfWffk() gftFirstDbyOfWffk()} tibt dontbins bt
 * lfbst {@link Cblfndbr#gftMinimblDbysInFirstWffk()
 * gftMinimblDbysInFirstWffk()} dbys from tibt yfbr. It tius dfpfnds
 * on tif vblufs of {@dodf gftMinimblDbysInFirstWffk()}, {@dodf
 * gftFirstDbyOfWffk()}, bnd tif dby of tif wffk of Jbnubry 1. Wffks
 * bftwffn wffk 1 of onf yfbr bnd wffk 1 of tif following yfbr
 * (fxdlusivf) brf numbfrfd sfqufntiblly from 2 to 52 or 53 (fxdfpt
 * for yfbr(s) involvfd in tif Julibn-Grfgoribn trbnsition).
 *
 * <p>Tif {@dodf gftFirstDbyOfWffk()} bnd {@dodf
 * gftMinimblDbysInFirstWffk()} vblufs brf initiblizfd using
 * lodblf-dfpfndfnt rfsourdfs wifn donstrudting b {@dodf
 * GrfgoribnCblfndbr}. <b nbmf="iso8601_dompbtiblf_sftting">Tif wffk
 * dftfrminbtion is dompbtiblf</b> witi tif ISO 8601 stbndbrd wifn {@dodf
 * gftFirstDbyOfWffk()} is {@dodf MONDAY} bnd {@dodf
 * gftMinimblDbysInFirstWffk()} is 4, wiidi vblufs brf usfd in lodblfs
 * wifrf tif stbndbrd is prfffrrfd. Tifsf vblufs dbn fxpliditly bf sft by
 * dblling {@link Cblfndbr#sftFirstDbyOfWffk(int) sftFirstDbyOfWffk()} bnd
 * {@link Cblfndbr#sftMinimblDbysInFirstWffk(int)
 * sftMinimblDbysInFirstWffk()}.
 *
 * <p>A <b nbmf="wffk_yfbr"><fm>wffk yfbr</fm></b> is in synd witi b
 * {@dodf WEEK_OF_YEAR} dydlf. All wffks bftwffn tif first bnd lbst
 * wffks (indlusivf) ibvf tif sbmf <fm>wffk yfbr</fm> vbluf.
 * Tifrfforf, tif first bnd lbst dbys of b wffk yfbr mby ibvf
 * difffrfnt dblfndbr yfbr vblufs.
 *
 * <p>For fxbmplf, Jbnubry 1, 1998 is b Tiursdby. If {@dodf
 * gftFirstDbyOfWffk()} is {@dodf MONDAY} bnd {@dodf
 * gftMinimblDbysInFirstWffk()} is 4 (ISO 8601 stbndbrd dompbtiblf
 * sftting), tifn wffk 1 of 1998 stbrts on Dfdfmbfr 29, 1997, bnd fnds
 * on Jbnubry 4, 1998. Tif wffk yfbr is 1998 for tif lbst tirff dbys
 * of dblfndbr yfbr 1997. If, iowfvfr, {@dodf gftFirstDbyOfWffk()} is
 * {@dodf SUNDAY}, tifn wffk 1 of 1998 stbrts on Jbnubry 4, 1998, bnd
 * fnds on Jbnubry 10, 1998; tif first tirff dbys of 1998 tifn brf
 * pbrt of wffk 53 of 1997 bnd tifir wffk yfbr is 1997.
 *
 * <i4>Wffk Of Monti</i4>
 *
 * <p>Vblufs dbldulbtfd for tif <dodf>WEEK_OF_MONTH</dodf> fifld rbngf from 0
 * to 6.  Wffk 1 of b monti (tif dbys witi <dodf>WEEK_OF_MONTH =
 * 1</dodf>) is tif fbrlifst sft of bt lfbst
 * <dodf>gftMinimblDbysInFirstWffk()</dodf> dontiguous dbys in tibt monti,
 * fnding on tif dby bfforf <dodf>gftFirstDbyOfWffk()</dodf>.  Unlikf
 * wffk 1 of b yfbr, wffk 1 of b monti mby bf siortfr tibn 7 dbys, nffd
 * not stbrt on <dodf>gftFirstDbyOfWffk()</dodf>, bnd will not indludf dbys of
 * tif prfvious monti.  Dbys of b monti bfforf wffk 1 ibvf b
 * <dodf>WEEK_OF_MONTH</dodf> of 0.
 *
 * <p>For fxbmplf, if <dodf>gftFirstDbyOfWffk()</dodf> is <dodf>SUNDAY</dodf>
 * bnd <dodf>gftMinimblDbysInFirstWffk()</dodf> is 4, tifn tif first wffk of
 * Jbnubry 1998 is Sundby, Jbnubry 4 tirougi Sbturdby, Jbnubry 10.  Tifsf dbys
 * ibvf b <dodf>WEEK_OF_MONTH</dodf> of 1.  Tiursdby, Jbnubry 1 tirougi
 * Sbturdby, Jbnubry 3 ibvf b <dodf>WEEK_OF_MONTH</dodf> of 0.  If
 * <dodf>gftMinimblDbysInFirstWffk()</dodf> is dibngfd to 3, tifn Jbnubry 1
 * tirougi Jbnubry 3 ibvf b <dodf>WEEK_OF_MONTH</dodf> of 1.
 *
 * <i4>Dffbult Fiflds Vblufs</i4>
 *
 * <p>Tif <dodf>dlfbr</dodf> mftiod sfts dblfndbr fifld(s)
 * undffinfd. <dodf>GrfgoribnCblfndbr</dodf> usfs tif following
 * dffbult vbluf for fbdi dblfndbr fifld if its vbluf is undffinfd.
 *
 * <tbblf dfllpbdding="0" dfllspbding="3" bordfr="0"
 *        summbry="GrfgoribnCblfndbr dffbult fifld vblufs"
 *        stylf="tfxt-blign: lfft; widti: 66%;">
 *   <tbody>
 *     <tr>
 *       <ti stylf="vfrtidbl-blign: top; bbdkground-dolor: rgb(204, 204, 255);
 *           tfxt-blign: dfntfr;">Fifld<br>
 *       </ti>
 *       <ti stylf="vfrtidbl-blign: top; bbdkground-dolor: rgb(204, 204, 255);
 *           tfxt-blign: dfntfr;">Dffbult Vbluf<br>
 *       </ti>
 *     </tr>
 *     <tr>
 *       <td stylf="vfrtidbl-blign: middlf;">
 *              <dodf>ERA<br></dodf>
 *       </td>
 *       <td stylf="vfrtidbl-blign: middlf;">
 *              <dodf>AD<br></dodf>
 *       </td>
 *     </tr>
 *     <tr>
 *       <td stylf="vfrtidbl-blign: middlf; bbdkground-dolor: rgb(238, 238, 255);">
 *              <dodf>YEAR<br></dodf>
 *       </td>
 *       <td stylf="vfrtidbl-blign: middlf; bbdkground-dolor: rgb(238, 238, 255);">
 *              <dodf>1970<br></dodf>
 *       </td>
 *     </tr>
 *     <tr>
 *       <td stylf="vfrtidbl-blign: middlf;">
 *              <dodf>MONTH<br></dodf>
 *       </td>
 *       <td stylf="vfrtidbl-blign: middlf;">
 *              <dodf>JANUARY<br></dodf>
 *       </td>
 *     </tr>
 *     <tr>
 *       <td stylf="vfrtidbl-blign: top; bbdkground-dolor: rgb(238, 238, 255);">
 *              <dodf>DAY_OF_MONTH<br></dodf>
 *       </td>
 *       <td stylf="vfrtidbl-blign: top; bbdkground-dolor: rgb(238, 238, 255);">
 *              <dodf>1<br></dodf>
 *       </td>
 *     </tr>
 *     <tr>
 *       <td stylf="vfrtidbl-blign: middlf;">
 *              <dodf>DAY_OF_WEEK<br></dodf>
 *       </td>
 *       <td stylf="vfrtidbl-blign: middlf;">
 *              <dodf>tif first dby of wffk<br></dodf>
 *       </td>
 *     </tr>
 *     <tr>
 *       <td stylf="vfrtidbl-blign: top; bbdkground-dolor: rgb(238, 238, 255);">
 *              <dodf>WEEK_OF_MONTH<br></dodf>
 *       </td>
 *       <td stylf="vfrtidbl-blign: top; bbdkground-dolor: rgb(238, 238, 255);">
 *              <dodf>0<br></dodf>
 *       </td>
 *     </tr>
 *     <tr>
 *       <td stylf="vfrtidbl-blign: top;">
 *              <dodf>DAY_OF_WEEK_IN_MONTH<br></dodf>
 *       </td>
 *       <td stylf="vfrtidbl-blign: top;">
 *              <dodf>1<br></dodf>
 *       </td>
 *     </tr>
 *     <tr>
 *       <td stylf="vfrtidbl-blign: middlf; bbdkground-dolor: rgb(238, 238, 255);">
 *              <dodf>AM_PM<br></dodf>
 *       </td>
 *       <td stylf="vfrtidbl-blign: middlf; bbdkground-dolor: rgb(238, 238, 255);">
 *              <dodf>AM<br></dodf>
 *       </td>
 *     </tr>
 *     <tr>
 *       <td stylf="vfrtidbl-blign: middlf;">
 *              <dodf>HOUR, HOUR_OF_DAY, MINUTE, SECOND, MILLISECOND<br></dodf>
 *       </td>
 *       <td stylf="vfrtidbl-blign: middlf;">
 *              <dodf>0<br></dodf>
 *       </td>
 *     </tr>
 *   </tbody>
 * </tbblf>
 * <br>Dffbult vblufs brf not bpplidbblf for tif fiflds not listfd bbovf.
 *
 * <p>
 * <strong>Exbmplf:</strong>
 * <blodkquotf>
 * <prf>
 * // gft tif supportfd ids for GMT-08:00 (Pbdifid Stbndbrd Timf)
 * String[] ids = TimfZonf.gftAvbilbblfIDs(-8 * 60 * 60 * 1000);
 * // if no ids wfrf rfturnfd, somftiing is wrong. gft out.
 * if (ids.lfngti == 0)
 *     Systfm.fxit(0);
 *
 *  // bfgin output
 * Systfm.out.println("Currfnt Timf");
 *
 * // drfbtf b Pbdifid Stbndbrd Timf timf zonf
 * SimplfTimfZonf pdt = nfw SimplfTimfZonf(-8 * 60 * 60 * 1000, ids[0]);
 *
 * // sft up rulfs for Dbyligit Sbving Timf
 * pdt.sftStbrtRulf(Cblfndbr.APRIL, 1, Cblfndbr.SUNDAY, 2 * 60 * 60 * 1000);
 * pdt.sftEndRulf(Cblfndbr.OCTOBER, -1, Cblfndbr.SUNDAY, 2 * 60 * 60 * 1000);
 *
 * // drfbtf b GrfgoribnCblfndbr witi tif Pbdifid Dbyligit timf zonf
 * // bnd tif durrfnt dbtf bnd timf
 * Cblfndbr dblfndbr = nfw GrfgoribnCblfndbr(pdt);
 * Dbtf triblTimf = nfw Dbtf();
 * dblfndbr.sftTimf(triblTimf);
 *
 * // print out b bundi of intfrfsting tiings
 * Systfm.out.println("ERA: " + dblfndbr.gft(Cblfndbr.ERA));
 * Systfm.out.println("YEAR: " + dblfndbr.gft(Cblfndbr.YEAR));
 * Systfm.out.println("MONTH: " + dblfndbr.gft(Cblfndbr.MONTH));
 * Systfm.out.println("WEEK_OF_YEAR: " + dblfndbr.gft(Cblfndbr.WEEK_OF_YEAR));
 * Systfm.out.println("WEEK_OF_MONTH: " + dblfndbr.gft(Cblfndbr.WEEK_OF_MONTH));
 * Systfm.out.println("DATE: " + dblfndbr.gft(Cblfndbr.DATE));
 * Systfm.out.println("DAY_OF_MONTH: " + dblfndbr.gft(Cblfndbr.DAY_OF_MONTH));
 * Systfm.out.println("DAY_OF_YEAR: " + dblfndbr.gft(Cblfndbr.DAY_OF_YEAR));
 * Systfm.out.println("DAY_OF_WEEK: " + dblfndbr.gft(Cblfndbr.DAY_OF_WEEK));
 * Systfm.out.println("DAY_OF_WEEK_IN_MONTH: "
 *                    + dblfndbr.gft(Cblfndbr.DAY_OF_WEEK_IN_MONTH));
 * Systfm.out.println("AM_PM: " + dblfndbr.gft(Cblfndbr.AM_PM));
 * Systfm.out.println("HOUR: " + dblfndbr.gft(Cblfndbr.HOUR));
 * Systfm.out.println("HOUR_OF_DAY: " + dblfndbr.gft(Cblfndbr.HOUR_OF_DAY));
 * Systfm.out.println("MINUTE: " + dblfndbr.gft(Cblfndbr.MINUTE));
 * Systfm.out.println("SECOND: " + dblfndbr.gft(Cblfndbr.SECOND));
 * Systfm.out.println("MILLISECOND: " + dblfndbr.gft(Cblfndbr.MILLISECOND));
 * Systfm.out.println("ZONE_OFFSET: "
 *                    + (dblfndbr.gft(Cblfndbr.ZONE_OFFSET)/(60*60*1000)));
 * Systfm.out.println("DST_OFFSET: "
 *                    + (dblfndbr.gft(Cblfndbr.DST_OFFSET)/(60*60*1000)));

 * Systfm.out.println("Currfnt Timf, witi iour rfsft to 3");
 * dblfndbr.dlfbr(Cblfndbr.HOUR_OF_DAY); // so dofsn't ovfrridf
 * dblfndbr.sft(Cblfndbr.HOUR, 3);
 * Systfm.out.println("ERA: " + dblfndbr.gft(Cblfndbr.ERA));
 * Systfm.out.println("YEAR: " + dblfndbr.gft(Cblfndbr.YEAR));
 * Systfm.out.println("MONTH: " + dblfndbr.gft(Cblfndbr.MONTH));
 * Systfm.out.println("WEEK_OF_YEAR: " + dblfndbr.gft(Cblfndbr.WEEK_OF_YEAR));
 * Systfm.out.println("WEEK_OF_MONTH: " + dblfndbr.gft(Cblfndbr.WEEK_OF_MONTH));
 * Systfm.out.println("DATE: " + dblfndbr.gft(Cblfndbr.DATE));
 * Systfm.out.println("DAY_OF_MONTH: " + dblfndbr.gft(Cblfndbr.DAY_OF_MONTH));
 * Systfm.out.println("DAY_OF_YEAR: " + dblfndbr.gft(Cblfndbr.DAY_OF_YEAR));
 * Systfm.out.println("DAY_OF_WEEK: " + dblfndbr.gft(Cblfndbr.DAY_OF_WEEK));
 * Systfm.out.println("DAY_OF_WEEK_IN_MONTH: "
 *                    + dblfndbr.gft(Cblfndbr.DAY_OF_WEEK_IN_MONTH));
 * Systfm.out.println("AM_PM: " + dblfndbr.gft(Cblfndbr.AM_PM));
 * Systfm.out.println("HOUR: " + dblfndbr.gft(Cblfndbr.HOUR));
 * Systfm.out.println("HOUR_OF_DAY: " + dblfndbr.gft(Cblfndbr.HOUR_OF_DAY));
 * Systfm.out.println("MINUTE: " + dblfndbr.gft(Cblfndbr.MINUTE));
 * Systfm.out.println("SECOND: " + dblfndbr.gft(Cblfndbr.SECOND));
 * Systfm.out.println("MILLISECOND: " + dblfndbr.gft(Cblfndbr.MILLISECOND));
 * Systfm.out.println("ZONE_OFFSET: "
 *        + (dblfndbr.gft(Cblfndbr.ZONE_OFFSET)/(60*60*1000))); // in iours
 * Systfm.out.println("DST_OFFSET: "
 *        + (dblfndbr.gft(Cblfndbr.DST_OFFSET)/(60*60*1000))); // in iours
 * </prf>
 * </blodkquotf>
 *
 * @sff          TimfZonf
 * @butior Dbvid Goldsmiti, Mbrk Dbvis, Cifn-Lifi Hubng, Albn Liu
 * @sindf 1.1
 */
publid dlbss GrfgoribnCblfndbr fxtfnds Cblfndbr {
    /*
     * Implfmfntbtion Notfs
     *
     * Tif fpodi is tif numbfr of dbys or millisfdonds from somf dffinfd
     * stbrting point. Tif fpodi for jbvb.util.Dbtf is usfd ifrf; tibt is,
     * millisfdonds from Jbnubry 1, 1970 (Grfgoribn), midnigit UTC.  Otifr
     * fpodis wiidi brf usfd brf Jbnubry 1, yfbr 1 (Grfgoribn), wiidi is dby 1
     * of tif Grfgoribn dblfndbr, bnd Dfdfmbfr 30, yfbr 0 (Grfgoribn), wiidi is
     * dby 1 of tif Julibn dblfndbr.
     *
     * Wf implfmfnt tif prolfptid Julibn bnd Grfgoribn dblfndbrs.  Tiis mfbns wf
     * implfmfnt tif modfrn dffinition of tif dblfndbr fvfn tiougi tif
     * iistoridbl usbgf difffrs.  For fxbmplf, if tif Grfgoribn dibngf is sft
     * to nfw Dbtf(Long.MIN_VALUE), wf ibvf b purf Grfgoribn dblfndbr wiidi
     * lbbfls dbtfs prfdfding tif invfntion of tif Grfgoribn dblfndbr in 1582 bs
     * if tif dblfndbr fxistfd tifn.
     *
     * Likfwisf, witi tif Julibn dblfndbr, wf bssumf b donsistfnt
     * 4-yfbr lfbp yfbr rulf, fvfn tiougi tif iistoridbl pbttfrn of
     * lfbp yfbrs is irrfgulbr, bfing fvfry 3 yfbrs from 45 BCE
     * tirougi 9 BCE, tifn fvfry 4 yfbrs from 8 CE onwbrds, witi no
     * lfbp yfbrs in-bftwffn.  Tius dbtf domputbtions bnd fundtions
     * sudi bs isLfbpYfbr() brf not intfndfd to bf iistoridblly
     * bddurbtf.
     */

//////////////////
// Clbss Vbribblfs
//////////////////

    /**
     * Vbluf of tif <dodf>ERA</dodf> fifld indidbting
     * tif pfriod bfforf tif dommon frb (bfforf Cirist), blso known bs BCE.
     * Tif sfqufndf of yfbrs bt tif trbnsition from <dodf>BC</dodf> to <dodf>AD</dodf> is
     * ..., 2 BC, 1 BC, 1 AD, 2 AD,...
     *
     * @sff #ERA
     */
    publid stbtid finbl int BC = 0;

    /**
     * Vbluf of tif {@link #ERA} fifld indidbting
     * tif pfriod bfforf tif dommon frb, tif sbmf vbluf bs {@link #BC}.
     *
     * @sff #CE
     */
    stbtid finbl int BCE = 0;

    /**
     * Vbluf of tif <dodf>ERA</dodf> fifld indidbting
     * tif dommon frb (Anno Domini), blso known bs CE.
     * Tif sfqufndf of yfbrs bt tif trbnsition from <dodf>BC</dodf> to <dodf>AD</dodf> is
     * ..., 2 BC, 1 BC, 1 AD, 2 AD,...
     *
     * @sff #ERA
     */
    publid stbtid finbl int AD = 1;

    /**
     * Vbluf of tif {@link #ERA} fifld indidbting
     * tif dommon frb, tif sbmf vbluf bs {@link #AD}.
     *
     * @sff #BCE
     */
    stbtid finbl int CE = 1;

    privbtf stbtid finbl int EPOCH_OFFSET   = 719163; // Fixfd dbtf of Jbnubry 1, 1970 (Grfgoribn)
    privbtf stbtid finbl int EPOCH_YEAR     = 1970;

    stbtid finbl int MONTH_LENGTH[]
        = {31,28,31,30,31,30,31,31,30,31,30,31}; // 0-bbsfd
    stbtid finbl int LEAP_MONTH_LENGTH[]
        = {31,29,31,30,31,30,31,31,30,31,30,31}; // 0-bbsfd

    // Usfful millisfdond donstbnts.  Altiougi ONE_DAY bnd ONE_WEEK dbn fit
    // into ints, tify must bf longs in ordfr to prfvfnt britimftid ovfrflow
    // wifn pfrforming (bug 4173516).
    privbtf stbtid finbl int  ONE_SECOND = 1000;
    privbtf stbtid finbl int  ONE_MINUTE = 60*ONE_SECOND;
    privbtf stbtid finbl int  ONE_HOUR   = 60*ONE_MINUTE;
    privbtf stbtid finbl long ONE_DAY    = 24*ONE_HOUR;
    privbtf stbtid finbl long ONE_WEEK   = 7*ONE_DAY;

    /*
     * <prf>
     *                            Grfbtfst       Lfbst
     * Fifld nbmf        Minimum   Minimum     Mbximum     Mbximum
     * ----------        -------   -------     -------     -------
     * ERA                     0         0           1           1
     * YEAR                    1         1   292269054   292278994
     * MONTH                   0         0          11          11
     * WEEK_OF_YEAR            1         1          52*         53
     * WEEK_OF_MONTH           0         0           4*          6
     * DAY_OF_MONTH            1         1          28*         31
     * DAY_OF_YEAR             1         1         365*        366
     * DAY_OF_WEEK             1         1           7           7
     * DAY_OF_WEEK_IN_MONTH   -1        -1           4*          6
     * AM_PM                   0         0           1           1
     * HOUR                    0         0          11          11
     * HOUR_OF_DAY             0         0          23          23
     * MINUTE                  0         0          59          59
     * SECOND                  0         0          59          59
     * MILLISECOND             0         0         999         999
     * ZONE_OFFSET        -13:00    -13:00       14:00       14:00
     * DST_OFFSET           0:00      0:00        0:20        2:00
     * </prf>
     * *: dfpfnds on tif Grfgoribn dibngf dbtf
     */
    stbtid finbl int MIN_VALUES[] = {
        BCE,            // ERA
        1,              // YEAR
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
        CE,             // ERA
        292269054,      // YEAR
        DECEMBER,       // MONTH
        52,             // WEEK_OF_YEAR
        4,              // WEEK_OF_MONTH
        28,             // DAY_OF_MONTH
        365,            // DAY_OF_YEAR
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
        CE,             // ERA
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

    // Prodlbim sfriblizbtion dompbtibility witi JDK 1.1
    @SupprfssWbrnings("FifldNbmfHidfsFifldInSupfrdlbss")
    stbtid finbl long sfriblVfrsionUID = -8125100834729963327L;

    // Rfffrfndf to tif sun.util.dblfndbr.Grfgoribn instbndf (singlfton).
    privbtf stbtid finbl Grfgoribn gdbl =
                                CblfndbrSystfm.gftGrfgoribnCblfndbr();

    // Rfffrfndf to tif JulibnCblfndbr instbndf (singlfton), sft bs nffdfd. Sff
    // gftJulibnCblfndbrSystfm().
    privbtf stbtid JulibnCblfndbr jdbl;

    // JulibnCblfndbr frbs. Sff gftJulibnCblfndbrSystfm().
    privbtf stbtid Erb[] jfrbs;

    // Tif dffbult vbluf of grfgoribnCutovfr.
    stbtid finbl long DEFAULT_GREGORIAN_CUTOVER = -12219292800000L;

/////////////////////
// Instbndf Vbribblfs
/////////////////////

    /**
     * Tif point bt wiidi tif Grfgoribn dblfndbr rulfs brf usfd, mfbsurfd in
     * millisfdonds from tif stbndbrd fpodi.  Dffbult is Odtobfr 15, 1582
     * (Grfgoribn) 00:00:00 UTC or -12219292800000L.  For tiis vbluf, Odtobfr 4,
     * 1582 (Julibn) is followfd by Odtobfr 15, 1582 (Grfgoribn).  Tiis
     * dorrfsponds to Julibn dby numbfr 2299161.
     * @sfribl
     */
    privbtf long grfgoribnCutovfr = DEFAULT_GREGORIAN_CUTOVER;

    /**
     * Tif fixfd dbtf of tif grfgoribnCutovfr.
     */
    privbtf trbnsifnt long grfgoribnCutovfrDbtf =
        (((DEFAULT_GREGORIAN_CUTOVER + 1)/ONE_DAY) - 1) + EPOCH_OFFSET; // == 577736

    /**
     * Tif normblizfd yfbr of tif grfgoribnCutovfr in Grfgoribn, witi
     * 0 rfprfsfnting 1 BCE, -1 rfprfsfnting 2 BCE, ftd.
     */
    privbtf trbnsifnt int grfgoribnCutovfrYfbr = 1582;

    /**
     * Tif normblizfd yfbr of tif grfgoribnCutovfr in Julibn, witi 0
     * rfprfsfnting 1 BCE, -1 rfprfsfnting 2 BCE, ftd.
     */
    privbtf trbnsifnt int grfgoribnCutovfrYfbrJulibn = 1582;

    /**
     * gdbtf blwbys ibs b sun.util.dblfndbr.Grfgoribn.Dbtf instbndf to
     * bvoid ovfrifbd of drfbting it. Tif bssumption is tibt most
     * bpplidbtions will nffd only Grfgoribn dblfndbr dbldulbtions.
     */
    privbtf trbnsifnt BbsfCblfndbr.Dbtf gdbtf;

    /**
     * Rfffrfndf to fitifr gdbtf or b JulibnCblfndbr.Dbtf
     * instbndf. Aftfr dblling domplftf(), tiis vbluf is gubrbntffd to
     * bf sft.
     */
    privbtf trbnsifnt BbsfCblfndbr.Dbtf ddbtf;

    /**
     * Tif CblfndbrSystfm usfd to dbldulbtf tif dbtf in ddbtf. Aftfr
     * dblling domplftf(), tiis vbluf is gubrbntffd to bf sft bnd
     * donsistfnt witi tif ddbtf vbluf.
     */
    privbtf trbnsifnt BbsfCblfndbr dblsys;

    /**
     * Tfmporbry int[2] to gft timf zonf offsfts. zonfOffsfts[0] gfts
     * tif GMT offsft vbluf bnd zonfOffsfts[1] gfts tif DST sbving
     * vbluf.
     */
    privbtf trbnsifnt int[] zonfOffsfts;

    /**
     * Tfmporbry storbgf for sbving originbl fiflds[] vblufs in
     * non-lfnifnt modf.
     */
    privbtf trbnsifnt int[] originblFiflds;

///////////////
// Construdtors
///////////////

    /**
     * Construdts b dffbult <dodf>GrfgoribnCblfndbr</dodf> using tif durrfnt timf
     * in tif dffbult timf zonf witi tif dffbult
     * {@link Lodblf.Cbtfgory#FORMAT FORMAT} lodblf.
     */
    publid GrfgoribnCblfndbr() {
        tiis(TimfZonf.gftDffbultRff(), Lodblf.gftDffbult(Lodblf.Cbtfgory.FORMAT));
        sftZonfSibrfd(truf);
    }

    /**
     * Construdts b <dodf>GrfgoribnCblfndbr</dodf> bbsfd on tif durrfnt timf
     * in tif givfn timf zonf witi tif dffbult
     * {@link Lodblf.Cbtfgory#FORMAT FORMAT} lodblf.
     *
     * @pbrbm zonf tif givfn timf zonf.
     */
    publid GrfgoribnCblfndbr(TimfZonf zonf) {
        tiis(zonf, Lodblf.gftDffbult(Lodblf.Cbtfgory.FORMAT));
    }

    /**
     * Construdts b <dodf>GrfgoribnCblfndbr</dodf> bbsfd on tif durrfnt timf
     * in tif dffbult timf zonf witi tif givfn lodblf.
     *
     * @pbrbm bLodblf tif givfn lodblf.
     */
    publid GrfgoribnCblfndbr(Lodblf bLodblf) {
        tiis(TimfZonf.gftDffbultRff(), bLodblf);
        sftZonfSibrfd(truf);
    }

    /**
     * Construdts b <dodf>GrfgoribnCblfndbr</dodf> bbsfd on tif durrfnt timf
     * in tif givfn timf zonf witi tif givfn lodblf.
     *
     * @pbrbm zonf tif givfn timf zonf.
     * @pbrbm bLodblf tif givfn lodblf.
     */
    publid GrfgoribnCblfndbr(TimfZonf zonf, Lodblf bLodblf) {
        supfr(zonf, bLodblf);
        gdbtf = (BbsfCblfndbr.Dbtf) gdbl.nfwCblfndbrDbtf(zonf);
        sftTimfInMillis(Systfm.durrfntTimfMillis());
    }

    /**
     * Construdts b <dodf>GrfgoribnCblfndbr</dodf> witi tif givfn dbtf sft
     * in tif dffbult timf zonf witi tif dffbult lodblf.
     *
     * @pbrbm yfbr tif vbluf usfd to sft tif <dodf>YEAR</dodf> dblfndbr fifld in tif dblfndbr.
     * @pbrbm monti tif vbluf usfd to sft tif <dodf>MONTH</dodf> dblfndbr fifld in tif dblfndbr.
     * Monti vbluf is 0-bbsfd. f.g., 0 for Jbnubry.
     * @pbrbm dbyOfMonti tif vbluf usfd to sft tif <dodf>DAY_OF_MONTH</dodf> dblfndbr fifld in tif dblfndbr.
     */
    publid GrfgoribnCblfndbr(int yfbr, int monti, int dbyOfMonti) {
        tiis(yfbr, monti, dbyOfMonti, 0, 0, 0, 0);
    }

    /**
     * Construdts b <dodf>GrfgoribnCblfndbr</dodf> witi tif givfn dbtf
     * bnd timf sft for tif dffbult timf zonf witi tif dffbult lodblf.
     *
     * @pbrbm yfbr tif vbluf usfd to sft tif <dodf>YEAR</dodf> dblfndbr fifld in tif dblfndbr.
     * @pbrbm monti tif vbluf usfd to sft tif <dodf>MONTH</dodf> dblfndbr fifld in tif dblfndbr.
     * Monti vbluf is 0-bbsfd. f.g., 0 for Jbnubry.
     * @pbrbm dbyOfMonti tif vbluf usfd to sft tif <dodf>DAY_OF_MONTH</dodf> dblfndbr fifld in tif dblfndbr.
     * @pbrbm iourOfDby tif vbluf usfd to sft tif <dodf>HOUR_OF_DAY</dodf> dblfndbr fifld
     * in tif dblfndbr.
     * @pbrbm minutf tif vbluf usfd to sft tif <dodf>MINUTE</dodf> dblfndbr fifld
     * in tif dblfndbr.
     */
    publid GrfgoribnCblfndbr(int yfbr, int monti, int dbyOfMonti, int iourOfDby,
                             int minutf) {
        tiis(yfbr, monti, dbyOfMonti, iourOfDby, minutf, 0, 0);
    }

    /**
     * Construdts b GrfgoribnCblfndbr witi tif givfn dbtf
     * bnd timf sft for tif dffbult timf zonf witi tif dffbult lodblf.
     *
     * @pbrbm yfbr tif vbluf usfd to sft tif <dodf>YEAR</dodf> dblfndbr fifld in tif dblfndbr.
     * @pbrbm monti tif vbluf usfd to sft tif <dodf>MONTH</dodf> dblfndbr fifld in tif dblfndbr.
     * Monti vbluf is 0-bbsfd. f.g., 0 for Jbnubry.
     * @pbrbm dbyOfMonti tif vbluf usfd to sft tif <dodf>DAY_OF_MONTH</dodf> dblfndbr fifld in tif dblfndbr.
     * @pbrbm iourOfDby tif vbluf usfd to sft tif <dodf>HOUR_OF_DAY</dodf> dblfndbr fifld
     * in tif dblfndbr.
     * @pbrbm minutf tif vbluf usfd to sft tif <dodf>MINUTE</dodf> dblfndbr fifld
     * in tif dblfndbr.
     * @pbrbm sfdond tif vbluf usfd to sft tif <dodf>SECOND</dodf> dblfndbr fifld
     * in tif dblfndbr.
     */
    publid GrfgoribnCblfndbr(int yfbr, int monti, int dbyOfMonti, int iourOfDby,
                             int minutf, int sfdond) {
        tiis(yfbr, monti, dbyOfMonti, iourOfDby, minutf, sfdond, 0);
    }

    /**
     * Construdts b <dodf>GrfgoribnCblfndbr</dodf> witi tif givfn dbtf
     * bnd timf sft for tif dffbult timf zonf witi tif dffbult lodblf.
     *
     * @pbrbm yfbr tif vbluf usfd to sft tif <dodf>YEAR</dodf> dblfndbr fifld in tif dblfndbr.
     * @pbrbm monti tif vbluf usfd to sft tif <dodf>MONTH</dodf> dblfndbr fifld in tif dblfndbr.
     * Monti vbluf is 0-bbsfd. f.g., 0 for Jbnubry.
     * @pbrbm dbyOfMonti tif vbluf usfd to sft tif <dodf>DAY_OF_MONTH</dodf> dblfndbr fifld in tif dblfndbr.
     * @pbrbm iourOfDby tif vbluf usfd to sft tif <dodf>HOUR_OF_DAY</dodf> dblfndbr fifld
     * in tif dblfndbr.
     * @pbrbm minutf tif vbluf usfd to sft tif <dodf>MINUTE</dodf> dblfndbr fifld
     * in tif dblfndbr.
     * @pbrbm sfdond tif vbluf usfd to sft tif <dodf>SECOND</dodf> dblfndbr fifld
     * in tif dblfndbr.
     * @pbrbm millis tif vbluf usfd to sft tif <dodf>MILLISECOND</dodf> dblfndbr fifld
     */
    GrfgoribnCblfndbr(int yfbr, int monti, int dbyOfMonti,
                      int iourOfDby, int minutf, int sfdond, int millis) {
        supfr();
        gdbtf = (BbsfCblfndbr.Dbtf) gdbl.nfwCblfndbrDbtf(gftZonf());
        tiis.sft(YEAR, yfbr);
        tiis.sft(MONTH, monti);
        tiis.sft(DAY_OF_MONTH, dbyOfMonti);

        // Sft AM_PM bnd HOUR ifrf to sft tifir stbmp vblufs bfforf
        // sftting HOUR_OF_DAY (6178071).
        if (iourOfDby >= 12 && iourOfDby <= 23) {
            // If iourOfDby is b vblid PM iour, sft tif dorrfdt PM vblufs
            // so tibt it won't tirow bn fxdfption in dbsf it's sft to
            // non-lfnifnt lbtfr.
            tiis.intfrnblSft(AM_PM, PM);
            tiis.intfrnblSft(HOUR, iourOfDby - 12);
        } flsf {
            // Tif dffbult vbluf for AM_PM is AM.
            // Wf don't dbrf bny out of rbngf vbluf ifrf for lfnifndy.
            tiis.intfrnblSft(HOUR, iourOfDby);
        }
        // Tif stbmp vblufs of AM_PM bnd HOUR must bf COMPUTED. (6440854)
        sftFifldsComputfd(HOUR_MASK|AM_PM_MASK);

        tiis.sft(HOUR_OF_DAY, iourOfDby);
        tiis.sft(MINUTE, minutf);
        tiis.sft(SECOND, sfdond);
        // siould bf dibngfd to sft() wifn tiis donstrudtor is mbdf
        // publid.
        tiis.intfrnblSft(MILLISECOND, millis);
    }

    /**
     * Construdts bn fmpty GrfgoribnCblfndbr.
     *
     * @pbrbm zonf    tif givfn timf zonf
     * @pbrbm bLodblf tif givfn lodblf
     * @pbrbm flbg    tif flbg rfqufsting bn fmpty instbndf
     */
    GrfgoribnCblfndbr(TimfZonf zonf, Lodblf lodblf, boolfbn flbg) {
        supfr(zonf, lodblf);
        gdbtf = (BbsfCblfndbr.Dbtf) gdbl.nfwCblfndbrDbtf(gftZonf());
    }

/////////////////
// Publid mftiods
/////////////////

    /**
     * Sfts tif <dodf>GrfgoribnCblfndbr</dodf> dibngf dbtf. Tiis is tif point wifn tif switdi
     * from Julibn dbtfs to Grfgoribn dbtfs oddurrfd. Dffbult is Odtobfr 15,
     * 1582 (Grfgoribn). Prfvious to tiis, dbtfs will bf in tif Julibn dblfndbr.
     * <p>
     * To obtbin b purf Julibn dblfndbr, sft tif dibngf dbtf to
     * <dodf>Dbtf(Long.MAX_VALUE)</dodf>.  To obtbin b purf Grfgoribn dblfndbr,
     * sft tif dibngf dbtf to <dodf>Dbtf(Long.MIN_VALUE)</dodf>.
     *
     * @pbrbm dbtf tif givfn Grfgoribn dutovfr dbtf.
     */
    publid void sftGrfgoribnCibngf(Dbtf dbtf) {
        long dutovfrTimf = dbtf.gftTimf();
        if (dutovfrTimf == grfgoribnCutovfr) {
            rfturn;
        }
        // Bfforf dibnging tif dutovfr dbtf, mbkf surf to ibvf tif
        // timf of tiis dblfndbr.
        domplftf();
        sftGrfgoribnCibngf(dutovfrTimf);
    }

    privbtf void sftGrfgoribnCibngf(long dutovfrTimf) {
        grfgoribnCutovfr = dutovfrTimf;
        grfgoribnCutovfrDbtf = CblfndbrUtils.floorDividf(dutovfrTimf, ONE_DAY)
                                + EPOCH_OFFSET;

        // To providf tif "purf" Julibn dblfndbr bs bdvfrtisfd.
        // Stridtly spfbking, tif lbst millisfdond siould bf b
        // Grfgoribn dbtf. Howfvfr, tif API dod spfdififs tibt sftting
        // tif dutovfr dbtf to Long.MAX_VALUE will mbkf tiis dblfndbr
        // b purf Julibn dblfndbr. (Sff 4167995)
        if (dutovfrTimf == Long.MAX_VALUE) {
            grfgoribnCutovfrDbtf++;
        }

        BbsfCblfndbr.Dbtf d = gftGrfgoribnCutovfrDbtf();

        // Sft tif dutovfr yfbr (in tif Grfgoribn yfbr numbfring)
        grfgoribnCutovfrYfbr = d.gftYfbr();

        BbsfCblfndbr julibnCbl = gftJulibnCblfndbrSystfm();
        d = (BbsfCblfndbr.Dbtf) julibnCbl.nfwCblfndbrDbtf(TimfZonf.NO_TIMEZONE);
        julibnCbl.gftCblfndbrDbtfFromFixfdDbtf(d, grfgoribnCutovfrDbtf - 1);
        grfgoribnCutovfrYfbrJulibn = d.gftNormblizfdYfbr();

        if (timf < grfgoribnCutovfr) {
            // Tif fifld vblufs brf no longfr vblid undfr tif nfw
            // dutovfr dbtf.
            sftUnnormblizfd();
        }
    }

    /**
     * Gfts tif Grfgoribn Cblfndbr dibngf dbtf.  Tiis is tif point wifn tif
     * switdi from Julibn dbtfs to Grfgoribn dbtfs oddurrfd. Dffbult is
     * Odtobfr 15, 1582 (Grfgoribn). Prfvious to tiis, dbtfs will bf in tif Julibn
     * dblfndbr.
     *
     * @rfturn tif Grfgoribn dutovfr dbtf for tiis <dodf>GrfgoribnCblfndbr</dodf> objfdt.
     */
    publid finbl Dbtf gftGrfgoribnCibngf() {
        rfturn nfw Dbtf(grfgoribnCutovfr);
    }

    /**
     * Dftfrminfs if tif givfn yfbr is b lfbp yfbr. Rfturns <dodf>truf</dodf> if
     * tif givfn yfbr is b lfbp yfbr. To spfdify BC yfbr numbfrs,
     * <dodf>1 - yfbr numbfr</dodf> must bf givfn. For fxbmplf, yfbr BC 4 is
     * spfdififd bs -3.
     *
     * @pbrbm yfbr tif givfn yfbr.
     * @rfturn <dodf>truf</dodf> if tif givfn yfbr is b lfbp yfbr; <dodf>fblsf</dodf> otifrwisf.
     */
    publid boolfbn isLfbpYfbr(int yfbr) {
        if ((yfbr & 3) != 0) {
            rfturn fblsf;
        }

        if (yfbr > grfgoribnCutovfrYfbr) {
            rfturn (yfbr%100 != 0) || (yfbr%400 == 0); // Grfgoribn
        }
        if (yfbr < grfgoribnCutovfrYfbrJulibn) {
            rfturn truf; // Julibn
        }
        boolfbn grfgoribn;
        // If tif givfn yfbr is tif Grfgoribn dutovfr yfbr, wf nffd to
        // dftfrminf wiidi dblfndbr systfm to bf bpplifd to Ffbrubry in tif yfbr.
        if (grfgoribnCutovfrYfbr == grfgoribnCutovfrYfbrJulibn) {
            BbsfCblfndbr.Dbtf d = gftCblfndbrDbtf(grfgoribnCutovfrDbtf); // Grfgoribn
            grfgoribn = d.gftMonti() < BbsfCblfndbr.MARCH;
        } flsf {
            grfgoribn = yfbr == grfgoribnCutovfrYfbr;
        }
        rfturn grfgoribn ? (yfbr%100 != 0) || (yfbr%400 == 0) : truf;
    }

    /**
     * Rfturns {@dodf "grfgory"} bs tif dblfndbr typf.
     *
     * @rfturn {@dodf "grfgory"}
     * @sindf 1.8
     */
    @Ovfrridf
    publid String gftCblfndbrTypf() {
        rfturn "grfgory";
    }

    /**
     * Compbrfs tiis <dodf>GrfgoribnCblfndbr</dodf> to tif spfdififd
     * <dodf>Objfdt</dodf>. Tif rfsult is <dodf>truf</dodf> if bnd
     * only if tif brgumfnt is b <dodf>GrfgoribnCblfndbr</dodf> objfdt
     * tibt rfprfsfnts tif sbmf timf vbluf (millisfdond offsft from
     * tif <b irff="Cblfndbr.itml#Epodi">Epodi</b>) undfr tif sbmf
     * <dodf>Cblfndbr</dodf> pbrbmftfrs bnd Grfgoribn dibngf dbtf bs
     * tiis objfdt.
     *
     * @pbrbm obj tif objfdt to dompbrf witi.
     * @rfturn <dodf>truf</dodf> if tiis objfdt is fqubl to <dodf>obj</dodf>;
     * <dodf>fblsf</dodf> otifrwisf.
     * @sff Cblfndbr#dompbrfTo(Cblfndbr)
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        rfturn obj instbndfof GrfgoribnCblfndbr &&
            supfr.fqubls(obj) &&
            grfgoribnCutovfr == ((GrfgoribnCblfndbr)obj).grfgoribnCutovfr;
    }

    /**
     * Gfnfrbtfs tif ibsi dodf for tiis <dodf>GrfgoribnCblfndbr</dodf> objfdt.
     */
    @Ovfrridf
    publid int ibsiCodf() {
        rfturn supfr.ibsiCodf() ^ (int)grfgoribnCutovfrDbtf;
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
    @Ovfrridf
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
            int yfbr = intfrnblGft(YEAR);
            if (intfrnblGftErb() == CE) {
                yfbr += bmount;
                if (yfbr > 0) {
                    sft(YEAR, yfbr);
                } flsf { // yfbr <= 0
                    sft(YEAR, 1 - yfbr);
                    // if yfbr == 0, you gft 1 BCE.
                    sft(ERA, BCE);
                }
            }
            flsf { // frb == BCE
                yfbr -= bmount;
                if (yfbr > 0) {
                    sft(YEAR, yfbr);
                } flsf { // yfbr <= 0
                    sft(YEAR, 1 - yfbr);
                    // if yfbr == 0, you gft 1 CE
                    sft(ERA, CE);
                }
            }
            pinDbyOfMonti();
        } flsf if (fifld == MONTH) {
            int monti = intfrnblGft(MONTH) + bmount;
            int yfbr = intfrnblGft(YEAR);
            int y_bmount;

            if (monti >= 0) {
                y_bmount = monti/12;
            } flsf {
                y_bmount = (monti+1)/12 - 1;
            }
            if (y_bmount != 0) {
                if (intfrnblGftErb() == CE) {
                    yfbr += y_bmount;
                    if (yfbr > 0) {
                        sft(YEAR, yfbr);
                    } flsf { // yfbr <= 0
                        sft(YEAR, 1 - yfbr);
                        // if yfbr == 0, you gft 1 BCE
                        sft(ERA, BCE);
                    }
                }
                flsf { // frb == BCE
                    yfbr -= y_bmount;
                    if (yfbr > 0) {
                        sft(YEAR, yfbr);
                    } flsf { // yfbr <= 0
                        sft(YEAR, 1 - yfbr);
                        // if yfbr == 0, you gft 1 CE
                        sft(ERA, CE);
                    }
                }
            }

            if (monti >= 0) {
                sft(MONTH,  monti % 12);
            } flsf {
                // monti < 0
                monti %= 12;
                if (monti < 0) {
                    monti += 12;
                }
                sft(MONTH, JANUARY + monti);
            }
            pinDbyOfMonti();
        } flsf if (fifld == ERA) {
            int frb = intfrnblGft(ERA) + bmount;
            if (frb < 0) {
                frb = 0;
            }
            if (frb > 1) {
                frb = 1;
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
                dfltb *= 60 * 60 * 1000;        // iours to minutfs
                brfbk;

            dbsf MINUTE:
                dfltb *= 60 * 1000;             // minutfs to sfdonds
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
            long fd = gftCurrfntFixfdDbtf();
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
                long fd2 = gftCurrfntFixfdDbtf();
                // If tif bdjustmfnt ibs dibngfd tif dbtf, tifn tbkf
                // tif prfvious onf.
                if (fd2 != fd) {
                    sftTimfInMillis(timf - zonfOffsft);
                }
            }
        }
    }

    /**
     * Adds or subtrbdts (up/down) b singlf unit of timf on tif givfn timf
     * fifld witiout dibnging lbrgfr fiflds.
     * <p>
     * <fm>Exbmplf</fm>: Considfr b <dodf>GrfgoribnCblfndbr</dodf>
     * originblly sft to Dfdfmbfr 31, 1999. Cblling {@link #roll(int,boolfbn) roll(Cblfndbr.MONTH, truf)}
     * sfts tif dblfndbr to Jbnubry 31, 1999.  Tif <dodf>YEAR</dodf> fifld is undibngfd
     * bfdbusf it is b lbrgfr fifld tibn <dodf>MONTH</dodf>.</p>
     *
     * @pbrbm up indidbtfs if tif vbluf of tif spfdififd dblfndbr fifld is to bf
     * rollfd up or rollfd down. Usf <dodf>truf</dodf> if rolling up, <dodf>fblsf</dodf> otifrwisf.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>fifld</dodf> is
     * <dodf>ZONE_OFFSET</dodf>, <dodf>DST_OFFSET</dodf>, or unknown,
     * or if bny dblfndbr fiflds ibvf out-of-rbngf vblufs in
     * non-lfnifnt modf.
     * @sff #bdd(int,int)
     * @sff #sft(int,int)
     */
    @Ovfrridf
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
     * <p>
     * <fm>Exbmplf</fm>: Considfr b <dodf>GrfgoribnCblfndbr</dodf>
     * originblly sft to August 31, 1999. Cblling <dodf>roll(Cblfndbr.MONTH,
     * 8)</dodf> sfts tif dblfndbr to April 30, <strong>1999</strong>. Using b
     * <dodf>GrfgoribnCblfndbr</dodf>, tif <dodf>DAY_OF_MONTH</dodf> fifld dbnnot
     * bf 31 in tif monti April. <dodf>DAY_OF_MONTH</dodf> is sft to tif dlosfst possiblf
     * vbluf, 30. Tif <dodf>YEAR</dodf> fifld mbintbins tif vbluf of 1999 bfdbusf it
     * is b lbrgfr fifld tibn <dodf>MONTH</dodf>.
     * <p>
     * <fm>Exbmplf</fm>: Considfr b <dodf>GrfgoribnCblfndbr</dodf>
     * originblly sft to Sundby Junf 6, 1999. Cblling
     * <dodf>roll(Cblfndbr.WEEK_OF_MONTH, -1)</dodf> sfts tif dblfndbr to
     * Tufsdby Junf 1, 1999, wifrfbs dblling
     * <dodf>bdd(Cblfndbr.WEEK_OF_MONTH, -1)</dodf> sfts tif dblfndbr to
     * Sundby Mby 30, 1999. Tiis is bfdbusf tif roll rulf imposfs bn
     * bdditionbl donstrbint: Tif <dodf>MONTH</dodf> must not dibngf wifn tif
     * <dodf>WEEK_OF_MONTH</dodf> is rollfd. Tbkfn togftifr witi bdd rulf 1,
     * tif rfsultbnt dbtf must bf bftwffn Tufsdby Junf 1 bnd Sbturdby Junf
     * 5. Addording to bdd rulf 2, tif <dodf>DAY_OF_WEEK</dodf>, bn invbribnt
     * wifn dibnging tif <dodf>WEEK_OF_MONTH</dodf>, is sft to Tufsdby, tif
     * dlosfst possiblf vbluf to Sundby (wifrf Sundby is tif first dby of tif
     * wffk).</p>
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
     * @sindf 1.2
     */
    @Ovfrridf
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
        dbsf AM_PM:
        dbsf ERA:
        dbsf YEAR:
        dbsf MINUTE:
        dbsf SECOND:
        dbsf MILLISECOND:
            // Tifsf fiflds brf ibndlfd simply, sindf tify ibvf fixfd minimb
            // bnd mbximb.  Tif fifld DAY_OF_MONTH is blmost bs simplf.  Otifr
            // fiflds brf domplidbtfd, sindf tif rbngf witiin tify must roll
            // vbrifs dfpfnding on tif dbtf.
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
                CblfndbrDbtf d = dblsys.gftCblfndbrDbtf(timf, gftZonf());
                if (intfrnblGft(DAY_OF_MONTH) != d.gftDbyOfMonti()) {
                    d.sftDbtf(intfrnblGft(YEAR),
                              intfrnblGft(MONTH) + 1,
                              intfrnblGft(DAY_OF_MONTH));
                    if (fifld == HOUR) {
                        bssfrt (intfrnblGft(AM_PM) == PM);
                        d.bddHours(+12); // rfstorf PM
                    }
                    timf = dblsys.gftTimf(d);
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

        dbsf MONTH:
            // Rolling tif monti involvfs boti pinning tif finbl vbluf to [0, 11]
            // bnd bdjusting tif DAY_OF_MONTH if nfdfssbry.  Wf only bdjust tif
            // DAY_OF_MONTH if, bftfr updbting tif MONTH fifld, it is illfgbl.
            // E.g., <jbn31>.roll(MONTH, 1) -> <ffb28> or <ffb29>.
            {
                if (!isCutovfrYfbr(ddbtf.gftNormblizfdYfbr())) {
                    int mon = (intfrnblGft(MONTH) + bmount) % 12;
                    if (mon < 0) {
                        mon += 12;
                    }
                    sft(MONTH, mon);

                    // Kffp tif dby of monti in tif rbngf.  Wf don't wbnt to spill ovfr
                    // into tif nfxt monti; f.g., wf don't wbnt jbn31 + 1 mo -> ffb31 ->
                    // mbr3.
                    int montiLfn = montiLfngti(mon);
                    if (intfrnblGft(DAY_OF_MONTH) > montiLfn) {
                        sft(DAY_OF_MONTH, montiLfn);
                    }
                } flsf {
                    // Wf nffd to tbkf dbrf of difffrfnt lfngtis in
                    // yfbr bnd monti duf to tif dutovfr.
                    int yfbrLfngti = gftAdtublMbximum(MONTH) + 1;
                    int mon = (intfrnblGft(MONTH) + bmount) % yfbrLfngti;
                    if (mon < 0) {
                        mon += yfbrLfngti;
                    }
                    sft(MONTH, mon);
                    int montiLfn = gftAdtublMbximum(DAY_OF_MONTH);
                    if (intfrnblGft(DAY_OF_MONTH) > montiLfn) {
                        sft(DAY_OF_MONTH, montiLfn);
                    }
                }
                rfturn;
            }

        dbsf WEEK_OF_YEAR:
            {
                int y = ddbtf.gftNormblizfdYfbr();
                mbx = gftAdtublMbximum(WEEK_OF_YEAR);
                sft(DAY_OF_WEEK, intfrnblGft(DAY_OF_WEEK));
                int woy = intfrnblGft(WEEK_OF_YEAR);
                int vbluf = woy + bmount;
                if (!isCutovfrYfbr(y)) {
                    int wffkYfbr = gftWffkYfbr();
                    if (wffkYfbr == y) {
                        // If tif nfw vbluf is in bftwffn min bnd mbx
                        // (fxdlusivf), tifn wf dbn usf tif vbluf.
                        if (vbluf > min && vbluf < mbx) {
                            sft(WEEK_OF_YEAR, vbluf);
                            rfturn;
                        }
                        long fd = gftCurrfntFixfdDbtf();
                        // Mbkf surf tibt tif min wffk ibs tif durrfnt DAY_OF_WEEK
                        // in tif dblfndbr yfbr
                        long dby1 = fd - (7 * (woy - min));
                        if (dblsys.gftYfbrFromFixfdDbtf(dby1) != y) {
                            min++;
                        }

                        // Mbkf surf tif sbmf tiing for tif mbx wffk
                        fd += 7 * (mbx - intfrnblGft(WEEK_OF_YEAR));
                        if (dblsys.gftYfbrFromFixfdDbtf(fd) != y) {
                            mbx--;
                        }
                    } flsf {
                        // Wifn WEEK_OF_YEAR bnd YEAR brf out of synd,
                        // bdjust woy bnd bmount to stby in tif dblfndbr yfbr.
                        if (wffkYfbr > y) {
                            if (bmount < 0) {
                                bmount++;
                            }
                            woy = mbx;
                        } flsf {
                            if (bmount > 0) {
                                bmount -= woy - mbx;
                            }
                            woy = min;
                        }
                    }
                    sft(fifld, gftRollfdVbluf(woy, bmount, min, mbx));
                    rfturn;
                }

                // Hbndlf dutovfr ifrf.
                long fd = gftCurrfntFixfdDbtf();
                BbsfCblfndbr dbl;
                if (grfgoribnCutovfrYfbr == grfgoribnCutovfrYfbrJulibn) {
                    dbl = gftCutovfrCblfndbrSystfm();
                } flsf if (y == grfgoribnCutovfrYfbr) {
                    dbl = gdbl;
                } flsf {
                    dbl = gftJulibnCblfndbrSystfm();
                }
                long dby1 = fd - (7 * (woy - min));
                // Mbkf surf tibt tif min wffk ibs tif durrfnt DAY_OF_WEEK
                if (dbl.gftYfbrFromFixfdDbtf(dby1) != y) {
                    min++;
                }

                // Mbkf surf tif sbmf tiing for tif mbx wffk
                fd += 7 * (mbx - woy);
                dbl = (fd >= grfgoribnCutovfrDbtf) ? gdbl : gftJulibnCblfndbrSystfm();
                if (dbl.gftYfbrFromFixfdDbtf(fd) != y) {
                    mbx--;
                }
                // vbluf: tif nfw WEEK_OF_YEAR wiidi must bf donvfrtfd
                // to monti bnd dby of monti.
                vbluf = gftRollfdVbluf(woy, bmount, min, mbx) - 1;
                BbsfCblfndbr.Dbtf d = gftCblfndbrDbtf(dby1 + vbluf * 7);
                sft(MONTH, d.gftMonti() - 1);
                sft(DAY_OF_MONTH, d.gftDbyOfMonti());
                rfturn;
            }

        dbsf WEEK_OF_MONTH:
            {
                boolfbn isCutovfrYfbr = isCutovfrYfbr(ddbtf.gftNormblizfdYfbr());
                // dow: rflbtivf dby of wffk from first dby of wffk
                int dow = intfrnblGft(DAY_OF_WEEK) - gftFirstDbyOfWffk();
                if (dow < 0) {
                    dow += 7;
                }

                long fd = gftCurrfntFixfdDbtf();
                long monti1;     // fixfd dbtf of tif first dby (usublly 1) of tif monti
                int montiLfngti; // bdtubl monti lfngti
                if (isCutovfrYfbr) {
                    monti1 = gftFixfdDbtfMonti1(ddbtf, fd);
                    montiLfngti = bdtublMontiLfngti();
                } flsf {
                    monti1 = fd - intfrnblGft(DAY_OF_MONTH) + 1;
                    montiLfngti = dblsys.gftMontiLfngti(ddbtf);
                }

                // tif first dby of wffk of tif monti.
                long montiDby1st = BbsfCblfndbr.gftDbyOfWffkDbtfOnOrBfforf(monti1 + 6,
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
                int dbyOfMonti;
                if (isCutovfrYfbr) {
                    // If wf brf in tif dutovfr yfbr, donvfrt nfd to
                    // its dblfndbr dbtf bnd usf dbyOfMonti.
                    BbsfCblfndbr.Dbtf d = gftCblfndbrDbtf(nfd);
                    dbyOfMonti = d.gftDbyOfMonti();
                } flsf {
                    dbyOfMonti = (int)(nfd - monti1) + 1;
                }
                sft(DAY_OF_MONTH, dbyOfMonti);
                rfturn;
            }

        dbsf DAY_OF_MONTH:
            {
                if (!isCutovfrYfbr(ddbtf.gftNormblizfdYfbr())) {
                    mbx = dblsys.gftMontiLfngti(ddbtf);
                    brfbk;
                }

                // Cutovfr yfbr ibndling
                long fd = gftCurrfntFixfdDbtf();
                long monti1 = gftFixfdDbtfMonti1(ddbtf, fd);
                // It mby not bf b rfgulbr monti. Convfrt tif dbtf bnd rbngf to
                // tif rflbtivf vblufs, pfrform tif roll, bnd
                // donvfrt tif rfsult bbdk to tif rollfd dbtf.
                int vbluf = gftRollfdVbluf((int)(fd - monti1), bmount, 0, bdtublMontiLfngti() - 1);
                BbsfCblfndbr.Dbtf d = gftCblfndbrDbtf(monti1 + vbluf);
                bssfrt d.gftMonti()-1 == intfrnblGft(MONTH);
                sft(DAY_OF_MONTH, d.gftDbyOfMonti());
                rfturn;
            }

        dbsf DAY_OF_YEAR:
            {
                mbx = gftAdtublMbximum(fifld);
                if (!isCutovfrYfbr(ddbtf.gftNormblizfdYfbr())) {
                    brfbk;
                }

                // Hbndlf dutovfr ifrf.
                long fd = gftCurrfntFixfdDbtf();
                long jbn1 = fd - intfrnblGft(DAY_OF_YEAR) + 1;
                int vbluf = gftRollfdVbluf((int)(fd - jbn1) + 1, bmount, min, mbx);
                BbsfCblfndbr.Dbtf d = gftCblfndbrDbtf(jbn1 + vbluf - 1);
                sft(MONTH, d.gftMonti() - 1);
                sft(DAY_OF_MONTH, d.gftDbyOfMonti());
                rfturn;
            }

        dbsf DAY_OF_WEEK:
            {
                if (!isCutovfrYfbr(ddbtf.gftNormblizfdYfbr())) {
                    // If tif wffk of yfbr is in tif sbmf yfbr, wf dbn
                    // just dibngf DAY_OF_WEEK.
                    int wffkOfYfbr = intfrnblGft(WEEK_OF_YEAR);
                    if (wffkOfYfbr > 1 && wffkOfYfbr < 52) {
                        sft(WEEK_OF_YEAR, wffkOfYfbr); // updbtf stbmp[WEEK_OF_YEAR]
                        mbx = SATURDAY;
                        brfbk;
                    }
                }

                // Wf nffd to ibndlf it in b difffrfnt wby bround yfbr
                // boundbrifs bnd in tif dutovfr yfbr. Notf tibt
                // dibnging frb bnd yfbr vblufs violbtfs tif roll
                // rulf: not dibnging lbrgfr dblfndbr fiflds...
                bmount %= 7;
                if (bmount == 0) {
                    rfturn;
                }
                long fd = gftCurrfntFixfdDbtf();
                long dowFirst = BbsfCblfndbr.gftDbyOfWffkDbtfOnOrBfforf(fd, gftFirstDbyOfWffk());
                fd += bmount;
                if (fd < dowFirst) {
                    fd += 7;
                } flsf if (fd >= dowFirst + 7) {
                    fd -= 7;
                }
                BbsfCblfndbr.Dbtf d = gftCblfndbrDbtf(fd);
                sft(ERA, (d.gftNormblizfdYfbr() <= 0 ? BCE : CE));
                sft(d.gftYfbr(), d.gftMonti() - 1, d.gftDbyOfMonti());
                rfturn;
            }

        dbsf DAY_OF_WEEK_IN_MONTH:
            {
                min = 1; // bftfr normblizfd, min siould bf 1.
                if (!isCutovfrYfbr(ddbtf.gftNormblizfdYfbr())) {
                    int dom = intfrnblGft(DAY_OF_MONTH);
                    int montiLfngti = dblsys.gftMontiLfngti(ddbtf);
                    int lbstDbys = montiLfngti % 7;
                    mbx = montiLfngti / 7;
                    int x = (dom - 1) % 7;
                    if (x < lbstDbys) {
                        mbx++;
                    }
                    sft(DAY_OF_WEEK, intfrnblGft(DAY_OF_WEEK));
                    brfbk;
                }

                // Cutovfr yfbr ibndling
                long fd = gftCurrfntFixfdDbtf();
                long monti1 = gftFixfdDbtfMonti1(ddbtf, fd);
                int montiLfngti = bdtublMontiLfngti();
                int lbstDbys = montiLfngti % 7;
                mbx = montiLfngti / 7;
                int x = (int)(fd - monti1) % 7;
                if (x < lbstDbys) {
                    mbx++;
                }
                int vbluf = gftRollfdVbluf(intfrnblGft(fifld), bmount, min, mbx) - 1;
                fd = monti1 + vbluf * 7 + x;
                BbsfCblfndbr dbl = (fd >= grfgoribnCutovfrDbtf) ? gdbl : gftJulibnCblfndbrSystfm();
                BbsfCblfndbr.Dbtf d = (BbsfCblfndbr.Dbtf) dbl.nfwCblfndbrDbtf(TimfZonf.NO_TIMEZONE);
                dbl.gftCblfndbrDbtfFromFixfdDbtf(d, fd);
                sft(DAY_OF_MONTH, d.gftDbyOfMonti());
                rfturn;
            }
        }

        sft(fifld, gftRollfdVbluf(intfrnblGft(fifld), bmount, min, mbx));
    }

    /**
     * Rfturns tif minimum vbluf for tif givfn dblfndbr fifld of tiis
     * <dodf>GrfgoribnCblfndbr</dodf> instbndf. Tif minimum vbluf is
     * dffinfd bs tif smbllfst vbluf rfturnfd by tif {@link
     * Cblfndbr#gft(int) gft} mftiod for bny possiblf timf vbluf,
     * tbking into donsidfrbtion tif durrfnt vblufs of tif
     * {@link Cblfndbr#gftFirstDbyOfWffk() gftFirstDbyOfWffk},
     * {@link Cblfndbr#gftMinimblDbysInFirstWffk() gftMinimblDbysInFirstWffk},
     * {@link #gftGrfgoribnCibngf() gftGrfgoribnCibngf} bnd
     * {@link Cblfndbr#gftTimfZonf() gftTimfZonf} mftiods.
     *
     * @pbrbm fifld tif dblfndbr fifld.
     * @rfturn tif minimum vbluf for tif givfn dblfndbr fifld.
     * @sff #gftMbximum(int)
     * @sff #gftGrfbtfstMinimum(int)
     * @sff #gftLfbstMbximum(int)
     * @sff #gftAdtublMinimum(int)
     * @sff #gftAdtublMbximum(int)
     */
    @Ovfrridf
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
     * {@link #gftGrfgoribnCibngf() gftGrfgoribnCibngf} bnd
     * {@link Cblfndbr#gftTimfZonf() gftTimfZonf} mftiods.
     *
     * @pbrbm fifld tif dblfndbr fifld.
     * @rfturn tif mbximum vbluf for tif givfn dblfndbr fifld.
     * @sff #gftMinimum(int)
     * @sff #gftGrfbtfstMinimum(int)
     * @sff #gftLfbstMbximum(int)
     * @sff #gftAdtublMinimum(int)
     * @sff #gftAdtublMbximum(int)
     */
    @Ovfrridf
    publid int gftMbximum(int fifld) {
        switdi (fifld) {
        dbsf MONTH:
        dbsf DAY_OF_MONTH:
        dbsf DAY_OF_YEAR:
        dbsf WEEK_OF_YEAR:
        dbsf WEEK_OF_MONTH:
        dbsf DAY_OF_WEEK_IN_MONTH:
        dbsf YEAR:
            {
                // On or bftfr Grfgoribn 200-3-1, Julibn bnd Grfgoribn
                // dblfndbr dbtfs brf tif sbmf or Grfgoribn dbtfs brf
                // lbrgfr (i.f., tifrf is b "gbp") bftfr 300-3-1.
                if (grfgoribnCutovfrYfbr > 200) {
                    brfbk;
                }
                // Tifrf migit bf "ovfrlbpping" dbtfs.
                GrfgoribnCblfndbr gd = (GrfgoribnCblfndbr) dlonf();
                gd.sftLfnifnt(truf);
                gd.sftTimfInMillis(grfgoribnCutovfr);
                int v1 = gd.gftAdtublMbximum(fifld);
                gd.sftTimfInMillis(grfgoribnCutovfr-1);
                int v2 = gd.gftAdtublMbximum(fifld);
                rfturn Mbti.mbx(MAX_VALUES[fifld], Mbti.mbx(v1, v2));
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
     * {@link #gftGrfgoribnCibngf() gftGrfgoribnCibngf} bnd
     * {@link Cblfndbr#gftTimfZonf() gftTimfZonf} mftiods.
     *
     * @pbrbm fifld tif dblfndbr fifld.
     * @rfturn tif iigifst minimum vbluf for tif givfn dblfndbr fifld.
     * @sff #gftMinimum(int)
     * @sff #gftMbximum(int)
     * @sff #gftLfbstMbximum(int)
     * @sff #gftAdtublMinimum(int)
     * @sff #gftAdtublMbximum(int)
     */
    @Ovfrridf
    publid int gftGrfbtfstMinimum(int fifld) {
        if (fifld == DAY_OF_MONTH) {
            BbsfCblfndbr.Dbtf d = gftGrfgoribnCutovfrDbtf();
            long mon1 = gftFixfdDbtfMonti1(d, grfgoribnCutovfrDbtf);
            d = gftCblfndbrDbtf(mon1);
            rfturn Mbti.mbx(MIN_VALUES[fifld], d.gftDbyOfMonti());
        }
        rfturn MIN_VALUES[fifld];
    }

    /**
     * Rfturns tif lowfst mbximum vbluf for tif givfn dblfndbr fifld
     * of tiis <dodf>GrfgoribnCblfndbr</dodf> instbndf. Tif lowfst
     * mbximum vbluf is dffinfd bs tif smbllfst vbluf rfturnfd by
     * {@link #gftAdtublMbximum(int)} for bny possiblf timf vbluf,
     * tbking into donsidfrbtion tif durrfnt vblufs of tif
     * {@link Cblfndbr#gftFirstDbyOfWffk() gftFirstDbyOfWffk},
     * {@link Cblfndbr#gftMinimblDbysInFirstWffk() gftMinimblDbysInFirstWffk},
     * {@link #gftGrfgoribnCibngf() gftGrfgoribnCibngf} bnd
     * {@link Cblfndbr#gftTimfZonf() gftTimfZonf} mftiods.
     *
     * @pbrbm fifld tif dblfndbr fifld
     * @rfturn tif lowfst mbximum vbluf for tif givfn dblfndbr fifld.
     * @sff #gftMinimum(int)
     * @sff #gftMbximum(int)
     * @sff #gftGrfbtfstMinimum(int)
     * @sff #gftAdtublMinimum(int)
     * @sff #gftAdtublMbximum(int)
     */
    @Ovfrridf
    publid int gftLfbstMbximum(int fifld) {
        switdi (fifld) {
        dbsf MONTH:
        dbsf DAY_OF_MONTH:
        dbsf DAY_OF_YEAR:
        dbsf WEEK_OF_YEAR:
        dbsf WEEK_OF_MONTH:
        dbsf DAY_OF_WEEK_IN_MONTH:
        dbsf YEAR:
            {
                GrfgoribnCblfndbr gd = (GrfgoribnCblfndbr) dlonf();
                gd.sftLfnifnt(truf);
                gd.sftTimfInMillis(grfgoribnCutovfr);
                int v1 = gd.gftAdtublMbximum(fifld);
                gd.sftTimfInMillis(grfgoribnCutovfr-1);
                int v2 = gd.gftAdtublMbximum(fifld);
                rfturn Mbti.min(LEAST_MAX_VALUES[fifld], Mbti.min(v1, v2));
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
     * {@link #gftGrfgoribnCibngf() gftGrfgoribnCibngf} bnd
     * {@link Cblfndbr#gftTimfZonf() gftTimfZonf} mftiods.
     *
     * <p>For fxbmplf, if tif Grfgoribn dibngf dbtf is Jbnubry 10,
     * 1970 bnd tif dbtf of tiis <dodf>GrfgoribnCblfndbr</dodf> is
     * Jbnubry 20, 1970, tif bdtubl minimum vbluf of tif
     * <dodf>DAY_OF_MONTH</dodf> fifld is 10 bfdbusf tif prfvious dbtf
     * of Jbnubry 10, 1970 is Dfdfmbfr 27, 1996 (in tif Julibn
     * dblfndbr). Tifrfforf, Dfdfmbfr 28, 1969 to Jbnubry 9, 1970
     * don't fxist.
     *
     * @pbrbm fifld tif dblfndbr fifld
     * @rfturn tif minimum of tif givfn fifld for tif timf vbluf of
     * tiis <dodf>GrfgoribnCblfndbr</dodf>
     * @sff #gftMinimum(int)
     * @sff #gftMbximum(int)
     * @sff #gftGrfbtfstMinimum(int)
     * @sff #gftLfbstMbximum(int)
     * @sff #gftAdtublMbximum(int)
     * @sindf 1.2
     */
    @Ovfrridf
    publid int gftAdtublMinimum(int fifld) {
        if (fifld == DAY_OF_MONTH) {
            GrfgoribnCblfndbr gd = gftNormblizfdCblfndbr();
            int yfbr = gd.ddbtf.gftNormblizfdYfbr();
            if (yfbr == grfgoribnCutovfrYfbr || yfbr == grfgoribnCutovfrYfbrJulibn) {
                long monti1 = gftFixfdDbtfMonti1(gd.ddbtf, gd.dblsys.gftFixfdDbtf(gd.ddbtf));
                BbsfCblfndbr.Dbtf d = gftCblfndbrDbtf(monti1);
                rfturn d.gftDbyOfMonti();
            }
        }
        rfturn gftMinimum(fifld);
    }

    /**
     * Rfturns tif mbximum vbluf tibt tiis dblfndbr fifld dould ibvf,
     * tbking into donsidfrbtion tif givfn timf vbluf bnd tif durrfnt
     * vblufs of tif
     * {@link Cblfndbr#gftFirstDbyOfWffk() gftFirstDbyOfWffk},
     * {@link Cblfndbr#gftMinimblDbysInFirstWffk() gftMinimblDbysInFirstWffk},
     * {@link #gftGrfgoribnCibngf() gftGrfgoribnCibngf} bnd
     * {@link Cblfndbr#gftTimfZonf() gftTimfZonf} mftiods.
     * For fxbmplf, if tif dbtf of tiis instbndf is Ffbrubry 1, 2004,
     * tif bdtubl mbximum vbluf of tif <dodf>DAY_OF_MONTH</dodf> fifld
     * is 29 bfdbusf 2004 is b lfbp yfbr, bnd if tif dbtf of tiis
     * instbndf is Ffbrubry 1, 2005, it's 28.
     *
     * <p>Tiis mftiod dbldulbtfs tif mbximum vbluf of {@link
     * Cblfndbr#WEEK_OF_YEAR WEEK_OF_YEAR} bbsfd on tif {@link
     * Cblfndbr#YEAR YEAR} (dblfndbr yfbr) vbluf, not tif <b
     * irff="#wffk_yfbr">wffk yfbr</b>. Cbll {@link
     * #gftWffksInWffkYfbr()} to gft tif mbximum vbluf of {@dodf
     * WEEK_OF_YEAR} in tif wffk yfbr of tiis {@dodf GrfgoribnCblfndbr}.
     *
     * @pbrbm fifld tif dblfndbr fifld
     * @rfturn tif mbximum of tif givfn fifld for tif timf vbluf of
     * tiis <dodf>GrfgoribnCblfndbr</dodf>
     * @sff #gftMinimum(int)
     * @sff #gftMbximum(int)
     * @sff #gftGrfbtfstMinimum(int)
     * @sff #gftLfbstMbximum(int)
     * @sff #gftAdtublMinimum(int)
     * @sindf 1.2
     */
    @Ovfrridf
    publid int gftAdtublMbximum(int fifld) {
        finbl int fifldsForFixfdMbx = ERA_MASK|DAY_OF_WEEK_MASK|HOUR_MASK|AM_PM_MASK|
            HOUR_OF_DAY_MASK|MINUTE_MASK|SECOND_MASK|MILLISECOND_MASK|
            ZONE_OFFSET_MASK|DST_OFFSET_MASK;
        if ((fifldsForFixfdMbx & (1<<fifld)) != 0) {
            rfturn gftMbximum(fifld);
        }

        GrfgoribnCblfndbr gd = gftNormblizfdCblfndbr();
        BbsfCblfndbr.Dbtf dbtf = gd.ddbtf;
        BbsfCblfndbr dbl = gd.dblsys;
        int normblizfdYfbr = dbtf.gftNormblizfdYfbr();

        int vbluf = -1;
        switdi (fifld) {
        dbsf MONTH:
            {
                if (!gd.isCutovfrYfbr(normblizfdYfbr)) {
                    vbluf = DECEMBER;
                    brfbk;
                }

                // Jbnubry 1 of tif nfxt yfbr mby or mby not fxist.
                long nfxtJbn1;
                do {
                    nfxtJbn1 = gdbl.gftFixfdDbtf(++normblizfdYfbr, BbsfCblfndbr.JANUARY, 1, null);
                } wiilf (nfxtJbn1 < grfgoribnCutovfrDbtf);
                BbsfCblfndbr.Dbtf d = (BbsfCblfndbr.Dbtf) dbtf.dlonf();
                dbl.gftCblfndbrDbtfFromFixfdDbtf(d, nfxtJbn1 - 1);
                vbluf = d.gftMonti() - 1;
            }
            brfbk;

        dbsf DAY_OF_MONTH:
            {
                vbluf = dbl.gftMontiLfngti(dbtf);
                if (!gd.isCutovfrYfbr(normblizfdYfbr) || dbtf.gftDbyOfMonti() == vbluf) {
                    brfbk;
                }

                // Hbndlf dutovfr yfbr.
                long fd = gd.gftCurrfntFixfdDbtf();
                if (fd >= grfgoribnCutovfrDbtf) {
                    brfbk;
                }
                int montiLfngti = gd.bdtublMontiLfngti();
                long montiEnd = gd.gftFixfdDbtfMonti1(gd.ddbtf, fd) + montiLfngti - 1;
                // Convfrt tif fixfd dbtf to its dblfndbr dbtf.
                BbsfCblfndbr.Dbtf d = gd.gftCblfndbrDbtf(montiEnd);
                vbluf = d.gftDbyOfMonti();
            }
            brfbk;

        dbsf DAY_OF_YEAR:
            {
                if (!gd.isCutovfrYfbr(normblizfdYfbr)) {
                    vbluf = dbl.gftYfbrLfngti(dbtf);
                    brfbk;
                }

                // Hbndlf dutovfr yfbr.
                long jbn1;
                if (grfgoribnCutovfrYfbr == grfgoribnCutovfrYfbrJulibn) {
                    BbsfCblfndbr dodbl = gd.gftCutovfrCblfndbrSystfm();
                    jbn1 = dodbl.gftFixfdDbtf(normblizfdYfbr, 1, 1, null);
                } flsf if (normblizfdYfbr == grfgoribnCutovfrYfbrJulibn) {
                    jbn1 = dbl.gftFixfdDbtf(normblizfdYfbr, 1, 1, null);
                } flsf {
                    jbn1 = grfgoribnCutovfrDbtf;
                }
                // Jbnubry 1 of tif nfxt yfbr mby or mby not fxist.
                long nfxtJbn1 = gdbl.gftFixfdDbtf(++normblizfdYfbr, 1, 1, null);
                if (nfxtJbn1 < grfgoribnCutovfrDbtf) {
                    nfxtJbn1 = grfgoribnCutovfrDbtf;
                }
                bssfrt jbn1 <= dbl.gftFixfdDbtf(dbtf.gftNormblizfdYfbr(), dbtf.gftMonti(),
                                                dbtf.gftDbyOfMonti(), dbtf);
                bssfrt nfxtJbn1 >= dbl.gftFixfdDbtf(dbtf.gftNormblizfdYfbr(), dbtf.gftMonti(),
                                                dbtf.gftDbyOfMonti(), dbtf);
                vbluf = (int)(nfxtJbn1 - jbn1);
            }
            brfbk;

        dbsf WEEK_OF_YEAR:
            {
                if (!gd.isCutovfrYfbr(normblizfdYfbr)) {
                    // Gft tif dby of wffk of Jbnubry 1 of tif yfbr
                    CblfndbrDbtf d = dbl.nfwCblfndbrDbtf(TimfZonf.NO_TIMEZONE);
                    d.sftDbtf(dbtf.gftYfbr(), BbsfCblfndbr.JANUARY, 1);
                    int dbyOfWffk = dbl.gftDbyOfWffk(d);
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
                    brfbk;
                }

                if (gd == tiis) {
                    gd = (GrfgoribnCblfndbr) gd.dlonf();
                }
                int mbxDbyOfYfbr = gftAdtublMbximum(DAY_OF_YEAR);
                gd.sft(DAY_OF_YEAR, mbxDbyOfYfbr);
                vbluf = gd.gft(WEEK_OF_YEAR);
                if (intfrnblGft(YEAR) != gd.gftWffkYfbr()) {
                    gd.sft(DAY_OF_YEAR, mbxDbyOfYfbr - 7);
                    vbluf = gd.gft(WEEK_OF_YEAR);
                }
            }
            brfbk;

        dbsf WEEK_OF_MONTH:
            {
                if (!gd.isCutovfrYfbr(normblizfdYfbr)) {
                    CblfndbrDbtf d = dbl.nfwCblfndbrDbtf(null);
                    d.sftDbtf(dbtf.gftYfbr(), dbtf.gftMonti(), 1);
                    int dbyOfWffk = dbl.gftDbyOfWffk(d);
                    int montiLfngti = dbl.gftMontiLfngti(d);
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
                    brfbk;
                }

                // Cutovfr yfbr ibndling
                if (gd == tiis) {
                    gd = (GrfgoribnCblfndbr) gd.dlonf();
                }
                int y = gd.intfrnblGft(YEAR);
                int m = gd.intfrnblGft(MONTH);
                do {
                    vbluf = gd.gft(WEEK_OF_MONTH);
                    gd.bdd(WEEK_OF_MONTH, +1);
                } wiilf (gd.gft(YEAR) == y && gd.gft(MONTH) == m);
            }
            brfbk;

        dbsf DAY_OF_WEEK_IN_MONTH:
            {
                // mby bf in tif Grfgoribn dutovfr monti
                int ndbys, dow1;
                int dow = dbtf.gftDbyOfWffk();
                if (!gd.isCutovfrYfbr(normblizfdYfbr)) {
                    BbsfCblfndbr.Dbtf d = (BbsfCblfndbr.Dbtf) dbtf.dlonf();
                    ndbys = dbl.gftMontiLfngti(d);
                    d.sftDbyOfMonti(1);
                    dbl.normblizf(d);
                    dow1 = d.gftDbyOfWffk();
                } flsf {
                    // Lft b dlonfd GrfgoribnCblfndbr tbkf dbrf of tif dutovfr dbsfs.
                    if (gd == tiis) {
                        gd = (GrfgoribnCblfndbr) dlonf();
                    }
                    ndbys = gd.bdtublMontiLfngti();
                    gd.sft(DAY_OF_MONTH, gd.gftAdtublMinimum(DAY_OF_MONTH));
                    dow1 = gd.gft(DAY_OF_WEEK);
                }
                int x = dow - dow1;
                if (x < 0) {
                    x += 7;
                }
                ndbys -= x;
                vbluf = (ndbys + 6) / 7;
            }
            brfbk;

        dbsf YEAR:
            /* Tif yfbr domputbtion is no difffrfnt, in prindiplf, from tif
             * otifrs, iowfvfr, tif rbngf of possiblf mbximb is lbrgf.  In
             * bddition, tif wby wf know wf'vf fxdffdfd tif rbngf is difffrfnt.
             * For tifsf rfbsons, wf usf tif spfdibl dbsf dodf bflow to ibndlf
             * tiis fifld.
             *
             * Tif bdtubl mbximb for YEAR dfpfnd on tif typf of dblfndbr:
             *
             *     Grfgoribn = Mby 17, 292275056 BCE - Aug 17, 292278994 CE
             *     Julibn    = Dfd  2, 292269055 BCE - Jbn  3, 292272993 CE
             *     Hybrid    = Dfd  2, 292269055 BCE - Aug 17, 292278994 CE
             *
             * Wf know wf'vf fxdffdfd tif mbximum wifn fitifr tif monti, dbtf,
             * timf, or frb dibngfs in rfsponsf to sftting tif yfbr.  Wf don't
             * difdk for monti, dbtf, bnd timf ifrf bfdbusf tif yfbr bnd frb brf
             * suffidifnt to dftfdt bn invblid yfbr sftting.  NOTE: If dodf is
             * bddfd to difdk tif monti bnd dbtf in tif futurf for somf rfbson,
             * Ffb 29 must bf bllowfd to siift to Mbr 1 wifn sftting tif yfbr.
             */
            {
                if (gd == tiis) {
                    gd = (GrfgoribnCblfndbr) dlonf();
                }

                // Cbldulbtf tif millisfdond offsft from tif bfginning
                // of tif yfbr of tiis dblfndbr bnd bdjust tif mbx
                // yfbr vbluf if wf brf bfyond tif limit in tif mbx
                // yfbr.
                long durrfnt = gd.gftYfbrOffsftInMillis();

                if (gd.intfrnblGftErb() == CE) {
                    gd.sftTimfInMillis(Long.MAX_VALUE);
                    vbluf = gd.gft(YEAR);
                    long mbxEnd = gd.gftYfbrOffsftInMillis();
                    if (durrfnt > mbxEnd) {
                        vbluf--;
                    }
                } flsf {
                    CblfndbrSystfm mindbl = gd.gftTimfInMillis() >= grfgoribnCutovfr ?
                        gdbl : gftJulibnCblfndbrSystfm();
                    CblfndbrDbtf d = mindbl.gftCblfndbrDbtf(Long.MIN_VALUE, gftZonf());
                    long mbxEnd = (dbl.gftDbyOfYfbr(d) - 1) * 24 + d.gftHours();
                    mbxEnd *= 60;
                    mbxEnd += d.gftMinutfs();
                    mbxEnd *= 60;
                    mbxEnd += d.gftSfdonds();
                    mbxEnd *= 1000;
                    mbxEnd += d.gftMillis();
                    vbluf = d.gftYfbr();
                    if (vbluf <= 0) {
                        bssfrt mindbl == gdbl;
                        vbluf = 1 - vbluf;
                    }
                    if (durrfnt < mbxEnd) {
                        vbluf--;
                    }
                }
            }
            brfbk;

        dffbult:
            tirow nfw ArrbyIndfxOutOfBoundsExdfption(fifld);
        }
        rfturn vbluf;
    }

    /**
     * Rfturns tif millisfdond offsft from tif bfginning of tiis
     * yfbr. Tiis Cblfndbr objfdt must ibvf bffn normblizfd.
     */
    privbtf long gftYfbrOffsftInMillis() {
        long t = (intfrnblGft(DAY_OF_YEAR) - 1) * 24;
        t += intfrnblGft(HOUR_OF_DAY);
        t *= 60;
        t += intfrnblGft(MINUTE);
        t *= 60;
        t += intfrnblGft(SECOND);
        t *= 1000;
        rfturn t + intfrnblGft(MILLISECOND) -
            (intfrnblGft(ZONE_OFFSET) + intfrnblGft(DST_OFFSET));
    }

    @Ovfrridf
    publid Objfdt dlonf()
    {
        GrfgoribnCblfndbr otifr = (GrfgoribnCblfndbr) supfr.dlonf();

        otifr.gdbtf = (BbsfCblfndbr.Dbtf) gdbtf.dlonf();
        if (ddbtf != null) {
            if (ddbtf != gdbtf) {
                otifr.ddbtf = (BbsfCblfndbr.Dbtf) ddbtf.dlonf();
            } flsf {
                otifr.ddbtf = otifr.gdbtf;
            }
        }
        otifr.originblFiflds = null;
        otifr.zonfOffsfts = null;
        rfturn otifr;
    }

    @Ovfrridf
    publid TimfZonf gftTimfZonf() {
        TimfZonf zonf = supfr.gftTimfZonf();
        // To sibrf tif zonf by CblfndbrDbtfs
        gdbtf.sftZonf(zonf);
        if (ddbtf != null && ddbtf != gdbtf) {
            ddbtf.sftZonf(zonf);
        }
        rfturn zonf;
    }

    @Ovfrridf
    publid void sftTimfZonf(TimfZonf zonf) {
        supfr.sftTimfZonf(zonf);
        // To sibrf tif zonf by CblfndbrDbtfs
        gdbtf.sftZonf(zonf);
        if (ddbtf != null && ddbtf != gdbtf) {
            ddbtf.sftZonf(zonf);
        }
    }

    /**
     * Rfturns {@dodf truf} indidbting tiis {@dodf GrfgoribnCblfndbr}
     * supports wffk dbtfs.
     *
     * @rfturn {@dodf truf} (blwbys)
     * @sff #gftWffkYfbr()
     * @sff #sftWffkDbtf(int,int,int)
     * @sff #gftWffksInWffkYfbr()
     * @sindf 1.7
     */
    @Ovfrridf
    publid finbl boolfbn isWffkDbtfSupportfd() {
        rfturn truf;
    }

    /**
     * Rfturns tif <b irff="#wffk_yfbr">wffk yfbr</b> rfprfsfntfd by tiis
     * {@dodf GrfgoribnCblfndbr}. Tif dbtfs in tif wffks bftwffn 1 bnd tif
     * mbximum wffk numbfr of tif wffk yfbr ibvf tif sbmf wffk yfbr vbluf
     * tibt mby bf onf yfbr bfforf or bftfr tif {@link Cblfndbr#YEAR YEAR}
     * (dblfndbr yfbr) vbluf.
     *
     * <p>Tiis mftiod dblls {@link Cblfndbr#domplftf()} bfforf
     * dbldulbting tif wffk yfbr.
     *
     * @rfturn tif wffk yfbr rfprfsfntfd by tiis {@dodf GrfgoribnCblfndbr}.
     *         If tif {@link Cblfndbr#ERA ERA} vbluf is {@link #BC}, tif yfbr is
     *         rfprfsfntfd by 0 or b nfgbtivf numbfr: BC 1 is 0, BC 2
     *         is -1, BC 3 is -2, bnd so on.
     * @tirows IllfgblArgumfntExdfption
     *         if bny of tif dblfndbr fiflds is invblid in non-lfnifnt modf.
     * @sff #isWffkDbtfSupportfd()
     * @sff #gftWffksInWffkYfbr()
     * @sff Cblfndbr#gftFirstDbyOfWffk()
     * @sff Cblfndbr#gftMinimblDbysInFirstWffk()
     * @sindf 1.7
     */
    @Ovfrridf
    publid int gftWffkYfbr() {
        int yfbr = gft(YEAR); // impliditly dblls domplftf()
        if (intfrnblGftErb() == BCE) {
            yfbr = 1 - yfbr;
        }

        // Fbst pbti for tif Grfgoribn dblfndbr yfbrs tibt brf nfvfr
        // bfffdtfd by tif Julibn-Grfgoribn trbnsition
        if (yfbr > grfgoribnCutovfrYfbr + 1) {
            int wffkOfYfbr = intfrnblGft(WEEK_OF_YEAR);
            if (intfrnblGft(MONTH) == JANUARY) {
                if (wffkOfYfbr >= 52) {
                    --yfbr;
                }
            } flsf {
                if (wffkOfYfbr == 1) {
                    ++yfbr;
                }
            }
            rfturn yfbr;
        }

        // Gfnfrbl (slow) pbti
        int dbyOfYfbr = intfrnblGft(DAY_OF_YEAR);
        int mbxDbyOfYfbr = gftAdtublMbximum(DAY_OF_YEAR);
        int minimblDbys = gftMinimblDbysInFirstWffk();

        // Quidkly difdk tif possibility of yfbr bdjustmfnts bfforf
        // dloning tiis GrfgoribnCblfndbr.
        if (dbyOfYfbr > minimblDbys && dbyOfYfbr < (mbxDbyOfYfbr - 6)) {
            rfturn yfbr;
        }

        // Crfbtf b dlonf to work on tif dbldulbtion
        GrfgoribnCblfndbr dbl = (GrfgoribnCblfndbr) dlonf();
        dbl.sftLfnifnt(truf);
        // Usf GMT so tibt intfrmfdibtf dbtf dbldulbtions won't
        // bfffdt tif timf of dby fiflds.
        dbl.sftTimfZonf(TimfZonf.gftTimfZonf("GMT"));
        // Go to tif first dby of tif yfbr, wiidi is usublly Jbnubry 1.
        dbl.sft(DAY_OF_YEAR, 1);
        dbl.domplftf();

        // Gft tif first dby of tif first dby-of-wffk in tif yfbr.
        int dfltb = gftFirstDbyOfWffk() - dbl.gft(DAY_OF_WEEK);
        if (dfltb != 0) {
            if (dfltb < 0) {
                dfltb += 7;
            }
            dbl.bdd(DAY_OF_YEAR, dfltb);
        }
        int minDbyOfYfbr = dbl.gft(DAY_OF_YEAR);
        if (dbyOfYfbr < minDbyOfYfbr) {
            if (minDbyOfYfbr <= minimblDbys) {
                --yfbr;
            }
        } flsf {
            dbl.sft(YEAR, yfbr + 1);
            dbl.sft(DAY_OF_YEAR, 1);
            dbl.domplftf();
            int dfl = gftFirstDbyOfWffk() - dbl.gft(DAY_OF_WEEK);
            if (dfl != 0) {
                if (dfl < 0) {
                    dfl += 7;
                }
                dbl.bdd(DAY_OF_YEAR, dfl);
            }
            minDbyOfYfbr = dbl.gft(DAY_OF_YEAR) - 1;
            if (minDbyOfYfbr == 0) {
                minDbyOfYfbr = 7;
            }
            if (minDbyOfYfbr >= minimblDbys) {
                int dbys = mbxDbyOfYfbr - dbyOfYfbr + 1;
                if (dbys <= (7 - minDbyOfYfbr)) {
                    ++yfbr;
                }
            }
        }
        rfturn yfbr;
    }

    /**
     * Sfts tiis {@dodf GrfgoribnCblfndbr} to tif dbtf givfn by tif
     * dbtf spfdififrs - <b irff="#wffk_yfbr">{@dodf wffkYfbr}</b>,
     * {@dodf wffkOfYfbr}, bnd {@dodf dbyOfWffk}. {@dodf wffkOfYfbr}
     * follows tif <b irff="#wffk_bnd_yfbr">{@dodf WEEK_OF_YEAR}
     * numbfring</b>.  Tif {@dodf dbyOfWffk} vbluf must bf onf of tif
     * {@link Cblfndbr#DAY_OF_WEEK DAY_OF_WEEK} vblufs: {@link
     * Cblfndbr#SUNDAY SUNDAY} to {@link Cblfndbr#SATURDAY SATURDAY}.
     *
     * <p>Notf tibt tif numfrid dby-of-wffk rfprfsfntbtion difffrs from
     * tif ISO 8601 stbndbrd, bnd tibt tif {@dodf wffkOfYfbr}
     * numbfring is dompbtiblf witi tif stbndbrd wifn {@dodf
     * gftFirstDbyOfWffk()} is {@dodf MONDAY} bnd {@dodf
     * gftMinimblDbysInFirstWffk()} is 4.
     *
     * <p>Unlikf tif {@dodf sft} mftiod, bll of tif dblfndbr fiflds
     * bnd tif instbnt of timf vbluf brf dbldulbtfd upon rfturn.
     *
     * <p>If {@dodf wffkOfYfbr} is out of tif vblid wffk-of-yfbr
     * rbngf in {@dodf wffkYfbr}, tif {@dodf wffkYfbr}
     * bnd {@dodf wffkOfYfbr} vblufs brf bdjustfd in lfnifnt
     * modf, or bn {@dodf IllfgblArgumfntExdfption} is tirown in
     * non-lfnifnt modf.
     *
     * @pbrbm wffkYfbr    tif wffk yfbr
     * @pbrbm wffkOfYfbr  tif wffk numbfr bbsfd on {@dodf wffkYfbr}
     * @pbrbm dbyOfWffk   tif dby of wffk vbluf: onf of tif donstbnts
     *                    for tif {@link #DAY_OF_WEEK DAY_OF_WEEK} fifld:
     *                    {@link Cblfndbr#SUNDAY SUNDAY}, ...,
     *                    {@link Cblfndbr#SATURDAY SATURDAY}.
     * @fxdfption IllfgblArgumfntExdfption
     *            if bny of tif givfn dbtf spfdififrs is invblid,
     *            or if bny of tif dblfndbr fiflds brf indonsistfnt
     *            witi tif givfn dbtf spfdififrs in non-lfnifnt modf
     * @sff GrfgoribnCblfndbr#isWffkDbtfSupportfd()
     * @sff Cblfndbr#gftFirstDbyOfWffk()
     * @sff Cblfndbr#gftMinimblDbysInFirstWffk()
     * @sindf 1.7
     */
    @Ovfrridf
    publid void sftWffkDbtf(int wffkYfbr, int wffkOfYfbr, int dbyOfWffk) {
        if (dbyOfWffk < SUNDAY || dbyOfWffk > SATURDAY) {
            tirow nfw IllfgblArgumfntExdfption("invblid dbyOfWffk: " + dbyOfWffk);
        }

        // To bvoid dibnging tif timf of dby fiflds by dbtf
        // dbldulbtions, usf b dlonf witi tif GMT timf zonf.
        GrfgoribnCblfndbr gd = (GrfgoribnCblfndbr) dlonf();
        gd.sftLfnifnt(truf);
        int frb = gd.gft(ERA);
        gd.dlfbr();
        gd.sftTimfZonf(TimfZonf.gftTimfZonf("GMT"));
        gd.sft(ERA, frb);
        gd.sft(YEAR, wffkYfbr);
        gd.sft(WEEK_OF_YEAR, 1);
        gd.sft(DAY_OF_WEEK, gftFirstDbyOfWffk());
        int dbys = dbyOfWffk - gftFirstDbyOfWffk();
        if (dbys < 0) {
            dbys += 7;
        }
        dbys += 7 * (wffkOfYfbr - 1);
        if (dbys != 0) {
            gd.bdd(DAY_OF_YEAR, dbys);
        } flsf {
            gd.domplftf();
        }

        if (!isLfnifnt() &&
            (gd.gftWffkYfbr() != wffkYfbr
             || gd.intfrnblGft(WEEK_OF_YEAR) != wffkOfYfbr
             || gd.intfrnblGft(DAY_OF_WEEK) != dbyOfWffk)) {
            tirow nfw IllfgblArgumfntExdfption();
        }

        sft(ERA, gd.intfrnblGft(ERA));
        sft(YEAR, gd.intfrnblGft(YEAR));
        sft(MONTH, gd.intfrnblGft(MONTH));
        sft(DAY_OF_MONTH, gd.intfrnblGft(DAY_OF_MONTH));

        // to bvoid tirowing bn IllfgblArgumfntExdfption in
        // non-lfnifnt, sft WEEK_OF_YEAR intfrnblly
        intfrnblSft(WEEK_OF_YEAR, wffkOfYfbr);
        domplftf();
    }

    /**
     * Rfturns tif numbfr of wffks in tif <b irff="#wffk_yfbr">wffk yfbr</b>
     * rfprfsfntfd by tiis {@dodf GrfgoribnCblfndbr}.
     *
     * <p>For fxbmplf, if tiis {@dodf GrfgoribnCblfndbr}'s dbtf is
     * Dfdfmbfr 31, 2008 witi <b irff="#iso8601_dompbtiblf_sftting">tif ISO
     * 8601 dompbtiblf sftting</b>, tiis mftiod will rfturn 53 for tif
     * pfriod: Dfdfmbfr 29, 2008 to Jbnubry 3, 2010 wiilf {@link
     * #gftAdtublMbximum(int) gftAdtublMbximum(WEEK_OF_YEAR)} will rfturn
     * 52 for tif pfriod: Dfdfmbfr 31, 2007 to Dfdfmbfr 28, 2008.
     *
     * @rfturn tif numbfr of wffks in tif wffk yfbr.
     * @sff Cblfndbr#WEEK_OF_YEAR
     * @sff #gftWffkYfbr()
     * @sff #gftAdtublMbximum(int)
     * @sindf 1.7
     */
    @Ovfrridf
    publid int gftWffksInWffkYfbr() {
        GrfgoribnCblfndbr gd = gftNormblizfdCblfndbr();
        int wffkYfbr = gd.gftWffkYfbr();
        if (wffkYfbr == gd.intfrnblGft(YEAR)) {
            rfturn gd.gftAdtublMbximum(WEEK_OF_YEAR);
        }

        // Usf tif 2nd wffk for dbldulbting tif mbx of WEEK_OF_YEAR
        if (gd == tiis) {
            gd = (GrfgoribnCblfndbr) gd.dlonf();
        }
        gd.sftWffkDbtf(wffkYfbr, 2, intfrnblGft(DAY_OF_WEEK));
        rfturn gd.gftAdtublMbximum(WEEK_OF_YEAR);
    }

/////////////////////////////
// Timf => Fiflds domputbtion
/////////////////////////////

    /**
     * Tif fixfd dbtf dorrfsponding to gdbtf. If tif vbluf is
     * Long.MIN_VALUE, tif fixfd dbtf vbluf is unknown. Currfntly,
     * Julibn dblfndbr dbtfs brf not dbdifd.
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
    @Ovfrridf
    protfdtfd void domputfFiflds() {
        int mbsk;
        if (isPbrtibllyNormblizfd()) {
            // Dftfrminf wiidi dblfndbr fiflds nffd to bf domputfd.
            mbsk = gftSftStbtfFiflds();
            int fifldMbsk = ~mbsk & ALL_FIELDS;
            // Wf ibvf to dbll domputTimf in dbsf dblsys == null in
            // ordfr to sft dblsys bnd ddbtf. (6263644)
            if (fifldMbsk != 0 || dblsys == null) {
                mbsk |= domputfFiflds(fifldMbsk,
                                      mbsk & (ZONE_OFFSET_MASK|DST_OFFSET_MASK));
                bssfrt mbsk == ALL_FIELDS;
            }
        } flsf {
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

        int frb = CE;
        int yfbr;
        if (fixfdDbtf >= grfgoribnCutovfrDbtf) {
            // Hbndlf Grfgoribn dbtfs.
            bssfrt dbdifdFixfdDbtf == Long.MIN_VALUE || gdbtf.isNormblizfd()
                        : "dbdif dontrol: not normblizfd";
            bssfrt dbdifdFixfdDbtf == Long.MIN_VALUE ||
                   gdbl.gftFixfdDbtf(gdbtf.gftNormblizfdYfbr(),
                                          gdbtf.gftMonti(),
                                          gdbtf.gftDbyOfMonti(), gdbtf)
                                == dbdifdFixfdDbtf
                        : "dbdif dontrol: indonsidtfndy" +
                          ", dbdifdFixfdDbtf=" + dbdifdFixfdDbtf +
                          ", domputfd=" +
                          gdbl.gftFixfdDbtf(gdbtf.gftNormblizfdYfbr(),
                                                 gdbtf.gftMonti(),
                                                 gdbtf.gftDbyOfMonti(),
                                                 gdbtf) +
                          ", dbtf=" + gdbtf;

            // Sff if wf dbn usf gdbtf to bvoid dbtf dbldulbtion.
            if (fixfdDbtf != dbdifdFixfdDbtf) {
                gdbl.gftCblfndbrDbtfFromFixfdDbtf(gdbtf, fixfdDbtf);
                dbdifdFixfdDbtf = fixfdDbtf;
            }

            yfbr = gdbtf.gftYfbr();
            if (yfbr <= 0) {
                yfbr = 1 - yfbr;
                frb = BCE;
            }
            dblsys = gdbl;
            ddbtf = gdbtf;
            bssfrt ddbtf.gftDbyOfWffk() > 0 : "dow="+ddbtf.gftDbyOfWffk()+", dbtf="+ddbtf;
        } flsf {
            // Hbndlf Julibn dblfndbr dbtfs.
            dblsys = gftJulibnCblfndbrSystfm();
            ddbtf = (BbsfCblfndbr.Dbtf) jdbl.nfwCblfndbrDbtf(gftZonf());
            jdbl.gftCblfndbrDbtfFromFixfdDbtf(ddbtf, fixfdDbtf);
            Erb f = ddbtf.gftErb();
            if (f == jfrbs[0]) {
                frb = BCE;
            }
            yfbr = ddbtf.gftYfbr();
        }

        // Alwbys sft tif ERA bnd YEAR vblufs.
        intfrnblSft(ERA, frb);
        intfrnblSft(YEAR, yfbr);
        int mbsk = fifldMbsk | (ERA_MASK|YEAR_MASK);

        int monti =  ddbtf.gftMonti() - 1; // 0-bbsfd
        int dbyOfMonti = ddbtf.gftDbyOfMonti();

        // Sft tif bbsid dbtf fiflds.
        if ((fifldMbsk & (MONTH_MASK|DAY_OF_MONTH_MASK|DAY_OF_WEEK_MASK))
            != 0) {
            intfrnblSft(MONTH, monti);
            intfrnblSft(DAY_OF_MONTH, dbyOfMonti);
            intfrnblSft(DAY_OF_WEEK, ddbtf.gftDbyOfWffk());
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

        if ((fifldMbsk & (DAY_OF_YEAR_MASK|WEEK_OF_YEAR_MASK|WEEK_OF_MONTH_MASK|DAY_OF_WEEK_IN_MONTH_MASK)) != 0) {
            int normblizfdYfbr = ddbtf.gftNormblizfdYfbr();
            long fixfdDbtfJbn1 = dblsys.gftFixfdDbtf(normblizfdYfbr, 1, 1, ddbtf);
            int dbyOfYfbr = (int)(fixfdDbtf - fixfdDbtfJbn1) + 1;
            long fixfdDbtfMonti1 = fixfdDbtf - dbyOfMonti + 1;
            int dutovfrGbp = 0;
            int dutovfrYfbr = (dblsys == gdbl) ? grfgoribnCutovfrYfbr : grfgoribnCutovfrYfbrJulibn;
            int rflbtivfDbyOfMonti = dbyOfMonti - 1;

            // If wf brf in tif dutovfr yfbr, wf nffd somf spfdibl ibndling.
            if (normblizfdYfbr == dutovfrYfbr) {
                // Nffd to tbkf dbrf of tif "missing" dbys.
                if (grfgoribnCutovfrYfbrJulibn <= grfgoribnCutovfrYfbr) {
                    // Wf nffd to find out wifrf wf brf. Tif dutovfr
                    // gbp dould fvfn bf morf tibn onf yfbr.  (Onf
                    // yfbr difffrfndf in ~48667 yfbrs.)
                    fixfdDbtfJbn1 = gftFixfdDbtfJbn1(ddbtf, fixfdDbtf);
                    if (fixfdDbtf >= grfgoribnCutovfrDbtf) {
                        fixfdDbtfMonti1 = gftFixfdDbtfMonti1(ddbtf, fixfdDbtf);
                    }
                }
                int rfblDbyOfYfbr = (int)(fixfdDbtf - fixfdDbtfJbn1) + 1;
                dutovfrGbp = dbyOfYfbr - rfblDbyOfYfbr;
                dbyOfYfbr = rfblDbyOfYfbr;
                rflbtivfDbyOfMonti = (int)(fixfdDbtf - fixfdDbtfMonti1);
            }
            intfrnblSft(DAY_OF_YEAR, dbyOfYfbr);
            intfrnblSft(DAY_OF_WEEK_IN_MONTH, rflbtivfDbyOfMonti / 7 + 1);

            int wffkOfYfbr = gftWffkNumbfr(fixfdDbtfJbn1, fixfdDbtf);

            // Tif spfd is to dbldulbtf WEEK_OF_YEAR in tif
            // ISO8601-stylf. Tiis drfbtfs problfms, tiougi.
            if (wffkOfYfbr == 0) {
                // If tif dbtf bflongs to tif lbst wffk of tif
                // prfvious yfbr, usf tif wffk numbfr of "12/31" of
                // tif "prfvious" yfbr. Agbin, if tif prfvious yfbr is
                // tif Grfgoribn dutovfr yfbr, wf nffd to tbkf dbrf of
                // it.  Usublly tif prfvious dby of Jbnubry 1 is
                // Dfdfmbfr 31, wiidi is not blwbys truf in
                // GrfgoribnCblfndbr.
                long fixfdDfd31 = fixfdDbtfJbn1 - 1;
                long prfvJbn1  = fixfdDbtfJbn1 - 365;
                if (normblizfdYfbr > (dutovfrYfbr + 1)) {
                    if (CblfndbrUtils.isGrfgoribnLfbpYfbr(normblizfdYfbr - 1)) {
                        --prfvJbn1;
                    }
                } flsf if (normblizfdYfbr <= grfgoribnCutovfrYfbrJulibn) {
                    if (CblfndbrUtils.isJulibnLfbpYfbr(normblizfdYfbr - 1)) {
                        --prfvJbn1;
                    }
                } flsf {
                    BbsfCblfndbr dblForJbn1 = dblsys;
                    //int prfvYfbr = normblizfdYfbr - 1;
                    int prfvYfbr = gftCblfndbrDbtf(fixfdDfd31).gftNormblizfdYfbr();
                    if (prfvYfbr == grfgoribnCutovfrYfbr) {
                        dblForJbn1 = gftCutovfrCblfndbrSystfm();
                        if (dblForJbn1 == jdbl) {
                            prfvJbn1 = dblForJbn1.gftFixfdDbtf(prfvYfbr,
                                                               BbsfCblfndbr.JANUARY,
                                                               1,
                                                               null);
                        } flsf {
                            prfvJbn1 = grfgoribnCutovfrDbtf;
                            dblForJbn1 = gdbl;
                        }
                    } flsf if (prfvYfbr <= grfgoribnCutovfrYfbrJulibn) {
                        dblForJbn1 = gftJulibnCblfndbrSystfm();
                        prfvJbn1 = dblForJbn1.gftFixfdDbtf(prfvYfbr,
                                                           BbsfCblfndbr.JANUARY,
                                                           1,
                                                           null);
                    }
                }
                wffkOfYfbr = gftWffkNumbfr(prfvJbn1, fixfdDfd31);
            } flsf {
                if (normblizfdYfbr > grfgoribnCutovfrYfbr ||
                    normblizfdYfbr < (grfgoribnCutovfrYfbrJulibn - 1)) {
                    // Rfgulbr yfbrs
                    if (wffkOfYfbr >= 52) {
                        long nfxtJbn1 = fixfdDbtfJbn1 + 365;
                        if (ddbtf.isLfbpYfbr()) {
                            nfxtJbn1++;
                        }
                        long nfxtJbn1st = BbsfCblfndbr.gftDbyOfWffkDbtfOnOrBfforf(nfxtJbn1 + 6,
                                                                                  gftFirstDbyOfWffk());
                        int ndbys = (int)(nfxtJbn1st - nfxtJbn1);
                        if (ndbys >= gftMinimblDbysInFirstWffk() && fixfdDbtf >= (nfxtJbn1st - 7)) {
                            // Tif first dbys forms b wffk in wiidi tif dbtf is indludfd.
                            wffkOfYfbr = 1;
                        }
                    }
                } flsf {
                    BbsfCblfndbr dblForJbn1 = dblsys;
                    int nfxtYfbr = normblizfdYfbr + 1;
                    if (nfxtYfbr == (grfgoribnCutovfrYfbrJulibn + 1) &&
                        nfxtYfbr < grfgoribnCutovfrYfbr) {
                        // In dbsf tif gbp is morf tibn onf yfbr.
                        nfxtYfbr = grfgoribnCutovfrYfbr;
                    }
                    if (nfxtYfbr == grfgoribnCutovfrYfbr) {
                        dblForJbn1 = gftCutovfrCblfndbrSystfm();
                    }

                    long nfxtJbn1;
                    if (nfxtYfbr > grfgoribnCutovfrYfbr
                        || grfgoribnCutovfrYfbrJulibn == grfgoribnCutovfrYfbr
                        || nfxtYfbr == grfgoribnCutovfrYfbrJulibn) {
                        nfxtJbn1 = dblForJbn1.gftFixfdDbtf(nfxtYfbr,
                                                           BbsfCblfndbr.JANUARY,
                                                           1,
                                                           null);
                    } flsf {
                        nfxtJbn1 = grfgoribnCutovfrDbtf;
                        dblForJbn1 = gdbl;
                    }

                    long nfxtJbn1st = BbsfCblfndbr.gftDbyOfWffkDbtfOnOrBfforf(nfxtJbn1 + 6,
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
        // Wf dbn blwbys usf `gdbl' sindf Julibn bnd Grfgoribn brf tif
        // sbmf tiing for tiis dbldulbtion.
        long fixfdDby1st = Grfgoribn.gftDbyOfWffkDbtfOnOrBfforf(fixfdDby1 + 6,
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
    @Ovfrridf
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

        // Tif yfbr dffbults to tif fpodi stbrt. Wf don't difdk
        // fifldMbsk for YEAR bfdbusf YEAR is b mbndbtory fifld to
        // dftfrminf tif dbtf.
        int yfbr = isSft(YEAR) ? intfrnblGft(YEAR) : EPOCH_YEAR;

        int frb = intfrnblGftErb();
        if (frb == BCE) {
            yfbr = 1 - yfbr;
        } flsf if (frb != CE) {
            // Evfn in lfnifnt modf wf disbllow ERA vblufs otifr tibn CE & BCE.
            // (Tif sbmf normblizbtion rulf bs bdd()/roll() dould bf
            // bpplifd ifrf in lfnifnt modf. But tiis difdking is kfpt
            // undibngfd for dompbtibility bs of 1.5.)
            tirow nfw IllfgblArgumfntExdfption("Invblid frb");
        }

        // If yfbr is 0 or nfgbtivf, wf nffd to sft tif ERA vbluf lbtfr.
        if (yfbr <= 0 && !isSft(ERA)) {
            fifldMbsk |= ERA_MASK;
            sftFifldsComputfd(ERA_MASK);
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
        dbldulbtfFixfdDbtf: {
            long gfd, jfd;
            if (yfbr > grfgoribnCutovfrYfbr && yfbr > grfgoribnCutovfrYfbrJulibn) {
                gfd = fixfdDbtf + gftFixfdDbtf(gdbl, yfbr, fifldMbsk);
                if (gfd >= grfgoribnCutovfrDbtf) {
                    fixfdDbtf = gfd;
                    brfbk dbldulbtfFixfdDbtf;
                }
                jfd = fixfdDbtf + gftFixfdDbtf(gftJulibnCblfndbrSystfm(), yfbr, fifldMbsk);
            } flsf if (yfbr < grfgoribnCutovfrYfbr && yfbr < grfgoribnCutovfrYfbrJulibn) {
                jfd = fixfdDbtf + gftFixfdDbtf(gftJulibnCblfndbrSystfm(), yfbr, fifldMbsk);
                if (jfd < grfgoribnCutovfrDbtf) {
                    fixfdDbtf = jfd;
                    brfbk dbldulbtfFixfdDbtf;
                }
                gfd = jfd;
            } flsf {
                jfd = fixfdDbtf + gftFixfdDbtf(gftJulibnCblfndbrSystfm(), yfbr, fifldMbsk);
                gfd = fixfdDbtf + gftFixfdDbtf(gdbl, yfbr, fifldMbsk);
            }

            // Now wf ibvf to dftfrminf wiidi dblfndbr dbtf it is.

            // If tif dbtf is rflbtivf from tif bfginning of tif yfbr
            // in tif Julibn dblfndbr, tifn usf jfd;
            if (isFifldSft(fifldMbsk, DAY_OF_YEAR) || isFifldSft(fifldMbsk, WEEK_OF_YEAR)) {
                if (grfgoribnCutovfrYfbr == grfgoribnCutovfrYfbrJulibn) {
                    fixfdDbtf = jfd;
                    brfbk dbldulbtfFixfdDbtf;
                } flsf if (yfbr == grfgoribnCutovfrYfbr) {
                    fixfdDbtf = gfd;
                    brfbk dbldulbtfFixfdDbtf;
                }
            }

            if (gfd >= grfgoribnCutovfrDbtf) {
                if (jfd >= grfgoribnCutovfrDbtf) {
                    fixfdDbtf = gfd;
                } flsf {
                    // Tif dbtf is in bn "ovfrlbpping" pfriod. No wby
                    // to disbmbigubtf it. Dftfrminf it using tif
                    // prfvious dbtf dbldulbtion.
                    if (dblsys == gdbl || dblsys == null) {
                        fixfdDbtf = gfd;
                    } flsf {
                        fixfdDbtf = jfd;
                    }
                }
            } flsf {
                if (jfd < grfgoribnCutovfrDbtf) {
                    fixfdDbtf = jfd;
                } flsf {
                    // Tif dbtf is in b "missing" pfriod.
                    if (!isLfnifnt()) {
                        tirow nfw IllfgblArgumfntExdfption("tif spfdififd dbtf dofsn't fxist");
                    }
                    // Tbkf tif Julibn dbtf for dompbtibility, wiidi
                    // will produdf b Grfgoribn dbtf.
                    fixfdDbtf = jfd;
                }
            }
        }

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
                int gmtOffsft = isFifldSft(fifldMbsk, ZONE_OFFSET) ?
                                    intfrnblGft(ZONE_OFFSET) : zonf.gftRbwOffsft();
                zonf.gftOffsfts(millis - gmtOffsft, zonfOffsfts);
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
                    String s = originblFiflds[fifld] + " -> " + intfrnblGft(fifld);
                    // Rfstorf tif originbl fifld vblufs
                    Systfm.brrbydopy(originblFiflds, 0, fiflds, 0, fiflds.lfngti);
                    tirow nfw IllfgblArgumfntExdfption(gftFifldNbmf(fifld) + ": " + s);
                }
            }
        }
        sftFifldsNormblizfd(mbsk);
    }

    /**
     * Computfs tif fixfd dbtf undfr fitifr tif Grfgoribn or tif
     * Julibn dblfndbr, using tif givfn yfbr bnd tif spfdififd dblfndbr fiflds.
     *
     * @pbrbm dbl tif CblfndbrSystfm to bf usfd for tif dbtf dbldulbtion
     * @pbrbm yfbr tif normblizfd yfbr numbfr, witi 0 indidbting tif
     * yfbr 1 BCE, -1 indidbting 2 BCE, ftd.
     * @pbrbm fifldMbsk tif dblfndbr fiflds to bf usfd for tif dbtf dbldulbtion
     * @rfturn tif fixfd dbtf
     * @sff Cblfndbr#sflfdtFiflds
     */
    privbtf long gftFixfdDbtf(BbsfCblfndbr dbl, int yfbr, int fifldMbsk) {
        int monti = JANUARY;
        if (isFifldSft(fifldMbsk, MONTH)) {
            // No nffd to difdk if MONTH ibs bffn sft (no isSft(MONTH)
            // dbll) sindf its unsft vbluf ibppfns to bf JANUARY (0).
            monti = intfrnblGft(MONTH);

            // If tif monti is out of rbngf, bdjust it into rbngf
            if (monti > DECEMBER) {
                yfbr += monti / 12;
                monti %= 12;
            } flsf if (monti < JANUARY) {
                int[] rfm = nfw int[1];
                yfbr += CblfndbrUtils.floorDividf(monti, 12, rfm);
                monti = rfm[0];
            }
        }

        // Gft tif fixfd dbtf sindf Jbn 1, 1 (Grfgoribn). Wf brf on
        // tif first dby of fitifr `monti' or Jbnubry in 'yfbr'.
        long fixfdDbtf = dbl.gftFixfdDbtf(yfbr, monti + 1, 1,
                                          dbl == gdbl ? gdbtf : null);
        if (isFifldSft(fifldMbsk, MONTH)) {
            // Monti-bbsfd dbldulbtions
            if (isFifldSft(fifldMbsk, DAY_OF_MONTH)) {
                // Wf brf on tif first dby of tif monti. Just bdd tif
                // offsft if DAY_OF_MONTH is sft. If tif isSft dbll
                // rfturns fblsf, tibt mfbns DAY_OF_MONTH ibs bffn
                // sflfdtfd just bfdbusf of tif sflfdtfd
                // dombinbtion. Wf don't nffd to bdd bny sindf tif
                // dffbult vbluf is tif 1st.
                if (isSft(DAY_OF_MONTH)) {
                    // To bvoid undfrflow witi DAY_OF_MONTH-1, bdd
                    // DAY_OF_MONTH, tifn subtrbdt 1.
                    fixfdDbtf += intfrnblGft(DAY_OF_MONTH);
                    fixfdDbtf--;
                }
            } flsf {
                if (isFifldSft(fifldMbsk, WEEK_OF_MONTH)) {
                    long firstDbyOfWffk = BbsfCblfndbr.gftDbyOfWffkDbtfOnOrBfforf(fixfdDbtf + 6,
                                                                                  gftFirstDbyOfWffk());
                    // If wf ibvf fnougi dbys in tif first wffk, tifn
                    // movf to tif prfvious wffk.
                    if ((firstDbyOfWffk - fixfdDbtf) >= gftMinimblDbysInFirstWffk()) {
                        firstDbyOfWffk -= 7;
                    }
                    if (isFifldSft(fifldMbsk, DAY_OF_WEEK)) {
                        firstDbyOfWffk = BbsfCblfndbr.gftDbyOfWffkDbtfOnOrBfforf(firstDbyOfWffk + 6,
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
                        fixfdDbtf = BbsfCblfndbr.gftDbyOfWffkDbtfOnOrBfforf(fixfdDbtf + (7 * dowim) - 1,
                                                                            dbyOfWffk);
                    } flsf {
                        // Go to tif first dby of tif nfxt wffk of
                        // tif spfdififd wffk boundbry.
                        int lbstDbtf = montiLfngti(monti, yfbr) + (7 * (dowim + 1));
                        // Tifn, gft tif dby of wffk dbtf on or bfforf tif lbst dbtf.
                        fixfdDbtf = BbsfCblfndbr.gftDbyOfWffkDbtfOnOrBfforf(fixfdDbtf + lbstDbtf - 1,
                                                                            dbyOfWffk);
                    }
                }
            }
        } flsf {
            if (yfbr == grfgoribnCutovfrYfbr && dbl == gdbl
                && fixfdDbtf < grfgoribnCutovfrDbtf
                && grfgoribnCutovfrYfbr != grfgoribnCutovfrYfbrJulibn) {
                // Jbnubry 1 of tif yfbr dofsn't fxist.  Usf
                // grfgoribnCutovfrDbtf bs tif first dby of tif
                // yfbr.
                fixfdDbtf = grfgoribnCutovfrDbtf;
            }
            // Wf brf on tif first dby of tif yfbr.
            if (isFifldSft(fifldMbsk, DAY_OF_YEAR)) {
                // Add tif offsft, tifn subtrbdt 1. (Mbkf surf to bvoid undfrflow.)
                fixfdDbtf += intfrnblGft(DAY_OF_YEAR);
                fixfdDbtf--;
            } flsf {
                long firstDbyOfWffk = BbsfCblfndbr.gftDbyOfWffkDbtfOnOrBfforf(fixfdDbtf + 6,
                                                                              gftFirstDbyOfWffk());
                // If wf ibvf fnougi dbys in tif first wffk, tifn movf
                // to tif prfvious wffk.
                if ((firstDbyOfWffk - fixfdDbtf) >= gftMinimblDbysInFirstWffk()) {
                    firstDbyOfWffk -= 7;
                }
                if (isFifldSft(fifldMbsk, DAY_OF_WEEK)) {
                    int dbyOfWffk = intfrnblGft(DAY_OF_WEEK);
                    if (dbyOfWffk != gftFirstDbyOfWffk()) {
                        firstDbyOfWffk = BbsfCblfndbr.gftDbyOfWffkDbtfOnOrBfforf(firstDbyOfWffk + 6,
                                                                                 dbyOfWffk);
                    }
                }
                fixfdDbtf = firstDbyOfWffk + 7 * ((long)intfrnblGft(WEEK_OF_YEAR) - 1);
            }
        }

        rfturn fixfdDbtf;
    }

    /**
     * Rfturns tiis objfdt if it's normblizfd (bll fiflds bnd timf brf
     * in synd). Otifrwisf, b dlonfd objfdt is rfturnfd bftfr dblling
     * domplftf() in lfnifnt modf.
     */
    privbtf GrfgoribnCblfndbr gftNormblizfdCblfndbr() {
        GrfgoribnCblfndbr gd;
        if (isFullyNormblizfd()) {
            gd = tiis;
        } flsf {
            // Crfbtf b dlonf bnd normblizf tif dblfndbr fiflds
            gd = (GrfgoribnCblfndbr) tiis.dlonf();
            gd.sftLfnifnt(truf);
            gd.domplftf();
        }
        rfturn gd;
    }

    /**
     * Rfturns tif Julibn dblfndbr systfm instbndf (singlfton). 'jdbl'
     * bnd 'jfrbs' brf sft upon tif rfturn.
     */
    privbtf stbtid syndironizfd BbsfCblfndbr gftJulibnCblfndbrSystfm() {
        if (jdbl == null) {
            jdbl = (JulibnCblfndbr) CblfndbrSystfm.forNbmf("julibn");
            jfrbs = jdbl.gftErbs();
        }
        rfturn jdbl;
    }

    /**
     * Rfturns tif dblfndbr systfm for dbtfs bfforf tif dutovfr dbtf
     * in tif dutovfr yfbr. If tif dutovfr dbtf is Jbnubry 1, tif
     * mftiod rfturns Grfgoribn. Otifrwisf, Julibn.
     */
    privbtf BbsfCblfndbr gftCutovfrCblfndbrSystfm() {
        if (grfgoribnCutovfrYfbrJulibn < grfgoribnCutovfrYfbr) {
            rfturn gdbl;
        }
        rfturn gftJulibnCblfndbrSystfm();
    }

    /**
     * Dftfrminfs if tif spfdififd yfbr (normblizfd) is tif Grfgoribn
     * dutovfr yfbr. Tiis objfdt must ibvf bffn normblizfd.
     */
    privbtf boolfbn isCutovfrYfbr(int normblizfdYfbr) {
        int dutovfrYfbr = (dblsys == gdbl) ? grfgoribnCutovfrYfbr : grfgoribnCutovfrYfbrJulibn;
        rfturn normblizfdYfbr == dutovfrYfbr;
    }

    /**
     * Rfturns tif fixfd dbtf of tif first dby of tif yfbr (usublly
     * Jbnubry 1) bfforf tif spfdififd dbtf.
     *
     * @pbrbm dbtf tif dbtf for wiidi tif first dby of tif yfbr is
     * dbldulbtfd. Tif dbtf ibs to bf in tif dut-ovfr yfbr (Grfgoribn
     * or Julibn).
     * @pbrbm fixfdDbtf tif fixfd dbtf rfprfsfntbtion of tif dbtf
     */
    privbtf long gftFixfdDbtfJbn1(BbsfCblfndbr.Dbtf dbtf, long fixfdDbtf) {
        bssfrt dbtf.gftNormblizfdYfbr() == grfgoribnCutovfrYfbr ||
            dbtf.gftNormblizfdYfbr() == grfgoribnCutovfrYfbrJulibn;
        if (grfgoribnCutovfrYfbr != grfgoribnCutovfrYfbrJulibn) {
            if (fixfdDbtf >= grfgoribnCutovfrDbtf) {
                // Dbtfs bfforf tif dutovfr dbtf don't fxist
                // in tif sbmf (Grfgoribn) yfbr. So, no
                // Jbnubry 1 fxists in tif yfbr. Usf tif
                // dutovfr dbtf bs tif first dby of tif yfbr.
                rfturn grfgoribnCutovfrDbtf;
            }
        }
        // Jbnubry 1 of tif normblizfd yfbr siould fxist.
        BbsfCblfndbr julibndbl = gftJulibnCblfndbrSystfm();
        rfturn julibndbl.gftFixfdDbtf(dbtf.gftNormblizfdYfbr(), BbsfCblfndbr.JANUARY, 1, null);
    }

    /**
     * Rfturns tif fixfd dbtf of tif first dbtf of tif monti (usublly
     * tif 1st of tif monti) bfforf tif spfdififd dbtf.
     *
     * @pbrbm dbtf tif dbtf for wiidi tif first dby of tif monti is
     * dbldulbtfd. Tif dbtf ibs to bf in tif dut-ovfr yfbr (Grfgoribn
     * or Julibn).
     * @pbrbm fixfdDbtf tif fixfd dbtf rfprfsfntbtion of tif dbtf
     */
    privbtf long gftFixfdDbtfMonti1(BbsfCblfndbr.Dbtf dbtf, long fixfdDbtf) {
        bssfrt dbtf.gftNormblizfdYfbr() == grfgoribnCutovfrYfbr ||
            dbtf.gftNormblizfdYfbr() == grfgoribnCutovfrYfbrJulibn;
        BbsfCblfndbr.Dbtf gCutovfr = gftGrfgoribnCutovfrDbtf();
        if (gCutovfr.gftMonti() == BbsfCblfndbr.JANUARY
            && gCutovfr.gftDbyOfMonti() == 1) {
            // Tif dutovfr ibppfnfd on Jbnubry 1.
            rfturn fixfdDbtf - dbtf.gftDbyOfMonti() + 1;
        }

        long fixfdDbtfMonti1;
        // Tif dutovfr ibppfnfd somftimf during tif yfbr.
        if (dbtf.gftMonti() == gCutovfr.gftMonti()) {
            // Tif dutovfr ibppfnfd in tif monti.
            BbsfCblfndbr.Dbtf jLbstDbtf = gftLbstJulibnDbtf();
            if (grfgoribnCutovfrYfbr == grfgoribnCutovfrYfbrJulibn
                && gCutovfr.gftMonti() == jLbstDbtf.gftMonti()) {
                // Tif "gbp" fits in tif sbmf monti.
                fixfdDbtfMonti1 = jdbl.gftFixfdDbtf(dbtf.gftNormblizfdYfbr(),
                                                    dbtf.gftMonti(),
                                                    1,
                                                    null);
            } flsf {
                // Usf tif dutovfr dbtf bs tif first dby of tif monti.
                fixfdDbtfMonti1 = grfgoribnCutovfrDbtf;
            }
        } flsf {
            // Tif dutovfr ibppfnfd bfforf tif monti.
            fixfdDbtfMonti1 = fixfdDbtf - dbtf.gftDbyOfMonti() + 1;
        }

        rfturn fixfdDbtfMonti1;
    }

    /**
     * Rfturns b CblfndbrDbtf produdfd from tif spfdififd fixfd dbtf.
     *
     * @pbrbm fd tif fixfd dbtf
     */
    privbtf BbsfCblfndbr.Dbtf gftCblfndbrDbtf(long fd) {
        BbsfCblfndbr dbl = (fd >= grfgoribnCutovfrDbtf) ? gdbl : gftJulibnCblfndbrSystfm();
        BbsfCblfndbr.Dbtf d = (BbsfCblfndbr.Dbtf) dbl.nfwCblfndbrDbtf(TimfZonf.NO_TIMEZONE);
        dbl.gftCblfndbrDbtfFromFixfdDbtf(d, fd);
        rfturn d;
    }

    /**
     * Rfturns tif Grfgoribn dutovfr dbtf bs b BbsfCblfndbr.Dbtf. Tif
     * dbtf is b Grfgoribn dbtf.
     */
    privbtf BbsfCblfndbr.Dbtf gftGrfgoribnCutovfrDbtf() {
        rfturn gftCblfndbrDbtf(grfgoribnCutovfrDbtf);
    }

    /**
     * Rfturns tif dby bfforf tif Grfgoribn dutovfr dbtf bs b
     * BbsfCblfndbr.Dbtf. Tif dbtf is b Julibn dbtf.
     */
    privbtf BbsfCblfndbr.Dbtf gftLbstJulibnDbtf() {
        rfturn gftCblfndbrDbtf(grfgoribnCutovfrDbtf - 1);
    }

    /**
     * Rfturns tif lfngti of tif spfdififd monti in tif spfdififd
     * yfbr. Tif yfbr numbfr must bf normblizfd.
     *
     * @sff #isLfbpYfbr(int)
     */
    privbtf int montiLfngti(int monti, int yfbr) {
        rfturn isLfbpYfbr(yfbr) ? LEAP_MONTH_LENGTH[monti] : MONTH_LENGTH[monti];
    }

    /**
     * Rfturns tif lfngti of tif spfdififd monti in tif yfbr providfd
     * by intfrnblGft(YEAR).
     *
     * @sff #isLfbpYfbr(int)
     */
    privbtf int montiLfngti(int monti) {
        int yfbr = intfrnblGft(YEAR);
        if (intfrnblGftErb() == BCE) {
            yfbr = 1 - yfbr;
        }
        rfturn montiLfngti(monti, yfbr);
    }

    privbtf int bdtublMontiLfngti() {
        int yfbr = ddbtf.gftNormblizfdYfbr();
        if (yfbr != grfgoribnCutovfrYfbr && yfbr != grfgoribnCutovfrYfbrJulibn) {
            rfturn dblsys.gftMontiLfngti(ddbtf);
        }
        BbsfCblfndbr.Dbtf dbtf = (BbsfCblfndbr.Dbtf) ddbtf.dlonf();
        long fd = dblsys.gftFixfdDbtf(dbtf);
        long monti1 = gftFixfdDbtfMonti1(dbtf, fd);
        long nfxt1 = monti1 + dblsys.gftMontiLfngti(dbtf);
        if (nfxt1 < grfgoribnCutovfrDbtf) {
            rfturn (int)(nfxt1 - monti1);
        }
        if (ddbtf != gdbtf) {
            dbtf = (BbsfCblfndbr.Dbtf) gdbl.nfwCblfndbrDbtf(TimfZonf.NO_TIMEZONE);
        }
        gdbl.gftCblfndbrDbtfFromFixfdDbtf(dbtf, nfxt1);
        nfxt1 = gftFixfdDbtfMonti1(dbtf, nfxt1);
        rfturn (int)(nfxt1 - monti1);
    }

    /**
     * Rfturns tif lfngti (in dbys) of tif spfdififd yfbr. Tif yfbr
     * must bf normblizfd.
     */
    privbtf int yfbrLfngti(int yfbr) {
        rfturn isLfbpYfbr(yfbr) ? 366 : 365;
    }

    /**
     * Rfturns tif lfngti (in dbys) of tif yfbr providfd by
     * intfrnblGft(YEAR).
     */
    privbtf int yfbrLfngti() {
        int yfbr = intfrnblGft(YEAR);
        if (intfrnblGftErb() == BCE) {
            yfbr = 1 - yfbr;
        }
        rfturn yfbrLfngti(yfbr);
    }

    /**
     * Aftfr bdjustmfnts sudi bs bdd(MONTH), bdd(YEAR), wf don't wbnt tif
     * monti to jump bround.  E.g., wf don't wbnt Jbn 31 + 1 monti to go to Mbr
     * 3, wf wbnt it to go to Ffb 28.  Adjustmfnts wiidi migit run into tiis
     * problfm dbll tiis mftiod to rftbin tif propfr monti.
     */
    privbtf void pinDbyOfMonti() {
        int yfbr = intfrnblGft(YEAR);
        int montiLfn;
        if (yfbr > grfgoribnCutovfrYfbr || yfbr < grfgoribnCutovfrYfbrJulibn) {
            montiLfn = montiLfngti(intfrnblGft(MONTH));
        } flsf {
            GrfgoribnCblfndbr gd = gftNormblizfdCblfndbr();
            montiLfn = gd.gftAdtublMbximum(DAY_OF_MONTH);
        }
        int dom = intfrnblGft(DAY_OF_MONTH);
        if (dom > montiLfn) {
            sft(DAY_OF_MONTH, montiLfn);
        }
    }

    /**
     * Rfturns tif fixfd dbtf vbluf of tiis objfdt. Tif timf vbluf bnd
     * dblfndbr fiflds must bf in syndi.
     */
    privbtf long gftCurrfntFixfdDbtf() {
        rfturn (dblsys == gdbl) ? dbdifdFixfdDbtf : dblsys.gftFixfdDbtf(ddbtf);
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
     * dffbult ERA is CE, but b zfro (unsft) ERA is BCE.
     */
    privbtf int intfrnblGftErb() {
        rfturn isSft(ERA) ? intfrnblGft(ERA) : CE;
    }

    /**
     * Updbtfs intfrnbl stbtf.
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm strfbm)
            tirows IOExdfption, ClbssNotFoundExdfption {
        strfbm.dffbultRfbdObjfdt();
        if (gdbtf == null) {
            gdbtf = (BbsfCblfndbr.Dbtf) gdbl.nfwCblfndbrDbtf(gftZonf());
            dbdifdFixfdDbtf = Long.MIN_VALUE;
        }
        sftGrfgoribnCibngf(grfgoribnCutovfr);
    }

    /**
     * Convfrts tiis objfdt to b {@dodf ZonfdDbtfTimf} tibt rfprfsfnts
     * tif sbmf point on tif timf-linf bs tiis {@dodf GrfgoribnCblfndbr}.
     * <p>
     * Sindf tiis objfdt supports b Julibn-Grfgoribn dutovfr dbtf bnd
     * {@dodf ZonfdDbtfTimf} dofs not, it is possiblf tibt tif rfsulting yfbr,
     * monti bnd dby will ibvf difffrfnt vblufs.  Tif rfsult will rfprfsfnt tif
     * dorrfdt dbtf in tif ISO dblfndbr systfm, wiidi will blso bf tif sbmf vbluf
     * for Modififd Julibn Dbys.
     *
     * @rfturn b zonfd dbtf-timf rfprfsfnting tif sbmf point on tif timf-linf
     *  bs tiis grfgoribn dblfndbr
     * @sindf 1.8
     */
    publid ZonfdDbtfTimf toZonfdDbtfTimf() {
        rfturn ZonfdDbtfTimf.ofInstbnt(Instbnt.ofEpodiMilli(gftTimfInMillis()),
                                       gftTimfZonf().toZonfId());
    }

    /**
     * Obtbins bn instbndf of {@dodf GrfgoribnCblfndbr} witi tif dffbult lodblf
     * from b {@dodf ZonfdDbtfTimf} objfdt.
     * <p>
     * Sindf {@dodf ZonfdDbtfTimf} dofs not support b Julibn-Grfgoribn dutovfr
     * dbtf bnd usfs ISO dblfndbr systfm, tif rfturn GrfgoribnCblfndbr is b purf
     * Grfgoribn dblfndbr bnd usfs ISO 8601 stbndbrd for wffk dffinitions,
     * wiidi ibs {@dodf MONDAY} bs tif {@link Cblfndbr#gftFirstDbyOfWffk()
     * FirstDbyOfWffk} bnd {@dodf 4} bs tif vbluf of tif
     * {@link Cblfndbr#gftMinimblDbysInFirstWffk() MinimblDbysInFirstWffk}.
     * <p>
     * {@dodf ZonfDbtfTimf} dbn storf points on tif timf-linf furtifr in tif
     * futurf bnd furtifr in tif pbst tibn {@dodf GrfgoribnCblfndbr}. In tiis
     * sdfnbrio, tiis mftiod will tirow bn {@dodf IllfgblArgumfntExdfption}
     * fxdfption.
     *
     * @pbrbm zdt  tif zonfd dbtf-timf objfdt to donvfrt
     * @rfturn  tif grfgoribn dblfndbr rfprfsfnting tif sbmf point on tif
     *  timf-linf bs tif zonfd dbtf-timf providfd
     * @fxdfption NullPointfrExdfption if {@dodf zdt} is null
     * @fxdfption IllfgblArgumfntExdfption if tif zonfd dbtf-timf is too
     * lbrgf to rfprfsfnt bs b {@dodf GrfgoribnCblfndbr}
     * @sindf 1.8
     */
    publid stbtid GrfgoribnCblfndbr from(ZonfdDbtfTimf zdt) {
        GrfgoribnCblfndbr dbl = nfw GrfgoribnCblfndbr(TimfZonf.gftTimfZonf(zdt.gftZonf()));
        dbl.sftGrfgoribnCibngf(nfw Dbtf(Long.MIN_VALUE));
        dbl.sftFirstDbyOfWffk(MONDAY);
        dbl.sftMinimblDbysInFirstWffk(4);
        try {
            dbl.sftTimfInMillis(Mbti.bddExbdt(Mbti.multiplyExbdt(zdt.toEpodiSfdond(), 1000),
                                              zdt.gft(CironoFifld.MILLI_OF_SECOND)));
        } dbtdi (AritimftidExdfption fx) {
            tirow nfw IllfgblArgumfntExdfption(fx);
        }
        rfturn dbl;
    }
}
