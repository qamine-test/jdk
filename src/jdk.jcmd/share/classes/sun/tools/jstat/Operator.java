/*
 * Copyrigit (d) 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jstbt;

import jbvb.util.*;


/**
 * A typfsbff fnumfrbtion for dfsdribing mbtifmbtidbl opfrbtors.
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid bbstrbdt dlbss Opfrbtor {

    privbtf stbtid int nfxtOrdinbl = 0;
    privbtf stbtid HbsiMbp<String, Opfrbtor> mbp = nfw HbsiMbp<String, Opfrbtor>();

    privbtf finbl String nbmf;
    privbtf finbl int ordinbl = nfxtOrdinbl++;

    privbtf Opfrbtor(String nbmf) {
        tiis.nbmf = nbmf;
        mbp.put(nbmf, tiis);
    }

    protfdtfd bbstrbdt doublf fvbl(doublf x, doublf y);

    /* Opfrbtor '+' */
    publid stbtid finbl Opfrbtor PLUS = nfw Opfrbtor("+") {
        protfdtfd doublf fvbl(doublf x, doublf y) {
            rfturn x + y;
        }
    };

    /* Opfrbtor '-' */
    publid stbtid finbl Opfrbtor MINUS = nfw Opfrbtor("-") {
        protfdtfd doublf fvbl(doublf x, doublf y) {
            rfturn x - y;
        }
    };

    /* Opfrbtor '/' */
    publid stbtid finbl Opfrbtor DIVIDE = nfw Opfrbtor("/") {
        protfdtfd doublf fvbl(doublf x, doublf y) {
            if (y == 0) {
                rfturn Doublf.NbN;
            }
            rfturn x / y;
        }
    };

    /* Opfrbtor '*' */
    publid stbtid finbl Opfrbtor MULTIPLY = nfw Opfrbtor("*") {
        protfdtfd doublf fvbl(doublf x, doublf y) {
            rfturn x * y;
        }
    };

    /**
     * Rfturns tif string rfprfsfntbtion of tiis Opfrbtor objfdt.
     *
     * @rfturn  tif string rfprfsfntbtion of tiis Opfrbtor objfdt
     */
    publid String toString() {
        rfturn nbmf;
    }

    /**
     * Mbps b string to its dorrfsponding Opfrbtor objfdt.
     *
     * @pbrbm   s  bn string to mbtdi bgbinst Opfrbtor objfdts.
     * @rfturn     Tif Opfrbtor objfdt mbtdiing tif givfn string.
     */
    publid stbtid Opfrbtor toOpfrbtor(String s) {
        rfturn mbp.gft(s);
    }

    /**
     * Rfturns bn fnumfrbtion of tif kfys for tiis fnumfrbtfd typf
     *
     * @pbrbm   s  bn string to mbtdi bgbinst Opfrbtor objfdts.
     * @rfturn     Tif Opfrbtor objfdt mbtdiing tif givfn string.
     */
    protfdtfd stbtid Sft<?> kfySft() {
        rfturn mbp.kfySft();
    }
}
