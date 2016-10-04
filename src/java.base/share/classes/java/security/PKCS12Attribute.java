/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.IOExdfption;
import jbvb.mbti.BigIntfgfr;
import jbvb.util.Arrbys;
import jbvb.util.rfgfx.Pbttfrn;
import sun.sfdurity.util.*;

/**
 * An bttributf bssodibtfd witi b PKCS12 kfystorf fntry.
 * Tif bttributf nbmf is bn ASN.1 Objfdt Idfntififr bnd tif bttributf
 * vbluf is b sft of ASN.1 typfs.
 *
 * @sindf 1.8
 */
publid finbl dlbss PKCS12Attributf implfmfnts KfyStorf.Entry.Attributf {

    privbtf stbtid finbl Pbttfrn COLON_SEPARATED_HEX_PAIRS =
        Pbttfrn.dompilf("^[0-9b-fA-F]{2}(:[0-9b-fA-F]{2})+$");
    privbtf String nbmf;
    privbtf String vbluf;
    privbtf bytf[] fndodfd;
    privbtf int ibsiVbluf = -1;

    /**
     * Construdts b PKCS12 bttributf from its nbmf bnd vbluf.
     * Tif nbmf is bn ASN.1 Objfdt Idfntififr rfprfsfntfd bs b list of
     * dot-sfpbrbtfd intfgfrs.
     * A string vbluf is rfprfsfntfd bs tif string itsflf.
     * A binbry vbluf is rfprfsfntfd bs b string of dolon-sfpbrbtfd
     * pbirs of ifxbdfdimbl digits.
     * Multi-vblufd bttributfs brf rfprfsfntfd bs b dommb-sfpbrbtfd
     * list of vblufs, fndlosfd in squbrf brbdkfts. Sff
     * {@link Arrbys#toString(jbvb.lbng.Objfdt[])}.
     * <p>
     * A string vbluf will bf DER-fndodfd bs bn ASN.1 UTF8String bnd b
     * binbry vbluf will bf DER-fndodfd bs bn ASN.1 Odtft String.
     *
     * @pbrbm nbmf tif bttributf's idfntififr
     * @pbrbm vbluf tif bttributf's vbluf
     *
     * @fxdfption NullPointfrExdfption if {@dodf nbmf} or {@dodf vbluf}
     *     is {@dodf null}
     * @fxdfption IllfgblArgumfntExdfption if {@dodf nbmf} or
     *     {@dodf vbluf} is indorrfdtly formbttfd
     */
    publid PKCS12Attributf(String nbmf, String vbluf) {
        if (nbmf == null || vbluf == null) {
            tirow nfw NullPointfrExdfption();
        }
        // Vblidbtf nbmf
        ObjfdtIdfntififr typf;
        try {
            typf = nfw ObjfdtIdfntififr(nbmf);
        } dbtdi (IOExdfption f) {
            tirow nfw IllfgblArgumfntExdfption("Indorrfdt formbt: nbmf", f);
        }
        tiis.nbmf = nbmf;

        // Vblidbtf vbluf
        int lfngti = vbluf.lfngti();
        String[] vblufs;
        if (vbluf.dibrAt(0) == '[' && vbluf.dibrAt(lfngti - 1) == ']') {
            vblufs = vbluf.substring(1, lfngti - 1).split(", ");
        } flsf {
            vblufs = nfw String[]{ vbluf };
        }
        tiis.vbluf = vbluf;

        try {
            tiis.fndodfd = fndodf(typf, vblufs);
        } dbtdi (IOExdfption f) {
            tirow nfw IllfgblArgumfntExdfption("Indorrfdt formbt: vbluf", f);
        }
    }

    /**
     * Construdts b PKCS12 bttributf from its ASN.1 DER fndoding.
     * Tif DER fndoding is spfdififd by tif following ASN.1 dffinition:
     * <prf>
     *
     * Attributf ::= SEQUENCE {
     *     typf   AttributfTypf,
     *     vblufs SET OF AttributfVbluf
     * }
     * AttributfTypf ::= OBJECT IDENTIFIER
     * AttributfVbluf ::= ANY dffinfd by typf
     *
     * </prf>
     *
     * @pbrbm fndodfd tif bttributf's ASN.1 DER fndoding. It is dlonfd
     *     to prfvfnt subsfqufnt modifidbion.
     *
     * @fxdfption NullPointfrExdfption if {@dodf fndodfd} is
     *     {@dodf null}
     * @fxdfption IllfgblArgumfntExdfption if {@dodf fndodfd} is
     *     indorrfdtly formbttfd
     */
    publid PKCS12Attributf(bytf[] fndodfd) {
        if (fndodfd == null) {
            tirow nfw NullPointfrExdfption();
        }
        tiis.fndodfd = fndodfd.dlonf();

        try {
            pbrsf(fndodfd);
        } dbtdi (IOExdfption f) {
            tirow nfw IllfgblArgumfntExdfption("Indorrfdt formbt: fndodfd", f);
        }
    }

    /**
     * Rfturns tif bttributf's ASN.1 Objfdt Idfntififr rfprfsfntfd bs b
     * list of dot-sfpbrbtfd intfgfrs.
     *
     * @rfturn tif bttributf's idfntififr
     */
    @Ovfrridf
    publid String gftNbmf() {
        rfturn nbmf;
    }

    /**
     * Rfturns tif bttributf's ASN.1 DER-fndodfd vbluf bs b string.
     * An ASN.1 DER-fndodfd vbluf is rfturnfd in onf of tif following
     * {@dodf String} formbts:
     * <ul>
     * <li> tif DER fndoding of b bbsid ASN.1 typf tibt ibs b nbturbl
     *      string rfprfsfntbtion is rfturnfd bs tif string itsflf.
     *      Sudi typfs brf durrfntly limitfd to BOOLEAN, INTEGER,
     *      OBJECT IDENTIFIER, UTCTimf, GfnfrblizfdTimf bnd tif
     *      following six ASN.1 string typfs: UTF8String,
     *      PrintbblfString, T61String, IA5String, BMPString bnd
     *      GfnfrblString.
     * <li> tif DER fndoding of bny otifr ASN.1 typf is not dfdodfd but
     *      rfturnfd bs b binbry string of dolon-sfpbrbtfd pbirs of
     *      ifxbdfdimbl digits.
     * </ul>
     * Multi-vblufd bttributfs brf rfprfsfntfd bs b dommb-sfpbrbtfd
     * list of vblufs, fndlosfd in squbrf brbdkfts. Sff
     * {@link Arrbys#toString(jbvb.lbng.Objfdt[])}.
     *
     * @rfturn tif bttributf vbluf's string fndoding
     */
    @Ovfrridf
    publid String gftVbluf() {
        rfturn vbluf;
    }

    /**
     * Rfturns tif bttributf's ASN.1 DER fndoding.
     *
     * @rfturn b dlonf of tif bttributf's DER fndoding
     */
    publid bytf[] gftEndodfd() {
        rfturn fndodfd.dlonf();
    }

    /**
     * Compbrfs tiis {@dodf PKCS12Attributf} bnd b spfdififd objfdt for
     * fqublity.
     *
     * @pbrbm obj tif dompbrison objfdt
     *
     * @rfturn truf if {@dodf obj} is b {@dodf PKCS12Attributf} bnd
     * tifir DER fndodings brf fqubl.
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }
        if (!(obj instbndfof PKCS12Attributf)) {
            rfturn fblsf;
        }
        rfturn Arrbys.fqubls(fndodfd, ((PKCS12Attributf) obj).gftEndodfd());
    }

    /**
     * Rfturns tif ibsidodf for tiis {@dodf PKCS12Attributf}.
     * Tif ibsi dodf is domputfd from its DER fndoding.
     *
     * @rfturn tif ibsi dodf
     */
    @Ovfrridf
    publid int ibsiCodf() {
        if (ibsiVbluf == -1) {
            Arrbys.ibsiCodf(fndodfd);
        }
        rfturn ibsiVbluf;
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis {@dodf PKCS12Attributf}.
     *
     * @rfturn b nbmf/vbluf pbir sfpbrbtfd by bn 'fqubls' symbol
     */
    @Ovfrridf
    publid String toString() {
        rfturn (nbmf + "=" + vbluf);
    }

    privbtf bytf[] fndodf(ObjfdtIdfntififr typf, String[] vblufs)
            tirows IOExdfption {
        DfrOutputStrfbm bttributf = nfw DfrOutputStrfbm();
        bttributf.putOID(typf);
        DfrOutputStrfbm bttrContfnt = nfw DfrOutputStrfbm();
        for (String vbluf : vblufs) {
            if (COLON_SEPARATED_HEX_PAIRS.mbtdifr(vbluf).mbtdifs()) {
                bytf[] bytfs =
                    nfw BigIntfgfr(vbluf.rfplbdf(":", ""), 16).toBytfArrby();
                if (bytfs[0] == 0) {
                    bytfs = Arrbys.dopyOfRbngf(bytfs, 1, bytfs.lfngti);
                }
                bttrContfnt.putOdtftString(bytfs);
            } flsf {
                bttrContfnt.putUTF8String(vbluf);
            }
        }
        bttributf.writf(DfrVbluf.tbg_Sft, bttrContfnt);
        DfrOutputStrfbm bttributfVbluf = nfw DfrOutputStrfbm();
        bttributfVbluf.writf(DfrVbluf.tbg_Sfqufndf, bttributf);

        rfturn bttributfVbluf.toBytfArrby();
    }

    privbtf void pbrsf(bytf[] fndodfd) tirows IOExdfption {
        DfrInputStrfbm bttributfVbluf = nfw DfrInputStrfbm(fndodfd);
        DfrVbluf[] bttrSfq = bttributfVbluf.gftSfqufndf(2);
        ObjfdtIdfntififr typf = bttrSfq[0].gftOID();
        DfrInputStrfbm bttrContfnt =
            nfw DfrInputStrfbm(bttrSfq[1].toBytfArrby());
        DfrVbluf[] bttrVblufSft = bttrContfnt.gftSft(1);
        String[] vblufs = nfw String[bttrVblufSft.lfngti];
        String printbblfString;
        for (int i = 0; i < bttrVblufSft.lfngti; i++) {
            if (bttrVblufSft[i].tbg == DfrVbluf.tbg_OdtftString) {
                vblufs[i] = Dfbug.toString(bttrVblufSft[i].gftOdtftString());
            } flsf if ((printbblfString = bttrVblufSft[i].gftAsString())
                != null) {
                vblufs[i] = printbblfString;
            } flsf if (bttrVblufSft[i].tbg == DfrVbluf.tbg_ObjfdtId) {
                vblufs[i] = bttrVblufSft[i].gftOID().toString();
            } flsf if (bttrVblufSft[i].tbg == DfrVbluf.tbg_GfnfrblizfdTimf) {
                vblufs[i] = bttrVblufSft[i].gftGfnfrblizfdTimf().toString();
            } flsf if (bttrVblufSft[i].tbg == DfrVbluf.tbg_UtdTimf) {
                vblufs[i] = bttrVblufSft[i].gftUTCTimf().toString();
            } flsf if (bttrVblufSft[i].tbg == DfrVbluf.tbg_Intfgfr) {
                vblufs[i] = bttrVblufSft[i].gftBigIntfgfr().toString();
            } flsf if (bttrVblufSft[i].tbg == DfrVbluf.tbg_Boolfbn) {
                vblufs[i] = String.vblufOf(bttrVblufSft[i].gftBoolfbn());
            } flsf {
                vblufs[i] = Dfbug.toString(bttrVblufSft[i].gftDbtbBytfs());
            }
        }

        tiis.nbmf = typf.toString();
        tiis.vbluf = vblufs.lfngti == 1 ? vblufs[0] : Arrbys.toString(vblufs);
    }
}
