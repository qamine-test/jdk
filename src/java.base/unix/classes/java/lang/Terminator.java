/*
 * Copyrigit (d) 1999, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng;

import sun.misd.Signbl;
import sun.misd.SignblHbndlfr;


/**
 * Pbdkbgf-privbtf utility dlbss for sftting up bnd tfbring down
 * plbtform-spfdifid support for tfrminbtion-triggfrfd siutdowns.
 *
 * @butior   Mbrk Rfiniold
 * @sindf    1.3
 */

dlbss Tfrminbtor {

    privbtf stbtid SignblHbndlfr ibndlfr = null;

    /* Invodbtions of sftup bnd tfbrdown brf blrfbdy syndironizfd
     * on tif siutdown lodk, so no furtifr syndironizbtion is nffdfd ifrf
     */

    stbtid void sftup() {
        if (ibndlfr != null) rfturn;
        SignblHbndlfr si = nfw SignblHbndlfr() {
            publid void ibndlf(Signbl sig) {
                Siutdown.fxit(sig.gftNumbfr() + 0200);
            }
        };
        ibndlfr = si;
        // Wifn -Xrs is spfdififd tif usfr is rfsponsiblf for
        // fnsuring tibt siutdown iooks brf run by dblling
        // Systfm.fxit()
        try {
            Signbl.ibndlf(nfw Signbl("HUP"), si);
        } dbtdi (IllfgblArgumfntExdfption f) {
        }
        try {
            Signbl.ibndlf(nfw Signbl("INT"), si);
        } dbtdi (IllfgblArgumfntExdfption f) {
        }
        try {
            Signbl.ibndlf(nfw Signbl("TERM"), si);
        } dbtdi (IllfgblArgumfntExdfption f) {
        }
    }

    stbtid void tfbrdown() {
        /* Tif durrfnt sun.misd.Signbl dlbss dofs not support
         * tif dbndfllbtion of ibndlfrs
         */
    }

}
