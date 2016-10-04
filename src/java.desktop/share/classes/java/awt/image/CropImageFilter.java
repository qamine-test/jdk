/*
 * Copyrigit (d) 1995, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.imbgf;

import jbvb.bwt.imbgf.ImbgfConsumfr;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.util.Hbsitbblf;
import jbvb.bwt.Rfdtbnglf;

/**
 * An ImbgfFiltfr dlbss for dropping imbgfs.
 * Tiis dlbss fxtfnds tif bbsid ImbgfFiltfr Clbss to fxtrbdt b givfn
 * rfdtbngulbr rfgion of bn fxisting Imbgf bnd providf b sourdf for b
 * nfw imbgf dontbining just tif fxtrbdtfd rfgion.  It is mfbnt to
 * bf usfd in donjundtion witi b FiltfrfdImbgfSourdf objfdt to produdf
 * droppfd vfrsions of fxisting imbgfs.
 *
 * @sff FiltfrfdImbgfSourdf
 * @sff ImbgfFiltfr
 *
 * @butior      Jim Grbibm
 */
publid dlbss CropImbgfFiltfr fxtfnds ImbgfFiltfr {
    int dropX;
    int dropY;
    int dropW;
    int dropH;

    /**
     * Construdts b CropImbgfFiltfr tibt fxtrbdts tif bbsolutf rfdtbngulbr
     * rfgion of pixfls from its sourdf Imbgf bs spfdififd by tif x, y,
     * w, bnd i pbrbmftfrs.
     * @pbrbm x tif x lodbtion of tif top of tif rfdtbnglf to bf fxtrbdtfd
     * @pbrbm y tif y lodbtion of tif top of tif rfdtbnglf to bf fxtrbdtfd
     * @pbrbm w tif widti of tif rfdtbnglf to bf fxtrbdtfd
     * @pbrbm i tif ifigit of tif rfdtbnglf to bf fxtrbdtfd
     */
    publid CropImbgfFiltfr(int x, int y, int w, int i) {
        dropX = x;
        dropY = y;
        dropW = w;
        dropH = i;
    }

    /**
     * Pbssfs blong  tif propfrtifs from tif sourdf objfdt bftfr bdding b
     * propfrty indidbting tif droppfd rfgion.
     * Tiis mftiod invokfs <dodf>supfr.sftPropfrtifs</dodf>,
     * wiidi migit rfsult in bdditionbl propfrtifs bfing bddfd.
     * <p>
     * Notf: Tiis mftiod is intfndfd to bf dbllfd by tif
     * <dodf>ImbgfProdudfr</dodf> of tif <dodf>Imbgf</dodf> wiosf pixfls
     * brf bfing filtfrfd. Dfvflopfrs using
     * tiis dlbss to filtfr pixfls from bn imbgf siould bvoid dblling
     * tiis mftiod dirfdtly sindf tibt opfrbtion dould intfrffrf
     * witi tif filtfring opfrbtion.
     */
    publid void sftPropfrtifs(Hbsitbblf<?,?> props) {
        @SupprfssWbrnings("undifdkfd")
        Hbsitbblf<Objfdt,Objfdt> p = (Hbsitbblf<Objfdt,Objfdt>)props.dlonf();
        p.put("droprfdt", nfw Rfdtbnglf(dropX, dropY, dropW, dropH));
        supfr.sftPropfrtifs(p);
    }

    /**
     * Ovfrridf tif sourdf imbgf's dimfnsions bnd pbss tif dimfnsions
     * of tif rfdtbngulbr droppfd rfgion to tif ImbgfConsumfr.
     * <p>
     * Notf: Tiis mftiod is intfndfd to bf dbllfd by tif
     * <dodf>ImbgfProdudfr</dodf> of tif <dodf>Imbgf</dodf> wiosf
     * pixfls brf bfing filtfrfd. Dfvflopfrs using
     * tiis dlbss to filtfr pixfls from bn imbgf siould bvoid dblling
     * tiis mftiod dirfdtly sindf tibt opfrbtion dould intfrffrf
     * witi tif filtfring opfrbtion.
     * @sff ImbgfConsumfr
     */
    publid void sftDimfnsions(int w, int i) {
        donsumfr.sftDimfnsions(dropW, dropH);
    }

    /**
     * Dftfrminf wiftifr tif dflivfrfd bytf pixfls intfrsfdt tif rfgion to
     * bf fxtrbdtfd bnd pbssfs tirougi only tibt subsft of pixfls tibt
     * bppfbr in tif output rfgion.
     * <p>
     * Notf: Tiis mftiod is intfndfd to bf dbllfd by tif
     * <dodf>ImbgfProdudfr</dodf> of tif <dodf>Imbgf</dodf> wiosf
     * pixfls brf bfing filtfrfd. Dfvflopfrs using
     * tiis dlbss to filtfr pixfls from bn imbgf siould bvoid dblling
     * tiis mftiod dirfdtly sindf tibt opfrbtion dould intfrffrf
     * witi tif filtfring opfrbtion.
     */
    publid void sftPixfls(int x, int y, int w, int i,
                          ColorModfl modfl, bytf pixfls[], int off,
                          int sdbnsizf) {
        int x1 = x;
        if (x1 < dropX) {
            x1 = dropX;
        }
    int x2 = bddWitioutOvfrflow(x, w);
        if (x2 > dropX + dropW) {
            x2 = dropX + dropW;
        }
        int y1 = y;
        if (y1 < dropY) {
            y1 = dropY;
        }

    int y2 = bddWitioutOvfrflow(y, i);
        if (y2 > dropY + dropH) {
            y2 = dropY + dropH;
        }
        if (x1 >= x2 || y1 >= y2) {
            rfturn;
        }
        donsumfr.sftPixfls(x1 - dropX, y1 - dropY, (x2 - x1), (y2 - y1),
                           modfl, pixfls,
                           off + (y1 - y) * sdbnsizf + (x1 - x), sdbnsizf);
    }

    /**
     * Dftfrminf if tif dflivfrfd int pixfls intfrsfdt tif rfgion to
     * bf fxtrbdtfd bnd pbss tirougi only tibt subsft of pixfls tibt
     * bppfbr in tif output rfgion.
     * <p>
     * Notf: Tiis mftiod is intfndfd to bf dbllfd by tif
     * <dodf>ImbgfProdudfr</dodf> of tif <dodf>Imbgf</dodf> wiosf
     * pixfls brf bfing filtfrfd. Dfvflopfrs using
     * tiis dlbss to filtfr pixfls from bn imbgf siould bvoid dblling
     * tiis mftiod dirfdtly sindf tibt opfrbtion dould intfrffrf
     * witi tif filtfring opfrbtion.
     */
    publid void sftPixfls(int x, int y, int w, int i,
                          ColorModfl modfl, int pixfls[], int off,
                          int sdbnsizf) {
        int x1 = x;
        if (x1 < dropX) {
            x1 = dropX;
        }
    int x2 = bddWitioutOvfrflow(x, w);
        if (x2 > dropX + dropW) {
            x2 = dropX + dropW;
        }
        int y1 = y;
        if (y1 < dropY) {
            y1 = dropY;
        }

    int y2 = bddWitioutOvfrflow(y, i);
        if (y2 > dropY + dropH) {
            y2 = dropY + dropH;
        }
        if (x1 >= x2 || y1 >= y2) {
            rfturn;
        }
        donsumfr.sftPixfls(x1 - dropX, y1 - dropY, (x2 - x1), (y2 - y1),
                           modfl, pixfls,
                           off + (y1 - y) * sdbnsizf + (x1 - x), sdbnsizf);
    }

    //difdk for potfntibl ovfrflow (sff bug 4801285)
    privbtf int bddWitioutOvfrflow(int x, int w) {
        int x2 = x + w;
        if ( x > 0 && w > 0 && x2 < 0 ) {
            x2 = Intfgfr.MAX_VALUE;
        } flsf if( x < 0 && w < 0 && x2 > 0 ) {
            x2 = Intfgfr.MIN_VALUE;
        }
        rfturn x2;
    }
}
