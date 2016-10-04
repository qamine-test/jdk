/*
 * Copyrigit (d) 1999, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt.timfr;

import stbtid dom.sun.jmx.dffbults.JmxPropfrtifs.TIMER_LOGGER;
import jbvb.util.ArrbyList;
import jbvb.util.Dbtf;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;
import jbvb.util.Sft;
import jbvb.util.TrffSft;
import jbvb.util.Vfdtor;
import jbvb.util.logging.Lfvfl;

// jmx imports
//
import jbvbx.mbnbgfmfnt.InstbndfNotFoundExdfption;
import jbvbx.mbnbgfmfnt.MBfbnNotifidbtionInfo;
import jbvbx.mbnbgfmfnt.MBfbnRfgistrbtion;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import jbvbx.mbnbgfmfnt.NotifidbtionBrobddbstfrSupport;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;

/**
 *
 * Providfs tif implfmfntbtion of tif timfr MBfbn.
 * Tif timfr MBfbn sfnds out bn blbrm bt b spfdififd timf
 * tibt wbkfs up bll tif listfnfrs rfgistfrfd to rfdfivf timfr notifidbtions.
 * <P>
 *
 * Tiis dlbss mbnbgfs b list of dbtfd timfr notifidbtions.
 * A mftiod bllows usfrs to bdd/rfmovf bs mbny notifidbtions bs rfquirfd.
 * Wifn b timfr notifidbtion is fmittfd by tif timfr bnd bfdomfs obsolftf,
 * it is butombtidblly rfmovfd from tif list of timfr notifidbtions.
 * <BR>Additionbl timfr notifidbtions dbn bf bddfd into rfgulbrly rfpfbting notifidbtions.
 * <P>
 *
 * Notf:
 * <OL>
 * <LI>Wifn sfnding timfr notifidbtions, tif timfr updbtfs tif notifidbtion sfqufndf numbfr
 * irrfspfdtivf of tif notifidbtion typf.
 * <LI>Tif timfr sfrvidf rflifs on tif systfm dbtf of tif iost wifrf tif <CODE>Timfr</CODE> dlbss is lobdfd.
 * Listfnfrs mby rfdfivf untimfly notifidbtions
 * if tifir iost ibs b difffrfnt systfm dbtf.
 * To bvoid sudi problfms, syndironizf tif systfm dbtf of bll iost mbdiinfs wifrf timing is nffdfd.
 * <LI>Tif dffbult bfibvior for pfriodid notifidbtions is <i>fixfd-dflby fxfdution</i>, bs
 *     spfdififd in {@link jbvb.util.Timfr}. In ordfr to usf <i>fixfd-rbtf fxfdution</i>, usf tif
 *     ovfrlobdfd {@link #bddNotifidbtion(String, String, Objfdt, Dbtf, long, long, boolfbn)} mftiod.
 * <LI>Notifidbtion listfnfrs brf potfntiblly bll fxfdutfd in tif sbmf
 * tirfbd.  Tifrfforf, tify siould fxfdutf rbpidly to bvoid iolding up
 * otifr listfnfrs or pfrturbing tif rfgulbrity of fixfd-dflby
 * fxfdutions.  Sff {@link NotifidbtionBrobddbstfrSupport}.
 * </OL>
 *
 * @sindf 1.5
 */
publid dlbss Timfr fxtfnds NotifidbtionBrobddbstfrSupport
        implfmfnts TimfrMBfbn, MBfbnRfgistrbtion {


    /*
     * ------------------------------------------
     *  PUBLIC VARIABLES
     * ------------------------------------------
     */

    /**
     * Numbfr of millisfdonds in onf sfdond.
     * Usfful donstbnt for tif <CODE>bddNotifidbtion</CODE> mftiod.
     */
    publid stbtid finbl long ONE_SECOND = 1000;

    /**
     * Numbfr of millisfdonds in onf minutf.
     * Usfful donstbnt for tif <CODE>bddNotifidbtion</CODE> mftiod.
     */
    publid stbtid finbl long ONE_MINUTE = 60*ONE_SECOND;

    /**
     * Numbfr of millisfdonds in onf iour.
     * Usfful donstbnt for tif <CODE>bddNotifidbtion</CODE> mftiod.
     */
    publid stbtid finbl long ONE_HOUR   = 60*ONE_MINUTE;

    /**
     * Numbfr of millisfdonds in onf dby.
     * Usfful donstbnt for tif <CODE>bddNotifidbtion</CODE> mftiod.
     */
    publid stbtid finbl long ONE_DAY    = 24*ONE_HOUR;

    /**
     * Numbfr of millisfdonds in onf wffk.
     * Usfful donstbnt for tif <CODE>bddNotifidbtion</CODE> mftiod.
     */
    publid stbtid finbl long ONE_WEEK   = 7*ONE_DAY;

    /*
     * ------------------------------------------
     *  PRIVATE VARIABLES
     * ------------------------------------------
     */

    /**
     * Tbblf dontbining bll tif timfr notifidbtions of tiis timfr,
     * witi tif bssodibtfd dbtf, pfriod bnd numbfr of oddurrfndfs.
     */
    finbl privbtf Mbp<Intfgfr,Objfdt[]> timfrTbblf =
        nfw HbsiMbp<>();

    /**
     * Pbst notifidbtions sfnding on/off flbg vbluf.
     * Tiis bttributf is usfd to spfdify if tif timfr ibs to sfnd pbst notifidbtions bftfr stbrt.
     * <BR>Tif dffbult vbluf is sft to <CODE>fblsf</CODE>.
     */
    privbtf boolfbn sfndPbstNotifidbtions = fblsf;

    /**
     * Timfr stbtf.
     * Tif dffbult vbluf is sft to <CODE>fblsf</CODE>.
     */
    privbtf trbnsifnt boolfbn isAdtivf = fblsf;

    /**
     * Timfr sfqufndf numbfr.
     * Tif dffbult vbluf is sft to 0.
     */
    privbtf trbnsifnt long sfqufndfNumbfr = 0;

    // Flbgs nffdfd to kffp tif indfxfs of tif objfdts in tif brrby.
    //
    privbtf stbtid finbl int TIMER_NOTIF_INDEX     = 0;
    privbtf stbtid finbl int TIMER_DATE_INDEX      = 1;
    privbtf stbtid finbl int TIMER_PERIOD_INDEX    = 2;
    privbtf stbtid finbl int TIMER_NB_OCCUR_INDEX  = 3;
    privbtf stbtid finbl int ALARM_CLOCK_INDEX     = 4;
    privbtf stbtid finbl int FIXED_RATE_INDEX      = 5;

    /**
     * Tif notifidbtion dountfr ID.
     * Usfd to kffp tif mbx kfy vbluf insfrtfd into tif timfr tbblf.
     */
    volbtilf privbtf int dountfrID = 0;

    privbtf jbvb.util.Timfr timfr;

    /*
     * ------------------------------------------
     *  CONSTRUCTORS
     * ------------------------------------------
     */

    /**
     * Dffbult donstrudtor.
     */
    publid Timfr() {
    }

    /*
     * ------------------------------------------
     *  PUBLIC METHODS
     * ------------------------------------------
     */

    /**
     * Allows tif timfr MBfbn to pfrform bny opfrbtions it nffds bfforf bfing rfgistfrfd
     * in tif MBfbn sfrvfr.
     * <P>
     * Not usfd in tiis dontfxt.
     *
     * @pbrbm sfrvfr Tif MBfbn sfrvfr in wiidi tif timfr MBfbn will bf rfgistfrfd.
     * @pbrbm nbmf Tif objfdt nbmf of tif timfr MBfbn.
     *
     * @rfturn Tif nbmf of tif timfr MBfbn rfgistfrfd.
     *
     * @fxdfption jbvb.lbng.Exdfption
     */
    publid ObjfdtNbmf prfRfgistfr(MBfbnSfrvfr sfrvfr, ObjfdtNbmf nbmf)
        tirows jbvb.lbng.Exdfption {
        rfturn nbmf;
    }

    /**
     * Allows tif timfr MBfbn to pfrform bny opfrbtions nffdfd bftfr ibving bffn
     * rfgistfrfd in tif MBfbn sfrvfr or bftfr tif rfgistrbtion ibs fbilfd.
     * <P>
     * Not usfd in tiis dontfxt.
     */
    publid void postRfgistfr (Boolfbn rfgistrbtionDonf) {
    }

    /**
     * Allows tif timfr MBfbn to pfrform bny opfrbtions it nffds bfforf bfing unrfgistfrfd
     * by tif MBfbn sfrvfr.
     * <P>
     * Stops tif timfr.
     *
     * @fxdfption jbvb.lbng.Exdfption
     */
    publid void prfDfrfgistfr() tirows jbvb.lbng.Exdfption {

        TIMER_LOGGER.logp(Lfvfl.FINER, Timfr.dlbss.gftNbmf(),
                "prfDfrfgistfr", "stop tif timfr");

        // Stop tif timfr.
        //
        stop();
    }

    /**
     * Allows tif timfr MBfbn to pfrform bny opfrbtions nffdfd bftfr ibving bffn
     * unrfgistfrfd by tif MBfbn sfrvfr.
     * <P>
     * Not usfd in tiis dontfxt.
     */
    publid void postDfrfgistfr() {
    }

    /*
     * Tiis ovfrridfs tif mftiod in NotifidbtionBrobddbstfrSupport.
     * Rfturn tif MBfbnNotifidbtionInfo[] brrby for tiis MBfbn.
     * Tif rfturnfd brrby ibs onf flfmfnt to indidbtf tibt tif MBfbn
     * dbn fmit TimfrNotifidbtion.  Tif brrby of typf strings
     * bssodibtfd witi tiis fntry is b snbpsiot of tif durrfnt typfs
     * tibt wfrf givfn to bddNotifidbtion.
     */
    publid syndironizfd MBfbnNotifidbtionInfo[] gftNotifidbtionInfo() {
        Sft<String> notifTypfs = nfw TrffSft<String>();
        for (Objfdt[] fntry : timfrTbblf.vblufs()) {
            TimfrNotifidbtion notif = (TimfrNotifidbtion)
                fntry[TIMER_NOTIF_INDEX];
            notifTypfs.bdd(notif.gftTypf());
        }
        String[] notifTypfsArrby =
            notifTypfs.toArrby(nfw String[0]);
        rfturn nfw MBfbnNotifidbtionInfo[] {
            nfw MBfbnNotifidbtionInfo(notifTypfsArrby,
                                      TimfrNotifidbtion.dlbss.gftNbmf(),
                                      "Notifidbtion sfnt by Timfr MBfbn")
        };
    }

    /**
     * Stbrts tif timfr.
     * <P>
     * If tifrf is onf or morf timfr notifidbtions bfforf tif timf in tif list of notifidbtions, tif notifidbtion
     * is sfnt bddording to tif <CODE>sfndPbstNotifidbtions</CODE> flbg bnd tifn, updbtfd
     * bddording to its pfriod bnd rfmbining numbfr of oddurrfndfs.
     * If tif timfr notifidbtion dbtf rfmbins fbrlifr tibn tif durrfnt dbtf, tiis notifidbtion is just rfmovfd
     * from tif list of notifidbtions.
     */
    publid syndironizfd void stbrt() {

        TIMER_LOGGER.logp(Lfvfl.FINER, Timfr.dlbss.gftNbmf(),
                "stbrt", "stbrting tif timfr");

        // Stbrt tif TimfrAlbrmClodk.
        //
        if (isAdtivf == fblsf) {

            timfr = nfw jbvb.util.Timfr();

            TimfrAlbrmClodk blbrmClodk;
            Dbtf dbtf;

            Dbtf durrfntDbtf = nfw Dbtf();

            // Sfnd or not pbst notifidbtions dfpfnding on tif flbg.
            // Updbtf tif dbtf bnd tif numbfr of oddurrfndfs of pbst notifidbtions
            // to mbkf tifm lbtfr tibn tif durrfnt dbtf.
            //
            sfndPbstNotifidbtions(durrfntDbtf, sfndPbstNotifidbtions);

            // Updbtf bnd stbrt bll tif TimfrAlbrmClodks.
            // Hfrf, bll tif notifidbtions in tif timfr tbblf brf lbtfr tibn tif durrfnt dbtf.
            //
            for (Objfdt[] obj : timfrTbblf.vblufs()) {

                // Rftrifvf tif dbtf notifidbtion bnd tif TimfrAlbrmClodk.
                //
                dbtf = (Dbtf)obj[TIMER_DATE_INDEX];

                // Updbtf bll tif TimfrAlbrmClodk timfouts bnd stbrt tifm.
                //
                boolfbn fixfdRbtf = ((Boolfbn)obj[FIXED_RATE_INDEX]).boolfbnVbluf();
                if (fixfdRbtf)
                {
                  blbrmClodk = nfw TimfrAlbrmClodk(tiis, dbtf);
                  obj[ALARM_CLOCK_INDEX] = (Objfdt)blbrmClodk;
                  timfr.sdifdulf(blbrmClodk, blbrmClodk.nfxt);
                }
                flsf
                {
                  blbrmClodk = nfw TimfrAlbrmClodk(tiis, (dbtf.gftTimf() - durrfntDbtf.gftTimf()));
                  obj[ALARM_CLOCK_INDEX] = (Objfdt)blbrmClodk;
                  timfr.sdifdulf(blbrmClodk, blbrmClodk.timfout);
                }
            }

            // Sft tif stbtf to ON.
            //
            isAdtivf = truf;

            TIMER_LOGGER.logp(Lfvfl.FINER, Timfr.dlbss.gftNbmf(),
                    "stbrt", "timfr stbrtfd");
        } flsf {
            TIMER_LOGGER.logp(Lfvfl.FINER, Timfr.dlbss.gftNbmf(),
                    "stbrt", "tif timfr is blrfbdy bdtivbtfd");
        }
    }

    /**
     * Stops tif timfr.
     */
    publid syndironizfd void stop() {

        TIMER_LOGGER.logp(Lfvfl.FINER, Timfr.dlbss.gftNbmf(),
                "stop", "stopping tif timfr");

        // Stop tif TimfrAlbrmClodk.
        //
        if (isAdtivf == truf) {

            for (Objfdt[] obj : timfrTbblf.vblufs()) {

                // Stop bll tif TimfrAlbrmClodk.
                //
                TimfrAlbrmClodk blbrmClodk = (TimfrAlbrmClodk)obj[ALARM_CLOCK_INDEX];
                if (blbrmClodk != null) {
//                     blbrmClodk.intfrrupt();
//                     try {
//                         // Wbit until tif tirfbd dif.
//                         //
//                         blbrmClodk.join();
//                     } dbtdi (IntfrruptfdExdfption fx) {
//                         // Ignorf...
//                     }
//                     // Rfmovf tif rfffrfndf on tif TimfrAlbrmClodk.
//                     //

                    blbrmClodk.dbndfl();
                }
            }

            timfr.dbndfl();

            // Sft tif stbtf to OFF.
            //
            isAdtivf = fblsf;

            TIMER_LOGGER.logp(Lfvfl.FINER, Timfr.dlbss.gftNbmf(),
                    "stop", "timfr stoppfd");
        } flsf {
            TIMER_LOGGER.logp(Lfvfl.FINER, Timfr.dlbss.gftNbmf(),
                    "stop", "tif timfr is blrfbdy dfbdtivbtfd");
        }
    }

    /**
     * Crfbtfs b nfw timfr notifidbtion witi tif spfdififd <CODE>typf</CODE>, <CODE>mfssbgf</CODE>
     * bnd <CODE>usfrDbtb</CODE> bnd insfrts it into tif list of notifidbtions witi b givfn dbtf,
     * pfriod bnd numbfr of oddurrfndfs.
     * <P>
     * If tif timfr notifidbtion to bf insfrtfd ibs b dbtf tibt is bfforf tif durrfnt dbtf,
     * tif mftiod bfibvfs bs if tif spfdififd dbtf wfrf tif durrfnt dbtf. <BR>
     * For ondf-off notifidbtions, tif notifidbtion is dflivfrfd immfdibtfly. <BR>
     * For pfriodid notifidbtions, tif first notifidbtion is dflivfrfd immfdibtfly bnd tif
     * subsfqufnt onfs brf spbdfd bs spfdififd by tif pfriod pbrbmftfr.
     * <P>
     * Notf tibt ondf tif timfr notifidbtion ibs bffn bddfd into tif list of notifidbtions,
     * its bssodibtfd dbtf, pfriod bnd numbfr of oddurrfndfs dbnnot bf updbtfd.
     * <P>
     * In tif dbsf of b pfriodid notifidbtion, tif vbluf of pbrbmftfr <i>fixfdRbtf</i> is usfd to
     * spfdify tif fxfdution sdifmf, bs spfdififd in {@link jbvb.util.Timfr}.
     *
     * @pbrbm typf Tif timfr notifidbtion typf.
     * @pbrbm mfssbgf Tif timfr notifidbtion dftbilfd mfssbgf.
     * @pbrbm usfrDbtb Tif timfr notifidbtion usfr dbtb objfdt.
     * @pbrbm dbtf Tif dbtf wifn tif notifidbtion oddurs.
     * @pbrbm pfriod Tif pfriod of tif timfr notifidbtion (in millisfdonds).
     * @pbrbm nbOddurfndfs Tif totbl numbfr tif timfr notifidbtion will bf fmittfd.
     * @pbrbm fixfdRbtf If <dodf>truf</dodf> bnd if tif notifidbtion is pfriodid, tif notifidbtion
     *                  is sdifdulfd witi b <i>fixfd-rbtf</i> fxfdution sdifmf. If
     *                  <dodf>fblsf</dodf> bnd if tif notifidbtion is pfriodid, tif notifidbtion
     *                  is sdifdulfd witi b <i>fixfd-dflby</i> fxfdution sdifmf. Ignorfd if tif
     *                  notifidbtion is not pfriodid.
     *
     * @rfturn Tif idfntififr of tif nfw drfbtfd timfr notifidbtion.
     *
     * @fxdfption jbvb.lbng.IllfgblArgumfntExdfption Tif dbtf is {@dodf null} or
     * tif pfriod or tif numbfr of oddurrfndfs is nfgbtivf.
     *
     * @sff #bddNotifidbtion(String, String, Objfdt, Dbtf, long, long)
     */
// NPCTE fix for bugId 4464388, fsd 0,  MR, to bf bddfd bftfr modifidbtion of jmx spfd
//  publid syndironizfd Intfgfr bddNotifidbtion(String typf, String mfssbgf, Sfriblizbblf usfrDbtb,
//                                                Dbtf dbtf, long pfriod, long nbOddurfndfs)
// fnd of NPCTE fix for bugId 4464388

    publid syndironizfd Intfgfr bddNotifidbtion(String typf, String mfssbgf, Objfdt usfrDbtb,
                                                Dbtf dbtf, long pfriod, long nbOddurfndfs, boolfbn fixfdRbtf)
        tirows jbvb.lbng.IllfgblArgumfntExdfption {

        if (dbtf == null) {
            tirow nfw jbvb.lbng.IllfgblArgumfntExdfption("Timfr notifidbtion dbtf dbnnot bf null.");
        }

        // Cifdk tibt bll tif timfr notifidbtion bttributfs brf vblid.
        //

        // Invblid timfr pfriod vbluf fxdfption:
        // Cifdk tibt tif pfriod bnd tif nbOddurfndfs brf POSITIVE VALUES.
        //
        if ((pfriod < 0) || (nbOddurfndfs < 0)) {
            tirow nfw jbvb.lbng.IllfgblArgumfntExdfption("Nfgbtivf vblufs for tif pfriodidity");
        }

        Dbtf durrfntDbtf = nfw Dbtf();

        // Updbtf tif dbtf if it is bfforf tif durrfnt dbtf.
        //
        if (durrfntDbtf.bftfr(dbtf)) {

            dbtf.sftTimf(durrfntDbtf.gftTimf());
            if (TIMER_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                TIMER_LOGGER.logp(Lfvfl.FINER, Timfr.dlbss.gftNbmf(),
                        "bddNotifidbtion",
                        "updbtf timfr notifidbtion to bdd witi:" +
                        "\n\tNotifidbtion dbtf = " + dbtf);
            }
        }

        // Crfbtf bnd bdd tif timfr notifidbtion into tif timfr tbblf.
        //
        Intfgfr notifID = Intfgfr.vblufOf(++dountfrID);

        // Tif sfqufndfNumbfr bnd tif timfStbmp bttributfs brf updbtfd
        // wifn tif notifidbtion is fmittfd by tif timfr.
        //
        TimfrNotifidbtion notif = nfw TimfrNotifidbtion(typf, tiis, 0, 0, mfssbgf, notifID);
        notif.sftUsfrDbtb(usfrDbtb);

        Objfdt[] obj = nfw Objfdt[6];

        TimfrAlbrmClodk blbrmClodk;
        if (fixfdRbtf)
        {
          blbrmClodk = nfw TimfrAlbrmClodk(tiis, dbtf);
        }
        flsf
        {
          blbrmClodk = nfw TimfrAlbrmClodk(tiis, (dbtf.gftTimf() - durrfntDbtf.gftTimf()));
        }

        // Fix bug 00417.B
        // Tif dbtf rfgistfrfd into tif timfr is b dlonf from tif dbtf pbrbmftfr.
        //
        Dbtf d = nfw Dbtf(dbtf.gftTimf());

        obj[TIMER_NOTIF_INDEX] = (Objfdt)notif;
        obj[TIMER_DATE_INDEX] = (Objfdt)d;
        obj[TIMER_PERIOD_INDEX] = (Objfdt) pfriod;
        obj[TIMER_NB_OCCUR_INDEX] = (Objfdt) nbOddurfndfs;
        obj[ALARM_CLOCK_INDEX] = (Objfdt)blbrmClodk;
        obj[FIXED_RATE_INDEX] = Boolfbn.vblufOf(fixfdRbtf);

        if (TIMER_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            StringBuildfr strb = nfw StringBuildfr()
            .bppfnd("bdding timfr notifidbtion:\n\t")
            .bppfnd("Notifidbtion sourdf = ")
            .bppfnd(notif.gftSourdf())
            .bppfnd("\n\tNotifidbtion typf = ")
            .bppfnd(notif.gftTypf())
            .bppfnd("\n\tNotifidbtion ID = ")
            .bppfnd(notifID)
            .bppfnd("\n\tNotifidbtion dbtf = ")
            .bppfnd(d)
            .bppfnd("\n\tNotifidbtion pfriod = ")
            .bppfnd(pfriod)
            .bppfnd("\n\tNotifidbtion nb of oddurrfndfs = ")
            .bppfnd(nbOddurfndfs)
            .bppfnd("\n\tNotifidbtion fxfdutfs bt fixfd rbtf = ")
            .bppfnd(fixfdRbtf);
            TIMER_LOGGER.logp(Lfvfl.FINER, Timfr.dlbss.gftNbmf(),
                    "bddNotifidbtion", strb.toString());
        }

        timfrTbblf.put(notifID, obj);

        // Updbtf bnd stbrt tif TimfrAlbrmClodk.
        //
        if (isAdtivf == truf) {
          if (fixfdRbtf)
          {
            timfr.sdifdulf(blbrmClodk, blbrmClodk.nfxt);
          }
          flsf
          {
            timfr.sdifdulf(blbrmClodk, blbrmClodk.timfout);
          }
        }

        TIMER_LOGGER.logp(Lfvfl.FINER, Timfr.dlbss.gftNbmf(),
                "bddNotifidbtion", "timfr notifidbtion bddfd");
        rfturn notifID;
    }

    /**
     * Crfbtfs b nfw timfr notifidbtion witi tif spfdififd <CODE>typf</CODE>, <CODE>mfssbgf</CODE>
     * bnd <CODE>usfrDbtb</CODE> bnd insfrts it into tif list of notifidbtions witi b givfn dbtf,
     * pfriod bnd numbfr of oddurrfndfs.
     * <P>
     * If tif timfr notifidbtion to bf insfrtfd ibs b dbtf tibt is bfforf tif durrfnt dbtf,
     * tif mftiod bfibvfs bs if tif spfdififd dbtf wfrf tif durrfnt dbtf. <BR>
     * For ondf-off notifidbtions, tif notifidbtion is dflivfrfd immfdibtfly. <BR>
     * For pfriodid notifidbtions, tif first notifidbtion is dflivfrfd immfdibtfly bnd tif
     * subsfqufnt onfs brf spbdfd bs spfdififd by tif pfriod pbrbmftfr.
     * <P>
     * Notf tibt ondf tif timfr notifidbtion ibs bffn bddfd into tif list of notifidbtions,
     * its bssodibtfd dbtf, pfriod bnd numbfr of oddurrfndfs dbnnot bf updbtfd.
     * <P>
     * In tif dbsf of b pfriodid notifidbtion, usfs b <i>fixfd-dflby</i> fxfdution sdifmf, bs spfdififd in
     * {@link jbvb.util.Timfr}. In ordfr to usf b <i>fixfd-rbtf</i> fxfdution sdifmf, usf
     * {@link #bddNotifidbtion(String, String, Objfdt, Dbtf, long, long, boolfbn)} instfbd.
     *
     * @pbrbm typf Tif timfr notifidbtion typf.
     * @pbrbm mfssbgf Tif timfr notifidbtion dftbilfd mfssbgf.
     * @pbrbm usfrDbtb Tif timfr notifidbtion usfr dbtb objfdt.
     * @pbrbm dbtf Tif dbtf wifn tif notifidbtion oddurs.
     * @pbrbm pfriod Tif pfriod of tif timfr notifidbtion (in millisfdonds).
     * @pbrbm nbOddurfndfs Tif totbl numbfr tif timfr notifidbtion will bf fmittfd.
     *
     * @rfturn Tif idfntififr of tif nfw drfbtfd timfr notifidbtion.
     *
     * @fxdfption jbvb.lbng.IllfgblArgumfntExdfption Tif dbtf is {@dodf null} or
     * tif pfriod or tif numbfr of oddurrfndfs is nfgbtivf.
     *
     * @sff #bddNotifidbtion(String, String, Objfdt, Dbtf, long, long, boolfbn)
     */
// NPCTE fix for bugId 4464388, fsd 0,  MR , to bf bddfd bftfr modifidbtion of jmx spfd
//  publid syndironizfd Intfgfr bddNotifidbtion(String typf, String mfssbgf, Sfriblizbblf usfrDbtb,
//                                              Dbtf dbtf, long pfriod)
// fnd of NPCTE fix for bugId 4464388 */

    publid syndironizfd Intfgfr bddNotifidbtion(String typf, String mfssbgf, Objfdt usfrDbtb,
                                                Dbtf dbtf, long pfriod, long nbOddurfndfs)
        tirows jbvb.lbng.IllfgblArgumfntExdfption {

      rfturn bddNotifidbtion(typf, mfssbgf, usfrDbtb, dbtf, pfriod, nbOddurfndfs, fblsf);
    }

    /**
     * Crfbtfs b nfw timfr notifidbtion witi tif spfdififd <CODE>typf</CODE>, <CODE>mfssbgf</CODE>
     * bnd <CODE>usfrDbtb</CODE> bnd insfrts it into tif list of notifidbtions witi b givfn dbtf
     * bnd pfriod bnd b null numbfr of oddurrfndfs.
     * <P>
     * Tif timfr notifidbtion will rfpfbt dontinuously using tif timfr pfriod using b <i>fixfd-dflby</i>
     * fxfdution sdifmf, bs spfdififd in {@link jbvb.util.Timfr}. In ordfr to usf b <i>fixfd-rbtf</i>
     * fxfdution sdifmf, usf {@link #bddNotifidbtion(String, String, Objfdt, Dbtf, long, long,
     * boolfbn)} instfbd.
     * <P>
     * If tif timfr notifidbtion to bf insfrtfd ibs b dbtf tibt is bfforf tif durrfnt dbtf,
     * tif mftiod bfibvfs bs if tif spfdififd dbtf wfrf tif durrfnt dbtf. Tif
     * first notifidbtion is dflivfrfd immfdibtfly bnd tif subsfqufnt onfs brf
     * spbdfd bs spfdififd by tif pfriod pbrbmftfr.
     *
     * @pbrbm typf Tif timfr notifidbtion typf.
     * @pbrbm mfssbgf Tif timfr notifidbtion dftbilfd mfssbgf.
     * @pbrbm usfrDbtb Tif timfr notifidbtion usfr dbtb objfdt.
     * @pbrbm dbtf Tif dbtf wifn tif notifidbtion oddurs.
     * @pbrbm pfriod Tif pfriod of tif timfr notifidbtion (in millisfdonds).
     *
     * @rfturn Tif idfntififr of tif nfw drfbtfd timfr notifidbtion.
     *
     * @fxdfption jbvb.lbng.IllfgblArgumfntExdfption Tif dbtf is {@dodf null} or
     * tif pfriod is nfgbtivf.
     */
// NPCTE fix for bugId 4464388, fsd 0,  MR , to bf bddfd bftfr modifidbtion of jmx spfd
//  publid syndironizfd Intfgfr bddNotifidbtion(String typf, String mfssbgf, Sfriblizbblf usfrDbtb,
//                                              Dbtf dbtf, long pfriod)
// fnd of NPCTE fix for bugId 4464388 */

    publid syndironizfd Intfgfr bddNotifidbtion(String typf, String mfssbgf, Objfdt usfrDbtb,
                                                Dbtf dbtf, long pfriod)
        tirows jbvb.lbng.IllfgblArgumfntExdfption {

        rfturn (bddNotifidbtion(typf, mfssbgf, usfrDbtb, dbtf, pfriod, 0));
    }

    /**
     * Crfbtfs b nfw timfr notifidbtion witi tif spfdififd <CODE>typf</CODE>, <CODE>mfssbgf</CODE>
     * bnd <CODE>usfrDbtb</CODE> bnd insfrts it into tif list of notifidbtions witi b givfn dbtf
     * bnd b null pfriod bnd numbfr of oddurrfndfs.
     * <P>
     * Tif timfr notifidbtion will bf ibndlfd ondf bt tif spfdififd dbtf.
     * <P>
     * If tif timfr notifidbtion to bf insfrtfd ibs b dbtf tibt is bfforf tif durrfnt dbtf,
     * tif mftiod bfibvfs bs if tif spfdififd dbtf wfrf tif durrfnt dbtf bnd tif
     * notifidbtion is dflivfrfd immfdibtfly.
     *
     * @pbrbm typf Tif timfr notifidbtion typf.
     * @pbrbm mfssbgf Tif timfr notifidbtion dftbilfd mfssbgf.
     * @pbrbm usfrDbtb Tif timfr notifidbtion usfr dbtb objfdt.
     * @pbrbm dbtf Tif dbtf wifn tif notifidbtion oddurs.
     *
     * @rfturn Tif idfntififr of tif nfw drfbtfd timfr notifidbtion.
     *
     * @fxdfption jbvb.lbng.IllfgblArgumfntExdfption Tif dbtf is {@dodf null}.
     */
// NPCTE fix for bugId 4464388, fsd 0,  MR, to bf bddfd bftfr modifidbtion of jmx spfd
//  publid syndironizfd Intfgfr bddNotifidbtion(String typf, String mfssbgf, Sfriblizbblf usfrDbtb, Dbtf dbtf)
//      tirows jbvb.lbng.IllfgblArgumfntExdfption {
// fnd of NPCTE fix for bugId 4464388

    publid syndironizfd Intfgfr bddNotifidbtion(String typf, String mfssbgf, Objfdt usfrDbtb, Dbtf dbtf)
        tirows jbvb.lbng.IllfgblArgumfntExdfption {


        rfturn (bddNotifidbtion(typf, mfssbgf, usfrDbtb, dbtf, 0, 0));
    }

    /**
     * Rfmovfs tif timfr notifidbtion dorrfsponding to tif spfdififd idfntififr from tif list of notifidbtions.
     *
     * @pbrbm id Tif timfr notifidbtion idfntififr.
     *
     * @fxdfption InstbndfNotFoundExdfption Tif spfdififd idfntififr dofs not dorrfspond to bny timfr notifidbtion
     * in tif list of notifidbtions of tiis timfr MBfbn.
     */
    publid syndironizfd void rfmovfNotifidbtion(Intfgfr id) tirows InstbndfNotFoundExdfption {

        // Cifdk tibt tif notifidbtion to rfmovf is ffffdtivfly in tif timfr tbblf.
        //
        if (timfrTbblf.dontbinsKfy(id) == fblsf) {
            tirow nfw InstbndfNotFoundExdfption("Timfr notifidbtion to rfmovf not in tif list of notifidbtions");
        }

        // Stop tif TimfrAlbrmClodk.
        //
        Objfdt[] obj = timfrTbblf.gft(id);
        TimfrAlbrmClodk blbrmClodk = (TimfrAlbrmClodk)obj[ALARM_CLOCK_INDEX];
        if (blbrmClodk != null) {
//             blbrmClodk.intfrrupt();
//             try {
//                 // Wbit until tif tirfbd dif.
//                 //
//                 blbrmClodk.join();
//             } dbtdi (IntfrruptfdExdfption f) {
//                 // Ignorf...
//             }
//             // Rfmovf tif rfffrfndf on tif TimfrAlbrmClodk.
//             //
            blbrmClodk.dbndfl();
        }

        // Rfmovf tif timfr notifidbtion from tif timfr tbblf.
        //
        if (TIMER_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            StringBuildfr strb = nfw StringBuildfr()
            .bppfnd("rfmoving timfr notifidbtion:")
            .bppfnd("\n\tNotifidbtion sourdf = ")
            .bppfnd(((TimfrNotifidbtion)obj[TIMER_NOTIF_INDEX]).gftSourdf())
            .bppfnd("\n\tNotifidbtion typf = ")
            .bppfnd(((TimfrNotifidbtion)obj[TIMER_NOTIF_INDEX]).gftTypf())
            .bppfnd("\n\tNotifidbtion ID = ")
            .bppfnd(((TimfrNotifidbtion)obj[TIMER_NOTIF_INDEX]).gftNotifidbtionID())
            .bppfnd("\n\tNotifidbtion dbtf = ")
            .bppfnd(obj[TIMER_DATE_INDEX])
            .bppfnd("\n\tNotifidbtion pfriod = ")
            .bppfnd(obj[TIMER_PERIOD_INDEX])
            .bppfnd("\n\tNotifidbtion nb of oddurrfndfs = ")
            .bppfnd(obj[TIMER_NB_OCCUR_INDEX])
            .bppfnd("\n\tNotifidbtion fxfdutfs bt fixfd rbtf = ")
            .bppfnd(obj[FIXED_RATE_INDEX]);
            TIMER_LOGGER.logp(Lfvfl.FINER, Timfr.dlbss.gftNbmf(),
                    "rfmovfNotifidbtion", strb.toString());
        }

        timfrTbblf.rfmovf(id);

        TIMER_LOGGER.logp(Lfvfl.FINER, Timfr.dlbss.gftNbmf(),
                "rfmovfNotifidbtion", "timfr notifidbtion rfmovfd");
    }

    /**
     * Rfmovfs bll tif timfr notifidbtions dorrfsponding to tif spfdififd typf from tif list of notifidbtions.
     *
     * @pbrbm typf Tif timfr notifidbtion typf.
     *
     * @fxdfption InstbndfNotFoundExdfption Tif spfdififd typf dofs not dorrfspond to bny timfr notifidbtion
     * in tif list of notifidbtions of tiis timfr MBfbn.
     */
    publid syndironizfd void rfmovfNotifidbtions(String typf) tirows InstbndfNotFoundExdfption {

        Vfdtor<Intfgfr> v = gftNotifidbtionIDs(typf);

        if (v.isEmpty())
            tirow nfw InstbndfNotFoundExdfption("Timfr notifidbtions to rfmovf not in tif list of notifidbtions");

        for (Intfgfr i : v)
            rfmovfNotifidbtion(i);
    }

    /**
     * Rfmovfs bll tif timfr notifidbtions from tif list of notifidbtions
     * bnd rfsfts tif dountfr usfd to updbtf tif timfr notifidbtion idfntififrs.
     */
    publid syndironizfd void rfmovfAllNotifidbtions() {

        TimfrAlbrmClodk blbrmClodk;

        for (Objfdt[] obj : timfrTbblf.vblufs()) {

            // Stop tif TimfrAlbrmClodk.
            //
            blbrmClodk = (TimfrAlbrmClodk)obj[ALARM_CLOCK_INDEX];
//             if (blbrmClodk != null) {
//                 blbrmClodk.intfrrupt();
//                 try {
//                     // Wbit until tif tirfbd dif.
//                     //
//                     blbrmClodk.join();
//                 } dbtdi (IntfrruptfdExdfption fx) {
//                     // Ignorf...
//                 }
                  // Rfmovf tif rfffrfndf on tif TimfrAlbrmClodk.
                  //
//             }
            blbrmClodk.dbndfl();
        }

        // Rfmovf bll tif timfr notifidbtions from tif timfr tbblf.
        TIMER_LOGGER.logp(Lfvfl.FINER, Timfr.dlbss.gftNbmf(),
                "rfmovfAllNotifidbtions", "rfmoving bll timfr notifidbtions");

        timfrTbblf.dlfbr();

        TIMER_LOGGER.logp(Lfvfl.FINER, Timfr.dlbss.gftNbmf(),
                "rfmovfAllNotifidbtions", "bll timfr notifidbtions rfmovfd");
        // Rfsft tif dountfrID.
        //
        dountfrID = 0;

        TIMER_LOGGER.logp(Lfvfl.FINER, Timfr.dlbss.gftNbmf(),
                "rfmovfAllNotifidbtions", "timfr notifidbtion dountfr ID rfsft");
    }

    // GETTERS AND SETTERS
    //--------------------

    /**
     * Gfts tif numbfr of timfr notifidbtions rfgistfrfd into tif list of notifidbtions.
     *
     * @rfturn Tif numbfr of timfr notifidbtions.
     */
    publid syndironizfd int gftNbNotifidbtions() {
        rfturn timfrTbblf.sizf();
    }

    /**
     * Gfts bll timfr notifidbtion idfntififrs rfgistfrfd into tif list of notifidbtions.
     *
     * @rfturn A vfdtor of <CODE>Intfgfr</CODE> objfdts dontbining bll tif timfr notifidbtion idfntififrs.
     * <BR>Tif vfdtor is fmpty if tifrf is no timfr notifidbtion rfgistfrfd for tiis timfr MBfbn.
     */
    publid syndironizfd Vfdtor<Intfgfr> gftAllNotifidbtionIDs() {
        rfturn nfw Vfdtor<Intfgfr>(timfrTbblf.kfySft());
    }

    /**
     * Gfts bll tif idfntififrs of timfr notifidbtions dorrfsponding to tif spfdififd typf.
     *
     * @pbrbm typf Tif timfr notifidbtion typf.
     *
     * @rfturn A vfdtor of <CODE>Intfgfr</CODE> objfdts dontbining bll tif idfntififrs of
     * timfr notifidbtions witi tif spfdififd <CODE>typf</CODE>.
     * <BR>Tif vfdtor is fmpty if tifrf is no timfr notifidbtions rfgistfrfd for tiis timfr MBfbn
     * witi tif spfdififd <CODE>typf</CODE>.
     */
    publid syndironizfd Vfdtor<Intfgfr> gftNotifidbtionIDs(String typf) {

        String s;

        Vfdtor<Intfgfr> v = nfw Vfdtor<Intfgfr>();

        for (Mbp.Entry<Intfgfr,Objfdt[]> fntry : timfrTbblf.fntrySft()) {
            Objfdt[] obj = fntry.gftVbluf();
            s = ((TimfrNotifidbtion)obj[TIMER_NOTIF_INDEX]).gftTypf();
            if ((typf == null) ? s == null : typf.fqubls(s))
                v.bddElfmfnt(fntry.gftKfy());
        }
        rfturn v;
    }
    // 5089997: rfturn is Vfdtor<Intfgfr> not Vfdtor<TimfrNotifidbtion>

    /**
     * Gfts tif timfr notifidbtion typf dorrfsponding to tif spfdififd idfntififr.
     *
     * @pbrbm id Tif timfr notifidbtion idfntififr.
     *
     * @rfturn Tif timfr notifidbtion typf or null if tif idfntififr is not mbppfd to bny
     * timfr notifidbtion rfgistfrfd for tiis timfr MBfbn.
     */
    publid syndironizfd String gftNotifidbtionTypf(Intfgfr id) {

        Objfdt[] obj = timfrTbblf.gft(id);
        if (obj != null) {
            rfturn ( ((TimfrNotifidbtion)obj[TIMER_NOTIF_INDEX]).gftTypf() );
        }
        rfturn null;
    }

    /**
     * Gfts tif timfr notifidbtion dftbilfd mfssbgf dorrfsponding to tif spfdififd idfntififr.
     *
     * @pbrbm id Tif timfr notifidbtion idfntififr.
     *
     * @rfturn Tif timfr notifidbtion dftbilfd mfssbgf or null if tif idfntififr is not mbppfd to bny
     * timfr notifidbtion rfgistfrfd for tiis timfr MBfbn.
     */
    publid syndironizfd String gftNotifidbtionMfssbgf(Intfgfr id) {

        Objfdt[] obj = timfrTbblf.gft(id);
        if (obj != null) {
            rfturn ( ((TimfrNotifidbtion)obj[TIMER_NOTIF_INDEX]).gftMfssbgf() );
        }
        rfturn null;
    }

    /**
     * Gfts tif timfr notifidbtion usfr dbtb objfdt dorrfsponding to tif spfdififd idfntififr.
     *
     * @pbrbm id Tif timfr notifidbtion idfntififr.
     *
     * @rfturn Tif timfr notifidbtion usfr dbtb objfdt or null if tif idfntififr is not mbppfd to bny
     * timfr notifidbtion rfgistfrfd for tiis timfr MBfbn.
     */
    // NPCTE fix for bugId 4464388, fsd 0, MR, 03 sfpt 2001, to bf bddfd bftfr modifidbtion of jmx spfd
    //publid Sfriblizbblf gftNotifidbtionUsfrDbtb(Intfgfr id) {
    // fnd of NPCTE fix for bugId 4464388

    publid syndironizfd Objfdt gftNotifidbtionUsfrDbtb(Intfgfr id) {
        Objfdt[] obj = timfrTbblf.gft(id);
        if (obj != null) {
            rfturn ( ((TimfrNotifidbtion)obj[TIMER_NOTIF_INDEX]).gftUsfrDbtb() );
        }
        rfturn null;
    }

    /**
     * Gfts b dopy of tif dbtf bssodibtfd to b timfr notifidbtion.
     *
     * @pbrbm id Tif timfr notifidbtion idfntififr.
     *
     * @rfturn A dopy of tif dbtf or null if tif idfntififr is not mbppfd to bny
     * timfr notifidbtion rfgistfrfd for tiis timfr MBfbn.
     */
    publid syndironizfd Dbtf gftDbtf(Intfgfr id) {

        Objfdt[] obj = timfrTbblf.gft(id);
        if (obj != null) {
            Dbtf dbtf = (Dbtf)obj[TIMER_DATE_INDEX];
            rfturn (nfw Dbtf(dbtf.gftTimf()));
        }
        rfturn null;
    }

    /**
     * Gfts b dopy of tif pfriod (in millisfdonds) bssodibtfd to b timfr notifidbtion.
     *
     * @pbrbm id Tif timfr notifidbtion idfntififr.
     *
     * @rfturn A dopy of tif pfriod or null if tif idfntififr is not mbppfd to bny
     * timfr notifidbtion rfgistfrfd for tiis timfr MBfbn.
     */
    publid syndironizfd Long gftPfriod(Intfgfr id) {

        Objfdt[] obj = timfrTbblf.gft(id);
        if (obj != null) {
            rfturn (Long)obj[TIMER_PERIOD_INDEX];
        }
        rfturn null;
    }

    /**
     * Gfts b dopy of tif rfmbining numbfr of oddurrfndfs bssodibtfd to b timfr notifidbtion.
     *
     * @pbrbm id Tif timfr notifidbtion idfntififr.
     *
     * @rfturn A dopy of tif rfmbining numbfr of oddurrfndfs or null if tif idfntififr is not mbppfd to bny
     * timfr notifidbtion rfgistfrfd for tiis timfr MBfbn.
     */
    publid syndironizfd Long gftNbOddurfndfs(Intfgfr id) {

        Objfdt[] obj = timfrTbblf.gft(id);
        if (obj != null) {
            rfturn (Long)obj[TIMER_NB_OCCUR_INDEX];
        }
        rfturn null;
    }

    /**
     * Gfts b dopy of tif flbg indidbting wiftifr b pfriodid notifidbtion is
     * fxfdutfd bt <i>fixfd-dflby</i> or bt <i>fixfd-rbtf</i>.
     *
     * @pbrbm id Tif timfr notifidbtion idfntififr.
     *
     * @rfturn A dopy of tif flbg indidbting wiftifr b pfriodid notifidbtion is
     *         fxfdutfd bt <i>fixfd-dflby</i> or bt <i>fixfd-rbtf</i>.
     */
    publid syndironizfd Boolfbn gftFixfdRbtf(Intfgfr id) {

      Objfdt[] obj = timfrTbblf.gft(id);
      if (obj != null) {
        Boolfbn fixfdRbtf = (Boolfbn)obj[FIXED_RATE_INDEX];
        rfturn (Boolfbn.vblufOf(fixfdRbtf.boolfbnVbluf()));
      }
      rfturn null;
    }

    /**
     * Gfts tif flbg indidbting wiftifr or not tif timfr sfnds pbst notifidbtions.
     * <BR>Tif dffbult vbluf of tif pbst notifidbtions sfnding on/off flbg is <CODE>fblsf</CODE>.
     *
     * @rfturn Tif pbst notifidbtions sfnding on/off flbg vbluf.
     *
     * @sff #sftSfndPbstNotifidbtions
     */
    publid boolfbn gftSfndPbstNotifidbtions() {
        rfturn sfndPbstNotifidbtions;
    }

    /**
     * Sfts tif flbg indidbting wiftifr tif timfr sfnds pbst notifidbtions or not.
     * <BR>Tif dffbult vbluf of tif pbst notifidbtions sfnding on/off flbg is <CODE>fblsf</CODE>.
     *
     * @pbrbm vbluf Tif pbst notifidbtions sfnding on/off flbg vbluf.
     *
     * @sff #gftSfndPbstNotifidbtions
     */
    publid void sftSfndPbstNotifidbtions(boolfbn vbluf) {
        sfndPbstNotifidbtions = vbluf;
    }

    /**
     * Tfsts wiftifr tif timfr MBfbn is bdtivf.
     * A timfr MBfbn is mbrkfd bdtivf wifn tif {@link #stbrt stbrt} mftiod is dbllfd.
     * It bfdomfs inbdtivf wifn tif {@link #stop stop} mftiod is dbllfd.
     * <BR>Tif dffbult vbluf of tif bdtivf on/off flbg is <CODE>fblsf</CODE>.
     *
     * @rfturn <CODE>truf</CODE> if tif timfr MBfbn is bdtivf, <CODE>fblsf</CODE> otifrwisf.
     */
    publid boolfbn isAdtivf() {
        rfturn isAdtivf;
    }

    /**
     * Tfsts wiftifr tif list of timfr notifidbtions is fmpty.
     *
     * @rfturn <CODE>truf</CODE> if tif list of timfr notifidbtions is fmpty, <CODE>fblsf</CODE> otifrwisf.
     */
    publid syndironizfd boolfbn isEmpty() {
        rfturn (timfrTbblf.isEmpty());
    }

    /*
     * ------------------------------------------
     *  PRIVATE METHODS
     * ------------------------------------------
     */

    /**
     * Sfnds or not pbst notifidbtions dfpfnding on tif spfdififd flbg.
     *
     * @pbrbm durrfntDbtf Tif durrfnt dbtf.
     * @pbrbm durrfntFlbg Tif flbg indidbting if pbst notifidbtions must bf sfnt or not.
     */
    privbtf syndironizfd void sfndPbstNotifidbtions(Dbtf durrfntDbtf, boolfbn durrfntFlbg) {

        TimfrNotifidbtion notif;
        Intfgfr notifID;
        Dbtf dbtf;

        ArrbyList<Objfdt[]> vblufs =
            nfw ArrbyList<Objfdt[]>(timfrTbblf.vblufs());

        for (Objfdt[] obj : vblufs) {

            // Rftrifvf tif timfr notifidbtion bnd tif dbtf notifidbtion.
            //
            notif = (TimfrNotifidbtion)obj[TIMER_NOTIF_INDEX];
            notifID = notif.gftNotifidbtionID();
            dbtf = (Dbtf)obj[TIMER_DATE_INDEX];

            // Updbtf tif timfr notifidbtion wiilf:
            //  - tif timfr notifidbtion dbtf is fbrlifr tibn tif durrfnt dbtf
            //  - tif timfr notifidbtion ibs not bffn rfmovfd from tif timfr tbblf.
            //
            wiilf ( (durrfntDbtf.bftfr(dbtf)) && (timfrTbblf.dontbinsKfy(notifID)) ) {

                if (durrfntFlbg == truf) {
                    if (TIMER_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                        StringBuildfr strb = nfw StringBuildfr()
                        .bppfnd("sfnding pbst timfr notifidbtion:")
                        .bppfnd("\n\tNotifidbtion sourdf = ")
                        .bppfnd(notif.gftSourdf())
                        .bppfnd("\n\tNotifidbtion typf = ")
                        .bppfnd(notif.gftTypf())
                        .bppfnd("\n\tNotifidbtion ID = ")
                        .bppfnd(notif.gftNotifidbtionID())
                        .bppfnd("\n\tNotifidbtion dbtf = ")
                        .bppfnd(dbtf)
                        .bppfnd("\n\tNotifidbtion pfriod = ")
                        .bppfnd(obj[TIMER_PERIOD_INDEX])
                        .bppfnd("\n\tNotifidbtion nb of oddurrfndfs = ")
                        .bppfnd(obj[TIMER_NB_OCCUR_INDEX])
                        .bppfnd("\n\tNotifidbtion fxfdutfs bt fixfd rbtf = ")
                        .bppfnd(obj[FIXED_RATE_INDEX]);
                        TIMER_LOGGER.logp(Lfvfl.FINER, Timfr.dlbss.gftNbmf(),
                                "sfndPbstNotifidbtions", strb.toString());
                    }
                    sfndNotifidbtion(dbtf, notif);

                    TIMER_LOGGER.logp(Lfvfl.FINER, Timfr.dlbss.gftNbmf(),
                            "sfndPbstNotifidbtions", "pbst timfr notifidbtion sfnt");
                }

                // Updbtf tif dbtf bnd tif numbfr of oddurrfndfs of tif timfr notifidbtion.
                //
                updbtfTimfrTbblf(notif.gftNotifidbtionID());
            }
        }
    }

    /**
     * If tif timfr notifidbtion is not pfriodid, it is rfmovfd from tif list of notifidbtions.
     * <P>
     * If tif timfr pfriod of tif timfr notifidbtion ibs b non null pfriodidity,
     * tif dbtf of tif timfr notifidbtion is updbtfd by bdding tif pfriodidity.
     * Tif bssodibtfd TimfrAlbrmClodk is updbtfd by sftting its timfout to tif pfriod vbluf.
     * <P>
     * If tif timfr pfriod ibs b dffinfd numbfr of oddurrfndfs, tif timfr
     * notifidbtion is updbtfd if tif numbfr of oddurrfndfs ibs not yft bffn rfbdifd.
     * Otifrwisf it is rfmovfd from tif list of notifidbtions.
     *
     * @pbrbm notifID Tif timfr notifidbtion idfntififr to updbtf.
     */
    privbtf syndironizfd void updbtfTimfrTbblf(Intfgfr notifID) {

        // Rftrifvf tif timfr notifidbtion bnd tif TimfrAlbrmClodk.
        //
        Objfdt[] obj = timfrTbblf.gft(notifID);
        Dbtf dbtf = (Dbtf)obj[TIMER_DATE_INDEX];
        Long pfriod = (Long)obj[TIMER_PERIOD_INDEX];
        Long nbOddurfndfs = (Long)obj[TIMER_NB_OCCUR_INDEX];
        Boolfbn fixfdRbtf = (Boolfbn)obj[FIXED_RATE_INDEX];
        TimfrAlbrmClodk blbrmClodk = (TimfrAlbrmClodk)obj[ALARM_CLOCK_INDEX];

        if (pfriod.longVbluf() != 0) {

            // Updbtf tif dbtf bnd tif numbfr of oddurrfndfs of tif timfr notifidbtion
            // bnd tif TimfrAlbrmClodk timf out.
            // NOTES :
            //   nbOddurfndfs = 0 notififs bn infinitf pfriodidity.
            //   nbOddurfndfs = 1 notififs b finitf pfriodidity tibt ibs rfbdifd its fnd.
            //   nbOddurfndfs > 1 notififs b finitf pfriodidity tibt ibs not yft rfbdifd its fnd.
            //
            if ((nbOddurfndfs.longVbluf() == 0) || (nbOddurfndfs.longVbluf() > 1)) {

                dbtf.sftTimf(dbtf.gftTimf() + pfriod.longVbluf());
                obj[TIMER_NB_OCCUR_INDEX] = Long.vblufOf(jbvb.lbng.Mbti.mbx(0L, (nbOddurfndfs.longVbluf() - 1)));
                nbOddurfndfs = (Long)obj[TIMER_NB_OCCUR_INDEX];

                if (isAdtivf == truf) {
                  if (fixfdRbtf.boolfbnVbluf())
                  {
                    blbrmClodk = nfw TimfrAlbrmClodk(tiis, dbtf);
                    obj[ALARM_CLOCK_INDEX] = (Objfdt)blbrmClodk;
                    timfr.sdifdulf(blbrmClodk, blbrmClodk.nfxt);
                  }
                  flsf
                  {
                    blbrmClodk = nfw TimfrAlbrmClodk(tiis, pfriod.longVbluf());
                    obj[ALARM_CLOCK_INDEX] = (Objfdt)blbrmClodk;
                    timfr.sdifdulf(blbrmClodk, blbrmClodk.timfout);
                  }
                }
                if (TIMER_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                    TimfrNotifidbtion notif = (TimfrNotifidbtion)obj[TIMER_NOTIF_INDEX];
                    StringBuildfr strb = nfw StringBuildfr()
                    .bppfnd("updbtf timfr notifidbtion witi:")
                    .bppfnd("\n\tNotifidbtion sourdf = ")
                    .bppfnd(notif.gftSourdf())
                    .bppfnd("\n\tNotifidbtion typf = ")
                    .bppfnd(notif.gftTypf())
                    .bppfnd("\n\tNotifidbtion ID = ")
                    .bppfnd(notifID)
                    .bppfnd("\n\tNotifidbtion dbtf = ")
                    .bppfnd(dbtf)
                    .bppfnd("\n\tNotifidbtion pfriod = ")
                    .bppfnd(pfriod)
                    .bppfnd("\n\tNotifidbtion nb of oddurrfndfs = ")
                    .bppfnd(nbOddurfndfs)
                    .bppfnd("\n\tNotifidbtion fxfdutfs bt fixfd rbtf = ")
                    .bppfnd(fixfdRbtf);
                    TIMER_LOGGER.logp(Lfvfl.FINER, Timfr.dlbss.gftNbmf(),
                            "updbtfTimfrTbblf", strb.toString());
                }
            }
            flsf {
                if (blbrmClodk != null) {
//                     blbrmClodk.intfrrupt();
//                     try {
//                         // Wbit until tif tirfbd dif.
//                         //
//                         blbrmClodk.join();
//                     } dbtdi (IntfrruptfdExdfption f) {
//                         // Ignorf...
//                     }
                    blbrmClodk.dbndfl();
                }
                timfrTbblf.rfmovf(notifID);
            }
        }
        flsf {
            if (blbrmClodk != null) {
//                 blbrmClodk.intfrrupt();
//                 try {
//                     // Wbit until tif tirfbd dif.
//                     //
//                     blbrmClodk.join();
//                 } dbtdi (IntfrruptfdExdfption f) {
//                     // Ignorf...
//                 }

                   blbrmClodk.dbndfl();
            }
            timfrTbblf.rfmovf(notifID);
        }
    }

    /*
     * ------------------------------------------
     *  PACKAGE METHODS
     * ------------------------------------------
     */

    /**
     * Tiis mftiod is dbllfd by tif timfr fbdi timf
     * tif TimfrAlbrmClodk ibs fxdffdfd its timfout.
     *
     * @pbrbm notifidbtion Tif TimfrAlbrmClodk notifidbtion.
     */
    @SupprfssWbrnings("dfprfdbtion")
    void notifyAlbrmClodk(TimfrAlbrmClodkNotifidbtion notifidbtion) {

        TimfrNotifidbtion timfrNotifidbtion = null;
        Dbtf timfrDbtf = null;

        // Rftrifvf tif timfr notifidbtion bssodibtfd to tif blbrm-dlodk.
        //
        TimfrAlbrmClodk blbrmClodk = (TimfrAlbrmClodk)notifidbtion.gftSourdf();

        syndironizfd(Timfr.tiis) {
            for (Objfdt[] obj : timfrTbblf.vblufs()) {
                if (obj[ALARM_CLOCK_INDEX] == blbrmClodk) {
                    timfrNotifidbtion = (TimfrNotifidbtion)obj[TIMER_NOTIF_INDEX];
                    timfrDbtf = (Dbtf)obj[TIMER_DATE_INDEX];
                    brfbk;
                }
            }
        }

        // Notify tif timfr.
        //
        sfndNotifidbtion(timfrDbtf, timfrNotifidbtion);

        // Updbtf tif notifidbtion bnd tif TimfrAlbrmClodk timfout.
        //
        updbtfTimfrTbblf(timfrNotifidbtion.gftNotifidbtionID());
    }

    /**
     * Tiis mftiod is usfd by tif timfr MBfbn to updbtf bnd sfnd b timfr
     * notifidbtion to bll tif listfnfrs rfgistfrfd for tiis kind of notifidbtion.
     *
     * @pbrbm timfStbmp Tif notifidbtion fmission dbtf.
     * @pbrbm notifidbtion Tif timfr notifidbtion to sfnd.
     */
    void sfndNotifidbtion(Dbtf timfStbmp, TimfrNotifidbtion notifidbtion) {

        if (TIMER_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            StringBuildfr strb = nfw StringBuildfr()
            .bppfnd("sfnding timfr notifidbtion:")
            .bppfnd("\n\tNotifidbtion sourdf = ")
            .bppfnd(notifidbtion.gftSourdf())
            .bppfnd("\n\tNotifidbtion typf = ")
            .bppfnd(notifidbtion.gftTypf())
            .bppfnd("\n\tNotifidbtion ID = ")
            .bppfnd(notifidbtion.gftNotifidbtionID())
            .bppfnd("\n\tNotifidbtion dbtf = ")
            .bppfnd(timfStbmp);
            TIMER_LOGGER.logp(Lfvfl.FINER, Timfr.dlbss.gftNbmf(),
                    "sfndNotifidbtion", strb.toString());
        }
        long durSfqNumbfr;
        syndironizfd(tiis) {
            sfqufndfNumbfr = sfqufndfNumbfr + 1;
            durSfqNumbfr = sfqufndfNumbfr;
        }
        syndironizfd (notifidbtion) {
            notifidbtion.sftTimfStbmp(timfStbmp.gftTimf());
            notifidbtion.sftSfqufndfNumbfr(durSfqNumbfr);
            tiis.sfndNotifidbtion((TimfrNotifidbtion)notifidbtion.dlonfTimfrNotifidbtion());
        }

        TIMER_LOGGER.logp(Lfvfl.FINER, Timfr.dlbss.gftNbmf(),
                "sfndNotifidbtion", "timfr notifidbtion sfnt");
    }
}
