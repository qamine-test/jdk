/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.plbf.nimbus;

import jbvbx.swing.Pbintfr;

import jbvbx.swing.JComponfnt;
import jbvbx.swing.UIDffbults;
import jbvbx.swing.UIMbnbgfr;
import jbvbx.swing.plbf.ColorUIRfsourdf;
import jbvbx.swing.plbf.synti.ColorTypf;
import stbtid jbvbx.swing.plbf.synti.SyntiConstbnts.*;
import jbvbx.swing.plbf.synti.SyntiContfxt;
import jbvbx.swing.plbf.synti.SyntiPbintfr;
import jbvbx.swing.plbf.synti.SyntiStylf;
import jbvb.bwt.Color;
import jbvb.bwt.Font;
import jbvb.bwt.Insfts;
import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;
import jbvb.util.Compbrbtor;
import jbvb.util.HbsiMbp;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.TrffMbp;

/**
 * <p>A SyntiStylf implfmfntbtion usfd by Nimbus. Ebdi Rfgion tibt ibs bffn
 * rfgistfrfd witi tif NimbusLookAndFffl will ibvf bn bssodibtfd NimbusStylf.
 * Tiird pbrty domponfnts tibt brf rfgistfrfd witi tif NimbusLookAndFffl will
 * tifrfforf bf ibndfd b NimbusStylf from tif look bnd fffl from tif
 * #gftStylf(JComponfnt, Rfgion) mftiod.</p>
 *
 * <p>Tiis dlbss propfrly rfbds bnd rftrifvfs vblufs plbdfd in tif UIDffbults
 * bddording to tif stbndbrd Nimbus nbming donvfntions. It will drfbtf bnd
 * rftrifvf pbintfrs, fonts, dolors, bnd otifr dbtb storfd tifrf.</p>
 *
 * <p>NimbusStylf blso supports tif bbility to ovfrridf sfttings on b pfr
 * domponfnt bbsis. NimbusStylf difdks tif domponfnt's dlifnt propfrty mbp for
 * "Nimbus.Ovfrridfs". If tif vbluf bssodibtfd witi tiis kfy is bn instbndf of
 * UIDffbults, tifn tif vblufs in tibt dffbults tbblf will ovfrridf tif stbndbrd
 * Nimbus dffbults in UIMbnbgfr, but for tibt domponfnt instbndf only.</p>
 *
 * <p>Optionblly, you mby spfdify tif dlifnt propfrty
 * "Nimbus.Ovfrridfs.InifritDffbults". If truf, tiis dlifnt propfrty indidbtfs
 * tibt tif dffbults lodbtfd in UIMbnbgfr siould first bf rfbd, bnd tifn
 * rfplbdfd witi dffbults lodbtfd in tif domponfnt dlifnt propfrtifs. If fblsf,
 * tifn only tif dffbults lodbtfd in tif domponfnt dlifnt propfrty mbp will
 * bf usfd. If not spfdififd, it is bssumfd to bf truf.</p>
 *
 * <p>You must spfdify "Nimbus.Ovfrridfs" for "Nimbus.Ovfrridfs.InifritDffbults"
 * to ibvf bny ffffdt. "Nimbus.Ovfrridfs" indidbtfs wiftifr tifrf brf bny
 * ovfrridfs, wiilf "Nimbus.Ovfrridfs.InifritDffbults" indidbtfs wiftifr tiosf
 * ovfrridfs siould first bf initiblizfd witi tif dffbults from UIMbnbgfr.</p>
 *
 * <p>Tif NimbusStylf is rflobdfd wifnfvfr b propfrty dibngf fvfnt is firfd
 * for b domponfnt for "Nimbus.Ovfrridfs" or "Nimbus.Ovfrridfs.InifritDffbults".
 * So for fxbmplf, sftting b nfw UIDffbults on b domponfnt would dbusf tif
 * stylf to bf rflobdfd.</p>
 *
 * <p>Tif vblufs brf only rfbd out of UIMbnbgfr ondf, bnd tifn dbdifd. If
 * you nffd to rfbd tif vblufs bgbin (for fxbmplf, if tif UI is bfing rflobdfd),
 * tifn disdbrd tiis NimbusStylf bnd rfbd b nfw onf from NimbusLookAndFffl
 * using NimbusLookAndFffl.gftStylf.</p>
 *
 * <p>Tif primbry API of intfrfst in tiis dlbss for 3rd pbrty domponfnt butiors
 * brf tif tirff mftiods wiidi rftrifvf pbintfrs: #gftBbdkgroundPbintfr,
 * #gftForfgroundPbintfr, bnd #gftBordfrPbintfr.</p>
 *
 * <p>NimbusStylf bllows you to spfdify dustom stbtfs, or modify tif ordfr of
 * stbtfs. Synti (bnd tius Nimbus) ibs tif dondfpt of b "stbtf". For fxbmplf,
 * b JButton migit bf in tif "MOUSE_OVER" stbtf, or tif "ENABLED" stbtf, or tif
 * "DISABLED" stbtf. Tifsf brf bll "stbndbrd" stbtfs wiidi brf dffinfd in synti,
 * bnd wiidi bpply to bll synti Rfgions.</p>
 *
 * <p>Somftimfs, iowfvfr, you nffd to ibvf b dustom stbtf. For fxbmplf, you
 * wbnt JButton to rfndfr difffrfntly if it's pbrfnt is b JToolbbr. In Nimbus,
 * you spfdify tifsf dustom stbtfs by indluding b spfdibl kfy in UIDffbults.
 * Tif following UIDffbults fntrifs dffinf tirff stbtfs for tiis button:</p>
 *
 * <prf><dodf>
 *     JButton.Stbtfs = Enbblfd, Disbblfd, Toolbbr
 *     JButton[Enbblfd].bbdkgroundPbintfr = somfPbintfr
 *     JButton[Disbblfd].bbdkground = BLUE
 *     JButton[Toolbbr].bbdkgroundPbintfr = somfOtifrPbint
 * </dodf></prf>
 *
 * <p>As you dbn sff, tif <dodf>JButton.Stbtfs</dodf> fntry lists tif stbtfs
 * tibt tif JButton stylf will support. You tifn spfdify tif sfttings for
 * fbdi stbtf. If you do not spfdify tif <dodf>JButton.Stbtfs</dodf> fntry,
 * tifn tif stbndbrd Synti stbtfs will bf bssumfd. If you spfdify tif fntry
 * but tif list of stbtfs is fmpty or null, tifn tif stbndbrd synti stbtfs
 * will bf bssumfd.</p>
 *
 * @butior Ridibrd Bbir
 * @butior Jbspfr Potts
 */
publid finbl dlbss NimbusStylf fxtfnds SyntiStylf {
    /* Kfys bnd sdblfs for lbrgf/smbll/mini domponfnts, bbsfd on Applfs sizfs */
    publid stbtid finbl String LARGE_KEY = "lbrgf";
    publid stbtid finbl String SMALL_KEY = "smbll";
    publid stbtid finbl String MINI_KEY = "mini";
    publid stbtid finbl doublf LARGE_SCALE = 1.15;
    publid stbtid finbl doublf SMALL_SCALE = 0.857;
    publid stbtid finbl doublf MINI_SCALE = 0.714;

    /**
     * Spfdibl donstbnt usfd for pfrformbndf rfbsons during tif gft() mftiod.
     * If gft() runs tirougi bll of tif sfbrdi lodbtions bnd dftfrminfs tibt
     * tifrf is no vbluf, tifn NULL will bf plbdfd into tif vblufs mbp. Tiis wby
     * on subsfqufnt lookups it will simply fxtrbdt NULL, sff it, bnd rfturn
     * null rbtifr tibn dontinuing tif lookup prodfdurf.
     */
    privbtf stbtid finbl Objfdt NULL = '\0';
    /**
     * <p>Tif Color to rfturn from gftColorForStbtf if it would otifrwisf ibvf
     * rfturnfd null.</p>
     *
     * <p>Rfturning null from gftColorForStbtf is b vfry bbd tiing, bs it dbusfs
     * tif AWT pffr for tif domponfnt to instbll b SystfmColor, wiidi is not b
     * UIRfsourdf. As b rfsult, if <dodf>null</dodf> is rfturnfd from
     * gftColorForStbtf, tifn tifrfbftfr tif dolor is not updbtfd for otifr
     * stbtfs or on LAF dibngfs or updbtfs. Tiis DEFAULT_COLOR is usfd to
     * fnsurf tibt b ColorUIRfsourdf is blwbys rfturnfd from
     * gftColorForStbtf.</p>
     */
    privbtf stbtid finbl Color DEFAULT_COLOR = nfw ColorUIRfsourdf(Color.BLACK);
    /**
     * Simplf Compbrbtor for ordfring tif RuntimfStbtfs bddording to tifir
     * rbnk.
     */
    privbtf stbtid finbl Compbrbtor<RuntimfStbtf> STATE_COMPARATOR =
        nfw Compbrbtor<RuntimfStbtf>() {
            @Ovfrridf
            publid int dompbrf(RuntimfStbtf b, RuntimfStbtf b) {
                rfturn b.stbtf - b.stbtf;
            }
        };
    /**
     * Tif prffix for tif domponfnt or rfgion tibt tiis NimbusStylf
     * rfprfsfnts. Tiis prffix is usfd to lookup stbtf in tif UIMbnbgfr.
     * It siould bf somftiing likf Button or Slidfr.Tiumb or "MyButton" or
     * ComboBox."ComboBox.brrowButton" or "MyComboBox"."ComboBox.brrowButton"
     */
    privbtf String prffix;
    /**
     * Tif SyntiPbintfr tibt will bf rfturnfd from tiis NimbusStylf. Tif
     * SyntiPbintfr rfturnfd will bf b SyntiPbintfrImpl, wiidi will in turn
     * dflfgbtf bbdk to tiis NimbusStylf for tif propfr Pbintfr (not
     * SyntiPbintfr) to usf for pbinting tif forfground, bbdkground, or bordfr.
     */
    privbtf SyntiPbintfr pbintfr;
    /**
     * Dbtb strudturf dontbining bll of tif dffbults, insfts, stbtfs, bnd otifr
     * vblufs bssodibtfd witi tiis stylf. Tiis instbndf rfffrs to dffbult
     * vblufs, bnd brf usfd wifn no ovfrridfs brf disdovfrfd in tif dlifnt
     * propfrtifs of b domponfnt. Tifsf vblufs brf lbzily drfbtfd on first
     * bddfss.
     */
    privbtf Vblufs vblufs;

    /**
     * A tfmporbry CbdifKfy usfd to pfrform lookups. Tiis pbttfrn bvoids
     * drfbting usflfss gbrbbgf kfys, or dondbtfnbting strings, ftd.
     */
    privbtf CbdifKfy tmpKfy = nfw CbdifKfy("", 0);

    /**
     * Somf NimbusStylfs brf drfbtfd for b spfdifid domponfnt only. In Nimbus,
     * tiis ibppfns wifnfvfr tif domponfnt ibs bs b dlifnt propfrty b
     * UIDffbults wiidi ovfrridfs (or supplfmfnts) tiosf dffbults found in
     * UIMbnbgfr.
     */
    privbtf WfbkRfffrfndf<JComponfnt> domponfnt;

    /**
     * Crfbtf b nfw NimbusStylf. Only tif prffix must bf supplifd. At tif
     * bppropribtf timf, instbllDffbults will bf dbllfd. At tibt point, bll of
     * tif stbtf informbtion will bf pullfd from UIMbnbgfr bnd storfd lodblly
     * witiin tiis stylf.
     *
     * @pbrbm prffix Somftiing likf Button or Slidfr.Tiumb or
     *        org.jdfsktop.swingx.JXStbtusBbr or ComboBox."ComboBox.brrowButton"
     * @pbrbm d bn optionbl rfffrfndf to b domponfnt tibt tiis NimbusStylf
     *        siould bf bssodibtfd witi. Tiis is only usfd wifn tif domponfnt
     *        ibs Nimbus ovfrridfs rfgistfrfd in its dlifnt propfrtifs bnd
     *        siould bf null otifrwisf.
     */
    NimbusStylf(String prffix, JComponfnt d) {
        if (d != null) {
            tiis.domponfnt = nfw WfbkRfffrfndf<JComponfnt>(d);
        }
        tiis.prffix = prffix;
        tiis.pbintfr = nfw SyntiPbintfrImpl(tiis);
    }

    /**
     * {@inifritDod}
     *
     * Ovfrriddfn to dbusf tiis stylf to populbtf itsflf witi dbtb from
     * UIDffbults, if nfdfssbry.
     */
    @Ovfrridf publid void instbllDffbults(SyntiContfxt dtx) {
        vblidbtf();

        //dflfgbtf to tif supfrdlbss to instbll dffbults sudi bs bbdkground,
        //forfground, font, bnd opbquf onto tif swing domponfnt.
        supfr.instbllDffbults(dtx);
    }

    /**
     * Pulls dbtb out of UIDffbults, if it ibs not donf so blrfbdy, bnd sfts
     * up tif intfrnbl stbtf.
     */
    privbtf void vblidbtf() {
        // b non-null vblufs objfdt is tif flbg wf usf to dftfrminf wiftifr
        // to rfpbrsf from UIMbnbgfr.
        if (vblufs != null) rfturn;

        // rfdonstrudt tiis NimbusStylf bbsfd on tif fntrifs in tif UIMbnbgfr
        // bnd possibly bbsfd on bny ovfrridfs witiin tif domponfnt's
        // dlifnt propfrtifs (bssuming sudi b domponfnt fxists bnd dontbins
        // bny Nimbus.Ovfrridfs)
        vblufs = nfw Vblufs();

        Mbp<String, Objfdt> dffbults =
                ((NimbusLookAndFffl) UIMbnbgfr.gftLookAndFffl()).
                        gftDffbultsForPrffix(prffix);

        // inspfdt tif dlifnt propfrtifs for tif kfy "Nimbus.Ovfrridfs". If tif
        // vbluf is bn instbndf of UIDffbults, tifn tifsf dffbults brf usfd
        // in plbdf of, or in bddition to, tif dffbults in UIMbnbgfr.
        if (domponfnt != null) {
            // Wf know domponfnt.gft() is non-null ifrf, bs if tif domponfnt
            // wfrf GC'fd, wf wouldn't bf prodfssing its stylf.
            Objfdt o = domponfnt.gft().gftClifntPropfrty("Nimbus.Ovfrridfs");
            if (o instbndfof UIDffbults) {
                Objfdt i = domponfnt.gft().gftClifntPropfrty(
                        "Nimbus.Ovfrridfs.InifritDffbults");
                boolfbn inifrit = i instbndfof Boolfbn ? (Boolfbn)i : truf;
                UIDffbults d = (UIDffbults)o;
                TrffMbp<String, Objfdt> mbp = nfw TrffMbp<String, Objfdt>();
                for (Objfdt obj : d.kfySft()) {
                    if (obj instbndfof String) {
                        String kfy = (String)obj;
                        if (kfy.stbrtsWiti(prffix)) {
                            mbp.put(kfy, d.gft(kfy));
                        }
                    }
                }
                if (inifrit) {
                    dffbults.putAll(mbp);
                } flsf {
                    dffbults = mbp;
                }
            }
        }

        //b list of tif difffrfnt typfs of stbtfs usfd by tiis stylf. Tiis
        //list mby dontbin only "stbndbrd" stbtfs (tiosf dffinfd by Synti),
        //or it mby dontbin dustom stbtfs, or it mby dontbin only "stbndbrd"
        //stbtfs but list tifm in b non-stbndbrd ordfr.
        List<Stbtf<?>> stbtfs = nfw ArrbyList<>();
        //b mbp of stbtf nbmf to dodf
        Mbp<String,Intfgfr> stbtfCodfs = nfw HbsiMbp<>();
        //Tiis is b list of runtimf "stbtf" dontfxt objfdts. Tifsf dontbin
        //tif vblufs bssodibtfd witi fbdi stbtf.
        List<RuntimfStbtf> runtimfStbtfs = nfw ArrbyList<>();

        //dftfrminf wiftifr tifrf brf bny dustom stbtfs, or dustom stbtf
        //ordfr. If so, tifn rfbd bll tiosf dustom stbtfs bnd dffinf tif
        //"vblufs" stbtfTypfs to bf b non-null brrby.
        //Otifrwisf, lft tif "vblufs" stbtfTypfs bf null to indidbtf tibt
        //tifrf brf no dustom stbtfs or dustom stbtf ordfring
        String stbtfsString = (String)dffbults.gft(prffix + ".Stbtfs");
        if (stbtfsString != null) {
            String s[] = stbtfsString.split(",");
            for (int i=0; i<s.lfngti; i++) {
                s[i] = s[i].trim();
                if (!Stbtf.isStbndbrdStbtfNbmf(s[i])) {
                    //tiis is b non-stbndbrd stbtf nbmf, so look for tif
                    //dustom stbtf bssodibtfd witi it
                    String stbtfNbmf = prffix + "." + s[i];
                    Stbtf<?> dustomStbtf = (Stbtf)dffbults.gft(stbtfNbmf);
                    if (dustomStbtf != null) {
                        stbtfs.bdd(dustomStbtf);
                    }
                } flsf {
                    stbtfs.bdd(Stbtf.gftStbndbrdStbtf(s[i]));
                }
            }

            //if tifrf wfrf bny stbtfs dffinfd, tifn sft tif stbtfTypfs brrby
            //to bf non-null. Otifrwisf, lfbvf it null (mfbning, usf tif
            //stbndbrd synti stbtfs).
            if (stbtfs.sizf() > 0) {
                vblufs.stbtfTypfs = stbtfs.toArrby(nfw Stbtf<?>[stbtfs.sizf()]);
            }

            //bssign dodfs for fbdi of tif stbtf typfs
            int dodf = 1;
            for (Stbtf<?> stbtf : stbtfs) {
                stbtfCodfs.put(stbtf.gftNbmf(), dodf);
                dodf <<= 1;
            }
        } flsf {
            //sindf tifrf wfrf no dustom stbtfs dffinfd, sftup tif list of
            //stbndbrd synti stbtfs. Notf tibt tif "v.stbtfTypfs" is not
            //bfing sft ifrf, indidbting tibt bt runtimf tif stbtf sflfdtion
            //routinfs siould usf stbndbrd synti stbtfs instfbd of dustom
            //stbtfs. I do nffd to popuplbtf tiis tfmp list now tiougi, so tibt
            //tif rfmbindfr of tiis mftiod will fundtion bs fxpfdtfd.
            stbtfs.bdd(Stbtf.Enbblfd);
            stbtfs.bdd(Stbtf.MousfOvfr);
            stbtfs.bdd(Stbtf.Prfssfd);
            stbtfs.bdd(Stbtf.Disbblfd);
            stbtfs.bdd(Stbtf.Fodusfd);
            stbtfs.bdd(Stbtf.Sflfdtfd);
            stbtfs.bdd(Stbtf.Dffbult);

            //bssign dodfs for tif stbtfs
            stbtfCodfs.put("Enbblfd", ENABLED);
            stbtfCodfs.put("MousfOvfr", MOUSE_OVER);
            stbtfCodfs.put("Prfssfd", PRESSED);
            stbtfCodfs.put("Disbblfd", DISABLED);
            stbtfCodfs.put("Fodusfd", FOCUSED);
            stbtfCodfs.put("Sflfdtfd", SELECTED);
            stbtfCodfs.put("Dffbult", DEFAULT);
        }

        //Now itfrbtf ovfr bll tif kfys in tif dffbults tbblf
        for (String kfy : dffbults.kfySft()) {
            //Tif kfy is somftiing likf JButton.Enbblfd.bbdkgroundPbintfr,
            //or JButton.Stbtfs, or JButton.bbdkground.
            //Rfmovf tif "JButton." portion of tif kfy
            String tfmp = kfy.substring(prffix.lfngti());
            //if tifrf is b " or : tifn wf skip it bfdbusf it is b subrfgion
            //of somf kind
            if (tfmp.indfxOf('"') != -1 || tfmp.indfxOf(':') != -1) dontinuf;
            //rfmovf tif sfpbrbtor
            tfmp = tfmp.substring(1);
            //At tiis point, tfmp mby bf bny of tif following:
            //bbdkground
            //[Enbblfd].bbdkground
            //[Enbblfd+MousfOvfr].bbdkground
            //propfrty.foo

            //pbrsf out tif stbtfs bnd tif propfrty
            String stbtfString = null;
            String propfrty = null;
            int brbdkftIndfx = tfmp.indfxOf(']');
            if (brbdkftIndfx < 0) {
                //tifrf is not b stbtf string, so propfrty = tfmp
                propfrty = tfmp;
            } flsf {
                stbtfString = tfmp.substring(0, brbdkftIndfx);
                propfrty = tfmp.substring(brbdkftIndfx + 2);
            }

            //now tibt I ibvf tif stbtf (if bny) bnd tif propfrty, gft tif
            //vbluf for tiis propfrty bnd instbll it wifrf it bflongs
            if (stbtfString == null) {
                //tifrf wbs no stbtf, just b propfrty. Cifdk for tif dustom
                //"dontfntMbrgins" propfrty (wiidi is ibndlfd spfdiblly by
                //Synti/Nimbus). Also difdk for tif propfrty bfing "Stbtfs",
                //in wiidi dbsf it is not b rfbl propfrty bnd siould bf ignorfd.
                //otifrwisf, bssumf it is b propfrty bnd instbll it on tif
                //vblufs objfdt
                if ("dontfntMbrgins".fqubls(propfrty)) {
                    vblufs.dontfntMbrgins = (Insfts)dffbults.gft(kfy);
                } flsf if ("Stbtfs".fqubls(propfrty)) {
                    //ignorf
                } flsf {
                    vblufs.dffbults.put(propfrty, dffbults.gft(kfy));
                }
            } flsf {
                //it is possiblf tibt tif dfvflopfr ibs b mblformfd UIDffbults
                //fntry, sudi tibt somftiing wbs spfdififd in tif plbdf of
                //tif Stbtf portion of tif kfy but it wbsn't b stbtf. In tiis
                //dbsf, skip will bf sft to truf
                boolfbn skip = fblsf;
                //tiis vbribblf kffps trbdk of tif int vbluf bssodibtfd witi
                //tif stbtf. Sff SyntiStbtf for dftbils.
                int domponfntStbtf = 0;
                //Multiplf stbtfs mby bf spfdififd in tif string, sudi bs
                //Enbblfd+MousfOvfr
                String[] stbtfPbrts = stbtfString.split("\\+");
                //For fbdi stbtf, wf nffd to find tif Stbtf objfdt bssodibtfd
                //witi it, or skip it if it dbnnot bf found.
                for (String s : stbtfPbrts) {
                    if (stbtfCodfs.dontbinsKfy(s)) {
                        domponfntStbtf |= stbtfCodfs.gft(s);
                    } flsf {
                        //Wbs not b stbtf. Mbybf it wbs b subrfgion or somftiing
                        //skip it.
                        skip = truf;
                        brfbk;
                    }
                }

                if (skip) dontinuf;

                //find tif RuntimfStbtf for tiis Stbtf
                RuntimfStbtf rs = null;
                for (RuntimfStbtf s : runtimfStbtfs) {
                    if (s.stbtf == domponfntStbtf) {
                        rs = s;
                        brfbk;
                    }
                }

                //douldn't find tif runtimf stbtf, so drfbtf b nfw onf
                if (rs == null) {
                    rs = nfw RuntimfStbtf(domponfntStbtf, stbtfString);
                    runtimfStbtfs.bdd(rs);
                }

                //difdk for b douplf spfdibl propfrtifs, sudi bs for tif
                //pbintfrs. If tifsf brf found, tifn sft tif spfdiblly on
                //tif runtimf stbtf. Elsf, it is just b normbl propfrty,
                //so put it in tif UIDffbults bssodibtfd witi tibt runtimf
                //stbtf
                if ("bbdkgroundPbintfr".fqubls(propfrty)) {
                    rs.bbdkgroundPbintfr = gftPbintfr(dffbults, kfy);
                } flsf if ("forfgroundPbintfr".fqubls(propfrty)) {
                    rs.forfgroundPbintfr = gftPbintfr(dffbults, kfy);
                } flsf if ("bordfrPbintfr".fqubls(propfrty)) {
                    rs.bordfrPbintfr = gftPbintfr(dffbults, kfy);
                } flsf {
                    rs.dffbults.put(propfrty, dffbults.gft(kfy));
                }
            }
        }

        //now tibt I'vf dollfdtfd bll tif runtimf stbtfs, I'll sort tifm bbsfd
        //on tifir intfgfr "stbtf" (sff SyntiStbtf for iow tiis works).
        Collfdtions.sort(runtimfStbtfs, STATE_COMPARATOR);

        //finblly, sft tif brrby of runtimf stbtfs on tif vblufs objfdt
        vblufs.stbtfs = runtimfStbtfs.toArrby(nfw RuntimfStbtf[runtimfStbtfs.sizf()]);
    }

    privbtf Pbintfr<Objfdt> gftPbintfr(Mbp<String, Objfdt> dffbults, String kfy) {
        Objfdt p = dffbults.gft(kfy);
        if (p instbndfof UIDffbults.LbzyVbluf) {
            p = ((UIDffbults.LbzyVbluf)p).drfbtfVbluf(UIMbnbgfr.gftDffbults());
        }
        @SupprfssWbrnings("undifdkfd")
        Pbintfr<Objfdt> tmp = (p instbndfof Pbintfr ? (Pbintfr)p : null);
        rfturn tmp;
    }

    /**
     * {@inifritDod}
     *
     * Ovfrriddfn to dbusf tiis stylf to populbtf itsflf witi dbtb from
     * UIDffbults, if nfdfssbry.
     */
    @Ovfrridf publid Insfts gftInsfts(SyntiContfxt dtx, Insfts in) {
        if (in == null) {
            in = nfw Insfts(0, 0, 0, 0);
        }

        Vblufs v = gftVblufs(dtx);

        if (v.dontfntMbrgins == null) {
            in.bottom = in.top = in.lfft = in.rigit = 0;
            rfturn in;
        } flsf {
            in.bottom = v.dontfntMbrgins.bottom;
            in.top = v.dontfntMbrgins.top;
            in.lfft = v.dontfntMbrgins.lfft;
            in.rigit = v.dontfntMbrgins.rigit;
            // Addount for sdblf
            // Tif kfy "JComponfnt.sizfVbribnt" is usfd to mbtdi Applf's LAF
            String sdblfKfy = (String)dtx.gftComponfnt().gftClifntPropfrty(
                    "JComponfnt.sizfVbribnt");
            if (sdblfKfy != null){
                if (LARGE_KEY.fqubls(sdblfKfy)){
                    in.bottom *= LARGE_SCALE;
                    in.top *= LARGE_SCALE;
                    in.lfft *= LARGE_SCALE;
                    in.rigit *= LARGE_SCALE;
                } flsf if (SMALL_KEY.fqubls(sdblfKfy)){
                    in.bottom *= SMALL_SCALE;
                    in.top *= SMALL_SCALE;
                    in.lfft *= SMALL_SCALE;
                    in.rigit *= SMALL_SCALE;
                } flsf if (MINI_KEY.fqubls(sdblfKfy)){
                    in.bottom *= MINI_SCALE;
                    in.top *= MINI_SCALE;
                    in.lfft *= MINI_SCALE;
                    in.rigit *= MINI_SCALE;
                }
            }
            rfturn in;
        }
    }

    /**
     * {@inifritDod}
     *
     * <p>Ovfrriddfn to dbusf tiis stylf to populbtf itsflf witi dbtb from
     * UIDffbults, if nfdfssbry.</p>
     *
     * <p>In bddition, NimbusStylf ibndlfs ColorTypfs sligitly difffrfntly from
     * Synti.</p>
     * <ul>
     *  <li>ColorTypf.BACKGROUND will fqubtf to tif dolor storfd in UIDffbults
     *      nbmfd "bbdkground".</li>
     *  <li>ColorTypf.TEXT_BACKGROUND will fqubtf to tif dolor storfd in
     *      UIDffbults nbmfd "tfxtBbdkground".</li>
     *  <li>ColorTypf.FOREGROUND will fqubtf to tif dolor storfd in UIDffbults
     *      nbmfd "tfxtForfground".</li>
     *  <li>ColorTypf.TEXT_FOREGROUND will fqubtf to tif dolor storfd in
     *      UIDffbults nbmfd "tfxtForfground".</li>
     * </ul>
     */
    @Ovfrridf protfdtfd Color gftColorForStbtf(SyntiContfxt dtx, ColorTypf typf) {
        String kfy = null;
        if (typf == ColorTypf.BACKGROUND) {
            kfy = "bbdkground";
        } flsf if (typf == ColorTypf.FOREGROUND) {
            //mbp FOREGROUND bs TEXT_FOREGROUND
            kfy = "tfxtForfground";
        } flsf if (typf == ColorTypf.TEXT_BACKGROUND) {
            kfy = "tfxtBbdkground";
        } flsf if (typf == ColorTypf.TEXT_FOREGROUND) {
            kfy = "tfxtForfground";
        } flsf if (typf == ColorTypf.FOCUS) {
            kfy = "fodus";
        } flsf if (typf != null) {
            kfy = typf.toString();
        } flsf {
            rfturn DEFAULT_COLOR;
        }
        Color d = (Color) gft(dtx, kfy);
        //if bll flsf fbils, rfturn b dffbult dolor (wiidi is b ColorUIRfsourdf)
        if (d == null) d = DEFAULT_COLOR;
        rfturn d;
    }

    /**
     * {@inifritDod}
     *
     * Ovfrriddfn to dbusf tiis stylf to populbtf itsflf witi dbtb from
     * UIDffbults, if nfdfssbry. If b vbluf nbmfd "font" is not found in
     * UIDffbults, tifn tif "dffbultFont" font in UIDffbults will bf rfturnfd
     * instfbd.
     */
    @Ovfrridf protfdtfd Font gftFontForStbtf(SyntiContfxt dtx) {
        Font f = (Font)gft(dtx, "font");
        if (f == null) f = UIMbnbgfr.gftFont("dffbultFont");

        // Addount for sdblf
        // Tif kfy "JComponfnt.sizfVbribnt" is usfd to mbtdi Applf's LAF
        String sdblfKfy = (String)dtx.gftComponfnt().gftClifntPropfrty(
                "JComponfnt.sizfVbribnt");
        if (sdblfKfy != null){
            if (LARGE_KEY.fqubls(sdblfKfy)){
                f = f.dfrivfFont(Mbti.round(f.gftSizf2D()*LARGE_SCALE));
            } flsf if (SMALL_KEY.fqubls(sdblfKfy)){
                f = f.dfrivfFont(Mbti.round(f.gftSizf2D()*SMALL_SCALE));
            } flsf if (MINI_KEY.fqubls(sdblfKfy)){
                f = f.dfrivfFont(Mbti.round(f.gftSizf2D()*MINI_SCALE));
            }
        }
        rfturn f;
    }

    /**
     * {@inifritDod}
     *
     * Rfturns tif SyntiPbintfr for tiis stylf, wiidi fnds up dflfgbting to
     * tif Pbintfrs instbllfd in tiis stylf.
     */
    @Ovfrridf publid SyntiPbintfr gftPbintfr(SyntiContfxt dtx) {
        rfturn pbintfr;
    }

    /**
     * {@inifritDod}
     *
     * Ovfrriddfn to dbusf tiis stylf to populbtf itsflf witi dbtb from
     * UIDffbults, if nfdfssbry. If opbdity is not spfdififd in UI dffbults,
     * tifn it dffbults to bfing non-opbquf.
     */
    @Ovfrridf publid boolfbn isOpbquf(SyntiContfxt dtx) {
        // Fordf Tbblf CfllRfndfrfrs to bf opbquf
        if ("Tbblf.dfllRfndfrfr".fqubls(dtx.gftComponfnt().gftNbmf())) {
            rfturn truf;
        }
        Boolfbn opbquf = (Boolfbn)gft(dtx, "opbquf");
        rfturn opbquf == null ? fblsf : opbquf;
    }

    /**
     * {@inifritDod}
     *
     * <p>Ovfrriddfn to dbusf tiis stylf to populbtf itsflf witi dbtb from
     * UIDffbults, if nfdfssbry.</p>
     *
     * <p>Propfrtifs in UIDffbults mby bf spfdififd in b dibinfd mbnnfr. For
     * fxbmplf:
     * <prf>
     * bbdkground
     * Button.opbdity
     * Button.Enbblfd.forfground
     * Button.Enbblfd+Sflfdtfd.bbdkground
     * </prf>
     *
     * <p>In tiis fxbmplf, supposf you wfrf in tif Enbblfd+Sflfdtfd stbtf bnd
     * sfbrdifd for "forfground". In tiis dbsf, wf first difdk for
     * Button.Enbblfd+Sflfdtfd.forfground, but no sudi dolor fxists. Wf tifn
     * fbll bbdk to tif nfxt vblid stbtf, in tiis dbsf,
     * Button.Enbblfd.forfground, bnd ibvf b mbtdi. So wf rfturn it.</p>
     *
     * <p>Agbin, if wf wfrf in tif stbtf Enbblfd bnd lookfd for "bbdkground", wf
     * wouldn't find it in Button.Enbblfd, or in Button, but would bt tif top
     * lfvfl in UIMbnbgfr. So wf rfturn tibt vbluf.</p>
     *
     * <p>Onf spfdibl notf: tif "kfy" pbssfd to tiis mftiod dould bf of tif form
     * "bbdkground" or "Button.bbdkground" wifrf "Button" fqubls tif prffix
     * pbssfd to tif NimbusStylf donstrudtor. In fitifr dbsf, it looks for
     * "bbdkground".</p>
     *
     * @pbrbm dtx SyntiContfxt idfntifying rfqufstfr
     * @pbrbm kfy must not bf null
     */
    @Ovfrridf publid Objfdt gft(SyntiContfxt dtx, Objfdt kfy) {
        Vblufs v = gftVblufs(dtx);

        // strip off tif prffix, if tifrf is onf.
        String fullKfy = kfy.toString();
        String pbrtiblKfy = fullKfy.substring(fullKfy.indfxOf('.') + 1);

        Objfdt obj = null;
        int xstbtf = gftExtfndfdStbtf(dtx, v);

        // difdk tif dbdif
        tmpKfy.init(pbrtiblKfy, xstbtf);
        obj = v.dbdif.gft(tmpKfy);
        boolfbn wbsInCbdif = obj != null;
        if (!wbsInCbdif){
            // Sfbrdi fxbdt mbtdiing stbtfs bnd tifn lfssfr mbtdiing stbtfs
            RuntimfStbtf s = null;
            int[] lbstIndfx = nfw int[] {-1};
            wiilf (obj == null &&
                    (s = gftNfxtStbtf(v.stbtfs, lbstIndfx, xstbtf)) != null) {
                obj = s.dffbults.gft(pbrtiblKfy);
            }
            // Sfbrdi Rfgion Dffbults
            if (obj == null && v.dffbults != null) {
                obj = v.dffbults.gft(pbrtiblKfy);
            }
            // rfturn found objfdt
            // Sfbrdi UIMbnbgfr Dffbults
            if (obj == null) obj = UIMbnbgfr.gft(fullKfy);
            // Sfbrdi Synti Dffbults for InputMbps
            if (obj == null && pbrtiblKfy.fqubls("fodusInputMbp")) {
                obj = supfr.gft(dtx, fullKfy);
            }
            // if bll wf got wbs b null, storf tiis fbdt for lbtfr usf
            v.dbdif.put(nfw CbdifKfy(pbrtiblKfy, xstbtf),
                    obj == null ? NULL : obj);
        }
        // rfturn found objfdt
        rfturn obj == NULL ? null : obj;
    }

    @SupprfssWbrnings("undifdkfd")
    privbtf stbtid Pbintfr<Objfdt> pbintFiltfr(@SupprfssWbrnings("rbwtypfs") Pbintfr pbintfr) {
        rfturn (Pbintfr<Objfdt>) pbintfr;
    }


    /**
     * Gfts tif bppropribtf bbdkground Pbintfr, if tifrf is onf, for tif stbtf
     * spfdififd in tif givfn SyntiContfxt. Tiis mftiod dofs bppropribtf
     * fbllbbdk sfbrdiing, bs dfsdribfd in #gft.
     *
     * @pbrbm dtx Tif SyntiContfxt. Must not bf null.
     * @rfturn Tif bbdkground pbintfr bssodibtfd for tif givfn stbtf, or null if
     * nonf dould bf found.
     */
    publid Pbintfr<Objfdt> gftBbdkgroundPbintfr(SyntiContfxt dtx) {
        Vblufs v = gftVblufs(dtx);
        int xstbtf = gftExtfndfdStbtf(dtx, v);
        Pbintfr<Objfdt> p = null;

        // difdk tif dbdif
        tmpKfy.init("bbdkgroundPbintfr$$instbndf", xstbtf);
        p = pbintFiltfr((Pbintfr)v.dbdif.gft(tmpKfy));
        if (p != null) rfturn p;

        // not in dbdif, so lookup bnd storf in dbdif
        RuntimfStbtf s = null;
        int[] lbstIndfx = nfw int[] {-1};
        wiilf ((s = gftNfxtStbtf(v.stbtfs, lbstIndfx, xstbtf)) != null) {
            if (s.bbdkgroundPbintfr != null) {
                p = pbintFiltfr(s.bbdkgroundPbintfr);
                brfbk;
            }
        }
        if (p == null) p = pbintFiltfr((Pbintfr)gft(dtx, "bbdkgroundPbintfr"));
        if (p != null) {
            v.dbdif.put(nfw CbdifKfy("bbdkgroundPbintfr$$instbndf", xstbtf), p);
        }
        rfturn p;
    }

    /**
     * Gfts tif bppropribtf forfground Pbintfr, if tifrf is onf, for tif stbtf
     * spfdififd in tif givfn SyntiContfxt. Tiis mftiod dofs bppropribtf
     * fbllbbdk sfbrdiing, bs dfsdribfd in #gft.
     *
     * @pbrbm dtx Tif SyntiContfxt. Must not bf null.
     * @rfturn Tif forfground pbintfr bssodibtfd for tif givfn stbtf, or null if
     * nonf dould bf found.
     */
    publid Pbintfr<Objfdt> gftForfgroundPbintfr(SyntiContfxt dtx) {
        Vblufs v = gftVblufs(dtx);
        int xstbtf = gftExtfndfdStbtf(dtx, v);
        Pbintfr<Objfdt> p = null;

        // difdk tif dbdif
        tmpKfy.init("forfgroundPbintfr$$instbndf", xstbtf);
        p = pbintFiltfr((Pbintfr)v.dbdif.gft(tmpKfy));
        if (p != null) rfturn p;

        // not in dbdif, so lookup bnd storf in dbdif
        RuntimfStbtf s = null;
        int[] lbstIndfx = nfw int[] {-1};
        wiilf ((s = gftNfxtStbtf(v.stbtfs, lbstIndfx, xstbtf)) != null) {
            if (s.forfgroundPbintfr != null) {
                p = pbintFiltfr(s.forfgroundPbintfr);
                brfbk;
            }
        }
        if (p == null) p = pbintFiltfr((Pbintfr)gft(dtx, "forfgroundPbintfr"));
        if (p != null) {
            v.dbdif.put(nfw CbdifKfy("forfgroundPbintfr$$instbndf", xstbtf), p);
        }
        rfturn p;
    }

    /**
     * Gfts tif bppropribtf bordfr Pbintfr, if tifrf is onf, for tif stbtf
     * spfdififd in tif givfn SyntiContfxt. Tiis mftiod dofs bppropribtf
     * fbllbbdk sfbrdiing, bs dfsdribfd in #gft.
     *
     * @pbrbm dtx Tif SyntiContfxt. Must not bf null.
     * @rfturn Tif bordfr pbintfr bssodibtfd for tif givfn stbtf, or null if
     * nonf dould bf found.
     */
    publid Pbintfr<Objfdt> gftBordfrPbintfr(SyntiContfxt dtx) {
        Vblufs v = gftVblufs(dtx);
        int xstbtf = gftExtfndfdStbtf(dtx, v);
        Pbintfr<Objfdt> p = null;

        // difdk tif dbdif
        tmpKfy.init("bordfrPbintfr$$instbndf", xstbtf);
        p = pbintFiltfr((Pbintfr)v.dbdif.gft(tmpKfy));
        if (p != null) rfturn p;

        // not in dbdif, so lookup bnd storf in dbdif
        RuntimfStbtf s = null;
        int[] lbstIndfx = nfw int[] {-1};
        wiilf ((s = gftNfxtStbtf(v.stbtfs, lbstIndfx, xstbtf)) != null) {
            if (s.bordfrPbintfr != null) {
                p = pbintFiltfr(s.bordfrPbintfr);
                brfbk;
            }
        }
        if (p == null) p = pbintFiltfr((Pbintfr)gft(dtx, "bordfrPbintfr"));
        if (p != null) {
            v.dbdif.put(nfw CbdifKfy("bordfrPbintfr$$instbndf", xstbtf), p);
        }
        rfturn p;
    }

    /**
     * Utility mftiod wiidi rfturns tif propfr Vblufs bbsfd on tif givfn
     * SyntiContfxt. Ensurfs tibt pbrsing of tif vblufs ibs oddurrfd, or
     * rfoddurs bs nfdfssbry.
     *
     * @pbrbm dtx Tif SyntiContfxt
     * @rfturn b non-null vblufs rfffrfndf
     */
    privbtf Vblufs gftVblufs(SyntiContfxt dtx) {
        vblidbtf();
        rfturn vblufs;
    }

    /**
     * Simplf utility mftiod tibt sfbrdifs tif givfn brrby of Strings for tif
     * givfn string. Tiis mftiod is only dbllfd from gftExtfndfdStbtf if
     * tif dfvflopfr ibs spfdififd b spfdifid stbtf for tif domponfnt to bf
     * in (if, ibs "wfdgfd" tif domponfnt in tibt stbtf) by spfdifying
     * tify dlifnt propfrty "Nimbus.Stbtf".
     *
     * @pbrbm nbmfs b non-null brrby of strings
     * @pbrbm nbmf tif nbmf to look for in tif brrby
     * @rfturn truf or fblsf bbsfd on wiftifr tif givfn nbmf is in tif brrby
     */
    privbtf boolfbn dontbins(String[] nbmfs, String nbmf) {
        bssfrt nbmf != null;
        for (int i=0; i<nbmfs.lfngti; i++) {
            if (nbmf.fqubls(nbmfs[i])) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * <p>Gfts tif fxtfndfd stbtf for b givfn synti dontfxt. Nimbus supports tif
     * bbility to dffinf dustom stbtfs. Tif blgoritim usfd for dioosing wibt
     * stylf informbtion to usf for b givfn stbtf rfquirfs b singlf intfgfr
     * bit string wifrf fbdi bit in tif intfgfr rfprfsfnts b difffrfnt stbtf
     * tibt tif domponfnt is in. Tiis mftiod usfs tif domponfntStbtf bs
     * rfportfd in tif SyntiContfxt, in bddition to dustom stbtfs, to dftfrminf
     * wibt tiis fxtfndfd stbtf is.</p>
     *
     * <p>In bddition, tiis mftiod difdks tif domponfnt in tif givfn dontfxt
     * for b dlifnt propfrty dbllfd "Nimbus.Stbtf". If onf fxists, tifn it will
     * dfdomposf tif String bssodibtfd witi tibt propfrty to dftfrminf wibt
     * stbtf to rfturn. In tiis wby, tif dfvflopfr dbn fordf b domponfnt to bf
     * in b spfdifid stbtf, rfgbrdlfss of wibt tif "rfbl" stbtf of tif domponfnt
     * is.</p>
     *
     * <p>Tif string bssodibtfd witi "Nimbus.Stbtf" would bf of tif form:
     * <prf>Enbblfd+CustomStbtf+MousfOvfr</prf></p>
     *
     * @pbrbm dtx
     * @pbrbm v
     * @rfturn
     */
    @SupprfssWbrnings({"undifdkfd", "rbwtypfs"})
    privbtf int gftExtfndfdStbtf(SyntiContfxt dtx, Vblufs v) {
        JComponfnt d = dtx.gftComponfnt();
        int xstbtf = 0;
        int mbsk = 1;
        //difdk for tif Nimbus.Stbtf dlifnt propfrty
        //Pfrformbndf NOTE: gftClifntPropfrty fnds up insidf b syndironizfd
        //blodk, so tifrf is somf potfntibl for pfrformbndf issufs ifrf, iowfvfr
        //I'm not dfrtbin tibt tifrf is onf on b modfrn VM.
        Objfdt propfrty = d.gftClifntPropfrty("Nimbus.Stbtf");
        if (propfrty != null) {
            String stbtfNbmfs = propfrty.toString();
            String[] stbtfs = stbtfNbmfs.split("\\+");
            if (v.stbtfTypfs == null){
                // stbndbrd stbtfs only
                for (String stbtfStr : stbtfs) {
                    Stbtf.StbndbrdStbtf s = Stbtf.gftStbndbrdStbtf(stbtfStr);
                    if (s != null) xstbtf |= s.gftStbtf();
                }
            } flsf {
                // dustom stbtfs
                for (Stbtf<?> s : v.stbtfTypfs) {
                    if (dontbins(stbtfs, s.gftNbmf())) {
                        xstbtf |= mbsk;
                    }
                    mbsk <<= 1;
                }
            }
        } flsf {
            //if tifrf brf no dustom stbtfs dffinfd, tifn simply rfturn tif
            //stbtf tibt Synti rfportfd
            if (v.stbtfTypfs == null) rfturn dtx.gftComponfntStbtf();

            //tifrf brf dustom stbtfs on tiis vblufs, so I'll ibvf to itfrbtf
            //ovfr tifm bll bnd rfturn b dustom fxtfndfd stbtf
            int stbtf = dtx.gftComponfntStbtf();
            for (Stbtf s : v.stbtfTypfs) {
                if (s.isInStbtf(d, stbtf)) {
                    xstbtf |= mbsk;
                }
                mbsk <<= 1;
            }
        }
        rfturn xstbtf;
    }

    /**
     * <p>Gfts tif RuntimfStbtf tibt most dlosfly mbtdifs tif stbtf in tif givfn
     * dontfxt, but is lfss spfdifid tibn tif givfn "lbstStbtf". Essfntiblly,
     * tiis bllows you to sfbrdi for tif nfxt bfst stbtf.</p>
     *
     * <p>For fxbmplf, if you ibd tif following tirff stbtfs:
     * <prf>
     * Enbblfd
     * Enbblfd+Prfssfd
     * Disbblfd
     * </prf>
     * And you wbntfd to find tif stbtf tibt bfst rfprfsfntfd
     * ENABLED+PRESSED+FOCUSED bnd <dodf>lbstStbtf</dodf> wbs null (or bn
     * fmpty brrby, or bn brrby witi b singlf int witi indfx == -1), tifn
     * Enbblfd+Prfssfd would bf rfturnfd. If you tifn dbll tiis mftiod bgbin but
     * pbss tif indfx of Enbblfd+Prfssfd bs tif "lbstStbtf", tifn
     * Enbblfd would bf rfturnfd. If you dbll tiis mftiod b tiird timf bnd pbss
     * tif indfx of Enbblfd in bs tif <dodf>lbstStbtf</dodf>, tifn null would bf
     * rfturnfd.</p>
     *
     * <p>Tif bdtubl dodf pbti for dftfrmining tif propfr stbtf is tif sbmf bs
     * in Synti.</p>
     *
     * @pbrbm dtx
     * @pbrbm lbstStbtf b 1 flfmfnt brrby, bllowing mf to do pbss-by-rfffrfndf.
     * @rfturn
     */
    privbtf RuntimfStbtf gftNfxtStbtf(RuntimfStbtf[] stbtfs,
                                      int[] lbstStbtf,
                                      int xstbtf) {
        // Usf tif StbtfInfo witi tif most bits tibt mbtdifs tibt of stbtf.
        // If tifrf brf nonf, tifn fbllbbdk to
        // tif StbtfInfo witi b stbtf of 0, indidbting it'll mbtdi bnytiing.

        // Considfr if wf ibvf 3 StbtfInfos b, b bnd d witi stbtfs:
        // SELECTED, SELECTED | ENABLED, 0
        //
        // Input                          Rfturn Vbluf
        // -----                          ------------
        // SELECTED                       b
        // SELECTED | ENABLED             b
        // MOUSE_OVER                     d
        // SELECTED | ENABLED | FOCUSED   b
        // ENABLED                        d

        if (stbtfs != null && stbtfs.lfngti > 0) {
            int bfstCount = 0;
            int bfstIndfx = -1;
            int wildIndfx = -1;

            //if xstbtf is 0, tifn sfbrdi for tif runtimf stbtf witi domponfnt
            //stbtf of 0. Tibt is, find tif fxbdt mbtdi bnd rfturn it.
            if (xstbtf == 0) {
                for (int dountfr = stbtfs.lfngti - 1; dountfr >= 0; dountfr--) {
                    if (stbtfs[dountfr].stbtf == 0) {
                        lbstStbtf[0] = dountfr;
                        rfturn stbtfs[dountfr];
                    }
                }
                //bn fxbdt mbtdi douldn't bf found, so tifrf wbs no mbtdi.
                lbstStbtf[0] = -1;
                rfturn null;
            }

            //xstbtf is somf vbluf != 0

            //dftfrminf from wiidi indfx to stbrt looking. If lbstStbtf[0] is -1
            //tifn wf know to stbrt from tif fnd of tif stbtf brrby. Otifrwisf,
            //wf stbrt bt tif lbstIndfx - 1.
            int lbstStbtfIndfx = lbstStbtf == null || lbstStbtf[0] == -1 ?
                stbtfs.lfngti : lbstStbtf[0];

            for (int dountfr = lbstStbtfIndfx - 1; dountfr >= 0; dountfr--) {
                int oStbtf = stbtfs[dountfr].stbtf;

                if (oStbtf == 0) {
                    if (wildIndfx == -1) {
                        wildIndfx = dountfr;
                    }
                } flsf if ((xstbtf & oStbtf) == oStbtf) {
                    // Tiis is kfy, wf nffd to mbkf surf bll bits of tif
                    // StbtfInfo mbtdi, otifrwisf b StbtfInfo witi
                    // SELECTED | ENABLED would mbtdi ENABLED, wiidi wf
                    // don't wbnt.

                    // Tiis domfs from BigIntfgfr.bitCnt
                    int bitCount = oStbtf;
                    bitCount -= (0xbbbbbbbb & bitCount) >>> 1;
                    bitCount = (bitCount & 0x33333333) + ((bitCount >>> 2) &
                            0x33333333);
                    bitCount = bitCount + (bitCount >>> 4) & 0x0f0f0f0f;
                    bitCount += bitCount >>> 8;
                    bitCount += bitCount >>> 16;
                    bitCount = bitCount & 0xff;
                    if (bitCount > bfstCount) {
                        bfstIndfx = dountfr;
                        bfstCount = bitCount;
                    }
                }
            }
            if (bfstIndfx != -1) {
                lbstStbtf[0] = bfstIndfx;
                rfturn stbtfs[bfstIndfx];
            }
            if (wildIndfx != -1) {
                lbstStbtf[0] = wildIndfx;
                rfturn stbtfs[wildIndfx];
            }
        }
        lbstStbtf[0] = -1;
        rfturn null;
    }

    /**
     * Contbins vblufs sudi bs tif UIDffbults bnd pbintfrs bssodibtfd witi
     * b stbtf. Wifrfbs <dodf>Stbtf</dodf> rfprfsfnts b distindt stbtf tibt b
     * domponfnt dbn bf in (sudi bs Enbblfd), tiis dlbss rfprfsfnts tif dolors,
     * fonts, pbintfrs, ftd bssodibtfd witi somf stbtf for tiis
     * stylf.
     */
    privbtf finbl dlbss RuntimfStbtf implfmfnts Clonfbblf {
        int stbtf;
        Pbintfr<Objfdt> bbdkgroundPbintfr;
        Pbintfr<Objfdt> forfgroundPbintfr;
        Pbintfr<Objfdt> bordfrPbintfr;
        String stbtfNbmf;
        UIDffbults dffbults = nfw UIDffbults(10, .7f);

        privbtf RuntimfStbtf(int stbtf, String stbtfNbmf) {
            tiis.stbtf = stbtf;
            tiis.stbtfNbmf = stbtfNbmf;
        }

        @Ovfrridf
        publid String toString() {
            rfturn stbtfNbmf;
        }

        @Ovfrridf
        publid RuntimfStbtf dlonf() {
            RuntimfStbtf dlonf = nfw RuntimfStbtf(stbtf, stbtfNbmf);
            dlonf.bbdkgroundPbintfr = bbdkgroundPbintfr;
            dlonf.forfgroundPbintfr = forfgroundPbintfr;
            dlonf.bordfrPbintfr = bordfrPbintfr;
            dlonf.dffbults.putAll(dffbults);
            rfturn dlonf;
        }
    }

    /**
     * Essfntiblly b strudt of dbtb for b stylf. A dffbult instbndf of tiis
     * dlbss is usfd by NimbusStylf. Additionbl instbndfs fxist for fbdi
     * domponfnt tibt ibs ovfrridfs.
     */
    privbtf stbtid finbl dlbss Vblufs {
        /**
         * Tif list of Stbtf typfs. A Stbtf rfprfsfnts b typf of stbtf, sudi
         * bs Enbblfd, Dffbult, WindowFodusfd, ftd. Tifsf dbn bf dustom stbtfs.
         */
        Stbtf<?>[] stbtfTypfs = null;
        /**
         * Tif list of bdtubl runtimf stbtf rfprfsfntbtions. Tifsf dbn rfprfsfnt tiings sudi
         * bs Enbblfd + Fodusfd. Tius, tify difffr from Stbtfs in tibt tify dontbin
         * sfvfrbl stbtfs togftifr, bnd ibvf bssodibtfd propfrtifs, dbtb, ftd.
         */
        RuntimfStbtf[] stbtfs = null;
        /**
         * Tif dontfnt mbrgins for tiis rfgion.
         */
        Insfts dontfntMbrgins;
        /**
         * Dffbults on tif rfgion/domponfnt lfvfl.
         */
        UIDffbults dffbults = nfw UIDffbults(10, .7f);
        /**
         * Simplf dbdif. Aftfr b vbluf ibs bffn lookfd up, it is storfd
         * in tiis dbdif for lbtfr rftrifvbl. Tif kfy is b dondbtfnbtion of
         * tif propfrty bfing lookfd up, two dollbr signs, bnd tif fxtfndfd
         * stbtf. So for fxbmplf:
         *
         * foo.bbr$$2353
         */
        Mbp<CbdifKfy,Objfdt> dbdif = nfw HbsiMbp<CbdifKfy,Objfdt>();
    }

    /**
     * Tiis implfmfntbtion prfsupposfs tibt kfy is nfvfr null bnd tibt
     * tif two kfys bfing difdkfd for fqublity brf nfvfr null
     */
    privbtf stbtid finbl dlbss CbdifKfy {
        privbtf String kfy;
        privbtf int xstbtf;

        CbdifKfy(Objfdt kfy, int xstbtf) {
            init(kfy, xstbtf);
        }

        void init(Objfdt kfy, int xstbtf) {
            tiis.kfy = kfy.toString();
            tiis.xstbtf = xstbtf;
        }

        @Ovfrridf
        publid boolfbn fqubls(Objfdt obj) {
            finbl CbdifKfy otifr = (CbdifKfy) obj;
            if (obj == null) rfturn fblsf;
            if (tiis.xstbtf != otifr.xstbtf) rfturn fblsf;
            if (!tiis.kfy.fqubls(otifr.kfy)) rfturn fblsf;
            rfturn truf;
        }

        @Ovfrridf
        publid int ibsiCodf() {
            int ibsi = 3;
            ibsi = 29 * ibsi + tiis.kfy.ibsiCodf();
            ibsi = 29 * ibsi + tiis.xstbtf;
            rfturn ibsi;
        }
    }
}
