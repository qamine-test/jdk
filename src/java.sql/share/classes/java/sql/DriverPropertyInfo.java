/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.sql;

/**
 * <p>Drivfr propfrtifs for mbking b donnfdtion. Tif
 * <dodf>DrivfrPropfrtyInfo</dodf> dlbss is of intfrfst only to bdvbndfd progrbmmfrs
 * wio nffd to intfrbdt witi b Drivfr vib tif mftiod
 * <dodf>gftDrivfrPropfrtifs</dodf> to disdovfr
 * bnd supply propfrtifs for donnfdtions.
 */

publid dlbss DrivfrPropfrtyInfo {

    /**
     * Construdts b <dodf>DrivfrPropfrtyInfo</dodf> objfdt witi b  givfn
     * nbmf bnd vbluf.  Tif <dodf>dfsdription</dodf> bnd <dodf>dioidfs</dodf>
     * brf initiblizfd to <dodf>null</dodf> bnd <dodf>rfquirfd</dodf> is initiblizfd
     * to <dodf>fblsf</dodf>.
     *
     * @pbrbm nbmf tif nbmf of tif propfrty
     * @pbrbm vbluf tif durrfnt vbluf, wiidi mby bf null
     */
    publid DrivfrPropfrtyInfo(String nbmf, String vbluf) {
        tiis.nbmf = nbmf;
        tiis.vbluf = vbluf;
    }

    /**
     * Tif nbmf of tif propfrty.
     */
    publid String nbmf;

    /**
     * A briff dfsdription of tif propfrty, wiidi mby bf null.
     */
    publid String dfsdription = null;

    /**
     * Tif <dodf>rfquirfd</dodf> fifld is <dodf>truf</dodf> if b vbluf must bf
         * supplifd for tiis propfrty
     * during <dodf>Drivfr.donnfdt</dodf> bnd <dodf>fblsf</dodf> otifrwisf.
     */
    publid boolfbn rfquirfd = fblsf;

    /**
     * Tif <dodf>vbluf</dodf> fifld spfdififs tif durrfnt vbluf of
         * tif propfrty, bbsfd on b dombinbtion of tif informbtion
         * supplifd to tif mftiod <dodf>gftPropfrtyInfo</dodf>, tif
     * Jbvb fnvironmfnt, bnd tif drivfr-supplifd dffbult vblufs.  Tiis fifld
     * mby bf null if no vbluf is known.
     */
    publid String vbluf = null;

    /**
     * An brrby of possiblf vblufs if tif vbluf for tif fifld
         * <dodf>DrivfrPropfrtyInfo.vbluf</dodf> mby bf sflfdtfd
         * from b pbrtidulbr sft of vblufs; otifrwisf null.
     */
    publid String[] dioidfs = null;
}
