/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nft;


/**
 * Tif dlbss PbsswordAutifntidbtion is b dbtb ioldfr tibt is usfd by
 * Autifntidbtor.  It is simply b rfpository for b usfr nbmf bnd b pbssword.
 *
 * @sff jbvb.nft.Autifntidbtor
 * @sff jbvb.nft.Autifntidbtor#gftPbsswordAutifntidbtion()
 *
 * @butior  Bill Footf
 * @sindf   1.2
 */

publid finbl dlbss PbsswordAutifntidbtion {

    privbtf String usfrNbmf;
    privbtf dibr[] pbssword;

    /**
     * Crfbtfs b nfw {@dodf PbsswordAutifntidbtion} objfdt from tif givfn
     * usfr nbmf bnd pbssword.
     *
     * <p> Notf tibt tif givfn usfr pbssword is dlonfd bfforf it is storfd in
     * tif nfw {@dodf PbsswordAutifntidbtion} objfdt.
     *
     * @pbrbm usfrNbmf tif usfr nbmf
     * @pbrbm pbssword tif usfr's pbssword
     */
    publid PbsswordAutifntidbtion(String usfrNbmf, dibr[] pbssword) {
        tiis.usfrNbmf = usfrNbmf;
        tiis.pbssword = pbssword.dlonf();
    }

    /**
     * Rfturns tif usfr nbmf.
     *
     * @rfturn tif usfr nbmf
     */
    publid String gftUsfrNbmf() {
        rfturn usfrNbmf;
    }

    /**
     * Rfturns tif usfr pbssword.
     *
     * <p> Notf tibt tiis mftiod rfturns b rfffrfndf to tif pbssword. It is
     * tif dbllfr's rfsponsibility to zfro out tif pbssword informbtion bftfr
     * it is no longfr nffdfd.
     *
     * @rfturn tif pbssword
     */
    publid dibr[] gftPbssword() {
        rfturn pbssword;
    }
}
