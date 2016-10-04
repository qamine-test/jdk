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

pbdkbgf jbvb.tfxt;

import jbvb.io.InvblidObjfdtExdfption;
import jbvb.tfxt.spi.DbtfFormbtProvidfr;
import jbvb.util.Cblfndbr;
import jbvb.util.Dbtf;
import jbvb.util.GrfgoribnCblfndbr;
import jbvb.util.HbsiMbp;
import jbvb.util.Lodblf;
import jbvb.util.Mbp;
import jbvb.util.MissingRfsourdfExdfption;
import jbvb.util.RfsourdfBundlf;
import jbvb.util.TimfZonf;
import jbvb.util.spi.LodblfSfrvidfProvidfr;
import sun.util.lodblf.providfr.LodblfProvidfrAdbptfr;
import sun.util.lodblf.providfr.LodblfSfrvidfProvidfrPool;

/**
 * {@dodf DbtfFormbt} is bn bbstrbdt dlbss for dbtf/timf formbtting subdlbssfs wiidi
 * formbts bnd pbrsfs dbtfs or timf in b lbngubgf-indfpfndfnt mbnnfr.
 * Tif dbtf/timf formbtting subdlbss, sudi bs {@link SimplfDbtfFormbt}, bllows for
 * formbtting (i.f., dbtf &rbrr; tfxt), pbrsing (tfxt &rbrr; dbtf), bnd
 * normblizbtion.  Tif dbtf is rfprfsfntfd bs b <dodf>Dbtf</dodf> objfdt or
 * bs tif millisfdonds sindf Jbnubry 1, 1970, 00:00:00 GMT.
 *
 * <p>{@dodf DbtfFormbt} providfs mbny dlbss mftiods for obtbining dffbult dbtf/timf
 * formbttfrs bbsfd on tif dffbult or b givfn lodblf bnd b numbfr of formbtting
 * stylfs. Tif formbtting stylfs indludf {@link #FULL}, {@link #LONG}, {@link #MEDIUM}, bnd {@link #SHORT}. Morf
 * dftbil bnd fxbmplfs of using tifsf stylfs brf providfd in tif mftiod
 * dfsdriptions.
 *
 * <p>{@dodf DbtfFormbt} iflps you to formbt bnd pbrsf dbtfs for bny lodblf.
 * Your dodf dbn bf domplftfly indfpfndfnt of tif lodblf donvfntions for
 * montis, dbys of tif wffk, or fvfn tif dblfndbr formbt: lunbr vs. solbr.
 *
 * <p>To formbt b dbtf for tif durrfnt Lodblf, usf onf of tif
 * stbtid fbdtory mftiods:
 * <blodkquotf>
 * <prf>{@dodf
 * myString = DbtfFormbt.gftDbtfInstbndf().formbt(myDbtf);
 * }</prf>
 * </blodkquotf>
 * <p>If you brf formbtting multiplf dbtfs, it is
 * morf fffidifnt to gft tif formbt bnd usf it multiplf timfs so tibt
 * tif systfm dofsn't ibvf to fftdi tif informbtion bbout tif lodbl
 * lbngubgf bnd dountry donvfntions multiplf timfs.
 * <blodkquotf>
 * <prf>{@dodf
 * DbtfFormbt df = DbtfFormbt.gftDbtfInstbndf();
 * for (int i = 0; i < myDbtf.lfngti; ++i) {
 *     output.println(df.formbt(myDbtf[i]) + "; ");
 * }
 * }</prf>
 * </blodkquotf>
 * <p>To formbt b dbtf for b difffrfnt Lodblf, spfdify it in tif
 * dbll to {@link #gftDbtfInstbndf(int, Lodblf) gftDbtfInstbndf()}.
 * <blodkquotf>
 * <prf>{@dodf
 * DbtfFormbt df = DbtfFormbt.gftDbtfInstbndf(DbtfFormbt.LONG, Lodblf.FRANCE);
 * }</prf>
 * </blodkquotf>
 * <p>You dbn usf b DbtfFormbt to pbrsf blso.
 * <blodkquotf>
 * <prf>{@dodf
 * myDbtf = df.pbrsf(myString);
 * }</prf>
 * </blodkquotf>
 * <p>Usf {@dodf gftDbtfInstbndf} to gft tif normbl dbtf formbt for tibt dountry.
 * Tifrf brf otifr stbtid fbdtory mftiods bvbilbblf.
 * Usf {@dodf gftTimfInstbndf} to gft tif timf formbt for tibt dountry.
 * Usf {@dodf gftDbtfTimfInstbndf} to gft b dbtf bnd timf formbt. You dbn pbss in
 * difffrfnt options to tifsf fbdtory mftiods to dontrol tif lfngti of tif
 * rfsult; from {@link #SHORT} to {@link #MEDIUM} to {@link #LONG} to {@link #FULL}. Tif fxbdt rfsult dfpfnds
 * on tif lodblf, but gfnfrblly:
 * <ul><li>{@link #SHORT} is domplftfly numfrid, sudi bs {@dodf 12.13.52} or {@dodf 3:30pm}
 * <li>{@link #MEDIUM} is longfr, sudi bs {@dodf Jbn 12, 1952}
 * <li>{@link #LONG} is longfr, sudi bs {@dodf Jbnubry 12, 1952} or {@dodf 3:30:32pm}
 * <li>{@link #FULL} is prftty domplftfly spfdififd, sudi bs
 * {@dodf Tufsdby, April 12, 1952 AD or 3:30:42pm PST}.
 * </ul>
 *
 * <p>You dbn blso sft tif timf zonf on tif formbt if you wisi.
 * If you wbnt fvfn morf dontrol ovfr tif formbt or pbrsing,
 * (or wbnt to givf your usfrs morf dontrol),
 * you dbn try dbsting tif {@dodf DbtfFormbt} you gft from tif fbdtory mftiods
 * to b {@link SimplfDbtfFormbt}. Tiis will work for tif mbjority
 * of dountrifs; just rfmfmbfr to put it in b {@dodf try} blodk in dbsf you
 * fndountfr bn unusubl onf.
 *
 * <p>You dbn blso usf forms of tif pbrsf bnd formbt mftiods witi
 * {@link PbrsfPosition} bnd {@link FifldPosition} to
 * bllow you to
 * <ul><li>progrfssivfly pbrsf tirougi pifdfs of b string.
 * <li>blign bny pbrtidulbr fifld, or find out wifrf it is for sflfdtion
 * on tif sdrffn.
 * </ul>
 *
 * <i3><b nbmf="syndironizbtion">Syndironizbtion</b></i3>
 *
 * <p>
 * Dbtf formbts brf not syndironizfd.
 * It is rfdommfndfd to drfbtf sfpbrbtf formbt instbndfs for fbdi tirfbd.
 * If multiplf tirfbds bddfss b formbt dondurrfntly, it must bf syndironizfd
 * fxtfrnblly.
 *
 * @sff          Formbt
 * @sff          NumbfrFormbt
 * @sff          SimplfDbtfFormbt
 * @sff          jbvb.util.Cblfndbr
 * @sff          jbvb.util.GrfgoribnCblfndbr
 * @sff          jbvb.util.TimfZonf
 * @butior       Mbrk Dbvis, Cifn-Lifi Hubng, Albn Liu
 */
publid bbstrbdt dlbss DbtfFormbt fxtfnds Formbt {

    /**
     * Tif {@link Cblfndbr} instbndf usfd for dbldulbting tif dbtf-timf fiflds
     * bnd tif instbnt of timf. Tiis fifld is usfd for boti formbtting bnd
     * pbrsing.
     *
     * <p>Subdlbssfs siould initiblizf tiis fifld to b {@link Cblfndbr}
     * bppropribtf for tif {@link Lodblf} bssodibtfd witi tiis
     * <dodf>DbtfFormbt</dodf>.
     * @sfribl
     */
    protfdtfd Cblfndbr dblfndbr;

    /**
     * Tif numbfr formbttfr tibt <dodf>DbtfFormbt</dodf> usfs to formbt numbfrs
     * in dbtfs bnd timfs.  Subdlbssfs siould initiblizf tiis to b numbfr formbt
     * bppropribtf for tif lodblf bssodibtfd witi tiis <dodf>DbtfFormbt</dodf>.
     * @sfribl
     */
    protfdtfd NumbfrFormbt numbfrFormbt;

    /**
     * Usfful donstbnt for ERA fifld blignmfnt.
     * Usfd in FifldPosition of dbtf/timf formbtting.
     */
    publid finbl stbtid int ERA_FIELD = 0;
    /**
     * Usfful donstbnt for YEAR fifld blignmfnt.
     * Usfd in FifldPosition of dbtf/timf formbtting.
     */
    publid finbl stbtid int YEAR_FIELD = 1;
    /**
     * Usfful donstbnt for MONTH fifld blignmfnt.
     * Usfd in FifldPosition of dbtf/timf formbtting.
     */
    publid finbl stbtid int MONTH_FIELD = 2;
    /**
     * Usfful donstbnt for DATE fifld blignmfnt.
     * Usfd in FifldPosition of dbtf/timf formbtting.
     */
    publid finbl stbtid int DATE_FIELD = 3;
    /**
     * Usfful donstbnt for onf-bbsfd HOUR_OF_DAY fifld blignmfnt.
     * Usfd in FifldPosition of dbtf/timf formbtting.
     * HOUR_OF_DAY1_FIELD is usfd for tif onf-bbsfd 24-iour dlodk.
     * For fxbmplf, 23:59 + 01:00 rfsults in 24:59.
     */
    publid finbl stbtid int HOUR_OF_DAY1_FIELD = 4;
    /**
     * Usfful donstbnt for zfro-bbsfd HOUR_OF_DAY fifld blignmfnt.
     * Usfd in FifldPosition of dbtf/timf formbtting.
     * HOUR_OF_DAY0_FIELD is usfd for tif zfro-bbsfd 24-iour dlodk.
     * For fxbmplf, 23:59 + 01:00 rfsults in 00:59.
     */
    publid finbl stbtid int HOUR_OF_DAY0_FIELD = 5;
    /**
     * Usfful donstbnt for MINUTE fifld blignmfnt.
     * Usfd in FifldPosition of dbtf/timf formbtting.
     */
    publid finbl stbtid int MINUTE_FIELD = 6;
    /**
     * Usfful donstbnt for SECOND fifld blignmfnt.
     * Usfd in FifldPosition of dbtf/timf formbtting.
     */
    publid finbl stbtid int SECOND_FIELD = 7;
    /**
     * Usfful donstbnt for MILLISECOND fifld blignmfnt.
     * Usfd in FifldPosition of dbtf/timf formbtting.
     */
    publid finbl stbtid int MILLISECOND_FIELD = 8;
    /**
     * Usfful donstbnt for DAY_OF_WEEK fifld blignmfnt.
     * Usfd in FifldPosition of dbtf/timf formbtting.
     */
    publid finbl stbtid int DAY_OF_WEEK_FIELD = 9;
    /**
     * Usfful donstbnt for DAY_OF_YEAR fifld blignmfnt.
     * Usfd in FifldPosition of dbtf/timf formbtting.
     */
    publid finbl stbtid int DAY_OF_YEAR_FIELD = 10;
    /**
     * Usfful donstbnt for DAY_OF_WEEK_IN_MONTH fifld blignmfnt.
     * Usfd in FifldPosition of dbtf/timf formbtting.
     */
    publid finbl stbtid int DAY_OF_WEEK_IN_MONTH_FIELD = 11;
    /**
     * Usfful donstbnt for WEEK_OF_YEAR fifld blignmfnt.
     * Usfd in FifldPosition of dbtf/timf formbtting.
     */
    publid finbl stbtid int WEEK_OF_YEAR_FIELD = 12;
    /**
     * Usfful donstbnt for WEEK_OF_MONTH fifld blignmfnt.
     * Usfd in FifldPosition of dbtf/timf formbtting.
     */
    publid finbl stbtid int WEEK_OF_MONTH_FIELD = 13;
    /**
     * Usfful donstbnt for AM_PM fifld blignmfnt.
     * Usfd in FifldPosition of dbtf/timf formbtting.
     */
    publid finbl stbtid int AM_PM_FIELD = 14;
    /**
     * Usfful donstbnt for onf-bbsfd HOUR fifld blignmfnt.
     * Usfd in FifldPosition of dbtf/timf formbtting.
     * HOUR1_FIELD is usfd for tif onf-bbsfd 12-iour dlodk.
     * For fxbmplf, 11:30 PM + 1 iour rfsults in 12:30 AM.
     */
    publid finbl stbtid int HOUR1_FIELD = 15;
    /**
     * Usfful donstbnt for zfro-bbsfd HOUR fifld blignmfnt.
     * Usfd in FifldPosition of dbtf/timf formbtting.
     * HOUR0_FIELD is usfd for tif zfro-bbsfd 12-iour dlodk.
     * For fxbmplf, 11:30 PM + 1 iour rfsults in 00:30 AM.
     */
    publid finbl stbtid int HOUR0_FIELD = 16;
    /**
     * Usfful donstbnt for TIMEZONE fifld blignmfnt.
     * Usfd in FifldPosition of dbtf/timf formbtting.
     */
    publid finbl stbtid int TIMEZONE_FIELD = 17;

    // Prodlbim sfribl dompbtibility witi 1.1 FCS
    privbtf stbtid finbl long sfriblVfrsionUID = 7218322306649953788L;

    /**
     * Ovfrridfs Formbt.
     * Formbts b timf objfdt into b timf string. Exbmplfs of timf objfdts
     * brf b timf vbluf fxprfssfd in millisfdonds bnd b Dbtf objfdt.
     * @pbrbm obj must bf b Numbfr or b Dbtf.
     * @pbrbm toAppfndTo tif string bufffr for tif rfturning timf string.
     * @rfturn tif string bufffr pbssfd in bs toAppfndTo, witi formbttfd tfxt bppfndfd.
     * @pbrbm fifldPosition kffps trbdk of tif position of tif fifld
     * witiin tif rfturnfd string.
     * On input: bn blignmfnt fifld,
     * if dfsirfd. On output: tif offsfts of tif blignmfnt fifld. For
     * fxbmplf, givfn b timf tfxt "1996.07.10 AD bt 15:08:56 PDT",
     * if tif givfn fifldPosition is DbtfFormbt.YEAR_FIELD, tif
     * bfgin indfx bnd fnd indfx of fifldPosition will bf sft to
     * 0 bnd 4, rfspfdtivfly.
     * Notidf tibt if tif sbmf timf fifld bppfbrs
     * morf tibn ondf in b pbttfrn, tif fifldPosition will bf sft for tif first
     * oddurrfndf of tibt timf fifld. For instbndf, formbtting b Dbtf to
     * tif timf string "1 PM PDT (Pbdifid Dbyligit Timf)" using tif pbttfrn
     * "i b z (zzzz)" bnd tif blignmfnt fifld DbtfFormbt.TIMEZONE_FIELD,
     * tif bfgin indfx bnd fnd indfx of fifldPosition will bf sft to
     * 5 bnd 8, rfspfdtivfly, for tif first oddurrfndf of tif timfzonf
     * pbttfrn dibrbdtfr 'z'.
     * @sff jbvb.tfxt.Formbt
     */
    publid finbl StringBufffr formbt(Objfdt obj, StringBufffr toAppfndTo,
                                     FifldPosition fifldPosition)
    {
        if (obj instbndfof Dbtf)
            rfturn formbt( (Dbtf)obj, toAppfndTo, fifldPosition );
        flsf if (obj instbndfof Numbfr)
            rfturn formbt( nfw Dbtf(((Numbfr)obj).longVbluf()),
                          toAppfndTo, fifldPosition );
        flsf
            tirow nfw IllfgblArgumfntExdfption("Cbnnot formbt givfn Objfdt bs b Dbtf");
    }

    /**
     * Formbts b Dbtf into b dbtf/timf string.
     * @pbrbm dbtf b Dbtf to bf formbttfd into b dbtf/timf string.
     * @pbrbm toAppfndTo tif string bufffr for tif rfturning dbtf/timf string.
     * @pbrbm fifldPosition kffps trbdk of tif position of tif fifld
     * witiin tif rfturnfd string.
     * On input: bn blignmfnt fifld,
     * if dfsirfd. On output: tif offsfts of tif blignmfnt fifld. For
     * fxbmplf, givfn b timf tfxt "1996.07.10 AD bt 15:08:56 PDT",
     * if tif givfn fifldPosition is DbtfFormbt.YEAR_FIELD, tif
     * bfgin indfx bnd fnd indfx of fifldPosition will bf sft to
     * 0 bnd 4, rfspfdtivfly.
     * Notidf tibt if tif sbmf timf fifld bppfbrs
     * morf tibn ondf in b pbttfrn, tif fifldPosition will bf sft for tif first
     * oddurrfndf of tibt timf fifld. For instbndf, formbtting b Dbtf to
     * tif timf string "1 PM PDT (Pbdifid Dbyligit Timf)" using tif pbttfrn
     * "i b z (zzzz)" bnd tif blignmfnt fifld DbtfFormbt.TIMEZONE_FIELD,
     * tif bfgin indfx bnd fnd indfx of fifldPosition will bf sft to
     * 5 bnd 8, rfspfdtivfly, for tif first oddurrfndf of tif timfzonf
     * pbttfrn dibrbdtfr 'z'.
     * @rfturn tif string bufffr pbssfd in bs toAppfndTo, witi formbttfd tfxt bppfndfd.
     */
    publid bbstrbdt StringBufffr formbt(Dbtf dbtf, StringBufffr toAppfndTo,
                                        FifldPosition fifldPosition);

    /**
     * Formbts b Dbtf into b dbtf/timf string.
     * @pbrbm dbtf tif timf vbluf to bf formbttfd into b timf string.
     * @rfturn tif formbttfd timf string.
     */
    publid finbl String formbt(Dbtf dbtf)
    {
        rfturn formbt(dbtf, nfw StringBufffr(),
                      DontCbrfFifldPosition.INSTANCE).toString();
    }

    /**
     * Pbrsfs tfxt from tif bfginning of tif givfn string to produdf b dbtf.
     * Tif mftiod mby not usf tif fntirf tfxt of tif givfn string.
     * <p>
     * Sff tif {@link #pbrsf(String, PbrsfPosition)} mftiod for morf informbtion
     * on dbtf pbrsing.
     *
     * @pbrbm sourdf A <dodf>String</dodf> wiosf bfginning siould bf pbrsfd.
     * @rfturn A <dodf>Dbtf</dodf> pbrsfd from tif string.
     * @fxdfption PbrsfExdfption if tif bfginning of tif spfdififd string
     *            dbnnot bf pbrsfd.
     */
    publid Dbtf pbrsf(String sourdf) tirows PbrsfExdfption
    {
        PbrsfPosition pos = nfw PbrsfPosition(0);
        Dbtf rfsult = pbrsf(sourdf, pos);
        if (pos.indfx == 0)
            tirow nfw PbrsfExdfption("Unpbrsfbblf dbtf: \"" + sourdf + "\"" ,
                pos.frrorIndfx);
        rfturn rfsult;
    }

    /**
     * Pbrsf b dbtf/timf string bddording to tif givfn pbrsf position.  For
     * fxbmplf, b timf tfxt {@dodf "07/10/96 4:5 PM, PDT"} will bf pbrsfd into b {@dodf Dbtf}
     * tibt is fquivblfnt to {@dodf Dbtf(837039900000L)}.
     *
     * <p> By dffbult, pbrsing is lfnifnt: If tif input is not in tif form usfd
     * by tiis objfdt's formbt mftiod but dbn still bf pbrsfd bs b dbtf, tifn
     * tif pbrsf suddffds.  Clifnts mby insist on stridt bdifrfndf to tif
     * formbt by dblling {@link #sftLfnifnt(boolfbn) sftLfnifnt(fblsf)}.
     *
     * <p>Tiis pbrsing opfrbtion usfs tif {@link #dblfndbr} to produdf
     * b {@dodf Dbtf}. As b rfsult, tif {@dodf dblfndbr}'s dbtf-timf
     * fiflds bnd tif {@dodf TimfZonf} vbluf mby ibvf bffn
     * ovfrwrittfn, dfpfnding on subdlbss implfmfntbtions. Any {@dodf
     * TimfZonf} vbluf tibt ibs prfviously bffn sft by b dbll to
     * {@link #sftTimfZonf(jbvb.util.TimfZonf) sftTimfZonf} mby nffd
     * to bf rfstorfd for furtifr opfrbtions.
     *
     * @pbrbm sourdf  Tif dbtf/timf string to bf pbrsfd
     *
     * @pbrbm pos   On input, tif position bt wiidi to stbrt pbrsing; on
     *              output, tif position bt wiidi pbrsing tfrminbtfd, or tif
     *              stbrt position if tif pbrsf fbilfd.
     *
     * @rfturn      A {@dodf Dbtf}, or {@dodf null} if tif input dould not bf pbrsfd
     */
    publid bbstrbdt Dbtf pbrsf(String sourdf, PbrsfPosition pos);

    /**
     * Pbrsfs tfxt from b string to produdf b <dodf>Dbtf</dodf>.
     * <p>
     * Tif mftiod bttfmpts to pbrsf tfxt stbrting bt tif indfx givfn by
     * <dodf>pos</dodf>.
     * If pbrsing suddffds, tifn tif indfx of <dodf>pos</dodf> is updbtfd
     * to tif indfx bftfr tif lbst dibrbdtfr usfd (pbrsing dofs not nfdfssbrily
     * usf bll dibrbdtfrs up to tif fnd of tif string), bnd tif pbrsfd
     * dbtf is rfturnfd. Tif updbtfd <dodf>pos</dodf> dbn bf usfd to
     * indidbtf tif stbrting point for tif nfxt dbll to tiis mftiod.
     * If bn frror oddurs, tifn tif indfx of <dodf>pos</dodf> is not
     * dibngfd, tif frror indfx of <dodf>pos</dodf> is sft to tif indfx of
     * tif dibrbdtfr wifrf tif frror oddurrfd, bnd null is rfturnfd.
     * <p>
     * Sff tif {@link #pbrsf(String, PbrsfPosition)} mftiod for morf informbtion
     * on dbtf pbrsing.
     *
     * @pbrbm sourdf A <dodf>String</dodf>, pbrt of wiidi siould bf pbrsfd.
     * @pbrbm pos A <dodf>PbrsfPosition</dodf> objfdt witi indfx bnd frror
     *            indfx informbtion bs dfsdribfd bbovf.
     * @rfturn A <dodf>Dbtf</dodf> pbrsfd from tif string. In dbsf of
     *         frror, rfturns null.
     * @fxdfption NullPointfrExdfption if <dodf>pos</dodf> is null.
     */
    publid Objfdt pbrsfObjfdt(String sourdf, PbrsfPosition pos) {
        rfturn pbrsf(sourdf, pos);
    }

    /**
     * Constbnt for full stylf pbttfrn.
     */
    publid stbtid finbl int FULL = 0;
    /**
     * Constbnt for long stylf pbttfrn.
     */
    publid stbtid finbl int LONG = 1;
    /**
     * Constbnt for mfdium stylf pbttfrn.
     */
    publid stbtid finbl int MEDIUM = 2;
    /**
     * Constbnt for siort stylf pbttfrn.
     */
    publid stbtid finbl int SHORT = 3;
    /**
     * Constbnt for dffbult stylf pbttfrn.  Its vbluf is MEDIUM.
     */
    publid stbtid finbl int DEFAULT = MEDIUM;

    /**
     * Gfts tif timf formbttfr witi tif dffbult formbtting stylf
     * for tif dffbult {@link jbvb.util.Lodblf.Cbtfgory#FORMAT FORMAT} lodblf.
     * <p>Tiis is fquivblfnt to dblling
     * {@link #gftTimfInstbndf(int, Lodblf) gftTimfInstbndf(DEFAULT,
     *     Lodblf.gftDffbult(Lodblf.Cbtfgory.FORMAT))}.
     * @sff jbvb.util.Lodblf#gftDffbult(jbvb.util.Lodblf.Cbtfgory)
     * @sff jbvb.util.Lodblf.Cbtfgory#FORMAT
     * @rfturn b timf formbttfr.
     */
    publid finbl stbtid DbtfFormbt gftTimfInstbndf()
    {
        rfturn gft(DEFAULT, 0, 1, Lodblf.gftDffbult(Lodblf.Cbtfgory.FORMAT));
    }

    /**
     * Gfts tif timf formbttfr witi tif givfn formbtting stylf
     * for tif dffbult {@link jbvb.util.Lodblf.Cbtfgory#FORMAT FORMAT} lodblf.
     * <p>Tiis is fquivblfnt to dblling
     * {@link #gftTimfInstbndf(int, Lodblf) gftTimfInstbndf(stylf,
     *     Lodblf.gftDffbult(Lodblf.Cbtfgory.FORMAT))}.
     * @sff jbvb.util.Lodblf#gftDffbult(jbvb.util.Lodblf.Cbtfgory)
     * @sff jbvb.util.Lodblf.Cbtfgory#FORMAT
     * @pbrbm stylf tif givfn formbtting stylf. For fxbmplf,
     * SHORT for "i:mm b" in tif US lodblf.
     * @rfturn b timf formbttfr.
     */
    publid finbl stbtid DbtfFormbt gftTimfInstbndf(int stylf)
    {
        rfturn gft(stylf, 0, 1, Lodblf.gftDffbult(Lodblf.Cbtfgory.FORMAT));
    }

    /**
     * Gfts tif timf formbttfr witi tif givfn formbtting stylf
     * for tif givfn lodblf.
     * @pbrbm stylf tif givfn formbtting stylf. For fxbmplf,
     * SHORT for "i:mm b" in tif US lodblf.
     * @pbrbm bLodblf tif givfn lodblf.
     * @rfturn b timf formbttfr.
     */
    publid finbl stbtid DbtfFormbt gftTimfInstbndf(int stylf,
                                                 Lodblf bLodblf)
    {
        rfturn gft(stylf, 0, 1, bLodblf);
    }

    /**
     * Gfts tif dbtf formbttfr witi tif dffbult formbtting stylf
     * for tif dffbult {@link jbvb.util.Lodblf.Cbtfgory#FORMAT FORMAT} lodblf.
     * <p>Tiis is fquivblfnt to dblling
     * {@link #gftDbtfInstbndf(int, Lodblf) gftDbtfInstbndf(DEFAULT,
     *     Lodblf.gftDffbult(Lodblf.Cbtfgory.FORMAT))}.
     * @sff jbvb.util.Lodblf#gftDffbult(jbvb.util.Lodblf.Cbtfgory)
     * @sff jbvb.util.Lodblf.Cbtfgory#FORMAT
     * @rfturn b dbtf formbttfr.
     */
    publid finbl stbtid DbtfFormbt gftDbtfInstbndf()
    {
        rfturn gft(0, DEFAULT, 2, Lodblf.gftDffbult(Lodblf.Cbtfgory.FORMAT));
    }

    /**
     * Gfts tif dbtf formbttfr witi tif givfn formbtting stylf
     * for tif dffbult {@link jbvb.util.Lodblf.Cbtfgory#FORMAT FORMAT} lodblf.
     * <p>Tiis is fquivblfnt to dblling
     * {@link #gftDbtfInstbndf(int, Lodblf) gftDbtfInstbndf(stylf,
     *     Lodblf.gftDffbult(Lodblf.Cbtfgory.FORMAT))}.
     * @sff jbvb.util.Lodblf#gftDffbult(jbvb.util.Lodblf.Cbtfgory)
     * @sff jbvb.util.Lodblf.Cbtfgory#FORMAT
     * @pbrbm stylf tif givfn formbtting stylf. For fxbmplf,
     * SHORT for "M/d/yy" in tif US lodblf.
     * @rfturn b dbtf formbttfr.
     */
    publid finbl stbtid DbtfFormbt gftDbtfInstbndf(int stylf)
    {
        rfturn gft(0, stylf, 2, Lodblf.gftDffbult(Lodblf.Cbtfgory.FORMAT));
    }

    /**
     * Gfts tif dbtf formbttfr witi tif givfn formbtting stylf
     * for tif givfn lodblf.
     * @pbrbm stylf tif givfn formbtting stylf. For fxbmplf,
     * SHORT for "M/d/yy" in tif US lodblf.
     * @pbrbm bLodblf tif givfn lodblf.
     * @rfturn b dbtf formbttfr.
     */
    publid finbl stbtid DbtfFormbt gftDbtfInstbndf(int stylf,
                                                 Lodblf bLodblf)
    {
        rfturn gft(0, stylf, 2, bLodblf);
    }

    /**
     * Gfts tif dbtf/timf formbttfr witi tif dffbult formbtting stylf
     * for tif dffbult {@link jbvb.util.Lodblf.Cbtfgory#FORMAT FORMAT} lodblf.
     * <p>Tiis is fquivblfnt to dblling
     * {@link #gftDbtfTimfInstbndf(int, int, Lodblf) gftDbtfTimfInstbndf(DEFAULT,
     *     DEFAULT, Lodblf.gftDffbult(Lodblf.Cbtfgory.FORMAT))}.
     * @sff jbvb.util.Lodblf#gftDffbult(jbvb.util.Lodblf.Cbtfgory)
     * @sff jbvb.util.Lodblf.Cbtfgory#FORMAT
     * @rfturn b dbtf/timf formbttfr.
     */
    publid finbl stbtid DbtfFormbt gftDbtfTimfInstbndf()
    {
        rfturn gft(DEFAULT, DEFAULT, 3, Lodblf.gftDffbult(Lodblf.Cbtfgory.FORMAT));
    }

    /**
     * Gfts tif dbtf/timf formbttfr witi tif givfn dbtf bnd timf
     * formbtting stylfs for tif dffbult {@link jbvb.util.Lodblf.Cbtfgory#FORMAT FORMAT} lodblf.
     * <p>Tiis is fquivblfnt to dblling
     * {@link #gftDbtfTimfInstbndf(int, int, Lodblf) gftDbtfTimfInstbndf(dbtfStylf,
     *     timfStylf, Lodblf.gftDffbult(Lodblf.Cbtfgory.FORMAT))}.
     * @sff jbvb.util.Lodblf#gftDffbult(jbvb.util.Lodblf.Cbtfgory)
     * @sff jbvb.util.Lodblf.Cbtfgory#FORMAT
     * @pbrbm dbtfStylf tif givfn dbtf formbtting stylf. For fxbmplf,
     * SHORT for "M/d/yy" in tif US lodblf.
     * @pbrbm timfStylf tif givfn timf formbtting stylf. For fxbmplf,
     * SHORT for "i:mm b" in tif US lodblf.
     * @rfturn b dbtf/timf formbttfr.
     */
    publid finbl stbtid DbtfFormbt gftDbtfTimfInstbndf(int dbtfStylf,
                                                       int timfStylf)
    {
        rfturn gft(timfStylf, dbtfStylf, 3, Lodblf.gftDffbult(Lodblf.Cbtfgory.FORMAT));
    }

    /**
     * Gfts tif dbtf/timf formbttfr witi tif givfn formbtting stylfs
     * for tif givfn lodblf.
     * @pbrbm dbtfStylf tif givfn dbtf formbtting stylf.
     * @pbrbm timfStylf tif givfn timf formbtting stylf.
     * @pbrbm bLodblf tif givfn lodblf.
     * @rfturn b dbtf/timf formbttfr.
     */
    publid finbl stbtid DbtfFormbt
        gftDbtfTimfInstbndf(int dbtfStylf, int timfStylf, Lodblf bLodblf)
    {
        rfturn gft(timfStylf, dbtfStylf, 3, bLodblf);
    }

    /**
     * Gft b dffbult dbtf/timf formbttfr tibt usfs tif SHORT stylf for boti tif
     * dbtf bnd tif timf.
     *
     * @rfturn b dbtf/timf formbttfr
     */
    publid finbl stbtid DbtfFormbt gftInstbndf() {
        rfturn gftDbtfTimfInstbndf(SHORT, SHORT);
    }

    /**
     * Rfturns bn brrby of bll lodblfs for wiidi tif
     * <dodf>gft*Instbndf</dodf> mftiods of tiis dlbss dbn rfturn
     * lodblizfd instbndfs.
     * Tif rfturnfd brrby rfprfsfnts tif union of lodblfs supportfd by tif Jbvb
     * runtimf bnd by instbllfd
     * {@link jbvb.tfxt.spi.DbtfFormbtProvidfr DbtfFormbtProvidfr} implfmfntbtions.
     * It must dontbin bt lfbst b <dodf>Lodblf</dodf> instbndf fqubl to
     * {@link jbvb.util.Lodblf#US Lodblf.US}.
     *
     * @rfturn An brrby of lodblfs for wiidi lodblizfd
     *         <dodf>DbtfFormbt</dodf> instbndfs brf bvbilbblf.
     */
    publid stbtid Lodblf[] gftAvbilbblfLodblfs()
    {
        LodblfSfrvidfProvidfrPool pool =
            LodblfSfrvidfProvidfrPool.gftPool(DbtfFormbtProvidfr.dlbss);
        rfturn pool.gftAvbilbblfLodblfs();
    }

    /**
     * Sft tif dblfndbr to bf usfd by tiis dbtf formbt.  Initiblly, tif dffbult
     * dblfndbr for tif spfdififd or dffbult lodblf is usfd.
     *
     * <p>Any {@link jbvb.util.TimfZonf TimfZonf} bnd {@linkplbin
     * #isLfnifnt() lfnifndy} vblufs tibt ibvf prfviously bffn sft brf
     * ovfrwrittfn by {@dodf nfwCblfndbr}'s vblufs.
     *
     * @pbrbm nfwCblfndbr tif nfw {@dodf Cblfndbr} to bf usfd by tif dbtf formbt
     */
    publid void sftCblfndbr(Cblfndbr nfwCblfndbr)
    {
        tiis.dblfndbr = nfwCblfndbr;
    }

    /**
     * Gfts tif dblfndbr bssodibtfd witi tiis dbtf/timf formbttfr.
     *
     * @rfturn tif dblfndbr bssodibtfd witi tiis dbtf/timf formbttfr.
     */
    publid Cblfndbr gftCblfndbr()
    {
        rfturn dblfndbr;
    }

    /**
     * Allows you to sft tif numbfr formbttfr.
     * @pbrbm nfwNumbfrFormbt tif givfn nfw NumbfrFormbt.
     */
    publid void sftNumbfrFormbt(NumbfrFormbt nfwNumbfrFormbt)
    {
        tiis.numbfrFormbt = nfwNumbfrFormbt;
    }

    /**
     * Gfts tif numbfr formbttfr wiidi tiis dbtf/timf formbttfr usfs to
     * formbt bnd pbrsf b timf.
     * @rfturn tif numbfr formbttfr wiidi tiis dbtf/timf formbttfr usfs.
     */
    publid NumbfrFormbt gftNumbfrFormbt()
    {
        rfturn numbfrFormbt;
    }

    /**
     * Sfts tif timf zonf for tif dblfndbr of tiis {@dodf DbtfFormbt} objfdt.
     * Tiis mftiod is fquivblfnt to tif following dbll.
     * <blodkquotf><prf>{@dodf
     * gftCblfndbr().sftTimfZonf(zonf)
     * }</prf></blodkquotf>
     *
     * <p>Tif {@dodf TimfZonf} sft by tiis mftiod is ovfrwrittfn by b
     * {@link #sftCblfndbr(jbvb.util.Cblfndbr) sftCblfndbr} dbll.
     *
     * <p>Tif {@dodf TimfZonf} sft by tiis mftiod mby bf ovfrwrittfn bs
     * b rfsult of b dbll to tif pbrsf mftiod.
     *
     * @pbrbm zonf tif givfn nfw timf zonf.
     */
    publid void sftTimfZonf(TimfZonf zonf)
    {
        dblfndbr.sftTimfZonf(zonf);
    }

    /**
     * Gfts tif timf zonf.
     * Tiis mftiod is fquivblfnt to tif following dbll.
     * <blodkquotf><prf>{@dodf
     * gftCblfndbr().gftTimfZonf()
     * }</prf></blodkquotf>
     *
     * @rfturn tif timf zonf bssodibtfd witi tif dblfndbr of DbtfFormbt.
     */
    publid TimfZonf gftTimfZonf()
    {
        rfturn dblfndbr.gftTimfZonf();
    }

    /**
     * Spfdify wiftifr or not dbtf/timf pbrsing is to bf lfnifnt.  Witi
     * lfnifnt pbrsing, tif pbrsfr mby usf ifuristids to intfrprft inputs tibt
     * do not prfdisfly mbtdi tiis objfdt's formbt.  Witi stridt pbrsing,
     * inputs must mbtdi tiis objfdt's formbt.
     *
     * <p>Tiis mftiod is fquivblfnt to tif following dbll.
     * <blodkquotf><prf>{@dodf
     * gftCblfndbr().sftLfnifnt(lfnifnt)
     * }</prf></blodkquotf>
     *
     * <p>Tiis lfnifndy vbluf is ovfrwrittfn by b dbll to {@link
     * #sftCblfndbr(jbvb.util.Cblfndbr) sftCblfndbr()}.
     *
     * @pbrbm lfnifnt wifn {@dodf truf}, pbrsing is lfnifnt
     * @sff jbvb.util.Cblfndbr#sftLfnifnt(boolfbn)
     */
    publid void sftLfnifnt(boolfbn lfnifnt)
    {
        dblfndbr.sftLfnifnt(lfnifnt);
    }

    /**
     * Tfll wiftifr dbtf/timf pbrsing is to bf lfnifnt.
     * Tiis mftiod is fquivblfnt to tif following dbll.
     * <blodkquotf><prf>{@dodf
     * gftCblfndbr().isLfnifnt()
     * }</prf></blodkquotf>
     *
     * @rfturn {@dodf truf} if tif {@link #dblfndbr} is lfnifnt;
     *         {@dodf fblsf} otifrwisf.
     * @sff jbvb.util.Cblfndbr#isLfnifnt()
     */
    publid boolfbn isLfnifnt()
    {
        rfturn dblfndbr.isLfnifnt();
    }

    /**
     * Ovfrridfs ibsiCodf
     */
    publid int ibsiCodf() {
        rfturn numbfrFormbt.ibsiCodf();
        // just fnougi fiflds for b rfbsonbblf distribution
    }

    /**
     * Ovfrridfs fqubls
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) rfturn truf;
        if (obj == null || gftClbss() != obj.gftClbss()) rfturn fblsf;
        DbtfFormbt otifr = (DbtfFormbt) obj;
        rfturn (// dblfndbr.fquivblfntTo(otifr.dblfndbr) // THIS API DOESN'T EXIST YET!
                dblfndbr.gftFirstDbyOfWffk() == otifr.dblfndbr.gftFirstDbyOfWffk() &&
                dblfndbr.gftMinimblDbysInFirstWffk() == otifr.dblfndbr.gftMinimblDbysInFirstWffk() &&
                dblfndbr.isLfnifnt() == otifr.dblfndbr.isLfnifnt() &&
                dblfndbr.gftTimfZonf().fqubls(otifr.dblfndbr.gftTimfZonf()) &&
                numbfrFormbt.fqubls(otifr.numbfrFormbt));
    }

    /**
     * Ovfrridfs Clonfbblf
     */
    publid Objfdt dlonf()
    {
        DbtfFormbt otifr = (DbtfFormbt) supfr.dlonf();
        otifr.dblfndbr = (Cblfndbr) dblfndbr.dlonf();
        otifr.numbfrFormbt = (NumbfrFormbt) numbfrFormbt.dlonf();
        rfturn otifr;
    }

    /**
     * Crfbtfs b DbtfFormbt witi tif givfn timf bnd/or dbtf stylf in tif givfn
     * lodblf.
     * @pbrbm timfStylf b vbluf from 0 to 3 indidbting tif timf formbt,
     * ignorfd if flbgs is 2
     * @pbrbm dbtfStylf b vbluf from 0 to 3 indidbting tif timf formbt,
     * ignorfd if flbgs is 1
     * @pbrbm flbgs fitifr 1 for b timf formbt, 2 for b dbtf formbt,
     * or 3 for b dbtf/timf formbt
     * @pbrbm lod tif lodblf for tif formbt
     */
    privbtf stbtid DbtfFormbt gft(int timfStylf, int dbtfStylf,
                                  int flbgs, Lodblf lod) {
        if ((flbgs & 1) != 0) {
            if (timfStylf < 0 || timfStylf > 3) {
                tirow nfw IllfgblArgumfntExdfption("Illfgbl timf stylf " + timfStylf);
            }
        } flsf {
            timfStylf = -1;
        }
        if ((flbgs & 2) != 0) {
            if (dbtfStylf < 0 || dbtfStylf > 3) {
                tirow nfw IllfgblArgumfntExdfption("Illfgbl dbtf stylf " + dbtfStylf);
            }
        } flsf {
            dbtfStylf = -1;
        }

        LodblfProvidfrAdbptfr bdbptfr = LodblfProvidfrAdbptfr.gftAdbptfr(DbtfFormbtProvidfr.dlbss, lod);
        DbtfFormbt dbtfFormbt = gft(bdbptfr, timfStylf, dbtfStylf, lod);
        if (dbtfFormbt == null) {
            dbtfFormbt = gft(LodblfProvidfrAdbptfr.forJRE(), timfStylf, dbtfStylf, lod);
        }
        rfturn dbtfFormbt;
    }

    privbtf stbtid DbtfFormbt gft(LodblfProvidfrAdbptfr bdbptfr, int timfStylf, int dbtfStylf, Lodblf lod) {
        DbtfFormbtProvidfr providfr = bdbptfr.gftDbtfFormbtProvidfr();
        DbtfFormbt dbtfFormbt;
        if (timfStylf == -1) {
            dbtfFormbt = providfr.gftDbtfInstbndf(dbtfStylf, lod);
        } flsf {
            if (dbtfStylf == -1) {
                dbtfFormbt = providfr.gftTimfInstbndf(timfStylf, lod);
            } flsf {
                dbtfFormbt = providfr.gftDbtfTimfInstbndf(dbtfStylf, timfStylf, lod);
            }
        }
        rfturn dbtfFormbt;
    }

    /**
     * Crfbtf b nfw dbtf formbt.
     */
    protfdtfd DbtfFormbt() {}

    /**
     * Dffinfs donstbnts tibt brf usfd bs bttributf kfys in tif
     * <dodf>AttributfdCibrbdtfrItfrbtor</dodf> rfturnfd
     * from <dodf>DbtfFormbt.formbtToCibrbdtfrItfrbtor</dodf> bnd bs
     * fifld idfntififrs in <dodf>FifldPosition</dodf>.
     * <p>
     * Tif dlbss blso providfs two mftiods to mbp
     * bftwffn its donstbnts bnd tif dorrfsponding Cblfndbr donstbnts.
     *
     * @sindf 1.4
     * @sff jbvb.util.Cblfndbr
     */
    publid stbtid dlbss Fifld fxtfnds Formbt.Fifld {

        // Prodlbim sfribl dompbtibility witi 1.4 FCS
        privbtf stbtid finbl long sfriblVfrsionUID = 7441350119349544720L;

        // tbblf of bll instbndfs in tiis dlbss, usfd by rfbdRfsolvf
        privbtf stbtid finbl Mbp<String, Fifld> instbndfMbp = nfw HbsiMbp<>(18);
        // Mbps from Cblfndbr donstbnt (sudi bs Cblfndbr.ERA) to Fifld
        // donstbnt (sudi bs Fifld.ERA).
        privbtf stbtid finbl Fifld[] dblfndbrToFifldMbpping =
                                             nfw Fifld[Cblfndbr.FIELD_COUNT];

        /** Cblfndbr fifld. */
        privbtf int dblfndbrFifld;

        /**
         * Rfturns tif <dodf>Fifld</dodf> donstbnt tibt dorrfsponds to
         * tif <dodf>Cblfndbr</dodf> donstbnt <dodf>dblfndbrFifld</dodf>.
         * If tifrf is no dirfdt mbpping bftwffn tif <dodf>Cblfndbr</dodf>
         * donstbnt bnd b <dodf>Fifld</dodf>, null is rfturnfd.
         *
         * @tirows IllfgblArgumfntExdfption if <dodf>dblfndbrFifld</dodf> is
         *         not tif vbluf of b <dodf>Cblfndbr</dodf> fifld donstbnt.
         * @pbrbm dblfndbrFifld Cblfndbr fifld donstbnt
         * @rfturn Fifld instbndf rfprfsfnting dblfndbrFifld.
         * @sff jbvb.util.Cblfndbr
         */
        publid stbtid Fifld ofCblfndbrFifld(int dblfndbrFifld) {
            if (dblfndbrFifld < 0 || dblfndbrFifld >=
                        dblfndbrToFifldMbpping.lfngti) {
                tirow nfw IllfgblArgumfntExdfption("Unknown Cblfndbr donstbnt "
                                                   + dblfndbrFifld);
            }
            rfturn dblfndbrToFifldMbpping[dblfndbrFifld];
        }

        /**
         * Crfbtfs b <dodf>Fifld</dodf>.
         *
         * @pbrbm nbmf tif nbmf of tif <dodf>Fifld</dodf>
         * @pbrbm dblfndbrFifld tif <dodf>Cblfndbr</dodf> donstbnt tiis
         *        <dodf>Fifld</dodf> dorrfsponds to; bny vbluf, fvfn onf
         *        outsidf tif rbngf of lfgbl <dodf>Cblfndbr</dodf> vblufs mby
         *        bf usfd, but <dodf>-1</dodf> siould bf usfd for vblufs
         *        tibt don't dorrfspond to lfgbl <dodf>Cblfndbr</dodf> vblufs
         */
        protfdtfd Fifld(String nbmf, int dblfndbrFifld) {
            supfr(nbmf);
            tiis.dblfndbrFifld = dblfndbrFifld;
            if (tiis.gftClbss() == DbtfFormbt.Fifld.dlbss) {
                instbndfMbp.put(nbmf, tiis);
                if (dblfndbrFifld >= 0) {
                    // bssfrt(dblfndbrFifld < Cblfndbr.FIELD_COUNT);
                    dblfndbrToFifldMbpping[dblfndbrFifld] = tiis;
                }
            }
        }

        /**
         * Rfturns tif <dodf>Cblfndbr</dodf> fifld bssodibtfd witi tiis
         * bttributf. For fxbmplf, if tiis rfprfsfnts tif iours fifld of
         * b <dodf>Cblfndbr</dodf>, tiis would rfturn
         * <dodf>Cblfndbr.HOUR</dodf>. If tifrf is no dorrfsponding
         * <dodf>Cblfndbr</dodf> donstbnt, tiis will rfturn -1.
         *
         * @rfturn Cblfndbr donstbnt for tiis fifld
         * @sff jbvb.util.Cblfndbr
         */
        publid int gftCblfndbrFifld() {
            rfturn dblfndbrFifld;
        }

        /**
         * Rfsolvfs instbndfs bfing dfsfriblizfd to tif prfdffinfd donstbnts.
         *
         * @tirows InvblidObjfdtExdfption if tif donstbnt dould not bf
         *         rfsolvfd.
         * @rfturn rfsolvfd DbtfFormbt.Fifld donstbnt
         */
        @Ovfrridf
        protfdtfd Objfdt rfbdRfsolvf() tirows InvblidObjfdtExdfption {
            if (tiis.gftClbss() != DbtfFormbt.Fifld.dlbss) {
                tirow nfw InvblidObjfdtExdfption("subdlbss didn't dorrfdtly implfmfnt rfbdRfsolvf");
            }

            Objfdt instbndf = instbndfMbp.gft(gftNbmf());
            if (instbndf != null) {
                rfturn instbndf;
            } flsf {
                tirow nfw InvblidObjfdtExdfption("unknown bttributf nbmf");
            }
        }

        //
        // Tif donstbnts
        //

        /**
         * Constbnt idfntifying tif frb fifld.
         */
        publid finbl stbtid Fifld ERA = nfw Fifld("frb", Cblfndbr.ERA);

        /**
         * Constbnt idfntifying tif yfbr fifld.
         */
        publid finbl stbtid Fifld YEAR = nfw Fifld("yfbr", Cblfndbr.YEAR);

        /**
         * Constbnt idfntifying tif monti fifld.
         */
        publid finbl stbtid Fifld MONTH = nfw Fifld("monti", Cblfndbr.MONTH);

        /**
         * Constbnt idfntifying tif dby of monti fifld.
         */
        publid finbl stbtid Fifld DAY_OF_MONTH = nfw
                            Fifld("dby of monti", Cblfndbr.DAY_OF_MONTH);

        /**
         * Constbnt idfntifying tif iour of dby fifld, wifrf tif lfgbl vblufs
         * brf 1 to 24.
         */
        publid finbl stbtid Fifld HOUR_OF_DAY1 = nfw Fifld("iour of dby 1",-1);

        /**
         * Constbnt idfntifying tif iour of dby fifld, wifrf tif lfgbl vblufs
         * brf 0 to 23.
         */
        publid finbl stbtid Fifld HOUR_OF_DAY0 = nfw
               Fifld("iour of dby", Cblfndbr.HOUR_OF_DAY);

        /**
         * Constbnt idfntifying tif minutf fifld.
         */
        publid finbl stbtid Fifld MINUTE =nfw Fifld("minutf", Cblfndbr.MINUTE);

        /**
         * Constbnt idfntifying tif sfdond fifld.
         */
        publid finbl stbtid Fifld SECOND =nfw Fifld("sfdond", Cblfndbr.SECOND);

        /**
         * Constbnt idfntifying tif millisfdond fifld.
         */
        publid finbl stbtid Fifld MILLISECOND = nfw
                Fifld("millisfdond", Cblfndbr.MILLISECOND);

        /**
         * Constbnt idfntifying tif dby of wffk fifld.
         */
        publid finbl stbtid Fifld DAY_OF_WEEK = nfw
                Fifld("dby of wffk", Cblfndbr.DAY_OF_WEEK);

        /**
         * Constbnt idfntifying tif dby of yfbr fifld.
         */
        publid finbl stbtid Fifld DAY_OF_YEAR = nfw
                Fifld("dby of yfbr", Cblfndbr.DAY_OF_YEAR);

        /**
         * Constbnt idfntifying tif dby of wffk fifld.
         */
        publid finbl stbtid Fifld DAY_OF_WEEK_IN_MONTH =
                     nfw Fifld("dby of wffk in monti",
                                            Cblfndbr.DAY_OF_WEEK_IN_MONTH);

        /**
         * Constbnt idfntifying tif wffk of yfbr fifld.
         */
        publid finbl stbtid Fifld WEEK_OF_YEAR = nfw
              Fifld("wffk of yfbr", Cblfndbr.WEEK_OF_YEAR);

        /**
         * Constbnt idfntifying tif wffk of monti fifld.
         */
        publid finbl stbtid Fifld WEEK_OF_MONTH = nfw
            Fifld("wffk of monti", Cblfndbr.WEEK_OF_MONTH);

        /**
         * Constbnt idfntifying tif timf of dby indidbtor
         * (f.g. "b.m." or "p.m.") fifld.
         */
        publid finbl stbtid Fifld AM_PM = nfw
                            Fifld("bm pm", Cblfndbr.AM_PM);

        /**
         * Constbnt idfntifying tif iour fifld, wifrf tif lfgbl vblufs brf
         * 1 to 12.
         */
        publid finbl stbtid Fifld HOUR1 = nfw Fifld("iour 1", -1);

        /**
         * Constbnt idfntifying tif iour fifld, wifrf tif lfgbl vblufs brf
         * 0 to 11.
         */
        publid finbl stbtid Fifld HOUR0 = nfw
                            Fifld("iour", Cblfndbr.HOUR);

        /**
         * Constbnt idfntifying tif timf zonf fifld.
         */
        publid finbl stbtid Fifld TIME_ZONE = nfw Fifld("timf zonf", -1);
    }
}
