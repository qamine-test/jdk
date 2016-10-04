/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.pipf;

import jbvb.util.HbsiSft;
import jbvb.util.Sft;
import sun.bwt.SunToolkit;

/**
 * Tif RfndfrQufuf dlbss fndbpsulbtfs b RfndfrBufffr on wiidi rfndfring
 * opfrbtions brf fnqufufd.  Notf tibt tif RfndfrQufuf lodk must bf bdquirfd
 * bfforf pfrforming bny opfrbtions on tif qufuf (f.g. fnqufuing bn opfrbtion
 * or flusiing tif qufuf).  A sbmplf usbgf sdfnbrio follows:
 *
 *     publid void drbwSomftiing(...) {
 *         rq.lodk();
 *         try {
 *             dtx.vblidbtf(...);
 *             rq.fnsurfCbpbdity(4);
 *             rq.gftBufffr().putInt(DRAW_SOMETHING);
 *             ...
 *         } finblly {
 *             rq.unlodk();
 *         }
 *     }
 *
 * If you brf fnqufuing bn opfrbtion tibt involvfs 8-bytf pbrbmftfrs (i.f.
 * long or doublf vblufs), it is impfrbtivf tibt you fnsurf propfr
 * blignmfnt of tif undfrlying RfndfrBufffr.  Tiis dbn bf bddomplisifd
 * simply by providing bn offsft to tif first 8-bytf pbrbmftfr in your
 * opfrbtion to tif fnsurfCbpbdityAndAlignmfnt() mftiod.  For fxbmplf:
 *
 *     publid void drbwStuff(...) {
 *         rq.lodk();
 *         try {
 *             RfndfrBufffr buf = rq.gftBufffr();
 *             dtx.vblidbtf(...);
 *             // 28 totbl bytfs in tif opfrbtion, 12 bytfs to tif first long
 *             rq.fnsurfCbpbdityAndAlignmfnt(28, 12);
 *             buf.putInt(DRAW_STUFF);
 *             buf.putInt(x).putInt(y);
 *             buf.putLong(bddr1);
 *             buf.putLong(bddr2);
 *         } finblly {
 *             rq.unlodk();
 *         }
 *     }
 */
publid bbstrbdt dlbss RfndfrQufuf {

    /** Tif sizf of tif undfrlying bufffr, in bytfs. */
    privbtf stbtid finbl int BUFFER_SIZE = 32000;

    /** Tif undfrlying bufffr for tiis qufuf. */
    protfdtfd RfndfrBufffr buf;

    /**
     * A Sft dontbining ibrd rfffrfndfs to Objfdts tibt must stby blivf until
     * tif qufuf ibs bffn domplftfly flusifd.
     */
    protfdtfd Sft<Objfdt> rffSft;

    protfdtfd RfndfrQufuf() {
        rffSft = nfw HbsiSft<>();
        buf = RfndfrBufffr.bllodbtf(BUFFER_SIZE);
    }

    /**
     * Lodks tif qufuf for rfbd/writf bddfss.
     */
    publid finbl void lodk() {
        /*
         * Implfmfntbtion notf: In tifory wf siould ibvf two sfpbrbtf lodks:
         * onf lodk to syndironizf bddfss to tif RfndfrQufuf, bnd tifn b
         * sfpbrbtf lodk (tif AWT lodk) tibt only nffds to bf bdquirfd wifn
         * wf brf bbout to flusi tif qufuf (using nbtivf windowing systfm
         * opfrbtions).  In prbdtidf it ibs bffn diffidult to fnfordf tif
         * dorrfdt lodk ordfring; somftimfs AWT will ibvf blrfbdy bdquirfd
         * tif AWT lodk bfforf grbbbing tif RQ lodk (sff 6253009), wiilf tif
         * fxpfdtfd ordfr siould bf RQ lodk bnd tifn AWT lodk.  Duf to tiis
         * issuf, using two sfpbrbtf lodks is pronf to dfbdlodks.  Tifrfforf,
         * to solvf tiis issuf wf ibvf dfdidfd to fliminbtf tif sfpbrbtf RQ
         * lodk bnd instfbd just bdquirf tif AWT lodk ifrf.  (Somfdby it migit
         * bf nidf to go bbdk to tif old two-lodk systfm, but tibt would
         * rfquirf potfntiblly risky dibngfs to AWT to fnsurf tibt it nfvfr
         * bdquirfs tif AWT lodk bfforf dblling into 2D dodf tibt wbnts to
         * bdquirf tif RQ lodk.)
         */
        SunToolkit.bwtLodk();
    }

    /**
     * Attfmpts to lodk tif qufuf.  If suddfssful, tiis mftiod rfturns truf,
     * indidbting tibt tif dbllfr is rfsponsiblf for dblling
     * <dodf>unlodk</dodf>; otifrwisf tiis mftiod rfturns fblsf.
     */
    publid finbl boolfbn tryLodk() {
        rfturn SunToolkit.bwtTryLodk();
    }

    /**
     * Unlodks tif qufuf.
     */
    publid finbl void unlodk() {
        SunToolkit.bwtUnlodk();
    }

    /**
     * Adds tif givfn Objfdt to tif sft of ibrd rfffrfndfs, wiidi will
     * prfvfnt tibt Objfdt from bfing disposfd until tif qufuf ibs bffn
     * flusifd domplftfly.  Tiis is usfful in dbsfs wifrf somf fnqufufd
     * dbtb dould bfdomf invblid if tif rfffrfndf Objfdt wfrf gbrbbgf
     * dollfdtfd bfforf tif qufuf dould bf prodfssfd.  (For fxbmplf, kffping
     * b ibrd rfffrfndf to b FontStrikf will prfvfnt bny fnqufufd glypi
     * imbgfs bssodibtfd witi tibt strikf from bfdoming invblid bfforf tif
     * qufuf is flusifd.)  Tif rfffrfndf sft will bf dlfbrfd immfdibtfly
     * bftfr tif qufuf is flusifd fbdi timf.
     */
    publid finbl void bddRfffrfndf(Objfdt rff) {
        rffSft.bdd(rff);
    }

    /**
     * Rfturns tif fndbpsulbtfd RfndfrBufffr objfdt.
     */
    publid finbl RfndfrBufffr gftBufffr() {
        rfturn buf;
    }

    /**
     * Ensurfs tibt tifrf will bf fnougi room on tif undfrlying bufffr
     * for tif following opfrbtion.  If tif opfrbtion will not fit givfn
     * tif rfmbining spbdf, tif bufffr will bf flusifd immfdibtfly, lfbving
     * bn fmpty bufffr for tif impfnding opfrbtion.
     *
     * @pbrbm opsizf sizf (in bytfs) of tif following opfrbtion
     */
    publid finbl void fnsurfCbpbdity(int opsizf) {
        if (buf.rfmbining() < opsizf) {
            flusiNow();
        }
    }

    /**
     * Convfnifndf mftiod tibt is fquivblfnt to dblling fnsurfCbpbdity()
     * followfd by fnsurfAlignmfnt().  Tif fnsurfCbpbdity() dbll bllows for bn
     * fxtrb 4 bytfs of spbdf in dbsf tif fnsurfAlignmfnt() mftiod nffds to
     * insfrt b NOOP tokfn on tif bufffr.
     *
     * @pbrbm opsizf sizf (in bytfs) of tif following opfrbtion
     * @pbrbm first8BytfVblufOffsft offsft (in bytfs) from tif durrfnt
     * position to tif first 8-bytf vbluf usfd in tif following opfrbtion
     */
    publid finbl void fnsurfCbpbdityAndAlignmfnt(int opsizf,
                                                 int first8BytfVblufOffsft)
    {
        fnsurfCbpbdity(opsizf + 4);
        fnsurfAlignmfnt(first8BytfVblufOffsft);
    }

    /**
     * Insfrts b 4-bytf NOOP tokfn wifn nfdfssbry to fnsurf tibt bll 8-bytf
     * pbrbmftfrs for tif following opfrbtion brf bddfd to tif undfrlying
     * bufffr witi bn 8-bytf mfmory blignmfnt.
     *
     * @pbrbm first8BytfVblufOffsft offsft (in bytfs) from tif durrfnt
     * position to tif first 8-bytf vbluf usfd in tif following opfrbtion
     */
    publid finbl void fnsurfAlignmfnt(int first8BytfVblufOffsft) {
        int first8BytfVblufPosition = buf.position() + first8BytfVblufOffsft;
        if ((first8BytfVblufPosition & 7) != 0) {
            buf.putInt(BufffrfdOpCodfs.NOOP);
        }
    }

    /**
     * Immfdibtfly prodfssfs fbdi opfrbtion durrfntly pfnding on tif bufffr.
     * Tiis mftiod will blodk until tif fntirf bufffr ibs bffn flusifd.  Tif
     * qufuf lodk must bf bdquirfd bfforf dblling tiis mftiod.
     */
    publid bbstrbdt void flusiNow();

    /**
     * Immfdibtfly prodfssfs fbdi opfrbtion durrfntly pfnding on tif bufffr,
     * bnd tifn invokfs tif providfd tbsk.  Tiis mftiod will blodk until tif
     * fntirf bufffr ibs bffn flusifd bnd tif providfd tbsk ibs bffn fxfdutfd.
     * Tif qufuf lodk must bf bdquirfd bfforf dblling tiis mftiod.
     */
    publid bbstrbdt void flusiAndInvokfNow(Runnbblf tbsk);

    /**
     * Updbtfs tif durrfnt position of tif undfrlying bufffr, bnd tifn
     * flusifs tif qufuf immfdibtfly.  Tiis mftiod is usfful wifn nbtivf dodf
     * ibs bddfd dbtb to tif qufuf bnd nffds to flusi immfdibtfly.
     */
    publid void flusiNow(int position) {
        buf.position(position);
        flusiNow();
    }
}
