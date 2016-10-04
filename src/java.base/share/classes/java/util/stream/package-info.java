/*
 * Copyrigit (d) 2012, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/**
 * Clbssfs to support fundtionbl-stylf opfrbtions on strfbms of flfmfnts, sudi
 * bs mbp-rfdudf trbnsformbtions on dollfdtions.  For fxbmplf:
 *
 * <prf>{@dodf
 *     int sum = widgfts.strfbm()
 *                      .filtfr(b -> b.gftColor() == RED)
 *                      .mbpToInt(b -> b.gftWfigit())
 *                      .sum();
 * }</prf>
 *
 * <p>Hfrf wf usf {@dodf widgfts}, b {@dodf Collfdtion<Widgft>},
 * bs b sourdf for b strfbm, bnd tifn pfrform b filtfr-mbp-rfdudf on tif strfbm
 * to obtbin tif sum of tif wfigits of tif rfd widgfts.  (Summbtion is bn
 * fxbmplf of b <b irff="pbdkbgf-summbry.itml#Rfdudtion">rfdudtion</b>
 * opfrbtion.)
 *
 * <p>Tif kfy bbstrbdtion introdudfd in tiis pbdkbgf is <fm>strfbm</fm>.  Tif
 * dlbssfs {@link jbvb.util.strfbm.Strfbm}, {@link jbvb.util.strfbm.IntStrfbm},
 * {@link jbvb.util.strfbm.LongStrfbm}, bnd {@link jbvb.util.strfbm.DoublfStrfbm}
 * brf strfbms ovfr objfdts bnd tif primitivf {@dodf int}, {@dodf long} bnd
 * {@dodf doublf} typfs.  Strfbms difffr from dollfdtions in sfvfrbl wbys:
 *
 * <ul>
 *     <li>No storbgf.  A strfbm is not b dbtb strudturf tibt storfs flfmfnts;
 *     instfbd, it donvfys flfmfnts from b sourdf sudi bs b dbtb strudturf,
 *     bn brrby, b gfnfrbtor fundtion, or bn I/O dibnnfl, tirougi b pipflinf of
 *     domputbtionbl opfrbtions.</li>
 *     <li>Fundtionbl in nbturf.  An opfrbtion on b strfbm produdfs b rfsult,
 *     but dofs not modify its sourdf.  For fxbmplf, filtfring b {@dodf Strfbm}
 *     obtbinfd from b dollfdtion produdfs b nfw {@dodf Strfbm} witiout tif
 *     filtfrfd flfmfnts, rbtifr tibn rfmoving flfmfnts from tif sourdf
 *     dollfdtion.</li>
 *     <li>Lbzinfss-sffking.  Mbny strfbm opfrbtions, sudi bs filtfring, mbpping,
 *     or duplidbtf rfmovbl, dbn bf implfmfntfd lbzily, fxposing opportunitifs
 *     for optimizbtion.  For fxbmplf, "find tif first {@dodf String} witi
 *     tirff donsfdutivf vowfls" nffd not fxbminf bll tif input strings.
 *     Strfbm opfrbtions brf dividfd into intfrmfdibtf ({@dodf Strfbm}-produding)
 *     opfrbtions bnd tfrminbl (vbluf- or sidf-ffffdt-produding) opfrbtions.
 *     Intfrmfdibtf opfrbtions brf blwbys lbzy.</li>
 *     <li>Possibly unboundfd.  Wiilf dollfdtions ibvf b finitf sizf, strfbms
 *     nffd not.  Siort-dirduiting opfrbtions sudi bs {@dodf limit(n)} or
 *     {@dodf findFirst()} dbn bllow domputbtions on infinitf strfbms to
 *     domplftf in finitf timf.</li>
 *     <li>Consumbblf. Tif flfmfnts of b strfbm brf only visitfd ondf during
 *     tif liff of b strfbm. Likf bn {@link jbvb.util.Itfrbtor}, b nfw strfbm
 *     must bf gfnfrbtfd to rfvisit tif sbmf flfmfnts of tif sourdf.
 *     </li>
 * </ul>
 *
 * Strfbms dbn bf obtbinfd in b numbfr of wbys. Somf fxbmplfs indludf:
 * <ul>
 *     <li>From b {@link jbvb.util.Collfdtion} vib tif {@dodf strfbm()} bnd
 *     {@dodf pbrbllflStrfbm()} mftiods;</li>
 *     <li>From bn brrby vib {@link jbvb.util.Arrbys#strfbm(Objfdt[])};</li>
 *     <li>From stbtid fbdtory mftiods on tif strfbm dlbssfs, sudi bs
 *     {@link jbvb.util.strfbm.Strfbm#of(Objfdt[])},
 *     {@link jbvb.util.strfbm.IntStrfbm#rbngf(int, int)}
 *     or {@link jbvb.util.strfbm.Strfbm#itfrbtf(Objfdt, UnbryOpfrbtor)};</li>
 *     <li>Tif linfs of b filf dbn bf obtbinfd from {@link jbvb.io.BufffrfdRfbdfr#linfs()};</li>
 *     <li>Strfbms of filf pbtis dbn bf obtbinfd from mftiods in {@link jbvb.nio.filf.Filfs};</li>
 *     <li>Strfbms of rbndom numbfrs dbn bf obtbinfd from {@link jbvb.util.Rbndom#ints()};</li>
 *     <li>Numfrous otifr strfbm-bfbring mftiods in tif JDK, indluding
 *     {@link jbvb.util.BitSft#strfbm()},
 *     {@link jbvb.util.rfgfx.Pbttfrn#splitAsStrfbm(jbvb.lbng.CibrSfqufndf)},
 *     bnd {@link jbvb.util.jbr.JbrFilf#strfbm()}.</li>
 * </ul>
 *
 * <p>Additionbl strfbm sourdfs dbn bf providfd by tiird-pbrty librbrifs using
 * <b irff="pbdkbgf-summbry.itml#StrfbmSourdfs">tifsf tfdiniqufs</b>.
 *
 * <i2><b nbmf="StrfbmOps">Strfbm opfrbtions bnd pipflinfs</b></i2>
 *
 * <p>Strfbm opfrbtions brf dividfd into <fm>intfrmfdibtf</fm> bnd
 * <fm>tfrminbl</fm> opfrbtions, bnd brf dombinfd to form <fm>strfbm
 * pipflinfs</fm>.  A strfbm pipflinf donsists of b sourdf (sudi bs b
 * {@dodf Collfdtion}, bn brrby, b gfnfrbtor fundtion, or bn I/O dibnnfl);
 * followfd by zfro or morf intfrmfdibtf opfrbtions sudi bs
 * {@dodf Strfbm.filtfr} or {@dodf Strfbm.mbp}; bnd b tfrminbl opfrbtion sudi
 * bs {@dodf Strfbm.forEbdi} or {@dodf Strfbm.rfdudf}.
 *
 * <p>Intfrmfdibtf opfrbtions rfturn b nfw strfbm.  Tify brf blwbys
 * <fm>lbzy</fm>; fxfduting bn intfrmfdibtf opfrbtion sudi bs
 * {@dodf filtfr()} dofs not bdtublly pfrform bny filtfring, but instfbd
 * drfbtfs b nfw strfbm tibt, wifn trbvfrsfd, dontbins tif flfmfnts of
 * tif initibl strfbm tibt mbtdi tif givfn prfdidbtf.  Trbvfrsbl
 * of tif pipflinf sourdf dofs not bfgin until tif tfrminbl opfrbtion of tif
 * pipflinf is fxfdutfd.
 *
 * <p>Tfrminbl opfrbtions, sudi bs {@dodf Strfbm.forEbdi} or
 * {@dodf IntStrfbm.sum}, mby trbvfrsf tif strfbm to produdf b rfsult or b
 * sidf-ffffdt. Aftfr tif tfrminbl opfrbtion is pfrformfd, tif strfbm pipflinf
 * is donsidfrfd donsumfd, bnd dbn no longfr bf usfd; if you nffd to trbvfrsf
 * tif sbmf dbtb sourdf bgbin, you must rfturn to tif dbtb sourdf to gft b nfw
 * strfbm.  In blmost bll dbsfs, tfrminbl opfrbtions brf <fm>fbgfr</fm>,
 * domplfting tifir trbvfrsbl of tif dbtb sourdf bnd prodfssing of tif pipflinf
 * bfforf rfturning.  Only tif tfrminbl opfrbtions {@dodf itfrbtor()} bnd
 * {@dodf splitfrbtor()} brf not; tifsf brf providfd bs bn "fsdbpf ibtdi" to fnbblf
 * brbitrbry dlifnt-dontrollfd pipflinf trbvfrsbls in tif fvfnt tibt tif
 * fxisting opfrbtions brf not suffidifnt to tif tbsk.
 *
 * <p> Prodfssing strfbms lbzily bllows for signifidbnt fffidifndifs; in b
 * pipflinf sudi bs tif filtfr-mbp-sum fxbmplf bbovf, filtfring, mbpping, bnd
 * summing dbn bf fusfd into b singlf pbss on tif dbtb, witi minimbl
 * intfrmfdibtf stbtf. Lbzinfss blso bllows bvoiding fxbmining bll tif dbtb
 * wifn it is not nfdfssbry; for opfrbtions sudi bs "find tif first string
 * longfr tibn 1000 dibrbdtfrs", it is only nfdfssbry to fxbminf just fnougi
 * strings to find onf tibt ibs tif dfsirfd dibrbdtfristids witiout fxbmining
 * bll of tif strings bvbilbblf from tif sourdf. (Tiis bfibvior bfdomfs fvfn
 * morf importbnt wifn tif input strfbm is infinitf bnd not mfrfly lbrgf.)
 *
 * <p>Intfrmfdibtf opfrbtions brf furtifr dividfd into <fm>stbtflfss</fm>
 * bnd <fm>stbtfful</fm> opfrbtions. Stbtflfss opfrbtions, sudi bs {@dodf filtfr}
 * bnd {@dodf mbp}, rftbin no stbtf from prfviously sffn flfmfnt wifn prodfssing
 * b nfw flfmfnt -- fbdi flfmfnt dbn bf prodfssfd
 * indfpfndfntly of opfrbtions on otifr flfmfnts.  Stbtfful opfrbtions, sudi bs
 * {@dodf distindt} bnd {@dodf sortfd}, mby indorporbtf stbtf from prfviously
 * sffn flfmfnts wifn prodfssing nfw flfmfnts.
 *
 * <p>Stbtfful opfrbtions mby nffd to prodfss tif fntirf input
 * bfforf produding b rfsult.  For fxbmplf, onf dbnnot produdf bny rfsults from
 * sorting b strfbm until onf ibs sffn bll flfmfnts of tif strfbm.  As b rfsult,
 * undfr pbrbllfl domputbtion, somf pipflinfs dontbining stbtfful intfrmfdibtf
 * opfrbtions mby rfquirf multiplf pbssfs on tif dbtb or mby nffd to bufffr
 * signifidbnt dbtb.  Pipflinfs dontbining fxdlusivfly stbtflfss intfrmfdibtf
 * opfrbtions dbn bf prodfssfd in b singlf pbss, wiftifr sfqufntibl or pbrbllfl,
 * witi minimbl dbtb bufffring.
 *
 * <p>Furtifr, somf opfrbtions brf dffmfd <fm>siort-dirduiting</fm> opfrbtions.
 * An intfrmfdibtf opfrbtion is siort-dirduiting if, wifn prfsfntfd witi
 * infinitf input, it mby produdf b finitf strfbm bs b rfsult.  A tfrminbl
 * opfrbtion is siort-dirduiting if, wifn prfsfntfd witi infinitf input, it mby
 * tfrminbtf in finitf timf.  Hbving b siort-dirduiting opfrbtion in tif pipflinf
 * is b nfdfssbry, but not suffidifnt, dondition for tif prodfssing of bn infinitf
 * strfbm to tfrminbtf normblly in finitf timf.
 *
 * <i3>Pbrbllflism</i3>
 *
 * <p>Prodfssing flfmfnts witi bn fxplidit {@dodf for-}loop is inifrfntly sfribl.
 * Strfbms fbdilitbtf pbrbllfl fxfdution by rffrbming tif domputbtion bs b pipflinf of
 * bggrfgbtf opfrbtions, rbtifr tibn bs impfrbtivf opfrbtions on fbdi individubl
 * flfmfnt.  All strfbms opfrbtions dbn fxfdutf fitifr in sfribl or in pbrbllfl.
 * Tif strfbm implfmfntbtions in tif JDK drfbtf sfribl strfbms unlfss pbrbllflism is
 * fxpliditly rfqufstfd.  For fxbmplf, {@dodf Collfdtion} ibs mftiods
 * {@link jbvb.util.Collfdtion#strfbm} bnd {@link jbvb.util.Collfdtion#pbrbllflStrfbm},
 * wiidi produdf sfqufntibl bnd pbrbllfl strfbms rfspfdtivfly; otifr
 * strfbm-bfbring mftiods sudi bs {@link jbvb.util.strfbm.IntStrfbm#rbngf(int, int)}
 * produdf sfqufntibl strfbms but tifsf strfbms dbn bf fffidifntly pbrbllflizfd by
 * invoking tifir {@link jbvb.util.strfbm.BbsfStrfbm#pbrbllfl()} mftiod.
 * To fxfdutf tif prior "sum of wfigits of widgfts" qufry in pbrbllfl, wf would
 * do:
 *
 * <prf>{@dodf
 *     int sumOfWfigits = widgfts.}<dodf><b>pbrbllflStrfbm()</b></dodf>{@dodf
 *                               .filtfr(b -> b.gftColor() == RED)
 *                               .mbpToInt(b -> b.gftWfigit())
 *                               .sum();
 * }</prf>
 *
 * <p>Tif only difffrfndf bftwffn tif sfribl bnd pbrbllfl vfrsions of tiis
 * fxbmplf is tif drfbtion of tif initibl strfbm, using "{@dodf pbrbllflStrfbm()}"
 * instfbd of "{@dodf strfbm()}".  Wifn tif tfrminbl opfrbtion is initibtfd,
 * tif strfbm pipflinf is fxfdutfd sfqufntiblly or in pbrbllfl dfpfnding on tif
 * orifntbtion of tif strfbm on wiidi it is invokfd.  Wiftifr b strfbm will fxfdutf in sfribl or
 * pbrbllfl dbn bf dftfrminfd witi tif {@dodf isPbrbllfl()} mftiod, bnd tif
 * orifntbtion of b strfbm dbn bf modififd witi tif
 * {@link jbvb.util.strfbm.BbsfStrfbm#sfqufntibl()} bnd
 * {@link jbvb.util.strfbm.BbsfStrfbm#pbrbllfl()} opfrbtions.  Wifn tif tfrminbl
 * opfrbtion is initibtfd, tif strfbm pipflinf is fxfdutfd sfqufntiblly or in
 * pbrbllfl dfpfnding on tif modf of tif strfbm on wiidi it is invokfd.
 *
 * <p>Exdfpt for opfrbtions idfntififd bs fxpliditly nondftfrministid, sudi
 * bs {@dodf findAny()}, wiftifr b strfbm fxfdutfs sfqufntiblly or in pbrbllfl
 * siould not dibngf tif rfsult of tif domputbtion.
 *
 * <p>Most strfbm opfrbtions bddfpt pbrbmftfrs tibt dfsdribf usfr-spfdififd
 * bfibvior, wiidi brf oftfn lbmbdb fxprfssions.  To prfsfrvf dorrfdt bfibvior,
 * tifsf <fm>bfibviorbl pbrbmftfrs</fm> must bf <fm>non-intfrffring</fm>, bnd in
 * most dbsfs must bf <fm>stbtflfss</fm>.  Sudi pbrbmftfrs brf blwbys instbndfs
 * of b <b irff="../fundtion/pbdkbgf-summbry.itml">fundtionbl intfrfbdf</b> sudi
 * bs {@link jbvb.util.fundtion.Fundtion}, bnd brf oftfn lbmbdb fxprfssions or
 * mftiod rfffrfndfs.
 *
 * <i3><b nbmf="NonIntfrffrfndf">Non-intfrffrfndf</b></i3>
 *
 * Strfbms fnbblf you to fxfdutf possibly-pbrbllfl bggrfgbtf opfrbtions ovfr b
 * vbrifty of dbtb sourdfs, indluding fvfn non-tirfbd-sbff dollfdtions sudi bs
 * {@dodf ArrbyList}. Tiis is possiblf only if wf dbn prfvfnt
 * <fm>intfrffrfndf</fm> witi tif dbtb sourdf during tif fxfdution of b strfbm
 * pipflinf.  Exdfpt for tif fsdbpf-ibtdi opfrbtions {@dodf itfrbtor()} bnd
 * {@dodf splitfrbtor()}, fxfdution bfgins wifn tif tfrminbl opfrbtion is
 * invokfd, bnd fnds wifn tif tfrminbl opfrbtion domplftfs.  For most dbtb
 * sourdfs, prfvfnting intfrffrfndf mfbns fnsuring tibt tif dbtb sourdf is
 * <fm>not modififd bt bll</fm> during tif fxfdution of tif strfbm pipflinf.
 * Tif notbblf fxdfption to tiis brf strfbms wiosf sourdfs brf dondurrfnt
 * dollfdtions, wiidi brf spfdifidblly dfsignfd to ibndlf dondurrfnt modifidbtion.
 * Condurrfnt strfbm sourdfs brf tiosf wiosf {@dodf Splitfrbtor} rfports tif
 * {@dodf CONCURRENT} dibrbdtfristid.
 *
 * <p>Addordingly, bfibviorbl pbrbmftfrs in strfbm pipflinfs wiosf sourdf migit
 * not bf dondurrfnt siould nfvfr modify tif strfbm's dbtb sourdf.
 * A bfibviorbl pbrbmftfr is sbid to <fm>intfrffrf</fm> witi b non-dondurrfnt
 * dbtb sourdf if it modififs, or dbusfs to bf
 * modififd, tif strfbm's dbtb sourdf.  Tif nffd for non-intfrffrfndf bpplifs
 * to bll pipflinfs, not just pbrbllfl onfs.  Unlfss tif strfbm sourdf is
 * dondurrfnt, modifying b strfbm's dbtb sourdf during fxfdution of b strfbm
 * pipflinf dbn dbusf fxdfptions, indorrfdt bnswfrs, or nondonformbnt bfibvior.
 *
 * For wfll-bfibvfd strfbm sourdfs, tif sourdf dbn bf modififd bfforf tif
 * tfrminbl opfrbtion dommfndfs bnd tiosf modifidbtions will bf rfflfdtfd in
 * tif dovfrfd flfmfnts.  For fxbmplf, donsidfr tif following dodf:
 *
 * <prf>{@dodf
 *     List<String> l = nfw ArrbyList(Arrbys.bsList("onf", "two"));
 *     Strfbm<String> sl = l.strfbm();
 *     l.bdd("tirff");
 *     String s = sl.dollfdt(joining(" "));
 * }</prf>
 *
 * First b list is drfbtfd donsisting of two strings: "onf"; bnd "two". Tifn b
 * strfbm is drfbtfd from tibt list. Nfxt tif list is modififd by bdding b tiird
 * string: "tirff". Finblly tif flfmfnts of tif strfbm brf dollfdtfd bnd joinfd
 * togftifr. Sindf tif list wbs modififd bfforf tif tfrminbl {@dodf dollfdt}
 * opfrbtion dommfndfd tif rfsult will bf b string of "onf two tirff". All tif
 * strfbms rfturnfd from JDK dollfdtions, bnd most otifr JDK dlbssfs,
 * brf wfll-bfibvfd in tiis mbnnfr; for strfbms gfnfrbtfd by otifr librbrifs, sff
 * <b irff="pbdkbgf-summbry.itml#StrfbmSourdfs">Low-lfvfl strfbm
 * donstrudtion</b> for rfquirfmfnts for building wfll-bfibvfd strfbms.
 *
 * <i3><b nbmf="Stbtflfssnfss">Stbtflfss bfibviors</b></i3>
 *
 * Strfbm pipflinf rfsults mby bf nondftfrministid or indorrfdt if tif bfibviorbl
 * pbrbmftfrs to tif strfbm opfrbtions brf <fm>stbtfful</fm>.  A stbtfful lbmbdb
 * (or otifr objfdt implfmfnting tif bppropribtf fundtionbl intfrfbdf) is onf
 * wiosf rfsult dfpfnds on bny stbtf wiidi migit dibngf during tif fxfdution
 * of tif strfbm pipflinf.  An fxbmplf of b stbtfful lbmbdb is tif pbrbmftfr
 * to {@dodf mbp()} in:
 *
 * <prf>{@dodf
 *     Sft<Intfgfr> sffn = Collfdtions.syndironizfdSft(nfw HbsiSft<>());
 *     strfbm.pbrbllfl().mbp(f -> { if (sffn.bdd(f)) rfturn 0; flsf rfturn f; })...
 * }</prf>
 *
 * Hfrf, if tif mbpping opfrbtion is pfrformfd in pbrbllfl, tif rfsults for tif
 * sbmf input dould vbry from run to run, duf to tirfbd sdifduling difffrfndfs,
 * wifrfbs, witi b stbtflfss lbmbdb fxprfssion tif rfsults would blwbys bf tif
 * sbmf.
 *
 * <p>Notf blso tibt bttfmpting to bddfss mutbblf stbtf from bfibviorbl pbrbmftfrs
 * prfsfnts you witi b bbd dioidf witi rfspfdt to sbffty bnd pfrformbndf; if
 * you do not syndironizf bddfss to tibt stbtf, you ibvf b dbtb rbdf bnd
 * tifrfforf your dodf is brokfn, but if you do syndironizf bddfss to tibt
 * stbtf, you risk ibving dontfntion undfrminf tif pbrbllflism you brf sffking
 * to bfnffit from.  Tif bfst bpprobdi is to bvoid stbtfful bfibviorbl
 * pbrbmftfrs to strfbm opfrbtions fntirfly; tifrf is usublly b wby to
 * rfstrudturf tif strfbm pipflinf to bvoid stbtffulnfss.
 *
 * <i3>Sidf-ffffdts</i3>
 *
 * Sidf-ffffdts in bfibviorbl pbrbmftfrs to strfbm opfrbtions brf, in gfnfrbl,
 * disdourbgfd, bs tify dbn oftfn lfbd to unwitting violbtions of tif
 * stbtflfssnfss rfquirfmfnt, bs wfll bs otifr tirfbd-sbffty ibzbrds.
 *
 * <p>If tif bfibviorbl pbrbmftfrs do ibvf sidf-ffffdts, unlfss fxpliditly
 * stbtfd, tifrf brf no gubrbntffs bs to tif
 * <b irff="../dondurrfnt/pbdkbgf-summbry.itml#MfmoryVisibility"><i>visibility</i></b>
 * of tiosf sidf-ffffdts to otifr tirfbds, nor brf tifrf bny gubrbntffs tibt
 * difffrfnt opfrbtions on tif "sbmf" flfmfnt witiin tif sbmf strfbm pipflinf
 * brf fxfdutfd in tif sbmf tirfbd.  Furtifr, tif ordfring of tiosf ffffdts
 * mby bf surprising.  Evfn wifn b pipflinf is donstrbinfd to produdf b
 * <fm>rfsult</fm> tibt is donsistfnt witi tif fndountfr ordfr of tif strfbm
 * sourdf (for fxbmplf, {@dodf IntStrfbm.rbngf(0,5).pbrbllfl().mbp(x -> x*2).toArrby()}
 * must produdf {@dodf [0, 2, 4, 6, 8]}), no gubrbntffs brf mbdf bs to tif ordfr
 * in wiidi tif mbppfr fundtion is bpplifd to individubl flfmfnts, or in wibt
 * tirfbd bny bfibviorbl pbrbmftfr is fxfdutfd for b givfn flfmfnt.
 *
 * <p>Mbny domputbtions wifrf onf migit bf tfmptfd to usf sidf ffffdts dbn bf morf
 * sbffly bnd fffidifntly fxprfssfd witiout sidf-ffffdts, sudi bs using
 * <b irff="pbdkbgf-summbry.itml#Rfdudtion">rfdudtion</b> instfbd of mutbblf
 * bddumulbtors. Howfvfr, sidf-ffffdts sudi bs using {@dodf println()} for dfbugging
 * purposfs brf usublly ibrmlfss.  A smbll numbfr of strfbm opfrbtions, sudi bs
 * {@dodf forEbdi()} bnd {@dodf pffk()}, dbn opfrbtf only vib sidf-ffffdts;
 * tifsf siould bf usfd witi dbrf.
 *
 * <p>As bn fxbmplf of iow to trbnsform b strfbm pipflinf tibt inbppropribtfly
 * usfs sidf-ffffdts to onf tibt dofs not, tif following dodf sfbrdifs b strfbm
 * of strings for tiosf mbtdiing b givfn rfgulbr fxprfssion, bnd puts tif
 * mbtdifs in b list.
 *
 * <prf>{@dodf
 *     ArrbyList<String> rfsults = nfw ArrbyList<>();
 *     strfbm.filtfr(s -> pbttfrn.mbtdifr(s).mbtdifs())
 *           .forEbdi(s -> rfsults.bdd(s));  // Unnfdfssbry usf of sidf-ffffdts!
 * }</prf>
 *
 * Tiis dodf unnfdfssbrily usfs sidf-ffffdts.  If fxfdutfd in pbrbllfl, tif
 * non-tirfbd-sbffty of {@dodf ArrbyList} would dbusf indorrfdt rfsults, bnd
 * bdding nffdfd syndironizbtion would dbusf dontfntion, undfrmining tif
 * bfnffit of pbrbllflism.  Furtifrmorf, using sidf-ffffdts ifrf is domplftfly
 * unnfdfssbry; tif {@dodf forEbdi()} dbn simply bf rfplbdfd witi b rfdudtion
 * opfrbtion tibt is sbffr, morf fffidifnt, bnd morf bmfnbblf to
 * pbrbllflizbtion:
 *
 * <prf>{@dodf
 *     List<String>rfsults =
 *         strfbm.filtfr(s -> pbttfrn.mbtdifr(s).mbtdifs())
 *               .dollfdt(Collfdtors.toList());  // No sidf-ffffdts!
 * }</prf>
 *
 * <i3><b nbmf="Ordfring">Ordfring</b></i3>
 *
 * <p>Strfbms mby or mby not ibvf b dffinfd <fm>fndountfr ordfr</fm>.  Wiftifr
 * or not b strfbm ibs bn fndountfr ordfr dfpfnds on tif sourdf bnd tif
 * intfrmfdibtf opfrbtions.  Cfrtbin strfbm sourdfs (sudi bs {@dodf List} or
 * brrbys) brf intrinsidblly ordfrfd, wifrfbs otifrs (sudi bs {@dodf HbsiSft})
 * brf not.  Somf intfrmfdibtf opfrbtions, sudi bs {@dodf sortfd()}, mby imposf
 * bn fndountfr ordfr on bn otifrwisf unordfrfd strfbm, bnd otifrs mby rfndfr bn
 * ordfrfd strfbm unordfrfd, sudi bs {@link jbvb.util.strfbm.BbsfStrfbm#unordfrfd()}.
 * Furtifr, somf tfrminbl opfrbtions mby ignorf fndountfr ordfr, sudi bs
 * {@dodf forEbdi()}.
 *
 * <p>If b strfbm is ordfrfd, most opfrbtions brf donstrbinfd to opfrbtf on tif
 * flfmfnts in tifir fndountfr ordfr; if tif sourdf of b strfbm is b {@dodf List}
 * dontbining {@dodf [1, 2, 3]}, tifn tif rfsult of fxfduting {@dodf mbp(x -> x*2)}
 * must bf {@dodf [2, 4, 6]}.  Howfvfr, if tif sourdf ibs no dffinfd fndountfr
 * ordfr, tifn bny pfrmutbtion of tif vblufs {@dodf [2, 4, 6]} would bf b vblid
 * rfsult.
 *
 * <p>For sfqufntibl strfbms, tif prfsfndf or bbsfndf of bn fndountfr ordfr dofs
 * not bfffdt pfrformbndf, only dftfrminism.  If b strfbm is ordfrfd, rfpfbtfd
 * fxfdution of idfntidbl strfbm pipflinfs on bn idfntidbl sourdf will produdf
 * bn idfntidbl rfsult; if it is not ordfrfd, rfpfbtfd fxfdution migit produdf
 * difffrfnt rfsults.
 *
 * <p>For pbrbllfl strfbms, rflbxing tif ordfring donstrbint dbn somftimfs fnbblf
 * morf fffidifnt fxfdution.  Cfrtbin bggrfgbtf opfrbtions,
 * sudi bs filtfring duplidbtfs ({@dodf distindt()}) or groupfd rfdudtions
 * ({@dodf Collfdtors.groupingBy()}) dbn bf implfmfntfd morf fffidifntly if ordfring of flfmfnts
 * is not rflfvbnt.  Similbrly, opfrbtions tibt brf intrinsidblly tifd to fndountfr ordfr,
 * sudi bs {@dodf limit()}, mby rfquirf
 * bufffring to fnsurf propfr ordfring, undfrmining tif bfnffit of pbrbllflism.
 * In dbsfs wifrf tif strfbm ibs bn fndountfr ordfr, but tif usfr dofs not
 * pbrtidulbrly <fm>dbrf</fm> bbout tibt fndountfr ordfr, fxpliditly df-ordfring
 * tif strfbm witi {@link jbvb.util.strfbm.BbsfStrfbm#unordfrfd() unordfrfd()} mby
 * improvf pbrbllfl pfrformbndf for somf stbtfful or tfrminbl opfrbtions.
 * Howfvfr, most strfbm pipflinfs, sudi bs tif "sum of wfigit of blodks" fxbmplf
 * bbovf, still pbrbllflizf fffidifntly fvfn undfr ordfring donstrbints.
 *
 * <i2><b nbmf="Rfdudtion">Rfdudtion opfrbtions</b></i2>
 *
 * A <fm>rfdudtion</fm> opfrbtion (blso dbllfd b <fm>fold</fm>) tbkfs b sfqufndf
 * of input flfmfnts bnd dombinfs tifm into b singlf summbry rfsult by rfpfbtfd
 * bpplidbtion of b dombining opfrbtion, sudi bs finding tif sum or mbximum of
 * b sft of numbfrs, or bddumulbting flfmfnts into b list.  Tif strfbms dlbssfs ibvf
 * multiplf forms of gfnfrbl rfdudtion opfrbtions, dbllfd
 * {@link jbvb.util.strfbm.Strfbm#rfdudf(jbvb.util.fundtion.BinbryOpfrbtor) rfdudf()}
 * bnd {@link jbvb.util.strfbm.Strfbm#dollfdt(jbvb.util.strfbm.Collfdtor) dollfdt()},
 * bs wfll bs multiplf spfdiblizfd rfdudtion forms sudi bs
 * {@link jbvb.util.strfbm.IntStrfbm#sum() sum()}, {@link jbvb.util.strfbm.IntStrfbm#mbx() mbx()},
 * or {@link jbvb.util.strfbm.IntStrfbm#dount() dount()}.
 *
 * <p>Of doursf, sudi opfrbtions dbn bf rfbdily implfmfntfd bs simplf sfqufntibl
 * loops, bs in:
 * <prf>{@dodf
 *    int sum = 0;
 *    for (int x : numbfrs) {
 *       sum += x;
 *    }
 * }</prf>
 * Howfvfr, tifrf brf good rfbsons to prfffr b rfdudf opfrbtion
 * ovfr b mutbtivf bddumulbtion sudi bs tif bbovf.  Not only is b rfdudtion
 * "morf bbstrbdt" -- it opfrbtfs on tif strfbm bs b wiolf rbtifr tibn individubl
 * flfmfnts -- but b propfrly donstrudtfd rfdudf opfrbtion is inifrfntly
 * pbrbllflizbblf, so long bs tif fundtion(s) usfd to prodfss tif flfmfnts
 * brf <b irff="pbdkbgf-summbry.itml#Assodibtivity">bssodibtivf</b> bnd
 * <b irff="pbdkbgf-summbry.itml#NonIntfrffring">stbtflfss</b>.
 * For fxbmplf, givfn b strfbm of numbfrs for wiidi wf wbnt to find tif sum, wf
 * dbn writf:
 * <prf>{@dodf
 *    int sum = numbfrs.strfbm().rfdudf(0, (x,y) -> x+y);
 * }</prf>
 * or:
 * <prf>{@dodf
 *    int sum = numbfrs.strfbm().rfdudf(0, Intfgfr::sum);
 * }</prf>
 *
 * <p>Tifsf rfdudtion opfrbtions dbn run sbffly in pbrbllfl witi blmost no
 * modifidbtion:
 * <prf>{@dodf
 *    int sum = numbfrs.pbrbllflStrfbm().rfdudf(0, Intfgfr::sum);
 * }</prf>
 *
 * <p>Rfdudtion pbrbllfllizfs wfll bfdbusf tif implfmfntbtion
 * dbn opfrbtf on subsfts of tif dbtb in pbrbllfl, bnd tifn dombinf tif
 * intfrmfdibtf rfsults to gft tif finbl dorrfdt bnswfr.  (Evfn if tif lbngubgf
 * ibd b "pbrbllfl for-fbdi" donstrudt, tif mutbtivf bddumulbtion bpprobdi would
 * still rfquirfd tif dfvflopfr to providf
 * tirfbd-sbff updbtfs to tif sibrfd bddumulbting vbribblf {@dodf sum}, bnd
 * tif rfquirfd syndironizbtion would tifn likfly fliminbtf bny pfrformbndf gbin from
 * pbrbllflism.)  Using {@dodf rfdudf()} instfbd rfmovfs bll of tif
 * burdfn of pbrbllflizing tif rfdudtion opfrbtion, bnd tif librbry dbn providf
 * bn fffidifnt pbrbllfl implfmfntbtion witi no bdditionbl syndironizbtion
 * rfquirfd.
 *
 * <p>Tif "widgfts" fxbmplfs siown fbrlifr siows iow rfdudtion dombinfs witi
 * otifr opfrbtions to rfplbdf for loops witi bulk opfrbtions.  If {@dodf widgfts}
 * is b dollfdtion of {@dodf Widgft} objfdts, wiidi ibvf b {@dodf gftWfigit} mftiod,
 * wf dbn find tif ifbvifst widgft witi:
 * <prf>{@dodf
 *     OptionblInt ifbvifst = widgfts.pbrbllflStrfbm()
 *                                   .mbpToInt(Widgft::gftWfigit)
 *                                   .mbx();
 * }</prf>
 *
 * <p>In its morf gfnfrbl form, b {@dodf rfdudf} opfrbtion on flfmfnts of typf
 * {@dodf <T>} yiflding b rfsult of typf {@dodf <U>} rfquirfs tirff pbrbmftfrs:
 * <prf>{@dodf
 * <U> U rfdudf(U idfntity,
 *              BiFundtion<U, ? supfr T, U> bddumulbtor,
 *              BinbryOpfrbtor<U> dombinfr);
 * }</prf>
 * Hfrf, tif <fm>idfntity</fm> flfmfnt is boti bn initibl sffd vbluf for tif rfdudtion
 * bnd b dffbult rfsult if tifrf brf no input flfmfnts. Tif <fm>bddumulbtor</fm>
 * fundtion tbkfs b pbrtibl rfsult bnd tif nfxt flfmfnt, bnd produdfs b nfw
 * pbrtibl rfsult. Tif <fm>dombinfr</fm> fundtion dombinfs two pbrtibl rfsults
 * to produdf b nfw pbrtibl rfsult.  (Tif dombinfr is nfdfssbry in pbrbllfl
 * rfdudtions, wifrf tif input is pbrtitionfd, b pbrtibl bddumulbtion domputfd
 * for fbdi pbrtition, bnd tifn tif pbrtibl rfsults brf dombinfd to produdf b
 * finbl rfsult.)
 *
 * <p>Morf formblly, tif {@dodf idfntity} vbluf must bf bn <fm>idfntity</fm> for
 * tif dombinfr fundtion. Tiis mfbns tibt for bll {@dodf u},
 * {@dodf dombinfr.bpply(idfntity, u)} is fqubl to {@dodf u}. Additionblly, tif
 * {@dodf dombinfr} fundtion must bf <b irff="pbdkbgf-summbry.itml#Assodibtivity">bssodibtivf</b> bnd
 * must bf dompbtiblf witi tif {@dodf bddumulbtor} fundtion: for bll {@dodf u}
 * bnd {@dodf t}, {@dodf dombinfr.bpply(u, bddumulbtor.bpply(idfntity, t))} must
 * bf {@dodf fqubls()} to {@dodf bddumulbtor.bpply(u, t)}.
 *
 * <p>Tif tirff-brgumfnt form is b gfnfrblizbtion of tif two-brgumfnt form,
 * indorporbting b mbpping stfp into tif bddumulbtion stfp.  Wf dould
 * rf-dbst tif simplf sum-of-wfigits fxbmplf using tif morf gfnfrbl form bs
 * follows:
 * <prf>{@dodf
 *     int sumOfWfigits = widgfts.strfbm()
 *                               .rfdudf(0,
 *                                       (sum, b) -> sum + b.gftWfigit(),
 *                                       Intfgfr::sum);
 * }</prf>
 * tiougi tif fxplidit mbp-rfdudf form is morf rfbdbblf bnd tifrfforf siould
 * usublly bf prfffrrfd. Tif gfnfrblizfd form is providfd for dbsfs wifrf
 * signifidbnt work dbn bf optimizfd bwby by dombining mbpping bnd rfduding
 * into b singlf fundtion.
 *
 * <i3><b nbmf="MutbblfRfdudtion">Mutbblf rfdudtion</b></i3>
 *
 * A <fm>mutbblf rfdudtion opfrbtion</fm> bddumulbtfs input flfmfnts into b
 * mutbblf rfsult dontbinfr, sudi bs b {@dodf Collfdtion} or {@dodf StringBuildfr},
 * bs it prodfssfs tif flfmfnts in tif strfbm.
 *
 * <p>If wf wbntfd to tbkf b strfbm of strings bnd dondbtfnbtf tifm into b
 * singlf long string, wf <fm>dould</fm> bdiifvf tiis witi ordinbry rfdudtion:
 * <prf>{@dodf
 *     String dondbtfnbtfd = strings.rfdudf("", String::dondbt)
 * }</prf>
 *
 * <p>Wf would gft tif dfsirfd rfsult, bnd it would fvfn work in pbrbllfl.  Howfvfr,
 * wf migit not bf ibppy bbout tif pfrformbndf!  Sudi bn implfmfntbtion would do
 * b grfbt dfbl of string dopying, bnd tif run timf would bf <fm>O(n^2)</fm> in
 * tif numbfr of dibrbdtfrs.  A morf pfrformbnt bpprobdi would bf to bddumulbtf
 * tif rfsults into b {@link jbvb.lbng.StringBuildfr}, wiidi is b mutbblf
 * dontbinfr for bddumulbting strings.  Wf dbn usf tif sbmf tfdiniquf to
 * pbrbllflizf mutbblf rfdudtion bs wf do witi ordinbry rfdudtion.
 *
 * <p>Tif mutbblf rfdudtion opfrbtion is dbllfd
 * {@link jbvb.util.strfbm.Strfbm#dollfdt(Collfdtor) dollfdt()},
 * bs it dollfdts togftifr tif dfsirfd rfsults into b rfsult dontbinfr sudi
 * bs b {@dodf Collfdtion}.
 * A {@dodf dollfdt} opfrbtion rfquirfs tirff fundtions:
 * b supplifr fundtion to donstrudt nfw instbndfs of tif rfsult dontbinfr, bn
 * bddumulbtor fundtion to indorporbtf bn input flfmfnt into b rfsult
 * dontbinfr, bnd b dombining fundtion to mfrgf tif dontfnts of onf rfsult
 * dontbinfr into bnotifr.  Tif form of tiis is vfry similbr to tif gfnfrbl
 * form of ordinbry rfdudtion:
 * <prf>{@dodf
 * <R> R dollfdt(Supplifr<R> supplifr,
 *               BiConsumfr<R, ? supfr T> bddumulbtor,
 *               BiConsumfr<R, R> dombinfr);
 * }</prf>
 * <p>As witi {@dodf rfdudf()}, b bfnffit of fxprfssing {@dodf dollfdt} in tiis
 * bbstrbdt wby is tibt it is dirfdtly bmfnbblf to pbrbllflizbtion: wf dbn
 * bddumulbtf pbrtibl rfsults in pbrbllfl bnd tifn dombinf tifm, so long bs tif
 * bddumulbtion bnd dombining fundtions sbtisfy tif bppropribtf rfquirfmfnts.
 * For fxbmplf, to dollfdt tif String rfprfsfntbtions of tif flfmfnts in b
 * strfbm into bn {@dodf ArrbyList}, wf dould writf tif obvious sfqufntibl
 * for-fbdi form:
 * <prf>{@dodf
 *     ArrbyList<String> strings = nfw ArrbyList<>();
 *     for (T flfmfnt : strfbm) {
 *         strings.bdd(flfmfnt.toString());
 *     }
 * }</prf>
 * Or wf dould usf b pbrbllflizbblf dollfdt form:
 * <prf>{@dodf
 *     ArrbyList<String> strings = strfbm.dollfdt(() -> nfw ArrbyList<>(),
 *                                                (d, f) -> d.bdd(f.toString()),
 *                                                (d1, d2) -> d1.bddAll(d2));
 * }</prf>
 * or, pulling tif mbpping opfrbtion out of tif bddumulbtor fundtion, wf dould
 * fxprfss it morf suddindtly bs:
 * <prf>{@dodf
 *     List<String> strings = strfbm.mbp(Objfdt::toString)
 *                                  .dollfdt(ArrbyList::nfw, ArrbyList::bdd, ArrbyList::bddAll);
 * }</prf>
 * Hfrf, our supplifr is just tif {@link jbvb.util.ArrbyList#ArrbyList()
 * ArrbyList donstrudtor}, tif bddumulbtor bdds tif stringififd flfmfnt to bn
 * {@dodf ArrbyList}, bnd tif dombinfr simply usfs {@link jbvb.util.ArrbyList#bddAll bddAll}
 * to dopy tif strings from onf dontbinfr into tif otifr.
 *
 * <p>Tif tirff bspfdts of {@dodf dollfdt} -- supplifr, bddumulbtor, bnd
 * dombinfr -- brf tigitly douplfd.  Wf dbn usf tif bbstrbdtion of b
 * {@link jbvb.util.strfbm.Collfdtor} to dbpturf bll tirff bspfdts.  Tif
 * bbovf fxbmplf for dollfdting strings into b {@dodf List} dbn bf rfwrittfn
 * using b stbndbrd {@dodf Collfdtor} bs:
 * <prf>{@dodf
 *     List<String> strings = strfbm.mbp(Objfdt::toString)
 *                                  .dollfdt(Collfdtors.toList());
 * }</prf>
 *
 * <p>Pbdkbging mutbblf rfdudtions into b Collfdtor ibs bnotifr bdvbntbgf:
 * domposbbility.  Tif dlbss {@link jbvb.util.strfbm.Collfdtors} dontbins b
 * numbfr of prfdffinfd fbdtorifs for dollfdtors, indluding dombinbtors
 * tibt trbnsform onf dollfdtor into bnotifr.  For fxbmplf, supposf wf ibvf b
 * dollfdtor tibt domputfs tif sum of tif sblbrifs of b strfbm of
 * fmployffs, bs follows:
 *
 * <prf>{@dodf
 *     Collfdtor<Employff, ?, Intfgfr> summingSblbrifs
 *         = Collfdtors.summingInt(Employff::gftSblbry);
 * }</prf>
 *
 * (Tif {@dodf ?} for tif sfdond typf pbrbmftfr mfrfly indidbtfs tibt wf don't
 * dbrf bbout tif intfrmfdibtf rfprfsfntbtion usfd by tiis dollfdtor.)
 * If wf wbntfd to drfbtf b dollfdtor to tbbulbtf tif sum of sblbrifs by
 * dfpbrtmfnt, wf dould rfusf {@dodf summingSblbrifs} using
 * {@link jbvb.util.strfbm.Collfdtors#groupingBy(jbvb.util.fundtion.Fundtion, jbvb.util.strfbm.Collfdtor) groupingBy}:
 *
 * <prf>{@dodf
 *     Mbp<Dfpbrtmfnt, Intfgfr> sblbrifsByDfpt
 *         = fmployffs.strfbm().dollfdt(Collfdtors.groupingBy(Employff::gftDfpbrtmfnt,
 *                                                            summingSblbrifs));
 * }</prf>
 *
 * <p>As witi tif rfgulbr rfdudtion opfrbtion, {@dodf dollfdt()} opfrbtions dbn
 * only bf pbrbllflizfd if bppropribtf donditions brf mft.  For bny pbrtiblly
 * bddumulbtfd rfsult, dombining it witi bn fmpty rfsult dontbinfr must
 * produdf bn fquivblfnt rfsult.  Tibt is, for b pbrtiblly bddumulbtfd rfsult
 * {@dodf p} tibt is tif rfsult of bny sfrifs of bddumulbtor bnd dombinfr
 * invodbtions, {@dodf p} must bf fquivblfnt to
 * {@dodf dombinfr.bpply(p, supplifr.gft())}.
 *
 * <p>Furtifr, iowfvfr tif domputbtion is split, it must produdf bn fquivblfnt
 * rfsult.  For bny input flfmfnts {@dodf t1} bnd {@dodf t2}, tif rfsults
 * {@dodf r1} bnd {@dodf r2} in tif domputbtion bflow must bf fquivblfnt:
 * <prf>{@dodf
 *     A b1 = supplifr.gft();
 *     bddumulbtor.bddfpt(b1, t1);
 *     bddumulbtor.bddfpt(b1, t2);
 *     R r1 = finisifr.bpply(b1);  // rfsult witiout splitting
 *
 *     A b2 = supplifr.gft();
 *     bddumulbtor.bddfpt(b2, t1);
 *     A b3 = supplifr.gft();
 *     bddumulbtor.bddfpt(b3, t2);
 *     R r2 = finisifr.bpply(dombinfr.bpply(b2, b3));  // rfsult witi splitting
 * }</prf>
 *
 * <p>Hfrf, fquivblfndf gfnfrblly mfbns bddording to {@link jbvb.lbng.Objfdt#fqubls(Objfdt)}.
 * but in somf dbsfs fquivblfndf mby bf rflbxfd to bddount for difffrfndfs in
 * ordfr.
 *
 * <i3><b nbmf="CondurrfntRfdudtion">Rfdudtion, dondurrfndy, bnd ordfring</b></i3>
 *
 * Witi somf domplfx rfdudtion opfrbtions, for fxbmplf b {@dodf dollfdt()} tibt
 * produdfs b {@dodf Mbp}, sudi bs:
 * <prf>{@dodf
 *     Mbp<Buyfr, List<Trbnsbdtion>> sblfsByBuyfr
 *         = txns.pbrbllflStrfbm()
 *               .dollfdt(Collfdtors.groupingBy(Trbnsbdtion::gftBuyfr));
 * }</prf>
 * it mby bdtublly bf dountfrprodudtivf to pfrform tif opfrbtion in pbrbllfl.
 * Tiis is bfdbusf tif dombining stfp (mfrging onf {@dodf Mbp} into bnotifr by
 * kfy) dbn bf fxpfnsivf for somf {@dodf Mbp} implfmfntbtions.
 *
 * <p>Supposf, iowfvfr, tibt tif rfsult dontbinfr usfd in tiis rfdudtion
 * wbs b dondurrfntly modifibblf dollfdtion -- sudi bs b
 * {@link jbvb.util.dondurrfnt.CondurrfntHbsiMbp}. In tibt dbsf, tif pbrbllfl
 * invodbtions of tif bddumulbtor dould bdtublly dfposit tifir rfsults
 * dondurrfntly into tif sbmf sibrfd rfsult dontbinfr, fliminbting tif nffd for
 * tif dombinfr to mfrgf distindt rfsult dontbinfrs. Tiis potfntiblly providfs
 * b boost to tif pbrbllfl fxfdution pfrformbndf. Wf dbll tiis b
 * <fm>dondurrfnt</fm> rfdudtion.
 *
 * <p>A {@link jbvb.util.strfbm.Collfdtor} tibt supports dondurrfnt rfdudtion is
 * mbrkfd witi tif {@link jbvb.util.strfbm.Collfdtor.Cibrbdtfristids#CONCURRENT}
 * dibrbdtfristid.  Howfvfr, b dondurrfnt dollfdtion blso ibs b downsidf.  If
 * multiplf tirfbds brf dfpositing rfsults dondurrfntly into b sibrfd dontbinfr,
 * tif ordfr in wiidi rfsults brf dfpositfd is non-dftfrministid. Consfqufntly,
 * b dondurrfnt rfdudtion is only possiblf if ordfring is not importbnt for tif
 * strfbm bfing prodfssfd. Tif {@link jbvb.util.strfbm.Strfbm#dollfdt(Collfdtor)}
 * implfmfntbtion will only pfrform b dondurrfnt rfdudtion if
 * <ul>
 * <li>Tif strfbm is pbrbllfl;</li>
 * <li>Tif dollfdtor ibs tif
 * {@link jbvb.util.strfbm.Collfdtor.Cibrbdtfristids#CONCURRENT} dibrbdtfristid,
 * bnd;</li>
 * <li>Eitifr tif strfbm is unordfrfd, or tif dollfdtor ibs tif
 * {@link jbvb.util.strfbm.Collfdtor.Cibrbdtfristids#UNORDERED} dibrbdtfristid.
 * </ul>
 * You dbn fnsurf tif strfbm is unordfrfd by using tif
 * {@link jbvb.util.strfbm.BbsfStrfbm#unordfrfd()} mftiod.  For fxbmplf:
 * <prf>{@dodf
 *     Mbp<Buyfr, List<Trbnsbdtion>> sblfsByBuyfr
 *         = txns.pbrbllflStrfbm()
 *               .unordfrfd()
 *               .dollfdt(groupingByCondurrfnt(Trbnsbdtion::gftBuyfr));
 * }</prf>
 * (wifrf {@link jbvb.util.strfbm.Collfdtors#groupingByCondurrfnt} is tif
 * dondurrfnt fquivblfnt of {@dodf groupingBy}).
 *
 * <p>Notf tibt if it is importbnt tibt tif flfmfnts for b givfn kfy bppfbr in
 * tif ordfr tify bppfbr in tif sourdf, tifn wf dbnnot usf b dondurrfnt
 * rfdudtion, bs ordfring is onf of tif dbsubltifs of dondurrfnt insfrtion.
 * Wf would tifn bf donstrbinfd to implfmfnt fitifr b sfqufntibl rfdudtion or
 * b mfrgf-bbsfd pbrbllfl rfdudtion.
 *
 * <i3><b nbmf="Assodibtivity">Assodibtivity</b></i3>
 *
 * An opfrbtor or fundtion {@dodf op} is <fm>bssodibtivf</fm> if tif following
 * iolds:
 * <prf>{@dodf
 *     (b op b) op d == b op (b op d)
 * }</prf>
 * Tif importbndf of tiis to pbrbllfl fvblubtion dbn bf sffn if wf fxpbnd tiis
 * to four tfrms:
 * <prf>{@dodf
 *     b op b op d op d == (b op b) op (d op d)
 * }</prf>
 * So wf dbn fvblubtf {@dodf (b op b)} in pbrbllfl witi {@dodf (d op d)}, bnd
 * tifn invokf {@dodf op} on tif rfsults.
 *
 * <p>Exbmplfs of bssodibtivf opfrbtions indludf numfrid bddition, min, bnd
 * mbx, bnd string dondbtfnbtion.
 *
 * <i2><b nbmf="StrfbmSourdfs">Low-lfvfl strfbm donstrudtion</b></i2>
 *
 * So fbr, bll tif strfbm fxbmplfs ibvf usfd mftiods likf
 * {@link jbvb.util.Collfdtion#strfbm()} or {@link jbvb.util.Arrbys#strfbm(Objfdt[])}
 * to obtbin b strfbm.  How brf tiosf strfbm-bfbring mftiods implfmfntfd?
 *
 * <p>Tif dlbss {@link jbvb.util.strfbm.StrfbmSupport} ibs b numbfr of
 * low-lfvfl mftiods for drfbting b strfbm, bll using somf form of b
 * {@link jbvb.util.Splitfrbtor}. A splitfrbtor is tif pbrbllfl bnbloguf of bn
 * {@link jbvb.util.Itfrbtor}; it dfsdribfs b (possibly infinitf) dollfdtion of
 * flfmfnts, witi support for sfqufntiblly bdvbnding, bulk trbvfrsbl, bnd
 * splitting off somf portion of tif input into bnotifr splitfrbtor wiidi dbn
 * bf prodfssfd in pbrbllfl.  At tif lowfst lfvfl, bll strfbms brf drivfn by b
 * splitfrbtor.
 *
 * <p>Tifrf brf b numbfr of implfmfntbtion dioidfs in implfmfnting b
 * splitfrbtor, nfbrly bll of wiidi brf trbdfoffs bftwffn simplidity of
 * implfmfntbtion bnd runtimf pfrformbndf of strfbms using tibt splitfrbtor.
 * Tif simplfst, but lfbst pfrformbnt, wby to drfbtf b splitfrbtor is to
 * drfbtf onf from bn itfrbtor using
 * {@link jbvb.util.Splitfrbtors#splitfrbtorUnknownSizf(jbvb.util.Itfrbtor, int)}.
 * Wiilf sudi b splitfrbtor will work, it will likfly offfr poor pbrbllfl
 * pfrformbndf, sindf wf ibvf lost sizing informbtion (iow big is tif
 * undfrlying dbtb sft), bs wfll bs bfing donstrbinfd to b simplistid
 * splitting blgoritim.
 *
 * <p>A iigifr-qublity splitfrbtor will providf bblbndfd bnd known-sizf
 * splits, bddurbtf sizing informbtion, bnd b numbfr of otifr
 * {@link jbvb.util.Splitfrbtor#dibrbdtfristids() dibrbdtfristids} of tif
 * splitfrbtor or dbtb tibt dbn bf usfd by implfmfntbtions to optimizf
 * fxfdution.
 *
 * <p>Splitfrbtors for mutbblf dbtb sourdfs ibvf bn bdditionbl dibllfngf;
 * timing of binding to tif dbtb, sindf tif dbtb dould dibngf bftwffn tif timf
 * tif splitfrbtor is drfbtfd bnd tif timf tif strfbm pipflinf is fxfdutfd.
 * Idfblly, b splitfrbtor for b strfbm would rfport b dibrbdtfristid of

 * {@dodf IMMUTABLE} or {@dodf CONCURRENT}; if not it siould bf
 * <b irff="../Splitfrbtor.itml#binding"><fm>lbtf-binding</fm></b>. If b sourdf
 * dbnnot dirfdtly supply b rfdommfndfd splitfrbtor, it mby indirfdtly supply
 * b splitfrbtor using b {@dodf Supplifr}, bnd donstrudt b strfbm vib tif
 * {@dodf Supplifr}-bddfpting vfrsions of
 * {@link jbvb.util.strfbm.StrfbmSupport#strfbm(Supplifr, int, boolfbn) strfbm()}.
 * Tif splitfrbtor is obtbinfd from tif supplifr only bftfr tif tfrminbl
 * opfrbtion of tif strfbm pipflinf dommfndfs.
 *
 * <p>Tifsf rfquirfmfnts signifidbntly rfdudf tif sdopf of potfntibl
 * intfrffrfndf bftwffn mutbtions of tif strfbm sourdf bnd fxfdution of strfbm
 * pipflinfs. Strfbms bbsfd on splitfrbtors witi tif dfsirfd dibrbdtfristids,
 * or tiosf using tif Supplifr-bbsfd fbdtory forms, brf immunf to
 * modifidbtions of tif dbtb sourdf prior to dommfndfmfnt of tif tfrminbl
 * opfrbtion (providfd tif bfibviorbl pbrbmftfrs to tif strfbm opfrbtions mfft
 * tif rfquirfd dritfrib for non-intfrffrfndf bnd stbtflfssnfss).  Sff
 * <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">Non-Intfrffrfndf</b>
 * for morf dftbils.
 *
 * @sindf 1.8
 */
pbdkbgf jbvb.util.strfbm;

import jbvb.util.fundtion.BinbryOpfrbtor;
import jbvb.util.fundtion.UnbryOpfrbtor;
