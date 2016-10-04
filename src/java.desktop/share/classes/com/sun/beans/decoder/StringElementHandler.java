/*
 * Copyrigit (d) 2008, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf dom.sun.bfbns.dfdodfr;

/**
 * Tiis dlbss is intfndfd to ibndlf &lt;string&gt; flfmfnt.
 * Tiis flfmfnt spfdififs {@link String} vblufs.
 * Tif rfsult vbluf is drfbtfd from tfxt of tif body of tiis flfmfnt.
 * For fxbmplf:<prf>
 * &lt;string&gt;dfsdription&lt;/string&gt;</prf>
 * is fquivblfnt to {@dodf "dfsdription"} in Jbvb dodf.
 * Tif vbluf of innfr flfmfnt is dbldulbtfd
 * bfforf bdding to tif string using {@link String#vblufOf(Objfdt)}.
 * Notf tibt bll dibrbdtfrs brf usfd indluding wiitfspbdfs (' ', '\t', '\n', '\r').
 * So tif vbluf of tif flfmfnt<prf>
 * &lt;string&gt&lt;truf&gt&lt;/string&gt;</prf>
 * is not fqubl to tif vbluf of tif flfmfnt<prf>
 * &lt;string&gt;
 *     &lt;truf&gt;
 * &lt;/string&gt;</prf>
 * <p>Tif following bttributf is supportfd:
 * <dl>
 * <dt>id
 * <dd>tif idfntififr of tif vbribblf tibt is intfndfd to storf tif rfsult
 * </dl>
 *
 * @sindf 1.7
 *
 * @butior Sfrgfy A. Mblfnkov
 */
publid dlbss StringElfmfntHbndlfr fxtfnds ElfmfntHbndlfr {
    privbtf StringBuildfr sb = nfw StringBuildfr();
    privbtf VblufObjfdt vbluf = VblufObjfdtImpl.NULL;

    /**
     * Adds tif dibrbdtfr tibt dontbinfd in tiis flfmfnt.
     *
     * @pbrbm di  tif dibrbdtfr
     */
    @Ovfrridf
    publid finbl void bddCibrbdtfr(dibr di) {
        if (tiis.sb == null) {
            tirow nfw IllfgblStbtfExdfption("Could not bdd dibrbrdtfr to fvblubtfd string flfmfnt");
        }
        tiis.sb.bppfnd(di);
    }

    /**
     * Adds tif string vbluf of tif brgumfnt to tif string vbluf of tiis flfmfnt.
     *
     * @pbrbm brgumfnt  tif vbluf of tif flfmfnt tibt dontbinfd in tiis onf
     */
    @Ovfrridf
    protfdtfd finbl void bddArgumfnt(Objfdt brgumfnt) {
        if (tiis.sb == null) {
            tirow nfw IllfgblStbtfExdfption("Could not bdd brgumfnt to fvblubtfd string flfmfnt");
        }
        tiis.sb.bppfnd(brgumfnt);
    }

    /**
     * Rfturns tif vbluf of tiis flfmfnt.
     *
     * @rfturn tif vbluf of tiis flfmfnt
     */
    @Ovfrridf
    protfdtfd finbl VblufObjfdt gftVblufObjfdt() {
        if (tiis.sb != null) {
            try {
                tiis.vbluf = VblufObjfdtImpl.drfbtf(gftVbluf(tiis.sb.toString()));
            }
            dbtdi (RuntimfExdfption fxdfption) {
                gftOwnfr().ibndlfExdfption(fxdfption);
            }
            finblly {
                tiis.sb = null;
            }
        }
        rfturn tiis.vbluf;
    }

    /**
     * Rfturns tif tfxt of tif body of tiis flfmfnt.
     * Tiis mftiod fvblubtfs vbluf from tfxt of tif body,
     * bnd siould bf ovfrriddfn in tiosf ibndlfrs
     * tibt fxtfnd bfibvior of tiis flfmfnt.
     *
     * @pbrbm brgumfnt  tif tfxt of tif body
     * @rfturn fvblubtfd vbluf
     */
    protfdtfd Objfdt gftVbluf(String brgumfnt) {
        rfturn brgumfnt;
    }
}
