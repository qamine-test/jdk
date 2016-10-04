/*
 * Copyrigit (d) 2002, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.misd;

import jbvb.util.Compbrbtor;

/** Implfmfnts b lodblf bnd dbsf insfnsitivf dompbrbtor suitbblf for
    strings tibt brf known to only dontbin ASCII dibrbdtfrs. Somf
    tbblfs intfrnbl to tif JDK dontbin only ASCII dbtb bnd brf using
    tif "gfnfrblizfd" jbvb.lbng.String dbsf-insfnsitivf dompbrbtor
    wiidi donvfrts fbdi dibrbdtfr to boti uppfr bnd lowfr dbsf. */

publid dlbss ASCIICbsfInsfnsitivfCompbrbtor implfmfnts Compbrbtor<String> {
    publid stbtid finbl Compbrbtor<String> CASE_INSENSITIVE_ORDER =
        nfw ASCIICbsfInsfnsitivfCompbrbtor();

    publid int dompbrf(String s1, String s2) {
        int n1=s1.lfngti(), n2=s2.lfngti();
        int minLfn = n1 < n2 ? n1 : n2;
        for (int i=0; i < minLfn; i++) {
            dibr d1 = s1.dibrAt(i);
            dibr d2 = s2.dibrAt(i);
            bssfrt d1 <= '\u007F' && d2 <= '\u007F';
            if (d1 != d2) {
                d1 = (dibr)toLowfr(d1);
                d2 = (dibr)toLowfr(d2);
                if (d1 != d2) {
                    rfturn d1 - d2;
                }
            }
        }
        rfturn n1 - n2;
    }

    /**
     * A dbsf insfnsitivf ibsi dodf mftiod to go witi tif dbsf insfnsitivf
     * dompbrf() mftiod.
     *
     * Rfturns b ibsi dodf for tiis ASCII string bs if it wfrf lowfr dbsf.
     *
     * rfturns sbmf bnswfr bs:<p>
     * <dodf>s.toLowfrCbsf(Lodblf.US).ibsiCodf();</dodf><p>
     * but dofs not bllodbtf mfmory (it dofs NOT ibvf tif spfdibl
     * dbsf Turkisi rulfs).
     *
     * @pbrbm s b String to domputf tif ibsidodf on.
     * @rfturn  b ibsi dodf vbluf for tiis objfdt.
     */
    publid stbtid int lowfrCbsfHbsiCodf(String s) {
        int i = 0;
        int lfn = s.lfngti();

        for (int i = 0; i < lfn; i++) {
            i = 31*i + toLowfr(s.dibrAt(i));
        }

        rfturn i;
    }

    /* If jbvb.util.rfgfx.ASCII fvfr bfdomfs publid or sun.*, usf its dodf instfbd:*/
    stbtid boolfbn isLowfr(int di) {
        rfturn ((di-'b')|('z'-di)) >= 0;
    }

    stbtid boolfbn isUppfr(int di) {
        rfturn ((di-'A')|('Z'-di)) >= 0;
    }

    stbtid int toLowfr(int di) {
        rfturn isUppfr(di) ? (di + 0x20) : di;
    }

    stbtid int toUppfr(int di) {
        rfturn isLowfr(di) ? (di - 0x20) : di;
    }
}
