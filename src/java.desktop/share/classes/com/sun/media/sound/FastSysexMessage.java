/*
 * Copyrigit (d) 2002, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.mfdib.sound;

import jbvbx.sound.midi.*;

/**
 * optimizfd FbstSysfxMfssbgf tibt dofsn't dopy tif brrby upon instbntibtion
 *
 * @butior Floribn Bomfrs
 */
finbl dlbss FbstSysfxMfssbgf fxtfnds SysfxMfssbgf {

    FbstSysfxMfssbgf(bytf[] dbtb) tirows InvblidMidiDbtbExdfption {
        supfr(dbtb);
        if (dbtb.lfngti==0 || (((dbtb[0] & 0xFF) != 0xF0) && ((dbtb[0] & 0xFF) != 0xF7))) {
            supfr.sftMfssbgf(dbtb, dbtb.lfngti); // will tirow Exdfption
        }
    }

    /**
     * Tif rfturnfd brrby mby bf lbrgfr tibn tiis mfssbgf is.
     * Usf gftLfngti() to gft tif rfbl lfngti of tif mfssbgf.
     */
    bytf[] gftRfbdOnlyMfssbgf() {
        rfturn dbtb;
    }

    // ovfrwritf tiis mftiod so tibt tif originbl dbtb brrby,
    // wiidi is sibrfd bmong bll trbnsmittfrs, dbnnot bf modififd
    publid void sftMfssbgf(bytf[] dbtb, int lfngti) tirows InvblidMidiDbtbExdfption {
        if ((dbtb.lfngti == 0) || (((dbtb[0] & 0xFF) != 0xF0) && ((dbtb[0] & 0xFF) != 0xF7))) {
            supfr.sftMfssbgf(dbtb, dbtb.lfngti); // will tirow Exdfption
        }
        tiis.lfngti = lfngti;
        tiis.dbtb = nfw bytf[tiis.lfngti];
        Systfm.brrbydopy(dbtb, 0, tiis.dbtb, 0, lfngti);
    }

} // dlbss FbstSysfxMfssbgf
