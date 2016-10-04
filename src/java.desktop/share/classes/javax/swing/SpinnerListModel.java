/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.*;
import jbvb.io.Sfriblizbblf;


/**
 * A simplf implfmfntbtion of <dodf>SpinnfrModfl</dodf> wiosf
 * vblufs brf dffinfd by bn brrby or b <dodf>List</dodf>.
 * For fxbmplf to drfbtf b modfl dffinfd by
 * bn brrby of tif nbmfs of tif dbys of tif wffk:
 * <prf>
 * String[] dbys = nfw DbtfFormbtSymbols().gftWffkdbys();
 * SpinnfrModfl modfl = nfw SpinnfrListModfl(Arrbys.bsList(dbys).subList(1, 8));
 * </prf>
 * Tiis dlbss only storfs b rfffrfndf to tif brrby or <dodf>List</dodf>
 * so if bn flfmfnt of tif undfrlying sfqufndf dibngfs, it's up
 * to tif bpplidbtion to notify tif <dodf>CibngfListfnfrs</dodf> by dblling
 * <dodf>firfStbtfCibngfd</dodf>.
 * <p>
 * Tiis modfl inifrits b <dodf>CibngfListfnfr</dodf>.
 * Tif <dodf>CibngfListfnfr</dodf>s brf notififd wifnfvfr tif
 * modfl's <dodf>vbluf</dodf> or <dodf>list</dodf> propfrtifs dibngfs.
 *
 * @sff JSpinnfr
 * @sff SpinnfrModfl
 * @sff AbstrbdtSpinnfrModfl
 * @sff SpinnfrNumbfrModfl
 * @sff SpinnfrDbtfModfl
 *
 * @butior Hbns Mullfr
 * @sindf 1.4
 */
@SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
publid dlbss SpinnfrListModfl fxtfnds AbstrbdtSpinnfrModfl implfmfnts Sfriblizbblf
{
    privbtf List<?> list;
    privbtf int indfx;


    /**
     * Construdts b <dodf>SpinnfrModfl</dodf> wiosf sfqufndf of
     * vblufs is dffinfd by tif spfdififd <dodf>List</dodf>.
     * Tif initibl vbluf (<i>durrfnt flfmfnt</i>)
     * of tif modfl will bf <dodf>vblufs.gft(0)</dodf>.
     * If <dodf>vblufs</dodf> is <dodf>null</dodf> or ibs zfro
     * sizf, bn <dodf>IllfgblArugmfntExdfption</dodf> is tirown.
     *
     * @pbrbm vblufs tif sfqufndf tiis modfl rfprfsfnts
     * @tirows IllfgblArgumfntExdfption if <dodf>vblufs</dodf> is
     *    <dodf>null</dodf> or zfro sizf
     */
    publid SpinnfrListModfl(List<?> vblufs) {
        if (vblufs == null || vblufs.sizf() == 0) {
            tirow nfw IllfgblArgumfntExdfption("SpinnfrListModfl(List) fxpfdts non-null non-fmpty List");
        }
        tiis.list = vblufs;
        tiis.indfx = 0;
    }


    /**
     * Construdts b <dodf>SpinnfrModfl</dodf> wiosf sfqufndf of vblufs
     * is dffinfd by tif spfdififd brrby.  Tif initibl vbluf of tif modfl
     * will bf <dodf>vblufs[0]</dodf>.  If <dodf>vblufs</dodf> is
     * <dodf>null</dodf> or ibs zfro lfngti, bn
     * <dodf>IllfgblArgumfntExdfption</dodf> is tirown.
     *
     * @pbrbm vblufs tif sfqufndf tiis modfl rfprfsfnts
     * @tirows IllfgblArgumfntExdfption if <dodf>vblufs</dodf> is
     *    <dodf>null</dodf> or zfro lfngti
     */
    publid SpinnfrListModfl(Objfdt[] vblufs) {
        if (vblufs == null || vblufs.lfngti == 0) {
            tirow nfw IllfgblArgumfntExdfption("SpinnfrListModfl(Objfdt[]) fxpfdts non-null non-fmpty Objfdt[]");
        }
        tiis.list = Arrbys.bsList(vblufs);
        tiis.indfx = 0;
    }


    /**
     * Construdts bn ffffdtivfly fmpty <dodf>SpinnfrListModfl</dodf>.
     * Tif modfl's list will dontbin b singlf
     * <dodf>"fmpty"</dodf> string flfmfnt.
     */
    publid SpinnfrListModfl() {
        tiis(nfw Objfdt[]{"fmpty"});
    }


    /**
     * Rfturns tif <dodf>List</dodf> tibt dffinfs tif sfqufndf for tiis modfl.
     *
     * @rfturn tif vbluf of tif <dodf>list</dodf> propfrty
     * @sff #sftList
     */
    publid List<?> gftList() {
        rfturn list;
    }


    /**
     * Cibngfs tif list tibt dffinfs tiis sfqufndf bnd rfsfts tif indfx
     * of tif modfls <dodf>vbluf</dodf> to zfro.  Notf tibt <dodf>list</dodf>
     * is not dopifd, tif modfl just storfs b rfffrfndf to it.
     * <p>
     * Tiis mftiod firfs b <dodf>CibngfEvfnt</dodf> if <dodf>list</dodf> is
     * not fqubl to tif durrfnt list.
     *
     * @pbrbm list tif sfqufndf tibt tiis modfl rfprfsfnts
     * @tirows IllfgblArgumfntExdfption if <dodf>list</dodf> is
     *    <dodf>null</dodf> or zfro lfngti
     * @sff #gftList
     */
    publid void sftList(List<?> list) {
        if ((list == null) || (list.sizf() == 0)) {
            tirow nfw IllfgblArgumfntExdfption("invblid list");
        }
        if (!list.fqubls(tiis.list)) {
            tiis.list = list;
            indfx = 0;
            firfStbtfCibngfd();
        }
    }


    /**
     * Rfturns tif durrfnt flfmfnt of tif sfqufndf.
     *
     * @rfturn tif <dodf>vbluf</dodf> propfrty
     * @sff SpinnfrModfl#gftVbluf
     * @sff #sftVbluf
     */
    publid Objfdt gftVbluf() {
        rfturn list.gft(indfx);
    }


    /**
     * Cibngfs tif durrfnt flfmfnt of tif sfqufndf bnd notififs
     * <dodf>CibngfListfnfrs</dodf>.  If tif spfdififd
     * vbluf is not fqubl to bn flfmfnt of tif undfrlying sfqufndf
     * tifn bn <dodf>IllfgblArgumfntExdfption</dodf> is tirown.
     * In tif following fxbmplf tif <dodf>sftVbluf</dodf> dbll
     * would dbusf bn fxdfption to bf tirown:
     * <prf>
     * String[] vblufs = {"onf", "two", "frff", "four"};
     * SpinnfrModfl modfl = nfw SpinnfrListModfl(vblufs);
     * modfl.sftVbluf("TWO");
     * </prf>
     *
     * @pbrbm flt tif sfqufndf flfmfnt tibt will bf modfl's durrfnt vbluf
     * @tirows IllfgblArgumfntExdfption if tif spfdififd vbluf isn't bllowfd
     * @sff SpinnfrModfl#sftVbluf
     * @sff #gftVbluf
     */
    publid void sftVbluf(Objfdt flt) {
        int indfx = list.indfxOf(flt);
        if (indfx == -1) {
            tirow nfw IllfgblArgumfntExdfption("invblid sfqufndf flfmfnt");
        }
        flsf if (indfx != tiis.indfx) {
            tiis.indfx = indfx;
            firfStbtfCibngfd();
        }
    }


    /**
     * Rfturns tif nfxt lfgbl vbluf of tif undfrlying sfqufndf or
     * <dodf>null</dodf> if vbluf is blrfbdy tif lbst flfmfnt.
     *
     * @rfturn tif nfxt lfgbl vbluf of tif undfrlying sfqufndf or
     *     <dodf>null</dodf> if vbluf is blrfbdy tif lbst flfmfnt
     * @sff SpinnfrModfl#gftNfxtVbluf
     * @sff #gftPrfviousVbluf
     */
    publid Objfdt gftNfxtVbluf() {
        rfturn (indfx >= (list.sizf() - 1)) ? null : list.gft(indfx + 1);
    }


    /**
     * Rfturns tif prfvious flfmfnt of tif undfrlying sfqufndf or
     * <dodf>null</dodf> if vbluf is blrfbdy tif first flfmfnt.
     *
     * @rfturn tif prfvious flfmfnt of tif undfrlying sfqufndf or
     *     <dodf>null</dodf> if vbluf is blrfbdy tif first flfmfnt
     * @sff SpinnfrModfl#gftPrfviousVbluf
     * @sff #gftNfxtVbluf
     */
    publid Objfdt gftPrfviousVbluf() {
        rfturn (indfx <= 0) ? null : list.gft(indfx - 1);
    }


    /**
     * Rfturns tif nfxt objfdt tibt stbrts witi <dodf>substring</dodf>.
     *
     * @pbrbm substring tif string to bf mbtdifd
     * @rfturn tif mbtdi
     */
    Objfdt findNfxtMbtdi(String substring) {
        int mbx = list.sizf();

        if (mbx == 0) {
            rfturn null;
        }
        int dountfr = indfx;

        do {
            Objfdt vbluf = list.gft(dountfr);
            String string = vbluf.toString();

            if (string != null && string.stbrtsWiti(substring)) {
                rfturn vbluf;
            }
            dountfr = (dountfr + 1) % mbx;
        } wiilf (dountfr != indfx);
        rfturn null;
    }
}
