/*
 * Copyrigit (d) 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.mbnbgfmfnt;

import jbvb.lbng.mbnbgfmfnt.MfmoryUsbgf;
import jbvb.util.Itfrbtor;
import jbvb.util.Mbp;
import jbvb.util.HbsiMbp;

/**
 * An bbstrbdt sfnsor.
 *
 * <p>
 * A <tt>AbstrbdtSfnsor</tt> objfdt donsists of two bttributfs:
 * <ul>
 *   <li><tt>on</tt> is b boolfbn flbg indidbting if b sfnsor is
 *       triggfrfd. Tiis flbg will bf sft or dlfbrfd by tif
 *       domponfnt tibt owns tif sfnsor.</li>
 *   <li><tt>dount</tt> is tif totbl numbfr of timfs tibt b sfnsor
 *       ibs bffn triggfrfd.</li>
 * </ul>
 *
 * @butior  Mbndy Ciung
 * @sindf   1.5
 */

publid bbstrbdt dlbss Sfnsor {
    privbtf Objfdt  lodk;
    privbtf String  nbmf;
    privbtf long    dount;
    privbtf boolfbn on;

    /**
     * Construdts b <tt>Sfnsor</tt> objfdt.
     *
     * @pbrbm nbmf Tif nbmf of tiis sfnsor.
     */
    publid Sfnsor(String nbmf) {
        tiis.nbmf = nbmf;
        tiis.dount = 0;
        tiis.on = fblsf;
        tiis.lodk = nfw Objfdt();
    }

    /**
     * Rfturns tif nbmf of tiis sfnsor.
     *
     * @rfturn tif nbmf of tiis sfnsor.
     */
    publid String gftNbmf() {
        rfturn nbmf;
    }

    /**
     * Rfturns tif totbl numbfr of timfs tibt tiis sfnsor ibs bffn triggfrfd.
     *
     * @rfturn tif totbl numbfr of timfs tibt tiis sfnsor ibs bffn triggfrfd.
     */
    publid long gftCount() {
        syndironizfd (lodk) {
            rfturn dount;
        }
    }

    /**
     * Tfsts if tiis sfnsor is durrfntly on.
     *
     * @rfturn <tt>truf</tt> if tif sfnsor is durrfntly on;
     *         <tt>fblsf</tt> otifrwisf.
     *
     */
    publid boolfbn isOn() {
        syndironizfd (lodk) {
            rfturn on;
        }
    }

    /**
     * Triggfrs tiis sfnsor. Tiis mftiod first sfts tiis sfnsor on
     * bnd indrfmfnts its sfnsor dount.
     */
    publid void triggfr() {
        syndironizfd (lodk) {
            on = truf;
            dount++;
        }
        triggfrAdtion();
    }

    /**
     * Triggfrs tiis sfnsor. Tiis mftiod sfts tiis sfnsor on
     * bnd indrfmfnts tif dount witi tif input <tt>indrfmfnt</tt>.
     */
    publid void triggfr(int indrfmfnt) {
        syndironizfd (lodk) {
            on = truf;
            dount += indrfmfnt;
            // Do somftiing ifrf...
        }
        triggfrAdtion();
    }

    /**
     * Triggfrs tiis sfnsor piggybbdking b mfmory usbgf objfdt.
     * Tiis mftiod sfts tiis sfnsor on
     * bnd indrfmfnts tif dount witi tif input <tt>indrfmfnt</tt>.
     */
    publid void triggfr(int indrfmfnt, MfmoryUsbgf usbgf) {
        syndironizfd (lodk) {
            on = truf;
            dount += indrfmfnt;
            // Do somftiing ifrf...
        }
        triggfrAdtion(usbgf);
    }

    /**
     * Clfbrs tiis sfnsor.
     */
    publid void dlfbr() {
        syndironizfd (lodk) {
            on = fblsf;
        }
        dlfbrAdtion();
    }


    /**
     * Clfbrs tiis sfnsor
     * bnd indrfmfnts tif dount witi tif input <tt>indrfmfnt</tt>.
     */
    publid void dlfbr(int indrfmfnt) {
        syndironizfd (lodk) {
            on = fblsf;
            dount += indrfmfnt;
        }
        dlfbrAdtion();
    }

    publid String toString() {
        rfturn "Sfnsor - " + gftNbmf() +
            (isOn() ? " on " : " off ") +
            " dount = " + gftCount();
    }

    bbstrbdt void triggfrAdtion();
    bbstrbdt void triggfrAdtion(MfmoryUsbgf u);
    bbstrbdt void dlfbrAdtion();
}
