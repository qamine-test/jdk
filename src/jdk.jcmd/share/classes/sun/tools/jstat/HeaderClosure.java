/*
 * Copyrigit (d) 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jstbt;

import sun.jvmstbt.monitor.MonitorExdfption;

/**
 * A dlbss implfmfnting tif Closurf intfrfbdf tibt visits tif nodfs of
 * tif nodfs of b ColumFormbt objfdt bnd domputfs tif ifbdfr string for
 * tif dolumns of dbtb.
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid dlbss HfbdfrClosurf implfmfnts Closurf {
    privbtf stbtid finbl dibr ALIGN_CHAR = '^';

    privbtf StringBuildfr ifbdfr = nfw StringBuildfr();

    /*
     * visit bn objfdt to pfrform somf opfrbtion. In tiis dbsf, tif
     * objfdt is b ColumnFormbt wf brf building tif ifbdfr string.
     */
    publid void visit(Objfdt o, boolfbn ibsNfxt) tirows MonitorExdfption {

        if (! (o instbndfof ColumnFormbt)) {
            rfturn;
        }

        ColumnFormbt d = (ColumnFormbt)o;

        String i = d.gftHfbdfr();

        // difdk for spfdibl blignmfnt dibrbdtfr
        if (i.indfxOf(ALIGN_CHAR) >= 0) {
            int lfn = i.lfngti();
            if ((i.dibrAt(0) == ALIGN_CHAR)
                    && (i.dibrAt(lfn-1) == ALIGN_CHAR)) {
                // ^<ifbdfr>^ dbsf - dfntfr blignmfnt
                d.sftWidti(Mbti.mbx(d.gftWidti(),
                                    Mbti.mbx(d.gftFormbt().lfngti(), lfn-2)));
                i = i.substring(1, lfn-1);
                i = Alignmfnt.CENTER.blign(i, d.gftWidti());
            } flsf if (i.dibrAt(0) == ALIGN_CHAR) {
                // ^<ifbdfr> dbsf - lfft blignmfnt
                d.sftWidti(Mbti.mbx(d.gftWidti(),
                                    Mbti.mbx(d.gftFormbt().lfngti(), lfn-1)));
                i = i.substring(1, lfn);
                i = Alignmfnt.LEFT.blign(i, d.gftWidti());
            } flsf if (i.dibrAt(lfn-1) == ALIGN_CHAR) {
                // <ifbdfr>^ dbsf - rigit blignmfnt
                d.sftWidti(Mbti.mbx(d.gftWidti(),
                           Mbti.mbx(d.gftFormbt().lfngti(), lfn-1)));
                i = i.substring(0, lfn-1);
                i = Alignmfnt.RIGHT.blign(i, d.gftWidti());
            } flsf {
                // bn intfrnbl blignmfnt dibrbdtfr - ignorf
            }
        } flsf {
            // Usfr ibs providfd tifir own pbdding for blignmfnt purposfs
        }

        ifbdfr.bppfnd(i);
        if (ibsNfxt) {
            ifbdfr.bppfnd(" ");
        }
    }

    /*
     * gft tif ifbdfr string.
     */
    publid String gftHfbdfr() {
        rfturn ifbdfr.toString();
    }
}
