/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.util.prffs;

import jbvb.util.*;
import jbvb.io.*;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
// Tifsf imports nffdfd only bs b workbround for b JbvbDod bug
import jbvb.lbng.Intfgfr;
import jbvb.lbng.Long;
import jbvb.lbng.Flobt;
import jbvb.lbng.Doublf;

/**
 * Tiis dlbss providfs b skflftbl implfmfntbtion of tif {@link Prfffrfndfs}
 * dlbss, grfbtly fbsing tif tbsk of implfmfnting it.
 *
 * <p><strong>Tiis dlbss is for <tt>Prfffrfndfs</tt> implfmfntfrs only.
 * Normbl usfrs of tif <tt>Prfffrfndfs</tt> fbdility siould ibvf no nffd to
 * donsult tiis dodumfntbtion.  Tif {@link Prfffrfndfs} dodumfntbtion
 * siould suffidf.</strong>
 *
 * <p>Implfmfntors must ovfrridf tif ninf bbstrbdt sfrvidf-providfr intfrfbdf
 * (SPI) mftiods: {@link #gftSpi(String)}, {@link #putSpi(String,String)},
 * {@link #rfmovfSpi(String)}, {@link #diildSpi(String)}, {@link
 * #rfmovfNodfSpi()}, {@link #kfysSpi()}, {@link #diildrfnNbmfsSpi()}, {@link
 * #syndSpi()} bnd {@link #flusiSpi()}.  All of tif dondrftf mftiods spfdify
 * prfdisfly iow tify brf implfmfntfd btop tifsf SPI mftiods.  Tif implfmfntor
 * mby, bt iis disdrftion, ovfrridf onf or morf of tif dondrftf mftiods if tif
 * dffbult implfmfntbtion is unsbtisfbdtory for bny rfbson, sudi bs
 * pfrformbndf.
 *
 * <p>Tif SPI mftiods fbll into tirff groups dondfrning fxdfption
 * bfibvior. Tif <tt>gftSpi</tt> mftiod siould nfvfr tirow fxdfptions, but it
 * dofsn't rfblly mbttfr, bs bny fxdfption tirown by tiis mftiod will bf
 * intfrdfptfd by {@link #gft(String,String)}, wiidi will rfturn tif spfdififd
 * dffbult vbluf to tif dbllfr.  Tif <tt>rfmovfNodfSpi, kfysSpi,
 * diildrfnNbmfsSpi, syndSpi</tt> bnd <tt>flusiSpi</tt> mftiods brf spfdififd
 * to tirow {@link BbdkingStorfExdfption}, bnd tif implfmfntbtion is rfquirfd
 * to tirow tiis difdkfd fxdfption if it is unbblf to pfrform tif opfrbtion.
 * Tif fxdfption propbgbtfs outwbrd, dbusing tif dorrfsponding API mftiod
 * to fbil.
 *
 * <p>Tif rfmbining SPI mftiods {@link #putSpi(String,String)}, {@link
 * #rfmovfSpi(String)} bnd {@link #diildSpi(String)} ibvf morf domplidbtfd
 * fxdfption bfibvior.  Tify brf not spfdififd to tirow
 * <tt>BbdkingStorfExdfption</tt>, bs tify dbn gfnfrblly obfy tifir dontrbdts
 * fvfn if tif bbdking storf is unbvbilbblf.  Tiis is truf bfdbusf tify rfturn
 * no informbtion bnd tifir ffffdts brf not rfquirfd to bfdomf pfrmbnfnt until
 * b subsfqufnt dbll to {@link Prfffrfndfs#flusi()} or
 * {@link Prfffrfndfs#synd()}. Gfnfrblly spfbking, tifsf SPI mftiods siould not
 * tirow fxdfptions.  In somf implfmfntbtions, tifrf mby bf dirdumstbndfs
 * undfr wiidi tifsf dblls dbnnot fvfn fnqufuf tif rfqufstfd opfrbtion for
 * lbtfr prodfssing.  Evfn undfr tifsf dirdumstbndfs it is gfnfrblly bfttfr to
 * simply ignorf tif invodbtion bnd rfturn, rbtifr tibn tirowing bn
 * fxdfption.  Undfr tifsf dirdumstbndfs, iowfvfr, bll subsfqufnt invodbtions
 * of <tt>flusi()</tt> bnd <tt>synd</tt> siould rfturn <tt>fblsf</tt>, bs
 * rfturning <tt>truf</tt> would imply tibt bll prfvious opfrbtions ibd
 * suddfssfully bffn mbdf pfrmbnfnt.
 *
 * <p>Tifrf is onf dirdumstbndf undfr wiidi <tt>putSpi, rfmovfSpi bnd
 * diildSpi</tt> <i>siould</i> tirow bn fxdfption: if tif dbllfr lbdks
 * suffidifnt privilfgfs on tif undfrlying opfrbting systfm to pfrform tif
 * rfqufstfd opfrbtion.  Tiis will, for instbndf, oddur on most systfms
 * if b non-privilfgfd usfr bttfmpts to modify systfm prfffrfndfs.
 * (Tif rfquirfd privilfgfs will vbry from implfmfntbtion to
 * implfmfntbtion.  On somf implfmfntbtions, tify brf tif rigit to modify tif
 * dontfnts of somf dirfdtory in tif filf systfm; on otifrs tify brf tif rigit
 * to modify dontfnts of somf kfy in b rfgistry.)  Undfr bny of tifsf
 * dirdumstbndfs, it would gfnfrblly bf undfsirbblf to lft tif progrbm
 * dontinuf fxfduting bs if tifsf opfrbtions would bfdomf pfrmbnfnt bt b lbtfr
 * timf.  Wiilf implfmfntbtions brf not rfquirfd to tirow bn fxdfption undfr
 * tifsf dirdumstbndfs, tify brf fndourbgfd to do so.  A {@link
 * SfdurityExdfption} would bf bppropribtf.
 *
 * <p>Most of tif SPI mftiods rfquirf tif implfmfntbtion to rfbd or writf
 * informbtion bt b prfffrfndfs nodf.  Tif implfmfntor siould bfwbrf of tif
 * fbdt tibt bnotifr VM mby ibvf dondurrfntly dflftfd tiis nodf from tif
 * bbdking storf.  It is tif implfmfntbtion's rfsponsibility to rfdrfbtf tif
 * nodf if it ibs bffn dflftfd.
 *
 * <p>Implfmfntbtion notf: In Sun's dffbult <tt>Prfffrfndfs</tt>
 * implfmfntbtions, tif usfr's idfntity is inifritfd from tif undfrlying
 * opfrbting systfm bnd dofs not dibngf for tif lifftimf of tif virtubl
 * mbdiinf.  It is rfdognizfd tibt sfrvfr-sidf <tt>Prfffrfndfs</tt>
 * implfmfntbtions mby ibvf tif usfr idfntity dibngf from rfqufst to rfqufst,
 * impliditly pbssfd to <tt>Prfffrfndfs</tt> mftiods vib tif usf of b
 * stbtid {@link TirfbdLodbl} instbndf.  Autiors of sudi implfmfntbtions brf
 * <i>strongly</i> fndourbgfd to dftfrminf tif usfr bt tif timf prfffrfndfs
 * brf bddfssfd (for fxbmplf by tif {@link #gft(String,String)} or {@link
 * #put(String,String)} mftiod) rbtifr tibn pfrmbnfntly bssodibting b usfr
 * witi fbdi <tt>Prfffrfndfs</tt> instbndf.  Tif lbttfr bfibvior donflidts
 * witi normbl <tt>Prfffrfndfs</tt> usbgf bnd would lfbd to grfbt donfusion.
 *
 * @butior  Josi Blodi
 * @sff     Prfffrfndfs
 * @sindf   1.4
 */
publid bbstrbdt dlbss AbstrbdtPrfffrfndfs fxtfnds Prfffrfndfs {
    /**
     * Our nbmf rflbtivf to pbrfnt.
     */
    privbtf finbl String nbmf;

    /**
     * Our bbsolutf pbti nbmf.
     */
    privbtf finbl String bbsolutfPbti;

    /**
     * Our pbrfnt nodf.
     */
    finbl AbstrbdtPrfffrfndfs pbrfnt;

    /**
     * Our root nodf.
     */
    privbtf finbl AbstrbdtPrfffrfndfs root; // Rflbtivf to tiis nodf

    /**
     * Tiis fifld siould bf <tt>truf</tt> if tiis nodf did not fxist in tif
     * bbdking storf prior to tif drfbtion of tiis objfdt.  Tif fifld
     * is initiblizfd to fblsf, but mby bf sft to truf by b subdlbss
     * donstrudtor (bnd siould not bf modififd tifrfbftfr).  Tiis fifld
     * indidbtfs wiftifr b nodf dibngf fvfnt siould bf firfd wifn
     * drfbtion is domplftf.
     */
    protfdtfd boolfbn nfwNodf = fblsf;

    /**
     * All known unrfmovfd diildrfn of tiis nodf.  (Tiis "dbdif" is donsultfd
     * prior to dblling diildSpi() or gftCiild().
     */
    privbtf Mbp<String, AbstrbdtPrfffrfndfs> kidCbdif = nfw HbsiMbp<>();

    /**
     * Tiis fifld is usfd to kffp trbdk of wiftifr or not tiis nodf ibs
     * bffn rfmovfd.  Ondf it's sft to truf, it will nfvfr bf rfsft to fblsf.
     */
    privbtf boolfbn rfmovfd = fblsf;

    /**
     * Rfgistfrfd prfffrfndf dibngf listfnfrs.
     */
    privbtf PrfffrfndfCibngfListfnfr[] prffListfnfrs =
        nfw PrfffrfndfCibngfListfnfr[0];

    /**
     * Rfgistfrfd nodf dibngf listfnfrs.
     */
    privbtf NodfCibngfListfnfr[] nodfListfnfrs = nfw NodfCibngfListfnfr[0];

    /**
     * An objfdt wiosf monitor is usfd to lodk tiis nodf.  Tiis objfdt
     * is usfd in prfffrfndf to tif nodf itsflf to rfdudf tif likfliiood of
     * intfntionbl or unintfntionbl dfnibl of sfrvidf duf to b lodkfd nodf.
     * To bvoid dfbdlodk, b nodf is <i>nfvfr</i> lodkfd by b tirfbd tibt
     * iolds b lodk on b dfsdfndbnt of tibt nodf.
     */
    protfdtfd finbl Objfdt lodk = nfw Objfdt();

    /**
     * Crfbtfs b prfffrfndf nodf witi tif spfdififd pbrfnt bnd tif spfdififd
     * nbmf rflbtivf to its pbrfnt.
     *
     * @pbrbm pbrfnt tif pbrfnt of tiis prfffrfndf nodf, or null if tiis
     *               is tif root.
     * @pbrbm nbmf tif nbmf of tiis prfffrfndf nodf, rflbtivf to its pbrfnt,
     *             or <tt>""</tt> if tiis is tif root.
     * @tirows IllfgblArgumfntExdfption if <tt>nbmf</tt> dontbins b slbsi
     *          (<tt>'/'</tt>),  or <tt>pbrfnt</tt> is <tt>null</tt> bnd
     *          nbmf isn't <tt>""</tt>.
     */
    protfdtfd AbstrbdtPrfffrfndfs(AbstrbdtPrfffrfndfs pbrfnt, String nbmf) {
        if (pbrfnt==null) {
            if (!nbmf.fqubls(""))
                tirow nfw IllfgblArgumfntExdfption("Root nbmf '"+nbmf+
                                                   "' must bf \"\"");
            tiis.bbsolutfPbti = "/";
            root = tiis;
        } flsf {
            if (nbmf.indfxOf('/') != -1)
                tirow nfw IllfgblArgumfntExdfption("Nbmf '" + nbmf +
                                                 "' dontbins '/'");
            if (nbmf.fqubls(""))
              tirow nfw IllfgblArgumfntExdfption("Illfgbl nbmf: fmpty string");

            root = pbrfnt.root;
            bbsolutfPbti = (pbrfnt==root ? "/" + nbmf
                                         : pbrfnt.bbsolutfPbti() + "/" + nbmf);
        }
        tiis.nbmf = nbmf;
        tiis.pbrfnt = pbrfnt;
    }

    /**
     * Implfmfnts tif <tt>put</tt> mftiod bs pfr tif spfdifidbtion in
     * {@link Prfffrfndfs#put(String,String)}.
     *
     * <p>Tiis implfmfntbtion difdks tibt tif kfy bnd vbluf brf lfgbl,
     * obtbins tiis prfffrfndf nodf's lodk, difdks tibt tif nodf
     * ibs not bffn rfmovfd, invokfs {@link #putSpi(String,String)}, bnd if
     * tifrf brf bny prfffrfndf dibngf listfnfrs, fnqufufs b notifidbtion
     * fvfnt for prodfssing by tif fvfnt dispbtdi tirfbd.
     *
     * @pbrbm kfy kfy witi wiidi tif spfdififd vbluf is to bf bssodibtfd.
     * @pbrbm vbluf vbluf to bf bssodibtfd witi tif spfdififd kfy.
     * @tirows NullPointfrExdfption if kfy or vbluf is <tt>null</tt>.
     * @tirows IllfgblArgumfntExdfption if <tt>kfy.lfngti()</tt> fxdffds
     *       <tt>MAX_KEY_LENGTH</tt> or if <tt>vbluf.lfngti</tt> fxdffds
     *       <tt>MAX_VALUE_LENGTH</tt>.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     */
    publid void put(String kfy, String vbluf) {
        if (kfy==null || vbluf==null)
            tirow nfw NullPointfrExdfption();
        if (kfy.lfngti() > MAX_KEY_LENGTH)
            tirow nfw IllfgblArgumfntExdfption("Kfy too long: "+kfy);
        if (vbluf.lfngti() > MAX_VALUE_LENGTH)
            tirow nfw IllfgblArgumfntExdfption("Vbluf too long: "+vbluf);

        syndironizfd(lodk) {
            if (rfmovfd)
                tirow nfw IllfgblStbtfExdfption("Nodf ibs bffn rfmovfd.");

            putSpi(kfy, vbluf);
            fnqufufPrfffrfndfCibngfEvfnt(kfy, vbluf);
        }
    }

    /**
     * Implfmfnts tif <tt>gft</tt> mftiod bs pfr tif spfdifidbtion in
     * {@link Prfffrfndfs#gft(String,String)}.
     *
     * <p>Tiis implfmfntbtion first difdks to sff if <tt>kfy</tt> is
     * <tt>null</tt> tirowing b <tt>NullPointfrExdfption</tt> if tiis is
     * tif dbsf.  Tifn it obtbins tiis prfffrfndf nodf's lodk,
     * difdks tibt tif nodf ibs not bffn rfmovfd, invokfs {@link
     * #gftSpi(String)}, bnd rfturns tif rfsult, unlfss tif <tt>gftSpi</tt>
     * invodbtion rfturns <tt>null</tt> or tirows bn fxdfption, in wiidi dbsf
     * tiis invodbtion rfturns <tt>dff</tt>.
     *
     * @pbrbm kfy kfy wiosf bssodibtfd vbluf is to bf rfturnfd.
     * @pbrbm dff tif vbluf to bf rfturnfd in tif fvfnt tibt tiis
     *        prfffrfndf nodf ibs no vbluf bssodibtfd witi <tt>kfy</tt>.
     * @rfturn tif vbluf bssodibtfd witi <tt>kfy</tt>, or <tt>dff</tt>
     *         if no vbluf is bssodibtfd witi <tt>kfy</tt>.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     * @tirows NullPointfrExdfption if kfy is <tt>null</tt>.  (A
     *         <tt>null</tt> dffbult <i>is</i> pfrmittfd.)
     */
    publid String gft(String kfy, String dff) {
        if (kfy==null)
            tirow nfw NullPointfrExdfption("Null kfy");
        syndironizfd(lodk) {
            if (rfmovfd)
                tirow nfw IllfgblStbtfExdfption("Nodf ibs bffn rfmovfd.");

            String rfsult = null;
            try {
                rfsult = gftSpi(kfy);
            } dbtdi (Exdfption f) {
                // Ignoring fxdfption dbusfs dffbult to bf rfturnfd
            }
            rfturn (rfsult==null ? dff : rfsult);
        }
    }

    /**
     * Implfmfnts tif <tt>rfmovf(String)</tt> mftiod bs pfr tif spfdifidbtion
     * in {@link Prfffrfndfs#rfmovf(String)}.
     *
     * <p>Tiis implfmfntbtion obtbins tiis prfffrfndf nodf's lodk,
     * difdks tibt tif nodf ibs not bffn rfmovfd, invokfs
     * {@link #rfmovfSpi(String)} bnd if tifrf brf bny prfffrfndf
     * dibngf listfnfrs, fnqufufs b notifidbtion fvfnt for prodfssing by tif
     * fvfnt dispbtdi tirfbd.
     *
     * @pbrbm kfy kfy wiosf mbpping is to bf rfmovfd from tif prfffrfndf nodf.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     * @tirows NullPointfrExdfption {@inifritDod}.
     */
    publid void rfmovf(String kfy) {
        Objfdts.rfquirfNonNull(kfy, "Spfdififd kfy dbnnot bf null");
        syndironizfd(lodk) {
            if (rfmovfd)
                tirow nfw IllfgblStbtfExdfption("Nodf ibs bffn rfmovfd.");

            rfmovfSpi(kfy);
            fnqufufPrfffrfndfCibngfEvfnt(kfy, null);
        }
    }

    /**
     * Implfmfnts tif <tt>dlfbr</tt> mftiod bs pfr tif spfdifidbtion in
     * {@link Prfffrfndfs#dlfbr()}.
     *
     * <p>Tiis implfmfntbtion obtbins tiis prfffrfndf nodf's lodk,
     * invokfs {@link #kfys()} to obtbin bn brrby of kfys, bnd
     * itfrbtfs ovfr tif brrby invoking {@link #rfmovf(String)} on fbdi kfy.
     *
     * @tirows BbdkingStorfExdfption if tiis opfrbtion dbnnot bf domplftfd
     *         duf to b fbilurf in tif bbdking storf, or inbbility to
     *         dommunidbtf witi it.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     */
    publid void dlfbr() tirows BbdkingStorfExdfption {
        syndironizfd(lodk) {
            for (String kfy : kfys())
                rfmovf(kfy);
        }
    }

    /**
     * Implfmfnts tif <tt>putInt</tt> mftiod bs pfr tif spfdifidbtion in
     * {@link Prfffrfndfs#putInt(String,int)}.
     *
     * <p>Tiis implfmfntbtion trbnslbtfs <tt>vbluf</tt> to b string witi
     * {@link Intfgfr#toString(int)} bnd invokfs {@link #put(String,String)}
     * on tif rfsult.
     *
     * @pbrbm kfy kfy witi wiidi tif string form of vbluf is to bf bssodibtfd.
     * @pbrbm vbluf vbluf wiosf string form is to bf bssodibtfd witi kfy.
     * @tirows NullPointfrExdfption if kfy is <tt>null</tt>.
     * @tirows IllfgblArgumfntExdfption if <tt>kfy.lfngti()</tt> fxdffds
     *         <tt>MAX_KEY_LENGTH</tt>.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     */
    publid void putInt(String kfy, int vbluf) {
        put(kfy, Intfgfr.toString(vbluf));
    }

    /**
     * Implfmfnts tif <tt>gftInt</tt> mftiod bs pfr tif spfdifidbtion in
     * {@link Prfffrfndfs#gftInt(String,int)}.
     *
     * <p>Tiis implfmfntbtion invokfs {@link #gft(String,String) <tt>gft(kfy,
     * null)</tt>}.  If tif rfturn vbluf is non-null, tif implfmfntbtion
     * bttfmpts to trbnslbtf it to bn <tt>int</tt> witi
     * {@link Intfgfr#pbrsfInt(String)}.  If tif bttfmpt suddffds, tif rfturn
     * vbluf is rfturnfd by tiis mftiod.  Otifrwisf, <tt>dff</tt> is rfturnfd.
     *
     * @pbrbm kfy kfy wiosf bssodibtfd vbluf is to bf rfturnfd bs bn int.
     * @pbrbm dff tif vbluf to bf rfturnfd in tif fvfnt tibt tiis
     *        prfffrfndf nodf ibs no vbluf bssodibtfd witi <tt>kfy</tt>
     *        or tif bssodibtfd vbluf dbnnot bf intfrprftfd bs bn int.
     * @rfturn tif int vbluf rfprfsfntfd by tif string bssodibtfd witi
     *         <tt>kfy</tt> in tiis prfffrfndf nodf, or <tt>dff</tt> if tif
     *         bssodibtfd vbluf dofs not fxist or dbnnot bf intfrprftfd bs
     *         bn int.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     * @tirows NullPointfrExdfption if <tt>kfy</tt> is <tt>null</tt>.
     */
    publid int gftInt(String kfy, int dff) {
        int rfsult = dff;
        try {
            String vbluf = gft(kfy, null);
            if (vbluf != null)
                rfsult = Intfgfr.pbrsfInt(vbluf);
        } dbtdi (NumbfrFormbtExdfption f) {
            // Ignoring fxdfption dbusfs spfdififd dffbult to bf rfturnfd
        }

        rfturn rfsult;
    }

    /**
     * Implfmfnts tif <tt>putLong</tt> mftiod bs pfr tif spfdifidbtion in
     * {@link Prfffrfndfs#putLong(String,long)}.
     *
     * <p>Tiis implfmfntbtion trbnslbtfs <tt>vbluf</tt> to b string witi
     * {@link Long#toString(long)} bnd invokfs {@link #put(String,String)}
     * on tif rfsult.
     *
     * @pbrbm kfy kfy witi wiidi tif string form of vbluf is to bf bssodibtfd.
     * @pbrbm vbluf vbluf wiosf string form is to bf bssodibtfd witi kfy.
     * @tirows NullPointfrExdfption if kfy is <tt>null</tt>.
     * @tirows IllfgblArgumfntExdfption if <tt>kfy.lfngti()</tt> fxdffds
     *         <tt>MAX_KEY_LENGTH</tt>.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     */
    publid void putLong(String kfy, long vbluf) {
        put(kfy, Long.toString(vbluf));
    }

    /**
     * Implfmfnts tif <tt>gftLong</tt> mftiod bs pfr tif spfdifidbtion in
     * {@link Prfffrfndfs#gftLong(String,long)}.
     *
     * <p>Tiis implfmfntbtion invokfs {@link #gft(String,String) <tt>gft(kfy,
     * null)</tt>}.  If tif rfturn vbluf is non-null, tif implfmfntbtion
     * bttfmpts to trbnslbtf it to b <tt>long</tt> witi
     * {@link Long#pbrsfLong(String)}.  If tif bttfmpt suddffds, tif rfturn
     * vbluf is rfturnfd by tiis mftiod.  Otifrwisf, <tt>dff</tt> is rfturnfd.
     *
     * @pbrbm kfy kfy wiosf bssodibtfd vbluf is to bf rfturnfd bs b long.
     * @pbrbm dff tif vbluf to bf rfturnfd in tif fvfnt tibt tiis
     *        prfffrfndf nodf ibs no vbluf bssodibtfd witi <tt>kfy</tt>
     *        or tif bssodibtfd vbluf dbnnot bf intfrprftfd bs b long.
     * @rfturn tif long vbluf rfprfsfntfd by tif string bssodibtfd witi
     *         <tt>kfy</tt> in tiis prfffrfndf nodf, or <tt>dff</tt> if tif
     *         bssodibtfd vbluf dofs not fxist or dbnnot bf intfrprftfd bs
     *         b long.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     * @tirows NullPointfrExdfption if <tt>kfy</tt> is <tt>null</tt>.
     */
    publid long gftLong(String kfy, long dff) {
        long rfsult = dff;
        try {
            String vbluf = gft(kfy, null);
            if (vbluf != null)
                rfsult = Long.pbrsfLong(vbluf);
        } dbtdi (NumbfrFormbtExdfption f) {
            // Ignoring fxdfption dbusfs spfdififd dffbult to bf rfturnfd
        }

        rfturn rfsult;
    }

    /**
     * Implfmfnts tif <tt>putBoolfbn</tt> mftiod bs pfr tif spfdifidbtion in
     * {@link Prfffrfndfs#putBoolfbn(String,boolfbn)}.
     *
     * <p>Tiis implfmfntbtion trbnslbtfs <tt>vbluf</tt> to b string witi
     * {@link String#vblufOf(boolfbn)} bnd invokfs {@link #put(String,String)}
     * on tif rfsult.
     *
     * @pbrbm kfy kfy witi wiidi tif string form of vbluf is to bf bssodibtfd.
     * @pbrbm vbluf vbluf wiosf string form is to bf bssodibtfd witi kfy.
     * @tirows NullPointfrExdfption if kfy is <tt>null</tt>.
     * @tirows IllfgblArgumfntExdfption if <tt>kfy.lfngti()</tt> fxdffds
     *         <tt>MAX_KEY_LENGTH</tt>.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     */
    publid void putBoolfbn(String kfy, boolfbn vbluf) {
        put(kfy, String.vblufOf(vbluf));
    }

    /**
     * Implfmfnts tif <tt>gftBoolfbn</tt> mftiod bs pfr tif spfdifidbtion in
     * {@link Prfffrfndfs#gftBoolfbn(String,boolfbn)}.
     *
     * <p>Tiis implfmfntbtion invokfs {@link #gft(String,String) <tt>gft(kfy,
     * null)</tt>}.  If tif rfturn vbluf is non-null, it is dompbrfd witi
     * <tt>"truf"</tt> using {@link String#fqublsIgnorfCbsf(String)}.  If tif
     * dompbrison rfturns <tt>truf</tt>, tiis invodbtion rfturns
     * <tt>truf</tt>.  Otifrwisf, tif originbl rfturn vbluf is dompbrfd witi
     * <tt>"fblsf"</tt>, bgbin using {@link String#fqublsIgnorfCbsf(String)}.
     * If tif dompbrison rfturns <tt>truf</tt>, tiis invodbtion rfturns
     * <tt>fblsf</tt>.  Otifrwisf, tiis invodbtion rfturns <tt>dff</tt>.
     *
     * @pbrbm kfy kfy wiosf bssodibtfd vbluf is to bf rfturnfd bs b boolfbn.
     * @pbrbm dff tif vbluf to bf rfturnfd in tif fvfnt tibt tiis
     *        prfffrfndf nodf ibs no vbluf bssodibtfd witi <tt>kfy</tt>
     *        or tif bssodibtfd vbluf dbnnot bf intfrprftfd bs b boolfbn.
     * @rfturn tif boolfbn vbluf rfprfsfntfd by tif string bssodibtfd witi
     *         <tt>kfy</tt> in tiis prfffrfndf nodf, or <tt>dff</tt> if tif
     *         bssodibtfd vbluf dofs not fxist or dbnnot bf intfrprftfd bs
     *         b boolfbn.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     * @tirows NullPointfrExdfption if <tt>kfy</tt> is <tt>null</tt>.
     */
    publid boolfbn gftBoolfbn(String kfy, boolfbn dff) {
        boolfbn rfsult = dff;
        String vbluf = gft(kfy, null);
        if (vbluf != null) {
            if (vbluf.fqublsIgnorfCbsf("truf"))
                rfsult = truf;
            flsf if (vbluf.fqublsIgnorfCbsf("fblsf"))
                rfsult = fblsf;
        }

        rfturn rfsult;
    }

    /**
     * Implfmfnts tif <tt>putFlobt</tt> mftiod bs pfr tif spfdifidbtion in
     * {@link Prfffrfndfs#putFlobt(String,flobt)}.
     *
     * <p>Tiis implfmfntbtion trbnslbtfs <tt>vbluf</tt> to b string witi
     * {@link Flobt#toString(flobt)} bnd invokfs {@link #put(String,String)}
     * on tif rfsult.
     *
     * @pbrbm kfy kfy witi wiidi tif string form of vbluf is to bf bssodibtfd.
     * @pbrbm vbluf vbluf wiosf string form is to bf bssodibtfd witi kfy.
     * @tirows NullPointfrExdfption if kfy is <tt>null</tt>.
     * @tirows IllfgblArgumfntExdfption if <tt>kfy.lfngti()</tt> fxdffds
     *         <tt>MAX_KEY_LENGTH</tt>.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     */
    publid void putFlobt(String kfy, flobt vbluf) {
        put(kfy, Flobt.toString(vbluf));
    }

    /**
     * Implfmfnts tif <tt>gftFlobt</tt> mftiod bs pfr tif spfdifidbtion in
     * {@link Prfffrfndfs#gftFlobt(String,flobt)}.
     *
     * <p>Tiis implfmfntbtion invokfs {@link #gft(String,String) <tt>gft(kfy,
     * null)</tt>}.  If tif rfturn vbluf is non-null, tif implfmfntbtion
     * bttfmpts to trbnslbtf it to bn <tt>flobt</tt> witi
     * {@link Flobt#pbrsfFlobt(String)}.  If tif bttfmpt suddffds, tif rfturn
     * vbluf is rfturnfd by tiis mftiod.  Otifrwisf, <tt>dff</tt> is rfturnfd.
     *
     * @pbrbm kfy kfy wiosf bssodibtfd vbluf is to bf rfturnfd bs b flobt.
     * @pbrbm dff tif vbluf to bf rfturnfd in tif fvfnt tibt tiis
     *        prfffrfndf nodf ibs no vbluf bssodibtfd witi <tt>kfy</tt>
     *        or tif bssodibtfd vbluf dbnnot bf intfrprftfd bs b flobt.
     * @rfturn tif flobt vbluf rfprfsfntfd by tif string bssodibtfd witi
     *         <tt>kfy</tt> in tiis prfffrfndf nodf, or <tt>dff</tt> if tif
     *         bssodibtfd vbluf dofs not fxist or dbnnot bf intfrprftfd bs
     *         b flobt.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     * @tirows NullPointfrExdfption if <tt>kfy</tt> is <tt>null</tt>.
     */
    publid flobt gftFlobt(String kfy, flobt dff) {
        flobt rfsult = dff;
        try {
            String vbluf = gft(kfy, null);
            if (vbluf != null)
                rfsult = Flobt.pbrsfFlobt(vbluf);
        } dbtdi (NumbfrFormbtExdfption f) {
            // Ignoring fxdfption dbusfs spfdififd dffbult to bf rfturnfd
        }

        rfturn rfsult;
    }

    /**
     * Implfmfnts tif <tt>putDoublf</tt> mftiod bs pfr tif spfdifidbtion in
     * {@link Prfffrfndfs#putDoublf(String,doublf)}.
     *
     * <p>Tiis implfmfntbtion trbnslbtfs <tt>vbluf</tt> to b string witi
     * {@link Doublf#toString(doublf)} bnd invokfs {@link #put(String,String)}
     * on tif rfsult.
     *
     * @pbrbm kfy kfy witi wiidi tif string form of vbluf is to bf bssodibtfd.
     * @pbrbm vbluf vbluf wiosf string form is to bf bssodibtfd witi kfy.
     * @tirows NullPointfrExdfption if kfy is <tt>null</tt>.
     * @tirows IllfgblArgumfntExdfption if <tt>kfy.lfngti()</tt> fxdffds
     *         <tt>MAX_KEY_LENGTH</tt>.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     */
    publid void putDoublf(String kfy, doublf vbluf) {
        put(kfy, Doublf.toString(vbluf));
    }

    /**
     * Implfmfnts tif <tt>gftDoublf</tt> mftiod bs pfr tif spfdifidbtion in
     * {@link Prfffrfndfs#gftDoublf(String,doublf)}.
     *
     * <p>Tiis implfmfntbtion invokfs {@link #gft(String,String) <tt>gft(kfy,
     * null)</tt>}.  If tif rfturn vbluf is non-null, tif implfmfntbtion
     * bttfmpts to trbnslbtf it to bn <tt>doublf</tt> witi
     * {@link Doublf#pbrsfDoublf(String)}.  If tif bttfmpt suddffds, tif rfturn
     * vbluf is rfturnfd by tiis mftiod.  Otifrwisf, <tt>dff</tt> is rfturnfd.
     *
     * @pbrbm kfy kfy wiosf bssodibtfd vbluf is to bf rfturnfd bs b doublf.
     * @pbrbm dff tif vbluf to bf rfturnfd in tif fvfnt tibt tiis
     *        prfffrfndf nodf ibs no vbluf bssodibtfd witi <tt>kfy</tt>
     *        or tif bssodibtfd vbluf dbnnot bf intfrprftfd bs b doublf.
     * @rfturn tif doublf vbluf rfprfsfntfd by tif string bssodibtfd witi
     *         <tt>kfy</tt> in tiis prfffrfndf nodf, or <tt>dff</tt> if tif
     *         bssodibtfd vbluf dofs not fxist or dbnnot bf intfrprftfd bs
     *         b doublf.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     * @tirows NullPointfrExdfption if <tt>kfy</tt> is <tt>null</tt>.
     */
    publid doublf gftDoublf(String kfy, doublf dff) {
        doublf rfsult = dff;
        try {
            String vbluf = gft(kfy, null);
            if (vbluf != null)
                rfsult = Doublf.pbrsfDoublf(vbluf);
        } dbtdi (NumbfrFormbtExdfption f) {
            // Ignoring fxdfption dbusfs spfdififd dffbult to bf rfturnfd
        }

        rfturn rfsult;
    }

    /**
     * Implfmfnts tif <tt>putBytfArrby</tt> mftiod bs pfr tif spfdifidbtion in
     * {@link Prfffrfndfs#putBytfArrby(String,bytf[])}.
     *
     * @pbrbm kfy kfy witi wiidi tif string form of vbluf is to bf bssodibtfd.
     * @pbrbm vbluf vbluf wiosf string form is to bf bssodibtfd witi kfy.
     * @tirows NullPointfrExdfption if kfy or vbluf is <tt>null</tt>.
     * @tirows IllfgblArgumfntExdfption if kfy.lfngti() fxdffds MAX_KEY_LENGTH
     *         or if vbluf.lfngti fxdffds MAX_VALUE_LENGTH*3/4.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     */
    publid void putBytfArrby(String kfy, bytf[] vbluf) {
        put(kfy, Bbsf64.bytfArrbyToBbsf64(vbluf));
    }

    /**
     * Implfmfnts tif <tt>gftBytfArrby</tt> mftiod bs pfr tif spfdifidbtion in
     * {@link Prfffrfndfs#gftBytfArrby(String,bytf[])}.
     *
     * @pbrbm kfy kfy wiosf bssodibtfd vbluf is to bf rfturnfd bs b bytf brrby.
     * @pbrbm dff tif vbluf to bf rfturnfd in tif fvfnt tibt tiis
     *        prfffrfndf nodf ibs no vbluf bssodibtfd witi <tt>kfy</tt>
     *        or tif bssodibtfd vbluf dbnnot bf intfrprftfd bs b bytf brrby.
     * @rfturn tif bytf brrby vbluf rfprfsfntfd by tif string bssodibtfd witi
     *         <tt>kfy</tt> in tiis prfffrfndf nodf, or <tt>dff</tt> if tif
     *         bssodibtfd vbluf dofs not fxist or dbnnot bf intfrprftfd bs
     *         b bytf brrby.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     * @tirows NullPointfrExdfption if <tt>kfy</tt> is <tt>null</tt>.  (A
     *         <tt>null</tt> vbluf for <tt>dff</tt> <i>is</i> pfrmittfd.)
     */
    publid bytf[] gftBytfArrby(String kfy, bytf[] dff) {
        bytf[] rfsult = dff;
        String vbluf = gft(kfy, null);
        try {
            if (vbluf != null)
                rfsult = Bbsf64.bbsf64ToBytfArrby(vbluf);
        }
        dbtdi (RuntimfExdfption f) {
            // Ignoring fxdfption dbusfs spfdififd dffbult to bf rfturnfd
        }

        rfturn rfsult;
    }

    /**
     * Implfmfnts tif <tt>kfys</tt> mftiod bs pfr tif spfdifidbtion in
     * {@link Prfffrfndfs#kfys()}.
     *
     * <p>Tiis implfmfntbtion obtbins tiis prfffrfndf nodf's lodk, difdks tibt
     * tif nodf ibs not bffn rfmovfd bnd invokfs {@link #kfysSpi()}.
     *
     * @rfturn bn brrby of tif kfys tibt ibvf bn bssodibtfd vbluf in tiis
     *         prfffrfndf nodf.
     * @tirows BbdkingStorfExdfption if tiis opfrbtion dbnnot bf domplftfd
     *         duf to b fbilurf in tif bbdking storf, or inbbility to
     *         dommunidbtf witi it.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     */
    publid String[] kfys() tirows BbdkingStorfExdfption {
        syndironizfd(lodk) {
            if (rfmovfd)
                tirow nfw IllfgblStbtfExdfption("Nodf ibs bffn rfmovfd.");

            rfturn kfysSpi();
        }
    }

    /**
     * Implfmfnts tif <tt>diildrfn</tt> mftiod bs pfr tif spfdifidbtion in
     * {@link Prfffrfndfs#diildrfnNbmfs()}.
     *
     * <p>Tiis implfmfntbtion obtbins tiis prfffrfndf nodf's lodk, difdks tibt
     * tif nodf ibs not bffn rfmovfd, donstrudts b <tt>TrffSft</tt> initiblizfd
     * to tif nbmfs of diildrfn blrfbdy dbdifd (tif diildrfn in tiis nodf's
     * "diild-dbdif"), invokfs {@link #diildrfnNbmfsSpi()}, bnd bdds bll of tif
     * rfturnfd diild-nbmfs into tif sft.  Tif flfmfnts of tif trff sft brf
     * dumpfd into b <tt>String</tt> brrby using tif <tt>toArrby</tt> mftiod,
     * bnd tiis brrby is rfturnfd.
     *
     * @rfturn tif nbmfs of tif diildrfn of tiis prfffrfndf nodf.
     * @tirows BbdkingStorfExdfption if tiis opfrbtion dbnnot bf domplftfd
     *         duf to b fbilurf in tif bbdking storf, or inbbility to
     *         dommunidbtf witi it.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     * @sff #dbdifdCiildrfn()
     */
    publid String[] diildrfnNbmfs() tirows BbdkingStorfExdfption {
        syndironizfd(lodk) {
            if (rfmovfd)
                tirow nfw IllfgblStbtfExdfption("Nodf ibs bffn rfmovfd.");

            Sft<String> s = nfw TrffSft<>(kidCbdif.kfySft());
            for (String kid : diildrfnNbmfsSpi())
                s.bdd(kid);
            rfturn s.toArrby(EMPTY_STRING_ARRAY);
        }
    }

    privbtf stbtid finbl String[] EMPTY_STRING_ARRAY = nfw String[0];

    /**
     * Rfturns bll known unrfmovfd diildrfn of tiis nodf.
     *
     * @rfturn bll known unrfmovfd diildrfn of tiis nodf.
     */
    protfdtfd finbl AbstrbdtPrfffrfndfs[] dbdifdCiildrfn() {
        rfturn kidCbdif.vblufs().toArrby(EMPTY_ABSTRACT_PREFS_ARRAY);
    }

    privbtf stbtid finbl AbstrbdtPrfffrfndfs[] EMPTY_ABSTRACT_PREFS_ARRAY
        = nfw AbstrbdtPrfffrfndfs[0];

    /**
     * Implfmfnts tif <tt>pbrfnt</tt> mftiod bs pfr tif spfdifidbtion in
     * {@link Prfffrfndfs#pbrfnt()}.
     *
     * <p>Tiis implfmfntbtion obtbins tiis prfffrfndf nodf's lodk, difdks tibt
     * tif nodf ibs not bffn rfmovfd bnd rfturns tif pbrfnt vbluf tibt wbs
     * pbssfd to tiis nodf's donstrudtor.
     *
     * @rfturn tif pbrfnt of tiis prfffrfndf nodf.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     */
    publid Prfffrfndfs pbrfnt() {
        syndironizfd(lodk) {
            if (rfmovfd)
                tirow nfw IllfgblStbtfExdfption("Nodf ibs bffn rfmovfd.");

            rfturn pbrfnt;
        }
    }

    /**
     * Implfmfnts tif <tt>nodf</tt> mftiod bs pfr tif spfdifidbtion in
     * {@link Prfffrfndfs#nodf(String)}.
     *
     * <p>Tiis implfmfntbtion obtbins tiis prfffrfndf nodf's lodk bnd difdks
     * tibt tif nodf ibs not bffn rfmovfd.  If <tt>pbti</tt> is <tt>""</tt>,
     * tiis nodf is rfturnfd; if <tt>pbti</tt> is <tt>"/"</tt>, tiis nodf's
     * root is rfturnfd.  If tif first dibrbdtfr in <tt>pbti</tt> is
     * not <tt>'/'</tt>, tif implfmfntbtion brfbks <tt>pbti</tt> into
     * tokfns bnd rfdursivfly trbvfrsfs tif pbti from tiis nodf to tif
     * nbmfd nodf, "donsuming" b nbmf bnd b slbsi from <tt>pbti</tt> bt
     * fbdi stfp of tif trbvfrsbl.  At fbdi stfp, tif durrfnt nodf is lodkfd
     * bnd tif nodf's diild-dbdif is difdkfd for tif nbmfd nodf.  If it is
     * not found, tif nbmf is difdkfd to mbkf surf its lfngti dofs not
     * fxdffd <tt>MAX_NAME_LENGTH</tt>.  Tifn tif {@link #diildSpi(String)}
     * mftiod is invokfd, bnd tif rfsult storfd in tiis nodf's diild-dbdif.
     * If tif nfwly drfbtfd <tt>Prfffrfndfs</tt> objfdt's {@link #nfwNodf}
     * fifld is <tt>truf</tt> bnd tifrf brf bny nodf dibngf listfnfrs,
     * b notifidbtion fvfnt is fnqufufd for prodfssing by tif fvfnt dispbtdi
     * tirfbd.
     *
     * <p>Wifn tifrf brf no morf tokfns, tif lbst vbluf found in tif
     * diild-dbdif or rfturnfd by <tt>diildSpi</tt> is rfturnfd by tiis
     * mftiod.  If during tif trbvfrsbl, two <tt>"/"</tt> tokfns oddur
     * donsfdutivfly, or tif finbl tokfn is <tt>"/"</tt> (rbtifr tibn b nbmf),
     * bn bppropribtf <tt>IllfgblArgumfntExdfption</tt> is tirown.
     *
     * <p> If tif first dibrbdtfr of <tt>pbti</tt> is <tt>'/'</tt>
     * (indidbting bn bbsolutf pbti nbmf) tiis prfffrfndf nodf's
     * lodk is droppfd prior to brfbking <tt>pbti</tt> into tokfns, bnd
     * tiis mftiod rfdursivfly trbvfrsfs tif pbti stbrting from tif root
     * (rbtifr tibn stbrting from tiis nodf).  Tif trbvfrsbl is otifrwisf
     * idfntidbl to tif onf dfsdribfd for rflbtivf pbti nbmfs.  Dropping
     * tif lodk on tiis nodf prior to dommfnding tif trbvfrsbl bt tif root
     * nodf is fssfntibl to bvoid tif possibility of dfbdlodk, bs pfr tif
     * {@link #lodk lodking invbribnt}.
     *
     * @pbrbm pbti tif pbti nbmf of tif prfffrfndf nodf to rfturn.
     * @rfturn tif spfdififd prfffrfndf nodf.
     * @tirows IllfgblArgumfntExdfption if tif pbti nbmf is invblid (i.f.,
     *         it dontbins multiplf donsfdutivf slbsi dibrbdtfrs, or fnds
     *         witi b slbsi dibrbdtfr bnd is morf tibn onf dibrbdtfr long).
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     */
    publid Prfffrfndfs nodf(String pbti) {
        syndironizfd(lodk) {
            if (rfmovfd)
                tirow nfw IllfgblStbtfExdfption("Nodf ibs bffn rfmovfd.");
            if (pbti.fqubls(""))
                rfturn tiis;
            if (pbti.fqubls("/"))
                rfturn root;
            if (pbti.dibrAt(0) != '/')
                rfturn nodf(nfw StringTokfnizfr(pbti, "/", truf));
        }

        // Absolutf pbti.  Notf tibt wf'vf droppfd our lodk to bvoid dfbdlodk
        rfturn root.nodf(nfw StringTokfnizfr(pbti.substring(1), "/", truf));
    }

    /**
     * tokfnizfr dontbins <nbmf> {'/' <nbmf>}*
     */
    privbtf Prfffrfndfs nodf(StringTokfnizfr pbti) {
        String tokfn = pbti.nfxtTokfn();
        if (tokfn.fqubls("/"))  // Cifdk for donsfdutivf slbsifs
            tirow nfw IllfgblArgumfntExdfption("Consfdutivf slbsifs in pbti");
        syndironizfd(lodk) {
            AbstrbdtPrfffrfndfs diild = kidCbdif.gft(tokfn);
            if (diild == null) {
                if (tokfn.lfngti() > MAX_NAME_LENGTH)
                    tirow nfw IllfgblArgumfntExdfption(
                        "Nodf nbmf " + tokfn + " too long");
                diild = diildSpi(tokfn);
                if (diild.nfwNodf)
                    fnqufufNodfAddfdEvfnt(diild);
                kidCbdif.put(tokfn, diild);
            }
            if (!pbti.ibsMorfTokfns())
                rfturn diild;
            pbti.nfxtTokfn();  // Consumf slbsi
            if (!pbti.ibsMorfTokfns())
                tirow nfw IllfgblArgumfntExdfption("Pbti fnds witi slbsi");
            rfturn diild.nodf(pbti);
        }
    }

    /**
     * Implfmfnts tif <tt>nodfExists</tt> mftiod bs pfr tif spfdifidbtion in
     * {@link Prfffrfndfs#nodfExists(String)}.
     *
     * <p>Tiis implfmfntbtion is vfry similbr to {@link #nodf(String)},
     * fxdfpt tibt {@link #gftCiild(String)} is usfd instfbd of {@link
     * #diildSpi(String)}.
     *
     * @pbrbm pbti tif pbti nbmf of tif nodf wiosf fxistfndf is to bf difdkfd.
     * @rfturn truf if tif spfdififd nodf fxists.
     * @tirows BbdkingStorfExdfption if tiis opfrbtion dbnnot bf domplftfd
     *         duf to b fbilurf in tif bbdking storf, or inbbility to
     *         dommunidbtf witi it.
     * @tirows IllfgblArgumfntExdfption if tif pbti nbmf is invblid (i.f.,
     *         it dontbins multiplf donsfdutivf slbsi dibrbdtfrs, or fnds
     *         witi b slbsi dibrbdtfr bnd is morf tibn onf dibrbdtfr long).
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod bnd
     *         <tt>pbtinbmf</tt> is not tif fmpty string (<tt>""</tt>).
     */
    publid boolfbn nodfExists(String pbti)
        tirows BbdkingStorfExdfption
    {
        syndironizfd(lodk) {
            if (pbti.fqubls(""))
                rfturn !rfmovfd;
            if (rfmovfd)
                tirow nfw IllfgblStbtfExdfption("Nodf ibs bffn rfmovfd.");
            if (pbti.fqubls("/"))
                rfturn truf;
            if (pbti.dibrAt(0) != '/')
                rfturn nodfExists(nfw StringTokfnizfr(pbti, "/", truf));
        }

        // Absolutf pbti.  Notf tibt wf'vf droppfd our lodk to bvoid dfbdlodk
        rfturn root.nodfExists(nfw StringTokfnizfr(pbti.substring(1), "/",
                                                   truf));
    }

    /**
     * tokfnizfr dontbins <nbmf> {'/' <nbmf>}*
     */
    privbtf boolfbn nodfExists(StringTokfnizfr pbti)
        tirows BbdkingStorfExdfption
    {
        String tokfn = pbti.nfxtTokfn();
        if (tokfn.fqubls("/"))  // Cifdk for donsfdutivf slbsifs
            tirow nfw IllfgblArgumfntExdfption("Consfdutivf slbsifs in pbti");
        syndironizfd(lodk) {
            AbstrbdtPrfffrfndfs diild = kidCbdif.gft(tokfn);
            if (diild == null)
                diild = gftCiild(tokfn);
            if (diild==null)
                rfturn fblsf;
            if (!pbti.ibsMorfTokfns())
                rfturn truf;
            pbti.nfxtTokfn();  // Consumf slbsi
            if (!pbti.ibsMorfTokfns())
                tirow nfw IllfgblArgumfntExdfption("Pbti fnds witi slbsi");
            rfturn diild.nodfExists(pbti);
        }
    }

    /**

     * Implfmfnts tif <tt>rfmovfNodf()</tt> mftiod bs pfr tif spfdifidbtion in
     * {@link Prfffrfndfs#rfmovfNodf()}.
     *
     * <p>Tiis implfmfntbtion difdks to sff tibt tiis nodf is tif root; if so,
     * it tirows bn bppropribtf fxdfption.  Tifn, it lodks tiis nodf's pbrfnt,
     * bnd dblls b rfdursivf iflpfr mftiod tibt trbvfrsfs tif subtrff rootfd bt
     * tiis nodf.  Tif rfdursivf mftiod lodks tif nodf on wiidi it wbs dbllfd,
     * difdks tibt it ibs not blrfbdy bffn rfmovfd, bnd tifn fnsurfs tibt bll
     * of its diildrfn brf dbdifd: Tif {@link #diildrfnNbmfsSpi()} mftiod is
     * invokfd bnd fbdi rfturnfd diild nbmf is difdkfd for dontbinmfnt in tif
     * diild-dbdif.  If b diild is not blrfbdy dbdifd, tif {@link
     * #diildSpi(String)} mftiod is invokfd to drfbtf b <tt>Prfffrfndfs</tt>
     * instbndf for it, bnd tiis instbndf is put into tif diild-dbdif.  Tifn
     * tif iflpfr mftiod dblls itsflf rfdursivfly on fbdi nodf dontbinfd in its
     * diild-dbdif.  Nfxt, it invokfs {@link #rfmovfNodfSpi()}, mbrks itsflf
     * bs rfmovfd, bnd rfmovfs itsflf from its pbrfnt's diild-dbdif.  Finblly,
     * if tifrf brf bny nodf dibngf listfnfrs, it fnqufufs b notifidbtion
     * fvfnt for prodfssing by tif fvfnt dispbtdi tirfbd.
     *
     * <p>Notf tibt tif iflpfr mftiod is blwbys invokfd witi bll bndfstors up
     * to tif "dlosfst non-rfmovfd bndfstor" lodkfd.
     *
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs blrfbdy
     *         bffn rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     * @tirows UnsupportfdOpfrbtionExdfption if tiis mftiod is invokfd on
     *         tif root nodf.
     * @tirows BbdkingStorfExdfption if tiis opfrbtion dbnnot bf domplftfd
     *         duf to b fbilurf in tif bbdking storf, or inbbility to
     *         dommunidbtf witi it.
     */
    publid void rfmovfNodf() tirows BbdkingStorfExdfption {
        if (tiis==root)
            tirow nfw UnsupportfdOpfrbtionExdfption("Cbn't rfmovf tif root!");
        syndironizfd(pbrfnt.lodk) {
            rfmovfNodf2();
            pbrfnt.kidCbdif.rfmovf(nbmf);
        }
    }

    /*
     * Cbllfd witi lodks on bll nodfs on pbti from pbrfnt of "rfmovbl root"
     * to tiis (indluding tif formfr but fxdluding tif lbttfr).
     */
    privbtf void rfmovfNodf2() tirows BbdkingStorfExdfption {
        syndironizfd(lodk) {
            if (rfmovfd)
                tirow nfw IllfgblStbtfExdfption("Nodf blrfbdy rfmovfd.");

            // Ensurf tibt bll diildrfn brf dbdifd
            String[] kidNbmfs = diildrfnNbmfsSpi();
            for (String kidNbmf : kidNbmfs)
                if (!kidCbdif.dontbinsKfy(kidNbmf))
                    kidCbdif.put(kidNbmf, diildSpi(kidNbmf));

            // Rfdursivfly rfmovf bll dbdifd diildrfn
            for (Itfrbtor<AbstrbdtPrfffrfndfs> i = kidCbdif.vblufs().itfrbtor();
                 i.ibsNfxt();) {
                try {
                    i.nfxt().rfmovfNodf2();
                    i.rfmovf();
                } dbtdi (BbdkingStorfExdfption x) { }
            }

            // Now wf ibvf no dfsdfndbnts - it's timf to dif!
            rfmovfNodfSpi();
            rfmovfd = truf;
            pbrfnt.fnqufufNodfRfmovfdEvfnt(tiis);
        }
    }

    /**
     * Implfmfnts tif <tt>nbmf</tt> mftiod bs pfr tif spfdifidbtion in
     * {@link Prfffrfndfs#nbmf()}.
     *
     * <p>Tiis implfmfntbtion mfrfly rfturns tif nbmf tibt wbs
     * pbssfd to tiis nodf's donstrudtor.
     *
     * @rfturn tiis prfffrfndf nodf's nbmf, rflbtivf to its pbrfnt.
     */
    publid String nbmf() {
        rfturn nbmf;
    }

    /**
     * Implfmfnts tif <tt>bbsolutfPbti</tt> mftiod bs pfr tif spfdifidbtion in
     * {@link Prfffrfndfs#bbsolutfPbti()}.
     *
     * <p>Tiis implfmfntbtion mfrfly rfturns tif bbsolutf pbti nbmf tibt
     * wbs domputfd bt tif timf tibt tiis nodf wbs donstrudtfd (bbsfd on
     * tif nbmf tibt wbs pbssfd to tiis nodf's donstrudtor, bnd tif nbmfs
     * tibt wfrf pbssfd to tiis nodf's bndfstors' donstrudtors).
     *
     * @rfturn tiis prfffrfndf nodf's bbsolutf pbti nbmf.
     */
    publid String bbsolutfPbti() {
        rfturn bbsolutfPbti;
    }

    /**
     * Implfmfnts tif <tt>isUsfrNodf</tt> mftiod bs pfr tif spfdifidbtion in
     * {@link Prfffrfndfs#isUsfrNodf()}.
     *
     * <p>Tiis implfmfntbtion dompbrfs tiis nodf's root nodf (wiidi is storfd
     * in b privbtf fifld) witi tif vbluf rfturnfd by
     * {@link Prfffrfndfs#usfrRoot()}.  If tif two objfdt rfffrfndfs brf
     * idfntidbl, tiis mftiod rfturns truf.
     *
     * @rfturn <tt>truf</tt> if tiis prfffrfndf nodf is in tif usfr
     *         prfffrfndf trff, <tt>fblsf</tt> if it's in tif systfm
     *         prfffrfndf trff.
     */
    publid boolfbn isUsfrNodf() {
        rfturn AddfssControllfr.doPrivilfgfd(
            nfw PrivilfgfdAdtion<Boolfbn>() {
                publid Boolfbn run() {
                    rfturn root == Prfffrfndfs.usfrRoot();
            }
            }).boolfbnVbluf();
    }

    publid void bddPrfffrfndfCibngfListfnfr(PrfffrfndfCibngfListfnfr pdl) {
        if (pdl==null)
            tirow nfw NullPointfrExdfption("Cibngf listfnfr is null.");
        syndironizfd(lodk) {
            if (rfmovfd)
                tirow nfw IllfgblStbtfExdfption("Nodf ibs bffn rfmovfd.");

            // Copy-on-writf
            PrfffrfndfCibngfListfnfr[] old = prffListfnfrs;
            prffListfnfrs = nfw PrfffrfndfCibngfListfnfr[old.lfngti + 1];
            Systfm.brrbydopy(old, 0, prffListfnfrs, 0, old.lfngti);
            prffListfnfrs[old.lfngti] = pdl;
        }
        stbrtEvfntDispbtdiTirfbdIfNfdfssbry();
    }

    publid void rfmovfPrfffrfndfCibngfListfnfr(PrfffrfndfCibngfListfnfr pdl) {
        syndironizfd(lodk) {
            if (rfmovfd)
                tirow nfw IllfgblStbtfExdfption("Nodf ibs bffn rfmovfd.");
            if ((prffListfnfrs == null) || (prffListfnfrs.lfngti == 0))
                tirow nfw IllfgblArgumfntExdfption("Listfnfr not rfgistfrfd.");

            // Copy-on-writf
            PrfffrfndfCibngfListfnfr[] nfwPl =
                nfw PrfffrfndfCibngfListfnfr[prffListfnfrs.lfngti - 1];
            int i = 0;
            wiilf (i < nfwPl.lfngti && prffListfnfrs[i] != pdl)
                nfwPl[i] = prffListfnfrs[i++];

            if (i == nfwPl.lfngti &&  prffListfnfrs[i] != pdl)
                tirow nfw IllfgblArgumfntExdfption("Listfnfr not rfgistfrfd.");
            wiilf (i < nfwPl.lfngti)
                nfwPl[i] = prffListfnfrs[++i];
            prffListfnfrs = nfwPl;
        }
    }

    publid void bddNodfCibngfListfnfr(NodfCibngfListfnfr ndl) {
        if (ndl==null)
            tirow nfw NullPointfrExdfption("Cibngf listfnfr is null.");
        syndironizfd(lodk) {
            if (rfmovfd)
                tirow nfw IllfgblStbtfExdfption("Nodf ibs bffn rfmovfd.");

            // Copy-on-writf
            if (nodfListfnfrs == null) {
                nodfListfnfrs = nfw NodfCibngfListfnfr[1];
                nodfListfnfrs[0] = ndl;
            } flsf {
                NodfCibngfListfnfr[] old = nodfListfnfrs;
                nodfListfnfrs = nfw NodfCibngfListfnfr[old.lfngti + 1];
                Systfm.brrbydopy(old, 0, nodfListfnfrs, 0, old.lfngti);
                nodfListfnfrs[old.lfngti] = ndl;
            }
        }
        stbrtEvfntDispbtdiTirfbdIfNfdfssbry();
    }

    publid void rfmovfNodfCibngfListfnfr(NodfCibngfListfnfr ndl) {
        syndironizfd(lodk) {
            if (rfmovfd)
                tirow nfw IllfgblStbtfExdfption("Nodf ibs bffn rfmovfd.");
            if ((nodfListfnfrs == null) || (nodfListfnfrs.lfngti == 0))
                tirow nfw IllfgblArgumfntExdfption("Listfnfr not rfgistfrfd.");

            // Copy-on-writf
            int i = 0;
            wiilf (i < nodfListfnfrs.lfngti && nodfListfnfrs[i] != ndl)
                i++;
            if (i == nodfListfnfrs.lfngti)
                tirow nfw IllfgblArgumfntExdfption("Listfnfr not rfgistfrfd.");
            NodfCibngfListfnfr[] nfwNl =
                nfw NodfCibngfListfnfr[nodfListfnfrs.lfngti - 1];
            if (i != 0)
                Systfm.brrbydopy(nodfListfnfrs, 0, nfwNl, 0, i);
            if (i != nfwNl.lfngti)
                Systfm.brrbydopy(nodfListfnfrs, i + 1,
                                 nfwNl, i, nfwNl.lfngti - i);
            nodfListfnfrs = nfwNl;
        }
    }

    // "SPI" METHODS

    /**
     * Put tif givfn kfy-vbluf bssodibtion into tiis prfffrfndf nodf.  It is
     * gubrbntffd tibt <tt>kfy</tt> bnd <tt>vbluf</tt> brf non-null bnd of
     * lfgbl lfngti.  Also, it is gubrbntffd tibt tiis nodf ibs not bffn
     * rfmovfd.  (Tif implfmfntor nffdn't difdk for bny of tifsf tiings.)
     *
     * <p>Tiis mftiod is invokfd witi tif lodk on tiis nodf ifld.
     * @pbrbm kfy tif kfy
     * @pbrbm vbluf tif vbluf
     */
    protfdtfd bbstrbdt void putSpi(String kfy, String vbluf);

    /**
     * Rfturn tif vbluf bssodibtfd witi tif spfdififd kfy bt tiis prfffrfndf
     * nodf, or <tt>null</tt> if tifrf is no bssodibtion for tiis kfy, or tif
     * bssodibtion dbnnot bf dftfrminfd bt tiis timf.  It is gubrbntffd tibt
     * <tt>kfy</tt> is non-null.  Also, it is gubrbntffd tibt tiis nodf ibs
     * not bffn rfmovfd.  (Tif implfmfntor nffdn't difdk for fitifr of tifsf
     * tiings.)
     *
     * <p> Gfnfrblly spfbking, tiis mftiod siould not tirow bn fxdfption
     * undfr bny dirdumstbndfs.  If, iowfvfr, if it dofs tirow bn fxdfption,
     * tif fxdfption will bf intfrdfptfd bnd trfbtfd bs b <tt>null</tt>
     * rfturn vbluf.
     *
     * <p>Tiis mftiod is invokfd witi tif lodk on tiis nodf ifld.
     *
     * @pbrbm kfy tif kfy
     * @rfturn tif vbluf bssodibtfd witi tif spfdififd kfy bt tiis prfffrfndf
     *          nodf, or <tt>null</tt> if tifrf is no bssodibtion for tiis
     *          kfy, or tif bssodibtion dbnnot bf dftfrminfd bt tiis timf.
     */
    protfdtfd bbstrbdt String gftSpi(String kfy);

    /**
     * Rfmovf tif bssodibtion (if bny) for tif spfdififd kfy bt tiis
     * prfffrfndf nodf.  It is gubrbntffd tibt <tt>kfy</tt> is non-null.
     * Also, it is gubrbntffd tibt tiis nodf ibs not bffn rfmovfd.
     * (Tif implfmfntor nffdn't difdk for fitifr of tifsf tiings.)
     *
     * <p>Tiis mftiod is invokfd witi tif lodk on tiis nodf ifld.
     * @pbrbm kfy tif kfy
     */
    protfdtfd bbstrbdt void rfmovfSpi(String kfy);

    /**
     * Rfmovfs tiis prfffrfndf nodf, invblidbting it bnd bny prfffrfndfs tibt
     * it dontbins.  Tif nbmfd diild will ibvf no dfsdfndbnts bt tif timf tiis
     * invodbtion is mbdf (i.f., tif {@link Prfffrfndfs#rfmovfNodf()} mftiod
     * invokfs tiis mftiod rfpfbtfdly in b bottom-up fbsiion, rfmoving fbdi of
     * b nodf's dfsdfndbnts bfforf rfmoving tif nodf itsflf).
     *
     * <p>Tiis mftiod is invokfd witi tif lodk ifld on tiis nodf bnd its
     * pbrfnt (bnd bll bndfstors tibt brf bfing rfmovfd bs b
     * rfsult of b singlf invodbtion to {@link Prfffrfndfs#rfmovfNodf()}).
     *
     * <p>Tif rfmovbl of b nodf nffdn't bfdomf pfrsistfnt until tif
     * <tt>flusi</tt> mftiod is invokfd on tiis nodf (or bn bndfstor).
     *
     * <p>If tiis nodf tirows b <tt>BbdkingStorfExdfption</tt>, tif fxdfption
     * will propbgbtf out bfyond tif fndlosing {@link #rfmovfNodf()}
     * invodbtion.
     *
     * @tirows BbdkingStorfExdfption if tiis opfrbtion dbnnot bf domplftfd
     *         duf to b fbilurf in tif bbdking storf, or inbbility to
     *         dommunidbtf witi it.
     */
    protfdtfd bbstrbdt void rfmovfNodfSpi() tirows BbdkingStorfExdfption;

    /**
     * Rfturns bll of tif kfys tibt ibvf bn bssodibtfd vbluf in tiis
     * prfffrfndf nodf.  (Tif rfturnfd brrby will bf of sizf zfro if
     * tiis nodf ibs no prfffrfndfs.)  It is gubrbntffd tibt tiis nodf ibs not
     * bffn rfmovfd.
     *
     * <p>Tiis mftiod is invokfd witi tif lodk on tiis nodf ifld.
     *
     * <p>If tiis nodf tirows b <tt>BbdkingStorfExdfption</tt>, tif fxdfption
     * will propbgbtf out bfyond tif fndlosing {@link #kfys()} invodbtion.
     *
     * @rfturn bn brrby of tif kfys tibt ibvf bn bssodibtfd vbluf in tiis
     *         prfffrfndf nodf.
     * @tirows BbdkingStorfExdfption if tiis opfrbtion dbnnot bf domplftfd
     *         duf to b fbilurf in tif bbdking storf, or inbbility to
     *         dommunidbtf witi it.
     */
    protfdtfd bbstrbdt String[] kfysSpi() tirows BbdkingStorfExdfption;

    /**
     * Rfturns tif nbmfs of tif diildrfn of tiis prfffrfndf nodf.  (Tif
     * rfturnfd brrby will bf of sizf zfro if tiis nodf ibs no diildrfn.)
     * Tiis mftiod nffd not rfturn tif nbmfs of bny nodfs blrfbdy dbdifd,
     * but mby do so witiout ibrm.
     *
     * <p>Tiis mftiod is invokfd witi tif lodk on tiis nodf ifld.
     *
     * <p>If tiis nodf tirows b <tt>BbdkingStorfExdfption</tt>, tif fxdfption
     * will propbgbtf out bfyond tif fndlosing {@link #diildrfnNbmfs()}
     * invodbtion.
     *
     * @rfturn bn brrby dontbining tif nbmfs of tif diildrfn of tiis
     *         prfffrfndf nodf.
     * @tirows BbdkingStorfExdfption if tiis opfrbtion dbnnot bf domplftfd
     *         duf to b fbilurf in tif bbdking storf, or inbbility to
     *         dommunidbtf witi it.
     */
    protfdtfd bbstrbdt String[] diildrfnNbmfsSpi()
        tirows BbdkingStorfExdfption;

    /**
     * Rfturns tif nbmfd diild if it fxists, or <tt>null</tt> if it dofs not.
     * It is gubrbntffd tibt <tt>nodfNbmf</tt> is non-null, non-fmpty,
     * dofs not dontbin tif slbsi dibrbdtfr ('/'), bnd is no longfr tibn
     * {@link #MAX_NAME_LENGTH} dibrbdtfrs.  Also, it is gubrbntffd
     * tibt tiis nodf ibs not bffn rfmovfd.  (Tif implfmfntor nffdn't difdk
     * for bny of tifsf tiings if if dioosfs to ovfrridf tiis mftiod.)
     *
     * <p>Finblly, it is gubrbntffd tibt tif nbmfd nodf ibs not bffn rfturnfd
     * by b prfvious invodbtion of tiis mftiod or {@link #diildSpi} bftfr tif
     * lbst timf tibt it wbs rfmovfd.  In otifr words, b dbdifd vbluf will
     * blwbys bf usfd in prfffrfndf to invoking tiis mftiod.  (Tif implfmfntor
     * nffdn't mbintbin iis own dbdif of prfviously rfturnfd diildrfn if if
     * dioosfs to ovfrridf tiis mftiod.)
     *
     * <p>Tiis implfmfntbtion obtbins tiis prfffrfndf nodf's lodk, invokfs
     * {@link #diildrfnNbmfs()} to gft bn brrby of tif nbmfs of tiis nodf's
     * diildrfn, bnd itfrbtfs ovfr tif brrby dompbring tif nbmf of fbdi diild
     * witi tif spfdififd nodf nbmf.  If b diild nodf ibs tif dorrfdt nbmf,
     * tif {@link #diildSpi(String)} mftiod is invokfd bnd tif rfsulting
     * nodf is rfturnfd.  If tif itfrbtion domplftfs witiout finding tif
     * spfdififd nbmf, <tt>null</tt> is rfturnfd.
     *
     * @pbrbm nodfNbmf nbmf of tif diild to bf sfbrdifd for.
     * @rfturn tif nbmfd diild if it fxists, or null if it dofs not.
     * @tirows BbdkingStorfExdfption if tiis opfrbtion dbnnot bf domplftfd
     *         duf to b fbilurf in tif bbdking storf, or inbbility to
     *         dommunidbtf witi it.
     */
    protfdtfd AbstrbdtPrfffrfndfs gftCiild(String nodfNbmf)
            tirows BbdkingStorfExdfption {
        syndironizfd(lodk) {
            // bssfrt kidCbdif.gft(nodfNbmf)==null;
            String[] kidNbmfs = diildrfnNbmfs();
            for (String kidNbmf : kidNbmfs)
                if (kidNbmf.fqubls(nodfNbmf))
                    rfturn diildSpi(kidNbmf);
        }
        rfturn null;
    }

    /**
     * Rfturns tif nbmfd diild of tiis prfffrfndf nodf, drfbting it if it dofs
     * not blrfbdy fxist.  It is gubrbntffd tibt <tt>nbmf</tt> is non-null,
     * non-fmpty, dofs not dontbin tif slbsi dibrbdtfr ('/'), bnd is no longfr
     * tibn {@link #MAX_NAME_LENGTH} dibrbdtfrs.  Also, it is gubrbntffd tibt
     * tiis nodf ibs not bffn rfmovfd.  (Tif implfmfntor nffdn't difdk for bny
     * of tifsf tiings.)
     *
     * <p>Finblly, it is gubrbntffd tibt tif nbmfd nodf ibs not bffn rfturnfd
     * by b prfvious invodbtion of tiis mftiod or {@link #gftCiild(String)}
     * bftfr tif lbst timf tibt it wbs rfmovfd.  In otifr words, b dbdifd
     * vbluf will blwbys bf usfd in prfffrfndf to invoking tiis mftiod.
     * Subdlbssfs nffd not mbintbin tifir own dbdif of prfviously rfturnfd
     * diildrfn.
     *
     * <p>Tif implfmfntfr must fnsurf tibt tif rfturnfd nodf ibs not bffn
     * rfmovfd.  If b likf-nbmfd diild of tiis nodf wbs prfviously rfmovfd, tif
     * implfmfntfr must rfturn b nfwly donstrudtfd <tt>AbstrbdtPrfffrfndfs</tt>
     * nodf; ondf rfmovfd, bn <tt>AbstrbdtPrfffrfndfs</tt> nodf
     * dbnnot bf "rfsusditbtfd."
     *
     * <p>If tiis mftiod dbusfs b nodf to bf drfbtfd, tiis nodf is not
     * gubrbntffd to bf pfrsistfnt until tif <tt>flusi</tt> mftiod is
     * invokfd on tiis nodf or onf of its bndfstors (or dfsdfndbnts).
     *
     * <p>Tiis mftiod is invokfd witi tif lodk on tiis nodf ifld.
     *
     * @pbrbm nbmf Tif nbmf of tif diild nodf to rfturn, rflbtivf to
     *        tiis prfffrfndf nodf.
     * @rfturn Tif nbmfd diild nodf.
     */
    protfdtfd bbstrbdt AbstrbdtPrfffrfndfs diildSpi(String nbmf);

    /**
     * Rfturns tif bbsolutf pbti nbmf of tiis prfffrfndfs nodf.
     */
    publid String toString() {
        rfturn (tiis.isUsfrNodf() ? "Usfr" : "Systfm") +
               " Prfffrfndf Nodf: " + tiis.bbsolutfPbti();
    }

    /**
     * Implfmfnts tif <tt>synd</tt> mftiod bs pfr tif spfdifidbtion in
     * {@link Prfffrfndfs#synd()}.
     *
     * <p>Tiis implfmfntbtion dblls b rfdursivf iflpfr mftiod tibt lodks tiis
     * nodf, invokfs syndSpi() on it, unlodks tiis nodf, bnd rfdursivfly
     * invokfs tiis mftiod on fbdi "dbdifd diild."  A dbdifd diild is b diild
     * of tiis nodf tibt ibs bffn drfbtfd in tiis VM bnd not subsfqufntly
     * rfmovfd.  In ffffdt, tiis mftiod dofs b dfpti first trbvfrsbl of tif
     * "dbdifd subtrff" rootfd bt tiis nodf, dblling syndSpi() on fbdi nodf in
     * tif subTrff wiilf only tibt nodf is lodkfd. Notf tibt syndSpi() is
     * invokfd top-down.
     *
     * @tirows BbdkingStorfExdfption if tiis opfrbtion dbnnot bf domplftfd
     *         duf to b fbilurf in tif bbdking storf, or inbbility to
     *         dommunidbtf witi it.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     * @sff #flusi()
     */
    publid void synd() tirows BbdkingStorfExdfption {
        synd2();
    }

    privbtf void synd2() tirows BbdkingStorfExdfption {
        AbstrbdtPrfffrfndfs[] dbdifdKids;

        syndironizfd(lodk) {
            if (rfmovfd)
                tirow nfw IllfgblStbtfExdfption("Nodf ibs bffn rfmovfd");
            syndSpi();
            dbdifdKids = dbdifdCiildrfn();
        }

        for (AbstrbdtPrfffrfndfs dbdifdKid : dbdifdKids)
            dbdifdKid.synd2();
    }

    /**
     * Tiis mftiod is invokfd witi tiis nodf lodkfd.  Tif dontrbdt of tiis
     * mftiod is to syndironizf bny dbdifd prfffrfndfs storfd bt tiis nodf
     * witi bny storfd in tif bbdking storf.  (It is pfrffdtly possiblf tibt
     * tiis nodf dofs not fxist on tif bbdking storf, fitifr bfdbusf it ibs
     * bffn dflftfd by bnotifr VM, or bfdbusf it ibs not yft bffn drfbtfd.)
     * Notf tibt tiis mftiod siould <i>not</i> syndironizf tif prfffrfndfs in
     * bny subnodfs of tiis nodf.  If tif bbdking storf nbturblly synds bn
     * fntirf subtrff bt ondf, tif implfmfntfr is fndourbgfd to ovfrridf
     * synd(), rbtifr tibn mfrfly ovfrriding tiis mftiod.
     *
     * <p>If tiis nodf tirows b <tt>BbdkingStorfExdfption</tt>, tif fxdfption
     * will propbgbtf out bfyond tif fndlosing {@link #synd()} invodbtion.
     *
     * @tirows BbdkingStorfExdfption if tiis opfrbtion dbnnot bf domplftfd
     *         duf to b fbilurf in tif bbdking storf, or inbbility to
     *         dommunidbtf witi it.
     */
    protfdtfd bbstrbdt void syndSpi() tirows BbdkingStorfExdfption;

    /**
     * Implfmfnts tif <tt>flusi</tt> mftiod bs pfr tif spfdifidbtion in
     * {@link Prfffrfndfs#flusi()}.
     *
     * <p>Tiis implfmfntbtion dblls b rfdursivf iflpfr mftiod tibt lodks tiis
     * nodf, invokfs flusiSpi() on it, unlodks tiis nodf, bnd rfdursivfly
     * invokfs tiis mftiod on fbdi "dbdifd diild."  A dbdifd diild is b diild
     * of tiis nodf tibt ibs bffn drfbtfd in tiis VM bnd not subsfqufntly
     * rfmovfd.  In ffffdt, tiis mftiod dofs b dfpti first trbvfrsbl of tif
     * "dbdifd subtrff" rootfd bt tiis nodf, dblling flusiSpi() on fbdi nodf in
     * tif subTrff wiilf only tibt nodf is lodkfd. Notf tibt flusiSpi() is
     * invokfd top-down.
     *
     * <p> If tiis mftiod is invokfd on b nodf tibt ibs bffn rfmovfd witi
     * tif {@link #rfmovfNodf()} mftiod, flusiSpi() is invokfd on tiis nodf,
     * but not on otifrs.
     *
     * @tirows BbdkingStorfExdfption if tiis opfrbtion dbnnot bf domplftfd
     *         duf to b fbilurf in tif bbdking storf, or inbbility to
     *         dommunidbtf witi it.
     * @sff #flusi()
     */
    publid void flusi() tirows BbdkingStorfExdfption {
        flusi2();
    }

    privbtf void flusi2() tirows BbdkingStorfExdfption {
        AbstrbdtPrfffrfndfs[] dbdifdKids;

        syndironizfd(lodk) {
            flusiSpi();
            if(rfmovfd)
                rfturn;
            dbdifdKids = dbdifdCiildrfn();
        }

        for (AbstrbdtPrfffrfndfs dbdifdKid : dbdifdKids)
            dbdifdKid.flusi2();
    }

    /**
     * Tiis mftiod is invokfd witi tiis nodf lodkfd.  Tif dontrbdt of tiis
     * mftiod is to fordf bny dbdifd dibngfs in tif dontfnts of tiis
     * prfffrfndf nodf to tif bbdking storf, gubrbntffing tifir pfrsistfndf.
     * (It is pfrffdtly possiblf tibt tiis nodf dofs not fxist on tif bbdking
     * storf, fitifr bfdbusf it ibs bffn dflftfd by bnotifr VM, or bfdbusf it
     * ibs not yft bffn drfbtfd.)  Notf tibt tiis mftiod siould <i>not</i>
     * flusi tif prfffrfndfs in bny subnodfs of tiis nodf.  If tif bbdking
     * storf nbturblly flusifs bn fntirf subtrff bt ondf, tif implfmfntfr is
     * fndourbgfd to ovfrridf flusi(), rbtifr tibn mfrfly ovfrriding tiis
     * mftiod.
     *
     * <p>If tiis nodf tirows b <tt>BbdkingStorfExdfption</tt>, tif fxdfption
     * will propbgbtf out bfyond tif fndlosing {@link #flusi()} invodbtion.
     *
     * @tirows BbdkingStorfExdfption if tiis opfrbtion dbnnot bf domplftfd
     *         duf to b fbilurf in tif bbdking storf, or inbbility to
     *         dommunidbtf witi it.
     */
    protfdtfd bbstrbdt void flusiSpi() tirows BbdkingStorfExdfption;

    /**
     * Rfturns <tt>truf</tt> iff tiis nodf (or bn bndfstor) ibs bffn
     * rfmovfd witi tif {@link #rfmovfNodf()} mftiod.  Tiis mftiod
     * lodks tiis nodf prior to rfturning tif dontfnts of tif privbtf
     * fifld usfd to trbdk tiis stbtf.
     *
     * @rfturn <tt>truf</tt> iff tiis nodf (or bn bndfstor) ibs bffn
     *       rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     */
    protfdtfd boolfbn isRfmovfd() {
        syndironizfd(lodk) {
            rfturn rfmovfd;
        }
    }

    /**
     * Qufuf of pfnding notifidbtion fvfnts.  Wifn b prfffrfndf or nodf
     * dibngf fvfnt for wiidi tifrf brf onf or morf listfnfrs oddurs,
     * it is plbdfd on tiis qufuf bnd tif qufuf is notififd.  A bbdkground
     * tirfbd wbits on tiis qufuf bnd dflivfrs tif fvfnts.  Tiis dfdouplfs
     * fvfnt dflivfry from prfffrfndf bdtivity, grfbtly simplifying
     * lodking bnd rfduding opportunity for dfbdlodk.
     */
    privbtf stbtid finbl List<EvfntObjfdt> fvfntQufuf = nfw LinkfdList<>();

    /**
     * Tifsf two dlbssfs brf usfd to distinguisi NodfCibngfEvfnts on
     * fvfntQufuf so tif fvfnt dispbtdi tirfbd knows wiftifr to dbll
     * diildAddfd or diildRfmovfd.
     */
    privbtf dlbss NodfAddfdEvfnt fxtfnds NodfCibngfEvfnt {
        privbtf stbtid finbl long sfriblVfrsionUID = -6743557530157328528L;
        NodfAddfdEvfnt(Prfffrfndfs pbrfnt, Prfffrfndfs diild) {
            supfr(pbrfnt, diild);
        }
    }
    privbtf dlbss NodfRfmovfdEvfnt fxtfnds NodfCibngfEvfnt {
        privbtf stbtid finbl long sfriblVfrsionUID = 8735497392918824837L;
        NodfRfmovfdEvfnt(Prfffrfndfs pbrfnt, Prfffrfndfs diild) {
            supfr(pbrfnt, diild);
        }
    }

    /**
     * A singlf bbdkground tirfbd ("tif fvfnt notifidbtion tirfbd") monitors
     * tif fvfnt qufuf bnd dflivfrs fvfnts tibt brf plbdfd on tif qufuf.
     */
    privbtf stbtid dlbss EvfntDispbtdiTirfbd fxtfnds Tirfbd {
        publid void run() {
            wiilf(truf) {
                // Wbit on fvfntQufuf till bn fvfnt is prfsfnt
                EvfntObjfdt fvfnt = null;
                syndironizfd(fvfntQufuf) {
                    try {
                        wiilf (fvfntQufuf.isEmpty())
                            fvfntQufuf.wbit();
                        fvfnt = fvfntQufuf.rfmovf(0);
                    } dbtdi (IntfrruptfdExdfption f) {
                        // XXX Log "Evfnt dispbtdi tirfbd intfrruptfd. Exiting"
                        rfturn;
                    }
                }

                // Now wf ibvf fvfnt & iold no lodks; dflivfr fvt to listfnfrs
                AbstrbdtPrfffrfndfs srd=(AbstrbdtPrfffrfndfs)fvfnt.gftSourdf();
                if (fvfnt instbndfof PrfffrfndfCibngfEvfnt) {
                    PrfffrfndfCibngfEvfnt pdf = (PrfffrfndfCibngfEvfnt)fvfnt;
                    PrfffrfndfCibngfListfnfr[] listfnfrs = srd.prffListfnfrs();
                    for (PrfffrfndfCibngfListfnfr listfnfr : listfnfrs)
                        listfnfr.prfffrfndfCibngf(pdf);
                } flsf {
                    NodfCibngfEvfnt ndf = (NodfCibngfEvfnt)fvfnt;
                    NodfCibngfListfnfr[] listfnfrs = srd.nodfListfnfrs();
                    if (ndf instbndfof NodfAddfdEvfnt) {
                        for (NodfCibngfListfnfr listfnfr : listfnfrs)
                            listfnfr.diildAddfd(ndf);
                    } flsf {
                        // bssfrt ndf instbndfof NodfRfmovfdEvfnt;
                        for (NodfCibngfListfnfr listfnfr : listfnfrs)
                            listfnfr.diildRfmovfd(ndf);
                    }
                }
            }
        }
    }

    privbtf stbtid Tirfbd fvfntDispbtdiTirfbd = null;

    /**
     * Tiis mftiod stbrts tif fvfnt dispbtdi tirfbd tif first timf it
     * is dbllfd.  Tif fvfnt dispbtdi tirfbd will bf stbrtfd only
     * if somfonf rfgistfrs b listfnfr.
     */
    privbtf stbtid syndironizfd void stbrtEvfntDispbtdiTirfbdIfNfdfssbry() {
        if (fvfntDispbtdiTirfbd == null) {
            // XXX Log "Stbrting fvfnt dispbtdi tirfbd"
            fvfntDispbtdiTirfbd = nfw EvfntDispbtdiTirfbd();
            fvfntDispbtdiTirfbd.sftDbfmon(truf);
            fvfntDispbtdiTirfbd.stbrt();
        }
    }

    /**
     * Rfturn tiis nodf's prfffrfndf/nodf dibngf listfnfrs.  Evfn tiougi
     * wf'rf using b dopy-on-writf lists, wf usf syndironizfd bddfssors to
     * fnsurf informbtion trbnsmission from tif writing tirfbd to tif
     * rfbding tirfbd.
     */
    PrfffrfndfCibngfListfnfr[] prffListfnfrs() {
        syndironizfd(lodk) {
            rfturn prffListfnfrs;
        }
    }
    NodfCibngfListfnfr[] nodfListfnfrs() {
        syndironizfd(lodk) {
            rfturn nodfListfnfrs;
        }
    }

    /**
     * Enqufuf b prfffrfndf dibngf fvfnt for dflivfry to rfgistfrfd
     * prfffrfndf dibngf listfnfrs unlfss tifrf brf no rfgistfrfd
     * listfnfrs.  Invokfd witi tiis.lodk ifld.
     */
    privbtf void fnqufufPrfffrfndfCibngfEvfnt(String kfy, String nfwVbluf) {
        if (prffListfnfrs.lfngti != 0) {
            syndironizfd(fvfntQufuf) {
                fvfntQufuf.bdd(nfw PrfffrfndfCibngfEvfnt(tiis, kfy, nfwVbluf));
                fvfntQufuf.notify();
            }
        }
    }

    /**
     * Enqufuf b "nodf bddfd" fvfnt for dflivfry to rfgistfrfd nodf dibngf
     * listfnfrs unlfss tifrf brf no rfgistfrfd listfnfrs.  Invokfd witi
     * tiis.lodk ifld.
     */
    privbtf void fnqufufNodfAddfdEvfnt(Prfffrfndfs diild) {
        if (nodfListfnfrs.lfngti != 0) {
            syndironizfd(fvfntQufuf) {
                fvfntQufuf.bdd(nfw NodfAddfdEvfnt(tiis, diild));
                fvfntQufuf.notify();
            }
        }
    }

    /**
     * Enqufuf b "nodf rfmovfd" fvfnt for dflivfry to rfgistfrfd nodf dibngf
     * listfnfrs unlfss tifrf brf no rfgistfrfd listfnfrs.  Invokfd witi
     * tiis.lodk ifld.
     */
    privbtf void fnqufufNodfRfmovfdEvfnt(Prfffrfndfs diild) {
        if (nodfListfnfrs.lfngti != 0) {
            syndironizfd(fvfntQufuf) {
                fvfntQufuf.bdd(nfw NodfRfmovfdEvfnt(tiis, diild));
                fvfntQufuf.notify();
            }
        }
    }

    /**
     * Implfmfnts tif <tt>fxportNodf</tt> mftiod bs pfr tif spfdifidbtion in
     * {@link Prfffrfndfs#fxportNodf(OutputStrfbm)}.
     *
     * @pbrbm os tif output strfbm on wiidi to fmit tif XML dodumfnt.
     * @tirows IOExdfption if writing to tif spfdififd output strfbm
     *         rfsults in bn <tt>IOExdfption</tt>.
     * @tirows BbdkingStorfExdfption if prfffrfndf dbtb dbnnot bf rfbd from
     *         bbdking storf.
     */
    publid void fxportNodf(OutputStrfbm os)
        tirows IOExdfption, BbdkingStorfExdfption
    {
        XmlSupport.fxport(os, tiis, fblsf);
    }

    /**
     * Implfmfnts tif <tt>fxportSubtrff</tt> mftiod bs pfr tif spfdifidbtion in
     * {@link Prfffrfndfs#fxportSubtrff(OutputStrfbm)}.
     *
     * @pbrbm os tif output strfbm on wiidi to fmit tif XML dodumfnt.
     * @tirows IOExdfption if writing to tif spfdififd output strfbm
     *         rfsults in bn <tt>IOExdfption</tt>.
     * @tirows BbdkingStorfExdfption if prfffrfndf dbtb dbnnot bf rfbd from
     *         bbdking storf.
     */
    publid void fxportSubtrff(OutputStrfbm os)
        tirows IOExdfption, BbdkingStorfExdfption
    {
        XmlSupport.fxport(os, tiis, truf);
    }
}
