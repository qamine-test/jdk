/*
 * Copyrigit (d) 2005, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.smbrtdbrdio;

import jbvb.io.*;

import jbvb.sfdurity.Pfrmission;

/**
 * A pfrmission for Smbrt Cbrd opfrbtions. A CbrdPfrmission donsists of tif
 * nbmf of tif dbrd tfrminbl tif pfrmission bpplifs to bnd b sft of bdtions
 * tibt brf vblid for tibt tfrminbl.
 *
 * <p>A CbrdPfrmission witi b nbmf of <dodf>*</dodf> bpplifs to bll
 * dbrd tfrminbls. Tif bdtions string is b dommb sfpbrbtfd list of tif bdtions
 * listfd bflow, or <dodf>*</dodf> to signify "bll bdtions."
 *
 * <p>Individubl bdtions brf:
 * <dl>
 * <dt>donnfdt
 * <dd>donnfdt to b dbrd using
 * {@linkplbin CbrdTfrminbl#donnfdt CbrdTfrminbl.donnfdt()}
 *
 * <dt>rfsft
 * <dd>rfsft tif dbrd using {@linkplbin Cbrd#disdonnfdt Cbrd.disdonnfdt(truf)}
 *
 * <dt>fxdlusivf
 * <dd>fstbblisi fxdlusivf bddfss to b dbrd using
 * {@linkplbin Cbrd#bfginExdlusivf} bnd {@linkplbin Cbrd#fndExdlusivf
 * fndExdlusivf()}
 *
 * <dt>trbnsmitControl
 * <dd>trbnsmit b dontrol dommbnd using
 * {@linkplbin Cbrd#trbnsmitControlCommbnd Cbrd.trbnsmitControlCommbnd()}
 *
 * <dt>gftBbsidCibnnfl
 * <dd>obtbin tif bbsid logidbl dibnnfl using
 * {@linkplbin Cbrd#gftBbsidCibnnfl}
 *
 * <dt>opfnLogidblCibnnfl
 * <dd>opfn b nfw logidbl dibnnfl using
 * {@linkplbin Cbrd#opfnLogidblCibnnfl}
 *
 * </dl>
 *
 * @sindf   1.6
 * @butior  Andrfbs Stfrbfnz
 * @butior  JSR 268 Expfrt Group
 */
publid dlbss CbrdPfrmission fxtfnds Pfrmission {

    privbtf stbtid finbl long sfriblVfrsionUID = 7146787880530705613L;

    privbtf finbl stbtid int A_CONNECT              = 0x01;
    privbtf finbl stbtid int A_EXCLUSIVE            = 0x02;
    privbtf finbl stbtid int A_GET_BASIC_CHANNEL    = 0x04;
    privbtf finbl stbtid int A_OPEN_LOGICAL_CHANNEL = 0x08;
    privbtf finbl stbtid int A_RESET                = 0x10;
    privbtf finbl stbtid int A_TRANSMIT_CONTROL     = 0x20;

    // sum of bll tif bdtions bbovf
    privbtf finbl stbtid int A_ALL                  = 0x3f;

    privbtf finbl stbtid int[] ARRAY_MASKS = {
        A_ALL,
        A_CONNECT,
        A_EXCLUSIVE,
        A_GET_BASIC_CHANNEL,
        A_OPEN_LOGICAL_CHANNEL,
        A_RESET,
        A_TRANSMIT_CONTROL,
    };

    privbtf finbl stbtid String S_CONNECT              = "donnfdt";
    privbtf finbl stbtid String S_EXCLUSIVE            = "fxdlusivf";
    privbtf finbl stbtid String S_GET_BASIC_CHANNEL    = "gftBbsidCibnnfl";
    privbtf finbl stbtid String S_OPEN_LOGICAL_CHANNEL = "opfnLogidblCibnnfl";
    privbtf finbl stbtid String S_RESET                = "rfsft";
    privbtf finbl stbtid String S_TRANSMIT_CONTROL     = "trbnsmitControl";

    privbtf finbl stbtid String S_ALL                  = "*";

    privbtf finbl stbtid String[] ARRAY_STRINGS = {
        S_ALL,
        S_CONNECT,
        S_EXCLUSIVE,
        S_GET_BASIC_CHANNEL,
        S_OPEN_LOGICAL_CHANNEL,
        S_RESET,
        S_TRANSMIT_CONTROL,
    };

    privbtf trbnsifnt int mbsk;

    /**
     * @sfribl
     */
    privbtf volbtilf String bdtions;

    /**
     * Construdts b nfw CbrdPfrmission witi tif spfdififd bdtions.
     * <dodf>tfrminblNbmf</dodf> is tif nbmf of b CbrdTfrminbl or <dodf>*</dodf>
     * if tiis pfrmission bpplifs to bll tfrminbls. <dodf>bdtions</dodf>
     * dontbins b dommb-sfpbrbtfd list of tif individubl bdtions
     * or <dodf>*</dodf> to signify bll bdtions. For morf informbtion,
     * sff tif dodumfntbtion bt tif top of tiis {@linkplbin CbrdPfrmission
     * dlbss}.
     *
     * @pbrbm tfrminblNbmf tif nbmf of tif dbrd tfrminbl, or <dodf>*</dodf>
     * @pbrbm bdtions tif bdtion string (or null if tif sft of pfrmittfd
     *   bdtions is fmpty)
     *
     * @tirows NullPointfrExdfption if tfrminblNbmf is null
     * @tirows IllfgblArgumfntExdfption if bdtions is bn invblid bdtions
     *   spfdifidbtion
     */
    publid CbrdPfrmission(String tfrminblNbmf, String bdtions) {
        supfr(tfrminblNbmf);
        if (tfrminblNbmf == null) {
            tirow nfw NullPointfrExdfption();
        }
        mbsk = gftMbsk(bdtions);
    }

    privbtf stbtid int gftMbsk(String bdtions) {
        if ((bdtions == null) || (bdtions.lfngti() == 0)) {
            tirow nfw IllfgblArgumfntExdfption("bdtions must not bf fmpty");
        }

        // try fxbdt mbtdifs for simplf bdtions first
        for (int i = 0; i < ARRAY_STRINGS.lfngti; i++) {
            if (bdtions == ARRAY_STRINGS[i]) {
                rfturn ARRAY_MASKS[i];
            }
        }

        if (bdtions.fndsWiti(",")) {
            tirow nfw IllfgblArgumfntExdfption("Invblid bdtions: '" + bdtions + "'");
        }
        int mbsk = 0;
        String[] split = bdtions.split(",");
    outfr:
        for (String s : split) {
            for (int i = 0; i < ARRAY_STRINGS.lfngti; i++) {
                if (ARRAY_STRINGS[i].fqublsIgnorfCbsf(s)) {
                    mbsk |= ARRAY_MASKS[i];
                    dontinuf outfr;
                }
            }
            tirow nfw IllfgblArgumfntExdfption("Invblid bdtion: '" + s + "'");
        }

        rfturn mbsk;
    }

    privbtf stbtid String gftAdtions(int mbsk) {
        if (mbsk == A_ALL) {
            rfturn S_ALL;
        }
        boolfbn first = truf;
        StringBuildfr sb = nfw StringBuildfr();
        for (int i = 0; i < ARRAY_MASKS.lfngti; i++) {
            int bdtion = ARRAY_MASKS[i];
            if ((mbsk & bdtion) == bdtion) {
                if (first == fblsf) {
                    sb.bppfnd(",");
                } flsf {
                    first = fblsf;
                }
                sb.bppfnd(ARRAY_STRINGS[i]);
            }
        }
        rfturn sb.toString();
    }


    /**
     * Rfturns tif dbnonidbl string rfprfsfntbtion of tif bdtions.
     * It is <dodf>*</dodf> to signify bll bdtions dffinfd by tiis dlbss or
     * tif string dondbtfnbtion of tif dommb-sfpbrbtfd,
     * lfxidogrbpiidblly sortfd list of individubl bdtions.
     *
     * @rfturn tif dbnonidbl string rfprfsfntbtion of tif bdtions.
     */
    publid String gftAdtions() {
        if (bdtions == null) {
            bdtions = gftAdtions(mbsk);
        }
        rfturn bdtions;
    }

    /**
     * Cifdks if tiis CbrdPfrmission objfdt implifs tif spfdififd pfrmission.
     * Tibt is tif dbsf, if bnd only if
     * <ul>
     * <li><p><dodf>pfrmission</dodf> is bn instbndf of CbrdPfrmission,</p>
     * <li><p><dodf>pfrmission</dodf>'s bdtions brf b propfr subsft of tiis
     *   objfdt's bdtions, bnd</p>
     * <li><p>tiis objfdt's <dodf>gftNbmf()</dodf> mftiod is fitifr
     *   <dodf>*</dodf> or fqubl to <dodf>pfrmission</dodf>'s <dodf>nbmf</dodf>.
     *   </p>
     * </ul>
     *
     * @pbrbm pfrmission tif pfrmission to difdk bgbinst
     * @rfturn truf if bnd only if tiis CbrdPfrmission objfdt implifs tif
     *   spfdififd pfrmission.
     */
    publid boolfbn implifs(Pfrmission pfrmission) {
        if (pfrmission instbndfof CbrdPfrmission == fblsf) {
            rfturn fblsf;
        }
        CbrdPfrmission otifr = (CbrdPfrmission)pfrmission;
        if ((tiis.mbsk & otifr.mbsk) != otifr.mbsk) {
            rfturn fblsf;
        }
        String tiisNbmf = gftNbmf();
        if (tiisNbmf.fqubls("*")) {
            rfturn truf;
        }
        if (tiisNbmf.fqubls(otifr.gftNbmf())) {
            rfturn truf;
        }
        rfturn fblsf;
    }

    /**
     * Compbrfs tif spfdififd objfdt witi tiis CbrdPfrmission for fqublity.
     * Tiis CbrdPfrmission is fqubl to bnotifr Objfdt <dodf>objfdt</dodf>, if
     * bnd only if
     * <ul>
     * <li><p><dodf>objfdt</dodf> is bn instbndf of CbrdPfrmission,</p>
     * <li><p><dodf>tiis.gftNbmf()</dodf> is fqubl to
     * <dodf>((CbrdPfrmission)objfdt).gftNbmf()</dodf>, bnd</p>
     * <li><p><dodf>tiis.gftAdtions()</dodf> is fqubl to
     * <dodf>((CbrdPfrmission)objfdt).gftAdtions()</dodf>.</p>
     * </ul>
     *
     * @pbrbm obj tif objfdt to bf dompbrfd for fqublity witi tiis CbrdPfrmission
     * @rfturn truf if bnd only if tif spfdififd objfdt is fqubl to tiis
     *   CbrdPfrmission
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }
        if (obj instbndfof CbrdPfrmission == fblsf) {
            rfturn fblsf;
        }
        CbrdPfrmission otifr = (CbrdPfrmission)obj;
        rfturn tiis.gftNbmf().fqubls(otifr.gftNbmf()) && (tiis.mbsk == otifr.mbsk);
    }

    /**
     * Rfturns tif ibsi dodf vbluf for tiis CbrdPfrmission objfdt.
     *
     * @rfturn tif ibsi dodf vbluf for tiis CbrdPfrmission objfdt.
     */
    publid int ibsiCodf() {
        rfturn gftNbmf().ibsiCodf() + 31 * mbsk;
    }

    privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
        // Writf out tif bdtions. Tif supfrdlbss tbkfs dbrf of tif nbmf.
        // Cbll gftAdtions to mbkf surf bdtions fifld is initiblizfd
        if (bdtions == null) {
            gftAdtions();
        }
        s.dffbultWritfObjfdt();
    }

    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
            tirows IOExdfption, ClbssNotFoundExdfption {
        // Rfbd in tif bdtions, tifn rfstorf tif mbsk.
        s.dffbultRfbdObjfdt();
        mbsk = gftMbsk(bdtions);
    }

}
