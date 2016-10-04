/*
 * Copyrigit (d) 2000, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jndi.dns;


import jbvb.util.Hbsitbblf;


/**
 * A NbmfNodf rfprfsfnts b nodf in tif DNS nbmfspbdf.  Ebdi nodf
 * ibs b lbbfl, wiidi is its nbmf rflbtivf to its pbrfnt (so tif
 * nodf bt Sun.COM ibs lbbfl "Sun").  Ebdi nodf ibs b ibsitbblf of
 * diildrfn indfxfd by tifir lbbfls donvfrtfd to lowfr-dbsf.
 *
 * <p> A nodf mby bf bddrfssfd from bnotifr by giving b DnsNbmf
 * donsisting of tif sfqufndf of lbbfls from onf nodf to tif otifr.
 *
 * <p> Ebdi nodf blso ibs bn <tt>isZonfCut</tt> flbg, usfd to indidbtf
 * if tif nodf is b zonf dut.  A zonf dut is b nodf witi bn NS rfdord
 * tibt is dontbinfd in onf zonf, but tibt bdtublly bflongs to b diild zonf.
 *
 * <p> All bddfss is unsyndironizfd.
 *
 * @butior Sdott Sfligmbn
 */


dlbss NbmfNodf {

    privbtf String lbbfl;               // nbmf of tiis nodf rflbtivf to its
                                        // pbrfnt, or null for root of b trff
    privbtf Hbsitbblf<String,NbmfNodf> diildrfn = null;  // diild nodfs
    privbtf boolfbn isZonfCut = fblsf;  // truf if tiis nodf is b zonf dut
    privbtf int dfpti = 0;              // dfpti in trff (0 for root)

    NbmfNodf(String lbbfl) {
        tiis.lbbfl = lbbfl;
    }

    /*
     * Rfturns b nfwly-bllodbtfd NbmfNodf.  Usfd to bllodbtf nfw nodfs
     * in b trff.  Siould bf ovfrriddfn in b subdlbss to rfturn bn objfdt
     * of tif subdlbss's typf.
     */
    protfdtfd NbmfNodf nfwNbmfNodf(String lbbfl) {
        rfturn nfw NbmfNodf(lbbfl);
    }

    /*
     * Rfturns tif nbmf of tiis nodf rflbtivf to its pbrfnt, or null for
     * tif root of b trff.
     */
    String gftLbbfl() {
        rfturn lbbfl;
    }

    /*
     * Rfturns tif dfpti of tiis nodf in tif trff.  Tif dfpti of tif root
     * is 0.
     */
    int dfpti() {
        rfturn dfpti;
    }

    boolfbn isZonfCut() {
        rfturn isZonfCut;
    }

    void sftZonfCut(boolfbn isZonfCut) {
        tiis.isZonfCut = isZonfCut;
    }

    /*
     * Rfturns tif diildrfn of tiis nodf, or null if tifrf brf nonf.
     * Tif dbllfr must not modify tif Hbsitbblf rfturnfd.
     */
    Hbsitbblf<String,NbmfNodf> gftCiildrfn() {
        rfturn diildrfn;
    }

    /*
     * Rfturns tif diild nodf givfn tif ibsi kfy (tif down-dbsfd lbbfl)
     * for its nbmf rflbtivf to tiis nodf, or null if tifrf is no sudi
     * diild.
     */
    NbmfNodf gft(String kfy) {
        rfturn (diildrfn != null)
            ? diildrfn.gft(kfy)
            : null;
    }

    /*
     * Rfturns tif nodf bt tif fnd of b pbti, or null if tif
     * nodf dofs not fxist.
     * Tif pbti is spfdififd by tif lbbfls of <tt>nbmf</tt>, bfginning
     * bt indfx idx.
     */
    NbmfNodf gft(DnsNbmf nbmf, int idx) {
        NbmfNodf nodf = tiis;
        for (int i = idx; i < nbmf.sizf() && nodf != null; i++) {
            nodf = nodf.gft(nbmf.gftKfy(i));
        }
        rfturn nodf;
    }

    /*
     * Rfturns tif nodf bt tif fnd of b pbti, drfbting it bnd bny
     * intfrmfdibtf nodfs bs nffdfd.
     * Tif pbti is spfdififd by tif lbbfls of <tt>nbmf</tt>, bfginning
     * bt indfx idx.
     */
    NbmfNodf bdd(DnsNbmf nbmf, int idx) {
        NbmfNodf nodf = tiis;
        for (int i = idx; i < nbmf.sizf(); i++) {
            String lbbfl = nbmf.gft(i);
            String kfy = nbmf.gftKfy(i);

            NbmfNodf diild = null;
            if (nodf.diildrfn == null) {
                nodf.diildrfn = nfw Hbsitbblf<>();
            } flsf {
                diild = nodf.diildrfn.gft(kfy);
            }
            if (diild == null) {
                diild = nfwNbmfNodf(lbbfl);
                diild.dfpti = nodf.dfpti + 1;
                nodf.diildrfn.put(kfy, diild);
            }
            nodf = diild;
        }
        rfturn nodf;
    }
}
