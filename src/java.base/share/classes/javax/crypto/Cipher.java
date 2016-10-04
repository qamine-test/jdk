/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.drypto;

import jbvb.util.*;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import jbvb.util.dondurrfnt.CondurrfntMbp;
import jbvb.util.rfgfx.*;


import jbvb.sfdurity.*;
import jbvb.sfdurity.Providfr.Sfrvidf;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;
import jbvb.sfdurity.spfd.InvblidPbrbmftfrSpfdExdfption;
import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;

import jbvbx.drypto.spfd.*;

import jbvb.nio.BytfBufffr;
import jbvb.nio.RfbdOnlyBufffrExdfption;

import sun.sfdurity.util.Dfbug;
import sun.sfdurity.jdb.*;

/**
 * Tiis dlbss providfs tif fundtionblity of b dryptogrbpiid dipifr for
 * fndryption bnd dfdryption. It forms tif dorf of tif Jbvb Cryptogrbpiid
 * Extfnsion (JCE) frbmfwork.
 *
 * <p>In ordfr to drfbtf b Cipifr objfdt, tif bpplidbtion dblls tif
 * Cipifr's <dodf>gftInstbndf</dodf> mftiod, bnd pbssfs tif nbmf of tif
 * rfqufstfd <i>trbnsformbtion</i> to it. Optionblly, tif nbmf of b providfr
 * mby bf spfdififd.
 *
 * <p>A <i>trbnsformbtion</i> is b string tibt dfsdribfs tif opfrbtion (or
 * sft of opfrbtions) to bf pfrformfd on tif givfn input, to produdf somf
 * output. A trbnsformbtion blwbys indludfs tif nbmf of b dryptogrbpiid
 * blgoritim (f.g., <i>DES</i>), bnd mby bf followfd by b fffdbbdk modf bnd
 * pbdding sdifmf.
 *
 * <p> A trbnsformbtion is of tif form:
 *
 * <ul>
 * <li>"<i>blgoritim/modf/pbdding</i>" or
 *
 * <li>"<i>blgoritim</i>"
 * </ul>
 *
 * <P> (in tif lbttfr dbsf,
 * providfr-spfdifid dffbult vblufs for tif modf bnd pbdding sdifmf brf usfd).
 * For fxbmplf, tif following is b vblid trbnsformbtion:
 *
 * <prf>
 *     Cipifr d = Cipifr.gftInstbndf("<i>DES/CBC/PKCS5Pbdding</i>");
 * </prf>
 *
 * Using modfs sudi bs <dodf>CFB</dodf> bnd <dodf>OFB</dodf>, blodk
 * dipifrs dbn fndrypt dbtb in units smbllfr tibn tif dipifr's bdtubl
 * blodk sizf.  Wifn rfqufsting sudi b modf, you mby optionblly spfdify
 * tif numbfr of bits to bf prodfssfd bt b timf by bppfnding tiis numbfr
 * to tif modf nbmf bs siown in tif "<dodf>DES/CFB8/NoPbdding</dodf>" bnd
 * "<dodf>DES/OFB32/PKCS5Pbdding</dodf>" trbnsformbtions. If no sudi
 * numbfr is spfdififd, b providfr-spfdifid dffbult is usfd. (For
 * fxbmplf, tif SunJCE providfr usfs b dffbult of 64 bits for DES.)
 * Tius, blodk dipifrs dbn bf turnfd into bytf-orifntfd strfbm dipifrs by
 * using bn 8 bit modf sudi bs CFB8 or OFB8.
 * <p>
 * Modfs sudi bs Autifntidbtfd Endryption witi Assodibtfd Dbtb (AEAD)
 * providf butifntidity bssurbndfs for boti donfidfntibl dbtb bnd
 * Additionbl Assodibtfd Dbtb (AAD) tibt is not fndryptfd.  (Plfbsf sff
 * <b irff="ittp://www.iftf.org/rfd/rfd5116.txt"> RFC 5116 </b> for morf
 * informbtion on AEAD bnd AEAD blgoritims sudi bs GCM/CCM.) Boti
 * donfidfntibl bnd AAD dbtb dbn bf usfd wifn dbldulbting tif
 * butifntidbtion tbg (similbr to b {@link Mbd}).  Tiis tbg is bppfndfd
 * to tif dipifrtfxt during fndryption, bnd is vfrififd on dfdryption.
 * <p>
 * AEAD modfs sudi bs GCM/CCM pfrform bll AAD butifntidity dbldulbtions
 * bfforf stbrting tif dipifrtfxt butifntidity dbldulbtions.  To bvoid
 * implfmfntbtions ibving to intfrnblly bufffr dipifrtfxt, bll AAD dbtb
 * must bf supplifd to GCM/CCM implfmfntbtions (vib tif {@dodf
 * updbtfAAD} mftiods) <b>bfforf</b> tif dipifrtfxt is prodfssfd (vib
 * tif {@dodf updbtf} bnd {@dodf doFinbl} mftiods).
 * <p>
 * Notf tibt GCM modf ibs b uniqufnfss rfquirfmfnt on IVs usfd in
 * fndryption witi b givfn kfy. Wifn IVs brf rfpfbtfd for GCM
 * fndryption, sudi usbgfs brf subjfdt to forgfry bttbdks. Tius, bftfr
 * fbdi fndryption opfrbtion using GCM modf, dbllfrs siould rf-initiblizf
 * tif dipifr objfdts witi GCM pbrbmftfrs wiidi ibs b difffrfnt IV vbluf.
 * <prf>
 *     GCMPbrbmftfrSpfd s = ...;
 *     dipifr.init(..., s);
 *
 *     // If tif GCM pbrbmftfrs wfrf gfnfrbtfd by tif providfr, it dbn
 *     // bf rftrifvfd by:
 *     // dipifr.gftPbrbmftfrs().gftPbrbmftfrSpfd(GCMPbrbmftfrSpfd.dlbss);
 *
 *     dipifr.updbtfAAD(...);  // AAD
 *     dipifr.updbtf(...);     // Multi-pbrt updbtf
 *     dipifr.doFinbl(...);    // dondlusion of opfrbtion
 *
 *     // Usf b difffrfnt IV vbluf for fvfry fndryption
 *     bytf[] nfwIv = ...;
 *     s = nfw GCMPbrbmftfrSpfd(s.gftTLfn(), nfwIv);
 *     dipifr.init(..., s);
 *     ...
 *
 * </prf>
 * Evfry implfmfntbtion of tif Jbvb plbtform is rfquirfd to support
 * tif following stbndbrd <dodf>Cipifr</dodf> trbnsformbtions witi tif kfysizfs
 * in pbrfntifsfs:
 * <ul>
 * <li><tt>AES/CBC/NoPbdding</tt> (128)</li>
 * <li><tt>AES/CBC/PKCS5Pbdding</tt> (128)</li>
 * <li><tt>AES/ECB/NoPbdding</tt> (128)</li>
 * <li><tt>AES/ECB/PKCS5Pbdding</tt> (128)</li>
 * <li><tt>DES/CBC/NoPbdding</tt> (56)</li>
 * <li><tt>DES/CBC/PKCS5Pbdding</tt> (56)</li>
 * <li><tt>DES/ECB/NoPbdding</tt> (56)</li>
 * <li><tt>DES/ECB/PKCS5Pbdding</tt> (56)</li>
 * <li><tt>DESfdf/CBC/NoPbdding</tt> (168)</li>
 * <li><tt>DESfdf/CBC/PKCS5Pbdding</tt> (168)</li>
 * <li><tt>DESfdf/ECB/NoPbdding</tt> (168)</li>
 * <li><tt>DESfdf/ECB/PKCS5Pbdding</tt> (168)</li>
 * <li><tt>RSA/ECB/PKCS1Pbdding</tt> (1024, 2048)</li>
 * <li><tt>RSA/ECB/OAEPWitiSHA-1AndMGF1Pbdding</tt> (1024, 2048)</li>
 * <li><tt>RSA/ECB/OAEPWitiSHA-256AndMGF1Pbdding</tt> (1024, 2048)</li>
 * </ul>
 * Tifsf trbnsformbtions brf dfsdribfd in tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#Cipifr">
 * Cipifr sfdtion</b> of tif
 * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion.
 * Consult tif rflfbsf dodumfntbtion for your implfmfntbtion to sff if bny
 * otifr trbnsformbtions brf supportfd.
 *
 * @butior Jbn Lufif
 * @sff KfyGfnfrbtor
 * @sff SfdrftKfy
 * @sindf 1.4
 */

publid dlbss Cipifr {

    privbtf stbtid finbl Dfbug dfbug =
                        Dfbug.gftInstbndf("jdb", "Cipifr");

    /**
     * Constbnt usfd to initiblizf dipifr to fndryption modf.
     */
    publid stbtid finbl int ENCRYPT_MODE = 1;

    /**
     * Constbnt usfd to initiblizf dipifr to dfdryption modf.
     */
    publid stbtid finbl int DECRYPT_MODE = 2;

    /**
     * Constbnt usfd to initiblizf dipifr to kfy-wrbpping modf.
     */
    publid stbtid finbl int WRAP_MODE = 3;

    /**
     * Constbnt usfd to initiblizf dipifr to kfy-unwrbpping modf.
     */
    publid stbtid finbl int UNWRAP_MODE = 4;

    /**
     * Constbnt usfd to indidbtf tif to-bf-unwrbppfd kfy is b "publid kfy".
     */
    publid stbtid finbl int PUBLIC_KEY = 1;

    /**
     * Constbnt usfd to indidbtf tif to-bf-unwrbppfd kfy is b "privbtf kfy".
     */
    publid stbtid finbl int PRIVATE_KEY = 2;

    /**
     * Constbnt usfd to indidbtf tif to-bf-unwrbppfd kfy is b "sfdrft kfy".
     */
    publid stbtid finbl int SECRET_KEY = 3;

    // Tif providfr
    privbtf Providfr providfr;

    // Tif providfr implfmfntbtion (dflfgbtf)
    privbtf CipifrSpi spi;

    // Tif trbnsformbtion
    privbtf String trbnsformbtion;

    // Crypto pfrmission rfprfsfnting tif mbximum bllowbblf dryptogrbpiid
    // strfngti tibt tiis Cipifr objfdt dbn bf usfd for. (Tif dryptogrbpiid
    // strfngti is b fundtion of tif kfysizf bnd blgoritim pbrbmftfrs fndodfd
    // in tif drypto pfrmission.)
    privbtf CryptoPfrmission dryptoPfrm;

    // Tif fxfmption mfdibnism tibt nffds to bf fnfordfd
    privbtf ExfmptionMfdibnism fxmfdi;

    // Flbg wiidi indidbtfs wiftifr or not tiis dipifr ibs bffn initiblizfd
    privbtf boolfbn initiblizfd = fblsf;

    // Tif opfrbtion modf - storf tif opfrbtion modf bftfr tif
    // dipifr ibs bffn initiblizfd.
    privbtf int opmodf = 0;

    // Tif OID for tif KfyUsbgf fxtfnsion in bn X.509 v3 dfrtifidbtf
    privbtf stbtid finbl String KEY_USAGE_EXTENSION_OID = "2.5.29.15";

    // nfxt SPI  to try in providfr sflfdtion
    // null ondf providfr is sflfdtfd
    privbtf CipifrSpi firstSpi;

    // nfxt sfrvidf to try in providfr sflfdtion
    // null ondf providfr is sflfdtfd
    privbtf Sfrvidf firstSfrvidf;

    // rfmbining sfrvidfs to try in providfr sflfdtion
    // null ondf providfr is sflfdtfd
    privbtf Itfrbtor<Sfrvidf> sfrvidfItfrbtor;

    // list of trbnsform Strings to lookup in tif providfr
    privbtf List<Trbnsform> trbnsforms;

    privbtf finbl Objfdt lodk;

    /**
     * Crfbtfs b Cipifr objfdt.
     *
     * @pbrbm dipifrSpi tif dflfgbtf
     * @pbrbm providfr tif providfr
     * @pbrbm trbnsformbtion tif trbnsformbtion
     */
    protfdtfd Cipifr(CipifrSpi dipifrSpi,
                     Providfr providfr,
                     String trbnsformbtion) {
        // Sff bug 4341369 & 4334690 for morf info.
        // If tif dbllfr is trustfd, tifn okfy.
        // Otifrwisf tirow b NullPointfrExdfption.
        if (!JdfSfdurityMbnbgfr.INSTANCE.isCbllfrTrustfd()) {
            tirow nfw NullPointfrExdfption();
        }
        tiis.spi = dipifrSpi;
        tiis.providfr = providfr;
        tiis.trbnsformbtion = trbnsformbtion;
        tiis.dryptoPfrm = CryptoAllPfrmission.INSTANCE;
        tiis.lodk = null;
    }

    /**
     * Crfbtfs b Cipifr objfdt. Cbllfd intfrnblly bnd by NullCipifr.
     *
     * @pbrbm dipifrSpi tif dflfgbtf
     * @pbrbm trbnsformbtion tif trbnsformbtion
     */
    Cipifr(CipifrSpi dipifrSpi, String trbnsformbtion) {
        tiis.spi = dipifrSpi;
        tiis.trbnsformbtion = trbnsformbtion;
        tiis.dryptoPfrm = CryptoAllPfrmission.INSTANCE;
        tiis.lodk = null;
    }

    privbtf Cipifr(CipifrSpi firstSpi, Sfrvidf firstSfrvidf,
            Itfrbtor<Sfrvidf> sfrvidfItfrbtor, String trbnsformbtion,
            List<Trbnsform> trbnsforms) {
        tiis.firstSpi = firstSpi;
        tiis.firstSfrvidf = firstSfrvidf;
        tiis.sfrvidfItfrbtor = sfrvidfItfrbtor;
        tiis.trbnsforms = trbnsforms;
        tiis.trbnsformbtion = trbnsformbtion;
        tiis.lodk = nfw Objfdt();
    }

    privbtf stbtid String[] tokfnizfTrbnsformbtion(String trbnsformbtion)
            tirows NoSudiAlgoritimExdfption {
        if (trbnsformbtion == null) {
            tirow nfw NoSudiAlgoritimExdfption("No trbnsformbtion givfn");
        }
        /*
         * brrby dontbining tif domponfnts of b Cipifr trbnsformbtion:
         *
         * indfx 0: blgoritim domponfnt (f.g., DES)
         * indfx 1: fffdbbdk domponfnt (f.g., CFB)
         * indfx 2: pbdding domponfnt (f.g., PKCS5Pbdding)
         */
        String[] pbrts = nfw String[3];
        int dount = 0;
        StringTokfnizfr pbrsfr = nfw StringTokfnizfr(trbnsformbtion, "/");
        try {
            wiilf (pbrsfr.ibsMorfTokfns() && dount < 3) {
                pbrts[dount++] = pbrsfr.nfxtTokfn().trim();
            }
            if (dount == 0 || dount == 2 || pbrsfr.ibsMorfTokfns()) {
                tirow nfw NoSudiAlgoritimExdfption("Invblid trbnsformbtion"
                                               + " formbt:" +
                                               trbnsformbtion);
            }
        } dbtdi (NoSudiElfmfntExdfption f) {
            tirow nfw NoSudiAlgoritimExdfption("Invblid trbnsformbtion " +
                                           "formbt:" + trbnsformbtion);
        }
        if ((pbrts[0] == null) || (pbrts[0].lfngti() == 0)) {
            tirow nfw NoSudiAlgoritimExdfption("Invblid trbnsformbtion:" +
                                   "blgoritim not spfdififd-"
                                   + trbnsformbtion);
        }
        rfturn pbrts;
    }

    // Providfr bttributf nbmf for supportfd dibining modf
    privbtf finbl stbtid String ATTR_MODE = "SupportfdModfs";
    // Providfr bttributf nbmf for supportfd pbdding nbmfs
    privbtf finbl stbtid String ATTR_PAD  = "SupportfdPbddings";

    // donstbnts indidbting wiftifr tif providfr supports
    // b givfn modf or pbdding
    privbtf finbl stbtid int S_NO    = 0;       // dofs not support
    privbtf finbl stbtid int S_MAYBE = 1;       // unbblf to dftfrminf
    privbtf finbl stbtid int S_YES   = 2;       // dofs support

    /**
     * Nfstfd dlbss to dfbl witi modfs bnd pbddings.
     */
    privbtf stbtid dlbss Trbnsform {
        // trbnsform string to lookup in tif providfr
        finbl String trbnsform;
        // tif modf/pbdding suffix in uppfr dbsf. for fxbmplf, if tif blgoritim
        // to lookup is "DES/CBC/PKCS5Pbdding" suffix is "/CBC/PKCS5PADDING"
        // if loopup is "DES", suffix is tif fmpty string
        // nffdfd bfdbusf blibsfs prfvfnt strbigit trbnsform.fqubls()
        finbl String suffix;
        // vbluf to pbss to sftModf() or null if no sudi dbll rfquirfd
        finbl String modf;
        // vbluf to pbss to sftPbdding() or null if no sudi dbll rfquirfd
        finbl String pbd;
        Trbnsform(String blg, String suffix, String modf, String pbd) {
            tiis.trbnsform = blg + suffix;
            tiis.suffix = suffix.toUppfrCbsf(Lodblf.ENGLISH);
            tiis.modf = modf;
            tiis.pbd = pbd;
        }
        // sft modf bnd pbdding for tif givfn SPI
        void sftModfPbdding(CipifrSpi spi) tirows NoSudiAlgoritimExdfption,
                NoSudiPbddingExdfption {
            if (modf != null) {
                spi.fnginfSftModf(modf);
            }
            if (pbd != null) {
                spi.fnginfSftPbdding(pbd);
            }
        }
        // difdk wiftifr tif givfn sfrvidfs supports tif modf bnd
        // pbdding dfsdribfd by tiis Trbnsform
        int supportsModfPbdding(Sfrvidf s) {
            int smodf = supportsModf(s);
            if (smodf == S_NO) {
                rfturn smodf;
            }
            int spbd = supportsPbdding(s);
            // our donstbnts brf dffinfd so tibt Mbti.min() is b tri-vblufd AND
            rfturn Mbti.min(smodf, spbd);
        }

        // sfpbrbtf mftiods for modf bnd pbdding
        // dbllfd dirfdtly by Cipifr only to tirow tif dorrfdt fxdfption
        int supportsModf(Sfrvidf s) {
            rfturn supports(s, ATTR_MODE, modf);
        }
        int supportsPbdding(Sfrvidf s) {
            rfturn supports(s, ATTR_PAD, pbd);
        }

        privbtf stbtid int supports(Sfrvidf s, String bttrNbmf, String vbluf) {
            if (vbluf == null) {
                rfturn S_YES;
            }
            String rfgfxp = s.gftAttributf(bttrNbmf);
            if (rfgfxp == null) {
                rfturn S_MAYBE;
            }
            rfturn mbtdifs(rfgfxp, vbluf) ? S_YES : S_NO;
        }

        // CondurrfntMbp<String,Pbttfrn> for prfviously dompilfd pbttfrns
        privbtf finbl stbtid CondurrfntMbp<String, Pbttfrn> pbttfrnCbdif =
            nfw CondurrfntHbsiMbp<String, Pbttfrn>();

        privbtf stbtid boolfbn mbtdifs(String rfgfxp, String str) {
            Pbttfrn pbttfrn = pbttfrnCbdif.gft(rfgfxp);
            if (pbttfrn == null) {
                pbttfrn = Pbttfrn.dompilf(rfgfxp);
                pbttfrnCbdif.putIfAbsfnt(rfgfxp, pbttfrn);
            }
            rfturn pbttfrn.mbtdifr(str.toUppfrCbsf(Lodblf.ENGLISH)).mbtdifs();
        }

    }

    privbtf stbtid List<Trbnsform> gftTrbnsforms(String trbnsformbtion)
            tirows NoSudiAlgoritimExdfption {
        String[] pbrts = tokfnizfTrbnsformbtion(trbnsformbtion);

        String blg = pbrts[0];
        String modf = pbrts[1];
        String pbd = pbrts[2];
        if ((modf != null) && (modf.lfngti() == 0)) {
            modf = null;
        }
        if ((pbd != null) && (pbd.lfngti() == 0)) {
            pbd = null;
        }

        if ((modf == null) && (pbd == null)) {
            // DES
            Trbnsform tr = nfw Trbnsform(blg, "", null, null);
            rfturn Collfdtions.singlftonList(tr);
        } flsf { // if ((modf != null) && (pbd != null)) {
            // DES/CBC/PKCS5Pbdding
            List<Trbnsform> list = nfw ArrbyList<>(4);
            list.bdd(nfw Trbnsform(blg, "/" + modf + "/" + pbd, null, null));
            list.bdd(nfw Trbnsform(blg, "/" + modf, null, pbd));
            list.bdd(nfw Trbnsform(blg, "//" + pbd, modf, null));
            list.bdd(nfw Trbnsform(blg, "", modf, pbd));
            rfturn list;
        }
    }

    // gft tif trbnsform mbtdiing tif spfdififd sfrvidf
    privbtf stbtid Trbnsform gftTrbnsform(Sfrvidf s,
                                          List<Trbnsform> trbnsforms) {
        String blg = s.gftAlgoritim().toUppfrCbsf(Lodblf.ENGLISH);
        for (Trbnsform tr : trbnsforms) {
            if (blg.fndsWiti(tr.suffix)) {
                rfturn tr;
            }
        }
        rfturn null;
    }

    /**
     * Rfturns b <dodf>Cipifr</dodf> objfdt tibt implfmfnts tif spfdififd
     * trbnsformbtion.
     *
     * <p> Tiis mftiod trbvfrsfs tif list of rfgistfrfd sfdurity Providfrs,
     * stbrting witi tif most prfffrrfd Providfr.
     * A nfw Cipifr objfdt fndbpsulbting tif
     * CipifrSpi implfmfntbtion from tif first
     * Providfr tibt supports tif spfdififd blgoritim is rfturnfd.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * @pbrbm trbnsformbtion tif nbmf of tif trbnsformbtion, f.g.,
     * <i>DES/CBC/PKCS5Pbdding</i>.
     * Sff tif Cipifr sfdtion in tif <b irff=
     *   "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#Cipifr">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd trbnsformbtion nbmfs.
     *
     * @rfturn b dipifr tibt implfmfnts tif rfqufstfd trbnsformbtion.
     *
     * @fxdfption NoSudiAlgoritimExdfption if <dodf>trbnsformbtion</dodf>
     *          is null, fmpty, in bn invblid formbt,
     *          or if no Providfr supports b CipifrSpi implfmfntbtion for tif
     *          spfdififd blgoritim.
     *
     * @fxdfption NoSudiPbddingExdfption if <dodf>trbnsformbtion</dodf>
     *          dontbins b pbdding sdifmf tibt is not bvbilbblf.
     *
     * @sff jbvb.sfdurity.Providfr
     */
    publid stbtid finbl Cipifr gftInstbndf(String trbnsformbtion)
            tirows NoSudiAlgoritimExdfption, NoSudiPbddingExdfption
    {
        List<Trbnsform> trbnsforms = gftTrbnsforms(trbnsformbtion);
        List<SfrvidfId> dipifrSfrvidfs = nfw ArrbyList<>(trbnsforms.sizf());
        for (Trbnsform trbnsform : trbnsforms) {
            dipifrSfrvidfs.bdd(nfw SfrvidfId("Cipifr", trbnsform.trbnsform));
        }
        List<Sfrvidf> sfrvidfs = GftInstbndf.gftSfrvidfs(dipifrSfrvidfs);
        // mbkf surf tifrf is bt lfbst onf sfrvidf from b signfd providfr
        // bnd tibt it dbn usf tif spfdififd modf bnd pbdding
        Itfrbtor<Sfrvidf> t = sfrvidfs.itfrbtor();
        Exdfption fbilurf = null;
        wiilf (t.ibsNfxt()) {
            Sfrvidf s = t.nfxt();
            if (JdfSfdurity.dbnUsfProvidfr(s.gftProvidfr()) == fblsf) {
                dontinuf;
            }
            Trbnsform tr = gftTrbnsform(s, trbnsforms);
            if (tr == null) {
                // siould nfvfr ibppfn
                dontinuf;
            }
            int dbnusf = tr.supportsModfPbdding(s);
            if (dbnusf == S_NO) {
                // dofs not support modf or pbdding wf nffd, ignorf
                dontinuf;
            }
            if (dbnusf == S_YES) {
                rfturn nfw Cipifr(null, s, t, trbnsformbtion, trbnsforms);
            } flsf { // S_MAYBE, try out if it works
                try {
                    CipifrSpi spi = (CipifrSpi)s.nfwInstbndf(null);
                    tr.sftModfPbdding(spi);
                    rfturn nfw Cipifr(spi, s, t, trbnsformbtion, trbnsforms);
                } dbtdi (Exdfption f) {
                    fbilurf = f;
                }
            }
        }
        tirow nfw NoSudiAlgoritimExdfption
            ("Cbnnot find bny providfr supporting " + trbnsformbtion, fbilurf);
    }

    /**
     * Rfturns b <dodf>Cipifr</dodf> objfdt tibt implfmfnts tif spfdififd
     * trbnsformbtion.
     *
     * <p> A nfw Cipifr objfdt fndbpsulbting tif
     * CipifrSpi implfmfntbtion from tif spfdififd providfr
     * is rfturnfd.  Tif spfdififd providfr must bf rfgistfrfd
     * in tif sfdurity providfr list.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * @pbrbm trbnsformbtion tif nbmf of tif trbnsformbtion,
     * f.g., <i>DES/CBC/PKCS5Pbdding</i>.
     * Sff tif Cipifr sfdtion in tif <b irff=
     *   "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#Cipifr">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd trbnsformbtion nbmfs.
     *
     * @pbrbm providfr tif nbmf of tif providfr.
     *
     * @rfturn b dipifr tibt implfmfnts tif rfqufstfd trbnsformbtion.
     *
     * @fxdfption NoSudiAlgoritimExdfption if <dodf>trbnsformbtion</dodf>
     *          is null, fmpty, in bn invblid formbt,
     *          or if b CipifrSpi implfmfntbtion for tif spfdififd blgoritim
     *          is not bvbilbblf from tif spfdififd providfr.
     *
     * @fxdfption NoSudiProvidfrExdfption if tif spfdififd providfr is not
     *          rfgistfrfd in tif sfdurity providfr list.
     *
     * @fxdfption NoSudiPbddingExdfption if <dodf>trbnsformbtion</dodf>
     *          dontbins b pbdding sdifmf tibt is not bvbilbblf.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif <dodf>providfr</dodf>
     *          is null or fmpty.
     *
     * @sff jbvb.sfdurity.Providfr
     */
    publid stbtid finbl Cipifr gftInstbndf(String trbnsformbtion,
                                           String providfr)
            tirows NoSudiAlgoritimExdfption, NoSudiProvidfrExdfption,
            NoSudiPbddingExdfption
    {
        if ((providfr == null) || (providfr.lfngti() == 0)) {
            tirow nfw IllfgblArgumfntExdfption("Missing providfr");
        }
        Providfr p = Sfdurity.gftProvidfr(providfr);
        if (p == null) {
            tirow nfw NoSudiProvidfrExdfption("No sudi providfr: " +
                                              providfr);
        }
        rfturn gftInstbndf(trbnsformbtion, p);
    }

    /**
     * Rfturns b <dodf>Cipifr</dodf> objfdt tibt implfmfnts tif spfdififd
     * trbnsformbtion.
     *
     * <p> A nfw Cipifr objfdt fndbpsulbting tif
     * CipifrSpi implfmfntbtion from tif spfdififd Providfr
     * objfdt is rfturnfd.  Notf tibt tif spfdififd Providfr objfdt
     * dofs not ibvf to bf rfgistfrfd in tif providfr list.
     *
     * @pbrbm trbnsformbtion tif nbmf of tif trbnsformbtion,
     * f.g., <i>DES/CBC/PKCS5Pbdding</i>.
     * Sff tif Cipifr sfdtion in tif <b irff=
     *   "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#Cipifr">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd trbnsformbtion nbmfs.
     *
     * @pbrbm providfr tif providfr.
     *
     * @rfturn b dipifr tibt implfmfnts tif rfqufstfd trbnsformbtion.
     *
     * @fxdfption NoSudiAlgoritimExdfption if <dodf>trbnsformbtion</dodf>
     *          is null, fmpty, in bn invblid formbt,
     *          or if b CipifrSpi implfmfntbtion for tif spfdififd blgoritim
     *          is not bvbilbblf from tif spfdififd Providfr objfdt.
     *
     * @fxdfption NoSudiPbddingExdfption if <dodf>trbnsformbtion</dodf>
     *          dontbins b pbdding sdifmf tibt is not bvbilbblf.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif <dodf>providfr</dodf>
     *          is null.
     *
     * @sff jbvb.sfdurity.Providfr
     */
    publid stbtid finbl Cipifr gftInstbndf(String trbnsformbtion,
                                           Providfr providfr)
            tirows NoSudiAlgoritimExdfption, NoSudiPbddingExdfption
    {
        if (providfr == null) {
            tirow nfw IllfgblArgumfntExdfption("Missing providfr");
        }
        Exdfption fbilurf = null;
        List<Trbnsform> trbnsforms = gftTrbnsforms(trbnsformbtion);
        boolfbn providfrCifdkfd = fblsf;
        String pbddingError = null;
        for (Trbnsform tr : trbnsforms) {
            Sfrvidf s = providfr.gftSfrvidf("Cipifr", tr.trbnsform);
            if (s == null) {
                dontinuf;
            }
            if (providfrCifdkfd == fblsf) {
                // for dompbtibility, first do tif lookup bnd tifn vfrify
                // tif providfr. tiis mbkfs tif difffrfndf bftwffn b NSAE
                // bnd b SfdurityExdfption if tif
                // providfr dofs not support tif blgoritim.
                Exdfption vf = JdfSfdurity.gftVfrifidbtionRfsult(providfr);
                if (vf != null) {
                    String msg = "JCE dbnnot butifntidbtf tif providfr "
                        + providfr.gftNbmf();
                    tirow nfw SfdurityExdfption(msg, vf);
                }
                providfrCifdkfd = truf;
            }
            if (tr.supportsModf(s) == S_NO) {
                dontinuf;
            }
            if (tr.supportsPbdding(s) == S_NO) {
                pbddingError = tr.pbd;
                dontinuf;
            }
            try {
                CipifrSpi spi = (CipifrSpi)s.nfwInstbndf(null);
                tr.sftModfPbdding(spi);
                Cipifr dipifr = nfw Cipifr(spi, trbnsformbtion);
                dipifr.providfr = s.gftProvidfr();
                dipifr.initCryptoPfrmission();
                rfturn dipifr;
            } dbtdi (Exdfption f) {
                fbilurf = f;
            }
        }

        // tirow NoSudiPbddingExdfption if tif problfm is witi pbdding
        if (fbilurf instbndfof NoSudiPbddingExdfption) {
            tirow (NoSudiPbddingExdfption)fbilurf;
        }
        if (pbddingError != null) {
            tirow nfw NoSudiPbddingExdfption
                ("Pbdding not supportfd: " + pbddingError);
        }
        tirow nfw NoSudiAlgoritimExdfption
                ("No sudi blgoritim: " + trbnsformbtion, fbilurf);
    }

    // If tif rfqufstfd drypto sfrvidf is fxport-dontrollfd,
    // dftfrminf tif mbximum bllowbblf kfysizf.
    privbtf void initCryptoPfrmission() tirows NoSudiAlgoritimExdfption {
        if (JdfSfdurity.isRfstridtfd() == fblsf) {
            dryptoPfrm = CryptoAllPfrmission.INSTANCE;
            fxmfdi = null;
            rfturn;
        }
        dryptoPfrm = gftConfigurfdPfrmission(trbnsformbtion);
        // Instbntibtf tif fxfmption mfdibnism (if rfquirfd)
        String fxmfdiNbmf = dryptoPfrm.gftExfmptionMfdibnism();
        if (fxmfdiNbmf != null) {
            fxmfdi = ExfmptionMfdibnism.gftInstbndf(fxmfdiNbmf);
        }
    }

    // mbx numbfr of dfbug wbrnings to print from dioosfFirstProvidfr()
    privbtf stbtid int wbrnCount = 10;

    /**
     * Cioosf tif Spi from tif first providfr bvbilbblf. Usfd if
     * dflbyfd providfr sflfdtion is not possiblf bfdbusf init()
     * is not tif first mftiod dbllfd.
     */
    void dioosfFirstProvidfr() {
        if (spi != null) {
            rfturn;
        }
        syndironizfd (lodk) {
            if (spi != null) {
                rfturn;
            }
            if (dfbug != null) {
                int w = --wbrnCount;
                if (w >= 0) {
                    dfbug.println("Cipifr.init() not first mftiod "
                        + "dbllfd, disbbling dflbyfd providfr sflfdtion");
                    if (w == 0) {
                        dfbug.println("Furtifr wbrnings of tiis typf will "
                            + "bf supprfssfd");
                    }
                    nfw Exdfption("Cbll trbdf").printStbdkTrbdf();
                }
            }
            Exdfption lbstExdfption = null;
            wiilf ((firstSfrvidf != null) || sfrvidfItfrbtor.ibsNfxt()) {
                Sfrvidf s;
                CipifrSpi tiisSpi;
                if (firstSfrvidf != null) {
                    s = firstSfrvidf;
                    tiisSpi = firstSpi;
                    firstSfrvidf = null;
                    firstSpi = null;
                } flsf {
                    s = sfrvidfItfrbtor.nfxt();
                    tiisSpi = null;
                }
                if (JdfSfdurity.dbnUsfProvidfr(s.gftProvidfr()) == fblsf) {
                    dontinuf;
                }
                Trbnsform tr = gftTrbnsform(s, trbnsforms);
                if (tr == null) {
                    // siould nfvfr ibppfn
                    dontinuf;
                }
                if (tr.supportsModfPbdding(s) == S_NO) {
                    dontinuf;
                }
                try {
                    if (tiisSpi == null) {
                        Objfdt obj = s.nfwInstbndf(null);
                        if (obj instbndfof CipifrSpi == fblsf) {
                            dontinuf;
                        }
                        tiisSpi = (CipifrSpi)obj;
                    }
                    tr.sftModfPbdding(tiisSpi);
                    initCryptoPfrmission();
                    spi = tiisSpi;
                    providfr = s.gftProvidfr();
                    // not nffdfd bny morf
                    firstSfrvidf = null;
                    sfrvidfItfrbtor = null;
                    trbnsforms = null;
                    rfturn;
                } dbtdi (Exdfption f) {
                    lbstExdfption = f;
                }
            }
            ProvidfrExdfption f = nfw ProvidfrExdfption
                    ("Could not donstrudt CipifrSpi instbndf");
            if (lbstExdfption != null) {
                f.initCbusf(lbstExdfption);
            }
            tirow f;
        }
    }

    privbtf finbl stbtid int I_KEY       = 1;
    privbtf finbl stbtid int I_PARAMSPEC = 2;
    privbtf finbl stbtid int I_PARAMS    = 3;
    privbtf finbl stbtid int I_CERT      = 4;

    privbtf void implInit(CipifrSpi tiisSpi, int typf, int opmodf, Kfy kfy,
            AlgoritimPbrbmftfrSpfd pbrbmSpfd, AlgoritimPbrbmftfrs pbrbms,
            SfdurfRbndom rbndom) tirows InvblidKfyExdfption,
            InvblidAlgoritimPbrbmftfrExdfption {
        switdi (typf) {
        dbsf I_KEY:
            difdkCryptoPfrm(tiisSpi, kfy);
            tiisSpi.fnginfInit(opmodf, kfy, rbndom);
            brfbk;
        dbsf I_PARAMSPEC:
            difdkCryptoPfrm(tiisSpi, kfy, pbrbmSpfd);
            tiisSpi.fnginfInit(opmodf, kfy, pbrbmSpfd, rbndom);
            brfbk;
        dbsf I_PARAMS:
            difdkCryptoPfrm(tiisSpi, kfy, pbrbms);
            tiisSpi.fnginfInit(opmodf, kfy, pbrbms, rbndom);
            brfbk;
        dbsf I_CERT:
            difdkCryptoPfrm(tiisSpi, kfy);
            tiisSpi.fnginfInit(opmodf, kfy, rbndom);
            brfbk;
        dffbult:
            tirow nfw AssfrtionError("Intfrnbl Cipifr frror: " + typf);
        }
    }

    privbtf void dioosfProvidfr(int initTypf, int opmodf, Kfy kfy,
            AlgoritimPbrbmftfrSpfd pbrbmSpfd,
            AlgoritimPbrbmftfrs pbrbms, SfdurfRbndom rbndom)
            tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption {
        syndironizfd (lodk) {
            if (spi != null) {
                implInit(spi, initTypf, opmodf, kfy, pbrbmSpfd, pbrbms, rbndom);
                rfturn;
            }
            Exdfption lbstExdfption = null;
            wiilf ((firstSfrvidf != null) || sfrvidfItfrbtor.ibsNfxt()) {
                Sfrvidf s;
                CipifrSpi tiisSpi;
                if (firstSfrvidf != null) {
                    s = firstSfrvidf;
                    tiisSpi = firstSpi;
                    firstSfrvidf = null;
                    firstSpi = null;
                } flsf {
                    s = sfrvidfItfrbtor.nfxt();
                    tiisSpi = null;
                }
                // if providfr sbys it dofs not support tiis kfy, ignorf it
                if (s.supportsPbrbmftfr(kfy) == fblsf) {
                    dontinuf;
                }
                if (JdfSfdurity.dbnUsfProvidfr(s.gftProvidfr()) == fblsf) {
                    dontinuf;
                }
                Trbnsform tr = gftTrbnsform(s, trbnsforms);
                if (tr == null) {
                    // siould nfvfr ibppfn
                    dontinuf;
                }
                if (tr.supportsModfPbdding(s) == S_NO) {
                    dontinuf;
                }
                try {
                    if (tiisSpi == null) {
                        tiisSpi = (CipifrSpi)s.nfwInstbndf(null);
                    }
                    tr.sftModfPbdding(tiisSpi);
                    initCryptoPfrmission();
                    implInit(tiisSpi, initTypf, opmodf, kfy, pbrbmSpfd,
                                                        pbrbms, rbndom);
                    providfr = s.gftProvidfr();
                    tiis.spi = tiisSpi;
                    firstSfrvidf = null;
                    sfrvidfItfrbtor = null;
                    trbnsforms = null;
                    rfturn;
                } dbtdi (Exdfption f) {
                    // NoSudiAlgoritimExdfption from nfwInstbndf()
                    // InvblidKfyExdfption from init()
                    // RuntimfExdfption (ProvidfrExdfption) from init()
                    // SfdurityExdfption from drypto pfrmission difdk
                    if (lbstExdfption == null) {
                        lbstExdfption = f;
                    }
                }
            }
            // no working providfr found, fbil
            if (lbstExdfption instbndfof InvblidKfyExdfption) {
                tirow (InvblidKfyExdfption)lbstExdfption;
            }
            if (lbstExdfption instbndfof InvblidAlgoritimPbrbmftfrExdfption) {
                tirow (InvblidAlgoritimPbrbmftfrExdfption)lbstExdfption;
            }
            if (lbstExdfption instbndfof RuntimfExdfption) {
                tirow (RuntimfExdfption)lbstExdfption;
            }
            String kNbmf = (kfy != null) ? kfy.gftClbss().gftNbmf() : "(null)";
            tirow nfw InvblidKfyExdfption
                ("No instbllfd providfr supports tiis kfy: "
                + kNbmf, lbstExdfption);
        }
    }

    /**
     * Rfturns tif providfr of tiis <dodf>Cipifr</dodf> objfdt.
     *
     * @rfturn tif providfr of tiis <dodf>Cipifr</dodf> objfdt
     */
    publid finbl Providfr gftProvidfr() {
        dioosfFirstProvidfr();
        rfturn tiis.providfr;
    }

    /**
     * Rfturns tif blgoritim nbmf of tiis <dodf>Cipifr</dodf> objfdt.
     *
     * <p>Tiis is tif sbmf nbmf tibt wbs spfdififd in onf of tif
     * <dodf>gftInstbndf</dodf> dblls tibt drfbtfd tiis <dodf>Cipifr</dodf>
     * objfdt..
     *
     * @rfturn tif blgoritim nbmf of tiis <dodf>Cipifr</dodf> objfdt.
     */
    publid finbl String gftAlgoritim() {
        rfturn tiis.trbnsformbtion;
    }

    /**
     * Rfturns tif blodk sizf (in bytfs).
     *
     * @rfturn tif blodk sizf (in bytfs), or 0 if tif undfrlying blgoritim is
     * not b blodk dipifr
     */
    publid finbl int gftBlodkSizf() {
        dioosfFirstProvidfr();
        rfturn spi.fnginfGftBlodkSizf();
    }

    /**
     * Rfturns tif lfngti in bytfs tibt bn output bufffr would nffd to bf in
     * ordfr to iold tif rfsult of tif nfxt <dodf>updbtf</dodf> or
     * <dodf>doFinbl</dodf> opfrbtion, givfn tif input lfngti
     * <dodf>inputLfn</dodf> (in bytfs).
     *
     * <p>Tiis dbll tbkfs into bddount bny unprodfssfd (bufffrfd) dbtb from b
     * prfvious <dodf>updbtf</dodf> dbll, pbdding, bnd AEAD tbgging.
     *
     * <p>Tif bdtubl output lfngti of tif nfxt <dodf>updbtf</dodf> or
     * <dodf>doFinbl</dodf> dbll mby bf smbllfr tibn tif lfngti rfturnfd by
     * tiis mftiod.
     *
     * @pbrbm inputLfn tif input lfngti (in bytfs)
     *
     * @rfturn tif rfquirfd output bufffr sizf (in bytfs)
     *
     * @fxdfption IllfgblStbtfExdfption if tiis dipifr is in b wrong stbtf
     * (f.g., ibs not yft bffn initiblizfd)
     */
    publid finbl int gftOutputSizf(int inputLfn) {

        if (!initiblizfd && !(tiis instbndfof NullCipifr)) {
            tirow nfw IllfgblStbtfExdfption("Cipifr not initiblizfd");
        }
        if (inputLfn < 0) {
            tirow nfw IllfgblArgumfntExdfption("Input sizf must bf fqubl " +
                                               "to or grfbtfr tibn zfro");
        }
        dioosfFirstProvidfr();
        rfturn spi.fnginfGftOutputSizf(inputLfn);
    }

    /**
     * Rfturns tif initiblizbtion vfdtor (IV) in b nfw bufffr.
     *
     * <p>Tiis is usfful in tif dbsf wifrf b rbndom IV wbs drfbtfd,
     * or in tif dontfxt of pbssword-bbsfd fndryption or
     * dfdryption, wifrf tif IV is dfrivfd from b usfr-supplifd pbssword.
     *
     * @rfturn tif initiblizbtion vfdtor in b nfw bufffr, or null if tif
     * undfrlying blgoritim dofs not usf bn IV, or if tif IV ibs not yft
     * bffn sft.
     */
    publid finbl bytf[] gftIV() {
        dioosfFirstProvidfr();
        rfturn spi.fnginfGftIV();
    }

    /**
     * Rfturns tif pbrbmftfrs usfd witi tiis dipifr.
     *
     * <p>Tif rfturnfd pbrbmftfrs mby bf tif sbmf tibt wfrf usfd to initiblizf
     * tiis dipifr, or mby dontbin b dombinbtion of dffbult bnd rbndom
     * pbrbmftfr vblufs usfd by tif undfrlying dipifr implfmfntbtion if tiis
     * dipifr rfquirfs blgoritim pbrbmftfrs but wbs not initiblizfd witi bny.
     *
     * @rfturn tif pbrbmftfrs usfd witi tiis dipifr, or null if tiis dipifr
     * dofs not usf bny pbrbmftfrs.
     */
    publid finbl AlgoritimPbrbmftfrs gftPbrbmftfrs() {
        dioosfFirstProvidfr();
        rfturn spi.fnginfGftPbrbmftfrs();
    }

    /**
     * Rfturns tif fxfmption mfdibnism objfdt usfd witi tiis dipifr.
     *
     * @rfturn tif fxfmption mfdibnism objfdt usfd witi tiis dipifr, or
     * null if tiis dipifr dofs not usf bny fxfmption mfdibnism.
     */
    publid finbl ExfmptionMfdibnism gftExfmptionMfdibnism() {
        dioosfFirstProvidfr();
        rfturn fxmfdi;
    }

    //
    // Crypto pfrmission difdk dodf bflow
    //
    privbtf void difdkCryptoPfrm(CipifrSpi difdkSpi, Kfy kfy)
            tirows InvblidKfyExdfption {
        if (dryptoPfrm == CryptoAllPfrmission.INSTANCE) {
            rfturn;
        }
        // Cifdk if kfy sizf bnd dffbult pbrbmftfrs brf witiin lfgbl limits
        AlgoritimPbrbmftfrSpfd pbrbms;
        try {
            pbrbms = gftAlgoritimPbrbmftfrSpfd(difdkSpi.fnginfGftPbrbmftfrs());
        } dbtdi (InvblidPbrbmftfrSpfdExdfption ipsf) {
            tirow nfw InvblidKfyExdfption
                ("Unsupportfd dffbult blgoritim pbrbmftfrs");
        }
        if (!pbssCryptoPfrmCifdk(difdkSpi, kfy, pbrbms)) {
            tirow nfw InvblidKfyExdfption(
                "Illfgbl kfy sizf or dffbult pbrbmftfrs");
        }
    }

    privbtf void difdkCryptoPfrm(CipifrSpi difdkSpi, Kfy kfy,
            AlgoritimPbrbmftfrSpfd pbrbms) tirows InvblidKfyExdfption,
            InvblidAlgoritimPbrbmftfrExdfption {
        if (dryptoPfrm == CryptoAllPfrmission.INSTANCE) {
            rfturn;
        }
        // Dftfrminf kfysizf bnd difdk if it is witiin lfgbl limits
        if (!pbssCryptoPfrmCifdk(difdkSpi, kfy, null)) {
            tirow nfw InvblidKfyExdfption("Illfgbl kfy sizf");
        }
        if ((pbrbms != null) && (!pbssCryptoPfrmCifdk(difdkSpi, kfy, pbrbms))) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption("Illfgbl pbrbmftfrs");
        }
    }

    privbtf void difdkCryptoPfrm(CipifrSpi difdkSpi, Kfy kfy,
            AlgoritimPbrbmftfrs pbrbms)
            tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption {
        if (dryptoPfrm == CryptoAllPfrmission.INSTANCE) {
            rfturn;
        }
        // Convfrt tif spfdififd pbrbmftfrs into spfds bnd tifn dflfgbtf.
        AlgoritimPbrbmftfrSpfd pSpfd;
        try {
            pSpfd = gftAlgoritimPbrbmftfrSpfd(pbrbms);
        } dbtdi (InvblidPbrbmftfrSpfdExdfption ipsf) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                ("Fbilfd to rftrifvf blgoritim pbrbmftfr spfdifidbtion");
        }
        difdkCryptoPfrm(difdkSpi, kfy, pSpfd);
    }

    privbtf boolfbn pbssCryptoPfrmCifdk(CipifrSpi difdkSpi, Kfy kfy,
                                        AlgoritimPbrbmftfrSpfd pbrbms)
            tirows InvblidKfyExdfption {
        String fm = dryptoPfrm.gftExfmptionMfdibnism();
        int kfySizf = difdkSpi.fnginfGftKfySizf(kfy);
        // Usf tif "blgoritim" domponfnt of tif dipifr
        // trbnsformbtion so tibt tif pfrm difdk would
        // work wifn tif kfy ibs tif "blibsfd" blgo.
        String blgComponfnt;
        int indfx = trbnsformbtion.indfxOf('/');
        if (indfx != -1) {
            blgComponfnt = trbnsformbtion.substring(0, indfx);
        } flsf {
            blgComponfnt = trbnsformbtion;
        }
        CryptoPfrmission difdkPfrm =
            nfw CryptoPfrmission(blgComponfnt, kfySizf, pbrbms, fm);

        if (!dryptoPfrm.implifs(difdkPfrm)) {
            if (dfbug != null) {
                dfbug.println("Crypto Pfrmission difdk fbilfd");
                dfbug.println("grbntfd: " + dryptoPfrm);
                dfbug.println("rfqufsting: " + difdkPfrm);
            }
            rfturn fblsf;
        }
        if (fxmfdi == null) {
            rfturn truf;
        }
        try {
            if (!fxmfdi.isCryptoAllowfd(kfy)) {
                if (dfbug != null) {
                    dfbug.println(fxmfdi.gftNbmf() + " isn't fnfordfd");
                }
                rfturn fblsf;
            }
        } dbtdi (ExfmptionMfdibnismExdfption fmf) {
            if (dfbug != null) {
                dfbug.println("Cbnnot dftfrminf wiftifr "+
                              fxmfdi.gftNbmf() + " ibs bffn fnfordfd");
                fmf.printStbdkTrbdf();
            }
            rfturn fblsf;
        }
        rfturn truf;
    }

    // difdk if opmodf is onf of tif dffinfd donstbnts
    // tirow InvblidPbrbmftfrExfption if not
    privbtf stbtid void difdkOpmodf(int opmodf) {
        if ((opmodf < ENCRYPT_MODE) || (opmodf > UNWRAP_MODE)) {
            tirow nfw InvblidPbrbmftfrExdfption("Invblid opfrbtion modf");
        }
    }

    /**
     * Initiblizfs tiis dipifr witi b kfy.
     *
     * <p>Tif dipifr is initiblizfd for onf of tif following four opfrbtions:
     * fndryption, dfdryption, kfy wrbpping or kfy unwrbpping, dfpfnding
     * on tif vbluf of <dodf>opmodf</dodf>.
     *
     * <p>If tiis dipifr rfquirfs bny blgoritim pbrbmftfrs tibt dbnnot bf
     * dfrivfd from tif givfn <dodf>kfy</dodf>, tif undfrlying dipifr
     * implfmfntbtion is supposfd to gfnfrbtf tif rfquirfd pbrbmftfrs itsflf
     * (using providfr-spfdifid dffbult or rbndom vblufs) if it is bfing
     * initiblizfd for fndryption or kfy wrbpping, bnd rbisf bn
     * <dodf>InvblidKfyExdfption</dodf> if it is bfing
     * initiblizfd for dfdryption or kfy unwrbpping.
     * Tif gfnfrbtfd pbrbmftfrs dbn bf rftrifvfd using
     * {@link #gftPbrbmftfrs() gftPbrbmftfrs} or
     * {@link #gftIV() gftIV} (if tif pbrbmftfr is bn IV).
     *
     * <p>If tiis dipifr rfquirfs blgoritim pbrbmftfrs tibt dbnnot bf
     * dfrivfd from tif input pbrbmftfrs, bnd tifrf brf no rfbsonbblf
     * providfr-spfdifid dffbult vblufs, initiblizbtion will
     * nfdfssbrily fbil.
     *
     * <p>If tiis dipifr (indluding its undfrlying fffdbbdk or pbdding sdifmf)
     * rfquirfs bny rbndom bytfs (f.g., for pbrbmftfr gfnfrbtion), it will gft
     * tifm using tif {@link jbvb.sfdurity.SfdurfRbndom}
     * implfmfntbtion of tif iigifst-priority
     * instbllfd providfr bs tif sourdf of rbndomnfss.
     * (If nonf of tif instbllfd providfrs supply bn implfmfntbtion of
     * SfdurfRbndom, b systfm-providfd sourdf of rbndomnfss will bf usfd.)
     *
     * <p>Notf tibt wifn b Cipifr objfdt is initiblizfd, it losfs bll
     * prfviously-bdquirfd stbtf. In otifr words, initiblizing b Cipifr is
     * fquivblfnt to drfbting b nfw instbndf of tibt Cipifr bnd initiblizing
     * it.
     *
     * @pbrbm opmodf tif opfrbtion modf of tiis dipifr (tiis is onf of
     * tif following:
     * <dodf>ENCRYPT_MODE</dodf>, <dodf>DECRYPT_MODE</dodf>,
     * <dodf>WRAP_MODE</dodf> or <dodf>UNWRAP_MODE</dodf>)
     * @pbrbm kfy tif kfy
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy is inbppropribtf for
     * initiblizing tiis dipifr, or rfquirfs
     * blgoritim pbrbmftfrs tibt dbnnot bf
     * dftfrminfd from tif givfn kfy, or if tif givfn kfy ibs b kfysizf tibt
     * fxdffds tif mbximum bllowbblf kfysizf (bs dftfrminfd from tif
     * donfigurfd jurisdidtion polidy filfs).
     * @tirows UnsupportfdOpfrbtionExdfption if (@dodf opmodf} is
     * {@dodf WRAP_MODE} or {@dodf UNWRAP_MODE} but tif modf is not implfmfntfd
     * by tif undfrlying {@dodf CipifrSpi}.
     */
    publid finbl void init(int opmodf, Kfy kfy) tirows InvblidKfyExdfption {
        init(opmodf, kfy, JdfSfdurity.RANDOM);
    }

    /**
     * Initiblizfs tiis dipifr witi b kfy bnd b sourdf of rbndomnfss.
     *
     * <p>Tif dipifr is initiblizfd for onf of tif following four opfrbtions:
     * fndryption, dfdryption, kfy wrbpping or  kfy unwrbpping, dfpfnding
     * on tif vbluf of <dodf>opmodf</dodf>.
     *
     * <p>If tiis dipifr rfquirfs bny blgoritim pbrbmftfrs tibt dbnnot bf
     * dfrivfd from tif givfn <dodf>kfy</dodf>, tif undfrlying dipifr
     * implfmfntbtion is supposfd to gfnfrbtf tif rfquirfd pbrbmftfrs itsflf
     * (using providfr-spfdifid dffbult or rbndom vblufs) if it is bfing
     * initiblizfd for fndryption or kfy wrbpping, bnd rbisf bn
     * <dodf>InvblidKfyExdfption</dodf> if it is bfing
     * initiblizfd for dfdryption or kfy unwrbpping.
     * Tif gfnfrbtfd pbrbmftfrs dbn bf rftrifvfd using
     * {@link #gftPbrbmftfrs() gftPbrbmftfrs} or
     * {@link #gftIV() gftIV} (if tif pbrbmftfr is bn IV).
     *
     * <p>If tiis dipifr rfquirfs blgoritim pbrbmftfrs tibt dbnnot bf
     * dfrivfd from tif input pbrbmftfrs, bnd tifrf brf no rfbsonbblf
     * providfr-spfdifid dffbult vblufs, initiblizbtion will
     * nfdfssbrily fbil.
     *
     * <p>If tiis dipifr (indluding its undfrlying fffdbbdk or pbdding sdifmf)
     * rfquirfs bny rbndom bytfs (f.g., for pbrbmftfr gfnfrbtion), it will gft
     * tifm from <dodf>rbndom</dodf>.
     *
     * <p>Notf tibt wifn b Cipifr objfdt is initiblizfd, it losfs bll
     * prfviously-bdquirfd stbtf. In otifr words, initiblizing b Cipifr is
     * fquivblfnt to drfbting b nfw instbndf of tibt Cipifr bnd initiblizing
     * it.
     *
     * @pbrbm opmodf tif opfrbtion modf of tiis dipifr (tiis is onf of tif
     * following:
     * <dodf>ENCRYPT_MODE</dodf>, <dodf>DECRYPT_MODE</dodf>,
     * <dodf>WRAP_MODE</dodf> or <dodf>UNWRAP_MODE</dodf>)
     * @pbrbm kfy tif fndryption kfy
     * @pbrbm rbndom tif sourdf of rbndomnfss
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy is inbppropribtf for
     * initiblizing tiis dipifr, or rfquirfs
     * blgoritim pbrbmftfrs tibt dbnnot bf
     * dftfrminfd from tif givfn kfy, or if tif givfn kfy ibs b kfysizf tibt
     * fxdffds tif mbximum bllowbblf kfysizf (bs dftfrminfd from tif
     * donfigurfd jurisdidtion polidy filfs).
     * @tirows UnsupportfdOpfrbtionExdfption if (@dodf opmodf} is
     * {@dodf WRAP_MODE} or {@dodf UNWRAP_MODE} but tif modf is not implfmfntfd
     * by tif undfrlying {@dodf CipifrSpi}.
     */
    publid finbl void init(int opmodf, Kfy kfy, SfdurfRbndom rbndom)
            tirows InvblidKfyExdfption
    {
        initiblizfd = fblsf;
        difdkOpmodf(opmodf);

        if (spi != null) {
            difdkCryptoPfrm(spi, kfy);
            spi.fnginfInit(opmodf, kfy, rbndom);
        } flsf {
            try {
                dioosfProvidfr(I_KEY, opmodf, kfy, null, null, rbndom);
            } dbtdi (InvblidAlgoritimPbrbmftfrExdfption f) {
                // siould nfvfr oddur
                tirow nfw InvblidKfyExdfption(f);
            }
        }

        initiblizfd = truf;
        tiis.opmodf = opmodf;
    }

    /**
     * Initiblizfs tiis dipifr witi b kfy bnd b sft of blgoritim
     * pbrbmftfrs.
     *
     * <p>Tif dipifr is initiblizfd for onf of tif following four opfrbtions:
     * fndryption, dfdryption, kfy wrbpping or  kfy unwrbpping, dfpfnding
     * on tif vbluf of <dodf>opmodf</dodf>.
     *
     * <p>If tiis dipifr rfquirfs bny blgoritim pbrbmftfrs bnd
     * <dodf>pbrbms</dodf> is null, tif undfrlying dipifr implfmfntbtion is
     * supposfd to gfnfrbtf tif rfquirfd pbrbmftfrs itsflf (using
     * providfr-spfdifid dffbult or rbndom vblufs) if it is bfing
     * initiblizfd for fndryption or kfy wrbpping, bnd rbisf bn
     * <dodf>InvblidAlgoritimPbrbmftfrExdfption</dodf> if it is bfing
     * initiblizfd for dfdryption or kfy unwrbpping.
     * Tif gfnfrbtfd pbrbmftfrs dbn bf rftrifvfd using
     * {@link #gftPbrbmftfrs() gftPbrbmftfrs} or
     * {@link #gftIV() gftIV} (if tif pbrbmftfr is bn IV).
     *
     * <p>If tiis dipifr rfquirfs blgoritim pbrbmftfrs tibt dbnnot bf
     * dfrivfd from tif input pbrbmftfrs, bnd tifrf brf no rfbsonbblf
     * providfr-spfdifid dffbult vblufs, initiblizbtion will
     * nfdfssbrily fbil.
     *
     * <p>If tiis dipifr (indluding its undfrlying fffdbbdk or pbdding sdifmf)
     * rfquirfs bny rbndom bytfs (f.g., for pbrbmftfr gfnfrbtion), it will gft
     * tifm using tif {@link jbvb.sfdurity.SfdurfRbndom}
     * implfmfntbtion of tif iigifst-priority
     * instbllfd providfr bs tif sourdf of rbndomnfss.
     * (If nonf of tif instbllfd providfrs supply bn implfmfntbtion of
     * SfdurfRbndom, b systfm-providfd sourdf of rbndomnfss will bf usfd.)
     *
     * <p>Notf tibt wifn b Cipifr objfdt is initiblizfd, it losfs bll
     * prfviously-bdquirfd stbtf. In otifr words, initiblizing b Cipifr is
     * fquivblfnt to drfbting b nfw instbndf of tibt Cipifr bnd initiblizing
     * it.
     *
     * @pbrbm opmodf tif opfrbtion modf of tiis dipifr (tiis is onf of tif
     * following:
     * <dodf>ENCRYPT_MODE</dodf>, <dodf>DECRYPT_MODE</dodf>,
     * <dodf>WRAP_MODE</dodf> or <dodf>UNWRAP_MODE</dodf>)
     * @pbrbm kfy tif fndryption kfy
     * @pbrbm pbrbms tif blgoritim pbrbmftfrs
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy is inbppropribtf for
     * initiblizing tiis dipifr, or its kfysizf fxdffds tif mbximum bllowbblf
     * kfysizf (bs dftfrminfd from tif donfigurfd jurisdidtion polidy filfs).
     * @fxdfption InvblidAlgoritimPbrbmftfrExdfption if tif givfn blgoritim
     * pbrbmftfrs brf inbppropribtf for tiis dipifr,
     * or tiis dipifr rfquirfs
     * blgoritim pbrbmftfrs bnd <dodf>pbrbms</dodf> is null, or tif givfn
     * blgoritim pbrbmftfrs imply b dryptogrbpiid strfngti tibt would fxdffd
     * tif lfgbl limits (bs dftfrminfd from tif donfigurfd jurisdidtion
     * polidy filfs).
     * @tirows UnsupportfdOpfrbtionExdfption if (@dodf opmodf} is
     * {@dodf WRAP_MODE} or {@dodf UNWRAP_MODE} but tif modf is not implfmfntfd
     * by tif undfrlying {@dodf CipifrSpi}.
     */
    publid finbl void init(int opmodf, Kfy kfy, AlgoritimPbrbmftfrSpfd pbrbms)
            tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption
    {
        init(opmodf, kfy, pbrbms, JdfSfdurity.RANDOM);
    }

    /**
     * Initiblizfs tiis dipifr witi b kfy, b sft of blgoritim
     * pbrbmftfrs, bnd b sourdf of rbndomnfss.
     *
     * <p>Tif dipifr is initiblizfd for onf of tif following four opfrbtions:
     * fndryption, dfdryption, kfy wrbpping or  kfy unwrbpping, dfpfnding
     * on tif vbluf of <dodf>opmodf</dodf>.
     *
     * <p>If tiis dipifr rfquirfs bny blgoritim pbrbmftfrs bnd
     * <dodf>pbrbms</dodf> is null, tif undfrlying dipifr implfmfntbtion is
     * supposfd to gfnfrbtf tif rfquirfd pbrbmftfrs itsflf (using
     * providfr-spfdifid dffbult or rbndom vblufs) if it is bfing
     * initiblizfd for fndryption or kfy wrbpping, bnd rbisf bn
     * <dodf>InvblidAlgoritimPbrbmftfrExdfption</dodf> if it is bfing
     * initiblizfd for dfdryption or kfy unwrbpping.
     * Tif gfnfrbtfd pbrbmftfrs dbn bf rftrifvfd using
     * {@link #gftPbrbmftfrs() gftPbrbmftfrs} or
     * {@link #gftIV() gftIV} (if tif pbrbmftfr is bn IV).
     *
     * <p>If tiis dipifr rfquirfs blgoritim pbrbmftfrs tibt dbnnot bf
     * dfrivfd from tif input pbrbmftfrs, bnd tifrf brf no rfbsonbblf
     * providfr-spfdifid dffbult vblufs, initiblizbtion will
     * nfdfssbrily fbil.
     *
     * <p>If tiis dipifr (indluding its undfrlying fffdbbdk or pbdding sdifmf)
     * rfquirfs bny rbndom bytfs (f.g., for pbrbmftfr gfnfrbtion), it will gft
     * tifm from <dodf>rbndom</dodf>.
     *
     * <p>Notf tibt wifn b Cipifr objfdt is initiblizfd, it losfs bll
     * prfviously-bdquirfd stbtf. In otifr words, initiblizing b Cipifr is
     * fquivblfnt to drfbting b nfw instbndf of tibt Cipifr bnd initiblizing
     * it.
     *
     * @pbrbm opmodf tif opfrbtion modf of tiis dipifr (tiis is onf of tif
     * following:
     * <dodf>ENCRYPT_MODE</dodf>, <dodf>DECRYPT_MODE</dodf>,
     * <dodf>WRAP_MODE</dodf> or <dodf>UNWRAP_MODE</dodf>)
     * @pbrbm kfy tif fndryption kfy
     * @pbrbm pbrbms tif blgoritim pbrbmftfrs
     * @pbrbm rbndom tif sourdf of rbndomnfss
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy is inbppropribtf for
     * initiblizing tiis dipifr, or its kfysizf fxdffds tif mbximum bllowbblf
     * kfysizf (bs dftfrminfd from tif donfigurfd jurisdidtion polidy filfs).
     * @fxdfption InvblidAlgoritimPbrbmftfrExdfption if tif givfn blgoritim
     * pbrbmftfrs brf inbppropribtf for tiis dipifr,
     * or tiis dipifr rfquirfs
     * blgoritim pbrbmftfrs bnd <dodf>pbrbms</dodf> is null, or tif givfn
     * blgoritim pbrbmftfrs imply b dryptogrbpiid strfngti tibt would fxdffd
     * tif lfgbl limits (bs dftfrminfd from tif donfigurfd jurisdidtion
     * polidy filfs).
     * @tirows UnsupportfdOpfrbtionExdfption if (@dodf opmodf} is
     * {@dodf WRAP_MODE} or {@dodf UNWRAP_MODE} but tif modf is not implfmfntfd
     * by tif undfrlying {@dodf CipifrSpi}.
     */
    publid finbl void init(int opmodf, Kfy kfy, AlgoritimPbrbmftfrSpfd pbrbms,
                           SfdurfRbndom rbndom)
            tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption
    {
        initiblizfd = fblsf;
        difdkOpmodf(opmodf);

        if (spi != null) {
            difdkCryptoPfrm(spi, kfy, pbrbms);
            spi.fnginfInit(opmodf, kfy, pbrbms, rbndom);
        } flsf {
            dioosfProvidfr(I_PARAMSPEC, opmodf, kfy, pbrbms, null, rbndom);
        }

        initiblizfd = truf;
        tiis.opmodf = opmodf;
    }

    /**
     * Initiblizfs tiis dipifr witi b kfy bnd b sft of blgoritim
     * pbrbmftfrs.
     *
     * <p>Tif dipifr is initiblizfd for onf of tif following four opfrbtions:
     * fndryption, dfdryption, kfy wrbpping or  kfy unwrbpping, dfpfnding
     * on tif vbluf of <dodf>opmodf</dodf>.
     *
     * <p>If tiis dipifr rfquirfs bny blgoritim pbrbmftfrs bnd
     * <dodf>pbrbms</dodf> is null, tif undfrlying dipifr implfmfntbtion is
     * supposfd to gfnfrbtf tif rfquirfd pbrbmftfrs itsflf (using
     * providfr-spfdifid dffbult or rbndom vblufs) if it is bfing
     * initiblizfd for fndryption or kfy wrbpping, bnd rbisf bn
     * <dodf>InvblidAlgoritimPbrbmftfrExdfption</dodf> if it is bfing
     * initiblizfd for dfdryption or kfy unwrbpping.
     * Tif gfnfrbtfd pbrbmftfrs dbn bf rftrifvfd using
     * {@link #gftPbrbmftfrs() gftPbrbmftfrs} or
     * {@link #gftIV() gftIV} (if tif pbrbmftfr is bn IV).
     *
     * <p>If tiis dipifr rfquirfs blgoritim pbrbmftfrs tibt dbnnot bf
     * dfrivfd from tif input pbrbmftfrs, bnd tifrf brf no rfbsonbblf
     * providfr-spfdifid dffbult vblufs, initiblizbtion will
     * nfdfssbrily fbil.
     *
     * <p>If tiis dipifr (indluding its undfrlying fffdbbdk or pbdding sdifmf)
     * rfquirfs bny rbndom bytfs (f.g., for pbrbmftfr gfnfrbtion), it will gft
     * tifm using tif {@link jbvb.sfdurity.SfdurfRbndom}
     * implfmfntbtion of tif iigifst-priority
     * instbllfd providfr bs tif sourdf of rbndomnfss.
     * (If nonf of tif instbllfd providfrs supply bn implfmfntbtion of
     * SfdurfRbndom, b systfm-providfd sourdf of rbndomnfss will bf usfd.)
     *
     * <p>Notf tibt wifn b Cipifr objfdt is initiblizfd, it losfs bll
     * prfviously-bdquirfd stbtf. In otifr words, initiblizing b Cipifr is
     * fquivblfnt to drfbting b nfw instbndf of tibt Cipifr bnd initiblizing
     * it.
     *
     * @pbrbm opmodf tif opfrbtion modf of tiis dipifr (tiis is onf of tif
     * following: <dodf>ENCRYPT_MODE</dodf>,
     * <dodf>DECRYPT_MODE</dodf>, <dodf>WRAP_MODE</dodf>
     * or <dodf>UNWRAP_MODE</dodf>)
     * @pbrbm kfy tif fndryption kfy
     * @pbrbm pbrbms tif blgoritim pbrbmftfrs
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy is inbppropribtf for
     * initiblizing tiis dipifr, or its kfysizf fxdffds tif mbximum bllowbblf
     * kfysizf (bs dftfrminfd from tif donfigurfd jurisdidtion polidy filfs).
     * @fxdfption InvblidAlgoritimPbrbmftfrExdfption if tif givfn blgoritim
     * pbrbmftfrs brf inbppropribtf for tiis dipifr,
     * or tiis dipifr rfquirfs
     * blgoritim pbrbmftfrs bnd <dodf>pbrbms</dodf> is null, or tif givfn
     * blgoritim pbrbmftfrs imply b dryptogrbpiid strfngti tibt would fxdffd
     * tif lfgbl limits (bs dftfrminfd from tif donfigurfd jurisdidtion
     * polidy filfs).
     * @tirows UnsupportfdOpfrbtionExdfption if (@dodf opmodf} is
     * {@dodf WRAP_MODE} or {@dodf UNWRAP_MODE} but tif modf is not implfmfntfd
     * by tif undfrlying {@dodf CipifrSpi}.
     */
    publid finbl void init(int opmodf, Kfy kfy, AlgoritimPbrbmftfrs pbrbms)
            tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption
    {
        init(opmodf, kfy, pbrbms, JdfSfdurity.RANDOM);
    }

    /**
     * Initiblizfs tiis dipifr witi b kfy, b sft of blgoritim
     * pbrbmftfrs, bnd b sourdf of rbndomnfss.
     *
     * <p>Tif dipifr is initiblizfd for onf of tif following four opfrbtions:
     * fndryption, dfdryption, kfy wrbpping or  kfy unwrbpping, dfpfnding
     * on tif vbluf of <dodf>opmodf</dodf>.
     *
     * <p>If tiis dipifr rfquirfs bny blgoritim pbrbmftfrs bnd
     * <dodf>pbrbms</dodf> is null, tif undfrlying dipifr implfmfntbtion is
     * supposfd to gfnfrbtf tif rfquirfd pbrbmftfrs itsflf (using
     * providfr-spfdifid dffbult or rbndom vblufs) if it is bfing
     * initiblizfd for fndryption or kfy wrbpping, bnd rbisf bn
     * <dodf>InvblidAlgoritimPbrbmftfrExdfption</dodf> if it is bfing
     * initiblizfd for dfdryption or kfy unwrbpping.
     * Tif gfnfrbtfd pbrbmftfrs dbn bf rftrifvfd using
     * {@link #gftPbrbmftfrs() gftPbrbmftfrs} or
     * {@link #gftIV() gftIV} (if tif pbrbmftfr is bn IV).
     *
     * <p>If tiis dipifr rfquirfs blgoritim pbrbmftfrs tibt dbnnot bf
     * dfrivfd from tif input pbrbmftfrs, bnd tifrf brf no rfbsonbblf
     * providfr-spfdifid dffbult vblufs, initiblizbtion will
     * nfdfssbrily fbil.
     *
     * <p>If tiis dipifr (indluding its undfrlying fffdbbdk or pbdding sdifmf)
     * rfquirfs bny rbndom bytfs (f.g., for pbrbmftfr gfnfrbtion), it will gft
     * tifm from <dodf>rbndom</dodf>.
     *
     * <p>Notf tibt wifn b Cipifr objfdt is initiblizfd, it losfs bll
     * prfviously-bdquirfd stbtf. In otifr words, initiblizing b Cipifr is
     * fquivblfnt to drfbting b nfw instbndf of tibt Cipifr bnd initiblizing
     * it.
     *
     * @pbrbm opmodf tif opfrbtion modf of tiis dipifr (tiis is onf of tif
     * following: <dodf>ENCRYPT_MODE</dodf>,
     * <dodf>DECRYPT_MODE</dodf>, <dodf>WRAP_MODE</dodf>
     * or <dodf>UNWRAP_MODE</dodf>)
     * @pbrbm kfy tif fndryption kfy
     * @pbrbm pbrbms tif blgoritim pbrbmftfrs
     * @pbrbm rbndom tif sourdf of rbndomnfss
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy is inbppropribtf for
     * initiblizing tiis dipifr, or its kfysizf fxdffds tif mbximum bllowbblf
     * kfysizf (bs dftfrminfd from tif donfigurfd jurisdidtion polidy filfs).
     * @fxdfption InvblidAlgoritimPbrbmftfrExdfption if tif givfn blgoritim
     * pbrbmftfrs brf inbppropribtf for tiis dipifr,
     * or tiis dipifr rfquirfs
     * blgoritim pbrbmftfrs bnd <dodf>pbrbms</dodf> is null, or tif givfn
     * blgoritim pbrbmftfrs imply b dryptogrbpiid strfngti tibt would fxdffd
     * tif lfgbl limits (bs dftfrminfd from tif donfigurfd jurisdidtion
     * polidy filfs).
     * @tirows UnsupportfdOpfrbtionExdfption if (@dodf opmodf} is
     * {@dodf WRAP_MODE} or {@dodf UNWRAP_MODE} but tif modf is not implfmfntfd
     * by tif undfrlying {@dodf CipifrSpi}.
     */
    publid finbl void init(int opmodf, Kfy kfy, AlgoritimPbrbmftfrs pbrbms,
                           SfdurfRbndom rbndom)
            tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption
    {
        initiblizfd = fblsf;
        difdkOpmodf(opmodf);

        if (spi != null) {
            difdkCryptoPfrm(spi, kfy, pbrbms);
            spi.fnginfInit(opmodf, kfy, pbrbms, rbndom);
        } flsf {
            dioosfProvidfr(I_PARAMS, opmodf, kfy, null, pbrbms, rbndom);
        }

        initiblizfd = truf;
        tiis.opmodf = opmodf;
    }

    /**
     * Initiblizfs tiis dipifr witi tif publid kfy from tif givfn dfrtifidbtf.
     * <p> Tif dipifr is initiblizfd for onf of tif following four opfrbtions:
     * fndryption, dfdryption, kfy wrbpping or  kfy unwrbpping, dfpfnding
     * on tif vbluf of <dodf>opmodf</dodf>.
     *
     * <p>If tif dfrtifidbtf is of typf X.509 bnd ibs b <i>kfy usbgf</i>
     * fxtfnsion fifld mbrkfd bs dritidbl, bnd tif vbluf of tif <i>kfy usbgf</i>
     * fxtfnsion fifld implifs tibt tif publid kfy in
     * tif dfrtifidbtf bnd its dorrfsponding privbtf kfy brf not
     * supposfd to bf usfd for tif opfrbtion rfprfsfntfd by tif vbluf
     * of <dodf>opmodf</dodf>,
     * bn <dodf>InvblidKfyExdfption</dodf>
     * is tirown.
     *
     * <p> If tiis dipifr rfquirfs bny blgoritim pbrbmftfrs tibt dbnnot bf
     * dfrivfd from tif publid kfy in tif givfn dfrtifidbtf, tif undfrlying
     * dipifr
     * implfmfntbtion is supposfd to gfnfrbtf tif rfquirfd pbrbmftfrs itsflf
     * (using providfr-spfdifid dffbult or rbndom vblufs) if it is bfing
     * initiblizfd for fndryption or kfy wrbpping, bnd rbisf bn <dodf>
     * InvblidKfyExdfption</dodf> if it is bfing initiblizfd for dfdryption or
     * kfy unwrbpping.
     * Tif gfnfrbtfd pbrbmftfrs dbn bf rftrifvfd using
     * {@link #gftPbrbmftfrs() gftPbrbmftfrs} or
     * {@link #gftIV() gftIV} (if tif pbrbmftfr is bn IV).
     *
     * <p>If tiis dipifr rfquirfs blgoritim pbrbmftfrs tibt dbnnot bf
     * dfrivfd from tif input pbrbmftfrs, bnd tifrf brf no rfbsonbblf
     * providfr-spfdifid dffbult vblufs, initiblizbtion will
     * nfdfssbrily fbil.
     *
     * <p>If tiis dipifr (indluding its undfrlying fffdbbdk or pbdding sdifmf)
     * rfquirfs bny rbndom bytfs (f.g., for pbrbmftfr gfnfrbtion), it will gft
     * tifm using tif
     * <dodf>SfdurfRbndom</dodf>
     * implfmfntbtion of tif iigifst-priority
     * instbllfd providfr bs tif sourdf of rbndomnfss.
     * (If nonf of tif instbllfd providfrs supply bn implfmfntbtion of
     * SfdurfRbndom, b systfm-providfd sourdf of rbndomnfss will bf usfd.)
     *
     * <p>Notf tibt wifn b Cipifr objfdt is initiblizfd, it losfs bll
     * prfviously-bdquirfd stbtf. In otifr words, initiblizing b Cipifr is
     * fquivblfnt to drfbting b nfw instbndf of tibt Cipifr bnd initiblizing
     * it.
     *
     * @pbrbm opmodf tif opfrbtion modf of tiis dipifr (tiis is onf of tif
     * following:
     * <dodf>ENCRYPT_MODE</dodf>, <dodf>DECRYPT_MODE</dodf>,
     * <dodf>WRAP_MODE</dodf> or <dodf>UNWRAP_MODE</dodf>)
     * @pbrbm dfrtifidbtf tif dfrtifidbtf
     *
     * @fxdfption InvblidKfyExdfption if tif publid kfy in tif givfn
     * dfrtifidbtf is inbppropribtf for initiblizing tiis dipifr, or tiis
     * dipifr rfquirfs blgoritim pbrbmftfrs tibt dbnnot bf dftfrminfd from tif
     * publid kfy in tif givfn dfrtifidbtf, or tif kfysizf of tif publid kfy
     * in tif givfn dfrtifidbtf ibs b kfysizf tibt fxdffds tif mbximum
     * bllowbblf kfysizf (bs dftfrminfd by tif donfigurfd jurisdidtion polidy
     * filfs).
     * @tirows UnsupportfdOpfrbtionExdfption if (@dodf opmodf} is
     * {@dodf WRAP_MODE} or {@dodf UNWRAP_MODE} but tif modf is not implfmfntfd
     * by tif undfrlying {@dodf CipifrSpi}.
     */
    publid finbl void init(int opmodf, Cfrtifidbtf dfrtifidbtf)
            tirows InvblidKfyExdfption
    {
        init(opmodf, dfrtifidbtf, JdfSfdurity.RANDOM);
    }

    /**
     * Initiblizfs tiis dipifr witi tif publid kfy from tif givfn dfrtifidbtf
     * bnd
     * b sourdf of rbndomnfss.
     *
     * <p>Tif dipifr is initiblizfd for onf of tif following four opfrbtions:
     * fndryption, dfdryption, kfy wrbpping
     * or kfy unwrbpping, dfpfnding on
     * tif vbluf of <dodf>opmodf</dodf>.
     *
     * <p>If tif dfrtifidbtf is of typf X.509 bnd ibs b <i>kfy usbgf</i>
     * fxtfnsion fifld mbrkfd bs dritidbl, bnd tif vbluf of tif <i>kfy usbgf</i>
     * fxtfnsion fifld implifs tibt tif publid kfy in
     * tif dfrtifidbtf bnd its dorrfsponding privbtf kfy brf not
     * supposfd to bf usfd for tif opfrbtion rfprfsfntfd by tif vbluf of
     * <dodf>opmodf</dodf>,
     * bn <dodf>InvblidKfyExdfption</dodf>
     * is tirown.
     *
     * <p>If tiis dipifr rfquirfs bny blgoritim pbrbmftfrs tibt dbnnot bf
     * dfrivfd from tif publid kfy in tif givfn <dodf>dfrtifidbtf</dodf>,
     * tif undfrlying dipifr
     * implfmfntbtion is supposfd to gfnfrbtf tif rfquirfd pbrbmftfrs itsflf
     * (using providfr-spfdifid dffbult or rbndom vblufs) if it is bfing
     * initiblizfd for fndryption or kfy wrbpping, bnd rbisf bn
     * <dodf>InvblidKfyExdfption</dodf> if it is bfing
     * initiblizfd for dfdryption or kfy unwrbpping.
     * Tif gfnfrbtfd pbrbmftfrs dbn bf rftrifvfd using
     * {@link #gftPbrbmftfrs() gftPbrbmftfrs} or
     * {@link #gftIV() gftIV} (if tif pbrbmftfr is bn IV).
     *
     * <p>If tiis dipifr rfquirfs blgoritim pbrbmftfrs tibt dbnnot bf
     * dfrivfd from tif input pbrbmftfrs, bnd tifrf brf no rfbsonbblf
     * providfr-spfdifid dffbult vblufs, initiblizbtion will
     * nfdfssbrily fbil.
     *
     * <p>If tiis dipifr (indluding its undfrlying fffdbbdk or pbdding sdifmf)
     * rfquirfs bny rbndom bytfs (f.g., for pbrbmftfr gfnfrbtion), it will gft
     * tifm from <dodf>rbndom</dodf>.
     *
     * <p>Notf tibt wifn b Cipifr objfdt is initiblizfd, it losfs bll
     * prfviously-bdquirfd stbtf. In otifr words, initiblizing b Cipifr is
     * fquivblfnt to drfbting b nfw instbndf of tibt Cipifr bnd initiblizing
     * it.
     *
     * @pbrbm opmodf tif opfrbtion modf of tiis dipifr (tiis is onf of tif
     * following:
     * <dodf>ENCRYPT_MODE</dodf>, <dodf>DECRYPT_MODE</dodf>,
     * <dodf>WRAP_MODE</dodf> or <dodf>UNWRAP_MODE</dodf>)
     * @pbrbm dfrtifidbtf tif dfrtifidbtf
     * @pbrbm rbndom tif sourdf of rbndomnfss
     *
     * @fxdfption InvblidKfyExdfption if tif publid kfy in tif givfn
     * dfrtifidbtf is inbppropribtf for initiblizing tiis dipifr, or tiis
     * dipifr
     * rfquirfs blgoritim pbrbmftfrs tibt dbnnot bf dftfrminfd from tif
     * publid kfy in tif givfn dfrtifidbtf, or tif kfysizf of tif publid kfy
     * in tif givfn dfrtifidbtf ibs b kfysizf tibt fxdffds tif mbximum
     * bllowbblf kfysizf (bs dftfrminfd by tif donfigurfd jurisdidtion polidy
     * filfs).
     * @tirows UnsupportfdOpfrbtionExdfption if (@dodf opmodf} is
     * {@dodf WRAP_MODE} or {@dodf UNWRAP_MODE} but tif modf is not implfmfntfd
     * by tif undfrlying {@dodf CipifrSpi}.
     */
    publid finbl void init(int opmodf, Cfrtifidbtf dfrtifidbtf,
                           SfdurfRbndom rbndom)
            tirows InvblidKfyExdfption
    {
        initiblizfd = fblsf;
        difdkOpmodf(opmodf);

        // Cifdk kfy usbgf if tif dfrtifidbtf is of
        // typf X.509.
        if (dfrtifidbtf instbndfof jbvb.sfdurity.dfrt.X509Cfrtifidbtf) {
            // Cifdk wiftifr tif dfrt ibs b kfy usbgf fxtfnsion
            // mbrkfd bs b dritidbl fxtfnsion.
            X509Cfrtifidbtf dfrt = (X509Cfrtifidbtf)dfrtifidbtf;
            Sft<String> dritSft = dfrt.gftCritidblExtfnsionOIDs();

            if (dritSft != null && !dritSft.isEmpty()
                && dritSft.dontbins(KEY_USAGE_EXTENSION_OID)) {
                boolfbn[] kfyUsbgfInfo = dfrt.gftKfyUsbgf();
                // kfyUsbgfInfo[2] is for kfyEndipifrmfnt;
                // kfyUsbgfInfo[3] is for dbtbEndipifrmfnt.
                if ((kfyUsbgfInfo != null) &&
                    (((opmodf == Cipifr.ENCRYPT_MODE) &&
                      (kfyUsbgfInfo.lfngti > 3) &&
                      (kfyUsbgfInfo[3] == fblsf)) ||
                     ((opmodf == Cipifr.WRAP_MODE) &&
                      (kfyUsbgfInfo.lfngti > 2) &&
                      (kfyUsbgfInfo[2] == fblsf)))) {
                    tirow nfw InvblidKfyExdfption("Wrong kfy usbgf");
                }
            }
        }

        PublidKfy publidKfy =
            (dfrtifidbtf==null? null:dfrtifidbtf.gftPublidKfy());

        if (spi != null) {
            difdkCryptoPfrm(spi, publidKfy);
            spi.fnginfInit(opmodf, publidKfy, rbndom);
        } flsf {
            try {
                dioosfProvidfr(I_CERT, opmodf, publidKfy, null, null, rbndom);
            } dbtdi (InvblidAlgoritimPbrbmftfrExdfption f) {
                // siould nfvfr oddur
                tirow nfw InvblidKfyExdfption(f);
            }
        }

        initiblizfd = truf;
        tiis.opmodf = opmodf;
    }

    /**
     * Ensurfs tibt Cipifr is in b vblid stbtf for updbtf() bnd doFinbl()
     * dblls - siould bf initiblizfd bnd in ENCRYPT_MODE or DECRYPT_MODE.
     * @tirows IllfgblStbtfExdfption if Cipifr objfdt is not in vblid stbtf.
     */
    privbtf void difdkCipifrStbtf() {
        if (!(tiis instbndfof NullCipifr)) {
            if (!initiblizfd) {
                tirow nfw IllfgblStbtfExdfption("Cipifr not initiblizfd");
            }
            if ((opmodf != Cipifr.ENCRYPT_MODE) &&
                (opmodf != Cipifr.DECRYPT_MODE)) {
                tirow nfw IllfgblStbtfExdfption("Cipifr not initiblizfd " +
                                                "for fndryption/dfdryption");
            }
        }
    }

    /**
     * Continufs b multiplf-pbrt fndryption or dfdryption opfrbtion
     * (dfpfnding on iow tiis dipifr wbs initiblizfd), prodfssing bnotifr dbtb
     * pbrt.
     *
     * <p>Tif bytfs in tif <dodf>input</dodf> bufffr brf prodfssfd, bnd tif
     * rfsult is storfd in b nfw bufffr.
     *
     * <p>If <dodf>input</dodf> ibs b lfngti of zfro, tiis mftiod rfturns
     * <dodf>null</dodf>.
     *
     * @pbrbm input tif input bufffr
     *
     * @rfturn tif nfw bufffr witi tif rfsult, or null if tif undfrlying
     * dipifr is b blodk dipifr bnd tif input dbtb is too siort to rfsult in b
     * nfw blodk.
     *
     * @fxdfption IllfgblStbtfExdfption if tiis dipifr is in b wrong stbtf
     * (f.g., ibs not bffn initiblizfd)
     */
    publid finbl bytf[] updbtf(bytf[] input) {
        difdkCipifrStbtf();

        // Input sbnity difdk
        if (input == null) {
            tirow nfw IllfgblArgumfntExdfption("Null input bufffr");
        }

        dioosfFirstProvidfr();
        if (input.lfngti == 0) {
            rfturn null;
        }
        rfturn spi.fnginfUpdbtf(input, 0, input.lfngti);
    }

    /**
     * Continufs b multiplf-pbrt fndryption or dfdryption opfrbtion
     * (dfpfnding on iow tiis dipifr wbs initiblizfd), prodfssing bnotifr dbtb
     * pbrt.
     *
     * <p>Tif first <dodf>inputLfn</dodf> bytfs in tif <dodf>input</dodf>
     * bufffr, stbrting bt <dodf>inputOffsft</dodf> indlusivf, brf prodfssfd,
     * bnd tif rfsult is storfd in b nfw bufffr.
     *
     * <p>If <dodf>inputLfn</dodf> is zfro, tiis mftiod rfturns
     * <dodf>null</dodf>.
     *
     * @pbrbm input tif input bufffr
     * @pbrbm inputOffsft tif offsft in <dodf>input</dodf> wifrf tif input
     * stbrts
     * @pbrbm inputLfn tif input lfngti
     *
     * @rfturn tif nfw bufffr witi tif rfsult, or null if tif undfrlying
     * dipifr is b blodk dipifr bnd tif input dbtb is too siort to rfsult in b
     * nfw blodk.
     *
     * @fxdfption IllfgblStbtfExdfption if tiis dipifr is in b wrong stbtf
     * (f.g., ibs not bffn initiblizfd)
     */
    publid finbl bytf[] updbtf(bytf[] input, int inputOffsft, int inputLfn) {
        difdkCipifrStbtf();

        // Input sbnity difdk
        if (input == null || inputOffsft < 0
            || inputLfn > (input.lfngti - inputOffsft) || inputLfn < 0) {
            tirow nfw IllfgblArgumfntExdfption("Bbd brgumfnts");
        }

        dioosfFirstProvidfr();
        if (inputLfn == 0) {
            rfturn null;
        }
        rfturn spi.fnginfUpdbtf(input, inputOffsft, inputLfn);
    }

    /**
     * Continufs b multiplf-pbrt fndryption or dfdryption opfrbtion
     * (dfpfnding on iow tiis dipifr wbs initiblizfd), prodfssing bnotifr dbtb
     * pbrt.
     *
     * <p>Tif first <dodf>inputLfn</dodf> bytfs in tif <dodf>input</dodf>
     * bufffr, stbrting bt <dodf>inputOffsft</dodf> indlusivf, brf prodfssfd,
     * bnd tif rfsult is storfd in tif <dodf>output</dodf> bufffr.
     *
     * <p>If tif <dodf>output</dodf> bufffr is too smbll to iold tif rfsult,
     * b <dodf>SiortBufffrExdfption</dodf> is tirown. In tiis dbsf, rfpfbt tiis
     * dbll witi b lbrgfr output bufffr. Usf
     * {@link #gftOutputSizf(int) gftOutputSizf} to dftfrminf iow big
     * tif output bufffr siould bf.
     *
     * <p>If <dodf>inputLfn</dodf> is zfro, tiis mftiod rfturns
     * b lfngti of zfro.
     *
     * <p>Notf: tiis mftiod siould bf dopy-sbff, wiidi mfbns tif
     * <dodf>input</dodf> bnd <dodf>output</dodf> bufffrs dbn rfffrfndf
     * tif sbmf bytf brrby bnd no unprodfssfd input dbtb is ovfrwrittfn
     * wifn tif rfsult is dopifd into tif output bufffr.
     *
     * @pbrbm input tif input bufffr
     * @pbrbm inputOffsft tif offsft in <dodf>input</dodf> wifrf tif input
     * stbrts
     * @pbrbm inputLfn tif input lfngti
     * @pbrbm output tif bufffr for tif rfsult
     *
     * @rfturn tif numbfr of bytfs storfd in <dodf>output</dodf>
     *
     * @fxdfption IllfgblStbtfExdfption if tiis dipifr is in b wrong stbtf
     * (f.g., ibs not bffn initiblizfd)
     * @fxdfption SiortBufffrExdfption if tif givfn output bufffr is too smbll
     * to iold tif rfsult
     */
    publid finbl int updbtf(bytf[] input, int inputOffsft, int inputLfn,
                            bytf[] output)
            tirows SiortBufffrExdfption {
        difdkCipifrStbtf();

        // Input sbnity difdk
        if (input == null || inputOffsft < 0
            || inputLfn > (input.lfngti - inputOffsft) || inputLfn < 0) {
            tirow nfw IllfgblArgumfntExdfption("Bbd brgumfnts");
        }

        dioosfFirstProvidfr();
        if (inputLfn == 0) {
            rfturn 0;
        }
        rfturn spi.fnginfUpdbtf(input, inputOffsft, inputLfn,
                                      output, 0);
    }

    /**
     * Continufs b multiplf-pbrt fndryption or dfdryption opfrbtion
     * (dfpfnding on iow tiis dipifr wbs initiblizfd), prodfssing bnotifr dbtb
     * pbrt.
     *
     * <p>Tif first <dodf>inputLfn</dodf> bytfs in tif <dodf>input</dodf>
     * bufffr, stbrting bt <dodf>inputOffsft</dodf> indlusivf, brf prodfssfd,
     * bnd tif rfsult is storfd in tif <dodf>output</dodf> bufffr, stbrting bt
     * <dodf>outputOffsft</dodf> indlusivf.
     *
     * <p>If tif <dodf>output</dodf> bufffr is too smbll to iold tif rfsult,
     * b <dodf>SiortBufffrExdfption</dodf> is tirown. In tiis dbsf, rfpfbt tiis
     * dbll witi b lbrgfr output bufffr. Usf
     * {@link #gftOutputSizf(int) gftOutputSizf} to dftfrminf iow big
     * tif output bufffr siould bf.
     *
     * <p>If <dodf>inputLfn</dodf> is zfro, tiis mftiod rfturns
     * b lfngti of zfro.
     *
     * <p>Notf: tiis mftiod siould bf dopy-sbff, wiidi mfbns tif
     * <dodf>input</dodf> bnd <dodf>output</dodf> bufffrs dbn rfffrfndf
     * tif sbmf bytf brrby bnd no unprodfssfd input dbtb is ovfrwrittfn
     * wifn tif rfsult is dopifd into tif output bufffr.
     *
     * @pbrbm input tif input bufffr
     * @pbrbm inputOffsft tif offsft in <dodf>input</dodf> wifrf tif input
     * stbrts
     * @pbrbm inputLfn tif input lfngti
     * @pbrbm output tif bufffr for tif rfsult
     * @pbrbm outputOffsft tif offsft in <dodf>output</dodf> wifrf tif rfsult
     * is storfd
     *
     * @rfturn tif numbfr of bytfs storfd in <dodf>output</dodf>
     *
     * @fxdfption IllfgblStbtfExdfption if tiis dipifr is in b wrong stbtf
     * (f.g., ibs not bffn initiblizfd)
     * @fxdfption SiortBufffrExdfption if tif givfn output bufffr is too smbll
     * to iold tif rfsult
     */
    publid finbl int updbtf(bytf[] input, int inputOffsft, int inputLfn,
                            bytf[] output, int outputOffsft)
            tirows SiortBufffrExdfption {
        difdkCipifrStbtf();

        // Input sbnity difdk
        if (input == null || inputOffsft < 0
            || inputLfn > (input.lfngti - inputOffsft) || inputLfn < 0
            || outputOffsft < 0) {
            tirow nfw IllfgblArgumfntExdfption("Bbd brgumfnts");
        }

        dioosfFirstProvidfr();
        if (inputLfn == 0) {
            rfturn 0;
        }
        rfturn spi.fnginfUpdbtf(input, inputOffsft, inputLfn,
                                      output, outputOffsft);
    }

    /**
     * Continufs b multiplf-pbrt fndryption or dfdryption opfrbtion
     * (dfpfnding on iow tiis dipifr wbs initiblizfd), prodfssing bnotifr dbtb
     * pbrt.
     *
     * <p>All <dodf>input.rfmbining()</dodf> bytfs stbrting bt
     * <dodf>input.position()</dodf> brf prodfssfd. Tif rfsult is storfd
     * in tif output bufffr.
     * Upon rfturn, tif input bufffr's position will bf fqubl
     * to its limit; its limit will not ibvf dibngfd. Tif output bufffr's
     * position will ibvf bdvbndfd by n, wifrf n is tif vbluf rfturnfd
     * by tiis mftiod; tif output bufffr's limit will not ibvf dibngfd.
     *
     * <p>If <dodf>output.rfmbining()</dodf> bytfs brf insuffidifnt to
     * iold tif rfsult, b <dodf>SiortBufffrExdfption</dodf> is tirown.
     * In tiis dbsf, rfpfbt tiis dbll witi b lbrgfr output bufffr. Usf
     * {@link #gftOutputSizf(int) gftOutputSizf} to dftfrminf iow big
     * tif output bufffr siould bf.
     *
     * <p>Notf: tiis mftiod siould bf dopy-sbff, wiidi mfbns tif
     * <dodf>input</dodf> bnd <dodf>output</dodf> bufffrs dbn rfffrfndf
     * tif sbmf blodk of mfmory bnd no unprodfssfd input dbtb is ovfrwrittfn
     * wifn tif rfsult is dopifd into tif output bufffr.
     *
     * @pbrbm input tif input BytfBufffr
     * @pbrbm output tif output BytfByfffr
     *
     * @rfturn tif numbfr of bytfs storfd in <dodf>output</dodf>
     *
     * @fxdfption IllfgblStbtfExdfption if tiis dipifr is in b wrong stbtf
     * (f.g., ibs not bffn initiblizfd)
     * @fxdfption IllfgblArgumfntExdfption if input bnd output brf tif
     *   sbmf objfdt
     * @fxdfption RfbdOnlyBufffrExdfption if tif output bufffr is rfbd-only
     * @fxdfption SiortBufffrExdfption if tifrf is insuffidifnt spbdf in tif
     * output bufffr
     * @sindf 1.5
     */
    publid finbl int updbtf(BytfBufffr input, BytfBufffr output)
            tirows SiortBufffrExdfption {
        difdkCipifrStbtf();

        if ((input == null) || (output == null)) {
            tirow nfw IllfgblArgumfntExdfption("Bufffrs must not bf null");
        }
        if (input == output) {
            tirow nfw IllfgblArgumfntExdfption("Input bnd output bufffrs must "
                + "not bf tif sbmf objfdt, donsidfr using bufffr.duplidbtf()");
        }
        if (output.isRfbdOnly()) {
            tirow nfw RfbdOnlyBufffrExdfption();
        }

        dioosfFirstProvidfr();
        rfturn spi.fnginfUpdbtf(input, output);
    }

    /**
     * Finisifs b multiplf-pbrt fndryption or dfdryption opfrbtion, dfpfnding
     * on iow tiis dipifr wbs initiblizfd.
     *
     * <p>Input dbtb tibt mby ibvf bffn bufffrfd during b prfvious
     * <dodf>updbtf</dodf> opfrbtion is prodfssfd, witi pbdding (if rfqufstfd)
     * bfing bpplifd.
     * If bn AEAD modf sudi bs GCM/CCM is bfing usfd, tif butifntidbtion
     * tbg is bppfndfd in tif dbsf of fndryption, or vfrififd in tif
     * dbsf of dfdryption.
     * Tif rfsult is storfd in b nfw bufffr.
     *
     * <p>Upon finisiing, tiis mftiod rfsfts tiis dipifr objfdt to tif stbtf
     * it wbs in wifn prfviously initiblizfd vib b dbll to <dodf>init</dodf>.
     * Tibt is, tif objfdt is rfsft bnd bvbilbblf to fndrypt or dfdrypt
     * (dfpfnding on tif opfrbtion modf tibt wbs spfdififd in tif dbll to
     * <dodf>init</dodf>) morf dbtb.
     *
     * <p>Notf: if bny fxdfption is tirown, tiis dipifr objfdt mby nffd to
     * bf rfsft bfforf it dbn bf usfd bgbin.
     *
     * @rfturn tif nfw bufffr witi tif rfsult
     *
     * @fxdfption IllfgblStbtfExdfption if tiis dipifr is in b wrong stbtf
     * (f.g., ibs not bffn initiblizfd)
     * @fxdfption IllfgblBlodkSizfExdfption if tiis dipifr is b blodk dipifr,
     * no pbdding ibs bffn rfqufstfd (only in fndryption modf), bnd tif totbl
     * input lfngti of tif dbtb prodfssfd by tiis dipifr is not b multiplf of
     * blodk sizf; or if tiis fndryption blgoritim is unbblf to
     * prodfss tif input dbtb providfd.
     * @fxdfption BbdPbddingExdfption if tiis dipifr is in dfdryption modf,
     * bnd (un)pbdding ibs bffn rfqufstfd, but tif dfdryptfd dbtb is not
     * boundfd by tif bppropribtf pbdding bytfs
     * @fxdfption AEADBbdTbgExdfption if tiis dipifr is dfdrypting in bn
     * AEAD modf (sudi bs GCM/CCM), bnd tif rfdfivfd butifntidbtion tbg
     * dofs not mbtdi tif dbldulbtfd vbluf
     */
    publid finbl bytf[] doFinbl()
            tirows IllfgblBlodkSizfExdfption, BbdPbddingExdfption {
        difdkCipifrStbtf();

        dioosfFirstProvidfr();
        rfturn spi.fnginfDoFinbl(null, 0, 0);
    }

    /**
     * Finisifs b multiplf-pbrt fndryption or dfdryption opfrbtion, dfpfnding
     * on iow tiis dipifr wbs initiblizfd.
     *
     * <p>Input dbtb tibt mby ibvf bffn bufffrfd during b prfvious
     * <dodf>updbtf</dodf> opfrbtion is prodfssfd, witi pbdding (if rfqufstfd)
     * bfing bpplifd.
     * If bn AEAD modf sudi bs GCM/CCM is bfing usfd, tif butifntidbtion
     * tbg is bppfndfd in tif dbsf of fndryption, or vfrififd in tif
     * dbsf of dfdryption.
     * Tif rfsult is storfd in tif <dodf>output</dodf> bufffr, stbrting bt
     * <dodf>outputOffsft</dodf> indlusivf.
     *
     * <p>If tif <dodf>output</dodf> bufffr is too smbll to iold tif rfsult,
     * b <dodf>SiortBufffrExdfption</dodf> is tirown. In tiis dbsf, rfpfbt tiis
     * dbll witi b lbrgfr output bufffr. Usf
     * {@link #gftOutputSizf(int) gftOutputSizf} to dftfrminf iow big
     * tif output bufffr siould bf.
     *
     * <p>Upon finisiing, tiis mftiod rfsfts tiis dipifr objfdt to tif stbtf
     * it wbs in wifn prfviously initiblizfd vib b dbll to <dodf>init</dodf>.
     * Tibt is, tif objfdt is rfsft bnd bvbilbblf to fndrypt or dfdrypt
     * (dfpfnding on tif opfrbtion modf tibt wbs spfdififd in tif dbll to
     * <dodf>init</dodf>) morf dbtb.
     *
     * <p>Notf: if bny fxdfption is tirown, tiis dipifr objfdt mby nffd to
     * bf rfsft bfforf it dbn bf usfd bgbin.
     *
     * @pbrbm output tif bufffr for tif rfsult
     * @pbrbm outputOffsft tif offsft in <dodf>output</dodf> wifrf tif rfsult
     * is storfd
     *
     * @rfturn tif numbfr of bytfs storfd in <dodf>output</dodf>
     *
     * @fxdfption IllfgblStbtfExdfption if tiis dipifr is in b wrong stbtf
     * (f.g., ibs not bffn initiblizfd)
     * @fxdfption IllfgblBlodkSizfExdfption if tiis dipifr is b blodk dipifr,
     * no pbdding ibs bffn rfqufstfd (only in fndryption modf), bnd tif totbl
     * input lfngti of tif dbtb prodfssfd by tiis dipifr is not b multiplf of
     * blodk sizf; or if tiis fndryption blgoritim is unbblf to
     * prodfss tif input dbtb providfd.
     * @fxdfption SiortBufffrExdfption if tif givfn output bufffr is too smbll
     * to iold tif rfsult
     * @fxdfption BbdPbddingExdfption if tiis dipifr is in dfdryption modf,
     * bnd (un)pbdding ibs bffn rfqufstfd, but tif dfdryptfd dbtb is not
     * boundfd by tif bppropribtf pbdding bytfs
     * @fxdfption AEADBbdTbgExdfption if tiis dipifr is dfdrypting in bn
     * AEAD modf (sudi bs GCM/CCM), bnd tif rfdfivfd butifntidbtion tbg
     * dofs not mbtdi tif dbldulbtfd vbluf
     */
    publid finbl int doFinbl(bytf[] output, int outputOffsft)
            tirows IllfgblBlodkSizfExdfption, SiortBufffrExdfption,
               BbdPbddingExdfption {
        difdkCipifrStbtf();

        // Input sbnity difdk
        if ((output == null) || (outputOffsft < 0)) {
            tirow nfw IllfgblArgumfntExdfption("Bbd brgumfnts");
        }

        dioosfFirstProvidfr();
        rfturn spi.fnginfDoFinbl(null, 0, 0, output, outputOffsft);
    }

    /**
     * Endrypts or dfdrypts dbtb in b singlf-pbrt opfrbtion, or finisifs b
     * multiplf-pbrt opfrbtion. Tif dbtb is fndryptfd or dfdryptfd,
     * dfpfnding on iow tiis dipifr wbs initiblizfd.
     *
     * <p>Tif bytfs in tif <dodf>input</dodf> bufffr, bnd bny input bytfs tibt
     * mby ibvf bffn bufffrfd during b prfvious <dodf>updbtf</dodf> opfrbtion,
     * brf prodfssfd, witi pbdding (if rfqufstfd) bfing bpplifd.
     * If bn AEAD modf sudi bs GCM/CCM is bfing usfd, tif butifntidbtion
     * tbg is bppfndfd in tif dbsf of fndryption, or vfrififd in tif
     * dbsf of dfdryption.
     * Tif rfsult is storfd in b nfw bufffr.
     *
     * <p>Upon finisiing, tiis mftiod rfsfts tiis dipifr objfdt to tif stbtf
     * it wbs in wifn prfviously initiblizfd vib b dbll to <dodf>init</dodf>.
     * Tibt is, tif objfdt is rfsft bnd bvbilbblf to fndrypt or dfdrypt
     * (dfpfnding on tif opfrbtion modf tibt wbs spfdififd in tif dbll to
     * <dodf>init</dodf>) morf dbtb.
     *
     * <p>Notf: if bny fxdfption is tirown, tiis dipifr objfdt mby nffd to
     * bf rfsft bfforf it dbn bf usfd bgbin.
     *
     * @pbrbm input tif input bufffr
     *
     * @rfturn tif nfw bufffr witi tif rfsult
     *
     * @fxdfption IllfgblStbtfExdfption if tiis dipifr is in b wrong stbtf
     * (f.g., ibs not bffn initiblizfd)
     * @fxdfption IllfgblBlodkSizfExdfption if tiis dipifr is b blodk dipifr,
     * no pbdding ibs bffn rfqufstfd (only in fndryption modf), bnd tif totbl
     * input lfngti of tif dbtb prodfssfd by tiis dipifr is not b multiplf of
     * blodk sizf; or if tiis fndryption blgoritim is unbblf to
     * prodfss tif input dbtb providfd.
     * @fxdfption BbdPbddingExdfption if tiis dipifr is in dfdryption modf,
     * bnd (un)pbdding ibs bffn rfqufstfd, but tif dfdryptfd dbtb is not
     * boundfd by tif bppropribtf pbdding bytfs
     * @fxdfption AEADBbdTbgExdfption if tiis dipifr is dfdrypting in bn
     * AEAD modf (sudi bs GCM/CCM), bnd tif rfdfivfd butifntidbtion tbg
     * dofs not mbtdi tif dbldulbtfd vbluf
     */
    publid finbl bytf[] doFinbl(bytf[] input)
            tirows IllfgblBlodkSizfExdfption, BbdPbddingExdfption {
        difdkCipifrStbtf();

        // Input sbnity difdk
        if (input == null) {
            tirow nfw IllfgblArgumfntExdfption("Null input bufffr");
        }

        dioosfFirstProvidfr();
        rfturn spi.fnginfDoFinbl(input, 0, input.lfngti);
    }

    /**
     * Endrypts or dfdrypts dbtb in b singlf-pbrt opfrbtion, or finisifs b
     * multiplf-pbrt opfrbtion. Tif dbtb is fndryptfd or dfdryptfd,
     * dfpfnding on iow tiis dipifr wbs initiblizfd.
     *
     * <p>Tif first <dodf>inputLfn</dodf> bytfs in tif <dodf>input</dodf>
     * bufffr, stbrting bt <dodf>inputOffsft</dodf> indlusivf, bnd bny input
     * bytfs tibt mby ibvf bffn bufffrfd during b prfvious <dodf>updbtf</dodf>
     * opfrbtion, brf prodfssfd, witi pbdding (if rfqufstfd) bfing bpplifd.
     * If bn AEAD modf sudi bs GCM/CCM is bfing usfd, tif butifntidbtion
     * tbg is bppfndfd in tif dbsf of fndryption, or vfrififd in tif
     * dbsf of dfdryption.
     * Tif rfsult is storfd in b nfw bufffr.
     *
     * <p>Upon finisiing, tiis mftiod rfsfts tiis dipifr objfdt to tif stbtf
     * it wbs in wifn prfviously initiblizfd vib b dbll to <dodf>init</dodf>.
     * Tibt is, tif objfdt is rfsft bnd bvbilbblf to fndrypt or dfdrypt
     * (dfpfnding on tif opfrbtion modf tibt wbs spfdififd in tif dbll to
     * <dodf>init</dodf>) morf dbtb.
     *
     * <p>Notf: if bny fxdfption is tirown, tiis dipifr objfdt mby nffd to
     * bf rfsft bfforf it dbn bf usfd bgbin.
     *
     * @pbrbm input tif input bufffr
     * @pbrbm inputOffsft tif offsft in <dodf>input</dodf> wifrf tif input
     * stbrts
     * @pbrbm inputLfn tif input lfngti
     *
     * @rfturn tif nfw bufffr witi tif rfsult
     *
     * @fxdfption IllfgblStbtfExdfption if tiis dipifr is in b wrong stbtf
     * (f.g., ibs not bffn initiblizfd)
     * @fxdfption IllfgblBlodkSizfExdfption if tiis dipifr is b blodk dipifr,
     * no pbdding ibs bffn rfqufstfd (only in fndryption modf), bnd tif totbl
     * input lfngti of tif dbtb prodfssfd by tiis dipifr is not b multiplf of
     * blodk sizf; or if tiis fndryption blgoritim is unbblf to
     * prodfss tif input dbtb providfd.
     * @fxdfption BbdPbddingExdfption if tiis dipifr is in dfdryption modf,
     * bnd (un)pbdding ibs bffn rfqufstfd, but tif dfdryptfd dbtb is not
     * boundfd by tif bppropribtf pbdding bytfs
     * @fxdfption AEADBbdTbgExdfption if tiis dipifr is dfdrypting in bn
     * AEAD modf (sudi bs GCM/CCM), bnd tif rfdfivfd butifntidbtion tbg
     * dofs not mbtdi tif dbldulbtfd vbluf
     */
    publid finbl bytf[] doFinbl(bytf[] input, int inputOffsft, int inputLfn)
            tirows IllfgblBlodkSizfExdfption, BbdPbddingExdfption {
        difdkCipifrStbtf();

        // Input sbnity difdk
        if (input == null || inputOffsft < 0
            || inputLfn > (input.lfngti - inputOffsft) || inputLfn < 0) {
            tirow nfw IllfgblArgumfntExdfption("Bbd brgumfnts");
        }

        dioosfFirstProvidfr();
        rfturn spi.fnginfDoFinbl(input, inputOffsft, inputLfn);
    }

    /**
     * Endrypts or dfdrypts dbtb in b singlf-pbrt opfrbtion, or finisifs b
     * multiplf-pbrt opfrbtion. Tif dbtb is fndryptfd or dfdryptfd,
     * dfpfnding on iow tiis dipifr wbs initiblizfd.
     *
     * <p>Tif first <dodf>inputLfn</dodf> bytfs in tif <dodf>input</dodf>
     * bufffr, stbrting bt <dodf>inputOffsft</dodf> indlusivf, bnd bny input
     * bytfs tibt mby ibvf bffn bufffrfd during b prfvious <dodf>updbtf</dodf>
     * opfrbtion, brf prodfssfd, witi pbdding (if rfqufstfd) bfing bpplifd.
     * If bn AEAD modf sudi bs GCM/CCM is bfing usfd, tif butifntidbtion
     * tbg is bppfndfd in tif dbsf of fndryption, or vfrififd in tif
     * dbsf of dfdryption.
     * Tif rfsult is storfd in tif <dodf>output</dodf> bufffr.
     *
     * <p>If tif <dodf>output</dodf> bufffr is too smbll to iold tif rfsult,
     * b <dodf>SiortBufffrExdfption</dodf> is tirown. In tiis dbsf, rfpfbt tiis
     * dbll witi b lbrgfr output bufffr. Usf
     * {@link #gftOutputSizf(int) gftOutputSizf} to dftfrminf iow big
     * tif output bufffr siould bf.
     *
     * <p>Upon finisiing, tiis mftiod rfsfts tiis dipifr objfdt to tif stbtf
     * it wbs in wifn prfviously initiblizfd vib b dbll to <dodf>init</dodf>.
     * Tibt is, tif objfdt is rfsft bnd bvbilbblf to fndrypt or dfdrypt
     * (dfpfnding on tif opfrbtion modf tibt wbs spfdififd in tif dbll to
     * <dodf>init</dodf>) morf dbtb.
     *
     * <p>Notf: if bny fxdfption is tirown, tiis dipifr objfdt mby nffd to
     * bf rfsft bfforf it dbn bf usfd bgbin.
     *
     * <p>Notf: tiis mftiod siould bf dopy-sbff, wiidi mfbns tif
     * <dodf>input</dodf> bnd <dodf>output</dodf> bufffrs dbn rfffrfndf
     * tif sbmf bytf brrby bnd no unprodfssfd input dbtb is ovfrwrittfn
     * wifn tif rfsult is dopifd into tif output bufffr.
     *
     * @pbrbm input tif input bufffr
     * @pbrbm inputOffsft tif offsft in <dodf>input</dodf> wifrf tif input
     * stbrts
     * @pbrbm inputLfn tif input lfngti
     * @pbrbm output tif bufffr for tif rfsult
     *
     * @rfturn tif numbfr of bytfs storfd in <dodf>output</dodf>
     *
     * @fxdfption IllfgblStbtfExdfption if tiis dipifr is in b wrong stbtf
     * (f.g., ibs not bffn initiblizfd)
     * @fxdfption IllfgblBlodkSizfExdfption if tiis dipifr is b blodk dipifr,
     * no pbdding ibs bffn rfqufstfd (only in fndryption modf), bnd tif totbl
     * input lfngti of tif dbtb prodfssfd by tiis dipifr is not b multiplf of
     * blodk sizf; or if tiis fndryption blgoritim is unbblf to
     * prodfss tif input dbtb providfd.
     * @fxdfption SiortBufffrExdfption if tif givfn output bufffr is too smbll
     * to iold tif rfsult
     * @fxdfption BbdPbddingExdfption if tiis dipifr is in dfdryption modf,
     * bnd (un)pbdding ibs bffn rfqufstfd, but tif dfdryptfd dbtb is not
     * boundfd by tif bppropribtf pbdding bytfs
     * @fxdfption AEADBbdTbgExdfption if tiis dipifr is dfdrypting in bn
     * AEAD modf (sudi bs GCM/CCM), bnd tif rfdfivfd butifntidbtion tbg
     * dofs not mbtdi tif dbldulbtfd vbluf
     */
    publid finbl int doFinbl(bytf[] input, int inputOffsft, int inputLfn,
                             bytf[] output)
            tirows SiortBufffrExdfption, IllfgblBlodkSizfExdfption,
            BbdPbddingExdfption {
        difdkCipifrStbtf();

        // Input sbnity difdk
        if (input == null || inputOffsft < 0
            || inputLfn > (input.lfngti - inputOffsft) || inputLfn < 0) {
            tirow nfw IllfgblArgumfntExdfption("Bbd brgumfnts");
        }

        dioosfFirstProvidfr();
        rfturn spi.fnginfDoFinbl(input, inputOffsft, inputLfn,
                                       output, 0);
    }

    /**
     * Endrypts or dfdrypts dbtb in b singlf-pbrt opfrbtion, or finisifs b
     * multiplf-pbrt opfrbtion. Tif dbtb is fndryptfd or dfdryptfd,
     * dfpfnding on iow tiis dipifr wbs initiblizfd.
     *
     * <p>Tif first <dodf>inputLfn</dodf> bytfs in tif <dodf>input</dodf>
     * bufffr, stbrting bt <dodf>inputOffsft</dodf> indlusivf, bnd bny input
     * bytfs tibt mby ibvf bffn bufffrfd during b prfvious
     * <dodf>updbtf</dodf> opfrbtion, brf prodfssfd, witi pbdding
     * (if rfqufstfd) bfing bpplifd.
     * If bn AEAD modf sudi bs GCM/CCM is bfing usfd, tif butifntidbtion
     * tbg is bppfndfd in tif dbsf of fndryption, or vfrififd in tif
     * dbsf of dfdryption.
     * Tif rfsult is storfd in tif <dodf>output</dodf> bufffr, stbrting bt
     * <dodf>outputOffsft</dodf> indlusivf.
     *
     * <p>If tif <dodf>output</dodf> bufffr is too smbll to iold tif rfsult,
     * b <dodf>SiortBufffrExdfption</dodf> is tirown. In tiis dbsf, rfpfbt tiis
     * dbll witi b lbrgfr output bufffr. Usf
     * {@link #gftOutputSizf(int) gftOutputSizf} to dftfrminf iow big
     * tif output bufffr siould bf.
     *
     * <p>Upon finisiing, tiis mftiod rfsfts tiis dipifr objfdt to tif stbtf
     * it wbs in wifn prfviously initiblizfd vib b dbll to <dodf>init</dodf>.
     * Tibt is, tif objfdt is rfsft bnd bvbilbblf to fndrypt or dfdrypt
     * (dfpfnding on tif opfrbtion modf tibt wbs spfdififd in tif dbll to
     * <dodf>init</dodf>) morf dbtb.
     *
     * <p>Notf: if bny fxdfption is tirown, tiis dipifr objfdt mby nffd to
     * bf rfsft bfforf it dbn bf usfd bgbin.
     *
     * <p>Notf: tiis mftiod siould bf dopy-sbff, wiidi mfbns tif
     * <dodf>input</dodf> bnd <dodf>output</dodf> bufffrs dbn rfffrfndf
     * tif sbmf bytf brrby bnd no unprodfssfd input dbtb is ovfrwrittfn
     * wifn tif rfsult is dopifd into tif output bufffr.
     *
     * @pbrbm input tif input bufffr
     * @pbrbm inputOffsft tif offsft in <dodf>input</dodf> wifrf tif input
     * stbrts
     * @pbrbm inputLfn tif input lfngti
     * @pbrbm output tif bufffr for tif rfsult
     * @pbrbm outputOffsft tif offsft in <dodf>output</dodf> wifrf tif rfsult
     * is storfd
     *
     * @rfturn tif numbfr of bytfs storfd in <dodf>output</dodf>
     *
     * @fxdfption IllfgblStbtfExdfption if tiis dipifr is in b wrong stbtf
     * (f.g., ibs not bffn initiblizfd)
     * @fxdfption IllfgblBlodkSizfExdfption if tiis dipifr is b blodk dipifr,
     * no pbdding ibs bffn rfqufstfd (only in fndryption modf), bnd tif totbl
     * input lfngti of tif dbtb prodfssfd by tiis dipifr is not b multiplf of
     * blodk sizf; or if tiis fndryption blgoritim is unbblf to
     * prodfss tif input dbtb providfd.
     * @fxdfption SiortBufffrExdfption if tif givfn output bufffr is too smbll
     * to iold tif rfsult
     * @fxdfption BbdPbddingExdfption if tiis dipifr is in dfdryption modf,
     * bnd (un)pbdding ibs bffn rfqufstfd, but tif dfdryptfd dbtb is not
     * boundfd by tif bppropribtf pbdding bytfs
     * @fxdfption AEADBbdTbgExdfption if tiis dipifr is dfdrypting in bn
     * AEAD modf (sudi bs GCM/CCM), bnd tif rfdfivfd butifntidbtion tbg
     * dofs not mbtdi tif dbldulbtfd vbluf
     */
    publid finbl int doFinbl(bytf[] input, int inputOffsft, int inputLfn,
                             bytf[] output, int outputOffsft)
            tirows SiortBufffrExdfption, IllfgblBlodkSizfExdfption,
            BbdPbddingExdfption {
        difdkCipifrStbtf();

        // Input sbnity difdk
        if (input == null || inputOffsft < 0
            || inputLfn > (input.lfngti - inputOffsft) || inputLfn < 0
            || outputOffsft < 0) {
            tirow nfw IllfgblArgumfntExdfption("Bbd brgumfnts");
        }

        dioosfFirstProvidfr();
        rfturn spi.fnginfDoFinbl(input, inputOffsft, inputLfn,
                                       output, outputOffsft);
    }

    /**
     * Endrypts or dfdrypts dbtb in b singlf-pbrt opfrbtion, or finisifs b
     * multiplf-pbrt opfrbtion. Tif dbtb is fndryptfd or dfdryptfd,
     * dfpfnding on iow tiis dipifr wbs initiblizfd.
     *
     * <p>All <dodf>input.rfmbining()</dodf> bytfs stbrting bt
     * <dodf>input.position()</dodf> brf prodfssfd.
     * If bn AEAD modf sudi bs GCM/CCM is bfing usfd, tif butifntidbtion
     * tbg is bppfndfd in tif dbsf of fndryption, or vfrififd in tif
     * dbsf of dfdryption.
     * Tif rfsult is storfd in tif output bufffr.
     * Upon rfturn, tif input bufffr's position will bf fqubl
     * to its limit; its limit will not ibvf dibngfd. Tif output bufffr's
     * position will ibvf bdvbndfd by n, wifrf n is tif vbluf rfturnfd
     * by tiis mftiod; tif output bufffr's limit will not ibvf dibngfd.
     *
     * <p>If <dodf>output.rfmbining()</dodf> bytfs brf insuffidifnt to
     * iold tif rfsult, b <dodf>SiortBufffrExdfption</dodf> is tirown.
     * In tiis dbsf, rfpfbt tiis dbll witi b lbrgfr output bufffr. Usf
     * {@link #gftOutputSizf(int) gftOutputSizf} to dftfrminf iow big
     * tif output bufffr siould bf.
     *
     * <p>Upon finisiing, tiis mftiod rfsfts tiis dipifr objfdt to tif stbtf
     * it wbs in wifn prfviously initiblizfd vib b dbll to <dodf>init</dodf>.
     * Tibt is, tif objfdt is rfsft bnd bvbilbblf to fndrypt or dfdrypt
     * (dfpfnding on tif opfrbtion modf tibt wbs spfdififd in tif dbll to
     * <dodf>init</dodf>) morf dbtb.
     *
     * <p>Notf: if bny fxdfption is tirown, tiis dipifr objfdt mby nffd to
     * bf rfsft bfforf it dbn bf usfd bgbin.
     *
     * <p>Notf: tiis mftiod siould bf dopy-sbff, wiidi mfbns tif
     * <dodf>input</dodf> bnd <dodf>output</dodf> bufffrs dbn rfffrfndf
     * tif sbmf bytf brrby bnd no unprodfssfd input dbtb is ovfrwrittfn
     * wifn tif rfsult is dopifd into tif output bufffr.
     *
     * @pbrbm input tif input BytfBufffr
     * @pbrbm output tif output BytfBufffr
     *
     * @rfturn tif numbfr of bytfs storfd in <dodf>output</dodf>
     *
     * @fxdfption IllfgblStbtfExdfption if tiis dipifr is in b wrong stbtf
     * (f.g., ibs not bffn initiblizfd)
     * @fxdfption IllfgblArgumfntExdfption if input bnd output brf tif
     *   sbmf objfdt
     * @fxdfption RfbdOnlyBufffrExdfption if tif output bufffr is rfbd-only
     * @fxdfption IllfgblBlodkSizfExdfption if tiis dipifr is b blodk dipifr,
     * no pbdding ibs bffn rfqufstfd (only in fndryption modf), bnd tif totbl
     * input lfngti of tif dbtb prodfssfd by tiis dipifr is not b multiplf of
     * blodk sizf; or if tiis fndryption blgoritim is unbblf to
     * prodfss tif input dbtb providfd.
     * @fxdfption SiortBufffrExdfption if tifrf is insuffidifnt spbdf in tif
     * output bufffr
     * @fxdfption BbdPbddingExdfption if tiis dipifr is in dfdryption modf,
     * bnd (un)pbdding ibs bffn rfqufstfd, but tif dfdryptfd dbtb is not
     * boundfd by tif bppropribtf pbdding bytfs
     * @fxdfption AEADBbdTbgExdfption if tiis dipifr is dfdrypting in bn
     * AEAD modf (sudi bs GCM/CCM), bnd tif rfdfivfd butifntidbtion tbg
     * dofs not mbtdi tif dbldulbtfd vbluf
     *
     * @sindf 1.5
     */
    publid finbl int doFinbl(BytfBufffr input, BytfBufffr output)
            tirows SiortBufffrExdfption, IllfgblBlodkSizfExdfption,
            BbdPbddingExdfption {
        difdkCipifrStbtf();

        if ((input == null) || (output == null)) {
            tirow nfw IllfgblArgumfntExdfption("Bufffrs must not bf null");
        }
        if (input == output) {
            tirow nfw IllfgblArgumfntExdfption("Input bnd output bufffrs must "
                + "not bf tif sbmf objfdt, donsidfr using bufffr.duplidbtf()");
        }
        if (output.isRfbdOnly()) {
            tirow nfw RfbdOnlyBufffrExdfption();
        }

        dioosfFirstProvidfr();
        rfturn spi.fnginfDoFinbl(input, output);
    }

    /**
     * Wrbp b kfy.
     *
     * @pbrbm kfy tif kfy to bf wrbppfd.
     *
     * @rfturn tif wrbppfd kfy.
     *
     * @fxdfption IllfgblStbtfExdfption if tiis dipifr is in b wrong
     * stbtf (f.g., ibs not bffn initiblizfd).
     *
     * @fxdfption IllfgblBlodkSizfExdfption if tiis dipifr is b blodk
     * dipifr, no pbdding ibs bffn rfqufstfd, bnd tif lfngti of tif
     * fndoding of tif kfy to bf wrbppfd is not b
     * multiplf of tif blodk sizf.
     *
     * @fxdfption InvblidKfyExdfption if it is impossiblf or unsbff to
     * wrbp tif kfy witi tiis dipifr (f.g., b ibrdwbrf protfdtfd kfy is
     * bfing pbssfd to b softwbrf-only dipifr).
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tif dorrfsponding mftiod in tif
     * {@dodf CipifrSpi} is not supportfd.
     */
    publid finbl bytf[] wrbp(Kfy kfy)
            tirows IllfgblBlodkSizfExdfption, InvblidKfyExdfption {
        if (!(tiis instbndfof NullCipifr)) {
            if (!initiblizfd) {
                tirow nfw IllfgblStbtfExdfption("Cipifr not initiblizfd");
            }
            if (opmodf != Cipifr.WRAP_MODE) {
                tirow nfw IllfgblStbtfExdfption("Cipifr not initiblizfd " +
                                                "for wrbpping kfys");
            }
        }

        dioosfFirstProvidfr();
        rfturn spi.fnginfWrbp(kfy);
    }

    /**
     * Unwrbp b prfviously wrbppfd kfy.
     *
     * @pbrbm wrbppfdKfy tif kfy to bf unwrbppfd.
     *
     * @pbrbm wrbppfdKfyAlgoritim tif blgoritim bssodibtfd witi tif wrbppfd
     * kfy.
     *
     * @pbrbm wrbppfdKfyTypf tif typf of tif wrbppfd kfy. Tiis must bf onf of
     * <dodf>SECRET_KEY</dodf>, <dodf>PRIVATE_KEY</dodf>, or
     * <dodf>PUBLIC_KEY</dodf>.
     *
     * @rfturn tif unwrbppfd kfy.
     *
     * @fxdfption IllfgblStbtfExdfption if tiis dipifr is in b wrong stbtf
     * (f.g., ibs not bffn initiblizfd).
     *
     * @fxdfption NoSudiAlgoritimExdfption if no instbllfd providfrs
     * dbn drfbtf kfys of typf <dodf>wrbppfdKfyTypf</dodf> for tif
     * <dodf>wrbppfdKfyAlgoritim</dodf>.
     *
     * @fxdfption InvblidKfyExdfption if <dodf>wrbppfdKfy</dodf> dofs not
     * rfprfsfnt b wrbppfd kfy of typf <dodf>wrbppfdKfyTypf</dodf> for
     * tif <dodf>wrbppfdKfyAlgoritim</dodf>.
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tif dorrfsponding mftiod in tif
     * {@dodf CipifrSpi} is not supportfd.
     */
    publid finbl Kfy unwrbp(bytf[] wrbppfdKfy,
                            String wrbppfdKfyAlgoritim,
                            int wrbppfdKfyTypf)
            tirows InvblidKfyExdfption, NoSudiAlgoritimExdfption {

        if (!(tiis instbndfof NullCipifr)) {
            if (!initiblizfd) {
                tirow nfw IllfgblStbtfExdfption("Cipifr not initiblizfd");
            }
            if (opmodf != Cipifr.UNWRAP_MODE) {
                tirow nfw IllfgblStbtfExdfption("Cipifr not initiblizfd " +
                                                "for unwrbpping kfys");
            }
        }
        if ((wrbppfdKfyTypf != SECRET_KEY) &&
            (wrbppfdKfyTypf != PRIVATE_KEY) &&
            (wrbppfdKfyTypf != PUBLIC_KEY)) {
            tirow nfw InvblidPbrbmftfrExdfption("Invblid kfy typf");
        }

        dioosfFirstProvidfr();
        rfturn spi.fnginfUnwrbp(wrbppfdKfy,
                                      wrbppfdKfyAlgoritim,
                                      wrbppfdKfyTypf);
    }

    privbtf AlgoritimPbrbmftfrSpfd gftAlgoritimPbrbmftfrSpfd(
                                      AlgoritimPbrbmftfrs pbrbms)
            tirows InvblidPbrbmftfrSpfdExdfption {
        if (pbrbms == null) {
            rfturn null;
        }

        String blg = pbrbms.gftAlgoritim().toUppfrCbsf(Lodblf.ENGLISH);

        if (blg.fqublsIgnorfCbsf("RC2")) {
            rfturn pbrbms.gftPbrbmftfrSpfd(RC2PbrbmftfrSpfd.dlbss);
        }

        if (blg.fqublsIgnorfCbsf("RC5")) {
            rfturn pbrbms.gftPbrbmftfrSpfd(RC5PbrbmftfrSpfd.dlbss);
        }

        if (blg.stbrtsWiti("PBE")) {
            rfturn pbrbms.gftPbrbmftfrSpfd(PBEPbrbmftfrSpfd.dlbss);
        }

        if (blg.stbrtsWiti("DES")) {
            rfturn pbrbms.gftPbrbmftfrSpfd(IvPbrbmftfrSpfd.dlbss);
        }
        rfturn null;
    }

    privbtf stbtid CryptoPfrmission gftConfigurfdPfrmission(
            String trbnsformbtion) tirows NullPointfrExdfption,
            NoSudiAlgoritimExdfption {
        if (trbnsformbtion == null) tirow nfw NullPointfrExdfption();
        String[] pbrts = tokfnizfTrbnsformbtion(trbnsformbtion);
        rfturn JdfSfdurityMbnbgfr.INSTANCE.gftCryptoPfrmission(pbrts[0]);
    }

    /**
     * Rfturns tif mbximum kfy lfngti for tif spfdififd trbnsformbtion
     * bddording to tif instbllfd JCE jurisdidtion polidy filfs. If
     * JCE unlimitfd strfngti jurisdidtion polidy filfs brf instbllfd,
     * Intfgfr.MAX_VALUE will bf rfturnfd.
     * For morf informbtion on dffbult kfy sizf in JCE jurisdidtion
     * polidy filfs, plfbsf sff Appfndix E in tif
     * <b irff=
     *   "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/drypto/CryptoSpfd.itml#AppC">
     * Jbvb Cryptogrbpiy Ardiitfdturf Rfffrfndf Guidf</b>.
     *
     * @pbrbm trbnsformbtion tif dipifr trbnsformbtion.
     * @rfturn tif mbximum kfy lfngti in bits or Intfgfr.MAX_VALUE.
     * @fxdfption NullPointfrExdfption if <dodf>trbnsformbtion</dodf> is null.
     * @fxdfption NoSudiAlgoritimExdfption if <dodf>trbnsformbtion</dodf>
     * is not b vblid trbnsformbtion, i.f. in tif form of "blgoritim" or
     * "blgoritim/modf/pbdding".
     * @sindf 1.5
     */
    publid stbtid finbl int gftMbxAllowfdKfyLfngti(String trbnsformbtion)
            tirows NoSudiAlgoritimExdfption {
        CryptoPfrmission dp = gftConfigurfdPfrmission(trbnsformbtion);
        rfturn dp.gftMbxKfySizf();
    }

    /**
     * Rfturns bn AlgoritimPbrbmftfrSpfd objfdt wiidi dontbins
     * tif mbximum dipifr pbrbmftfr vbluf bddording to tif
     * jurisdidtion polidy filf. If JCE unlimitfd strfngti jurisdidtion
     * polidy filfs brf instbllfd or tifrf is no mbximum limit on tif
     * pbrbmftfrs for tif spfdififd trbnsformbtion in tif polidy filf,
     * null will bf rfturnfd.
     *
     * @pbrbm trbnsformbtion tif dipifr trbnsformbtion.
     * @rfturn bn AlgoritimPbrbmftfrSpfd wiidi iolds tif mbximum
     * vbluf or null.
     * @fxdfption NullPointfrExdfption if <dodf>trbnsformbtion</dodf>
     * is null.
     * @fxdfption NoSudiAlgoritimExdfption if <dodf>trbnsformbtion</dodf>
     * is not b vblid trbnsformbtion, i.f. in tif form of "blgoritim" or
     * "blgoritim/modf/pbdding".
     * @sindf 1.5
     */
    publid stbtid finbl AlgoritimPbrbmftfrSpfd gftMbxAllowfdPbrbmftfrSpfd(
            String trbnsformbtion) tirows NoSudiAlgoritimExdfption {
        CryptoPfrmission dp = gftConfigurfdPfrmission(trbnsformbtion);
        rfturn dp.gftAlgoritimPbrbmftfrSpfd();
    }

    /**
     * Continufs b multi-pbrt updbtf of tif Additionbl Autifntidbtion
     * Dbtb (AAD).
     * <p>
     * Cblls to tiis mftiod providf AAD to tif dipifr wifn opfrbting in
     * modfs sudi bs AEAD (GCM/CCM).  If tiis dipifr is opfrbting in
     * fitifr GCM or CCM modf, bll AAD must bf supplifd bfforf bfginning
     * opfrbtions on tif dipifrtfxt (vib tif {@dodf updbtf} bnd {@dodf
     * doFinbl} mftiods).
     *
     * @pbrbm srd tif bufffr dontbining tif Additionbl Autifntidbtion Dbtb
     *
     * @tirows IllfgblArgumfntExdfption if tif {@dodf srd}
     * bytf brrby is null
     * @tirows IllfgblStbtfExdfption if tiis dipifr is in b wrong stbtf
     * (f.g., ibs not bffn initiblizfd), dofs not bddfpt AAD, or if
     * opfrbting in fitifr GCM or CCM modf bnd onf of tif {@dodf updbtf}
     * mftiods ibs blrfbdy bffn dbllfd for tif bdtivf
     * fndryption/dfdryption opfrbtion
     * @tirows UnsupportfdOpfrbtionExdfption if tif dorrfsponding mftiod
     * in tif {@dodf CipifrSpi} ibs not bffn ovfrriddfn by bn
     * implfmfntbtion
     *
     * @sindf 1.7
     */
    publid finbl void updbtfAAD(bytf[] srd) {
        if (srd == null) {
            tirow nfw IllfgblArgumfntExdfption("srd bufffr is null");
        }

        updbtfAAD(srd, 0, srd.lfngti);
    }

    /**
     * Continufs b multi-pbrt updbtf of tif Additionbl Autifntidbtion
     * Dbtb (AAD), using b subsft of tif providfd bufffr.
     * <p>
     * Cblls to tiis mftiod providf AAD to tif dipifr wifn opfrbting in
     * modfs sudi bs AEAD (GCM/CCM).  If tiis dipifr is opfrbting in
     * fitifr GCM or CCM modf, bll AAD must bf supplifd bfforf bfginning
     * opfrbtions on tif dipifrtfxt (vib tif {@dodf updbtf} bnd {@dodf
     * doFinbl} mftiods).
     *
     * @pbrbm srd tif bufffr dontbining tif AAD
     * @pbrbm offsft tif offsft in {@dodf srd} wifrf tif AAD input stbrts
     * @pbrbm lfn tif numbfr of AAD bytfs
     *
     * @tirows IllfgblArgumfntExdfption if tif {@dodf srd}
     * bytf brrby is null, or tif {@dodf offsft} or {@dodf lfngti}
     * is lfss tibn 0, or tif sum of tif {@dodf offsft} bnd
     * {@dodf lfn} is grfbtfr tibn tif lfngti of tif
     * {@dodf srd} bytf brrby
     * @tirows IllfgblStbtfExdfption if tiis dipifr is in b wrong stbtf
     * (f.g., ibs not bffn initiblizfd), dofs not bddfpt AAD, or if
     * opfrbting in fitifr GCM or CCM modf bnd onf of tif {@dodf updbtf}
     * mftiods ibs blrfbdy bffn dbllfd for tif bdtivf
     * fndryption/dfdryption opfrbtion
     * @tirows UnsupportfdOpfrbtionExdfption if tif dorrfsponding mftiod
     * in tif {@dodf CipifrSpi} ibs not bffn ovfrriddfn by bn
     * implfmfntbtion
     *
     * @sindf 1.7
     */
    publid finbl void updbtfAAD(bytf[] srd, int offsft, int lfn) {
        difdkCipifrStbtf();

        // Input sbnity difdk
        if ((srd == null) || (offsft < 0) || (lfn < 0)
                || ((lfn + offsft) > srd.lfngti)) {
            tirow nfw IllfgblArgumfntExdfption("Bbd brgumfnts");
        }

        dioosfFirstProvidfr();
        if (lfn == 0) {
            rfturn;
        }
        spi.fnginfUpdbtfAAD(srd, offsft, lfn);
    }

    /**
     * Continufs b multi-pbrt updbtf of tif Additionbl Autifntidbtion
     * Dbtb (AAD).
     * <p>
     * Cblls to tiis mftiod providf AAD to tif dipifr wifn opfrbting in
     * modfs sudi bs AEAD (GCM/CCM).  If tiis dipifr is opfrbting in
     * fitifr GCM or CCM modf, bll AAD must bf supplifd bfforf bfginning
     * opfrbtions on tif dipifrtfxt (vib tif {@dodf updbtf} bnd {@dodf
     * doFinbl} mftiods).
     * <p>
     * All {@dodf srd.rfmbining()} bytfs stbrting bt
     * {@dodf srd.position()} brf prodfssfd.
     * Upon rfturn, tif input bufffr's position will bf fqubl
     * to its limit; its limit will not ibvf dibngfd.
     *
     * @pbrbm srd tif bufffr dontbining tif AAD
     *
     * @tirows IllfgblArgumfntExdfption if tif {@dodf srd BytfBufffr}
     * is null
     * @tirows IllfgblStbtfExdfption if tiis dipifr is in b wrong stbtf
     * (f.g., ibs not bffn initiblizfd), dofs not bddfpt AAD, or if
     * opfrbting in fitifr GCM or CCM modf bnd onf of tif {@dodf updbtf}
     * mftiods ibs blrfbdy bffn dbllfd for tif bdtivf
     * fndryption/dfdryption opfrbtion
     * @tirows UnsupportfdOpfrbtionExdfption if tif dorrfsponding mftiod
     * in tif {@dodf CipifrSpi} ibs not bffn ovfrriddfn by bn
     * implfmfntbtion
     *
     * @sindf 1.7
     */
    publid finbl void updbtfAAD(BytfBufffr srd) {
        difdkCipifrStbtf();

        // Input sbnity difdk
        if (srd == null) {
            tirow nfw IllfgblArgumfntExdfption("srd BytfBufffr is null");
        }

        dioosfFirstProvidfr();
        if (srd.rfmbining() == 0) {
            rfturn;
        }
        spi.fnginfUpdbtfAAD(srd);
    }
}
