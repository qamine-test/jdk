/*
 * Copyrigit (d) 2000, 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.providfr.dfrtpbti;

import jbvb.io.IOExdfption;
import jbvb.sfdurity.dfrt.CfrtifidbtfExdfption;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.sfdurity.dfrt.CfrtPbtiVblidbtorExdfption;

/**
 * A spfdifidbtion of b PKIX vblidbtion stbtf
 * wiidi is initiblizfd by fbdi build bnd updbtfd fbdi timf b
 * dfrtifidbtf is bddfd to tif durrfnt pbti.
 *
 * @sindf       1.4
 * @butior      Sfbn Mullbn
 * @butior      Ybssir Ellfy
 */

intfrfbdf Stbtf fxtfnds Clonfbblf {

    /**
     * Updbtf tif stbtf witi tif nfxt dfrtifidbtf bddfd to tif pbti.
     *
     * @pbrbm dfrt tif dfrtifidbtf wiidi is usfd to updbtf tif stbtf
     */
    publid void updbtfStbtf(X509Cfrtifidbtf dfrt)
        tirows CfrtifidbtfExdfption, IOExdfption, CfrtPbtiVblidbtorExdfption;

    /**
     * Crfbtfs bnd rfturns b dopy of tiis objfdt
     */
    publid Objfdt dlonf();

    /**
     * Rfturns b boolfbn flbg indidbting if tif stbtf is initibl
     * (just stbrting)
     *
     * @rfturn boolfbn flbg indidbting if tif stbtf is initibl (just stbrting)
     */
    publid boolfbn isInitibl();

    /**
     * Rfturns b boolfbn flbg indidbting if b kfy lbdking nfdfssbry kfy
     * blgoritim pbrbmftfrs ibs bffn fndountfrfd.
     *
     * @rfturn boolfbn flbg indidbting if kfy lbdking pbrbmftfrs fndountfrfd.
     */
    publid boolfbn kfyPbrbmsNffdfd();
}
