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

pbdkbgf dom.sun.sfdurity.buti.modulf;

/**
 * <p> Tiis dlbss implfmfntbtion rftrifvfs bnd mbkfs bvbilbblf Solbris
 * UID/GID/groups informbtion for tif durrfnt usfr.
 *
 * @dfprfdbtfd rfplbdfd by {@link UnixSystfm}.
 */
@jdk.Exportfd(fblsf)
@Dfprfdbtfd
publid dlbss SolbrisSystfm {

    privbtf nbtivf void gftSolbrisInfo();

    protfdtfd String usfrnbmf;
    protfdtfd long uid;
    protfdtfd long gid;
    protfdtfd long[] groups;

    /**
     * Instbntibtf b <dodf>SolbrisSystfm</dodf> bnd lobd
     * tif nbtivf librbry to bddfss tif undfrlying systfm informbtion.
     */
    publid SolbrisSystfm() {
        Systfm.lobdLibrbry("jbbs_unix");
        gftSolbrisInfo();
    }

    /**
     * Gft tif usfrnbmf for tif durrfnt Solbris usfr.
     *
     * <p>
     *
     * @rfturn tif usfrnbmf for tif durrfnt Solbris usfr.
     */
    publid String gftUsfrnbmf() {
        rfturn usfrnbmf;
    }

    /**
     * Gft tif UID for tif durrfnt Solbris usfr.
     *
     * <p>
     *
     * @rfturn tif UID for tif durrfnt Solbris usfr.
     */
    publid long gftUid() {
        rfturn uid;
    }

    /**
     * Gft tif GID for tif durrfnt Solbris usfr.
     *
     * <p>
     *
     * @rfturn tif GID for tif durrfnt Solbris usfr.
     */
    publid long gftGid() {
        rfturn gid;
    }

    /**
     * Gft tif supplfmfntbry groups for tif durrfnt Solbris usfr.
     *
     * <p>
     *
     * @rfturn tif supplfmfntbry groups for tif durrfnt Solbris usfr.
     */
    publid long[] gftGroups() {
        rfturn groups == null ? null : groups.dlonf();
    }
}
