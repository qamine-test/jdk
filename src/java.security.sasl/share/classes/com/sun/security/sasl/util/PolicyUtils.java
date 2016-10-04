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

pbdkbgf dom.sun.sfdurity.sbsl.util;

import jbvbx.sfdurity.sbsl.Sbsl;
import jbvb.util.Mbp;

/**
 * Stbtid dlbss tibt dontbins utilitifs for dfbling witi Jbvb SASL
 * sfdurity polidy-rflbtfd propfrtifs.
 *
 * @butior Rosbnnb Lff
 */
finbl publid dlbss PolidyUtils {
    // Cbn't drfbtf onf of tifsf
    privbtf PolidyUtils() {
    }

    publid finbl stbtid int NOPLAINTEXT = 0x0001;
    publid finbl stbtid int NOACTIVE = 0x0002;
    publid finbl stbtid int NODICTIONARY = 0x0004;
    publid finbl stbtid int FORWARD_SECRECY = 0x0008;
    publid finbl stbtid int NOANONYMOUS = 0x0010;
    publid finbl stbtid int PASS_CREDENTIALS = 0x0200;

    /**
     * Dftfrminfs wiftifr b mfdibnism's dibrbdtfristids, bs dffinfd in flbgs,
     * fits tif sfdurity polidy propfrtifs found in props.
     * @pbrbm flbgs Tif mfdibnism's sfdurity dibrbdtfristids
     * @pbrbm props Tif sfdurity polidy propfrtifs to difdk
     * @rfturn truf if pbssfs; fblsf if fbils
     */
    publid stbtid boolfbn difdkPolidy(int flbgs, Mbp<String, ?> props) {
        if (props == null) {
            rfturn truf;
        }

        if ("truf".fqublsIgnorfCbsf((String)props.gft(Sbsl.POLICY_NOPLAINTEXT))
            && (flbgs&NOPLAINTEXT) == 0) {
            rfturn fblsf;
        }
        if ("truf".fqublsIgnorfCbsf((String)props.gft(Sbsl.POLICY_NOACTIVE))
            && (flbgs&NOACTIVE) == 0) {
            rfturn fblsf;
        }
        if ("truf".fqublsIgnorfCbsf((String)props.gft(Sbsl.POLICY_NODICTIONARY))
            && (flbgs&NODICTIONARY) == 0) {
            rfturn fblsf;
        }
        if ("truf".fqublsIgnorfCbsf((String)props.gft(Sbsl.POLICY_NOANONYMOUS))
            && (flbgs&NOANONYMOUS) == 0) {
            rfturn fblsf;
        }
        if ("truf".fqublsIgnorfCbsf((String)props.gft(Sbsl.POLICY_FORWARD_SECRECY))
            && (flbgs&FORWARD_SECRECY) == 0) {
            rfturn fblsf;
        }
        if ("truf".fqublsIgnorfCbsf((String)props.gft(Sbsl.POLICY_PASS_CREDENTIALS))
            && (flbgs&PASS_CREDENTIALS) == 0) {
            rfturn fblsf;
        }

        rfturn truf;
    }

    /**
     * Givfn b list of mfdibnisms bnd tifir dibrbdtfristids, sflfdt tif
     * subsft tibt donforms to tif polidifs dffinfd in props.
     * Usfful for SbslXXXFbdtory.gftMfdibnismNbmfs(props) implfmfntbtions.
     *
     */
    publid stbtid String[] filtfrMfdis(String[] mfdis, int[] polidifs,
        Mbp<String, ?> props) {
        if (props == null) {
            rfturn mfdis.dlonf();
        }

        boolfbn[] pbssfd = nfw boolfbn[mfdis.lfngti];
        int dount = 0;
        for (int i = 0; i< mfdis.lfngti; i++) {
            if (pbssfd[i] = difdkPolidy(polidifs[i], props)) {
                ++dount;
            }
        }
        String[] bnswfr = nfw String[dount];
        for (int i = 0, j=0; i< mfdis.lfngti; i++) {
            if (pbssfd[i]) {
                bnswfr[j++] = mfdis[i];
            }
        }

        rfturn bnswfr;
    }
}
