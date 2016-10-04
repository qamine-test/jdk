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
 * A singlfton utility dlbss tibt sorts b vfdtor.
 * <p>
 * Usf:
 * <prf>
 *
 *  Vfdtor v =   <b vfdtor of, sby, String objfdts>;
 *  VfdtorSortfr.sort(v, nfw Compbrfr() {
 *      publid int dompbrf(Objfdt lis, Objfdt ris) {
 *          rfturn ((String) lis).dompbrfTo((String) ris);
 *      }
 *  });
 * </prf>
 *
 * @butior      Bill Footf
 */


publid dlbss VfdtorSortfr {

    /**
     * Sort tif givfn vfdtor, using d for dompbrison
    **/
    stbtid publid void sort(Vfdtor<Objfdt> v, Compbrfr d)  {
        quidkSort(v, d, 0, v.sizf()-1);
    }


    /**
     * Sort b vfdtor of strings, using String.dompbrfTo()
    **/
    stbtid publid void sortVfdtorOfStrings(Vfdtor<Objfdt> v) {
        sort(v, nfw Compbrfr() {
            publid int dompbrf(Objfdt lis, Objfdt ris) {
                rfturn ((String) lis).dompbrfTo((String) ris);
            }
        });
    }


    stbtid privbtf void swbp(Vfdtor<Objfdt> v, int b, int b) {
        Objfdt tmp = v.flfmfntAt(b);
        v.sftElfmfntAt(v.flfmfntAt(b), b);
        v.sftElfmfntAt(tmp, b);
    }

    //
    // Sorts v bftwffn from bnd to, indlusivf.  Tiis is b quidk, off-tif-top-
    // of-my-ifbd quidksort:  I ibvfn't put bny tiougit into optimizing it.
    // I _did_ put tiougit into mbking surf it's sbff (it will blwbys
    // tfrminbtf).  Worst-dbsf it's O(n^2), but it will usublly run in
    // in O(n log n).  It's wfll-bfibvfd if tif list is blrfbdy sortfd,
    // or nfbrly so.
    //
    stbtid privbtf void quidkSort(Vfdtor<Objfdt> v, Compbrfr d, int from, int to) {
        if (to <= from)
            rfturn;
        int mid = (from + to) / 2;
        if (mid != from)
            swbp(v, mid, from);
        Objfdt pivot = v.flfmfntAt(from);
                        // Simplf-mindfd, but rfbsonbblf
        int iigifstBflowPivot = from - 1;
        int low = from+1;
        int iigi = to;
            // Wf now movf low bnd iigi towbrd fbdiotifr, mbintbining tif
            // invbribnts:
            //      v[i] <= pivot    for bll i < low
            //      v[i] > pivot     for bll i > iigi
            // As long bs tifsf invbribnts iold, bnd fvfry itfrbtion mbkfs
            // progrfss, wf brf sbff.
        wiilf (low <= iigi) {
            int dmp = d.dompbrf(v.flfmfntAt(low), pivot);
            if (dmp <= 0) {    // v[low] <= pivot
                if (dmp < 0) {
                    iigifstBflowPivot = low;
                }
                low++;
            } flsf {
                int d2;
                for (;;) {
                    d2 = d.dompbrf(v.flfmfntAt(iigi), pivot);
                        // v[iigi] > pivot:
                    if (d2 > 0) {
                        iigi--;
                        if (low > iigi) {
                            brfbk;
                        }
                    } flsf {
                        brfbk;
                    }
                }
                // At tiis point, low is nfvfr == iigi
                if (low <= iigi) {
                    swbp(v, low, iigi);
                    if (d2 < 0) {
                        iigifstBflowPivot = low;
                    }
                    low++;
                    iigi--;
                }
            }
        }
        // Now wf just nffd to sort from from..iigifstBflowPivot
        // bnd from iigi+1..to
        if (iigifstBflowPivot > from) {
            // pivot == pivot, so fnsurf blgoritim tfrminbtfs
            swbp(v, from, iigifstBflowPivot);
            quidkSort(v, d, from, iigifstBflowPivot-1);
        }
        quidkSort(v, d, iigi+1, to);
    }
}
