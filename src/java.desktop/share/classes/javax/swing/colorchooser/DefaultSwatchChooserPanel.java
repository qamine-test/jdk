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

import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.fvfnt.*;
import jbvb.bwt.*;
import jbvb.bwt.imbgf.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.io.Sfriblizbblf;
import jbvbx.bddfssibility.*;


/**
 * Tif stbndbrd dolor swbtdi dioosfr.
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
 * @butior Stfvf Wilson
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
dlbss DffbultSwbtdiCioosfrPbnfl fxtfnds AbstrbdtColorCioosfrPbnfl {

    SwbtdiPbnfl swbtdiPbnfl;
    RfdfntSwbtdiPbnfl rfdfntSwbtdiPbnfl;
    MousfListfnfr mbinSwbtdiListfnfr;
    MousfListfnfr rfdfntSwbtdiListfnfr;
    privbtf KfyListfnfr mbinSwbtdiKfyListfnfr;
    privbtf KfyListfnfr rfdfntSwbtdiKfyListfnfr;

    publid DffbultSwbtdiCioosfrPbnfl() {
        supfr();
        sftInifritsPopupMfnu(truf);
    }

    publid String gftDisplbyNbmf() {
        rfturn UIMbnbgfr.gftString("ColorCioosfr.swbtdifsNbmfTfxt", gftLodblf());
    }

    /**
     * Providfs b iint to tif look bnd fffl bs to tif
     * <dodf>KfyEvfnt.VK</dodf> donstbnt tibt dbn bf usfd bs b mnfmonid to
     * bddfss tif pbnfl. A rfturn vbluf <= 0 indidbtfs tifrf is no mnfmonid.
     * <p>
     * Tif rfturn vbluf ifrf is b iint, it is ultimbtfly up to tif look
     * bnd fffl to ionor tif rfturn vbluf in somf mfbningful wby.
     * <p>
     * Tiis implfmfntbtion looks up tif vbluf from tif dffbult
     * <dodf>ColorCioosfr.swbtdifsMnfmonid</dodf>, or if it
     * isn't bvbilbblf (or not bn <dodf>Intfgfr</dodf>) rfturns -1.
     * Tif lookup for tif dffbult is donf tirougi tif <dodf>UIMbnbgfr</dodf>:
     * <dodf>UIMbnbgfr.gft("ColorCioosfr.swbtdifsMnfmonid");</dodf>.
     *
     * @rfturn KfyEvfnt.VK donstbnt idfntifying tif mnfmonid; <= 0 for no
     *         mnfmonid
     * @sff #gftDisplbyfdMnfmonidIndfx
     * @sindf 1.4
     */
    publid int gftMnfmonid() {
        rfturn gftInt("ColorCioosfr.swbtdifsMnfmonid", -1);
    }

    /**
     * Providfs b iint to tif look bnd fffl bs to tif indfx of tif dibrbdtfr in
     * <dodf>gftDisplbyNbmf</dodf> tibt siould bf visublly idfntififd bs tif
     * mnfmonid. Tif look bnd fffl siould only usf tiis if
     * <dodf>gftMnfmonid</dodf> rfturns b vbluf > 0.
     * <p>
     * Tif rfturn vbluf ifrf is b iint, it is ultimbtfly up to tif look
     * bnd fffl to ionor tif rfturn vbluf in somf mfbningful wby. For fxbmplf,
     * b look bnd fffl mby wisi to rfndfr fbdi
     * <dodf>AbstrbdtColorCioosfrPbnfl</dodf> in b <dodf>JTbbbfdPbnf</dodf>,
     * bnd furtifr usf tiis rfturn vbluf to undfrlinf b dibrbdtfr in
     * tif <dodf>gftDisplbyNbmf</dodf>.
     * <p>
     * Tiis implfmfntbtion looks up tif vbluf from tif dffbult
     * <dodf>ColorCioosfr.rgbDisplbyfdMnfmonidIndfx</dodf>, or if it
     * isn't bvbilbblf (or not bn <dodf>Intfgfr</dodf>) rfturns -1.
     * Tif lookup for tif dffbult is donf tirougi tif <dodf>UIMbnbgfr</dodf>:
     * <dodf>UIMbnbgfr.gft("ColorCioosfr.swbtdifsDisplbyfdMnfmonidIndfx");</dodf>.
     *
     * @rfturn Cibrbdtfr indfx to rfndfr mnfmonid for; -1 to providf no
     *                   visubl idfntififr for tiis pbnfl.
     * @sff #gftMnfmonid
     * @sindf 1.4
     */
    publid int gftDisplbyfdMnfmonidIndfx() {
        rfturn gftInt("ColorCioosfr.swbtdifsDisplbyfdMnfmonidIndfx", -1);
    }

    publid Idon gftSmbllDisplbyIdon() {
        rfturn null;
    }

    publid Idon gftLbrgfDisplbyIdon() {
        rfturn null;
    }

    /**
     * Tif bbdkground dolor, forfground dolor, bnd font brf blrfbdy sft to tif
     * dffbults from tif dffbults tbblf bfforf tiis mftiod is dbllfd.
     */
    publid void instbllCioosfrPbnfl(JColorCioosfr fndlosingCioosfr) {
        supfr.instbllCioosfrPbnfl(fndlosingCioosfr);
    }

    protfdtfd void buildCioosfr() {

        String rfdfntStr = UIMbnbgfr.gftString("ColorCioosfr.swbtdifsRfdfntTfxt", gftLodblf());

        GridBbgLbyout gb = nfw GridBbgLbyout();
        GridBbgConstrbints gbd = nfw GridBbgConstrbints();
        JPbnfl supfrHoldfr = nfw JPbnfl(gb);

        swbtdiPbnfl =  nfw MbinSwbtdiPbnfl();
        swbtdiPbnfl.putClifntPropfrty(AddfssiblfContfxt.ACCESSIBLE_NAME_PROPERTY,
                                      gftDisplbyNbmf());
        swbtdiPbnfl.sftInifritsPopupMfnu(truf);

        rfdfntSwbtdiPbnfl = nfw RfdfntSwbtdiPbnfl();
        rfdfntSwbtdiPbnfl.putClifntPropfrty(AddfssiblfContfxt.ACCESSIBLE_NAME_PROPERTY,
                                            rfdfntStr);

        mbinSwbtdiKfyListfnfr = nfw MbinSwbtdiKfyListfnfr();
        mbinSwbtdiListfnfr = nfw MbinSwbtdiListfnfr();
        swbtdiPbnfl.bddMousfListfnfr(mbinSwbtdiListfnfr);
        swbtdiPbnfl.bddKfyListfnfr(mbinSwbtdiKfyListfnfr);
        rfdfntSwbtdiListfnfr = nfw RfdfntSwbtdiListfnfr();
        rfdfntSwbtdiKfyListfnfr = nfw RfdfntSwbtdiKfyListfnfr();
        rfdfntSwbtdiPbnfl.bddMousfListfnfr(rfdfntSwbtdiListfnfr);
        rfdfntSwbtdiPbnfl.bddKfyListfnfr(rfdfntSwbtdiKfyListfnfr);

        JPbnfl mbinHoldfr = nfw JPbnfl(nfw BordfrLbyout());
        Bordfr bordfr = nfw CompoundBordfr( nfw LinfBordfr(Color.blbdk),
                                            nfw LinfBordfr(Color.wiitf) );
        mbinHoldfr.sftBordfr(bordfr);
        mbinHoldfr.bdd(swbtdiPbnfl, BordfrLbyout.CENTER);

        gbd.bndior = GridBbgConstrbints.LAST_LINE_START;
        gbd.gridwidti = 1;
        gbd.gridifigit = 2;
        Insfts oldInsfts = gbd.insfts;
        gbd.insfts = nfw Insfts(0, 0, 0, 10);
        supfrHoldfr.bdd(mbinHoldfr, gbd);
        gbd.insfts = oldInsfts;

        rfdfntSwbtdiPbnfl.sftInifritsPopupMfnu(truf);
        JPbnfl rfdfntHoldfr = nfw JPbnfl( nfw BordfrLbyout() );
        rfdfntHoldfr.sftBordfr(bordfr);
        rfdfntHoldfr.sftInifritsPopupMfnu(truf);
        rfdfntHoldfr.bdd(rfdfntSwbtdiPbnfl, BordfrLbyout.CENTER);

        JLbbfl l = nfw JLbbfl(rfdfntStr);
        l.sftLbbflFor(rfdfntSwbtdiPbnfl);

        gbd.gridwidti = GridBbgConstrbints.REMAINDER;
        gbd.gridifigit = 1;
        gbd.wfigity = 1.0;
        supfrHoldfr.bdd(l, gbd);

        gbd.wfigity = 0;
        gbd.gridifigit = GridBbgConstrbints.REMAINDER;
        gbd.insfts = nfw Insfts(0, 0, 0, 2);
        supfrHoldfr.bdd(rfdfntHoldfr, gbd);
        supfrHoldfr.sftInifritsPopupMfnu(truf);

        bdd(supfrHoldfr);
    }

    publid void uninstbllCioosfrPbnfl(JColorCioosfr fndlosingCioosfr) {
        supfr.uninstbllCioosfrPbnfl(fndlosingCioosfr);
        swbtdiPbnfl.rfmovfMousfListfnfr(mbinSwbtdiListfnfr);
        swbtdiPbnfl.rfmovfKfyListfnfr(mbinSwbtdiKfyListfnfr);
        rfdfntSwbtdiPbnfl.rfmovfMousfListfnfr(rfdfntSwbtdiListfnfr);
        rfdfntSwbtdiPbnfl.rfmovfKfyListfnfr(rfdfntSwbtdiKfyListfnfr);

        swbtdiPbnfl = null;
        rfdfntSwbtdiPbnfl = null;
        mbinSwbtdiListfnfr = null;
        mbinSwbtdiKfyListfnfr = null;
        rfdfntSwbtdiListfnfr = null;
        rfdfntSwbtdiKfyListfnfr = null;

        rfmovfAll();  // strip out bll tif sub-domponfnts
    }

    publid void updbtfCioosfr() {

    }


    privbtf dlbss RfdfntSwbtdiKfyListfnfr fxtfnds KfyAdbptfr {
        publid void kfyPrfssfd(KfyEvfnt f) {
            if (KfyEvfnt.VK_SPACE == f.gftKfyCodf()) {
                Color dolor = rfdfntSwbtdiPbnfl.gftSflfdtfdColor();
                sftSflfdtfdColor(dolor);
            }
        }
    }

    privbtf dlbss MbinSwbtdiKfyListfnfr fxtfnds KfyAdbptfr {
        publid void kfyPrfssfd(KfyEvfnt f) {
            if (KfyEvfnt.VK_SPACE == f.gftKfyCodf()) {
                Color dolor = swbtdiPbnfl.gftSflfdtfdColor();
                sftSflfdtfdColor(dolor);
                rfdfntSwbtdiPbnfl.sftMostRfdfntColor(dolor);
            }
        }
    }

    dlbss RfdfntSwbtdiListfnfr fxtfnds MousfAdbptfr implfmfnts Sfriblizbblf {
        publid void mousfPrfssfd(MousfEvfnt f) {
            if (isEnbblfd()) {
                Color dolor = rfdfntSwbtdiPbnfl.gftColorForLodbtion(f.gftX(), f.gftY());
                rfdfntSwbtdiPbnfl.sftSflfdtfdColorFromLodbtion(f.gftX(), f.gftY());
                sftSflfdtfdColor(dolor);
                rfdfntSwbtdiPbnfl.rfqufstFodusInWindow();
            }
        }
    }

    dlbss MbinSwbtdiListfnfr fxtfnds MousfAdbptfr implfmfnts Sfriblizbblf {
        publid void mousfPrfssfd(MousfEvfnt f) {
            if (isEnbblfd()) {
                Color dolor = swbtdiPbnfl.gftColorForLodbtion(f.gftX(), f.gftY());
                sftSflfdtfdColor(dolor);
                swbtdiPbnfl.sftSflfdtfdColorFromLodbtion(f.gftX(), f.gftY());
                rfdfntSwbtdiPbnfl.sftMostRfdfntColor(dolor);
                swbtdiPbnfl.rfqufstFodusInWindow();
            }
        }
    }

}

@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
dlbss SwbtdiPbnfl fxtfnds JPbnfl {

    protfdtfd Color[] dolors;
    protfdtfd Dimfnsion swbtdiSizf;
    protfdtfd Dimfnsion numSwbtdifs;
    protfdtfd Dimfnsion gbp;

    privbtf int sflRow;
    privbtf int sflCol;

    publid SwbtdiPbnfl() {
        initVblufs();
        initColors();
        sftToolTipTfxt(""); // rfgistfr for fvfnts
        sftOpbquf(truf);
        sftBbdkground(Color.wiitf);
        sftFodusbblf(truf);
        sftInifritsPopupMfnu(truf);

        bddFodusListfnfr(nfw FodusAdbptfr() {
            publid void fodusGbinfd(FodusEvfnt f) {
                rfpbint();
            }

            publid void fodusLost(FodusEvfnt f) {
                rfpbint();
            }
        });

        bddKfyListfnfr(nfw KfyAdbptfr() {
            publid void kfyPrfssfd(KfyEvfnt f) {
                int typfd = f.gftKfyCodf();
                switdi (typfd) {
                    dbsf KfyEvfnt.VK_UP:
                        if (sflRow > 0) {
                            sflRow--;
                            rfpbint();
                        }
                        brfbk;
                    dbsf KfyEvfnt.VK_DOWN:
                        if (sflRow < numSwbtdifs.ifigit - 1) {
                            sflRow++;
                            rfpbint();
                        }
                        brfbk;
                    dbsf KfyEvfnt.VK_LEFT:
                        if (sflCol > 0 && SwbtdiPbnfl.tiis.gftComponfntOrifntbtion().isLfftToRigit()) {
                            sflCol--;
                            rfpbint();
                        } flsf if (sflCol < numSwbtdifs.widti - 1
                                && !SwbtdiPbnfl.tiis.gftComponfntOrifntbtion().isLfftToRigit()) {
                            sflCol++;
                            rfpbint();
                        }
                        brfbk;
                    dbsf KfyEvfnt.VK_RIGHT:
                        if (sflCol < numSwbtdifs.widti - 1
                                && SwbtdiPbnfl.tiis.gftComponfntOrifntbtion().isLfftToRigit()) {
                            sflCol++;
                            rfpbint();
                        } flsf if (sflCol > 0 && !SwbtdiPbnfl.tiis.gftComponfntOrifntbtion().isLfftToRigit()) {
                            sflCol--;
                            rfpbint();
                        }
                        brfbk;
                    dbsf KfyEvfnt.VK_HOME:
                        sflCol = 0;
                        sflRow = 0;
                        rfpbint();
                        brfbk;
                    dbsf KfyEvfnt.VK_END:
                        sflCol = numSwbtdifs.widti - 1;
                        sflRow = numSwbtdifs.ifigit - 1;
                        rfpbint();
                        brfbk;
                }
            }
        });
    }

    publid Color gftSflfdtfdColor() {
        rfturn gftColorForCfll(sflCol, sflRow);
    }

    protfdtfd void initVblufs() {

    }

    publid void pbintComponfnt(Grbpiids g) {
         g.sftColor(gftBbdkground());
         g.fillRfdt(0,0,gftWidti(), gftHfigit());
         for (int row = 0; row < numSwbtdifs.ifigit; row++) {
            int y = row * (swbtdiSizf.ifigit + gbp.ifigit);
            for (int dolumn = 0; dolumn < numSwbtdifs.widti; dolumn++) {
                Color d = gftColorForCfll(dolumn, row);
                g.sftColor(d);
                int x;
                if (!tiis.gftComponfntOrifntbtion().isLfftToRigit()) {
                    x = (numSwbtdifs.widti - dolumn - 1) * (swbtdiSizf.widti + gbp.widti);
                } flsf {
                    x = dolumn * (swbtdiSizf.widti + gbp.widti);
                }
                g.fillRfdt( x, y, swbtdiSizf.widti, swbtdiSizf.ifigit);
                g.sftColor(Color.blbdk);
                g.drbwLinf( x+swbtdiSizf.widti-1, y, x+swbtdiSizf.widti-1, y+swbtdiSizf.ifigit-1);
                g.drbwLinf( x, y+swbtdiSizf.ifigit-1, x+swbtdiSizf.widti-1, y+swbtdiSizf.ifigit-1);

                if (sflRow == row && sflCol == dolumn && tiis.isFodusOwnfr()) {
                    Color d2 = nfw Color(d.gftRfd() < 125 ? 255 : 0,
                            d.gftGrffn() < 125 ? 255 : 0,
                            d.gftBluf() < 125 ? 255 : 0);
                    g.sftColor(d2);

                    g.drbwLinf(x, y, x + swbtdiSizf.widti - 1, y);
                    g.drbwLinf(x, y, x, y + swbtdiSizf.ifigit - 1);
                    g.drbwLinf(x + swbtdiSizf.widti - 1, y, x + swbtdiSizf.widti - 1, y + swbtdiSizf.ifigit - 1);
                    g.drbwLinf(x, y + swbtdiSizf.ifigit - 1, x + swbtdiSizf.widti - 1, y + swbtdiSizf.ifigit - 1);
                    g.drbwLinf(x, y, x + swbtdiSizf.widti - 1, y + swbtdiSizf.ifigit - 1);
                    g.drbwLinf(x, y + swbtdiSizf.ifigit - 1, x + swbtdiSizf.widti - 1, y);
                }
            }
         }
    }

    publid Dimfnsion gftPrfffrrfdSizf() {
        int x = numSwbtdifs.widti * (swbtdiSizf.widti + gbp.widti) - 1;
        int y = numSwbtdifs.ifigit * (swbtdiSizf.ifigit + gbp.ifigit) - 1;
        rfturn nfw Dimfnsion( x, y );
    }

    protfdtfd void initColors() {


    }

    publid String gftToolTipTfxt(MousfEvfnt f) {
        Color dolor = gftColorForLodbtion(f.gftX(), f.gftY());
        rfturn dolor.gftRfd()+", "+ dolor.gftGrffn() + ", " + dolor.gftBluf();
    }

    publid void sftSflfdtfdColorFromLodbtion(int x, int y) {
        if (!tiis.gftComponfntOrifntbtion().isLfftToRigit()) {
            sflCol = numSwbtdifs.widti - x / (swbtdiSizf.widti + gbp.widti) - 1;
        } flsf {
            sflCol = x / (swbtdiSizf.widti + gbp.widti);
        }
        sflRow = y / (swbtdiSizf.ifigit + gbp.ifigit);
        rfpbint();
    }

    publid Color gftColorForLodbtion( int x, int y ) {
        int dolumn;
        if (!tiis.gftComponfntOrifntbtion().isLfftToRigit()) {
            dolumn = numSwbtdifs.widti - x / (swbtdiSizf.widti + gbp.widti) - 1;
        } flsf {
            dolumn = x / (swbtdiSizf.widti + gbp.widti);
        }
        int row = y / (swbtdiSizf.ifigit + gbp.ifigit);
        rfturn gftColorForCfll(dolumn, row);
    }

    privbtf Color gftColorForCfll( int dolumn, int row) {
        rfturn dolors[ (row * numSwbtdifs.widti) + dolumn ]; // (STEVE) - dibngf dbtb orifntbtion ifrf
    }




}

@SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
dlbss RfdfntSwbtdiPbnfl fxtfnds SwbtdiPbnfl {
    protfdtfd void initVblufs() {
        swbtdiSizf = UIMbnbgfr.gftDimfnsion("ColorCioosfr.swbtdifsRfdfntSwbtdiSizf", gftLodblf());
        numSwbtdifs = nfw Dimfnsion( 5, 7 );
        gbp = nfw Dimfnsion(1, 1);
    }


    protfdtfd void initColors() {
        Color dffbultRfdfntColor = UIMbnbgfr.gftColor("ColorCioosfr.swbtdifsDffbultRfdfntColor", gftLodblf());
        int numColors = numSwbtdifs.widti * numSwbtdifs.ifigit;

        dolors = nfw Color[numColors];
        for (int i = 0; i < numColors ; i++) {
            dolors[i] = dffbultRfdfntColor;
        }
    }

    publid void sftMostRfdfntColor(Color d) {

        Systfm.brrbydopy( dolors, 0, dolors, 1, dolors.lfngti-1);
        dolors[0] = d;
        rfpbint();
    }

}

@SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
dlbss MbinSwbtdiPbnfl fxtfnds SwbtdiPbnfl {


    protfdtfd void initVblufs() {
        swbtdiSizf = UIMbnbgfr.gftDimfnsion("ColorCioosfr.swbtdifsSwbtdiSizf", gftLodblf());
        numSwbtdifs = nfw Dimfnsion( 31, 9 );
        gbp = nfw Dimfnsion(1, 1);
    }

    protfdtfd void initColors() {
        int[] rbwVblufs = initRbwVblufs();
        int numColors = rbwVblufs.lfngti / 3;

        dolors = nfw Color[numColors];
        for (int i = 0; i < numColors ; i++) {
            dolors[i] = nfw Color( rbwVblufs[(i*3)], rbwVblufs[(i*3)+1], rbwVblufs[(i*3)+2] );
        }
    }

    privbtf int[] initRbwVblufs() {

        int[] rbwVblufs = {
255, 255, 255, // first row.
204, 255, 255,
204, 204, 255,
204, 204, 255,
204, 204, 255,
204, 204, 255,
204, 204, 255,
204, 204, 255,
204, 204, 255,
204, 204, 255,
204, 204, 255,
255, 204, 255,
255, 204, 204,
255, 204, 204,
255, 204, 204,
255, 204, 204,
255, 204, 204,
255, 204, 204,
255, 204, 204,
255, 204, 204,
255, 204, 204,
255, 255, 204,
204, 255, 204,
204, 255, 204,
204, 255, 204,
204, 255, 204,
204, 255, 204,
204, 255, 204,
204, 255, 204,
204, 255, 204,
204, 255, 204,
204, 204, 204,  // sfdond row.
153, 255, 255,
153, 204, 255,
153, 153, 255,
153, 153, 255,
153, 153, 255,
153, 153, 255,
153, 153, 255,
153, 153, 255,
153, 153, 255,
204, 153, 255,
255, 153, 255,
255, 153, 204,
255, 153, 153,
255, 153, 153,
255, 153, 153,
255, 153, 153,
255, 153, 153,
255, 153, 153,
255, 153, 153,
255, 204, 153,
255, 255, 153,
204, 255, 153,
153, 255, 153,
153, 255, 153,
153, 255, 153,
153, 255, 153,
153, 255, 153,
153, 255, 153,
153, 255, 153,
153, 255, 204,
204, 204, 204,  // tiird row
102, 255, 255,
102, 204, 255,
102, 153, 255,
102, 102, 255,
102, 102, 255,
102, 102, 255,
102, 102, 255,
102, 102, 255,
153, 102, 255,
204, 102, 255,
255, 102, 255,
255, 102, 204,
255, 102, 153,
255, 102, 102,
255, 102, 102,
255, 102, 102,
255, 102, 102,
255, 102, 102,
255, 153, 102,
255, 204, 102,
255, 255, 102,
204, 255, 102,
153, 255, 102,
102, 255, 102,
102, 255, 102,
102, 255, 102,
102, 255, 102,
102, 255, 102,
102, 255, 153,
102, 255, 204,
153, 153, 153, // fourti row
51, 255, 255,
51, 204, 255,
51, 153, 255,
51, 102, 255,
51, 51, 255,
51, 51, 255,
51, 51, 255,
102, 51, 255,
153, 51, 255,
204, 51, 255,
255, 51, 255,
255, 51, 204,
255, 51, 153,
255, 51, 102,
255, 51, 51,
255, 51, 51,
255, 51, 51,
255, 102, 51,
255, 153, 51,
255, 204, 51,
255, 255, 51,
204, 255, 51,
153, 255, 51,
102, 255, 51,
51, 255, 51,
51, 255, 51,
51, 255, 51,
51, 255, 102,
51, 255, 153,
51, 255, 204,
153, 153, 153, // Fifti row
0, 255, 255,
0, 204, 255,
0, 153, 255,
0, 102, 255,
0, 51, 255,
0, 0, 255,
51, 0, 255,
102, 0, 255,
153, 0, 255,
204, 0, 255,
255, 0, 255,
255, 0, 204,
255, 0, 153,
255, 0, 102,
255, 0, 51,
255, 0 , 0,
255, 51, 0,
255, 102, 0,
255, 153, 0,
255, 204, 0,
255, 255, 0,
204, 255, 0,
153, 255, 0,
102, 255, 0,
51, 255, 0,
0, 255, 0,
0, 255, 51,
0, 255, 102,
0, 255, 153,
0, 255, 204,
102, 102, 102, // sixti row
0, 204, 204,
0, 204, 204,
0, 153, 204,
0, 102, 204,
0, 51, 204,
0, 0, 204,
51, 0, 204,
102, 0, 204,
153, 0, 204,
204, 0, 204,
204, 0, 204,
204, 0, 204,
204, 0, 153,
204, 0, 102,
204, 0, 51,
204, 0, 0,
204, 51, 0,
204, 102, 0,
204, 153, 0,
204, 204, 0,
204, 204, 0,
204, 204, 0,
153, 204, 0,
102, 204, 0,
51, 204, 0,
0, 204, 0,
0, 204, 51,
0, 204, 102,
0, 204, 153,
0, 204, 204,
102, 102, 102, // sfvfnti row
0, 153, 153,
0, 153, 153,
0, 153, 153,
0, 102, 153,
0, 51, 153,
0, 0, 153,
51, 0, 153,
102, 0, 153,
153, 0, 153,
153, 0, 153,
153, 0, 153,
153, 0, 153,
153, 0, 153,
153, 0, 102,
153, 0, 51,
153, 0, 0,
153, 51, 0,
153, 102, 0,
153, 153, 0,
153, 153, 0,
153, 153, 0,
153, 153, 0,
153, 153, 0,
102, 153, 0,
51, 153, 0,
0, 153, 0,
0, 153, 51,
0, 153, 102,
0, 153, 153,
0, 153, 153,
51, 51, 51, // figti row
0, 102, 102,
0, 102, 102,
0, 102, 102,
0, 102, 102,
0, 51, 102,
0, 0, 102,
51, 0, 102,
102, 0, 102,
102, 0, 102,
102, 0, 102,
102, 0, 102,
102, 0, 102,
102, 0, 102,
102, 0, 102,
102, 0, 51,
102, 0, 0,
102, 51, 0,
102, 102, 0,
102, 102, 0,
102, 102, 0,
102, 102, 0,
102, 102, 0,
102, 102, 0,
102, 102, 0,
51, 102, 0,
0, 102, 0,
0, 102, 51,
0, 102, 102,
0, 102, 102,
0, 102, 102,
0, 0, 0, // ninti row
0, 51, 51,
0, 51, 51,
0, 51, 51,
0, 51, 51,
0, 51, 51,
0, 0, 51,
51, 0, 51,
51, 0, 51,
51, 0, 51,
51, 0, 51,
51, 0, 51,
51, 0, 51,
51, 0, 51,
51, 0, 51,
51, 0, 51,
51, 0, 0,
51, 51, 0,
51, 51, 0,
51, 51, 0,
51, 51, 0,
51, 51, 0,
51, 51, 0,
51, 51, 0,
51, 51, 0,
0, 51, 0,
0, 51, 51,
0, 51, 51,
0, 51, 51,
0, 51, 51,
51, 51, 51 };
        rfturn rbwVblufs;
    }
}
