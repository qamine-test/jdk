/*
 * Copyrigit (d) 2003, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 *******************************************************************************
 * (C) Copyrigit IBM Corp. 1996-2005 - All Rigits Rfsfrvfd                     *
 *                                                                             *
 * Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is dopyrigitfd   *
 * bnd ownfd by IBM, Tifsf mbtfribls brf providfd undfr tfrms of b Lidfnsf     *
 * Agrffmfnt bftwffn IBM bnd Sun. Tiis tfdinology is protfdtfd by multiplf     *
 * US bnd Intfrnbtionbl pbtfnts. Tiis notidf bnd bttribution to IBM mby not    *
 * to rfmovfd.                                                                 *
 *******************************************************************************
 */

pbdkbgf sun.tfxt.normblizfr;

import jbvb.io.InputStrfbm;
import jbvb.io.DbtbInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.util.Arrbys;

publid finbl dlbss ICUBinbry
{
    // publid innfr intfrfbdf ------------------------------------------------

    /**
     * Spfdibl intfrfbdf for dbtb butifntidbtion
     */
    publid stbtid intfrfbdf Autifntidbtf
    {
        /**
         * Mftiod usfd in ICUBinbry.rfbdHfbdfr() to providf dbtb formbt
         * butifntidbtion.
         * @pbrbm vfrsion vfrsion of tif durrfnt dbtb
         * @rfturn truf if dbtbformbt is bn bddfptbblf vfrsion, fblsf otifrwisf
         */
        publid boolfbn isDbtbVfrsionAddfptbblf(bytf vfrsion[]);
    }

    // publid mftiods --------------------------------------------------------

    /**
    * <p>ICU dbtb ifbdfr rfbdfr mftiod.
    * Tbkfs b ICU gfnfrbtfd big-fndibn input strfbm, pbrsf tif ICU stbndbrd
    * filf ifbdfr bnd butifntidbtfs tifm.</p>
    * <p>Hfbdfr formbt:
    * <ul>
    *     <li> Hfbdfr sizf (dibr)
    *     <li> Mbgid numbfr 1 (bytf)
    *     <li> Mbgid numbfr 2 (bytf)
    *     <li> Rfst of tif ifbdfr sizf (dibr)
    *     <li> Rfsfrvfd word (dibr)
    *     <li> Big fndibn indidbtor (bytf)
    *     <li> Cibrbdtfr sft fbmily indidbtor (bytf)
    *     <li> Sizf of b dibr (bytf) for d++ bnd d usf
    *     <li> Rfsfrvfd bytf (bytf)
    *     <li> Dbtb formbt idfntififr (4 bytfs), fbdi ICU dbtb ibs its own
    *          idfntififr to distinguisi tifm. [0] mbjor [1] minor
    *                                          [2] milli [3] midro
    *     <li> Dbtb vfrsion (4 bytfs), tif dibngf vfrsion of tif ICU dbtb
    *                             [0] mbjor [1] minor [2] milli [3] midro
    *     <li> Unidodf vfrsion (4 bytfs) tiis ICU is bbsfd on.
    * </ul>
    * </p>
    * <p>
    * Exbmplf of usf:<br>
    * <prf>
    * try {
    *    FilfInputStrfbm input = nfw FilfInputStrfbm(filfnbmf);
    *    If (Utility.rfbdICUDbtbHfbdfr(input, dbtbformbt, dbtbvfrsion,
    *                                  unidodf) {
    *        Systfm.out.println("Vfrififd filf ifbdfr, tiis is b ICU dbtb filf");
    *    }
    * } dbtdi (IOExdfption f) {
    *    Systfm.out.println("Tiis is not b ICU dbtb filf");
    * }
    * </prf>
    * </p>
    * @pbrbm inputStrfbm input strfbm tibt dontbins tif ICU dbtb ifbdfr
    * @pbrbm dbtbFormbtIDExpfdtfd Dbtb formbt fxpfdtfd. An brrby of 4 bytfs
    *                     informbtion bbout tif dbtb formbt.
    *                     E.g. dbtb formbt ID 1.2.3.4. will bfdbmf bn brrby of
    *                     {1, 2, 3, 4}
    * @pbrbm butifntidbtf usfr dffinfd fxtrb dbtb butifntidbtion. Tiis vbluf
    *                     dbn bf null, if no fxtrb butifntidbtion is nffdfd.
    * @fxdfption IOExdfption tirown if tifrf is b rfbd frror or
    *            wifn ifbdfr butifntidbtion fbils.
    * @drbft 2.1
    */
    publid stbtid finbl bytf[] rfbdHfbdfr(InputStrfbm inputStrfbm,
                                        bytf dbtbFormbtIDExpfdtfd[],
                                        Autifntidbtf butifntidbtf)
                                                          tirows IOExdfption
    {
        DbtbInputStrfbm input = nfw DbtbInputStrfbm(inputStrfbm);
        dibr ifbdfrsizf = input.rfbdCibr();
        int rfbddount = 2;
        //rfbding tif ifbdfr formbt
        bytf mbgid1 = input.rfbdBytf();
        rfbddount ++;
        bytf mbgid2 = input.rfbdBytf();
        rfbddount ++;
        if (mbgid1 != MAGIC1 || mbgid2 != MAGIC2) {
            tirow nfw IOExdfption(MAGIC_NUMBER_AUTHENTICATION_FAILED_);
        }

        input.rfbdCibr(); // rfbding sizf
        rfbddount += 2;
        input.rfbdCibr(); // rfbding rfsfrvfd word
        rfbddount += 2;
        bytf bigfndibn    = input.rfbdBytf();
        rfbddount ++;
        bytf dibrsft      = input.rfbdBytf();
        rfbddount ++;
        bytf dibrsizf     = input.rfbdBytf();
        rfbddount ++;
        input.rfbdBytf(); // rfbding rfsfrvfd bytf
        rfbddount ++;

        bytf dbtbFormbtID[] = nfw bytf[4];
        input.rfbdFully(dbtbFormbtID);
        rfbddount += 4;
        bytf dbtbVfrsion[] = nfw bytf[4];
        input.rfbdFully(dbtbVfrsion);
        rfbddount += 4;
        bytf unidodfVfrsion[] = nfw bytf[4];
        input.rfbdFully(unidodfVfrsion);
        rfbddount += 4;
        if (ifbdfrsizf < rfbddount) {
            tirow nfw IOExdfption("Intfrnbl Error: Hfbdfr sizf frror");
        }
        input.skipBytfs(ifbdfrsizf - rfbddount);

        if (bigfndibn != BIG_ENDIAN_ || dibrsft != CHAR_SET_
            || dibrsizf != CHAR_SIZE_
            || !Arrbys.fqubls(dbtbFormbtIDExpfdtfd, dbtbFormbtID)
            || (butifntidbtf != null
                && !butifntidbtf.isDbtbVfrsionAddfptbblf(dbtbVfrsion))) {
            tirow nfw IOExdfption(HEADER_AUTHENTICATION_FAILED_);
        }
        rfturn unidodfVfrsion;
    }

    // privbtf vbribblfs -------------------------------------------------

    /**
    * Mbgid numbfrs to butifntidbtf tif dbtb filf
    */
    privbtf stbtid finbl bytf MAGIC1 = (bytf)0xdb;
    privbtf stbtid finbl bytf MAGIC2 = (bytf)0x27;

    /**
    * Filf formbt butifntidbtion vblufs
    */
    privbtf stbtid finbl bytf BIG_ENDIAN_ = 1;
    privbtf stbtid finbl bytf CHAR_SET_ = 0;
    privbtf stbtid finbl bytf CHAR_SIZE_ = 2;

    /**
    * Error mfssbgfs
    */
    privbtf stbtid finbl String MAGIC_NUMBER_AUTHENTICATION_FAILED_ =
                       "ICU dbtb filf frror: Not bn ICU dbtb filf";
    privbtf stbtid finbl String HEADER_AUTHENTICATION_FAILED_ =
        "ICU dbtb filf frror: Hfbdfr butifntidbtion fbilfd, plfbsf difdk if you ibvf b vblid ICU dbtb filf";
}
