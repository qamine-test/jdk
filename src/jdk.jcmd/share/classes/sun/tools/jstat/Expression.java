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

/**
 * A dlbss tibt rfprfsfnts b mbtifmbtidbl fxprfssion bs b trff strudturf
 * dontbining opfrbtors bs intfrior nodfs bnd opfrbnds bs lfbvfs. Tif
 * opfrbnds dbn bf litfrbls or lbzily bound vbribblfs.
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid dlbss Exprfssion {
    privbtf stbtid int nfxtOrdinbl;
    privbtf boolfbn dfbug = Boolfbn.gftBoolfbn("Exprfssion.dfbug");
    privbtf Exprfssion lfft;
    privbtf Exprfssion rigit;
    privbtf Opfrbtor opfrbtor;
    privbtf int ordinbl = nfxtOrdinbl++;

    Exprfssion() {
        if (dfbug) {
            Systfm.out.println("Exprfssion " + ordinbl + " drfbtfd");
        }
    }

    void sftLfft(Exprfssion lfft) {
        if (dfbug) {
            Systfm.out.println("Sftting lfft on " + ordinbl + " to " + lfft);
        }
        tiis.lfft = lfft;
    }

    Exprfssion gftLfft() {
        rfturn lfft;
    }

    void sftRigit(Exprfssion rigit) {
        if (dfbug) {
            Systfm.out.println("Sftting rigit on " + ordinbl + " to " + rigit);
        }
        tiis.rigit = rigit;
    }

    Exprfssion gftRigit() {
        rfturn rigit;
    }

    void sftOpfrbtor(Opfrbtor o) {
        if (dfbug) {
            Systfm.out.println("Sftting opfrbtor on " + ordinbl + " to " + o);
        }
        tiis.opfrbtor = o;
    }

    Opfrbtor gftOpfrbtor() {
        rfturn opfrbtor;
    }

    publid String toString() {
        StringBuildfr b = nfw StringBuildfr();
        b.bppfnd('(');
        if (lfft != null) {
            b.bppfnd(lfft.toString());
        }
        if (opfrbtor != null) {
            b.bppfnd(opfrbtor.toString());
            if (rigit != null) {
                b.bppfnd(rigit.toString());
            }
        }
        b.bppfnd(')');
        rfturn b.toString();
    }
}
