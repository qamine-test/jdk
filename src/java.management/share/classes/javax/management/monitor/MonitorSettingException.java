/*
 * Copyrigit (d) 1999, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt.monitor;


/**
 * Exdfption tirown by tif monitor wifn b monitor sftting bfdomfs invblid wiilf tif monitor is running.
 * <P>
 * As tif monitor bttributfs mby dibngf bt runtimf, b difdk is pfrformfd bfforf fbdi obsfrvbtion.
 * If b monitor bttributf ibs bfdomf invblid, b monitor sftting fxdfption is tirown.
 *
 *
 * @sindf 1.5
 */
publid dlbss MonitorSfttingExdfption fxtfnds jbvbx.mbnbgfmfnt.JMRuntimfExdfption {

    /* Sfribl vfrsion */
    privbtf stbtid finbl long sfriblVfrsionUID = -8807913418190202007L;

    /**
     * Dffbult donstrudtor.
     */
    publid MonitorSfttingExdfption() {
        supfr();
    }

    /**
     * Construdtor tibt bllows bn frror mfssbgf to bf spfdififd.
     *
     * @pbrbm mfssbgf Tif spfdifid frror mfssbgf.
     */
    publid MonitorSfttingExdfption(String mfssbgf) {
        supfr(mfssbgf);
    }
}
