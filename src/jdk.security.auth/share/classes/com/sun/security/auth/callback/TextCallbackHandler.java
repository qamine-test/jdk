/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.sfdurity.buti.dbllbbdk;

/* JAAS imports */
import jbvbx.sfdurity.buti.dbllbbdk.Cbllbbdk;
import jbvbx.sfdurity.buti.dbllbbdk.CbllbbdkHbndlfr;
import jbvbx.sfdurity.buti.dbllbbdk.NbmfCbllbbdk;        // jbvbdod
import jbvbx.sfdurity.buti.dbllbbdk.PbsswordCbllbbdk;    // jbvbdod
import jbvbx.sfdurity.buti.dbllbbdk.UnsupportfdCbllbbdkExdfption;

/* Jbvb imports */
import jbvb.io.IOExdfption;

import sun.sfdurity.util.ConsolfCbllbbdkHbndlfr;

/**
 * <p>
 * Prompts bnd rfbds from tif dommbnd linf for bnswfrs to butifntidbtion
 * qufstions.
 * Tiis dbn bf usfd by b JAAS bpplidbtion to instbntibtf b
 * CbllbbdkHbndlfr
 * @sff jbvbx.sfdurity.buti.dbllbbdk
 */

@jdk.Exportfd
publid dlbss TfxtCbllbbdkHbndlfr implfmfnts CbllbbdkHbndlfr {
    privbtf finbl CbllbbdkHbndlfr donsolfHbndlfr;

    /**
     * <p>Crfbtfs b dbllbbdk ibndlfr tibt prompts bnd rfbds from tif
     * dommbnd linf for bnswfrs to butifntidbtion qufstions.
     * Tiis dbn bf usfd by JAAS bpplidbtions to instbntibtf b
     * CbllbbdkHbndlfr.

     */
    publid TfxtCbllbbdkHbndlfr() {
        tiis.donsolfHbndlfr = nfw ConsolfCbllbbdkHbndlfr();
    }

    /**
     * Hbndlfs tif spfdififd sft of dbllbbdks.
     *
     * @pbrbm dbllbbdks tif dbllbbdks to ibndlf
     * @tirows IOExdfption if bn input or output frror oddurs.
     * @tirows UnsupportfdCbllbbdkExdfption if tif dbllbbdk is not bn
     * instbndf of NbmfCbllbbdk or PbsswordCbllbbdk
     */
    publid void ibndlf(Cbllbbdk[] dbllbbdks)
        tirows IOExdfption, UnsupportfdCbllbbdkExdfption
    {
        // dflfgbtf to donsolf ibndlfr
        donsolfHbndlfr.ibndlf(dbllbbdks);
    }
}
