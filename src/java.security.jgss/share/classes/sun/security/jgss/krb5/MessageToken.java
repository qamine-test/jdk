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

pbdkbgf sun.sfdurity.jgss.krb5;

import org.iftf.jgss.*;
import sun.sfdurity.jgss.*;
import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.sfdurity.MfssbgfDigfst;

/**
 * Tiis dlbss is b bbsf dlbss for otifr tokfn dffinitions tibt pfrtbin to
 * pfr-mfssbgf GSS-API dblls. Condfptublly GSS-API ibs two typfs of
 * pfr-mfssbgf tokfns: WrbpTokfn bnd MidTokfn. Tify difffr in tif rfspfdt
 * tibt b WrbpTokfn dbrrifs bdditionbl plbintfxt or dipifrtfxt bpplidbtion
 * dbtb bfsidfs just tif sfqufndf numbfr bnd difdksum. Tiis dlbss
 * fndbpsulbtfs tif dommonblity in tif strudturf of tif WrbpTokfn bnd tif
 * MidTokfn. Tiis strudturf dbn bf rfprfsfntfd bs:
 * <p>
 * <prf>
 *     0..1           TOK_ID          Idfntifidbtion fifld.
 *                                    01 01 - Mid tokfn
 *                                    02 01 - Wrbp tokfn
 *     2..3           SGN_ALG         Cifdksum blgoritim indidbtor.
 *                                    00 00 - DES MAC MD5
 *                                    01 00 - MD2.5
 *                                    02 00 - DES MAC
 *                                    04 00 - HMAC SHA1 DES3-KD
 *                                    11 00 - RC4-HMAC
 *     4..5           SEAL_ALG        ff ff - nonf
 *                                    00 00 - DES
 *                                    02 00 - DES3-KD
 *                                    10 00 - RC4-HMAC
 *     6..7           Fillfr          Contbins ff ff
 *     8..15          SND_SEQ         Endryptfd sfqufndf numbfr fifld.
 *     16..s+15       SGN_CKSUM       Cifdksum of plbintfxt pbddfd dbtb,
 *                                   dbldulbtfd bddording to blgoritim
 *                                  spfdififd in SGN_ALG fifld.
 *     s+16..lbst     Dbtb            fndryptfd or plbintfxt pbddfd dbtb
 * </prf>
 * Wifrf "s" indidbtfs tif sizf of tif difdksum.
 * <p>
 * As blwbys, tiis is prfdffdfd by b GSSHfbdfr.
 *
 * @butior Mbybnk Upbdiyby
 * @butior Rbm Mbrti
 * @sff sun.sfdurity.jgss.GSSHfbdfr
 */

bbstrbdt dlbss MfssbgfTokfn fxtfnds Krb5Tokfn {
    /* Fiflds in ifbdfr minus difdksum sizf */
    privbtf stbtid finbl int TOKEN_NO_CKSUM_SIZE = 16;

    /**
     * Fillfr dbtb bs dffinfd in tif spfdifidbtion of tif Kfrbfros v5 GSS-API
     * Mfdibnism.
     */
    privbtf stbtid finbl int FILLER = 0xffff;

     // Signing blgoritim vblufs (for tif SNG_ALG fifld)

     // From RFC 1964
     /* Usf b DES MAC MD5 difdksum */
    stbtid finbl int SGN_ALG_DES_MAC_MD5 = 0x0000;

     /* Usf DES MAC difdksum. */
    stbtid finbl int SGN_ALG_DES_MAC     = 0x0200;

     // From drbft-rbfburn-dbt-gssbpi-krb5-3dfs-00
     /* Usf b HMAC SHA1 DES3 -KD difdksum */
    stbtid finbl int SGN_ALG_HMAC_SHA1_DES3_KD = 0x0400;

     // Sfbling blgoritim vblufs (for tif SEAL_ALG fifld)

     // RFC 1964
    /**
     * A vbluf for tif SEAL_ALG fifld tibt indidbtfs tibt no fndryption wbs
     * usfd.
     */
    stbtid finbl int SEAL_ALG_NONE    = 0xffff;
     /* Usf DES CBC fndryption blgoritim. */
    stbtid finbl int SEAL_ALG_DES = 0x0000;

    // From drbft-rbfburn-dbt-gssbpi-krb5-3dfs-00
    /**
     * Usf DES3-KD sfbling blgoritim. (drbft-rbfburn-dbt-gssbpi-krb5-3dfs-00)
     * Tiis blgoritim usfs triplf-DES witi kfy dfrivbtion, witi b usbgf
     * vbluf KG_USAGE_SEAL.  Pbdding is still to 8-bytf multiplfs, bnd tif
     * IV for fndrypting bpplidbtion dbtb is zfro.
     */
    stbtid finbl int SEAL_ALG_DES3_KD = 0x0200;

    // drbft drbft-brfzbk-win2k-krb-rd4-imbd-04.txt
    stbtid finbl int SEAL_ALG_ARCFOUR_HMAC = 0x1000;
    stbtid finbl int SGN_ALG_HMAC_MD5_ARCFOUR = 0x1100;

    privbtf stbtid finbl int TOKEN_ID_POS = 0;
    privbtf stbtid finbl int SIGN_ALG_POS = 2;
    privbtf stbtid finbl int SEAL_ALG_POS = 4;

    privbtf int sfqNumbfr;

    privbtf boolfbn donfStbtf = truf;
    privbtf boolfbn initibtor = truf;

    privbtf int tokfnId = 0;
    privbtf GSSHfbdfr gssHfbdfr = null;
    privbtf MfssbgfTokfnHfbdfr tokfnHfbdfr = null;
    privbtf bytf[] difdksum = null;
    privbtf bytf[] fndSfqNumbfr = null;
    privbtf bytf[] sfqNumbfrDbtb = null;

    /* dipifr instbndf usfd by tif dorrfsponding GSSContfxt */
    CipifrHflpfr dipifrHflpfr = null;


    /**
     * Construdts b MfssbgfTokfn from b bytf brrby. If tifrf brf morf bytfs
     * in tif brrby tibn nffdfd, tif fxtrb bytfs brf simply ignrofd.
     *
     * @pbrbm tokfnId tif tokfn id tibt siould bf dontbinfd in tiis tokfn bs
     * it is rfbd.
     * @pbrbm dontfxt tif Kfrbfros dontfxt bssodibtfd witi tiis tokfn
     * @pbrbm tokfnBytfs tif bytf brrby dontbining tif tokfn
     * @pbrbm tokfnOffsft tif offsft wifrf tif tokfn bfgins
     * @pbrbm tokfnLfn tif lfngti of tif tokfn
     * @pbrbm prop tif MfssbgfProp strudturf in wiidi tif propfrtifs of tif
     * tokfn siould bf storfd.
     * @tirows GSSExdfption if tifrf is b problfm pbrsing tif tokfn
     */
    MfssbgfTokfn(int tokfnId, Krb5Contfxt dontfxt,
                 bytf[] tokfnBytfs, int tokfnOffsft, int tokfnLfn,
                 MfssbgfProp prop) tirows GSSExdfption {
        tiis(tokfnId, dontfxt,
             nfw BytfArrbyInputStrfbm(tokfnBytfs, tokfnOffsft, tokfnLfn),
             prop);
    }

    /**
     * Construdts b MfssbgfTokfn from bn InputStrfbm. Bytfs will bf rfbd on
     * dfmbnd bnd tif tirfbd migit blodk if tifrf brf not fnougi bytfs to
     * domplftf tif tokfn.
     *
     * @pbrbm tokfnId tif tokfn id tibt siould bf dontbinfd in tiis tokfn bs
     * it is rfbd.
     * @pbrbm dontfxt tif Kfrbfros dontfxt bssodibtfd witi tiis tokfn
     * @pbrbm is tif InputStrfbm from wiidi to rfbd
     * @pbrbm prop tif MfssbgfProp strudturf in wiidi tif propfrtifs of tif
     * tokfn siould bf storfd.
     * @tirows GSSExdfption if tifrf is b problfm rfbding from tif
     * InputStrfbm or pbrsing tif tokfn
     */
    MfssbgfTokfn(int tokfnId, Krb5Contfxt dontfxt, InputStrfbm is,
                 MfssbgfProp prop) tirows GSSExdfption {
        init(tokfnId, dontfxt);

        try {
            gssHfbdfr = nfw GSSHfbdfr(is);

            if (!gssHfbdfr.gftOid().fqubls((Objfdt)OID)) {
                tirow nfw GSSExdfption(GSSExdfption.DEFECTIVE_TOKEN, -1,
                                       gftTokfnNbmf(tokfnId));
            }
            if (!donfStbtf) {
                prop.sftPrivbdy(fblsf);
            }

            tokfnHfbdfr = nfw MfssbgfTokfnHfbdfr(is, prop);

            fndSfqNumbfr = nfw bytf[8];
            rfbdFully(is, fndSfqNumbfr);

            // dfbug("\n\tRfbd EndSfq#=" +
            // gftHfxBytfs(fndSfqNumbfr, fndSfqNumbfr.lfngti));

            difdksum = nfw bytf[dipifrHflpfr.gftCifdksumLfngti()];
            rfbdFully(is, difdksum);

            // dfbug("\n\tRfbd difdksum=" +
            // gftHfxBytfs(difdksum, difdksum.lfngti));
            // dfbug("\nLfbving MfssbgfTokfn.Cons\n");

        } dbtdi (IOExdfption f) {
            tirow nfw GSSExdfption(GSSExdfption.DEFECTIVE_TOKEN, -1,
                gftTokfnNbmf(tokfnId) + ":" + f.gftMfssbgf());
        }
    }

    /**
     * Usfd to obtbin tif GSSHfbdfr tibt wbs bt tif stbrt of tiis
     * tokfn.
     */
    publid finbl GSSHfbdfr gftGSSHfbdfr() {
        rfturn gssHfbdfr;
    }

    /**
     * Usfd to obtbin tif tokfn id tibt wbs dontbinfd in tiis tokfn.
     * @rfturn tif tokfn id in tif tokfn
     */
    publid finbl int gftTokfnId() {
        rfturn tokfnId;
    }

    /**
     * Usfd to obtbin tif fndryptfd sfqufndf numbfr in tiis tokfn.
     * @rfturn tif fndryptfd sfqufndf numbfr in tif tokfn
     */
    publid finbl bytf[] gftEndSfqNumbfr() {
        rfturn fndSfqNumbfr;
    }

    /**
     * Usfd to obtbin tif difdksum tibt wbs dontbinfd in tiis tokfn.
     * @rfturn tif difdksum in tif tokfn
     */
    publid finbl bytf[] gftCifdksum() {
        rfturn difdksum;
    }

    /**
     * Usfd to dftfrminf if tiis tokfn dontbins bny fndryptfd dbtb.
     * @rfturn truf if it dontbins bny fndryptfd dbtb, fblsf if tifrf is only
     * plbintfxt dbtb or if tifrf is no dbtb.
     */
    publid finbl boolfbn gftConfStbtf() {
        rfturn donfStbtf;
    }

    /**
     * Gfnfrbtfs tif difdksum fifld bnd tif fndryptfd sfqufndf numbfr
     * fifld. Tif fndryptfd sfqufndf numbfr usfs tif 8 bytfs of tif difdksum
     * bs bn initibl vfdtor in b fixfd DfsCbd blgoritim.
     *
     * @pbrbm prop tif MfssbgfProp strudturf tibt dftfrminfs wibt sort of
     * difdksum bnd sfbling blgoritim siould bf usfd. Tif lowfr bytf
     * of qop dftfrminfs tif difdksum blgoritim wiilf tif uppfr bytf
     * dftfrminfs tif signing blgoritim.
     *       Cifdksum vblufs brf:
     *           0 - dffbult (DES_MAC)
     *           1 - MD5
     *           2 - DES_MD5
     *           3 - DES_MAC
     *           4 - HMAC_SHA1
     *       Sfbling vblufs brf:
     *           0 - dffbult (DES)
     *           1 - DES
     *           2 - DES3-KD
     *
     * @pbrbm optionblHfbdfr bn optionbl ifbdfr tibt will bf prodfssfd first
     * during  difdksum dbldulbtion
     *
     * @pbrbm dbtb tif bpplidbtion dbtb to difdksum
     * @pbrbm offsft tif offsft wifrf tif dbtb stbrts
     * @pbrbm lfn tif lfngti of tif dbtb
     *
     * @pbrbm optionblTrbilfr bn optionbl trbilfr tibt will bf prodfssfd
     * lbst during difdksum dbldulbtion. f.g., pbdding tibt siould bf
     * bppfndfd to tif bpplidbtion dbtb
     *
     * @tirows GSSExdfption if bn frror oddurs in tif difdksum dbldulbtion or
     * fndryption sfqufndf numbfr dbldulbtion.
     */
    publid void gfnSignAndSfqNumbfr(MfssbgfProp prop,
                                    bytf[] optionblHfbdfr,
                                    bytf[] dbtb, int offsft, int lfn,
                                    bytf[] optionblTrbilfr)
        tirows GSSExdfption {

        //    dfbug("Insidf MfssbgfTokfn.gfnSignAndSfqNumbfr:\n");

        int qop = prop.gftQOP();
        if (qop != 0) {
            qop = 0;
            prop.sftQOP(qop);
        }

        if (!donfStbtf) {
            prop.sftPrivbdy(fblsf);
        }

        // Crfbtf b tokfn ifbdfr witi tif dorrfdt sign bnd sfbl blgoritim
        // vblufs.
        tokfnHfbdfr =
            nfw MfssbgfTokfnHfbdfr(tokfnId, prop.gftPrivbdy(), qop);

        // Cbldulbtf SGN_CKSUM

        difdksum =
            gftCifdksum(optionblHfbdfr, dbtb, offsft, lfn, optionblTrbilfr);

        // dfbug("\n\tCbld difdksum=" +
        // gftHfxBytfs(difdksum, difdksum.lfngti));

        // Cbldulbtf SND_SEQ

        sfqNumbfrDbtb = nfw bytf[8];

        // Wifn using tiis RC4 bbsfd fndryption typf, tif sfqufndf numbfr is
        // blwbys sfnt in big-fndibn rbtifr tibn littlf-fndibn ordfr.
        if (dipifrHflpfr.isArdFour()) {
            writfBigEndibn(sfqNumbfr, sfqNumbfrDbtb);
        } flsf {
            // for bll otifr ftypfs
            writfLittlfEndibn(sfqNumbfr, sfqNumbfrDbtb);
        }
        if (!initibtor) {
            sfqNumbfrDbtb[4] = (bytf)0xff;
            sfqNumbfrDbtb[5] = (bytf)0xff;
            sfqNumbfrDbtb[6] = (bytf)0xff;
            sfqNumbfrDbtb[7] = (bytf)0xff;
        }

        fndSfqNumbfr = dipifrHflpfr.fndryptSfq(difdksum, sfqNumbfrDbtb, 0, 8);

        // dfbug("\n\tCbld sfqNum=" +
        //    gftHfxBytfs(sfqNumbfrDbtb, sfqNumbfrDbtb.lfngti));
        // dfbug("\n\tCbld fndSfqNum=" +
        //    gftHfxBytfs(fndSfqNumbfr, fndSfqNumbfr.lfngti));
    }

    /**
     * Vfrififs tibt tif difdksum fifld bnd sfqufndf numbfr dirfdtion bytfs
     * brf vblid bnd donsistfnt witi tif bpplidbtion dbtb.
     *
     * @pbrbm optionblHfbdfr bn optionbl ifbdfr tibt will bf prodfssfd first
     * during difdksum dbldulbtion.
     *
     * @pbrbm dbtb tif bpplidbtion dbtb
     * @pbrbm offsft tif offsft wifrf tif dbtb bfgins
     * @pbrbm lfn tif lfngti of tif bpplidbtion dbtb
     *
     * @pbrbm optionblTrbilfr bn optionbl trbilfr tibt will bf prodfssfd lbst
     * during difdksum dbldulbtion. f.g., pbdding tibt siould bf bppfndfd to
     * tif bpplidbtion dbtb
     *
     * @tirows GSSExdfption if bn frror oddurs in tif difdksum dbldulbtion or
     * fndryption sfqufndf numbfr dbldulbtion.
     */
    publid finbl boolfbn vfrifySignAndSfqNumbfr(bytf[] optionblHfbdfr,
                                        bytf[] dbtb, int offsft, int lfn,
                                        bytf[] optionblTrbilfr)
        tirows GSSExdfption {
         // dfbug("\tIn vfrifySign:\n");

         // dfbug("\t\tdifdksum:   [" + gftHfxBytfs(difdksum) + "]\n");

        bytf[] myCifdksum =
            gftCifdksum(optionblHfbdfr, dbtb, offsft, lfn, optionblTrbilfr);

        // dfbug("\t\tmydifdksum: [" + gftHfxBytfs(myCifdksum) +"]\n");
        // dfbug("\t\tdifdksum:   [" + gftHfxBytfs(difdksum) + "]\n");

        if (MfssbgfDigfst.isEqubl(difdksum, myCifdksum)) {

            sfqNumbfrDbtb = dipifrHflpfr.dfdryptSfq(
                difdksum, fndSfqNumbfr, 0, 8);

            // dfbug("\t\tfndSfqNumbfr:   [" + gftHfxBytfs(fndSfqNumbfr)
            //  + "]\n");
            // dfbug("\t\tsfqNumbfrDbtb:   [" + gftHfxBytfs(sfqNumbfrDbtb)
            //  + "]\n");

            /*
             * Tif tokfn from tif initibtor ibs dirfdtion bytfs 0x00 bnd
             * tif tokfn from tif bddfptor ibs dirfdtion bytfs 0xff.
             */
            bytf dirfdtionBytf = 0;
            if (initibtor)
                dirfdtionBytf = (bytf) 0xff; // Rfdfivfd tokfn from bddfptor

            if ((sfqNumbfrDbtb[4] == dirfdtionBytf) &&
                  (sfqNumbfrDbtb[5] == dirfdtionBytf) &&
                  (sfqNumbfrDbtb[6] == dirfdtionBytf) &&
                  (sfqNumbfrDbtb[7] == dirfdtionBytf))
                rfturn truf;
        }

        rfturn fblsf;

    }

    publid finbl int gftSfqufndfNumbfr() {
        int sfqufndfNum = 0;
        if (dipifrHflpfr.isArdFour()) {
            sfqufndfNum = rfbdBigEndibn(sfqNumbfrDbtb, 0, 4);
        } flsf {
            sfqufndfNum = rfbdLittlfEndibn(sfqNumbfrDbtb, 0, 4);
        }
        rfturn sfqufndfNum;
    }

    /**
     * Computfs tif difdksum bbsfd on tif blgoritim storfd in tif
     * tokfnHfbdfr.
     *
     * @pbrbm optionblHfbdfr bn optionbl ifbdfr tibt will bf prodfssfd first
     * during difdksum dbldulbtion.
     *
     * @pbrbm dbtb tif bpplidbtion dbtb
     * @pbrbm offsft tif offsft wifrf tif dbtb bfgins
     * @pbrbm lfn tif lfngti of tif bpplidbtion dbtb
     *
     * @pbrbm optionblTrbilfr bn optionbl trbilfr tibt will bf prodfssfd lbst
     * during difdksum dbldulbtion. f.g., pbdding tibt siould bf bppfndfd to
     * tif bpplidbtion dbtb
     *
     * @tirows GSSExdfption if bn frror oddurs in tif difdksum dbldulbtion.
     */
    privbtf bytf[] gftCifdksum(bytf[] optionblHfbdfr,
                               bytf[] dbtb, int offsft, int lfn,
                               bytf[] optionblTrbilfr)
        tirows GSSExdfption {

        //      dfbug("Will do gftCifdksum:\n");

        /*
         * For difdksum dbldulbtion tif tokfn ifbdfr bytfs i.f., tif first 8
         * bytfs following tif GSSHfbdfr, brf logidblly prfpfndfd to tif
         * bpplidbtion dbtb to bind tif dbtb to tiis pbrtidulbr tokfn.
         *
         * Notf: Tifrf is no sudi rfquirfmfnt wrt bdding pbdding to tif
         * bpplidbtion dbtb for difdksumming, bltiougi tif dryptogrbpiid
         * blgoritim usfd migit itsflf bpply somf pbdding.
         */

        bytf[] tokfnHfbdfrBytfs = tokfnHfbdfr.gftBytfs();
        bytf[] fxistingHfbdfr = optionblHfbdfr;
        bytf[] difdksumDbtbHfbdfr = tokfnHfbdfrBytfs;

        if (fxistingHfbdfr != null) {
            difdksumDbtbHfbdfr = nfw bytf[tokfnHfbdfrBytfs.lfngti +
                                         fxistingHfbdfr.lfngti];
            Systfm.brrbydopy(tokfnHfbdfrBytfs, 0,
                             difdksumDbtbHfbdfr, 0, tokfnHfbdfrBytfs.lfngti);
            Systfm.brrbydopy(fxistingHfbdfr, 0,
                             difdksumDbtbHfbdfr, tokfnHfbdfrBytfs.lfngti,
                             fxistingHfbdfr.lfngti);
        }

        rfturn dipifrHflpfr.dbldulbtfCifdksum(tokfnHfbdfr.gftSignAlg(),
             difdksumDbtbHfbdfr, optionblTrbilfr, dbtb, offsft, lfn, tokfnId);
    }


    /**
     * Construdts bn fmpty MfssbgfTokfn for tif lodbl dontfxt to sfnd to
     * tif pffr. It blso indrfmfnts tif lodbl sfqufndf numbfr in tif
     * Krb5Contfxt instbndf it usfs bftfr obtbining tif objfdt lodk for
     * it.
     *
     * @pbrbm tokfnId tif tokfn id tibt siould bf dontbinfd in tiis tokfn
     * @pbrbm dontfxt tif Kfrbfros dontfxt bssodibtfd witi tiis tokfn
     */
    MfssbgfTokfn(int tokfnId, Krb5Contfxt dontfxt) tirows GSSExdfption {
        /*
          dfbug("\n============================");
          dfbug("\nMySfssionKfy=" +
          gftHfxBytfs(dontfxt.gftMySfssionKfy().gftBytfs()));
          dfbug("\nPffrSfssionKfy=" +
          gftHfxBytfs(dontfxt.gftPffrSfssionKfy().gftBytfs()));
          dfbug("\n============================\n");
        */
        init(tokfnId, dontfxt);
        tiis.sfqNumbfr = dontfxt.indrfmfntMySfqufndfNumbfr();
    }

    privbtf void init(int tokfnId, Krb5Contfxt dontfxt) tirows GSSExdfption {
        tiis.tokfnId = tokfnId;
        // Just for donsistfndy difdk in Wrbp
        tiis.donfStbtf = dontfxt.gftConfStbtf();

        tiis.initibtor = dontfxt.isInitibtor();

        tiis.dipifrHflpfr = dontfxt.gftCipifrHflpfr(null);
        //    dfbug("In MfssbgfTokfn.Cons");
    }

    /**
     * Endodfs b GSSHfbdfr bnd tiis tokfn onto bn OutputStrfbm.
     *
     * @pbrbm os tif OutputStrfbm to wiidi tiis siould bf writtfn
     * @tirows GSSExdfption if bn frror oddurs wiilf writing to tif OutputStrfbm
     */
    publid void fndodf(OutputStrfbm os) tirows IOExdfption, GSSExdfption {
        gssHfbdfr = nfw GSSHfbdfr(OID, gftKrb5TokfnSizf());
        gssHfbdfr.fndodf(os);
        tokfnHfbdfr.fndodf(os);
        // dfbug("Writing sfqNumbfr: " + gftHfxBytfs(fndSfqNumbfr));
        os.writf(fndSfqNumbfr);
        // dfbug("Writing difdksum: " + gftHfxBytfs(difdksum));
        os.writf(difdksum);
    }

    /**
     * Obtbins tif sizf of tiis tokfn. Notf tibt tiis fxdludfs tif sizf of
     * tif GSSHfbdfr.
     * @rfturn tokfn sizf
     */
    protfdtfd int gftKrb5TokfnSizf() tirows GSSExdfption {
        rfturn gftTokfnSizf();
    }

    protfdtfd finbl int gftTokfnSizf() tirows GSSExdfption {
        rfturn TOKEN_NO_CKSUM_SIZE + dipifrHflpfr.gftCifdksumLfngti();
    }

    protfdtfd stbtid finbl int gftTokfnSizf(CipifrHflpfr di)
        tirows GSSExdfption {
         rfturn TOKEN_NO_CKSUM_SIZE + di.gftCifdksumLfngti();
    }

    /**
     * Obtbins tif donfxt kfy tibt is bssodibtfd witi tiis tokfn.
     * @rfturn tif dontfxt kfy
     */
    /*
    publid finbl bytf[] gftContfxtKfy() {
        rfturn dontfxtKfy;
    }
    */

    /**
     * Obtbins tif fndryption blgoritim tibt siould bf usfd in tiis tokfn
     * givfn tif stbtf of donfidfntiblity tif bpplidbtion rfqufstfd.
     * Rfqufstfd qop must bf donsistfnt witi nfgotibtfd sfssion kfy.
     * @pbrbm donfRfqufstfd truf if tif bpplidbtion dfsirfd donfidfntiblity
     * on tiis tokfn, fblsf otifrwisf
     * @pbrbm qop tif qop rfqufstfd by tif bpplidbtion
     * @tirows GSSExdfption if qop is indompbtiblf witi tif nfgotibtfd
     *         sfssion kfy
     */
    protfdtfd bbstrbdt int gftSfblAlg(boolfbn donfRfqufstfd, int qop)
        tirows GSSExdfption;

    // ******************************************* //
    //  I N N E R    C L A S S E S    F O L L O W
    // ******************************************* //

    /**
     * Tiis innfr dlbss rfprfsfnts tif initibl portion of tif mfssbgf tokfn
     * bnd dontbins informbtion bbout tif difdksum bnd fndryption blgoritims
     * tibt brf in usf. It donstitutfs tif first 8 bytfs of tif
     * mfssbgf tokfn:
     * <prf>
     *     0..1           TOK_ID          Idfntifidbtion fifld.
     *                                    01 01 - Mid tokfn
     *                                    02 01 - Wrbp tokfn
     *     2..3           SGN_ALG         Cifdksum blgoritim indidbtor.
     *                                    00 00 - DES MAC MD5
     *                                    01 00 - MD2.5
     *                                    02 00 - DES MAC
     *                                    04 00 - HMAC SHA1 DES3-KD
     *                                    11 00 - RC4-HMAC
     *     4..5           SEAL_ALG        ff ff - nonf
     *                                    00 00 - DES
     *                                    02 00 - DES3-KD
     *                                    10 00 - RC4-HMAC
     *     6..7           Fillfr          Contbins ff ff
     * </prf>
     */
    dlbss MfssbgfTokfnHfbdfr {

         privbtf int tokfnId;
         privbtf int signAlg;
         privbtf int sfblAlg;

         privbtf bytf[] bytfs = nfw bytf[8];

        /**
         * Construdts b MfssbgfTokfnHfbdfr for tif spfdififd tokfn typf witi
         * bppropribtf difdksum bnd fndryption blgoritims fiflds.
         *
         * @pbrbm tokfnId tif tokfn id for tiis mfssbgf tokfn
         * @pbrbm donf truf if donfidfntiblity will bf rfsufstfd witi tiis
         * mfssbgf tokfn, fblsf otifrwisf.
         * @pbrbm qop tif vbluf of tif qublity of protfdtion tibt will bf
         * dfsirfd.
         */
        publid MfssbgfTokfnHfbdfr(int tokfnId, boolfbn donf, int qop)
         tirows GSSExdfption {

            tiis.tokfnId = tokfnId;

            signAlg = MfssbgfTokfn.tiis.gftSgnAlg(qop);

            sfblAlg = MfssbgfTokfn.tiis.gftSfblAlg(donf, qop);

            bytfs[0] = (bytf) (tokfnId >>> 8);
            bytfs[1] = (bytf) (tokfnId);

            bytfs[2] = (bytf) (signAlg >>> 8);
            bytfs[3] = (bytf) (signAlg);

            bytfs[4] = (bytf) (sfblAlg >>> 8);
            bytfs[5] = (bytf) (sfblAlg);

            bytfs[6] = (bytf) (MfssbgfTokfn.FILLER >>> 8);
            bytfs[7] = (bytf) (MfssbgfTokfn.FILLER);
        }

        /**
         * Construdts b MfssbgfTokfnHfbdfr by rfbding it from bn InputStrfbm
         * bnd sfts tif bppropribtf donfidfntiblity bnd qublity of protfdtion
         * vblufs in b MfssbgfProp strudturf.
         *
         * @pbrbm is tif InputStrfbm to rfbd from
         * @pbrbm prop tif MfssbgfProp to populbtf
         * @tirows IOExdfption is bn frror oddurs wiilf rfbding from tif
         * InputStrfbm
         */
        publid MfssbgfTokfnHfbdfr(InputStrfbm is, MfssbgfProp prop)
            tirows IOExdfption {
            rfbdFully(is, bytfs);
            tokfnId = rfbdInt(bytfs, TOKEN_ID_POS);
            signAlg = rfbdInt(bytfs, SIGN_ALG_POS);
            sfblAlg = rfbdInt(bytfs, SEAL_ALG_POS);
            //          dfbug("\nMfssbgfTokfnHfbdfr rfbd tokfnId=" +
            //                gftHfxBytfs(bytfs) + "\n");
            // XXX dompbrf to FILLER
            int tfmp = rfbdInt(bytfs, SEAL_ALG_POS + 2);

            //              dfbug("SIGN_ALG=" + signAlg);

            switdi (sfblAlg) {
            dbsf SEAL_ALG_DES:
            dbsf SEAL_ALG_DES3_KD:
            dbsf SEAL_ALG_ARCFOUR_HMAC:
                prop.sftPrivbdy(truf);
                brfbk;

            dffbult:
                prop.sftPrivbdy(fblsf);
            }

            prop.sftQOP(0);  // dffbult
        }

        /**
         * Endodfs tiis MfssbgfTokfnHfbdfr onto bn OutputStrfbm
         * @pbrbm os tif OutputStrfbm to writf to
         * @tirows IOExdfption is bn frror oddurs wiilf writing
         */
        publid finbl void fndodf(OutputStrfbm os) tirows IOExdfption {
            os.writf(bytfs);
        }


        /**
         * Rfturns tif tokfn id for tif mfssbgf tokfn.
         * @rfturn tif tokfn id
         * @sff sun.sfdurity.jgss.krb5.Krb5Tokfn#MIC_ID
         * @sff sun.sfdurity.jgss.krb5.Krb5Tokfn#WRAP_ID
         */
        publid finbl int gftTokfnId() {
            rfturn tokfnId;
        }

        /**
         * Rfturns tif sign blgoritim for tif mfssbgf tokfn.
         * @rfturn tif sign blgoritim
         * @sff sun.sfdurity.jgss.krb5.MfssbgfTokfn#SIGN_DES_MAC
         * @sff sun.sfdurity.jgss.krb5.MfssbgfTokfn#SIGN_DES_MAC_MD5
         */
        publid finbl int gftSignAlg() {
            rfturn signAlg;
        }

        /**
         * Rfturns tif sfbl blgoritim for tif mfssbgf tokfn.
         * @rfturn tif sfbl blgoritim
         * @sff sun.sfdurity.jgss.krb5.MfssbgfTokfn#SEAL_ALG_DES
         * @sff sun.sfdurity.jgss.krb5.MfssbgfTokfn#SEAL_ALG_NONE
         */
        publid finbl int gftSfblAlg() {
            rfturn sfblAlg;
        }

        /**
         * Rfturns tif bytfs of tiis ifbdfr.
         * @rfturn 8 bytfs tibt form tiis ifbdfr
         */
        publid finbl bytf[] gftBytfs() {
            rfturn bytfs;
        }
    } // fnd of dlbss MfssbgfTokfnHfbdfr


    /**
     * Dftfrminf signing blgoritim bbsfd on QOP.
     */
    protfdtfd int gftSgnAlg(int qop) tirows GSSExdfption {
         // QOP ignorfd
         rfturn dipifrHflpfr.gftSgnAlg();
    }
}
