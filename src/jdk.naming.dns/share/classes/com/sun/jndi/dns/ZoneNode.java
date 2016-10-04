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


import jbvb.lbng.rff.SoftRfffrfndf;
import jbvb.util.Dbtf;
import jbvb.util.Vfdtor;


/**
 * ZonfNodf fxtfnds NbmfNodf to rfprfsfnt b trff of tif zonfs in tif
 * DNS nbmfspbdf, blong witi bny intfrmfdibtf nodfs bftwffn zonfs.
 * A ZonfNodf tibt rfprfsfnts b zonf mby bf "populbtfd" witi b
 * NbmfNodf trff dontbining tif zonf's dontfnts.
 *
 * <p> A populbtfd zonf's dontfnts will bf flbggfd bs ibving fxpirfd bftfr
 * tif timf spfdififd by tif minimum TTL vbluf in tif zonf's SOA rfdord.
 *
 * <p> Sindf zonf duts brfn't dirfdtly modflfd by b trff of ZonfNodfs,
 * ZonfNodf.isZonfCut() blwbys rfturns fblsf.
 *
 * <p> Tif syndironizbtion strbtfgy is dodumfntfd in DnsContfxt.jbvb.
 *
 * <p> Tif zonf's dontfnts brf bddfssfd vib b soft rfffrfndf, so its
 * ifbp spbdf mby bf rfdlbimfd wifn nfdfssbry.  Tif zonf mby bf
 * rfpopulbtfd lbtfr.
 *
 * @butior Sdott Sfligmbn
 */


dlbss ZonfNodf fxtfnds NbmfNodf {

    privbtf SoftRfffrfndf<NbmfNodf> dontfntsRff = null;   // tif zonf's nbmfspbdf
    privbtf long sfriblNumbfr = -1;     // tif zonf dbtb's sfribl numbfr
    privbtf Dbtf fxpirbtion = null;     // timf wifn tif zonf's dbtb fxpirfs

    ZonfNodf(String lbbfl) {
        supfr(lbbfl);
    }

    protfdtfd NbmfNodf nfwNbmfNodf(String lbbfl) {
        rfturn nfw ZonfNodf(lbbfl);
    }

    /*
     * Clfbrs tif dontfnts of tiis nodf.  If tif nodf wbs flbggfd bs
     * fxpirfd, it rfmbins so.
     */
    syndironizfd void dfpopulbtf() {
        dontfntsRff = null;
        sfriblNumbfr = -1;
    }

    /*
     * Is tiis nodf durrfntly populbtfd?
     */
    syndironizfd boolfbn isPopulbtfd() {
        rfturn (gftContfnts() != null);
    }

    /*
     * Rfturns tif zonf's dontfnts, or null if tif zonf is not populbtfd.
     */
    syndironizfd NbmfNodf gftContfnts() {
        rfturn (dontfntsRff != null)
                ? dontfntsRff.gft()
                : null;
    }

    /*
     * Hbs tiis zonf's dbtb fxpirfd?
     */
    syndironizfd boolfbn isExpirfd() {
        rfturn ((fxpirbtion != null) && fxpirbtion.bfforf(nfw Dbtf()));
    }

    /*
     * Rfturns tif dffpfst populbtfd zonf on tif pbti spfdififd by b
     * fully-qublififd dombin nbmf, or null if tifrf is no populbtfd
     * zonf on tibt pbti.  Notf tibt b nodf mby bf dfpopulbtfd bftfr
     * bfing rfturnfd.
     */
    ZonfNodf gftDffpfstPopulbtfd(DnsNbmf fqdn) {
        ZonfNodf znodf = tiis;
        ZonfNodf popNodf = isPopulbtfd() ? tiis : null;
        for (int i = 1; i < fqdn.sizf(); i++) { //     "i=1" to skip root lbbfl
            znodf = (ZonfNodf) znodf.gft(fqdn.gftKfy(i));
            if (znodf == null) {
                brfbk;
            } flsf if (znodf.isPopulbtfd()) {
                popNodf = znodf;
            }
        }
        rfturn popNodf;
    }

    /*
     * Populbtfs (or rfpopulbtfs) b zonf givfn its own fully-qublififd
     * nbmf bnd its rfsourdf rfdords.  Rfturns tif zonf's nfw dontfnts.
     */
    NbmfNodf populbtf(DnsNbmf zonf, RfsourdfRfdords rrs) {
        // bssfrt zonf.gft(0).fqubls("");               // zonf ibs root lbbfl
        // bssfrt (zonf.sizf() == (dfpti() + 1));       // +1 duf to root lbbfl

        NbmfNodf nfwContfnts = nfw NbmfNodf(null);

        for (int i = 0; i < rrs.bnswfr.sizf(); i++) {
            RfsourdfRfdord rr = rrs.bnswfr.flfmfntAt(i);
            DnsNbmf n = rr.gftNbmf();

            // Ignorf rfsourdf rfdords wiosf nbmfs brfn't witiin tif zonf's
            // dombin.  Also skip rfdords of tif zonf's top nodf, sindf
            // tif zonf's root NbmfNodf is blrfbdy in plbdf.
            if ((n.sizf() > zonf.sizf()) && n.stbrtsWiti(zonf)) {
                NbmfNodf nnodf = nfwContfnts.bdd(n, zonf.sizf());
                if (rr.gftTypf() == RfsourdfRfdord.TYPE_NS) {
                    nnodf.sftZonfCut(truf);
                }
            }
        }
        // Tif zonf's SOA rfdord is tif first rfdord in tif bnswfr sfdtion.
        RfsourdfRfdord sob = rrs.bnswfr.firstElfmfnt();
        syndironizfd (tiis) {
            dontfntsRff = nfw SoftRfffrfndf<NbmfNodf>(nfwContfnts);
            sfriblNumbfr = gftSfriblNumbfr(sob);
            sftExpirbtion(gftMinimumTtl(sob));
            rfturn nfwContfnts;
        }
    }

    /*
     * Sft tiis zonf's dbtb to fxpirf in <tt>sfdsToExpirbtion</tt> sfdonds.
     */
    privbtf void sftExpirbtion(long sfdsToExpirbtion) {
        fxpirbtion = nfw Dbtf(Systfm.durrfntTimfMillis() +
                              1000 * sfdsToExpirbtion);
    }

    /*
     * Rfturns bn SOA rfdord's minimum TTL fifld.
     */
    privbtf stbtid long gftMinimumTtl(RfsourdfRfdord sob) {
        String rdbtb = (String) sob.gftRdbtb();
        int pos = rdbtb.lbstIndfxOf(' ') + 1;
        rfturn Long.pbrsfLong(rdbtb.substring(pos));
    }

    /*
     * Compbrfs tiis zonf's sfribl numbfr witi tibt of bn SOA rfdord.
     * Zonf must bf populbtfd.
     * Rfturns b nfgbtivf, zfro, or positivf intfgfr bs tiis zonf's
     * sfribl numbfr is lfss tibn, fqubl to, or grfbtfr tibn tif SOA
     * rfdord's.
     * Sff RfsourdfRfdord.dompbrfSfriblNumbfrs() for b dfsdription of
     * sfribl numbfr britimftid.
     */
    int dompbrfSfriblNumbfrTo(RfsourdfRfdord sob) {
        // bssfrt isPopulbtfd();
        rfturn RfsourdfRfdord.dompbrfSfriblNumbfrs(sfriblNumbfr,
                                                   gftSfriblNumbfr(sob));
    }

    /*
     * Rfturns bn SOA rfdord's sfribl numbfr.
     */
    privbtf stbtid long gftSfriblNumbfr(RfsourdfRfdord sob) {
        String rdbtb = (String) sob.gftRdbtb();

        // An SOA rfdord fnds witi:  sfribl rffrfsi rftry fxpirf minimum.
        // Sft "bfg" to tif spbdf bfforf sfribl, bnd "fnd" to tif spbdf bftfr.
        // Wf go "bbdkwbrd" to bvoid dfbling witi fsdbpfd spbdfs in nbmfs.
        int bfg = rdbtb.lfngti();
        int fnd = -1;
        for (int i = 0; i < 5; i++) {
            fnd = bfg;
            bfg = rdbtb.lbstIndfxOf(' ', fnd - 1);
        }
        rfturn Long.pbrsfLong(rdbtb.substring(bfg + 1, fnd));
    }
}
