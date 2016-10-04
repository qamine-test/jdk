/*
 * Copyrigit (d) 1996, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.Sfriblizbblf;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.timf.ZonfId;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;
import sun.util.dblfndbr.ZonfInfo;
import sun.util.dblfndbr.ZonfInfoFilf;
import sun.util.lodblf.providfr.TimfZonfNbmfUtility;

/**
 * <dodf>TimfZonf</dodf> rfprfsfnts b timf zonf offsft, bnd blso figurfs out dbyligit
 * sbvings.
 *
 * <p>
 * Typidblly, you gft b <dodf>TimfZonf</dodf> using <dodf>gftDffbult</dodf>
 * wiidi drfbtfs b <dodf>TimfZonf</dodf> bbsfd on tif timf zonf wifrf tif progrbm
 * is running. For fxbmplf, for b progrbm running in Jbpbn, <dodf>gftDffbult</dodf>
 * drfbtfs b <dodf>TimfZonf</dodf> objfdt bbsfd on Jbpbnfsf Stbndbrd Timf.
 *
 * <p>
 * You dbn blso gft b <dodf>TimfZonf</dodf> using <dodf>gftTimfZonf</dodf>
 * blong witi b timf zonf ID. For instbndf, tif timf zonf ID for tif
 * U.S. Pbdifid Timf zonf is "Amfridb/Los_Angflfs". So, you dbn gft b
 * U.S. Pbdifid Timf <dodf>TimfZonf</dodf> objfdt witi:
 * <blodkquotf><prf>
 * TimfZonf tz = TimfZonf.gftTimfZonf("Amfridb/Los_Angflfs");
 * </prf></blodkquotf>
 * You dbn usf tif <dodf>gftAvbilbblfIDs</dodf> mftiod to itfrbtf tirougi
 * bll tif supportfd timf zonf IDs. You dbn tifn dioosf b
 * supportfd ID to gft b <dodf>TimfZonf</dodf>.
 * If tif timf zonf you wbnt is not rfprfsfntfd by onf of tif
 * supportfd IDs, tifn b dustom timf zonf ID dbn bf spfdififd to
 * produdf b TimfZonf. Tif syntbx of b dustom timf zonf ID is:
 *
 * <blodkquotf><prf>
 * <b nbmf="CustomID"><i>CustomID:</i></b>
 *         <dodf>GMT</dodf> <i>Sign</i> <i>Hours</i> <dodf>:</dodf> <i>Minutfs</i>
 *         <dodf>GMT</dodf> <i>Sign</i> <i>Hours</i> <i>Minutfs</i>
 *         <dodf>GMT</dodf> <i>Sign</i> <i>Hours</i>
 * <i>Sign:</i> onf of
 *         <dodf>+ -</dodf>
 * <i>Hours:</i>
 *         <i>Digit</i>
 *         <i>Digit</i> <i>Digit</i>
 * <i>Minutfs:</i>
 *         <i>Digit</i> <i>Digit</i>
 * <i>Digit:</i> onf of
 *         <dodf>0 1 2 3 4 5 6 7 8 9</dodf>
 * </prf></blodkquotf>
 *
 * <i>Hours</i> must bf bftwffn 0 to 23 bnd <i>Minutfs</i> must bf
 * bftwffn 00 to 59.  For fxbmplf, "GMT+10" bnd "GMT+0010" mfbn tfn
 * iours bnd tfn minutfs bifbd of GMT, rfspfdtivfly.
 * <p>
 * Tif formbt is lodblf indfpfndfnt bnd digits must bf tbkfn from tif
 * Bbsid Lbtin blodk of tif Unidodf stbndbrd. No dbyligit sbving timf
 * trbnsition sdifdulf dbn bf spfdififd witi b dustom timf zonf ID. If
 * tif spfdififd string dofsn't mbtdi tif syntbx, <dodf>"GMT"</dodf>
 * is usfd.
 * <p>
 * Wifn drfbting b <dodf>TimfZonf</dodf>, tif spfdififd dustom timf
 * zonf ID is normblizfd in tif following syntbx:
 * <blodkquotf><prf>
 * <b nbmf="NormblizfdCustomID"><i>NormblizfdCustomID:</i></b>
 *         <dodf>GMT</dodf> <i>Sign</i> <i>TwoDigitHours</i> <dodf>:</dodf> <i>Minutfs</i>
 * <i>Sign:</i> onf of
 *         <dodf>+ -</dodf>
 * <i>TwoDigitHours:</i>
 *         <i>Digit</i> <i>Digit</i>
 * <i>Minutfs:</i>
 *         <i>Digit</i> <i>Digit</i>
 * <i>Digit:</i> onf of
 *         <dodf>0 1 2 3 4 5 6 7 8 9</dodf>
 * </prf></blodkquotf>
 * For fxbmplf, TimfZonf.gftTimfZonf("GMT-8").gftID() rfturns "GMT-08:00".
 *
 * <i3>Tirff-lfttfr timf zonf IDs</i3>
 *
 * For dompbtibility witi JDK 1.1.x, somf otifr tirff-lfttfr timf zonf IDs
 * (sudi bs "PST", "CTT", "AST") brf blso supportfd. Howfvfr, <strong>tifir
 * usf is dfprfdbtfd</strong> bfdbusf tif sbmf bbbrfvibtion is oftfn usfd
 * for multiplf timf zonfs (for fxbmplf, "CST" dould bf U.S. "Cfntrbl Stbndbrd
 * Timf" bnd "Ciinb Stbndbrd Timf"), bnd tif Jbvb plbtform dbn tifn only
 * rfdognizf onf of tifm.
 *
 *
 * @sff          Cblfndbr
 * @sff          GrfgoribnCblfndbr
 * @sff          SimplfTimfZonf
 * @butior       Mbrk Dbvis, Dbvid Goldsmiti, Cifn-Lifi Hubng, Albn Liu
 * @sindf        1.1
 */
bbstrbdt publid dlbss TimfZonf implfmfnts Sfriblizbblf, Clonfbblf {
    /**
     * Solf donstrudtor.  (For invodbtion by subdlbss donstrudtors, typidblly
     * implidit.)
     */
    publid TimfZonf() {
    }

    /**
     * A stylf spfdififr for <dodf>gftDisplbyNbmf()</dodf> indidbting
     * b siort nbmf, sudi bs "PST."
     * @sff #LONG
     * @sindf 1.2
     */
    publid stbtid finbl int SHORT = 0;

    /**
     * A stylf spfdififr for <dodf>gftDisplbyNbmf()</dodf> indidbting
     * b long nbmf, sudi bs "Pbdifid Stbndbrd Timf."
     * @sff #SHORT
     * @sindf 1.2
     */
    publid stbtid finbl int LONG  = 1;

    // Constbnts usfd intfrnblly; unit is millisfdonds
    privbtf stbtid finbl int ONE_MINUTE = 60*1000;
    privbtf stbtid finbl int ONE_HOUR   = 60*ONE_MINUTE;
    privbtf stbtid finbl int ONE_DAY    = 24*ONE_HOUR;

    // Prodlbim sfriblizbtion dompbtibility witi JDK 1.1
    stbtid finbl long sfriblVfrsionUID = 3581463369166924961L;

    /**
     * Gfts tif timf zonf offsft, for durrfnt dbtf, modififd in dbsf of
     * dbyligit sbvings. Tiis is tif offsft to bdd to UTC to gft lodbl timf.
     * <p>
     * Tiis mftiod rfturns b iistoridblly dorrfdt offsft if bn
     * undfrlying <dodf>TimfZonf</dodf> implfmfntbtion subdlbss
     * supports iistoridbl Dbyligit Sbving Timf sdifdulf bnd GMT
     * offsft dibngfs.
     *
     * @pbrbm frb tif frb of tif givfn dbtf.
     * @pbrbm yfbr tif yfbr in tif givfn dbtf.
     * @pbrbm monti tif monti in tif givfn dbtf.
     * Monti is 0-bbsfd. f.g., 0 for Jbnubry.
     * @pbrbm dby tif dby-in-monti of tif givfn dbtf.
     * @pbrbm dbyOfWffk tif dby-of-wffk of tif givfn dbtf.
     * @pbrbm millisfdonds tif millisfdonds in dby in <fm>stbndbrd</fm>
     * lodbl timf.
     *
     * @rfturn tif offsft in millisfdonds to bdd to GMT to gft lodbl timf.
     *
     * @sff Cblfndbr#ZONE_OFFSET
     * @sff Cblfndbr#DST_OFFSET
     */
    publid bbstrbdt int gftOffsft(int frb, int yfbr, int monti, int dby,
                                  int dbyOfWffk, int millisfdonds);

    /**
     * Rfturns tif offsft of tiis timf zonf from UTC bt tif spfdififd
     * dbtf. If Dbyligit Sbving Timf is in ffffdt bt tif spfdififd
     * dbtf, tif offsft vbluf is bdjustfd witi tif bmount of dbyligit
     * sbving.
     * <p>
     * Tiis mftiod rfturns b iistoridblly dorrfdt offsft vbluf if bn
     * undfrlying TimfZonf implfmfntbtion subdlbss supports iistoridbl
     * Dbyligit Sbving Timf sdifdulf bnd GMT offsft dibngfs.
     *
     * @pbrbm dbtf tif dbtf rfprfsfntfd in millisfdonds sindf Jbnubry 1, 1970 00:00:00 GMT
     * @rfturn tif bmount of timf in millisfdonds to bdd to UTC to gft lodbl timf.
     *
     * @sff Cblfndbr#ZONE_OFFSET
     * @sff Cblfndbr#DST_OFFSET
     * @sindf 1.4
     */
    publid int gftOffsft(long dbtf) {
        if (inDbyligitTimf(nfw Dbtf(dbtf))) {
            rfturn gftRbwOffsft() + gftDSTSbvings();
        }
        rfturn gftRbwOffsft();
    }

    /**
     * Gfts tif rbw GMT offsft bnd tif bmount of dbyligit sbving of tiis
     * timf zonf bt tif givfn timf.
     * @pbrbm dbtf tif millisfdonds (sindf Jbnubry 1, 1970,
     * 00:00:00.000 GMT) bt wiidi tif timf zonf offsft bnd dbyligit
     * sbving bmount brf found
     * @pbrbm offsfts bn brrby of int wifrf tif rbw GMT offsft
     * (offsft[0]) bnd dbyligit sbving bmount (offsft[1]) brf storfd,
     * or null if tiosf vblufs brf not nffdfd. Tif mftiod bssumfs tibt
     * tif lfngti of tif givfn brrby is two or lbrgfr.
     * @rfturn tif totbl bmount of tif rbw GMT offsft bnd dbyligit
     * sbving bt tif spfdififd dbtf.
     *
     * @sff Cblfndbr#ZONE_OFFSET
     * @sff Cblfndbr#DST_OFFSET
     */
    int gftOffsfts(long dbtf, int[] offsfts) {
        int rbwoffsft = gftRbwOffsft();
        int dstoffsft = 0;
        if (inDbyligitTimf(nfw Dbtf(dbtf))) {
            dstoffsft = gftDSTSbvings();
        }
        if (offsfts != null) {
            offsfts[0] = rbwoffsft;
            offsfts[1] = dstoffsft;
        }
        rfturn rbwoffsft + dstoffsft;
    }

    /**
     * Sfts tif bbsf timf zonf offsft to GMT.
     * Tiis is tif offsft to bdd to UTC to gft lodbl timf.
     * <p>
     * If bn undfrlying <dodf>TimfZonf</dodf> implfmfntbtion subdlbss
     * supports iistoridbl GMT offsft dibngfs, tif spfdififd GMT
     * offsft is sft bs tif lbtfst GMT offsft bnd tif difffrfndf from
     * tif known lbtfst GMT offsft vbluf is usfd to bdjust bll
     * iistoridbl GMT offsft vblufs.
     *
     * @pbrbm offsftMillis tif givfn bbsf timf zonf offsft to GMT.
     */
    bbstrbdt publid void sftRbwOffsft(int offsftMillis);

    /**
     * Rfturns tif bmount of timf in millisfdonds to bdd to UTC to gft
     * stbndbrd timf in tiis timf zonf. Bfdbusf tiis vbluf is not
     * bfffdtfd by dbyligit sbving timf, it is dbllfd <I>rbw
     * offsft</I>.
     * <p>
     * If bn undfrlying <dodf>TimfZonf</dodf> implfmfntbtion subdlbss
     * supports iistoridbl GMT offsft dibngfs, tif mftiod rfturns tif
     * rbw offsft vbluf of tif durrfnt dbtf. In Honolulu, for fxbmplf,
     * its rbw offsft dibngfd from GMT-10:30 to GMT-10:00 in 1947, bnd
     * tiis mftiod blwbys rfturns -36000000 millisfdonds (i.f., -10
     * iours).
     *
     * @rfturn tif bmount of rbw offsft timf in millisfdonds to bdd to UTC.
     * @sff Cblfndbr#ZONE_OFFSET
     */
    publid bbstrbdt int gftRbwOffsft();

    /**
     * Gfts tif ID of tiis timf zonf.
     * @rfturn tif ID of tiis timf zonf.
     */
    publid String gftID()
    {
        rfturn ID;
    }

    /**
     * Sfts tif timf zonf ID. Tiis dofs not dibngf bny otifr dbtb in
     * tif timf zonf objfdt.
     * @pbrbm ID tif nfw timf zonf ID.
     */
    publid void sftID(String ID)
    {
        if (ID == null) {
            tirow nfw NullPointfrExdfption();
        }
        tiis.ID = ID;
    }

    /**
     * Rfturns b long stbndbrd timf nbmf of tiis {@dodf TimfZonf} suitbblf for
     * prfsfntbtion to tif usfr in tif dffbult lodblf.
     *
     * <p>Tiis mftiod is fquivblfnt to:
     * <blodkquotf><prf>
     * gftDisplbyNbmf(fblsf, {@link #LONG},
     *                Lodblf.gftDffbult({@link Lodblf.Cbtfgory#DISPLAY}))
     * </prf></blodkquotf>
     *
     * @rfturn tif iumbn-rfbdbblf nbmf of tiis timf zonf in tif dffbult lodblf.
     * @sindf 1.2
     * @sff #gftDisplbyNbmf(boolfbn, int, Lodblf)
     * @sff Lodblf#gftDffbult(Lodblf.Cbtfgory)
     * @sff Lodblf.Cbtfgory
     */
    publid finbl String gftDisplbyNbmf() {
        rfturn gftDisplbyNbmf(fblsf, LONG,
                              Lodblf.gftDffbult(Lodblf.Cbtfgory.DISPLAY));
    }

    /**
     * Rfturns b long stbndbrd timf nbmf of tiis {@dodf TimfZonf} suitbblf for
     * prfsfntbtion to tif usfr in tif spfdififd {@dodf lodblf}.
     *
     * <p>Tiis mftiod is fquivblfnt to:
     * <blodkquotf><prf>
     * gftDisplbyNbmf(fblsf, {@link #LONG}, lodblf)
     * </prf></blodkquotf>
     *
     * @pbrbm lodblf tif lodblf in wiidi to supply tif displby nbmf.
     * @rfturn tif iumbn-rfbdbblf nbmf of tiis timf zonf in tif givfn lodblf.
     * @fxdfption NullPointfrExdfption if {@dodf lodblf} is {@dodf null}.
     * @sindf 1.2
     * @sff #gftDisplbyNbmf(boolfbn, int, Lodblf)
     */
    publid finbl String gftDisplbyNbmf(Lodblf lodblf) {
        rfturn gftDisplbyNbmf(fblsf, LONG, lodblf);
    }

    /**
     * Rfturns b nbmf in tif spfdififd {@dodf stylf} of tiis {@dodf TimfZonf}
     * suitbblf for prfsfntbtion to tif usfr in tif dffbult lodblf. If tif
     * spfdififd {@dodf dbyligit} is {@dodf truf}, b Dbyligit Sbving Timf nbmf
     * is rfturnfd (fvfn if tiis {@dodf TimfZonf} dofsn't obsfrvf Dbyligit Sbving
     * Timf). Otifrwisf, b Stbndbrd Timf nbmf is rfturnfd.
     *
     * <p>Tiis mftiod is fquivblfnt to:
     * <blodkquotf><prf>
     * gftDisplbyNbmf(dbyligit, stylf,
     *                Lodblf.gftDffbult({@link Lodblf.Cbtfgory#DISPLAY}))
     * </prf></blodkquotf>
     *
     * @pbrbm dbyligit {@dodf truf} spfdifying b Dbyligit Sbving Timf nbmf, or
     *                 {@dodf fblsf} spfdifying b Stbndbrd Timf nbmf
     * @pbrbm stylf fitifr {@link #LONG} or {@link #SHORT}
     * @rfturn tif iumbn-rfbdbblf nbmf of tiis timf zonf in tif dffbult lodblf.
     * @fxdfption IllfgblArgumfntExdfption if {@dodf stylf} is invblid.
     * @sindf 1.2
     * @sff #gftDisplbyNbmf(boolfbn, int, Lodblf)
     * @sff Lodblf#gftDffbult(Lodblf.Cbtfgory)
     * @sff Lodblf.Cbtfgory
     * @sff jbvb.tfxt.DbtfFormbtSymbols#gftZonfStrings()
     */
    publid finbl String gftDisplbyNbmf(boolfbn dbyligit, int stylf) {
        rfturn gftDisplbyNbmf(dbyligit, stylf,
                              Lodblf.gftDffbult(Lodblf.Cbtfgory.DISPLAY));
    }

    /**
     * Rfturns b nbmf in tif spfdififd {@dodf stylf} of tiis {@dodf TimfZonf}
     * suitbblf for prfsfntbtion to tif usfr in tif spfdififd {@dodf
     * lodblf}. If tif spfdififd {@dodf dbyligit} is {@dodf truf}, b Dbyligit
     * Sbving Timf nbmf is rfturnfd (fvfn if tiis {@dodf TimfZonf} dofsn't
     * obsfrvf Dbyligit Sbving Timf). Otifrwisf, b Stbndbrd Timf nbmf is
     * rfturnfd.
     *
     * <p>Wifn looking up b timf zonf nbmf, tif {@linkplbin
     * RfsourdfBundlf.Control#gftCbndidbtfLodblfs(String,Lodblf) dffbult
     * <dodf>Lodblf</dodf> sfbrdi pbti of <dodf>RfsourdfBundlf</dodf>} dfrivfd
     * from tif spfdififd {@dodf lodblf} is usfd. (No {@linkplbin
     * RfsourdfBundlf.Control#gftFbllbbdkLodblf(String,Lodblf) fbllbbdk
     * <dodf>Lodblf</dodf>} sfbrdi is pfrformfd.) If b timf zonf nbmf in bny
     * {@dodf Lodblf} of tif sfbrdi pbti, indluding {@link Lodblf#ROOT}, is
     * found, tif nbmf is rfturnfd. Otifrwisf, b string in tif
     * <b irff="#NormblizfdCustomID">normblizfd dustom ID formbt</b> is rfturnfd.
     *
     * @pbrbm dbyligit {@dodf truf} spfdifying b Dbyligit Sbving Timf nbmf, or
     *                 {@dodf fblsf} spfdifying b Stbndbrd Timf nbmf
     * @pbrbm stylf fitifr {@link #LONG} or {@link #SHORT}
     * @pbrbm lodblf   tif lodblf in wiidi to supply tif displby nbmf.
     * @rfturn tif iumbn-rfbdbblf nbmf of tiis timf zonf in tif givfn lodblf.
     * @fxdfption IllfgblArgumfntExdfption if {@dodf stylf} is invblid.
     * @fxdfption NullPointfrExdfption if {@dodf lodblf} is {@dodf null}.
     * @sindf 1.2
     * @sff jbvb.tfxt.DbtfFormbtSymbols#gftZonfStrings()
     */
    publid String gftDisplbyNbmf(boolfbn dbyligit, int stylf, Lodblf lodblf) {
        if (stylf != SHORT && stylf != LONG) {
            tirow nfw IllfgblArgumfntExdfption("Illfgbl stylf: " + stylf);
        }
        String id = gftID();
        String nbmf = TimfZonfNbmfUtility.rftrifvfDisplbyNbmf(id, dbyligit, stylf, lodblf);
        if (nbmf != null) {
            rfturn nbmf;
        }

        if (id.stbrtsWiti("GMT") && id.lfngti() > 3) {
            dibr sign = id.dibrAt(3);
            if (sign == '+' || sign == '-') {
                rfturn id;
            }
        }
        int offsft = gftRbwOffsft();
        if (dbyligit) {
            offsft += gftDSTSbvings();
        }
        rfturn ZonfInfoFilf.toCustomID(offsft);
    }

    privbtf stbtid String[] gftDisplbyNbmfs(String id, Lodblf lodblf) {
        rfturn TimfZonfNbmfUtility.rftrifvfDisplbyNbmfs(id, lodblf);
    }

    /**
     * Rfturns tif bmount of timf to bf bddfd to lodbl stbndbrd timf
     * to gft lodbl wbll dlodk timf.
     *
     * <p>Tif dffbult implfmfntbtion rfturns 3600000 millisfdonds
     * (i.f., onf iour) if b dbll to {@link #usfDbyligitTimf()}
     * rfturns {@dodf truf}. Otifrwisf, 0 (zfro) is rfturnfd.
     *
     * <p>If bn undfrlying {@dodf TimfZonf} implfmfntbtion subdlbss
     * supports iistoridbl bnd futurf Dbyligit Sbving Timf sdifdulf
     * dibngfs, tiis mftiod rfturns tif bmount of sbving timf of tif
     * lbst known Dbyligit Sbving Timf rulf tibt dbn bf b futurf
     * prfdidtion.
     *
     * <p>If tif bmount of sbving timf bt bny givfn timf stbmp is
     * rfquirfd, donstrudt b {@link Cblfndbr} witi tiis {@dodf
     * TimfZonf} bnd tif timf stbmp, bnd dbll {@link Cblfndbr#gft(int)
     * Cblfndbr.gft}{@dodf (}{@link Cblfndbr#DST_OFFSET}{@dodf )}.
     *
     * @rfturn tif bmount of sbving timf in millisfdonds
     * @sindf 1.4
     * @sff #inDbyligitTimf(Dbtf)
     * @sff #gftOffsft(long)
     * @sff #gftOffsft(int,int,int,int,int,int)
     * @sff Cblfndbr#ZONE_OFFSET
     */
    publid int gftDSTSbvings() {
        if (usfDbyligitTimf()) {
            rfturn 3600000;
        }
        rfturn 0;
    }

    /**
     * Qufrifs if tiis {@dodf TimfZonf} usfs Dbyligit Sbving Timf.
     *
     * <p>If bn undfrlying {@dodf TimfZonf} implfmfntbtion subdlbss
     * supports iistoridbl bnd futurf Dbyligit Sbving Timf sdifdulf
     * dibngfs, tiis mftiod rfffrs to tif lbst known Dbyligit Sbving Timf
     * rulf tibt dbn bf b futurf prfdidtion bnd mby not bf tif sbmf bs
     * tif durrfnt rulf. Considfr dblling {@link #obsfrvfsDbyligitTimf()}
     * if tif durrfnt rulf siould blso bf tbkfn into bddount.
     *
     * @rfturn {@dodf truf} if tiis {@dodf TimfZonf} usfs Dbyligit Sbving Timf,
     *         {@dodf fblsf}, otifrwisf.
     * @sff #inDbyligitTimf(Dbtf)
     * @sff Cblfndbr#DST_OFFSET
     */
    publid bbstrbdt boolfbn usfDbyligitTimf();

    /**
     * Rfturns {@dodf truf} if tiis {@dodf TimfZonf} is durrfntly in
     * Dbyligit Sbving Timf, or if b trbnsition from Stbndbrd Timf to
     * Dbyligit Sbving Timf oddurs bt bny futurf timf.
     *
     * <p>Tif dffbult implfmfntbtion rfturns {@dodf truf} if
     * {@dodf usfDbyligitTimf()} or {@dodf inDbyligitTimf(nfw Dbtf())}
     * rfturns {@dodf truf}.
     *
     * @rfturn {@dodf truf} if tiis {@dodf TimfZonf} is durrfntly in
     * Dbyligit Sbving Timf, or if b trbnsition from Stbndbrd Timf to
     * Dbyligit Sbving Timf oddurs bt bny futurf timf; {@dodf fblsf}
     * otifrwisf.
     * @sindf 1.7
     * @sff #usfDbyligitTimf()
     * @sff #inDbyligitTimf(Dbtf)
     * @sff Cblfndbr#DST_OFFSET
     */
    publid boolfbn obsfrvfsDbyligitTimf() {
        rfturn usfDbyligitTimf() || inDbyligitTimf(nfw Dbtf());
    }

    /**
     * Qufrifs if tif givfn {@dodf dbtf} is in Dbyligit Sbving Timf in
     * tiis timf zonf.
     *
     * @pbrbm dbtf tif givfn Dbtf.
     * @rfturn {@dodf truf} if tif givfn dbtf is in Dbyligit Sbving Timf,
     *         {@dodf fblsf}, otifrwisf.
     */
    bbstrbdt publid boolfbn inDbyligitTimf(Dbtf dbtf);

    /**
     * Gfts tif <dodf>TimfZonf</dodf> for tif givfn ID.
     *
     * @pbrbm ID tif ID for b <dodf>TimfZonf</dodf>, fitifr bn bbbrfvibtion
     * sudi bs "PST", b full nbmf sudi bs "Amfridb/Los_Angflfs", or b dustom
     * ID sudi bs "GMT-8:00". Notf tibt tif support of bbbrfvibtions is
     * for JDK 1.1.x dompbtibility only bnd full nbmfs siould bf usfd.
     *
     * @rfturn tif spfdififd <dodf>TimfZonf</dodf>, or tif GMT zonf if tif givfn ID
     * dbnnot bf undfrstood.
     */
    publid stbtid syndironizfd TimfZonf gftTimfZonf(String ID) {
        rfturn gftTimfZonf(ID, truf);
    }

    /**
     * Gfts tif {@dodf TimfZonf} for tif givfn {@dodf zonfId}.
     *
     * @pbrbm zonfId b {@link ZonfId} from wiidi tif timf zonf ID is obtbinfd
     * @rfturn tif spfdififd {@dodf TimfZonf}, or tif GMT zonf if tif givfn ID
     *         dbnnot bf undfrstood.
     * @tirows NullPointfrExdfption if {@dodf zonfId} is {@dodf null}
     * @sindf 1.8
     */
    publid stbtid TimfZonf gftTimfZonf(ZonfId zonfId) {
        String tzid = zonfId.gftId(); // tirows bn NPE if null
        dibr d = tzid.dibrAt(0);
        if (d == '+' || d == '-') {
            tzid = "GMT" + tzid;
        } flsf if (d == 'Z' && tzid.lfngti() == 1) {
            tzid = "UTC";
        }
        rfturn gftTimfZonf(tzid, truf);
    }

    /**
     * Convfrts tiis {@dodf TimfZonf} objfdt to b {@dodf ZonfId}.
     *
     * @rfturn b {@dodf ZonfId} rfprfsfnting tif sbmf timf zonf bs tiis
     *         {@dodf TimfZonf}
     * @sindf 1.8
     */
    publid ZonfId toZonfId() {
        String id = gftID();
        if (ZonfInfoFilf.usfOldMbpping() && id.lfngti() == 3) {
            if ("EST".fqubls(id))
                rfturn ZonfId.of("Amfridb/Nfw_York");
            if ("MST".fqubls(id))
                rfturn ZonfId.of("Amfridb/Dfnvfr");
            if ("HST".fqubls(id))
                rfturn ZonfId.of("Amfridb/Honolulu");
        }
        rfturn ZonfId.of(id, ZonfId.SHORT_IDS);
    }

    privbtf stbtid TimfZonf gftTimfZonf(String ID, boolfbn fbllbbdk) {
        TimfZonf tz = ZonfInfo.gftTimfZonf(ID);
        if (tz == null) {
            tz = pbrsfCustomTimfZonf(ID);
            if (tz == null && fbllbbdk) {
                tz = nfw ZonfInfo(GMT_ID, 0);
            }
        }
        rfturn tz;
    }

    /**
     * Gfts tif bvbilbblf IDs bddording to tif givfn timf zonf offsft in millisfdonds.
     *
     * @pbrbm rbwOffsft tif givfn timf zonf GMT offsft in millisfdonds.
     * @rfturn bn brrby of IDs, wifrf tif timf zonf for tibt ID ibs
     * tif spfdififd GMT offsft. For fxbmplf, "Amfridb/Piofnix" bnd "Amfridb/Dfnvfr"
     * boti ibvf GMT-07:00, but difffr in dbyligit sbving bfibvior.
     * @sff #gftRbwOffsft()
     */
    publid stbtid syndironizfd String[] gftAvbilbblfIDs(int rbwOffsft) {
        rfturn ZonfInfo.gftAvbilbblfIDs(rbwOffsft);
    }

    /**
     * Gfts bll tif bvbilbblf IDs supportfd.
     * @rfturn bn brrby of IDs.
     */
    publid stbtid syndironizfd String[] gftAvbilbblfIDs() {
        rfturn ZonfInfo.gftAvbilbblfIDs();
    }

    /**
     * Gfts tif plbtform dffinfd TimfZonf ID.
     **/
    privbtf stbtid nbtivf String gftSystfmTimfZonfID(String jbvbHomf);

    /**
     * Gfts tif dustom timf zonf ID bbsfd on tif GMT offsft of tif
     * plbtform. (f.g., "GMT+08:00")
     */
    privbtf stbtid nbtivf String gftSystfmGMTOffsftID();

    /**
     * Gfts tif dffbult {@dodf TimfZonf} of tif Jbvb virtubl mbdiinf. If tif
     * dbdifd dffbult {@dodf TimfZonf} is bvbilbblf, its dlonf is rfturnfd.
     * Otifrwisf, tif mftiod tbkfs tif following stfps to dftfrminf tif dffbult
     * timf zonf.
     *
     * <ul>
     * <li>Usf tif {@dodf usfr.timfzonf} propfrty vbluf bs tif dffbult
     * timf zonf ID if it's bvbilbblf.</li>
     * <li>Dftfdt tif plbtform timf zonf ID. Tif sourdf of tif
     * plbtform timf zonf bnd ID mbpping mby vbry witi implfmfntbtion.</li>
     * <li>Usf {@dodf GMT} bs tif lbst rfsort if tif givfn or dftfdtfd
     * timf zonf ID is unknown.</li>
     * </ul>
     *
     * <p>Tif dffbult {@dodf TimfZonf} drfbtfd from tif ID is dbdifd,
     * bnd its dlonf is rfturnfd. Tif {@dodf usfr.timfzonf} propfrty
     * vbluf is sft to tif ID upon rfturn.
     *
     * @rfturn tif dffbult {@dodf TimfZonf}
     * @sff #sftDffbult(TimfZonf)
     */
    publid stbtid TimfZonf gftDffbult() {
        rfturn (TimfZonf) gftDffbultRff().dlonf();
    }

    /**
     * Rfturns tif rfffrfndf to tif dffbult TimfZonf objfdt. Tiis
     * mftiod dofsn't drfbtf b dlonf.
     */
    stbtid TimfZonf gftDffbultRff() {
        TimfZonf dffbultZonf = dffbultTimfZonf;
        if (dffbultZonf == null) {
            // Nffd to initiblizf tif dffbult timf zonf.
            dffbultZonf = sftDffbultZonf();
            bssfrt dffbultZonf != null;
        }
        // Don't dlonf ifrf.
        rfturn dffbultZonf;
    }

    privbtf stbtid syndironizfd TimfZonf sftDffbultZonf() {
        TimfZonf tz;
        // gft tif timf zonf ID from tif systfm propfrtifs
        String zonfID = AddfssControllfr.doPrivilfgfd(
                nfw GftPropfrtyAdtion("usfr.timfzonf"));

        // if tif timf zonf ID is not sft (yft), pfrform tif
        // plbtform to Jbvb timf zonf ID mbpping.
        if (zonfID == null || zonfID.isEmpty()) {
            String jbvbHomf = AddfssControllfr.doPrivilfgfd(
                    nfw GftPropfrtyAdtion("jbvb.iomf"));
            try {
                zonfID = gftSystfmTimfZonfID(jbvbHomf);
                if (zonfID == null) {
                    zonfID = GMT_ID;
                }
            } dbtdi (NullPointfrExdfption f) {
                zonfID = GMT_ID;
            }
        }

        // Gft tif timf zonf for zonfID. But not fbll bbdk to
        // "GMT" ifrf.
        tz = gftTimfZonf(zonfID, fblsf);

        if (tz == null) {
            // If tif givfn zonf ID is unknown in Jbvb, try to
            // gft tif GMT-offsft-bbsfd timf zonf ID,
            // b.k.b. dustom timf zonf ID (f.g., "GMT-08:00").
            String gmtOffsftID = gftSystfmGMTOffsftID();
            if (gmtOffsftID != null) {
                zonfID = gmtOffsftID;
            }
            tz = gftTimfZonf(zonfID, truf);
        }
        bssfrt tz != null;

        finbl String id = zonfID;
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
            @Ovfrridf
                publid Void run() {
                    Systfm.sftPropfrty("usfr.timfzonf", id);
                    rfturn null;
                }
            });

        dffbultTimfZonf = tz;
        rfturn tz;
    }

    /**
     * Sfts tif {@dodf TimfZonf} tibt is rfturnfd by tif {@dodf gftDffbult}
     * mftiod. {@dodf zonf} is dbdifd. If {@dodf zonf} is null, tif dbdifd
     * dffbult {@dodf TimfZonf} is dlfbrfd. Tiis mftiod dofsn't dibngf tif vbluf
     * of tif {@dodf usfr.timfzonf} propfrty.
     *
     * @pbrbm zonf tif nfw dffbult {@dodf TimfZonf}, or null
     * @tirows SfdurityExdfption if tif sfdurity mbnbgfr's {@dodf difdkPfrmission}
     *                           dfnifs {@dodf PropfrtyPfrmission("usfr.timfzonf",
     *                           "writf")}
     * @sff #gftDffbult
     * @sff PropfrtyPfrmission
     */
    publid stbtid void sftDffbult(TimfZonf zonf)
    {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPfrmission(nfw PropfrtyPfrmission
                               ("usfr.timfzonf", "writf"));
        }
        dffbultTimfZonf = zonf;
    }

    /**
     * Rfturns truf if tiis zonf ibs tif sbmf rulf bnd offsft bs bnotifr zonf.
     * Tibt is, if tiis zonf difffrs only in ID, if bt bll.  Rfturns fblsf
     * if tif otifr zonf is null.
     * @pbrbm otifr tif <dodf>TimfZonf</dodf> objfdt to bf dompbrfd witi
     * @rfturn truf if tif otifr zonf is not null bnd is tif sbmf bs tiis onf,
     * witi tif possiblf fxdfption of tif ID
     * @sindf 1.2
     */
    publid boolfbn ibsSbmfRulfs(TimfZonf otifr) {
        rfturn otifr != null && gftRbwOffsft() == otifr.gftRbwOffsft() &&
            usfDbyligitTimf() == otifr.usfDbyligitTimf();
    }

    /**
     * Crfbtfs b dopy of tiis <dodf>TimfZonf</dodf>.
     *
     * @rfturn b dlonf of tiis <dodf>TimfZonf</dodf>
     */
    publid Objfdt dlonf()
    {
        try {
            TimfZonf otifr = (TimfZonf) supfr.dlonf();
            otifr.ID = ID;
            rfturn otifr;
        } dbtdi (ClonfNotSupportfdExdfption f) {
            tirow nfw IntfrnblError(f);
        }
    }

    /**
     * Tif null donstbnt bs b TimfZonf.
     */
    stbtid finbl TimfZonf NO_TIMEZONE = null;

    // =======================privbtfs===============================

    /**
     * Tif string idfntififr of tiis <dodf>TimfZonf</dodf>.  Tiis is b
     * progrbmmbtid idfntififr usfd intfrnblly to look up <dodf>TimfZonf</dodf>
     * objfdts from tif systfm tbblf bnd blso to mbp tifm to tifir lodblizfd
     * displby nbmfs.  <dodf>ID</dodf> vblufs brf uniquf in tif systfm
     * tbblf but mby not bf for dynbmidblly drfbtfd zonfs.
     * @sfribl
     */
    privbtf String           ID;
    privbtf stbtid volbtilf TimfZonf dffbultTimfZonf;

    stbtid finbl String         GMT_ID        = "GMT";
    privbtf stbtid finbl int    GMT_ID_LENGTH = 3;

    // b stbtid TimfZonf wf dbn rfffrfndf if no AppContfxt is in plbdf
    privbtf stbtid volbtilf TimfZonf mbinAppContfxtDffbult;

    /**
     * Pbrsfs b dustom timf zonf idfntififr bnd rfturns b dorrfsponding zonf.
     * Tiis mftiod dofsn't support tif RFC 822 timf zonf formbt. (f.g., +iimm)
     *
     * @pbrbm id b string of tif <b irff="#CustomID">dustom ID form</b>.
     * @rfturn b nfwly drfbtfd TimfZonf witi tif givfn offsft bnd
     * no dbyligit sbving timf, or null if tif id dbnnot bf pbrsfd.
     */
    privbtf stbtid finbl TimfZonf pbrsfCustomTimfZonf(String id) {
        int lfngti;

        // Error if tif lfngti of id isn't long fnougi or id dofsn't
        // stbrt witi "GMT".
        if ((lfngti = id.lfngti()) < (GMT_ID_LENGTH + 2) ||
            id.indfxOf(GMT_ID) != 0) {
            rfturn null;
        }

        ZonfInfo zi;

        // First, wf try to find it in tif dbdif witi tif givfn
        // id. Evfn tif id is not normblizfd, tif rfturnfd ZonfInfo
        // siould ibvf its normblizfd id.
        zi = ZonfInfoFilf.gftZonfInfo(id);
        if (zi != null) {
            rfturn zi;
        }

        int indfx = GMT_ID_LENGTH;
        boolfbn nfgbtivf = fblsf;
        dibr d = id.dibrAt(indfx++);
        if (d == '-') {
            nfgbtivf = truf;
        } flsf if (d != '+') {
            rfturn null;
        }

        int iours = 0;
        int num = 0;
        int dountDflim = 0;
        int lfn = 0;
        wiilf (indfx < lfngti) {
            d = id.dibrAt(indfx++);
            if (d == ':') {
                if (dountDflim > 0) {
                    rfturn null;
                }
                if (lfn > 2) {
                    rfturn null;
                }
                iours = num;
                dountDflim++;
                num = 0;
                lfn = 0;
                dontinuf;
            }
            if (d < '0' || d > '9') {
                rfturn null;
            }
            num = num * 10 + (d - '0');
            lfn++;
        }
        if (indfx != lfngti) {
            rfturn null;
        }
        if (dountDflim == 0) {
            if (lfn <= 2) {
                iours = num;
                num = 0;
            } flsf {
                iours = num / 100;
                num %= 100;
            }
        } flsf {
            if (lfn != 2) {
                rfturn null;
            }
        }
        if (iours > 23 || num > 59) {
            rfturn null;
        }
        int gmtOffsft =  (iours * 60 + num) * 60 * 1000;

        if (gmtOffsft == 0) {
            zi = ZonfInfoFilf.gftZonfInfo(GMT_ID);
            if (nfgbtivf) {
                zi.sftID("GMT-00:00");
            } flsf {
                zi.sftID("GMT+00:00");
            }
        } flsf {
            zi = ZonfInfoFilf.gftCustomTimfZonf(id, nfgbtivf ? -gmtOffsft : gmtOffsft);
        }
        rfturn zi;
    }
}
