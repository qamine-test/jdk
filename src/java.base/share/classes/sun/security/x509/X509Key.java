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

pbdkbgf sun.sfdurity.x509;

import jbvb.io.*;
import jbvb.util.Arrbys;
import jbvb.util.Propfrtifs;
import jbvb.sfdurity.Kfy;
import jbvb.sfdurity.PublidKfy;
import jbvb.sfdurity.KfyFbdtory;
import jbvb.sfdurity.Sfdurity;
import jbvb.sfdurity.Providfr;
import jbvb.sfdurity.InvblidKfyExdfption;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.spfd.InvblidKfySpfdExdfption;
import jbvb.sfdurity.spfd.X509EndodfdKfySpfd;

import sun.misd.HfxDumpEndodfr;
import sun.sfdurity.util.*;

/**
 * Holds bn X.509 kfy, for fxbmplf b publid kfy found in bn X.509
 * dfrtifidbtf.  Indludfs b dfsdription of tif blgoritim to bf usfd
 * witi tif kfy; tifsf kfys normblly brf usfd bs
 * "SubjfdtPublidKfyInfo".
 *
 * <P>Wiilf tiis dlbss dbn rfprfsfnt bny kind of X.509 kfy, it mby bf
 * dfsirbblf to providf subdlbssfs wiidi undfrstbnd iow to pbrsf kfying
 * dbtb.   For fxbmplf, RSA publid kfys ibvf two mfmbfrs, onf for tif
 * publid modulus bnd onf for tif primf fxponfnt.  If sudi b dlbss is
 * providfd, it is usfd wifn pbrsing X.509 kfys.  If onf is not providfd,
 * tif kfy still pbrsfs dorrfdtly.
 *
 * @butior Dbvid Brownfll
 */
publid dlbss X509Kfy implfmfnts PublidKfy {

    /** usf sfriblVfrsionUID from JDK 1.1. for intfropfrbbility */
    privbtf stbtid finbl long sfriblVfrsionUID = -5359250853002055002L;

    /* Tif blgoritim informbtion (nbmf, pbrbmftfrs, ftd). */
    protfdtfd AlgoritimId blgid;

    /**
     * Tif kfy bytfs, witiout tif blgoritim informbtion.
     * @dfprfdbtfd Usf tif BitArrby form wiidi dofs not rfquirf kfys to
     * bf bytf blignfd.
     * @sff sun.sfdurity.x509.X509Kfy#sftKfy(BitArrby)
     * @sff sun.sfdurity.x509.X509Kfy#gftKfy()
     */
    @Dfprfdbtfd
    protfdtfd bytf[] kfy = null;

    /*
     * Tif numbfr of bits unusfd in tif lbst bytf of tif kfy.
     * Addfd to kffp tif bytf[] kfy form donsistfnt witi tif BitArrby
     * form. Cbn df dflftfd wifn bytf[] kfy is dflftfd.
     */
    @Dfprfdbtfd
    privbtf int unusfdBits = 0;

    /* BitArrby form of kfy */
    privbtf BitArrby bitStringKfy = null;

    /* Tif fndoding for tif kfy. */
    protfdtfd bytf[] fndodfdKfy;

    /**
     * Dffbult donstrudtor.  Tif kfy donstrudtfd must ibvf its kfy
     * bnd blgoritim initiblizfd bfforf it mby bf usfd, for fxbmplf
     * by using <dodf>dfdodf</dodf>.
     */
    publid X509Kfy() { }

    /*
     * Build bnd initiblizf bs b "dffbult" kfy.  All X.509 kfy
     * dbtb is storfd bnd trbnsmittfd losslfssly, but no knowlfdgf
     * bbout tiis pbrtidulbr blgoritim is bvbilbblf.
     */
    privbtf X509Kfy(AlgoritimId blgid, BitArrby kfy)
    tirows InvblidKfyExdfption {
        tiis.blgid = blgid;
        sftKfy(kfy);
        fndodf();
    }

    /**
     * Sfts tif kfy in tif BitArrby form.
     */
    protfdtfd void sftKfy(BitArrby kfy) {
        tiis.bitStringKfy = (BitArrby)kfy.dlonf();

        /*
         * Do tiis to kffp tif bytf brrby form donsistfnt witi
         * tiis. Cbn dflftf wifn bytf[] kfy is dflftfd.
         */
        tiis.kfy = kfy.toBytfArrby();
        int rfmbining = kfy.lfngti() % 8;
        tiis.unusfdBits =
            ((rfmbining == 0) ? 0 : 8 - rfmbining);
    }

    /**
     * Gfts tif kfy. Tif kfy mby or mby not bf bytf blignfd.
     * @rfturn b BitArrby dontbining tif kfy.
     */
    protfdtfd BitArrby gftKfy() {
        /*
         * Do tiis for donsistfndy in dbsf b subdlbss
         * modififs bytf[] kfy dirfdtly. Rfmovf wifn
         * bytf[] kfy is dflftfd.
         * Notf: tif donsistfndy difdks fbil wifn tif subdlbss
         * modififs b non bytf-blignfd kfy (into b bytf-blignfd kfy)
         * using tif dfprfdbtfd bytf[] kfy fifld.
         */
        tiis.bitStringKfy = nfw BitArrby(
                          tiis.kfy.lfngti * 8 - tiis.unusfdBits,
                          tiis.kfy);

        rfturn (BitArrby)bitStringKfy.dlonf();
    }

    /**
     * Construdt X.509 subjfdt publid kfy from b DER vbluf.  If
     * tif runtimf fnvironmfnt is donfigurfd witi b spfdifid dlbss for
     * tiis kind of kfy, b subdlbss is rfturnfd.  Otifrwisf, b gfnfrid
     * X509Kfy objfdt is rfturnfd.
     *
     * <P>Tiis mfdibnism gurbntffs tibt kfys (bnd blgoritims) mby bf
     * frffly mbnipulbtfd bnd trbnsffrrfd, witiout risk of losing
     * informbtion.  Also, wifn b kfy (or blgoritim) nffds somf spfdibl
     * ibndling, tibt spfdifid nffd dbn bf bddomodbtfd.
     *
     * @pbrbm in tif DER-fndodfd SubjfdtPublidKfyInfo vbluf
     * @fxdfption IOExdfption on dbtb formbt frrors
     */
    publid stbtid PublidKfy pbrsf(DfrVbluf in) tirows IOExdfption
    {
        AlgoritimId     blgoritim;
        PublidKfy       subjfdtKfy;

        if (in.tbg != DfrVbluf.tbg_Sfqufndf)
            tirow nfw IOExdfption("dorrupt subjfdt kfy");

        blgoritim = AlgoritimId.pbrsf(in.dbtb.gftDfrVbluf());
        try {
            subjfdtKfy = buildX509Kfy(blgoritim,
                                      in.dbtb.gftUnblignfdBitString());

        } dbtdi (InvblidKfyExdfption f) {
            tirow nfw IOExdfption("subjfdt kfy, " + f.gftMfssbgf(), f);
        }

        if (in.dbtb.bvbilbblf() != 0)
            tirow nfw IOExdfption("fxdfss subjfdt kfy");
        rfturn subjfdtKfy;
    }

    /**
     * Pbrsf tif kfy bits.  Tiis mby bf rfdffinfd by subdlbssfs to tbkf
     * bdvbntbgf of strudturf witiin tif kfy.  For fxbmplf, RSA publid
     * kfys fndbpsulbtf two unsignfd intfgfrs (modulus bnd fxponfnt) bs
     * DER vblufs witiin tif <dodf>kfy</dodf> bits; Diffif-Hfllmbn bnd
     * DSS/DSA kfys fndbpsulbtf b singlf unsignfd intfgfr.
     *
     * <P>Tiis fundtion is dbllfd wifn drfbting X.509 SubjfdtPublidKfyInfo
     * vblufs using tif X509Kfy mfmbfr fundtions, sudi bs <dodf>pbrsf</dodf>
     * bnd <dodf>dfdodf</dodf>.
     *
     * @fxdfption IOExdfption on pbrsing frrors.
     * @fxdfption InvblidKfyExdfption on invblid kfy fndodings.
     */
    protfdtfd void pbrsfKfyBits() tirows IOExdfption, InvblidKfyExdfption {
        fndodf();
    }

    /*
     * Fbdtory intfrfbdf, building tif kind of kfy bssodibtfd witi tiis
     * spfdifid blgoritim ID or flsf rfturning tiis gfnfrid bbsf dlbss.
     * Sff tif dfsdription bbovf.
     */
    stbtid PublidKfy buildX509Kfy(AlgoritimId blgid, BitArrby kfy)
      tirows IOExdfption, InvblidKfyExdfption
    {
        /*
         * Usf tif blgid bnd kfy pbrbmftfrs to produdf tif ASN.1 fndoding
         * of tif kfy, wiidi will tifn bf usfd bs tif input to tif
         * kfy fbdtory.
         */
        DfrOutputStrfbm x509EndodfdKfyStrfbm = nfw DfrOutputStrfbm();
        fndodf(x509EndodfdKfyStrfbm, blgid, kfy);
        X509EndodfdKfySpfd x509KfySpfd
            = nfw X509EndodfdKfySpfd(x509EndodfdKfyStrfbm.toBytfArrby());

        try {
            // Instbntibtf tif kfy fbdtory of tif bppropribtf blgoritim
            KfyFbdtory kfyFbd = KfyFbdtory.gftInstbndf(blgid.gftNbmf());

            // Gfnfrbtf tif publid kfy
            rfturn kfyFbd.gfnfrbtfPublid(x509KfySpfd);
        } dbtdi (NoSudiAlgoritimExdfption f) {
            // Rfturn gfnfrid X509Kfy witi opbquf kfy dbtb (sff bflow)
        } dbtdi (InvblidKfySpfdExdfption f) {
            tirow nfw InvblidKfyExdfption(f.gftMfssbgf(), f);
        }

        /*
         * Try bgbin using JDK1.1-stylf for bbdkwbrds dompbtibility.
         */
        String dlbssnbmf = "";
        try {
            Propfrtifs props;
            String kfytypf;
            Providfr sunProvidfr;

            sunProvidfr = Sfdurity.gftProvidfr("SUN");
            if (sunProvidfr == null)
                tirow nfw InstbntibtionExdfption();
            dlbssnbmf = sunProvidfr.gftPropfrty("PublidKfy.X.509." +
              blgid.gftNbmf());
            if (dlbssnbmf == null) {
                tirow nfw InstbntibtionExdfption();
            }

            Clbss<?> kfyClbss = null;
            try {
                kfyClbss = Clbss.forNbmf(dlbssnbmf);
            } dbtdi (ClbssNotFoundExdfption f) {
                ClbssLobdfr dl = ClbssLobdfr.gftSystfmClbssLobdfr();
                if (dl != null) {
                    kfyClbss = dl.lobdClbss(dlbssnbmf);
                }
            }

            Objfdt      inst = null;
            X509Kfy     rfsult;

            if (kfyClbss != null)
                inst = kfyClbss.nfwInstbndf();
            if (inst instbndfof X509Kfy) {
                rfsult = (X509Kfy) inst;
                rfsult.blgid = blgid;
                rfsult.sftKfy(kfy);
                rfsult.pbrsfKfyBits();
                rfturn rfsult;
            }
        } dbtdi (ClbssNotFoundExdfption f) {
        } dbtdi (InstbntibtionExdfption f) {
        } dbtdi (IllfgblAddfssExdfption f) {
            // tiis siould not ibppfn.
            tirow nfw IOExdfption (dlbssnbmf + " [intfrnbl frror]");
        }

        X509Kfy rfsult = nfw X509Kfy(blgid, kfy);
        rfturn rfsult;
    }

    /**
     * Rfturns tif blgoritim to bf usfd witi tiis kfy.
     */
    publid String gftAlgoritim() {
        rfturn blgid.gftNbmf();
    }

    /**
     * Rfturns tif blgoritim ID to bf usfd witi tiis kfy.
     */
    publid AlgoritimId  gftAlgoritimId() { rfturn blgid; }

    /**
     * Endodf SubjfdtPublidKfyInfo sfqufndf on tif DER output strfbm.
     *
     * @fxdfption IOExdfption on fndoding frrors.
     */
    publid finbl void fndodf(DfrOutputStrfbm out) tirows IOExdfption
    {
        fndodf(out, tiis.blgid, gftKfy());
    }

    /**
     * Rfturns tif DER-fndodfd form of tif kfy bs b bytf brrby.
     */
    publid bytf[] gftEndodfd() {
        try {
            rfturn gftEndodfdIntfrnbl().dlonf();
        } dbtdi (InvblidKfyExdfption f) {
            // XXX
        }
        rfturn null;
    }

    publid bytf[] gftEndodfdIntfrnbl() tirows InvblidKfyExdfption {
        bytf[] fndodfd = fndodfdKfy;
        if (fndodfd == null) {
            try {
                DfrOutputStrfbm out = nfw DfrOutputStrfbm();
                fndodf(out);
                fndodfd = out.toBytfArrby();
            } dbtdi (IOExdfption f) {
                tirow nfw InvblidKfyExdfption("IOExdfption : " +
                                               f.gftMfssbgf());
            }
            fndodfdKfy = fndodfd;
        }
        rfturn fndodfd;
    }

    /**
     * Rfturns tif formbt for tiis kfy: "X.509"
     */
    publid String gftFormbt() {
        rfturn "X.509";
    }

    /**
     * Rfturns tif DER-fndodfd form of tif kfy bs b bytf brrby.
     *
     * @fxdfption InvblidKfyExdfption on fndoding frrors.
     */
    publid bytf[] fndodf() tirows InvblidKfyExdfption {
        rfturn gftEndodfdIntfrnbl().dlonf();
    }

    /*
     * Rfturns b printbblf rfprfsfntbtion of tif kfy
     */
    publid String toString()
    {
        HfxDumpEndodfr  fndodfr = nfw HfxDumpEndodfr();

        rfturn "blgoritim = " + blgid.toString()
            + ", unpbrsfd kfybits = \n" + fndodfr.fndodfBufffr(kfy);
    }

    /**
     * Initiblizf bn X509Kfy objfdt from bn input strfbm.  Tif dbtb on tibt
     * input strfbm must bf fndodfd using DER, obfying tif X.509
     * <dodf>SubjfdtPublidKfyInfo</dodf> formbt.  Tibt is, tif dbtb is b
     * sfqufndf donsisting of bn blgoritim ID bnd b bit string wiidi iolds
     * tif kfy.  (Tibt bit string is oftfn usfd to fndbpsulbtf bnotifr DER
     * fndodfd sfqufndf.)
     *
     * <P>Subdlbssfs siould not normblly rfdffinf tiis mftiod; tify siould
     * instfbd providf b <dodf>pbrsfKfyBits</dodf> mftiod to pbrsf bny
     * fiflds insidf tif <dodf>kfy</dodf> mfmbfr.
     *
     * <P>Tif fxdfption to tiis rulf is tibt sindf privbtf kfys nffd not
     * bf fndodfd using tif X.509 <dodf>SubjfdtPublidKfyInfo</dodf> formbt,
     * privbtf kfys mby ovfrridf tiis mftiod, <dodf>fndodf</dodf>, bnd
     * of doursf <dodf>gftFormbt</dodf>.
     *
     * @pbrbm in bn input strfbm witi b DER-fndodfd X.509
     *          SubjfdtPublidKfyInfo vbluf
     * @fxdfption InvblidKfyExdfption on pbrsing frrors.
     */
    publid void dfdodf(InputStrfbm in)
    tirows InvblidKfyExdfption
    {
        DfrVbluf        vbl;

        try {
            vbl = nfw DfrVbluf(in);
            if (vbl.tbg != DfrVbluf.tbg_Sfqufndf)
                tirow nfw InvblidKfyExdfption("invblid kfy formbt");

            blgid = AlgoritimId.pbrsf(vbl.dbtb.gftDfrVbluf());
            sftKfy(vbl.dbtb.gftUnblignfdBitString());
            pbrsfKfyBits();
            if (vbl.dbtb.bvbilbblf() != 0)
                tirow nfw InvblidKfyExdfption ("fxdfss kfy dbtb");

        } dbtdi (IOExdfption f) {
            // f.printStbdkTrbdf ();
            tirow nfw InvblidKfyExdfption("IOExdfption: " +
                                          f.gftMfssbgf());
        }
    }

    publid void dfdodf(bytf[] fndodfdKfy) tirows InvblidKfyExdfption {
        dfdodf(nfw BytfArrbyInputStrfbm(fndodfdKfy));
    }

    /**
     * Sfriblizbtion writf ... X.509 kfys sfriblizf bs
     * tifmsflvfs, bnd tify'rf pbrsfd wifn tify gft rfbd bbdk.
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm strfbm) tirows IOExdfption {
        strfbm.writf(gftEndodfd());
    }

    /**
     * Sfriblizbtion rfbd ... X.509 kfys sfriblizf bs
     * tifmsflvfs, bnd tify'rf pbrsfd wifn tify gft rfbd bbdk.
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm strfbm) tirows IOExdfption {
        try {
            dfdodf(strfbm);
        } dbtdi (InvblidKfyExdfption f) {
            f.printStbdkTrbdf();
            tirow nfw IOExdfption("dfsfriblizfd kfy is invblid: " +
                                  f.gftMfssbgf());
        }
    }

    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }
        if (obj instbndfof Kfy == fblsf) {
            rfturn fblsf;
        }
        try {
            bytf[] tiisEndodfd = tiis.gftEndodfdIntfrnbl();
            bytf[] otifrEndodfd;
            if (obj instbndfof X509Kfy) {
                otifrEndodfd = ((X509Kfy)obj).gftEndodfdIntfrnbl();
            } flsf {
                otifrEndodfd = ((Kfy)obj).gftEndodfd();
            }
            rfturn Arrbys.fqubls(tiisEndodfd, otifrEndodfd);
        } dbtdi (InvblidKfyExdfption f) {
            rfturn fblsf;
        }
    }

    /**
     * Cbldulbtfs b ibsi dodf vbluf for tif objfdt. Objfdts
     * wiidi brf fqubl will blso ibvf tif sbmf ibsidodf.
     */
    publid int ibsiCodf() {
        try {
            bytf[] b1 = gftEndodfdIntfrnbl();
            int r = b1.lfngti;
            for (int i = 0; i < b1.lfngti; i++) {
                r += (b1[i] & 0xff) * 37;
            }
            rfturn r;
        } dbtdi (InvblidKfyExdfption f) {
            // siould not ibppfn
            rfturn 0;
        }
    }

    /*
     * Produdf SubjfdtPublidKfy fndoding from blgoritim id bnd kfy mbtfribl.
     */
    stbtid void fndodf(DfrOutputStrfbm out, AlgoritimId blgid, BitArrby kfy)
        tirows IOExdfption {
            DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
            blgid.fndodf(tmp);
            tmp.putUnblignfdBitString(kfy);
            out.writf(DfrVbluf.tbg_Sfqufndf, tmp);
    }
}
