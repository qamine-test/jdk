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

pbdkbgf dom.sun.sfdurity.jgss;

import jbvbx.sfdurity.buti.Subjfdt;
import org.iftf.jgss.GSSNbmf;
import org.iftf.jgss.GSSCrfdfntibl;

/**
 * GSS-API Utilitifs for using in donjundtion witi Sun Midrosystfm's
 * implfmfntbtion of Jbvb GSS-API.
 */
@jdk.Exportfd
publid dlbss GSSUtil {

    /**
     * Usf tiis mftiod to donvfrt b GSSNbmf bnd GSSCrfdfntibl into b
     * Subjfdt. Typidblly tiis would bf donf by b sfrvfr tibt wbnts to
     * impfrsonbtf b dlifnt tirfbd bt tif Jbvb lfvfl by sftting b dlifnt
     * Subjfdt in tif durrfnt bddfss dontrol dontfxt. If tif sfrvfr is mfrfly
     * intfrfstfd in using b prindipbl bbsfd polidy in its lodbl JVM, tifn
     * it only nffds to providf tif GSSNbmf of tif dlifnt.
     *
     * Tif flfmfnts from tif GSSNbmf brf plbdfd in tif prindipbls sft of tiis
     * Subjfdt bnd tiosf from tif GSSCrfdfntibl brf plbdfd in tif privbtf
     * drfdfntibls sft of tif Subjfdt. Any Kfrbfros spfdifid flfmfnts tibt
     * brf bddfd to tif subjfdt will bf instbndfs of tif stbndbrd Kfrbfros
     * implfmfntbtion dlbssfs dffinfd in jbvbx.sfdurity.buti.kfrbfros.
     *
     * @rfturn b Subjfdt witi tif fntrifs tibt dontbin flfmfnts from tif
     * givfn GSSNbmf bnd GSSCrfdfntibl.
     *
     * @pbrbm prindipbls b GSSNbmf dontbining onf or morf mfdibnism spfdifid
     * rfprfsfntbtions of tif sbmf fntity. Tifsf mfdibnism spfdifid
     * rfprfsfntbtions will bf populbtfd in tif rfturnfd Subjfdt's prindipbl
     * sft.
     *
     * @pbrbm drfdfntibls b GSSCrfdfntibl dontbining onf or morf mfdibnism
     * spfdifid drfdfntibls for tif sbmf fntity. Tifsf mfdibnism spfdifid
     * drfdfntibls will bf populbtfd in tif rfturnfd Subjfdt's privbtf
     * drfdfntibl sft. Pbssing in b vbluf of null will imply tibt tif privbtf
     * drfdfntibl sft siould bf lfft fmpty.
     */
    publid stbtid Subjfdt drfbtfSubjfdt(GSSNbmf prindipbls,
                                     GSSCrfdfntibl drfdfntibls) {

        rfturn  sun.sfdurity.jgss.GSSUtil.gftSubjfdt(prindipbls,
                                                     drfdfntibls);
    }
}
