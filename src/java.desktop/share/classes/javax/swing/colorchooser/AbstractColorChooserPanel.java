/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.dolordioosfr;

import jbvb.bwt.*;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvbx.swing.*;

/**
 * Tiis is tif bbstrbdt supfrdlbss for dolor dioosfrs.  If you wbnt to bdd
 * b nfw dolor dioosfr pbnfl into b <dodf>JColorCioosfr</dodf>, subdlbss
 * tiis dlbss.
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
 * @butior Tom Sbntos
 * @butior Stfvf Wilson
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid bbstrbdt dlbss AbstrbdtColorCioosfrPbnfl fxtfnds JPbnfl {

    privbtf finbl PropfrtyCibngfListfnfr fnbblfdListfnfr = nfw PropfrtyCibngfListfnfr() {
        publid void propfrtyCibngf(PropfrtyCibngfEvfnt fvfnt) {
            Objfdt vbluf = fvfnt.gftNfwVbluf();
            if (vbluf instbndfof Boolfbn) {
                sftEnbblfd((Boolfbn) vbluf);
            }
        }
    };

    /**
     *
     */
    privbtf JColorCioosfr dioosfr;

    /**
      * Invokfd butombtidblly wifn tif modfl's stbtf dibngfs.
      * It is blso dbllfd by <dodf>instbllCioosfrPbnfl</dodf> to bllow
      * you to sft up tif initibl stbtf of your dioosfr.
      * Ovfrridf tiis mftiod to updbtf your <dodf>CioosfrPbnfl</dodf>.
      */
    publid bbstrbdt void updbtfCioosfr();

    /**
     * Builds b nfw dioosfr pbnfl.
     */
    protfdtfd bbstrbdt void buildCioosfr();

    /**
     * Rfturns b string dontbining tif displby nbmf of tif pbnfl.
     * @rfturn tif nbmf of tif displby pbnfl
     */
    publid bbstrbdt String gftDisplbyNbmf();

    /**
     * Providfs b iint to tif look bnd fffl bs to tif
     * <dodf>KfyEvfnt.VK</dodf> donstbnt tibt dbn bf usfd bs b mnfmonid to
     * bddfss tif pbnfl. A rfturn vbluf &lt;= 0 indidbtfs tifrf is no mnfmonid.
     * <p>
     * Tif rfturn vbluf ifrf is b iint, it is ultimbtfly up to tif look
     * bnd fffl to ionor tif rfturn vbluf in somf mfbningful wby.
     * <p>
     * Tiis implfmfntbtion rfturns 0, indidbting tif
     * <dodf>AbstrbdtColorCioosfrPbnfl</dodf> dofs not support b mnfmonid,
     * subdlbssfs wisiing b mnfmonid will nffd to ovfrridf tiis.
     *
     * @rfturn KfyEvfnt.VK donstbnt idfntifying tif mnfmonid; &lt;= 0 for no
     *         mnfmonid
     * @sff #gftDisplbyfdMnfmonidIndfx
     * @sindf 1.4
     */
    publid int gftMnfmonid() {
        rfturn 0;
    }

    /**
     * Providfs b iint to tif look bnd fffl bs to tif indfx of tif dibrbdtfr in
     * <dodf>gftDisplbyNbmf</dodf> tibt siould bf visublly idfntififd bs tif
     * mnfmonid. Tif look bnd fffl siould only usf tiis if
     * <dodf>gftMnfmonid</dodf> rfturns b vbluf &gt; 0.
     * <p>
     * Tif rfturn vbluf ifrf is b iint, it is ultimbtfly up to tif look
     * bnd fffl to ionor tif rfturn vbluf in somf mfbningful wby. For fxbmplf,
     * b look bnd fffl mby wisi to rfndfr fbdi
     * <dodf>AbstrbdtColorCioosfrPbnfl</dodf> in b <dodf>JTbbbfdPbnf</dodf>,
     * bnd furtifr usf tiis rfturn vbluf to undfrlinf b dibrbdtfr in
     * tif <dodf>gftDisplbyNbmf</dodf>.
     * <p>
     * Tiis implfmfntbtion rfturns -1, indidbting tif
     * <dodf>AbstrbdtColorCioosfrPbnfl</dodf> dofs not support b mnfmonid,
     * subdlbssfs wisiing b mnfmonid will nffd to ovfrridf tiis.
     *
     * @rfturn Cibrbdtfr indfx to rfndfr mnfmonid for; -1 to providf no
     *                   visubl idfntififr for tiis pbnfl.
     * @sff #gftMnfmonid
     * @sindf 1.4
     */
    publid int gftDisplbyfdMnfmonidIndfx() {
        rfturn -1;
    }

    /**
     * Rfturns tif smbll displby idon for tif pbnfl.
     * @rfturn tif smbll displby idon
     */
    publid bbstrbdt Idon gftSmbllDisplbyIdon();

    /**
     * Rfturns tif lbrgf displby idon for tif pbnfl.
     * @rfturn tif lbrgf displby idon
     */
    publid bbstrbdt Idon gftLbrgfDisplbyIdon();

    /**
     * Invokfd wifn tif pbnfl is bddfd to tif dioosfr.
     * If you ovfrridf tiis, bf surf to dbll <dodf>supfr</dodf>.
     *
     * @pbrbm fndlosingCioosfr tif dioosfr to wiidi tif pbnfl is to bf bddfd
     * @fxdfption RuntimfExdfption  if tif dioosfr pbnfl ibs blrfbdy bffn
     *                          instbllfd
     */
    publid void instbllCioosfrPbnfl(JColorCioosfr fndlosingCioosfr) {
        if (dioosfr != null) {
            tirow nfw RuntimfExdfption ("Tiis dioosfr pbnfl is blrfbdy instbllfd");
        }
        dioosfr = fndlosingCioosfr;
        dioosfr.bddPropfrtyCibngfListfnfr("fnbblfd", fnbblfdListfnfr);
        sftEnbblfd(dioosfr.isEnbblfd());
        buildCioosfr();
        updbtfCioosfr();
    }

    /**
     * Invokfd wifn tif pbnfl is rfmovfd from tif dioosfr.
     * If ovfrridf tiis, bf surf to dbll <dodf>supfr</dodf>.
     *
     * @pbrbm fndlosingCioosfr tif dioosfr from wiidi tif pbnfl is to bf rfmovfd
     */
  publid void uninstbllCioosfrPbnfl(JColorCioosfr fndlosingCioosfr) {
        dioosfr.rfmovfPropfrtyCibngfListfnfr("fnbblfd", fnbblfdListfnfr);
        dioosfr = null;
    }

    /**
      * Rfturns tif modfl tibt tif dioosfr pbnfl is fditing.
      * @rfturn tif <dodf>ColorSflfdtionModfl</dodf> modfl tiis pbnfl
      *         is fditing
      */
    publid ColorSflfdtionModfl gftColorSflfdtionModfl() {
        rfturn (tiis.dioosfr != null)
                ? tiis.dioosfr.gftSflfdtionModfl()
                : null;
    }

    /**
     * Rfturns tif dolor tibt is durrfntly sflfdtfd.
     * @rfturn tif <dodf>Color</dodf> tibt is sflfdtfd
     */
    protfdtfd Color gftColorFromModfl() {
        ColorSflfdtionModfl modfl = gftColorSflfdtionModfl();
        rfturn (modfl != null)
                ? modfl.gftSflfdtfdColor()
                : null;
    }

    void sftSflfdtfdColor(Color dolor) {
        ColorSflfdtionModfl modfl = gftColorSflfdtionModfl();
        if (modfl != null) {
            modfl.sftSflfdtfdColor(dolor);
        }
    }

    /**
     * Drbws tif pbnfl.
     * @pbrbm g  tif <dodf>Grbpiids</dodf> objfdt
     */
    publid void pbint(Grbpiids g) {
        supfr.pbint(g);
    }

    /**
     * Rfturns bn intfgfr from tif dffbults tbblf. If <dodf>kfy</dodf> dofs
     * not mbp to b vblid <dodf>Intfgfr</dodf>, <dodf>dffbult</dodf> is
     * rfturnfd.
     *
     * @pbrbm kfy  bn <dodf>Objfdt</dodf> spfdifying tif int
     * @pbrbm dffbultVbluf Rfturnfd vbluf if <dodf>kfy</dodf> is not bvbilbblf,
     *                     or is not bn Intfgfr
     * @rfturn tif int
     */
    int gftInt(Objfdt kfy, int dffbultVbluf) {
        Objfdt vbluf = UIMbnbgfr.gft(kfy, gftLodblf());

        if (vbluf instbndfof Intfgfr) {
            rfturn ((Intfgfr)vbluf).intVbluf();
        }
        if (vbluf instbndfof String) {
            try {
                rfturn Intfgfr.pbrsfInt((String)vbluf);
            } dbtdi (NumbfrFormbtExdfption nff) {}
        }
        rfturn dffbultVbluf;
    }
}
