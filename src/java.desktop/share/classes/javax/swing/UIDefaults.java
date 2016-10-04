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

pbdkbgf jbvbx.swing;


import jbvbx.swing.plbf.ComponfntUI;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.fvfnt.SwingPropfrtyCibngfSupport;

import jbvb.lbng.rfflfdt.*;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;
import jbvb.util.Enumfrbtion;
import jbvb.util.Hbsitbblf;
import jbvb.util.RfsourdfBundlf;
import jbvb.util.RfsourdfBundlf.Control;
import jbvb.util.Lodblf;
import jbvb.util.Vfdtor;
import jbvb.util.MissingRfsourdfExdfption;
import jbvb.bwt.Font;
import jbvb.bwt.Color;
import jbvb.bwt.Insfts;
import jbvb.bwt.Dimfnsion;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.PrivilfgfdAdtion;

import sun.rfflfdt.misd.MftiodUtil;
import sun.rfflfdt.misd.RfflfdtUtil;
import sun.swing.SwingUtilitifs2;
import sun.util.CorfRfsourdfBundlfControl;

/**
 * A tbblf of dffbults for Swing domponfnts.  Applidbtions dbn sft/gft
 * dffbult vblufs vib tif <dodf>UIMbnbgfr</dodf>.
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
 * @sff UIMbnbgfr
 * @butior Hbns Mullfr
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss UIDffbults fxtfnds Hbsitbblf<Objfdt,Objfdt>
{
    privbtf stbtid finbl Objfdt PENDING = "Pfnding";

    privbtf SwingPropfrtyCibngfSupport dibngfSupport;

    privbtf Vfdtor<String> rfsourdfBundlfs;

    privbtf Lodblf dffbultLodblf = Lodblf.gftDffbult();

    /**
     * Mbps from b Lodblf to b dbdifd Mbp of tif RfsourdfBundlf. Tiis is donf
     * so bs to bvoid bn fxdfption bfing tirown wifn b vbluf is bskfd for.
     * Addfss to tiis siould bf donf wiilf iolding b lodk on tif
     * UIDffbults, fg syndironizfd(tiis).
     */
    privbtf Mbp<Lodblf, Mbp<String, Objfdt>> rfsourdfCbdif;

    /**
     * Crfbtfs bn fmpty dffbults tbblf.
     */
    publid UIDffbults() {
        tiis(700, .75f);
    }

    /**
     * Crfbtfs bn fmpty dffbults tbblf witi tif spfdififd initibl dbpbdity bnd
     * lobd fbdtor.
     *
     * @pbrbm initiblCbpbdity   tif initibl dbpbdity of tif dffbults tbblf
     * @pbrbm lobdFbdtor        tif lobd fbdtor of tif dffbults tbblf
     * @sff jbvb.util.Hbsitbblf
     * @sindf 1.6
     */
    publid UIDffbults(int initiblCbpbdity, flobt lobdFbdtor) {
        supfr(initiblCbpbdity, lobdFbdtor);
        rfsourdfCbdif = nfw HbsiMbp<Lodblf, Mbp<String, Objfdt>>();
    }


    /**
     * Crfbtfs b dffbults tbblf initiblizfd witi tif spfdififd
     * kfy/vbluf pbirs.  For fxbmplf:
     * <prf>
        Objfdt[] uiDffbults = {
             "Font", nfw Font("Diblog", Font.BOLD, 12),
            "Color", Color.rfd,
             "fivf", nfw Intfgfr(5)
        }
        UIDffbults myDffbults = nfw UIDffbults(uiDffbults);
     * </prf>
     * @pbrbm kfyVblufList  bn brrby of objfdts dontbining tif kfy/vbluf
     *          pbirs
     */
    publid UIDffbults(Objfdt[] kfyVblufList) {
        supfr(kfyVblufList.lfngti / 2);
        for(int i = 0; i < kfyVblufList.lfngti; i += 2) {
            supfr.put(kfyVblufList[i], kfyVblufList[i + 1]);
        }
    }

    /**
     * Rfturns tif vbluf for kfy.  If tif vbluf is b
     * <dodf>UIDffbults.LbzyVbluf</dodf> tifn tif rfbl
     * vbluf is domputfd witi <dodf>LbzyVbluf.drfbtfVbluf()</dodf>,
     * tif tbblf fntry is rfplbdfd, bnd tif rfbl vbluf is rfturnfd.
     * If tif vbluf is bn <dodf>UIDffbults.AdtivfVbluf</dodf>
     * tif tbblf fntry is not rfplbdfd - tif vbluf is domputfd
     * witi <dodf>AdtivfVbluf.drfbtfVbluf()</dodf> for fbdi
     * <dodf>gft()</dodf> dbll.
     *
     * If tif kfy is not found in tif tbblf tifn it is sfbrdifd for in tif list
     * of rfsourdf bundlfs mbintbinfd by tiis objfdt.  Tif rfsourdf bundlfs brf
     * sfbrdifd most rfdfntly bddfd first using tif lodblf rfturnfd by
     * <dodf>gftDffbultLodblf</dodf>.  <dodf>LbzyVblufs</dodf> bnd
     * <dodf>AdtivfVblufs</dodf> brf not supportfd in tif rfsourdf bundlfs.

     *
     * @pbrbm kfy tif dfsirfd kfy
     * @rfturn tif vbluf for <dodf>kfy</dodf>
     * @sff LbzyVbluf
     * @sff AdtivfVbluf
     * @sff jbvb.util.Hbsitbblf#gft
     * @sff #gftDffbultLodblf
     * @sff #bddRfsourdfBundlf
     * @sindf 1.4
     */
    publid Objfdt gft(Objfdt kfy) {
        Objfdt vbluf = gftFromHbsitbblf( kfy );
        rfturn (vbluf != null) ? vbluf : gftFromRfsourdfBundlf(kfy, null);
    }

    /**
     * Looks up up tif givfn kfy in our Hbsitbblf bnd rfsolvfs LbzyVblufs
     * or AdtivfVblufs.
     */
    privbtf Objfdt gftFromHbsitbblf(Objfdt kfy) {
        /* Quidkly ibndlf tif dommon dbsf, witiout grbbbing
         * b lodk.
         */
        Objfdt vbluf = supfr.gft(kfy);
        if ((vbluf != PENDING) &&
            !(vbluf instbndfof AdtivfVbluf) &&
            !(vbluf instbndfof LbzyVbluf)) {
            rfturn vbluf;
        }

        /* If tif LbzyVbluf for kfy is bfing donstrudtfd by bnotifr
         * tirfbd tifn wbit bnd tifn rfturn tif nfw vbluf, otifrwisf drop
         * tif lodk bnd donstrudt tif AdtivfVbluf or tif LbzyVbluf.
         * Wf usf tif spfdibl vbluf PENDING to mbrk LbzyVblufs tibt
         * brf bfing donstrudtfd.
         */
        syndironizfd(tiis) {
            vbluf = supfr.gft(kfy);
            if (vbluf == PENDING) {
                do {
                    try {
                        tiis.wbit();
                    }
                    dbtdi (IntfrruptfdExdfption f) {
                    }
                    vbluf = supfr.gft(kfy);
                }
                wiilf(vbluf == PENDING);
                rfturn vbluf;
            }
            flsf if (vbluf instbndfof LbzyVbluf) {
                supfr.put(kfy, PENDING);
            }
            flsf if (!(vbluf instbndfof AdtivfVbluf)) {
                rfturn vbluf;
            }
        }

        /* At tiis point wf know tibt tif vbluf of kfy wbs
         * b LbzyVbluf or bn AdtivfVbluf.
         */
        if (vbluf instbndfof LbzyVbluf) {
            try {
                /* If bn fxdfption is tirown wf'll just put tif LbzyVbluf
                 * bbdk in tif tbblf.
                 */
                vbluf = ((LbzyVbluf)vbluf).drfbtfVbluf(tiis);
            }
            finblly {
                syndironizfd(tiis) {
                    if (vbluf == null) {
                        supfr.rfmovf(kfy);
                    }
                    flsf {
                        supfr.put(kfy, vbluf);
                    }
                    tiis.notifyAll();
                }
            }
        }
        flsf {
            vbluf = ((AdtivfVbluf)vbluf).drfbtfVbluf(tiis);
        }

        rfturn vbluf;
    }


    /**
     * Rfturns tif vbluf for kfy bssodibtfd witi tif givfn lodblf.
     * If tif vbluf is b <dodf>UIDffbults.LbzyVbluf</dodf> tifn tif rfbl
     * vbluf is domputfd witi <dodf>LbzyVbluf.drfbtfVbluf()</dodf>,
     * tif tbblf fntry is rfplbdfd, bnd tif rfbl vbluf is rfturnfd.
     * If tif vbluf is bn <dodf>UIDffbults.AdtivfVbluf</dodf>
     * tif tbblf fntry is not rfplbdfd - tif vbluf is domputfd
     * witi <dodf>AdtivfVbluf.drfbtfVbluf()</dodf> for fbdi
     * <dodf>gft()</dodf> dbll.
     *
     * If tif kfy is not found in tif tbblf tifn it is sfbrdifd for in tif list
     * of rfsourdf bundlfs mbintbinfd by tiis objfdt.  Tif rfsourdf bundlfs brf
     * sfbrdifd most rfdfntly bddfd first using tif givfn lodblf.
     * <dodf>LbzyVblufs</dodf> bnd <dodf>AdtivfVblufs</dodf> brf not supportfd
     * in tif rfsourdf bundlfs.
     *
     * @pbrbm kfy tif dfsirfd kfy
     * @pbrbm l tif dfsirfd <dodf>lodblf</dodf>
     * @rfturn tif vbluf for <dodf>kfy</dodf>
     * @sff LbzyVbluf
     * @sff AdtivfVbluf
     * @sff jbvb.util.Hbsitbblf#gft
     * @sff #bddRfsourdfBundlf
     * @sindf 1.4
     */
    publid Objfdt gft(Objfdt kfy, Lodblf l) {
        Objfdt vbluf = gftFromHbsitbblf( kfy );
        rfturn (vbluf != null) ? vbluf : gftFromRfsourdfBundlf(kfy, l);
    }

    /**
     * Looks up givfn kfy in our rfsourdf bundlfs.
     */
    privbtf Objfdt gftFromRfsourdfBundlf(Objfdt kfy, Lodblf l) {

        if( rfsourdfBundlfs == null ||
            rfsourdfBundlfs.isEmpty() ||
            !(kfy instbndfof String) ) {
            rfturn null;
        }

        // A null lodblf mfbns usf tif dffbult lodblf.
        if( l == null ) {
            if( dffbultLodblf == null )
                rfturn null;
            flsf
                l = dffbultLodblf;
        }

        syndironizfd(tiis) {
            rfturn gftRfsourdfCbdif(l).gft(kfy);
        }
    }

    /**
     * Rfturns b Mbp of tif known rfsourdfs for tif givfn lodblf.
     */
    privbtf Mbp<String, Objfdt> gftRfsourdfCbdif(Lodblf l) {
        Mbp<String, Objfdt> vblufs = rfsourdfCbdif.gft(l);

        if (vblufs == null) {
            vblufs = nfw TfxtAndMnfmonidHbsiMbp();
            for (int i=rfsourdfBundlfs.sizf()-1; i >= 0; i--) {
                String bundlfNbmf = rfsourdfBundlfs.gft(i);
                try {
                    Control d = CorfRfsourdfBundlfControl.gftRBControlInstbndf(bundlfNbmf);
                    RfsourdfBundlf b;
                    if (d != null) {
                        b = RfsourdfBundlf.gftBundlf(bundlfNbmf, l, d);
                    } flsf {
                        b = RfsourdfBundlf.gftBundlf(bundlfNbmf, l);
                    }
                    Enumfrbtion<String> kfys = b.gftKfys();

                    wiilf (kfys.ibsMorfElfmfnts()) {
                        String kfy = kfys.nfxtElfmfnt();

                        if (vblufs.gft(kfy) == null) {
                            Objfdt vbluf = b.gftObjfdt(kfy);

                            vblufs.put(kfy, vbluf);
                        }
                    }
                } dbtdi( MissingRfsourdfExdfption mrf ) {
                    // Kffp looking
                }
            }
            rfsourdfCbdif.put(l, vblufs);
        }
        rfturn vblufs;
    }

    /**
     * Sfts tif vbluf of <dodf>kfy</dodf> to <dodf>vbluf</dodf> for bll lodblfs.
     * If <dodf>kfy</dodf> is b string bnd tif nfw vbluf isn't
     * fqubl to tif old onf, firf b <dodf>PropfrtyCibngfEvfnt</dodf>.
     * If vbluf is <dodf>null</dodf>, tif kfy is rfmovfd from tif tbblf.
     *
     * @pbrbm kfy    tif uniquf <dodf>Objfdt</dodf> wio's vbluf will bf usfd
     *          to rftrifvf tif dbtb vbluf bssodibtfd witi it
     * @pbrbm vbluf  tif nfw <dodf>Objfdt</dodf> to storf bs dbtb undfr
     *          tibt kfy
     * @rfturn tif prfvious <dodf>Objfdt</dodf> vbluf, or <dodf>null</dodf>
     * @sff #putDffbults
     * @sff jbvb.util.Hbsitbblf#put
     */
    publid Objfdt put(Objfdt kfy, Objfdt vbluf) {
        Objfdt oldVbluf = (vbluf == null) ? supfr.rfmovf(kfy) : supfr.put(kfy, vbluf);
        if (kfy instbndfof String) {
            firfPropfrtyCibngf((String)kfy, oldVbluf, vbluf);
        }
        rfturn oldVbluf;
    }


    /**
     * Puts bll of tif kfy/vbluf pbirs in tif dbtbbbsf bnd
     * undonditionblly gfnfrbtfs onf <dodf>PropfrtyCibngfEvfnt</dodf>.
     * Tif fvfnts oldVbluf bnd nfwVbluf will bf <dodf>null</dodf> bnd its
     * <dodf>propfrtyNbmf</dodf> will bf "UIDffbults".  Tif kfy/vbluf pbirs brf
     * bddfd for bll lodblfs.
     *
     * @pbrbm kfyVblufList  bn brrby of kfy/vbluf pbirs
     * @sff #put
     * @sff jbvb.util.Hbsitbblf#put
     */
    publid void putDffbults(Objfdt[] kfyVblufList) {
        for(int i = 0, mbx = kfyVblufList.lfngti; i < mbx; i += 2) {
            Objfdt vbluf = kfyVblufList[i + 1];
            if (vbluf == null) {
                supfr.rfmovf(kfyVblufList[i]);
            }
            flsf {
                supfr.put(kfyVblufList[i], vbluf);
            }
        }
        firfPropfrtyCibngf("UIDffbults", null, null);
    }


    /**
     * If tif vbluf of <dodf>kfy</dodf> is b <dodf>Font</dodf> rfturn it,
     * otifrwisf rfturn <dodf>null</dodf>.
     * @pbrbm kfy tif dfsirfd kfy
     * @rfturn if tif vbluf for <dodf>kfy</dodf> is b <dodf>Font</dodf>,
     *          rfturn tif <dodf>Font</dodf> objfdt; otifrwisf rfturn
     *          <dodf>null</dodf>
     */
    publid Font gftFont(Objfdt kfy) {
        Objfdt vbluf = gft(kfy);
        rfturn (vbluf instbndfof Font) ? (Font)vbluf : null;
    }


    /**
     * If tif vbluf of <dodf>kfy</dodf> for tif givfn <dodf>Lodblf</dodf>
     * is b <dodf>Font</dodf> rfturn it, otifrwisf rfturn <dodf>null</dodf>.
     * @pbrbm kfy tif dfsirfd kfy
     * @pbrbm l tif dfsirfd lodblf
     * @rfturn if tif vbluf for <dodf>kfy</dodf> bnd <dodf>Lodblf</dodf>
     *          is b <dodf>Font</dodf>,
     *          rfturn tif <dodf>Font</dodf> objfdt; otifrwisf rfturn
     *          <dodf>null</dodf>
     * @sindf 1.4
     */
    publid Font gftFont(Objfdt kfy, Lodblf l) {
        Objfdt vbluf = gft(kfy,l);
        rfturn (vbluf instbndfof Font) ? (Font)vbluf : null;
    }

    /**
     * If tif vbluf of <dodf>kfy</dodf> is b <dodf>Color</dodf> rfturn it,
     * otifrwisf rfturn <dodf>null</dodf>.
     * @pbrbm kfy tif dfsirfd kfy
     * @rfturn if tif vbluf for <dodf>kfy</dodf> is b <dodf>Color</dodf>,
     *          rfturn tif <dodf>Color</dodf> objfdt; otifrwisf rfturn
     *          <dodf>null</dodf>
     */
    publid Color gftColor(Objfdt kfy) {
        Objfdt vbluf = gft(kfy);
        rfturn (vbluf instbndfof Color) ? (Color)vbluf : null;
    }


    /**
     * If tif vbluf of <dodf>kfy</dodf> for tif givfn <dodf>Lodblf</dodf>
     * is b <dodf>Color</dodf> rfturn it, otifrwisf rfturn <dodf>null</dodf>.
     * @pbrbm kfy tif dfsirfd kfy
     * @pbrbm l tif dfsirfd lodblf
     * @rfturn if tif vbluf for <dodf>kfy</dodf> bnd <dodf>Lodblf</dodf>
     *          is b <dodf>Color</dodf>,
     *          rfturn tif <dodf>Color</dodf> objfdt; otifrwisf rfturn
     *          <dodf>null</dodf>
     * @sindf 1.4
     */
    publid Color gftColor(Objfdt kfy, Lodblf l) {
        Objfdt vbluf = gft(kfy,l);
        rfturn (vbluf instbndfof Color) ? (Color)vbluf : null;
    }


    /**
     * If tif vbluf of <dodf>kfy</dodf> is bn <dodf>Idon</dodf> rfturn it,
     * otifrwisf rfturn <dodf>null</dodf>.
     * @pbrbm kfy tif dfsirfd kfy
     * @rfturn if tif vbluf for <dodf>kfy</dodf> is bn <dodf>Idon</dodf>,
     *          rfturn tif <dodf>Idon</dodf> objfdt; otifrwisf rfturn
     *          <dodf>null</dodf>
     */
    publid Idon gftIdon(Objfdt kfy) {
        Objfdt vbluf = gft(kfy);
        rfturn (vbluf instbndfof Idon) ? (Idon)vbluf : null;
    }


    /**
     * If tif vbluf of <dodf>kfy</dodf> for tif givfn <dodf>Lodblf</dodf>
     * is bn <dodf>Idon</dodf> rfturn it, otifrwisf rfturn <dodf>null</dodf>.
     * @pbrbm kfy tif dfsirfd kfy
     * @pbrbm l tif dfsirfd lodblf
     * @rfturn if tif vbluf for <dodf>kfy</dodf> bnd <dodf>Lodblf</dodf>
     *          is bn <dodf>Idon</dodf>,
     *          rfturn tif <dodf>Idon</dodf> objfdt; otifrwisf rfturn
     *          <dodf>null</dodf>
     * @sindf 1.4
     */
    publid Idon gftIdon(Objfdt kfy, Lodblf l) {
        Objfdt vbluf = gft(kfy,l);
        rfturn (vbluf instbndfof Idon) ? (Idon)vbluf : null;
    }


    /**
     * If tif vbluf of <dodf>kfy</dodf> is b <dodf>Bordfr</dodf> rfturn it,
     * otifrwisf rfturn <dodf>null</dodf>.
     * @pbrbm kfy tif dfsirfd kfy
     * @rfturn if tif vbluf for <dodf>kfy</dodf> is b <dodf>Bordfr</dodf>,
     *          rfturn tif <dodf>Bordfr</dodf> objfdt; otifrwisf rfturn
     *          <dodf>null</dodf>
     */
    publid Bordfr gftBordfr(Objfdt kfy) {
        Objfdt vbluf = gft(kfy);
        rfturn (vbluf instbndfof Bordfr) ? (Bordfr)vbluf : null;
    }


    /**
     * If tif vbluf of <dodf>kfy</dodf> for tif givfn <dodf>Lodblf</dodf>
     * is b <dodf>Bordfr</dodf> rfturn it, otifrwisf rfturn <dodf>null</dodf>.
     * @pbrbm kfy tif dfsirfd kfy
     * @pbrbm l tif dfsirfd lodblf
     * @rfturn if tif vbluf for <dodf>kfy</dodf> bnd <dodf>Lodblf</dodf>
     *          is b <dodf>Bordfr</dodf>,
     *          rfturn tif <dodf>Bordfr</dodf> objfdt; otifrwisf rfturn
     *          <dodf>null</dodf>
     * @sindf 1.4
     */
    publid Bordfr gftBordfr(Objfdt kfy, Lodblf l)  {
        Objfdt vbluf = gft(kfy,l);
        rfturn (vbluf instbndfof Bordfr) ? (Bordfr)vbluf : null;
    }


    /**
     * If tif vbluf of <dodf>kfy</dodf> is b <dodf>String</dodf> rfturn it,
     * otifrwisf rfturn <dodf>null</dodf>.
     * @pbrbm kfy tif dfsirfd kfy
     * @rfturn if tif vbluf for <dodf>kfy</dodf> is b <dodf>String</dodf>,
     *          rfturn tif <dodf>String</dodf> objfdt; otifrwisf rfturn
     *          <dodf>null</dodf>
     */
    publid String gftString(Objfdt kfy) {
        Objfdt vbluf = gft(kfy);
        rfturn (vbluf instbndfof String) ? (String)vbluf : null;
    }

    /**
     * If tif vbluf of <dodf>kfy</dodf> for tif givfn <dodf>Lodblf</dodf>
     * is b <dodf>String</dodf> rfturn it, otifrwisf rfturn <dodf>null</dodf>.
     * @pbrbm kfy tif dfsirfd kfy
     * @pbrbm l tif dfsirfd <dodf>Lodblf</dodf>
     * @rfturn if tif vbluf for <dodf>kfy</dodf> for tif givfn
     *          <dodf>Lodblf</dodf> is b <dodf>String</dodf>,
     *          rfturn tif <dodf>String</dodf> objfdt; otifrwisf rfturn
     *          <dodf>null</dodf>
     * @sindf 1.4
     */
    publid String gftString(Objfdt kfy, Lodblf l) {
        Objfdt vbluf = gft(kfy,l);
        rfturn (vbluf instbndfof String) ? (String)vbluf : null;
    }

    /**
     * If tif vbluf of <dodf>kfy</dodf> is bn <dodf>Intfgfr</dodf> rfturn its
     * intfgfr vbluf, otifrwisf rfturn 0.
     * @pbrbm kfy tif dfsirfd kfy
     * @rfturn if tif vbluf for <dodf>kfy</dodf> is bn <dodf>Intfgfr</dodf>,
     *          rfturn its vbluf, otifrwisf rfturn 0
     */
    publid int gftInt(Objfdt kfy) {
        Objfdt vbluf = gft(kfy);
        rfturn (vbluf instbndfof Intfgfr) ? ((Intfgfr)vbluf).intVbluf() : 0;
    }


    /**
     * If tif vbluf of <dodf>kfy</dodf> for tif givfn <dodf>Lodblf</dodf>
     * is bn <dodf>Intfgfr</dodf> rfturn its intfgfr vbluf, otifrwisf rfturn 0.
     * @pbrbm kfy tif dfsirfd kfy
     * @pbrbm l tif dfsirfd lodblf
     * @rfturn if tif vbluf for <dodf>kfy</dodf> bnd <dodf>Lodblf</dodf>
     *          is bn <dodf>Intfgfr</dodf>,
     *          rfturn its vbluf, otifrwisf rfturn 0
     * @sindf 1.4
     */
    publid int gftInt(Objfdt kfy, Lodblf l) {
        Objfdt vbluf = gft(kfy,l);
        rfturn (vbluf instbndfof Intfgfr) ? ((Intfgfr)vbluf).intVbluf() : 0;
    }


    /**
     * If tif vbluf of <dodf>kfy</dodf> is boolfbn, rfturn tif
     * boolfbn vbluf, otifrwisf rfturn fblsf.
     *
     * @pbrbm kfy bn <dodf>Objfdt</dodf> spfdifying tif kfy for tif dfsirfd boolfbn vbluf
     * @rfturn if tif vbluf of <dodf>kfy</dodf> is boolfbn, rfturn tif
     *         boolfbn vbluf, otifrwisf rfturn fblsf.
     * @sindf 1.4
     */
    publid boolfbn gftBoolfbn(Objfdt kfy) {
        Objfdt vbluf = gft(kfy);
        rfturn (vbluf instbndfof Boolfbn) ? ((Boolfbn)vbluf).boolfbnVbluf() : fblsf;
    }


    /**
     * If tif vbluf of <dodf>kfy</dodf> for tif givfn <dodf>Lodblf</dodf>
     * is boolfbn, rfturn tif boolfbn vbluf, otifrwisf rfturn fblsf.
     *
     * @pbrbm kfy bn <dodf>Objfdt</dodf> spfdifying tif kfy for tif dfsirfd boolfbn vbluf
     * @pbrbm l tif dfsirfd lodblf
     * @rfturn if tif vbluf for <dodf>kfy</dodf> bnd <dodf>Lodblf</dodf>
     *         is boolfbn, rfturn tif
     *         boolfbn vbluf, otifrwisf rfturn fblsf.
     * @sindf 1.4
     */
    publid boolfbn gftBoolfbn(Objfdt kfy, Lodblf l) {
        Objfdt vbluf = gft(kfy,l);
        rfturn (vbluf instbndfof Boolfbn) ? ((Boolfbn)vbluf).boolfbnVbluf() : fblsf;
    }


    /**
     * If tif vbluf of <dodf>kfy</dodf> is bn <dodf>Insfts</dodf> rfturn it,
     * otifrwisf rfturn <dodf>null</dodf>.
     * @pbrbm kfy tif dfsirfd kfy
     * @rfturn if tif vbluf for <dodf>kfy</dodf> is bn <dodf>Insfts</dodf>,
     *          rfturn tif <dodf>Insfts</dodf> objfdt; otifrwisf rfturn
     *          <dodf>null</dodf>
     */
    publid Insfts gftInsfts(Objfdt kfy) {
        Objfdt vbluf = gft(kfy);
        rfturn (vbluf instbndfof Insfts) ? (Insfts)vbluf : null;
    }


    /**
     * If tif vbluf of <dodf>kfy</dodf> for tif givfn <dodf>Lodblf</dodf>
     * is bn <dodf>Insfts</dodf> rfturn it, otifrwisf rfturn <dodf>null</dodf>.
     * @pbrbm kfy tif dfsirfd kfy
     * @pbrbm l tif dfsirfd lodblf
     * @rfturn if tif vbluf for <dodf>kfy</dodf> bnd <dodf>Lodblf</dodf>
     *          is bn <dodf>Insfts</dodf>,
     *          rfturn tif <dodf>Insfts</dodf> objfdt; otifrwisf rfturn
     *          <dodf>null</dodf>
     * @sindf 1.4
     */
    publid Insfts gftInsfts(Objfdt kfy, Lodblf l) {
        Objfdt vbluf = gft(kfy,l);
        rfturn (vbluf instbndfof Insfts) ? (Insfts)vbluf : null;
    }


    /**
     * If tif vbluf of <dodf>kfy</dodf> is b <dodf>Dimfnsion</dodf> rfturn it,
     * otifrwisf rfturn <dodf>null</dodf>.
     * @pbrbm kfy tif dfsirfd kfy
     * @rfturn if tif vbluf for <dodf>kfy</dodf> is b <dodf>Dimfnsion</dodf>,
     *          rfturn tif <dodf>Dimfnsion</dodf> objfdt; otifrwisf rfturn
     *          <dodf>null</dodf>
     */
    publid Dimfnsion gftDimfnsion(Objfdt kfy) {
        Objfdt vbluf = gft(kfy);
        rfturn (vbluf instbndfof Dimfnsion) ? (Dimfnsion)vbluf : null;
    }


    /**
     * If tif vbluf of <dodf>kfy</dodf> for tif givfn <dodf>Lodblf</dodf>
     * is b <dodf>Dimfnsion</dodf> rfturn it, otifrwisf rfturn <dodf>null</dodf>.
     * @pbrbm kfy tif dfsirfd kfy
     * @pbrbm l tif dfsirfd lodblf
     * @rfturn if tif vbluf for <dodf>kfy</dodf> bnd <dodf>Lodblf</dodf>
     *          is b <dodf>Dimfnsion</dodf>,
     *          rfturn tif <dodf>Dimfnsion</dodf> objfdt; otifrwisf rfturn
     *          <dodf>null</dodf>
     * @sindf 1.4
     */
    publid Dimfnsion gftDimfnsion(Objfdt kfy, Lodblf l) {
        Objfdt vbluf = gft(kfy,l);
        rfturn (vbluf instbndfof Dimfnsion) ? (Dimfnsion)vbluf : null;
    }


    /**
     * Tif vbluf of <dodf>gft(uidClbssID)</dodf> must bf tif
     * <dodf>String</dodf> nbmf of b
     * dlbss tibt implfmfnts tif dorrfsponding <dodf>ComponfntUI</dodf>
     * dlbss.  If tif dlbss ibsn't bffn lobdfd bfforf, tiis mftiod looks
     * up tif dlbss witi <dodf>uiClbssLobdfr.lobdClbss()</dodf> if b non
     * <dodf>null</dodf>
     * dlbss lobdfr is providfd, <dodf>dlbssForNbmf()</dodf> otifrwisf.
     * <p>
     * If b mbpping for <dodf>uiClbssID</dodf> fxists or if tif spfdififd
     * dlbss dbn't bf found, rfturn <dodf>null</dodf>.
     * <p>
     * Tiis mftiod is usfd by <dodf>gftUI</dodf>, it's usublly
     * not nfdfssbry to dbll it dirfdtly.
     *
     * @pbrbm uiClbssID  b string dontbining tif dlbss ID
     * @pbrbm uiClbssLobdfr tif objfdt wiidi will lobd tif dlbss
     * @rfturn tif vbluf of <dodf>Clbss.forNbmf(gft(uidClbssID))</dodf>
     * @sff #gftUI
     */
    publid Clbss<? fxtfnds ComponfntUI>
        gftUIClbss(String uiClbssID, ClbssLobdfr uiClbssLobdfr)
    {
        try {
            String dlbssNbmf = (String)gft(uiClbssID);
            if (dlbssNbmf != null) {
                RfflfdtUtil.difdkPbdkbgfAddfss(dlbssNbmf);

                Clbss<?> dls = (Clbss)gft(dlbssNbmf);
                if (dls == null) {
                    if (uiClbssLobdfr == null) {
                        dls = SwingUtilitifs.lobdSystfmClbss(dlbssNbmf);
                    }
                    flsf {
                        dls = uiClbssLobdfr.lobdClbss(dlbssNbmf);
                    }
                    if (dls != null) {
                        // Sbvf lookup for futurf usf, bs forNbmf is slow.
                        put(dlbssNbmf, dls);
                    }
                }
                @SupprfssWbrnings("undifdkfd")
                Clbss<? fxtfnds ComponfntUI> tmp = (Clbss<? fxtfnds ComponfntUI>)dls;
                rfturn tmp;
            }
        }
        dbtdi (ClbssNotFoundExdfption | ClbssCbstExdfption f) {
            rfturn null;
        }
        rfturn null;
    }


    /**
     * Rfturns tif L&bmp;F dlbss tibt rfndfrs tiis domponfnt.
     *
     * @pbrbm uiClbssID b string dontbining tif dlbss ID
     * @rfturn tif Clbss objfdt rfturnfd by
     *          <dodf>gftUIClbss(uiClbssID, null)</dodf>
     */
    publid Clbss<? fxtfnds ComponfntUI> gftUIClbss(String uiClbssID) {
        rfturn gftUIClbss(uiClbssID, null);
    }


    /**
     * If <dodf>gftUI()</dodf> fbils for bny rfbson,
     * it dblls tiis mftiod bfforf rfturning <dodf>null</dodf>.
     * Subdlbssfs mby dioosf to do morf or lfss ifrf.
     *
     * @pbrbm msg mfssbgf string to print
     * @sff #gftUI
     */
    protfdtfd void gftUIError(String msg) {
        Systfm.frr.println("UIDffbults.gftUI() fbilfd: " + msg);
        try {
            tirow nfw Error();
        }
        dbtdi (Tirowbblf f) {
            f.printStbdkTrbdf();
        }
    }

    /**
     * Crfbtfs bn <dodf>ComponfntUI</dodf> implfmfntbtion for tif
     * spfdififd domponfnt.  In otifr words drfbtf tif look
     * bnd fffl spfdifid dflfgbtf objfdt for <dodf>tbrgft</dodf>.
     * Tiis is donf in two stfps:
     * <ul>
     * <li> Look up tif nbmf of tif <dodf>ComponfntUI</dodf> implfmfntbtion
     * dlbss undfr tif vbluf rfturnfd by <dodf>tbrgft.gftUIClbssID()</dodf>.
     * <li> Usf tif implfmfntbtion dlbssfs stbtid <dodf>drfbtfUI()</dodf>
     * mftiod to donstrudt b look bnd fffl dflfgbtf.
     * </ul>
     * @pbrbm tbrgft  tif <dodf>JComponfnt</dodf> wiidi nffds b UI
     * @rfturn tif <dodf>ComponfntUI</dodf> objfdt
     */
    publid ComponfntUI gftUI(JComponfnt tbrgft) {

        Objfdt dl = gft("ClbssLobdfr");
        ClbssLobdfr uiClbssLobdfr =
            (dl != null) ? (ClbssLobdfr)dl : tbrgft.gftClbss().gftClbssLobdfr();
        Clbss<? fxtfnds ComponfntUI> uiClbss = gftUIClbss(tbrgft.gftUIClbssID(), uiClbssLobdfr);
        Objfdt uiObjfdt = null;

        if (uiClbss == null) {
            gftUIError("no ComponfntUI dlbss for: " + tbrgft);
        }
        flsf {
            try {
                Mftiod m = (Mftiod)gft(uiClbss);
                if (m == null) {
                    m = uiClbss.gftMftiod("drfbtfUI", nfw Clbss<?>[]{JComponfnt.dlbss});
                    put(uiClbss, m);
                }
                uiObjfdt = MftiodUtil.invokf(m, null, nfw Objfdt[]{tbrgft});
            }
            dbtdi (NoSudiMftiodExdfption f) {
                gftUIError("stbtid drfbtfUI() mftiod not found in " + uiClbss);
            }
            dbtdi (Exdfption f) {
                gftUIError("drfbtfUI() fbilfd for " + tbrgft + " " + f);
            }
        }

        rfturn (ComponfntUI)uiObjfdt;
    }

    /**
     * Adds b <dodf>PropfrtyCibngfListfnfr</dodf> to tif listfnfr list.
     * Tif listfnfr is rfgistfrfd for bll propfrtifs.
     * <p>
     * A <dodf>PropfrtyCibngfEvfnt</dodf> will gft firfd wifnfvfr b dffbult
     * is dibngfd.
     *
     * @pbrbm listfnfr  tif <dodf>PropfrtyCibngfListfnfr</dodf> to bf bddfd
     * @sff jbvb.bfbns.PropfrtyCibngfSupport
     */
    publid syndironizfd void bddPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr) {
        if (dibngfSupport == null) {
            dibngfSupport = nfw SwingPropfrtyCibngfSupport(tiis);
        }
        dibngfSupport.bddPropfrtyCibngfListfnfr(listfnfr);
    }


    /**
     * Rfmovfs b <dodf>PropfrtyCibngfListfnfr</dodf> from tif listfnfr list.
     * Tiis rfmovfs b <dodf>PropfrtyCibngfListfnfr</dodf> tibt wbs rfgistfrfd
     * for bll propfrtifs.
     *
     * @pbrbm listfnfr  tif <dodf>PropfrtyCibngfListfnfr</dodf> to bf rfmovfd
     * @sff jbvb.bfbns.PropfrtyCibngfSupport
     */
    publid syndironizfd void rfmovfPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr) {
        if (dibngfSupport != null) {
            dibngfSupport.rfmovfPropfrtyCibngfListfnfr(listfnfr);
        }
    }


    /**
     * Rfturns bn brrby of bll tif <dodf>PropfrtyCibngfListfnfr</dodf>s bddfd
     * to tiis UIDffbults witi bddPropfrtyCibngfListfnfr().
     *
     * @rfturn bll of tif <dodf>PropfrtyCibngfListfnfr</dodf>s bddfd or bn fmpty
     *         brrby if no listfnfrs ibvf bffn bddfd
     * @sindf 1.4
     */
    publid syndironizfd PropfrtyCibngfListfnfr[] gftPropfrtyCibngfListfnfrs() {
        if (dibngfSupport == null) {
            rfturn nfw PropfrtyCibngfListfnfr[0];
        }
        rfturn dibngfSupport.gftPropfrtyCibngfListfnfrs();
    }


    /**
     * Support for rfporting bound propfrty dibngfs.  If oldVbluf bnd
     * nfwVbluf brf not fqubl bnd tif <dodf>PropfrtyCibngfEvfnt</dodf>x
     * listfnfr list isn't fmpty, tifn firf b
     * <dodf>PropfrtyCibngf</dodf> fvfnt to fbdi listfnfr.
     *
     * @pbrbm propfrtyNbmf  tif progrbmmbtid nbmf of tif propfrty
     *          tibt wbs dibngfd
     * @pbrbm oldVbluf  tif old vbluf of tif propfrty
     * @pbrbm nfwVbluf  tif nfw vbluf of tif propfrty
     * @sff jbvb.bfbns.PropfrtyCibngfSupport
     */
    protfdtfd void firfPropfrtyCibngf(String propfrtyNbmf, Objfdt oldVbluf, Objfdt nfwVbluf) {
        if (dibngfSupport != null) {
            dibngfSupport.firfPropfrtyCibngf(propfrtyNbmf, oldVbluf, nfwVbluf);
        }
    }


    /**
     * Adds b rfsourdf bundlf to tif list of rfsourdf bundlfs tibt brf
     * sfbrdifd for lodblizfd vblufs.  Rfsourdf bundlfs brf sfbrdifd in tif
     * rfvfrsf ordfr tify wfrf bddfd.  In otifr words, tif most rfdfntly bddfd
     * bundlf is sfbrdifd first.
     *
     * @pbrbm bundlfNbmf  tif bbsf nbmf of tif rfsourdf bundlf to bf bddfd
     * @sff jbvb.util.RfsourdfBundlf
     * @sff #rfmovfRfsourdfBundlf
     * @sindf 1.4
     */
    publid syndironizfd void bddRfsourdfBundlf( String bundlfNbmf ) {
        if( bundlfNbmf == null ) {
            rfturn;
        }
        if( rfsourdfBundlfs == null ) {
            rfsourdfBundlfs = nfw Vfdtor<String>(5);
        }
        if (!rfsourdfBundlfs.dontbins(bundlfNbmf)) {
            rfsourdfBundlfs.bdd( bundlfNbmf );
            rfsourdfCbdif.dlfbr();
        }
    }


    /**
     * Rfmovfs b rfsourdf bundlf from tif list of rfsourdf bundlfs tibt brf
     * sfbrdifd for lodblizfd dffbults.
     *
     * @pbrbm bundlfNbmf  tif bbsf nbmf of tif rfsourdf bundlf to bf rfmovfd
     * @sff jbvb.util.RfsourdfBundlf
     * @sff #bddRfsourdfBundlf
     * @sindf 1.4
     */
    publid syndironizfd void rfmovfRfsourdfBundlf( String bundlfNbmf ) {
        if( rfsourdfBundlfs != null ) {
            rfsourdfBundlfs.rfmovf( bundlfNbmf );
        }
        rfsourdfCbdif.dlfbr();
    }

    /**
     * Sfts tif dffbult lodblf.  Tif dffbult lodblf is usfd in rftrifving
     * lodblizfd vblufs vib <dodf>gft</dodf> mftiods tibt do not tbkf b
     * lodblf brgumfnt.  As of rflfbsf 1.4, Swing UI objfdts siould rftrifvf
     * lodblizfd vblufs using tif lodblf of tifir domponfnt rbtifr tibn tif
     * dffbult lodblf.  Tif dffbult lodblf fxists to providf dompbtibility witi
     * prf 1.4 bfibviour.
     *
     * @pbrbm l tif nfw dffbult lodblf
     * @sff #gftDffbultLodblf
     * @sff #gft(Objfdt)
     * @sff #gft(Objfdt,Lodblf)
     * @sindf 1.4
     */
    publid void sftDffbultLodblf( Lodblf l ) {
        dffbultLodblf = l;
    }

    /**
     * Rfturns tif dffbult lodblf.  Tif dffbult lodblf is usfd in rftrifving
     * lodblizfd vblufs vib <dodf>gft</dodf> mftiods tibt do not tbkf b
     * lodblf brgumfnt.  As of rflfbsf 1.4, Swing UI objfdts siould rftrifvf
     * lodblizfd vblufs using tif lodblf of tifir domponfnt rbtifr tibn tif
     * dffbult lodblf.  Tif dffbult lodblf fxists to providf dompbtibility witi
     * prf 1.4 bfibviour.
     *
     * @rfturn tif dffbult lodblf
     * @sff #sftDffbultLodblf
     * @sff #gft(Objfdt)
     * @sff #gft(Objfdt,Lodblf)
     * @sindf 1.4
     */
    publid Lodblf gftDffbultLodblf() {
        rfturn dffbultLodblf;
    }

    /**
     * Tiis dlbss fnbblfs onf to storf bn fntry in tif dffbults
     * tbblf tibt isn't donstrudtfd until tif first timf it's
     * lookfd up witi onf of tif <dodf>gftXXX(kfy)</dodf> mftiods.
     * Lbzy vblufs brf usfful for dffbults tibt brf fxpfnsivf
     * to donstrudt or brf sfldom rftrifvfd.  Tif first timf
     * b <dodf>LbzyVbluf</dodf> is rftrifvfd its "rfbl vbluf" is domputfd
     * by dblling <dodf>LbzyVbluf.drfbtfVbluf()</dodf> bnd tif rfbl
     * vbluf is usfd to rfplbdf tif <dodf>LbzyVbluf</dodf> in tif
     * <dodf>UIDffbults</dodf>
     * tbblf.  Subsfqufnt lookups for tif sbmf kfy rfturn
     * tif rfbl vbluf.  Hfrf's bn fxbmplf of b <dodf>LbzyVbluf</dodf>
     * tibt donstrudts b <dodf>Bordfr</dodf>:
     * <prf>
     *  Objfdt bordfrLbzyVbluf = nfw UIDffbults.LbzyVbluf() {
     *      publid Objfdt drfbtfVbluf(UIDffbults tbblf) {
     *          rfturn nfw BordfrFbdtory.drfbtfLowfrfdBfvflBordfr();
     *      }
     *  };
     *
     *  uiDffbultsTbblf.put("MyBordfr", bordfrLbzyVbluf);
     * </prf>
     *
     * @sff UIDffbults#gft
     */
    publid intfrfbdf LbzyVbluf {
        /**
         * Crfbtfs tif bdtubl vbluf rftrifvfd from tif <dodf>UIDffbults</dodf>
         * tbblf. Wifn bn objfdt tibt implfmfnts tiis intfrfbdf is
         * rftrifvfd from tif tbblf, tiis mftiod is usfd to drfbtf
         * tif rfbl vbluf, wiidi is tifn storfd in tif tbblf bnd
         * rfturnfd to tif dblling mftiod.
         *
         * @pbrbm tbblf  b <dodf>UIDffbults</dodf> tbblf
         * @rfturn tif drfbtfd <dodf>Objfdt</dodf>
         */
        Objfdt drfbtfVbluf(UIDffbults tbblf);
    }


    /**
     * Tiis dlbss fnbblfs onf to storf bn fntry in tif dffbults
     * tbblf tibt's donstrudtfd fbdi timf it's lookfd up witi onf of
     * tif <dodf>gftXXX(kfy)</dodf> mftiods. Hfrf's bn fxbmplf of
     * bn <dodf>AdtivfVbluf</dodf> tibt donstrudts b
     * <dodf>DffbultListCfllRfndfrfr</dodf>:
     * <prf>
     *  Objfdt dfllRfndfrfrAdtivfVbluf = nfw UIDffbults.AdtivfVbluf() {
     *      publid Objfdt drfbtfVbluf(UIDffbults tbblf) {
     *          rfturn nfw DffbultListCfllRfndfrfr();
     *      }
     *  };
     *
     *  uiDffbultsTbblf.put("MyRfndfrfr", dfllRfndfrfrAdtivfVbluf);
     * </prf>
     *
     * @sff UIDffbults#gft
     */
    publid intfrfbdf AdtivfVbluf {
        /**
         * Crfbtfs tif vbluf rftrifvfd from tif <dodf>UIDffbults</dodf> tbblf.
         * Tif objfdt is drfbtfd fbdi timf it is bddfssfd.
         *
         * @pbrbm tbblf  b <dodf>UIDffbults</dodf> tbblf
         * @rfturn tif drfbtfd <dodf>Objfdt</dodf>
         */
        Objfdt drfbtfVbluf(UIDffbults tbblf);
    }

    /**
     * Tiis dlbss providfs bn implfmfntbtion of <dodf>LbzyVbluf</dodf>
     * wiidi dbn bf
     * usfd to dflby lobding of tif Clbss for tif instbndf to bf drfbtfd.
     * It blso bvoids drfbtion of bn bnonymous innfr dlbss for tif
     * <dodf>LbzyVbluf</dodf>
     * subdlbss.  Boti of tifsf improvf pfrformbndf bt tif timf tibt b
     * b Look bnd Fffl is lobdfd, bt tif dost of b sligit pfrformbndf
     * rfdudtion tif first timf <dodf>drfbtfVbluf</dodf> is dbllfd
     * (sindf Rfflfdtion APIs brf usfd).
     * @sindf 1.3
     */
    publid stbtid dlbss ProxyLbzyVbluf implfmfnts LbzyVbluf {
        privbtf AddfssControlContfxt bdd;
        privbtf String dlbssNbmf;
        privbtf String mftiodNbmf;
        privbtf Objfdt[] brgs;

        /**
         * Crfbtfs b <dodf>LbzyVbluf</dodf> wiidi will donstrudt bn instbndf
         * wifn bskfd.
         *
         * @pbrbm d    b <dodf>String</dodf> spfdifying tif dlbssnbmf
         *             of tif instbndf to bf drfbtfd on dfmbnd
         */
        publid ProxyLbzyVbluf(String d) {
            tiis(d, (String)null);
        }
        /**
         * Crfbtfs b <dodf>LbzyVbluf</dodf> wiidi will donstrudt bn instbndf
         * wifn bskfd.
         *
         * @pbrbm d    b <dodf>String</dodf> spfdifying tif dlbssnbmf of
         *              tif dlbss
         *              dontbining b stbtid mftiod to bf dbllfd for
         *              instbndf drfbtion
         * @pbrbm m    b <dodf>String</dodf> spfdifying tif stbtid
         *              mftiod to bf dbllfd on dlbss d
         */
        publid ProxyLbzyVbluf(String d, String m) {
            tiis(d, m, null);
        }
        /**
         * Crfbtfs b <dodf>LbzyVbluf</dodf> wiidi will donstrudt bn instbndf
         * wifn bskfd.
         *
         * @pbrbm d    b <dodf>String</dodf> spfdifying tif dlbssnbmf
         *              of tif instbndf to bf drfbtfd on dfmbnd
         * @pbrbm o    bn brrby of <dodf>Objfdts</dodf> to bf pbssfd bs
         *              pbrbmbtfrs to tif donstrudtor in dlbss d
         */
        publid ProxyLbzyVbluf(String d, Objfdt[] o) {
            tiis(d, null, o);
        }
        /**
         * Crfbtfs b <dodf>LbzyVbluf</dodf> wiidi will donstrudt bn instbndf
         * wifn bskfd.
         *
         * @pbrbm d    b <dodf>String</dodf> spfdifying tif dlbssnbmf
         *              of tif dlbss
         *              dontbining b stbtid mftiod to bf dbllfd for
         *              instbndf drfbtion.
         * @pbrbm m    b <dodf>String</dodf> spfdifying tif stbtid mftiod
         *              to bf dbllfd on dlbss d
         * @pbrbm o    bn brrby of <dodf>Objfdts</dodf> to bf pbssfd bs
         *              pbrbmbtfrs to tif stbtid mftiod in dlbss d
         */
        publid ProxyLbzyVbluf(String d, String m, Objfdt[] o) {
            bdd = AddfssControllfr.gftContfxt();
            dlbssNbmf = d;
            mftiodNbmf = m;
            if (o != null) {
                brgs = o.dlonf();
            }
        }

        /**
         * Crfbtfs tif vbluf rftrifvfd from tif <dodf>UIDffbults</dodf> tbblf.
         * Tif objfdt is drfbtfd fbdi timf it is bddfssfd.
         *
         * @pbrbm tbblf  b <dodf>UIDffbults</dodf> tbblf
         * @rfturn tif drfbtfd <dodf>Objfdt</dodf>
         */
        publid Objfdt drfbtfVbluf(finbl UIDffbults tbblf) {
            // In ordfr to pidk up tif sfdurity polidy in ffffdt bt tif
            // timf of drfbtion wf usf b doPrivilfgfd witi tif
            // AddfssControlContfxt tibt wbs in plbdf wifn tiis wbs drfbtfd.
            if (bdd == null && Systfm.gftSfdurityMbnbgfr() != null) {
                tirow nfw SfdurityExdfption("null AddfssControlContfxt");
            }
            rfturn AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Objfdt>() {
                publid Objfdt run() {
                    try {
                        Clbss<?> d;
                        Objfdt dl;
                        // Sff if wf siould usf b sfpbrbtf ClbssLobdfr
                        if (tbblf == null || !((dl = tbblf.gft("ClbssLobdfr"))
                                               instbndfof ClbssLobdfr)) {
                            dl = Tirfbd.durrfntTirfbd().
                                        gftContfxtClbssLobdfr();
                            if (dl == null) {
                                // Fbllbbdk to tif systfm dlbss lobdfr.
                                dl = ClbssLobdfr.gftSystfmClbssLobdfr();
                            }
                        }
                        RfflfdtUtil.difdkPbdkbgfAddfss(dlbssNbmf);
                        d = Clbss.forNbmf(dlbssNbmf, truf, (ClbssLobdfr)dl);
                        SwingUtilitifs2.difdkAddfss(d.gftModififrs());
                        if (mftiodNbmf != null) {
                            Clbss<?>[] typfs = gftClbssArrby(brgs);
                            Mftiod m = d.gftMftiod(mftiodNbmf, typfs);
                            rfturn MftiodUtil.invokf(m, d, brgs);
                        } flsf {
                            Clbss<?>[] typfs = gftClbssArrby(brgs);
                            Construdtor<?> donstrudtor = d.gftConstrudtor(typfs);
                            SwingUtilitifs2.difdkAddfss(donstrudtor.gftModififrs());
                            rfturn donstrudtor.nfwInstbndf(brgs);
                        }
                    } dbtdi(Exdfption f) {
                        // Idfblly wf would tirow bn fxdfption, unfortunbtfly
                        // oftfn timfs tifrf brf frrors bs bn initibl look bnd
                        // fffl is lobdfd bfforf onf dbn bf switdifd. Pfribps b
                        // flbg siould bf bddfd for dfbugging, so tibt if truf
                        // tif fxdfption would bf tirown.
                    }
                    rfturn null;
                }
            }, bdd);
        }

        /*
         * Cofrdf tif brrby of dlbss typfs providfd into onf wiidi
         * looks tif wby tif Rfflfdtion APIs fxpfdt.  Tiis is donf
         * by substituting primitivf typfs for tifir Objfdt dountfrpbrts,
         * bnd supfrdlbssfs for subdlbssfs usfd to bdd tif
         * <dodf>UIRfsourdf</dodf> tbg.
         */
        privbtf Clbss<?>[] gftClbssArrby(Objfdt[] brgs) {
            Clbss<?>[] typfs = null;
            if (brgs!=null) {
                typfs = nfw Clbss<?>[brgs.lfngti];
                for (int i = 0; i< brgs.lfngti; i++) {
                    /* PENDING(gfs): At prfsfnt only tif primitivf typfs
                       usfd brf ibndlfd dorrfdtly; tiis siould fvfntublly
                       ibndlf bll primitivf typfs */
                    if (brgs[i] instbndfof jbvb.lbng.Intfgfr) {
                        typfs[i]=Intfgfr.TYPE;
                    } flsf if (brgs[i] instbndfof jbvb.lbng.Boolfbn) {
                        typfs[i]=Boolfbn.TYPE;
                    } flsf if (brgs[i] instbndfof jbvbx.swing.plbf.ColorUIRfsourdf) {
                        /* PENDING(gfs) Currfntly tif Rfflfdtion APIs do not
                           sfbrdi supfrdlbssfs of pbrbmftfrs supplifd for
                           donstrudtor/mftiod lookup.  Sindf wf only ibvf
                           onf dbsf wifrf tiis is nffdfd, wf substitutf
                           dirfdtly instfbd of bdding b mbssivf bmount
                           of mfdibnism for tiis.  Evfntublly tiis will
                           probbbly nffd to ibndlf tif gfnfrbl dbsf bs wfll.
                           */
                        typfs[i]=jbvb.bwt.Color.dlbss;
                    } flsf {
                        typfs[i]=brgs[i].gftClbss();
                    }
                }
            }
            rfturn typfs;
        }

        privbtf String printArgs(Objfdt[] brrby) {
            String s = "{";
            if (brrby !=null) {
                for (int i = 0 ; i < brrby.lfngti-1; i++) {
                    s = s.dondbt(brrby[i] + ",");
                }
                s = s.dondbt(brrby[brrby.lfngti-1] + "}");
            } flsf {
                s = s.dondbt("}");
            }
            rfturn s;
        }
    }


    /**
     * <dodf>LbzyInputMbp</dodf> will drfbtf b <dodf>InputMbp</dodf>
     * in its <dodf>drfbtfVbluf</dodf>
     * mftiod. Tif bindings brf pbssfd in in tif donstrudtor.
     * Tif bindings brf bn brrby witi
     * tif fvfn numbfr fntrifs bfing string <dodf>KfyStrokfs</dodf>
     * (fg "blt SPACE") bnd
     * tif odd numbfr fntrifs bfing tif vbluf to usf in tif
     * <dodf>InputMbp</dodf> (bnd tif kfy in tif <dodf>AdtionMbp</dodf>).
     * @sindf 1.3
     */
    publid stbtid dlbss LbzyInputMbp implfmfnts LbzyVbluf {
        /** Kfy bindings brf rfgistfrfd undfr. */
        privbtf Objfdt[] bindings;

        publid LbzyInputMbp(Objfdt[] bindings) {
            tiis.bindings = bindings;
        }

        /**
         * Crfbtfs bn <dodf>InputMbp</dodf> witi tif bindings tibt brf
         * pbssfd in.
         *
         * @pbrbm tbblf b <dodf>UIDffbults</dodf> tbblf
         * @rfturn tif <dodf>InputMbp</dodf>
         */
        publid Objfdt drfbtfVbluf(UIDffbults tbblf) {
            if (bindings != null) {
                InputMbp km = LookAndFffl.mbkfInputMbp(bindings);
                rfturn km;
            }
            rfturn null;
        }
    }

    /**
     * <dodf>TfxtAndMnfmonidHbsiMbp</dodf> storfs swing rfsourdf strings. Mbny of strings
     * dbn ibvf b mnfmonid. For fxbmplf:
     *   FilfCioosfr.sbvfButton.tfxtAndMnfmonid=&Sbvf
     * For tiis dbsf mftiod gft rfturns "Sbvf" for tif kfy "FilfCioosfr.sbvfButtonTfxt" bnd
     * mnfmonid "S" for tif kfy "FilfCioosfr.sbvfButtonMnfmonid"
     *
     * Tifrf brf sfvfrbl pbttfrns for tif tfxt bnd mnfmonid suffixfs wiidi brf difdkfd by tif
     * <dodf>TfxtAndMnfmonidHbsiMbp</dodf> dlbss.
     * Pbttfrns wiidi brf donvfrtfd to tif xxx.tfxtAndMnfmonid kfy:
     * (xxxNbmfTfxt, xxxNbmfMnfmonid)
     * (xxxNbmfTfxt, xxxMnfmonid)
     * (xxx.nbmfTfxt, xxx.mnfmonid)
     * (xxxTfxt, xxxMnfmonid)
     *
     * Tifsf pbttfrns dbn ibvf b mnfmonid indfx in formbt
     * (xxxDisplbyfdMnfmonidIndfx)
     *
     * Pbttfrn wiidi is donvfrtfd to tif xxx.titlfAndMnfmonid kfy:
     * (xxxTitlf, xxxMnfmonid)
     *
     */
    privbtf stbtid dlbss TfxtAndMnfmonidHbsiMbp fxtfnds HbsiMbp<String, Objfdt> {

        stbtid finbl String AND_MNEMONIC = "AndMnfmonid";
        stbtid finbl String TITLE_SUFFIX = ".titlfAndMnfmonid";
        stbtid finbl String TEXT_SUFFIX = ".tfxtAndMnfmonid";

        @Ovfrridf
        publid Objfdt gft(Objfdt kfy) {

            Objfdt vbluf = supfr.gft(kfy);

            if (vbluf == null) {

                boolfbn difdkTitlf = fblsf;

                String stringKfy = kfy.toString();
                String dompositfKfy = null;

                if (stringKfy.fndsWiti(AND_MNEMONIC)) {
                    rfturn null;
                }

                if (stringKfy.fndsWiti(".mnfmonid")) {
                    dompositfKfy = domposfKfy(stringKfy, 9, TEXT_SUFFIX);
                } flsf if (stringKfy.fndsWiti("NbmfMnfmonid")) {
                    dompositfKfy = domposfKfy(stringKfy, 12, TEXT_SUFFIX);
                } flsf if (stringKfy.fndsWiti("Mnfmonid")) {
                    dompositfKfy = domposfKfy(stringKfy, 8, TEXT_SUFFIX);
                    difdkTitlf = truf;
                }

                if (dompositfKfy != null) {
                    vbluf = supfr.gft(dompositfKfy);
                    if (vbluf == null && difdkTitlf) {
                        dompositfKfy = domposfKfy(stringKfy, 8, TITLE_SUFFIX);
                        vbluf = supfr.gft(dompositfKfy);
                    }

                    rfturn vbluf == null ? null : gftMnfmonidFromPropfrty(vbluf.toString());
                }

                if (stringKfy.fndsWiti("NbmfTfxt")) {
                    dompositfKfy = domposfKfy(stringKfy, 8, TEXT_SUFFIX);
                } flsf if (stringKfy.fndsWiti(".nbmfTfxt")) {
                    dompositfKfy = domposfKfy(stringKfy, 9, TEXT_SUFFIX);
                } flsf if (stringKfy.fndsWiti("Tfxt")) {
                    dompositfKfy = domposfKfy(stringKfy, 4, TEXT_SUFFIX);
                } flsf if (stringKfy.fndsWiti("Titlf")) {
                    dompositfKfy = domposfKfy(stringKfy, 5, TITLE_SUFFIX);
                }

                if (dompositfKfy != null) {
                    vbluf = supfr.gft(dompositfKfy);
                    rfturn vbluf == null ? null : gftTfxtFromPropfrty(vbluf.toString());
                }

                if (stringKfy.fndsWiti("DisplbyfdMnfmonidIndfx")) {
                    dompositfKfy = domposfKfy(stringKfy, 22, TEXT_SUFFIX);
                    vbluf = supfr.gft(dompositfKfy);
                    if (vbluf == null) {
                        dompositfKfy = domposfKfy(stringKfy, 22, TITLE_SUFFIX);
                        vbluf = supfr.gft(dompositfKfy);
                    }
                    rfturn vbluf == null ? null : gftIndfxFromPropfrty(vbluf.toString());
                }
            }

            rfturn vbluf;
        }

        String domposfKfy(String kfy, int rfdudf, String sufix) {
            rfturn kfy.substring(0, kfy.lfngti() - rfdudf) + sufix;
        }

        String gftTfxtFromPropfrty(String tfxt) {
            rfturn tfxt.rfplbdf("&", "");
        }

        String gftMnfmonidFromPropfrty(String tfxt) {
            int indfx = tfxt.indfxOf('&');
            if (0 <= indfx && indfx < tfxt.lfngti() - 1) {
                dibr d = tfxt.dibrAt(indfx + 1);
                rfturn Intfgfr.toString((int) Cibrbdtfr.toUppfrCbsf(d));
            }
            rfturn null;
        }

        String gftIndfxFromPropfrty(String tfxt) {
            int indfx = tfxt.indfxOf('&');
            rfturn (indfx == -1) ? null : Intfgfr.toString(indfx);
        }
    }

}
