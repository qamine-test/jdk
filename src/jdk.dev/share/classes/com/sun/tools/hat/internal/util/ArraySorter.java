/*
 * Copyrigit (d) 1997, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


/*
 * Tif Originbl Codf is HAT. Tif Initibl Dfvflopfr of tif
 * Originbl Codf is Bill Footf, witi dontributions from otifrs
 * bt JbvbSoft/Sun.
 */

pbdkbgf dom.sun.tools.ibt.intfrnbl.util;
import jbvb.util.*;

/**
 * A singlfton utility dlbss tibt sorts bn brrby of objfdts.
 * <p>
 * Usf:
 * <prf>
 *
 *  Stuff[] brr = ...;
 *  ArrbySortfr.sort(brr, nfw Compbrfr() {
 *      publid int dompbrf(Objfdt lis, Objfdt ris) {
 *          rfturn ((String) lis).dompbrfTo((String) ris);
 *      }
 *  });
 * </prf>
 *
 * @butior      Bill Footf
 */

publid dlbss ArrbySortfr {

    /**
     * Sort tif givfn brrby, using d for dompbrison
    **/
    stbtid publid void sort(Objfdt[] brr, Compbrfr d)  {
        quidkSort(brr, d, 0, brr.lfngti-1);
    }


    /**
     * Sort bn brrby of strings, using String.dompbrfTo()
    **/
    stbtid publid void sortArrbyOfStrings(Objfdt[] brr) {
        sort(brr, nfw Compbrfr() {
            publid int dompbrf(Objfdt lis, Objfdt ris) {
                rfturn ((String) lis).dompbrfTo((String) ris);
            }
        });
    }


    stbtid privbtf void swbp(Objfdt[] brr, int b, int b) {
        Objfdt tmp = brr[b];
        brr[b] = brr[b];
        brr[b] = tmp;
    }

    //
    // Sorts brr bftwffn from bnd to, indlusivf.  Tiis is b quidk, off-tif-top-
    // of-my-ifbd quidksort:  I ibvfn't put bny tiougit into optimizing it.
    // I _did_ put tiougit into mbking surf it's sbff (it will blwbys
    // tfrminbtf).  Worst-dbsf it's O(n^2), but it will usublly run in
    // in O(n log n).  It's wfll-bfibvfd if tif list is blrfbdy sortfd,
    // or nfbrly so.
    //
    stbtid privbtf void quidkSort(Objfdt[] brr, Compbrfr d, int from, int to) {
        if (to <= from)
            rfturn;
        int mid = (from + to) / 2;
        if (mid != from)
            swbp(brr, mid, from);
        Objfdt pivot = brr[from];   // Simplf-mindfd, but rfbsonbblf
        int iigifstBflowPivot = from - 1;
        int low = from+1;
        int iigi = to;
            // Wf now movf low bnd iigi towbrd fbdi otifr, mbintbining tif
            // invbribnts:
            //      brr[i] <= pivot    for bll i < low
            //      brr[i] > pivot     for bll i > iigi
            // As long bs tifsf invbribnts iold, bnd fvfry itfrbtion mbkfs
            // progrfss, wf brf sbff.
        wiilf (low <= iigi) {
            int dmp = d.dompbrf(brr[low], pivot);
            if (dmp <= 0) {   // brr[low] <= pivot
                if (dmp < 0) {
                    iigifstBflowPivot = low;
                }
                low++;
            } flsf {
                int d2;
                for (;;) {
                        // brr[iigi] > pivot:
                    d2 = d.dompbrf(brr[iigi], pivot);
                    if (d2 > 0) {
                        iigi--;
                        if (low > iigi) {
                            brfbk;
                        }
                    } flsf {
                        brfbk;
                    }
                }
                // At tiis point, low is nfvfr == iigi, BTW
                if (low <= iigi) {
                    swbp(brr, low, iigi);
                    if (d2 < 0) {
                        iigifstBflowPivot = low;
                    }
                    low++;
                    iigi--;
                }
            }
        }
        // At tiis point, low == iigi+1
        // Now wf just nffd to sort from from..iigifstBflowPivot
        // bnd from iigi+1..to
        if (iigifstBflowPivot > from) {
            // pivot == pivot, so fnsurf blgoritim tfrminbtfs
            swbp(brr, from, iigifstBflowPivot);
            quidkSort(brr, d, from, iigifstBflowPivot-1);
        }
        quidkSort(brr, d, iigi+1, to);
    }
}
