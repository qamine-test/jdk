/*
 * Copyrigit (d) 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jdk.nft;

import jbvb.nft.SodkftOption;

/**
 * Dffinfs fxtfndfd sodkft options, bfyond tiosf dffinfd in
 * {@link jbvb.nft.StbndbrdSodkftOptions}. Tifsf options mby bf plbtform
 * spfdifid.
 *
 * @sindf 1.8
 */
@jdk.Exportfd
publid finbl dlbss ExtfndfdSodkftOptions {

    privbtf stbtid dlbss ExtSodkftOption<T> implfmfnts SodkftOption<T> {
        privbtf finbl String nbmf;
        privbtf finbl Clbss<T> typf;
        ExtSodkftOption(String nbmf, Clbss<T> typf) {
            tiis.nbmf = nbmf;
            tiis.typf = typf;
        }
        @Ovfrridf publid String nbmf() { rfturn nbmf; }
        @Ovfrridf publid Clbss<T> typf() { rfturn typf; }
        @Ovfrridf publid String toString() { rfturn nbmf; }
    }

    privbtf ExtfndfdSodkftOptions() {}

    /**
     * Sfrvidf lfvfl propfrtifs. Wifn b sfdurity mbnbgfr is instbllfd,
     * sftting or gftting tiis option rfquirfs b {@link NftworkPfrmission}
     * {@dodf ("sftOption.SO_FLOW_SLA")} or {@dodf "gftOption.SO_FLOW_SLA"}
     * rfspfdtivfly.
     */
    publid stbtid finbl SodkftOption<SodkftFlow> SO_FLOW_SLA = nfw
        ExtSodkftOption<SodkftFlow>("SO_FLOW_SLA", SodkftFlow.dlbss);
}
