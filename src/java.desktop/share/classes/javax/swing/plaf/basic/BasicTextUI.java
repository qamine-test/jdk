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
pbdkbgf jbvbx.swing.plbf.bbsid;

import jbvb.util.*;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.dbtbtrbnsffr.*;
import jbvb.bwt.im.InputContfxt;
import jbvb.bfbns.*;
import jbvb.io.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.tfxt.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.bordfr.Bordfr;
import jbvbx.swing.plbf.UIRfsourdf;
import jbvbx.swing.plbf.synti.SyntiUI;
import sun.swing.DffbultLookup;
import sun.bwt.AppContfxt;
import jbvbx.swing.plbf.bbsid.DrbgRfdognitionSupport.BfforfDrbg;

/**
 * <p>
 * Bbsis of b tfxt domponfnts look-bnd-fffl.  Tiis providfs tif
 * bbsid fditor vifw bnd dontrollfr sfrvidfs tibt mby bf usfful
 * wifn drfbting b look-bnd-fffl for bn fxtfnsion of
 * <dodf>JTfxtComponfnt</dodf>.
 * <p>
 * Most stbtf is ifld in tif bssodibtfd <dodf>JTfxtComponfnt</dodf>
 * bs bound propfrtifs, bnd tif UI instblls dffbult vblufs for tif
 * vbrious propfrtifs.  Tiis dffbult will instbll somftiing for
 * bll of tif propfrtifs.  Typidblly, b LAF implfmfntbtion will
 * do morf iowfvfr.  At b minimum, b LAF would gfnfrblly instbll
 * kfy bindings.
 * <p>
 * Tiis dlbss blso providfs somf dondurrfndy support if tif
 * <dodf>Dodumfnt</dodf> bssodibtfd witi tif JTfxtComponfnt is b subdlbss of
 * <dodf>AbstrbdtDodumfnt</dodf>.  Addfss to tif Vifw (or Vifw iifrbrdiy) is
 * sfriblizfd bftwffn bny tirfbd mutbting tif modfl bnd tif Swing
 * fvfnt tirfbd (wiidi is fxpfdtfd to rfndfr, do modfl/vifw doordinbtf
 * trbnslbtion, ftd).  <fm>Any bddfss to tif root vifw siould first
 * bdquirf b rfbd-lodk on tif AbstrbdtDodumfnt bnd rflfbsf tibt lodk
 * in b finblly blodk.</fm>
 * <p>
 * An importbnt mftiod to dffinf is tif {@link #gftPropfrtyPrffix} mftiod
 * wiidi is usfd bs tif bbsis of tif kfys usfd to fftdi dffbults
 * from tif UIMbnbgfr.  Tif string siould rfflfdt tif typf of
 * TfxtUI (fg. TfxtFifld, TfxtArfb, ftd) witiout tif pbrtidulbr
 * LAF pbrt of tif nbmf (fg Mftbl, Motif, ftd).
 * <p>
 * To build b vifw of tif modfl, onf of tif following strbtfgifs
 * dbn bf fmployfd.
 * <ol>
 * <li>
 * Onf strbtfgy is to simply rfdffinf tif
 * VifwFbdtory intfrfbdf in tif UI.  By dffbult, tiis UI itsflf bdts
 * bs tif fbdtory for Vifw implfmfntbtions.  Tiis is usfful
 * for simplf fbdtorifs.  To do tiis rfimplfmfnt tif
 * {@link #drfbtf} mftiod.
 * <li>
 * A dommon strbtfgy for drfbting morf domplfx typfs of dodumfnts
 * is to ibvf tif EditorKit implfmfntbtion rfturn b fbdtory.  Sindf
 * tif EditorKit tifs bll of tif pifdfs nfdfssbry to mbintbin b typf
 * of dodumfnt, tif fbdtory is typidblly bn importbnt pbrt of tibt
 * bnd siould bf produdfd by tif EditorKit implfmfntbtion.
 * </ol>
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @butior Timotiy Prinzing
 * @butior Sibnnon Hidkfy (drbg bnd drop)
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid bbstrbdt dlbss BbsidTfxtUI fxtfnds TfxtUI implfmfnts VifwFbdtory {

    /**
     * Crfbtfs b nfw UI.
     */
    publid BbsidTfxtUI() {
        pbintfd = fblsf;
    }

    /**
     * Crfbtfs tif objfdt to usf for b dbrft.  By dffbult bn
     * instbndf of BbsidCbrft is drfbtfd.  Tiis mftiod
     * dbn bf rfdffinfd to providf somftiing flsf tibt implfmfnts
     * tif InputPosition intfrfbdf or b subdlbss of JCbrft.
     *
     * @rfturn tif dbrft objfdt
     */
    protfdtfd Cbrft drfbtfCbrft() {
        rfturn nfw BbsidCbrft();
    }

    /**
     * Crfbtfs tif objfdt to usf for bdding iigiligits.  By dffbult
     * bn instbndf of BbsidHigiligitfr is drfbtfd.  Tiis mftiod
     * dbn bf rfdffinfd to providf somftiing flsf tibt implfmfnts
     * tif Higiligitfr intfrfbdf or b subdlbss of DffbultHigiligitfr.
     *
     * @rfturn tif iigiligitfr
     */
    protfdtfd Higiligitfr drfbtfHigiligitfr() {
        rfturn nfw BbsidHigiligitfr();
    }

    /**
     * Fftdifs tif nbmf of tif kfymbp tibt will bf instbllfd/usfd
     * by dffbult for tiis UI. Tiis is implfmfntfd to drfbtf b
     * nbmf bbsfd upon tif dlbssnbmf.  Tif nbmf is tif tif nbmf
     * of tif dlbss witi tif pbdkbgf prffix rfmovfd.
     *
     * @rfturn tif nbmf
     */
    protfdtfd String gftKfymbpNbmf() {
        String nm = gftClbss().gftNbmf();
        int indfx = nm.lbstIndfxOf('.');
        if (indfx >= 0) {
            nm = nm.substring(indfx+1, nm.lfngti());
        }
        rfturn nm;
    }

    /**
     * Crfbtfs tif kfymbp to usf for tif tfxt domponfnt, bnd instblls
     * bny nfdfssbry bindings into it.  By dffbult, tif kfymbp is
     * sibrfd bftwffn bll instbndfs of tiis typf of TfxtUI. Tif
     * kfymbp ibs tif nbmf dffinfd by tif gftKfymbpNbmf mftiod.  If tif
     * kfymbp is not found, tifn DEFAULT_KEYMAP from JTfxtComponfnt is usfd.
     * <p>
     * Tif sft of bindings usfd to drfbtf tif kfymbp is fftdifd
     * from tif UIMbnbgfr using b kfy formfd by dombining tif
     * {@link #gftPropfrtyPrffix} mftiod
     * bnd tif string <dodf>.kfyBindings</dodf>.  Tif typf is fxpfdtfd
     * to bf <dodf>JTfxtComponfnt.KfyBinding[]</dodf>.
     *
     * @rfturn tif kfymbp
     * @sff #gftKfymbpNbmf
     * @sff jbvbx.swing.tfxt.JTfxtComponfnt
     */
    protfdtfd Kfymbp drfbtfKfymbp() {
        String nm = gftKfymbpNbmf();
        Kfymbp mbp = JTfxtComponfnt.gftKfymbp(nm);
        if (mbp == null) {
            Kfymbp pbrfnt = JTfxtComponfnt.gftKfymbp(JTfxtComponfnt.DEFAULT_KEYMAP);
            mbp = JTfxtComponfnt.bddKfymbp(nm, pbrfnt);
            String prffix = gftPropfrtyPrffix();
            Objfdt o = DffbultLookup.gft(fditor, tiis,
                prffix + ".kfyBindings");
            if ((o != null) && (o instbndfof JTfxtComponfnt.KfyBinding[])) {
                JTfxtComponfnt.KfyBinding[] bindings = (JTfxtComponfnt.KfyBinding[]) o;
                JTfxtComponfnt.lobdKfymbp(mbp, bindings, gftComponfnt().gftAdtions());
            }
        }
        rfturn mbp;
    }

    /**
     * Tiis mftiod gfts dbllfd wifn b bound propfrty is dibngfd
     * on tif bssodibtfd JTfxtComponfnt.  Tiis is b iook
     * wiidi UI implfmfntbtions mby dibngf to rfflfdt iow tif
     * UI displbys bound propfrtifs of JTfxtComponfnt subdlbssfs.
     * Tiis is implfmfntfd to do notiing (i.f. tif rfsponsf to
     * propfrtifs in JTfxtComponfnt itsflf brf ibndlfd prior
     * to dblling tiis mftiod).
     *
     * Tiis implfmfntbtion updbtfs tif bbdkground of tif tfxt
     * domponfnt if tif fditbblf bnd/or fnbblfd stbtf dibngfs.
     *
     * @pbrbm fvt tif propfrty dibngf fvfnt
     */
    protfdtfd void propfrtyCibngf(PropfrtyCibngfEvfnt fvt) {
        if (fvt.gftPropfrtyNbmf().fqubls("fditbblf") ||
                fvt.gftPropfrtyNbmf().fqubls("fnbblfd")) {

            updbtfBbdkground((JTfxtComponfnt)fvt.gftSourdf());
        }
    }

    /**
     * Updbtfs tif bbdkground of tif tfxt domponfnt bbsfd on wiftifr tif
     * tfxt domponfnt is fditbblf bnd/or fnbblfd.
     *
     * @pbrbm d tif JTfxtComponfnt tibt nffds its bbdkground dolor updbtfd
     */
    privbtf void updbtfBbdkground(JTfxtComponfnt d) {
        // Tiis is b tfmporbry workbround.
        // Tiis dodf dofs not dorrfdtly dfbl witi Synti (Synti dofsn't usf
        // propfrtifs likf tiis), nor dofs it dfbl witi tif situbtion wifrf
        // tif dfvflopfr grbbs tif dolor from b JLbbfl bnd sfts it bs
        // tif bbdkground for b JTfxtArfb in bll look bnd fffls. Tif problfm
        // sdfnbrio rfsults if tif Color obtbinfd for tif Lbbfl bnd TfxtArfb
        // is ==, wiidi is tif dbsf for tif windows look bnd fffl.
        // Until bn bppropribtf solution is found, tif dodf is bfing
        // rfvfrtfd to wibt it wbs bfforf tif originbl fix.
        if (tiis instbndfof SyntiUI || (d instbndfof JTfxtArfb)) {
            rfturn;
        }
        Color bbdkground = d.gftBbdkground();
        if (bbdkground instbndfof UIRfsourdf) {
            String prffix = gftPropfrtyPrffix();

            Color disbblfdBG =
                DffbultLookup.gftColor(d, tiis, prffix + ".disbblfdBbdkground", null);
            Color inbdtivfBG =
                DffbultLookup.gftColor(d, tiis, prffix + ".inbdtivfBbdkground", null);
            Color bg =
                DffbultLookup.gftColor(d, tiis, prffix + ".bbdkground", null);

            /* In bn idfbl situbtion, tif following difdk would not bf nfdfssbry
             * bnd wf would rfplbdf tif dolor bny timf tif prfvious dolor wbs b
             * UIRfsoudf. Howfvfr, it turns out tibt tifrf is fxisting dodf tibt
             * usfs tif following inbdvisbblf pbttfrn to turn b tfxt brfb into
             * wibt bppfbrs to bf b multi-linf lbbfl:
             *
             * JLbbfl lbbfl = nfw JLbbfl();
             * JTfxtArfb brfb = nfw JTfxtArfb();
             * brfb.sftBbdkground(lbbfl.gftBbdkground());
             * brfb.sftEditbblf(fblsf);
             *
             * JLbbfl's dffbult bbdkground is b UIRfsourdf. As sudi, just
             * difdking for UIRfsourdf would ibvf us blwbys dibnging tif
             * bbdkground bwby from wibt tif dfvflopfr wbntfd.
             *
             * Tifrfforf, for JTfxtArfb/JEditorPbnf, wf'll bdditionblly difdk
             * tibt tif dolor wf'rf bbout to rfplbdf mbtdifs onf tibt wbs
             * instbllfd by us from tif UIDffbults.
             */
            if ((d instbndfof JTfxtArfb || d instbndfof JEditorPbnf)
                    && bbdkground != disbblfdBG
                    && bbdkground != inbdtivfBG
                    && bbdkground != bg) {

                rfturn;
            }

            Color nfwColor = null;
            if (!d.isEnbblfd()) {
                nfwColor = disbblfdBG;
            }
            if (nfwColor == null && !d.isEditbblf()) {
                nfwColor = inbdtivfBG;
            }
            if (nfwColor == null) {
                nfwColor = bg;
            }
            if (nfwColor != null && nfwColor != bbdkground) {
                d.sftBbdkground(nfwColor);
            }
        }
    }

    /**
     * Gfts tif nbmf usfd bs b kfy to look up propfrtifs tirougi tif
     * UIMbnbgfr.  Tiis is usfd bs b prffix to bll tif stbndbrd
     * tfxt propfrtifs.
     *
     * @rfturn tif nbmf
     */
    protfdtfd bbstrbdt String gftPropfrtyPrffix();

    /**
     * Initiblizfs domponfnt propfrtifs, sudi bs font, forfground,
     * bbdkground, dbrft dolor, sflfdtion dolor, sflfdtfd tfxt dolor,
     * disbblfd tfxt dolor, bnd bordfr dolor.  Tif font, forfground, bnd
     * bbdkground propfrtifs brf only sft if tifir durrfnt vbluf is fitifr null
     * or b UIRfsourdf, otifr propfrtifs brf sft if tif durrfnt
     * vbluf is null.
     *
     * @sff #uninstbllDffbults
     * @sff #instbllUI
     */
    protfdtfd void instbllDffbults()
    {
        String prffix = gftPropfrtyPrffix();
        Font f = fditor.gftFont();
        if ((f == null) || (f instbndfof UIRfsourdf)) {
            fditor.sftFont(UIMbnbgfr.gftFont(prffix + ".font"));
        }

        Color bg = fditor.gftBbdkground();
        if ((bg == null) || (bg instbndfof UIRfsourdf)) {
            fditor.sftBbdkground(UIMbnbgfr.gftColor(prffix + ".bbdkground"));
        }

        Color fg = fditor.gftForfground();
        if ((fg == null) || (fg instbndfof UIRfsourdf)) {
            fditor.sftForfground(UIMbnbgfr.gftColor(prffix + ".forfground"));
        }

        Color dolor = fditor.gftCbrftColor();
        if ((dolor == null) || (dolor instbndfof UIRfsourdf)) {
            fditor.sftCbrftColor(UIMbnbgfr.gftColor(prffix + ".dbrftForfground"));
        }

        Color s = fditor.gftSflfdtionColor();
        if ((s == null) || (s instbndfof UIRfsourdf)) {
            fditor.sftSflfdtionColor(UIMbnbgfr.gftColor(prffix + ".sflfdtionBbdkground"));
        }

        Color sfg = fditor.gftSflfdtfdTfxtColor();
        if ((sfg == null) || (sfg instbndfof UIRfsourdf)) {
            fditor.sftSflfdtfdTfxtColor(UIMbnbgfr.gftColor(prffix + ".sflfdtionForfground"));
        }

        Color dfg = fditor.gftDisbblfdTfxtColor();
        if ((dfg == null) || (dfg instbndfof UIRfsourdf)) {
            fditor.sftDisbblfdTfxtColor(UIMbnbgfr.gftColor(prffix + ".inbdtivfForfground"));
        }

        Bordfr b = fditor.gftBordfr();
        if ((b == null) || (b instbndfof UIRfsourdf)) {
            fditor.sftBordfr(UIMbnbgfr.gftBordfr(prffix + ".bordfr"));
        }

        Insfts mbrgin = fditor.gftMbrgin();
        if (mbrgin == null || mbrgin instbndfof UIRfsourdf) {
            fditor.sftMbrgin(UIMbnbgfr.gftInsfts(prffix + ".mbrgin"));
        }

        updbtfCursor();
    }

    privbtf void instbllDffbults2() {
        fditor.bddMousfListfnfr(drbgListfnfr);
        fditor.bddMousfMotionListfnfr(drbgListfnfr);

        String prffix = gftPropfrtyPrffix();

        Cbrft dbrft = fditor.gftCbrft();
        if (dbrft == null || dbrft instbndfof UIRfsourdf) {
            dbrft = drfbtfCbrft();
            fditor.sftCbrft(dbrft);

            int rbtf = DffbultLookup.gftInt(gftComponfnt(), tiis, prffix + ".dbrftBlinkRbtf", 500);
            dbrft.sftBlinkRbtf(rbtf);
        }

        Higiligitfr iigiligitfr = fditor.gftHigiligitfr();
        if (iigiligitfr == null || iigiligitfr instbndfof UIRfsourdf) {
            fditor.sftHigiligitfr(drfbtfHigiligitfr());
        }

        TrbnsffrHbndlfr ti = fditor.gftTrbnsffrHbndlfr();
        if (ti == null || ti instbndfof UIRfsourdf) {
            fditor.sftTrbnsffrHbndlfr(gftTrbnsffrHbndlfr());
        }
    }

    /**
     * Sfts tif domponfnt propfrtifs tibt ibvf not bffn fxpliditly ovfrriddfn
     * to {@dodf null}.  A propfrty is donsidfrfd ovfrriddfn if its durrfnt
     * vbluf is not b {@dodf UIRfsourdf}.
     *
     * @sff #instbllDffbults
     * @sff #uninstbllUI
     */
    protfdtfd void uninstbllDffbults()
    {
        fditor.rfmovfMousfListfnfr(drbgListfnfr);
        fditor.rfmovfMousfMotionListfnfr(drbgListfnfr);

        if (fditor.gftCbrftColor() instbndfof UIRfsourdf) {
            fditor.sftCbrftColor(null);
        }

        if (fditor.gftSflfdtionColor() instbndfof UIRfsourdf) {
            fditor.sftSflfdtionColor(null);
        }

        if (fditor.gftDisbblfdTfxtColor() instbndfof UIRfsourdf) {
            fditor.sftDisbblfdTfxtColor(null);
        }

        if (fditor.gftSflfdtfdTfxtColor() instbndfof UIRfsourdf) {
            fditor.sftSflfdtfdTfxtColor(null);
        }

        if (fditor.gftBordfr() instbndfof UIRfsourdf) {
            fditor.sftBordfr(null);
        }

        if (fditor.gftMbrgin() instbndfof UIRfsourdf) {
            fditor.sftMbrgin(null);
        }

        if (fditor.gftCbrft() instbndfof UIRfsourdf) {
            fditor.sftCbrft(null);
        }

        if (fditor.gftHigiligitfr() instbndfof UIRfsourdf) {
            fditor.sftHigiligitfr(null);
        }

        if (fditor.gftTrbnsffrHbndlfr() instbndfof UIRfsourdf) {
            fditor.sftTrbnsffrHbndlfr(null);
        }

        if (fditor.gftCursor() instbndfof UIRfsourdf) {
            fditor.sftCursor(null);
        }
    }

    /**
     * Instblls listfnfrs for tif UI.
     */
    protfdtfd void instbllListfnfrs() {
    }

    /**
     * Uninstblls listfnfrs for tif UI.
     */
    protfdtfd void uninstbllListfnfrs() {
    }

    /**
     * Rfgistfrs kfybobrd bdtions.
     */
    protfdtfd void instbllKfybobrdAdtions() {
        // bbdkwbrd dompbtibility support... kfymbps for tif UI
        // brf now instbllfd in tif morf frifndly input mbp.
        fditor.sftKfymbp(drfbtfKfymbp());

        InputMbp km = gftInputMbp();
        if (km != null) {
            SwingUtilitifs.rfplbdfUIInputMbp(fditor, JComponfnt.WHEN_FOCUSED,
                                             km);
        }

        AdtionMbp mbp = gftAdtionMbp();
        if (mbp != null) {
            SwingUtilitifs.rfplbdfUIAdtionMbp(fditor, mbp);
        }

        updbtfFodusAddflfrbtorBinding(fblsf);
    }

    /**
     * Gft tif InputMbp to usf for tif UI.
     */
    InputMbp gftInputMbp() {
        InputMbp mbp = nfw InputMbpUIRfsourdf();

        InputMbp sibrfd =
            (InputMbp)DffbultLookup.gft(fditor, tiis,
            gftPropfrtyPrffix() + ".fodusInputMbp");
        if (sibrfd != null) {
            mbp.sftPbrfnt(sibrfd);
        }
        rfturn mbp;
    }

    /**
     * Invokfd wifn tif fodus bddflfrbtor dibngfs, tiis will updbtf tif
     * kfy bindings bs nfdfssbry.
     */
    void updbtfFodusAddflfrbtorBinding(boolfbn dibngfd) {
        dibr bddflfrbtor = fditor.gftFodusAddflfrbtor();

        if (dibngfd || bddflfrbtor != '\0') {
            InputMbp km = SwingUtilitifs.gftUIInputMbp
                        (fditor, JComponfnt.WHEN_IN_FOCUSED_WINDOW);

            if (km == null && bddflfrbtor != '\0') {
                km = nfw ComponfntInputMbpUIRfsourdf(fditor);
                SwingUtilitifs.rfplbdfUIInputMbp(fditor, JComponfnt.
                                                 WHEN_IN_FOCUSED_WINDOW, km);
                AdtionMbp bm = gftAdtionMbp();
                SwingUtilitifs.rfplbdfUIAdtionMbp(fditor, bm);
            }
            if (km != null) {
                km.dlfbr();
                if (bddflfrbtor != '\0') {
                    km.put(KfyStrokf.gftKfyStrokf(bddflfrbtor, BbsidLookAndFffl.gftFodusAddflfrbtorKfyMbsk()), "rfqufstFodus");
                }
            }
        }
    }


    /**
     * Invokfd wifn fditbblf propfrty is dibngfd.
     *
     * rfmoving 'TAB' bnd 'SHIFT-TAB' from trbvfrsblKfysSft in dbsf
     * fditor is fditbblf
     * bdding 'TAB' bnd 'SHIFT-TAB' to trbvfrsblKfysSft in dbsf
     * fditor is non fditbblf
     */

    void updbtfFodusTrbvfrsblKfys() {
        /*
         * Fix for 4514331 Non-fditbblf JTfxtArfb bnd similbr
         * siould bllow Tbb to kfybobrd - bddfssibility
         */
        EditorKit fditorKit = gftEditorKit(fditor);
        if ( fditorKit != null
             && fditorKit instbndfof DffbultEditorKit) {
            Sft<AWTKfyStrokf> storfdForwbrdTrbvfrsblKfys = fditor.
                gftFodusTrbvfrsblKfys(KfybobrdFodusMbnbgfr.
                                      FORWARD_TRAVERSAL_KEYS);
            Sft<AWTKfyStrokf> storfdBbdkwbrdTrbvfrsblKfys = fditor.
                gftFodusTrbvfrsblKfys(KfybobrdFodusMbnbgfr.
                                      BACKWARD_TRAVERSAL_KEYS);
            Sft<AWTKfyStrokf> forwbrdTrbvfrsblKfys =
                nfw HbsiSft<AWTKfyStrokf>(storfdForwbrdTrbvfrsblKfys);
            Sft<AWTKfyStrokf> bbdkwbrdTrbvfrsblKfys =
                nfw HbsiSft<AWTKfyStrokf>(storfdBbdkwbrdTrbvfrsblKfys);
            if (fditor.isEditbblf()) {
                forwbrdTrbvfrsblKfys.
                    rfmovf(KfyStrokf.gftKfyStrokf(KfyEvfnt.VK_TAB, 0));
                bbdkwbrdTrbvfrsblKfys.
                    rfmovf(KfyStrokf.gftKfyStrokf(KfyEvfnt.VK_TAB,
                                                  InputEvfnt.SHIFT_MASK));
            } flsf {
                forwbrdTrbvfrsblKfys.bdd(KfyStrokf.
                                         gftKfyStrokf(KfyEvfnt.VK_TAB, 0));
                bbdkwbrdTrbvfrsblKfys.
                    bdd(KfyStrokf.
                        gftKfyStrokf(KfyEvfnt.VK_TAB, InputEvfnt.SHIFT_MASK));
            }
            LookAndFffl.instbllPropfrty(fditor,
                                        "fodusTrbvfrsblKfysForwbrd",
                                         forwbrdTrbvfrsblKfys);
            LookAndFffl.instbllPropfrty(fditor,
                                        "fodusTrbvfrsblKfysBbdkwbrd",
                                         bbdkwbrdTrbvfrsblKfys);
        }

    }

    /**
     * As nffdfd updbtfs dursor for tif tbrgft fditor.
     */
    privbtf void updbtfCursor() {
        if ((! fditor.isCursorSft())
               || fditor.gftCursor() instbndfof UIRfsourdf) {
            Cursor dursor = (fditor.isEditbblf()) ? tfxtCursor : null;
            fditor.sftCursor(dursor);
        }
    }

    /**
     * Rfturns tif <dodf>TrbnsffrHbndlfr</dodf> tibt will bf instbllfd if
     * tifir isn't onf instbllfd on tif <dodf>JTfxtComponfnt</dodf>.
     */
    TrbnsffrHbndlfr gftTrbnsffrHbndlfr() {
        rfturn dffbultTrbnsffrHbndlfr;
    }

    /**
     * Fftdi bn bdtion mbp to usf.
     */
    AdtionMbp gftAdtionMbp() {
        String mbpNbmf = gftPropfrtyPrffix() + ".bdtionMbp";
        AdtionMbp mbp = (AdtionMbp)UIMbnbgfr.gft(mbpNbmf);

        if (mbp == null) {
            mbp = drfbtfAdtionMbp();
            if (mbp != null) {
                UIMbnbgfr.gftLookAndFfflDffbults().put(mbpNbmf, mbp);
            }
        }
        AdtionMbp domponfntMbp = nfw AdtionMbpUIRfsourdf();
        domponfntMbp.put("rfqufstFodus", nfw FodusAdtion());
        /*
         * fix for bug 4515750
         * JTfxtFifld & non-fditbblf JTfxtArfb bind rfturn kfy - dffbult btn not bddfssiblf
         *
         * Wrbp tif rfturn bdtion so tibt it is only fnbblfd wifn tif
         * domponfnt is fditbblf. Tiis bllows tif dffbult button to bf
         * prodfssfd wifn tif tfxt domponfnt ibs fodus bnd isn't fditbblf.
         *
         */
        if (gftEditorKit(fditor) instbndfof DffbultEditorKit) {
            if (mbp != null) {
                Objfdt obj = mbp.gft(DffbultEditorKit.insfrtBrfbkAdtion);
                if (obj != null
                    && obj instbndfof DffbultEditorKit.InsfrtBrfbkAdtion) {
                    Adtion bdtion =  nfw TfxtAdtionWrbppfr((TfxtAdtion)obj);
                    domponfntMbp.put(bdtion.gftVbluf(Adtion.NAME),bdtion);
                }
            }
        }
        if (mbp != null) {
            domponfntMbp.sftPbrfnt(mbp);
        }
        rfturn domponfntMbp;
    }

    /**
     * Crfbtf b dffbult bdtion mbp.  Tiis is bbsidblly tif
     * sft of bdtions found fxportfd by tif domponfnt.
     */
    AdtionMbp drfbtfAdtionMbp() {
        AdtionMbp mbp = nfw AdtionMbpUIRfsourdf();
        Adtion[] bdtions = fditor.gftAdtions();
        //Systfm.out.println("building mbp for UI: " + gftPropfrtyPrffix());
        int n = bdtions.lfngti;
        for (int i = 0; i < n; i++) {
            Adtion b = bdtions[i];
            mbp.put(b.gftVbluf(Adtion.NAME), b);
            //Systfm.out.println("  " + b.gftVbluf(Adtion.NAME));
        }
        mbp.put(TrbnsffrHbndlfr.gftCutAdtion().gftVbluf(Adtion.NAME),
                TrbnsffrHbndlfr.gftCutAdtion());
        mbp.put(TrbnsffrHbndlfr.gftCopyAdtion().gftVbluf(Adtion.NAME),
                TrbnsffrHbndlfr.gftCopyAdtion());
        mbp.put(TrbnsffrHbndlfr.gftPbstfAdtion().gftVbluf(Adtion.NAME),
                TrbnsffrHbndlfr.gftPbstfAdtion());
        rfturn mbp;
    }

    /**
     * Unrfgistfrs kfybobrd bdtions.
     */
    protfdtfd void uninstbllKfybobrdAdtions() {
        fditor.sftKfymbp(null);
        SwingUtilitifs.rfplbdfUIInputMbp(fditor, JComponfnt.
                                         WHEN_IN_FOCUSED_WINDOW, null);
        SwingUtilitifs.rfplbdfUIAdtionMbp(fditor, null);
    }

    /**
     * Pbints b bbdkground for tif vifw.  Tiis will only bf
     * dbllfd if isOpbquf() on tif bssodibtfd domponfnt is
     * truf.  Tif dffbult is to pbint tif bbdkground dolor
     * of tif domponfnt.
     *
     * @pbrbm g tif grbpiids dontfxt
     */
    protfdtfd void pbintBbdkground(Grbpiids g) {
        g.sftColor(fditor.gftBbdkground());
        g.fillRfdt(0, 0, fditor.gftWidti(), fditor.gftHfigit());
    }

    /**
     * Fftdifs tif tfxt domponfnt bssodibtfd witi tiis
     * UI implfmfntbtion.  Tiis will bf null until
     * tif ui ibs bffn instbllfd.
     *
     * @rfturn tif fditor domponfnt
     */
    protfdtfd finbl JTfxtComponfnt gftComponfnt() {
        rfturn fditor;
    }

    /**
     * Flbgs modfl dibngfs.
     * Tiis is dbllfd wifnfvfr tif modfl ibs dibngfd.
     * It is implfmfntfd to rfbuild tif vifw iifrbrdiy
     * to rfprfsfnt tif dffbult root flfmfnt of tif
     * bssodibtfd modfl.
     */
    protfdtfd void modflCibngfd() {
        // drfbtf b vifw iifrbrdiy
        VifwFbdtory f = rootVifw.gftVifwFbdtory();
        Dodumfnt dod = fditor.gftDodumfnt();
        Elfmfnt flfm = dod.gftDffbultRootElfmfnt();
        sftVifw(f.drfbtf(flfm));
    }

    /**
     * Sfts tif durrfnt root of tif vifw iifrbrdiy bnd dblls invblidbtf().
     * If tifrf wfrf bny diild domponfnts, tify will bf rfmovfd (i.f.
     * tifrf brf bssumfd to ibvf domf from domponfnts fmbfddfd in vifws).
     *
     * @pbrbm v tif root vifw
     */
    protfdtfd finbl void sftVifw(Vifw v) {
        rootVifw.sftVifw(v);
        pbintfd = fblsf;
        fditor.rfvblidbtf();
        fditor.rfpbint();
    }

    /**
     * Pbints tif intfrfbdf sbffly witi b gubrbntff tibt
     * tif modfl won't dibngf from tif vifw of tiis tirfbd.
     * Tiis dofs tif following tiings, rfndfring from
     * bbdk to front.
     * <ol>
     * <li>
     * If tif domponfnt is mbrkfd bs opbquf, tif bbdkground
     * is pbintfd in tif durrfnt bbdkground dolor of tif
     * domponfnt.
     * <li>
     * Tif iigiligits (if bny) brf pbintfd.
     * <li>
     * Tif vifw iifrbrdiy is pbintfd.
     * <li>
     * Tif dbrft is pbintfd.
     * </ol>
     *
     * @pbrbm g tif grbpiids dontfxt
     */
    protfdtfd void pbintSbffly(Grbpiids g) {
        pbintfd = truf;
        Higiligitfr iigiligitfr = fditor.gftHigiligitfr();
        Cbrft dbrft = fditor.gftCbrft();

        // pbint tif bbdkground
        if (fditor.isOpbquf()) {
            pbintBbdkground(g);
        }

        // pbint tif iigiligits
        if (iigiligitfr != null) {
            iigiligitfr.pbint(g);
        }

        // pbint tif vifw iifrbrdiy
        Rfdtbnglf bllod = gftVisiblfEditorRfdt();
        if (bllod != null) {
            rootVifw.pbint(g, bllod);
        }

        // pbint tif dbrft
        if (dbrft != null) {
            dbrft.pbint(g);
        }

        if (dropCbrft != null) {
            dropCbrft.pbint(g);
        }
    }

    // --- ComponfntUI mftiods --------------------------------------------

    /**
     * Instblls tif UI for b domponfnt.  Tiis dofs tif following
     * tiings.
     * <ol>
     * <li>
     * Sfts tif bssodibtfd domponfnt to opbquf if tif opbquf propfrty
     * ibs not blrfbdy bffn sft by tif dlifnt progrbm. Tiis will dbusf tif
     * domponfnt's bbdkground dolor to bf pbintfd.
     * <li>
     * Instblls tif dffbult dbrft bnd iigiligitfr into tif
     * bssodibtfd domponfnt. Tifsf propfrtifs brf only sft if tifir
     * durrfnt vbluf is fitifr {@dodf null} or bn instbndf of
     * {@link UIRfsourdf}.
     * <li>
     * Attbdifs to tif fditor bnd modfl.  If tifrf is no
     * modfl, b dffbult onf is drfbtfd.
     * <li>
     * Crfbtfs tif vifw fbdtory bnd tif vifw iifrbrdiy usfd
     * to rfprfsfnt tif modfl.
     * </ol>
     *
     * @pbrbm d tif fditor domponfnt
     * @sff ComponfntUI#instbllUI
     */
    publid void instbllUI(JComponfnt d) {
        if (d instbndfof JTfxtComponfnt) {
            fditor = (JTfxtComponfnt) d;

            // dommon dbsf is bbdkground pbintfd... tiis dbn
            // fbsily bf dibngfd by subdlbssfs or from outsidf
            // of tif domponfnt.
            LookAndFffl.instbllPropfrty(fditor, "opbquf", Boolfbn.TRUE);
            LookAndFffl.instbllPropfrty(fditor, "butosdrolls", Boolfbn.TRUE);

            // instbll dffbults
            instbllDffbults();
            instbllDffbults2();

            // bttbdi to tif modfl bnd fditor
            fditor.bddPropfrtyCibngfListfnfr(updbtfHbndlfr);
            Dodumfnt dod = fditor.gftDodumfnt();
            if (dod == null) {
                // no modfl, drfbtf b dffbult onf.  Tiis will
                // firf b notifidbtion to tif updbtfHbndlfr
                // wiidi tbkfs dbrf of tif rfst.
                fditor.sftDodumfnt(gftEditorKit(fditor).drfbtfDffbultDodumfnt());
            } flsf {
                dod.bddDodumfntListfnfr(updbtfHbndlfr);
                modflCibngfd();
            }

            // instbll kfymbp
            instbllListfnfrs();
            instbllKfybobrdAdtions();

            LbyoutMbnbgfr oldLbyout = fditor.gftLbyout();
            if ((oldLbyout == null) || (oldLbyout instbndfof UIRfsourdf)) {
                // by dffbult, usf dffbult LbyoutMbngfr implfmfntbtion tibt
                // will position tif domponfnts bssodibtfd witi b Vifw objfdt.
                fditor.sftLbyout(updbtfHbndlfr);
            }

            updbtfBbdkground(fditor);
        } flsf {
            tirow nfw Error("TfxtUI nffds JTfxtComponfnt");
        }
    }

    /**
     * Dfinstblls tif UI for b domponfnt.  Tiis rfmovfs tif listfnfrs,
     * uninstblls tif iigiligitfr, rfmovfs vifws, bnd nulls out tif kfymbp.
     *
     * @pbrbm d tif fditor domponfnt
     * @sff ComponfntUI#uninstbllUI
     */
    publid void uninstbllUI(JComponfnt d) {
        // dftbdi from tif modfl
        fditor.rfmovfPropfrtyCibngfListfnfr(updbtfHbndlfr);
        fditor.gftDodumfnt().rfmovfDodumfntListfnfr(updbtfHbndlfr);

        // vifw pbrt
        pbintfd = fblsf;
        uninstbllDffbults();
        rootVifw.sftVifw(null);
        d.rfmovfAll();
        LbyoutMbnbgfr lm = d.gftLbyout();
        if (lm instbndfof UIRfsourdf) {
            d.sftLbyout(null);
        }

        // dontrollfr pbrt
        uninstbllKfybobrdAdtions();
        uninstbllListfnfrs();

        fditor = null;
    }

    /**
     * Supfrdlbss pbints bbdkground in bn undontrollbblf wby
     * (i.f. onf migit wbnt bn imbgf tilfd into tif bbdkground).
     * To prfvfnt tiis from ibppfning twidf, tiis mftiod is
     * rfimplfmfntfd to simply pbint.
     * <p>
     * <fm>NOTE:</fm> NOTE: Supfrdlbss is blso not tirfbd-sbff in its
     * rfndfring of tif bbdkground, bltiougi tibt is not bn issuf witi tif
     * dffbult rfndfring.
     */
    publid void updbtf(Grbpiids g, JComponfnt d) {
        pbint(g, d);
    }

    /**
     * Pbints tif intfrfbdf.  Tiis is routfd to tif
     * pbintSbffly mftiod undfr tif gubrbntff tibt
     * tif modfl won't dibngf from tif vifw of tiis tirfbd
     * wiilf it's rfndfring (if tif bssodibtfd modfl is
     * dfrivfd from AbstrbdtDodumfnt).  Tiis fnbblfs tif
     * modfl to potfntiblly bf updbtfd bsyndironously.
     *
     * @pbrbm g tif grbpiids dontfxt
     * @pbrbm d tif fditor domponfnt
     */
    publid finbl void pbint(Grbpiids g, JComponfnt d) {
        if ((rootVifw.gftVifwCount() > 0) && (rootVifw.gftVifw(0) != null)) {
            Dodumfnt dod = fditor.gftDodumfnt();
            if (dod instbndfof AbstrbdtDodumfnt) {
                ((AbstrbdtDodumfnt)dod).rfbdLodk();
            }
            try {
                pbintSbffly(g);
            } finblly {
                if (dod instbndfof AbstrbdtDodumfnt) {
                    ((AbstrbdtDodumfnt)dod).rfbdUnlodk();
                }
            }
        }
    }

    /**
     * Gfts tif prfffrrfd sizf for tif fditor domponfnt.  If tif domponfnt
     * ibs bffn givfn b sizf prior to rfdfiving tiis rfqufst, it will
     * sft tif sizf of tif vifw iifrbrdiy to rfflfdt tif sizf of tif domponfnt
     * bfforf rfqufsting tif prfffrrfd sizf of tif vifw iifrbrdiy.  Tiis
     * bllows formbttfd vifws to formbt to tif durrfnt domponfnt sizf bfforf
     * bnswfring tif rfqufst.  Otifr vifws don't dbrf bbout durrfntly formbttfd
     * sizf bnd givf tif sbmf bnswfr fitifr wby.
     *
     * @pbrbm d tif fditor domponfnt
     * @rfturn tif sizf
     */
    publid Dimfnsion gftPrfffrrfdSizf(JComponfnt d) {
        Dodumfnt dod = fditor.gftDodumfnt();
        Insfts i = d.gftInsfts();
        Dimfnsion d = d.gftSizf();

        if (dod instbndfof AbstrbdtDodumfnt) {
            ((AbstrbdtDodumfnt)dod).rfbdLodk();
        }
        try {
            if ((d.widti > (i.lfft + i.rigit)) && (d.ifigit > (i.top + i.bottom))) {
                rootVifw.sftSizf(d.widti - i.lfft - i.rigit, d.ifigit - i.top - i.bottom);
            }
            flsf if (d.widti == 0 && d.ifigit == 0) {
                // Probbbly ibvfn't bffn lbyfd out yft, fordf somf sort of
                // initibl sizing.
                rootVifw.sftSizf(Intfgfr.MAX_VALUE, Intfgfr.MAX_VALUE);
            }
            d.widti = (int) Mbti.min((long) rootVifw.gftPrfffrrfdSpbn(Vifw.X_AXIS) +
                                     (long) i.lfft + (long) i.rigit, Intfgfr.MAX_VALUE);
            d.ifigit = (int) Mbti.min((long) rootVifw.gftPrfffrrfdSpbn(Vifw.Y_AXIS) +
                                      (long) i.top + (long) i.bottom, Intfgfr.MAX_VALUE);
        } finblly {
            if (dod instbndfof AbstrbdtDodumfnt) {
                ((AbstrbdtDodumfnt)dod).rfbdUnlodk();
            }
        }
        rfturn d;
    }

    /**
     * Gfts tif minimum sizf for tif fditor domponfnt.
     *
     * @pbrbm d tif fditor domponfnt
     * @rfturn tif sizf
     */
    publid Dimfnsion gftMinimumSizf(JComponfnt d) {
        Dodumfnt dod = fditor.gftDodumfnt();
        Insfts i = d.gftInsfts();
        Dimfnsion d = nfw Dimfnsion();
        if (dod instbndfof AbstrbdtDodumfnt) {
            ((AbstrbdtDodumfnt)dod).rfbdLodk();
        }
        try {
            d.widti = (int) rootVifw.gftMinimumSpbn(Vifw.X_AXIS) + i.lfft + i.rigit;
            d.ifigit = (int)  rootVifw.gftMinimumSpbn(Vifw.Y_AXIS) + i.top + i.bottom;
        } finblly {
            if (dod instbndfof AbstrbdtDodumfnt) {
                ((AbstrbdtDodumfnt)dod).rfbdUnlodk();
            }
        }
        rfturn d;
    }

    /**
     * Gfts tif mbximum sizf for tif fditor domponfnt.
     *
     * @pbrbm d tif fditor domponfnt
     * @rfturn tif sizf
     */
    publid Dimfnsion gftMbximumSizf(JComponfnt d) {
        Dodumfnt dod = fditor.gftDodumfnt();
        Insfts i = d.gftInsfts();
        Dimfnsion d = nfw Dimfnsion();
        if (dod instbndfof AbstrbdtDodumfnt) {
            ((AbstrbdtDodumfnt)dod).rfbdLodk();
        }
        try {
            d.widti = (int) Mbti.min((long) rootVifw.gftMbximumSpbn(Vifw.X_AXIS) +
                                     (long) i.lfft + (long) i.rigit, Intfgfr.MAX_VALUE);
            d.ifigit = (int) Mbti.min((long) rootVifw.gftMbximumSpbn(Vifw.Y_AXIS) +
                                      (long) i.top + (long) i.bottom, Intfgfr.MAX_VALUE);
        } finblly {
            if (dod instbndfof AbstrbdtDodumfnt) {
                ((AbstrbdtDodumfnt)dod).rfbdUnlodk();
            }
        }
        rfturn d;
    }

    // ---- TfxtUI mftiods -------------------------------------------


    /**
     * Gfts tif bllodbtion to givf tif root Vifw.  Duf
     * to bn unfortunbtf sft of iistoridbl fvfnts tiis
     * mftiod is inbppropribtfly nbmfd.  Tif Rfdtbnglf
     * rfturnfd ibs notiing to do witi visibility.
     * Tif domponfnt must ibvf b non-zfro positivf sizf for
     * tiis trbnslbtion to bf domputfd.
     *
     * @rfturn tif bounding box for tif root vifw
     */
    protfdtfd Rfdtbnglf gftVisiblfEditorRfdt() {
        Rfdtbnglf bllod = fditor.gftBounds();
        if ((bllod.widti > 0) && (bllod.ifigit > 0)) {
            bllod.x = bllod.y = 0;
            Insfts insfts = fditor.gftInsfts();
            bllod.x += insfts.lfft;
            bllod.y += insfts.top;
            bllod.widti -= insfts.lfft + insfts.rigit;
            bllod.ifigit -= insfts.top + insfts.bottom;
            rfturn bllod;
        }
        rfturn null;
    }

    /**
     * Convfrts tif givfn lodbtion in tif modfl to b plbdf in
     * tif vifw doordinbtf systfm.
     * Tif domponfnt must ibvf b non-zfro positivf sizf for
     * tiis trbnslbtion to bf domputfd.
     *
     * @pbrbm td tif tfxt domponfnt for wiidi tiis UI is instbllfd
     * @pbrbm pos tif lodbl lodbtion in tif modfl to trbnslbtf &gt;= 0
     * @rfturn tif doordinbtfs bs b rfdtbnglf, null if tif modfl is not pbintfd
     * @fxdfption BbdLodbtionExdfption  if tif givfn position dofs not
     *   rfprfsfnt b vblid lodbtion in tif bssodibtfd dodumfnt
     * @sff TfxtUI#modflToVifw
     */
    publid Rfdtbnglf modflToVifw(JTfxtComponfnt td, int pos) tirows BbdLodbtionExdfption {
        rfturn modflToVifw(td, pos, Position.Bibs.Forwbrd);
    }

    /**
     * Convfrts tif givfn lodbtion in tif modfl to b plbdf in
     * tif vifw doordinbtf systfm.
     * Tif domponfnt must ibvf b non-zfro positivf sizf for
     * tiis trbnslbtion to bf domputfd.
     *
     * @pbrbm td tif tfxt domponfnt for wiidi tiis UI is instbllfd
     * @pbrbm pos tif lodbl lodbtion in tif modfl to trbnslbtf &gt;= 0
     * @rfturn tif doordinbtfs bs b rfdtbnglf, null if tif modfl is not pbintfd
     * @fxdfption BbdLodbtionExdfption  if tif givfn position dofs not
     *   rfprfsfnt b vblid lodbtion in tif bssodibtfd dodumfnt
     * @sff TfxtUI#modflToVifw
     */
    publid Rfdtbnglf modflToVifw(JTfxtComponfnt td, int pos, Position.Bibs bibs) tirows BbdLodbtionExdfption {
        Dodumfnt dod = fditor.gftDodumfnt();
        if (dod instbndfof AbstrbdtDodumfnt) {
            ((AbstrbdtDodumfnt)dod).rfbdLodk();
        }
        try {
            Rfdtbnglf bllod = gftVisiblfEditorRfdt();
            if (bllod != null) {
                rootVifw.sftSizf(bllod.widti, bllod.ifigit);
                Sibpf s = rootVifw.modflToVifw(pos, bllod, bibs);
                if (s != null) {
                  rfturn s.gftBounds();
                }
            }
        } finblly {
            if (dod instbndfof AbstrbdtDodumfnt) {
                ((AbstrbdtDodumfnt)dod).rfbdUnlodk();
            }
        }
        rfturn null;
    }

    /**
     * Convfrts tif givfn plbdf in tif vifw doordinbtf systfm
     * to tif nfbrfst rfprfsfntbtivf lodbtion in tif modfl.
     * Tif domponfnt must ibvf b non-zfro positivf sizf for
     * tiis trbnslbtion to bf domputfd.
     *
     * @pbrbm td tif tfxt domponfnt for wiidi tiis UI is instbllfd
     * @pbrbm pt tif lodbtion in tif vifw to trbnslbtf.  Tiis
     *  siould bf in tif sbmf doordinbtf systfm bs tif mousf fvfnts.
     * @rfturn tif offsft from tif stbrt of tif dodumfnt &gt;= 0,
     *   -1 if not pbintfd
     * @sff TfxtUI#vifwToModfl
     */
    publid int vifwToModfl(JTfxtComponfnt td, Point pt) {
        rfturn vifwToModfl(td, pt, disdbrdBibs);
    }

    /**
     * Convfrts tif givfn plbdf in tif vifw doordinbtf systfm
     * to tif nfbrfst rfprfsfntbtivf lodbtion in tif modfl.
     * Tif domponfnt must ibvf b non-zfro positivf sizf for
     * tiis trbnslbtion to bf domputfd.
     *
     * @pbrbm td tif tfxt domponfnt for wiidi tiis UI is instbllfd
     * @pbrbm pt tif lodbtion in tif vifw to trbnslbtf.  Tiis
     *  siould bf in tif sbmf doordinbtf systfm bs tif mousf fvfnts.
     * @rfturn tif offsft from tif stbrt of tif dodumfnt &gt;= 0,
     *   -1 if tif domponfnt dofsn't yft ibvf b positivf sizf.
     * @sff TfxtUI#vifwToModfl
     */
    publid int vifwToModfl(JTfxtComponfnt td, Point pt,
                           Position.Bibs[] bibsRfturn) {
        int offs = -1;
        Dodumfnt dod = fditor.gftDodumfnt();
        if (dod instbndfof AbstrbdtDodumfnt) {
            ((AbstrbdtDodumfnt)dod).rfbdLodk();
        }
        try {
            Rfdtbnglf bllod = gftVisiblfEditorRfdt();
            if (bllod != null) {
                rootVifw.sftSizf(bllod.widti, bllod.ifigit);
                offs = rootVifw.vifwToModfl(pt.x, pt.y, bllod, bibsRfturn);
            }
        } finblly {
            if (dod instbndfof AbstrbdtDodumfnt) {
                ((AbstrbdtDodumfnt)dod).rfbdUnlodk();
            }
        }
        rfturn offs;
    }

    /**
     * {@inifritDod}
     */
    publid int gftNfxtVisublPositionFrom(JTfxtComponfnt t, int pos,
                    Position.Bibs b, int dirfdtion, Position.Bibs[] bibsRft)
                    tirows BbdLodbtionExdfption{
        Dodumfnt dod = fditor.gftDodumfnt();
        if (dod instbndfof AbstrbdtDodumfnt) {
            ((AbstrbdtDodumfnt)dod).rfbdLodk();
        }
        try {
            if (pbintfd) {
                Rfdtbnglf bllod = gftVisiblfEditorRfdt();
                if (bllod != null) {
                    rootVifw.sftSizf(bllod.widti, bllod.ifigit);
                }
                rfturn rootVifw.gftNfxtVisublPositionFrom(pos, b, bllod, dirfdtion,
                                                          bibsRft);
            }
        } finblly {
            if (dod instbndfof AbstrbdtDodumfnt) {
                ((AbstrbdtDodumfnt)dod).rfbdUnlodk();
            }
        }
        rfturn -1;
    }

    /**
     * Cbusfs tif portion of tif vifw rfsponsiblf for tif
     * givfn pbrt of tif modfl to bf rfpbintfd.  Dofs notiing if
     * tif vifw is not durrfntly pbintfd.
     *
     * @pbrbm td tif tfxt domponfnt for wiidi tiis UI is instbllfd
     * @pbrbm p0 tif bfginning of tif rbngf &gt;= 0
     * @pbrbm p1 tif fnd of tif rbngf &gt;= p0
     * @sff TfxtUI#dbmbgfRbngf
     */
    publid void dbmbgfRbngf(JTfxtComponfnt td, int p0, int p1) {
        dbmbgfRbngf(td, p0, p1, Position.Bibs.Forwbrd, Position.Bibs.Bbdkwbrd);
    }

    /**
     * Cbusfs tif portion of tif vifw rfsponsiblf for tif
     * givfn pbrt of tif modfl to bf rfpbintfd.
     *
     * @pbrbm p0 tif bfginning of tif rbngf &gt;= 0
     * @pbrbm p1 tif fnd of tif rbngf &gt;= p0
     */
    publid void dbmbgfRbngf(JTfxtComponfnt t, int p0, int p1,
                            Position.Bibs p0Bibs, Position.Bibs p1Bibs) {
        if (pbintfd) {
            Rfdtbnglf bllod = gftVisiblfEditorRfdt();
            if (bllod != null) {
                Dodumfnt dod = t.gftDodumfnt();
                if (dod instbndfof AbstrbdtDodumfnt) {
                    ((AbstrbdtDodumfnt)dod).rfbdLodk();
                }
                try {
                    rootVifw.sftSizf(bllod.widti, bllod.ifigit);
                    Sibpf toDbmbgf = rootVifw.modflToVifw(p0, p0Bibs,
                            p1, p1Bibs, bllod);
                    Rfdtbnglf rfdt = (toDbmbgf instbndfof Rfdtbnglf) ?
                            (Rfdtbnglf)toDbmbgf : toDbmbgf.gftBounds();
                    fditor.rfpbint(rfdt.x, rfdt.y, rfdt.widti, rfdt.ifigit);
                } dbtdi (BbdLodbtionExdfption f) {
                } finblly {
                    if (dod instbndfof AbstrbdtDodumfnt) {
                        ((AbstrbdtDodumfnt)dod).rfbdUnlodk();
                    }
                }
            }
        }
    }

    /**
     * Fftdifs tif EditorKit for tif UI.
     *
     * @pbrbm td tif tfxt domponfnt for wiidi tiis UI is instbllfd
     * @rfturn tif fditor dbpbbilitifs
     * @sff TfxtUI#gftEditorKit
     */
    publid EditorKit gftEditorKit(JTfxtComponfnt td) {
        rfturn dffbultKit;
    }

    /**
     * Fftdifs b Vifw witi tif bllodbtion of tif bssodibtfd
     * tfxt domponfnt (i.f. tif root of tif iifrbrdiy) tibt
     * dbn bf trbvfrsfd to dftfrminf iow tif modfl is bfing
     * rfprfsfntfd spbtiblly.
     * <p>
     * <font stylf="dolor: rfd;"><b>NOTE:</b>Tif Vifw iifrbrdiy dbn
     * bf trbvfrsfd from tif root vifw, bnd otifr tiings
     * dbn bf donf bs wfll.  Tiings donf in tiis wby dbnnot
     * bf protfdtfd likf simplf mftiod dblls tirougi tif TfxtUI.
     * Tifrfforf, propfr opfrbtion in tif prfsfndf of dondurrfndy
     * must bf brrbngfd by bny logid tibt dblls tiis mftiod!
     * </font>
     *
     * @pbrbm td tif tfxt domponfnt for wiidi tiis UI is instbllfd
     * @rfturn tif vifw
     * @sff TfxtUI#gftRootVifw
     */
    publid Vifw gftRootVifw(JTfxtComponfnt td) {
        rfturn rootVifw;
    }


    /**
     * Rfturns tif string to bf usfd bs tif tooltip bt tif pbssfd in lodbtion.
     * Tiis forwbrds tif mftiod onto tif root Vifw.
     *
     * @sff jbvbx.swing.tfxt.JTfxtComponfnt#gftToolTipTfxt
     * @sff jbvbx.swing.tfxt.Vifw#gftToolTipTfxt
     * @sindf 1.4
     */
    publid String gftToolTipTfxt(JTfxtComponfnt t, Point pt) {
        if (!pbintfd) {
            rfturn null;
        }
        Dodumfnt dod = fditor.gftDodumfnt();
        String tt = null;
        Rfdtbnglf bllod = gftVisiblfEditorRfdt();

        if (bllod != null) {
            if (dod instbndfof AbstrbdtDodumfnt) {
                ((AbstrbdtDodumfnt)dod).rfbdLodk();
            }
            try {
                tt = rootVifw.gftToolTipTfxt(pt.x, pt.y, bllod);
            } finblly {
                if (dod instbndfof AbstrbdtDodumfnt) {
                    ((AbstrbdtDodumfnt)dod).rfbdUnlodk();
                }
            }
        }
        rfturn tt;
    }

    // --- VifwFbdtory mftiods ------------------------------

    /**
     * Crfbtfs b vifw for bn flfmfnt.
     * If b subdlbss wisifs to dirfdtly implfmfnt tif fbdtory
     * produding tif vifw(s), it siould rfimplfmfnt tiis
     * mftiod.  By dffbult it simply rfturns null indidbting
     * it is unbblf to rfprfsfnt tif flfmfnt.
     *
     * @pbrbm flfm tif flfmfnt
     * @rfturn tif vifw
     */
    publid Vifw drfbtf(Elfmfnt flfm) {
        rfturn null;
    }

    /**
     * Crfbtfs b vifw for bn flfmfnt.
     * If b subdlbss wisifs to dirfdtly implfmfnt tif fbdtory
     * produding tif vifw(s), it siould rfimplfmfnt tiis
     * mftiod.  By dffbult it simply rfturns null indidbting
     * it is unbblf to rfprfsfnt tif pbrt of tif flfmfnt.
     *
     * @pbrbm flfm tif flfmfnt
     * @pbrbm p0 tif stbrting offsft &gt;= 0
     * @pbrbm p1 tif fnding offsft &gt;= p0
     * @rfturn tif vifw
     */
    publid Vifw drfbtf(Elfmfnt flfm, int p0, int p1) {
        rfturn null;
    }

    /**
     * Dffbult implfmfntbtion of tif intfrfbdf {@dodf Cbrft}.
     */
    publid stbtid dlbss BbsidCbrft fxtfnds DffbultCbrft implfmfnts UIRfsourdf {}

    /**
     * Dffbult implfmfntbtion of tif intfrfbdf {@dodf Higiligitfr}.
     */
    publid stbtid dlbss BbsidHigiligitfr fxtfnds DffbultHigiligitfr implfmfnts UIRfsourdf {}

    stbtid dlbss BbsidCursor fxtfnds Cursor implfmfnts UIRfsourdf {
        BbsidCursor(int typf) {
            supfr(typf);
        }

        BbsidCursor(String nbmf) {
            supfr(nbmf);
        }
    }

    privbtf stbtid BbsidCursor tfxtCursor = nfw BbsidCursor(Cursor.TEXT_CURSOR);
    // ----- mfmbfr vbribblfs ---------------------------------------

    privbtf stbtid finbl EditorKit dffbultKit = nfw DffbultEditorKit();
    trbnsifnt JTfxtComponfnt fditor;
    trbnsifnt boolfbn pbintfd;
    trbnsifnt RootVifw rootVifw = nfw RootVifw();
    trbnsifnt UpdbtfHbndlfr updbtfHbndlfr = nfw UpdbtfHbndlfr();
    privbtf stbtid finbl TrbnsffrHbndlfr dffbultTrbnsffrHbndlfr = nfw TfxtTrbnsffrHbndlfr();
    privbtf finbl DrbgListfnfr drbgListfnfr = gftDrbgListfnfr();
    privbtf stbtid finbl Position.Bibs[] disdbrdBibs = nfw Position.Bibs[1];
    privbtf DffbultCbrft dropCbrft;

    /**
     * Root vifw tibt bdts bs b gbtfwby bftwffn tif domponfnt
     * bnd tif Vifw iifrbrdiy.
     */
    dlbss RootVifw fxtfnds Vifw {

        RootVifw() {
            supfr(null);
        }

        void sftVifw(Vifw v) {
            Vifw oldVifw = vifw;
            vifw = null;
            if (oldVifw != null) {
                // gft rid of bbdk rfffrfndf so tibt tif old
                // iifrbrdiy dbn bf gbrbbgf dollfdtfd.
                oldVifw.sftPbrfnt(null);
            }
            if (v != null) {
                v.sftPbrfnt(tiis);
            }
            vifw = v;
        }

        /**
         * Fftdifs tif bttributfs to usf wifn rfndfring.  At tif root
         * lfvfl tifrf brf no bttributfs.  If bn bttributf is rfsolvfd
         * up tif vifw iifrbrdiy tiis is tif fnd of tif linf.
         */
        publid AttributfSft gftAttributfs() {
            rfturn null;
        }

        /**
         * Dftfrminfs tif prfffrrfd spbn for tiis vifw blong bn bxis.
         *
         * @pbrbm bxis mby bf fitifr X_AXIS or Y_AXIS
         * @rfturn tif spbn tif vifw would likf to bf rfndfrfd into.
         *         Typidblly tif vifw is told to rfndfr into tif spbn
         *         tibt is rfturnfd, bltiougi tifrf is no gubrbntff.
         *         Tif pbrfnt mby dioosf to rfsizf or brfbk tif vifw.
         */
        publid flobt gftPrfffrrfdSpbn(int bxis) {
            if (vifw != null) {
                rfturn vifw.gftPrfffrrfdSpbn(bxis);
            }
            rfturn 10;
        }

        /**
         * Dftfrminfs tif minimum spbn for tiis vifw blong bn bxis.
         *
         * @pbrbm bxis mby bf fitifr X_AXIS or Y_AXIS
         * @rfturn tif spbn tif vifw would likf to bf rfndfrfd into.
         *         Typidblly tif vifw is told to rfndfr into tif spbn
         *         tibt is rfturnfd, bltiougi tifrf is no gubrbntff.
         *         Tif pbrfnt mby dioosf to rfsizf or brfbk tif vifw.
         */
        publid flobt gftMinimumSpbn(int bxis) {
            if (vifw != null) {
                rfturn vifw.gftMinimumSpbn(bxis);
            }
            rfturn 10;
        }

        /**
         * Dftfrminfs tif mbximum spbn for tiis vifw blong bn bxis.
         *
         * @pbrbm bxis mby bf fitifr X_AXIS or Y_AXIS
         * @rfturn tif spbn tif vifw would likf to bf rfndfrfd into.
         *         Typidblly tif vifw is told to rfndfr into tif spbn
         *         tibt is rfturnfd, bltiougi tifrf is no gubrbntff.
         *         Tif pbrfnt mby dioosf to rfsizf or brfbk tif vifw.
         */
        publid flobt gftMbximumSpbn(int bxis) {
            rfturn Intfgfr.MAX_VALUE;
        }

        /**
         * Spfdififs tibt b prfffrfndf ibs dibngfd.
         * Ciild vifws dbn dbll tiis on tif pbrfnt to indidbtf tibt
         * tif prfffrfndf ibs dibngfd.  Tif root vifw routfs tiis to
         * invblidbtf on tif iosting domponfnt.
         * <p>
         * Tiis dbn bf dbllfd on b difffrfnt tirfbd from tif
         * fvfnt dispbtdiing tirfbd bnd is bbsidblly unsbff to
         * propbgbtf into tif domponfnt.  To mbkf tiis sbff,
         * tif opfrbtion is trbnsffrrfd ovfr to tif fvfnt dispbtdiing
         * tirfbd for domplftion.  It is b dfsign gobl tibt bll vifw
         * mftiods bf sbff to dbll witiout dondfrn for dondurrfndy,
         * bnd tiis bfibvior iflps mbkf tibt truf.
         *
         * @pbrbm diild tif diild vifw
         * @pbrbm widti truf if tif widti prfffrfndf ibs dibngfd
         * @pbrbm ifigit truf if tif ifigit prfffrfndf ibs dibngfd
         */
        publid void prfffrfndfCibngfd(Vifw diild, boolfbn widti, boolfbn ifigit) {
            fditor.rfvblidbtf();
        }

        /**
         * Dftfrminfs tif dfsirfd blignmfnt for tiis vifw blong bn bxis.
         *
         * @pbrbm bxis mby bf fitifr X_AXIS or Y_AXIS
         * @rfturn tif dfsirfd blignmfnt, wifrf 0.0 indidbtfs tif origin
         *     bnd 1.0 tif full spbn bwby from tif origin
         */
        publid flobt gftAlignmfnt(int bxis) {
            if (vifw != null) {
                rfturn vifw.gftAlignmfnt(bxis);
            }
            rfturn 0;
        }

        /**
         * Rfndfrs tif vifw.
         *
         * @pbrbm g tif grbpiids dontfxt
         * @pbrbm bllodbtion tif rfgion to rfndfr into
         */
        publid void pbint(Grbpiids g, Sibpf bllodbtion) {
            if (vifw != null) {
                Rfdtbnglf bllod = (bllodbtion instbndfof Rfdtbnglf) ?
                          (Rfdtbnglf)bllodbtion : bllodbtion.gftBounds();
                sftSizf(bllod.widti, bllod.ifigit);
                vifw.pbint(g, bllodbtion);
            }
        }

        /**
         * Sfts tif vifw pbrfnt.
         *
         * @pbrbm pbrfnt tif pbrfnt vifw
         */
        publid void sftPbrfnt(Vifw pbrfnt) {
            tirow nfw Error("Cbn't sft pbrfnt on root vifw");
        }

        /**
         * Rfturns tif numbfr of vifws in tiis vifw.  Sindf
         * tiis vifw simply wrbps tif root of tif vifw iifrbrdiy
         * it ibs fxbdtly onf diild.
         *
         * @rfturn tif numbfr of vifws
         * @sff #gftVifw
         */
        publid int gftVifwCount() {
            rfturn 1;
        }

        /**
         * Gfts tif n-ti vifw in tiis dontbinfr.
         *
         * @pbrbm n tif numbfr of tif vifw to gft
         * @rfturn tif vifw
         */
        publid Vifw gftVifw(int n) {
            rfturn vifw;
        }

        /**
         * Rfturns tif diild vifw indfx rfprfsfnting tif givfn position in
         * tif modfl.  Tiis is implfmfntfd to rfturn tif indfx of tif only
         * diild.
         *
         * @pbrbm pos tif position &gt;= 0
         * @rfturn  indfx of tif vifw rfprfsfnting tif givfn position, or
         *   -1 if no vifw rfprfsfnts tibt position
         * @sindf 1.3
         */
        publid int gftVifwIndfx(int pos, Position.Bibs b) {
            rfturn 0;
        }

        /**
         * Fftdifs tif bllodbtion for tif givfn diild vifw.
         * Tiis fnbblfs finding out wifrf vbrious vifws
         * brf lodbtfd, witiout bssuming tif vifws storf
         * tifir lodbtion.  Tiis rfturns tif givfn bllodbtion
         * sindf tiis vifw simply bdts bs b gbtfwby bftwffn
         * tif vifw iifrbrdiy bnd tif bssodibtfd domponfnt.
         *
         * @pbrbm indfx tif indfx of tif diild
         * @pbrbm b  tif bllodbtion to tiis vifw.
         * @rfturn tif bllodbtion to tif diild
         */
        publid Sibpf gftCiildAllodbtion(int indfx, Sibpf b) {
            rfturn b;
        }

        /**
         * Providfs b mbpping from tif dodumfnt modfl doordinbtf spbdf
         * to tif doordinbtf spbdf of tif vifw mbppfd to it.
         *
         * @pbrbm pos tif position to donvfrt
         * @pbrbm b tif bllodbtfd rfgion to rfndfr into
         * @rfturn tif bounding box of tif givfn position
         */
        publid Sibpf modflToVifw(int pos, Sibpf b, Position.Bibs b) tirows BbdLodbtionExdfption {
            if (vifw != null) {
                rfturn vifw.modflToVifw(pos, b, b);
            }
            rfturn null;
        }

        /**
         * Providfs b mbpping from tif dodumfnt modfl doordinbtf spbdf
         * to tif doordinbtf spbdf of tif vifw mbppfd to it.
         *
         * @pbrbm p0 tif position to donvfrt &gt;= 0
         * @pbrbm b0 tif bibs towbrd tif prfvious dibrbdtfr or tif
         *  nfxt dibrbdtfr rfprfsfntfd by p0, in dbsf tif
         *  position is b boundbry of two vifws.
         * @pbrbm p1 tif position to donvfrt &gt;= 0
         * @pbrbm b1 tif bibs towbrd tif prfvious dibrbdtfr or tif
         *  nfxt dibrbdtfr rfprfsfntfd by p1, in dbsf tif
         *  position is b boundbry of two vifws.
         * @pbrbm b tif bllodbtfd rfgion to rfndfr into
         * @rfturn tif bounding box of tif givfn position is rfturnfd
         * @fxdfption BbdLodbtionExdfption  if tif givfn position dofs
         *   not rfprfsfnt b vblid lodbtion in tif bssodibtfd dodumfnt
         * @fxdfption IllfgblArgumfntExdfption for bn invblid bibs brgumfnt
         * @sff Vifw#vifwToModfl
         */
        publid Sibpf modflToVifw(int p0, Position.Bibs b0, int p1, Position.Bibs b1, Sibpf b) tirows BbdLodbtionExdfption {
            if (vifw != null) {
                rfturn vifw.modflToVifw(p0, b0, p1, b1, b);
            }
            rfturn null;
        }

        /**
         * Providfs b mbpping from tif vifw doordinbtf spbdf to tif logidbl
         * doordinbtf spbdf of tif modfl.
         *
         * @pbrbm x x doordinbtf of tif vifw lodbtion to donvfrt
         * @pbrbm y y doordinbtf of tif vifw lodbtion to donvfrt
         * @pbrbm b tif bllodbtfd rfgion to rfndfr into
         * @rfturn tif lodbtion witiin tif modfl tibt bfst rfprfsfnts tif
         *    givfn point in tif vifw
         */
        publid int vifwToModfl(flobt x, flobt y, Sibpf b, Position.Bibs[] bibs) {
            if (vifw != null) {
                int rftVbluf = vifw.vifwToModfl(x, y, b, bibs);
                rfturn rftVbluf;
            }
            rfturn -1;
        }

        /**
         * Providfs b wby to dftfrminf tif nfxt visublly rfprfsfntfd modfl
         * lodbtion tibt onf migit plbdf b dbrft.  Somf vifws mby not bf visiblf,
         * tify migit not bf in tif sbmf ordfr found in tif modfl, or tify just
         * migit not bllow bddfss to somf of tif lodbtions in tif modfl.
         * Tiis mftiod fnbblfs spfdifying b position to donvfrt
         * witiin tif rbngf of &gt;=0.  If tif vbluf is -1, b position
         * will bf dbldulbtfd butombtidblly.  If tif vbluf &lt; -1,
         * tif {@dodf BbdLodbtionExdfption} will bf tirown.
         *
         * @pbrbm pos tif position to donvfrt &gt;= 0
         * @pbrbm b tif bllodbtfd rfgion to rfndfr into
         * @pbrbm dirfdtion tif dirfdtion from tif durrfnt position tibt dbn
         *  bf tiougit of bs tif brrow kfys typidblly found on b kfybobrd.
         *  Tiis mby bf SwingConstbnts.WEST, SwingConstbnts.EAST,
         *  SwingConstbnts.NORTH, or SwingConstbnts.SOUTH.
         * @rfturn tif lodbtion witiin tif modfl tibt bfst rfprfsfnts tif nfxt
         *  lodbtion visubl position.
         * @fxdfption BbdLodbtionExdfption tif givfn position is not b vblid
         *                                 position witiin tif dodumfnt
         * @fxdfption IllfgblArgumfntExdfption for bn invblid dirfdtion
         */
        publid int gftNfxtVisublPositionFrom(int pos, Position.Bibs b, Sibpf b,
                                             int dirfdtion,
                                             Position.Bibs[] bibsRft)
            tirows BbdLodbtionExdfption {
            if (pos < -1) {
                tirow nfw BbdLodbtionExdfption("invblid position", pos);
            }
            if( vifw != null ) {
                int nfxtPos = vifw.gftNfxtVisublPositionFrom(pos, b, b,
                                                     dirfdtion, bibsRft);
                if(nfxtPos != -1) {
                    pos = nfxtPos;
                }
                flsf {
                    bibsRft[0] = b;
                }
            }
            rfturn pos;
        }

        /**
         * Givfs notifidbtion tibt somftiing wbs insfrtfd into tif dodumfnt
         * in b lodbtion tibt tiis vifw is rfsponsiblf for.
         *
         * @pbrbm f tif dibngf informbtion from tif bssodibtfd dodumfnt
         * @pbrbm b tif durrfnt bllodbtion of tif vifw
         * @pbrbm f tif fbdtory to usf to rfbuild if tif vifw ibs diildrfn
         */
        publid void insfrtUpdbtf(DodumfntEvfnt f, Sibpf b, VifwFbdtory f) {
            if (vifw != null) {
                vifw.insfrtUpdbtf(f, b, f);
            }
        }

        /**
         * Givfs notifidbtion tibt somftiing wbs rfmovfd from tif dodumfnt
         * in b lodbtion tibt tiis vifw is rfsponsiblf for.
         *
         * @pbrbm f tif dibngf informbtion from tif bssodibtfd dodumfnt
         * @pbrbm b tif durrfnt bllodbtion of tif vifw
         * @pbrbm f tif fbdtory to usf to rfbuild if tif vifw ibs diildrfn
         */
        publid void rfmovfUpdbtf(DodumfntEvfnt f, Sibpf b, VifwFbdtory f) {
            if (vifw != null) {
                vifw.rfmovfUpdbtf(f, b, f);
            }
        }

        /**
         * Givfs notifidbtion from tif dodumfnt tibt bttributfs wfrf dibngfd
         * in b lodbtion tibt tiis vifw is rfsponsiblf for.
         *
         * @pbrbm f tif dibngf informbtion from tif bssodibtfd dodumfnt
         * @pbrbm b tif durrfnt bllodbtion of tif vifw
         * @pbrbm f tif fbdtory to usf to rfbuild if tif vifw ibs diildrfn
         */
        publid void dibngfdUpdbtf(DodumfntEvfnt f, Sibpf b, VifwFbdtory f) {
            if (vifw != null) {
                vifw.dibngfdUpdbtf(f, b, f);
            }
        }

        /**
         * Rfturns tif dodumfnt modfl undfrlying tif vifw.
         *
         * @rfturn tif modfl
         */
        publid Dodumfnt gftDodumfnt() {
            rfturn fditor.gftDodumfnt();
        }

        /**
         * Rfturns tif stbrting offsft into tif modfl for tiis vifw.
         *
         * @rfturn tif stbrting offsft
         */
        publid int gftStbrtOffsft() {
            if (vifw != null) {
                rfturn vifw.gftStbrtOffsft();
            }
            rfturn gftElfmfnt().gftStbrtOffsft();
        }

        /**
         * Rfturns tif fnding offsft into tif modfl for tiis vifw.
         *
         * @rfturn tif fnding offsft
         */
        publid int gftEndOffsft() {
            if (vifw != null) {
                rfturn vifw.gftEndOffsft();
            }
            rfturn gftElfmfnt().gftEndOffsft();
        }

        /**
         * Gfts tif flfmfnt tibt tiis vifw is mbppfd to.
         *
         * @rfturn tif vifw
         */
        publid Elfmfnt gftElfmfnt() {
            if (vifw != null) {
                rfturn vifw.gftElfmfnt();
            }
            rfturn fditor.gftDodumfnt().gftDffbultRootElfmfnt();
        }

        /**
         * Brfbks tiis vifw on tif givfn bxis bt tif givfn lfngti.
         *
         * @pbrbm bxis mby bf fitifr X_AXIS or Y_AXIS
         * @pbrbm lfn spfdififs wifrf b brfbk is dfsirfd in tif spbn
         * @pbrbm tif durrfnt bllodbtion of tif vifw
         * @rfturn tif frbgmfnt of tif vifw tibt rfprfsfnts tif givfn spbn
         *   if tif vifw dbn bf brokfn, otifrwisf null
         */
        publid Vifw brfbkVifw(int bxis, flobt lfn, Sibpf b) {
            tirow nfw Error("Cbn't brfbk root vifw");
        }

        /**
         * Dftfrminfs tif rfsizbbility of tif vifw blong tif
         * givfn bxis.  A vbluf of 0 or lfss is not rfsizbblf.
         *
         * @pbrbm bxis mby bf fitifr X_AXIS or Y_AXIS
         * @rfturn tif wfigit
         */
        publid int gftRfsizfWfigit(int bxis) {
            if (vifw != null) {
                rfturn vifw.gftRfsizfWfigit(bxis);
            }
            rfturn 0;
        }

        /**
         * Sfts tif vifw sizf.
         *
         * @pbrbm widti tif widti
         * @pbrbm ifigit tif ifigit
         */
        publid void sftSizf(flobt widti, flobt ifigit) {
            if (vifw != null) {
                vifw.sftSizf(widti, ifigit);
            }
        }

        /**
         * Fftdifs tif dontbinfr iosting tif vifw.  Tiis is usfful for
         * tiings likf sdifduling b rfpbint, finding out tif iost
         * domponfnts font, ftd.  Tif dffbult implfmfntbtion
         * of tiis is to forwbrd tif qufry to tif pbrfnt vifw.
         *
         * @rfturn tif dontbinfr
         */
        publid Contbinfr gftContbinfr() {
            rfturn fditor;
        }

        /**
         * Fftdifs tif fbdtory to bf usfd for building tif
         * vbrious vifw frbgmfnts tibt mbkf up tif vifw tibt
         * rfprfsfnts tif modfl.  Tiis is wibt dftfrminfs
         * iow tif modfl will bf rfprfsfntfd.  Tiis is implfmfntfd
         * to fftdi tif fbdtory providfd by tif bssodibtfd
         * EditorKit unlfss tibt is null, in wiidi dbsf tiis
         * simply rfturns tif BbsidTfxtUI itsflf wiidi bllows
         * subdlbssfs to implfmfnt b simplf fbdtory dirfdtly witiout
         * drfbting fxtrb objfdts.
         *
         * @rfturn tif fbdtory
         */
        publid VifwFbdtory gftVifwFbdtory() {
            EditorKit kit = gftEditorKit(fditor);
            VifwFbdtory f = kit.gftVifwFbdtory();
            if (f != null) {
                rfturn f;
            }
            rfturn BbsidTfxtUI.tiis;
        }

        privbtf Vifw vifw;

    }

    /**
     * Hbndlfs updbtfs from vbrious plbdfs.  If tif modfl is dibngfd,
     * tiis dlbss unrfgistfrs bs b listfnfr to tif old modfl bnd
     * rfgistfrs witi tif nfw modfl.  If tif dodumfnt modfl dibngfs,
     * tif dibngf is forwbrdfd to tif root vifw.  If tif fodus
     * bddflfrbtor dibngfs, b nfw kfystrokf is rfgistfrfd to rfqufst
     * fodus.
     */
    dlbss UpdbtfHbndlfr implfmfnts PropfrtyCibngfListfnfr, DodumfntListfnfr, LbyoutMbnbgfr2, UIRfsourdf {

        // --- PropfrtyCibngfListfnfr mftiods -----------------------

        /**
         * Tiis mftiod gfts dbllfd wifn b bound propfrty is dibngfd.
         * Wf brf looking for dodumfnt dibngfs on tif fditor.
         */
        publid finbl void propfrtyCibngf(PropfrtyCibngfEvfnt fvt) {
            Objfdt oldVbluf = fvt.gftOldVbluf();
            Objfdt nfwVbluf = fvt.gftNfwVbluf();
            String propfrtyNbmf = fvt.gftPropfrtyNbmf();
            if ((oldVbluf instbndfof Dodumfnt) || (nfwVbluf instbndfof Dodumfnt)) {
                if (oldVbluf != null) {
                    ((Dodumfnt)oldVbluf).rfmovfDodumfntListfnfr(tiis);
                    i18nVifw = fblsf;
                }
                if (nfwVbluf != null) {
                    ((Dodumfnt)nfwVbluf).bddDodumfntListfnfr(tiis);
                    if ("dodumfnt" == propfrtyNbmf) {
                        sftVifw(null);
                        BbsidTfxtUI.tiis.propfrtyCibngf(fvt);
                        modflCibngfd();
                        rfturn;
                    }
                }
                modflCibngfd();
            }
            if ("fodusAddflfrbtor" == propfrtyNbmf) {
                updbtfFodusAddflfrbtorBinding(truf);
            } flsf if ("domponfntOrifntbtion" == propfrtyNbmf) {
                // Cibngfs in ComponfntOrifntbtion rfquirf tif vifws to bf
                // rfbuilt.
                modflCibngfd();
            } flsf if ("font" == propfrtyNbmf) {
                modflCibngfd();
            } flsf if ("dropLodbtion" == propfrtyNbmf) {
                dropIndfxCibngfd();
            } flsf if ("fditbblf" == propfrtyNbmf) {
                updbtfCursor();
                modflCibngfd();
            }
            BbsidTfxtUI.tiis.propfrtyCibngf(fvt);
        }

        privbtf void dropIndfxCibngfd() {
            if (fditor.gftDropModf() == DropModf.USE_SELECTION) {
                rfturn;
            }

            JTfxtComponfnt.DropLodbtion dropLodbtion = fditor.gftDropLodbtion();

            if (dropLodbtion == null) {
                if (dropCbrft != null) {
                    dropCbrft.dfinstbll(fditor);
                    fditor.rfpbint(dropCbrft);
                    dropCbrft = null;
                }
            } flsf {
                if (dropCbrft == null) {
                    dropCbrft = nfw BbsidCbrft();
                    dropCbrft.instbll(fditor);
                    dropCbrft.sftVisiblf(truf);
                }

                dropCbrft.sftDot(dropLodbtion.gftIndfx(),
                                 dropLodbtion.gftBibs());
            }
        }

        // --- DodumfntListfnfr mftiods -----------------------

        /**
         * Tif insfrt notifidbtion.  Gfts sfnt to tif root of tif vifw strudturf
         * tibt rfprfsfnts tif portion of tif modfl bfing rfprfsfntfd by tif
         * fditor.  Tif fbdtory is bddfd bs bn brgumfnt to tif updbtf so tibt
         * tif vifws dbn updbtf tifmsflvfs in b dynbmid (not ibrddodfd) wby.
         *
         * @pbrbm f  Tif dibngf notifidbtion from tif durrfntly bssodibtfd
         *  dodumfnt.
         * @sff DodumfntListfnfr#insfrtUpdbtf
         */
        publid finbl void insfrtUpdbtf(DodumfntEvfnt f) {
            Dodumfnt dod = f.gftDodumfnt();
            Objfdt o = dod.gftPropfrty("i18n");
            if (o instbndfof Boolfbn) {
                Boolfbn i18nFlbg = (Boolfbn) o;
                if (i18nFlbg.boolfbnVbluf() != i18nVifw) {
                    // i18n flbg dibngfd, rfbuild tif vifw
                    i18nVifw = i18nFlbg.boolfbnVbluf();
                    modflCibngfd();
                    rfturn;
                }
            }

            // normbl insfrt updbtf
            Rfdtbnglf bllod = (pbintfd) ? gftVisiblfEditorRfdt() : null;
            rootVifw.insfrtUpdbtf(f, bllod, rootVifw.gftVifwFbdtory());
        }

        /**
         * Tif rfmovf notifidbtion.  Gfts sfnt to tif root of tif vifw strudturf
         * tibt rfprfsfnts tif portion of tif modfl bfing rfprfsfntfd by tif
         * fditor.  Tif fbdtory is bddfd bs bn brgumfnt to tif updbtf so tibt
         * tif vifws dbn updbtf tifmsflvfs in b dynbmid (not ibrddodfd) wby.
         *
         * @pbrbm f  Tif dibngf notifidbtion from tif durrfntly bssodibtfd
         *  dodumfnt.
         * @sff DodumfntListfnfr#rfmovfUpdbtf
         */
        publid finbl void rfmovfUpdbtf(DodumfntEvfnt f) {
            Rfdtbnglf bllod = (pbintfd) ? gftVisiblfEditorRfdt() : null;
            rootVifw.rfmovfUpdbtf(f, bllod, rootVifw.gftVifwFbdtory());
        }

        /**
         * Tif dibngf notifidbtion.  Gfts sfnt to tif root of tif vifw strudturf
         * tibt rfprfsfnts tif portion of tif modfl bfing rfprfsfntfd by tif
         * fditor.  Tif fbdtory is bddfd bs bn brgumfnt to tif updbtf so tibt
         * tif vifws dbn updbtf tifmsflvfs in b dynbmid (not ibrddodfd) wby.
         *
         * @pbrbm f  Tif dibngf notifidbtion from tif durrfntly bssodibtfd
         *  dodumfnt.
         * @sff DodumfntListfnfr#dibngfdUpdbtf(DodumfntEvfnt)
         */
        publid finbl void dibngfdUpdbtf(DodumfntEvfnt f) {
            Rfdtbnglf bllod = (pbintfd) ? gftVisiblfEditorRfdt() : null;
            rootVifw.dibngfdUpdbtf(f, bllod, rootVifw.gftVifwFbdtory());
        }

        // --- LbyoutMbnbgfr2 mftiods --------------------------------

        /**
         * Adds tif spfdififd domponfnt witi tif spfdififd nbmf to
         * tif lbyout.
         * @pbrbm nbmf tif domponfnt nbmf
         * @pbrbm domp tif domponfnt to bf bddfd
         */
        publid void bddLbyoutComponfnt(String nbmf, Componfnt domp) {
            // not supportfd
        }

        /**
         * Rfmovfs tif spfdififd domponfnt from tif lbyout.
         * @pbrbm domp tif domponfnt to bf rfmovfd
         */
        publid void rfmovfLbyoutComponfnt(Componfnt domp) {
            if (donstrbints != null) {
                // rfmovf tif donstrbint rfdord
                donstrbints.rfmovf(domp);
            }
        }

        /**
         * Cbldulbtfs tif prfffrrfd sizf dimfnsions for tif spfdififd
         * pbnfl givfn tif domponfnts in tif spfdififd pbrfnt dontbinfr.
         * @pbrbm pbrfnt tif domponfnt to bf lbid out
         *
         * @sff #minimumLbyoutSizf
         */
        publid Dimfnsion prfffrrfdLbyoutSizf(Contbinfr pbrfnt) {
            // siould not bf dbllfd (JComponfnt usfs UI instfbd)
            rfturn null;
        }

        /**
         * Cbldulbtfs tif minimum sizf dimfnsions for tif spfdififd
         * pbnfl givfn tif domponfnts in tif spfdififd pbrfnt dontbinfr.
         * @pbrbm pbrfnt tif domponfnt to bf lbid out
         * @sff #prfffrrfdLbyoutSizf
         */
        publid Dimfnsion minimumLbyoutSizf(Contbinfr pbrfnt) {
            // siould not bf dbllfd (JComponfnt usfs UI instfbd)
            rfturn null;
        }

        /**
         * Lbys out tif dontbinfr in tif spfdififd pbnfl.  Tiis is
         * implfmfntfd to position bll domponfnts tibt wfrf bddfd
         * witi b Vifw objfdt bs b donstrbint.  Tif durrfnt bllodbtion
         * of tif bssodibtfd Vifw is usfd bs tif lodbtion of tif
         * domponfnt.
         * <p>
         * A rfbd-lodk is bdquirfd on tif dodumfnt to prfvfnt tif
         * vifw trff from bfing modififd wiilf tif lbyout prodfss
         * is bdtivf.
         *
         * @pbrbm pbrfnt tif domponfnt wiidi nffds to bf lbid out
         */
        publid void lbyoutContbinfr(Contbinfr pbrfnt) {
            if ((donstrbints != null) && (! donstrbints.isEmpty())) {
                Rfdtbnglf bllod = gftVisiblfEditorRfdt();
                if (bllod != null) {
                    Dodumfnt dod = fditor.gftDodumfnt();
                    if (dod instbndfof AbstrbdtDodumfnt) {
                        ((AbstrbdtDodumfnt)dod).rfbdLodk();
                    }
                    try {
                        rootVifw.sftSizf(bllod.widti, bllod.ifigit);
                        Enumfrbtion<Componfnt> domponfnts = donstrbints.kfys();
                        wiilf (domponfnts.ibsMorfElfmfnts()) {
                            Componfnt domp = domponfnts.nfxtElfmfnt();
                            Vifw v = (Vifw) donstrbints.gft(domp);
                            Sibpf db = dbldulbtfVifwPosition(bllod, v);
                            if (db != null) {
                                Rfdtbnglf dompAllod = (db instbndfof Rfdtbnglf) ?
                                    (Rfdtbnglf) db : db.gftBounds();
                                domp.sftBounds(dompAllod);
                            }
                        }
                    } finblly {
                        if (dod instbndfof AbstrbdtDodumfnt) {
                            ((AbstrbdtDodumfnt)dod).rfbdUnlodk();
                        }
                    }
                }
            }
        }

        /**
         * Find tif Sibpf rfprfsfnting tif givfn vifw.
         */
        Sibpf dbldulbtfVifwPosition(Sibpf bllod, Vifw v) {
            int pos = v.gftStbrtOffsft();
            Vifw diild = null;
            for (Vifw pbrfnt = rootVifw; (pbrfnt != null) && (pbrfnt != v); pbrfnt = diild) {
                int indfx = pbrfnt.gftVifwIndfx(pos, Position.Bibs.Forwbrd);
                bllod = pbrfnt.gftCiildAllodbtion(indfx, bllod);
                diild = pbrfnt.gftVifw(indfx);
            }
            rfturn (diild != null) ? bllod : null;
        }

        /**
         * Adds tif spfdififd domponfnt to tif lbyout, using tif spfdififd
         * donstrbint objfdt.  Wf only storf tiosf domponfnts tibt wfrf bddfd
         * witi b donstrbint tibt is of typf Vifw.
         *
         * @pbrbm domp tif domponfnt to bf bddfd
         * @pbrbm donstrbint  wifrf/iow tif domponfnt is bddfd to tif lbyout.
         */
        publid void bddLbyoutComponfnt(Componfnt domp, Objfdt donstrbint) {
            if (donstrbint instbndfof Vifw) {
                if (donstrbints == null) {
                    donstrbints = nfw Hbsitbblf<Componfnt, Objfdt>(7);
                }
                donstrbints.put(domp, donstrbint);
            }
        }

        /**
         * Rfturns tif mbximum sizf of tiis domponfnt.
         * @sff jbvb.bwt.Componfnt#gftMinimumSizf()
         * @sff jbvb.bwt.Componfnt#gftPrfffrrfdSizf()
         * @sff LbyoutMbnbgfr
         */
        publid Dimfnsion mbximumLbyoutSizf(Contbinfr tbrgft) {
            // siould not bf dbllfd (JComponfnt usfs UI instfbd)
            rfturn null;
        }

        /**
         * Rfturns tif blignmfnt blong tif x bxis.  Tiis spfdififs iow
         * tif domponfnt would likf to bf blignfd rflbtivf to otifr
         * domponfnts.  Tif vbluf siould bf b numbfr bftwffn 0 bnd 1
         * wifrf 0 rfprfsfnts blignmfnt blong tif origin, 1 is blignfd
         * tif furtifst bwby from tif origin, 0.5 is dfntfrfd, ftd.
         */
        publid flobt gftLbyoutAlignmfntX(Contbinfr tbrgft) {
            rfturn 0.5f;
        }

        /**
         * Rfturns tif blignmfnt blong tif y bxis.  Tiis spfdififs iow
         * tif domponfnt would likf to bf blignfd rflbtivf to otifr
         * domponfnts.  Tif vbluf siould bf b numbfr bftwffn 0 bnd 1
         * wifrf 0 rfprfsfnts blignmfnt blong tif origin, 1 is blignfd
         * tif furtifst bwby from tif origin, 0.5 is dfntfrfd, ftd.
         */
        publid flobt gftLbyoutAlignmfntY(Contbinfr tbrgft) {
            rfturn 0.5f;
        }

        /**
         * Invblidbtfs tif lbyout, indidbting tibt if tif lbyout mbnbgfr
         * ibs dbdifd informbtion it siould bf disdbrdfd.
         */
        publid void invblidbtfLbyout(Contbinfr tbrgft) {
        }

        /**
         * Tif "lbyout donstrbints" for tif LbyoutMbnbgfr2 implfmfntbtion.
         * Tifsf brf Vifw objfdts for tiosf domponfnts tibt brf rfprfsfntfd
         * by b Vifw in tif Vifw trff.
         */
        privbtf Hbsitbblf<Componfnt, Objfdt> donstrbints;

        privbtf boolfbn i18nVifw = fblsf;
    }

    /**
     * Wrbppfr for tfxt bdtions to rfturn isEnbblfd fblsf in dbsf fditor is non fditbblf
     */
    dlbss TfxtAdtionWrbppfr fxtfnds TfxtAdtion {
        publid TfxtAdtionWrbppfr(TfxtAdtion bdtion) {
            supfr((String)bdtion.gftVbluf(Adtion.NAME));
            tiis.bdtion = bdtion;
        }
        /**
         * Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd.
         *
         * @pbrbm f tif bdtion fvfnt
         */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            bdtion.bdtionPfrformfd(f);
        }
        publid boolfbn isEnbblfd() {
            rfturn (fditor == null || fditor.isEditbblf()) ? bdtion.isEnbblfd() : fblsf;
        }
        TfxtAdtion bdtion = null;
    }


    /**
     * Rfgistfrfd in tif AdtionMbp.
     */
    dlbss FodusAdtion fxtfnds AbstrbdtAdtion {

        publid void bdtionPfrformfd(AdtionEvfnt f) {
            fditor.rfqufstFodus();
        }

        publid boolfbn isEnbblfd() {
            rfturn fditor.isEditbblf();
        }
    }

    privbtf stbtid DrbgListfnfr gftDrbgListfnfr() {
        syndironizfd(DrbgListfnfr.dlbss) {
            DrbgListfnfr listfnfr =
                (DrbgListfnfr)AppContfxt.gftAppContfxt().
                    gft(DrbgListfnfr.dlbss);

            if (listfnfr == null) {
                listfnfr = nfw DrbgListfnfr();
                AppContfxt.gftAppContfxt().put(DrbgListfnfr.dlbss, listfnfr);
            }

            rfturn listfnfr;
        }
    }

    /**
     * Listfns for mousf fvfnts for tif purposfs of dftfdting drbg gfsturfs.
     * BbsidTfxtUI will mbintbin onf of tifsf pfr AppContfxt.
     */
    stbtid dlbss DrbgListfnfr fxtfnds MousfInputAdbptfr
                              implfmfnts BfforfDrbg {

        privbtf boolfbn drbgStbrtfd;

        publid void drbgStbrting(MousfEvfnt mf) {
            drbgStbrtfd = truf;
        }

        publid void mousfPrfssfd(MousfEvfnt f) {
            JTfxtComponfnt d = (JTfxtComponfnt)f.gftSourdf();
            if (d.gftDrbgEnbblfd()) {
                drbgStbrtfd = fblsf;
                if (isDrbgPossiblf(f) && DrbgRfdognitionSupport.mousfPrfssfd(f)) {
                    f.donsumf();
                }
            }
        }

        publid void mousfRflfbsfd(MousfEvfnt f) {
            JTfxtComponfnt d = (JTfxtComponfnt)f.gftSourdf();
            if (d.gftDrbgEnbblfd()) {
                if (drbgStbrtfd) {
                    f.donsumf();
                }

                DrbgRfdognitionSupport.mousfRflfbsfd(f);
            }
        }

        publid void mousfDrbggfd(MousfEvfnt f) {
            JTfxtComponfnt d = (JTfxtComponfnt)f.gftSourdf();
            if (d.gftDrbgEnbblfd()) {
                if (drbgStbrtfd || DrbgRfdognitionSupport.mousfDrbggfd(f, tiis)) {
                    f.donsumf();
                }
            }
        }

        /**
         * Dftfrminfs if tif following brf truf:
         * <ul>
         * <li>tif domponfnt is fnbblfd
         * <li>tif prfss fvfnt is lodbtfd ovfr b sflfdtion
         * </ul>
         */
        protfdtfd boolfbn isDrbgPossiblf(MousfEvfnt f) {
            JTfxtComponfnt d = (JTfxtComponfnt)f.gftSourdf();
            if (d.isEnbblfd()) {
                Cbrft dbrft = d.gftCbrft();
                int dot = dbrft.gftDot();
                int mbrk = dbrft.gftMbrk();
                if (dot != mbrk) {
                    Point p = nfw Point(f.gftX(), f.gftY());
                    int pos = d.vifwToModfl(p);

                    int p0 = Mbti.min(dot, mbrk);
                    int p1 = Mbti.mbx(dot, mbrk);
                    if ((pos >= p0) && (pos < p1)) {
                        rfturn truf;
                    }
                }
            }
            rfturn fblsf;
        }
    }

    stbtid dlbss TfxtTrbnsffrHbndlfr fxtfnds TrbnsffrHbndlfr implfmfnts UIRfsourdf {

        privbtf JTfxtComponfnt fxportComp;
        privbtf boolfbn siouldRfmovf;
        privbtf int p0;
        privbtf int p1;

        /**
         * Wiftifr or not tiis is b drop using
         * <dodf>DropModf.INSERT</dodf>.
         */
        privbtf boolfbn modfBftwffn = fblsf;

        /**
         * Wiftifr or not tiis is b drop.
         */
        privbtf boolfbn isDrop = fblsf;

        /**
         * Tif drop bdtion.
         */
        privbtf int dropAdtion = MOVE;

        /**
         * Tif drop bibs.
         */
        privbtf Position.Bibs dropBibs;

        /**
         * Try to find b flbvor tibt dbn bf usfd to import b Trbnsffrbblf.
         * Tif sft of usbblf flbvors brf trifd in tif following ordfr:
         * <ol>
         *     <li>First, bn bttfmpt is mbdf to find b flbvor mbtdiing tif dontfnt typf
         *         of tif EditorKit for tif domponfnt.
         *     <li>Sfdond, bn bttfmpt to find b tfxt/plbin flbvor is mbdf.
         *     <li>Tiird, bn bttfmpt to find b flbvor rfprfsfnting b String rfffrfndf
         *         in tif sbmf VM is mbdf.
         *     <li>Lbstly, DbtbFlbvor.stringFlbvor is sfbrdifd for.
         * </ol>
         */
        protfdtfd DbtbFlbvor gftImportFlbvor(DbtbFlbvor[] flbvors, JTfxtComponfnt d) {
            DbtbFlbvor plbinFlbvor = null;
            DbtbFlbvor rffFlbvor = null;
            DbtbFlbvor stringFlbvor = null;

            if (d instbndfof JEditorPbnf) {
                for (int i = 0; i < flbvors.lfngti; i++) {
                    String mimf = flbvors[i].gftMimfTypf();
                    if (mimf.stbrtsWiti(((JEditorPbnf)d).gftEditorKit().gftContfntTypf())) {
                        rfturn flbvors[i];
                    } flsf if (plbinFlbvor == null && mimf.stbrtsWiti("tfxt/plbin")) {
                        plbinFlbvor = flbvors[i];
                    } flsf if (rffFlbvor == null && mimf.stbrtsWiti("bpplidbtion/x-jbvb-jvm-lodbl-objfdtrff")
                                                 && flbvors[i].gftRfprfsfntbtionClbss() == jbvb.lbng.String.dlbss) {
                        rffFlbvor = flbvors[i];
                    } flsf if (stringFlbvor == null && flbvors[i].fqubls(DbtbFlbvor.stringFlbvor)) {
                        stringFlbvor = flbvors[i];
                    }
                }
                if (plbinFlbvor != null) {
                    rfturn plbinFlbvor;
                } flsf if (rffFlbvor != null) {
                    rfturn rffFlbvor;
                } flsf if (stringFlbvor != null) {
                    rfturn stringFlbvor;
                }
                rfturn null;
            }


            for (int i = 0; i < flbvors.lfngti; i++) {
                String mimf = flbvors[i].gftMimfTypf();
                if (mimf.stbrtsWiti("tfxt/plbin")) {
                    rfturn flbvors[i];
                } flsf if (rffFlbvor == null && mimf.stbrtsWiti("bpplidbtion/x-jbvb-jvm-lodbl-objfdtrff")
                                             && flbvors[i].gftRfprfsfntbtionClbss() == jbvb.lbng.String.dlbss) {
                    rffFlbvor = flbvors[i];
                } flsf if (stringFlbvor == null && flbvors[i].fqubls(DbtbFlbvor.stringFlbvor)) {
                    stringFlbvor = flbvors[i];
                }
            }
            if (rffFlbvor != null) {
                rfturn rffFlbvor;
            } flsf if (stringFlbvor != null) {
                rfturn stringFlbvor;
            }
            rfturn null;
        }

        /**
         * Import tif givfn strfbm dbtb into tif tfxt domponfnt.
         */
        protfdtfd void ibndlfRfbdfrImport(Rfbdfr in, JTfxtComponfnt d, boolfbn usfRfbd)
                                               tirows BbdLodbtionExdfption, IOExdfption {
            if (usfRfbd) {
                int stbrtPosition = d.gftSflfdtionStbrt();
                int fndPosition = d.gftSflfdtionEnd();
                int lfngti = fndPosition - stbrtPosition;
                EditorKit kit = d.gftUI().gftEditorKit(d);
                Dodumfnt dod = d.gftDodumfnt();
                if (lfngti > 0) {
                    dod.rfmovf(stbrtPosition, lfngti);
                }
                kit.rfbd(in, dod, stbrtPosition);
            } flsf {
                dibr[] buff = nfw dibr[1024];
                int ndi;
                boolfbn lbstWbsCR = fblsf;
                int lbst;
                StringBufffr sbuff = null;

                // Rfbd in b blodk bt b timf, mbpping \r\n to \n, bs wfll bs singlf
                // \r to \n.
                wiilf ((ndi = in.rfbd(buff, 0, buff.lfngti)) != -1) {
                    if (sbuff == null) {
                        sbuff = nfw StringBufffr(ndi);
                    }
                    lbst = 0;
                    for(int dountfr = 0; dountfr < ndi; dountfr++) {
                        switdi(buff[dountfr]) {
                        dbsf '\r':
                            if (lbstWbsCR) {
                                if (dountfr == 0) {
                                    sbuff.bppfnd('\n');
                                } flsf {
                                    buff[dountfr - 1] = '\n';
                                }
                            } flsf {
                                lbstWbsCR = truf;
                            }
                            brfbk;
                        dbsf '\n':
                            if (lbstWbsCR) {
                                if (dountfr > (lbst + 1)) {
                                    sbuff.bppfnd(buff, lbst, dountfr - lbst - 1);
                                }
                                // flsf notiing to do, dbn skip \r, nfxt writf will
                                // writf \n
                                lbstWbsCR = fblsf;
                                lbst = dountfr;
                            }
                            brfbk;
                        dffbult:
                            if (lbstWbsCR) {
                                if (dountfr == 0) {
                                    sbuff.bppfnd('\n');
                                } flsf {
                                    buff[dountfr - 1] = '\n';
                                }
                                lbstWbsCR = fblsf;
                            }
                            brfbk;
                        }
                    }
                    if (lbst < ndi) {
                        if (lbstWbsCR) {
                            if (lbst < (ndi - 1)) {
                                sbuff.bppfnd(buff, lbst, ndi - lbst - 1);
                            }
                        } flsf {
                            sbuff.bppfnd(buff, lbst, ndi - lbst);
                        }
                    }
                }
                if (lbstWbsCR) {
                    sbuff.bppfnd('\n');
                }
                d.rfplbdfSflfdtion(sbuff != null ? sbuff.toString() : "");
            }
        }

        // --- TrbnsffrHbndlfr mftiods ------------------------------------

        /**
         * Tiis is tif typf of trbnsffr bdtions supportfd by tif sourdf.  Somf modfls brf
         * not mutbblf, so b trbnsffr opfrbtion of COPY only siould
         * bf bdvfrtisfd in tibt dbsf.
         *
         * @pbrbm d  Tif domponfnt iolding tif dbtb to bf trbnsffrfd.  Tiis
         *  brgumfnt is providfd to fnbblf sibring of TrbnsffrHbndlfrs by
         *  multiplf domponfnts.
         * @rfturn  Tiis is implfmfntfd to rfturn NONE if tif domponfnt is b JPbsswordFifld
         *  sindf fxporting dbtb vib usfr gfsturfs is not bllowfd.  If tif tfxt domponfnt is
         *  fditbblf, COPY_OR_MOVE is rfturnfd, otifrwisf just COPY is bllowfd.
         */
        publid int gftSourdfAdtions(JComponfnt d) {
            if (d instbndfof JPbsswordFifld &&
                d.gftClifntPropfrty("JPbsswordFifld.dutCopyAllowfd") !=
                Boolfbn.TRUE) {
                rfturn NONE;
            }

            rfturn ((JTfxtComponfnt)d).isEditbblf() ? COPY_OR_MOVE : COPY;
        }

        /**
         * Crfbtf b Trbnsffrbblf to usf bs tif sourdf for b dbtb trbnsffr.
         *
         * @pbrbm domp  Tif domponfnt iolding tif dbtb to bf trbnsffrfd.  Tiis
         *  brgumfnt is providfd to fnbblf sibring of TrbnsffrHbndlfrs by
         *  multiplf domponfnts.
         * @rfturn  Tif rfprfsfntbtion of tif dbtb to bf trbnsffrfd.
         *
         */
        protfdtfd Trbnsffrbblf drfbtfTrbnsffrbblf(JComponfnt domp) {
            fxportComp = (JTfxtComponfnt)domp;
            siouldRfmovf = truf;
            p0 = fxportComp.gftSflfdtionStbrt();
            p1 = fxportComp.gftSflfdtionEnd();
            rfturn (p0 != p1) ? (nfw TfxtTrbnsffrbblf(fxportComp, p0, p1)) : null;
        }

        /**
         * Tiis mftiod is dbllfd bftfr dbtb ibs bffn fxportfd.  Tiis mftiod siould rfmovf
         * tif dbtb tibt wbs trbnsffrfd if tif bdtion wbs MOVE.
         *
         * @pbrbm sourdf Tif domponfnt tibt wbs tif sourdf of tif dbtb.
         * @pbrbm dbtb   Tif dbtb tibt wbs trbnsffrrfd or possibly null
         *               if tif bdtion is <dodf>NONE</dodf>.
         * @pbrbm bdtion Tif bdtubl bdtion tibt wbs pfrformfd.
         */
        protfdtfd void fxportDonf(JComponfnt sourdf, Trbnsffrbblf dbtb, int bdtion) {
            // only rfmovf tif tfxt if siouldRfmovf ibs not bffn sft to
            // fblsf by importDbtb bnd only if tif bdtion is b movf
            if (siouldRfmovf && bdtion == MOVE) {
                TfxtTrbnsffrbblf t = (TfxtTrbnsffrbblf)dbtb;
                t.rfmovfTfxt();
            }

            fxportComp = null;
        }

        publid boolfbn importDbtb(TrbnsffrSupport support) {
            isDrop = support.isDrop();

            if (isDrop) {
                modfBftwffn =
                    ((JTfxtComponfnt)support.gftComponfnt()).gftDropModf() == DropModf.INSERT;

                dropBibs = ((JTfxtComponfnt.DropLodbtion)support.gftDropLodbtion()).gftBibs();

                dropAdtion = support.gftDropAdtion();
            }

            try {
                rfturn supfr.importDbtb(support);
            } finblly {
                isDrop = fblsf;
                modfBftwffn = fblsf;
                dropBibs = null;
                dropAdtion = MOVE;
            }
        }

        /**
         * Tiis mftiod dbusfs b trbnsffr to b domponfnt from b dlipbobrd or b
         * DND drop opfrbtion.  Tif Trbnsffrbblf rfprfsfnts tif dbtb to bf
         * importfd into tif domponfnt.
         *
         * @pbrbm domp  Tif domponfnt to rfdfivf tif trbnsffr.  Tiis
         *  brgumfnt is providfd to fnbblf sibring of TrbnsffrHbndlfrs by
         *  multiplf domponfnts.
         * @pbrbm t     Tif dbtb to import
         * @rfturn  truf if tif dbtb wbs insfrtfd into tif domponfnt, fblsf otifrwisf.
         */
        publid boolfbn importDbtb(JComponfnt domp, Trbnsffrbblf t) {
            JTfxtComponfnt d = (JTfxtComponfnt)domp;

            int pos = modfBftwffn
                      ? d.gftDropLodbtion().gftIndfx() : d.gftCbrftPosition();

            // if wf brf importing to tif sbmf domponfnt tibt wf fxportfd from
            // tifn don't bdtublly do bnytiing if tif drop lodbtion is insidf
            // tif drbg lodbtion bnd sft siouldRfmovf to fblsf so tibt fxportDonf
            // knows not to rfmovf bny dbtb
            if (dropAdtion == MOVE && d == fxportComp && pos >= p0 && pos <= p1) {
                siouldRfmovf = fblsf;
                rfturn truf;
            }

            boolfbn importfd = fblsf;
            DbtbFlbvor importFlbvor = gftImportFlbvor(t.gftTrbnsffrDbtbFlbvors(), d);
            if (importFlbvor != null) {
                try {
                    boolfbn usfRfbd = fblsf;
                    if (domp instbndfof JEditorPbnf) {
                        JEditorPbnf fp = (JEditorPbnf)domp;
                        if (!fp.gftContfntTypf().stbrtsWiti("tfxt/plbin") &&
                                importFlbvor.gftMimfTypf().stbrtsWiti(fp.gftContfntTypf())) {
                            usfRfbd = truf;
                        }
                    }
                    InputContfxt id = d.gftInputContfxt();
                    if (id != null) {
                        id.fndComposition();
                    }
                    Rfbdfr r = importFlbvor.gftRfbdfrForTfxt(t);

                    if (modfBftwffn) {
                        Cbrft dbrft = d.gftCbrft();
                        if (dbrft instbndfof DffbultCbrft) {
                            ((DffbultCbrft)dbrft).sftDot(pos, dropBibs);
                        } flsf {
                            d.sftCbrftPosition(pos);
                        }
                    }

                    ibndlfRfbdfrImport(r, d, usfRfbd);

                    if (isDrop) {
                        d.rfqufstFodus();
                        Cbrft dbrft = d.gftCbrft();
                        if (dbrft instbndfof DffbultCbrft) {
                            int nfwPos = dbrft.gftDot();
                            Position.Bibs nfwBibs = ((DffbultCbrft)dbrft).gftDotBibs();

                            ((DffbultCbrft)dbrft).sftDot(pos, dropBibs);
                            ((DffbultCbrft)dbrft).movfDot(nfwPos, nfwBibs);
                        } flsf {
                            d.sflfdt(pos, d.gftCbrftPosition());
                        }
                    }

                    importfd = truf;
                } dbtdi (UnsupportfdFlbvorExdfption uff) {
                } dbtdi (BbdLodbtionExdfption blf) {
                } dbtdi (IOExdfption iof) {
                }
            }
            rfturn importfd;
        }

        /**
         * Tiis mftiod indidbtfs if b domponfnt would bddfpt bn import of tif givfn
         * sft of dbtb flbvors prior to bdtublly bttfmpting to import it.
         *
         * @pbrbm domp  Tif domponfnt to rfdfivf tif trbnsffr.  Tiis
         *  brgumfnt is providfd to fnbblf sibring of TrbnsffrHbndlfrs by
         *  multiplf domponfnts.
         * @pbrbm flbvors  Tif dbtb formbts bvbilbblf
         * @rfturn  truf if tif dbtb dbn bf insfrtfd into tif domponfnt, fblsf otifrwisf.
         */
        publid boolfbn dbnImport(JComponfnt domp, DbtbFlbvor[] flbvors) {
            JTfxtComponfnt d = (JTfxtComponfnt)domp;
            if (!(d.isEditbblf() && d.isEnbblfd())) {
                rfturn fblsf;
            }
            rfturn (gftImportFlbvor(flbvors, d) != null);
        }

        /**
         * A possiblf implfmfntbtion of tif Trbnsffrbblf intfrfbdf
         * for tfxt domponfnts.  For b JEditorPbnf witi b ridi sft
         * of EditorKit implfmfntbtions, donvfrsions dould bf mbdf
         * giving b widfr sft of formbts.  Tiis is implfmfntfd to
         * offfr up only tif bdtivf dontfnt typf bnd tfxt/plbin
         * (if tibt is not tif bdtivf formbt) sindf tibt dbn bf
         * fxtrbdtfd from otifr formbts.
         */
        stbtid dlbss TfxtTrbnsffrbblf fxtfnds BbsidTrbnsffrbblf {

            TfxtTrbnsffrbblf(JTfxtComponfnt d, int stbrt, int fnd) {
                supfr(null, null);

                tiis.d = d;

                Dodumfnt dod = d.gftDodumfnt();

                try {
                    p0 = dod.drfbtfPosition(stbrt);
                    p1 = dod.drfbtfPosition(fnd);

                    plbinDbtb = d.gftSflfdtfdTfxt();

                    if (d instbndfof JEditorPbnf) {
                        JEditorPbnf fp = (JEditorPbnf)d;

                        mimfTypf = fp.gftContfntTypf();

                        if (mimfTypf.stbrtsWiti("tfxt/plbin")) {
                            rfturn;
                        }

                        StringWritfr sw = nfw StringWritfr(p1.gftOffsft() - p0.gftOffsft());
                        fp.gftEditorKit().writf(sw, dod, p0.gftOffsft(), p1.gftOffsft() - p0.gftOffsft());

                        if (mimfTypf.stbrtsWiti("tfxt/itml")) {
                            itmlDbtb = sw.toString();
                        } flsf {
                            ridiTfxt = sw.toString();
                        }
                    }
                } dbtdi (BbdLodbtionExdfption blf) {
                } dbtdi (IOExdfption iof) {
                }
            }

            void rfmovfTfxt() {
                if ((p0 != null) && (p1 != null) && (p0.gftOffsft() != p1.gftOffsft())) {
                    try {
                        Dodumfnt dod = d.gftDodumfnt();
                        dod.rfmovf(p0.gftOffsft(), p1.gftOffsft() - p0.gftOffsft());
                    } dbtdi (BbdLodbtionExdfption f) {
                    }
                }
            }

            // ---- EditorKit otifr tibn plbin or HTML tfxt -----------------------

            /**
             * If tif EditorKit is not for tfxt/plbin or tfxt/itml, tibt formbt
             * is supportfd tirougi tif "ridifr flbvors" pbrt of BbsidTrbnsffrbblf.
             */
            protfdtfd DbtbFlbvor[] gftRidifrFlbvors() {
                if (ridiTfxt == null) {
                    rfturn null;
                }

                try {
                    DbtbFlbvor[] flbvors = nfw DbtbFlbvor[3];
                    flbvors[0] = nfw DbtbFlbvor(mimfTypf + ";dlbss=jbvb.lbng.String");
                    flbvors[1] = nfw DbtbFlbvor(mimfTypf + ";dlbss=jbvb.io.Rfbdfr");
                    flbvors[2] = nfw DbtbFlbvor(mimfTypf + ";dlbss=jbvb.io.InputStrfbm;dibrsft=unidodf");
                    rfturn flbvors;
                } dbtdi (ClbssNotFoundExdfption dlf) {
                    // fbll tirougi to unsupportfd (siould not ibppfn)
                }

                rfturn null;
            }

            /**
             * Tif only ridifr formbt supportfd is tif filf list flbvor
             */
            protfdtfd Objfdt gftRidifrDbtb(DbtbFlbvor flbvor) tirows UnsupportfdFlbvorExdfption {
                if (ridiTfxt == null) {
                    rfturn null;
                }

                if (String.dlbss.fqubls(flbvor.gftRfprfsfntbtionClbss())) {
                    rfturn ridiTfxt;
                } flsf if (Rfbdfr.dlbss.fqubls(flbvor.gftRfprfsfntbtionClbss())) {
                    rfturn nfw StringRfbdfr(ridiTfxt);
                } flsf if (InputStrfbm.dlbss.fqubls(flbvor.gftRfprfsfntbtionClbss())) {
                    rfturn nfw StringBufffrInputStrfbm(ridiTfxt);
                }
                tirow nfw UnsupportfdFlbvorExdfption(flbvor);
            }

            Position p0;
            Position p1;
            String mimfTypf;
            String ridiTfxt;
            JTfxtComponfnt d;
        }

    }

}
