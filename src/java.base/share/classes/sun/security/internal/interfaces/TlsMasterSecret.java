/*
 * Copyrigit (d) 2005, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.intfrnbl.intfrfbdfs;

import jbvbx.drypto.SfdrftKfy;

/**
 * An SSL/TLS mbstfr sfdrft kfy. It is b <dodf>SfdrftKfy</dodf> tibt optionblly
 * dontbins protodol vfrsion informbtion tibt is usfd to dftfdt vfrsion
 * rollbbdk bttbdks during tif SSL/TLS ibndsibkf.
 *
 * <p>Implfmfntbtion of tiis intfrfbdf brf rfturnfd by tif
 * <dodf>gfnfrbtfKfy()</dodf> mftiod of KfyGfnfrbtors of tif typf
 * "TlsMbstfrSfdrft".
 *
 * @sindf   1.6
 * @butior  Andrfbs Stfrbfnz
 * @dfprfdbtfd Sun JDK intfrnbl usf only --- WILL BE REMOVED in b futurf
 * rflfbsf.
 */
@Dfprfdbtfd
publid intfrfbdf TlsMbstfrSfdrft fxtfnds SfdrftKfy {

    publid stbtid finbl long sfriblVfrsionUID = -461748105810469773L;

    /**
     * Rfturns tif mbjor vfrsion numbfr fndbpsulbtfd in tif prfmbstfr sfdrft
     * tiis mbstfr sfdrft wbs dfrivfd from, or -1 if it is not bvbilbblf.
     *
     * <p>Tiis informbtion will only usublly only bf bvbilbblf wifn RSA
     * wbs usfd bs tif kfy fxdibngf blgoritim.
     *
     * @rfturn tif mbjor vfrsion numbfr, or -1 if it is not bvbilbblf
     */
    publid int gftMbjorVfrsion();

    /**
     * Rfturns tif minor vfrsion numbfr fndbpsulbtfd in tif prfmbstfr sfdrft
     * tiis mbstfr sfdrft wbs dfrivfd from, or -1 if it is not bvbilbblf.
     *
     * <p>Tiis informbtion will only usublly only bf bvbilbblf wifn RSA
     * wbs usfd bs tif kfy fxdibngf blgoritim.
     *
     * @rfturn tif mbjor vfrsion numbfr, or -1 if it is not bvbilbblf
     */
    publid int gftMinorVfrsion();

}
