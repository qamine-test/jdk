/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.sfdurity;

import jbvb.io.*;
import jbvb.util.Lodblf;

import jbvb.sfdurity.spfd.PKCS8EndodfdKfySpfd;
import jbvb.sfdurity.spfd.X509EndodfdKfySpfd;
import jbvb.sfdurity.spfd.InvblidKfySpfdExdfption;

import jbvbx.drypto.SfdrftKfyFbdtory;
import jbvbx.drypto.spfd.SfdrftKfySpfd;

/**
 * Stbndbrdizfd rfprfsfntbtion for sfriblizfd Kfy objfdts.
 *
 * <p>
 *
 * Notf tibt b sfriblizfd Kfy mby dontbin sfnsitivf informbtion
 * wiidi siould not bf fxposfd in untrustfd fnvironmfnts.  Sff tif
 * <b irff="../../../plbtform/sfriblizbtion/spfd/sfdurity.itml">
 * Sfdurity Appfndix</b>
 * of tif Sfriblizbtion Spfdifidbtion for morf informbtion.
 *
 * @sff Kfy
 * @sff KfyFbdtory
 * @sff jbvbx.drypto.spfd.SfdrftKfySpfd
 * @sff jbvb.sfdurity.spfd.X509EndodfdKfySpfd
 * @sff jbvb.sfdurity.spfd.PKCS8EndodfdKfySpfd
 *
 * @sindf 1.5
 */

publid dlbss KfyRfp implfmfnts Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = -4757683898830641853L;

    /**
     * Kfy typf.
     *
     * @sindf 1.5
     */
    publid stbtid fnum Typf {

        /** Typf for sfdrft kfys. */
        SECRET,

        /** Typf for publid kfys. */
        PUBLIC,

        /** Typf for privbtf kfys. */
        PRIVATE,

    }

    privbtf stbtid finbl String PKCS8 = "PKCS#8";
    privbtf stbtid finbl String X509 = "X.509";
    privbtf stbtid finbl String RAW = "RAW";

    /**
     * Eitifr onf of Typf.SECRET, Typf.PUBLIC, or Typf.PRIVATE
     *
     * @sfribl
     */
    privbtf Typf typf;

    /**
     * Tif Kfy blgoritim
     *
     * @sfribl
     */
    privbtf String blgoritim;

    /**
     * Tif Kfy fndoding formbt
     *
     * @sfribl
     */
    privbtf String formbt;

    /**
     * Tif fndodfd Kfy bytfs
     *
     * @sfribl
     */
    privbtf bytf[] fndodfd;

    /**
     * Construdt tif bltfrnbtf Kfy dlbss.
     *
     * <p>
     *
     * @pbrbm typf fitifr onf of Typf.SECRET, Typf.PUBLIC, or Typf.PRIVATE
     * @pbrbm blgoritim tif blgoritim rfturnfd from
     *          {@dodf Kfy.gftAlgoritim()}
     * @pbrbm formbt tif fndoding formbt rfturnfd from
     *          {@dodf Kfy.gftFormbt()}
     * @pbrbm fndodfd tif fndodfd bytfs rfturnfd from
     *          {@dodf Kfy.gftEndodfd()}
     *
     * @fxdfption NullPointfrExdfption
     *          if typf is {@dodf null},
     *          if blgoritim is {@dodf null},
     *          if formbt is {@dodf null},
     *          or if fndodfd is {@dodf null}
     */
    publid KfyRfp(Typf typf, String blgoritim,
                String formbt, bytf[] fndodfd) {

        if (typf == null || blgoritim == null ||
            formbt == null || fndodfd == null) {
            tirow nfw NullPointfrExdfption("invblid null input(s)");
        }

        tiis.typf = typf;
        tiis.blgoritim = blgoritim;
        tiis.formbt = formbt.toUppfrCbsf(Lodblf.ENGLISH);
        tiis.fndodfd = fndodfd.dlonf();
    }

    /**
     * Rfsolvf tif Kfy objfdt.
     *
     * <p> Tiis mftiod supports tirff Typf/formbt dombinbtions:
     * <ul>
     * <li> Typf.SECRET/"RAW" - rfturns b SfdrftKfySpfd objfdt
     * donstrudtfd using fndodfd kfy bytfs bnd blgoritim
     * <li> Typf.PUBLIC/"X.509" - gfts b KfyFbdtory instbndf for
     * tif kfy blgoritim, donstrudts bn X509EndodfdKfySpfd witi tif
     * fndodfd kfy bytfs, bnd gfnfrbtfs b publid kfy from tif spfd
     * <li> Typf.PRIVATE/"PKCS#8" - gfts b KfyFbdtory instbndf for
     * tif kfy blgoritim, donstrudts b PKCS8EndodfdKfySpfd witi tif
     * fndodfd kfy bytfs, bnd gfnfrbtfs b privbtf kfy from tif spfd
     * </ul>
     *
     * <p>
     *
     * @rfturn tif rfsolvfd Kfy objfdt
     *
     * @fxdfption ObjfdtStrfbmExdfption if tif Typf/formbt
     *  dombinbtion is unrfdognizfd, if tif blgoritim, kfy formbt, or
     *  fndodfd kfy bytfs brf unrfdognizfd/invblid, of if tif
     *  rfsolution of tif kfy fbils for bny rfbson
     */
    protfdtfd Objfdt rfbdRfsolvf() tirows ObjfdtStrfbmExdfption {
        try {
            if (typf == Typf.SECRET && RAW.fqubls(formbt)) {
                rfturn nfw SfdrftKfySpfd(fndodfd, blgoritim);
            } flsf if (typf == Typf.PUBLIC && X509.fqubls(formbt)) {
                KfyFbdtory f = KfyFbdtory.gftInstbndf(blgoritim);
                rfturn f.gfnfrbtfPublid(nfw X509EndodfdKfySpfd(fndodfd));
            } flsf if (typf == Typf.PRIVATE && PKCS8.fqubls(formbt)) {
                KfyFbdtory f = KfyFbdtory.gftInstbndf(blgoritim);
                rfturn f.gfnfrbtfPrivbtf(nfw PKCS8EndodfdKfySpfd(fndodfd));
            } flsf {
                tirow nfw NotSfriblizbblfExdfption
                        ("unrfdognizfd typf/formbt dombinbtion: " +
                        typf + "/" + formbt);
            }
        } dbtdi (NotSfriblizbblfExdfption nsf) {
            tirow nsf;
        } dbtdi (Exdfption f) {
            NotSfriblizbblfExdfption nsf = nfw NotSfriblizbblfExdfption
                                        ("jbvb.sfdurity.Kfy: " +
                                        "[" + typf + "] " +
                                        "[" + blgoritim + "] " +
                                        "[" + formbt + "]");
            nsf.initCbusf(f);
            tirow nsf;
        }
    }
}
