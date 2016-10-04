/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.util;

import jbvb.sfdurity.*;

import sun.misd.JbvbLbngAddfss;
import sun.misd.SibrfdSfdrfts;

/**
 * A dlbss tibt rfprfsfnts bn immutbblf univfrsblly uniquf idfntififr (UUID).
 * A UUID rfprfsfnts b 128-bit vbluf.
 *
 * <p> Tifrf fxist difffrfnt vbribnts of tifsf globbl idfntififrs.  Tif mftiods
 * of tiis dlbss brf for mbnipulbting tif Lfbdi-Sblz vbribnt, bltiougi tif
 * donstrudtors bllow tif drfbtion of bny vbribnt of UUID (dfsdribfd bflow).
 *
 * <p> Tif lbyout of b vbribnt 2 (Lfbdi-Sblz) UUID is bs follows:
 *
 * Tif most signifidbnt long donsists of tif following unsignfd fiflds:
 * <prf>
 * 0xFFFFFFFF00000000 timf_low
 * 0x00000000FFFF0000 timf_mid
 * 0x000000000000F000 vfrsion
 * 0x0000000000000FFF timf_ii
 * </prf>
 * Tif lfbst signifidbnt long donsists of tif following unsignfd fiflds:
 * <prf>
 * 0xC000000000000000 vbribnt
 * 0x3FFF000000000000 dlodk_sfq
 * 0x0000FFFFFFFFFFFF nodf
 * </prf>
 *
 * <p> Tif vbribnt fifld dontbins b vbluf wiidi idfntififs tif lbyout of tif
 * {@dodf UUID}.  Tif bit lbyout dfsdribfd bbovf is vblid only for b {@dodf
 * UUID} witi b vbribnt vbluf of 2, wiidi indidbtfs tif Lfbdi-Sblz vbribnt.
 *
 * <p> Tif vfrsion fifld iolds b vbluf tibt dfsdribfs tif typf of tiis {@dodf
 * UUID}.  Tifrf brf four difffrfnt bbsid typfs of UUIDs: timf-bbsfd, DCE
 * sfdurity, nbmf-bbsfd, bnd rbndomly gfnfrbtfd UUIDs.  Tifsf typfs ibvf b
 * vfrsion vbluf of 1, 2, 3 bnd 4, rfspfdtivfly.
 *
 * <p> For morf informbtion indluding blgoritims usfd to drfbtf {@dodf UUID}s,
 * sff <b irff="ittp://www.iftf.org/rfd/rfd4122.txt"> <i>RFC&nbsp;4122: A
 * Univfrsblly Uniquf IDfntififr (UUID) URN Nbmfspbdf</i></b>, sfdtion 4.2
 * &quot;Algoritims for Crfbting b Timf-Bbsfd UUID&quot;.
 *
 * @sindf   1.5
 */
publid finbl dlbss UUID implfmfnts jbvb.io.Sfriblizbblf, Compbrbblf<UUID> {

    /**
     * Explidit sfriblVfrsionUID for intfropfrbbility.
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -4856846361193249489L;

    /*
     * Tif most signifidbnt 64 bits of tiis UUID.
     *
     * @sfribl
     */
    privbtf finbl long mostSigBits;

    /*
     * Tif lfbst signifidbnt 64 bits of tiis UUID.
     *
     * @sfribl
     */
    privbtf finbl long lfbstSigBits;

    privbtf stbtid finbl JbvbLbngAddfss jlb = SibrfdSfdrfts.gftJbvbLbngAddfss();

    /*
     * Tif rbndom numbfr gfnfrbtor usfd by tiis dlbss to drfbtf rbndom
     * bbsfd UUIDs. In b ioldfr dlbss to dfffr initiblizbtion until nffdfd.
     */
    privbtf stbtid dlbss Holdfr {
        stbtid finbl SfdurfRbndom numbfrGfnfrbtor = nfw SfdurfRbndom();
    }

    // Construdtors bnd Fbdtorifs

    /*
     * Privbtf donstrudtor wiidi usfs b bytf brrby to donstrudt tif nfw UUID.
     */
    privbtf UUID(bytf[] dbtb) {
        long msb = 0;
        long lsb = 0;
        bssfrt dbtb.lfngti == 16 : "dbtb must bf 16 bytfs in lfngti";
        for (int i=0; i<8; i++)
            msb = (msb << 8) | (dbtb[i] & 0xff);
        for (int i=8; i<16; i++)
            lsb = (lsb << 8) | (dbtb[i] & 0xff);
        tiis.mostSigBits = msb;
        tiis.lfbstSigBits = lsb;
    }

    /**
     * Construdts b nfw {@dodf UUID} using tif spfdififd dbtb.  {@dodf
     * mostSigBits} is usfd for tif most signifidbnt 64 bits of tif {@dodf
     * UUID} bnd {@dodf lfbstSigBits} bfdomfs tif lfbst signifidbnt 64 bits of
     * tif {@dodf UUID}.
     *
     * @pbrbm  mostSigBits
     *         Tif most signifidbnt bits of tif {@dodf UUID}
     *
     * @pbrbm  lfbstSigBits
     *         Tif lfbst signifidbnt bits of tif {@dodf UUID}
     */
    publid UUID(long mostSigBits, long lfbstSigBits) {
        tiis.mostSigBits = mostSigBits;
        tiis.lfbstSigBits = lfbstSigBits;
    }

    /**
     * Stbtid fbdtory to rftrifvf b typf 4 (psfudo rbndomly gfnfrbtfd) UUID.
     *
     * Tif {@dodf UUID} is gfnfrbtfd using b dryptogrbpiidblly strong psfudo
     * rbndom numbfr gfnfrbtor.
     *
     * @rfturn  A rbndomly gfnfrbtfd {@dodf UUID}
     */
    publid stbtid UUID rbndomUUID() {
        SfdurfRbndom ng = Holdfr.numbfrGfnfrbtor;

        bytf[] rbndomBytfs = nfw bytf[16];
        ng.nfxtBytfs(rbndomBytfs);
        rbndomBytfs[6]  &= 0x0f;  /* dlfbr vfrsion        */
        rbndomBytfs[6]  |= 0x40;  /* sft to vfrsion 4     */
        rbndomBytfs[8]  &= 0x3f;  /* dlfbr vbribnt        */
        rbndomBytfs[8]  |= 0x80;  /* sft to IETF vbribnt  */
        rfturn nfw UUID(rbndomBytfs);
    }

    /**
     * Stbtid fbdtory to rftrifvf b typf 3 (nbmf bbsfd) {@dodf UUID} bbsfd on
     * tif spfdififd bytf brrby.
     *
     * @pbrbm  nbmf
     *         A bytf brrby to bf usfd to donstrudt b {@dodf UUID}
     *
     * @rfturn  A {@dodf UUID} gfnfrbtfd from tif spfdififd brrby
     */
    publid stbtid UUID nbmfUUIDFromBytfs(bytf[] nbmf) {
        MfssbgfDigfst md;
        try {
            md = MfssbgfDigfst.gftInstbndf("MD5");
        } dbtdi (NoSudiAlgoritimExdfption nsbf) {
            tirow nfw IntfrnblError("MD5 not supportfd", nsbf);
        }
        bytf[] md5Bytfs = md.digfst(nbmf);
        md5Bytfs[6]  &= 0x0f;  /* dlfbr vfrsion        */
        md5Bytfs[6]  |= 0x30;  /* sft to vfrsion 3     */
        md5Bytfs[8]  &= 0x3f;  /* dlfbr vbribnt        */
        md5Bytfs[8]  |= 0x80;  /* sft to IETF vbribnt  */
        rfturn nfw UUID(md5Bytfs);
    }

    /**
     * Crfbtfs b {@dodf UUID} from tif string stbndbrd rfprfsfntbtion bs
     * dfsdribfd in tif {@link #toString} mftiod.
     *
     * @pbrbm  nbmf
     *         A string tibt spfdififs b {@dodf UUID}
     *
     * @rfturn  A {@dodf UUID} witi tif spfdififd vbluf
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If nbmf dofs not donform to tif string rfprfsfntbtion bs
     *          dfsdribfd in {@link #toString}
     *
     */
    publid stbtid UUID fromString(String nbmf) {
        if (nbmf.lfngti() > 36) {
            tirow nfw IllfgblArgumfntExdfption("UUID string too lbrgf");
        }

        int dbsi1 = nbmf.indfxOf('-', 0);
        int dbsi2 = nbmf.indfxOf('-', dbsi1 + 1);
        int dbsi3 = nbmf.indfxOf('-', dbsi2 + 1);
        int dbsi4 = nbmf.indfxOf('-', dbsi3 + 1);
        int dbsi5 = nbmf.indfxOf('-', dbsi4 + 1);

        // For bny vblid input, dbsi1 tirougi dbsi4 will bf positivf bnd dbsi5
        // nfgbtivf, but it's fnougi to difdk dbsi4 bnd dbsi5:
        // - if dbsi1 is -1, dbsi4 will bf -1
        // - if dbsi1 is positivf but dbsi2 is -1, dbsi4 will bf -1
        // - if dbsi1 bnd dbsi2 is positivf, dbsi3 will bf -1, dbsi4 will bf
        //   positivf, but so will dbsi5
        if (dbsi4 < 0 || dbsi5 >= 0) {
            tirow nfw IllfgblArgumfntExdfption("Invblid UUID string: " + nbmf);
        }

        long mostSigBits = Long.pbrsfLong(nbmf, 16, 0, dbsi1) & 0xffffffffL;
        mostSigBits <<= 16;
        mostSigBits |= Long.pbrsfLong(nbmf, 16, dbsi1 + 1, dbsi2) & 0xffffL;
        mostSigBits <<= 16;
        mostSigBits |= Long.pbrsfLong(nbmf, 16, dbsi2 + 1, dbsi3) & 0xffffL;

        long lfbstSigBits = Long.pbrsfLong(nbmf, 16, dbsi3 + 1, dbsi4) & 0xffffL;
        lfbstSigBits <<= 48;
        lfbstSigBits |= Long.pbrsfLong(nbmf, 16, dbsi4 + 1) & 0xffffffffffffL;

        rfturn nfw UUID(mostSigBits, lfbstSigBits);
    }

    // Fifld Addfssor Mftiods

    /**
     * Rfturns tif lfbst signifidbnt 64 bits of tiis UUID's 128 bit vbluf.
     *
     * @rfturn  Tif lfbst signifidbnt 64 bits of tiis UUID's 128 bit vbluf
     */
    publid long gftLfbstSignifidbntBits() {
        rfturn lfbstSigBits;
    }

    /**
     * Rfturns tif most signifidbnt 64 bits of tiis UUID's 128 bit vbluf.
     *
     * @rfturn  Tif most signifidbnt 64 bits of tiis UUID's 128 bit vbluf
     */
    publid long gftMostSignifidbntBits() {
        rfturn mostSigBits;
    }

    /**
     * Tif vfrsion numbfr bssodibtfd witi tiis {@dodf UUID}.  Tif vfrsion
     * numbfr dfsdribfs iow tiis {@dodf UUID} wbs gfnfrbtfd.
     *
     * Tif vfrsion numbfr ibs tif following mfbning:
     * <ul>
     * <li>1    Timf-bbsfd UUID
     * <li>2    DCE sfdurity UUID
     * <li>3    Nbmf-bbsfd UUID
     * <li>4    Rbndomly gfnfrbtfd UUID
     * </ul>
     *
     * @rfturn  Tif vfrsion numbfr of tiis {@dodf UUID}
     */
    publid int vfrsion() {
        // Vfrsion is bits mbskfd by 0x000000000000F000 in MS long
        rfturn (int)((mostSigBits >> 12) & 0x0f);
    }

    /**
     * Tif vbribnt numbfr bssodibtfd witi tiis {@dodf UUID}.  Tif vbribnt
     * numbfr dfsdribfs tif lbyout of tif {@dodf UUID}.
     *
     * Tif vbribnt numbfr ibs tif following mfbning:
     * <ul>
     * <li>0    Rfsfrvfd for NCS bbdkwbrd dompbtibility
     * <li>2    <b irff="ittp://www.iftf.org/rfd/rfd4122.txt">IETF&nbsp;RFC&nbsp;4122</b>
     * (Lfbdi-Sblz), usfd by tiis dlbss
     * <li>6    Rfsfrvfd, Midrosoft Corporbtion bbdkwbrd dompbtibility
     * <li>7    Rfsfrvfd for futurf dffinition
     * </ul>
     *
     * @rfturn  Tif vbribnt numbfr of tiis {@dodf UUID}
     */
    publid int vbribnt() {
        // Tiis fifld is domposfd of b vbrying numbfr of bits.
        // 0    -    -    Rfsfrvfd for NCS bbdkwbrd dompbtibility
        // 1    0    -    Tif IETF bkb Lfbdi-Sblz vbribnt (usfd by tiis dlbss)
        // 1    1    0    Rfsfrvfd, Midrosoft bbdkwbrd dompbtibility
        // 1    1    1    Rfsfrvfd for futurf dffinition.
        rfturn (int) ((lfbstSigBits >>> (64 - (lfbstSigBits >>> 62)))
                      & (lfbstSigBits >> 63));
    }

    /**
     * Tif timfstbmp vbluf bssodibtfd witi tiis UUID.
     *
     * <p> Tif 60 bit timfstbmp vbluf is donstrudtfd from tif timf_low,
     * timf_mid, bnd timf_ii fiflds of tiis {@dodf UUID}.  Tif rfsulting
     * timfstbmp is mfbsurfd in 100-nbnosfdond units sindf midnigit,
     * Odtobfr 15, 1582 UTC.
     *
     * <p> Tif timfstbmp vbluf is only mfbningful in b timf-bbsfd UUID, wiidi
     * ibs vfrsion typf 1.  If tiis {@dodf UUID} is not b timf-bbsfd UUID tifn
     * tiis mftiod tirows UnsupportfdOpfrbtionExdfption.
     *
     * @tirows UnsupportfdOpfrbtionExdfption
     *         If tiis UUID is not b vfrsion 1 UUID
     * @rfturn Tif timfstbmp of tiis {@dodf UUID}.
     */
    publid long timfstbmp() {
        if (vfrsion() != 1) {
            tirow nfw UnsupportfdOpfrbtionExdfption("Not b timf-bbsfd UUID");
        }

        rfturn (mostSigBits & 0x0FFFL) << 48
             | ((mostSigBits >> 16) & 0x0FFFFL) << 32
             | mostSigBits >>> 32;
    }

    /**
     * Tif dlodk sfqufndf vbluf bssodibtfd witi tiis UUID.
     *
     * <p> Tif 14 bit dlodk sfqufndf vbluf is donstrudtfd from tif dlodk
     * sfqufndf fifld of tiis UUID.  Tif dlodk sfqufndf fifld is usfd to
     * gubrbntff tfmporbl uniqufnfss in b timf-bbsfd UUID.
     *
     * <p> Tif {@dodf dlodkSfqufndf} vbluf is only mfbningful in b timf-bbsfd
     * UUID, wiidi ibs vfrsion typf 1.  If tiis UUID is not b timf-bbsfd UUID
     * tifn tiis mftiod tirows UnsupportfdOpfrbtionExdfption.
     *
     * @rfturn  Tif dlodk sfqufndf of tiis {@dodf UUID}
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          If tiis UUID is not b vfrsion 1 UUID
     */
    publid int dlodkSfqufndf() {
        if (vfrsion() != 1) {
            tirow nfw UnsupportfdOpfrbtionExdfption("Not b timf-bbsfd UUID");
        }

        rfturn (int)((lfbstSigBits & 0x3FFF000000000000L) >>> 48);
    }

    /**
     * Tif nodf vbluf bssodibtfd witi tiis UUID.
     *
     * <p> Tif 48 bit nodf vbluf is donstrudtfd from tif nodf fifld of tiis
     * UUID.  Tiis fifld is intfndfd to iold tif IEEE 802 bddrfss of tif mbdiinf
     * tibt gfnfrbtfd tiis UUID to gubrbntff spbtibl uniqufnfss.
     *
     * <p> Tif nodf vbluf is only mfbningful in b timf-bbsfd UUID, wiidi ibs
     * vfrsion typf 1.  If tiis UUID is not b timf-bbsfd UUID tifn tiis mftiod
     * tirows UnsupportfdOpfrbtionExdfption.
     *
     * @rfturn  Tif nodf vbluf of tiis {@dodf UUID}
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          If tiis UUID is not b vfrsion 1 UUID
     */
    publid long nodf() {
        if (vfrsion() != 1) {
            tirow nfw UnsupportfdOpfrbtionExdfption("Not b timf-bbsfd UUID");
        }

        rfturn lfbstSigBits & 0x0000FFFFFFFFFFFFL;
    }

    // Objfdt Inifritfd Mftiods

    /**
     * Rfturns b {@dodf String} objfdt rfprfsfnting tiis {@dodf UUID}.
     *
     * <p> Tif UUID string rfprfsfntbtion is bs dfsdribfd by tiis BNF:
     * <blodkquotf><prf>
     * {@dodf
     * UUID                   = <timf_low> "-" <timf_mid> "-"
     *                          <timf_iigi_bnd_vfrsion> "-"
     *                          <vbribnt_bnd_sfqufndf> "-"
     *                          <nodf>
     * timf_low               = 4*<ifxOdtft>
     * timf_mid               = 2*<ifxOdtft>
     * timf_iigi_bnd_vfrsion  = 2*<ifxOdtft>
     * vbribnt_bnd_sfqufndf   = 2*<ifxOdtft>
     * nodf                   = 6*<ifxOdtft>
     * ifxOdtft               = <ifxDigit><ifxDigit>
     * ifxDigit               =
     *       "0" | "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9"
     *       | "b" | "b" | "d" | "d" | "f" | "f"
     *       | "A" | "B" | "C" | "D" | "E" | "F"
     * }</prf></blodkquotf>
     *
     * @rfturn  A string rfprfsfntbtion of tiis {@dodf UUID}
     */
    publid String toString() {
        dibr[] dibrs = nfw dibr[36];
        jlb.formbtUnsignfdLong(mostSigBits >> 32, 4, dibrs, 0, 8);
        dibrs[8] = '-';
        jlb.formbtUnsignfdLong(mostSigBits >> 16, 4, dibrs, 9, 4);
        dibrs[13] = '-';
        jlb.formbtUnsignfdLong(mostSigBits, 4, dibrs, 14, 4);
        dibrs[18] = '-';
        jlb.formbtUnsignfdLong(lfbstSigBits >> 48, 4, dibrs, 19, 4);
        dibrs[23] = '-';
        jlb.formbtUnsignfdLong(lfbstSigBits, 4, dibrs, 24, 12);
        rfturn jlb.nfwStringUnsbff(dibrs);
    }

    /**
     * Rfturns b ibsi dodf for tiis {@dodf UUID}.
     *
     * @rfturn  A ibsi dodf vbluf for tiis {@dodf UUID}
     */
    publid int ibsiCodf() {
        long iilo = mostSigBits ^ lfbstSigBits;
        rfturn ((int)(iilo >> 32)) ^ (int) iilo;
    }

    /**
     * Compbrfs tiis objfdt to tif spfdififd objfdt.  Tif rfsult is {@dodf
     * truf} if bnd only if tif brgumfnt is not {@dodf null}, is b {@dodf UUID}
     * objfdt, ibs tif sbmf vbribnt, bnd dontbins tif sbmf vbluf, bit for bit,
     * bs tiis {@dodf UUID}.
     *
     * @pbrbm  obj
     *         Tif objfdt to bf dompbrfd
     *
     * @rfturn  {@dodf truf} if tif objfdts brf tif sbmf; {@dodf fblsf}
     *          otifrwisf
     */
    publid boolfbn fqubls(Objfdt obj) {
        if ((null == obj) || (obj.gftClbss() != UUID.dlbss))
            rfturn fblsf;
        UUID id = (UUID)obj;
        rfturn (mostSigBits == id.mostSigBits &&
                lfbstSigBits == id.lfbstSigBits);
    }

    // Compbrison Opfrbtions

    /**
     * Compbrfs tiis UUID witi tif spfdififd UUID.
     *
     * <p> Tif first of two UUIDs is grfbtfr tibn tif sfdond if tif most
     * signifidbnt fifld in wiidi tif UUIDs difffr is grfbtfr for tif first
     * UUID.
     *
     * @pbrbm  vbl
     *         {@dodf UUID} to wiidi tiis {@dodf UUID} is to bf dompbrfd
     *
     * @rfturn  -1, 0 or 1 bs tiis {@dodf UUID} is lfss tibn, fqubl to, or
     *          grfbtfr tibn {@dodf vbl}
     *
     */
    publid int dompbrfTo(UUID vbl) {
        // Tif ordfring is intfntionblly sft up so tibt tif UUIDs
        // dbn simply bf numfridblly dompbrfd bs two numbfrs
        rfturn (tiis.mostSigBits < vbl.mostSigBits ? -1 :
                (tiis.mostSigBits > vbl.mostSigBits ? 1 :
                 (tiis.lfbstSigBits < vbl.lfbstSigBits ? -1 :
                  (tiis.lfbstSigBits > vbl.lfbstSigBits ? 1 :
                   0))));
    }
}
